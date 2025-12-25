package com.yucheng.smarthealthpro.home.activity.respiratoryrate.bean;

/* loaded from: classes5.dex */
public class RespiratoryRateHisListBean {
    private String awRR;
    private String state;
    private String time;

    public RespiratoryRateHisListBean(String time, String awRR, String state) {
        this.time = time;
        this.awRR = awRR;
        this.state = state;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAwRR() {
        return this.awRR;
    }

    public void setAwRR(String awRR) {
        this.awRR = awRR;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
