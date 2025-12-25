package com.yucheng.ycbtsdk.upgrade;

import android.content.Context;
import com.yucheng.ycbtsdk.jl.JLOTAManager;
import com.yucheng.ycbtsdk.upgrade.utils.DfuService;
import com.yucheng.ycbtsdk.utils.YCBTLog;
import no.nordicsemi.android.dfu.DfuProgressListener;
import no.nordicsemi.android.dfu.DfuProgressListenerAdapter;
import no.nordicsemi.android.dfu.DfuServiceInitiator;
import no.nordicsemi.android.dfu.DfuServiceListenerHelper;

/* loaded from: classes5.dex */
public class NordicDfuUpdateUtil {
    private static NordicDfuUpdateUtil nordicDfuUpdateUtil;
    private DfuCallBack callBack;
    private Context context;
    private final DfuProgressListener mDfuProgressListener;

    private NordicDfuUpdateUtil() {
        this.mDfuProgressListener = new DfuProgressListenerAdapter() { // from class: com.yucheng.ycbtsdk.upgrade.NordicDfuUpdateUtil.1
            @Override // no.nordicsemi.android.dfu.DfuProgressListenerAdapter, no.nordicsemi.android.dfu.DfuProgressListener
            public void onDeviceConnected(String str) {
                YCBTLog.e("dfu-onDeviceConnected");
                if (NordicDfuUpdateUtil.this.callBack != null) {
                    NordicDfuUpdateUtil.this.callBack.connected();
                }
            }

            @Override // no.nordicsemi.android.dfu.DfuProgressListenerAdapter, no.nordicsemi.android.dfu.DfuProgressListener
            public void onDeviceConnecting(String str) {
                YCBTLog.e("dfu-onDeviceConnecting");
                if (NordicDfuUpdateUtil.this.callBack != null) {
                    NordicDfuUpdateUtil.this.callBack.connecting();
                }
            }

            @Override // no.nordicsemi.android.dfu.DfuProgressListenerAdapter, no.nordicsemi.android.dfu.DfuProgressListener
            public void onDeviceDisconnecting(String str) {
                YCBTLog.e("dfu-onDeviceDisconnecting");
                if (NordicDfuUpdateUtil.this.callBack != null) {
                    NordicDfuUpdateUtil.this.callBack.disconnect();
                }
            }

            @Override // no.nordicsemi.android.dfu.DfuProgressListenerAdapter, no.nordicsemi.android.dfu.DfuProgressListener
            public void onDfuAborted(String str) {
                YCBTLog.e("dfu-onDfuAborted " + str);
                if (NordicDfuUpdateUtil.this.callBack != null) {
                    NordicDfuUpdateUtil.this.callBack.failed("onDfuAborted");
                }
            }

            @Override // no.nordicsemi.android.dfu.DfuProgressListenerAdapter, no.nordicsemi.android.dfu.DfuProgressListener
            public void onDfuCompleted(String str) {
                YCBTLog.e("dfu-onDfuCompleted " + str);
                if (NordicDfuUpdateUtil.this.callBack != null) {
                    NordicDfuUpdateUtil.this.callBack.success();
                }
            }

            @Override // no.nordicsemi.android.dfu.DfuProgressListenerAdapter, no.nordicsemi.android.dfu.DfuProgressListener
            public void onDfuProcessStarting(String str) {
                YCBTLog.e("dfu-onDfuProcessStarting " + str);
            }

            @Override // no.nordicsemi.android.dfu.DfuProgressListenerAdapter, no.nordicsemi.android.dfu.DfuProgressListener
            public void onEnablingDfuMode(String str) {
                YCBTLog.e("dfu-onEnablingDfuMode " + str);
            }

            @Override // no.nordicsemi.android.dfu.DfuProgressListenerAdapter, no.nordicsemi.android.dfu.DfuProgressListener
            public void onError(String str, int i2, int i3, String str2) {
                YCBTLog.e("dfu-onError");
                if (NordicDfuUpdateUtil.this.callBack != null) {
                    NordicDfuUpdateUtil.this.callBack.failed("onError");
                }
            }

            @Override // no.nordicsemi.android.dfu.DfuProgressListenerAdapter, no.nordicsemi.android.dfu.DfuProgressListener
            public void onFirmwareValidating(String str) {
                YCBTLog.e("dfu-onFirmwareValidating " + str);
            }

            @Override // no.nordicsemi.android.dfu.DfuProgressListenerAdapter, no.nordicsemi.android.dfu.DfuProgressListener
            public void onProgressChanged(String str, int i2, float f2, float f3, int i3, int i4) {
                YCBTLog.e("dfu-progress--" + i2 + "," + f2 + "," + f3 + "," + i3 + ":" + i4);
                if (NordicDfuUpdateUtil.this.callBack != null) {
                    NordicDfuUpdateUtil.this.callBack.progress(i2);
                }
            }
        };
    }

    public static synchronized NordicDfuUpdateUtil getInstance(Context context) {
        if (nordicDfuUpdateUtil == null) {
            nordicDfuUpdateUtil = new NordicDfuUpdateUtil(context);
        }
        return nordicDfuUpdateUtil;
    }

    public void dfuInit(String str, String str2, String str3, DfuCallBack dfuCallBack) {
        if (str3.endsWith(JLOTAManager.OTA_ZIP_SUFFIX)) {
            this.callBack = dfuCallBack;
            new DfuServiceInitiator(str).setDeviceName(str2).setKeepBond(false).setForceDfu(true).setPacketsReceiptNotificationsEnabled(false).setPacketsReceiptNotificationsValue(12).setUnsafeExperimentalButtonlessServiceInSecureDfuEnabled(true).setForeground(false).setDisableNotification(true).setZip(null, str3).setScope(2).start(this.context, DfuService.class);
            YCBTLog.d("chong-----开始升级。。。。。。");
        } else if (dfuCallBack != null) {
            dfuCallBack.failed("it's not upgrade file.");
        }
    }

    public void onDestroy() {
        DfuProgressListener dfuProgressListener = this.mDfuProgressListener;
        if (dfuProgressListener != null) {
            DfuServiceListenerHelper.unregisterProgressListener(this.context, dfuProgressListener);
        }
    }

    private NordicDfuUpdateUtil(Context context) {
        DfuProgressListenerAdapter dfuProgressListenerAdapter = new DfuProgressListenerAdapter() { // from class: com.yucheng.ycbtsdk.upgrade.NordicDfuUpdateUtil.1
            @Override // no.nordicsemi.android.dfu.DfuProgressListenerAdapter, no.nordicsemi.android.dfu.DfuProgressListener
            public void onDeviceConnected(String str) {
                YCBTLog.e("dfu-onDeviceConnected");
                if (NordicDfuUpdateUtil.this.callBack != null) {
                    NordicDfuUpdateUtil.this.callBack.connected();
                }
            }

            @Override // no.nordicsemi.android.dfu.DfuProgressListenerAdapter, no.nordicsemi.android.dfu.DfuProgressListener
            public void onDeviceConnecting(String str) {
                YCBTLog.e("dfu-onDeviceConnecting");
                if (NordicDfuUpdateUtil.this.callBack != null) {
                    NordicDfuUpdateUtil.this.callBack.connecting();
                }
            }

            @Override // no.nordicsemi.android.dfu.DfuProgressListenerAdapter, no.nordicsemi.android.dfu.DfuProgressListener
            public void onDeviceDisconnecting(String str) {
                YCBTLog.e("dfu-onDeviceDisconnecting");
                if (NordicDfuUpdateUtil.this.callBack != null) {
                    NordicDfuUpdateUtil.this.callBack.disconnect();
                }
            }

            @Override // no.nordicsemi.android.dfu.DfuProgressListenerAdapter, no.nordicsemi.android.dfu.DfuProgressListener
            public void onDfuAborted(String str) {
                YCBTLog.e("dfu-onDfuAborted " + str);
                if (NordicDfuUpdateUtil.this.callBack != null) {
                    NordicDfuUpdateUtil.this.callBack.failed("onDfuAborted");
                }
            }

            @Override // no.nordicsemi.android.dfu.DfuProgressListenerAdapter, no.nordicsemi.android.dfu.DfuProgressListener
            public void onDfuCompleted(String str) {
                YCBTLog.e("dfu-onDfuCompleted " + str);
                if (NordicDfuUpdateUtil.this.callBack != null) {
                    NordicDfuUpdateUtil.this.callBack.success();
                }
            }

            @Override // no.nordicsemi.android.dfu.DfuProgressListenerAdapter, no.nordicsemi.android.dfu.DfuProgressListener
            public void onDfuProcessStarting(String str) {
                YCBTLog.e("dfu-onDfuProcessStarting " + str);
            }

            @Override // no.nordicsemi.android.dfu.DfuProgressListenerAdapter, no.nordicsemi.android.dfu.DfuProgressListener
            public void onEnablingDfuMode(String str) {
                YCBTLog.e("dfu-onEnablingDfuMode " + str);
            }

            @Override // no.nordicsemi.android.dfu.DfuProgressListenerAdapter, no.nordicsemi.android.dfu.DfuProgressListener
            public void onError(String str, int i2, int i3, String str2) {
                YCBTLog.e("dfu-onError");
                if (NordicDfuUpdateUtil.this.callBack != null) {
                    NordicDfuUpdateUtil.this.callBack.failed("onError");
                }
            }

            @Override // no.nordicsemi.android.dfu.DfuProgressListenerAdapter, no.nordicsemi.android.dfu.DfuProgressListener
            public void onFirmwareValidating(String str) {
                YCBTLog.e("dfu-onFirmwareValidating " + str);
            }

            @Override // no.nordicsemi.android.dfu.DfuProgressListenerAdapter, no.nordicsemi.android.dfu.DfuProgressListener
            public void onProgressChanged(String str, int i2, float f2, float f3, int i3, int i4) {
                YCBTLog.e("dfu-progress--" + i2 + "," + f2 + "," + f3 + "," + i3 + ":" + i4);
                if (NordicDfuUpdateUtil.this.callBack != null) {
                    NordicDfuUpdateUtil.this.callBack.progress(i2);
                }
            }
        };
        this.mDfuProgressListener = dfuProgressListenerAdapter;
        this.context = context;
        DfuServiceListenerHelper.registerProgressListener(context, dfuProgressListenerAdapter);
    }
}
