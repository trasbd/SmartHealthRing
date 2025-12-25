package com.yucheng.ycbtsdk.utils;

import java.util.Calendar;
import java.util.TimeZone;

/* loaded from: classes5.dex */
public class TimeUtil {
    public static byte[] makeBleTime() {
        Calendar calendar = Calendar.getInstance();
        int i2 = calendar.get(1);
        int i3 = calendar.get(2) + 1;
        int i4 = calendar.get(5);
        int i5 = calendar.get(7);
        return new byte[]{(byte) (i2 & 255), (byte) ((i2 >> 8) & 255), (byte) i3, (byte) i4, (byte) calendar.get(11), (byte) calendar.get(12), (byte) calendar.get(13), (byte) (i5 == 1 ? 6 : i5 - 2)};
    }

    public static byte[] makeBleTimeZone() {
        Calendar calendar = Calendar.getInstance();
        long offset = TimeZone.getDefault().getOffset(System.currentTimeMillis());
        long j2 = ((offset / 1000) / 60) / 60;
        YCBTLog.e("时区=" + j2 + ",utc时间=" + (calendar.getTimeInMillis() / 1000));
        return new byte[]{(byte) (r4 & 255), (byte) ((r4 >> 8) & 255), (byte) ((r4 >> 16) & 255), (byte) ((r4 >> 24) & 255), (byte) ((r4 >> 32) & 255), (byte) ((r4 >> 40) & 255), (byte) ((r4 >> 48) & 255), (byte) ((r4 >> 56) & 255), (byte) (j2 & 255)};
    }

    public static byte[] makeBleTime(long j2) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j2);
        int i2 = calendar.get(1);
        int i3 = calendar.get(2) + 1;
        int i4 = calendar.get(5);
        int i5 = calendar.get(7);
        return new byte[]{(byte) (i2 & 255), (byte) ((i2 >> 8) & 255), (byte) i3, (byte) i4, (byte) calendar.get(11), (byte) calendar.get(12), (byte) calendar.get(13), (byte) (i5 == 1 ? 6 : i5 - 2)};
    }
}
