package com.yucheng.smarthealthpro.utils;

/* loaded from: classes5.dex */
public class TransUtils {
    public static float BLOOD_FAT_MAX = 9.5f;
    public static float BLOOD_FAT_MAX_MG = 366.8f;
    public static float BLOOD_FAT_MIN = 1.0f;
    public static float BLOOD_FAT_MIN_MG = 38.6f;
    public static float BLOOD_FAT_TRANS = 38.61f;
    public static float BLOOD_FAT_TRANS2 = 0.0259f;
    public static float BLOOD_FAT_VISIBLE_MAX = 10.4f;
    public static float BLOOD_FAT_VISIBLE_MIN = 0.1f;
    public static int BLOOD_OXYGEN_VISIBLE_MAX = 100;
    public static int BLOOD_OXYGEN_VISIBLE_MIN = 70;
    public static int BLOOD_SUGAR_VISIBLE_MAX = 330;
    public static int BLOOD_SUGAR_VISIBLE_MAX_2 = 33;
    public static int BLOOD_SUGAR_VISIBLE_MIN = 20;
    public static int BLOOD_SUGAR_VISIBLE_MIN_2 = 2;
    public static int HEART_RATE_VISIBLE_MAX = 220;
    public static int HEART_RATE_VISIBLE_MIN = 40;
    public static int HRV_VISIBLE_MAX = 150;
    public static int HRV_VISIBLE_MIN = 1;
    public static float KETONE_TRANS = 18.0f;
    public static float KETONE_TRANS2 = 0.01667f;
    public static float KETONE_VISIBLE_MAX = 12.0f;
    public static float KETONE_VISIBLE_MIN = 0.1f;
    public static int PRESSURE_VISIBLE_MAX = 100;
    public static int PRESSURE_VISIBLE_MIN = 1;
    public static int RESPIRATORY_RATE_VISIBLE_MAX = 50;
    public static int RESPIRATORY_RATE_VISIBLE_MIN = 6;
    public static int RIDE_MAX_DISTANCE_CHANGE = 20;
    public static int STEP_VISIBLE_MAX = 9000;
    public static int TEMPERATURE_VISIBLE_MAX = 42;
    public static int TEMPERATURE_VISIBLE_MIN = 33;
    public static int URIC_ACID_MAX = 1000;
    public static float URIC_ACID_MAX_MG = 16.8f;
    public static int URIC_ACID_MIN = 90;
    public static float URIC_ACID_MIN_MG = 1.5f;
    public static float URIC_ACID_TRANS = 0.0168f;
    public static float URIC_ACID_TRANS2 = 60.0f;
    public static int URIC_ACID_VISIBLE_MAX = 1190;
    public static int URIC_ACID_VISIBLE_MIN = 50;
    public static int VOMAX_VISIBLE_MAX = 70;
    public static int VOMAX_VISIBLE_MIN = 10;

    public static int Bytes2Dec(byte[] bytes) {
        int i2 = 0;
        for (int i3 = 0; i3 < bytes.length; i3++) {
            i2 += (bytes[i3] & 255) << ((3 - i3) * 8);
        }
        return i2;
    }

    public static String bloodFatMmol2Mg(float value) {
        return FormatUtil.keep2(value * BLOOD_FAT_TRANS);
    }

    public static String uricAcidUmol2Mg(float value) {
        return FormatUtil.keep1(value * URIC_ACID_TRANS);
    }

    public static boolean isAvailableHr(int hrValue) {
        return hrValue >= HEART_RATE_VISIBLE_MIN && hrValue <= HEART_RATE_VISIBLE_MAX;
    }

    public static String ketoneUmol2Mg(float value) {
        return FormatUtil.keep1(value * KETONE_TRANS);
    }

    public static String ketoneMg2Umol(float value) {
        return FormatUtil.keep1(value / KETONE_TRANS2);
    }
}
