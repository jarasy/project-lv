package com.jarasy.lv.api.controller;

import com.alibaba.fastjson.JSONObject;
import com.cyyz.spt.springbase.util.BaseController;
import com.jarasy.lv.api.adapter.WeixinAdapter;
import com.jarasy.lv.api.domain.po.LvRole;
import com.jarasy.lv.api.domain.po.LvWxUser;
import com.jarasy.lv.api.domain.vo.Property;
import com.jarasy.lv.api.service.LvRoleService;
import com.jarasy.lv.api.service.LvWxUserService;
import com.jarasy.lv.common.http.DataResult;
import com.jarasy.lv.common.utils.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.URL;

/**
 * Created by cjq on 2018/5/10.
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private WeixinAdapter weixinAdapter;

    @Autowired
    private LvWxUserService lvWxUserService;
    @Autowired
    private LvRoleService lvRoleService;
    @Value("${picture.upload.path}")
    private String PICTURE_UPLOAD_PATH;


    /**
     * 获取openid
     * @param jsonObject
     * @return
     */
    @RequestMapping(value="/login_wx",method= RequestMethod.POST, produces = "application/json;charset=utf-8")
    public DataResult login_wx(@RequestBody JSONObject jsonObject){
        DataResult result = DataResult.init();
        try{
            JSONObject jsonObject1 = weixinAdapter.jscode2session(jsonObject.getString("code"));
            result.setData(jsonObject1.getString("openid"));
        }catch(Exception e){
            this.processError(result,e);
        }
        return result;
    }

    /**
     * 检查是否微信注册
     * @param jsonObject
     * @return
     */
    @RequestMapping(value="/checkUser",method= RequestMethod.POST, produces = "application/json;charset=utf-8")
    public DataResult checkUser(@RequestBody JSONObject jsonObject){
        DataResult result = DataResult.init();
        try{
            LvWxUser lvWxUser = lvWxUserService.selectByOpenid(jsonObject.getString("openid"));
            if(null==lvWxUser){
                ImageUtil.saveImageByURL(jsonObject.getString("avatarUrl"),jsonObject.getString("openid")+".png",PICTURE_UPLOAD_PATH);
                lvWxUserService.insert(jsonObject);
            }
        }catch(Exception e){
            this.processError(result,e);
        }
        return result;
    }


    /**
     * 获取角色
     * @param jsonObject
     * @return
     */
    @RequestMapping(value="/getRole",method= RequestMethod.POST, produces = "application/json;charset=utf-8")
    public DataResult getRole(@RequestBody JSONObject jsonObject){
        DataResult result = DataResult.init();
        try{
            LvRole lvRole = lvRoleService.selectByOpenid(jsonObject.getString("openId"));
            result.setData(null==lvRole?0:1);
        }catch(Exception e){
            this.processError(result,e);
        }
        return result;
    }

    /**
     * 新增角色
     * @param jsonObject
     * @return
     */
    @RequestMapping(value="/addRole",method= RequestMethod.POST, produces = "application/json;charset=utf-8")
    public DataResult checkRole(@RequestBody JSONObject jsonObject){
        DataResult result = DataResult.init();
        try{
            lvRoleService.insert(jsonObject);
            result.setData(lvRoleService.getRoleProperty(jsonObject.getString("openId")));
        }catch(Exception e){
            this.processError(result,e);
        }
        return result;
    }

    /**
     * 获取角色属性
     * @param jsonObject
     * @return
     */
    @RequestMapping(value="/getRoleProperty",method= RequestMethod.POST, produces = "application/json;charset=utf-8")
    public DataResult getRoleProperty(@RequestBody JSONObject jsonObject){
        DataResult result = DataResult.init();
        try{
            result.setData(lvRoleService.getRoleProperty(jsonObject.getString("openId")));
        }catch(Exception e){
            this.processError(result,e);
        }
        return result;
    }

}
