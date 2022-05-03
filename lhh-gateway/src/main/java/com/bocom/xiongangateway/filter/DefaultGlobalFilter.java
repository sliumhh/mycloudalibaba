package com.bocom.xiongangateway.filter;

import com.bocom.common.constant.CommonConst;
import com.bocom.common.pojo.BaseResp;
import com.bocom.common.exception.BaseExceptionEnum;
import com.bocom.common.trace.entity.Trace;
import com.bocom.common.util.JsonUtil;
import com.bocom.xiongangateway.service.DeviceFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author sliu
 * @date 2022/4/14 09:38
 */
@Slf4j
@Component
public class DefaultGlobalFilter implements GlobalFilter, Ordered {
    private final static ThreadLocal<Long> TIME_LOCAL = new ThreadLocal<>();

    @Autowired
    private DeviceFeignService deviceFeignService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        TIME_LOCAL.set(System.currentTimeMillis());
        Mono<Void> result;
        try {
            ServerHttpRequest request = exchange.getRequest();
            request.mutate().header(CommonConst.HEADER_TRACE, Trace.generateTraceId());
            log.info("request uri={},body={}", request.getPath().toString(), JsonUtil.toJsonStr(request.getQueryParams()));
//            todo 鉴权
            List<String> deviceId = request.getHeaders().get(CommonConst.HEADER_DEVICE_ID);
//            if (request.getHeaders().containsKey(CommonConst.HEADER_DEVICE_ID)) {
//                log.info("DeviceFilter deviceId={}", "");
//            } else {
//                return filterFailed(exchange);
//            }
            result = chain.filter(exchange);
            log.info("request uri = {}, total time = {}", request.getPath().toString(),
                    System.currentTimeMillis() - TIME_LOCAL.get());

        } catch (Exception e) {
            log.error("deviceIdList", e);
            return filterFailed(exchange);
        } finally {
            TIME_LOCAL.remove();
        }
        return result;
    }

    @Override
    public int getOrder() {
        return 0;
    }

    private Mono<Void> filterFailed(ServerWebExchange serverWebExchange) {
        serverWebExchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        BaseResp<Object> baseResp = BaseResp.failed(BaseExceptionEnum.DEVICE_ID_INVALID);
        DataBuffer buffer = serverWebExchange.getResponse()
                .bufferFactory().wrap(JsonUtil.toJsonStr(baseResp).getBytes(StandardCharsets.UTF_8));
        ServerHttpResponse response = serverWebExchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        return response.writeWith(Mono.just(buffer));
    }

}
