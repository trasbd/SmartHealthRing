package com.yucheng.smarthealthpro.view.chart;

import android.content.Context;
import android.graphics.RectF;
import android.util.AttributeSet;
import com.yucheng.smarthealthpro.customchart.charts.BarChart;
import com.yucheng.smarthealthpro.customchart.data.BarData;
import com.yucheng.smarthealthpro.customchart.data.BarEntry;
import com.yucheng.smarthealthpro.customchart.interfaces.datasets.IBarDataSet;

/* loaded from: classes5.dex */
public class SleepChart extends BarChart {
    public SleepChart(Context context) {
        super(context);
    }

    public SleepChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SleepChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override // com.yucheng.smarthealthpro.customchart.charts.BarChart, com.yucheng.smarthealthpro.customchart.charts.BarLineChartBase, com.yucheng.smarthealthpro.customchart.charts.Chart
    protected void init() {
        super.init();
        this.mRenderer = new SleepRenderer(this, this.mAnimator, this.mViewPortHandler, getContext());
        setHighlighter(new SleepHighlighter(this));
        getXAxis().setSpaceMin(0.5f);
        getXAxis().setSpaceMax(0.5f);
    }

    @Override // com.yucheng.smarthealthpro.customchart.charts.BarChart
    public void getBarBounds(BarEntry entry, RectF outputRect) {
        IBarDataSet iBarDataSet = (IBarDataSet) ((BarData) this.mData).getDataSetForEntry(entry);
        if (iBarDataSet == null) {
            outputRect.set(Float.MIN_VALUE, Float.MIN_VALUE, Float.MIN_VALUE, Float.MIN_VALUE);
            return;
        }
        float y = entry.getY();
        float x = entry.getX();
        ((BarData) this.mData).getBarWidth();
        SleepItemEntry sleepItemEntry = (SleepItemEntry) entry;
        outputRect.set(x, y, sleepItemEntry.endTime, (sleepItemEntry.type * 5) - 5);
        getTransformer(iBarDataSet.getAxisDependency()).rectValueToPixel(outputRect);
    }
}
