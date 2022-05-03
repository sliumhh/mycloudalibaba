package com.bocom.selfhelp.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author sliu
 * @date 2022/4/12 09:24
 */
@Slf4j
@Aspect
@Component
public class LogSaveAspect {
    @Pointcut("@annotation(LogSave)")
    public void doAspect() {
    }

    @Around("doAspect()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        Object result;
        try {
            Method method = ((MethodSignature) pjp.getSignature()).getMethod();
            Object[] args = pjp.getArgs();
            LogSave methodLog = method.getAnnotation(LogSave.class);
            String remark = methodLog.remark();
            String params = Arrays.toString(pjp.getArgs());
            log.info(params);
            // TODO 保存请求日志+备注
            result = pjp.proceed();
            // todo,保存响应日志
        } catch (Throwable e) {
            // todo, 保存响应日志
            throw e;
        }
        return result;
    }
}
