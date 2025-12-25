package com.yucheng.smarthealthpro.framework.http;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import androidx.browser.trusted.sharing.ShareTarget;
import com.facebook.internal.AnalyticsEvents;
import com.google.gson.Gson;
import com.google.maps.android.BuildConfig;
import com.safframework.http.interceptor.AndroidLoggingInterceptor;
import com.yucheng.smarthealthpro.framework.R;
import com.yucheng.smarthealthpro.framework.util.Constants;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.framework.util.SubObserver;
import com.yucheng.smarthealthpro.utils.Constant;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes4.dex */
public class HttpUtils {
    public static final int DOWN_FILE = 3;
    public static final int GET_METHOD = 0;
    private static int METHOD = 0;
    public static final int POST_JSON_METHOD = 2;
    public static final int POST_METHOD = 1;
    private static HttpUtils httpUtils;
    private static Handler mHandler = new Handler(Looper.getMainLooper());
    String appName = "";
    String versionName = "";
    String versionCode = "";
    String firmwareVersionNumber = "";
    String productName = "";
    String productType = "";
    String language = "";
    String zone = "";
    private OkHttpClient mOkHttpClient = new OkHttpClient().newBuilder().hostnameVerifier(new HostnameVerifier() { // from class: com.yucheng.smarthealthpro.framework.http.HttpUtils.1
        @Override // javax.net.ssl.HostnameVerifier
        public boolean verify(String str, SSLSession sSLSession) {
            return HttpUtils.verifyHostName(str);
        }
    }).connectTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).addInterceptor(AndroidLoggingInterceptor.build(false)).build();

    public interface HttpCallback {
        void onSuccess(String str);
    }

    public interface OnDownloadListener {
        void onDownloadFailed();

        void onDownloadSuccess();

        void onDownloading(int i2);
    }

    private HttpUtils() {
    }

    public static synchronized HttpUtils getInstance() {
        if (httpUtils == null) {
            httpUtils = new HttpUtils();
        }
        return httpUtils;
    }

    private static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService("connectivity");
        return (connectivityManager == null || connectivityManager.getActiveNetworkInfo() == null || !connectivityManager.getActiveNetworkInfo().isAvailable()) ? false : true;
    }

    public void postJsonOtherAsynHttp(Context context, String str, String str2, final HttpCallback httpCallback) {
        if (!isNetworkAvailable(context)) {
            Toast.makeText(context, context.getResources().getString(R.string.please_connect_net), 0).show();
            httpCallback.onSuccess(null);
        } else {
            this.mOkHttpClient.newCall(getRequest(context, str, RequestBody.create(str2, MediaType.parse("application/json;charset:utf-8")), "application/json;charset:utf-8")).enqueue(new Callback() { // from class: com.yucheng.smarthealthpro.framework.http.HttpUtils.2
                @Override // okhttp3.Callback
                public void onFailure(Call call, IOException iOException) {
                    HttpUtils.mHandler.post(new Runnable() { // from class: com.yucheng.smarthealthpro.framework.http.HttpUtils.2.1
                        @Override // java.lang.Runnable
                        public void run() {
                            httpCallback.onSuccess(null);
                        }
                    });
                }

                @Override // okhttp3.Callback
                public void onResponse(Call call, Response response) throws IOException {
                    String strString = response.body().string();
                    Log.d("baseframe", "chong-----------postjson--response==" + strString);
                    httpCallback.onSuccess(strString);
                }
            });
        }
    }

    public void postJsonMsgAsynHttp(final Context context, final String str, final String str2, final HttpCallback httpCallback) {
        if (!isNetworkAvailable(context)) {
            if (Looper.getMainLooper() == Looper.myLooper()) {
                Toast.makeText(context, context.getResources().getString(R.string.please_connect_net), 0).show();
            }
            httpCallback.onSuccess(null);
        } else {
            this.mOkHttpClient.newCall(getRequest(context, str, RequestBody.create(str2, MediaType.parse("application/json;charset:utf-8")), "application/json;charset:utf-8")).enqueue(new Callback() { // from class: com.yucheng.smarthealthpro.framework.http.HttpUtils.3
                @Override // okhttp3.Callback
                public void onFailure(Call call, IOException iOException) {
                    HttpUtils.mHandler.post(new Runnable() { // from class: com.yucheng.smarthealthpro.framework.http.HttpUtils.3.1
                        @Override // java.lang.Runnable
                        public void run() {
                            httpCallback.onSuccess(null);
                        }
                    });
                }

                @Override // okhttp3.Callback
                public void onResponse(Call call, Response response) throws IOException {
                    final String strString = response.body().string();
                    Log.d("HttpUtils", "postjson--response==" + str + "--" + strString + "--" + str2);
                    try {
                        final Result result = (Result) new Gson().fromJson(strString, Result.class);
                        HttpUtils.mHandler.post(new Runnable() { // from class: com.yucheng.smarthealthpro.framework.http.HttpUtils.3.2
                            @Override // java.lang.Runnable
                            public void run() {
                                Result result2 = result;
                                if (result2 == null || (result2.code != 0 && result.code != 200)) {
                                    HttpUtils httpUtils2 = HttpUtils.this;
                                    Result result3 = result;
                                    httpUtils2.showToast(result3 == null ? 101010 : result3.code, context);
                                    httpCallback.onSuccess(null);
                                    return;
                                }
                                httpCallback.onSuccess(strString);
                            }
                        });
                    } catch (Exception e2) {
                        HttpUtils.mHandler.post(new Runnable() { // from class: com.yucheng.smarthealthpro.framework.http.HttpUtils.3.3
                            @Override // java.lang.Runnable
                            public void run() {
                                httpCallback.onSuccess(null);
                            }
                        });
                        e2.printStackTrace();
                    }
                }
            });
        }
    }

    public void postMsgAsynHttp(final Context context, final String str, final Map<String, String> map, final HttpCallback httpCallback) {
        if (!isNetworkAvailable(context)) {
            Toast.makeText(context, context.getResources().getString(R.string.please_connect_net), 0).show();
            httpCallback.onSuccess(null);
            return;
        }
        FormBody.Builder builder = new FormBody.Builder();
        if (map != null) {
            for (String str2 : map.keySet()) {
                builder.add(str2, map.get(str2));
            }
        }
        this.mOkHttpClient.newCall(getRequest(context, str, builder.build(), "application/x-www-form-urlencoded;charset:utf-8")).enqueue(new Callback() { // from class: com.yucheng.smarthealthpro.framework.http.HttpUtils.4
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                HttpUtils.mHandler.post(new Runnable() { // from class: com.yucheng.smarthealthpro.framework.http.HttpUtils.4.1
                    @Override // java.lang.Runnable
                    public void run() {
                        httpCallback.onSuccess(null);
                    }
                });
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                final String strString = response.body().string();
                StringBuilder sbAppend = new StringBuilder("chong-----------post--response==").append(str).append("--map==");
                Map map2 = map;
                Log.d("baseframe", sbAppend.append(map2 == null ? BuildConfig.TRAVIS : map2.toString()).append("--").append(strString).toString());
                try {
                    final Result result = (Result) new Gson().fromJson(strString, Result.class);
                    HttpUtils.mHandler.post(new Runnable() { // from class: com.yucheng.smarthealthpro.framework.http.HttpUtils.4.2
                        @Override // java.lang.Runnable
                        public void run() {
                            boolean z = Constants.UPMAC.equals(str) && result.code == 700;
                            Result result2 = result;
                            if (result2 == null || result2.code != 0) {
                                if (!Constants.GETSTATICURL.equals(str) && !z) {
                                    HttpUtils httpUtils2 = HttpUtils.this;
                                    Result result3 = result;
                                    httpUtils2.showToast(result3 == null ? 101010 : result3.code, context);
                                }
                                httpCallback.onSuccess(null);
                                return;
                            }
                            httpCallback.onSuccess(strString);
                        }
                    });
                } catch (Exception e2) {
                    HttpCallback httpCallback2 = httpCallback;
                    if (httpCallback2 != null) {
                        if (context instanceof Activity) {
                            HttpUtils.mHandler.post(new Runnable() { // from class: com.yucheng.smarthealthpro.framework.http.HttpUtils.4.3
                                @Override // java.lang.Runnable
                                public void run() {
                                    httpCallback.onSuccess(null);
                                }
                            });
                        } else {
                            httpCallback2.onSuccess(null);
                        }
                    }
                    e2.printStackTrace();
                }
            }
        });
    }

    public void postMsgAsynHttp(final int i2, final Context context, final String str, Map<String, String> map, final HttpCallback httpCallback) {
        if (!isNetworkAvailable(context)) {
            Toast.makeText(context, context.getResources().getString(R.string.please_connect_net), 0).show();
            httpCallback.onSuccess(null);
            return;
        }
        FormBody.Builder builder = new FormBody.Builder();
        if (map != null) {
            for (String str2 : map.keySet()) {
                builder.add(str2, map.get(str2));
            }
        }
        this.mOkHttpClient.newCall(getRequest(context, str, builder.build(), "application/x-www-form-urlencoded;charset:utf-8")).enqueue(new Callback() { // from class: com.yucheng.smarthealthpro.framework.http.HttpUtils.5
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                HttpUtils.mHandler.post(new Runnable() { // from class: com.yucheng.smarthealthpro.framework.http.HttpUtils.5.1
                    @Override // java.lang.Runnable
                    public void run() {
                        httpCallback.onSuccess(null);
                    }
                });
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                final String strString = response.body().string();
                Log.d("baseframe", "chong-----------post--response==" + str + "--" + strString);
                try {
                    final Result result = (Result) new Gson().fromJson(strString, Result.class);
                    HttpUtils.mHandler.post(new Runnable() { // from class: com.yucheng.smarthealthpro.framework.http.HttpUtils.5.2
                        @Override // java.lang.Runnable
                        public void run() {
                            Result result2;
                            Result result3 = result;
                            if (result3 == null || result3.code != 0) {
                                if (i2 == 100 && (result2 = result) != null && result2.code == 1009) {
                                    httpCallback.onSuccess("1");
                                    return;
                                }
                                HttpUtils httpUtils2 = HttpUtils.this;
                                Result result4 = result;
                                httpUtils2.showToast(result4 == null ? 101010 : result4.code, context);
                                httpCallback.onSuccess(null);
                                return;
                            }
                            httpCallback.onSuccess(strString);
                        }
                    });
                } catch (Exception e2) {
                    httpCallback.onSuccess(null);
                    e2.printStackTrace();
                }
            }
        });
    }

    public void getMsgAsynHttp(final Context context, final String str, Map<String, String> map, final HttpCallback httpCallback) {
        if (!isNetworkAvailable(context)) {
            Toast.makeText(context, context.getResources().getString(R.string.please_connect_net), 0).show();
            httpCallback.onSuccess(null);
            return;
        }
        if (map != null && map.size() > 0) {
            String str2 = str + "?";
            for (String str3 : map.keySet()) {
                str2 = str2 + str3 + "=" + map.get(str3) + "&";
            }
            str = str2.substring(0, str2.length() - 1);
        }
        this.mOkHttpClient.newCall(getRequest(context, str, "application/x-www-form-urlencoded;charset:utf-8")).enqueue(new Callback() { // from class: com.yucheng.smarthealthpro.framework.http.HttpUtils.6
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                Log.d("baseframe", iOException.getMessage());
                Context context2 = context;
                if (context2 == null || !(context2 instanceof Activity) || ((Activity) context2).isFinishing()) {
                    return;
                }
                HttpUtils.mHandler.post(new Runnable() { // from class: com.yucheng.smarthealthpro.framework.http.HttpUtils.6.1
                    @Override // java.lang.Runnable
                    public void run() {
                        httpCallback.onSuccess(null);
                    }
                });
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                final String strString = response.body().string();
                Log.d("baseframe", "chong-----------url===" + str + "--get--response==" + strString);
                try {
                    final Result result = (Result) new Gson().fromJson(strString, Result.class);
                    Context context2 = context;
                    if (context2 == null || !(context2 instanceof Activity) || ((Activity) context2).isFinishing()) {
                        return;
                    }
                    HttpUtils.mHandler.post(new Runnable() { // from class: com.yucheng.smarthealthpro.framework.http.HttpUtils.6.2
                        @Override // java.lang.Runnable
                        public void run() {
                            Result result2 = result;
                            if (result2 == null || (result2.code != 0 && result.code != 200)) {
                                HttpUtils httpUtils2 = HttpUtils.this;
                                Result result3 = result;
                                httpUtils2.showToast(result3 == null ? 101010 : result3.code, context);
                                httpCallback.onSuccess(null);
                                return;
                            }
                            Log.d("baseframe", "chong---------result_code==" + result.code);
                            httpCallback.onSuccess(strString);
                        }
                    });
                } catch (Exception e2) {
                    httpCallback.onSuccess(null);
                    e2.printStackTrace();
                }
            }
        });
    }

    public void getMsgAsynHttpV2(final Context context, final String str, Map<String, String> map, final HttpCallback httpCallback) {
        if (!isNetworkAvailable(context)) {
            Toast.makeText(context, context.getResources().getString(R.string.please_connect_net), 0).show();
            httpCallback.onSuccess(null);
            return;
        }
        if (map != null && map.size() > 0) {
            String str2 = str + "?";
            for (String str3 : map.keySet()) {
                str2 = str2 + str3 + "=" + map.get(str3) + "&";
            }
            str = str2.substring(0, str2.length() - 1);
        }
        this.mOkHttpClient.newCall(getRequest(context, str, "application/x-www-form-urlencoded;charset:utf-8")).enqueue(new Callback() { // from class: com.yucheng.smarthealthpro.framework.http.HttpUtils.7
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                Context context2 = context;
                if (context2 == null || !(context2 instanceof Activity) || ((Activity) context2).isFinishing()) {
                    return;
                }
                HttpUtils.mHandler.post(new Runnable() { // from class: com.yucheng.smarthealthpro.framework.http.HttpUtils.7.1
                    @Override // java.lang.Runnable
                    public void run() {
                        httpCallback.onSuccess(null);
                    }
                });
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                final String strString = response.body().string();
                Log.d("baseframe", "chong-----------url===" + str + "--get--response==" + strString);
                try {
                    final Result result = (Result) new Gson().fromJson(strString, Result.class);
                    Context context2 = context;
                    if (context2 == null || !(context2 instanceof Activity) || ((Activity) context2).isFinishing()) {
                        return;
                    }
                    HttpUtils.mHandler.post(new Runnable() { // from class: com.yucheng.smarthealthpro.framework.http.HttpUtils.7.2
                        @Override // java.lang.Runnable
                        public void run() {
                            Result result2 = result;
                            if (result2 == null || result2.code != 200) {
                                HttpUtils httpUtils2 = HttpUtils.this;
                                Result result3 = result;
                                httpUtils2.showToast(result3 == null ? 101010 : result3.code, context);
                                httpCallback.onSuccess(null);
                                return;
                            }
                            Log.d("baseframe", "chong---------result_code==" + result.code);
                            httpCallback.onSuccess(strString);
                        }
                    });
                } catch (Exception e2) {
                    httpCallback.onSuccess(null);
                    e2.printStackTrace();
                }
            }
        });
    }

    public void upload(final Context context, String str, String str2, File file, final HttpCallback httpCallback) {
        this.mOkHttpClient.newCall(getRequest(context, str, new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart(str2, file.getName(), RequestBody.create(file, MediaType.parse(ShareTarget.ENCODING_TYPE_MULTIPART))).addFormDataPart(Constant.SpConstKey.TOKEN, (String) SharedPreferencesUtils.get(context, Constant.SpConstKey.TOKEN, "")).build())).enqueue(new Callback() { // from class: com.yucheng.smarthealthpro.framework.http.HttpUtils.8
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                HttpUtils.mHandler.post(new Runnable() { // from class: com.yucheng.smarthealthpro.framework.http.HttpUtils.8.1
                    @Override // java.lang.Runnable
                    public void run() {
                        httpCallback.onSuccess(null);
                    }
                });
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                final String strString = response.body().string();
                Log.d("baseframe", "chong-----------get--response==" + strString);
                try {
                    final Result result = (Result) new Gson().fromJson(strString, Result.class);
                    HttpUtils.mHandler.post(new Runnable() { // from class: com.yucheng.smarthealthpro.framework.http.HttpUtils.8.2
                        @Override // java.lang.Runnable
                        public void run() {
                            Result result2 = result;
                            if (result2 == null || result2.code != 0) {
                                HttpUtils httpUtils2 = HttpUtils.this;
                                Result result3 = result;
                                httpUtils2.showToast(result3 == null ? 101010 : result3.code, context);
                                httpCallback.onSuccess(null);
                                return;
                            }
                            httpCallback.onSuccess(strString);
                        }
                    });
                } catch (Exception e2) {
                    httpCallback.onSuccess(null);
                    e2.printStackTrace();
                }
            }
        });
    }

    public void uploadV2(final Context context, String str, String str2, File file, final HttpCallback httpCallback) {
        this.mOkHttpClient.newCall(getRequest(context, str, new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart(str2, file.getName(), RequestBody.create(file, MediaType.parse(ShareTarget.ENCODING_TYPE_MULTIPART))).addFormDataPart(Constant.SpConstKey.TOKEN, (String) SharedPreferencesUtils.get(context, Constant.SpConstKey.TOKEN, "")).build())).enqueue(new Callback() { // from class: com.yucheng.smarthealthpro.framework.http.HttpUtils.9
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                Log.d("baseframe", "upload=" + iOException.getMessage());
                HttpUtils.mHandler.post(new Runnable() { // from class: com.yucheng.smarthealthpro.framework.http.HttpUtils.9.1
                    @Override // java.lang.Runnable
                    public void run() {
                        httpCallback.onSuccess(null);
                    }
                });
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                final String strString = response.body().string();
                Log.d("baseframe", "chong-----------get--response==" + strString);
                try {
                    final Result result = (Result) new Gson().fromJson(strString, Result.class);
                    HttpUtils.mHandler.post(new Runnable() { // from class: com.yucheng.smarthealthpro.framework.http.HttpUtils.9.2
                        @Override // java.lang.Runnable
                        public void run() {
                            Result result2 = result;
                            if (result2 == null || result2.code != 200) {
                                HttpUtils httpUtils2 = HttpUtils.this;
                                Result result3 = result;
                                httpUtils2.showToast(result3 == null ? 101010 : result3.code, context);
                                httpCallback.onSuccess(null);
                                return;
                            }
                            httpCallback.onSuccess(strString);
                        }
                    });
                } catch (Exception e2) {
                    httpCallback.onSuccess(null);
                    e2.printStackTrace();
                }
            }
        });
    }

    public void upload(final Context context, String str, Map<String, String> map, String str2, List<File> list, final HttpCallback httpCallback) {
        MultipartBody.Builder type = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if (list != null) {
            for (File file : list) {
                type.addFormDataPart(str2, file.getName(), RequestBody.create(file, MediaType.parse(ShareTarget.ENCODING_TYPE_MULTIPART)));
            }
        }
        if (map != null) {
            for (String str3 : map.keySet()) {
                type.addFormDataPart(str3, map.get(str3));
            }
        }
        this.mOkHttpClient.newCall(getRequest(context, str, type.build())).enqueue(new Callback() { // from class: com.yucheng.smarthealthpro.framework.http.HttpUtils.10
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                if (context instanceof Activity) {
                    HttpUtils.mHandler.post(new Runnable() { // from class: com.yucheng.smarthealthpro.framework.http.HttpUtils.10.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (httpCallback != null) {
                                httpCallback.onSuccess(null);
                            }
                        }
                    });
                    return;
                }
                HttpCallback httpCallback2 = httpCallback;
                if (httpCallback2 != null) {
                    httpCallback2.onSuccess(null);
                }
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                String strString = response.body().string();
                Log.d("baseframe", "chong-----------get--response==" + strString);
                try {
                    Result result = (Result) new Gson().fromJson(strString, Result.class);
                    if (result == null || result.code != 0) {
                        httpCallback.onSuccess(null);
                    } else {
                        httpCallback.onSuccess(strString);
                    }
                } catch (Exception e2) {
                    httpCallback.onSuccess(null);
                    e2.printStackTrace();
                }
            }
        });
    }

    public void uploadFileAndParam(final Context context, String str, Map<String, String> map, String str2, List<File> list, final HttpCallback httpCallback) {
        MultipartBody.Builder type = new MultipartBody.Builder().setType(MultipartBody.FORM);
        for (File file : list) {
            type.addFormDataPart(str2, file.getName(), RequestBody.create(file, MediaType.parse(ShareTarget.ENCODING_TYPE_MULTIPART)));
        }
        if (map != null) {
            for (String str3 : map.keySet()) {
                type.addFormDataPart(str3, map.get(str3));
            }
        }
        this.mOkHttpClient.newCall(getRequest(context, str, type.build())).enqueue(new Callback() { // from class: com.yucheng.smarthealthpro.framework.http.HttpUtils.11
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                HttpUtils.mHandler.post(new Runnable() { // from class: com.yucheng.smarthealthpro.framework.http.HttpUtils.11.1
                    @Override // java.lang.Runnable
                    public void run() {
                        httpCallback.onSuccess(null);
                    }
                });
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                final String strString = response.body().string();
                Log.d("baseframe", "chong-----------get--response==" + strString);
                try {
                    final Result result = (Result) new Gson().fromJson(strString, Result.class);
                    HttpUtils.mHandler.post(new Runnable() { // from class: com.yucheng.smarthealthpro.framework.http.HttpUtils.11.2
                        @Override // java.lang.Runnable
                        public void run() {
                            Result result2 = result;
                            if (result2 == null || result2.code != 0) {
                                HttpUtils httpUtils2 = HttpUtils.this;
                                Result result3 = result;
                                httpUtils2.showToast(result3 == null ? 101010 : result3.code, context);
                                httpCallback.onSuccess(null);
                                return;
                            }
                            httpCallback.onSuccess(strString);
                        }
                    });
                } catch (Exception e2) {
                    httpCallback.onSuccess(null);
                    e2.printStackTrace();
                }
            }
        });
    }

    public void download(Context context, final String str, final String str2, final OnDownloadListener onDownloadListener) {
        new OkHttpClient().newBuilder().hostnameVerifier(new HostnameVerifier() { // from class: com.yucheng.smarthealthpro.framework.http.HttpUtils.13
            @Override // javax.net.ssl.HostnameVerifier
            public boolean verify(String str3, SSLSession sSLSession) {
                return HttpUtils.verifyHostName(str3);
            }
        }).build().newCall(getRequest(context, str)).enqueue(new Callback() { // from class: com.yucheng.smarthealthpro.framework.http.HttpUtils.12
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                onDownloadListener.onDownloadFailed();
            }

            /* JADX WARN: Removed duplicated region for block: B:62:0x00a2 A[EXC_TOP_SPLITTER, SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:69:0x0098 A[EXC_TOP_SPLITTER, SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:78:? A[SYNTHETIC] */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:44:0x0091 -> B:66:0x0094). Please report as a decompilation issue!!! */
            @Override // okhttp3.Callback
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void onResponse(okhttp3.Call r10, okhttp3.Response r11) throws java.lang.Throwable {
                /*
                    r9 = this;
                    r10 = 1024(0x400, float:1.435E-42)
                    byte[] r10 = new byte[r10]
                    r0 = 0
                    okhttp3.ResponseBody r1 = r11.body()     // Catch: java.lang.Throwable -> L73 java.lang.Exception -> L76
                    java.io.InputStream r1 = r1.byteStream()     // Catch: java.lang.Throwable -> L73 java.lang.Exception -> L76
                    okhttp3.ResponseBody r11 = r11.body()     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L6f
                    long r2 = r11.contentLength()     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L6f
                    java.io.File r11 = new java.io.File     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L6f
                    java.lang.String r4 = r3     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L6f
                    com.yucheng.smarthealthpro.framework.http.HttpUtils r5 = com.yucheng.smarthealthpro.framework.http.HttpUtils.this     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L6f
                    java.lang.String r6 = r4     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L6f
                    java.lang.String r5 = com.yucheng.smarthealthpro.framework.http.HttpUtils.access$200(r5, r6)     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L6f
                    r11.<init>(r4, r5)     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L6f
                    boolean r4 = r11.exists()     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L6f
                    if (r4 == 0) goto L2d
                    r11.delete()     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L6f
                L2d:
                    java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L6f
                    r4.<init>(r11)     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L6f
                    r5 = 0
                L34:
                    int r11 = r1.read(r10)     // Catch: java.lang.Throwable -> L67 java.lang.Exception -> L69
                    r0 = -1
                    if (r11 == r0) goto L51
                    r0 = 0
                    r4.write(r10, r0, r11)     // Catch: java.lang.Throwable -> L67 java.lang.Exception -> L69
                    long r7 = (long) r11     // Catch: java.lang.Throwable -> L67 java.lang.Exception -> L69
                    long r5 = r5 + r7
                    float r11 = (float) r5     // Catch: java.lang.Throwable -> L67 java.lang.Exception -> L69
                    r0 = 1065353216(0x3f800000, float:1.0)
                    float r11 = r11 * r0
                    float r0 = (float) r2     // Catch: java.lang.Throwable -> L67 java.lang.Exception -> L69
                    float r11 = r11 / r0
                    r0 = 1120403456(0x42c80000, float:100.0)
                    float r11 = r11 * r0
                    int r11 = (int) r11     // Catch: java.lang.Throwable -> L67 java.lang.Exception -> L69
                    com.yucheng.smarthealthpro.framework.http.HttpUtils$OnDownloadListener r0 = r2     // Catch: java.lang.Throwable -> L67 java.lang.Exception -> L69
                    r0.onDownloading(r11)     // Catch: java.lang.Throwable -> L67 java.lang.Exception -> L69
                    goto L34
                L51:
                    r4.flush()     // Catch: java.lang.Throwable -> L67 java.lang.Exception -> L69
                    com.yucheng.smarthealthpro.framework.http.HttpUtils$OnDownloadListener r10 = r2     // Catch: java.lang.Throwable -> L67 java.lang.Exception -> L69
                    r10.onDownloadSuccess()     // Catch: java.lang.Throwable -> L67 java.lang.Exception -> L69
                    if (r1 == 0) goto L63
                    r1.close()     // Catch: java.io.IOException -> L5f
                    goto L63
                L5f:
                    r10 = move-exception
                    r10.printStackTrace()
                L63:
                    r4.close()     // Catch: java.io.IOException -> L90
                    goto L94
                L67:
                    r10 = move-exception
                    goto L6d
                L69:
                    r10 = move-exception
                    goto L71
                L6b:
                    r10 = move-exception
                    r4 = r0
                L6d:
                    r0 = r1
                    goto L96
                L6f:
                    r10 = move-exception
                    r4 = r0
                L71:
                    r0 = r1
                    goto L78
                L73:
                    r10 = move-exception
                    r4 = r0
                    goto L96
                L76:
                    r10 = move-exception
                    r4 = r0
                L78:
                    r10.printStackTrace()     // Catch: java.lang.Throwable -> L95
                    com.yucheng.smarthealthpro.framework.http.HttpUtils$OnDownloadListener r10 = r2     // Catch: java.lang.Throwable -> L95
                    r10.onDownloadFailed()     // Catch: java.lang.Throwable -> L95
                    if (r0 == 0) goto L8a
                    r0.close()     // Catch: java.io.IOException -> L86
                    goto L8a
                L86:
                    r10 = move-exception
                    r10.printStackTrace()
                L8a:
                    if (r4 == 0) goto L94
                    r4.close()     // Catch: java.io.IOException -> L90
                    goto L94
                L90:
                    r10 = move-exception
                    r10.printStackTrace()
                L94:
                    return
                L95:
                    r10 = move-exception
                L96:
                    if (r0 == 0) goto La0
                    r0.close()     // Catch: java.io.IOException -> L9c
                    goto La0
                L9c:
                    r11 = move-exception
                    r11.printStackTrace()
                La0:
                    if (r4 == 0) goto Laa
                    r4.close()     // Catch: java.io.IOException -> La6
                    goto Laa
                La6:
                    r11 = move-exception
                    r11.printStackTrace()
                Laa:
                    throw r10
                */
                throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.framework.http.HttpUtils.AnonymousClass12.onResponse(okhttp3.Call, okhttp3.Response):void");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getNameFromUrl(String str) {
        if (str == null) {
            return null;
        }
        return str.substring(str.lastIndexOf("/") + 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showToast(int i2, Context context) {
        if (context == null || Constants.isTechFeel) {
            return;
        }
        if (i2 == 400) {
            Toast.makeText(context, context.getResources().getString(R.string.wrong_account_or_password), 0).show();
            return;
        }
        if (i2 == 500) {
            Toast.makeText(context, context.getResources().getString(R.string.server_exception), 0).show();
            return;
        }
        if (i2 == 700) {
            Toast.makeText(context, context.getString(R.string.please_relogin), 0).show();
            HashMap map = new HashMap();
            map.put(Constant.SpConstKey.TOKEN, true);
            SubObserver.getInstance().nodifyObservers(map);
            return;
        }
        if (i2 == 2629) {
            Toast.makeText(context, context.getResources().getString(R.string.sms_out_of_limit), 0).show();
            return;
        }
        if (i2 == 2009) {
            Toast.makeText(context, context.getResources().getString(R.string.the_parameter_is_empty), 0).show();
            return;
        }
        if (i2 != 2010) {
            switch (i2) {
                case 1003:
                    Toast.makeText(context, context.getResources().getString(R.string.user_already_exists), 0).show();
                    break;
                case 1004:
                    Toast.makeText(context, context.getResources().getString(R.string.verification_code_error), 0).show();
                    break;
                case 1005:
                    Toast.makeText(context, context.getResources().getString(R.string.msisdn_does_not_exist), 0).show();
                    break;
                case 1006:
                    Toast.makeText(context, context.getResources().getString(R.string.no_data_found), 0).show();
                    break;
                case 1007:
                    Toast.makeText(context, context.getResources().getString(R.string.lock_bound), 0).show();
                    break;
                case 1008:
                    Toast.makeText(context, context.getResources().getString(R.string.data_already_exists), 0).show();
                    break;
                case 1009:
                    Toast.makeText(context, context.getResources().getString(R.string.user_does_not_exist), 0).show();
                    break;
                case 1010:
                    Toast.makeText(context, context.getResources().getString(R.string.user_password_error), 0).show();
                    break;
                case 1011:
                    Toast.makeText(context, context.getResources().getString(R.string.the_password_doesnt_match_twice), 0).show();
                    break;
                case 1012:
                    Toast.makeText(context, context.getResources().getString(R.string.old_password_error), 0).show();
                    break;
                case 1013:
                    Toast.makeText(context, context.getResources().getString(R.string.failed_to_send_mail), 0).show();
                    break;
                default:
                    switch (i2) {
                        case 1015:
                            Toast.makeText(context, context.getResources().getString(R.string.user_information_not_found), 0).show();
                            break;
                        case 1016:
                            Toast.makeText(context, context.getResources().getString(R.string.applied_please_do_not_repeat_application), 0).show();
                            break;
                        case 1017:
                            Toast.makeText(context, context.getResources().getString(R.string.you_are_already_friends_Please_dont_apply_again), 0).show();
                            break;
                        case 1018:
                            Toast.makeText(context, context.getResources().getString(R.string.you_cannot_add_yourself_as_a_relative_or_friend), 0).show();
                            break;
                        case 1019:
                            Toast.makeText(context, context.getResources().getString(R.string.you_cancel_adding_yourself_as_a_friend), 0).show();
                            break;
                        case 1020:
                            Toast.makeText(context, context.getResources().getString(R.string.users_cannot_delete_themselves), 0).show();
                            break;
                    }
            }
            return;
        }
        Toast.makeText(context, context.getResources().getString(R.string.failed_to_get_weather), 0).show();
    }

    private class Result {
        public int code = 0;

        private Result() {
        }
    }

    public void postMsgAsynHttpV2(final Context context, final String str, final Map<String, String> map, final HttpCallback httpCallback) {
        if (!isNetworkAvailable(context)) {
            Toast.makeText(context, context.getResources().getString(R.string.please_connect_net), 0).show();
            httpCallback.onSuccess(null);
            return;
        }
        FormBody.Builder builder = new FormBody.Builder();
        if (map != null) {
            for (String str2 : map.keySet()) {
                builder.add(str2, map.get(str2));
            }
        }
        this.mOkHttpClient.newCall(getRequest(context, str, builder.build())).enqueue(new Callback() { // from class: com.yucheng.smarthealthpro.framework.http.HttpUtils.14
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                HttpUtils.mHandler.post(new Runnable() { // from class: com.yucheng.smarthealthpro.framework.http.HttpUtils.14.1
                    @Override // java.lang.Runnable
                    public void run() {
                        httpCallback.onSuccess(null);
                    }
                });
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                final String strString = response.body().string();
                StringBuilder sbAppend = new StringBuilder("chong-----------post--response==").append(str).append("--map==");
                Map map2 = map;
                Log.d("baseframe", sbAppend.append(map2 == null ? BuildConfig.TRAVIS : map2.toString()).append("--").append(strString).toString());
                try {
                    final Result result = (Result) new Gson().fromJson(strString, Result.class);
                    HttpUtils.mHandler.post(new Runnable() { // from class: com.yucheng.smarthealthpro.framework.http.HttpUtils.14.2
                        @Override // java.lang.Runnable
                        public void run() {
                            Result result2 = result;
                            if (result2 == null || result2.code != 200) {
                                if (!Constants.GETSTATICURL.equals(str)) {
                                    HttpUtils httpUtils2 = HttpUtils.this;
                                    Result result3 = result;
                                    httpUtils2.showToast(result3 == null ? 101010 : result3.code, context);
                                }
                                httpCallback.onSuccess(null);
                                return;
                            }
                            httpCallback.onSuccess(strString);
                        }
                    });
                } catch (Exception e2) {
                    HttpCallback httpCallback2 = httpCallback;
                    if (httpCallback2 != null) {
                        if (context instanceof Activity) {
                            HttpUtils.mHandler.post(new Runnable() { // from class: com.yucheng.smarthealthpro.framework.http.HttpUtils.14.3
                                @Override // java.lang.Runnable
                                public void run() {
                                    httpCallback.onSuccess(null);
                                }
                            });
                        } else {
                            httpCallback2.onSuccess(null);
                        }
                    }
                    e2.printStackTrace();
                }
            }
        });
    }

    public static boolean verifyHostName(String str) {
        return str.contains("web-api.ycaviation.com") || str.contains("web-api-test-d.ycaviation.com") || str.contains("weather-api.ycaviation.com") || str.contains("weather-api-test-d.ycaviation.com") || str.contains("android.bugly.qq.com") || str.contains("dualstack-a.apilocate.amap.com") || str.contains("apilocate.amap.com") || str.contains("mpsapi.amap.com") || str.contains("restsdk.amap.com") || str.contains("opsc-test-d.ycaviation.com") || str.contains("h.trace.qq.com") || str.contains("cgi.connect.qq.com") || str.contains("appsupport.qq.com") || str.contains("thirdwx.qlogo.cn") || str.contains("app.storage.test.ycinnovate.com") || str.contains("kangyuanai.com") || str.contains("aitis.co") || str.contains("wprd01.is.autonavi.com") || str.contains("wprd02.is.autonavi.com") || str.contains("wprd03.is.autonavi.com") || str.contains("wprd04.is.autonavi.com") || str.contains("autonavi.com") || str.contains("opsc.ycaviation.com") || str.contains("googleapis.com") || str.contains("amap.com") || str.contains("qq.com") || str.contains("ycaviation.com") || str.contains("ycinnovate.com") || str.contains("google.com");
    }

    private Request getRequest(Context context, String str) {
        return getRequest(context, str, null, "");
    }

    private Request getRequest(Context context, String str, String str2) {
        return getRequest(context, str, null, str2);
    }

    private Request getRequest(Context context, String str, RequestBody requestBody) {
        return getRequest(context, str, requestBody, "");
    }

    private Request getRequest(Context context, String str, RequestBody requestBody, String str2) {
        Request.Builder builderUrl = new Request.Builder().url(str);
        if (requestBody != null) {
            builderUrl.post(requestBody);
        }
        addHeaderInfo(context, builderUrl, str2);
        return builderUrl.build();
    }

    public void putHeaderInfo(String str, String str2, String str3, String str4, int i2, String str5, String str6, String str7) {
        this.appName = str;
        this.versionName = str2;
        this.versionCode = str3;
        this.firmwareVersionNumber = str4;
        if (i2 == 0) {
            this.productType = "watch";
        } else if (i2 == 1) {
            this.productType = "ring";
        } else if (i2 == 2) {
            this.productType = "touchRing";
        } else {
            this.productType = AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
        }
        this.productName = str5;
        this.language = str6;
        this.zone = str7;
    }

    private void addHeaderInfo(Context context, Request.Builder builder, String str) {
        String str2 = (String) SharedPreferencesUtils.get(context, Constant.SpConstKey.TOKEN, "");
        String str3 = Build.BRAND;
        String str4 = Build.MODEL;
        String string = new StringBuffer().append(this.versionName).append(";").append(this.versionCode).append(";").append(this.appName).append(";Android ").append(Build.VERSION.RELEASE).append(";").append(Build.BRAND).append(StringUtils.SPACE).append(Build.MODEL).append(";").append(this.firmwareVersionNumber).append(";").append(this.productType).append(";").append(this.productName).append(";").toString();
        if (!TextUtils.isEmpty(str)) {
            builder.addHeader("content-type", str);
        }
        builder.addHeader("Access-Token", str2).addHeader("User-Agent", string).addHeader("language", this.language).addHeader("zone", this.zone);
        Log.d("ltf", "headder=" + builder.getHeaders().toString());
    }
}
