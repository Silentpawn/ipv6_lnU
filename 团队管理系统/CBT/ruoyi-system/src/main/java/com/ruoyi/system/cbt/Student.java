package com.ruoyi.system.cbt;

import com.ruoyi.system.domain.SysUser;

public class Student {
    private long stuId;             //用户编号
    private double ta;              //理论能力
    private double pa;              //运用能力
    private double ce;              //竞赛经验
    private double la;              //领导能力
    private double ca;              //协作能力
    private double ae;              //精力值
    private double uta = 0.0;              //理论能力效用
    private double upa = 0.0;              //运用能力效用
    private double uce = 0.0;              //竞赛经验效用
    private double ula = 0.0;              //领导能力效用
    private double uca = 0.0;              //协作能力效用
    private double uae = 0.0;              //精力值效用
    private boolean selected = false;//被选择

    public Student(long id, double ta, double pa, double ce, double la, double ca, double ae) {
        this.stuId = id;
        this.ta = ta;
        this.pa = pa;
        this.ce = ce;
        this.la = la;
        this.ca = ca;
        this.ae = ae;
    }


    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public long getStuId() {
        return stuId;
    }

    public void setStuId(long stuId) {
        this.stuId = stuId;
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

    public double getLa() {
        return la;
    }

    public void setLa(double la) {
        this.la = la;
    }

    public double getCa() {
        return ca;
    }

    public void setCa(double ca) {
        this.ca = ca;
    }

    public double getAe() {
        return ae;
    }

    public void setAe(double ae) {
        this.ae = ae;
    }

    public double getUta() {
        return uta;
    }

    public void setUta(double cta) {
        uta = ta * cta;
    }

    public double getUpa() {
        return upa;
    }

    public void setUpa(double cpa) {
        upa = pa * cpa;
    }

    public double getUce() {
        return uce;
    }

    public void setUce(double cce) {
        uce = ce * cce;
    }

    public double getUla() {
        return ula;
    }

    public void setUla(double cla) {
        ula = la * cla;
    }

    public double getUca() {
        return uca;
    }

    public void setUca(double cca) {
        uca = ca * cca;
    }

    public double getUae() {
        return uae;
    }

    public void setUae() {
        if (ae > -1) {
            uae = Math.sin(ae * Math.PI / 2.0);
        } else {
            uae = ae;
        }
    }
}
