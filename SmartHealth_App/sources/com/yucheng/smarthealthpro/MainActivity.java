package com.yucheng.smarthealthpro;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.work.WorkManager;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.autonavi.amap.mapcore.AMapEngineUtils;
import com.dd.plist.NSDictionary;
import com.dd.plist.PropertyListParser;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.tencent.bugly.crashreport.CrashReport;
import com.yanzhenjie.permission.Permission;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.care.bean.UploadFileTypeBean;
import com.yucheng.smarthealthpro.databinding.ActivityMainBinding;
import com.yucheng.smarthealthpro.framework.HealthApplication;
import com.yucheng.smarthealthpro.framework.http.HttpUtils;
import com.yucheng.smarthealthpro.framework.util.Constants;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.framework.util.SubObserver;
import com.yucheng.smarthealthpro.framework.util.ToastUtil;
import com.yucheng.smarthealthpro.home.bean.DeviceUpgradeInfo;
import com.yucheng.smarthealthpro.home.bean.ToAppDataResponse;
import com.yucheng.smarthealthpro.home.fragment.HomeFragment;
import com.yucheng.smarthealthpro.home.fragment.HomeFragment2;
import com.yucheng.smarthealthpro.home.view.NoScrollViewPager;
import com.yucheng.smarthealthpro.login.LoginActivity;
import com.yucheng.smarthealthpro.me.activity.PermissionActivity;
import com.yucheng.smarthealthpro.me.activity.RecoveryActivity;
import com.yucheng.smarthealthpro.me.activity.SoftUpdateActivity;
import com.yucheng.smarthealthpro.me.bean.UpgradeBean;
import com.yucheng.smarthealthpro.me.setting.camera.CameraActivity;
import com.yucheng.smarthealthpro.perfect.UserInfoActivity;
import com.yucheng.smarthealthpro.service.MyBleService;
import com.yucheng.smarthealthpro.sport.activity.SportRunningActivity;
import com.yucheng.smarthealthpro.sport.view.CommonDialog;
import com.yucheng.smarthealthpro.sport.viewmodel.SportViewModel;
import com.yucheng.smarthealthpro.sport.weathers.WeatherUtils;
import com.yucheng.smarthealthpro.tasks.BatteryTask;
import com.yucheng.smarthealthpro.tasks.TimeUploadTask;
import com.yucheng.smarthealthpro.utils.CommonAction;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.DialogUtils;
import com.yucheng.smarthealthpro.utils.DownloadUtil;
import com.yucheng.smarthealthpro.utils.EventBusEcgEnd;
import com.yucheng.smarthealthpro.utils.EventBusExitExerciseEvent;
import com.yucheng.smarthealthpro.utils.EventBusMessageEvent;
import com.yucheng.smarthealthpro.utils.EventBusTakePhotoEvent;
import com.yucheng.smarthealthpro.utils.HangUpTelephonyUtil;
import com.yucheng.smarthealthpro.utils.JxdUtils;
import com.yucheng.smarthealthpro.utils.MainVpAdapter;
import com.yucheng.smarthealthpro.utils.MultiLanguageUtils;
import com.yucheng.smarthealthpro.utils.PermissionUtil;
import com.yucheng.smarthealthpro.utils.ShareUtils;
import com.yucheng.smarthealthpro.utils.SoundPoolUtil;
import com.yucheng.smarthealthpro.utils.Tools;
import com.yucheng.smarthealthpro.utils.UpdateVersionUtil;
import com.yucheng.smarthealthpro.utils.VersionUtilsKt;
import com.yucheng.ycbtsdk.YCBTClient;
import com.yucheng.ycbtsdk.gatt.Reconnect;
import com.yucheng.ycbtsdk.response.BleDataResponse;
import com.yucheng.ycbtsdk.response.BleDeviceToAppDataResponse;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes3.dex */
public class MainActivity extends BaseVbActivity<ActivityMainBinding> implements BottomNavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener, Observer {
    public static final String TAG = "MainActivity";
    private String algorithm_autoUpdateVersion;
    private String autoUpdateVersion;
    private int bNo;
    CommonDialog blueToothDialog;
    GpsSwitchStateReceiver gpsSwitchStateReceiver;
    HomeFragment homeFragment;
    HomeFragment2 homeFragment2;
    BottomNavigationView mBottomNavigationView;
    private List<Fragment> mFragmentList;
    private MainVpAdapter mMainVpAdapter;
    SpinKitView mSpinKitView;
    NoScrollViewPager mViewPager;
    private String path;
    RelativeLayout rvDialog;
    private int sNo;
    private String tempPath;
    CommonDialog tipDialog;
    private String tp_autoUpdateVersion;
    public boolean isNeedStopStep = true;
    private int[] bottomArr = {R.id.menu_home, R.id.menu_sport, R.id.menu_wisdom, R.id.menu_care, R.id.menu_me};
    private int[] bottomArr2 = {R.id.menu_home, R.id.menu_care, R.id.menu_wisdom, R.id.menu_sport, R.id.menu_me};
    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    int max = 35000;
    int current = 0;
    private int isSync = 0;
    private boolean isResume = false;
    public boolean isShowBluetoothDialog = false;
    boolean isSendWeather = false;
    boolean isFirst = true;
    private SportViewModel mSportViewModel = null;
    boolean isFirstNofity = true;
    private Handler handler = new Handler(new Handler.Callback() { // from class: com.yucheng.smarthealthpro.MainActivity.1
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message msg) {
            return false;
        }
    });
    Runnable runnable = new Runnable() { // from class: com.yucheng.smarthealthpro.MainActivity.2
        @Override // java.lang.Runnable
        public void run() {
            SoundPoolUtil.getInstance(MainActivity.this).stop();
        }
    };
    Reconnect.ReconnectResponse reconnectResponse = new Reconnect.ReconnectResponse() { // from class: com.yucheng.smarthealthpro.MainActivity.4
        @Override // com.yucheng.ycbtsdk.gatt.Reconnect.ReconnectResponse
        public void onReconnectFail() {
            if (MainActivity.this.isResume) {
                MainActivity.this.showBluetoothDialog();
            } else {
                MainActivity.this.isShowBluetoothDialog = true;
            }
        }
    };
    Runnable checkSendFisnish = new Runnable() { // from class: com.yucheng.smarthealthpro.MainActivity.13
        @Override // java.lang.Runnable
        public void run() {
            if (YCBTClient.getQueueSize() > 0 && MainActivity.this.current < MainActivity.this.max) {
                MainActivity.this.current += 1000;
                MainActivity.this.handler.postDelayed(MainActivity.this.checkSendFisnish, 1000L);
            } else {
                MainActivity.this.dismissProgressDialog();
                MainActivity.this.homeFragment.syncFinish();
            }
        }
    };

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i2) {
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i2, float v, int i1) {
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        SoundPoolUtil.getInstance(this);
        SubObserver.getInstance().addObs(this);
        EventBus.getDefault().register(this);
        Intent intent = new Intent(this, (Class<?>) MyBleService.class);
        intent.addFlags(AMapEngineUtils.MAX_P20_WIDTH);
        startForegroundService(intent);
        if (Constant.isTechFeel() && !((Boolean) SharedPreferencesUtils.get(this, "", false)).booleanValue()) {
            SharedPreferencesUtils.put(this, "", true);
            showPermissionDialog();
        }
        checkAPPPermision();
        this.gpsSwitchStateReceiver = new GpsSwitchStateReceiver();
        IntentFilter intentFilter = new IntentFilter("android.location.PROVIDERS_CHANGED");
        if (Build.VERSION.SDK_INT >= 33) {
            registerReceiver(this.gpsSwitchStateReceiver, intentFilter, 2);
        } else {
            registerReceiver(this.gpsSwitchStateReceiver, intentFilter);
        }
        Reconnect.getInstance().registerReconnectResponse(this.reconnectResponse);
        initShare(getIntent());
        if (YCBTClient.connectState() == 10) {
            EventBusMessageEvent eventBusMessageEvent = new EventBusMessageEvent();
            eventBusMessageEvent.belState = 1;
            getCompile(eventBusMessageEvent);
        }
        WorkManager.getInstance(this.context).cancelAllWork();
        TimeUploadTask.INSTANCE.request(this);
        if (Constant.isHealthWear()) {
            BatteryTask.INSTANCE.request(getActivity());
        }
        initViewModel();
    }

    private void initViewModel() {
        this.mSportViewModel = (SportViewModel) MyApplication.sInstance.getAppViewModel(SportViewModel.class);
    }

    private void checkAPPPermision() {
        if (TextUtils.isEmpty(YCBTClient.getBindDeviceMac())) {
            return;
        }
        final String[] strArr = new String[2];
        strArr[0] = Permission.ACCESS_FINE_LOCATION;
        strArr[1] = Build.VERSION.SDK_INT >= 29 ? "android.permission.ACCESS_BACKGROUND_LOCATION" : Permission.ACCESS_COARSE_LOCATION;
        boolean zIsPermission = PermissionUtil.isPermission(this, strArr);
        boolean zBooleanValue = ((Boolean) SharedPreferencesUtils.get(getApplicationContext(), Constant.SpConstKey.IS_SHOW_LOCATION_PERMISSION_DIALOG, true)).booleanValue();
        if (!zIsPermission && zBooleanValue && this.isResume) {
            DialogUtils.showPromptDialog(this, getString(R.string.location_permission_prompt_content), new DialogInterface.OnClickListener() { // from class: com.yucheng.smarthealthpro.MainActivity$$ExternalSyntheticLambda1
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i2) {
                    this.f$0.lambda$checkAPPPermision$0(strArr, dialogInterface, i2);
                }
            });
            SharedPreferencesUtils.put(getApplicationContext(), Constant.SpConstKey.IS_SHOW_LOCATION_PERMISSION_DIALOG, false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$checkAPPPermision$0(String[] strArr, DialogInterface dialogInterface, int i2) {
        PermissionUtil.openPermission(this, strArr);
        dialogInterface.dismiss();
    }

    private void getProp() {
        try {
            if (Constant.isSmartHealth() || Constant.isHealthRing() || Constant.isHealthWear()) {
                HttpUtils.getInstance().getMsgAsynHttp(this, Constants.Props, null, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.MainActivity.3
                    @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
                    public void onSuccess(String result) {
                        try {
                            Log.d("HttpUtils", "upload=" + result);
                            SharedPreferencesUtils.put(MainActivity.this.getApplicationContext(), Constant.SpConstKey.Props, result);
                            UploadFileTypeBean uploadFileTypeBean = (UploadFileTypeBean) new Gson().fromJson(result, UploadFileTypeBean.class);
                            String packageName = MainActivity.this.context.getPackageName();
                            boolean z = false;
                            for (int i2 = 0; i2 < uploadFileTypeBean.getData().getAppBeta().Android.size(); i2++) {
                                UploadFileTypeBean.AppInfo appInfo = uploadFileTypeBean.getData().getAppBeta().Android.get(i2);
                                if (packageName.equals(appInfo.bundleID)) {
                                    z = appInfo.vestBag;
                                }
                            }
                            SharedPreferencesUtils.put(MainActivity.this.getApplicationContext(), Constant.SpConstKey.vestBag, Boolean.valueOf(z));
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                });
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            CrashReport.postCatchedException(e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showBluetoothDialog() {
        if (YCBTClient.connectState() == 10) {
            return;
        }
        this.isShowBluetoothDialog = false;
        this.handler.post(new Runnable() { // from class: com.yucheng.smarthealthpro.MainActivity.5
            @Override // java.lang.Runnable
            public void run() {
                MainActivity.this.blueToothDialog = new CommonDialog(MainActivity.this);
                MainActivity.this.blueToothDialog.setMessage(MainActivity.this.getString(R.string.reboot_bluetooth_tip)).setTitle(MainActivity.this.getString(R.string.prompt)).setSingle(true).setOnClickBottomListener(new CommonDialog.OnClickBottomListener() { // from class: com.yucheng.smarthealthpro.MainActivity.5.1
                    @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
                    public void onConfirmClick() {
                        MainActivity.this.blueToothDialog.dismiss();
                        MainActivity.this.blueToothDialog = null;
                    }

                    @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
                    public void onCancelClick() {
                        MainActivity.this.blueToothDialog.dismiss();
                        MainActivity.this.blueToothDialog = null;
                    }

                    @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
                    public void onEditTextConfirmClick(String mEditText) {
                        MainActivity.this.blueToothDialog.dismiss();
                        MainActivity.this.blueToothDialog = null;
                    }
                }).show();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showTipDialog(final String tip) {
        this.handler.post(new Runnable() { // from class: com.yucheng.smarthealthpro.MainActivity.6
            @Override // java.lang.Runnable
            public void run() {
                if (MainActivity.this.tipDialog != null) {
                    MainActivity.this.tipDialog.dismiss();
                    MainActivity.this.tipDialog = null;
                }
                MainActivity.this.tipDialog = new CommonDialog(MainActivity.this);
                MainActivity.this.tipDialog.setMessage(tip).setTitle(MainActivity.this.getString(R.string.prompt)).setSingle(true).setOnClickBottomListener(new CommonDialog.OnClickBottomListener() { // from class: com.yucheng.smarthealthpro.MainActivity.6.1
                    @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
                    public void onConfirmClick() {
                        MainActivity.this.tipDialog.dismiss();
                        MainActivity.this.tipDialog = null;
                    }

                    @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
                    public void onCancelClick() {
                        MainActivity.this.tipDialog.dismiss();
                        MainActivity.this.tipDialog = null;
                    }

                    @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
                    public void onEditTextConfirmClick(String mEditText) {
                        MainActivity.this.tipDialog.dismiss();
                        MainActivity.this.tipDialog = null;
                    }
                }).show();
            }
        });
    }

    private void showPermissionDialog() {
        DialogUtils.showPromptDialog(this, getString(R.string.hint_first_permission_open), new DialogInterface.OnClickListener() { // from class: com.yucheng.smarthealthpro.MainActivity$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                this.f$0.lambda$showPermissionDialog$1(dialogInterface, i2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showPermissionDialog$1(DialogInterface dialogInterface, int i2) {
        startActivity(new Intent(this, (Class<?>) PermissionActivity.class));
        dialogInterface.dismiss();
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        checkLocale();
        this.isResume = true;
        deviceToApp();
        if (!Constant.isTechFeel()) {
            this.mBottomNavigationView.getMenu().findItem(R.id.menu_wisdom).setVisible(Constant.isRingTouch());
        }
        WeatherUtils.checkLocation();
        if (this.isShowBluetoothDialog) {
            showBluetoothDialog();
        }
        if (!((Boolean) SharedPreferencesUtils.get(this, Constant.SpConstKey.isSendNotifyDevice, true)).booleanValue() && YCBTClient.connectState() == 10 && Constant.isSmartHealth()) {
            YCBTClient.notifyDevice(new BleDataResponse() { // from class: com.yucheng.smarthealthpro.MainActivity.7
                @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                public void onDataResponse(int i2, float v, HashMap hashMap) {
                    SharedPreferencesUtils.put(MainActivity.this.getActivity(), Constant.SpConstKey.isSendNotifyDevice, false);
                }
            });
        }
    }

    private void notifyDeviceSleep() {
        if (YCBTClient.connectState() == 10) {
            YCBTClient.notifyDevice(new BleDataResponse() { // from class: com.yucheng.smarthealthpro.MainActivity.8
                @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                public void onDataResponse(int i2, float v, HashMap hashMap) {
                }
            });
        }
    }

    private void checkLocale() {
        Log.d("ltf", "applocale=" + MultiLanguageUtils.getAppLocale(this).toLanguageTag());
        boolean zIsSameWithSetting = MultiLanguageUtils.isSameWithSetting(this);
        Log.d("ltf", "sameWithSetting=" + zIsSameWithSetting);
        if (zIsSameWithSetting) {
            return;
        }
        resetLanguage();
    }

    private void init() {
        if (((Boolean) SharedPreferencesUtils.get(this, Constant.SpConstKey.IS_FIRST_OPEN, false)).booleanValue()) {
            startActivity(new Intent(this, (Class<?>) UserInfoActivity.class));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x013e  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x019c  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x01a3  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x01c5  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x01ca  */
    /* JADX WARN: Removed duplicated region for block: B:56:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0066  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void initView() {
        /*
            Method dump skipped, instructions count: 521
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.MainActivity.initView():void");
    }

    private void checkedVersion() {
        UpdateVersionUtil.getInstance().checkUpdate("https://staticpage.ycaviation.com/app/app_version.xml", this, false);
    }

    public void upgradeDownload(String mac) {
        if (mac == null) {
            return;
        }
        HashMap map = new HashMap();
        map.put("mac", mac);
        HttpUtils.getInstance().postMsgAsynHttp(this, Constants.SELECTMAC, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.MainActivity.9
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                if (result == null) {
                    return;
                }
                try {
                    UpgradeBean upgradeBean = (UpgradeBean) new Gson().fromJson(result, UpgradeBean.class);
                    if (upgradeBean == null || upgradeBean.data == null || upgradeBean.data.deviceName == null) {
                        return;
                    }
                    MainActivity.this.checkFirmwareVersion(upgradeBean.data.deviceName);
                } catch (Exception e2) {
                    CrashReport.postCatchedException(e2);
                    e2.printStackTrace();
                }
            }
        });
    }

    public void checkFirmwareVersion(final String deviceType) {
        String bindDeviceVersion = YCBTClient.getBindDeviceVersion();
        if (bindDeviceVersion == null || !bindDeviceVersion.contains(".")) {
            return;
        }
        String[] strArrSplit = bindDeviceVersion.split("\\.");
        try {
            this.bNo = Integer.parseInt(strArrSplit[0]);
            this.sNo = Integer.parseInt(strArrSplit[1]);
            Tools.saveFirmwareVersion(this, "" + this.bNo + "." + this.sNo);
            if (TextUtils.isEmpty(deviceType)) {
                return;
            }
            DownloadUtil.getInstance().download(Constants.getFirmwareurl() + deviceType + ".plist", "health", new DownloadUtil.OnDownloadListener() { // from class: com.yucheng.smarthealthpro.MainActivity.10
                @Override // com.yucheng.smarthealthpro.utils.DownloadUtil.OnDownloadListener
                public void onDownloadFailed() {
                }

                @Override // com.yucheng.smarthealthpro.utils.DownloadUtil.OnDownloadListener
                public void onDownloading(int progress) {
                }

                @Override // com.yucheng.smarthealthpro.utils.DownloadUtil.OnDownloadListener
                public void onDownloadSuccess() {
                    MainActivity.this.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.MainActivity.10.1
                        @Override // java.lang.Runnable
                        public void run() throws NumberFormatException {
                            MainActivity.this.checkUp(deviceType);
                        }
                    });
                }
            });
        } catch (NumberFormatException e2) {
            e2.printStackTrace();
        }
    }

    public void newCheckFirmwareVersion(final String deviceType) {
        Log.d(TAG, "newCheckFirmwareVersion: ");
        String bindDeviceVersion = YCBTClient.getBindDeviceVersion();
        if (bindDeviceVersion == null || !bindDeviceVersion.contains(".")) {
            return;
        }
        String[] strArrSplit = bindDeviceVersion.split("\\.");
        try {
            this.bNo = Integer.parseInt(strArrSplit[0]);
            this.sNo = Integer.parseInt(strArrSplit[1]);
            Tools.saveFirmwareVersion(this, "" + this.bNo + "." + this.sNo);
            if (TextUtils.isEmpty(deviceType)) {
                return;
            }
            String str = Constants.getFirmwareurl() + deviceType + ".plist";
            DownloadUtil.getInstance().download(Constants.getFirmwareurl() + deviceType + ".plist", "health", new DownloadUtil.OnDownloadListener() { // from class: com.yucheng.smarthealthpro.MainActivity.11
                @Override // com.yucheng.smarthealthpro.utils.DownloadUtil.OnDownloadListener
                public void onDownloadFailed() {
                }

                @Override // com.yucheng.smarthealthpro.utils.DownloadUtil.OnDownloadListener
                public void onDownloading(int progress) {
                }

                @Override // com.yucheng.smarthealthpro.utils.DownloadUtil.OnDownloadListener
                public void onDownloadSuccess() {
                    MainActivity.this.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.MainActivity.11.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity.this.newCheckUp(deviceType);
                        }
                    });
                }
            });
        } catch (NumberFormatException e2) {
            e2.printStackTrace();
        }
    }

    public void newCheckUp(String deviceName) {
        DeviceUpgradeInfo plist = VersionUtilsKt.parsePlist(DownloadUtil.getInstance().filePath);
        DeviceUpgradeInfo deviceVersionInfo = VersionUtilsKt.parseDeviceVersionInfo((String) SharedPreferencesUtils.get(this, Constant.SpConstKey.deviceVersion, ""), (String) SharedPreferencesUtils.get(this, Constant.SpConstKey.bloodAlgoVersion, ""), (String) SharedPreferencesUtils.get(this, Constant.SpConstKey.tpVersion, ""));
        if (plist == null || deviceVersionInfo == null) {
            return;
        }
        Logger.d("serverUpgradeInfo: " + plist);
        Logger.d("deviceInfo: " + deviceVersionInfo);
        if (VersionUtilsKt.checkCanUpgrade(deviceVersionInfo.getOsVersion(), plist.getOsVersion())) {
            updateFirmware(deviceVersionInfo.getOsVersion().getMajorVersion(), deviceVersionInfo.getOsVersion().getSubVersion(), deviceName, plist.getOsVersion().getAutoUpdateVersions(), 0);
        } else if (VersionUtilsKt.checkCanUpgrade(deviceVersionInfo.getBpVersion(), plist.getBpVersion())) {
            updateFirmware(deviceVersionInfo.getBpVersion().getMajorVersion(), deviceVersionInfo.getBpVersion().getSubVersion(), deviceName, plist.getBpVersion().getAutoUpdateVersions(), 1);
        } else if (VersionUtilsKt.checkCanUpgrade(deviceVersionInfo.getTpVersion(), plist.getTpVersion())) {
            updateFirmware(deviceVersionInfo.getTpVersion().getMajorVersion(), deviceVersionInfo.getTpVersion().getSubVersion(), deviceName, plist.getTpVersion().getAutoUpdateVersions(), 2);
        }
    }

    public void checkUp(String deviceName) throws NumberFormatException {
        try {
            NSDictionary nSDictionary = (NSDictionary) PropertyListParser.parse(new File(DownloadUtil.getInstance().filePath));
            int i2 = Integer.parseInt(nSDictionary.get("bNo").toJavaObject().toString());
            int i3 = Integer.parseInt(nSDictionary.get("sNo").toJavaObject().toString());
            boolean z = nSDictionary.containsKey("only_show_ota_page") ? Boolean.parseBoolean(nSDictionary.get("only_show_ota_page").toJavaObject().toString()) : false;
            try {
                Integer.parseInt(nSDictionary.get("algorithm_bNo").toJavaObject().toString());
                Integer.parseInt(nSDictionary.get("algorithm_sNo").toJavaObject().toString());
            } catch (Exception e2) {
                CrashReport.postCatchedException(e2);
                e2.printStackTrace();
            }
            try {
                Integer.parseInt(nSDictionary.get("tp_bNo").toJavaObject().toString());
                Integer.parseInt(nSDictionary.get("tp_sNo").toJavaObject().toString());
            } catch (Exception e3) {
                CrashReport.postCatchedException(e3);
                e3.printStackTrace();
            }
            if (checkFirmware(nSDictionary, this.bNo, this.sNo)) {
                int i4 = this.bNo;
                if ((i2 > i4 || (i3 > this.sNo && i2 == i4)) && !z) {
                    updateFirmware(i4, this.sNo, deviceName, this.autoUpdateVersion, 0);
                }
            }
        } catch (Exception e4) {
            CrashReport.postCatchedException(e4);
            e4.printStackTrace();
        }
    }

    private boolean checkFirmware(NSDictionary rootDict, int bNo, int sNo) {
        String string = "";
        this.autoUpdateVersion = "";
        if (rootDict.get("autoUpdateVersion") != null) {
            this.autoUpdateVersion = rootDict.get("autoUpdateVersion").toJavaObject().toString();
        }
        if (rootDict.get("allUpdateVersion") != null) {
            string = rootDict.get("allUpdateVersion").toJavaObject().toString();
        }
        if (string.isEmpty()) {
            return true;
        }
        String str = bNo + "." + sNo;
        for (String str2 : string.split(",")) {
            if (str.equals(str2)) {
                return true;
            }
        }
        return false;
    }

    private void updateFirmware(int bNo, int sNo, String deviceName, String autoUpdateVersion, int type) {
        if (CommonAction.getInstance().getCurrActivity(SoftUpdateActivity.class) || CommonAction.getInstance().getCurrActivity(RecoveryActivity.class) || !PermissionUtil.checkOtaPermission(this)) {
            return;
        }
        Intent intent = new Intent(this, (Class<?>) SoftUpdateActivity.class);
        intent.putExtra("isMainStart", true);
        intent.putExtra("bNo", bNo);
        intent.putExtra("autoUpdateVersion", autoUpdateVersion);
        intent.putExtra("sNo", sNo);
        intent.putExtra("deviceName", deviceName);
        intent.putExtra("type", type);
        startActivity(intent);
    }

    @Override // com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        int i2 = 0;
        while (true) {
            int[] iArr = this.bottomArr;
            if (i2 >= iArr.length) {
                return true;
            }
            if (iArr[i2] == menuItem.getItemId()) {
                this.mViewPager.setCurrentItem(i2, false);
            }
            i2++;
        }
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageSelected(int vpSelectIndex) {
        if (Constant.isSmartHealth()) {
            notifyDeviceSleep();
        }
        this.mBottomNavigationView.setSelectedItemId(this.bottomArr[vpSelectIndex]);
    }

    public void showLoad() {
        Log.i("AAAAAAAA", "--showLoad--");
        this.isSync = 1;
    }

    public void dismissLoad() {
        Log.i("AAAAAAAA", "--dismissLoad--");
        this.isSync = 0;
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == 4) {
            moveTaskToBack(false);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    protected void attachBaseContext(Context newBase) {
        ContextWrapper contextWrapperWrap = ViewPumpContextWrapper.wrap(newBase);
        MultiLanguageUtils.setConfiguration(contextWrapperWrap);
        super.attachBaseContext(contextWrapperWrap);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        boolean booleanExtra = intent.getBooleanExtra("changeLanguage", false);
        boolean booleanExtra2 = intent.getBooleanExtra("change", false);
        if (booleanExtra) {
            recreate();
        } else if (booleanExtra2) {
            finish();
            startActivity(new Intent(this, (Class<?>) MainActivity.class));
        } else {
            setIntent(intent);
            initShare(intent);
        }
    }

    private void deviceToApp() {
        YCBTClient.deviceToApp(new BleDeviceToAppDataResponse() { // from class: com.yucheng.smarthealthpro.MainActivity.12
            @Override // com.yucheng.ycbtsdk.response.BleDeviceToAppDataResponse
            public void onDataResponse(int i2, HashMap hashMap) {
                if (hashMap != null && i2 == 0) {
                    int iIntValue = ((Integer) hashMap.get("dataType")).intValue();
                    int iIntValue2 = hashMap.get("data") != null ? ((Integer) hashMap.get("data")).intValue() : -1;
                    if (iIntValue == 788) {
                        ((Integer) hashMap.get("EcgStatus")).intValue();
                        ((Integer) hashMap.get("PPGStatus")).intValue();
                    } else if (iIntValue != 1046) {
                        if (iIntValue == 1035) {
                            EventBus.getDefault().post(new EventBusEcgEnd());
                        } else if (iIntValue != 1036) {
                            switch (iIntValue) {
                                case 1024:
                                    if (iIntValue2 != 0) {
                                        if (iIntValue2 == 1) {
                                            SoundPoolUtil.getInstance(MainActivity.this).play(100);
                                            MainActivity.this.handler.removeCallbacks(MainActivity.this.runnable);
                                            MainActivity.this.handler.postDelayed(MainActivity.this.runnable, 15000L);
                                            break;
                                        }
                                    } else {
                                        MainActivity.this.handler.removeCallbacks(MainActivity.this.runnable);
                                        SoundPoolUtil.getInstance(MainActivity.this).stop();
                                        break;
                                    }
                                    break;
                                case 1026:
                                    if (iIntValue2 == 0) {
                                        MainActivity.this.answerCall();
                                        break;
                                    } else {
                                        MainActivity.this.rejectCall();
                                        break;
                                    }
                                case 1027:
                                    if (PermissionUtil.openCameraPermission(MainActivity.this.context) && PermissionUtil.openSDCardPermission(MainActivity.this.context)) {
                                        if (iIntValue2 == 1) {
                                            MainActivity.this.startActivity(new Intent(MainActivity.this, (Class<?>) CameraActivity.class));
                                            break;
                                        } else {
                                            EventBus.getDefault().post(new EventBusTakePhotoEvent(iIntValue2));
                                            break;
                                        }
                                    }
                                    break;
                                case 1028:
                                    HealthApplication.getInstance().musicCon(iIntValue2);
                                    break;
                            }
                        } else {
                            int iIntValue3 = ((Integer) hashMap.get("sportState")).intValue();
                            int iIntValue4 = ((Integer) hashMap.get("sportType")).intValue();
                            if (iIntValue3 == 1) {
                                Intent intent = new Intent(MainActivity.this.context, (Class<?>) SportRunningActivity.class);
                                intent.putExtra("Title", iIntValue4);
                                MainActivity.this.startActivity(intent);
                            } else {
                                EventBus.getDefault().post(new EventBusExitExerciseEvent(iIntValue3, iIntValue4));
                            }
                        }
                    } else if (Constant.isSmartHealth()) {
                        MainActivity mainActivity = MainActivity.this;
                        mainActivity.showTipDialog(mainActivity.getString(R.string.sedentary_tip));
                    }
                }
                EventBus.getDefault().post(new ToAppDataResponse(i2, hashMap));
            }
        });
    }

    @Override // java.util.Observer
    public void update(Observable o, Object arg) {
        Object obj = ((Map) arg).get(Constant.SpConstKey.TOKEN);
        if (obj == null || !((Boolean) obj).booleanValue()) {
            return;
        }
        loginOutDone();
    }

    private void loginOutDone() {
        SharedPreferencesUtils.put(this.context, Constant.SpConstKey.IS_LOGIN, false);
        YCBTClient.disconnectBle();
        SharedPreferencesUtils.remove(this.context, Constant.SpConstKey.IMAGE_PATH);
        SharedPreferencesUtils.remove(this.context, Constant.SpConstKey.HEAD_IMG);
        SharedPreferencesUtils.remove(this.context, Constant.SpConstKey.TOKEN);
        Intent intent = new Intent(this.context, (Class<?>) LoginActivity.class);
        intent.setFlags(335544320);
        startActivity(intent);
        CommonAction.getInstance().OutSign();
        finish();
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        SubObserver.getInstance().delObs(this);
        EventBus.getDefault().unregister(this);
        this.handler.removeCallbacks(this.checkSendFisnish);
        GpsSwitchStateReceiver gpsSwitchStateReceiver = this.gpsSwitchStateReceiver;
        if (gpsSwitchStateReceiver != null) {
            unregisterReceiver(gpsSwitchStateReceiver);
        }
        Reconnect.getInstance().unRegisterReconnectResponse(this.reconnectResponse);
        super.onDestroy();
    }

    public void answerCall() {
        Log.i("chong", " 接电话...................");
        HangUpTelephonyUtil.answerCall(getApplicationContext());
    }

    public void rejectCall() {
        Log.i("chong", " 挂电话...................");
        if (HangUpTelephonyUtil.endCall(getApplicationContext())) {
            Log.i("chong", " 挂电话...................成功");
        } else {
            Log.i("chong", " 挂电话...................失败");
        }
    }

    public void startCheck() {
        this.current = 0;
        this.handler.postDelayed(this.checkSendFisnish, 1000L);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getCompile(EventBusMessageEvent messageEvent) {
        int i2 = messageEvent.belState;
        if (i2 != 0) {
            if (i2 == 1) {
                SportViewModel sportViewModel = this.mSportViewModel;
                if (sportViewModel != null && sportViewModel.getSportMode().getValue().booleanValue()) {
                    Logger.d("mSportViewModel: 跳过同步");
                    return;
                }
                if (this.isFirst) {
                    this.isFirst = false;
                    checkAPPPermision();
                }
                Reconnect.getInstance().resetFailedCount();
                CommonDialog commonDialog = this.blueToothDialog;
                if (commonDialog != null && commonDialog.isShowing()) {
                    this.blueToothDialog.dismiss();
                    this.blueToothDialog = null;
                }
                showProgressDialog(getString(R.string.ecg_sync_data));
                startCheck();
                this.mBottomNavigationView.getMenu().findItem(R.id.menu_wisdom).setVisible(Constant.isRingTouch());
                YCBTClient.getDeviceInfo(new BleDataResponse() { // from class: com.yucheng.smarthealthpro.MainActivity.14
                    @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                    public void onDataResponse(int code, float ratio, HashMap resultMap) {
                        int iIntValue;
                        MainActivity mainActivity = MainActivity.this;
                        if (mainActivity == null || mainActivity.isDestroyed() || code != 0) {
                            return;
                        }
                        try {
                            try {
                                iIntValue = ((Integer) ((HashMap) resultMap.get("data")).get("hardwareType")).intValue();
                            } catch (Exception e2) {
                                CrashReport.postCatchedException(e2);
                                e2.printStackTrace();
                                iIntValue = 0;
                            }
                            SharedPreferencesUtils.put(MainActivity.this, "hardwareType", Integer.valueOf(iIntValue));
                            MainActivity.this.mBottomNavigationView.getMenu().findItem(R.id.menu_wisdom).setVisible(Constant.isRingTouch());
                        } catch (Exception e3) {
                            CrashReport.postCatchedException(e3);
                            e3.printStackTrace();
                        }
                    }
                });
                if (Constant.isSmartHealth() && this.isFirstNofity) {
                    this.isFirstNofity = false;
                    YCBTClient.notifyDevice(new BleDataResponse() { // from class: com.yucheng.smarthealthpro.MainActivity.15
                        @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                        public void onDataResponse(int i3, float v, HashMap hashMap) {
                            SharedPreferencesUtils.put(MainActivity.this.getActivity(), Constant.SpConstKey.isSendNotifyDevice, false);
                        }
                    });
                    return;
                }
                return;
            }
        } else if (Constant.isRingTouch()) {
            this.mBottomNavigationView.getMenu().findItem(R.id.menu_wisdom).setVisible(false);
        }
        dismissProgressDialog();
    }

    @Override // com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        this.isResume = false;
    }

    private class GpsSwitchStateReceiver extends BroadcastReceiver {
        private GpsSwitchStateReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("android.location.PROVIDERS_CHANGED".equals(intent.getAction())) {
                boolean zIsGpsEnabled = MainActivity.this.isGpsEnabled(context);
                if (!MainActivity.this.isSendWeather && zIsGpsEnabled) {
                    MainActivity.this.isSendWeather = true;
                    WeatherUtils.weatherFunction(MainActivity.this.getApplicationContext());
                } else {
                    if (!MainActivity.this.isSendWeather || zIsGpsEnabled) {
                        return;
                    }
                    MainActivity.this.isSendWeather = false;
                }
            }
        }
    }

    public boolean isGpsEnabled(Context context) {
        return ((LocationManager) context.getSystemService(FirebaseAnalytics.Param.LOCATION)).isProviderEnabled(GeocodeSearch.GPS);
    }

    public void initShare(final Intent intent) {
        this.executorService.execute(new Runnable() { // from class: com.yucheng.smarthealthpro.MainActivity.16
            @Override // java.lang.Runnable
            public void run() {
                if (!"android.intent.action.VIEW".equals(intent.getAction()) || intent.getBooleanExtra("isGetPath", false)) {
                    return;
                }
                intent.putExtra("isGetPath", true);
                Uri data = intent.getData();
                MainActivity mainActivity = MainActivity.this;
                mainActivity.path = ShareUtils.getRealPathFromUri(mainActivity.getApplicationContext(), data);
                if (!TextUtils.isEmpty(MainActivity.this.path)) {
                    MainActivity mainActivity2 = MainActivity.this;
                    mainActivity2.path = JxdUtils.copyFile(mainActivity2.getApplicationContext(), MainActivity.this.path);
                    if (TextUtils.isEmpty(MainActivity.this.path)) {
                        MainActivity mainActivity3 = MainActivity.this;
                        mainActivity3.path = JxdUtils.getFilePathFromURI(mainActivity3.getApplicationContext(), data);
                    }
                } else {
                    MainActivity mainActivity4 = MainActivity.this;
                    mainActivity4.path = JxdUtils.getFilePathFromURI(mainActivity4.getApplicationContext(), data);
                }
                String str = (String) SharedPreferencesUtils.get(MainActivity.this.getApplicationContext(), Constant.SpConstKey.FIRM_WARE_FILE, "");
                ArrayList arrayList = new ArrayList();
                if (!TextUtils.isEmpty(str)) {
                    arrayList.addAll(Arrays.asList(str.split(",")));
                }
                if (!arrayList.contains(MainActivity.this.path)) {
                    arrayList.add(MainActivity.this.path);
                }
                SharedPreferencesUtils.put(MainActivity.this.getApplicationContext(), Constant.SpConstKey.FIRM_WARE_FILE, String.join(",", arrayList));
                SharedPreferencesUtils.put(MainActivity.this.getApplicationContext(), Constant.SpConstKey.FIRM_WARE_FILE_CURRENT, MainActivity.this.path);
                MainActivity.this.handler.post(new Runnable() { // from class: com.yucheng.smarthealthpro.MainActivity.16.1
                    @Override // java.lang.Runnable
                    public void run() {
                        ToastUtil.getInstance(MainActivity.this.getApplicationContext()).toast(MainActivity.this.path);
                    }
                });
            }
        });
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, androidx.appcompat.app.AppCompatActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d("ltf", "onConfigurationChanged");
        checkLocale();
    }

    public void resetLanguage() {
        String str = (String) SharedPreferencesUtils.get(this, Constant.SP_LANGUAGE, "");
        String str2 = (String) SharedPreferencesUtils.get(this, "COUNTRY", "");
        if (TextUtils.isEmpty(str)) {
            return;
        }
        MultiLanguageUtils.setAppLanguage(this, new Locale(str, str2));
        finish();
        startActivity(new Intent(this, (Class<?>) MainActivity.class));
    }
}
