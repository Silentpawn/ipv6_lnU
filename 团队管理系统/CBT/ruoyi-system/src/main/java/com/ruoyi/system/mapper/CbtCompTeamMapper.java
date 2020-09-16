package com.ruoyi.system.mapper;

import java.util.List;

import com.ruoyi.system.domain.CbtCompTeam;

/**
 * 竞赛团队Mapper接口
 *
 * @author wsl
 * @date 2020-06-16
 */
public interface CbtCompTeamMapper {
    /**
     * 查询竞赛团队
     *
     * @param teamId 竞赛团队ID
     * @return 竞赛团队
     */
    public CbtCompTeam selectCbtCompTeamById(Long teamId);

    public List<CbtCompTeam> selectCbtCompTeamByUserIds(String[] teamIds);

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
     * 删除竞赛团队
     *
     * @param teamId 竞赛团队ID
     * @return 结果
     */
    public int deleteCbtCompTeamById(Long teamId);

    /**
     * 批量删除竞赛团队
     *
     * @param teamIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteCbtCompTeamByIds(String[] teamIds);
}
