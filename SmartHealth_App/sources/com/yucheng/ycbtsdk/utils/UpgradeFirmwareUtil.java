package com.yucheng.ycbtsdk.utils;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import com.yucheng.ycbtsdk.YCBTClient;
import com.yucheng.ycbtsdk.jl.JLOTAManager;
import com.yucheng.ycbtsdk.upgrade.DfuCallBack;
import com.yucheng.ycbtsdk.upgrade.NordicDfuUpdateUtil;
import com.yucheng.ycbtsdk.upgrade.RealtekDfuUpdateUtil;

/* loaded from: classes5.dex */
public class UpgradeFirmwareUtil {
    public static void startUpgrade(Context context, String str, DfuCallBack dfuCallBack) {
        if (YCBTClient.connectState() != 10) {
            if (dfuCallBack != null) {
                dfuCallBack.disconnect();
            }
        } else {
            if (str != null) {
                upgrade(context, str, dfuCallBack);
                return;
            }
            YCBTLog.e("FirmwareFilePath is null.");
            if (dfuCallBack != null) {
                dfuCallBack.error("FirmwareFilePath is null.");
            }
        }
    }

    private static void upgrade(final Context context, final String str, final DfuCallBack dfuCallBack) {
        int chipScheme = SPUtil.getChipScheme();
        final String bindDeviceMac = YCBTClient.getBindDeviceMac();
        final String bindDeviceName = YCBTClient.getBindDeviceName();
        if (!BluetoothAdapter.checkBluetoothAddress(bindDeviceMac)) {
            if (dfuCallBack != null) {
                dfuCallBack.error("mac is null.");
                return;
            }
            return;
        }
        YCBTLog.e("firmwareFilePath==" + str + "--chipScheme==" + chipScheme);
        if (chipScheme == 0) {
            if (context instanceof Activity) {
                ((Activity) context).runOnUiThread(new Runnable() { // from class: com.yucheng.ycbtsdk.utils.UpgradeFirmwareUtil.1
                    @Override // java.lang.Runnable
                    public void run() {
                        NordicDfuUpdateUtil.getInstance(context).dfuInit(bindDeviceMac, bindDeviceName, str, dfuCallBack);
                    }
                });
                return;
            }
            return;
        }
        if (chipScheme == 1 || chipScheme == 2) {
            if (context instanceof Activity) {
                ((Activity) context).runOnUiThread(new Runnable() { // from class: com.yucheng.ycbtsdk.utils.UpgradeFirmwareUtil.2
                    @Override // java.lang.Runnable
                    public void run() {
                        RealtekDfuUpdateUtil.getInstance().dfuInit(context, bindDeviceMac, str, dfuCallBack);
                    }
                });
            }
        } else if (InnerUtils.isJieLiChipScheme(chipScheme)) {
            if (context instanceof Activity) {
                ((Activity) context).runOnUiThread(new Runnable() { // from class: com.yucheng.ycbtsdk.utils.UpgradeFirmwareUtil.3
                    @Override // java.lang.Runnable
                    public void run() {
                        JLOTAManager.getInstance(context).dfuInit(bindDeviceMac, bindDeviceName, str, dfuCallBack);
                    }
                });
            }
        } else {
            YCBTLog.e("Unsupported Chip Vendors");
            if (dfuCallBack != null) {
                dfuCallBack.error("Unsupported Chip Vendors");
            }
        }
    }
}
