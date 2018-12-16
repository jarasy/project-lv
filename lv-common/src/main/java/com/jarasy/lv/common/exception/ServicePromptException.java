package com.jarasy.lv.common.exception;


/**
 * 业务提示异常类
 * Created by Administrator on 2017/8/9 0009.
 */
public class ServicePromptException extends RuntimeException{

    private int code;
    private String msg;

    public ServicePromptException(String errorContent){
        super(errorContent);
        this.code = ErrorCode.SERVICE_PROMPT;
        this.msg = errorContent;
    }

    public ServicePromptException(){}

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
