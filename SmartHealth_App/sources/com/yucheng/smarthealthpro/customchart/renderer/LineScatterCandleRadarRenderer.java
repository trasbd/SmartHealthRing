package com.yucheng.smarthealthpro.customchart.renderer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.util.Log;
import com.yucheng.smarthealthpro.customchart.animation.ChartAnimator;
import com.yucheng.smarthealthpro.customchart.interfaces.datasets.IBarDataSet;
import com.yucheng.smarthealthpro.customchart.interfaces.datasets.ILineScatterCandleRadarDataSet;
import com.yucheng.smarthealthpro.customchart.utils.ViewPortHandler;

/* loaded from: classes4.dex */
public abstract class LineScatterCandleRadarRenderer extends BarLineScatterCandleBubbleRenderer {
    private Path mHighlightLinePath;

    public LineScatterCandleRadarRenderer(ChartAnimator animator, ViewPortHandler viewPortHandler) {
        super(animator, viewPortHandler);
        this.mHighlightLinePath = new Path();
    }

    protected void drawHighlightLines(Canvas c2, float x, float y, ILineScatterCandleRadarDataSet set) {
        this.mHighlightPaint.setColor(set.getHighLightColor());
        this.mHighlightPaint.setStrokeWidth(set.getHighlightLineWidth());
        this.mHighlightPaint.setPathEffect(set.getDashPathEffectHighlight());
        if (set.isVerticalHighlightIndicatorEnabled()) {
            this.mHighlightLinePath.reset();
            this.mHighlightLinePath.moveTo(x, this.mViewPortHandler.contentTop());
            this.mHighlightLinePath.lineTo(x, this.mViewPortHandler.contentBottom());
            c2.drawPath(this.mHighlightLinePath, this.mHighlightPaint);
        }
        if (set.isHorizontalHighlightIndicatorEnabled()) {
            this.mHighlightLinePath.reset();
            this.mHighlightLinePath.moveTo(this.mViewPortHandler.contentLeft(), y);
            this.mHighlightLinePath.lineTo(this.mViewPortHandler.contentRight(), y);
            c2.drawPath(this.mHighlightLinePath, this.mHighlightPaint);
        }
    }

    protected void drawHighlightLine(Canvas c2, float x, float y, IBarDataSet set) {
        Log.i("ZZZZZZZZZ", "--" + x + "--" + y + "--" + this.mViewPortHandler.contentTop() + "--" + this.mViewPortHandler.contentBottom() + "--" + this.mViewPortHandler.contentLeft() + "--" + this.mViewPortHandler.contentRight());
        this.mHighlightPaint.setColor(Color.parseColor("#FF8C00"));
        this.mHighlightPaint.setStrokeWidth(1.44f);
        this.mHighlightPaint.setPathEffect(null);
        this.mHighlightLinePath.reset();
        this.mHighlightLinePath.moveTo(x, this.mViewPortHandler.contentTop());
        this.mHighlightLinePath.lineTo(x, this.mViewPortHandler.contentBottom());
        c2.drawLine(x, this.mViewPortHandler.contentTop(), x, this.mViewPortHandler.contentBottom(), this.mHighlightPaint);
        c2.drawPath(this.mHighlightLinePath, this.mHighlightPaint);
    }
}
