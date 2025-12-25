package com.yucheng.smarthealthpro.customchart.sleep;

import com.dd.plist.ASCIIPropertyListParser;

/* loaded from: classes4.dex */
public class SleepMegInfo {
    public int sleepLong;
    public int sleepType;
    public long stime;

    public SleepMegInfo(int sleepType, long stime, int sleepLong) {
        this.sleepType = sleepType;
        this.stime = stime;
        this.sleepLong = sleepLong;
    }

    public int getSleepType() {
        return this.sleepType;
    }

    public void setSleepType(int sleepType) {
        this.sleepType = sleepType;
    }

    public long getStime() {
        return this.stime;
    }

    public void setStime(long stime) {
        this.stime = stime;
    }

    public int getSleepLong() {
        return this.sleepLong;
    }

    public void setSleepLong(int sleepLong) {
        this.sleepLong = sleepLong;
    }

    public String toString() {
        return "SleepMegInfo{sleepType=" + this.sleepType + ", stime=" + this.stime + ", sleepLong=" + this.sleepLong + ASCIIPropertyListParser.DICTIONARY_END_TOKEN;
    }
}
