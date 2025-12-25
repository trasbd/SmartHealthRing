package com.yucheng.smarthealthpro.customchart.listener;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import com.yucheng.smarthealthpro.customchart.components.LimitLine;
import com.yucheng.smarthealthpro.customchart.components.YAxis;
import com.yucheng.smarthealthpro.customchart.renderer.YAxisRenderer;
import com.yucheng.smarthealthpro.customchart.utils.MPPointD;
import com.yucheng.smarthealthpro.customchart.utils.Transformer;
import com.yucheng.smarthealthpro.customchart.utils.Utils;
import com.yucheng.smarthealthpro.customchart.utils.ViewPortHandler;
import java.util.List;

/* loaded from: classes4.dex */
public class YAxisRendererHorizontalBarChart extends YAxisRenderer {
    protected Path mDrawZeroLinePathBuffer;
    protected float[] mRenderLimitLinesBuffer;
    protected Path mRenderLimitLinesPathBuffer;

    public YAxisRendererHorizontalBarChart(ViewPortHandler viewPortHandler, YAxis yAxis, Transformer trans) {
        super(viewPortHandler, yAxis, trans);
        this.mDrawZeroLinePathBuffer = new Path();
        this.mRenderLimitLinesPathBuffer = new Path();
        this.mRenderLimitLinesBuffer = new float[4];
        this.mLimitLinePaint.setTextAlign(Paint.Align.LEFT);
    }

    @Override // com.yucheng.smarthealthpro.customchart.renderer.AxisRenderer
    public void computeAxis(float yMin, float yMax, boolean inverted) {
        float f2;
        double d2;
        if (this.mViewPortHandler.contentHeight() > 10.0f && !this.mViewPortHandler.isFullyZoomedOutX()) {
            MPPointD valuesByTouchPoint = this.mTrans.getValuesByTouchPoint(this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentTop());
            MPPointD valuesByTouchPoint2 = this.mTrans.getValuesByTouchPoint(this.mViewPortHandler.contentRight(), this.mViewPortHandler.contentTop());
            if (!inverted) {
                f2 = (float) valuesByTouchPoint.x;
                d2 = valuesByTouchPoint2.x;
            } else {
                f2 = (float) valuesByTouchPoint2.x;
                d2 = valuesByTouchPoint.x;
            }
            MPPointD.recycleInstance(valuesByTouchPoint);
            MPPointD.recycleInstance(valuesByTouchPoint2);
            yMin = f2;
            yMax = (float) d2;
        }
        computeAxisValues(yMin, yMax);
    }

    @Override // com.yucheng.smarthealthpro.customchart.renderer.YAxisRenderer, com.yucheng.smarthealthpro.customchart.renderer.AxisRenderer
    public void renderAxisLabels(Canvas c2) {
        float fContentBottom;
        float f2;
        float fContentTop;
        if (this.mYAxis.isEnabled() && this.mYAxis.isDrawLabelsEnabled()) {
            float[] transformedPositions = getTransformedPositions();
            this.mAxisLabelPaint.setTypeface(this.mYAxis.getTypeface());
            this.mAxisLabelPaint.setTextSize(this.mYAxis.getTextSize());
            this.mAxisLabelPaint.setColor(this.mYAxis.getTextColor());
            this.mAxisLabelPaint.setTextAlign(Paint.Align.CENTER);
            float fConvertDpToPixel = Utils.convertDpToPixel(2.5f);
            float fCalcTextHeight = Utils.calcTextHeight(this.mAxisLabelPaint, "Q");
            YAxis.AxisDependency axisDependency = this.mYAxis.getAxisDependency();
            YAxis.YAxisLabelPosition labelPosition = this.mYAxis.getLabelPosition();
            if (axisDependency == YAxis.AxisDependency.LEFT) {
                if (labelPosition == YAxis.YAxisLabelPosition.OUTSIDE_CHART) {
                    fContentTop = this.mViewPortHandler.contentTop();
                } else {
                    fContentTop = this.mViewPortHandler.contentTop();
                }
                f2 = fContentTop - fConvertDpToPixel;
            } else {
                if (labelPosition == YAxis.YAxisLabelPosition.OUTSIDE_CHART) {
                    fContentBottom = this.mViewPortHandler.contentBottom();
                } else {
                    fContentBottom = this.mViewPortHandler.contentBottom();
                }
                f2 = fContentBottom + fCalcTextHeight + fConvertDpToPixel;
            }
            drawYLabels(c2, f2, transformedPositions, this.mYAxis.getYOffset());
        }
    }

    @Override // com.yucheng.smarthealthpro.customchart.renderer.YAxisRenderer, com.yucheng.smarthealthpro.customchart.renderer.AxisRenderer
    public void renderAxisLine(Canvas c2) {
        if (this.mYAxis.isEnabled() && this.mYAxis.isDrawAxisLineEnabled()) {
            this.mAxisLinePaint.setColor(this.mYAxis.getAxisLineColor());
            this.mAxisLinePaint.setStrokeWidth(this.mYAxis.getAxisLineWidth());
            if (this.mYAxis.getAxisDependency() == YAxis.AxisDependency.LEFT) {
                c2.drawLine(this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentTop(), this.mViewPortHandler.contentRight(), this.mViewPortHandler.contentTop(), this.mAxisLinePaint);
            } else {
                c2.drawLine(this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentBottom(), this.mViewPortHandler.contentRight(), this.mViewPortHandler.contentBottom(), this.mAxisLinePaint);
            }
        }
    }

    @Override // com.yucheng.smarthealthpro.customchart.renderer.YAxisRenderer
    protected void drawYLabels(Canvas canvas, float f2, float[] fArr, float f3) {
        this.mAxisLabelPaint.setTypeface(this.mYAxis.getTypeface());
        this.mAxisLabelPaint.setTextSize(this.mYAxis.getTextSize());
        this.mAxisLabelPaint.setColor(this.mYAxis.getTextColor());
        int i2 = this.mYAxis.isDrawTopYLabelEntryEnabled() ? this.mYAxis.mEntryCount : this.mYAxis.mEntryCount - 1;
        float labelXOffset = this.mYAxis.getLabelXOffset();
        for (int i3 = !this.mYAxis.isDrawBottomYLabelEntryEnabled() ? 1 : 0; i3 < i2; i3++) {
            canvas.drawText(this.mYAxis.getFormattedLabel(i3), fArr[i3 * 2], (f2 - f3) + labelXOffset, this.mAxisLabelPaint);
        }
    }

    @Override // com.yucheng.smarthealthpro.customchart.renderer.YAxisRenderer
    protected float[] getTransformedPositions() {
        if (this.mGetTransformedPositionsBuffer.length != this.mYAxis.mEntryCount * 2) {
            this.mGetTransformedPositionsBuffer = new float[this.mYAxis.mEntryCount * 2];
        }
        float[] fArr = this.mGetTransformedPositionsBuffer;
        for (int i2 = 0; i2 < fArr.length; i2 += 2) {
            fArr[i2] = this.mYAxis.mEntries[i2 / 2];
        }
        this.mTrans.pointValuesToPixel(fArr);
        return fArr;
    }

    @Override // com.yucheng.smarthealthpro.customchart.renderer.YAxisRenderer
    public RectF getGridClippingRect() {
        this.mGridClippingRect.set(this.mViewPortHandler.getContentRect());
        this.mGridClippingRect.inset(-this.mAxis.getGridLineWidth(), 0.0f);
        return this.mGridClippingRect;
    }

    @Override // com.yucheng.smarthealthpro.customchart.renderer.YAxisRenderer
    protected Path linePath(Path p, int i2, float[] positions) {
        p.moveTo(positions[i2], this.mViewPortHandler.contentTop());
        p.lineTo(positions[i2], this.mViewPortHandler.contentBottom());
        return p;
    }

    @Override // com.yucheng.smarthealthpro.customchart.renderer.YAxisRenderer
    protected void drawZeroLine(Canvas c2) {
        int iSave = c2.save();
        this.mZeroLineClippingRect.set(this.mViewPortHandler.getContentRect());
        this.mZeroLineClippingRect.inset(-this.mYAxis.getZeroLineWidth(), 0.0f);
        c2.clipRect(this.mLimitLineClippingRect);
        MPPointD pixelForValues = this.mTrans.getPixelForValues(0.0f, 0.0f);
        this.mZeroLinePaint.setColor(this.mYAxis.getZeroLineColor());
        this.mZeroLinePaint.setStrokeWidth(this.mYAxis.getZeroLineWidth());
        Path path = this.mDrawZeroLinePathBuffer;
        path.reset();
        path.moveTo(((float) pixelForValues.x) - 1.0f, this.mViewPortHandler.contentTop());
        path.lineTo(((float) pixelForValues.x) - 1.0f, this.mViewPortHandler.contentBottom());
        c2.drawPath(path, this.mZeroLinePaint);
        c2.restoreToCount(iSave);
    }

    @Override // com.yucheng.smarthealthpro.customchart.renderer.YAxisRenderer, com.yucheng.smarthealthpro.customchart.renderer.AxisRenderer
    public void renderLimitLines(Canvas c2) {
        List<LimitLine> limitLines = this.mYAxis.getLimitLines();
        if (limitLines == null || limitLines.size() <= 0) {
            return;
        }
        float[] fArr = this.mRenderLimitLinesBuffer;
        float f2 = 0.0f;
        fArr[0] = 0.0f;
        char c3 = 1;
        fArr[1] = 0.0f;
        fArr[2] = 0.0f;
        fArr[3] = 0.0f;
        Path path = this.mRenderLimitLinesPathBuffer;
        path.reset();
        int i2 = 0;
        while (i2 < limitLines.size()) {
            LimitLine limitLine = limitLines.get(i2);
            if (limitLine.isEnabled()) {
                int iSave = c2.save();
                this.mLimitLineClippingRect.set(this.mViewPortHandler.getContentRect());
                this.mLimitLineClippingRect.inset(-limitLine.getLineWidth(), f2);
                c2.clipRect(this.mLimitLineClippingRect);
                fArr[0] = limitLine.getLimit();
                fArr[2] = limitLine.getLimit();
                this.mTrans.pointValuesToPixel(fArr);
                fArr[c3] = this.mViewPortHandler.contentTop();
                fArr[3] = this.mViewPortHandler.contentBottom();
                path.moveTo(fArr[0], fArr[c3]);
                path.lineTo(fArr[2], fArr[3]);
                this.mLimitLinePaint.setStyle(Paint.Style.STROKE);
                this.mLimitLinePaint.setColor(limitLine.getLineColor());
                this.mLimitLinePaint.setPathEffect(limitLine.getDashPathEffect());
                this.mLimitLinePaint.setStrokeWidth(limitLine.getLineWidth());
                c2.drawPath(path, this.mLimitLinePaint);
                path.reset();
                String label = limitLine.getLabel();
                if (label != null && !label.equals("")) {
                    this.mLimitLinePaint.setStyle(limitLine.getTextStyle());
                    this.mLimitLinePaint.setPathEffect(null);
                    this.mLimitLinePaint.setColor(limitLine.getTextColor());
                    this.mLimitLinePaint.setTypeface(limitLine.getTypeface());
                    this.mLimitLinePaint.setStrokeWidth(0.5f);
                    this.mLimitLinePaint.setTextSize(limitLine.getTextSize());
                    float lineWidth = limitLine.getLineWidth() + limitLine.getXOffset();
                    float fConvertDpToPixel = Utils.convertDpToPixel(2.0f) + limitLine.getYOffset();
                    LimitLine.LimitLabelPosition labelPosition = limitLine.getLabelPosition();
                    if (labelPosition == LimitLine.LimitLabelPosition.RIGHT_TOP) {
                        float fCalcTextHeight = Utils.calcTextHeight(this.mLimitLinePaint, label);
                        this.mLimitLinePaint.setTextAlign(Paint.Align.LEFT);
                        c2.drawText(label, fArr[0] + lineWidth, this.mViewPortHandler.contentTop() + fConvertDpToPixel + fCalcTextHeight, this.mLimitLinePaint);
                    } else if (labelPosition == LimitLine.LimitLabelPosition.RIGHT_BOTTOM) {
                        this.mLimitLinePaint.setTextAlign(Paint.Align.LEFT);
                        c2.drawText(label, fArr[0] + lineWidth, this.mViewPortHandler.contentBottom() - fConvertDpToPixel, this.mLimitLinePaint);
                    } else if (labelPosition == LimitLine.LimitLabelPosition.LEFT_TOP) {
                        this.mLimitLinePaint.setTextAlign(Paint.Align.RIGHT);
                        c2.drawText(label, fArr[0] - lineWidth, this.mViewPortHandler.contentTop() + fConvertDpToPixel + Utils.calcTextHeight(this.mLimitLinePaint, label), this.mLimitLinePaint);
                    } else {
                        this.mLimitLinePaint.setTextAlign(Paint.Align.RIGHT);
                        c2.drawText(label, fArr[0] - lineWidth, this.mViewPortHandler.contentBottom() - fConvertDpToPixel, this.mLimitLinePaint);
                    }
                }
                c2.restoreToCount(iSave);
            }
            i2++;
            f2 = 0.0f;
            c3 = 1;
        }
    }
}
