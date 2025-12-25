package com.yucheng.smarthealthpro.home.util;

import com.google.android.material.timepicker.TimeModel;

/* loaded from: classes5.dex */
public class TimeDateUtil {
    public static String intToStr(int year, int month, int day) {
        return year + "-" + String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(month)) + "-" + String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(day));
    }
}
