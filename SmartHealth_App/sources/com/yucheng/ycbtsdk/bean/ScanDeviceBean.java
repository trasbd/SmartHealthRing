package com.yucheng.ycbtsdk.bean;

import android.bluetooth.BluetoothDevice;

/* loaded from: classes5.dex */
public class ScanDeviceBean implements Comparable<ScanDeviceBean> {
    public int battery;
    public int bloodOxygen;
    public int broadcastProtocol;
    public int calorie;
    private int dbp;
    public BluetoothDevice device;
    private String deviceName;
    private int deviceRssi;
    public int distance;
    public int heart;
    public int imageId;
    public int ringColor;
    public int ringNumber;
    private int sbp;
    public float sleepTime;
    public String sleepTimeStr;
    public int step;
    public String temp;
    public String version;
    private String deviceMac = "";
    private String advMac = "";

    public String getAdvMac() {
        return this.advMac;
    }

    public int getBattery() {
        return this.battery;
    }

    public int getBloodOxygen() {
        return this.bloodOxygen;
    }

    public int getBroadcastProtocol() {
        return this.broadcastProtocol;
    }

    public int getCalorie() {
        return this.calorie;
    }

    public int getDbp() {
        return this.dbp;
    }

    public String getDeviceMac() {
        return this.deviceMac;
    }

    public String getDeviceName() {
        return this.deviceName;
    }

    public int getDeviceRssi() {
        return this.deviceRssi;
    }

    public int getDistance() {
        return this.distance;
    }

    public int getHeart() {
        return this.heart;
    }

    public int getImageId() {
        return this.imageId;
    }

    public int getRingColor() {
        return this.ringColor;
    }

    public int getRingNumber() {
        return this.ringNumber;
    }

    public int getSbp() {
        return this.sbp;
    }

    public float getSleepTime() {
        return this.sleepTime;
    }

    public String getSleepTimeStr() {
        return this.sleepTimeStr;
    }

    public int getStep() {
        return this.step;
    }

    public String getTemp() {
        return this.temp;
    }

    public String getVersion() {
        return this.version;
    }

    public void setAdvMac(String str) {
        this.advMac = str;
    }

    public void setBattery(int i2) {
        this.battery = i2;
    }

    public void setBloodOxygen(int i2) {
        this.bloodOxygen = i2;
    }

    public void setBroadcastProtocol(int i2) {
        this.broadcastProtocol = i2;
    }

    public void setCalorie(int i2) {
        this.calorie = i2;
    }

    public void setDbp(int i2) {
        this.dbp = i2;
    }

    public void setDeviceMac(String str) {
        this.deviceMac = str;
    }

    public void setDeviceName(String str) {
        this.deviceName = str;
    }

    public void setDeviceRssi(int i2) {
        this.deviceRssi = i2;
    }

    public void setDistance(int i2) {
        this.distance = i2;
    }

    public void setHeart(int i2) {
        this.heart = i2;
    }

    public void setImageId(int i2) {
        this.imageId = i2;
    }

    public void setRingColor(int i2) {
        this.ringColor = i2;
    }

    public void setRingNumber(int i2) {
        this.ringNumber = i2;
    }

    public void setSbp(int i2) {
        this.sbp = i2;
    }

    public void setSleepTime(float f2) {
        this.sleepTime = f2;
    }

    public void setSleepTimeStr(String str) {
        this.sleepTimeStr = str;
    }

    public void setStep(int i2) {
        this.step = i2;
    }

    public void setTemp(String str) {
        this.temp = str;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    public String toString() {
        return "ScanDeviceBean{deviceMac='" + this.deviceMac + "'advMac='" + this.advMac + "', deviceName='" + this.deviceName + "'}";
    }

    @Override // java.lang.Comparable
    public int compareTo(ScanDeviceBean scanDeviceBean) {
        return -(this.deviceRssi - scanDeviceBean.getDeviceRssi());
    }
}
