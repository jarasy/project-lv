package com.jarasy.lv.common.exception;


/**
 * Created by cyjxc1 on 2017/4/5 0005.
 */
public class HttpInvokeException extends RuntimeException {

    private int code;
    private String msg;

    public HttpInvokeException(String errorContent){
        super(errorContent);
        this.code = ErrorCode.HTTP_INVOKE_ERROR;
        this.msg = errorContent;
    }

    public HttpInvokeException(){}

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
