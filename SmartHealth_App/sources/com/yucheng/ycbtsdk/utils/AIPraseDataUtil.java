package com.yucheng.ycbtsdk.utils;

import android.util.Base64;
import androidx.core.view.ViewCompat;
import com.google.gson.Gson;
import com.yucheng.ycbtsdk.AITools;
import com.yucheng.ycbtsdk.Constants;
import com.yucheng.ycbtsdk.YCBTClient;
import com.yucheng.ycbtsdk.bean.AIDataBean;
import com.yucheng.ycbtsdk.bean.AIResult;
import com.yucheng.ycbtsdk.bean.ECGRealDataBean;
import com.yucheng.ycbtsdk.bean.HealthNormBean;
import com.yucheng.ycbtsdk.core.AIData;
import com.yucheng.ycbtsdk.response.BleAIDiagnosisResponse;
import com.yucheng.ycbtsdk.utils.HttpUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class AIPraseDataUtil {
    private static int currECGDataIndex;
    private static int dangerDataSize;
    private static int max_val;
    private static int min_val;
    private static float pre_predata;
    private static float predata;
    private static List<Integer> vpp_array = new ArrayList();
    private static List<Integer> blist = new ArrayList();
    private static int ECG_MAX_VALUE = 55000;
    private static int initialValue = 250;
    private static List<Integer> nativeList = new ArrayList();

    public static List<Integer> aicheck() {
        float f2;
        ArrayList arrayList = new ArrayList();
        Collections.sort(vpp_array);
        float f3 = 1.0f;
        if (vpp_array.size() > 15) {
            int iIntValue = 0;
            int i2 = 0;
            for (int i3 = 5; i3 < vpp_array.size() - 10; i3++) {
                iIntValue += vpp_array.get(i3).intValue();
                i2++;
            }
            f2 = (iIntValue * 1.0f) / i2;
        } else {
            f2 = 0.0f;
        }
        int i4 = ECG_MAX_VALUE / 3;
        if (f2 != 0.0f) {
            float f4 = i4;
            if (f2 < f4) {
                f3 = f4 / f2;
            }
        }
        int size = blist.size();
        for (int i5 = 0; i5 < size; i5++) {
            int iIntValue2 = (int) (blist.get(i5).intValue() * f3);
            int i6 = ECG_MAX_VALUE;
            if (iIntValue2 > i6 || iIntValue2 < (i6 = -i6)) {
                iIntValue2 = i6;
            }
            arrayList.add(Integer.valueOf(iIntValue2));
        }
        return arrayList;
    }

    private static float calc_HRV_norm(float f2) {
        float f3;
        float f4;
        if (f2 < 10.0f) {
            f2 = 10.0f;
        }
        if (f2 >= 10.0f && f2 < 25.0f) {
            f3 = f2 * (-0.06f);
            f4 = 10.6f;
        } else if (f2 >= 25.0f && f2 < 40.0f) {
            f3 = f2 * (-0.0333f);
            f4 = 9.9333f;
        } else if (f2 >= 40.0f && f2 < 60.0f) {
            f3 = f2 * (-0.045f);
            f4 = 10.4f;
        } else if (f2 >= 60.0f && f2 < 75.0f) {
            f3 = f2 * (-0.1267f);
            f4 = 15.3f;
        } else if (f2 >= 75.0f && f2 < 90.0f) {
            f3 = f2 * (-0.12f);
            f4 = 14.8f;
        } else if (f2 >= 90.0f && f2 < 130.0f) {
            f3 = f2 * (-0.05f);
            f4 = 8.5f;
        } else {
            if (f2 < 130.0f || f2 >= 150.0f) {
                return 0.0f;
            }
            f3 = f2 * (-0.055f);
            f4 = 9.15f;
        }
        return f3 + f4;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0049 A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static float calc_body(float r4) {
        /*
            r0 = 1092616192(0x41200000, float:10.0)
            int r1 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            if (r1 >= 0) goto L7
            r4 = r0
        L7:
            int r0 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            r1 = 1115160576(0x42780000, float:62.0)
            if (r0 < 0) goto L1a
            int r0 = (r4 > r1 ? 1 : (r4 == r1 ? 0 : -1))
            if (r0 >= 0) goto L1a
            r0 = -1116973354(0xffffffffbd6c56d6, float:-0.0577)
            float r4 = r4 * r0
            r0 = 1092487112(0x411e07c8, float:9.8769)
        L18:
            float r4 = r4 + r0
            goto L3f
        L1a:
            int r0 = (r4 > r1 ? 1 : (r4 == r1 ? 0 : -1))
            r1 = 1119092736(0x42b40000, float:90.0)
            if (r0 < 0) goto L2c
            int r0 = (r4 > r1 ? 1 : (r4 == r1 ? 0 : -1))
            if (r0 >= 0) goto L2c
            r0 = -1105376942(0xffffffffbe1d4952, float:-0.1536)
            float r4 = r4 * r0
            r0 = 1098720372(0x417d2474, float:15.8214)
            goto L18
        L2c:
            int r0 = (r4 > r1 ? 1 : (r4 == r1 ? 0 : -1))
            if (r0 < 0) goto L3e
            r0 = 1123024896(0x42f00000, float:120.0)
            int r0 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            if (r0 >= 0) goto L3e
            r0 = -1143239764(0xffffffffbbdb8bac, float:-0.0067)
            float r4 = r4 * r0
            r0 = 1076258406(0x40266666, float:2.6)
            goto L18
        L3e:
            r4 = 0
        L3f:
            double r0 = (double) r4
            r2 = 4610334938539176755(0x3ffb333333333333, double:1.7)
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 >= 0) goto L4c
            r4 = 1071225242(0x3fd9999a, float:1.7)
        L4c:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.ycbtsdk.utils.AIPraseDataUtil.calc_body(float):float");
    }

    /* JADX WARN: Removed duplicated region for block: B:62:0x00be A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:64:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static float calc_heavy_load(float r4) {
        /*
            r0 = 1109393408(0x42200000, float:40.0)
            int r1 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            if (r1 >= 0) goto L7
            r4 = r0
        L7:
            int r0 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            r1 = 1036831949(0x3dcccccd, float:0.1)
            r2 = 1112014848(0x42480000, float:50.0)
            if (r0 < 0) goto L1a
            int r0 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r0 >= 0) goto L1a
            float r4 = r4 * r1
            r0 = 1077936128(0x40400000, float:3.0)
        L17:
            float r4 = r4 - r0
            goto Lb4
        L1a:
            int r0 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            r2 = 1114636288(0x42700000, float:60.0)
            if (r0 < 0) goto L2b
            int r0 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r0 >= 0) goto L2b
            r0 = 1045220557(0x3e4ccccd, float:0.2)
            float r4 = r4 * r0
            r0 = 1090519040(0x41000000, float:8.0)
            goto L17
        L2b:
            int r0 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            r2 = 1028443341(0x3d4ccccd, float:0.05)
            r3 = 1116471296(0x428c0000, float:70.0)
            if (r0 < 0) goto L3c
            int r0 = (r4 > r3 ? 1 : (r4 == r3 ? 0 : -1))
            if (r0 >= 0) goto L3c
            float r4 = r4 * r2
            r0 = 1065353216(0x3f800000, float:1.0)
            goto L17
        L3c:
            int r0 = (r4 > r3 ? 1 : (r4 == r3 ? 0 : -1))
            r3 = 1117782016(0x42a00000, float:80.0)
            if (r0 < 0) goto L4e
            int r0 = (r4 > r3 ? 1 : (r4 == r3 ? 0 : -1))
            if (r0 >= 0) goto L4e
            r0 = 1034147594(0x3da3d70a, float:0.08)
            float r4 = r4 * r0
            r0 = 1066192077(0x3f8ccccd, float:1.1)
            goto L17
        L4e:
            int r0 = (r4 > r3 ? 1 : (r4 == r3 ? 0 : -1))
            r3 = 1119092736(0x42b40000, float:90.0)
            if (r0 < 0) goto L59
            int r0 = (r4 > r3 ? 1 : (r4 == r3 ? 0 : -1))
            if (r0 >= 0) goto L59
            goto L63
        L59:
            int r0 = (r4 > r3 ? 1 : (r4 == r3 ? 0 : -1))
            r3 = 1120403456(0x42c80000, float:100.0)
            if (r0 < 0) goto L68
            int r0 = (r4 > r3 ? 1 : (r4 == r3 ? 0 : -1))
            if (r0 >= 0) goto L68
        L63:
            float r4 = r4 * r1
            r0 = 1076677837(0x402ccccd, float:2.7)
            goto L17
        L68:
            int r0 = (r4 > r3 ? 1 : (r4 == r3 ? 0 : -1))
            r1 = 1121714176(0x42dc0000, float:110.0)
            if (r0 < 0) goto L7a
            int r0 = (r4 > r1 ? 1 : (r4 == r1 ? 0 : -1))
            if (r0 >= 0) goto L7a
            r0 = 1032805417(0x3d8f5c29, float:0.07)
            float r4 = r4 * r0
            r0 = 1050253722(0x3e99999a, float:0.3)
            goto L17
        L7a:
            int r0 = (r4 > r1 ? 1 : (r4 == r1 ? 0 : -1))
            r1 = 1123024896(0x42f00000, float:120.0)
            if (r0 < 0) goto L85
            int r0 = (r4 > r1 ? 1 : (r4 == r1 ? 0 : -1))
            if (r0 >= 0) goto L85
            goto L8f
        L85:
            int r0 = (r4 > r1 ? 1 : (r4 == r1 ? 0 : -1))
            r1 = 1124204544(0x43020000, float:130.0)
            if (r0 < 0) goto L93
            int r0 = (r4 > r1 ? 1 : (r4 == r1 ? 0 : -1))
            if (r0 >= 0) goto L93
        L8f:
            float r4 = r4 * r2
            r0 = 1075838976(0x40200000, float:2.5)
            goto L17
        L93:
            int r0 = (r4 > r1 ? 1 : (r4 == r1 ? 0 : -1))
            r1 = 1124859904(0x430c0000, float:140.0)
            if (r0 < 0) goto La6
            int r0 = (r4 > r1 ? 1 : (r4 == r1 ? 0 : -1))
            if (r0 >= 0) goto La6
            r0 = 1022739087(0x3cf5c28f, float:0.03)
            float r4 = r4 * r0
            r0 = 1084437299(0x40a33333, float:5.1)
            goto L17
        La6:
            int r0 = (r4 > r1 ? 1 : (r4 == r1 ? 0 : -1))
            if (r0 < 0) goto Lb3
            r0 = 1031127695(0x3d75c28f, float:0.06)
            float r4 = r4 * r0
            r0 = 1063675494(0x3f666666, float:0.9)
            goto L17
        Lb3:
            r4 = 0
        Lb4:
            double r0 = (double) r4
            r2 = 4621706527598287258(0x402399999999999a, double:9.8)
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 <= 0) goto Lc1
            r4 = 1092406477(0x411ccccd, float:9.8)
        Lc1:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.ycbtsdk.utils.AIPraseDataUtil.calc_heavy_load(float):float");
    }

    public static void getAiResult(BleAIDiagnosisResponse bleAIDiagnosisResponse) {
        int i2;
        ArrayList arrayList = new ArrayList();
        ECGRealDataBean eCGRealDataBean = new ECGRealDataBean();
        eCGRealDataBean.bangleMac = SPUtil.getBindedDeviceMac();
        eCGRealDataBean.bangleName = SPUtil.getBindedDeviceName();
        eCGRealDataBean.time = System.currentTimeMillis();
        eCGRealDataBean.userId = "";
        eCGRealDataBean.ecgOriginal = new Gson().toJson(nativeList);
        if (nativeList.size() < 2800) {
            if (bleAIDiagnosisResponse != null) {
                bleAIDiagnosisResponse.onAIDiagnosisResponse(null);
                return;
            }
            return;
        }
        Iterator<Integer> it2 = nativeList.iterator();
        while (it2.hasNext()) {
            AITools.getInstance().parsonAIData(it2.next().intValue());
        }
        try {
            AIDataBean aIResult = AITools.getInstance().getAIResult();
            if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASINACCURATEECG) && (i2 = aIResult.qrstype) != 1 && i2 != 5 && i2 != 9 && !aIResult.is_atrial_fibrillation) {
                aIResult.qrstype = 1;
            }
            YCBTLog.d("chong-------airesult==" + AITools.getInstance().getAIResult() + "--" + AITools.getInstance().getAIResult().qrstype);
            eCGRealDataBean.aiData = Base64.encodeToString(new Gson().toJson(aIResult).getBytes(), 0).trim();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        arrayList.add(eCGRealDataBean);
        uploadNativeLocalService(new Gson().toJson(arrayList), bleAIDiagnosisResponse);
        AITools.getInstance().initAIData();
        nativeList.clear();
    }

    public static HealthNormBean healthNormCallBack(int i2) {
        int i3;
        HealthNormBean healthNormResult = AIData.getInstance().getHealthNormResult();
        if (healthNormResult == null) {
            healthNormResult = new HealthNormBean();
        }
        healthNormResult.flag = -1;
        if (i2 >= 3) {
            float f2 = healthNormResult.heavyLoad;
            if (f2 >= 0.0f && f2 <= 10.0d) {
                double d2 = healthNormResult.pressure;
                if (d2 >= 0.0d && d2 <= 10.0d) {
                    double d3 = healthNormResult.hrvNorm;
                    if (d3 >= 0.0d && d3 <= 10.0d) {
                        double d4 = healthNormResult.body;
                        if (d4 >= 0.0d && d4 <= 10.0d) {
                            double d5 = healthNormResult.sympatheticParasympathetic;
                            if (d5 >= -10.0d && d5 <= 10.0d && (i3 = healthNormResult.respiratoryRate) >= 6 && i3 <= 50) {
                                healthNormResult.flag = 0;
                            }
                        }
                    }
                }
            }
        }
        return healthNormResult;
    }

    public static void init() {
        int i2 = ECG_MAX_VALUE;
        max_val = -i2;
        min_val = i2;
        currECGDataIndex = 0;
        predata = 0.0f;
        pre_predata = 0.0f;
        dangerDataSize = 0;
        vpp_array.clear();
        blist.clear();
        nativeList.clear();
    }

    public static void parseData(int i2, List<Integer> list) {
        nativeList.add(Integer.valueOf(i2));
        int iMakeValue = AITools.getInstance().makeValue(i2);
        int i3 = ECG_MAX_VALUE;
        if (iMakeValue > i3) {
            iMakeValue = i3;
        }
        int i4 = -i3;
        if (iMakeValue < i4) {
            iMakeValue = i4;
        }
        list.add(Integer.valueOf(iMakeValue));
    }

    public static List<Integer> perECGData(int i2, List<Integer> list) {
        ArrayList arrayList = new ArrayList();
        if (i2 == 1) {
            ECG_MAX_VALUE = 400;
        }
        int size = list.size();
        for (int i3 = 0; i3 < size; i3++) {
            int iIntValue = list.get(i3).intValue();
            if (i2 == 1) {
                if (currECGDataIndex % 3 == 0) {
                    iIntValue = (int) ((((iIntValue + pre_predata) + predata) / 3.0f) * 0.007d);
                    saveEcgVal(iIntValue, arrayList);
                }
                pre_predata = predata;
                predata = iIntValue;
            } else {
                saveEcgVal(iIntValue, arrayList);
            }
            int i4 = currECGDataIndex + 1;
            currECGDataIndex = i4;
            if (i4 % 400 == 0) {
                int i5 = max_val - min_val;
                if (i5 > 0) {
                    vpp_array.add(Integer.valueOf(i5));
                }
                int i6 = ECG_MAX_VALUE;
                min_val = i6;
                max_val = -i6;
            }
        }
        return arrayList;
    }

    public static void praseData(int i2, int i3, int i4, List<Integer> list, List<Integer> list2) {
        int i5 = (i2 & 255) + (i3 << 8) + (i4 << 16);
        if ((i4 & 128) != 0) {
            i5 |= ViewCompat.MEASURED_STATE_MASK;
        }
        nativeList.add(Integer.valueOf(i5));
        list2.add(Integer.valueOf(i5));
        list.add(Integer.valueOf(AITools.getInstance().makeValue(i5)));
    }

    private static void saveEcgVal(int i2, List<Integer> list) {
        int i3 = ECG_MAX_VALUE;
        if (i2 > i3) {
            i2 = i3;
        }
        int i4 = -i3;
        if (i2 < i4) {
            i2 = i4;
        }
        if (blist.size() < initialValue + dangerDataSize) {
            blist.add(0);
            list.add(0);
            return;
        }
        blist.add(Integer.valueOf(i2));
        list.add(Integer.valueOf(i2));
        if (i2 > max_val) {
            max_val = i2;
        } else if (i2 < min_val) {
            min_val = i2;
        }
    }

    public static void setDangerDataSize() {
        dangerDataSize = blist.size();
    }

    public static void setInitialValue(int i2) {
        initialValue = i2;
    }

    public static void uploadNativeLocalService(String str, final BleAIDiagnosisResponse bleAIDiagnosisResponse) {
        HttpUtils.getInstance().postJsonMsgAsynHttp("https://web-api.ycaviation.com/smartam/upheartEcgOriginalData", str, new HttpUtils.HttpCallback() { // from class: com.yucheng.ycbtsdk.utils.AIPraseDataUtil.1
            @Override // com.yucheng.ycbtsdk.utils.HttpUtils.HttpCallback
            public void onSuccess(String str2) {
                List<AIResult.Data> list;
                BleAIDiagnosisResponse bleAIDiagnosisResponse2;
                YCBTLog.d("uploadNativeLocalService----result==" + str2);
                try {
                    if (str2 != null) {
                        AIResult aIResult = (AIResult) new Gson().fromJson(str2, AIResult.class);
                        if (aIResult.code == 0 && (list = aIResult.data) != null && list.size() > 0 && (bleAIDiagnosisResponse2 = bleAIDiagnosisResponse) != null) {
                            bleAIDiagnosisResponse2.onAIDiagnosisResponse((AIDataBean) new Gson().fromJson(new String(Base64.decode(list.get(0).aiData.getBytes(), 0)), AIDataBean.class));
                        }
                    } else {
                        BleAIDiagnosisResponse bleAIDiagnosisResponse3 = bleAIDiagnosisResponse;
                        if (bleAIDiagnosisResponse3 != null) {
                            bleAIDiagnosisResponse3.onAIDiagnosisResponse(AITools.getInstance().getAIResult());
                        }
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                    BleAIDiagnosisResponse bleAIDiagnosisResponse4 = bleAIDiagnosisResponse;
                    if (bleAIDiagnosisResponse4 != null) {
                        bleAIDiagnosisResponse4.onAIDiagnosisResponse(AITools.getInstance().getAIResult());
                    }
                }
            }
        });
    }
}
