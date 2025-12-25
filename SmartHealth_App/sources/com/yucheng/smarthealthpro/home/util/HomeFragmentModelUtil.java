package com.yucheng.smarthealthpro.home.util;

import android.content.Context;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.ycbtsdk.Constants;
import com.yucheng.ycbtsdk.YCBTClient;
import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: classes5.dex */
public class HomeFragmentModelUtil {
    public static void updateHomeFunction(Context context) {
        int iIntValue = ((Integer) SharedPreferencesUtils.get(context, Constant.SpConstKey.M_HOME_FUNCTION_BEAN_SIZE, 0)).intValue();
        int iIntValue2 = ((Integer) SharedPreferencesUtils.get(context, Constant.SpConstKey.M_HOME_ADD_FUNCTION_BEAN_SIZE, 0)).intValue();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList(Arrays.asList("心电", "睡眠", "心率", "血压", "血糖", "血氧", "呼吸率", "温度", "运动", "血脂", "尿酸", "血酮", "理疗", "HRV", "压力"));
        for (int i2 = 0; i2 < iIntValue; i2++) {
            String str = (String) SharedPreferencesUtils.get(context, Constant.SpConstKey.FUNCTION + i2, "");
            if (checkedFunction(str)) {
                arrayList.add(str);
            }
            SharedPreferencesUtils.remove(context, Constant.SpConstKey.FUNCTION + i2);
        }
        for (int i3 = 0; i3 < iIntValue2; i3++) {
            String str2 = (String) SharedPreferencesUtils.get(context, Constant.SpConstKey.HIDE_FUNCTION + i3, "");
            if (checkedFunction(str2)) {
                arrayList2.add(str2);
            }
            SharedPreferencesUtils.remove(context, Constant.SpConstKey.HIDE_FUNCTION + i3);
        }
        arrayList3.removeAll(arrayList);
        arrayList3.removeAll(arrayList2);
        for (int i4 = 0; i4 < arrayList3.size(); i4++) {
            if (checkedFunction((String) arrayList3.get(i4))) {
                arrayList.add((String) arrayList3.get(i4));
            }
        }
        for (int i5 = 0; i5 < arrayList.size(); i5++) {
            SharedPreferencesUtils.put(context, Constant.SpConstKey.FUNCTION + i5, arrayList.get(i5));
        }
        for (int i6 = 0; i6 < arrayList2.size(); i6++) {
            SharedPreferencesUtils.put(context, Constant.SpConstKey.HIDE_FUNCTION + i6, arrayList2.get(i6));
        }
        SharedPreferencesUtils.put(context, Constant.SpConstKey.M_HOME_FUNCTION_BEAN_SIZE, Integer.valueOf(arrayList.size()));
        SharedPreferencesUtils.put(context, Constant.SpConstKey.M_HOME_ADD_FUNCTION_BEAN_SIZE, Integer.valueOf(arrayList2.size()));
    }

    public static boolean checkedFunction(String function) {
        function.hashCode();
        switch (function) {
            case "HRV":
                return YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASHRV);
            case "压力":
                return YCBTClient.isSupportFunction(Constants.FunctionConstant.IS_HAS_PRESSURE);
            case "尿酸":
                return YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASURICACIDMEASUREMENT) || YCBTClient.isSupportFunction(Constants.FunctionConstant.IS_HAS_PRECISION_URIC_ACID);
            case "心率":
                return YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASHEARTRATE);
            case "心电":
                return YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASECGREALUPLOAD);
            case "温度":
                return YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASTEMP);
            case "理疗":
                return YCBTClient.isSupportFunction(Constants.FunctionConstant.IS_HAS_PHYSIOTHERAPY);
            case "睡眠":
                return YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASSLEEP);
            case "血压":
                return YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASBLOOD);
            case "血氧":
                return YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASBLOODOXYGEN);
            case "血糖":
                return YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASBLOODSUGAR) || YCBTClient.isSupportFunction(Constants.FunctionConstant.IS_HAS_PRECISION_BLOOD_GLUCOSE);
            case "血脂":
                return YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASIMPRECISEBLOODFAT) || YCBTClient.isSupportFunction(Constants.FunctionConstant.IS_HAS_PRECISION_LIPIDS);
            case "血酮":
                return YCBTClient.isSupportFunction(Constants.FunctionConstant.IS_HAS_PRECISION_BLOOD_KETONE);
            case "运动":
                return Constant.isMymon();
            case "呼吸率":
                return YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASRESPIRATORYRATE);
            default:
                return false;
        }
    }
}
