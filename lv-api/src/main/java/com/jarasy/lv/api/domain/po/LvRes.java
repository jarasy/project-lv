package com.jarasy.lv.api.domain.po;

public class LvRes {
    private String openId;

    private Integer hs;

    private Integer hy;

    private Integer energy;

    private Long exp;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public Integer getHs() {
        return hs;
    }

    public void setHs(Integer hs) {
        this.hs = hs;
    }

    public Integer getHy() {
        return hy;
    }

    public void setHy(Integer hy) {
        this.hy = hy;
    }

    public Integer getEnergy() {
        return energy;
    }

    public void setEnergy(Integer energy) {
        this.energy = energy;
    }

    public Long getExp() {
        return exp;
    }

    public void setExp(Long exp) {
        this.exp = exp;
    }
}