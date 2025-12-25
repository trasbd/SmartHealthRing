package com.yucheng.smarthealthpro.customchart;

import com.orhanobut.logger.Logger;
import com.realsil.sdk.dfu.DfuConstants;
import com.yucheng.smarthealthpro.customchart.components.AxisBase;
import com.yucheng.smarthealthpro.customchart.formatter.IAxisValueFormatter;
import com.yucheng.smarthealthpro.utils.YearToDayListUtils;
import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: classes4.dex */
public class MyHalfYearCustomXAxisValueFormatter implements IAxisValueFormatter {
    private ArrayList<String> dateString;
    private boolean drawValue;

    public MyHalfYearCustomXAxisValueFormatter(boolean drawValue, int n) {
        this.drawValue = drawValue;
        this.dateString = YearToDayListUtils.getPostStringDateFromMonth(n);
        Logger.d("chong--------dateString==" + Arrays.toString(this.dateString.toArray()));
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
        if (value < 0.0f) {
            return this.dateString.get(0);
        }
        int i3 = (int) value;
        if (i3 == 0) {
            return this.dateString.get(1);
        }
        if (i3 == 1) {
            return this.dateString.get(2);
        }
        if (i3 == 3) {
            return this.dateString.get(3);
        }
        if (i3 == 4) {
            return this.dateString.get(4);
        }
        if (i3 == 6) {
            return this.dateString.get(5);
        }
        return "";
    }
}
