package com.yucheng.ycbtsdk.bean;

import com.dd.plist.ASCIIPropertyListParser;
import com.yucheng.ycbtsdk.utils.ByteUtil;

/* loaded from: classes5.dex */
public class HealthAssistanceFunction {
    private boolean bloodFat;
    private boolean bloodOxygen;
    private boolean bloodSugar;
    private boolean bpAccurate;
    private boolean bpNormal;
    private boolean ecg;
    private boolean heartRate;
    private boolean hrv;
    private boolean temperature;
    private boolean uricAcid;

    public HealthAssistanceFunction() {
        this.bloodSugar = false;
    }

    public boolean isBloodFat() {
        return this.bloodFat;
    }

    public boolean isBloodOxygen() {
        return this.bloodOxygen;
    }

    public boolean isBloodSugar() {
        return this.bloodSugar;
    }

    public boolean isBpAccurate() {
        return this.bpAccurate;
    }

    public boolean isBpNormal() {
        return this.bpNormal;
    }

    public boolean isEcg() {
        return this.ecg;
    }

    public boolean isHeartRate() {
        return this.heartRate;
    }

    public boolean isHrv() {
        return this.hrv;
    }

    public boolean isTemperature() {
        return this.temperature;
    }

    public boolean isUricAcid() {
        return this.uricAcid;
    }

    public byte[] reunited() {
        return new byte[]{(byte) (ByteUtil.setValueAsBits(this.bpAccurate ? 1 : 0, ByteUtil.setValueAsBits(this.bpNormal ? 1 : 0, ByteUtil.setValueAsBits(this.temperature ? 1 : 0, ByteUtil.setValueAsBits(this.bloodOxygen ? 1 : 0, ByteUtil.setValueAsBits(this.heartRate ? 1 : 0, ByteUtil.setValueAsBits(this.bloodFat ? 1 : 0, ByteUtil.setValueAsBits(this.uricAcid ? 1 : 0, ByteUtil.setValueAsBits(this.bloodSugar ? 1 : 0, 0, 0), 1), 2), 3), 4), 5), 6), 7) & 255), (byte) (ByteUtil.setValueAsBits(this.hrv ? 1 : 0, ByteUtil.setValueAsBits(this.ecg ? 1 : 0, 0, 0), 1) & 255)};
    }

    public void setBloodFat(boolean z) {
        this.bloodFat = z;
    }

    public void setBloodOxygen(boolean z) {
        this.bloodOxygen = z;
    }

    public void setBloodSugar(boolean z) {
        this.bloodSugar = z;
    }

    public void setBpAccurate(boolean z) {
        this.bpAccurate = z;
    }

    public void setBpNormal(boolean z) {
        this.bpNormal = z;
    }

    public void setEcg(boolean z) {
        this.ecg = z;
    }

    public void setHeartRate(boolean z) {
        this.heartRate = z;
    }

    public void setHrv(boolean z) {
        this.hrv = z;
    }

    public void setTemperature(boolean z) {
        this.temperature = z;
    }

    public void setUricAcid(boolean z) {
        this.uricAcid = z;
    }

    public String toString() {
        return "HealthAssistanceFunction{bloodSugar=" + this.bloodSugar + ", uricAcid=" + this.uricAcid + ", bloodFat=" + this.bloodFat + ", heartRate=" + this.heartRate + ", bloodOxygen=" + this.bloodOxygen + ", temperature=" + this.temperature + ", bpNormal=" + this.bpNormal + ", bpAccurate=" + this.bpAccurate + ", ecg=" + this.ecg + ", hrv=" + this.hrv + ASCIIPropertyListParser.DICTIONARY_END_TOKEN;
    }

    public HealthAssistanceFunction(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9, boolean z10) {
        this.bloodSugar = z;
        this.uricAcid = z2;
        this.bloodFat = z3;
        this.heartRate = z4;
        this.bloodOxygen = z5;
        this.temperature = z6;
        this.bpNormal = z7;
        this.bpAccurate = z8;
        this.ecg = z9;
        this.hrv = z10;
    }
}
