package com.bocom.xiongangateway.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author sliu
 * @date 2022/4/14 15:29
 */
@FeignClient("auth-manage")
public interface DeviceFeignService {

    @GetMapping("/device/listId")
    List<String> listId();

}
