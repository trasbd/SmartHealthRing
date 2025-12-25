package com.yucheng.ycbtsdk.upgrade;

import android.content.Context;
import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.dfu.image.BinFactory;
import com.realsil.sdk.dfu.image.LoadParams;
import com.realsil.sdk.dfu.model.BinInfo;
import com.realsil.sdk.dfu.model.DfuConfig;
import com.realsil.sdk.dfu.model.DfuProgressInfo;
import com.realsil.sdk.dfu.model.Throughput;
import com.realsil.sdk.dfu.utils.DfuAdapter;
import com.realsil.sdk.dfu.utils.GattDfuAdapter;
import com.yucheng.ycbtsdk.utils.YCBTLog;
import no.nordicsemi.android.dfu.DfuBaseService;

/* loaded from: classes5.dex */
public class RealtekDfuUpdateUtil {
    private static RealtekDfuUpdateUtil realtekDfuUpdateUtil;
    private DfuCallBack callBack;
    private BinInfo mBinInfo;
    private GattDfuAdapter mDfuAdapter;
    private DfuConfig mDfuConfig;
    private DfuAdapter.DfuHelperCallback mDfuHelperCallback = new DfuAdapter.DfuHelperCallback() { // from class: com.yucheng.ycbtsdk.upgrade.RealtekDfuUpdateUtil.1
        @Override // com.realsil.sdk.dfu.utils.DfuAdapter.DfuHelperCallback
        public void onError(int i2, int i3) {
            super.onError(i2, i3);
            if (RealtekDfuUpdateUtil.this.callBack != null) {
                YCBTLog.e(i2 == 65536 ? "OTA:" : "CONNECTION:" + i3);
                RealtekDfuUpdateUtil.this.callBack.failed(i2 != 65536 ? "CONNECTION:" + i3 : "OTA:");
            }
        }

        @Override // com.realsil.sdk.dfu.utils.DfuAdapter.DfuHelperCallback
        public void onProcessStateChanged(int i2, Throughput throughput) {
            super.onProcessStateChanged(i2, throughput);
            if (i2 == 258 && RealtekDfuUpdateUtil.this.callBack != null) {
                RealtekDfuUpdateUtil.this.callBack.success();
            }
            YCBTLog.e("dfu-state==" + i2);
        }

        @Override // com.realsil.sdk.dfu.utils.DfuAdapter.DfuHelperCallback
        public void onProgressChanged(DfuProgressInfo dfuProgressInfo) {
            super.onProgressChanged(dfuProgressInfo);
            if (dfuProgressInfo == null || RealtekDfuUpdateUtil.this.callBack == null) {
                return;
            }
            RealtekDfuUpdateUtil.this.callBack.progress(dfuProgressInfo.getProgress());
        }

        @Override // com.realsil.sdk.dfu.utils.DfuAdapter.DfuHelperCallback
        public void onStateChanged(int i2) {
            super.onStateChanged(i2);
            YCBTLog.e("chong---Realtek state == " + i2);
            if (i2 == 258) {
                RealtekDfuUpdateUtil.this.mDfuAdapter.connectDevice(RealtekDfuUpdateUtil.this.mDfuConfig.getAddress());
                return;
            }
            if (i2 == 527) {
                if (RealtekDfuUpdateUtil.this.mDfuAdapter.startOtaProcedure(RealtekDfuUpdateUtil.this.mDfuConfig)) {
                    if (RealtekDfuUpdateUtil.this.callBack != null) {
                        RealtekDfuUpdateUtil.this.callBack.connected();
                        return;
                    }
                    return;
                } else {
                    if (RealtekDfuUpdateUtil.this.callBack != null) {
                        RealtekDfuUpdateUtil.this.callBack.failed("dft-openFailed");
                        return;
                    }
                    return;
                }
            }
            if (i2 == 4097 || i2 == 4098) {
                if (RealtekDfuUpdateUtil.this.callBack != null) {
                    RealtekDfuUpdateUtil.this.callBack.disconnect();
                }
            } else {
                if (i2 != 533 || RealtekDfuUpdateUtil.this.callBack == null) {
                    return;
                }
                RealtekDfuUpdateUtil.this.callBack.connecting();
            }
        }
    };

    private RealtekDfuUpdateUtil() {
    }

    public static synchronized RealtekDfuUpdateUtil getInstance() {
        if (realtekDfuUpdateUtil == null) {
            realtekDfuUpdateUtil = new RealtekDfuUpdateUtil();
        }
        return realtekDfuUpdateUtil;
    }

    public void dfuInit(Context context, String str, String str2, DfuCallBack dfuCallBack) {
        YCBTLog.e("RealtekDfuUpdateUtil.dfuInit");
        this.callBack = dfuCallBack;
        ZLogger.initialize(DfuBaseService.NOTIFICATION_CHANNEL_DFU, true);
        GattDfuAdapter gattDfuAdapter = GattDfuAdapter.getInstance(context);
        this.mDfuAdapter = gattDfuAdapter;
        gattDfuAdapter.initialize(this.mDfuHelperCallback);
        DfuConfig dfuConfig = new DfuConfig();
        this.mDfuConfig = dfuConfig;
        dfuConfig.setAddress(str);
        this.mDfuConfig.setProtocolType(0);
        try {
            this.mBinInfo = BinFactory.loadImageBinInfo(new LoadParams.Builder().with(context).setFilePath(str2).setFileSuffix("bin").setOtaDeviceInfo(null).setIcCheckEnabled(true).setSectionSizeCheckEnabled(false).setVersionCheckEnabled(true).build());
        } catch (Exception e2) {
            e2.printStackTrace();
            YCBTLog.e("BinFactory.loadImageBinInfo " + e2.getMessage());
        }
        this.mDfuConfig.setBreakpointResumeEnabled(true);
        this.mDfuConfig.setAutomaticActiveEnabled(true);
        this.mDfuConfig.setBatteryCheckEnabled(false);
        this.mDfuConfig.setLowBatteryThreshold(30);
        this.mDfuConfig.setVersionCheckEnabled(false);
        this.mDfuConfig.setIcCheckEnabled(true);
        this.mDfuConfig.setSectionSizeCheckEnabled(true);
        this.mDfuConfig.setWaitActiveCmdAckEnabled(false);
        this.mDfuConfig.setFileLocation(0);
        this.mDfuConfig.setFileSuffix("bin");
        this.mDfuConfig.setOtaWorkMode(16);
        this.mDfuConfig.setFilePath(this.mBinInfo.path);
    }

    public void onDestroy() {
        GattDfuAdapter gattDfuAdapter = this.mDfuAdapter;
        if (gattDfuAdapter != null) {
            gattDfuAdapter.abort();
            this.mDfuAdapter.close();
        }
    }
}
