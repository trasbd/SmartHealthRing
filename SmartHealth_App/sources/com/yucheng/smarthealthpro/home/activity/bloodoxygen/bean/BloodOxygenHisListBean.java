package com.yucheng.smarthealthpro.home.activity.bloodoxygen.bean;

/* loaded from: classes5.dex */
public class BloodOxygenHisListBean {
    private String state;
    private String time;
    private String value;

    public BloodOxygenHisListBean(String time, String value, String state) {
        this.time = time;
        this.value = value;
        this.state = state;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
