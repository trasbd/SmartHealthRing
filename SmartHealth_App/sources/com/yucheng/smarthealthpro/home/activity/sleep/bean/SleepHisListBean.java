package com.yucheng.smarthealthpro.home.activity.sleep.bean;

/* loaded from: classes5.dex */
public class SleepHisListBean {
    private int deepSleepCount;
    private int deepSleepTotal;
    private long endTime;
    private boolean isMonthWeekDay;
    private int lightSleepCount;
    private int lightSleepTotal;
    public int remTimes;
    private long startTime;
    private String state;
    private long totalTime;
    private int wakeCount;
    public int wakeDuration;

    public SleepHisListBean(long startTime, long endTime, long totalTime, int deepSleepTotal, int lightSleepTotal, int deepSleepCount, int lightSleepCount, int wakeCount, String state, int remTimes, int wakeDuration, boolean isMonthWeekDay) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalTime = totalTime;
        this.deepSleepTotal = deepSleepTotal;
        this.lightSleepTotal = lightSleepTotal;
        this.deepSleepCount = deepSleepCount;
        this.lightSleepCount = lightSleepCount;
        this.state = state;
        this.isMonthWeekDay = isMonthWeekDay;
        this.remTimes = remTimes;
        this.wakeCount = wakeCount;
        this.wakeDuration = wakeDuration;
    }

    public long getStartTime() {
        return this.startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return this.endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public long getTotalTime() {
        return this.totalTime;
    }

    public void setTotalTime(long totalTime) {
        this.totalTime = totalTime;
    }

    public int getDeepSleepTotal() {
        return this.deepSleepTotal;
    }

    public void setDeepSleepTotal(int deepSleepTotal) {
        this.deepSleepTotal = deepSleepTotal;
    }

    public int getLightSleepTotal() {
        return this.lightSleepTotal;
    }

    public void setLightSleepTotal(int lightSleepTotal) {
        this.lightSleepTotal = lightSleepTotal;
    }

    public int getDeepSleepCount() {
        return this.deepSleepCount;
    }

    public void setDeepSleepCount(int deepSleepCount) {
        this.deepSleepCount = deepSleepCount;
    }

    public int getLightSleepCount() {
        return this.lightSleepCount;
    }

    public void setLightSleepCount(int lightSleepCount) {
        this.lightSleepCount = lightSleepCount;
    }

    public int getWakeCount() {
        return this.wakeCount;
    }

    public void setWakeCount(int wakeCount) {
        this.wakeCount = wakeCount;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isMonthWeekDay() {
        return this.isMonthWeekDay;
    }

    public void setMonthWeekDay(boolean monthWeekDay) {
        this.isMonthWeekDay = monthWeekDay;
    }
}
