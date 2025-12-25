package com.yucheng.smarthealthpro.home.fragment;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.alibaba.fastjson2.internal.asm.Opcodes;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.gms.location.DeviceOrientationRequest;
import com.google.gson.Gson;
import com.gyf.immersionbar.ImmersionBar;
import com.orhanobut.logger.Logger;
import com.realsil.sdk.dfu.DfuConstants;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.tencent.bugly.crashreport.BuglyLog;
import com.tencent.bugly.crashreport.CrashReport;
import com.yanzhenjie.permission.Permission;
import com.yucheng.smarthealthpro.MainActivity;
import com.yucheng.smarthealthpro.MyApplication;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbFragment;
import com.yucheng.smarthealthpro.customchart.utils.SleepUtil;
import com.yucheng.smarthealthpro.database.room.bean.BloodKetones;
import com.yucheng.smarthealthpro.database.room.bean.BloodLipids;
import com.yucheng.smarthealthpro.database.room.bean.BloodPressure;
import com.yucheng.smarthealthpro.database.room.bean.BodyData;
import com.yucheng.smarthealthpro.database.room.bean.HealthMetric;
import com.yucheng.smarthealthpro.database.room.bean.HeartRate;
import com.yucheng.smarthealthpro.database.room.bean.Physiotherapy;
import com.yucheng.smarthealthpro.database.room.bean.Sleep;
import com.yucheng.smarthealthpro.database.room.bean.UricAcid;
import com.yucheng.smarthealthpro.databinding.FragmentHomeBinding;
import com.yucheng.smarthealthpro.framework.http.HttpUtils;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.framework.util.ToastUtil;
import com.yucheng.smarthealthpro.framework.util.UUIDUtils;
import com.yucheng.smarthealthpro.framework.view.MyTextView;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import com.yucheng.smarthealthpro.home.activity.CompileActivity;
import com.yucheng.smarthealthpro.home.activity.DeviceListActivity;
import com.yucheng.smarthealthpro.home.activity.HealthyActivity;
import com.yucheng.smarthealthpro.home.activity.RankingActivity;
import com.yucheng.smarthealthpro.home.activity.bloodfat.activity.BloodFatActivity;
import com.yucheng.smarthealthpro.home.activity.bloodoxygen.activity.BloodOxygenActivity;
import com.yucheng.smarthealthpro.home.activity.bloodpressure.activity.BloodPressureActivity;
import com.yucheng.smarthealthpro.home.activity.bloodsugar.activity.BloodSugarActivity;
import com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgActivity;
import com.yucheng.smarthealthpro.home.activity.heartrate.activity.HeartRateActivity;
import com.yucheng.smarthealthpro.home.activity.hrv.activity.HRVActivity;
import com.yucheng.smarthealthpro.home.activity.ketone.activity.KetoneActivity;
import com.yucheng.smarthealthpro.home.activity.pdnumber.activity.PDNumberActivity;
import com.yucheng.smarthealthpro.home.activity.phy.activity.PhyActivity;
import com.yucheng.smarthealthpro.home.activity.pressure.PressureActivity;
import com.yucheng.smarthealthpro.home.activity.respiratoryrate.activity.RespiratoryRateActivity;
import com.yucheng.smarthealthpro.home.activity.running.activity.RunningActivity;
import com.yucheng.smarthealthpro.home.activity.sleep.activity.SleepActivity;
import com.yucheng.smarthealthpro.home.activity.sleep.bean.SleepSummary;
import com.yucheng.smarthealthpro.home.activity.temperature.activity.TemperatureActivity;
import com.yucheng.smarthealthpro.home.activity.uricacid.activity.UricAcidActivity;
import com.yucheng.smarthealthpro.home.adapter.HomeFunctionAdapter;
import com.yucheng.smarthealthpro.home.bean.HomeFunctionBean;
import com.yucheng.smarthealthpro.home.bean.RealDataResponse;
import com.yucheng.smarthealthpro.home.util.DataTools;
import com.yucheng.smarthealthpro.home.util.HealthDataFilterKt;
import com.yucheng.smarthealthpro.home.util.HomeFragmentModelUtil;
import com.yucheng.smarthealthpro.home.view.StepView;
import com.yucheng.smarthealthpro.login.normal.WebViewActivity;
import com.yucheng.smarthealthpro.me.activity.MeDeviceActivity;
import com.yucheng.smarthealthpro.me.activity.PermissionActivity;
import com.yucheng.smarthealthpro.sport.view.CommonDialog;
import com.yucheng.smarthealthpro.sport.viewmodel.SportViewModel;
import com.yucheng.smarthealthpro.sport.weathers.WeatherUtils;
import com.yucheng.smarthealthpro.utils.AppDateMgr;
import com.yucheng.smarthealthpro.utils.AppImageMgr;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.DataSyncEvent;
import com.yucheng.smarthealthpro.utils.DataSyncUtils;
import com.yucheng.smarthealthpro.utils.DebouncingClick;
import com.yucheng.smarthealthpro.utils.EventBusMessageEvent;
import com.yucheng.smarthealthpro.utils.EventBusSyncData;
import com.yucheng.smarthealthpro.utils.FlowUtils;
import com.yucheng.smarthealthpro.utils.FormatUtil;
import com.yucheng.smarthealthpro.utils.MLog;
import com.yucheng.smarthealthpro.utils.MultiLanguageUtils;
import com.yucheng.smarthealthpro.utils.PackageUtils;
import com.yucheng.smarthealthpro.utils.PermissionUtil;
import com.yucheng.smarthealthpro.utils.SyncState;
import com.yucheng.smarthealthpro.utils.TimeStampUtils;
import com.yucheng.smarthealthpro.utils.TimeZoneUtils;
import com.yucheng.smarthealthpro.utils.Tools;
import com.yucheng.smarthealthpro.utils.TransUtils;
import com.yucheng.smarthealthpro.utils.UploadUtil;
import com.yucheng.smarthealthpro.utils.YearToDayListUtils;
import com.yucheng.smarthealthpro.viewmodel.HomeViewModel;
import com.yucheng.ycbtsdk.Constants;
import com.yucheng.ycbtsdk.YCBTClient;
import com.yucheng.ycbtsdk.response.BleDataResponse;
import com.yucheng.ycbtsdk.response.BleRealDataResponse;
import com.yucheng.ycbtsdk.utils.SPUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kotlin.Pair;
import org.apache.commons.lang3.StringUtils;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes5.dex */
public class HomeFragment extends BaseVbFragment<FragmentHomeBinding> {
    protected DataSyncUtils dataSyncUtils;
    MyTextView home_pair_permission_title;
    LinearLayout llCompile;
    LinearLayout llHealthy;
    LinearLayout llNoFunction;
    LinearLayout llRanking;
    LinearLayout llRankingHealthy;
    View lyAnswer;
    private AppImageMgr mAppImageMgr;
    private int mBloodSugarUnit;
    private MBroadcastReceiver mBroadcastReceiver;
    private HomeFunctionAdapter mHomeFunctionAdapter;
    private List<HomeFunctionBean> mHomeFunctionBean;
    RecyclerView mRecyclerView;
    SmartRefreshLayout mSmartRefreshLayout;
    SpinKitView mSpinKitView;
    StepView mStepView;
    private int mTempUnit;
    private String mToDay;
    private int mUnit;
    private String mUricAcidUnit;
    private MainActivity mainActivity;
    RelativeLayout rlRunning;
    RelativeLayout rvDialog;
    private int sportCalorie;
    private int sportDistance;
    private int sportStep;
    private int sportTarget;
    private CommonDialog tipDialog;
    TextView tvAnswer;
    TextView tvKcal;
    TextView tvOdo;
    TextView tvOdoUnit;
    TextView tvPermission;
    TextView tvStep;
    TextView tvSteps;
    private ExecutorService uploadThread = Executors.newSingleThreadExecutor();
    private List<HomeFunctionBean> mHomeFunctionBeanCache = new ArrayList();
    private HashMap<String, Integer> mFunctionSortMap = new HashMap<>();
    private boolean isFirst = true;
    private boolean isFirstConnect = false;
    private boolean isResume = false;
    final int SLEEP_LIMIT_HIGH = 57600;
    int failCount = 0;
    int failMaxCount = 3;
    private SportViewModel mSportViewModel = null;
    private final DataSyncEvent dataSyncEvent = new DataSyncEvent() { // from class: com.yucheng.smarthealthpro.home.fragment.HomeFragment$$ExternalSyntheticLambda0
        @Override // com.yucheng.smarthealthpro.utils.DataSyncEvent
        public final void callback(SyncState syncState) {
            this.f$0.lambda$new$0(syncState);
        }
    };
    private HomeViewModel mViewModel = null;
    Runnable checkDeviceIcon = new Runnable() { // from class: com.yucheng.smarthealthpro.home.fragment.HomeFragment.3
        @Override // java.lang.Runnable
        public void run() {
            if (HomeFragment.this.isResume) {
                HomeFragment.this.freshConnectIcon();
                HomeFragment.this.handler.postDelayed(HomeFragment.this.checkDeviceIcon, 2000L);
            }
        }
    };
    Runnable dismissRun = new Runnable() { // from class: com.yucheng.smarthealthpro.home.fragment.HomeFragment.7
        @Override // java.lang.Runnable
        public void run() {
            HomeFragment.this.dismissDialog();
        }
    };
    Handler handler = new Handler(new Handler.Callback() { // from class: com.yucheng.smarthealthpro.home.fragment.HomeFragment$$ExternalSyntheticLambda1
        @Override // android.os.Handler.Callback
        public final boolean handleMessage(Message message) {
            return this.f$0.lambda$new$1(message);
        }
    });
    Runnable checkBondRun = new Runnable() { // from class: com.yucheng.smarthealthpro.home.fragment.HomeFragment.30
        @Override // java.lang.Runnable
        public void run() {
            if (HomeFragment.this.checkBond()) {
                return;
            }
            HomeFragment.this.handler.postDelayed(HomeFragment.this.checkBondRun, 2000L);
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(final SyncState syncState) {
        if (this.isResume) {
            requireActivity().runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.home.fragment.HomeFragment.1
                @Override // java.lang.Runnable
                public void run() {
                    if (syncState == SyncState.FAILED) {
                        ToastUtil.getInstance(HomeFragment.this.getActivity()).toast(HomeFragment.this.getString(R.string.ecg_sync_data_failed));
                        HomeFragment.this.syncFinish();
                        WeatherUtils.weatherFunction(HomeFragment.this.requireActivity());
                    }
                    if (syncState == SyncState.SUCCESS || syncState == SyncState.FAILED) {
                        UploadUtil.checkUploadLog(HomeFragment.this.getActivity(), true);
                    }
                    if (syncState == SyncState.END) {
                        HomeFragment.this.dismissDialog();
                        HomeFragment.this.syncRealData(1);
                        HomeFragment.this.getDbWatchesData();
                        WeatherUtils.weatherFunction(HomeFragment.this.requireActivity());
                    }
                }
            });
        }
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbFragment, com.yucheng.smarthealthpro.framework.BaseFragment
    protected int initLayout() {
        return R.layout.fragment_home;
    }

    @Override // com.yucheng.smarthealthpro.framework.BaseFragment, com.gyf.immersionbar.components.ImmersionFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        this.dataSyncUtils = DataSyncUtils.INSTANCE.getInstance(requireContext().getApplicationContext());
    }

    @Override // com.gyf.immersionbar.components.ImmersionFragment, androidx.fragment.app.Fragment
    public void onResume() throws NumberFormatException {
        super.onResume();
        checkBond();
        checkBattery();
        YCBTClient.appRegisterRealDataCallBack(new BleRealDataResponse() { // from class: com.yucheng.smarthealthpro.home.fragment.HomeFragment.2
            @Override // com.yucheng.ycbtsdk.response.BleRealDataResponse
            public void onRealDataResponse(int i2, HashMap hashMap) {
                if (i2 == 1536 && hashMap != null && hashMap.size() > 0) {
                    HomeFragment.this.sportStep = ((Integer) hashMap.get("sportStep")).intValue();
                    HomeFragment.this.sportDistance = ((Integer) hashMap.get("sportDistance")).intValue();
                    HomeFragment.this.sportCalorie = ((Integer) hashMap.get("sportCalorie")).intValue();
                    SharedPreferencesUtils.put(HomeFragment.this.context, Constant.SpConstKey.HOME_STEP, Integer.valueOf(HomeFragment.this.sportStep));
                    SharedPreferencesUtils.put(HomeFragment.this.context, Constant.SpConstKey.HOME_KM, Integer.valueOf(HomeFragment.this.sportDistance));
                    SharedPreferencesUtils.put(HomeFragment.this.context, Constant.SpConstKey.HOME_KCAL, Integer.valueOf(HomeFragment.this.sportCalorie));
                    SharedPreferencesUtils.put(HomeFragment.this.context, Constant.SpConstKey.HOME_STEP_TIME, Long.valueOf(System.currentTimeMillis()));
                    HomeFragment.this.handler.sendEmptyMessage(1);
                    int iIntValue = ((Integer) SharedPreferencesUtils.get(HomeFragment.this.context, Constant.SpConstKey.TARGET_NUMBER_OF_MOVEMENT_STEPS, Integer.valueOf(DfuConstants.MAX_NOTIFICATION_LOCK_WAIT_TIME))).intValue();
                    String str = (String) SharedPreferencesUtils.get(HomeFragment.this.context, Constant.SpConstKey.STEPS_TIP_DIALOG, "");
                    String str2 = AppDateMgr.todayYyyyMmDd();
                    if (Constant.isSmartHealth() && HomeFragment.this.sportStep >= iIntValue && !str.equals(str2)) {
                        SharedPreferencesUtils.put(HomeFragment.this.context, Constant.SpConstKey.STEPS_TIP_DIALOG, str2);
                        HomeFragment homeFragment = HomeFragment.this;
                        homeFragment.showTipDialog(homeFragment.getString(R.string.exercise_tip));
                    }
                    Log.e(HomeFragment.this.TAG, "实时监听步数 sportStep = " + HomeFragment.this.sportStep + " ,sportDistance = " + HomeFragment.this.sportDistance + " ,sportCalorie = " + HomeFragment.this.sportCalorie);
                }
                EventBus.getDefault().post(new RealDataResponse(i2, hashMap));
            }
        });
        this.isResume = true;
        this.handler.postDelayed(this.checkDeviceIcon, 2000L);
        this.mToDay = TimeStampUtils.getToDay();
        initUnit();
        freshConnectIcon();
        getDbWatchesData();
        if (Constant.isMymon()) {
            setPDNumber();
        }
        if (YCBTClient.connectState() == 10 && !YCBTClient.isForceOta()) {
            if (!this.mainActivity.isNeedStopStep) {
                this.mainActivity.isNeedStopStep = true;
            } else {
                syncRealData(1);
            }
            upLoadEcgDiagnosis();
        }
        if (isChecked() && (PermissionUtil.isNotificationEnable(getActivity()) || Constant.isSmartHealth())) {
            this.tvPermission.setVisibility(8);
        } else {
            this.tvPermission.setVisibility(0);
        }
    }

    public void checkBattery() {
        int deviceBatteryValue = YCBTClient.getDeviceBatteryValue();
        boolean zBooleanValue = ((Boolean) SharedPreferencesUtils.get(getActivity(), Constant.IS_BATTERY_LOW_SHOW, false)).booleanValue();
        if (deviceBatteryValue > (Constant.isHealthWear() ? 15 : 20) || zBooleanValue || YCBTClient.connectState() != 10) {
            return;
        }
        SharedPreferencesUtils.put(getActivity(), Constant.IS_BATTERY_LOW_SHOW, true);
        showBattyDialog();
    }

    @Override // com.gyf.immersionbar.components.ImmersionFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        this.isResume = false;
        if (this.mainActivity.isNeedStopStep) {
            syncRealData(0);
        }
        this.handler.removeCallbacks(this.checkDeviceIcon);
    }

    protected boolean isChecked() {
        ArrayList<String[]> arrayList = new ArrayList();
        if (Constant.isHealthWear()) {
            arrayList.add(new String[]{Permission.READ_PHONE_STATE, Permission.CALL_PHONE, "android.permission.ANSWER_PHONE_CALLS"});
            arrayList.add(new String[]{Permission.READ_CALL_LOG});
            arrayList.add(new String[]{Permission.RECEIVE_SMS, Permission.READ_SMS});
            arrayList.add(new String[]{Permission.READ_CONTACTS});
            String[] strArr = new String[2];
            strArr[0] = Permission.ACCESS_FINE_LOCATION;
            strArr[1] = Build.VERSION.SDK_INT >= 29 ? "android.permission.ACCESS_BACKGROUND_LOCATION" : Permission.ACCESS_COARSE_LOCATION;
            arrayList.add(strArr);
        }
        if (Build.VERSION.SDK_INT >= 31) {
            arrayList.add(new String[]{"android.permission.BLUETOOTH_SCAN", "android.permission.BLUETOOTH_CONNECT"});
        }
        for (String[] strArr2 : arrayList) {
            if (getActivity() != null && !PermissionUtil.isPermission(getActivity(), strArr2)) {
                Log.e(this.TAG, "Permission:" + Arrays.toString(strArr2));
                return false;
            }
        }
        return true;
    }

    protected void freshConnectIcon() {
        int iConnectState = YCBTClient.connectState();
        if (iConnectState == 1 || iConnectState == 2 || iConnectState == 3 || iConnectState == 4) {
            if (Constant.isRing()) {
                setRightImage(R.mipmap.ic_device_ring_0);
                return;
            } else {
                setRightImage(R.mipmap.home_icon_btoff);
                return;
            }
        }
        if (iConnectState == 9 || iConnectState == 10) {
            if (Constant.isRing()) {
                setRightImage(R.mipmap.ic_device_ring_1);
                return;
            } else {
                setRightImage(R.mipmap.home_icon_bt);
                return;
            }
        }
        if (Constant.isRing()) {
            setRightImage(R.mipmap.ic_device_ring_0);
        } else {
            setRightImage(R.mipmap.home_icon_btoff_gr);
        }
    }

    @Override // com.yucheng.smarthealthpro.framework.BaseFragment
    protected void initView(View view) {
        this.tvPermission = getMViewBind().homePermissionTitle;
        this.tvOdoUnit = getMViewBind().tvOdoUnit;
        this.rlRunning = getMViewBind().rlRunning;
        this.tvKcal = getMViewBind().tvKcal;
        this.tvStep = getMViewBind().tvStep;
        this.tvOdo = getMViewBind().tvOdo;
        this.llRanking = getMViewBind().llRanking;
        this.llHealthy = getMViewBind().llHealthy;
        this.mRecyclerView = getMViewBind().recycleHome;
        this.llCompile = getMViewBind().llCompile;
        this.mStepView = getMViewBind().stepView;
        this.mSmartRefreshLayout = getMViewBind().srlHome;
        this.mSpinKitView = getMViewBind().rvDialog.spinKit;
        this.rvDialog = getMViewBind().rvDialog.rvDialog;
        this.llNoFunction = getMViewBind().llNoFunction;
        this.llRankingHealthy = getMViewBind().llRankingHealthy;
        this.tvSteps = getMViewBind().tvSteps;
        this.tvAnswer = getMViewBind().tvAnswer;
        this.lyAnswer = getMViewBind().lyAnswer;
        this.home_pair_permission_title = getMViewBind().homePairPermissionTitle;
        this.rlRunning.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.fragment.HomeFragment$$ExternalSyntheticLambda2
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view2) {
                this.f$0.onViewClicked(view2);
            }
        }));
        this.llRanking.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.fragment.HomeFragment$$ExternalSyntheticLambda2
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view2) {
                this.f$0.onViewClicked(view2);
            }
        }));
        this.llHealthy.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.fragment.HomeFragment$$ExternalSyntheticLambda2
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view2) {
                this.f$0.onViewClicked(view2);
            }
        }));
        this.llCompile.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.fragment.HomeFragment$$ExternalSyntheticLambda2
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view2) {
                this.f$0.onViewClicked(view2);
            }
        }));
        this.rvDialog.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.fragment.HomeFragment$$ExternalSyntheticLambda2
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view2) {
                this.f$0.onViewClicked(view2);
            }
        }));
        this.tvPermission.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.fragment.HomeFragment$$ExternalSyntheticLambda2
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view2) {
                this.f$0.onViewClicked(view2);
            }
        }));
        this.home_pair_permission_title.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.fragment.HomeFragment$$ExternalSyntheticLambda2
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view2) {
                this.f$0.onViewClicked(view2);
            }
        }));
        changeTitle(getString(R.string.home_title));
        showRightImage(R.mipmap.home_icon_btoff, new MyOnClickListenerImpl());
        this.mRecyclerView = (RecyclerView) view.findViewById(R.id.recycle_home);
        this.mAppImageMgr = new AppImageMgr(this.context);
        this.mHomeFunctionBean = new ArrayList();
        ClassicsHeader.REFRESH_HEADER_PULLING = getString(R.string.Pull_down_can_refresh);
        ClassicsHeader.REFRESH_HEADER_REFRESHING = getString(R.string.Refreshing);
        ClassicsHeader.REFRESH_HEADER_RELEASE = getString(R.string.Release_immediate_update);
        ClassicsHeader.REFRESH_HEADER_FINISH = getString(R.string.Refresh_completed);
        ClassicsHeader.REFRESH_HEADER_UPDATE = getString(R.string.Last_updated);
        ClassicsFooter.REFRESH_FOOTER_LOADING = getString(R.string.loading_more);
        ClassicsFooter.REFRESH_FOOTER_FINISH = getString(R.string.load_more_end);
        this.mSmartRefreshLayout.setEnableLoadMore(false);
        this.mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListenerImpl());
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("syncData");
        this.mBroadcastReceiver = new MBroadcastReceiver();
        ActivityCompat.registerReceiver(requireActivity(), this.mBroadcastReceiver, intentFilter, 2);
        if (Constant.isTechFeel()) {
            this.llRankingHealthy.setVisibility(8);
        }
        this.lyAnswer.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.home.fragment.HomeFragment.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                HomeFragment.this.startActivity(new Intent(HomeFragment.this.requireActivity(), (Class<?>) WebViewActivity.class).putExtra("title", HomeFragment.this.tvAnswer.getText()).putExtra("url", "https://staticpage.ycaviation.com/app/answer/index.html#/home?lang=" + MultiLanguageUtils.getLanguageStr(HomeFragment.this.context)));
            }
        });
        if (Constant.isHealthWear() || Constant.isSmartHealth()) {
            this.lyAnswer.setVisibility(0);
        } else {
            this.lyAnswer.setVisibility(8);
        }
        initViewModel();
    }

    @Override // com.yucheng.smarthealthpro.framework.BaseFragment
    protected void initData(Context mContext) {
        this.mToDay = TimeStampUtils.getToDay();
        initUnit();
        setRecycleView();
        initRealData();
        getDbWatchesData();
    }

    protected void initUnit() {
        String str = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.UNIT, "");
        if (str != null && str.equals(Constant.SpConstValue.INCH)) {
            this.mUnit = 1;
            this.tvOdoUnit.setText(getString(R.string.dis_inch_unit));
        } else {
            this.mUnit = 0;
            this.tvOdoUnit.setText(getString(R.string.dis_km_unit));
        }
        String str2 = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.TEMP_UNIT, "");
        if (str2 != null && str2.equals(Constant.SpConstValue.TEMP_INCH)) {
            this.mTempUnit = 1;
        } else {
            this.mTempUnit = 0;
        }
        String str3 = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.BLOOD_SUGAR_AND_BLOOD_FAT_UNIT, "");
        if (str3 != null && str3.equals("mg/dL")) {
            this.mBloodSugarUnit = 1;
        } else {
            this.mBloodSugarUnit = 0;
        }
        this.mUricAcidUnit = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.URIC_ACID_UNIT, getString(R.string.uric_acid_unit_1));
        this.tvSteps.setText(R.string.step_number);
    }

    protected void initRealData() {
        boolean zCheckIsToday = AppDateMgr.checkIsToday(((Long) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.HOME_STEP_TIME, 0L)).longValue());
        Log.d("ltf", "isToday=" + zCheckIsToday);
        if (zCheckIsToday) {
            this.sportStep = ((Integer) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.HOME_STEP, 0)).intValue();
            this.sportDistance = ((Integer) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.HOME_KM, 0)).intValue();
            this.sportCalorie = ((Integer) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.HOME_KCAL, 0)).intValue();
        } else {
            this.sportStep = 0;
            this.sportDistance = 0;
            this.sportCalorie = 0;
        }
        updateRealData();
    }

    protected void startAnimator(int stepNum) {
        ValueAnimator valueAnimatorOfFloat = ObjectAnimator.ofFloat(0.0f, stepNum);
        valueAnimatorOfFloat.setDuration(1000L);
        valueAnimatorOfFloat.setInterpolator(new DecelerateInterpolator());
        valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.yucheng.smarthealthpro.home.fragment.HomeFragment.5
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator animation) {
                try {
                    float fFloatValue = ((Float) animation.getAnimatedValue()).floatValue();
                    if (HomeFragment.this.mStepView != null) {
                        HomeFragment.this.mStepView.setCurrentStep((int) fFloatValue);
                    }
                } catch (Exception e2) {
                    CrashReport.postCatchedException(e2);
                }
            }
        });
        valueAnimatorOfFloat.start();
    }

    protected void addDefaultData() {
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASECGREALUPLOAD)) {
            this.mHomeFunctionBean.add(new HomeFunctionBean("", "", getString(R.string.home_ecg_title), "心电", this.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_ecg), true));
        }
        if (Constant.isMymon()) {
            this.mHomeFunctionBean.add(new HomeFunctionBean("--", "steps", getString(R.string.sport_title), "运动", this.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_sport), true));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASSLEEP)) {
            this.mHomeFunctionBean.add(new HomeFunctionBean("--", "min", getString(R.string.home_sleep_title), "睡眠", this.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_sleep), true));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASHEARTRATE)) {
            this.mHomeFunctionBean.add(new HomeFunctionBean("--", "bpm", getString(R.string.function_heart), "心率", this.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_hr), true));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASBLOOD)) {
            this.mHomeFunctionBean.add(new HomeFunctionBean("--/--", "mmHg", getString(R.string.home_blood_pressure_title), "血压", this.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_rr), true));
        }
        Logger.w("精准血糖：" + YCBTClient.isSupportFunction(Constants.FunctionConstant.IS_HAS_PRECISION_BLOOD_GLUCOSE), new Object[0]);
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASBLOODSUGAR) || YCBTClient.isSupportFunction(Constants.FunctionConstant.IS_HAS_PRECISION_BLOOD_GLUCOSE)) {
            this.mHomeFunctionBean.add(new HomeFunctionBean("--", getString(getString(R.string.blood_sugar_unit_2).equals((String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.BLOOD_SUGAR_AND_BLOOD_FAT_UNIT, "")) ? R.string.blood_sugar_unit_2 : R.string.blood_sugar_unit_1), getString(R.string.home_blood_sugar_title), "血糖", this.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_blood_sugar), true));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASBLOODOXYGEN)) {
            this.mHomeFunctionBean.add(new HomeFunctionBean("--", "%", getString(R.string.home_blood_oxygen_title), "血氧", this.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_bo), true));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASRESPIRATORYRATE)) {
            this.mHomeFunctionBean.add(new HomeFunctionBean("--", "rpm", getString(R.string.home_respiratory_rate_title), "呼吸率", this.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_rr), true));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASTEMP)) {
            this.mHomeFunctionBean.add(new HomeFunctionBean("--", Constant.SpConstValue.TEMP_INCH.equals((String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.TEMP_UNIT, "")) ? Constant.SpConstValue.TEMP_INCH : Constant.SpConstValue.TEMP_ISO, getString(R.string.function_temp), "温度", this.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_tp), true));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASIMPRECISEBLOODFAT)) {
            this.mHomeFunctionBean.add(new HomeFunctionBean("--", (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.BLOOD_SUGAR_AND_BLOOD_FAT_UNIT, ""), getString(R.string.blood_fat), "血脂", this.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_blood_fat), true));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASURICACIDMEASUREMENT) || YCBTClient.isSupportFunction(Constants.FunctionConstant.IS_HAS_PRECISION_URIC_ACID)) {
            this.mHomeFunctionBean.add(new HomeFunctionBean("--", (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.URIC_ACID_UNIT, getString(R.string.uric_acid_unit_1)), getString(R.string.uric_acid), "尿酸", this.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_uric_acid), true));
        }
        Log.w(this.TAG, "支持血酮: " + YCBTClient.isSupportFunction(Constants.FunctionConstant.IS_HAS_PRECISION_BLOOD_KETONE));
        if ((Constant.isHealthWear() || Constant.isSmartHealth()) && YCBTClient.isSupportFunction(Constants.FunctionConstant.IS_HAS_PRECISION_BLOOD_KETONE)) {
            this.mHomeFunctionBean.add(new HomeFunctionBean("--", "", getString(R.string.blood_ketones), "血酮", this.mAppImageMgr.getBitmap(R.mipmap.home_blood_ketone), true));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.IS_HAS_PHYSIOTHERAPY)) {
            this.mHomeFunctionBean.add(new HomeFunctionBean("--", "", getString(R.string.physiotherapy), "理疗", this.mAppImageMgr.getBitmap(R.mipmap.home_physiotherapy), true));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASHRV)) {
            this.mHomeFunctionBean.add(new HomeFunctionBean("--", "ms", getString(R.string.hrv_unit), "HRV", this.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_hrv), true));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.IS_HAS_PRESSURE)) {
            Log.d(this.TAG, "addDefaultData: 压力");
            this.mHomeFunctionBean.add(new HomeFunctionBean("--", "", getString(R.string.pressure_str), "压力", this.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_pressure), true));
        }
        Log.d(this.TAG, "addDefaultData: size: " + this.mHomeFunctionBean.size());
        for (int i2 = 0; i2 < this.mHomeFunctionBean.size(); i2++) {
            SharedPreferencesUtils.put(this.context, Constant.SpConstKey.FUNCTION + i2, this.mHomeFunctionBean.get(i2).getFunction());
        }
        SharedPreferencesUtils.put(this.context, Constant.SpConstKey.M_HOME_FUNCTION_BEAN_SIZE, Integer.valueOf(this.mHomeFunctionBean.size()));
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    protected void setLatestDbData() {
        int i2;
        boolean z;
        boolean z2;
        Boolean bool;
        int i3;
        HomeFragment homeFragment;
        HomeFragment homeFragment2;
        HomeFragment homeFragment3;
        HomeFragment homeFragment4;
        char c2;
        HomeFragment homeFragment5 = this;
        MLog.INSTANCE.d("addDefaultData");
        boolean z3 = false;
        boolean z4 = true;
        Boolean bool2 = true;
        if (YCBTClient.connectState() != 10) {
            HomeViewModel homeViewModel = homeFragment5.mViewModel;
            String str = homeFragment5.mToDay;
            homeViewModel.getSleepData(str, YearToDayListUtils.getPastStringArray(str, 1).get(0));
            homeFragment5.mViewModel.getHeartRateDayData(homeFragment5.mToDay);
            return;
        }
        int iIntValue = ((Integer) SharedPreferencesUtils.get(homeFragment5.context, Constant.SpConstKey.M_HOME_FUNCTION_BEAN_SIZE, 0)).intValue();
        MLog.INSTANCE.d("setLatestDbData: size: " + iIntValue);
        homeFragment5.mFunctionSortMap.clear();
        int i4 = 0;
        while (i4 < iIntValue) {
            String str2 = (String) SharedPreferencesUtils.get(homeFragment5.context, Constant.SpConstKey.FUNCTION + i4, "");
            if (str2 != null) {
                homeFragment5.mFunctionSortMap.put(str2, Integer.valueOf(i4));
                str2.hashCode();
                i2 = iIntValue;
                i3 = i4;
                bool = bool2;
                char c3 = 65535;
                switch (str2.hashCode()) {
                    case 71820:
                        if (str2.equals("HRV")) {
                            c3 = 0;
                            break;
                        }
                        break;
                    case 684144:
                        if (str2.equals("压力")) {
                            c3 = 1;
                            break;
                        }
                        break;
                    case 769305:
                        if (str2.equals("尿酸")) {
                            c2 = 2;
                            c3 = c2;
                            break;
                        }
                        break;
                    case 789540:
                        if (str2.equals("心率")) {
                            c2 = 3;
                            c3 = c2;
                            break;
                        }
                        break;
                    case 789970:
                        if (str2.equals("心电")) {
                            c2 = 4;
                            c3 = c2;
                            break;
                        }
                        break;
                    case 898461:
                        if (str2.equals("温度")) {
                            c2 = 5;
                            c3 = c2;
                            break;
                        }
                        break;
                    case 950865:
                        if (str2.equals("理疗")) {
                            c2 = 6;
                            c3 = c2;
                            break;
                        }
                        break;
                    case 977887:
                        if (str2.equals("睡眠")) {
                            c2 = 7;
                            c3 = c2;
                            break;
                        }
                        break;
                    case 1102667:
                        if (str2.equals("血压")) {
                            c2 = '\b';
                            c3 = c2;
                            break;
                        }
                        break;
                    case 1108967:
                        if (str2.equals("血氧")) {
                            c2 = '\t';
                            c3 = c2;
                            break;
                        }
                        break;
                    case 1113238:
                        if (str2.equals("血糖")) {
                            c3 = '\n';
                            break;
                        }
                        break;
                    case 1114306:
                        if (str2.equals("血脂")) {
                            c2 = 11;
                            c3 = c2;
                            break;
                        }
                        break;
                    case 1118510:
                        if (str2.equals("血酮")) {
                            c2 = '\f';
                            c3 = c2;
                            break;
                        }
                        break;
                    case 1162456:
                        if (str2.equals("运动")) {
                            c2 = '\r';
                            c3 = c2;
                            break;
                        }
                        break;
                    case 21482443:
                        if (str2.equals("呼吸率")) {
                            c2 = 14;
                            c3 = c2;
                            break;
                        }
                        break;
                }
                switch (c3) {
                    case 0:
                        z2 = true;
                        z = false;
                        homeFragment = this;
                        homeFragment.addCacheData("HRV", new HomeFunctionBean("--", "ms", homeFragment.getString(R.string.hrv_unit), "HRV", homeFragment.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_hrv), bool));
                        homeFragment.mViewModel.getHrvDayData(homeFragment.mToDay);
                        break;
                    case 1:
                        z2 = true;
                        z = false;
                        homeFragment = this;
                        homeFragment.addCacheData("压力", new HomeFunctionBean("--", "", homeFragment.getString(R.string.pressure_str), "压力", homeFragment.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_pressure), bool));
                        homeFragment.mViewModel.getPressureDayData(homeFragment.mToDay);
                        break;
                    case 2:
                        z2 = true;
                        z = false;
                        homeFragment2 = this;
                        homeFragment2.addCacheData("尿酸", new HomeFunctionBean("--", homeFragment2.mUricAcidUnit, homeFragment2.getString(R.string.uric_acid), "尿酸", homeFragment2.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_uric_acid), bool));
                        homeFragment2.mViewModel.getUricAcidDayData(homeFragment2.mToDay);
                        homeFragment = homeFragment2;
                        break;
                    case 3:
                        z2 = true;
                        z = false;
                        homeFragment2 = this;
                        homeFragment2.addCacheData("心率", new HomeFunctionBean("--", "bpm", homeFragment2.getString(R.string.function_heart), "心率", homeFragment2.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_hr), bool));
                        homeFragment2.mViewModel.getHeartRateDayData(homeFragment2.mToDay);
                        homeFragment = homeFragment2;
                        break;
                    case 4:
                        z2 = true;
                        z = false;
                        homeFragment2 = this;
                        homeFragment2.mHomeFunctionBean.add(new HomeFunctionBean("", "", homeFragment2.getString(R.string.home_ecg_title), "心电", homeFragment2.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_ecg), bool));
                        homeFragment = homeFragment2;
                        break;
                    case 5:
                        z = false;
                        homeFragment2 = this;
                        String string = homeFragment2.getString(R.string.temp_c_unit);
                        z2 = true;
                        if (homeFragment2.mBloodSugarUnit == 1) {
                            string = homeFragment2.getString(R.string.temp_f_unit);
                        }
                        homeFragment2.addCacheData("温度", new HomeFunctionBean("--", string, homeFragment2.getString(R.string.function_temp), "温度", homeFragment2.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_tp), bool));
                        homeFragment2.mViewModel.getTemperatureDayData(homeFragment2.mToDay);
                        homeFragment = homeFragment2;
                        break;
                    case 6:
                        z = false;
                        homeFragment3 = this;
                        homeFragment3.addCacheData("理疗", new HomeFunctionBean("--", "", homeFragment3.getString(R.string.physiotherapy), "理疗", homeFragment3.mAppImageMgr.getBitmap(R.mipmap.ic_physiotherapy), bool));
                        homeFragment3.mViewModel.getPhysiotherapyDayData(homeFragment3.mToDay);
                        homeFragment = homeFragment3;
                        z2 = true;
                        break;
                    case 7:
                        homeFragment3 = this;
                        homeFragment3.addCacheData("睡眠", new HomeFunctionBean("00", "min", homeFragment3.getString(R.string.home_sleep_title), "睡眠", homeFragment3.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_sleep), bool));
                        HomeViewModel homeViewModel2 = homeFragment3.mViewModel;
                        String str3 = homeFragment3.mToDay;
                        z = false;
                        homeViewModel2.getSleepData(str3, YearToDayListUtils.getPastStringArray(str3, 1).get(0));
                        homeFragment = homeFragment3;
                        z2 = true;
                        break;
                    case '\b':
                        homeFragment4 = this;
                        homeFragment4.addCacheData("血压", new HomeFunctionBean("--/--", "mmHg", homeFragment4.getString(R.string.home_blood_pressure_title), "血压", homeFragment4.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_bp), bool));
                        homeFragment4.mViewModel.getBloodPressureDayData(homeFragment4.mToDay);
                        homeFragment = homeFragment4;
                        z2 = true;
                        z = false;
                        break;
                    case '\t':
                        homeFragment4 = this;
                        homeFragment4.addCacheData("血氧", new HomeFunctionBean("--", "%", homeFragment4.getString(R.string.home_blood_oxygen_title), "血氧", homeFragment4.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_bo), bool));
                        homeFragment4.mViewModel.getBloodOxygenDayData(homeFragment4.mToDay);
                        homeFragment = homeFragment4;
                        z2 = true;
                        z = false;
                        break;
                    case '\n':
                        homeFragment4 = this;
                        String string2 = homeFragment4.getString(R.string.blood_sugar_unit_1);
                        if (homeFragment4.mBloodSugarUnit == 1) {
                            string2 = homeFragment4.getString(R.string.blood_sugar_unit_2);
                        }
                        homeFragment4.addCacheData("血糖", new HomeFunctionBean("--", string2, homeFragment4.getString(R.string.home_blood_sugar_title), "血糖", homeFragment4.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_blood_sugar), bool));
                        homeFragment4.mViewModel.getBloodSugarDayData(homeFragment4.mToDay);
                        homeFragment = homeFragment4;
                        z2 = true;
                        z = false;
                        break;
                    case 11:
                        homeFragment4 = this;
                        homeFragment4.addCacheData("血脂", new HomeFunctionBean("--", (String) SharedPreferencesUtils.get(homeFragment4.context, Constant.SpConstKey.BLOOD_SUGAR_AND_BLOOD_FAT_UNIT, homeFragment4.getString(R.string.blood_sugar_unit_1)), homeFragment4.getString(R.string.blood_fat), "血脂", homeFragment4.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_blood_fat), bool));
                        homeFragment4.mViewModel.getBloodLipidsDayData(homeFragment4.mToDay);
                        homeFragment = homeFragment4;
                        z2 = true;
                        z = false;
                        break;
                    case '\f':
                        homeFragment4 = this;
                        homeFragment4.addCacheData("血酮", new HomeFunctionBean("--", "", homeFragment4.getString(R.string.blood_ketones), "血酮", homeFragment4.mAppImageMgr.getBitmap(R.mipmap.home_blood_ketone), bool));
                        homeFragment4.mViewModel.getBloodKetonesDayData(homeFragment4.mToDay);
                        homeFragment = homeFragment4;
                        z2 = true;
                        z = false;
                        break;
                    case '\r':
                        homeFragment4 = this;
                        homeFragment4.addCacheData("运动", new HomeFunctionBean("--", "steps", homeFragment4.getString(R.string.sport_title), "运动", homeFragment4.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_sport), bool));
                        addSportsData();
                        homeFragment = homeFragment4;
                        z2 = true;
                        z = false;
                        break;
                    case 14:
                        homeFragment4 = this;
                        homeFragment4.addCacheData("呼吸率", new HomeFunctionBean("--", "rpm", homeFragment4.getString(R.string.home_respiratory_rate_title), "呼吸率", homeFragment4.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_rr), bool));
                        homeFragment4.mViewModel.getRespiratoryRateDayData(homeFragment4.mToDay);
                        homeFragment = homeFragment4;
                        z2 = true;
                        z = false;
                        break;
                    default:
                        z2 = true;
                        z = false;
                        homeFragment = this;
                        break;
                }
            } else {
                i2 = iIntValue;
                z = z3;
                z2 = z4;
                bool = bool2;
                i3 = i4;
                homeFragment = homeFragment5;
            }
            i4 = i3 + 1;
            homeFragment5 = homeFragment;
            z4 = z2;
            z3 = z;
            iIntValue = i2;
            bool2 = bool;
        }
    }

    private void addCacheData(String functionName, HomeFunctionBean defaultFunctionBean) {
        HealthDataFilterKt.removeExistsFunction(this.mHomeFunctionBean, functionName);
        HomeFunctionBean homeFunctionBeanFindFunction = HealthDataFilterKt.findFunction(this.mHomeFunctionBeanCache, functionName);
        if (homeFunctionBeanFindFunction != null) {
            this.mHomeFunctionBean.add(homeFunctionBeanFindFunction);
        } else {
            this.mHomeFunctionBean.add(defaultFunctionBean);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addHRVData(List<HealthMetric> hrvData) {
        List<HealthMetric> listFilterHeartRateVariabilityData = HealthDataFilterKt.filterHeartRateVariabilityData(hrvData);
        HealthDataFilterKt.removeExistsFunction(this.mHomeFunctionBean, "HRV");
        if (!listFilterHeartRateVariabilityData.isEmpty()) {
            this.mHomeFunctionBean.add(new HomeFunctionBean(listFilterHeartRateVariabilityData.get(0).getHeartRateVariability() + "", "ms", getString(R.string.hrv_unit), "HRV", this.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_hrv), true));
        } else {
            this.mHomeFunctionBean.add(new HomeFunctionBean("--", "ms", getString(R.string.hrv_unit), "HRV", this.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_hrv), true));
        }
        HealthDataFilterKt.resortFunction(this.mHomeFunctionBean, this.mFunctionSortMap);
        this.mHomeFunctionAdapter.setList(this.mHomeFunctionBean);
        MLog.INSTANCE.d("addHRVData: " + this.mHomeFunctionBean.size());
    }

    protected void addPressureData(List<BodyData> pressureData) {
        HealthDataFilterKt.removeExistsFunction(this.mHomeFunctionBean, "压力");
        if (pressureData.size() > 0) {
            this.mHomeFunctionBean.add(new HomeFunctionBean(pressureData.get(0).getCompositePressure() + "", "", getString(R.string.pressure_str), "压力", this.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_pressure), true));
        } else {
            this.mHomeFunctionBean.add(new HomeFunctionBean("--", "", getString(R.string.pressure_str), "压力", this.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_pressure), true));
        }
        HealthDataFilterKt.resortFunction(this.mHomeFunctionBean, this.mFunctionSortMap);
        this.mHomeFunctionAdapter.setList(this.mHomeFunctionBean);
        MLog.INSTANCE.d("addPressureData: " + this.mHomeFunctionBean.size());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addLatestBloodPressureData(List<BloodPressure> bpData) {
        List<BloodPressure> listFilterBloodPressureData = HealthDataFilterKt.filterBloodPressureData(bpData);
        HealthDataFilterKt.removeExistsFunction(this.mHomeFunctionBean, "血压");
        if (!listFilterBloodPressureData.isEmpty()) {
            this.mHomeFunctionBean.add(new HomeFunctionBean(listFilterBloodPressureData.get(0).getSystolicBloodPressure() + "/" + listFilterBloodPressureData.get(0).getDiastolicBloodPressure(), "mmHg", getString(R.string.home_blood_pressure_title), "血压", this.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_bp), true));
        } else {
            this.mHomeFunctionBean.add(new HomeFunctionBean("--/--", "mmHg", getString(R.string.home_blood_pressure_title), "血压", this.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_bp), true));
        }
        HealthDataFilterKt.resortFunction(this.mHomeFunctionBean, this.mFunctionSortMap);
        this.mHomeFunctionAdapter.setList(this.mHomeFunctionBean);
        MLog.INSTANCE.d("addLatestBloodPressureData: " + this.mHomeFunctionBean.size());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addLatestHeartRateData(List<HeartRate> heartRateData) {
        List<HeartRate> listFilterHeartRateData = HealthDataFilterKt.filterHeartRateData(heartRateData);
        HealthDataFilterKt.removeExistsFunction(this.mHomeFunctionBean, "心率");
        if (listFilterHeartRateData.size() > 0) {
            this.mHomeFunctionBean.add(new HomeFunctionBean(listFilterHeartRateData.get(0).getHeartRate() + "", "bpm", getString(R.string.function_heart), "心率", this.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_hr), true));
        } else {
            this.mHomeFunctionBean.add(new HomeFunctionBean("--", "bpm", getString(R.string.function_heart), "心率", this.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_hr), true));
        }
        HealthDataFilterKt.resortFunction(this.mHomeFunctionBean, this.mFunctionSortMap);
        this.mHomeFunctionAdapter.setList(this.mHomeFunctionBean);
        MLog.INSTANCE.d("addLatestHeartRateData: " + this.mHomeFunctionBean.size());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addLatestRespiratoryRateData(List<HealthMetric> respiratoryRateData) {
        List<HealthMetric> listFilterRespiratoryRateData = HealthDataFilterKt.filterRespiratoryRateData(respiratoryRateData);
        HealthDataFilterKt.removeExistsFunction(this.mHomeFunctionBean, "呼吸率");
        if (!listFilterRespiratoryRateData.isEmpty()) {
            this.mHomeFunctionBean.add(new HomeFunctionBean(listFilterRespiratoryRateData.get(0).getRespiratoryRate() + "", "rpm", getString(R.string.home_respiratory_rate_title), "呼吸率", this.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_rr), true));
        } else {
            this.mHomeFunctionBean.add(new HomeFunctionBean("--", "rpm", getString(R.string.home_respiratory_rate_title), "呼吸率", this.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_rr), true));
        }
        HealthDataFilterKt.resortFunction(this.mHomeFunctionBean, this.mFunctionSortMap);
        this.mHomeFunctionAdapter.setList(this.mHomeFunctionBean);
        MLog.INSTANCE.d("addLatestRespiratoryRateData: " + this.mHomeFunctionBean.size());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addLatestSpo2Data(List<HealthMetric> bloodOxygenData) {
        List<HealthMetric> listFilterBloodOxygenData = HealthDataFilterKt.filterBloodOxygenData(bloodOxygenData);
        HealthDataFilterKt.removeExistsFunction(this.mHomeFunctionBean, "血氧");
        if (!listFilterBloodOxygenData.isEmpty()) {
            this.mHomeFunctionBean.add(new HomeFunctionBean(listFilterBloodOxygenData.get(0).getBloodOxygenLevel() + "", "%", getString(R.string.home_blood_oxygen_title), "血氧", this.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_bo), true));
        } else {
            this.mHomeFunctionBean.add(new HomeFunctionBean("--", "%", getString(R.string.home_blood_oxygen_title), "血氧", this.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_bo), true));
        }
        HealthDataFilterKt.resortFunction(this.mHomeFunctionBean, this.mFunctionSortMap);
        this.mHomeFunctionAdapter.setList(this.mHomeFunctionBean);
        MLog.INSTANCE.d("addLatestSpo2Data: " + this.mHomeFunctionBean.size());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addLatestSleepData(List<Sleep> mSleepDb, List<Sleep> mSleepDb2) {
        int lightSleepTotal;
        int deepSleepTotal;
        int remTime;
        MLog.INSTANCE.d("sleep section first: " + mSleepDb);
        MLog.INSTANCE.d("sleep section second: " + mSleepDb2);
        if (mSleepDb2 != null) {
            lightSleepTotal = 0;
            deepSleepTotal = 0;
            remTime = 0;
            for (int i2 = 0; i2 < mSleepDb2.size(); i2++) {
                if (SleepUtil.isTodaySleep(mSleepDb2.get(i2), 1)) {
                    if (mSleepDb2.get(i2).getDeepSleepCount() == 65535) {
                        SleepSummary sleepSummarySleepTimeSummary = HealthDataFilterKt.sleepTimeSummary(mSleepDb2.get(i2).getSleepItems());
                        lightSleepTotal += sleepSummarySleepTimeSummary.getLightSleepTime();
                        deepSleepTotal += sleepSummarySleepTimeSummary.getDeepSleepTime();
                        remTime += sleepSummarySleepTimeSummary.getRemTime();
                    } else {
                        lightSleepTotal += mSleepDb2.get(i2).getLightSleepTotal();
                        deepSleepTotal += mSleepDb2.get(i2).getDeepSleepTotal();
                    }
                }
            }
        } else {
            lightSleepTotal = 0;
            deepSleepTotal = 0;
            remTime = 0;
        }
        if (mSleepDb != null) {
            for (int i3 = 0; i3 < mSleepDb.size(); i3++) {
                if (SleepUtil.isTodaySleep(mSleepDb.get(i3), 0)) {
                    if (mSleepDb.get(i3).getDeepSleepCount() == 65535) {
                        SleepSummary sleepSummarySleepTimeSummary2 = HealthDataFilterKt.sleepTimeSummary(mSleepDb.get(i3).getSleepItems());
                        lightSleepTotal += sleepSummarySleepTimeSummary2.getLightSleepTime();
                        deepSleepTotal += sleepSummarySleepTimeSummary2.getDeepSleepTime();
                        remTime += sleepSummarySleepTimeSummary2.getRemTime();
                    } else {
                        lightSleepTotal += mSleepDb.get(i3).getLightSleepTotal();
                        deepSleepTotal += mSleepDb.get(i3).getDeepSleepTotal();
                    }
                }
            }
        }
        int i4 = ((lightSleepTotal / 60) * 60) + ((deepSleepTotal / 60) * 60) + ((remTime / 60) * 60);
        if (i4 > 57600) {
            i4 = 57600;
        }
        HealthDataFilterKt.removeExistsFunction(this.mHomeFunctionBean, "睡眠");
        MLog.INSTANCE.d("addLatestSleepData: before " + this.mHomeFunctionBean.size());
        if (i4 > 0) {
            this.mHomeFunctionBean.add(new HomeFunctionBean(i4 + "", "min", getString(R.string.home_sleep_title), "睡眠", this.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_sleep), true));
        } else {
            this.mHomeFunctionBean.add(new HomeFunctionBean("00", "min", getString(R.string.home_sleep_title), "睡眠", this.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_sleep), true));
        }
        HealthDataFilterKt.resortFunction(this.mHomeFunctionBean, this.mFunctionSortMap);
        this.mHomeFunctionAdapter.setList(this.mHomeFunctionBean);
        MLog.INSTANCE.d("addLatestSleepData: " + this.mHomeFunctionBean.size());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addLatestTempData(List<HealthMetric> temperatureData) {
        List<HealthMetric> listFilterTemperatureData = HealthDataFilterKt.filterTemperatureData(temperatureData);
        String strKeep1NoZero = "--";
        String strValueOf = !listFilterTemperatureData.isEmpty() ? String.valueOf(listFilterTemperatureData.get(0).getTemperature()) : "--";
        HealthDataFilterKt.removeExistsFunction(this.mHomeFunctionBean, "温度");
        if (this.mTempUnit == 0) {
            this.mHomeFunctionBean.add(new HomeFunctionBean(strValueOf, Constant.SpConstValue.TEMP_ISO, getString(R.string.function_temp), "温度", this.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_tp), true));
        } else {
            try {
                List<HomeFunctionBean> list = this.mHomeFunctionBean;
                if (!"--".equals(strValueOf)) {
                    strKeep1NoZero = FormatUtil.keep1NoZero((Float.parseFloat(strValueOf) * 1.8f) + 32.0f);
                }
                list.add(new HomeFunctionBean(strKeep1NoZero, Constant.SpConstValue.TEMP_INCH, getString(R.string.function_temp), "温度", this.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_tp), true));
            } catch (Exception e2) {
                CrashReport.postCatchedException(e2);
                e2.printStackTrace();
                this.mHomeFunctionBean.add(new HomeFunctionBean("--", Constant.SpConstValue.TEMP_INCH, getString(R.string.function_temp), "温度", this.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_tp), true));
            }
        }
        HealthDataFilterKt.resortFunction(this.mHomeFunctionBean, this.mFunctionSortMap);
        this.mHomeFunctionAdapter.setList(this.mHomeFunctionBean);
        MLog.INSTANCE.d("addLatestTempData: " + this.mHomeFunctionBean.size());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addLatestBloodSugarData(List<HealthMetric> bloodSugarData) {
        String strKeep1NoZero;
        List<HealthMetric> listFilterBloodSugarData = HealthDataFilterKt.filterBloodSugarData(bloodSugarData);
        String string = getString(R.string.blood_sugar_unit_1);
        if (listFilterBloodSugarData.isEmpty()) {
            strKeep1NoZero = "--";
        } else if (this.mBloodSugarUnit == 0) {
            strKeep1NoZero = FormatUtil.keep1NoZero(listFilterBloodSugarData.get(0).getBloodSugarLevel() / 10.0f);
        } else {
            strKeep1NoZero = FormatUtil.keep1NoZero((listFilterBloodSugarData.get(0).getBloodSugarLevel() * 18) / 10.0f);
            string = getString(R.string.blood_sugar_unit_2);
        }
        HealthDataFilterKt.removeExistsFunction(this.mHomeFunctionBean, "血糖");
        this.mHomeFunctionBean.add(new HomeFunctionBean(strKeep1NoZero, string, getString(R.string.home_blood_sugar_title), "血糖", this.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_blood_sugar), true));
        HealthDataFilterKt.resortFunction(this.mHomeFunctionBean, this.mFunctionSortMap);
        this.mHomeFunctionAdapter.setList(this.mHomeFunctionBean);
        MLog.INSTANCE.d("addLatestBloodSugarData: " + this.mHomeFunctionBean.size());
    }

    private void addSportsData() {
        HealthDataFilterKt.removeExistsFunction(this.mHomeFunctionBean, "运动");
        if (this.sportStep > 0) {
            this.mHomeFunctionBean.add(new HomeFunctionBean(this.sportStep + "", "steps", getString(R.string.sport_title), "运动", this.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_sport), true));
        } else {
            this.mHomeFunctionBean.add(new HomeFunctionBean("--", "steps", getString(R.string.sport_title), "运动", this.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_sport), true));
        }
        HealthDataFilterKt.resortFunction(this.mHomeFunctionBean, this.mFunctionSortMap);
        this.mHomeFunctionAdapter.setList(this.mHomeFunctionBean);
        MLog.INSTANCE.d("addSportsData: " + this.mHomeFunctionBean.size());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addBloodFatsData(List<BloodLipids> bloodLipidsData) {
        String strBloodFatMmol2Mg;
        String str;
        List<BloodLipids> listFilterBloodLipidsData = HealthDataFilterKt.filterBloodLipidsData(bloodLipidsData);
        String str2 = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.BLOOD_SUGAR_AND_BLOOD_FAT_UNIT, getString(R.string.blood_sugar_unit_1));
        if (listFilterBloodLipidsData.isEmpty()) {
            strBloodFatMmol2Mg = "--";
        } else {
            float cholesterol = listFilterBloodLipidsData.get(0).getCholesterol();
            String strKeep2 = FormatUtil.keep2(cholesterol);
            if ("mg/dL".equals(str2)) {
                strBloodFatMmol2Mg = TransUtils.bloodFatMmol2Mg(cholesterol);
            } else {
                str = strKeep2;
                HealthDataFilterKt.removeExistsFunction(this.mHomeFunctionBean, "血脂");
                this.mHomeFunctionBean.add(new HomeFunctionBean(str, str2, getString(R.string.blood_fat), "血脂", this.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_blood_fat), true));
                HealthDataFilterKt.resortFunction(this.mHomeFunctionBean, this.mFunctionSortMap);
                this.mHomeFunctionAdapter.setList(this.mHomeFunctionBean);
                MLog.INSTANCE.d("addBloodFatsData: " + this.mHomeFunctionBean.size());
            }
        }
        str = strBloodFatMmol2Mg;
        HealthDataFilterKt.removeExistsFunction(this.mHomeFunctionBean, "血脂");
        this.mHomeFunctionBean.add(new HomeFunctionBean(str, str2, getString(R.string.blood_fat), "血脂", this.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_blood_fat), true));
        HealthDataFilterKt.resortFunction(this.mHomeFunctionBean, this.mFunctionSortMap);
        this.mHomeFunctionAdapter.setList(this.mHomeFunctionBean);
        MLog.INSTANCE.d("addBloodFatsData: " + this.mHomeFunctionBean.size());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addUricAcidsData(List<UricAcid> uricAcidData) {
        String strUricAcidUmol2Mg;
        List<UricAcid> listFilterUricAcidData = HealthDataFilterKt.filterUricAcidData(uricAcidData);
        if (listFilterUricAcidData.isEmpty()) {
            strUricAcidUmol2Mg = "--";
        } else {
            int uricAcid = listFilterUricAcidData.get(0).getUricAcid();
            if ("mg/dL".equals(this.mUricAcidUnit)) {
                strUricAcidUmol2Mg = TransUtils.uricAcidUmol2Mg(uricAcid);
            } else {
                strUricAcidUmol2Mg = uricAcid + "";
            }
        }
        HealthDataFilterKt.removeExistsFunction(this.mHomeFunctionBean, "尿酸");
        this.mHomeFunctionBean.add(new HomeFunctionBean(strUricAcidUmol2Mg + "", this.mUricAcidUnit, getString(R.string.uric_acid), "尿酸", this.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_uric_acid), true));
        HealthDataFilterKt.resortFunction(this.mHomeFunctionBean, this.mFunctionSortMap);
        this.mHomeFunctionAdapter.setList(this.mHomeFunctionBean);
        MLog.INSTANCE.d("addUricAcidsData: " + this.mHomeFunctionBean.size());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addBloodKetonesData(List<BloodKetones> bloodKetonesData) {
        String strUricAcidUmol2Mg;
        List<BloodKetones> listFilterBloodKetonesData = HealthDataFilterKt.filterBloodKetonesData(bloodKetonesData);
        if (listFilterBloodKetonesData.isEmpty()) {
            strUricAcidUmol2Mg = "--";
        } else {
            float bloodKetones = listFilterBloodKetonesData.get(0).getBloodKetones();
            if ("mg/dL".equals(this.mUricAcidUnit)) {
                strUricAcidUmol2Mg = TransUtils.uricAcidUmol2Mg(bloodKetones);
            } else {
                strUricAcidUmol2Mg = bloodKetones + "";
            }
        }
        HealthDataFilterKt.removeExistsFunction(this.mHomeFunctionBean, "血酮");
        this.mHomeFunctionBean.add(new HomeFunctionBean(strUricAcidUmol2Mg + "", "", getString(R.string.blood_ketones), "血酮", this.mAppImageMgr.getBitmap(R.mipmap.home_blood_ketone), true));
        HealthDataFilterKt.resortFunction(this.mHomeFunctionBean, this.mFunctionSortMap);
        this.mHomeFunctionAdapter.setList(this.mHomeFunctionBean);
        MLog.INSTANCE.d("addBloodKetonesData: " + this.mHomeFunctionBean.size());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addPhysiotherapyData(List<Physiotherapy> physiotherapyData) {
        String str = "--";
        try {
            if (!physiotherapyData.isEmpty()) {
                long duration = 0;
                while (physiotherapyData.iterator().hasNext()) {
                    duration += r9.next().getDuration();
                }
                str = "" + duration;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            CrashReport.postCatchedException(e2);
        }
        HealthDataFilterKt.removeExistsFunction(this.mHomeFunctionBean, "理疗");
        this.mHomeFunctionBean.add(new HomeFunctionBean(str, "", getString(R.string.physiotherapy), "理疗", this.mAppImageMgr.getBitmap(R.mipmap.ic_physiotherapy), true));
        HealthDataFilterKt.resortFunction(this.mHomeFunctionBean, this.mFunctionSortMap);
        this.mHomeFunctionAdapter.setList(this.mHomeFunctionBean);
        MLog.INSTANCE.d("addPhysiotherapyData: " + this.mHomeFunctionBean.size());
    }

    protected void syncRealData(int type) {
        Log.d(this.TAG, "syncRealData: " + type);
        YCBTClient.appRealSportFromDevice(type, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.home.fragment.HomeFragment.6
            @Override // com.yucheng.ycbtsdk.response.BleDataResponse
            public void onDataResponse(int i2, float v, HashMap hashMap) {
            }
        });
    }

    protected void showDialog() {
        RelativeLayout relativeLayout = this.rvDialog;
        if (relativeLayout != null) {
            relativeLayout.setVisibility(0);
        }
        SpinKitView spinKitView = this.mSpinKitView;
        if (spinKitView != null) {
            spinKitView.setVisibility(0);
        }
        this.handler.postDelayed(this.dismissRun, DeviceOrientationRequest.OUTPUT_PERIOD_DEFAULT);
    }

    protected void dismissDialog() {
        RelativeLayout relativeLayout = this.rvDialog;
        if (relativeLayout != null) {
            relativeLayout.setVisibility(8);
        }
        SpinKitView spinKitView = this.mSpinKitView;
        if (spinKitView != null) {
            spinKitView.setVisibility(8);
        }
    }

    protected void syncFail() {
        this.handler.removeCallbacks(this.dismissRun);
        dismissDialog();
        ToastUtil.getInstance(getActivity()).toast(getString(R.string.ecg_sync_data_failed));
    }

    protected void syncSuccess() {
        this.handler.removeCallbacks(this.dismissRun);
        dismissDialog();
        getDbWatchesData();
        ToastUtil.getInstance(requireActivity()).toast(getString(R.string.ecg_sync_data_success));
        WeatherUtils.weatherFunction(requireActivity());
    }

    protected void setBond() {
        checkBond(true);
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASCREATEBOND) && this.isFirstConnect && !YCBTClient.isOta()) {
            this.isFirstConnect = false;
            YCBTClient.setBonding(true);
            YCBTClient.createBond();
            return;
        }
        YCBTClient.setBonding(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$new$1(Message message) {
        if (getActivity() == null) {
            return false;
        }
        int i2 = message.what;
        if (i2 == 1) {
            updateRealData();
            setBond();
        } else if (i2 == 2) {
            syncFail();
        } else if (i2 == 3) {
            syncSuccess();
        } else if (i2 == 4) {
            setBond();
        }
        return false;
    }

    protected void updateRealData() {
        try {
            this.tvKcal.setText(this.sportCalorie + "");
            if (this.mUnit == 0) {
                this.tvOdo.setText(String.format("%.3f", Float.valueOf(this.sportDistance / 1000.0f)) + "");
            } else {
                this.tvOdo.setText(String.format("%.3f", Float.valueOf(this.sportDistance / 1609.344f)) + "");
            }
            this.sportTarget = ((Integer) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.TARGET_NUMBER_OF_MOVEMENT_STEPS, Integer.valueOf(DfuConstants.MAX_NOTIFICATION_LOCK_WAIT_TIME))).intValue();
            this.tvStep.setText(this.sportTarget + "");
            this.mStepView.setStepMax(this.sportTarget);
            if (((Integer) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.TARGET_NUMBER_OF_MOVEMENT_STEPS, Integer.valueOf(DfuConstants.MAX_NOTIFICATION_LOCK_WAIT_TIME))).intValue() != this.sportTarget || this.isFirst) {
                this.isFirst = false;
                startAnimator(this.sportStep);
            } else {
                this.mStepView.setCurrentStep(this.sportStep);
            }
            if (Constant.isMymon()) {
                for (HomeFunctionBean homeFunctionBean : this.mHomeFunctionBean) {
                    if ("运动".equals(homeFunctionBean.getFunction())) {
                        homeFunctionBean.setValue(this.sportStep + "");
                    }
                }
                this.mHomeFunctionAdapter.setList(this.mHomeFunctionBean);
                this.mHomeFunctionAdapter.notifyDataSetChanged();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            BuglyLog.e(this.TAG, e2.getMessage());
            CrashReport.postCatchedException(e2);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getCompile(EventBusMessageEvent messageEvent) {
        if (getActivity() == null) {
            return;
        }
        int i2 = messageEvent.belState;
        if (i2 == 0) {
            this.bar.getTv_right().setVisibility(8);
            this.handler.removeCallbacks(this.dismissRun);
            dismissDialog();
            DataSyncUtils.INSTANCE.setSyncing(false);
        } else if (i2 == 1) {
            SportViewModel sportViewModel = this.mSportViewModel;
            if (sportViewModel != null && sportViewModel.getSportMode().getValue().booleanValue()) {
                Log.d(this.TAG, "mSportViewModel: 跳过同步");
                return;
            }
            this.failCount = 0;
            this.bar.getTv_right().setVisibility(8);
            if (this.isResume) {
                checkBattery();
            }
            HomeFragmentModelUtil.updateHomeFunction(getActivity());
            if (YCBTClient.isForceOta()) {
                this.mainActivity.upgradeDownload(YCBTClient.getBindDeviceMac());
            } else if (!YCBTClient.isOta()) {
                showDialog();
                WeatherUtils.weatherFunction(requireActivity());
                YCBTClient.getDeviceInfo(new BleDataResponse() { // from class: com.yucheng.smarthealthpro.home.fragment.HomeFragment.8
                    @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                    public void onDataResponse(int code, float ratio, HashMap resultMap) throws NumberFormatException {
                        String str;
                        String str2;
                        String str3 = "";
                        if (code == 0) {
                            int iIntValue = 0;
                            try {
                                HashMap map = (HashMap) resultMap.get("data");
                                iIntValue = ((Integer) map.get("hardwareType")).intValue();
                                str = (String) map.get(Constant.SpConstKey.deviceVersion);
                                try {
                                    int iIntValue2 = ((Integer) map.get("bloodAlgoSubVersion")).intValue();
                                    int iIntValue3 = ((Integer) map.get("bloodAlgoMainVersion")).intValue();
                                    int iIntValue4 = ((Integer) map.get("tpSubVersion")).intValue();
                                    str2 = iIntValue3 + "." + iIntValue2;
                                    try {
                                        str3 = ((Integer) map.get("tpMainVersion")).intValue() + "." + iIntValue4;
                                    } catch (Exception e2) {
                                        e = e2;
                                        e.printStackTrace();
                                        CrashReport.postCatchedException(e);
                                        Log.d(HomeFragment.this.TAG, "getDeviceInfo: " + str + " bloodAlgoVersion:" + str2 + Constant.SpConstKey.tpVersion + str3);
                                        SharedPreferencesUtils.put(MyApplication.getInstance().getApplicationContext(), "hardwareType", Integer.valueOf(iIntValue));
                                        SharedPreferencesUtils.put(MyApplication.getInstance().getApplicationContext(), Constant.SpConstKey.deviceVersion, str);
                                        SharedPreferencesUtils.put(MyApplication.getInstance().getApplicationContext(), Constant.SpConstKey.bloodAlgoVersion, str2);
                                        SharedPreferencesUtils.put(MyApplication.getInstance().getApplicationContext(), Constant.SpConstKey.tpVersion, str3);
                                        HomeFragment.this.syncSettingData();
                                    }
                                } catch (Exception e3) {
                                    e = e3;
                                    str2 = "";
                                }
                            } catch (Exception e4) {
                                e = e4;
                                str = "";
                                str2 = str;
                            }
                            Log.d(HomeFragment.this.TAG, "getDeviceInfo: " + str + " bloodAlgoVersion:" + str2 + Constant.SpConstKey.tpVersion + str3);
                            SharedPreferencesUtils.put(MyApplication.getInstance().getApplicationContext(), "hardwareType", Integer.valueOf(iIntValue));
                            SharedPreferencesUtils.put(MyApplication.getInstance().getApplicationContext(), Constant.SpConstKey.deviceVersion, str);
                            SharedPreferencesUtils.put(MyApplication.getInstance().getApplicationContext(), Constant.SpConstKey.bloodAlgoVersion, str2);
                            SharedPreferencesUtils.put(MyApplication.getInstance().getApplicationContext(), Constant.SpConstKey.tpVersion, str3);
                            HomeFragment.this.syncSettingData();
                        }
                    }
                });
                YCBTClient.getDeviceUserConfig(new BleDataResponse() { // from class: com.yucheng.smarthealthpro.home.fragment.HomeFragment.9
                    /* JADX WARN: Removed duplicated region for block: B:17:0x0043  */
                    /* JADX WARN: Removed duplicated region for block: B:18:0x0047  */
                    /* JADX WARN: Removed duplicated region for block: B:21:0x0055  */
                    @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct code enable 'Show inconsistent code' option in preferences
                    */
                    public void onDataResponse(int r5, float r6, java.util.HashMap r7) {
                        /*
                            r4 = this;
                            if (r5 != 0) goto L6c
                            r5 = 0
                            r6 = 1
                            java.lang.String r0 = "data"
                            java.lang.Object r7 = r7.get(r0)     // Catch: java.lang.Exception -> L36
                            java.util.HashMap r7 = (java.util.HashMap) r7     // Catch: java.lang.Exception -> L36
                            java.lang.String r0 = "handupswitch"
                            java.lang.Object r0 = r7.get(r0)     // Catch: java.lang.Exception -> L36
                            java.lang.Integer r0 = (java.lang.Integer) r0     // Catch: java.lang.Exception -> L36
                            int r0 = r0.intValue()     // Catch: java.lang.Exception -> L36
                            java.lang.String r1 = "screenval"
                            java.lang.Object r1 = r7.get(r1)     // Catch: java.lang.Exception -> L33
                            java.lang.Integer r1 = (java.lang.Integer) r1     // Catch: java.lang.Exception -> L33
                            int r1 = r1.intValue()     // Catch: java.lang.Exception -> L33
                            java.lang.String r2 = "heartHand"
                            java.lang.Object r7 = r7.get(r2)     // Catch: java.lang.Exception -> L31
                            java.lang.Integer r7 = (java.lang.Integer) r7     // Catch: java.lang.Exception -> L31
                            int r7 = r7.intValue()     // Catch: java.lang.Exception -> L31
                            goto L3d
                        L31:
                            r7 = move-exception
                            goto L39
                        L33:
                            r7 = move-exception
                            r1 = r5
                            goto L39
                        L36:
                            r7 = move-exception
                            r1 = r5
                            r0 = r6
                        L39:
                            r7.printStackTrace()
                            r7 = r5
                        L3d:
                            com.yucheng.smarthealthpro.home.fragment.HomeFragment r2 = com.yucheng.smarthealthpro.home.fragment.HomeFragment.this
                            android.content.Context r2 = r2.context
                            if (r0 != r6) goto L47
                            java.lang.String r0 = "开"
                            goto L4a
                        L47:
                            java.lang.String r0 = "关"
                        L4a:
                            java.lang.String r3 = "isRaiseScreen"
                            com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils.put(r2, r3, r0)
                            com.yucheng.smarthealthpro.home.fragment.HomeFragment r0 = com.yucheng.smarthealthpro.home.fragment.HomeFragment.this
                            android.content.Context r0 = r0.context
                            if (r7 != r6) goto L56
                            r5 = r6
                        L56:
                            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
                            java.lang.String r6 = "ecgWearHand"
                            com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils.put(r0, r6, r5)
                            com.yucheng.smarthealthpro.home.fragment.HomeFragment r5 = com.yucheng.smarthealthpro.home.fragment.HomeFragment.this
                            android.content.Context r5 = r5.context
                            java.lang.String r6 = "mLuminanceOptionsOne"
                            java.lang.Integer r7 = java.lang.Integer.valueOf(r1)
                            com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils.put(r5, r6, r7)
                        L6c:
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.home.fragment.HomeFragment.AnonymousClass9.onDataResponse(int, float, java.util.HashMap):void");
                    }
                });
                this.dataSyncUtils.startDataSync(this.dataSyncEvent);
                this.mainActivity.startCheck();
                String str = Tools.readString(Constant.SpConstKey.USER_NAME, getActivity(), "") + "--" + YCBTClient.getBindDeviceMac();
                if (Constant.isMymon() && Tools.readLogin(getActivity()) && !str.equals(SharedPreferencesUtils.get(getActivity(), "userAndMac", ""))) {
                    upMac();
                }
            }
        }
        freshConnectIcon();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getBel(String message) {
        if (message.equals(Constant.EventBusTags.COMPILE_SAVE_SUCCEED)) {
            getDbWatchesData();
        }
    }

    protected void getDbWatchesData() {
        if (this.mHomeFunctionBean == null || getActivity() == null) {
            MLog.INSTANCE.d("mHomeFunctionBean 为空");
            return;
        }
        this.mHomeFunctionBeanCache.clear();
        this.mHomeFunctionBeanCache.addAll(this.mHomeFunctionBean);
        this.mHomeFunctionBean.clear();
        String str = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.IS_CONNECT, "");
        if (!Constant.SpConstValue.IS_CONNECT.equals(str) && YCBTClient.connectState() == 10) {
            SharedPreferencesUtils.put(this.context, Constant.SpConstKey.IS_CONNECT, Constant.SpConstValue.IS_CONNECT);
            addDefaultData();
            MLog.INSTANCE.d("addDefaultData isConnect:" + str);
        } else {
            MLog.INSTANCE.d("setLatestDbData");
            setLatestDbData();
        }
        this.mHomeFunctionAdapter.setList(this.mHomeFunctionBean);
        this.mHomeFunctionAdapter.notifyDataSetChanged();
    }

    private void initViewModel() {
        this.mViewModel = (HomeViewModel) new ViewModelProvider(this).get(HomeViewModel.class);
        this.mSportViewModel = (SportViewModel) MyApplication.sInstance.getAppViewModel(SportViewModel.class);
        Log.d(this.TAG, "mSportViewModel: " + this.mSportViewModel);
        this.mSportViewModel.getSportMode().observe(this, new Observer<Boolean>() { // from class: com.yucheng.smarthealthpro.home.fragment.HomeFragment.10
            @Override // androidx.lifecycle.Observer
            public void onChanged(Boolean isSportMode) {
                MLog.INSTANCE.d("SportViewModel onChanged: " + isSportMode);
            }
        });
        FlowUtils.INSTANCE.collectFlow(this, this.mViewModel.getHrvDataFlow(), new FlowUtils.FlowCollector<List<HealthMetric>>() { // from class: com.yucheng.smarthealthpro.home.fragment.HomeFragment.11
            @Override // com.yucheng.smarthealthpro.utils.FlowUtils.FlowCollector
            public void collect(List<HealthMetric> data) {
                MLog.INSTANCE.d("getHrvDataFlow: " + data.toString());
                HomeFragment.this.addHRVData(data);
            }
        });
        FlowUtils.INSTANCE.collectFlow(this, this.mViewModel.getPressureDataFlow(), new FlowUtils.FlowCollector<List<BodyData>>() { // from class: com.yucheng.smarthealthpro.home.fragment.HomeFragment.12
            @Override // com.yucheng.smarthealthpro.utils.FlowUtils.FlowCollector
            public void collect(List<BodyData> data) {
                MLog.INSTANCE.d("getPressureDataFlow: " + data.toString());
                HomeFragment.this.addPressureData(data);
            }
        });
        FlowUtils.INSTANCE.collectFlow(this, this.mViewModel.getPhysiotherapyDataFlow(), new FlowUtils.FlowCollector<List<Physiotherapy>>() { // from class: com.yucheng.smarthealthpro.home.fragment.HomeFragment.13
            @Override // com.yucheng.smarthealthpro.utils.FlowUtils.FlowCollector
            public void collect(List<Physiotherapy> data) {
                MLog.INSTANCE.d("getPhysiotherapyDataFlow: " + data.toString());
                HomeFragment.this.addPhysiotherapyData(data);
            }
        });
        FlowUtils.INSTANCE.collectFlow(this, this.mViewModel.getTemperatureDataFlow(), new FlowUtils.FlowCollector<List<HealthMetric>>() { // from class: com.yucheng.smarthealthpro.home.fragment.HomeFragment.14
            @Override // com.yucheng.smarthealthpro.utils.FlowUtils.FlowCollector
            public void collect(List<HealthMetric> data) {
                MLog.INSTANCE.d("getTemperatureDataFlow: " + data.toString());
                HomeFragment.this.addLatestTempData(data);
            }
        });
        FlowUtils.INSTANCE.collectFlow(this, this.mViewModel.getBloodLipidsDataFlow(), new FlowUtils.FlowCollector<List<BloodLipids>>() { // from class: com.yucheng.smarthealthpro.home.fragment.HomeFragment.15
            @Override // com.yucheng.smarthealthpro.utils.FlowUtils.FlowCollector
            public void collect(List<BloodLipids> data) {
                MLog.INSTANCE.d("getBloodLipidsDataFlow: " + data.toString());
                HomeFragment.this.addBloodFatsData(data);
            }
        });
        FlowUtils.INSTANCE.collectFlow(this, this.mViewModel.getBloodKetonesDataFlow(), new FlowUtils.FlowCollector<List<BloodKetones>>() { // from class: com.yucheng.smarthealthpro.home.fragment.HomeFragment.16
            @Override // com.yucheng.smarthealthpro.utils.FlowUtils.FlowCollector
            public void collect(List<BloodKetones> data) {
                MLog.INSTANCE.d("getBloodKetonesDataFlow: " + data.toString());
                HomeFragment.this.addBloodKetonesData(data);
            }
        });
        FlowUtils.INSTANCE.collectFlow(this, this.mViewModel.getBloodOxygenDataFlow(), new FlowUtils.FlowCollector<List<HealthMetric>>() { // from class: com.yucheng.smarthealthpro.home.fragment.HomeFragment.17
            @Override // com.yucheng.smarthealthpro.utils.FlowUtils.FlowCollector
            public void collect(List<HealthMetric> data) {
                MLog.INSTANCE.d("getBloodOxygenDataFlow: " + data.toString());
                HomeFragment.this.addLatestSpo2Data(data);
            }
        });
        FlowUtils.INSTANCE.collectFlow(this, this.mViewModel.getBloodSugarDataFlow(), new FlowUtils.FlowCollector<List<HealthMetric>>() { // from class: com.yucheng.smarthealthpro.home.fragment.HomeFragment.18
            @Override // com.yucheng.smarthealthpro.utils.FlowUtils.FlowCollector
            public void collect(List<HealthMetric> data) {
                MLog.INSTANCE.d("getBloodSugarDataFlow: " + data.toString());
                HomeFragment.this.addLatestBloodSugarData(data);
            }
        });
        FlowUtils.INSTANCE.collectFlow(this, this.mViewModel.getHeartRateDataFlow(), new FlowUtils.FlowCollector<List<HeartRate>>() { // from class: com.yucheng.smarthealthpro.home.fragment.HomeFragment.19
            @Override // com.yucheng.smarthealthpro.utils.FlowUtils.FlowCollector
            public void collect(List<HeartRate> data) {
                MLog.INSTANCE.d("getHeartRateDataFlow: " + data.toString());
                HomeFragment.this.addLatestHeartRateData(data);
            }
        });
        FlowUtils.INSTANCE.collectFlow(this, this.mViewModel.getRespiratoryRateDataFlow(), new FlowUtils.FlowCollector<List<HealthMetric>>() { // from class: com.yucheng.smarthealthpro.home.fragment.HomeFragment.20
            @Override // com.yucheng.smarthealthpro.utils.FlowUtils.FlowCollector
            public void collect(List<HealthMetric> data) {
                MLog.INSTANCE.d("getRespiratoryRateDataFlow: " + data.toString());
                HomeFragment.this.addLatestRespiratoryRateData(data);
            }
        });
        FlowUtils.INSTANCE.collectFlow(this, this.mViewModel.getUricAcidDataFlow(), new FlowUtils.FlowCollector<List<UricAcid>>() { // from class: com.yucheng.smarthealthpro.home.fragment.HomeFragment.21
            @Override // com.yucheng.smarthealthpro.utils.FlowUtils.FlowCollector
            public void collect(List<UricAcid> data) {
                MLog.INSTANCE.d("getUricAcidDataFlow: " + data.toString());
                HomeFragment.this.addUricAcidsData(data);
            }
        });
        FlowUtils.INSTANCE.collectFlow(this, this.mViewModel.getBloodPressureDataFlow(), new FlowUtils.FlowCollector<List<BloodPressure>>() { // from class: com.yucheng.smarthealthpro.home.fragment.HomeFragment.22
            @Override // com.yucheng.smarthealthpro.utils.FlowUtils.FlowCollector
            public void collect(List<BloodPressure> data) {
                MLog.INSTANCE.d("getBloodPressureDataFlow: " + data.toString());
                HomeFragment.this.addLatestBloodPressureData(data);
            }
        });
        FlowUtils.INSTANCE.collectFlow(this, this.mViewModel.getSleepDataFlow(), new FlowUtils.FlowCollector<Pair<List<Sleep>, List<Sleep>>>() { // from class: com.yucheng.smarthealthpro.home.fragment.HomeFragment.23
            @Override // com.yucheng.smarthealthpro.utils.FlowUtils.FlowCollector
            public void collect(Pair<List<Sleep>, List<Sleep>> data) {
                MLog.INSTANCE.d("getSleepDataFlow: " + data.toString());
                HomeFragment.this.addLatestSleepData(data.getFirst(), data.getSecond());
            }
        });
    }

    @Override // com.yucheng.smarthealthpro.framework.BaseFragment, androidx.fragment.app.Fragment
    public void onAttach(Context ctx) {
        super.onAttach(ctx);
        try {
            this.mainActivity = (MainActivity) ctx;
        } catch (Exception e2) {
            e2.printStackTrace();
            CrashReport.postCatchedException(e2);
        }
    }

    protected void syncSettingData() throws NumberFormatException {
        String str;
        this.isFirstConnect = true;
        try {
            if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASCREATEBOND)) {
                YCBTClient.appSendUUID(UUIDUtils.generateUUID(getActivity()), null);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        YCBTClient.settingLanguage(MultiLanguageUtils.getLanguage(MultiLanguageUtils.getAppLocale(this.context)), null);
        int[] unit = DataTools.getUnit(this.context);
        YCBTClient.settingUnit(unit[0], unit[1], unit[2], (byte) (!DateFormat.is24HourFormat(this.context) ? 1 : 0), unit[3], unit[4], null);
        if ((YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASHEARTRATE) || YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASTEMP)) && ((str = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.HEART_RATE_DETECTION_SWITCH, Constant.getDefaultMonitorSwitch())) == null || str.equals(Constant.SpConstValue.OPEN))) {
            try {
                int i2 = Integer.parseInt((String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.HEART_RATE_INTERVAL_TIME, Constant.getDefaultMonitorTime()));
                if (Constant.isRing() && !Constant.isHealthRing() && i2 < 30) {
                    i2 = 30;
                }
                YCBTClient.settingHeartMonitor(1, i2, null);
                if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASTEMP)) {
                    YCBTClient.settingTemperatureMonitor(true, i2, null);
                }
            } catch (Exception e3) {
                e3.printStackTrace();
                CrashReport.postCatchedException(e3);
            }
        }
        YCBTClient.settingAppSystem(0, Build.VERSION.RELEASE, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.home.fragment.HomeFragment.24
            @Override // com.yucheng.ycbtsdk.response.BleDataResponse
            public void onDataResponse(int i3, float v, HashMap hashMap) {
            }
        });
        YCBTClient.getDeviceName(new BleDataResponse() { // from class: com.yucheng.smarthealthpro.home.fragment.HomeFragment.25
            @Override // com.yucheng.ycbtsdk.response.BleDataResponse
            public void onDataResponse(int i3, float v, HashMap hashMap) {
                if (i3 != 0 || HomeFragment.this.mainActivity == null) {
                    return;
                }
                HomeFragment.this.updateHttpCache();
                if (System.currentTimeMillis() - ((Long) SharedPreferencesUtils.get(HomeFragment.this.getContext(), Constant.SpConstKey.lastAlgorithmUpgradeTime, 0L)).longValue() > 240000) {
                    HomeFragment.this.mainActivity.newCheckFirmwareVersion(Tools.getDeviceType(HomeFragment.this.context));
                }
            }
        });
        int age = YearToDayListUtils.getAge((String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.BIRTH_DATE, YearToDayListUtils.subYear(20)));
        int iIntValue = ((Integer) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.SEX, 0)).intValue();
        int iIntValue2 = ((Integer) SharedPreferencesUtils.get(this.context, "height", Integer.valueOf(Opcodes.TABLESWITCH))).intValue();
        int iIntValue3 = ((Integer) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.WEIGHT, 65)).intValue();
        int i3 = this.mUnit;
        if (i3 != 0) {
            iIntValue2 = (int) (iIntValue2 * 2.54f);
        }
        if (i3 != 0) {
            iIntValue3 = (int) (iIntValue3 * 0.45359f);
        }
        YCBTClient.settingUserInfo(iIntValue2, iIntValue3, iIntValue, age, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.home.fragment.HomeFragment.26
            @Override // com.yucheng.ycbtsdk.response.BleDataResponse
            public void onDataResponse(int i4, float v, HashMap hashMap) {
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateHttpCache() {
        String versionName = PackageUtils.getVersionName(this.context);
        String str = PackageUtils.getVersionCode(this.context) + "";
        String bindDeviceVersion = YCBTClient.getBindDeviceVersion();
        int hardwareType = SPUtil.getHardwareType();
        String deviceType = Tools.getDeviceType(this.context);
        String lowerCase = getString(R.string.lan).toLowerCase();
        String string = getString(R.string.app_name);
        String timeZoneOffset = TimeZoneUtils.getTimeZoneOffset();
        Logger.d("updateHttpCache: " + deviceType);
        HttpUtils.getInstance().putHeaderInfo(string, versionName, str, bindDeviceVersion, hardwareType, deviceType, lowerCase, timeZoneOffset);
    }

    protected void setRecycleView() {
        this.mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        HomeFunctionAdapter homeFunctionAdapter = new HomeFunctionAdapter(R.layout.item_home_function);
        this.mHomeFunctionAdapter = homeFunctionAdapter;
        homeFunctionAdapter.addData((Collection) this.mHomeFunctionBean);
        this.mRecyclerView.setAdapter(this.mHomeFunctionAdapter);
        this.mRecyclerView.setNestedScrollingEnabled(false);
        this.mHomeFunctionAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.yucheng.smarthealthpro.home.fragment.HomeFragment.27
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public void onItemClick(BaseQuickAdapter<?, ?> adapter, View view, int position) {
                HomeFunctionBean homeFunctionBean = HomeFragment.this.mHomeFunctionAdapter.getData().get(position);
                if (HomeFragment.this.checkCanClick()) {
                    HomeFragment.this.onFunctionItemClick(homeFunctionBean);
                }
            }
        });
        this.mHomeFunctionAdapter.addChildClickViewIds(R.id.ll_data);
        this.mHomeFunctionAdapter.setOnItemChildClickListener(new OnItemChildClickListener() { // from class: com.yucheng.smarthealthpro.home.fragment.HomeFragment.28
            @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                HomeFunctionBean homeFunctionBean = HomeFragment.this.mHomeFunctionAdapter.getData().get(position);
                if (HomeFragment.this.checkCanClick()) {
                    if ("睡眠".equals(homeFunctionBean.getFunction()) && "00".equals(homeFunctionBean.getValue())) {
                        HomeFragment.this.showSleepQuestionDialog();
                    } else {
                        HomeFragment.this.onFunctionItemClick(homeFunctionBean);
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onFunctionItemClick(HomeFunctionBean functionBean) {
        String function = functionBean.getFunction();
        function.hashCode();
        switch (function) {
            case "HRV":
                startActivity(new Intent(getActivity(), (Class<?>) HRVActivity.class));
                break;
            case "压力":
                startActivity(new Intent(getActivity(), (Class<?>) PressureActivity.class));
                break;
            case "尿酸":
                startActivity(new Intent(getActivity(), (Class<?>) UricAcidActivity.class));
                break;
            case "心率":
                startActivity(new Intent(getActivity(), (Class<?>) HeartRateActivity.class));
                break;
            case "心电":
                startActivity(new Intent(getActivity(), (Class<?>) EcgActivity.class));
                break;
            case "温度":
                startActivity(new Intent(getActivity(), (Class<?>) TemperatureActivity.class));
                break;
            case "理疗":
                startActivity(new Intent(getActivity(), (Class<?>) PhyActivity.class));
                break;
            case "睡眠":
                startActivity(new Intent(getActivity(), (Class<?>) SleepActivity.class));
                break;
            case "血压":
                startActivity(new Intent(getActivity(), (Class<?>) BloodPressureActivity.class));
                break;
            case "血氧":
                startActivity(new Intent(getActivity(), (Class<?>) BloodOxygenActivity.class));
                break;
            case "血糖":
                startActivity(new Intent(getActivity(), (Class<?>) BloodSugarActivity.class));
                break;
            case "血脂":
                startActivity(new Intent(getActivity(), (Class<?>) BloodFatActivity.class));
                break;
            case "血酮":
                startActivity(new Intent(getActivity(), (Class<?>) KetoneActivity.class));
                break;
            case "运动":
                startActivity(new Intent(getActivity(), (Class<?>) RunningActivity.class));
                break;
            case "呼吸率":
                startActivity(new Intent(getActivity(), (Class<?>) RespiratoryRateActivity.class));
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showSleepQuestionDialog() {
        String string = new StringBuffer().append(getString(R.string.sleep_empty_hint)).append("\n\n").append(getString(R.string.sleep_empty_explanation)).append(StringUtils.LF).append(getString(R.string.sleep_empty_reason_1)).append(StringUtils.LF).append(getString(R.string.sleep_empty_reason_2)).append(StringUtils.LF).append(getString(R.string.sleep_empty_reason_3)).append(StringUtils.LF).append(getString(R.string.sleep_empty_reason_4)).toString();
        final CommonDialog commonDialog = new CommonDialog(getActivity());
        commonDialog.setMessage(string).setSingle(true).setAlignStart(true).setSmallerMessage(true).setConfirm(getString(R.string.wisdom_action_knew)).setOnClickBottomListener(new CommonDialog.OnClickBottomListener() { // from class: com.yucheng.smarthealthpro.home.fragment.HomeFragment.29
            @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
            public void onCancelClick() {
            }

            @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
            public void onEditTextConfirmClick(String mEditText) {
            }

            @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
            public void onConfirmClick() {
                commonDialog.dismiss();
            }
        }).show();
    }

    public void onViewClicked(View view) {
        if (view.getId() == R.id.rl_running) {
            if (Constant.isMymon()) {
                startActivity(new Intent(getActivity(), (Class<?>) PDNumberActivity.class).putExtra(Constant.SpConstKey.DEV_ID, (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.DEV_ID, "1")).putExtra("title", (String) SharedPreferencesUtils.get(getActivity(), "displaytext", "--")));
                return;
            } else {
                startActivity(new Intent(getActivity(), (Class<?>) RunningActivity.class));
                return;
            }
        }
        if (view.getId() == R.id.ll_ranking) {
            if (Tools.readLogin(getActivity())) {
                startActivity(new Intent(getActivity(), (Class<?>) RankingActivity.class));
                return;
            } else {
                ToastUtil.getInstance(getActivity()).toast(getString(R.string.me_using_help_feed_back_token_null));
                return;
            }
        }
        if (view.getId() == R.id.ll_healthy) {
            if (Tools.readLogin(getActivity())) {
                startActivity(new Intent(getActivity(), (Class<?>) HealthyActivity.class));
                return;
            } else {
                ToastUtil.getInstance(getActivity()).toast(getString(R.string.me_using_help_feed_back_token_null));
                return;
            }
        }
        if (view.getId() == R.id.ll_compile) {
            if (YCBTClient.connectState() == 10) {
                startActivity(new Intent(this.context, (Class<?>) CompileActivity.class));
                return;
            } else {
                ToastUtil.getInstance(getActivity()).toast(getString(R.string.please_connect_the_device));
                return;
            }
        }
        if (view.getId() == R.id.rv_dialog) {
            return;
        }
        if (view.getId() == R.id.home_pair_permission_title) {
            YCBTClient.setBonding(true);
            YCBTClient.createBond();
            this.home_pair_permission_title.setText(R.string.bluetooth_is_connecting);
            this.handler.removeCallbacks(this.checkBondRun);
            this.handler.postDelayed(this.checkBondRun, 10000L);
            return;
        }
        if (view.getId() == R.id.home_permission_title) {
            startActivity(new Intent(getActivity(), (Class<?>) PermissionActivity.class));
        }
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        Logger.d("onDestroyView");
        this.handler.removeCallbacks(this.dismissRun);
        this.handler.removeCallbacks(this.checkDeviceIcon);
        this.handler.removeCallbacks(this.checkBondRun);
        EventBus.getDefault().unregister(this);
    }

    @Override // com.gyf.immersionbar.components.ImmersionOwner
    public void initImmersionBar() {
        ImmersionBar.with(this).statusBarDarkFont(true, 0.0f).navigationBarDarkIcon(true, 0.0f).navigationBarColor(R.color.transparent).keyboardEnable(true).init();
    }

    protected class MyOnClickListenerImpl implements NavigationBar.MyOnClickListener {
        protected MyOnClickListenerImpl() {
        }

        @Override // com.yucheng.smarthealthpro.framework.view.NavigationBar.MyOnClickListener
        public void onClick(View btn) {
            if (YCBTClient.connectState() == 10) {
                HomeFragment.this.startActivity(new Intent(HomeFragment.this.getContext(), (Class<?>) MeDeviceActivity.class));
                return;
            }
            if (HomeFragment.this.mainActivity != null) {
                HomeFragment.this.mainActivity.isShowBluetoothDialog = false;
            }
            HomeFragment.this.startActivity(new Intent(HomeFragment.this.getContext(), (Class<?>) DeviceListActivity.class));
        }
    }

    protected class OnRefreshListenerImpl implements OnRefreshListener {
        protected OnRefreshListenerImpl() {
        }

        @Override // com.scwang.smart.refresh.layout.listener.OnRefreshListener
        public void onRefresh(RefreshLayout refreshLayout) {
            Log.d(HomeFragment.this.TAG, "onRefresh: ");
            HomeFragment.this.freshConnectIcon();
            HomeFragment.this.mToDay = TimeStampUtils.getToDay();
            int iConnectState = YCBTClient.connectState();
            if (iConnectState != 3 && iConnectState == 10) {
                if (!YCBTClient.isForceOta() && !YCBTClient.isOta()) {
                    HomeFragment.this.showDialog();
                    WeatherUtils.weatherFunction(HomeFragment.this.requireActivity());
                    HomeFragment.this.dataSyncUtils.startDataSync(HomeFragment.this.dataSyncEvent);
                    HomeFragment.this.mainActivity.showProgressDialog(HomeFragment.this.getString(R.string.ecg_sync_data));
                    HomeFragment.this.mainActivity.startCheck();
                }
            } else {
                Toast.makeText(HomeFragment.this.context, HomeFragment.this.getString(R.string.please_connect_the_device), 0).show();
            }
            HomeFragment.this.upLoadEcgDiagnosis();
            refreshLayout.finishRefresh();
        }
    }

    @Override // com.gyf.immersionbar.components.ImmersionFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        if (this.mBroadcastReceiver != null) {
            requireActivity().unregisterReceiver(this.mBroadcastReceiver);
        }
    }

    protected class MBroadcastReceiver extends BroadcastReceiver {
        protected MBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction() == null || !intent.getAction().equals("syncData")) {
                return;
            }
            HomeFragment.this.dataSyncUtils.getWatchesData(intent.getIntExtra("type", Constants.DATATYPE.Health_HistorySport));
        }
    }

    protected void setPDNumber() throws NumberFormatException {
        float f2;
        if (getActivity() == null) {
            return;
        }
        String str = (String) SharedPreferencesUtils.get(getActivity(), "displayvalue", "0");
        String str2 = (String) SharedPreferencesUtils.get(getActivity(), "displayunits", "--");
        String str3 = (String) SharedPreferencesUtils.get(getActivity(), "displaytext", "--");
        float fFloatValue = ((Float) SharedPreferencesUtils.get(getActivity(), "displayvalueMax", Float.valueOf(0.0f))).floatValue();
        float fFloatValue2 = ((Float) SharedPreferencesUtils.get(getActivity(), "displayvalueMin", Float.valueOf(0.0f))).floatValue();
        ((TextView) getActivity().findViewById(R.id.home_pd_number)).setText(str);
        ((TextView) getActivity().findViewById(R.id.home_pd_number_unit)).setText(str2);
        ((TextView) getActivity().findViewById(R.id.home_pd_number_AAAAAAA)).setText(str3);
        ((TextView) getActivity().findViewById(R.id.home_pd_number_max)).setText("Max\n" + (fFloatValue == 0.0f ? "--" : fFloatValue + StringUtils.SPACE + str2));
        ((TextView) getActivity().findViewById(R.id.home_pd_number_min)).setText("Min\n" + (fFloatValue != 0.0f ? fFloatValue2 + StringUtils.SPACE + str2 : "--"));
        try {
            f2 = Float.parseFloat(str);
        } catch (Exception e2) {
            e2.printStackTrace();
            f2 = 0.0f;
        }
        if (f2 > 11.0f) {
            getActivity().findViewById(R.id.rl_home_pd_number).setBackgroundResource(R.drawable.home_top_red_bg);
        } else if (f2 > 4.0f) {
            getActivity().findViewById(R.id.rl_home_pd_number).setBackgroundResource(R.drawable.home_top_green_bg);
        } else if (f2 > 0.0f) {
            getActivity().findViewById(R.id.rl_home_pd_number).setBackgroundResource(R.drawable.home_top_amber_bg);
        }
    }

    protected void upMac() {
        final FragmentActivity fragmentActivityRequireActivity = requireActivity();
        HashMap map = new HashMap();
        map.put("userId", SharedPreferencesUtils.get(fragmentActivityRequireActivity, Constant.SpConstKey.DEV_ID, "1"));
        map.put(Constant.SpConstKey.USER_NAME, Tools.readString(Constant.SpConstKey.USER_NAME, fragmentActivityRequireActivity, ""));
        map.put("mac", YCBTClient.getBindDeviceMac());
        HttpUtils.getInstance().postJsonMsgAsynHttp(getActivity(), com.yucheng.smarthealthpro.framework.util.Constants.MYMOMUPMAC, new Gson().toJson(map), new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.home.fragment.HomeFragment.31
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                if (result != null) {
                    SharedPreferencesUtils.put(fragmentActivityRequireActivity, "userAndMac", Tools.readString(Constant.SpConstKey.USER_NAME, fragmentActivityRequireActivity, "") + "--" + YCBTClient.getBindDeviceMac());
                }
            }
        });
    }

    public void upLoadEcgDiagnosis() {
        this.dataSyncUtils.uploadEcgHistoryData();
    }

    protected void showBattyDialog() {
        final CommonDialog commonDialog = new CommonDialog(getActivity());
        commonDialog.setMessage(getString(R.string.update_batty_low)).setTitle(getString(R.string.prompt)).setSingle(true).setOnClickBottomListener(new CommonDialog.OnClickBottomListener() { // from class: com.yucheng.smarthealthpro.home.fragment.HomeFragment.32
            @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
            public void onConfirmClick() {
                commonDialog.dismiss();
            }

            @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
            public void onCancelClick() {
                commonDialog.dismiss();
            }

            @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
            public void onEditTextConfirmClick(String mEditText) {
                commonDialog.dismiss();
            }
        }).show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSyncEvent(EventBusSyncData eventBusSyncData) {
        if (eventBusSyncData.isSyncSuccess) {
            syncFinish();
        }
    }

    public void syncFinish() {
        this.handler.removeCallbacks(this.dismissRun);
        dismissDialog();
        syncRealData(1);
        getDbWatchesData();
    }

    protected void pairBluetoothDevice(BluetoothDevice device) {
        Intent intent = new Intent();
        intent.setAction("android.bluetooth.device.action.PAIRING_REQUEST");
        intent.putExtra("android.bluetooth.device.extra.DEVICE", device);
        startActivity(intent);
    }

    protected void toBleSetting() {
        startActivityForResult(new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE"), 1);
    }

    protected void toBleSetting2() {
        startActivityForResult(new Intent("android.settings.BLUETOOTH_SETTINGS"), 1);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        checkBond();
    }

    public boolean checkBond() {
        return checkBond(false);
    }

    public boolean checkBond(boolean isPairing) {
        try {
            if (this.home_pair_permission_title != null && Constant.isHealthWear() && Constant.isSmartHealth()) {
                if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASCREATEBOND) && YCBTClient.connectState() == 10 && !YCBTClient.isForceOta() && !YCBTClient.isBond() && YCBTClient.isJieLi()) {
                    if (!this.home_pair_permission_title.getText().toString().equals(getString(R.string.bluetooth_pairing_tips))) {
                        this.home_pair_permission_title.setText(R.string.bluetooth_pairing_tips);
                    }
                    if (isPairing) {
                        this.home_pair_permission_title.setText(R.string.bluetooth_is_connecting);
                    }
                    this.home_pair_permission_title.setVisibility(0);
                    return false;
                }
                this.home_pair_permission_title.setVisibility(8);
            }
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    protected void showPairDialog() {
        final CommonDialog commonDialog = new CommonDialog(getActivity());
        commonDialog.setMessage(getString(R.string.bluetooth_pairing_tips)).setTitle(getString(R.string.prompt)).setSingle(false).setOnClickBottomListener(new CommonDialog.OnClickBottomListener() { // from class: com.yucheng.smarthealthpro.home.fragment.HomeFragment.33
            @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
            public void onConfirmClick() {
                commonDialog.dismiss();
                YCBTClient.setBonding(true);
                YCBTClient.createBond();
            }

            @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
            public void onCancelClick() {
                commonDialog.dismiss();
            }

            @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
            public void onEditTextConfirmClick(String mEditText) {
                commonDialog.dismiss();
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showTipDialog(final String tip) {
        this.handler.post(new Runnable() { // from class: com.yucheng.smarthealthpro.home.fragment.HomeFragment.34
            @Override // java.lang.Runnable
            public void run() {
                HomeFragment.this.tipDialog = new CommonDialog(HomeFragment.this.getActivity());
                HomeFragment.this.tipDialog.setMessage(tip).setTitle(HomeFragment.this.getString(R.string.prompt)).setSingle(true).setOnClickBottomListener(new CommonDialog.OnClickBottomListener() { // from class: com.yucheng.smarthealthpro.home.fragment.HomeFragment.34.1
                    @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
                    public void onConfirmClick() {
                        if (HomeFragment.this.tipDialog != null) {
                            HomeFragment.this.tipDialog.dismiss();
                            HomeFragment.this.tipDialog = null;
                        }
                    }

                    @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
                    public void onCancelClick() {
                        if (HomeFragment.this.tipDialog != null) {
                            HomeFragment.this.tipDialog.dismiss();
                            HomeFragment.this.tipDialog = null;
                        }
                    }

                    @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
                    public void onEditTextConfirmClick(String mEditText) {
                        if (HomeFragment.this.tipDialog != null) {
                            HomeFragment.this.tipDialog.dismiss();
                            HomeFragment.this.tipDialog = null;
                        }
                    }
                }).show();
            }
        });
    }
}
