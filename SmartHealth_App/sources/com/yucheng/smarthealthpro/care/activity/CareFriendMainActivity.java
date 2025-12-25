package com.yucheng.smarthealthpro.care.activity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;
import com.realsil.sdk.dfu.DfuConstants;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.care.bean.FriendCareBloodBean;
import com.yucheng.smarthealthpro.care.bean.FriendCareBloodFatBean;
import com.yucheng.smarthealthpro.care.bean.FriendCareBloodSugarBean;
import com.yucheng.smarthealthpro.care.bean.FriendCareDataBean;
import com.yucheng.smarthealthpro.care.bean.FriendCareHeartBean;
import com.yucheng.smarthealthpro.care.bean.FriendCareKetoneBean;
import com.yucheng.smarthealthpro.care.bean.FriendCareModeBean;
import com.yucheng.smarthealthpro.care.bean.FriendCarePhyBean;
import com.yucheng.smarthealthpro.care.bean.FriendCareRateBean;
import com.yucheng.smarthealthpro.care.bean.FriendCareSpo2Bean;
import com.yucheng.smarthealthpro.care.bean.FriendCareTempBean;
import com.yucheng.smarthealthpro.care.bean.FriendCareUricAcidBean;
import com.yucheng.smarthealthpro.care.bean.FriendPhyBean;
import com.yucheng.smarthealthpro.care.bean.HistoryPressureResponse;
import com.yucheng.smarthealthpro.care.bean.HistorySleep;
import com.yucheng.smarthealthpro.care.bean.HistorySleepResponse;
import com.yucheng.smarthealthpro.care.bean.HistorySportResponse;
import com.yucheng.smarthealthpro.databinding.ActivityFriendmainBinding;
import com.yucheng.smarthealthpro.framework.http.HttpUtils;
import com.yucheng.smarthealthpro.framework.util.Constants;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.home.activity.bloodfat.activity.BloodFatActivity;
import com.yucheng.smarthealthpro.home.activity.bloodoxygen.activity.BloodOxygenActivity;
import com.yucheng.smarthealthpro.home.activity.bloodpressure.activity.BloodPressureActivity;
import com.yucheng.smarthealthpro.home.activity.bloodsugar.activity.BloodSugarActivity;
import com.yucheng.smarthealthpro.home.activity.ecg.activity.EcgActivity;
import com.yucheng.smarthealthpro.home.activity.heartrate.activity.HeartRateActivity;
import com.yucheng.smarthealthpro.home.activity.hrv.activity.HRVActivity;
import com.yucheng.smarthealthpro.home.activity.ketone.activity.KetoneActivity;
import com.yucheng.smarthealthpro.home.activity.phy.activity.PhyActivity;
import com.yucheng.smarthealthpro.home.activity.pressure.PressureActivity;
import com.yucheng.smarthealthpro.home.activity.respiratoryrate.activity.RespiratoryRateActivity;
import com.yucheng.smarthealthpro.home.activity.running.activity.RunningActivity;
import com.yucheng.smarthealthpro.home.activity.sleep.activity.SleepActivity;
import com.yucheng.smarthealthpro.home.activity.temperature.activity.TemperatureActivity;
import com.yucheng.smarthealthpro.home.activity.uricacid.activity.UricAcidActivity;
import com.yucheng.smarthealthpro.home.adapter.HomeFunctionAdapter;
import com.yucheng.smarthealthpro.home.bean.HomeFunctionBean;
import com.yucheng.smarthealthpro.home.view.StepView;
import com.yucheng.smarthealthpro.sport.view.CommonDialog;
import com.yucheng.smarthealthpro.utils.AppDateMgr;
import com.yucheng.smarthealthpro.utils.AppImageMgr;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.DebouncingClick;
import com.yucheng.smarthealthpro.utils.FormatUtil;
import com.yucheng.smarthealthpro.utils.MLog;
import com.yucheng.smarthealthpro.utils.TimeStampUtils;
import com.yucheng.smarthealthpro.utils.Tools;
import com.yucheng.smarthealthpro.utils.TransUtils;
import com.yucheng.smarthealthpro.utils.YearToDayListUtils;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class CareFriendMainActivity extends BaseVbActivity<ActivityFriendmainBinding> {
    private int SumUpKcal;
    private float SumUpKm;
    private int SumUpStepNum;
    private FriendCareModeBean bean;
    private String dateTime;
    private String devId;
    private AppImageMgr mAppImageMgr;
    private HomeFunctionAdapter mHomeFunctionAdapter;
    private List<HomeFunctionBean> mHomeFunctionBean;
    private List<HomeFunctionBean> mHomeFunctionBeans;
    RecyclerView mRecyclerView;
    SmartRefreshLayout mSmartRefreshLayout;
    SpinKitView mSpinKitView;
    StepView mStepView;
    private int mTempUnit;
    private String mToDay;
    private int mUnit;
    private String nickName;
    RelativeLayout rlRunning;
    RelativeLayout rvDialog;
    TextView tvKcal;
    TextView tvOdo;
    TextView tvOdoUnit;
    TextView tvStep;
    private List<HistorySleepResponse.SleepBean> yesterdaySleepBeans;
    private Gson mGson = new Gson();
    private int sex = 0;
    List<HistorySleepResponse.SleepBean> todayHistorySleepList = new ArrayList();
    List<HistorySleepResponse.SleepBean> yesterdayHistorySleepList = new ArrayList();
    List<HistorySleepResponse.SleepBean> slist = new ArrayList();
    List<HistorySleep> historySleeps = new ArrayList();
    final int SLEEP_LIMIT_HIGH = 57600;
    private String[] function = {"心电", "睡眠", "心率", "血压", "血糖", "血氧", "呼吸率", "温度", "血脂", "尿酸", "运动", "理疗", "血酮", "HRV", "压力"};
    private Handler mHandler = new Handler() { // from class: com.yucheng.smarthealthpro.care.activity.CareFriendMainActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what != 1) {
                return;
            }
            MLog.INSTANCE.d("bean: " + CareFriendMainActivity.this.bean + "  mHomeFunctionBean: " + CareFriendMainActivity.this.mHomeFunctionBean.size());
            if (CareFriendMainActivity.this.bean == null || CareFriendMainActivity.this.mHomeFunctionBean == null) {
                return;
            }
            if (CareFriendMainActivity.this.bean.getData().getHeartLine() == 1) {
                CareFriendMainActivity.this.mHomeFunctionBean.add(new HomeFunctionBean("", "", CareFriendMainActivity.this.getString(R.string.home_ecg_title), "心电", CareFriendMainActivity.this.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_ecg), true));
                CareFriendMainActivity careFriendMainActivity = CareFriendMainActivity.this;
                careFriendMainActivity.notifyRecycle(careFriendMainActivity.mHomeFunctionBean);
            }
            if (CareFriendMainActivity.this.bean.getData().getBlood() == 1) {
                CareFriendMainActivity careFriendMainActivity2 = CareFriendMainActivity.this;
                careFriendMainActivity2.getBloodRequest(careFriendMainActivity2.devId, CareFriendMainActivity.this.mToDay);
            }
            if (CareFriendMainActivity.this.bean.getData().getRespiratoryRate() == 1) {
                CareFriendMainActivity.this.getRate();
            }
            if (CareFriendMainActivity.this.bean.getData().getBloodOxygen() == 1) {
                CareFriendMainActivity.this.getBloodOxygen();
            }
            if (CareFriendMainActivity.this.bean.getData().getTemperature() == 1) {
                CareFriendMainActivity.this.getTemp();
            }
            if (CareFriendMainActivity.this.bean.getData().bloodSugar == 1) {
                CareFriendMainActivity.this.getBloodSugar();
            }
            if (CareFriendMainActivity.this.bean.getData().getBloodFat() == 1) {
                CareFriendMainActivity.this.getBloodFat();
            }
            if (CareFriendMainActivity.this.bean.getData().getUricAcid() == 1) {
                CareFriendMainActivity.this.getUricAcid();
            }
            if (CareFriendMainActivity.this.bean.getData().getBloodKetone() == 1) {
                CareFriendMainActivity.this.getBloodKetone();
            }
            if (CareFriendMainActivity.this.bean.getData().getLaserConditioningTherapy() == 1) {
                CareFriendMainActivity.this.getLaserConditioningTherapy();
            }
            if (CareFriendMainActivity.this.bean.getData().getHrv() == 1) {
                CareFriendMainActivity.this.getHRV();
            }
            if (CareFriendMainActivity.this.bean.getData().getPressure() == 1) {
                CareFriendMainActivity.this.getPressure();
            }
        }
    };

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        this.mStepView = ((ActivityFriendmainBinding) this.mBinding).stepView;
        this.tvKcal = ((ActivityFriendmainBinding) this.mBinding).tvKcal;
        this.tvStep = ((ActivityFriendmainBinding) this.mBinding).tvStep;
        this.tvOdo = ((ActivityFriendmainBinding) this.mBinding).tvOdo;
        this.rlRunning = ((ActivityFriendmainBinding) this.mBinding).rlRunning;
        this.mRecyclerView = ((ActivityFriendmainBinding) this.mBinding).recycleHome;
        this.mSmartRefreshLayout = ((ActivityFriendmainBinding) this.mBinding).srlHome;
        this.mSpinKitView = ((ActivityFriendmainBinding) this.mBinding).rvDialog.spinKit;
        this.rvDialog = ((ActivityFriendmainBinding) this.mBinding).rvDialog.rvDialog;
        this.tvOdoUnit = ((ActivityFriendmainBinding) this.mBinding).tvOdoUnit;
        this.rlRunning.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.care.activity.CareFriendMainActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        changeTitle(getIntent().getStringExtra("nickName"));
        showBack();
        String str = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.UNIT, "");
        if ((str == null || !str.equals(Constant.SpConstValue.ISO)) && str != null && str.equals(Constant.SpConstValue.INCH)) {
            this.mUnit = 1;
            this.tvOdoUnit.setText(getString(R.string.dis_inch_unit));
        } else {
            this.mUnit = 0;
            this.tvOdoUnit.setText(getString(R.string.dis_km_unit));
        }
        String str2 = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.TEMP_UNIT, "");
        if ((str2 == null || !str2.equals(Constant.SpConstValue.TEMP_ISO)) && str2 != null && str2.equals(Constant.SpConstValue.TEMP_INCH)) {
            this.mTempUnit = 1;
        } else {
            this.mTempUnit = 0;
        }
        this.mToDay = TimeStampUtils.getToDay();
        Intent intent = getIntent();
        this.devId = intent.getStringExtra(Constant.SpConstKey.DEV_ID);
        this.nickName = intent.getStringExtra("nickName");
        String stringExtra = intent.getStringExtra(Constant.SpConstKey.SEX);
        if (!TextUtils.isEmpty(stringExtra)) {
            try {
                this.sex = Integer.parseInt(stringExtra);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        this.dateTime = Tools.transformNowTime(System.currentTimeMillis());
        this.mSmartRefreshLayout.autoRefresh();
        this.mSmartRefreshLayout.setEnableLoadMore(false);
        this.mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() { // from class: com.yucheng.smarthealthpro.care.activity.CareFriendMainActivity.2
            @Override // com.scwang.smart.refresh.layout.listener.OnRefreshListener
            public void onRefresh(RefreshLayout refreshLayout) {
                MLog.INSTANCE.d("care onRefresh");
                if (CareFriendMainActivity.this.mHomeFunctionBean == null) {
                    CareFriendMainActivity.this.mHomeFunctionBean = new ArrayList();
                } else {
                    CareFriendMainActivity.this.mHomeFunctionBean.clear();
                }
                CareFriendMainActivity.this.rvDialog.setVisibility(0);
                CareFriendMainActivity.this.mSpinKitView.setVisibility(0);
                CareFriendMainActivity careFriendMainActivity = CareFriendMainActivity.this;
                careFriendMainActivity.getSportRequest(careFriendMainActivity.devId, CareFriendMainActivity.this.mToDay);
                CareFriendMainActivity careFriendMainActivity2 = CareFriendMainActivity.this;
                careFriendMainActivity2.getHeartRequest(careFriendMainActivity2.devId, CareFriendMainActivity.this.mToDay);
                CareFriendMainActivity careFriendMainActivity3 = CareFriendMainActivity.this;
                careFriendMainActivity3.getDaySleep(careFriendMainActivity3.devId, CareFriendMainActivity.this.mToDay);
                CareFriendMainActivity.this.getAction();
                refreshLayout.finishRefresh();
                CareFriendMainActivity.this.rvDialog.setVisibility(8);
                CareFriendMainActivity.this.mSpinKitView.setVisibility(8);
            }
        });
    }

    private void initData() {
        this.mHomeFunctionBean = new ArrayList();
        this.mAppImageMgr = new AppImageMgr(this.context);
        setRecycleView();
        getSportRequest(this.devId, this.mToDay);
        getHeartRequest(this.devId, this.mToDay);
        getDaySleep(this.devId, this.mToDay);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setStepNum(List<HistorySportResponse.DataBean> sportDataList) {
        this.SumUpStepNum = 0;
        this.SumUpKcal = 0;
        this.SumUpKm = 0.0f;
        try {
            JSONArray jSONArray = new JSONArray("[" + sportDataList.get(0).mlist.replaceAll("\\[", "").replaceAll("]", "") + "]");
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i2);
                this.SumUpKcal += jSONObject.getInt("cakl");
                this.SumUpStepNum += jSONObject.getInt("step");
                this.SumUpKm += jSONObject.getInt("des");
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        this.tvKcal.setText(this.SumUpKcal + "");
        int iIntValue = ((Integer) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.TARGET_NUMBER_OF_MOVEMENT_STEPS, Integer.valueOf(DfuConstants.MAX_NOTIFICATION_LOCK_WAIT_TIME))).intValue();
        this.tvStep.setText(iIntValue + "");
        this.mStepView.setStepMax(iIntValue);
        if (this.mUnit == 0) {
            this.tvOdo.setText(String.format("%.3f", Float.valueOf(this.SumUpKm / 1000.0f)) + "");
        } else {
            this.tvOdo.setText(String.format("%.3f", Float.valueOf(this.SumUpKm / 1609.344f)) + "");
        }
        ValueAnimator valueAnimatorOfFloat = ObjectAnimator.ofFloat(0.0f, this.SumUpStepNum);
        valueAnimatorOfFloat.setDuration(1000L);
        valueAnimatorOfFloat.setInterpolator(new DecelerateInterpolator());
        valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.yucheng.smarthealthpro.care.activity.CareFriendMainActivity.3
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator animation) {
                CareFriendMainActivity.this.mStepView.setCurrentStep((int) ((Float) animation.getAnimatedValue()).floatValue());
            }
        });
        valueAnimatorOfFloat.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyRecycle(List<HomeFunctionBean> mHomeFunctionBean) {
        MLog.INSTANCE.d("notifyRecycle: " + mHomeFunctionBean.size());
        this.mHomeFunctionBeans = new ArrayList();
        ArrayList arrayList = new ArrayList(Arrays.asList(this.function));
        for (int i2 = 0; i2 < mHomeFunctionBean.size(); i2++) {
            String function = mHomeFunctionBean.get(i2).getFunction();
            if (arrayList.contains(function)) {
                this.mHomeFunctionBeans.add(mHomeFunctionBean.get(i2));
                arrayList.remove(function);
            }
        }
        this.mHomeFunctionAdapter.setList(this.mHomeFunctionBeans);
        this.mHomeFunctionAdapter.notifyDataSetChanged();
    }

    private void setRecycleView() {
        this.mRecyclerView.setLayoutManager(new GridLayoutManager(this.context, 2));
        HomeFunctionAdapter homeFunctionAdapter = new HomeFunctionAdapter(R.layout.item_home_function, true);
        this.mHomeFunctionAdapter = homeFunctionAdapter;
        homeFunctionAdapter.addData((Collection) this.mHomeFunctionBean);
        this.mRecyclerView.setAdapter(this.mHomeFunctionAdapter);
        this.mRecyclerView.setNestedScrollingEnabled(false);
        this.mHomeFunctionAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.yucheng.smarthealthpro.care.activity.CareFriendMainActivity.4
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public void onItemClick(BaseQuickAdapter<?, ?> adapter, View view, int position) {
                HomeFunctionBean homeFunctionBean = CareFriendMainActivity.this.mHomeFunctionAdapter.getData().get(position);
                if (CareFriendMainActivity.this.checkCanClick()) {
                    CareFriendMainActivity.this.onFunctionItemClick(homeFunctionBean);
                }
            }
        });
        this.mHomeFunctionAdapter.addChildClickViewIds(R.id.ll_data);
        this.mHomeFunctionAdapter.setOnItemChildClickListener(new OnItemChildClickListener() { // from class: com.yucheng.smarthealthpro.care.activity.CareFriendMainActivity.5
            @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                HomeFunctionBean homeFunctionBean = CareFriendMainActivity.this.mHomeFunctionAdapter.getData().get(position);
                if (CareFriendMainActivity.this.checkCanClick()) {
                    if ("睡眠".equals(homeFunctionBean.getFunction()) && "00".equals(homeFunctionBean.getValue())) {
                        CareFriendMainActivity.this.showSleepQuestionDialog();
                    } else {
                        CareFriendMainActivity.this.onFunctionItemClick(homeFunctionBean);
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showSleepQuestionDialog() {
        String string = new StringBuffer().append(getString(R.string.sleep_empty_hint)).append("\n\n").append(getString(R.string.sleep_empty_explanation)).append(StringUtils.LF).append(getString(R.string.sleep_empty_reason_1)).append(StringUtils.LF).append(getString(R.string.sleep_empty_reason_2)).append(StringUtils.LF).append(getString(R.string.sleep_empty_reason_3)).append(StringUtils.LF).append(getString(R.string.sleep_empty_reason_4)).toString();
        final CommonDialog commonDialog = new CommonDialog(getActivity());
        commonDialog.setMessage(string).setSingle(true).setAlignStart(true).setSmallerMessage(true).setConfirm(getString(R.string.wisdom_action_knew)).setOnClickBottomListener(new CommonDialog.OnClickBottomListener() { // from class: com.yucheng.smarthealthpro.care.activity.CareFriendMainActivity.6
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

    /* JADX INFO: Access modifiers changed from: private */
    public void onFunctionItemClick(HomeFunctionBean functionBean) {
        String function = functionBean.getFunction();
        function.hashCode();
        switch (function) {
            case "HRV":
                startActivity(new Intent(this.context, (Class<?>) HRVActivity.class).putExtra(Constant.SpConstKey.DEV_ID, this.devId).putExtra("phone", this.nickName).putExtra(Constant.SpConstKey.SEX, this.sex).putExtra("care", getString(R.string.care_title)));
                break;
            case "压力":
                startActivity(new Intent(this.context, (Class<?>) PressureActivity.class).putExtra(Constant.SpConstKey.DEV_ID, this.devId).putExtra("phone", this.nickName).putExtra(Constant.SpConstKey.SEX, this.sex).putExtra("care", getString(R.string.care_title)));
                break;
            case "尿酸":
                startActivity(new Intent(this.context, (Class<?>) UricAcidActivity.class).putExtra(Constant.SpConstKey.DEV_ID, this.devId).putExtra("phone", this.nickName).putExtra(Constant.SpConstKey.SEX, this.sex).putExtra("care", getString(R.string.care_title)));
                break;
            case "心率":
                startActivity(new Intent(this.context, (Class<?>) HeartRateActivity.class).putExtra(Constant.SpConstKey.DEV_ID, this.devId).putExtra("phone", this.nickName).putExtra("care", getString(R.string.care_title)));
                break;
            case "心电":
                startActivity(new Intent(this.context, (Class<?>) EcgActivity.class).putExtra(Constant.SpConstKey.DEV_ID, this.devId).putExtra("phone", this.nickName).putExtra("care", getString(R.string.care_title)));
                break;
            case "温度":
                startActivity(new Intent(this.context, (Class<?>) TemperatureActivity.class).putExtra(Constant.SpConstKey.DEV_ID, this.devId).putExtra("phone", this.nickName).putExtra("care", getString(R.string.care_title)));
                break;
            case "理疗":
                startActivity(new Intent(this.context, (Class<?>) PhyActivity.class).putExtra(Constant.SpConstKey.DEV_ID, this.devId).putExtra("phone", this.nickName).putExtra(Constant.SpConstKey.SEX, this.sex).putExtra("care", getString(R.string.care_title)));
                break;
            case "睡眠":
                startActivity(new Intent(this.context, (Class<?>) SleepActivity.class).putExtra(Constant.SpConstKey.DEV_ID, this.devId).putExtra("phone", this.nickName).putExtra("care", getString(R.string.care_title)));
                break;
            case "血压":
                startActivity(new Intent(this.context, (Class<?>) BloodPressureActivity.class).putExtra(Constant.SpConstKey.DEV_ID, this.devId).putExtra("phone", this.nickName).putExtra("care", getString(R.string.care_title)));
                break;
            case "血氧":
                startActivity(new Intent(this.context, (Class<?>) BloodOxygenActivity.class).putExtra(Constant.SpConstKey.DEV_ID, this.devId).putExtra("phone", this.nickName).putExtra("care", getString(R.string.care_title)));
                break;
            case "血糖":
                startActivity(new Intent(this.context, (Class<?>) BloodSugarActivity.class).putExtra(Constant.SpConstKey.DEV_ID, this.devId).putExtra("phone", this.nickName).putExtra("care", getString(R.string.care_title)));
                break;
            case "血脂":
                startActivity(new Intent(this.context, (Class<?>) BloodFatActivity.class).putExtra(Constant.SpConstKey.DEV_ID, this.devId).putExtra("phone", this.nickName).putExtra("care", getString(R.string.care_title)));
                break;
            case "血酮":
                startActivity(new Intent(this.context, (Class<?>) KetoneActivity.class).putExtra(Constant.SpConstKey.DEV_ID, this.devId).putExtra("phone", this.nickName).putExtra(Constant.SpConstKey.SEX, this.sex).putExtra("care", getString(R.string.care_title)));
                break;
            case "呼吸率":
                startActivity(new Intent(this.context, (Class<?>) RespiratoryRateActivity.class).putExtra(Constant.SpConstKey.DEV_ID, this.devId).putExtra("phone", this.nickName).putExtra("care", getString(R.string.care_title)));
                break;
        }
    }

    public void getAction() {
        HashMap map = new HashMap();
        map.put(Constant.SpConstKey.DEV_ID, this.devId);
        HttpUtils.getInstance().postMsgAsynHttp(this, Constants.checkBydevIdUrl, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.care.activity.CareFriendMainActivity.7
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                if (result != null) {
                    try {
                        CareFriendMainActivity careFriendMainActivity = CareFriendMainActivity.this;
                        careFriendMainActivity.bean = (FriendCareModeBean) careFriendMainActivity.mGson.fromJson(result, FriendCareModeBean.class);
                    } catch (JsonSyntaxException e2) {
                        e2.printStackTrace();
                    }
                    CareFriendMainActivity.this.mHandler.sendEmptyMessage(1);
                }
            }
        });
    }

    public void getSportRequest(String devId, String day) {
        HashMap map = new HashMap();
        map.put("userId", devId);
        map.put("day", day);
        HttpUtils.getInstance().postMsgAsynHttp(this.context, Constants.sportDayUrl, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.care.activity.CareFriendMainActivity.8
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                HistorySportResponse historySportResponse;
                List<HistorySportResponse.DataBean> list;
                if (result != null) {
                    try {
                        historySportResponse = (HistorySportResponse) CareFriendMainActivity.this.mGson.fromJson(result, HistorySportResponse.class);
                    } catch (JsonSyntaxException e2) {
                        e2.printStackTrace();
                        Logger.d("chong-------sport_result==" + result);
                        historySportResponse = null;
                    }
                    if (historySportResponse == null || (list = historySportResponse.data) == null || list.size() <= 0) {
                        return;
                    }
                    CareFriendMainActivity.this.setStepNum(list);
                }
            }
        });
    }

    public void getBloodRequest(String devId, String day) {
        HashMap map = new HashMap();
        map.put("userId", devId);
        map.put("day", day);
        HttpUtils.getInstance().postMsgAsynHttp(this.context, Constants.bloodDayUrl, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.care.activity.CareFriendMainActivity.9
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                int i2;
                ArrayList<FriendCareBloodBean> arrayList = new ArrayList();
                if (result != null) {
                    try {
                        Iterator<FriendCareDataBean.Data> it2 = ((FriendCareDataBean) CareFriendMainActivity.this.mGson.fromJson(result, FriendCareDataBean.class)).data.iterator();
                        while (it2.hasNext()) {
                            arrayList.addAll((Collection) CareFriendMainActivity.this.mGson.fromJson(it2.next().mlist, new TypeToken<List<FriendCareBloodBean>>() { // from class: com.yucheng.smarthealthpro.care.activity.CareFriendMainActivity.9.1
                            }.getType()));
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        Logger.d("chong-------blood_result==" + result);
                    }
                }
                int i3 = 0;
                int i4 = 0;
                int i5 = 0;
                for (FriendCareBloodBean friendCareBloodBean : arrayList) {
                    i4 += friendCareBloodBean.sbp;
                    i5 += friendCareBloodBean.dbp;
                }
                Iterator<FriendCareBloodBean> it3 = Tools.sortCareBloodData(arrayList).iterator();
                while (true) {
                    if (!it3.hasNext()) {
                        i2 = 0;
                        break;
                    }
                    FriendCareBloodBean next = it3.next();
                    if (i4 > i5 && next.sbp >= 60 && next.sbp <= 250 && next.dbp >= 30 && next.dbp <= 160 && next.sbp - next.dbp >= 10 && next.sbp - next.dbp <= 90) {
                        i3 = next.sbp;
                        i2 = next.dbp;
                        break;
                    } else if (i4 < i5 && next.sbp >= 30 && next.sbp <= 160 && next.dbp >= 60 && next.dbp <= 250 && next.dbp - next.sbp >= 10 && next.dbp - next.sbp <= 90) {
                        i3 = next.dbp;
                        i2 = next.sbp;
                        break;
                    }
                }
                if (i3 != 0 && i2 != 0) {
                    CareFriendMainActivity.this.mHomeFunctionBean.add(new HomeFunctionBean(i3 + "/" + i2, "mmHg", CareFriendMainActivity.this.getString(R.string.home_blood_pressure_title), "血压", CareFriendMainActivity.this.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_bp), true));
                } else {
                    CareFriendMainActivity.this.mHomeFunctionBean.add(new HomeFunctionBean("--/--", "mmHg", CareFriendMainActivity.this.getString(R.string.home_blood_pressure_title), "血压", CareFriendMainActivity.this.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_bp), true));
                }
                CareFriendMainActivity careFriendMainActivity = CareFriendMainActivity.this;
                careFriendMainActivity.notifyRecycle(careFriendMainActivity.mHomeFunctionBean);
            }
        });
    }

    public void getHeartRequest(String devId, String day) {
        HashMap map = new HashMap();
        map.put("userId", devId);
        map.put("day", day);
        HttpUtils.getInstance().postMsgAsynHttp(this.context, Constants.heartDayUrl, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.care.activity.CareFriendMainActivity.10
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                int i2;
                ArrayList arrayList = new ArrayList();
                if (result != null) {
                    try {
                        Iterator<FriendCareDataBean.Data> it2 = ((FriendCareDataBean) CareFriendMainActivity.this.mGson.fromJson(result, FriendCareDataBean.class)).data.iterator();
                        while (it2.hasNext()) {
                            arrayList.addAll((Collection) CareFriendMainActivity.this.mGson.fromJson(it2.next().mlist, new TypeToken<List<FriendCareHeartBean>>() { // from class: com.yucheng.smarthealthpro.care.activity.CareFriendMainActivity.10.1
                            }.getType()));
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        Logger.d("chong-------heart_result==" + result);
                    }
                }
                Iterator<FriendCareHeartBean> it3 = Tools.sortCareHeartData(arrayList).iterator();
                while (true) {
                    if (!it3.hasNext()) {
                        i2 = 0;
                        break;
                    }
                    FriendCareHeartBean next = it3.next();
                    if (next.heartTimes >= TransUtils.HEART_RATE_VISIBLE_MIN && next.heartTimes <= TransUtils.HEART_RATE_VISIBLE_MAX) {
                        i2 = next.heartTimes;
                        break;
                    }
                }
                if (i2 != 0) {
                    CareFriendMainActivity.this.mHomeFunctionBean.add(new HomeFunctionBean(i2 + "", "bpm", CareFriendMainActivity.this.getString(R.string.function_heart), "心率", CareFriendMainActivity.this.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_hr), true));
                } else {
                    CareFriendMainActivity.this.mHomeFunctionBean.add(new HomeFunctionBean("--", "bpm", CareFriendMainActivity.this.getString(R.string.function_heart), "心率", CareFriendMainActivity.this.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_hr), true));
                }
                CareFriendMainActivity careFriendMainActivity = CareFriendMainActivity.this;
                careFriendMainActivity.notifyRecycle(careFriendMainActivity.mHomeFunctionBean);
            }
        });
    }

    public void getDaySleep(final String devId, final String dateTime) {
        List<HistorySleepResponse.SleepBean> list = this.todayHistorySleepList;
        if (list != null) {
            list.clear();
        }
        List<HistorySleepResponse.SleepBean> list2 = this.yesterdayHistorySleepList;
        if (list2 != null) {
            list2.clear();
        }
        List<HistorySleepResponse.SleepBean> list3 = this.slist;
        if (list3 != null) {
            list3.clear();
        }
        HashMap map = new HashMap();
        map.put("userId", devId);
        map.put("day", dateTime);
        HttpUtils.getInstance().postMsgAsynHttp(this, Constants.sleepDayUrl, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.care.activity.CareFriendMainActivity.11
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                HistorySleepResponse historySleepResponse;
                List<HistorySleepResponse.SleepBean> list4;
                if (result != null) {
                    try {
                        historySleepResponse = (HistorySleepResponse) CareFriendMainActivity.this.mGson.fromJson(result, HistorySleepResponse.class);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        Logger.d("chong----------result==" + result);
                        historySleepResponse = null;
                    }
                    if (historySleepResponse != null && (list4 = historySleepResponse.data) != null && list4.size() > 0) {
                        CareFriendMainActivity.this.todayHistorySleepList.addAll(list4);
                    }
                }
                CareFriendMainActivity.this.getCareYesterdaySleepRequest(devId, dateTime);
            }
        });
    }

    public void getCareYesterdaySleepRequest(String userId, final String day) {
        ArrayList<String> pastStringArray = YearToDayListUtils.getPastStringArray(day, 1);
        HashMap map = new HashMap();
        map.put("userId", userId);
        map.put("day", pastStringArray.get(0));
        HttpUtils.getInstance().postMsgAsynHttp(this, Constants.sleepDayUrl, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.care.activity.CareFriendMainActivity.12
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                HistorySleepResponse historySleepResponse;
                List<HistorySleepResponse.SleepBean> list;
                if (result != null) {
                    try {
                        historySleepResponse = (HistorySleepResponse) CareFriendMainActivity.this.mGson.fromJson(result, HistorySleepResponse.class);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        Logger.d("chong--------result==" + result);
                        historySleepResponse = null;
                    }
                    if (historySleepResponse != null && (list = historySleepResponse.data) != null && list.size() > 0) {
                        CareFriendMainActivity.this.yesterdayHistorySleepList.addAll(list);
                    }
                }
                CareFriendMainActivity.this.setDayData(day);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDayData(String day) {
        this.yesterdayHistorySleepList = Tools.sortListSleep(this.yesterdayHistorySleepList);
        List<HistorySleepResponse.SleepBean> listSortListSleep = Tools.sortListSleep(this.todayHistorySleepList);
        this.todayHistorySleepList = listSortListSleep;
        this.slist.addAll(GetFixedDataOfSleep(this.yesterdayHistorySleepList, listSortListSleep, day));
        Long.valueOf(0L);
        Long lValueOf = 0L;
        Long lValueOf2 = null;
        int i2 = 0;
        for (HistorySleepResponse.SleepBean sleepBean : this.slist) {
            int i3 = sleepBean.remTimes + sleepBean.dsTimes + sleepBean.qsTimes;
            if (i3 <= 57600) {
                int i4 = sleepBean.dsTimes;
                int i5 = sleepBean.qsTimes;
                int i6 = sleepBean.remTimes;
                try {
                    long time = Tools.getTime(sleepBean.beginTime);
                    long time2 = Tools.getTime(sleepBean.endTime);
                    if (lValueOf.longValue() == 0 || lValueOf.longValue() > time) {
                        lValueOf = Long.valueOf(time);
                    }
                    if (lValueOf2.longValue() == 0 || lValueOf2.longValue() < time2) {
                        lValueOf2 = Long.valueOf(time2);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                String str = sleepBean.mlist;
                Logger.d("chong------------qstimes==" + sleepBean.qsTimes + "--" + sleepBean.dsTimes);
                if (sleepBean.qsTimes == 0 && sleepBean.dsTimes == 0) {
                    try {
                        JSONArray jSONArray = new JSONArray("[" + str.replaceAll("\\[", "").replaceAll("]", "").replaceAll("\\\\", "") + "]");
                        for (int i7 = 0; i7 < jSONArray.length(); i7++) {
                            int i8 = jSONArray.getJSONObject(i7).getInt("sleepLong") + i2;
                            if (i8 <= 57600) {
                                i2 = i8;
                            }
                        }
                    } catch (JSONException e3) {
                        e3.printStackTrace();
                    }
                } else {
                    i2 += i3;
                }
            }
        }
        if (i2 > 57600) {
            i2 = 57600;
        }
        if (i2 != 0) {
            this.mHomeFunctionBean.add(new HomeFunctionBean(i2 + "", "mins", getString(R.string.home_sleep_title), "睡眠", this.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_sleep), true));
        } else {
            this.mHomeFunctionBean.add(new HomeFunctionBean("00", "mins", getString(R.string.home_sleep_title), "睡眠", this.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_sleep), true));
        }
        notifyRecycle(this.mHomeFunctionBean);
    }

    public List<HistorySleepResponse.SleepBean> GetFixedDataOfSleep(List<HistorySleepResponse.SleepBean> list1, List<HistorySleepResponse.SleepBean> list2, String dateString) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        arrayList2.addAll(list1);
        arrayList2.addAll(list2);
        int size = arrayList2.size();
        long stringToDate = TimeStampUtils.getStringToDate(dateString, AppDateMgr.DF_YYYY_MM_DD);
        long time = stringToDate - 14400000;
        long j2 = stringToDate + 43200000;
        for (int i2 = 0; i2 < size; i2++) {
            if (Tools.getTime(((HistorySleepResponse.SleepBean) arrayList2.get(i2)).beginTime) >= time && Tools.getTime(((HistorySleepResponse.SleepBean) arrayList2.get(i2)).beginTime) < j2) {
                arrayList.add((HistorySleepResponse.SleepBean) arrayList2.get(i2));
                time = Tools.getTime(((HistorySleepResponse.SleepBean) arrayList2.get(i2)).endTime);
            }
        }
        return arrayList;
    }

    private void requestElectrocardiogramData(String devId, String day) {
        HashMap map = new HashMap();
        map.put("userId", devId);
        map.put("day", day);
        Logger.d("chong-----devid==" + devId + "--" + day);
        HttpUtils.getInstance().postMsgAsynHttp(this, Constants.HeartLineSingle, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.care.activity.CareFriendMainActivity.13
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                Log.i("CareFriendMain", "--ElectrocardiogramData--" + result);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getBloodOxygen() {
        HashMap map = new HashMap();
        map.put("userId", this.devId);
        map.put("day", this.dateTime);
        HttpUtils.getInstance().postMsgAsynHttp(this, Constants.bloodOxygenDayUrl, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.care.activity.CareFriendMainActivity.14
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                int i2;
                ArrayList arrayList = new ArrayList();
                if (result != null) {
                    try {
                        Iterator<FriendCareDataBean.Data> it2 = ((FriendCareDataBean) CareFriendMainActivity.this.mGson.fromJson(result, FriendCareDataBean.class)).data.iterator();
                        while (it2.hasNext()) {
                            arrayList.addAll((Collection) CareFriendMainActivity.this.mGson.fromJson(it2.next().mlist, new TypeToken<List<FriendCareSpo2Bean>>() { // from class: com.yucheng.smarthealthpro.care.activity.CareFriendMainActivity.14.1
                            }.getType()));
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        Logger.d("chong-------spo2_result==" + result);
                    }
                }
                Iterator<FriendCareSpo2Bean> it3 = Tools.sortSpo2Data(arrayList).iterator();
                while (true) {
                    if (!it3.hasNext()) {
                        i2 = 0;
                        break;
                    }
                    FriendCareSpo2Bean next = it3.next();
                    if (next.bloodoxygen >= TransUtils.BLOOD_OXYGEN_VISIBLE_MIN && next.bloodoxygen <= TransUtils.BLOOD_OXYGEN_VISIBLE_MAX) {
                        i2 = next.bloodoxygen;
                        break;
                    }
                }
                if (i2 != 0) {
                    CareFriendMainActivity.this.mHomeFunctionBean.add(new HomeFunctionBean(i2 + "", "%", CareFriendMainActivity.this.getString(R.string.home_blood_oxygen_title), "血氧", CareFriendMainActivity.this.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_bo), true));
                    CareFriendMainActivity careFriendMainActivity = CareFriendMainActivity.this;
                    careFriendMainActivity.notifyRecycle(careFriendMainActivity.mHomeFunctionBean);
                } else {
                    CareFriendMainActivity.this.mHomeFunctionBean.add(new HomeFunctionBean("--", "%", CareFriendMainActivity.this.getString(R.string.home_blood_oxygen_title), "血氧", CareFriendMainActivity.this.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_bo), true));
                    CareFriendMainActivity careFriendMainActivity2 = CareFriendMainActivity.this;
                    careFriendMainActivity2.notifyRecycle(careFriendMainActivity2.mHomeFunctionBean);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getRate() {
        HashMap map = new HashMap();
        map.put("userId", this.devId);
        map.put("day", this.dateTime);
        HttpUtils.getInstance().postMsgAsynHttp(this, Constants.respiratoryRateDayUrl, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.care.activity.CareFriendMainActivity.15
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                int i2;
                ArrayList arrayList = new ArrayList();
                if (result != null) {
                    try {
                        Iterator<FriendCareDataBean.Data> it2 = ((FriendCareDataBean) CareFriendMainActivity.this.mGson.fromJson(result, FriendCareDataBean.class)).data.iterator();
                        while (it2.hasNext()) {
                            arrayList.addAll((Collection) CareFriendMainActivity.this.mGson.fromJson(it2.next().mlist, new TypeToken<List<FriendCareRateBean>>() { // from class: com.yucheng.smarthealthpro.care.activity.CareFriendMainActivity.15.1
                            }.getType()));
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                Iterator<FriendCareRateBean> it3 = Tools.sortCareRateData(arrayList).iterator();
                while (true) {
                    if (!it3.hasNext()) {
                        i2 = 0;
                        break;
                    }
                    FriendCareRateBean next = it3.next();
                    if (next.respiratoryrate >= TransUtils.RESPIRATORY_RATE_VISIBLE_MIN && next.respiratoryrate <= TransUtils.RESPIRATORY_RATE_VISIBLE_MAX) {
                        i2 = next.respiratoryrate;
                        break;
                    }
                }
                if (i2 != 0) {
                    CareFriendMainActivity.this.mHomeFunctionBean.add(new HomeFunctionBean(i2 + "", "rpm", CareFriendMainActivity.this.getString(R.string.home_respiratory_rate_title), "呼吸率", CareFriendMainActivity.this.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_rr), true));
                    CareFriendMainActivity careFriendMainActivity = CareFriendMainActivity.this;
                    careFriendMainActivity.notifyRecycle(careFriendMainActivity.mHomeFunctionBean);
                } else {
                    CareFriendMainActivity.this.mHomeFunctionBean.add(new HomeFunctionBean("--", "rpm", CareFriendMainActivity.this.getString(R.string.home_respiratory_rate_title), "呼吸率", CareFriendMainActivity.this.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_rr), true));
                    CareFriendMainActivity careFriendMainActivity2 = CareFriendMainActivity.this;
                    careFriendMainActivity2.notifyRecycle(careFriendMainActivity2.mHomeFunctionBean);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getTemp() {
        HashMap map = new HashMap();
        map.put("userId", this.devId);
        map.put("day", this.dateTime);
        HttpUtils.getInstance().postMsgAsynHttp(this, Constants.temperatureDayUrl, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.care.activity.CareFriendMainActivity.16
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                float f2;
                ArrayList arrayList = new ArrayList();
                if (result != null) {
                    try {
                        Iterator<FriendCareDataBean.Data> it2 = ((FriendCareDataBean) CareFriendMainActivity.this.mGson.fromJson(result, FriendCareDataBean.class)).data.iterator();
                        while (it2.hasNext()) {
                            arrayList.addAll((Collection) CareFriendMainActivity.this.mGson.fromJson(it2.next().mlist, new TypeToken<List<FriendCareTempBean>>() { // from class: com.yucheng.smarthealthpro.care.activity.CareFriendMainActivity.16.1
                            }.getType()));
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                Iterator<FriendCareTempBean> it3 = Tools.sortCareTempRepeatData(arrayList).iterator();
                while (true) {
                    if (!it3.hasNext()) {
                        f2 = 0.0f;
                        break;
                    }
                    FriendCareTempBean next = it3.next();
                    if (next.temperature >= TransUtils.TEMPERATURE_VISIBLE_MIN && next.temperature <= TransUtils.TEMPERATURE_VISIBLE_MAX && !(next.temperature + "").endsWith(".15")) {
                        f2 = next.temperature;
                        break;
                    }
                }
                if (CareFriendMainActivity.this.mTempUnit == 0) {
                    if (f2 != 0.0f) {
                        CareFriendMainActivity.this.mHomeFunctionBean.add(new HomeFunctionBean(f2 + "", Constant.SpConstValue.TEMP_ISO, CareFriendMainActivity.this.getString(R.string.function_temp), "温度", CareFriendMainActivity.this.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_tp), true));
                        CareFriendMainActivity careFriendMainActivity = CareFriendMainActivity.this;
                        careFriendMainActivity.notifyRecycle(careFriendMainActivity.mHomeFunctionBean);
                        return;
                    } else {
                        CareFriendMainActivity.this.mHomeFunctionBean.add(new HomeFunctionBean("--", Constant.SpConstValue.TEMP_ISO, CareFriendMainActivity.this.getString(R.string.function_temp), "温度", CareFriendMainActivity.this.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_tp), true));
                        CareFriendMainActivity careFriendMainActivity2 = CareFriendMainActivity.this;
                        careFriendMainActivity2.notifyRecycle(careFriendMainActivity2.mHomeFunctionBean);
                        return;
                    }
                }
                if (f2 != 0.0f) {
                    CareFriendMainActivity.this.mHomeFunctionBean.add(new HomeFunctionBean(FormatUtil.getBigDecimal((f2 * 1.8d) + 32.0d).setScale(1, RoundingMode.HALF_UP).floatValue() + "", Constant.SpConstValue.TEMP_INCH, CareFriendMainActivity.this.getString(R.string.function_temp), "温度", CareFriendMainActivity.this.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_tp), true));
                    CareFriendMainActivity careFriendMainActivity3 = CareFriendMainActivity.this;
                    careFriendMainActivity3.notifyRecycle(careFriendMainActivity3.mHomeFunctionBean);
                } else {
                    CareFriendMainActivity.this.mHomeFunctionBean.add(new HomeFunctionBean("--", Constant.SpConstValue.TEMP_INCH, CareFriendMainActivity.this.getString(R.string.function_temp), "温度", CareFriendMainActivity.this.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_tp), true));
                    CareFriendMainActivity careFriendMainActivity4 = CareFriendMainActivity.this;
                    careFriendMainActivity4.notifyRecycle(careFriendMainActivity4.mHomeFunctionBean);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getBloodSugar() {
        HashMap map = new HashMap();
        map.put("userId", this.devId);
        map.put("day", this.dateTime);
        HttpUtils.getInstance().postMsgAsynHttp(this, Constants.BLOODSUGARDAYURL, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.care.activity.CareFriendMainActivity.17
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                float f2;
                if (result != null) {
                    ArrayList arrayList = new ArrayList();
                    try {
                        Iterator<FriendCareDataBean.Data> it2 = ((FriendCareDataBean) CareFriendMainActivity.this.mGson.fromJson(result, FriendCareDataBean.class)).data.iterator();
                        while (it2.hasNext()) {
                            arrayList.addAll((Collection) CareFriendMainActivity.this.mGson.fromJson(it2.next().mlist, new TypeToken<List<FriendCareBloodSugarBean>>() { // from class: com.yucheng.smarthealthpro.care.activity.CareFriendMainActivity.17.1
                            }.getType()));
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        Logger.d("chong------blood_sugar_result==" + result);
                    }
                    Iterator<FriendCareBloodSugarBean> it3 = Tools.sortCareListBloodSugar(arrayList).iterator();
                    while (true) {
                        if (!it3.hasNext()) {
                            f2 = 0.0f;
                            break;
                        }
                        FriendCareBloodSugarBean next = it3.next();
                        if (next.bloodSugar >= TransUtils.BLOOD_SUGAR_VISIBLE_MIN_2 && next.bloodSugar <= TransUtils.BLOOD_SUGAR_VISIBLE_MAX_2) {
                            f2 = next.bloodSugar;
                            break;
                        }
                    }
                    String string = CareFriendMainActivity.this.getString(R.string.blood_sugar_unit_1);
                    String str = (String) SharedPreferencesUtils.get(CareFriendMainActivity.this.context, Constant.SpConstKey.BLOOD_SUGAR_AND_BLOOD_FAT_UNIT, "");
                    if (str != null && str.equals("mg/dL")) {
                        string = CareFriendMainActivity.this.getString(R.string.blood_sugar_unit_2);
                        f2 *= 18.0f;
                    }
                    String str2 = string;
                    if (f2 != 0.0f) {
                        CareFriendMainActivity.this.mHomeFunctionBean.add(new HomeFunctionBean(FormatUtil.getBigDecimal(f2).setScale(1, RoundingMode.HALF_UP).toString(), str2, CareFriendMainActivity.this.getString(R.string.home_blood_sugar_title), "血糖", CareFriendMainActivity.this.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_blood_sugar), true));
                        CareFriendMainActivity careFriendMainActivity = CareFriendMainActivity.this;
                        careFriendMainActivity.notifyRecycle(careFriendMainActivity.mHomeFunctionBean);
                    } else {
                        CareFriendMainActivity.this.mHomeFunctionBean.add(new HomeFunctionBean("--", str2, CareFriendMainActivity.this.getString(R.string.home_blood_sugar_title), "血糖", CareFriendMainActivity.this.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_blood_sugar), true));
                        CareFriendMainActivity careFriendMainActivity2 = CareFriendMainActivity.this;
                        careFriendMainActivity2.notifyRecycle(careFriendMainActivity2.mHomeFunctionBean);
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getBloodFat() {
        HashMap map = new HashMap();
        map.put("userId", this.devId);
        map.put("day", this.dateTime);
        HttpUtils.getInstance().postMsgAsynHttp(this, Constants.bloodFatDayUrl, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.care.activity.CareFriendMainActivity.18
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                float f2;
                if (result != null) {
                    ArrayList arrayList = new ArrayList();
                    try {
                        Iterator<FriendCareBloodFatBean.Data> it2 = ((FriendCareBloodFatBean) CareFriendMainActivity.this.mGson.fromJson(result, FriendCareBloodFatBean.class)).data.iterator();
                        while (it2.hasNext()) {
                            arrayList.addAll((Collection) CareFriendMainActivity.this.mGson.fromJson(it2.next().mlist, new TypeToken<List<FriendCareBloodFatBean.BloodFat>>() { // from class: com.yucheng.smarthealthpro.care.activity.CareFriendMainActivity.18.1
                            }.getType()));
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    Tools.sortCareListBloodFat(arrayList);
                    Iterator it3 = arrayList.iterator();
                    while (true) {
                        if (!it3.hasNext()) {
                            f2 = 0.0f;
                            break;
                        }
                        FriendCareBloodFatBean.BloodFat bloodFat = (FriendCareBloodFatBean.BloodFat) it3.next();
                        if (bloodFat.tc >= TransUtils.BLOOD_FAT_VISIBLE_MIN && bloodFat.tc <= TransUtils.BLOOD_FAT_VISIBLE_MAX) {
                            f2 = bloodFat.tc;
                            break;
                        }
                    }
                    String str = (String) SharedPreferencesUtils.get(CareFriendMainActivity.this.context, Constant.SpConstKey.BLOOD_SUGAR_AND_BLOOD_FAT_UNIT, CareFriendMainActivity.this.getString(R.string.blood_sugar_unit_1));
                    String strBloodFatMmol2Mg = FormatUtil.keep2(f2) + "";
                    if (str != null && str.equals("mg/dL")) {
                        strBloodFatMmol2Mg = TransUtils.bloodFatMmol2Mg(f2);
                    }
                    String str2 = strBloodFatMmol2Mg;
                    if (f2 != 0.0f) {
                        CareFriendMainActivity.this.mHomeFunctionBean.add(new HomeFunctionBean(str2, str, CareFriendMainActivity.this.getString(R.string.blood_fat), "血脂", CareFriendMainActivity.this.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_blood_fat), true));
                        CareFriendMainActivity careFriendMainActivity = CareFriendMainActivity.this;
                        careFriendMainActivity.notifyRecycle(careFriendMainActivity.mHomeFunctionBean);
                    } else {
                        CareFriendMainActivity.this.mHomeFunctionBean.add(new HomeFunctionBean("--", str, CareFriendMainActivity.this.getString(R.string.blood_fat), "血脂", CareFriendMainActivity.this.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_blood_fat), true));
                        CareFriendMainActivity careFriendMainActivity2 = CareFriendMainActivity.this;
                        careFriendMainActivity2.notifyRecycle(careFriendMainActivity2.mHomeFunctionBean);
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getHRV() {
        HashMap map = new HashMap();
        map.put("userId", this.devId);
        map.put("day", this.dateTime);
        HttpUtils.getInstance().postMsgAsynHttp(this, Constants.hrvDayUrl, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.care.activity.CareFriendMainActivity.19
            /* JADX WARN: Code restructure failed: missing block: B:14:0x005c, code lost:
            
                r0 = r1.hrv;
             */
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void onSuccess(java.lang.String r11) {
                /*
                    r10 = this;
                    if (r11 == 0) goto Le7
                    com.google.gson.Gson r0 = new com.google.gson.Gson
                    r0.<init>()
                    java.lang.Class<com.yucheng.smarthealthpro.care.bean.HistoryHRVResponse> r1 = com.yucheng.smarthealthpro.care.bean.HistoryHRVResponse.class
                    java.lang.Object r11 = r0.fromJson(r11, r1)
                    com.yucheng.smarthealthpro.care.bean.HistoryHRVResponse r11 = (com.yucheng.smarthealthpro.care.bean.HistoryHRVResponse) r11
                    java.util.ArrayList r0 = new java.util.ArrayList
                    r0.<init>()
                    r0 = 0
                    java.util.List<com.yucheng.smarthealthpro.care.bean.HistoryHRVResponse$HRVBean> r1 = r11.data     // Catch: java.lang.Exception -> L5e
                    int r1 = r1.size()     // Catch: java.lang.Exception -> L5e
                    if (r1 == 0) goto L62
                    com.google.gson.Gson r1 = new com.google.gson.Gson     // Catch: java.lang.Exception -> L5e
                    r1.<init>()     // Catch: java.lang.Exception -> L5e
                    java.util.List<com.yucheng.smarthealthpro.care.bean.HistoryHRVResponse$HRVBean> r11 = r11.data     // Catch: java.lang.Exception -> L5e
                    java.lang.Object r11 = r11.get(r0)     // Catch: java.lang.Exception -> L5e
                    com.yucheng.smarthealthpro.care.bean.HistoryHRVResponse$HRVBean r11 = (com.yucheng.smarthealthpro.care.bean.HistoryHRVResponse.HRVBean) r11     // Catch: java.lang.Exception -> L5e
                    java.lang.String r11 = r11.mlist     // Catch: java.lang.Exception -> L5e
                    com.yucheng.smarthealthpro.care.activity.CareFriendMainActivity$19$1 r2 = new com.yucheng.smarthealthpro.care.activity.CareFriendMainActivity$19$1     // Catch: java.lang.Exception -> L5e
                    r2.<init>()     // Catch: java.lang.Exception -> L5e
                    java.lang.reflect.Type r2 = r2.getType()     // Catch: java.lang.Exception -> L5e
                    java.lang.Object r11 = r1.fromJson(r11, r2)     // Catch: java.lang.Exception -> L5e
                    java.util.List r11 = (java.util.List) r11     // Catch: java.lang.Exception -> L5e
                    com.yucheng.smarthealthpro.utils.Tools.sortCareListHRV(r11)     // Catch: java.lang.Exception -> L5e
                    java.util.Iterator r11 = r11.iterator()     // Catch: java.lang.Exception -> L5e
                L42:
                    boolean r1 = r11.hasNext()     // Catch: java.lang.Exception -> L5e
                    if (r1 == 0) goto L62
                    java.lang.Object r1 = r11.next()     // Catch: java.lang.Exception -> L5e
                    com.yucheng.smarthealthpro.care.bean.HistoryHRVResponse$Mlist r1 = (com.yucheng.smarthealthpro.care.bean.HistoryHRVResponse.Mlist) r1     // Catch: java.lang.Exception -> L5e
                    int r2 = r1.hrv     // Catch: java.lang.Exception -> L5e
                    int r3 = com.yucheng.smarthealthpro.utils.TransUtils.HRV_VISIBLE_MIN     // Catch: java.lang.Exception -> L5e
                    if (r2 < r3) goto L42
                    int r2 = r1.hrv     // Catch: java.lang.Exception -> L5e
                    int r3 = com.yucheng.smarthealthpro.utils.TransUtils.HRV_VISIBLE_MAX     // Catch: java.lang.Exception -> L5e
                    if (r2 > r3) goto L42
                    int r11 = r1.hrv     // Catch: java.lang.Exception -> L5e
                    r0 = r11
                    goto L62
                L5e:
                    r11 = move-exception
                    r11.printStackTrace()
                L62:
                    com.yucheng.smarthealthpro.care.activity.CareFriendMainActivity r11 = com.yucheng.smarthealthpro.care.activity.CareFriendMainActivity.this
                    int r1 = com.yucheng.smarthealthpro.R.string.hrv_unit_ms
                    java.lang.String r4 = r11.getString(r1)
                    r11 = 1
                    if (r0 == 0) goto Lb3
                    com.yucheng.smarthealthpro.care.activity.CareFriendMainActivity r1 = com.yucheng.smarthealthpro.care.activity.CareFriendMainActivity.this
                    java.util.List r1 = com.yucheng.smarthealthpro.care.activity.CareFriendMainActivity.m990$$Nest$fgetmHomeFunctionBean(r1)
                    com.yucheng.smarthealthpro.home.bean.HomeFunctionBean r9 = new com.yucheng.smarthealthpro.home.bean.HomeFunctionBean
                    java.lang.StringBuilder r2 = new java.lang.StringBuilder
                    r2.<init>()
                    java.lang.StringBuilder r0 = r2.append(r0)
                    java.lang.String r2 = ""
                    java.lang.StringBuilder r0 = r0.append(r2)
                    java.lang.String r3 = r0.toString()
                    com.yucheng.smarthealthpro.care.activity.CareFriendMainActivity r0 = com.yucheng.smarthealthpro.care.activity.CareFriendMainActivity.this
                    int r2 = com.yucheng.smarthealthpro.R.string.hrv_unit
                    java.lang.String r5 = r0.getString(r2)
                    com.yucheng.smarthealthpro.care.activity.CareFriendMainActivity r0 = com.yucheng.smarthealthpro.care.activity.CareFriendMainActivity.this
                    com.yucheng.smarthealthpro.utils.AppImageMgr r0 = com.yucheng.smarthealthpro.care.activity.CareFriendMainActivity.m986$$Nest$fgetmAppImageMgr(r0)
                    int r2 = com.yucheng.smarthealthpro.R.mipmap.home_measure_icon_hrv
                    android.graphics.Bitmap r7 = r0.getBitmap(r2)
                    java.lang.Boolean r8 = java.lang.Boolean.valueOf(r11)
                    java.lang.String r6 = "HRV"
                    r2 = r9
                    r2.<init>(r3, r4, r5, r6, r7, r8)
                    r1.add(r9)
                    com.yucheng.smarthealthpro.care.activity.CareFriendMainActivity r11 = com.yucheng.smarthealthpro.care.activity.CareFriendMainActivity.this
                    java.util.List r0 = com.yucheng.smarthealthpro.care.activity.CareFriendMainActivity.m990$$Nest$fgetmHomeFunctionBean(r11)
                    com.yucheng.smarthealthpro.care.activity.CareFriendMainActivity.m1005$$Nest$mnotifyRecycle(r11, r0)
                    goto Le7
                Lb3:
                    com.yucheng.smarthealthpro.care.activity.CareFriendMainActivity r0 = com.yucheng.smarthealthpro.care.activity.CareFriendMainActivity.this
                    java.util.List r0 = com.yucheng.smarthealthpro.care.activity.CareFriendMainActivity.m990$$Nest$fgetmHomeFunctionBean(r0)
                    com.yucheng.smarthealthpro.home.bean.HomeFunctionBean r1 = new com.yucheng.smarthealthpro.home.bean.HomeFunctionBean
                    com.yucheng.smarthealthpro.care.activity.CareFriendMainActivity r2 = com.yucheng.smarthealthpro.care.activity.CareFriendMainActivity.this
                    int r3 = com.yucheng.smarthealthpro.R.string.hrv_unit
                    java.lang.String r5 = r2.getString(r3)
                    com.yucheng.smarthealthpro.care.activity.CareFriendMainActivity r2 = com.yucheng.smarthealthpro.care.activity.CareFriendMainActivity.this
                    com.yucheng.smarthealthpro.utils.AppImageMgr r2 = com.yucheng.smarthealthpro.care.activity.CareFriendMainActivity.m986$$Nest$fgetmAppImageMgr(r2)
                    int r3 = com.yucheng.smarthealthpro.R.mipmap.home_measure_icon_hrv
                    android.graphics.Bitmap r7 = r2.getBitmap(r3)
                    java.lang.Boolean r8 = java.lang.Boolean.valueOf(r11)
                    java.lang.String r3 = "--"
                    java.lang.String r6 = "HRV"
                    r2 = r1
                    r2.<init>(r3, r4, r5, r6, r7, r8)
                    r0.add(r1)
                    com.yucheng.smarthealthpro.care.activity.CareFriendMainActivity r11 = com.yucheng.smarthealthpro.care.activity.CareFriendMainActivity.this
                    java.util.List r0 = com.yucheng.smarthealthpro.care.activity.CareFriendMainActivity.m990$$Nest$fgetmHomeFunctionBean(r11)
                    com.yucheng.smarthealthpro.care.activity.CareFriendMainActivity.m1005$$Nest$mnotifyRecycle(r11, r0)
                Le7:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.care.activity.CareFriendMainActivity.AnonymousClass19.onSuccess(java.lang.String):void");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getUricAcid() {
        HashMap map = new HashMap();
        map.put("userId", this.devId);
        map.put("day", this.dateTime);
        HttpUtils.getInstance().postMsgAsynHttp(this, Constants.uricAcidDayUrl, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.care.activity.CareFriendMainActivity.20
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                float f2;
                String str;
                if (result != null) {
                    ArrayList arrayList = new ArrayList();
                    try {
                        Iterator<FriendCareUricAcidBean.Data> it2 = ((FriendCareUricAcidBean) CareFriendMainActivity.this.mGson.fromJson(result, FriendCareUricAcidBean.class)).data.iterator();
                        while (it2.hasNext()) {
                            arrayList.addAll((Collection) CareFriendMainActivity.this.mGson.fromJson(it2.next().mlist, new TypeToken<List<FriendCareUricAcidBean.UricAcid>>() { // from class: com.yucheng.smarthealthpro.care.activity.CareFriendMainActivity.20.1
                            }.getType()));
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    Tools.sortCareListUricAcid(arrayList);
                    Iterator it3 = arrayList.iterator();
                    while (true) {
                        if (!it3.hasNext()) {
                            f2 = 0.0f;
                            break;
                        }
                        FriendCareUricAcidBean.UricAcid uricAcid = (FriendCareUricAcidBean.UricAcid) it3.next();
                        if (uricAcid.uricAcid >= TransUtils.URIC_ACID_VISIBLE_MIN && uricAcid.uricAcid <= TransUtils.URIC_ACID_VISIBLE_MAX) {
                            f2 = uricAcid.uricAcid;
                            break;
                        }
                    }
                    String string = CareFriendMainActivity.this.getString(R.string.uric_acid_unit_1);
                    String string2 = CareFriendMainActivity.this.getString(R.string.uric_acid_unit_2);
                    String str2 = (String) SharedPreferencesUtils.get(CareFriendMainActivity.this.context, Constant.SpConstKey.URIC_ACID_UNIT, string);
                    String strUricAcidUmol2Mg = ((int) f2) + "";
                    if (string2.equals(str2)) {
                        strUricAcidUmol2Mg = TransUtils.uricAcidUmol2Mg(f2);
                        str = string2;
                    } else {
                        str = str2;
                    }
                    String str3 = strUricAcidUmol2Mg;
                    if (f2 != 0.0f) {
                        CareFriendMainActivity.this.mHomeFunctionBean.add(new HomeFunctionBean(str3, str, CareFriendMainActivity.this.getString(R.string.uric_acid), "尿酸", CareFriendMainActivity.this.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_uric_acid), true));
                        CareFriendMainActivity careFriendMainActivity = CareFriendMainActivity.this;
                        careFriendMainActivity.notifyRecycle(careFriendMainActivity.mHomeFunctionBean);
                    } else {
                        CareFriendMainActivity.this.mHomeFunctionBean.add(new HomeFunctionBean("--", str, CareFriendMainActivity.this.getString(R.string.uric_acid), "尿酸", CareFriendMainActivity.this.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_uric_acid), true));
                        CareFriendMainActivity careFriendMainActivity2 = CareFriendMainActivity.this;
                        careFriendMainActivity2.notifyRecycle(careFriendMainActivity2.mHomeFunctionBean);
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getBloodKetone() {
        HashMap map = new HashMap();
        map.put("userId", this.devId);
        map.put("day", this.dateTime);
        HttpUtils.getInstance().postMsgAsynHttp(this, Constants.ketoneDayUrl, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.care.activity.CareFriendMainActivity.21
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                float f2;
                String str;
                if (result != null) {
                    ArrayList arrayList = new ArrayList();
                    try {
                        Iterator<FriendCareKetoneBean.Data> it2 = ((FriendCareKetoneBean) CareFriendMainActivity.this.mGson.fromJson(result, FriendCareKetoneBean.class)).data.iterator();
                        while (it2.hasNext()) {
                            arrayList.addAll((Collection) CareFriendMainActivity.this.mGson.fromJson(it2.next().mlist, new TypeToken<List<FriendCareKetoneBean.BloodKetone>>() { // from class: com.yucheng.smarthealthpro.care.activity.CareFriendMainActivity.21.1
                            }.getType()));
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    Tools.sortCareListBloodKetone(arrayList);
                    Iterator it3 = arrayList.iterator();
                    while (true) {
                        if (!it3.hasNext()) {
                            f2 = 0.0f;
                            break;
                        }
                        FriendCareKetoneBean.BloodKetone bloodKetone = (FriendCareKetoneBean.BloodKetone) it3.next();
                        if (bloodKetone.bloodKetone >= TransUtils.KETONE_VISIBLE_MIN && bloodKetone.bloodKetone <= TransUtils.KETONE_VISIBLE_MAX) {
                            f2 = bloodKetone.bloodKetone;
                            break;
                        }
                    }
                    String string = CareFriendMainActivity.this.getString(R.string.uric_acid_unit_1);
                    String string2 = CareFriendMainActivity.this.getString(R.string.uric_acid_unit_2);
                    String str2 = (String) SharedPreferencesUtils.get(CareFriendMainActivity.this.context, Constant.SpConstKey.URIC_ACID_UNIT, string);
                    String strUricAcidUmol2Mg = ((int) f2) + "";
                    if (string2.equals(str2)) {
                        strUricAcidUmol2Mg = TransUtils.uricAcidUmol2Mg(f2);
                        str = string2;
                    } else {
                        str = str2;
                    }
                    String str3 = strUricAcidUmol2Mg;
                    if (f2 != 0.0f) {
                        CareFriendMainActivity.this.mHomeFunctionBean.add(new HomeFunctionBean(str3, str, CareFriendMainActivity.this.getString(R.string.blood_ketones), "血酮", CareFriendMainActivity.this.mAppImageMgr.getBitmap(R.mipmap.home_blood_ketone), true));
                        CareFriendMainActivity careFriendMainActivity = CareFriendMainActivity.this;
                        careFriendMainActivity.notifyRecycle(careFriendMainActivity.mHomeFunctionBean);
                    } else {
                        CareFriendMainActivity.this.mHomeFunctionBean.add(new HomeFunctionBean("--", str, CareFriendMainActivity.this.getString(R.string.blood_ketones), "血酮", CareFriendMainActivity.this.mAppImageMgr.getBitmap(R.mipmap.home_blood_ketone), true));
                        CareFriendMainActivity careFriendMainActivity2 = CareFriendMainActivity.this;
                        careFriendMainActivity2.notifyRecycle(careFriendMainActivity2.mHomeFunctionBean);
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getLaserConditioningTherapy() {
        HashMap map = new HashMap();
        map.put("userId", this.devId);
        map.put("day", this.dateTime);
        HttpUtils.getInstance().postMsgAsynHttp(this, Constants.GET_PHYSIOTHERAPY_DAY, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.care.activity.CareFriendMainActivity.22
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                if (result != null) {
                    ArrayList arrayList = new ArrayList();
                    try {
                        Iterator<FriendCarePhyBean.DataDTO> it2 = ((FriendCarePhyBean) CareFriendMainActivity.this.mGson.fromJson(result, FriendCarePhyBean.class)).getData().iterator();
                        while (it2.hasNext()) {
                            arrayList.addAll((Collection) CareFriendMainActivity.this.mGson.fromJson(it2.next().getMlist(), new TypeToken<List<FriendPhyBean>>() { // from class: com.yucheng.smarthealthpro.care.activity.CareFriendMainActivity.22.1
                            }.getType()));
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    long duration = arrayList.size() > 0 ? ((FriendPhyBean) arrayList.get(0)).getDuration() : 0L;
                    if (duration != 0) {
                        CareFriendMainActivity.this.mHomeFunctionBean.add(new HomeFunctionBean("" + duration, "", CareFriendMainActivity.this.getString(R.string.physiotherapy), "理疗", CareFriendMainActivity.this.mAppImageMgr.getBitmap(R.mipmap.home_physiotherapy), true));
                        CareFriendMainActivity careFriendMainActivity = CareFriendMainActivity.this;
                        careFriendMainActivity.notifyRecycle(careFriendMainActivity.mHomeFunctionBean);
                    } else {
                        CareFriendMainActivity.this.mHomeFunctionBean.add(new HomeFunctionBean("--", "", CareFriendMainActivity.this.getString(R.string.physiotherapy), "理疗", CareFriendMainActivity.this.mAppImageMgr.getBitmap(R.mipmap.home_physiotherapy), true));
                        CareFriendMainActivity careFriendMainActivity2 = CareFriendMainActivity.this;
                        careFriendMainActivity2.notifyRecycle(careFriendMainActivity2.mHomeFunctionBean);
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getPressure() {
        HashMap map = new HashMap();
        map.put("userId", this.devId);
        map.put("day", this.dateTime);
        HttpUtils.getInstance().postMsgAsynHttp(this, Constants.pressureDayUrl, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.care.activity.CareFriendMainActivity.23
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                float f2;
                if (result != null) {
                    ArrayList arrayList = new ArrayList();
                    try {
                        Iterator<HistoryPressureResponse.PressureBean> it2 = ((HistoryPressureResponse) CareFriendMainActivity.this.mGson.fromJson(result, HistoryPressureResponse.class)).data.iterator();
                        while (it2.hasNext()) {
                            arrayList.addAll((Collection) CareFriendMainActivity.this.mGson.fromJson(it2.next().mlist, new TypeToken<List<HistoryPressureResponse.Mlist>>() { // from class: com.yucheng.smarthealthpro.care.activity.CareFriendMainActivity.23.1
                            }.getType()));
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    Tools.sortCareListPressure(arrayList);
                    Iterator it3 = arrayList.iterator();
                    while (true) {
                        if (!it3.hasNext()) {
                            f2 = 0.0f;
                            break;
                        }
                        HistoryPressureResponse.Mlist mlist = (HistoryPressureResponse.Mlist) it3.next();
                        if (mlist.pressure >= TransUtils.PRESSURE_VISIBLE_MIN && mlist.pressure <= TransUtils.PRESSURE_VISIBLE_MAX) {
                            f2 = mlist.pressure * 10.0f;
                            break;
                        }
                    }
                    String str = ((int) f2) + "";
                    if (f2 != 0.0f) {
                        CareFriendMainActivity.this.mHomeFunctionBean.add(new HomeFunctionBean(str, "", CareFriendMainActivity.this.getString(R.string.pressure_str), "压力", CareFriendMainActivity.this.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_pressure), true));
                        CareFriendMainActivity careFriendMainActivity = CareFriendMainActivity.this;
                        careFriendMainActivity.notifyRecycle(careFriendMainActivity.mHomeFunctionBean);
                    } else {
                        CareFriendMainActivity.this.mHomeFunctionBean.add(new HomeFunctionBean("--", "", CareFriendMainActivity.this.getString(R.string.pressure_str), "压力", CareFriendMainActivity.this.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_pressure), true));
                        CareFriendMainActivity careFriendMainActivity2 = CareFriendMainActivity.this;
                        careFriendMainActivity2.notifyRecycle(careFriendMainActivity2.mHomeFunctionBean);
                    }
                }
            }
        });
    }

    public void onViewClicked(View view) {
        startActivity(new Intent(this.context, (Class<?>) RunningActivity.class).putExtra(Constant.SpConstKey.DEV_ID, this.devId).putExtra("phone", this.nickName).putExtra("care", "关爱"));
    }
}
