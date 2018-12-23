package com.jarasy.lv.api.domain.vo;

import com.jarasy.lv.api.domain.po.LvSkill;

import java.util.List;

/**
 * Created by wjh on 2018/12/14 0014.
 */
public class Property {
    private Integer id;
    private String name;
    private Integer type;
    private Integer profession;
    private Integer energy;
    private Integer gender;
    private Integer rank;
    private Integer level;
    private Integer hy;
    private Integer hs;
    private Long exp;
    private Integer hp;
    private Integer mp;
    private Integer gj;
    private Integer fy;
    private Integer sd;
    private Integer hx;
    private Integer position;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getProfession() {
        return profession;
    }

    public void setProfession(Integer profession) {
        this.profession = profession;
    }

    public Integer getEnergy() {
        return energy;
    }

    public void setEnergy(Integer energy) {
        this.energy = energy;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getHy() {
        return hy;
    }

    public void setHy(Integer hy) {
        this.hy = hy;
    }

    public Integer getHs() {
        return hs;
    }

    public void setHs(Integer hs) {
        this.hs = hs;
    }

    public Long getExp() {
        return exp;
    }

    public void setExp(Long exp) {
        this.exp = exp;
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

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public List<LvSkill> getSkills() {
        return skills;
    }

    public void setSkills(List<LvSkill> skills) {
        this.skills = skills;
    }
}
