package com.bocom.xiongangateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author sliu
 * @date 2022年04月11日18:14:31
 */
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class LhhGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(LhhGatewayApplication.class, args);
    }

}
