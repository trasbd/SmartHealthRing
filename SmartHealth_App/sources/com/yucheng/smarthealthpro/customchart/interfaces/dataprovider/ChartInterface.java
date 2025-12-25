package com.yucheng.smarthealthpro.customchart.interfaces.dataprovider;

import android.graphics.RectF;
import com.yucheng.smarthealthpro.customchart.data.ChartData;
import com.yucheng.smarthealthpro.customchart.formatter.IValueFormatter;
import com.yucheng.smarthealthpro.customchart.utils.MPPointF;

/* loaded from: classes4.dex */
public interface ChartInterface {
    MPPointF getCenterOfView();

    MPPointF getCenterOffsets();

    RectF getContentRect();

    ChartData getData();

    IValueFormatter getDefaultValueFormatter();

    int getHeight();

    float getMaxHighlightDistance();

    int getMaxVisibleCount();

    int getWidth();

    float getXChartMax();

    float getXChartMin();

    float getXRange();

    float getYChartMax();

    float getYChartMin();
}
