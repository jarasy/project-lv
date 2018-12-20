package com.jarasy.lv.api.service;

import com.alibaba.fastjson.JSONObject;
import com.jarasy.lv.api.domain.po.LvWxUser;

/**
 * Created by wjh on 2018/11/20.
 */
public interface LvWxUserService {
    void insert(JSONObject jsonObject);

    LvWxUser selectByOpenid(String openid) throws Exception;
}
