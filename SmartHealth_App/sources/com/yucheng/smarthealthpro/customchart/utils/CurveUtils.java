package com.yucheng.smarthealthpro.customchart.utils;

/* loaded from: classes4.dex */
public class CurveUtils {
    public static double exteriorSlope(double d1, double d2, double h1, double h2) {
        double d3 = ((((2.0d * h1) + h2) * d1) - (h1 * d2)) / (h1 + h2);
        double d4 = d1 < 0.0d ? -1.0d : d1 > 0.0d ? 1.0d : d1 == 0.0d ? 0.0d : d1;
        if ((d3 < 0.0d ? -1.0d : d3 > 0.0d ? 1.0d : d3 == 0.0d ? 0.0d : d3) != d4) {
            return 0.0d;
        }
        if (d2 < 0.0d) {
            d2 = -1.0d;
        } else if (d2 > 0.0d) {
            d2 = 1.0d;
        } else if (d2 == 0.0d) {
            d2 = 0.0d;
        }
        if (d4 == d2) {
            return d3;
        }
        double d5 = d1 * 3.0d;
        return Math.abs(d3) > Math.abs(d5) ? d5 : d3;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x006b A[PHI: r22
  0x006b: PHI (r22v4 double) = (r22v2 double), (r22v5 double) binds: [B:23:0x0089, B:15:0x0069] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0078 A[PHI: r22
  0x0078: PHI (r22v3 double) = (r22v2 double), (r22v5 double) binds: [B:26:0x008e, B:18:0x0076] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static double[] pchip(double[] r30, double[] r31, int r32, double[] r33, int r34) {
        /*
            Method dump skipped, instructions count: 288
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.customchart.utils.CurveUtils.pchip(double[], double[], int, double[], int):double[]");
    }

    public static double[] linearInterpolation(double x0, double y0, double x1, double y1, double[] new_x) {
        double d2 = (y1 - y0) / (x1 - x0);
        double[] dArr = new double[new_x.length];
        for (int i2 = 0; i2 < new_x.length; i2++) {
            dArr[i2] = ((new_x[i2] - x0) * d2) + y0;
        }
        return dArr;
    }
}
