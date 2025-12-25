package com.yucheng.smarthealthpro.utils;

import com.google.android.material.timepicker.TimeModel;
import com.yucheng.ycbtsdk.Constants;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/* loaded from: classes5.dex */
public class TimeStampUtils {
    public static String parseHour(int runTime) {
        int i2 = runTime - (runTime % 60);
        int i3 = (i2 - (((i2 / 60) % 60) * 60)) / Constants.DATATYPE.FactoryTest;
        if (i3 >= 10) {
            return i3 + "";
        }
        return "0" + i3;
    }

    public static String parseMinute(int runTime) {
        int i2 = ((runTime - (runTime % 60)) / 60) % 60;
        if (i2 >= 10) {
            return i2 + "";
        }
        return "0" + i2;
    }

    public static String parseSecond(int runTime) {
        StringBuilder sb;
        StringBuilder sbAppend;
        int i2 = runTime % 60;
        int i3 = runTime / 60;
        int i4 = i3 % 60;
        int i5 = (i3 / 60) % 60;
        StringBuilder sbAppend2 = new StringBuilder().append((i5 >= 10 ? new StringBuilder() : new StringBuilder("0")).append(i5).append(":").toString()).append((i4 >= 10 ? new StringBuilder() : new StringBuilder("0")).append(i4).append(":").toString());
        if (i2 >= 10) {
            sb = new StringBuilder();
            sbAppend = sb.append(i2).append("");
        } else {
            sb = new StringBuilder("0");
            sbAppend = sb.append(i2);
        }
        return sbAppend2.append(sbAppend.toString()).toString();
    }

    public static String getToDay() {
        return new SimpleDateFormat(AppDateMgr.DF_YYYY_MM_DD, Locale.ENGLISH).format(new Date());
    }

    public static Date stampForDate(Integer timestamp) {
        return new Date(timestamp.intValue() * 1000);
    }

    public static Date longStampForDate(long timestamp) {
        return new Date(timestamp);
    }

    public static Long getStringToTimestamp(String time, String sdf) {
        long time2;
        try {
            time2 = new SimpleDateFormat(sdf).parse(time).getTime();
        } catch (ParseException e2) {
            e2.printStackTrace();
            time2 = 0;
        }
        return Long.valueOf(time2);
    }

    public static String dateForString(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).format(date);
    }

    public static String dateForString(Date date, String format) {
        return new SimpleDateFormat(format, Locale.getDefault()).format(date);
    }

    public static String dateForStringYearToDate(Date date) {
        return new SimpleDateFormat(AppDateMgr.DF_YYYY_MM_DD, Locale.ENGLISH).format(date);
    }

    public static String toFormatDate(String strDate) {
        try {
            return new SimpleDateFormat("MM-dd").format(new SimpleDateFormat(AppDateMgr.DF_YYYY_MM_DD, Locale.ENGLISH).parse(strDate));
        } catch (ParseException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String dateForStringYearToMonth(Date date) {
        return new SimpleDateFormat("yyyy/MM").format(date);
    }

    public static String dateForStringDate(Date date) {
        return new SimpleDateFormat("MM/dd").format(date);
    }

    public static String dateForStringMonthDate(Date date) {
        return new SimpleDateFormat("MM").format(date);
    }

    public static String dateForStringDates(Date date) {
        return new SimpleDateFormat("MM-dd").format(date);
    }

    public static String dateForStringToDate(Date date) {
        return new SimpleDateFormat(AppDateMgr.DF_HH_MM, Locale.ENGLISH).format(date);
    }

    public static String formatHourMinute(Long timeStamp) {
        return new SimpleDateFormat(AppDateMgr.DF_HH_MM).format(new Date(timeStamp.longValue()));
    }

    public static String cal(int second) {
        int i2;
        int i3 = second % Constants.DATATYPE.FactoryTest;
        int i4 = 0;
        if (second > 3600) {
            int i5 = second / Constants.DATATYPE.FactoryTest;
            if (i3 == 0) {
                i3 = 0;
                i2 = 0;
            } else if (i3 > 60) {
                i2 = i3 / 60;
                i3 %= 60;
                if (i3 == 0) {
                    i3 = 0;
                }
            } else {
                i2 = 0;
            }
            i4 = i5;
        } else {
            int i6 = second / 60;
            int i7 = second % 60;
            i2 = i6;
            i3 = i7 != 0 ? i7 : 0;
        }
        return String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(i4)) + ":" + String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(i2)) + ":" + String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(i3));
    }

    public static String dateForStringToDateHHmmss(Date date) {
        return new SimpleDateFormat(AppDateMgr.DF_HH_MM_SS, Locale.ENGLISH).format(date);
    }

    public static String dateForStringToHourDate(Date date) {
        return new SimpleDateFormat("HH").format(date);
    }

    public static Date stringForDate(String time) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).parse(time);
        } catch (ParseException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static Date stringForDateDay(String time) {
        try {
            return new SimpleDateFormat(AppDateMgr.DF_YYYY_MM_DD, Locale.ENGLISH).parse(time);
        } catch (ParseException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static long getStringToDate(String dateString, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = new Date();
        try {
            date = simpleDateFormat.parse(dateString);
        } catch (ParseException e2) {
            e2.printStackTrace();
        }
        return date.getTime();
    }

    public static Integer dateForStamp(Date data) {
        return Integer.valueOf((int) (data.getTime() / 1000));
    }

    public long getDistanceTime(long time1, long time2) {
        long j2 = time1 < time2 ? time2 - time1 : time1 - time2;
        long j3 = j2 / 86400000;
        long j4 = (j2 / org.apache.commons.lang3.time.DateUtils.MILLIS_PER_HOUR) - (24 * j3);
        return (((j2 / 1000) - (j3 * 86400)) - (j4 * 3600)) - ((((j2 / 60000) - (1440 * j3)) - (j4 * 60)) * 60);
    }

    public static boolean isSameDate(long a2, long b2) {
        Calendar calendar = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar.setTimeInMillis(a2);
        calendar2.setTimeInMillis(b2);
        return calendar.get(1) == calendar2.get(1) && calendar.get(6) == calendar2.get(6);
    }

    public static int getMinForDay(long time) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTimeInMillis(time);
        return (gregorianCalendar.get(11) * 60) + gregorianCalendar.get(12);
    }

    public static int getSecondsForDay(long time) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTimeInMillis(time);
        int i2 = gregorianCalendar.get(11);
        int i3 = gregorianCalendar.get(12);
        return (i2 * Constants.DATATYPE.FactoryTest) + (i3 * 60) + gregorianCalendar.get(13);
    }
}
