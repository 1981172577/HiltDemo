package com.migrsoft.hiltdemo.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * 简介：接口调用封装
 */

public class WebApiService {

    private static final String BASE_URL = "http://www.baidu.com/";

    private static final Gson GSON = new GsonBuilder()
            .serializeNulls()
            .disableInnerClassSerialization()
            .create();
    private static final Retrofit.Builder RETROFIT_BUILDER = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(GSON));
    private static final OkHttpClient.Builder CLIENT_BUILDER = new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS)
            .writeTimeout(100, TimeUnit.SECONDS);

    /**
     * @param cls
     * @param interceptors
     * @param <T>
     * @return
     */
    public synchronized static <T> T generateApi(Class<T> cls, Interceptor... interceptors) {
        return generateApi(cls, null, interceptors);
    }

    /**
     * @param cls
     * @param baseUrl
     * @param interceptors
     * @param <T>
     * @return
     */
    public synchronized static <T> T generateApi(Class<T> cls, String baseUrl, Interceptor... interceptors) {
        CLIENT_BUILDER.interceptors().clear();
        for (Interceptor interceptor : interceptors) {
            CLIENT_BUILDER.addInterceptor(interceptor);
        }
        Retrofit.Builder retrofitBuilder;
        if (baseUrl != null) {
            retrofitBuilder = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(GSON));
        } else {
            retrofitBuilder = RETROFIT_BUILDER;
        }
        return retrofitBuilder.client(CLIENT_BUILDER.build())
                .build()
                .create(cls);
    }
}
