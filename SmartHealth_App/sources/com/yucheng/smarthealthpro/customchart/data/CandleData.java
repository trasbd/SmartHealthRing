package com.yucheng.smarthealthpro.customchart.data;

import com.yucheng.smarthealthpro.customchart.interfaces.datasets.ICandleDataSet;
import java.util.List;

/* loaded from: classes4.dex */
public class CandleData extends BarLineScatterCandleBubbleData<ICandleDataSet> {
    public CandleData() {
    }

    public CandleData(List<ICandleDataSet> dataSets) {
        super(dataSets);
    }

    public CandleData(ICandleDataSet... dataSets) {
        super(dataSets);
    }
}
