package com.yucheng.smarthealthpro.utils;

import android.content.Context;
import android.os.Build;
import com.facebook.internal.ServerProtocol;
import com.yucheng.smarthealthpro.framework.http.HttpUtils;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.me.bean.UpgradeBean;
import com.yucheng.ycbtsdk.Constants;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class UpgradleUtil {
    public static void upgradeUpload(final UpgradeBean bean, final Context context) {
        HashMap map = new HashMap();
        map.put("mac", bean.data.mac);
        map.put(ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION, bean.data.version);
        map.put("upStatus", bean.data.upStatus + "");
        map.put(Constants.FunctionConstant.DEVICETYPE, "2--" + getAppInfo(context));
        if (bean.data.deviceName != null) {
            map.put("deviceName", bean.data.deviceName);
        }
        HttpUtils.getInstance().postMsgAsynHttp(context, com.yucheng.smarthealthpro.framework.util.Constants.UPMAC, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.utils.UpgradleUtil.1
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                if (result == null) {
                    return;
                }
                try {
                    SharedPreferencesUtils.put(context, "isUpLoadUpgrade", Integer.valueOf(bean.data.upStatus));
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
    }

    private static String getAppInfo(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName + "--" + Build.MODEL + "--" + Build.VERSION.RELEASE;
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }
}
