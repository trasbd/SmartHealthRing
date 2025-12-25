package com.yucheng.smarthealthpro.sport.utils;

/* loaded from: classes5.dex */
public class GPSConverterUtils {
    public static final String BAIDU_LBS_TYPE = "bd09ll";

    /* renamed from: a, reason: collision with root package name */
    public static double f5707a = 6378245.0d;
    public static double ee = 0.006693421622965943d;
    public static double pi = 3.141592653589793d;

    public static boolean outOfChina(double lat, double lon) {
        return lon < 72.004d || lon > 137.8347d || lat < 0.8293d || lat > 55.8271d;
    }

    public static GPS gps84_To_Gcj02(double lat, double lon) {
        if (outOfChina(lat, lon)) {
            return null;
        }
        double d2 = lon - 105.0d;
        double d3 = lat - 35.0d;
        double dTransformLat = transformLat(d2, d3);
        double dTransformLon = transformLon(d2, d3);
        double d4 = (lat / 180.0d) * pi;
        double dSin = Math.sin(d4);
        double d5 = 1.0d - ((ee * dSin) * dSin);
        double dSqrt = Math.sqrt(d5);
        double d6 = f5707a;
        return new GPS(lat + ((dTransformLat * 180.0d) / ((((1.0d - ee) * d6) / (d5 * dSqrt)) * pi)), lon + ((dTransformLon * 180.0d) / (((d6 / dSqrt) * Math.cos(d4)) * pi)));
    }

    public static GPS gcj_To_Gps84(double lat, double lon) {
        GPS gpsTransform = transform(lat, lon);
        return new GPS((lat * 2.0d) - gpsTransform.getLat(), (lon * 2.0d) - gpsTransform.getLon());
    }

    public static GPS gcj02_To_Bd09(double gg_lat, double gg_lon) {
        double dSqrt = Math.sqrt((gg_lon * gg_lon) + (gg_lat * gg_lat)) + (Math.sin(pi * gg_lat) * 2.0E-5d);
        double dAtan2 = Math.atan2(gg_lat, gg_lon) + (Math.cos(gg_lon * pi) * 3.0E-6d);
        return new GPS((dSqrt * Math.sin(dAtan2)) + 0.006d, (Math.cos(dAtan2) * dSqrt) + 0.0065d);
    }

    public static GPS bd09_To_Gcj02(double bd_lat, double bd_lon) {
        double d2 = bd_lon - 0.0065d;
        double d3 = bd_lat - 0.006d;
        double dSqrt = Math.sqrt((d2 * d2) + (d3 * d3)) - (Math.sin(pi * d3) * 2.0E-5d);
        double dAtan2 = Math.atan2(d3, d2) - (Math.cos(d2 * pi) * 3.0E-6d);
        return new GPS(dSqrt * Math.sin(dAtan2), Math.cos(dAtan2) * dSqrt);
    }

    public static GPS bd09_To_Gps84(double bd_lat, double bd_lon) {
        GPS gpsBd09_To_Gcj02 = bd09_To_Gcj02(bd_lat, bd_lon);
        return gcj_To_Gps84(gpsBd09_To_Gcj02.getLat(), gpsBd09_To_Gcj02.getLon());
    }

    public static GPS transform(double lat, double lon) {
        if (outOfChina(lat, lon)) {
            return new GPS(lat, lon);
        }
        double d2 = lon - 105.0d;
        double d3 = lat - 35.0d;
        double dTransformLat = transformLat(d2, d3);
        double dTransformLon = transformLon(d2, d3);
        double d4 = (lat / 180.0d) * pi;
        double dSin = Math.sin(d4);
        double d5 = 1.0d - ((ee * dSin) * dSin);
        double dSqrt = Math.sqrt(d5);
        double d6 = f5707a;
        return new GPS(lat + ((dTransformLat * 180.0d) / ((((1.0d - ee) * d6) / (d5 * dSqrt)) * pi)), lon + ((dTransformLon * 180.0d) / (((d6 / dSqrt) * Math.cos(d4)) * pi)));
    }

    public static double transformLat(double x, double y) {
        double d2 = x * 2.0d;
        return (-100.0d) + d2 + (y * 3.0d) + (y * 0.2d * y) + (0.1d * x * y) + (Math.sqrt(Math.abs(x)) * 0.2d) + ((((Math.sin((x * 6.0d) * pi) * 20.0d) + (Math.sin(d2 * pi) * 20.0d)) * 2.0d) / 3.0d) + ((((Math.sin(pi * y) * 20.0d) + (Math.sin((y / 3.0d) * pi) * 40.0d)) * 2.0d) / 3.0d) + ((((Math.sin((y / 12.0d) * pi) * 160.0d) + (Math.sin((y * pi) / 30.0d) * 320.0d)) * 2.0d) / 3.0d);
    }

    public static double transformLon(double x, double y) {
        double d2 = x * 0.1d;
        return x + 300.0d + (y * 2.0d) + (d2 * x) + (d2 * y) + (Math.sqrt(Math.abs(x)) * 0.1d) + ((((Math.sin((6.0d * x) * pi) * 20.0d) + (Math.sin((x * 2.0d) * pi) * 20.0d)) * 2.0d) / 3.0d) + ((((Math.sin(pi * x) * 20.0d) + (Math.sin((x / 3.0d) * pi) * 40.0d)) * 2.0d) / 3.0d) + ((((Math.sin((x / 12.0d) * pi) * 150.0d) + (Math.sin((x / 30.0d) * pi) * 300.0d)) * 2.0d) / 3.0d);
    }
}
