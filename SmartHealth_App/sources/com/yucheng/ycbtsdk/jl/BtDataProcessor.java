package com.yucheng.ycbtsdk.jl;

import android.bluetooth.BluetoothDevice;
import com.dd.plist.ASCIIPropertyListParser;
import com.jieli.jl_rcsp.interfaces.data.OnDataEventCallback;
import com.jieli.jl_rcsp.interfaces.watch.OnWatchCallback;
import com.jieli.jl_rcsp.model.base.BaseError;
import com.jieli.jl_rcsp.model.data.SendParams;
import com.yucheng.ycbtsdk.utils.YCBTLog;
import java.util.concurrent.LinkedBlockingQueue;

/* loaded from: classes5.dex */
public class BtDataProcessor {
    private volatile boolean isSendData;
    private final OnIOTEventListener mListener;
    private final OnWatchCallback mWatchCallback;
    private final WatchManager mWatchManager;
    private final String tag = "BtDataProcessor";
    private final LinkedBlockingQueue<SendTaskParam> mSendTaskQueue = new LinkedBlockingQueue<>();

    public interface OnIOTEventListener {
        void onIotData(BluetoothDevice bluetoothDevice, int i2, byte[] bArr);
    }

    private static class SendTaskParam {
        private final OnDataEventCallback mCallback;
        private final SendParams mParam;

        public SendTaskParam(SendParams sendParams, OnDataEventCallback onDataEventCallback) {
            this.mParam = sendParams;
            this.mCallback = onDataEventCallback;
        }

        public OnDataEventCallback getCallback() {
            return this.mCallback;
        }

        public SendParams getParam() {
            return this.mParam;
        }

        public String toString() {
            return "SendTaskParam{mParam=" + this.mParam + ", mCallback=" + this.mCallback + ASCIIPropertyListParser.DICTIONARY_END_TOKEN;
        }
    }

    public BtDataProcessor(WatchManager watchManager, OnIOTEventListener onIOTEventListener) {
        OnWatchCallback onWatchCallback = new OnWatchCallback() { // from class: com.yucheng.ycbtsdk.jl.BtDataProcessor.2
            @Override // com.jieli.jl_rcsp.interfaces.watch.OnWatchCallback
            public void onBigDataError(BluetoothDevice bluetoothDevice, BaseError baseError) {
                YCBTLog.e("chong---onBigDataError >> " + baseError);
            }

            @Override // com.jieli.jl_rcsp.interfaces.watch.OnWatchCallback
            public void onReceiveBigData(BluetoothDevice bluetoothDevice, int i2, byte[] bArr) {
                YCBTLog.e("chong---onReceiveData >> " + i2 + ", data : " + (bArr == null ? 0 : bArr.length));
                if (i2 != 1 || BtDataProcessor.this.mListener == null) {
                    return;
                }
                BtDataProcessor.this.mListener.onIotData(bluetoothDevice, i2, bArr);
            }
        };
        this.mWatchCallback = onWatchCallback;
        this.mWatchManager = watchManager;
        this.mListener = onIOTEventListener;
        watchManager.registerOnWatchCallback(onWatchCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startSendTask() {
        if (this.isSendData) {
            YCBTLog.e("chong---startSendTask >> task is running");
            return;
        }
        final SendTaskParam sendTaskParamPeek = this.mSendTaskQueue.peek();
        if (sendTaskParamPeek == null) {
            YCBTLog.e("chong---startSendTask >> SendTaskParam is null");
        } else {
            YCBTLog.e("chong---startSendTask >> sendLargeData >>> " + sendTaskParamPeek);
            this.mWatchManager.sendLargeData(sendTaskParamPeek.getParam(), new OnDataEventCallback() { // from class: com.yucheng.ycbtsdk.jl.BtDataProcessor.1
                @Override // com.jieli.jl_rcsp.interfaces.data.OnDataEventCallback
                public void onBegin(int i2) {
                    YCBTLog.e("chong---startSendTask >> onBegin >>>");
                    BtDataProcessor.this.isSendData = true;
                    if (sendTaskParamPeek.getCallback() != null) {
                        sendTaskParamPeek.getCallback().onBegin(i2);
                    }
                }

                @Override // com.jieli.jl_rcsp.interfaces.data.OnDataEventCallback
                public void onError(BaseError baseError) {
                    YCBTLog.e("chong---startSendTask >> onError >>> " + baseError);
                    BtDataProcessor.this.isSendData = false;
                    BtDataProcessor.this.mSendTaskQueue.clear();
                    if (sendTaskParamPeek.getCallback() != null) {
                        sendTaskParamPeek.getCallback().onError(baseError);
                    }
                }

                @Override // com.jieli.jl_rcsp.interfaces.data.OnDataEventCallback
                public void onProgress(float f2) {
                    YCBTLog.e("chong---startSendTask >> onProgress >>> " + f2);
                    if (sendTaskParamPeek.getCallback() != null) {
                        sendTaskParamPeek.getCallback().onProgress(f2);
                    }
                }

                @Override // com.jieli.jl_rcsp.interfaces.data.OnDataEventCallback
                public void onStop(int i2, byte[] bArr) {
                    YCBTLog.e("chong---startSendTask >> onFinish >>> ");
                    BtDataProcessor.this.isSendData = false;
                    BtDataProcessor.this.mSendTaskQueue.poll();
                    if (sendTaskParamPeek.getCallback() != null) {
                        sendTaskParamPeek.getCallback().onStop(i2, bArr);
                    }
                    BtDataProcessor.this.startSendTask();
                }
            });
        }
    }

    public void destroy() {
        YCBTLog.e("chong---destroy >> ");
        this.mSendTaskQueue.clear();
        this.mWatchManager.unregisterOnWatchCallback(this.mWatchCallback);
    }

    public void writeAliIotData(byte[] bArr, OnDataEventCallback onDataEventCallback) {
        try {
            this.mSendTaskQueue.put(new SendTaskParam(new SendParams(1, 0, 4096, 4096, bArr), onDataEventCallback));
            YCBTLog.e("chong---writeAliIotData >> put task in queue...");
            startSendTask();
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
    }
}
