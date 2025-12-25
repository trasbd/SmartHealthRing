package com.yucheng.smarthealthpro.home.activity.running.bean;

/* loaded from: classes5.dex */
public class StepBean {
    private String beginhhmm;
    private String endhhmm;
    private float step;
    public String unit;

    public StepBean(float step, String beginhhmm, String endhhmm) {
        this.step = step;
        this.beginhhmm = beginhhmm;
        this.endhhmm = endhhmm;
    }

    public float getStep() {
        return this.step;
    }

    public void setStep(float step) {
        this.step = step;
    }

    public String getBeginhhmm() {
        return this.beginhhmm;
    }

    public void setBeginhhmm(String beginhhmm) {
        this.beginhhmm = beginhhmm;
    }

    public String getEndhhmm() {
        return this.endhhmm;
    }

    public void setEndhhmm(String endhhmm) {
        this.endhhmm = endhhmm;
    }
}
