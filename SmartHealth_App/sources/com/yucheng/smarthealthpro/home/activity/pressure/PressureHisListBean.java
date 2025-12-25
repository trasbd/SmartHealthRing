package com.yucheng.smarthealthpro.home.activity.pressure;

/* loaded from: classes5.dex */
public class PressureHisListBean {
    private String pressure;
    private String state;
    private String time;

    public PressureHisListBean(String time, String pressure, String state) {
        this.time = time;
        this.pressure = pressure;
        this.state = state;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPressure() {
        return this.pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
