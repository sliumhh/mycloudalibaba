package com.bocom.common.trace.entity;

import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * trace
 *
 * @author sliu
 * @date 2022/4/11 20:09
 */
@Data
public class Trace implements Serializable {
    private static final long serialVersionUID = -3529478096267544099L;
    private String traceId;
    private String token;

    public Trace() {
    }

    public Trace(String traceId) {
        this.traceId = traceId;
    }

    public static String generateTraceId() {
        return RandomStringUtils.randomAlphanumeric(15).toLowerCase(Locale.ROOT) +
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
    }

}
