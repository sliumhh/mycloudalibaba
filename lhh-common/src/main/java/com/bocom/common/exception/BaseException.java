package com.bocom.common.exception;


import lombok.Getter;
import lombok.Setter;

/**
 * @author sliu
 * @date 2022/4/13 10:02
 */
@Getter
@Setter
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = -2330163010811410000L;

    private String errno;

    private String errmsg;

    public BaseException(String errno, String errmsg) {
        super(errmsg);
        this.errno = errno;
        this.errmsg = errmsg;
    }

}
