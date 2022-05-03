package com.bocom.xiongangateway.filter;

import com.bocom.common.constant.CommonConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 添加header，from，请求证明来自网关
 *
 * @author sliu
 * @date 2022年04月21日14:06:43
 */
@Slf4j
@Component
public class FromGlobalFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        request.mutate().header(CommonConst.HEADER_FROM_K, CommonConst.HEADER_FROM_V);
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 1;
    }

}
