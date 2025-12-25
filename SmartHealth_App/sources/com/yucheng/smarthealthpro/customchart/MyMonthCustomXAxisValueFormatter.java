package com.yucheng.smarthealthpro.customchart;

import com.realsil.sdk.dfu.DfuConstants;
import com.yucheng.smarthealthpro.customchart.components.AxisBase;
import com.yucheng.smarthealthpro.customchart.formatter.IAxisValueFormatter;
import com.yucheng.smarthealthpro.utils.TimeStampUtils;
import com.yucheng.smarthealthpro.utils.YearToDayListUtils;
import java.util.ArrayList;
import java.util.Calendar;

/* loaded from: classes4.dex */
public class MyMonthCustomXAxisValueFormatter implements IAxisValueFormatter {
    private int days;
    private boolean drawValue;

    public MyMonthCustomXAxisValueFormatter(boolean drawValue, int days) {
        this.drawValue = drawValue;
        this.days = days;
    }

    @Override // com.yucheng.smarthealthpro.customchart.formatter.IAxisValueFormatter
    public String getFormattedValue(float value, AxisBase axis) {
        Calendar calendar = Calendar.getInstance();
        ArrayList<String> pastStringArray = YearToDayListUtils.getPastStringArray(calendar.get(1) + "-" + (calendar.get(2) + 1) + "-" + calendar.get(5), this.days - 1);
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
                return TimeStampUtils.dateForStringDate(TimeStampUtils.stringForDateDay(pastStringArray.get(0)));
            }
            if (i3 == this.days) {
                return TimeStampUtils.dateForStringDate(TimeStampUtils.stringForDateDay(pastStringArray.get(pastStringArray.size() - 1)));
            }
        }
        return "-";
    }
}
