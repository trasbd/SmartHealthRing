package com.yucheng.smarthealthpro.database.room;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.room.InvalidationTracker;
import androidx.room.RoomMasterTable;
import androidx.room.RoomOpenDelegate;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.SQLite;
import androidx.sqlite.SQLiteConnection;
import com.facebook.AccessToken;
import com.yucheng.smarthealthpro.database.room.dao.BloodKetonesDao;
import com.yucheng.smarthealthpro.database.room.dao.BloodKetonesDao_Impl;
import com.yucheng.smarthealthpro.database.room.dao.BloodLipidsDao;
import com.yucheng.smarthealthpro.database.room.dao.BloodLipidsDao_Impl;
import com.yucheng.smarthealthpro.database.room.dao.BloodPressureDao;
import com.yucheng.smarthealthpro.database.room.dao.BloodPressureDao_Impl;
import com.yucheng.smarthealthpro.database.room.dao.BodyDataDao;
import com.yucheng.smarthealthpro.database.room.dao.BodyDataDao_Impl;
import com.yucheng.smarthealthpro.database.room.dao.EcgMeasureDao;
import com.yucheng.smarthealthpro.database.room.dao.EcgMeasureDao_Impl;
import com.yucheng.smarthealthpro.database.room.dao.HealthMetricDao;
import com.yucheng.smarthealthpro.database.room.dao.HealthMetricDao_Impl;
import com.yucheng.smarthealthpro.database.room.dao.HeartRateDao;
import com.yucheng.smarthealthpro.database.room.dao.HeartRateDao_Impl;
import com.yucheng.smarthealthpro.database.room.dao.MotionPatternDao;
import com.yucheng.smarthealthpro.database.room.dao.MotionPatternDao_Impl;
import com.yucheng.smarthealthpro.database.room.dao.PhysiotherapyDao;
import com.yucheng.smarthealthpro.database.room.dao.PhysiotherapyDao_Impl;
import com.yucheng.smarthealthpro.database.room.dao.SleepDao;
import com.yucheng.smarthealthpro.database.room.dao.SleepDao_Impl;
import com.yucheng.smarthealthpro.database.room.dao.SportRecordDao;
import com.yucheng.smarthealthpro.database.room.dao.SportRecordDao_Impl;
import com.yucheng.smarthealthpro.database.room.dao.StepDao;
import com.yucheng.smarthealthpro.database.room.dao.StepDao_Impl;
import com.yucheng.smarthealthpro.database.room.dao.UricAcidDao;
import com.yucheng.smarthealthpro.database.room.dao.UricAcidDao_Impl;
import com.yucheng.smarthealthpro.utils.Constant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes4.dex */
public final class AppDatabase_Impl extends AppDatabase {
    private volatile BloodKetonesDao _bloodKetonesDao;
    private volatile BloodLipidsDao _bloodLipidsDao;
    private volatile BloodPressureDao _bloodPressureDao;
    private volatile BodyDataDao _bodyDataDao;
    private volatile EcgMeasureDao _ecgMeasureDao;
    private volatile HealthMetricDao _healthMetricDao;
    private volatile HeartRateDao _heartRateDao;
    private volatile MotionPatternDao _motionPatternDao;
    private volatile PhysiotherapyDao _physiotherapyDao;
    private volatile SleepDao _sleepDao;
    private volatile SportRecordDao _sportRecordDao;
    private volatile StepDao _stepDao;
    private volatile UricAcidDao _uricAcidDao;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.room.RoomDatabase
    public RoomOpenDelegate createOpenDelegate() {
        return new RoomOpenDelegate(42, "2d21c83850168627869cf4db3aae1ea9", "8b8664854b869b5a912b02e7af8e70e1") { // from class: com.yucheng.smarthealthpro.database.room.AppDatabase_Impl.1
            @Override // androidx.room.RoomOpenDelegate
            public void onCreate(final SQLiteConnection connection) {
            }

            @Override // androidx.room.RoomOpenDelegate
            public void onPostMigrate(final SQLiteConnection connection) {
            }

            @Override // androidx.room.RoomOpenDelegate
            public void createAllTables(final SQLiteConnection connection) throws Exception {
                SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `blood_ketones_data` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `query_id` INTEGER NOT NULL DEFAULT 0, `start_timestamp` INTEGER NOT NULL, `time_year_to_day` TEXT NOT NULL, `blood_ketones_integer_part` INTEGER NOT NULL, `blood_ketones_fractional_part` INTEGER NOT NULL, `measure_mode` INTEGER NOT NULL, `user_id` TEXT NOT NULL, `device_type` TEXT NOT NULL, `device_mac_address` TEXT NOT NULL, `data_group_id` TEXT, `is_uploaded` INTEGER NOT NULL, `is_other_uploaded` INTEGER NOT NULL)");
                SQLite.execSQL(connection, "CREATE INDEX IF NOT EXISTS `index_blood_ketones_data_start_timestamp` ON `blood_ketones_data` (`start_timestamp`)");
                SQLite.execSQL(connection, "CREATE INDEX IF NOT EXISTS `index_blood_ketones_data_user_id` ON `blood_ketones_data` (`user_id`)");
                SQLite.execSQL(connection, "CREATE UNIQUE INDEX IF NOT EXISTS `index_blood_ketones_data_start_timestamp_device_mac_address` ON `blood_ketones_data` (`start_timestamp`, `device_mac_address`)");
                SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `blood_lipids_data` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `query_id` INTEGER NOT NULL DEFAULT 0, `start_timestamp` INTEGER NOT NULL, `time_year_to_day` TEXT NOT NULL, `cholesterol_integer_part` INTEGER NOT NULL, `cholesterol_fractional_part` INTEGER NOT NULL, `triglyceride_integer_part` INTEGER NOT NULL, `triglyceride_fractional_part` INTEGER NOT NULL, `high_lipoprotein_cholesterol_integer_part` INTEGER NOT NULL, `high_lipoprotein_cholesterol_fractional_part` INTEGER NOT NULL, `low_lipoprotein_cholesterol_integer_part` INTEGER NOT NULL, `low_lipoprotein_cholesterol_fractional_part` INTEGER NOT NULL, `measure_mode` INTEGER NOT NULL, `user_id` TEXT NOT NULL, `device_type` TEXT NOT NULL, `device_mac_address` TEXT NOT NULL, `data_group_id` TEXT, `is_uploaded` INTEGER NOT NULL, `is_other_uploaded` INTEGER NOT NULL)");
                SQLite.execSQL(connection, "CREATE INDEX IF NOT EXISTS `index_blood_lipids_data_start_timestamp` ON `blood_lipids_data` (`start_timestamp`)");
                SQLite.execSQL(connection, "CREATE INDEX IF NOT EXISTS `index_blood_lipids_data_user_id` ON `blood_lipids_data` (`user_id`)");
                SQLite.execSQL(connection, "CREATE UNIQUE INDEX IF NOT EXISTS `index_blood_lipids_data_start_timestamp_device_mac_address` ON `blood_lipids_data` (`start_timestamp`, `device_mac_address`)");
                SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `blood_pressure_data` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `query_id` INTEGER NOT NULL DEFAULT 0, `start_timestamp` INTEGER NOT NULL, `time_year_to_day` TEXT NOT NULL, `diastolic_bp` INTEGER NOT NULL, `systolic_bp` INTEGER NOT NULL, `measure_mode` INTEGER NOT NULL, `user_id` TEXT NOT NULL, `device_type` TEXT NOT NULL, `device_mac_address` TEXT NOT NULL, `data_group_id` TEXT, `is_uploaded` INTEGER NOT NULL, `is_other_uploaded` INTEGER NOT NULL)");
                SQLite.execSQL(connection, "CREATE INDEX IF NOT EXISTS `index_blood_pressure_data_start_timestamp` ON `blood_pressure_data` (`start_timestamp`)");
                SQLite.execSQL(connection, "CREATE INDEX IF NOT EXISTS `index_blood_pressure_data_user_id` ON `blood_pressure_data` (`user_id`)");
                SQLite.execSQL(connection, "CREATE UNIQUE INDEX IF NOT EXISTS `index_blood_pressure_data_start_timestamp_device_mac_address` ON `blood_pressure_data` (`start_timestamp`, `device_mac_address`)");
                SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `body_data` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `query_id` INTEGER NOT NULL DEFAULT 0, `start_timestamp` INTEGER NOT NULL, `time_year_to_day` TEXT NOT NULL, `load_index_integer_part` INTEGER NOT NULL, `load_index_fractional_part` INTEGER NOT NULL, `hrv_integer_part` INTEGER NOT NULL, `hrv_fractional_part` INTEGER NOT NULL, `pressure_integer_part` INTEGER NOT NULL, `pressure_fractional_part` INTEGER NOT NULL, `body_state_integer_part` INTEGER NOT NULL, `body_state_fractional_part` INTEGER NOT NULL, `sympathetic_integer_part` INTEGER NOT NULL, `sympathetic_fractional_part` INTEGER NOT NULL, `sdn_value` INTEGER NOT NULL, `maximal_oxygen_intake` INTEGER NOT NULL, `user_id` TEXT NOT NULL, `device_type` TEXT NOT NULL, `device_mac_address` TEXT NOT NULL, `data_group_id` TEXT, `is_uploaded` INTEGER NOT NULL, `is_other_uploaded` INTEGER NOT NULL)");
                SQLite.execSQL(connection, "CREATE INDEX IF NOT EXISTS `index_body_data_start_timestamp` ON `body_data` (`start_timestamp`)");
                SQLite.execSQL(connection, "CREATE INDEX IF NOT EXISTS `index_body_data_user_id` ON `body_data` (`user_id`)");
                SQLite.execSQL(connection, "CREATE UNIQUE INDEX IF NOT EXISTS `index_body_data_start_timestamp_device_mac_address` ON `body_data` (`start_timestamp`, `device_mac_address`)");
                SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `ecg_measure_data` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `query_id` INTEGER NOT NULL DEFAULT 0, `start_timestamp` INTEGER NOT NULL, `time_year_to_day` TEXT NOT NULL, `hrv_value` INTEGER NOT NULL, `heart_rate` INTEGER NOT NULL, `max_bp` INTEGER NOT NULL, `min_bp` INTEGER NOT NULL, `measure_data` TEXT NOT NULL, `age` INTEGER NOT NULL, `sex` INTEGER NOT NULL, `is_afib` INTEGER NOT NULL, `diagnose_type` INTEGER NOT NULL, `health_norm` TEXT, `user_id` TEXT NOT NULL, `device_type` TEXT NOT NULL, `device_mac_address` TEXT NOT NULL, `data_group_id` TEXT, `is_uploaded` INTEGER NOT NULL, `is_other_uploaded` INTEGER NOT NULL)");
                SQLite.execSQL(connection, "CREATE INDEX IF NOT EXISTS `index_ecg_measure_data_start_timestamp` ON `ecg_measure_data` (`start_timestamp`)");
                SQLite.execSQL(connection, "CREATE INDEX IF NOT EXISTS `index_ecg_measure_data_user_id` ON `ecg_measure_data` (`user_id`)");
                SQLite.execSQL(connection, "CREATE UNIQUE INDEX IF NOT EXISTS `index_ecg_measure_data_start_timestamp_device_mac_address` ON `ecg_measure_data` (`start_timestamp`, `device_mac_address`)");
                SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `health_metrics_data` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `query_id` INTEGER NOT NULL DEFAULT 0, `start_timestamp` INTEGER NOT NULL, `time_year_to_day` TEXT NOT NULL, `heart_rate_value` INTEGER NOT NULL, `hrv_value` INTEGER NOT NULL, `cvrr_value` INTEGER NOT NULL, `blood_oxygen_level` INTEGER NOT NULL, `step_count` INTEGER NOT NULL, `diastolic_bp` INTEGER NOT NULL, `systolic_bp` INTEGER NOT NULL, `respiratory_rate` INTEGER NOT NULL, `temperature_integer_part` INTEGER NOT NULL, `temperature_fractional_part` INTEGER NOT NULL, `body_fat_integer_part` INTEGER NOT NULL, `body_fat_fractional_part` INTEGER NOT NULL, `blood_sugar_level` INTEGER NOT NULL, `blood_sugar_measurement_mode` INTEGER NOT NULL, `user_id` TEXT NOT NULL, `device_type` TEXT NOT NULL, `device_mac_address` TEXT NOT NULL, `data_group_id` TEXT, `is_hrv_uploaded` INTEGER NOT NULL, `is_blood_oxygen_uploaded` INTEGER NOT NULL, `is_respiratory_rate_uploaded` INTEGER NOT NULL, `is_temperature_uploaded` INTEGER NOT NULL, `is_body_fat_uploaded` INTEGER NOT NULL, `is_blood_sugar_uploaded` INTEGER NOT NULL, `is_other_hrv_uploaded` INTEGER NOT NULL, `is_other_blood_oxygen_uploaded` INTEGER NOT NULL, `is_other_respiratory_rate_uploaded` INTEGER NOT NULL, `is_other_temperature_uploaded` INTEGER NOT NULL, `is_other_body_fat_uploaded` INTEGER NOT NULL, `is_other_blood_sugar_uploaded` INTEGER NOT NULL)");
                SQLite.execSQL(connection, "CREATE INDEX IF NOT EXISTS `index_health_metrics_data_start_timestamp` ON `health_metrics_data` (`start_timestamp`)");
                SQLite.execSQL(connection, "CREATE INDEX IF NOT EXISTS `index_health_metrics_data_user_id` ON `health_metrics_data` (`user_id`)");
                SQLite.execSQL(connection, "CREATE UNIQUE INDEX IF NOT EXISTS `index_health_metrics_data_start_timestamp_device_mac_address` ON `health_metrics_data` (`start_timestamp`, `device_mac_address`)");
                SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `heart_rate_data` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `query_id` INTEGER NOT NULL DEFAULT 0, `start_timestamp` INTEGER NOT NULL, `time_year_to_day` TEXT NOT NULL, `heart_rate` INTEGER NOT NULL, `user_id` TEXT NOT NULL, `device_type` TEXT NOT NULL, `device_mac_address` TEXT NOT NULL, `data_group_id` TEXT, `is_uploaded` INTEGER NOT NULL, `is_other_uploaded` INTEGER NOT NULL)");
                SQLite.execSQL(connection, "CREATE INDEX IF NOT EXISTS `index_heart_rate_data_start_timestamp` ON `heart_rate_data` (`start_timestamp`)");
                SQLite.execSQL(connection, "CREATE INDEX IF NOT EXISTS `index_heart_rate_data_user_id` ON `heart_rate_data` (`user_id`)");
                SQLite.execSQL(connection, "CREATE UNIQUE INDEX IF NOT EXISTS `index_heart_rate_data_start_timestamp_device_mac_address` ON `heart_rate_data` (`start_timestamp`, `device_mac_address`)");
                SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `motion_pattern_data` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `query_id` INTEGER NOT NULL DEFAULT 0, `start_timestamp` INTEGER NOT NULL, `end_timestamp` INTEGER NOT NULL, `time_year_to_day` TEXT NOT NULL, `sport_steps` INTEGER NOT NULL, `sport_distances` INTEGER NOT NULL, `sport_calories` INTEGER NOT NULL, `sport_mode` INTEGER NOT NULL, `start_method` INTEGER NOT NULL, `sport_heart_rate` INTEGER NOT NULL, `sport_duration` INTEGER NOT NULL, `min_heart_rate` INTEGER NOT NULL, `max_heart_rate` INTEGER NOT NULL, `user_id` TEXT NOT NULL, `device_type` TEXT NOT NULL, `device_mac_address` TEXT NOT NULL, `data_group_id` TEXT, `is_uploaded` INTEGER NOT NULL, `is_other_uploaded` INTEGER NOT NULL)");
                SQLite.execSQL(connection, "CREATE INDEX IF NOT EXISTS `index_motion_pattern_data_start_timestamp` ON `motion_pattern_data` (`start_timestamp`)");
                SQLite.execSQL(connection, "CREATE INDEX IF NOT EXISTS `index_motion_pattern_data_user_id` ON `motion_pattern_data` (`user_id`)");
                SQLite.execSQL(connection, "CREATE UNIQUE INDEX IF NOT EXISTS `index_motion_pattern_data_start_timestamp_device_mac_address` ON `motion_pattern_data` (`start_timestamp`, `device_mac_address`)");
                SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `physiotherapy_data` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `query_id` INTEGER NOT NULL DEFAULT 0, `start_timestamp` INTEGER NOT NULL, `time_year_to_day` TEXT NOT NULL, `duration` INTEGER NOT NULL, `type` INTEGER NOT NULL, `start_type` INTEGER NOT NULL, `power_level` INTEGER NOT NULL, `duration_level` INTEGER NOT NULL, `user_id` TEXT NOT NULL, `device_type` TEXT NOT NULL, `device_mac_address` TEXT NOT NULL, `data_group_id` TEXT, `is_uploaded` INTEGER NOT NULL)");
                SQLite.execSQL(connection, "CREATE INDEX IF NOT EXISTS `index_physiotherapy_data_start_timestamp` ON `physiotherapy_data` (`start_timestamp`)");
                SQLite.execSQL(connection, "CREATE INDEX IF NOT EXISTS `index_physiotherapy_data_user_id` ON `physiotherapy_data` (`user_id`)");
                SQLite.execSQL(connection, "CREATE UNIQUE INDEX IF NOT EXISTS `index_physiotherapy_data_start_timestamp_device_mac_address` ON `physiotherapy_data` (`start_timestamp`, `device_mac_address`)");
                SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `sleep_data` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `query_id` INTEGER NOT NULL DEFAULT 0, `start_timestamp` INTEGER NOT NULL, `end_timestamp` INTEGER NOT NULL, `time_year_to_day` TEXT NOT NULL, `deep_sleep_count` INTEGER NOT NULL, `light_sleep_count` INTEGER NOT NULL, `deep_sleep_total_seconds` INTEGER NOT NULL, `light_sleep_total_seconds` INTEGER NOT NULL, `rem_total_seconds` INTEGER NOT NULL, `wake_count` INTEGER NOT NULL, `wake_duration_seconds` INTEGER NOT NULL, `sleep_stages_json` TEXT, `user_id` TEXT NOT NULL, `device_type` TEXT NOT NULL, `device_mac_address` TEXT NOT NULL, `data_group_id` TEXT, `is_uploaded` INTEGER NOT NULL, `is_other_uploaded` INTEGER NOT NULL)");
                SQLite.execSQL(connection, "CREATE INDEX IF NOT EXISTS `index_sleep_data_start_timestamp` ON `sleep_data` (`start_timestamp`)");
                SQLite.execSQL(connection, "CREATE INDEX IF NOT EXISTS `index_sleep_data_user_id` ON `sleep_data` (`user_id`)");
                SQLite.execSQL(connection, "CREATE UNIQUE INDEX IF NOT EXISTS `index_sleep_data_start_timestamp_device_mac_address` ON `sleep_data` (`start_timestamp`, `device_mac_address`)");
                SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `sport_data` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `query_id` INTEGER NOT NULL DEFAULT 0, `activity_type` INTEGER NOT NULL, `start_timestamp` INTEGER NOT NULL, `time_year_to_day` TEXT NOT NULL, `total_steps` INTEGER NOT NULL, `total_distance` REAL NOT NULL, `last_distance` REAL NOT NULL, `total_calories` INTEGER NOT NULL, `last_calories` INTEGER NOT NULL, `pace_per_km` TEXT, `avg_heart_rate` INTEGER NOT NULL, `duration_seconds` INTEGER NOT NULL, `speed_kmh` REAL NOT NULL, `start_coordinates` TEXT, `end_coordinates` TEXT, `path_coordinates` TEXT, `user_id` TEXT NOT NULL, `device_type` TEXT NOT NULL, `device_mac_address` TEXT NOT NULL, `data_group_id` TEXT, `is_uploaded` INTEGER NOT NULL, `is_other_uploaded` INTEGER NOT NULL)");
                SQLite.execSQL(connection, "CREATE INDEX IF NOT EXISTS `index_sport_data_start_timestamp` ON `sport_data` (`start_timestamp`)");
                SQLite.execSQL(connection, "CREATE INDEX IF NOT EXISTS `index_sport_data_user_id` ON `sport_data` (`user_id`)");
                SQLite.execSQL(connection, "CREATE UNIQUE INDEX IF NOT EXISTS `index_sport_data_start_timestamp_device_mac_address` ON `sport_data` (`start_timestamp`, `device_mac_address`)");
                SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `step_data` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `query_id` INTEGER NOT NULL DEFAULT 0, `start_timestamp` INTEGER NOT NULL, `end_timestamp` INTEGER NOT NULL, `time_year_to_day` TEXT NOT NULL, `step_count` INTEGER NOT NULL, `distance_meters` INTEGER NOT NULL, `calories` INTEGER NOT NULL, `user_id` TEXT NOT NULL, `device_type` TEXT NOT NULL, `device_mac_address` TEXT NOT NULL, `data_group_id` TEXT, `is_uploaded` INTEGER NOT NULL, `is_other_uploaded` INTEGER NOT NULL)");
                SQLite.execSQL(connection, "CREATE INDEX IF NOT EXISTS `index_step_data_start_timestamp` ON `step_data` (`start_timestamp`)");
                SQLite.execSQL(connection, "CREATE INDEX IF NOT EXISTS `index_step_data_user_id` ON `step_data` (`user_id`)");
                SQLite.execSQL(connection, "CREATE UNIQUE INDEX IF NOT EXISTS `index_step_data_start_timestamp_device_mac_address` ON `step_data` (`start_timestamp`, `device_mac_address`)");
                SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `uric_acid_data` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `query_id` INTEGER NOT NULL DEFAULT 0, `start_timestamp` INTEGER NOT NULL, `time_year_to_day` TEXT NOT NULL, `uric_acid` INTEGER NOT NULL, `measure_mode` INTEGER NOT NULL, `user_id` TEXT NOT NULL, `device_type` TEXT NOT NULL, `device_mac_address` TEXT NOT NULL, `data_group_id` TEXT, `is_uploaded` INTEGER NOT NULL, `is_other_uploaded` INTEGER NOT NULL)");
                SQLite.execSQL(connection, "CREATE INDEX IF NOT EXISTS `index_uric_acid_data_start_timestamp` ON `uric_acid_data` (`start_timestamp`)");
                SQLite.execSQL(connection, "CREATE INDEX IF NOT EXISTS `index_uric_acid_data_user_id` ON `uric_acid_data` (`user_id`)");
                SQLite.execSQL(connection, "CREATE UNIQUE INDEX IF NOT EXISTS `index_uric_acid_data_start_timestamp_device_mac_address` ON `uric_acid_data` (`start_timestamp`, `device_mac_address`)");
                SQLite.execSQL(connection, RoomMasterTable.CREATE_QUERY);
                SQLite.execSQL(connection, "INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '2d21c83850168627869cf4db3aae1ea9')");
            }

            @Override // androidx.room.RoomOpenDelegate
            public void dropAllTables(final SQLiteConnection connection) throws Exception {
                SQLite.execSQL(connection, "DROP TABLE IF EXISTS `blood_ketones_data`");
                SQLite.execSQL(connection, "DROP TABLE IF EXISTS `blood_lipids_data`");
                SQLite.execSQL(connection, "DROP TABLE IF EXISTS `blood_pressure_data`");
                SQLite.execSQL(connection, "DROP TABLE IF EXISTS `body_data`");
                SQLite.execSQL(connection, "DROP TABLE IF EXISTS `ecg_measure_data`");
                SQLite.execSQL(connection, "DROP TABLE IF EXISTS `health_metrics_data`");
                SQLite.execSQL(connection, "DROP TABLE IF EXISTS `heart_rate_data`");
                SQLite.execSQL(connection, "DROP TABLE IF EXISTS `motion_pattern_data`");
                SQLite.execSQL(connection, "DROP TABLE IF EXISTS `physiotherapy_data`");
                SQLite.execSQL(connection, "DROP TABLE IF EXISTS `sleep_data`");
                SQLite.execSQL(connection, "DROP TABLE IF EXISTS `sport_data`");
                SQLite.execSQL(connection, "DROP TABLE IF EXISTS `step_data`");
                SQLite.execSQL(connection, "DROP TABLE IF EXISTS `uric_acid_data`");
            }

            @Override // androidx.room.RoomOpenDelegate
            public void onOpen(final SQLiteConnection connection) {
                AppDatabase_Impl.this.internalInitInvalidationTracker(connection);
            }

            @Override // androidx.room.RoomOpenDelegate
            public void onPreMigrate(final SQLiteConnection connection) {
                DBUtil.dropFtsSyncTriggers(connection);
            }

            @Override // androidx.room.RoomOpenDelegate
            public RoomOpenDelegate.ValidationResult onValidateSchema(final SQLiteConnection connection) {
                HashMap map = new HashMap(13);
                map.put("id", new TableInfo.Column("id", "INTEGER", false, 1, null, 1));
                map.put("query_id", new TableInfo.Column("query_id", "INTEGER", true, 0, "0", 1));
                map.put("start_timestamp", new TableInfo.Column("start_timestamp", "INTEGER", true, 0, null, 1));
                map.put("time_year_to_day", new TableInfo.Column("time_year_to_day", "TEXT", true, 0, null, 1));
                map.put("blood_ketones_integer_part", new TableInfo.Column("blood_ketones_integer_part", "INTEGER", true, 0, null, 1));
                map.put("blood_ketones_fractional_part", new TableInfo.Column("blood_ketones_fractional_part", "INTEGER", true, 0, null, 1));
                map.put("measure_mode", new TableInfo.Column("measure_mode", "INTEGER", true, 0, null, 1));
                map.put(AccessToken.USER_ID_KEY, new TableInfo.Column(AccessToken.USER_ID_KEY, "TEXT", true, 0, null, 1));
                map.put("device_type", new TableInfo.Column("device_type", "TEXT", true, 0, null, 1));
                map.put("device_mac_address", new TableInfo.Column("device_mac_address", "TEXT", true, 0, null, 1));
                map.put("data_group_id", new TableInfo.Column("data_group_id", "TEXT", false, 0, null, 1));
                map.put("is_uploaded", new TableInfo.Column("is_uploaded", "INTEGER", true, 0, null, 1));
                map.put("is_other_uploaded", new TableInfo.Column("is_other_uploaded", "INTEGER", true, 0, null, 1));
                HashSet hashSet = new HashSet(0);
                HashSet hashSet2 = new HashSet(3);
                hashSet2.add(new TableInfo.Index("index_blood_ketones_data_start_timestamp", false, Arrays.asList("start_timestamp"), Arrays.asList("ASC")));
                hashSet2.add(new TableInfo.Index("index_blood_ketones_data_user_id", false, Arrays.asList(AccessToken.USER_ID_KEY), Arrays.asList("ASC")));
                hashSet2.add(new TableInfo.Index("index_blood_ketones_data_start_timestamp_device_mac_address", true, Arrays.asList("start_timestamp", "device_mac_address"), Arrays.asList("ASC", "ASC")));
                TableInfo tableInfo = new TableInfo("blood_ketones_data", map, hashSet, hashSet2);
                TableInfo tableInfo2 = TableInfo.read(connection, "blood_ketones_data");
                if (!tableInfo.equals(tableInfo2)) {
                    return new RoomOpenDelegate.ValidationResult(false, "blood_ketones_data(com.yucheng.smarthealthpro.database.room.bean.BloodKetones).\n Expected:\n" + tableInfo + "\n Found:\n" + tableInfo2);
                }
                HashMap map2 = new HashMap(19);
                map2.put("id", new TableInfo.Column("id", "INTEGER", false, 1, null, 1));
                map2.put("query_id", new TableInfo.Column("query_id", "INTEGER", true, 0, "0", 1));
                map2.put("start_timestamp", new TableInfo.Column("start_timestamp", "INTEGER", true, 0, null, 1));
                map2.put("time_year_to_day", new TableInfo.Column("time_year_to_day", "TEXT", true, 0, null, 1));
                map2.put("cholesterol_integer_part", new TableInfo.Column("cholesterol_integer_part", "INTEGER", true, 0, null, 1));
                map2.put("cholesterol_fractional_part", new TableInfo.Column("cholesterol_fractional_part", "INTEGER", true, 0, null, 1));
                map2.put("triglyceride_integer_part", new TableInfo.Column("triglyceride_integer_part", "INTEGER", true, 0, null, 1));
                map2.put("triglyceride_fractional_part", new TableInfo.Column("triglyceride_fractional_part", "INTEGER", true, 0, null, 1));
                map2.put("high_lipoprotein_cholesterol_integer_part", new TableInfo.Column("high_lipoprotein_cholesterol_integer_part", "INTEGER", true, 0, null, 1));
                map2.put("high_lipoprotein_cholesterol_fractional_part", new TableInfo.Column("high_lipoprotein_cholesterol_fractional_part", "INTEGER", true, 0, null, 1));
                map2.put("low_lipoprotein_cholesterol_integer_part", new TableInfo.Column("low_lipoprotein_cholesterol_integer_part", "INTEGER", true, 0, null, 1));
                map2.put("low_lipoprotein_cholesterol_fractional_part", new TableInfo.Column("low_lipoprotein_cholesterol_fractional_part", "INTEGER", true, 0, null, 1));
                map2.put("measure_mode", new TableInfo.Column("measure_mode", "INTEGER", true, 0, null, 1));
                map2.put(AccessToken.USER_ID_KEY, new TableInfo.Column(AccessToken.USER_ID_KEY, "TEXT", true, 0, null, 1));
                map2.put("device_type", new TableInfo.Column("device_type", "TEXT", true, 0, null, 1));
                map2.put("device_mac_address", new TableInfo.Column("device_mac_address", "TEXT", true, 0, null, 1));
                map2.put("data_group_id", new TableInfo.Column("data_group_id", "TEXT", false, 0, null, 1));
                map2.put("is_uploaded", new TableInfo.Column("is_uploaded", "INTEGER", true, 0, null, 1));
                map2.put("is_other_uploaded", new TableInfo.Column("is_other_uploaded", "INTEGER", true, 0, null, 1));
                HashSet hashSet3 = new HashSet(0);
                HashSet hashSet4 = new HashSet(3);
                hashSet4.add(new TableInfo.Index("index_blood_lipids_data_start_timestamp", false, Arrays.asList("start_timestamp"), Arrays.asList("ASC")));
                hashSet4.add(new TableInfo.Index("index_blood_lipids_data_user_id", false, Arrays.asList(AccessToken.USER_ID_KEY), Arrays.asList("ASC")));
                hashSet4.add(new TableInfo.Index("index_blood_lipids_data_start_timestamp_device_mac_address", true, Arrays.asList("start_timestamp", "device_mac_address"), Arrays.asList("ASC", "ASC")));
                TableInfo tableInfo3 = new TableInfo("blood_lipids_data", map2, hashSet3, hashSet4);
                TableInfo tableInfo4 = TableInfo.read(connection, "blood_lipids_data");
                if (!tableInfo3.equals(tableInfo4)) {
                    return new RoomOpenDelegate.ValidationResult(false, "blood_lipids_data(com.yucheng.smarthealthpro.database.room.bean.BloodLipids).\n Expected:\n" + tableInfo3 + "\n Found:\n" + tableInfo4);
                }
                HashMap map3 = new HashMap(13);
                map3.put("id", new TableInfo.Column("id", "INTEGER", false, 1, null, 1));
                map3.put("query_id", new TableInfo.Column("query_id", "INTEGER", true, 0, "0", 1));
                map3.put("start_timestamp", new TableInfo.Column("start_timestamp", "INTEGER", true, 0, null, 1));
                map3.put("time_year_to_day", new TableInfo.Column("time_year_to_day", "TEXT", true, 0, null, 1));
                map3.put("diastolic_bp", new TableInfo.Column("diastolic_bp", "INTEGER", true, 0, null, 1));
                map3.put("systolic_bp", new TableInfo.Column("systolic_bp", "INTEGER", true, 0, null, 1));
                map3.put("measure_mode", new TableInfo.Column("measure_mode", "INTEGER", true, 0, null, 1));
                map3.put(AccessToken.USER_ID_KEY, new TableInfo.Column(AccessToken.USER_ID_KEY, "TEXT", true, 0, null, 1));
                map3.put("device_type", new TableInfo.Column("device_type", "TEXT", true, 0, null, 1));
                map3.put("device_mac_address", new TableInfo.Column("device_mac_address", "TEXT", true, 0, null, 1));
                map3.put("data_group_id", new TableInfo.Column("data_group_id", "TEXT", false, 0, null, 1));
                map3.put("is_uploaded", new TableInfo.Column("is_uploaded", "INTEGER", true, 0, null, 1));
                map3.put("is_other_uploaded", new TableInfo.Column("is_other_uploaded", "INTEGER", true, 0, null, 1));
                HashSet hashSet5 = new HashSet(0);
                HashSet hashSet6 = new HashSet(3);
                hashSet6.add(new TableInfo.Index("index_blood_pressure_data_start_timestamp", false, Arrays.asList("start_timestamp"), Arrays.asList("ASC")));
                hashSet6.add(new TableInfo.Index("index_blood_pressure_data_user_id", false, Arrays.asList(AccessToken.USER_ID_KEY), Arrays.asList("ASC")));
                hashSet6.add(new TableInfo.Index("index_blood_pressure_data_start_timestamp_device_mac_address", true, Arrays.asList("start_timestamp", "device_mac_address"), Arrays.asList("ASC", "ASC")));
                TableInfo tableInfo5 = new TableInfo("blood_pressure_data", map3, hashSet5, hashSet6);
                TableInfo tableInfo6 = TableInfo.read(connection, "blood_pressure_data");
                if (!tableInfo5.equals(tableInfo6)) {
                    return new RoomOpenDelegate.ValidationResult(false, "blood_pressure_data(com.yucheng.smarthealthpro.database.room.bean.BloodPressure).\n Expected:\n" + tableInfo5 + "\n Found:\n" + tableInfo6);
                }
                HashMap map4 = new HashMap(22);
                map4.put("id", new TableInfo.Column("id", "INTEGER", false, 1, null, 1));
                map4.put("query_id", new TableInfo.Column("query_id", "INTEGER", true, 0, "0", 1));
                map4.put("start_timestamp", new TableInfo.Column("start_timestamp", "INTEGER", true, 0, null, 1));
                map4.put("time_year_to_day", new TableInfo.Column("time_year_to_day", "TEXT", true, 0, null, 1));
                map4.put("load_index_integer_part", new TableInfo.Column("load_index_integer_part", "INTEGER", true, 0, null, 1));
                map4.put("load_index_fractional_part", new TableInfo.Column("load_index_fractional_part", "INTEGER", true, 0, null, 1));
                map4.put("hrv_integer_part", new TableInfo.Column("hrv_integer_part", "INTEGER", true, 0, null, 1));
                map4.put("hrv_fractional_part", new TableInfo.Column("hrv_fractional_part", "INTEGER", true, 0, null, 1));
                map4.put("pressure_integer_part", new TableInfo.Column("pressure_integer_part", "INTEGER", true, 0, null, 1));
                map4.put("pressure_fractional_part", new TableInfo.Column("pressure_fractional_part", "INTEGER", true, 0, null, 1));
                map4.put("body_state_integer_part", new TableInfo.Column("body_state_integer_part", "INTEGER", true, 0, null, 1));
                map4.put("body_state_fractional_part", new TableInfo.Column("body_state_fractional_part", "INTEGER", true, 0, null, 1));
                map4.put("sympathetic_integer_part", new TableInfo.Column("sympathetic_integer_part", "INTEGER", true, 0, null, 1));
                map4.put("sympathetic_fractional_part", new TableInfo.Column("sympathetic_fractional_part", "INTEGER", true, 0, null, 1));
                map4.put("sdn_value", new TableInfo.Column("sdn_value", "INTEGER", true, 0, null, 1));
                map4.put("maximal_oxygen_intake", new TableInfo.Column("maximal_oxygen_intake", "INTEGER", true, 0, null, 1));
                map4.put(AccessToken.USER_ID_KEY, new TableInfo.Column(AccessToken.USER_ID_KEY, "TEXT", true, 0, null, 1));
                map4.put("device_type", new TableInfo.Column("device_type", "TEXT", true, 0, null, 1));
                map4.put("device_mac_address", new TableInfo.Column("device_mac_address", "TEXT", true, 0, null, 1));
                map4.put("data_group_id", new TableInfo.Column("data_group_id", "TEXT", false, 0, null, 1));
                map4.put("is_uploaded", new TableInfo.Column("is_uploaded", "INTEGER", true, 0, null, 1));
                map4.put("is_other_uploaded", new TableInfo.Column("is_other_uploaded", "INTEGER", true, 0, null, 1));
                HashSet hashSet7 = new HashSet(0);
                HashSet hashSet8 = new HashSet(3);
                hashSet8.add(new TableInfo.Index("index_body_data_start_timestamp", false, Arrays.asList("start_timestamp"), Arrays.asList("ASC")));
                hashSet8.add(new TableInfo.Index("index_body_data_user_id", false, Arrays.asList(AccessToken.USER_ID_KEY), Arrays.asList("ASC")));
                hashSet8.add(new TableInfo.Index("index_body_data_start_timestamp_device_mac_address", true, Arrays.asList("start_timestamp", "device_mac_address"), Arrays.asList("ASC", "ASC")));
                TableInfo tableInfo7 = new TableInfo("body_data", map4, hashSet7, hashSet8);
                TableInfo tableInfo8 = TableInfo.read(connection, "body_data");
                if (!tableInfo7.equals(tableInfo8)) {
                    return new RoomOpenDelegate.ValidationResult(false, "body_data(com.yucheng.smarthealthpro.database.room.bean.BodyData).\n Expected:\n" + tableInfo7 + "\n Found:\n" + tableInfo8);
                }
                HashMap map5 = new HashMap(20);
                map5.put("id", new TableInfo.Column("id", "INTEGER", false, 1, null, 1));
                map5.put("query_id", new TableInfo.Column("query_id", "INTEGER", true, 0, "0", 1));
                map5.put("start_timestamp", new TableInfo.Column("start_timestamp", "INTEGER", true, 0, null, 1));
                map5.put("time_year_to_day", new TableInfo.Column("time_year_to_day", "TEXT", true, 0, null, 1));
                map5.put("hrv_value", new TableInfo.Column("hrv_value", "INTEGER", true, 0, null, 1));
                map5.put("heart_rate", new TableInfo.Column("heart_rate", "INTEGER", true, 0, null, 1));
                map5.put("max_bp", new TableInfo.Column("max_bp", "INTEGER", true, 0, null, 1));
                map5.put("min_bp", new TableInfo.Column("min_bp", "INTEGER", true, 0, null, 1));
                map5.put("measure_data", new TableInfo.Column("measure_data", "TEXT", true, 0, null, 1));
                map5.put(Constant.SpConstKey.AGE, new TableInfo.Column(Constant.SpConstKey.AGE, "INTEGER", true, 0, null, 1));
                map5.put(Constant.SpConstKey.SEX, new TableInfo.Column(Constant.SpConstKey.SEX, "INTEGER", true, 0, null, 1));
                map5.put("is_afib", new TableInfo.Column("is_afib", "INTEGER", true, 0, null, 1));
                map5.put("diagnose_type", new TableInfo.Column("diagnose_type", "INTEGER", true, 0, null, 1));
                map5.put("health_norm", new TableInfo.Column("health_norm", "TEXT", false, 0, null, 1));
                map5.put(AccessToken.USER_ID_KEY, new TableInfo.Column(AccessToken.USER_ID_KEY, "TEXT", true, 0, null, 1));
                map5.put("device_type", new TableInfo.Column("device_type", "TEXT", true, 0, null, 1));
                map5.put("device_mac_address", new TableInfo.Column("device_mac_address", "TEXT", true, 0, null, 1));
                map5.put("data_group_id", new TableInfo.Column("data_group_id", "TEXT", false, 0, null, 1));
                map5.put("is_uploaded", new TableInfo.Column("is_uploaded", "INTEGER", true, 0, null, 1));
                map5.put("is_other_uploaded", new TableInfo.Column("is_other_uploaded", "INTEGER", true, 0, null, 1));
                HashSet hashSet9 = new HashSet(0);
                HashSet hashSet10 = new HashSet(3);
                hashSet10.add(new TableInfo.Index("index_ecg_measure_data_start_timestamp", false, Arrays.asList("start_timestamp"), Arrays.asList("ASC")));
                hashSet10.add(new TableInfo.Index("index_ecg_measure_data_user_id", false, Arrays.asList(AccessToken.USER_ID_KEY), Arrays.asList("ASC")));
                hashSet10.add(new TableInfo.Index("index_ecg_measure_data_start_timestamp_device_mac_address", true, Arrays.asList("start_timestamp", "device_mac_address"), Arrays.asList("ASC", "ASC")));
                TableInfo tableInfo9 = new TableInfo("ecg_measure_data", map5, hashSet9, hashSet10);
                TableInfo tableInfo10 = TableInfo.read(connection, "ecg_measure_data");
                if (!tableInfo9.equals(tableInfo10)) {
                    return new RoomOpenDelegate.ValidationResult(false, "ecg_measure_data(com.yucheng.smarthealthpro.database.room.bean.EcgMeasure).\n Expected:\n" + tableInfo9 + "\n Found:\n" + tableInfo10);
                }
                HashMap map6 = new HashMap(34);
                map6.put("id", new TableInfo.Column("id", "INTEGER", false, 1, null, 1));
                map6.put("query_id", new TableInfo.Column("query_id", "INTEGER", true, 0, "0", 1));
                map6.put("start_timestamp", new TableInfo.Column("start_timestamp", "INTEGER", true, 0, null, 1));
                map6.put("time_year_to_day", new TableInfo.Column("time_year_to_day", "TEXT", true, 0, null, 1));
                map6.put("heart_rate_value", new TableInfo.Column("heart_rate_value", "INTEGER", true, 0, null, 1));
                map6.put("hrv_value", new TableInfo.Column("hrv_value", "INTEGER", true, 0, null, 1));
                map6.put("cvrr_value", new TableInfo.Column("cvrr_value", "INTEGER", true, 0, null, 1));
                map6.put("blood_oxygen_level", new TableInfo.Column("blood_oxygen_level", "INTEGER", true, 0, null, 1));
                map6.put("step_count", new TableInfo.Column("step_count", "INTEGER", true, 0, null, 1));
                map6.put("diastolic_bp", new TableInfo.Column("diastolic_bp", "INTEGER", true, 0, null, 1));
                map6.put("systolic_bp", new TableInfo.Column("systolic_bp", "INTEGER", true, 0, null, 1));
                map6.put("respiratory_rate", new TableInfo.Column("respiratory_rate", "INTEGER", true, 0, null, 1));
                map6.put("temperature_integer_part", new TableInfo.Column("temperature_integer_part", "INTEGER", true, 0, null, 1));
                map6.put("temperature_fractional_part", new TableInfo.Column("temperature_fractional_part", "INTEGER", true, 0, null, 1));
                map6.put("body_fat_integer_part", new TableInfo.Column("body_fat_integer_part", "INTEGER", true, 0, null, 1));
                map6.put("body_fat_fractional_part", new TableInfo.Column("body_fat_fractional_part", "INTEGER", true, 0, null, 1));
                map6.put("blood_sugar_level", new TableInfo.Column("blood_sugar_level", "INTEGER", true, 0, null, 1));
                map6.put("blood_sugar_measurement_mode", new TableInfo.Column("blood_sugar_measurement_mode", "INTEGER", true, 0, null, 1));
                map6.put(AccessToken.USER_ID_KEY, new TableInfo.Column(AccessToken.USER_ID_KEY, "TEXT", true, 0, null, 1));
                map6.put("device_type", new TableInfo.Column("device_type", "TEXT", true, 0, null, 1));
                map6.put("device_mac_address", new TableInfo.Column("device_mac_address", "TEXT", true, 0, null, 1));
                map6.put("data_group_id", new TableInfo.Column("data_group_id", "TEXT", false, 0, null, 1));
                map6.put("is_hrv_uploaded", new TableInfo.Column("is_hrv_uploaded", "INTEGER", true, 0, null, 1));
                map6.put("is_blood_oxygen_uploaded", new TableInfo.Column("is_blood_oxygen_uploaded", "INTEGER", true, 0, null, 1));
                map6.put("is_respiratory_rate_uploaded", new TableInfo.Column("is_respiratory_rate_uploaded", "INTEGER", true, 0, null, 1));
                map6.put("is_temperature_uploaded", new TableInfo.Column("is_temperature_uploaded", "INTEGER", true, 0, null, 1));
                map6.put("is_body_fat_uploaded", new TableInfo.Column("is_body_fat_uploaded", "INTEGER", true, 0, null, 1));
                map6.put("is_blood_sugar_uploaded", new TableInfo.Column("is_blood_sugar_uploaded", "INTEGER", true, 0, null, 1));
                map6.put("is_other_hrv_uploaded", new TableInfo.Column("is_other_hrv_uploaded", "INTEGER", true, 0, null, 1));
                map6.put("is_other_blood_oxygen_uploaded", new TableInfo.Column("is_other_blood_oxygen_uploaded", "INTEGER", true, 0, null, 1));
                map6.put("is_other_respiratory_rate_uploaded", new TableInfo.Column("is_other_respiratory_rate_uploaded", "INTEGER", true, 0, null, 1));
                map6.put("is_other_temperature_uploaded", new TableInfo.Column("is_other_temperature_uploaded", "INTEGER", true, 0, null, 1));
                map6.put("is_other_body_fat_uploaded", new TableInfo.Column("is_other_body_fat_uploaded", "INTEGER", true, 0, null, 1));
                map6.put("is_other_blood_sugar_uploaded", new TableInfo.Column("is_other_blood_sugar_uploaded", "INTEGER", true, 0, null, 1));
                HashSet hashSet11 = new HashSet(0);
                HashSet hashSet12 = new HashSet(3);
                hashSet12.add(new TableInfo.Index("index_health_metrics_data_start_timestamp", false, Arrays.asList("start_timestamp"), Arrays.asList("ASC")));
                hashSet12.add(new TableInfo.Index("index_health_metrics_data_user_id", false, Arrays.asList(AccessToken.USER_ID_KEY), Arrays.asList("ASC")));
                hashSet12.add(new TableInfo.Index("index_health_metrics_data_start_timestamp_device_mac_address", true, Arrays.asList("start_timestamp", "device_mac_address"), Arrays.asList("ASC", "ASC")));
                TableInfo tableInfo11 = new TableInfo("health_metrics_data", map6, hashSet11, hashSet12);
                TableInfo tableInfo12 = TableInfo.read(connection, "health_metrics_data");
                if (!tableInfo11.equals(tableInfo12)) {
                    return new RoomOpenDelegate.ValidationResult(false, "health_metrics_data(com.yucheng.smarthealthpro.database.room.bean.HealthMetric).\n Expected:\n" + tableInfo11 + "\n Found:\n" + tableInfo12);
                }
                HashMap map7 = new HashMap(11);
                map7.put("id", new TableInfo.Column("id", "INTEGER", false, 1, null, 1));
                map7.put("query_id", new TableInfo.Column("query_id", "INTEGER", true, 0, "0", 1));
                map7.put("start_timestamp", new TableInfo.Column("start_timestamp", "INTEGER", true, 0, null, 1));
                map7.put("time_year_to_day", new TableInfo.Column("time_year_to_day", "TEXT", true, 0, null, 1));
                map7.put("heart_rate", new TableInfo.Column("heart_rate", "INTEGER", true, 0, null, 1));
                map7.put(AccessToken.USER_ID_KEY, new TableInfo.Column(AccessToken.USER_ID_KEY, "TEXT", true, 0, null, 1));
                map7.put("device_type", new TableInfo.Column("device_type", "TEXT", true, 0, null, 1));
                map7.put("device_mac_address", new TableInfo.Column("device_mac_address", "TEXT", true, 0, null, 1));
                map7.put("data_group_id", new TableInfo.Column("data_group_id", "TEXT", false, 0, null, 1));
                map7.put("is_uploaded", new TableInfo.Column("is_uploaded", "INTEGER", true, 0, null, 1));
                map7.put("is_other_uploaded", new TableInfo.Column("is_other_uploaded", "INTEGER", true, 0, null, 1));
                HashSet hashSet13 = new HashSet(0);
                HashSet hashSet14 = new HashSet(3);
                hashSet14.add(new TableInfo.Index("index_heart_rate_data_start_timestamp", false, Arrays.asList("start_timestamp"), Arrays.asList("ASC")));
                hashSet14.add(new TableInfo.Index("index_heart_rate_data_user_id", false, Arrays.asList(AccessToken.USER_ID_KEY), Arrays.asList("ASC")));
                hashSet14.add(new TableInfo.Index("index_heart_rate_data_start_timestamp_device_mac_address", true, Arrays.asList("start_timestamp", "device_mac_address"), Arrays.asList("ASC", "ASC")));
                TableInfo tableInfo13 = new TableInfo("heart_rate_data", map7, hashSet13, hashSet14);
                TableInfo tableInfo14 = TableInfo.read(connection, "heart_rate_data");
                if (!tableInfo13.equals(tableInfo14)) {
                    return new RoomOpenDelegate.ValidationResult(false, "heart_rate_data(com.yucheng.smarthealthpro.database.room.bean.HeartRate).\n Expected:\n" + tableInfo13 + "\n Found:\n" + tableInfo14);
                }
                HashMap map8 = new HashMap(20);
                map8.put("id", new TableInfo.Column("id", "INTEGER", false, 1, null, 1));
                map8.put("query_id", new TableInfo.Column("query_id", "INTEGER", true, 0, "0", 1));
                map8.put("start_timestamp", new TableInfo.Column("start_timestamp", "INTEGER", true, 0, null, 1));
                map8.put("end_timestamp", new TableInfo.Column("end_timestamp", "INTEGER", true, 0, null, 1));
                map8.put("time_year_to_day", new TableInfo.Column("time_year_to_day", "TEXT", true, 0, null, 1));
                map8.put("sport_steps", new TableInfo.Column("sport_steps", "INTEGER", true, 0, null, 1));
                map8.put("sport_distances", new TableInfo.Column("sport_distances", "INTEGER", true, 0, null, 1));
                map8.put("sport_calories", new TableInfo.Column("sport_calories", "INTEGER", true, 0, null, 1));
                map8.put("sport_mode", new TableInfo.Column("sport_mode", "INTEGER", true, 0, null, 1));
                map8.put("start_method", new TableInfo.Column("start_method", "INTEGER", true, 0, null, 1));
                map8.put("sport_heart_rate", new TableInfo.Column("sport_heart_rate", "INTEGER", true, 0, null, 1));
                map8.put("sport_duration", new TableInfo.Column("sport_duration", "INTEGER", true, 0, null, 1));
                map8.put("min_heart_rate", new TableInfo.Column("min_heart_rate", "INTEGER", true, 0, null, 1));
                map8.put("max_heart_rate", new TableInfo.Column("max_heart_rate", "INTEGER", true, 0, null, 1));
                map8.put(AccessToken.USER_ID_KEY, new TableInfo.Column(AccessToken.USER_ID_KEY, "TEXT", true, 0, null, 1));
                map8.put("device_type", new TableInfo.Column("device_type", "TEXT", true, 0, null, 1));
                map8.put("device_mac_address", new TableInfo.Column("device_mac_address", "TEXT", true, 0, null, 1));
                map8.put("data_group_id", new TableInfo.Column("data_group_id", "TEXT", false, 0, null, 1));
                map8.put("is_uploaded", new TableInfo.Column("is_uploaded", "INTEGER", true, 0, null, 1));
                map8.put("is_other_uploaded", new TableInfo.Column("is_other_uploaded", "INTEGER", true, 0, null, 1));
                HashSet hashSet15 = new HashSet(0);
                HashSet hashSet16 = new HashSet(3);
                hashSet16.add(new TableInfo.Index("index_motion_pattern_data_start_timestamp", false, Arrays.asList("start_timestamp"), Arrays.asList("ASC")));
                hashSet16.add(new TableInfo.Index("index_motion_pattern_data_user_id", false, Arrays.asList(AccessToken.USER_ID_KEY), Arrays.asList("ASC")));
                hashSet16.add(new TableInfo.Index("index_motion_pattern_data_start_timestamp_device_mac_address", true, Arrays.asList("start_timestamp", "device_mac_address"), Arrays.asList("ASC", "ASC")));
                TableInfo tableInfo15 = new TableInfo("motion_pattern_data", map8, hashSet15, hashSet16);
                TableInfo tableInfo16 = TableInfo.read(connection, "motion_pattern_data");
                if (!tableInfo15.equals(tableInfo16)) {
                    return new RoomOpenDelegate.ValidationResult(false, "motion_pattern_data(com.yucheng.smarthealthpro.database.room.bean.MotionPattern).\n Expected:\n" + tableInfo15 + "\n Found:\n" + tableInfo16);
                }
                HashMap map9 = new HashMap(14);
                map9.put("id", new TableInfo.Column("id", "INTEGER", false, 1, null, 1));
                map9.put("query_id", new TableInfo.Column("query_id", "INTEGER", true, 0, "0", 1));
                map9.put("start_timestamp", new TableInfo.Column("start_timestamp", "INTEGER", true, 0, null, 1));
                map9.put("time_year_to_day", new TableInfo.Column("time_year_to_day", "TEXT", true, 0, null, 1));
                map9.put(TypedValues.TransitionType.S_DURATION, new TableInfo.Column(TypedValues.TransitionType.S_DURATION, "INTEGER", true, 0, null, 1));
                map9.put("type", new TableInfo.Column("type", "INTEGER", true, 0, null, 1));
                map9.put("start_type", new TableInfo.Column("start_type", "INTEGER", true, 0, null, 1));
                map9.put("power_level", new TableInfo.Column("power_level", "INTEGER", true, 0, null, 1));
                map9.put("duration_level", new TableInfo.Column("duration_level", "INTEGER", true, 0, null, 1));
                map9.put(AccessToken.USER_ID_KEY, new TableInfo.Column(AccessToken.USER_ID_KEY, "TEXT", true, 0, null, 1));
                map9.put("device_type", new TableInfo.Column("device_type", "TEXT", true, 0, null, 1));
                map9.put("device_mac_address", new TableInfo.Column("device_mac_address", "TEXT", true, 0, null, 1));
                map9.put("data_group_id", new TableInfo.Column("data_group_id", "TEXT", false, 0, null, 1));
                map9.put("is_uploaded", new TableInfo.Column("is_uploaded", "INTEGER", true, 0, null, 1));
                HashSet hashSet17 = new HashSet(0);
                HashSet hashSet18 = new HashSet(3);
                hashSet18.add(new TableInfo.Index("index_physiotherapy_data_start_timestamp", false, Arrays.asList("start_timestamp"), Arrays.asList("ASC")));
                hashSet18.add(new TableInfo.Index("index_physiotherapy_data_user_id", false, Arrays.asList(AccessToken.USER_ID_KEY), Arrays.asList("ASC")));
                hashSet18.add(new TableInfo.Index("index_physiotherapy_data_start_timestamp_device_mac_address", true, Arrays.asList("start_timestamp", "device_mac_address"), Arrays.asList("ASC", "ASC")));
                TableInfo tableInfo17 = new TableInfo("physiotherapy_data", map9, hashSet17, hashSet18);
                TableInfo tableInfo18 = TableInfo.read(connection, "physiotherapy_data");
                if (!tableInfo17.equals(tableInfo18)) {
                    return new RoomOpenDelegate.ValidationResult(false, "physiotherapy_data(com.yucheng.smarthealthpro.database.room.bean.Physiotherapy).\n Expected:\n" + tableInfo17 + "\n Found:\n" + tableInfo18);
                }
                HashMap map10 = new HashMap(19);
                map10.put("id", new TableInfo.Column("id", "INTEGER", false, 1, null, 1));
                map10.put("query_id", new TableInfo.Column("query_id", "INTEGER", true, 0, "0", 1));
                map10.put("start_timestamp", new TableInfo.Column("start_timestamp", "INTEGER", true, 0, null, 1));
                map10.put("end_timestamp", new TableInfo.Column("end_timestamp", "INTEGER", true, 0, null, 1));
                map10.put("time_year_to_day", new TableInfo.Column("time_year_to_day", "TEXT", true, 0, null, 1));
                map10.put("deep_sleep_count", new TableInfo.Column("deep_sleep_count", "INTEGER", true, 0, null, 1));
                map10.put("light_sleep_count", new TableInfo.Column("light_sleep_count", "INTEGER", true, 0, null, 1));
                map10.put("deep_sleep_total_seconds", new TableInfo.Column("deep_sleep_total_seconds", "INTEGER", true, 0, null, 1));
                map10.put("light_sleep_total_seconds", new TableInfo.Column("light_sleep_total_seconds", "INTEGER", true, 0, null, 1));
                map10.put("rem_total_seconds", new TableInfo.Column("rem_total_seconds", "INTEGER", true, 0, null, 1));
                map10.put("wake_count", new TableInfo.Column("wake_count", "INTEGER", true, 0, null, 1));
                map10.put("wake_duration_seconds", new TableInfo.Column("wake_duration_seconds", "INTEGER", true, 0, null, 1));
                map10.put("sleep_stages_json", new TableInfo.Column("sleep_stages_json", "TEXT", false, 0, null, 1));
                map10.put(AccessToken.USER_ID_KEY, new TableInfo.Column(AccessToken.USER_ID_KEY, "TEXT", true, 0, null, 1));
                map10.put("device_type", new TableInfo.Column("device_type", "TEXT", true, 0, null, 1));
                map10.put("device_mac_address", new TableInfo.Column("device_mac_address", "TEXT", true, 0, null, 1));
                map10.put("data_group_id", new TableInfo.Column("data_group_id", "TEXT", false, 0, null, 1));
                map10.put("is_uploaded", new TableInfo.Column("is_uploaded", "INTEGER", true, 0, null, 1));
                map10.put("is_other_uploaded", new TableInfo.Column("is_other_uploaded", "INTEGER", true, 0, null, 1));
                HashSet hashSet19 = new HashSet(0);
                HashSet hashSet20 = new HashSet(3);
                hashSet20.add(new TableInfo.Index("index_sleep_data_start_timestamp", false, Arrays.asList("start_timestamp"), Arrays.asList("ASC")));
                hashSet20.add(new TableInfo.Index("index_sleep_data_user_id", false, Arrays.asList(AccessToken.USER_ID_KEY), Arrays.asList("ASC")));
                hashSet20.add(new TableInfo.Index("index_sleep_data_start_timestamp_device_mac_address", true, Arrays.asList("start_timestamp", "device_mac_address"), Arrays.asList("ASC", "ASC")));
                TableInfo tableInfo19 = new TableInfo("sleep_data", map10, hashSet19, hashSet20);
                TableInfo tableInfo20 = TableInfo.read(connection, "sleep_data");
                if (!tableInfo19.equals(tableInfo20)) {
                    return new RoomOpenDelegate.ValidationResult(false, "sleep_data(com.yucheng.smarthealthpro.database.room.bean.Sleep).\n Expected:\n" + tableInfo19 + "\n Found:\n" + tableInfo20);
                }
                HashMap map11 = new HashMap(23);
                map11.put("id", new TableInfo.Column("id", "INTEGER", false, 1, null, 1));
                map11.put("query_id", new TableInfo.Column("query_id", "INTEGER", true, 0, "0", 1));
                map11.put("activity_type", new TableInfo.Column("activity_type", "INTEGER", true, 0, null, 1));
                map11.put("start_timestamp", new TableInfo.Column("start_timestamp", "INTEGER", true, 0, null, 1));
                map11.put("time_year_to_day", new TableInfo.Column("time_year_to_day", "TEXT", true, 0, null, 1));
                map11.put("total_steps", new TableInfo.Column("total_steps", "INTEGER", true, 0, null, 1));
                map11.put("total_distance", new TableInfo.Column("total_distance", "REAL", true, 0, null, 1));
                map11.put("last_distance", new TableInfo.Column("last_distance", "REAL", true, 0, null, 1));
                map11.put("total_calories", new TableInfo.Column("total_calories", "INTEGER", true, 0, null, 1));
                map11.put("last_calories", new TableInfo.Column("last_calories", "INTEGER", true, 0, null, 1));
                map11.put("pace_per_km", new TableInfo.Column("pace_per_km", "TEXT", false, 0, null, 1));
                map11.put("avg_heart_rate", new TableInfo.Column("avg_heart_rate", "INTEGER", true, 0, null, 1));
                map11.put("duration_seconds", new TableInfo.Column("duration_seconds", "INTEGER", true, 0, null, 1));
                map11.put("speed_kmh", new TableInfo.Column("speed_kmh", "REAL", true, 0, null, 1));
                map11.put("start_coordinates", new TableInfo.Column("start_coordinates", "TEXT", false, 0, null, 1));
                map11.put("end_coordinates", new TableInfo.Column("end_coordinates", "TEXT", false, 0, null, 1));
                map11.put("path_coordinates", new TableInfo.Column("path_coordinates", "TEXT", false, 0, null, 1));
                map11.put(AccessToken.USER_ID_KEY, new TableInfo.Column(AccessToken.USER_ID_KEY, "TEXT", true, 0, null, 1));
                map11.put("device_type", new TableInfo.Column("device_type", "TEXT", true, 0, null, 1));
                map11.put("device_mac_address", new TableInfo.Column("device_mac_address", "TEXT", true, 0, null, 1));
                map11.put("data_group_id", new TableInfo.Column("data_group_id", "TEXT", false, 0, null, 1));
                map11.put("is_uploaded", new TableInfo.Column("is_uploaded", "INTEGER", true, 0, null, 1));
                map11.put("is_other_uploaded", new TableInfo.Column("is_other_uploaded", "INTEGER", true, 0, null, 1));
                HashSet hashSet21 = new HashSet(0);
                HashSet hashSet22 = new HashSet(3);
                hashSet22.add(new TableInfo.Index("index_sport_data_start_timestamp", false, Arrays.asList("start_timestamp"), Arrays.asList("ASC")));
                hashSet22.add(new TableInfo.Index("index_sport_data_user_id", false, Arrays.asList(AccessToken.USER_ID_KEY), Arrays.asList("ASC")));
                hashSet22.add(new TableInfo.Index("index_sport_data_start_timestamp_device_mac_address", true, Arrays.asList("start_timestamp", "device_mac_address"), Arrays.asList("ASC", "ASC")));
                TableInfo tableInfo21 = new TableInfo("sport_data", map11, hashSet21, hashSet22);
                TableInfo tableInfo22 = TableInfo.read(connection, "sport_data");
                if (!tableInfo21.equals(tableInfo22)) {
                    return new RoomOpenDelegate.ValidationResult(false, "sport_data(com.yucheng.smarthealthpro.database.room.bean.SportRecord).\n Expected:\n" + tableInfo21 + "\n Found:\n" + tableInfo22);
                }
                HashMap map12 = new HashMap(14);
                map12.put("id", new TableInfo.Column("id", "INTEGER", false, 1, null, 1));
                map12.put("query_id", new TableInfo.Column("query_id", "INTEGER", true, 0, "0", 1));
                map12.put("start_timestamp", new TableInfo.Column("start_timestamp", "INTEGER", true, 0, null, 1));
                map12.put("end_timestamp", new TableInfo.Column("end_timestamp", "INTEGER", true, 0, null, 1));
                map12.put("time_year_to_day", new TableInfo.Column("time_year_to_day", "TEXT", true, 0, null, 1));
                map12.put("step_count", new TableInfo.Column("step_count", "INTEGER", true, 0, null, 1));
                map12.put("distance_meters", new TableInfo.Column("distance_meters", "INTEGER", true, 0, null, 1));
                map12.put("calories", new TableInfo.Column("calories", "INTEGER", true, 0, null, 1));
                map12.put(AccessToken.USER_ID_KEY, new TableInfo.Column(AccessToken.USER_ID_KEY, "TEXT", true, 0, null, 1));
                map12.put("device_type", new TableInfo.Column("device_type", "TEXT", true, 0, null, 1));
                map12.put("device_mac_address", new TableInfo.Column("device_mac_address", "TEXT", true, 0, null, 1));
                map12.put("data_group_id", new TableInfo.Column("data_group_id", "TEXT", false, 0, null, 1));
                map12.put("is_uploaded", new TableInfo.Column("is_uploaded", "INTEGER", true, 0, null, 1));
                map12.put("is_other_uploaded", new TableInfo.Column("is_other_uploaded", "INTEGER", true, 0, null, 1));
                HashSet hashSet23 = new HashSet(0);
                HashSet hashSet24 = new HashSet(3);
                hashSet24.add(new TableInfo.Index("index_step_data_start_timestamp", false, Arrays.asList("start_timestamp"), Arrays.asList("ASC")));
                hashSet24.add(new TableInfo.Index("index_step_data_user_id", false, Arrays.asList(AccessToken.USER_ID_KEY), Arrays.asList("ASC")));
                hashSet24.add(new TableInfo.Index("index_step_data_start_timestamp_device_mac_address", true, Arrays.asList("start_timestamp", "device_mac_address"), Arrays.asList("ASC", "ASC")));
                TableInfo tableInfo23 = new TableInfo("step_data", map12, hashSet23, hashSet24);
                TableInfo tableInfo24 = TableInfo.read(connection, "step_data");
                if (!tableInfo23.equals(tableInfo24)) {
                    return new RoomOpenDelegate.ValidationResult(false, "step_data(com.yucheng.smarthealthpro.database.room.bean.Step).\n Expected:\n" + tableInfo23 + "\n Found:\n" + tableInfo24);
                }
                HashMap map13 = new HashMap(12);
                map13.put("id", new TableInfo.Column("id", "INTEGER", false, 1, null, 1));
                map13.put("query_id", new TableInfo.Column("query_id", "INTEGER", true, 0, "0", 1));
                map13.put("start_timestamp", new TableInfo.Column("start_timestamp", "INTEGER", true, 0, null, 1));
                map13.put("time_year_to_day", new TableInfo.Column("time_year_to_day", "TEXT", true, 0, null, 1));
                map13.put("uric_acid", new TableInfo.Column("uric_acid", "INTEGER", true, 0, null, 1));
                map13.put("measure_mode", new TableInfo.Column("measure_mode", "INTEGER", true, 0, null, 1));
                map13.put(AccessToken.USER_ID_KEY, new TableInfo.Column(AccessToken.USER_ID_KEY, "TEXT", true, 0, null, 1));
                map13.put("device_type", new TableInfo.Column("device_type", "TEXT", true, 0, null, 1));
                map13.put("device_mac_address", new TableInfo.Column("device_mac_address", "TEXT", true, 0, null, 1));
                map13.put("data_group_id", new TableInfo.Column("data_group_id", "TEXT", false, 0, null, 1));
                map13.put("is_uploaded", new TableInfo.Column("is_uploaded", "INTEGER", true, 0, null, 1));
                map13.put("is_other_uploaded", new TableInfo.Column("is_other_uploaded", "INTEGER", true, 0, null, 1));
                HashSet hashSet25 = new HashSet(0);
                HashSet hashSet26 = new HashSet(3);
                hashSet26.add(new TableInfo.Index("index_uric_acid_data_start_timestamp", false, Arrays.asList("start_timestamp"), Arrays.asList("ASC")));
                hashSet26.add(new TableInfo.Index("index_uric_acid_data_user_id", false, Arrays.asList(AccessToken.USER_ID_KEY), Arrays.asList("ASC")));
                hashSet26.add(new TableInfo.Index("index_uric_acid_data_start_timestamp_device_mac_address", true, Arrays.asList("start_timestamp", "device_mac_address"), Arrays.asList("ASC", "ASC")));
                TableInfo tableInfo25 = new TableInfo("uric_acid_data", map13, hashSet25, hashSet26);
                TableInfo tableInfo26 = TableInfo.read(connection, "uric_acid_data");
                if (!tableInfo25.equals(tableInfo26)) {
                    return new RoomOpenDelegate.ValidationResult(false, "uric_acid_data(com.yucheng.smarthealthpro.database.room.bean.UricAcid).\n Expected:\n" + tableInfo25 + "\n Found:\n" + tableInfo26);
                }
                return new RoomOpenDelegate.ValidationResult(true, null);
            }
        };
    }

    @Override // androidx.room.RoomDatabase
    protected InvalidationTracker createInvalidationTracker() {
        return new InvalidationTracker(this, new HashMap(0), new HashMap(0), "blood_ketones_data", "blood_lipids_data", "blood_pressure_data", "body_data", "ecg_measure_data", "health_metrics_data", "heart_rate_data", "motion_pattern_data", "physiotherapy_data", "sleep_data", "sport_data", "step_data", "uric_acid_data");
    }

    @Override // androidx.room.RoomDatabase
    public void clearAllTables() {
        super.performClear(false, "blood_ketones_data", "blood_lipids_data", "blood_pressure_data", "body_data", "ecg_measure_data", "health_metrics_data", "heart_rate_data", "motion_pattern_data", "physiotherapy_data", "sleep_data", "sport_data", "step_data", "uric_acid_data");
    }

    @Override // androidx.room.RoomDatabase
    protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
        HashMap map = new HashMap();
        map.put(BloodKetonesDao.class, BloodKetonesDao_Impl.getRequiredConverters());
        map.put(BloodLipidsDao.class, BloodLipidsDao_Impl.getRequiredConverters());
        map.put(BloodPressureDao.class, BloodPressureDao_Impl.getRequiredConverters());
        map.put(BodyDataDao.class, BodyDataDao_Impl.getRequiredConverters());
        map.put(EcgMeasureDao.class, EcgMeasureDao_Impl.getRequiredConverters());
        map.put(HealthMetricDao.class, HealthMetricDao_Impl.getRequiredConverters());
        map.put(HeartRateDao.class, HeartRateDao_Impl.getRequiredConverters());
        map.put(MotionPatternDao.class, MotionPatternDao_Impl.getRequiredConverters());
        map.put(PhysiotherapyDao.class, PhysiotherapyDao_Impl.getRequiredConverters());
        map.put(SleepDao.class, SleepDao_Impl.getRequiredConverters());
        map.put(SportRecordDao.class, SportRecordDao_Impl.getRequiredConverters());
        map.put(StepDao.class, StepDao_Impl.getRequiredConverters());
        map.put(UricAcidDao.class, UricAcidDao_Impl.getRequiredConverters());
        return map;
    }

    @Override // androidx.room.RoomDatabase
    public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
        return new HashSet();
    }

    @Override // androidx.room.RoomDatabase
    public List<Migration> getAutoMigrations(final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
        return new ArrayList();
    }

    @Override // com.yucheng.smarthealthpro.database.room.AppDatabase
    public BloodKetonesDao bloodKetonesDao() {
        BloodKetonesDao bloodKetonesDao;
        if (this._bloodKetonesDao != null) {
            return this._bloodKetonesDao;
        }
        synchronized (this) {
            if (this._bloodKetonesDao == null) {
                this._bloodKetonesDao = new BloodKetonesDao_Impl(this);
            }
            bloodKetonesDao = this._bloodKetonesDao;
        }
        return bloodKetonesDao;
    }

    @Override // com.yucheng.smarthealthpro.database.room.AppDatabase
    public BloodLipidsDao bloodLipidsDao() {
        BloodLipidsDao bloodLipidsDao;
        if (this._bloodLipidsDao != null) {
            return this._bloodLipidsDao;
        }
        synchronized (this) {
            if (this._bloodLipidsDao == null) {
                this._bloodLipidsDao = new BloodLipidsDao_Impl(this);
            }
            bloodLipidsDao = this._bloodLipidsDao;
        }
        return bloodLipidsDao;
    }

    @Override // com.yucheng.smarthealthpro.database.room.AppDatabase
    public BloodPressureDao bloodPressureDao() {
        BloodPressureDao bloodPressureDao;
        if (this._bloodPressureDao != null) {
            return this._bloodPressureDao;
        }
        synchronized (this) {
            if (this._bloodPressureDao == null) {
                this._bloodPressureDao = new BloodPressureDao_Impl(this);
            }
            bloodPressureDao = this._bloodPressureDao;
        }
        return bloodPressureDao;
    }

    @Override // com.yucheng.smarthealthpro.database.room.AppDatabase
    public BodyDataDao bodyDataDao() {
        BodyDataDao bodyDataDao;
        if (this._bodyDataDao != null) {
            return this._bodyDataDao;
        }
        synchronized (this) {
            if (this._bodyDataDao == null) {
                this._bodyDataDao = new BodyDataDao_Impl(this);
            }
            bodyDataDao = this._bodyDataDao;
        }
        return bodyDataDao;
    }

    @Override // com.yucheng.smarthealthpro.database.room.AppDatabase
    public EcgMeasureDao ecgMeasureDao() {
        EcgMeasureDao ecgMeasureDao;
        if (this._ecgMeasureDao != null) {
            return this._ecgMeasureDao;
        }
        synchronized (this) {
            if (this._ecgMeasureDao == null) {
                this._ecgMeasureDao = new EcgMeasureDao_Impl(this);
            }
            ecgMeasureDao = this._ecgMeasureDao;
        }
        return ecgMeasureDao;
    }

    @Override // com.yucheng.smarthealthpro.database.room.AppDatabase
    public HealthMetricDao healthMetricDao() {
        HealthMetricDao healthMetricDao;
        if (this._healthMetricDao != null) {
            return this._healthMetricDao;
        }
        synchronized (this) {
            if (this._healthMetricDao == null) {
                this._healthMetricDao = new HealthMetricDao_Impl(this);
            }
            healthMetricDao = this._healthMetricDao;
        }
        return healthMetricDao;
    }

    @Override // com.yucheng.smarthealthpro.database.room.AppDatabase
    public HeartRateDao heartRateDao() {
        HeartRateDao heartRateDao;
        if (this._heartRateDao != null) {
            return this._heartRateDao;
        }
        synchronized (this) {
            if (this._heartRateDao == null) {
                this._heartRateDao = new HeartRateDao_Impl(this);
            }
            heartRateDao = this._heartRateDao;
        }
        return heartRateDao;
    }

    @Override // com.yucheng.smarthealthpro.database.room.AppDatabase
    public MotionPatternDao motionPatternDao() {
        MotionPatternDao motionPatternDao;
        if (this._motionPatternDao != null) {
            return this._motionPatternDao;
        }
        synchronized (this) {
            if (this._motionPatternDao == null) {
                this._motionPatternDao = new MotionPatternDao_Impl(this);
            }
            motionPatternDao = this._motionPatternDao;
        }
        return motionPatternDao;
    }

    @Override // com.yucheng.smarthealthpro.database.room.AppDatabase
    public PhysiotherapyDao physiotherapyDao() {
        PhysiotherapyDao physiotherapyDao;
        if (this._physiotherapyDao != null) {
            return this._physiotherapyDao;
        }
        synchronized (this) {
            if (this._physiotherapyDao == null) {
                this._physiotherapyDao = new PhysiotherapyDao_Impl(this);
            }
            physiotherapyDao = this._physiotherapyDao;
        }
        return physiotherapyDao;
    }

    @Override // com.yucheng.smarthealthpro.database.room.AppDatabase
    public SleepDao sleepDao() {
        SleepDao sleepDao;
        if (this._sleepDao != null) {
            return this._sleepDao;
        }
        synchronized (this) {
            if (this._sleepDao == null) {
                this._sleepDao = new SleepDao_Impl(this);
            }
            sleepDao = this._sleepDao;
        }
        return sleepDao;
    }

    @Override // com.yucheng.smarthealthpro.database.room.AppDatabase
    public SportRecordDao sportRecordDao() {
        SportRecordDao sportRecordDao;
        if (this._sportRecordDao != null) {
            return this._sportRecordDao;
        }
        synchronized (this) {
            if (this._sportRecordDao == null) {
                this._sportRecordDao = new SportRecordDao_Impl(this);
            }
            sportRecordDao = this._sportRecordDao;
        }
        return sportRecordDao;
    }

    @Override // com.yucheng.smarthealthpro.database.room.AppDatabase
    public StepDao stepDao() {
        StepDao stepDao;
        if (this._stepDao != null) {
            return this._stepDao;
        }
        synchronized (this) {
            if (this._stepDao == null) {
                this._stepDao = new StepDao_Impl(this);
            }
            stepDao = this._stepDao;
        }
        return stepDao;
    }

    @Override // com.yucheng.smarthealthpro.database.room.AppDatabase
    public UricAcidDao uricAcidDao() {
        UricAcidDao uricAcidDao;
        if (this._uricAcidDao != null) {
            return this._uricAcidDao;
        }
        synchronized (this) {
            if (this._uricAcidDao == null) {
                this._uricAcidDao = new UricAcidDao_Impl(this);
            }
            uricAcidDao = this._uricAcidDao;
        }
        return uricAcidDao;
    }
}
