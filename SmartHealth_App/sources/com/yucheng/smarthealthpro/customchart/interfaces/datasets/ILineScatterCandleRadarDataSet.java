package com.yucheng.smarthealthpro.customchart.interfaces.datasets;

import android.graphics.DashPathEffect;
import com.yucheng.smarthealthpro.customchart.data.Entry;

/* loaded from: classes4.dex */
public interface ILineScatterCandleRadarDataSet<T extends Entry> extends IBarLineScatterCandleBubbleDataSet<T> {
    DashPathEffect getDashPathEffectHighlight();

    float getHighlightLineWidth();

    boolean isHorizontalHighlightIndicatorEnabled();

    boolean isVerticalHighlightIndicatorEnabled();
}
