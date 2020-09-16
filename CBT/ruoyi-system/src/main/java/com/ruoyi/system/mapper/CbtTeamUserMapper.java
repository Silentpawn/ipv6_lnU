package com.ruoyi.system.mapper;

import java.util.List;

import com.ruoyi.system.domain.CbtTeamUser;

/**
 * 团队成员Mapper接口
 *
 * @author wsl
 * @date 2020-06-16
 */
public interface CbtTeamUserMapper {
    /**
     * 查询团队成员
     *
     * @param tuId 团队成员ID
     * @return 团队成员
     */
    public CbtTeamUser selectCbtTeamUserById(Long tuId);

    /**
     * 查询参加竞赛的学生
     *
     * @param compId 竞赛Id
     * @return 学生ID集合
     */
    public List<CbtTeamUser> selectMemByCompId(Long compId);

    /**
     * 查询团队成员列表
     *
     * @param cbtTeamUser 团队成员
     * @return 团队成员集合
     */
    public List<CbtTeamUser> selectCbtTeamUserList(CbtTeamUser cbtTeamUser);

    /**
     * 查询团队成员个数
     *
     * @param teamId 团队成员ID
     * @return 结果
     */
    public int countMemById(Long teamId);

    /**
     * 新增团队成员
     *
     * @param cbtTeamUser 团队成员
     * @return 结果
     */
    public int insertCbtTeamUser(CbtTeamUser cbtTeamUser);

    /**
     * 修改团队成员
     *
     * @param cbtTeamUser 团队成员
     * @return 结果
     */
    public int updateCbtTeamUser(CbtTeamUser cbtTeamUser);

    /**
     * 删除团队成员
     *
     * @param tuId 团队成员ID
     * @return 结果
     */
    public int deleteCbtTeamUserById(Long tuId);

    /**
     * 批量删除团队成员
     *
     * @param tuIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteCbtTeamUserByIds(String[] tuIds);
}
