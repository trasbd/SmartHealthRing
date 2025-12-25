package com.yucheng.ycbtsdk.utils;

import android.os.Build;

/* loaded from: classes5.dex */
public class InnerUtils {
    public static boolean isJieLiChipScheme(int i2) {
        return i2 == 3 || i2 == 4 || i2 == 5;
    }

    public static boolean isLooseSearch() {
        String str = Build.BRAND;
        return str != null && str.equals("Symphony");
    }
}
