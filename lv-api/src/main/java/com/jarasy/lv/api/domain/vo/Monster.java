package com.jarasy.lv.api.domain.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.jarasy.lv.api.domain.po.LvSkill;

import java.util.List;

/**
 * Created by wjh on 2018/12/18 0018.
 */
public class Monster {
    private Integer id;

    private String name;

    private Integer lv;

    private Integer rank;

    private Integer hp;

    private Integer mp;

    private Integer gj;

    private Integer fy;

    private Integer sd;

    private Integer hx;

    private List<LvSkill> skills;

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
        this.name = name;
    }

    public Integer getLv() {
        return lv;
    }

    public void setLv(Integer lv) {
        this.lv = lv;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public Integer getMp() {
        return mp;
    }

    public void setMp(Integer mp) {
        this.mp = mp;
    }

    public Integer getGj() {
        return gj;
    }

    public void setGj(Integer gj) {
        this.gj = gj;
    }

    public Integer getFy() {
        return fy;
    }

    public void setFy(Integer fy) {
        this.fy = fy;
    }

    public Integer getSd() {
        return sd;
    }

    public void setSd(Integer sd) {
        this.sd = sd;
    }

    public Integer getHx() {
        return hx;
    }

    public void setHx(Integer hx) {
        this.hx = hx;
    }

    public List<LvSkill> getSkills() {
        return skills;
    }

    public void setSkills(List<LvSkill> skills) {
        this.skills = skills;
    }
}
