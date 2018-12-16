package com.jarasy.lv.api.mapper;

import com.jarasy.lv.api.domain.po.LvProfession;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LvProfessionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LvProfession record);

    int insertSelective(LvProfession record);

    LvProfession selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LvProfession record);

    int updateByPrimaryKey(LvProfession record);
}