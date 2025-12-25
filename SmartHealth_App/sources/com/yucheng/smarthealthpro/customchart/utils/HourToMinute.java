package com.yucheng.smarthealthpro.customchart.utils;

import com.amap.api.col.p0003sl.jt;
import com.yucheng.ycbtsdk.Constants;

/* loaded from: classes4.dex */
public class HourToMinute {
    public static int timeToMinute(String strTime) throws NumberFormatException {
        int i2 = 0;
        for (int i3 = 0; i3 < strTime.length(); i3++) {
            try {
                i2 = Integer.parseInt(strTime.charAt(3) + "" + strTime.charAt(4)) + (Integer.parseInt(strTime.charAt(0) + "" + strTime.charAt(1)) * 60);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return i2;
    }

    public static int timeToMinutes(String strTime) throws NumberFormatException {
        int i2 = 0;
        for (int i3 = 0; i3 < strTime.length(); i3++) {
            i2 = (Integer.parseInt(strTime.charAt(11) + "" + strTime.charAt(12)) * 60) + Integer.parseInt(strTime.charAt(14) + "" + strTime.charAt(15));
        }
        return i2;
    }

    public static String secToTime(int seconds) {
        return (seconds / 60) + "";
    }

    public static String secToTime2(int seconds) {
        int i2 = seconds / Constants.DATATYPE.FactoryTest;
        int i3 = (seconds - (i2 * Constants.DATATYPE.FactoryTest)) / 60;
        StringBuffer stringBuffer = new StringBuffer();
        if (i2 > 0 && i2 < 10) {
            stringBuffer.append("0" + i2 + jt.f1391g);
        } else if (i2 > 0 && i2 >= 10) {
            stringBuffer.append(i2 + jt.f1391g);
        }
        if (i3 > 0 && i3 < 10) {
            stringBuffer.append("0" + i3 + "");
        } else if (i3 > 0 && i3 >= 10) {
            stringBuffer.append(i3 + "");
        }
        return stringBuffer.toString();
    }

    public static String secToTime2(long seconds) {
        long j2 = seconds / 3600;
        long j3 = seconds - (3600 * j2);
        long j4 = j3 / 60;
        long j5 = j3 - (60 * j4);
        StringBuffer stringBuffer = new StringBuffer();
        if (j2 > 0) {
            stringBuffer.append(j2 + "小时 ");
        }
        if (j4 > 0) {
            stringBuffer.append(j4 + "分钟 ");
        }
        stringBuffer.append(j5 + "秒");
        return stringBuffer.toString();
    }

    public static String msecToTime(int time) {
        if (time <= 0) {
            return "00:00:00.000";
        }
        int i2 = time / 1000;
        int i3 = i2 / 60;
        int i4 = time % 1000;
        if (i2 < 60) {
            return "00:00:" + unitFormat(i2) + "." + unitFormat2(i4);
        }
        if (i3 < 60) {
            return "00:" + unitFormat(i3) + ":" + unitFormat(i2 % 60) + "." + unitFormat2(i4);
        }
        int i5 = i3 / 60;
        int i6 = i3 % 60;
        return unitFormat(i5) + ":" + unitFormat(i6) + ":" + unitFormat((i2 - (i5 * Constants.DATATYPE.FactoryTest)) - (i6 * 60)) + "." + unitFormat2(i4);
    }

    public static String unitFormat(int i2) {
        if (i2 >= 0 && i2 < 10) {
            return "0" + Integer.toString(i2);
        }
        return "" + i2;
    }

    public static String unitFormat2(int i2) {
        if (i2 >= 0 && i2 < 10) {
            return "00" + Integer.toString(i2);
        }
        if (i2 >= 10 && i2 < 100) {
            return "0" + Integer.toString(i2);
        }
        return "" + i2;
    }
}
