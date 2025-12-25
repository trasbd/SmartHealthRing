package com.yucheng.ycbtsdk.core;

import com.yucheng.ycbtsdk.bean.AIDataBean;
import com.yucheng.ycbtsdk.bean.AIRealDataBean;
import com.yucheng.ycbtsdk.bean.HealthNormBean;
import com.yucheng.ycbtsdk.bean.ImageBean;

/* loaded from: classes5.dex */
public class AIData {
    private static AIData aiData;

    static {
        System.loadLibrary("EcgAnaly");
    }

    public static synchronized AIData getInstance() {
        if (aiData == null) {
            aiData = new AIData();
        }
        return aiData;
    }

    public native int crc16JNI(byte[] bArr);

    public native float freeGps();

    public native AIRealDataBean getAIECGResult();

    public native AIDataBean getAIResult();

    public native ImageBean getBmpSize(byte[] bArr);

    public native ImageBean getCompressionBmpSize(byte[] bArr);

    public native HealthNormBean getHealthNormResult();

    public native float getHrv();

    public native float getRri();

    public native void initAIData();

    public native int initGps();

    public native int initHeart(int i2, boolean z);

    public native double[] makeGps(double d2, double d3);

    public native int makeValue(int i2);

    public native boolean modifyBinFile(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, int i2, int i3, byte b2, byte b3, byte b4);

    public native int parsonAIData(int i2);

    public native byte[] toBmp565(byte[] bArr, int i2, boolean z);

    public native byte[] toBmp565Thumb(byte[] bArr, int i2, boolean z);
}
