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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by wjh on 2018/11/20.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FightingServiceImpl implements FightingService {

    @Autowired
    private LvBackpakeMapper lvBackpakeMapper;
    @Autowired
    private LvMapMapper lvMapMapper;
    @Autowired
    private LvAwardMapper lvAwardMapper;
    @Autowired
    private LvSkillMapper lvSkillMapper;
    @Autowired
    private LvFriendshipMapper lvFriendshipMapper;
    @Autowired
    private LvPetMapper lvPetMapper;
    @Autowired
    private LvResMapper lvResMapper;
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
        String openId = jSONObject.getString("openId");
        LvRes lvRes = lvResMapper.selectByPrimaryKey(openId);
        if(lvRes.getEnergy()<1){
            throw new Exception("精神力不足.");
        }
        List<Monster> monsters=new ArrayList<Monster>();
        Random rand = new Random();
        Integer mapId = jSONObject.getInteger("mapId");
        LvMap map = getMapById(mapId);
        int count= rand.nextInt(map.getMonsterCount())+1;
        List<LvProperty> lvPropertys = lvPropertyMapper.selectByTypeAndPosition(1, mapId);
        List<LvGoods> goods = backpakeService.selectGoodsByDrop(mapId);
        Map<Integer,Integer> gs=new HashMap<>();
        int exp=0;
        int hs=0;
        for (int i=0;i<count;i++) {
            int idx = rand.nextInt(lvPropertys.size());
            LvProperty p = lvPropertys.get(idx);
            int lv=p.getLv()-3+rand.nextInt(7);
            Monster monster=new Monster();
            monster.setLv(lv);
            monster.setId(p.getId());
            monster.setName(p.getName());
            monster.setRank(p.getRank());
            double xs=(float)lv/p.getLv();
            monster.setHp((int) (p.getHp()*xs));
            monster.setMp((int) (p.getMp()*xs));
            monster.setGj((int) (p.getGj()*xs));
            monster.setFy((int) (p.getFy()*xs));
            monster.setSd((int) (p.getSd()*xs));
            monster.setHx((int) (p.getHx()*xs));
            monster.setSkills(getSkillsByType(p.getSkillType()));
            monsters.add(monster);
            exp+=p.getExp()*xs;
            hs+=Integer.parseInt(p.getAward())*xs;
            idx = rand.nextInt(goods.size());
            LvGoods g = goods.get(idx);
            if ("1".equals(g.getType())){
                Integer c = gs.get(g.getType());
                if (null!=c){
                    gs.put(g.getId(),c+1);
                }else {
                    gs.put(g.getId(),1);
                }
            }else {
                if(rand.nextInt(5)==0){
                    gs.put(g.getId(),1);
                }
            }
        }
        StringBuffer goodsL=new StringBuffer();
        for (Map.Entry<Integer, Integer> entry : gs.entrySet()) {
            goodsL.append(String.valueOf(entry.getKey())+"_"+String.valueOf(entry.getValue())+",");

        }

        LvAward la=new LvAward();
        String laid = UUID.randomUUID().toString().replace("-", "");
        la.setId(laid);
        la.setExp(exp);
        la.setAward(hs+"");
        la.setGoods(goodsL.toString());
        la.setOpenId(openId);
        lvAwardMapper.insert(la);
        //减少精神力
        lvResMapper.updateFighting(lvRes.getOpenId());
        JSONObject result=new JSONObject();
        result.put("monsters",monsters);
        result.put("award",laid);
        return result;
    }

    @Override
    public JSONObject getAwards(JSONObject jSONObject) throws Exception{
        LvAward lvAward = lvAwardMapper.selectByPrimaryKey(jSONObject.getString("id"));
        LvRes lvRes = lvResMapper.selectByPrimaryKey(lvAward.getOpenId());
        LvRole lvRole = lvRoleService.selectByOpenid(lvAward.getOpenId());
        String goods = lvAward.getGoods();
        String award = lvAward.getAward().split("_")[0];
        Integer exp = lvAward.getExp();
        boolean lvup=false;

        Long oldexp = lvRes.getExp();
        Integer level = lvRole.getLevel();
        if(oldexp+exp>level*level*level){
            lvRole.setLevel(lvRole.getLevel()+1);
            lvRes.setExp(0L);
            lvup=true;
            lvRoleService.updateRole(lvRole);
        }
        lvRes.setHs(lvRes.getHs()+Integer.parseInt(award));
        lvRes.setExp(oldexp+exp);
        List<JSONObject> list=new ArrayList<JSONObject>();
        if(!StringUtils.isEmpty(goods)){
            for (String gs:goods.split(",")) {
                String[] g = gs.split("_");
                Integer id = Integer.valueOf(g[0]);
                LvGoods lvGoods = backpakeService.selectGoodsById(id);
                JSONObject jg=new JSONObject();
                jg.put("name",lvGoods.getName());
                jg.put("count",g[1]);
                list.add(jg);
                LvBackpake lb = lvBackpakeMapper.selectByRoleIdAndGoodsId(lvRole.getId(), id);
                if(null!=lb){
                    lb.setCount(lb.getCount()+Integer.valueOf(g[1]));
                }else {
                    LvBackpake lv=new LvBackpake();
                    lv.setRoleId(lvRole.getId());
                    lv.setGoodsId(lvGoods.getId());
                    lv.setType(lvGoods.getType());
                    lv.setEquipped(0);
                    lv.setCount(Integer.valueOf(g[1]));
                    lvBackpakeMapper.insert(lv);
                }
            }
        }

        lvResMapper.updateByPrimaryKeySelective(lvRes);
        lvAwardMapper.deleteByPrimaryKey(lvAward.getId());

        JSONObject result=new JSONObject();
        result.put("goods",list);
        result.put("lvUp",lvup);

        return result;
    }

    @Override
    public JSONObject getPlayers(JSONObject jSONObject) throws Exception{
        LvRole lvRole = lvRoleService.selectByOpenid(jSONObject.getString("openId"));
        LvFriendship lvFriendship = lvFriendshipMapper.selectByRoleId(lvRole.getId());
        LvRole friendship = lvRoleService.selectByOpenid(lvFriendship.getFsOpenId());
        LvPet lvPet=getPetByRoleId(lvRole.getId());
        //player
        Property player = setPlayerProperty(lvRole);
        //friendship
        Property fs = setPlayerProperty(friendship);
        //pet
        Property pet = setPetProperty(lvPet);

        JSONObject result=new JSONObject();
        result.put("player",player);
        result.put("friendship",fs);
        result.put("pet",pet);
        return result;
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

    @Override
    public LvPet getPetByRoleId(Integer roleId) throws Exception{
        LvPet lvPet =(LvPet) redisService.hgetAllForObject(HashKeyPrefix.PET_INFO + roleId, LvPet.class);
        if (null == lvPet) {
            lvPet = lvPetMapper.selectByRoleId(roleId);
            if (null != lvPet) {
                // 更新缓存
                redisService.hsetForObject(HashKeyPrefix.PET_INFO + roleId, lvPet, TimeUnit.DAYS.toSeconds(60));
            } else {
                throw new DataErrorException("LvMap id 异常 " + roleId);
            }
            return lvPet;
        }
        return lvPet;
    }


    private Property setPlayerProperty(LvRole lvRole) throws Exception {
        LvProfession lvProfession = lvRoleService.selectProfessionById(lvRole.getProfession());
        List<Map<String, String>> zbs = lvBackpakeMapper.selectZbByType(lvRole.getId(), 2);
        LvRes lvRes = lvResMapper.selectByPrimaryKey(lvRole.getOpenid());
        int hp=100;
        int mp=100;
        int gj=10;
        int fy=5;
        int sd=5;
        int hx=5;
        for (Map<String, String> zb:zbs) {
            String parameter = zb.get("parameter");
            String[] split = parameter.split("_");
            hp+=Integer.parseInt(split[1]);
            mp+=Integer.parseInt(split[2]);
            gj+=Integer.parseInt(split[3]);
            fy+=Integer.parseInt(split[4]);
            sd+=Integer.parseInt(split[5]);
            hx+=Integer.parseInt(split[6]);
        }
        Integer level = lvRole.getLevel();
        Integer gender = lvRole.getGender();

        Property property=new Property();
        property.setHp((int)(lvProfession.getHp()*hp));
        property.setMp((int)(lvProfession.getMp()*mp));
        property.setGj((int)(lvProfession.getGj()*gj));
        property.setFy((int)(lvProfession.getFy()*fy));
        property.setSd((int)(lvProfession.getSd()*sd));
        property.setHx((int)(lvProfession.getHx()*hx));
        property.setHs(lvRes.getHs());
        property.setEnergy(lvRes.getEnergy());

        property.setExp(lvRes.getExp());
        property.setGender(gender);
        property.setId(lvRole.getId());
        property.setLevel(level);
        property.setName(lvRole.getName());
        property.setProfession(lvRole.getProfession());
        property.setRank(lvRole.getRank());
        property.setPosition(lvRole.getPosition());

        property.setSkills(getSkillsByType(lvProfession.getSkillType()));

        return property;
    }

    private Property setPetProperty(LvPet lvPet) throws Exception {
        LvProfession lvProfession = lvRoleService.selectProfessionById(lvPet.getProfession());
        int hp=100;
        int mp=100;
        int gj=10;
        int fy=5;
        int sd=5;
        int hx=5;

        Integer level = lvPet.getLevel();
        Integer gender = lvPet.getGender();

        Property property=new Property();
        property.setHp((int)(lvProfession.getHp()*hp));
        property.setMp((int)(lvProfession.getMp()*mp));
        property.setGj((int)(lvProfession.getGj()*gj));
        property.setFy((int)(lvProfession.getFy()*fy));
        property.setSd((int)(lvProfession.getSd()*sd));
        property.setHx((int)(lvProfession.getHx()*hx));

        property.setExp(lvPet.getExp());
        property.setGender(gender);
        property.setId(lvPet.getId());
        property.setLevel(level);
        property.setName(lvPet.getName());
        property.setProfession(lvPet.getProfession());
        property.setRank(lvPet.getRank());

        property.setSkills(getSkillsByType(lvProfession.getSkillType()));

        return property;
    }

}
