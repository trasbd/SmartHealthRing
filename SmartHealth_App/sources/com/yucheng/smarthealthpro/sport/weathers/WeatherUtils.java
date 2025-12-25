package com.yucheng.smarthealthpro.sport.weathers;

import android.content.Context;
import android.content.res.Resources;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.os.ConfigurationCompat;
import androidx.fragment.app.FragmentActivity;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.services.district.DistrictSearchQuery;
import com.amap.api.services.weather.WeatherSearch;
import com.amap.api.services.weather.WeatherSearchQuery;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.Gson;
import com.jieli.jl_rcsp.model.device.settings.v0.DeviceState;
import com.orhanobut.logger.Logger;
import com.yanzhenjie.permission.Permission;
import com.yucheng.smarthealthpro.MyApplication;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.HealthApplication;
import com.yucheng.smarthealthpro.framework.http.HttpUtils;
import com.yucheng.smarthealthpro.framework.util.Constants;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.sport.weathers.WeatherBean;
import com.yucheng.smarthealthpro.utils.AppDateMgr;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.FormatUtil;
import com.yucheng.smarthealthpro.utils.PermissionUtil;
import com.yucheng.ycbtsdk.Constants;
import com.yucheng.ycbtsdk.YCBTClient;
import com.yucheng.ycbtsdk.response.BleDataResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import okhttp3.OkHttpClient;

/* loaded from: classes5.dex */
public class WeatherUtils {
    static final int TEMP = 1001;
    private static String city;
    private static OkHttpClient client;
    private static byte[] code;
    private static int count;
    private static String country;
    private static ImageView iv_weathers;
    private static AMapLocationClient mLocationClient;
    private static AMapLocationClientOption mLocationOption;
    private static String mTempUnit;
    private static OnWeatherSearchListenerImpl onWeatherSearchListener;
    private static TextView tv_city;
    private static TextView tv_temp_between;
    private static TextView tv_temp_now;
    private static AMapLocationListener mListener = new AMapLocationListener() { // from class: com.yucheng.smarthealthpro.sport.weathers.WeatherUtils.1
        @Override // com.amap.api.location.AMapLocationListener
        public void onLocationChanged(AMapLocation aMapLocation) throws UnsupportedEncodingException {
            HealthApplication healthApplication = HealthApplication.getInstance();
            if (aMapLocation != null && aMapLocation.getErrorCode() == 0) {
                SharedPreferencesUtils.put(healthApplication, Constant.Location.Latitude, aMapLocation.getLatitude() + "");
                SharedPreferencesUtils.put(healthApplication, Constant.Location.Longitude, aMapLocation.getLongitude() + "");
                Logger.d("chong-------定位成功:" + aMapLocation.getLatitude() + ": " + aMapLocation.getLongitude() + "--" + aMapLocation.getCountry() + "--" + aMapLocation.getCity());
                SharedPreferencesUtils.put(healthApplication, "country", aMapLocation.getCountry() + "");
                try {
                    if (aMapLocation.getCity().contains("香港") || aMapLocation.getCountry().contains("香港") || aMapLocation.getProvince().contains("香港")) {
                        WeatherUtils.city = "香港";
                    } else if (aMapLocation.getCity().contains("澳门") || aMapLocation.getCountry().contains("澳门") || aMapLocation.getProvince().contains("澳门") || aMapLocation.getCity().contains("澳門") || aMapLocation.getCountry().contains("澳門") || aMapLocation.getProvince().contains("澳門")) {
                        WeatherUtils.city = "澳门";
                    } else if (aMapLocation.getCity() != null && !"".equals(aMapLocation.getCity())) {
                        WeatherUtils.city = aMapLocation.getCity();
                    } else if (aMapLocation.getProvince() != null && !"".equals(aMapLocation.getProvince())) {
                        WeatherUtils.city = aMapLocation.getProvince();
                    } else if (aMapLocation.getCountry() != null && !"".equals(aMapLocation.getCountry())) {
                        WeatherUtils.city = aMapLocation.getCountry();
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                if (WeatherUtils.city != null && !WeatherUtils.city.isEmpty() && !WeatherUtils.city.contains("中华人民共和国")) {
                    HashMap<String, Double> mapChangedGps = GCJ2WGS.changedGps(aMapLocation.getLatitude(), aMapLocation.getLongitude());
                    String str = FormatUtil.keepNF(6, mapChangedGps.get("lon").floatValue()) + "";
                    String str2 = FormatUtil.keepNF(6, mapChangedGps.get("lat").floatValue()) + "";
                    WeatherUtils.coordinate = mapChangedGps.get("lon") + "," + mapChangedGps.get("lat");
                    WeatherUtils.fullName = aMapLocation.getAddress();
                    SharedPreferencesUtils.put(healthApplication, "fullName", WeatherUtils.fullName);
                    if (WeatherUtils.checkIsSendWeather(healthApplication)) {
                        Logger.d("chong-------获取天气:" + WeatherUtils.fullName);
                        WeatherUtils.getWeather(WeatherUtils.city);
                    } else {
                        WeatherUtils.sendToDeviceUpdate();
                    }
                    if (WeatherUtils.mLocationClient != null) {
                        WeatherUtils.mLocationClient.stopLocation();
                        WeatherUtils.mLocationClient.onDestroy();
                        WeatherUtils.mLocationClient = null;
                        WeatherUtils.count = 0;
                    }
                } else {
                    WeatherUtils.count++;
                }
                YCBTClient.appSendLocationNumber(1, (String) SharedPreferencesUtils.get(healthApplication, "fullName", ""), null);
            } else {
                String str3 = "定位失败,";
                if (aMapLocation != null) {
                    str3 = "定位失败," + aMapLocation.getErrorCode() + ": " + aMapLocation.getErrorInfo();
                }
                Logger.d("chong-------定位失败:" + str3);
                WeatherUtils.count++;
                SharedPreferencesUtils.put(healthApplication, Constant.SpConstKey.TMP_CACHE_TIME, 0L);
            }
            if (WeatherUtils.count < 3 || WeatherUtils.mLocationClient == null) {
                return;
            }
            WeatherUtils.mLocationClient.stopLocation();
            WeatherUtils.mLocationClient.onDestroy();
            WeatherUtils.mLocationClient = null;
            WeatherUtils.count = 0;
            WeatherUtils.gooleLocation(healthApplication);
        }
    };
    private static String coordinate = "";
    private static String fullName = "";
    private static boolean isGetTodayWeather = false;
    private static boolean isGetTomrrowWeather = false;

    public static boolean checkIsSendWeather(Context c2) {
        String str = (String) SharedPreferencesUtils.get(c2, DistrictSearchQuery.KEYWORDS_CITY, "");
        String str2 = (String) SharedPreferencesUtils.get(c2, Constant.SpConstKey.TMP, "");
        if (TextUtils.isEmpty(city)) {
            return false;
        }
        return !str.equals(city) || ((((System.currentTimeMillis() / 1000) - ((Long) SharedPreferencesUtils.get(c2, Constant.SpConstKey.TMP_CACHE_TIME, 0L)).longValue()) > 3600L ? 1 : (((System.currentTimeMillis() / 1000) - ((Long) SharedPreferencesUtils.get(c2, Constant.SpConstKey.TMP_CACHE_TIME, 0L)).longValue()) == 3600L ? 0 : -1)) > 0) || TextUtils.isEmpty(str2);
    }

    public static void init() {
        count = 0;
        if (mLocationClient == null) {
            try {
                mLocationClient = new AMapLocationClient(MyApplication.getInstance());
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (mLocationClient == null) {
            return;
        }
        if (mLocationOption == null) {
            AMapLocationClientOption aMapLocationClientOption = new AMapLocationClientOption();
            mLocationOption = aMapLocationClientOption;
            aMapLocationClientOption.setNeedAddress(true);
        }
        mLocationClient.setLocationListener(mListener);
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setOnceLocationLatest(false);
        mLocationClient.setLocationOption(mLocationOption);
        mLocationClient.stopLocation();
        mLocationClient.startLocation();
    }

    public static void weatherFunction(Context context) {
        context.getApplicationContext();
        init();
    }

    public static void weatherFunction(Context context, TextView tv_temp, TextView tv_run_address, TextView tv_temp_all, ImageView iv_weather) {
        if (tv_temp == null || tv_run_address == null || tv_temp_all == null) {
            return;
        }
        tv_temp_now = tv_temp;
        tv_city = tv_run_address;
        tv_temp_between = tv_temp_all;
        iv_weathers = iv_weather;
        weatherFunction(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void getWeather(final String city2) {
        String bindDeviceMac;
        final HealthApplication healthApplication = HealthApplication.getInstance();
        if ("".equals(YCBTClient.getBindDeviceMac())) {
            bindDeviceMac = DeviceState.INVALID_ADDRESS;
        } else {
            bindDeviceMac = YCBTClient.getBindDeviceMac();
        }
        SharedPreferencesUtils.put(healthApplication, DistrictSearchQuery.KEYWORDS_CITY, city2);
        country = (String) SharedPreferencesUtils.get(healthApplication, "country", "");
        HashMap map = new HashMap();
        map.put(FirebaseAnalytics.Param.LOCATION, city2);
        map.put("phone", (String) SharedPreferencesUtils.get(healthApplication, Constant.SpConstKey.USER_NAME, "0755-10010"));
        map.put("coordinate", coordinate);
        map.put("mac", bindDeviceMac);
        map.put("fullName", fullName);
        map.put("country", country);
        map.put("type", "Android");
        map.put(Constant.SpConstKey.TOKEN, (String) SharedPreferencesUtils.get(healthApplication, Constant.SpConstKey.TOKEN, ""));
        map.put("accKey", "");
        map.put("client", "");
        map.put("ip", "");
        HttpUtils.getInstance().postJsonMsgAsynHttp(healthApplication, Constants.GETWEATHERURL, new Gson().toJson(map), new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.sport.weathers.WeatherUtils.2
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                Logger.e("weather_result==" + result, new Object[0]);
                if (result != null) {
                    Message message = new Message();
                    message.what = 1001;
                    message.obj = result;
                    WeatherUtils.getMainHanlder().sendMessage(message);
                    return;
                }
                SharedPreferencesUtils.put(healthApplication, Constant.SpConstKey.TMP_CACHE_TIME, 0L);
                WeatherUtils.searchPoiGetWeather(healthApplication, city2);
            }
        });
    }

    public static String getStringDate() {
        return new SimpleDateFormat(AppDateMgr.DF_YYYY_MM_DD, Locale.ENGLISH).format(new Date());
    }

    public static String getTomorrowStringDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(5, 1);
        return new SimpleDateFormat(AppDateMgr.DF_YYYY_MM_DD, Locale.ENGLISH).format(calendar.getTime());
    }

    public static Handler getMainHanlder() {
        return new Handler(Looper.getMainLooper()) { // from class: com.yucheng.smarthealthpro.sport.weathers.WeatherUtils.3
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                HealthApplication healthApplication = HealthApplication.getInstance();
                if (msg.what == 1001 && msg.obj != null) {
                    String str = (String) msg.obj;
                    Logger.d("chong-----msg==" + str);
                    try {
                        WeatherBean weatherBean = (WeatherBean) new Gson().fromJson(str, WeatherBean.class);
                        if (weatherBean != null && weatherBean.code == 0) {
                            WeatherBean.Data data = weatherBean.data;
                            if (data != null && !"".equals(data)) {
                                WeatherUtils.parsonWeatherBean(data);
                                WeatherUtils.sendToDeviceUpdate();
                                if (WeatherUtils.mLocationClient != null) {
                                    WeatherUtils.mLocationClient.stopLocation();
                                    WeatherUtils.mLocationClient.onDestroy();
                                    WeatherUtils.mLocationClient = null;
                                }
                            }
                        } else {
                            WeatherUtils.searchPoiGetWeather(healthApplication, WeatherUtils.city);
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        WeatherUtils.searchPoiGetWeather(healthApplication, WeatherUtils.city);
                    }
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void parsonWeatherBean(WeatherBean.Data data) {
        String str;
        HealthApplication healthApplication = HealthApplication.getInstance();
        int i2 = data.condCode;
        String str2 = data.tmp;
        String str3 = null;
        if (data.weather != null) {
            List listAsList = Arrays.asList(data.weather);
            Collections.sort(listAsList, new WeatherOrder());
            if (listAsList.size() > 0) {
                WeatherBean.Data.Weather weather = (WeatherBean.Data.Weather) listAsList.get(0);
                str3 = weather.tmpMax;
                str = weather.tmpMin;
            } else {
                str = null;
            }
            if (listAsList.size() > 1) {
                WeatherBean.Data.Weather weather2 = (WeatherBean.Data.Weather) listAsList.get(1);
                SharedPreferencesUtils.put(healthApplication, Constant.SpConstKey.TOMORROW_TMP_MAX, weather2.tmpMax);
                SharedPreferencesUtils.put(healthApplication, Constant.SpConstKey.TOMORROW_TMP_MIN, weather2.tmpMin);
                SharedPreferencesUtils.put(healthApplication, Constant.SpConstKey.TOMORROW_COND_CODE, Integer.valueOf(weather2.condCodeD));
            }
        } else {
            str = null;
        }
        SharedPreferencesUtils.put(healthApplication, Constant.SpConstKey.TMP_MAX, str3);
        SharedPreferencesUtils.put(healthApplication, Constant.SpConstKey.TMP_MIN, str);
        SharedPreferencesUtils.put(healthApplication, Constant.SpConstKey.COND_CODE, Integer.valueOf(i2));
        SharedPreferencesUtils.put(healthApplication, Constant.SpConstKey.TMP, str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void sendToDeviceUpdate() throws UnsupportedEncodingException {
        final HealthApplication healthApplication = HealthApplication.getInstance();
        String str = (String) SharedPreferencesUtils.get(healthApplication, Constant.SpConstKey.TMP, "");
        String str2 = (String) SharedPreferencesUtils.get(healthApplication, Constant.SpConstKey.TMP_MAX, "");
        String str3 = (String) SharedPreferencesUtils.get(healthApplication, Constant.SpConstKey.TMP_MIN, "");
        int weatherCode = WeatherCodeTools.getWeatherCode(((Integer) SharedPreferencesUtils.get(healthApplication, Constant.SpConstKey.COND_CODE, 0)).intValue());
        if (YCBTClient.connectState() == 10) {
            YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASTODAYWEATHER);
            YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASTOMORROWWEATHER);
            if (!"".equals(str) && !"".equals(str2) && !"".equals(str3)) {
                YCBTClient.appTodayWeather(str3, str2, str, weatherCode, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.sport.weathers.WeatherUtils.4
                    @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                    public void onDataResponse(int i2, float v, HashMap hashMap) {
                        SharedPreferencesUtils.put(healthApplication, Constant.SpConstKey.TMP_CACHE_TIME, Long.valueOf(System.currentTimeMillis() / 1000));
                    }
                });
            }
            if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASTOMORROWWEATHER)) {
                String str4 = (String) SharedPreferencesUtils.get(healthApplication, Constant.SpConstKey.TOMORROW_TMP_MAX, "");
                String str5 = (String) SharedPreferencesUtils.get(healthApplication, Constant.SpConstKey.TOMORROW_TMP_MIN, "");
                int weatherCode2 = WeatherCodeTools.getWeatherCode(((Integer) SharedPreferencesUtils.get(healthApplication, Constant.SpConstKey.TOMORROW_COND_CODE, 0)).intValue());
                if (!"".equals(str4) && !"".equals(str5)) {
                    YCBTClient.appTomorrowWeather(str5, str4, "20", weatherCode2, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.sport.weathers.WeatherUtils.5
                        @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                        public void onDataResponse(int i2, float v, HashMap hashMap) {
                        }
                    });
                }
            }
            YCBTClient.appSendLocationNumber(1, (String) SharedPreferencesUtils.get(healthApplication, "fullName", ""), null);
        }
        updateUi(weatherCode, str, str2, str3);
    }

    private static void updateUi(int cond_code, String temp, String temp_max, String temp_min) {
        HealthApplication healthApplication = HealthApplication.getInstance();
        String str = (String) SharedPreferencesUtils.get(healthApplication, Constant.SpConstKey.TEMP_UNIT, "");
        mTempUnit = str;
        if (str != null && str.equals(Constant.SpConstValue.TEMP_INCH)) {
            TextView textView = tv_temp_now;
            if (textView != null) {
                textView.setText(((int) ((Integer.parseInt(temp) * 1.8f) + 32.0f)) + "°F");
            }
            TextView textView2 = tv_temp_between;
            if (textView2 != null) {
                textView2.setText(((int) ((Integer.parseInt(temp_min) * 1.8f) + 32.0f)) + "°F ~ " + ((int) ((Integer.parseInt(temp_max) * 1.8f) + 32.0f)) + "°F");
            }
        } else {
            TextView textView3 = tv_temp_now;
            if (textView3 != null) {
                textView3.setText(temp + Constant.SpConstValue.TEMP_ISO);
            }
            TextView textView4 = tv_temp_between;
            if (textView4 != null) {
                textView4.setText(temp_min + "℃ ~ " + temp_max + Constant.SpConstValue.TEMP_ISO);
            }
        }
        TextView textView5 = tv_city;
        if (textView5 != null) {
            textView5.setText(city);
        }
        ImageView imageView = iv_weathers;
        if (imageView == null) {
        }
        switch (cond_code) {
            case 0:
                imageView.setBackground(ResourcesCompat.getDrawable(healthApplication.getResources(), R.mipmap.icon_sp_weather_unknow, null));
                break;
            case 1:
                imageView.setBackground(ResourcesCompat.getDrawable(healthApplication.getResources(), R.mipmap.icon_sp_weather_sun, null));
                break;
            case 2:
            case 3:
            case 6:
                imageView.setBackground(ResourcesCompat.getDrawable(healthApplication.getResources(), R.mipmap.icon_sp_weather_cloud, null));
                break;
            case 4:
                imageView.setBackground(ResourcesCompat.getDrawable(healthApplication.getResources(), R.mipmap.icon_sp_weather_rain, null));
                break;
            case 5:
                imageView.setBackground(ResourcesCompat.getDrawable(healthApplication.getResources(), R.mipmap.icon_sp_weather_snow, null));
                break;
        }
    }

    public static void start() {
        AMapLocationClient aMapLocationClient = mLocationClient;
        if (aMapLocationClient == null || aMapLocationClient.isStarted()) {
            return;
        }
        mLocationClient.startLocation();
    }

    public static void stop() {
        AMapLocationClient aMapLocationClient = mLocationClient;
        if (aMapLocationClient == null || !aMapLocationClient.isStarted()) {
            return;
        }
        mLocationClient.stopLocation();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void searchPoiGetWeather(Context context, String city2) {
        if (context == null) {
            context = HealthApplication.getInstance();
        }
        isGetTodayWeather = false;
        isGetTomrrowWeather = false;
        if (onWeatherSearchListener == null) {
            onWeatherSearchListener = new OnWeatherSearchListenerImpl();
        }
        WeatherSearchQuery weatherSearchQuery = new WeatherSearchQuery(city2, 1);
        try {
            WeatherSearch weatherSearch = new WeatherSearch(context);
            weatherSearch.setOnWeatherSearchListener(onWeatherSearchListener);
            weatherSearch.setQuery(weatherSearchQuery);
            weatherSearch.searchWeatherAsyn();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        try {
            WeatherSearchQuery weatherSearchQuery2 = new WeatherSearchQuery(city2, 2);
            WeatherSearch weatherSearch2 = new WeatherSearch(context);
            weatherSearch2.setOnWeatherSearchListener(onWeatherSearchListener);
            weatherSearch2.setQuery(weatherSearchQuery2);
            weatherSearch2.searchWeatherAsyn();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    private static class OnWeatherSearchListenerImpl implements WeatherSearch.OnWeatherSearchListener {
        private OnWeatherSearchListenerImpl() {
        }

        /* JADX WARN: Removed duplicated region for block: B:23:0x004f  */
        @Override // com.amap.api.services.weather.WeatherSearch.OnWeatherSearchListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void onWeatherLiveSearched(com.amap.api.services.weather.LocalWeatherLiveResult r4, int r5) throws java.io.UnsupportedEncodingException {
            /*
                r3 = this;
                r0 = 1000(0x3e8, float:1.401E-42)
                if (r5 != r0) goto L83
                if (r4 == 0) goto L83
                com.amap.api.services.weather.LocalWeatherLive r5 = r4.getLiveResult()
                if (r5 == 0) goto L83
                com.amap.api.services.weather.LocalWeatherLive r4 = r4.getLiveResult()
                java.lang.String r5 = r4.getWeather()
                if (r5 == 0) goto L4f
                java.lang.String r0 = "晴"
                boolean r0 = r5.contains(r0)
                if (r0 == 0) goto L22
                r5 = 100
                goto L50
            L22:
                java.lang.String r0 = "雨"
                boolean r0 = r5.contains(r0)
                if (r0 == 0) goto L2e
                r5 = 300(0x12c, float:4.2E-43)
                goto L50
            L2e:
                java.lang.String r0 = "雪"
                boolean r0 = r5.contains(r0)
                if (r0 == 0) goto L3a
                r5 = 400(0x190, float:5.6E-43)
                goto L50
            L3a:
                java.lang.String r0 = "云"
                boolean r0 = r5.contains(r0)
                if (r0 != 0) goto L4c
                java.lang.String r0 = "阴"
                boolean r5 = r5.contains(r0)
                if (r5 == 0) goto L4f
            L4c:
                r5 = 200(0xc8, float:2.8E-43)
                goto L50
            L4f:
                r5 = 0
            L50:
                com.yucheng.smarthealthpro.framework.HealthApplication r0 = com.yucheng.smarthealthpro.framework.HealthApplication.getInstance()
                java.lang.String r4 = r4.getTemperature()
                java.lang.String r1 = "tmp"
                com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils.put(r0, r1, r4)
                java.lang.String r4 = "cond_code"
                java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
                com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils.put(r0, r4, r5)
                r4 = 1
                com.yucheng.smarthealthpro.sport.weathers.WeatherUtils.m2541$$Nest$sfputisGetTodayWeather(r4)
                boolean r4 = com.yucheng.smarthealthpro.sport.weathers.WeatherUtils.m2535$$Nest$sfgetisGetTomrrowWeather()
                if (r4 == 0) goto L83
                long r4 = java.lang.System.currentTimeMillis()
                r1 = 1000(0x3e8, double:4.94E-321)
                long r4 = r4 / r1
                java.lang.Long r4 = java.lang.Long.valueOf(r4)
                java.lang.String r5 = "temp_cache_time"
                com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils.put(r0, r5, r4)
                com.yucheng.smarthealthpro.sport.weathers.WeatherUtils.m2549$$Nest$smsendToDeviceUpdate()
            L83:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.sport.weathers.WeatherUtils.OnWeatherSearchListenerImpl.onWeatherLiveSearched(com.amap.api.services.weather.LocalWeatherLiveResult, int):void");
        }

        /* JADX WARN: Removed duplicated region for block: B:26:0x008a  */
        @Override // com.amap.api.services.weather.WeatherSearch.OnWeatherSearchListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void onWeatherForecastSearched(com.amap.api.services.weather.LocalWeatherForecastResult r5, int r6) throws java.io.UnsupportedEncodingException {
            /*
                r4 = this;
                com.yucheng.smarthealthpro.framework.HealthApplication r6 = com.yucheng.smarthealthpro.framework.HealthApplication.getInstance()
                com.amap.api.services.weather.LocalWeatherForecast r5 = r5.getForecastResult()
                java.util.List r5 = r5.getWeatherForecast()
                java.util.Iterator r5 = r5.iterator()
            L10:
                boolean r0 = r5.hasNext()
                if (r0 == 0) goto La8
                java.lang.Object r0 = r5.next()
                com.amap.api.services.weather.LocalDayWeatherForecast r0 = (com.amap.api.services.weather.LocalDayWeatherForecast) r0
                java.lang.String r1 = com.yucheng.smarthealthpro.sport.weathers.WeatherUtils.getStringDate()
                java.lang.String r2 = r0.getDate()
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L3d
                java.lang.String r1 = r0.getDayTemp()
                java.lang.String r0 = r0.getNightTemp()
                java.lang.String r2 = "tmp_max"
                com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils.put(r6, r2, r1)
                java.lang.String r1 = "tmp_min"
                com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils.put(r6, r1, r0)
                goto L10
            L3d:
                java.lang.String r1 = com.yucheng.smarthealthpro.sport.weathers.WeatherUtils.getTomorrowStringDate()
                java.lang.String r2 = r0.getDate()
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L10
                java.lang.String r1 = r0.getDayWeather()
                if (r1 == 0) goto L8a
                java.lang.String r2 = "晴"
                boolean r2 = r1.contains(r2)
                if (r2 == 0) goto L5d
                r1 = 100
                goto L8b
            L5d:
                java.lang.String r2 = "雨"
                boolean r2 = r1.contains(r2)
                if (r2 == 0) goto L69
                r1 = 300(0x12c, float:4.2E-43)
                goto L8b
            L69:
                java.lang.String r2 = "雪"
                boolean r2 = r1.contains(r2)
                if (r2 == 0) goto L75
                r1 = 400(0x190, float:5.6E-43)
                goto L8b
            L75:
                java.lang.String r2 = "云"
                boolean r2 = r1.contains(r2)
                if (r2 != 0) goto L87
                java.lang.String r2 = "阴"
                boolean r1 = r1.contains(r2)
                if (r1 == 0) goto L8a
            L87:
                r1 = 200(0xc8, float:2.8E-43)
                goto L8b
            L8a:
                r1 = 0
            L8b:
                java.lang.String r2 = "tomorrow_cond_code"
                java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
                com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils.put(r6, r2, r1)
                java.lang.String r1 = "tomorrow_tmp_min"
                java.lang.String r2 = r0.getNightTemp()
                com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils.put(r6, r1, r2)
                java.lang.String r1 = "tomorrow_tmp_max"
                java.lang.String r0 = r0.getDayTemp()
                com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils.put(r6, r1, r0)
                goto L10
            La8:
                r5 = 1
                com.yucheng.smarthealthpro.sport.weathers.WeatherUtils.m2542$$Nest$sfputisGetTomrrowWeather(r5)
                boolean r5 = com.yucheng.smarthealthpro.sport.weathers.WeatherUtils.m2534$$Nest$sfgetisGetTodayWeather()
                if (r5 == 0) goto Lc5
                long r0 = java.lang.System.currentTimeMillis()
                r2 = 1000(0x3e8, double:4.94E-321)
                long r0 = r0 / r2
                java.lang.Long r5 = java.lang.Long.valueOf(r0)
                java.lang.String r0 = "temp_cache_time"
                com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils.put(r6, r0, r5)
                com.yucheng.smarthealthpro.sport.weathers.WeatherUtils.m2549$$Nest$smsendToDeviceUpdate()
            Lc5:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.sport.weathers.WeatherUtils.OnWeatherSearchListenerImpl.onWeatherForecastSearched(com.amap.api.services.weather.LocalWeatherForecastResult, int):void");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void gooleLocation(final Context context) {
        Logger.d("chong-------google定位:" + (context instanceof FragmentActivity));
        FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);
        if (ContextCompat.checkSelfPermission(context, Permission.ACCESS_COARSE_LOCATION) != 0) {
            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() { // from class: com.yucheng.smarthealthpro.sport.weathers.WeatherUtils.6
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public void onSuccess(Location location) throws IOException {
                if (location != null) {
                    Logger.d("chong-------google定位成功:" + location.getLatitude() + ": " + location.getLongitude());
                    WeatherUtils.getCity(context, location);
                } else {
                    WeatherUtils.count++;
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void getCity(Context context, final Location location) throws IOException {
        Address geocoderAddress = getGeocoderAddress(context, location.getLatitude(), location.getLongitude());
        if (geocoderAddress == null || (TextUtils.isEmpty(geocoderAddress.getLocality()) && TextUtils.isEmpty(geocoderAddress.getSubAdminArea()) && TextUtils.isEmpty(geocoderAddress.getAdminArea()))) {
            String str = "https://maps.google.com/maps/api/geocode/json?latlng=" + location.getLatitude() + "," + location.getLongitude() + "&sensor=true&language=" + getLocale().toString();
            Logger.d("chong----------language==" + getLocale().toString());
            HttpUtils.getInstance().getMsgAsynHttp(context, str, null, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.sport.weathers.WeatherUtils.7
                @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
                public void onSuccess(String result) {
                    Logger.d("chong--------city_result==" + result);
                    if (WeatherUtils.city != null && !WeatherUtils.city.isEmpty() && result != null) {
                        HashMap<String, Double> mapChangedGps = GCJ2WGS.changedGps(location.getLatitude(), location.getLongitude());
                        WeatherUtils.coordinate = mapChangedGps.get("lon") + "," + mapChangedGps.get("lat");
                        WeatherUtils.fullName = result;
                        SharedPreferencesUtils.put(HealthApplication.getInstance(), "fullName", WeatherUtils.fullName);
                        WeatherUtils.getWeather(WeatherUtils.city);
                    }
                    WeatherUtils.count++;
                }
            });
            return;
        }
        String locality = geocoderAddress.getLocality();
        String subAdminArea = geocoderAddress.getSubAdminArea();
        String adminArea = geocoderAddress.getAdminArea();
        if (TextUtils.isEmpty(locality)) {
            locality = TextUtils.isEmpty(subAdminArea) ? TextUtils.isEmpty(adminArea) ? "" : adminArea : subAdminArea;
        }
        city = locality;
        coordinate = location.getLongitude() + "," + location.getLatitude();
        fullName = geocoderAddress.getAddressLine(0);
        SharedPreferencesUtils.put(HealthApplication.getInstance(), "fullName", fullName);
        getWeather(city);
    }

    public static Address getGeocoderAddress(Context context, double latitude, double longitude) throws IOException {
        try {
            List<Address> fromLocation = new Geocoder(context, Locale.getDefault()).getFromLocation(latitude, longitude, 1);
            Logger.d("chong--------getCityName==" + fromLocation);
            if (fromLocation == null || fromLocation.isEmpty()) {
                return null;
            }
            return fromLocation.get(0);
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private static Locale getLocale() {
        try {
            return ConfigurationCompat.getLocales(Resources.getSystem().getConfiguration()).get(0);
        } catch (Exception unused) {
            return Locale.getDefault();
        }
    }

    public static void checkLocation() {
        Context applicationContext = MyApplication.getInstance().getApplicationContext();
        String[] strArr = new String[2];
        strArr[0] = Permission.ACCESS_FINE_LOCATION;
        strArr[1] = Build.VERSION.SDK_INT >= 29 ? "android.permission.ACCESS_BACKGROUND_LOCATION" : Permission.ACCESS_COARSE_LOCATION;
        boolean zBooleanValue = ((Boolean) SharedPreferencesUtils.get(applicationContext, Constant.SpConstKey.IS_HAS_LOCATION_PERMISSION, false)).booleanValue();
        boolean zIsPermission = PermissionUtil.isPermission(applicationContext, strArr);
        if (!zBooleanValue && zIsPermission) {
            SharedPreferencesUtils.put(applicationContext, Constant.SpConstKey.IS_HAS_LOCATION_PERMISSION, true);
            weatherFunction(applicationContext);
        } else {
            SharedPreferencesUtils.put(applicationContext, Constant.SpConstKey.IS_HAS_LOCATION_PERMISSION, false);
        }
    }
}
