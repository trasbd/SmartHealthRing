package com.yucheng.smarthealthpro.customchart.interfaces.dataprovider;

import com.yucheng.smarthealthpro.customchart.data.BarData;

/* loaded from: classes4.dex */
public interface BarDataProvider extends BarLineScatterCandleBubbleDataProvider {
    BarData getBarData();

    boolean isDrawBarShadowEnabled();

    boolean isDrawValueAboveBarEnabled();

    boolean isHighlightFullBarEnabled();
}
