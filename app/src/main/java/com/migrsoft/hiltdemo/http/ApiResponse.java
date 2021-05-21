package com.migrsoft.hiltdemo.http;

import com.google.gson.annotations.SerializedName;

public class ApiResponse<T> {
    @SerializedName(value = "datasize")
    int dataSize;
    @SerializedName(value = "status", alternate = {"code", "error_code", "returnCode"})
    private int code;
    @SerializedName(value = "message", alternate = {"returnMsg"})
    private String message;
    @SerializedName(value = "addMessage")
    private String addMessage;
    @SerializedName(value = "resultBean", alternate = {"resultList", "returnData"})
    private T data;

    public ApiResponse() {
    }

    public ApiResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ApiResponse(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAddMessage() {
        return addMessage;
    }

    public void setAddMessage(String addMessage) {
        this.addMessage = addMessage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getDataSize() {
        return dataSize;
    }

    public void setDataSize(int dataSize) {
        this.dataSize = dataSize;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", addMessage='" + addMessage + '\'' +
                ", data=" + data +
                ", dataSize=" + dataSize +
                '}';
    }

    public static class Status {
        /**
         * 失败
         */
        public static int FAIL = 0;
        /**
         * 成功
         */
        public static int OK = 1;
        /**
         * 错误，如网络超时
         */
        public static int ERROR = 2;
        /**
         * 其它设备登录,当前设备需要登出
         */
        public static int LOGIN_OUT = 4;
    }
}
