package com.yucheng.smarthealthpro.sport.utils;

import android.util.Log;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.model.LatLng;
import com.orhanobut.logger.Logger;
import com.yucheng.smarthealthpro.MyApplication;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.framework.util.SubObserver;
import com.yucheng.smarthealthpro.sport.activity.SportRunningActivity;
import com.yucheng.smarthealthpro.sport.activity.SportRunningGoogleMapActivity;
import com.yucheng.smarthealthpro.sport.activity.SportRunningMapActivity;
import com.yucheng.smarthealthpro.sport.weathers.GCJ2WGS;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.ycbtsdk.AITools;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class LocationAMapUtils {
    private static AMapLocationClient mLocationClient;
    private AMapLocation aMapLocation;
    private int index;
    private LatLng last;
    private LatLng last2;
    private List<LatLng> latLngs;
    private double mLatitude;
    private double mLongitude;
    private AMapLocationClientOption mLocationOption = null;
    private Map<String, Object> map = new HashMap();
    boolean isStart = false;
    int maxCount = 5;
    int currentCount = 0;

    private static class LocationHolder {
        private static final LocationAMapUtils INSTANCE = new LocationAMapUtils();

        private LocationHolder() {
        }
    }

    public static LocationAMapUtils getInstance() {
        return LocationHolder.INSTANCE;
    }

    public void startLocalService() {
        Logger.d("ltf startLocalService isStart=" + this.isStart);
        if (this.isStart) {
            AMapLocationClient aMapLocationClient = mLocationClient;
            if (aMapLocationClient != null) {
                aMapLocationClient.startLocation();
                return;
            }
            return;
        }
        this.isStart = true;
        this.latLngs = new ArrayList();
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
        AMapLocationClientOption defaultOption = getDefaultOption();
        this.mLocationOption = defaultOption;
        mLocationClient.setLocationOption(defaultOption);
        mLocationClient.setLocationListener(new AMapLocationListener() { // from class: com.yucheng.smarthealthpro.sport.utils.LocationAMapUtils$$ExternalSyntheticLambda0
            @Override // com.amap.api.location.AMapLocationListener
            public final void onLocationChanged(AMapLocation aMapLocation) {
                this.f$0.lambda$startLocalService$0(aMapLocation);
            }
        });
        mLocationClient.startLocation();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$startLocalService$0(AMapLocation aMapLocation) {
        double d2;
        LatLng latLng;
        if (aMapLocation != null) {
            this.aMapLocation = aMapLocation;
            if (aMapLocation.getErrorCode() == 0) {
                int i2 = this.index;
                if (i2 > 5) {
                    double[] dArrMakeGps = AITools.getInstance().makeGps(aMapLocation.getLatitude(), aMapLocation.getLongitude());
                    if (dArrMakeGps != null && dArrMakeGps.length >= 2 && dArrMakeGps[0] > 0.0d && dArrMakeGps[1] > 0.0d && (latLng = this.last) != null && (latLng.latitude != dArrMakeGps[0] || this.last.longitude != dArrMakeGps[1])) {
                        List<LatLng> list = this.latLngs;
                        boolean z = list == null || list.size() <= 60;
                        double distance = GCJ2WGS.getDistance(this.last.latitude, this.last.longitude, dArrMakeGps[0], dArrMakeGps[1]);
                        Log.d("ltf", "距离上一个位置" + distance);
                        if (z && (distance < 15.0d || distance > 100.0d)) {
                            if (distance <= 100.0d) {
                                return;
                            }
                            int i3 = this.currentCount;
                            if (i3 < this.maxCount) {
                                this.currentCount = i3 + 1;
                                return;
                            }
                            this.currentCount = 0;
                        }
                        this.last = new LatLng(dArrMakeGps[0], dArrMakeGps[1]);
                        SharedPreferencesUtils.put(MyApplication.getInstance().getApplicationContext(), Constant.Location.Latitude, Double.valueOf(this.last.latitude));
                        SharedPreferencesUtils.put(MyApplication.getInstance().getApplicationContext(), Constant.Location.Longitude, Double.valueOf(this.last.longitude));
                        this.latLngs.add(this.last);
                        this.map.put("latLngs", this.latLngs);
                        this.map.put("currLocation", aMapLocation);
                    } else {
                        LatLng latLng2 = this.last;
                        if (latLng2 == null || latLng2.latitude != aMapLocation.getLatitude() || this.last.longitude != aMapLocation.getLongitude()) {
                            List<LatLng> list2 = this.latLngs;
                            boolean z2 = list2 == null || list2.size() <= 60;
                            LatLng latLng3 = this.last;
                            if (latLng3 != null && z2) {
                                double distance2 = GCJ2WGS.getDistance(latLng3.latitude, this.last.longitude, aMapLocation.getLatitude(), aMapLocation.getLongitude());
                                Log.d("ltf", "距离上一个位置" + distance2);
                                if (distance2 >= 15.0d) {
                                    d2 = 100.0d;
                                    if (distance2 > 100.0d) {
                                    }
                                } else {
                                    d2 = 100.0d;
                                }
                                if (distance2 <= d2) {
                                    return;
                                }
                                int i4 = this.currentCount;
                                if (i4 < this.maxCount) {
                                    this.currentCount = i4 + 1;
                                    return;
                                }
                                this.currentCount = 0;
                            }
                            this.last = new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude());
                            SharedPreferencesUtils.put(MyApplication.getInstance().getApplicationContext(), Constant.Location.Latitude, Double.valueOf(this.last.latitude));
                            SharedPreferencesUtils.put(MyApplication.getInstance().getApplicationContext(), Constant.Location.Longitude, Double.valueOf(this.last.longitude));
                            this.latLngs.add(this.last);
                            this.map.put("latLngs", this.latLngs);
                            this.map.put("currLocation", aMapLocation);
                        }
                    }
                    SubObserver.getInstance().nodifyObservers(SportRunningActivity.class, this.map);
                    SubObserver.getInstance().nodifyObservers(SportRunningMapActivity.class, this.map);
                    SubObserver.getInstance().nodifyObservers(SportRunningGoogleMapActivity.class, this.map);
                    return;
                }
                this.index = i2 + 1;
                return;
            }
            Logger.d("chong---------定位失败\n错误码：" + aMapLocation.getErrorCode() + "\n错误信息:" + aMapLocation.getErrorInfo() + "\n错误描述:" + aMapLocation.getLocationDetail());
            return;
        }
        Logger.d("chong---------定位失败，loc is null");
    }

    public void stopLocalService() {
        Logger.d("ltf stopLocalService");
        this.isStart = false;
        AMapLocationClient aMapLocationClient = mLocationClient;
        if (aMapLocationClient != null) {
            aMapLocationClient.stopLocation();
            mLocationClient.onDestroy();
            mLocationClient = null;
            this.mLocationOption = null;
        }
    }

    private AMapLocationClientOption getDefaultOption() {
        AMapLocationClientOption aMapLocationClientOption = new AMapLocationClientOption();
        aMapLocationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        aMapLocationClientOption.setGpsFirst(true);
        aMapLocationClientOption.setHttpTimeOut(30000L);
        aMapLocationClientOption.setInterval(1000L);
        aMapLocationClientOption.setNeedAddress(true);
        aMapLocationClientOption.setOnceLocation(false);
        aMapLocationClientOption.setOnceLocationLatest(false);
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTPS);
        aMapLocationClientOption.setSensorEnable(false);
        aMapLocationClientOption.setWifiScan(true);
        aMapLocationClientOption.setLocationCacheEnable(false);
        aMapLocationClientOption.setGeoLanguage(AMapLocationClientOption.GeoLanguage.DEFAULT);
        return aMapLocationClientOption;
    }

    public Map<String, Object> getMap() {
        return this.map;
    }

    public List<LatLng> getLatLngs() {
        return this.latLngs;
    }

    public AMapLocation getaMapLocation() {
        return this.aMapLocation;
    }
}
