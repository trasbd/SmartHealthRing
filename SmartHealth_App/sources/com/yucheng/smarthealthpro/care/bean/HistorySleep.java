package com.yucheng.smarthealthpro.care.bean;

import java.io.Serializable;

/* loaded from: classes4.dex */
public class HistorySleep implements Serializable {
    private int sleepLong;
    private int sleepType;
    private long stime;

    public long getStime() {
        return this.stime;
    }

    public void setStime(long stime) {
        this.stime = stime;
    }

    public int getSleepType() {
        return this.sleepType;
    }

    public void setSleepType(int sleepType) {
        this.sleepType = sleepType;
    }

    public int getSleepLong() {
        return this.sleepLong;
    }

    public void setSleepLong(int sleepLong) {
        this.sleepLong = sleepLong;
    }
}
