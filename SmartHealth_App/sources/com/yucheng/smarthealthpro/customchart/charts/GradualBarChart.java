package com.yucheng.smarthealthpro.customchart.charts;

import android.content.Context;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import com.yucheng.smarthealthpro.customchart.components.YAxis;
import com.yucheng.smarthealthpro.customchart.data.BarData;
import com.yucheng.smarthealthpro.customchart.data.BarEntry;
import com.yucheng.smarthealthpro.customchart.highlight.BarHighlighter;
import com.yucheng.smarthealthpro.customchart.highlight.Highlight;
import com.yucheng.smarthealthpro.customchart.interfaces.dataprovider.BarDataProvider;
import com.yucheng.smarthealthpro.customchart.interfaces.datasets.IBarDataSet;
import com.yucheng.smarthealthpro.customchart.renderer.BarChartStepRenderer;

/* loaded from: classes4.dex */
public class GradualBarChart extends BarLineChartBase<BarData> implements BarDataProvider {
    private boolean mDrawBarShadow;
    private boolean mDrawValueAboveBar;
    private boolean mFitBars;
    protected boolean mHighlightFullBarEnabled;

    public GradualBarChart(Context context) {
        super(context);
        this.mHighlightFullBarEnabled = false;
        this.mDrawValueAboveBar = true;
        this.mDrawBarShadow = false;
        this.mFitBars = false;
    }

    public GradualBarChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mHighlightFullBarEnabled = false;
        this.mDrawValueAboveBar = true;
        this.mDrawBarShadow = false;
        this.mFitBars = false;
    }

    public GradualBarChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mHighlightFullBarEnabled = false;
        this.mDrawValueAboveBar = true;
        this.mDrawBarShadow = false;
        this.mFitBars = false;
    }

    @Override // com.yucheng.smarthealthpro.customchart.charts.BarLineChartBase, com.yucheng.smarthealthpro.customchart.charts.Chart
    protected void init() {
        super.init();
        this.mRenderer = new BarChartStepRenderer(this, this.mAnimator, this.mViewPortHandler, getContext());
        setHighlighter(new BarHighlighter(this));
        getXAxis().setSpaceMin(0.5f);
        getXAxis().setSpaceMax(0.5f);
    }

    @Override // com.yucheng.smarthealthpro.customchart.charts.BarLineChartBase, com.yucheng.smarthealthpro.customchart.charts.Chart
    protected void calcMinMax() {
        if (this.mFitBars) {
            this.mXAxis.calculate(((BarData) this.mData).getXMin() - (((BarData) this.mData).getBarWidth() / 2.0f), ((BarData) this.mData).getXMax() + (((BarData) this.mData).getBarWidth() / 2.0f));
        } else {
            this.mXAxis.calculate(((BarData) this.mData).getXMin(), ((BarData) this.mData).getXMax());
        }
        this.mAxisLeft.calculate(((BarData) this.mData).getYMin(YAxis.AxisDependency.LEFT), ((BarData) this.mData).getYMax(YAxis.AxisDependency.LEFT));
        this.mAxisRight.calculate(((BarData) this.mData).getYMin(YAxis.AxisDependency.RIGHT), ((BarData) this.mData).getYMax(YAxis.AxisDependency.RIGHT));
    }

    @Override // com.yucheng.smarthealthpro.customchart.charts.Chart
    public Highlight getHighlightByTouchPoint(float x, float y) {
        if (this.mData == 0) {
            Log.e("MPAndroidChart", "Can't select by touch. No data set.");
            return null;
        }
        Highlight highlight = getHighlighter().getHighlight(x, y);
        return (highlight == null || !isHighlightFullBarEnabled()) ? highlight : new Highlight(highlight.getX(), highlight.getY(), highlight.getXPx(), highlight.getYPx(), highlight.getDataSetIndex(), -1, highlight.getAxis());
    }

    public RectF getBarBounds(BarEntry e2) {
        RectF rectF = new RectF();
        getBarBounds(e2, rectF);
        return rectF;
    }

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
        outputRect.set(f2, f4, f3, y);
        getTransformer(iBarDataSet.getAxisDependency()).rectValueToPixel(outputRect);
    }

    public void setDrawValueAboveBar(boolean enabled) {
        this.mDrawValueAboveBar = enabled;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.dataprovider.BarDataProvider
    public boolean isDrawValueAboveBarEnabled() {
        return this.mDrawValueAboveBar;
    }

    public void setDrawBarShadow(boolean enabled) {
        this.mDrawBarShadow = enabled;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.dataprovider.BarDataProvider
    public boolean isDrawBarShadowEnabled() {
        return this.mDrawBarShadow;
    }

    public void setHighlightFullBarEnabled(boolean enabled) {
        this.mHighlightFullBarEnabled = enabled;
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.dataprovider.BarDataProvider
    public boolean isHighlightFullBarEnabled() {
        return this.mHighlightFullBarEnabled;
    }

    @Override // com.yucheng.smarthealthpro.customchart.charts.Chart
    public void highlightValue(float x, int dataSetIndex, int stackIndex) {
        highlightValue(new Highlight(x, dataSetIndex, stackIndex), false);
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.dataprovider.BarDataProvider
    public BarData getBarData() {
        return (BarData) this.mData;
    }

    public void setFitBars(boolean enabled) {
        this.mFitBars = enabled;
    }

    public void groupBars(float fromX, float groupSpace, float barSpace) {
        if (getBarData() == null) {
            throw new RuntimeException("You need to set data for the chart before grouping bars.");
        }
        getBarData().groupBars(fromX, groupSpace, barSpace);
        notifyDataSetChanged();
    }
}
