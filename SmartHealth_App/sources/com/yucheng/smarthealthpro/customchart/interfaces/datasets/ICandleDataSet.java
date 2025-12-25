package com.yucheng.smarthealthpro.customchart.interfaces.datasets;

import android.graphics.Paint;
import com.yucheng.smarthealthpro.customchart.data.CandleEntry;

/* loaded from: classes4.dex */
public interface ICandleDataSet extends ILineScatterCandleRadarDataSet<CandleEntry> {
    float getBarSpace();

    int getDecreasingColor();

    Paint.Style getDecreasingPaintStyle();

    int getIncreasingColor();

    Paint.Style getIncreasingPaintStyle();

    int getNeutralColor();

    int getShadowColor();

    boolean getShadowColorSameAsCandle();

    float getShadowWidth();

    boolean getShowCandleBar();
}
