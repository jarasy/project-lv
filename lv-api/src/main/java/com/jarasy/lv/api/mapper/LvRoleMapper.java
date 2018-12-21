package com.jarasy.lv.api.mapper;

import com.jarasy.lv.api.domain.po.LvRole;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LvRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LvRole record);

    int insertSelective(LvRole record);

    LvRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LvRole record);

    int updateByPrimaryKey(LvRole record);

    /*自定义*/
    LvRole selectByOpenid(String openid);
}