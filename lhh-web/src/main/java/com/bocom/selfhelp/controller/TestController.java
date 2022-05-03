package com.bocom.selfhelp.controller;

import com.bocom.api.auth.TestFeignService;
import com.bocom.common.pojo.BaseResp;
import com.bocom.selfhelp.aspect.LogSave;
import com.bocom.selfhelp.entity.TestUser;
import com.bocom.selfhelp.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * demo
 *
 * @author sliu
 * @date 2022/4/11 18:17
 */
@Slf4j
@RestController
public class TestController {
    @Autowired
    private TestService testService;
    @Autowired
    private TestFeignService testFeignService;

    @LogSave
    @GetMapping("/hello")
    public String hello(String name) {
        List<String> strings = testFeignService.listId();
        return testService.testService(name);
    }

    @LogSave
    @PostMapping("/hello2")
    public BaseResp<TestUser> helloPost(@RequestBody TestUser user) {
        return BaseResp.success(user);
    }
}
