package com.yucheng.smarthealthpro.me.dfu.scanner;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.ScanResult;

/* loaded from: classes5.dex */
public class ExtendedBluetoothDevice {
    static final int NO_RSSI = -1000;
    public final BluetoothDevice device;
    public boolean isBonded;
    public String name;
    public int rssi;

    public ExtendedBluetoothDevice(final ScanResult scanResult) {
        this.device = scanResult.getDevice();
        this.name = scanResult.getScanRecord() != null ? scanResult.getScanRecord().getDeviceName() : null;
        this.rssi = scanResult.getRssi();
        this.isBonded = false;
    }

    public ExtendedBluetoothDevice(final BluetoothDevice device) {
        this.device = device;
        this.name = device.getName();
        this.rssi = -1000;
        this.isBonded = true;
    }

    public boolean matches(final ScanResult scanResult) {
        return this.device.getAddress().equals(scanResult.getDevice().getAddress());
    }
}
