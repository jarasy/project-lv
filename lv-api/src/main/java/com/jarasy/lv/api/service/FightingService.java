package com.jarasy.lv.api.service;

import com.alibaba.fastjson.JSONObject;
import com.jarasy.lv.api.domain.po.LvMap;
import com.jarasy.lv.api.domain.po.LvRole;
import com.jarasy.lv.api.domain.po.LvSkill;
import com.jarasy.lv.api.domain.vo.Monster;
import com.jarasy.lv.api.domain.vo.Property;

import java.util.List;

/**
 * Created by wjh on 2018/11/20.
 */
public interface FightingService {
    JSONObject  getMonsters(JSONObject jsonObject) throws Exception;
    Property getPlays(JSONObject jSONObject) throws Exception;

    LvMap getMapById(Integer id) throws Exception;
    List<LvMap> getMapsByType(Integer type) throws Exception;

    List<LvSkill> getSkillsByType(Integer type) throws Exception;

}
