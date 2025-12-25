package com.yucheng.smarthealthpro.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import com.yucheng.smarthealthpro.BuildConfig;

/* loaded from: classes5.dex */
public class PackageUtils {
    public static String getVersionName(Context context) {
        return BuildConfig.VERSION_NAME;
    }

    public static int getVersionCode(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    public static String getAppName(Context context) {
        try {
            return context.getResources().getString(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.labelRes);
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
