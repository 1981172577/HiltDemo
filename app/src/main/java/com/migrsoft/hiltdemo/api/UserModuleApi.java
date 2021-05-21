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

    /**
     * 重置密码接口
     *
     * @param loginPwd 新密码
     * @param mobileNo 手机号
     * @return
     */
    @GET("/mys/resetPassword")
    Observable<ApiResponse> resetPassword(@Query("loginPwd") String loginPwd,
                                          @Query("sendData") String sendData,
                                          @Query("mobileNo") String mobileNo);

    /**
     * 根据查询条件查询门店下的会员
     *
     */
    @GET("/mys/getMemberListByStoreCode")
    Observable<ApiResponse<String>> getMemberListByStoreCode(@QueryMap Map<String,Object> queryMap);

    /**
     * @param vendorId
     * @param id
     * @param userName
     * @return
     */
    @GET("/mys/getMemberDetailByIdAndStoreCode")
    Observable<ApiResponse<String>> getMemberDetailByIdAndStoreCode(@Query("vendorId") long vendorId,
                                                                    @Query("storeCode") String storeCode,
                                                                    @Query("userStoreCode") String userStoreCode,
                                                                    @Query("id") long id,
                                                                    @Query("userName") String userName);

    /**
     * 更新会员接口
     *
     * @param vendorId
     * @param userName
     * @param member
     * @return
     */
    @GET("/mys/memberUpdate")
    Observable<ApiResponse<String>> memberUpdate(@Query("vendorId") long vendorId,
                                                 @Query("userName") String userName,
                                                 @Query("userStoreCode") String userStoreCode,
                                                 @Query("member") String member);

    /**
     * 查询各类型的审批单 待审批数量
     *
     * @return Observable<ApiResponse>
     */
    @GET("/mys/getApproveRecordWaitCountForType")
    Observable<ApiResponse<String>> getApproveRecordWaitCountForType(@Query("vendorId") long vendorId,
                                                                     @Query("userStoreCode") String userStoreCode,
                                                                     @Query("userName") String userName);

    /**
     * 获取会员的相册
     *
     * @param memId 会员ID
     * @return Observable<ApiResponse < String>>
     */
    @GET("/mys/listImgStorage")
    Observable<ApiResponse<String>> getMemberPhotoAlbum(@Query("vendorId") long vendorId,
                                                        @Query("userStoreCode") String userStoreCode,
                                                        @Query("userName") String userName,
                                                        @Query("memId") long memId,
                                                        @Query("start") int start,
                                                        @Query("length") int length);

    /**
     * 删除相册指定图片
     *
     * @param id 删除的图片ID
     * @return Observable<ApiResponse>
     */
    @GET("/mys/delImgStorage")
    Observable<ApiResponse> delImgStorage(@Query("vendorId") long vendorId,
                                          @Query("userName") String userName,
                                          @Query("userStoreCode") String userStoreCode,
                                          @Query("id") long id);
}
