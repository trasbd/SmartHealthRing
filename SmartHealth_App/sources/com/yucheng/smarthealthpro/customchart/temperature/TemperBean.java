package com.yucheng.smarthealthpro.customchart.temperature;

/* loaded from: classes4.dex */
public class TemperBean {
    private String mMonthDay;
    private float temper;
    private int time;

    public TemperBean(float temper, int time, String mMonthDay) {
        this.temper = temper;
        this.time = time;
        this.mMonthDay = mMonthDay;
    }

    public float getTemper() {
        return this.temper;
    }

    public void setTemper(float temper) {
        this.temper = temper;
    }

    public int getTime() {
        return this.time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getmMonthDay() {
        return this.mMonthDay;
    }

    public void setmMonthDay(String mMonthDay) {
        this.mMonthDay = mMonthDay;
    }
}
