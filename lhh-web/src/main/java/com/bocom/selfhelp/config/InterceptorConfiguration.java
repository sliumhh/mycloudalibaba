package com.bocom.selfhelp.config;

import com.bocom.common.interceptor.TraceInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author sliu
 * @date 2022/4/14 17:01
 */
@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {
    @Value("${onlyGateway:true}")
    private Boolean onlyGateway;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TraceInterceptor(onlyGateway))
                .addPathPatterns("/**");
    }
}
