package com.yucheng.smarthealthpro.me.bean;

/* loaded from: classes5.dex */
public class DeviceBaseInfoData {
    byte bindStatus;
    int deviceId;
    byte electricity;
    byte electricityStatus;
    int mainVersion;
    int subVersion;
    byte synchronizedFlag;

    public DeviceBaseInfoData(int deviceId, int subVersion, int mainVersion, byte electricityStatus, byte electricity, byte bindStatus, byte synchronizedFlag) {
        this.deviceId = deviceId;
        this.subVersion = subVersion;
        this.mainVersion = mainVersion;
        this.electricityStatus = electricityStatus;
        this.electricity = electricity;
        this.bindStatus = bindStatus;
        this.synchronizedFlag = synchronizedFlag;
    }

    public int getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public int getSubVersion() {
        return this.subVersion;
    }

    public void setSubVersion(int subVersion) {
        this.subVersion = subVersion;
    }

    public int getMainVersion() {
        return this.mainVersion;
    }

    public void setMainVersion(int mainVersion) {
        this.mainVersion = mainVersion;
    }

    public byte getElectricityStatus() {
        return this.electricityStatus;
    }

    public void setElectricityStatus(byte electricityStatus) {
        this.electricityStatus = electricityStatus;
    }

    public byte getElectricity() {
        return this.electricity;
    }

    public void setElectricity(byte electricity) {
        this.electricity = electricity;
    }

    public byte getBindStatus() {
        return this.bindStatus;
    }

    public void setBindStatus(byte bindStatus) {
        this.bindStatus = bindStatus;
    }

    public byte getSynchronizedFlag() {
        return this.synchronizedFlag;
    }

    public void setSynchronizedFlag(byte synchronizedFlag) {
        this.synchronizedFlag = synchronizedFlag;
    }

    public String toString() {
        return "deviceId " + this.deviceId + " subVersion " + this.subVersion + " mainVersion " + this.mainVersion;
    }
}
