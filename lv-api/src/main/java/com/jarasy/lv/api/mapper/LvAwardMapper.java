package com.jarasy.lv.api.mapper;

import com.jarasy.lv.api.domain.po.LvAward;

public interface LvAwardMapper {
    int deleteByPrimaryKey(String id);

    int insert(LvAward record);

    int insertSelective(LvAward record);

    LvAward selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LvAward record);

    int updateByPrimaryKey(LvAward record);
}