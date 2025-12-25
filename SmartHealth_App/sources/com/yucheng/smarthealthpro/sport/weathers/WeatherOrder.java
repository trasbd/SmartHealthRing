package com.yucheng.smarthealthpro.sport.weathers;

import com.yucheng.smarthealthpro.sport.weathers.WeatherBean;
import com.yucheng.smarthealthpro.utils.AppDateMgr;
import java.util.Comparator;

/* loaded from: classes5.dex */
public class WeatherOrder implements Comparator<WeatherBean.Data.Weather> {
    @Override // java.util.Comparator
    public int compare(WeatherBean.Data.Weather o1, WeatherBean.Data.Weather o2) {
        return AppDateMgr.parseToDate(o1.date, AppDateMgr.YYYYMMDD).compareTo(AppDateMgr.parseToDate(o2.date, AppDateMgr.YYYYMMDD));
    }
}
