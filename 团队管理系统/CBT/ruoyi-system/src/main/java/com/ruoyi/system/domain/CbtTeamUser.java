package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 团队成员对象 cbt_team_user
 *
 * @author wsl
 * @date 2020-06-16
 */
public class CbtTeamUser extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 组队序号 */
    private Long tuId;

    /**
     * 团队ID
     */
    @Excel(name = "团队ID")
    private Long teamId;

    /**
     * 用户ID
     */
    @Excel(name = "用户ID")
    private Long userId;

    /**
     * 领导能力评价
     */
    @Excel(name = "领导能力评价")
    private Double ela;

    /**
     * 协作能力评价
     */
    @Excel(name = "协作能力评价")
    private Double eca;

    private CbtCompTeam cbtCompTeam;

    private SysUser sysUser;

    public Long getTuId() {
        return tuId;
    }

    public void setTuId(Long tuId) {
        this.tuId = tuId;
    }

    public CbtCompTeam getCbtCompTeam() {
        return cbtCompTeam;
    }

    public void setCbtCompTeam(CbtCompTeam cbtCompTeam) {
        this.cbtCompTeam = cbtCompTeam;
    }

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setEla(Double ela) {
        this.ela = ela;
    }

    public Double getEla() {
        return ela;
    }

    public void setEca(Double eca) {
        this.eca = eca;
    }

    public Double getEca() {
        return eca;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("tuId",getTuId())
                .append("teamId", getTeamId())
                .append("userId", getUserId())
                .append("ela", getEla())
                .append("eca", getEca())
                .append("sysUser", getSysUser())
                .append("cbtCompTeam",getCbtCompTeam())
                .toString();
    }
}
