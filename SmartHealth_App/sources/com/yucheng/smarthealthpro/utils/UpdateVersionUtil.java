package com.yucheng.smarthealthpro.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.gson.Gson;
import com.yucheng.smarthealthpro.MyApplication;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.care.bean.UploadFileTypeBean;
import com.yucheng.smarthealthpro.framework.http.HttpUtils;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.framework.util.ToastUtil;
import com.yucheng.smarthealthpro.me.activity.BetaUpdateActivity;
import com.yucheng.smarthealthpro.utils.Constant;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/* loaded from: classes5.dex */
public class UpdateVersionUtil {
    private static final int ISUPDATE = 3;
    static String healthRaw = "77c12ed0f7ee5c325c058451ffe26fd7";
    static String mecareRaw = "344fb3f63e18c76c7aba4bcd6229a3be";
    private static UpdateVersionUtil updateVersionService;
    private Context context;
    private HashMap<String, String> hashMap;
    private OkHttpClient okHttpClient;
    private ToastUtil toast;
    private String updateVersionXMLPath;
    private boolean isShowLeast = false;
    private Handler handler = new Handler(Looper.getMainLooper()) { // from class: com.yucheng.smarthealthpro.utils.UpdateVersionUtil.1
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (((Integer) msg.obj).intValue() != 3) {
                return;
            }
            boolean zIsUpdate = UpdateVersionUtil.this.isUpdate();
            if (zIsUpdate) {
                UpdateVersionUtil.this.showUpdateVersionDialog();
            } else if (UpdateVersionUtil.this.isShowLeast) {
                UpdateVersionUtil.this.toast.toast(UpdateVersionUtil.this.context.getResources().getString(R.string.the_latest_version));
            }
            if (UpdateVersionUtil.this.hashMap != null && UpdateVersionUtil.this.hashMap.get("ecgUploadEnable") != null) {
                Tools.saveString("ECGUPLOADENABLE", (String) UpdateVersionUtil.this.hashMap.get("ecgUploadEnable"), UpdateVersionUtil.this.context);
            }
            if (UpdateVersionUtil.this.hashMap != null && UpdateVersionUtil.this.hashMap.get("bpUploadEnable") != null) {
                Tools.saveString("BPUPLOADENABLE", (String) UpdateVersionUtil.this.hashMap.get("bpUploadEnable"), UpdateVersionUtil.this.context);
            }
            if (zIsUpdate) {
                return;
            }
            UpdateVersionUtil.getInstance().checkBetaVersion(true);
        }
    };
    private Runnable runnable = new Runnable() { // from class: com.yucheng.smarthealthpro.utils.UpdateVersionUtil.2
        @Override // java.lang.Runnable
        public void run() {
            UpdateVersionUtil updateVersionUtil = UpdateVersionUtil.this;
            updateVersionUtil.downFile(updateVersionUtil.updateVersionXMLPath);
        }
    };

    interface Signature {
        public static final String health = "health";
        public static final String mecare = "mecare";
    }

    public static synchronized UpdateVersionUtil getInstance() {
        if (updateVersionService == null) {
            updateVersionService = new UpdateVersionUtil();
        }
        return updateVersionService;
    }

    private UpdateVersionUtil() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void downFile(String url) {
        if (url == null) {
            return;
        }
        this.okHttpClient.newCall(new Request.Builder().url(url).build()).enqueue(new Callback() { // from class: com.yucheng.smarthealthpro.utils.UpdateVersionUtil.3
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException e2) {
            }

            /* JADX WARN: Removed duplicated region for block: B:22:? A[RETURN, SYNTHETIC] */
            @Override // okhttp3.Callback
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void onResponse(okhttp3.Call r2, okhttp3.Response r3) throws java.io.IOException {
                /*
                    r1 = this;
                    r2 = 0
                    if (r3 == 0) goto L3e
                    okhttp3.ResponseBody r3 = r3.body()     // Catch: java.lang.Throwable -> L2f java.lang.Exception -> L31
                    java.io.InputStream r2 = r3.byteStream()     // Catch: java.lang.Throwable -> L2f java.lang.Exception -> L31
                    com.yucheng.smarthealthpro.utils.ParseXmlService r3 = new com.yucheng.smarthealthpro.utils.ParseXmlService     // Catch: java.lang.Throwable -> L2f java.lang.Exception -> L31
                    r3.<init>()     // Catch: java.lang.Throwable -> L2f java.lang.Exception -> L31
                    com.yucheng.smarthealthpro.utils.UpdateVersionUtil r0 = com.yucheng.smarthealthpro.utils.UpdateVersionUtil.this     // Catch: java.lang.Throwable -> L2f java.lang.Exception -> L31
                    java.util.HashMap r3 = r3.parseXml(r2)     // Catch: java.lang.Throwable -> L2f java.lang.Exception -> L31
                    com.yucheng.smarthealthpro.utils.UpdateVersionUtil.m2569$$Nest$fputhashMap(r0, r3)     // Catch: java.lang.Throwable -> L2f java.lang.Exception -> L31
                    android.os.Message r3 = new android.os.Message     // Catch: java.lang.Throwable -> L2f java.lang.Exception -> L31
                    r3.<init>()     // Catch: java.lang.Throwable -> L2f java.lang.Exception -> L31
                    r0 = 3
                    java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch: java.lang.Throwable -> L2f java.lang.Exception -> L31
                    r3.obj = r0     // Catch: java.lang.Throwable -> L2f java.lang.Exception -> L31
                    com.yucheng.smarthealthpro.utils.UpdateVersionUtil r0 = com.yucheng.smarthealthpro.utils.UpdateVersionUtil.this     // Catch: java.lang.Throwable -> L2f java.lang.Exception -> L31
                    android.os.Handler r0 = com.yucheng.smarthealthpro.utils.UpdateVersionUtil.m2564$$Nest$fgethandler(r0)     // Catch: java.lang.Throwable -> L2f java.lang.Exception -> L31
                    r0.sendMessage(r3)     // Catch: java.lang.Throwable -> L2f java.lang.Exception -> L31
                    goto L3e
                L2f:
                    r3 = move-exception
                    goto L38
                L31:
                    r3 = move-exception
                    r3.printStackTrace()     // Catch: java.lang.Throwable -> L2f
                    if (r2 == 0) goto L43
                    goto L40
                L38:
                    if (r2 == 0) goto L3d
                    r2.close()
                L3d:
                    throw r3
                L3e:
                    if (r2 == 0) goto L43
                L40:
                    r2.close()
                L43:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.utils.UpdateVersionUtil.AnonymousClass3.onResponse(okhttp3.Call, okhttp3.Response):void");
            }
        });
    }

    public void checkUpdate(String updateVersionXMLPath, Context context, boolean isShowLeast) {
        this.isShowLeast = isShowLeast;
        this.context = context;
        this.updateVersionXMLPath = updateVersionXMLPath;
        this.okHttpClient = new OkHttpClient().newBuilder().hostnameVerifier(new HostnameVerifier() { // from class: com.yucheng.smarthealthpro.utils.UpdateVersionUtil.4
            @Override // javax.net.ssl.HostnameVerifier
            public boolean verify(String hostname, SSLSession session) {
                return HttpUtils.verifyHostName(hostname);
            }
        }).build();
        this.hashMap = new HashMap<>();
        this.toast = ToastUtil.getInstance(context);
        new Thread(this.runnable).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showUpdateVersionDialog() {
        Context context = this.context;
        if (context == null) {
            return;
        }
        Activity activity = (Activity) context;
        if (activity.isFinishing() || activity.isDestroyed()) {
            return;
        }
        View viewInflate = LayoutInflater.from(this.context).inflate(R.layout.dialog_view, (ViewGroup) null);
        ((TextView) viewInflate.findViewById(R.id.dialog_title)).setText(this.context.getString(R.string.prompt));
        ((TextView) viewInflate.findViewById(R.id.dialog_content)).setText(this.context.getString(R.string.update_version_content));
        ((TextView) viewInflate.findViewById(R.id.dialog_cancle)).setText(this.context.getString(R.string.cancel));
        ((TextView) viewInflate.findViewById(R.id.dialog_done)).setText(this.context.getString(R.string.ok));
        final AlertDialog alertDialogCreate = new AlertDialog.Builder(this.context, R.style.loading_dialog).create();
        alertDialogCreate.setCanceledOnTouchOutside(false);
        alertDialogCreate.show();
        if (alertDialogCreate.getWindow() == null) {
            return;
        }
        alertDialogCreate.getWindow().setContentView(viewInflate);
        viewInflate.findViewById(R.id.dialog_done).setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.utils.UpdateVersionUtil.5
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                try {
                    UpdateVersionUtil.this.context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse((String) UpdateVersionUtil.this.hashMap.get("SmartHealth" + ("cn".equals(UpdateVersionUtil.this.context.getString(R.string.lan)) ? "Cn" : "En") + "LoadUrl"))));
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                alertDialogCreate.dismiss();
            }
        });
        viewInflate.findViewById(R.id.dialog_cancle).setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.utils.UpdateVersionUtil.6
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                alertDialogCreate.dismiss();
            }
        });
    }

    private void showBetaVersionDialog(final UploadFileTypeBean.AppInfo app) {
        View viewInflate = LayoutInflater.from(this.context).inflate(R.layout.dialog_view, (ViewGroup) null);
        ((TextView) viewInflate.findViewById(R.id.dialog_title)).setText(this.context.getString(R.string.prompt));
        ((TextView) viewInflate.findViewById(R.id.dialog_content)).setText(this.context.getString(R.string.beta_version_update_tip));
        ((TextView) viewInflate.findViewById(R.id.dialog_cancle)).setText(this.context.getString(R.string.cancel));
        ((TextView) viewInflate.findViewById(R.id.dialog_done)).setText(this.context.getString(R.string.ok));
        final AlertDialog alertDialogCreate = new AlertDialog.Builder(this.context, R.style.loading_dialog).create();
        alertDialogCreate.setCanceledOnTouchOutside(false);
        alertDialogCreate.show();
        if (alertDialogCreate.getWindow() == null) {
            return;
        }
        alertDialogCreate.getWindow().setContentView(viewInflate);
        viewInflate.findViewById(R.id.dialog_done).setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.utils.UpdateVersionUtil.7
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                try {
                    Intent intent = new Intent();
                    intent.putExtra("AppInfo", app);
                    intent.setClass(UpdateVersionUtil.this.context, BetaUpdateActivity.class);
                    UpdateVersionUtil.this.context.startActivity(intent);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                alertDialogCreate.dismiss();
            }
        });
        viewInflate.findViewById(R.id.dialog_cancle).setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.utils.UpdateVersionUtil.8
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                alertDialogCreate.dismiss();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isUpdate() {
        String str;
        HashMap<String, String> map = this.hashMap;
        if (map == null || map.size() == 0) {
            return false;
        }
        try {
            str = this.hashMap.get("SmartHealth" + ("cn".equals(this.context.getString(R.string.lan)) ? "Cn" : "En") + "VersionCode");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        int i2 = !TextUtils.isEmpty(str) ? Integer.parseInt(str) : 0;
        return i2 > getVersionCode();
    }

    private int getVersionCode() {
        try {
            return this.context.getPackageManager().getPackageInfo(this.context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    public UploadFileTypeBean.AppInfo checkBetaVersion(boolean isShowTip) {
        UploadFileTypeBean.AppInfo next;
        if (!Constant.isHealthWear() && !Constant.isSmartHealth()) {
            return null;
        }
        try {
            Context applicationContext = MyApplication.getInstance().getApplicationContext();
            String packageName = this.context.getPackageName();
            UploadFileTypeBean uploadFileTypeBean = (UploadFileTypeBean) new Gson().fromJson((String) SharedPreferencesUtils.get(applicationContext, Constant.SpConstKey.Props, ""), UploadFileTypeBean.class);
            if (Constant.isSmartHealth()) {
                if (healthRaw.equals(ApplySigningUtils.getRawSignatureStr(applicationContext, applicationContext.getPackageName()))) {
                    Iterator<UploadFileTypeBean.AppInfo> it2 = uploadFileTypeBean.getData().getAppBeta().Android.iterator();
                    while (it2.hasNext()) {
                        next = it2.next();
                        if ("health".equals(next.signature) && packageName.equals(next.bundleID)) {
                            break;
                        }
                    }
                    next = null;
                } else {
                    Iterator<UploadFileTypeBean.AppInfo> it3 = uploadFileTypeBean.getData().getAppBeta().Android.iterator();
                    while (it3.hasNext()) {
                        next = it3.next();
                        if (Signature.mecare.equals(next.signature) && packageName.equals(next.bundleID)) {
                            break;
                        }
                    }
                    next = null;
                }
            } else if (uploadFileTypeBean == null || uploadFileTypeBean.getData() == null || uploadFileTypeBean.getData().getAppBeta() == null) {
                next = null;
            } else {
                Iterator<UploadFileTypeBean.AppInfo> it4 = uploadFileTypeBean.getData().getAppBeta().Android.iterator();
                while (it4.hasNext()) {
                    next = it4.next();
                    if (packageName.equals(next.bundleID)) {
                        break;
                    }
                }
                next = null;
            }
            if (next == null || Integer.parseInt(next.bundleVersion) <= getVersionCode()) {
                return null;
            }
            if (isShowTip) {
                showBetaVersionDialog(next);
            }
            return next;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
