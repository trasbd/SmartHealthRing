package com.yucheng.smarthealthpro.home.activity.bloodpressure.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import com.orhanobut.logger.Logger;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.databinding.ActivityBloodpressuresettingBinding;
import com.yucheng.smarthealthpro.home.view.CustomSelectors;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.DebouncingClick;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class BloodPressureSettingActivity extends BaseVbActivity<ActivityBloodpressuresettingBinding> {
    ImageView ivBpCalibration;
    ImageView ivBpLevel;
    ImageView ivRemind;
    private CustomSelectors mBpCustomSelectors;
    private CustomSelectors mBpLevelCustomSelectors;
    private CustomSelectors mBpRemindCustomSelectors;
    Switch mPoliceSwitch;
    TextView tvBpCalibration;
    TextView tvBpLevel;
    TextView tvRemind;
    private ArrayList<String> firstList = new ArrayList<>();
    private ArrayList<String> secondList = new ArrayList<>();
    private ArrayList<String> bpLevelList = new ArrayList<>();

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        this.tvBpCalibration = ((ActivityBloodpressuresettingBinding) this.mBinding).tvBpCalibration;
        this.ivBpCalibration = ((ActivityBloodpressuresettingBinding) this.mBinding).ivBpCalibration;
        this.ivBpLevel = ((ActivityBloodpressuresettingBinding) this.mBinding).ivBpLevel;
        this.mPoliceSwitch = ((ActivityBloodpressuresettingBinding) this.mBinding).switchPolice;
        this.tvRemind = ((ActivityBloodpressuresettingBinding) this.mBinding).tvRemind;
        this.ivRemind = ((ActivityBloodpressuresettingBinding) this.mBinding).ivRemind;
        this.tvBpLevel = ((ActivityBloodpressuresettingBinding) this.mBinding).tvBpLevel;
        this.ivBpCalibration.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.bloodpressure.activity.BloodPressureSettingActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.ivBpLevel.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.bloodpressure.activity.BloodPressureSettingActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.ivRemind.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.bloodpressure.activity.BloodPressureSettingActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        changeTitle(getString(R.string.include_bottom_tv_first_button));
        showBack();
        this.mPoliceSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.yucheng.smarthealthpro.home.activity.bloodpressure.activity.BloodPressureSettingActivity.1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(BloodPressureSettingActivity.this, Constant.SpConstValue.OPEN, 0).show();
                } else {
                    Toast.makeText(BloodPressureSettingActivity.this, Constant.SpConstValue.CLOSE, 0).show();
                }
            }
        });
    }

    private void initData() {
        for (int i2 = 70; i2 <= 250; i2++) {
            this.firstList.add(i2 + "");
        }
        for (int i3 = 40; i3 <= 160; i3++) {
            this.secondList.add(i3 + "");
        }
        this.bpLevelList.add(getString(R.string.value_low));
        this.bpLevelList.add(getString(R.string.value_normal));
        this.bpLevelList.add(getString(R.string.bp_selectors_list_two));
        this.bpLevelList.add(getString(R.string.bp_selectors_list_four));
        this.bpLevelList.add(getString(R.string.bp_selectors_list_five));
    }

    private void initBpCalibrationlPicker() {
        CustomSelectors customSelectors = new CustomSelectors();
        this.mBpCustomSelectors = customSelectors;
        customSelectors.BpLevelPicker(this.firstList, this.secondList, null, 65, 40, 1, "", "", "", false, CustomSelectors.IsShow.BP, CustomSelectors.SelectorsDataNum.TWO, this.context);
        this.mBpCustomSelectors.setOnTwoSelectorsDataListener(new CustomSelectors.OnTwoSelectorsDataListener() { // from class: com.yucheng.smarthealthpro.home.activity.bloodpressure.activity.BloodPressureSettingActivity.2
            @Override // com.yucheng.smarthealthpro.home.view.CustomSelectors.OnTwoSelectorsDataListener
            public void getSelectorsDataClick(String oneValue, String twoValue, int optionsOne, int optionsTwo) {
                Toast.makeText(BloodPressureSettingActivity.this, "收缩压:" + oneValue + "\n舒张压:" + twoValue, 0).show();
                BloodPressureSettingActivity.this.tvBpCalibration.setText(oneValue + "/" + twoValue);
            }
        });
    }

    private void initBpLevelPicker() {
        CustomSelectors customSelectors = new CustomSelectors();
        this.mBpLevelCustomSelectors = customSelectors;
        customSelectors.BpLevelPicker(this.bpLevelList, null, null, 2, 1, 1, "", "", "", false, CustomSelectors.IsShow.TOP_CONFIRM_CANCEL, CustomSelectors.SelectorsDataNum.ONE, this.context);
        this.mBpLevelCustomSelectors.setOnOneSelectorsDataListener(new CustomSelectors.OnOneSelectorsDataListener() { // from class: com.yucheng.smarthealthpro.home.activity.bloodpressure.activity.BloodPressureSettingActivity.3
            @Override // com.yucheng.smarthealthpro.home.view.CustomSelectors.OnOneSelectorsDataListener
            public void getSelectorsDataClick(String oneValue, int optionsOne) {
                Toast.makeText(BloodPressureSettingActivity.this, oneValue, 0).show();
                BloodPressureSettingActivity.this.tvBpLevel.setText(oneValue);
            }
        });
    }

    private void initBpRemindPicker() {
        CustomSelectors customSelectors = new CustomSelectors();
        this.mBpRemindCustomSelectors = customSelectors;
        customSelectors.BpLevelPicker(this.firstList, null, null, 2, 1, 1, "", "", "", false, CustomSelectors.IsShow.TOP_CONFIRM_CANCEL, CustomSelectors.SelectorsDataNum.ONE, this.context);
        this.mBpRemindCustomSelectors.setOnOneSelectorsDataListener(new CustomSelectors.OnOneSelectorsDataListener() { // from class: com.yucheng.smarthealthpro.home.activity.bloodpressure.activity.BloodPressureSettingActivity.4
            @Override // com.yucheng.smarthealthpro.home.view.CustomSelectors.OnOneSelectorsDataListener
            public void getSelectorsDataClick(String oneValue, int optionsOne) {
                Toast.makeText(BloodPressureSettingActivity.this, oneValue, 0).show();
                BloodPressureSettingActivity.this.tvRemind.setText(oneValue);
            }
        });
    }

    public void onViewClicked(View view) {
        if (view.getId() == R.id.iv_bp_calibration) {
            Logger.d("chong---------firstlist---" + this.firstList.size() + "--" + this.firstList.get(0));
            initBpCalibrationlPicker();
        } else if (view.getId() == R.id.iv_bp_level) {
            initBpLevelPicker();
        } else if (view.getId() == R.id.iv_remind) {
            Logger.d("chong2---------firstlist---" + this.firstList.size() + "--" + this.firstList.get(0));
            initBpRemindPicker();
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
}
