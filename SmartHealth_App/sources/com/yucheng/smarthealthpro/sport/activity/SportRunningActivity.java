package com.yucheng.smarthealthpro.sport.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.ViewModelProvider;
import com.amap.api.maps.model.LatLng;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.tencent.bugly.crashreport.CrashReport;
import com.yucheng.smarthealthpro.MyApplication;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.data.bean.SaveMotionPattern;
import com.yucheng.smarthealthpro.data.bean.SaveSportRecord;
import com.yucheng.smarthealthpro.data.packed.HealthResult;
import com.yucheng.smarthealthpro.databinding.ActivitySportrunningBinding;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.framework.util.SubObserver;
import com.yucheng.smarthealthpro.framework.util.ToastUtil;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import com.yucheng.smarthealthpro.home.bean.RealDataResponse;
import com.yucheng.smarthealthpro.login.normal.util.UserInfoUtil;
import com.yucheng.smarthealthpro.me.setting.contacts.utils.DpUtil;
import com.yucheng.smarthealthpro.sport.bean.RunInfo;
import com.yucheng.smarthealthpro.sport.bean.SportTabItem;
import com.yucheng.smarthealthpro.sport.utils.GoogleUtil;
import com.yucheng.smarthealthpro.sport.utils.LocationAMapUtils;
import com.yucheng.smarthealthpro.sport.utils.LocationUtils;
import com.yucheng.smarthealthpro.sport.view.CommonDialog;
import com.yucheng.smarthealthpro.sport.view.StopProgressButton;
import com.yucheng.smarthealthpro.sport.viewmodel.SportViewModel;
import com.yucheng.smarthealthpro.tasks.TimeUploadTask;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.DataSyncUtils;
import com.yucheng.smarthealthpro.utils.DebouncingClick;
import com.yucheng.smarthealthpro.utils.EventBusExitExerciseEvent;
import com.yucheng.smarthealthpro.utils.EventBusMessageEvent;
import com.yucheng.smarthealthpro.utils.EventBusSyncData;
import com.yucheng.smarthealthpro.utils.FlowUtils;
import com.yucheng.smarthealthpro.utils.FormatUtil;
import com.yucheng.smarthealthpro.utils.MLog;
import com.yucheng.smarthealthpro.utils.TimeStampUtils;
import com.yucheng.smarthealthpro.utils.Tools;
import com.yucheng.smarthealthpro.utils.TransUtils;
import com.yucheng.smarthealthpro.viewmodel.SportRunningViewModel;
import com.yucheng.ycbtsdk.AITools;
import com.yucheng.ycbtsdk.Constants;
import com.yucheng.ycbtsdk.YCBTClient;
import com.yucheng.ycbtsdk.response.BleDataResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.ToIntFunction;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes5.dex */
public class SportRunningActivity extends BaseVbActivity<ActivitySportrunningBinding> implements Observer {
    private static final int MIN_DURATION = 60;
    public static final String TAG = "SportRunningActivity";
    private int heartValue;
    private int isMap;
    ImageView ivLock;
    ImageView ivMap;
    ImageView ivStartStop;
    ImageView ivStop;
    ImageView ivUnlock;
    private LatLng lastPoint;
    LinearLayout llFirst;
    LinearLayout llFourthly;
    LinearLayout llLockBg;
    LinearLayout llRunning;
    LinearLayout llSecond;
    LinearLayout llStep;
    LinearLayout llThirdly;
    LinearLayout llThirdlyFourthly;
    private String mAMapLocationString;
    private LatLng mEndLatLng;
    private List<LatLng> mLatLngList;
    private RunInfo mRunInfo;
    private int mSportType;
    private LatLng mStartLatLng;
    StopProgressButton mStopProgressButtonStop;
    StopProgressButton mStopProgressButtonUnlock;
    private int mUnit;
    private SportRunningViewModel mViewModel;
    double m_Latitude;
    double m_Longitude;
    private int sportCalorie;
    private int sportDistance;
    private int sportStep;
    private SportTabItem sportTabItem;
    private int startCalorie;
    private float startDistance;
    private int startSportStep;
    private Timer timer;
    TextView tvClock;
    TextView tvFirstValue;
    TextView tvFourthlyValue;
    TextView tvMotorPattern;
    TextView tvSecondUnit;
    TextView tvSecondValue;
    TextView tvStepValue;
    TextView tvThirdlyValue;
    View vBottomSpace;
    private boolean isRun = false;
    private int LOCK = 0;
    private int SPORT_STATE = 0;
    Boolean UpdataLocationEnable = false;
    List<LatLng> plist = new ArrayList();
    private Map<String, Object> objects = new HashMap();
    private final int minTime = 60;
    private boolean isFirst = true;
    private String title = "";
    private SportViewModel mSportViewModel = null;
    private boolean isTimerStart = false;
    private boolean isRideHeartRateStart = false;
    private ArrayList<Integer> mHrList = new ArrayList<>();
    private Handler handler = new Handler(new Handler.Callback() { // from class: com.yucheng.smarthealthpro.sport.activity.SportRunningActivity.1
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message msg) {
            int i2 = msg.what;
            if (i2 == 0) {
                SportRunningActivity.this.updateUI();
                SportRunningActivity.this.objects.put("smsg", SportRunningActivity.this.mRunInfo);
                SubObserver.getInstance().nodifyObservers(SportRunningActivity.this.objects);
                return false;
            }
            if (i2 != 1) {
                if (i2 != 2) {
                    return false;
                }
                SportRunningActivity.this.refreshTime(2);
                return false;
            }
            if (SportRunningActivity.this.SPORT_STATE != 0) {
                return false;
            }
            SportRunningActivity.this.stopRun();
            return false;
        }
    });
    private int postDelayed = 2;
    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: com.yucheng.smarthealthpro.sport.activity.SportRunningActivity.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            int intExtra = intent.getIntExtra("type", 0);
            if (intExtra != 1 && intExtra == 2) {
                if (YCBTClient.connectState() != 10) {
                    SportRunningActivity.this.mRunInfo.runTime++;
                    SportRunningActivity.this.makeTime();
                } else if (!YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASREALEXERCISEDATA) && SportRunningActivity.this.SPORT_STATE != 3) {
                    if (SportRunningActivity.this.postDelayed > 0) {
                        SportRunningActivity.this.postDelayed--;
                    } else {
                        SportRunningActivity.this.mRunInfo.runTime++;
                        SportRunningActivity.this.makeTime();
                    }
                }
                SportRunningActivity.this.objects.put("smsg", SportRunningActivity.this.mRunInfo);
                SubObserver.getInstance().nodifyObservers(SportRunningActivity.this.objects);
            }
        }
    };
    Runnable reconnectCheck = new Runnable() { // from class: com.yucheng.smarthealthpro.sport.activity.SportRunningActivity.12
        @Override // java.lang.Runnable
        public void run() {
            if (YCBTClient.connectState() == 10) {
                return;
            }
            EventBusMessageEvent eventBusMessageEvent = new EventBusMessageEvent();
            eventBusMessageEvent.belState = 0;
            eventBusMessageEvent.connectState = 3;
            EventBus.getDefault().post(eventBusMessageEvent);
            SportRunningActivity.this.SPORT_STATE = 0;
            SportRunningActivity.this.changedSport();
            SportRunningActivity.this.finish();
        }
    };
    private MBroadcastReceiver mBroadcastReceiver = new MBroadcastReceiver();

    private boolean checkPlayServices() {
        return true;
    }

    public void registerRealData() {
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TimeUploadTask.INSTANCE.setSportRunning(true);
        SubObserver.getInstance().addObs(this);
        EventBus.getDefault().register(this);
        refRev();
        runRev();
        initView();
        initViewModel();
        initData();
        hideBottomUIMenu();
    }

    private void setViewWidth(View view) {
        int screenSizeWidth = DpUtil.getScreenSizeWidth(this);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = (screenSizeWidth / 3) - 36;
        view.setLayoutParams(layoutParams);
    }

    private void runRev() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.health.RunService.Rev");
        ActivityCompat.registerReceiver(this, this.broadcastReceiver, intentFilter, 2);
    }

    public void refreshTime(int type) {
        if (type != 1 && type == 2) {
            if (YCBTClient.connectState() != 10 || YCBTClient.isSupportFunction(Constants.FunctionConstant.IS_MOTION_DELAY_DISCONNECT)) {
                int i2 = this.postDelayed;
                if (i2 > 0) {
                    this.postDelayed = i2 - 1;
                    return;
                }
                this.mRunInfo.runTime++;
                makeTime();
                return;
            }
            if (!YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASREALEXERCISEDATA) && this.SPORT_STATE != 3) {
                int i3 = this.postDelayed;
                if (i3 > 0) {
                    this.postDelayed = i3 - 1;
                    return;
                }
                this.mRunInfo.runTime++;
                makeTime();
                return;
            }
            if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASREALEXERCISEDATA)) {
                int i4 = this.postDelayed;
                if (i4 > 0) {
                    this.postDelayed = i4 - 1;
                    return;
                }
                this.mRunInfo.runTime++;
                makeTime();
            }
        }
    }

    private void initView() {
        this.tvMotorPattern = ((ActivitySportrunningBinding) this.mBinding).tvMotorPattern;
        this.tvClock = ((ActivitySportrunningBinding) this.mBinding).tvClock;
        this.tvFirstValue = ((ActivitySportrunningBinding) this.mBinding).tvFirstValue;
        this.tvSecondValue = ((ActivitySportrunningBinding) this.mBinding).tvSecondValue;
        this.tvThirdlyValue = ((ActivitySportrunningBinding) this.mBinding).tvThirdlyValue;
        this.tvFourthlyValue = ((ActivitySportrunningBinding) this.mBinding).tvFourthlyValue;
        this.tvStepValue = ((ActivitySportrunningBinding) this.mBinding).tvStepValue;
        this.llThirdlyFourthly = ((ActivitySportrunningBinding) this.mBinding).llThirdlyFourthly;
        this.tvSecondUnit = ((ActivitySportrunningBinding) this.mBinding).tvSecondUnit;
        this.ivLock = ((ActivitySportrunningBinding) this.mBinding).ivLock;
        this.ivMap = ((ActivitySportrunningBinding) this.mBinding).ivMap;
        this.ivStartStop = ((ActivitySportrunningBinding) this.mBinding).ivStartStop;
        this.vBottomSpace = ((ActivitySportrunningBinding) this.mBinding).vBottomSpace;
        this.ivStop = ((ActivitySportrunningBinding) this.mBinding).ivStop;
        this.llRunning = ((ActivitySportrunningBinding) this.mBinding).llRunning;
        this.ivUnlock = ((ActivitySportrunningBinding) this.mBinding).ivUnlock;
        this.llLockBg = ((ActivitySportrunningBinding) this.mBinding).llLockBg;
        this.mStopProgressButtonStop = ((ActivitySportrunningBinding) this.mBinding).pbStop;
        this.mStopProgressButtonUnlock = ((ActivitySportrunningBinding) this.mBinding).pbUnlock;
        this.llFirst = ((ActivitySportrunningBinding) this.mBinding).llFirst;
        this.llStep = ((ActivitySportrunningBinding) this.mBinding).llStep;
        this.llThirdly = ((ActivitySportrunningBinding) this.mBinding).llThirdly;
        this.llSecond = ((ActivitySportrunningBinding) this.mBinding).llSecond;
        this.llFourthly = ((ActivitySportrunningBinding) this.mBinding).llFourthly;
        this.ivLock.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.sport.activity.SportRunningActivity$$ExternalSyntheticLambda2
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.ivMap.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.sport.activity.SportRunningActivity$$ExternalSyntheticLambda2
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.ivStartStop.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.sport.activity.SportRunningActivity$$ExternalSyntheticLambda2
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.ivStop.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.sport.activity.SportRunningActivity$$ExternalSyntheticLambda2
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.ivUnlock.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.sport.activity.SportRunningActivity$$ExternalSyntheticLambda2
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.llLockBg.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.sport.activity.SportRunningActivity$$ExternalSyntheticLambda2
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        setViewWidth(this.llFirst);
        setViewWidth(this.llStep);
        setViewWidth(this.llThirdly);
        setViewWidth(this.llSecond);
        setViewWidth(this.llFourthly);
        changeTitle(getString(R.string.sport_running_title));
        showBack();
        showLeftImage(R.mipmap.topbar_ic_back, new NavigationBar.MyOnClickListener() { // from class: com.yucheng.smarthealthpro.sport.activity.SportRunningActivity.3
            @Override // com.yucheng.smarthealthpro.framework.view.NavigationBar.MyOnClickListener
            public void onClick(View btn) {
                if (SportRunningActivity.this.mRunInfo.runTime < 60) {
                    SportRunningActivity.this.initDialog();
                } else {
                    SportRunningActivity.this.initIsFinishDialog();
                }
            }
        });
        showBgLine(false);
        setTitleBarBackgroundColor("#3B544B");
        String str = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.UNIT, "");
        if ((str == null || !str.equals(Constant.SpConstValue.ISO)) && str != null && str.equals(Constant.SpConstValue.INCH)) {
            this.mUnit = 1;
        } else {
            this.mUnit = 0;
        }
        SportTabItem sportTabItem = (SportTabItem) getIntent().getSerializableExtra("data");
        this.sportTabItem = sportTabItem;
        this.title = sportTabItem.title;
        this.mSportType = this.sportTabItem.sportType;
        this.tvMotorPattern.setText(this.title);
        if (this.sportTabItem.hasStep) {
            this.llStep.setVisibility(0);
        }
        int i2 = this.mSportType;
        if (i2 == 1 || i2 == 3 || i2 == 8 || i2 == 16 || i2 == 11 || i2 == 15) {
            this.isMap = 1;
            startLocal();
        } else {
            this.isMap = 2;
            this.ivMap.setVisibility(8);
            this.llThirdlyFourthly.setVisibility(8);
            this.tvSecondUnit.setText(R.string.sport_running_tv_thirdly_value_unit);
        }
        registerRealData();
        RunInfo runInfo = new RunInfo();
        this.mRunInfo = runInfo;
        runInfo.type = this.mSportType;
        this.SPORT_STATE = 1;
        changedSport();
        setSportState();
        this.mStopProgressButtonStop.setListener(new StopProgressButton.ProgressButtonFinishCallback() { // from class: com.yucheng.smarthealthpro.sport.activity.SportRunningActivity.4
            @Override // com.yucheng.smarthealthpro.sport.view.StopProgressButton.ProgressButtonFinishCallback
            public void onCancel() {
            }

            @Override // com.yucheng.smarthealthpro.sport.view.StopProgressButton.ProgressButtonFinishCallback
            public void onFinish() {
                Log.i("AAAAAAAA", "===完成===");
                if (SportRunningActivity.this.mRunInfo.runTime < 60) {
                    SportRunningActivity.this.initDialog();
                } else {
                    SportRunningActivity.this.initIsFinishDialog();
                }
            }
        });
        this.mStopProgressButtonUnlock.setListener(new StopProgressButton.ProgressButtonFinishCallback() { // from class: com.yucheng.smarthealthpro.sport.activity.SportRunningActivity.5
            @Override // com.yucheng.smarthealthpro.sport.view.StopProgressButton.ProgressButtonFinishCallback
            public void onCancel() {
            }

            @Override // com.yucheng.smarthealthpro.sport.view.StopProgressButton.ProgressButtonFinishCallback
            public void onFinish() {
                SportRunningActivity.this.ivLock.setBackground(ResourcesCompat.getDrawable(SportRunningActivity.this.getResources(), R.mipmap.icon_sp_bg_unlock, null));
                SportRunningActivity.this.LOCK = 0;
                SportRunningActivity.this.llLockBg.setVisibility(8);
                if (SportRunningActivity.this.isShowPause()) {
                    SportRunningActivity.this.ivStartStop.setVisibility(0);
                    SportRunningActivity.this.vBottomSpace.setVisibility(0);
                }
                SportRunningActivity.this.ivStop.setVisibility(0);
            }
        });
        if (isShowPause()) {
            this.ivStartStop.setVisibility(0);
            this.vBottomSpace.setVisibility(0);
        } else {
            this.ivStartStop.setVisibility(8);
            this.vBottomSpace.setVisibility(8);
        }
    }

    private void initViewModel() {
        this.mViewModel = (SportRunningViewModel) new ViewModelProvider(this).get(SportRunningViewModel.class);
        FlowUtils.INSTANCE.collectFlow(this, this.mViewModel.getSaveResultFlow(), new FlowUtils.FlowCollector<HealthResult<Boolean>>() { // from class: com.yucheng.smarthealthpro.sport.activity.SportRunningActivity.6
            @Override // com.yucheng.smarthealthpro.utils.FlowUtils.FlowCollector
            public void collect(HealthResult<Boolean> result) {
                SportRunningActivity.this.onSaveResult(result.getValue().booleanValue());
            }
        });
    }

    private void initData() {
        SportViewModel sportViewModel = (SportViewModel) MyApplication.sInstance.getAppViewModel(SportViewModel.class);
        this.mSportViewModel = sportViewModel;
        sportViewModel.updateSportMode(true);
    }

    private void initGpsDialog() {
        final CommonDialog commonDialog = new CommonDialog(this);
        commonDialog.setMessage(getString(R.string.sport_running_gps_dialog_message)).setTitle(getString(R.string.prompt)).setSingle(true).setOnClickBottomListener(new CommonDialog.OnClickBottomListener() { // from class: com.yucheng.smarthealthpro.sport.activity.SportRunningActivity.7
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

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isShowPause() {
        if (YCBTClient.connectState() == 10) {
            return YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASPAUSEEXERCISE);
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initDialog() {
        try {
            final CommonDialog commonDialog = new CommonDialog(this);
            commonDialog.setMessage(getString(R.string.sport_running_sec_dialog_message)).setTitle(getString(R.string.prompt)).setConfirm(getString(R.string.sport_running_sec_dialog_confirm)).setCancel(getString(R.string.sport_running_sec_dialog_cancel)).setSingle(false).setOnClickBottomListener(new CommonDialog.OnClickBottomListener() { // from class: com.yucheng.smarthealthpro.sport.activity.SportRunningActivity.8
                @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
                public void onConfirmClick() {
                    commonDialog.dismiss();
                }

                @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
                public void onCancelClick() {
                    SportRunningActivity.this.SPORT_STATE = 0;
                    commonDialog.dismiss();
                    SportRunningActivity.this.changedSport();
                }

                @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
                public void onEditTextConfirmClick(String mEditText) {
                    commonDialog.dismiss();
                }
            }).show();
        } catch (Exception e2) {
            e2.printStackTrace();
            CrashReport.postCatchedException(e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initIsFinishDialog() {
        final CommonDialog commonDialog = new CommonDialog(this);
        commonDialog.setMessage(getString(R.string.sport_running_sec_dialog_enable_message)).setTitle(getString(R.string.prompt)).setConfirm(getString(R.string.sport_running_sec_dialog_confirm)).setCancel(getString(R.string.sport_running_sec_dialog_cancel)).setSingle(false).setOnClickBottomListener(new CommonDialog.OnClickBottomListener() { // from class: com.yucheng.smarthealthpro.sport.activity.SportRunningActivity.9
            @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
            public void onConfirmClick() {
                commonDialog.dismiss();
            }

            @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
            public void onCancelClick() {
                SportRunningActivity.this.SPORT_STATE = 0;
                commonDialog.dismiss();
                SportRunningActivity.this.changedSport();
            }

            @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
            public void onEditTextConfirmClick(String mEditText) {
                commonDialog.dismiss();
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void changedSport() {
        int i2 = this.SPORT_STATE;
        if ((i2 == 0 || i2 == 1 || i2 == 2 || i2 == 3) && !isFinishing()) {
            int i3 = this.SPORT_STATE;
            if (i3 == 1 && this.isRun) {
                this.ivStartStop.setBackground(ResourcesCompat.getDrawable(getResources(), R.mipmap.icon_sp_bot_stop, null));
                return;
            }
            if (i3 == 2 && !YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASPAUSEEXERCISE) && YCBTClient.connectState() == 10) {
                return;
            }
            if (YCBTClient.connectState() == 10) {
                this.handler.removeMessages(1);
                this.handler.sendEmptyMessageDelayed(1, 2000L);
                YCBTClient.appRunMode(this.SPORT_STATE, this.mSportType, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.sport.activity.SportRunningActivity.10
                    @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                    public void onDataResponse(int i4, float v, HashMap hashMap) {
                        SportRunningActivity.this.handler.removeMessages(1);
                        if (SportRunningActivity.this.isFinishing() || i4 != 0) {
                            return;
                        }
                        SportRunningActivity.this.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.sport.activity.SportRunningActivity.10.1
                            @Override // java.lang.Runnable
                            public void run() {
                                SportRunningActivity.this.setSportState();
                            }
                        });
                    }
                });
                return;
            }
            setSportState();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSportState() {
        try {
            int i2 = this.SPORT_STATE;
            if (i2 == 0) {
                stopRun();
            } else if (i2 == 1) {
                this.ivStartStop.setBackground(ResourcesCompat.getDrawable(getResources(), R.mipmap.icon_sp_bot_stop, null));
                this.mRunInfo.beginDate = System.currentTimeMillis();
                this.postDelayed = 2;
                this.isRun = true;
                run(this.isMap);
            } else if (i2 == 2) {
                this.ivStartStop.setBackground(ResourcesCompat.getDrawable(getResources(), R.mipmap.icon_sp_bot_start, null));
                run(4);
            } else if (i2 == 3) {
                this.ivStartStop.setBackground(ResourcesCompat.getDrawable(getResources(), R.mipmap.icon_sp_bot_stop, null));
                run(5);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            CrashReport.postCatchedException(e2);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(RealDataResponse realDataResponse) {
        int i2 = realDataResponse.f5705i;
        HashMap map = realDataResponse.hashMap;
        if (map == null) {
            return;
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASREALEXERCISEDATA)) {
            if (i2 == 1549) {
                if (this.mSportType != 3) {
                    this.mRunInfo.distance = ((Integer) map.get("sportsRealDistance")).intValue();
                    this.mRunInfo.calorie = ((Integer) map.get("sportsRealCalories")).intValue();
                }
                if (this.isTimerStart) {
                    stopCount();
                }
                this.mRunInfo.runTime = ((Integer) map.get("recordTime")).intValue();
                this.mRunInfo.heart = ((Integer) map.get("heartRate")).intValue();
                if (TransUtils.isAvailableHr(this.mRunInfo.heart)) {
                    if (!this.isRideHeartRateStart) {
                        this.isRideHeartRateStart = true;
                    }
                    this.mHrList.add(Integer.valueOf(this.mRunInfo.heart));
                }
                insertMotionPattern(this.mRunInfo.heart);
                this.sportStep = ((Integer) map.get("sportsRealSteps")).intValue();
                MLog.INSTANCE.d("onEvent: runInfo:" + this.mRunInfo);
                this.handler.sendEmptyMessage(0);
                return;
            }
            return;
        }
        if (i2 != 1536) {
            if (i2 == 1537) {
                Log.i(TAG, i2 + "--onRealDataResponse--" + map.toString());
                int iIntValue = ((Integer) map.get("heartValue")).intValue();
                this.heartValue = iIntValue;
                this.mRunInfo.heart = iIntValue;
                this.mHrList.add(Integer.valueOf(this.mRunInfo.heart));
                insertMotionPattern(this.mRunInfo.heart);
                this.handler.sendEmptyMessage(0);
                return;
            }
            return;
        }
        if (this.isFirst) {
            this.startDistance = ((Integer) map.get("sportDistance")).intValue();
            this.startCalorie = ((Integer) map.get("sportCalorie")).intValue();
            this.startSportStep = ((Integer) map.get("sportStep")).intValue();
            this.isFirst = false;
        }
        this.sportStep = ((Integer) map.get("sportStep")).intValue();
        this.sportDistance = ((Integer) map.get("sportDistance")).intValue();
        this.sportCalorie = ((Integer) map.get("sportCalorie")).intValue();
        this.mRunInfo.distance = this.sportDistance - this.startDistance;
        this.mRunInfo.calorie = this.sportCalorie - this.startCalorie;
        this.sportStep -= this.startSportStep;
        this.handler.sendEmptyMessage(0);
    }

    public void insertMotionPattern(int heartRate) {
        this.mViewModel.saveMotionPattern(new SaveMotionPattern(System.currentTimeMillis(), heartRate, UserInfoUtil.getUserName(), Tools.getDeviceType(this.context), YCBTClient.getBindDeviceMac()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onSaveResult(boolean result) {
        if (result) {
            Logger.d("ltf 运动保存成功");
            this.mRunInfo.type = 0;
            this.mRunInfo.beginDate = 0L;
            this.mRunInfo.distance = 0.0f;
            this.mRunInfo.distances = 0.0f;
            this.mRunInfo.calorie = 0;
            this.mRunInfo.minkm = "";
            this.mRunInfo.heart = 0;
            this.mRunInfo.runTime = 0;
            this.mRunInfo.kmh = 0.0f;
            this.mRunInfo.mapCoordinatesList = "";
            this.mRunInfo.isUpload = false;
            this.sportStep = 0;
            this.sportDistance = 0;
            this.sportCalorie = 0;
            this.plist.clear();
            if (YCBTClient.isSupportFunction(Constants.FunctionConstant.IS_HAS_LOCAL_SPORT_DATA)) {
                DataSyncUtils.INSTANCE.getInstance(getApplicationContext()).getWatchesData(Constants.DATATYPE.Health_HistorySportMode);
            }
            finish();
            return;
        }
        Logger.d("ltf 运动保存失败");
        finish();
    }

    public void stopRun() {
        if (!isFinishing()) {
            this.ivStartStop.setBackground(ResourcesCompat.getDrawable(getResources(), R.mipmap.icon_sp_bot_start, null));
            this.SPORT_STATE = 1;
            this.isRun = false;
            run(3);
        }
        RunInfo runInfo = this.mRunInfo;
        if (runInfo != null && runInfo.runTime >= 60) {
            MLog.INSTANCE.d("stopRun: runInfo:" + this.mRunInfo);
            IntSummaryStatistics intSummaryStatisticsSummaryStatistics = this.mHrList.stream().mapToInt(new ToIntFunction() { // from class: com.yucheng.smarthealthpro.sport.activity.SportRunningActivity$$ExternalSyntheticLambda1
                @Override // java.util.function.ToIntFunction
                public final int applyAsInt(Object obj) {
                    return ((Integer) obj).intValue();
                }
            }).summaryStatistics();
            int sum = intSummaryStatisticsSummaryStatistics.getCount() > 0 ? (int) (intSummaryStatisticsSummaryStatistics.getSum() / intSummaryStatisticsSummaryStatistics.getCount()) : 0;
            MLog.INSTANCE.d("averageHr: " + sum + "  count:" + intSummaryStatisticsSummaryStatistics.getCount());
            MLog.INSTANCE.d("sportCalorie: " + this.sportCalorie + "  mRunInfo.calorie:" + this.mRunInfo.calorie);
            this.mViewModel.saveSportRecord(new SaveSportRecord(this.mSportType, this.mRunInfo.beginDate, TimeStampUtils.dateForStringYearToDate(TimeStampUtils.longStampForDate(this.mRunInfo.beginDate)), this.sportStep, this.mRunInfo.distance, this.sportDistance, this.mRunInfo.calorie, this.sportCalorie, this.mRunInfo.minkm, sum, this.mRunInfo.runTime, this.mRunInfo.kmh, new Gson().toJson(this.mStartLatLng), new Gson().toJson(this.mEndLatLng), this.mAMapLocationString, UserInfoUtil.getUserName(), Tools.getDeviceType(this.context), YCBTClient.getBindDeviceMac(), true));
            return;
        }
        finish();
    }

    private void goToMap() {
        if (GoogleUtil.checkGoogleAvailable()) {
            Intent intent = new Intent(this, (Class<?>) SportRunningGoogleMapActivity.class);
            intent.putExtra("runInfo", this.mRunInfo);
            intent.putExtra("from", 1);
            intent.putParcelableArrayListExtra("plist", (ArrayList) this.plist);
            startActivity(intent);
            return;
        }
        Intent intent2 = new Intent(this, (Class<?>) SportRunningMapActivity.class);
        intent2.putExtra("runInfo", this.mRunInfo);
        intent2.putExtra("from", 1);
        intent2.putParcelableArrayListExtra("plist", (ArrayList) this.plist);
        startActivity(intent2);
    }

    public boolean isGoogleMapsInstalled() throws PackageManager.NameNotFoundException {
        try {
            getPackageManager().getApplicationInfo("com.google.android.apps.maps", 0);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.core.app.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == 4) {
            if (this.LOCK == 1) {
                return true;
            }
            return super.dispatchKeyEvent(event);
        }
        return super.dispatchKeyEvent(event);
    }

    public void onViewClicked(View view) {
        if (view.getId() == R.id.iv_lock) {
            if (this.LOCK == 0) {
                this.ivLock.setBackground(ResourcesCompat.getDrawable(getResources(), R.mipmap.icon_sp_bg_lock, null));
                this.llLockBg.setVisibility(0);
                if (isShowPause()) {
                    this.ivStartStop.setVisibility(8);
                    this.vBottomSpace.setVisibility(8);
                }
                this.ivStop.setVisibility(8);
                this.LOCK = 1;
                return;
            }
            return;
        }
        if (view.getId() == R.id.iv_map) {
            goToMap();
            return;
        }
        if (view.getId() == R.id.iv_start_stop) {
            int i2 = this.SPORT_STATE;
            if (i2 == 1) {
                this.SPORT_STATE = 2;
            } else if (i2 == 2) {
                this.SPORT_STATE = 3;
            } else if (i2 == 3) {
                this.SPORT_STATE = 2;
            }
            changedSport();
            return;
        }
        view.getId();
        int i3 = R.id.ll_lock_bg;
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
        TextView textView = this.tvClock;
        if (textView != null) {
            textView.setText(str3);
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == 4) {
            if (this.mRunInfo.runTime < 60) {
                initDialog();
                return true;
            }
            initIsFinishDialog();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.mSportViewModel.updateSportMode(false);
        TimeUploadTask.INSTANCE.setSportRunning(false);
        unregisterReceiver(this.broadcastReceiver);
        unregisterReceiver(this.mBroadcastReceiver);
        stoprRun();
        EventBus.getDefault().unregister(this);
        SubObserver.getInstance().delObs(this);
        Timer timer = this.timer;
        if (timer != null) {
            timer.cancel();
            this.timer = null;
        }
    }

    @Override // java.util.Observer
    public void update(Observable o, Object arg) {
        Map map = (Map) arg;
        if (map.get("latLngs") == null) {
            return;
        }
        try {
            this.mLatLngList = (List) map.get("latLngs");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        List<LatLng> list = this.mLatLngList;
        if (list == null || list.size() <= 0) {
            return;
        }
        this.mAMapLocationString = new Gson().toJson(this.mLatLngList);
        this.mStartLatLng = new LatLng(this.mLatLngList.get(0).latitude, this.mLatLngList.get(0).longitude);
        List<LatLng> list2 = this.mLatLngList;
        boolean z = true;
        double d2 = list2.get(list2.size() - 1).latitude;
        List<LatLng> list3 = this.mLatLngList;
        this.mEndLatLng = new LatLng(d2, list3.get(list3.size() - 1).longitude);
        if (isFinishing()) {
            return;
        }
        if ((YCBTClient.connectState() == 10 && this.mSportType != 3) || this.mEndLatLng == null || this.mRunInfo.runTime == 0) {
            return;
        }
        if (this.lastPoint != null) {
            float distance = (float) LocationUtils.getDistance(this.mEndLatLng.latitude, this.mEndLatLng.longitude, this.lastPoint.latitude, this.lastPoint.longitude);
            Logger.d("distance:" + distance);
            if (distance >= 2.0f) {
                if (YCBTClient.connectState() == 10 && YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASREALEXERCISEDATA)) {
                    z = this.isRideHeartRateStart;
                }
                if (getString(R.string.sport_riding).equals(this.title) && z && distance <= TransUtils.RIDE_MAX_DISTANCE_CHANGE) {
                    this.mRunInfo.distance += distance;
                } else {
                    this.mRunInfo.distance += distance;
                }
                Logger.d("update distance==" + this.mRunInfo.distance);
                if (getString(R.string.sport_running).equals(this.title)) {
                    RunInfo runInfo = this.mRunInfo;
                    runInfo.calorie = ((int) ((runInfo.distance * 60.0f) * 1.036f)) / 1000;
                } else if (getString(R.string.sport_riding).equals(this.title)) {
                    RunInfo runInfo2 = this.mRunInfo;
                    runInfo2.calorie = ((int) ((runInfo2.distance * 60.0f) * 0.8214f)) / 1000;
                } else {
                    finish();
                    return;
                }
                this.lastPoint = new LatLng(this.mEndLatLng.latitude, this.mEndLatLng.longitude);
            }
        } else {
            this.lastPoint = new LatLng(this.mEndLatLng.latitude, this.mEndLatLng.longitude);
        }
        this.handler.sendEmptyMessage(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateUI() {
        makeTime();
        this.tvFirstValue.setText(this.mRunInfo.heart == 0 ? "--" : this.mRunInfo.heart + "");
        this.tvStepValue.setText("" + this.sportStep);
        try {
            RunInfo runInfo = this.mRunInfo;
            runInfo.distances = FormatUtil.keep2F(runInfo.distance / 1000.0f);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        this.mRunInfo.makeKmh();
        if (this.isMap == 2) {
            this.tvSecondValue.setText(this.mRunInfo.calorie + "");
        } else {
            if (this.mUnit == 0) {
                this.tvSecondValue.setText(String.format("%.2f", Float.valueOf(this.mRunInfo.distance / 1000.0f)) + "");
            } else {
                this.tvSecondValue.setText(String.format("%.2f", Float.valueOf(this.mRunInfo.distance / 1609.344f)) + "");
            }
            this.tvFourthlyValue.setText(this.mRunInfo.minkm + "");
        }
        this.tvThirdlyValue.setText(this.mRunInfo.calorie + "");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void exerciseState(EventBusExitExerciseEvent eventBusExitExerciseEvent) throws Resources.NotFoundException {
        int i2 = eventBusExitExerciseEvent.sportState;
        if (eventBusExitExerciseEvent.sportType != this.mSportType) {
            return;
        }
        if (i2 == 0 || i2 == 1 || i2 == 2 || i2 == 3) {
            this.SPORT_STATE = i2;
            if (i2 == 0) {
                ToastUtil.getInstance(getApplicationContext()).toast(R.string.sport_running_sec_dialog_cancel);
                this.objects.put("SPORT_STATE", Integer.valueOf(i2));
                SubObserver.getInstance().nodifyObservers(this.objects);
            }
            setSportState();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getCompile(EventBusMessageEvent messageEvent) {
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.IS_MOTION_DELAY_DISCONNECT) || Constant.isHealthRing()) {
            if (messageEvent.belState == 0 && !this.isTimerStart) {
                startTimer();
            }
        } else if (messageEvent.belState == 0) {
            this.SPORT_STATE = 0;
            changedSport();
            finish();
        }
        if (messageEvent.belState == 1 && YCBTClient.connectState() == 10) {
            YCBTClient.appRealDataReport(1, 1, 2, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.sport.activity.SportRunningActivity.11
                @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                public void onDataResponse(int i2, float v, HashMap hashMap) {
                    if (i2 == 0) {
                        Logger.d("重连-开启运动上报");
                    }
                }
            });
        }
    }

    public void refRev() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.health.RunService.Send");
        ActivityCompat.registerReceiver(this, this.mBroadcastReceiver, intentFilter, 2);
    }

    public class MBroadcastReceiver extends BroadcastReceiver {
        public MBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            int intExtra = intent.getIntExtra("run", -1);
            if (intExtra == 1) {
                SportRunningActivity.this.beginRun();
                return;
            }
            if (intExtra == 2) {
                SportRunningActivity.this.beginTimeRun();
                return;
            }
            if (intExtra == 3) {
                SportRunningActivity.this.stoprRun();
            } else if (intExtra == 4) {
                SportRunningActivity.this.pauseRun();
            } else {
                if (intExtra != 5) {
                    return;
                }
                SportRunningActivity.this.continueRun();
            }
        }
    }

    public void run(int type) {
        if (type == 1) {
            beginRun();
            return;
        }
        if (type == 2) {
            beginTimeRun();
            return;
        }
        if (type == 3) {
            stoprRun();
        } else if (type == 4) {
            pauseRun();
        } else {
            if (type != 5) {
                return;
            }
            continueRun();
        }
    }

    public void beginRun() {
        startLocal();
        this.isRun = true;
        startCount();
    }

    public void beginTimeRun() {
        this.isRun = true;
        startCount();
    }

    public synchronized void stoprRun() {
        this.isRun = false;
        stopLocal();
        stopCount();
    }

    public void pauseRun() {
        this.isRun = false;
        LocationAMapUtils.getInstance().stopLocalService();
        stopCount();
    }

    public void continueRun() {
        this.isRun = true;
        startCount();
        LocationAMapUtils.getInstance().startLocalService();
    }

    public synchronized void startLocal() {
        LocationAMapUtils.getInstance().startLocalService();
        new Thread(new Runnable() { // from class: com.yucheng.smarthealthpro.sport.activity.SportRunningActivity$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                AITools.getInstance().initGps();
            }
        }).start();
    }

    public synchronized void stopLocal() {
        LocationAMapUtils.getInstance().stopLocalService();
        new Thread(new Runnable() { // from class: com.yucheng.smarthealthpro.sport.activity.SportRunningActivity$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                AITools.getInstance().freeGps();
            }
        }).start();
    }

    private void startCount() {
        boolean zIsSupportFunction = YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASREALEXERCISEDATA);
        if (YCBTClient.connectState() == 10 && zIsSupportFunction) {
            return;
        }
        startTimer();
    }

    private void startTimer() {
        this.isTimerStart = true;
        this.postDelayed = 0;
        Timer timer = this.timer;
        if (timer != null) {
            timer.cancel();
            this.timer = null;
        }
        Timer timer2 = new Timer();
        this.timer = timer2;
        timer2.schedule(new TimerTask() { // from class: com.yucheng.smarthealthpro.sport.activity.SportRunningActivity.13
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                if (SportRunningActivity.this.isRun) {
                    SportRunningActivity.this.handler.sendEmptyMessage(2);
                }
            }
        }, 1000L, 1000L);
    }

    private void stopCount() {
        this.isTimerStart = false;
        Timer timer = this.timer;
        if (timer != null) {
            timer.cancel();
            this.timer = null;
        }
        this.handler.removeMessages(2);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSyncEvent(EventBusSyncData eventBusSyncData) {
        if (eventBusSyncData.isSyncSuccess) {
            int i2 = eventBusSyncData.type;
        }
    }
}
