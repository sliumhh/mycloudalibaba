package com.bocom.common.exception;

/**
 * 异常码
 * 模块码(3位) + 上下游（1上2下） +（错误码 4位）
 *
 * @author sliu
 * @date 2022/4/13 10:05
 */
public enum BaseExceptionEnum {

    SYSTEM_EXCEPTION("500", "系统异常"),
    SERVICE_UNAVAILABLE("503", "服务不可用"),
    DEVICE_ID_INVALID("10010001", "设备编号不合法");

    private final String code;
    private final String msg;

    BaseExceptionEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
