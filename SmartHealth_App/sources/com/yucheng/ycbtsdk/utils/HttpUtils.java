package com.yucheng.ycbtsdk.utils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/* loaded from: classes5.dex */
public class HttpUtils {
    private static HttpUtils httpUtils;
    private OkHttpClient mOkHttpClient;

    public interface HttpCallback {
        void onSuccess(String str);
    }

    private HttpUtils() {
        OkHttpClient.Builder builderNewBuilder = new OkHttpClient().newBuilder();
        TimeUnit timeUnit = TimeUnit.SECONDS;
        this.mOkHttpClient = builderNewBuilder.connectTimeout(30L, timeUnit).writeTimeout(30L, timeUnit).readTimeout(30L, timeUnit).build();
    }

    public static synchronized HttpUtils getInstance() {
        if (httpUtils == null) {
            httpUtils = new HttpUtils();
        }
        return httpUtils;
    }

    public void postJsonMsgAsynHttp(String str, String str2, final HttpCallback httpCallback) {
        this.mOkHttpClient.newCall(new Request.Builder().url(str).post(RequestBody.create(str2, MediaType.parse("application/json;charset:utf-8"))).addHeader("content-type", "application/json;charset:utf-8").build()).enqueue(new Callback() { // from class: com.yucheng.ycbtsdk.utils.HttpUtils.1
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                if (iOException != null) {
                    YCBTLog.e("postJsonMsgAsynHttp onFailure " + iOException.getMessage());
                }
                httpCallback.onSuccess(null);
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) {
                httpCallback.onSuccess(response.body().string());
            }
        });
    }
}
