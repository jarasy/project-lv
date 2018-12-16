package com.jarasy.lv.api.adapter;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cyyz.spt.springbase.util.RestHttpCilent;
import com.jarasy.lv.common.exception.HttpResultErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Administrator on 2018/4/16 0016.
 */
@Component
public class WeixinAdapter {

    private Logger logger = LoggerFactory.getLogger(WeixinAdapter.class);
    @Autowired
    private RestTemplate restTemplate;


    @Value("${api.weixin.server}")
    private String WEIXIN_SERVER;
    @Value("${api.weixin.appid}")
    private String APPID;
    @Value("${api.weixin.secret}")
    private String SECRET;



    private static final String JSCODE2SESSION_URL = "/sns/jscode2session";



    public JSONObject jscode2session(String code) {
        //code="061bWY2X1s8j5U0jGHZW1aS23X1bWY2J";
        String result = RestHttpCilent.get(restTemplate, WEIXIN_SERVER + JSCODE2SESSION_URL + "?appid="+APPID+"&secret="+SECRET+"&js_code="+code+"&grant_type=authorization_code");
        JSONObject dataResult = JSONObject.parseObject(result);
        if (!StringUtils.isEmpty(dataResult.getString("openid"))) {
            return dataResult;
        } else {
            throw new HttpResultErrorException("errorMsg: " + dataResult.getString("errmsg"));
        }
    }

}