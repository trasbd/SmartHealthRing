package com.yucheng.smarthealthpro.customchart.interfaces.datasets;

import android.graphics.drawable.Drawable;
import com.yucheng.smarthealthpro.customchart.data.Entry;

/* loaded from: classes4.dex */
public interface ILineRadarDataSet<T extends Entry> extends ILineScatterCandleRadarDataSet<T> {
    int getDPColor();

    int getFillAlpha();

    int getFillColor();

    Drawable getFillDrawable();

    int getLightColor();

    float getLineWidth();

    int getNapsColor();

    int getRemColor();

    int getType();

    boolean isDrawFilledEnabled();

    void setDrawFilled(boolean enabled);
}
