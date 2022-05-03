package com.bocom.common.util;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * json
 *
 * @author sliu
 * @date 2022/4/12 08:49
 */
public class JsonUtil {
    private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static String toJsonStr(Object obj) {
        if (obj == null) {
            return StringUtils.EMPTY;
        }
        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("json deserialize failed.obj={}", obj.toString());
            throw new RuntimeException("json deserialize failed");
        }
    }

    public static <T> T toClass(String jsonStr, TypeReference<T> typeReference) {
        if (StringUtils.isEmpty(jsonStr)) {
            return null;
        }
        try {
            return OBJECT_MAPPER.readValue(jsonStr, typeReference);
        } catch (JsonProcessingException e) {
            log.error("json deserialize failed.jsonStr={}", jsonStr);
            throw new RuntimeException("json serialize failed");
        }
    }

    public static <T> T toClass(String jsonStr, Class<T> clazz) {
        if (StringUtils.isEmpty(jsonStr)) {
            return null;
        }
        try {
            return OBJECT_MAPPER.readValue(jsonStr, clazz);
        } catch (JsonProcessingException e) {
            log.error("json deserialize failed.jsonStr={}", jsonStr);
            throw new RuntimeException("json serialize failed");
        }
    }

}

