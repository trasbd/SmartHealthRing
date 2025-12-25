package com.yucheng.smarthealthpro.customchart.utils;

import com.orhanobut.logger.Logger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* loaded from: classes4.dex */
public class Time {
    public static void main(String[] args) {
        Logger.d("时间戳转换为时间:" + stampToDate(1603288800));
        Logger.d("时间转换为时间戳:" + dateToStamp("2018-12-18 10:04:59"));
    }

    public static String dateToStamp(String s) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        if (!"".equals(s)) {
            try {
                return String.valueOf(simpleDateFormat.parse(s).getTime() / 1000);
            } catch (Exception unused) {
                Logger.d("传入了null值");
                return "";
            }
        }
        return String.valueOf(System.currentTimeMillis() / 1000);
    }

    public static String stampToDate(long time) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).format(new Date(time * 1000));
    }
}
