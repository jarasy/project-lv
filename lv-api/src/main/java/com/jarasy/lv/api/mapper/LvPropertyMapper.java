package com.jarasy.lv.api.mapper;

import com.jarasy.lv.api.domain.po.LvProperty;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LvPropertyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LvProperty record);

    int insertSelective(LvProperty record);

    LvProperty selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LvProperty record);

    int updateByPrimaryKey(LvProperty record);

    /*自定义*/
    List<LvProperty> selectByTypeAndPosition(@Param("type")Integer type, @Param("position")Integer position);
}