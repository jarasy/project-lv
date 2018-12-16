/*
package com.cyyz.spt.springbase.util;

import com.cyyz.spt.platform.common.constant.HttpHeader;
import com.cyyz.spt.platform.common.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.util.List;
import java.util.Map;

*/
/**
 * Created by wjh on 2018/10/15 0015.
 *//*

public class RequestInterceptor implements ClientHttpRequestInterceptor {
    protected Logger logger = LoggerFactory.getLogger(RequestInterceptor.class);
    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        List<String> headers = httpRequest.getHeaders().get(HttpHeader.CITY_ID);
        if(null ==headers || headers.size()<=0){
            Map<String, Object> headerInfo = RequestHeaderContext.getHeaderInfo();
            logger.info("httpRequest.getHeaders():{}"+ JsonUtil.objectToJson(headerInfo));
            //将城市信息放入请求header中
            if (null!=headerInfo) {
                logger.info("RequestHeaderContext.getHeaderInfo():{}"+ JsonUtil.objectToJson(headerInfo));
                logger.info("RequestHeaderContext.cityId:{}"+String.valueOf(headerInfo.get(HttpHeader.CITY_ID)));
                httpRequest.getHeaders().add(HttpHeader.CITY_ID, String.valueOf(headerInfo.get(HttpHeader.CITY_ID)));
            }else {
                logger.info("RequestHeaderContext.getHeaderInfo() is null");
            }
        }
        return clientHttpRequestExecution.execute(httpRequest, bytes);
    }
}
*/
