package com.bocom.common.interceptor;

import com.bocom.common.constant.CommonConst;
import com.bocom.common.pojo.BaseResp;
import com.bocom.common.exception.BaseExceptionEnum;
import com.bocom.common.trace.TraceContext;
import com.bocom.common.trace.entity.Trace;
import com.bocom.common.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;

/**
 * @author sliu
 * @date 2022/4/14 16:49
 */
@Slf4j
public class TraceInterceptor implements HandlerInterceptor {
    private Boolean onlyGateway;

    public TraceInterceptor(Boolean onlyGateway) {
        this.onlyGateway = onlyGateway;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        try {
            if (Boolean.TRUE.equals(onlyGateway)) {
                if (!CommonConst.HEADER_FROM_V.equals(request.getHeader(CommonConst.HEADER_FROM_K))) {
                    BaseResp<Object> baseResp = BaseResp.failed(BaseExceptionEnum.SYSTEM_EXCEPTION);
                    ServletOutputStream outputStream = response.getOutputStream();
                    outputStream.write(JsonUtil.toJsonStr(baseResp).getBytes(StandardCharsets.UTF_8));
                    outputStream.flush();
                    outputStream.close();
                    return false;
                }
            }

            String traceId = request.getHeader(CommonConst.HEADER_TRACE);
            if (StringUtils.isEmpty(traceId)) {
                traceId = Trace.generateTraceId();
                log.debug("TraceInterceptor request uri has no trace.generate new traceId={}", traceId);
            }
            MDC.put(CommonConst.HEADER_TRACE, traceId);
            log.info("TraceInterceptor request uri = {},trace={}", request.getRequestURI(), traceId);
            Trace trace = new Trace(traceId);
            trace.setToken(request.getHeader(CommonConst.HEADER_TOKEN));
            TraceContext.setTrace(trace);
        } catch (Exception e) {
            log.warn("TraceInterceptor pre handle exception.uri={}", request.getRequestURI(), e);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        MDC.clear();
        TraceContext.remove();
    }
}
