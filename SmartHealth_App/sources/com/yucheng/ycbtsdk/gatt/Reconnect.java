package com.yucheng.ycbtsdk.gatt;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.text.TextUtils;
import com.jieli.jl_bt_ota.constant.BluetoothConstant;
import com.yucheng.ycbtsdk.YCBTClient;
import com.yucheng.ycbtsdk.bean.ScanDeviceBean;
import com.yucheng.ycbtsdk.core.YCBTClientImpl;
import com.yucheng.ycbtsdk.response.BleConnectResponse;
import com.yucheng.ycbtsdk.response.BleScanResponse;
import com.yucheng.ycbtsdk.utils.SPUtil;
import com.yucheng.ycbtsdk.utils.YCBTLog;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes5.dex */
public class Reconnect {
    private static Reconnect gReconnect;
    private Context mContext;
    private boolean isStartThread = false;
    private boolean isReconnect = false;
    private List<ReconnectResponse> reconnectResponseList = new ArrayList();
    private int reConnectCount = 0;
    private final int maxCount = 10;
    private final Object lock = new Object();
    public boolean connectManyTimesFailed = false;
    BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    final int TIME1 = 20000;
    final int TIME2 = BluetoothConstant.PAIR_OR_UNPAIR_TIMEOUT;
    final int TIME3 = 40000;
    int reconnectTime = 20000;
    int firstConnectCount = 0;
    boolean hasSearched = false;
    boolean isDirectConnection = true;
    final Thread thread = new Thread(new Runnable() { // from class: com.yucheng.ycbtsdk.gatt.Reconnect.1
        @Override // java.lang.Runnable
        public void run() throws InterruptedException {
            BluetoothAdapter bluetoothAdapter;
            try {
                Thread.sleep(1500L);
                while (Reconnect.this.isStartThread) {
                    if (YCBTClientImpl.getInstance().isOta) {
                        synchronized (Reconnect.this.lock) {
                            try {
                                Reconnect.this.lock.wait(40000L);
                            } catch (InterruptedException e2) {
                                e2.printStackTrace();
                            }
                        }
                    } else {
                        boolean zIsBleScanning = BleHelper.getHelper().isBleScanning();
                        boolean zIsConnecting = BleHelper.getHelper().isConnecting();
                        boolean zIsConnected = BleHelper.getHelper().isConnected();
                        YCBTLog.d("bleScanning >>>> " + zIsBleScanning + StringUtils.SPACE + zIsConnecting + StringUtils.SPACE + zIsConnected);
                        if (Reconnect.this.isReconnect && !zIsBleScanning && !zIsConnecting && !zIsConnected) {
                            String bindedDeviceMac = SPUtil.getBindedDeviceMac();
                            YCBTLog.d("tSaveMac >>>> " + bindedDeviceMac);
                            if (!TextUtils.isEmpty(bindedDeviceMac) && (bluetoothAdapter = Reconnect.this.mBluetoothAdapter) != null && bluetoothAdapter.isEnabled()) {
                                Reconnect reconnect = Reconnect.this;
                                if (reconnect.isDirectConnection) {
                                    BluetoothDevice remoteDevice = reconnect.mBluetoothAdapter.getRemoteDevice(bindedDeviceMac);
                                    if (remoteDevice != null) {
                                        Reconnect.this.directConnect(remoteDevice);
                                    } else {
                                        Reconnect.this.searchConnect(bindedDeviceMac);
                                    }
                                } else {
                                    reconnect.searchConnect(bindedDeviceMac);
                                }
                                Reconnect.this.isDirectConnection = !r0.isDirectConnection;
                            }
                        }
                        try {
                            synchronized (Reconnect.this.lock) {
                                Reconnect.this.lock.wait(Reconnect.this.reconnectTime);
                            }
                        } catch (Exception e3) {
                            e3.printStackTrace();
                        }
                    }
                }
            } catch (InterruptedException e4) {
                throw new RuntimeException(e4);
            }
        }
    });
    private BroadcastReceiver mReceiver = new BroadcastReceiver() { // from class: com.yucheng.ycbtsdk.gatt.Reconnect.4
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            action.hashCode();
            if (action.equals("android.bluetooth.adapter.action.STATE_CHANGED")) {
                switch (intent.getIntExtra("android.bluetooth.adapter.extra.STATE", 0)) {
                    case 10:
                        YCBTLog.e("蓝牙已关闭");
                        if (YCBTClient.connectState() >= 6) {
                            BleHelper.getHelper().callBackState(3);
                            BleHelper.getHelper().disconnectGatt();
                            break;
                        }
                        break;
                    case 11:
                        YCBTLog.e("蓝牙正在开启");
                        break;
                    case 12:
                        YCBTLog.e("蓝牙已开启，开始连接");
                        Reconnect.getInstance().wakeUp();
                        break;
                    case 13:
                        YCBTLog.e("蓝牙正在关闭");
                        break;
                }
            }
        }
    };

    public interface ReconnectResponse {
        void onReconnectFail();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void directConnect(final BluetoothDevice bluetoothDevice) {
        YCBTLog.d("Reconnect >>>> directConnect >>>>> 准备重连");
        YCBTClient.connectBleDevice(bluetoothDevice, new BleConnectResponse() { // from class: com.yucheng.ycbtsdk.gatt.Reconnect.2
            @Override // com.yucheng.ycbtsdk.response.BleConnectResponse
            public void onConnectResponse(int i2) {
                YCBTLog.d("Reconnect >>>> directConnect >>>>> " + i2);
                if (i2 == 1) {
                    String bindedDeviceMac = SPUtil.getBindedDeviceMac();
                    YCBTLog.d("searchConnect >>>> " + bindedDeviceMac);
                    if (TextUtils.isEmpty(bindedDeviceMac)) {
                        return;
                    }
                    Reconnect.this.searchConnect(bluetoothDevice.getAddress());
                }
            }
        });
    }

    public static Reconnect getInstance() {
        if (gReconnect == null) {
            synchronized (Reconnect.class) {
                if (gReconnect == null) {
                    gReconnect = new Reconnect();
                }
            }
        }
        return gReconnect;
    }

    private IntentFilter makeFilter() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        return intentFilter;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void searchConnect(final String str) {
        this.hasSearched = false;
        YCBTLog.d("Reconnect >>>> connectBleDevice >>>>> 准备重连");
        YCBTClient.startScanBle(new BleScanResponse() { // from class: com.yucheng.ycbtsdk.gatt.Reconnect.3
            @Override // com.yucheng.ycbtsdk.response.BleScanResponse
            public void onScanResponse(int i2, ScanDeviceBean scanDeviceBean) {
                if (scanDeviceBean != null) {
                    YCBTLog.d("onScanResponse >>>> " + scanDeviceBean.getDeviceMac());
                    if (str.equals(scanDeviceBean.getDeviceMac())) {
                        YCBTClient.stopScanBle();
                        Reconnect.this.hasSearched = true;
                        YCBTClient.connectBleDevice(scanDeviceBean.device, new BleConnectResponse() { // from class: com.yucheng.ycbtsdk.gatt.Reconnect.3.1
                            @Override // com.yucheng.ycbtsdk.response.BleConnectResponse
                            public void onConnectResponse(int i3) {
                                YCBTLog.d("Reconnect >>>> connectBleDevice >>>>> " + i3);
                            }
                        });
                    }
                }
            }
        }, 5);
    }

    public void init(Context context, boolean z) {
        BroadcastReceiver broadcastReceiver;
        Context context2 = this.mContext;
        if (context2 != null && (broadcastReceiver = this.mReceiver) != null) {
            context2.unregisterReceiver(broadcastReceiver);
        }
        this.mContext = context;
        if (Build.VERSION.SDK_INT >= 33) {
            context.registerReceiver(this.mReceiver, makeFilter(), 2);
        } else {
            context.registerReceiver(this.mReceiver, makeFilter());
        }
        this.isReconnect = z;
        if (this.isStartThread) {
            return;
        }
        this.isStartThread = true;
        this.thread.start();
    }

    public void registerReconnectResponse(ReconnectResponse reconnectResponse) {
        this.reconnectResponseList.add(reconnectResponse);
    }

    public void resetFailedCount() {
        this.reConnectCount = 0;
    }

    public void resetReconnectTime() {
        YCBTLog.e("resetReconnectTime");
        this.reconnectTime = 20000;
        this.firstConnectCount = 0;
    }

    public void setReconnect(boolean z) {
        this.isReconnect = z;
    }

    public void unRegisterReconnectResponse(ReconnectResponse reconnectResponse) {
        this.reconnectResponseList.remove(reconnectResponse);
    }

    public void wakeUp() {
        synchronized (this.lock) {
            this.lock.notify();
        }
    }
}
