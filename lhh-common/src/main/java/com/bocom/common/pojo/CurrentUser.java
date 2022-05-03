package com.bocom.common.pojo;

import lombok.Data;

/**
 * @author sliu
 * @date 2022/4/12 10:59
 */
@Data
public class CurrentUser {
    /**
     * 证件号码
     */
    private String certNo;
    /**
     * 证件类型
     */
    private String certType;
    /**
     * 注册手机号
     */
    private String contactMobile;

    /**
     * 设备编号
     */
    private String deviceId;

    private String token;

    private String traceId;
}
