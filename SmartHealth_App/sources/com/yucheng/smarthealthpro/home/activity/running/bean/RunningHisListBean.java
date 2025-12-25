package com.yucheng.smarthealthpro.home.activity.running.bean;

/* loaded from: classes5.dex */
public class RunningHisListBean implements Comparable<RunningHisListBean> {
    private int sportCalorie;
    private int sportDistance;
    private String sportEndTime;
    private String sportStartTime;
    private int sportStep;
    private long timeMilli;

    public long getTimeMilli() {
        return this.timeMilli;
    }

    public void setTimeMilli(long timeMilli) {
        this.timeMilli = timeMilli;
    }

    public RunningHisListBean() {
    }

    public RunningHisListBean(String sportStartTime, String sportEndTime, int sportStep, int sportDistance, int sportCalorie, long timeMilli) {
        this.sportStartTime = sportStartTime;
        this.sportEndTime = sportEndTime;
        this.sportStep = sportStep;
        this.sportDistance = sportDistance;
        this.sportCalorie = sportCalorie;
        this.timeMilli = timeMilli;
    }

    public RunningHisListBean(String sportStartTime, String sportEndTime, int sportStep, int sportDistance, int sportCalorie) {
        this.sportStartTime = sportStartTime;
        this.sportEndTime = sportEndTime;
        this.sportStep = sportStep;
        this.sportDistance = sportDistance;
        this.sportCalorie = sportCalorie;
    }

    public String getSportStartTime() {
        return this.sportStartTime;
    }

    public void setSportStartTime(String sportStartTime) {
        this.sportStartTime = sportStartTime;
    }

    public String getSportEndTime() {
        return this.sportEndTime;
    }

    public void setSportEndTime(String sportEndTime) {
        this.sportEndTime = sportEndTime;
    }

    public int getSportStep() {
        return this.sportStep;
    }

    public void setSportStep(int sportStep) {
        this.sportStep = sportStep;
    }

    public int getSportDistance() {
        return this.sportDistance;
    }

    public void setSportDistance(int sportDistance) {
        this.sportDistance = sportDistance;
    }

    public int getSportCalorie() {
        return this.sportCalorie;
    }

    public void setSportCalorie(int sportCalorie) {
        this.sportCalorie = sportCalorie;
    }

    @Override // java.lang.Comparable
    public int compareTo(RunningHisListBean o) {
        return (int) (o.timeMilli - this.timeMilli);
    }
}
