package com.yucheng.smarthealthpro.customchart.formatter;

import com.yucheng.smarthealthpro.customchart.data.LineData;
import com.yucheng.smarthealthpro.customchart.interfaces.dataprovider.LineDataProvider;
import com.yucheng.smarthealthpro.customchart.interfaces.datasets.ILineDataSet;

/* loaded from: classes4.dex */
public class DefaultFillFormatter implements IFillFormatter {
    @Override // com.yucheng.smarthealthpro.customchart.formatter.IFillFormatter
    public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
        float yChartMax = dataProvider.getYChartMax();
        float yChartMin = dataProvider.getYChartMin();
        LineData lineData = dataProvider.getLineData();
        if (dataSet.getYMax() > 0.0f && dataSet.getYMin() < 0.0f) {
            return 0.0f;
        }
        if (lineData.getYMax() > 0.0f) {
            yChartMax = 0.0f;
        }
        if (lineData.getYMin() < 0.0f) {
            yChartMin = 0.0f;
        }
        return dataSet.getYMin() >= 0.0f ? yChartMin : yChartMax;
    }
}
