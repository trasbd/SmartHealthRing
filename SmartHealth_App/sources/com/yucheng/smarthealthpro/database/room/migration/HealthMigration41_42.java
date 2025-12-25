package com.yucheng.smarthealthpro.database.room.migration;

import android.database.SQLException;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.facebook.appevents.UserDataStore;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HealthMigration.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\u0010\u0010\t\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\u0010\u0010\n\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\u0010\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\u0010\u0010\f\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\u0010\u0010\r\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\u0010\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\u0010\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\u0010\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\u0010\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\u0010\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\u0010\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\u0010\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0002¨\u0006\u0015"}, d2 = {"Lcom/yucheng/smarthealthpro/database/room/migration/HealthMigration41_42;", "Landroidx/room/migration/Migration;", "<init>", "()V", "migrate", "", UserDataStore.DATE_OF_BIRTH, "Landroidx/sqlite/db/SupportSQLiteDatabase;", "migrateUricAcid", "migrateStep", "migrateSportRecord", "migrateSleep", "migratePhysiotherapy", "migrateMotionPattern", "migrateHealthRate", "migrateHealthMetric", "migrateEcgMeasure", "migrateBodyData", "migrateBloodPressure", "migrateBloodLipids", "migrateBloodKetones", "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class HealthMigration41_42 extends Migration {
    public HealthMigration41_42() {
        super(41, 42);
    }

    @Override // androidx.room.migration.Migration
    public void migrate(SupportSQLiteDatabase db) throws SQLException {
        Intrinsics.checkNotNullParameter(db, "db");
        migrateBloodKetones(db);
        migrateBloodLipids(db);
        migrateBloodPressure(db);
        migrateBodyData(db);
        migrateEcgMeasure(db);
        migrateHealthMetric(db);
        migrateHealthRate(db);
        migrateMotionPattern(db);
        migratePhysiotherapy(db);
        migrateSleep(db);
        migrateSportRecord(db);
        migrateStep(db);
        migrateUricAcid(db);
    }

    private final void migrateUricAcid(SupportSQLiteDatabase db) throws SQLException {
        db.execSQL("UPDATE URIC_ACID_DB SET USER_ID = '' WHERE USER_ID IS NULL");
        db.execSQL("UPDATE URIC_ACID_DB SET DEVICE_TYPE = '' WHERE DEVICE_TYPE IS NULL");
        db.execSQL("CREATE TABLE uric_acid_data (\n    id                 INTEGER PRIMARY KEY AUTOINCREMENT,\n    query_id           INTEGER NOT NULL\n                               DEFAULT 0,\n    start_timestamp    INTEGER NOT NULL,\n    time_year_to_day   TEXT    NOT NULL,\n    uric_acid          INTEGER NOT NULL,\n    measure_mode       INTEGER NOT NULL,\n    user_id            TEXT    NOT NULL,\n    device_type        TEXT    NOT NULL,\n    device_mac_address TEXT    NOT NULL,\n    data_group_id      TEXT,\n    is_uploaded        INTEGER NOT NULL,\n    is_other_uploaded  INTEGER NOT NULL\n)        ");
        db.execSQL("CREATE INDEX IF NOT EXISTS index_uric_acid_data_start_timestamp ON uric_acid_data (start_timestamp)");
        db.execSQL("CREATE INDEX IF NOT EXISTS index_uric_acid_data_user_id ON uric_acid_data (user_id)");
        db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS index_uric_acid_data_start_timestamp_device_mac_address ON uric_acid_data (start_timestamp, device_mac_address)");
        db.execSQL("INSERT INTO uric_acid_data (id, query_id, start_timestamp, time_year_to_day, \nuric_acid, measure_mode, \nuser_id, device_type, device_mac_address, is_uploaded, is_other_uploaded, data_group_id)\nSELECT _id, QUERY_ID, URIC_ACID_START_TIME, TIME_YEAR_TO_DATE, URIC_ACID, URIC_ACID_MODEL, \nUSER_ID, DEVICE_TYPE, DEVICE_MAC, IS_UPLOAD, IS_OTHER_UPLOAD, BELONG_DATA_GROUP_ID\nFROM URIC_ACID_DB");
        db.execSQL("DROP TABLE IF EXISTS URIC_ACID_DB");
    }

    private final void migrateStep(SupportSQLiteDatabase db) throws SQLException {
        db.execSQL("UPDATE STEP_DP SET USER_ID = '' WHERE USER_ID IS NULL");
        db.execSQL("UPDATE STEP_DP SET DEVICE_TYPE = '' WHERE DEVICE_TYPE IS NULL");
        db.execSQL("CREATE TABLE step_data (\n    id                 INTEGER PRIMARY KEY AUTOINCREMENT,\n    query_id           INTEGER NOT NULL\n                               DEFAULT 0,\n    start_timestamp    INTEGER NOT NULL,\n    end_timestamp      INTEGER NOT NULL,\n    time_year_to_day   TEXT    NOT NULL,\n    step_count         INTEGER NOT NULL,\n    distance_meters    INTEGER NOT NULL,\n    calories           INTEGER NOT NULL,\n    user_id            TEXT    NOT NULL,\n    device_type        TEXT    NOT NULL,\n    device_mac_address TEXT    NOT NULL,\n    data_group_id      TEXT,\n    is_uploaded        INTEGER NOT NULL,\n    is_other_uploaded  INTEGER NOT NULL\n)        ");
        db.execSQL("CREATE INDEX IF NOT EXISTS index_step_data_start_timestamp ON step_data (start_timestamp)");
        db.execSQL("CREATE INDEX IF NOT EXISTS index_step_data_user_id ON step_data (user_id)");
        db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS index_step_data_start_timestamp_device_mac_address ON step_data (start_timestamp, device_mac_address)");
        db.execSQL("INSERT INTO step_data (id, query_id, start_timestamp, end_timestamp, time_year_to_day, \nstep_count, distance_meters, calories,  \nuser_id, device_type, device_mac_address, is_uploaded, is_other_uploaded, data_group_id)\nSELECT _id, QUERY_ID, SPORT_START_TIME, SPORT_END_TIME, TIME_YEAR_TO_DATE, \nSPORT_STEP, SPORT_DISTANCE, SPORT_CALORIE, \nUSER_ID, DEVICE_TYPE, DEVICE_MAC, IS_UPLOAD, IS_OTHER_UPLOAD, BELONG_DATA_GROUP_ID\nFROM STEP_DP");
        db.execSQL("DROP TABLE IF EXISTS STEP_DP");
    }

    private final void migrateSportRecord(SupportSQLiteDatabase db) throws SQLException {
        db.execSQL("UPDATE RUN_DB SET USER_ID = '' WHERE USER_ID IS NULL");
        db.execSQL("UPDATE RUN_DB SET DEVICE_TYPE = '' WHERE DEVICE_TYPE IS NULL");
        db.execSQL("CREATE TABLE sport_data (\n    id                 INTEGER PRIMARY KEY AUTOINCREMENT,\n    query_id           INTEGER NOT NULL\n                               DEFAULT 0,\n    activity_type      INTEGER NOT NULL,\n    start_timestamp    INTEGER NOT NULL,\n    time_year_to_day   TEXT    NOT NULL,\n    total_steps        INTEGER NOT NULL,\n    total_distance     REAL    NOT NULL,\n    last_distance      REAL    NOT NULL,\n    total_calories     INTEGER NOT NULL,\n    last_calories      INTEGER NOT NULL,\n    pace_per_km        TEXT,\n    avg_heart_rate     INTEGER NOT NULL,\n    duration_seconds   INTEGER NOT NULL,\n    speed_kmh          REAL    NOT NULL,\n    start_coordinates  TEXT,\n    end_coordinates    TEXT,\n    path_coordinates   TEXT,\n    user_id            TEXT    NOT NULL,\n    device_type        TEXT    NOT NULL,\n    device_mac_address TEXT    NOT NULL,\n    data_group_id      TEXT,\n    is_uploaded        INTEGER NOT NULL,\n    is_other_uploaded  INTEGER NOT NULL\n)        ");
        db.execSQL("CREATE INDEX IF NOT EXISTS index_sport_data_start_timestamp ON sport_data (start_timestamp)");
        db.execSQL("CREATE INDEX IF NOT EXISTS index_sport_data_user_id ON sport_data (user_id)");
        db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS index_sport_data_start_timestamp_device_mac_address ON sport_data (start_timestamp, device_mac_address)");
        db.execSQL("INSERT INTO sport_data (id, query_id, start_timestamp, time_year_to_day, \nactivity_type, total_steps, total_distance, last_distance, total_calories, last_calories, \npace_per_km, avg_heart_rate, duration_seconds, speed_kmh, start_coordinates, end_coordinates, path_coordinates,  \nuser_id, device_type, device_mac_address, is_uploaded, is_other_uploaded, data_group_id)\nSELECT _id, QUERY_ID, BEGIN_DATE, TIME_YEAR_TO_DATE, TYPE, SPORT_STEP, DISTANCE, START_DISTANCE, CALORIE, START_CALORIE, \nMINKM, HEART, RUN_TIME, KMH, START_POINT, END_POINT, PATH_LINE_POINTS, \nUSER_ID, DEVICE_TYPE, DEVICE_MAC, IS_UPLOAD, IS_OTHER_UPLOAD, BELONG_DATA_GROUP_ID\nFROM RUN_DB");
        db.execSQL("DROP TABLE IF EXISTS RUN_DB");
    }

    private final void migrateSleep(SupportSQLiteDatabase db) throws SQLException {
        db.execSQL("UPDATE SLEEP_DB SET USER_ID = '' WHERE USER_ID IS NULL");
        db.execSQL("UPDATE SLEEP_DB SET DEVICE_TYPE = '' WHERE DEVICE_TYPE IS NULL");
        db.execSQL("CREATE TABLE sleep_data (\n    id                        INTEGER PRIMARY KEY AUTOINCREMENT,\n    query_id                  INTEGER NOT NULL\n                                      DEFAULT 0,\n    start_timestamp           INTEGER NOT NULL,\n    end_timestamp             INTEGER NOT NULL,\n    time_year_to_day          TEXT    NOT NULL,\n    deep_sleep_count          INTEGER NOT NULL,\n    light_sleep_count         INTEGER NOT NULL,\n    deep_sleep_total_seconds  INTEGER NOT NULL,\n    light_sleep_total_seconds INTEGER NOT NULL,\n    rem_total_seconds         INTEGER NOT NULL,\n    wake_count                INTEGER NOT NULL,\n    wake_duration_seconds     INTEGER NOT NULL,\n    sleep_stages_json         TEXT,\n    user_id                   TEXT    NOT NULL,\n    device_type               TEXT    NOT NULL,\n    device_mac_address        TEXT    NOT NULL,\n    data_group_id             TEXT,\n    is_uploaded               INTEGER NOT NULL,\n    is_other_uploaded         INTEGER NOT NULL\n)       ");
        db.execSQL("CREATE INDEX IF NOT EXISTS index_sleep_data_start_timestamp ON sleep_data (start_timestamp)");
        db.execSQL("CREATE INDEX IF NOT EXISTS index_sleep_data_user_id ON sleep_data (user_id)");
        db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS index_sleep_data_start_timestamp_device_mac_address ON sleep_data (start_timestamp, device_mac_address)");
        db.execSQL("INSERT INTO sleep_data (id, query_id, start_timestamp, end_timestamp, time_year_to_day, \ndeep_sleep_count, light_sleep_count, deep_sleep_total_seconds, light_sleep_total_seconds, rem_total_seconds, \nwake_count, wake_duration_seconds, sleep_stages_json, \nuser_id, device_type, device_mac_address, is_uploaded, is_other_uploaded, data_group_id)\nSELECT _id, QUERY_ID, START_TIME, END_TIME, TIME_YEAR_TO_DATE, DEEP_SLEEP_TOTAL,  \nLIGHT_SLEEP_TOTAL, DEEP_SLEEP_TOTAL, LIGHT_SLEEP_TOTAL, RAPID_EYE_MOVEMENT_TOTAL, WAKE_COUNT, WAKE_DURATION, \nSLEEP_DATA, \nUSER_ID, DEVICE_TYPE, DEVICE_MAC, IS_UPLOAD, IS_OTHER_UPLOAD, DATA_GROUP_ID\nFROM SLEEP_DB");
        db.execSQL("DROP TABLE IF EXISTS SLEEP_DB");
    }

    private final void migratePhysiotherapy(SupportSQLiteDatabase db) throws SQLException {
        db.execSQL("UPDATE PHYSIOTHERAPY_DB SET USER_ID = '' WHERE USER_ID IS NULL");
        db.execSQL("UPDATE PHYSIOTHERAPY_DB SET DEVICE_TYPE = '' WHERE DEVICE_TYPE IS NULL");
        db.execSQL("CREATE TABLE physiotherapy_data (\n    id                 INTEGER PRIMARY KEY AUTOINCREMENT,\n    query_id           INTEGER NOT NULL\n                               DEFAULT 0,\n    start_timestamp    INTEGER NOT NULL,\n    time_year_to_day   TEXT    NOT NULL,\n    duration           INTEGER NOT NULL,\n    type               INTEGER NOT NULL,\n    start_type         INTEGER NOT NULL,\n    power_level        INTEGER NOT NULL,\n    duration_level     INTEGER NOT NULL,\n    user_id            TEXT    NOT NULL,\n    device_type        TEXT    NOT NULL,\n    device_mac_address TEXT    NOT NULL,\n    data_group_id      TEXT,\n    is_uploaded        INTEGER NOT NULL\n)         ");
        db.execSQL("CREATE INDEX IF NOT EXISTS index_physiotherapy_data_start_timestamp ON physiotherapy_data (start_timestamp)");
        db.execSQL("CREATE INDEX IF NOT EXISTS index_physiotherapy_data_user_id ON physiotherapy_data (user_id)");
        db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS index_physiotherapy_data_start_timestamp_device_mac_address ON physiotherapy_data (start_timestamp, device_mac_address)");
        db.execSQL("INSERT INTO physiotherapy_data (id, query_id, start_timestamp, time_year_to_day, \nduration, type, start_type, power_level, duration_level, \nuser_id, device_type, device_mac_address, is_uploaded, data_group_id)\nSELECT _id, QUERY_ID, START_TIME, '', DURATION, TYPE, START_TYPE, POWER_LEVEL, DURATION_LEVEL, \nUSER_ID, DEVICE_TYPE, DEVICE_MAC, IS_UPLOAD, BELONG_DATA_GROUP_ID\nFROM PHYSIOTHERAPY_DB");
        db.execSQL("DROP TABLE IF EXISTS PHYSIOTHERAPY_DB");
    }

    private final void migrateMotionPattern(SupportSQLiteDatabase db) throws SQLException {
        db.execSQL("UPDATE MOTION_PATTERN_DB SET USER_ID = '' WHERE USER_ID IS NULL");
        db.execSQL("UPDATE MOTION_PATTERN_DB SET DEVICE_TYPE = '' WHERE DEVICE_TYPE IS NULL");
        db.execSQL("CREATE TABLE motion_pattern_data (\n    id                 INTEGER PRIMARY KEY AUTOINCREMENT,\n    query_id           INTEGER NOT NULL\n                               DEFAULT 0,\n    start_timestamp    INTEGER NOT NULL,\n    end_timestamp      INTEGER NOT NULL,\n    time_year_to_day   TEXT    NOT NULL,\n    sport_steps        INTEGER NOT NULL,\n    sport_distances    INTEGER NOT NULL,\n    sport_calories     INTEGER NOT NULL,\n    sport_mode         INTEGER NOT NULL,\n    start_method       INTEGER NOT NULL,\n    sport_heart_rate   INTEGER NOT NULL,\n    sport_duration     INTEGER NOT NULL,\n    min_heart_rate     INTEGER NOT NULL,\n    max_heart_rate     INTEGER NOT NULL,\n    user_id            TEXT    NOT NULL,\n    device_type        TEXT    NOT NULL,\n    device_mac_address TEXT    NOT NULL,\n    data_group_id      TEXT,\n    is_uploaded        INTEGER NOT NULL,\n    is_other_uploaded  INTEGER NOT NULL\n)         ");
        db.execSQL("CREATE INDEX IF NOT EXISTS index_motion_pattern_data_start_timestamp ON motion_pattern_data (start_timestamp)");
        db.execSQL("CREATE INDEX IF NOT EXISTS index_motion_pattern_data_user_id ON motion_pattern_data (user_id)");
        db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS index_motion_pattern_data_start_timestamp_device_mac_address ON motion_pattern_data (start_timestamp, device_mac_address)");
        db.execSQL("INSERT INTO motion_pattern_data (id, query_id, start_timestamp, end_timestamp, time_year_to_day, \nsport_steps, sport_distances, sport_calories, sport_mode, start_method, sport_heart_rate, sport_duration, \nmin_heart_rate, max_heart_rate, \nuser_id, device_type, device_mac_address, is_uploaded, is_other_uploaded, data_group_id)\nSELECT _id, QUERY_ID, START_TIME, END_TIME, '', SPORT_STEPS, SPORT_DISTANCES, SPORT_CALORIES, SPORT_MODE, START_METHOD, \nSPORT_HEART_RATE, SPORT_TIME, MIN_HEART_RATE, MAX_HEART_RATE, \nUSER_ID, DEVICE_TYPE, DEVICE_MAC, 0, 0, BELONG_DATA_GROUP_ID\nFROM MOTION_PATTERN_DB");
        db.execSQL("DROP TABLE IF EXISTS MOTION_PATTERN_DB");
    }

    private final void migrateHealthRate(SupportSQLiteDatabase db) throws SQLException {
        db.execSQL("UPDATE HEART_DB SET USER_ID = '' WHERE USER_ID IS NULL");
        db.execSQL("UPDATE HEART_DB SET DEVICE_TYPE = '' WHERE DEVICE_TYPE IS NULL");
        db.execSQL("CREATE TABLE heart_rate_data (\n    id                 INTEGER PRIMARY KEY AUTOINCREMENT,\n    query_id           INTEGER NOT NULL\n                               DEFAULT 0,\n    start_timestamp    INTEGER NOT NULL,\n    time_year_to_day   TEXT    NOT NULL,\n    heart_rate         INTEGER NOT NULL,\n    user_id            TEXT    NOT NULL,\n    device_type        TEXT    NOT NULL,\n    device_mac_address TEXT    NOT NULL,\n    data_group_id      TEXT,\n    is_uploaded        INTEGER NOT NULL,\n    is_other_uploaded  INTEGER NOT NULL\n)          ");
        db.execSQL("CREATE INDEX IF NOT EXISTS index_heart_rate_data_start_timestamp ON heart_rate_data (start_timestamp)");
        db.execSQL("CREATE INDEX IF NOT EXISTS index_heart_rate_data_user_id ON heart_rate_data (user_id)");
        db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS index_heart_rate_data_start_timestamp_device_mac_address ON heart_rate_data (start_timestamp, device_mac_address)");
        db.execSQL("INSERT INTO heart_rate_data (id, query_id, start_timestamp, time_year_to_day, \nheart_rate, \nuser_id, device_type, device_mac_address, is_uploaded, is_other_uploaded, data_group_id)\nSELECT _id, QUERY_ID, HEART_START_TIME, TIME_YEAR_TO_DATE, HEART_VALUE,  \nUSER_ID, DEVICE_TYPE, DEVICE_MAC, IS_UPLOAD, IS_OTHER_UPLOAD, BELONG_DATA_GROUP_ID\nFROM HEART_DB");
        db.execSQL("DROP TABLE IF EXISTS HEART_DB");
    }

    private final void migrateHealthMetric(SupportSQLiteDatabase db) throws SQLException {
        db.execSQL("UPDATE ALL_DB SET USER_ID = '' WHERE USER_ID IS NULL");
        db.execSQL("UPDATE ALL_DB SET DEVICE_TYPE = '' WHERE DEVICE_TYPE IS NULL");
        db.execSQL("CREATE TABLE health_metrics_data (\n    id                                 INTEGER PRIMARY KEY AUTOINCREMENT,\n    query_id                           INTEGER NOT NULL\n                                               DEFAULT 0,\n    start_timestamp                    INTEGER NOT NULL,\n    time_year_to_day                   TEXT    NOT NULL,\n    heart_rate_value                   INTEGER NOT NULL,\n    hrv_value                          INTEGER NOT NULL,\n    cvrr_value                         INTEGER NOT NULL,\n    blood_oxygen_level                 INTEGER NOT NULL,\n    step_count                         INTEGER NOT NULL,\n    diastolic_bp                       INTEGER NOT NULL,\n    systolic_bp                        INTEGER NOT NULL,\n    respiratory_rate                   INTEGER NOT NULL,\n    temperature_integer_part           INTEGER NOT NULL,\n    temperature_fractional_part        INTEGER NOT NULL,\n    body_fat_integer_part              INTEGER NOT NULL,\n    body_fat_fractional_part           INTEGER NOT NULL,\n    blood_sugar_level                  INTEGER NOT NULL,\n    blood_sugar_measurement_mode       INTEGER NOT NULL,\n    user_id                            TEXT    NOT NULL,\n    device_type                        TEXT    NOT NULL,\n    device_mac_address                 TEXT    NOT NULL,\n    data_group_id                      TEXT,\n    is_hrv_uploaded                    INTEGER NOT NULL,\n    is_blood_oxygen_uploaded           INTEGER NOT NULL,\n    is_respiratory_rate_uploaded       INTEGER NOT NULL,\n    is_temperature_uploaded            INTEGER NOT NULL,\n    is_body_fat_uploaded               INTEGER NOT NULL,\n    is_blood_sugar_uploaded            INTEGER NOT NULL,\n    is_other_hrv_uploaded              INTEGER NOT NULL,\n    is_other_blood_oxygen_uploaded     INTEGER NOT NULL,\n    is_other_respiratory_rate_uploaded INTEGER NOT NULL,\n    is_other_temperature_uploaded      INTEGER NOT NULL,\n    is_other_body_fat_uploaded         INTEGER NOT NULL,\n    is_other_blood_sugar_uploaded      INTEGER NOT NULL\n)      ");
        db.execSQL("CREATE INDEX IF NOT EXISTS index_health_metrics_data_start_timestamp ON health_metrics_data (start_timestamp)");
        db.execSQL("CREATE INDEX IF NOT EXISTS index_health_metrics_data_user_id ON health_metrics_data (user_id)");
        db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS index_health_metrics_data_start_timestamp_device_mac_address ON health_metrics_data (start_timestamp, device_mac_address)");
        db.execSQL("INSERT INTO health_metrics_data (id, query_id, start_timestamp, time_year_to_day, \nheart_rate_value, hrv_value, cvrr_value, blood_oxygen_level, step_count, diastolic_bp, systolic_bp, \nrespiratory_rate, temperature_integer_part, temperature_fractional_part, \nbody_fat_integer_part, body_fat_fractional_part, blood_sugar_level, blood_sugar_measurement_mode, \nuser_id, device_type, device_mac_address, is_hrv_uploaded, is_blood_oxygen_uploaded,\nis_respiratory_rate_uploaded, is_temperature_uploaded, is_body_fat_uploaded, is_blood_sugar_uploaded, \ndata_group_id, is_other_hrv_uploaded, is_other_blood_oxygen_uploaded, is_other_respiratory_rate_uploaded, \nis_other_temperature_uploaded, is_other_body_fat_uploaded, is_other_blood_sugar_uploaded)\nSELECT _id, QUERY_ID, START_TIME, TIME_YEAR_TO_DATE, HEART_VALUE, HRV_VALUE, CVRR_VALUE, OOVALUE, \nSTEP_VALUE, DBPVALUE, SBPVALUE, RESPIRATORY_RATE_VALUE, TEMP_INT_VALUE, TEMP_FLOAT_VALUE, \nBODY_FAT_INTEGER, BODY_FAT_DOUBLE, BLOOD_SUGAR, BLOOD_SUGAR_MODEL, \nUSER_ID, DEVICE_TYPE, DEVICE_MAC, IS_HRV_UPLOAD, IS_BLOOD_UPLOAD, IS_AW_RRUPLOAD, IS_TEMP_UPLOAD, \nIS_BODY_FAT_UPLOAD, IS_BLOOD_SUGAR_UPLOAD, BELONG_DATA_GROUP_ID, 0, 0, 0, 0, 0, 0\nFROM ALL_DB");
        db.execSQL("DROP TABLE IF EXISTS ALL_DB");
    }

    private final void migrateEcgMeasure(SupportSQLiteDatabase db) throws SQLException {
        db.execSQL("UPDATE ECG_MEASURE_DB SET USER_ID = '' WHERE USER_ID IS NULL");
        db.execSQL("UPDATE ECG_MEASURE_DB SET DEVICE_TYPE = '' WHERE DEVICE_TYPE IS NULL");
        db.execSQL("UPDATE ECG_MEASURE_DB SET TIME_YEAR_TO_DATE = '' WHERE TIME_YEAR_TO_DATE IS NULL");
        db.execSQL("CREATE TABLE ecg_measure_data (\n    id                 INTEGER PRIMARY KEY AUTOINCREMENT,\n    query_id           INTEGER NOT NULL\n                               DEFAULT 0,\n    start_timestamp    INTEGER NOT NULL,\n    time_year_to_day   TEXT    NOT NULL,\n    hrv_value          INTEGER NOT NULL,\n    heart_rate         INTEGER NOT NULL,\n    max_bp             INTEGER NOT NULL,\n    min_bp             INTEGER NOT NULL,\n    measure_data       TEXT    NOT NULL,\n    age                INTEGER NOT NULL,\n    sex                INTEGER NOT NULL,\n    is_afib            INTEGER NOT NULL,\n    diagnose_type      INTEGER NOT NULL,\n    health_norm        TEXT,\n    user_id            TEXT    NOT NULL,\n    device_type        TEXT    NOT NULL,\n    device_mac_address TEXT    NOT NULL,\n    data_group_id      TEXT,\n    is_uploaded        INTEGER NOT NULL,\n    is_other_uploaded  INTEGER NOT NULL\n)       ");
        db.execSQL("CREATE INDEX IF NOT EXISTS index_ecg_measure_data_start_timestamp ON ecg_measure_data (start_timestamp)");
        db.execSQL("CREATE INDEX IF NOT EXISTS index_ecg_measure_data_user_id ON ecg_measure_data (user_id)");
        db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS index_ecg_measure_data_start_timestamp_device_mac_address ON ecg_measure_data (start_timestamp, device_mac_address)");
        db.execSQL("INSERT INTO ecg_measure_data (id, query_id, start_timestamp, time_year_to_day, \nhrv_value, heart_rate, max_bp, min_bp, \nmeasure_data, age, sex, is_afib, diagnose_type, health_norm,\nuser_id, device_type, device_mac_address, is_uploaded, is_other_uploaded, data_group_id)\nSELECT _id, QUERY_ID, TIME, TIME_YEAR_TO_DATE, HRV, HEART, MAX_BP, MIN_BP, \nMEASURE_DATA, AGE, SEX, IS_AFIB, DIAGNOSE_TYPE, HEALTH_NORM, \nUSER_ID, DEVICE_TYPE, DEVICE_MAC, IS_UPLOAD, IS_OTHER_UPLOAD, BELONG_DATA_GROUP_ID\nFROM ECG_MEASURE_DB");
        db.execSQL("DROP TABLE IF EXISTS ECG_MEASURE_DB");
    }

    private final void migrateBodyData(SupportSQLiteDatabase db) throws SQLException {
        db.execSQL("UPDATE BODY_DATA_DB SET USER_ID = '' WHERE USER_ID IS NULL");
        db.execSQL("UPDATE BODY_DATA_DB SET DEVICE_TYPE = '' WHERE DEVICE_TYPE IS NULL");
        db.execSQL("CREATE TABLE body_data (\n    id                          INTEGER PRIMARY KEY AUTOINCREMENT,\n    query_id                    INTEGER NOT NULL\n                                        DEFAULT 0,\n    start_timestamp             INTEGER NOT NULL,\n    time_year_to_day            TEXT    NOT NULL,\n    load_index_integer_part     INTEGER NOT NULL,\n    load_index_fractional_part  INTEGER NOT NULL,\n    hrv_integer_part            INTEGER NOT NULL,\n    hrv_fractional_part         INTEGER NOT NULL,\n    pressure_integer_part       INTEGER NOT NULL,\n    pressure_fractional_part    INTEGER NOT NULL,\n    body_state_integer_part     INTEGER NOT NULL,\n    body_state_fractional_part  INTEGER NOT NULL,\n    sympathetic_integer_part    INTEGER NOT NULL,\n    sympathetic_fractional_part INTEGER NOT NULL,\n    sdn_value                   INTEGER NOT NULL,\n    maximal_oxygen_intake       INTEGER NOT NULL,\n    user_id                     TEXT    NOT NULL,\n    device_type                 TEXT    NOT NULL,\n    device_mac_address          TEXT    NOT NULL,\n    data_group_id               TEXT,\n    is_uploaded                 INTEGER NOT NULL,\n    is_other_uploaded           INTEGER NOT NULL\n)          ");
        db.execSQL("CREATE INDEX IF NOT EXISTS index_body_data_start_timestamp ON body_data (start_timestamp)");
        db.execSQL("CREATE INDEX IF NOT EXISTS index_body_data_user_id ON body_data (user_id)");
        db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS index_body_data_start_timestamp_device_mac_address ON body_data (start_timestamp, device_mac_address)");
        db.execSQL("INSERT INTO body_data (id, query_id, start_timestamp, time_year_to_day, \nload_index_integer_part, load_index_fractional_part, hrv_integer_part, hrv_fractional_part, \npressure_integer_part, pressure_fractional_part, body_state_integer_part, body_state_fractional_part, \nsympathetic_integer_part, sympathetic_fractional_part, sdn_value, maximal_oxygen_intake, \nuser_id, device_type, device_mac_address, is_uploaded, is_other_uploaded, data_group_id)\nSELECT _id, QUERY_ID, TIME, TIME_YEAR_TO_DATE, LOAD_INDEX_INTEGER, LOAD_INDEX_FLOAT, HRV_INTEGER, HRV_FLOAT, \nPRESSURE_INTEGER, PRESSURE_FLOAT, BODY_INTEGER, BODY_FLOAT, SYMPATHETIC_INTEGER, SYMPATHETIC_FLOAT, SDN, MAXIMAL_OXYGEN_INTAKE, \nUSER_ID, DEVICE_TYPE, DEVICE_MAC, IS_UPLOAD, 0, BELONG_DATA_GROUP_ID\nFROM BODY_DATA_DB");
        db.execSQL("DROP TABLE IF EXISTS BODY_DATA_DB");
    }

    private final void migrateBloodPressure(SupportSQLiteDatabase db) throws SQLException {
        db.execSQL("UPDATE BLOOD_DB SET USER_ID = '' WHERE USER_ID IS NULL");
        db.execSQL("UPDATE BLOOD_DB SET DEVICE_TYPE = '' WHERE DEVICE_TYPE IS NULL");
        db.execSQL("CREATE TABLE blood_pressure_data (\n    id                 INTEGER PRIMARY KEY AUTOINCREMENT,\n    query_id           INTEGER NOT NULL\n                               DEFAULT 0,\n    start_timestamp    INTEGER NOT NULL,\n    time_year_to_day   TEXT    NOT NULL,\n    diastolic_bp       INTEGER NOT NULL,\n    systolic_bp        INTEGER NOT NULL,\n    measure_mode       INTEGER NOT NULL,\n    user_id            TEXT    NOT NULL,\n    device_type        TEXT    NOT NULL,\n    device_mac_address TEXT    NOT NULL,\n    data_group_id      TEXT,\n    is_uploaded        INTEGER NOT NULL,\n    is_other_uploaded  INTEGER NOT NULL\n)           ");
        db.execSQL("CREATE INDEX IF NOT EXISTS index_blood_pressure_data_start_timestamp ON blood_pressure_data (start_timestamp)");
        db.execSQL("CREATE INDEX IF NOT EXISTS index_blood_pressure_data_user_id ON blood_pressure_data (user_id)");
        db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS index_blood_pressure_data_start_timestamp_device_mac_address ON blood_pressure_data (start_timestamp, device_mac_address)");
        db.execSQL("INSERT INTO blood_pressure_data (id, query_id, start_timestamp, time_year_to_day, \ndiastolic_bp, systolic_bp,\nmeasure_mode, user_id, device_type, device_mac_address, is_uploaded, is_other_uploaded, data_group_id)\nSELECT _id, QUERY_ID, BLOOD_START_TIME, TIME_YEAR_TO_DATE, BLOOD_DBP, BLOOD_SBP,\nIS_INFLATED, USER_ID, DEVICE_TYPE, DEVICE_MAC, IS_UPLOAD, IS_OTHER_UPLOAD, BELONG_DATA_GROUP_ID\nFROM BLOOD_DB");
        db.execSQL("DROP TABLE IF EXISTS BLOOD_DB");
    }

    private final void migrateBloodLipids(SupportSQLiteDatabase db) throws SQLException {
        db.execSQL("UPDATE BLOOD_FAT_DB SET USER_ID = '' WHERE USER_ID IS NULL");
        db.execSQL("UPDATE BLOOD_FAT_DB SET DEVICE_TYPE = '' WHERE DEVICE_TYPE IS NULL");
        db.execSQL("CREATE TABLE blood_lipids_data (\n    id                                           INTEGER PRIMARY KEY AUTOINCREMENT,\n    query_id                                     INTEGER NOT NULL\n                                                         DEFAULT 0,\n    start_timestamp                              INTEGER NOT NULL,\n    time_year_to_day                             TEXT    NOT NULL,\n    cholesterol_integer_part                     INTEGER NOT NULL,\n    cholesterol_fractional_part                  INTEGER NOT NULL,\n    triglyceride_integer_part                    INTEGER NOT NULL,\n    triglyceride_fractional_part                 INTEGER NOT NULL,\n    high_lipoprotein_cholesterol_integer_part    INTEGER NOT NULL,\n    high_lipoprotein_cholesterol_fractional_part INTEGER NOT NULL,\n    low_lipoprotein_cholesterol_integer_part     INTEGER NOT NULL,\n    low_lipoprotein_cholesterol_fractional_part  INTEGER NOT NULL,\n    measure_mode                                 INTEGER NOT NULL,\n    user_id                                      TEXT    NOT NULL,\n    device_type                                  TEXT    NOT NULL,\n    device_mac_address                           TEXT    NOT NULL,\n    data_group_id                                TEXT,\n    is_uploaded                                  INTEGER NOT NULL,\n    is_other_uploaded                            INTEGER NOT NULL\n)           ");
        db.execSQL("CREATE INDEX IF NOT EXISTS index_blood_lipids_data_start_timestamp ON blood_lipids_data (start_timestamp)");
        db.execSQL("CREATE INDEX IF NOT EXISTS index_blood_lipids_data_user_id ON blood_lipids_data (user_id)");
        db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS index_blood_lipids_data_start_timestamp_device_mac_address ON blood_lipids_data (start_timestamp, device_mac_address)");
        db.execSQL("INSERT INTO blood_lipids_data (id, query_id, start_timestamp, time_year_to_day, \ncholesterol_integer_part, cholesterol_fractional_part, triglyceride_integer_part, triglyceride_fractional_part, \nhigh_lipoprotein_cholesterol_integer_part, high_lipoprotein_cholesterol_fractional_part, \nlow_lipoprotein_cholesterol_integer_part, low_lipoprotein_cholesterol_fractional_part, \nmeasure_mode, user_id, device_type, device_mac_address, is_uploaded, is_other_uploaded)\nSELECT _id, QUERY_ID, BLOOD_FAT_START_TIME, TIME_YEAR_TO_DATE, CHOLESTEROL_INTEGER, CHOLESTEROL_FLOAT,\nTRIGLYCERIDE_CHOLESTEROL_INTEGER, TRIGLYCERIDE_CHOLESTEROL_FLOAT, \nHIGH_LIPOPROTEIN_CHOLESTEROL_INTEGER, HIGH_LIPOPROTEIN_CHOLESTEROL_FLOAT, \nLOW_LIPOPROTEIN_CHOLESTEROL_INTEGER, LOW_LIPOPROTEIN_CHOLESTEROL_FLOAT, \nBLOOD_FAT_MODEL, USER_ID, DEVICE_TYPE, DEVICE_MAC, IS_UPLOAD, IS_OTHER_UPLOAD\nFROM BLOOD_FAT_DB");
        db.execSQL("DROP TABLE IF EXISTS BLOOD_FAT_DB");
    }

    private final void migrateBloodKetones(SupportSQLiteDatabase db) throws SQLException {
        db.execSQL("UPDATE BLOOD_KETONES_DB SET USER_ID = '' WHERE USER_ID IS NULL");
        db.execSQL("UPDATE BLOOD_KETONES_DB SET DEVICE_TYPE = '' WHERE DEVICE_TYPE IS NULL");
        db.execSQL("CREATE TABLE blood_ketones_data (\n    id                            INTEGER PRIMARY KEY AUTOINCREMENT,\n    query_id                      INTEGER NOT NULL\n                                          DEFAULT 0,\n    start_timestamp               INTEGER NOT NULL,\n    time_year_to_day              TEXT    NOT NULL,\n    blood_ketones_integer_part    INTEGER NOT NULL,\n    blood_ketones_fractional_part INTEGER NOT NULL,\n    measure_mode                  INTEGER NOT NULL,\n    user_id                       TEXT    NOT NULL,\n    device_type                   TEXT    NOT NULL,\n    device_mac_address            TEXT    NOT NULL,\n    data_group_id                 TEXT,\n    is_uploaded                   INTEGER NOT NULL,\n    is_other_uploaded             INTEGER NOT NULL\n)            ");
        db.execSQL("CREATE INDEX IF NOT EXISTS index_blood_ketones_data_start_timestamp ON blood_ketones_data (start_timestamp)");
        db.execSQL("CREATE INDEX IF NOT EXISTS index_blood_ketones_data_user_id ON blood_ketones_data (user_id)");
        db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS index_blood_ketones_data_start_timestamp_device_mac_address ON blood_ketones_data (start_timestamp, device_mac_address)");
        db.execSQL("INSERT INTO blood_ketones_data (id, query_id, start_timestamp, time_year_to_day, \nblood_ketones_integer_part, blood_ketones_fractional_part, measure_mode, \nuser_id, device_type, device_mac_address, is_uploaded, is_other_uploaded)\nSELECT _id, QUERY_ID, START_TIME, TIME_YEAR_TO_DATE, PART_INTEGER, PART_FLOAT, \nMODEL, USER_ID, DEVICE_TYPE, DEVICE_MAC, IS_UPLOAD, IS_OTHER_UPLOAD\nFROM BLOOD_KETONES_DB");
        db.execSQL("DROP TABLE IF EXISTS BLOOD_KETONES_DB");
    }
}
