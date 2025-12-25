package com.yucheng.smarthealthpro.me.setting.contacts.utils;

import android.graphics.Color;
import android.graphics.Paint;

/* loaded from: classes5.dex */
public class ColorUtil {
    public static void setPaintColor(Paint mPaint, int position) {
        int i2 = position % 6;
        if (i2 == 0) {
            mPaint.setColor(Color.parseColor("#EC5745"));
            return;
        }
        if (i2 == 1) {
            mPaint.setColor(Color.parseColor("#377caf"));
            return;
        }
        if (i2 == 2) {
            mPaint.setColor(Color.parseColor("#4ebcd3"));
            return;
        }
        if (i2 == 3) {
            mPaint.setColor(Color.parseColor("#6fb30d"));
        } else if (i2 == 4) {
            mPaint.setColor(Color.parseColor("#FFA500"));
        } else {
            if (i2 != 5) {
                return;
            }
            mPaint.setColor(Color.parseColor("#bf9e5a"));
        }
    }
}
