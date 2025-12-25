package com.yucheng.smarthealthpro.utils;

import com.yucheng.smarthealthpro.framework.http.HttpUtils;
import java.io.IOException;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/* loaded from: classes5.dex */
public class DownloadUtil {
    private static DownloadUtil downloadUtil;
    public String filePath;
    private final OkHttpClient okHttpClient = new OkHttpClient().newBuilder().hostnameVerifier(new HostnameVerifier() { // from class: com.yucheng.smarthealthpro.utils.DownloadUtil.1
        @Override // javax.net.ssl.HostnameVerifier
        public boolean verify(String hostname, SSLSession session) {
            return HttpUtils.verifyHostName(hostname);
        }
    }).build();
    public long total;

    public interface OnDownloadListener {
        void onDownloadFailed();

        void onDownloadSuccess();

        void onDownloading(int progress);
    }

    public static DownloadUtil getInstance() {
        if (downloadUtil == null) {
            downloadUtil = new DownloadUtil();
        }
        return downloadUtil;
    }

    private DownloadUtil() {
    }

    public void download(final String url, final String saveDir, final OnDownloadListener listener) {
        this.okHttpClient.newCall(new Request.Builder().url(url).build()).enqueue(new Callback() { // from class: com.yucheng.smarthealthpro.utils.DownloadUtil.2
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException e2) {
                listener.onDownloadFailed();
            }

            /* JADX WARN: Removed duplicated region for block: B:52:0x00a0 A[EXC_TOP_SPLITTER, SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:58:0x009b A[EXC_TOP_SPLITTER, SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:69:? A[SYNTHETIC] */
            @Override // okhttp3.Callback
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void onResponse(okhttp3.Call r8, okhttp3.Response r9) throws java.lang.Throwable {
                /*
                    r7 = this;
                    r8 = 2048(0x800, float:2.87E-42)
                    byte[] r8 = new byte[r8]
                    java.lang.String r0 = r3
                    java.lang.String r0 = com.yucheng.smarthealthpro.me.setting.dial.util.SystemUiUtil.isExistDir(r0)
                    r1 = 0
                    okhttp3.ResponseBody r2 = r9.body()     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L89
                    java.io.InputStream r2 = r2.byteStream()     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L89
                    com.yucheng.smarthealthpro.utils.DownloadUtil r3 = com.yucheng.smarthealthpro.utils.DownloadUtil.this     // Catch: java.lang.Throwable -> L7f java.lang.Exception -> L83
                    okhttp3.ResponseBody r9 = r9.body()     // Catch: java.lang.Throwable -> L7f java.lang.Exception -> L83
                    long r4 = r9.getContentLength()     // Catch: java.lang.Throwable -> L7f java.lang.Exception -> L83
                    r3.total = r4     // Catch: java.lang.Throwable -> L7f java.lang.Exception -> L83
                    java.io.File r9 = new java.io.File     // Catch: java.lang.Throwable -> L7f java.lang.Exception -> L83
                    java.lang.String r3 = r4     // Catch: java.lang.Throwable -> L7f java.lang.Exception -> L83
                    java.lang.String r3 = com.yucheng.smarthealthpro.utils.DownloadUtil.getNameFromUrl(r3)     // Catch: java.lang.Throwable -> L7f java.lang.Exception -> L83
                    r9.<init>(r0, r3)     // Catch: java.lang.Throwable -> L7f java.lang.Exception -> L83
                    boolean r0 = r9.exists()     // Catch: java.lang.Throwable -> L7f java.lang.Exception -> L83
                    if (r0 == 0) goto L33
                    r9.delete()     // Catch: java.lang.Throwable -> L7f java.lang.Exception -> L83
                L33:
                    com.yucheng.smarthealthpro.utils.DownloadUtil r0 = com.yucheng.smarthealthpro.utils.DownloadUtil.this     // Catch: java.lang.Throwable -> L7f java.lang.Exception -> L83
                    java.lang.String r3 = r9.getPath()     // Catch: java.lang.Throwable -> L7f java.lang.Exception -> L83
                    r0.filePath = r3     // Catch: java.lang.Throwable -> L7f java.lang.Exception -> L83
                    boolean r0 = r9.exists()     // Catch: java.lang.Throwable -> L7f java.lang.Exception -> L83
                    if (r0 == 0) goto L44
                    r9.delete()     // Catch: java.lang.Throwable -> L7f java.lang.Exception -> L83
                L44:
                    java.io.FileOutputStream r0 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L7f java.lang.Exception -> L83
                    r0.<init>(r9)     // Catch: java.lang.Throwable -> L7f java.lang.Exception -> L83
                    r3 = 0
                L4b:
                    int r9 = r2.read(r8)     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L84
                    r1 = -1
                    if (r9 == r1) goto L6c
                    r1 = 0
                    r0.write(r8, r1, r9)     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L84
                    long r5 = (long) r9     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L84
                    long r3 = r3 + r5
                    float r9 = (float) r3     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L84
                    r1 = 1065353216(0x3f800000, float:1.0)
                    float r9 = r9 * r1
                    com.yucheng.smarthealthpro.utils.DownloadUtil r1 = com.yucheng.smarthealthpro.utils.DownloadUtil.this     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L84
                    long r5 = r1.total     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L84
                    float r1 = (float) r5     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L84
                    float r9 = r9 / r1
                    r1 = 1120403456(0x42c80000, float:100.0)
                    float r9 = r9 * r1
                    int r9 = (int) r9     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L84
                    com.yucheng.smarthealthpro.utils.DownloadUtil$OnDownloadListener r1 = r2     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L84
                    r1.onDownloading(r9)     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L84
                    goto L4b
                L6c:
                    r0.flush()     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L84
                    com.yucheng.smarthealthpro.utils.DownloadUtil$OnDownloadListener r8 = r2     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L84
                    r8.onDownloadSuccess()     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L84
                    if (r2 == 0) goto L79
                    r2.close()     // Catch: java.io.IOException -> L79
                L79:
                    r0.close()     // Catch: java.io.IOException -> L97
                    goto L97
                L7d:
                    r8 = move-exception
                    goto L81
                L7f:
                    r8 = move-exception
                    r0 = r1
                L81:
                    r1 = r2
                    goto L99
                L83:
                    r0 = r1
                L84:
                    r1 = r2
                    goto L8a
                L86:
                    r8 = move-exception
                    r0 = r1
                    goto L99
                L89:
                    r0 = r1
                L8a:
                    com.yucheng.smarthealthpro.utils.DownloadUtil$OnDownloadListener r8 = r2     // Catch: java.lang.Throwable -> L98
                    r8.onDownloadFailed()     // Catch: java.lang.Throwable -> L98
                    if (r1 == 0) goto L94
                    r1.close()     // Catch: java.io.IOException -> L94
                L94:
                    if (r0 == 0) goto L97
                    goto L79
                L97:
                    return
                L98:
                    r8 = move-exception
                L99:
                    if (r1 == 0) goto L9e
                    r1.close()     // Catch: java.io.IOException -> L9e
                L9e:
                    if (r0 == 0) goto La3
                    r0.close()     // Catch: java.io.IOException -> La3
                La3:
                    throw r8
                */
                throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.utils.DownloadUtil.AnonymousClass2.onResponse(okhttp3.Call, okhttp3.Response):void");
            }
        });
    }

    public static String getNameFromUrl(String url) {
        return url.substring(url.lastIndexOf("/") + 1);
    }
}
