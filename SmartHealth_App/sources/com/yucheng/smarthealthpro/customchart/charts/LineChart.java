package com.yucheng.smarthealthpro.customchart.charts;

import android.content.Context;
import android.util.AttributeSet;
import com.yucheng.smarthealthpro.customchart.data.LineData;
import com.yucheng.smarthealthpro.customchart.interfaces.dataprovider.LineDataProvider;
import com.yucheng.smarthealthpro.customchart.renderer.LineChartRenderer;

/* loaded from: classes4.dex */
public class LineChart extends BarLineChartBase<LineData> implements LineDataProvider {
    public LineChart(Context context) {
        super(context);
    }

    public LineChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LineChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override // com.yucheng.smarthealthpro.customchart.charts.BarLineChartBase, com.yucheng.smarthealthpro.customchart.charts.Chart
    protected void init() {
        super.init();
        this.mRenderer = new LineChartRenderer(this, this.mAnimator, this.mViewPortHandler);
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.dataprovider.LineDataProvider
    public LineData getLineData() {
        return (LineData) this.mData;
    }

    @Override // com.yucheng.smarthealthpro.customchart.charts.Chart, android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        if (this.mRenderer != null && (this.mRenderer instanceof LineChartRenderer)) {
            ((LineChartRenderer) this.mRenderer).releaseBitmap();
        }
        super.onDetachedFromWindow();
    }
}
