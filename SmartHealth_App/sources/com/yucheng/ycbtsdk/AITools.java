package com.yucheng.ycbtsdk;

import com.yucheng.ycbtsdk.bean.AIDataBean;
import com.yucheng.ycbtsdk.bean.AIRealDataBean;
import com.yucheng.ycbtsdk.bean.HealthNormBean;
import com.yucheng.ycbtsdk.bean.ImageBean;
import com.yucheng.ycbtsdk.core.AIData;
import com.yucheng.ycbtsdk.response.BleAIDiagnosisHRVNormResponse;
import com.yucheng.ycbtsdk.response.BleAIDiagnosisResponse;
import com.yucheng.ycbtsdk.utils.AIPraseDataUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class AITools {
    private static AITools aiTools;
    private int hrv;
    private int mode;
    private BleAIDiagnosisHRVNormResponse response;
    private List<Integer> hearts = new ArrayList();
    private List<Float> rris = new ArrayList();

    private AITools() {
        init();
    }

    public static synchronized AITools getInstance() {
        if (aiTools == null) {
            aiTools = new AITools();
        }
        return aiTools;
    }

    public List<Integer> aicheck() {
        return AIPraseDataUtil.aicheck();
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0054  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.List<java.lang.Integer> ecgRealWaveFiltering(byte[] r11) {
        /*
            r10 = this;
            int r0 = r11.length
            byte[] r1 = new byte[r0]
            int r2 = r11.length
            r3 = 0
            java.lang.System.arraycopy(r11, r3, r1, r3, r2)
            java.util.ArrayList r11 = new java.util.ArrayList
            r11.<init>()
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
        L12:
            int r4 = r3 + 2
            if (r4 >= r0) goto L9f
            r5 = r1[r3]
            r5 = r5 & 255(0xff, float:3.57E-43)
            int r6 = r3 + 1
            r6 = r1[r6]
            r6 = r6 & 255(0xff, float:3.57E-43)
            r4 = r1[r4]
            r4 = r4 & 255(0xff, float:3.57E-43)
            com.yucheng.ycbtsdk.utils.AIPraseDataUtil.praseData(r5, r6, r4, r11, r2)
            int r3 = r3 + 3
            float r4 = r10.getRri()
            float r5 = r10.getHrv()
            r6 = 0
            int r7 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r7 == 0) goto L96
            java.util.List<java.lang.Float> r7 = r10.rris
            int r7 = r7.size()
            if (r7 == 0) goto L54
            java.util.List<java.lang.Float> r7 = r10.rris
            int r8 = r7.size()
            int r8 = r8 + (-1)
            java.lang.Object r7 = r7.get(r8)
            java.lang.Float r7 = (java.lang.Float) r7
            float r7 = r7.floatValue()
            int r7 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
            if (r7 == 0) goto L96
        L54:
            java.util.List<java.lang.Integer> r7 = r10.hearts
            r8 = 1198153728(0x476a6000, float:60000.0)
            float r8 = r8 / r4
            int r8 = (int) r8
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)
            r7.add(r8)
            java.util.List<java.lang.Float> r7 = r10.rris
            java.lang.Float r8 = java.lang.Float.valueOf(r4)
            r7.add(r8)
            com.yucheng.ycbtsdk.response.BleAIDiagnosisHRVNormResponse r7 = r10.response
            r8 = 3
            if (r7 == 0) goto L87
            java.util.List<java.lang.Float> r7 = r10.rris
            int r7 = r7.size()
            if (r7 < r8) goto L87
            com.yucheng.ycbtsdk.response.BleAIDiagnosisHRVNormResponse r7 = r10.response
            java.util.List<java.lang.Float> r9 = r10.rris
            int r9 = r9.size()
            com.yucheng.ycbtsdk.bean.HealthNormBean r9 = com.yucheng.ycbtsdk.utils.AIPraseDataUtil.healthNormCallBack(r9)
            r7.onAIDiagnosisResponse(r9)
        L87:
            com.yucheng.ycbtsdk.core.YCBTClientImpl r7 = com.yucheng.ycbtsdk.core.YCBTClientImpl.getInstance()
            r7.jniCallback(r8, r4)
            com.yucheng.ycbtsdk.core.YCBTClientImpl r4 = com.yucheng.ycbtsdk.core.YCBTClientImpl.getInstance()
            r7 = 4
            r4.jniCallback(r7, r5)
        L96:
            int r4 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1))
            if (r4 == 0) goto L12
            int r4 = (int) r5
            r10.hrv = r4
            goto L12
        L9f:
            int r0 = r10.mode
            java.util.List r11 = com.yucheng.ycbtsdk.utils.AIPraseDataUtil.perECGData(r0, r11)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.ycbtsdk.AITools.ecgRealWaveFiltering(byte[]):java.util.List");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0054  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.HashMap<java.lang.String, java.util.List<java.lang.Integer>> ecgRealWaveFilteringMap(byte[] r11) {
        /*
            r10 = this;
            int r0 = r11.length
            byte[] r1 = new byte[r0]
            int r2 = r11.length
            r3 = 0
            java.lang.System.arraycopy(r11, r3, r1, r3, r2)
            java.util.ArrayList r11 = new java.util.ArrayList
            r11.<init>()
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
        L12:
            int r4 = r3 + 2
            if (r4 >= r0) goto L9f
            r5 = r1[r3]
            r5 = r5 & 255(0xff, float:3.57E-43)
            int r6 = r3 + 1
            r6 = r1[r6]
            r6 = r6 & 255(0xff, float:3.57E-43)
            r4 = r1[r4]
            r4 = r4 & 255(0xff, float:3.57E-43)
            com.yucheng.ycbtsdk.utils.AIPraseDataUtil.praseData(r5, r6, r4, r11, r2)
            int r3 = r3 + 3
            float r4 = r10.getRri()
            float r5 = r10.getHrv()
            r6 = 0
            int r7 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r7 == 0) goto L96
            java.util.List<java.lang.Float> r7 = r10.rris
            int r7 = r7.size()
            if (r7 == 0) goto L54
            java.util.List<java.lang.Float> r7 = r10.rris
            int r8 = r7.size()
            int r8 = r8 + (-1)
            java.lang.Object r7 = r7.get(r8)
            java.lang.Float r7 = (java.lang.Float) r7
            float r7 = r7.floatValue()
            int r7 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
            if (r7 == 0) goto L96
        L54:
            java.util.List<java.lang.Integer> r7 = r10.hearts
            r8 = 1198153728(0x476a6000, float:60000.0)
            float r8 = r8 / r4
            int r8 = (int) r8
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)
            r7.add(r8)
            java.util.List<java.lang.Float> r7 = r10.rris
            java.lang.Float r8 = java.lang.Float.valueOf(r4)
            r7.add(r8)
            com.yucheng.ycbtsdk.response.BleAIDiagnosisHRVNormResponse r7 = r10.response
            r8 = 3
            if (r7 == 0) goto L87
            java.util.List<java.lang.Float> r7 = r10.rris
            int r7 = r7.size()
            if (r7 < r8) goto L87
            com.yucheng.ycbtsdk.response.BleAIDiagnosisHRVNormResponse r7 = r10.response
            java.util.List<java.lang.Float> r9 = r10.rris
            int r9 = r9.size()
            com.yucheng.ycbtsdk.bean.HealthNormBean r9 = com.yucheng.ycbtsdk.utils.AIPraseDataUtil.healthNormCallBack(r9)
            r7.onAIDiagnosisResponse(r9)
        L87:
            com.yucheng.ycbtsdk.core.YCBTClientImpl r7 = com.yucheng.ycbtsdk.core.YCBTClientImpl.getInstance()
            r7.jniCallback(r8, r4)
            com.yucheng.ycbtsdk.core.YCBTClientImpl r4 = com.yucheng.ycbtsdk.core.YCBTClientImpl.getInstance()
            r7 = 4
            r4.jniCallback(r7, r5)
        L96:
            int r4 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1))
            if (r4 == 0) goto L12
            int r4 = (int) r5
            r10.hrv = r4
            goto L12
        L9f:
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            java.lang.String r1 = "originalData"
            r0.put(r1, r2)
            int r1 = r10.mode
            java.util.List r11 = com.yucheng.ycbtsdk.utils.AIPraseDataUtil.perECGData(r1, r11)
            java.lang.String r1 = "data"
            r0.put(r1, r11)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.ycbtsdk.AITools.ecgRealWaveFilteringMap(byte[]):java.util.HashMap");
    }

    public List<Integer> ecgRealWaveOriginalData(byte[] bArr) {
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        int i2 = 0;
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        while (true) {
            int i3 = i2 + 2;
            if (i3 >= length) {
                return arrayList2;
            }
            AIPraseDataUtil.praseData(bArr2[i2] & 255, bArr2[i2 + 1] & 255, bArr2[i3] & 255, arrayList, arrayList2);
            i2 += 3;
        }
    }

    public float freeGps() {
        return AIData.getInstance().freeGps();
    }

    public void getAIDiagnosisResult(BleAIDiagnosisResponse bleAIDiagnosisResponse) {
        AIPraseDataUtil.getAiResult(bleAIDiagnosisResponse);
    }

    public AIRealDataBean getAIECGResult() {
        return AIData.getInstance().getAIECGResult();
    }

    public AIDataBean getAIResult() {
        return AIData.getInstance().getAIResult();
    }

    public ImageBean getBmpSize(byte[] bArr) {
        return AIData.getInstance().getBmpSize(bArr);
    }

    public ImageBean getCompressionBmpSize(byte[] bArr) {
        return AIData.getInstance().getCompressionBmpSize(bArr);
    }

    public int getHRV() {
        return this.hrv;
    }

    public HealthNormBean getHealthNorm() {
        return AIPraseDataUtil.healthNormCallBack(this.rris.size());
    }

    public int getHeart() {
        if (this.hearts.size() == 0) {
            return 0;
        }
        List<Integer> list = this.hearts;
        return list.get(list.size() / 2).intValue();
    }

    public float getHrv() {
        return AIData.getInstance().getHrv();
    }

    public float getRri() {
        return AIData.getInstance().getRri();
    }

    public AITools init() {
        initHeart(250, false);
        initAIData();
        AIPraseDataUtil.init();
        this.hearts.clear();
        this.rris.clear();
        return aiTools;
    }

    public void initAIData() {
        AIData.getInstance().initAIData();
    }

    public int initGps() {
        return AIData.getInstance().initGps();
    }

    public int initHeart(int i2, boolean z) {
        return AIData.getInstance().initHeart(i2, z);
    }

    public double[] makeGps(double d2, double d3) {
        return AIData.getInstance().makeGps(d2, d3);
    }

    public int makeValue(int i2) {
        return AIData.getInstance().makeValue(i2);
    }

    public boolean modifyBinFile(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, int i2, int i3, byte b2, byte b3, byte b4) {
        return AIData.getInstance().modifyBinFile(bArr, bArr2, bArr3, bArr4, i2, i3, b2, b3, b4);
    }

    public int parsonAIData(int i2) {
        return AIData.getInstance().parsonAIData(i2);
    }

    public void setAIDiagnosisHRVNormResponse(BleAIDiagnosisHRVNormResponse bleAIDiagnosisHRVNormResponse) {
        this.response = bleAIDiagnosisHRVNormResponse;
    }

    public void setDangerDataSize() {
        AIPraseDataUtil.setDangerDataSize();
    }

    public AITools setInitialValue(int i2) {
        AIPraseDataUtil.setInitialValue(i2);
        return aiTools;
    }

    public AITools setMode(int i2) {
        this.mode = i2;
        return aiTools;
    }

    public byte[] toBmp565(byte[] bArr, int i2, boolean z) {
        return AIData.getInstance().toBmp565(bArr, i2, z);
    }

    public byte[] toBmp565Thumb(byte[] bArr, int i2, boolean z) {
        return AIData.getInstance().toBmp565Thumb(bArr, i2, z);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0044  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void ecgRealWaveFiltering(java.util.List<java.lang.Integer> r9) {
        /*
            r8 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
        L6:
            int r2 = r9.size()
            if (r1 >= r2) goto L89
            java.lang.Object r2 = r9.get(r1)
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r2 = r2.intValue()
            com.yucheng.ycbtsdk.utils.AIPraseDataUtil.parseData(r2, r0)
            float r2 = r8.getRri()
            float r3 = r8.getHrv()
            r4 = 0
            int r5 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r5 == 0) goto L7e
            java.util.List<java.lang.Float> r5 = r8.rris
            int r5 = r5.size()
            if (r5 == 0) goto L44
            java.util.List<java.lang.Float> r5 = r8.rris
            int r6 = r5.size()
            int r6 = r6 + (-1)
            java.lang.Object r5 = r5.get(r6)
            java.lang.Float r5 = (java.lang.Float) r5
            float r5 = r5.floatValue()
            int r5 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1))
            if (r5 == 0) goto L7e
        L44:
            java.util.List<java.lang.Integer> r5 = r8.hearts
            r6 = 1198153728(0x476a6000, float:60000.0)
            float r6 = r6 / r2
            int r6 = (int) r6
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            r5.add(r6)
            java.util.List<java.lang.Float> r5 = r8.rris
            java.lang.Float r6 = java.lang.Float.valueOf(r2)
            r5.add(r6)
            com.yucheng.ycbtsdk.response.BleAIDiagnosisHRVNormResponse r5 = r8.response
            r6 = 3
            if (r5 == 0) goto L77
            java.util.List<java.lang.Float> r5 = r8.rris
            int r5 = r5.size()
            if (r5 < r6) goto L77
            com.yucheng.ycbtsdk.response.BleAIDiagnosisHRVNormResponse r5 = r8.response
            java.util.List<java.lang.Float> r7 = r8.rris
            int r7 = r7.size()
            com.yucheng.ycbtsdk.bean.HealthNormBean r7 = com.yucheng.ycbtsdk.utils.AIPraseDataUtil.healthNormCallBack(r7)
            r5.onAIDiagnosisResponse(r7)
        L77:
            com.yucheng.ycbtsdk.core.YCBTClientImpl r5 = com.yucheng.ycbtsdk.core.YCBTClientImpl.getInstance()
            r5.jniCallback(r6, r2)
        L7e:
            int r2 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            if (r2 == 0) goto L85
            int r2 = (int) r3
            r8.hrv = r2
        L85:
            int r1 = r1 + 1
            goto L6
        L89:
            int r9 = r8.mode
            com.yucheng.ycbtsdk.utils.AIPraseDataUtil.perECGData(r9, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.ycbtsdk.AITools.ecgRealWaveFiltering(java.util.List):void");
    }
}
