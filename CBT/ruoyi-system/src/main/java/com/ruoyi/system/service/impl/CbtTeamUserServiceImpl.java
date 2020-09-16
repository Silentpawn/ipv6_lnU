package com.ruoyi.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.CbtTeamUserMapper;
import com.ruoyi.system.domain.CbtTeamUser;
import com.ruoyi.system.service.ICbtTeamUserService;
import com.ruoyi.common.core.text.Convert;

/**
 * 团队成员Service业务层处理
 *
 * @author wsl
 * @date 2020-06-16
 */
@Service
public class CbtTeamUserServiceImpl implements ICbtTeamUserService {
    @Autowired
    private CbtTeamUserMapper cbtTeamUserMapper;

    /**
     * 查询团队成员
     *
     * @param tuId 团队成员ID
     * @return 团队成员
     */
    @Override
    public CbtTeamUser selectCbtTeamUserById(Long tuId) {
        return cbtTeamUserMapper.selectCbtTeamUserById(tuId);
    }



    /**
     * 查询参加竞赛的学生ID
     *
     * @param compId 竞赛ID
     * @return 团队成员ID
     */
    @Override
    public List<CbtTeamUser> selectMemByCompId(Long compId) {
        return cbtTeamUserMapper.selectMemByCompId(compId);
    }

    /**
     * 查询团队成员列表
     *
     * @param cbtTeamUser 团队成员
     * @return 团队成员
     */
    @Override
    public List<CbtTeamUser> selectCbtTeamUserList(CbtTeamUser cbtTeamUser) {
        return cbtTeamUserMapper.selectCbtTeamUserList(cbtTeamUser);
    }

    /**
     * 查询团队成员个数
     *
     * @param teamId 团队成员
     * @return 结果
     */
    @Override
    public int countMemById(Long teamId) {
        return cbtTeamUserMapper.countMemById(teamId);
    }

    /**
     * 新增团队成员
     *
     * @param cbtTeamUser 团队成员
     * @return 结果
     */
    @Override
    public int insertCbtTeamUser(CbtTeamUser cbtTeamUser) {
        return cbtTeamUserMapper.insertCbtTeamUser(cbtTeamUser);
    }

    /**
     * 修改团队成员
     *
     * @param cbtTeamUser 团队成员
     * @return 结果
     */
    @Override
    public int updateCbtTeamUser(CbtTeamUser cbtTeamUser) {
        return cbtTeamUserMapper.updateCbtTeamUser(cbtTeamUser);
    }

    /**
     * 删除团队成员对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCbtTeamUserByIds(String ids) {
        return cbtTeamUserMapper.deleteCbtTeamUserByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除团队成员信息
     *
     * @param tuId 组队序号ID
     * @return 结果
     */
    @Override
    public int deleteCbtTeamUserById(Long tuId) {
        return cbtTeamUserMapper.deleteCbtTeamUserById(tuId);
    }
}
