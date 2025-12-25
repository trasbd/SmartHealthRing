package com.yucheng.smarthealthpro.perfect.utils;

import android.content.Context;

/* loaded from: classes5.dex */
public class Machine {
    private static boolean sCheckTablet = false;
    private static boolean sIsTablet = false;
    public static boolean s_IS_SDK_ABOVE_KITKAT = true;

    private static boolean isPad() {
        if (DrawUtil.sDensity < 1.5d && DrawUtil.sDensity > 0.0f) {
            if (DrawUtil.sWidthPixels < DrawUtil.sHeightPixels) {
                if (DrawUtil.sWidthPixels > 480 && DrawUtil.sHeightPixels > 800) {
                    return true;
                }
            } else if (DrawUtil.sWidthPixels > 800 && DrawUtil.sHeightPixels > 480) {
                return true;
            }
        }
        return false;
    }

    public static boolean isTablet(Context context) {
        if (sCheckTablet) {
            return sIsTablet;
        }
        sCheckTablet = true;
        boolean zIsPad = isPad();
        sIsTablet = zIsPad;
        return zIsPad;
    }
}
