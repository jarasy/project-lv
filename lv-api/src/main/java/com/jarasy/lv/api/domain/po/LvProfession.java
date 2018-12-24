package com.jarasy.lv.api.domain.po;

public class LvProfession {
    private Integer id;

    private String name;

    private Integer type;

    private Float hp;

    private Double mp;

    private Double gj;

    private Double fy;

    private Double sd;

    private Double hx;

    private String skills;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Float getHp() {
        return hp;
    }

    public void setHp(Float hp) {
        this.hp = hp;
    }

    public Double getMp() {
        return mp;
    }

    public void setMp(Double mp) {
        this.mp = mp;
    }

    public Double getGj() {
        return gj;
    }

    public void setGj(Double gj) {
        this.gj = gj;
    }

    public Double getFy() {
        return fy;
    }

    public void setFy(Double fy) {
        this.fy = fy;
    }

    public Double getSd() {
        return sd;
    }

    public void setSd(Double sd) {
        this.sd = sd;
    }

    public Double getHx() {
        return hx;
    }

    public void setHx(Double hx) {
        this.hx = hx;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills == null ? null : skills.trim();
    }
}