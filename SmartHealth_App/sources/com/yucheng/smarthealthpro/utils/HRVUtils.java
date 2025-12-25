package com.yucheng.smarthealthpro.utils;

/* loaded from: classes5.dex */
public class HRVUtils {
    public static final int BODY_ABNORMAL = 21;
    public static final int BODY_MILD = 22;
    public static final int BODY_NORMAL = 23;
    public static final int HRV_ABNORMAL = 31;
    public static final int HRV_MILD = 32;
    public static final int HRV_NORMAL = 33;
    public static final int LOAD_RODE_STRONG = 4;
    public static final int LOAD_RODE_STRONGER = 5;
    public static final int LOAD_RODE_SUITABLE = 3;
    public static final int LOAD_RODE_WEAK = 2;
    public static final int LOAD_RODE_WEAKER = 1;
    public static final int PRESSURE_MILD = 12;
    public static final int PRESSURE_MODERATELY = 13;
    public static final int PRESSURE_RELAX = 11;
    public static final int PRESSURE_SEVERE = 14;
    public static final int RR_FAST = 53;
    public static final int RR_NORMAL = 52;
    public static final int RR_SLOW = 51;
    public static final int SP_MILD = 42;
    public static final int SP_MODERATELY = 43;
    public static final int SP_RELAX = 41;
    public static final int SP_SEVERE = 44;

    public static int getBody(float v) {
        if (v >= 0.0f && v < 4.0d) {
            return 21;
        }
        double d2 = v;
        return (d2 < 4.0d || d2 >= 6.0d) ? 23 : 22;
    }

    public static int getHRV(float v) {
        if (v >= 0.0f && v < 4.0d) {
            return 31;
        }
        double d2 = v;
        return (d2 < 4.0d || d2 >= 6.0d) ? 33 : 32;
    }

    public static int getLoadRode(float v) {
        if (v >= 0.0f && v < 2.0d) {
            return 1;
        }
        double d2 = v;
        if (d2 >= 2.0d && d2 < 4.0d) {
            return 2;
        }
        if (d2 < 4.0d || d2 >= 6.0d) {
            return (d2 < 6.0d || d2 >= 8.0d) ? 5 : 4;
        }
        return 3;
    }

    public static int getPressure(float v) {
        if (v >= 0.0f && v < 2.6d) {
            return 11;
        }
        double d2 = v;
        if (d2 < 2.6d || d2 >= 5.1d) {
            return (d2 < 5.1d || d2 >= 8.1d) ? 14 : 13;
        }
        return 12;
    }

    public static int getRespiratoryRate(float v) {
        if (v < 12.0f) {
            return 51;
        }
        return (v < 12.0f || v >= 24.0f) ? 53 : 52;
    }

    public static int getSympatheticParasympathetic(float v) {
        if (v <= 4.0f) {
            return 42;
        }
        return (((double) v) < 4.1d || v >= 8.0f) ? 44 : 43;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to find switch 'out' block (already processed)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.calcSwitchOut(SwitchRegionMaker.java:200)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.process(SwitchRegionMaker.java:61)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:112)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.processFallThroughCases(SwitchRegionMaker.java:105)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.process(SwitchRegionMaker.java:64)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:112)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.processFallThroughCases(SwitchRegionMaker.java:105)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.process(SwitchRegionMaker.java:64)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:112)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.processFallThroughCases(SwitchRegionMaker.java:105)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.process(SwitchRegionMaker.java:64)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:112)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:95)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:95)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:95)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:95)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:95)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeMthRegion(RegionMaker.java:48)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:25)
        */
    /* JADX WARN: Removed duplicated region for block: B:20:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x004b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String getHRVIndex(android.content.Context r1, int r2) {
        /*
            r0 = 1
            if (r2 == r0) goto L75
            r0 = 2
            if (r2 == r0) goto L6e
            r0 = 3
            if (r2 == r0) goto L67
            r0 = 4
            if (r2 == r0) goto L60
            r0 = 5
            if (r2 == r0) goto L59
            switch(r2) {
                case 11: goto L52;
                case 12: goto L4b;
                case 13: goto L44;
                case 14: goto L3d;
                default: goto L12;
            }
        L12:
            switch(r2) {
                case 21: goto L36;
                case 22: goto L4b;
                case 23: goto L2f;
                default: goto L15;
            }
        L15:
            switch(r2) {
                case 31: goto L36;
                case 32: goto L4b;
                case 33: goto L2f;
                default: goto L18;
            }
        L18:
            switch(r2) {
                case 42: goto L4b;
                case 43: goto L44;
                case 44: goto L3d;
                default: goto L1b;
            }
        L1b:
            switch(r2) {
                case 51: goto L28;
                case 52: goto L2f;
                case 53: goto L21;
                default: goto L1e;
            }
        L1e:
            java.lang.String r1 = ""
            goto L7b
        L21:
            int r2 = com.yucheng.smarthealthpro.R.string.index_fast
            java.lang.String r1 = r1.getString(r2)
            goto L7b
        L28:
            int r2 = com.yucheng.smarthealthpro.R.string.index_slow
            java.lang.String r1 = r1.getString(r2)
            goto L7b
        L2f:
            int r2 = com.yucheng.smarthealthpro.R.string.index_normal
            java.lang.String r1 = r1.getString(r2)
            goto L7b
        L36:
            int r2 = com.yucheng.smarthealthpro.R.string.index_abnormal
            java.lang.String r1 = r1.getString(r2)
            goto L7b
        L3d:
            int r2 = com.yucheng.smarthealthpro.R.string.severe
            java.lang.String r1 = r1.getString(r2)
            goto L7b
        L44:
            int r2 = com.yucheng.smarthealthpro.R.string.moderately
            java.lang.String r1 = r1.getString(r2)
            goto L7b
        L4b:
            int r2 = com.yucheng.smarthealthpro.R.string.index_mild
            java.lang.String r1 = r1.getString(r2)
            goto L7b
        L52:
            int r2 = com.yucheng.smarthealthpro.R.string.index_relax
            java.lang.String r1 = r1.getString(r2)
            goto L7b
        L59:
            int r2 = com.yucheng.smarthealthpro.R.string.stronger
            java.lang.String r1 = r1.getString(r2)
            goto L7b
        L60:
            int r2 = com.yucheng.smarthealthpro.R.string.index_strong
            java.lang.String r1 = r1.getString(r2)
            goto L7b
        L67:
            int r2 = com.yucheng.smarthealthpro.R.string.index_suitable
            java.lang.String r1 = r1.getString(r2)
            goto L7b
        L6e:
            int r2 = com.yucheng.smarthealthpro.R.string.index_weak
            java.lang.String r1 = r1.getString(r2)
            goto L7b
        L75:
            int r2 = com.yucheng.smarthealthpro.R.string.index_weaker
            java.lang.String r1 = r1.getString(r2)
        L7b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.utils.HRVUtils.getHRVIndex(android.content.Context, int):java.lang.String");
    }
}
