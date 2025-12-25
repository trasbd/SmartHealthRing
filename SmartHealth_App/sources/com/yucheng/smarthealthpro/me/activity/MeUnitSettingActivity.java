package com.yucheng.smarthealthpro.me.activity;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.orhanobut.logger.Logger;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.databinding.ActivityMeUnitsettingBinding;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.ycbtsdk.Constants;
import com.yucheng.ycbtsdk.YCBTClient;
import com.yucheng.ycbtsdk.response.BleDataResponse;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class MeUnitSettingActivity extends BaseVbActivity<ActivityMeUnitsettingBinding> {
    private int bloodSugarUnit;
    private int distanceUnit;
    private int isUnit;
    private int mHeight;
    RadioGroup mRadioGroup;
    RadioGroup mRadioGroupBloodSugar;
    RadioGroup mRadioGroupTemp;
    RadioGroup mRadioGroupUricAcid;
    RadioButton mRbImperial;
    RadioButton mRbImperialTemp;
    RadioButton mRbMetric;
    RadioButton mRbMetricTemp;
    TextView mTvBloodTitle;
    private int mWeight;
    private int temperatureUnit;
    private int timeFormat;
    private int uricAcidUnit;
    private int weightUnit;

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        this.mRadioGroup = ((ActivityMeUnitsettingBinding) this.mBinding).radioGroup;
        this.mRadioGroupTemp = ((ActivityMeUnitsettingBinding) this.mBinding).radioGroupTemp;
        this.mRadioGroupBloodSugar = ((ActivityMeUnitsettingBinding) this.mBinding).radioGroupBloodSugar;
        this.mRadioGroupUricAcid = ((ActivityMeUnitsettingBinding) this.mBinding).radioGroupUricAcid;
        this.mRbMetric = ((ActivityMeUnitsettingBinding) this.mBinding).rbMetric;
        this.mRbImperial = ((ActivityMeUnitsettingBinding) this.mBinding).rbImperial;
        this.mRbMetricTemp = ((ActivityMeUnitsettingBinding) this.mBinding).rbMetricTemp;
        this.mRbImperialTemp = ((ActivityMeUnitsettingBinding) this.mBinding).rbImperialTemp;
        this.mTvBloodTitle = ((ActivityMeUnitsettingBinding) this.mBinding).tvBloodTitle;
        changeTitle(getString(R.string.me_my_device_more_settings_units_setup_title));
        showBack();
        showRightText(getString(R.string.save), new NavigationBar.MyOnClickListener() { // from class: com.yucheng.smarthealthpro.me.activity.MeUnitSettingActivity.1
            @Override // com.yucheng.smarthealthpro.framework.view.NavigationBar.MyOnClickListener
            public void onClick(View btn) {
                if (YCBTClient.connectState() == 10) {
                    MeUnitSettingActivity.this.saveUnitSetting();
                } else {
                    Toast.makeText(MeUnitSettingActivity.this.context, MeUnitSettingActivity.this.getString(R.string.please_connect_the_device), 0).show();
                }
            }
        });
        this.mHeight = ((Integer) SharedPreferencesUtils.get(this.context, "height", 1)).intValue();
        this.mWeight = ((Integer) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.WEIGHT, 1)).intValue();
        String str = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.UNIT, "");
        if ((str == null || !str.equals(Constant.SpConstValue.ISO)) && str != null && str.equals(Constant.SpConstValue.INCH)) {
            this.mRadioGroup.check(R.id.rb_imperial);
            this.distanceUnit = 1;
            this.weightUnit = 1;
            this.isUnit = 1;
        } else {
            this.mRadioGroup.check(R.id.rb_metric);
            this.distanceUnit = 0;
            this.weightUnit = 0;
            this.isUnit = 0;
        }
        String str2 = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.TEMP_UNIT, "");
        if ((str2 == null || !str2.equals(Constant.SpConstValue.TEMP_ISO)) && str2 != null && str2.equals(Constant.SpConstValue.TEMP_INCH)) {
            this.mRadioGroupTemp.check(R.id.rb_imperial_temp);
            this.temperatureUnit = 1;
        } else {
            this.mRadioGroupTemp.check(R.id.rb_metric_temp);
            this.temperatureUnit = 0;
        }
        String str3 = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.BLOOD_SUGAR_AND_BLOOD_FAT_UNIT, "");
        Logger.d("chong----------" + str3 + "--mmol/L--mg/dL");
        if ((str3 == null || !str3.equals(Constant.SpConstValue.BLOOD_SUGAR_AND_BLOOD_FAT_MMOL)) && str3 != null && str3.equals("mg/dL")) {
            this.mRadioGroupBloodSugar.check(R.id.rb_blood_sugar_mg);
            this.bloodSugarUnit = 1;
        } else {
            this.mRadioGroupBloodSugar.check(R.id.rb_blood_sugar_mmol);
            this.bloodSugarUnit = 0;
        }
        String str4 = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.URIC_ACID_UNIT, Constant.SpConstValue.URIC_ACID_UMOL);
        if (str4 != null && str4.equals(Constant.SpConstValue.URIC_ACID_UMOL)) {
            this.mRadioGroupUricAcid.check(R.id.rb_blood_uric_acid1);
            this.uricAcidUnit = 0;
        } else if (str4 != null && str4.equals("mg/dL")) {
            this.mRadioGroupUricAcid.check(R.id.rb_blood_uric_acid2);
            this.uricAcidUnit = 1;
        } else {
            this.mRadioGroupUricAcid.check(R.id.rb_blood_sugar_mmol);
            this.uricAcidUnit = 0;
        }
    }

    private void initData() {
        boolean zIsSupportFunction = YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASBLOODSUGAR);
        boolean zIsSupportFunction2 = YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASIMPRECISEBLOODFAT);
        boolean z = YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASURICACIDMEASUREMENT) || YCBTClient.isSupportFunction(Constants.FunctionConstant.IS_HAS_PRECISION_URIC_ACID) || YCBTClient.isSupportFunction(Constants.FunctionConstant.IS_HAS_PRECISION_BLOOD_KETONE) || YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASBLOODKETONEMEASUREMENT);
        if (zIsSupportFunction || zIsSupportFunction2) {
            findViewById(R.id.ly_blood_sugar).setVisibility(0);
        }
        if (zIsSupportFunction && zIsSupportFunction2) {
            this.mTvBloodTitle.setText(getString(R.string.home_blood_sugar_title) + "、" + getString(R.string.blood_fat));
        } else if (zIsSupportFunction) {
            this.mTvBloodTitle.setText(getString(R.string.home_blood_sugar_title));
        } else if (zIsSupportFunction2) {
            this.mTvBloodTitle.setText(getString(R.string.blood_fat));
        }
        if (z) {
            findViewById(R.id.ly_uric_acid).setVisibility(0);
        }
        this.mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.yucheng.smarthealthpro.me.activity.MeUnitSettingActivity.2
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_metric) {
                    MeUnitSettingActivity.this.distanceUnit = 0;
                    MeUnitSettingActivity.this.weightUnit = 0;
                } else if (checkedId == R.id.rb_imperial) {
                    MeUnitSettingActivity.this.distanceUnit = 1;
                    MeUnitSettingActivity.this.weightUnit = 1;
                }
            }
        });
        this.mRadioGroupTemp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.yucheng.smarthealthpro.me.activity.MeUnitSettingActivity.3
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_metric_temp) {
                    MeUnitSettingActivity.this.temperatureUnit = 0;
                } else if (checkedId == R.id.rb_imperial_temp) {
                    MeUnitSettingActivity.this.temperatureUnit = 1;
                }
            }
        });
        this.mRadioGroupBloodSugar.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.yucheng.smarthealthpro.me.activity.MeUnitSettingActivity.4
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_blood_sugar_mmol) {
                    MeUnitSettingActivity.this.bloodSugarUnit = 0;
                } else if (checkedId == R.id.rb_blood_sugar_mg) {
                    MeUnitSettingActivity.this.bloodSugarUnit = 1;
                }
            }
        });
        this.mRadioGroupUricAcid.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.yucheng.smarthealthpro.me.activity.MeUnitSettingActivity.5
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_blood_uric_acid1) {
                    MeUnitSettingActivity.this.uricAcidUnit = 0;
                } else if (checkedId == R.id.rb_blood_uric_acid2) {
                    MeUnitSettingActivity.this.uricAcidUnit = 1;
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveUnitSetting() {
        YCBTClient.settingUnit(this.distanceUnit, this.weightUnit, this.temperatureUnit, (byte) (!DateFormat.is24HourFormat(this) ? 1 : 0), this.bloodSugarUnit, this.uricAcidUnit, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.activity.MeUnitSettingActivity.6
            @Override // com.yucheng.ycbtsdk.response.BleDataResponse
            public void onDataResponse(int code, float ratio, HashMap resultMap) {
                if (code == 0) {
                    MeUnitSettingActivity.this.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.me.activity.MeUnitSettingActivity.6.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (MeUnitSettingActivity.this.distanceUnit == 0) {
                                SharedPreferencesUtils.put(MeUnitSettingActivity.this.context, Constant.SpConstKey.UNIT, Constant.SpConstValue.ISO);
                                if (MeUnitSettingActivity.this.isUnit == 1) {
                                    SharedPreferencesUtils.put(MeUnitSettingActivity.this.context, "height", Integer.valueOf((int) ((MeUnitSettingActivity.this.mHeight * 2.54f) + 0.5d)));
                                    SharedPreferencesUtils.put(MeUnitSettingActivity.this.context, Constant.SpConstKey.WEIGHT, Integer.valueOf((int) ((MeUnitSettingActivity.this.mWeight * 0.4536f) + 0.5d)));
                                }
                            } else {
                                SharedPreferencesUtils.put(MeUnitSettingActivity.this.context, Constant.SpConstKey.UNIT, Constant.SpConstValue.INCH);
                                if (MeUnitSettingActivity.this.isUnit == 0) {
                                    SharedPreferencesUtils.put(MeUnitSettingActivity.this.context, "height", Integer.valueOf((int) ((MeUnitSettingActivity.this.mHeight * 0.3937f) + 0.5d)));
                                    SharedPreferencesUtils.put(MeUnitSettingActivity.this.context, Constant.SpConstKey.WEIGHT, Integer.valueOf((int) ((MeUnitSettingActivity.this.mWeight * 2.2046f) + 0.5d)));
                                }
                            }
                            if (MeUnitSettingActivity.this.temperatureUnit == 0) {
                                SharedPreferencesUtils.put(MeUnitSettingActivity.this.context, Constant.SpConstKey.TEMP_UNIT, Constant.SpConstValue.TEMP_ISO);
                            } else {
                                SharedPreferencesUtils.put(MeUnitSettingActivity.this.context, Constant.SpConstKey.TEMP_UNIT, Constant.SpConstValue.TEMP_INCH);
                            }
                            if (MeUnitSettingActivity.this.bloodSugarUnit == 0) {
                                SharedPreferencesUtils.put(MeUnitSettingActivity.this.context, Constant.SpConstKey.BLOOD_SUGAR_AND_BLOOD_FAT_UNIT, Constant.SpConstValue.BLOOD_SUGAR_AND_BLOOD_FAT_MMOL);
                            } else {
                                SharedPreferencesUtils.put(MeUnitSettingActivity.this.context, Constant.SpConstKey.BLOOD_SUGAR_AND_BLOOD_FAT_UNIT, "mg/dL");
                            }
                            if (MeUnitSettingActivity.this.uricAcidUnit == 0) {
                                SharedPreferencesUtils.put(MeUnitSettingActivity.this.context, Constant.SpConstKey.URIC_ACID_UNIT, Constant.SpConstValue.URIC_ACID_UMOL);
                            } else {
                                SharedPreferencesUtils.put(MeUnitSettingActivity.this.context, Constant.SpConstKey.URIC_ACID_UNIT, "mg/dL");
                            }
                            Toast.makeText(MeUnitSettingActivity.this, MeUnitSettingActivity.this.getString(R.string.save_successfully), 0).show();
                            MeUnitSettingActivity.this.finish();
                        }
                    });
                }
            }
        });
    }
}
