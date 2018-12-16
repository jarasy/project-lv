package com.jarasy.lv.common.http;

import java.util.Map;

/**
 * Created by cjq on 2018/4/12.
 */
public class Request {
    private Map<String,Object> params;

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "Request{" +
                "params=" + params +
                '}';
    }
}
