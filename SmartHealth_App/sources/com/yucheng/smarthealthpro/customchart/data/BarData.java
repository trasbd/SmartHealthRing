package com.yucheng.smarthealthpro.customchart.data;

import com.yucheng.smarthealthpro.customchart.interfaces.datasets.IBarDataSet;
import java.util.List;

/* loaded from: classes4.dex */
public class BarData extends BarLineScatterCandleBubbleData<IBarDataSet> {
    private float mBarWidth;

    public BarData() {
        this.mBarWidth = 0.85f;
    }

    public BarData(IBarDataSet... dataSets) {
        super(dataSets);
        this.mBarWidth = 0.85f;
    }

    public BarData(List<IBarDataSet> dataSets) {
        super(dataSets);
        this.mBarWidth = 0.85f;
    }

    public void setBarWidth(float mBarWidth) {
        this.mBarWidth = mBarWidth;
    }

    public float getBarWidth() {
        return this.mBarWidth;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void groupBars(float fromX, float groupSpace, float barSpace) {
        BarEntry barEntry;
        if (this.mDataSets.size() <= 1) {
            throw new RuntimeException("BarData needs to hold at least 2 BarDataSets to allow grouping.");
        }
        int entryCount = ((IBarDataSet) getMaxEntryCountSet()).getEntryCount();
        float f2 = groupSpace / 2.0f;
        float f3 = barSpace / 2.0f;
        float f4 = this.mBarWidth / 2.0f;
        float groupWidth = getGroupWidth(groupSpace, barSpace);
        for (int i2 = 0; i2 < entryCount; i2++) {
            float f5 = fromX + f2;
            for (T t : this.mDataSets) {
                float f6 = f5 + f3 + f4;
                if (i2 < t.getEntryCount() && (barEntry = (BarEntry) t.getEntryForIndex(i2)) != null) {
                    barEntry.setX(f6);
                }
                f5 = f6 + f4 + f3;
            }
            float f7 = f5 + f2;
            float f8 = groupWidth - (f7 - fromX);
            if (f8 > 0.0f || f8 < 0.0f) {
                f7 += f8;
            }
            fromX = f7;
        }
        notifyDataChanged();
    }

    public float getGroupWidth(float groupSpace, float barSpace) {
        return (this.mDataSets.size() * (this.mBarWidth + barSpace)) + groupSpace;
    }
}
