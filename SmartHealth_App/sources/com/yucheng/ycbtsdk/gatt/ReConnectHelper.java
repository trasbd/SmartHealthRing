package com.yucheng.ycbtsdk.gatt;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.dd.plist.ASCIIPropertyListParser;
import com.jieli.jl_bt_ota.util.BluetoothUtil;
import com.yucheng.ycbtsdk.bean.ScanDeviceBean;
import com.yucheng.ycbtsdk.gatt.interfaces.BleEventCallback;
import com.yucheng.ycbtsdk.gatt.model.BleScanInfo;
import com.yucheng.ycbtsdk.gatt.model.BleScanMessage;
import com.yucheng.ycbtsdk.utils.BluetoothUtils;
import com.yucheng.ycbtsdk.utils.YCBTLog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* loaded from: classes5.dex */
public class ReConnectHelper {
    private static final long FAILED_DELAY = 3000;
    private static final int MSG_PROCESS_TASK = 2;
    private static final int MSG_RECONNECT_TIMEOUT = 1;
    private static final long RECONNECT_TIMEOUT = 65000;
    private static final long SCAN_TIMEOUT = 3000;
    private BleEventCallback bleEventCallback;
    private BleHelper mBleHelper;
    private Context mContext;
    private ArrayList<ReconnectParam> mParams = new ArrayList<>();
    private Map<String, BleScanMessage> mBleAdvCache = new HashMap();
    private Handler mUIHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.yucheng.ycbtsdk.gatt.ReConnectHelper.1
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i2 = message.what;
            if (i2 == 1) {
                ReConnectHelper.this.stopBtScan();
                ReConnectHelper.this.mParams.clear();
            } else if (i2 == 2) {
                ReConnectHelper.this.processReconnectTask();
            } else {
                Object obj = message.obj;
                if (obj instanceof String) {
                    ReConnectHelper.this.removeParam((String) obj);
                }
            }
            return true;
        }
    });

    public static class ReconnectParam {
        private String connectAddress;
        private String deviceAddress;
        private boolean isUseNewADV;

        public ReconnectParam(String str, boolean z) {
            this.deviceAddress = str;
            this.isUseNewADV = z;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            ReconnectParam reconnectParam = (ReconnectParam) obj;
            return this.isUseNewADV == reconnectParam.isUseNewADV && Objects.equals(this.deviceAddress, reconnectParam.deviceAddress);
        }

        public String getConnectAddress() {
            return this.connectAddress;
        }

        public String getDeviceAddress() {
            return this.deviceAddress;
        }

        public int hashCode() {
            return Objects.hash(this.deviceAddress, Boolean.valueOf(this.isUseNewADV));
        }

        public boolean isUseNewADV() {
            return this.isUseNewADV;
        }

        public void setConnectAddress(String str) {
            this.connectAddress = str;
        }

        public String toString() {
            return "ReconnectParam{deviceAddress='" + this.deviceAddress + "', connectAddress='" + this.connectAddress + "', isUseNewADV=" + this.isUseNewADV + ASCIIPropertyListParser.DICTIONARY_END_TOKEN;
        }
    }

    public ReConnectHelper(Context context, BleHelper bleHelper) {
        BleEventCallback bleEventCallback = new BleEventCallback() { // from class: com.yucheng.ycbtsdk.gatt.ReConnectHelper.2
            @Override // com.yucheng.ycbtsdk.gatt.interfaces.BleEventCallback, com.yucheng.ycbtsdk.gatt.interfaces.IBleEventCallback
            public void onAdapterChange(boolean z) {
                ReConnectHelper.this.isReconnecting();
            }

            @Override // com.yucheng.ycbtsdk.gatt.interfaces.BleEventCallback, com.yucheng.ycbtsdk.gatt.interfaces.IBleEventCallback
            public void onBleConnection(BluetoothDevice bluetoothDevice, int i2) {
                if (!ReConnectHelper.this.isReconnecting() || bluetoothDevice == null) {
                    return;
                }
                if (ReConnectHelper.this.isReconnectDevice(bluetoothDevice, (BleScanMessage) ReConnectHelper.this.mBleAdvCache.get(bluetoothDevice.getAddress()))) {
                    YCBTLog.d("onBleConnection : $device, status = $status, $advMsg");
                    if (i2 == 2) {
                        YCBTLog.d("onBleConnection : removeParam >>> " + bluetoothDevice.getAddress());
                        ReConnectHelper.this.removeParam(bluetoothDevice.getAddress());
                    } else if (i2 == 0) {
                        YCBTLog.d("-onConnection- resume reconnect task.");
                        ReConnectHelper.this.mUIHandler.sendEmptyMessage(2);
                    }
                }
            }

            @Override // com.yucheng.ycbtsdk.gatt.interfaces.BleEventCallback, com.yucheng.ycbtsdk.gatt.interfaces.IBleEventCallback
            public void onDiscoveryBle(BluetoothDevice bluetoothDevice, BleScanInfo bleScanInfo) {
                if (!ReConnectHelper.this.isReconnecting() || bluetoothDevice == null) {
                    return;
                }
                ScanDeviceBean scanDeviceInfo = BluetoothUtils.parseScanDeviceInfo(bleScanInfo.getScanResult());
                if (scanDeviceInfo != null) {
                    ReConnectHelper.this.mBleAdvCache.put(bluetoothDevice.getAddress(), new BleScanMessage().setRssi(bleScanInfo.getRssi()).setRawData(bleScanInfo.getRawData()).setOldBleAddress(scanDeviceInfo.getAdvMac()));
                }
                ReConnectHelper reConnectHelper = ReConnectHelper.this;
                boolean zIsReconnectDevice = reConnectHelper.isReconnectDevice(bluetoothDevice, (BleScanMessage) reConnectHelper.mBleAdvCache.get(bluetoothDevice.getAddress()));
                YCBTLog.d("onDiscoveryBle : " + bluetoothDevice.getAddress() + ", isReconnectDevice = " + zIsReconnectDevice);
                YCBTLog.d("onDiscoveryBle :" + scanDeviceInfo);
                if (zIsReconnectDevice) {
                    ReConnectHelper.this.stopBtScan();
                    ReconnectParam cacheParam = ReConnectHelper.this.getCacheParam(bluetoothDevice.getAddress());
                    if (cacheParam != null) {
                        cacheParam.connectAddress = bluetoothDevice.getAddress();
                    }
                    ReConnectHelper.this.mBleHelper.connectBleDevice(bluetoothDevice);
                }
            }

            @Override // com.yucheng.ycbtsdk.gatt.interfaces.BleEventCallback, com.yucheng.ycbtsdk.gatt.interfaces.IBleEventCallback
            public void onDiscoveryBleChange(boolean z) {
                ReConnectHelper.this.isReconnecting();
            }
        };
        this.bleEventCallback = bleEventCallback;
        this.mContext = context;
        this.mBleHelper = bleHelper;
        bleHelper.registerBleEventCallback(bleEventCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ReconnectParam getCacheParam(String str) {
        if (!BluetoothAdapter.checkBluetoothAddress(str)) {
            return null;
        }
        BleScanMessage bleScanMessage = this.mBleAdvCache.get(str);
        Iterator<ReconnectParam> it2 = this.mParams.iterator();
        while (it2.hasNext()) {
            ReconnectParam next = it2.next();
            if (next.getDeviceAddress().equals(str) || (bleScanMessage != null && next.getDeviceAddress().equals(bleScanMessage.getOldBleAddress()))) {
                return next;
            }
        }
        return null;
    }

    private BluetoothDevice getSystemConnectedDevice() {
        List<BluetoothDevice> systemConnectedBtDeviceList = BluetoothUtil.getSystemConnectedBtDeviceList(this.mContext);
        if (systemConnectedBtDeviceList != null && !systemConnectedBtDeviceList.isEmpty()) {
            for (BluetoothDevice bluetoothDevice : systemConnectedBtDeviceList) {
                if (isReconnectDevice(bluetoothDevice, null)) {
                    return bluetoothDevice;
                }
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isReconnectDevice(BluetoothDevice bluetoothDevice, BleScanMessage bleScanMessage) {
        if (bluetoothDevice == null || this.mParams.isEmpty()) {
            return false;
        }
        Iterator<ReconnectParam> it2 = this.mParams.iterator();
        while (true) {
            boolean zEquals = false;
            while (it2.hasNext()) {
                ReconnectParam next = it2.next();
                if (bleScanMessage != null) {
                    zEquals = Objects.equals(next.deviceAddress, bleScanMessage.getOldBleAddress());
                }
                if (zEquals || Objects.equals(next.deviceAddress, bluetoothDevice.getAddress())) {
                    zEquals = true;
                }
            }
            return zEquals;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processReconnectTask() {
        if (this.mBleHelper.isBleScanning()) {
            this.mUIHandler.sendEmptyMessageDelayed(2, 3000L);
            return;
        }
        BluetoothDevice systemConnectedDevice = getSystemConnectedDevice();
        if (systemConnectedDevice != null) {
            ReconnectParam cacheParam = getCacheParam(systemConnectedDevice.getAddress());
            if (cacheParam != null) {
                cacheParam.setConnectAddress(systemConnectedDevice.getAddress());
            }
            this.mBleHelper.connectBleDevice(systemConnectedDevice);
            return;
        }
        if (this.mBleHelper.startLeScan(3000L)) {
            return;
        }
        YCBTLog.d("processReconnectTask : start Le scan failed.");
        this.mUIHandler.sendEmptyMessageDelayed(2, 3000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeParam(String str) {
        ReconnectParam cacheParam = getCacheParam(str);
        if (cacheParam == null) {
            return;
        }
        if (this.mParams.remove(cacheParam)) {
            this.mUIHandler.removeMessages(cacheParam.hashCode());
            if (this.mParams.isEmpty()) {
                this.mUIHandler.removeMessages(1);
                return;
            }
        }
        this.mUIHandler.sendEmptyMessage(2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopBtScan() {
        this.mBleHelper.stopLeScan();
    }

    public boolean isMatchAddress(String str, String str2) {
        ReconnectParam cacheParam = getCacheParam(str);
        if (cacheParam == null || !BluetoothAdapter.checkBluetoothAddress(str2)) {
            return false;
        }
        return str2 == cacheParam.deviceAddress || str2 == cacheParam.connectAddress;
    }

    public boolean isReconnecting() {
        return this.mUIHandler.hasMessages(1);
    }

    public boolean putParam(ReconnectParam reconnectParam) {
        if (reconnectParam == null) {
            return false;
        }
        if (this.mParams.contains(reconnectParam)) {
            return true;
        }
        if (!this.mParams.add(reconnectParam)) {
            return false;
        }
        this.mUIHandler.sendEmptyMessageDelayed(this.mParams.hashCode(), RECONNECT_TIMEOUT);
        if (!isReconnecting()) {
            Handler handler = this.mUIHandler;
            handler.sendMessageDelayed(handler.obtainMessage(1, reconnectParam.deviceAddress), 75000L);
            this.mUIHandler.sendEmptyMessage(2);
        }
        return true;
    }

    public void release() {
        this.mParams.clear();
        this.mBleAdvCache.clear();
        this.mUIHandler.removeCallbacksAndMessages(null);
    }
}
