package com.yucheng.smarthealthpro.home.util;

import android.content.Context;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.utils.Constant;

/* loaded from: classes5.dex */
public class DataTools {
    public static int[] getUnit(Context context) {
        String str = (String) SharedPreferencesUtils.get(context, Constant.SpConstKey.UNIT, "");
        int i2 = (str == null || !str.equals(Constant.SpConstValue.INCH)) ? 0 : 1;
        int i3 = i2;
        String str2 = (String) SharedPreferencesUtils.get(context, Constant.SpConstKey.TEMP_UNIT, "");
        int i4 = (str2 == null || !str2.equals(Constant.SpConstValue.TEMP_INCH)) ? 0 : 1;
        String str3 = (String) SharedPreferencesUtils.get(context, Constant.SpConstKey.BLOOD_SUGAR_AND_BLOOD_FAT_UNIT, "");
        int i5 = (str3 == null || !str3.equals("mg/dL")) ? 0 : 1;
        String str4 = (String) SharedPreferencesUtils.get(context, Constant.SpConstKey.URIC_ACID_UNIT, "");
        return new int[]{i2, i3, i4, i5, (str4 == null || !str4.equals("mg/dL")) ? 0 : 1};
    }
}
