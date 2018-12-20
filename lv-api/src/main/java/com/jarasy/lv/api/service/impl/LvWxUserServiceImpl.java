package com.jarasy.lv.api.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.jarasy.lv.api.domain.po.LvWxUser;
import com.jarasy.lv.api.mapper.LvWxUserMapper;
import com.jarasy.lv.api.service.LvWxUserService;
import com.jarasy.lv.common.exception.DataErrorException;
import com.jarasy.lv.redis.HashKeyPrefix;
import com.jarasy.lv.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by wjh on 2018/11/20.
 */
@Service
public class LvWxUserServiceImpl implements LvWxUserService {

    @Autowired
    private LvWxUserMapper lvWxUserMapper;
    @Autowired
    private RedisService redisService;

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
        redisService.hsetForObject(HashKeyPrefix.WXUSER_INFO + jSONObject.getString("openid"), lvWxUser, TimeUnit.DAYS.toSeconds(30));
    }

    @Override
    public LvWxUser selectByOpenid(String openid) throws Exception {
        LvWxUser lvWxUser = (LvWxUser) redisService.hgetAllForObject(HashKeyPrefix.WXUSER_INFO + openid, LvWxUser.class);
        if (null == lvWxUser) {
            lvWxUser = lvWxUserMapper.selectByPrimaryKey(openid);
            if (null != lvWxUser) {
                // 更新缓存
                redisService.hsetForObject(HashKeyPrefix.WXUSER_INFO + openid, lvWxUser, TimeUnit.DAYS.toSeconds(30));
            } else {
                throw new DataErrorException("LvWxUser openid 异常 " + openid);
            }
            return lvWxUser;
        }
        return lvWxUser;
    }

}
