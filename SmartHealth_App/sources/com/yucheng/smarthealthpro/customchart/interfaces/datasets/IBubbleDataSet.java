package com.yucheng.smarthealthpro.customchart.interfaces.datasets;

import com.yucheng.smarthealthpro.customchart.data.BubbleEntry;

/* loaded from: classes4.dex */
public interface IBubbleDataSet extends IBarLineScatterCandleBubbleDataSet<BubbleEntry> {
    float getHighlightCircleWidth();

    float getMaxSize();

    boolean isNormalizeSizeEnabled();

    void setHighlightCircleWidth(float width);
}
