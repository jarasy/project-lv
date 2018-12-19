package com.jarasy.lv.api.mapper;

import com.jarasy.lv.api.domain.po.LvFriendship;

public interface LvFriendshipMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LvFriendship record);

    int insertSelective(LvFriendship record);

    LvFriendship selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LvFriendship record);

    int updateByPrimaryKey(LvFriendship record);
}