package com.jarasy.lv.common.exception;

/**
 * Created by hwd on 2017/6/14.
 */
public class ParamError {
    private String field;
    private String msg;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
