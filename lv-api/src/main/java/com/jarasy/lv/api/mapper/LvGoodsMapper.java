package com.jarasy.lv.api.mapper;

import com.jarasy.lv.api.domain.po.LvGoods;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LvGoodsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LvGoods record);

    int insertSelective(LvGoods record);

    LvGoods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LvGoods record);

    int updateByPrimaryKey(LvGoods record);
}