package com.yucheng.smarthealthpro.customchart.charts;

import android.content.Context;
import android.util.AttributeSet;
import com.yucheng.smarthealthpro.customchart.data.CandleData;
import com.yucheng.smarthealthpro.customchart.interfaces.dataprovider.CandleDataProvider;
import com.yucheng.smarthealthpro.customchart.renderer.CandleStickChartRenderer;

/* loaded from: classes4.dex */
public class CandleStickChart extends BarLineChartBase<CandleData> implements CandleDataProvider {
    public CandleStickChart(Context context) {
        super(context);
    }

    public CandleStickChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CandleStickChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override // com.yucheng.smarthealthpro.customchart.charts.BarLineChartBase, com.yucheng.smarthealthpro.customchart.charts.Chart
    protected void init() {
        super.init();
        this.mRenderer = new CandleStickChartRenderer(this, this.mAnimator, this.mViewPortHandler);
        getXAxis().setSpaceMin(0.5f);
        getXAxis().setSpaceMax(0.5f);
    }

    @Override // com.yucheng.smarthealthpro.customchart.interfaces.dataprovider.CandleDataProvider
    public CandleData getCandleData() {
        return (CandleData) this.mData;
    }
}
