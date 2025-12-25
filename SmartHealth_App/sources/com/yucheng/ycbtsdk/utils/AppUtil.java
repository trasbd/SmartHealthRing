package com.yucheng.ycbtsdk.utils;

import android.bluetooth.BluetoothGatt;
import android.content.Context;
import android.os.Build;
import androidx.core.app.ActivityCompat;
import com.yanzhenjie.permission.Permission;

/* loaded from: classes5.dex */
public class AppUtil {
    public static boolean checkHasConnectPermission(Context context) {
        if (Build.VERSION.SDK_INT >= 31) {
            return isHasPermission(context, "android.permission.BLUETOOTH_CONNECT");
        }
        return true;
    }

    public static boolean checkHasScanPermission(Context context) {
        if (Build.VERSION.SDK_INT >= 31) {
            return isHasPermission(context, "android.permission.BLUETOOTH_SCAN");
        }
        return true;
    }

    public static boolean isHasLocationPermission(Context context) {
        return isHasPermission(context, Permission.ACCESS_COARSE_LOCATION);
    }

    public static boolean isHasPermission(Context context, String str) {
        return context != null && ActivityCompat.checkSelfPermission(context, str) == 0;
    }

    public static boolean isHasStoragePermission(Context context) {
        return Build.VERSION.SDK_INT >= 28 ? isHasPermission(context, Permission.READ_EXTERNAL_STORAGE) : isHasPermission(context, Permission.WRITE_EXTERNAL_STORAGE) && isHasPermission(context, Permission.READ_EXTERNAL_STORAGE);
    }

    public static boolean refreshBleDeviceCache(Context context, BluetoothGatt bluetoothGatt) {
        if (bluetoothGatt != null && checkHasConnectPermission(context)) {
            try {
                return bluetoothGatt.getClass().getMethod("refresh", null).invoke(bluetoothGatt, null) == Boolean.TRUE;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return false;
    }
}
