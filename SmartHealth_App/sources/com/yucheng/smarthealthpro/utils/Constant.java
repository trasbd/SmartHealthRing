package com.yucheng.smarthealthpro.utils;

import android.content.Context;
import com.tencent.connect.common.Constants;
import com.yucheng.smarthealthpro.MyApplication;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;

/* loaded from: classes5.dex */
public interface Constant {
    public static final String BATTERY_CHANNEL = "BatteryChannel";
    public static final String DEBUG_VERSION = "debug_version";
    public static final int DEVICE_RING = 1;
    public static final int DEVICE_RING_TOUCH = 2;
    public static final int DEVICE_WATCH = 0;
    public static final String IS_BATTERY_LOW_SHOW = "IS_BATTERY_LOW_SHOW";
    public static final String IS_SEND_MESSAGE = "IS_SEND_MESSAGE";
    public static final String KEY_SYNC_DATA = "sync_data";
    public static final int POST_NOTIFICATIONS_CODE = 111;
    public static final int RESULT_FAILED = -1;
    public static final int RESULT_OK = 0;
    public static final String SP_COUNTRY = "COUNTRY";
    public static final String SP_LANGUAGE = "Language";
    public static final String SYSTEM_LANGUAGE = "SystemLanguage";
    public static final String VERSION_CODE = "VERSION_CODE";

    public interface BloodFat {
        public static final float STATE = 5.2f;
    }

    public static class BloodMode {
        public static final int Accurate = 2;
        public static final int Monitoring = 1;
        public static final int Single = 0;
    }

    public interface EventBusTags {
        public static final String BROADCAST_ACCEPT_THE_PHONE = "广播接听电话";
        public static final String BROADCAST_HANG_UP_THE_PHONE = "广播挂断电话";
        public static final String BROADCAST_RECEIVED_THE_PHONE = "广播接收到电话";
        public static final String COMPILE_SAVE_SUCCEED = "编辑保存成功";
        public static final String MONITOR_ACCEPT_THE_PHONE = "监听到接听电话";
        public static final String MONITOR_HANG_UP_THE_PHONE = "监听到挂断电话";
        public static final String MONITOR_RECEIVED_THE_PHONE = "监听接收到电话";
    }

    public interface FilePath {
    }

    public interface Location {
        public static final String Latitude = "Latitude";
        public static final String Longitude = "Longitude";
    }

    public static class SleepType {
        public static final int abnormal = 6;
        public static final int awake = 4;
        public static final int deepSleep = 1;
        public static final int lightSleep = 2;
        public static final int naps = 5;
        public static final int rem = 3;
    }

    public interface SpConstKey {
        public static final String AFTER_BLOOD_SUGAR_CALIBRATION_MG_VALUE = "after_blood_sugar_calibration_mg_value";
        public static final String AFTER_BLOOD_SUGAR_CALIBRATION_VALUE = "after_blood_sugar_calibration_value";
        public static final String AGE = "age";
        public static final String BEDTIME_ALARM_SWITCH = "bedtime_alarm_switch";
        public static final String BEDTIME_START_HOUR = "mBedtimeStartHour";
        public static final String BEDTIME_START_MIN = "mBedtimeStartMin";
        public static final String BEDTIME_WEEK = "mBedtimeWeek";
        public static final String BEFORE_BLOOD_SUGAR_CALIBRATION_MG_VALUE = "befor_blood_sugar_calibration_mg_value";
        public static final String BEFORE_BLOOD_SUGAR_CALIBRATION_VALUE = "befor_blood_sugar_calibration_value";
        public static final String BIRTH_DATE = "birth_date";
        public static final String BLOOD_FAT_CALIBRATION_MG_VALUE = "blood_fat_calibration_mg_value";
        public static final String BLOOD_FAT_CALIBRATION_VALUE = "blood_fat_calibration_value";
        public static final String BLOOD_MEASURE_PLAN = "BLOOD_MEASURE_PLAN";
        public static final String BLOOD_OXYGEN_ALARM_REMINDER_SWITCH = "blood_oxygen_alarm_reminder_value";
        public static final String BLOOD_OXYGEN_ALARM_SWITCH = "blood_oxygen_alarm_switch";
        public static final String BLOOD_SUGAR_AND_BLOOD_FAT_UNIT = "blood_sugar_unit";
        public static final String BLOOD_SUGAR_CALIBRATION_MG_VALUE = "blood_sugar_calibration_mg_value";
        public static final String BLOOD_SUGAR_CALIBRATION_TYPE_VALUE = "blood_sugar_calibration_type_value";
        public static final String BLOOD_SUGAR_CALIBRATION_VALUE = "blood_sugar_calibration_value";
        public static final String BP_CALIBRATION_DBP_VALUE = "bp_calibration_dbp_value";
        public static final String BP_CALIBRATION_LEVEL = "bp_calibration_Level";
        public static final String BP_CALIBRATION_SBP_VALUE = "bp_calibration_sbp_value";
        public static final String BP_LEVEL_OPTIONS_ONE = "mBpLevelOptionsOne";
        public static final String COND_CODE = "cond_code";
        public static final String DEVICE_IMAGE_PATH = "deviceImagePath";
        public static final String DEVICE_MAC = "DeviceMac";
        public static final String DEV_ID = "devId";
        public static final String DND_END_TIME_HOUR = "dndEndTimeHour";
        public static final String DND_END_TIME_MIN = "dndEndTimeMin";
        public static final String DND_START_TIME_HOUR = "dndStartTimeHour";
        public static final String DND_START_TIME_MIN = "dndStartTimeMin";
        public static final String DRINK_WATER_END_HOUR = "mDrinkWaterEndHour";
        public static final String DRINK_WATER_END_MIN = "mDrinkWaterEndMin";
        public static final String DRINK_WATER_INTERVAL = "mDrinkWaterIntervalTime";
        public static final String DRINK_WATER_REMINDER_SWITCH = "drink_water_reminder_switch";
        public static final String DRINK_WATER_START_HOUR = "mDrinkWaterStartHour";
        public static final String DRINK_WATER_START_MIN = "mDrinkWaterStartMin";
        public static final String DRINK_WATER_WEEK = "mDrinkWaterWeek";
        public static final String FIRM_WARE_FILE = "FIRM_WARE_FILE";
        public static final String FIRM_WARE_FILE_CURRENT = "FIRM_WARE_FILE_CURRENT";
        public static final String FUNCTION = "function";
        public static final String HEAD_IMG = "headimg";
        public static final String HEART_INTERVAL_OPTIONS_ONE = "mHeartIntervalOptionsOne";
        public static final String HEART_RATE_ALARM_REMINDER_SWITCH = "heart_rate_alarm_reminder_value";
        public static final String HEART_RATE_ALARM_SWITCH = "heart_rate_alarm_switch";
        public static final String HEART_RATE_DETECTION_SWITCH = "heart_rate_detection_switch";
        public static final String HEART_RATE_INTERVAL_TIME = "heart_rate_interval_time";
        public static final String HEART_REMINDER_OPTIONS_ONE = "mHeartRemindOptionsOne";
        public static final String HEIGHT = "height";
        public static final String HIDE_FUNCTION = "hideFunction";
        public static final String HOME_KCAL = "home_kcal";
        public static final String HOME_KM = "home_km";
        public static final String HOME_STEP = "home_step";
        public static final String HOME_STEP_TIME = "home_step_time";
        public static final String IMAGE_PATH = "imagePath";
        public static final String INFO_FIRST_CHANGE = "infoFirstChange";
        public static final String IS_ANTI_LOSE = "isAntiLose";
        public static final String IS_COMPILE = "isCompile";
        public static final String IS_CONNECT = "isConnect";
        public static final String IS_DND_MODE = "isDndMode";
        public static final String IS_FIRST = "isFirst";
        public static final String IS_FIRST_DOWN = "isFirstDown";
        public static final String IS_FIRST_OPEN = "isFirstOpen";
        public static final String IS_FIRST_PERMISSION = "";
        public static final String IS_HAS_LOCATION_PERMISSION = "IS_HAS_LOCATION_PERMISSION";
        public static final String IS_LOGIN = "isLogin";
        public static final String IS_RAISE_SCREEN = "isRaiseScreen";
        public static final String IS_SHOW_LOCATION_PERMISSION_DIALOG = "IS_SHOW_LOCATION_PERMISSION_DIALOG";
        public static final String LUMINANCE_GRADE = "LuminanceGrade";
        public static final String LUMINANCE_OPTIONS_ONE = "mLuminanceOptionsOne";
        public static final String M_HOME_ADD_FUNCTION_BEAN_SIZE = "mHomeAddFunctionBeanSize";
        public static final String M_HOME_FUNCTION_BEAN_SIZE = "mHomeFunctionBeanSize";
        public static final String NEW_FRIENDS = "new_friends";
        public static final String NICK_NAME = "nickname";
        public static final String NotShowMeasureTip = "NotShowMeareTip";
        public static final String OpenLogSwitch = "OpenLogSwitch";
        public static final String PUSHMESSAGE = "pushMessage";
        public static final String PUSH_MESSAGE = "mPushMessage";
        public static final String Props = "Props";
        public static final String PropsBloodFat = "props_bloodFat";
        public static final String PropsBloodSugar = "props_bloodSugar";
        public static final String PropsTimeStamp = "props_timestamp";
        public static final String PropsUricAcid = "props_uricAcid";
        public static final String SEDENTARY_AM_END_HOUR = "mSedentaryAmEndHour";
        public static final String SEDENTARY_AM_END_MIN = "mSedentaryAmEndMin";
        public static final String SEDENTARY_AM_START_HOUR = "mSedentaryAmStartHour";
        public static final String SEDENTARY_AM_START_MIN = "mSedentaryAmStartMin";
        public static final String SEDENTARY_INTERVAL_TIME = "mSedentaryIntervalTime";
        public static final String SEDENTARY_INTERVAL_WEEK = "mSedentaryIntervalWeek";
        public static final String SEDENTARY_PM_END_HOUR = "mSedentaryPmEndHour";
        public static final String SEDENTARY_PM_END_MIN = "mSedentaryPmEndMin";
        public static final String SEDENTARY_PM_START_HOUR = "mSedentaryPmStartHour";
        public static final String SEDENTARY_PM_START_MIN = "mSedentaryPmStartMin";
        public static final String SEDENTARY_REMINDER_SWITCH = "sedentary_reminder_switch";
        public static final String SEX = "sex";
        public static final String SKIN_COLOR = "SkinColor";
        public static final String SLEEP_DATA_ID = "SLEEP_DATA_ID";
        public static final String STEPS_TIP_DIALOG = "steps_tip_dialog";
        public static final String TARGET_NUMBER_OF_MOVEMENT_STEPS = "target_number_of_movement_steps";
        public static final String TARGET_SLEEP_TIME = "target_sleep_time";
        public static final String TEMPERATURE_ALARM_REMINDER_F_VALUE = "temperature_alarm_reminder_f_value";
        public static final String TEMPERATURE_ALARM_REMINDER_VALUE = "temperature_alarm_reminder_value";
        public static final String TEMPERATURE_ALARM_SWITCH = "temperature_alarm_switch";
        public static final String TEMPERATURE_DETECTING_SWITCH = "temperature_detecting_switch";
        public static final String TEMPERATURE_MONITORING_INTERVAL_TIME = "temperature_monitoring_interval_time";
        public static final String TEMP_INTERVAL_OPTIONS_TWO = "mTempIntervalOptionsOne";
        public static final String TEMP_REMINDER_OPTIONS_TWO = "mTempRemindOptionsOne";
        public static final String TEMP_UNIT = "temp_unit";
        public static final String THEME_SELECTORS_INDEX = "mThemeSelectorsIndex";
        public static final String TMP = "tmp";
        public static final String TMP_CACHE_TIME = "temp_cache_time";
        public static final String TMP_MAX = "tmp_max";
        public static final String TMP_MIN = "tmp_min";
        public static final String TOKEN = "token";
        public static final String TOMORROW_COND_CODE = "tomorrow_cond_code";
        public static final String TOMORROW_TMP_MAX = "tomorrow_tmp_max";
        public static final String TOMORROW_TMP_MIN = "tomorrow_tmp_min";
        public static final String UNIT = "unit";
        public static final String URIC_ACID_CALIBRATION_MG_VALUE = "uric_acid_calibration_mg_value";
        public static final String URIC_ACID_CALIBRATION_VALUE = "uric_acid_calibration_value";
        public static final String URIC_ACID_UNIT = "uric_acid_unit";
        public static final String USER_NAME = "userName";
        public static final String VIBRATION_SETTINGS = "vibration_settings";
        public static final String WEIGHT = "weight";
        public static final String YCBLE_BINDED_MAC = "ycble_bindedmac";
        public static final String YCBLE_BINDED_NAME = "ycble_bindedname";
        public static final String anomalyLogEnable = "anomalyLogEnable";
        public static final String bloodAlgoVersion = "bloodAlgoVersion";
        public static final String deviceVersion = "deviceVersion";
        public static final String ecgWearHand = "ecgWearHand";
        public static final String ecgWearHint = "ecgWearHint";
        public static final String isSendNotifyDevice = "isSendNotifyDevice";
        public static final String isShowBond = "isShowBond";
        public static final String lastAlgorithmUpgradeTime = "lastAlgorithmUpgradeTime";
        public static final String moduleCorrelationTimeStamp = "moduleCorrelationTimeStamp";
        public static final String sleepMonitorTimeStamp = "sleepMonitorTimeStamp";
        public static final String step2HrCorrelationTimeStamp = "step2HrCorrelationTimeStamp";
        public static final String tpVersion = "tpVersion";
        public static final String vestBag = "vestBag";
    }

    public interface SpConstValue {
        public static final String AUTOMATION = "自动";
        public static final String BLOOD_SUGAR_AND_BLOOD_FAT_MG = "mg/dL";
        public static final String BLOOD_SUGAR_AND_BLOOD_FAT_MMOL = "mmol/L";
        public static final String CLOSE = "关";
        public static final int ECG_LEFT = 0;
        public static final int ECG_RIGHT = 1;
        public static final String INCH = "英制";
        public static final String ISO = "公制";
        public static final String IS_COMPILE = "已编辑";
        public static final String IS_CONNECT = "已连接";
        public static final String OPEN = "开";
        public static final String TEMP_INCH = "℉";
        public static final String TEMP_ISO = "℃";
        public static final String URIC_ACID_MG = "mg/dL";
        public static final String URIC_ACID_UMOL = "μmol/L";
        public static final String Wisdom_Function = "Wisdom_Function";
        public static final String Wisdom_SOS = "Wisdom_SOS";
    }

    public interface UricAcid {
        public static final int FEMALE_HIGH_STATE = 360;
        public static final int FEMALE_LOW_STATE = 90;
        public static final int MALE_HIGH_STATE = 420;
        public static final int MALE_LOW_STATE = 120;
    }

    public static class Wisdom {
        public static final int MUSIC = 2;
        public static final int READ = 3;
        public static final int SLIDES = 6;
        public static final int SOS = 5;
        public static final int TAKE_PHOTO = 4;
        public static final int VIDEO = 1;
    }

    static boolean isHeGe() {
        return "HeGe".equals("SmartHealth");
    }

    static boolean isTianJia() {
        return "TianJia".equals("SmartHealth");
    }

    static boolean isZhiLian() {
        return "ZhiLian".equals("SmartHealth");
    }

    static boolean iseHekim() {
        return "eHekim".equals("SmartHealth");
    }

    static boolean isMymon() {
        return "MymonX".equals("SmartHealth");
    }

    static boolean isBNRHealth() {
        return "BNRHealth".equals("SmartHealth");
    }

    static boolean isTechFeel() {
        return "TechFeel".equals("SmartHealth");
    }

    static boolean isHealthWear() {
        return "HealthWear".equals("SmartHealth");
    }

    static boolean isHealthRing() {
        return "Kingstad".equals("SmartHealth");
    }

    static boolean isSmartHealthPro() {
        return "SmartHealthPro".equals("SmartHealth");
    }

    static boolean isSmartHealth() {
        return "SmartHealth".equals("SmartHealth");
    }

    static boolean isRing() {
        int iIntValue = ((Integer) SharedPreferencesUtils.get(MyApplication.getInstance().getApplicationContext(), "hardwareType", Integer.valueOf(!isHealthWear() ? 1 : 0))).intValue();
        return iIntValue == 1 || iIntValue == 2 || isHeGe() || isHealthRing();
    }

    static int getDeviceIcon() {
        int i2 = R.mipmap.icon_me_watch;
        if (isRing()) {
            i2 = R.mipmap.ic_device_ring_1;
        }
        return isHeGe() ? R.mipmap.ic_device_ring_4 : i2;
    }

    static boolean isRingTouch() {
        return ((Integer) SharedPreferencesUtils.get(MyApplication.getInstance().getApplicationContext(), "hardwareType", 0)).intValue() == 2;
    }

    static boolean isHealthband() {
        return "HealthBand".equals("SmartHealth");
    }

    static String getMonth(Context context, String str) {
        return context.getResources().getStringArray(R.array.months)[Integer.parseInt(str) - 1];
    }

    static String getDefaultMonitorTime() {
        if (!isMymon()) {
            return "60";
        }
        return Constants.VIA_REPORT_TYPE_SHARE_TO_QQ;
    }

    static String getDefaultMonitorSwitch() {
        if (!isTechFeel()) {
            return SpConstValue.OPEN;
        }
        return SpConstValue.CLOSE;
    }
}
