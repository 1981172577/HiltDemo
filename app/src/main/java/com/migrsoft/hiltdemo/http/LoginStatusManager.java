package com.migrsoft.hiltdemo.http;

import androidx.lifecycle.MutableLiveData;

/**
 * 全局单例的livedata
 * 当value = {@link ApiResponse.Status#LOGIN_OUT}
 * 用户再其它设备上登录,当前设备需要退出登录
 */
public class LoginStatusManager extends MutableLiveData<Integer> {

    private static class Holder {
        public static final LoginStatusManager INSTANCE = new LoginStatusManager();
    }

    private LoginStatusManager() {
    }

    public static LoginStatusManager getInstance() {
        return Holder.INSTANCE;
    }



}
