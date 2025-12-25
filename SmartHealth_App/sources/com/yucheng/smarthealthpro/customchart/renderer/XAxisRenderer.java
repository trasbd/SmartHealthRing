package com.yucheng.smarthealthpro.customchart.renderer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import androidx.core.view.ViewCompat;
import com.yucheng.smarthealthpro.customchart.components.LimitLine;
import com.yucheng.smarthealthpro.customchart.components.XAxis;
import com.yucheng.smarthealthpro.customchart.utils.FSize;
import com.yucheng.smarthealthpro.customchart.utils.MPPointD;
import com.yucheng.smarthealthpro.customchart.utils.MPPointF;
import com.yucheng.smarthealthpro.customchart.utils.Transformer;
import com.yucheng.smarthealthpro.customchart.utils.Utils;
import com.yucheng.smarthealthpro.customchart.utils.ViewPortHandler;
import java.util.List;

/* loaded from: classes4.dex */
public class XAxisRenderer extends AxisRenderer {
    protected RectF mGridClippingRect;
    protected RectF mLimitLineClippingRect;
    private Path mLimitLinePath;
    float[] mLimitLineSegmentsBuffer;
    protected float[] mRenderGridLinesBuffer;
    protected Path mRenderGridLinesPath;
    protected float[] mRenderLimitLinesBuffer;
    protected XAxis mXAxis;

    public XAxisRenderer(ViewPortHandler viewPortHandler, XAxis xAxis, Transformer trans) {
        super(viewPortHandler, trans, xAxis);
        this.mRenderGridLinesPath = new Path();
        this.mRenderGridLinesBuffer = new float[2];
        this.mGridClippingRect = new RectF();
        this.mRenderLimitLinesBuffer = new float[2];
        this.mLimitLineClippingRect = new RectF();
        this.mLimitLineSegmentsBuffer = new float[4];
        this.mLimitLinePath = new Path();
        this.mXAxis = xAxis;
        this.mAxisLabelPaint.setColor(ViewCompat.MEASURED_STATE_MASK);
        this.mAxisLabelPaint.setTextAlign(Paint.Align.CENTER);
        this.mAxisLabelPaint.setTextSize(Utils.convertDpToPixel(10.0f));
    }

    protected void setupGridPaint() {
        this.mGridPaint.setColor(this.mXAxis.getGridColor());
        this.mGridPaint.setStrokeWidth(this.mXAxis.getGridLineWidth());
        this.mGridPaint.setPathEffect(this.mXAxis.getGridDashPathEffect());
    }

    @Override // com.yucheng.smarthealthpro.customchart.renderer.AxisRenderer
    public void computeAxis(float min, float max, boolean inverted) {
        float f2;
        double d2;
        if (this.mViewPortHandler.contentWidth() > 10.0f && !this.mViewPortHandler.isFullyZoomedOutX()) {
            MPPointD valuesByTouchPoint = this.mTrans.getValuesByTouchPoint(this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentTop());
            MPPointD valuesByTouchPoint2 = this.mTrans.getValuesByTouchPoint(this.mViewPortHandler.contentRight(), this.mViewPortHandler.contentTop());
            if (inverted) {
                f2 = (float) valuesByTouchPoint2.x;
                d2 = valuesByTouchPoint.x;
            } else {
                f2 = (float) valuesByTouchPoint.x;
                d2 = valuesByTouchPoint2.x;
            }
            MPPointD.recycleInstance(valuesByTouchPoint);
            MPPointD.recycleInstance(valuesByTouchPoint2);
            min = f2;
            max = (float) d2;
        }
        computeAxisValues(min, max);
    }

    @Override // com.yucheng.smarthealthpro.customchart.renderer.AxisRenderer
    protected void computeAxisValues(float min, float max) {
        super.computeAxisValues(min, max);
        computeSize();
    }

    protected void computeSize() {
        String longestLabel = this.mXAxis.getLongestLabel();
        this.mAxisLabelPaint.setTypeface(this.mXAxis.getTypeface());
        this.mAxisLabelPaint.setTextSize(this.mXAxis.getTextSize());
        FSize fSizeCalcTextSize = Utils.calcTextSize(this.mAxisLabelPaint, longestLabel);
        float f2 = fSizeCalcTextSize.width;
        float fCalcTextHeight = Utils.calcTextHeight(this.mAxisLabelPaint, "Q");
        FSize sizeOfRotatedRectangleByDegrees = Utils.getSizeOfRotatedRectangleByDegrees(f2, fCalcTextHeight, this.mXAxis.getLabelRotationAngle());
        this.mXAxis.mLabelWidth = Math.round(f2);
        this.mXAxis.mLabelHeight = Math.round(fCalcTextHeight);
        this.mXAxis.mLabelRotatedWidth = Math.round(sizeOfRotatedRectangleByDegrees.width);
        this.mXAxis.mLabelRotatedHeight = Math.round(sizeOfRotatedRectangleByDegrees.height);
        FSize.recycleInstance(sizeOfRotatedRectangleByDegrees);
        FSize.recycleInstance(fSizeCalcTextSize);
    }

    @Override // com.yucheng.smarthealthpro.customchart.renderer.AxisRenderer
    public void renderAxisLabels(Canvas c2) {
        if (this.mXAxis.isEnabled() && this.mXAxis.isDrawLabelsEnabled()) {
            float yOffset = this.mXAxis.getYOffset();
            this.mAxisLabelPaint.setTypeface(this.mXAxis.getTypeface());
            this.mAxisLabelPaint.setTextSize(this.mXAxis.getTextSize());
            this.mAxisLabelPaint.setColor(this.mXAxis.getTextColor());
            MPPointF mPPointF = MPPointF.getInstance(0.0f, 0.0f);
            if (this.mXAxis.getPosition() == XAxis.XAxisPosition.TOP) {
                mPPointF.x = 0.5f;
                mPPointF.y = 1.0f;
                drawLabels(c2, this.mViewPortHandler.contentTop() - yOffset, mPPointF);
            } else if (this.mXAxis.getPosition() == XAxis.XAxisPosition.TOP_INSIDE) {
                mPPointF.x = 0.5f;
                mPPointF.y = 1.0f;
                drawLabels(c2, this.mViewPortHandler.contentTop() + yOffset + this.mXAxis.mLabelRotatedHeight, mPPointF);
            } else if (this.mXAxis.getPosition() == XAxis.XAxisPosition.BOTTOM) {
                mPPointF.x = 0.5f;
                mPPointF.y = 0.0f;
                drawLabels(c2, this.mViewPortHandler.contentBottom() + yOffset, mPPointF);
            } else if (this.mXAxis.getPosition() == XAxis.XAxisPosition.BOTTOM_INSIDE) {
                mPPointF.x = 0.5f;
                mPPointF.y = 0.0f;
                drawLabels(c2, (this.mViewPortHandler.contentBottom() - yOffset) - this.mXAxis.mLabelRotatedHeight, mPPointF);
            } else {
                mPPointF.x = 0.5f;
                mPPointF.y = 1.0f;
                drawLabels(c2, this.mViewPortHandler.contentTop() - yOffset, mPPointF);
                mPPointF.x = 0.5f;
                mPPointF.y = 0.0f;
                drawLabels(c2, this.mViewPortHandler.contentBottom() + yOffset, mPPointF);
            }
            MPPointF.recycleInstance(mPPointF);
        }
    }

    @Override // com.yucheng.smarthealthpro.customchart.renderer.AxisRenderer
    public void renderAxisLine(Canvas c2) {
        if (this.mXAxis.isDrawAxisLineEnabled() && this.mXAxis.isEnabled()) {
            this.mAxisLinePaint.setColor(this.mXAxis.getAxisLineColor());
            this.mAxisLinePaint.setStrokeWidth(this.mXAxis.getAxisLineWidth());
            this.mAxisLinePaint.setPathEffect(this.mXAxis.getAxisLineDashPathEffect());
            if (this.mXAxis.getPosition() == XAxis.XAxisPosition.TOP || this.mXAxis.getPosition() == XAxis.XAxisPosition.TOP_INSIDE || this.mXAxis.getPosition() == XAxis.XAxisPosition.BOTH_SIDED) {
                c2.drawLine(this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentTop(), this.mViewPortHandler.contentRight(), this.mViewPortHandler.contentTop(), this.mAxisLinePaint);
            }
            if (this.mXAxis.getPosition() == XAxis.XAxisPosition.BOTTOM || this.mXAxis.getPosition() == XAxis.XAxisPosition.BOTTOM_INSIDE || this.mXAxis.getPosition() == XAxis.XAxisPosition.BOTH_SIDED) {
                Paint paint = new Paint();
                paint.reset();
                paint.setAntiAlias(true);
                paint.setStyle(Paint.Style.FILL);
                paint.setStrokeWidth(2.0f);
                paint.setColor(Color.parseColor("#8E8E93"));
                paint.setPathEffect(new DashPathEffect(new float[]{5.0f, 5.0f, 5.0f, 5.0f}, 2.0f));
                c2.drawLine(this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentBottom(), this.mViewPortHandler.contentRight(), this.mViewPortHandler.contentBottom(), this.mAxisLinePaint);
            }
        }
    }

    protected void drawLabels(Canvas c2, float pos, MPPointF anchor) {
        float labelRotationAngle = this.mXAxis.getLabelRotationAngle();
        boolean zIsCenterAxisLabelsEnabled = this.mXAxis.isCenterAxisLabelsEnabled();
        int i2 = this.mXAxis.mEntryCount * 2;
        float[] fArr = new float[i2];
        for (int i3 = 0; i3 < i2; i3 += 2) {
            if (zIsCenterAxisLabelsEnabled) {
                fArr[i3] = this.mXAxis.mCenteredEntries[i3 / 2];
            } else {
                fArr[i3] = this.mXAxis.mEntries[i3 / 2];
            }
        }
        this.mTrans.pointValuesToPixel(fArr);
        for (int i4 = 0; i4 < i2; i4 += 2) {
            float fCalcTextWidth = fArr[i4];
            if (this.mViewPortHandler.isInBoundsX(fCalcTextWidth)) {
                int i5 = i4 / 2;
                String formattedValue = this.mXAxis.getValueFormatter().getFormattedValue(this.mXAxis.mEntries[i5], this.mXAxis);
                if (this.mXAxis.isAvoidFirstLastClippingEnabled()) {
                    if (i5 == this.mXAxis.mEntryCount - 1 && this.mXAxis.mEntryCount > 1) {
                        float fCalcTextWidth2 = Utils.calcTextWidth(this.mAxisLabelPaint, formattedValue);
                        if (fCalcTextWidth2 > this.mViewPortHandler.offsetRight() * 2.0f && fCalcTextWidth + fCalcTextWidth2 > this.mViewPortHandler.getChartWidth()) {
                            fCalcTextWidth -= fCalcTextWidth2 / 2.0f;
                        }
                    } else if (i4 == 0) {
                        fCalcTextWidth += Utils.calcTextWidth(this.mAxisLabelPaint, formattedValue) / 2.0f;
                    }
                }
                drawLabel(c2, formattedValue, fCalcTextWidth, pos, anchor, labelRotationAngle);
            }
        }
    }

    protected void drawLabel(Canvas c2, String formattedLabel, float x, float y, MPPointF anchor, float angleDegrees) {
        Utils.drawXAxisValue(c2, formattedLabel, x, y, this.mAxisLabelPaint, anchor, angleDegrees);
    }

    @Override // com.yucheng.smarthealthpro.customchart.renderer.AxisRenderer
    public void renderGridLines(Canvas c2) {
        if (this.mXAxis.isDrawGridLinesEnabled() && this.mXAxis.isEnabled()) {
            int iSave = c2.save();
            c2.clipRect(getGridClippingRect());
            if (this.mRenderGridLinesBuffer.length != this.mAxis.mEntryCount * 2) {
                this.mRenderGridLinesBuffer = new float[this.mXAxis.mEntryCount * 2];
            }
            float[] fArr = this.mRenderGridLinesBuffer;
            for (int i2 = 0; i2 < fArr.length; i2 += 2) {
                int i3 = i2 / 2;
                fArr[i2] = this.mXAxis.mEntries[i3];
                fArr[i2 + 1] = this.mXAxis.mEntries[i3];
            }
            this.mTrans.pointValuesToPixel(fArr);
            setupGridPaint();
            Path path = this.mRenderGridLinesPath;
            path.reset();
            for (int i4 = 0; i4 < fArr.length; i4 += 2) {
                drawGridLine(c2, fArr[i4], fArr[i4 + 1], path);
            }
            c2.restoreToCount(iSave);
        }
    }

    public RectF getGridClippingRect() {
        this.mGridClippingRect.set(this.mViewPortHandler.getContentRect());
        this.mGridClippingRect.inset(-this.mAxis.getGridLineWidth(), 0.0f);
        return this.mGridClippingRect;
    }

    protected void drawGridLine(Canvas c2, float x, float y, Path gridLinePath) {
        gridLinePath.moveTo(x, this.mViewPortHandler.contentBottom());
        gridLinePath.lineTo(x, this.mViewPortHandler.contentTop());
        c2.drawPath(gridLinePath, this.mGridPaint);
        gridLinePath.reset();
    }

    @Override // com.yucheng.smarthealthpro.customchart.renderer.AxisRenderer
    public void renderLimitLines(Canvas c2) {
        List<LimitLine> limitLines = this.mXAxis.getLimitLines();
        if (limitLines == null || limitLines.size() <= 0) {
            return;
        }
        float[] fArr = this.mRenderLimitLinesBuffer;
        fArr[0] = 0.0f;
        fArr[1] = 0.0f;
        for (int i2 = 0; i2 < limitLines.size(); i2++) {
            LimitLine limitLine = limitLines.get(i2);
            if (limitLine.isEnabled()) {
                int iSave = c2.save();
                this.mLimitLineClippingRect.set(this.mViewPortHandler.getContentRect());
                this.mLimitLineClippingRect.inset(-limitLine.getLineWidth(), 0.0f);
                c2.clipRect(this.mLimitLineClippingRect);
                fArr[0] = limitLine.getLimit();
                fArr[1] = 0.0f;
                this.mTrans.pointValuesToPixel(fArr);
                renderLimitLineLine(c2, limitLine, fArr);
                renderLimitLineLabel(c2, limitLine, fArr, limitLine.getYOffset() + 2.0f);
                c2.restoreToCount(iSave);
            }
        }
    }

    public void renderLimitLineLine(Canvas c2, LimitLine limitLine, float[] position) {
        float[] fArr = this.mLimitLineSegmentsBuffer;
        fArr[0] = position[0];
        fArr[1] = this.mViewPortHandler.contentTop();
        float[] fArr2 = this.mLimitLineSegmentsBuffer;
        fArr2[2] = position[0];
        fArr2[3] = this.mViewPortHandler.contentBottom();
        this.mLimitLinePath.reset();
        Path path = this.mLimitLinePath;
        float[] fArr3 = this.mLimitLineSegmentsBuffer;
        path.moveTo(fArr3[0], fArr3[1]);
        Path path2 = this.mLimitLinePath;
        float[] fArr4 = this.mLimitLineSegmentsBuffer;
        path2.lineTo(fArr4[2], fArr4[3]);
        this.mLimitLinePaint.setStyle(Paint.Style.STROKE);
        this.mLimitLinePaint.setColor(limitLine.getLineColor());
        this.mLimitLinePaint.setStrokeWidth(limitLine.getLineWidth());
        this.mLimitLinePaint.setPathEffect(limitLine.getDashPathEffect());
        c2.drawPath(this.mLimitLinePath, this.mLimitLinePaint);
    }

    public void renderLimitLineLabel(Canvas c2, LimitLine limitLine, float[] position, float yOffset) {
        String label = limitLine.getLabel();
        if (label == null || label.equals("")) {
            return;
        }
        this.mLimitLinePaint.setStyle(limitLine.getTextStyle());
        this.mLimitLinePaint.setPathEffect(null);
        this.mLimitLinePaint.setColor(limitLine.getTextColor());
        this.mLimitLinePaint.setStrokeWidth(0.5f);
        this.mLimitLinePaint.setTextSize(limitLine.getTextSize());
        float lineWidth = limitLine.getLineWidth() + limitLine.getXOffset();
        LimitLine.LimitLabelPosition labelPosition = limitLine.getLabelPosition();
        if (labelPosition == LimitLine.LimitLabelPosition.RIGHT_TOP) {
            float fCalcTextHeight = Utils.calcTextHeight(this.mLimitLinePaint, label);
            this.mLimitLinePaint.setTextAlign(Paint.Align.LEFT);
            c2.drawText(label, position[0] + lineWidth, this.mViewPortHandler.contentTop() + yOffset + fCalcTextHeight, this.mLimitLinePaint);
        } else if (labelPosition == LimitLine.LimitLabelPosition.RIGHT_BOTTOM) {
            this.mLimitLinePaint.setTextAlign(Paint.Align.LEFT);
            c2.drawText(label, position[0] + lineWidth, this.mViewPortHandler.contentBottom() - yOffset, this.mLimitLinePaint);
        } else if (labelPosition == LimitLine.LimitLabelPosition.LEFT_TOP) {
            this.mLimitLinePaint.setTextAlign(Paint.Align.RIGHT);
            c2.drawText(label, position[0] - lineWidth, this.mViewPortHandler.contentTop() + yOffset + Utils.calcTextHeight(this.mLimitLinePaint, label), this.mLimitLinePaint);
        } else {
            this.mLimitLinePaint.setTextAlign(Paint.Align.RIGHT);
            c2.drawText(label, position[0] - lineWidth, this.mViewPortHandler.contentBottom() - yOffset, this.mLimitLinePaint);
        }
    }
}
