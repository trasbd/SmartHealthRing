package com.yucheng.smarthealthpro.customchart.renderer.scatter;

import android.graphics.Canvas;
import android.graphics.Paint;
import com.yucheng.smarthealthpro.customchart.interfaces.datasets.IScatterDataSet;
import com.yucheng.smarthealthpro.customchart.utils.ViewPortHandler;

/* loaded from: classes4.dex */
public interface IShapeRenderer {
    void renderShape(Canvas c2, IScatterDataSet dataSet, ViewPortHandler viewPortHandler, float posX, float posY, Paint renderPaint);
}
