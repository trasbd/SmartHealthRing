package com.yucheng.ycbtsdk.gatt.model;

import android.bluetooth.BluetoothGatt;
import android.content.Context;
import com.dd.plist.ASCIIPropertyListParser;

/* loaded from: classes5.dex */
public class BleDevice {
    private long connectedTime;
    private final Context context;
    private final BluetoothGatt gatt;
    private final String tag = "BleManager";
    private int mtu = 20;

    public BleDevice(Context context, BluetoothGatt bluetoothGatt) {
        this.context = context;
        this.gatt = bluetoothGatt;
    }

    public long getConnectedTime() {
        return this.connectedTime;
    }

    public BluetoothGatt getGatt() {
        return this.gatt;
    }

    public int getMtu() {
        return this.mtu;
    }

    public void setConnectedTime(long j2) {
        this.connectedTime = j2;
    }

    public void setMtu(int i2) {
        this.mtu = i2;
    }

    public String toString() {
        return "BleDevice{context=" + this.context + ", gatt=" + this.gatt + ", mtu=" + this.mtu + ", connectedTime=" + this.connectedTime + ASCIIPropertyListParser.DICTIONARY_END_TOKEN;
    }
}
