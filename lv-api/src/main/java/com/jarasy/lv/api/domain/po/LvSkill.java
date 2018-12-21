package com.jarasy.lv.api.domain.po;

public class LvSkill {
    private Integer id;

    private String name;

    private Integer type;

    private Integer usehp;

    private Integer usemp;

    private Integer rank;

    private Integer lvLimit;

    private String parameter;

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

    public Integer getUsehp() {
        return usehp;
    }

    public void setUsehp(Integer usehp) {
        this.usehp = usehp;
    }

    public Integer getUsemp() {
        return usemp;
    }

    public void setUsemp(Integer usemp) {
        this.usemp = usemp;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getLvLimit() {
        return lvLimit;
    }

    public void setLvLimit(Integer lvLimit) {
        this.lvLimit = lvLimit;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter == null ? null : parameter.trim();
    }
}