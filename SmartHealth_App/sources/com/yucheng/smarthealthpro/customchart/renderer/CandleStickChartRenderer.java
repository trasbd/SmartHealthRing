package com.yucheng.smarthealthpro.customchart.renderer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import com.yucheng.smarthealthpro.customchart.animation.ChartAnimator;
import com.yucheng.smarthealthpro.customchart.data.CandleData;
import com.yucheng.smarthealthpro.customchart.data.CandleEntry;
import com.yucheng.smarthealthpro.customchart.highlight.Highlight;
import com.yucheng.smarthealthpro.customchart.interfaces.dataprovider.CandleDataProvider;
import com.yucheng.smarthealthpro.customchart.interfaces.datasets.ICandleDataSet;
import com.yucheng.smarthealthpro.customchart.interfaces.datasets.ILineScatterCandleRadarDataSet;
import com.yucheng.smarthealthpro.customchart.utils.MPPointD;
import com.yucheng.smarthealthpro.customchart.utils.MPPointF;
import com.yucheng.smarthealthpro.customchart.utils.Transformer;
import com.yucheng.smarthealthpro.customchart.utils.Utils;
import com.yucheng.smarthealthpro.customchart.utils.ViewPortHandler;
import java.util.List;

/* loaded from: classes4.dex */
public class CandleStickChartRenderer extends LineScatterCandleRadarRenderer {
    private int height;
    private float[] mBodyBuffers;
    protected CandleDataProvider mChart;
    private float[] mCloseBuffers;
    private float[] mOpenBuffers;
    private float[] mRangeBuffers;
    private float[] mShadowBuffers;

    @Override // com.yucheng.smarthealthpro.customchart.renderer.DataRenderer
    public void drawExtras(Canvas c2) {
    }

    @Override // com.yucheng.smarthealthpro.customchart.renderer.DataRenderer
    public void initBuffers() {
    }

    @Override // com.yucheng.smarthealthpro.customchart.renderer.DataRenderer
    public void setRoundBar(boolean isRound) {
    }

    public CandleStickChartRenderer(CandleDataProvider chart, ChartAnimator animator, ViewPortHandler viewPortHandler) {
        super(animator, viewPortHandler);
        this.mShadowBuffers = new float[8];
        this.mBodyBuffers = new float[4];
        this.mRangeBuffers = new float[4];
        this.mOpenBuffers = new float[4];
        this.mCloseBuffers = new float[4];
        this.mChart = chart;
    }

    @Override // com.yucheng.smarthealthpro.customchart.renderer.DataRenderer
    public void drawData(Canvas c2) {
        for (T t : this.mChart.getCandleData().getDataSets()) {
            if (t.isVisible()) {
                drawDataSet(c2, t);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected void drawDataSet(Canvas c2, ICandleDataSet dataSet) {
        int neutralColor;
        int shadowColor;
        int neutralColor2;
        int increasingColor;
        int decreasingColor;
        Transformer transformer = this.mChart.getTransformer(dataSet.getAxisDependency());
        float phaseY = this.mAnimator.getPhaseY();
        float barSpace = dataSet.getBarSpace();
        boolean showCandleBar = dataSet.getShowCandleBar();
        this.mXBounds.set(this.mChart, dataSet);
        this.mRenderPaint.setStrokeWidth(dataSet.getShadowWidth());
        for (int i2 = this.mXBounds.min; i2 <= this.mXBounds.range + this.mXBounds.min; i2++) {
            CandleEntry candleEntry = (CandleEntry) dataSet.getEntryForIndex(i2);
            if (candleEntry != null) {
                float x = candleEntry.getX();
                float open = candleEntry.getOpen();
                float close = candleEntry.getClose();
                float high = candleEntry.getHigh();
                float low = candleEntry.getLow();
                if (showCandleBar) {
                    float[] fArr = this.mShadowBuffers;
                    fArr[0] = x;
                    fArr[2] = x;
                    fArr[4] = x;
                    fArr[6] = x;
                    if (open > close) {
                        fArr[1] = high * phaseY;
                        fArr[3] = open * phaseY;
                        fArr[5] = low * phaseY;
                        fArr[7] = close * phaseY;
                    } else if (open < close) {
                        fArr[1] = high * phaseY;
                        fArr[3] = close * phaseY;
                        fArr[5] = low * phaseY;
                        fArr[7] = open * phaseY;
                    } else {
                        fArr[1] = high * phaseY;
                        float f2 = open * phaseY;
                        fArr[3] = f2;
                        fArr[5] = low * phaseY;
                        fArr[7] = f2;
                    }
                    transformer.pointValuesToPixel(fArr);
                    if (!dataSet.getShadowColorSameAsCandle()) {
                        Paint paint = this.mRenderPaint;
                        if (dataSet.getShadowColor() == 1122867) {
                            shadowColor = dataSet.getColor(i2);
                        } else {
                            shadowColor = dataSet.getShadowColor();
                        }
                        paint.setColor(shadowColor);
                    } else if (open > close) {
                        Paint paint2 = this.mRenderPaint;
                        if (dataSet.getDecreasingColor() == 1122867) {
                            decreasingColor = dataSet.getColor(i2);
                        } else {
                            decreasingColor = dataSet.getDecreasingColor();
                        }
                        paint2.setColor(decreasingColor);
                    } else if (open < close) {
                        Paint paint3 = this.mRenderPaint;
                        if (dataSet.getIncreasingColor() == 1122867) {
                            increasingColor = dataSet.getColor(i2);
                        } else {
                            increasingColor = dataSet.getIncreasingColor();
                        }
                        paint3.setColor(increasingColor);
                    } else {
                        Paint paint4 = this.mRenderPaint;
                        if (dataSet.getNeutralColor() == 1122867) {
                            neutralColor2 = dataSet.getColor(i2);
                        } else {
                            neutralColor2 = dataSet.getNeutralColor();
                        }
                        paint4.setColor(neutralColor2);
                    }
                    this.mRenderPaint.setStyle(Paint.Style.STROKE);
                    float[] fArr2 = this.mBodyBuffers;
                    fArr2[0] = (x - 0.5f) + barSpace;
                    fArr2[1] = close * phaseY;
                    fArr2[2] = (x + 0.5f) - barSpace;
                    fArr2[3] = open * phaseY;
                    transformer.pointValuesToPixel(fArr2);
                    if (open > close) {
                        if (dataSet.getDecreasingColor() == 1122867) {
                            this.mRenderPaint.setColor(dataSet.getColor(i2));
                        } else {
                            this.mRenderPaint.setColor(dataSet.getDecreasingColor());
                        }
                        this.mRenderPaint.setStyle(dataSet.getDecreasingPaintStyle());
                        if (dataSet.getDecreasingColor() == 1122867) {
                            this.mRenderPaint.setColor(Color.parseColor("#ffba6c"));
                        } else {
                            this.mRenderPaint.setColor(dataSet.getDecreasingColor());
                        }
                        float[] fArr3 = this.mBodyBuffers;
                        c2.drawRoundRect(fArr3[0], fArr3[3], fArr3[2], fArr3[1], 0.0f, 0.0f, this.mRenderPaint);
                        if (dataSet.getDecreasingColor() == 1122867) {
                            this.mRenderPaint.setColor(Color.parseColor("#e05e30"));
                        } else {
                            this.mRenderPaint.setColor(dataSet.getIncreasingColor());
                        }
                        float[] fArr4 = this.mBodyBuffers;
                        c2.drawRoundRect(fArr4[0], fArr4[1], fArr4[2], this.mChart.getHeight(), 0.0f, 0.0f, this.mRenderPaint);
                    } else if (open < close) {
                        if (dataSet.getIncreasingColor() == 1122867) {
                            this.mRenderPaint.setColor(dataSet.getColor(i2));
                        } else {
                            this.mRenderPaint.setColor(dataSet.getIncreasingColor());
                        }
                        this.mRenderPaint.setStyle(dataSet.getIncreasingPaintStyle());
                        if (dataSet.getDecreasingColor() == 1122867) {
                            this.mRenderPaint.setColor(Color.parseColor("#ffba6c"));
                        } else {
                            this.mRenderPaint.setColor(dataSet.getDecreasingColor());
                        }
                        float[] fArr5 = this.mBodyBuffers;
                        c2.drawRoundRect(fArr5[0], fArr5[1], fArr5[2], fArr5[3], 0.0f, 0.0f, this.mRenderPaint);
                        if (dataSet.getDecreasingColor() == 1122867) {
                            this.mRenderPaint.setColor(Color.parseColor("#e05e30"));
                        } else {
                            this.mRenderPaint.setColor(dataSet.getIncreasingColor());
                        }
                        float[] fArr6 = this.mBodyBuffers;
                        c2.drawRoundRect(fArr6[0], fArr6[3], fArr6[2], this.mChart.getHeight(), 0.0f, 0.0f, this.mRenderPaint);
                    } else if (dataSet.getNeutralColor() == 1122867) {
                        this.mRenderPaint.setColor(dataSet.getColor(i2));
                    } else {
                        this.mRenderPaint.setColor(dataSet.getNeutralColor());
                    }
                } else {
                    float[] fArr7 = this.mRangeBuffers;
                    fArr7[0] = x;
                    fArr7[1] = high * phaseY;
                    fArr7[2] = x;
                    fArr7[3] = low * phaseY;
                    float[] fArr8 = this.mOpenBuffers;
                    fArr8[0] = (x - 0.5f) + barSpace;
                    float f3 = open * phaseY;
                    fArr8[1] = f3;
                    fArr8[2] = x;
                    fArr8[3] = f3;
                    float[] fArr9 = this.mCloseBuffers;
                    fArr9[0] = (0.5f + x) - barSpace;
                    float f4 = close * phaseY;
                    fArr9[1] = f4;
                    fArr9[2] = x;
                    fArr9[3] = f4;
                    transformer.pointValuesToPixel(fArr7);
                    transformer.pointValuesToPixel(this.mOpenBuffers);
                    transformer.pointValuesToPixel(this.mCloseBuffers);
                    if (open > close) {
                        if (dataSet.getDecreasingColor() == 1122867) {
                            neutralColor = dataSet.getColor(i2);
                        } else {
                            neutralColor = dataSet.getDecreasingColor();
                        }
                    } else if (open < close) {
                        if (dataSet.getIncreasingColor() == 1122867) {
                            neutralColor = dataSet.getColor(i2);
                        } else {
                            neutralColor = dataSet.getIncreasingColor();
                        }
                    } else if (dataSet.getNeutralColor() == 1122867) {
                        neutralColor = dataSet.getColor(i2);
                    } else {
                        neutralColor = dataSet.getNeutralColor();
                    }
                    this.mRenderPaint.setColor(neutralColor);
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.yucheng.smarthealthpro.customchart.renderer.DataRenderer
    public void drawValues(Canvas c2) {
        int i2;
        MPPointF mPPointF;
        float f2;
        float f3;
        if (isDrawingValuesAllowed(this.mChart)) {
            List<T> dataSets = this.mChart.getCandleData().getDataSets();
            for (int i3 = 0; i3 < dataSets.size(); i3++) {
                ICandleDataSet iCandleDataSet = (ICandleDataSet) dataSets.get(i3);
                if (shouldDrawValues(iCandleDataSet) && iCandleDataSet.getEntryCount() >= 1) {
                    applyValueTextStyle(iCandleDataSet);
                    Transformer transformer = this.mChart.getTransformer(iCandleDataSet.getAxisDependency());
                    this.mXBounds.set(this.mChart, iCandleDataSet);
                    float[] fArrGenerateTransformedValuesCandle = transformer.generateTransformedValuesCandle(iCandleDataSet, this.mAnimator.getPhaseX(), this.mAnimator.getPhaseY(), this.mXBounds.min, this.mXBounds.max);
                    float fConvertDpToPixel = Utils.convertDpToPixel(5.0f);
                    MPPointF mPPointF2 = MPPointF.getInstance(iCandleDataSet.getIconsOffset());
                    mPPointF2.x = Utils.convertDpToPixel(mPPointF2.x);
                    mPPointF2.y = Utils.convertDpToPixel(mPPointF2.y);
                    int i4 = 0;
                    while (i4 < fArrGenerateTransformedValuesCandle.length) {
                        float f4 = fArrGenerateTransformedValuesCandle[i4];
                        float f5 = fArrGenerateTransformedValuesCandle[i4 + 1];
                        if (!this.mViewPortHandler.isInBoundsRight(f4)) {
                            break;
                        }
                        if (this.mViewPortHandler.isInBoundsLeft(f4) && this.mViewPortHandler.isInBoundsY(f5)) {
                            int i5 = i4 / 2;
                            CandleEntry candleEntry = (CandleEntry) iCandleDataSet.getEntryForIndex(this.mXBounds.min + i5);
                            if (iCandleDataSet.isDrawValuesEnabled()) {
                                f2 = f5;
                                f3 = f4;
                                i2 = i4;
                                mPPointF = mPPointF2;
                                drawValue(c2, iCandleDataSet.getValueFormatter(), candleEntry.getHigh(), candleEntry, i3, f4, f5 - fConvertDpToPixel, iCandleDataSet.getValueTextColor(i5));
                            } else {
                                f2 = f5;
                                f3 = f4;
                                i2 = i4;
                                mPPointF = mPPointF2;
                            }
                            if (candleEntry.getIcon() != null && iCandleDataSet.isDrawIconsEnabled()) {
                                Drawable icon = candleEntry.getIcon();
                                Utils.drawImage(c2, icon, (int) (f3 + mPPointF.x), (int) (f2 + mPPointF.y), icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
                            }
                        } else {
                            i2 = i4;
                            mPPointF = mPPointF2;
                        }
                        i4 = i2 + 2;
                        mPPointF2 = mPPointF;
                    }
                    MPPointF.recycleInstance(mPPointF2);
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.yucheng.smarthealthpro.customchart.renderer.DataRenderer
    public void drawHighlighted(Canvas c2, Highlight[] indices) {
        CandleData candleData = this.mChart.getCandleData();
        for (Highlight highlight : indices) {
            ILineScatterCandleRadarDataSet iLineScatterCandleRadarDataSet = (ICandleDataSet) candleData.getDataSetByIndex(highlight.getDataSetIndex());
            if (iLineScatterCandleRadarDataSet != null && iLineScatterCandleRadarDataSet.isHighlightEnabled()) {
                CandleEntry candleEntry = (CandleEntry) iLineScatterCandleRadarDataSet.getEntryForXValue(highlight.getX(), highlight.getY());
                if (isInBoundsX(candleEntry, iLineScatterCandleRadarDataSet)) {
                    MPPointD pixelForValues = this.mChart.getTransformer(iLineScatterCandleRadarDataSet.getAxisDependency()).getPixelForValues(candleEntry.getX(), ((candleEntry.getLow() * this.mAnimator.getPhaseY()) + (candleEntry.getHigh() * this.mAnimator.getPhaseY())) / 2.0f);
                    highlight.setDraw((float) pixelForValues.x, (float) pixelForValues.y);
                    drawHighlightLines(c2, (float) pixelForValues.x, (float) pixelForValues.y, iLineScatterCandleRadarDataSet);
                }
            }
        }
    }
}
