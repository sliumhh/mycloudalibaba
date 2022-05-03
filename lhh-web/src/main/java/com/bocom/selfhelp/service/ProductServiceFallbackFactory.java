package com.bocom.selfhelp.service;

import com.bocom.common.exception.BaseException;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author sliu
 * @date 2022/4/14 20:01
 */
@Slf4j
@Component
public class ProductServiceFallbackFactory implements FallbackFactory<Void> {
    @Override
    public Void create(Throwable throwable) {
        log.info("ProductServiceFallbackFactory", throwable);
        throw new BaseException("22", "222");
    }
}
