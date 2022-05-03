package com.bocom.selfhelp.util.http;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Objects;

/**
 * @author sliu
 * @date 2022/4/13 10:16
 */
@Slf4j
public class HttpClientUtil {

    @Autowired
    private OkHttpClient okHttpClient;

    private static OkHttpClient client;

    @PostConstruct
    public void init() {
        client = okHttpClient;
    }

    public static String doPost(Request request) {
        Response response;
        try {
            response = client.newCall(request).execute();
            ResponseBody body = response.body();
            if (Objects.nonNull(body)) {
                return body.string();
            }
        } catch (IOException e) {
            log.warn("doPost IOException", e);
        }
        return StringUtils.EMPTY;
    }


}
