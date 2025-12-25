package com.yucheng.smarthealthpro.customchart.listener;

import com.yucheng.smarthealthpro.customchart.data.Entry;
import com.yucheng.smarthealthpro.customchart.highlight.Highlight;

/* loaded from: classes4.dex */
public interface OnChartValueSelectedListener {
    void onNothingSelected();

    void onValueSelected(Entry e2, Highlight h2);
}
