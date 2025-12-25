package com.yucheng.smarthealthpro.me.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.widget.CompoundButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.yanzhenjie.recyclerview.widget.DefaultItemDecoration;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.care.bean.HealthFunctionResponse;
import com.yucheng.smarthealthpro.databinding.ActivityMeHealthFunctionBinding;
import com.yucheng.smarthealthpro.framework.http.HttpUtils;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.me.adapter.HealthAssistanceFunctionAdapter;
import com.yucheng.smarthealthpro.me.bean.HealthFunctionType;
import com.yucheng.smarthealthpro.me.bean.HealthFunctionUnitBean;
import com.yucheng.smarthealthpro.me.setting.contacts.utils.DpUtil;
import com.yucheng.smarthealthpro.sport.view.CommonDialog;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.EventBusMessageEvent;
import com.yucheng.smarthealthpro.utils.MLog;
import com.yucheng.ycbtsdk.Constants;
import com.yucheng.ycbtsdk.YCBTClient;
import com.yucheng.ycbtsdk.bean.HealthAssistanceFunction;
import com.yucheng.ycbtsdk.response.BleDataResponse;
import com.yucheng.ycbtsdk.utils.SPUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes5.dex */
public class HealthFunctionActivity extends BaseVbActivity<ActivityMeHealthFunctionBinding> {
    HealthAssistanceFunction mHealthAssistanceFunction;
    private RecyclerView mRecycleView;
    boolean isCancel = false;
    private Handler handler = new Handler(Looper.getMainLooper());
    private List<HealthFunctionUnitBean> mData = new ArrayList();
    private HealthAssistanceFunctionAdapter mAdapter = new HealthAssistanceFunctionAdapter();
    HealthAssistanceFunctionAdapter.OnItemCheckedChangeListener onCheckedChangeListener = new HealthAssistanceFunctionAdapter.OnItemCheckedChangeListener() { // from class: com.yucheng.smarthealthpro.me.activity.HealthFunctionActivity.1
        @Override // com.yucheng.smarthealthpro.me.adapter.HealthAssistanceFunctionAdapter.OnItemCheckedChangeListener
        public void OnCheckedChangeListener(HealthFunctionUnitBean functionUnitBean, CompoundButton buttonView, boolean isChecked) {
            if (HealthFunctionActivity.this.isCancel) {
                HealthFunctionActivity.this.isCancel = false;
                return;
            }
            if (isChecked == functionUnitBean.getEnable()) {
                return;
            }
            if (isChecked) {
                HealthFunctionActivity.this.showHealthDialog(functionUnitBean, buttonView);
                return;
            }
            HealthFunctionActivity.this.updateFunctionValue(functionUnitBean.getType(), false);
            functionUnitBean.setEnable(false);
            HealthFunctionActivity healthFunctionActivity = HealthFunctionActivity.this;
            healthFunctionActivity.showProgressDialog(healthFunctionActivity.getString(R.string.ecg_sync_data));
            HealthFunctionActivity.this.handler.postDelayed(HealthFunctionActivity.this.dismissRun, 2000L);
            YCBTClient.setSingleMeasurementFunction(HealthFunctionActivity.this.mHealthAssistanceFunction.reunited(), HealthFunctionActivity.this.dataResponse);
        }
    };
    Runnable dismissRun = new Runnable() { // from class: com.yucheng.smarthealthpro.me.activity.HealthFunctionActivity.4
        @Override // java.lang.Runnable
        public void run() {
            HealthFunctionActivity.this.dismissProgressDialog();
        }
    };
    BleDataResponse dataResponse = new AnonymousClass5();

    private void addRingData() {
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        showBack();
        changeTitle(getString(R.string.health_functions));
        HealthAssistanceFunction healthAssistanceFunction = (HealthAssistanceFunction) new Gson().fromJson(SPUtil.get(Constants.SharedKey.Function_Str, "").toString(), HealthAssistanceFunction.class);
        this.mHealthAssistanceFunction = healthAssistanceFunction;
        if (healthAssistanceFunction == null) {
            this.mHealthAssistanceFunction = new HealthAssistanceFunction();
        }
        initView();
        initRingData();
    }

    protected void initView() {
        if (!Constant.isRing()) {
            addWatchData();
        }
        RecyclerView recyclerView = ((ActivityMeHealthFunctionBinding) this.mBinding).recyclerView;
        this.mRecycleView = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.mAdapter.setList(this.mData);
        this.mAdapter.setOnItemCheckedChangeListener(this.onCheckedChangeListener);
        this.mRecycleView.setAdapter(this.mAdapter);
        this.mRecycleView.addItemDecoration(new DefaultItemDecoration(getColor(R.color.transparent), 2, (int) DpUtil.dp2px(this, 9.0f)));
    }

    protected void initRingData() {
        if (Constant.isRing()) {
            if (Constant.isRing()) {
                addRingData();
            }
            if (System.currentTimeMillis() - ((Long) SharedPreferencesUtils.get(getApplicationContext(), Constant.SpConstKey.PropsTimeStamp, 0L)).longValue() > 43200000) {
                MLog.INSTANCE.d("loadServerConfig");
                loadServerConfig();
                return;
            }
            MLog.INSTANCE.d("loadConfig");
            updateFunction(new HealthFunctionResponse.DataBean(Boolean.valueOf(((Boolean) SharedPreferencesUtils.get(getApplicationContext(), Constant.SpConstKey.PropsUricAcid, false)).booleanValue()), Boolean.valueOf(((Boolean) SharedPreferencesUtils.get(getApplicationContext(), Constant.SpConstKey.PropsBloodSugar, false)).booleanValue()), Boolean.valueOf(((Boolean) SharedPreferencesUtils.get(getApplicationContext(), Constant.SpConstKey.PropsBloodFat, false)).booleanValue())));
            this.mAdapter.setList(this.mData);
        }
    }

    /* renamed from: com.yucheng.smarthealthpro.me.activity.HealthFunctionActivity$6, reason: invalid class name */
    static /* synthetic */ class AnonymousClass6 {
        static final /* synthetic */ int[] $SwitchMap$com$yucheng$smarthealthpro$me$bean$HealthFunctionType;

        static {
            int[] iArr = new int[HealthFunctionType.values().length];
            $SwitchMap$com$yucheng$smarthealthpro$me$bean$HealthFunctionType = iArr;
            try {
                iArr[HealthFunctionType.BLOODSUGAR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$yucheng$smarthealthpro$me$bean$HealthFunctionType[HealthFunctionType.URICACID.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$yucheng$smarthealthpro$me$bean$HealthFunctionType[HealthFunctionType.BLOODFAT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$yucheng$smarthealthpro$me$bean$HealthFunctionType[HealthFunctionType.HEARTRATE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$yucheng$smarthealthpro$me$bean$HealthFunctionType[HealthFunctionType.BLOODOXYGEN.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$yucheng$smarthealthpro$me$bean$HealthFunctionType[HealthFunctionType.TEMPERATURE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$yucheng$smarthealthpro$me$bean$HealthFunctionType[HealthFunctionType.BPNORMAL.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$yucheng$smarthealthpro$me$bean$HealthFunctionType[HealthFunctionType.BPACCURATE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$yucheng$smarthealthpro$me$bean$HealthFunctionType[HealthFunctionType.ECG.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$yucheng$smarthealthpro$me$bean$HealthFunctionType[HealthFunctionType.HRV.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateFunctionValue(HealthFunctionType type, boolean isEnable) {
        MLog.INSTANCE.d("type: " + type + "  enable: " + isEnable);
        switch (AnonymousClass6.$SwitchMap$com$yucheng$smarthealthpro$me$bean$HealthFunctionType[type.ordinal()]) {
            case 1:
                this.mHealthAssistanceFunction.setBloodSugar(isEnable);
                break;
            case 2:
                this.mHealthAssistanceFunction.setUricAcid(isEnable);
                break;
            case 3:
                this.mHealthAssistanceFunction.setBloodFat(isEnable);
                break;
            case 4:
                this.mHealthAssistanceFunction.setHeartRate(isEnable);
                break;
            case 5:
                this.mHealthAssistanceFunction.setBloodOxygen(isEnable);
                break;
            case 6:
                this.mHealthAssistanceFunction.setTemperature(isEnable);
                break;
            case 7:
                this.mHealthAssistanceFunction.setBpNormal(isEnable);
                break;
            case 8:
                this.mHealthAssistanceFunction.setBpAccurate(isEnable);
                break;
            case 9:
                this.mHealthAssistanceFunction.setEcg(isEnable);
                break;
            case 10:
                this.mHealthAssistanceFunction.setHrv(isEnable);
                break;
        }
        MLog.INSTANCE.d("mHealthAssistanceFunction: " + this.mHealthAssistanceFunction);
    }

    private void addWatchData() {
        this.mData.clear();
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.IS_HAS_MeasurementFunction)) {
            this.mData.add(new HealthFunctionUnitBean(getString(R.string.uric_acid_risk_assessment), this.mHealthAssistanceFunction.isUricAcid(), HealthFunctionType.URICACID));
            this.mData.add(new HealthFunctionUnitBean(getString(R.string.lipid_risk_assessment), this.mHealthAssistanceFunction.isBloodFat(), HealthFunctionType.BLOODFAT));
            this.mData.add(new HealthFunctionUnitBean(getString(R.string.blood_glucose_risk_assessment), this.mHealthAssistanceFunction.isBloodSugar(), HealthFunctionType.BLOODSUGAR));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.IS_HAS_MF_HEART_RATE)) {
            this.mData.add(new HealthFunctionUnitBean(getString(R.string.heart_rate_risk_assessment), this.mHealthAssistanceFunction.isHeartRate(), HealthFunctionType.HEARTRATE));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.IS_HAS_MF_BLOOD_OXYGEN)) {
            this.mData.add(new HealthFunctionUnitBean(getString(R.string.blood_oxygen_risk_assessment), this.mHealthAssistanceFunction.isBloodOxygen(), HealthFunctionType.BLOODOXYGEN));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.IS_HAS_MF_TEMPERATURE)) {
            this.mData.add(new HealthFunctionUnitBean(getString(R.string.temperature_risk_assessment), this.mHealthAssistanceFunction.isTemperature(), HealthFunctionType.TEMPERATURE));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.IS_HAS_MF_BLOOD_PRESSURE)) {
            this.mData.add(new HealthFunctionUnitBean(getString(R.string.bp_normal_risk_assessment), this.mHealthAssistanceFunction.isBpNormal(), HealthFunctionType.BPNORMAL));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.IS_HAS_MF_BLOOD_PRESSURE_ACCURATE)) {
            this.mData.add(new HealthFunctionUnitBean(getString(R.string.bp_accurate_risk_assessment), this.mHealthAssistanceFunction.isBpAccurate(), HealthFunctionType.BPACCURATE));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.IS_HAS_MF_ECG)) {
            this.mData.add(new HealthFunctionUnitBean(getString(R.string.ecg_risk_assessment), this.mHealthAssistanceFunction.isEcg(), HealthFunctionType.ECG));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.IS_HAS_MF_HRV)) {
            this.mData.add(new HealthFunctionUnitBean(getString(R.string.hrv_risk_assessment), this.mHealthAssistanceFunction.isHrv(), HealthFunctionType.HRV));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateFunction(HealthFunctionResponse.DataBean dataBean) {
        this.mData.clear();
        if (dataBean.isSupportUricAcid.booleanValue()) {
            this.mData.add(new HealthFunctionUnitBean(getString(R.string.uric_acid_risk_assessment), this.mHealthAssistanceFunction.isUricAcid(), HealthFunctionType.URICACID));
        }
        if (dataBean.isSupportBloodFat.booleanValue()) {
            this.mData.add(new HealthFunctionUnitBean(getString(R.string.lipid_risk_assessment), this.mHealthAssistanceFunction.isBloodFat(), HealthFunctionType.BLOODFAT));
        }
        if (dataBean.isSupportBloodSugar.booleanValue()) {
            this.mData.add(new HealthFunctionUnitBean(getString(R.string.blood_glucose_risk_assessment), this.mHealthAssistanceFunction.isBloodSugar(), HealthFunctionType.BLOODSUGAR));
        }
    }

    private void loadServerConfig() {
        HttpUtils.getInstance().getMsgAsynHttp(this, com.yucheng.smarthealthpro.framework.util.Constants.Props, null, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.me.activity.HealthFunctionActivity.2
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                try {
                    if (!TextUtils.isEmpty(result)) {
                        SharedPreferencesUtils.put(HealthFunctionActivity.this.getApplicationContext(), Constant.SpConstKey.Props, result);
                        SharedPreferencesUtils.put(HealthFunctionActivity.this.getApplicationContext(), Constant.SpConstKey.PropsTimeStamp, Long.valueOf(System.currentTimeMillis()));
                    }
                    Log.d("HttpUtils", "upload=" + result);
                    HealthFunctionResponse healthFunctionResponse = (HealthFunctionResponse) new Gson().fromJson(result, HealthFunctionResponse.class);
                    HealthFunctionActivity.this.updateFunction(healthFunctionResponse.data);
                    HealthFunctionActivity.this.mAdapter.setList(HealthFunctionActivity.this.mData);
                    SharedPreferencesUtils.put(HealthFunctionActivity.this.getApplicationContext(), Constant.SpConstKey.PropsUricAcid, healthFunctionResponse.data.isSupportUricAcid);
                    SharedPreferencesUtils.put(HealthFunctionActivity.this.getApplicationContext(), Constant.SpConstKey.PropsBloodFat, healthFunctionResponse.data.isSupportBloodFat);
                    SharedPreferencesUtils.put(HealthFunctionActivity.this.getApplicationContext(), Constant.SpConstKey.PropsBloodSugar, healthFunctionResponse.data.isSupportBloodSugar);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
    }

    public void showHealthDialog(final HealthFunctionUnitBean functionUnitBean, final CompoundButton buttonView) {
        final CommonDialog commonDialog = new CommonDialog(this);
        commonDialog.setMessage(getString(R.string.health_functions_tip)).setTitle(getString(R.string.prompt)).setConfirm(getString(R.string.ok)).setCancel(getString(R.string.cancel)).setOutSideTouch(false).setSingle(false).setOnClickBottomListener(new CommonDialog.OnClickBottomListener() { // from class: com.yucheng.smarthealthpro.me.activity.HealthFunctionActivity.3
            @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
            public void onConfirmClick() {
                HealthFunctionActivity.this.updateFunctionValue(functionUnitBean.getType(), true);
                functionUnitBean.setEnable(true);
                commonDialog.dismiss();
                HealthFunctionActivity healthFunctionActivity = HealthFunctionActivity.this;
                healthFunctionActivity.showProgressDialog(healthFunctionActivity.getString(R.string.ecg_sync_data));
                HealthFunctionActivity.this.handler.postDelayed(HealthFunctionActivity.this.dismissRun, 2000L);
                YCBTClient.setSingleMeasurementFunction(HealthFunctionActivity.this.mHealthAssistanceFunction.reunited(), HealthFunctionActivity.this.dataResponse);
            }

            @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
            public void onCancelClick() {
                commonDialog.dismiss();
                HealthFunctionActivity.this.isCancel = true;
                buttonView.setChecked(false);
            }

            @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
            public void onEditTextConfirmClick(String mEditText) {
                commonDialog.dismiss();
            }
        }).show();
    }

    /* renamed from: com.yucheng.smarthealthpro.me.activity.HealthFunctionActivity$5, reason: invalid class name */
    class AnonymousClass5 implements BleDataResponse {
        AnonymousClass5() {
        }

        /* renamed from: com.yucheng.smarthealthpro.me.activity.HealthFunctionActivity$5$1, reason: invalid class name */
        class AnonymousClass1 implements Runnable {
            final /* synthetic */ int val$code;

            AnonymousClass1(final int val$code) {
                this.val$code = val$code;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (this.val$code == 0) {
                    SharedPreferencesUtils.put(HealthFunctionActivity.this.context, Constant.SpConstKey.IS_CONNECT, "");
                    YCBTClient.getDeviceSupportFunction(new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.activity.HealthFunctionActivity.5.1.1
                        @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                        public void onDataResponse(int i2, float v, HashMap hashMap) {
                            HealthFunctionActivity.this.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.me.activity.HealthFunctionActivity.5.1.1.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    HealthFunctionActivity.this.showToast(HealthFunctionActivity.this.getString(R.string.setup_successful));
                                    EventBusMessageEvent eventBusMessageEvent = new EventBusMessageEvent();
                                    eventBusMessageEvent.belState = 1;
                                    EventBus.getDefault().post(eventBusMessageEvent);
                                }
                            });
                        }
                    });
                } else {
                    HealthFunctionActivity.this.showToast(HealthFunctionActivity.this.getString(R.string.health_set_failed));
                }
            }
        }

        @Override // com.yucheng.ycbtsdk.response.BleDataResponse
        public void onDataResponse(int code, float ratio, HashMap resultMap) {
            HealthFunctionActivity.this.runOnUiThread(new AnonymousClass1(code));
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getCompile(EventBusMessageEvent messageEvent) {
        if (messageEvent.belState != 0) {
            return;
        }
        finish();
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        this.handler.removeCallbacks(this.dismissRun);
    }
}
