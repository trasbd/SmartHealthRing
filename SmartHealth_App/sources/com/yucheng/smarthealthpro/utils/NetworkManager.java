package com.yucheng.smarthealthpro.utils;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import com.facebook.internal.ServerProtocol;

/* loaded from: classes5.dex */
public class NetworkManager {
    private static NetworkManager networkManager;
    private ConnectivityManager connManager;
    private Context context;

    public static synchronized NetworkManager getInstance(Context context) {
        if (networkManager == null) {
            networkManager = new NetworkManager(context);
        }
        return networkManager;
    }

    private NetworkManager(Context context) {
        this.context = context;
        this.connManager = (ConnectivityManager) context.getSystemService("connectivity");
    }

    public boolean isNetworkConnected() {
        NetworkInfo activeNetworkInfo = this.connManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null) {
            return activeNetworkInfo.isConnected();
        }
        return false;
    }

    public boolean isWifiConnected() {
        NetworkInfo networkInfo = this.connManager.getNetworkInfo(1);
        if (networkInfo != null) {
            return networkInfo.isConnected();
        }
        return false;
    }

    public boolean isMobileConnected() {
        NetworkInfo networkInfo = this.connManager.getNetworkInfo(0);
        if (networkInfo != null) {
            return networkInfo.isConnected();
        }
        return false;
    }

    public void toggleGprs(boolean isEnable) throws Exception {
        this.connManager.getClass().getMethod("setMobileDataEnabled", Boolean.TYPE).invoke(this.connManager, Boolean.valueOf(isEnable));
    }

    public boolean toggleWiFi(boolean enabled) {
        return ((WifiManager) this.context.getSystemService(AppNetworkMgr.NETWORK_TYPE_WIFI)).setWifiEnabled(enabled);
    }

    public boolean isAirplaneModeOn() {
        return Settings.System.getInt(this.context.getContentResolver(), "airplane_mode_on", 0) == 1;
    }

    public void toggleAirplaneMode(boolean z) {
        Settings.System.putInt(this.context.getContentResolver(), "airplane_mode_on", z ? 1 : 0);
        Intent intent = new Intent("android.intent.action.AIRPLANE_MODE");
        intent.putExtra(ServerProtocol.DIALOG_PARAM_STATE, z);
        this.context.sendBroadcast(intent);
    }

    public String getInternetState() {
        try {
            if (isNetworkConnected()) {
                if (isWifiConnected()) {
                    return "WIFI";
                }
                if (isMobileConnected()) {
                    return "4/5G";
                }
                return "No network";
            }
            return "No network";
        } catch (SecurityException e2) {
            e2.printStackTrace();
            return "No network";
        }
    }
}
