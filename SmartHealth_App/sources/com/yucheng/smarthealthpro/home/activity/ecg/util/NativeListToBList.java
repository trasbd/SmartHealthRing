package com.yucheng.smarthealthpro.home.activity.ecg.util;

import com.yucheng.ycbtsdk.AITools;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class NativeListToBList {
    private static List<Integer> vpp_array = new ArrayList();

    public static List<Integer> nativeListToBList(List<Integer> nativeList) {
        ArrayList arrayList = new ArrayList();
        if (nativeList != null) {
            int size = nativeList.size();
            vpp_array.clear();
            AITools aITools = AITools.getInstance();
            int i2 = 0;
            aITools.initHeart(250, false);
            int i3 = 1;
            int i4 = 0;
            int i5 = 0;
            int i6 = 1000;
            int i7 = -1000;
            while (i2 < size) {
                int iMakeValue = aITools.makeValue(nativeList.get(i2).intValue()) / 40;
                if (i3 % 3 == 0) {
                    int i8 = (int) (((iMakeValue + i4) + i5) / 3.0f);
                    if (i8 > 1000) {
                        i8 = 1000;
                    }
                    iMakeValue = i8 < -1000 ? -1000 : i8;
                    if (i2 >= 750) {
                        arrayList.add(Integer.valueOf(iMakeValue));
                        if (iMakeValue > i7) {
                            i7 = iMakeValue;
                        } else if (iMakeValue < i6) {
                            i6 = iMakeValue;
                        }
                    }
                }
                i3++;
                if (i3 % 400 == 0) {
                    int i9 = i7 - i6;
                    if (i9 > 0) {
                        vpp_array.add(Integer.valueOf(i9));
                    }
                    i6 = 1000;
                    i7 = -1000;
                }
                i2++;
                i4 = i5;
                i5 = iMakeValue;
            }
        }
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x007d A[PHI: r3
  0x007d: PHI (r3v17 float) = (r3v6 float), (r3v7 float) binds: [B:14:0x007b, B:17:0x0084] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x009e A[PHI: r5
  0x009e: PHI (r5v5 int) = (r5v3 int), (r5v4 int) binds: [B:22:0x009c, B:25:0x00a2] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.List<java.lang.Integer> bListToAIList(java.util.List<java.lang.Integer> r8) {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            int r1 = r8.size()
            r2 = 0
            r3 = r2
        Lb:
            if (r3 >= r1) goto L2b
            java.lang.Object r4 = r8.get(r3)
            java.lang.Integer r4 = (java.lang.Integer) r4
            int r4 = r4.intValue()
            int r4 = r4 * 40
            double r4 = (double) r4
            r6 = 4574720472685930873(0x3f7cac083126e979, double:0.007)
            double r4 = r4 * r6
            int r4 = (int) r4
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r0.add(r4)
            int r3 = r3 + 1
            goto Lb
        L2b:
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            java.util.List<java.lang.Integer> r1 = com.yucheng.smarthealthpro.home.activity.ecg.util.NativeListToBList.vpp_array
            java.util.Collections.sort(r1)
            java.util.List<java.lang.Integer> r1 = com.yucheng.smarthealthpro.home.activity.ecg.util.NativeListToBList.vpp_array
            int r1 = r1.size()
            r3 = 4
            r4 = 1065353216(0x3f800000, float:1.0)
            if (r1 <= r3) goto L64
            r1 = 2
            r3 = r1
            r5 = r2
            r6 = r5
        L44:
            java.util.List<java.lang.Integer> r7 = com.yucheng.smarthealthpro.home.activity.ecg.util.NativeListToBList.vpp_array
            int r7 = r7.size()
            int r7 = r7 - r1
            if (r3 >= r7) goto L5f
            java.util.List<java.lang.Integer> r7 = com.yucheng.smarthealthpro.home.activity.ecg.util.NativeListToBList.vpp_array
            java.lang.Object r7 = r7.get(r3)
            java.lang.Integer r7 = (java.lang.Integer) r7
            int r7 = r7.intValue()
            int r5 = r5 + r7
            int r6 = r6 + 1
            int r3 = r3 + 1
            goto L44
        L5f:
            float r1 = (float) r5
            float r1 = r1 * r4
            float r3 = (float) r6
            float r1 = r1 / r3
            goto L65
        L64:
            r1 = 0
        L65:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r5 = "chong--------radio==="
            r3.<init>(r5)
            java.lang.StringBuilder r3 = r3.append(r1)
            java.lang.String r3 = r3.toString()
            com.orhanobut.logger.Logger.d(r3)
            r3 = 1123024896(0x42f00000, float:120.0)
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 >= 0) goto L80
        L7d:
            float r4 = r3 / r1
            goto L87
        L80:
            r3 = 1148846080(0x447a0000, float:1000.0)
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 <= 0) goto L87
            goto L7d
        L87:
            int r1 = r0.size()
        L8b:
            if (r2 >= r1) goto Laf
            java.lang.Object r3 = r0.get(r2)
            java.lang.Integer r3 = (java.lang.Integer) r3
            int r3 = r3.intValue()
            float r3 = (float) r3
            float r3 = r3 * r4
            int r3 = (int) r3
            r5 = 400(0x190, float:5.6E-43)
            if (r3 <= r5) goto La0
        L9e:
            r3 = r5
            goto La5
        La0:
            r5 = -400(0xfffffffffffffe70, float:NaN)
            if (r3 >= r5) goto La5
            goto L9e
        La5:
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r8.add(r3)
            int r2 = r2 + 1
            goto L8b
        Laf:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.home.activity.ecg.util.NativeListToBList.bListToAIList(java.util.List):java.util.List");
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x0023 A[PHI: r4
  0x0023: PHI (r4v4 int) = (r4v2 int), (r4v3 int) binds: [B:5:0x0021, B:8:0x0027] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.List<java.lang.Integer> oldListTobList(java.util.List<java.lang.Integer> r5) {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            int r1 = r5.size()
            r2 = 0
        La:
            if (r2 >= r1) goto L34
            java.lang.Object r3 = r5.get(r2)
            java.lang.Integer r3 = (java.lang.Integer) r3
            int r3 = r3.intValue()
            float r3 = (float) r3
            r4 = 1004888130(0x3be56042, float:0.007)
            float r3 = r3 / r4
            r4 = 1109393408(0x42200000, float:40.0)
            float r3 = r3 / r4
            int r3 = (int) r3
            r4 = 1000(0x3e8, float:1.401E-42)
            if (r3 <= r4) goto L25
        L23:
            r3 = r4
            goto L2a
        L25:
            r4 = -1000(0xfffffffffffffc18, float:NaN)
            if (r3 >= r4) goto L2a
            goto L23
        L2a:
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r0.add(r3)
            int r2 = r2 + 1
            goto La
        L34:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.home.activity.ecg.util.NativeListToBList.oldListTobList(java.util.List):java.util.List");
    }
}
