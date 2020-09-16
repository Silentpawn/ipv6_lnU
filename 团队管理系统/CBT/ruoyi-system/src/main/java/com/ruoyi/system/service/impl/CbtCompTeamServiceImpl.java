package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.CbtCompTeamMapper;
import com.ruoyi.system.domain.CbtCompTeam;
import com.ruoyi.system.service.ICbtCompTeamService;
import com.ruoyi.common.core.text.Convert;

/**
 * 竞赛团队Service业务层处理
 *
 * @author wsl
 * @date 2020-06-16
 */
@Service
public class CbtCompTeamServiceImpl implements ICbtCompTeamService {
    @Autowired
    private CbtCompTeamMapper cbtCompTeamMapper;

    /**
     * 查询竞赛团队
     *
     * @param teamId 竞赛团队ID
     * @return 竞赛团队
     */
    @Override
    public CbtCompTeam selectCbtCompTeamById(Long teamId) {
        return cbtCompTeamMapper.selectCbtCompTeamById(teamId);
    }

    /**
     * 查询指定teamId竞赛团队列表
     *
     * @param cbtCompTeam 竞赛团队
     * @return 竞赛团队
     */
    @Override
    public List<CbtCompTeam> selectCbtCompTeamByUserIds(String ids) {
        return cbtCompTeamMapper.selectCbtCompTeamByUserIds(Convert.toStrArray(ids));
    }

    /**
     * 查询竞赛团队列表
     *
     * @param cbtCompTeam 竞赛团队
     * @return 竞赛团队
     */
    @Override
    public List<CbtCompTeam> selectCbtCompTeamList(CbtCompTeam cbtCompTeam) {
        return cbtCompTeamMapper.selectCbtCompTeamList(cbtCompTeam);
    }

    /**
     * 新增竞赛团队
     *
     * @param cbtCompTeam 竞赛团队
     * @return 结果
     */
    @Override
    public int insertCbtCompTeam(CbtCompTeam cbtCompTeam) {
        cbtCompTeam.setCreateTime(DateUtils.getNowDate());
        return cbtCompTeamMapper.insertCbtCompTeam(cbtCompTeam);
    }

    /**
     * 修改竞赛团队
     *
     * @param cbtCompTeam 竞赛团队
     * @return 结果
     */
    @Override
    public int updateCbtCompTeam(CbtCompTeam cbtCompTeam) {
        return cbtCompTeamMapper.updateCbtCompTeam(cbtCompTeam);
    }

    /**
     * 删除竞赛团队对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCbtCompTeamByIds(String ids) {
        return cbtCompTeamMapper.deleteCbtCompTeamByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除竞赛团队信息
     *
     * @param teamId 竞赛团队ID
     * @return 结果
     */
    @Override
    public int deleteCbtCompTeamById(Long teamId) {
        return cbtCompTeamMapper.deleteCbtCompTeamById(teamId);
    }
}
