package com.jarasy.lv.common.exception;

/**
 * Created by admin on 2018/1/15.
 */
public interface ErrorCode {
    //系统异常
    int SYSTEM_ERROR= 1000000;

    /**
     * TOKEN 校验不通过
     */
    int INVALID_ERROR = 100;

    /**
     * http 请求错误,非200 的网络异常
     */
    int HTTP_INVOKE_ERROR = 101;
    /**
     * 参数异常
     */
    int PARAM_ERROR = 102;

    /**
     * 未处理异常
     */
    int UNKONW_ERROR = 103;

    /***
     * 数据异常
     */
    int DATA_ERROR = 104;

    /**
     * 服务提示异常
     */
    int SERVICE_PROMPT = 105;

    /**
     * http 请求错误,返回值为200 ，code 非0
     */
    int HTTP_INNER_INVOKE_ERROR = 106;

    /**
     * 供应商系统
     */
    int COMPANY_NOT_PASS = 1001;

    /**
     * 单位转区申请，审核异常
     */
    int UNIT_EXCHANGE_SAVE_ERROR = 2011;
    int UNIT_EXCHANGE_AUDIT_ERROR = 2012;
    int UNIT_EXCHANGE_DETAIL_ERROR = 2013;

    /**
     * 单位注销申请，审核异常
     */
    int UNIT_CANCEL_SAVE_ERROR = 2021;
    int UNIT_CANCEL_AUDIT_ERROR = 2022;
    int UNIT_CANCEL_DETAIL_ERROR = 2023;

    /**
     * 用户未登录异常
     */
    int USER_NO_LOGIN = 107;
}
