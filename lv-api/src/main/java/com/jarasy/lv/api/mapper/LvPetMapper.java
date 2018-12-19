package com.jarasy.lv.api.mapper;

import com.jarasy.lv.api.domain.po.LvPet;

public interface LvPetMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LvPet record);

    int insertSelective(LvPet record);

    LvPet selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LvPet record);

    int updateByPrimaryKey(LvPet record);
}