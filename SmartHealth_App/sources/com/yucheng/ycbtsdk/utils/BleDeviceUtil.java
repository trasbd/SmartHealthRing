package com.yucheng.ycbtsdk.utils;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.text.TextUtils;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class BleDeviceUtil {
    public static List<BluetoothDevice> getBleConnectedDevice(Context context) {
        List<BluetoothDevice> connectedDevices;
        try {
            connectedDevices = ((BluetoothManager) context.getSystemService("bluetooth")).getConnectedDevices(7);
        } catch (Exception e2) {
            e2.printStackTrace();
            connectedDevices = null;
        }
        return connectedDevices == null ? new ArrayList() : connectedDevices;
    }

    public static String getMacAddOne(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String upperCase = Integer.toHexString((Integer.valueOf(str.split(":")[str.split(":").length - 1], 16).intValue() + 1) & 255).toUpperCase();
        StringBuilder sbAppend = new StringBuilder().append(str.substring(0, str.length() - 2));
        if (upperCase.length() != 2) {
            upperCase = "0" + upperCase;
        }
        return sbAppend.append(upperCase).toString();
    }

    public static String getMacSubOne(String str) {
        String upperCase = Integer.toHexString((Integer.valueOf(str.split(":")[str.split(":").length - 1], 16).intValue() - 1) & 255).toUpperCase();
        StringBuilder sbAppend = new StringBuilder().append(str.substring(0, str.length() - 2));
        if (upperCase.length() != 2) {
            upperCase = "0" + upperCase;
        }
        return sbAppend.append(upperCase).toString();
    }

    public static List<BluetoothDevice> getSystemConnectedDevice() throws NoSuchMethodException, SecurityException {
        ArrayList arrayList = new ArrayList();
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        try {
            BluetoothAdapter.class.getDeclaredMethod("getConnectionState", null).setAccessible(true);
            for (BluetoothDevice bluetoothDevice : defaultAdapter.getBondedDevices()) {
                Method declaredMethod = BluetoothDevice.class.getDeclaredMethod("isConnected", null);
                declaredMethod.setAccessible(true);
                if (((Boolean) declaredMethod.invoke(bluetoothDevice, null)).booleanValue()) {
                    arrayList.add(bluetoothDevice);
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return arrayList;
    }

    public static boolean isBleConnected(String str, Context context) {
        if (!BluetoothAdapter.checkBluetoothAddress(str)) {
            return false;
        }
        Iterator<BluetoothDevice> it2 = getBleConnectedDevice(context).iterator();
        while (it2.hasNext()) {
            if (str.equals(it2.next().getAddress())) {
                return true;
            }
        }
        return false;
    }

    public static boolean isConnected(String str) throws NoSuchMethodException, SecurityException {
        if (!BluetoothAdapter.checkBluetoothAddress(str)) {
            return false;
        }
        BluetoothDevice remoteDevice = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(str);
        try {
            Method declaredMethod = BluetoothDevice.class.getDeclaredMethod("isConnected", null);
            if (declaredMethod == null) {
                return false;
            }
            declaredMethod.setAccessible(true);
            return ((Boolean) declaredMethod.invoke(remoteDevice, null)).booleanValue();
        } catch (Exception unused) {
            return false;
        }
    }
}
