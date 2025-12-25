package com.yucheng.smarthealthpro.sport.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gyf.immersionbar.ImmersionBar;
import com.orhanobut.logger.Logger;
import com.yanzhenjie.permission.Permission;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.customchart.charts.LineChart;
import com.yucheng.smarthealthpro.customchart.temperature.TempLineChartUtils;
import com.yucheng.smarthealthpro.data.packed.HealthResult;
import com.yucheng.smarthealthpro.database.room.bean.MotionPattern;
import com.yucheng.smarthealthpro.databinding.ActivitySportrunninghismapBinding;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.home.activity.temperature.bean.TemperatureHisListBean;
import com.yucheng.smarthealthpro.sport.SportType;
import com.yucheng.smarthealthpro.sport.bean.SportHisListBean;
import com.yucheng.smarthealthpro.sport.utils.PathSmoothTool;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.DebouncingClick;
import com.yucheng.smarthealthpro.utils.FlowUtils;
import com.yucheng.smarthealthpro.utils.FormatUtil;
import com.yucheng.smarthealthpro.utils.ShareUtils;
import com.yucheng.smarthealthpro.utils.TimeStampUtils;
import com.yucheng.smarthealthpro.viewmodel.SportRunningHisMapViewModel;
import com.yucheng.ycbtsdk.Constants;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

/* loaded from: classes5.dex */
public class SportRunningHisMapActivity extends BaseVbActivity<ActivitySportrunninghismapBinding> implements OnMapReadyCallback, AMapLocationListener {
    private boolean isGpsMap = false;
    ImageView ivBack;
    ImageView ivBg;
    ImageView ivLocation;
    ImageView ivSportImg;
    LinearLayout llFourthly;
    LinearLayout llStep;
    LinearLayout llThirdly;
    private AMapLocationClient locationClient;
    private AMap mAMap;
    LineChart mHeartRateChart;
    AMapLocationClientOption mLocationOption;
    private GoogleMap mMap;
    MapView mMapView;
    private SportHisListBean mSportHisListBean;
    private int mSportType;
    private SportRunningHisMapViewModel mViewModel;
    private SupportMapFragment mapFragment;
    private String mapType;
    AMapLocationClient mlocationClient;
    private LocationSource.OnLocationChangedListener onLocationChangedListener;
    private Polyline polyline;
    RelativeLayout rlSportHisMap;
    TextView tvDistance;
    TextView tvFirstValue;
    TextView tvFourthlyValue;
    TextView tvKeepTime;
    TextView tvMotorPattern;
    TextView tvSecondValue;
    TextView tvStepValue;
    TextView tvThirdlyValue;
    TextView tvTime;
    TextView tvUnit;
    View viewBack;

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) throws NumberFormatException {
        super.onCreate(savedInstanceState);
        initView(savedInstanceState);
        this.mSportHisListBean = (SportHisListBean) getIntent().getSerializableExtra("hislist");
        initData();
        initViewModel();
        ImmersionBar.with(this).titleBar(this.bar).statusBarDarkFont(true, 0.0f).navigationBarDarkIcon(true, 0.0f).navigationBarColor(R.color.transparent).keyboardEnable(true).init();
    }

    private void initView(Bundle savedInstanceState) throws NumberFormatException {
        this.rlSportHisMap = ((ActivitySportrunninghismapBinding) this.mBinding).rlSportHisMap;
        this.mMapView = ((ActivitySportrunninghismapBinding) this.mBinding).map;
        this.ivBack = ((ActivitySportrunninghismapBinding) this.mBinding).ivBack;
        this.ivLocation = ((ActivitySportrunninghismapBinding) this.mBinding).ivLocation;
        this.ivSportImg = ((ActivitySportrunninghismapBinding) this.mBinding).ivSportImg;
        this.tvMotorPattern = ((ActivitySportrunninghismapBinding) this.mBinding).tvMotorPattern;
        this.tvTime = ((ActivitySportrunninghismapBinding) this.mBinding).tvTime;
        this.tvDistance = ((ActivitySportrunninghismapBinding) this.mBinding).tvDistance;
        this.tvKeepTime = ((ActivitySportrunninghismapBinding) this.mBinding).tvKeepTime;
        this.tvFirstValue = ((ActivitySportrunninghismapBinding) this.mBinding).tvFirstValue;
        this.tvStepValue = ((ActivitySportrunninghismapBinding) this.mBinding).tvStepValue;
        this.tvSecondValue = ((ActivitySportrunninghismapBinding) this.mBinding).tvSecondValue;
        this.tvThirdlyValue = ((ActivitySportrunninghismapBinding) this.mBinding).tvThirdlyValue;
        this.tvFourthlyValue = ((ActivitySportrunninghismapBinding) this.mBinding).tvFourthlyValue;
        this.llThirdly = ((ActivitySportrunninghismapBinding) this.mBinding).llThirdly;
        this.llFourthly = ((ActivitySportrunninghismapBinding) this.mBinding).llFourthly;
        this.llStep = ((ActivitySportrunninghismapBinding) this.mBinding).llStep;
        this.tvUnit = ((ActivitySportrunninghismapBinding) this.mBinding).tvUnit;
        this.viewBack = ((ActivitySportrunninghismapBinding) this.mBinding).viewBack;
        this.ivBg = ((ActivitySportrunninghismapBinding) this.mBinding).ivBg;
        this.mHeartRateChart = ((ActivitySportrunninghismapBinding) this.mBinding).lineChartDay;
        this.ivBack.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.sport.activity.SportRunningHisMapActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.ivLocation.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.sport.activity.SportRunningHisMapActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        String stringExtra = getIntent().getStringExtra("map");
        this.mapType = stringExtra;
        if (stringExtra != null && stringExtra.equals("googleMap")) {
            SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_google);
            this.mapFragment = supportMapFragment;
            if (supportMapFragment != null) {
                supportMapFragment.getMapAsync(this);
                return;
            }
            return;
        }
        this.mMapView.onCreate(savedInstanceState);
        this.mAMap = this.mMapView.getMap();
        if (getString(R.string.lan).equals("cn")) {
            this.mAMap.setMapLanguage("zh_cn");
        } else {
            this.mAMap.setMapLanguage("en");
        }
        this.mAMap.moveCamera(CameraUpdateFactory.zoomTo(15.0f));
        double d2 = Double.parseDouble(SharedPreferencesUtils.get(this.context, Constant.Location.Latitude, "-1").toString());
        double d3 = Double.parseDouble(SharedPreferencesUtils.get(this.context, Constant.Location.Longitude, "-1").toString());
        if (d2 == -1.0d || d3 == -1.0d) {
            return;
        }
        this.mAMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(d2, d3)));
    }

    private void initViewModel() {
        this.mViewModel = (SportRunningHisMapViewModel) new ViewModelProvider(this).get(SportRunningHisMapViewModel.class);
        FlowUtils.INSTANCE.collectFlow(this, this.mViewModel.getHistoryHeartRateResultFlow(), new FlowUtils.FlowCollector<HealthResult<List<MotionPattern>>>() { // from class: com.yucheng.smarthealthpro.sport.activity.SportRunningHisMapActivity.1
            @Override // com.yucheng.smarthealthpro.utils.FlowUtils.FlowCollector
            public void collect(HealthResult<List<MotionPattern>> result) throws NumberFormatException {
                SportRunningHisMapActivity.this.onHeartRateData(result.getValue());
            }
        });
        SportHisListBean sportHisListBean = this.mSportHisListBean;
        if (sportHisListBean != null) {
            long beginDate = sportHisListBean.getBeginDate();
            this.mViewModel.getHeartRateDataByTimeRange(beginDate, (this.mSportHisListBean.getRunTime() * 1000) + beginDate);
        }
    }

    private void initData() {
        SportHisListBean sportHisListBean = this.mSportHisListBean;
        if (sportHisListBean != null) {
            int type = sportHisListBean.getType();
            this.mSportType = type;
            if (type == 1 || type == 3 || type == 16 || type == 11 || type == 15) {
                this.isGpsMap = true;
                this.viewBack.setVisibility(8);
                List<LatLng> list = (List) new Gson().fromJson(this.mSportHisListBean.getPathLinePoints(), new TypeToken<List<LatLng>>() { // from class: com.yucheng.smarthealthpro.sport.activity.SportRunningHisMapActivity.2
                }.getType());
                String str = this.mapType;
                if (str != null && str.equals("googleMap")) {
                    this.mMapView.setVisibility(8);
                    if (list != null && list.size() != 0 && this.mMap != null) {
                        makegoogleline(list);
                    }
                } else {
                    this.mMapView.getMap().getUiSettings().setZoomControlsEnabled(false);
                    this.mMapView.setVisibility(0);
                    makeline(list);
                }
            } else {
                this.isGpsMap = false;
                this.viewBack.setVisibility(0);
                this.mMapView.setVisibility(0);
                this.llThirdly.setVisibility(0);
                this.llFourthly.setVisibility(0);
                MyLocationStyle myLocationStyle = new MyLocationStyle();
                myLocationStyle.myLocationType(1);
                this.mMapView.getMap().setMyLocationStyle(myLocationStyle);
                this.mMapView.getMap().getUiSettings().setMyLocationButtonEnabled(false);
                this.mMapView.getMap().setMyLocationEnabled(true);
                this.mMapView.getMap().getUiSettings().setZoomControlsEnabled(false);
            }
            int[] ids = SportType.getIds(this.mSportHisListBean.getType());
            this.tvMotorPattern.setText(getString(ids[0]));
            this.ivSportImg.setBackgroundResource(ids[1]);
            this.llStep.setVisibility(ids[2] == 0 ? 8 : 0);
            this.tvStepValue.setVisibility(ids[2] == 0 ? 8 : 0);
            this.tvDistance.setVisibility(ids[3] == 0 ? 8 : 0);
            this.tvUnit.setVisibility(ids[3] != 0 ? 0 : 8);
            String str2 = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.UNIT, "");
            if (str2 == null || !str2.equals(Constant.SpConstValue.INCH)) {
                this.tvDistance.setText(String.format("%.2f", Float.valueOf(this.mSportHisListBean.getDistance() / 1000.0f)));
                this.tvUnit.setText(getString(R.string.dis_km_unit));
            } else {
                this.tvDistance.setText(String.format("%.2f", Float.valueOf(this.mSportHisListBean.getDistance() / 1609.344f)));
                this.tvUnit.setText(getString(R.string.dis_inch_unit));
            }
            this.tvTime.setText(TimeStampUtils.dateForString(TimeStampUtils.longStampForDate(this.mSportHisListBean.getBeginDate())) + "");
            this.tvKeepTime.setText(TimeStampUtils.parseSecond(this.mSportHisListBean.getRunTime()));
            this.tvFirstValue.setText(this.mSportHisListBean.getHeart() + "");
            this.tvStepValue.setText(this.mSportHisListBean.getSportStep() + "");
            this.tvSecondValue.setText(this.mSportHisListBean.getCalorie() + "");
            String minkm = this.mSportHisListBean.getMinkm();
            this.tvThirdlyValue.setText(minkm == null ? "0" : minkm + "");
            this.tvFourthlyValue.setText(FormatUtil.getBigDecimal(this.mSportHisListBean.getKmh()).setScale(2, 4).floatValue() + "");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onHeartRateData(List<MotionPattern> data) throws NumberFormatException {
        ArrayList arrayList = new ArrayList();
        if (data == null || data.size() == 0) {
            this.mHeartRateChart.setVisibility(8);
            return;
        }
        this.mHeartRateChart.setVisibility(0);
        Calendar calendar = Calendar.getInstance();
        int i2 = 0;
        long startTimestamp = 0;
        long startTimestamp2 = 0;
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < data.size(); i5++) {
            if (data.get(i5).getSportHeartRate() >= 40 && data.get(i5).getSportHeartRate() <= 220) {
                startTimestamp = data.get(i5).getStartTimestamp();
                startTimestamp2 = data.get(i5).getStartTimestamp();
                calendar.setTimeInMillis(startTimestamp);
                int second = getSecond(calendar);
                if (i3 == 0 || i3 > second) {
                    i3 = second;
                }
                calendar.setTimeInMillis(startTimestamp2);
                int second2 = getSecond(calendar);
                if (i4 == 0 || i4 < second2) {
                    i4 = second2;
                }
                if (i2 == 0 || i2 != second) {
                    arrayList.add(new TemperatureHisListBean(TimeStampUtils.dateForStringToDateHHmmss(TimeStampUtils.longStampForDate(data.get(i5).getStartTimestamp())), data.get(i5).getSportHeartRate() + "", "正常", second));
                    i2 = second;
                }
            }
        }
        if (arrayList.isEmpty()) {
            this.mHeartRateChart.setVisibility(8);
            return;
        }
        String toDay = TimeStampUtils.getToDay();
        calendar.setTimeInMillis(startTimestamp);
        calendar.setTimeInMillis(startTimestamp2);
        Collections.reverse(arrayList);
        TempLineChartUtils.initSportHeartRateLineChart(this.mHeartRateChart, this.context, arrayList, toDay, null, TempLineChartUtils.FUNCTION.HR, 220.0f, 0.0f, i4, i3, 2, 5, TempLineChartUtils.FORMATTER.DAY, true);
    }

    public int getMin(Calendar instance) {
        return instance.get(12) + (instance.get(11) * 60);
    }

    public int getSecond(Calendar instance) {
        return instance.get(13) + (instance.get(12) * 60) + (instance.get(11) * Constants.DATATYPE.FactoryTest);
    }

    public void onViewClicked(View view) {
        if (view.getId() == R.id.iv_back) {
            finish();
            return;
        }
        if (view.getId() == R.id.iv_location && checkCanClick()) {
            String str = this.mapType;
            if (str != null && str.equals("googleMap")) {
                this.mMap.snapshot(new GoogleMap.SnapshotReadyCallback() { // from class: com.yucheng.smarthealthpro.sport.activity.SportRunningHisMapActivity.3
                    @Override // com.google.android.gms.maps.GoogleMap.SnapshotReadyCallback
                    public void onSnapshotReady(final Bitmap bitmap) {
                        SportRunningHisMapActivity.this.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.sport.activity.SportRunningHisMapActivity.3.1
                            @Override // java.lang.Runnable
                            public void run() {
                                SportRunningHisMapActivity.this.ivBg.setVisibility(0);
                                SportRunningHisMapActivity.this.ivBg.setImageBitmap(bitmap);
                                ShareUtils.share(SportRunningHisMapActivity.this);
                                SportRunningHisMapActivity.this.ivBg.setVisibility(8);
                            }
                        });
                    }
                });
            } else {
                this.mAMap.getMapScreenShot(new AMap.OnMapScreenShotListener() { // from class: com.yucheng.smarthealthpro.sport.activity.SportRunningHisMapActivity.4
                    @Override // com.amap.api.maps.AMap.OnMapScreenShotListener
                    public void onMapScreenShot(Bitmap bitmap, int i2) {
                    }

                    @Override // com.amap.api.maps.AMap.OnMapScreenShotListener
                    public void onMapScreenShot(final Bitmap bitmap) {
                        SportRunningHisMapActivity.this.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.sport.activity.SportRunningHisMapActivity.4.1
                            @Override // java.lang.Runnable
                            public void run() {
                                SportRunningHisMapActivity.this.ivBg.setVisibility(0);
                                SportRunningHisMapActivity.this.ivBg.setImageBitmap(bitmap);
                                ShareUtils.share(SportRunningHisMapActivity.this);
                                SportRunningHisMapActivity.this.ivBg.setVisibility(8);
                            }
                        });
                    }
                });
            }
        }
    }

    private void makeline(List<LatLng> mLatLng) {
        if (mLatLng != null && mLatLng.size() > 0) {
            PathSmoothTool pathSmoothTool = new PathSmoothTool();
            pathSmoothTool.setIntensity(4);
            List<LatLng> listPathOptimize = pathSmoothTool.pathOptimize(mLatLng);
            if (listPathOptimize.size() > 0) {
                this.polyline = this.mAMap.addPolyline(new PolylineOptions().addAll(listPathOptimize).color(-16776961));
                this.mAMap.moveCamera(CameraUpdateFactory.newLatLngBounds(getBounds(listPathOptimize), 200));
                makePoint(listPathOptimize.get(0), 0);
                makePoint(listPathOptimize.get(listPathOptimize.size() - 1), 1);
                return;
            }
            this.mAMap.moveCamera(CameraUpdateFactory.newLatLngBounds(getBounds(mLatLng), 200));
            makePoint(mLatLng.get(0), 0);
            makePoint(mLatLng.get(mLatLng.size() - 1), 1);
            return;
        }
        this.mAMap.setLocationSource(new LocationSource() { // from class: com.yucheng.smarthealthpro.sport.activity.SportRunningHisMapActivity.5
            @Override // com.amap.api.maps.LocationSource
            public void activate(LocationSource.OnLocationChangedListener onLocationChangedListener) {
                SportRunningHisMapActivity.this.onLocationChangedListener = onLocationChangedListener;
                if (SportRunningHisMapActivity.this.locationClient == null) {
                    try {
                        SportRunningHisMapActivity.this.locationClient = new AMapLocationClient(SportRunningHisMapActivity.this);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    AMapLocationClientOption aMapLocationClientOption = new AMapLocationClientOption();
                    SportRunningHisMapActivity.this.locationClient.setLocationListener(SportRunningHisMapActivity.this);
                    aMapLocationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
                    aMapLocationClientOption.setOnceLocation(true);
                    SportRunningHisMapActivity.this.locationClient.setLocationOption(aMapLocationClientOption);
                    SportRunningHisMapActivity.this.locationClient.startLocation();
                    Logger.d("chong------开启定位");
                }
            }

            @Override // com.amap.api.maps.LocationSource
            public void deactivate() {
                if (SportRunningHisMapActivity.this.locationClient != null) {
                    SportRunningHisMapActivity.this.locationClient.stopLocation();
                    SportRunningHisMapActivity.this.locationClient.onDestroy();
                }
            }
        });
        this.mAMap.setMyLocationEnabled(true);
    }

    @Override // com.amap.api.location.AMapLocationListener
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (this.onLocationChangedListener == null || aMapLocation == null) {
            return;
        }
        if (aMapLocation.getErrorCode() == 0) {
            this.onLocationChangedListener.onLocationChanged(aMapLocation);
            Logger.d("chong------定位成功" + aMapLocation.getLatitude() + ":" + aMapLocation.getLongitude());
        } else {
            Logger.d("chong------定位失败" + aMapLocation.getErrorCode() + ":" + aMapLocation.getErrorInfo());
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

    public void makePoint(LatLng point, int type) {
        if (type == 0) {
            this.mAMap.addMarker(new MarkerOptions().position(point).icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.run_icon))).draggable(true));
        } else {
            this.mAMap.addMarker(new MarkerOptions().position(point).icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.run_icon3))).draggable(true));
        }
    }

    private void makegoogleline(List<LatLng> mLatLng) {
        ArrayList arrayList = new ArrayList();
        if (mLatLng.size() > 0) {
            for (int i2 = 0; i2 < mLatLng.size(); i2++) {
                arrayList.add(new com.google.android.gms.maps.model.LatLng(mLatLng.get(i2).latitude, mLatLng.get(i2).longitude));
            }
            this.mMap.addPolyline(new com.google.android.gms.maps.model.PolylineOptions().addAll(arrayList));
            this.mMap.moveCamera(com.google.android.gms.maps.CameraUpdateFactory.newLatLng(new com.google.android.gms.maps.model.LatLng(mLatLng.get(0).latitude, mLatLng.get(0).longitude)));
            makeGooglePoint((com.google.android.gms.maps.model.LatLng) arrayList.get(0), 0);
            makeGooglePoint((com.google.android.gms.maps.model.LatLng) arrayList.get(arrayList.size() - 1), 1);
            return;
        }
        makeGooglePoint((com.google.android.gms.maps.model.LatLng) arrayList.get(0), 0);
    }

    public void makeGooglePoint(com.google.android.gms.maps.model.LatLng point, int type) {
        if (type == 0) {
            this.mMap.addMarker(new com.google.android.gms.maps.model.MarkerOptions().position(new com.google.android.gms.maps.model.LatLng(point.latitude, point.longitude)).title("Marker").icon(com.google.android.gms.maps.model.BitmapDescriptorFactory.fromResource(R.mipmap.run_icon)));
        } else {
            this.mMap.addMarker(new com.google.android.gms.maps.model.MarkerOptions().position(new com.google.android.gms.maps.model.LatLng(point.latitude, point.longitude)).title("Marker").icon(com.google.android.gms.maps.model.BitmapDescriptorFactory.fromResource(R.mipmap.run_icon3)));
        }
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.mMapView.onDestroy();
        AMapLocationClient aMapLocationClient = this.mlocationClient;
        if (aMapLocationClient != null) {
            aMapLocationClient.onDestroy();
        }
    }

    @Override // com.google.android.gms.maps.OnMapReadyCallback
    public void onMapReady(GoogleMap googleMap) throws NumberFormatException {
        this.mMap = googleMap;
        googleMap.moveCamera(com.google.android.gms.maps.CameraUpdateFactory.zoomTo(15.0f));
        double d2 = Double.parseDouble(SharedPreferencesUtils.get(this.context, Constant.Location.Latitude, "-1").toString());
        double d3 = Double.parseDouble(SharedPreferencesUtils.get(this.context, Constant.Location.Longitude, "-1").toString());
        if (d2 != -1.0d && d3 != -1.0d) {
            googleMap.moveCamera(com.google.android.gms.maps.CameraUpdateFactory.newLatLng(new com.google.android.gms.maps.model.LatLng(d2, d3)));
        }
        initData();
        if (ActivityCompat.checkSelfPermission(this, Permission.ACCESS_FINE_LOCATION) == 0 || ActivityCompat.checkSelfPermission(this, Permission.ACCESS_COARSE_LOCATION) == 0) {
            this.mMap.setMyLocationEnabled(false);
        }
    }
}
