package com.migrsoft.hiltdemo.api;


import com.migrsoft.hiltdemo.bean.User;
import com.migrsoft.hiltdemo.http.ApiResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * 用户模块
 */
public interface UserModuleApi {

    @GET("/mys/login")
    Observable<ApiResponse<User>> loginByAccount(@Query("loginMobileNo") String userName,
                                                 @Query("loginPwd") String psw,
                                                 @Query("vendorCode") String vendorCode);

    /**
     * 重置密码发送验证码
     *
     * @param mobileNo 手机号
     * @return
     */
    @GET("/mys/sendMessage")
    Observable<ApiResponse> sendMessage(@Query("mobileNo") String mobileNo);
}
