package com.jarasy.lv.api.service;

import com.alibaba.fastjson.JSONObject;
import com.jarasy.lv.api.domain.po.LvGoods;
import com.jarasy.lv.api.domain.po.LvRole;
import com.jarasy.lv.api.domain.vo.Property;

import java.util.List;
import java.util.Map;

/**
 * Created by wjh on 2018/11/20.
 */
public interface BackpakeService {
    List<Map<String, String>> selectGoodsByType(JSONObject jSONObject);
    LvGoods selectGoodsById(JSONObject jSONObject);
}
