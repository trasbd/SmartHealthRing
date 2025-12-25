package com.yucheng.smarthealthpro.me.setting.contacts.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

/* loaded from: classes5.dex */
public class DpUtil {
    public static float dp2px(Context context, float dp) {
        return (dp * context.getResources().getDisplayMetrics().density) + 0.5f;
    }

    public static float sp2px(Context context, float sp) {
        return sp * context.getResources().getDisplayMetrics().scaledDensity;
    }

    public static int getScreenSizeWidth(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public static int getScreenSizeHeight(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }
}
