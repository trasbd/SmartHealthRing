package com.yucheng.smarthealthpro.framework.util;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import androidx.core.content.ContextCompat;
import com.yanzhenjie.permission.Permission;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Formatter;
import java.util.Random;

/* loaded from: classes4.dex */
public class UUIDUtils {
    public static String get32DataId() {
        return get32DataId(System.currentTimeMillis());
    }

    public static String get32DataId(long j2) {
        String str = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date(j2));
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        while (sb.length() < 32) {
            sb.append((int) (Math.random() * Math.pow(10.0d, 8.0d)));
        }
        if (sb.length() > 32) {
            return sb.substring(0, 32);
        }
        return sb.toString();
    }

    public static String generateUUID(Context context) {
        Exception e2;
        String strValueOf;
        long jCurrentTimeMillis = (System.currentTimeMillis() / 1000) / 3600;
        if ((ContextCompat.checkSelfPermission(context, Permission.READ_PHONE_STATE) != 0 || ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_WIFI_STATE") != 0) && jCurrentTimeMillis - ((Long) SharedPreferencesUtils.get(context, "uuid_time", 0L)).longValue() > 48) {
            SharedPreferencesUtils.put(context, "uuid_time", Long.valueOf(jCurrentTimeMillis));
            ((Activity) context).requestPermissions(new String[]{Permission.READ_PHONE_STATE, "android.permission.ACCESS_WIFI_STATE"}, 1);
            return null;
        }
        try {
            strValueOf = getDeviceId(context) + getAndroidId(context) + getDeviceSerial() + getDeviceMac(context);
            try {
                if (strValueOf.trim().equals("")) {
                    strValueOf = String.valueOf(System.currentTimeMillis());
                }
                String mD5String = getMD5String(strValueOf);
                if (mD5String.length() < 36) {
                    return mD5String + getRandomString(36 - mD5String.length());
                }
                return mD5String.substring(0, 36);
            } catch (Exception e3) {
                e2 = e3;
                e2.printStackTrace();
                return strValueOf;
            }
        } catch (Exception e4) {
            e2 = e4;
            strValueOf = "";
        }
    }

    private static String getDeviceId(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getImei();
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    private static String getAndroidId(Context context) {
        try {
            return Settings.Secure.getString(context.getContentResolver(), "android_id");
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    private static String getDeviceSerial() throws Exception {
        try {
            return Build.SERIAL;
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    private static String getDeviceMac(Context context) {
        try {
            return getMachineHardwareAddress();
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    private static String getMachineHardwareAddress() throws SocketException {
        Enumeration<NetworkInterface> networkInterfaces;
        String strBytesToString = null;
        try {
            networkInterfaces = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e2) {
            e2.printStackTrace();
            networkInterfaces = null;
        }
        if (networkInterfaces == null) {
            return null;
        }
        while (networkInterfaces.hasMoreElements()) {
            try {
                strBytesToString = bytesToString(networkInterfaces.nextElement().getHardwareAddress());
            } catch (SocketException e3) {
                e3.printStackTrace();
            }
            if (strBytesToString != null) {
                break;
            }
        }
        return strBytesToString;
    }

    private static String bytesToString(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (byte b2 : bArr) {
            sb.append(String.format("%02X:", Byte.valueOf(b2)));
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    private static String getMD5String(String str) throws NoSuchAlgorithmException {
        byte[] bArrDigest = MessageDigest.getInstance("SHA1").digest(str.getBytes());
        Formatter formatter = new Formatter();
        int length = bArrDigest.length;
        for (int i2 = 0; i2 < length; i2++) {
            Object[] objArr = {Byte.valueOf(bArrDigest[i2])};
            if (i2 == 4 || i2 == 7 || i2 == 10 || i2 == 13) {
                formatter.format("%s", "-");
            } else {
                formatter.format("%02x", objArr);
            }
        }
        return formatter.toString();
    }

    private static String getRandomString(int i2) {
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i3 = 0; i3 < i2; i3++) {
            stringBuffer.append("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".charAt(random.nextInt(62)));
        }
        return stringBuffer.toString();
    }
}
