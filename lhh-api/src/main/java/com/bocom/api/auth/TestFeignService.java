package com.bocom.api.auth;

import com.bocom.api.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author sliu
 * @date 2022/4/14 15:29
 */
@FeignClient(value = "auth-manage",
        configuration = FeignConfig.class)
public interface TestFeignService {

    /**
     * @return
     */
    @GetMapping("/device/listId")
    List<String> listId();

}
