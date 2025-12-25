package com.yucheng.ycbtsdk.utils;

import com.yucheng.ycbtsdk.core.YCBTClientImpl;

/* loaded from: classes5.dex */
public class ECGJniCallback {
    public void jniCallback(int i2, float f2) {
        YCBTLog.e("hrv_evt_handle " + i2 + " params " + f2);
        YCBTClientImpl.getInstance().jniCallback(i2, f2);
    }
}
