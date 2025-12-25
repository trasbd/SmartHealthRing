package com.yucheng.smarthealthpro.customchart.sleep;

/* loaded from: classes4.dex */
public class LightSleepInfo {
    public String qsEndTime;
    public int qsEndTimes;
    public String qsStartTime;
    public int qsStartTimes;

    public LightSleepInfo(int qsStartTimes, int qsEndTimes, String qsStartTime, String qsEndTime) {
        this.qsStartTimes = qsStartTimes;
        this.qsEndTimes = qsEndTimes;
        this.qsStartTime = qsStartTime;
        this.qsEndTime = qsEndTime;
    }

    public int getQsStartTimes() {
        return this.qsStartTimes;
    }

    public void setQsStartTimes(int qsStartTimes) {
        this.qsStartTimes = qsStartTimes;
    }

    public int getQsEndTimes() {
        return this.qsEndTimes;
    }

    public void setQsEndTimes(int qsEndTimes) {
        this.qsEndTimes = qsEndTimes;
    }

    public String getQsStartTime() {
        return this.qsStartTime;
    }

    public void setQsStartTime(String qsStartTime) {
        this.qsStartTime = qsStartTime;
    }

    public String getQsEndTime() {
        return this.qsEndTime;
    }

    public void setQsEndTime(String qsEndTime) {
        this.qsEndTime = qsEndTime;
    }
}
