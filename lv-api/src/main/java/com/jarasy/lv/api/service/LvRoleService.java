package com.jarasy.lv.api.service;

import com.alibaba.fastjson.JSONObject;
import com.jarasy.lv.api.domain.po.LvRole;
import com.jarasy.lv.api.domain.po.LvWxUser;
import com.jarasy.lv.api.domain.vo.Property;

/**
 * Created by wjh on 2018/11/20.
 */
public interface LvRoleService {
    void insert(JSONObject jsonObject);

    LvRole selectByOpenid(String openid);

    Property getRoleProperty(String openid);
}
