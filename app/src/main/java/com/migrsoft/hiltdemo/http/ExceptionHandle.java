package com.migrsoft.hiltdemo.http;

import com.blankj.utilcode.util.NetworkUtils;
import com.google.gson.JsonSyntaxException;

import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.HttpException;

/**
 * 网络请求错误提示处理
 */
public class ExceptionHandle {

    public static Throwable unifiedError(Throwable e){
        Throwable throwable;
        if(e instanceof UnknownHostException || e instanceof HttpException) {
            //无网络的情况，或者主机挂掉了。返回，对应消息  Unable to resolve host "m.app.haosou.com": No address associated with hostname
            if (!NetworkUtils.isConnected()) {
                //无网络
                throwable = new Throwable("hello?好像没网络啊！",e.getCause());
            } else {
                //主机挂了，也就是你服务器关了
                throwable = new Throwable("服务器开小差,请稍后重试！", e.getCause());
            }
        } else if(e instanceof ConnectException || e instanceof SocketTimeoutException || e instanceof SocketException){
            //连接超时等
            throwable = new Throwable("网络连接超时，请检查您的网络状态！", e.getCause());
        } else if(e instanceof NumberFormatException || e instanceof IllegalArgumentException || e instanceof JsonSyntaxException){
            //也就是后台返回的数据，与你本地定义的Gson类，不一致，导致解析异常 (ps:当然这不能跟客户这么说)
            throwable = new Throwable("未能请求到数据，攻城狮正在修复!", e.getCause());
        }else{
            //其他 未知
            throwable = new Throwable("哎呀故障了，攻城狮正在修复！", e.getCause());
        }
        return throwable;
    }


    public static String getErrorHint(Throwable e){
        e.printStackTrace();
        String result = "";
        if(e instanceof UnknownHostException || e instanceof HttpException) {
            //无网络的情况，或者主机挂掉了。返回，对应消息  Unable to resolve host "m.app.haosou.com": No address associated with hostname
            if (!NetworkUtils.isConnected()) {
                //无网络
                result = "网络连接异常!";
            } else {
                //主机挂了，也就是你服务器关了
                result = "服务器开小差,请稍后重试!";
            }
        } else if(e instanceof ConnectException || e instanceof SocketTimeoutException || e instanceof SocketException){
            //连接超时等
            result = "网络连接超时，请检查您的网络状态!";
        } else if(e instanceof NumberFormatException || e instanceof IllegalArgumentException || e instanceof JsonSyntaxException){
            //也就是后台返回的数据，与你本地定义的Gson类，不一致，导致解析异常 (ps:当然这不能跟客户这么说)
            result = "未能请求到数据，攻城狮正在修复!";
        } else if(e instanceof RuntimeException){
            result = e.getMessage();
        }else{
            //其他 未知
            result = "哎呀故障了，攻城狮正在修复!";
        }
        return result;
    }
}
