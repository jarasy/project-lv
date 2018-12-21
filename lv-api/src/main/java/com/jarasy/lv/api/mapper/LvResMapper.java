package com.jarasy.lv.api.mapper;

import com.jarasy.lv.api.domain.po.LvRes;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LvResMapper {
    int deleteByPrimaryKey(String openId);

    int insert(LvRes record);

    int insertSelective(LvRes record);

    LvRes selectByPrimaryKey(String openId);

    int updateByPrimaryKeySelective(LvRes record);

    int updateByPrimaryKey(LvRes record);

    /*自定义*/
    int updateFighting(String openId);
}