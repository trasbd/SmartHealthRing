package com.yucheng.ycbtsdk.utils;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Build;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.autonavi.amap.mapcore.AMapEngineUtils;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.yanzhenjie.permission.Permission;
import com.yucheng.ycbtsdk.core.YCBTClientImpl;
import com.yucheng.ycbtsdk.response.BlePermissionResponse;

/* loaded from: classes5.dex */
public class PermissionUtil {
    private static PermissionUtil permissionUtil;
    public BlePermissionResponse blePermissionResponse;

    public static synchronized PermissionUtil getInstance() {
        if (permissionUtil == null) {
            permissionUtil = new PermissionUtil();
        }
        return permissionUtil;
    }

    private boolean isOPen(Context context) {
        if (Build.VERSION.SDK_INT >= 31) {
            return true;
        }
        LocationManager locationManager = (LocationManager) context.getSystemService(FirebaseAnalytics.Param.LOCATION);
        return locationManager.isProviderEnabled(GeocodeSearch.GPS) || locationManager.isProviderEnabled("network");
    }

    public boolean hasPermission(BluetoothAdapter bluetoothAdapter, Context context) {
        if (bluetoothAdapter == null) {
            bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        }
        if (bluetoothAdapter == null) {
            YCBTLog.e("手机不支持Ble");
            BlePermissionResponse blePermissionResponse = this.blePermissionResponse;
            if (blePermissionResponse != null) {
                blePermissionResponse.onPermissionResponse(1);
            }
            return false;
        }
        if (!bluetoothAdapter.isEnabled()) {
            if (YCBTClientImpl.getInstance().isOta) {
                if (ActivityCompat.checkSelfPermission(context, "android.permission.BLUETOOTH_CONNECT") == 0) {
                    bluetoothAdapter.enable();
                }
            } else if (ActivityCompat.checkSelfPermission(context, "android.permission.BLUETOOTH_CONNECT") == 0) {
                Intent intent = new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE");
                intent.setFlags(AMapEngineUtils.MAX_P20_WIDTH);
                context.startActivity(intent);
            }
            YCBTLog.e("没有开启 蓝牙 ");
            BlePermissionResponse blePermissionResponse2 = this.blePermissionResponse;
            if (blePermissionResponse2 != null) {
                blePermissionResponse2.onPermissionResponse(2);
            }
            return false;
        }
        int i2 = Build.VERSION.SDK_INT;
        if (i2 < 31 && !isOPen(context)) {
            YCBTLog.e("没有开启 GPS ");
            BlePermissionResponse blePermissionResponse3 = this.blePermissionResponse;
            if (blePermissionResponse3 != null) {
                blePermissionResponse3.onPermissionResponse(3);
            }
            return false;
        }
        if (i2 >= 31) {
            if (ContextCompat.checkSelfPermission(context, "android.permission.BLUETOOTH_SCAN") != 0 || ContextCompat.checkSelfPermission(context, "android.permission.BLUETOOTH_CONNECT") != 0) {
                YCBTLog.e("没有权限 BLUETOOTH_SCAN and BLUETOOTH_CONNECT");
                BlePermissionResponse blePermissionResponse4 = this.blePermissionResponse;
                if (blePermissionResponse4 != null) {
                    blePermissionResponse4.onPermissionResponse(5);
                }
                return false;
            }
        } else if (ContextCompat.checkSelfPermission(context, Permission.ACCESS_FINE_LOCATION) != 0) {
            YCBTLog.e("没有权限 ACCESS_FINE_LOCATION");
            BlePermissionResponse blePermissionResponse5 = this.blePermissionResponse;
            if (blePermissionResponse5 != null) {
                blePermissionResponse5.onPermissionResponse(4);
            }
            return false;
        }
        BlePermissionResponse blePermissionResponse6 = this.blePermissionResponse;
        if (blePermissionResponse6 != null) {
            blePermissionResponse6.onPermissionResponse(0);
        }
        return true;
    }

    public void setBlePermissionResponse(BlePermissionResponse blePermissionResponse) {
        this.blePermissionResponse = blePermissionResponse;
    }
}
