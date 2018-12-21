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

import java.util.ArrayList;
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
    public List<LvGoods> selectGoodsByDrop(Integer drop)  throws Exception {
        List<LvGoods> rs=new ArrayList<>();
        String lvGoods = (String) redisService.getString(HashKeyPrefix.GOODS_INFO_DROP + drop);
        if (null == lvGoods) {
            rs = lvGoodsMapper.selectByDrop(drop);
            if (null != rs) {
                StringBuffer lgs=new StringBuffer();
                for (LvGoods l:rs){
                    lgs.append(l.getId()+"_"+l.getType()+",");
                }
                // 更新缓存
                redisService.setString(HashKeyPrefix.GOODS_INFO_DROP + drop, lgs.toString(), TimeUnit.DAYS.toSeconds(30));
            } else {
                throw new DataErrorException("lvGoods drop 异常 " + drop);
            }
            return rs;
        }

        for (String gs:lvGoods.split(",")) {
            LvGoods e=new LvGoods();
            String[] split = gs.split("_");
            e.setId(Integer.valueOf(split[0]));
            e.setType(Integer.valueOf(split[1]));
            rs.add(e);
        }
        return rs;
    }

}
