package com.yucheng.smarthealthpro.sport.utils;

/* loaded from: classes5.dex */
public class LocationDistanceUtils {
    private static double EARTH_RADIUS = 6378.137d;

    private static double rad(double d2) {
        return (d2 * 3.141592653589793d) / 180.0d;
    }

    public static double getDistance(double lat1, double lng1, double lat2, double lng2) {
        double dRad = rad(lat1);
        double dRad2 = rad(lat2);
        return (Math.round(((Math.asin(Math.sqrt(Math.pow(Math.sin((dRad - dRad2) / 2.0d), 2.0d) + ((Math.cos(dRad) * Math.cos(dRad2)) * Math.pow(Math.sin((rad(lng1) - rad(lng2)) / 2.0d), 2.0d)))) * 2.0d) * EARTH_RADIUS) * 10000.0d) / 10000.0d) * 1000.0d;
    }
}
