package com.yucheng.smarthealthpro.utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.List;

/* loaded from: classes5.dex */
public class AppNetworkMgr {
    public static final String NETWORK_TYPE_2G = "2g";
    public static final String NETWORK_TYPE_3G = "eg";
    public static final String NETWORK_TYPE_DISCONNECT = "disconnect";
    public static final int NETWORK_TYPE_NO_CONNECTION = -1231545315;
    public static final String NETWORK_TYPE_UNKNOWN = "unknown";
    public static final String NETWORK_TYPE_WAP = "wap";
    public static final String NETWORK_TYPE_WIFI = "wifi";
    public static final int TYPE_MOBILE_3GWAP = 4;
    public static final int TYPE_MOBILE_CMNET = 1;
    public static final int TYPE_MOBILE_CMWAP = 2;
    public static final int TYPE_MOBILE_CTNET = 8;
    public static final int TYPE_MOBILE_CTWAP = 7;
    public static final int TYPE_MOBILE_UNINET = 6;
    public static final int TYPE_MOBILE_UNIWAP = 3;
    public static final int TYPE_MOBLIE_3GNET = 5;
    public static final int TYPE_NO = 0;
    public static final int TYPE_WIFI = 10;

    public static int getNetworkState(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
            int type = activeNetworkInfo.getType();
            if (type == 0) {
                if (activeNetworkInfo.getExtraInfo() != null) {
                    if (activeNetworkInfo.getExtraInfo().equals("cmnet")) {
                        AppLogMessageMgr.i("AppNetworkMgr", "当前网络为中国移动CMNET网络");
                        return 1;
                    }
                    if (activeNetworkInfo.getExtraInfo().equals("cmwap")) {
                        AppLogMessageMgr.i("AppNetworkMgr", "当前网络为中国移动CMWAP网络");
                        return 2;
                    }
                    if (activeNetworkInfo.getExtraInfo().equals("uniwap")) {
                        AppLogMessageMgr.i("AppNetworkMgr", "当前网络为中国联通UNIWAP网络");
                        return 3;
                    }
                    if (activeNetworkInfo.getExtraInfo().equals("3gwap")) {
                        AppLogMessageMgr.i("AppNetworkMgr", "当前网络为中国联通3GWAP网络");
                        return 4;
                    }
                    if (activeNetworkInfo.getExtraInfo().equals("3gnet")) {
                        AppLogMessageMgr.i("AppNetworkMgr", "当前网络为中国联通3GNET网络");
                        return 5;
                    }
                    if (activeNetworkInfo.getExtraInfo().equals("uninet")) {
                        AppLogMessageMgr.i("AppNetworkMgr", "当前网络为中国联通UNINET网络");
                        return 6;
                    }
                    if (activeNetworkInfo.getExtraInfo().equals("ctwap")) {
                        AppLogMessageMgr.i("AppNetworkMgr", "当前网络为中国电信CTWAP网络");
                        return 6;
                    }
                    if (activeNetworkInfo.getExtraInfo().equals("ctnet")) {
                        AppLogMessageMgr.i("AppNetworkMgr", "当前网络为中国电信CTNET网络");
                        return 6;
                    }
                }
            } else if (type == 1) {
                AppLogMessageMgr.i("AppNetworkMgr", "当前网络为WIFI网络");
                return 10;
            }
        }
        AppLogMessageMgr.i("AppNetworkMgr-->>NetworkUtils", "当前网络为不是我们考虑的网络");
        return 0;
    }

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager connectivityManager;
        NetworkInfo activeNetworkInfo;
        if (context != null && (activeNetworkInfo = (connectivityManager = (ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo()) != null) {
            if (activeNetworkInfo.getType() == 0) {
                AppLogMessageMgr.i("AppNetworkMgr", "网络连接类型为：TYPE_MOBILE");
                if (connectivityManager.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTED) {
                    AppLogMessageMgr.i("AppNetworkMgrd", "网络连接类型为：TYPE_MOBILE, 网络连接状态CONNECTED成功！");
                    return activeNetworkInfo.isAvailable();
                }
            }
            if (1 == activeNetworkInfo.getType()) {
                AppLogMessageMgr.i("AppNetworkMgr", "网络连接类型为：TYPE_WIFI");
                if (connectivityManager.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTED) {
                    AppLogMessageMgr.i("AppNetworkMgr", "网络连接类型为：TYPE_WIFI, 网络连接状态CONNECTED成功！");
                    return activeNetworkInfo.isAvailable();
                }
            }
        }
        return false;
    }

    public static boolean isNetworkConnected(Activity activity) {
        NetworkInfo[] allNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) activity.getApplicationContext().getSystemService("connectivity");
        if (connectivityManager == null || (allNetworkInfo = connectivityManager.getAllNetworkInfo()) == null) {
            return false;
        }
        for (NetworkInfo networkInfo : allNetworkInfo) {
            if (networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                return true;
            }
        }
        return false;
    }

    public static void openNetSetting(Activity activity) {
        Intent intent = new Intent("/");
        intent.setComponent(new ComponentName("com.android.settings", "com.android.settings.WirelessSettings"));
        intent.setAction("android.intent.action.VIEW");
        activity.startActivityForResult(intent, 0);
    }

    public static boolean is3gConnected(Context context) {
        NetworkInfo activeNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        return (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || activeNetworkInfo.getType() != 0) ? false : true;
    }

    public static int getNetworkType(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        NetworkInfo activeNetworkInfo = connectivityManager == null ? null : connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return -1;
        }
        return activeNetworkInfo.getType();
    }

    public static String getNetworkTypeName(Context context) {
        NetworkInfo activeNetworkInfo;
        String str;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || !activeNetworkInfo.isConnected()) {
            return "disconnect";
        }
        String typeName = activeNetworkInfo.getTypeName();
        if ("WIFI".equalsIgnoreCase(typeName)) {
            return NETWORK_TYPE_WIFI;
        }
        if ("MOBILE".equalsIgnoreCase(typeName)) {
            if (TextUtils.isEmpty(Proxy.getDefaultHost())) {
                if (isFastMobileNetwork(context)) {
                    str = NETWORK_TYPE_3G;
                } else {
                    str = NETWORK_TYPE_2G;
                }
            } else {
                str = NETWORK_TYPE_WAP;
            }
            return str;
        }
        return "unknown";
    }

    private static boolean isFastMobileNetwork(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager == null) {
            return false;
        }
        switch (telephonyManager.getNetworkType()) {
        }
        return false;
    }

    public static NetworkInfo.State getCurrentNetworkState(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null) {
            return activeNetworkInfo.getState();
        }
        return null;
    }

    public static int getCurrentNetworkType(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null ? activeNetworkInfo.getType() : NETWORK_TYPE_NO_CONNECTION;
    }

    public static int getCurrentNetworkSubtype(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null ? activeNetworkInfo.getSubtype() : NETWORK_TYPE_NO_CONNECTION;
    }

    public static boolean isConnectedByState(Context context) {
        return getCurrentNetworkState(context) == NetworkInfo.State.CONNECTED;
    }

    public static boolean isConnectingByState(Context context) {
        return getCurrentNetworkState(context) == NetworkInfo.State.CONNECTING;
    }

    public static boolean isDisconnectedByState(Context context) {
        return getCurrentNetworkState(context) == NetworkInfo.State.DISCONNECTED;
    }

    public static boolean isDisconnectingByState(Context context) {
        return getCurrentNetworkState(context) == NetworkInfo.State.DISCONNECTING;
    }

    public static boolean isSuspendedByState(Context context) {
        return getCurrentNetworkState(context) == NetworkInfo.State.SUSPENDED;
    }

    public static boolean isUnknownByState(Context context) {
        return getCurrentNetworkState(context) == NetworkInfo.State.UNKNOWN;
    }

    public static boolean isBluetoothByType(Context context) {
        return getCurrentNetworkType(context) == 7;
    }

    public static boolean isDummyByType(Context context) {
        return getCurrentNetworkType(context) == 8;
    }

    public static boolean isEthernetByType(Context context) {
        return getCurrentNetworkType(context) == 9;
    }

    public static boolean isMobileByType(Context context) {
        return getCurrentNetworkType(context) == 0;
    }

    public static boolean isMobileDunByType(Context context) {
        return getCurrentNetworkType(context) == 4;
    }

    public static boolean isMobileHipriByType(Context context) {
        return getCurrentNetworkType(context) == 5;
    }

    public static boolean isMobileMmsByType(Context context) {
        return getCurrentNetworkType(context) == 2;
    }

    public static boolean isMobileSuplByType(Context context) {
        return getCurrentNetworkType(context) == 3;
    }

    public static boolean isWifiByType(Context context) {
        return getCurrentNetworkType(context) == 1;
    }

    public static boolean isWimaxByType(Context context) {
        return getCurrentNetworkType(context) == 6;
    }

    public static boolean is1XRTTBySubtype(Context context) {
        return getCurrentNetworkSubtype(context) == 7;
    }

    public static boolean isCDMABySubtype(Context context) {
        return getCurrentNetworkSubtype(context) == 4;
    }

    public static boolean isEDGEBySubtype(Context context) {
        return getCurrentNetworkSubtype(context) == 2;
    }

    public static boolean isEHRPDBySubtype(Context context) {
        return getCurrentNetworkSubtype(context) == 14;
    }

    public static boolean isEVDO_0BySubtype(Context context) {
        return getCurrentNetworkSubtype(context) == 5;
    }

    public static boolean isEVDO_ABySubtype(Context context) {
        return getCurrentNetworkSubtype(context) == 6;
    }

    public static boolean isEVDO_BBySubtype(Context context) {
        return getCurrentNetworkSubtype(context) == 12;
    }

    public static boolean isGPRSBySubtype(Context context) {
        return getCurrentNetworkSubtype(context) == 1;
    }

    public static boolean isHSDPABySubtype(Context context) {
        return getCurrentNetworkSubtype(context) == 8;
    }

    public static boolean isHSPABySubtype(Context context) {
        return getCurrentNetworkSubtype(context) == 10;
    }

    public static boolean isHSPAPBySubtype(Context context) {
        return getCurrentNetworkSubtype(context) == 15;
    }

    public static boolean isHSUPABySubtype(Context context) {
        return getCurrentNetworkSubtype(context) == 9;
    }

    public static boolean isIDENBySubtype(Context context) {
        return getCurrentNetworkSubtype(context) == 11;
    }

    public static boolean isLTEBySubtype(Context context) {
        return getCurrentNetworkSubtype(context) == 13;
    }

    public static boolean isUMTSBySubtype(Context context) {
        return getCurrentNetworkSubtype(context) == 3;
    }

    public static boolean isUNKNOWNBySubtype(Context context) {
        return getCurrentNetworkSubtype(context) == 0;
    }

    public static boolean isChinaMobile2G(Context context) {
        return isEDGEBySubtype(context);
    }

    public static boolean isChinaUnicom2G(Context context) {
        return isGPRSBySubtype(context);
    }

    public static boolean isChinaUnicom3G(Context context) {
        return isHSDPABySubtype(context) || isUMTSBySubtype(context);
    }

    public static boolean isChinaTelecom2G(Context context) {
        return isCDMABySubtype(context);
    }

    public static boolean isChinaTelecom3G(Context context) {
        return isEVDO_0BySubtype(context) || isEVDO_ABySubtype(context) || isEVDO_BBySubtype(context);
    }

    public static int getWifiState(Context context) throws Exception {
        WifiManager wifiManager = (WifiManager) context.getSystemService(NETWORK_TYPE_WIFI);
        if (wifiManager != null) {
            return wifiManager.getWifiState();
        }
        throw new Exception("wifi device not found!");
    }

    public static boolean isWifiOpen(Context context) throws Exception {
        int wifiState = getWifiState(context);
        return wifiState == 3 || wifiState == 2;
    }

    public static boolean setWifi(Context context, boolean enable) throws Exception {
        if (isWifiOpen(context) == enable) {
            return true;
        }
        ((WifiManager) context.getSystemService(NETWORK_TYPE_WIFI)).setWifiEnabled(enable);
        return true;
    }

    public static boolean isMobileNetworkOpen(Context context) {
        return ((ConnectivityManager) context.getSystemService("connectivity")).getNetworkInfo(0).isConnected();
    }

    public static String getIpAddress() throws SocketException {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddressNextElement = inetAddresses.nextElement();
                    if (!inetAddressNextElement.isLoopbackAddress()) {
                        return inetAddressNextElement.getHostAddress().toString();
                    }
                }
            }
            return null;
        } catch (SocketException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static void setDataEnabled(Context context, boolean enabled) throws IllegalAccessException, NoSuchFieldException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        try {
            Field declaredField = Class.forName(connectivityManager.getClass().getName()).getDeclaredField("mService");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(connectivityManager);
            Method declaredMethod = Class.forName(obj.getClass().getName()).getDeclaredMethod("setMobileDataEnabled", Boolean.TYPE);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(obj, Boolean.valueOf(enabled));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static List<ScanResult> getWifiScanResults(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(NETWORK_TYPE_WIFI);
        if (wifiManager.startScan()) {
            return wifiManager.getScanResults();
        }
        return null;
    }

    public static ScanResult getScanResultsByBSSID(Context context, String bssid) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(NETWORK_TYPE_WIFI);
        if (!wifiManager.startScan()) {
            getScanResultsByBSSID(context, bssid);
        }
        List<ScanResult> scanResults = wifiManager.getScanResults();
        ScanResult scanResult = null;
        if (scanResults != null) {
            for (int i2 = 0; i2 < scanResults.size(); i2++) {
                scanResult = scanResults.get(i2);
                if (scanResult.BSSID.equals(bssid)) {
                    break;
                }
            }
        }
        return scanResult;
    }

    public static WifiInfo getWifiConnectionInfo(Context context) {
        return ((WifiManager) context.getSystemService(NETWORK_TYPE_WIFI)).getConnectionInfo();
    }

    public static String getProxy(Context context) {
        NetworkInfo activeNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager != null && (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) != null && activeNetworkInfo.isAvailable()) {
            String extraInfo = activeNetworkInfo.getExtraInfo();
            if (extraInfo != null && ("cmwap".equals(extraInfo) || "uniwap".equals(extraInfo))) {
                return "10.0.0.172:80";
            }
            if (extraInfo != null && "ctwap".equals(extraInfo)) {
                return "10.0.0.200:80";
            }
        }
        return null;
    }
}
