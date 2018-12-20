package com.jarasy.lv.api.controller;

import com.alibaba.fastjson.JSONObject;
import com.cyyz.spt.springbase.util.BaseController;
import com.jarasy.lv.api.adapter.WeixinAdapter;
import com.jarasy.lv.api.domain.po.LvRole;
import com.jarasy.lv.api.domain.po.LvWxUser;
import com.jarasy.lv.api.service.BackpakeService;
import com.jarasy.lv.api.service.LvRoleService;
import com.jarasy.lv.api.service.LvWxUserService;
import com.jarasy.lv.common.http.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cjq on 2018/5/10.
 */
@RestController
@RequestMapping("/backpake")
public class BackPakeController extends BaseController {

    @Autowired
    private BackpakeService backpakeService;



    /**
     * 获取物品by类型
     * @param jsonObject
     * @return
     */
    @RequestMapping(value="/selectGoodsByType",method= RequestMethod.POST, produces = "application/json;charset=utf-8")
    public DataResult selectGoodsByType(@RequestBody JSONObject jsonObject){
        DataResult result = DataResult.init();
        try{
            result.setData( backpakeService.selectGoodsByType(jsonObject));
        }catch(Exception e){
            this.processError(result,e);
        }
        return result;
    }

    /**
     * 获取物品byID
     * @param jsonObject
     * @return
     */
    @RequestMapping(value="/selectGoodsById",method= RequestMethod.POST, produces = "application/json;charset=utf-8")
    public DataResult selectGoodsById(@RequestBody JSONObject jsonObject){
        DataResult result = DataResult.init();
        try{
            result.setData(backpakeService.selectGoodsById(jsonObject.getInteger("id")));
        }catch(Exception e){
            this.processError(result,e);
        }
        return result;
    }

    /**
     * 获取装备物品byType
     * @param jsonObject
     * @return
     */
    @RequestMapping(value="/selectZbByType",method= RequestMethod.POST, produces = "application/json;charset=utf-8")
    public DataResult selectZbByType(@RequestBody JSONObject jsonObject){
        DataResult result = DataResult.init();
        try{
            result.setData( backpakeService.selectZbByType(jsonObject));
        }catch(Exception e){
            this.processError(result,e);
        }
        return result;
    }

    /**
     * 装备物品
     * @param jsonObject
     * @return
     */
    @RequestMapping(value="/zbGoods",method= RequestMethod.POST, produces = "application/json;charset=utf-8")
    public DataResult zbGoods(@RequestBody JSONObject jsonObject){
        DataResult result = DataResult.init();
        try{
            result.setData( backpakeService.zbGoods(jsonObject));
        }catch(Exception e){
            this.processError(result,e);
        }
        return result;
    }
    /**
     * 解除装备物品
     * @param jsonObject
     * @return
     */
    @RequestMapping(value="/outZbGoods",method= RequestMethod.POST, produces = "application/json;charset=utf-8")
    public DataResult outZbGoods(@RequestBody JSONObject jsonObject){
        DataResult result = DataResult.init();
        try{
            result.setData( backpakeService.outZbGoods(jsonObject));
        }catch(Exception e){
            this.processError(result,e);
        }
        return result;
    }

    /**
     * 丢弃全部数量物品
     * @param jsonObject
     * @return
     */
    @RequestMapping(value="/dqGoodsForAll",method= RequestMethod.POST, produces = "application/json;charset=utf-8")
    public DataResult dqGoodsForAll(@RequestBody JSONObject jsonObject){
        DataResult result = DataResult.init();
        try{
            backpakeService.dqGoodsForAll(jsonObject);
        }catch(Exception e){
            this.processError(result,e);
        }
        return result;
    }

    /**
     * 丢弃物品(数量)
     * @param jsonObject
     * @return
     */
    @RequestMapping(value="/dqGoodsForCount",method= RequestMethod.POST, produces = "application/json;charset=utf-8")
    public DataResult dqGoodsForCount(@RequestBody JSONObject jsonObject){
        DataResult result = DataResult.init();
        try{
            backpakeService.dqGoodsForCount(jsonObject);
        }catch(Exception e){
            this.processError(result,e);
        }
        return result;
    }


}
