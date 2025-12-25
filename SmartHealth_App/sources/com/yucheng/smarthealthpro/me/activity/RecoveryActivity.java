package com.yucheng.smarthealthpro.me.activity;

import android.app.NotificationManager;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.PowerManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.dd.plist.NSDictionary;
import com.dd.plist.NSObject;
import com.dd.plist.PropertyListParser;
import com.orhanobut.logger.Logger;
import com.realsil.sdk.dfu.DfuConstants;
import com.yucheng.smarthealthpro.MyApplication;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.databinding.ActivityRecoveryBinding;
import com.yucheng.smarthealthpro.dialog.MyDialog;
import com.yucheng.smarthealthpro.framework.HealthApplication;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.framework.util.ToastUtil;
import com.yucheng.smarthealthpro.home.activity.DeviceListActivity;
import com.yucheng.smarthealthpro.home.view.CustomSelectors;
import com.yucheng.smarthealthpro.me.bean.UpgradeBean;
import com.yucheng.smarthealthpro.me.setting.dial.util.SystemUiUtil;
import com.yucheng.smarthealthpro.sport.view.CommonDialog;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.DialogUtils;
import com.yucheng.smarthealthpro.utils.DownloadUtil;
import com.yucheng.smarthealthpro.utils.EventBusMessageEvent;
import com.yucheng.smarthealthpro.utils.PermissionUtil;
import com.yucheng.smarthealthpro.utils.Tools;
import com.yucheng.smarthealthpro.utils.UpgradleUtil;
import com.yucheng.smarthealthpro.view.progress.NumberProgressBar;
import com.yucheng.ycbtsdk.Constants;
import com.yucheng.ycbtsdk.YCBTClient;
import com.yucheng.ycbtsdk.gatt.BleHelper;
import com.yucheng.ycbtsdk.response.BleDataResponse;
import com.yucheng.ycbtsdk.upgrade.DfuCallBack;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import kotlinx.coroutines.DebugKt;
import no.nordicsemi.android.dfu.DfuBaseService;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes5.dex */
public class RecoveryActivity extends BaseVbActivity<ActivityRecoveryBinding> {
    private static final int CONNECTED = 1005;
    private static final int CONNECTING = 1006;
    private static final int DISCONNECTED = 1004;
    private static final int ERROR = 1008;
    private static final int FAILED = 1003;
    private static final int LATEST = 1007;
    private static final int NO_FILE = 1009;
    private static final int SELECT_DEVICE_REQES = 2;
    private static final int SELECT_FILE_REQES = 1;
    private static final int SUCCESS = 1002;
    private static final int UPDATE = 1001;
    private int bNo;
    private String filePath;
    private TextView findtxt;
    private TextView firmware_url;
    private boolean isStart;
    private MyDialog mLoading;
    private String mSelectedDeviceMac;
    private String mSelectedDeviceName;
    private PowerManager.WakeLock mWakeLock;
    private NumberProgressBar numberbar;
    private PowerManager pManager;
    private int sNo;
    private String error = "";
    String mFilePath = "";
    private UpgradeBean upgradeBean = new UpgradeBean();
    private int currentIndex = 0;
    private boolean isStartRecovery = false;
    int connectCount = 0;
    Handler handler = new Handler(Looper.getMainLooper()) { // from class: com.yucheng.smarthealthpro.me.activity.RecoveryActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message msg) throws Resources.NotFoundException {
            if (RecoveryActivity.this.isFinishing()) {
            }
            switch (msg.what) {
                case 1001:
                    int iIntValue = ((Integer) msg.obj).intValue();
                    if (iIntValue >= 100 && !YCBTClient.isJieLi()) {
                        RecoveryActivity recoveryActivity = RecoveryActivity.this;
                        recoveryActivity.setDialogTitle(recoveryActivity.getString(R.string.update_success));
                        RecoveryActivity.this.numberbar.setProgress(100);
                        break;
                    } else if (YCBTClient.isJieLi() && iIntValue >= 10000) {
                        RecoveryActivity recoveryActivity2 = RecoveryActivity.this;
                        recoveryActivity2.setDialogTitle(recoveryActivity2.getString(R.string.update_success));
                        RecoveryActivity.this.numberbar.setProgress(DfuConstants.MAX_NOTIFICATION_LOCK_WAIT_TIME);
                        break;
                    } else {
                        RecoveryActivity recoveryActivity3 = RecoveryActivity.this;
                        recoveryActivity3.setDialogTitle(recoveryActivity3.getString(R.string.updating));
                        RecoveryActivity.this.numberbar.setProgress(iIntValue);
                        break;
                    }
                    break;
                case 1002:
                    Logger.d("升级成功");
                    ToastUtil.getInstance(RecoveryActivity.this.getApplicationContext()).toast(R.string.update_success);
                    RecoveryActivity recoveryActivity4 = RecoveryActivity.this;
                    recoveryActivity4.setDialogTitle(recoveryActivity4.getString(R.string.update_success));
                    RecoveryActivity.this.cancelNotification();
                    RecoveryActivity.this.updateUI();
                    break;
                case 1003:
                    RecoveryActivity recoveryActivity5 = RecoveryActivity.this;
                    recoveryActivity5.setDialogTitle(recoveryActivity5.getString(R.string.bluetooth_is_connecting));
                    RecoveryActivity.this.cancelNotification();
                    RecoveryActivity.this.onUploadClicked();
                    break;
                case 1004:
                    RecoveryActivity recoveryActivity6 = RecoveryActivity.this;
                    recoveryActivity6.setDialogTitle(recoveryActivity6.getString(R.string.disconnect_connection));
                    break;
                case 1005:
                    RecoveryActivity recoveryActivity7 = RecoveryActivity.this;
                    recoveryActivity7.setDialogTitle(recoveryActivity7.getString(R.string.connect_success));
                    break;
                case 1006:
                    RecoveryActivity recoveryActivity8 = RecoveryActivity.this;
                    recoveryActivity8.setDialogTitle(recoveryActivity8.getString(R.string.bluetooth_is_connecting));
                    break;
                case 1007:
                    if (YCBTClient.getChipScheme() == 4) {
                        ToastUtil.getInstance(RecoveryActivity.this).toast(RecoveryActivity.this.getString(R.string.the_latest_version));
                        RecoveryActivity.this.dismissLoading();
                        RecoveryActivity.this.numberbar.setVisibility(8);
                        RecoveryActivity.this.isStartRecovery = false;
                        RecoveryActivity.this.finish();
                        break;
                    }
                    break;
                case 1008:
                    RecoveryActivity recoveryActivity9 = RecoveryActivity.this;
                    recoveryActivity9.setDialogTitle(recoveryActivity9.getString(R.string.bluetooth_is_connecting));
                    RecoveryActivity.this.cancelNotification();
                    BleHelper.getHelper().disconnectGatt();
                    break;
                case 1009:
                    ToastUtil.getInstance(RecoveryActivity.this).toast(RecoveryActivity.this.getString(R.string.upgrading_failed));
                    RecoveryActivity.this.dismissLoading();
                    RecoveryActivity.this.numberbar.setVisibility(8);
                    break;
            }
        }
    };
    DfuCallBack dfuCallBack = new DfuCallBack() { // from class: com.yucheng.smarthealthpro.me.activity.RecoveryActivity.2
        @Override // com.yucheng.ycbtsdk.upgrade.DfuCallBack
        public void onNeedReconnect(String s, boolean b2) {
        }

        @Override // com.yucheng.ycbtsdk.upgrade.DfuCallBack
        public void progress(int progress) {
            Message message = new Message();
            message.what = 1001;
            message.obj = Integer.valueOf(progress);
            RecoveryActivity.this.handler.sendMessage(message);
        }

        @Override // com.yucheng.ycbtsdk.upgrade.DfuCallBack
        public void success() {
            RecoveryActivity.this.handler.sendEmptyMessageDelayed(1002, 500L);
        }

        @Override // com.yucheng.ycbtsdk.upgrade.DfuCallBack
        public void failed(String s) {
            if ("File exception.".equals(s)) {
                RecoveryActivity.this.handler.sendEmptyMessageDelayed(1009, 500L);
            } else if ("Same upgrade file.".equals(s)) {
                Log.e("ltf", "LATEST");
                RecoveryActivity.this.handler.sendEmptyMessageDelayed(1007, 500L);
            } else {
                RecoveryActivity.this.handler.sendEmptyMessageDelayed(1003, 500L);
            }
        }

        @Override // com.yucheng.ycbtsdk.upgrade.DfuCallBack
        public void disconnect() {
            RecoveryActivity.this.handler.sendEmptyMessage(1004);
        }

        @Override // com.yucheng.ycbtsdk.upgrade.DfuCallBack
        public void connecting() {
            RecoveryActivity.this.handler.sendEmptyMessage(1006);
        }

        @Override // com.yucheng.ycbtsdk.upgrade.DfuCallBack
        public void connected() {
            RecoveryActivity.this.handler.sendEmptyMessage(1005);
        }

        @Override // com.yucheng.ycbtsdk.upgrade.DfuCallBack
        public void latest() {
            Log.e("ltf", "LATEST");
            RecoveryActivity.this.handler.sendEmptyMessage(1007);
        }

        @Override // com.yucheng.ycbtsdk.upgrade.DfuCallBack
        public void error(String s) {
            RecoveryActivity.this.error = s;
            RecoveryActivity.this.handler.sendEmptyMessage(1008);
        }
    };

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        getDeviceTypeName();
    }

    private void initView() {
        HealthApplication.isUpgradeing = true;
        YCBTClient.setOta(true);
        changeTitle(getString(R.string.me_about_us_dfu));
        showBack();
        this.numberbar = (NumberProgressBar) findViewById(R.id.numberbar);
        this.findtxt = (TextView) findViewById(R.id.findtxt);
        this.firmware_url = (TextView) findViewById(R.id.file_path_txt);
        findViewById(R.id.seldev).setOnClickListener(new OnClickListenerImpl());
        findViewById(R.id.rectxt).setOnClickListener(new OnClickListenerImpl());
        findViewById(R.id.sel_file_path).setOnClickListener(new OnClickListenerImpl());
    }

    private void initData() {
        EventBus.getDefault().register(this);
        this.mLoading = (MyDialog) DialogUtils.createUpgradingDialog(this);
        this.mSelectedDeviceMac = YCBTClient.getBindDeviceMac();
        if (YCBTClient.isForceOta()) {
            this.mSelectedDeviceMac = getMacAddOne(this.mSelectedDeviceMac);
        }
        if (!TextUtils.isEmpty(this.mSelectedDeviceMac)) {
            this.mSelectedDeviceName = YCBTClient.getBindDeviceName();
        }
        setDeviceInfo();
        if (getIntent() != null && getIntent().getBooleanExtra(DebugKt.DEBUG_PROPERTY_VALUE_AUTO, false)) {
            onUploadClicked();
        }
        initFilePath();
    }

    public static String getMacAddOne(String mac) {
        String upperCase = Integer.toHexString((Integer.valueOf(mac.split(":")[mac.split(":").length - 1], 16).intValue() + 1) & 255).toUpperCase();
        StringBuilder sbAppend = new StringBuilder().append(mac.substring(0, mac.length() - 2));
        if (upperCase.length() != 2) {
            upperCase = "0" + upperCase;
        }
        return sbAppend.append(upperCase).toString();
    }

    private void initFilePath() {
        this.firmware_url.setText("");
        this.filePath = null;
        String str = (String) SharedPreferencesUtils.get(getApplicationContext(), Constant.SpConstKey.FIRM_WARE_FILE_CURRENT, "");
        String str2 = (String) YCBTClient.readDeviceInfo(Constants.FunctionConstant.DEVICETYPE);
        try {
            if (!TextUtils.isEmpty(str) && str.contains(str2)) {
                File file = new File(str);
                if (file.exists() && file.isFile()) {
                    this.filePath = str;
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (TextUtils.isEmpty(this.filePath)) {
            return;
        }
        this.firmware_url.setText(this.filePath);
    }

    private void setDeviceInfo() {
        String str = this.mSelectedDeviceName;
        if (str != null && !str.isEmpty()) {
            this.findtxt.setText(this.mSelectedDeviceName);
        } else if (BluetoothAdapter.checkBluetoothAddress(this.mSelectedDeviceMac)) {
            this.findtxt.setText(this.mSelectedDeviceMac);
        }
    }

    @Override // com.yucheng.smarthealthpro.framework.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        if (this.pManager == null || this.mWakeLock == null) {
            PowerManager powerManager = (PowerManager) getSystemService("power");
            this.pManager = powerManager;
            this.mWakeLock = powerManager.newWakeLock(536870922, "softup");
        }
        PowerManager.WakeLock wakeLock = this.mWakeLock;
        if (wakeLock == null || wakeLock.isHeld()) {
            return;
        }
        this.mWakeLock.acquire();
    }

    @Override // com.yucheng.smarthealthpro.framework.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        PowerManager.WakeLock wakeLock = this.mWakeLock;
        if (wakeLock == null || !wakeLock.isHeld()) {
            return;
        }
        this.mWakeLock.release();
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        HealthApplication.isUpgradeing = false;
        YCBTClient.setOta(false);
        this.handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }

    public void onUploadClicked() {
        this.isStartRecovery = true;
        Log.e("ltf", "onUploadClicked");
        showLoading();
        this.numberbar.setProgress(0);
        if (YCBTClient.isJieLi()) {
            this.numberbar.setMax(DfuConstants.MAX_NOTIFICATION_LOCK_WAIT_TIME);
        } else {
            this.numberbar.setMax(100);
        }
        this.numberbar.setVisibility(0);
        YCBTClient.upgradeFirmware(this, this.mSelectedDeviceMac, this.mSelectedDeviceName, this.filePath, this.dfuCallBack);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode != 0 || intent == null) {
            return;
        }
        if (requestCode == 1) {
            if (intent.getStringExtra("url") != null) {
                String stringExtra = intent.getStringExtra("url");
                this.filePath = stringExtra;
                this.firmware_url.setText(stringExtra);
                return;
            }
            return;
        }
        if (requestCode == 2 && intent.getStringExtra("deviceName") != null) {
            this.mSelectedDeviceName = intent.getStringExtra("deviceName");
            this.mSelectedDeviceMac = intent.getStringExtra("deviceMac");
            initFilePath();
            setDeviceInfo();
        }
    }

    private String getMacSubOne(String mac) {
        try {
            String upperCase = Integer.toHexString((Integer.valueOf(mac.split(":")[mac.split(":").length - 1], 16).intValue() - 1) & 255).toUpperCase();
            StringBuilder sbAppend = new StringBuilder().append(mac.substring(0, mac.length() - 2));
            if (upperCase.length() != 2) {
                upperCase = "0" + upperCase;
            }
            return sbAppend.append(upperCase).toString();
        } catch (Exception e2) {
            e2.printStackTrace();
            return mac;
        }
    }

    private class OnClickListenerImpl implements View.OnClickListener {
        private OnClickListenerImpl() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            if (v.getId() == R.id.seldev) {
                RecoveryActivity.this.startActivityForResult(new Intent(RecoveryActivity.this.context, (Class<?>) DeviceListActivity.class).putExtra("recovery", true), 2);
                return;
            }
            if (v.getId() == R.id.rectxt) {
                if (RecoveryActivity.this.checked()) {
                    RecoveryActivity.this.onUploadClicked();
                }
            } else if (v.getId() == R.id.sel_file_path) {
                RecoveryActivity.this.showFilePick();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showFilePick() {
        CustomSelectors customSelectors = new CustomSelectors();
        String str = (String) SharedPreferencesUtils.get(getApplicationContext(), Constant.SpConstKey.FIRM_WARE_FILE, "");
        final ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(str)) {
            arrayList.addAll(Arrays.asList(str.split(",")));
        }
        String filePath = Tools.getFilePath(getApplicationContext());
        if (arrayList.size() == 0 && TextUtils.isEmpty(filePath)) {
            return;
        }
        if (!TextUtils.isEmpty(filePath)) {
            arrayList.add(filePath);
        }
        ArrayList arrayList2 = new ArrayList();
        String str2 = (String) YCBTClient.readDeviceInfo(Constants.FunctionConstant.DEVICETYPE);
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            String str3 = (String) arrayList.get(i2);
            int iLastIndexOf = str3.lastIndexOf("/");
            if (iLastIndexOf != -1 && str3.contains(str2)) {
                String strSubstring = str3.substring(iLastIndexOf + 1);
                if (strSubstring.startsWith(str2)) {
                    arrayList2.add(strSubstring);
                }
            }
            if (str3.equals(this.filePath)) {
                this.currentIndex = i2;
            }
        }
        if (arrayList2.size() > 0) {
            customSelectors.BpLevelPicker(arrayList2, null, null, this.currentIndex, 1, 1, "", "", "", false, CustomSelectors.IsShow.TOP_CONFIRM_CANCEL, CustomSelectors.SelectorsDataNum.ONE, this.context);
            customSelectors.setOnOneSelectorsDataListener(new CustomSelectors.OnOneSelectorsDataListener() { // from class: com.yucheng.smarthealthpro.me.activity.RecoveryActivity.3
                @Override // com.yucheng.smarthealthpro.home.view.CustomSelectors.OnOneSelectorsDataListener
                public void getSelectorsDataClick(String oneValue, int optionsOne) {
                    RecoveryActivity.this.filePath = (String) arrayList.get(optionsOne);
                    RecoveryActivity.this.firmware_url.setText(RecoveryActivity.this.filePath);
                    RecoveryActivity.this.currentIndex = optionsOne;
                }
            });
        }
    }

    public boolean checkBattery() throws Throwable {
        int deviceBatteryState = YCBTClient.getDeviceBatteryState();
        int deviceBatteryValue = YCBTClient.getDeviceBatteryValue();
        long fileSize = getFileSize(new File(this.filePath));
        if (fileSize > 8388608) {
            if (deviceBatteryState == 2 || deviceBatteryValue >= 50) {
                return true;
            }
            initBattyDialog();
            return false;
        }
        if (fileSize > 1048576) {
            if (deviceBatteryState == 2 || deviceBatteryValue >= 40) {
                return true;
            }
            initBattyDialog();
            return false;
        }
        if (deviceBatteryState == 2 || deviceBatteryValue >= 30) {
            return true;
        }
        initBattyDialog();
        return false;
    }

    public static long getFileSize(File file) throws Throwable {
        if (file.exists()) {
            FileInputStream fileInputStream = null;
            try {
                try {
                    FileInputStream fileInputStream2 = new FileInputStream(file);
                    try {
                        long jAvailable = fileInputStream2.available();
                        try {
                            fileInputStream2.close();
                            return jAvailable;
                        } catch (IOException e2) {
                            e2.printStackTrace();
                            return jAvailable;
                        }
                    } catch (IOException e3) {
                        e = e3;
                        fileInputStream = fileInputStream2;
                        e.printStackTrace();
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                        return 0L;
                    } catch (Throwable th) {
                        th = th;
                        fileInputStream = fileInputStream2;
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e5) {
                                e5.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (IOException e6) {
                    e = e6;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
        return 0L;
    }

    private void initBattyDialog() {
        final CommonDialog commonDialog = new CommonDialog(this);
        commonDialog.setMessage(getString(R.string.update_batty_low)).setTitle(getString(R.string.prompt)).setSingle(true).setOnClickBottomListener(new CommonDialog.OnClickBottomListener() { // from class: com.yucheng.smarthealthpro.me.activity.RecoveryActivity.4
            @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
            public void onConfirmClick() {
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

    /* JADX INFO: Access modifiers changed from: private */
    public boolean checked() {
        if (!BluetoothAdapter.checkBluetoothAddress(this.mSelectedDeviceMac)) {
            ToastUtil.getInstance(this).toast(getString(R.string.please_select_device));
        } else {
            String str = this.filePath;
            if (str != null && !str.isEmpty()) {
                return true;
            }
            ToastUtil.getInstance(this).toast(getString(R.string.please_select_file));
        }
        return false;
    }

    private boolean hasPermission() {
        if (Build.VERSION.SDK_INT >= 30 && !Environment.isExternalStorageManager()) {
            ToastUtil.getInstance(this).toast(getString(R.string.recoverry_browse_files));
            requestPermission();
            return false;
        }
        return PermissionUtil.openSDCardPermission(this);
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= 30) {
            try {
                Intent intent = new Intent("android.settings.MANAGE_APP_ALL_FILES_ACCESS_PERMISSION");
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setData(Uri.parse(String.format("package:%s", getApplicationContext().getPackageName())));
                startActivity(intent);
            } catch (Exception unused) {
                Intent intent2 = new Intent();
                intent2.setAction("android.settings.MANAGE_ALL_FILES_ACCESS_PERMISSION");
                startActivity(intent2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cancelNotification() {
        NotificationManager notificationManager;
        if (YCBTClient.getChipScheme() != 0 || (notificationManager = (NotificationManager) getSystemService("notification")) == null) {
            return;
        }
        notificationManager.cancel(283);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateUI() {
        MyApplication.is_auto_upgrade = false;
        UpgradeBean upgradeBean = new UpgradeBean();
        String str = this.mSelectedDeviceName;
        if (str != null && str.toLowerCase(Locale.ROOT).contains(DfuBaseService.NOTIFICATION_CHANNEL_DFU)) {
            this.mSelectedDeviceMac = getMacSubOne(this.mSelectedDeviceMac);
        }
        upgradeBean.data.mac = this.mSelectedDeviceMac;
        upgradeBean.data.version = YCBTClient.getBindDeviceVersion();
        upgradeBean.data.upStatus = 10;
        UpgradleUtil.upgradeUpload(upgradeBean, this);
        dismissLoading();
        this.isStartRecovery = false;
        HealthApplication.isUpgradeing = false;
        YCBTClient.setOta(false);
        finish();
    }

    private void showLoading() {
        if (isDestroyed()) {
            return;
        }
        if (this.mLoading == null) {
            this.mLoading = (MyDialog) DialogUtils.createUpgradingDialog(this);
        }
        if (this.mLoading.isShowing()) {
            return;
        }
        this.mLoading.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDialogTitle(String title) {
        MyDialog myDialog = this.mLoading;
        if (myDialog != null) {
            myDialog.setTitle(title);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismissLoading() {
        MyDialog myDialog = this.mLoading;
        if (myDialog == null || !myDialog.isShowing() || isDestroyed()) {
            return;
        }
        this.mLoading.dismiss();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDeviceBaseInfoData(EventBusMessageEvent messageEvent) {
        if (!this.isStartRecovery || isDestroyed()) {
            return;
        }
        Log.e("ltf", "onDeviceBaseInfoData");
        if (messageEvent.connectState > 6 && YCBTClient.getChipScheme() == 4 && YCBTClient.isForceOta() && !this.isStart) {
            this.isStart = true;
            Log.e("ltf", "postDelayUpload  isStart = true");
            postDelayUpload();
        }
        int i2 = messageEvent.belState;
        if (i2 == 0) {
            if (this.connectCount > 2) {
                this.connectCount = 0;
            }
            if (YCBTClient.isJieLi()) {
                this.isStart = false;
                return;
            }
            return;
        }
        if (i2 != 1) {
            return;
        }
        this.connectCount++;
        if (!YCBTClient.isJieLi() && YCBTClient.isForceOta()) {
            Log.e("ltf", "!YCBTClient.isJieLi()&&YCBTClient.isForceOta()");
            postDelayUpload();
        }
        if (YCBTClient.getChipScheme() != 3 || this.isStart) {
            return;
        }
        this.isStart = true;
        Log.e("ltf", "YCBTClient.getChipScheme() == com.yucheng.ycbtsdk.Constants.Platform.JieLi");
        postDelayUpload();
    }

    public void postDelayUpload() {
        this.handler.postDelayed(new Runnable() { // from class: com.yucheng.smarthealthpro.me.activity.RecoveryActivity.5
            @Override // java.lang.Runnable
            public void run() {
                RecoveryActivity.this.onUploadClicked();
            }
        }, 2000L);
    }

    private void getDeviceTypeName() {
        if (YCBTClient.connectState() == 10) {
            YCBTClient.getDeviceName(new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.activity.RecoveryActivity.6
                @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                public void onDataResponse(int i2, float v, HashMap hashMap) {
                    String str;
                    if (i2 != 0 || hashMap == null || (str = (String) hashMap.get("data")) == null) {
                        return;
                    }
                    RecoveryActivity.this.upgradeBean.data.deviceName = str;
                    RecoveryActivity.this.checkVersion(str);
                }
            });
        } else {
            checkVersion((String) YCBTClient.readDeviceInfo(Constants.FunctionConstant.DEVICETYPE));
        }
    }

    public void checkVersion(String devname) {
        String bindDeviceVersion = YCBTClient.getBindDeviceVersion();
        if (bindDeviceVersion != null && bindDeviceVersion.contains(".")) {
            String[] strArrSplit = bindDeviceVersion.split("\\.");
            try {
                this.bNo = Integer.parseInt(strArrSplit[0]);
                this.sNo = Integer.parseInt(strArrSplit[1]);
                Tools.saveFirmwareVersion(this, "" + this.bNo + "." + this.sNo);
            } catch (NumberFormatException e2) {
                e2.printStackTrace();
                return;
            }
        }
        if ("E66B".equals(devname) && this.bNo == 0 && this.sNo == 92) {
            devname = "E66C";
        }
        DownloadUtil.getInstance().download(com.yucheng.smarthealthpro.framework.util.Constants.getFirmwareurl() + devname + ".plist", "health", new DownloadUtil.OnDownloadListener() { // from class: com.yucheng.smarthealthpro.me.activity.RecoveryActivity.7
            @Override // com.yucheng.smarthealthpro.utils.DownloadUtil.OnDownloadListener
            public void onDownloadFailed() {
            }

            @Override // com.yucheng.smarthealthpro.utils.DownloadUtil.OnDownloadListener
            public void onDownloading(int progress) {
            }

            @Override // com.yucheng.smarthealthpro.utils.DownloadUtil.OnDownloadListener
            public void onDownloadSuccess() {
                Logger.d("plist onDownloadSuccess");
                RecoveryActivity.this.getDeviceVersion();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getDeviceVersion() {
        YCBTClient.getDeviceInfo(new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.activity.RecoveryActivity.8
            @Override // com.yucheng.ycbtsdk.response.BleDataResponse
            public void onDataResponse(int i2, float v, HashMap hashMap) {
                if (i2 != 0 || hashMap == null || hashMap.get("data") == null || ((HashMap) hashMap.get("data")).get(Constant.SpConstKey.deviceVersion) == null) {
                    return;
                }
                String[] strArrSplit = ((String) ((HashMap) hashMap.get("data")).get(Constant.SpConstKey.deviceVersion)).split("\\.");
                RecoveryActivity.this.bNo = Integer.parseInt(strArrSplit[0]);
                RecoveryActivity.this.sNo = Integer.parseInt(strArrSplit[1]);
                RecoveryActivity.this.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.me.activity.RecoveryActivity.8.1
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            NSObject nSObject = ((NSDictionary) PropertyListParser.parse(new File(DownloadUtil.getInstance().filePath))).get((Object) "url");
                            if (nSObject != null) {
                                RecoveryActivity.this.upVersion(nSObject.toJavaObject().toString());
                            }
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                });
                Logger.d("chong------state==" + YCBTClient.getDeviceBatteryState() + "--value==" + YCBTClient.getDeviceBatteryValue());
            }
        });
    }

    public void upVersion(String url) {
        String str = (String) SharedPreferencesUtils.get(getApplicationContext(), Constant.SpConstKey.FIRM_WARE_FILE, "");
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(str)) {
            arrayList.addAll(Arrays.asList(str.split(",")));
        }
        if (new File(SystemUiUtil.isExistDir("health"), DownloadUtil.getNameFromUrl(url)).exists()) {
            return;
        }
        DownloadUtil.getInstance().download(url, "health", new DownloadUtil.OnDownloadListener() { // from class: com.yucheng.smarthealthpro.me.activity.RecoveryActivity.9
            @Override // com.yucheng.smarthealthpro.utils.DownloadUtil.OnDownloadListener
            public void onDownloadFailed() {
            }

            @Override // com.yucheng.smarthealthpro.utils.DownloadUtil.OnDownloadListener
            public void onDownloadSuccess() {
                String str2 = DownloadUtil.getInstance().filePath;
                Tools.saveFilePath(str2, RecoveryActivity.this.getApplicationContext());
                String str3 = (String) SharedPreferencesUtils.get(RecoveryActivity.this.getApplicationContext(), Constant.SpConstKey.FIRM_WARE_FILE, "");
                ArrayList arrayList2 = new ArrayList();
                if (!TextUtils.isEmpty(str3)) {
                    arrayList2.addAll(Arrays.asList(str3.split(",")));
                }
                if (!arrayList2.contains(str2)) {
                    arrayList2.add(str2);
                }
                SharedPreferencesUtils.put(RecoveryActivity.this.getApplicationContext(), Constant.SpConstKey.FIRM_WARE_FILE, String.join(",", arrayList2));
            }

            @Override // com.yucheng.smarthealthpro.utils.DownloadUtil.OnDownloadListener
            public void onDownloading(int progress) {
                Logger.d("onDownloading progress=" + progress);
            }
        });
    }
}
