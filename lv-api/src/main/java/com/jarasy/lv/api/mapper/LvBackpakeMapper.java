package com.jarasy.lv.api.mapper;

import com.jarasy.lv.api.domain.po.LvBackpake;
import org.apache.ibatis.annotations.Mapper;

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

    List<Map<String,String>> selectGoodsByType(int roleId,int type);

    List<Map<String,String>> selectZbByType(int roleId,int type);
}