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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

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

    @Override
    public void insert(JSONObject jSONObject) {
        LvRole vvRole = new LvRole();
        vvRole.setOpenid(jSONObject.getString("openid"));
        vvRole.setName(jSONObject.getString("name"));
        vvRole.setGender(jSONObject.getInteger("gender"));
        vvRole.setRank(1);
        vvRole.setLevel(1);
        vvRole.setProfession(jSONObject.getInteger("profession"));
        vvRole.setHs(100);
        vvRole.setHy(0);
        vvRole.setExp(0L);
        vvRole.setPosition(0);
        vvRole.setCreateTime(new Date());
        lvRoleMapper.insertSelective(vvRole);
    }

    @Override
    public LvRole selectByOpenid(String openid) {
        LvRole lvRole = lvRoleMapper.selectByOpenid(openid);
        return lvRole;
    }

    @Override
    public Property getRoleProperty(String openid) {
        LvRole lvRole = lvRoleMapper.selectByOpenid(openid);
        LvProfession lvProfession = lvProfessionMapper.selectByPrimaryKey(lvRole.getProfession());
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
        int a= (int) Math.pow(5,2);
        System.out.println("args = [" + a + "]");
    }

}
