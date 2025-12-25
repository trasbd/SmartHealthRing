package com.yucheng.ycbtsdk.gatt.model;

import com.dd.plist.ASCIIPropertyListParser;
import java.util.Arrays;

/* loaded from: classes5.dex */
public class BleScanMessage {
    private int battery;
    private boolean isOTA;
    private String oldBleAddress;
    private byte[] rawData;
    private int rssi;

    public int getBattery() {
        return this.battery;
    }

    public String getOldBleAddress() {
        return this.oldBleAddress;
    }

    public byte[] getRawData() {
        return this.rawData;
    }

    public int getRssi() {
        return this.rssi;
    }

    public boolean isOTA() {
        return this.isOTA;
    }

    public BleScanMessage setBattery(int i2) {
        this.battery = i2;
        return this;
    }

    public BleScanMessage setOTA(boolean z) {
        this.isOTA = z;
        return this;
    }

    public BleScanMessage setOldBleAddress(String str) {
        this.oldBleAddress = str;
        return this;
    }

    public BleScanMessage setRawData(byte[] bArr) {
        this.rawData = bArr;
        return this;
    }

    public BleScanMessage setRssi(int i2) {
        this.rssi = i2;
        return this;
    }

    public String toString() {
        return "BleScanMessage{rawData=" + Arrays.toString(this.rawData) + ", rssi=" + this.rssi + ", oldBleAddress='" + this.oldBleAddress + "', isOTA=" + this.isOTA + ", battery=" + this.battery + ASCIIPropertyListParser.DICTIONARY_END_TOKEN;
    }
}
