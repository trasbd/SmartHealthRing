package com.yucheng.smarthealthpro.utils;

import androidx.exifinterface.media.ExifInterface;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

/* loaded from: classes5.dex */
public class YearToDayListUtils {
    public static ArrayList<String> getPastByMonthDayArray(String time, int num) {
        ArrayList<String> arrayList = new ArrayList<>();
        Iterator<String> it2 = getPastStringArray(time, num).iterator();
        while (it2.hasNext()) {
            arrayList.add(TimeStampUtils.dateForStringDates(TimeStampUtils.stringForDateDay(it2.next())));
        }
        return arrayList;
    }

    public static ArrayList<String> getPastStringArray(String time, int num) {
        try {
            return getPastStringArrayByDate(new SimpleDateFormat(AppDateMgr.DF_YYYY_MM_DD, Locale.ENGLISH).parse(time), num);
        } catch (ParseException e2) {
            e2.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static ArrayList<String> getPastStringArrayByDate(Date date, int num) {
        ArrayList<String> arrayList = new ArrayList<>();
        while (num >= 0) {
            arrayList.add(getPastDateString(num, date));
            num--;
        }
        return arrayList;
    }

    public static String getPastDateString(int past, Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(5, calendar.get(5) - past);
        return new SimpleDateFormat(AppDateMgr.DF_YYYY_MM_DD, Locale.ENGLISH).format(calendar.getTime());
    }

    public static Date getPastDate(int past, Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(5, calendar.get(5) - past);
        return calendar.getTime();
    }

    public static int getMonthLastDay(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1, year);
        calendar.set(2, month - 1);
        calendar.set(5, 1);
        calendar.roll(5, -1);
        return calendar.get(5);
    }

    public static int getCurrMonthDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(5, 1);
        calendar.roll(5, -1);
        return calendar.get(5);
    }

    public static int getHourFromDateString(String dateString) {
        SimpleDateFormat simpleDateFormat;
        try {
            if (dateString.contains(ExifInterface.GPS_DIRECTION_TRUE)) {
                simpleDateFormat = new SimpleDateFormat("yyyyMMdd'T'HH:mm:ss");
            } else if (dateString.contains("-") && dateString.contains(":")) {
                simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
            } else {
                if (!dateString.contains(":")) {
                    return -1;
                }
                simpleDateFormat = new SimpleDateFormat(AppDateMgr.DF_HH_MM_SS, Locale.ENGLISH);
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(simpleDateFormat.parse(dateString));
            return calendar.get(11);
        } catch (Exception e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    public static int[] getTimeFromDateString(String dateString) {
        SimpleDateFormat simpleDateFormat;
        int[] iArr = new int[3];
        try {
            if (dateString.contains(ExifInterface.GPS_DIRECTION_TRUE)) {
                simpleDateFormat = new SimpleDateFormat("yyyyMMdd'T'HH:mm:ss");
            } else if (dateString.contains("-") && dateString.contains(":")) {
                simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
            } else {
                if (!dateString.contains(":")) {
                    return null;
                }
                simpleDateFormat = new SimpleDateFormat(AppDateMgr.DF_HH_MM_SS, Locale.ENGLISH);
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(simpleDateFormat.parse(dateString));
            iArr[0] = calendar.get(11);
            iArr[1] = calendar.get(12);
            iArr[2] = calendar.get(13);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return iArr;
    }

    public static int getHourFromTimeStamp(long timeStamp) {
        try {
            Date date = new Date();
            date.setTime(timeStamp);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return calendar.get(11);
        } catch (Exception e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    public static String getStringDateFromMonth(int month) {
        String str = month + "";
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.add(2, month - (calendar.get(2) + 1));
            return new SimpleDateFormat("yyyy-MM").format(calendar.getTime());
        } catch (Exception e2) {
            e2.printStackTrace();
            return str;
        }
    }

    public static ArrayList<String> getPostStringDateFromMonth(int n) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i2 = n - 1; i2 >= 0; i2--) {
            try {
                Calendar calendar = Calendar.getInstance();
                calendar.get(2);
                calendar.add(2, -i2);
                arrayList.add(new SimpleDateFormat("yyyy/MM").format(calendar.getTime()));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return arrayList;
    }

    public static boolean isMidDate(Date starDate, String stringDate, Date endDate) {
        try {
            Date date = new SimpleDateFormat(AppDateMgr.DF_YYYY_MM_DD, Locale.ENGLISH).parse(stringDate);
            if (date.before(endDate)) {
                return date.after(starDate);
            }
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static int getAge(String stringDate) {
        try {
            Calendar calendar = Calendar.getInstance();
            int i2 = calendar.get(1);
            calendar.setTime(new SimpleDateFormat(AppDateMgr.DF_YYYY_MM_DD, Locale.ENGLISH).parse(stringDate));
            return i2 - calendar.get(1);
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    public static String subYear(int n) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.add(1, -n);
            return new SimpleDateFormat(AppDateMgr.DF_YYYY_MM_DD, Locale.ENGLISH).format(calendar.getTime());
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }
}
