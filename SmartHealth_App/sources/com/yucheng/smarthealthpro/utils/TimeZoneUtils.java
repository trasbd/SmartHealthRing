package com.yucheng.smarthealthpro.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import org.apache.commons.lang3.time.TimeZones;

/* loaded from: classes5.dex */
public class TimeZoneUtils {
    public static String getTimeZone() {
        String str = new SimpleDateFormat("Z").format(Calendar.getInstance(TimeZone.getTimeZone(TimeZones.GMT_ID), Locale.getDefault()).getTime());
        return "" + str.substring(0, 3) + ":" + str.substring(3, 5);
    }

    public static String getTimeZoneOffset() {
        int offset = ((new GregorianCalendar().getTimeZone().getOffset(System.currentTimeMillis()) / 1000) / 60) / 60;
        return (offset > 0 ? new StringBuilder("+") : new StringBuilder("")).append(offset).toString();
    }
}
