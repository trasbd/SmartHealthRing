package com.yucheng.smarthealthpro.home.activity.ecg.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.lifecycle.ViewModelProvider;
import com.alibaba.fastjson2.internal.asm.Opcodes;
import com.google.android.material.timepicker.TimeModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.orhanobut.logger.Logger;
import com.tencent.bugly.crashreport.BuglyLog;
import com.tencent.bugly.crashreport.CrashReport;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.data.packed.HealthResult;
import com.yucheng.smarthealthpro.database.room.bean.EcgMeasure;
import com.yucheng.smarthealthpro.databinding.ActivityEcgmeasureBinding;
import com.yucheng.smarthealthpro.framework.http.HttpUtils;
import com.yucheng.smarthealthpro.framework.util.Constants;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.framework.util.ToastUtil;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import com.yucheng.smarthealthpro.home.activity.ecg.bean.AIDiagnosisResultBean;
import com.yucheng.smarthealthpro.home.bean.RealDataResponse;
import com.yucheng.smarthealthpro.home.view.CardiographView4;
import com.yucheng.smarthealthpro.home.view.EcgMeasurDialog;
import com.yucheng.smarthealthpro.home.view.EcgMeasurRingDialog;
import com.yucheng.smarthealthpro.login.normal.util.UserInfoUtil;
import com.yucheng.smarthealthpro.sport.view.CommonDialog;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.DebouncingClick;
import com.yucheng.smarthealthpro.utils.EventBusEcgEnd;
import com.yucheng.smarthealthpro.utils.EventBusMessageEvent;
import com.yucheng.smarthealthpro.utils.FlowUtils;
import com.yucheng.smarthealthpro.utils.MLog;
import com.yucheng.smarthealthpro.utils.TimeStampUtils;
import com.yucheng.smarthealthpro.utils.Tools;
import com.yucheng.smarthealthpro.utils.YearToDayListUtils;
import com.yucheng.smarthealthpro.viewmodel.EcgViewModel;
import com.yucheng.ycbtsdk.AITools;
import com.yucheng.ycbtsdk.YCBTClient;
import com.yucheng.ycbtsdk.bean.AIDataBean;
import com.yucheng.ycbtsdk.bean.HealthNormBean;
import com.yucheng.ycbtsdk.response.BleAIDiagnosisResponse;
import com.yucheng.ycbtsdk.response.BleDataResponse;
import com.yucheng.ycbtsdk.response.BleRealDataResponse;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.StringUtils;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes5.dex */
public class EcgMeasureActivity extends BaseVbActivity<ActivityEcgmeasureBinding> {
    public static final String TAG = "EcgMeasureActivity";
    private static EcgMeasureActivity ecgMeasureActivity;
    private HealthNormBean ecgData;
    private boolean isAfib;
    ImageView ivStop;
    LinearLayout llElectricOff;
    LinearLayout llElectricOn;
    CardiographView4 mCardiographView;
    private int mDBP;
    private int mDiagnoseType;
    private int mHRV;
    private int mHeart;
    private long mMeasureTime;
    private MediaPlayer mMediaPlay;
    ProgressBar mProgressBar;
    TextView mProgressBarText;
    private int mSBP;
    private EcgViewModel mViewModel;
    ProgressDialog progressDialog;
    TextView tvBpm;
    TextView tvHrv;
    TextView tvMmhg;
    TextView tvStartFinish;
    boolean isResume = true;
    boolean isHasDiagnosis = false;
    private boolean mStatusFlag = false;
    private int count = 0;
    private boolean isStart = false;
    private List<Integer> mEcgMeasureList = new ArrayList();
    private int index = 0;
    private int mLastUpDataCnt = 0;
    private boolean hrv_is_from_device = false;
    private boolean isProgressBar = true;
    PowerManager powerManager = null;
    PowerManager.WakeLock wakeLock = null;
    boolean isSendEcgEnd = false;
    int checkConnectMillis = 5000;
    boolean isGetAiResult = false;
    private ScheduledExecutorService mExecutorService = Executors.newScheduledThreadPool(1);
    Handler mHandler = new Handler() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgMeasureActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message msg) throws IllegalStateException {
            super.handleMessage(msg);
            try {
                if (msg.what == 1) {
                    EcgMeasureActivity.this.openEcgMeasure();
                    return;
                }
                if (msg.what == 2) {
                    if (EcgMeasureActivity.this.index < EcgMeasureActivity.this.drawLists.size()) {
                        EcgMeasureActivity ecgMeasureActivity2 = EcgMeasureActivity.this;
                        ecgMeasureActivity2.mLastUpDataCnt = ecgMeasureActivity2.drawLists.size();
                        return;
                    }
                    return;
                }
                if (msg.what == 3) {
                    EcgMeasureActivity.this.mHandler.removeCallbacks(EcgMeasureActivity.this.mProRunnable);
                    EcgMeasureActivity.this.isProgressBar = true;
                    EcgMeasureActivity.this.isStart = false;
                    EcgMeasureActivity.this.count = 0;
                    EcgMeasureActivity.this.index = 0;
                    if (EcgMeasureActivity.this.mProgressBar == null || EcgMeasureActivity.this.isFinishing()) {
                        return;
                    }
                    EcgMeasureActivity.this.mProgressBar.setProgress(EcgMeasureActivity.this.count);
                    EcgMeasureActivity.this.ivStop.setVisibility(8);
                    EcgMeasureActivity.this.mProgressBarText.setVisibility(8);
                    EcgMeasureActivity.this.tvStartFinish.setVisibility(0);
                    EcgMeasureActivity.this.tvStartFinish.setText(EcgMeasureActivity.this.getString(R.string.health_reminder_of_long_sitting_reminder_time_start_title));
                    return;
                }
                if (msg.what == 4) {
                    EcgMeasureActivity.this.llElectricOn.setVisibility(8);
                    EcgMeasureActivity.this.llElectricOff.setVisibility(0);
                    return;
                }
                if (msg.what == 5) {
                    EcgMeasureActivity.this.llElectricOff.setVisibility(8);
                    EcgMeasureActivity.this.llElectricOn.setVisibility(0);
                    return;
                }
                if (msg.what == 6) {
                    if (EcgMeasureActivity.this.progressDialog != null) {
                        EcgMeasureActivity.this.progressDialog.dismiss();
                    }
                    EcgMeasureActivity.this.goEcgAIDiagnosisActivity();
                    return;
                }
                if (msg.what == 7) {
                    EcgMeasureActivity.this.llElectricOn.setVisibility(8);
                    EcgMeasureActivity.this.llElectricOff.setVisibility(8);
                    return;
                }
                if (msg.what == 21) {
                    EcgMeasureActivity.this.setTextViewHrv();
                    return;
                }
                if (msg.what == 22) {
                    if (!EcgMeasureActivity.this.isStart || EcgMeasureActivity.this.mMediaPlay == null || EcgMeasureActivity.this.drawLists.size() <= 200) {
                        return;
                    }
                    EcgMeasureActivity.this.mMediaPlay.seekTo(0);
                    EcgMeasureActivity.this.mMediaPlay.start();
                    return;
                }
                if (msg.what == 23) {
                    if (EcgMeasureActivity.this.tvBpm == null) {
                        return;
                    }
                    EcgMeasureActivity.this.tvBpm.setText(EcgMeasureActivity.this.mHeart == 0 ? "--" : EcgMeasureActivity.this.mHeart + "");
                    EcgMeasureActivity.this.tvMmhg.setText((EcgMeasureActivity.this.mSBP == 0 || EcgMeasureActivity.this.mDBP == 0) ? "--/--" : EcgMeasureActivity.this.mSBP + "/" + EcgMeasureActivity.this.mDBP);
                    EcgMeasureActivity.this.setTextViewHrv();
                    return;
                }
                if (msg.what == 145653) {
                    try {
                        if (EcgMeasureActivity.this.wakeLock == null || !EcgMeasureActivity.this.wakeLock.isHeld()) {
                            return;
                        }
                        EcgMeasureActivity.this.wakeLock.release();
                        return;
                    } catch (Exception e2) {
                        CrashReport.postCatchedException(e2);
                        e2.printStackTrace();
                        return;
                    }
                }
                if (msg.what == 8) {
                    if (EcgMeasureActivity.this.progressDialog == null) {
                        EcgMeasureActivity ecgMeasureActivity3 = EcgMeasureActivity.this;
                        ecgMeasureActivity3.progressDialog = ProgressDialog.show(ecgMeasureActivity3, ecgMeasureActivity3.getString(R.string.prompt), EcgMeasureActivity.this.getString(R.string.being_tested), true, false);
                    } else {
                        EcgMeasureActivity.this.progressDialog.show();
                    }
                }
            } catch (Exception e3) {
                BuglyLog.e(EcgMeasureActivity.TAG, e3.getMessage());
                CrashReport.postCatchedException(e3);
            }
        }
    };
    private long mFrameInterval = 15;
    private Runnable mRefreshRunnable = new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgMeasureActivity.2
        @Override // java.lang.Runnable
        public void run() {
            EcgMeasureActivity.this.mCardiographView.postInvalidate();
            if (EcgMeasureActivity.this.isStart) {
                EcgMeasureActivity.this.mExecutorService.schedule(EcgMeasureActivity.this.mRefreshRunnable, EcgMeasureActivity.this.mFrameInterval, TimeUnit.MILLISECONDS);
            }
            if (EcgMeasureActivity.this.drawLists.size() <= 0 || !EcgMeasureActivity.this.isProgressBar) {
                return;
            }
            EcgMeasureActivity.this.mHandler.post(EcgMeasureActivity.this.mProRunnable);
            EcgMeasureActivity.this.isProgressBar = false;
        }
    };
    Runnable mProRunnable = new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgMeasureActivity.5
        @Override // java.lang.Runnable
        public void run() throws IllegalStateException {
            if (EcgMeasureActivity.this.count == 0) {
                EcgMeasureActivity.this.tvStartFinish.setText(EcgMeasureActivity.this.getString(R.string.health_reminder_of_long_sitting_reminder_time_start_title));
            }
            if (EcgMeasureActivity.this.count < 100 && EcgMeasureActivity.this.isStart) {
                EcgMeasureActivity.this.count++;
                EcgMeasureActivity.this.mProgressBar.setProgress(EcgMeasureActivity.this.count);
                EcgMeasureActivity.this.tvStartFinish.setVisibility(8);
                EcgMeasureActivity.this.mProgressBarText.setVisibility(0);
                EcgMeasureActivity.this.ivStop.setVisibility(0);
                EcgMeasureActivity.this.mProgressBarText.setText(EcgMeasureActivity.this.count + "%");
                EcgMeasureActivity.this.mHandler.postDelayed(EcgMeasureActivity.this.mProRunnable, 600L);
                return;
            }
            if (EcgMeasureActivity.this.count >= 100) {
                EcgMeasureActivity.this.ecgMeasureStop();
            }
        }
    };
    private List<Integer> drawLists = new ArrayList();

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(RealDataResponse realDataResponse) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTextViewHrv() {
        TextView textView = this.tvHrv;
        if (textView == null) {
            return;
        }
        int i2 = this.mHRV;
        if (i2 != 0.0f) {
            if (i2 > 150) {
                this.mHRV = Opcodes.FCMPG;
            }
            textView.setText(String.format(TimeModel.NUMBER_FORMAT, Integer.valueOf(this.mHRV)));
        } else {
            if (AITools.getInstance().getHRV() != 0) {
                int hrv = AITools.getInstance().getHRV();
                this.mHRV = hrv;
                if (hrv > 150) {
                    this.mHRV = Opcodes.FCMPG;
                }
                this.tvHrv.setText(this.mHRV + "");
                return;
            }
            this.tvHrv.setText("--");
        }
    }

    public static EcgMeasureActivity getInstance() {
        return ecgMeasureActivity;
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        PowerManager powerManager = (PowerManager) getSystemService("power");
        this.powerManager = powerManager;
        this.wakeLock = powerManager.newWakeLock(26, "My Lock");
        initView();
        initViewModel();
    }

    private void initView() {
        this.mCardiographView = ((ActivityEcgmeasureBinding) this.mBinding).cardiographView;
        this.llElectricOn = ((ActivityEcgmeasureBinding) this.mBinding).llElectricOn;
        this.llElectricOff = ((ActivityEcgmeasureBinding) this.mBinding).llElectricOff;
        this.tvBpm = ((ActivityEcgmeasureBinding) this.mBinding).tvBpm;
        this.tvMmhg = ((ActivityEcgmeasureBinding) this.mBinding).tvMmhg;
        this.tvHrv = ((ActivityEcgmeasureBinding) this.mBinding).tvHrv;
        this.mProgressBarText = ((ActivityEcgmeasureBinding) this.mBinding).tvSchedule;
        this.mProgressBar = ((ActivityEcgmeasureBinding) this.mBinding).progressBar;
        this.tvStartFinish = ((ActivityEcgmeasureBinding) this.mBinding).tvStartFinish;
        this.ivStop = ((ActivityEcgmeasureBinding) this.mBinding).ivStop;
        this.tvStartFinish.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgMeasureActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.ivStop.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgMeasureActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        changeTitle(getString(R.string.ecg_measure_title));
        showBack();
        showLeftImage(R.mipmap.topbar_ic_back, new NavigationBar.MyOnClickListener() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgMeasureActivity.3
            @Override // com.yucheng.smarthealthpro.framework.view.NavigationBar.MyOnClickListener
            public void onClick(View btn) {
                if (EcgMeasureActivity.this.isStart) {
                    EcgMeasureActivity.this.initDialog();
                } else {
                    EcgMeasureActivity.this.finish();
                }
            }
        });
        int iIntValue = ((Integer) SharedPreferencesUtils.get(getApplicationContext(), Constant.SpConstKey.ecgWearHand, -1)).intValue();
        boolean zBooleanValue = ((Boolean) SharedPreferencesUtils.get(getApplicationContext(), Constant.SpConstKey.ecgWearHint, false)).booleanValue();
        int i2 = R.mipmap.ecg_select_left_hand;
        if (iIntValue == 0) {
            i2 = R.mipmap.ecg_select_left_hand;
        } else if (iIntValue == 1) {
            i2 = R.mipmap.ecg_select_right_hand;
        }
        showRightImage(i2, new NavigationBar.MyOnClickListener() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgMeasureActivity.4
            @Override // com.yucheng.smarthealthpro.framework.view.NavigationBar.MyOnClickListener
            public void onClick(View btn) {
                if (Constant.isRing()) {
                    EcgMeasureActivity.this.showLeftRightRingDialog();
                } else {
                    EcgMeasureActivity.this.showLeftRightDiaLog();
                }
            }
        });
        if (!zBooleanValue) {
            if (Constant.isRing()) {
                showLeftRightRingDialog();
            } else {
                showLeftRightDiaLog();
            }
            SharedPreferencesUtils.put(getApplicationContext(), Constant.SpConstKey.ecgWearHint, true);
            return;
        }
        YCBTClient.settingHandWear(iIntValue, null);
    }

    private void initViewModel() {
        this.mViewModel = (EcgViewModel) new ViewModelProvider(this).get(EcgViewModel.class);
        FlowUtils.INSTANCE.collectFlow(this, this.mViewModel.getSaveResultFlow(), new FlowUtils.FlowCollector<HealthResult<Long>>() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgMeasureActivity.6
            @Override // com.yucheng.smarthealthpro.utils.FlowUtils.FlowCollector
            public void collect(HealthResult<Long> result) {
                MLog.INSTANCE.d("getSaveResultFlow: " + result.getValue());
                EcgMeasureActivity.this.onEcgSaved(result.getValue());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateHandImg(int leftOrRight) {
        if (leftOrRight == 0) {
            setRightImage(R.mipmap.ecg_select_left_hand);
        } else {
            setRightImage(R.mipmap.ecg_select_right_hand);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showLeftRightRingDialog() {
        EcgMeasurRingDialog ecgMeasurRingDialog = new EcgMeasurRingDialog(this.context);
        ecgMeasurRingDialog.onCreateView();
        ecgMeasurRingDialog.setUiBeforShow();
        ecgMeasurRingDialog.setCanceledOnTouchOutside(true);
        ecgMeasurRingDialog.setOnButtonClickListener(new EcgMeasurRingDialog.OnDialogButtonClickListener() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgMeasureActivity.7
            @Override // com.yucheng.smarthealthpro.home.view.EcgMeasurRingDialog.OnDialogButtonClickListener
            public void onLeftClick() {
                SharedPreferencesUtils.put(EcgMeasureActivity.this.getApplicationContext(), Constant.SpConstKey.ecgWearHand, 0);
                EcgMeasureActivity.this.updateHandImg(0);
                YCBTClient.settingHandWear(0, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgMeasureActivity.7.1
                    @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                    public void onDataResponse(int code, float ratio, HashMap resultMap) {
                        if (code == 0) {
                            Log.i("EcgMeasurRingDialog", "你选择的是:左手");
                        }
                    }
                });
            }

            @Override // com.yucheng.smarthealthpro.home.view.EcgMeasurRingDialog.OnDialogButtonClickListener
            public void onRightClick() {
                SharedPreferencesUtils.put(EcgMeasureActivity.this.getApplicationContext(), Constant.SpConstKey.ecgWearHand, 1);
                EcgMeasureActivity.this.updateHandImg(1);
                YCBTClient.settingHandWear(1, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgMeasureActivity.7.2
                    @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                    public void onDataResponse(int code, float ratio, HashMap resultMap) {
                        if (code == 0) {
                            Log.i("EcgMeasurRingDialog", "你选择的是:右手");
                        }
                    }
                });
            }
        });
        ecgMeasurRingDialog.setCancelable(true);
        ecgMeasurRingDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showLeftRightDiaLog() {
        EcgMeasurDialog ecgMeasurDialog = new EcgMeasurDialog(this.context);
        ecgMeasurDialog.onCreateView();
        ecgMeasurDialog.setUiBeforShow();
        ecgMeasurDialog.setCanceledOnTouchOutside(true);
        ecgMeasurDialog.setOnButtonClickListener(new EcgMeasurDialog.OnDialogButtonClickListener() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgMeasureActivity.8
            @Override // com.yucheng.smarthealthpro.home.view.EcgMeasurDialog.OnDialogButtonClickListener
            public void onLeftClick() {
                SharedPreferencesUtils.put(EcgMeasureActivity.this.getApplicationContext(), Constant.SpConstKey.ecgWearHand, 0);
                EcgMeasureActivity.this.updateHandImg(0);
                YCBTClient.settingHandWear(0, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgMeasureActivity.8.1
                    @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                    public void onDataResponse(int code, float ratio, HashMap resultMap) {
                        if (code == 0) {
                            Log.i("EcgMeasurDialog", "你选择的是:左手");
                        }
                    }
                });
            }

            @Override // com.yucheng.smarthealthpro.home.view.EcgMeasurDialog.OnDialogButtonClickListener
            public void onRightClick() {
                SharedPreferencesUtils.put(EcgMeasureActivity.this.getApplicationContext(), Constant.SpConstKey.ecgWearHand, 1);
                EcgMeasureActivity.this.updateHandImg(1);
                YCBTClient.settingHandWear(1, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgMeasureActivity.8.2
                    @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                    public void onDataResponse(int code, float ratio, HashMap resultMap) {
                        if (code == 0) {
                            Log.i("EcgMeasurDialog", "你选择的是:右手");
                        }
                    }
                });
            }
        });
        ecgMeasurDialog.setCancelable(true);
        ecgMeasurDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initDialog() {
        String string;
        final CommonDialog commonDialog = new CommonDialog(this.context);
        if (this.mEcgMeasureList.size() > 2800) {
            string = getString(R.string.ecg_measure_dialog_message);
        } else {
            string = getString(R.string.ecg_measure_dialog_message_low_time);
        }
        commonDialog.setMessage(string).setTitle(getString(R.string.prompt)).setSingle(false).setOnClickBottomListener(new CommonDialog.OnClickBottomListener() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgMeasureActivity.9
            @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
            public void onConfirmClick() throws IllegalStateException {
                commonDialog.dismiss();
                if (YCBTClient.connectState() != 10) {
                    EcgMeasureActivity.this.mHandler.sendEmptyMessage(3);
                    if (EcgMeasureActivity.this.mEcgMeasureList.size() > 2800) {
                        EcgMeasureActivity.this.getAiResult();
                        return;
                    }
                    return;
                }
                EcgMeasureActivity.this.ecgMeasureStop();
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

    public void onViewClicked(View view) {
        if (view.getId() == R.id.tv_start_finish) {
            if (YCBTClient.connectState() == 10) {
                ecgMeasureStart();
                return;
            } else {
                Toast.makeText(this.context, getString(R.string.please_connect_the_device), 0).show();
                return;
            }
        }
        if (view.getId() == R.id.iv_stop) {
            initDialog();
        }
    }

    private void ecgMeasureStart() {
        this.isGetAiResult = false;
        AITools.getInstance().init();
        this.tvBpm.setText("---");
        this.tvMmhg.setText("---");
        this.tvHrv.setText("---");
        this.mMediaPlay = MediaPlayer.create(this, R.raw.vidio);
        this.tvStartFinish.setVisibility(8);
        this.mProgressBarText.setVisibility(0);
        this.ivStop.setVisibility(0);
        this.llElectricOff.setVisibility(8);
        this.llElectricOn.setVisibility(8);
        this.mProgressBarText.setText("0%");
        List<Integer> list = this.mEcgMeasureList;
        if (list != null) {
            list.clear();
        }
        List<Integer> list2 = this.drawLists;
        if (list2 != null) {
            list2.clear();
        }
        this.isStart = true;
        this.count = 0;
        this.isProgressBar = true;
        this.hrv_is_from_device = false;
        this.mStatusFlag = false;
        this.mCardiographView.plist.clear();
        this.mCardiographView.initList();
        this.mCardiographView.invalidate();
        makeStart();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ecgMeasureStop() throws IllegalStateException {
        this.isStart = false;
        if (this.mEcgMeasureList.size() > 2800) {
            this.mHandler.removeMessages(8);
            this.mHandler.sendEmptyMessage(8);
        }
        MediaPlayer mediaPlayer = this.mMediaPlay;
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            this.mMediaPlay.release();
            this.mMediaPlay = null;
        }
        this.mHandler.sendEmptyMessageDelayed(3, 3000L);
        YCBTClient.appEcgTestEnd(new BleDataResponse() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgMeasureActivity.10
            @Override // com.yucheng.ycbtsdk.response.BleDataResponse
            public void onDataResponse(int code, float ratio, HashMap resultMap) {
                EcgMeasureActivity.this.mHandler.removeMessages(3);
                EcgMeasureActivity.this.mHandler.sendEmptyMessage(3);
                if (code != 0 || EcgMeasureActivity.this.mEcgMeasureList.size() <= 2800) {
                    return;
                }
                EcgMeasureActivity.this.getAiResult();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getAiResult() {
        Logger.d("chong-----------getAiResult-==========");
        this.isGetAiResult = true;
        AITools.getInstance().getAIDiagnosisResult(new BleAIDiagnosisResponse() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgMeasureActivity.11
            @Override // com.yucheng.ycbtsdk.response.BleAIDiagnosisResponse
            public void onAIDiagnosisResponse(AIDataBean aiDataBean) {
                if (aiDataBean != null) {
                    short s = aiDataBean.heart;
                    EcgMeasureActivity.this.mDiagnoseType = aiDataBean.qrstype;
                    EcgMeasureActivity.this.isAfib = aiDataBean.is_atrial_fibrillation;
                    Logger.d("chong------heart==" + ((int) s) + "--qrstype==" + EcgMeasureActivity.this.mDiagnoseType + "--is_atrial_fibrillation==" + EcgMeasureActivity.this.isAfib);
                    EcgMeasureActivity.this.saveData(AITools.getInstance().getHealthNorm());
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openEcgMeasure() {
        YCBTClient.appEcgTestStart(new BleDataResponse() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgMeasureActivity.12
            @Override // com.yucheng.ycbtsdk.response.BleDataResponse
            public void onDataResponse(int i2, float v, HashMap hashMap) {
                Logger.d("code=" + i2 + StringUtils.SPACE + new Gson().toJson(hashMap));
            }
        }, new BleRealDataResponse() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgMeasureActivity.13
            @Override // com.yucheng.ycbtsdk.response.BleRealDataResponse
            public void onRealDataResponse(int i2, HashMap hashMap) {
                if (hashMap != null) {
                    Log.e("qob", "onRealDataResponse " + i2 + " dataType " + ((Integer) hashMap.get("dataType")).intValue());
                    if (i2 == 1541) {
                        if (!EcgMeasureActivity.this.mStatusFlag) {
                            EcgMeasureActivity.this.mStatusFlag = !r6.mStatusFlag;
                            EcgMeasureActivity.this.mHandler.sendEmptyMessage(5);
                        }
                        EcgMeasureActivity.this.person((List) hashMap.get("data"));
                        Log.e("ecgMeasure", new Gson().toJson(hashMap));
                        return;
                    }
                    if (i2 == 1776) {
                        if (EcgMeasureActivity.this.hrv_is_from_device || hashMap.get("data") == null || ((Float) hashMap.get("data")).floatValue() == 0.0f) {
                            return;
                        }
                        EcgMeasureActivity.this.mHRV = (int) ((Float) hashMap.get("data")).floatValue();
                        EcgMeasureActivity.this.mHandler.sendEmptyMessage(21);
                        return;
                    }
                    if (i2 == 1777) {
                        EcgMeasureActivity.this.mHandler.sendEmptyMessage(22);
                        Log.e("qob", "RR invo " + ((Float) hashMap.get("data")).floatValue());
                        return;
                    }
                    if (i2 != 1539) {
                        if (i2 == 788) {
                            int iIntValue = ((Integer) hashMap.get("EcgStatus")).intValue();
                            int iIntValue2 = ((Integer) hashMap.get("PPGStatus")).intValue();
                            if (iIntValue == 1 || iIntValue2 == 1) {
                                EcgMeasureActivity.this.mHandler.sendEmptyMessage(4);
                                return;
                            } else {
                                EcgMeasureActivity.this.mHandler.sendEmptyMessage(5);
                                return;
                            }
                        }
                        return;
                    }
                    EcgMeasureActivity.this.mHeart = ((Integer) hashMap.get("heartValue")).intValue();
                    EcgMeasureActivity.this.mDBP = ((Integer) hashMap.get("bloodDBP")).intValue();
                    EcgMeasureActivity.this.mSBP = ((Integer) hashMap.get("bloodSBP")).intValue();
                    if (hashMap.get("hrv") != null && ((Integer) hashMap.get("hrv")).intValue() != 0) {
                        EcgMeasureActivity.this.hrv_is_from_device = true;
                        EcgMeasureActivity.this.mHRV = ((Integer) hashMap.get("hrv")).intValue();
                    }
                    EcgMeasureActivity.this.mHandler.sendEmptyMessage(23);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void person(List<Integer> datas) {
        this.mEcgMeasureList.addAll(datas);
        Iterator<Integer> it2 = datas.iterator();
        int iIntValue = 0;
        int i2 = 0;
        while (it2.hasNext()) {
            iIntValue += it2.next().intValue();
            i2++;
            if (i2 % 3 == 0) {
                iIntValue = (iIntValue / 40) / 3;
                if (iIntValue > 500) {
                    iIntValue = 500;
                }
                if (iIntValue < -500) {
                    iIntValue = -500;
                }
                this.drawLists.add(Integer.valueOf(iIntValue));
            }
        }
    }

    public void makeStart() {
        this.mHandler.sendEmptyMessage(1);
        this.mMeasureTime = System.currentTimeMillis();
        new Thread(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgMeasureActivity.14
            @Override // java.lang.Runnable
            public void run() throws InterruptedException {
                while (EcgMeasureActivity.this.isStart) {
                    try {
                    } catch (InterruptedException e2) {
                        e2.getStackTrace();
                    }
                    if (EcgMeasureActivity.this.mCardiographView == null) {
                        EcgMeasureActivity.this.isStart = false;
                        return;
                    }
                    Thread.sleep(12L);
                    if (EcgMeasureActivity.this.index >= EcgMeasureActivity.this.drawLists.size()) {
                        continue;
                    } else {
                        if (EcgMeasureActivity.this.mCardiographView == null) {
                            return;
                        }
                        synchronized (EcgMeasureActivity.this.mCardiographView.plist) {
                            if (EcgMeasureActivity.this.mCardiographView.plist.size() > EcgMeasureActivity.this.mCardiographView.WidthDots) {
                                EcgMeasureActivity.this.mCardiographView.plist.remove(0);
                            }
                            if (EcgMeasureActivity.this.drawLists.size() >= 200) {
                                EcgMeasureActivity.this.mCardiographView.plist.add((Integer) EcgMeasureActivity.this.drawLists.get(EcgMeasureActivity.this.index));
                            } else {
                                EcgMeasureActivity.this.mCardiographView.plist.add(0);
                            }
                            EcgMeasureActivity.this.index++;
                        }
                    }
                    EcgMeasureActivity.this.mHandler.sendEmptyMessage(2);
                }
                EcgMeasureActivity.this.mHandler.sendEmptyMessage(7);
            }
        }).start();
        this.mExecutorService.schedule(this.mRefreshRunnable, this.mFrameInterval, TimeUnit.MILLISECONDS);
    }

    public void hrv_evt_handle(int evt_type, float params) {
        if (evt_type == 3) {
            Logger.d("ltf hrv_evt_handle evt_type=" + evt_type);
            Message message = new Message();
            message.what = 22;
            message.obj = Float.valueOf(params);
            this.mHandler.sendMessage(message);
            return;
        }
        if (evt_type == 4 && params != 0.0f) {
            Message message2 = new Message();
            message2.what = 21;
            message2.obj = Float.valueOf(params);
            this.mHandler.sendMessage(message2);
        }
    }

    public void saveData(HealthNormBean ecgData) {
        if (this.mEcgMeasureList.size() < 2800) {
            Log.i("saveData", "您当前测试时间过短,请重新测试");
            return;
        }
        this.ecgData = ecgData;
        int age = YearToDayListUtils.getAge((String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.BIRTH_DATE, YearToDayListUtils.subYear(20)));
        String json = new GsonBuilder().create().toJson(ecgData);
        int iIntValue = ((Integer) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.SEX, 0)).intValue();
        EcgViewModel ecgViewModel = this.mViewModel;
        long j2 = this.mMeasureTime;
        ecgViewModel.saveEcgMeasureData(new EcgMeasure(null, 0, j2, TimeStampUtils.dateForStringYearToDate(TimeStampUtils.longStampForDate(j2)), this.mHRV, this.mHeart, this.mSBP, this.mDBP, new Gson().toJson(this.mEcgMeasureList), age, iIntValue, this.isAfib, this.mDiagnoseType, json, UserInfoUtil.getUserName(), Tools.getDeviceType(this.context), YCBTClient.getBindDeviceMac(), "", false, false));
        this.mHandler.removeMessages(6);
        this.mHandler.sendEmptyMessageDelayed(6, 1000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onEcgSaved(Long l) {
        int age = YearToDayListUtils.getAge((String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.BIRTH_DATE, YearToDayListUtils.subYear(20)));
        String json = new GsonBuilder().create().toJson(this.ecgData);
        AIDiagnosisResultBean aIDiagnosisResultBean = new AIDiagnosisResultBean();
        aIDiagnosisResultBean.userId = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.DEV_ID, "1");
        aIDiagnosisResultBean.time = this.mMeasureTime;
        aIDiagnosisResultBean.hrHz = this.mHRV;
        aIDiagnosisResultBean.hhhh = this.mHeart;
        aIDiagnosisResultBean.maxb = this.mSBP;
        aIDiagnosisResultBean.minb = this.mDBP;
        aIDiagnosisResultBean.data = new Gson().toJson(this.mEcgMeasureList);
        aIDiagnosisResultBean.age = age;
        aIDiagnosisResultBean.sex = ((Integer) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.SEX, 0)).intValue();
        aIDiagnosisResultBean.medicalResult.afflag = this.isAfib ? 1 : 0;
        aIDiagnosisResultBean.medicalResult.qrstype = this.mDiagnoseType;
        aIDiagnosisResultBean.healthNorm = json;
        aIDiagnosisResultBean.heavyLoad = this.ecgData.heavyLoad;
        aIDiagnosisResultBean.body = this.ecgData.body;
        aIDiagnosisResultBean.pressure = this.ecgData.pressure;
        aIDiagnosisResultBean.hrvNorm = this.ecgData.hrvNorm;
        aIDiagnosisResultBean.sympatheticActivityIndex = this.ecgData.sympatheticParasympathetic;
        aIDiagnosisResultBean.respiratoryRate = this.ecgData.respiratoryRate;
        aIDiagnosisResultBean.medicalResult.heavyLoad = "" + this.ecgData.heavyLoad;
        aIDiagnosisResultBean.medicalResult.body = "" + this.ecgData.body;
        aIDiagnosisResultBean.medicalResult.pressure = "" + this.ecgData.pressure;
        aIDiagnosisResultBean.medicalResult.hrvNorm = "" + this.ecgData.hrvNorm;
        aIDiagnosisResultBean.medicalResult.sympatheticActivityIndex = "" + this.ecgData.sympatheticParasympathetic;
        aIDiagnosisResultBean.medicalResult.respiratoryRate = this.ecgData.respiratoryRate;
        aIDiagnosisResultBean.deviceMac = YCBTClient.getBindDeviceMac();
        aIDiagnosisResultBean.deviceModel = Tools.getDeviceType(this.context);
        ArrayList arrayList = new ArrayList();
        arrayList.add(aIDiagnosisResultBean);
        Log.w("EcgMeasureActivity", "uploadLocalService=" + aIDiagnosisResultBean.healthNorm);
        uploadLocalService(new Gson().toJson(arrayList).toString(), l);
    }

    private void uploadLocalService(String data, final Long id) {
        HttpUtils.getInstance().postJsonMsgAsynHttp(this, Constants.upheartline, data, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgMeasureActivity.15
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                try {
                    Logger.d("chong---------response1==" + result);
                    if (id.longValue() != -1) {
                        EcgMeasureActivity.this.mViewModel.updateEcgUploaded(id.longValue());
                    }
                } catch (Exception e2) {
                    CrashReport.postCatchedException(e2);
                    e2.printStackTrace();
                }
            }
        });
        if (Constant.isMymon()) {
            HttpUtils.getInstance().postJsonMsgAsynHttp(this, Constants.upheartline.replace("https://web-api.ycaviation.com/smartam", Constants.BASEMYMOMURL), data, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgMeasureActivity.16
                @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
                public void onSuccess(String result) {
                    try {
                        Logger.d("chong---------response1==" + result);
                    } catch (Exception e2) {
                        CrashReport.postCatchedException(e2);
                        e2.printStackTrace();
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void goEcgAIDiagnosisActivity() {
        if (isDestroyed()) {
            Logger.d("EcgMeasureActivity  isDestroyed");
            return;
        }
        if (!this.isResume) {
            Log.i("AIDiagnosisActivity", "!isResume");
            this.isHasDiagnosis = true;
            return;
        }
        int age = YearToDayListUtils.getAge((String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.BIRTH_DATE, YearToDayListUtils.subYear(20)));
        String str = this.mSBP + "/" + this.mDBP;
        Intent intent = new Intent(this, (Class<?>) EcgAiDiagnoseActivity.class);
        if (Constant.isHealthWear() || Constant.isSmartHealth()) {
            intent = new Intent(this.context, (Class<?>) EcgAiDiagnoseNewActivity.class);
        }
        intent.putExtra("time", this.mMeasureTime);
        intent.putExtra("mBp", str);
        intent.putExtra("mHeart", this.mHeart);
        intent.putExtra("mHRV", this.mHRV);
        intent.putExtra("mAge", age);
        intent.putExtra("mSex", ((Integer) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.SEX, 0)).intValue());
        intent.putExtra("isAfib", this.isAfib);
        intent.putExtra("mDiagnoseType", this.mDiagnoseType);
        try {
            intent.putExtra("healthNorm", new GsonBuilder().create().toJson(this.ecgData));
        } catch (Exception e2) {
            CrashReport.postCatchedException(e2);
            e2.printStackTrace();
        }
        this.mEcgMeasureList.clear();
        this.isHasDiagnosis = false;
        startActivity(intent);
        int i2 = this.mDiagnoseType;
        if (i2 == 1 || i2 == 5 || i2 == 9 || this.isAfib) {
            Log.i("AIDiagnosisActivity", "-1-");
        } else {
            Log.i("AIDiagnosisActivity", "-2-");
        }
        if (YCBTClient.connectState() != 10) {
            finish();
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == 4) {
            if (this.isStart) {
                initDialog();
                return true;
            }
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        this.mHandler.removeCallbacksAndMessages(null);
        List<Integer> list = this.mEcgMeasureList;
        if (list != null) {
            list.clear();
        }
        try {
            PowerManager.WakeLock wakeLock = this.wakeLock;
            if (wakeLock != null && wakeLock.isHeld()) {
                this.wakeLock.release();
            }
        } catch (Exception e2) {
            CrashReport.postCatchedException(e2);
            e2.printStackTrace();
        }
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        this.isResume = true;
        if (this.isHasDiagnosis) {
            goEcgAIDiagnosisActivity();
        }
        try {
            this.mHandler.removeMessages(145653);
            if (this.wakeLock == null) {
                this.wakeLock = this.powerManager.newWakeLock(26, "My Lock");
            }
            if (this.wakeLock.isHeld()) {
                return;
            }
            this.wakeLock.acquire();
        } catch (Exception e2) {
            CrashReport.postCatchedException(e2);
            e2.printStackTrace();
        }
    }

    @Override // com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        this.isResume = false;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventBusEcgEnd ecgEnd) {
        ToastUtil.getInstance(this).toast(getString(R.string.device_stopped));
        this.isStart = false;
        this.mHandler.sendEmptyMessage(3);
        if (this.mEcgMeasureList.size() <= 2800 || this.isGetAiResult) {
            return;
        }
        getAiResult();
        this.mHandler.removeMessages(8);
        this.mHandler.sendEmptyMessage(8);
        this.mHandler.removeMessages(6);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventBusMessageEvent messageEvent) {
        if (messageEvent.belState != 0) {
            return;
        }
        if (this.mEcgMeasureList.size() <= 2800) {
            ToastUtil.getInstance(this).toast(getString(R.string.device_disconnect));
            finish();
        } else {
            if (!this.isStart || this.isGetAiResult) {
                return;
            }
            this.mHandler.sendEmptyMessageDelayed(3, 3000L);
            getAiResult();
            this.mHandler.removeMessages(8);
            this.mHandler.sendEmptyMessage(8);
            this.mHandler.removeMessages(6);
        }
    }
}
