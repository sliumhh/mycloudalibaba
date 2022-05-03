package com.bocom.selfhelp.service.bbc;

import com.bocom.common.constant.CommonConst;
import com.bocom.common.pojo.CurrentUser;
import com.bocom.common.util.CurrentUserContext;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

/**
 * 上传文件到建行
 *
 * @author sliu
 * @date 2022/4/7 10:33
 */
@Slf4j
@Service
public class BbcInvoker {

    @Value("${bbc.host}")
    private String host;


    @Autowired
    private OkHttpClient okHttpClient;
    public static final String UPLOAD_URI = "/image-service/uploadImage?ObjNm=%s&BucketId=%s";

    public static final String LOGIN_UC00023_URI = "/gsp/uc00023";
    public static final String LOGOUT_UC990092_URI = "/gsp/uc990092";


    private Request buildRequest(String uri, RequestBody requestBody) {
        return new Request.Builder()
                .url(host + uri)
                .header(CommonConst.BBC_DYNAMIC_PASSWORD, Optional.ofNullable(CurrentUserContext.getCurrentUser()).map(CurrentUser::getToken).orElse(StringUtils.EMPTY))
                .header("Referer", "http://zwfw.xiongan.gov.cn/testwebs/")
                .post(requestBody)
                .build();
    }

    private String doPost(Request request) {
        Response response;
        try {
            response = okHttpClient.newCall(request).execute();
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
