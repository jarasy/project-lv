package com.jarasy.lv.api.mapper;

import com.jarasy.lv.api.domain.po.LvWxUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LvWxUserMapper {
    int deleteByPrimaryKey(String openid);

    int insert(LvWxUser record);

    int insertSelective(LvWxUser record);

    LvWxUser selectByPrimaryKey(String openid);

    int updateByPrimaryKeySelective(LvWxUser record);

    int updateByPrimaryKey(LvWxUser record);
}