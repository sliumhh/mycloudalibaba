package com.bocom.api.config;

import com.bocom.common.constant.CommonConst;
import com.bocom.common.trace.TraceContext;
import com.bocom.common.trace.entity.Trace;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

/**
 * 传递trace
 *
 * @author sliu
 * @date 2022/4/11 20:31
 */
@Slf4j
public class FeignInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        try {
            Trace trace = TraceContext.getOrDefault();
            requestTemplate.header(CommonConst.HEADER_TRACE, trace.getTraceId());
            requestTemplate.header(CommonConst.HEADER_FROM_K, CommonConst.HEADER_FROM_V);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}