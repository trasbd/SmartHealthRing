package com.yucheng.smarthealthpro.customchart;

import com.realsil.sdk.dfu.DfuConstants;
import com.yucheng.smarthealthpro.customchart.components.AxisBase;
import com.yucheng.smarthealthpro.customchart.formatter.IAxisValueFormatter;
import com.yucheng.smarthealthpro.utils.YearToDayListUtils;
import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes4.dex */
public class MyYearCustomXAxisValueFormatter implements IAxisValueFormatter {
    private ArrayList<String> dateString;
    private boolean drawValue;

    public MyYearCustomXAxisValueFormatter(boolean drawValue, int n) {
        this.drawValue = drawValue;
        this.dateString = YearToDayListUtils.getPostStringDateFromMonth(n);
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
        if (i3 == 0) {
            return this.dateString.get(0);
        }
        if (i3 == this.dateString.size()) {
            return this.dateString.get(r3.size() - 1);
        }
        return StringUtils.SPACE;
    }
}
