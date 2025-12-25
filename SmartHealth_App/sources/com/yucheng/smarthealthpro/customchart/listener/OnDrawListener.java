package com.yucheng.smarthealthpro.customchart.listener;

import com.yucheng.smarthealthpro.customchart.data.DataSet;
import com.yucheng.smarthealthpro.customchart.data.Entry;

/* loaded from: classes4.dex */
public interface OnDrawListener {
    void onDrawFinished(DataSet<?> dataSet);

    void onEntryAdded(Entry entry);

    void onEntryMoved(Entry entry);
}
