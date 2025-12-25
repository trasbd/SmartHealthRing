package com.yucheng.smarthealthpro.customchart.renderer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import com.tencent.connect.common.Constants;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.customchart.animation.ChartAnimator;
import com.yucheng.smarthealthpro.customchart.buffer.BarBuffer;
import com.yucheng.smarthealthpro.customchart.data.BarData;
import com.yucheng.smarthealthpro.customchart.data.BarEntry;
import com.yucheng.smarthealthpro.customchart.data.Entry;
import com.yucheng.smarthealthpro.customchart.formatter.IValueFormatter;
import com.yucheng.smarthealthpro.customchart.highlight.Highlight;
import com.yucheng.smarthealthpro.customchart.interfaces.dataprovider.BarDataProvider;
import com.yucheng.smarthealthpro.customchart.interfaces.datasets.IBarDataSet;
import com.yucheng.smarthealthpro.customchart.utils.Fill;
import com.yucheng.smarthealthpro.customchart.utils.MPPointF;
import com.yucheng.smarthealthpro.customchart.utils.Transformer;
import com.yucheng.smarthealthpro.customchart.utils.Utils;
import com.yucheng.smarthealthpro.customchart.utils.ViewPortHandler;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public class BarChartStepRenderer extends LineScatterCandleRadarRenderer {
    private Context context;
    public boolean isRoundBar;
    protected Paint mBarBorderPaint;
    protected BarBuffer[] mBarBuffers;
    protected RectF mBarRect;
    private RectF mBarShadowRectBuffer;
    protected BarDataProvider mChart;
    protected Paint mShadowPaint;

    @Override // com.yucheng.smarthealthpro.customchart.renderer.DataRenderer
    public void drawExtras(Canvas c2) {
    }

    public BarChartStepRenderer(BarDataProvider chart, ChartAnimator animator, ViewPortHandler viewPortHandler, Context context) {
        super(animator, viewPortHandler);
        this.mBarRect = new RectF();
        this.isRoundBar = false;
        this.mBarShadowRectBuffer = new RectF();
        this.mChart = chart;
        this.context = context;
        this.mHighlightPaint = new Paint(1);
        this.mHighlightPaint.setStyle(Paint.Style.FILL);
        this.mHighlightPaint.setColor(Color.rgb(0, 0, 0));
        this.mHighlightPaint.setAlpha(120);
        Paint paint = new Paint(1);
        this.mShadowPaint = paint;
        paint.setStyle(Paint.Style.FILL);
        Paint paint2 = new Paint(1);
        this.mBarBorderPaint = paint2;
        paint2.setStyle(Paint.Style.STROKE);
    }

    @Override // com.yucheng.smarthealthpro.customchart.renderer.DataRenderer
    public void initBuffers() {
        BarData barData = this.mChart.getBarData();
        this.mBarBuffers = new BarBuffer[barData.getDataSetCount()];
        for (int i2 = 0; i2 < this.mBarBuffers.length; i2++) {
            IBarDataSet iBarDataSet = (IBarDataSet) barData.getDataSetByIndex(i2);
            this.mBarBuffers[i2] = new BarBuffer(iBarDataSet.getEntryCount() * 4 * (iBarDataSet.isStacked() ? iBarDataSet.getStackSize() : 1), barData.getDataSetCount(), iBarDataSet.isStacked());
        }
    }

    @Override // com.yucheng.smarthealthpro.customchart.renderer.DataRenderer
    public void drawData(Canvas c2) {
        BarData barData = this.mChart.getBarData();
        for (int i2 = 0; i2 < barData.getDataSetCount(); i2++) {
            IBarDataSet iBarDataSet = (IBarDataSet) barData.getDataSetByIndex(i2);
            if (iBarDataSet.isVisible()) {
                drawDataSet(c2, iBarDataSet, i2);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected void drawDataSet(Canvas c2, IBarDataSet dataSet, int index) {
        int i2;
        Transformer transformer = this.mChart.getTransformer(dataSet.getAxisDependency());
        this.mBarBorderPaint.setColor(dataSet.getBarBorderColor());
        this.mBarBorderPaint.setStrokeWidth(Utils.convertDpToPixel(dataSet.getBarBorderWidth()));
        int i3 = 0;
        boolean z = dataSet.getBarBorderWidth() > 0.0f;
        float phaseX = this.mAnimator.getPhaseX();
        float phaseY = this.mAnimator.getPhaseY();
        if (this.mChart.isDrawBarShadowEnabled()) {
            this.mShadowPaint.setColor(dataSet.getBarShadowColor());
            float barWidth = this.mChart.getBarData().getBarWidth() / 2.0f;
            int iMin = Math.min((int) Math.ceil(dataSet.getEntryCount() * phaseX), dataSet.getEntryCount());
            for (int i4 = 0; i4 < iMin; i4++) {
                float x = ((BarEntry) dataSet.getEntryForIndex(i4)).getX();
                this.mBarShadowRectBuffer.left = x - barWidth;
                this.mBarShadowRectBuffer.right = x + barWidth;
                transformer.rectValueToPixel(this.mBarShadowRectBuffer);
                if (this.mViewPortHandler.isInBoundsLeft(this.mBarShadowRectBuffer.right)) {
                    if (!this.mViewPortHandler.isInBoundsRight(this.mBarShadowRectBuffer.left)) {
                        break;
                    }
                    this.mBarShadowRectBuffer.top = this.mViewPortHandler.contentTop();
                    this.mBarShadowRectBuffer.bottom = this.mViewPortHandler.contentBottom();
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
            coloringLine(dataSet, this.mRenderPaint, c2.getWidth(), c2.getHeight(), Integer.valueOf(dataSet.getColor()));
        }
        int i5 = 0;
        while (i3 < barBuffer.size()) {
            int i6 = i3 + 2;
            if (!this.mViewPortHandler.isInBoundsLeft(barBuffer.buffer[i6])) {
                i2 = i5;
            } else {
                if (!this.mViewPortHandler.isInBoundsRight(barBuffer.buffer[i3])) {
                    return;
                }
                if (!z3) {
                    this.mRenderPaint.setColor(dataSet.getColor(i5));
                    coloringLine(dataSet, this.mRenderPaint, c2.getWidth(), c2.getHeight(), Integer.valueOf(dataSet.getColor()));
                }
                if (z2) {
                    i2 = i5;
                    dataSet.getFill(i5).fillRect(c2, this.mRenderPaint, barBuffer.buffer[i3], barBuffer.buffer[i3 + 1], barBuffer.buffer[i6], barBuffer.buffer[i3 + 3], zIsInverted ? Fill.Direction.DOWN : Fill.Direction.UP);
                } else {
                    i2 = i5;
                    c2.drawRoundRect(barBuffer.buffer[i3], barBuffer.buffer[i3 + 1], barBuffer.buffer[i6], barBuffer.buffer[i3 + 3], 10.0f, 10.0f, this.mRenderPaint);
                }
                if (z) {
                    c2.drawRect(barBuffer.buffer[i3], barBuffer.buffer[i3 + 1], barBuffer.buffer[i6], barBuffer.buffer[i3 + 3], this.mBarBorderPaint);
                }
            }
            i3 += 4;
            i5 = i2 + 1;
        }
    }

    private void coloringLine(IBarDataSet dataSet, Paint renderer, int canvasWidth, int canvasHeight) {
        coloringLine(dataSet, renderer, canvasWidth, canvasHeight, null);
    }

    private void coloringLine(IBarDataSet dataSet, Paint renderer, int canvasWidth, int canvasHeight, Integer color) {
        try {
            if (dataSet.getColors().size() == 1) {
                this.mRenderPaint.setShader(new LinearGradient(0.0f, 0.0f, 0.0f, canvasHeight, this.context.getResources().getColor(R.color.step_chart_end_bg), color.intValue(), Shader.TileMode.CLAMP));
            } else {
                this.mRenderPaint.setShader(new LinearGradient(0.0f, 0.0f, 0.0f, canvasHeight, preparePrimitiveColors(dataSet), (float[]) null, Shader.TileMode.CLAMP));
            }
        } catch (IndexOutOfBoundsException | NullPointerException e2) {
            renderer.setColor(dataSet.getColor());
            e2.printStackTrace();
        }
    }

    private int[] preparePrimitiveColors(IBarDataSet dataSet) {
        int[] iArr = new int[dataSet.getColors().size()];
        Iterator<Integer> it2 = dataSet.getColors().iterator();
        int i2 = 0;
        while (it2.hasNext()) {
            iArr[i2] = it2.next().intValue();
            i2++;
        }
        return iArr;
    }

    protected void prepareBarHighlight(float x, float y1, float y2, float barWidthHalf, Transformer trans) {
        this.mBarRect.set(x - barWidthHalf, y1, x + barWidthHalf, y2);
        trans.rectToPixelPhase(this.mBarRect, this.mAnimator.getPhaseY());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.yucheng.smarthealthpro.customchart.renderer.DataRenderer
    public void drawValues(Canvas c2) {
        MPPointF mPPointF;
        List list;
        int i2;
        float f2;
        boolean z;
        float[] fArr;
        Transformer transformer;
        int i3;
        float[] fArr2;
        int i4;
        float f3;
        float f4;
        float f5;
        float f6;
        float f7;
        boolean z2;
        int i5;
        MPPointF mPPointF2;
        List list2;
        BarBuffer barBuffer;
        float f8;
        float f9;
        float f10;
        if (isDrawingValuesAllowed(this.mChart)) {
            List dataSets = this.mChart.getBarData().getDataSets();
            float fConvertDpToPixel = Utils.convertDpToPixel(4.5f);
            boolean zIsDrawValueAboveBarEnabled = this.mChart.isDrawValueAboveBarEnabled();
            int i6 = 0;
            while (i6 < this.mChart.getBarData().getDataSetCount()) {
                IBarDataSet iBarDataSet = (IBarDataSet) dataSets.get(i6);
                if (shouldDrawValues(iBarDataSet)) {
                    applyValueTextStyle(iBarDataSet);
                    boolean zIsInverted = this.mChart.isInverted(iBarDataSet.getAxisDependency());
                    float fCalcTextHeight = Utils.calcTextHeight(this.mValuePaint, Constants.VIA_SHARE_TYPE_PUBLISHVIDEO);
                    float f11 = zIsDrawValueAboveBarEnabled ? -fConvertDpToPixel : fCalcTextHeight + fConvertDpToPixel;
                    float f12 = zIsDrawValueAboveBarEnabled ? fCalcTextHeight + fConvertDpToPixel : -fConvertDpToPixel;
                    if (zIsInverted) {
                        f11 = (-f11) - fCalcTextHeight;
                        f12 = (-f12) - fCalcTextHeight;
                    }
                    float f13 = f11;
                    float f14 = f12;
                    BarBuffer barBuffer2 = this.mBarBuffers[i6];
                    float phaseY = this.mAnimator.getPhaseY();
                    MPPointF mPPointF3 = MPPointF.getInstance(iBarDataSet.getIconsOffset());
                    mPPointF3.x = Utils.convertDpToPixel(mPPointF3.x);
                    mPPointF3.y = Utils.convertDpToPixel(mPPointF3.y);
                    if (iBarDataSet.isStacked()) {
                        mPPointF = mPPointF3;
                        list = dataSets;
                        Transformer transformer2 = this.mChart.getTransformer(iBarDataSet.getAxisDependency());
                        int i7 = 0;
                        int length = 0;
                        while (i7 < iBarDataSet.getEntryCount() * this.mAnimator.getPhaseX()) {
                            BarEntry barEntry = (BarEntry) iBarDataSet.getEntryForIndex(i7);
                            float[] yVals = barEntry.getYVals();
                            float f15 = (barBuffer2.buffer[length] + barBuffer2.buffer[length + 2]) / 2.0f;
                            int valueTextColor = iBarDataSet.getValueTextColor(i7);
                            if (yVals == null) {
                                if (!this.mViewPortHandler.isInBoundsRight(f15)) {
                                    break;
                                }
                                int i8 = length + 1;
                                if (this.mViewPortHandler.isInBoundsY(barBuffer2.buffer[i8]) && this.mViewPortHandler.isInBoundsLeft(f15)) {
                                    if (iBarDataSet.isDrawValuesEnabled()) {
                                        f6 = f15;
                                        f2 = fConvertDpToPixel;
                                        fArr = yVals;
                                        i2 = i7;
                                        z = zIsDrawValueAboveBarEnabled;
                                        transformer = transformer2;
                                        drawValue(c2, iBarDataSet.getValueFormatter(), barEntry.getY(), barEntry, i6, f6, barBuffer2.buffer[i8] + (barEntry.getY() >= 0.0f ? f13 : f14), valueTextColor);
                                    } else {
                                        f6 = f15;
                                        i2 = i7;
                                        f2 = fConvertDpToPixel;
                                        z = zIsDrawValueAboveBarEnabled;
                                        fArr = yVals;
                                        transformer = transformer2;
                                    }
                                    if (barEntry.getIcon() != null && iBarDataSet.isDrawIconsEnabled()) {
                                        Drawable icon = barEntry.getIcon();
                                        Utils.drawImage(c2, icon, (int) (f6 + mPPointF.x), (int) (barBuffer2.buffer[i8] + (barEntry.getY() >= 0.0f ? f13 : f14) + mPPointF.y), icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
                                    }
                                } else {
                                    transformer2 = transformer2;
                                    zIsDrawValueAboveBarEnabled = zIsDrawValueAboveBarEnabled;
                                    fConvertDpToPixel = fConvertDpToPixel;
                                    i7 = i7;
                                }
                            } else {
                                i2 = i7;
                                f2 = fConvertDpToPixel;
                                z = zIsDrawValueAboveBarEnabled;
                                fArr = yVals;
                                transformer = transformer2;
                                float f16 = f15;
                                int length2 = fArr.length * 2;
                                float[] fArr3 = new float[length2];
                                float f17 = -barEntry.getNegativeSum();
                                float f18 = 0.0f;
                                int i9 = 0;
                                int i10 = 0;
                                while (i9 < length2) {
                                    float f19 = fArr[i10];
                                    if (f19 == 0.0f && (f18 == 0.0f || f17 == 0.0f)) {
                                        float f20 = f17;
                                        f17 = f19;
                                        f5 = f20;
                                    } else if (f19 >= 0.0f) {
                                        f18 += f19;
                                        f5 = f17;
                                        f17 = f18;
                                    } else {
                                        f5 = f17 - f19;
                                    }
                                    fArr3[i9 + 1] = f17 * phaseY;
                                    i9 += 2;
                                    i10++;
                                    f17 = f5;
                                }
                                transformer.pointValuesToPixel(fArr3);
                                int i11 = 0;
                                while (i11 < length2) {
                                    int i12 = i11 / 2;
                                    float f21 = fArr[i12];
                                    float f22 = fArr3[i11 + 1] + (((f21 > 0.0f ? 1 : (f21 == 0.0f ? 0 : -1)) == 0 && (f17 > 0.0f ? 1 : (f17 == 0.0f ? 0 : -1)) == 0 && (f18 > 0.0f ? 1 : (f18 == 0.0f ? 0 : -1)) > 0) || (f21 > 0.0f ? 1 : (f21 == 0.0f ? 0 : -1)) < 0 ? f14 : f13);
                                    if (!this.mViewPortHandler.isInBoundsRight(f16)) {
                                        break;
                                    }
                                    if (this.mViewPortHandler.isInBoundsY(f22) && this.mViewPortHandler.isInBoundsLeft(f16)) {
                                        if (iBarDataSet.isDrawValuesEnabled()) {
                                            f4 = f22;
                                            i3 = i11;
                                            fArr2 = fArr3;
                                            i4 = length2;
                                            f3 = f16;
                                            drawValue(c2, iBarDataSet.getValueFormatter(), fArr[i12], barEntry, i6, f16, f4, valueTextColor);
                                        } else {
                                            f4 = f22;
                                            i3 = i11;
                                            fArr2 = fArr3;
                                            i4 = length2;
                                            f3 = f16;
                                        }
                                        if (barEntry.getIcon() != null && iBarDataSet.isDrawIconsEnabled()) {
                                            Drawable icon2 = barEntry.getIcon();
                                            Utils.drawImage(c2, icon2, (int) (f3 + mPPointF.x), (int) (f4 + mPPointF.y), icon2.getIntrinsicWidth(), icon2.getIntrinsicHeight());
                                        }
                                    } else {
                                        i3 = i11;
                                        fArr2 = fArr3;
                                        i4 = length2;
                                        f3 = f16;
                                    }
                                    i11 = i3 + 2;
                                    fArr3 = fArr2;
                                    length2 = i4;
                                    f16 = f3;
                                }
                            }
                            length = fArr == null ? length + 4 : length + (fArr.length * 4);
                            i7 = i2 + 1;
                            transformer2 = transformer;
                            zIsDrawValueAboveBarEnabled = z;
                            fConvertDpToPixel = f2;
                        }
                    } else {
                        int i13 = 0;
                        while (i13 < barBuffer2.buffer.length * this.mAnimator.getPhaseX()) {
                            float f23 = (barBuffer2.buffer[i13] + barBuffer2.buffer[i13 + 2]) / 2.0f;
                            if (!this.mViewPortHandler.isInBoundsRight(f23)) {
                                break;
                            }
                            int i14 = i13 + 1;
                            if (this.mViewPortHandler.isInBoundsY(barBuffer2.buffer[i14]) && this.mViewPortHandler.isInBoundsLeft(f23)) {
                                int i15 = i13 / 4;
                                Entry entry = (BarEntry) iBarDataSet.getEntryForIndex(i15);
                                float y = entry.getY();
                                if (iBarDataSet.isDrawValuesEnabled()) {
                                    IValueFormatter valueFormatter = iBarDataSet.getValueFormatter();
                                    if (y >= 0.0f) {
                                        f10 = barBuffer2.buffer[i14] + f13;
                                    } else {
                                        f10 = barBuffer2.buffer[i13 + 3] + f14;
                                    }
                                    f8 = f23;
                                    i5 = i13;
                                    mPPointF2 = mPPointF3;
                                    list2 = dataSets;
                                    barBuffer = barBuffer2;
                                    drawValue(c2, valueFormatter, y, entry, i6, f8, f10, iBarDataSet.getValueTextColor(i15));
                                } else {
                                    f8 = f23;
                                    i5 = i13;
                                    mPPointF2 = mPPointF3;
                                    list2 = dataSets;
                                    barBuffer = barBuffer2;
                                }
                                if (entry.getIcon() != null && iBarDataSet.isDrawIconsEnabled()) {
                                    Drawable icon3 = entry.getIcon();
                                    if (y >= 0.0f) {
                                        f9 = barBuffer.buffer[i14] + f13;
                                    } else {
                                        f9 = barBuffer.buffer[i5 + 3] + f14;
                                    }
                                    Utils.drawImage(c2, icon3, (int) (f8 + mPPointF2.x), (int) (f9 + mPPointF2.y), icon3.getIntrinsicWidth(), icon3.getIntrinsicHeight());
                                }
                            } else {
                                i5 = i13;
                                mPPointF2 = mPPointF3;
                                list2 = dataSets;
                                barBuffer = barBuffer2;
                            }
                            i13 = i5 + 4;
                            barBuffer2 = barBuffer;
                            mPPointF3 = mPPointF2;
                            dataSets = list2;
                        }
                        mPPointF = mPPointF3;
                        list = dataSets;
                    }
                    f7 = fConvertDpToPixel;
                    z2 = zIsDrawValueAboveBarEnabled;
                    MPPointF.recycleInstance(mPPointF);
                } else {
                    list = dataSets;
                    f7 = fConvertDpToPixel;
                    z2 = zIsDrawValueAboveBarEnabled;
                }
                i6++;
                dataSets = list;
                zIsDrawValueAboveBarEnabled = z2;
                fConvertDpToPixel = f7;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00d4  */
    @Override // com.yucheng.smarthealthpro.customchart.renderer.DataRenderer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void drawHighlighted(android.graphics.Canvas r15, com.yucheng.smarthealthpro.customchart.highlight.Highlight[] r16) {
        /*
            r14 = this;
            r6 = r14
            r7 = r16
            com.yucheng.smarthealthpro.customchart.interfaces.dataprovider.BarDataProvider r0 = r6.mChart
            com.yucheng.smarthealthpro.customchart.data.BarData r8 = r0.getBarData()
            int r9 = r7.length
            r0 = 0
            r10 = r0
        Lc:
            if (r10 >= r9) goto Ld9
            r11 = r7[r10]
            int r0 = r11.getDataSetIndex()
            com.yucheng.smarthealthpro.customchart.interfaces.datasets.IDataSet r0 = r8.getDataSetByIndex(r0)
            r12 = r0
            com.yucheng.smarthealthpro.customchart.interfaces.datasets.IBarDataSet r12 = (com.yucheng.smarthealthpro.customchart.interfaces.datasets.IBarDataSet) r12
            if (r12 == 0) goto Ld4
            boolean r0 = r12.isHighlightEnabled()
            if (r0 != 0) goto L25
            goto Ld4
        L25:
            float r0 = r11.getX()
            float r1 = r11.getY()
            com.yucheng.smarthealthpro.customchart.data.Entry r0 = r12.getEntryForXValue(r0, r1)
            r13 = r0
            com.yucheng.smarthealthpro.customchart.data.BarEntry r13 = (com.yucheng.smarthealthpro.customchart.data.BarEntry) r13
            boolean r0 = r14.isInBoundsX(r13, r12)
            if (r0 != 0) goto L3c
            goto Ld4
        L3c:
            com.yucheng.smarthealthpro.customchart.interfaces.dataprovider.BarDataProvider r0 = r6.mChart
            com.yucheng.smarthealthpro.customchart.components.YAxis$AxisDependency r1 = r12.getAxisDependency()
            com.yucheng.smarthealthpro.customchart.utils.Transformer r5 = r0.getTransformer(r1)
            android.graphics.Paint r0 = r6.mHighlightPaint
            int r1 = r12.getHighLightColor()
            r0.setColor(r1)
            android.graphics.Paint r0 = r6.mHighlightPaint
            int r1 = r12.getHighLightAlpha()
            r0.setAlpha(r1)
            int r0 = r11.getStackIndex()
            if (r0 < 0) goto L87
            boolean r0 = r13.isStacked()
            if (r0 == 0) goto L87
            com.yucheng.smarthealthpro.customchart.interfaces.dataprovider.BarDataProvider r0 = r6.mChart
            boolean r0 = r0.isHighlightFullBarEnabled()
            if (r0 == 0) goto L76
            float r0 = r13.getPositiveSum()
            float r1 = r13.getNegativeSum()
            float r1 = -r1
            goto L8c
        L76:
            com.yucheng.smarthealthpro.customchart.highlight.Range[] r0 = r13.getRanges()
            int r1 = r11.getStackIndex()
            r0 = r0[r1]
            float r1 = r0.from
            float r0 = r0.to
            r3 = r0
            r2 = r1
            goto L8e
        L87:
            float r0 = r13.getY()
            r1 = 0
        L8c:
            r2 = r0
            r3 = r1
        L8e:
            float r1 = r13.getX()
            float r0 = r8.getBarWidth()
            r4 = 1073741824(0x40000000, float:2.0)
            float r4 = r0 / r4
            r0 = r14
            r0.prepareBarHighlight(r1, r2, r3, r4, r5)
            android.graphics.RectF r0 = r6.mBarRect
            r14.setHighlightDrawPos(r11, r0)
            com.yucheng.smarthealthpro.customchart.interfaces.dataprovider.BarDataProvider r0 = r6.mChart
            com.yucheng.smarthealthpro.customchart.components.YAxis$AxisDependency r1 = r12.getAxisDependency()
            com.yucheng.smarthealthpro.customchart.utils.Transformer r0 = r0.getTransformer(r1)
            float r1 = r13.getX()
            float r2 = r13.getY()
            com.yucheng.smarthealthpro.customchart.animation.ChartAnimator r3 = r6.mAnimator
            float r3 = r3.getPhaseY()
            float r2 = r2 * r3
            com.yucheng.smarthealthpro.customchart.utils.MPPointD r0 = r0.getPixelForValues(r1, r2)
            double r1 = r0.x
            float r1 = (float) r1
            double r2 = r0.y
            float r2 = (float) r2
            r11.setDraw(r1, r2)
            double r1 = r0.x
            float r1 = (float) r1
            double r2 = r0.y
            float r0 = (float) r2
            r2 = r15
            r14.drawHighlightLine(r15, r1, r0, r12)
            goto Ld5
        Ld4:
            r2 = r15
        Ld5:
            int r10 = r10 + 1
            goto Lc
        Ld9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.customchart.renderer.BarChartStepRenderer.drawHighlighted(android.graphics.Canvas, com.yucheng.smarthealthpro.customchart.highlight.Highlight[]):void");
    }

    @Override // com.yucheng.smarthealthpro.customchart.renderer.DataRenderer
    public void setRoundBar(boolean roundBar) {
        this.isRoundBar = roundBar;
    }

    protected void setHighlightDrawPos(Highlight high, RectF bar) {
        high.setDraw(bar.centerX(), bar.top);
    }
}
