package com.yucheng.smarthealthpro.customchart.renderer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import com.tencent.connect.common.Constants;
import com.yucheng.smarthealthpro.customchart.animation.ChartAnimator;
import com.yucheng.smarthealthpro.customchart.buffer.BarBuffer;
import com.yucheng.smarthealthpro.customchart.buffer.HorizontalBarBuffer;
import com.yucheng.smarthealthpro.customchart.data.BarData;
import com.yucheng.smarthealthpro.customchart.data.BarEntry;
import com.yucheng.smarthealthpro.customchart.formatter.IValueFormatter;
import com.yucheng.smarthealthpro.customchart.highlight.Highlight;
import com.yucheng.smarthealthpro.customchart.interfaces.dataprovider.BarDataProvider;
import com.yucheng.smarthealthpro.customchart.interfaces.dataprovider.ChartInterface;
import com.yucheng.smarthealthpro.customchart.interfaces.datasets.IBarDataSet;
import com.yucheng.smarthealthpro.customchart.utils.Fill;
import com.yucheng.smarthealthpro.customchart.utils.MPPointF;
import com.yucheng.smarthealthpro.customchart.utils.Transformer;
import com.yucheng.smarthealthpro.customchart.utils.Utils;
import com.yucheng.smarthealthpro.customchart.utils.ViewPortHandler;
import java.util.List;

/* loaded from: classes4.dex */
public class HorizontalBarChartRenderer extends BarChartRenderer {
    private RectF mBarShadowRectBuffer;

    public HorizontalBarChartRenderer(BarDataProvider chart, ChartAnimator animator, ViewPortHandler viewPortHandler, Context context) {
        super(chart, animator, viewPortHandler, context);
        this.mBarShadowRectBuffer = new RectF();
        this.mValuePaint.setTextAlign(Paint.Align.LEFT);
    }

    @Override // com.yucheng.smarthealthpro.customchart.renderer.BarChartRenderer, com.yucheng.smarthealthpro.customchart.renderer.DataRenderer
    public void initBuffers() {
        BarData barData = this.mChart.getBarData();
        this.mBarBuffers = new HorizontalBarBuffer[barData.getDataSetCount()];
        for (int i2 = 0; i2 < this.mBarBuffers.length; i2++) {
            IBarDataSet iBarDataSet = (IBarDataSet) barData.getDataSetByIndex(i2);
            this.mBarBuffers[i2] = new HorizontalBarBuffer(iBarDataSet.getEntryCount() * 4 * (iBarDataSet.isStacked() ? iBarDataSet.getStackSize() : 1), barData.getDataSetCount(), iBarDataSet.isStacked());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.yucheng.smarthealthpro.customchart.renderer.BarChartRenderer
    protected void drawDataSet(Canvas c2, IBarDataSet dataSet, int index) {
        Transformer transformer = this.mChart.getTransformer(dataSet.getAxisDependency());
        this.mBarBorderPaint.setColor(dataSet.getBarBorderColor());
        this.mBarBorderPaint.setStrokeWidth(Utils.convertDpToPixel(dataSet.getBarBorderWidth()));
        int i2 = 0;
        boolean z = dataSet.getBarBorderWidth() > 0.0f;
        float phaseX = this.mAnimator.getPhaseX();
        float phaseY = this.mAnimator.getPhaseY();
        if (this.mChart.isDrawBarShadowEnabled()) {
            this.mShadowPaint.setColor(dataSet.getBarShadowColor());
            float barWidth = this.mChart.getBarData().getBarWidth() / 2.0f;
            int iMin = Math.min((int) Math.ceil(dataSet.getEntryCount() * phaseX), dataSet.getEntryCount());
            for (int i3 = 0; i3 < iMin; i3++) {
                float x = ((BarEntry) dataSet.getEntryForIndex(i3)).getX();
                this.mBarShadowRectBuffer.top = x - barWidth;
                this.mBarShadowRectBuffer.bottom = x + barWidth;
                transformer.rectValueToPixel(this.mBarShadowRectBuffer);
                if (this.mViewPortHandler.isInBoundsTop(this.mBarShadowRectBuffer.bottom)) {
                    if (!this.mViewPortHandler.isInBoundsBottom(this.mBarShadowRectBuffer.top)) {
                        break;
                    }
                    this.mBarShadowRectBuffer.left = this.mViewPortHandler.contentLeft();
                    this.mBarShadowRectBuffer.right = this.mViewPortHandler.contentRight();
                    c2.drawRect(this.mBarShadowRectBuffer, this.mShadowPaint);
                }
            }
        }
        BarBuffer barBuffer = this.mBarBuffers[index];
        barBuffer.setPhases(phaseX, phaseY);
        barBuffer.setDataSet(index);
        barBuffer.setInverted(this.mChart.isInverted(dataSet.getAxisDependency()));
        barBuffer.setBarWidth(this.mChart.getBarData().getBarWidth());
        barBuffer.feed(dataSet);
        transformer.pointValuesToPixel(barBuffer.buffer);
        boolean z2 = (dataSet.getFills() == null || dataSet.getFills().isEmpty()) ? false : true;
        boolean z3 = dataSet.getColors().size() == 1;
        boolean zIsInverted = this.mChart.isInverted(dataSet.getAxisDependency());
        if (z3) {
            this.mRenderPaint.setColor(dataSet.getColor());
        }
        int i4 = 0;
        while (i2 < barBuffer.size()) {
            int i5 = i2 + 3;
            if (!this.mViewPortHandler.isInBoundsTop(barBuffer.buffer[i5])) {
                return;
            }
            int i6 = i2 + 1;
            if (this.mViewPortHandler.isInBoundsBottom(barBuffer.buffer[i6])) {
                if (!z3) {
                    this.mRenderPaint.setColor(dataSet.getColor(i2 / 4));
                }
                if (z2) {
                    dataSet.getFill(i4).fillRect(c2, this.mRenderPaint, barBuffer.buffer[i2], barBuffer.buffer[i6], barBuffer.buffer[i2 + 2], barBuffer.buffer[i5], zIsInverted ? Fill.Direction.LEFT : Fill.Direction.RIGHT);
                } else {
                    c2.drawRect(barBuffer.buffer[i2], barBuffer.buffer[i6], barBuffer.buffer[i2 + 2], barBuffer.buffer[i5], this.mRenderPaint);
                }
                if (z) {
                    c2.drawRect(barBuffer.buffer[i2], barBuffer.buffer[i6], barBuffer.buffer[i2 + 2], barBuffer.buffer[i5], this.mBarBorderPaint);
                }
            }
            i2 += 4;
            i4++;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.yucheng.smarthealthpro.customchart.renderer.BarChartRenderer, com.yucheng.smarthealthpro.customchart.renderer.DataRenderer
    public void drawValues(Canvas c2) {
        List list;
        float f2;
        MPPointF mPPointF;
        int i2;
        float[] fArr;
        float f3;
        int i3;
        float[] fArr2;
        float f4;
        float f5;
        BarEntry barEntry;
        List list2;
        float f6;
        float f7;
        float f8;
        int i4;
        float f9;
        float f10;
        MPPointF mPPointF2;
        IValueFormatter iValueFormatter;
        float f11;
        BarBuffer barBuffer;
        if (isDrawingValuesAllowed(this.mChart)) {
            List dataSets = this.mChart.getBarData().getDataSets();
            float fConvertDpToPixel = Utils.convertDpToPixel(5.0f);
            boolean zIsDrawValueAboveBarEnabled = this.mChart.isDrawValueAboveBarEnabled();
            int i5 = 0;
            while (i5 < this.mChart.getBarData().getDataSetCount()) {
                IBarDataSet iBarDataSet = (IBarDataSet) dataSets.get(i5);
                if (shouldDrawValues(iBarDataSet)) {
                    boolean zIsInverted = this.mChart.isInverted(iBarDataSet.getAxisDependency());
                    applyValueTextStyle(iBarDataSet);
                    float f12 = 2.0f;
                    float fCalcTextHeight = Utils.calcTextHeight(this.mValuePaint, Constants.VIA_REPORT_TYPE_SHARE_TO_QQ) / 2.0f;
                    IValueFormatter valueFormatter = iBarDataSet.getValueFormatter();
                    BarBuffer barBuffer2 = this.mBarBuffers[i5];
                    float phaseY = this.mAnimator.getPhaseY();
                    MPPointF mPPointF3 = MPPointF.getInstance(iBarDataSet.getIconsOffset());
                    mPPointF3.x = Utils.convertDpToPixel(mPPointF3.x);
                    mPPointF3.y = Utils.convertDpToPixel(mPPointF3.y);
                    if (iBarDataSet.isStacked()) {
                        list = dataSets;
                        f2 = fConvertDpToPixel;
                        mPPointF = mPPointF3;
                        Transformer transformer = this.mChart.getTransformer(iBarDataSet.getAxisDependency());
                        int i6 = 0;
                        int length = 0;
                        while (i6 < iBarDataSet.getEntryCount() * this.mAnimator.getPhaseX()) {
                            BarEntry barEntry2 = (BarEntry) iBarDataSet.getEntryForIndex(i6);
                            int valueTextColor = iBarDataSet.getValueTextColor(i6);
                            float[] yVals = barEntry2.getYVals();
                            if (yVals == null) {
                                int i7 = length + 1;
                                if (!this.mViewPortHandler.isInBoundsTop(barBuffer2.buffer[i7])) {
                                    break;
                                }
                                if (this.mViewPortHandler.isInBoundsX(barBuffer2.buffer[length]) && this.mViewPortHandler.isInBoundsBottom(barBuffer2.buffer[i7])) {
                                    String formattedValue = valueFormatter.getFormattedValue(barEntry2.getY(), barEntry2, i5, this.mViewPortHandler);
                                    float fCalcTextWidth = Utils.calcTextWidth(this.mValuePaint, formattedValue);
                                    float f13 = zIsDrawValueAboveBarEnabled ? f2 : -(fCalcTextWidth + f2);
                                    float f14 = zIsDrawValueAboveBarEnabled ? -(fCalcTextWidth + f2) : f2;
                                    if (zIsInverted) {
                                        f13 = (-f13) - fCalcTextWidth;
                                        f14 = (-f14) - fCalcTextWidth;
                                    }
                                    float f15 = f13;
                                    float f16 = f14;
                                    if (iBarDataSet.isDrawValuesEnabled()) {
                                        i2 = i6;
                                        fArr = yVals;
                                        barEntry = barEntry2;
                                        drawValue(c2, formattedValue, barBuffer2.buffer[length + 2] + (barEntry2.getY() >= 0.0f ? f15 : f16), barBuffer2.buffer[i7] + fCalcTextHeight, valueTextColor);
                                    } else {
                                        barEntry = barEntry2;
                                        i2 = i6;
                                        fArr = yVals;
                                    }
                                    if (barEntry.getIcon() != null && iBarDataSet.isDrawIconsEnabled()) {
                                        Drawable icon = barEntry.getIcon();
                                        float f17 = barBuffer2.buffer[length + 2];
                                        if (barEntry.getY() < 0.0f) {
                                            f15 = f16;
                                        }
                                        Utils.drawImage(c2, icon, (int) (f17 + f15 + mPPointF.x), (int) (barBuffer2.buffer[i7] + mPPointF.y), icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
                                    }
                                }
                            } else {
                                i2 = i6;
                                fArr = yVals;
                                int length2 = fArr.length * 2;
                                float[] fArr3 = new float[length2];
                                float f18 = -barEntry2.getNegativeSum();
                                float f19 = 0.0f;
                                int i8 = 0;
                                int i9 = 0;
                                while (i8 < length2) {
                                    float f20 = fArr[i9];
                                    if (f20 == 0.0f && (f19 == 0.0f || f18 == 0.0f)) {
                                        float f21 = f18;
                                        f18 = f20;
                                        f5 = f21;
                                    } else if (f20 >= 0.0f) {
                                        f19 += f20;
                                        f5 = f18;
                                        f18 = f19;
                                    } else {
                                        f5 = f18 - f20;
                                    }
                                    fArr3[i8] = f18 * phaseY;
                                    i8 += 2;
                                    i9++;
                                    f18 = f5;
                                }
                                transformer.pointValuesToPixel(fArr3);
                                int i10 = 0;
                                while (i10 < length2) {
                                    float f22 = fArr[i10 / 2];
                                    String formattedValue2 = valueFormatter.getFormattedValue(f22, barEntry2, i5, this.mViewPortHandler);
                                    float fCalcTextWidth2 = Utils.calcTextWidth(this.mValuePaint, formattedValue2);
                                    float f23 = zIsDrawValueAboveBarEnabled ? f2 : -(fCalcTextWidth2 + f2);
                                    int i11 = length2;
                                    float f24 = zIsDrawValueAboveBarEnabled ? -(fCalcTextWidth2 + f2) : f2;
                                    if (zIsInverted) {
                                        f23 = (-f23) - fCalcTextWidth2;
                                        f24 = (-f24) - fCalcTextWidth2;
                                    }
                                    boolean z = (f22 == 0.0f && f18 == 0.0f && f19 > 0.0f) || f22 < 0.0f;
                                    float f25 = fArr3[i10];
                                    if (z) {
                                        f23 = f24;
                                    }
                                    float f26 = f25 + f23;
                                    float f27 = (barBuffer2.buffer[length + 1] + barBuffer2.buffer[length + 3]) / 2.0f;
                                    if (!this.mViewPortHandler.isInBoundsTop(f27)) {
                                        break;
                                    }
                                    if (this.mViewPortHandler.isInBoundsX(f26) && this.mViewPortHandler.isInBoundsBottom(f27)) {
                                        if (iBarDataSet.isDrawValuesEnabled()) {
                                            f3 = f27;
                                            i3 = i10;
                                            fArr2 = fArr3;
                                            f4 = f26;
                                            drawValue(c2, formattedValue2, f26, f27 + fCalcTextHeight, valueTextColor);
                                        } else {
                                            f3 = f27;
                                            i3 = i10;
                                            fArr2 = fArr3;
                                            f4 = f26;
                                        }
                                        if (barEntry2.getIcon() != null && iBarDataSet.isDrawIconsEnabled()) {
                                            Drawable icon2 = barEntry2.getIcon();
                                            Utils.drawImage(c2, icon2, (int) (f4 + mPPointF.x), (int) (f3 + mPPointF.y), icon2.getIntrinsicWidth(), icon2.getIntrinsicHeight());
                                        }
                                    } else {
                                        i3 = i10;
                                        fArr2 = fArr3;
                                    }
                                    i10 = i3 + 2;
                                    length2 = i11;
                                    fArr3 = fArr2;
                                }
                            }
                            length = fArr == null ? length + 4 : length + (fArr.length * 4);
                            i6 = i2 + 1;
                        }
                    } else {
                        int i12 = 0;
                        while (i12 < barBuffer2.buffer.length * this.mAnimator.getPhaseX()) {
                            int i13 = i12 + 1;
                            float f28 = (barBuffer2.buffer[i13] + barBuffer2.buffer[i12 + 3]) / f12;
                            if (!this.mViewPortHandler.isInBoundsTop(barBuffer2.buffer[i13])) {
                                break;
                            }
                            if (this.mViewPortHandler.isInBoundsX(barBuffer2.buffer[i12]) && this.mViewPortHandler.isInBoundsBottom(barBuffer2.buffer[i13])) {
                                BarEntry barEntry3 = (BarEntry) iBarDataSet.getEntryForIndex(i12 / 4);
                                float y = barEntry3.getY();
                                String formattedValue3 = valueFormatter.getFormattedValue(y, barEntry3, i5, this.mViewPortHandler);
                                MPPointF mPPointF4 = mPPointF3;
                                float fCalcTextWidth3 = Utils.calcTextWidth(this.mValuePaint, formattedValue3);
                                float f29 = zIsDrawValueAboveBarEnabled ? fConvertDpToPixel : -(fCalcTextWidth3 + fConvertDpToPixel);
                                IValueFormatter iValueFormatter2 = valueFormatter;
                                if (zIsDrawValueAboveBarEnabled) {
                                    f6 = -(fCalcTextWidth3 + fConvertDpToPixel);
                                    list2 = dataSets;
                                } else {
                                    list2 = dataSets;
                                    f6 = fConvertDpToPixel;
                                }
                                int i14 = i12 + 2;
                                f7 = fConvertDpToPixel;
                                float f30 = f6 - (barBuffer2.buffer[i14] - barBuffer2.buffer[i12]);
                                if (zIsInverted) {
                                    f29 = (-f29) - fCalcTextWidth3;
                                    f30 = (-f30) - fCalcTextWidth3;
                                }
                                float f31 = f29;
                                float f32 = f30;
                                if (iBarDataSet.isDrawValuesEnabled()) {
                                    float f33 = barBuffer2.buffer[i14];
                                    float f34 = y >= 0.0f ? f31 : f32;
                                    f8 = y;
                                    i4 = i12;
                                    f9 = f31;
                                    mPPointF2 = mPPointF4;
                                    f11 = f32;
                                    barBuffer = barBuffer2;
                                    f10 = fCalcTextHeight;
                                    iValueFormatter = iValueFormatter2;
                                    drawValue(c2, formattedValue3, f34 + f33, f28 + fCalcTextHeight, iBarDataSet.getValueTextColor(i12 / 2));
                                } else {
                                    f8 = y;
                                    i4 = i12;
                                    f9 = f31;
                                    f10 = fCalcTextHeight;
                                    mPPointF2 = mPPointF4;
                                    iValueFormatter = iValueFormatter2;
                                    f11 = f32;
                                    barBuffer = barBuffer2;
                                }
                                if (barEntry3.getIcon() != null && iBarDataSet.isDrawIconsEnabled()) {
                                    Drawable icon3 = barEntry3.getIcon();
                                    float f35 = barBuffer.buffer[i14];
                                    if (f8 < 0.0f) {
                                        f9 = f11;
                                    }
                                    Utils.drawImage(c2, icon3, (int) (f35 + f9 + mPPointF2.x), (int) (f28 + mPPointF2.y), icon3.getIntrinsicWidth(), icon3.getIntrinsicHeight());
                                }
                            } else {
                                i4 = i12;
                                list2 = dataSets;
                                f7 = fConvertDpToPixel;
                                f10 = fCalcTextHeight;
                                mPPointF2 = mPPointF3;
                                barBuffer = barBuffer2;
                                iValueFormatter = valueFormatter;
                            }
                            i12 = i4 + 4;
                            mPPointF3 = mPPointF2;
                            barBuffer2 = barBuffer;
                            valueFormatter = iValueFormatter;
                            dataSets = list2;
                            fConvertDpToPixel = f7;
                            fCalcTextHeight = f10;
                            f12 = 2.0f;
                        }
                        list = dataSets;
                        f2 = fConvertDpToPixel;
                        mPPointF = mPPointF3;
                    }
                    MPPointF.recycleInstance(mPPointF);
                } else {
                    list = dataSets;
                    f2 = fConvertDpToPixel;
                }
                i5++;
                dataSets = list;
                fConvertDpToPixel = f2;
            }
        }
    }

    protected void drawValue(Canvas c2, String valueText, float x, float y, int color) {
        this.mValuePaint.setColor(color);
        c2.drawText(valueText, x, y, this.mValuePaint);
    }

    @Override // com.yucheng.smarthealthpro.customchart.renderer.BarChartRenderer
    protected void prepareBarHighlight(float x, float y1, float y2, float barWidthHalf, Transformer trans) {
        this.mBarRect.set(y1, x - barWidthHalf, y2, x + barWidthHalf);
        trans.rectToPixelPhaseHorizontal(this.mBarRect, this.mAnimator.getPhaseY());
    }

    @Override // com.yucheng.smarthealthpro.customchart.renderer.BarChartRenderer
    protected void setHighlightDrawPos(Highlight high, RectF bar) {
        high.setDraw(bar.centerY(), bar.right);
    }

    @Override // com.yucheng.smarthealthpro.customchart.renderer.DataRenderer
    protected boolean isDrawingValuesAllowed(ChartInterface chart) {
        return ((float) chart.getData().getEntryCount()) < ((float) chart.getMaxVisibleCount()) * this.mViewPortHandler.getScaleY();
    }
}
