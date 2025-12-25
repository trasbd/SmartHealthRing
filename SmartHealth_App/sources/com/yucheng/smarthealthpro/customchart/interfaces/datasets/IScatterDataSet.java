package com.yucheng.smarthealthpro.customchart.interfaces.datasets;

import com.yucheng.smarthealthpro.customchart.data.Entry;
import com.yucheng.smarthealthpro.customchart.renderer.IShapeRenderer;

/* loaded from: classes4.dex */
public interface IScatterDataSet extends ILineScatterCandleRadarDataSet<Entry> {
    int getScatterShapeHoleColor();

    float getScatterShapeHoleRadius();

    float getScatterShapeSize();

    IShapeRenderer getShapeRenderer();
}
