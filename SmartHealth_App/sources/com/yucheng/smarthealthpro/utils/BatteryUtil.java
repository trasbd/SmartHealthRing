package com.yucheng.smarthealthpro.utils;

import com.yucheng.smarthealthpro.R;

/* loaded from: classes5.dex */
public class BatteryUtil {
    public static int getBatteryId(int batteryPower) {
        int i2;
        int i3 = R.mipmap.icon_me_watch_power_green_six;
        try {
            if (batteryPower >= 0 && batteryPower <= 9) {
                i2 = R.mipmap.icon_me_watch_power_red;
            } else if (batteryPower >= 10 && batteryPower <= 19) {
                i2 = R.mipmap.icon_me_watch_power_green_one;
            } else if (batteryPower >= 20 && batteryPower <= 39) {
                i2 = R.mipmap.icon_me_watch_power_green_tow;
            } else if (batteryPower >= 40 && batteryPower <= 59) {
                i2 = R.mipmap.icon_me_watch_power_green_three;
            } else if (batteryPower >= 60 && batteryPower <= 79) {
                i2 = R.mipmap.icon_me_watch_power_green_four;
            } else if (batteryPower >= 80 && batteryPower <= 99) {
                i2 = R.mipmap.icon_me_watch_power_green_five;
            } else {
                if (batteryPower != 100) {
                    return i3;
                }
                i2 = R.mipmap.icon_me_watch_power_green_six;
            }
            i3 = i2;
            return i3;
        } catch (Exception e2) {
            e2.printStackTrace();
            return i3;
        }
    }
}
