package com.yucheng.smarthealthpro.sport.weathers;

/* loaded from: classes5.dex */
public class WeatherCodeTools {
    public static int getWeatherCode(int code) {
        int i2 = (code == 900 || code == 100 || code == 150) ? 1 : (code == 901 || (code >= 101 && code <= 199) || (code >= 200 && code <= 204)) ? 2 : (code < 205 || code > 213) ? (code < 300 || code > 399) ? (code < 400 || code > 499) ? (code < 500 || code > 599) ? 0 : 6 : 5 : 4 : 3;
        if (i2 == 3 || i2 == 6) {
            return 2;
        }
        return i2;
    }
}
