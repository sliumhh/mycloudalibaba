package com.bocom.selfhelp.config;

import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @author sliu
 * @date 2022/4/7 11:31
 */
@RefreshScope
@Configuration
public class HttpClientConfig {

    @Value("${okHttpClient.writeTimeout}")
    private Integer readTimeout;

    @Value("${okHttpClient.writeTimeout}")
    private Integer writeTimeout;

    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder()
                .readTimeout(readTimeout, TimeUnit.MILLISECONDS)
                .writeTimeout(writeTimeout, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(Boolean.FALSE)
                .build();
    }

}
