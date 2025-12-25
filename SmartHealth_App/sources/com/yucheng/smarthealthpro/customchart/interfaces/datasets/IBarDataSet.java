package com.yucheng.smarthealthpro.customchart.interfaces.datasets;

import com.yucheng.smarthealthpro.customchart.data.BarEntry;
import com.yucheng.smarthealthpro.customchart.utils.Fill;
import java.util.List;

/* loaded from: classes4.dex */
public interface IBarDataSet extends IBarLineScatterCandleBubbleDataSet<BarEntry> {
    int getBarBorderColor();

    float getBarBorderWidth();

    int getBarShadowColor();

    Fill getFill(int index);

    List<Fill> getFills();

    int getHighLightAlpha();

    String[] getStackLabels();

    int getStackSize();

    boolean isStacked();
}
