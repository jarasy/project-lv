package com.jarasy.lv.common.exception;


/**
 * 业务异常类
 * Created by cyjxc1 on 2017/6/6 0006.
 */
public class ServiceException extends RuntimeException {

    private int code;
    private String msg;

    public ServiceException(Integer code,String errorContent){

        super(errorContent);
        this.code = code;
        this.msg = errorContent;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
