package com.jarasy.lv.common.exception;


/**
 * Created by cyjxc1 on 2017/6/6 0006.
 */
public class DataErrorException extends RuntimeException {

    private int code;
    private String msg;

    public DataErrorException(String errorContent){
        super(errorContent);
        this.code = ErrorCode.DATA_ERROR;
        this.msg = errorContent;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
