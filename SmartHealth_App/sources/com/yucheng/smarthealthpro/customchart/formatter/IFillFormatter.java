package com.yucheng.smarthealthpro.customchart.formatter;

import com.yucheng.smarthealthpro.customchart.interfaces.dataprovider.LineDataProvider;
import com.yucheng.smarthealthpro.customchart.interfaces.datasets.ILineDataSet;

/* loaded from: classes4.dex */
public interface IFillFormatter {
    float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider);
}
