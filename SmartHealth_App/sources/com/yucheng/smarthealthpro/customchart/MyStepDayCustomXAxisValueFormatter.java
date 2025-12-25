package com.yucheng.smarthealthpro.customchart;

import com.realsil.sdk.dfu.DfuConstants;
import com.yucheng.smarthealthpro.customchart.components.AxisBase;
import com.yucheng.smarthealthpro.customchart.formatter.IAxisValueFormatter;

/* loaded from: classes4.dex */
public class MyStepDayCustomXAxisValueFormatter implements IAxisValueFormatter {
    private boolean drawValue;

    public MyStepDayCustomXAxisValueFormatter(boolean drawValue) {
        this.drawValue = drawValue;
    }

    @Override // com.yucheng.smarthealthpro.customchart.formatter.IAxisValueFormatter
    public String getFormattedValue(float value, AxisBase axis) {
        if (this.drawValue) {
            if (value >= 100000.0f) {
                return (((int) value) / DfuConstants.MAX_NOTIFICATION_LOCK_WAIT_TIME) + "w -";
            }
            if (value >= 10000.0f) {
                int i2 = (int) value;
                return (i2 / DfuConstants.MAX_NOTIFICATION_LOCK_WAIT_TIME) + "." + ((i2 / 1000) % 10) + "w -";
            }
            int i3 = (int) value;
            if (i3 == 0) {
                return "00:00";
            }
            if (i3 == 11) {
                return "06:00";
            }
            if (i3 == 23) {
                return "12:00";
            }
            if (i3 == 35) {
                return "18:00";
            }
            if (i3 == 48) {
                return "24:00";
            }
        }
        return "-";
    }
}
