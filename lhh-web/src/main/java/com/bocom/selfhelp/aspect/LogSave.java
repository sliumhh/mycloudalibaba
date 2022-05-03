package com.bocom.selfhelp.aspect;

import java.lang.annotation.*;

/**
 * @author sliu
 * @date 2022/4/12 09:23
 */
@Documented
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface LogSave {
    /**
     * 日志备注信息
     */
    String remark() default "";

}
