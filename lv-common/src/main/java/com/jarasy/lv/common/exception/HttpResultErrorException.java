package com.jarasy.lv.common.exception;

/**
 * Created by Administrator on 2018/4/14 0014.
 */
public class HttpResultErrorException extends RuntimeException {

    private int code;
    private String msg;

    public HttpResultErrorException(String errorContent){
        super(errorContent);
        this.code = ErrorCode.HTTP_INNER_INVOKE_ERROR;
        this.msg = errorContent;
    }

    public HttpResultErrorException(){}

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
