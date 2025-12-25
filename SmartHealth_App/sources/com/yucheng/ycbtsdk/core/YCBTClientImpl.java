package com.yucheng.ycbtsdk.core;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattService;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Handler;
import com.alibaba.fastjson2.JSONB;
import com.facebook.internal.ServerProtocol;
import com.yucheng.ycbtsdk.Constants;
import com.yucheng.ycbtsdk.YCBTClient;
import com.yucheng.ycbtsdk.bean.ScanDeviceBean;
import com.yucheng.ycbtsdk.gatt.BleHelper;
import com.yucheng.ycbtsdk.gatt.GattBleResponse;
import com.yucheng.ycbtsdk.gatt.Reconnect;
import com.yucheng.ycbtsdk.gatt.interfaces.BleEventCallback;
import com.yucheng.ycbtsdk.gatt.model.BleScanInfo;
import com.yucheng.ycbtsdk.jl.JLOTAManager;
import com.yucheng.ycbtsdk.jl.WatchManager;
import com.yucheng.ycbtsdk.receiver.MPhoneReceiver;
import com.yucheng.ycbtsdk.response.BleConnectResponse;
import com.yucheng.ycbtsdk.response.BleDataResponse;
import com.yucheng.ycbtsdk.response.BleDeviceToAppDataResponse;
import com.yucheng.ycbtsdk.response.BleRealDataResponse;
import com.yucheng.ycbtsdk.response.BleScanListResponse;
import com.yucheng.ycbtsdk.response.BleScanResponse;
import com.yucheng.ycbtsdk.utils.ByteUtil;
import com.yucheng.ycbtsdk.utils.InnerUtils;
import com.yucheng.ycbtsdk.utils.LogToFileUtils;
import com.yucheng.ycbtsdk.utils.SPUtil;
import com.yucheng.ycbtsdk.utils.TimeUtil;
import com.yucheng.ycbtsdk.utils.YCBTLog;
import com.zhihu.matisse.internal.loader.AlbumLoader;
import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kotlin.text.Charsets;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes5.dex */
public class YCBTClientImpl implements GattBleResponse {
    private static byte[] otaDownloadData;
    private static volatile YCBTClientImpl sInstance;
    private static byte[] watchDialDownloadData;
    private Context context;
    private byte[] datas;
    private boolean isRecvRealEcging;
    private BleConnectResponse mBleConnectResponse;
    private BleDeviceToAppDataResponse mBleDeviceToAppResponse;
    private BleRealDataResponse mBleRealDataResponse;
    private BleScanListResponse mBleScanListResponse;
    private BleScanResponse mBleScanResponse;
    private ArrayList<BleConnectResponse> mBleStatelistens;
    private ArrayList mBlockArray;
    private BleRealDataResponse mECGBleRealDataResponse;
    private int mEndTimeOutCount;
    private ArrayList mLastBlockArray;
    private boolean mQueueSendState;
    private CopyOnWriteArrayList<YCSendBean> mSendQueue;
    private Handler mTimeOutHander;
    private BleDataResponse sendingDataResponse;
    private int mBleStateCode = 3;
    private boolean isGattWriteCallBackFinish = true;
    private int mBlockFrame = 0;
    private HashMap scheduleInfo = new HashMap();
    private List<HashMap> scheduleInfos = new ArrayList();
    private boolean isWatchDialPause = false;
    private boolean isRealData = false;
    public boolean isOta = false;
    public boolean isForceOta = false;
    public long totalSendLength = 0;
    public int lastCrc = -1;
    public int lastPacketIndex = -1;
    public int crcFailCount = 0;
    public boolean isStartThree = false;
    public boolean isTool = false;
    private boolean reconnectStartFlag = false;
    private MPhoneReceiver phoneReceiver = new MPhoneReceiver();
    ExecutorService mSingleExecutor = Executors.newSingleThreadExecutor();
    private Runnable mTimerOutRunnable = new Runnable() { // from class: com.yucheng.ycbtsdk.core.YCBTClientImpl.1
        @Override // java.lang.Runnable
        public void run() {
            YCBTLog.e("TimeOut");
            YCBTClientImpl.this.stopScanBle();
        }
    };
    private Runnable mTimeRunnable = new Runnable() { // from class: com.yucheng.ycbtsdk.core.YCBTClientImpl.2
        @Override // java.lang.Runnable
        public void run() {
            if (YCBTClientImpl.this.mBleStateCode == 9) {
                YCBTClientImpl.access$104(YCBTClientImpl.this);
                YCBTLog.e("同步时间超时,重发 " + YCBTClientImpl.this.mEndTimeOutCount);
                if (YCBTClientImpl.this.mEndTimeOutCount <= 3) {
                    YCBTClientImpl.this.isGattWriteCallBackFinish = true;
                    if (YCBTClientImpl.this.mSendQueue != null && YCBTClientImpl.this.mSendQueue.size() > 0) {
                        ((YCSendBean) YCBTClientImpl.this.mSendQueue.get(0)).collectStopReset();
                    }
                    YCBTClientImpl.this.frontQueue();
                    return;
                }
                try {
                    if (YCBTClientImpl.this.sendingDataResponse != null) {
                        YCBTClientImpl.this.sendingDataResponse.onDataResponse(1, 0.0f, null);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                YCBTClientImpl.this.popQueue();
                YCBTClientImpl.this.mEndTimeOutCount = 0;
                return;
            }
            YCBTClientImpl.access$104(YCBTClientImpl.this);
            if (YCBTClientImpl.this.mSendQueue.size() > 0) {
                YCBTLog.e("重发 " + YCBTClientImpl.this.mEndTimeOutCount + StringUtils.SPACE + YCBTClientImpl.this.mSendQueue.get(0));
            }
            if (YCBTClientImpl.this.mSendQueue.size() > 0 && ((YCSendBean) YCBTClientImpl.this.mSendQueue.get(0)).groupType == 3) {
                if (YCBTClientImpl.this.sendingDataResponse != null) {
                    try {
                        YCBTClientImpl.this.sendingDataResponse.onDataResponse(-1, 0.0f, null);
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                }
                YCBTClientImpl.this.isGattWriteCallBackFinish = true;
                YCBTClientImpl.this.popQueue();
                YCBTClientImpl.this.mEndTimeOutCount = 0;
                return;
            }
            if (YCBTClientImpl.this.mEndTimeOutCount < 3) {
                YCBTClientImpl.this.isGattWriteCallBackFinish = true;
                if (YCBTClientImpl.this.mSendQueue != null && YCBTClientImpl.this.mSendQueue.size() > 0) {
                    ((YCSendBean) YCBTClientImpl.this.mSendQueue.get(0)).collectStopReset();
                }
                YCBTClientImpl.this.frontQueue();
                return;
            }
            if (YCBTClientImpl.this.sendingDataResponse != null) {
                try {
                    YCBTClientImpl.this.sendingDataResponse.onDataResponse(-1, 0.0f, null);
                } catch (Exception e4) {
                    e4.printStackTrace();
                }
            }
            YCBTClientImpl.this.isGattWriteCallBackFinish = true;
            YCBTClientImpl.this.popQueue();
            YCBTClientImpl.this.mEndTimeOutCount = 0;
        }
    };
    private int mEndEcgTimeOutCount = 0;
    private Runnable mEndEcgTestOut = new Runnable() { // from class: com.yucheng.ycbtsdk.core.YCBTClientImpl.3
        @Override // java.lang.Runnable
        public void run() {
            YCBTClientImpl.access$704(YCBTClientImpl.this);
            YCBTLog.e("实时ECG结束超时,重发 " + YCBTClientImpl.this.mEndEcgTimeOutCount);
            if (YCBTClientImpl.this.mEndEcgTimeOutCount > 3) {
                YCBTClientImpl.this.isRecvRealEcging = false;
                YCBTClientImpl.this.mTimeOutHander.removeCallbacks(YCBTClientImpl.this.mEndEcgTestOut);
                if (YCBTClientImpl.this.sendingDataResponse != null) {
                    YCBTClientImpl.this.sendingDataResponse.onDataResponse(1, 0.0f, null);
                }
                YCBTClientImpl.this.mEndEcgTimeOutCount = 0;
                YCBTClientImpl.this.popQueue();
                return;
            }
            if (YCBTClientImpl.this.mSendQueue.size() == 0) {
                return;
            }
            ((YCSendBean) YCBTClientImpl.this.mSendQueue.get(0)).resetGroup(Constants.DATATYPE.AppBloodSwitch, new byte[]{0});
            YCBTClientImpl.this.frontQueue();
            YCBTClientImpl.this.mTimeOutHander.removeCallbacks(YCBTClientImpl.this.mEndEcgTestOut);
            YCBTClientImpl.this.mTimeOutHander.postDelayed(YCBTClientImpl.this.mEndEcgTestOut, 2500L);
        }
    };
    private int bondingCount = 0;
    private Runnable bondingRunnable = new Runnable() { // from class: com.yucheng.ycbtsdk.core.YCBTClientImpl.6
        @Override // java.lang.Runnable
        public void run() {
            YCBTClientImpl.this.frontQueue();
            if (YCBTClientImpl.this.bondingCount < 12) {
                YCBTClientImpl.access$1208(YCBTClientImpl.this);
            } else {
                YCBTClientImpl.this.bondingCount = 0;
                YCBTClientImpl.this.isBonding = false;
            }
        }
    };
    public boolean isBonding = false;
    private int fileSize = 0;
    private int dialLength = 0;
    private int dialSize = 4096;
    private int currentDataIndex = 0;
    private int remainderPackage = 0;
    private int oldDataIndex = 0;
    private int otaIndex = 0;
    private int otaLength = 0;
    private int otaSize = 4096;
    private int currentOtaIndex = 0;
    private int remainderOtaPackage = 0;
    private int oldOtaIndex = 0;
    private int dialIndex = 0;
    private boolean isFlag = false;
    private BleEventCallback mBleEventCallback = new BleEventCallback() { // from class: com.yucheng.ycbtsdk.core.YCBTClientImpl.9
        @Override // com.yucheng.ycbtsdk.gatt.interfaces.BleEventCallback, com.yucheng.ycbtsdk.gatt.interfaces.IBleEventCallback
        public void onAdapterChange(boolean z) {
            super.onAdapterChange(z);
        }

        @Override // com.yucheng.ycbtsdk.gatt.interfaces.BleEventCallback, com.yucheng.ycbtsdk.gatt.interfaces.IBleEventCallback
        public void onBleConnection(BluetoothDevice bluetoothDevice, int i2) {
            super.onBleConnection(bluetoothDevice, i2);
        }

        @Override // com.yucheng.ycbtsdk.gatt.interfaces.BleEventCallback, com.yucheng.ycbtsdk.gatt.interfaces.IBleEventCallback
        public void onBleDataBlockChanged(BluetoothDevice bluetoothDevice, int i2, int i3) {
            super.onBleDataBlockChanged(bluetoothDevice, i2, i3);
        }

        @Override // com.yucheng.ycbtsdk.gatt.interfaces.BleEventCallback, com.yucheng.ycbtsdk.gatt.interfaces.IBleEventCallback
        public void onBleDataNotification(BluetoothDevice bluetoothDevice, UUID uuid, UUID uuid2, byte[] bArr) {
            super.onBleDataNotification(bluetoothDevice, uuid, uuid2, bArr);
        }

        @Override // com.yucheng.ycbtsdk.gatt.interfaces.BleEventCallback, com.yucheng.ycbtsdk.gatt.interfaces.IBleEventCallback
        public void onBleNotificationStatus(BluetoothDevice bluetoothDevice, UUID uuid, UUID uuid2, int i2) {
            super.onBleNotificationStatus(bluetoothDevice, uuid, uuid2, i2);
        }

        @Override // com.yucheng.ycbtsdk.gatt.interfaces.BleEventCallback, com.yucheng.ycbtsdk.gatt.interfaces.IBleEventCallback
        public void onBleServiceDiscovery(BluetoothDevice bluetoothDevice, int i2, List<BluetoothGattService> list) {
            super.onBleServiceDiscovery(bluetoothDevice, i2, list);
        }

        @Override // com.yucheng.ycbtsdk.gatt.interfaces.BleEventCallback, com.yucheng.ycbtsdk.gatt.interfaces.IBleEventCallback
        public void onBleWriteStatus(BluetoothDevice bluetoothDevice, UUID uuid, UUID uuid2, byte[] bArr, int i2) {
            super.onBleWriteStatus(bluetoothDevice, uuid, uuid2, bArr, i2);
        }

        @Override // com.yucheng.ycbtsdk.gatt.interfaces.BleEventCallback, com.yucheng.ycbtsdk.gatt.interfaces.IBleEventCallback
        public void onConnectionUpdated(BluetoothDevice bluetoothDevice, int i2, int i3, int i4, int i5) {
            super.onConnectionUpdated(bluetoothDevice, i2, i3, i4, i5);
        }

        @Override // com.yucheng.ycbtsdk.gatt.interfaces.BleEventCallback, com.yucheng.ycbtsdk.gatt.interfaces.IBleEventCallback
        public void onDiscoveryBle(BluetoothDevice bluetoothDevice, BleScanInfo bleScanInfo) {
            super.onDiscoveryBle(bluetoothDevice, bleScanInfo);
        }

        @Override // com.yucheng.ycbtsdk.gatt.interfaces.BleEventCallback, com.yucheng.ycbtsdk.gatt.interfaces.IBleEventCallback
        public void onDiscoveryBleChange(boolean z) {
            super.onDiscoveryBleChange(z);
        }
    };

    static /* synthetic */ int access$104(YCBTClientImpl yCBTClientImpl) {
        int i2 = yCBTClientImpl.mEndTimeOutCount + 1;
        yCBTClientImpl.mEndTimeOutCount = i2;
        return i2;
    }

    static /* synthetic */ int access$1208(YCBTClientImpl yCBTClientImpl) {
        int i2 = yCBTClientImpl.bondingCount;
        yCBTClientImpl.bondingCount = i2 + 1;
        return i2;
    }

    static /* synthetic */ int access$704(YCBTClientImpl yCBTClientImpl) {
        int i2 = yCBTClientImpl.mEndEcgTimeOutCount + 1;
        yCBTClientImpl.mEndEcgTimeOutCount = i2;
        return i2;
    }

    private void dataResponse(int i2, float f2, HashMap map) {
        try {
            YCBTLog.e("dataResponse code=" + i2 + StringUtils.SPACE + this.sendingDataResponse);
            BleDataResponse bleDataResponse = this.sendingDataResponse;
            if (bleDataResponse != null) {
                bleDataResponse.onDataResponse(i2, 0.0f, map);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void frontQueue() {
        int i2;
        int i3;
        synchronized (this) {
            YCBTLog.e("frontQueue " + this.isGattWriteCallBackFinish);
            if (this.mSendQueue.size() > 0) {
                if (isBonding()) {
                    this.mTimeOutHander.removeCallbacks(this.bondingRunnable);
                    this.mTimeOutHander.postDelayed(this.bondingRunnable, 1000L);
                    return;
                }
                this.bondingCount = 0;
                try {
                    YCSendBean yCSendBean = this.mSendQueue.get(0);
                    this.sendingDataResponse = yCSendBean.mDataResponse;
                    if (this.isGattWriteCallBackFinish) {
                        byte[] bArrWillSendFrame = yCSendBean.willSendFrame();
                        if (bArrWillSendFrame != null && ((i2 = this.mBleStateCode) == 10 || ((i2 == 9 && ((i3 = yCSendBean.dataType) == 256 || i3 == 513 || i3 == 539 || i3 == 512 || i3 == 43690)) || (this.isForceOta && yCSendBean.dataType == 43690)))) {
                            this.mQueueSendState = true;
                            int i4 = yCSendBean.dataType;
                            if (i4 == 43690 || i4 == 48059 || i4 == 52428) {
                                BleHelper.getHelper().jlGattWriteData(yCSendBean.willData);
                            } else {
                                int i5 = yCSendBean.groupType;
                                if (i5 == 1 || i5 == 8 || i5 == 9 || i5 == 12) {
                                    this.mTimeOutHander.removeCallbacks(this.mTimeRunnable);
                                    this.mTimeOutHander.postDelayed(this.mTimeRunnable, 3000L);
                                }
                                if (yCSendBean.groupType == 3) {
                                    this.mTimeOutHander.removeCallbacks(this.mTimeRunnable);
                                    this.mTimeOutHander.postDelayed(this.mTimeRunnable, 30000L);
                                }
                                sendData2Device(yCSendBean.dataType, bArrWillSendFrame);
                            }
                        } else if (bArrWillSendFrame != null) {
                            YCBTLog.e("tWillSendFrame != null");
                            popQueue();
                        } else {
                            YCBTLog.e("tWillSendFrame == null");
                        }
                    } else {
                        YCBTLog.e("frontQueue isGattWriteCallBackFinish == " + this.isGattWriteCallBackFinish);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                    YCBTLog.e(e2.getMessage());
                }
            }
        }
    }

    public static YCBTClientImpl getInstance() {
        if (sInstance == null) {
            synchronized (YCBTClientImpl.class) {
                if (sInstance == null) {
                    sInstance = new YCBTClientImpl();
                }
            }
        }
        return sInstance;
    }

    private byte[] getLastBlockArray(int i2) {
        int length = 0;
        for (int i3 = 0; i3 < this.mLastBlockArray.size(); i3++) {
            try {
                length += ((byte[]) this.mLastBlockArray.get(i3)).length;
            } catch (Exception e2) {
                e2.printStackTrace();
                return null;
            }
        }
        byte[] bArr = new byte[length];
        int length2 = 0;
        for (int i4 = 0; i4 < this.mLastBlockArray.size(); i4++) {
            byte[] bArr2 = (byte[]) this.mLastBlockArray.get(i4);
            System.arraycopy(bArr2, 0, bArr, length2, bArr2.length);
            length2 += bArr2.length;
        }
        return bArr;
    }

    private void initReceiver() {
        if (((Boolean) SPUtil.get(Constants.SharedKey.Call_Alerts_Str, Boolean.FALSE)).booleanValue()) {
            registerReceiver();
        }
    }

    private boolean isBonding() {
        return this.isBonding && YCBTClient.connectState() == 10 && ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASCREATEBOND, 0)).intValue() == 1 && BleHelper.getHelper().getConnectedBtDevice() != null && BleHelper.getHelper().getConnectedBtDevice().getBondState() != 12 && BleHelper.getHelper().getConnectedBtDevice().getBondState() == 11;
    }

    private boolean isError(byte[] bArr) {
        if (bArr != null && bArr.length == 1) {
            byte b2 = bArr[0];
            if ((b2 & JSONB.Constants.BC_INT32_NUM_MIN) == 240) {
                int i2 = b2 & 255;
                if (i2 == 251) {
                    YCBTLog.e("不支持的Command ID");
                    return true;
                }
                if (i2 == 252) {
                    YCBTLog.e("不支持的Key");
                    return true;
                }
                if (i2 == 253) {
                    YCBTLog.e("Length错误");
                    return true;
                }
                if (i2 == 254) {
                    YCBTLog.e("Data错误");
                    return true;
                }
                if (i2 == 255) {
                    YCBTLog.e("CRC16校验错误");
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isNeedStopCollect() {
        boolean z = true;
        int i2 = 1;
        while (true) {
            if (i2 >= this.mSendQueue.size()) {
                z = false;
                break;
            }
            if (this.mSendQueue.get(i2).sendPriority > 1) {
                break;
            }
            i2++;
        }
        YCBTLog.e("是否需要停止当前 " + z);
        return z;
    }

    private void next() {
        if (this.isWatchDialPause) {
            pauseDial();
            return;
        }
        int i2 = this.remainderPackage;
        if (i2 <= 0) {
            if (this.mSendQueue.get(0).dataType == 2304) {
                stopDial();
                return;
            }
            return;
        }
        int i3 = BleHelper.MTU - 9;
        int i4 = this.dialSize;
        if (i2 == 1) {
            i4 = this.dialLength - this.oldDataIndex;
        }
        int i5 = i4 / i3;
        int i6 = i4 % i3;
        int i7 = i5 + (i6 == 0 ? 0 : 1);
        int i8 = this.dialIndex;
        if (i8 >= i7) {
            this.dialIndex = 0;
            int i9 = this.currentDataIndex;
            int i10 = this.oldDataIndex;
            int i11 = i9 - i10;
            byte[] bArr = new byte[i11];
            System.arraycopy(watchDialDownloadData, i10, bArr, 0, i11);
            int iCrc16_compute = ByteUtil.crc16_compute(bArr, i11);
            int i12 = i7 >> 8;
            byte[] bArr2 = {(byte) i11, (byte) (i11 >> 8), (byte) (i11 >> 16), (byte) (i11 >> 24), (byte) i7, (byte) i12, (byte) iCrc16_compute, (byte) (iCrc16_compute >> 8)};
            YCBTLog.e("YCBTClientImpl next()----" + i11 + "--" + i7 + "--" + iCrc16_compute + "--" + i12);
            if (this.isWatchDialPause) {
                return;
            }
            if (this.mSendQueue.get(0).dataType == 2304) {
                sendData2Device(2306, bArr2);
            } else {
                sendData2Device(32259, bArr2);
            }
            this.oldDataIndex = this.currentDataIndex;
            this.remainderPackage--;
            return;
        }
        if (i8 == i7 - 1) {
            i3 = i6;
        }
        byte[] bArr3 = new byte[i3];
        System.arraycopy(watchDialDownloadData, this.currentDataIndex, bArr3, 0, i3);
        this.currentDataIndex += i3;
        if (this.isWatchDialPause) {
            return;
        }
        if (this.mSendQueue.get(0).dataType == 2304) {
            sendData2Device(2305, bArr3);
        } else {
            sendData2Device(32258, bArr3);
        }
        this.dialIndex++;
        if (this.sendingDataResponse != null) {
            HashMap map = new HashMap();
            map.put("code", 0);
            map.put("progress", Float.valueOf((this.currentDataIndex * 100.0f) / this.dialLength));
            map.put("dataType", Integer.valueOf(Constants.DATATYPE.WatchDialProgress));
            this.sendingDataResponse.onDataResponse(0, 0.0f, map);
        }
    }

    private void nextOta() {
        int i2 = this.remainderOtaPackage;
        if (i2 > 0) {
            int i3 = BleHelper.MTU - 9;
            int i4 = this.otaSize;
            if (i2 == 1) {
                i4 = this.otaLength - this.oldOtaIndex;
            }
            int i5 = i4 / i3;
            int i6 = i4 % i3;
            int i7 = i5 + (i6 == 0 ? 0 : 1);
            int i8 = this.otaIndex;
            if (i8 < i7) {
                if (i8 == i7 - 1) {
                    i3 = i6;
                }
                byte[] bArr = new byte[i3];
                System.arraycopy(otaDownloadData, this.currentOtaIndex, bArr, 0, i3);
                this.currentOtaIndex += i3;
                sendData2Device(Constants.DATATYPE.OtaSend, bArr);
                this.otaIndex++;
                if (this.sendingDataResponse != null) {
                    HashMap map = new HashMap();
                    map.put("code", 0);
                    map.put("progress", Float.valueOf((this.currentOtaIndex * 100.0f) / this.otaLength));
                    map.put("dataType", Integer.valueOf(Constants.DATATYPE.OTAProgress));
                    this.sendingDataResponse.onDataResponse(0, 0.0f, map);
                    return;
                }
                return;
            }
            this.otaIndex = 0;
            int i9 = this.currentOtaIndex;
            int i10 = this.oldOtaIndex;
            int i11 = i9 - i10;
            byte[] bArr2 = new byte[i11];
            System.arraycopy(otaDownloadData, i10, bArr2, 0, i11);
            int iCrc16_compute = ByteUtil.crc16_compute(bArr2, i11);
            int i12 = i7 >> 8;
            byte[] bArr3 = {(byte) i11, (byte) (i11 >> 8), (byte) (i11 >> 16), (byte) (i11 >> 24), (byte) i7, (byte) i12, (byte) iCrc16_compute, (byte) (iCrc16_compute >> 8)};
            YCBTLog.e("YCBTClientImpl next()----" + i11 + "--" + i7 + "--" + iCrc16_compute + "--" + i12);
            if (this.mSendQueue.get(0).dataType == 2560) {
                sendData2Device(Constants.DATATYPE.OtaBlock, bArr3);
            }
            this.oldOtaIndex = this.currentOtaIndex;
            this.remainderOtaPackage--;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:170:0x02b8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void packetAppControlHandle(int r17, int r18, byte[] r19, int r20) {
        /*
            Method dump skipped, instructions count: 909
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.ycbtsdk.core.YCBTClientImpl.packetAppControlHandle(int, int, byte[], int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:131:0x0413 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:134:0x041a  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x052a  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x052c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void packetCollectHandle(int r27, int r28, byte[] r29, int r30) {
        /*
            Method dump skipped, instructions count: 2108
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.ycbtsdk.core.YCBTClientImpl.packetCollectHandle(int, int, byte[], int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x00b8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void packetCollectionToolsHandle(int r6, int r7, byte[] r8, int r9) {
        /*
            Method dump skipped, instructions count: 197
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.ycbtsdk.core.YCBTClientImpl.packetCollectionToolsHandle(int, int, byte[], int):void");
    }

    private void packetCustomizeHandle(int i2, int i3, byte[] bArr, int i4) {
        try {
            if (i2 == 1) {
                try {
                    HashMap<String, Object> mapUnpackCustomizeCGM = DataUnpack.unpackCustomizeCGM(bArr);
                    dataResponse(((Integer) mapUnpackCustomizeCGM.get("code")).intValue(), 0.0f, mapUnpackCustomizeCGM);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                return;
            }
            try {
                if (i2 == 2) {
                    try {
                        int i5 = bArr[0] & 255;
                        if (i5 == 1) {
                            HashMap<String, Object> mapUnpackWit = DataUnpack.unpackWit(bArr);
                            dataResponse(((Integer) mapUnpackWit.get("code")).intValue(), 0.0f, mapUnpackWit);
                        }
                        if (i5 == 2) {
                            dataResponse(0, 0.0f, DataUnpack.unpackGetWit(bArr));
                        }
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                    return;
                }
                if (i2 != 117) {
                    return;
                }
                try {
                    HashMap<String, Object> mapUnpackCustomizeData = DataUnpack.unpackCustomizeData(bArr);
                    int iIntValue = ((Integer) mapUnpackCustomizeData.get("code")).intValue();
                    int iIntValue2 = ((Integer) mapUnpackCustomizeData.get("opcode")).intValue();
                    mapUnpackCustomizeData.remove("opcode");
                    if (iIntValue2 == 1) {
                        int iIntValue3 = ((Integer) mapUnpackCustomizeData.get(AlbumLoader.COLUMN_COUNT)).intValue();
                        int iIntValue4 = ((Integer) mapUnpackCustomizeData.get(ServerProtocol.DIALOG_PARAM_STATE)).intValue();
                        ((Integer) mapUnpackCustomizeData.get("packageNum")).intValue();
                        ((Integer) mapUnpackCustomizeData.get("total")).intValue();
                        mapUnpackCustomizeData.remove(AlbumLoader.COLUMN_COUNT);
                        mapUnpackCustomizeData.remove(ServerProtocol.DIALOG_PARAM_STATE);
                        mapUnpackCustomizeData.remove("packageNum");
                        mapUnpackCustomizeData.remove("total");
                        if (iIntValue4 != 1 && iIntValue4 != 2) {
                            if (iIntValue3 == 0) {
                                dataResponse(iIntValue, 0.0f, mapUnpackCustomizeData);
                                popQueue();
                                return;
                            }
                            return;
                        }
                        mapUnpackCustomizeData.put("code", 1);
                        dataResponse(iIntValue, 0.0f, mapUnpackCustomizeData);
                        popQueue();
                        return;
                    }
                    if (iIntValue2 == 3) {
                        dataResponse(iIntValue, 0.0f, mapUnpackCustomizeData);
                        popQueue();
                        return;
                    }
                    if (iIntValue2 == 2) {
                        BleRealDataResponse bleRealDataResponse = this.mBleRealDataResponse;
                        if (bleRealDataResponse != null) {
                            bleRealDataResponse.onRealDataResponse(3445, mapUnpackCustomizeData);
                            return;
                        }
                        return;
                    }
                    if (iIntValue2 != 128) {
                        dataResponse(iIntValue, 0.0f, mapUnpackCustomizeData);
                        return;
                    }
                    try {
                        if (iIntValue == 0) {
                            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(3);
                            int iIntValue5 = ((Integer) mapUnpackCustomizeData.get("type")).intValue();
                            byteBufferAllocate.put((byte) iIntValue2);
                            byteBufferAllocate.put((byte) iIntValue5);
                            byteBufferAllocate.put((byte) 0);
                            sendData2Device(3445, byteBufferAllocate.array());
                        } else {
                            sendData2Device(3445, new byte[]{4});
                        }
                        dataResponse(iIntValue, 0.0f, mapUnpackCustomizeData);
                    } catch (Exception e4) {
                        resetQueue();
                        e4.printStackTrace();
                    }
                } catch (Exception e5) {
                    resetQueue();
                    e5.printStackTrace();
                }
            } finally {
            }
        } finally {
        }
    }

    private void packetDevControlHandle(int i2, int i3, byte[] bArr, int i4) {
        int i5;
        switch (i2) {
            case 0:
                i5 = 1024;
                break;
            case 1:
                i5 = 1025;
                break;
            case 2:
                i5 = 1026;
                break;
            case 3:
                i5 = 1027;
                break;
            case 4:
                i5 = 1028;
                break;
            case 5:
                i5 = 1029;
                break;
            case 6:
                i5 = 1030;
                break;
            case 7:
                i5 = Constants.DATATYPE.DeviceConnectOrDisconnect;
                break;
            case 8:
                i5 = Constants.DATATYPE.DeviceSportMode;
                break;
            case 9:
                i5 = Constants.DATATYPE.DeviceSyncContacts;
                break;
            case 10:
                i5 = Constants.DATATYPE.DeviceRest;
                break;
            case 11:
                this.isRecvRealEcging = false;
                i5 = Constants.DATATYPE.DeviceEndECG;
                break;
            case 12:
                i5 = Constants.DATATYPE.DeviceSportModeControl;
                break;
            case 13:
                i5 = Constants.DATATYPE.DeviceSwitchDial;
                break;
            case 14:
                i5 = Constants.DATATYPE.DeviceMeasurementResult;
                break;
            case 15:
                i5 = Constants.DATATYPE.DeviceAlarmData;
                break;
            case 16:
                i5 = 1040;
                break;
            case 17:
                i5 = Constants.DATATYPE.DeviceUpgradeResult;
                break;
            case 18:
                i5 = Constants.DATATYPE.DevicePPIData;
                break;
            case 19:
                i5 = Constants.DATATYPE.DeviceMeasurStatusAndResults;
                break;
            case 20:
            default:
                i5 = -1;
                break;
            case 21:
                i5 = Constants.DATATYPE.DeviceRequestDynamicCode;
                break;
            case 22:
                i5 = Constants.DATATYPE.DeviceSedentaryReminder;
                break;
            case 23:
                i5 = Constants.DATATYPE.SosCall;
                break;
            case 24:
                i5 = Constants.DATATYPE.TerminalConf;
                break;
            case 25:
                i5 = 1049;
                break;
        }
        if (i5 != -1) {
            if (this.mBleDeviceToAppResponse != null) {
                try {
                    sendData2Device(i5, new byte[]{0});
                    this.mBleDeviceToAppResponse.onDataResponse(0, DataUnpack.unpackParseData(bArr, i5));
                } catch (Exception e2) {
                    e2.printStackTrace();
                    sendData2Device(i5, new byte[]{1});
                    this.mBleDeviceToAppResponse.onDataResponse(1, null);
                }
            }
            popQueue();
        }
    }

    private void packetDialHandle(int i2, int i3, byte[] bArr, int i4) {
        int i5;
        if (i2 == 0) {
            if (bArr.length < 2 || bArr[0] != 1 || bArr[1] != 0) {
                HashMap map = new HashMap();
                map.put("code", 0);
                map.put("data", bArr);
                map.put("dataType", 2304);
                BleDataResponse bleDataResponse = this.sendingDataResponse;
                if (bleDataResponse != null) {
                    if (bArr.length > 1) {
                        bleDataResponse.onDataResponse(bArr[1], 0.0f, map);
                    } else {
                        bleDataResponse.onDataResponse(1, 0.0f, map);
                    }
                }
                popQueue();
                return;
            }
            byte[] bArr2 = this.mSendQueue.get(0).willData;
            if (bArr2 == null || bArr2.length <= 9 || (i5 = (bArr2[9] & 255) + ((bArr2[10] & 255) << 8)) == 255) {
                i5 = 0;
            }
            this.dialIndex = 0;
            int i6 = this.dialSize;
            int i7 = i5 * i6;
            this.currentDataIndex = i7;
            this.oldDataIndex = i7;
            int length = watchDialDownloadData.length;
            this.dialLength = length;
            this.remainderPackage = ((length / i6) + (length % i6 == 0 ? 0 : 1)) - i5;
            this.isWatchDialPause = false;
            next();
            return;
        }
        if (i2 == 2) {
            if (bArr.length >= 1 && bArr[0] == 0) {
                next();
                return;
            }
            HashMap map2 = new HashMap();
            map2.put("code", 0);
            map2.put("data", bArr);
            map2.put("dataType", 2304);
            BleDataResponse bleDataResponse2 = this.sendingDataResponse;
            if (bleDataResponse2 != null) {
                bleDataResponse2.onDataResponse(1, 0.0f, map2);
                return;
            }
            return;
        }
        if (i2 == 3) {
            HashMap mapUnpackDialInfo = DataUnpack.unpackDialInfo(bArr);
            BleDataResponse bleDataResponse3 = this.sendingDataResponse;
            if (bleDataResponse3 != null) {
                bleDataResponse3.onDataResponse(mapUnpackDialInfo != null ? 0 : 1, 0.0f, mapUnpackDialInfo);
            }
            popQueue();
            return;
        }
        if (i2 == 4) {
            HashMap map3 = new HashMap();
            map3.put("code", 0);
            map3.put("data", bArr);
            map3.put("dataType", 2308);
            BleDataResponse bleDataResponse4 = this.sendingDataResponse;
            if (bleDataResponse4 != null) {
                bleDataResponse4.onDataResponse(bArr[0], 0.0f, map3);
            }
            popQueue();
            return;
        }
        if (i2 != 5) {
            return;
        }
        HashMap map4 = new HashMap();
        map4.put("code", 0);
        map4.put("data", bArr);
        map4.put("dataType", 2309);
        BleDataResponse bleDataResponse5 = this.sendingDataResponse;
        if (bleDataResponse5 != null) {
            bleDataResponse5.onDataResponse(bArr[0], 0.0f, map4);
        }
        popQueue();
    }

    private void packetFactoryHandle(int i2, int i3, byte[] bArr, int i4) {
        HashMap map = new HashMap();
        byte b2 = (bArr == null || bArr.length <= 2) ? (byte) 0 : bArr[2];
        if (i2 != 9) {
            return;
        }
        BleDataResponse bleDataResponse = this.sendingDataResponse;
        if (bleDataResponse != null) {
            try {
                bleDataResponse.onDataResponse(b2, 0.0f, map);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        popQueue();
    }

    private void packetOTAHandle(int i2, int i3, byte[] bArr, int i4) {
        int i5;
        if (i2 != 0) {
            if (i2 == 1) {
                HashMap map = new HashMap();
                map.put(ServerProtocol.DIALOG_PARAM_STATE, Integer.valueOf(bArr[0] & 255));
                BleDataResponse bleDataResponse = this.sendingDataResponse;
                if (bleDataResponse != null) {
                    bleDataResponse.onDataResponse(0, 0.0f, map);
                }
                return;
            }
            if (i2 != 2) {
                return;
            }
            if (bArr.length >= 1 && bArr[0] == 0) {
                nextOta();
                return;
            }
            HashMap map2 = new HashMap();
            int i6 = bArr[0] & 255;
            map2.put("code", 0);
            map2.put(ServerProtocol.DIALOG_PARAM_STATE, Integer.valueOf(i6));
            map2.put("dataType", 2560);
            BleDataResponse bleDataResponse2 = this.sendingDataResponse;
            if (bleDataResponse2 != null) {
                bleDataResponse2.onDataResponse(1, 0.0f, map2);
                return;
            }
            return;
        }
        if (bArr.length < 2 || bArr[0] != 1 || bArr[1] != 0) {
            try {
                HashMap mapUnpackOTAData = DataUnpack.unpackOTAData(bArr);
                dataResponse(((Integer) mapUnpackOTAData.get("code")).intValue(), 0.0f, mapUnpackOTAData);
                return;
            } catch (Exception e2) {
                e2.printStackTrace();
                return;
            } finally {
                popQueue();
            }
        }
        byte[] bArr2 = this.mSendQueue.get(0).willData;
        if (bArr2 == null || bArr2.length <= 9 || (i5 = (bArr2[9] & 255) + ((bArr2[10] & 255) << 8)) == 255) {
            i5 = 0;
        }
        this.otaIndex = 0;
        int i7 = this.otaSize;
        int i8 = i5 * i7;
        this.currentOtaIndex = i8;
        this.oldOtaIndex = i8;
        int length = otaDownloadData.length;
        this.otaLength = length;
        this.remainderOtaPackage = ((length / i7) + (length % i7 == 0 ? 0 : 1)) - i5;
        nextOta();
    }

    private void packetRealHandle(int i2, int i3, byte[] bArr, int i4) {
        if (this.isRealData) {
            this.isRealData = false;
            BleDataResponse bleDataResponse = this.sendingDataResponse;
            if (bleDataResponse != null) {
                try {
                    bleDataResponse.onDataResponse(0, 0.0f, null);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        switch (i2) {
            case 0:
                if (this.mBleRealDataResponse != null) {
                    try {
                        this.mBleRealDataResponse.onRealDataResponse(Constants.DATATYPE.Real_UploadSport, DataUnpack.unpackRealSportData(bArr));
                        break;
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                }
                break;
            case 1:
                if (this.mBleRealDataResponse != null) {
                    try {
                        this.mBleRealDataResponse.onRealDataResponse(1537, DataUnpack.unpackRealHeartData(bArr));
                        break;
                    } catch (Exception e4) {
                        e4.printStackTrace();
                        return;
                    }
                }
                break;
            case 2:
                if (this.mBleRealDataResponse != null) {
                    try {
                        this.mBleRealDataResponse.onRealDataResponse(1538, DataUnpack.unpackRealBloodOxygenData(bArr));
                        break;
                    } catch (Exception e5) {
                        e5.printStackTrace();
                        return;
                    }
                }
                break;
            case 3:
                if (this.mBleRealDataResponse != null) {
                    try {
                        this.mBleRealDataResponse.onRealDataResponse(1539, DataUnpack.unpackRealBloodData(bArr));
                    } catch (Exception e6) {
                        e6.printStackTrace();
                    }
                }
                if (this.mECGBleRealDataResponse != null) {
                    try {
                        this.mECGBleRealDataResponse.onRealDataResponse(1539, DataUnpack.unpackRealBloodData(bArr));
                        break;
                    } catch (Exception e7) {
                        e7.printStackTrace();
                        return;
                    }
                }
                break;
            case 4:
                if (this.mBleRealDataResponse != null) {
                    try {
                        this.mBleRealDataResponse.onRealDataResponse(Constants.DATATYPE.Real_UploadPPG, DataUnpack.unpackRealPPGData(bArr));
                    } catch (Exception e8) {
                        e8.printStackTrace();
                    }
                }
                if (this.mECGBleRealDataResponse != null) {
                    try {
                        this.mECGBleRealDataResponse.onRealDataResponse(Constants.DATATYPE.Real_UploadPPG, DataUnpack.unpackRealPPGData(bArr));
                        break;
                    } catch (Exception e9) {
                        e9.printStackTrace();
                        return;
                    }
                }
                break;
            case 5:
                HashMap mapUnpackRealECGData = DataUnpack.unpackRealECGData(bArr);
                BleRealDataResponse bleRealDataResponse = this.mBleRealDataResponse;
                if (bleRealDataResponse != null) {
                    try {
                        bleRealDataResponse.onRealDataResponse(Constants.DATATYPE.Real_UploadECG, mapUnpackRealECGData);
                    } catch (Exception e10) {
                        e10.printStackTrace();
                    }
                }
                BleRealDataResponse bleRealDataResponse2 = this.mECGBleRealDataResponse;
                if (bleRealDataResponse2 != null) {
                    try {
                        bleRealDataResponse2.onRealDataResponse(Constants.DATATYPE.Real_UploadECG, mapUnpackRealECGData);
                        break;
                    } catch (Exception e11) {
                        e11.printStackTrace();
                        return;
                    }
                }
                break;
            case 6:
                if (this.mBleRealDataResponse != null) {
                    try {
                        this.mBleRealDataResponse.onRealDataResponse(1537, DataUnpack.unpackRealUploadRunData(bArr));
                        break;
                    } catch (Exception e12) {
                        e12.printStackTrace();
                        return;
                    }
                }
                break;
            case 7:
                if (this.mBleRealDataResponse != null) {
                    try {
                        this.mBleRealDataResponse.onRealDataResponse(Constants.DATATYPE.Real_UploadRespiratoryRate, DataUnpack.unpackRealRespiratoryRateData(bArr));
                        break;
                    } catch (Exception e13) {
                        e13.printStackTrace();
                        return;
                    }
                }
                break;
            case 8:
                if (this.mBleRealDataResponse != null) {
                    try {
                        this.mBleRealDataResponse.onRealDataResponse(Constants.DATATYPE.Real_UploadSensor, DataUnpack.unpackRealSensorData(bArr));
                        break;
                    } catch (Exception e14) {
                        e14.printStackTrace();
                        return;
                    }
                }
                break;
            case 9:
                if (this.mBleRealDataResponse != null) {
                    try {
                        this.mBleRealDataResponse.onRealDataResponse(Constants.DATATYPE.Real_UploadAmbientlight, DataUnpack.unpackRealAmbientlightData(bArr));
                        break;
                    } catch (Exception e15) {
                        e15.printStackTrace();
                        return;
                    }
                }
                break;
            case 10:
                if (this.mBleRealDataResponse != null) {
                    try {
                        this.mBleRealDataResponse.onRealDataResponse(Constants.DATATYPE.Real_UploadComprehensive, DataUnpack.unpackRealComprehensiveData(bArr));
                        break;
                    } catch (Exception e16) {
                        e16.printStackTrace();
                        return;
                    }
                }
                break;
            case 11:
                if (bArr.length >= 9 && this.sendingDataResponse != null) {
                    this.scheduleInfos.add(DataUnpack.unpackGetScheduleInfo(bArr));
                    break;
                }
                break;
            case 12:
                if (bArr.length >= 6 && this.sendingDataResponse != null) {
                    this.scheduleInfos.add(DataUnpack.unpackGetEventReminder(bArr));
                    break;
                }
                break;
            case 13:
                if (this.mBleRealDataResponse != null) {
                    try {
                        this.mBleRealDataResponse.onRealDataResponse(Constants.DATATYPE.Real_UploadOGA, DataUnpack.unpackGetUploadOGA(bArr));
                        break;
                    } catch (Exception e17) {
                        e17.printStackTrace();
                        return;
                    }
                }
                break;
            case 14:
                if (this.mBleRealDataResponse != null) {
                    try {
                        this.mBleRealDataResponse.onRealDataResponse(Constants.DATATYPE.Real_UploadInflatedBlood, DataUnpack.unpackGetInflatedBlood(bArr));
                        break;
                    } catch (Exception e18) {
                        e18.printStackTrace();
                        return;
                    }
                }
                break;
            case 15:
                if (this.mBleRealDataResponse != null) {
                    try {
                        this.mBleRealDataResponse.onRealDataResponse(Constants.DATATYPE.Real_UploadMulPhotoelectricWaveform, DataUnpack.unpackMulPhotoelectricWaveform(bArr));
                        break;
                    } catch (Exception e19) {
                        e19.printStackTrace();
                        return;
                    }
                }
                break;
            case 16:
                if (this.mBleRealDataResponse != null) {
                    try {
                        this.mBleRealDataResponse.onRealDataResponse(Constants.DATATYPE.Real_UploadBodyData, DataUnpack.unpackBodyData(bArr));
                        break;
                    } catch (Exception e20) {
                        e20.printStackTrace();
                        return;
                    }
                }
                break;
            case 18:
                if (this.mBleRealDataResponse != null) {
                    try {
                        this.mBleRealDataResponse.onRealDataResponse(Constants.DATATYPE.Real_HengAiData, DataUnpack.unpackHengAiData(bArr));
                        break;
                    } catch (Exception e21) {
                        e21.printStackTrace();
                        return;
                    }
                }
                break;
            case 19:
                if (this.mBleRealDataResponse != null) {
                    try {
                        this.mBleRealDataResponse.onRealDataResponse(Constants.DATATYPE.Real_WearingStatus, DataUnpack.unpackWearingStatusData(bArr));
                        break;
                    } catch (Exception e22) {
                        e22.printStackTrace();
                        return;
                    }
                }
                break;
            case 20:
                if (this.mBleRealDataResponse != null) {
                    try {
                        this.mBleRealDataResponse.onRealDataResponse(Constants.DATATYPE.Real_UploadPrayer, DataUnpack.unpackGetUploadPrayer(bArr));
                        break;
                    } catch (Exception e23) {
                        e23.printStackTrace();
                        return;
                    }
                }
                break;
        }
    }

    private void packetSelfInspectionHandle(int i2, int i3, byte[] bArr, int i4) {
        if (i2 != 0) {
            return;
        }
        HashMap map = new HashMap();
        int i5 = bArr[0] & 255;
        byte b2 = bArr[1];
        int i6 = bArr[2] & 1;
        map.put("code", Integer.valueOf(i5));
        map.put("TP", Integer.valueOf((b2 >> 7) & 1));
        map.put("TEMP", Integer.valueOf((b2 >> 6) & 1));
        map.put("ECG", Integer.valueOf((b2 >> 5) & 1));
        map.put("PPG_IR", Integer.valueOf((b2 >> 4) & 1));
        map.put("PPG_R", Integer.valueOf((b2 >> 3) & 1));
        map.put("PPG_G", Integer.valueOf((b2 >> 2) & 1));
        map.put("PPG", Integer.valueOf((b2 >> 1) & 1));
        map.put("GS", Integer.valueOf(b2 & 1));
        map.put("LCD", Integer.valueOf(i6));
        BleDataResponse bleDataResponse = this.sendingDataResponse;
        if (bleDataResponse != null) {
            try {
                bleDataResponse.onDataResponse(0, 0.0f, map);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        popQueue();
    }

    private void packetSettingHandle(int i2, int i3, byte[] bArr, int i4) {
        HashMap map;
        byte b2 = 0;
        if (bArr != null) {
            byte b3 = bArr.length > 0 ? bArr[0] : (byte) 0;
            if (bArr.length > 1) {
                map = new HashMap();
                map.put("data", Byte.valueOf(bArr[1]));
                CopyOnWriteArrayList<YCSendBean> copyOnWriteArrayList = this.mSendQueue;
                if (copyOnWriteArrayList != null && copyOnWriteArrayList.size() > 0) {
                    map.put("dataType", Integer.valueOf(this.mSendQueue.get(0).dataType));
                }
            } else {
                map = null;
            }
            b2 = b3;
        } else {
            map = null;
        }
        if (i2 != 69 && i2 != 70 && i2 != 72 && i2 != 81 && i2 != 89) {
            switch (i2) {
                case 0:
                    BleDataResponse bleDataResponse = this.sendingDataResponse;
                    if (bleDataResponse != null) {
                        try {
                            bleDataResponse.onDataResponse(b2, 0.0f, map);
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    } else if (this.mBleStateCode == 9) {
                        sendSingleData2Device(513, new byte[]{JSONB.Constants.BC_INT32_SHORT_MAX, 70}, 2, null);
                    }
                    popQueue();
                    return;
                case 1:
                    BleDataResponse bleDataResponse2 = this.sendingDataResponse;
                    if (bleDataResponse2 != null) {
                        try {
                            bleDataResponse2.onDataResponse(b2, 0.0f, DataUnpack.unpackAlarmData(bArr));
                        } catch (Exception e3) {
                            e3.printStackTrace();
                        }
                    }
                    popQueue();
                    return;
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                    break;
                default:
                    switch (i2) {
                        case 8:
                        case 9:
                        case 10:
                        case 11:
                        case 12:
                        case 13:
                        case 14:
                        case 15:
                            break;
                        default:
                            switch (i2) {
                                case 18:
                                case 19:
                                case 20:
                                case 21:
                                case 22:
                                    break;
                                default:
                                    switch (i2) {
                                        case 25:
                                        case 26:
                                        case 27:
                                        case 28:
                                            break;
                                        default:
                                            switch (i2) {
                                                case 30:
                                                case 31:
                                                case 32:
                                                case 33:
                                                case 34:
                                                case 35:
                                                case 36:
                                                case 37:
                                                case 38:
                                                case 39:
                                                case 40:
                                                case 41:
                                                case 42:
                                                case 43:
                                                case 44:
                                                case 45:
                                                case 46:
                                                case 47:
                                                case 48:
                                                case 49:
                                                case 50:
                                                case 51:
                                                case 52:
                                                case 53:
                                                case 54:
                                                case 55:
                                                case 56:
                                                case 57:
                                                case 58:
                                                case 59:
                                                case 60:
                                                case 61:
                                                case 62:
                                                    break;
                                                default:
                                                    BleDataResponse bleDataResponse3 = this.sendingDataResponse;
                                                    if (bleDataResponse3 != null) {
                                                        try {
                                                            bleDataResponse3.onDataResponse(b2, 0.0f, map);
                                                        } catch (Exception e4) {
                                                            e4.printStackTrace();
                                                        }
                                                    }
                                                    popQueue();
                                                    break;
                                            }
                                    }
                            }
                    }
            }
        }
        BleDataResponse bleDataResponse4 = this.sendingDataResponse;
        if (bleDataResponse4 != null) {
            try {
                bleDataResponse4.onDataResponse(b2, 0.0f, map);
            } catch (Exception e5) {
                e5.printStackTrace();
            }
        }
        popQueue();
    }

    private void packetTestToolHandle(int i2, int i3, byte[] bArr, int i4) {
        if (i2 == 4) {
            if (this.mBleRealDataResponse != null) {
                try {
                    this.mBleRealDataResponse.onRealDataResponse(Constants.DATATYPE.Gsensor, DataUnpack.unpackRealGsensorData(bArr));
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            popQueue();
            return;
        }
        byte b2 = 0;
        if (i2 == 16) {
            HashMap mapUnpackFactoryTest = DataUnpack.unpackFactoryTest(bArr);
            BleDataResponse bleDataResponse = this.sendingDataResponse;
            if (bleDataResponse != null) {
                try {
                    bleDataResponse.onDataResponse(0, 0.0f, mapUnpackFactoryTest);
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }
            popQueue();
            return;
        }
        if (i2 == 17) {
            if (this.mBleRealDataResponse != null) {
                try {
                    this.mBleRealDataResponse.onRealDataResponse(Constants.DATATYPE.FactoryTestReport, DataUnpack.unpackFactoryReport(bArr));
                    return;
                } catch (Exception e4) {
                    e4.printStackTrace();
                    return;
                }
            }
            return;
        }
        HashMap map = null;
        if (bArr != null) {
            byte b3 = bArr.length > 0 ? bArr[0] : (byte) 0;
            if (bArr.length > 1) {
                map = new HashMap();
                map.put("data", Byte.valueOf(bArr[1]));
                CopyOnWriteArrayList<YCSendBean> copyOnWriteArrayList = this.mSendQueue;
                if (copyOnWriteArrayList != null && copyOnWriteArrayList.size() > 0) {
                    map.put("dataType", Integer.valueOf(this.mSendQueue.get(0).dataType));
                }
            }
            b2 = b3;
        }
        BleDataResponse bleDataResponse2 = this.sendingDataResponse;
        if (bleDataResponse2 != null) {
            try {
                bleDataResponse2.onDataResponse(b2, 0.0f, map);
            } catch (Exception e5) {
                e5.printStackTrace();
            }
        }
        popQueue();
    }

    private void pauseDial() {
        this.isWatchDialPause = false;
        stopDial();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void popQueue() {
        synchronized (this) {
            if (this.mSendQueue.size() > 0) {
                YCBTLog.e("popQueue Gatt写回调 " + this.isGattWriteCallBackFinish);
                if (this.isGattWriteCallBackFinish) {
                    removeTimeout();
                    this.mSendQueue.remove(0);
                    this.mQueueSendState = false;
                    Collections.sort(this.mSendQueue);
                    YCBTLog.e("排序后 " + this.mSendQueue);
                    YCBTLog.e("popQueue 队列剩余大小 " + this.mSendQueue.size() + StringUtils.SPACE + this.mSendQueue + " 实时测试 " + this.isRecvRealEcging + " mQueueSendState " + this.mQueueSendState);
                    if (!this.isRecvRealEcging) {
                        frontQueue();
                    }
                } else {
                    this.mSendQueue.get(0).dataSendFinish = true;
                }
            }
        }
    }

    public static void setOtaDownloadData(byte[] bArr) {
        otaDownloadData = bArr;
    }

    public static void setWatchDialDownloadData(byte[] bArr) {
        watchDialDownloadData = bArr;
    }

    private void stopDial() {
        int i2 = this.dialLength;
        sendData2Device(2304, new byte[]{0, (byte) i2, (byte) (i2 >> 8), (byte) (i2 >> 16), (byte) (i2 >> 24)});
    }

    @Override // com.yucheng.ycbtsdk.gatt.GattBleResponse
    public void bleDataResponse(int i2, byte[] bArr, String str) throws IllegalAccessException, InterruptedException, IllegalArgumentException, InvocationTargetException {
        int i3;
        int i4;
        int i5;
        int i6;
        if (CMD.JL_UUID_NOTIFICATION.equals(str)) {
            if (this.isOta && YCBTClient.getAuthPass()) {
                JLOTAManager.getInstance(this.context).onOtaReceiveDeviceData(getGatt().getDevice(), bArr);
                return;
            } else {
                WatchManager.getInstance().onReceiveData(getGatt().getDevice(), bArr);
            }
        }
        if (CMD.UART_TX_CHARACTERISTIC.equals(str) || bArr == null) {
            return;
        }
        boolean z = this.isFlag;
        if (z) {
            i3 = 0;
            i4 = 0;
            i5 = 0;
            i6 = 0;
        } else {
            if (bArr.length < 6) {
                return;
            }
            i3 = bArr[0] & 255;
            i4 = bArr[1] & 255;
            i5 = (bArr[2] & 255) + ((bArr[3] & 255) << 8);
            i6 = 4;
        }
        if (i5 != bArr.length) {
            if (!z && bArr.length != BleHelper.MTU - 3) {
                return;
            }
            this.isFlag = true;
            YCBTLog.e("BLE datas == " + ByteUtil.byteToString(bArr) + " 返回长度有问题：cmdlen = " + i5 + "byteLen == " + bArr.length);
            byte[] bArr2 = this.datas;
            if (bArr2 == null) {
                this.datas = bArr;
                return;
            }
            int length = bArr2.length + bArr.length;
            byte[] bArr3 = new byte[length];
            System.arraycopy(bArr2, 0, bArr3, 0, bArr2.length);
            System.arraycopy(bArr, 0, bArr3, this.datas.length, bArr.length);
            YCBTLog.e("BLE datas == " + ByteUtil.byteToString(bArr3));
            i3 = bArr3[0] & 255;
            int i7 = bArr3[1] & 255;
            int i8 = (bArr3[2] & 255) + ((bArr3[3] & 255) << 8);
            if (i8 == length) {
                this.isFlag = false;
                bArr = bArr3;
            } else {
                if (i8 > length) {
                    this.datas = null;
                    this.isFlag = false;
                    return;
                }
                this.datas = bArr3;
            }
            i4 = i7;
            i6 = 4;
            i5 = i8;
        }
        int i9 = ((bArr[i5 - 2] & 255) << 8) + (bArr[i5 - 1] & 255);
        int i10 = i5 - 6;
        byte[] bArr4 = new byte[i10];
        System.arraycopy(bArr, i6, bArr4, 0, i10);
        CopyOnWriteArrayList<YCSendBean> copyOnWriteArrayList = this.mSendQueue;
        if (copyOnWriteArrayList != null && copyOnWriteArrayList.size() > 0) {
            if (this.mSendQueue.get(0).dataType == (i3 << 8) + i4) {
                removeTimeout();
            }
        }
        if (3 != i3 && isError(bArr4)) {
            YCBTLog.e("isError--" + ((int) bArr4[0]));
            if (i3 == 4 || i3 == 6) {
                return;
            }
            if (this.mTimeOutHander != null && this.mTimeRunnable != null) {
                removeTimeout();
            }
            if (this.mBleStateCode == 9 && i3 == 2 && i4 == 27) {
                SPUtil.saveChipScheme(0);
                sendSingleData2Device(512, new byte[]{JSONB.Constants.BC_INT32_SHORT_MAX, 67}, 2, null);
            }
            dataResponse(bArr4[0], 0.0f, null);
            popQueue();
            return;
        }
        if (i3 == 19) {
            packetCollectionToolsHandle(i4, i10, bArr4, i9);
            return;
        }
        if (i3 == 126) {
            packetOtaUIHandle(i4, i10, bArr4, i9);
            return;
        }
        switch (i3) {
            case 1:
                packetSettingHandle(i4, i10, bArr4, i9);
                break;
            case 2:
                packetGetHandle(i4, i10, bArr4, i9);
                break;
            case 3:
                packetAppControlHandle(i4, i10, bArr4, i9);
                break;
            case 4:
                packetDevControlHandle(i4, i10, bArr4, i9);
                break;
            case 5:
                packetHealthHandle(i4, i10, bArr4, i9);
                break;
            case 6:
                packetRealHandle(i4, i10, bArr4, i9);
                break;
            case 7:
                packetCollectHandle(i4, i10, bArr4, i9);
                break;
            case 8:
                packetFactoryHandle(i4, i10, bArr4, i9);
                break;
            case 9:
                packetDialHandle(i4, i10, bArr4, i9);
                break;
            case 10:
                packetOTAHandle(i4, i10, bArr4, i9);
                break;
            default:
                switch (i3) {
                    case 12:
                        packetSelfInspectionHandle(i4, i10, bArr4, i9);
                        break;
                    case 13:
                        packetCustomizeHandle(i4, i10, bArr4, i9);
                        break;
                    case 14:
                        packetTestToolHandle(i4, i10, bArr4, i9);
                        break;
                    default:
                        packetGetHandle(i4, i10, bArr4, i9);
                        break;
                }
        }
    }

    @Override // com.yucheng.ycbtsdk.gatt.GattBleResponse
    public void bleOnCharacteristicWrite(int i2, byte[] bArr, String str) {
        byte b2;
        if (CMD.JL_UUID_WRITE.equals(str)) {
            YCBTLog.d("杰理发送数据 " + i2);
            this.isGattWriteCallBackFinish = true;
            popQueue();
            return;
        }
        if (bArr != null && bArr.length >= 2 && (((b2 = bArr[0]) == 9 && bArr[1] == 1) || (b2 == 126 && bArr[1] == 2))) {
            next();
            return;
        }
        if (bArr != null && bArr.length >= 2 && bArr[0] == 10 && bArr[1] == 1) {
            nextOta();
            return;
        }
        this.isGattWriteCallBackFinish = true;
        CopyOnWriteArrayList<YCSendBean> copyOnWriteArrayList = this.mSendQueue;
        if (copyOnWriteArrayList == null || copyOnWriteArrayList.size() <= 0) {
            return;
        }
        YCSendBean yCSendBean = this.mSendQueue.get(0);
        if (i2 == 257) {
            yCSendBean.dataSendFinish = true;
            popQueue();
        } else if (yCSendBean.dataSendFinish) {
            popQueue();
        } else {
            frontQueue();
        }
    }

    @Override // com.yucheng.ycbtsdk.gatt.GattBleResponse
    public void bleScanListResponse(int i2, List<ScanDeviceBean> list) {
        BleScanListResponse bleScanListResponse = this.mBleScanListResponse;
        if (bleScanListResponse != null) {
            bleScanListResponse.onScanListResponse(i2, list);
        }
    }

    @Override // com.yucheng.ycbtsdk.gatt.GattBleResponse
    public void bleScanResponse(int i2, ScanDeviceBean scanDeviceBean) {
        BleScanResponse bleScanResponse = this.mBleScanResponse;
        if (bleScanResponse != null) {
            bleScanResponse.onScanResponse(i2, scanDeviceBean);
        }
        if (i2 != 0) {
            stopScanBle();
        }
    }

    @Override // com.yucheng.ycbtsdk.gatt.GattBleResponse
    public void bleStateResponse(int i2) {
        BleConnectResponse bleConnectResponse;
        BleConnectResponse bleConnectResponse2;
        YCBTLog.e("connectState==" + i2);
        if (i2 == 6) {
            this.isRecvRealEcging = false;
            resetQueue();
        }
        this.mBleStateCode = i2;
        if (i2 == 9) {
            if (this.isForceOta) {
                WatchManager.getInstance().initWatchManager(this.context);
                YCBTLog.e("onDescriptorWrite  开始认证 ");
                WatchManager.getInstance().setReAuthPass(new BleDataResponse() { // from class: com.yucheng.ycbtsdk.core.YCBTClientImpl.8
                    @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                    public void onDataResponse(int i3, float f2, HashMap map) {
                        if (i3 == 0) {
                            YCBTClientImpl.this.bleStateResponse(10);
                        }
                    }
                });
            } else if (this.isOta) {
                sendSingleData2Device(539, new byte[0], 2, null);
            } else {
                YCBTClient.getDeviceName(null);
                if (this.isTool) {
                    sendSingleData2Device(513, new byte[]{JSONB.Constants.BC_INT32_SHORT_MAX, 70}, 2, null);
                } else {
                    sendSingleData2Device(256, TimeUtil.makeBleTime(), 2, null);
                }
            }
        }
        try {
            if (this.mBleStateCode == 10) {
                Reconnect.getInstance().resetReconnectTime();
            }
            Iterator<BleConnectResponse> it2 = this.mBleStatelistens.iterator();
            while (it2.hasNext()) {
                it2.next().onConnectResponse(this.mBleStateCode);
            }
            if (this.mBleStateCode == 9 && (bleConnectResponse2 = this.mBleConnectResponse) != null) {
                bleConnectResponse2.onConnectResponse(0);
            }
            if (this.mBleStateCode > 3 || (bleConnectResponse = this.mBleConnectResponse) == null) {
                return;
            }
            bleConnectResponse.onConnectResponse(1);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public boolean connectBleDevice(BluetoothDevice bluetoothDevice, BleConnectResponse bleConnectResponse) {
        if (bleConnectResponse != null) {
            this.mBleConnectResponse = bleConnectResponse;
        }
        YCBTLog.e("connectBleDevie device:" + bluetoothDevice.getAddress());
        return BleHelper.getHelper().connectBleDevice(bluetoothDevice);
    }

    public int connectState() {
        return this.mBleStateCode;
    }

    public void disconnectBle() {
        SPUtil.saveBindedDeviceMac("");
        BleHelper.getHelper().disconnectGatt();
    }

    public BluetoothDevice getConnectedDevice() {
        return BleHelper.getHelper().getConnectedBtDevice();
    }

    public BluetoothGatt getGatt() {
        return BleHelper.getHelper().getConnectedBtGatt(BleHelper.getHelper().getConnectedBtDevice());
    }

    public int getQueueSize() {
        CopyOnWriteArrayList<YCSendBean> copyOnWriteArrayList = this.mSendQueue;
        if (copyOnWriteArrayList != null) {
            return copyOnWriteArrayList.size();
        }
        return 0;
    }

    public BleDeviceToAppDataResponse getmBleDeviceToAppResponse() {
        return this.mBleDeviceToAppResponse;
    }

    public void init(Context context, boolean z, int i2, boolean z2) {
        this.context = context;
        SPUtil.init(context);
        BleHelper.getHelper().initContext(context);
        BleHelper.getHelper().registerGattResponse(this);
        BleHelper.getHelper().setMaxGattReconnectTimes(i2);
        BleHelper.getHelper().registerBleEventCallback(this.mBleEventCallback);
        this.mSendQueue = new CopyOnWriteArrayList<>();
        this.mQueueSendState = false;
        this.mBlockArray = new ArrayList();
        this.mLastBlockArray = new ArrayList();
        this.mBleStatelistens = new ArrayList<>();
        this.mTimeOutHander = new Handler();
        Reconnect.getInstance().init(context, z);
        initReceiver();
        LogToFileUtils.init(context);
        YCBTClient.OpenLogSwitch = z2;
    }

    public void jniCallback(int i2, float f2) {
        HashMap map = new HashMap();
        map.put("code", 0);
        map.put("data", Float.valueOf(f2));
        if (i2 == 3) {
            try {
                map.put("dataType", Integer.valueOf(Constants.DATATYPE.Real_UploadECGRR));
                if (this.mBleRealDataResponse != null) {
                    YCBTLog.e("RR值 " + this.mBleRealDataResponse + StringUtils.SPACE + map);
                    this.mBleRealDataResponse.onRealDataResponse(Constants.DATATYPE.Real_UploadECGRR, map);
                }
                if (this.mECGBleRealDataResponse != null) {
                    YCBTLog.e("RR值 " + this.mECGBleRealDataResponse + StringUtils.SPACE + map);
                    this.mECGBleRealDataResponse.onRealDataResponse(Constants.DATATYPE.Real_UploadECGRR, map);
                    return;
                }
                return;
            } catch (Exception e2) {
                e2.printStackTrace();
                return;
            }
        }
        if (i2 != 4) {
            return;
        }
        try {
            map.put("dataType", Integer.valueOf(Constants.DATATYPE.Real_UploadECGHrv));
            BleRealDataResponse bleRealDataResponse = this.mBleRealDataResponse;
            if (bleRealDataResponse != null) {
                bleRealDataResponse.onRealDataResponse(Constants.DATATYPE.Real_UploadECGHrv, map);
            }
            BleRealDataResponse bleRealDataResponse2 = this.mECGBleRealDataResponse;
            if (bleRealDataResponse2 != null) {
                bleRealDataResponse2.onRealDataResponse(Constants.DATATYPE.Real_UploadECGHrv, map);
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x00c4 A[Catch: all -> 0x010b, TryCatch #1 {, blocks: (B:4:0x0003, B:6:0x0007, B:10:0x0013, B:12:0x0015, B:14:0x0049, B:16:0x004d, B:17:0x0053, B:19:0x0059, B:22:0x0065, B:25:0x0072, B:26:0x0076, B:27:0x007b, B:31:0x00c0, B:33:0x00c4, B:39:0x0109, B:34:0x00fe, B:36:0x0102, B:38:0x0106, B:30:0x00bd), top: B:46:0x0003, inners: #0, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00fe A[Catch: all -> 0x010b, TryCatch #1 {, blocks: (B:4:0x0003, B:6:0x0007, B:10:0x0013, B:12:0x0015, B:14:0x0049, B:16:0x004d, B:17:0x0053, B:19:0x0059, B:22:0x0065, B:25:0x0072, B:26:0x0076, B:27:0x007b, B:31:0x00c0, B:33:0x00c4, B:39:0x0109, B:34:0x00fe, B:36:0x0102, B:38:0x0106, B:30:0x00bd), top: B:46:0x0003, inners: #0, #2 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void pushQueue(com.yucheng.ycbtsdk.core.YCSendBean r6) {
        /*
            Method dump skipped, instructions count: 270
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.ycbtsdk.core.YCBTClientImpl.pushQueue(com.yucheng.ycbtsdk.core.YCSendBean):void");
    }

    public void reconnectDevice(final String str, final BleConnectResponse bleConnectResponse) {
        this.reconnectStartFlag = false;
        startScanBle(new BleScanResponse() { // from class: com.yucheng.ycbtsdk.core.YCBTClientImpl.4
            @Override // com.yucheng.ycbtsdk.response.BleScanResponse
            public void onScanResponse(int i2, ScanDeviceBean scanDeviceBean) {
                YCBTLog.d("onScanResponse >>>> code:" + i2);
                if (i2 != 0 || scanDeviceBean == null) {
                    if (YCBTClientImpl.this.reconnectStartFlag) {
                        return;
                    }
                    bleConnectResponse.onConnectResponse(i2);
                } else {
                    YCBTLog.d("onScanResponse >>>> " + scanDeviceBean.getDeviceMac());
                    if (str.equals(scanDeviceBean.getDeviceMac())) {
                        YCBTClientImpl.this.reconnectStartFlag = true;
                        YCBTClient.connectBleDevice(scanDeviceBean.device, bleConnectResponse);
                    }
                }
            }
        }, 5);
    }

    public void registerBleStateChangeCallBack(BleConnectResponse bleConnectResponse) {
        if (this.mBleStatelistens == null) {
            this.mBleStatelistens = new ArrayList<>();
        }
        this.mBleStatelistens.add(bleConnectResponse);
    }

    public void registerEcgRealDataCallBack(BleRealDataResponse bleRealDataResponse) {
        this.mECGBleRealDataResponse = bleRealDataResponse;
    }

    public void registerRealDataCallBack(BleRealDataResponse bleRealDataResponse) {
        this.mBleRealDataResponse = bleRealDataResponse;
    }

    public void registerRealTypeCallBack(BleDeviceToAppDataResponse bleDeviceToAppDataResponse) {
        this.mBleDeviceToAppResponse = bleDeviceToAppDataResponse;
    }

    public void registerReceiver() {
        if (this.context == null) {
            return;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PHONE_STATE");
        intentFilter.addAction("android.intent.action.NEW_OUTGOING_CALL");
        this.context.registerReceiver(this.phoneReceiver, intentFilter);
    }

    public void removeTimeout() {
        this.mTimeOutHander.removeCallbacks(this.mTimeRunnable);
        this.mEndTimeOutCount = 0;
    }

    public void resetQueue() {
        YCBTLog.e("resetQueue");
        this.mQueueSendState = false;
        this.isRecvRealEcging = false;
        this.isGattWriteCallBackFinish = true;
        if (this.mSendQueue == null) {
            this.mSendQueue = new CopyOnWriteArrayList<>();
        }
        this.mSendQueue.clear();
    }

    public void sendControlCommand(byte[] bArr) {
    }

    public void sendData2Device(final int i2, byte[] bArr) {
        int length = bArr.length;
        int i3 = length + 6;
        final byte[] bArr2 = new byte[i3];
        bArr2[0] = (byte) ((i2 >> 8) & 255);
        bArr2[1] = (byte) (i2 & 255);
        bArr2[2] = (byte) (i3 & 255);
        bArr2[3] = (byte) ((i3 >> 8) & 255);
        System.arraycopy(bArr, 0, bArr2, 4, length);
        int i4 = length + 4;
        int iCrc16_compute = ByteUtil.crc16_compute(bArr2, i4);
        bArr2[i4] = (byte) (iCrc16_compute & 255);
        bArr2[length + 5] = (byte) ((iCrc16_compute >> 8) & 255);
        this.isGattWriteCallBackFinish = false;
        this.mSingleExecutor.execute(new Runnable() { // from class: com.yucheng.ycbtsdk.core.YCBTClientImpl.5
            @Override // java.lang.Runnable
            public void run() throws InterruptedException {
                if (i2 == 2561) {
                    BleHelper.getHelper().gatt2WriteData(bArr2);
                    return;
                }
                try {
                    Thread.sleep(30L);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                if (i2 == 2305 || (SPUtil.getChipScheme() != 0 && i2 == 32258)) {
                    BleHelper.getHelper().gatt2WriteData(bArr2);
                } else {
                    BleHelper.getHelper().gattWriteData(bArr2);
                }
            }
        });
    }

    public void sendDataType2Device(int i2, int i3, byte[] bArr, int i4, BleDataResponse bleDataResponse) {
        YCSendBean yCSendBean = new YCSendBean(bArr, i4, bleDataResponse);
        yCSendBean.groupType = i3;
        yCSendBean.dataType = i2;
        pushQueue(yCSendBean);
    }

    public void sendSingleData2Device(int i2, byte[] bArr, int i3, BleDataResponse bleDataResponse) {
        YCSendBean yCSendBean = new YCSendBean(bArr, i3, bleDataResponse);
        yCSendBean.dataType = i2;
        yCSendBean.groupType = 1;
        if (i2 != 2304 || bArr.length <= 0 || bArr[0] != 0) {
            if (i2 == 32257) {
                Iterator<YCSendBean> it2 = this.mSendQueue.iterator();
                while (it2.hasNext()) {
                    if (it2.next().dataType == 32257) {
                        this.sendingDataResponse = bleDataResponse;
                        return;
                    }
                }
            }
            pushQueue(yCSendBean);
            return;
        }
        if (this.mSendQueue.size() > 0 && this.mSendQueue.get(0).dataType == 2304) {
            this.isWatchDialPause = true;
            return;
        }
        Iterator<YCSendBean> it3 = this.mSendQueue.iterator();
        while (it3.hasNext()) {
            YCSendBean next = it3.next();
            if (next.dataType == 2304) {
                this.mSendQueue.remove(next);
            }
        }
    }

    public void setBleConnectResponse(BleConnectResponse bleConnectResponse) {
        this.mBleConnectResponse = bleConnectResponse;
    }

    public void setForceOta(boolean z) {
        this.isForceOta = z;
    }

    public void setOta(boolean z) {
        this.isOta = z;
    }

    public void startScanBle(BleScanResponse bleScanResponse, int i2) {
        this.mBleScanResponse = bleScanResponse;
        YCBTLog.e("startScanBle timeoutSec=" + i2 + " scanResponse=" + bleScanResponse);
        this.mTimeOutHander.removeCallbacks(this.mTimerOutRunnable);
        long j2 = i2 * 1000;
        this.mTimeOutHander.postDelayed(this.mTimerOutRunnable, j2);
        BleHelper.getHelper().startLeScan(j2);
    }

    public void stopScanBle() {
        Handler handler = this.mTimeOutHander;
        if (handler != null) {
            handler.removeCallbacks(this.mTimerOutRunnable);
        }
        BleHelper.getHelper().stopLeScan();
        BleScanResponse bleScanResponse = this.mBleScanResponse;
        if (bleScanResponse != null) {
            bleScanResponse.onScanResponse(2, null);
            this.mBleScanResponse = null;
        }
        BleScanListResponse bleScanListResponse = this.mBleScanListResponse;
        if (bleScanListResponse != null) {
            bleScanListResponse.onScanListResponse(2, null);
            this.mBleScanListResponse = null;
        }
    }

    public void unRegisterRealDataCallBack(BleRealDataResponse bleRealDataResponse) {
        this.mBleRealDataResponse = bleRealDataResponse;
    }

    public void unregisterBleStateChangeCallBack(BleConnectResponse bleConnectResponse) {
        if (this.mBleStatelistens.contains(bleConnectResponse)) {
            this.mBleStatelistens.remove(bleConnectResponse);
        }
    }

    public void unregisterReceiver() {
        Context context = this.context;
        if (context == null) {
            return;
        }
        context.unregisterReceiver(this.phoneReceiver);
    }

    public boolean connectBleDevice(BluetoothDevice bluetoothDevice, int i2, BleConnectResponse bleConnectResponse) {
        if (bleConnectResponse != null) {
            this.mBleConnectResponse = bleConnectResponse;
        }
        YCBTLog.e("connectBleDevie device:" + bluetoothDevice.getAddress());
        return BleHelper.getHelper().connectBleDevice(bluetoothDevice, i2);
    }

    public void startScanBle(BleScanListResponse bleScanListResponse, int i2) {
        this.mBleScanListResponse = bleScanListResponse;
        YCBTLog.e("startScanBle timeoutSec=" + i2 + " scanResponse=" + bleScanListResponse);
        this.mTimeOutHander.removeCallbacks(this.mTimerOutRunnable);
        long j2 = i2;
        this.mTimeOutHander.postDelayed(this.mTimerOutRunnable, 1000 * j2);
        BleHelper.getHelper().startLeScan(j2);
    }

    private void packetHealthHandle(int i2, int i3, byte[] bArr, int i4) throws NumberFormatException {
        if (i2 != 8 && i2 != 9) {
            if (i2 != 23 && i2 != 24) {
                if (i2 != 102) {
                    if (i2 != 103) {
                        switch (i2) {
                            case 2:
                            case 4:
                            case 6:
                            case 26:
                            case 28:
                            case 30:
                            case 32:
                                break;
                            case 17:
                            case 19:
                            case 21:
                            case 34:
                            case 36:
                            case 38:
                                break;
                            case 128:
                                int i5 = (bArr[0] & 255) + ((bArr[1] & 255) << 8);
                                int i6 = (bArr[2] & 255) + ((bArr[3] & 255) << 8);
                                int i7 = (bArr[4] & 255) + ((bArr[5] & 255) << 8);
                                byte[] bArr2 = new byte[i6];
                                int iIntValue = ((Integer) this.mBlockArray.get(0)).intValue();
                                int length = 0;
                                for (int i8 = 1; i8 < this.mBlockArray.size(); i8++) {
                                    byte[] bArr3 = (byte[]) this.mBlockArray.get(i8);
                                    System.arraycopy(bArr3, 0, bArr2, length, bArr3.length);
                                    length += bArr3.length;
                                }
                                int iCrc16_compute = ByteUtil.crc16_compute(bArr2, length);
                                YCBTLog.e("历史包数 " + i5 + " 字节数据: " + i6 + " 校验码 " + i7 + " 计算出的Crc16 " + iCrc16_compute + " 接收到的长度 " + length);
                                if (iCrc16_compute == i7) {
                                    sendData2Device(1408, new byte[]{0});
                                    HashMap mapUnpackHealthData = DataUnpack.unpackHealthData(bArr2, iIntValue);
                                    BleDataResponse bleDataResponse = this.sendingDataResponse;
                                    if (bleDataResponse != null) {
                                        try {
                                            bleDataResponse.onDataResponse(0, 0.0f, mapUnpackHealthData);
                                        } catch (Exception e2) {
                                            e2.printStackTrace();
                                        }
                                    }
                                } else {
                                    sendData2Device(1408, new byte[]{4});
                                    BleDataResponse bleDataResponse2 = this.sendingDataResponse;
                                    if (bleDataResponse2 != null) {
                                        try {
                                            bleDataResponse2.onDataResponse(0, 0.0f, null);
                                        } catch (Exception e3) {
                                            e3.printStackTrace();
                                        }
                                    }
                                }
                                popQueue();
                                break;
                            default:
                                switch (i2) {
                                    case 40:
                                    case 42:
                                    case 44:
                                    case 46:
                                    case 48:
                                    case 50:
                                    case 52:
                                    case 54:
                                    case 56:
                                    case 58:
                                    case 60:
                                        break;
                                    case 41:
                                    case 43:
                                    case 45:
                                    case 47:
                                    case 49:
                                    case 51:
                                    case 53:
                                    case 55:
                                    case 57:
                                    case 59:
                                        break;
                                    default:
                                        switch (i2) {
                                            case 64:
                                            case 65:
                                            case 66:
                                            case 67:
                                            case 68:
                                            case 69:
                                            case 70:
                                            case 71:
                                            case 72:
                                            case 73:
                                            case 74:
                                            case 75:
                                            case 76:
                                            case 77:
                                            case 78:
                                            case 79:
                                            case 80:
                                            case 81:
                                            case 82:
                                                BleDataResponse bleDataResponse3 = this.sendingDataResponse;
                                                if (bleDataResponse3 != null) {
                                                    try {
                                                        bleDataResponse3.onDataResponse(0, 0.0f, null);
                                                    } catch (Exception e4) {
                                                        e4.printStackTrace();
                                                    }
                                                }
                                                popQueue();
                                                break;
                                        }
                                }
                        }
                    }
                }
            }
            this.mBlockArray.add(bArr);
            return;
        }
        if (i3 > 9) {
            YCBTLog.e("历史条数 " + ((bArr[0] & 255) + ((bArr[1] & 255) << 8)) + " 总包数: " + ((bArr[2] & 255) + ((bArr[3] & 255) << 8) + ((bArr[4] & 255) << 16) + ((bArr[5] & 255) << 24)) + " 总字节数据 " + ((bArr[6] & 255) + ((bArr[7] & 255) << 8) + ((bArr[8] & 255) << 16) + ((bArr[9] & 255) << 24)));
        } else {
            BleDataResponse bleDataResponse4 = this.sendingDataResponse;
            if (bleDataResponse4 != null) {
                try {
                    bleDataResponse4.onDataResponse(0, 0.0f, null);
                } catch (Exception e5) {
                    e5.printStackTrace();
                }
            }
            popQueue();
        }
        this.mBlockArray.clear();
        this.mBlockArray.add(Integer.valueOf(i2));
    }

    private void packetOtaUIHandle(int i2, int i3, byte[] bArr, int i4) {
        if (i2 == 0) {
            if (this.sendingDataResponse != null) {
                try {
                    this.sendingDataResponse.onDataResponse(0, 0.0f, DataUnpack.unpackUIFileBreakInfo(bArr));
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            popQueue();
            return;
        }
        if (i2 != 1) {
            if (i2 != 3) {
                return;
            }
            if (this.remainderPackage > 0 && bArr != null && bArr.length > 0 && bArr[0] == 0) {
                next();
                return;
            }
            if (this.sendingDataResponse != null) {
                try {
                    byte b2 = bArr[0];
                    HashMap map = new HashMap();
                    map.put("code", 0);
                    map.put("dataType", 32259);
                    map.put("data", Integer.valueOf(b2));
                    this.sendingDataResponse.onDataResponse(0, 0.0f, map);
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }
            popQueue();
            return;
        }
        if (bArr == null || bArr.length <= 0 || bArr[0] != 0) {
            try {
                if (this.sendingDataResponse != null) {
                    byte b3 = bArr[0];
                    HashMap map2 = new HashMap();
                    map2.put("code", 0);
                    map2.put("dataType", 32257);
                    map2.put("data", Integer.valueOf(b3));
                    this.sendingDataResponse.onDataResponse(0, 0.0f, map2);
                }
            } catch (Exception e4) {
                e4.printStackTrace();
            }
            popQueue();
            return;
        }
        try {
            YCSendBean yCSendBean = this.mSendQueue.get(0);
            if (SPUtil.getChipScheme() == 0) {
                this.dialSize = 1024;
            } else {
                this.dialSize = 4096;
            }
            this.dialIndex = 0;
            byte[] bArr2 = yCSendBean.willData;
            int i5 = (bArr2[12] & 255) + ((bArr2[13] & 255) << 8) + ((bArr2[14] & 255) << 16) + ((bArr2[15] & 255) << 24);
            this.currentDataIndex = i5;
            this.oldDataIndex = i5;
            int length = watchDialDownloadData.length;
            this.dialLength = length;
            int i6 = this.dialSize;
            this.remainderPackage = ((length / i6) + (length % i6 == 0 ? 0 : 1)) - (i5 / i6);
            this.isWatchDialPause = false;
            next();
        } catch (Exception e5) {
            e5.printStackTrace();
        }
    }

    private void packetGetHandle(int i2, int i3, byte[] bArr, int i4) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        String str;
        if (i2 == 0) {
            try {
                HashMap mapUnpackDeviceInfoData = DataUnpack.unpackDeviceInfoData(bArr);
                BleDataResponse bleDataResponse = this.sendingDataResponse;
                if (bleDataResponse != null) {
                    bleDataResponse.onDataResponse(0, 0.0f, mapUnpackDeviceInfoData);
                } else if (this.mBleStateCode == 9) {
                    if (InnerUtils.isJieLiChipScheme(YCBTClient.getChipScheme())) {
                        WatchManager.getInstance().setReAuthPass(new BleDataResponse() { // from class: com.yucheng.ycbtsdk.core.YCBTClientImpl.7
                            @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                            public void onDataResponse(int i5, float f2, HashMap map) {
                                if (i5 == 0) {
                                    YCBTClientImpl.this.bleStateResponse(10);
                                } else {
                                    BleHelper.getHelper().disconnectGatt();
                                }
                            }
                        });
                    } else {
                        bleStateResponse(10);
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            popQueue();
        }
        if (i2 == 1) {
            DataUnpack.removeAllFunction();
            if (bArr.length >= 14) {
                DataUnpack.saveDeviceSupportFunctionData(bArr);
            }
            BleDataResponse bleDataResponse2 = this.sendingDataResponse;
            if (bleDataResponse2 != null) {
                bleDataResponse2.onDataResponse(0, 0.0f, null);
            } else if (this.mBleStateCode == 9) {
                sendSingleData2Device(539, new byte[0], 2, null);
            }
            popQueue();
            return;
        }
        if (i2 == 3) {
            HashMap mapUnpackDeviceName = DataUnpack.unpackDeviceName(bArr);
            try {
                BleDataResponse bleDataResponse3 = this.sendingDataResponse;
                if (bleDataResponse3 != null) {
                    bleDataResponse3.onDataResponse(0, 0.0f, mapUnpackDeviceName);
                }
            } catch (Exception e3) {
                e3.printStackTrace();
            }
            popQueue();
            return;
        }
        if (i2 == 47) {
            BleDataResponse bleDataResponse4 = this.sendingDataResponse;
            if (bleDataResponse4 != null) {
                try {
                    bleDataResponse4.onDataResponse(0, 0.0f, DataUnpack.unpackRingProductionTestHost(bArr));
                } catch (Exception e4) {
                    e4.printStackTrace();
                }
            }
            popQueue();
            return;
        }
        switch (i2) {
            case 7:
                BleDataResponse bleDataResponse5 = this.sendingDataResponse;
                if (bleDataResponse5 != null) {
                    try {
                        bleDataResponse5.onDataResponse(0, 0.0f, DataUnpack.unpackDeviceUserConfigData(bArr));
                    } catch (Exception e5) {
                        e5.printStackTrace();
                    }
                }
                popQueue();
                break;
            case 8:
                if (i3 <= 1) {
                    YCBTLog.e("不支持设备日志功能");
                    if (this.sendingDataResponse != null) {
                        try {
                            HashMap map = new HashMap();
                            map.put("code", 0);
                            map.put("dataType", 520);
                            map.put("functionVersion", 97);
                            this.sendingDataResponse.onDataResponse(0, 0.0f, map);
                        } catch (Exception e6) {
                            e6.printStackTrace();
                        }
                    }
                    popQueue();
                    break;
                } else {
                    int i5 = bArr[0] & 255;
                    if (i5 == 1) {
                        YCBTLog.e("设备日志条数 " + ((bArr[1] & 255) + ((bArr[2] & 255) << 8)));
                        this.mBlockArray.clear();
                        break;
                    } else if (i5 == 0) {
                        YCBTLog.e("不支持设备日志功能");
                        if (this.sendingDataResponse != null) {
                            try {
                                HashMap map2 = new HashMap();
                                map2.put("code", 0);
                                map2.put("dataType", 520);
                                map2.put("functionVersion", 97);
                                this.sendingDataResponse.onDataResponse(0, 0.0f, map2);
                            } catch (Exception e7) {
                                e7.printStackTrace();
                            }
                        }
                        popQueue();
                        break;
                    } else if (i5 == 2 || i5 == 255) {
                        int i6 = i3 - 1;
                        byte[] bArr2 = new byte[i6];
                        System.arraycopy(bArr, 1, bArr2, 0, i6);
                        try {
                            str = new String(bArr2, Charsets.US_ASCII);
                        } catch (Exception unused) {
                            str = null;
                        }
                        YCBTLog.e("日志内容: " + str);
                        this.mBlockArray.add(str);
                        if (i5 == 255) {
                            HashMap map3 = new HashMap();
                            map3.put("code", 0);
                            map3.put("dataType", 520);
                            map3.put("data", this.mBlockArray);
                            map3.put("one_data", str);
                            map3.put("functionVersion", 97);
                            BleDataResponse bleDataResponse6 = this.sendingDataResponse;
                            if (bleDataResponse6 != null) {
                                try {
                                    bleDataResponse6.onDataResponse(0, 0.0f, map3);
                                } catch (Exception e8) {
                                    e8.printStackTrace();
                                }
                            }
                            popQueue();
                            break;
                        } else if (i5 == 2) {
                            HashMap map4 = new HashMap();
                            map4.put("code", 0);
                            map4.put("one_data", str);
                            BleDataResponse bleDataResponse7 = this.sendingDataResponse;
                            if (bleDataResponse7 != null) {
                                try {
                                    bleDataResponse7.onDataResponse(0, 0.0f, map4);
                                    break;
                                } catch (Exception e9) {
                                    e9.printStackTrace();
                                    return;
                                }
                            }
                        }
                    }
                }
                break;
            case 9:
                BleDataResponse bleDataResponse8 = this.sendingDataResponse;
                if (bleDataResponse8 != null) {
                    try {
                        bleDataResponse8.onDataResponse(0, 0.0f, DataUnpack.unpackHomeTheme(bArr));
                    } catch (Exception e10) {
                        e10.printStackTrace();
                    }
                }
                popQueue();
                break;
            case 10:
                BleDataResponse bleDataResponse9 = this.sendingDataResponse;
                if (bleDataResponse9 != null) {
                    try {
                        bleDataResponse9.onDataResponse(0, 0.0f, DataUnpack.unpackEcgLocation(bArr));
                    } catch (Exception e11) {
                        e11.printStackTrace();
                    }
                }
                popQueue();
                break;
            case 11:
                BleDataResponse bleDataResponse10 = this.sendingDataResponse;
                if (bleDataResponse10 != null) {
                    try {
                        bleDataResponse10.onDataResponse(0, 0.0f, DataUnpack.unpackDeviceScreenInfo(bArr));
                    } catch (Exception e12) {
                        e12.printStackTrace();
                    }
                }
                popQueue();
                break;
            case 12:
                BleDataResponse bleDataResponse11 = this.sendingDataResponse;
                if (bleDataResponse11 != null) {
                    try {
                        bleDataResponse11.onDataResponse(0, 0.0f, DataUnpack.unpackGetNowSport(bArr));
                    } catch (Exception e13) {
                        e13.printStackTrace();
                    }
                }
                popQueue();
                break;
            case 13:
                BleDataResponse bleDataResponse12 = this.sendingDataResponse;
                if (bleDataResponse12 != null) {
                    try {
                        bleDataResponse12.onDataResponse(0, 0.0f, DataUnpack.unpackGetHistoryOutline(bArr));
                    } catch (Exception e14) {
                        e14.printStackTrace();
                    }
                }
                popQueue();
                break;
            case 14:
                BleDataResponse bleDataResponse13 = this.sendingDataResponse;
                if (bleDataResponse13 != null) {
                    try {
                        bleDataResponse13.onDataResponse(0, 0.0f, DataUnpack.unpackGetRealTemp(bArr));
                    } catch (Exception e15) {
                        e15.printStackTrace();
                    }
                }
                popQueue();
                break;
            case 15:
                BleDataResponse bleDataResponse14 = this.sendingDataResponse;
                if (bleDataResponse14 != null) {
                    try {
                        bleDataResponse14.onDataResponse(0, 0.0f, DataUnpack.unpackGetScreenInfo(bArr));
                    } catch (Exception e16) {
                        e16.printStackTrace();
                    }
                }
                popQueue();
                break;
            case 16:
                BleDataResponse bleDataResponse15 = this.sendingDataResponse;
                if (bleDataResponse15 != null) {
                    try {
                        bleDataResponse15.onDataResponse(0, 0.0f, DataUnpack.unpackGetHeavenEarthAndFiveElement(bArr));
                    } catch (Exception e17) {
                        e17.printStackTrace();
                    }
                }
                popQueue();
                break;
            case 17:
                BleDataResponse bleDataResponse16 = this.sendingDataResponse;
                if (bleDataResponse16 != null) {
                    try {
                        bleDataResponse16.onDataResponse(0, 0.0f, DataUnpack.unpackGetRealBloodOxygen(bArr));
                    } catch (Exception e18) {
                        e18.printStackTrace();
                    }
                }
                popQueue();
                break;
            case 18:
                BleDataResponse bleDataResponse17 = this.sendingDataResponse;
                if (bleDataResponse17 != null) {
                    try {
                        bleDataResponse17.onDataResponse(0, 0.0f, DataUnpack.unpackGetCurrentAmbientLightIntensity(bArr));
                    } catch (Exception e19) {
                        e19.printStackTrace();
                    }
                }
                popQueue();
                break;
            case 19:
                BleDataResponse bleDataResponse18 = this.sendingDataResponse;
                if (bleDataResponse18 != null) {
                    try {
                        bleDataResponse18.onDataResponse(0, 0.0f, DataUnpack.unpackGetCurrentAmbientTempAndHumidity(bArr));
                    } catch (Exception e20) {
                        e20.printStackTrace();
                    }
                }
                popQueue();
                break;
            case 20:
                if (bArr.length != 2) {
                    if (bArr.length != 1) {
                        BleDataResponse bleDataResponse19 = this.sendingDataResponse;
                        if (bleDataResponse19 != null) {
                            try {
                                bleDataResponse19.onDataResponse(1, 0.0f, null);
                            } catch (Exception e21) {
                                e21.printStackTrace();
                            }
                        }
                        popQueue();
                        break;
                    } else {
                        if (this.sendingDataResponse != null) {
                            try {
                                this.scheduleInfo.put("data", this.scheduleInfos);
                                this.sendingDataResponse.onDataResponse(0, 0.0f, this.scheduleInfo);
                            } catch (Exception e22) {
                                e22.printStackTrace();
                            }
                        }
                        popQueue();
                        break;
                    }
                } else {
                    this.scheduleInfos.clear();
                    this.scheduleInfo.clear();
                    this.scheduleInfo.put("totalScheduleInfoValue", Integer.valueOf((bArr[0] & 255) + ((bArr[1] & 255) << 8)));
                    this.scheduleInfo.put("dataType", 532);
                    break;
                }
            case 21:
                BleDataResponse bleDataResponse20 = this.sendingDataResponse;
                if (bleDataResponse20 != null) {
                    try {
                        bleDataResponse20.onDataResponse(0, 0.0f, DataUnpack.unpackGetSensorSamplingInfo(bArr));
                    } catch (Exception e23) {
                        e23.printStackTrace();
                    }
                }
                popQueue();
                break;
            case 22:
                BleDataResponse bleDataResponse21 = this.sendingDataResponse;
                if (bleDataResponse21 != null) {
                    try {
                        bleDataResponse21.onDataResponse(0, 0.0f, DataUnpack.unpackGetCurrentSystemWorkingMode(bArr));
                    } catch (Exception e24) {
                        e24.printStackTrace();
                    }
                }
                popQueue();
                break;
            case 23:
                BleDataResponse bleDataResponse22 = this.sendingDataResponse;
                if (bleDataResponse22 != null) {
                    try {
                        bleDataResponse22.onDataResponse(0, 0.0f, DataUnpack.unpackGetInsuranceRelatedInfo(bArr));
                    } catch (Exception e25) {
                        e25.printStackTrace();
                    }
                }
                popQueue();
                break;
            case 24:
                BleDataResponse bleDataResponse23 = this.sendingDataResponse;
                if (bleDataResponse23 != null) {
                    try {
                        bleDataResponse23.onDataResponse(0, 0.0f, DataUnpack.unpackGetUploadConfigurationInfoOfReminder(bArr));
                    } catch (Exception e26) {
                        e26.printStackTrace();
                    }
                }
                popQueue();
                break;
            case 25:
                BleDataResponse bleDataResponse24 = this.sendingDataResponse;
                if (bleDataResponse24 != null) {
                    try {
                        bleDataResponse24.onDataResponse(0, 0.0f, DataUnpack.unpackGetStatusOfManualMode(bArr));
                    } catch (Exception e27) {
                        e27.printStackTrace();
                    }
                }
                popQueue();
                break;
            case 26:
                if (bArr.length != 2) {
                    if (bArr.length != 1) {
                        BleDataResponse bleDataResponse25 = this.sendingDataResponse;
                        if (bleDataResponse25 != null) {
                            try {
                                bleDataResponse25.onDataResponse(1, 0.0f, null);
                            } catch (Exception e28) {
                                e28.printStackTrace();
                            }
                        }
                        popQueue();
                        break;
                    } else {
                        if (this.sendingDataResponse != null) {
                            try {
                                this.scheduleInfo.put("data", this.scheduleInfos);
                                this.sendingDataResponse.onDataResponse(0, 0.0f, this.scheduleInfo);
                            } catch (Exception e29) {
                                e29.printStackTrace();
                            }
                        }
                        popQueue();
                        break;
                    }
                } else {
                    this.scheduleInfos.clear();
                    this.scheduleInfo.clear();
                    this.scheduleInfo.put("totalEventReminderInfoValue", Integer.valueOf((bArr[0] & 255) + ((bArr[1] & 255) << 8)));
                    this.scheduleInfo.put("dataType", 538);
                    break;
                }
            case 27:
                HashMap mapUnpackGetChipScheme = DataUnpack.unpackGetChipScheme(bArr);
                BleDataResponse bleDataResponse26 = this.sendingDataResponse;
                if (bleDataResponse26 != null) {
                    try {
                        bleDataResponse26.onDataResponse(0, 0.0f, mapUnpackGetChipScheme);
                    } catch (Exception e30) {
                        e30.printStackTrace();
                    }
                } else if (this.mBleStateCode == 9) {
                    sendSingleData2Device(512, new byte[]{JSONB.Constants.BC_INT32_SHORT_MAX, 67}, 2, null);
                }
                popQueue();
                break;
            default:
                switch (i2) {
                    case 31:
                        BleDataResponse bleDataResponse27 = this.sendingDataResponse;
                        if (bleDataResponse27 != null) {
                            try {
                                bleDataResponse27.onDataResponse(0, 0.0f, DataUnpack.unpackGetDeviceRemindInfo(bArr));
                            } catch (Exception e31) {
                                e31.printStackTrace();
                            }
                        }
                        popQueue();
                        break;
                    case 32:
                        BleDataResponse bleDataResponse28 = this.sendingDataResponse;
                        if (bleDataResponse28 != null) {
                            try {
                                bleDataResponse28.onDataResponse(0, 0.0f, DataUnpack.unpackGetAllRealDataFromDevice(bArr));
                            } catch (Exception e32) {
                                e32.printStackTrace();
                            }
                        }
                        popQueue();
                        break;
                    case 33:
                        BleDataResponse bleDataResponse29 = this.sendingDataResponse;
                        if (bleDataResponse29 != null) {
                            try {
                                bleDataResponse29.onDataResponse(0, 0.0f, DataUnpack.unpackGetLaserTreatmentParams(bArr));
                            } catch (Exception e33) {
                                e33.printStackTrace();
                            }
                        }
                        popQueue();
                        break;
                    case 34:
                        BleDataResponse bleDataResponse30 = this.sendingDataResponse;
                        if (bleDataResponse30 != null) {
                            try {
                                bleDataResponse30.onDataResponse(0, 0.0f, DataUnpack.unpackGetALiIOTActivationState(bArr));
                            } catch (Exception e34) {
                                e34.printStackTrace();
                            }
                        }
                        popQueue();
                        break;
                    case 35:
                        BleDataResponse bleDataResponse31 = this.sendingDataResponse;
                        if (bleDataResponse31 != null) {
                            try {
                                bleDataResponse31.onDataResponse(0, 0.0f, DataUnpack.unpackGetScreenParameters(bArr));
                            } catch (Exception e35) {
                                e35.printStackTrace();
                            }
                        }
                        popQueue();
                        break;
                    case 36:
                        break;
                    case 37:
                        BleDataResponse bleDataResponse32 = this.sendingDataResponse;
                        if (bleDataResponse32 != null) {
                            try {
                                bleDataResponse32.onDataResponse(0, 0.0f, DataUnpack.unpackGetPowerStatistics(bArr));
                            } catch (Exception e36) {
                                e36.printStackTrace();
                            }
                        }
                        popQueue();
                        break;
                    case 38:
                        BleDataResponse bleDataResponse33 = this.sendingDataResponse;
                        if (bleDataResponse33 != null) {
                            try {
                                bleDataResponse33.onDataResponse(0, 0.0f, DataUnpack.unpackGetSleepStatus(bArr));
                            } catch (Exception e37) {
                                e37.printStackTrace();
                            }
                        }
                        popQueue();
                        break;
                    case 39:
                        BleDataResponse bleDataResponse34 = this.sendingDataResponse;
                        if (bleDataResponse34 != null) {
                            try {
                                bleDataResponse34.onDataResponse(0, 0.0f, DataUnpack.unpackGetEcgMode(bArr));
                            } catch (Exception e38) {
                                e38.printStackTrace();
                            }
                        }
                        popQueue();
                        break;
                    case 40:
                        BleDataResponse bleDataResponse35 = this.sendingDataResponse;
                        if (bleDataResponse35 != null) {
                            try {
                                bleDataResponse35.onDataResponse(0, 0.0f, DataUnpack.unpackGetMeasurementFunction(bArr));
                            } catch (Exception e39) {
                                e39.printStackTrace();
                            }
                        }
                        popQueue();
                        break;
                    case 41:
                        BleDataResponse bleDataResponse36 = this.sendingDataResponse;
                        if (bleDataResponse36 != null) {
                            try {
                                bleDataResponse36.onDataResponse(0, 0.0f, DataUnpack.unpackGetAlgorithmicLicense(bArr));
                            } catch (Exception e40) {
                                e40.printStackTrace();
                            }
                        }
                        popQueue();
                        break;
                    case 42:
                        BleDataResponse bleDataResponse37 = this.sendingDataResponse;
                        if (bleDataResponse37 != null) {
                            try {
                                bleDataResponse37.onDataResponse(0, 0.0f, DataUnpack.unpackTerminalConf(bArr));
                            } catch (Exception e41) {
                                e41.printStackTrace();
                            }
                        }
                        popQueue();
                        break;
                    default:
                        popQueue();
                        break;
                }
                BleDataResponse bleDataResponse38 = this.sendingDataResponse;
                if (bleDataResponse38 != null) {
                    try {
                        bleDataResponse38.onDataResponse(0, 0.0f, DataUnpack.unpackGetCardInfo(bArr));
                    } catch (Exception e42) {
                        e42.printStackTrace();
                    }
                }
                popQueue();
                break;
        }
    }
}
