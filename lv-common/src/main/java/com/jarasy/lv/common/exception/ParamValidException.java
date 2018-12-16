package com.jarasy.lv.common.exception;


import com.jarasy.lv.common.constant.CommonErrorCode;
import com.jarasy.lv.common.http.DataResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hwd on 2017/6/14.
 */
public class ParamValidException extends ParamException {
    public ParamValidException(String param, String errorContent) {
        super(param, errorContent);
    }

    public ParamValidException(BindingResult bindingResult, DataResult result){
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        List<ParamError> errors = new ArrayList<ParamError>();
        for(FieldError error:fieldErrors){
            ParamError paramError = new ParamError();
            paramError.setField(error.getField());
            paramError.setMsg(error.getDefaultMessage());
            errors.add(paramError);
        }
        this.setCode(CommonErrorCode.PARAM_ERROR);
        this.setMsg("参数异常");
        result.setData(errors);
    }
}