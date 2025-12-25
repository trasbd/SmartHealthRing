package com.yucheng.smarthealthpro.home.activity.bloodpressure.bean;

/* loaded from: classes5.dex */
public class BloodPressureHisListBean {
    private int bloodDBP;
    private int bloodSBP;
    private String bpStartTime;
    private int isInflated;
    private String state;
    private String time;

    public BloodPressureHisListBean(String bpStartTime, String time, int bloodDBP, int bloodSBP, String state, int isInflated) {
        this.bpStartTime = bpStartTime;
        this.time = time;
        this.bloodDBP = bloodDBP;
        this.bloodSBP = bloodSBP;
        this.state = state;
        this.isInflated = isInflated;
    }

    public int getIsInflated() {
        return this.isInflated;
    }

    public void setIsInflated(int isInflated) {
        this.isInflated = isInflated;
    }

    public String getBpStartTime() {
        return this.bpStartTime;
    }

    public void setBpStartTime(String bpStartTime) {
        this.bpStartTime = bpStartTime;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getBloodDBP() {
        return this.bloodDBP;
    }

    public void setBloodDBP(int bloodDBP) {
        this.bloodDBP = bloodDBP;
    }

    public int getBloodSBP() {
        return this.bloodSBP;
    }

    public void setBloodSBP(int bloodSBP) {
        this.bloodSBP = bloodSBP;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
