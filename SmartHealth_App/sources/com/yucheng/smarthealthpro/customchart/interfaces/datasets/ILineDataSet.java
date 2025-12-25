package com.yucheng.smarthealthpro.customchart.interfaces.datasets;

import android.graphics.DashPathEffect;
import com.yucheng.smarthealthpro.customchart.data.Entry;
import com.yucheng.smarthealthpro.customchart.data.LineDataSet;
import com.yucheng.smarthealthpro.customchart.formatter.IFillFormatter;

/* loaded from: classes4.dex */
public interface ILineDataSet extends ILineRadarDataSet<Entry> {
    int getCircleColor(int index);

    int getCircleColorCount();

    int getCircleHoleColor();

    float getCircleHoleRadius();

    float getCircleRadius();

    float getCubicIntensity();

    DashPathEffect getDashPathEffect();

    IFillFormatter getFillFormatter();

    LineDataSet.Mode getMode();

    boolean isDashedLineEnabled();

    boolean isDrawCircleHoleEnabled();

    boolean isDrawCirclesEnabled();

    @Deprecated
    boolean isDrawCubicEnabled();

    @Deprecated
    boolean isDrawSteppedEnabled();
}
