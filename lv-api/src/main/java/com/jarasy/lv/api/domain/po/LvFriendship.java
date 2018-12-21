package com.jarasy.lv.api.domain.po;

public class LvFriendship {
    private Integer id;

    private Integer roleId;

    private String fsOpenId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getFsOpenId() {
        return fsOpenId;
    }

    public void setFsOpenId(String fsOpenId) {
        this.fsOpenId = fsOpenId == null ? null : fsOpenId.trim();
    }
}