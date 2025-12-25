package com.yucheng.smarthealthpro.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@Deprecated
/* loaded from: classes5.dex */
public class DateUtils {
    public static String getNowDate() {
        return new SimpleDateFormat(AppDateMgr.DF_YYYY_MM_DD, Locale.ENGLISH).format(new Date());
    }

    int getYear() {
        return Integer.parseInt(new SimpleDateFormat("yyyy", Locale.ENGLISH).format(new Date()));
    }

    int getMonth() {
        return Integer.parseInt(new SimpleDateFormat("MM", Locale.ENGLISH).format(new Date()));
    }

    boolean isLeap(int year) {
        int i2 = year % 100;
        if (i2 == 0 && year % 400 == 0) {
            return true;
        }
        return i2 != 0 && year % 4 == 0;
    }

    int getDays(int year, int month) {
        int i2 = isLeap(year) ? 29 : 28;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 2:
                return i2;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            default:
                return 0;
        }
    }

    int getSundays(int year, int month) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE", Locale.ENGLISH);
        Calendar calendar = Calendar.getInstance();
        int i2 = 0;
        for (int i3 = 1; i3 <= getDays(year, month); i3++) {
            calendar.set(5, i3);
            if (simpleDateFormat.format(calendar.getTime()).equals("星期日")) {
                i2++;
            }
        }
        return i2;
    }

    public int getMonthLastDay(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1, year);
        calendar.set(2, month - 1);
        calendar.set(5, 1);
        calendar.roll(5, -1);
        return calendar.get(5);
    }
}
