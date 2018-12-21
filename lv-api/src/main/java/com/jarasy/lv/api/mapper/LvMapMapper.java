package com.jarasy.lv.api.mapper;

import com.jarasy.lv.api.domain.po.LvMap;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LvMapMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LvMap record);

    int insertSelective(LvMap record);

    LvMap selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LvMap record);

    int updateByPrimaryKey(LvMap record);

    /*自定义*/
    List<LvMap> selectByType(@Param("type") Integer type);
}