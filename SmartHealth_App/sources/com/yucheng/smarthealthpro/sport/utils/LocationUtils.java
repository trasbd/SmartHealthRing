package com.yucheng.smarthealthpro.sport.utils;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import androidx.core.app.ActivityCompat;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.orhanobut.logger.Logger;
import com.yanzhenjie.permission.Permission;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* loaded from: classes5.dex */
public class LocationUtils {
    private static double EARTH_RADIUS = 6378.137d;
    private static ArrayList<AddressCallback> addressCallbacks;
    private static Location location;
    private static volatile LocationUtils uniqueInstance;
    private AddressCallback addressCallback;
    private boolean isInit = false;
    private LocationListener locationListener = new LocationListener() { // from class: com.yucheng.smarthealthpro.sport.utils.LocationUtils.1
        @Override // android.location.LocationListener
        public void onProviderDisabled(String provider) {
        }

        @Override // android.location.LocationListener
        public void onProviderEnabled(String provider) {
        }

        @Override // android.location.LocationListener
        public void onStatusChanged(String provider, int status, Bundle arg2) {
        }

        @Override // android.location.LocationListener
        public void onLocationChanged(Location loc) {
            Logger.d("==LocationUtils==onLocationChanged==" + loc.toString());
        }
    };
    private LocationManager locationManager;
    private Context mContext;

    public interface AddressCallback {
        void onGetAddress(Address address);

        void onGetLocation(double lat, double lng);
    }

    private static double rad(double d2) {
        return (d2 * 3.141592653589793d) / 180.0d;
    }

    public AddressCallback getAddressCallback() {
        return this.addressCallback;
    }

    public void setAddressCallback(AddressCallback addressCallback) throws IOException {
        this.addressCallback = addressCallback;
        if (this.isInit) {
            showLocation();
        } else {
            this.isInit = true;
        }
    }

    private LocationUtils(Context context) throws IOException {
        this.mContext = context;
        getLocation();
    }

    public static LocationUtils getInstance(Context context) {
        if (uniqueInstance == null) {
            synchronized (LocationUtils.class) {
                if (uniqueInstance == null) {
                    addressCallbacks = new ArrayList<>();
                    uniqueInstance = new LocationUtils(context);
                }
            }
        }
        return uniqueInstance;
    }

    private void addAddressCallback(AddressCallback addressCallback) throws IOException {
        addressCallbacks.add(addressCallback);
        if (this.isInit) {
            showLocation();
        }
    }

    public void removeAddressCallback(AddressCallback addressCallback) {
        if (addressCallbacks.contains(addressCallback)) {
            addressCallbacks.remove(addressCallback);
        }
    }

    public void cleareAddressCallback() {
        removeLocationUpdatesListener();
        addressCallbacks.clear();
    }

    private void getLocation() throws IOException {
        this.locationManager = (LocationManager) this.mContext.getSystemService(FirebaseAnalytics.Param.LOCATION);
        if (ActivityCompat.checkSelfPermission(this.mContext, Permission.ACCESS_FINE_LOCATION) == 0 || ActivityCompat.checkSelfPermission(this.mContext, Permission.ACCESS_COARSE_LOCATION) == 0) {
            List<String> providers = this.locationManager.getProviders(true);
            String str = GeocodeSearch.GPS;
            if (providers.contains(GeocodeSearch.GPS)) {
                Logger.d("=====GPS_PROVIDER=====");
            } else {
                str = "network";
                if (providers.contains("network")) {
                    Logger.d("=====NETWORK_PROVIDER=====");
                } else {
                    Logger.d("=====NO_PROVIDER=====");
                    Intent intent = new Intent();
                    intent.setAction("android.settings.LOCATION_SOURCE_SETTINGS");
                    this.mContext.startActivity(intent);
                    return;
                }
            }
            String str2 = str;
            Location lastKnownLocation = this.locationManager.getLastKnownLocation(str2);
            location = lastKnownLocation;
            if (lastKnownLocation != null) {
                Logger.d("==显示当前设备的位置信息==");
                showLocation();
            } else {
                Logger.d("==Google服务被墙的解决办法==");
                getLngAndLatWithNetwork();
            }
            this.locationManager.requestLocationUpdates(str2, 1000L, 0.0f, this.locationListener);
        }
    }

    private void showLocation() throws IOException {
        Location location2 = location;
        if (location2 == null) {
            getLocation();
            return;
        }
        double latitude = location2.getLatitude();
        double longitude = location.getLongitude();
        Log.i("定位经纬度", "--latitude--" + latitude + "--longitude--" + longitude);
        AddressCallback addressCallback = this.addressCallback;
        if (addressCallback != null) {
            addressCallback.onGetLocation(latitude, longitude);
        }
        getAddress(latitude, longitude);
    }

    private void getAddress(double latitude, double longitude) throws IOException {
        try {
            List<Address> fromLocation = new Geocoder(this.mContext, Locale.getDefault()).getFromLocation(latitude, longitude, 1);
            if (fromLocation != null) {
                Address address = fromLocation.get(0);
                address.getCountryName();
                address.getCountryCode();
                address.getAdminArea();
                address.getLocality();
                address.getSubLocality();
                address.getFeatureName();
                for (int i2 = 0; address.getAddressLine(i2) != null; i2++) {
                    Logger.d("addressLine=====" + address.getAddressLine(i2));
                }
                AddressCallback addressCallback = this.addressCallback;
                if (addressCallback != null) {
                    addressCallback.onGetAddress(address);
                }
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    private void removeLocationUpdatesListener() {
        if (this.locationManager != null) {
            uniqueInstance = null;
            this.locationManager.removeUpdates(this.locationListener);
        }
    }

    private void getLngAndLatWithNetwork() throws IOException {
        if (ActivityCompat.checkSelfPermission(this.mContext, Permission.ACCESS_FINE_LOCATION) == 0 || ActivityCompat.checkSelfPermission(this.mContext, Permission.ACCESS_COARSE_LOCATION) == 0) {
            LocationManager locationManager = (LocationManager) this.mContext.getSystemService(FirebaseAnalytics.Param.LOCATION);
            locationManager.requestLocationUpdates("network", 1000L, 0.0f, this.locationListener);
            location = locationManager.getLastKnownLocation("network");
            showLocation();
        }
    }

    public static double getDistance(double lat1, double lng1, double lat2, double lng2) {
        double dRad = rad(lat1);
        double dRad2 = rad(lat2);
        return (Math.round(((Math.asin(Math.sqrt(Math.pow(Math.sin((dRad - dRad2) / 2.0d), 2.0d) + ((Math.cos(dRad) * Math.cos(dRad2)) * Math.pow(Math.sin((rad(lng1) - rad(lng2)) / 2.0d), 2.0d)))) * 2.0d) * EARTH_RADIUS) * 10000.0d) / 10000.0d) * 1000.0d;
    }
}
