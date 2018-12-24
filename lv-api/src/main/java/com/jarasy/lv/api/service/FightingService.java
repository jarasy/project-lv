package com.jarasy.lv.api.service;

import com.alibaba.fastjson.JSONObject;
import com.jarasy.lv.api.domain.po.LvMap;
import com.jarasy.lv.api.domain.po.LvPet;
import com.jarasy.lv.api.domain.po.LvRole;
import com.jarasy.lv.api.domain.po.LvSkill;
import com.jarasy.lv.api.domain.vo.Monster;
import com.jarasy.lv.api.domain.vo.Property;

import java.util.List;

/**
 * Created by wjh on 2018/11/20.
 */
public interface FightingService {
    JSONObject  getMonsters(String openId,Integer mapId) throws Exception;
    List<Property>  getPlayers(String openId) throws Exception;

    LvMap getMapById(Integer id) throws Exception;
    List<LvMap> getMapsByType(Integer type) throws Exception;

    List<LvSkill> getSkillsByType(String ids) throws Exception;
    LvPet getPetByRoleId(Integer roleId) throws Exception;

    JSONObject getAwards(String id) throws Exception;

}
