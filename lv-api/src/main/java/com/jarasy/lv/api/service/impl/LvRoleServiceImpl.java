package com.jarasy.lv.api.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.jarasy.lv.api.domain.po.LvBackpake;
import com.jarasy.lv.api.domain.po.LvProfession;
import com.jarasy.lv.api.domain.po.LvRole;
import com.jarasy.lv.api.domain.po.LvWxUser;
import com.jarasy.lv.api.domain.vo.Property;
import com.jarasy.lv.api.mapper.LvBackpakeMapper;
import com.jarasy.lv.api.mapper.LvProfessionMapper;
import com.jarasy.lv.api.mapper.LvRoleMapper;
import com.jarasy.lv.api.service.LvRoleService;
import com.jarasy.lv.api.service.LvWxUserService;
import com.jarasy.lv.common.exception.DataErrorException;
import com.jarasy.lv.redis.HashKeyPrefix;
import com.jarasy.lv.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by wjh on 2018/11/20.
 */
@Service
public class LvRoleServiceImpl implements LvRoleService {

    @Autowired
    private LvRoleMapper lvRoleMapper;
    @Autowired
    private LvProfessionMapper lvProfessionMapper;
    @Autowired
    private LvBackpakeMapper lvBackpakeMapper;
    @Autowired
    private RedisService redisService;


    @Override
    public void insert(JSONObject jSONObject) {
        LvRole lvRole = new LvRole();
        String openid = jSONObject.getString("openid");
        lvRole.setOpenid(openid);
        lvRole.setName(jSONObject.getString("name"));
        lvRole.setGender(jSONObject.getInteger("gender"));
        lvRole.setRank(1);
        lvRole.setLevel(1);
        lvRole.setProfession(jSONObject.getInteger("profession"));
        lvRole.setHs(100);
        lvRole.setHy(0);
        lvRole.setExp(0L);
        lvRole.setPosition(0);
        lvRole.setCreateTime(new Date());
        lvRoleMapper.insertSelective(lvRole);
        redisService.hsetForObject(HashKeyPrefix.ROLE_INFO + openid, lvRole, TimeUnit.DAYS.toSeconds(30));

    }

    @Override
    public LvRole selectByOpenid(String openid) throws Exception {
        LvRole lvRole = (LvRole) redisService.hgetAllForObject(HashKeyPrefix.ROLE_INFO + openid, LvRole.class);
        if (null == lvRole) {
            lvRole = lvRoleMapper.selectByOpenid(openid);
            if (null != lvRole) {
                // 更新缓存
                redisService.hsetForObject(HashKeyPrefix.ROLE_INFO + openid, lvRole, TimeUnit.DAYS.toSeconds(30));
            } else {
                throw new DataErrorException("lvRole openid 异常 " + openid);
            }
            return lvRole;
        }
        return lvRole;
    }

    @Override
    public LvProfession selectProfessionById(Integer id) throws Exception {
        LvProfession lvProfession = (LvProfession) redisService.hgetAllForObject(HashKeyPrefix.PROFESSION_INFO + id, LvProfession.class);
        if (null == lvProfession) {
            lvProfession = lvProfessionMapper.selectByPrimaryKey(id);
            if (null != lvProfession) {
                // 更新缓存
                redisService.hsetForObject(HashKeyPrefix.PROFESSION_INFO + id, lvProfession, TimeUnit.DAYS.toSeconds(30));
            } else {
                throw new DataErrorException("lvRole openid 异常 " + id);
            }
            return lvProfession;
        }
        return lvProfession;
    }

    @Override
    public Property getRoleProperty(String openid) throws Exception {
        LvRole lvRole = selectByOpenid(openid);
        LvProfession lvProfession = selectProfessionById(lvRole.getProfession());
        List<Map<String, String>> zbs = lvBackpakeMapper.selectZbByType(lvRole.getId(), 2);
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
        property.setHs(lvRole.getHs());
        property.setEnergy(lvRole.getEnergy());

        property.setExp(lvRole.getExp());
        property.setGender(gender==1?"男修":"女修");
        property.setId(lvRole.getId());
        property.setLevel(level);
        property.setName(lvRole.getName());
        property.setProfession(lvRole.getProfession());
        property.setRank(lvRole.getRank());
        property.setPosition(lvRole.getPosition());


        return property;
    }

    public Property setPropertyGender(Property property) {

        return null;
    }

    public static void main(String[] args) {
        Random rand = new Random();
        int a= rand.nextInt(10);
        System.out.println("args = [" + a + "]");
    }

}
