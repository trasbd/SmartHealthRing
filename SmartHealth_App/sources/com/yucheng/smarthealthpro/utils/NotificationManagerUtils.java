package com.yucheng.smarthealthpro.utils;

import android.content.Context;
import com.yucheng.smarthealthpro.MyApplication;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.utils.Constant;

/* loaded from: classes5.dex */
public class NotificationManagerUtils {
    public static boolean getIsSwitch(Context context) {
        return Constant.SpConstValue.OPEN.equals((String) SharedPreferencesUtils.get(context, Constant.SpConstKey.PUSH_MESSAGE, Constant.SpConstValue.OPEN));
    }

    public static char[] getAllSwitchState(Context context) {
        String str = (String) SharedPreferencesUtils.get(context, Constant.SpConstKey.PUSHMESSAGE, "");
        if (str != null && !str.isEmpty() && str.length() >= 21) {
            return str.toCharArray();
        }
        char[] cArr = {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'};
        SharedPreferencesUtils.put(MyApplication.getInstance(), Constant.SpConstKey.PUSHMESSAGE, "111111111111111111111");
        return cArr;
    }
}
