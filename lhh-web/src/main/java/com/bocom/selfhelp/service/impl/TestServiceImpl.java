package com.bocom.selfhelp.service.impl;

import com.bocom.selfhelp.service.TestService;
import org.springframework.stereotype.Service;

/**
 * @author sliu
 * @date 2022/4/11 18:16
 */
@Service
public class TestServiceImpl implements TestService {
    @Override
    public String testService(String name) {
        return "hello " + name;
    }

}
