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
import com.jarasy.lv.common.exception.DataErrorException;
import com.jarasy.lv.redis.HashKeyPrefix;
import com.jarasy.lv.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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

    @Autowired
    private RedisService redisService;

    @Override
    public List<Map<String, String>> selectGoodsByType(JSONObject jSONObject)  throws Exception {
        return lvBackpakeMapper.selectGoodsByType(jSONObject.getInteger("roleId"), jSONObject.getInteger("type"));
    }

    @Override
    public LvGoods selectGoodsById(Integer id)  throws Exception {
        LvGoods lvGoods = (LvGoods) redisService.hgetAllForObject(HashKeyPrefix.GOODS_INFO + id, LvGoods.class);
        if (null == lvGoods) {
            lvGoods = lvGoodsMapper.selectByPrimaryKey(id);
            if (null != lvGoods) {
                // 更新缓存
                redisService.hsetForObject(HashKeyPrefix.GOODS_INFO + id, lvGoods, TimeUnit.DAYS.toSeconds(30));
            } else {
                throw new DataErrorException("lvGoods id 异常 " + id);
            }
            return lvGoods;
        }
        return lvGoods;
    }

    @Override
    public List<Map<String, String>> selectZbByType(JSONObject jSONObject)  throws Exception {
        return lvBackpakeMapper.selectZbByType(jSONObject.getInteger("roleId"), jSONObject.getInteger("type"));
    }

    @Override
    public Property zbGoods(JSONObject jSONObject)  throws Exception {
        lvBackpakeMapper.outZbByTypeInRole(jSONObject.getInteger("roleId"),jSONObject.getInteger("type"),jSONObject.getInteger("wz"));
        LvBackpake lb=new LvBackpake();
        lb.setId(jSONObject.getInteger("id"));
        lb.setEquipped(1);
        lvBackpakeMapper.updateByPrimaryKeySelective(lb);
        return null;
    }

    @Override
    public Property outZbGoods(JSONObject jSONObject) throws Exception {
        LvBackpake lb=new LvBackpake();
        lb.setId(jSONObject.getInteger("id"));
        lb.setEquipped(0);
        lvBackpakeMapper.updateByPrimaryKeySelective(lb);
        return null;
    }

    @Override
    public void dqGoodsForAll(JSONObject jSONObject) throws Exception {
        lvBackpakeMapper.deleteByPrimaryKey(jSONObject.getInteger("id"));
    }

    @Override
    public void dqGoodsForCount(JSONObject jSONObject) throws Exception {
        lvBackpakeMapper.dqGoodsForCount(jSONObject.getInteger("id"),jSONObject.getInteger("count"));
    }

    @Override
    public List<Map<String,String>> selectGoodsByDrop(Integer drop)  throws Exception {
        List<Map<String,String>> lvGoods = (List<Map<String,String>>) redisService.hgetAllForObject(HashKeyPrefix.GOODS_INFO_DROP + drop, List.class);
        if (null == lvGoods) {
            lvGoods = lvGoodsMapper.selectByDrop(drop);
            if (null != lvGoods) {
                // 更新缓存
                redisService.hsetForObject(HashKeyPrefix.GOODS_INFO_DROP + drop, lvGoods, TimeUnit.DAYS.toSeconds(30));
            } else {
                throw new DataErrorException("lvGoods drop 异常 " + drop);
            }
            return lvGoods;
        }
        return lvGoods;
    }

}
