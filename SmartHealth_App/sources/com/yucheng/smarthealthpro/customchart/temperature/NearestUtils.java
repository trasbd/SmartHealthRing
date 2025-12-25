package com.yucheng.smarthealthpro.customchart.temperature;

/* loaded from: classes4.dex */
public class NearestUtils {
    public static int getNumberThree(int[] intarray, Integer number) {
        int iAbs = Math.abs(number.intValue() - intarray[0]);
        int i2 = intarray[0];
        for (int i3 : intarray) {
            int iAbs2 = Math.abs(number.intValue() - i3);
            if (iAbs2 <= iAbs) {
                i2 = i3;
                iAbs = iAbs2;
            }
        }
        return i2;
    }
}
