package com.yucheng.smarthealthpro.me.bean;

/* loaded from: classes5.dex */
public class MeAlarmClock {
    private int alarmHour;
    private int alarmMin;
    private String alternativeDate;
    private String isSwitch;
    private String label;

    public MeAlarmClock(int alarmHour, int alarmMin, String label, String alternativeDate, String isSwitch) {
        this.alarmHour = alarmHour;
        this.alarmMin = alarmMin;
        this.label = label;
        this.alternativeDate = alternativeDate;
        this.isSwitch = isSwitch;
    }

    public int getAlarmHour() {
        return this.alarmHour;
    }

    public void setAlarmHour(int alarmHour) {
        this.alarmHour = alarmHour;
    }

    public int getAlarmMin() {
        return this.alarmMin;
    }

    public void setAlarmMin(int alarmMin) {
        this.alarmMin = alarmMin;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getAlternativeDate() {
        return this.alternativeDate;
    }

    public void setAlternativeDate(String alternativeDate) {
        this.alternativeDate = alternativeDate;
    }

    public String getIsSwitch() {
        return this.isSwitch;
    }

    public void setIsSwitch(String isSwitch) {
        this.isSwitch = isSwitch;
    }
}
