package com.yucheng.ycbtsdk.gatt.model;

import android.bluetooth.le.ScanResult;
import android.os.Parcel;
import com.dd.plist.ASCIIPropertyListParser;
import com.jieli.jl_bt_ota.util.CHexConver;

/* loaded from: classes5.dex */
public class BleScanInfo {
    private boolean isEnableConnect;
    private byte[] rawData;
    private int rssi;
    private ScanResult scanResult;

    public BleScanInfo() {
        this.isEnableConnect = true;
    }

    public byte[] getRawData() {
        return this.rawData;
    }

    public int getRssi() {
        return this.rssi;
    }

    public ScanResult getScanResult() {
        return this.scanResult;
    }

    public boolean isEnableConnect() {
        return this.isEnableConnect;
    }

    public BleScanInfo setEnableConnect(boolean z) {
        this.isEnableConnect = z;
        return this;
    }

    public BleScanInfo setRawData(byte[] bArr) {
        this.rawData = bArr;
        return this;
    }

    public BleScanInfo setRssi(int i2) {
        this.rssi = i2;
        return this;
    }

    public BleScanInfo setScanResult(ScanResult scanResult) {
        this.scanResult = scanResult;
        return this;
    }

    public String toString() {
        return "BleScanMessage{rawData=" + CHexConver.byte2HexStr(this.rawData) + ", rssi=" + this.rssi + ", isEnableConnect=" + this.isEnableConnect + ASCIIPropertyListParser.DICTIONARY_END_TOKEN;
    }

    protected BleScanInfo(Parcel parcel) {
        this.isEnableConnect = true;
        this.rawData = parcel.createByteArray();
        this.rssi = parcel.readInt();
        this.isEnableConnect = parcel.readByte() != 0;
    }
}
