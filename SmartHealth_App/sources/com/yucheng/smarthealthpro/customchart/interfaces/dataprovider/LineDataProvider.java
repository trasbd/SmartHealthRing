package com.yucheng.smarthealthpro.customchart.interfaces.dataprovider;

import com.yucheng.smarthealthpro.customchart.components.YAxis;
import com.yucheng.smarthealthpro.customchart.data.LineData;

/* loaded from: classes4.dex */
public interface LineDataProvider extends BarLineScatterCandleBubbleDataProvider {
    YAxis getAxis(YAxis.AxisDependency dependency);

    LineData getLineData();
}
