package com.jarasy.lv.api.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.jarasy.lv.api.domain.po.*;
import com.jarasy.lv.api.domain.vo.Monster;
import com.jarasy.lv.api.domain.vo.Property;
import com.jarasy.lv.api.mapper.*;
import com.jarasy.lv.api.service.BackpakeService;
import com.jarasy.lv.api.service.FightingService;
import com.jarasy.lv.api.service.LvRoleService;
import com.jarasy.lv.common.exception.DataErrorException;
import com.jarasy.lv.redis.HashKeyPrefix;
import com.jarasy.lv.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by wjh on 2018/11/20.
 */
@Service
public class FightingServiceImpl implements FightingService {

    @Autowired
    private LvRoleMapper lvRoleMapper;
    @Autowired
    private LvMapMapper lvMapMapper;
    @Autowired
    private LvAwardMapper lvAwardMapper;
    @Autowired
    private LvSkillMapper lvSkillMapper;

    @Autowired
    private LvPropertyMapper lvPropertyMapper;
    @Autowired
    private LvRoleService lvRoleService;
    @Autowired
    private BackpakeService backpakeService;
    @Autowired
    private RedisService redisService;

    @Override
    public JSONObject getMonsters(JSONObject jSONObject) throws Exception{
        List<Monster> monsters=new ArrayList<Monster>();
        Random rand = new Random();
        Integer mapId = jSONObject.getInteger("mapId");
        LvRole lvRole = lvRoleService.selectByOpenid(jSONObject.getString("openId"));
        LvMap map = getMapById(mapId);
        int count= rand.nextInt(map.getMonsterCount())+1;
        List<LvProperty> lvPropertys = lvPropertyMapper.selectByTypeAndPosition(1, mapId);
        List<Map<String, String>> goods = backpakeService.selectGoodsByDrop(mapId);
        Map<String,Integer> gs=new HashMap<>();
        int exp=0;
        int hs=0;
        for (int i=0;i<count;i++) {
            int idx = rand.nextInt(lvPropertys.size());
            LvProperty p = lvPropertys.get(idx);
            int lv=p.getLv()-5+rand.nextInt(10);
            Monster monster=new Monster();
            monster.setId(p.getId());
            monster.setName(p.getName());
            monster.setLv(p.getLv());
            monster.setRank(p.getRank());
            double xs=lv/p.getLv();
            monster.setHp((int) (p.getHp()*xs));
            monster.setMp((int) (p.getMp()*xs));
            monster.setGj((int) (p.getGj()*xs));
            monster.setFy((int) (p.getFy()*xs));
            monster.setSd((int) (p.getSd()*xs));
            monster.setHx((int) (p.getHx()*xs));
            monster.setSkills(getSkillsByType(p.getSkillType()));
            monsters.add(monster);
            exp+=p.getExp();
            hs+=Integer.parseInt(p.getAward());
            idx = rand.nextInt(goods.size());
            Map<String, String> g = goods.get(idx);
            if ("1".equals(g.get("type"))){
                Integer c = gs.get(g.get("id"));
                if (null!=c){
                    gs.put(g.get("id"),c+1);
                }else {
                    gs.put(g.get("id"),1);
                }
            }else {
                if(rand.nextInt(5)==0){
                    gs.put(g.get("id"),1);
                }
            }
        }
        StringBuffer goodsL=new StringBuffer();
        for (Map.Entry<String, Integer> entry : gs.entrySet()) {
            goodsL.append(entry.getKey() + "_" + entry.getValue()+"|");
        }
        goodsL.substring(0,goodsL.length()-1);

        LvAward la=new LvAward();
        String laid = UUID.randomUUID().toString().replace("-", "");
        la.setId(laid);
        la.setExp(exp);
        la.setAward(hs+"");
        la.setGoods(goodsL.toString());
        lvAwardMapper.insert(la);
        JSONObject result=new JSONObject();
        result.put("monsters",monsters);
        result.put("award",laid);
        return result;
    }

    @Override
    public Property getPlays(JSONObject jSONObject) throws Exception{

        return null;
    }

    @Override
    public List<LvMap> getMapsByType(Integer type) throws Exception{
        List<LvMap> maps =(List<LvMap>) redisService.hgetAllForObject(HashKeyPrefix.MAP_INFO_TYPE + type, List.class);
        if (null == maps) {
            maps = lvMapMapper.selectByType(type);
            if (null != maps) {
                // 更新缓存
                redisService.hsetForObject(HashKeyPrefix.MAP_INFO_TYPE + type, maps, TimeUnit.DAYS.toSeconds(60));
            } else {
                throw new DataErrorException("LvMaps type 异常 " + type);
            }
            return maps;
        }
        return maps;
    }

    @Override
    public LvMap getMapById(Integer id) throws Exception{
        LvMap map =(LvMap) redisService.hgetAllForObject(HashKeyPrefix.MAP_INFO + id, LvMap.class);
        if (null == map) {
            map = lvMapMapper.selectByPrimaryKey(id);
            if (null != map) {
                // 更新缓存
                redisService.hsetForObject(HashKeyPrefix.MAP_INFO + id, map, TimeUnit.DAYS.toSeconds(60));
            } else {
                throw new DataErrorException("LvMap id 异常 " + id);
            }
            return map;
        }
        return map;
    }

    @Override
    public List<LvSkill> getSkillsByType(Integer type) throws Exception{
        List<LvSkill> skills =(List<LvSkill>) redisService.hgetAllForObject(HashKeyPrefix.SKILL_INFO_TYPE + type, List.class);
        if (null == skills) {
            skills = lvSkillMapper.selectByType(type);
            if (null != skills) {
                // 更新缓存
                redisService.hsetForObject(HashKeyPrefix.SKILL_INFO_TYPE + type, skills, TimeUnit.DAYS.toSeconds(60));
            } else {
                throw new DataErrorException("LvSkill type 异常 " + type);
            }
            return skills;
        }
        return skills;
    }

}
