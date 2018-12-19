package com.jarasy.lv.api.service;

import com.alibaba.fastjson.JSONObject;
import com.jarasy.lv.api.domain.po.LvRole;
import com.jarasy.lv.api.domain.vo.Monster;
import com.jarasy.lv.api.domain.vo.Property;

/**
 * Created by wjh on 2018/11/20.
 */
public interface FightingService {
    Monster getMonsters(JSONObject jsonObject) throws Exception;
    Property getPlays(JSONObject jSONObject) throws Exception;

}
