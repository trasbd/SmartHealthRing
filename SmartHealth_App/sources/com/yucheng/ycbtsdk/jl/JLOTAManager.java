package com.yucheng.ycbtsdk.jl;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.jieli.jl_bt_ota.impl.BluetoothOTAManager;
import com.jieli.jl_bt_ota.interfaces.BtEventCallback;
import com.jieli.jl_bt_ota.interfaces.IActionCallback;
import com.jieli.jl_bt_ota.interfaces.IUpgradeCallback;
import com.jieli.jl_bt_ota.model.BluetoothOTAConfigure;
import com.jieli.jl_bt_ota.model.base.BaseError;
import com.jieli.jl_bt_ota.model.response.TargetInfoResponse;
import com.jieli.jl_fatfs.utils.ZipUtil;
import com.yucheng.ycbtsdk.YCBTClient;
import com.yucheng.ycbtsdk.core.YCBTClientImpl;
import com.yucheng.ycbtsdk.gatt.BleHelper;
import com.yucheng.ycbtsdk.response.BleConnectResponse;
import com.yucheng.ycbtsdk.response.BleDataResponse;
import com.yucheng.ycbtsdk.upgrade.DfuCallBack;
import com.yucheng.ycbtsdk.utils.BleDeviceUtil;
import com.yucheng.ycbtsdk.utils.ByteUtil;
import com.yucheng.ycbtsdk.utils.SPUtil;
import com.yucheng.ycbtsdk.utils.YCBTLog;
import java.io.File;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes5.dex */
public class JLOTAManager extends BluetoothOTAManager {
    public static final String OTA_FILE_NAME = "update.ufw";
    public static final String OTA_FILE_SUFFIX = ".ufw";
    public static final String OTA_ZIP_SUFFIX = ".zip";
    private static JLOTAManager otaManager;
    private DfuCallBack callBack;
    private String deviceMac;
    private String deviceName;
    private String firmwarePath;
    private final Handler handler;
    private boolean isInit;
    private int oldProgress;

    public JLOTAManager(Context context) {
        super(context);
        this.isInit = false;
        this.oldProgress = -1;
        this.handler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.yucheng.ycbtsdk.jl.JLOTAManager.1
            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message message) {
                if (message.what != 0 || JLOTAManager.this.isInit || JLOTAManager.this.callBack == null) {
                    return false;
                }
                JLOTAManager.this.callBack.failed("jlOta init failed");
                return false;
            }
        });
        configureOta();
        registerCallBack();
    }

    public static synchronized JLOTAManager getInstance(Context context) {
        if (otaManager == null) {
            otaManager = new JLOTAManager(context);
        }
        return otaManager;
    }

    public void configureOta() {
        BluetoothOTAConfigure bluetoothOTAConfigureCreateDefault = BluetoothOTAConfigure.createDefault();
        bluetoothOTAConfigureCreateDefault.setPriority(0).setUseAuthDevice(false).setBleIntervalMs(500).setTimeoutMs(3000).setMtu(BleHelper.MTU).setNeedChangeMtu(false).setUseReconnect(true);
        String strCreateFilePath = FileUtil.createFilePath(this.context, "upgrade");
        File file = new File(strCreateFilePath);
        boolean zExists = file.exists();
        if (!zExists) {
            zExists = file.mkdir();
        }
        if (zExists) {
            String strObtainUpdateFilePath = FileUtil.obtainUpdateFilePath(strCreateFilePath, OTA_FILE_SUFFIX);
            if (strObtainUpdateFilePath == null) {
                strObtainUpdateFilePath = strCreateFilePath + "/update.ufw";
            }
            bluetoothOTAConfigureCreateDefault.setFirmwareFilePath(strObtainUpdateFilePath);
        }
        configure(bluetoothOTAConfigureCreateDefault);
    }

    @Override // com.jieli.jl_bt_ota.interfaces.IBluetoothManager
    public void connectBluetoothDevice(BluetoothDevice bluetoothDevice) {
        YCBTLog.e("-jlOtaFirmware- connectGatt >>>>>> ");
        BleHelper.getHelper().connectBleDevice(bluetoothDevice);
    }

    public void dfuInit(String str, String str2, String str3, DfuCallBack dfuCallBack) {
        this.callBack = dfuCallBack;
        this.deviceMac = str;
        this.deviceName = str2;
        this.firmwarePath = str3;
        this.handler.sendEmptyMessageDelayed(0, 30000L);
        onChangedState(YCBTClient.getGatt().getDevice(), 1);
    }

    @Override // com.jieli.jl_bt_ota.interfaces.IBluetoothManager
    public void disconnectBluetoothDevice(BluetoothDevice bluetoothDevice) {
        YCBTLog.e("-jlOtaFirmware- disconnectGatt >>>>>> ");
        BleHelper.getHelper().disconnectGatt();
    }

    @Override // com.jieli.jl_bt_ota.impl.BluetoothOTAManager, com.jieli.jl_bt_ota.interfaces.IBluetoothManager
    public void errorEventCallback(BaseError baseError) {
        YCBTLog.e("chong--ota-error" + baseError.toString());
        DfuCallBack dfuCallBack = this.callBack;
        if (dfuCallBack != null) {
            dfuCallBack.error(baseError.toString());
        }
    }

    @Override // com.jieli.jl_bt_ota.interfaces.IBluetoothManager
    public BluetoothGatt getConnectedBluetoothGatt() {
        return YCBTClient.getGatt();
    }

    @Override // com.jieli.jl_bt_ota.interfaces.IBluetoothManager
    public BluetoothDevice getConnectedDevice() {
        if (YCBTClient.getGatt() == null) {
            return null;
        }
        return YCBTClient.getGatt().getDevice();
    }

    public String getFilePath(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (str.endsWith(OTA_FILE_SUFFIX) || str.endsWith(".buf")) {
            return str;
        }
        if (str.endsWith(OTA_ZIP_SUFFIX)) {
            try {
                String str2 = this.context.getExternalCacheDir().getAbsolutePath() + "/health";
                ZipUtil.unZipFolder(str, str2);
                String strObtainUpdateFilePath = FileUtil.obtainUpdateFilePath(str2, OTA_FILE_SUFFIX);
                File file = new File(str2 + "/res.ori");
                if (file.exists()) {
                    com.jieli.jl_bt_ota.util.FileUtil.deleteFile(file);
                }
                return strObtainUpdateFilePath;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        YCBTLog.e("chong--------Not found ota file. code == 20484");
        return null;
    }

    public void isForceOta(final BleDataResponse bleDataResponse) {
        YCBTLog.e("chong------isOTA==" + otaManager.isOTA());
        if (otaManager.isOTA()) {
            return;
        }
        otaManager.queryMandatoryUpdate(new IActionCallback<TargetInfoResponse>() { // from class: com.yucheng.ycbtsdk.jl.JLOTAManager.4
            @Override // com.jieli.jl_bt_ota.interfaces.IActionCallback
            public void onError(BaseError baseError) {
                BleDataResponse bleDataResponse2 = bleDataResponse;
                if (bleDataResponse2 != null) {
                    bleDataResponse2.onDataResponse(-1, 0.0f, null);
                }
            }

            @Override // com.jieli.jl_bt_ota.interfaces.IActionCallback
            public void onSuccess(TargetInfoResponse targetInfoResponse) {
                BleDataResponse bleDataResponse2 = bleDataResponse;
                if (bleDataResponse2 != null) {
                    bleDataResponse2.onDataResponse(0, 0.0f, null);
                }
            }
        });
    }

    public void onChangedState(BluetoothDevice bluetoothDevice, int i2) {
        YCBTLog.e("onChangedState  connectStatus=" + i2);
        onBtDeviceConnection(bluetoothDevice, i2);
    }

    public void onOtaMtuChanged(int i2, int i3) {
        onMtuChanged(getConnectedBluetoothGatt(), i2, i3);
    }

    public void onOtaReceiveDeviceData(BluetoothDevice bluetoothDevice, byte[] bArr) {
        YCBTLog.e("chong----接收杰理ota数据==" + ByteUtil.byteToString(bArr));
        onReceiveDeviceData(bluetoothDevice, bArr);
    }

    public void otaFirmware(String str, final DfuCallBack dfuCallBack) {
        this.oldProgress = -1;
        getBluetoothOption().setFirmwareFilePath(str);
        startOTA(new IUpgradeCallback() { // from class: com.yucheng.ycbtsdk.jl.JLOTAManager.3
            @Override // com.jieli.jl_bt_ota.interfaces.IUpgradeCallback
            public void onCancelOTA() {
                YCBTLog.e("-jlOtaFirmware- onCancel >>>>>> ");
                DfuCallBack dfuCallBack2 = dfuCallBack;
                if (dfuCallBack2 != null) {
                    dfuCallBack2.disconnect();
                }
            }

            @Override // com.jieli.jl_bt_ota.interfaces.IUpgradeCallback
            public void onError(BaseError baseError) {
                YCBTLog.e("-jlOtaFirmware- onError >>>>>> , baseError = " + baseError.toString());
                DfuCallBack dfuCallBack2 = dfuCallBack;
                if (dfuCallBack2 != null) {
                    dfuCallBack2.failed(baseError.getMessage());
                }
            }

            @Override // com.jieli.jl_bt_ota.interfaces.IUpgradeCallback
            public void onNeedReconnect(String str2, boolean z) {
                DfuCallBack dfuCallBack2 = dfuCallBack;
                if (dfuCallBack2 != null) {
                    dfuCallBack2.onNeedReconnect(BleDeviceUtil.getMacAddOne(str2), z);
                }
                YCBTLog.e("-jlOtaFirmware- onNeedReconnect >>>>>> " + str2 + StringUtils.SPACE + z);
                YCBTClient.reconnectDevice(BleDeviceUtil.getMacAddOne(SPUtil.getBindedDeviceMac()), new BleConnectResponse() { // from class: com.yucheng.ycbtsdk.jl.JLOTAManager.3.1
                    @Override // com.yucheng.ycbtsdk.response.BleConnectResponse
                    public void onConnectResponse(int i2) {
                        if (i2 == 0) {
                            JLOTAManager.this.onChangedState(YCBTClient.getGatt().getDevice(), 1);
                            return;
                        }
                        DfuCallBack dfuCallBack3 = dfuCallBack;
                        if (dfuCallBack3 != null) {
                            dfuCallBack3.failed("reconnect failed");
                        }
                    }
                });
                YCBTClientImpl.getInstance();
            }

            @Override // com.jieli.jl_bt_ota.interfaces.IUpgradeCallback
            public void onProgress(int i2, float f2) {
                int i3 = (int) (100.0f * f2);
                if (dfuCallBack != null && JLOTAManager.this.oldProgress != i3) {
                    dfuCallBack.progress(i3);
                }
                JLOTAManager.this.oldProgress = i3;
                YCBTLog.e("-jlOtaFirmware- onProgress >>>>>> type = " + i2 + ", progress = " + f2);
            }

            @Override // com.jieli.jl_bt_ota.interfaces.IUpgradeCallback
            public void onStartOTA() {
                YCBTLog.e("-jlOtaFirmware- onStart >>>>>> ");
                DfuCallBack dfuCallBack2 = dfuCallBack;
                if (dfuCallBack2 != null) {
                    dfuCallBack2.connected();
                }
            }

            @Override // com.jieli.jl_bt_ota.interfaces.IUpgradeCallback
            public void onStopOTA() {
                YCBTLog.e("-jlOtaFirmware- onStop >>>>>> ");
                DfuCallBack dfuCallBack2 = dfuCallBack;
                if (dfuCallBack2 != null) {
                    dfuCallBack2.success();
                }
            }
        });
    }

    public void registerCallBack() {
        registerBluetoothCallback(new BtEventCallback() { // from class: com.yucheng.ycbtsdk.jl.JLOTAManager.2
            @Override // com.jieli.jl_bt_ota.interfaces.BtEventCallback, com.jieli.jl_bt_ota.interfaces.IBluetoothCallback
            public void onConnection(BluetoothDevice bluetoothDevice, int i2) {
                YCBTLog.e("chong---------jl--ota初始化状态==" + i2);
                if (i2 == 1) {
                    JLOTAManager.this.isInit = true;
                    JLOTAManager.this.handler.removeMessages(0);
                    YCBTLog.e("chong---------jl--ota初始化成功");
                    if (JLOTAManager.this.isOTA()) {
                        return;
                    }
                    YCBTLog.e("chong--------in-firmwarePath==" + JLOTAManager.this.firmwarePath);
                    JLOTAManager jLOTAManager = JLOTAManager.this;
                    String filePath = jLOTAManager.getFilePath(jLOTAManager.firmwarePath);
                    YCBTLog.e("chong--------out-firmwarePath==" + filePath);
                    if (filePath != null) {
                        JLOTAManager jLOTAManager2 = JLOTAManager.this;
                        jLOTAManager2.otaFirmware(filePath, jLOTAManager2.callBack);
                    } else if (JLOTAManager.this.callBack != null) {
                        JLOTAManager.this.callBack.failed("File exception.");
                    }
                }
            }
        });
    }

    @Override // com.jieli.jl_bt_ota.impl.BluetoothOTAManager, com.jieli.jl_bt_ota.impl.BluetoothBreProfiles, com.jieli.jl_bt_ota.impl.BluetoothDiscovery, com.jieli.jl_bt_ota.impl.BluetoothBase, com.jieli.jl_bt_ota.interfaces.IUpgradeManager
    public void release() {
        super.release();
        otaManager = null;
        this.deviceMac = null;
        this.deviceName = null;
        this.firmwarePath = null;
        this.callBack = null;
    }

    @Override // com.jieli.jl_bt_ota.interfaces.IBluetoothManager
    public boolean sendDataToDevice(BluetoothDevice bluetoothDevice, byte[] bArr) {
        if (bArr == null) {
            return false;
        }
        int mtu = YCBTClient.getMtu() - 3;
        int length = bArr.length / mtu;
        if (bArr.length % mtu != 0) {
            length++;
        }
        YCBTLog.e("mtu=" + mtu + " size=" + length + " length=" + bArr.length + " chong----发送杰理ota数据==" + ByteUtil.byteToString(bArr));
        int i2 = 0;
        while (i2 < length) {
            int length2 = i2 == length + (-1) ? bArr.length - (i2 * mtu) : mtu;
            byte[] bArr2 = new byte[length2];
            System.arraycopy(bArr, i2 * mtu, bArr2, 0, length2);
            YCBTClient.sendJLDataToDevice(bArr2, true);
            i2++;
        }
        return true;
    }
}
