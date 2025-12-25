package com.yucheng.ycbtsdk.jl;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.alibaba.aliagentsdk.AliAgentSdk;
import com.alibaba.aliagentsdk.api.IAliAgent;
import com.alibaba.aliagentsdk.api.IAliAgentDev;
import com.alibaba.aliagentsdk.api.IJustDataTransportAliBt;
import com.alibaba.aliagentsdk.callback.IBtDataUploadCallback;
import com.alibaba.aliagentsdk.callback.IConnectCallback;
import com.alibaba.aliagentsdk.callback.ID2Callback;
import com.alibaba.aliagentsdk.callback.IFgsStateCheckCallback;
import com.alibaba.aliagentsdk.callback.IRegisterCallback;
import com.alibaba.aliagentsdk.callback.ISend2BtCallback;
import com.alibaba.aliagentsdk.callback.ISend2LpCallback;
import com.jieli.jl_rcsp.interfaces.data.OnDataEventCallback;
import com.jieli.jl_rcsp.model.base.BaseError;
import com.jieli.jl_rcsp.util.CHexConver;
import com.yucheng.ycbtsdk.YCBTClient;
import com.yucheng.ycbtsdk.jl.BtDataProcessor;
import com.yucheng.ycbtsdk.response.BleDataResponse;
import com.yucheng.ycbtsdk.utils.ByteUtil;
import com.yucheng.ycbtsdk.utils.YCBTLog;

/* loaded from: classes5.dex */
public class ALiIOTKit {
    private static final String TAG = "ALiIOTKit";
    private static ALiIOTKit aLiIOTKit;
    private static IAliAgent mAliAgent;
    private static BtDataProcessor mBtDataProcessor;
    private static IBtDataUploadCallback mBtDataUploadCallback;
    private BleDataResponse bleDataResponse;
    private volatile boolean isIOTServerConnect;
    private final Handler mUIHandler = new Handler(Looper.getMainLooper());

    private class IConnectCallbackImpl implements IConnectCallback {
        private IConnectCallbackImpl() {
        }

        @Override // com.alibaba.aliagentsdk.callback.IConnectCallback
        public void onIotConnectFailure(String str, int i2) {
            YCBTLog.e("ALiIOT---onIotConnectFailure >>> IOT服务器连接失败, code = " + i2 + ", " + str);
            ALiIOTKit.this.isIOTServerConnect = false;
            if (ALiIOTKit.this.bleDataResponse != null) {
                ALiIOTKit.this.bleDataResponse.onDataResponse(-1, 0.0f, null);
                ALiIOTKit.this.bleDataResponse = null;
            }
        }

        @Override // com.alibaba.aliagentsdk.callback.IConnectCallback
        public void onIotConnected() {
            YCBTLog.e("ALiIOT---onIotConnected >>> IOT服务器已连接");
            ALiIOTKit.this.isIOTServerConnect = true;
            if (ALiIOTKit.this.bleDataResponse != null) {
                ALiIOTKit.this.bleDataResponse.onDataResponse(0, 0.0f, null);
                ALiIOTKit.this.bleDataResponse = null;
            }
        }

        @Override // com.alibaba.aliagentsdk.callback.IConnectCallback
        public void onIotDisconnected() {
            YCBTLog.e("ALiIOT---onIotDisconnected >>> IOT服务器已断开");
            ALiIOTKit.this.isIOTServerConnect = false;
        }
    }

    private class IJustDataTransportAliBtImpl extends IJustDataTransportAliBt {
        private IJustDataTransportAliBtImpl() {
        }

        @Override // com.alibaba.aliagentsdk.api.IJustDataTransportAliBt, com.alibaba.aliagentsdk.api.IAliBtSender
        public void sendData(byte[] bArr, ISend2BtCallback iSend2BtCallback) {
            YCBTLog.e("ALiIOT---IJustDataTransportAliBt#sendData >>> " + CHexConver.bytesToStr(bArr));
            ALiIOTKit.this.writeDataToDevice(bArr, iSend2BtCallback);
        }

        @Override // com.alibaba.aliagentsdk.api.IJustDataTransportAliBt, com.alibaba.aliagentsdk.api.IAliBt
        public void setBtDataUploadCallback(IBtDataUploadCallback iBtDataUploadCallback) {
            YCBTLog.e("ALiIOT---setBtDataUploadCallback#sendData >>> " + iBtDataUploadCallback);
            IBtDataUploadCallback unused = ALiIOTKit.mBtDataUploadCallback = iBtDataUploadCallback;
        }
    }

    private ALiIOTKit(Context context) {
        AliAgentSdk aliAgentSdk = AliAgentSdk.getInstance();
        aliAgentSdk.init(context, true);
        IAliAgent agent = aliAgentSdk.getAgent();
        mAliAgent = agent;
        agent.customBtImpl(new IJustDataTransportAliBtImpl());
        mAliAgent.setLpConnectedCallback(new IConnectCallbackImpl());
        mBtDataProcessor = new BtDataProcessor(WatchManager.getInstance(), new BtDataProcessor.OnIOTEventListener() { // from class: com.yucheng.ycbtsdk.jl.ALiIOTKit$$ExternalSyntheticLambda1
            @Override // com.yucheng.ycbtsdk.jl.BtDataProcessor.OnIOTEventListener
            public final void onIotData(BluetoothDevice bluetoothDevice, int i2, byte[] bArr) {
                this.f$0.m2602lambda$new$0$comyuchengycbtsdkjlALiIOTKit(bluetoothDevice, i2, bArr);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void connectIOTServer() {
        if (mAliAgent == null) {
            return;
        }
        YCBTLog.e("ALiIOT---connectIOTServer : isIOTServerConnect = " + this.isIOTServerConnect);
        if (!this.isIOTServerConnect) {
            mAliAgent.connectLp();
            return;
        }
        BleDataResponse bleDataResponse = this.bleDataResponse;
        if (bleDataResponse != null) {
            bleDataResponse.onDataResponse(0, 0.0f, null);
            this.bleDataResponse = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: dealWithDeviceConnectedEvent, reason: merged with bridge method [inline-methods] */
    public void m2603lambda$startChecked$1$comyuchengycbtsdkjlALiIOTKit() {
        if (mAliAgent == null) {
            return;
        }
        YCBTLog.e("ALiIOT---dealWithDeviceConnectedEvent : checkFgsState >>> ");
        mAliAgent.checkFgsState(new IFgsStateCheckCallback() { // from class: com.yucheng.ycbtsdk.jl.ALiIOTKit.1
            @Override // com.alibaba.aliagentsdk.callback.IFgsStateCheckCallback
            public void onFgsCheckError(String str, int i2) {
                YCBTLog.e("ALiIOT---onFgsCheckError : code = " + i2 + ", " + str);
                if (i2 == 0 || ALiIOTKit.this.bleDataResponse == null) {
                    return;
                }
                ALiIOTKit.this.bleDataResponse.onDataResponse(i2, 0.0f, null);
                ALiIOTKit.this.bleDataResponse = null;
            }

            @Override // com.alibaba.aliagentsdk.callback.IFgsStateCheckCallback
            public void onFgsCheckSuccess() {
                YCBTLog.e("ALiIOT---onFgsCheckSuccess : onFgsCheckSuccess >>>>");
                ALiIOTKit.this.connectIOTServer();
            }
        });
    }

    private void disconnectIOTServer() {
        if (mAliAgent == null) {
            return;
        }
        YCBTLog.e("ALiIOT---disconnectIOTServer : isIOTServerConnect = " + this.isIOTServerConnect);
        if (this.isIOTServerConnect) {
            try {
                mAliAgent.disconnectLp();
            } catch (Exception e2) {
                e2.printStackTrace();
            } finally {
                this.isIOTServerConnect = false;
            }
        }
    }

    public static synchronized ALiIOTKit getInstance(Context context) {
        if (aLiIOTKit == null) {
            aLiIOTKit = new ALiIOTKit(context);
        }
        return aLiIOTKit;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void writeDataToDevice(byte[] bArr, final ISend2BtCallback iSend2BtCallback) {
        YCBTLog.e("ALiIOT----发送阿里数据==" + ByteUtil.byteToString(bArr) + "--mAliAgent==" + mAliAgent);
        mBtDataProcessor.writeAliIotData(bArr, new OnDataEventCallback() { // from class: com.yucheng.ycbtsdk.jl.ALiIOTKit.2
            @Override // com.jieli.jl_rcsp.interfaces.data.OnDataEventCallback
            public void onBegin(int i2) {
                YCBTLog.e("ALiIOT---writeDataToDevice : onBegin :: way = " + i2);
            }

            @Override // com.jieli.jl_rcsp.interfaces.data.OnDataEventCallback
            public void onError(BaseError baseError) {
                YCBTLog.e("ALiIOT---writeDataToDevice : onError >>> " + baseError);
                ISend2BtCallback iSend2BtCallback2 = iSend2BtCallback;
                if (iSend2BtCallback2 != null) {
                    iSend2BtCallback2.onSendFailed(baseError.getMessage(), baseError.getSubCode());
                }
            }

            @Override // com.jieli.jl_rcsp.interfaces.data.OnDataEventCallback
            public void onProgress(float f2) {
                YCBTLog.e("ALiIOT---writeDataToDevice : onProgress :: progress = " + f2);
            }

            @Override // com.jieli.jl_rcsp.interfaces.data.OnDataEventCallback
            public void onStop(int i2, byte[] bArr2) {
                YCBTLog.e("ALiIOT---writeDataToDevice : onStop :: type = " + i2 + ", data = " + (bArr2 == null ? 0 : bArr2.length) + "callback==" + iSend2BtCallback);
                ISend2BtCallback iSend2BtCallback2 = iSend2BtCallback;
                if (iSend2BtCallback2 != null) {
                    iSend2BtCallback2.onSendSuccess();
                }
            }
        });
    }

    public void destroy() {
        disconnectIOTServer();
        Handler handler = this.mUIHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        BtDataProcessor btDataProcessor = mBtDataProcessor;
        if (btDataProcessor != null) {
            btDataProcessor.destroy();
        }
        aLiIOTKit = null;
        mAliAgent = null;
        mBtDataProcessor = null;
    }

    public void handleIOTData(byte[] bArr) {
        YCBTLog.e("ALiIOT---handleIOTData :: " + CHexConver.bytesToStr(bArr));
        IBtDataUploadCallback iBtDataUploadCallback = mBtDataUploadCallback;
        if (iBtDataUploadCallback != null) {
            iBtDataUploadCallback.onDataUpload(bArr);
        }
    }

    /* renamed from: lambda$new$0$com-yucheng-ycbtsdk-jl-ALiIOTKit, reason: not valid java name */
    /* synthetic */ void m2602lambda$new$0$comyuchengycbtsdkjlALiIOTKit(BluetoothDevice bluetoothDevice, int i2, byte[] bArr) {
        handleIOTData(bArr);
    }

    public void startChecked(BleDataResponse bleDataResponse) {
        if (YCBTClient.connectState() == 10 && YCBTClient.getAuthPass()) {
            this.bleDataResponse = bleDataResponse;
            this.mUIHandler.postDelayed(new Runnable() { // from class: com.yucheng.ycbtsdk.jl.ALiIOTKit$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m2603lambda$startChecked$1$comyuchengycbtsdkjlALiIOTKit();
                }
            }, 500L);
        }
    }

    public boolean testId2DataSend(String str, long j2, ID2Callback iD2Callback) {
        IAliAgent iAliAgent = mAliAgent;
        if (iAliAgent == null) {
            return false;
        }
        ((IAliAgentDev) iAliAgent).testId2DataSend(str, j2, iD2Callback);
        return true;
    }

    public boolean testRegister(String str, String str2, String str3, IRegisterCallback iRegisterCallback) {
        IAliAgent iAliAgent = mAliAgent;
        if (iAliAgent == null) {
            return false;
        }
        ((IAliAgentDev) iAliAgent).testRegister(str, str2, str3, iRegisterCallback);
        return true;
    }

    public boolean testSendDataToDevice(String str, String str2, ISend2BtCallback iSend2BtCallback) {
        IAliAgent iAliAgent = mAliAgent;
        if (iAliAgent == null) {
            return false;
        }
        ((IAliAgentDev) iAliAgent).testSendData2Bt(str, str2, iSend2BtCallback);
        return true;
    }

    public boolean testSendTransCodeToServer(String str, String str2, ISend2LpCallback iSend2LpCallback) {
        IAliAgent iAliAgent = mAliAgent;
        if (iAliAgent == null) {
            return false;
        }
        ((IAliAgentDev) iAliAgent).testSendTransCodeToLp(str, str2, iSend2LpCallback);
        return true;
    }

    public boolean testRegister(String str, String str2, String str3, String str4, String str5, IRegisterCallback iRegisterCallback) {
        IAliAgent iAliAgent = mAliAgent;
        if (iAliAgent == null) {
            return false;
        }
        ((IAliAgentDev) iAliAgent).testRegister(str, str2, str3, str4, str5, iRegisterCallback);
        return true;
    }
}
