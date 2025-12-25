package com.yucheng.smarthealthpro.sport.utils;

import android.content.pm.PackageManager;
import com.google.android.gms.common.GoogleApiAvailability;
import com.yucheng.smarthealthpro.MyApplication;

/* loaded from: classes5.dex */
public class GoogleUtil {
    public static boolean checkGoogleAvailable() {
        return GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MyApplication.getInstance()) == 0 && isGoogleMapsInstalled() && MyApplication.getInstance().getResources().getConfiguration().mcc != 460;
    }

    public static boolean isGoogleMapsInstalled() throws PackageManager.NameNotFoundException {
        try {
            MyApplication.getInstance().getPackageManager().getApplicationInfo("com.google.android.apps.maps", 0);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }
}
