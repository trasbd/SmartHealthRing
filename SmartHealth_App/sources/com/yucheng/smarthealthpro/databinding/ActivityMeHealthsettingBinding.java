package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;

/* loaded from: classes4.dex */
public final class ActivityMeHealthsettingBinding implements ViewBinding {
    public final ImageView ivBpCalibration;
    public final ImageView ivBpLevel;
    public final LinearLayout llDrinkWaterReminder;
    public final LinearLayout llDrinkWaterReminderContent;
    public final LinearLayout llHealthSetMonitoring;
    public final LinearLayout llLongSit;
    public final LinearLayout llRiskBedtimeValue;
    public final LinearLayout llRiskHeartValue;
    public final LinearLayout llRiskOxygenValue;
    public final LinearLayout llRiskTempValue;
    public final LinearLayout llSedentaryWarn;
    public final LinearLayout llTargetObject;
    public final NavigationBar navigationbar;
    public final RelativeLayout rlBedtimeWarnReuse;
    public final RelativeLayout rlBloodFatCalibration;
    public final RelativeLayout rlBloodSugarCalibration;
    public final RelativeLayout rlBpCalibration;
    public final RelativeLayout rlBpGrade;
    public final RelativeLayout rlDrinkWaterReminderEndTime;
    public final RelativeLayout rlDrinkWaterReminderInterval;
    public final RelativeLayout rlDrinkWaterReminderRepeat;
    public final RelativeLayout rlDrinkWaterReminderStartTime;
    public final RelativeLayout rlHealthMonitoringInterval;
    public final RelativeLayout rlMovingObject;
    public final RelativeLayout rlRiskBedtimeValue;
    public final RelativeLayout rlRiskHeartValue;
    public final RelativeLayout rlRiskOxygenValue;
    public final RelativeLayout rlRiskTempValue;
    public final RelativeLayout rlSedentaryWarnAmEndTime;
    public final RelativeLayout rlSedentaryWarnAmStartTime;
    public final RelativeLayout rlSedentaryWarnInterval;
    public final RelativeLayout rlSedentaryWarnPmEndTime;
    public final RelativeLayout rlSedentaryWarnPmStartTime;
    public final RelativeLayout rlSedentaryWarnReuse;
    public final RelativeLayout rlSleepQuality;
    public final RelativeLayout rlUricAcidCalibration;
    private final LinearLayout rootView;
    public final Switch switchDrinkWater;
    public final Switch switchHealthMonitoring;
    public final Switch switchRiskBedtimeWarn;
    public final Switch switchRiskHeartWarn;
    public final Switch switchRiskOxygenWarn;
    public final Switch switchRiskTempWarn;
    public final Switch switchSedentaryWarn;
    public final View targetViewLine;
    public final TextView tvBedtimeWarnReuse;
    public final TextView tvBedtimeWarnReuseTitle;
    public final TextView tvBloodFatCalibration;
    public final TextView tvBloodSugarCalibration;
    public final TextView tvBpCalibration;
    public final TextView tvBpCalibrationTitle;
    public final TextView tvBpLevel;
    public final TextView tvDrinkWaterReminderEndTime;
    public final TextView tvDrinkWaterReminderInterval;
    public final TextView tvDrinkWaterReminderRepeat;
    public final TextView tvDrinkWaterReminderRepeatTitle;
    public final TextView tvDrinkWaterReminderStartTime;
    public final TextView tvHealthMonitoringIntervalValue;
    public final TextView tvMovingObject;
    public final TextView tvRiskBedtimeValue;
    public final TextView tvRiskHeartValue;
    public final TextView tvRiskOxygenValue;
    public final TextView tvRiskTempValue;
    public final TextView tvSedentaryWarnAmEndTime;
    public final TextView tvSedentaryWarnAmStartTime;
    public final TextView tvSedentaryWarnInterval;
    public final TextView tvSedentaryWarnPmEndTime;
    public final TextView tvSedentaryWarnPmStartTime;
    public final TextView tvSedentaryWarnReuse;
    public final TextView tvSedentaryWarnReuseTitle;
    public final TextView tvSleepQuality;
    public final TextView tvUricAcidCalibration;

    private ActivityMeHealthsettingBinding(LinearLayout rootView, ImageView ivBpCalibration, ImageView ivBpLevel, LinearLayout llDrinkWaterReminder, LinearLayout llDrinkWaterReminderContent, LinearLayout llHealthSetMonitoring, LinearLayout llLongSit, LinearLayout llRiskBedtimeValue, LinearLayout llRiskHeartValue, LinearLayout llRiskOxygenValue, LinearLayout llRiskTempValue, LinearLayout llSedentaryWarn, LinearLayout llTargetObject, NavigationBar navigationbar, RelativeLayout rlBedtimeWarnReuse, RelativeLayout rlBloodFatCalibration, RelativeLayout rlBloodSugarCalibration, RelativeLayout rlBpCalibration, RelativeLayout rlBpGrade, RelativeLayout rlDrinkWaterReminderEndTime, RelativeLayout rlDrinkWaterReminderInterval, RelativeLayout rlDrinkWaterReminderRepeat, RelativeLayout rlDrinkWaterReminderStartTime, RelativeLayout rlHealthMonitoringInterval, RelativeLayout rlMovingObject, RelativeLayout rlRiskBedtimeValue, RelativeLayout rlRiskHeartValue, RelativeLayout rlRiskOxygenValue, RelativeLayout rlRiskTempValue, RelativeLayout rlSedentaryWarnAmEndTime, RelativeLayout rlSedentaryWarnAmStartTime, RelativeLayout rlSedentaryWarnInterval, RelativeLayout rlSedentaryWarnPmEndTime, RelativeLayout rlSedentaryWarnPmStartTime, RelativeLayout rlSedentaryWarnReuse, RelativeLayout rlSleepQuality, RelativeLayout rlUricAcidCalibration, Switch switchDrinkWater, Switch switchHealthMonitoring, Switch switchRiskBedtimeWarn, Switch switchRiskHeartWarn, Switch switchRiskOxygenWarn, Switch switchRiskTempWarn, Switch switchSedentaryWarn, View targetViewLine, TextView tvBedtimeWarnReuse, TextView tvBedtimeWarnReuseTitle, TextView tvBloodFatCalibration, TextView tvBloodSugarCalibration, TextView tvBpCalibration, TextView tvBpCalibrationTitle, TextView tvBpLevel, TextView tvDrinkWaterReminderEndTime, TextView tvDrinkWaterReminderInterval, TextView tvDrinkWaterReminderRepeat, TextView tvDrinkWaterReminderRepeatTitle, TextView tvDrinkWaterReminderStartTime, TextView tvHealthMonitoringIntervalValue, TextView tvMovingObject, TextView tvRiskBedtimeValue, TextView tvRiskHeartValue, TextView tvRiskOxygenValue, TextView tvRiskTempValue, TextView tvSedentaryWarnAmEndTime, TextView tvSedentaryWarnAmStartTime, TextView tvSedentaryWarnInterval, TextView tvSedentaryWarnPmEndTime, TextView tvSedentaryWarnPmStartTime, TextView tvSedentaryWarnReuse, TextView tvSedentaryWarnReuseTitle, TextView tvSleepQuality, TextView tvUricAcidCalibration) {
        this.rootView = rootView;
        this.ivBpCalibration = ivBpCalibration;
        this.ivBpLevel = ivBpLevel;
        this.llDrinkWaterReminder = llDrinkWaterReminder;
        this.llDrinkWaterReminderContent = llDrinkWaterReminderContent;
        this.llHealthSetMonitoring = llHealthSetMonitoring;
        this.llLongSit = llLongSit;
        this.llRiskBedtimeValue = llRiskBedtimeValue;
        this.llRiskHeartValue = llRiskHeartValue;
        this.llRiskOxygenValue = llRiskOxygenValue;
        this.llRiskTempValue = llRiskTempValue;
        this.llSedentaryWarn = llSedentaryWarn;
        this.llTargetObject = llTargetObject;
        this.navigationbar = navigationbar;
        this.rlBedtimeWarnReuse = rlBedtimeWarnReuse;
        this.rlBloodFatCalibration = rlBloodFatCalibration;
        this.rlBloodSugarCalibration = rlBloodSugarCalibration;
        this.rlBpCalibration = rlBpCalibration;
        this.rlBpGrade = rlBpGrade;
        this.rlDrinkWaterReminderEndTime = rlDrinkWaterReminderEndTime;
        this.rlDrinkWaterReminderInterval = rlDrinkWaterReminderInterval;
        this.rlDrinkWaterReminderRepeat = rlDrinkWaterReminderRepeat;
        this.rlDrinkWaterReminderStartTime = rlDrinkWaterReminderStartTime;
        this.rlHealthMonitoringInterval = rlHealthMonitoringInterval;
        this.rlMovingObject = rlMovingObject;
        this.rlRiskBedtimeValue = rlRiskBedtimeValue;
        this.rlRiskHeartValue = rlRiskHeartValue;
        this.rlRiskOxygenValue = rlRiskOxygenValue;
        this.rlRiskTempValue = rlRiskTempValue;
        this.rlSedentaryWarnAmEndTime = rlSedentaryWarnAmEndTime;
        this.rlSedentaryWarnAmStartTime = rlSedentaryWarnAmStartTime;
        this.rlSedentaryWarnInterval = rlSedentaryWarnInterval;
        this.rlSedentaryWarnPmEndTime = rlSedentaryWarnPmEndTime;
        this.rlSedentaryWarnPmStartTime = rlSedentaryWarnPmStartTime;
        this.rlSedentaryWarnReuse = rlSedentaryWarnReuse;
        this.rlSleepQuality = rlSleepQuality;
        this.rlUricAcidCalibration = rlUricAcidCalibration;
        this.switchDrinkWater = switchDrinkWater;
        this.switchHealthMonitoring = switchHealthMonitoring;
        this.switchRiskBedtimeWarn = switchRiskBedtimeWarn;
        this.switchRiskHeartWarn = switchRiskHeartWarn;
        this.switchRiskOxygenWarn = switchRiskOxygenWarn;
        this.switchRiskTempWarn = switchRiskTempWarn;
        this.switchSedentaryWarn = switchSedentaryWarn;
        this.targetViewLine = targetViewLine;
        this.tvBedtimeWarnReuse = tvBedtimeWarnReuse;
        this.tvBedtimeWarnReuseTitle = tvBedtimeWarnReuseTitle;
        this.tvBloodFatCalibration = tvBloodFatCalibration;
        this.tvBloodSugarCalibration = tvBloodSugarCalibration;
        this.tvBpCalibration = tvBpCalibration;
        this.tvBpCalibrationTitle = tvBpCalibrationTitle;
        this.tvBpLevel = tvBpLevel;
        this.tvDrinkWaterReminderEndTime = tvDrinkWaterReminderEndTime;
        this.tvDrinkWaterReminderInterval = tvDrinkWaterReminderInterval;
        this.tvDrinkWaterReminderRepeat = tvDrinkWaterReminderRepeat;
        this.tvDrinkWaterReminderRepeatTitle = tvDrinkWaterReminderRepeatTitle;
        this.tvDrinkWaterReminderStartTime = tvDrinkWaterReminderStartTime;
        this.tvHealthMonitoringIntervalValue = tvHealthMonitoringIntervalValue;
        this.tvMovingObject = tvMovingObject;
        this.tvRiskBedtimeValue = tvRiskBedtimeValue;
        this.tvRiskHeartValue = tvRiskHeartValue;
        this.tvRiskOxygenValue = tvRiskOxygenValue;
        this.tvRiskTempValue = tvRiskTempValue;
        this.tvSedentaryWarnAmEndTime = tvSedentaryWarnAmEndTime;
        this.tvSedentaryWarnAmStartTime = tvSedentaryWarnAmStartTime;
        this.tvSedentaryWarnInterval = tvSedentaryWarnInterval;
        this.tvSedentaryWarnPmEndTime = tvSedentaryWarnPmEndTime;
        this.tvSedentaryWarnPmStartTime = tvSedentaryWarnPmStartTime;
        this.tvSedentaryWarnReuse = tvSedentaryWarnReuse;
        this.tvSedentaryWarnReuseTitle = tvSedentaryWarnReuseTitle;
        this.tvSleepQuality = tvSleepQuality;
        this.tvUricAcidCalibration = tvUricAcidCalibration;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityMeHealthsettingBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityMeHealthsettingBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_me_healthsetting, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityMeHealthsettingBinding bind(View rootView) {
        View viewFindChildViewById;
        int i2 = R.id.iv_bp_calibration;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i2);
        if (imageView != null) {
            i2 = R.id.iv_bp_level;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
            if (imageView2 != null) {
                i2 = R.id.ll_drink_water_reminder;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                if (linearLayout != null) {
                    i2 = R.id.ll_drink_water_reminder_content;
                    LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                    if (linearLayout2 != null) {
                        i2 = R.id.ll_health_set_monitoring;
                        LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                        if (linearLayout3 != null) {
                            i2 = R.id.ll_long_sit;
                            LinearLayout linearLayout4 = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                            if (linearLayout4 != null) {
                                i2 = R.id.ll_risk_bedtime_value;
                                LinearLayout linearLayout5 = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                                if (linearLayout5 != null) {
                                    i2 = R.id.ll_risk_heart_value;
                                    LinearLayout linearLayout6 = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                                    if (linearLayout6 != null) {
                                        i2 = R.id.ll_risk_oxygen_value;
                                        LinearLayout linearLayout7 = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                                        if (linearLayout7 != null) {
                                            i2 = R.id.ll_risk_temp_value;
                                            LinearLayout linearLayout8 = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                                            if (linearLayout8 != null) {
                                                i2 = R.id.ll_sedentary_warn;
                                                LinearLayout linearLayout9 = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                                                if (linearLayout9 != null) {
                                                    i2 = R.id.ll_target_object;
                                                    LinearLayout linearLayout10 = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                                                    if (linearLayout10 != null) {
                                                        i2 = R.id.navigationbar;
                                                        NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
                                                        if (navigationBar != null) {
                                                            i2 = R.id.rl_bedtime_warn_reuse;
                                                            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                                                            if (relativeLayout != null) {
                                                                i2 = R.id.rl_blood_fat_calibration;
                                                                RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                                                                if (relativeLayout2 != null) {
                                                                    i2 = R.id.rl_bloodSugar_calibration;
                                                                    RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                                                                    if (relativeLayout3 != null) {
                                                                        i2 = R.id.rl_bp_calibration;
                                                                        RelativeLayout relativeLayout4 = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                                                                        if (relativeLayout4 != null) {
                                                                            i2 = R.id.rl_bp_grade;
                                                                            RelativeLayout relativeLayout5 = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                                                                            if (relativeLayout5 != null) {
                                                                                i2 = R.id.rl_drink_water_reminder_end_time;
                                                                                RelativeLayout relativeLayout6 = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                                                                                if (relativeLayout6 != null) {
                                                                                    i2 = R.id.rl_drink_water_reminder_interval;
                                                                                    RelativeLayout relativeLayout7 = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                                                                                    if (relativeLayout7 != null) {
                                                                                        i2 = R.id.rl_drink_water_reminder_repeat;
                                                                                        RelativeLayout relativeLayout8 = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                                                                                        if (relativeLayout8 != null) {
                                                                                            i2 = R.id.rl_drink_water_reminder_start_time;
                                                                                            RelativeLayout relativeLayout9 = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                                                                                            if (relativeLayout9 != null) {
                                                                                                i2 = R.id.rl_health_monitoring_interval;
                                                                                                RelativeLayout relativeLayout10 = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                                                                                                if (relativeLayout10 != null) {
                                                                                                    i2 = R.id.rl_moving_object;
                                                                                                    RelativeLayout relativeLayout11 = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                                                                                                    if (relativeLayout11 != null) {
                                                                                                        i2 = R.id.rl_risk_bedtime_value;
                                                                                                        RelativeLayout relativeLayout12 = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                                                                                                        if (relativeLayout12 != null) {
                                                                                                            i2 = R.id.rl_risk_heart_value;
                                                                                                            RelativeLayout relativeLayout13 = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                                                                                                            if (relativeLayout13 != null) {
                                                                                                                i2 = R.id.rl_risk_oxygen_value;
                                                                                                                RelativeLayout relativeLayout14 = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                if (relativeLayout14 != null) {
                                                                                                                    i2 = R.id.rl_risk_temp_value;
                                                                                                                    RelativeLayout relativeLayout15 = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                    if (relativeLayout15 != null) {
                                                                                                                        i2 = R.id.rl_sedentary_warn_am_end_time;
                                                                                                                        RelativeLayout relativeLayout16 = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                        if (relativeLayout16 != null) {
                                                                                                                            i2 = R.id.rl_sedentary_warn_am_start_time;
                                                                                                                            RelativeLayout relativeLayout17 = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                            if (relativeLayout17 != null) {
                                                                                                                                i2 = R.id.rl_sedentary_warn_interval;
                                                                                                                                RelativeLayout relativeLayout18 = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                                if (relativeLayout18 != null) {
                                                                                                                                    i2 = R.id.rl_sedentary_warn_pm_end_time;
                                                                                                                                    RelativeLayout relativeLayout19 = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                                    if (relativeLayout19 != null) {
                                                                                                                                        i2 = R.id.rl_sedentary_warn_pm_start_time;
                                                                                                                                        RelativeLayout relativeLayout20 = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                                        if (relativeLayout20 != null) {
                                                                                                                                            i2 = R.id.rl_sedentary_warn_reuse;
                                                                                                                                            RelativeLayout relativeLayout21 = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                                            if (relativeLayout21 != null) {
                                                                                                                                                i2 = R.id.rl_sleep_quality;
                                                                                                                                                RelativeLayout relativeLayout22 = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                                                if (relativeLayout22 != null) {
                                                                                                                                                    i2 = R.id.rl_uric_acid_calibration;
                                                                                                                                                    RelativeLayout relativeLayout23 = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                                                    if (relativeLayout23 != null) {
                                                                                                                                                        i2 = R.id.switch_drink_water;
                                                                                                                                                        Switch r41 = (Switch) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                                                        if (r41 != null) {
                                                                                                                                                            i2 = R.id.switch_health_monitoring;
                                                                                                                                                            Switch r42 = (Switch) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                                                            if (r42 != null) {
                                                                                                                                                                i2 = R.id.switch_risk_bedtime_warn;
                                                                                                                                                                Switch r43 = (Switch) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                                                                if (r43 != null) {
                                                                                                                                                                    i2 = R.id.switch_risk_heart_warn;
                                                                                                                                                                    Switch r44 = (Switch) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                                                                    if (r44 != null) {
                                                                                                                                                                        i2 = R.id.switch_risk_oxygen_warn;
                                                                                                                                                                        Switch r45 = (Switch) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                                                                        if (r45 != null) {
                                                                                                                                                                            i2 = R.id.switch_risk_temp_warn;
                                                                                                                                                                            Switch r46 = (Switch) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                                                                            if (r46 != null) {
                                                                                                                                                                                i2 = R.id.switch_sedentary_warn;
                                                                                                                                                                                Switch r47 = (Switch) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                                                                                if (r47 != null && (viewFindChildViewById = ViewBindings.findChildViewById(rootView, (i2 = R.id.target_view_line))) != null) {
                                                                                                                                                                                    i2 = R.id.tv_bedtime_warn_reuse;
                                                                                                                                                                                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                                                                                    if (textView != null) {
                                                                                                                                                                                        i2 = R.id.tv_bedtime_warn_reuse_title;
                                                                                                                                                                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                                                                                        if (textView2 != null) {
                                                                                                                                                                                            i2 = R.id.tv_blood_fat_calibration;
                                                                                                                                                                                            TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                                                                                            if (textView3 != null) {
                                                                                                                                                                                                i2 = R.id.tv_bloodSugar_calibration;
                                                                                                                                                                                                TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                                                                                                if (textView4 != null) {
                                                                                                                                                                                                    i2 = R.id.tv_bp_calibration;
                                                                                                                                                                                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                                                                                                    if (textView5 != null) {
                                                                                                                                                                                                        i2 = R.id.tv_bp_calibration_title;
                                                                                                                                                                                                        TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                                                                                                        if (textView6 != null) {
                                                                                                                                                                                                            i2 = R.id.tv_bp_level;
                                                                                                                                                                                                            TextView textView7 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                                                                                                            if (textView7 != null) {
                                                                                                                                                                                                                i2 = R.id.tv_drink_water_reminder_end_time;
                                                                                                                                                                                                                TextView textView8 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                                                                                                                if (textView8 != null) {
                                                                                                                                                                                                                    i2 = R.id.tv_drink_water_reminder_interval;
                                                                                                                                                                                                                    TextView textView9 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                                                                                                                    if (textView9 != null) {
                                                                                                                                                                                                                        i2 = R.id.tv_drink_water_reminder_repeat;
                                                                                                                                                                                                                        TextView textView10 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                                                                                                                        if (textView10 != null) {
                                                                                                                                                                                                                            i2 = R.id.tv_drink_water_reminder_repeat_title;
                                                                                                                                                                                                                            TextView textView11 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                                                                                                                            if (textView11 != null) {
                                                                                                                                                                                                                                i2 = R.id.tv_drink_water_reminder_start_time;
                                                                                                                                                                                                                                TextView textView12 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                                                                                                                                if (textView12 != null) {
                                                                                                                                                                                                                                    i2 = R.id.tv_health_monitoring_interval_value;
                                                                                                                                                                                                                                    TextView textView13 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                                                                                                                                    if (textView13 != null) {
                                                                                                                                                                                                                                        i2 = R.id.tv_moving_object;
                                                                                                                                                                                                                                        TextView textView14 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                                                                                                                                        if (textView14 != null) {
                                                                                                                                                                                                                                            i2 = R.id.tv_risk_bedtime_value;
                                                                                                                                                                                                                                            TextView textView15 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                                                                                                                                            if (textView15 != null) {
                                                                                                                                                                                                                                                i2 = R.id.tv_risk_heart_value;
                                                                                                                                                                                                                                                TextView textView16 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                                                                                                                                                if (textView16 != null) {
                                                                                                                                                                                                                                                    i2 = R.id.tv_risk_oxygen_value;
                                                                                                                                                                                                                                                    TextView textView17 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                                                                                                                                                    if (textView17 != null) {
                                                                                                                                                                                                                                                        i2 = R.id.tv_risk_temp_value;
                                                                                                                                                                                                                                                        TextView textView18 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                                                                                                                                                        if (textView18 != null) {
                                                                                                                                                                                                                                                            i2 = R.id.tv_sedentary_warn_am_end_time;
                                                                                                                                                                                                                                                            TextView textView19 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                                                                                                                                                            if (textView19 != null) {
                                                                                                                                                                                                                                                                i2 = R.id.tv_sedentary_warn_am_start_time;
                                                                                                                                                                                                                                                                TextView textView20 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                                                                                                                                                                if (textView20 != null) {
                                                                                                                                                                                                                                                                    i2 = R.id.tv_sedentary_warn_interval;
                                                                                                                                                                                                                                                                    TextView textView21 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                                                                                                                                                                    if (textView21 != null) {
                                                                                                                                                                                                                                                                        i2 = R.id.tv_sedentary_warn_pm_end_time;
                                                                                                                                                                                                                                                                        TextView textView22 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                                                                                                                                                                        if (textView22 != null) {
                                                                                                                                                                                                                                                                            i2 = R.id.tv_sedentary_warn_pm_start_time;
                                                                                                                                                                                                                                                                            TextView textView23 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                                                                                                                                                                            if (textView23 != null) {
                                                                                                                                                                                                                                                                                i2 = R.id.tv_sedentary_warn_reuse;
                                                                                                                                                                                                                                                                                TextView textView24 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                                                                                                                                                                                if (textView24 != null) {
                                                                                                                                                                                                                                                                                    i2 = R.id.tv_sedentary_warn_reuse_title;
                                                                                                                                                                                                                                                                                    TextView textView25 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                                                                                                                                                                                    if (textView25 != null) {
                                                                                                                                                                                                                                                                                        i2 = R.id.tv_sleep_quality;
                                                                                                                                                                                                                                                                                        TextView textView26 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                                                                                                                                                                                        if (textView26 != null) {
                                                                                                                                                                                                                                                                                            i2 = R.id.tv_uric_acid_calibration;
                                                                                                                                                                                                                                                                                            TextView textView27 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                                                                                                                                                                                            if (textView27 != null) {
                                                                                                                                                                                                                                                                                                return new ActivityMeHealthsettingBinding((LinearLayout) rootView, imageView, imageView2, linearLayout, linearLayout2, linearLayout3, linearLayout4, linearLayout5, linearLayout6, linearLayout7, linearLayout8, linearLayout9, linearLayout10, navigationBar, relativeLayout, relativeLayout2, relativeLayout3, relativeLayout4, relativeLayout5, relativeLayout6, relativeLayout7, relativeLayout8, relativeLayout9, relativeLayout10, relativeLayout11, relativeLayout12, relativeLayout13, relativeLayout14, relativeLayout15, relativeLayout16, relativeLayout17, relativeLayout18, relativeLayout19, relativeLayout20, relativeLayout21, relativeLayout22, relativeLayout23, r41, r42, r43, r44, r45, r46, r47, viewFindChildViewById, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10, textView11, textView12, textView13, textView14, textView15, textView16, textView17, textView18, textView19, textView20, textView21, textView22, textView23, textView24, textView25, textView26, textView27);
                                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                }
                                                                                                                                                                                                                            }
                                                                                                                                                                                                                        }
                                                                                                                                                                                                                    }
                                                                                                                                                                                                                }
                                                                                                                                                                                                            }
                                                                                                                                                                                                        }
                                                                                                                                                                                                    }
                                                                                                                                                                                                }
                                                                                                                                                                                            }
                                                                                                                                                                                        }
                                                                                                                                                                                    }
                                                                                                                                                                                }
                                                                                                                                                                            }
                                                                                                                                                                        }
                                                                                                                                                                    }
                                                                                                                                                                }
                                                                                                                                                            }
                                                                                                                                                        }
                                                                                                                                                    }
                                                                                                                                                }
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                }
                                                                                                                            }
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
