package com.jarasy.lv.common.exception;


/**
 * Created by cyjxc1 on 2017/4/5 0005.
 */
public class ParamException extends RuntimeException {

    private int code;
    private String msg;
    //满足前端异常信息的情况下也会附带更多的信息
    private Object data;

    public ParamException(String param, String errorContent){
        super("参数["+param+ "]"+errorContent);
        String localMsg = "参数["+param+ "]"+errorContent;
        this.code = ErrorCode.PARAM_ERROR;
        this.msg = localMsg;
    }
    
    public ParamException(int code) {
    	this.code = code;
    }

    public ParamException(String errorContent) {
        this.code = ErrorCode.PARAM_ERROR;
        this.msg = errorContent;
    }

    public ParamException(){}

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

	
	public Object getData() {
		return data;
	}

	
	public void setData(Object data) {
		this.data = data;
	}
}
