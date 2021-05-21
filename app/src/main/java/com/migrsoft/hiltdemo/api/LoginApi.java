package com.migrsoft.hiltdemo.api;

import com.migrsoft.hiltdemo.bean.User;
import com.migrsoft.hiltdemo.http.ApiResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LoginApi {

    @GET("/mys/login")
    Observable<ApiResponse<User>> loginByAccount(@Query("loginMobileNo") String userName,
                                                 @Query("loginPwd") String psw,
                                                 @Query("vendorCode") String vendorCode);
}
