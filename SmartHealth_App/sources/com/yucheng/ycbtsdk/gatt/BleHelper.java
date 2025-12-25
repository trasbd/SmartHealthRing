package com.yucheng.ycbtsdk.gatt;

import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothHeadset;
import android.bluetooth.BluetoothHidDevice;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.login.widget.ToolTipPopup;
import com.jieli.jl_bt_ota.util.BluetoothUtil;
import com.yucheng.ycbtsdk.Constants;
import com.yucheng.ycbtsdk.YCBTClient;
import com.yucheng.ycbtsdk.bean.AdvUnit;
import com.yucheng.ycbtsdk.bean.ScanDeviceBean;
import com.yucheng.ycbtsdk.core.CMD;
import com.yucheng.ycbtsdk.core.YCBTClientImpl;
import com.yucheng.ycbtsdk.gatt.ReConnectHelper;
import com.yucheng.ycbtsdk.gatt.interfaces.BleEventCallback;
import com.yucheng.ycbtsdk.gatt.model.BleDevice;
import com.yucheng.ycbtsdk.gatt.model.BleScanInfo;
import com.yucheng.ycbtsdk.jl.WatchManager;
import com.yucheng.ycbtsdk.utils.AppUtil;
import com.yucheng.ycbtsdk.utils.BleDeviceUtil;
import com.yucheng.ycbtsdk.utils.BleUtil;
import com.yucheng.ycbtsdk.utils.BluetoothUtils;
import com.yucheng.ycbtsdk.utils.ByteUtil;
import com.yucheng.ycbtsdk.utils.InnerUtils;
import com.yucheng.ycbtsdk.utils.PermissionUtil;
import com.yucheng.ycbtsdk.utils.SPUtil;
import com.yucheng.ycbtsdk.utils.YCBTLog;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import no.nordicsemi.android.dfu.DfuBaseService;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.paho.android.service.MqttServiceConstants;

/* loaded from: classes5.dex */
public class BleHelper {
    private static final int DEFAULT_CALLBACK_TIMEOUT = 6000;
    private static final int DEFAULT_CONNECT_BLE_TIMEOUT = 10000;
    private static int DEFAULT_MTU = 500;
    private static int DEVICE_MTU = 185;
    private static final int MAX_RETRY_CONNECT_COUNT = 1;
    private static final int MIN_CONNECT_TIME = 5000;
    private static final int MSG_BLE_CHARACTERISTIC_ENABLE_TIMEOUT = 4118;
    private static final int MSG_BLE_DISCOVER_SERVICES_CALLBACK_TIMEOUT = 4117;
    private static final int MSG_CHANGE_BLE_MTU_TIMEOUT = 4116;
    private static final int MSG_CONNECT_BLE_TIMEOUT = 4113;
    private static final int MSG_NOTIFY_BLE_TIMEOUT = 4115;
    private static final int MSG_SCAN_BLE_TIMEOUT = 4112;
    public static int MTU;
    private static BleHelper bleHelper;
    private Context bleContext;
    private BluetoothA2dp bluetoothA2dp;
    private BluetoothHeadset bluetoothHeadset;
    private BluetoothHidDevice bluetoothHidDevice;
    private boolean isBleScanning;
    private boolean isEnableWriteChar;
    private boolean isJLEnableNotifyChar;
    private boolean isUartEnableNotifyChar;
    private GattBleResponse mBleResponse;
    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothLeScanner mBluetoothLeScanner;
    private volatile BluetoothDevice mConnectingBtDevice;
    private BluetoothGattCharacteristic mJLNotifyChar;
    private BluetoothGattCharacteristic mJLWriteChar;
    private ReConnectHelper mReConnectHelper;
    private BluetoothGattCharacteristic mUartNotifyChar;
    private BluetoothGattCharacteristic mUartWriteChar;
    private volatile BluetoothDevice mUsingDevice;
    private BluetoothGattCharacteristic mWriteChar;
    private BluetoothGattCharacteristic mWriteChar2;
    private MBroadcastReceiver myReceive;
    private int productId;
    private List<String> productIds;
    private final Map<String, BleDevice> mConnectedGattMap = new HashMap();
    private final List<BluetoothDevice> mDiscoveredBleDevices = new ArrayList();
    private int mRetryConnectCount = 0;
    private long startConnectTime = 0;
    private int mConnectBleTimeout = 10000;
    private final BleEventCallbackManager mCallbackManager = new BleEventCallbackManager();
    long reconnectTimeMillis = 15000;
    public int maxScanDevice = 0;
    List<ScanDeviceBean> scanDeviceBeanList = new ArrayList();
    ExecutorService mExecutor = Executors.newSingleThreadExecutor();
    private int maxGattReconnectTimes = 3;
    private BluetoothGatt mConnectingGatt = null;
    private BluetoothGattCallback mBluetoothGattCallback = new BluetoothGattCallback() { // from class: com.yucheng.ycbtsdk.gatt.BleHelper.1
        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            BluetoothDevice device;
            YCBTLog.e("BLE Data:" + ByteUtil.byteToString(bluetoothGattCharacteristic.getValue()) + " uuid:" + bluetoothGattCharacteristic.getUuid().toString());
            try {
                BleHelper.this.mBleResponse.bleDataResponse(0, bluetoothGattCharacteristic.getValue(), bluetoothGattCharacteristic.getUuid().toString());
            } catch (Exception e2) {
                YCBTLog.e(e2.getMessage());
                e2.printStackTrace();
            }
            if (bluetoothGatt == null || !AppUtil.checkHasConnectPermission(BleHelper.this.bleContext) || (device = bluetoothGatt.getDevice()) == null) {
                return;
            }
            UUID uuid = bluetoothGattCharacteristic.getUuid();
            byte[] value = bluetoothGattCharacteristic.getValue();
            BluetoothGattService service = bluetoothGattCharacteristic.getService();
            BleHelper.this.mCallbackManager.onBleDataNotification(device, service != null ? service.getUuid() : null, uuid, value);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i2) {
            YCBTLog.e("onCharacteristicRead  status " + i2);
            YCBTLog.e("onCharacteristicRead characteristic.getvalue=" + bluetoothGattCharacteristic.getStringValue(0));
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i2) {
            YCBTLog.e("onCharacteristicWrite  status " + i2 + StringUtils.SPACE + ByteUtil.byteToString(bluetoothGattCharacteristic.getValue()) + StringUtils.SPACE + Thread.currentThread().toString() + "--" + BleHelper.this.lists.size());
            if (BleHelper.this.lists.size() > 0) {
                BleHelper.this.sendDataToDevice(bluetoothGattCharacteristic);
            } else {
                try {
                    BleHelper.this.mBleResponse.bleOnCharacteristicWrite(i2, bluetoothGattCharacteristic.getValue(), bluetoothGattCharacteristic.getUuid().toString());
                } catch (Exception e2) {
                    YCBTLog.e(e2.getMessage());
                    e2.printStackTrace();
                }
            }
            if (bluetoothGatt == null || bluetoothGatt.getDevice() == null || !AppUtil.checkHasConnectPermission(BleHelper.this.bleContext)) {
                return;
            }
            BluetoothDevice device = bluetoothGatt.getDevice();
            UUID uuid = bluetoothGattCharacteristic.getUuid();
            BluetoothGattService service = bluetoothGattCharacteristic.getService();
            BleHelper.this.mCallbackManager.onBleWriteStatus(device, service != null ? service.getUuid() : null, uuid, bluetoothGattCharacteristic.getValue(), i2);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i2, int i3) throws InterruptedException {
            BluetoothDevice device;
            if (bluetoothGatt == null || (device = bluetoothGatt.getDevice()) == null) {
                return;
            }
            YCBTLog.d(String.format(Locale.getDefault(), "onConnectionStateChange : device : %s, status = %d, newState = %d.", device.getAddress(), Integer.valueOf(i2), Integer.valueOf(i3)));
            if (i3 == 0 || i3 == 3 || i3 == 2) {
                BleHelper.this.mConnectingGatt = null;
                BleHelper.this.stopConnectTimeout();
                BleHelper.this.setConnectingBtDevice(null);
                if (i3 == 2) {
                    BleHelper.this.updateCacheInfo(device);
                    BleHelper.this.callBackState(6);
                    BleHelper.this.mRetryConnectCount = 0;
                    boolean zDiscoverServices = bluetoothGatt.discoverServices();
                    BleHelper.this.putConnectedGattInMap(device.getAddress(), bluetoothGatt);
                    if (!zDiscoverServices) {
                        BleHelper.this.disconnectBleDevice(device);
                        return;
                    } else {
                        BleHelper.this.mHandler.removeMessages(BleHelper.MSG_BLE_DISCOVER_SERVICES_CALLBACK_TIMEOUT);
                        BleHelper.this.mHandler.sendMessageDelayed(BleHelper.this.mHandler.obtainMessage(BleHelper.MSG_BLE_DISCOVER_SERVICES_CALLBACK_TIMEOUT, device), ToolTipPopup.DEFAULT_POPUP_DISPLAY_TIME);
                        return;
                    }
                }
                if (i3 == 3) {
                    BleHelper.this.callBackState(4);
                } else {
                    BleHelper.this.callBackState(3);
                    BleHelper.this.resetCache();
                }
                BleHelper.this.removeConnectedBle(device);
                AppUtil.refreshBleDeviceCache(BleHelper.this.bleContext, bluetoothGatt);
                bluetoothGatt.close();
                try {
                    Thread.sleep(200L);
                    long jCurrentTimeMillis = System.currentTimeMillis() - BleHelper.this.startConnectTime;
                    YCBTLog.d("onConnectionStateChange >> usedConnectTime = " + jCurrentTimeMillis + ", limit time = 5000");
                    if (i2 == 133 && jCurrentTimeMillis < 5000) {
                        if (BleHelper.this.mRetryConnectCount < BleHelper.this.maxGattReconnectTimes) {
                            BleHelper.access$308(BleHelper.this);
                            BleHelper.this.connectBleDevice(device);
                            return;
                        }
                        BleHelper.this.mRetryConnectCount = 0;
                    }
                    BleHelper.this.handleBleConnection(device, i3);
                } catch (InterruptedException e2) {
                    throw new RuntimeException(e2);
                }
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorRead(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i2) {
            Log.e(CMD.BLETAG, "onDescriptorRead  status " + i2);
            YCBTLog.e("onDescriptorRead descriptor.getValue=" + ByteUtil.byteToStr(bluetoothGattDescriptor.getValue()));
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i2) {
            BluetoothDevice device;
            UUID uuid;
            if (bluetoothGatt == null || !AppUtil.checkHasConnectPermission(BleHelper.this.bleContext) || (device = bluetoothGatt.getDevice()) == null || bluetoothGattDescriptor == null) {
                return;
            }
            YCBTLog.e("onDescriptorWrite  status " + i2 + " descriptor:" + bluetoothGattDescriptor.getUuid().toString() + " Characteristic:" + bluetoothGattDescriptor.getCharacteristic().getUuid().toString());
            if (!BleHelper.this.isEnableWriteChar && BleHelper.this.mWriteChar != null) {
                BleHelper bleHelper2 = BleHelper.this;
                bleHelper2.setNotificationForCharacteristic(bluetoothGatt, bleHelper2.mWriteChar, BluetoothGattDescriptor.ENABLE_INDICATION_VALUE);
                BleHelper.this.isEnableWriteChar = true;
                return;
            }
            if (!BleHelper.this.isJLEnableNotifyChar && BleHelper.this.mJLNotifyChar != null) {
                BleHelper bleHelper3 = BleHelper.this;
                bleHelper3.setNotificationForCharacteristic(bluetoothGatt, bleHelper3.mJLNotifyChar, BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
                BleHelper.this.isJLEnableNotifyChar = true;
                return;
            }
            if (!BleHelper.this.isUartEnableNotifyChar && BleHelper.this.mUartNotifyChar != null) {
                BleHelper bleHelper4 = BleHelper.this;
                bleHelper4.setNotificationForCharacteristic(bluetoothGatt, bleHelper4.mUartNotifyChar, BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
                BleHelper.this.isUartEnableNotifyChar = true;
                return;
            }
            if (BleHelper.this.mWriteChar == null && BleHelper.this.mWriteChar2 == null && BleHelper.this.mJLWriteChar != null && BleHelper.this.mJLNotifyChar != null) {
                YCBTClientImpl.getInstance().isForceOta = true;
                SPUtil.saveChipScheme(3);
                SPUtil.saveBindedDeviceMac(BleDeviceUtil.getMacSubOne(bluetoothGatt.getDevice().getAddress()));
            }
            BleHelper.this.mHandler.removeMessages(4118);
            BleHelper.this.callBackState(9);
            BluetoothGattCharacteristic characteristic = bluetoothGattDescriptor.getCharacteristic();
            UUID uuid2 = null;
            if (characteristic != null) {
                uuid = characteristic.getUuid();
                BluetoothGattService service = characteristic.getService();
                if (service != null) {
                    uuid2 = service.getUuid();
                }
            } else {
                uuid = null;
            }
            BleHelper.this.mCallbackManager.onBleNotificationStatus(device, uuid2, uuid, i2);
            BleHelper.this.handleBleConnectedEvent(device);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onMtuChanged(BluetoothGatt bluetoothGatt, int i2, int i3) {
            BluetoothDevice device;
            YCBTLog.e("MTU change.  MTU==" + BleHelper.MTU + "--mtu==" + i2 + "--status==" + i3);
            if (bluetoothGatt == null || !AppUtil.checkHasConnectPermission(BleHelper.this.bleContext) || (device = bluetoothGatt.getDevice()) == null) {
                return;
            }
            BleHelper.this.mCallbackManager.onBleDataBlockChanged(device, i2, i3);
            BleDevice connectedBle = BleHelper.this.getConnectedBle(device);
            if (i3 == 0) {
                if (BleHelper.this.mHandler.hasMessages(BleHelper.MSG_CHANGE_BLE_MTU_TIMEOUT)) {
                    BleHelper.this.stopChangeMtu();
                }
                connectedBle.setMtu(i2);
                BleHelper.MTU = i2;
                BleHelper.this.mOnServicesDiscovered(bluetoothGatt);
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onPhyRead(BluetoothGatt bluetoothGatt, int i2, int i3, int i4) {
            Log.e("phy", "txPhy:" + i2 + " rxPhy:" + i3 + " status:" + i4);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onReliableWriteCompleted(BluetoothGatt bluetoothGatt, int i2) {
            YCBTLog.e("onReliableWriteCompleted  status " + i2);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i2) {
            BluetoothDevice device;
            boolean zRequestMtu;
            if (bluetoothGatt == null || !AppUtil.checkHasConnectPermission(BleHelper.this.bleContext) || (device = bluetoothGatt.getDevice()) == null) {
                return;
            }
            BleHelper.this.mHandler.removeMessages(BleHelper.MSG_BLE_DISCOVER_SERVICES_CALLBACK_TIMEOUT);
            BleHelper.this.mCallbackManager.onBleServiceDiscovery(device, i2, bluetoothGatt.getServices());
            if (i2 == 0) {
                BleHelper.this.mHandler.removeMessages(7);
                if (BleHelper.MTU == BleHelper.DEVICE_MTU) {
                    BleHelper.this.mOnServicesDiscovered(bluetoothGatt);
                    return;
                } else {
                    YCBTLog.e("change MTU.  mtu = " + BleHelper.MTU);
                    zRequestMtu = bluetoothGatt.requestMtu(BleHelper.MTU);
                }
            } else {
                zRequestMtu = false;
            }
            if (zRequestMtu) {
                return;
            }
            BleHelper.this.disconnectBleDevice(device);
        }
    };
    private Handler mHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.yucheng.ycbtsdk.gatt.BleHelper.2
        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            List<BluetoothGattService> services;
            switch (message.what) {
                case 4112:
                    if (BleHelper.this.isBleScanning) {
                        BleHelper.this.stopLeScan();
                    }
                    return false;
                case 4113:
                    if (message.obj instanceof BluetoothDevice) {
                        BleHelper.this.clearConnectingGatt();
                        BluetoothDevice bluetoothDevice = (BluetoothDevice) message.obj;
                        if (BleHelper.this.getConnectedBle(bluetoothDevice) == null) {
                            BleHelper.this.handleBleConnection(bluetoothDevice, 0);
                        }
                        BleHelper.this.setConnectingBtDevice(null);
                        BleHelper.this.callBackState(1);
                    }
                    return false;
                case 4114:
                default:
                    return false;
                case 4115:
                case 4118:
                    Object obj = message.obj;
                    if (obj instanceof BluetoothDevice) {
                        BleHelper.this.disconnectBleDevice((BluetoothDevice) obj);
                    }
                    return false;
                case BleHelper.MSG_CHANGE_BLE_MTU_TIMEOUT /* 4116 */:
                    BluetoothDevice bluetoothDevice2 = (BluetoothDevice) message.obj;
                    BleDevice connectedBle = BleHelper.this.getConnectedBle(bluetoothDevice2);
                    YCBTLog.d("-MSG_CHANGE_BLE_MTU_TIMEOUT- request mtu timeout, device : " + bluetoothDevice2.getAddress() + ", " + connectedBle);
                    if (connectedBle != null) {
                        BleHelper.this.handleBleConnectedEvent(bluetoothDevice2);
                    } else {
                        BleHelper.this.handleBleConnection(bluetoothDevice2, 0);
                    }
                    return false;
                case BleHelper.MSG_BLE_DISCOVER_SERVICES_CALLBACK_TIMEOUT /* 4117 */:
                    Object obj2 = message.obj;
                    if (obj2 instanceof BluetoothDevice) {
                        BluetoothDevice bluetoothDevice3 = (BluetoothDevice) obj2;
                        if (BluetoothUtil.deviceEquals(bluetoothDevice3, BleHelper.this.mUsingDevice)) {
                            BleDevice connectedBle2 = BleHelper.this.getConnectedBle(bluetoothDevice3);
                            if (connectedBle2 == null || (services = connectedBle2.getGatt().getServices()) == null || services.size() <= 0) {
                                YCBTLog.d("discover services timeout.");
                                BleHelper.this.disconnectBleDevice(bluetoothDevice3);
                            } else {
                                BleHelper.this.mBluetoothGattCallback.onServicesDiscovered(connectedBle2.getGatt(), 0);
                            }
                        }
                    }
                    return false;
            }
        }
    });
    private int sendDataCount = 0;
    private List<byte[]> lists = new ArrayList();
    private ScanCallback mScanCallback = new ScanCallback() { // from class: com.yucheng.ycbtsdk.gatt.BleHelper.7
        @Override // android.bluetooth.le.ScanCallback
        public void onBatchScanResults(List<ScanResult> list) {
            super.onBatchScanResults(list);
            YCBTLog.e("onBatchScanResults results.size()=" + list.size());
            for (int i2 = 0; i2 < list.size(); i2++) {
                list.get(i2);
            }
        }

        @Override // android.bluetooth.le.ScanCallback
        public void onScanFailed(int i2) {
            super.onScanFailed(i2);
            YCBTLog.e("onScanFailed=" + i2);
            BleHelper.this.stopLeScan();
            if (BleHelper.this.mBluetoothAdapter == null || i2 != 2 || BleHelper.this.mBleResponse == null) {
                return;
            }
            BleHelper.this.mBleResponse.bleScanResponse(3, null);
        }

        @Override // android.bluetooth.le.ScanCallback
        public void onScanResult(int i2, ScanResult scanResult) {
            if (scanResult == null || scanResult.getScanRecord() == null) {
                return;
            }
            BleHelper.this.filterDevice(scanResult.getDevice(), scanResult, scanResult.getRssi(), scanResult.getScanRecord().getBytes(), scanResult.isConnectable());
        }
    };
    private int discoverCount = 0;
    private BluetoothProfile.ServiceListener serviceListener = new BluetoothProfile.ServiceListener() { // from class: com.yucheng.ycbtsdk.gatt.BleHelper.8
        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceConnected(int i2, BluetoothProfile bluetoothProfile) {
            YCBTLog.d("onServiceConnected " + i2);
            if (i2 == 2) {
                BleHelper.this.bluetoothA2dp = (BluetoothA2dp) bluetoothProfile;
            } else if (i2 == 1) {
                BleHelper.this.bluetoothHeadset = (BluetoothHeadset) bluetoothProfile;
            } else {
                if (i2 != 19 || Build.VERSION.SDK_INT < 28) {
                    return;
                }
                BleHelper.this.bluetoothHidDevice = (BluetoothHidDevice) bluetoothProfile;
            }
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceDisconnected(int i2) {
            YCBTLog.d("onServiceDisconnected " + i2);
            if (i2 == 2) {
                BleHelper.this.bluetoothA2dp = null;
                return;
            }
            if (i2 == 1) {
                BleHelper.this.bluetoothHeadset = null;
            } else {
                if (i2 != 19 || Build.VERSION.SDK_INT < 28) {
                    return;
                }
                BleHelper.this.bluetoothHidDevice = null;
            }
        }
    };
    private boolean isDiscovering = false;

    private class MBroadcastReceiver extends BroadcastReceiver {
        private MBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (!"android.bluetooth.device.action.FOUND".equals(intent.getAction())) {
                if ("android.bluetooth.adapter.action.DISCOVERY_STARTED".equals(intent.getAction())) {
                    YCBTLog.e("BlutoothReceive  开始搜索");
                    return;
                } else {
                    if ("android.bluetooth.adapter.action.DISCOVERY_FINISHED".equals(intent.getAction())) {
                        YCBTLog.e("BlutoothReceive  完成搜索");
                        boolean unused = BleHelper.this.isDiscovering;
                        return;
                    }
                    return;
                }
            }
            BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
            if (bluetoothDevice == null || bluetoothDevice.getAddress() == null || BleHelper.this.mUsingDevice == null || !bluetoothDevice.getAddress().equals(BleHelper.this.mUsingDevice.getAddress())) {
                return;
            }
            BleHelper.this.isDiscovering = true;
            if (BleHelper.this.mBluetoothAdapter.isDiscovering()) {
                BleHelper.this.mBluetoothAdapter.cancelDiscovery();
            }
            if (BleHelper.this.bleContext != null && BleHelper.this.myReceive != null) {
                BleHelper.this.bleContext.unregisterReceiver(BleHelper.this.myReceive);
                BleHelper.this.myReceive = null;
            }
            BleHelper.this.createBond();
        }
    }

    static /* synthetic */ int access$308(BleHelper bleHelper2) {
        int i2 = bleHelper2.mRetryConnectCount;
        bleHelper2.mRetryConnectCount = i2 + 1;
        return i2;
    }

    static /* synthetic */ int access$3408(BleHelper bleHelper2) {
        int i2 = bleHelper2.sendDataCount;
        bleHelper2.sendDataCount = i2 + 1;
        return i2;
    }

    private void addConnectedDevices() {
        try {
            ArrayList<BluetoothDevice> arrayList = new ArrayList();
            if (Build.VERSION.SDK_INT < 31 || InnerUtils.isLooseSearch()) {
                arrayList.addAll(BleDeviceUtil.getSystemConnectedDevice());
            }
            arrayList.addAll(BleDeviceUtil.getBleConnectedDevice(this.bleContext));
            for (BluetoothDevice bluetoothDevice : arrayList) {
                ScanDeviceBean scanDeviceBean = new ScanDeviceBean();
                scanDeviceBean.setDeviceMac(bluetoothDevice.getAddress());
                String name = "";
                if (TextUtils.isEmpty(bluetoothDevice.getName())) {
                    scanDeviceBean.setDeviceName((String) SPUtil.get(bluetoothDevice.getAddress(), ""));
                }
                if (!TextUtils.isEmpty(bluetoothDevice.getName())) {
                    name = bluetoothDevice.getName();
                }
                scanDeviceBean.setDeviceName(name);
                int i2 = 0;
                scanDeviceBean.setDeviceRssi(0);
                scanDeviceBean.device = bluetoothDevice;
                scanDeviceBean.setAdvMac(bluetoothDevice.getAddress());
                if (!TextUtils.isEmpty(scanDeviceBean.getDeviceName())) {
                    GattBleResponse gattBleResponse = this.mBleResponse;
                    if (gattBleResponse != null) {
                        gattBleResponse.bleScanResponse(0, scanDeviceBean);
                    }
                    while (true) {
                        if (i2 >= this.scanDeviceBeanList.size()) {
                            break;
                        }
                        if (this.scanDeviceBeanList.get(i2).getDeviceMac().equals(scanDeviceBean.getDeviceMac())) {
                            this.scanDeviceBeanList.remove(i2);
                            break;
                        }
                        i2++;
                    }
                    this.scanDeviceBeanList.add(scanDeviceBean);
                    YCBTLog.e("addConnectedDevices " + scanDeviceBean.getDeviceMac() + " name " + scanDeviceBean.getDeviceName());
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void clearConnectedBleDevices() {
        if (AppUtil.checkHasConnectPermission(this.bleContext) && !this.mConnectedGattMap.isEmpty()) {
            HashMap map = new HashMap(this.mConnectedGattMap);
            Iterator it2 = map.keySet().iterator();
            while (it2.hasNext()) {
                BleDevice bleDevice = (BleDevice) map.get((String) it2.next());
                if (bleDevice != null) {
                    bleDevice.getGatt().disconnect();
                    bleDevice.getGatt().close();
                }
            }
            this.mConnectedGattMap.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearConnectingGatt() {
        BluetoothGatt bluetoothGatt = this.mConnectingGatt;
        if (bluetoothGatt != null) {
            bluetoothGatt.disconnect();
            this.mConnectingGatt = null;
        }
    }

    private void closeA2dp() {
        BluetoothAdapter bluetoothAdapter;
        if (((Integer) SPUtil.get(Constants.FunctionConstant.ISHASCREATEBOND, 0)).intValue() != 1 || (bluetoothAdapter = this.mBluetoothAdapter) == null) {
            return;
        }
        BluetoothA2dp bluetoothA2dp = this.bluetoothA2dp;
        if (bluetoothA2dp != null) {
            bluetoothAdapter.closeProfileProxy(2, bluetoothA2dp);
            this.bluetoothA2dp = null;
        }
        BluetoothHeadset bluetoothHeadset = this.bluetoothHeadset;
        if (bluetoothHeadset != null) {
            this.mBluetoothAdapter.closeProfileProxy(1, bluetoothHeadset);
            this.bluetoothHeadset = null;
        }
    }

    private void connectA2dp() throws NoSuchMethodException, SecurityException {
        BluetoothHidDevice bluetoothHidDevice;
        if (this.bluetoothA2dp != null && this.mUsingDevice != null) {
            int connectionState = this.bluetoothA2dp.getConnectionState(this.mUsingDevice);
            YCBTLog.e("A2dp connectionState == " + connectionState);
            if (connectionState == 2) {
                return;
            }
            if (this.bluetoothA2dp != null) {
                try {
                    Method method = BluetoothA2dp.class.getMethod(MqttServiceConstants.CONNECT_ACTION, BluetoothDevice.class);
                    method.setAccessible(true);
                    YCBTLog.e("connectA2dp == " + ((Boolean) method.invoke(this.bluetoothA2dp, this.mUsingDevice)).booleanValue());
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            if (this.bluetoothHeadset != null) {
                try {
                    Method method2 = BluetoothHeadset.class.getMethod(MqttServiceConstants.CONNECT_ACTION, BluetoothDevice.class);
                    method2.setAccessible(true);
                    YCBTLog.e("connectHfp == " + ((Boolean) method2.invoke(this.bluetoothHeadset, this.mUsingDevice)).booleanValue());
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }
        }
        if (this.bluetoothHidDevice == null || this.mUsingDevice == null || Build.VERSION.SDK_INT < 28) {
            return;
        }
        int connectionState2 = this.bluetoothHidDevice.getConnectionState(this.mUsingDevice);
        YCBTLog.e("hid connectionState == " + connectionState2);
        if (connectionState2 == 2 || (bluetoothHidDevice = this.bluetoothHidDevice) == null) {
            return;
        }
        YCBTLog.e("connectHid == " + bluetoothHidDevice.connect(this.mUsingDevice));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createBond() {
        if (YCBTClient.connectState() == 10 && YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASCREATEBOND) && this.mUsingDevice != null) {
            YCBTLog.d("device type: " + this.mUsingDevice.getType());
            boolean zCreateBond = this.mUsingDevice.getType() == 3 ? BluetoothUtils.createBond(this.bleContext, this.mUsingDevice, 1) : false;
            if (!zCreateBond) {
                zCreateBond = BluetoothUtils.createBond(this.bleContext, this.mUsingDevice);
            }
            if (zCreateBond) {
                return;
            }
            YCBTLog.d("create bond fail");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BleDevice getConnectedBle(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            return null;
        }
        return this.mConnectedGattMap.get(bluetoothDevice.getAddress());
    }

    public static BleHelper getHelper() {
        if (bleHelper == null) {
            synchronized (BleHelper.class) {
                if (bleHelper == null) {
                    bleHelper = new BleHelper();
                }
            }
        }
        return bleHelper;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0082 A[Catch: Exception -> 0x0231, TryCatch #2 {Exception -> 0x0231, blocks: (B:3:0x0009, B:6:0x001a, B:9:0x0026, B:11:0x002c, B:13:0x0048, B:15:0x004e, B:20:0x007a, B:22:0x0082, B:25:0x008e, B:27:0x0093), top: B:56:0x0009 }] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x021c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.yucheng.ycbtsdk.bean.ScanDeviceBean getScanDeviceInfo(android.bluetooth.le.ScanResult r22, android.bluetooth.BluetoothDevice r23, java.lang.String r24) {
        /*
            Method dump skipped, instructions count: 566
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.ycbtsdk.gatt.BleHelper.getScanDeviceInfo(android.bluetooth.le.ScanResult, android.bluetooth.BluetoothDevice, java.lang.String):com.yucheng.ycbtsdk.bean.ScanDeviceBean");
    }

    private List<BleDevice> getSortList() {
        if (this.mConnectedGattMap.isEmpty()) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList(this.mConnectedGattMap.values());
        Collections.sort(arrayList, new Comparator() { // from class: com.yucheng.ycbtsdk.gatt.BleHelper$$ExternalSyntheticLambda0
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return BleHelper.lambda$getSortList$0((BleDevice) obj, (BleDevice) obj2);
            }
        });
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleBleConnectedEvent(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            YCBTLog.d("-handleBleConnectedEvent- device is null.");
            return;
        }
        stopChangeMtu();
        getConnectedBle(bluetoothDevice);
        handleBleConnection(bluetoothDevice, 2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleBleConnection(BluetoothDevice bluetoothDevice, int i2) {
        if (i2 == 0) {
            this.isEnableWriteChar = false;
            this.isJLEnableNotifyChar = false;
            this.isUartEnableNotifyChar = false;
        }
        if (i2 == 0 || i2 == 2) {
            this.mHandler.removeMessages(4115);
            this.startConnectTime = 0L;
        } else if (i2 == 1) {
            this.startConnectTime = System.currentTimeMillis();
        }
        YCBTLog.d("handleBleConnection >> device : " + bluetoothDevice + ", status : " + i2);
        this.mCallbackManager.onBleConnection(bluetoothDevice, i2);
    }

    private void handleDiscoveryBle(BluetoothDevice bluetoothDevice, BleScanInfo bleScanInfo) {
        this.mCallbackManager.onDiscoveryBle(bluetoothDevice, bleScanInfo);
    }

    private boolean isContainsProductIds(String str) {
        List<String> list;
        if (str == null || (list = this.productIds) == null || list.size() <= 0) {
            return false;
        }
        Iterator<String> it2 = this.productIds.iterator();
        while (it2.hasNext()) {
            if (str.contains(it2.next())) {
                return true;
            }
        }
        return false;
    }

    static /* synthetic */ int lambda$getSortList$0(BleDevice bleDevice, BleDevice bleDevice2) {
        if (bleDevice == null && bleDevice2 == null) {
            return 0;
        }
        if (bleDevice == null) {
            return 1;
        }
        if (bleDevice2 == null) {
            return -1;
        }
        return Long.compare(bleDevice2.getConnectedTime(), bleDevice.getConnectedTime());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mOnServicesDiscovered(BluetoothGatt bluetoothGatt) {
        String str;
        if (bluetoothGatt == null) {
            YCBTLog.e("mOnServicesDiscovered gatt: " + bluetoothGatt);
            return;
        }
        callBackState(7);
        YCBTLog.e("特征 gatt==" + bluetoothGatt + "--" + bluetoothGatt.getServices().size());
        List<BluetoothGattService> services = bluetoothGatt.getServices();
        Iterator<BluetoothGattService> it2 = services.iterator();
        while (true) {
            boolean z = false;
            String str2 = " mWriteChar2=";
            if (!it2.hasNext()) {
                break;
            }
            BluetoothGattService next = it2.next();
            if (next.getUuid().toString().equals(CMD.UUID_S) || next.getUuid().toString().equals(CMD.JL_UUID_SERVICE) || next.getUuid().toString().equals(CMD.UART_SERVICE_UUID)) {
                YCBTLog.e("service uuid: " + next.getUuid().toString());
                for (BluetoothGattCharacteristic bluetoothGattCharacteristic : next.getCharacteristics()) {
                    if (bluetoothGattCharacteristic.getUuid().toString().equals(CMD.UUID_C_1)) {
                        this.isEnableWriteChar = z;
                        this.mWriteChar = bluetoothGattCharacteristic;
                        YCBTLog.e("开始使能写特征 " + bluetoothGattCharacteristic.getUuid().toString());
                    } else {
                        if (bluetoothGattCharacteristic.getUuid().toString().equals(CMD.UUID_C_3)) {
                            callBackState(8);
                            setNotificationForCharacteristic(bluetoothGatt, bluetoothGattCharacteristic, BluetoothGattDescriptor.ENABLE_INDICATION_VALUE);
                            this.mHandler.removeMessages(4118);
                            Handler handler = this.mHandler;
                            str = str2;
                            handler.sendMessageDelayed(handler.obtainMessage(4118, bluetoothGatt.getDevice()), ToolTipPopup.DEFAULT_POPUP_DISPLAY_TIME);
                            YCBTLog.e("开始使能写特征3 " + bluetoothGattCharacteristic.getUuid().toString());
                        } else {
                            str = str2;
                            if (bluetoothGattCharacteristic.getUuid().toString().equals(CMD.UUID_C_2)) {
                                this.mWriteChar2 = bluetoothGattCharacteristic;
                                YCBTLog.e("开始使能写特征2 " + bluetoothGattCharacteristic.getUuid().toString());
                            }
                        }
                        str2 = str;
                        z = false;
                    }
                }
                String str3 = str2;
                if (this.mWriteChar != null || this.mWriteChar2 != null || this.mJLWriteChar == null || this.mJLNotifyChar == null) {
                    YCBTLog.e("mJLWriteChar=" + this.mJLWriteChar + " mJLNotifyChar=" + this.mJLNotifyChar + " mWriteChar=" + this.mWriteChar + str3 + this.mWriteChar2);
                } else {
                    callBackState(8);
                    this.isJLEnableNotifyChar = true;
                    setNotificationForCharacteristic(bluetoothGatt, this.mJLNotifyChar, BluetoothGattDescriptor.ENABLE_INDICATION_VALUE);
                }
            }
        }
        for (BluetoothGattService bluetoothGattService : services) {
            if (bluetoothGattService.getUuid().toString().equals(CMD.JL_UUID_SERVICE)) {
                for (BluetoothGattCharacteristic bluetoothGattCharacteristic2 : bluetoothGattService.getCharacteristics()) {
                    if (bluetoothGattCharacteristic2.getUuid().toString().equals(CMD.JL_UUID_WRITE)) {
                        this.mJLWriteChar = bluetoothGattCharacteristic2;
                        YCBTLog.e("杰理平台使能写 " + bluetoothGattCharacteristic2.getUuid().toString());
                    } else {
                        if (bluetoothGattCharacteristic2.getUuid().toString().equals(CMD.JL_UUID_NOTIFICATION)) {
                            this.isJLEnableNotifyChar = false;
                            this.mJLNotifyChar = bluetoothGattCharacteristic2;
                            YCBTLog.e("杰理平台使能通知 " + bluetoothGattCharacteristic2.getUuid().toString());
                        }
                        if (this.mWriteChar == null || this.mWriteChar2 != null || this.mJLWriteChar == null || this.mJLNotifyChar == null) {
                            YCBTLog.e("mJLWriteChar=" + this.mJLWriteChar + " mJLNotifyChar=" + this.mJLNotifyChar + " mWriteChar=" + this.mWriteChar + " mWriteChar2=" + this.mWriteChar2);
                        } else {
                            callBackState(8);
                            this.isJLEnableNotifyChar = true;
                            setNotificationForCharacteristic(bluetoothGatt, this.mJLNotifyChar, BluetoothGattDescriptor.ENABLE_INDICATION_VALUE);
                        }
                    }
                    if (this.mWriteChar == null) {
                    }
                    YCBTLog.e("mJLWriteChar=" + this.mJLWriteChar + " mJLNotifyChar=" + this.mJLNotifyChar + " mWriteChar=" + this.mWriteChar + " mWriteChar2=" + this.mWriteChar2);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void putConnectedGattInMap(String str, BluetoothGatt bluetoothGatt) {
        if (!BluetoothAdapter.checkBluetoothAddress(str) || bluetoothGatt == null) {
            return;
        }
        BleDevice bleDevice = new BleDevice(this.bleContext, bluetoothGatt);
        bleDevice.setConnectedTime(System.currentTimeMillis());
        this.mConnectedGattMap.put(str, bleDevice);
        if (this.mUsingDevice == null) {
            setConnectedBtDevice(bluetoothGatt.getDevice());
        }
        YCBTLog.d("putConnectedGattInMap >>>>>>>>>>>>> start");
        Iterator<String> it2 = this.mConnectedGattMap.keySet().iterator();
        while (it2.hasNext()) {
            YCBTLog.d("putConnectedGattInMap >>>>>>>>>>>>> " + it2.next());
        }
        YCBTLog.d("putConnectedGattInMap >>>>>>>>>>>>> end");
    }

    private void registerBroadcast() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.device.action.FOUND");
        intentFilter.addAction("android.bluetooth.adapter.action.DISCOVERY_STARTED");
        intentFilter.addAction("android.bluetooth.adapter.action.DISCOVERY_FINISHED");
        if (Build.VERSION.SDK_INT >= 33) {
            this.bleContext.registerReceiver(this.myReceive, intentFilter, 2);
        } else {
            this.bleContext.registerReceiver(this.myReceive, intentFilter);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BleDevice removeConnectedBle(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            return null;
        }
        return removeConnectedBle(bluetoothDevice.getAddress());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetCache() {
        if (this.mJLWriteChar != null) {
            WatchManager.getInstance().release();
        }
        this.mWriteChar = null;
        this.mWriteChar2 = null;
        this.mJLNotifyChar = null;
        this.mJLWriteChar = null;
        this.mUartWriteChar = null;
        this.mUartNotifyChar = null;
        YCBTClientImpl.getInstance().isForceOta = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized boolean sendDataToDevice(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        int iWriteCharacteristic;
        boolean zWriteCharacteristic = false;
        if (this.mUsingDevice == null) {
            return false;
        }
        BleDevice bleDevice = this.mConnectedGattMap.get(this.mUsingDevice.getAddress());
        if (bleDevice == null) {
            return false;
        }
        if (this.lists.size() <= 0) {
            return true;
        }
        int length = this.lists.get(0).length;
        byte[] bArr = new byte[length];
        System.arraycopy(this.lists.get(0), 0, bArr, 0, length);
        this.lists.remove(0);
        bluetoothGattCharacteristic.setValue(bArr);
        if (Build.VERSION.SDK_INT >= 33) {
            iWriteCharacteristic = bleDevice.getGatt().writeCharacteristic(bluetoothGattCharacteristic, bArr, bluetoothGattCharacteristic.getWriteType());
            if (iWriteCharacteristic == 0) {
                zWriteCharacteristic = true;
            }
        } else {
            zWriteCharacteristic = bleDevice.getGatt().writeCharacteristic(bluetoothGattCharacteristic);
            iWriteCharacteristic = 0;
        }
        YCBTLog.e("发送数据 " + ByteUtil.byteToString(bArr) + " 写结果 " + zWriteCharacteristic + " requestStatus=" + iWriteCharacteristic + StringUtils.SPACE + Thread.currentThread().toString() + "--" + bluetoothGattCharacteristic.getUuid().toString());
        return zWriteCharacteristic;
    }

    private void setConnectedBtDevice(BluetoothDevice bluetoothDevice) {
        this.mUsingDevice = bluetoothDevice;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setConnectingBtDevice(BluetoothDevice bluetoothDevice) {
        this.mConnectingBtDevice = bluetoothDevice;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNotificationForCharacteristic(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr) {
        if (bluetoothGatt == null) {
            YCBTLog.e("BluetoothGatt is null");
            return;
        }
        boolean characteristicNotification = bluetoothGatt.setCharacteristicNotification(bluetoothGattCharacteristic, true);
        BluetoothGattDescriptor descriptor = bluetoothGattCharacteristic.getDescriptor(UUID.fromString("00002902-0000-1000-8000-00805f9b34fb"));
        if (descriptor != null) {
            YCBTLog.e("descriptor.setValue(type)=" + descriptor.setValue(bArr));
            YCBTLog.e("gatt.writeDescriptor(descriptor)=" + bluetoothGatt.writeDescriptor(descriptor));
        }
        YCBTLog.e("开始使能读特征==" + bluetoothGattCharacteristic.getUuid().toString() + "--success==" + characteristicNotification);
    }

    private void startConnectTimeout(BluetoothDevice bluetoothDevice) {
        if (this.mHandler.hasMessages(4113)) {
            return;
        }
        Handler handler = this.mHandler;
        handler.sendMessageDelayed(handler.obtainMessage(4113, bluetoothDevice), this.mConnectBleTimeout);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopChangeMtu() {
        this.mHandler.removeMessages(MSG_CHANGE_BLE_MTU_TIMEOUT);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopConnectTimeout() {
        if (this.mHandler.hasMessages(4113)) {
            this.mHandler.removeMessages(4113);
        }
    }

    private void syncSystemBleDevice() {
        List<BluetoothDevice> bleConnectedDevice = BleDeviceUtil.getBleConnectedDevice(this.bleContext);
        if (bleConnectedDevice == null || bleConnectedDevice.isEmpty()) {
            return;
        }
        for (BluetoothDevice bluetoothDevice : bleConnectedDevice) {
            Log.d("TAG", "syncSystemBleDevice: " + bluetoothDevice.getAddress());
            if (!BluetoothUtil.deviceEquals(bluetoothDevice, this.mUsingDevice) && !this.mDiscoveredBleDevices.contains(bluetoothDevice)) {
                this.mDiscoveredBleDevices.add(bluetoothDevice);
                handleDiscoveryBle(bluetoothDevice, new BleScanInfo().setEnableConnect(true));
            }
        }
    }

    public static void unpairDevice(BluetoothDevice bluetoothDevice) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        try {
            Method method = bluetoothDevice.getClass().getMethod("removeBond", null);
            method.setAccessible(true);
            method.invoke(bluetoothDevice, null);
        } catch (Exception e2) {
            Log.e("ble", e2.toString());
        }
    }

    public void callBackState(int i2) {
        GattBleResponse gattBleResponse = this.mBleResponse;
        if (gattBleResponse != null) {
            gattBleResponse.bleStateResponse(i2);
        }
    }

    public boolean connectBleDevice(BluetoothDevice bluetoothDevice) {
        return connectBleDevice(bluetoothDevice, this.mConnectBleTimeout);
    }

    public void destroy() {
        YCBTLog.d(">>>>>>>>>>>>>>destroy >>>>>>>>>>>>>>> ");
        stopConnectTimeout();
        clearConnectedBleDevices();
        if (isBleScanning()) {
            stopLeScan();
        }
        isBleScanning(false);
        this.mDiscoveredBleDevices.clear();
        this.mReConnectHelper.release();
        this.mCallbackManager.release();
        this.mHandler.removeCallbacksAndMessages(null);
        bleHelper = null;
    }

    public void disconnectA2dp() {
        if (((Integer) SPUtil.get(Constants.FunctionConstant.ISHASCREATEBOND, 0)).intValue() == 1 && this.mUsingDevice != null) {
            if (this.bluetoothA2dp != null) {
                try {
                    Method method = BluetoothA2dp.class.getMethod("disconnect", BluetoothDevice.class);
                    method.setAccessible(true);
                    YCBTLog.e("disconnectA2dp == " + ((Boolean) method.invoke(this.bluetoothA2dp, this.mUsingDevice)).booleanValue());
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            if (this.bluetoothHeadset != null) {
                try {
                    Method method2 = BluetoothHeadset.class.getMethod("disconnect", BluetoothDevice.class);
                    method2.setAccessible(true);
                    YCBTLog.e("connectHfp == " + ((Boolean) method2.invoke(this.bluetoothHeadset, this.mUsingDevice)).booleanValue());
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }
        }
        closeA2dp();
    }

    public void disconnectBleDevice(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            return;
        }
        BleDevice bleDeviceRemoveConnectedBle = removeConnectedBle(bluetoothDevice);
        YCBTLog.d("disconnectBleDevice : " + bluetoothDevice.getAddress() + ", " + bleDeviceRemoveConnectedBle);
        if (bleDeviceRemoveConnectedBle == null) {
            YCBTLog.d("disconnectBleDevice : It is not a connected device.");
        } else if (BluetoothUtil.isBluetoothEnable()) {
            bleDeviceRemoveConnectedBle.getGatt().disconnect();
        }
    }

    public void disconnectGatt() {
        YCBTLog.e("disconnectGatt()");
        if (this.mUsingDevice != null) {
            disconnectBleDevice(this.mUsingDevice);
        }
    }

    public void filterDevice(BluetoothDevice bluetoothDevice, ScanResult scanResult, int i2, byte[] bArr, boolean z) {
        if (this.isBleScanning && isBluetoothEnable() && !TextUtils.isEmpty(bluetoothDevice.getName()) && !this.mDiscoveredBleDevices.contains(bluetoothDevice)) {
            String strByteToString = ByteUtil.byteToString(bArr);
            List<String> list = this.productIds;
            int i3 = 0;
            if (!((list == null || list.size() <= 0) ? strByteToString.contains("1078") || strByteToString.contains("1178") || strByteToString.contains("1278") || strByteToString.contains("1378") || strByteToString.contains("C5FE") : isContainsProductIds(strByteToString))) {
                try {
                    if (bluetoothDevice.getName() == null || !bluetoothDevice.getName().toLowerCase(Locale.ROOT).contains(DfuBaseService.NOTIFICATION_CHANNEL_DFU)) {
                        return;
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return;
                }
            }
            if (YCBTClient.connectState() != 10) {
                YCBTLog.e("onLeScan " + bluetoothDevice.getAddress() + " name " + bluetoothDevice.getName() + StringUtils.SPACE + Thread.currentThread().getName());
            }
            this.mDiscoveredBleDevices.add(bluetoothDevice);
            handleDiscoveryBle(bluetoothDevice, new BleScanInfo().setRawData(bArr).setScanResult(scanResult).setRssi(i2).setEnableConnect(true));
            if (this.mBleResponse != null) {
                String name = bluetoothDevice.getName();
                if (TextUtils.isEmpty(name)) {
                    name = BleUtil.parseAdertisedData(bArr).getName();
                }
                List<AdvUnit> scanRecord = BleUtil.parseScanRecord(bArr);
                final ScanDeviceBean scanDeviceInfo = getScanDeviceInfo(scanResult, bluetoothDevice, name);
                if (scanDeviceInfo == null) {
                    scanDeviceInfo = new ScanDeviceBean();
                    scanDeviceInfo.setBattery(-1);
                }
                scanDeviceInfo.setDeviceMac(bluetoothDevice.getAddress());
                scanDeviceInfo.setDeviceName(name);
                scanDeviceInfo.setDeviceRssi(i2);
                scanDeviceInfo.device = bluetoothDevice;
                String advMac = BleUtil.parseAdvMac(bluetoothDevice.getAddress(), scanRecord);
                if (!advMac.isEmpty()) {
                    scanDeviceInfo.setAdvMac(advMac);
                }
                while (true) {
                    if (i3 >= this.scanDeviceBeanList.size()) {
                        break;
                    }
                    if (this.scanDeviceBeanList.get(i3).getDeviceMac().equals(scanDeviceInfo.getDeviceMac())) {
                        this.scanDeviceBeanList.remove(i3);
                        break;
                    }
                    i3++;
                }
                if (this.mBleResponse != null) {
                    this.mHandler.post(new Runnable() { // from class: com.yucheng.ycbtsdk.gatt.BleHelper.6
                        @Override // java.lang.Runnable
                        public void run() {
                            BleHelper.this.mBleResponse.bleScanResponse(0, scanDeviceInfo);
                        }
                    });
                }
                this.scanDeviceBeanList.add(scanDeviceInfo);
            }
        }
    }

    public void gatt2WriteData(byte[] bArr) {
        this.sendDataCount = 0;
        sendDataToDeviceByMtu(this.mWriteChar2, bArr);
    }

    public void gattWriteData(byte[] bArr) {
        this.sendDataCount = 0;
        sendDataToDeviceByMtu(this.mWriteChar, bArr);
    }

    public Context getBleContext() {
        return this.bleContext;
    }

    public BluetoothDevice getConnectedBLEDevice(String str) {
        if (!BluetoothAdapter.checkBluetoothAddress(str)) {
            return null;
        }
        List<BluetoothDevice> connectedDeviceList = getConnectedDeviceList();
        if (connectedDeviceList.isEmpty()) {
            return null;
        }
        for (BluetoothDevice bluetoothDevice : connectedDeviceList) {
            if (bluetoothDevice.getAddress().equals(str)) {
                return bluetoothDevice;
            }
        }
        return null;
    }

    public BluetoothDevice getConnectedBtDevice() {
        return this.mUsingDevice;
    }

    public BluetoothGatt getConnectedBtGatt(BluetoothDevice bluetoothDevice) {
        BleDevice connectedBle = getConnectedBle(bluetoothDevice);
        if (connectedBle == null) {
            return null;
        }
        return connectedBle.getGatt();
    }

    public List<BluetoothDevice> getConnectedDeviceList() {
        if (this.mConnectedGattMap.isEmpty()) {
            return new ArrayList();
        }
        List<BleDevice> sortList = getSortList();
        ArrayList arrayList = new ArrayList();
        for (BleDevice bleDevice : sortList) {
            if (bleDevice != null && bleDevice.getGatt().getDevice() != null) {
                arrayList.add(bleDevice.getGatt().getDevice());
            }
        }
        return arrayList;
    }

    public List<byte[]> getLists() {
        return this.lists;
    }

    public void initA2dp() {
        Context context;
        this.bluetoothA2dp = null;
        this.bluetoothHeadset = null;
        this.bluetoothHidDevice = null;
        BluetoothAdapter bluetoothAdapter = this.mBluetoothAdapter;
        if (bluetoothAdapter == null || (context = this.bleContext) == null) {
            return;
        }
        bluetoothAdapter.getProfileProxy(context, this.serviceListener, 2);
        this.mBluetoothAdapter.getProfileProxy(this.bleContext, this.serviceListener, 1);
        if (Build.VERSION.SDK_INT >= 28) {
            this.mBluetoothAdapter.getProfileProxy(this.bleContext, this.serviceListener, 19);
        }
    }

    public void initBond() {
        if (YCBTClient.connectState() == 10 && YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASCREATEBOND) && this.mUsingDevice != null) {
            YCBTLog.d("chong------------bondState==" + this.mUsingDevice.getBondState() + "  isDiscovering--" + this.isDiscovering);
            if (this.mUsingDevice.getBondState() == 10) {
                createBond();
            } else if (this.mUsingDevice.getBondState() == 12) {
                getHelper().connectA2dp();
            }
        }
    }

    public void initContext(Context context) {
        this.bleContext = context;
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        this.mBluetoothAdapter = defaultAdapter;
        this.mBluetoothLeScanner = defaultAdapter.getBluetoothLeScanner();
        this.mReConnectHelper = new ReConnectHelper(context, this);
    }

    public boolean isBleScanning() {
        return this.isBleScanning;
    }

    public boolean isBluetoothEnable() {
        BluetoothAdapter bluetoothAdapter = this.mBluetoothAdapter;
        return bluetoothAdapter != null && bluetoothAdapter.isEnabled();
    }

    public boolean isBond() {
        if (YCBTClient.connectState() != 10 || !YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASCREATEBOND) || this.mUsingDevice == null) {
            return false;
        }
        YCBTLog.e("mBluetoothGatt.getDevice().getBondState() == " + this.mUsingDevice.getBondState());
        return this.mUsingDevice.getBondState() == 12;
    }

    public boolean isConnected() {
        return this.mUsingDevice != null;
    }

    public boolean isConnectedDevice(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            return false;
        }
        return isConnectedDevice(bluetoothDevice.getAddress());
    }

    public boolean isConnecting() {
        return this.mConnectingBtDevice != null;
    }

    public boolean isConnectingDevice(BluetoothDevice bluetoothDevice) {
        return BluetoothUtil.deviceEquals(this.mConnectingBtDevice, bluetoothDevice);
    }

    public boolean isMatchReConnectDevice(String str, String str2) {
        return this.mReConnectHelper.isMatchAddress(str, str2);
    }

    public void jlGattWriteData(final byte[] bArr) {
        final BleDevice bleDevice;
        if (this.mJLWriteChar == null || this.mUsingDevice == null || (bleDevice = this.mConnectedGattMap.get(this.mUsingDevice.getAddress())) == null) {
            return;
        }
        this.mExecutor.execute(new Runnable() { // from class: com.yucheng.ycbtsdk.gatt.BleHelper.4
            @Override // java.lang.Runnable
            public void run() throws InterruptedException {
                try {
                    Thread.sleep(5L);
                    BleHelper.this.mJLWriteChar.setValue(bArr);
                    YCBTLog.e("杰理发送数据 " + ByteUtil.byteToString(bArr) + " 写结果 " + bleDevice.getGatt().writeCharacteristic(BleHelper.this.mJLWriteChar) + StringUtils.SPACE + Thread.currentThread().toString());
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
    }

    public void reconnectDevice(String str, boolean z) {
        YCBTLog.d("reconnectDevice : address = " + str + ", isUseAdv = " + z);
        YCBTLog.d("reconnectDevice : ret = " + this.mReConnectHelper.putParam(new ReConnectHelper.ReconnectParam(str, z)));
    }

    public void registerBleEventCallback(BleEventCallback bleEventCallback) {
        this.mCallbackManager.registerBleEventCallback(bleEventCallback);
    }

    public void registerGattResponse(GattBleResponse gattBleResponse) {
        this.mBleResponse = gattBleResponse;
    }

    public void sendDataToDeviceByMtu(final BluetoothGattCharacteristic bluetoothGattCharacteristic, final byte[] bArr) {
        if (bArr == null || bluetoothGattCharacteristic == null || this.mUsingDevice == null) {
            return;
        }
        int i2 = MTU - 3;
        int length = bArr.length / i2;
        if (bArr.length % i2 != 0) {
            length++;
        }
        int i3 = 0;
        while (i3 < length) {
            int length2 = i3 == length + (-1) ? bArr.length - (i3 * i2) : i2;
            byte[] bArr2 = new byte[length2];
            System.arraycopy(bArr, i3 * i2, bArr2, 0, length2);
            this.lists.add(bArr2);
            i3++;
        }
        this.mExecutor.execute(new Runnable() { // from class: com.yucheng.ycbtsdk.gatt.BleHelper.3
            @Override // java.lang.Runnable
            public void run() throws InterruptedException {
                boolean zSendDataToDevice = BleHelper.this.sendDataToDevice(bluetoothGattCharacteristic);
                boolean z = !zSendDataToDevice;
                if (zSendDataToDevice || BleHelper.this.sendDataCount >= 3) {
                    if (zSendDataToDevice) {
                        return;
                    }
                    BleHelper.getHelper().disconnectGatt();
                    YCBTLog.e("bleOnCharacteristicWrite failed");
                    return;
                }
                YCBTLog.e("notSend=" + z + " sendDataCount=" + BleHelper.this.sendDataCount);
                BleHelper.access$3408(BleHelper.this);
                try {
                    Thread.sleep(500L);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
                BleHelper.this.sendDataToDeviceByMtu(bluetoothGattCharacteristic, bArr);
            }
        });
    }

    public void setMTU(int i2) {
        DEFAULT_MTU = i2;
    }

    public void setMaxGattReconnectTimes(int i2) {
        if (i2 < 0 || i2 >= 10) {
            return;
        }
        this.maxGattReconnectTimes = i2;
    }

    public void setProductId(int i2) {
        this.productId = i2;
    }

    public void setProductIds(List<String> list) {
        this.productIds = list;
    }

    public boolean startLeScan(long j2) {
        if (!PermissionUtil.getInstance().hasPermission(this.mBluetoothAdapter, this.bleContext)) {
            YCBTLog.e("缺少蓝牙权限");
            return false;
        }
        if (this.isBleScanning) {
            YCBTLog.e("正在搜索 isScaning " + this.isBleScanning);
            BluetoothLeScanner bluetoothLeScanner = this.mBluetoothLeScanner;
            if (bluetoothLeScanner != null) {
                bluetoothLeScanner.flushPendingScanResults(this.mScanCallback);
            }
            this.mDiscoveredBleDevices.clear();
            this.mHandler.removeMessages(4112);
            this.mHandler.sendEmptyMessageDelayed(4112, j2);
            syncSystemBleDevice();
            return true;
        }
        ScanSettings scanSettingsBuild = new ScanSettings.Builder().setScanMode(2).setMatchMode(1).build();
        ArrayList arrayList = new ArrayList();
        if (this.productId > 0) {
            ScanFilter.Builder builder = new ScanFilter.Builder();
            builder.setManufacturerData(this.productId, new byte[0]);
            arrayList.add(builder.build());
        }
        if (this.mBluetoothLeScanner == null) {
            this.mBluetoothLeScanner = this.mBluetoothAdapter.getBluetoothLeScanner();
        }
        this.mBluetoothLeScanner.startScan(arrayList, scanSettingsBuild, this.mScanCallback);
        YCBTLog.e("startLeScan : true, timeout = " + j2);
        isBleScanning(true);
        this.mDiscoveredBleDevices.clear();
        this.scanDeviceBeanList.clear();
        this.mHandler.removeMessages(4112);
        this.mHandler.sendEmptyMessageDelayed(4112, j2);
        syncSystemBleDevice();
        addConnectedDevices();
        return true;
    }

    public void stopLeScan() {
        if (this.mBluetoothAdapter != null && isBluetoothEnable() && isBleScanning()) {
            this.mBluetoothLeScanner.stopScan(this.mScanCallback);
            this.mHandler.removeMessages(4112);
            isBleScanning(false);
        }
    }

    public void uartWriteData(final byte[] bArr) {
        final BleDevice bleDevice;
        if (this.mUartWriteChar == null || this.mUsingDevice == null || (bleDevice = this.mConnectedGattMap.get(this.mUsingDevice.getAddress())) == null) {
            return;
        }
        this.mExecutor.execute(new Runnable() { // from class: com.yucheng.ycbtsdk.gatt.BleHelper.5
            @Override // java.lang.Runnable
            public void run() throws InterruptedException {
                try {
                    Thread.sleep(5L);
                    BleHelper.this.mUartWriteChar.setValue(bArr);
                    YCBTLog.e("UART发送数据 " + ByteUtil.byteToString(bArr) + " 写结果 " + bleDevice.getGatt().writeCharacteristic(BleHelper.this.mUartWriteChar) + StringUtils.SPACE + Thread.currentThread().toString());
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
    }

    public void unregisterBleEventCallback(BleEventCallback bleEventCallback) {
        this.mCallbackManager.unregisterBleEventCallback(bleEventCallback);
    }

    public void updateCacheInfo(BluetoothDevice bluetoothDevice) {
        String address = bluetoothDevice.getAddress();
        String name = bluetoothDevice.getName();
        if (address.equals(SPUtil.getBindedDeviceMac())) {
            return;
        }
        SPUtil.saveBindedDeviceMac(address);
        SPUtil.saveBindedDeviceName(name);
    }

    private void isBleScanning(boolean z) {
        this.isBleScanning = z;
        this.mCallbackManager.onDiscoveryBleChange(z);
    }

    private BleDevice removeConnectedBle(String str) {
        if (!BluetoothAdapter.checkBluetoothAddress(str)) {
            return null;
        }
        BleDevice bleDeviceRemove = this.mConnectedGattMap.remove(str);
        if (bleDeviceRemove != null) {
            if (this.mConnectedGattMap.isEmpty()) {
                setConnectedBtDevice(null);
            } else if (bleDeviceRemove.getGatt().getDevice() != null && BluetoothUtil.deviceEquals(bleDeviceRemove.getGatt().getDevice(), getConnectedBtDevice())) {
                setConnectedBtDevice(getSortList().get(0).getGatt().getDevice());
            }
        }
        return bleDeviceRemove;
    }

    public boolean connectBleDevice(BluetoothDevice bluetoothDevice, int i2) {
        BluetoothGatt bluetoothGattConnectGatt;
        YCBTLog.d("connectBleDevice " + bluetoothDevice.getAddress() + "   timeout " + i2);
        if (i2 <= 0) {
            this.mConnectBleTimeout = 10000;
        } else {
            this.mConnectBleTimeout = i2;
        }
        if (!AppUtil.checkHasConnectPermission(this.bleContext)) {
            return false;
        }
        if (this.mConnectingBtDevice != null) {
            YCBTLog.d("BleDevice is connecting, please wait.");
            return isConnectingDevice(bluetoothDevice);
        }
        if (this.mUsingDevice != null) {
            return false;
        }
        if (isBleScanning()) {
            stopLeScan();
        }
        MTU = DEFAULT_MTU;
        initA2dp();
        updateCacheInfo(bluetoothDevice);
        clearConnectingGatt();
        try {
            bluetoothGattConnectGatt = bluetoothDevice.connectGatt(this.bleContext, false, this.mBluetoothGattCallback, 2);
        } catch (Exception e2) {
            e2.printStackTrace();
            bluetoothGattConnectGatt = null;
        }
        boolean z = bluetoothGattConnectGatt != null;
        if (z) {
            setConnectingBtDevice(bluetoothDevice);
            callBackState(5);
            handleBleConnection(bluetoothDevice, 1);
            startConnectTimeout(bluetoothDevice);
            YCBTLog.d("connect start...." + bluetoothDevice.getAddress());
        }
        return z;
    }

    public boolean isConnectedDevice(String str) {
        if (!BluetoothAdapter.checkBluetoothAddress(str)) {
            return false;
        }
        List<BluetoothDevice> connectedDeviceList = getConnectedDeviceList();
        if (connectedDeviceList.isEmpty()) {
            return false;
        }
        Iterator<BluetoothDevice> it2 = connectedDeviceList.iterator();
        while (it2.hasNext()) {
            if (it2.next().getAddress().equals(str)) {
                return true;
            }
        }
        return false;
    }
}
