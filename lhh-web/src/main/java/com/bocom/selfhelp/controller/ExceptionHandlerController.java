package com.bocom.selfhelp.controller;

import com.bocom.common.pojo.BaseResp;
import com.bocom.common.exception.BaseException;
import com.bocom.common.exception.BaseExceptionEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author sliu
 * @date 2022/4/7 16:31
 */
@Slf4j
@RestControllerAdvice
public class ExceptionHandlerController {

    private static final String SERVICE_UNAVAILABLE = "com.netflix.client.ClientException: Load balancer does not have available server for client:";

    @ExceptionHandler(BaseException.class)
    public BaseResp exceptionHandler(HttpServletRequest request, BaseException exception) {
        log.info("handle baseException.uri={},e", request.getRequestURI(), exception);
        BaseResp resp = new BaseResp();
        resp.setErrorMsg(exception.getErrmsg());
        resp.setErrorNo(exception.getErrno());
        return resp;
    }

    @ExceptionHandler(RuntimeException.class)
    public BaseResp exceptionHandler(HttpServletRequest request, RuntimeException exception) {
        log.info("handle runtimeException.uri={},e", request.getRequestURI(), exception);
        BaseResp resp = new BaseResp();
        if (exception.getMessage().contains(SERVICE_UNAVAILABLE)) {
            resp.setErrorMsg(exception.getMessage().replace(SERVICE_UNAVAILABLE, "") +
                    BaseExceptionEnum.SERVICE_UNAVAILABLE.getMsg());
            resp.setErrorNo(BaseExceptionEnum.SERVICE_UNAVAILABLE.getCode());
        } else {
            resp.setErrorMsg(BaseExceptionEnum.SYSTEM_EXCEPTION.getMsg());
            resp.setErrorNo(BaseExceptionEnum.SYSTEM_EXCEPTION.getCode());
        }
        return resp;
    }

    @ExceptionHandler(Exception.class)
    public BaseResp exceptionHandler(HttpServletRequest request, Exception exception) {
        log.info("handle exception.uri={},e", request.getRequestURI(), exception);
        BaseResp resp = new BaseResp();
        resp.setErrorMsg(BaseExceptionEnum.SYSTEM_EXCEPTION.getMsg());
        resp.setErrorNo(BaseExceptionEnum.SYSTEM_EXCEPTION.getCode());
        return resp;
    }

}
