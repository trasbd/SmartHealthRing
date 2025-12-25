package com.yucheng.smarthealthpro.customchart.data;

import com.yucheng.smarthealthpro.customchart.interfaces.datasets.IBarLineScatterCandleBubbleDataSet;
import java.util.List;

/* loaded from: classes4.dex */
public abstract class BarLineScatterCandleBubbleData<T extends IBarLineScatterCandleBubbleDataSet<? extends Entry>> extends ChartData<T> {
    public BarLineScatterCandleBubbleData() {
    }

    public BarLineScatterCandleBubbleData(T... sets) {
        super(sets);
    }

    public BarLineScatterCandleBubbleData(List<T> sets) {
        super(sets);
    }
}
