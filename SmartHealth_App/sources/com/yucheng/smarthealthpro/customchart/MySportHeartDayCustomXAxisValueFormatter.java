package com.yucheng.smarthealthpro.customchart;

import com.yucheng.smarthealthpro.customchart.components.AxisBase;
import com.yucheng.smarthealthpro.customchart.formatter.IAxisValueFormatter;
import com.yucheng.smarthealthpro.utils.TimeStampUtils;

/* loaded from: classes4.dex */
public class MySportHeartDayCustomXAxisValueFormatter implements IAxisValueFormatter {
    private boolean drawValue;

    public MySportHeartDayCustomXAxisValueFormatter(boolean drawValue) {
        this.drawValue = drawValue;
    }

    @Override // com.yucheng.smarthealthpro.customchart.formatter.IAxisValueFormatter
    public String getFormattedValue(float value, AxisBase axis) {
        if (this.drawValue) {
            return TimeStampUtils.cal((int) value);
        }
        return "-";
    }
}
