package com.yucheng.smarthealthpro.utils;

import android.content.Context;
import com.yucheng.smarthealthpro.R;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes5.dex */
public class WeekUtil {
    public static String getLable(char[] ar, Context context) {
        String string;
        String string2;
        if (ar == null || ar.length < 7) {
            return "";
        }
        if (Integer.parseInt(ar[0] + "") != 1 || Integer.parseInt(ar[1] + "") != 1 || Integer.parseInt(ar[2] + "") != 1 || Integer.parseInt(ar[3] + "") != 1 || Integer.parseInt(ar[4] + "") != 1 || Integer.parseInt(ar[5] + "") != 1 || Integer.parseInt(ar[6] + "") != 1) {
            if (Integer.parseInt(ar[0] + "") != 1 || Integer.parseInt(ar[1] + "") != 1 || Integer.parseInt(ar[2] + "") != 1 || Integer.parseInt(ar[3] + "") != 1 || Integer.parseInt(ar[4] + "") != 1 || Integer.parseInt(ar[5] + "") != 1 || Integer.parseInt(ar[6] + "") != 0) {
                if (Integer.parseInt(ar[0] + "") != 1 || Integer.parseInt(ar[1] + "") != 1 || Integer.parseInt(ar[2] + "") != 1 || Integer.parseInt(ar[3] + "") != 1 || Integer.parseInt(ar[4] + "") != 1 || Integer.parseInt(ar[5] + "") != 0 || Integer.parseInt(ar[6] + "") != 0) {
                    if (Integer.parseInt(ar[0] + "") != 1) {
                        string = "";
                    } else {
                        string = context.getString(R.string.clock_repeat_content_mon);
                    }
                    if (Integer.parseInt(ar[1] + "") == 1) {
                        string = string + StringUtils.SPACE + context.getString(R.string.clock_repeat_content_tue);
                    }
                    if (Integer.parseInt(ar[2] + "") == 1) {
                        string = string + StringUtils.SPACE + context.getString(R.string.clock_repeat_content_wed);
                    }
                    if (Integer.parseInt(ar[3] + "") == 1) {
                        string = string + StringUtils.SPACE + context.getString(R.string.clock_repeat_content_thur);
                    }
                    if (Integer.parseInt(ar[4] + "") == 1) {
                        string = string + StringUtils.SPACE + context.getString(R.string.clock_repeat_content_fri);
                    }
                    if (Integer.parseInt(ar[5] + "") == 1) {
                        string = string + StringUtils.SPACE + context.getString(R.string.clock_repeat_content_sat);
                    }
                    string2 = Integer.parseInt(new StringBuilder().append(ar[6]).append("").toString()) == 1 ? string + StringUtils.SPACE + context.getString(R.string.clock_repeat_content_sun) : string;
                } else {
                    string2 = context.getString(R.string.clock_repeat_content_weekday);
                }
            } else {
                string2 = context.getString(R.string.clock_repeat_content_mon) + " - " + context.getString(R.string.clock_repeat_content_sat);
            }
        } else {
            string2 = context.getString(R.string.clock_repeat_content_every_day);
        }
        return "".equals(string2) ? context.getString(R.string.pulbic_never) : string2;
    }

    public static String getLable(int week, Context context) {
        String string;
        String string2;
        int i2 = week & 1;
        if (i2 == 1 && ((week >> 1) & 1) == 1 && ((week >> 2) & 1) == 1 && ((week >> 3) & 1) == 1 && ((week >> 4) & 1) == 1 && ((week >> 5) & 1) == 1 && ((week >> 6) & 1) == 1) {
            string2 = context.getString(R.string.clock_repeat_content_every_day);
        } else if (i2 == 1 && ((week >> 1) & 1) == 1 && ((week >> 2) & 1) == 1 && ((week >> 3) & 1) == 1 && ((week >> 4) & 1) == 1 && ((week >> 5) & 1) == 1 && ((week >> 6) & 1) == 0) {
            string2 = context.getString(R.string.clock_repeat_content_mon) + " - " + context.getString(R.string.clock_repeat_content_sat);
        } else if (i2 == 1 && ((week >> 1) & 1) == 1 && ((week >> 2) & 1) == 1 && ((week >> 3) & 1) == 1 && ((week >> 4) & 1) == 1 && ((week >> 5) & 1) == 0 && ((week >> 6) & 1) == 0) {
            string2 = context.getString(R.string.clock_repeat_content_weekday);
        } else {
            if (i2 != 1) {
                string = "";
            } else {
                string = context.getString(R.string.clock_repeat_content_mon);
            }
            if (((week >> 1) & 1) == 1) {
                string = string + StringUtils.SPACE + context.getString(R.string.clock_repeat_content_tue);
            }
            if (((week >> 2) & 1) == 1) {
                string = string + StringUtils.SPACE + context.getString(R.string.clock_repeat_content_wed);
            }
            if (((week >> 3) & 1) == 1) {
                string = string + StringUtils.SPACE + context.getString(R.string.clock_repeat_content_thur);
            }
            if (((week >> 4) & 1) == 1) {
                string = string + StringUtils.SPACE + context.getString(R.string.clock_repeat_content_fri);
            }
            if (((week >> 5) & 1) == 1) {
                string = string + StringUtils.SPACE + context.getString(R.string.clock_repeat_content_sat);
            }
            string2 = ((week >> 6) & 1) == 1 ? string + StringUtils.SPACE + context.getString(R.string.clock_repeat_content_sun) : string;
        }
        return "".equals(string2) ? context.getString(R.string.pulbic_never) : string2;
    }
}
