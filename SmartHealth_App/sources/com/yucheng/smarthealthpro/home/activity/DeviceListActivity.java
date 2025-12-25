package com.yucheng.smarthealthpro.home.activity;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.flyco.animation.BaseAnimatorSet;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.google.android.gms.location.DeviceOrientationRequest;
import com.orhanobut.logger.Logger;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.wevey.selector.dialog.DialogInterface;
import com.wevey.selector.dialog.MDAlertDialog;
import com.yanzhenjie.permission.Permission;
import com.yucheng.smarthealthpro.MainActivity;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.databinding.ActivityDevicelistBinding;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.framework.util.ToastUtil;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import com.yucheng.smarthealthpro.home.adapter.HomeDecivcleListAdapter;
import com.yucheng.smarthealthpro.sport.utils.GetGPSUtil;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.CustomDialog;
import com.yucheng.smarthealthpro.utils.DialogUtils;
import com.yucheng.smarthealthpro.utils.EventBusMessageEvent;
import com.yucheng.ycbtsdk.YCBTClient;
import com.yucheng.ycbtsdk.bean.ScanDeviceBean;
import com.yucheng.ycbtsdk.gatt.BleHelper;
import com.yucheng.ycbtsdk.gatt.Reconnect;
import com.yucheng.ycbtsdk.response.BleConnectResponse;
import com.yucheng.ycbtsdk.response.BleDataResponse;
import com.yucheng.ycbtsdk.response.BleScanResponse;
import com.yucheng.ycbtsdk.utils.PermissionUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes5.dex */
public class DeviceListActivity extends BaseVbActivity<ActivityDevicelistBinding> {
    private HomeDecivcleListAdapter mAdapter;
    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;
    RecyclerView mRecyclerView;
    private List<ScanDeviceBean> mScanDeviceBeans;
    SmartRefreshLayout mSmartRefreshLayout;
    private ProgressDialog progressDialog;
    private boolean isRecovery = false;
    boolean isToastFailed = false;
    private Handler handler = new Handler(new Handler.Callback() { // from class: com.yucheng.smarthealthpro.home.activity.DeviceListActivity.1
        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message msg) {
            if (DeviceListActivity.this.isFinishing()) {
                return false;
            }
            switch (msg.what) {
                case 1:
                    if (DeviceListActivity.this.progressDialog != null && !DeviceListActivity.this.isFinishing()) {
                        DeviceListActivity.this.progressDialog.dismiss();
                    }
                    if (!YCBTClient.getBindDeviceMac().isEmpty()) {
                        ToastUtil.getInstance(DeviceListActivity.this.context).toast(DeviceListActivity.this.getString(R.string.connect_failed));
                    }
                    return false;
                case 2:
                    ToastUtil.getInstance(DeviceListActivity.this.context).toast(DeviceListActivity.this.getString(R.string.connect_success));
                    DeviceListActivity.this.progressDialog.dismiss();
                    if (((Boolean) SharedPreferencesUtils.get(DeviceListActivity.this.getApplicationContext(), Constant.SpConstKey.vestBag, false)).booleanValue()) {
                        SharedPreferencesUtils.put(DeviceListActivity.this.getApplicationContext(), Constant.SpConstKey.vestBag, false);
                        Intent intent = new Intent(DeviceListActivity.this, (Class<?>) MainActivity.class);
                        intent.putExtra("change", true);
                        DeviceListActivity.this.startActivity(intent);
                    }
                    DeviceListActivity.this.finish();
                    return false;
                case 3:
                    ToastUtil.getInstance(DeviceListActivity.this.context).toast(DeviceListActivity.this.getString(R.string.connect_failed));
                    if (DeviceListActivity.this.progressDialog != null && !DeviceListActivity.this.isFinishing()) {
                        DeviceListActivity.this.progressDialog.dismiss();
                    }
                    return false;
                case 4:
                    if (DeviceListActivity.this.mAdapter != null && !DeviceListActivity.this.isFinishing()) {
                        DeviceListActivity.this.mAdapter.setList(DeviceListActivity.this.mScanDeviceBeans);
                        DeviceListActivity.this.mAdapter.notifyDataSetChanged();
                    }
                    return false;
                case 5:
                    DeviceListActivity deviceListActivity = DeviceListActivity.this;
                    deviceListActivity.showPermissionDialog(deviceListActivity.getString(R.string.permission_prompt_android12), DeviceListActivity.this.getString(R.string.permission_content_android12), true);
                    return false;
                case 6:
                    GetGPSUtil.showLocation(DeviceListActivity.this);
                    return false;
                case 7:
                    DeviceListActivity deviceListActivity2 = DeviceListActivity.this;
                    deviceListActivity2.showPermissionDialog(deviceListActivity2.getString(R.string.permission_prompt), DeviceListActivity.this.getString(R.string.permission_content), false);
                    return false;
                case 8:
                    if (!YCBTClient.getBindDeviceMac().isEmpty()) {
                        ToastUtil.getInstance(DeviceListActivity.this.context).toast(DeviceListActivity.this.getString(R.string.connect_failed));
                    }
                    if (DeviceListActivity.this.progressDialog != null && !DeviceListActivity.this.isFinishing()) {
                        DeviceListActivity.this.progressDialog.dismiss();
                    }
                    return false;
                default:
                    return false;
            }
        }
    });
    Thread thread = new Thread(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.DeviceListActivity.5
        @Override // java.lang.Runnable
        public void run() {
            while (!DeviceListActivity.this.isFinishing()) {
                try {
                    synchronized (DeviceListActivity.this.thread) {
                        DeviceListActivity.this.thread.wait();
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                DeviceListActivity.this.scanLeDevice();
            }
        }
    });
    private boolean isFirst = true;
    private BroadcastReceiver mReceiver = new BroadcastReceiver() { // from class: com.yucheng.smarthealthpro.home.activity.DeviceListActivity.14
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context con, Intent intent) {
            String action = intent.getAction();
            action.hashCode();
            if (action.equals("android.bluetooth.adapter.action.STATE_CHANGED")) {
                int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", 0);
                if (intExtra == 10) {
                    PermissionUtil.getInstance().hasPermission(null, DeviceListActivity.this);
                } else {
                    if (intExtra != 12) {
                        return;
                    }
                    DeviceListActivity.this.scanLeDevice();
                }
            }
        }
    };

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerReceiver(this.mReceiver, makeFilter());
        EventBus.getDefault().register(this);
        if (YCBTClient.connectState() != 3 && YCBTClient.connectState() != 4) {
            BleHelper.getHelper().disconnectGatt();
            BleHelper.getHelper().disconnectA2dp();
        }
        initView();
        initData();
        SharedPreferencesUtils.put(this.context, Constant.SpConstKey.IS_CONNECT, "");
        Reconnect.getInstance().setReconnect(false);
        YCBTClient.resetBond();
    }

    private IntentFilter makeFilter() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        return intentFilter;
    }

    private void initView() {
        this.mSmartRefreshLayout = ((ActivityDevicelistBinding) this.mBinding).srlHome;
        this.mRecyclerView = ((ActivityDevicelistBinding) this.mBinding).recycleDevicelist;
        changeTitle(getString(R.string.home_device_list_title));
        showBack();
        showRightImage(R.mipmap.topbar_ic_load, new NavigationBar.MyOnClickListener() { // from class: com.yucheng.smarthealthpro.home.activity.DeviceListActivity.2
            @Override // com.yucheng.smarthealthpro.framework.view.NavigationBar.MyOnClickListener
            public void onClick(View btn) {
                synchronized (DeviceListActivity.this.thread) {
                    DeviceListActivity.this.thread.notify();
                }
            }
        });
    }

    private void initData() {
        this.mScanDeviceBeans = new ArrayList();
        setRecycleView();
        this.thread.start();
        Logger.d("refresh");
        this.handler.postDelayed(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.DeviceListActivity.3
            @Override // java.lang.Runnable
            public void run() {
                DeviceListActivity.this.refresh();
            }
        }, 200L);
        this.mSmartRefreshLayout.setEnableLoadMore(false);
        this.mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() { // from class: com.yucheng.smarthealthpro.home.activity.DeviceListActivity.4
            @Override // com.scwang.smart.refresh.layout.listener.OnRefreshListener
            public void onRefresh(RefreshLayout refreshLayout) {
                synchronized (DeviceListActivity.this.thread) {
                    DeviceListActivity.this.thread.notify();
                }
                refreshLayout.finishRefresh();
            }
        });
        if (getIntent() != null) {
            this.isRecovery = getIntent().getBooleanExtra("recovery", false);
        }
    }

    public void refresh() {
        synchronized (this.thread) {
            this.thread.notify();
        }
        this.mSmartRefreshLayout.finishRefresh();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void scanLeDevice() {
        if (hasPermission()) {
            if (PermissionUtil.getInstance().hasPermission(null, this)) {
                List<ScanDeviceBean> list = this.mScanDeviceBeans;
                if (list != null) {
                    list.clear();
                }
                ArrayList arrayList = new ArrayList();
                if (Constant.isMymon()) {
                    YCBTClient.stopScanBle();
                    YCBTClient.startScanBle(new BleScanResponse() { // from class: com.yucheng.smarthealthpro.home.activity.DeviceListActivity.6
                        @Override // com.yucheng.ycbtsdk.response.BleScanResponse
                        public void onScanResponse(int code, ScanDeviceBean scanDeviceBean) {
                            DeviceListActivity.this.parseScanResult(code, scanDeviceBean);
                        }
                    }, 8, 30738);
                    return;
                }
                if (Constant.isHealthWear()) {
                    arrayList.add("1278");
                    arrayList.add("1178");
                    arrayList.add("1078");
                    YCBTClient.stopScanBle();
                    YCBTClient.startScanBle(new BleScanResponse() { // from class: com.yucheng.smarthealthpro.home.activity.DeviceListActivity.7
                        @Override // com.yucheng.ycbtsdk.response.BleScanResponse
                        public void onScanResponse(int code, ScanDeviceBean scanDeviceBean) {
                            DeviceListActivity.this.parseScanResult(code, scanDeviceBean);
                        }
                    }, 8, arrayList);
                    return;
                }
                if (Constant.isSmartHealth()) {
                    arrayList.add("1078");
                    YCBTClient.stopScanBle();
                    YCBTClient.startScanBle(new BleScanResponse() { // from class: com.yucheng.smarthealthpro.home.activity.DeviceListActivity.8
                        @Override // com.yucheng.ycbtsdk.response.BleScanResponse
                        public void onScanResponse(int code, ScanDeviceBean scanDeviceBean) {
                            DeviceListActivity.this.parseScanResult(code, scanDeviceBean);
                        }
                    }, 8, arrayList);
                } else {
                    YCBTClient.stopScanBle();
                    YCBTClient.startScanBle(new BleScanResponse() { // from class: com.yucheng.smarthealthpro.home.activity.DeviceListActivity.9
                        @Override // com.yucheng.ycbtsdk.response.BleScanResponse
                        public void onScanResponse(int code, ScanDeviceBean scanDeviceBean) {
                            DeviceListActivity.this.parseScanResult(code, scanDeviceBean);
                        }
                    }, 8, arrayList);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void parseScanResult(int code, ScanDeviceBean scanDeviceBean) {
        if (isDestroyed()) {
            return;
        }
        if (code == 3) {
            DialogUtils.showPromptDialog(this, getString(R.string.scan_failed_tip), new DialogInterface.OnClickListener() { // from class: com.yucheng.smarthealthpro.home.activity.DeviceListActivity$$ExternalSyntheticLambda0
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i2) {
                    this.f$0.lambda$parseScanResult$0(dialogInterface, i2);
                }
            });
        }
        if (scanDeviceBean != null) {
            int i2 = 0;
            while (true) {
                if (i2 < this.mScanDeviceBeans.size()) {
                    if (this.mScanDeviceBeans.get(i2).getDeviceMac().equals(scanDeviceBean.getDeviceMac())) {
                        String deviceName = scanDeviceBean.getDeviceName();
                        String deviceName2 = this.mScanDeviceBeans.get(i2).getDeviceName();
                        String deviceMac = this.mScanDeviceBeans.get(i2).getDeviceMac();
                        Log.d("ltf", "sameMac = " + deviceMac);
                        String bindDeviceMac = YCBTClient.getBindDeviceMac();
                        String bindDeviceName = YCBTClient.getBindDeviceName();
                        if (TextUtils.isEmpty(deviceName2) && deviceMac.equals(bindDeviceMac)) {
                            scanDeviceBean.setDeviceName(bindDeviceName);
                        }
                        if (TextUtils.isEmpty(deviceName2) && !TextUtils.isEmpty(deviceName)) {
                            this.mScanDeviceBeans.get(i2).setDeviceName(deviceName);
                        }
                    } else {
                        i2++;
                    }
                } else {
                    this.mScanDeviceBeans.add(scanDeviceBean);
                    break;
                }
            }
            Collections.sort(this.mScanDeviceBeans);
            this.handler.sendEmptyMessage(4);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$parseScanResult$0(DialogInterface dialogInterface, int i2) {
        if (isFinishing()) {
            return;
        }
        dialogInterface.dismiss();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void setDaLog() {
        final NormalDialog normalDialog = new NormalDialog(this.context);
        ((NormalDialog) ((NormalDialog) normalDialog.content(getString(R.string.open_blue)).title(getString(R.string.prompt)).titleTextColor(Color.parseColor("#000000")).titleTextSize(20.0f).bgColor(Color.parseColor("#F8F8F8")).cornerRadius(5.0f).style(1).contentTextColor(Color.parseColor("#000000")).btnTextColor(Color.parseColor("#000000"), Color.parseColor("#00C495")).btnPressColor(Color.parseColor("#CCCCCC")).showAnim(this.mBasIn)).dismissAnim(this.mBasOut)).show();
        normalDialog.setOnBtnClickL(new OnBtnClickL() { // from class: com.yucheng.smarthealthpro.home.activity.DeviceListActivity.10
            @Override // com.flyco.dialog.listener.OnBtnClickL
            public void onBtnClick() {
                normalDialog.dismiss();
            }
        }, new OnBtnClickL() { // from class: com.yucheng.smarthealthpro.home.activity.DeviceListActivity.11
            @Override // com.flyco.dialog.listener.OnBtnClickL
            public void onBtnClick() {
                normalDialog.dismiss();
            }
        });
    }

    private boolean hasPermission() {
        if (Build.VERSION.SDK_INT >= 31) {
            if (ContextCompat.checkSelfPermission(this, "android.permission.BLUETOOTH_SCAN") != 0 || ContextCompat.checkSelfPermission(this, "android.permission.BLUETOOTH_CONNECT") != 0) {
                this.handler.sendEmptyMessage(5);
                return false;
            }
            if (!com.yucheng.smarthealthpro.utils.PermissionUtil.isHonor()) {
                return true;
            }
            if (!GetGPSUtil.isOpenGpsService(getApplicationContext())) {
                this.handler.sendEmptyMessage(6);
            }
            if (ContextCompat.checkSelfPermission(this, Permission.ACCESS_FINE_LOCATION) == 0) {
                return true;
            }
            this.handler.sendEmptyMessage(7);
            return true;
        }
        if (!GetGPSUtil.isOpenGpsService(getApplicationContext())) {
            this.handler.sendEmptyMessage(6);
            return false;
        }
        if (ContextCompat.checkSelfPermission(this, Permission.ACCESS_FINE_LOCATION) == 0) {
            return true;
        }
        this.handler.sendEmptyMessage(7);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showPermissionDialog(String title, String content, final boolean nearby) {
        ((CustomDialog.Builder) new CustomDialog.Builder(this).setHeight(0.21f).setWidth(0.7f).setTitleVisible(true).setTitleText(title).setTitleTextColor(R.color.black).setContentText(content).setContentTextColor(R.color.black_light).setLeftButtonText(getString(R.string.cancel)).setLeftButtonTextColor(R.color.gray).setRightButtonText(getString(R.string.ok)).setRightButtonTextColor(R.color.black_light).setTitleTextSize(16).setContentTextSize(14).setButtonTextSize(14).setOnclickListener(new DialogInterface.OnLeftAndRightClickListener<MDAlertDialog>() { // from class: com.yucheng.smarthealthpro.home.activity.DeviceListActivity.12
            @Override // com.wevey.selector.dialog.DialogInterface.OnLeftAndRightClickListener
            public void clickLeftButton(MDAlertDialog dialog, View view) {
                dialog.dismiss();
                DeviceListActivity.this.finish();
            }

            @Override // com.wevey.selector.dialog.DialogInterface.OnLeftAndRightClickListener
            public void clickRightButton(MDAlertDialog dialog, View view) {
                dialog.dismiss();
                if (DeviceListActivity.this.isFirst) {
                    DeviceListActivity.this.isFirst = false;
                    if (Build.VERSION.SDK_INT >= 31) {
                        if (!nearby) {
                            DeviceListActivity.this.requestPermissions(new String[]{Permission.ACCESS_FINE_LOCATION, Permission.ACCESS_COARSE_LOCATION}, 1);
                        }
                        DeviceListActivity.this.requestPermissions(new String[]{"android.permission.BLUETOOTH_SCAN", "android.permission.BLUETOOTH_CONNECT"}, 1);
                        return;
                    }
                    DeviceListActivity.this.requestPermissions(new String[]{Permission.ACCESS_FINE_LOCATION, Permission.ACCESS_COARSE_LOCATION}, 1);
                    return;
                }
                com.yucheng.smarthealthpro.utils.PermissionUtil.gotoPermission(DeviceListActivity.this);
            }
        })).build().show();
    }

    private void setRecycleView() {
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        HomeDecivcleListAdapter homeDecivcleListAdapter = new HomeDecivcleListAdapter(R.layout.item_home_devicelist);
        this.mAdapter = homeDecivcleListAdapter;
        this.mRecyclerView.setAdapter(homeDecivcleListAdapter);
        this.mRecyclerView.setNestedScrollingEnabled(false);
        this.mAdapter.setOnItemClickListener(new HomeDecivcleListAdapter.OnItemClickListener() { // from class: com.yucheng.smarthealthpro.home.activity.DeviceListActivity.13
            @Override // com.yucheng.smarthealthpro.home.adapter.HomeDecivcleListAdapter.OnItemClickListener
            public void onDelClick(ScanDeviceBean hisSearch, int position) {
            }

            @Override // com.yucheng.smarthealthpro.home.adapter.HomeDecivcleListAdapter.OnItemClickListener
            public void onLongClick(ScanDeviceBean hisSearch, int position) {
            }

            @Override // com.yucheng.smarthealthpro.home.adapter.HomeDecivcleListAdapter.OnItemClickListener
            public void onClick(ScanDeviceBean scanDeviceBean, int position) {
                if (DeviceListActivity.this.progressDialog != null && !DeviceListActivity.this.isFinishing()) {
                    DeviceListActivity.this.progressDialog.dismiss();
                }
                DeviceListActivity.this.isToastFailed = false;
                if (DeviceListActivity.this.isRecovery) {
                    DeviceListActivity.this.setResult(0, new Intent().putExtra("deviceName", scanDeviceBean.getDeviceName()).putExtra("deviceMac", scanDeviceBean.getDeviceMac()));
                    DeviceListActivity.this.finish();
                    return;
                }
                DeviceListActivity deviceListActivity = DeviceListActivity.this;
                deviceListActivity.progressDialog = ProgressDialog.show(deviceListActivity.context, DeviceListActivity.this.getString(R.string.prompt), DeviceListActivity.this.getString(R.string.bluetooth_is_connecting), true, true);
                try {
                    Window window = DeviceListActivity.this.progressDialog.getWindow();
                    WindowManager.LayoutParams attributes = window.getAttributes();
                    attributes.width = (int) (window.getWindowManager().getDefaultDisplay().getWidth() * 0.95d);
                    attributes.gravity = 17;
                    window.setAttributes(attributes);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                YCBTClient.stopScanBle();
                YCBTClient.connectBleDevice(scanDeviceBean.device, new BleConnectResponse() { // from class: com.yucheng.smarthealthpro.home.activity.DeviceListActivity.13.1
                    @Override // com.yucheng.ycbtsdk.response.BleConnectResponse
                    public void onConnectResponse(int code) {
                        Logger.w("connectBle " + code, new Object[0]);
                        if (code == 0) {
                            DeviceListActivity.this.handler.removeMessages(1);
                            DeviceListActivity.this.handler.sendEmptyMessage(2);
                            Logger.d("settingAppSystem");
                            YCBTClient.settingAppSystem(0, Build.VERSION.RELEASE, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.home.activity.DeviceListActivity.13.1.1
                                @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                                public void onDataResponse(int i2, float v, HashMap hashMap) {
                                    Logger.d("settingAppSystem onDataResponse code = " + i2);
                                }
                            });
                            YCBTClient.getDeviceName(new BleDataResponse() { // from class: com.yucheng.smarthealthpro.home.activity.DeviceListActivity.13.1.2
                                @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                                public void onDataResponse(int i2, float v, HashMap hashMap) {
                                    if (i2 != 0 || hashMap == null) {
                                        Logger.d("getDeviceName failed");
                                    }
                                }
                            });
                            return;
                        }
                        if (DeviceListActivity.this.isToastFailed || YCBTClient.connectState() == 10) {
                            return;
                        }
                        DeviceListActivity.this.isToastFailed = true;
                        DeviceListActivity.this.handler.sendEmptyMessage(3);
                        DeviceListActivity.this.handler.removeMessages(1);
                    }
                });
                DeviceListActivity.this.handler.sendEmptyMessageDelayed(1, DeviceOrientationRequest.OUTPUT_PERIOD_DEFAULT);
            }
        });
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        this.handler.removeCallbacksAndMessages(null);
        unregisterReceiver(this.mReceiver);
        YCBTClient.stopScanBle();
        ProgressDialog progressDialog = this.progressDialog;
        if (progressDialog != null && progressDialog.isShowing()) {
            this.progressDialog.dismiss();
        }
        Reconnect.getInstance().setReconnect(true);
        super.onDestroy();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getCompile(EventBusMessageEvent messageEvent) {
        if (!isFinishing() && messageEvent.belState == 1) {
            YCBTClient.stopScanBle();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (hasPermission()) {
            Logger.d("refresh");
            refresh();
        }
    }
}
