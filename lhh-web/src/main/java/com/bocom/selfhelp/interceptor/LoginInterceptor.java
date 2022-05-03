package com.bocom.selfhelp.interceptor;

import com.bocom.common.pojo.CurrentUser;
import com.bocom.common.util.JsonUtil;
import com.bocom.common.util.CurrentUserContext;
import com.bocom.selfhelp.util.RedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author sliu
 * @date 2022/4/7 12:51
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            throw new RuntimeException("需要登录");
        }

        String userInfoStr = redisUtils.get(token);
        if (StringUtils.isBlank(userInfoStr)) {
            throw new RuntimeException("需要登录");
        }

        CurrentUser userInfo = JsonUtil.toClass(userInfoStr, CurrentUser.class);
        CurrentUserContext.setUser(userInfo);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        CurrentUserContext.clear();
    }
}
