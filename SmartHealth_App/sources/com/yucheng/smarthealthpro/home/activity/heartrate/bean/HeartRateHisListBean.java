package com.yucheng.smarthealthpro.home.activity.heartrate.bean;

/* loaded from: classes5.dex */
public class HeartRateHisListBean {
    private String bPm;
    private String state;
    private String time;

    public HeartRateHisListBean(String time, String bPm, String state) {
        this.time = time;
        this.bPm = bPm;
        this.state = state;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getbPm() {
        return this.bPm;
    }

    public void setbPm(String bPm) {
        this.bPm = bPm;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
