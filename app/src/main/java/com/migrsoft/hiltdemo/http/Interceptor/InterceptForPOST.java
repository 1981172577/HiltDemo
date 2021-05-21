package com.migrsoft.hiltdemo.http.Interceptor;

import android.net.Uri;
import android.text.TextUtils;


import com.migrsoft.hiltdemo.BuildConfig;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.hilt.components.SingletonComponent;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;
import okio.Buffer;

@Singleton
public class InterceptForPOST extends InterceptForGET {

    @Inject
    public InterceptForPOST() {
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request originalRequest = chain.request();
        if (TextUtils.equals("POST", originalRequest.method())) {
            long vendorId = getPostVendorId(originalRequest);
            if (vendorId == 0) {
                vendorId = getVendorId(originalRequest);
            }
            HttpUrl url = originalRequest.url().newBuilder()
                    .addQueryParameter("appVersion", BuildConfig.VERSION_NAME)
                    .addQueryParameter("sign", getSign(originalRequest, vendorId))
                    .build();
            Request request = originalRequest.newBuilder()
                    .url(url)
                    .build();
            return chain.proceed(request);
        } else {
            return super.intercept(chain);
        }
    }

    private long getPostVendorId(Request request) {
        String url = request.url().toString();
//            if (TextUtils.equals("POST", request.method())) {
//                url += "?" + getPostBodyData(request);
//            }
        Uri uri = Uri.parse(url);
        String vendorId = uri.getQueryParameter("vendorId");
        if (TextUtils.isEmpty(vendorId)) {
            return 0;
        } else {
            return Long.parseLong(vendorId);
        }
    }

    private String getPostBodyData(Request request) {
        Buffer buffer = new Buffer();
        try {
            request.body().writeTo(buffer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buffer.readUtf8();
    }
}
