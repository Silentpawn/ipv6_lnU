package com.ruoyi.system.cbt;

import com.ruoyi.system.domain.CbtCompInfo;

public class Comp {
    private long compId;            //竞赛id
    private double pf;              //专业领域
    private double cl;              //层次等级
    private double ta;              //理论能力
    private double pa;              //运用能力
    private double ce;              //竞赛经验
    private double re;              //疲劳值
    private int memNum;

    public Comp(CbtCompInfo cbtCompInfo) {
        compId = cbtCompInfo.getCompId().longValue();
        pf = cbtCompInfo.getPf().doubleValue();
        cl = cbtCompInfo.getCl().doubleValue();
        ta = cbtCompInfo.getTa().doubleValue();
        pa = cbtCompInfo.getPa().doubleValue();
        ce = cbtCompInfo.getCe().doubleValue();
        re = cbtCompInfo.getRe().doubleValue();
        memNum = cbtCompInfo.getMemNum().intValue();
    }

    public int getMemNum() {
        return memNum;
    }

    public void setMemNum(int memNum) {
        this.memNum = memNum;
    }

    public long getCompId() {
        return compId;
    }

    public void setCompId(long compId) {
        this.compId = compId;
    }

    public double getPf() {
        return pf;
    }

    public void setPf(double pf) {
        this.pf = pf;
    }

    public double getCl() {
        return cl;
    }

    public void setCl(double cl) {
        this.cl = cl;
    }

    public double getTa() {
        return ta;
    }

    public void setTa(double ta) {
        this.ta = ta;
    }

    public double getPa() {
        return pa;
    }

    public void setPa(double pa) {
        this.pa = pa;
    }

    public double getCe() {
        return ce;
    }

    public void setCe(double ce) {
        this.ce = ce;
    }

    public double getRe() {
        return re;
    }

    public void setRe(double re) {
        this.re = re;
    }
}
