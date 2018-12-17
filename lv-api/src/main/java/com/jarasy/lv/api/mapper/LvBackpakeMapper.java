package com.jarasy.lv.api.mapper;

import com.jarasy.lv.api.domain.po.LvBackpake;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface LvBackpakeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LvBackpake record);

    int insertSelective(LvBackpake record);

    LvBackpake selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LvBackpake record);

    int updateByPrimaryKey(LvBackpake record);

    List<Map<String,String>> selectGoodsByType(@Param("roleId") Integer roleId, @Param("type") Integer type);

    List<Map<String,String>> selectZbByType(@Param("roleId") Integer roleId, @Param("type") Integer type);

    int dqGoodsForCount(@Param("id") Integer id, @Param("count") Integer count);
}