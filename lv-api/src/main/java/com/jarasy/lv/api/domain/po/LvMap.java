package com.jarasy.lv.api.domain.po;

public class LvMap {
    private Integer id;

    private String name;

    private Integer type;

    private Integer monsterCount;

    private String bosses;

    private Integer limit;

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

    public Integer getMonsterCount() {
        return monsterCount;
    }

    public void setMonsterCount(Integer monsterCount) {
        this.monsterCount = monsterCount;
    }

    public String getBosses() {
        return bosses;
    }

    public void setBosses(String bosses) {
        this.bosses = bosses == null ? null : bosses.trim();
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}