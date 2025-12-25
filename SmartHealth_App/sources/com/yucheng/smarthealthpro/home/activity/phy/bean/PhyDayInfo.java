package com.yucheng.smarthealthpro.home.activity.phy.bean;

import com.yucheng.smarthealthpro.view.chart.PhyData;

/* loaded from: classes5.dex */
public class PhyDayInfo {
    public int beginTime;
    public String beginTimes;
    public int endTime;
    public String endTimes;
    public int type;

    public PhyData toPhyData() {
        return new PhyData(this.beginTime, this.endTime, this.type, this.beginTimes, this.endTimes);
    }
}
