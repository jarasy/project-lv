package com.jarasy.lv.common.exception;


/**
 * 权限Exception
 * Created by cyjxc1 on 2017/4/5 0005.
 */
public class AuthException extends RuntimeException {

    private int code;
    private String msg;

    public AuthException(String errorContent){
        super(errorContent);
        this.code = ErrorCode.INVALID_ERROR;
        this.msg = errorContent;
    }

    public AuthException(){}

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
