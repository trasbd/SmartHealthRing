package com.yucheng.smarthealthpro.customchart.charts;

import android.content.Context;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import com.yucheng.smarthealthpro.customchart.components.Legend;
import com.yucheng.smarthealthpro.customchart.components.XAxis;
import com.yucheng.smarthealthpro.customchart.components.YAxis;
import com.yucheng.smarthealthpro.customchart.data.BarData;
import com.yucheng.smarthealthpro.customchart.data.BarEntry;
import com.yucheng.smarthealthpro.customchart.data.Entry;
import com.yucheng.smarthealthpro.customchart.highlight.Highlight;
import com.yucheng.smarthealthpro.customchart.highlight.HorizontalBarHighlighter;
import com.yucheng.smarthealthpro.customchart.interfaces.datasets.IBarDataSet;
import com.yucheng.smarthealthpro.customchart.listener.XAxisRendererHorizontalBarChart;
import com.yucheng.smarthealthpro.customchart.listener.YAxisRendererHorizontalBarChart;
import com.yucheng.smarthealthpro.customchart.renderer.HorizontalBarChartRenderer;
import com.yucheng.smarthealthpro.customchart.utils.HorizontalViewPortHandler;
import com.yucheng.smarthealthpro.customchart.utils.MPPointF;
import com.yucheng.smarthealthpro.customchart.utils.TransformerHorizontalBarChart;
import com.yucheng.smarthealthpro.customchart.utils.Utils;

/* loaded from: classes4.dex */
public class HorizontalBarChart extends BarChart {
    protected float[] mGetPositionBuffer;
    private RectF mOffsetsBuffer;

    public HorizontalBarChart(Context context) {
        super(context);
        this.mOffsetsBuffer = new RectF();
        this.mGetPositionBuffer = new float[2];
    }

    public HorizontalBarChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mOffsetsBuffer = new RectF();
        this.mGetPositionBuffer = new float[2];
    }

    public HorizontalBarChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mOffsetsBuffer = new RectF();
        this.mGetPositionBuffer = new float[2];
    }

    @Override // com.yucheng.smarthealthpro.customchart.charts.BarChart, com.yucheng.smarthealthpro.customchart.charts.BarLineChartBase, com.yucheng.smarthealthpro.customchart.charts.Chart
    protected void init() {
        this.mViewPortHandler = new HorizontalViewPortHandler();
        super.init();
        this.mLeftAxisTransformer = new TransformerHorizontalBarChart(this.mViewPortHandler);
        this.mRightAxisTransformer = new TransformerHorizontalBarChart(this.mViewPortHandler);
        this.mRenderer = new HorizontalBarChartRenderer(this, this.mAnimator, this.mViewPortHandler, getContext());
        setHighlighter(new HorizontalBarHighlighter(this));
        this.mAxisRendererLeft = new YAxisRendererHorizontalBarChart(this.mViewPortHandler, this.mAxisLeft, this.mLeftAxisTransformer);
        this.mAxisRendererRight = new YAxisRendererHorizontalBarChart(this.mViewPortHandler, this.mAxisRight, this.mRightAxisTransformer);
        this.mXAxisRenderer = new XAxisRendererHorizontalBarChart(this.mViewPortHandler, this.mXAxis, this.mLeftAxisTransformer, this);
    }

    @Override // com.yucheng.smarthealthpro.customchart.charts.BarLineChartBase
    protected void calculateLegendOffsets(RectF offsets) {
        offsets.left = 0.0f;
        offsets.right = 0.0f;
        offsets.top = 0.0f;
        offsets.bottom = 0.0f;
        if (this.mLegend == null || !this.mLegend.isEnabled() || this.mLegend.isDrawInsideEnabled()) {
            return;
        }
        int i2 = AnonymousClass1.$SwitchMap$com$yucheng$smarthealthpro$customchart$components$Legend$LegendOrientation[this.mLegend.getOrientation().ordinal()];
        if (i2 == 1) {
            int i3 = AnonymousClass1.$SwitchMap$com$yucheng$smarthealthpro$customchart$components$Legend$LegendHorizontalAlignment[this.mLegend.getHorizontalAlignment().ordinal()];
            if (i3 == 1) {
                offsets.left += Math.min(this.mLegend.mNeededWidth, this.mViewPortHandler.getChartWidth() * this.mLegend.getMaxSizePercent()) + this.mLegend.getXOffset();
                return;
            }
            if (i3 == 2) {
                offsets.right += Math.min(this.mLegend.mNeededWidth, this.mViewPortHandler.getChartWidth() * this.mLegend.getMaxSizePercent()) + this.mLegend.getXOffset();
                return;
            }
            if (i3 != 3) {
                return;
            }
            int i4 = AnonymousClass1.$SwitchMap$com$yucheng$smarthealthpro$customchart$components$Legend$LegendVerticalAlignment[this.mLegend.getVerticalAlignment().ordinal()];
            if (i4 == 1) {
                offsets.top += Math.min(this.mLegend.mNeededHeight, this.mViewPortHandler.getChartHeight() * this.mLegend.getMaxSizePercent()) + this.mLegend.getYOffset();
                return;
            } else {
                if (i4 != 2) {
                    return;
                }
                offsets.bottom += Math.min(this.mLegend.mNeededHeight, this.mViewPortHandler.getChartHeight() * this.mLegend.getMaxSizePercent()) + this.mLegend.getYOffset();
                return;
            }
        }
        if (i2 != 2) {
            return;
        }
        int i5 = AnonymousClass1.$SwitchMap$com$yucheng$smarthealthpro$customchart$components$Legend$LegendVerticalAlignment[this.mLegend.getVerticalAlignment().ordinal()];
        if (i5 == 1) {
            offsets.top += Math.min(this.mLegend.mNeededHeight, this.mViewPortHandler.getChartHeight() * this.mLegend.getMaxSizePercent()) + this.mLegend.getYOffset();
            if (this.mAxisLeft.isEnabled() && this.mAxisLeft.isDrawLabelsEnabled()) {
                offsets.top += this.mAxisLeft.getRequiredHeightSpace(this.mAxisRendererLeft.getPaintAxisLabels());
                return;
            }
            return;
        }
        if (i5 != 2) {
            return;
        }
        offsets.bottom += Math.min(this.mLegend.mNeededHeight, this.mViewPortHandler.getChartHeight() * this.mLegend.getMaxSizePercent()) + this.mLegend.getYOffset();
        if (this.mAxisRight.isEnabled() && this.mAxisRight.isDrawLabelsEnabled()) {
            offsets.bottom += this.mAxisRight.getRequiredHeightSpace(this.mAxisRendererRight.getPaintAxisLabels());
        }
    }

    /* renamed from: com.yucheng.smarthealthpro.customchart.charts.HorizontalBarChart$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$yucheng$smarthealthpro$customchart$components$Legend$LegendHorizontalAlignment;
        static final /* synthetic */ int[] $SwitchMap$com$yucheng$smarthealthpro$customchart$components$Legend$LegendOrientation;
        static final /* synthetic */ int[] $SwitchMap$com$yucheng$smarthealthpro$customchart$components$Legend$LegendVerticalAlignment;

        static {
            int[] iArr = new int[Legend.LegendOrientation.values().length];
            $SwitchMap$com$yucheng$smarthealthpro$customchart$components$Legend$LegendOrientation = iArr;
            try {
                iArr[Legend.LegendOrientation.VERTICAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$yucheng$smarthealthpro$customchart$components$Legend$LegendOrientation[Legend.LegendOrientation.HORIZONTAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[Legend.LegendHorizontalAlignment.values().length];
            $SwitchMap$com$yucheng$smarthealthpro$customchart$components$Legend$LegendHorizontalAlignment = iArr2;
            try {
                iArr2[Legend.LegendHorizontalAlignment.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$yucheng$smarthealthpro$customchart$components$Legend$LegendHorizontalAlignment[Legend.LegendHorizontalAlignment.RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$yucheng$smarthealthpro$customchart$components$Legend$LegendHorizontalAlignment[Legend.LegendHorizontalAlignment.CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            int[] iArr3 = new int[Legend.LegendVerticalAlignment.values().length];
            $SwitchMap$com$yucheng$smarthealthpro$customchart$components$Legend$LegendVerticalAlignment = iArr3;
            try {
                iArr3[Legend.LegendVerticalAlignment.TOP.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$yucheng$smarthealthpro$customchart$components$Legend$LegendVerticalAlignment[Legend.LegendVerticalAlignment.BOTTOM.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    @Override // com.yucheng.smarthealthpro.customchart.charts.BarLineChartBase, com.yucheng.smarthealthpro.customchart.charts.Chart
    public void calculateOffsets() {
        calculateLegendOffsets(this.mOffsetsBuffer);
        float f2 = this.mOffsetsBuffer.left + 0.0f;
        float requiredHeightSpace = this.mOffsetsBuffer.top + 0.0f;
        float f3 = this.mOffsetsBuffer.right + 0.0f;
        float requiredHeightSpace2 = this.mOffsetsBuffer.bottom + 0.0f;
        if (this.mAxisLeft.needsOffset()) {
            requiredHeightSpace += this.mAxisLeft.getRequiredHeightSpace(this.mAxisRendererLeft.getPaintAxisLabels());
        }
        if (this.mAxisRight.needsOffset()) {
            requiredHeightSpace2 += this.mAxisRight.getRequiredHeightSpace(this.mAxisRendererRight.getPaintAxisLabels());
        }
        float f4 = this.mXAxis.mLabelRotatedWidth;
        if (this.mXAxis.isEnabled()) {
            if (this.mXAxis.getPosition() == XAxis.XAxisPosition.BOTTOM) {
                f2 += f4;
            } else if (this.mXAxis.getPosition() == XAxis.XAxisPosition.TOP) {
                f3 += f4;
            } else if (this.mXAxis.getPosition() == XAxis.XAxisPosition.BOTH_SIDED) {
                f2 += f4;
                f3 += f4;
            }
        }
        float extraTopOffset = requiredHeightSpace + getExtraTopOffset();
        float extraRightOffset = f3 + getExtraRightOffset();
        float extraBottomOffset = requiredHeightSpace2 + getExtraBottomOffset();
        float extraLeftOffset = f2 + getExtraLeftOffset();
        float fConvertDpToPixel = Utils.convertDpToPixel(this.mMinOffset);
        this.mViewPortHandler.restrainViewPort(Math.max(fConvertDpToPixel, extraLeftOffset), Math.max(fConvertDpToPixel, extraTopOffset), Math.max(fConvertDpToPixel, extraRightOffset), Math.max(fConvertDpToPixel, extraBottomOffset));
        if (this.mLogEnabled) {
            Log.i("MPAndroidChart", "offsetLeft: " + extraLeftOffset + ", offsetTop: " + extraTopOffset + ", offsetRight: " + extraRightOffset + ", offsetBottom: " + extraBottomOffset);
            Log.i("MPAndroidChart", "Content: " + this.mViewPortHandler.getContentRect().toString());
        }
        prepareOffsetMatrix();
        prepareValuePxMatrix();
    }

    @Override // com.yucheng.smarthealthpro.customchart.charts.BarLineChartBase
    protected void prepareValuePxMatrix() {
        this.mRightAxisTransformer.prepareMatrixValuePx(this.mAxisRight.mAxisMinimum, this.mAxisRight.mAxisRange, this.mXAxis.mAxisRange, this.mXAxis.mAxisMinimum);
        this.mLeftAxisTransformer.prepareMatrixValuePx(this.mAxisLeft.mAxisMinimum, this.mAxisLeft.mAxisRange, this.mXAxis.mAxisRange, this.mXAxis.mAxisMinimum);
    }

    @Override // com.yucheng.smarthealthpro.customchart.charts.Chart
    protected float[] getMarkerPosition(Highlight high) {
        return new float[]{high.getDrawY(), high.getDrawX()};
    }

    @Override // com.yucheng.smarthealthpro.customchart.charts.BarChart
    public void getBarBounds(BarEntry e2, RectF outputRect) {
        IBarDataSet iBarDataSet = (IBarDataSet) ((BarData) this.mData).getDataSetForEntry(e2);
        if (iBarDataSet == null) {
            outputRect.set(Float.MIN_VALUE, Float.MIN_VALUE, Float.MIN_VALUE, Float.MIN_VALUE);
            return;
        }
        float y = e2.getY();
        float x = e2.getX();
        float barWidth = ((BarData) this.mData).getBarWidth() / 2.0f;
        float f2 = x - barWidth;
        float f3 = x + barWidth;
        float f4 = y >= 0.0f ? y : 0.0f;
        if (y > 0.0f) {
            y = 0.0f;
        }
        outputRect.set(f4, f2, y, f3);
        getTransformer(iBarDataSet.getAxisDependency()).rectValueToPixel(outputRect);
    }

    @Override // com.yucheng.smarthealthpro.customchart.charts.BarLineChartBase
    public MPPointF getPosition(Entry e2, YAxis.AxisDependency axis) {
        if (e2 == null) {
            return null;
        }
        float[] fArr = this.mGetPositionBuffer;
        fArr[0] = e2.getY();
        fArr[1] = e2.getX();
        getTransformer(axis).pointValuesToPixel(fArr);
        return MPPointF.getInstance(fArr[0], fArr[1]);
    }

    @Override // com.yucheng.smarthealthpro.customchart.charts.BarChart, com.yucheng.smarthealthpro.customchart.charts.Chart
    public Highlight getHighlightByTouchPoint(float x, float y) {
        if (this.mData == 0) {
            if (!this.mLogEnabled) {
                return null;
            }
            Log.e("MPAndroidChart", "Can't select by touch. No data set.");
            return null;
        }
        return getHighlighter().getHighlight(y, x);
    }

    @Override // com.yucheng.smarthealthpro.customchart.charts.BarLineChartBase, com.yucheng.smarthealthpro.customchart.interfaces.dataprovider.BarLineScatterCandleBubbleDataProvider
    public float getLowestVisibleX() {
        getTransformer(YAxis.AxisDependency.LEFT).getValuesByTouchPoint(this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentBottom(), this.posForGetLowestVisibleX);
        return (float) Math.max(this.mXAxis.mAxisMinimum, this.posForGetLowestVisibleX.y);
    }

    @Override // com.yucheng.smarthealthpro.customchart.charts.BarLineChartBase, com.yucheng.smarthealthpro.customchart.interfaces.dataprovider.BarLineScatterCandleBubbleDataProvider
    public float getHighestVisibleX() {
        getTransformer(YAxis.AxisDependency.LEFT).getValuesByTouchPoint(this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentTop(), this.posForGetHighestVisibleX);
        return (float) Math.min(this.mXAxis.mAxisMaximum, this.posForGetHighestVisibleX.y);
    }

    @Override // com.yucheng.smarthealthpro.customchart.charts.BarLineChartBase
    public void setVisibleXRangeMaximum(float maxXRange) {
        this.mViewPortHandler.setMinimumScaleY(this.mXAxis.mAxisRange / maxXRange);
    }

    @Override // com.yucheng.smarthealthpro.customchart.charts.BarLineChartBase
    public void setVisibleXRangeMinimum(float minXRange) {
        this.mViewPortHandler.setMaximumScaleY(this.mXAxis.mAxisRange / minXRange);
    }

    @Override // com.yucheng.smarthealthpro.customchart.charts.BarLineChartBase
    public void setVisibleXRange(float minXRange, float maxXRange) {
        this.mViewPortHandler.setMinMaxScaleY(this.mXAxis.mAxisRange / minXRange, this.mXAxis.mAxisRange / maxXRange);
    }

    @Override // com.yucheng.smarthealthpro.customchart.charts.BarLineChartBase
    public void setVisibleYRangeMaximum(float maxYRange, YAxis.AxisDependency axis) {
        this.mViewPortHandler.setMinimumScaleX(getAxisRange(axis) / maxYRange);
    }

    @Override // com.yucheng.smarthealthpro.customchart.charts.BarLineChartBase
    public void setVisibleYRangeMinimum(float minYRange, YAxis.AxisDependency axis) {
        this.mViewPortHandler.setMaximumScaleX(getAxisRange(axis) / minYRange);
    }

    @Override // com.yucheng.smarthealthpro.customchart.charts.BarLineChartBase
    public void setVisibleYRange(float minYRange, float maxYRange, YAxis.AxisDependency axis) {
        this.mViewPortHandler.setMinMaxScaleX(getAxisRange(axis) / minYRange, getAxisRange(axis) / maxYRange);
    }
}
