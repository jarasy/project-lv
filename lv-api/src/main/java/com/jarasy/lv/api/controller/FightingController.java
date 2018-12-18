package com.jarasy.lv.api.controller;

import com.alibaba.fastjson.JSONObject;
import com.cyyz.spt.springbase.util.BaseController;
import com.jarasy.lv.api.adapter.WeixinAdapter;
import com.jarasy.lv.api.domain.po.LvRole;
import com.jarasy.lv.api.domain.po.LvWxUser;
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




    /**
     * 获取
     * @param jsonObject
     * @return
     */
    @RequestMapping(value="/getRole",method= RequestMethod.POST, produces = "application/json;charset=utf-8")
    public DataResult getRole(@RequestBody JSONObject jsonObject){
        DataResult result = DataResult.init();
        try{
            LvRole lvRole = lvRoleService.selectByOpenid(jsonObject.getString("openid"));
            result.setData(null==lvRole?0:1);
        }catch(Exception e){
            this.processError(result,e);
        }
        return result;
    }



}
