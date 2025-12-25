package com.yucheng.smarthealthpro.view.chart;

import com.yucheng.smarthealthpro.customchart.data.BarEntry;

/* loaded from: classes5.dex */
public class SleepItemEntry extends BarEntry {
    public int beginTime;
    public int endTime;
    public int type;

    public SleepItemEntry(float x, float y, int type, int beginTime, int endTime) {
        super(x, y);
        this.type = type;
        this.beginTime = beginTime;
        this.endTime = endTime;
    }
}
