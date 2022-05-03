package com.bocom.common.pojo;

import com.bocom.common.exception.BaseExceptionEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * 通用响应体
 *
 * @author sliu
 * @date 2022/4/12 09:50
 */
@Data
public class BaseResp<T> implements Serializable {
    private static final long serialVersionUID = -3150147860316025068L;
    private static final String SUCCESS_NO = "0";
    private static final String SUCCESS_MSG = "ok";

    private static final String FAILED_NO = "500";
    private static final String FAILED_MSG = "系统维护中";
    private String errorNo;
    private String errorMsg;
    private T body;


    public static <T> BaseResp<T> success() {
        BaseResp<T> resp = new BaseResp<>();
        resp.setErrorNo(SUCCESS_NO);
        resp.setErrorMsg(SUCCESS_MSG);
        resp.setBody(null);
        return resp;
    }

    public static <T> BaseResp<T> success(T obj) {
        BaseResp<T> resp = new BaseResp<>();
        resp.setErrorNo(SUCCESS_NO);
        resp.setErrorMsg(SUCCESS_MSG);
        resp.setBody(obj);
        return resp;
    }

    public static <T> BaseResp<T> failed() {
        BaseResp<T> resp = new BaseResp<>();
        resp.setErrorNo(FAILED_NO);
        resp.setErrorMsg(FAILED_MSG);
        return resp;
    }

    public static <T> BaseResp<T> failed(String errorNo, String errorMsg) {
        BaseResp<T> resp = new BaseResp<>();
        resp.setErrorNo(FAILED_NO);
        resp.setErrorMsg(FAILED_MSG);
        return resp;
    }

    public static <T> BaseResp<T> failed(BaseExceptionEnum exceptionEnum) {
        BaseResp<T> resp = new BaseResp<>();
        resp.setErrorNo(exceptionEnum.getCode());
        resp.setErrorMsg(exceptionEnum.getMsg());
        return resp;
    }
}
