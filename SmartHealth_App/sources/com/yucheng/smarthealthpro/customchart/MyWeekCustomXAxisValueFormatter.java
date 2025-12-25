package com.yucheng.smarthealthpro.customchart;

import com.realsil.sdk.dfu.DfuConstants;
import com.yucheng.smarthealthpro.customchart.components.AxisBase;
import com.yucheng.smarthealthpro.customchart.formatter.IAxisValueFormatter;
import com.yucheng.smarthealthpro.utils.TimeStampUtils;
import com.yucheng.smarthealthpro.utils.YearToDayListUtils;
import java.util.ArrayList;
import java.util.Calendar;

/* loaded from: classes4.dex */
public class MyWeekCustomXAxisValueFormatter implements IAxisValueFormatter {
    private boolean drawValue;

    public MyWeekCustomXAxisValueFormatter(boolean drawValue) {
        this.drawValue = drawValue;
    }

    @Override // com.yucheng.smarthealthpro.customchart.formatter.IAxisValueFormatter
    public String getFormattedValue(float value, AxisBase axis) {
        Calendar calendar = Calendar.getInstance();
        ArrayList<String> pastStringArray = YearToDayListUtils.getPastStringArray(calendar.get(1) + "-" + (calendar.get(2) + 1) + "-" + calendar.get(5), 6);
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
            return TimeStampUtils.dateForStringDate(TimeStampUtils.stringForDateDay(pastStringArray.get(0)));
        }
        if (i3 == 1) {
            return TimeStampUtils.dateForStringDate(TimeStampUtils.stringForDateDay(pastStringArray.get(1)));
        }
        if (i3 == 2) {
            return TimeStampUtils.dateForStringDate(TimeStampUtils.stringForDateDay(pastStringArray.get(2)));
        }
        if (i3 == 3) {
            return TimeStampUtils.dateForStringDate(TimeStampUtils.stringForDateDay(pastStringArray.get(3)));
        }
        if (i3 == 4) {
            return TimeStampUtils.dateForStringDate(TimeStampUtils.stringForDateDay(pastStringArray.get(4)));
        }
        if (i3 == 5) {
            return TimeStampUtils.dateForStringDate(TimeStampUtils.stringForDateDay(pastStringArray.get(5)));
        }
        if (i3 == 6) {
            return TimeStampUtils.dateForStringDate(TimeStampUtils.stringForDateDay(pastStringArray.get(6)));
        }
        return "";
    }
}
