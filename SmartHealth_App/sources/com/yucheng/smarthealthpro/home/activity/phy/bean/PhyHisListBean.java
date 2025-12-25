package com.yucheng.smarthealthpro.home.activity.phy.bean;

/* loaded from: classes5.dex */
public class PhyHisListBean {
    private long dateTime;
    private long level1Duration;
    private long level2Duration;
    private long level3Duration;
    private long level4Duration;
    private int level1Count = 0;
    private int level2Count = 0;
    private int level3Count = 0;
    private int level4Count = 0;

    public void setDateTime(long dateTime) {
        this.dateTime = dateTime;
    }

    public void setLevel1Duration(long level1Duration) {
        this.level1Duration = level1Duration;
    }

    public void setLevel2Duration(long level2Duration) {
        this.level2Duration = level2Duration;
    }

    public void setLevel3Duration(long level3Duration) {
        this.level3Duration = level3Duration;
    }

    public void setLevel4Duration(long level4Duration) {
        this.level4Duration = level4Duration;
    }

    public long getDateTime() {
        return this.dateTime;
    }

    public long getLevel1Duration() {
        return this.level1Duration;
    }

    public long getLevel2Duration() {
        return this.level2Duration;
    }

    public long getLevel3Duration() {
        return this.level3Duration;
    }

    public long getLevel4Duration() {
        return this.level4Duration;
    }

    public void setLevel1Count(int level1Count) {
        this.level1Count = level1Count;
    }

    public void setLevel2Count(int level2Count) {
        this.level2Count = level2Count;
    }

    public void setLevel3Count(int level3Count) {
        this.level3Count = level3Count;
    }

    public void setLevel4Count(int level4Count) {
        this.level4Count = level4Count;
    }

    public int getLevel1Count() {
        return this.level1Count;
    }

    public int getLevel2Count() {
        return this.level2Count;
    }

    public int getLevel3Count() {
        return this.level3Count;
    }

    public int getLevel4Count() {
        return this.level4Count;
    }
}
