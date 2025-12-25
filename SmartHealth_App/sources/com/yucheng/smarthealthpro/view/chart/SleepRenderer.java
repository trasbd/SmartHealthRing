package com.yucheng.smarthealthpro.view.chart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.Log;
import com.yucheng.smarthealthpro.customchart.animation.ChartAnimator;
import com.yucheng.smarthealthpro.customchart.buffer.BarBuffer;
import com.yucheng.smarthealthpro.customchart.data.BarData;
import com.yucheng.smarthealthpro.customchart.highlight.Highlight;
import com.yucheng.smarthealthpro.customchart.highlight.Range;
import com.yucheng.smarthealthpro.customchart.interfaces.dataprovider.BarDataProvider;
import com.yucheng.smarthealthpro.customchart.interfaces.datasets.IBarDataSet;
import com.yucheng.smarthealthpro.customchart.renderer.BarChartRenderer;
import com.yucheng.smarthealthpro.customchart.utils.Transformer;
import com.yucheng.smarthealthpro.customchart.utils.Utils;
import com.yucheng.smarthealthpro.customchart.utils.ViewPortHandler;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes5.dex */
public class SleepRenderer extends BarChartRenderer {
    public SleepRenderer(BarDataProvider chart, ChartAnimator animator, ViewPortHandler viewPortHandler, Context context) {
        super(chart, animator, viewPortHandler, context);
    }

    @Override // com.yucheng.smarthealthpro.customchart.renderer.BarChartRenderer, com.yucheng.smarthealthpro.customchart.renderer.DataRenderer
    public void initBuffers() {
        BarData barData = this.mChart.getBarData();
        this.mBarBuffers = new PhyBuffer[barData.getDataSetCount()];
        for (int i2 = 0; i2 < this.mBarBuffers.length; i2++) {
            IBarDataSet iBarDataSet = (IBarDataSet) barData.getDataSetByIndex(i2);
            this.mBarBuffers[i2] = new PhyBuffer(iBarDataSet.getEntryCount() * 4 * (iBarDataSet.isStacked() ? iBarDataSet.getStackSize() : 1), barData.getDataSetCount(), iBarDataSet.isStacked());
        }
    }

    @Override // com.yucheng.smarthealthpro.customchart.renderer.BarChartRenderer
    protected void drawDataSet(Canvas c2, IBarDataSet dataSet, int index) {
        Transformer transformer = this.mChart.getTransformer(dataSet.getAxisDependency());
        this.mBarBorderPaint.setColor(dataSet.getBarBorderColor());
        this.mBarBorderPaint.setStrokeWidth(Utils.convertDpToPixel(dataSet.getBarBorderWidth()));
        int i2 = 0;
        boolean z = dataSet.getBarBorderWidth() > 0.0f;
        float phaseX = this.mAnimator.getPhaseX();
        float phaseY = this.mAnimator.getPhaseY();
        BarBuffer barBuffer = this.mBarBuffers[index];
        barBuffer.setPhases(phaseX, phaseY);
        barBuffer.setDataSet(index);
        barBuffer.setInverted(this.mChart.isInverted(dataSet.getAxisDependency()));
        barBuffer.feed(dataSet);
        transformer.pointValuesToPixel(barBuffer.buffer);
        if (dataSet.getFills() != null) {
            dataSet.getFills().isEmpty();
        }
        boolean z2 = dataSet.getColors().size() == 1;
        this.mChart.isInverted(dataSet.getAxisDependency());
        if (z2) {
            this.mRenderPaint.setColor(dataSet.getColor());
        }
        int i3 = 0;
        while (i2 < barBuffer.size()) {
            int i4 = i2 + 2;
            if (this.mViewPortHandler.isInBoundsLeft(barBuffer.buffer[i4])) {
                if (!this.mViewPortHandler.isInBoundsRight(barBuffer.buffer[i2])) {
                    return;
                }
                if (barBuffer instanceof PhyBuffer) {
                    this.mRenderPaint.setColor(((PhyBuffer) barBuffer).getColor(dataSet, i3));
                }
                int i5 = i2 + 1;
                int i6 = i2 + 3;
                Log.e("Renderer", "Renderer:" + barBuffer.buffer[i2] + StringUtils.SPACE + barBuffer.buffer[i5] + StringUtils.SPACE + barBuffer.buffer[i4] + StringUtils.SPACE + barBuffer.buffer[i6]);
                c2.drawRect(barBuffer.buffer[i2], barBuffer.buffer[i5], barBuffer.buffer[i4], barBuffer.buffer[i6], this.mRenderPaint);
                if (z) {
                    c2.drawRect(barBuffer.buffer[i2], barBuffer.buffer[i5], barBuffer.buffer[i4], barBuffer.buffer[i6], this.mBarBorderPaint);
                }
            }
            i2 += 4;
            i3++;
        }
    }

    @Override // com.yucheng.smarthealthpro.customchart.renderer.BarChartRenderer
    protected void setHighlightDrawPos(Highlight high, RectF bar) {
        high.setDraw(bar.centerX(), bar.top);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.yucheng.smarthealthpro.customchart.renderer.BarChartRenderer, com.yucheng.smarthealthpro.customchart.renderer.DataRenderer
    public void drawHighlighted(Canvas c2, Highlight[] indices) {
        float y;
        float y2;
        float f2;
        float f3;
        BarData barData = this.mChart.getBarData();
        for (Highlight highlight : indices) {
            IBarDataSet iBarDataSet = (IBarDataSet) barData.getDataSetByIndex(highlight.getDataSetIndex());
            if (iBarDataSet != null && iBarDataSet.isHighlightEnabled()) {
                SleepItemEntry sleepItemEntry = (SleepItemEntry) iBarDataSet.getEntryForXValue(highlight.getX(), highlight.getY());
                if (isInBoundsX(sleepItemEntry, iBarDataSet)) {
                    Transformer transformer = this.mChart.getTransformer(iBarDataSet.getAxisDependency());
                    this.mHighlightPaint.setColor(iBarDataSet.getHighLightColor());
                    this.mHighlightPaint.setAlpha(iBarDataSet.getHighLightAlpha());
                    if (highlight.getStackIndex() >= 0 && sleepItemEntry.isStacked()) {
                        if (this.mChart.isHighlightFullBarEnabled()) {
                            y = sleepItemEntry.getPositiveSum();
                            y2 = -sleepItemEntry.getNegativeSum();
                        } else {
                            Range range = sleepItemEntry.getRanges()[highlight.getStackIndex()];
                            f3 = range.from;
                            f2 = range.to;
                            prepareBarHighlight(sleepItemEntry.getX(), f3, f2, sleepItemEntry.endTime - sleepItemEntry.beginTime, transformer);
                            setHighlightDrawPos(highlight, this.mBarRect);
                            c2.drawRect(this.mBarRect, this.mHighlightPaint);
                        }
                    } else {
                        y = sleepItemEntry.getY();
                        y2 = sleepItemEntry.getY() - 5.0f;
                    }
                    f2 = y2;
                    f3 = y;
                    prepareBarHighlight(sleepItemEntry.getX(), f3, f2, sleepItemEntry.endTime - sleepItemEntry.beginTime, transformer);
                    setHighlightDrawPos(highlight, this.mBarRect);
                    c2.drawRect(this.mBarRect, this.mHighlightPaint);
                }
            }
        }
    }

    @Override // com.yucheng.smarthealthpro.customchart.renderer.BarChartRenderer
    protected void prepareBarHighlight(float x, float y1, float y2, float barWidthHalf, Transformer trans) {
        this.mBarRect.set(x, y1, barWidthHalf + x, y2);
        trans.rectToPixelPhase(this.mBarRect, this.mAnimator.getPhaseY());
    }
}
