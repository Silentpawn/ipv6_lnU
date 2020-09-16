package com.ruoyi.system.cbt;

import java.util.List;
import java.util.Random;

public class Pso {
    private int loopNum;           //迭代次数
    private int psoNum;            //粒子的数目
    private int partNum;           //粒子的维度
    private int pso[][];             //基本粒子位置信息
    private int speed[][];     //粒子的速度
    private double fit[];                 //粒子的适应度
    private double pbest[];               //粒子局部适应度
    private int xpbest[][];    //记录迭代中最优的粒子
    private double gbest;                                       //全局最优适应度
    private int xgbest[];              //全局最优粒子

    public int[] getXgbest() {
        return xgbest;
    }


    private double w = 0.8;
    private int c1 = 2;
    private int c2 = 2;
    private int WMax;
    private int WMin = 0;
    private int SMax = 3;
    private int SMin = -3;

    public Pso(int loop_num, int pso_num, List<Student> students, Comp c) {
        loopNum = loop_num;
        psoNum = pso_num;
        partNum = c.getMemNum();
        WMax = students.size() - 1;
        pso = new int[psoNum][partNum];
        speed = new int[psoNum][partNum];
        fit = new double[psoNum];
        pbest = new double[psoNum];
        xpbest = new int[psoNum][partNum];
        xgbest = new int[partNum];
        init(students, c);
        process(students, c);
    }

    private void init(List<Student> students, Comp comp) {
        Random r = new Random();
        for (int i = 0; i < psoNum; i++) {
            for (int j = 0; j < partNum; j++) {
                int x;
                while (true) {
                    int syb = 0;
                    x = r.nextInt(WMax);
                    for (int k = 0; k < j; k++) {
                        if (pso[i][k] == x || students.get(x).isSelected() == true) {
                            syb = 1;
                            break;
                        }
                    }
                    if (syb == 0) {
                        break;
                    }
                }
                pso[i][j] = x;
                speed[i][j] = r.nextInt(SMax - SMin + 1) + SMin;
                xpbest[i][j] = 0;
            }
            fit[i] = 0.0;
            pbest[i] = 0.0;
        }
        gbest = 0.0;
    }

    private void process(List<Student> students, Comp comp) {
        Random r = new Random();
        for (int num = 1; num <= loopNum; ++num) {
            for (int i = 0; i < psoNum; i++) {
                fit[i] = fitness(pso[i], students, comp);
                if (fit[i] > pbest[i]) {
                    pbest[i] = fit[i];
                    for (int j = 0; j < partNum; ++j) {
                        xpbest[i][j] = pso[i][j];
                    }
                }
                if ((pbest[i] - gbest) > 1e-7) {
                    gbest = pbest[i];
                    for (int j = 0; j < partNum; ++j) {
                        xgbest[j] = xpbest[i][j];
                    }
                }
            }
            for (int i = 0; i < psoNum; ++i) {
                for (int j = 0; j < partNum; ++j) {
                    speed[i][j] =
                            (int) (w * speed[i][j] + c1 * Math.random() * (xpbest[i][j] - pso[i][j]) +
                                    c2 * Math.random() * (xgbest[j] - pso[i][j]));
                    if ((speed[i][j] < SMin))
                        speed[i][j] = SMin;
                    if (speed[i][j] > SMax)
                        speed[i][j] = SMax;
                    if (speed[i][j] >= 0 && speed[i][j] < 1)
                        speed[i][j] = 1;
                    if (speed[i][j] > -1 && speed[i][j] < 0)
                        speed[i][j] = -1;
                    int x = (int) (pso[i][j] + speed[i][j]);
                    if (x < WMin)
                        x = WMax;
                    if (x >= WMax)
                        x = WMin;

                    while (true) {
                        int syb = 0;
                        int k;
                        for (k = 0; k < j; k++)
                            if (pso[i][k] == x || students.get(x).isSelected() == true) {
                                x += speed[i][j];
                                if (x < WMin)
                                    x = WMax;
                                if (x > WMax)
                                    x = WMin;
                                syb = 1;
                                break;
                            }
                        if (syb == 0)
                            break;
                    }
                    pso[i][j] = x;
                }
            }
        }
    }

    private double fitness(int x[], List<Student> students, Comp comp) {
        double c = 0.;
        c = teamUility(students, comp, x);
        return c;
    }

    private double teamUility(List<Student> students, Comp comp, int x[])        //团队数组、学生信息数组、竞赛、每个团队人数
    {
        double uility = 0.;
        for (int i = 0; i < partNum; i++) {
            /**************************************
             **功能描述：计算学生的理论、运用、经验、疲劳值效用
             **最后修改时间：2018.10.31
             **修改者：wsl
             **修改内容：初次创建
             **************************************/
            students.get(x[i]).setUta(comp.getTa());
            students.get(x[i]).setUpa(comp.getPa());
            students.get(x[i]).setUce(comp.getCe());
            students.get(x[i]).setUae();
        }

        /**************************************
         **功能描述：计算学生的领导能力效用权值
         **最后修改时间：2018.10.31
         **修改者：wsl
         **修改内容：初次创建
         **************************************/
        double ld_ave = 0.;
        double ld_sd2 = 0.;
        for (int i = 0; i < partNum; i++) {
            ld_ave += (1.0 / (double) partNum) * students.get(x[i]).getLa();//领导能力平均值
        }
        for (int i = 0; i < partNum; i++) {
            ld_sd2 += (1.0 / (double) partNum) * Math.pow((students.get(x[i]).getLa() - ld_ave), 2);
        }
        double ld_sd = Math.pow(ld_sd2, 0.5);//领导能力均方差
        double pld = (ld_ave) / (1.0 + 1.0 / ld_sd);

        /**************************************
         **功能描述：计算学生的团队协作效用权值
         **最后修改时间：2018.10.31
         **修改者：wsl
         **修改内容：初次创建
         **************************************/
        double tm_ave = 0.0;
        double tm_sd2 = 0.0;
        for (int i = 0; i < partNum; i++) {
            tm_ave += (1.0 / (double) partNum) * students.get(x[i]).getCa();//团队协作平均值
        }
        for (int i = 0; i < partNum; i++) {
            tm_sd2 += (1.0 / (double) partNum) * Math.pow((students.get(x[i]).getCa() - tm_ave), 2);
        }
        double tm_sd = Math.pow(tm_sd2, 0.5);//团队协作均方差
        double ptm = Math.pow(tm_ave, tm_sd);
        //cout << ptm << endl;

        for (int i = 0; i < partNum; i++) {
            students.get(x[i]).setUla(pld);
            students.get(x[i]).setUca(ptm);
        }
        /**************************************
         **功能描述：计算该团队的团队效用
         **最后修改时间：2018.10.31
         **修改者：wsl
         **修改内容：初次创建
         **************************************/
        double tmp[] = new double[6];
        for (int i = 0; i < partNum; i++) {
            tmp[0] += students.get(x[i]).getTa();
            tmp[1] += students.get(x[i]).getPa();
            tmp[2] += students.get(x[i]).getCe();
            tmp[3] += students.get(x[i]).getLa();
            tmp[4] += students.get(x[i]).getCa();
            tmp[5] += students.get(x[i]).getAe();
        }
        for (int i = 0; i < 6; i++)
            tmp[i] /= (double) partNum;

        double tmp0 = 0.0;
        double tmp1 = 0.0;
        tmp0 = 0.5 * tmp[3] + 0.5 * tmp[4];
        tmp1 = 0.25 * tmp[0] + 0.25 * tmp[1] + 0.25 * tmp[2] + 0.25 * tmp[5];
        if (tmp1 < 0) {
            tmp1 = Math.abs(tmp1);
            uility = -1.0 * Math.pow(tmp1, tmp0);
        } else
            uility = Math.pow(tmp1, tmp0);
        return uility;
    }
}
