package com.yucheng.smarthealthpro.customchart.renderer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import androidx.core.view.ViewCompat;
import androidx.exifinterface.media.ExifInterface;
import com.yucheng.smarthealthpro.customchart.components.LimitLine;
import com.yucheng.smarthealthpro.customchart.components.YAxis;
import com.yucheng.smarthealthpro.customchart.utils.MPPointD;
import com.yucheng.smarthealthpro.customchart.utils.Transformer;
import com.yucheng.smarthealthpro.customchart.utils.Utils;
import com.yucheng.smarthealthpro.customchart.utils.ViewPortHandler;
import java.util.List;

/* loaded from: classes4.dex */
public class YAxisRenderer extends AxisRenderer {
    protected Path mDrawZeroLinePath;
    protected float[] mGetTransformedPositionsBuffer;
    protected RectF mGridClippingRect;
    protected RectF mLimitLineClippingRect;
    protected Path mRenderGridLinesPath;
    protected Path mRenderLimitLines;
    protected float[] mRenderLimitLinesBuffer;
    protected YAxis mYAxis;
    protected RectF mZeroLineClippingRect;
    protected Paint mZeroLinePaint;

    public YAxisRenderer(ViewPortHandler viewPortHandler, YAxis yAxis, Transformer trans) {
        super(viewPortHandler, trans, yAxis);
        this.mRenderGridLinesPath = new Path();
        this.mGridClippingRect = new RectF();
        this.mGetTransformedPositionsBuffer = new float[2];
        this.mDrawZeroLinePath = new Path();
        this.mZeroLineClippingRect = new RectF();
        this.mRenderLimitLines = new Path();
        this.mRenderLimitLinesBuffer = new float[2];
        this.mLimitLineClippingRect = new RectF();
        this.mYAxis = yAxis;
        if (this.mViewPortHandler != null) {
            this.mAxisLabelPaint.setColor(ViewCompat.MEASURED_STATE_MASK);
            this.mAxisLabelPaint.setTextSize(Utils.convertDpToPixel(10.0f));
            Paint paint = new Paint(1);
            this.mZeroLinePaint = paint;
            paint.setColor(-7829368);
            this.mZeroLinePaint.setStrokeWidth(1.0f);
            this.mZeroLinePaint.setStyle(Paint.Style.STROKE);
        }
    }

    @Override // com.yucheng.smarthealthpro.customchart.renderer.AxisRenderer
    public void renderAxisLabels(Canvas c2) {
        float fContentRight;
        float fContentRight2;
        float f2;
        if (this.mYAxis.isEnabled() && this.mYAxis.isDrawLabelsEnabled()) {
            float[] transformedPositions = getTransformedPositions();
            this.mAxisLabelPaint.setTypeface(this.mYAxis.getTypeface());
            this.mAxisLabelPaint.setTextSize(this.mYAxis.getTextSize());
            this.mAxisLabelPaint.setColor(this.mYAxis.getTextColor());
            float xOffset = this.mYAxis.getXOffset();
            float fCalcTextHeight = (Utils.calcTextHeight(this.mAxisLabelPaint, ExifInterface.GPS_MEASUREMENT_IN_PROGRESS) / 2.5f) + this.mYAxis.getYOffset();
            YAxis.AxisDependency axisDependency = this.mYAxis.getAxisDependency();
            YAxis.YAxisLabelPosition labelPosition = this.mYAxis.getLabelPosition();
            if (axisDependency == YAxis.AxisDependency.LEFT) {
                if (labelPosition == YAxis.YAxisLabelPosition.OUTSIDE_CHART) {
                    this.mAxisLabelPaint.setTextAlign(Paint.Align.RIGHT);
                    fContentRight = this.mViewPortHandler.offsetLeft();
                    f2 = fContentRight - xOffset;
                } else {
                    this.mAxisLabelPaint.setTextAlign(Paint.Align.LEFT);
                    fContentRight2 = this.mViewPortHandler.offsetLeft();
                    f2 = fContentRight2 + xOffset;
                }
            } else if (labelPosition == YAxis.YAxisLabelPosition.OUTSIDE_CHART) {
                this.mAxisLabelPaint.setTextAlign(Paint.Align.LEFT);
                fContentRight2 = this.mViewPortHandler.contentRight();
                f2 = fContentRight2 + xOffset;
            } else {
                this.mAxisLabelPaint.setTextAlign(Paint.Align.RIGHT);
                fContentRight = this.mViewPortHandler.contentRight();
                f2 = fContentRight - xOffset;
            }
            drawYLabels(c2, f2, transformedPositions, fCalcTextHeight);
        }
    }

    @Override // com.yucheng.smarthealthpro.customchart.renderer.AxisRenderer
    public void renderAxisLine(Canvas c2) {
        if (this.mYAxis.isEnabled() && this.mYAxis.isDrawAxisLineEnabled()) {
            this.mAxisLinePaint.setColor(this.mYAxis.getAxisLineColor());
            this.mAxisLinePaint.setStrokeWidth(this.mYAxis.getAxisLineWidth());
            if (this.mYAxis.getAxisDependency() == YAxis.AxisDependency.LEFT) {
                c2.drawLine(this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentTop(), this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentBottom(), this.mAxisLinePaint);
            } else {
                c2.drawLine(this.mViewPortHandler.contentRight(), this.mViewPortHandler.contentTop(), this.mViewPortHandler.contentRight(), this.mViewPortHandler.contentBottom(), this.mAxisLinePaint);
            }
        }
    }

    protected void drawYLabels(Canvas canvas, float f2, float[] fArr, float f3) {
        int i2 = this.mYAxis.isDrawTopYLabelEntryEnabled() ? this.mYAxis.mEntryCount : this.mYAxis.mEntryCount - 1;
        float labelXOffset = this.mYAxis.getLabelXOffset();
        for (int i3 = !this.mYAxis.isDrawBottomYLabelEntryEnabled() ? 1 : 0; i3 < i2; i3++) {
            canvas.drawText(this.mYAxis.getFormattedLabel(i3), f2 + labelXOffset, fArr[(i3 * 2) + 1] + f3, this.mAxisLabelPaint);
        }
    }

    @Override // com.yucheng.smarthealthpro.customchart.renderer.AxisRenderer
    public void renderGridLines(Canvas c2) {
        if (this.mYAxis.isEnabled()) {
            if (this.mYAxis.isDrawGridLinesEnabled()) {
                int iSave = c2.save();
                c2.clipRect(getGridClippingRect());
                float[] transformedPositions = getTransformedPositions();
                this.mGridPaint.setColor(this.mYAxis.getGridColor());
                this.mGridPaint.setStrokeWidth(this.mYAxis.getGridLineWidth());
                this.mGridPaint.setPathEffect(this.mYAxis.getGridDashPathEffect());
                Path path = this.mRenderGridLinesPath;
                path.reset();
                for (int i2 = 0; i2 < transformedPositions.length; i2 += 2) {
                    c2.drawPath(linePath(path, i2, transformedPositions), this.mGridPaint);
                    path.reset();
                }
                c2.restoreToCount(iSave);
            }
            if (this.mYAxis.isDrawZeroLineEnabled()) {
                drawZeroLine(c2);
            }
        }
    }

    public RectF getGridClippingRect() {
        this.mGridClippingRect.set(this.mViewPortHandler.getContentRect());
        this.mGridClippingRect.inset(0.0f, -this.mAxis.getGridLineWidth());
        return this.mGridClippingRect;
    }

    protected Path linePath(Path p, int i2, float[] positions) {
        int i3 = i2 + 1;
        p.moveTo(this.mViewPortHandler.offsetLeft(), positions[i3]);
        p.lineTo(this.mViewPortHandler.contentRight(), positions[i3]);
        return p;
    }

    protected float[] getTransformedPositions() {
        if (this.mGetTransformedPositionsBuffer.length != this.mYAxis.mEntryCount * 2) {
            this.mGetTransformedPositionsBuffer = new float[this.mYAxis.mEntryCount * 2];
        }
        float[] fArr = this.mGetTransformedPositionsBuffer;
        for (int i2 = 0; i2 < fArr.length; i2 += 2) {
            fArr[i2 + 1] = this.mYAxis.mEntries[i2 / 2];
        }
        this.mTrans.pointValuesToPixel(fArr);
        return fArr;
    }

    protected void drawZeroLine(Canvas c2) {
        int iSave = c2.save();
        this.mZeroLineClippingRect.set(this.mViewPortHandler.getContentRect());
        this.mZeroLineClippingRect.inset(0.0f, -this.mYAxis.getZeroLineWidth());
        c2.clipRect(this.mZeroLineClippingRect);
        MPPointD pixelForValues = this.mTrans.getPixelForValues(0.0f, 0.0f);
        this.mZeroLinePaint.setColor(this.mYAxis.getZeroLineColor());
        this.mZeroLinePaint.setStrokeWidth(this.mYAxis.getZeroLineWidth());
        Path path = this.mDrawZeroLinePath;
        path.reset();
        path.moveTo(this.mViewPortHandler.contentLeft(), (float) pixelForValues.y);
        path.lineTo(this.mViewPortHandler.contentRight(), (float) pixelForValues.y);
        c2.drawPath(path, this.mZeroLinePaint);
        c2.restoreToCount(iSave);
    }

    @Override // com.yucheng.smarthealthpro.customchart.renderer.AxisRenderer
    public void renderLimitLines(Canvas c2) {
        List<LimitLine> limitLines = this.mYAxis.getLimitLines();
        if (limitLines == null || limitLines.size() <= 0) {
            return;
        }
        float[] fArr = this.mRenderLimitLinesBuffer;
        fArr[0] = 0.0f;
        fArr[1] = 0.0f;
        Path path = this.mRenderLimitLines;
        path.reset();
        for (int i2 = 0; i2 < limitLines.size(); i2++) {
            LimitLine limitLine = limitLines.get(i2);
            if (limitLine.isEnabled()) {
                int iSave = c2.save();
                this.mLimitLineClippingRect.set(this.mViewPortHandler.getContentRect());
                this.mLimitLineClippingRect.inset(0.0f, -limitLine.getLineWidth());
                c2.clipRect(this.mLimitLineClippingRect);
                this.mLimitLinePaint.setStyle(Paint.Style.STROKE);
                this.mLimitLinePaint.setColor(limitLine.getLineColor());
                this.mLimitLinePaint.setStrokeWidth(limitLine.getLineWidth());
                this.mLimitLinePaint.setPathEffect(limitLine.getDashPathEffect());
                fArr[1] = limitLine.getLimit();
                this.mTrans.pointValuesToPixel(fArr);
                path.moveTo(this.mViewPortHandler.contentLeft(), fArr[1]);
                path.lineTo(this.mViewPortHandler.contentRight(), fArr[1]);
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
                    float fCalcTextHeight = Utils.calcTextHeight(this.mLimitLinePaint, label);
                    float fConvertDpToPixel = Utils.convertDpToPixel(4.0f) + limitLine.getXOffset();
                    float lineWidth = limitLine.getLineWidth() + fCalcTextHeight + limitLine.getYOffset();
                    LimitLine.LimitLabelPosition labelPosition = limitLine.getLabelPosition();
                    if (labelPosition == LimitLine.LimitLabelPosition.RIGHT_TOP) {
                        this.mLimitLinePaint.setTextAlign(Paint.Align.RIGHT);
                        c2.drawText(label, this.mViewPortHandler.contentRight() - fConvertDpToPixel, (fArr[1] - lineWidth) + fCalcTextHeight, this.mLimitLinePaint);
                    } else if (labelPosition == LimitLine.LimitLabelPosition.RIGHT_BOTTOM) {
                        this.mLimitLinePaint.setTextAlign(Paint.Align.RIGHT);
                        c2.drawText(label, this.mViewPortHandler.contentRight() - fConvertDpToPixel, fArr[1] + lineWidth, this.mLimitLinePaint);
                    } else if (labelPosition == LimitLine.LimitLabelPosition.LEFT_TOP) {
                        this.mLimitLinePaint.setTextAlign(Paint.Align.LEFT);
                        c2.drawText(label, this.mViewPortHandler.contentLeft() + fConvertDpToPixel, (fArr[1] - lineWidth) + fCalcTextHeight, this.mLimitLinePaint);
                    } else {
                        this.mLimitLinePaint.setTextAlign(Paint.Align.LEFT);
                        c2.drawText(label, this.mViewPortHandler.offsetLeft() + fConvertDpToPixel, fArr[1] + lineWidth, this.mLimitLinePaint);
                    }
                }
                c2.restoreToCount(iSave);
            }
        }
    }
}
