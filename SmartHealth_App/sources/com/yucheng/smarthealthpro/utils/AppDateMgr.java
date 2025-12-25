package com.yucheng.smarthealthpro.utils;

import com.dd.plist.ASCIIPropertyListParser;
import com.realsil.sdk.dfu.DfuConstants;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import org.apache.commons.lang3.time.TimeZones;
import org.eclipse.paho.client.mqttv3.MqttTopic;

/* loaded from: classes5.dex */
public class AppDateMgr {
    private static final long DAY = 86400000;
    public static final String DF_HH_MM = "HH:mm";
    public static final String DF_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    public static final String DF_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String Detail_Format = "yyyy-MM-dd HH:mm:ss";
    private static final long HOUR = 3600000;
    private static final long MINUTE = 60000;
    private static final long MONTH = 2678400000L;
    private static final long YEAR = 32140800000L;
    public static final String DF_YYYY_MM_DD = "yyyy-MM-dd";
    public static final SimpleDateFormat YYYYMMDD_FORMAT = new SimpleDateFormat(DF_YYYY_MM_DD, Locale.getDefault());
    public static final String DF_HH_MM_SS = "HH:mm:ss";
    public static final SimpleDateFormat HHMMSS_FORMAT = new SimpleDateFormat(DF_HH_MM_SS, Locale.getDefault());
    public static final SimpleDateFormat YYYYMMDDHHMMSS_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
    public static final SimpleDateFormat HHMMSSMMDDYYYY_FORMAT = new SimpleDateFormat("HH:mm MM/dd/yyyy", Locale.getDefault());
    public static final SimpleDateFormat MMDD_FORMAT = new SimpleDateFormat("MM/dd", Locale.getDefault());
    private static final String[] CHINESE_ZODIAC = {"猴", "鸡", "狗", "猪", "鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊"};
    private static final String[] ZODIAC = {"水瓶座", "双鱼座", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "魔羯座"};
    private static final int[] ZODIAC_FLAGS = {20, 19, 21, 21, 21, 22, 23, 23, 23, 24, 23, 22};
    public static String YYYYMMDD = DF_YYYY_MM_DD;
    private static final String TAG = "AppDateMgr";
    private static SimpleDateFormat second = new SimpleDateFormat("yy-MM-dd hh:mm:ss");
    private static SimpleDateFormat day = new SimpleDateFormat(DF_YYYY_MM_DD, Locale.ENGLISH);
    private static SimpleDateFormat detailDay = new SimpleDateFormat("yyyy年MM月dd日");
    private static SimpleDateFormat fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
    private static SimpleDateFormat tempTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
    private static SimpleDateFormat excelDate = new SimpleDateFormat("yyyy/MM/dd");

    public AppDateMgr() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static TimeZone getBeijingTimeZone() {
        return TimeZone.getTimeZone("GMT+8:00");
    }

    public static TimeZone getPhoneTimeZone() {
        return TimeZone.getDefault();
    }

    public static String getCurrentTimeZoneStr() {
        return createGmtOffsetString(true, true, TimeZone.getDefault().getRawOffset());
    }

    public static String createGmtOffsetString(boolean includeGmt, boolean includeMinuteSeparator, int offsetMillis) {
        char c2;
        int i2 = offsetMillis / DfuConstants.DFU_UPLOAD_IMAGE_TIMEOUT;
        if (i2 < 0) {
            i2 = -i2;
            c2 = ASCIIPropertyListParser.DATE_DATE_FIELD_DELIMITER;
        } else {
            c2 = '+';
        }
        StringBuilder sb = new StringBuilder(9);
        if (includeGmt) {
            sb.append(TimeZones.GMT_ID);
        }
        sb.append(c2);
        appendNumber(sb, 2, i2 / 60);
        if (includeMinuteSeparator) {
            sb.append(ASCIIPropertyListParser.DATE_TIME_FIELD_DELIMITER);
        }
        appendNumber(sb, 2, i2 % 60);
        return sb.toString();
    }

    private static void appendNumber(StringBuilder builder, int count, int value) {
        String string = Integer.toString(value);
        for (int i2 = 0; i2 < count - string.length(); i2++) {
            builder.append('0');
        }
        builder.append(string);
    }

    public static Date changeTimeZone(Date date, TimeZone oldZone, TimeZone newZone) {
        if (date == null) {
            return null;
        }
        return new Date(date.getTime() - (oldZone.getRawOffset() - newZone.getRawOffset()));
    }

    public static String beijingTime2PhoneTime(String beijingTime, String format) {
        return formatDateToStr(changeTimeZone(parseToDate(beijingTime, format), getBeijingTimeZone(), getPhoneTimeZone()), format);
    }

    public static Date parseToDate(String date, String format) {
        try {
            return new SimpleDateFormat(format).parse(date);
        } catch (ParseException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String formatDateToStr(Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }

    public static String phoneTime2BeijingTime(String phoneTime) {
        return formatDateToStr(changeTimeZone(parseToDate(phoneTime, "yyyy-MM-dd HH:mm:ss"), getPhoneTimeZone(), getBeijingTimeZone()), "yyyy-MM-dd HH:mm:ss");
    }

    public static String todayYyyyMmDd() {
        return YYYYMMDD_FORMAT.format(new Date());
    }

    public static String todayHhMmSs() {
        return HHMMSS_FORMAT.format(new Date());
    }

    public static String todayYyyyMmDdHhMmSs() {
        return YYYYMMDDHHMMSS_FORMAT.format(new Date());
    }

    public static int parseYyyy(String dateTime) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(YYYYMMDDHHMMSS_FORMAT.parse(dateTime));
            return calendar.get(1);
        } catch (ParseException e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    public static int parseYyyy(String dateTime, SimpleDateFormat simpleDateFormat) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(simpleDateFormat.parse(dateTime));
            return calendar.get(1);
        } catch (ParseException e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    public static int parseYyyy(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(1);
    }

    public static int parseMm(String dateTime) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(YYYYMMDDHHMMSS_FORMAT.parse(dateTime));
            return calendar.get(2);
        } catch (ParseException e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    public static int parseMm(String dateTime, SimpleDateFormat simpleDateFormat) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(simpleDateFormat.parse(dateTime));
            return calendar.get(2);
        } catch (ParseException e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    public static int parseMm(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(2);
    }

    public static int parseDd(String dateTime) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(YYYYMMDDHHMMSS_FORMAT.parse(dateTime));
            return calendar.get(5);
        } catch (ParseException e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    public static int parseDd(String dateTime, SimpleDateFormat simpleDateFormat) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(simpleDateFormat.parse(dateTime));
            return calendar.get(5);
        } catch (ParseException e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    public static int parseDd(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(5);
    }

    public static String parseYyyyMmDd(String dateTime) {
        try {
            return YYYYMMDD_FORMAT.format(YYYYMMDDHHMMSS_FORMAT.parse(dateTime));
        } catch (ParseException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String parseYyyyMmDd(String dateTime, SimpleDateFormat simpleDateFormat) {
        try {
            return YYYYMMDD_FORMAT.format(simpleDateFormat.parse(dateTime));
        } catch (ParseException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String parseYyyyMmDd(Date date) {
        return YYYYMMDD_FORMAT.format(date);
    }

    public static String parseHhMmSs(String dateTime) {
        try {
            return HHMMSS_FORMAT.format(YYYYMMDDHHMMSS_FORMAT.parse(dateTime));
        } catch (ParseException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String parseHhMmSs(String dateTime, SimpleDateFormat simpleDateFormat) {
        try {
            return HHMMSS_FORMAT.format(simpleDateFormat.parse(dateTime));
        } catch (ParseException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String parseHhMmSs(Date date) {
        return HHMMSS_FORMAT.format(date);
    }

    public static int getWeekNumber(String dateTime) {
        return getWeekNumber(string2Date(dateTime, YYYYMMDDHHMMSS_FORMAT));
    }

    public static int getWeekNumber(String dateTime, SimpleDateFormat simpleDateFormat) {
        return getWeekNumber(string2Date(dateTime, simpleDateFormat));
    }

    public static int getWeekNumber(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(7);
    }

    public static int getWeekOfMonth(String dateTime) {
        return getWeekOfMonth(string2Date(dateTime, YYYYMMDDHHMMSS_FORMAT));
    }

    public static int getWeekOfMonth(String dateTime, SimpleDateFormat simpleDateFormat) {
        return getWeekOfMonth(string2Date(dateTime, simpleDateFormat));
    }

    public static int getWeekOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(4);
    }

    public static int getWeekOfYear(String time) {
        return getWeekOfYear(string2Date(time, YYYYMMDDHHMMSS_FORMAT));
    }

    public static int getWeekOfYear(String time, SimpleDateFormat simpleDateFormat) {
        return getWeekOfYear(string2Date(time, simpleDateFormat));
    }

    public static int getWeekOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(3);
    }

    public static Long dateTimeToTimeStamp(String dateTime) {
        try {
            return Long.valueOf(YYYYMMDDHHMMSS_FORMAT.parse(dateTime).getTime() / 1000);
        } catch (ParseException e2) {
            e2.printStackTrace();
            return 0L;
        }
    }

    public static String timeStampToDateTime(Long timeStamp) {
        return YYYYMMDDHHMMSS_FORMAT.format(new Date(timeStamp.longValue() * 1000));
    }

    public static String timeStampToDateTime2(Long timeStamp) {
        return YYYYMMDDHHMMSS_FORMAT.format(new Date(timeStamp.longValue()));
    }

    public static Date string2Date(String time) {
        return string2Date(time, YYYYMMDDHHMMSS_FORMAT);
    }

    public static Date string2Date(String time, SimpleDateFormat simpleDateFormat) {
        try {
            return simpleDateFormat.parse(time);
        } catch (ParseException e2) {
            e2.printStackTrace();
            return new Date();
        }
    }

    public static String date2String(Date date) {
        return date2String(date, YYYYMMDDHHMMSS_FORMAT);
    }

    public static String date2String(Date date, SimpleDateFormat simpleDateFormat) {
        return simpleDateFormat.format(date);
    }

    public static boolean dateIsBefore(String standDate, String desDate) {
        try {
            SimpleDateFormat simpleDateFormat = YYYYMMDDHHMMSS_FORMAT;
            return simpleDateFormat.parse(desDate).before(simpleDateFormat.parse(standDate));
        } catch (ParseException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static boolean dateIsBeforeDay(String standDate, String desDate) {
        try {
            SimpleDateFormat simpleDateFormat = YYYYMMDD_FORMAT;
            return simpleDateFormat.parse(desDate).before(simpleDateFormat.parse(standDate));
        } catch (ParseException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static long minutesBetweenTwoDate(String beginDate, String endDate) {
        return (dateTimeToTimeStamp(endDate).longValue() - dateTimeToTimeStamp(beginDate).longValue()) / 60;
    }

    public static String getChineseZodiac(String dateTime) {
        return getChineseZodiac(parseYyyy(dateTime));
    }

    public static String getChineseZodiac(String dateTime, SimpleDateFormat simpleDateFormat) {
        return getChineseZodiac(parseYyyy(dateTime, simpleDateFormat));
    }

    public static String getChineseZodiac(Date date) {
        return getChineseZodiac(parseYyyy(date));
    }

    public static String getChineseZodiac(int year) {
        return CHINESE_ZODIAC[year % 12];
    }

    public static String getZodiac(String dateTime) {
        return getZodiac(parseMm(dateTime), parseDd(dateTime));
    }

    public static String getZodiac(String dateTime, SimpleDateFormat simpleDateFormat) {
        return getZodiac(parseMm(dateTime, simpleDateFormat), parseDd(dateTime, simpleDateFormat));
    }

    public static String getZodiac(Date date) {
        return getZodiac(parseMm(date), parseDd(date));
    }

    public static String getZodiac(int month, int day2) {
        String[] strArr = ZODIAC;
        int i2 = month - 1;
        if (day2 < ZODIAC_FLAGS[i2]) {
            i2 = (month + 10) % 12;
        }
        return strArr[i2];
    }

    public String getNowDayOffset(int offset) {
        return new SimpleDateFormat(DF_YYYY_MM_DD, Locale.ENGLISH).format(new Date(Calendar.getInstance().getTimeInMillis() + (offset * 86400000)));
    }

    public String getTime(long time) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).format(new Date(time));
    }

    public void forward(Calendar cal) {
        int i2 = cal.get(1);
        int i3 = cal.get(2);
        if (cal.get(5) != getDaysOfMonth(i2, i3 + 1)) {
            cal.roll(5, 1);
            return;
        }
        if (i3 == 11) {
            cal.roll(1, true);
            cal.set(2, 0);
            cal.set(5, 1);
        } else {
            cal.roll(2, true);
            cal.set(5, 1);
        }
    }

    public void backward(Calendar cal) {
        int i2 = cal.get(2);
        int daysOfMonth = getDaysOfMonth(cal.get(1), i2);
        if (cal.get(5) != 1) {
            cal.roll(5, false);
            return;
        }
        if (i2 == 0) {
            cal.roll(1, false);
            cal.set(2, 11);
            cal.set(5, 31);
        } else {
            cal.roll(2, false);
            cal.set(5, daysOfMonth);
        }
    }

    public boolean isLeapYear(int year) {
        if (year % 400 == 0) {
            return true;
        }
        return year % 100 != 0 && year % 4 == 0;
    }

    public int getDaysOfMonth(int year, int month) {
        if (month < 1 || month > 12) {
            return 0;
        }
        boolean zIsLeapYear = isLeapYear(year);
        switch (month) {
            case 2:
                if (zIsLeapYear) {
                }
                break;
        }
        return 0;
    }

    public long secondsMorning(Calendar c2) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(c2.get(1), c2.get(2), c2.get(5), 0, 0, 0);
        return calendar.getTimeInMillis();
    }

    public long secondsNight(Calendar c2) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(c2.get(1), c2.get(2), c2.get(5), 0, 0, 0);
        forward(calendar);
        return calendar.getTimeInMillis();
    }

    public boolean isSameDay(Calendar c1, Calendar c2) {
        return c1.get(1) == c2.get(1) && c1.get(2) == c2.get(2) && c1.get(5) == c2.get(5);
    }

    public static String formatFriendly(Date date) {
        if (date == null) {
            return null;
        }
        long time = new Date().getTime() - date.getTime();
        if (time > YEAR) {
            return (time / YEAR) + "年前";
        }
        if (time > MONTH) {
            return (time / MONTH) + "个月前";
        }
        if (time > 86400000) {
            return (time / 86400000) + "天前";
        }
        if (time > 3600000) {
            return (time / 3600000) + "个小时前";
        }
        if (time > 60000) {
            return (time / 60000) + "分钟前";
        }
        return "刚刚";
    }

    public static String formatDateTime(long dateL) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(dateL));
    }

    public static String formatDateTime(long dateL, String formater) {
        return new SimpleDateFormat(formater).format(new Date(dateL));
    }

    public static String formatDateTime(long dateL, SimpleDateFormat formater) {
        return formater.format(new Date(dateL));
    }

    public static String formatDateTime(Date date, String formater) {
        return new SimpleDateFormat(formater).format(date);
    }

    public static Date parseDate(String strDate) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(strDate);
        } catch (ParseException unused) {
            return null;
        }
    }

    public static Date gainCurrentDate() {
        return new Date();
    }

    public static boolean compareDate(Date target1, Date target2) {
        try {
            return formatDateTime(target1, "yyyy-MM-dd HH:mm:ss").compareTo(formatDateTime(target2, "yyyy-MM-dd HH:mm:ss")) <= 0;
        } catch (Exception unused) {
            return false;
        }
    }

    public static Date addDateTime(Date target, double hour) {
        return (target == null || hour < 0.0d) ? target : new Date(target.getTime() + ((long) (hour * 60.0d * 60.0d * 1000.0d)));
    }

    public static Date subDateTime(Date target, double hour) {
        return (target == null || hour < 0.0d) ? target : new Date(target.getTime() - ((long) (((hour * 60.0d) * 60.0d) * 1000.0d)));
    }

    public static String formatDateForExcelDate(Date date) {
        return excelDate.format(date);
    }

    public static String formatDateForFileName(Date date) {
        return fileName.format(date);
    }

    public static String formatDateSecond(Date date) {
        return second.format(date);
    }

    public static String tempDateSecond(Date date) {
        return tempTime.format(date);
    }

    public static Date tempDateSecond(String str) {
        try {
            return tempTime.parse(str);
        } catch (ParseException e2) {
            e2.printStackTrace();
            return new Date();
        }
    }

    public static String formatDateDay(Date date) {
        return day.format(date);
    }

    public static String formatDateDetailDay(Date date) {
        return detailDay.format(date);
    }

    public static String formatNumber(double number) {
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.applyPattern("#0.00");
        return decimalFormat.format(number);
    }

    public static Date formateDate(String date) throws Exception {
        return day.parse(date);
    }

    public static Date parseStringToDate(String date) throws Exception {
        return day.parse(date);
    }

    public static String formatDoubleNumber(double number) {
        return new DecimalFormat(MqttTopic.MULTI_LEVEL_WILDCARD).format(number);
    }

    public static long getTimeMillis(Date date) {
        return date.getTime();
    }

    public static long getCurrentDayTimeMillis() {
        return System.currentTimeMillis();
    }

    public static long convertMillisecond(String day2, String format) {
        if (day2 != null && format != null) {
            try {
                return new SimpleDateFormat(format).parse(day2).getTime();
            } catch (ParseException e2) {
                e2.printStackTrace();
            }
        }
        return 0L;
    }

    public static int getDateInterval(String sdate1, String sdate2) {
        long time;
        try {
            Date date = new SimpleDateFormat(DF_YYYY_MM_DD, Locale.ENGLISH).parse(sdate1);
            Date date2 = new SimpleDateFormat(DF_YYYY_MM_DD, Locale.ENGLISH).parse(sdate2);
            time = (date2.getTime() - date.getTime()) / 86400000;
        } catch (ParseException e2) {
            e2.printStackTrace();
            time = 0;
        }
        return (int) time;
    }

    public static int compareTime(String format, String time1, String time2) {
        if (format == null || time1 == null || time2 == null) {
            return 0;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Calendar calendar = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        try {
            calendar.setTime(simpleDateFormat.parse(time1));
            calendar2.setTime(simpleDateFormat.parse(time2));
        } catch (ParseException e2) {
            e2.printStackTrace();
        }
        return calendar.compareTo(calendar2);
    }

    public static String time_offset(Date date) {
        return String.format("%tz", date);
    }

    public static String am_or_pm(Date date) {
        return String.format("%tp", date);
    }

    public static String subtle(Date date) {
        return String.format("%tN", date);
    }

    public static String mill(Date date) {
        return String.format("%tL", date);
    }

    public static String second(Date date) {
        return String.format("%tS", date);
    }

    public static String minute(Date date) {
        return String.format("%tM", date);
    }

    public static String hour_l(Date date) {
        return String.format("%tl", date);
    }

    public static String hour_H(Date date) {
        return String.format("%tH", date);
    }

    public static String hour_minute(Date date) {
        return String.format("%tR", date);
    }

    public static String hour_minute_second(Date date) {
        return String.format("%tT", date);
    }

    public static String hour_minute_second_pm_or_am(Date date) {
        return String.format("%tr", date);
    }

    public static String mdy(Date date) {
        return String.format("%tD", date);
    }

    public static String ymd(Date date) {
        return String.format("%tF", date);
    }

    public static String day_one(Date date) {
        return String.format("%te", date);
    }

    public static String day_two(Date date) {
        return String.format("%td", date);
    }

    public static String day_to_year(Date date) {
        return String.format("%tj", date);
    }

    public static String month_referred(Date date) {
        return String.format("%tb", date);
    }

    public static String month_full_name(Date date) {
        return String.format("%tB", date);
    }

    public static String month(Date date) {
        return String.format("%tm", date);
    }

    public static String week_referred(Date date) {
        return String.format("%ta", date);
    }

    public static String week_full_name(Date date) {
        return String.format("%tA", date);
    }

    public static String year_referred(Date date) {
        return String.format("%ty", date);
    }

    public static String year_full_name(Date date) {
        return String.format("%tY", date);
    }

    public static String time(Date date) {
        return String.format("%tc", date);
    }

    public static String time_to_second(Date date) {
        return String.format("%ts", date);
    }

    public static String time_to_mill(Date date) {
        return String.format("%tQ", date);
    }

    public static long time_to_mill() {
        return System.currentTimeMillis();
    }

    public static boolean checkIsToday(long timeMiliie) {
        Calendar calendar = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(new Date(timeMiliie));
        return calendar.get(6) == calendar2.get(6) || timeMiliie == 0;
    }
}
