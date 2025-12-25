package com.yucheng.smarthealthpro.me.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.orhanobut.logger.Logger;
import com.realsil.sdk.dfu.DfuConstants;
import com.tencent.bugly.crashreport.CrashReport;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.databinding.ActivityMeHealthsettingBinding;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.framework.util.ToastUtil;
import com.yucheng.smarthealthpro.home.view.CustomAlarmClockWeekSelectors;
import com.yucheng.smarthealthpro.home.view.CustomSelectors;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.DebouncingClick;
import com.yucheng.smarthealthpro.utils.EventBusMessageEvent;
import com.yucheng.smarthealthpro.utils.FormatUtil;
import com.yucheng.smarthealthpro.utils.Tools;
import com.yucheng.smarthealthpro.utils.TransUtils;
import com.yucheng.smarthealthpro.utils.WeekUtil;
import com.yucheng.ycbtsdk.Constants;
import com.yucheng.ycbtsdk.YCBTClient;
import com.yucheng.ycbtsdk.response.BleDataResponse;
import java.io.UnsupportedEncodingException;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.commons.lang3.StringUtils;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes5.dex */
public class MeHealthSettingActivity extends BaseVbActivity<ActivityMeHealthsettingBinding> {
    private int firstBloodSugarIndex;
    private boolean isSwitchBedtimeWarn;
    private boolean isSwitchDrinkWater;
    private boolean isSwitchSedentaryWarn;
    ImageView ivBpCalibration;
    ImageView ivBpLevel;
    LinearLayout llDrinkWaterReminder;
    LinearLayout llDrinkWaterReminderContent;
    LinearLayout llHealthSetMonitoring;
    LinearLayout llLongSit;
    LinearLayout llRiskBedtimeValue;
    LinearLayout llRiskHeartValue;
    LinearLayout llRiskOxygenValue;
    LinearLayout llRiskTempValue;
    LinearLayout llSedentaryWarn;
    LinearLayout llTargetObject;
    private int mAfterBloodSugarValueIndex;
    private CustomSelectors mAmEndTimeCustomSelectors;
    private CustomSelectors mAmStartTimeCustomSelectors;
    private int mBedtimeStartHour;
    private int mBedtimeStartMin;
    private int mBedtimeWeek;
    private int mBeforBloodSugarValueIndex;
    private CustomSelectors mBloodFatSelectors;
    private CustomSelectors mBloodSugarCustomSelectors;
    private String mBloodSugarUnit;
    private int mBloodSugarValueIndex;
    private CustomSelectors mBpCustomSelectors;
    private int mBpDbpOptionsTwo;
    private CustomSelectors mBpLevelCustomSelectors;
    private int mBpLevelOptionsOne;
    private int mBpSbpOptionsOne;
    private int mDrinkWaterEndHour;
    private int mDrinkWaterEndMin;
    private int mDrinkWaterIntervalTime;
    private int mDrinkWaterStartHour;
    private int mDrinkWaterStartMin;
    private int mDrinkWaterWeek;
    private CustomSelectors mHealthMonitoringIntervalCustomSelectors;
    private int mHealthMonitoringIntervalOptionsOne;
    private String mHeartInterval;
    private String mHeartRemind;
    private int mHeartRemindOptionsOne;
    private CustomSelectors mHeartRiskCustomSelectors;
    private int mMovingObject;
    private String mOxygenRemind;
    private int mOxygenRemindOptionsOne;
    private CustomSelectors mPmEndTimeCustomSelectors;
    private CustomSelectors mPmStartTimeCustomSelectors;
    private int mSedentaryAmEndHour;
    private int mSedentaryAmEndMin;
    private int mSedentaryAmStartHour;
    private int mSedentaryAmStartMin;
    private CustomSelectors mSedentaryIntervalCustomSelectors;
    private int mSedentaryIntervalTime;
    private int mSedentaryPmEndHour;
    private int mSedentaryPmEndMin;
    private int mSedentaryPmStartHour;
    private int mSedentaryPmStartMin;
    private CustomSelectors mSleepCustomSelectors;
    private int mSleepQuality;
    private CustomSelectors mSportCustomSelectors;
    Switch mSwitchDrinkWater;
    Switch mSwitchHealthMonitoring;
    Switch mSwitchRiskBedtimeWarn;
    Switch mSwitchRiskHeartWarn;
    Switch mSwitchRiskOxygenWarn;
    Switch mSwitchRiskTempWarn;
    Switch mSwitchSedentaryWarn;
    private String mTempRemind;
    private int mTempRemindOptionsOne;
    private CustomSelectors mTempRiskCustomSelectors;
    private CustomSelectors mUricAcidSelectors;
    private int mWeek;
    private String movingObject;
    View rlBedtimeWarnReuse;
    RelativeLayout rlBloodFatCalibration;
    RelativeLayout rlBloodSugarCalibration;
    RelativeLayout rlBpCalibration;
    RelativeLayout rlBpGrade;
    RelativeLayout rlHealthMonitoringInterval;
    RelativeLayout rlMovingObject;
    RelativeLayout rlRiskBedtimeValue;
    RelativeLayout rlRiskHeartValue;
    RelativeLayout rlRiskOxygenValue;
    RelativeLayout rlRiskTempValue;
    RelativeLayout rlSedentaryWarnAmEndTime;
    RelativeLayout rlSedentaryWarnAmStartTime;
    RelativeLayout rlSedentaryWarnInterval;
    RelativeLayout rlSedentaryWarnPmEndTime;
    RelativeLayout rlSedentaryWarnPmStartTime;
    RelativeLayout rlSedentaryWarnReuse;
    RelativeLayout rlSleepQuality;
    RelativeLayout rlUricAcidCalibration;
    private int secondBloodSugarIndex;
    private String sleepQuality;
    View targetViewLine;
    private String tempUnit;
    TextView tvBedtimeWarnReuse;
    TextView tvBloodSugarCalibration;
    TextView tvBpCalibration;
    TextView tvBpCalibrationTitle;
    TextView tvBpLevel;
    TextView tvDrinkWaterEndTime;
    TextView tvDrinkWaterInterval;
    TextView tvDrinkWaterRepeat;
    TextView tvDrinkWaterStartTime;
    TextView tvHealthMonitoringIntervalValue;
    TextView tvMovingObject;
    TextView tvRiskBedtimeValue;
    TextView tvRiskHeartValue;
    TextView tvRiskOxygenValue;
    TextView tvRiskTempValue;
    TextView tvSedentaryWarnAmEndTime;
    TextView tvSedentaryWarnAmStartTime;
    TextView tvSedentaryWarnInterval;
    TextView tvSedentaryWarnPmEndTime;
    TextView tvSedentaryWarnPmStartTime;
    TextView tvSedentaryWarnReuse;
    TextView tvSleepQuality;
    TextView tv_blood_fat_calibration;
    TextView tv_uric_acid_calibration;
    private ArrayList<String> exerciseList = new ArrayList<>();
    private ArrayList<String> sleepList = new ArrayList<>();
    private ArrayList<String> secondBloodSugarList = new ArrayList<>();
    private ArrayList<String> newBloodSugarList = new ArrayList<>();
    private ArrayList<String> newAfterBloodSugarList = new ArrayList<>();
    private ArrayList<String> firstList = new ArrayList<>();
    private ArrayList<String> secondList = new ArrayList<>();
    private ArrayList<String> bpLevelList = new ArrayList<>();
    private ArrayList<String> mHealthMonitoringIntervalList = new ArrayList<>();
    private ArrayList<String> mTempRiskValueList = new ArrayList<>();
    private ArrayList<String> mHeartRateRiskValueList = new ArrayList<>();
    private ArrayList<String> mSedentaryReminderIntervalList = new ArrayList<>();
    private ArrayList<String> mFirstHourList = new ArrayList<>();
    private ArrayList<String> mSecondMinuteList = new ArrayList<>();
    private String mAlternativeDate = "0000000";
    private String mBedtimeAlternativeDate = "1111100";
    private final int failed_msg = 16;
    private final int success_msg = 17;
    private ArrayList<String> mDrinkWaterIntervalList = new ArrayList<>();
    private final int blood_sugar_compared = 1;
    private final int blood_sugar_compared_mg = 18;
    private final int blood_sugar_trans = 18;
    boolean isCanClick = true;
    private Handler mHandler = new Handler() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                MeHealthSettingActivity.this.tvMovingObject.setText(MeHealthSettingActivity.this.movingObject + MeHealthSettingActivity.this.getString(R.string.step_unit));
                MeHealthSettingActivity meHealthSettingActivity = MeHealthSettingActivity.this;
                meHealthSettingActivity.mMovingObject = Integer.parseInt(meHealthSettingActivity.movingObject);
                SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.TARGET_NUMBER_OF_MOVEMENT_STEPS, Integer.valueOf(MeHealthSettingActivity.this.mMovingObject));
            } else if (msg.what == 2) {
                MeHealthSettingActivity.this.tvSleepQuality.setText(MeHealthSettingActivity.this.sleepQuality + MeHealthSettingActivity.this.getString(R.string.time_hour_unit));
                MeHealthSettingActivity meHealthSettingActivity2 = MeHealthSettingActivity.this;
                meHealthSettingActivity2.mSleepQuality = Integer.parseInt(meHealthSettingActivity2.sleepQuality);
                SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.TARGET_SLEEP_TIME, Integer.valueOf(MeHealthSettingActivity.this.mSleepQuality));
            } else if (msg.what == 5) {
                MeHealthSettingActivity.this.rlHealthMonitoringInterval.setVisibility(0);
                MeHealthSettingActivity.this.tvHealthMonitoringIntervalValue.setText(MeHealthSettingActivity.this.mHeartInterval + MeHealthSettingActivity.this.getString(R.string.time_mins_unit));
                SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.HEART_RATE_DETECTION_SWITCH, Constant.SpConstValue.OPEN);
            } else if (msg.what == 6) {
                MeHealthSettingActivity.this.rlHealthMonitoringInterval.setVisibility(8);
                SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.HEART_RATE_DETECTION_SWITCH, Constant.SpConstValue.CLOSE);
            }
            if (msg.what == 7) {
                MeHealthSettingActivity.this.tvHealthMonitoringIntervalValue.setText(MeHealthSettingActivity.this.mHeartInterval + MeHealthSettingActivity.this.getString(R.string.time_mins_unit));
                SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.HEART_RATE_INTERVAL_TIME, MeHealthSettingActivity.this.mHeartInterval);
                return;
            }
            if (msg.what == 9) {
                MeHealthSettingActivity.this.tvRiskHeartValue.setText(MeHealthSettingActivity.this.mHeartRemind);
                SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.HEART_RATE_ALARM_REMINDER_SWITCH, MeHealthSettingActivity.this.mHeartRemind);
                return;
            }
            if (msg.what == 11) {
                MeHealthSettingActivity.this.rlRiskHeartValue.setVisibility(0);
                MeHealthSettingActivity.this.tvRiskHeartValue.setText(MeHealthSettingActivity.this.mHeartRemind);
                SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.HEART_RATE_ALARM_SWITCH, Constant.SpConstValue.OPEN);
                SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.HEART_RATE_ALARM_REMINDER_SWITCH, MeHealthSettingActivity.this.mHeartRemind);
                return;
            }
            if (msg.what == 12) {
                MeHealthSettingActivity.this.rlRiskHeartValue.setVisibility(8);
                SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.HEART_RATE_ALARM_SWITCH, Constant.SpConstValue.CLOSE);
                return;
            }
            if (msg.what == 13) {
                MeHealthSettingActivity.this.rlRiskTempValue.setVisibility(0);
                SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.TEMPERATURE_ALARM_SWITCH, Constant.SpConstValue.OPEN);
                return;
            }
            if (msg.what == 14) {
                MeHealthSettingActivity.this.rlRiskTempValue.setVisibility(8);
                SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.TEMPERATURE_ALARM_SWITCH, Constant.SpConstValue.CLOSE);
                return;
            }
            if (msg.what == 15) {
                MeHealthSettingActivity.this.tvRiskTempValue.setText(MeHealthSettingActivity.this.mTempRemind + MeHealthSettingActivity.this.tempUnit);
                if (Constant.SpConstValue.TEMP_INCH.equals((String) SharedPreferencesUtils.get(MeHealthSettingActivity.this.context, Constant.SpConstKey.TEMP_UNIT, ""))) {
                    SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.TEMPERATURE_ALARM_REMINDER_VALUE, ((int) ((Integer.parseInt(MeHealthSettingActivity.this.mTempRemind) - 32) / 1.8d)) + "");
                    SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.TEMPERATURE_ALARM_REMINDER_F_VALUE, MeHealthSettingActivity.this.mTempRemind);
                    return;
                } else {
                    SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.TEMPERATURE_ALARM_REMINDER_VALUE, MeHealthSettingActivity.this.mTempRemind);
                    SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.TEMPERATURE_ALARM_REMINDER_F_VALUE, ((int) ((Integer.parseInt(MeHealthSettingActivity.this.mTempRemind) * 1.8d) + 32.0d)) + "");
                    return;
                }
            }
            if (msg.what == 16) {
                Toast.makeText(MeHealthSettingActivity.this.context, MeHealthSettingActivity.this.getString(R.string.health_set_failed), 0).show();
                return;
            }
            if (msg.what == 17) {
                ToastUtil.getInstance(MeHealthSettingActivity.this.getApplicationContext()).toast(MeHealthSettingActivity.this.getString(R.string.save_successfully));
                return;
            }
            if (msg.what == 18) {
                MeHealthSettingActivity.this.rlRiskOxygenValue.setVisibility(0);
                MeHealthSettingActivity.this.tvRiskOxygenValue.setText(MeHealthSettingActivity.this.mOxygenRemind);
                SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.BLOOD_OXYGEN_ALARM_SWITCH, Constant.SpConstValue.OPEN);
                SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.BLOOD_OXYGEN_ALARM_REMINDER_SWITCH, MeHealthSettingActivity.this.mOxygenRemind);
                return;
            }
            if (msg.what == 19) {
                MeHealthSettingActivity.this.rlRiskOxygenValue.setVisibility(8);
                SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.BLOOD_OXYGEN_ALARM_SWITCH, Constant.SpConstValue.CLOSE);
                return;
            }
            if (msg.what == 20) {
                if (MeHealthSettingActivity.this.isSwitchBedtimeWarn) {
                    MeHealthSettingActivity.this.rlRiskBedtimeValue.setVisibility(0);
                    MeHealthSettingActivity.this.rlBedtimeWarnReuse.setVisibility(0);
                    SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.BEDTIME_ALARM_SWITCH, Constant.SpConstValue.OPEN);
                } else {
                    MeHealthSettingActivity.this.rlRiskBedtimeValue.setVisibility(8);
                    MeHealthSettingActivity.this.rlBedtimeWarnReuse.setVisibility(8);
                    SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.BEDTIME_ALARM_SWITCH, Constant.SpConstValue.CLOSE);
                }
                String string = (MeHealthSettingActivity.this.mBedtimeStartHour < 10 ? new StringBuilder("0").append(MeHealthSettingActivity.this.mBedtimeStartHour) : new StringBuilder().append(MeHealthSettingActivity.this.mBedtimeStartHour).append("")).toString();
                String string2 = (MeHealthSettingActivity.this.mBedtimeStartMin < 10 ? new StringBuilder("0").append(MeHealthSettingActivity.this.mBedtimeStartMin) : new StringBuilder().append(MeHealthSettingActivity.this.mBedtimeStartMin).append("")).toString();
                MeHealthSettingActivity.this.tvRiskBedtimeValue.setText(string + ":" + string2);
                SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.BEDTIME_START_HOUR, string);
                SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.BEDTIME_START_MIN, string2);
                return;
            }
            if (msg.what == 21) {
                if (MeHealthSettingActivity.this.isSwitchBedtimeWarn) {
                    MeHealthSettingActivity.this.rlRiskBedtimeValue.setVisibility(0);
                    MeHealthSettingActivity.this.rlBedtimeWarnReuse.setVisibility(0);
                    SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.BEDTIME_ALARM_SWITCH, Constant.SpConstValue.OPEN);
                } else {
                    MeHealthSettingActivity.this.rlRiskBedtimeValue.setVisibility(8);
                    MeHealthSettingActivity.this.rlBedtimeWarnReuse.setVisibility(8);
                    SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.BEDTIME_ALARM_SWITCH, Constant.SpConstValue.CLOSE);
                }
            }
        }
    };
    int firstSelect = 0;
    int secondSelect = 0;

    @Override // com.yucheng.smarthealthpro.framework.BaseActivity
    public void hideKeyboard(IBinder token) {
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        this.mBloodSugarUnit = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.BLOOD_SUGAR_AND_BLOOD_FAT_UNIT, getString(R.string.blood_sugar_unit_1));
        initView();
        initData();
        if (Constant.isTechFeel()) {
            this.rlSedentaryWarnPmStartTime.setVisibility(8);
            this.rlSedentaryWarnPmEndTime.setVisibility(8);
        }
    }

    private void initView() {
        String str;
        StringBuilder sb;
        StringBuilder sbAppend;
        StringBuilder sb2;
        StringBuilder sbAppend2;
        String str2;
        this.tvMovingObject = ((ActivityMeHealthsettingBinding) this.mBinding).tvMovingObject;
        this.llTargetObject = ((ActivityMeHealthsettingBinding) this.mBinding).llTargetObject;
        this.rlMovingObject = ((ActivityMeHealthsettingBinding) this.mBinding).rlMovingObject;
        this.targetViewLine = ((ActivityMeHealthsettingBinding) this.mBinding).targetViewLine;
        this.tvSleepQuality = ((ActivityMeHealthsettingBinding) this.mBinding).tvSleepQuality;
        this.rlSleepQuality = ((ActivityMeHealthsettingBinding) this.mBinding).rlSleepQuality;
        this.tvBpCalibration = ((ActivityMeHealthsettingBinding) this.mBinding).tvBpCalibration;
        this.tvBloodSugarCalibration = ((ActivityMeHealthsettingBinding) this.mBinding).tvBloodSugarCalibration;
        this.ivBpCalibration = ((ActivityMeHealthsettingBinding) this.mBinding).ivBpCalibration;
        this.rlBloodSugarCalibration = ((ActivityMeHealthsettingBinding) this.mBinding).rlBloodSugarCalibration;
        this.rlBpCalibration = ((ActivityMeHealthsettingBinding) this.mBinding).rlBpCalibration;
        this.tvBpCalibrationTitle = ((ActivityMeHealthsettingBinding) this.mBinding).tvBpCalibrationTitle;
        this.tvBpLevel = ((ActivityMeHealthsettingBinding) this.mBinding).tvBpLevel;
        this.ivBpLevel = ((ActivityMeHealthsettingBinding) this.mBinding).ivBpLevel;
        this.rlBpGrade = ((ActivityMeHealthsettingBinding) this.mBinding).rlBpGrade;
        this.mSwitchRiskTempWarn = ((ActivityMeHealthsettingBinding) this.mBinding).switchRiskTempWarn;
        this.tvRiskTempValue = ((ActivityMeHealthsettingBinding) this.mBinding).tvRiskTempValue;
        this.llRiskTempValue = ((ActivityMeHealthsettingBinding) this.mBinding).llRiskTempValue;
        this.rlRiskTempValue = ((ActivityMeHealthsettingBinding) this.mBinding).rlRiskTempValue;
        this.llRiskOxygenValue = ((ActivityMeHealthsettingBinding) this.mBinding).llRiskOxygenValue;
        this.mSwitchRiskOxygenWarn = ((ActivityMeHealthsettingBinding) this.mBinding).switchRiskOxygenWarn;
        this.rlRiskOxygenValue = ((ActivityMeHealthsettingBinding) this.mBinding).rlRiskOxygenValue;
        this.tvRiskOxygenValue = ((ActivityMeHealthsettingBinding) this.mBinding).tvRiskOxygenValue;
        this.mSwitchRiskHeartWarn = ((ActivityMeHealthsettingBinding) this.mBinding).switchRiskHeartWarn;
        this.tvRiskHeartValue = ((ActivityMeHealthsettingBinding) this.mBinding).tvRiskHeartValue;
        this.llRiskHeartValue = ((ActivityMeHealthsettingBinding) this.mBinding).llRiskHeartValue;
        this.rlRiskHeartValue = ((ActivityMeHealthsettingBinding) this.mBinding).rlRiskHeartValue;
        this.llLongSit = ((ActivityMeHealthsettingBinding) this.mBinding).llLongSit;
        this.mSwitchSedentaryWarn = ((ActivityMeHealthsettingBinding) this.mBinding).switchSedentaryWarn;
        this.tvSedentaryWarnInterval = ((ActivityMeHealthsettingBinding) this.mBinding).tvSedentaryWarnInterval;
        this.rlSedentaryWarnInterval = ((ActivityMeHealthsettingBinding) this.mBinding).rlSedentaryWarnInterval;
        this.tvSedentaryWarnReuse = ((ActivityMeHealthsettingBinding) this.mBinding).tvSedentaryWarnReuse;
        this.rlSedentaryWarnReuse = ((ActivityMeHealthsettingBinding) this.mBinding).rlSedentaryWarnReuse;
        this.tvSedentaryWarnAmStartTime = ((ActivityMeHealthsettingBinding) this.mBinding).tvSedentaryWarnAmStartTime;
        this.rlSedentaryWarnAmStartTime = ((ActivityMeHealthsettingBinding) this.mBinding).rlSedentaryWarnAmStartTime;
        this.tvSedentaryWarnAmEndTime = ((ActivityMeHealthsettingBinding) this.mBinding).tvSedentaryWarnAmEndTime;
        this.rlSedentaryWarnAmEndTime = ((ActivityMeHealthsettingBinding) this.mBinding).rlSedentaryWarnAmEndTime;
        this.tvSedentaryWarnPmStartTime = ((ActivityMeHealthsettingBinding) this.mBinding).tvSedentaryWarnPmStartTime;
        this.rlSedentaryWarnPmStartTime = ((ActivityMeHealthsettingBinding) this.mBinding).rlSedentaryWarnPmStartTime;
        this.tvSedentaryWarnPmEndTime = ((ActivityMeHealthsettingBinding) this.mBinding).tvSedentaryWarnPmEndTime;
        this.rlSedentaryWarnPmEndTime = ((ActivityMeHealthsettingBinding) this.mBinding).rlSedentaryWarnPmEndTime;
        this.llSedentaryWarn = ((ActivityMeHealthsettingBinding) this.mBinding).llSedentaryWarn;
        this.mSwitchHealthMonitoring = ((ActivityMeHealthsettingBinding) this.mBinding).switchHealthMonitoring;
        this.tvHealthMonitoringIntervalValue = ((ActivityMeHealthsettingBinding) this.mBinding).tvHealthMonitoringIntervalValue;
        this.rlHealthMonitoringInterval = ((ActivityMeHealthsettingBinding) this.mBinding).rlHealthMonitoringInterval;
        this.llHealthSetMonitoring = ((ActivityMeHealthsettingBinding) this.mBinding).llHealthSetMonitoring;
        this.rlBloodFatCalibration = ((ActivityMeHealthsettingBinding) this.mBinding).rlBloodFatCalibration;
        this.rlUricAcidCalibration = ((ActivityMeHealthsettingBinding) this.mBinding).rlUricAcidCalibration;
        this.tv_blood_fat_calibration = ((ActivityMeHealthsettingBinding) this.mBinding).tvBloodFatCalibration;
        this.tv_uric_acid_calibration = ((ActivityMeHealthsettingBinding) this.mBinding).tvUricAcidCalibration;
        this.llRiskBedtimeValue = ((ActivityMeHealthsettingBinding) this.mBinding).llRiskBedtimeValue;
        this.mSwitchRiskBedtimeWarn = ((ActivityMeHealthsettingBinding) this.mBinding).switchRiskBedtimeWarn;
        this.rlRiskBedtimeValue = ((ActivityMeHealthsettingBinding) this.mBinding).rlRiskBedtimeValue;
        this.tvRiskBedtimeValue = ((ActivityMeHealthsettingBinding) this.mBinding).tvRiskBedtimeValue;
        this.rlBedtimeWarnReuse = ((ActivityMeHealthsettingBinding) this.mBinding).rlBedtimeWarnReuse;
        this.tvBedtimeWarnReuse = ((ActivityMeHealthsettingBinding) this.mBinding).tvBedtimeWarnReuse;
        this.llDrinkWaterReminder = ((ActivityMeHealthsettingBinding) this.mBinding).llDrinkWaterReminder;
        this.llDrinkWaterReminderContent = ((ActivityMeHealthsettingBinding) this.mBinding).llDrinkWaterReminderContent;
        this.mSwitchDrinkWater = ((ActivityMeHealthsettingBinding) this.mBinding).switchDrinkWater;
        this.tvDrinkWaterStartTime = ((ActivityMeHealthsettingBinding) this.mBinding).tvDrinkWaterReminderStartTime;
        this.tvDrinkWaterEndTime = ((ActivityMeHealthsettingBinding) this.mBinding).tvDrinkWaterReminderEndTime;
        this.tvDrinkWaterInterval = ((ActivityMeHealthsettingBinding) this.mBinding).tvDrinkWaterReminderInterval;
        this.tvDrinkWaterRepeat = ((ActivityMeHealthsettingBinding) this.mBinding).tvDrinkWaterReminderRepeat;
        this.rlMovingObject.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity$$ExternalSyntheticLambda1
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlSleepQuality.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity$$ExternalSyntheticLambda1
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlBloodSugarCalibration.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity$$ExternalSyntheticLambda1
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlBpCalibration.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity$$ExternalSyntheticLambda1
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlBpGrade.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity$$ExternalSyntheticLambda1
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlMovingObject.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity$$ExternalSyntheticLambda1
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlHealthMonitoringInterval.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity$$ExternalSyntheticLambda1
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlSedentaryWarnInterval.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity$$ExternalSyntheticLambda1
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlSedentaryWarnReuse.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity$$ExternalSyntheticLambda1
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlRiskTempValue.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity$$ExternalSyntheticLambda1
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlRiskHeartValue.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity$$ExternalSyntheticLambda1
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlSedentaryWarnAmStartTime.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity$$ExternalSyntheticLambda1
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlSedentaryWarnAmEndTime.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity$$ExternalSyntheticLambda1
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlSedentaryWarnPmStartTime.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity$$ExternalSyntheticLambda1
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlSedentaryWarnPmEndTime.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity$$ExternalSyntheticLambda1
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        ((ActivityMeHealthsettingBinding) this.mBinding).rlDrinkWaterReminderRepeat.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity$$ExternalSyntheticLambda1
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        ((ActivityMeHealthsettingBinding) this.mBinding).rlDrinkWaterReminderInterval.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity$$ExternalSyntheticLambda1
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        ((ActivityMeHealthsettingBinding) this.mBinding).rlDrinkWaterReminderStartTime.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity$$ExternalSyntheticLambda1
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        ((ActivityMeHealthsettingBinding) this.mBinding).rlDrinkWaterReminderEndTime.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity$$ExternalSyntheticLambda1
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        ((ActivityMeHealthsettingBinding) this.mBinding).rlBloodFatCalibration.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity$$ExternalSyntheticLambda1
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        ((ActivityMeHealthsettingBinding) this.mBinding).rlUricAcidCalibration.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity$$ExternalSyntheticLambda1
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        ((ActivityMeHealthsettingBinding) this.mBinding).rlRiskOxygenValue.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity$$ExternalSyntheticLambda1
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        ((ActivityMeHealthsettingBinding) this.mBinding).rlRiskBedtimeValue.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity$$ExternalSyntheticLambda1
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        ((ActivityMeHealthsettingBinding) this.mBinding).rlBedtimeWarnReuse.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity$$ExternalSyntheticLambda1
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        changeTitle(getString(R.string.include_bottom_tv_first_button));
        showBack();
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASHEARTRATE) || YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASTEMP)) {
            this.llHealthSetMonitoring.setVisibility(0);
        } else {
            this.llHealthSetMonitoring.setVisibility(8);
        }
        if (!YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASSTEPCOUNT) && !YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASSLEEP)) {
            this.llTargetObject.setVisibility(8);
        } else {
            if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASSTEPCOUNT)) {
                this.rlMovingObject.setVisibility(0);
                this.targetViewLine.setVisibility(0);
            } else {
                this.rlMovingObject.setVisibility(8);
                this.targetViewLine.setVisibility(8);
            }
            if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASSLEEP)) {
                this.rlSleepQuality.setVisibility(0);
                this.targetViewLine.setVisibility(0);
            } else {
                this.rlSleepQuality.setVisibility(8);
                this.targetViewLine.setVisibility(8);
            }
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASBLOODPRESSURECALIBRATION)) {
            this.rlBpCalibration.setVisibility(0);
            if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASINFLATED)) {
                this.tvBpCalibrationTitle.setText(R.string.photoelectric_blood_pressure_calibration);
            }
        } else if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASBLOODLEVEL)) {
            this.rlBpGrade.setVisibility(0);
            this.rlBpCalibration.setVisibility(8);
        } else {
            this.rlBpGrade.setVisibility(8);
            this.rlBpCalibration.setVisibility(8);
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASBLOODSUGAR)) {
            this.rlBloodSugarCalibration.setVisibility(0);
        } else {
            this.rlBloodSugarCalibration.setVisibility(8);
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASTEMPALARM)) {
            this.llRiskTempValue.setVisibility(0);
        } else {
            this.llRiskTempValue.setVisibility(8);
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASBLOODOXYGENALARM)) {
            this.llRiskOxygenValue.setVisibility(0);
        } else {
            this.llRiskOxygenValue.setVisibility(8);
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASHEARTALARM)) {
            this.llRiskHeartValue.setVisibility(0);
        } else {
            this.llRiskHeartValue.setVisibility(8);
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.IS_HAS_SLEEP_REMIND)) {
            this.llRiskBedtimeValue.setVisibility(0);
        } else {
            this.llRiskBedtimeValue.setVisibility(8);
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASLONGSITTING)) {
            this.llLongSit.setVisibility(0);
            setLongSitReminder();
        } else {
            this.llLongSit.setVisibility(8);
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASDRINKWATERREMINDER)) {
            this.llDrinkWaterReminder.setVisibility(0);
            setDrinkWaterReminder();
        } else {
            this.llDrinkWaterReminder.setVisibility(8);
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASIMPRECISEBLOODFAT)) {
            this.rlBloodFatCalibration.setVisibility(0);
        } else {
            this.rlBloodFatCalibration.setVisibility(8);
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASURICACIDMEASUREMENT)) {
            this.rlUricAcidCalibration.setVisibility(0);
        } else {
            this.rlUricAcidCalibration.setVisibility(8);
        }
        String str3 = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.BLOOD_SUGAR_AND_BLOOD_FAT_UNIT, Constant.SpConstValue.BLOOD_SUGAR_AND_BLOOD_FAT_MMOL);
        if ("mg/dL".equals(str3)) {
            this.tv_blood_fat_calibration.setText(FormatUtil.keep2(((Float) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.BLOOD_FAT_CALIBRATION_MG_VALUE, Float.valueOf(193.05f))).floatValue()) + str3);
        } else {
            this.tv_blood_fat_calibration.setText(FormatUtil.keep2(((Float) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.BLOOD_FAT_CALIBRATION_VALUE, Float.valueOf(5.0f))).floatValue()) + str3);
        }
        String str4 = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.URIC_ACID_UNIT, Constant.SpConstValue.URIC_ACID_UMOL);
        if ("mg/dL".equals(str4)) {
            this.tv_uric_acid_calibration.setText(((Float) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.URIC_ACID_CALIBRATION_MG_VALUE, Float.valueOf(3.4f))).floatValue() + str4);
        } else {
            this.tv_uric_acid_calibration.setText(((int) ((Float) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.URIC_ACID_CALIBRATION_VALUE, Float.valueOf(200.0f))).floatValue()) + str4);
        }
        this.mMovingObject = ((Integer) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.TARGET_NUMBER_OF_MOVEMENT_STEPS, Integer.valueOf(DfuConstants.MAX_NOTIFICATION_LOCK_WAIT_TIME))).intValue();
        this.tvMovingObject.setText(this.mMovingObject + getString(R.string.step_unit));
        try {
            this.mSleepQuality = ((Integer) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.TARGET_SLEEP_TIME, 8)).intValue();
        } catch (Exception e2) {
            this.mSleepQuality = 8;
            CrashReport.postCatchedException(e2);
        }
        this.tvSleepQuality.setText(this.mSleepQuality + getString(R.string.time_hour_unit));
        int i2 = 360;
        int i3 = 54;
        if ("".equals(YCBTClient.getBloodSugarVersion())) {
            if (getString(R.string.blood_sugar_unit_2).equals(this.mBloodSugarUnit)) {
                this.mBloodSugarValueIndex = ((Integer) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.BLOOD_SUGAR_CALIBRATION_MG_VALUE, 25)).intValue();
                this.tvBloodSugarCalibration.setText((this.mBloodSugarValueIndex + 54) + this.mBloodSugarUnit);
            } else {
                this.mBloodSugarValueIndex = ((Integer) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.BLOOD_SUGAR_CALIBRATION_VALUE, 14)).intValue();
                this.tvBloodSugarCalibration.setText(((this.mBloodSugarValueIndex + 30) / 10.0f) + this.mBloodSugarUnit);
                i2 = 200;
                i3 = 30;
            }
            while (i3 <= i2) {
                if (getString(R.string.blood_sugar_unit_2).equals(this.mBloodSugarUnit)) {
                    this.secondBloodSugarList.add(i3 + this.mBloodSugarUnit);
                } else {
                    this.secondBloodSugarList.add((i3 / 10.0f) + this.mBloodSugarUnit);
                }
                i3++;
            }
        } else if (getString(R.string.blood_sugar_unit_2).equals(this.mBloodSugarUnit)) {
            this.mBeforBloodSugarValueIndex = ((Integer) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.BEFORE_BLOOD_SUGAR_CALIBRATION_MG_VALUE, 90)).intValue();
            this.mAfterBloodSugarValueIndex = ((Integer) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.AFTER_BLOOD_SUGAR_CALIBRATION_MG_VALUE, 144)).intValue();
            for (int i4 = 54; i4 <= 360; i4++) {
                this.newBloodSugarList.add(i4 + "");
            }
            this.tvBloodSugarCalibration.setText((this.mBeforBloodSugarValueIndex + 54) + "/" + (this.mAfterBloodSugarValueIndex + 54) + this.mBloodSugarUnit);
        } else {
            this.mBeforBloodSugarValueIndex = ((Integer) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.BEFORE_BLOOD_SUGAR_CALIBRATION_VALUE, 50)).intValue();
            this.mAfterBloodSugarValueIndex = ((Integer) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.AFTER_BLOOD_SUGAR_CALIBRATION_VALUE, 80)).intValue();
            for (int i5 = 30; i5 <= 200; i5++) {
                this.newBloodSugarList.add((i5 / 10.0f) + "");
            }
            this.tvBloodSugarCalibration.setText(((this.mBeforBloodSugarValueIndex + 30) / 10.0f) + "/" + ((this.mAfterBloodSugarValueIndex + 30) / 10.0f) + this.mBloodSugarUnit);
        }
        String str5 = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.BP_CALIBRATION_SBP_VALUE, "120");
        String str6 = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.BP_CALIBRATION_DBP_VALUE, "80");
        if (str5 != null && str6 != null && !str5.isEmpty() && !str6.isEmpty()) {
            this.tvBpCalibration.setText(str5 + "/" + str6);
            this.mBpSbpOptionsOne = Integer.parseInt(str5) - 70;
            this.mBpDbpOptionsTwo = Integer.parseInt(str6) - 40;
        } else {
            this.tvBpCalibration.setText("");
        }
        String str7 = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.BP_CALIBRATION_LEVEL, "");
        if (str7 != null && !str7.isEmpty()) {
            this.tvBpLevel.setText(str7);
        } else {
            this.tvBpLevel.setText("");
        }
        int iIntValue = ((Integer) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.BP_LEVEL_OPTIONS_ONE, 1)).intValue();
        if (iIntValue != 0) {
            this.mBpLevelOptionsOne = iIntValue;
        }
        if (YCBTClient.connectState() == 10) {
            String str8 = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.HEART_RATE_DETECTION_SWITCH, Constant.getDefaultMonitorSwitch());
            if (str8 != null && str8.equals(Constant.SpConstValue.CLOSE)) {
                this.rlHealthMonitoringInterval.setVisibility(8);
                this.mSwitchHealthMonitoring.setChecked(false);
            } else {
                this.rlHealthMonitoringInterval.setVisibility(0);
                this.mSwitchHealthMonitoring.setChecked(true);
            }
        }
        String str9 = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.HEART_RATE_INTERVAL_TIME, Constant.getDefaultMonitorTime());
        this.mHeartInterval = str9;
        if (str9 != null && !str9.isEmpty()) {
            this.tvHealthMonitoringIntervalValue.setText(this.mHeartInterval + getString(R.string.time_mins_unit));
            try {
                if (Constant.isRing()) {
                    this.mHealthMonitoringIntervalOptionsOne = (Integer.parseInt(this.mHeartInterval) / 10) - 3;
                } else {
                    this.mHealthMonitoringIntervalOptionsOne = (Integer.parseInt(this.mHeartInterval) / 10) - 1;
                }
            } catch (Exception e3) {
                e3.printStackTrace();
                CrashReport.postCatchedException(e3);
            }
        } else {
            this.mHealthMonitoringIntervalOptionsOne = 0;
        }
        String str10 = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.TEMPERATURE_ALARM_SWITCH, "");
        if (str10 != null && str10.equals(Constant.SpConstValue.OPEN)) {
            this.rlRiskTempValue.setVisibility(0);
            this.mSwitchRiskTempWarn.setChecked(true);
        } else {
            this.rlRiskTempValue.setVisibility(8);
            this.mSwitchRiskTempWarn.setChecked(false);
        }
        if (Constant.SpConstValue.TEMP_INCH.equals((String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.TEMP_UNIT, ""))) {
            this.mTempRemind = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.TEMPERATURE_ALARM_REMINDER_F_VALUE, "100");
            this.tempUnit = getString(R.string.temp_f_unit);
            this.tvRiskTempValue.setText(this.mTempRemind + this.tempUnit);
            try {
                this.mTempRemindOptionsOne = Integer.parseInt(this.mTempRemind) - 98;
            } catch (Exception e4) {
                e4.printStackTrace();
                CrashReport.postCatchedException(e4);
                this.mTempRemindOptionsOne = 2;
            }
            for (int i6 = 98; i6 <= 117; i6++) {
                this.mTempRiskValueList.add(i6 + "");
            }
        } else {
            this.mTempRemind = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.TEMPERATURE_ALARM_REMINDER_VALUE, "38");
            this.tempUnit = getString(R.string.temp_c_unit);
            this.tvRiskTempValue.setText(this.mTempRemind + this.tempUnit);
            try {
                this.mTempRemindOptionsOne = Integer.parseInt(this.mTempRemind) - 37;
            } catch (Exception e5) {
                e5.printStackTrace();
                CrashReport.postCatchedException(e5);
                this.mTempRemindOptionsOne = 2;
            }
            for (int i7 = 37; i7 <= 42; i7++) {
                this.mTempRiskValueList.add(i7 + "");
            }
        }
        if (Constant.SpConstValue.OPEN.equals((String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.BLOOD_OXYGEN_ALARM_SWITCH, ""))) {
            this.rlRiskOxygenValue.setVisibility(0);
            this.mSwitchRiskOxygenWarn.setChecked(true);
        } else {
            this.rlRiskOxygenValue.setVisibility(8);
            this.mSwitchRiskOxygenWarn.setChecked(false);
        }
        String str11 = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.BLOOD_OXYGEN_ALARM_REMINDER_SWITCH, "");
        if (!TextUtils.isEmpty(str11)) {
            this.tvRiskOxygenValue.setText(str11);
            this.mOxygenRemindOptionsOne = Integer.parseInt(str11) - 70;
            this.mOxygenRemind = str11;
        } else {
            this.mOxygenRemindOptionsOne = 11;
            this.mOxygenRemind = "80";
        }
        if (Constant.SpConstValue.OPEN.equals((String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.BEDTIME_ALARM_SWITCH, ""))) {
            this.rlRiskBedtimeValue.setVisibility(0);
            this.rlBedtimeWarnReuse.setVisibility(0);
            this.mSwitchRiskBedtimeWarn.setChecked(true);
            this.isSwitchBedtimeWarn = true;
        } else {
            this.mSwitchRiskBedtimeWarn.setChecked(false);
            this.rlRiskBedtimeValue.setVisibility(8);
            this.rlBedtimeWarnReuse.setVisibility(8);
            this.isSwitchBedtimeWarn = false;
        }
        String str12 = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.BEDTIME_WEEK, "");
        if (!TextUtils.isEmpty(str12)) {
            if (this.isSwitchBedtimeWarn) {
                str2 = str12 + "1";
            } else {
                str2 = str12 + "0";
            }
            this.mBedtimeWeek = Integer.parseInt(Tools.BinaryToHex(str2), 16);
            this.tvBedtimeWarnReuse.setText(WeekUtil.getLable(str12.toCharArray(), this));
            Logger.w(str2 + StringUtils.SPACE + this.mBedtimeWeek, new Object[0]);
            this.mBedtimeAlternativeDate = str12;
            setBedtimeWarnReuse(str12);
        } else {
            if (this.isSwitchBedtimeWarn) {
                str = this.mBedtimeAlternativeDate + "1";
            } else {
                str = this.mAlternativeDate + "0";
            }
            this.mBedtimeWeek = Integer.parseInt(Tools.BinaryToHex(str), 16);
            this.tvBedtimeWarnReuse.setText(getString(R.string.pulbic_never));
        }
        String str13 = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.BEDTIME_START_HOUR, "");
        String str14 = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.BEDTIME_START_MIN, "");
        if (!TextUtils.isEmpty(str13) && !TextUtils.isEmpty(str14)) {
            this.tvRiskBedtimeValue.setText(str13 + ":" + str14);
            this.mBedtimeStartHour = Integer.parseInt(str13);
            this.mBedtimeStartMin = Integer.parseInt(str14);
        } else {
            this.mBedtimeStartHour = 22;
            this.mBedtimeStartMin = 30;
        }
        String str15 = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.HEART_RATE_ALARM_SWITCH, "");
        if (str15 != null && str15.equals(Constant.SpConstValue.OPEN)) {
            this.rlRiskHeartValue.setVisibility(0);
            this.mSwitchRiskHeartWarn.setChecked(true);
        } else {
            this.rlRiskHeartValue.setVisibility(8);
            this.mSwitchRiskHeartWarn.setChecked(false);
        }
        String str16 = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.HEART_RATE_ALARM_REMINDER_SWITCH, "");
        if (str16 != null && !str16.isEmpty()) {
            this.tvRiskHeartValue.setText(str16);
            this.mHeartRemindOptionsOne = (Integer.parseInt(str16) / 10) - 10;
            this.mHeartRemind = str16;
        } else {
            this.mHeartRemindOptionsOne = 0;
            this.mHeartRemind = "100";
        }
        int iIntValue2 = ((Integer) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.SEDENTARY_INTERVAL_TIME, 0)).intValue();
        if (iIntValue2 != 0) {
            this.tvSedentaryWarnInterval.setText(iIntValue2 + getString(R.string.time_mins_unit));
            this.mSedentaryIntervalTime = iIntValue2;
        } else {
            this.mSedentaryIntervalTime = 30;
        }
        String str17 = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.SEDENTARY_INTERVAL_WEEK, "");
        Logger.d("ltf mSedentaryIntervalWeek=" + str17);
        if (str17 != null && !str17.isEmpty()) {
            if (this.isSwitchSedentaryWarn) {
                this.mWeek = Integer.parseInt(Tools.BinaryToHex(str17 + "1"), 16);
            } else {
                this.mWeek = Integer.parseInt(Tools.BinaryToHex(str17 + "0"), 16);
            }
            String lable = WeekUtil.getLable(str17.toCharArray(), this);
            Logger.d("ltf lable=" + lable);
            this.tvSedentaryWarnReuse.setText(lable);
            this.mAlternativeDate = str17;
        } else {
            if (this.isSwitchSedentaryWarn) {
                this.mWeek = Integer.parseInt(Tools.BinaryToHex(this.mAlternativeDate + "1"), 16);
            } else {
                this.mWeek = Integer.parseInt(Tools.BinaryToHex(this.mAlternativeDate + "0"), 16);
            }
            this.tvSedentaryWarnReuse.setText(getString(R.string.pulbic_never));
        }
        String str18 = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.SEDENTARY_AM_START_HOUR, "");
        String str19 = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.SEDENTARY_AM_START_MIN, "");
        if (str18 != null && str19 != null && !str18.isEmpty() && !str19.isEmpty()) {
            this.tvSedentaryWarnAmStartTime.setText(str18 + ":" + str19);
            this.mSedentaryAmStartHour = Integer.parseInt(str18);
            this.mSedentaryAmStartMin = Integer.parseInt(str19);
        } else {
            this.mSedentaryAmStartHour = 9;
            this.mSedentaryAmStartMin = 1;
        }
        String str20 = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.SEDENTARY_AM_END_HOUR, "");
        String str21 = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.SEDENTARY_AM_END_MIN, "");
        if (str20 != null && str21 != null && !str20.isEmpty() && !str21.isEmpty()) {
            this.tvSedentaryWarnAmEndTime.setText(str20 + ":" + str21);
            this.mSedentaryAmEndHour = Integer.parseInt(str20);
            this.mSedentaryAmEndMin = Integer.parseInt(str21);
        } else {
            this.mSedentaryAmEndHour = 12;
            this.mSedentaryAmEndMin = 30;
        }
        String str22 = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.SEDENTARY_PM_START_HOUR, "");
        String str23 = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.SEDENTARY_PM_START_MIN, "");
        if (str22 != null && str23 != null && !str22.isEmpty() && !str23.isEmpty()) {
            this.tvSedentaryWarnPmStartTime.setText(str22 + ":" + str23);
            this.mSedentaryPmStartHour = Integer.parseInt(str22);
            this.mSedentaryPmStartMin = Integer.parseInt(str23);
        } else {
            this.mSedentaryPmStartHour = 14;
            this.mSedentaryPmStartMin = 1;
        }
        String str24 = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.SEDENTARY_PM_END_HOUR, "");
        String str25 = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.SEDENTARY_PM_END_MIN, "");
        if (str24 != null && str25 != null && !str24.isEmpty() && !str25.isEmpty()) {
            this.tvSedentaryWarnPmEndTime.setText(str24 + ":" + str25);
            this.mSedentaryPmEndHour = Integer.parseInt(str24);
            this.mSedentaryPmEndMin = Integer.parseInt(str25);
        } else {
            this.mSedentaryPmEndHour = 18;
            this.mSedentaryPmEndMin = 30;
        }
        for (int i8 = 2000; i8 <= 30000; i8 += 1000) {
            this.exerciseList.add(i8 + "");
        }
        for (int i9 = 6; i9 <= 16; i9++) {
            this.sleepList.add(i9 + "");
        }
        for (int i10 = 70; i10 <= 230; i10++) {
            this.firstList.add(i10 + "");
        }
        for (int i11 = 40; i11 <= 150; i11++) {
            this.secondList.add(i11 + "");
        }
        this.bpLevelList.add(getString(R.string.value_low));
        this.bpLevelList.add(getString(R.string.value_normal));
        this.bpLevelList.add(getString(R.string.bp_selectors_list_two));
        this.bpLevelList.add(getString(R.string.bp_selectors_list_four));
        this.bpLevelList.add(getString(R.string.bp_selectors_list_five));
        for (int i12 = (!Constant.isRing() || Constant.isHealthRing()) ? 10 : 30; i12 <= 60; i12 += 10) {
            this.mHealthMonitoringIntervalList.add(i12 + "");
            if (Constant.isMymon()) {
                break;
            }
        }
        for (int i13 = 100; i13 <= 220; i13 += 10) {
            this.mHeartRateRiskValueList.add(i13 + "");
        }
        for (int i14 = 15; i14 <= 60; i14 += 15) {
            this.mSedentaryReminderIntervalList.add(i14 + "");
        }
        for (int i15 = 0; i15 <= 23; i15++) {
            ArrayList<String> arrayList = this.mFirstHourList;
            if (i15 < 10) {
                sb2 = new StringBuilder("0");
                sbAppend2 = sb2.append(i15);
            } else {
                sb2 = new StringBuilder();
                sbAppend2 = sb2.append(i15).append("");
            }
            arrayList.add(sbAppend2.toString());
        }
        for (int i16 = 0; i16 <= 59; i16++) {
            ArrayList<String> arrayList2 = this.mSecondMinuteList;
            if (i16 < 10) {
                sb = new StringBuilder("0");
                sbAppend = sb.append(i16);
            } else {
                sb = new StringBuilder();
                sbAppend = sb.append(i16).append("");
            }
            arrayList2.add(sbAppend.toString());
        }
    }

    /* renamed from: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity$2, reason: invalid class name */
    class AnonymousClass2 implements CompoundButton.OnCheckedChangeListener {
        AnonymousClass2() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                if (YCBTClient.connectState() == 10) {
                    YCBTClient.settingHeartMonitor(1, (MeHealthSettingActivity.this.mHealthMonitoringIntervalOptionsOne + 1) * 10, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity.2.1
                        @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                        public void onDataResponse(int code, float ratio, HashMap resultMap) {
                            if (code == 0) {
                                if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASTEMP)) {
                                    YCBTClient.settingTemperatureMonitor(true, (MeHealthSettingActivity.this.mHealthMonitoringIntervalOptionsOne + 1) * 10, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity.2.1.1
                                        @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                                        public void onDataResponse(int code2, float ratio2, HashMap resultMap2) {
                                            MeHealthSettingActivity.this.mHandler.sendEmptyMessage(5);
                                            MeHealthSettingActivity.this.mHandler.sendEmptyMessage(17);
                                        }
                                    });
                                } else {
                                    MeHealthSettingActivity.this.mHandler.sendEmptyMessage(5);
                                    MeHealthSettingActivity.this.mHandler.sendEmptyMessage(17);
                                }
                            }
                        }
                    });
                    return;
                } else {
                    Toast.makeText(MeHealthSettingActivity.this.context, MeHealthSettingActivity.this.getString(R.string.please_connect_the_device), 0).show();
                    MeHealthSettingActivity.this.mSwitchHealthMonitoring.setChecked(false);
                    return;
                }
            }
            if (YCBTClient.connectState() == 10) {
                YCBTClient.settingHeartMonitor(0, (MeHealthSettingActivity.this.mHealthMonitoringIntervalOptionsOne + 1) * 10, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity.2.2
                    @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                    public void onDataResponse(int code, float ratio, HashMap resultMap) {
                        if (code == 0) {
                            if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASTEMP)) {
                                YCBTClient.settingTemperatureMonitor(false, (MeHealthSettingActivity.this.mHealthMonitoringIntervalOptionsOne + 1) * 10, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity.2.2.1
                                    @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                                    public void onDataResponse(int code2, float ratio2, HashMap resultMap2) {
                                        MeHealthSettingActivity.this.mHandler.sendEmptyMessage(6);
                                        MeHealthSettingActivity.this.mHandler.sendEmptyMessage(17);
                                    }
                                });
                            } else {
                                MeHealthSettingActivity.this.mHandler.sendEmptyMessage(6);
                                MeHealthSettingActivity.this.mHandler.sendEmptyMessage(17);
                            }
                        }
                    }
                });
            } else {
                MeHealthSettingActivity.this.mSwitchHealthMonitoring.setChecked(false);
                Toast.makeText(MeHealthSettingActivity.this.context, MeHealthSettingActivity.this.getString(R.string.please_connect_the_device), 0).show();
            }
        }
    }

    private void initData() {
        this.mSwitchHealthMonitoring.setOnCheckedChangeListener(new AnonymousClass2());
        this.mSwitchRiskTempWarn.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (YCBTClient.connectState() != 10) {
                    Toast.makeText(MeHealthSettingActivity.this.context, MeHealthSettingActivity.this.getString(R.string.please_connect_the_device), 0).show();
                    MeHealthSettingActivity.this.mSwitchRiskTempWarn.setChecked(!MeHealthSettingActivity.this.mSwitchRiskTempWarn.isChecked());
                } else if (MeHealthSettingActivity.this.mSwitchRiskTempWarn.isChecked()) {
                    YCBTClient.settingTemperatureAlarm(true, Integer.parseInt(MeHealthSettingActivity.this.mTempRemind), new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity.3.1
                        @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                        public void onDataResponse(int code, float ratio, HashMap resultMap) {
                            if (code == 0) {
                                MeHealthSettingActivity.this.mHandler.sendEmptyMessage(13);
                                MeHealthSettingActivity.this.mHandler.sendEmptyMessage(17);
                            }
                        }
                    });
                } else {
                    YCBTClient.settingTemperatureAlarm(false, 0, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity.3.2
                        @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                        public void onDataResponse(int code, float ratio, HashMap resultMap) {
                            if (code == 0) {
                                MeHealthSettingActivity.this.mHandler.sendEmptyMessage(14);
                                MeHealthSettingActivity.this.mHandler.sendEmptyMessage(17);
                            }
                        }
                    });
                }
            }
        });
        this.mSwitchRiskHeartWarn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity.4
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (YCBTClient.connectState() == 10) {
                        YCBTClient.settingHeartAlarm(1, Integer.parseInt(MeHealthSettingActivity.this.mHeartRemind), 0, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity.4.1
                            @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                            public void onDataResponse(int code, float ratio, HashMap resultMap) {
                                if (code == 0) {
                                    MeHealthSettingActivity.this.mHandler.sendEmptyMessage(11);
                                    MeHealthSettingActivity.this.mHandler.sendEmptyMessage(17);
                                }
                            }
                        });
                        return;
                    } else {
                        MeHealthSettingActivity.this.mSwitchRiskHeartWarn.setChecked(false);
                        Toast.makeText(MeHealthSettingActivity.this.context, MeHealthSettingActivity.this.getString(R.string.please_connect_the_device), 0).show();
                        return;
                    }
                }
                if (YCBTClient.connectState() == 10) {
                    YCBTClient.settingHeartAlarm(0, 0, 0, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity.4.2
                        @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                        public void onDataResponse(int code, float ratio, HashMap resultMap) {
                            if (code == 0) {
                                MeHealthSettingActivity.this.mHandler.sendEmptyMessage(12);
                                MeHealthSettingActivity.this.mHandler.sendEmptyMessage(17);
                            }
                        }
                    });
                } else {
                    MeHealthSettingActivity.this.mSwitchRiskHeartWarn.setChecked(false);
                    Toast.makeText(MeHealthSettingActivity.this.context, MeHealthSettingActivity.this.getString(R.string.please_connect_the_device), 0).show();
                }
            }
        });
        this.mSwitchRiskOxygenWarn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity.5
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (YCBTClient.connectState() != 10) {
                    Toast.makeText(MeHealthSettingActivity.this.context, MeHealthSettingActivity.this.getString(R.string.please_connect_the_device), 0).show();
                } else if (isChecked) {
                    Logger.i("" + Integer.parseInt(MeHealthSettingActivity.this.mOxygenRemind), new Object[0]);
                    YCBTClient.settingBloodOxygenAlarm(1, Integer.parseInt(MeHealthSettingActivity.this.mOxygenRemind), new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity.5.1
                        @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                        public void onDataResponse(int code, float ratio, HashMap resultMap) {
                            if (code == 0) {
                                MeHealthSettingActivity.this.mHandler.sendEmptyMessage(18);
                                MeHealthSettingActivity.this.mHandler.sendEmptyMessage(17);
                            }
                        }
                    });
                } else {
                    YCBTClient.settingBloodOxygenAlarm(0, Integer.parseInt(MeHealthSettingActivity.this.mOxygenRemind), new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity.5.2
                        @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                        public void onDataResponse(int code, float ratio, HashMap resultMap) {
                            if (code == 0) {
                                MeHealthSettingActivity.this.mHandler.sendEmptyMessage(19);
                                MeHealthSettingActivity.this.mHandler.sendEmptyMessage(17);
                            }
                        }
                    });
                }
            }
        });
        this.mSwitchRiskBedtimeWarn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity.6
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (YCBTClient.connectState() != 10) {
                    Toast.makeText(MeHealthSettingActivity.this.context, MeHealthSettingActivity.this.getString(R.string.please_connect_the_device), 0).show();
                    return;
                }
                MeHealthSettingActivity.this.isSwitchBedtimeWarn = isChecked;
                if (isChecked) {
                    MeHealthSettingActivity.this.mBedtimeWeek = Integer.parseInt(Tools.BinaryToHex(new StringBuffer(MeHealthSettingActivity.this.mBedtimeAlternativeDate + "1").reverse().toString()), 16);
                } else {
                    MeHealthSettingActivity.this.mBedtimeWeek = Integer.parseInt(Tools.BinaryToHex(new StringBuffer(MeHealthSettingActivity.this.mBedtimeAlternativeDate + "0").reverse().toString()), 16);
                }
                Logger.i(MeHealthSettingActivity.this.mBedtimeAlternativeDate + "=" + MeHealthSettingActivity.this.mBedtimeWeek, new Object[0]);
                MeHealthSettingActivity.this.setBedtime();
                MeHealthSettingActivity meHealthSettingActivity = MeHealthSettingActivity.this;
                meHealthSettingActivity.setBedtimeWarnReuse(meHealthSettingActivity.mBedtimeAlternativeDate);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBedtime() {
        YCBTClient.settingSleepRemind(this.mBedtimeStartHour, this.mBedtimeStartMin, this.mBedtimeWeek, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity.7
            @Override // com.yucheng.ycbtsdk.response.BleDataResponse
            public void onDataResponse(int code, float ratio, HashMap resultMap) {
                if (code == 0) {
                    MeHealthSettingActivity.this.mHandler.sendEmptyMessage(20);
                    MeHealthSettingActivity.this.mHandler.sendEmptyMessage(17);
                } else {
                    MeHealthSettingActivity.this.mHandler.sendEmptyMessage(21);
                    MeHealthSettingActivity.this.mHandler.sendEmptyMessage(16);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSedentary(final int mSwitch, final String value) {
        if (Constant.isTechFeel()) {
            this.mSedentaryPmStartHour = this.mSedentaryAmStartHour;
            this.mSedentaryPmStartMin = this.mSedentaryAmStartMin;
            this.mSedentaryPmEndHour = this.mSedentaryAmEndHour;
            this.mSedentaryPmEndMin = this.mSedentaryAmEndMin;
        }
        Logger.w("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,", Integer.valueOf(this.mSedentaryAmStartHour), Integer.valueOf(this.mSedentaryAmStartMin), Integer.valueOf(this.mSedentaryAmEndHour), Integer.valueOf(this.mSedentaryAmEndMin), Integer.valueOf(this.mSedentaryPmStartHour), Integer.valueOf(this.mSedentaryPmStartMin), Integer.valueOf(this.mSedentaryPmEndHour), Integer.valueOf(this.mSedentaryPmEndMin), Integer.valueOf(this.mSedentaryIntervalTime), Integer.valueOf(this.mWeek));
        Log.e("TAG", "mWeek========" + Integer.toHexString(this.mWeek));
        YCBTClient.settingLongsite(this.mSedentaryAmStartHour, this.mSedentaryAmStartMin, this.mSedentaryAmEndHour, this.mSedentaryAmEndMin, this.mSedentaryPmStartHour, this.mSedentaryPmStartMin, this.mSedentaryPmEndHour, this.mSedentaryPmEndMin, this.mSedentaryIntervalTime, this.mWeek, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity.8
            @Override // com.yucheng.ycbtsdk.response.BleDataResponse
            public void onDataResponse(int code, float v, HashMap hashMap) {
                if (code == 0) {
                    Log.i("AAAAAA", "==设置成功==");
                    MeHealthSettingActivity.this.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity.8.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MeHealthSettingActivity.this.mHandler.sendEmptyMessage(17);
                            Logger.d("ltf put SEDENTARY_INTERVAL_WEEK=" + MeHealthSettingActivity.this.mAlternativeDate);
                            switch (mSwitch) {
                                case 0:
                                    SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.SEDENTARY_REMINDER_SWITCH, value);
                                    SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.SEDENTARY_INTERVAL_TIME, Integer.valueOf(MeHealthSettingActivity.this.mSedentaryIntervalTime));
                                    SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.SEDENTARY_INTERVAL_WEEK, MeHealthSettingActivity.this.mAlternativeDate);
                                    SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.SEDENTARY_AM_START_HOUR, (MeHealthSettingActivity.this.mSedentaryAmStartHour < 10 ? new StringBuilder("0").append(MeHealthSettingActivity.this.mSedentaryAmStartHour) : new StringBuilder().append(MeHealthSettingActivity.this.mSedentaryAmStartHour).append("")).toString());
                                    SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.SEDENTARY_AM_START_MIN, (MeHealthSettingActivity.this.mSedentaryAmStartMin < 10 ? new StringBuilder("0").append(MeHealthSettingActivity.this.mSedentaryAmStartMin) : new StringBuilder().append(MeHealthSettingActivity.this.mSedentaryAmStartMin).append("")).toString());
                                    SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.SEDENTARY_AM_END_HOUR, (MeHealthSettingActivity.this.mSedentaryAmEndHour < 10 ? new StringBuilder("0").append(MeHealthSettingActivity.this.mSedentaryAmEndHour) : new StringBuilder().append(MeHealthSettingActivity.this.mSedentaryAmEndHour).append("")).toString());
                                    SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.SEDENTARY_AM_END_MIN, (MeHealthSettingActivity.this.mSedentaryAmEndMin < 10 ? new StringBuilder("0").append(MeHealthSettingActivity.this.mSedentaryAmEndMin) : new StringBuilder().append(MeHealthSettingActivity.this.mSedentaryAmEndMin).append("")).toString());
                                    SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.SEDENTARY_PM_START_HOUR, (MeHealthSettingActivity.this.mSedentaryPmStartHour < 10 ? new StringBuilder("0").append(MeHealthSettingActivity.this.mSedentaryPmStartHour) : new StringBuilder().append(MeHealthSettingActivity.this.mSedentaryPmStartHour).append("")).toString());
                                    SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.SEDENTARY_PM_START_MIN, (MeHealthSettingActivity.this.mSedentaryPmStartMin < 10 ? new StringBuilder("0").append(MeHealthSettingActivity.this.mSedentaryPmStartMin) : new StringBuilder().append(MeHealthSettingActivity.this.mSedentaryPmStartMin).append("")).toString());
                                    SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.SEDENTARY_PM_END_HOUR, (MeHealthSettingActivity.this.mSedentaryPmEndHour < 10 ? new StringBuilder("0").append(MeHealthSettingActivity.this.mSedentaryPmEndHour) : new StringBuilder().append(MeHealthSettingActivity.this.mSedentaryPmEndHour).append("")).toString());
                                    SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.SEDENTARY_PM_END_MIN, (MeHealthSettingActivity.this.mSedentaryPmEndMin < 10 ? new StringBuilder("0").append(MeHealthSettingActivity.this.mSedentaryPmEndMin) : new StringBuilder().append(MeHealthSettingActivity.this.mSedentaryPmEndMin).append("")).toString());
                                    MeHealthSettingActivity.this.tvSedentaryWarnInterval.setText(MeHealthSettingActivity.this.mSedentaryIntervalTime + MeHealthSettingActivity.this.getString(R.string.time_mins_unit));
                                    MeHealthSettingActivity.this.setSedentaryWarnReuse(MeHealthSettingActivity.this.mAlternativeDate);
                                    MeHealthSettingActivity.this.tvSedentaryWarnAmStartTime.setText((MeHealthSettingActivity.this.mSedentaryAmStartHour < 10 ? "0" + MeHealthSettingActivity.this.mSedentaryAmStartHour : Integer.valueOf(MeHealthSettingActivity.this.mSedentaryAmStartHour)) + ":" + (MeHealthSettingActivity.this.mSedentaryAmStartMin < 10 ? "0" + MeHealthSettingActivity.this.mSedentaryAmStartMin : Integer.valueOf(MeHealthSettingActivity.this.mSedentaryAmStartMin)));
                                    MeHealthSettingActivity.this.tvSedentaryWarnAmEndTime.setText((MeHealthSettingActivity.this.mSedentaryAmEndHour < 10 ? "0" + MeHealthSettingActivity.this.mSedentaryAmEndHour : Integer.valueOf(MeHealthSettingActivity.this.mSedentaryAmEndHour)) + ":" + (MeHealthSettingActivity.this.mSedentaryAmEndMin < 10 ? "0" + MeHealthSettingActivity.this.mSedentaryAmEndMin : Integer.valueOf(MeHealthSettingActivity.this.mSedentaryAmEndMin)));
                                    MeHealthSettingActivity.this.tvSedentaryWarnPmStartTime.setText((MeHealthSettingActivity.this.mSedentaryPmStartHour < 10 ? "0" + MeHealthSettingActivity.this.mSedentaryPmStartHour : Integer.valueOf(MeHealthSettingActivity.this.mSedentaryPmStartHour)) + ":" + (MeHealthSettingActivity.this.mSedentaryPmStartMin < 10 ? "0" + MeHealthSettingActivity.this.mSedentaryPmStartMin : Integer.valueOf(MeHealthSettingActivity.this.mSedentaryPmStartMin)));
                                    MeHealthSettingActivity.this.tvSedentaryWarnPmEndTime.setText((MeHealthSettingActivity.this.mSedentaryPmEndHour < 10 ? "0" + MeHealthSettingActivity.this.mSedentaryPmEndHour : Integer.valueOf(MeHealthSettingActivity.this.mSedentaryPmEndHour)) + ":" + (MeHealthSettingActivity.this.mSedentaryPmEndMin < 10 ? "0" + MeHealthSettingActivity.this.mSedentaryPmEndMin : Integer.valueOf(MeHealthSettingActivity.this.mSedentaryPmEndMin)));
                                    break;
                                case 1:
                                    MeHealthSettingActivity.this.tvSedentaryWarnInterval.setText(MeHealthSettingActivity.this.mSedentaryIntervalTime + MeHealthSettingActivity.this.getString(R.string.time_mins_unit));
                                    SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.SEDENTARY_INTERVAL_TIME, Integer.valueOf(MeHealthSettingActivity.this.mSedentaryIntervalTime));
                                    break;
                                case 2:
                                    SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.SEDENTARY_INTERVAL_WEEK, value);
                                    break;
                                case 3:
                                    MeHealthSettingActivity.this.tvSedentaryWarnAmStartTime.setText((MeHealthSettingActivity.this.mSedentaryAmStartHour < 10 ? "0" + MeHealthSettingActivity.this.mSedentaryAmStartHour : Integer.valueOf(MeHealthSettingActivity.this.mSedentaryAmStartHour)) + ":" + (MeHealthSettingActivity.this.mSedentaryAmStartMin < 10 ? "0" + MeHealthSettingActivity.this.mSedentaryAmStartMin : Integer.valueOf(MeHealthSettingActivity.this.mSedentaryAmStartMin)));
                                    SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.SEDENTARY_AM_START_HOUR, (MeHealthSettingActivity.this.mSedentaryAmStartHour < 10 ? new StringBuilder("0").append(MeHealthSettingActivity.this.mSedentaryAmStartHour) : new StringBuilder().append(MeHealthSettingActivity.this.mSedentaryAmStartHour).append("")).toString());
                                    SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.SEDENTARY_AM_START_MIN, (MeHealthSettingActivity.this.mSedentaryAmStartMin < 10 ? new StringBuilder("0").append(MeHealthSettingActivity.this.mSedentaryAmStartMin) : new StringBuilder().append(MeHealthSettingActivity.this.mSedentaryAmStartMin).append("")).toString());
                                    break;
                                case 4:
                                    MeHealthSettingActivity.this.tvSedentaryWarnAmEndTime.setText((MeHealthSettingActivity.this.mSedentaryAmEndHour < 10 ? "0" + MeHealthSettingActivity.this.mSedentaryAmEndHour : Integer.valueOf(MeHealthSettingActivity.this.mSedentaryAmEndHour)) + ":" + (MeHealthSettingActivity.this.mSedentaryAmEndMin < 10 ? "0" + MeHealthSettingActivity.this.mSedentaryAmEndMin : Integer.valueOf(MeHealthSettingActivity.this.mSedentaryAmEndMin)));
                                    SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.SEDENTARY_AM_END_HOUR, (MeHealthSettingActivity.this.mSedentaryAmEndHour < 10 ? new StringBuilder("0").append(MeHealthSettingActivity.this.mSedentaryAmEndHour) : new StringBuilder().append(MeHealthSettingActivity.this.mSedentaryAmEndHour).append("")).toString());
                                    SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.SEDENTARY_AM_END_MIN, (MeHealthSettingActivity.this.mSedentaryAmEndMin < 10 ? new StringBuilder("0").append(MeHealthSettingActivity.this.mSedentaryAmEndMin) : new StringBuilder().append(MeHealthSettingActivity.this.mSedentaryAmEndMin).append("")).toString());
                                    break;
                                case 5:
                                    MeHealthSettingActivity.this.tvSedentaryWarnPmStartTime.setText((MeHealthSettingActivity.this.mSedentaryPmStartHour < 10 ? "0" + MeHealthSettingActivity.this.mSedentaryPmStartHour : Integer.valueOf(MeHealthSettingActivity.this.mSedentaryPmStartHour)) + ":" + (MeHealthSettingActivity.this.mSedentaryPmStartMin < 10 ? "0" + MeHealthSettingActivity.this.mSedentaryPmStartMin : Integer.valueOf(MeHealthSettingActivity.this.mSedentaryPmStartMin)));
                                    SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.SEDENTARY_PM_START_HOUR, (MeHealthSettingActivity.this.mSedentaryPmStartHour < 10 ? new StringBuilder("0").append(MeHealthSettingActivity.this.mSedentaryPmStartHour) : new StringBuilder().append(MeHealthSettingActivity.this.mSedentaryPmStartHour).append("")).toString());
                                    SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.SEDENTARY_PM_START_MIN, (MeHealthSettingActivity.this.mSedentaryPmStartMin < 10 ? new StringBuilder("0").append(MeHealthSettingActivity.this.mSedentaryPmStartMin) : new StringBuilder().append(MeHealthSettingActivity.this.mSedentaryPmStartMin).append("")).toString());
                                    break;
                                case 6:
                                    MeHealthSettingActivity.this.tvSedentaryWarnPmEndTime.setText((MeHealthSettingActivity.this.mSedentaryPmEndHour < 10 ? "0" + MeHealthSettingActivity.this.mSedentaryPmEndHour : Integer.valueOf(MeHealthSettingActivity.this.mSedentaryPmEndHour)) + ":" + (MeHealthSettingActivity.this.mSedentaryPmEndMin < 10 ? "0" + MeHealthSettingActivity.this.mSedentaryPmEndMin : Integer.valueOf(MeHealthSettingActivity.this.mSedentaryPmEndMin)));
                                    SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.SEDENTARY_PM_END_HOUR, (MeHealthSettingActivity.this.mSedentaryPmEndHour < 10 ? new StringBuilder("0").append(MeHealthSettingActivity.this.mSedentaryPmEndHour) : new StringBuilder().append(MeHealthSettingActivity.this.mSedentaryPmEndHour).append("")).toString());
                                    SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.SEDENTARY_PM_END_MIN, (MeHealthSettingActivity.this.mSedentaryPmEndMin < 10 ? new StringBuilder("0").append(MeHealthSettingActivity.this.mSedentaryPmEndMin) : new StringBuilder().append(MeHealthSettingActivity.this.mSedentaryPmEndMin).append("")).toString());
                                    break;
                            }
                        }
                    });
                } else {
                    MeHealthSettingActivity.this.mHandler.sendEmptyMessage(16);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSedentaryWarnReuse(String week) {
        if (week != null) {
            String lable = WeekUtil.getLable(week.toCharArray(), this);
            Logger.d("ltf lable=" + lable);
            this.tvSedentaryWarnReuse.setText(lable);
            this.mAlternativeDate = week;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBedtimeWarnReuse(String week) {
        if (week != null) {
            this.tvBedtimeWarnReuse.setText(WeekUtil.getLable(week.toCharArray(), this));
            this.mBedtimeAlternativeDate = week;
        }
    }

    private void initMovingObjectPicker() {
        CustomSelectors customSelectors = new CustomSelectors();
        this.mSportCustomSelectors = customSelectors;
        customSelectors.BpLevelPicker(this.exerciseList, null, null, (this.mMovingObject / 1000) - 2, 1, 1, "", "", "", false, CustomSelectors.IsShow.TOP_CONFIRM_CANCEL, CustomSelectors.SelectorsDataNum.ONE, this.context);
        this.mSportCustomSelectors.setOnOneSelectorsDataListener(new CustomSelectors.OnOneSelectorsDataListener() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity.9
            @Override // com.yucheng.smarthealthpro.home.view.CustomSelectors.OnOneSelectorsDataListener
            public void getSelectorsDataClick(final String oneValue, int optionsOne) {
                Log.i("CustomSelectors", "" + oneValue);
                YCBTClient.settingGoal(0, Integer.parseInt(oneValue), 0, 0, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity.9.1
                    @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                    public void onDataResponse(int code, float ratio, HashMap resultMap) {
                        if (code == 0) {
                            MeHealthSettingActivity.this.movingObject = oneValue;
                            MeHealthSettingActivity.this.mHandler.sendEmptyMessage(1);
                            MeHealthSettingActivity.this.mHandler.sendEmptyMessage(17);
                            return;
                        }
                        MeHealthSettingActivity.this.mHandler.sendEmptyMessage(16);
                    }
                });
            }
        });
    }

    private void initSleepObjectPicker() {
        CustomSelectors customSelectors = new CustomSelectors();
        this.mSleepCustomSelectors = customSelectors;
        customSelectors.BpLevelPicker(this.sleepList, null, null, this.mSleepQuality - 6, 1, 1, "", "", "", false, CustomSelectors.IsShow.TOP_CONFIRM_CANCEL, CustomSelectors.SelectorsDataNum.ONE, this.context);
        this.mSleepCustomSelectors.setOnOneSelectorsDataListener(new CustomSelectors.OnOneSelectorsDataListener() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity.10
            @Override // com.yucheng.smarthealthpro.home.view.CustomSelectors.OnOneSelectorsDataListener
            public void getSelectorsDataClick(final String oneValue, int optionsOne) {
                Log.i("CustomSelectors", "" + oneValue);
                YCBTClient.settingGoal(3, 0, Integer.parseInt(oneValue), 0, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity.10.1
                    @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                    public void onDataResponse(int code, float ratio, HashMap resultMap) {
                        if (code == 0) {
                            MeHealthSettingActivity.this.sleepQuality = oneValue;
                            MeHealthSettingActivity.this.mHandler.sendEmptyMessage(2);
                            MeHealthSettingActivity.this.mHandler.sendEmptyMessage(17);
                            return;
                        }
                        MeHealthSettingActivity.this.mHandler.sendEmptyMessage(16);
                    }
                });
            }
        });
    }

    private void initBloodSugarCalibrationlPicker() {
        CustomSelectors customSelectors = new CustomSelectors();
        this.mBloodSugarCustomSelectors = customSelectors;
        customSelectors.BpLevelPicker(this.secondBloodSugarList, null, null, this.mBloodSugarValueIndex, 1, 1, "", "", "", false, CustomSelectors.IsShow.TOP_CONFIRM_CANCEL, CustomSelectors.SelectorsDataNum.ONE, this.context);
        this.mBloodSugarCustomSelectors.setOnOneSelectorsDataListener(new AnonymousClass11());
    }

    /* renamed from: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity$11, reason: invalid class name */
    class AnonymousClass11 implements CustomSelectors.OnOneSelectorsDataListener {
        AnonymousClass11() {
        }

        @Override // com.yucheng.smarthealthpro.home.view.CustomSelectors.OnOneSelectorsDataListener
        public void getSelectorsDataClick(final String oneValue, final int optionsOne) {
            float f2 = MeHealthSettingActivity.this.getString(R.string.blood_sugar_unit_2).equals(MeHealthSettingActivity.this.mBloodSugarUnit) ? ((optionsOne + 54.0f) / 18.0f) * 10.0f : optionsOne + 30;
            YCBTClient.appBloodSugarCalibration((int) (f2 / 10.0f), (int) (f2 % 10.0f), 0, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity.11.1
                @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                public void onDataResponse(int code, float v, HashMap hashMap) {
                    if (code == 0) {
                        MeHealthSettingActivity.this.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity.11.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                MeHealthSettingActivity.this.mHandler.sendEmptyMessage(17);
                                MeHealthSettingActivity.this.tvBloodSugarCalibration.setText("" + oneValue);
                                MeHealthSettingActivity.this.mBloodSugarValueIndex = optionsOne;
                                if (MeHealthSettingActivity.this.getString(R.string.blood_sugar_unit_2).equals(MeHealthSettingActivity.this.mBloodSugarUnit)) {
                                    SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.BLOOD_SUGAR_CALIBRATION_VALUE, Integer.valueOf((int) (optionsOne / 1.8f)));
                                    SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.BLOOD_SUGAR_CALIBRATION_MG_VALUE, Integer.valueOf(optionsOne));
                                } else {
                                    SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.BLOOD_SUGAR_CALIBRATION_VALUE, Integer.valueOf(optionsOne));
                                    SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.BLOOD_SUGAR_CALIBRATION_MG_VALUE, Integer.valueOf((int) (optionsOne * 1.8f)));
                                }
                            }
                        });
                    } else {
                        MeHealthSettingActivity.this.mHandler.sendEmptyMessage(16);
                    }
                }
            });
        }
    }

    private void initNewBloodSugarCalibrationlPicker() {
        CustomSelectors customSelectors = new CustomSelectors();
        this.mBloodSugarCustomSelectors = customSelectors;
        ArrayList<String> arrayList = this.newBloodSugarList;
        customSelectors.BpLevelPicker(arrayList, arrayList, null, this.mBeforBloodSugarValueIndex, this.mAfterBloodSugarValueIndex, 1, "", "", "", false, CustomSelectors.IsShow.NEW_BLOOD_SUGAR, CustomSelectors.SelectorsDataNum.TWO, this.context);
        this.firstBloodSugarIndex = this.mBeforBloodSugarValueIndex;
        this.secondBloodSugarIndex = this.mAfterBloodSugarValueIndex;
        this.mBloodSugarCustomSelectors.setOnSelectChange(new CustomSelectors.OnSelectChange() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity.12
            @Override // com.yucheng.smarthealthpro.home.view.CustomSelectors.OnSelectChange
            public void onChange(OptionsPickerView pvBpOptions, int options1, int options2, int options3) throws NumberFormatException {
                float f2 = Float.parseFloat((String) MeHealthSettingActivity.this.newBloodSugarList.get(options1));
                float f3 = Float.parseFloat((String) MeHealthSettingActivity.this.newBloodSugarList.get(options2));
                boolean z = options1 == MeHealthSettingActivity.this.firstBloodSugarIndex;
                if ("mg/dL".equals(MeHealthSettingActivity.this.mBloodSugarUnit)) {
                    int size = MeHealthSettingActivity.this.newBloodSugarList.size();
                    if (f3 - f2 < 18.0f) {
                        if (z) {
                            int i2 = options1 + 18;
                            pvBpOptions.setSelectOptions(options1, i2);
                            MeHealthSettingActivity.this.firstBloodSugarIndex = options1;
                            MeHealthSettingActivity.this.secondBloodSugarIndex = i2;
                            return;
                        }
                        int i3 = options1 + 18;
                        if (i3 < size) {
                            pvBpOptions.setSelectOptions(options1, i3);
                            MeHealthSettingActivity.this.firstBloodSugarIndex = options1;
                            MeHealthSettingActivity.this.secondBloodSugarIndex = i3;
                            return;
                        } else {
                            int i4 = size - 19;
                            int i5 = size - 1;
                            pvBpOptions.setSelectOptions(i4, i5);
                            MeHealthSettingActivity.this.firstBloodSugarIndex = i4;
                            MeHealthSettingActivity.this.secondBloodSugarIndex = i5;
                            return;
                        }
                    }
                    return;
                }
                int size2 = MeHealthSettingActivity.this.newBloodSugarList.size();
                if (f3 - f2 < 1.0f) {
                    int i6 = options1 + 10;
                    if (z) {
                        pvBpOptions.setSelectOptions(options1, i6);
                        MeHealthSettingActivity.this.firstBloodSugarIndex = options1;
                        MeHealthSettingActivity.this.secondBloodSugarIndex = i6;
                    } else if (i6 < size2) {
                        pvBpOptions.setSelectOptions(options1, i6);
                        MeHealthSettingActivity.this.firstBloodSugarIndex = options1;
                        MeHealthSettingActivity.this.secondBloodSugarIndex = i6;
                    } else {
                        int i7 = size2 - 11;
                        int i8 = size2 - 1;
                        pvBpOptions.setSelectOptions(i7, i8);
                        MeHealthSettingActivity.this.firstBloodSugarIndex = i7;
                        MeHealthSettingActivity.this.secondBloodSugarIndex = i8;
                    }
                }
            }
        });
        this.mBloodSugarCustomSelectors.setOnTwoSelectorsDataListener(new AnonymousClass13());
    }

    /* renamed from: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity$13, reason: invalid class name */
    class AnonymousClass13 implements CustomSelectors.OnTwoSelectorsDataListener {
        AnonymousClass13() {
        }

        @Override // com.yucheng.smarthealthpro.home.view.CustomSelectors.OnTwoSelectorsDataListener
        public void getSelectorsDataClick(final String oneValue, final String twoValue, final int optionsOne, final int optionsTwo) throws NumberFormatException {
            int i2;
            int i3;
            if (MeHealthSettingActivity.this.getString(R.string.blood_sugar_unit_2).equals(MeHealthSettingActivity.this.mBloodSugarUnit)) {
                float f2 = Float.parseFloat(oneValue);
                i2 = (int) ((f2 / 1.8d) + 0.5d);
                i3 = (int) ((Float.parseFloat(twoValue) / 1.8d) + 0.5d);
                if (Float.parseFloat(twoValue) - f2 < 18.0f) {
                    ToastUtil.getInstance(MeHealthSettingActivity.this).toast(MeHealthSettingActivity.this.getString(R.string.health_blood_sugar_calibration_error).replace("1", "18"));
                    return;
                }
            } else {
                i2 = optionsOne + 30;
                i3 = optionsTwo + 30;
                if (i3 - i2 < 10) {
                    ToastUtil.getInstance(MeHealthSettingActivity.this).toast(MeHealthSettingActivity.this.getString(R.string.health_blood_sugar_calibration_error));
                    return;
                }
            }
            YCBTClient.appBloodSugarCalibration(i2 / 10, i2 % 10, 0, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity.13.1
                @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                public void onDataResponse(int code, float v, HashMap hashMap) {
                }
            });
            YCBTClient.appBloodSugarCalibration(i3 / 10, i3 % 10, 1, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity.13.2
                @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                public void onDataResponse(int code, float v, HashMap hashMap) {
                    if (code == 0) {
                        MeHealthSettingActivity.this.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity.13.2.1
                            @Override // java.lang.Runnable
                            public void run() {
                                MeHealthSettingActivity.this.mHandler.sendEmptyMessage(17);
                                MeHealthSettingActivity.this.tvBloodSugarCalibration.setText(oneValue + "/" + twoValue + MeHealthSettingActivity.this.mBloodSugarUnit);
                                MeHealthSettingActivity.this.mBeforBloodSugarValueIndex = optionsOne;
                                MeHealthSettingActivity.this.mAfterBloodSugarValueIndex = optionsTwo;
                                if (MeHealthSettingActivity.this.getString(R.string.blood_sugar_unit_2).equals(MeHealthSettingActivity.this.mBloodSugarUnit)) {
                                    SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.BEFORE_BLOOD_SUGAR_CALIBRATION_VALUE, Integer.valueOf((int) ((optionsOne / 1.8d) + 0.5d)));
                                    SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.BEFORE_BLOOD_SUGAR_CALIBRATION_MG_VALUE, Integer.valueOf(optionsOne));
                                    SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.AFTER_BLOOD_SUGAR_CALIBRATION_VALUE, Integer.valueOf((int) ((optionsTwo / 1.8d) + 0.5d)));
                                    SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.AFTER_BLOOD_SUGAR_CALIBRATION_MG_VALUE, Integer.valueOf(optionsTwo));
                                    return;
                                }
                                SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.BEFORE_BLOOD_SUGAR_CALIBRATION_VALUE, Integer.valueOf(optionsOne));
                                SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.BEFORE_BLOOD_SUGAR_CALIBRATION_MG_VALUE, Integer.valueOf((int) ((optionsOne * 1.8d) + 0.5d)));
                                SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.AFTER_BLOOD_SUGAR_CALIBRATION_VALUE, Integer.valueOf(optionsTwo));
                                SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.AFTER_BLOOD_SUGAR_CALIBRATION_MG_VALUE, Integer.valueOf((int) ((optionsTwo * 1.8d) + 0.5d)));
                            }
                        });
                    } else {
                        MeHealthSettingActivity.this.mHandler.sendEmptyMessage(16);
                    }
                }
            });
        }
    }

    private void initBpCalibrationlPicker() {
        CustomSelectors customSelectors = new CustomSelectors();
        this.mBpCustomSelectors = customSelectors;
        customSelectors.BpLevelPicker(this.firstList, this.secondList, null, this.mBpSbpOptionsOne, this.mBpDbpOptionsTwo, 1, "", "", "", false, CustomSelectors.IsShow.BP, CustomSelectors.SelectorsDataNum.TWO, this.context);
        this.firstSelect = this.mBpSbpOptionsOne;
        this.secondSelect = this.mBpDbpOptionsTwo;
        this.mBpCustomSelectors.setOnSelectChange(new CustomSelectors.OnSelectChange() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.home.view.CustomSelectors.OnSelectChange
            public final void onChange(OptionsPickerView optionsPickerView, int i2, int i3, int i4) throws NumberFormatException {
                this.f$0.lambda$initBpCalibrationlPicker$0(optionsPickerView, i2, i3, i4);
            }
        });
        this.mBpCustomSelectors.setOnTwoSelectorsDataListener(new AnonymousClass14());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initBpCalibrationlPicker$0(OptionsPickerView optionsPickerView, int i2, int i3, int i4) throws NumberFormatException {
        int i5 = Integer.parseInt(this.firstList.get(i2));
        int i6 = Integer.parseInt(this.secondList.get(i3));
        if (this.secondSelect != i3) {
            int i7 = i5 - i6;
            if (i7 < 10) {
                int iAbs = i2 + Math.abs(10 - i7);
                optionsPickerView.setSelectOptions(iAbs, i3);
                this.firstSelect = iAbs;
            } else if (i7 > 80) {
                int iAbs2 = i2 - Math.abs(80 - i7);
                optionsPickerView.setSelectOptions(iAbs2, i3);
                this.firstSelect = iAbs2;
            }
            this.secondSelect = i3;
            return;
        }
        int i8 = i5 - i6;
        if (i8 < 10) {
            int iAbs3 = i3 - Math.abs(10 - i8);
            optionsPickerView.setSelectOptions(i2, iAbs3);
            this.secondSelect = iAbs3;
        } else if (i8 > 80) {
            int iAbs4 = i3 + Math.abs(80 - i8);
            optionsPickerView.setSelectOptions(i2, iAbs4);
            this.secondSelect = iAbs4;
        }
        this.firstSelect = i2;
    }

    /* renamed from: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity$14, reason: invalid class name */
    class AnonymousClass14 implements CustomSelectors.OnTwoSelectorsDataListener {
        AnonymousClass14() {
        }

        @Override // com.yucheng.smarthealthpro.home.view.CustomSelectors.OnTwoSelectorsDataListener
        public void getSelectorsDataClick(final String oneValue, final String twoValue, final int optionsOne, final int optionsTwo) throws NumberFormatException {
            int i2;
            int i3 = Integer.parseInt(oneValue);
            int i4 = Integer.parseInt(twoValue);
            if (i4 < 30 || i4 > 150 || i3 < 60 || i3 > 250 || (i2 = i3 - i4) < 10 || i2 > 80) {
                ToastUtil.getInstance(MeHealthSettingActivity.this).toast(MeHealthSettingActivity.this.getString(R.string.blood_calibration_error1));
            } else {
                YCBTClient.appBloodCalibration(i3, i4, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity.14.1
                    @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                    public void onDataResponse(int code, float v, HashMap hashMap) {
                        if (code == 0) {
                            MeHealthSettingActivity.this.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity.14.1.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    MeHealthSettingActivity.this.mHandler.sendEmptyMessage(17);
                                    MeHealthSettingActivity.this.tvBpCalibration.setText(oneValue + "/" + twoValue);
                                    MeHealthSettingActivity.this.mBpSbpOptionsOne = optionsOne;
                                    MeHealthSettingActivity.this.mBpDbpOptionsTwo = optionsTwo;
                                    SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.BP_CALIBRATION_SBP_VALUE, oneValue);
                                    SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.BP_CALIBRATION_DBP_VALUE, twoValue);
                                }
                            });
                        } else {
                            MeHealthSettingActivity.this.mHandler.sendEmptyMessage(16);
                        }
                    }
                });
            }
        }
    }

    private void initBpLevelPicker() {
        CustomSelectors customSelectors = new CustomSelectors();
        this.mBpLevelCustomSelectors = customSelectors;
        customSelectors.BpLevelPicker(this.bpLevelList, null, null, this.mBpLevelOptionsOne, 1, 1, "", "", "", false, CustomSelectors.IsShow.TOP_CONFIRM_CANCEL, CustomSelectors.SelectorsDataNum.ONE, this.context);
        this.mBpLevelCustomSelectors.setOnOneSelectorsDataListener(new AnonymousClass15());
    }

    /* renamed from: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity$15, reason: invalid class name */
    class AnonymousClass15 implements CustomSelectors.OnOneSelectorsDataListener {
        AnonymousClass15() {
        }

        @Override // com.yucheng.smarthealthpro.home.view.CustomSelectors.OnOneSelectorsDataListener
        public void getSelectorsDataClick(final String oneValue, final int optionsOne) {
            YCBTClient.settingBloodRange(optionsOne, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity.15.1
                @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                public void onDataResponse(int code, float v, HashMap hashMap) {
                    if (code == 0) {
                        MeHealthSettingActivity.this.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity.15.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                MeHealthSettingActivity.this.mHandler.sendEmptyMessage(17);
                                MeHealthSettingActivity.this.tvBpLevel.setText(oneValue);
                                MeHealthSettingActivity.this.mBpLevelOptionsOne = optionsOne;
                                SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.BP_CALIBRATION_LEVEL, oneValue);
                                SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.BP_LEVEL_OPTIONS_ONE, Integer.valueOf(optionsOne));
                            }
                        });
                    } else {
                        MeHealthSettingActivity.this.mHandler.sendEmptyMessage(16);
                    }
                }
            });
        }
    }

    private void initHealthMonitoringIntervalPicker() {
        CustomSelectors customSelectors = new CustomSelectors();
        this.mHealthMonitoringIntervalCustomSelectors = customSelectors;
        customSelectors.BpLevelPicker(this.mHealthMonitoringIntervalList, null, null, this.mHealthMonitoringIntervalOptionsOne, 1, 1, " min", "", "", false, CustomSelectors.IsShow.TOP_CONFIRM_CANCEL, CustomSelectors.SelectorsDataNum.ONE, this.context);
        this.mHealthMonitoringIntervalCustomSelectors.setOnOneSelectorsDataListener(new CustomSelectors.OnOneSelectorsDataListener() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity.16
            @Override // com.yucheng.smarthealthpro.home.view.CustomSelectors.OnOneSelectorsDataListener
            public void getSelectorsDataClick(final String oneValue, final int optionsOne) {
                if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASHEARTRATE)) {
                    YCBTClient.settingHeartMonitor(1, Integer.parseInt(oneValue), new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity.16.1
                        @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                        public void onDataResponse(int code, float ratio, HashMap resultMap) {
                            if (code == 0) {
                                MeHealthSettingActivity.this.mHeartInterval = oneValue;
                                MeHealthSettingActivity.this.mHealthMonitoringIntervalOptionsOne = optionsOne;
                                MeHealthSettingActivity.this.mHandler.sendEmptyMessage(7);
                                MeHealthSettingActivity.this.mHandler.sendEmptyMessage(17);
                                return;
                            }
                            MeHealthSettingActivity.this.mHandler.sendEmptyMessage(16);
                        }
                    });
                }
                if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASTEMP)) {
                    YCBTClient.settingTemperatureMonitor(true, Integer.parseInt(oneValue), new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity.16.2
                        @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                        public void onDataResponse(int code, float ratio, HashMap resultMap) {
                            if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASHEARTRATE)) {
                                return;
                            }
                            if (code == 0) {
                                MeHealthSettingActivity.this.mHeartInterval = oneValue;
                                MeHealthSettingActivity.this.mHealthMonitoringIntervalOptionsOne = optionsOne;
                                MeHealthSettingActivity.this.mHandler.sendEmptyMessage(7);
                                MeHealthSettingActivity.this.mHandler.sendEmptyMessage(17);
                                return;
                            }
                            MeHealthSettingActivity.this.mHandler.sendEmptyMessage(16);
                        }
                    });
                }
            }
        });
    }

    private void initOxygenRiskPicker() {
        CustomSelectors customSelectors = new CustomSelectors();
        ArrayList arrayList = new ArrayList();
        for (int i2 = 70; i2 <= 95; i2++) {
            arrayList.add("" + i2);
        }
        customSelectors.BpLevelPicker(arrayList, null, null, this.mOxygenRemindOptionsOne, 1, 0, " %", "", "", false, CustomSelectors.IsShow.TOP_CONFIRM_CANCEL, CustomSelectors.SelectorsDataNum.ONE, this.context);
        customSelectors.setOnOneSelectorsDataListener(new CustomSelectors.OnOneSelectorsDataListener() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity.17
            @Override // com.yucheng.smarthealthpro.home.view.CustomSelectors.OnOneSelectorsDataListener
            public void getSelectorsDataClick(final String oneValue, final int optionsOne) {
                if (YCBTClient.connectState() != 10) {
                    Toast.makeText(MeHealthSettingActivity.this.context, MeHealthSettingActivity.this.getString(R.string.please_connect_the_device), 0).show();
                } else {
                    YCBTClient.settingBloodOxygenAlarm(1, Integer.parseInt(oneValue), new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity.17.1
                        @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                        public void onDataResponse(int code, float ratio, HashMap resultMap) {
                            if (code == 0) {
                                MeHealthSettingActivity.this.mOxygenRemind = oneValue;
                                MeHealthSettingActivity.this.mOxygenRemindOptionsOne = optionsOne;
                                MeHealthSettingActivity.this.mHandler.sendEmptyMessage(18);
                                MeHealthSettingActivity.this.mHandler.sendEmptyMessage(17);
                                return;
                            }
                            MeHealthSettingActivity.this.mHandler.sendEmptyMessage(16);
                        }
                    });
                }
            }
        });
    }

    private void initTempRiskPicker() {
        CustomSelectors customSelectors = new CustomSelectors();
        this.mTempRiskCustomSelectors = customSelectors;
        customSelectors.BpLevelPicker(this.mTempRiskValueList, null, null, this.mTempRemindOptionsOne, 1, 0, StringUtils.SPACE + this.tempUnit, "", "", false, CustomSelectors.IsShow.TOP_CONFIRM_CANCEL, CustomSelectors.SelectorsDataNum.ONE, this.context);
        this.mTempRiskCustomSelectors.setOnOneSelectorsDataListener(new CustomSelectors.OnOneSelectorsDataListener() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity.18
            @Override // com.yucheng.smarthealthpro.home.view.CustomSelectors.OnOneSelectorsDataListener
            public void getSelectorsDataClick(final String oneValue, final int optionsOne) throws NumberFormatException {
                if (YCBTClient.connectState() != 10) {
                    Toast.makeText(MeHealthSettingActivity.this.context, MeHealthSettingActivity.this.getString(R.string.please_connect_the_device), 0).show();
                    return;
                }
                int i2 = Integer.parseInt(oneValue.replaceAll(MeHealthSettingActivity.this.tempUnit, ""));
                if (Constant.SpConstValue.TEMP_INCH.equals((String) SharedPreferencesUtils.get(MeHealthSettingActivity.this.context, Constant.SpConstKey.TEMP_UNIT, Constant.SpConstValue.TEMP_ISO))) {
                    i2 = (int) ((i2 - 32) / 1.8d);
                }
                YCBTClient.settingTemperatureAlarm(true, i2, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity.18.1
                    @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                    public void onDataResponse(int code, float ratio, HashMap resultMap) {
                        if (code == 0) {
                            MeHealthSettingActivity.this.mTempRemind = oneValue;
                            MeHealthSettingActivity.this.mTempRemindOptionsOne = optionsOne;
                            MeHealthSettingActivity.this.mHandler.sendEmptyMessage(15);
                            MeHealthSettingActivity.this.mHandler.sendEmptyMessage(17);
                            return;
                        }
                        MeHealthSettingActivity.this.mHandler.sendEmptyMessage(16);
                    }
                });
            }
        });
    }

    private void initHeartRiskPicker() {
        CustomSelectors customSelectors = new CustomSelectors();
        this.mHeartRiskCustomSelectors = customSelectors;
        customSelectors.BpLevelPicker(this.mHeartRateRiskValueList, null, null, this.mHeartRemindOptionsOne, 1, 1, "", "", "", false, CustomSelectors.IsShow.TOP_CONFIRM_CANCEL, CustomSelectors.SelectorsDataNum.ONE, this.context);
        this.mHeartRiskCustomSelectors.setOnOneSelectorsDataListener(new CustomSelectors.OnOneSelectorsDataListener() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity.19
            @Override // com.yucheng.smarthealthpro.home.view.CustomSelectors.OnOneSelectorsDataListener
            public void getSelectorsDataClick(final String oneValue, final int optionsOne) {
                MeHealthSettingActivity.this.tvRiskHeartValue.setText(oneValue);
                YCBTClient.settingHeartAlarm(1, Integer.parseInt(oneValue), 0, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity.19.1
                    @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                    public void onDataResponse(int code, float ratio, HashMap resultMap) {
                        if (code == 0) {
                            MeHealthSettingActivity.this.mHeartRemind = oneValue;
                            MeHealthSettingActivity.this.mHeartRemindOptionsOne = optionsOne;
                            MeHealthSettingActivity.this.mHandler.sendEmptyMessage(9);
                            MeHealthSettingActivity.this.mHandler.sendEmptyMessage(17);
                            return;
                        }
                        MeHealthSettingActivity.this.mHandler.sendEmptyMessage(16);
                    }
                });
            }
        });
    }

    private void initSedentaryIntervalPicker() {
        CustomSelectors customSelectors = new CustomSelectors();
        this.mSedentaryIntervalCustomSelectors = customSelectors;
        customSelectors.BpLevelPicker(this.mSedentaryReminderIntervalList, null, null, (this.mSedentaryIntervalTime / 15) - 1, 1, 1, " min", "", "", false, CustomSelectors.IsShow.TOP_CONFIRM_CANCEL, CustomSelectors.SelectorsDataNum.ONE, this.context);
        this.mSedentaryIntervalCustomSelectors.setOnOneSelectorsDataListener(new CustomSelectors.OnOneSelectorsDataListener() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity.20
            @Override // com.yucheng.smarthealthpro.home.view.CustomSelectors.OnOneSelectorsDataListener
            public void getSelectorsDataClick(String oneValue, int optionsOne) {
                MeHealthSettingActivity.this.mSedentaryIntervalTime = Integer.parseInt(oneValue);
                MeHealthSettingActivity.this.setSedentary(1, oneValue);
            }
        });
    }

    private void initBedtimeRepetitionPicker() {
        CustomAlarmClockWeekSelectors customAlarmClockWeekSelectors = new CustomAlarmClockWeekSelectors();
        ArrayList<String> arrayList = this.firstList;
        ArrayList<String> arrayList2 = this.secondList;
        customAlarmClockWeekSelectors.BpLevelPicker(arrayList, arrayList2, arrayList2, 1, 1, 1, "", "", "", false, CustomAlarmClockWeekSelectors.SelectorsDataNum.THREE, true, this.mBedtimeAlternativeDate, this.context);
        customAlarmClockWeekSelectors.setOnThreeSelectorsDataListener(new CustomAlarmClockWeekSelectors.OnThreeSelectorsDataListener() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity.21
            @Override // com.yucheng.smarthealthpro.home.view.CustomAlarmClockWeekSelectors.OnThreeSelectorsDataListener
            public void getSelectorsDataClick(String oneValue, String twoValue, String threeValue, int optionsOne, int optionsTwo, int optionsThree) {
            }

            @Override // com.yucheng.smarthealthpro.home.view.CustomAlarmClockWeekSelectors.OnThreeSelectorsDataListener
            public void saveWeek(String week) {
                if (MeHealthSettingActivity.this.isSwitchSedentaryWarn) {
                    MeHealthSettingActivity.this.mBedtimeWeek = Integer.parseInt(Tools.BinaryToHex(new StringBuffer(week + "1").reverse().toString()), 16);
                } else {
                    MeHealthSettingActivity.this.mBedtimeWeek = Integer.parseInt(Tools.BinaryToHex(new StringBuffer(week + "0").reverse().toString()), 16);
                }
                SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.BEDTIME_WEEK, week);
                MeHealthSettingActivity.this.setBedtime();
                MeHealthSettingActivity.this.setBedtimeWarnReuse(week);
            }
        });
    }

    private void initSedentaryRepetitionPicker() {
        CustomAlarmClockWeekSelectors customAlarmClockWeekSelectors = new CustomAlarmClockWeekSelectors();
        ArrayList<String> arrayList = this.firstList;
        ArrayList<String> arrayList2 = this.secondList;
        customAlarmClockWeekSelectors.BpLevelPicker(arrayList, arrayList2, arrayList2, 1, 1, 1, "", "", "", false, CustomAlarmClockWeekSelectors.SelectorsDataNum.THREE, true, this.mAlternativeDate, this.context);
        customAlarmClockWeekSelectors.setOnThreeSelectorsDataListener(new CustomAlarmClockWeekSelectors.OnThreeSelectorsDataListener() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity.22
            @Override // com.yucheng.smarthealthpro.home.view.CustomAlarmClockWeekSelectors.OnThreeSelectorsDataListener
            public void getSelectorsDataClick(String oneValue, String twoValue, String threeValue, int optionsOne, int optionsTwo, int optionsThree) {
            }

            @Override // com.yucheng.smarthealthpro.home.view.CustomAlarmClockWeekSelectors.OnThreeSelectorsDataListener
            public void saveWeek(String week) {
                Log.i("AAAAAA", "===重复===" + week);
                if (MeHealthSettingActivity.this.isSwitchSedentaryWarn) {
                    MeHealthSettingActivity.this.mWeek = Integer.parseInt(Tools.BinaryToHex(new StringBuffer(week + "1").reverse().toString()), 16);
                } else {
                    MeHealthSettingActivity.this.mWeek = Integer.parseInt(Tools.BinaryToHex(new StringBuffer(week + "0").reverse().toString()), 16);
                }
                Logger.d("ltf put SEDENTARY_INTERVAL_WEEK=" + week);
                SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.SEDENTARY_INTERVAL_WEEK, week);
                MeHealthSettingActivity.this.setSedentary(2, week);
                MeHealthSettingActivity.this.setSedentaryWarnReuse(week);
            }
        });
    }

    private void initBedtimePicker() {
        CustomSelectors customSelectors = new CustomSelectors();
        customSelectors.BpLevelPicker(this.mFirstHourList, this.mSecondMinuteList, null, this.mBedtimeStartHour, this.mBedtimeStartMin, 1, ":", "", "", true, CustomSelectors.IsShow.TOP_CONFIRM_CANCEL, CustomSelectors.SelectorsDataNum.TWO, this.context);
        customSelectors.setOnTwoSelectorsDataListener(new CustomSelectors.OnTwoSelectorsDataListener() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity.23
            @Override // com.yucheng.smarthealthpro.home.view.CustomSelectors.OnTwoSelectorsDataListener
            public void getSelectorsDataClick(String oneValue, String twoValue, int optionsOne, int optionsTwo) {
                MeHealthSettingActivity.this.mBedtimeStartHour = Integer.parseInt(oneValue);
                MeHealthSettingActivity.this.mBedtimeStartMin = Integer.parseInt(twoValue);
                MeHealthSettingActivity.this.setBedtime();
            }
        });
    }

    private void initAmStartTimePicker() {
        CustomSelectors customSelectors = new CustomSelectors();
        this.mAmStartTimeCustomSelectors = customSelectors;
        customSelectors.BpLevelPicker(this.mFirstHourList, this.mSecondMinuteList, null, this.mSedentaryAmStartHour, this.mSedentaryAmStartMin, 1, ":", "", "", true, CustomSelectors.IsShow.TOP_CONFIRM_CANCEL, CustomSelectors.SelectorsDataNum.TWO, this.context);
        this.mAmStartTimeCustomSelectors.setOnTwoSelectorsDataListener(new CustomSelectors.OnTwoSelectorsDataListener() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity.24
            @Override // com.yucheng.smarthealthpro.home.view.CustomSelectors.OnTwoSelectorsDataListener
            public void getSelectorsDataClick(String oneValue, String twoValue, int optionsOne, int optionsTwo) {
                MeHealthSettingActivity.this.mSedentaryAmStartHour = Integer.parseInt(oneValue);
                MeHealthSettingActivity.this.mSedentaryAmStartMin = Integer.parseInt(twoValue);
                MeHealthSettingActivity.this.setSedentary(3, null);
            }
        });
    }

    private void initAmEndTimePicker() {
        CustomSelectors customSelectors = new CustomSelectors();
        this.mAmEndTimeCustomSelectors = customSelectors;
        customSelectors.BpLevelPicker(this.mFirstHourList, this.mSecondMinuteList, null, this.mSedentaryAmEndHour, this.mSedentaryAmEndMin, 1, ":", "", "", true, CustomSelectors.IsShow.TOP_CONFIRM_CANCEL, CustomSelectors.SelectorsDataNum.TWO, this.context);
        this.mAmEndTimeCustomSelectors.setOnTwoSelectorsDataListener(new CustomSelectors.OnTwoSelectorsDataListener() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity.25
            @Override // com.yucheng.smarthealthpro.home.view.CustomSelectors.OnTwoSelectorsDataListener
            public void getSelectorsDataClick(String oneValue, String twoValue, int optionsOne, int optionsTwo) {
                MeHealthSettingActivity.this.mSedentaryAmEndHour = Integer.parseInt(oneValue);
                MeHealthSettingActivity.this.mSedentaryAmEndMin = Integer.parseInt(twoValue);
                MeHealthSettingActivity.this.setSedentary(4, null);
            }
        });
    }

    private void initPmStartTimePicker() {
        CustomSelectors customSelectors = new CustomSelectors();
        this.mPmStartTimeCustomSelectors = customSelectors;
        customSelectors.BpLevelPicker(this.mFirstHourList, this.mSecondMinuteList, null, this.mSedentaryPmStartHour, this.mSedentaryPmStartMin, 1, ":", "", "", true, CustomSelectors.IsShow.TOP_CONFIRM_CANCEL, CustomSelectors.SelectorsDataNum.TWO, this.context);
        this.mPmStartTimeCustomSelectors.setOnTwoSelectorsDataListener(new CustomSelectors.OnTwoSelectorsDataListener() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity.26
            @Override // com.yucheng.smarthealthpro.home.view.CustomSelectors.OnTwoSelectorsDataListener
            public void getSelectorsDataClick(String oneValue, String twoValue, int optionsOne, int optionsTwo) {
                MeHealthSettingActivity.this.mSedentaryPmStartHour = Integer.parseInt(oneValue);
                MeHealthSettingActivity.this.mSedentaryPmStartMin = Integer.parseInt(twoValue);
                MeHealthSettingActivity.this.setSedentary(5, null);
            }
        });
    }

    private void initPmEndTimePicker() {
        CustomSelectors customSelectors = new CustomSelectors();
        this.mPmEndTimeCustomSelectors = customSelectors;
        customSelectors.BpLevelPicker(this.mFirstHourList, this.mSecondMinuteList, null, this.mSedentaryPmEndHour, this.mSedentaryPmEndMin, 1, ":", "", "", true, CustomSelectors.IsShow.TOP_CONFIRM_CANCEL, CustomSelectors.SelectorsDataNum.TWO, this.context);
        this.mPmEndTimeCustomSelectors.setOnTwoSelectorsDataListener(new CustomSelectors.OnTwoSelectorsDataListener() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity.27
            @Override // com.yucheng.smarthealthpro.home.view.CustomSelectors.OnTwoSelectorsDataListener
            public void getSelectorsDataClick(String oneValue, String twoValue, int optionsOne, int optionsTwo) {
                MeHealthSettingActivity.this.mSedentaryPmEndHour = Integer.parseInt(oneValue);
                MeHealthSettingActivity.this.mSedentaryPmEndMin = Integer.parseInt(twoValue);
                MeHealthSettingActivity.this.setSedentary(6, null);
            }
        });
    }

    public void onViewClicked(View view) {
        if (this.isCanClick) {
            this.isCanClick = false;
            this.mHandler.postDelayed(new Runnable() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity.28
                @Override // java.lang.Runnable
                public void run() {
                    MeHealthSettingActivity.this.isCanClick = true;
                }
            }, 200L);
            if (view.getId() == R.id.rl_blood_fat_calibration) {
                showBloodFatSetting();
                return;
            }
            if (view.getId() == R.id.rl_uric_acid_calibration) {
                showUrciAcidSetting();
                return;
            }
            if (view.getId() == R.id.rl_moving_object) {
                initMovingObjectPicker();
                return;
            }
            if (view.getId() == R.id.rl_sleep_quality) {
                initSleepObjectPicker();
                return;
            }
            if (view.getId() == R.id.rl_bloodSugar_calibration) {
                if ("".equals(YCBTClient.getBloodSugarVersion())) {
                    initBloodSugarCalibrationlPicker();
                    return;
                } else {
                    initNewBloodSugarCalibrationlPicker();
                    return;
                }
            }
            if (view.getId() == R.id.rl_bp_calibration) {
                initBpCalibrationlPicker();
                return;
            }
            if (view.getId() == R.id.rl_bp_grade) {
                initBpLevelPicker();
                return;
            }
            if (view.getId() == R.id.rl_health_monitoring_interval) {
                initHealthMonitoringIntervalPicker();
                return;
            }
            if (view.getId() == R.id.rl_risk_temp_value) {
                initTempRiskPicker();
                return;
            }
            if (view.getId() == R.id.rl_risk_oxygen_value) {
                initOxygenRiskPicker();
                return;
            }
            if (view.getId() == R.id.rl_risk_heart_value) {
                initHeartRiskPicker();
                return;
            }
            if (view.getId() == R.id.rl_risk_bedtime_value) {
                initBedtimePicker();
                return;
            }
            if (view.getId() == R.id.rl_bedtime_warn_reuse) {
                initBedtimeRepetitionPicker();
                return;
            }
            if (view.getId() == R.id.rl_sedentary_warn_interval) {
                initSedentaryIntervalPicker();
                return;
            }
            if (view.getId() == R.id.rl_sedentary_warn_reuse) {
                initSedentaryRepetitionPicker();
                return;
            }
            if (view.getId() == R.id.rl_sedentary_warn_am_start_time) {
                initAmStartTimePicker();
                return;
            }
            if (view.getId() == R.id.rl_sedentary_warn_am_end_time) {
                initAmEndTimePicker();
                return;
            }
            if (view.getId() == R.id.rl_sedentary_warn_pm_start_time) {
                initPmStartTimePicker();
                return;
            }
            if (view.getId() == R.id.rl_sedentary_warn_pm_end_time) {
                initPmEndTimePicker();
                return;
            }
            if (view.getId() == R.id.rl_drink_water_reminder_repeat) {
                String string = new StringBuffer(Integer.toBinaryString(this.mDrinkWaterWeek)).reverse().toString();
                while (string.length() < 7) {
                    string = string + "0";
                }
                initDrinkWaterRepetitionPicker(string);
                return;
            }
            if (view.getId() == R.id.rl_drink_water_reminder_interval) {
                initDrinkWaterIntervalPicker(this.mDrinkWaterIntervalList, this.mDrinkWaterIntervalTime);
            } else if (view.getId() == R.id.rl_drink_water_reminder_start_time) {
                initDrinkWaterStartTimePicker();
            } else if (view.getId() == R.id.rl_drink_water_reminder_end_time) {
                initDrinkWaterEndTimePicker();
            }
        }
    }

    private void showBloodFatSetting() {
        getWindow().setSoftInputMode(32);
        CustomSelectors customSelectors = new CustomSelectors();
        this.mBloodFatSelectors = customSelectors;
        customSelectors.setOnInputDataListener(new AnonymousClass29());
        this.mBloodFatSelectors.showBloodFatPicker(this);
    }

    /* renamed from: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity$29, reason: invalid class name */
    class AnonymousClass29 implements CustomSelectors.OnInputDataListener {
        AnonymousClass29() {
        }

        @Override // com.yucheng.smarthealthpro.home.view.CustomSelectors.OnInputDataListener
        public void getDataClick(String value) throws NumberFormatException {
            try {
                float f2 = Float.parseFloat(value);
                final String str = (String) SharedPreferencesUtils.get(MeHealthSettingActivity.this.context, Constant.SpConstKey.BLOOD_SUGAR_AND_BLOOD_FAT_UNIT, Constant.SpConstValue.BLOOD_SUGAR_AND_BLOOD_FAT_MMOL);
                if ("mg/dL".equals(str)) {
                    if (f2 >= TransUtils.BLOOD_FAT_MIN_MG && f2 <= TransUtils.BLOOD_FAT_MAX_MG) {
                        final float fFloatValue = FormatUtil.getBigDecimal(f2).setScale(2, RoundingMode.HALF_UP).floatValue();
                        final float fFloatValue2 = FormatUtil.getBigDecimal(f2 * TransUtils.BLOOD_FAT_TRANS2).setScale(2, 4).floatValue();
                        int i2 = (int) (100.0f * fFloatValue2);
                        YCBTClient.appLipidCalibration(0, i2 / 100, i2 % 100, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity.29.1
                            @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                            public void onDataResponse(int code, float v, HashMap hashMap) {
                                if (code == 0) {
                                    MeHealthSettingActivity.this.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity.29.1.1
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            MeHealthSettingActivity.this.mHandler.sendEmptyMessage(17);
                                            MeHealthSettingActivity.this.tv_blood_fat_calibration.setText(FormatUtil.keep2(fFloatValue) + str);
                                            SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.BLOOD_FAT_CALIBRATION_MG_VALUE, Float.valueOf(fFloatValue));
                                            SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.BLOOD_FAT_CALIBRATION_VALUE, Float.valueOf(fFloatValue2));
                                        }
                                    });
                                } else {
                                    MeHealthSettingActivity.this.mHandler.sendEmptyMessage(16);
                                }
                            }
                        });
                    } else {
                        ToastUtil.getInstance(MeHealthSettingActivity.this.getApplicationContext()).toast(MeHealthSettingActivity.this.getString(R.string.clock_modify_failed_param_error));
                    }
                } else if (f2 >= TransUtils.BLOOD_FAT_MIN && f2 <= TransUtils.BLOOD_FAT_MAX) {
                    final float fFloatValue3 = FormatUtil.getBigDecimal(f2).setScale(2, 4).floatValue();
                    final float fFloatValue4 = FormatUtil.getBigDecimal(f2 * TransUtils.BLOOD_FAT_TRANS).setScale(2, 4).floatValue();
                    int i3 = (int) (100.0f * fFloatValue3);
                    YCBTClient.appLipidCalibration(0, i3 / 100, i3 % 100, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity.29.2
                        @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                        public void onDataResponse(int code, float v, HashMap hashMap) {
                            if (code == 0) {
                                MeHealthSettingActivity.this.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity.29.2.1
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        MeHealthSettingActivity.this.mHandler.sendEmptyMessage(17);
                                        MeHealthSettingActivity.this.tv_blood_fat_calibration.setText(FormatUtil.keep2(fFloatValue3) + str);
                                        SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.BLOOD_FAT_CALIBRATION_MG_VALUE, Float.valueOf(fFloatValue4));
                                        SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.BLOOD_FAT_CALIBRATION_VALUE, Float.valueOf(fFloatValue3));
                                    }
                                });
                            } else {
                                MeHealthSettingActivity.this.mHandler.sendEmptyMessage(16);
                            }
                        }
                    });
                } else {
                    ToastUtil.getInstance(MeHealthSettingActivity.this.getApplicationContext()).toast(MeHealthSettingActivity.this.getString(R.string.clock_modify_failed_param_error));
                }
            } catch (Exception e2) {
                CrashReport.postCatchedException(e2);
                e2.printStackTrace();
            }
        }
    }

    private void showUrciAcidSetting() {
        getWindow().setSoftInputMode(32);
        CustomSelectors customSelectors = new CustomSelectors();
        this.mUricAcidSelectors = customSelectors;
        customSelectors.setOnInputDataListener(new AnonymousClass30());
        this.mUricAcidSelectors.showUricAcidPicker(this);
    }

    /* renamed from: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity$30, reason: invalid class name */
    class AnonymousClass30 implements CustomSelectors.OnInputDataListener {
        AnonymousClass30() {
        }

        @Override // com.yucheng.smarthealthpro.home.view.CustomSelectors.OnInputDataListener
        public void getDataClick(String value) throws NumberFormatException {
            try {
                float f2 = Float.parseFloat(value);
                final String str = (String) SharedPreferencesUtils.get(MeHealthSettingActivity.this.context, Constant.SpConstKey.URIC_ACID_UNIT, Constant.SpConstValue.URIC_ACID_UMOL);
                if ("mg/dL".equals(str)) {
                    if (f2 >= 1.5f && f2 <= 16.8f) {
                        final float fFloatValue = FormatUtil.getBigDecimal(f2).setScale(1, 4).floatValue();
                        float f3 = f2 * TransUtils.URIC_ACID_TRANS2;
                        final float fFloatValue2 = FormatUtil.getBigDecimal(f3).setScale(0, 4).floatValue();
                        YCBTClient.appUricAcidCalibration(0, (int) f3, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity.30.1
                            @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                            public void onDataResponse(int code, float v, HashMap hashMap) {
                                if (code == 0) {
                                    MeHealthSettingActivity.this.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity.30.1.1
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            MeHealthSettingActivity.this.mHandler.sendEmptyMessage(17);
                                            MeHealthSettingActivity.this.tv_uric_acid_calibration.setText(fFloatValue + str);
                                            SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.URIC_ACID_CALIBRATION_MG_VALUE, Float.valueOf(fFloatValue));
                                            SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.URIC_ACID_CALIBRATION_VALUE, Float.valueOf(fFloatValue2));
                                        }
                                    });
                                } else {
                                    MeHealthSettingActivity.this.mHandler.sendEmptyMessage(16);
                                }
                            }
                        });
                    } else {
                        ToastUtil.getInstance(MeHealthSettingActivity.this.getApplicationContext()).toast(MeHealthSettingActivity.this.getString(R.string.clock_modify_failed_param_error));
                    }
                } else if (f2 >= TransUtils.URIC_ACID_MIN && f2 <= TransUtils.URIC_ACID_MAX) {
                    final float fFloatValue3 = FormatUtil.getBigDecimal(f2).setScale(0, 4).floatValue();
                    final float fFloatValue4 = FormatUtil.getBigDecimal(f2 * TransUtils.URIC_ACID_TRANS).setScale(1, 4).floatValue();
                    YCBTClient.appUricAcidCalibration(0, (int) fFloatValue3, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity.30.2
                        @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                        public void onDataResponse(int code, float v, HashMap hashMap) {
                            if (code == 0) {
                                MeHealthSettingActivity.this.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity.30.2.1
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        MeHealthSettingActivity.this.mHandler.sendEmptyMessage(17);
                                        MeHealthSettingActivity.this.tv_uric_acid_calibration.setText(((int) fFloatValue3) + str);
                                        SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.URIC_ACID_CALIBRATION_MG_VALUE, Float.valueOf(fFloatValue4));
                                        SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.URIC_ACID_CALIBRATION_VALUE, Float.valueOf(fFloatValue3));
                                    }
                                });
                            } else {
                                MeHealthSettingActivity.this.mHandler.sendEmptyMessage(16);
                            }
                        }
                    });
                } else {
                    ToastUtil.getInstance(MeHealthSettingActivity.this.getApplicationContext()).toast(MeHealthSettingActivity.this.getString(R.string.clock_modify_failed_param_error));
                }
            } catch (Exception e2) {
                CrashReport.postCatchedException(e2);
                e2.printStackTrace();
            }
        }
    }

    private void setLongSitReminder() {
        String str = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.SEDENTARY_REMINDER_SWITCH, "");
        if (str != null && str.equals(Constant.SpConstValue.OPEN)) {
            this.llSedentaryWarn.setVisibility(0);
            this.mSwitchSedentaryWarn.setChecked(true);
            this.isSwitchSedentaryWarn = true;
        } else {
            this.llSedentaryWarn.setVisibility(8);
            this.mSwitchSedentaryWarn.setChecked(false);
            this.isSwitchSedentaryWarn = false;
        }
        this.mSwitchSedentaryWarn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity.31
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    MeHealthSettingActivity.this.mWeek = Integer.parseInt(Tools.BinaryToHex(new StringBuffer(MeHealthSettingActivity.this.mAlternativeDate + "1").reverse().toString()), 16);
                    MeHealthSettingActivity.this.llSedentaryWarn.setVisibility(0);
                    MeHealthSettingActivity.this.isSwitchSedentaryWarn = true;
                    MeHealthSettingActivity.this.setSedentary(0, Constant.SpConstValue.OPEN);
                    return;
                }
                MeHealthSettingActivity.this.mWeek = Integer.parseInt(Tools.BinaryToHex(new StringBuffer(MeHealthSettingActivity.this.mAlternativeDate + "0").reverse().toString()), 16);
                MeHealthSettingActivity.this.llSedentaryWarn.setVisibility(8);
                MeHealthSettingActivity.this.isSwitchSedentaryWarn = false;
                SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.SEDENTARY_REMINDER_SWITCH, Constant.SpConstValue.CLOSE);
                MeHealthSettingActivity.this.setSedentary(0, Constant.SpConstValue.CLOSE);
            }
        });
    }

    private void initBedtimeReminderPicker() {
        CustomSelectors customSelectors = new CustomSelectors();
        customSelectors.BpLevelPicker(this.mFirstHourList, this.mSecondMinuteList, null, this.mDrinkWaterStartHour, this.mDrinkWaterStartMin, 1, ":", "", "", true, CustomSelectors.IsShow.TOP_CONFIRM_CANCEL, CustomSelectors.SelectorsDataNum.TWO, this.context);
        customSelectors.setOnTwoSelectorsDataListener(new CustomSelectors.OnTwoSelectorsDataListener() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity.32
            @Override // com.yucheng.smarthealthpro.home.view.CustomSelectors.OnTwoSelectorsDataListener
            public void getSelectorsDataClick(String oneValue, String twoValue, int optionsOne, int optionsTwo) throws UnsupportedEncodingException {
                MeHealthSettingActivity.this.mDrinkWaterStartHour = optionsOne;
                MeHealthSettingActivity.this.mDrinkWaterStartMin = optionsTwo;
                MeHealthSettingActivity.this.setDrinkWater(3);
            }
        });
    }

    private void setDrinkWaterReminder() {
        this.mDrinkWaterWeek = ((Integer) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.DRINK_WATER_WEEK, 0)).intValue();
        this.mDrinkWaterIntervalTime = ((Integer) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.DRINK_WATER_INTERVAL, 10)).intValue();
        this.mDrinkWaterStartHour = ((Integer) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.DRINK_WATER_START_HOUR, 0)).intValue();
        this.mDrinkWaterStartMin = ((Integer) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.DRINK_WATER_START_MIN, 0)).intValue();
        this.mDrinkWaterEndHour = ((Integer) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.DRINK_WATER_END_HOUR, 23)).intValue();
        this.mDrinkWaterEndMin = ((Integer) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.DRINK_WATER_END_MIN, 59)).intValue();
        this.isSwitchDrinkWater = ((Boolean) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.DRINK_WATER_REMINDER_SWITCH, false)).booleanValue();
        this.tvDrinkWaterInterval.setText(this.mDrinkWaterIntervalTime == 0 ? getString(R.string.setting_health_interval_single) : this.mDrinkWaterIntervalTime + StringUtils.SPACE + getString(R.string.time_mins_unit));
        this.tvDrinkWaterRepeat.setText(WeekUtil.getLable(this.mDrinkWaterWeek, this));
        TextView textView = this.tvDrinkWaterStartTime;
        StringBuilder sb = new StringBuilder();
        int i2 = this.mDrinkWaterStartHour;
        StringBuilder sbAppend = sb.append(i2 < 10 ? "0" + this.mDrinkWaterStartHour : Integer.valueOf(i2)).append(":");
        int i3 = this.mDrinkWaterStartMin;
        textView.setText(sbAppend.append(i3 < 10 ? "0" + this.mDrinkWaterStartMin : Integer.valueOf(i3)).toString());
        TextView textView2 = this.tvDrinkWaterEndTime;
        StringBuilder sb2 = new StringBuilder();
        int i4 = this.mDrinkWaterEndHour;
        StringBuilder sbAppend2 = sb2.append(i4 < 10 ? "0" + this.mDrinkWaterEndHour : Integer.valueOf(i4)).append(":");
        int i5 = this.mDrinkWaterEndMin;
        textView2.setText(sbAppend2.append(i5 < 10 ? "0" + this.mDrinkWaterEndMin : Integer.valueOf(i5)).toString());
        this.mSwitchDrinkWater.setChecked(this.isSwitchDrinkWater);
        if (this.isSwitchDrinkWater) {
            this.llDrinkWaterReminderContent.setVisibility(0);
        } else {
            this.llDrinkWaterReminderContent.setVisibility(8);
        }
        for (int i6 = 0; i6 <= 60; i6++) {
            if (i6 == 0) {
                this.mDrinkWaterIntervalList.add(this.context.getString(R.string.setting_health_interval_single));
            } else {
                this.mDrinkWaterIntervalList.add(i6 + "");
            }
        }
        this.mSwitchDrinkWater.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity.33
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) throws UnsupportedEncodingException {
                if (isChecked) {
                    MeHealthSettingActivity.this.llDrinkWaterReminderContent.setVisibility(0);
                } else {
                    MeHealthSettingActivity.this.llDrinkWaterReminderContent.setVisibility(8);
                }
                MeHealthSettingActivity.this.isSwitchDrinkWater = isChecked;
                MeHealthSettingActivity meHealthSettingActivity = MeHealthSettingActivity.this;
                meHealthSettingActivity.mDrinkWaterWeek = isChecked ? meHealthSettingActivity.mDrinkWaterWeek | 128 : meHealthSettingActivity.mDrinkWaterWeek & 127;
                MeHealthSettingActivity.this.setDrinkWater(0);
            }
        });
    }

    private void initDrinkWaterRepetitionPicker(String week) {
        CustomAlarmClockWeekSelectors customAlarmClockWeekSelectors = new CustomAlarmClockWeekSelectors();
        ArrayList<String> arrayList = this.firstList;
        ArrayList<String> arrayList2 = this.secondList;
        customAlarmClockWeekSelectors.BpLevelPicker(arrayList, arrayList2, arrayList2, 1, 1, 1, "", "", "", false, CustomAlarmClockWeekSelectors.SelectorsDataNum.THREE, true, week, this.context);
        customAlarmClockWeekSelectors.setOnThreeSelectorsDataListener(new CustomAlarmClockWeekSelectors.OnThreeSelectorsDataListener() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity.34
            @Override // com.yucheng.smarthealthpro.home.view.CustomAlarmClockWeekSelectors.OnThreeSelectorsDataListener
            public void getSelectorsDataClick(String oneValue, String twoValue, String threeValue, int optionsOne, int optionsTwo, int optionsThree) {
            }

            @Override // com.yucheng.smarthealthpro.home.view.CustomAlarmClockWeekSelectors.OnThreeSelectorsDataListener
            public void saveWeek(String week2) throws UnsupportedEncodingException {
                StringBuilder sbAppend;
                String str;
                if (MeHealthSettingActivity.this.isSwitchDrinkWater) {
                    sbAppend = new StringBuilder().append(week2);
                    str = "1";
                } else {
                    sbAppend = new StringBuilder().append(week2);
                    str = "0";
                }
                String string = sbAppend.append(str).toString();
                MeHealthSettingActivity.this.mDrinkWaterWeek = Integer.parseInt(Tools.BinaryToHex(new StringBuffer(string).reverse().toString()), 16);
                MeHealthSettingActivity.this.setDrinkWater(2);
            }
        });
    }

    private void initDrinkWaterIntervalPicker(ArrayList<String> lists, int index) {
        CustomSelectors customSelectors = new CustomSelectors();
        customSelectors.BpLevelPicker(lists, null, null, index, 1, 1, " min", "", "", false, CustomSelectors.IsShow.TOP_CONFIRM_CANCEL, CustomSelectors.SelectorsDataNum.ONE, this.context);
        customSelectors.setOnOneSelectorsDataListener(new CustomSelectors.OnOneSelectorsDataListener() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity.35
            @Override // com.yucheng.smarthealthpro.home.view.CustomSelectors.OnOneSelectorsDataListener
            public void getSelectorsDataClick(String oneValue, int optionsOne) throws UnsupportedEncodingException {
                MeHealthSettingActivity.this.mDrinkWaterIntervalTime = optionsOne;
                MeHealthSettingActivity.this.setDrinkWater(1);
            }
        });
    }

    private void initDrinkWaterStartTimePicker() {
        CustomSelectors customSelectors = new CustomSelectors();
        customSelectors.BpLevelPicker(this.mFirstHourList, this.mSecondMinuteList, null, this.mDrinkWaterStartHour, this.mDrinkWaterStartMin, 1, ":", "", "", true, CustomSelectors.IsShow.TOP_CONFIRM_CANCEL, CustomSelectors.SelectorsDataNum.TWO, this.context);
        customSelectors.setOnTwoSelectorsDataListener(new CustomSelectors.OnTwoSelectorsDataListener() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity.36
            @Override // com.yucheng.smarthealthpro.home.view.CustomSelectors.OnTwoSelectorsDataListener
            public void getSelectorsDataClick(String oneValue, String twoValue, int optionsOne, int optionsTwo) throws UnsupportedEncodingException {
                MeHealthSettingActivity.this.mDrinkWaterStartHour = optionsOne;
                MeHealthSettingActivity.this.mDrinkWaterStartMin = optionsTwo;
                MeHealthSettingActivity.this.setDrinkWater(3);
            }
        });
    }

    private void initDrinkWaterEndTimePicker() {
        CustomSelectors customSelectors = new CustomSelectors();
        customSelectors.BpLevelPicker(this.mFirstHourList, this.mSecondMinuteList, null, this.mDrinkWaterEndHour, this.mDrinkWaterEndMin, 1, ":", "", "", true, CustomSelectors.IsShow.TOP_CONFIRM_CANCEL, CustomSelectors.SelectorsDataNum.TWO, this.context);
        customSelectors.setOnTwoSelectorsDataListener(new CustomSelectors.OnTwoSelectorsDataListener() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity.37
            @Override // com.yucheng.smarthealthpro.home.view.CustomSelectors.OnTwoSelectorsDataListener
            public void getSelectorsDataClick(String oneValue, String twoValue, int optionsOne, int optionsTwo) throws UnsupportedEncodingException {
                MeHealthSettingActivity.this.mDrinkWaterEndHour = optionsOne;
                MeHealthSettingActivity.this.mDrinkWaterEndMin = optionsTwo;
                MeHealthSettingActivity.this.setDrinkWater(4);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDrinkWater(final int type) throws UnsupportedEncodingException {
        int i2 = this.mDrinkWaterStartHour;
        int i3 = this.mDrinkWaterStartMin;
        int i4 = (i2 * 60) + i3;
        int i5 = this.mDrinkWaterEndHour;
        int i6 = this.mDrinkWaterEndMin;
        if ((i5 * 60) + i6 <= i4) {
            ToastUtil.getInstance(getApplicationContext()).toast(getString(R.string.health_set_failed));
        } else {
            YCBTClient.settingRegularReminder(0, i2, i3, i5, i6, this.mDrinkWaterWeek, this.mDrinkWaterIntervalTime, null, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity.38
                @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                public void onDataResponse(int code, float v, HashMap hashMap) {
                    if (code == 0) {
                        MeHealthSettingActivity.this.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity.38.1
                            @Override // java.lang.Runnable
                            public void run() {
                                MeHealthSettingActivity.this.mHandler.sendEmptyMessage(17);
                                int i7 = type;
                                if (i7 == 0) {
                                    SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.DRINK_WATER_REMINDER_SWITCH, Boolean.valueOf(MeHealthSettingActivity.this.isSwitchDrinkWater));
                                    SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.DRINK_WATER_WEEK, Integer.valueOf(MeHealthSettingActivity.this.mDrinkWaterWeek));
                                    return;
                                }
                                if (i7 == 1) {
                                    SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.DRINK_WATER_INTERVAL, Integer.valueOf(MeHealthSettingActivity.this.mDrinkWaterIntervalTime));
                                    MeHealthSettingActivity.this.tvDrinkWaterInterval.setText(MeHealthSettingActivity.this.mDrinkWaterIntervalTime == 0 ? MeHealthSettingActivity.this.getString(R.string.setting_health_interval_single) : MeHealthSettingActivity.this.mDrinkWaterIntervalTime + StringUtils.SPACE + MeHealthSettingActivity.this.getString(R.string.time_mins_unit));
                                    return;
                                }
                                if (i7 == 2) {
                                    SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.DRINK_WATER_WEEK, Integer.valueOf(MeHealthSettingActivity.this.mDrinkWaterWeek));
                                    MeHealthSettingActivity.this.tvDrinkWaterRepeat.setText(WeekUtil.getLable(MeHealthSettingActivity.this.mDrinkWaterWeek, MeHealthSettingActivity.this));
                                } else if (i7 == 3) {
                                    SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.DRINK_WATER_START_HOUR, Integer.valueOf(MeHealthSettingActivity.this.mDrinkWaterStartHour));
                                    SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.DRINK_WATER_START_MIN, Integer.valueOf(MeHealthSettingActivity.this.mDrinkWaterStartMin));
                                    MeHealthSettingActivity.this.tvDrinkWaterStartTime.setText((MeHealthSettingActivity.this.mDrinkWaterStartHour < 10 ? "0" + MeHealthSettingActivity.this.mDrinkWaterStartHour : Integer.valueOf(MeHealthSettingActivity.this.mDrinkWaterStartHour)) + ":" + (MeHealthSettingActivity.this.mDrinkWaterStartMin < 10 ? "0" + MeHealthSettingActivity.this.mDrinkWaterStartMin : Integer.valueOf(MeHealthSettingActivity.this.mDrinkWaterStartMin)));
                                } else {
                                    if (i7 != 4) {
                                        return;
                                    }
                                    SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.DRINK_WATER_END_HOUR, Integer.valueOf(MeHealthSettingActivity.this.mDrinkWaterEndHour));
                                    SharedPreferencesUtils.put(MeHealthSettingActivity.this.context, Constant.SpConstKey.DRINK_WATER_END_MIN, Integer.valueOf(MeHealthSettingActivity.this.mDrinkWaterEndMin));
                                    MeHealthSettingActivity.this.tvDrinkWaterEndTime.setText((MeHealthSettingActivity.this.mDrinkWaterEndHour < 10 ? "0" + MeHealthSettingActivity.this.mDrinkWaterEndHour : Integer.valueOf(MeHealthSettingActivity.this.mDrinkWaterEndHour)) + ":" + (MeHealthSettingActivity.this.mDrinkWaterEndMin < 10 ? "0" + MeHealthSettingActivity.this.mDrinkWaterEndMin : Integer.valueOf(MeHealthSettingActivity.this.mDrinkWaterEndMin)));
                                }
                            }
                        });
                    } else {
                        MeHealthSettingActivity.this.mHandler.sendEmptyMessage(16);
                    }
                }
            });
        }
    }

    @Override // com.yucheng.smarthealthpro.framework.BaseActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
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
    }
}
