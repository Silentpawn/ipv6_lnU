package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 竞赛团队对象 cbt_comp_team
 *
 * @author wsl
 * @date 2020-06-16
 */
public class CbtCompTeam extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 团队ID
     */
    private Long teamId;

    /**
     * 竞赛ID
     */
    @Excel(name = "竞赛ID")
    private Long compId;

    /**
     * 团队状态
     */
    @Excel(name = "团队状态")
    private Long status;


    private CbtCompInfo compInfo;

    public CbtCompInfo getCompInfo() {
        return compInfo;
    }

    public void setCbtCompInfo(CbtCompInfo compInfo) {
        this.compInfo = compInfo;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setCompId(Long compId) {
        this.compId = compId;
    }

    public Long getCompId() {
        return compId;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getStatus() {
        return status;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("teamId", getTeamId())
                .append("compId", getCompId())
                .append("status", getStatus())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("compInfo", getCompInfo())
                .toString();
    }
}
