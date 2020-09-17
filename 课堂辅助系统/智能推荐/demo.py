# coding=utf-8
from __future__ import print_function, division, unicode_literals
#from utils import cached_property, get_log_beta_pd
import numpy as np
#from CDM.myMcmcDina.myMcmcDina import MyMcmcDina
import math
import re
import cx_Oracle as cx


class UnknownModelError(Exception):
    """
    unknown model
    """


class ItemParamError(Exception):
    """
    Item Param Type Error
    """


class ScoreError(Exception):
    """
    score error
    """


class ThetaError(Exception):
    """
    theta error
    """


class IterMethodError(Exception):
    """
    iter method error
    """

class cached_property(object):
    """
    # 从django抄的详见同名函数
    """

    def __init__(self, func, name=None):
        self.func = func
        self.__doc__ = getattr(func, '__doc__')
        self.name = name or func.__name__

    def __get__(self, instance, type=None):
        if instance is None:
            return self
        res = instance.__dict__[self.name] = self.func(instance)
        return res


class Dina(object):

    def __init__(self, attrs, score=None):
        self._attrs = attrs
        self._score = score

    @cached_property
    def _people_size(self):
        return self._score.shape[0]  # 行数

    @cached_property
    def item_size(self):
        # 题量
        return self._attrs.shape[1]  # 列数

    @cached_property
    def _skills_size(self):
        # 被试技能数量，也是试题属性数量
        return self._attrs.shape[0]

    def get_yita(self, skills):
        # dina模型下的yita值
        _yita = np.dot(skills, self._attrs)  # 矩阵乘法，点乘
        _aa = np.sum(self._attrs * self._attrs, axis=0)
        _yita[_yita < _aa] = 0
        _yita[_yita == _aa] = 1
        return _yita

    @staticmethod
    def _get_p(yita, no_slip, guess):
        # dina模型下的答题正确的概率值
        return no_slip ** yita * guess ** (1 - yita)  # （1-s）^yita*g^(a-yita)

    def get_p(self, yita, no_slip, guess):
        # 答对的概率值
        p_val = self._get_p(yita, no_slip, guess)
        p_val[p_val <= 0] = 1e-10
        p_val[p_val >= 1] = 1 - 1e-10
        return p_val


class MyBaseMcmcDina(Dina):
    def __init__(self, max_iter=10000, *args, **kwargs):
        super(MyBaseMcmcDina, self).__init__(*args, **kwargs)
        self.max_iter = max_iter

    def get_my_yita(self, skills):
        _bb = np.dot(skills, self._attrs)
        _aa = self._attrs.sum(axis=0)
        _yita = _bb / _aa
        return _yita

    def _get_item_params_tran(self, skills, no_slip, guess, next_no_slip, next_guess, sigma):
        # 项目参数转移概率函数
        yita_val = self.get_my_yita(skills)
        pre = self._get_loglik(yita_val, no_slip, guess, sigma, axis=0) + get_log_beta_pd(no_slip, guess)
        nxt = self._get_loglik(yita_val, next_no_slip, next_guess, sigma, axis=0) + get_log_beta_pd(next_no_slip,
                                                                                                    next_guess)
        res = np.exp(nxt - pre)
        res[res > 1] = 1
        return res

    def get_my_p(self, yita, no_slip, guess, sigma, score):
        # 答题得score分的概率值
        p_val = ((math.sqrt(2 * math.pi) * sigma) ** -1) * np.exp(
            (score - (1 - yita) * guess + yita * no_slip) ** 2 / (-2 * sigma ** 2))
        p_val[score < 0] = 0.5
        return p_val  # p(score|s,g,yita,sigma)=N((1-s)*yita+g*(1-yita),sigma^2)

    def _get_loglik(self, yita, no_slip, guess, sigma, axis):
        score = self._score
        p_val = self.get_my_p(yita, no_slip, guess, sigma, score)
        return np.sum(np.log(p_val), axis)

    def _get_item_params_init(self, size):
        # 初始值
        skills = np.ones((self._people_size, self._skills_size))  # 生成全1的矩阵，人数*知识点数
        skills_list = np.zeros((size, self._people_size, self._skills_size))  # 生成全0的矩阵，人数*知识点数
        no_slip = np.zeros((1, self.item_size)) + 0.7  # s初值为0.3
        guess = np.zeros((1, self.item_size)) + 0.3  # g初值为0.3
        sigma = np.zeros((1, self.item_size)) + 0.6  # sigma初值为0.6
        no_slip_list = np.zeros((size, self.item_size))
        guess_list = np.zeros((size, self.item_size))
        sigma_list = np.zeros((size, self.item_size))
        return guess, guess_list, no_slip, no_slip_list, skills, skills_list, sigma, sigma_list

    def _get_item_params_tran_res(self, skills, no_slip, guess, sigma):
        # 项目参数转移的结果
        next_no_slip = np.random.uniform(no_slip - 0.1, no_slip + 0.1)
        next_no_slip[next_no_slip <= 0.4] = 0.4 + 1e-10
        next_no_slip[next_no_slip >= 1] = 1 - 1e-10
        next_guess = np.random.uniform(guess - 0.1, guess + 0.1)
        next_guess[next_guess <= 0] = 1e-10
        next_guess[next_guess >= 0.6] = 0.6 - 1e-10
        tran_param = self._get_item_params_tran(skills, no_slip, guess, next_no_slip, next_guess, sigma)
        param_r = np.random.uniform(0, 1, tran_param.shape)
        no_slip[tran_param >= param_r] = next_no_slip[tran_param >= param_r]
        guess[tran_param >= param_r] = next_guess[tran_param >= param_r]
        return no_slip, guess


class MyMcmcDina(MyBaseMcmcDina):
    def _get_skills_tran(self, skills, no_slip, guess, next_skills, sigma):
        # 被试技能参数转移概率函数
        yita_val = self.get_yita(skills)
        pre = self._get_loglik(yita_val, no_slip, guess, sigma, axis=1)
        next_yita_val = self.get_yita(next_skills)
        nxt = self._get_loglik(next_yita_val, no_slip, guess, sigma, axis=1)
        res = np.exp(nxt - pre)
        res[res > 1] = 1
        return res

    def _get_sigma_tran(self, skills, no_slip, guess, sigma, next_sigma):
        yita_val = self.get_my_yita(skills)
        pre = self._get_loglik(yita_val, no_slip, guess, sigma, axis=0) + np.log(np.abs(0.5 - sigma))
        nxt = self._get_loglik(yita_val, no_slip, guess, next_sigma, axis=0) + np.log(np.abs(0.5 - next_sigma))
        res = np.exp(nxt - pre)
        res[res > 1] = 1
        return res

    def mcmc(self):
        size = self.max_iter
        guess, guess_list, no_slip, no_slip_list, skills, skills_list, sigma, sigma_list = self._get_item_params_init(
            size)
        for i in range(size):
            skills = self._get_skills_tran_res(skills, no_slip, guess, sigma)
            no_slip, guess = self._get_item_params_tran_res(skills, no_slip, guess, sigma)
            sigma = self._get_sigma_tran_res(skills, no_slip, guess, sigma)
            skills_list[i] = skills
            no_slip_list[i] = no_slip
            guess_list[i] = guess
            sigma_list[i] = sigma
        return skills_list[size-1], no_slip_list[size-1], guess_list[size-1]

    def _get_skills_tran_res(self, skills, no_slip, guess, sigma):
        # 被试技能参数转移结果
        next_skills = np.random.uniform(0, 1, skills.shape)  # **
        tran_skills = self._get_skills_tran(skills, no_slip, guess, next_skills, sigma)
        skills_r = np.random.uniform(0, 1, tran_skills.shape)
        skills[tran_skills >= skills_r] = next_skills[tran_skills >= skills_r]
        return skills

    def _get_sigma_tran_res(self, skills, no_slip, guess, sigma):
        next_sigma = np.random.uniform(sigma - 0.1, sigma + 0.1)
        next_sigma[next_sigma <= 0.5] = 0.5 + 1e-10
        next_sigma[next_sigma >= 1] = 1 - 1e-10
        tran_param = self._get_sigma_tran(skills, no_slip, guess, sigma, next_sigma)
        param_r = np.random.uniform(0, 1, tran_param.shape)
        sigma[tran_param >= param_r] = next_sigma[tran_param >= param_r]
        return sigma

class f_dina(object):
    def __init__(self, attrs, score=None, max_iter=10000, rec_num=10):
        self.__score = score  # 得分矩阵（人数*题目数）
        self.__attrs = attrs  # Q矩阵（知识点数*题目数）
        self.__max_iter = max_iter  # 最大迭代次数
        self.__rec_num = rec_num  # 每个学生推荐试题的数量
        self.__stu_num = self.__score.shape[0]  # 学生的个数
        self.__pro_num = self.__attrs.shape[1]  # 试题的个数
        self.__know_num = self.__attrs.shape[0]  # 知识点的个数

    def _get_my_yita(self, sk):
        _bb = np.dot(sk, self.__attrs)
        _aa = self.__attrs.sum(axis=0)
        _yita = _bb / _aa
        return _yita

    def _calculate_ut(self, sk, ns, g):  # 计算推荐效用，并返回所有学生对应试题的效用矩阵
        # 计算试题通过率
        sita = np.zeros(self.__pro_num)
        ans_num = np.zeros(self.__pro_num)
        for i in range(self.__pro_num):
            for j in range(self.__stu_num):
                if self.__score[j][i] > 0.6:
                    sita[i] += 1
                if self.__score[j][i] != -1:
                    ans_num[i] += 1
            sita[i] /= ans_num[i]

        # 计算效用
        yita = self._get_my_yita(sk)
        utility_list = np.zeros((self.__stu_num, self.__pro_num))
        rr = np.zeros((self.__stu_num, self.__pro_num))
        for i in range(self.__stu_num):
            for j in range(self.__pro_num):
                bb = 0
                for k in range(self.__know_num):
                    bb += sk[i][k] * self.__attrs.T[j][k]
                aa = np.linalg.norm(sk[i]) * np.linalg.norm(self.__attrs.T[j])
                cos = bb / aa
                simi = 0.5 + 0.5 * cos
                r = (1 - yita[i][j]) * g[j] + yita[i][j] * ns[j]
                rr[i][j] = r
                utility_list[i][j] = ((math.sqrt(2 * math.pi) * sita[j]) ** -1) * np.exp(
                    (r - simi) ** 2 / (-2 * sita[j] ** 2))
        return utility_list

    def recommendation(self):  # 推荐函数，返回
        my_mcmc_dina = MyMcmcDina(attrs=self.__attrs, score=self.__score, max_iter=self.__max_iter)  # 初始化参数
        sk, ns, g = my_mcmc_dina.mcmc()  # f-dina过程，生成学生的学习状态，失误率，猜测率
        ut_list = self._calculate_ut(sk, ns, g)  # 计算试题效用
        rec_ut, rec_list = self._sort(ut_list)
        return rec_list

    def _sort(self, est_ut):
        rec_list = np.zeros((self.__stu_num, self.__rec_num))
        rec_ut = np.zeros((self.__stu_num, self.__rec_num))

        for i in range(self.__stu_num):
            aa = np.zeros(self.__pro_num)  # 效用值
            ai = np.zeros(self.__pro_num)  # 题目序号
            for j in range(self.__pro_num):
                aa[j] = est_ut[i][j]
                ai[j] = j
            for j in range(self.__pro_num - 1):
                for k in range(self.__pro_num - 1 - j):
                    if aa[k] < aa[k + 1]:
                        tmp_ut = aa[k]
                        aa[k] = aa[k + 1]
                        aa[k + 1] = tmp_ut
                        tmp_index = ai[k]
                        ai[k] = ai[k + 1]
                        ai[k + 1] = tmp_index

            for j in range(self.__rec_num):
                rec_list[i][j] = int(ai[j])
                rec_ut[i][j] = aa[j]

        return rec_ut, rec_list
def r4beta(shape1, shape2, a, b, size):
    # 4参数beta分布
    x = np.random.beta(shape1, shape2, size)
    return (b - a) * x + a


def get_log_beta_pd(no_slip, guess):
    # beta分布的对数概率密度函数
    return np.log(0.6 - guess) + np.log(no_slip - 0.4)
class cached_property(object):
    """
    # 从django抄的详见同名函数
    """

    def __init__(self, func, name=None):
        self.func = func
        self.__doc__ = getattr(func, '__doc__')
        self.name = name or func.__name__

    def __get__(self, instance, type=None):
        if instance is None:
            return self
        res = instance.__dict__[self.name] = self.func(instance)
        return res

dsn = cx.makedsn('2001:da8:270:2020:f816:3eff:fe53:deb1', '1521', 'ORCL')
conn = cx.connect('wsl', 'wsl19950510', dsn)
#print('连接成功!')
cur = conn.cursor()
cur.execute("select count(id) from VICE_TASK")
result = cur.fetchall()#获取查询结果
for i in result:
    pro_num=i[0]


cur.execute("select count(id) from VICE_STUDENT")
result = cur.fetchall()
for i in result:
    stu_num=i[0]

cur.execute("select count(id) from VICE_TAG")
result = cur.fetchall()
for i in result:
    know_num=i[0]

#print("stu_num=",stu_num)
#print("pro_num=",pro_num)
#print("know_num=",know_num)

#提取用户id
cur.execute("SELECT id from  VICE_STUDENT ")
usr=cur.fetchall()
usr.sort();
#print("usr=",usr)
#提取标签id
cur.execute("SELECT id from VICE_TAG ")
Tags=cur.fetchall()
Tags.sort();
#print("Tags=",Tags)
#提取题目id
cur.execute("SELECT id from VICE_TASK ")
pro=cur.fetchall()
pro.sort();
#print("pro=",pro)

cur.execute("select count(id) from VICE_LIST")
rc_num=cur.fetchall()
#print(rc_num)


score = np.random.uniform(0,1,(stu_num,pro_num))  # 人*题目
attrs = np.random.binomial(1,0.5, (know_num,pro_num))  # 知识点40*题目
max_iter = 10000  # 最大迭代次数
rec_num = 10  # 每个学生推荐试题的个数
myf_dina = f_dina(attrs, score, max_iter, rec_num)
rec_list = myf_dina.recommendation()
print(rec_list)#

for i in rc_num:
    b = int(''.join(map(str, i)))  # 将list的元组转换为int类型b=92
j=1
for i in usr:
    if  j<b:
        continue
    else:
         h=int(''.join(map(str,i)))#将list的元组转换为int类型
         param = {'studentid': h, 'rc_tasks': '1.2.3.4.5.6.7.8.9.10.', 'ID': j}
         cur.execute('insert into VICE_LIST values(:studentid,:rc_tasks,:ID)', param)
         conn.commit()
    j=j+1


f = 1
for j in  range(len(rec_list)):
    template=rec_list[j]
    y = str(template)
    y = re.sub('\\[', '', y)
    y = re.sub('\\]', '', y)
    #print('输出y：')
    #print(type(y))
    #print(y)
    cur.execute("update VICE_LIST set rc_tasks =: RCLIST where ID =: ZID", ZID=f,RCLIST=y)
    conn.commit()
    f=f+1
conn.commit();
print(1)
