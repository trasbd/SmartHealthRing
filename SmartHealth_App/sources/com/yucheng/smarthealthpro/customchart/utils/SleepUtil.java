package com.yucheng.smarthealthpro.customchart.utils;

import com.yucheng.smarthealthpro.database.room.bean.Sleep;
import com.yucheng.smarthealthpro.utils.TimeStampUtils;

/* loaded from: classes4.dex */
public class SleepUtil {
    public static int getState(long allTime, long allDeepTime) {
        double d2 = allTime / 3600.0d;
        if (d2 > 7.0d && allDeepTime / 3600.0d > 0.5d) {
            return 5;
        }
        if (d2 <= 7.0d || allDeepTime / 3600.0d >= 0.5d) {
            if (d2 <= 6.0d || allDeepTime / 3600.0d >= 0.5d) {
                if (d2 > 6.0d) {
                    double d3 = allDeepTime / 3600.0d;
                    if (d3 < 0.5d || d3 >= 2.0d) {
                    }
                }
                if (d2 > 6.0d && allDeepTime / 3600.0d >= 2.0d) {
                    return 5;
                }
                if (d2 <= 5.0d || allDeepTime / 3600.0d >= 0.5d) {
                    if (d2 <= 5.0d || allDeepTime / 3600.0d < 0.5d) {
                        if (d2 > 3.0d && allDeepTime / 3600.0d < 0.5d) {
                            return 2;
                        }
                        if (d2 <= 3.0d || allDeepTime / 3600.0d < 0.5d) {
                            if (d2 <= 2.0d || allDeepTime / 3600.0d >= 0.5d) {
                                if (d2 > 2.0d && allDeepTime / 3600.0d >= 0.5d) {
                                    return 2;
                                }
                                if (allTime == 0) {
                                    return -1;
                                }
                            }
                            return 1;
                        }
                    }
                }
            }
            return 3;
        }
        return 4;
    }

    public static boolean isTodaySleep(Sleep db, int past) throws NumberFormatException {
        int i2 = Integer.parseInt(TimeStampUtils.dateForStringToHourDate(TimeStampUtils.longStampForDate(db.getStartTime())));
        int i3 = Integer.parseInt(TimeStampUtils.dateForStringToHourDate(TimeStampUtils.longStampForDate(db.getEndTime())));
        if (i2 < 20 || past != 1) {
            return i2 <= 20 && past == 0 && i3 < 20;
        }
        return true;
    }
}
