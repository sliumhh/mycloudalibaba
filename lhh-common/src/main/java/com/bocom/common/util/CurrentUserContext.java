package com.bocom.common.util;

import com.bocom.common.pojo.CurrentUser;

/**
 * 当前登录人信息
 *
 * @author sliu
 * @date 2022/4/7 14:34
 */
public class CurrentUserContext {
    private final static ThreadLocal<CurrentUser> USER_CONTEXT = new ThreadLocal<>();

    public static CurrentUser getCurrentUser() {
        return USER_CONTEXT.get();
    }

    public static void setUser(CurrentUser user) {
        USER_CONTEXT.set(user);
    }

    public static void clear() {
        USER_CONTEXT.remove();
    }

}
