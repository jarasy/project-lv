package com.cyyz.spt.springbase.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.jarasy.lv.common.exception.HttpInvokeException;
import com.jarasy.lv.common.http.PageResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 2017/7/21.
 */
public abstract class RestHttpCilent {
    private static Logger logger = LoggerFactory.getLogger(RestHttpCilent.class);

    public static String post(RestTemplate restTemplate, String url, String json) throws HttpInvokeException {
        return post(restTemplate, url, json, "application/json; charset=utf-8", "utf-8",true);
    }

    public static String post(RestTemplate restTemplate, String url, String json,boolean isPrint) throws HttpInvokeException {
        return post(restTemplate, url, json, "application/json; charset=utf-8", "utf-8",isPrint);
    }

    public static String post(RestTemplate restTemplate, String url, String params, String contentType, String charset) throws HttpInvokeException {
        return post(restTemplate,url,params,contentType,charset,true);
    }

    /**
     *
     * @param restTemplate
     * @param url
     * @param params
     * @param contentType
     * @param charset
     * @param isPrint
     * @return
     * @throws HttpInvokeException
     */
    public static String post(RestTemplate restTemplate, String url, String params, String contentType, String charset,boolean isPrint) throws HttpInvokeException {
        logger.info("url:" + url);
        if(isPrint) {
            logger.info("params:" + params);
        }
        if (StringUtils.isEmpty(params)) {
            logger.error("接口服务数据异常：" + url);
            throw new HttpInvokeException("网络异常");
        }
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept-Charset", charset);
        headers.set("Content-type", contentType);  //header的规定
        HttpEntity<String> formEntity = new HttpEntity(params, headers);
        String result;
        try {
            result = restTemplate.postForObject(url, formEntity, String.class);
        } catch (Exception e) {
            logger.error("接口服务网络异常：" + url, e);
            throw new HttpInvokeException("网络异常");
        }
        if (StringUtils.isEmpty(result)) {
            logger.error("接口服务数据异常：" + url);
            throw new HttpInvokeException("网络数据异常");
        }
        if(isPrint) {
            logger.info("result:" + result);
        }
        return result;
    }

    /**
     *
     * @param restTemplate 带头部信息
     * @param url
     * @param params
     * @param isPrint
     * @return
     * @throws HttpInvokeException
     */
    public static String postWithHeaders(RestTemplate restTemplate, String url, String params, HttpHeaders headers, boolean isPrint) throws HttpInvokeException {
        logger.info("url:" + url);
        if(isPrint) {
            logger.info("params:" + params);
            logger.info("headers:" + headers);
        }
        if (StringUtils.isEmpty(params)) {
            logger.error("接口服务数据异常：" + url);
            throw new HttpInvokeException("网络异常");
        }
        HttpEntity<String> formEntity = new HttpEntity(params, headers);
        String result;
        try {
            result = restTemplate.postForObject(url, formEntity, String.class);
        } catch (Exception e) {
            logger.error("接口服务网络异常：" + url, e);
            throw new HttpInvokeException("网络异常");
        }
        if (StringUtils.isEmpty(result)) {
            logger.error("接口服务数据异常：" + url);
            throw new HttpInvokeException("网络数据异常");
        }
        if(isPrint) {
            logger.info("result:" + result);
        }
        return result;
    }


    public static PageResult postForResult(RestTemplate restTemplate, String url, String json) throws HttpInvokeException {
        try {
            return JSONObject.parseObject(post(restTemplate, url, json), PageResult.class);
        } catch (JSONException e) {
            logger.error("接口服务数据异常：" + url, e);
            throw new HttpInvokeException("网络数据异常");
        }
    }

    public static String put(RestTemplate restTemplate, String url, String json) throws HttpInvokeException {

        if (StringUtils.isEmpty(json)) {
            logger.error("接口服务数据异常：" + url);
            throw new HttpInvokeException("网络数据异常");
        }
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept-Charset", "utf-8");
        headers.set("Content-type", "application/json; charset=utf-8");  //header的规定
        HttpEntity<String> formEntity = new HttpEntity(json, headers);

        String result;
        try {
            ResponseEntity<String> entity = restTemplate.exchange(url, HttpMethod.PUT, formEntity, String.class);
            result = entity.getBody();
        } catch (Exception e) {
            logger.error("接口服务网络异常：" + url, e);
            throw new HttpInvokeException("网络异常");
        }
        if (StringUtils.isEmpty(result)) {
            logger.error("接口服务数据异常：" + url);
            throw new HttpInvokeException("网络数据异常");
        }
        return result;
    }

    public static PageResult putForResult(RestTemplate restTemplate, String url, String json) throws HttpInvokeException {
        try {
            return JSONObject.parseObject(put(restTemplate, url, json), PageResult.class);
        } catch (JSONException e) {
            logger.error("接口服务数据异常：" + url, e);
            throw new HttpInvokeException("网络数据异常");
        }
    }

    public static String get(RestTemplate restTemplate, String url) throws HttpInvokeException {
        String result;
        try {
            logger.info("get request url:{}", url);
            result = restTemplate.getForObject(url, String.class);
        } catch (Exception e) {
            logger.error("接口服务网络异常：" + url, e);
            throw new HttpInvokeException("网络异常");
        }
        if (StringUtils.isEmpty(result)) {
            logger.error("接口服务数据异常：" + url);
            throw new HttpInvokeException("网络数据异常");
        }
        return result;
    }

    public static PageResult getForResult(RestTemplate restTemplate, String url) throws HttpInvokeException {
        try {
            return JSONObject.parseObject(get(restTemplate, url), PageResult.class);
        } catch (JSONException e) {
            logger.error("接口服务数据异常：" + url, e);
            throw new HttpInvokeException("网络数据异常");
        }
    }

    public static String delete(RestTemplate restTemplate, String url) {
        String result;
        try {
            ResponseEntity<String> entity = restTemplate.exchange(url, HttpMethod.DELETE, null, String.class);
            result = entity.getBody();
        } catch (Exception e) {
            logger.error("接口服务网络异常：" + url, e);
            throw new HttpInvokeException("网络异常");
        }
        if (StringUtils.isEmpty(result)) {
            logger.error("接口服务数据异常：" + url);
            throw new HttpInvokeException("网络数据异常");
        }
        return result;
    }

    public static PageResult deleteForResult(RestTemplate restTemplate, String url) {
        try {
            return JSONObject.parseObject(delete(restTemplate, url), PageResult.class);
        } catch (JSONException e) {
            logger.error("接口服务数据异常：" + url, e);
            throw new HttpInvokeException("网络数据异常");
        }
    }

    public static String getData(RestTemplate restTemplate, String url) throws Exception {
        String result = get(restTemplate, url);
        Map<String, String> map = JSON.parseObject(result, new TypeReference<Map<String, String>>() {
        });
        int resultCode = Integer.parseInt(map.get("code"));
        if (resultCode == 0) {
            return map.get("data");
        } else {
            logger.error("请求数据失败," + map.get("msg"));
            return null;
        }
    }

    public static PageResult requestForResult(RestTemplate restTemplate, HttpMethod method, String url, String json) {
        return requestForObject(restTemplate, method, url, json, PageResult.class);
    }

    public static <T> T requestForObject(RestTemplate restTemplate, HttpMethod method, String url, String json, Class<T> clazz) {
        String result = request(restTemplate, method, url, json);
        if (!StringUtils.isEmpty(result)) {
            return JSONObject.parseObject(result, clazz);
        }
        return null;
    }

    public static String request(RestTemplate restTemplate, HttpMethod method, String url, String json) {
        switch (method) {
            case GET:
                return get(restTemplate, url);
            case PUT:
                return put(restTemplate, url, json);
            case POST:
                return post(restTemplate, url, json);
            case DELETE:
                return delete(restTemplate, url);
            default:
                return null;
        }
    }
    
    public static String postFile(RestTemplate restTemplate, String url, Resource resource  ) throws HttpInvokeException {
        logger.info("url:" + url);
        if (resource == null) {
            logger.error("接口服务数据异常：" + url);
            throw new HttpInvokeException("网络异常");
        }
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept-Charset", "utf-8");
        headers.set("Content-type", "multipart/form-data");
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
        param.add("file", resource);  
        String result;
        try {
            result = restTemplate.postForObject(url, param, String.class);
        } catch (Exception e) {
            logger.error("接口服务网络异常：" + url, e);
            throw new HttpInvokeException("网络异常");
        }
        if (StringUtils.isEmpty(result)) {
            logger.error("接口服务数据异常：" + url);
            throw new HttpInvokeException("网络数据异常");
        }
        logger.info("result:" + result);
        return result;
    }

    public static String postHeader(RestTemplate restTemplate, String url, String params,HttpHeaders headers,boolean isPrint) throws HttpInvokeException {
        //logger.info("url:" + url);
        if(isPrint) {
            logger.info("params:" + params);
        }
        if (StringUtils.isEmpty(params)) {
            logger.error("接口服务数据异常：" + url);
            throw new HttpInvokeException("网络异常");
        }
        HttpEntity<String> formEntity = new HttpEntity(params, headers);
        String result;
        try {
            result = restTemplate.postForObject(url, formEntity, String.class);
        } catch (Exception e) {
            logger.error("接口服务网络异常：" + url, e);
            throw new HttpInvokeException("网络异常");
        }
        if (StringUtils.isEmpty(result)) {
            logger.error("接口服务数据异常：" + url);
            throw new HttpInvokeException("网络数据异常");
        }
        if(isPrint) {
            logger.info("result:" + result);
        }
        return result;
    }
}
