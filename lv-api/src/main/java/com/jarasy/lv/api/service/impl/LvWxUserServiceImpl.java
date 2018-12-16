package com.jarasy.lv.api.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.jarasy.lv.api.domain.po.LvWxUser;
import com.jarasy.lv.api.mapper.LvWxUserMapper;
import com.jarasy.lv.api.service.LvWxUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by wjh on 2018/11/20.
 */
@Service
public class LvWxUserServiceImpl implements LvWxUserService {

    @Autowired
    private LvWxUserMapper lvWxUserMapper;

    @Override
    public void insert(JSONObject jSONObject) {
        LvWxUser lvWxUser = new LvWxUser();
        lvWxUser.setOpenid(jSONObject.getString("openid"));
        lvWxUser.setNickName(jSONObject.getString("nickName"));
        lvWxUser.setGender(jSONObject.getInteger("gender"));
        lvWxUser.setCity(jSONObject.getString("city"));
        lvWxUser.setCountry(jSONObject.getString("country"));
        lvWxUser.setProvince(jSONObject.getString("province"));
        lvWxUser.setLanguage(jSONObject.getString("language"));
        lvWxUser.setAvatarurl(jSONObject.getString("avatarUrl"));
        lvWxUser.setCreateTime(new Date());
        lvWxUserMapper.insertSelective(lvWxUser);
    }

    @Override
    public LvWxUser selectByOpenid(String openid) {
        LvWxUser lvWxUser = lvWxUserMapper.selectByPrimaryKey(openid);
        return lvWxUser;
    }

}
