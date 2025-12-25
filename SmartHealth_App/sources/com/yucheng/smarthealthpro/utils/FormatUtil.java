package com.yucheng.smarthealthpro.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/* loaded from: classes5.dex */
public class FormatUtil {
    public static String keep1(float value) {
        return new DecimalFormat("0.0").format(keep1F(value));
    }

    public static String keep2(float value) {
        return new DecimalFormat("0.00").format(keep2F(value));
    }

    public static String keep1NoZero(float value) {
        return keep1F(value) + "";
    }

    public static String keep2NoZero(float value) {
        return keep2F(value) + "";
    }

    public static String keep3NoZero(float value) {
        return keep3F(value) + "";
    }

    public static float keep1F(float value) {
        return (float) new BigDecimal(value + "").setScale(1, RoundingMode.HALF_UP).doubleValue();
    }

    public static float keep2F(float value) {
        return (float) new BigDecimal(value + "").setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public static float keep3F(float value) {
        return (float) new BigDecimal(value + "").setScale(3, RoundingMode.HALF_UP).doubleValue();
    }

    public static float keepNF(int num, float value) {
        return (float) new BigDecimal(value + "").setScale(num, RoundingMode.HALF_UP).doubleValue();
    }

    public static BigDecimal getBigDecimal(double d2) {
        return new BigDecimal(d2 + "");
    }

    public static BigDecimal getBigDecimal(float f2) {
        return new BigDecimal(f2 + "");
    }

    public static BigDecimal getBigDecimal(String s) {
        return new BigDecimal(s);
    }
}
