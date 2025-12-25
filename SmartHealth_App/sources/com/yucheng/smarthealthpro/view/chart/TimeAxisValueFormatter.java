package com.yucheng.smarthealthpro.view.chart;

import android.util.Log;
import com.amap.api.services.core.AMapException;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import com.yucheng.smarthealthpro.customchart.components.AxisBase;
import com.yucheng.smarthealthpro.customchart.formatter.IAxisValueFormatter;
import com.yucheng.smarthealthpro.utils.AppDateMgr;
import java.text.SimpleDateFormat;
import java.util.Locale;

/* loaded from: classes5.dex */
public class TimeAxisValueFormatter implements IAxisValueFormatter {
    private final SimpleDateFormat sdf = new SimpleDateFormat(AppDateMgr.DF_HH_MM, Locale.ENGLISH);
    private int max = AMapException.CODE_AMAP_SERVICE_INVALID_PARAMS;

    public void setMax(int max) {
        this.max = max;
    }

    public int getMax() {
        return this.max;
    }

    @Override // com.yucheng.smarthealthpro.customchart.formatter.IAxisValueFormatter
    public String getFormattedValue(float value, AxisBase axis) {
        Log.e(ViewHierarchyConstants.TAG_KEY, "value:" + value);
        if (value < 0.0f) {
            value = 1440.0f - Math.abs(value);
        }
        if (value > 1440.0f) {
            value -= 1440.0f;
        }
        return String.format("%02d:%02d", Integer.valueOf((int) (value / 60.0f)), Integer.valueOf((int) (value % 60.0f)));
    }
}
