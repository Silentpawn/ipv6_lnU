package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.system.domain.CbtCompTeam;

/**
 * 竞赛团队Service接口
 *
 * @author wsl
 * @date 2020-06-16
 */
public interface ICbtCompTeamService {
    /**
     * 查询竞赛团队
     *
     * @param teamId 竞赛团队ID
     * @return 竞赛团队
     */
    public CbtCompTeam selectCbtCompTeamById(Long teamId);

    /**
     * 查询指定teamId竞赛团队列表
     *
     * @param ids teamIds
     * @return 竞赛团队集合
     */
    public List<CbtCompTeam> selectCbtCompTeamByUserIds(String ids);

    /**
     * 查询竞赛团队列表
     *
     * @param cbtCompTeam 竞赛团队
     * @return 竞赛团队集合
     */
    public List<CbtCompTeam> selectCbtCompTeamList(CbtCompTeam cbtCompTeam);

    /**
     * 新增竞赛团队
     *
     * @param cbtCompTeam 竞赛团队
     * @return 结果
     */
    public int insertCbtCompTeam(CbtCompTeam cbtCompTeam);

    /**
     * 修改竞赛团队
     *
     * @param cbtCompTeam 竞赛团队
     * @return 结果
     */
    public int updateCbtCompTeam(CbtCompTeam cbtCompTeam);

    /**
     * 批量删除竞赛团队
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCbtCompTeamByIds(String ids);

    /**
     * 删除竞赛团队信息
     *
     * @param teamId 竞赛团队ID
     * @return 结果
     */
    public int deleteCbtCompTeamById(Long teamId);
}
