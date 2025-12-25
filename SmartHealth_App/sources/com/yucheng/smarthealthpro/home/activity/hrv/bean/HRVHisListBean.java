package com.yucheng.smarthealthpro.home.activity.hrv.bean;

/* loaded from: classes5.dex */
public class HRVHisListBean {
    private String HRV;
    private String state;
    private String time;

    public HRVHisListBean(String time, String HRV, String state) {
        this.time = time;
        this.HRV = HRV;
        this.state = state;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getHRV() {
        return this.HRV;
    }

    public void setHRV(String HRV) {
        this.HRV = HRV;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
