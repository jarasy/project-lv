package com.jarasy.lv.api.mapper;

import com.jarasy.lv.api.domain.po.LvGoods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface LvGoodsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LvGoods record);

    int insertSelective(LvGoods record);

    LvGoods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LvGoods record);

    int updateByPrimaryKey(LvGoods record);

    /*自定义*/
    List<LvGoods> selectByDrop(@Param("drop") Integer drop);

}