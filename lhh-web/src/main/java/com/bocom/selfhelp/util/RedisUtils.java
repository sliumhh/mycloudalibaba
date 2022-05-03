package com.bocom.selfhelp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author sliu
 * @date 2022/4/12 10:55
 */
@Component
public class RedisUtils {
    @Autowired
    private StringRedisTemplate redisTemplate;

    public void set(String key, String value, Long expire) {
        redisTemplate.opsForValue().set(key, value, expire, TimeUnit.MINUTES);
    }

    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void del(String key) {
        redisTemplate.delete(key);
    }


}
