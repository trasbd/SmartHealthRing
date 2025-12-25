package com.yucheng.smarthealthpro.sport.weathers;

import com.amap.api.maps.model.LatLng;
import com.orhanobut.logger.Logger;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class GCJ2WGS {
    private static final double EARTH_RADIUS = 6378.137d;
    public static final double PI = 3.141592653589793d;

    private static double rad(double d2) {
        return (d2 * 3.141592653589793d) / 180.0d;
    }

    public static synchronized HashMap<String, Double> changedGps(double lat, double lon) {
        HashMap<String, Double> map;
        double d2 = lon - 105.0d;
        double d3 = lat - 35.0d;
        double dTransformLat = transformLat(d2, d3);
        double dTransformLon = transformLon(d2, d3);
        double d4 = (lat / 180.0d) * 3.141592653589793d;
        double dSin = Math.sin(d4);
        double d5 = 1.0d - ((0.006693421622965943d * dSin) * dSin);
        double dSqrt = Math.sqrt(d5);
        double dCos = (dTransformLon * 180.0d) / (((6378245.0d / dSqrt) * Math.cos(d4)) * 3.141592653589793d);
        map = new HashMap<>();
        map.put("lat", Double.valueOf(lat - ((dTransformLat * 180.0d) / ((6335552.717000426d / (d5 * dSqrt)) * 3.141592653589793d))));
        map.put("lon", Double.valueOf(lon - dCos));
        return map;
    }

    private static double transformLon(double x, double y) {
        double d2 = x * 0.1d;
        return x + 300.0d + (y * 2.0d) + (d2 * x) + (d2 * y) + (Math.sqrt(Math.abs(x)) * 0.1d) + ((((Math.sin((6.0d * x) * 3.141592653589793d) * 20.0d) + (Math.sin((x * 2.0d) * 3.141592653589793d) * 20.0d)) * 2.0d) / 3.0d) + ((((Math.sin(x * 3.141592653589793d) * 20.0d) + (Math.sin((x / 3.0d) * 3.141592653589793d) * 40.0d)) * 2.0d) / 3.0d) + ((((Math.sin((x / 12.0d) * 3.141592653589793d) * 150.0d) + (Math.sin((x / 30.0d) * 3.141592653589793d) * 300.0d)) * 2.0d) / 3.0d);
    }

    private static double transformLat(double x, double y) {
        double d2 = x * 2.0d;
        double dSqrt = (-100.0d) + d2 + (y * 3.0d) + (y * 0.2d * y) + (0.1d * x * y) + (Math.sqrt(Math.abs(x)) * 0.2d) + ((((Math.sin((6.0d * x) * 3.141592653589793d) * 20.0d) + (Math.sin(d2 * 3.141592653589793d) * 20.0d)) * 2.0d) / 3.0d);
        double d3 = y * 3.141592653589793d;
        return dSqrt + ((((Math.sin(d3) * 20.0d) + (Math.sin((y / 3.0d) * 3.141592653589793d) * 40.0d)) * 2.0d) / 3.0d) + ((((Math.sin((y / 12.0d) * 3.141592653589793d) * 160.0d) + (Math.sin(d3 / 30.0d) * 320.0d)) * 2.0d) / 3.0d);
    }

    public static double getDistance(double lat1, double lon1, double lat2, double lon2) {
        if (lat1 == lat2 && lon1 == lon2) {
            return 0.0d;
        }
        double d2 = (0.017453292519943295d * lon2) - (lon1 * 0.017453292519943295d);
        double dAtan = Math.atan(Math.tan(lat1 * 0.017453292519943295d) * 0.996647189328169d);
        double dAtan2 = Math.atan(0.996647189328169d * Math.tan(lat2 * 0.017453292519943295d));
        double dCos = Math.cos(dAtan);
        double dCos2 = Math.cos(dAtan2);
        double dSin = Math.sin(dAtan);
        double dSin2 = Math.sin(dAtan2);
        double d3 = dCos * dCos2;
        double d4 = dSin * dSin2;
        int i2 = 0;
        double d5 = 0.0d;
        double dAtan22 = 0.0d;
        double d6 = 0.0d;
        double d7 = d2;
        while (true) {
            if (i2 >= 20) {
                break;
            }
            double dCos3 = Math.cos(d7);
            double dSin3 = Math.sin(d7);
            double d8 = dCos2 * dSin3;
            double d9 = (dCos * dSin2) - ((dSin * dCos2) * dCos3);
            double d10 = dSin;
            double dSqrt = Math.sqrt((d8 * d8) + (d9 * d9));
            double d11 = (dCos3 * d3) + d4;
            dAtan22 = Math.atan2(dSqrt, d11);
            double d12 = dSqrt == 0.0d ? 0.0d : (dSin3 * d3) / dSqrt;
            double d13 = 1.0d - (d12 * d12);
            double d14 = d13 == 0.0d ? 0.0d : d11 - ((d4 * 2.0d) / d13);
            double d15 = 0.006739496756586903d * d13;
            double d16 = ((d15 / 16384.0d) * (((((320.0d - (175.0d * d15)) * d15) - 768.0d) * d15) + 4096.0d)) + 1.0d;
            double d17 = (d15 / 1024.0d) * ((d15 * (((74.0d - (47.0d * d15)) * d15) - 128.0d)) + 256.0d);
            double d18 = 2.0955066698943685E-4d * d13 * (((4.0d - (d13 * 3.0d)) * 0.0033528106718309896d) + 4.0d);
            double d19 = d14 * d14;
            d6 = d17 * dSqrt * (d14 + ((d17 / 4.0d) * ((((d19 * 2.0d) - 1.0d) * d11) - ((((d17 / 6.0d) * d14) * (((dSqrt * 4.0d) * dSqrt) - 3.0d)) * ((d19 * 4.0d) - 3.0d)))));
            double d20 = d2 + ((1.0d - d18) * 0.0033528106718309896d * d12 * (dAtan22 + (dSqrt * d18 * (d14 + (d18 * d11 * (((2.0d * d14) * d14) - 1.0d))))));
            if (Math.abs((d20 - d7) / d20) < 1.0E-12d) {
                d5 = d16;
                break;
            }
            i2++;
            dSin = d10;
            d7 = d20;
            d5 = d16;
        }
        return (float) (6356752.3142d * d5 * (dAtan22 - d6));
    }

    public static LatLng BD2GCJ(LatLng bd) {
        double d2 = bd.longitude - 0.0065d;
        double d3 = bd.latitude - 0.006d;
        double dSqrt = Math.sqrt((d2 * d2) + (d3 * d3)) - (Math.sin(d3 * 3.141592653589793d) * 2.0E-5d);
        double dAtan2 = Math.atan2(d3, d2) - (Math.cos(d2 * 3.141592653589793d) * 3.0E-6d);
        return new LatLng(dSqrt * Math.sin(dAtan2), Math.cos(dAtan2) * dSqrt);
    }

    public static LatLng GCJ2BD(LatLng bd) {
        double d2 = bd.longitude;
        double d3 = bd.latitude;
        double dSqrt = Math.sqrt((d2 * d2) + (d3 * d3)) + (Math.sin(d3 * 3.141592653589793d) * 2.0E-5d);
        double dAtan2 = Math.atan2(d3, d2) + (Math.cos(d2 * 3.141592653589793d) * 3.0E-6d);
        return new LatLng((dSqrt * Math.sin(dAtan2)) + 0.006d, (Math.cos(dAtan2) * dSqrt) + 0.0065d);
    }

    public static double getDistance2(double lat1, double lng1, double lat2, double lng2) {
        double dRad = rad(lat1);
        double dRad2 = rad(lat2);
        return Math.round(((Math.asin(Math.sqrt(Math.pow(Math.sin((dRad - dRad2) / 2.0d), 2.0d) + ((Math.cos(dRad) * Math.cos(dRad2)) * Math.pow(Math.sin((rad(lng1) - rad(lng2)) / 2.0d), 2.0d)))) * 2.0d) * EARTH_RADIUS) * 10000.0d) / 10000;
    }

    public static void main(String[] args) {
        try {
            Logger.d("太原－上海：" + getDistance(37.87d, 112.53d, 31.22d, 121.48d));
            Logger.d("宁波－上海：" + getDistance(29.86d, 121.56d, 31.22d, 121.48d));
            Logger.d("太原－上海2：" + getDistance2(37.87d, 112.53d, 31.22d, 121.48d));
            Logger.d("宁波－上海2：" + getDistance2(29.86d, 121.56d, 31.22d, 121.48d));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
