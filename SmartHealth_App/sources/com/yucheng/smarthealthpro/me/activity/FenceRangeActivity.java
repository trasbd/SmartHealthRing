package com.yucheng.smarthealthpro.me.activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.Circle;
import com.amap.api.maps.model.CircleOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseActivity;
import com.yucheng.smarthealthpro.databinding.ActivityFenceRangeBinding;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import com.yucheng.smarthealthpro.home.view.CustomSelectors;
import com.yucheng.smarthealthpro.sport.utils.GPS;
import com.yucheng.smarthealthpro.sport.utils.GPSConverterUtils;
import com.yucheng.smarthealthpro.utils.Constant;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class FenceRangeActivity extends BaseActivity implements LocationSource {
    AMap aMap;
    ActivityFenceRangeBinding binding;
    Circle circle;
    Marker fenceMarker;
    private LocationSource.OnLocationChangedListener mListener;
    MapView mMapView;
    LatLng target;

    @Override // com.yucheng.smarthealthpro.base.BaseActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) throws NumberFormatException {
        super.onCreate(savedInstanceState);
        ActivityFenceRangeBinding activityFenceRangeBindingInflate = ActivityFenceRangeBinding.inflate(getLayoutInflater());
        this.binding = activityFenceRangeBindingInflate;
        setContentView(activityFenceRangeBindingInflate.getRoot());
        changeTitle(R.string.set_the_fence_range);
        showBack();
        showRightText(getString(R.string.set_range), new NavigationBar.MyOnClickListener() { // from class: com.yucheng.smarthealthpro.me.activity.FenceRangeActivity.1
            @Override // com.yucheng.smarthealthpro.framework.view.NavigationBar.MyOnClickListener
            public void onClick(View btn) {
                FenceRangeActivity.this.showRangeSelect();
            }
        });
        this.mMapView = this.binding.map;
        this.binding.map.onCreate(savedInstanceState);
        this.aMap = this.binding.map.getMap();
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        myLocationStyle.interval(2000L);
        this.aMap.setMyLocationStyle(myLocationStyle);
        this.aMap.setMyLocationEnabled(true);
        double d2 = Double.parseDouble(SharedPreferencesUtils.get(this.context, Constant.Location.Latitude, "-1").toString());
        double d3 = Double.parseDouble(SharedPreferencesUtils.get(this.context, Constant.Location.Longitude, "-1").toString());
        LatLng latLng = new LatLng(d2, d3);
        this.circle = this.aMap.addCircle(new CircleOptions().center(latLng).radius(1000.0d).fillColor(getColor(R.color.dial_item_btn_bg_enable)).strokeColor(getColor(R.color.black)).strokeWidth(15.0f));
        this.aMap.moveCamera(CameraUpdateFactory.zoomTo(15.0f));
        this.aMap.getUiSettings().setZoomControlsEnabled(false);
        if (d2 != -1.0d && d3 != -1.0d) {
            this.aMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        }
        this.aMap.setLocationSource(this);
        this.aMap.setMyLocationEnabled(true);
        this.aMap.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.run_icon))).draggable(true));
        this.fenceMarker = this.aMap.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.run_icon3))).draggable(true));
        this.aMap.setOnCameraChangeListener(new AMap.OnCameraChangeListener() { // from class: com.yucheng.smarthealthpro.me.activity.FenceRangeActivity.2
            @Override // com.amap.api.maps.AMap.OnCameraChangeListener
            public void onCameraChangeFinish(CameraPosition cameraPosition) {
            }

            @Override // com.amap.api.maps.AMap.OnCameraChangeListener
            public void onCameraChange(CameraPosition cameraPosition) {
                FenceRangeActivity.this.target = cameraPosition.target;
                FenceRangeActivity.this.fenceMarker.setPosition(FenceRangeActivity.this.target);
                FenceRangeActivity.this.circle.setCenter(FenceRangeActivity.this.target);
            }
        });
        this.binding.btnNav.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.me.activity.FenceRangeActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                FenceRangeActivity.this.showNav();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showNav() {
        CustomSelectors customSelectors = new CustomSelectors();
        ArrayList arrayList = new ArrayList();
        arrayList.add("高德地图");
        arrayList.add("百度地图");
        arrayList.add("腾讯地图");
        customSelectors.BpLevelPicker(arrayList, null, null, 0, 1, 1, "", "", "", false, CustomSelectors.IsShow.TOP_CONFIRM_CANCEL, CustomSelectors.SelectorsDataNum.ONE, this.context);
        customSelectors.setOnOneSelectorsDataListener(new CustomSelectors.OnOneSelectorsDataListener() { // from class: com.yucheng.smarthealthpro.me.activity.FenceRangeActivity.4
            @Override // com.yucheng.smarthealthpro.home.view.CustomSelectors.OnOneSelectorsDataListener
            public void getSelectorsDataClick(String oneValue, int optionsOne) {
                Log.i("CustomSelectors", "" + oneValue);
                if (optionsOne == 0) {
                    FenceRangeActivity fenceRangeActivity = FenceRangeActivity.this;
                    fenceRangeActivity.openGaoDeMap(fenceRangeActivity.target.latitude, FenceRangeActivity.this.target.longitude);
                } else if (optionsOne == 1) {
                    GPS gpsGcj02_To_Bd09 = GPSConverterUtils.gcj02_To_Bd09(FenceRangeActivity.this.target.latitude, FenceRangeActivity.this.target.longitude);
                    FenceRangeActivity.this.openBaiduMap(gpsGcj02_To_Bd09.getLat(), gpsGcj02_To_Bd09.getLon(), "");
                } else if (optionsOne == 2) {
                    FenceRangeActivity fenceRangeActivity2 = FenceRangeActivity.this;
                    fenceRangeActivity2.openTencent(fenceRangeActivity2.target.latitude, FenceRangeActivity.this.target.longitude, "");
                }
            }
        });
    }

    private LatLngBounds getBounds(List<LatLng> pointlist) {
        LatLngBounds.Builder builder = LatLngBounds.builder();
        if (pointlist == null) {
            return builder.build();
        }
        for (int i2 = 0; i2 < pointlist.size(); i2++) {
            builder.include(pointlist.get(i2));
        }
        return builder.build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showRangeSelect() {
        CustomSelectors customSelectors = new CustomSelectors();
        ArrayList arrayList = new ArrayList();
        for (int i2 = 1; i2 <= 20; i2++) {
            arrayList.add((i2 * 100) + "");
        }
        customSelectors.BpLevelPicker(arrayList, null, null, 0, 1, 1, "", "", "", false, CustomSelectors.IsShow.TOP_CONFIRM_CANCEL, CustomSelectors.SelectorsDataNum.ONE, this.context);
        customSelectors.setOnOneSelectorsDataListener(new CustomSelectors.OnOneSelectorsDataListener() { // from class: com.yucheng.smarthealthpro.me.activity.FenceRangeActivity.5
            @Override // com.yucheng.smarthealthpro.home.view.CustomSelectors.OnOneSelectorsDataListener
            public void getSelectorsDataClick(String oneValue, int optionsOne) {
                Log.i("CustomSelectors", "" + oneValue);
                FenceRangeActivity.this.circle.setRadius(Integer.parseInt(oneValue));
            }
        });
    }

    @Override // com.yucheng.smarthealthpro.base.BaseActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.mMapView.onDestroy();
    }

    @Override // com.amap.api.maps.LocationSource
    public void activate(LocationSource.OnLocationChangedListener listener) {
        this.mListener = listener;
    }

    @Override // com.amap.api.maps.LocationSource
    public void deactivate() {
        this.mListener = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openGaoDeMap(double dlat, double dlon) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setPackage("com.autonavi.minimap");
            intent.addCategory("android.intent.category.DEFAULT");
            intent.setData(Uri.parse("androidamap://route?sourceApplication=" + R.string.app_name + "&sname=我的位置&dlat=" + dlat + "&dlon=" + dlon + "&dev=0&t=0"));
            startActivity(intent);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openBaiduMap(double dlat, double dlon, String dname) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("baidumap://map/direction?origin=我的位置&destination=name:" + dname + "|latlng:" + dlat + "," + dlon + "&mode=driving&sy=3&index=0&target=1"));
        startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openTencent(double dlat, double dlon, String dname) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("qqmap://map/routeplan?type=drive&from=我的位置&fromcoord=0,0&to=" + dname + "&tocoord=" + dlat + "," + dlon + "&policy=1&referer=myapp"));
        startActivity(intent);
    }
}
