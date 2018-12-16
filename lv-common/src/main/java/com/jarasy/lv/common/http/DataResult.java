package com.jarasy.lv.common.http;

/**
 * Created by cjq on 2018/3/31.
 */
public class DataResult<T> extends Result{

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public DataResult(int code, String message) {
        super(code,message);
    }

    public static DataResult init(){
        return new DataResult(OK,SUCCESS);
    }
    public DataResult(){

    }
    public DataResult buildData(T data){
        this.data = data;
        return this;
    }
}
