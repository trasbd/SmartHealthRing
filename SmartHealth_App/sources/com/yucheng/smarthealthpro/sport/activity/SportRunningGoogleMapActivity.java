package com.yucheng.smarthealthpro.sport.activity;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.GsonBuilder;
import com.gyf.immersionbar.ImmersionBar;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.databinding.ActivitySportrunninggooglemapBinding;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.framework.util.SubObserver;
import com.yucheng.smarthealthpro.sport.bean.RunInfo;
import com.yucheng.smarthealthpro.sport.utils.GPSConverterUtils;
import com.yucheng.smarthealthpro.sport.utils.LocationAMapUtils;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.DebouncingClick;
import com.yucheng.smarthealthpro.utils.EventBusExitExerciseEvent;
import com.yucheng.smarthealthpro.utils.EventBusMessageEvent;
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
public class SportRunningGoogleMapActivity extends BaseVbActivity<ActivitySportrunninggooglemapBinding> implements OnMapReadyCallback, Observer {
    private FusedLocationProviderClient fusedLocationProviderClient;
    private GoogleMap googleMap;
    ImageView ivBack;
    ImageView ivLocation;
    private LatLng lastLatLng;
    private RunInfo mRunInfo;
    private int mUnit;
    private Marker marker;
    private PolylineOptions polygonOptions = new PolylineOptions().width(10.0f).color(Color.parseColor("#FFC125"));
    Runnable reconnectCheck = new Runnable() { // from class: com.yucheng.smarthealthpro.sport.activity.SportRunningGoogleMapActivity.1
        @Override // java.lang.Runnable
        public void run() {
            if (YCBTClient.connectState() != 10) {
                SportRunningGoogleMapActivity.this.finish();
            }
        }
    };
    private Marker startMarker;
    TextView tvHeartValue;
    TextView tvKilometreValue;
    TextView tvMinkmValue;
    TextView tvUnit;

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        SubObserver.getInstance().addObs(this);
        EventBus.getDefault().register(this);
        ImmersionBar.with(this).titleBar(this.bar).statusBarDarkFont(true, 0.0f).navigationBarDarkIcon(true, 0.0f).navigationBarColor(R.color.transparent).keyboardEnable(true).init();
        this.fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient((Activity) this);
        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if (supportMapFragment != null) {
            supportMapFragment.getMapAsync(this);
        }
        String str = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.UNIT, "");
        if ((str == null || !str.equals(Constant.SpConstValue.ISO)) && str != null && str.equals(Constant.SpConstValue.INCH)) {
            this.mUnit = 1;
            this.tvUnit.setText(getString(R.string.sport_running_inch_unit));
        } else {
            this.mUnit = 0;
            this.tvUnit.setText(getString(R.string.sport_running_iso_unit));
        }
        RunInfo runInfo = (RunInfo) getIntent().getSerializableExtra("runInfo");
        HashMap map = new HashMap();
        map.put("smsg", runInfo);
        map.put("latLngs", LocationAMapUtils.getInstance().getLatLngs());
        map.put("currLocation", LocationAMapUtils.getInstance().getaMapLocation());
        SubObserver.getInstance().nodifyObservers(map);
    }

    private void initView() {
        this.ivBack = ((ActivitySportrunninggooglemapBinding) this.mBinding).ivBack;
        this.ivLocation = ((ActivitySportrunninggooglemapBinding) this.mBinding).ivLocation;
        this.tvHeartValue = ((ActivitySportrunninggooglemapBinding) this.mBinding).tvFirstValue;
        this.tvMinkmValue = ((ActivitySportrunninggooglemapBinding) this.mBinding).tvFourthlyValue;
        this.tvKilometreValue = ((ActivitySportrunninggooglemapBinding) this.mBinding).tvKilometreValue;
        this.tvUnit = ((ActivitySportrunninggooglemapBinding) this.mBinding).tvUnit;
        this.ivBack.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.sport.activity.SportRunningGoogleMapActivity$$ExternalSyntheticLambda1
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.ivLocation.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.sport.activity.SportRunningGoogleMapActivity$$ExternalSyntheticLambda1
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
    }

    @Override // com.google.android.gms.maps.OnMapReadyCallback
    public void onMapReady(GoogleMap googleMap) throws NumberFormatException {
        this.googleMap = googleMap;
        googleMap.setMapType(1);
        googleMap.setIndoorEnabled(true);
        googleMap.moveCamera(CameraUpdateFactory.zoomTo(18.0f));
        googleMap.setMyLocationEnabled(false);
        googleMap.getUiSettings().setCompassEnabled(false);
        googleMap.addPolyline(this.polygonOptions);
        double d2 = Double.parseDouble(SharedPreferencesUtils.get(this.context, Constant.Location.Latitude, "-1").toString());
        double d3 = Double.parseDouble(SharedPreferencesUtils.get(this.context, Constant.Location.Longitude, "-1").toString());
        if (d2 == -1.0d || d3 == -1.0d) {
            return;
        }
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(d2, d3)));
    }

    private void startLocationUpdates() {
        LatLng latLng = this.lastLatLng;
        if (latLng != null) {
            this.googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17.0f));
        } else {
            this.fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener() { // from class: com.yucheng.smarthealthpro.sport.activity.SportRunningGoogleMapActivity$$ExternalSyntheticLambda0
                @Override // com.google.android.gms.tasks.OnSuccessListener
                public final void onSuccess(Object obj) {
                    this.f$0.lambda$startLocationUpdates$0((Location) obj);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$startLocationUpdates$0(Location location) {
        if (location != null) {
            this.googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(), location.getLongitude())));
            this.googleMap.animateCamera(CameraUpdateFactory.zoomTo(17.0f));
        }
    }

    @Override // java.util.Observer
    public void update(Observable o, Object arg) {
        Map map = (Map) arg;
        RunInfo runInfo = (RunInfo) map.get("smsg");
        this.mRunInfo = runInfo;
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
                List list = (List) map.get("latLngs");
                if (list != null) {
                    ArrayList arrayList = new ArrayList();
                    for (int i2 = 0; i2 < list.size(); i2++) {
                        com.amap.api.maps.model.LatLng latLng = (com.amap.api.maps.model.LatLng) list.get(i2);
                        GPSConverterUtils.gcj_To_Gps84(latLng.latitude, latLng.longitude);
                        arrayList.add(new LatLng(((com.amap.api.maps.model.LatLng) list.get(i2)).latitude, ((com.amap.api.maps.model.LatLng) list.get(i2)).longitude));
                    }
                    makeLine(arrayList);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (map.get("SPORT_STATE") == null || ((Integer) map.get("SPORT_STATE")).intValue() != 0) {
            return;
        }
        finish();
    }

    private void makeLine(List<LatLng> mLatLng) {
        if (mLatLng == null || mLatLng.size() == 0) {
            return;
        }
        mLatLng.get(0);
        LatLng latLng = mLatLng.get(mLatLng.size() - 1);
        this.lastLatLng = latLng;
        makeMarker(latLng);
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        SubObserver.getInstance().delObs(this);
        EventBus.getDefault().unregister(this);
    }

    public void onViewClicked(View view) {
        if (view.getId() == R.id.iv_back) {
            finish();
        } else if (view.getId() == R.id.iv_location) {
            location();
        }
    }

    private void location() {
        startLocationUpdates();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(com.amap.api.maps.model.LatLng latLng) {
        Log.e("TAG", "------onEvent(LatLng latLng)------>" + new GsonBuilder().create().toJson(latLng));
        if (latLng != null) {
            LatLng latLng2 = new LatLng(latLng.latitude, latLng.longitude);
            this.lastLatLng = latLng2;
            makeMarker(latLng2);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventBusMessageEvent messageEvent) {
        if (messageEvent.belState == 0) {
            finish();
        }
    }

    private void makeMarker(LatLng latLng) {
        if (this.startMarker == null) {
            this.startMarker = this.googleMap.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.fromResource(R.mipmap.icon_run_start)));
        }
        Marker marker = this.marker;
        if (marker == null) {
            this.marker = this.googleMap.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.icon_run_end))).draggable(true));
        } else {
            marker.setPosition(latLng);
        }
        this.polygonOptions.add(latLng);
        this.googleMap.addPolyline(this.polygonOptions);
        this.googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getCompile(EventBusMessageEvent messageEvent) {
        if (!isDestroyed() && messageEvent.belState == 0) {
            finish();
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
}
