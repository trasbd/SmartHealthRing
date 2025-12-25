package com.yucheng.smarthealthpro.sport.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.autonavi.amap.mapcore.AMapEngineUtils;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.orhanobut.logger.Logger;
import com.yanzhenjie.permission.Permission;
import com.yucheng.smarthealthpro.R;

/* loaded from: classes5.dex */
public class GetGPSUtil {
    static GetGPSUtil getGPSUtil;
    LocationListener locationListener = new LocationListener() { // from class: com.yucheng.smarthealthpro.sport.utils.GetGPSUtil.1
        @Override // android.location.LocationListener
        public void onProviderDisabled(String provider) {
        }

        @Override // android.location.LocationListener
        public void onProviderEnabled(String provider) {
        }

        @Override // android.location.LocationListener
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        @Override // android.location.LocationListener
        public void onLocationChanged(Location location) {
            Logger.d("==GetGPSUtil==onLocationChanged==" + location.toString());
        }
    };

    public static GetGPSUtil getInstance() {
        if (getGPSUtil == null) {
            getGPSUtil = new GetGPSUtil();
        }
        return getGPSUtil;
    }

    public String getLngAndLat(Context context) {
        double latitude;
        double longitude;
        LocationManager locationManager = (LocationManager) context.getSystemService(FirebaseAnalytics.Param.LOCATION);
        if (locationManager.isProviderEnabled(GeocodeSearch.GPS)) {
            if (ActivityCompat.checkSelfPermission(context, Permission.ACCESS_FINE_LOCATION) != 0 && ActivityCompat.checkSelfPermission(context, Permission.ACCESS_COARSE_LOCATION) != 0) {
                return "没有GPS权限";
            }
            Location lastKnownLocation = locationManager.getLastKnownLocation(GeocodeSearch.GPS);
            if (lastKnownLocation != null) {
                latitude = lastKnownLocation.getLatitude();
                longitude = lastKnownLocation.getLongitude();
            } else {
                Toast.makeText(context, "GPS信号弱获取不到", 0).show();
                return getLngAndLatWithNetwork(context);
            }
        } else {
            locationManager.requestLocationUpdates("network", 1000L, 0.0f, this.locationListener);
            Location lastKnownLocation2 = locationManager.getLastKnownLocation("network");
            if (lastKnownLocation2 != null) {
                latitude = lastKnownLocation2.getLatitude();
                longitude = lastKnownLocation2.getLongitude();
            } else {
                latitude = 0.0d;
                longitude = 0.0d;
            }
        }
        return longitude + "," + latitude;
    }

    public String getLngAndLatWithNetwork(Context context) {
        double latitude;
        double longitude;
        LocationManager locationManager = (LocationManager) context.getSystemService(FirebaseAnalytics.Param.LOCATION);
        if (ActivityCompat.checkSelfPermission(context, Permission.ACCESS_FINE_LOCATION) != 0 && ActivityCompat.checkSelfPermission(context, Permission.ACCESS_COARSE_LOCATION) != 0) {
            return "没有网络Internet获取权限";
        }
        locationManager.requestLocationUpdates("network", 1000L, 0.0f, this.locationListener);
        Location lastKnownLocation = locationManager.getLastKnownLocation("network");
        if (lastKnownLocation != null) {
            latitude = lastKnownLocation.getLatitude();
            longitude = lastKnownLocation.getLongitude();
        } else {
            latitude = 0.0d;
            longitude = 0.0d;
        }
        return longitude + "," + latitude;
    }

    public static boolean isOpenGpsService(final Context context) {
        LocationManager locationManager;
        if (context == null || (locationManager = (LocationManager) context.getSystemService(FirebaseAnalytics.Param.LOCATION)) == null) {
            return false;
        }
        return locationManager.isProviderEnabled(GeocodeSearch.GPS);
    }

    public static void showLocation(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setTitle(context.getString(R.string.gpsNotOpen));
        builder.setMessage(context.getString(R.string.gpsTip));
        builder.setPositiveButton(context.getString(R.string.goSetting), new DialogInterface.OnClickListener() { // from class: com.yucheng.smarthealthpro.sport.utils.GetGPSUtil.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                Intent intent = new Intent("android.settings.LOCATION_SOURCE_SETTINGS");
                intent.setFlags(AMapEngineUtils.MAX_P20_WIDTH);
                context.startActivity(intent);
            }
        });
        builder.setNegativeButton(context.getString(R.string.cancel), new DialogInterface.OnClickListener() { // from class: com.yucheng.smarthealthpro.sport.utils.GetGPSUtil.3
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }
}
