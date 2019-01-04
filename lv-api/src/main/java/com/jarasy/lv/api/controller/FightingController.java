package com.jarasy.lv.api.controller;

import com.alibaba.fastjson.JSONObject;
import com.cyyz.spt.springbase.util.BaseController;
import com.jarasy.lv.api.adapter.WeixinAdapter;
import com.jarasy.lv.api.domain.po.LvRole;
import com.jarasy.lv.api.domain.po.LvWxUser;
import com.jarasy.lv.api.mapper.LvFriendshipMapper;
import com.jarasy.lv.api.service.FightingService;
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
@RequestMapping("/fighting")
public class FightingController extends BaseController {


    @Autowired
    private LvWxUserService lvWxUserService;
    @Autowired
    private LvRoleService lvRoleService;
    @Autowired
    private FightingService fightingService;




    /**
     * 获取怪物信息
     * @param jsonObject
     * @return
     */
    @RequestMapping(value="/getMonsters",method= RequestMethod.POST, produces = "application/json;charset=utf-8")
    public DataResult getMonsters(@RequestBody JSONObject jsonObject){
        DataResult result = DataResult.init();
        try{
            result.setData(fightingService.getMonsters(jsonObject.getString("openId"),jsonObject.getInteger("mapId")));
        }catch(Exception e){
            this.processError(result,e);
        }
        return result;
    }

    /**
     * 获取玩家信息
     * @param jsonObject
     * @return
     */
    @RequestMapping(value="/getPlayers",method= RequestMethod.POST, produces = "application/json;charset=utf-8")
    public DataResult getPlayers(@RequestBody JSONObject jsonObject){
        DataResult result = DataResult.init();
        try{
            result.setData(fightingService.getPlayers(jsonObject.getString("openId")));
        }catch(Exception e){
            this.processError(result,e);
        }
        return result;
    }

    /**
     * 获取奖励
     * @param jsonObject
     * @return
     */
    @RequestMapping(value="/getAwards",method= RequestMethod.POST, produces = "application/json;charset=utf-8")
    public DataResult getAwards(@RequestBody JSONObject jsonObject){
        DataResult result = DataResult.init();
        try{
            result.setData(fightingService.getAwards(jsonObject.getString("id")));
        }catch(Exception e){
            this.processError(result,e);
        }
        return result;
    }

    /**
     * 获取boss凭证
     * @param jsonObject
     * @return
     */
    @RequestMapping(value="/getFToken",method= RequestMethod.POST, produces = "application/json;charset=utf-8")
    public DataResult getFToken(@RequestBody JSONObject jsonObject){
        DataResult result = DataResult.init();
        try{
            result.setData(fightingService.getFToken(jsonObject.getString("openId"),jsonObject.getInteger("mapId")));
        }catch(Exception e){
            this.processError(result,e);
        }
        return result;
    }

    /**
     * 获取boss
     * @param jsonObject
     * @return
     */
    @RequestMapping(value="/getBoss",method= RequestMethod.POST, produces = "application/json;charset=utf-8")
    public DataResult getBoss(@RequestBody JSONObject jsonObject){
        DataResult result = DataResult.init();
        try{
            result.setData(fightingService.getBoss(jsonObject.getString("fToken")));
        }catch(Exception e){
            this.processError(result,e);
        }
        return result;
    }


}
