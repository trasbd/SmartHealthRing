package com.yucheng.smarthealthpro.customchart;

import com.google.android.material.timepicker.TimeModel;
import com.realsil.sdk.dfu.DfuConstants;
import com.yucheng.smarthealthpro.customchart.components.AxisBase;
import com.yucheng.smarthealthpro.customchart.formatter.IAxisValueFormatter;

/* loaded from: classes4.dex */
public class MyTempDayCustomXAxisValueFormatter implements IAxisValueFormatter {
    private boolean drawValue;

    public MyTempDayCustomXAxisValueFormatter(boolean drawValue) {
        this.drawValue = drawValue;
    }

    @Override // com.yucheng.smarthealthpro.customchart.formatter.IAxisValueFormatter
    public String getFormattedValue(float value, AxisBase axis) {
        if (!this.drawValue) {
            return "-";
        }
        if (value >= 100000.0f) {
            return (((int) value) / DfuConstants.MAX_NOTIFICATION_LOCK_WAIT_TIME) + "w -";
        }
        if (value >= 10000.0f) {
            int i2 = (int) value;
            return (i2 / DfuConstants.MAX_NOTIFICATION_LOCK_WAIT_TIME) + "." + ((i2 / 1000) % 10) + "w -";
        }
        int i3 = (int) value;
        return String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(i3 / 60)) + ":" + String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(i3 % 60));
    }
}
