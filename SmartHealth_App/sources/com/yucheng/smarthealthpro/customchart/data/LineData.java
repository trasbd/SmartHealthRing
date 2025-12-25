package com.yucheng.smarthealthpro.customchart.data;

import com.yucheng.smarthealthpro.customchart.interfaces.datasets.ILineDataSet;
import java.util.List;

/* loaded from: classes4.dex */
public class LineData extends BarLineScatterCandleBubbleData<ILineDataSet> {
    public LineData() {
    }

    public LineData(ILineDataSet... dataSets) {
        super(dataSets);
    }

    public LineData(List<ILineDataSet> dataSets) {
        super(dataSets);
    }
}
