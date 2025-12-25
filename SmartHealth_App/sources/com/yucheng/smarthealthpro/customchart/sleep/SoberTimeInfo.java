package com.yucheng.smarthealthpro.customchart.sleep;

/* loaded from: classes4.dex */
public class SoberTimeInfo {
    public String soberEndTime;
    public int soberEndTimes;
    public String soberStartTime;
    public int soberStartTimes;

    public SoberTimeInfo(int soberStartTimes, int soberEndTimes, String soberStartTime, String soberEndTime) {
        this.soberStartTimes = soberStartTimes;
        this.soberEndTimes = soberEndTimes;
        this.soberStartTime = soberStartTime;
        this.soberEndTime = soberEndTime;
    }

    public int getSoberStartTimes() {
        return this.soberStartTimes;
    }

    public void setSoberStartTimes(int soberStartTimes) {
        this.soberStartTimes = soberStartTimes;
    }

    public int getSoberEndTimes() {
        return this.soberEndTimes;
    }

    public void setSoberEndTimes(int soberEndTimes) {
        this.soberEndTimes = soberEndTimes;
    }

    public String getSoberStartTime() {
        return this.soberStartTime;
    }

    public void setSoberStartTime(String soberStartTime) {
        this.soberStartTime = soberStartTime;
    }

    public String getSoberEndTime() {
        return this.soberEndTime;
    }

    public void setSoberEndTime(String soberEndTime) {
        this.soberEndTime = soberEndTime;
    }
}
