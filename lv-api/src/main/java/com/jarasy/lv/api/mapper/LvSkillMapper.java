package com.jarasy.lv.api.mapper;

import com.jarasy.lv.api.domain.po.LvSkill;

public interface LvSkillMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LvSkill record);

    int insertSelective(LvSkill record);

    LvSkill selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LvSkill record);

    int updateByPrimaryKey(LvSkill record);
}