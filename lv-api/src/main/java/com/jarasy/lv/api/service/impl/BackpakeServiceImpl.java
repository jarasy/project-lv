package com.jarasy.lv.api.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.jarasy.lv.api.domain.po.LvBackpake;
import com.jarasy.lv.api.domain.po.LvGoods;
import com.jarasy.lv.api.domain.po.LvProfession;
import com.jarasy.lv.api.domain.po.LvRole;
import com.jarasy.lv.api.domain.vo.Property;
import com.jarasy.lv.api.mapper.LvBackpakeMapper;
import com.jarasy.lv.api.mapper.LvGoodsMapper;
import com.jarasy.lv.api.mapper.LvProfessionMapper;
import com.jarasy.lv.api.mapper.LvRoleMapper;
import com.jarasy.lv.api.service.BackpakeService;
import com.jarasy.lv.api.service.LvRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by wjh on 2018/11/20.
 */
@Service
public class BackpakeServiceImpl implements BackpakeService {

    @Autowired
    private LvBackpakeMapper lvBackpakeMapper;

    @Autowired
    private LvGoodsMapper lvGoodsMapper;

    @Autowired
    private LvRoleService lvRoleService;

    @Override
    public List<Map<String, String>> selectGoodsByType(JSONObject jSONObject)  throws Exception {
        return lvBackpakeMapper.selectGoodsByType(jSONObject.getInteger("roleId"), jSONObject.getInteger("type"));
    }

    @Override
    public LvGoods selectGoodsById(JSONObject jSONObject)  throws Exception {
        return lvGoodsMapper.selectByPrimaryKey(jSONObject.getInteger("id"));
    }

    @Override
    public List<Map<String, String>> selectZbByType(JSONObject jSONObject)  throws Exception {
        return lvBackpakeMapper.selectZbByType(jSONObject.getInteger("roleId"), jSONObject.getInteger("type"));
    }

    @Override
    public Property zbGoods(JSONObject jSONObject)  throws Exception {
        LvBackpake lb=new LvBackpake();
        lb.setId(jSONObject.getInteger("id"));
        lb.setEquipped(1);
        lvBackpakeMapper.updateByPrimaryKeySelective(lb);
        return lvRoleService.getRoleProperty(jSONObject.getString("openId"));
    }

    @Override
    public Property outZbGoods(JSONObject jSONObject) throws Exception {
        LvBackpake lb=new LvBackpake();
        lb.setId(jSONObject.getInteger("id"));
        lb.setEquipped(0);
        lvBackpakeMapper.updateByPrimaryKeySelective(lb);
        return lvRoleService.getRoleProperty(jSONObject.getString("openId"));
    }

    @Override
    public void dqGoodsForAll(JSONObject jSONObject) throws Exception {
        lvBackpakeMapper.deleteByPrimaryKey(jSONObject.getInteger("id"));
    }

    @Override
    public void dqGoodsForCount(JSONObject jSONObject) throws Exception {
        lvBackpakeMapper.dqGoodsForCount(jSONObject.getInteger("id"),jSONObject.getInteger("count"));
    }


}
