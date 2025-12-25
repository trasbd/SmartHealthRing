package com.yucheng.ycbtsdk.jl;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import com.jieli.jl_rcsp.impl.RcspAuth;
import com.jieli.jl_rcsp.impl.WatchOpImpl;
import com.jieli.jl_rcsp.interfaces.watch.OnWatchCallback;
import com.jieli.jl_rcsp.model.device.BatteryInfo;
import com.jieli.jl_rcsp.task.TaskListener;
import com.jieli.jl_rcsp.task.logcat.ReadLogcatTask;
import com.jieli.jl_rcsp.util.JL_Log;
import com.jieli.jl_rcsp.util.RcspUtil;
import com.yucheng.ycbtsdk.Constants;
import com.yucheng.ycbtsdk.YCBTClient;
import com.yucheng.ycbtsdk.core.YCBTClientImpl;
import com.yucheng.ycbtsdk.response.BleConnectResponse;
import com.yucheng.ycbtsdk.response.BleDataResponse;
import com.yucheng.ycbtsdk.response.BleDeviceToAppDataResponse;
import com.yucheng.ycbtsdk.utils.YCBTLog;
import java.util.HashMap;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes5.dex */
public class WatchManager extends WatchOpImpl {
    private static BleConnectResponseImpl bleConnectResponse;
    private static WatchManager watchManager;
    private BleDataResponse bleDataResponse;
    private Context context;
    private boolean isAuthPass;
    private boolean isInit;
    private boolean isRcspInit;
    private final OnWatchCallback mOnWatchCallback;
    private RcspAuth mRcspAuth;
    private BluetoothDevice mTargetDevice;
    public String tag;

    private class BleConnectResponseImpl implements BleConnectResponse {
        private BleConnectResponseImpl() {
        }

        @Override // com.yucheng.ycbtsdk.response.BleConnectResponse
        public void onConnectResponse(int i2) {
            YCBTLog.e("chong-------code==" + i2);
            if (i2 < 10) {
                WatchManager.this.setAuthPass(false);
                WatchManager.this.setInit(false);
            }
            if (i2 != 10) {
                int i3 = i2 != 1 ? (i2 == 2 || i2 == 3 || i2 == 4) ? 0 : 3 : 2;
                int iChangeConnectStatus = RcspUtil.changeConnectStatus(i3);
                YCBTLog.e(String.format(Locale.getDefault(), "原连接状态: %d ==> 转换后连接状态: %d", Integer.valueOf(i3), Integer.valueOf(iChangeConnectStatus)));
                if (YCBTClient.getGatt() != null) {
                    WatchManager.this.notifyBtDeviceConnection(YCBTClient.getGatt().getDevice(), iChangeConnectStatus);
                    if (YCBTClientImpl.getInstance().isOta && i2 == 3) {
                        YCBTLog.e("-jlOtaFirmware- disconnect >>>>>> WatchManager");
                        try {
                            JLOTAManager.getInstance(WatchManager.this.context).onChangedState(YCBTClient.getGatt().getDevice(), 0);
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                }
                ALiIOTKit.getInstance(WatchManager.this.context).destroy();
            }
        }
    }

    private class OnRcspAuthListenerImpl implements RcspAuth.OnRcspAuthListener {
        private OnRcspAuthListenerImpl() {
        }

        @Override // com.jieli.jl_rcsp.impl.RcspAuth.OnRcspAuthListener
        public void onAuthFailed(BluetoothDevice bluetoothDevice, int i2, String str) {
            YCBTLog.e("认证失败    code:" + i2 + "    message: " + str);
            WatchManager.this.setAuthPass(false);
            if (WatchManager.this.bleDataResponse != null) {
                WatchManager.this.bleDataResponse.onDataResponse(-2, 0.0f, null);
                WatchManager.this.bleDataResponse = null;
            }
        }

        @Override // com.jieli.jl_rcsp.impl.RcspAuth.OnRcspAuthListener
        public void onAuthSuccess(BluetoothDevice bluetoothDevice) {
            YCBTLog.e("认证完成");
            WatchManager.this.setAuthPass(true);
            WatchManager.this.notifyBtDeviceConnection(bluetoothDevice, 1);
            WatchManager watchManager = WatchManager.this;
            watchManager.registerOnWatchCallback(watchManager.mOnWatchCallback);
            if (WatchManager.this.bleDataResponse != null) {
                WatchManager.this.bleDataResponse.onDataResponse(0, 0.0f, null);
                WatchManager.this.bleDataResponse = null;
            }
        }

        @Override // com.jieli.jl_rcsp.impl.RcspAuth.OnRcspAuthListener
        public void onInitResult(boolean z) {
            YCBTLog.e("chong-----------onInitResult " + z);
        }
    }

    private WatchManager(int i2) {
        super(i2);
        this.tag = "WatchManager";
        this.mOnWatchCallback = new OnWatchCallback() { // from class: com.yucheng.ycbtsdk.jl.WatchManager.1
            @Override // com.jieli.jl_rcsp.interfaces.watch.OnWatchCallback
            public void onCurrentWatchInfo(BluetoothDevice bluetoothDevice, String str) {
                YCBTLog.e("chong-----onCurrentWatchInfo- device = , fatFilePath = " + str);
                BleDeviceToAppDataResponse bleDeviceToAppDataResponse = YCBTClientImpl.getInstance().getmBleDeviceToAppResponse();
                if (bleDeviceToAppDataResponse != null) {
                    HashMap map = new HashMap();
                    map.put("dataType", Integer.valueOf(Constants.DATATYPE.DeviceSwitchDial));
                    map.put("datas", str);
                    bleDeviceToAppDataResponse.onDataResponse(0, map);
                }
            }

            @Override // com.jieli.jl_rcsp.interfaces.watch.OnWatchCallback
            public void onDevicePower(BluetoothDevice bluetoothDevice, BatteryInfo batteryInfo) {
                YCBTLog.e("chong-----onDevicePower- batteryInfo = " + batteryInfo);
            }

            @Override // com.jieli.jl_rcsp.interfaces.rcsp.OnRcspCallback
            public void onMandatoryUpgrade(BluetoothDevice bluetoothDevice) {
                YCBTLog.e("onMandatoryUpgrade:  " + bluetoothDevice.getAddress());
            }

            @Override // com.jieli.jl_rcsp.interfaces.rcsp.OnRcspCallback
            public void onRcspInit(BluetoothDevice bluetoothDevice, boolean z) {
                YCBTLog.e("chong-----onRcspInit- isInit = " + z);
                WatchManager.this.setRespInit(true);
                if (!z || WatchManager.this.bleDataResponse == null) {
                    return;
                }
                WatchManager.this.bleDataResponse.onDataResponse(0, 0.0f, null);
                WatchManager.this.bleDataResponse = null;
            }

            @Override // com.jieli.jl_rcsp.interfaces.watch.OnWatchCallback
            public void onWatchSystemException(BluetoothDevice bluetoothDevice, int i3) {
                YCBTLog.e("chong-----onWatchSystemException- sysStatus = " + i3);
            }

            @Override // com.jieli.jl_rcsp.interfaces.watch.OnWatchCallback
            public void onWatchSystemInit(int i3) {
                YCBTLog.e("chong-----onWatchSystemInit- code = " + i3);
                if (i3 != 0) {
                    WatchManager.this.setInit(false);
                    if (WatchManager.this.bleDataResponse != null) {
                        WatchManager.this.bleDataResponse.onDataResponse(-1, 0.0f, null);
                        WatchManager.this.bleDataResponse = null;
                        return;
                    }
                    return;
                }
                WatchManager.this.setInit(true);
                if (WatchManager.this.bleDataResponse != null) {
                    WatchManager.this.bleDataResponse.onDataResponse(0, 0.0f, null);
                    WatchManager.this.bleDataResponse = null;
                }
            }
        };
        bleConnectResponse = new BleConnectResponseImpl();
    }

    public static WatchManager getInstance() {
        if (watchManager == null) {
            synchronized (WatchManager.class) {
                if (watchManager == null) {
                    watchManager = new WatchManager(1);
                }
            }
        }
        return watchManager;
    }

    @Override // com.jieli.jl_rcsp.interfaces.bluetooth.IBluetoothProxy
    public BluetoothDevice getConnectedDevice() {
        if (YCBTClient.getGatt() != null) {
            this.mTargetDevice = YCBTClient.getGatt().getDevice();
        }
        YCBTLog.d("getConnectedDevice " + this.mTargetDevice.getAddress());
        return this.mTargetDevice;
    }

    public void getLog(final BleDataResponse bleDataResponse) {
        try {
            final ReadLogcatTask readLogcatTask = new ReadLogcatTask(getInstance());
            readLogcatTask.setListener(new TaskListener() { // from class: com.yucheng.ycbtsdk.jl.WatchManager.2
                @Override // com.jieli.jl_rcsp.task.TaskListener
                public void onBegin() throws InterruptedException {
                    JL_Log.i(WatchManager.this.tag, "ReadLogcatTask:onBegin");
                }

                @Override // com.jieli.jl_rcsp.task.TaskListener
                public void onCancel(int i2) {
                }

                @Override // com.jieli.jl_rcsp.task.TaskListener
                public void onError(int i2, String str) throws InterruptedException {
                    JL_Log.w(WatchManager.this.tag, "ReadLogcatTask: onError : " + i2 + ", " + str);
                }

                @Override // com.jieli.jl_rcsp.task.TaskListener
                public void onFinish() throws InterruptedException {
                    JL_Log.i(WatchManager.this.tag, "ReadLogcatTask: onFinish : read logcat size = " + readLogcatTask.getResult().length);
                    HashMap map = new HashMap();
                    map.put("size", Integer.valueOf(readLogcatTask.getResult().length));
                    map.put("data", readLogcatTask.getResult());
                    bleDataResponse.onDataResponse(0, 0.0f, map);
                }

                @Override // com.jieli.jl_rcsp.task.TaskListener
                public void onProgress(int i2) throws InterruptedException {
                    JL_Log.i(WatchManager.this.tag, "ReadLogcatTask:onProgress=" + i2);
                }
            });
            readLogcatTask.start();
        } catch (Exception e2) {
            e2.printStackTrace();
            JL_Log.w(this.tag, e2.getMessage());
        }
    }

    public void initWatchManager(Context context) {
        this.context = context;
        YCBTClient.unRegisterBleStateChange(bleConnectResponse);
        YCBTClient.registerBleStateChange(bleConnectResponse);
        this.mRcspAuth = new RcspAuth(new RcspAuth.IRcspAuthOp() { // from class: com.yucheng.ycbtsdk.jl.WatchManager$$ExternalSyntheticLambda0
            @Override // com.jieli.jl_rcsp.impl.RcspAuth.IRcspAuthOp
            public final boolean sendAuthDataToDevice(BluetoothDevice bluetoothDevice, byte[] bArr) {
                return this.f$0.m2604lambda$initWatchManager$0$comyuchengycbtsdkjlWatchManager(bluetoothDevice, bArr);
            }
        }, new OnRcspAuthListenerImpl());
    }

    public boolean isAuthPass() {
        return this.isAuthPass;
    }

    public boolean isInit() {
        return this.isInit;
    }

    @Override // com.jieli.jl_rcsp.impl.RcspOpImpl
    public boolean isRcspInit() {
        return this.isRcspInit;
    }

    public void onReceiveData(BluetoothDevice bluetoothDevice, byte[] bArr) throws InterruptedException {
        if (this.isAuthPass) {
            notifyReceiveDeviceData(bluetoothDevice, bArr);
            return;
        }
        RcspAuth rcspAuth = this.mRcspAuth;
        if (rcspAuth != null) {
            rcspAuth.handleAuthData(bluetoothDevice, bArr);
        }
    }

    @Override // com.jieli.jl_rcsp.impl.WatchOpImpl, com.jieli.jl_rcsp.impl.RcspOpImpl, com.jieli.jl_rcsp.interfaces.rcsp.IRcspOp
    public void release() {
        super.release();
        YCBTClient.unRegisterBleStateChange(bleConnectResponse);
        RcspAuth rcspAuth = this.mRcspAuth;
        if (rcspAuth != null) {
            rcspAuth.destroy();
            this.mRcspAuth = null;
        }
        watchManager = null;
        unregisterOnWatchCallback(this.mOnWatchCallback);
        setRespInit(false);
    }

    @Override // com.jieli.jl_rcsp.interfaces.bluetooth.IBluetoothProxy
    /* renamed from: sendDataToDevice, reason: merged with bridge method [inline-methods] */
    public boolean m2604lambda$initWatchManager$0$comyuchengycbtsdkjlWatchManager(BluetoothDevice bluetoothDevice, byte[] bArr) {
        if (bArr == null) {
            return false;
        }
        int mtu = YCBTClient.getMtu() - 3;
        int length = bArr.length / mtu;
        if (bArr.length % mtu != 0) {
            length++;
        }
        int i2 = 0;
        while (i2 < length) {
            int length2 = i2 == length + (-1) ? bArr.length - (i2 * mtu) : mtu;
            byte[] bArr2 = new byte[length2];
            System.arraycopy(bArr, i2 * mtu, bArr2, 0, length2);
            YCBTClient.sendJLDataToDevice(bArr2, false);
            i2++;
        }
        return true;
    }

    public void setAuthPass(boolean z) {
        this.isAuthPass = z;
    }

    public void setInit(boolean z) {
        this.isInit = z;
    }

    public void setReAuthPass(BleDataResponse bleDataResponse) {
        RcspAuth rcspAuth;
        YCBTLog.d("设备认证 //开启设备认证 " + YCBTClient.connectState() + " isAuthPass=" + this.isAuthPass + StringUtils.SPACE + this.mRcspAuth);
        if (YCBTClient.connectState() < 9 || this.isAuthPass || (rcspAuth = this.mRcspAuth) == null) {
            return;
        }
        this.bleDataResponse = bleDataResponse;
        rcspAuth.stopAuth(getConnectedDevice(), false);
        this.mRcspAuth.startAuth(getConnectedDevice());
        YCBTLog.d("开启设备认证 " + YCBTClient.connectState());
    }

    public void setRespInit(boolean z) {
        this.isRcspInit = z;
    }
}
