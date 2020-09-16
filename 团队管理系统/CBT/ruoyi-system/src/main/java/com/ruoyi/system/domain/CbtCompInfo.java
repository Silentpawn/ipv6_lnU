package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 竞赛属性对象 cbt_comp_info
 *
 * @author wsl
 * @date 2020-06-16
 */
public class CbtCompInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 竞赛ID
     */
    @Excel(name = "竞赛ID")
    private Long compId;

    /**
     * 竞赛名称
     */
    @Excel(name = "竞赛名称")
    private String compName;

    /**
     * 开始时间
     */
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /**
     * 截止时间
     */
    @Excel(name = "截止时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /**
     * 专业领域
     */
    @Excel(name = "专业领域")
    private Double pf;

    /**
     * 竞赛评级
     */
    @Excel(name = "竞赛评级")
    private Double cl;

    /**
     * 理论能力
     */
    @Excel(name = "理论能力")
    private Double ta;

    /**
     * 运用能力
     */
    @Excel(name = "运用能力")
    private Double pa;

    /**
     * 竞赛经验
     */
    @Excel(name = "竞赛经验")
    private Double ce;

    /**
     * 疲劳值
     */
    @Excel(name = "疲劳值")
    private Double re;

    /**
     * 疲劳值
     */
    @Excel(name = "匹配成员数")
    private Long memNum;

    public Long getMemNum() {
        return memNum;
    }

    public void setMemNum(Long memNum) {
        this.memNum = memNum;
    }

    public void setCompId(Long compId) {
        this.compId = compId;
    }

    public Long getCompId() {
        return compId;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public String getCompName() {
        return compName;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setPf(Double pf) {
        this.pf = pf;
    }

    public Double getPf() {
        return pf;
    }

    public void setCl(Double cl) {
        this.cl = cl;
    }

    public Double getCl() {
        return cl;
    }

    public void setTa(Double ta) {
        this.ta = ta;
    }

    public Double getTa() {
        return ta;
    }

    public void setPa(Double pa) {
        this.pa = pa;
    }

    public Double getPa() {
        return pa;
    }

    public void setCe(Double ce) {
        this.ce = ce;
    }

    public Double getCe() {
        return ce;
    }

    public void setRe(Double re) {
        this.re = re;
    }

    public Double getRe() {
        return re;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("compId", getCompId())
                .append("compName", getCompName())
                .append("startTime", getStartTime())
                .append("endTime", getEndTime())
                .append("pf", getPf())
                .append("cl", getCl())
                .append("ta", getTa())
                .append("pa", getPa())
                .append("ce", getCe())
                .append("re", getRe())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
