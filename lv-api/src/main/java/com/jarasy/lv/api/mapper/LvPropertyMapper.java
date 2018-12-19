package com.jarasy.lv.api.mapper;

import com.jarasy.lv.api.domain.po.LvProperty;

public interface LvPropertyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LvProperty record);

    int insertSelective(LvProperty record);

    LvProperty selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LvProperty record);

    int updateByPrimaryKey(LvProperty record);
}