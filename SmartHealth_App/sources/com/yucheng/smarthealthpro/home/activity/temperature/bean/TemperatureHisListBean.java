package com.yucheng.smarthealthpro.home.activity.temperature.bean;

/* loaded from: classes5.dex */
public class TemperatureHisListBean {
    private String mValue;
    public int model;
    private String state;
    private String time;
    private int timeSec;
    public String unit;

    public TemperatureHisListBean(String time, String mValue, String state) {
        this.time = time;
        this.mValue = mValue;
        this.state = state;
    }

    public TemperatureHisListBean(String time, String mValue, String state, int timeSec) {
        this.time = time;
        this.timeSec = timeSec;
        this.mValue = mValue;
        this.state = state;
    }

    public TemperatureHisListBean(String time, String mValue, String state, String unit) {
        this.time = time;
        this.mValue = mValue;
        this.state = state;
        this.unit = unit;
    }

    public void setModel(int model) {
        this.model = model;
    }

    public int getModel() {
        return this.model;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getmValue() {
        return this.mValue;
    }

    public void setmValue(String mValue) {
        this.mValue = mValue;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getTimeSec() {
        return this.timeSec;
    }

    public void setTimeSec(int timeSec) {
        this.timeSec = timeSec;
    }

    public String toString() {
        return "TemperatureHisListBean{time='" + this.time + "', mValue='" + this.mValue + "', state='" + this.state + "'}";
    }
}
