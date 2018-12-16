package com.cyyz.spt.springbase.util;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;


import com.cyyz.spt.springbase.domain.UserVO;
import com.jarasy.lv.common.exception.*;
import com.jarasy.lv.common.http.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by admin on 2017/6/22.
 */
public class BaseController extends WebMvcConfigurerAdapter {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        // 2 添加fastjson 的配置信息 比如 是否要格式化 返回的json数据
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullListAsEmpty,SerializerFeature.WriteDateUseDateFormat);
        fastConverter.setFastJsonConfig(fastJsonConfig);
        converters.add(fastConverter);
    }
    protected void processError(Result resultMap, Exception e){

        if(e instanceof ParamException) {
            // 处理公共异常
            ParamException pe = (ParamException) e;
            resultMap.setCode(pe.getCode());
            resultMap.setMsg(pe.getMsg());
        }else if(e instanceof DataErrorException){
            DataErrorException de = (DataErrorException) e;
            resultMap.setCode(de.getCode());
            resultMap.setMsg(de.getMsg());
        }else if(e instanceof HttpInvokeException){
            HttpInvokeException de = (HttpInvokeException) e;
            resultMap.setCode(de.getCode());
            resultMap.setMsg(de.getMsg());
        }else if(e instanceof AuthException){
            AuthException de = (AuthException) e;
            resultMap.setCode(de.getCode());
            resultMap.setMsg(de.getMsg());
        }else if(e instanceof ServicePromptException){
            ServicePromptException spe = (ServicePromptException) e;
            resultMap.setCode(spe.getCode());
            resultMap.setMsg(spe.getMsg());
        }else if(e instanceof ServiceException){
            ServiceException se  = (ServiceException) e;
            resultMap.setCode(se.getCode());
            resultMap.setMsg(se.getMsg());
        }else{
            String errorMsg = e.getMessage();
            if(e!= null && e.getMessage() != null){
                errorMsg = e.getMessage();
            }else if(e!= null && e.getCause() != null){
                errorMsg = e.getCause().getMessage();
            }else if(e!= null){
                errorMsg = e.toString();
            }
            logger.error(errorMsg);
            resultMap.setCode(ErrorCode.UNKONW_ERROR);
            resultMap.setMsg(errorMsg);
        }
        logger.error("",e);
    }

    public Map<String,Object> convertRequst(Map<String,Object> attrs){
        if (null!=attrs && !attrs.isEmpty()){
            Map<String,Object> map = new HashMap<>();
            for (String key : attrs.keySet()){
                if (!StringUtils.isEmpty(key)){
                    Object value = attrs.get(key);
                    Matcher m = Pattern.compile("[A-Z]").matcher(key);
                    while (m.find()){
                        String str = m.group();
                        key = key.replace(str,"_"+str.toLowerCase());
                    }
                    map.put(key,value);
                }
                return map;
            }
        }
        return null;
    }

    public String getRemoteHost(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

//    public Object getUserInfo(HttpServletRequest request){
//       return request.getSession().getAttribute(ServiceConstants.GET_USER_INFO);
//    }

}
