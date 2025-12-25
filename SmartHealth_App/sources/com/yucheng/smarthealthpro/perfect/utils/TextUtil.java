package com.yucheng.smarthealthpro.perfect.utils;

import android.graphics.Paint;
import android.text.TextPaint;

/* loaded from: classes5.dex */
public class TextUtil {
    public static float getFontlength(TextPaint paint, String str) {
        return paint.measureText(str);
    }

    public static float getFontHeight(Paint paint) {
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        return fontMetrics.descent - fontMetrics.ascent;
    }

    public static String getNoString(int no2) {
        if (no2 > 19) {
            return tranNoString(no2 / 10) + "十" + tranNoString(no2 % 10);
        }
        if (no2 > 9) {
            return "十" + tranNoString(no2 % 10);
        }
        return tranNoString(no2);
    }

    public static String tranNoString(int no2) {
        switch (no2) {
            case 1:
                return "一";
            case 2:
                return "二";
            case 3:
                return "三";
            case 4:
                return "四";
            case 5:
                return "五";
            case 6:
                return "六";
            case 7:
                return "七";
            case 8:
                return "八";
            case 9:
                return "九";
            default:
                return "";
        }
    }
}
