package com.yucheng.smarthealthpro.sport.activity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.amap.api.location.AMapLocation;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;
import com.gyf.immersionbar.ImmersionBar;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.databinding.ActivitySportrunningmapBinding;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.framework.util.SubObserver;
import com.yucheng.smarthealthpro.sport.bean.RunInfo;
import com.yucheng.smarthealthpro.sport.utils.LocationAMapUtils;
import com.yucheng.smarthealthpro.sport.utils.PathSmoothTool;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.DebouncingClick;
import com.yucheng.smarthealthpro.utils.EventBusExitExerciseEvent;
import com.yucheng.smarthealthpro.utils.EventBusMessageEvent;
import com.yucheng.smarthealthpro.utils.PhoneUtils;
import com.yucheng.ycbtsdk.Constants;
import com.yucheng.ycbtsdk.YCBTClient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes5.dex */
public class SportRunningMapActivity extends BaseVbActivity<ActivitySportrunningmapBinding> implements Observer, LocationSource {
    private AMapLocation amapLocation;
    private CameraUpdate cameraUpdate;
    private LatLng currLatLng;
    private boolean isMapTouch;
    ImageView ivBack;
    ImageView ivLocation;
    private LatLng lastPoint;
    private AMap mAMap;
    private List<AMapLocation> mAMapLocation;
    private LocationSource.OnLocationChangedListener mListener;
    MapView mMapView;
    private Marker mMarker;
    private RunInfo mRunInfo;
    private int mUnit;
    private Polyline polyline;
    private Marker startMarker;
    TextView tvHeartValue;
    TextView tvKilometreValue;
    TextView tvMinkmValue;
    TextView tvUnit;
    private List<LatLng> plist = new ArrayList();
    private boolean isLocation = false;
    Runnable reconnectCheck = new Runnable() { // from class: com.yucheng.smarthealthpro.sport.activity.SportRunningMapActivity.2
        @Override // java.lang.Runnable
        public void run() {
            if (YCBTClient.connectState() != 10) {
                SportRunningMapActivity.this.finish();
            }
        }
    };

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) throws NumberFormatException {
        super.onCreate(savedInstanceState);
        initView();
        SubObserver.getInstance().addObs(this);
        EventBus.getDefault().register(this);
        this.mMapView.onCreate(savedInstanceState);
        initData();
        ImmersionBar.with(this).titleBar(this.bar).statusBarDarkFont(true, 0.0f).navigationBarDarkIcon(true, 0.0f).navigationBarColor(R.color.transparent).keyboardEnable(true).init();
    }

    private void initView() throws NumberFormatException {
        this.mMapView = ((ActivitySportrunningmapBinding) this.mBinding).map;
        this.ivBack = ((ActivitySportrunningmapBinding) this.mBinding).ivBack;
        this.ivLocation = ((ActivitySportrunningmapBinding) this.mBinding).ivLocation;
        this.tvHeartValue = ((ActivitySportrunningmapBinding) this.mBinding).tvFirstValue;
        this.tvMinkmValue = ((ActivitySportrunningmapBinding) this.mBinding).tvFourthlyValue;
        this.tvKilometreValue = ((ActivitySportrunningmapBinding) this.mBinding).tvKilometreValue;
        this.tvUnit = ((ActivitySportrunningmapBinding) this.mBinding).tvUnit;
        this.ivBack.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.sport.activity.SportRunningMapActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.ivLocation.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.sport.activity.SportRunningMapActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.mAMapLocation = new ArrayList();
        getIntent().getParcelableArrayListExtra("plist");
        this.mAMap = this.mMapView.getMap();
        if (getString(R.string.lan).equals("cn")) {
            this.mAMap.setMapLanguage("zh_ch");
        } else {
            this.mAMap.setMapLanguage("en");
        }
        this.mAMap.moveCamera(CameraUpdateFactory.zoomTo(15.0f));
        this.mAMap.getUiSettings().setZoomControlsEnabled(false);
        double d2 = Double.parseDouble(SharedPreferencesUtils.get(this.context, Constant.Location.Latitude, "-1").toString());
        double d3 = Double.parseDouble(SharedPreferencesUtils.get(this.context, Constant.Location.Longitude, "-1").toString());
        if (d2 != -1.0d && d3 != -1.0d) {
            this.mAMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(d2, d3)));
        }
        String str = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.UNIT, "");
        if (str != null && str.equals(Constant.SpConstValue.ISO)) {
            this.mUnit = 0;
            this.tvUnit.setText(getString(R.string.sport_running_iso_unit));
        } else if (str != null && str.equals(Constant.SpConstValue.INCH)) {
            this.mUnit = 1;
            this.tvUnit.setText(getString(R.string.sport_running_inch_unit));
        } else {
            this.mUnit = 0;
            this.tvUnit.setText(getString(R.string.sport_running_iso_unit));
        }
    }

    private void initData() {
        this.mAMap.setLocationSource(this);
        this.mAMap.setMyLocationEnabled(true);
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        myLocationStyle.showMyLocation(false);
        this.mMapView.getMap().setMyLocationStyle(myLocationStyle);
        this.mMapView.getMap().setOnMapTouchListener(new AMap.OnMapTouchListener() { // from class: com.yucheng.smarthealthpro.sport.activity.SportRunningMapActivity.1
            @Override // com.amap.api.maps.AMap.OnMapTouchListener
            public void onTouch(MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    SportRunningMapActivity.this.isMapTouch = true;
                }
            }
        });
        RunInfo runInfo = (RunInfo) getIntent().getSerializableExtra("runInfo");
        HashMap map = new HashMap();
        map.put("smsg", runInfo);
        map.put("latLngs", LocationAMapUtils.getInstance().getLatLngs());
        map.put("currLocation", LocationAMapUtils.getInstance().getaMapLocation());
        SubObserver.getInstance().nodifyObservers(map);
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
    }

    public void makePoint(LatLng point, int type) {
        if (type == 0) {
            if (this.startMarker == null) {
                this.startMarker = this.mAMap.addMarker(new MarkerOptions().position(point).icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.run_icon))).draggable(true));
            }
        } else {
            Marker marker = this.mMarker;
            if (marker == null) {
                this.mMarker = this.mAMap.addMarker(new MarkerOptions().position(point).icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.run_icon3))).draggable(true));
            } else {
                marker.setPosition(point);
            }
        }
    }

    public void makeTime() {
        String str;
        String str2;
        String str3;
        int i2 = this.mRunInfo.runTime % 60;
        int i3 = ((this.mRunInfo.runTime - i2) / 60) % 60;
        int i4 = ((this.mRunInfo.runTime - i2) - (i3 * 60)) / Constants.DATATYPE.FactoryTest;
        if (i2 < 10) {
            str = ":0" + i2;
        } else {
            str = ":" + i2;
        }
        if (i3 < 10) {
            str2 = ":0" + i3 + str;
        } else {
            str2 = ":" + i3 + str;
        }
        if (i4 < 10) {
            str3 = "0" + i4 + str2;
        } else {
            str3 = i4 + str2;
        }
        this.tvHeartValue.setText(str3);
    }

    public void onViewClicked(View view) {
        if (view.getId() == R.id.iv_back) {
            finish();
            return;
        }
        if (view.getId() != R.id.iv_location || PhoneUtils.isFastClick() || this.amapLocation == null) {
            return;
        }
        CameraUpdate cameraUpdate = this.cameraUpdate;
        if (cameraUpdate != null) {
            this.isMapTouch = false;
            this.mAMap.moveCamera(cameraUpdate);
        } else {
            this.mAMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(this.amapLocation.getLatitude(), this.amapLocation.getLongitude())));
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void exerciseState(EventBusExitExerciseEvent eventBusExitExerciseEvent) {
        int i2 = eventBusExitExerciseEvent.sportState;
        if (eventBusExitExerciseEvent.sportType != this.mRunInfo.type) {
            return;
        }
        if ((i2 == 0 || i2 == 1 || i2 == 2 || i2 == 3) && i2 == 0) {
            finish();
        }
    }

    @Override // java.util.Observer
    public void update(Observable observable, Object arg) {
        Map map = (Map) arg;
        if (map.get("smsg") != null) {
            try {
                this.mRunInfo = (RunInfo) map.get("smsg");
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        RunInfo runInfo = this.mRunInfo;
        if (runInfo != null) {
            this.tvHeartValue.setText(runInfo.heart == 0 ? "--" : this.mRunInfo.heart + "");
            this.tvMinkmValue.setText(this.mRunInfo.minkm + "");
            if (this.mUnit == 0) {
                this.tvKilometreValue.setText(String.format("%.2f", Float.valueOf(this.mRunInfo.distance / 1000.0f)) + "");
            } else {
                this.tvKilometreValue.setText(String.format("%.2f", Float.valueOf(this.mRunInfo.distance / 1609.344f)) + "");
            }
        }
        if (map.get("latLngs") != null) {
            try {
                List<LatLng> list = (List) map.get("latLngs");
                if (list != null) {
                    makeline(list);
                }
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
        if (map.get("currLocation") != null) {
            try {
                AMapLocation aMapLocation = (AMapLocation) map.get("currLocation");
                this.amapLocation = aMapLocation;
                if (aMapLocation == null || this.isLocation) {
                    return;
                }
                this.isLocation = true;
                this.mListener.onLocationChanged(aMapLocation);
            } catch (Exception e4) {
                e4.printStackTrace();
            }
        }
    }

    private void makeline(List<LatLng> latLngs) {
        if (latLngs != null && latLngs.size() > 0) {
            if (latLngs.size() < 3) {
                this.currLatLng = latLngs.get(latLngs.size() - 1);
                this.polyline = this.mAMap.addPolyline(new PolylineOptions().addAll(latLngs).color(-16776961));
                makePoint(latLngs.get(0), 0);
                makePoint(this.currLatLng, 1);
                return;
            }
            PathSmoothTool pathSmoothTool = new PathSmoothTool();
            pathSmoothTool.setIntensity(4);
            List<LatLng> listPathOptimize = pathSmoothTool.pathOptimize(latLngs);
            if (listPathOptimize.size() > 0) {
                this.currLatLng = listPathOptimize.get(listPathOptimize.size() - 1);
                this.polyline = this.mAMap.addPolyline(new PolylineOptions().addAll(listPathOptimize).color(-16776961));
                if (!this.isMapTouch) {
                    CameraUpdate cameraUpdateNewLatLngBounds = CameraUpdateFactory.newLatLngBounds(getBounds(listPathOptimize), 200);
                    this.cameraUpdate = cameraUpdateNewLatLngBounds;
                    this.mAMap.moveCamera(cameraUpdateNewLatLngBounds);
                }
                makePoint(listPathOptimize.get(0), 0);
                makePoint(this.currLatLng, 1);
            }
        }
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getCompile(EventBusMessageEvent messageEvent) {
        if (!isDestroyed() && messageEvent.belState == 0) {
            finish();
        }
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.mMapView.onDestroy();
        EventBus.getDefault().unregister(this);
        SubObserver.getInstance().delObs(this);
    }

    @Override // com.amap.api.maps.LocationSource
    public void activate(LocationSource.OnLocationChangedListener listener) {
        this.mListener = listener;
    }

    @Override // com.amap.api.maps.LocationSource
    public void deactivate() {
        this.mListener = null;
    }
}
