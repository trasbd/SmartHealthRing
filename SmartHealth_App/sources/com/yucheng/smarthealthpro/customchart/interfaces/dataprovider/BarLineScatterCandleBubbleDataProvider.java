package com.yucheng.smarthealthpro.customchart.interfaces.dataprovider;

import com.yucheng.smarthealthpro.customchart.components.YAxis;
import com.yucheng.smarthealthpro.customchart.data.BarLineScatterCandleBubbleData;
import com.yucheng.smarthealthpro.customchart.utils.Transformer;

/* loaded from: classes4.dex */
public interface BarLineScatterCandleBubbleDataProvider extends ChartInterface {
    BarLineScatterCandleBubbleData getData();

    float getHighestVisibleX();

    float getLowestVisibleX();

    Transformer getTransformer(YAxis.AxisDependency axis);

    boolean isInverted(YAxis.AxisDependency axis);
}
