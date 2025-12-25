package com.yucheng.smarthealthpro.customchart.klinechart;

/* loaded from: classes4.dex */
public class BloodPressureBean {
    private String bpStartTime;
    private float mClose;
    private float mOpen;
    private float mShadowHigh;
    private float mShadowLow;
    private float maxtemper;
    private float mintemper;
    private int time;

    public BloodPressureBean(String bpStartTime, int time, float maxtemper, float mintemper, float mShadowHigh, float mShadowLow, float mClose, float mOpen) {
        this.bpStartTime = bpStartTime;
        this.time = time;
        this.maxtemper = maxtemper;
        this.mintemper = mintemper;
        this.mShadowHigh = mShadowHigh;
        this.mShadowLow = mShadowLow;
        this.mClose = mClose;
        this.mOpen = mOpen;
    }

    public String getBpStartTime() {
        return this.bpStartTime;
    }

    public void setBpStartTime(String bpStartTime) {
        this.bpStartTime = bpStartTime;
    }

    public int getTime() {
        return this.time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public float getMaxtemper() {
        return this.maxtemper;
    }

    public void setMaxtemper(float maxtemper) {
        this.maxtemper = maxtemper;
    }

    public float getMintemper() {
        return this.mintemper;
    }

    public void setMintemper(float mintemper) {
        this.mintemper = mintemper;
    }

    public float getmShadowHigh() {
        return this.mShadowHigh;
    }

    public void setmShadowHigh(float mShadowHigh) {
        this.mShadowHigh = mShadowHigh;
    }

    public float getmShadowLow() {
        return this.mShadowLow;
    }

    public void setmShadowLow(float mShadowLow) {
        this.mShadowLow = mShadowLow;
    }

    public float getmClose() {
        return this.mClose;
    }

    public void setmClose(float mClose) {
        this.mClose = mClose;
    }

    public float getmOpen() {
        return this.mOpen;
    }

    public void setmOpen(float mOpen) {
        this.mOpen = mOpen;
    }
}
