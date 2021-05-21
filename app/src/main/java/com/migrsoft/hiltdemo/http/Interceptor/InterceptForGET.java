package com.migrsoft.hiltdemo.http.Interceptor;

import android.net.Uri;
import android.text.TextUtils;


import com.migrsoft.hiltdemo.BuildConfig;
import com.migrsoft.hiltdemo.utils.encrypt.MD5Utils;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.regex.Pattern;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class InterceptForGET implements Interceptor {

    String patStr = "http://.*\\?"
            + "|[\\u4E00-\\u9FA5]+"
            + "|[\\u3000-\\u301e\\ufe10-\\ufe19\\ufe30-\\ufe44\\ufe50-\\ufe6b\\uff01-\\uffee]+";

    String newPatStr = "http://.*\\?" + "|[^\\w]|_" + "|[\\u4E00-\\u9FA5]+";

    @Override
    @NotNull
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request originalRequest = chain.request();
        HttpUrl url = originalRequest.url().newBuilder()
                .addQueryParameter("appVersion", BuildConfig.VERSION_NAME)
                .addQueryParameter("sign", getSign(originalRequest,getVendorId(originalRequest)))
                .build();
        Request request = originalRequest.newBuilder()
                .url(url)
                .build();
        return chain.proceed(request);
    }

    long getVendorId(Request request) {
        Uri uri = Uri.parse(request.url().toString());
        String vendorId = uri.getQueryParameter("vendorId");
        if (TextUtils.isEmpty(vendorId)) {
            return 0;
        } else {
            return Long.parseLong(vendorId);
        }
    }


    String getSign(Request request, long vendorId) {
        Pattern pat = Pattern.compile(newPatStr);
        String url = "";
        try {
            url = URLDecoder.decode(request.url().toString(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String vendorIdStr = "";
        if (vendorId != 0) {
            vendorIdStr = MD5Utils.md5(MD5Utils.md5(vendorId + ""));// 商家ID的2次MD加密
        }
        String urlString = url + "&appVersion="+ BuildConfig.VERSION_NAME + vendorIdStr;

        String signStr = pat.matcher(urlString).replaceAll("");

        return MD5Utils.md5(signStr);
    }
}
