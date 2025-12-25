package com.yucheng.ycbtsdk.utils;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.util.SparseArray;
import androidx.core.app.ActivityCompat;
import com.alibaba.fastjson2.JSONB;
import com.google.gson.Gson;
import com.yucheng.ycbtsdk.bean.ScanDeviceBean;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes5.dex */
public class BluetoothUtils {
    public static final String TAG = "BluetoothUtils";

    public static String bytesToMacAddress(byte[] bArr) {
        if (bArr == null || bArr.length != 6) {
            throw new IllegalArgumentException("字节数组必须为 6 字节");
        }
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < bArr.length; i2++) {
            sb.append(String.format("%02X", Integer.valueOf(bArr[i2] & 255)));
            if (i2 < bArr.length - 1) {
                sb.append(":");
            }
        }
        return sb.toString();
    }

    public static boolean createBond(Context context, BluetoothDevice bluetoothDevice, int i2) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        if (bluetoothDevice == null || !isHasConnectPermission(context)) {
            return false;
        }
        try {
            Method declaredMethod = bluetoothDevice.getClass().getDeclaredMethod("createBond", Integer.TYPE);
            declaredMethod.setAccessible(true);
            Object objInvoke = declaredMethod.invoke(bluetoothDevice, Integer.valueOf(i2));
            if (objInvoke instanceof Boolean) {
                return ((Boolean) objInvoke).booleanValue();
            }
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static boolean deviceEquals(BluetoothDevice bluetoothDevice, BluetoothDevice bluetoothDevice2) {
        return (bluetoothDevice == null || bluetoothDevice2 == null || !bluetoothDevice.getAddress().equals(bluetoothDevice2.getAddress())) ? false : true;
    }

    public static boolean isHasConnectPermission(Context context) {
        if (Build.VERSION.SDK_INT >= 31) {
            return isHasPermission(context, "android.permission.BLUETOOTH_CONNECT");
        }
        return true;
    }

    public static boolean isHasPermission(Context context, String str) {
        return context != null && ActivityCompat.checkSelfPermission(context, str) == 0;
    }

    public static ScanDeviceBean parseScanDeviceInfo(ScanResult scanResult) {
        SparseArray<byte[]> sparseArray;
        int i2;
        ScanDeviceBean scanDeviceBean = new ScanDeviceBean();
        try {
        } catch (Exception e2) {
            e = e2;
        }
        if (scanResult.getScanRecord() == null) {
            return scanDeviceBean;
        }
        SparseArray<byte[]> manufacturerSpecificData = scanResult.getScanRecord().getManufacturerSpecificData();
        if (manufacturerSpecificData != null) {
            char c2 = 0;
            int i3 = 0;
            while (i3 < manufacturerSpecificData.size()) {
                byte[] bArr = manufacturerSpecificData.get(manufacturerSpecificData.keyAt(i3));
                if (ByteUtil.byteToString(bArr).length() != 54 || bArr == null || bArr.length < 20) {
                    sparseArray = manufacturerSpecificData;
                    i2 = i3;
                } else {
                    ScanDeviceBean scanDeviceBean2 = new ScanDeviceBean();
                    try {
                        int i4 = (bArr[c2] & 255) + ((bArr[1] & 255) << 8);
                        byte b2 = bArr[2];
                        String str = ((b2 & JSONB.Constants.BC_INT32_NUM_MIN) >> 4) + "." + (b2 & 15);
                        int i5 = bArr[3] & 255;
                        if (i5 == 1) {
                            byte b3 = bArr[4];
                            byte b4 = bArr[5];
                            byte b5 = bArr[6];
                            byte b6 = bArr[7];
                            byte b7 = bArr[8];
                            String str2 = ((int) b3) + "." + (b4 < 10 ? "0" + ((int) b4) : Integer.valueOf(b4));
                            scanDeviceBean2.setBroadcastProtocol(i5);
                            scanDeviceBean2.setVersion(str2);
                            scanDeviceBean2.setRingNumber(b5);
                            scanDeviceBean2.setRingColor(b6);
                            scanDeviceBean2.setImageId(b7);
                        }
                        byte b8 = bArr[10];
                        byte b9 = bArr[11];
                        int i6 = (bArr[12] & 255) + ((bArr[13] & 255) << 8);
                        byte b10 = bArr[14];
                        byte b11 = bArr[15];
                        int i7 = (bArr[16] & 255) + ((bArr[17] & 255) << 8);
                        byte b12 = bArr[18];
                        sparseArray = manufacturerSpecificData;
                        int i8 = bArr[19] & 255;
                        i2 = i3;
                        int i9 = bArr[20] & 255;
                        String string = (i9 < 10 ? new StringBuilder().append("0").append(i9) : new StringBuilder().append("").append(i9)).toString();
                        if (bArr.length >= 26) {
                            scanDeviceBean2.setAdvMac(bytesToMacAddress(ByteUtil.getSubArray(bArr, 21, 6)));
                        }
                        scanDeviceBean2.setDbp(b8);
                        scanDeviceBean2.setSbp(b9);
                        scanDeviceBean2.setStep(i6);
                        scanDeviceBean2.setHeart(b10);
                        scanDeviceBean2.setBloodOxygen(b11);
                        scanDeviceBean2.setCalorie(i7);
                        scanDeviceBean2.setBattery(b12);
                        scanDeviceBean2.setTemp(i8 + "." + string);
                        scanDeviceBean2.setSleepTimeStr(str);
                        scanDeviceBean2.setDistance(i4);
                        if (i5 == 1) {
                            YCBTLog.e("新广播协议  scanDeviceBean=" + new Gson().toJson(scanDeviceBean2));
                        }
                        scanDeviceBean = scanDeviceBean2;
                    } catch (Exception e3) {
                        e = e3;
                        scanDeviceBean = scanDeviceBean2;
                        e.printStackTrace();
                        return scanDeviceBean;
                    }
                }
                i3 = i2 + 1;
                manufacturerSpecificData = sparseArray;
                c2 = 0;
            }
        }
        return scanDeviceBean;
    }

    public static boolean removeBond(Context context, BluetoothDevice bluetoothDevice) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (bluetoothDevice == null || !isHasConnectPermission(context)) {
            return false;
        }
        try {
            Object objInvoke = bluetoothDevice.getClass().getMethod("removeBond", null).invoke(bluetoothDevice, null);
            if (objInvoke instanceof Boolean) {
                return ((Boolean) objInvoke).booleanValue();
            }
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            Log.e(TAG, "Invoke removeBond : " + e2.getMessage());
            return false;
        }
    }

    public static boolean createBond(Context context, BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null || !isHasConnectPermission(context)) {
            return false;
        }
        return bluetoothDevice.createBond();
    }
}
