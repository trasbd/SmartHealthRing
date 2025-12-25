package com.yucheng.smarthealthpro.customchart.sleep;

/* loaded from: classes4.dex */
public class DeepSleepInfo {
    public String dsEndTime;
    public int dsEndTimes;
    public String dsStartTime;
    public int dsStartTimes;

    public DeepSleepInfo(int dsStartTimes, int dsEndTimes, String dsStartTime, String dsEndTime) {
        this.dsStartTimes = dsStartTimes;
        this.dsEndTimes = dsEndTimes;
        this.dsStartTime = dsStartTime;
        this.dsEndTime = dsEndTime;
    }

    public int getDsStartTimes() {
        return this.dsStartTimes;
    }

    public void setDsStartTimes(int dsStartTimes) {
        this.dsStartTimes = dsStartTimes;
    }

    public int getDsEndTimes() {
        return this.dsEndTimes;
    }

    public void setDsEndTimes(int dsEndTimes) {
        this.dsEndTimes = dsEndTimes;
    }

    public String getDsStartTime() {
        return this.dsStartTime;
    }

    public void setDsStartTime(String dsStartTime) {
        this.dsStartTime = dsStartTime;
    }

    public String getDsEndTime() {
        return this.dsEndTime;
    }

    public void setDsEndTime(String dsEndTime) {
        this.dsEndTime = dsEndTime;
    }
}
