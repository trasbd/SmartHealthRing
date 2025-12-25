package com.yucheng.ycbtsdk.utils;

import android.util.Log;
import com.yucheng.ycbtsdk.YCBTClient;

/* loaded from: classes5.dex */
public class YCBTLog {
    private static final String LOG_TAG = "yc-ble";

    public static void d(String str) {
        if (YCBTClient.OpenLogSwitch) {
            LogToFileUtils.write(str);
            Log.d(LOG_TAG, str);
        }
    }

    public static void e(String str) {
        if (YCBTClient.OpenLogSwitch) {
            LogToFileUtils.write(str);
            Log.e(LOG_TAG, str);
        }
    }

    public static void saveBoolFile(String str, byte[] bArr) {
        String strBoolByteToString = ByteUtil.boolByteToString(bArr);
        if (YCBTClient.OpenLogSwitch) {
            LogToFileUtils.write(str, strBoolByteToString);
        }
        Log.e(LOG_TAG, str + "--" + strBoolByteToString);
    }

    public static void saveECGFile(String str, byte[] bArr, boolean z) {
        String strEcgByteToString = ByteUtil.ecgByteToString(bArr);
        if (YCBTClient.OpenLogSwitch) {
            LogToFileUtils.write(str, strEcgByteToString, z);
        }
        Log.e(LOG_TAG, str + "--" + strEcgByteToString);
    }

    public static void saveFile(String str, String str2) {
        if (YCBTClient.OpenLogSwitch) {
            LogToFileUtils.write(str, str2);
        }
        Log.e(LOG_TAG, str + "--" + str2);
    }

    public static void saveThreeFile(String str, byte[] bArr) {
        String strThreeByteToString = ByteUtil.threeByteToString(bArr);
        if (YCBTClient.OpenLogSwitch) {
            LogToFileUtils.write(str, strThreeByteToString);
        }
        Log.e(LOG_TAG, str + "--" + strThreeByteToString);
    }

    public static void saveFile(String str, String str2, boolean z) {
        if (YCBTClient.OpenLogSwitch) {
            LogToFileUtils.write(str, str2, z);
        }
        Log.e(LOG_TAG, str + "--" + str2 + "--" + z);
    }
}
