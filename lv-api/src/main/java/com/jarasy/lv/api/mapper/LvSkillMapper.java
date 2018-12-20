package com.jarasy.lv.api.mapper;

import com.jarasy.lv.api.domain.po.LvSkill;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LvSkillMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LvSkill record);

    int insertSelective(LvSkill record);

    LvSkill selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LvSkill record);

    int updateByPrimaryKey(LvSkill record);

    /*自定义*/
    List<LvSkill> selectByType(@Param("type") Integer type);
}