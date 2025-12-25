package com.yucheng.smarthealthpro.view.chart;

/* loaded from: classes5.dex */
public class PhyData {
    public int beginTime;
    public String beginTimes;
    public int endTime;
    public String endTimes;
    public int type;

    public PhyData(int beginTime, int endTime, int type) {
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.type = type;
    }

    public PhyData(int beginTime, int endTime, int type, String beginTimes, String endTimes) {
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.type = type;
        this.beginTimes = beginTimes;
        this.endTimes = endTimes;
    }
}
