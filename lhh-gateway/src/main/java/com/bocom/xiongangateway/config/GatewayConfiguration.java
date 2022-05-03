package com.bocom.xiongangateway.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;

import java.util.stream.Collectors;

/**
 * gateway没有用springmvc那一套，用的是webflux那一堆所以没有转换器
 *
 * @author sliu
 * @date 2022/4/14 16:26
 */
@Configuration
public class GatewayConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public HttpMessageConverters messageConverters(ObjectProvider<HttpMessageConverter<?>> converters) {
        return new HttpMessageConverters(converters.orderedStream().collect(Collectors.toList()));
    }

    @Bean
    public ErrorWebExceptionHandler errorWebExceptionHandler() {
        return new GlobalExceptionHandler(new ObjectMapper());
    }

}
