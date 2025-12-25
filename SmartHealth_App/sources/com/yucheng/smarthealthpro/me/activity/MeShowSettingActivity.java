package com.yucheng.smarthealthpro.me.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.databinding.ActivityMeShowsettingBinding;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.home.view.CustomSelectors;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.DebouncingClick;
import com.yucheng.ycbtsdk.Constants;
import com.yucheng.ycbtsdk.YCBTClient;
import com.yucheng.ycbtsdk.response.BleDataResponse;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class MeShowSettingActivity extends BaseVbActivity<ActivityMeShowsettingBinding> {
    RelativeLayout ll_luminance;
    private CustomSelectors mLuminanceCustomSelectors;
    private int mLuminanceOptionsOne;
    Switch mSwitchRaiseToWake;
    RelativeLayout rlRaiseToWake;
    TextView tvLuminance;
    private ArrayList<String> firstLuminanceList = new ArrayList<>();
    private ArrayList<Brightness> brightnessList = new ArrayList<>();

    static class Brightness {
        private int cmd;
        private Level level;
        private String name;

        enum Level {
            LOW,
            LOW_MIDDLE,
            MIDDLE,
            MIDDLE_HIGH,
            HIGH
        }

        public Brightness(String name, Level level, int cmd) {
            this.name = name;
            this.level = level;
            this.cmd = cmd;
        }

        public String getName() {
            return this.name;
        }

        public int getCmd() {
            return this.cmd;
        }

        public Level getLevel() {
            return this.level;
        }
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        this.mSwitchRaiseToWake = ((ActivityMeShowsettingBinding) this.mBinding).switchRaiseToWake;
        this.tvLuminance = ((ActivityMeShowsettingBinding) this.mBinding).tvLuminance;
        this.rlRaiseToWake = ((ActivityMeShowsettingBinding) this.mBinding).rlRaiseToWake;
        RelativeLayout relativeLayout = ((ActivityMeShowsettingBinding) this.mBinding).llLuminance;
        this.ll_luminance = relativeLayout;
        relativeLayout.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.me.activity.MeShowSettingActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        changeTitle(getString(R.string.me_my_device_more_settings_display_setup_title));
        showBack();
        String string = getString(R.string.me_my_device_more_settings_display_setup_selectors_low);
        String string2 = getString(R.string.value_low);
        String string3 = getString(R.string.me_my_device_more_settings_display_setup_selectors_center);
        String string4 = getString(R.string.value_high);
        String string5 = getString(R.string.me_my_device_more_settings_display_setup_selectors_high);
        this.firstLuminanceList.add(getString(R.string.me_my_device_more_settings_display_setup_selectors_low));
        this.brightnessList.add(new Brightness(string, Brightness.Level.LOW, 0));
        this.firstLuminanceList.add(getString(R.string.me_my_device_more_settings_display_setup_selectors_center));
        this.brightnessList.add(new Brightness(string3, Brightness.Level.MIDDLE, 1));
        this.firstLuminanceList.add(getString(R.string.me_my_device_more_settings_display_setup_selectors_high));
        this.brightnessList.add(new Brightness(string5, Brightness.Level.HIGH, 2));
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.WATCHSCREENBRIGHTNESS)) {
            this.firstLuminanceList.add(1, string2);
            this.brightnessList.add(1, new Brightness(string5, Brightness.Level.LOW_MIDDLE, 4));
            this.firstLuminanceList.add(3, string4);
            this.brightnessList.add(3, new Brightness(string5, Brightness.Level.MIDDLE_HIGH, 5));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASWATCHSCREENBRIGHTNESS)) {
            this.ll_luminance.setVisibility(0);
        } else {
            this.ll_luminance.setVisibility(8);
        }
        int iIntValue = ((Integer) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.LUMINANCE_OPTIONS_ONE, 1)).intValue();
        this.mLuminanceOptionsOne = iIntValue;
        if (iIntValue >= this.firstLuminanceList.size()) {
            this.mLuminanceOptionsOne = 1;
        }
        this.tvLuminance.setText(this.firstLuminanceList.get(this.mLuminanceOptionsOne));
        if (!YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASLIFTBRIGHT)) {
            this.rlRaiseToWake.setVisibility(8);
        } else {
            this.rlRaiseToWake.setVisibility(0);
        }
        String str = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.IS_RAISE_SCREEN, Constant.SpConstValue.OPEN);
        if (str != null && !str.isEmpty()) {
            if (str.equals(Constant.SpConstValue.OPEN)) {
                this.mSwitchRaiseToWake.setChecked(true);
                return;
            } else {
                this.mSwitchRaiseToWake.setChecked(false);
                return;
            }
        }
        this.mSwitchRaiseToWake.setChecked(false);
    }

    /* renamed from: com.yucheng.smarthealthpro.me.activity.MeShowSettingActivity$1, reason: invalid class name */
    class AnonymousClass1 implements CompoundButton.OnCheckedChangeListener {
        AnonymousClass1() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                YCBTClient.settingRaiseScreen(1, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.activity.MeShowSettingActivity.1.1
                    @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                    public void onDataResponse(final int code, float ratio, HashMap resultMap) {
                        MeShowSettingActivity.this.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.me.activity.MeShowSettingActivity.1.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                if (code == 0) {
                                    SharedPreferencesUtils.put(MeShowSettingActivity.this.context, Constant.SpConstKey.IS_RAISE_SCREEN, Constant.SpConstValue.OPEN);
                                    Toast.makeText(MeShowSettingActivity.this.context, R.string.setup_successful, 0).show();
                                } else {
                                    Toast.makeText(MeShowSettingActivity.this.context, R.string.health_set_failed, 0).show();
                                }
                            }
                        });
                    }
                });
            } else {
                YCBTClient.settingRaiseScreen(0, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.activity.MeShowSettingActivity.1.2
                    @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                    public void onDataResponse(final int code, float ratio, HashMap resultMap) {
                        MeShowSettingActivity.this.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.me.activity.MeShowSettingActivity.1.2.1
                            @Override // java.lang.Runnable
                            public void run() {
                                if (code == 0) {
                                    SharedPreferencesUtils.put(MeShowSettingActivity.this.context, Constant.SpConstKey.IS_RAISE_SCREEN, Constant.SpConstValue.CLOSE);
                                    Toast.makeText(MeShowSettingActivity.this.context, R.string.setup_successful, 0).show();
                                } else {
                                    Toast.makeText(MeShowSettingActivity.this.context, R.string.health_set_failed, 0).show();
                                }
                            }
                        });
                    }
                });
            }
        }
    }

    private void initData() {
        this.mSwitchRaiseToWake.setOnCheckedChangeListener(new AnonymousClass1());
    }

    private void initLuminancePicker() {
        CustomSelectors customSelectors = new CustomSelectors();
        this.mLuminanceCustomSelectors = customSelectors;
        customSelectors.BpLevelPicker(this.firstLuminanceList, null, null, this.mLuminanceOptionsOne, 1, 1, "", "", "", false, CustomSelectors.IsShow.BOTTOM_CONFIRM, CustomSelectors.SelectorsDataNum.ONE, this.context);
        this.mLuminanceCustomSelectors.setOnOneSelectorsDataListener(new CustomSelectors.OnOneSelectorsDataListener() { // from class: com.yucheng.smarthealthpro.me.activity.MeShowSettingActivity.2
            @Override // com.yucheng.smarthealthpro.home.view.CustomSelectors.OnOneSelectorsDataListener
            public void getSelectorsDataClick(String oneValue, int optionsOne) {
                MeShowSettingActivity.this.settingLuminance(((Brightness) MeShowSettingActivity.this.brightnessList.get(optionsOne)).cmd, oneValue, optionsOne);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void settingLuminance(final int level, final String oneValue, final int optionsOne) {
        YCBTClient.settingDisplayBrightness(level, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.activity.MeShowSettingActivity.3
            @Override // com.yucheng.ycbtsdk.response.BleDataResponse
            public void onDataResponse(int code, float ratio, HashMap resultMap) {
                if (code == 0) {
                    MeShowSettingActivity.this.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.me.activity.MeShowSettingActivity.3.1
                        @Override // java.lang.Runnable
                        public void run() {
                            Toast.makeText(MeShowSettingActivity.this.context, R.string.setup_successful, 0).show();
                            int i2 = level;
                            if (i2 == 0) {
                                MeShowSettingActivity.this.mLuminanceOptionsOne = 0;
                                MeShowSettingActivity.this.tvLuminance.setText(oneValue);
                                SharedPreferencesUtils.put(MeShowSettingActivity.this.context, Constant.SpConstKey.LUMINANCE_OPTIONS_ONE, Integer.valueOf(optionsOne));
                                return;
                            }
                            if (i2 == 1) {
                                if (YCBTClient.isSupportFunction(Constants.FunctionConstant.WATCHSCREENBRIGHTNESS)) {
                                    MeShowSettingActivity.this.mLuminanceOptionsOne = 2;
                                } else {
                                    MeShowSettingActivity.this.mLuminanceOptionsOne = 1;
                                }
                                MeShowSettingActivity.this.tvLuminance.setText(oneValue);
                                SharedPreferencesUtils.put(MeShowSettingActivity.this.context, Constant.SpConstKey.LUMINANCE_OPTIONS_ONE, Integer.valueOf(optionsOne));
                                return;
                            }
                            if (i2 == 2) {
                                if (YCBTClient.isSupportFunction(Constants.FunctionConstant.WATCHSCREENBRIGHTNESS)) {
                                    MeShowSettingActivity.this.mLuminanceOptionsOne = 4;
                                } else {
                                    MeShowSettingActivity.this.mLuminanceOptionsOne = 2;
                                }
                                MeShowSettingActivity.this.tvLuminance.setText(oneValue);
                                SharedPreferencesUtils.put(MeShowSettingActivity.this.context, Constant.SpConstKey.LUMINANCE_OPTIONS_ONE, Integer.valueOf(optionsOne));
                                return;
                            }
                            if (i2 == 4) {
                                MeShowSettingActivity.this.mLuminanceOptionsOne = 1;
                                MeShowSettingActivity.this.tvLuminance.setText(oneValue);
                                SharedPreferencesUtils.put(MeShowSettingActivity.this.context, Constant.SpConstKey.LUMINANCE_OPTIONS_ONE, Integer.valueOf(optionsOne));
                            } else {
                                if (i2 != 5) {
                                    return;
                                }
                                MeShowSettingActivity.this.mLuminanceOptionsOne = 3;
                                MeShowSettingActivity.this.tvLuminance.setText(oneValue);
                                SharedPreferencesUtils.put(MeShowSettingActivity.this.context, Constant.SpConstKey.LUMINANCE_OPTIONS_ONE, Integer.valueOf(optionsOne));
                            }
                        }
                    });
                }
            }
        });
    }

    public void onViewClicked(View view) {
        initLuminancePicker();
    }
}
