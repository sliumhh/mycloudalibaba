package com.bocom.selfhelp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author sliu
 */
@EnableFeignClients(basePackages = "com.bocom.api" )
@EnableDiscoveryClient
@SpringBootApplication
public class LhhApplication {

    public static void main(String[] args) {
        SpringApplication.run(LhhApplication.class, args);
    }

}
