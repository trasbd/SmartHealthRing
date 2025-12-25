package com.yucheng.smarthealthpro.me.activity;

import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.PowerManager;
import android.util.Log;
import android.widget.TextView;
import com.facebook.internal.ServerProtocol;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.realsil.sdk.dfu.DfuConstants;
import com.tencent.bugly.crashreport.CrashReport;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.databinding.ActivitySoftUpdateBinding;
import com.yucheng.smarthealthpro.dialog.MyDialog;
import com.yucheng.smarthealthpro.framework.HealthApplication;
import com.yucheng.smarthealthpro.framework.http.HttpUtils;
import com.yucheng.smarthealthpro.framework.util.Constants;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.framework.util.ToastUtil;
import com.yucheng.smarthealthpro.login.normal.WebViewActivity;
import com.yucheng.smarthealthpro.me.bean.UpgradeBean;
import com.yucheng.smarthealthpro.sport.view.CommonDialog;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.DialogUtils;
import com.yucheng.smarthealthpro.utils.DownloadUtil;
import com.yucheng.smarthealthpro.utils.EventBusMessageEvent;
import com.yucheng.smarthealthpro.utils.MultiLanguageUtils;
import com.yucheng.smarthealthpro.utils.Tools;
import com.yucheng.smarthealthpro.view.progress.NumberProgressBar;
import com.yucheng.ycbtsdk.Constants;
import com.yucheng.ycbtsdk.YCBTClient;
import com.yucheng.ycbtsdk.gatt.BleHelper;
import com.yucheng.ycbtsdk.response.BleDataResponse;
import com.yucheng.ycbtsdk.upgrade.DfuCallBack;
import com.yucheng.ycbtsdk.utils.SPUtil;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import org.apache.commons.lang3.StringUtils;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes5.dex */
public class SoftUpdateActivity extends BaseVbActivity<ActivitySoftUpdateBinding> {
    private static final int CONNECTING = 1008;
    private static final int DISCONNECT = 1007;
    private static final int DOWNFAILED = 1003;
    private static final int DOWNPROGRESS = 1001;
    private static final int DOWNSUCCESS = 1002;
    private static final int ERROR = 1009;
    private static final int FAILED = 1006;
    private static final int SUCCESS = 1005;
    private static final String TAG = "SoftUpdateActivity:";
    private static final int UPDATA = 1004;
    private int bNo;
    CommonDialog dialog;
    private boolean isStart;
    private String mFilePath;
    private MyDialog mLoading;
    private String mSelectedDeviceMac;
    private String mSelectedDeviceName;
    private PowerManager.WakeLock mWakeLock;
    private NumberProgressBar numberProgressBar;
    private int sNo;
    private int type;
    String upurl;
    private TextView wversion;
    private UpgradeBean upgradeBean = new UpgradeBean();
    private int number = 0;
    private String autoUpdateVersion = "";
    private final Handler handler = new Handler(Looper.getMainLooper()) { // from class: com.yucheng.smarthealthpro.me.activity.SoftUpdateActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message msg) throws IOException {
            if (SoftUpdateActivity.this.isFinishing()) {
            }
            switch (msg.what) {
                case 1001:
                    int iIntValue = ((Integer) msg.obj).intValue();
                    SoftUpdateActivity.this.numberProgressBar.setProgress(iIntValue);
                    if (iIntValue >= 100) {
                        SoftUpdateActivity softUpdateActivity = SoftUpdateActivity.this;
                        softUpdateActivity.setDialogTitle(softUpdateActivity.getString(R.string.down_success));
                        SoftUpdateActivity.this.numberProgressBar.setProgress(100);
                        break;
                    }
                    break;
                case 1002:
                    if (YCBTClient.connectState() != 10 || SoftUpdateActivity.this.mSelectedDeviceMac == null) {
                        ToastUtil.getInstance(SoftUpdateActivity.this).toast(SoftUpdateActivity.this.getString(R.string.disconnect_connection));
                        YCBTClient.disconnectBle();
                        SoftUpdateActivity.this.finish();
                        break;
                    } else {
                        SoftUpdateActivity.this.upgradeBean.data.mac = SoftUpdateActivity.this.mSelectedDeviceMac;
                        SoftUpdateActivity softUpdateActivity2 = SoftUpdateActivity.this;
                        softUpdateActivity2.setDialogTitle(softUpdateActivity2.getString(R.string.down_success));
                        TextView textView = (TextView) SoftUpdateActivity.this.findViewById(R.id.upgrade_content);
                        int deviceBatteryState = YCBTClient.getDeviceBatteryState();
                        int deviceBatteryValue = YCBTClient.getDeviceBatteryValue();
                        if (DownloadUtil.getInstance().total <= 8388608) {
                            if (DownloadUtil.getInstance().total <= 1048576) {
                                if (deviceBatteryState != 2 && deviceBatteryValue < 30) {
                                    SoftUpdateActivity.this.initBattyDialog();
                                    break;
                                }
                            } else {
                                textView.setText(SoftUpdateActivity.this.getString(R.string.upgrade_content).replaceAll("1-3", "3-5").replaceAll("30%", "40%"));
                                if (deviceBatteryState != 2 && deviceBatteryValue < 40) {
                                    SoftUpdateActivity.this.initBattyDialog();
                                    break;
                                }
                            }
                        } else {
                            textView.setText(SoftUpdateActivity.this.getString(R.string.upgrade_content).replaceAll("1-3", "30-60").replaceAll("30%", "50%"));
                            if (deviceBatteryState != 2 && deviceBatteryValue < 50) {
                                SoftUpdateActivity.this.initBattyDialog();
                                break;
                            }
                        }
                        SoftUpdateActivity.this.onUploadClicked();
                        break;
                    }
                case 1003:
                    SoftUpdateActivity.this.dismissDialog();
                    SoftUpdateActivity softUpdateActivity3 = SoftUpdateActivity.this;
                    Tools.showAlert3(softUpdateActivity3, softUpdateActivity3.getString(R.string.down_failed));
                    SoftUpdateActivity.this.finish();
                    break;
                case 1004:
                    int iIntValue2 = ((Integer) msg.obj).intValue();
                    if (iIntValue2 >= 100 && !YCBTClient.isJieLi()) {
                        SoftUpdateActivity softUpdateActivity4 = SoftUpdateActivity.this;
                        softUpdateActivity4.setDialogTitle(softUpdateActivity4.getString(R.string.update_success));
                        SoftUpdateActivity.this.numberProgressBar.setProgress(100);
                        break;
                    } else if (YCBTClient.isJieLi() && iIntValue2 >= 10000) {
                        SoftUpdateActivity softUpdateActivity5 = SoftUpdateActivity.this;
                        softUpdateActivity5.setDialogTitle(softUpdateActivity5.getString(R.string.update_success));
                        SoftUpdateActivity.this.numberProgressBar.setProgress(DfuConstants.MAX_NOTIFICATION_LOCK_WAIT_TIME);
                        break;
                    } else {
                        SoftUpdateActivity softUpdateActivity6 = SoftUpdateActivity.this;
                        softUpdateActivity6.setDialogTitle(softUpdateActivity6.getString(R.string.updating));
                        SoftUpdateActivity.this.numberProgressBar.setProgress(iIntValue2);
                        break;
                    }
                    break;
                case 1005:
                    SoftUpdateActivity.this.upgradeBean.data.upStatus = 10;
                    SoftUpdateActivity softUpdateActivity7 = SoftUpdateActivity.this;
                    softUpdateActivity7.upgradeUpload(softUpdateActivity7.upgradeBean);
                    SoftUpdateActivity.this.dismissDialog();
                    SoftUpdateActivity softUpdateActivity8 = SoftUpdateActivity.this;
                    Tools.showAlert3(softUpdateActivity8, softUpdateActivity8.getString(R.string.update_success));
                    SoftUpdateActivity.this.finish();
                    break;
                case 1006:
                    if (SoftUpdateActivity.this.number < 2) {
                        SoftUpdateActivity.this.onUploadClicked();
                        SoftUpdateActivity.this.number++;
                    } else {
                        SoftUpdateActivity softUpdateActivity9 = SoftUpdateActivity.this;
                        Tools.showAlert3(softUpdateActivity9, softUpdateActivity9.getString(R.string.upgrading_failed));
                        SoftUpdateActivity.this.dismissDialog();
                        if (Constant.isHealthWear() || Constant.isSmartHealth()) {
                            SoftUpdateActivity.this.showHelp();
                        } else {
                            SoftUpdateActivity.this.finish();
                        }
                    }
                    SoftUpdateActivity softUpdateActivity10 = SoftUpdateActivity.this;
                    softUpdateActivity10.setDialogTitle(softUpdateActivity10.getString(R.string.upgrading_failed));
                    break;
                case 1007:
                    SoftUpdateActivity softUpdateActivity11 = SoftUpdateActivity.this;
                    softUpdateActivity11.setDialogTitle(softUpdateActivity11.getString(R.string.disconnect_connection));
                    break;
                case 1008:
                    SoftUpdateActivity softUpdateActivity12 = SoftUpdateActivity.this;
                    softUpdateActivity12.setDialogTitle(softUpdateActivity12.getString(R.string.bluetooth_is_connecting));
                    break;
                case 1009:
                    if (SoftUpdateActivity.this.number < 3) {
                        SoftUpdateActivity.this.number++;
                        SoftUpdateActivity softUpdateActivity13 = SoftUpdateActivity.this;
                        softUpdateActivity13.setDialogTitle(softUpdateActivity13.getString(R.string.bluetooth_is_connecting));
                        SoftUpdateActivity.this.cancelNotification();
                        BleHelper.getHelper().disconnectGatt();
                        break;
                    } else {
                        SoftUpdateActivity softUpdateActivity14 = SoftUpdateActivity.this;
                        Tools.showAlert3(softUpdateActivity14, softUpdateActivity14.getString(R.string.upgrading_failed));
                        SoftUpdateActivity.this.showHelp();
                        break;
                    }
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void cancelNotification() {
        NotificationManager notificationManager;
        if (YCBTClient.getChipScheme() != 0 || (notificationManager = (NotificationManager) getSystemService("notification")) == null) {
            return;
        }
        notificationManager.cancel(283);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showHelp() {
        CommonDialog commonDialog = this.dialog;
        if (commonDialog != null) {
            commonDialog.dismiss();
            this.dialog = null;
        }
        CommonDialog commonDialog2 = new CommonDialog(this);
        this.dialog = commonDialog2;
        commonDialog2.setMessage(getString(R.string.upgrade_failed_help)).setTitle(getString(R.string.prompt)).setSingle(false).setOnClickBottomListener(new CommonDialog.OnClickBottomListener() { // from class: com.yucheng.smarthealthpro.me.activity.SoftUpdateActivity.2
            @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
            public void onConfirmClick() {
                if (Constant.isHealthWear()) {
                    if (MultiLanguageUtils.isZh(SoftUpdateActivity.this)) {
                        SoftUpdateActivity.this.startActivity(new Intent(SoftUpdateActivity.this, (Class<?>) WebViewActivity.class).putExtra("title", SoftUpdateActivity.this.getString(R.string.me_using_help_title)).putExtra("url", "https://staticpage.ycaviation.com/new_app/other/ota/ota_cn.html"));
                    } else {
                        SoftUpdateActivity.this.startActivity(new Intent(SoftUpdateActivity.this, (Class<?>) WebViewActivity.class).putExtra("title", SoftUpdateActivity.this.getString(R.string.me_using_help_title)).putExtra("url", "https://staticpage.ycaviation.com/new_app/other/ota/ota_en.html"));
                    }
                } else if (Constant.isSmartHealth()) {
                    if (MultiLanguageUtils.isZh(SoftUpdateActivity.this)) {
                        SoftUpdateActivity.this.startActivity(new Intent(SoftUpdateActivity.this, (Class<?>) WebViewActivity.class).putExtra("title", SoftUpdateActivity.this.getString(R.string.me_using_help_title)).putExtra("url", "https://staticpage.ycaviation.com/new_app/other/ota_smart_health_ring/ota_cn.html"));
                    } else {
                        SoftUpdateActivity.this.startActivity(new Intent(SoftUpdateActivity.this, (Class<?>) WebViewActivity.class).putExtra("title", SoftUpdateActivity.this.getString(R.string.me_using_help_title)).putExtra("url", "https://staticpage.ycaviation.com/new_app/other/ota_smart_health_ring/ota_en.html"));
                    }
                }
                SoftUpdateActivity.this.dialog.dismiss();
                SoftUpdateActivity.this.dialog = null;
                SoftUpdateActivity.this.finish();
            }

            @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
            public void onCancelClick() {
                SoftUpdateActivity.this.dialog.dismiss();
                SoftUpdateActivity.this.dialog = null;
                SoftUpdateActivity.this.finish();
            }

            @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
            public void onEditTextConfirmClick(String mEditText) {
                SoftUpdateActivity.this.dialog.dismiss();
                SoftUpdateActivity.this.dialog = null;
            }
        }).show();
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) throws NumberFormatException {
        super.onCreate(savedInstanceState);
        init();
        initData();
    }

    private void init() {
        changeTitle(getString(R.string.upgrade_software));
        showBack();
        EventBus.getDefault().register(this);
        this.wversion = (TextView) findViewById(R.id.wversion);
        this.numberProgressBar = (NumberProgressBar) findViewById(R.id.numberbar);
    }

    private void initData() throws NumberFormatException {
        YCBTClient.setOta(true);
        this.mSelectedDeviceName = YCBTClient.getBindDeviceName();
        this.mSelectedDeviceMac = YCBTClient.getBindDeviceMac();
        if (YCBTClient.isForceOta()) {
            this.mSelectedDeviceMac = getMacAddOne(SPUtil.getBindedDeviceMac());
        }
        if (getIntent() != null && getIntent().getBooleanExtra("isMainStart", false)) {
            this.bNo = getIntent().getIntExtra("bNo", 0);
            this.sNo = getIntent().getIntExtra("sNo", 0);
            this.type = getIntent().getIntExtra("type", 0);
            this.autoUpdateVersion = getIntent().getStringExtra("autoUpdateVersion");
            this.upgradeBean.data.deviceName = getIntent().getStringExtra("deviceName");
            checkUp();
        } else {
            this.bNo = 0;
            this.sNo = 0;
            getDeviceTypeName();
        }
        this.wversion.setText(this.bNo + "." + this.sNo);
    }

    @Override // com.yucheng.smarthealthpro.framework.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        try {
            if (this.mWakeLock == null) {
                this.mWakeLock = ((PowerManager) getSystemService("power")).newWakeLock(536870922, "softup");
            }
            PowerManager.WakeLock wakeLock = this.mWakeLock;
            if (wakeLock == null || wakeLock.isHeld()) {
                return;
            }
            this.mWakeLock.acquire();
        } catch (Exception e2) {
            CrashReport.postCatchedException(e2);
            e2.printStackTrace();
        }
    }

    @Override // com.yucheng.smarthealthpro.framework.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        try {
            PowerManager.WakeLock wakeLock = this.mWakeLock;
            if (wakeLock == null || !wakeLock.isHeld()) {
                return;
            }
            this.mWakeLock.release();
        } catch (Exception e2) {
            CrashReport.postCatchedException(e2);
            e2.printStackTrace();
        }
    }

    public void checkVersion(final String devname) {
        if ("E66B".equals(devname) && this.bNo == 0 && this.sNo == 92) {
            devname = "E66C";
        }
        DownloadUtil.getInstance().download(Constants.getFirmwareurl() + devname + ".plist", "health", new DownloadUtil.OnDownloadListener() { // from class: com.yucheng.smarthealthpro.me.activity.SoftUpdateActivity.3
            @Override // com.yucheng.smarthealthpro.utils.DownloadUtil.OnDownloadListener
            public void onDownloading(int progress) {
            }

            @Override // com.yucheng.smarthealthpro.utils.DownloadUtil.OnDownloadListener
            public void onDownloadSuccess() {
                SoftUpdateActivity.this.getDeviceVersion();
            }

            @Override // com.yucheng.smarthealthpro.utils.DownloadUtil.OnDownloadListener
            public void onDownloadFailed() {
                SoftUpdateActivity.this.handler.sendEmptyMessage(1003);
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x00c8 A[Catch: Exception -> 0x0292, TryCatch #6 {Exception -> 0x0292, blocks: (B:3:0x000c, B:30:0x00c2, B:32:0x00c8, B:35:0x00ef, B:37:0x0125, B:39:0x012b, B:51:0x0168, B:54:0x0172, B:56:0x0193, B:59:0x019c, B:61:0x01a0, B:63:0x01a8, B:65:0x01cb, B:70:0x01db, B:72:0x01df, B:76:0x01e6, B:77:0x01f1, B:79:0x0212, B:81:0x021a, B:68:0x01d6, B:41:0x013a, B:43:0x0140, B:46:0x0150, B:48:0x0156, B:50:0x015c, B:34:0x00dd, B:29:0x00bb, B:20:0x008a, B:11:0x005a), top: B:98:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00db  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0125 A[Catch: Exception -> 0x0292, TryCatch #6 {Exception -> 0x0292, blocks: (B:3:0x000c, B:30:0x00c2, B:32:0x00c8, B:35:0x00ef, B:37:0x0125, B:39:0x012b, B:51:0x0168, B:54:0x0172, B:56:0x0193, B:59:0x019c, B:61:0x01a0, B:63:0x01a8, B:65:0x01cb, B:70:0x01db, B:72:0x01df, B:76:0x01e6, B:77:0x01f1, B:79:0x0212, B:81:0x021a, B:68:0x01d6, B:41:0x013a, B:43:0x0140, B:46:0x0150, B:48:0x0156, B:50:0x015c, B:34:0x00dd, B:29:0x00bb, B:20:0x008a, B:11:0x005a), top: B:98:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0138  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0172 A[Catch: Exception -> 0x0292, TRY_ENTER, TryCatch #6 {Exception -> 0x0292, blocks: (B:3:0x000c, B:30:0x00c2, B:32:0x00c8, B:35:0x00ef, B:37:0x0125, B:39:0x012b, B:51:0x0168, B:54:0x0172, B:56:0x0193, B:59:0x019c, B:61:0x01a0, B:63:0x01a8, B:65:0x01cb, B:70:0x01db, B:72:0x01df, B:76:0x01e6, B:77:0x01f1, B:79:0x0212, B:81:0x021a, B:68:0x01d6, B:41:0x013a, B:43:0x0140, B:46:0x0150, B:48:0x0156, B:50:0x015c, B:34:0x00dd, B:29:0x00bb, B:20:0x008a, B:11:0x005a), top: B:98:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x01a8 A[Catch: Exception -> 0x0292, TryCatch #6 {Exception -> 0x0292, blocks: (B:3:0x000c, B:30:0x00c2, B:32:0x00c8, B:35:0x00ef, B:37:0x0125, B:39:0x012b, B:51:0x0168, B:54:0x0172, B:56:0x0193, B:59:0x019c, B:61:0x01a0, B:63:0x01a8, B:65:0x01cb, B:70:0x01db, B:72:0x01df, B:76:0x01e6, B:77:0x01f1, B:79:0x0212, B:81:0x021a, B:68:0x01d6, B:41:0x013a, B:43:0x0140, B:46:0x0150, B:48:0x0156, B:50:0x015c, B:34:0x00dd, B:29:0x00bb, B:20:0x008a, B:11:0x005a), top: B:98:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x01d9  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x01df A[Catch: Exception -> 0x0292, TryCatch #6 {Exception -> 0x0292, blocks: (B:3:0x000c, B:30:0x00c2, B:32:0x00c8, B:35:0x00ef, B:37:0x0125, B:39:0x012b, B:51:0x0168, B:54:0x0172, B:56:0x0193, B:59:0x019c, B:61:0x01a0, B:63:0x01a8, B:65:0x01cb, B:70:0x01db, B:72:0x01df, B:76:0x01e6, B:77:0x01f1, B:79:0x0212, B:81:0x021a, B:68:0x01d6, B:41:0x013a, B:43:0x0140, B:46:0x0150, B:48:0x0156, B:50:0x015c, B:34:0x00dd, B:29:0x00bb, B:20:0x008a, B:11:0x005a), top: B:98:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0212 A[Catch: Exception -> 0x0292, TryCatch #6 {Exception -> 0x0292, blocks: (B:3:0x000c, B:30:0x00c2, B:32:0x00c8, B:35:0x00ef, B:37:0x0125, B:39:0x012b, B:51:0x0168, B:54:0x0172, B:56:0x0193, B:59:0x019c, B:61:0x01a0, B:63:0x01a8, B:65:0x01cb, B:70:0x01db, B:72:0x01df, B:76:0x01e6, B:77:0x01f1, B:79:0x0212, B:81:0x021a, B:68:0x01d6, B:41:0x013a, B:43:0x0140, B:46:0x0150, B:48:0x0156, B:50:0x015c, B:34:0x00dd, B:29:0x00bb, B:20:0x008a, B:11:0x005a), top: B:98:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:81:0x021a A[Catch: Exception -> 0x0292, TRY_LEAVE, TryCatch #6 {Exception -> 0x0292, blocks: (B:3:0x000c, B:30:0x00c2, B:32:0x00c8, B:35:0x00ef, B:37:0x0125, B:39:0x012b, B:51:0x0168, B:54:0x0172, B:56:0x0193, B:59:0x019c, B:61:0x01a0, B:63:0x01a8, B:65:0x01cb, B:70:0x01db, B:72:0x01df, B:76:0x01e6, B:77:0x01f1, B:79:0x0212, B:81:0x021a, B:68:0x01d6, B:41:0x013a, B:43:0x0140, B:46:0x0150, B:48:0x0156, B:50:0x015c, B:34:0x00dd, B:29:0x00bb, B:20:0x008a, B:11:0x005a), top: B:98:0x000c }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void checkUp() throws java.lang.NumberFormatException {
        /*
            Method dump skipped, instructions count: 666
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.me.activity.SoftUpdateActivity.checkUp():void");
    }

    public void upVersion(String url) {
        showDialog();
        this.numberProgressBar.setMax(100);
        setDialogTitle(getString(R.string.upgrade_downing));
        DownloadUtil.getInstance().download(url, "health", new DownloadUtil.OnDownloadListener() { // from class: com.yucheng.smarthealthpro.me.activity.SoftUpdateActivity.5
            @Override // com.yucheng.smarthealthpro.utils.DownloadUtil.OnDownloadListener
            public void onDownloadSuccess() {
                SoftUpdateActivity.this.mFilePath = DownloadUtil.getInstance().filePath;
                Tools.saveFilePath(SoftUpdateActivity.this.mFilePath, SoftUpdateActivity.this);
                SoftUpdateActivity.this.handler.sendEmptyMessage(1002);
            }

            @Override // com.yucheng.smarthealthpro.utils.DownloadUtil.OnDownloadListener
            public void onDownloading(int progress) {
                Message message = new Message();
                message.what = 1001;
                message.obj = Integer.valueOf(progress);
                SoftUpdateActivity.this.handler.sendMessage(message);
            }

            @Override // com.yucheng.smarthealthpro.utils.DownloadUtil.OnDownloadListener
            public void onDownloadFailed() {
                SoftUpdateActivity.this.handler.sendEmptyMessage(1003);
            }
        });
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        HealthApplication.isUpgradeing = false;
        YCBTClient.setOta(false);
        dismissDialog();
        this.handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }

    public void onUploadClicked() throws IOException {
        showDialog();
        int i2 = this.type;
        if ((i2 == 0 || i2 == 3) && YCBTClient.isJieLi()) {
            this.numberProgressBar.setProgress(0);
            this.numberProgressBar.setMax(DfuConstants.MAX_NOTIFICATION_LOCK_WAIT_TIME);
        } else {
            this.numberProgressBar.setProgress(0);
            this.numberProgressBar.setMax(100);
        }
        setDialogTitle(getString(R.string.updating));
        int i3 = this.type;
        if (i3 == 0 || i3 == 3) {
            deviceUpgrade();
        } else {
            algorithmTpUpgrade();
        }
    }

    private void deviceUpgrade() {
        YCBTClient.upgradeFirmware(this, this.mSelectedDeviceMac, this.mSelectedDeviceName, this.mFilePath, new DfuCallBack() { // from class: com.yucheng.smarthealthpro.me.activity.SoftUpdateActivity.6
            @Override // com.yucheng.ycbtsdk.upgrade.DfuCallBack
            public void latest() {
            }

            @Override // com.yucheng.ycbtsdk.upgrade.DfuCallBack
            public void onNeedReconnect(String s, boolean b2) {
            }

            @Override // com.yucheng.ycbtsdk.upgrade.DfuCallBack
            public void progress(int progress) {
                Message message = new Message();
                message.what = 1004;
                message.obj = Integer.valueOf(progress);
                SoftUpdateActivity.this.handler.sendMessage(message);
            }

            @Override // com.yucheng.ycbtsdk.upgrade.DfuCallBack
            public void success() {
                SoftUpdateActivity.this.handler.sendEmptyMessageDelayed(1005, 200L);
            }

            @Override // com.yucheng.ycbtsdk.upgrade.DfuCallBack
            public void failed(String s) {
                SoftUpdateActivity.this.handler.sendEmptyMessageDelayed(1006, 200L);
            }

            @Override // com.yucheng.ycbtsdk.upgrade.DfuCallBack
            public void disconnect() {
                SoftUpdateActivity.this.handler.sendEmptyMessage(1007);
            }

            @Override // com.yucheng.ycbtsdk.upgrade.DfuCallBack
            public void connecting() {
                SoftUpdateActivity.this.handler.sendEmptyMessage(1008);
            }

            @Override // com.yucheng.ycbtsdk.upgrade.DfuCallBack
            public void connected() {
                SoftUpdateActivity.this.upgradeBean.data.upStatus = 20;
                SoftUpdateActivity softUpdateActivity = SoftUpdateActivity.this;
                softUpdateActivity.upgradeUpload(softUpdateActivity.upgradeBean);
            }

            @Override // com.yucheng.ycbtsdk.upgrade.DfuCallBack
            public void error(String s) {
                SoftUpdateActivity.this.handler.sendEmptyMessageDelayed(1009, 200L);
            }
        });
    }

    private void algorithmTpUpgrade() throws IOException {
        try {
            FileInputStream fileInputStream = new FileInputStream(this.mFilePath);
            byte[] bArr = new byte[1024];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (true) {
                int i2 = fileInputStream.read(bArr);
                if (i2 != -1) {
                    byteArrayOutputStream.write(bArr, 0, i2);
                } else {
                    byteArrayOutputStream.flush();
                    YCBTClient.otaDownload(1, this.type, byteArrayOutputStream.toByteArray(), new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.activity.SoftUpdateActivity$$ExternalSyntheticLambda0
                        @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                        public final void onDataResponse(int i3, float f2, HashMap map) {
                            this.f$0.lambda$algorithmTpUpgrade$0(i3, f2, map);
                        }
                    });
                    return;
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$algorithmTpUpgrade$0(int i2, float f2, HashMap map) {
        Log.w("otaDownload", "code=" + i2 + StringUtils.SPACE + new Gson().toJson(map));
        if (i2 == 0) {
            float fFloatValue = ((Float) map.get("progress")).floatValue();
            Message message = new Message();
            message.what = 1004;
            message.obj = Integer.valueOf((int) fFloatValue);
            this.handler.sendMessage(message);
            if (fFloatValue >= 100.0f) {
                this.handler.sendEmptyMessageDelayed(1005, 200L);
                return;
            }
            return;
        }
        this.handler.sendEmptyMessageDelayed(1006, 200L);
    }

    private void getDeviceTypeName() {
        YCBTClient.getDeviceName(new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.activity.SoftUpdateActivity.7
            @Override // com.yucheng.ycbtsdk.response.BleDataResponse
            public void onDataResponse(int i2, float v, HashMap hashMap) {
                String str;
                if (i2 != 0 || hashMap == null || (str = (String) hashMap.get("data")) == null) {
                    return;
                }
                SoftUpdateActivity.this.upgradeBean.data.deviceName = str;
                SoftUpdateActivity.this.checkVersion(str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getDeviceVersion() {
        YCBTClient.getDeviceInfo(new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.activity.SoftUpdateActivity.8
            @Override // com.yucheng.ycbtsdk.response.BleDataResponse
            public void onDataResponse(int i2, float v, HashMap hashMap) {
                if (i2 != 0 || hashMap == null || hashMap.get("data") == null || ((HashMap) hashMap.get("data")).get(Constant.SpConstKey.deviceVersion) == null) {
                    return;
                }
                final String str = (String) ((HashMap) hashMap.get("data")).get(Constant.SpConstKey.deviceVersion);
                String[] strArrSplit = str.split("\\.");
                SoftUpdateActivity.this.bNo = Integer.parseInt(strArrSplit[0]);
                SoftUpdateActivity.this.sNo = Integer.parseInt(strArrSplit[1]);
                SoftUpdateActivity.this.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.me.activity.SoftUpdateActivity.8.1
                    @Override // java.lang.Runnable
                    public void run() throws NumberFormatException {
                        SoftUpdateActivity.this.wversion.setText(str);
                        SoftUpdateActivity.this.checkUp();
                    }
                });
                Logger.d("chong------state==" + YCBTClient.getDeviceBatteryState() + "--value==" + YCBTClient.getDeviceBatteryValue());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void upgradeUpload(final UpgradeBean bean) {
        int i2 = this.type;
        if (i2 == 0 || i2 == 3) {
            HashMap map = new HashMap();
            map.put("mac", bean.data.mac);
            map.put(ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION, bean.data.version);
            map.put("upStatus", bean.data.upStatus + "");
            map.put(Constants.FunctionConstant.DEVICETYPE, "2--" + getAppInfo());
            map.put("deviceName", bean.data.deviceName);
            HttpUtils.getInstance().postMsgAsynHttp(this, com.yucheng.smarthealthpro.framework.util.Constants.UPMAC, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.me.activity.SoftUpdateActivity.9
                @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
                public void onSuccess(String result) {
                    if (result == null) {
                        return;
                    }
                    try {
                        SharedPreferencesUtils.put(SoftUpdateActivity.this, "isUpLoadUpgrade", Integer.valueOf(bean.data.upStatus));
                    } catch (Exception e2) {
                        CrashReport.postCatchedException(e2);
                        e2.printStackTrace();
                    }
                }
            });
            return;
        }
        SharedPreferencesUtils.put(this, Constant.SpConstKey.lastAlgorithmUpgradeTime, Long.valueOf(System.currentTimeMillis()));
    }

    private String getAppInfo() {
        try {
            return getPackageManager().getPackageInfo(getPackageName(), 0).versionName + "--" + Build.MODEL + "--" + Build.VERSION.RELEASE;
        } catch (Exception e2) {
            CrashReport.postCatchedException(e2);
            e2.printStackTrace();
            return "";
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDeviceBaseInfoData(EventBusMessageEvent messageEvent) throws IOException {
        if (messageEvent.connectState > 6 && YCBTClient.isJieLi() && YCBTClient.isForceOta() && !this.isStart) {
            this.isStart = true;
            onUploadClicked();
        }
        int i2 = messageEvent.belState;
        if (i2 != 0) {
            if (i2 == 1 && YCBTClient.isForceOta() && !isFinishing()) {
                onUploadClicked();
                return;
            }
            return;
        }
        if (YCBTClient.isJieLi()) {
            this.isStart = false;
        }
    }

    public String getMacAddOne(String mac) {
        String upperCase = Integer.toHexString((Integer.valueOf(mac.split(":")[mac.split(":").length - 1], 16).intValue() + 1) & 255).toUpperCase();
        StringBuilder sbAppend = new StringBuilder().append(mac.substring(0, mac.length() - 2));
        if (upperCase.length() != 2) {
            upperCase = "0" + upperCase;
        }
        return sbAppend.append(upperCase).toString();
    }

    private void showDialog() {
        if (isFinishing()) {
            return;
        }
        if (this.mLoading == null) {
            this.mLoading = (MyDialog) DialogUtils.createUpgradingDialog(this);
        }
        if (!this.mLoading.isShowing()) {
            this.mLoading.show();
        }
        NumberProgressBar numberProgressBar = this.numberProgressBar;
        if (numberProgressBar != null) {
            numberProgressBar.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDialogTitle(String title) {
        MyDialog myDialog = this.mLoading;
        if (myDialog != null) {
            myDialog.setTitle(title);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismissDialog() {
        MyDialog myDialog = this.mLoading;
        if (myDialog == null || !myDialog.isShowing()) {
            return;
        }
        this.mLoading.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initBattyDialog() {
        final CommonDialog commonDialog = new CommonDialog(this);
        commonDialog.setMessage(getString(R.string.update_batty_low)).setTitle(getString(R.string.prompt)).setSingle(true).setOnClickBottomListener(new CommonDialog.OnClickBottomListener() { // from class: com.yucheng.smarthealthpro.me.activity.SoftUpdateActivity.10
            @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
            public void onConfirmClick() {
                SoftUpdateActivity.this.finish();
                commonDialog.dismiss();
            }

            @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
            public void onCancelClick() {
                commonDialog.dismiss();
            }

            @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
            public void onEditTextConfirmClick(String mEditText) {
                commonDialog.dismiss();
            }
        }).show();
    }
}
