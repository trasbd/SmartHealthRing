package com.yucheng.smarthealthpro.database.room.dao;

import androidx.room.EntityDeleteOrUpdateAdapter;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteConnectionUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.SQLiteStatement;
import com.facebook.AccessToken;
import com.yucheng.smarthealthpro.database.room.bean.DataGroupIdUpdate;
import com.yucheng.smarthealthpro.database.room.bean.HealthMetric;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;

/* loaded from: classes4.dex */
public final class HealthMetricDao_Impl implements HealthMetricDao {
    private final RoomDatabase __db;
    private final EntityInsertAdapter<HealthMetric> __insertAdapterOfHealthMetric = new EntityInsertAdapter<HealthMetric>() { // from class: com.yucheng.smarthealthpro.database.room.dao.HealthMetricDao_Impl.1
        @Override // androidx.room.EntityInsertAdapter
        protected String createQuery() {
            return "INSERT OR REPLACE INTO `health_metrics_data` (`id`,`query_id`,`start_timestamp`,`time_year_to_day`,`heart_rate_value`,`hrv_value`,`cvrr_value`,`blood_oxygen_level`,`step_count`,`diastolic_bp`,`systolic_bp`,`respiratory_rate`,`temperature_integer_part`,`temperature_fractional_part`,`body_fat_integer_part`,`body_fat_fractional_part`,`blood_sugar_level`,`blood_sugar_measurement_mode`,`user_id`,`device_type`,`device_mac_address`,`data_group_id`,`is_hrv_uploaded`,`is_blood_oxygen_uploaded`,`is_respiratory_rate_uploaded`,`is_temperature_uploaded`,`is_body_fat_uploaded`,`is_blood_sugar_uploaded`,`is_other_hrv_uploaded`,`is_other_blood_oxygen_uploaded`,`is_other_respiratory_rate_uploaded`,`is_other_temperature_uploaded`,`is_other_body_fat_uploaded`,`is_other_blood_sugar_uploaded`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // androidx.room.EntityInsertAdapter
        public void bind(SQLiteStatement sQLiteStatement, HealthMetric healthMetric) {
            if (healthMetric.getId() == null) {
                sQLiteStatement.mo182bindNull(1);
            } else {
                sQLiteStatement.mo181bindLong(1, healthMetric.getId().longValue());
            }
            sQLiteStatement.mo181bindLong(2, healthMetric.getQueryID());
            sQLiteStatement.mo181bindLong(3, healthMetric.getStartTimestamp());
            if (healthMetric.getTimeYearToDay() == null) {
                sQLiteStatement.mo182bindNull(4);
            } else {
                sQLiteStatement.mo183bindText(4, healthMetric.getTimeYearToDay());
            }
            sQLiteStatement.mo181bindLong(5, healthMetric.getHeartRate());
            sQLiteStatement.mo181bindLong(6, healthMetric.getHeartRateVariability());
            sQLiteStatement.mo181bindLong(7, healthMetric.getCvrr());
            sQLiteStatement.mo181bindLong(8, healthMetric.getBloodOxygenLevel());
            sQLiteStatement.mo181bindLong(9, healthMetric.getStepCount());
            sQLiteStatement.mo181bindLong(10, healthMetric.getDiastolicBloodPressure());
            sQLiteStatement.mo181bindLong(11, healthMetric.getSystolicBloodPressure());
            sQLiteStatement.mo181bindLong(12, healthMetric.getRespiratoryRate());
            sQLiteStatement.mo181bindLong(13, healthMetric.getTemperatureInteger());
            sQLiteStatement.mo181bindLong(14, healthMetric.getTemperatureFraction());
            sQLiteStatement.mo181bindLong(15, healthMetric.getBodyFatInteger());
            sQLiteStatement.mo181bindLong(16, healthMetric.getBodyFatFraction());
            sQLiteStatement.mo181bindLong(17, healthMetric.getBloodSugarLevel());
            sQLiteStatement.mo181bindLong(18, healthMetric.getBloodSugarMode());
            if (healthMetric.getUserId() == null) {
                sQLiteStatement.mo182bindNull(19);
            } else {
                sQLiteStatement.mo183bindText(19, healthMetric.getUserId());
            }
            if (healthMetric.getDeviceType() == null) {
                sQLiteStatement.mo182bindNull(20);
            } else {
                sQLiteStatement.mo183bindText(20, healthMetric.getDeviceType());
            }
            if (healthMetric.getDeviceMacAddress() == null) {
                sQLiteStatement.mo182bindNull(21);
            } else {
                sQLiteStatement.mo183bindText(21, healthMetric.getDeviceMacAddress());
            }
            if (healthMetric.getDataGroupId() == null) {
                sQLiteStatement.mo182bindNull(22);
            } else {
                sQLiteStatement.mo183bindText(22, healthMetric.getDataGroupId());
            }
            sQLiteStatement.mo181bindLong(23, healthMetric.isHrvUploaded() ? 1L : 0L);
            sQLiteStatement.mo181bindLong(24, healthMetric.isBloodOxygenUploaded() ? 1L : 0L);
            sQLiteStatement.mo181bindLong(25, healthMetric.isRespiratoryRateUploaded() ? 1L : 0L);
            sQLiteStatement.mo181bindLong(26, healthMetric.isTemperatureUploaded() ? 1L : 0L);
            sQLiteStatement.mo181bindLong(27, healthMetric.isBodyFatUploaded() ? 1L : 0L);
            sQLiteStatement.mo181bindLong(28, healthMetric.isBloodSugarUploaded() ? 1L : 0L);
            sQLiteStatement.mo181bindLong(29, healthMetric.isOtherHrvUploaded() ? 1L : 0L);
            sQLiteStatement.mo181bindLong(30, healthMetric.isOtherBloodOxygenUploaded() ? 1L : 0L);
            sQLiteStatement.mo181bindLong(31, healthMetric.isOtherRespiratoryRateUploaded() ? 1L : 0L);
            sQLiteStatement.mo181bindLong(32, healthMetric.isOtherTemperatureUploaded() ? 1L : 0L);
            sQLiteStatement.mo181bindLong(33, healthMetric.isOtherBodyFatUploaded() ? 1L : 0L);
            sQLiteStatement.mo181bindLong(34, healthMetric.isOtherBloodSugarUploaded() ? 1L : 0L);
        }
    };
    private final EntityDeleteOrUpdateAdapter<HealthMetric> __updateAdapterOfHealthMetric = new EntityDeleteOrUpdateAdapter<HealthMetric>() { // from class: com.yucheng.smarthealthpro.database.room.dao.HealthMetricDao_Impl.2
        @Override // androidx.room.EntityDeleteOrUpdateAdapter
        protected String createQuery() {
            return "UPDATE OR ABORT `health_metrics_data` SET `id` = ?,`query_id` = ?,`start_timestamp` = ?,`time_year_to_day` = ?,`heart_rate_value` = ?,`hrv_value` = ?,`cvrr_value` = ?,`blood_oxygen_level` = ?,`step_count` = ?,`diastolic_bp` = ?,`systolic_bp` = ?,`respiratory_rate` = ?,`temperature_integer_part` = ?,`temperature_fractional_part` = ?,`body_fat_integer_part` = ?,`body_fat_fractional_part` = ?,`blood_sugar_level` = ?,`blood_sugar_measurement_mode` = ?,`user_id` = ?,`device_type` = ?,`device_mac_address` = ?,`data_group_id` = ?,`is_hrv_uploaded` = ?,`is_blood_oxygen_uploaded` = ?,`is_respiratory_rate_uploaded` = ?,`is_temperature_uploaded` = ?,`is_body_fat_uploaded` = ?,`is_blood_sugar_uploaded` = ?,`is_other_hrv_uploaded` = ?,`is_other_blood_oxygen_uploaded` = ?,`is_other_respiratory_rate_uploaded` = ?,`is_other_temperature_uploaded` = ?,`is_other_body_fat_uploaded` = ?,`is_other_blood_sugar_uploaded` = ? WHERE `id` = ?";
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // androidx.room.EntityDeleteOrUpdateAdapter
        public void bind(SQLiteStatement sQLiteStatement, HealthMetric healthMetric) {
            if (healthMetric.getId() == null) {
                sQLiteStatement.mo182bindNull(1);
            } else {
                sQLiteStatement.mo181bindLong(1, healthMetric.getId().longValue());
            }
            sQLiteStatement.mo181bindLong(2, healthMetric.getQueryID());
            sQLiteStatement.mo181bindLong(3, healthMetric.getStartTimestamp());
            if (healthMetric.getTimeYearToDay() == null) {
                sQLiteStatement.mo182bindNull(4);
            } else {
                sQLiteStatement.mo183bindText(4, healthMetric.getTimeYearToDay());
            }
            sQLiteStatement.mo181bindLong(5, healthMetric.getHeartRate());
            sQLiteStatement.mo181bindLong(6, healthMetric.getHeartRateVariability());
            sQLiteStatement.mo181bindLong(7, healthMetric.getCvrr());
            sQLiteStatement.mo181bindLong(8, healthMetric.getBloodOxygenLevel());
            sQLiteStatement.mo181bindLong(9, healthMetric.getStepCount());
            sQLiteStatement.mo181bindLong(10, healthMetric.getDiastolicBloodPressure());
            sQLiteStatement.mo181bindLong(11, healthMetric.getSystolicBloodPressure());
            sQLiteStatement.mo181bindLong(12, healthMetric.getRespiratoryRate());
            sQLiteStatement.mo181bindLong(13, healthMetric.getTemperatureInteger());
            sQLiteStatement.mo181bindLong(14, healthMetric.getTemperatureFraction());
            sQLiteStatement.mo181bindLong(15, healthMetric.getBodyFatInteger());
            sQLiteStatement.mo181bindLong(16, healthMetric.getBodyFatFraction());
            sQLiteStatement.mo181bindLong(17, healthMetric.getBloodSugarLevel());
            sQLiteStatement.mo181bindLong(18, healthMetric.getBloodSugarMode());
            if (healthMetric.getUserId() == null) {
                sQLiteStatement.mo182bindNull(19);
            } else {
                sQLiteStatement.mo183bindText(19, healthMetric.getUserId());
            }
            if (healthMetric.getDeviceType() == null) {
                sQLiteStatement.mo182bindNull(20);
            } else {
                sQLiteStatement.mo183bindText(20, healthMetric.getDeviceType());
            }
            if (healthMetric.getDeviceMacAddress() == null) {
                sQLiteStatement.mo182bindNull(21);
            } else {
                sQLiteStatement.mo183bindText(21, healthMetric.getDeviceMacAddress());
            }
            if (healthMetric.getDataGroupId() == null) {
                sQLiteStatement.mo182bindNull(22);
            } else {
                sQLiteStatement.mo183bindText(22, healthMetric.getDataGroupId());
            }
            sQLiteStatement.mo181bindLong(23, healthMetric.isHrvUploaded() ? 1L : 0L);
            sQLiteStatement.mo181bindLong(24, healthMetric.isBloodOxygenUploaded() ? 1L : 0L);
            sQLiteStatement.mo181bindLong(25, healthMetric.isRespiratoryRateUploaded() ? 1L : 0L);
            sQLiteStatement.mo181bindLong(26, healthMetric.isTemperatureUploaded() ? 1L : 0L);
            sQLiteStatement.mo181bindLong(27, healthMetric.isBodyFatUploaded() ? 1L : 0L);
            sQLiteStatement.mo181bindLong(28, healthMetric.isBloodSugarUploaded() ? 1L : 0L);
            sQLiteStatement.mo181bindLong(29, healthMetric.isOtherHrvUploaded() ? 1L : 0L);
            sQLiteStatement.mo181bindLong(30, healthMetric.isOtherBloodOxygenUploaded() ? 1L : 0L);
            sQLiteStatement.mo181bindLong(31, healthMetric.isOtherRespiratoryRateUploaded() ? 1L : 0L);
            sQLiteStatement.mo181bindLong(32, healthMetric.isOtherTemperatureUploaded() ? 1L : 0L);
            sQLiteStatement.mo181bindLong(33, healthMetric.isOtherBodyFatUploaded() ? 1L : 0L);
            sQLiteStatement.mo181bindLong(34, healthMetric.isOtherBloodSugarUploaded() ? 1L : 0L);
            if (healthMetric.getId() == null) {
                sQLiteStatement.mo182bindNull(35);
            } else {
                sQLiteStatement.mo181bindLong(35, healthMetric.getId().longValue());
            }
        }
    };
    private final EntityDeleteOrUpdateAdapter<DataGroupIdUpdate> __updateAdapterOfDataGroupIdUpdateAsHealthMetric = new EntityDeleteOrUpdateAdapter<DataGroupIdUpdate>() { // from class: com.yucheng.smarthealthpro.database.room.dao.HealthMetricDao_Impl.3
        @Override // androidx.room.EntityDeleteOrUpdateAdapter
        protected String createQuery() {
            return "UPDATE OR ABORT `health_metrics_data` SET `id` = ?,`data_group_id` = ? WHERE `id` = ?";
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // androidx.room.EntityDeleteOrUpdateAdapter
        public void bind(final SQLiteStatement statement, final DataGroupIdUpdate entity) {
            statement.mo181bindLong(1, entity.getId());
            if (entity.getDataGroupId() == null) {
                statement.mo182bindNull(2);
            } else {
                statement.mo183bindText(2, entity.getDataGroupId());
            }
            statement.mo181bindLong(3, entity.getId());
        }
    };

    public HealthMetricDao_Impl(final RoomDatabase __db) {
        this.__db = __db;
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.HealthMetricDao
    public Object insert(final HealthMetric metric, final Continuation<? super Long> arg1) {
        metric.getClass();
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.HealthMetricDao_Impl$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f$0.lambda$insert$0(metric, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Long lambda$insert$0(HealthMetric healthMetric, SQLiteConnection sQLiteConnection) {
        return Long.valueOf(this.__insertAdapterOfHealthMetric.insertAndReturnId(sQLiteConnection, healthMetric));
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.HealthMetricDao
    public Object insertAll(final List<HealthMetric> metrics, final Continuation<? super List<Long>> arg1) {
        metrics.getClass();
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.HealthMetricDao_Impl$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f$0.lambda$insertAll$1(metrics, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ List lambda$insertAll$1(List list, SQLiteConnection sQLiteConnection) {
        return this.__insertAdapterOfHealthMetric.insertAndReturnIdsList(sQLiteConnection, list);
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.HealthMetricDao
    public Object update(final HealthMetric metric, final Continuation<? super Unit> arg1) {
        metric.getClass();
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.HealthMetricDao_Impl$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f$0.lambda$update$2(metric, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Unit lambda$update$2(HealthMetric healthMetric, SQLiteConnection sQLiteConnection) throws Exception {
        this.__updateAdapterOfHealthMetric.handle(sQLiteConnection, healthMetric);
        return Unit.INSTANCE;
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.HealthMetricDao
    public Object updateDataGroupIds(final List<DataGroupIdUpdate> updates, final Continuation<? super Unit> arg1) {
        updates.getClass();
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.HealthMetricDao_Impl$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f$0.lambda$updateDataGroupIds$3(updates, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Unit lambda$updateDataGroupIds$3(List list, SQLiteConnection sQLiteConnection) throws Exception {
        this.__updateAdapterOfDataGroupIdUpdateAsHealthMetric.handleMultiple(sQLiteConnection, list);
        return Unit.INSTANCE;
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.HealthMetricDao
    public Object getById(final long id, final Continuation<? super HealthMetric> arg1) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.HealthMetricDao_Impl$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return HealthMetricDao_Impl.lambda$getById$4(id, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    static /* synthetic */ HealthMetric lambda$getById$4(long j2, SQLiteConnection sQLiteConnection) {
        String text;
        int i2;
        String text2;
        int i3;
        String text3;
        int i4;
        int i5;
        boolean z;
        int i6;
        boolean z2;
        int i7;
        boolean z3;
        int i8;
        boolean z4;
        int i9;
        boolean z5;
        int i10;
        boolean z6;
        int i11;
        boolean z7;
        int i12;
        boolean z8;
        int i13;
        boolean z9;
        int i14;
        boolean z10;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("SELECT * FROM health_metrics_data WHERE id = ?");
        try {
            sQLiteStatementPrepare.mo181bindLong(1, j2);
            int columnIndexOrThrow = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "id");
            int columnIndexOrThrow2 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "query_id");
            int columnIndexOrThrow3 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "start_timestamp");
            int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "time_year_to_day");
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "heart_rate_value");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "hrv_value");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "cvrr_value");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "blood_oxygen_level");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "step_count");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "diastolic_bp");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "systolic_bp");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "respiratory_rate");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "temperature_integer_part");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "temperature_fractional_part");
            int columnIndexOrThrow15 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "body_fat_integer_part");
            int columnIndexOrThrow16 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "body_fat_fractional_part");
            int columnIndexOrThrow17 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "blood_sugar_level");
            int columnIndexOrThrow18 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "blood_sugar_measurement_mode");
            int columnIndexOrThrow19 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow20 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow21 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow22 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow23 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_hrv_uploaded");
            int columnIndexOrThrow24 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_blood_oxygen_uploaded");
            int columnIndexOrThrow25 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_respiratory_rate_uploaded");
            int columnIndexOrThrow26 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_temperature_uploaded");
            int columnIndexOrThrow27 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_body_fat_uploaded");
            int columnIndexOrThrow28 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_blood_sugar_uploaded");
            int columnIndexOrThrow29 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_hrv_uploaded");
            int columnIndexOrThrow30 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_blood_oxygen_uploaded");
            int columnIndexOrThrow31 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_respiratory_rate_uploaded");
            int columnIndexOrThrow32 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_temperature_uploaded");
            int columnIndexOrThrow33 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_body_fat_uploaded");
            int columnIndexOrThrow34 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_blood_sugar_uploaded");
            HealthMetric healthMetric = null;
            if (sQLiteStatementPrepare.step()) {
                Long lValueOf = sQLiteStatementPrepare.isNull(columnIndexOrThrow) ? null : Long.valueOf(sQLiteStatementPrepare.getLong(columnIndexOrThrow));
                int i15 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
                long j3 = sQLiteStatementPrepare.getLong(columnIndexOrThrow3);
                String text4 = sQLiteStatementPrepare.isNull(columnIndexOrThrow4) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow4);
                int i16 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5);
                int i17 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                int i18 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7);
                int i19 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow8);
                int i20 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow9);
                int i21 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10);
                int i22 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11);
                int i23 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12);
                int i24 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow13);
                int i25 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow14);
                int i26 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow15);
                int i27 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow16);
                int i28 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow17);
                int i29 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow18);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow19)) {
                    i2 = columnIndexOrThrow20;
                    text = null;
                } else {
                    text = sQLiteStatementPrepare.getText(columnIndexOrThrow19);
                    i2 = columnIndexOrThrow20;
                }
                if (sQLiteStatementPrepare.isNull(i2)) {
                    i3 = columnIndexOrThrow21;
                    text2 = null;
                } else {
                    text2 = sQLiteStatementPrepare.getText(i2);
                    i3 = columnIndexOrThrow21;
                }
                if (sQLiteStatementPrepare.isNull(i3)) {
                    i4 = columnIndexOrThrow22;
                    text3 = null;
                } else {
                    text3 = sQLiteStatementPrepare.getText(i3);
                    i4 = columnIndexOrThrow22;
                }
                String text5 = sQLiteStatementPrepare.isNull(i4) ? null : sQLiteStatementPrepare.getText(i4);
                if (((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow23)) != 0) {
                    i5 = columnIndexOrThrow24;
                    z = true;
                } else {
                    i5 = columnIndexOrThrow24;
                    z = false;
                }
                if (((int) sQLiteStatementPrepare.getLong(i5)) != 0) {
                    i6 = columnIndexOrThrow25;
                    z2 = true;
                } else {
                    i6 = columnIndexOrThrow25;
                    z2 = false;
                }
                if (((int) sQLiteStatementPrepare.getLong(i6)) != 0) {
                    i7 = columnIndexOrThrow26;
                    z3 = true;
                } else {
                    i7 = columnIndexOrThrow26;
                    z3 = false;
                }
                if (((int) sQLiteStatementPrepare.getLong(i7)) != 0) {
                    i8 = columnIndexOrThrow27;
                    z4 = true;
                } else {
                    i8 = columnIndexOrThrow27;
                    z4 = false;
                }
                if (((int) sQLiteStatementPrepare.getLong(i8)) != 0) {
                    i9 = columnIndexOrThrow28;
                    z5 = true;
                } else {
                    i9 = columnIndexOrThrow28;
                    z5 = false;
                }
                if (((int) sQLiteStatementPrepare.getLong(i9)) != 0) {
                    i10 = columnIndexOrThrow29;
                    z6 = true;
                } else {
                    i10 = columnIndexOrThrow29;
                    z6 = false;
                }
                if (((int) sQLiteStatementPrepare.getLong(i10)) != 0) {
                    i11 = columnIndexOrThrow30;
                    z7 = true;
                } else {
                    i11 = columnIndexOrThrow30;
                    z7 = false;
                }
                if (((int) sQLiteStatementPrepare.getLong(i11)) != 0) {
                    i12 = columnIndexOrThrow31;
                    z8 = true;
                } else {
                    i12 = columnIndexOrThrow31;
                    z8 = false;
                }
                if (((int) sQLiteStatementPrepare.getLong(i12)) != 0) {
                    i13 = columnIndexOrThrow32;
                    z9 = true;
                } else {
                    i13 = columnIndexOrThrow32;
                    z9 = false;
                }
                if (((int) sQLiteStatementPrepare.getLong(i13)) != 0) {
                    i14 = columnIndexOrThrow33;
                    z10 = true;
                } else {
                    i14 = columnIndexOrThrow33;
                    z10 = false;
                }
                healthMetric = new HealthMetric(lValueOf, i15, j3, text4, i16, i17, i18, i19, i20, i21, i22, i23, i24, i25, i26, i27, i28, i29, text, text2, text3, text5, z, z2, z3, z4, z5, z6, z7, z8, z9, z10, ((int) sQLiteStatementPrepare.getLong(i14)) != 0, ((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow34)) != 0);
            }
            return healthMetric;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.HealthMetricDao
    public Object getByUser(final String userId, final Continuation<? super List<HealthMetric>> arg1) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.HealthMetricDao_Impl$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return HealthMetricDao_Impl.lambda$getByUser$5(userId, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    static /* synthetic */ List lambda$getByUser$5(String str, SQLiteConnection sQLiteConnection) {
        int i2;
        Long lValueOf;
        int i3;
        int i4;
        String text;
        int i5;
        String text2;
        int i6;
        int i7;
        String text3;
        int i8;
        String text4;
        int i9;
        int i10;
        boolean z;
        int i11;
        boolean z2;
        int i12;
        boolean z3;
        int i13;
        boolean z4;
        int i14;
        boolean z5;
        int i15;
        boolean z6;
        int i16;
        boolean z7;
        int i17;
        boolean z8;
        int i18;
        boolean z9;
        int i19;
        boolean z10;
        int i20;
        boolean z11;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("SELECT * FROM health_metrics_data WHERE user_id = ? ORDER BY start_timestamp DESC");
        try {
            if (str == null) {
                sQLiteStatementPrepare.mo182bindNull(1);
            } else {
                sQLiteStatementPrepare.mo183bindText(1, str);
            }
            int columnIndexOrThrow = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "id");
            int columnIndexOrThrow2 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "query_id");
            int columnIndexOrThrow3 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "start_timestamp");
            int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "time_year_to_day");
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "heart_rate_value");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "hrv_value");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "cvrr_value");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "blood_oxygen_level");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "step_count");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "diastolic_bp");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "systolic_bp");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "respiratory_rate");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "temperature_integer_part");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "temperature_fractional_part");
            int columnIndexOrThrow15 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "body_fat_integer_part");
            int columnIndexOrThrow16 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "body_fat_fractional_part");
            int columnIndexOrThrow17 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "blood_sugar_level");
            int columnIndexOrThrow18 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "blood_sugar_measurement_mode");
            int columnIndexOrThrow19 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow20 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow21 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow22 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow23 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_hrv_uploaded");
            int columnIndexOrThrow24 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_blood_oxygen_uploaded");
            int columnIndexOrThrow25 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_respiratory_rate_uploaded");
            int columnIndexOrThrow26 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_temperature_uploaded");
            int columnIndexOrThrow27 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_body_fat_uploaded");
            int columnIndexOrThrow28 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_blood_sugar_uploaded");
            int columnIndexOrThrow29 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_hrv_uploaded");
            int columnIndexOrThrow30 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_blood_oxygen_uploaded");
            int columnIndexOrThrow31 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_respiratory_rate_uploaded");
            int columnIndexOrThrow32 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_temperature_uploaded");
            int columnIndexOrThrow33 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_body_fat_uploaded");
            int columnIndexOrThrow34 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_blood_sugar_uploaded");
            ArrayList arrayList = new ArrayList();
            while (sQLiteStatementPrepare.step()) {
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow)) {
                    i3 = columnIndexOrThrow13;
                    i2 = columnIndexOrThrow14;
                    lValueOf = null;
                } else {
                    i2 = columnIndexOrThrow14;
                    lValueOf = Long.valueOf(sQLiteStatementPrepare.getLong(columnIndexOrThrow));
                    i3 = columnIndexOrThrow13;
                }
                int i21 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
                long j2 = sQLiteStatementPrepare.getLong(columnIndexOrThrow3);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow4)) {
                    i5 = columnIndexOrThrow2;
                    i4 = columnIndexOrThrow3;
                    text = null;
                } else {
                    i4 = columnIndexOrThrow3;
                    text = sQLiteStatementPrepare.getText(columnIndexOrThrow4);
                    i5 = columnIndexOrThrow2;
                }
                int i22 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5);
                int i23 = columnIndexOrThrow4;
                int i24 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                int i25 = columnIndexOrThrow5;
                int i26 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7);
                int i27 = columnIndexOrThrow6;
                int i28 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow8);
                int i29 = columnIndexOrThrow7;
                int i30 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow9);
                int i31 = columnIndexOrThrow8;
                int i32 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10);
                int i33 = columnIndexOrThrow9;
                int i34 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11);
                int i35 = columnIndexOrThrow10;
                int i36 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12);
                int i37 = columnIndexOrThrow12;
                int i38 = i3;
                int i39 = columnIndexOrThrow11;
                int i40 = (int) sQLiteStatementPrepare.getLong(i38);
                int i41 = i2;
                int i42 = (int) sQLiteStatementPrepare.getLong(i41);
                int i43 = columnIndexOrThrow15;
                int i44 = (int) sQLiteStatementPrepare.getLong(i43);
                columnIndexOrThrow15 = i43;
                int i45 = columnIndexOrThrow16;
                int i46 = (int) sQLiteStatementPrepare.getLong(i45);
                int i47 = columnIndexOrThrow17;
                int i48 = (int) sQLiteStatementPrepare.getLong(i47);
                int i49 = columnIndexOrThrow18;
                int i50 = (int) sQLiteStatementPrepare.getLong(i49);
                int i51 = columnIndexOrThrow19;
                if (sQLiteStatementPrepare.isNull(i51)) {
                    i6 = columnIndexOrThrow;
                    i7 = columnIndexOrThrow20;
                    text2 = null;
                } else {
                    text2 = sQLiteStatementPrepare.getText(i51);
                    i6 = columnIndexOrThrow;
                    i7 = columnIndexOrThrow20;
                }
                if (sQLiteStatementPrepare.isNull(i7)) {
                    columnIndexOrThrow20 = i7;
                    i8 = columnIndexOrThrow21;
                    text3 = null;
                } else {
                    text3 = sQLiteStatementPrepare.getText(i7);
                    columnIndexOrThrow20 = i7;
                    i8 = columnIndexOrThrow21;
                }
                if (sQLiteStatementPrepare.isNull(i8)) {
                    columnIndexOrThrow21 = i8;
                    i9 = columnIndexOrThrow22;
                    text4 = null;
                } else {
                    text4 = sQLiteStatementPrepare.getText(i8);
                    columnIndexOrThrow21 = i8;
                    i9 = columnIndexOrThrow22;
                }
                String text5 = sQLiteStatementPrepare.isNull(i9) ? null : sQLiteStatementPrepare.getText(i9);
                columnIndexOrThrow22 = i9;
                int i52 = columnIndexOrThrow23;
                String str2 = text5;
                if (((int) sQLiteStatementPrepare.getLong(i52)) != 0) {
                    i10 = columnIndexOrThrow24;
                    z = true;
                } else {
                    i10 = columnIndexOrThrow24;
                    z = false;
                }
                if (((int) sQLiteStatementPrepare.getLong(i10)) != 0) {
                    i11 = columnIndexOrThrow25;
                    z2 = true;
                } else {
                    i11 = columnIndexOrThrow25;
                    z2 = false;
                }
                int i53 = i10;
                if (((int) sQLiteStatementPrepare.getLong(i11)) != 0) {
                    i12 = columnIndexOrThrow26;
                    z3 = true;
                } else {
                    i12 = columnIndexOrThrow26;
                    z3 = false;
                }
                int i54 = i11;
                if (((int) sQLiteStatementPrepare.getLong(i12)) != 0) {
                    i13 = columnIndexOrThrow27;
                    z4 = true;
                } else {
                    i13 = columnIndexOrThrow27;
                    z4 = false;
                }
                int i55 = i12;
                if (((int) sQLiteStatementPrepare.getLong(i13)) != 0) {
                    i14 = columnIndexOrThrow28;
                    z5 = true;
                } else {
                    i14 = columnIndexOrThrow28;
                    z5 = false;
                }
                int i56 = i13;
                if (((int) sQLiteStatementPrepare.getLong(i14)) != 0) {
                    i15 = columnIndexOrThrow29;
                    z6 = true;
                } else {
                    i15 = columnIndexOrThrow29;
                    z6 = false;
                }
                int i57 = i14;
                if (((int) sQLiteStatementPrepare.getLong(i15)) != 0) {
                    i16 = columnIndexOrThrow30;
                    z7 = true;
                } else {
                    i16 = columnIndexOrThrow30;
                    z7 = false;
                }
                int i58 = i15;
                if (((int) sQLiteStatementPrepare.getLong(i16)) != 0) {
                    i17 = columnIndexOrThrow31;
                    z8 = true;
                } else {
                    i17 = columnIndexOrThrow31;
                    z8 = false;
                }
                int i59 = i16;
                if (((int) sQLiteStatementPrepare.getLong(i17)) != 0) {
                    i18 = columnIndexOrThrow32;
                    z9 = true;
                } else {
                    i18 = columnIndexOrThrow32;
                    z9 = false;
                }
                int i60 = i17;
                if (((int) sQLiteStatementPrepare.getLong(i18)) != 0) {
                    i19 = columnIndexOrThrow33;
                    z10 = true;
                } else {
                    i19 = columnIndexOrThrow33;
                    z10 = false;
                }
                int i61 = i18;
                if (((int) sQLiteStatementPrepare.getLong(i19)) != 0) {
                    i20 = columnIndexOrThrow34;
                    z11 = true;
                } else {
                    i20 = columnIndexOrThrow34;
                    z11 = false;
                }
                int i62 = i19;
                arrayList.add(new HealthMetric(lValueOf, i21, j2, text, i22, i24, i26, i28, i30, i32, i34, i36, i40, i42, i44, i46, i48, i50, text2, text3, text4, str2, z, z2, z3, z4, z5, z6, z7, z8, z9, z10, z11, ((int) sQLiteStatementPrepare.getLong(i20)) != 0));
                columnIndexOrThrow2 = i5;
                columnIndexOrThrow14 = i41;
                columnIndexOrThrow16 = i45;
                columnIndexOrThrow18 = i49;
                columnIndexOrThrow17 = i47;
                columnIndexOrThrow24 = i53;
                columnIndexOrThrow25 = i54;
                columnIndexOrThrow27 = i56;
                columnIndexOrThrow28 = i57;
                columnIndexOrThrow26 = i55;
                columnIndexOrThrow30 = i59;
                columnIndexOrThrow31 = i60;
                columnIndexOrThrow29 = i58;
                columnIndexOrThrow33 = i62;
                columnIndexOrThrow13 = i38;
                columnIndexOrThrow3 = i4;
                columnIndexOrThrow4 = i23;
                columnIndexOrThrow5 = i25;
                columnIndexOrThrow6 = i27;
                columnIndexOrThrow7 = i29;
                columnIndexOrThrow8 = i31;
                columnIndexOrThrow9 = i33;
                columnIndexOrThrow10 = i35;
                columnIndexOrThrow23 = i52;
                columnIndexOrThrow34 = i20;
                columnIndexOrThrow32 = i61;
                columnIndexOrThrow = i6;
                columnIndexOrThrow11 = i39;
                columnIndexOrThrow19 = i51;
                columnIndexOrThrow12 = i37;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.HealthMetricDao
    public Object getByStartTimestamp(final long startTimestamp, final Continuation<? super List<HealthMetric>> arg1) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.HealthMetricDao_Impl$$ExternalSyntheticLambda24
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return HealthMetricDao_Impl.lambda$getByStartTimestamp$6(startTimestamp, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    static /* synthetic */ List lambda$getByStartTimestamp$6(long j2, SQLiteConnection sQLiteConnection) {
        int i2;
        Long lValueOf;
        int i3;
        int i4;
        String text;
        int i5;
        String text2;
        int i6;
        int i7;
        String text3;
        int i8;
        String text4;
        int i9;
        int i10;
        boolean z;
        int i11;
        boolean z2;
        int i12;
        boolean z3;
        int i13;
        boolean z4;
        int i14;
        boolean z5;
        int i15;
        boolean z6;
        int i16;
        boolean z7;
        int i17;
        boolean z8;
        int i18;
        boolean z9;
        int i19;
        boolean z10;
        int i20;
        boolean z11;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("SELECT * FROM health_metrics_data WHERE start_timestamp = ?");
        try {
            sQLiteStatementPrepare.mo181bindLong(1, j2);
            int columnIndexOrThrow = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "id");
            int columnIndexOrThrow2 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "query_id");
            int columnIndexOrThrow3 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "start_timestamp");
            int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "time_year_to_day");
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "heart_rate_value");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "hrv_value");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "cvrr_value");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "blood_oxygen_level");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "step_count");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "diastolic_bp");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "systolic_bp");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "respiratory_rate");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "temperature_integer_part");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "temperature_fractional_part");
            int columnIndexOrThrow15 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "body_fat_integer_part");
            int columnIndexOrThrow16 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "body_fat_fractional_part");
            int columnIndexOrThrow17 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "blood_sugar_level");
            int columnIndexOrThrow18 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "blood_sugar_measurement_mode");
            int columnIndexOrThrow19 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow20 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow21 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow22 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow23 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_hrv_uploaded");
            int columnIndexOrThrow24 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_blood_oxygen_uploaded");
            int columnIndexOrThrow25 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_respiratory_rate_uploaded");
            int columnIndexOrThrow26 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_temperature_uploaded");
            int columnIndexOrThrow27 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_body_fat_uploaded");
            int columnIndexOrThrow28 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_blood_sugar_uploaded");
            int columnIndexOrThrow29 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_hrv_uploaded");
            int columnIndexOrThrow30 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_blood_oxygen_uploaded");
            int columnIndexOrThrow31 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_respiratory_rate_uploaded");
            int columnIndexOrThrow32 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_temperature_uploaded");
            int columnIndexOrThrow33 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_body_fat_uploaded");
            int columnIndexOrThrow34 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_blood_sugar_uploaded");
            ArrayList arrayList = new ArrayList();
            while (sQLiteStatementPrepare.step()) {
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow)) {
                    i3 = columnIndexOrThrow13;
                    i2 = columnIndexOrThrow14;
                    lValueOf = null;
                } else {
                    i2 = columnIndexOrThrow14;
                    lValueOf = Long.valueOf(sQLiteStatementPrepare.getLong(columnIndexOrThrow));
                    i3 = columnIndexOrThrow13;
                }
                int i21 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
                long j3 = sQLiteStatementPrepare.getLong(columnIndexOrThrow3);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow4)) {
                    i5 = columnIndexOrThrow;
                    i4 = columnIndexOrThrow2;
                    text = null;
                } else {
                    i4 = columnIndexOrThrow2;
                    text = sQLiteStatementPrepare.getText(columnIndexOrThrow4);
                    i5 = columnIndexOrThrow;
                }
                int i22 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5);
                int i23 = columnIndexOrThrow3;
                int i24 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                int i25 = columnIndexOrThrow4;
                int i26 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7);
                int i27 = columnIndexOrThrow5;
                int i28 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow8);
                int i29 = columnIndexOrThrow6;
                int i30 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow9);
                int i31 = columnIndexOrThrow7;
                int i32 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10);
                int i33 = columnIndexOrThrow8;
                int i34 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11);
                int i35 = columnIndexOrThrow9;
                int i36 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12);
                int i37 = columnIndexOrThrow11;
                int i38 = i3;
                int i39 = columnIndexOrThrow10;
                int i40 = (int) sQLiteStatementPrepare.getLong(i38);
                int i41 = i2;
                int i42 = (int) sQLiteStatementPrepare.getLong(i41);
                int i43 = columnIndexOrThrow15;
                int i44 = columnIndexOrThrow12;
                int i45 = (int) sQLiteStatementPrepare.getLong(i43);
                int i46 = columnIndexOrThrow16;
                int i47 = (int) sQLiteStatementPrepare.getLong(i46);
                int i48 = columnIndexOrThrow17;
                int i49 = (int) sQLiteStatementPrepare.getLong(i48);
                int i50 = columnIndexOrThrow18;
                int i51 = (int) sQLiteStatementPrepare.getLong(i50);
                int i52 = columnIndexOrThrow19;
                if (sQLiteStatementPrepare.isNull(i52)) {
                    i6 = i50;
                    i7 = columnIndexOrThrow20;
                    text2 = null;
                } else {
                    text2 = sQLiteStatementPrepare.getText(i52);
                    i6 = i50;
                    i7 = columnIndexOrThrow20;
                }
                if (sQLiteStatementPrepare.isNull(i7)) {
                    columnIndexOrThrow20 = i7;
                    i8 = columnIndexOrThrow21;
                    text3 = null;
                } else {
                    text3 = sQLiteStatementPrepare.getText(i7);
                    columnIndexOrThrow20 = i7;
                    i8 = columnIndexOrThrow21;
                }
                if (sQLiteStatementPrepare.isNull(i8)) {
                    columnIndexOrThrow21 = i8;
                    i9 = columnIndexOrThrow22;
                    text4 = null;
                } else {
                    text4 = sQLiteStatementPrepare.getText(i8);
                    columnIndexOrThrow21 = i8;
                    i9 = columnIndexOrThrow22;
                }
                String text5 = sQLiteStatementPrepare.isNull(i9) ? null : sQLiteStatementPrepare.getText(i9);
                columnIndexOrThrow22 = i9;
                int i53 = columnIndexOrThrow23;
                String str = text5;
                if (((int) sQLiteStatementPrepare.getLong(i53)) != 0) {
                    i10 = columnIndexOrThrow24;
                    z = true;
                } else {
                    i10 = columnIndexOrThrow24;
                    z = false;
                }
                if (((int) sQLiteStatementPrepare.getLong(i10)) != 0) {
                    i11 = columnIndexOrThrow25;
                    z2 = true;
                } else {
                    i11 = columnIndexOrThrow25;
                    z2 = false;
                }
                int i54 = i10;
                if (((int) sQLiteStatementPrepare.getLong(i11)) != 0) {
                    i12 = columnIndexOrThrow26;
                    z3 = true;
                } else {
                    i12 = columnIndexOrThrow26;
                    z3 = false;
                }
                int i55 = i11;
                if (((int) sQLiteStatementPrepare.getLong(i12)) != 0) {
                    i13 = columnIndexOrThrow27;
                    z4 = true;
                } else {
                    i13 = columnIndexOrThrow27;
                    z4 = false;
                }
                int i56 = i12;
                if (((int) sQLiteStatementPrepare.getLong(i13)) != 0) {
                    i14 = columnIndexOrThrow28;
                    z5 = true;
                } else {
                    i14 = columnIndexOrThrow28;
                    z5 = false;
                }
                int i57 = i13;
                if (((int) sQLiteStatementPrepare.getLong(i14)) != 0) {
                    i15 = columnIndexOrThrow29;
                    z6 = true;
                } else {
                    i15 = columnIndexOrThrow29;
                    z6 = false;
                }
                int i58 = i14;
                if (((int) sQLiteStatementPrepare.getLong(i15)) != 0) {
                    i16 = columnIndexOrThrow30;
                    z7 = true;
                } else {
                    i16 = columnIndexOrThrow30;
                    z7 = false;
                }
                int i59 = i15;
                if (((int) sQLiteStatementPrepare.getLong(i16)) != 0) {
                    i17 = columnIndexOrThrow31;
                    z8 = true;
                } else {
                    i17 = columnIndexOrThrow31;
                    z8 = false;
                }
                int i60 = i16;
                if (((int) sQLiteStatementPrepare.getLong(i17)) != 0) {
                    i18 = columnIndexOrThrow32;
                    z9 = true;
                } else {
                    i18 = columnIndexOrThrow32;
                    z9 = false;
                }
                int i61 = i17;
                if (((int) sQLiteStatementPrepare.getLong(i18)) != 0) {
                    i19 = columnIndexOrThrow33;
                    z10 = true;
                } else {
                    i19 = columnIndexOrThrow33;
                    z10 = false;
                }
                int i62 = i18;
                if (((int) sQLiteStatementPrepare.getLong(i19)) != 0) {
                    i20 = columnIndexOrThrow34;
                    z11 = true;
                } else {
                    i20 = columnIndexOrThrow34;
                    z11 = false;
                }
                int i63 = i19;
                arrayList.add(new HealthMetric(lValueOf, i21, j3, text, i22, i24, i26, i28, i30, i32, i34, i36, i40, i42, i45, i47, i49, i51, text2, text3, text4, str, z, z2, z3, z4, z5, z6, z7, z8, z9, z10, z11, ((int) sQLiteStatementPrepare.getLong(i20)) != 0));
                columnIndexOrThrow = i5;
                columnIndexOrThrow24 = i54;
                columnIndexOrThrow25 = i55;
                columnIndexOrThrow23 = i53;
                columnIndexOrThrow27 = i57;
                columnIndexOrThrow28 = i58;
                columnIndexOrThrow26 = i56;
                columnIndexOrThrow30 = i60;
                columnIndexOrThrow31 = i61;
                columnIndexOrThrow29 = i59;
                columnIndexOrThrow33 = i63;
                columnIndexOrThrow10 = i39;
                columnIndexOrThrow2 = i4;
                columnIndexOrThrow3 = i23;
                columnIndexOrThrow4 = i25;
                columnIndexOrThrow5 = i27;
                columnIndexOrThrow6 = i29;
                columnIndexOrThrow7 = i31;
                columnIndexOrThrow8 = i33;
                columnIndexOrThrow9 = i35;
                columnIndexOrThrow11 = i37;
                columnIndexOrThrow14 = i41;
                columnIndexOrThrow17 = i48;
                columnIndexOrThrow34 = i20;
                columnIndexOrThrow32 = i62;
                columnIndexOrThrow13 = i38;
                columnIndexOrThrow12 = i44;
                columnIndexOrThrow15 = i43;
                columnIndexOrThrow16 = i46;
                columnIndexOrThrow18 = i6;
                columnIndexOrThrow19 = i52;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.HealthMetricDao
    public Object getByUserId(final String userId, final Continuation<? super List<HealthMetric>> arg1) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.HealthMetricDao_Impl$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return HealthMetricDao_Impl.lambda$getByUserId$7(userId, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    static /* synthetic */ List lambda$getByUserId$7(String str, SQLiteConnection sQLiteConnection) {
        int i2;
        Long lValueOf;
        int i3;
        int i4;
        String text;
        int i5;
        String text2;
        int i6;
        int i7;
        String text3;
        int i8;
        String text4;
        int i9;
        int i10;
        boolean z;
        int i11;
        boolean z2;
        int i12;
        boolean z3;
        int i13;
        boolean z4;
        int i14;
        boolean z5;
        int i15;
        boolean z6;
        int i16;
        boolean z7;
        int i17;
        boolean z8;
        int i18;
        boolean z9;
        int i19;
        boolean z10;
        int i20;
        boolean z11;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("\n        SELECT * FROM health_metrics_data \n        WHERE user_id = ? \n        OR user_id = \"\"\n        OR user_id IS NULL\n        ORDER BY start_timestamp DESC\n    ");
        try {
            if (str == null) {
                sQLiteStatementPrepare.mo182bindNull(1);
            } else {
                sQLiteStatementPrepare.mo183bindText(1, str);
            }
            int columnIndexOrThrow = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "id");
            int columnIndexOrThrow2 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "query_id");
            int columnIndexOrThrow3 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "start_timestamp");
            int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "time_year_to_day");
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "heart_rate_value");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "hrv_value");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "cvrr_value");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "blood_oxygen_level");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "step_count");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "diastolic_bp");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "systolic_bp");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "respiratory_rate");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "temperature_integer_part");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "temperature_fractional_part");
            int columnIndexOrThrow15 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "body_fat_integer_part");
            int columnIndexOrThrow16 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "body_fat_fractional_part");
            int columnIndexOrThrow17 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "blood_sugar_level");
            int columnIndexOrThrow18 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "blood_sugar_measurement_mode");
            int columnIndexOrThrow19 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow20 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow21 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow22 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow23 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_hrv_uploaded");
            int columnIndexOrThrow24 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_blood_oxygen_uploaded");
            int columnIndexOrThrow25 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_respiratory_rate_uploaded");
            int columnIndexOrThrow26 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_temperature_uploaded");
            int columnIndexOrThrow27 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_body_fat_uploaded");
            int columnIndexOrThrow28 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_blood_sugar_uploaded");
            int columnIndexOrThrow29 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_hrv_uploaded");
            int columnIndexOrThrow30 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_blood_oxygen_uploaded");
            int columnIndexOrThrow31 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_respiratory_rate_uploaded");
            int columnIndexOrThrow32 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_temperature_uploaded");
            int columnIndexOrThrow33 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_body_fat_uploaded");
            int columnIndexOrThrow34 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_blood_sugar_uploaded");
            ArrayList arrayList = new ArrayList();
            while (sQLiteStatementPrepare.step()) {
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow)) {
                    i3 = columnIndexOrThrow13;
                    i2 = columnIndexOrThrow14;
                    lValueOf = null;
                } else {
                    i2 = columnIndexOrThrow14;
                    lValueOf = Long.valueOf(sQLiteStatementPrepare.getLong(columnIndexOrThrow));
                    i3 = columnIndexOrThrow13;
                }
                int i21 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
                long j2 = sQLiteStatementPrepare.getLong(columnIndexOrThrow3);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow4)) {
                    i5 = columnIndexOrThrow2;
                    i4 = columnIndexOrThrow3;
                    text = null;
                } else {
                    i4 = columnIndexOrThrow3;
                    text = sQLiteStatementPrepare.getText(columnIndexOrThrow4);
                    i5 = columnIndexOrThrow2;
                }
                int i22 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5);
                int i23 = columnIndexOrThrow4;
                int i24 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                int i25 = columnIndexOrThrow5;
                int i26 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7);
                int i27 = columnIndexOrThrow6;
                int i28 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow8);
                int i29 = columnIndexOrThrow7;
                int i30 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow9);
                int i31 = columnIndexOrThrow8;
                int i32 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10);
                int i33 = columnIndexOrThrow9;
                int i34 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11);
                int i35 = columnIndexOrThrow10;
                int i36 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12);
                int i37 = columnIndexOrThrow12;
                int i38 = i3;
                int i39 = columnIndexOrThrow11;
                int i40 = (int) sQLiteStatementPrepare.getLong(i38);
                int i41 = i2;
                int i42 = (int) sQLiteStatementPrepare.getLong(i41);
                int i43 = columnIndexOrThrow15;
                int i44 = (int) sQLiteStatementPrepare.getLong(i43);
                columnIndexOrThrow15 = i43;
                int i45 = columnIndexOrThrow16;
                int i46 = (int) sQLiteStatementPrepare.getLong(i45);
                int i47 = columnIndexOrThrow17;
                int i48 = (int) sQLiteStatementPrepare.getLong(i47);
                int i49 = columnIndexOrThrow18;
                int i50 = (int) sQLiteStatementPrepare.getLong(i49);
                int i51 = columnIndexOrThrow19;
                if (sQLiteStatementPrepare.isNull(i51)) {
                    i6 = columnIndexOrThrow;
                    i7 = columnIndexOrThrow20;
                    text2 = null;
                } else {
                    text2 = sQLiteStatementPrepare.getText(i51);
                    i6 = columnIndexOrThrow;
                    i7 = columnIndexOrThrow20;
                }
                if (sQLiteStatementPrepare.isNull(i7)) {
                    columnIndexOrThrow20 = i7;
                    i8 = columnIndexOrThrow21;
                    text3 = null;
                } else {
                    text3 = sQLiteStatementPrepare.getText(i7);
                    columnIndexOrThrow20 = i7;
                    i8 = columnIndexOrThrow21;
                }
                if (sQLiteStatementPrepare.isNull(i8)) {
                    columnIndexOrThrow21 = i8;
                    i9 = columnIndexOrThrow22;
                    text4 = null;
                } else {
                    text4 = sQLiteStatementPrepare.getText(i8);
                    columnIndexOrThrow21 = i8;
                    i9 = columnIndexOrThrow22;
                }
                String text5 = sQLiteStatementPrepare.isNull(i9) ? null : sQLiteStatementPrepare.getText(i9);
                columnIndexOrThrow22 = i9;
                int i52 = columnIndexOrThrow23;
                String str2 = text5;
                if (((int) sQLiteStatementPrepare.getLong(i52)) != 0) {
                    i10 = columnIndexOrThrow24;
                    z = true;
                } else {
                    i10 = columnIndexOrThrow24;
                    z = false;
                }
                if (((int) sQLiteStatementPrepare.getLong(i10)) != 0) {
                    i11 = columnIndexOrThrow25;
                    z2 = true;
                } else {
                    i11 = columnIndexOrThrow25;
                    z2 = false;
                }
                int i53 = i10;
                if (((int) sQLiteStatementPrepare.getLong(i11)) != 0) {
                    i12 = columnIndexOrThrow26;
                    z3 = true;
                } else {
                    i12 = columnIndexOrThrow26;
                    z3 = false;
                }
                int i54 = i11;
                if (((int) sQLiteStatementPrepare.getLong(i12)) != 0) {
                    i13 = columnIndexOrThrow27;
                    z4 = true;
                } else {
                    i13 = columnIndexOrThrow27;
                    z4 = false;
                }
                int i55 = i12;
                if (((int) sQLiteStatementPrepare.getLong(i13)) != 0) {
                    i14 = columnIndexOrThrow28;
                    z5 = true;
                } else {
                    i14 = columnIndexOrThrow28;
                    z5 = false;
                }
                int i56 = i13;
                if (((int) sQLiteStatementPrepare.getLong(i14)) != 0) {
                    i15 = columnIndexOrThrow29;
                    z6 = true;
                } else {
                    i15 = columnIndexOrThrow29;
                    z6 = false;
                }
                int i57 = i14;
                if (((int) sQLiteStatementPrepare.getLong(i15)) != 0) {
                    i16 = columnIndexOrThrow30;
                    z7 = true;
                } else {
                    i16 = columnIndexOrThrow30;
                    z7 = false;
                }
                int i58 = i15;
                if (((int) sQLiteStatementPrepare.getLong(i16)) != 0) {
                    i17 = columnIndexOrThrow31;
                    z8 = true;
                } else {
                    i17 = columnIndexOrThrow31;
                    z8 = false;
                }
                int i59 = i16;
                if (((int) sQLiteStatementPrepare.getLong(i17)) != 0) {
                    i18 = columnIndexOrThrow32;
                    z9 = true;
                } else {
                    i18 = columnIndexOrThrow32;
                    z9 = false;
                }
                int i60 = i17;
                if (((int) sQLiteStatementPrepare.getLong(i18)) != 0) {
                    i19 = columnIndexOrThrow33;
                    z10 = true;
                } else {
                    i19 = columnIndexOrThrow33;
                    z10 = false;
                }
                int i61 = i18;
                if (((int) sQLiteStatementPrepare.getLong(i19)) != 0) {
                    i20 = columnIndexOrThrow34;
                    z11 = true;
                } else {
                    i20 = columnIndexOrThrow34;
                    z11 = false;
                }
                int i62 = i19;
                arrayList.add(new HealthMetric(lValueOf, i21, j2, text, i22, i24, i26, i28, i30, i32, i34, i36, i40, i42, i44, i46, i48, i50, text2, text3, text4, str2, z, z2, z3, z4, z5, z6, z7, z8, z9, z10, z11, ((int) sQLiteStatementPrepare.getLong(i20)) != 0));
                columnIndexOrThrow2 = i5;
                columnIndexOrThrow14 = i41;
                columnIndexOrThrow16 = i45;
                columnIndexOrThrow18 = i49;
                columnIndexOrThrow17 = i47;
                columnIndexOrThrow24 = i53;
                columnIndexOrThrow25 = i54;
                columnIndexOrThrow27 = i56;
                columnIndexOrThrow28 = i57;
                columnIndexOrThrow26 = i55;
                columnIndexOrThrow30 = i59;
                columnIndexOrThrow31 = i60;
                columnIndexOrThrow29 = i58;
                columnIndexOrThrow33 = i62;
                columnIndexOrThrow13 = i38;
                columnIndexOrThrow3 = i4;
                columnIndexOrThrow4 = i23;
                columnIndexOrThrow5 = i25;
                columnIndexOrThrow6 = i27;
                columnIndexOrThrow7 = i29;
                columnIndexOrThrow8 = i31;
                columnIndexOrThrow9 = i33;
                columnIndexOrThrow10 = i35;
                columnIndexOrThrow23 = i52;
                columnIndexOrThrow34 = i20;
                columnIndexOrThrow32 = i61;
                columnIndexOrThrow = i6;
                columnIndexOrThrow11 = i39;
                columnIndexOrThrow19 = i51;
                columnIndexOrThrow12 = i37;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.HealthMetricDao
    public Object queryAll(final String userId, final Continuation<? super List<HealthMetric>> arg1) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.HealthMetricDao_Impl$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return HealthMetricDao_Impl.lambda$queryAll$8(userId, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    static /* synthetic */ List lambda$queryAll$8(String str, SQLiteConnection sQLiteConnection) {
        int i2;
        Long lValueOf;
        int i3;
        int i4;
        String text;
        int i5;
        String text2;
        int i6;
        int i7;
        String text3;
        int i8;
        String text4;
        int i9;
        int i10;
        boolean z;
        int i11;
        boolean z2;
        int i12;
        boolean z3;
        int i13;
        boolean z4;
        int i14;
        boolean z5;
        int i15;
        boolean z6;
        int i16;
        boolean z7;
        int i17;
        boolean z8;
        int i18;
        boolean z9;
        int i19;
        boolean z10;
        int i20;
        boolean z11;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("\n        SELECT * FROM health_metrics_data \n        WHERE (user_id = ? \n        OR user_id = \"\"\n        OR user_id IS NULL)\n        ORDER BY start_timestamp DESC\n    ");
        try {
            if (str == null) {
                sQLiteStatementPrepare.mo182bindNull(1);
            } else {
                sQLiteStatementPrepare.mo183bindText(1, str);
            }
            int columnIndexOrThrow = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "id");
            int columnIndexOrThrow2 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "query_id");
            int columnIndexOrThrow3 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "start_timestamp");
            int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "time_year_to_day");
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "heart_rate_value");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "hrv_value");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "cvrr_value");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "blood_oxygen_level");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "step_count");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "diastolic_bp");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "systolic_bp");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "respiratory_rate");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "temperature_integer_part");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "temperature_fractional_part");
            int columnIndexOrThrow15 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "body_fat_integer_part");
            int columnIndexOrThrow16 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "body_fat_fractional_part");
            int columnIndexOrThrow17 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "blood_sugar_level");
            int columnIndexOrThrow18 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "blood_sugar_measurement_mode");
            int columnIndexOrThrow19 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow20 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow21 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow22 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow23 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_hrv_uploaded");
            int columnIndexOrThrow24 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_blood_oxygen_uploaded");
            int columnIndexOrThrow25 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_respiratory_rate_uploaded");
            int columnIndexOrThrow26 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_temperature_uploaded");
            int columnIndexOrThrow27 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_body_fat_uploaded");
            int columnIndexOrThrow28 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_blood_sugar_uploaded");
            int columnIndexOrThrow29 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_hrv_uploaded");
            int columnIndexOrThrow30 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_blood_oxygen_uploaded");
            int columnIndexOrThrow31 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_respiratory_rate_uploaded");
            int columnIndexOrThrow32 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_temperature_uploaded");
            int columnIndexOrThrow33 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_body_fat_uploaded");
            int columnIndexOrThrow34 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_blood_sugar_uploaded");
            ArrayList arrayList = new ArrayList();
            while (sQLiteStatementPrepare.step()) {
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow)) {
                    i3 = columnIndexOrThrow13;
                    i2 = columnIndexOrThrow14;
                    lValueOf = null;
                } else {
                    i2 = columnIndexOrThrow14;
                    lValueOf = Long.valueOf(sQLiteStatementPrepare.getLong(columnIndexOrThrow));
                    i3 = columnIndexOrThrow13;
                }
                int i21 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
                long j2 = sQLiteStatementPrepare.getLong(columnIndexOrThrow3);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow4)) {
                    i5 = columnIndexOrThrow2;
                    i4 = columnIndexOrThrow3;
                    text = null;
                } else {
                    i4 = columnIndexOrThrow3;
                    text = sQLiteStatementPrepare.getText(columnIndexOrThrow4);
                    i5 = columnIndexOrThrow2;
                }
                int i22 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5);
                int i23 = columnIndexOrThrow4;
                int i24 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                int i25 = columnIndexOrThrow5;
                int i26 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7);
                int i27 = columnIndexOrThrow6;
                int i28 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow8);
                int i29 = columnIndexOrThrow7;
                int i30 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow9);
                int i31 = columnIndexOrThrow8;
                int i32 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10);
                int i33 = columnIndexOrThrow9;
                int i34 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11);
                int i35 = columnIndexOrThrow10;
                int i36 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12);
                int i37 = columnIndexOrThrow12;
                int i38 = i3;
                int i39 = columnIndexOrThrow11;
                int i40 = (int) sQLiteStatementPrepare.getLong(i38);
                int i41 = i2;
                int i42 = (int) sQLiteStatementPrepare.getLong(i41);
                int i43 = columnIndexOrThrow15;
                int i44 = (int) sQLiteStatementPrepare.getLong(i43);
                columnIndexOrThrow15 = i43;
                int i45 = columnIndexOrThrow16;
                int i46 = (int) sQLiteStatementPrepare.getLong(i45);
                int i47 = columnIndexOrThrow17;
                int i48 = (int) sQLiteStatementPrepare.getLong(i47);
                int i49 = columnIndexOrThrow18;
                int i50 = (int) sQLiteStatementPrepare.getLong(i49);
                int i51 = columnIndexOrThrow19;
                if (sQLiteStatementPrepare.isNull(i51)) {
                    i6 = columnIndexOrThrow;
                    i7 = columnIndexOrThrow20;
                    text2 = null;
                } else {
                    text2 = sQLiteStatementPrepare.getText(i51);
                    i6 = columnIndexOrThrow;
                    i7 = columnIndexOrThrow20;
                }
                if (sQLiteStatementPrepare.isNull(i7)) {
                    columnIndexOrThrow20 = i7;
                    i8 = columnIndexOrThrow21;
                    text3 = null;
                } else {
                    text3 = sQLiteStatementPrepare.getText(i7);
                    columnIndexOrThrow20 = i7;
                    i8 = columnIndexOrThrow21;
                }
                if (sQLiteStatementPrepare.isNull(i8)) {
                    columnIndexOrThrow21 = i8;
                    i9 = columnIndexOrThrow22;
                    text4 = null;
                } else {
                    text4 = sQLiteStatementPrepare.getText(i8);
                    columnIndexOrThrow21 = i8;
                    i9 = columnIndexOrThrow22;
                }
                String text5 = sQLiteStatementPrepare.isNull(i9) ? null : sQLiteStatementPrepare.getText(i9);
                columnIndexOrThrow22 = i9;
                int i52 = columnIndexOrThrow23;
                String str2 = text5;
                if (((int) sQLiteStatementPrepare.getLong(i52)) != 0) {
                    i10 = columnIndexOrThrow24;
                    z = true;
                } else {
                    i10 = columnIndexOrThrow24;
                    z = false;
                }
                if (((int) sQLiteStatementPrepare.getLong(i10)) != 0) {
                    i11 = columnIndexOrThrow25;
                    z2 = true;
                } else {
                    i11 = columnIndexOrThrow25;
                    z2 = false;
                }
                int i53 = i10;
                if (((int) sQLiteStatementPrepare.getLong(i11)) != 0) {
                    i12 = columnIndexOrThrow26;
                    z3 = true;
                } else {
                    i12 = columnIndexOrThrow26;
                    z3 = false;
                }
                int i54 = i11;
                if (((int) sQLiteStatementPrepare.getLong(i12)) != 0) {
                    i13 = columnIndexOrThrow27;
                    z4 = true;
                } else {
                    i13 = columnIndexOrThrow27;
                    z4 = false;
                }
                int i55 = i12;
                if (((int) sQLiteStatementPrepare.getLong(i13)) != 0) {
                    i14 = columnIndexOrThrow28;
                    z5 = true;
                } else {
                    i14 = columnIndexOrThrow28;
                    z5 = false;
                }
                int i56 = i13;
                if (((int) sQLiteStatementPrepare.getLong(i14)) != 0) {
                    i15 = columnIndexOrThrow29;
                    z6 = true;
                } else {
                    i15 = columnIndexOrThrow29;
                    z6 = false;
                }
                int i57 = i14;
                if (((int) sQLiteStatementPrepare.getLong(i15)) != 0) {
                    i16 = columnIndexOrThrow30;
                    z7 = true;
                } else {
                    i16 = columnIndexOrThrow30;
                    z7 = false;
                }
                int i58 = i15;
                if (((int) sQLiteStatementPrepare.getLong(i16)) != 0) {
                    i17 = columnIndexOrThrow31;
                    z8 = true;
                } else {
                    i17 = columnIndexOrThrow31;
                    z8 = false;
                }
                int i59 = i16;
                if (((int) sQLiteStatementPrepare.getLong(i17)) != 0) {
                    i18 = columnIndexOrThrow32;
                    z9 = true;
                } else {
                    i18 = columnIndexOrThrow32;
                    z9 = false;
                }
                int i60 = i17;
                if (((int) sQLiteStatementPrepare.getLong(i18)) != 0) {
                    i19 = columnIndexOrThrow33;
                    z10 = true;
                } else {
                    i19 = columnIndexOrThrow33;
                    z10 = false;
                }
                int i61 = i18;
                if (((int) sQLiteStatementPrepare.getLong(i19)) != 0) {
                    i20 = columnIndexOrThrow34;
                    z11 = true;
                } else {
                    i20 = columnIndexOrThrow34;
                    z11 = false;
                }
                int i62 = i19;
                arrayList.add(new HealthMetric(lValueOf, i21, j2, text, i22, i24, i26, i28, i30, i32, i34, i36, i40, i42, i44, i46, i48, i50, text2, text3, text4, str2, z, z2, z3, z4, z5, z6, z7, z8, z9, z10, z11, ((int) sQLiteStatementPrepare.getLong(i20)) != 0));
                columnIndexOrThrow2 = i5;
                columnIndexOrThrow14 = i41;
                columnIndexOrThrow16 = i45;
                columnIndexOrThrow18 = i49;
                columnIndexOrThrow17 = i47;
                columnIndexOrThrow24 = i53;
                columnIndexOrThrow25 = i54;
                columnIndexOrThrow27 = i56;
                columnIndexOrThrow28 = i57;
                columnIndexOrThrow26 = i55;
                columnIndexOrThrow30 = i59;
                columnIndexOrThrow31 = i60;
                columnIndexOrThrow29 = i58;
                columnIndexOrThrow33 = i62;
                columnIndexOrThrow13 = i38;
                columnIndexOrThrow3 = i4;
                columnIndexOrThrow4 = i23;
                columnIndexOrThrow5 = i25;
                columnIndexOrThrow6 = i27;
                columnIndexOrThrow7 = i29;
                columnIndexOrThrow8 = i31;
                columnIndexOrThrow9 = i33;
                columnIndexOrThrow10 = i35;
                columnIndexOrThrow23 = i52;
                columnIndexOrThrow34 = i20;
                columnIndexOrThrow32 = i61;
                columnIndexOrThrow = i6;
                columnIndexOrThrow11 = i39;
                columnIndexOrThrow19 = i51;
                columnIndexOrThrow12 = i37;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.HealthMetricDao
    public Object queryByYearToDay(final String yearToDay, final String userId, final Continuation<? super List<HealthMetric>> arg2) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.HealthMetricDao_Impl$$ExternalSyntheticLambda18
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return HealthMetricDao_Impl.lambda$queryByYearToDay$9(yearToDay, userId, (SQLiteConnection) obj);
            }
        }, arg2);
    }

    static /* synthetic */ List lambda$queryByYearToDay$9(String str, String str2, SQLiteConnection sQLiteConnection) {
        int i2;
        Long lValueOf;
        int i3;
        int i4;
        String text;
        int i5;
        String text2;
        int i6;
        int i7;
        String text3;
        int i8;
        String text4;
        int i9;
        int i10;
        boolean z;
        int i11;
        boolean z2;
        int i12;
        boolean z3;
        int i13;
        boolean z4;
        int i14;
        boolean z5;
        int i15;
        boolean z6;
        int i16;
        boolean z7;
        int i17;
        boolean z8;
        int i18;
        boolean z9;
        int i19;
        boolean z10;
        int i20;
        boolean z11;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("\n        SELECT * FROM health_metrics_data \n        WHERE time_year_to_day = ?\n        AND (user_id = ? \n        OR user_id = \"\"\n        OR user_id IS NULL)\n        ORDER BY start_timestamp DESC\n    ");
        try {
            if (str == null) {
                sQLiteStatementPrepare.mo182bindNull(1);
            } else {
                sQLiteStatementPrepare.mo183bindText(1, str);
            }
            if (str2 == null) {
                sQLiteStatementPrepare.mo182bindNull(2);
            } else {
                sQLiteStatementPrepare.mo183bindText(2, str2);
            }
            int columnIndexOrThrow = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "id");
            int columnIndexOrThrow2 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "query_id");
            int columnIndexOrThrow3 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "start_timestamp");
            int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "time_year_to_day");
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "heart_rate_value");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "hrv_value");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "cvrr_value");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "blood_oxygen_level");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "step_count");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "diastolic_bp");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "systolic_bp");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "respiratory_rate");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "temperature_integer_part");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "temperature_fractional_part");
            int columnIndexOrThrow15 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "body_fat_integer_part");
            int columnIndexOrThrow16 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "body_fat_fractional_part");
            int columnIndexOrThrow17 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "blood_sugar_level");
            int columnIndexOrThrow18 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "blood_sugar_measurement_mode");
            int columnIndexOrThrow19 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow20 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow21 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow22 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow23 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_hrv_uploaded");
            int columnIndexOrThrow24 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_blood_oxygen_uploaded");
            int columnIndexOrThrow25 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_respiratory_rate_uploaded");
            int columnIndexOrThrow26 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_temperature_uploaded");
            int columnIndexOrThrow27 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_body_fat_uploaded");
            int columnIndexOrThrow28 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_blood_sugar_uploaded");
            int columnIndexOrThrow29 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_hrv_uploaded");
            int columnIndexOrThrow30 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_blood_oxygen_uploaded");
            int columnIndexOrThrow31 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_respiratory_rate_uploaded");
            int columnIndexOrThrow32 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_temperature_uploaded");
            int columnIndexOrThrow33 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_body_fat_uploaded");
            int columnIndexOrThrow34 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_blood_sugar_uploaded");
            ArrayList arrayList = new ArrayList();
            while (sQLiteStatementPrepare.step()) {
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow)) {
                    i3 = columnIndexOrThrow13;
                    i2 = columnIndexOrThrow14;
                    lValueOf = null;
                } else {
                    i2 = columnIndexOrThrow14;
                    lValueOf = Long.valueOf(sQLiteStatementPrepare.getLong(columnIndexOrThrow));
                    i3 = columnIndexOrThrow13;
                }
                int i21 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
                long j2 = sQLiteStatementPrepare.getLong(columnIndexOrThrow3);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow4)) {
                    i5 = columnIndexOrThrow;
                    i4 = columnIndexOrThrow2;
                    text = null;
                } else {
                    i4 = columnIndexOrThrow2;
                    text = sQLiteStatementPrepare.getText(columnIndexOrThrow4);
                    i5 = columnIndexOrThrow;
                }
                int i22 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5);
                int i23 = columnIndexOrThrow3;
                int i24 = columnIndexOrThrow4;
                int i25 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                int i26 = columnIndexOrThrow5;
                int i27 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7);
                int i28 = columnIndexOrThrow6;
                int i29 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow8);
                int i30 = columnIndexOrThrow7;
                int i31 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow9);
                int i32 = columnIndexOrThrow8;
                int i33 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10);
                int i34 = columnIndexOrThrow9;
                int i35 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11);
                int i36 = columnIndexOrThrow10;
                int i37 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12);
                int i38 = columnIndexOrThrow12;
                int i39 = i3;
                int i40 = columnIndexOrThrow11;
                int i41 = (int) sQLiteStatementPrepare.getLong(i39);
                int i42 = i2;
                int i43 = (int) sQLiteStatementPrepare.getLong(i42);
                int i44 = columnIndexOrThrow15;
                int i45 = (int) sQLiteStatementPrepare.getLong(i44);
                int i46 = columnIndexOrThrow16;
                int i47 = (int) sQLiteStatementPrepare.getLong(i46);
                int i48 = columnIndexOrThrow17;
                int i49 = (int) sQLiteStatementPrepare.getLong(i48);
                int i50 = columnIndexOrThrow18;
                int i51 = (int) sQLiteStatementPrepare.getLong(i50);
                int i52 = columnIndexOrThrow19;
                if (sQLiteStatementPrepare.isNull(i52)) {
                    i6 = i23;
                    i7 = columnIndexOrThrow20;
                    text2 = null;
                } else {
                    text2 = sQLiteStatementPrepare.getText(i52);
                    i6 = i23;
                    i7 = columnIndexOrThrow20;
                }
                if (sQLiteStatementPrepare.isNull(i7)) {
                    columnIndexOrThrow20 = i7;
                    i8 = columnIndexOrThrow21;
                    text3 = null;
                } else {
                    text3 = sQLiteStatementPrepare.getText(i7);
                    columnIndexOrThrow20 = i7;
                    i8 = columnIndexOrThrow21;
                }
                if (sQLiteStatementPrepare.isNull(i8)) {
                    columnIndexOrThrow21 = i8;
                    i9 = columnIndexOrThrow22;
                    text4 = null;
                } else {
                    text4 = sQLiteStatementPrepare.getText(i8);
                    columnIndexOrThrow21 = i8;
                    i9 = columnIndexOrThrow22;
                }
                String text5 = sQLiteStatementPrepare.isNull(i9) ? null : sQLiteStatementPrepare.getText(i9);
                columnIndexOrThrow22 = i9;
                int i53 = columnIndexOrThrow23;
                String str3 = text5;
                if (((int) sQLiteStatementPrepare.getLong(i53)) != 0) {
                    i10 = columnIndexOrThrow24;
                    z = true;
                } else {
                    i10 = columnIndexOrThrow24;
                    z = false;
                }
                if (((int) sQLiteStatementPrepare.getLong(i10)) != 0) {
                    i11 = columnIndexOrThrow25;
                    z2 = true;
                } else {
                    i11 = columnIndexOrThrow25;
                    z2 = false;
                }
                int i54 = i10;
                if (((int) sQLiteStatementPrepare.getLong(i11)) != 0) {
                    i12 = columnIndexOrThrow26;
                    z3 = true;
                } else {
                    i12 = columnIndexOrThrow26;
                    z3 = false;
                }
                int i55 = i11;
                if (((int) sQLiteStatementPrepare.getLong(i12)) != 0) {
                    i13 = columnIndexOrThrow27;
                    z4 = true;
                } else {
                    i13 = columnIndexOrThrow27;
                    z4 = false;
                }
                int i56 = i12;
                if (((int) sQLiteStatementPrepare.getLong(i13)) != 0) {
                    i14 = columnIndexOrThrow28;
                    z5 = true;
                } else {
                    i14 = columnIndexOrThrow28;
                    z5 = false;
                }
                int i57 = i13;
                if (((int) sQLiteStatementPrepare.getLong(i14)) != 0) {
                    i15 = columnIndexOrThrow29;
                    z6 = true;
                } else {
                    i15 = columnIndexOrThrow29;
                    z6 = false;
                }
                int i58 = i14;
                if (((int) sQLiteStatementPrepare.getLong(i15)) != 0) {
                    i16 = columnIndexOrThrow30;
                    z7 = true;
                } else {
                    i16 = columnIndexOrThrow30;
                    z7 = false;
                }
                int i59 = i15;
                if (((int) sQLiteStatementPrepare.getLong(i16)) != 0) {
                    i17 = columnIndexOrThrow31;
                    z8 = true;
                } else {
                    i17 = columnIndexOrThrow31;
                    z8 = false;
                }
                int i60 = i16;
                if (((int) sQLiteStatementPrepare.getLong(i17)) != 0) {
                    i18 = columnIndexOrThrow32;
                    z9 = true;
                } else {
                    i18 = columnIndexOrThrow32;
                    z9 = false;
                }
                int i61 = i17;
                if (((int) sQLiteStatementPrepare.getLong(i18)) != 0) {
                    i19 = columnIndexOrThrow33;
                    z10 = true;
                } else {
                    i19 = columnIndexOrThrow33;
                    z10 = false;
                }
                int i62 = i18;
                if (((int) sQLiteStatementPrepare.getLong(i19)) != 0) {
                    i20 = columnIndexOrThrow34;
                    z11 = true;
                } else {
                    i20 = columnIndexOrThrow34;
                    z11 = false;
                }
                int i63 = i19;
                arrayList.add(new HealthMetric(lValueOf, i21, j2, text, i22, i25, i27, i29, i31, i33, i35, i37, i41, i43, i45, i47, i49, i51, text2, text3, text4, str3, z, z2, z3, z4, z5, z6, z7, z8, z9, z10, z11, ((int) sQLiteStatementPrepare.getLong(i20)) != 0));
                columnIndexOrThrow = i5;
                columnIndexOrThrow3 = i6;
                columnIndexOrThrow18 = i50;
                columnIndexOrThrow27 = i57;
                columnIndexOrThrow28 = i58;
                columnIndexOrThrow29 = i59;
                columnIndexOrThrow33 = i63;
                columnIndexOrThrow19 = i52;
                columnIndexOrThrow13 = i39;
                columnIndexOrThrow4 = i24;
                columnIndexOrThrow5 = i26;
                columnIndexOrThrow6 = i28;
                columnIndexOrThrow7 = i30;
                columnIndexOrThrow8 = i32;
                columnIndexOrThrow9 = i34;
                columnIndexOrThrow10 = i36;
                columnIndexOrThrow14 = i42;
                columnIndexOrThrow15 = i44;
                columnIndexOrThrow23 = i53;
                columnIndexOrThrow34 = i20;
                columnIndexOrThrow16 = i46;
                columnIndexOrThrow17 = i48;
                columnIndexOrThrow24 = i54;
                columnIndexOrThrow25 = i55;
                columnIndexOrThrow26 = i56;
                columnIndexOrThrow30 = i60;
                columnIndexOrThrow31 = i61;
                columnIndexOrThrow11 = i40;
                columnIndexOrThrow2 = i4;
                columnIndexOrThrow32 = i62;
                columnIndexOrThrow12 = i38;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.HealthMetricDao
    public Object querySinceYearToDay(final String yearToDay, final String userId, final Continuation<? super List<HealthMetric>> arg2) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.HealthMetricDao_Impl$$ExternalSyntheticLambda16
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return HealthMetricDao_Impl.lambda$querySinceYearToDay$10(yearToDay, userId, (SQLiteConnection) obj);
            }
        }, arg2);
    }

    static /* synthetic */ List lambda$querySinceYearToDay$10(String str, String str2, SQLiteConnection sQLiteConnection) {
        int i2;
        Long lValueOf;
        int i3;
        int i4;
        String text;
        int i5;
        String text2;
        int i6;
        int i7;
        String text3;
        int i8;
        String text4;
        int i9;
        int i10;
        boolean z;
        int i11;
        boolean z2;
        int i12;
        boolean z3;
        int i13;
        boolean z4;
        int i14;
        boolean z5;
        int i15;
        boolean z6;
        int i16;
        boolean z7;
        int i17;
        boolean z8;
        int i18;
        boolean z9;
        int i19;
        boolean z10;
        int i20;
        boolean z11;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("\n        SELECT * FROM health_metrics_data \n        WHERE time_year_to_day >= ?\n        AND (user_id = ? OR user_id = \"\" OR user_id IS NULL)\n    ");
        try {
            if (str == null) {
                sQLiteStatementPrepare.mo182bindNull(1);
            } else {
                sQLiteStatementPrepare.mo183bindText(1, str);
            }
            if (str2 == null) {
                sQLiteStatementPrepare.mo182bindNull(2);
            } else {
                sQLiteStatementPrepare.mo183bindText(2, str2);
            }
            int columnIndexOrThrow = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "id");
            int columnIndexOrThrow2 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "query_id");
            int columnIndexOrThrow3 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "start_timestamp");
            int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "time_year_to_day");
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "heart_rate_value");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "hrv_value");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "cvrr_value");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "blood_oxygen_level");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "step_count");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "diastolic_bp");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "systolic_bp");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "respiratory_rate");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "temperature_integer_part");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "temperature_fractional_part");
            int columnIndexOrThrow15 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "body_fat_integer_part");
            int columnIndexOrThrow16 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "body_fat_fractional_part");
            int columnIndexOrThrow17 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "blood_sugar_level");
            int columnIndexOrThrow18 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "blood_sugar_measurement_mode");
            int columnIndexOrThrow19 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow20 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow21 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow22 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow23 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_hrv_uploaded");
            int columnIndexOrThrow24 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_blood_oxygen_uploaded");
            int columnIndexOrThrow25 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_respiratory_rate_uploaded");
            int columnIndexOrThrow26 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_temperature_uploaded");
            int columnIndexOrThrow27 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_body_fat_uploaded");
            int columnIndexOrThrow28 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_blood_sugar_uploaded");
            int columnIndexOrThrow29 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_hrv_uploaded");
            int columnIndexOrThrow30 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_blood_oxygen_uploaded");
            int columnIndexOrThrow31 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_respiratory_rate_uploaded");
            int columnIndexOrThrow32 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_temperature_uploaded");
            int columnIndexOrThrow33 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_body_fat_uploaded");
            int columnIndexOrThrow34 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_blood_sugar_uploaded");
            ArrayList arrayList = new ArrayList();
            while (sQLiteStatementPrepare.step()) {
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow)) {
                    i3 = columnIndexOrThrow13;
                    i2 = columnIndexOrThrow14;
                    lValueOf = null;
                } else {
                    i2 = columnIndexOrThrow14;
                    lValueOf = Long.valueOf(sQLiteStatementPrepare.getLong(columnIndexOrThrow));
                    i3 = columnIndexOrThrow13;
                }
                int i21 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
                long j2 = sQLiteStatementPrepare.getLong(columnIndexOrThrow3);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow4)) {
                    i5 = columnIndexOrThrow;
                    i4 = columnIndexOrThrow2;
                    text = null;
                } else {
                    i4 = columnIndexOrThrow2;
                    text = sQLiteStatementPrepare.getText(columnIndexOrThrow4);
                    i5 = columnIndexOrThrow;
                }
                int i22 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5);
                int i23 = columnIndexOrThrow3;
                int i24 = columnIndexOrThrow4;
                int i25 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                int i26 = columnIndexOrThrow5;
                int i27 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7);
                int i28 = columnIndexOrThrow6;
                int i29 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow8);
                int i30 = columnIndexOrThrow7;
                int i31 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow9);
                int i32 = columnIndexOrThrow8;
                int i33 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10);
                int i34 = columnIndexOrThrow9;
                int i35 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11);
                int i36 = columnIndexOrThrow10;
                int i37 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12);
                int i38 = columnIndexOrThrow12;
                int i39 = i3;
                int i40 = columnIndexOrThrow11;
                int i41 = (int) sQLiteStatementPrepare.getLong(i39);
                int i42 = i2;
                int i43 = (int) sQLiteStatementPrepare.getLong(i42);
                int i44 = columnIndexOrThrow15;
                int i45 = (int) sQLiteStatementPrepare.getLong(i44);
                int i46 = columnIndexOrThrow16;
                int i47 = (int) sQLiteStatementPrepare.getLong(i46);
                int i48 = columnIndexOrThrow17;
                int i49 = (int) sQLiteStatementPrepare.getLong(i48);
                int i50 = columnIndexOrThrow18;
                int i51 = (int) sQLiteStatementPrepare.getLong(i50);
                int i52 = columnIndexOrThrow19;
                if (sQLiteStatementPrepare.isNull(i52)) {
                    i6 = i23;
                    i7 = columnIndexOrThrow20;
                    text2 = null;
                } else {
                    text2 = sQLiteStatementPrepare.getText(i52);
                    i6 = i23;
                    i7 = columnIndexOrThrow20;
                }
                if (sQLiteStatementPrepare.isNull(i7)) {
                    columnIndexOrThrow20 = i7;
                    i8 = columnIndexOrThrow21;
                    text3 = null;
                } else {
                    text3 = sQLiteStatementPrepare.getText(i7);
                    columnIndexOrThrow20 = i7;
                    i8 = columnIndexOrThrow21;
                }
                if (sQLiteStatementPrepare.isNull(i8)) {
                    columnIndexOrThrow21 = i8;
                    i9 = columnIndexOrThrow22;
                    text4 = null;
                } else {
                    text4 = sQLiteStatementPrepare.getText(i8);
                    columnIndexOrThrow21 = i8;
                    i9 = columnIndexOrThrow22;
                }
                String text5 = sQLiteStatementPrepare.isNull(i9) ? null : sQLiteStatementPrepare.getText(i9);
                columnIndexOrThrow22 = i9;
                int i53 = columnIndexOrThrow23;
                String str3 = text5;
                if (((int) sQLiteStatementPrepare.getLong(i53)) != 0) {
                    i10 = columnIndexOrThrow24;
                    z = true;
                } else {
                    i10 = columnIndexOrThrow24;
                    z = false;
                }
                if (((int) sQLiteStatementPrepare.getLong(i10)) != 0) {
                    i11 = columnIndexOrThrow25;
                    z2 = true;
                } else {
                    i11 = columnIndexOrThrow25;
                    z2 = false;
                }
                int i54 = i10;
                if (((int) sQLiteStatementPrepare.getLong(i11)) != 0) {
                    i12 = columnIndexOrThrow26;
                    z3 = true;
                } else {
                    i12 = columnIndexOrThrow26;
                    z3 = false;
                }
                int i55 = i11;
                if (((int) sQLiteStatementPrepare.getLong(i12)) != 0) {
                    i13 = columnIndexOrThrow27;
                    z4 = true;
                } else {
                    i13 = columnIndexOrThrow27;
                    z4 = false;
                }
                int i56 = i12;
                if (((int) sQLiteStatementPrepare.getLong(i13)) != 0) {
                    i14 = columnIndexOrThrow28;
                    z5 = true;
                } else {
                    i14 = columnIndexOrThrow28;
                    z5 = false;
                }
                int i57 = i13;
                if (((int) sQLiteStatementPrepare.getLong(i14)) != 0) {
                    i15 = columnIndexOrThrow29;
                    z6 = true;
                } else {
                    i15 = columnIndexOrThrow29;
                    z6 = false;
                }
                int i58 = i14;
                if (((int) sQLiteStatementPrepare.getLong(i15)) != 0) {
                    i16 = columnIndexOrThrow30;
                    z7 = true;
                } else {
                    i16 = columnIndexOrThrow30;
                    z7 = false;
                }
                int i59 = i15;
                if (((int) sQLiteStatementPrepare.getLong(i16)) != 0) {
                    i17 = columnIndexOrThrow31;
                    z8 = true;
                } else {
                    i17 = columnIndexOrThrow31;
                    z8 = false;
                }
                int i60 = i16;
                if (((int) sQLiteStatementPrepare.getLong(i17)) != 0) {
                    i18 = columnIndexOrThrow32;
                    z9 = true;
                } else {
                    i18 = columnIndexOrThrow32;
                    z9 = false;
                }
                int i61 = i17;
                if (((int) sQLiteStatementPrepare.getLong(i18)) != 0) {
                    i19 = columnIndexOrThrow33;
                    z10 = true;
                } else {
                    i19 = columnIndexOrThrow33;
                    z10 = false;
                }
                int i62 = i18;
                if (((int) sQLiteStatementPrepare.getLong(i19)) != 0) {
                    i20 = columnIndexOrThrow34;
                    z11 = true;
                } else {
                    i20 = columnIndexOrThrow34;
                    z11 = false;
                }
                int i63 = i19;
                arrayList.add(new HealthMetric(lValueOf, i21, j2, text, i22, i25, i27, i29, i31, i33, i35, i37, i41, i43, i45, i47, i49, i51, text2, text3, text4, str3, z, z2, z3, z4, z5, z6, z7, z8, z9, z10, z11, ((int) sQLiteStatementPrepare.getLong(i20)) != 0));
                columnIndexOrThrow = i5;
                columnIndexOrThrow3 = i6;
                columnIndexOrThrow18 = i50;
                columnIndexOrThrow27 = i57;
                columnIndexOrThrow28 = i58;
                columnIndexOrThrow29 = i59;
                columnIndexOrThrow33 = i63;
                columnIndexOrThrow19 = i52;
                columnIndexOrThrow13 = i39;
                columnIndexOrThrow4 = i24;
                columnIndexOrThrow5 = i26;
                columnIndexOrThrow6 = i28;
                columnIndexOrThrow7 = i30;
                columnIndexOrThrow8 = i32;
                columnIndexOrThrow9 = i34;
                columnIndexOrThrow10 = i36;
                columnIndexOrThrow14 = i42;
                columnIndexOrThrow15 = i44;
                columnIndexOrThrow23 = i53;
                columnIndexOrThrow34 = i20;
                columnIndexOrThrow16 = i46;
                columnIndexOrThrow17 = i48;
                columnIndexOrThrow24 = i54;
                columnIndexOrThrow25 = i55;
                columnIndexOrThrow26 = i56;
                columnIndexOrThrow30 = i60;
                columnIndexOrThrow31 = i61;
                columnIndexOrThrow11 = i40;
                columnIndexOrThrow2 = i4;
                columnIndexOrThrow32 = i62;
                columnIndexOrThrow12 = i38;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.HealthMetricDao
    public Object getMetricsInTimeRange(final long startTime, final long endTime, final String userName, final Continuation<? super List<HealthMetric>> arg3) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.HealthMetricDao_Impl$$ExternalSyntheticLambda17
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return HealthMetricDao_Impl.lambda$getMetricsInTimeRange$11(startTime, endTime, userName, (SQLiteConnection) obj);
            }
        }, arg3);
    }

    static /* synthetic */ List lambda$getMetricsInTimeRange$11(long j2, long j3, String str, SQLiteConnection sQLiteConnection) {
        int i2;
        Long lValueOf;
        int i3;
        int i4;
        String text;
        int i5;
        int i6;
        int i7;
        String text2;
        String text3;
        int i8;
        String text4;
        int i9;
        int i10;
        boolean z;
        int i11;
        boolean z2;
        int i12;
        boolean z3;
        int i13;
        boolean z4;
        int i14;
        boolean z5;
        int i15;
        boolean z6;
        int i16;
        boolean z7;
        int i17;
        boolean z8;
        int i18;
        boolean z9;
        int i19;
        boolean z10;
        int i20;
        boolean z11;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("\n        SELECT * FROM health_metrics_data \n        WHERE start_timestamp BETWEEN ? AND ?\n        AND (user_id = ? OR user_id IS NULL OR user_id = '')\n    ");
        try {
            sQLiteStatementPrepare.mo181bindLong(1, j2);
            sQLiteStatementPrepare.mo181bindLong(2, j3);
            if (str == null) {
                sQLiteStatementPrepare.mo182bindNull(3);
            } else {
                sQLiteStatementPrepare.mo183bindText(3, str);
            }
            int columnIndexOrThrow = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "id");
            int columnIndexOrThrow2 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "query_id");
            int columnIndexOrThrow3 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "start_timestamp");
            int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "time_year_to_day");
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "heart_rate_value");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "hrv_value");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "cvrr_value");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "blood_oxygen_level");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "step_count");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "diastolic_bp");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "systolic_bp");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "respiratory_rate");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "temperature_integer_part");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "temperature_fractional_part");
            int columnIndexOrThrow15 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "body_fat_integer_part");
            int columnIndexOrThrow16 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "body_fat_fractional_part");
            int columnIndexOrThrow17 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "blood_sugar_level");
            int columnIndexOrThrow18 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "blood_sugar_measurement_mode");
            int columnIndexOrThrow19 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow20 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow21 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow22 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow23 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_hrv_uploaded");
            int columnIndexOrThrow24 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_blood_oxygen_uploaded");
            int columnIndexOrThrow25 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_respiratory_rate_uploaded");
            int columnIndexOrThrow26 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_temperature_uploaded");
            int columnIndexOrThrow27 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_body_fat_uploaded");
            int columnIndexOrThrow28 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_blood_sugar_uploaded");
            int columnIndexOrThrow29 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_hrv_uploaded");
            int columnIndexOrThrow30 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_blood_oxygen_uploaded");
            int columnIndexOrThrow31 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_respiratory_rate_uploaded");
            int columnIndexOrThrow32 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_temperature_uploaded");
            int columnIndexOrThrow33 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_body_fat_uploaded");
            int columnIndexOrThrow34 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_blood_sugar_uploaded");
            ArrayList arrayList = new ArrayList();
            while (sQLiteStatementPrepare.step()) {
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow)) {
                    i3 = columnIndexOrThrow13;
                    i2 = columnIndexOrThrow14;
                    lValueOf = null;
                } else {
                    i2 = columnIndexOrThrow14;
                    lValueOf = Long.valueOf(sQLiteStatementPrepare.getLong(columnIndexOrThrow));
                    i3 = columnIndexOrThrow13;
                }
                int i21 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
                long j4 = sQLiteStatementPrepare.getLong(columnIndexOrThrow3);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow4)) {
                    i5 = columnIndexOrThrow2;
                    i4 = columnIndexOrThrow3;
                    text = null;
                } else {
                    i4 = columnIndexOrThrow3;
                    text = sQLiteStatementPrepare.getText(columnIndexOrThrow4);
                    i5 = columnIndexOrThrow2;
                }
                int i22 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5);
                int i23 = columnIndexOrThrow4;
                int i24 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                int i25 = columnIndexOrThrow5;
                int i26 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7);
                int i27 = columnIndexOrThrow6;
                int i28 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow8);
                int i29 = columnIndexOrThrow7;
                int i30 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow9);
                int i31 = columnIndexOrThrow8;
                int i32 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10);
                int i33 = columnIndexOrThrow9;
                int i34 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11);
                int i35 = columnIndexOrThrow10;
                int i36 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12);
                int i37 = columnIndexOrThrow12;
                int i38 = i3;
                int i39 = columnIndexOrThrow11;
                int i40 = (int) sQLiteStatementPrepare.getLong(i38);
                int i41 = i2;
                int i42 = (int) sQLiteStatementPrepare.getLong(i41);
                int i43 = columnIndexOrThrow15;
                int i44 = (int) sQLiteStatementPrepare.getLong(i43);
                int i45 = columnIndexOrThrow16;
                int i46 = (int) sQLiteStatementPrepare.getLong(i45);
                int i47 = columnIndexOrThrow17;
                int i48 = (int) sQLiteStatementPrepare.getLong(i47);
                int i49 = columnIndexOrThrow18;
                int i50 = (int) sQLiteStatementPrepare.getLong(i49);
                int i51 = columnIndexOrThrow19;
                if (sQLiteStatementPrepare.isNull(i51)) {
                    i6 = columnIndexOrThrow;
                    i7 = columnIndexOrThrow20;
                    text2 = null;
                } else {
                    i6 = columnIndexOrThrow;
                    i7 = columnIndexOrThrow20;
                    text2 = sQLiteStatementPrepare.getText(i51);
                }
                if (sQLiteStatementPrepare.isNull(i7)) {
                    columnIndexOrThrow20 = i7;
                    i8 = columnIndexOrThrow21;
                    text3 = null;
                } else {
                    text3 = sQLiteStatementPrepare.getText(i7);
                    columnIndexOrThrow20 = i7;
                    i8 = columnIndexOrThrow21;
                }
                if (sQLiteStatementPrepare.isNull(i8)) {
                    columnIndexOrThrow21 = i8;
                    i9 = columnIndexOrThrow22;
                    text4 = null;
                } else {
                    text4 = sQLiteStatementPrepare.getText(i8);
                    columnIndexOrThrow21 = i8;
                    i9 = columnIndexOrThrow22;
                }
                String text5 = sQLiteStatementPrepare.isNull(i9) ? null : sQLiteStatementPrepare.getText(i9);
                columnIndexOrThrow22 = i9;
                columnIndexOrThrow19 = i51;
                int i52 = columnIndexOrThrow23;
                String str2 = text5;
                if (((int) sQLiteStatementPrepare.getLong(i52)) != 0) {
                    i10 = columnIndexOrThrow24;
                    z = true;
                } else {
                    i10 = columnIndexOrThrow24;
                    z = false;
                }
                if (((int) sQLiteStatementPrepare.getLong(i10)) != 0) {
                    i11 = columnIndexOrThrow25;
                    z2 = true;
                } else {
                    i11 = columnIndexOrThrow25;
                    z2 = false;
                }
                int i53 = i10;
                if (((int) sQLiteStatementPrepare.getLong(i11)) != 0) {
                    i12 = columnIndexOrThrow26;
                    z3 = true;
                } else {
                    i12 = columnIndexOrThrow26;
                    z3 = false;
                }
                int i54 = i11;
                if (((int) sQLiteStatementPrepare.getLong(i12)) != 0) {
                    i13 = columnIndexOrThrow27;
                    z4 = true;
                } else {
                    i13 = columnIndexOrThrow27;
                    z4 = false;
                }
                int i55 = i12;
                if (((int) sQLiteStatementPrepare.getLong(i13)) != 0) {
                    i14 = columnIndexOrThrow28;
                    z5 = true;
                } else {
                    i14 = columnIndexOrThrow28;
                    z5 = false;
                }
                int i56 = i13;
                if (((int) sQLiteStatementPrepare.getLong(i14)) != 0) {
                    i15 = columnIndexOrThrow29;
                    z6 = true;
                } else {
                    i15 = columnIndexOrThrow29;
                    z6 = false;
                }
                int i57 = i14;
                if (((int) sQLiteStatementPrepare.getLong(i15)) != 0) {
                    i16 = columnIndexOrThrow30;
                    z7 = true;
                } else {
                    i16 = columnIndexOrThrow30;
                    z7 = false;
                }
                int i58 = i15;
                if (((int) sQLiteStatementPrepare.getLong(i16)) != 0) {
                    i17 = columnIndexOrThrow31;
                    z8 = true;
                } else {
                    i17 = columnIndexOrThrow31;
                    z8 = false;
                }
                int i59 = i16;
                if (((int) sQLiteStatementPrepare.getLong(i17)) != 0) {
                    i18 = columnIndexOrThrow32;
                    z9 = true;
                } else {
                    i18 = columnIndexOrThrow32;
                    z9 = false;
                }
                int i60 = i17;
                if (((int) sQLiteStatementPrepare.getLong(i18)) != 0) {
                    i19 = columnIndexOrThrow33;
                    z10 = true;
                } else {
                    i19 = columnIndexOrThrow33;
                    z10 = false;
                }
                int i61 = i18;
                if (((int) sQLiteStatementPrepare.getLong(i19)) != 0) {
                    i20 = columnIndexOrThrow34;
                    z11 = true;
                } else {
                    i20 = columnIndexOrThrow34;
                    z11 = false;
                }
                int i62 = i19;
                arrayList.add(new HealthMetric(lValueOf, i21, j4, text, i22, i24, i26, i28, i30, i32, i34, i36, i40, i42, i44, i46, i48, i50, text2, text3, text4, str2, z, z2, z3, z4, z5, z6, z7, z8, z9, z10, z11, ((int) sQLiteStatementPrepare.getLong(i20)) != 0));
                columnIndexOrThrow2 = i5;
                columnIndexOrThrow27 = i56;
                columnIndexOrThrow28 = i57;
                columnIndexOrThrow29 = i58;
                columnIndexOrThrow33 = i62;
                columnIndexOrThrow13 = i38;
                columnIndexOrThrow3 = i4;
                columnIndexOrThrow4 = i23;
                columnIndexOrThrow5 = i25;
                columnIndexOrThrow6 = i27;
                columnIndexOrThrow7 = i29;
                columnIndexOrThrow8 = i31;
                columnIndexOrThrow9 = i33;
                columnIndexOrThrow10 = i35;
                columnIndexOrThrow14 = i41;
                columnIndexOrThrow15 = i43;
                columnIndexOrThrow16 = i45;
                columnIndexOrThrow34 = i20;
                columnIndexOrThrow17 = i47;
                columnIndexOrThrow24 = i53;
                columnIndexOrThrow25 = i54;
                columnIndexOrThrow26 = i55;
                columnIndexOrThrow30 = i59;
                columnIndexOrThrow31 = i60;
                columnIndexOrThrow11 = i39;
                columnIndexOrThrow32 = i61;
                columnIndexOrThrow12 = i37;
                columnIndexOrThrow = i6;
                columnIndexOrThrow18 = i49;
                columnIndexOrThrow23 = i52;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.HealthMetricDao
    public Object getBoUploadedWithTimeRange(final long startTime, final long endTime, final boolean isUpload, final String userName, final Continuation<? super List<HealthMetric>> arg4) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.HealthMetricDao_Impl$$ExternalSyntheticLambda20
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return HealthMetricDao_Impl.lambda$getBoUploadedWithTimeRange$12(isUpload, startTime, endTime, userName, (SQLiteConnection) obj);
            }
        }, arg4);
    }

    static /* synthetic */ List lambda$getBoUploadedWithTimeRange$12(boolean z, long j2, long j3, String str, SQLiteConnection sQLiteConnection) {
        ArrayList arrayList;
        Long lValueOf;
        int i2;
        String text;
        int i3;
        int i4;
        String text2;
        int i5;
        String str2;
        String text3;
        int i6;
        int i7;
        boolean z2;
        int i8;
        boolean z3;
        int i9;
        boolean z4;
        int i10;
        boolean z5;
        int i11;
        boolean z6;
        int i12;
        boolean z7;
        int i13;
        boolean z8;
        int i14;
        boolean z9;
        int i15;
        boolean z10;
        int i16;
        boolean z11;
        int i17;
        boolean z12;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("\n        SELECT * FROM health_metrics_data \n        WHERE is_blood_oxygen_uploaded = ? \n        AND (start_timestamp BETWEEN ? AND ?)\n        AND (user_id = ? OR user_id IS NULL OR user_id = '')\n    ");
        try {
            sQLiteStatementPrepare.mo181bindLong(1, z ? 1L : 0L);
            sQLiteStatementPrepare.mo181bindLong(2, j2);
            sQLiteStatementPrepare.mo181bindLong(3, j3);
            if (str == null) {
                sQLiteStatementPrepare.mo182bindNull(4);
            } else {
                sQLiteStatementPrepare.mo183bindText(4, str);
            }
            int columnIndexOrThrow = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "id");
            int columnIndexOrThrow2 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "query_id");
            int columnIndexOrThrow3 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "start_timestamp");
            int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "time_year_to_day");
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "heart_rate_value");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "hrv_value");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "cvrr_value");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "blood_oxygen_level");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "step_count");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "diastolic_bp");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "systolic_bp");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "respiratory_rate");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "temperature_integer_part");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "temperature_fractional_part");
            int columnIndexOrThrow15 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "body_fat_integer_part");
            int columnIndexOrThrow16 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "body_fat_fractional_part");
            int columnIndexOrThrow17 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "blood_sugar_level");
            int columnIndexOrThrow18 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "blood_sugar_measurement_mode");
            int columnIndexOrThrow19 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow20 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow21 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow22 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow23 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_hrv_uploaded");
            int columnIndexOrThrow24 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_blood_oxygen_uploaded");
            int columnIndexOrThrow25 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_respiratory_rate_uploaded");
            int columnIndexOrThrow26 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_temperature_uploaded");
            int columnIndexOrThrow27 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_body_fat_uploaded");
            int columnIndexOrThrow28 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_blood_sugar_uploaded");
            int columnIndexOrThrow29 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_hrv_uploaded");
            int columnIndexOrThrow30 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_blood_oxygen_uploaded");
            int columnIndexOrThrow31 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_respiratory_rate_uploaded");
            int columnIndexOrThrow32 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_temperature_uploaded");
            int columnIndexOrThrow33 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_body_fat_uploaded");
            int columnIndexOrThrow34 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_blood_sugar_uploaded");
            ArrayList arrayList2 = new ArrayList();
            while (sQLiteStatementPrepare.step()) {
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow)) {
                    i2 = columnIndexOrThrow14;
                    arrayList = arrayList2;
                    lValueOf = null;
                } else {
                    arrayList = arrayList2;
                    lValueOf = Long.valueOf(sQLiteStatementPrepare.getLong(columnIndexOrThrow));
                    i2 = columnIndexOrThrow14;
                }
                int i18 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
                long j4 = sQLiteStatementPrepare.getLong(columnIndexOrThrow3);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow4)) {
                    i3 = columnIndexOrThrow2;
                    text = null;
                } else {
                    text = sQLiteStatementPrepare.getText(columnIndexOrThrow4);
                    i3 = columnIndexOrThrow2;
                }
                int i19 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5);
                int i20 = columnIndexOrThrow3;
                int i21 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                int i22 = columnIndexOrThrow4;
                int i23 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7);
                int i24 = columnIndexOrThrow5;
                int i25 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow8);
                int i26 = columnIndexOrThrow6;
                int i27 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow9);
                int i28 = columnIndexOrThrow7;
                int i29 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10);
                int i30 = columnIndexOrThrow8;
                int i31 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11);
                int i32 = columnIndexOrThrow9;
                int i33 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12);
                int i34 = columnIndexOrThrow10;
                int i35 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow13);
                int i36 = columnIndexOrThrow12;
                int i37 = i2;
                int i38 = columnIndexOrThrow11;
                int i39 = (int) sQLiteStatementPrepare.getLong(i37);
                int i40 = columnIndexOrThrow15;
                int i41 = (int) sQLiteStatementPrepare.getLong(i40);
                int i42 = columnIndexOrThrow16;
                int i43 = columnIndexOrThrow13;
                int i44 = (int) sQLiteStatementPrepare.getLong(i42);
                int i45 = columnIndexOrThrow17;
                int i46 = (int) sQLiteStatementPrepare.getLong(i45);
                int i47 = columnIndexOrThrow18;
                int i48 = (int) sQLiteStatementPrepare.getLong(i47);
                int i49 = columnIndexOrThrow19;
                if (sQLiteStatementPrepare.isNull(i49)) {
                    i4 = columnIndexOrThrow;
                    text2 = null;
                } else {
                    i4 = columnIndexOrThrow;
                    text2 = sQLiteStatementPrepare.getText(i49);
                }
                int i50 = columnIndexOrThrow20;
                if (sQLiteStatementPrepare.isNull(i50)) {
                    columnIndexOrThrow20 = i50;
                    i5 = columnIndexOrThrow21;
                    str2 = null;
                } else {
                    String text4 = sQLiteStatementPrepare.getText(i50);
                    columnIndexOrThrow20 = i50;
                    i5 = columnIndexOrThrow21;
                    str2 = text4;
                }
                if (sQLiteStatementPrepare.isNull(i5)) {
                    columnIndexOrThrow21 = i5;
                    i6 = columnIndexOrThrow22;
                    text3 = null;
                } else {
                    text3 = sQLiteStatementPrepare.getText(i5);
                    columnIndexOrThrow21 = i5;
                    i6 = columnIndexOrThrow22;
                }
                String text5 = sQLiteStatementPrepare.isNull(i6) ? null : sQLiteStatementPrepare.getText(i6);
                columnIndexOrThrow22 = i6;
                columnIndexOrThrow19 = i49;
                int i51 = columnIndexOrThrow23;
                String str3 = text5;
                if (((int) sQLiteStatementPrepare.getLong(i51)) != 0) {
                    i7 = columnIndexOrThrow24;
                    z2 = true;
                } else {
                    i7 = columnIndexOrThrow24;
                    z2 = false;
                }
                if (((int) sQLiteStatementPrepare.getLong(i7)) != 0) {
                    i8 = columnIndexOrThrow25;
                    z3 = true;
                } else {
                    i8 = columnIndexOrThrow25;
                    z3 = false;
                }
                int i52 = i7;
                if (((int) sQLiteStatementPrepare.getLong(i8)) != 0) {
                    i9 = columnIndexOrThrow26;
                    z4 = true;
                } else {
                    i9 = columnIndexOrThrow26;
                    z4 = false;
                }
                int i53 = i8;
                if (((int) sQLiteStatementPrepare.getLong(i9)) != 0) {
                    i10 = columnIndexOrThrow27;
                    z5 = true;
                } else {
                    i10 = columnIndexOrThrow27;
                    z5 = false;
                }
                int i54 = i9;
                if (((int) sQLiteStatementPrepare.getLong(i10)) != 0) {
                    i11 = columnIndexOrThrow28;
                    z6 = true;
                } else {
                    i11 = columnIndexOrThrow28;
                    z6 = false;
                }
                int i55 = i10;
                if (((int) sQLiteStatementPrepare.getLong(i11)) != 0) {
                    i12 = columnIndexOrThrow29;
                    z7 = true;
                } else {
                    i12 = columnIndexOrThrow29;
                    z7 = false;
                }
                int i56 = i11;
                if (((int) sQLiteStatementPrepare.getLong(i12)) != 0) {
                    i13 = columnIndexOrThrow30;
                    z8 = true;
                } else {
                    i13 = columnIndexOrThrow30;
                    z8 = false;
                }
                int i57 = i12;
                if (((int) sQLiteStatementPrepare.getLong(i13)) != 0) {
                    i14 = columnIndexOrThrow31;
                    z9 = true;
                } else {
                    i14 = columnIndexOrThrow31;
                    z9 = false;
                }
                int i58 = i13;
                if (((int) sQLiteStatementPrepare.getLong(i14)) != 0) {
                    i15 = columnIndexOrThrow32;
                    z10 = true;
                } else {
                    i15 = columnIndexOrThrow32;
                    z10 = false;
                }
                int i59 = i14;
                if (((int) sQLiteStatementPrepare.getLong(i15)) != 0) {
                    i16 = columnIndexOrThrow33;
                    z11 = true;
                } else {
                    i16 = columnIndexOrThrow33;
                    z11 = false;
                }
                int i60 = i15;
                if (((int) sQLiteStatementPrepare.getLong(i16)) != 0) {
                    i17 = columnIndexOrThrow34;
                    z12 = true;
                } else {
                    i17 = columnIndexOrThrow34;
                    z12 = false;
                }
                int i61 = i16;
                HealthMetric healthMetric = new HealthMetric(lValueOf, i18, j4, text, i19, i21, i23, i25, i27, i29, i31, i33, i35, i39, i41, i44, i46, i48, text2, str2, text3, str3, z2, z3, z4, z5, z6, z7, z8, z9, z10, z11, z12, ((int) sQLiteStatementPrepare.getLong(i17)) != 0);
                ArrayList arrayList3 = arrayList;
                arrayList3.add(healthMetric);
                arrayList2 = arrayList3;
                columnIndexOrThrow27 = i55;
                columnIndexOrThrow28 = i56;
                columnIndexOrThrow29 = i57;
                columnIndexOrThrow33 = i61;
                columnIndexOrThrow11 = i38;
                columnIndexOrThrow2 = i3;
                columnIndexOrThrow3 = i20;
                columnIndexOrThrow4 = i22;
                columnIndexOrThrow5 = i24;
                columnIndexOrThrow6 = i26;
                columnIndexOrThrow7 = i28;
                columnIndexOrThrow8 = i30;
                columnIndexOrThrow9 = i32;
                columnIndexOrThrow10 = i34;
                columnIndexOrThrow14 = i37;
                columnIndexOrThrow34 = i17;
                columnIndexOrThrow12 = i36;
                columnIndexOrThrow15 = i40;
                columnIndexOrThrow13 = i43;
                columnIndexOrThrow16 = i42;
                columnIndexOrThrow17 = i45;
                columnIndexOrThrow24 = i52;
                columnIndexOrThrow25 = i53;
                columnIndexOrThrow26 = i54;
                columnIndexOrThrow30 = i58;
                columnIndexOrThrow31 = i59;
                columnIndexOrThrow32 = i60;
                columnIndexOrThrow = i4;
                columnIndexOrThrow18 = i47;
                columnIndexOrThrow23 = i51;
            }
            return arrayList2;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.HealthMetricDao
    public Object queryBloodOxygenSyncedWithYearToDay(final String yearToDay, final String userId, final boolean synced, final Continuation<? super List<HealthMetric>> arg3) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.HealthMetricDao_Impl$$ExternalSyntheticLambda13
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return HealthMetricDao_Impl.lambda$queryBloodOxygenSyncedWithYearToDay$13(synced, yearToDay, userId, (SQLiteConnection) obj);
            }
        }, arg3);
    }

    static /* synthetic */ List lambda$queryBloodOxygenSyncedWithYearToDay$13(boolean z, String str, String str2, SQLiteConnection sQLiteConnection) {
        ArrayList arrayList;
        Long lValueOf;
        int i2;
        int i3;
        String text;
        int i4;
        String text2;
        int i5;
        int i6;
        String text3;
        int i7;
        String text4;
        int i8;
        int i9;
        boolean z2;
        int i10;
        boolean z3;
        int i11;
        boolean z4;
        int i12;
        boolean z5;
        int i13;
        boolean z6;
        int i14;
        boolean z7;
        int i15;
        boolean z8;
        int i16;
        boolean z9;
        int i17;
        boolean z10;
        int i18;
        boolean z11;
        int i19;
        boolean z12;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("\n        SELECT * FROM health_metrics_data \n        WHERE is_blood_oxygen_uploaded = ?\n        AND time_year_to_day = ?\n        AND (user_id = ? \n        OR user_id = \"\"\n        OR user_id IS NULL)\n        ORDER BY start_timestamp DESC\n    ");
        try {
            sQLiteStatementPrepare.mo181bindLong(1, z ? 1L : 0L);
            if (str == null) {
                sQLiteStatementPrepare.mo182bindNull(2);
            } else {
                sQLiteStatementPrepare.mo183bindText(2, str);
            }
            if (str2 == null) {
                sQLiteStatementPrepare.mo182bindNull(3);
            } else {
                sQLiteStatementPrepare.mo183bindText(3, str2);
            }
            int columnIndexOrThrow = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "id");
            int columnIndexOrThrow2 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "query_id");
            int columnIndexOrThrow3 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "start_timestamp");
            int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "time_year_to_day");
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "heart_rate_value");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "hrv_value");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "cvrr_value");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "blood_oxygen_level");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "step_count");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "diastolic_bp");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "systolic_bp");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "respiratory_rate");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "temperature_integer_part");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "temperature_fractional_part");
            int columnIndexOrThrow15 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "body_fat_integer_part");
            int columnIndexOrThrow16 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "body_fat_fractional_part");
            int columnIndexOrThrow17 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "blood_sugar_level");
            int columnIndexOrThrow18 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "blood_sugar_measurement_mode");
            int columnIndexOrThrow19 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow20 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow21 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow22 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow23 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_hrv_uploaded");
            int columnIndexOrThrow24 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_blood_oxygen_uploaded");
            int columnIndexOrThrow25 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_respiratory_rate_uploaded");
            int columnIndexOrThrow26 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_temperature_uploaded");
            int columnIndexOrThrow27 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_body_fat_uploaded");
            int columnIndexOrThrow28 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_blood_sugar_uploaded");
            int columnIndexOrThrow29 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_hrv_uploaded");
            int columnIndexOrThrow30 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_blood_oxygen_uploaded");
            int columnIndexOrThrow31 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_respiratory_rate_uploaded");
            int columnIndexOrThrow32 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_temperature_uploaded");
            int columnIndexOrThrow33 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_body_fat_uploaded");
            int columnIndexOrThrow34 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_blood_sugar_uploaded");
            ArrayList arrayList2 = new ArrayList();
            while (sQLiteStatementPrepare.step()) {
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow)) {
                    i2 = columnIndexOrThrow14;
                    arrayList = arrayList2;
                    lValueOf = null;
                } else {
                    arrayList = arrayList2;
                    lValueOf = Long.valueOf(sQLiteStatementPrepare.getLong(columnIndexOrThrow));
                    i2 = columnIndexOrThrow14;
                }
                int i20 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
                long j2 = sQLiteStatementPrepare.getLong(columnIndexOrThrow3);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow4)) {
                    i4 = columnIndexOrThrow;
                    i3 = columnIndexOrThrow2;
                    text = null;
                } else {
                    i3 = columnIndexOrThrow2;
                    text = sQLiteStatementPrepare.getText(columnIndexOrThrow4);
                    i4 = columnIndexOrThrow;
                }
                int i21 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5);
                int i22 = i4;
                int i23 = columnIndexOrThrow3;
                int i24 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                int i25 = columnIndexOrThrow4;
                int i26 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7);
                int i27 = columnIndexOrThrow5;
                int i28 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow8);
                int i29 = columnIndexOrThrow6;
                int i30 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow9);
                int i31 = columnIndexOrThrow7;
                int i32 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10);
                int i33 = columnIndexOrThrow8;
                int i34 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11);
                int i35 = columnIndexOrThrow9;
                int i36 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12);
                int i37 = columnIndexOrThrow10;
                int i38 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow13);
                int i39 = columnIndexOrThrow12;
                int i40 = i2;
                int i41 = columnIndexOrThrow11;
                int i42 = (int) sQLiteStatementPrepare.getLong(i40);
                int i43 = columnIndexOrThrow15;
                int i44 = (int) sQLiteStatementPrepare.getLong(i43);
                int i45 = columnIndexOrThrow16;
                int i46 = columnIndexOrThrow13;
                int i47 = (int) sQLiteStatementPrepare.getLong(i45);
                int i48 = columnIndexOrThrow17;
                int i49 = (int) sQLiteStatementPrepare.getLong(i48);
                int i50 = columnIndexOrThrow18;
                int i51 = (int) sQLiteStatementPrepare.getLong(i50);
                int i52 = columnIndexOrThrow19;
                if (sQLiteStatementPrepare.isNull(i52)) {
                    i5 = i23;
                    i6 = columnIndexOrThrow20;
                    text2 = null;
                } else {
                    text2 = sQLiteStatementPrepare.getText(i52);
                    i5 = i23;
                    i6 = columnIndexOrThrow20;
                }
                if (sQLiteStatementPrepare.isNull(i6)) {
                    columnIndexOrThrow20 = i6;
                    i7 = columnIndexOrThrow21;
                    text3 = null;
                } else {
                    text3 = sQLiteStatementPrepare.getText(i6);
                    columnIndexOrThrow20 = i6;
                    i7 = columnIndexOrThrow21;
                }
                if (sQLiteStatementPrepare.isNull(i7)) {
                    columnIndexOrThrow21 = i7;
                    i8 = columnIndexOrThrow22;
                    text4 = null;
                } else {
                    text4 = sQLiteStatementPrepare.getText(i7);
                    columnIndexOrThrow21 = i7;
                    i8 = columnIndexOrThrow22;
                }
                String text5 = sQLiteStatementPrepare.isNull(i8) ? null : sQLiteStatementPrepare.getText(i8);
                columnIndexOrThrow22 = i8;
                int i53 = columnIndexOrThrow23;
                String str3 = text5;
                if (((int) sQLiteStatementPrepare.getLong(i53)) != 0) {
                    i9 = columnIndexOrThrow24;
                    z2 = true;
                } else {
                    i9 = columnIndexOrThrow24;
                    z2 = false;
                }
                if (((int) sQLiteStatementPrepare.getLong(i9)) != 0) {
                    i10 = columnIndexOrThrow25;
                    z3 = true;
                } else {
                    i10 = columnIndexOrThrow25;
                    z3 = false;
                }
                int i54 = i9;
                if (((int) sQLiteStatementPrepare.getLong(i10)) != 0) {
                    i11 = columnIndexOrThrow26;
                    z4 = true;
                } else {
                    i11 = columnIndexOrThrow26;
                    z4 = false;
                }
                int i55 = i10;
                if (((int) sQLiteStatementPrepare.getLong(i11)) != 0) {
                    i12 = columnIndexOrThrow27;
                    z5 = true;
                } else {
                    i12 = columnIndexOrThrow27;
                    z5 = false;
                }
                int i56 = i11;
                if (((int) sQLiteStatementPrepare.getLong(i12)) != 0) {
                    i13 = columnIndexOrThrow28;
                    z6 = true;
                } else {
                    i13 = columnIndexOrThrow28;
                    z6 = false;
                }
                int i57 = i12;
                if (((int) sQLiteStatementPrepare.getLong(i13)) != 0) {
                    i14 = columnIndexOrThrow29;
                    z7 = true;
                } else {
                    i14 = columnIndexOrThrow29;
                    z7 = false;
                }
                int i58 = i13;
                if (((int) sQLiteStatementPrepare.getLong(i14)) != 0) {
                    i15 = columnIndexOrThrow30;
                    z8 = true;
                } else {
                    i15 = columnIndexOrThrow30;
                    z8 = false;
                }
                int i59 = i14;
                if (((int) sQLiteStatementPrepare.getLong(i15)) != 0) {
                    i16 = columnIndexOrThrow31;
                    z9 = true;
                } else {
                    i16 = columnIndexOrThrow31;
                    z9 = false;
                }
                int i60 = i15;
                if (((int) sQLiteStatementPrepare.getLong(i16)) != 0) {
                    i17 = columnIndexOrThrow32;
                    z10 = true;
                } else {
                    i17 = columnIndexOrThrow32;
                    z10 = false;
                }
                int i61 = i16;
                if (((int) sQLiteStatementPrepare.getLong(i17)) != 0) {
                    i18 = columnIndexOrThrow33;
                    z11 = true;
                } else {
                    i18 = columnIndexOrThrow33;
                    z11 = false;
                }
                int i62 = i17;
                if (((int) sQLiteStatementPrepare.getLong(i18)) != 0) {
                    i19 = columnIndexOrThrow34;
                    z12 = true;
                } else {
                    i19 = columnIndexOrThrow34;
                    z12 = false;
                }
                int i63 = i18;
                HealthMetric healthMetric = new HealthMetric(lValueOf, i20, j2, text, i21, i24, i26, i28, i30, i32, i34, i36, i38, i42, i44, i47, i49, i51, text2, text3, text4, str3, z2, z3, z4, z5, z6, z7, z8, z9, z10, z11, z12, ((int) sQLiteStatementPrepare.getLong(i19)) != 0);
                ArrayList arrayList3 = arrayList;
                arrayList3.add(healthMetric);
                columnIndexOrThrow14 = i40;
                arrayList2 = arrayList3;
                columnIndexOrThrow3 = i5;
                columnIndexOrThrow18 = i50;
                columnIndexOrThrow27 = i57;
                columnIndexOrThrow28 = i58;
                columnIndexOrThrow29 = i59;
                columnIndexOrThrow33 = i63;
                columnIndexOrThrow11 = i41;
                columnIndexOrThrow19 = i52;
                columnIndexOrThrow = i22;
                columnIndexOrThrow4 = i25;
                columnIndexOrThrow5 = i27;
                columnIndexOrThrow6 = i29;
                columnIndexOrThrow7 = i31;
                columnIndexOrThrow8 = i33;
                columnIndexOrThrow9 = i35;
                columnIndexOrThrow10 = i37;
                columnIndexOrThrow15 = i43;
                columnIndexOrThrow23 = i53;
                columnIndexOrThrow34 = i19;
                columnIndexOrThrow2 = i3;
                columnIndexOrThrow12 = i39;
                columnIndexOrThrow13 = i46;
                columnIndexOrThrow16 = i45;
                columnIndexOrThrow17 = i48;
                columnIndexOrThrow24 = i54;
                columnIndexOrThrow25 = i55;
                columnIndexOrThrow26 = i56;
                columnIndexOrThrow30 = i60;
                columnIndexOrThrow31 = i61;
                columnIndexOrThrow32 = i62;
            }
            return arrayList2;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.HealthMetricDao
    public Object queryBloodSugarSyncedWithYearToDay(final String yearToDay, final String userId, final boolean synced, final Continuation<? super List<HealthMetric>> arg3) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.HealthMetricDao_Impl$$ExternalSyntheticLambda22
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return HealthMetricDao_Impl.lambda$queryBloodSugarSyncedWithYearToDay$14(synced, yearToDay, userId, (SQLiteConnection) obj);
            }
        }, arg3);
    }

    static /* synthetic */ List lambda$queryBloodSugarSyncedWithYearToDay$14(boolean z, String str, String str2, SQLiteConnection sQLiteConnection) {
        ArrayList arrayList;
        Long lValueOf;
        int i2;
        int i3;
        String text;
        int i4;
        String text2;
        int i5;
        int i6;
        String text3;
        int i7;
        String text4;
        int i8;
        int i9;
        boolean z2;
        int i10;
        boolean z3;
        int i11;
        boolean z4;
        int i12;
        boolean z5;
        int i13;
        boolean z6;
        int i14;
        boolean z7;
        int i15;
        boolean z8;
        int i16;
        boolean z9;
        int i17;
        boolean z10;
        int i18;
        boolean z11;
        int i19;
        boolean z12;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("\n        SELECT * FROM health_metrics_data \n        WHERE is_blood_sugar_uploaded = ?\n        AND time_year_to_day = ?\n        AND (user_id = ? \n        OR user_id = \"\"\n        OR user_id IS NULL)\n        ORDER BY start_timestamp DESC\n    ");
        try {
            sQLiteStatementPrepare.mo181bindLong(1, z ? 1L : 0L);
            if (str == null) {
                sQLiteStatementPrepare.mo182bindNull(2);
            } else {
                sQLiteStatementPrepare.mo183bindText(2, str);
            }
            if (str2 == null) {
                sQLiteStatementPrepare.mo182bindNull(3);
            } else {
                sQLiteStatementPrepare.mo183bindText(3, str2);
            }
            int columnIndexOrThrow = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "id");
            int columnIndexOrThrow2 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "query_id");
            int columnIndexOrThrow3 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "start_timestamp");
            int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "time_year_to_day");
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "heart_rate_value");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "hrv_value");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "cvrr_value");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "blood_oxygen_level");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "step_count");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "diastolic_bp");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "systolic_bp");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "respiratory_rate");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "temperature_integer_part");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "temperature_fractional_part");
            int columnIndexOrThrow15 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "body_fat_integer_part");
            int columnIndexOrThrow16 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "body_fat_fractional_part");
            int columnIndexOrThrow17 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "blood_sugar_level");
            int columnIndexOrThrow18 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "blood_sugar_measurement_mode");
            int columnIndexOrThrow19 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow20 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow21 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow22 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow23 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_hrv_uploaded");
            int columnIndexOrThrow24 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_blood_oxygen_uploaded");
            int columnIndexOrThrow25 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_respiratory_rate_uploaded");
            int columnIndexOrThrow26 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_temperature_uploaded");
            int columnIndexOrThrow27 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_body_fat_uploaded");
            int columnIndexOrThrow28 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_blood_sugar_uploaded");
            int columnIndexOrThrow29 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_hrv_uploaded");
            int columnIndexOrThrow30 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_blood_oxygen_uploaded");
            int columnIndexOrThrow31 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_respiratory_rate_uploaded");
            int columnIndexOrThrow32 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_temperature_uploaded");
            int columnIndexOrThrow33 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_body_fat_uploaded");
            int columnIndexOrThrow34 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_blood_sugar_uploaded");
            ArrayList arrayList2 = new ArrayList();
            while (sQLiteStatementPrepare.step()) {
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow)) {
                    i2 = columnIndexOrThrow14;
                    arrayList = arrayList2;
                    lValueOf = null;
                } else {
                    arrayList = arrayList2;
                    lValueOf = Long.valueOf(sQLiteStatementPrepare.getLong(columnIndexOrThrow));
                    i2 = columnIndexOrThrow14;
                }
                int i20 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
                long j2 = sQLiteStatementPrepare.getLong(columnIndexOrThrow3);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow4)) {
                    i4 = columnIndexOrThrow;
                    i3 = columnIndexOrThrow2;
                    text = null;
                } else {
                    i3 = columnIndexOrThrow2;
                    text = sQLiteStatementPrepare.getText(columnIndexOrThrow4);
                    i4 = columnIndexOrThrow;
                }
                int i21 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5);
                int i22 = i4;
                int i23 = columnIndexOrThrow3;
                int i24 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                int i25 = columnIndexOrThrow4;
                int i26 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7);
                int i27 = columnIndexOrThrow5;
                int i28 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow8);
                int i29 = columnIndexOrThrow6;
                int i30 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow9);
                int i31 = columnIndexOrThrow7;
                int i32 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10);
                int i33 = columnIndexOrThrow8;
                int i34 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11);
                int i35 = columnIndexOrThrow9;
                int i36 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12);
                int i37 = columnIndexOrThrow10;
                int i38 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow13);
                int i39 = columnIndexOrThrow12;
                int i40 = i2;
                int i41 = columnIndexOrThrow11;
                int i42 = (int) sQLiteStatementPrepare.getLong(i40);
                int i43 = columnIndexOrThrow15;
                int i44 = (int) sQLiteStatementPrepare.getLong(i43);
                int i45 = columnIndexOrThrow16;
                int i46 = columnIndexOrThrow13;
                int i47 = (int) sQLiteStatementPrepare.getLong(i45);
                int i48 = columnIndexOrThrow17;
                int i49 = (int) sQLiteStatementPrepare.getLong(i48);
                int i50 = columnIndexOrThrow18;
                int i51 = (int) sQLiteStatementPrepare.getLong(i50);
                int i52 = columnIndexOrThrow19;
                if (sQLiteStatementPrepare.isNull(i52)) {
                    i5 = i23;
                    i6 = columnIndexOrThrow20;
                    text2 = null;
                } else {
                    text2 = sQLiteStatementPrepare.getText(i52);
                    i5 = i23;
                    i6 = columnIndexOrThrow20;
                }
                if (sQLiteStatementPrepare.isNull(i6)) {
                    columnIndexOrThrow20 = i6;
                    i7 = columnIndexOrThrow21;
                    text3 = null;
                } else {
                    text3 = sQLiteStatementPrepare.getText(i6);
                    columnIndexOrThrow20 = i6;
                    i7 = columnIndexOrThrow21;
                }
                if (sQLiteStatementPrepare.isNull(i7)) {
                    columnIndexOrThrow21 = i7;
                    i8 = columnIndexOrThrow22;
                    text4 = null;
                } else {
                    text4 = sQLiteStatementPrepare.getText(i7);
                    columnIndexOrThrow21 = i7;
                    i8 = columnIndexOrThrow22;
                }
                String text5 = sQLiteStatementPrepare.isNull(i8) ? null : sQLiteStatementPrepare.getText(i8);
                columnIndexOrThrow22 = i8;
                int i53 = columnIndexOrThrow23;
                String str3 = text5;
                if (((int) sQLiteStatementPrepare.getLong(i53)) != 0) {
                    i9 = columnIndexOrThrow24;
                    z2 = true;
                } else {
                    i9 = columnIndexOrThrow24;
                    z2 = false;
                }
                if (((int) sQLiteStatementPrepare.getLong(i9)) != 0) {
                    i10 = columnIndexOrThrow25;
                    z3 = true;
                } else {
                    i10 = columnIndexOrThrow25;
                    z3 = false;
                }
                int i54 = i9;
                if (((int) sQLiteStatementPrepare.getLong(i10)) != 0) {
                    i11 = columnIndexOrThrow26;
                    z4 = true;
                } else {
                    i11 = columnIndexOrThrow26;
                    z4 = false;
                }
                int i55 = i10;
                if (((int) sQLiteStatementPrepare.getLong(i11)) != 0) {
                    i12 = columnIndexOrThrow27;
                    z5 = true;
                } else {
                    i12 = columnIndexOrThrow27;
                    z5 = false;
                }
                int i56 = i11;
                if (((int) sQLiteStatementPrepare.getLong(i12)) != 0) {
                    i13 = columnIndexOrThrow28;
                    z6 = true;
                } else {
                    i13 = columnIndexOrThrow28;
                    z6 = false;
                }
                int i57 = i12;
                if (((int) sQLiteStatementPrepare.getLong(i13)) != 0) {
                    i14 = columnIndexOrThrow29;
                    z7 = true;
                } else {
                    i14 = columnIndexOrThrow29;
                    z7 = false;
                }
                int i58 = i13;
                if (((int) sQLiteStatementPrepare.getLong(i14)) != 0) {
                    i15 = columnIndexOrThrow30;
                    z8 = true;
                } else {
                    i15 = columnIndexOrThrow30;
                    z8 = false;
                }
                int i59 = i14;
                if (((int) sQLiteStatementPrepare.getLong(i15)) != 0) {
                    i16 = columnIndexOrThrow31;
                    z9 = true;
                } else {
                    i16 = columnIndexOrThrow31;
                    z9 = false;
                }
                int i60 = i15;
                if (((int) sQLiteStatementPrepare.getLong(i16)) != 0) {
                    i17 = columnIndexOrThrow32;
                    z10 = true;
                } else {
                    i17 = columnIndexOrThrow32;
                    z10 = false;
                }
                int i61 = i16;
                if (((int) sQLiteStatementPrepare.getLong(i17)) != 0) {
                    i18 = columnIndexOrThrow33;
                    z11 = true;
                } else {
                    i18 = columnIndexOrThrow33;
                    z11 = false;
                }
                int i62 = i17;
                if (((int) sQLiteStatementPrepare.getLong(i18)) != 0) {
                    i19 = columnIndexOrThrow34;
                    z12 = true;
                } else {
                    i19 = columnIndexOrThrow34;
                    z12 = false;
                }
                int i63 = i18;
                HealthMetric healthMetric = new HealthMetric(lValueOf, i20, j2, text, i21, i24, i26, i28, i30, i32, i34, i36, i38, i42, i44, i47, i49, i51, text2, text3, text4, str3, z2, z3, z4, z5, z6, z7, z8, z9, z10, z11, z12, ((int) sQLiteStatementPrepare.getLong(i19)) != 0);
                ArrayList arrayList3 = arrayList;
                arrayList3.add(healthMetric);
                columnIndexOrThrow14 = i40;
                arrayList2 = arrayList3;
                columnIndexOrThrow3 = i5;
                columnIndexOrThrow18 = i50;
                columnIndexOrThrow27 = i57;
                columnIndexOrThrow28 = i58;
                columnIndexOrThrow29 = i59;
                columnIndexOrThrow33 = i63;
                columnIndexOrThrow11 = i41;
                columnIndexOrThrow19 = i52;
                columnIndexOrThrow = i22;
                columnIndexOrThrow4 = i25;
                columnIndexOrThrow5 = i27;
                columnIndexOrThrow6 = i29;
                columnIndexOrThrow7 = i31;
                columnIndexOrThrow8 = i33;
                columnIndexOrThrow9 = i35;
                columnIndexOrThrow10 = i37;
                columnIndexOrThrow15 = i43;
                columnIndexOrThrow23 = i53;
                columnIndexOrThrow34 = i19;
                columnIndexOrThrow2 = i3;
                columnIndexOrThrow12 = i39;
                columnIndexOrThrow13 = i46;
                columnIndexOrThrow16 = i45;
                columnIndexOrThrow17 = i48;
                columnIndexOrThrow24 = i54;
                columnIndexOrThrow25 = i55;
                columnIndexOrThrow26 = i56;
                columnIndexOrThrow30 = i60;
                columnIndexOrThrow31 = i61;
                columnIndexOrThrow32 = i62;
            }
            return arrayList2;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.HealthMetricDao
    public Object queryHrvSyncedWithYearToDay(final String yearToDay, final String userId, final boolean synced, final Continuation<? super List<HealthMetric>> arg3) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.HealthMetricDao_Impl$$ExternalSyntheticLambda23
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return HealthMetricDao_Impl.lambda$queryHrvSyncedWithYearToDay$15(synced, yearToDay, userId, (SQLiteConnection) obj);
            }
        }, arg3);
    }

    static /* synthetic */ List lambda$queryHrvSyncedWithYearToDay$15(boolean z, String str, String str2, SQLiteConnection sQLiteConnection) {
        ArrayList arrayList;
        Long lValueOf;
        int i2;
        int i3;
        String text;
        int i4;
        String text2;
        int i5;
        int i6;
        String text3;
        int i7;
        String text4;
        int i8;
        int i9;
        boolean z2;
        int i10;
        boolean z3;
        int i11;
        boolean z4;
        int i12;
        boolean z5;
        int i13;
        boolean z6;
        int i14;
        boolean z7;
        int i15;
        boolean z8;
        int i16;
        boolean z9;
        int i17;
        boolean z10;
        int i18;
        boolean z11;
        int i19;
        boolean z12;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("\n        SELECT * FROM health_metrics_data \n        WHERE is_hrv_uploaded = ?\n        AND time_year_to_day = ?\n        AND (user_id = ? \n        OR user_id = \"\"\n        OR user_id IS NULL)\n        ORDER BY start_timestamp DESC\n    ");
        try {
            sQLiteStatementPrepare.mo181bindLong(1, z ? 1L : 0L);
            if (str == null) {
                sQLiteStatementPrepare.mo182bindNull(2);
            } else {
                sQLiteStatementPrepare.mo183bindText(2, str);
            }
            if (str2 == null) {
                sQLiteStatementPrepare.mo182bindNull(3);
            } else {
                sQLiteStatementPrepare.mo183bindText(3, str2);
            }
            int columnIndexOrThrow = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "id");
            int columnIndexOrThrow2 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "query_id");
            int columnIndexOrThrow3 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "start_timestamp");
            int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "time_year_to_day");
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "heart_rate_value");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "hrv_value");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "cvrr_value");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "blood_oxygen_level");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "step_count");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "diastolic_bp");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "systolic_bp");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "respiratory_rate");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "temperature_integer_part");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "temperature_fractional_part");
            int columnIndexOrThrow15 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "body_fat_integer_part");
            int columnIndexOrThrow16 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "body_fat_fractional_part");
            int columnIndexOrThrow17 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "blood_sugar_level");
            int columnIndexOrThrow18 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "blood_sugar_measurement_mode");
            int columnIndexOrThrow19 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow20 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow21 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow22 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow23 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_hrv_uploaded");
            int columnIndexOrThrow24 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_blood_oxygen_uploaded");
            int columnIndexOrThrow25 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_respiratory_rate_uploaded");
            int columnIndexOrThrow26 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_temperature_uploaded");
            int columnIndexOrThrow27 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_body_fat_uploaded");
            int columnIndexOrThrow28 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_blood_sugar_uploaded");
            int columnIndexOrThrow29 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_hrv_uploaded");
            int columnIndexOrThrow30 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_blood_oxygen_uploaded");
            int columnIndexOrThrow31 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_respiratory_rate_uploaded");
            int columnIndexOrThrow32 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_temperature_uploaded");
            int columnIndexOrThrow33 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_body_fat_uploaded");
            int columnIndexOrThrow34 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_blood_sugar_uploaded");
            ArrayList arrayList2 = new ArrayList();
            while (sQLiteStatementPrepare.step()) {
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow)) {
                    i2 = columnIndexOrThrow14;
                    arrayList = arrayList2;
                    lValueOf = null;
                } else {
                    arrayList = arrayList2;
                    lValueOf = Long.valueOf(sQLiteStatementPrepare.getLong(columnIndexOrThrow));
                    i2 = columnIndexOrThrow14;
                }
                int i20 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
                long j2 = sQLiteStatementPrepare.getLong(columnIndexOrThrow3);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow4)) {
                    i4 = columnIndexOrThrow;
                    i3 = columnIndexOrThrow2;
                    text = null;
                } else {
                    i3 = columnIndexOrThrow2;
                    text = sQLiteStatementPrepare.getText(columnIndexOrThrow4);
                    i4 = columnIndexOrThrow;
                }
                int i21 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5);
                int i22 = i4;
                int i23 = columnIndexOrThrow3;
                int i24 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                int i25 = columnIndexOrThrow4;
                int i26 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7);
                int i27 = columnIndexOrThrow5;
                int i28 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow8);
                int i29 = columnIndexOrThrow6;
                int i30 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow9);
                int i31 = columnIndexOrThrow7;
                int i32 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10);
                int i33 = columnIndexOrThrow8;
                int i34 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11);
                int i35 = columnIndexOrThrow9;
                int i36 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12);
                int i37 = columnIndexOrThrow10;
                int i38 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow13);
                int i39 = columnIndexOrThrow12;
                int i40 = i2;
                int i41 = columnIndexOrThrow11;
                int i42 = (int) sQLiteStatementPrepare.getLong(i40);
                int i43 = columnIndexOrThrow15;
                int i44 = (int) sQLiteStatementPrepare.getLong(i43);
                int i45 = columnIndexOrThrow16;
                int i46 = columnIndexOrThrow13;
                int i47 = (int) sQLiteStatementPrepare.getLong(i45);
                int i48 = columnIndexOrThrow17;
                int i49 = (int) sQLiteStatementPrepare.getLong(i48);
                int i50 = columnIndexOrThrow18;
                int i51 = (int) sQLiteStatementPrepare.getLong(i50);
                int i52 = columnIndexOrThrow19;
                if (sQLiteStatementPrepare.isNull(i52)) {
                    i5 = i23;
                    i6 = columnIndexOrThrow20;
                    text2 = null;
                } else {
                    text2 = sQLiteStatementPrepare.getText(i52);
                    i5 = i23;
                    i6 = columnIndexOrThrow20;
                }
                if (sQLiteStatementPrepare.isNull(i6)) {
                    columnIndexOrThrow20 = i6;
                    i7 = columnIndexOrThrow21;
                    text3 = null;
                } else {
                    text3 = sQLiteStatementPrepare.getText(i6);
                    columnIndexOrThrow20 = i6;
                    i7 = columnIndexOrThrow21;
                }
                if (sQLiteStatementPrepare.isNull(i7)) {
                    columnIndexOrThrow21 = i7;
                    i8 = columnIndexOrThrow22;
                    text4 = null;
                } else {
                    text4 = sQLiteStatementPrepare.getText(i7);
                    columnIndexOrThrow21 = i7;
                    i8 = columnIndexOrThrow22;
                }
                String text5 = sQLiteStatementPrepare.isNull(i8) ? null : sQLiteStatementPrepare.getText(i8);
                columnIndexOrThrow22 = i8;
                int i53 = columnIndexOrThrow23;
                String str3 = text5;
                if (((int) sQLiteStatementPrepare.getLong(i53)) != 0) {
                    i9 = columnIndexOrThrow24;
                    z2 = true;
                } else {
                    i9 = columnIndexOrThrow24;
                    z2 = false;
                }
                if (((int) sQLiteStatementPrepare.getLong(i9)) != 0) {
                    i10 = columnIndexOrThrow25;
                    z3 = true;
                } else {
                    i10 = columnIndexOrThrow25;
                    z3 = false;
                }
                int i54 = i9;
                if (((int) sQLiteStatementPrepare.getLong(i10)) != 0) {
                    i11 = columnIndexOrThrow26;
                    z4 = true;
                } else {
                    i11 = columnIndexOrThrow26;
                    z4 = false;
                }
                int i55 = i10;
                if (((int) sQLiteStatementPrepare.getLong(i11)) != 0) {
                    i12 = columnIndexOrThrow27;
                    z5 = true;
                } else {
                    i12 = columnIndexOrThrow27;
                    z5 = false;
                }
                int i56 = i11;
                if (((int) sQLiteStatementPrepare.getLong(i12)) != 0) {
                    i13 = columnIndexOrThrow28;
                    z6 = true;
                } else {
                    i13 = columnIndexOrThrow28;
                    z6 = false;
                }
                int i57 = i12;
                if (((int) sQLiteStatementPrepare.getLong(i13)) != 0) {
                    i14 = columnIndexOrThrow29;
                    z7 = true;
                } else {
                    i14 = columnIndexOrThrow29;
                    z7 = false;
                }
                int i58 = i13;
                if (((int) sQLiteStatementPrepare.getLong(i14)) != 0) {
                    i15 = columnIndexOrThrow30;
                    z8 = true;
                } else {
                    i15 = columnIndexOrThrow30;
                    z8 = false;
                }
                int i59 = i14;
                if (((int) sQLiteStatementPrepare.getLong(i15)) != 0) {
                    i16 = columnIndexOrThrow31;
                    z9 = true;
                } else {
                    i16 = columnIndexOrThrow31;
                    z9 = false;
                }
                int i60 = i15;
                if (((int) sQLiteStatementPrepare.getLong(i16)) != 0) {
                    i17 = columnIndexOrThrow32;
                    z10 = true;
                } else {
                    i17 = columnIndexOrThrow32;
                    z10 = false;
                }
                int i61 = i16;
                if (((int) sQLiteStatementPrepare.getLong(i17)) != 0) {
                    i18 = columnIndexOrThrow33;
                    z11 = true;
                } else {
                    i18 = columnIndexOrThrow33;
                    z11 = false;
                }
                int i62 = i17;
                if (((int) sQLiteStatementPrepare.getLong(i18)) != 0) {
                    i19 = columnIndexOrThrow34;
                    z12 = true;
                } else {
                    i19 = columnIndexOrThrow34;
                    z12 = false;
                }
                int i63 = i18;
                HealthMetric healthMetric = new HealthMetric(lValueOf, i20, j2, text, i21, i24, i26, i28, i30, i32, i34, i36, i38, i42, i44, i47, i49, i51, text2, text3, text4, str3, z2, z3, z4, z5, z6, z7, z8, z9, z10, z11, z12, ((int) sQLiteStatementPrepare.getLong(i19)) != 0);
                ArrayList arrayList3 = arrayList;
                arrayList3.add(healthMetric);
                columnIndexOrThrow14 = i40;
                arrayList2 = arrayList3;
                columnIndexOrThrow3 = i5;
                columnIndexOrThrow18 = i50;
                columnIndexOrThrow27 = i57;
                columnIndexOrThrow28 = i58;
                columnIndexOrThrow29 = i59;
                columnIndexOrThrow33 = i63;
                columnIndexOrThrow11 = i41;
                columnIndexOrThrow19 = i52;
                columnIndexOrThrow = i22;
                columnIndexOrThrow4 = i25;
                columnIndexOrThrow5 = i27;
                columnIndexOrThrow6 = i29;
                columnIndexOrThrow7 = i31;
                columnIndexOrThrow8 = i33;
                columnIndexOrThrow9 = i35;
                columnIndexOrThrow10 = i37;
                columnIndexOrThrow15 = i43;
                columnIndexOrThrow23 = i53;
                columnIndexOrThrow34 = i19;
                columnIndexOrThrow2 = i3;
                columnIndexOrThrow12 = i39;
                columnIndexOrThrow13 = i46;
                columnIndexOrThrow16 = i45;
                columnIndexOrThrow17 = i48;
                columnIndexOrThrow24 = i54;
                columnIndexOrThrow25 = i55;
                columnIndexOrThrow26 = i56;
                columnIndexOrThrow30 = i60;
                columnIndexOrThrow31 = i61;
                columnIndexOrThrow32 = i62;
            }
            return arrayList2;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.HealthMetricDao
    public Object queryRespiratoryRateSyncedWithYearToDay(final String yearToDay, final String userId, final boolean synced, final Continuation<? super List<HealthMetric>> arg3) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.HealthMetricDao_Impl$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return HealthMetricDao_Impl.lambda$queryRespiratoryRateSyncedWithYearToDay$16(synced, yearToDay, userId, (SQLiteConnection) obj);
            }
        }, arg3);
    }

    static /* synthetic */ List lambda$queryRespiratoryRateSyncedWithYearToDay$16(boolean z, String str, String str2, SQLiteConnection sQLiteConnection) {
        ArrayList arrayList;
        Long lValueOf;
        int i2;
        int i3;
        String text;
        int i4;
        String text2;
        int i5;
        int i6;
        String text3;
        int i7;
        String text4;
        int i8;
        int i9;
        boolean z2;
        int i10;
        boolean z3;
        int i11;
        boolean z4;
        int i12;
        boolean z5;
        int i13;
        boolean z6;
        int i14;
        boolean z7;
        int i15;
        boolean z8;
        int i16;
        boolean z9;
        int i17;
        boolean z10;
        int i18;
        boolean z11;
        int i19;
        boolean z12;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("\n        SELECT * FROM health_metrics_data \n        WHERE is_respiratory_rate_uploaded = ?\n        AND time_year_to_day = ?\n        AND (user_id = ? \n        OR user_id = \"\"\n        OR user_id IS NULL)\n        ORDER BY start_timestamp DESC\n    ");
        try {
            sQLiteStatementPrepare.mo181bindLong(1, z ? 1L : 0L);
            if (str == null) {
                sQLiteStatementPrepare.mo182bindNull(2);
            } else {
                sQLiteStatementPrepare.mo183bindText(2, str);
            }
            if (str2 == null) {
                sQLiteStatementPrepare.mo182bindNull(3);
            } else {
                sQLiteStatementPrepare.mo183bindText(3, str2);
            }
            int columnIndexOrThrow = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "id");
            int columnIndexOrThrow2 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "query_id");
            int columnIndexOrThrow3 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "start_timestamp");
            int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "time_year_to_day");
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "heart_rate_value");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "hrv_value");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "cvrr_value");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "blood_oxygen_level");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "step_count");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "diastolic_bp");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "systolic_bp");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "respiratory_rate");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "temperature_integer_part");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "temperature_fractional_part");
            int columnIndexOrThrow15 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "body_fat_integer_part");
            int columnIndexOrThrow16 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "body_fat_fractional_part");
            int columnIndexOrThrow17 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "blood_sugar_level");
            int columnIndexOrThrow18 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "blood_sugar_measurement_mode");
            int columnIndexOrThrow19 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow20 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow21 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow22 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow23 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_hrv_uploaded");
            int columnIndexOrThrow24 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_blood_oxygen_uploaded");
            int columnIndexOrThrow25 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_respiratory_rate_uploaded");
            int columnIndexOrThrow26 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_temperature_uploaded");
            int columnIndexOrThrow27 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_body_fat_uploaded");
            int columnIndexOrThrow28 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_blood_sugar_uploaded");
            int columnIndexOrThrow29 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_hrv_uploaded");
            int columnIndexOrThrow30 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_blood_oxygen_uploaded");
            int columnIndexOrThrow31 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_respiratory_rate_uploaded");
            int columnIndexOrThrow32 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_temperature_uploaded");
            int columnIndexOrThrow33 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_body_fat_uploaded");
            int columnIndexOrThrow34 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_blood_sugar_uploaded");
            ArrayList arrayList2 = new ArrayList();
            while (sQLiteStatementPrepare.step()) {
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow)) {
                    i2 = columnIndexOrThrow14;
                    arrayList = arrayList2;
                    lValueOf = null;
                } else {
                    arrayList = arrayList2;
                    lValueOf = Long.valueOf(sQLiteStatementPrepare.getLong(columnIndexOrThrow));
                    i2 = columnIndexOrThrow14;
                }
                int i20 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
                long j2 = sQLiteStatementPrepare.getLong(columnIndexOrThrow3);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow4)) {
                    i4 = columnIndexOrThrow;
                    i3 = columnIndexOrThrow2;
                    text = null;
                } else {
                    i3 = columnIndexOrThrow2;
                    text = sQLiteStatementPrepare.getText(columnIndexOrThrow4);
                    i4 = columnIndexOrThrow;
                }
                int i21 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5);
                int i22 = i4;
                int i23 = columnIndexOrThrow3;
                int i24 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                int i25 = columnIndexOrThrow4;
                int i26 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7);
                int i27 = columnIndexOrThrow5;
                int i28 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow8);
                int i29 = columnIndexOrThrow6;
                int i30 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow9);
                int i31 = columnIndexOrThrow7;
                int i32 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10);
                int i33 = columnIndexOrThrow8;
                int i34 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11);
                int i35 = columnIndexOrThrow9;
                int i36 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12);
                int i37 = columnIndexOrThrow10;
                int i38 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow13);
                int i39 = columnIndexOrThrow12;
                int i40 = i2;
                int i41 = columnIndexOrThrow11;
                int i42 = (int) sQLiteStatementPrepare.getLong(i40);
                int i43 = columnIndexOrThrow15;
                int i44 = (int) sQLiteStatementPrepare.getLong(i43);
                int i45 = columnIndexOrThrow16;
                int i46 = columnIndexOrThrow13;
                int i47 = (int) sQLiteStatementPrepare.getLong(i45);
                int i48 = columnIndexOrThrow17;
                int i49 = (int) sQLiteStatementPrepare.getLong(i48);
                int i50 = columnIndexOrThrow18;
                int i51 = (int) sQLiteStatementPrepare.getLong(i50);
                int i52 = columnIndexOrThrow19;
                if (sQLiteStatementPrepare.isNull(i52)) {
                    i5 = i23;
                    i6 = columnIndexOrThrow20;
                    text2 = null;
                } else {
                    text2 = sQLiteStatementPrepare.getText(i52);
                    i5 = i23;
                    i6 = columnIndexOrThrow20;
                }
                if (sQLiteStatementPrepare.isNull(i6)) {
                    columnIndexOrThrow20 = i6;
                    i7 = columnIndexOrThrow21;
                    text3 = null;
                } else {
                    text3 = sQLiteStatementPrepare.getText(i6);
                    columnIndexOrThrow20 = i6;
                    i7 = columnIndexOrThrow21;
                }
                if (sQLiteStatementPrepare.isNull(i7)) {
                    columnIndexOrThrow21 = i7;
                    i8 = columnIndexOrThrow22;
                    text4 = null;
                } else {
                    text4 = sQLiteStatementPrepare.getText(i7);
                    columnIndexOrThrow21 = i7;
                    i8 = columnIndexOrThrow22;
                }
                String text5 = sQLiteStatementPrepare.isNull(i8) ? null : sQLiteStatementPrepare.getText(i8);
                columnIndexOrThrow22 = i8;
                int i53 = columnIndexOrThrow23;
                String str3 = text5;
                if (((int) sQLiteStatementPrepare.getLong(i53)) != 0) {
                    i9 = columnIndexOrThrow24;
                    z2 = true;
                } else {
                    i9 = columnIndexOrThrow24;
                    z2 = false;
                }
                if (((int) sQLiteStatementPrepare.getLong(i9)) != 0) {
                    i10 = columnIndexOrThrow25;
                    z3 = true;
                } else {
                    i10 = columnIndexOrThrow25;
                    z3 = false;
                }
                int i54 = i9;
                if (((int) sQLiteStatementPrepare.getLong(i10)) != 0) {
                    i11 = columnIndexOrThrow26;
                    z4 = true;
                } else {
                    i11 = columnIndexOrThrow26;
                    z4 = false;
                }
                int i55 = i10;
                if (((int) sQLiteStatementPrepare.getLong(i11)) != 0) {
                    i12 = columnIndexOrThrow27;
                    z5 = true;
                } else {
                    i12 = columnIndexOrThrow27;
                    z5 = false;
                }
                int i56 = i11;
                if (((int) sQLiteStatementPrepare.getLong(i12)) != 0) {
                    i13 = columnIndexOrThrow28;
                    z6 = true;
                } else {
                    i13 = columnIndexOrThrow28;
                    z6 = false;
                }
                int i57 = i12;
                if (((int) sQLiteStatementPrepare.getLong(i13)) != 0) {
                    i14 = columnIndexOrThrow29;
                    z7 = true;
                } else {
                    i14 = columnIndexOrThrow29;
                    z7 = false;
                }
                int i58 = i13;
                if (((int) sQLiteStatementPrepare.getLong(i14)) != 0) {
                    i15 = columnIndexOrThrow30;
                    z8 = true;
                } else {
                    i15 = columnIndexOrThrow30;
                    z8 = false;
                }
                int i59 = i14;
                if (((int) sQLiteStatementPrepare.getLong(i15)) != 0) {
                    i16 = columnIndexOrThrow31;
                    z9 = true;
                } else {
                    i16 = columnIndexOrThrow31;
                    z9 = false;
                }
                int i60 = i15;
                if (((int) sQLiteStatementPrepare.getLong(i16)) != 0) {
                    i17 = columnIndexOrThrow32;
                    z10 = true;
                } else {
                    i17 = columnIndexOrThrow32;
                    z10 = false;
                }
                int i61 = i16;
                if (((int) sQLiteStatementPrepare.getLong(i17)) != 0) {
                    i18 = columnIndexOrThrow33;
                    z11 = true;
                } else {
                    i18 = columnIndexOrThrow33;
                    z11 = false;
                }
                int i62 = i17;
                if (((int) sQLiteStatementPrepare.getLong(i18)) != 0) {
                    i19 = columnIndexOrThrow34;
                    z12 = true;
                } else {
                    i19 = columnIndexOrThrow34;
                    z12 = false;
                }
                int i63 = i18;
                HealthMetric healthMetric = new HealthMetric(lValueOf, i20, j2, text, i21, i24, i26, i28, i30, i32, i34, i36, i38, i42, i44, i47, i49, i51, text2, text3, text4, str3, z2, z3, z4, z5, z6, z7, z8, z9, z10, z11, z12, ((int) sQLiteStatementPrepare.getLong(i19)) != 0);
                ArrayList arrayList3 = arrayList;
                arrayList3.add(healthMetric);
                columnIndexOrThrow14 = i40;
                arrayList2 = arrayList3;
                columnIndexOrThrow3 = i5;
                columnIndexOrThrow18 = i50;
                columnIndexOrThrow27 = i57;
                columnIndexOrThrow28 = i58;
                columnIndexOrThrow29 = i59;
                columnIndexOrThrow33 = i63;
                columnIndexOrThrow11 = i41;
                columnIndexOrThrow19 = i52;
                columnIndexOrThrow = i22;
                columnIndexOrThrow4 = i25;
                columnIndexOrThrow5 = i27;
                columnIndexOrThrow6 = i29;
                columnIndexOrThrow7 = i31;
                columnIndexOrThrow8 = i33;
                columnIndexOrThrow9 = i35;
                columnIndexOrThrow10 = i37;
                columnIndexOrThrow15 = i43;
                columnIndexOrThrow23 = i53;
                columnIndexOrThrow34 = i19;
                columnIndexOrThrow2 = i3;
                columnIndexOrThrow12 = i39;
                columnIndexOrThrow13 = i46;
                columnIndexOrThrow16 = i45;
                columnIndexOrThrow17 = i48;
                columnIndexOrThrow24 = i54;
                columnIndexOrThrow25 = i55;
                columnIndexOrThrow26 = i56;
                columnIndexOrThrow30 = i60;
                columnIndexOrThrow31 = i61;
                columnIndexOrThrow32 = i62;
            }
            return arrayList2;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.HealthMetricDao
    public Object queryTemperatureSyncedWithYearToDay(final String yearToDay, final String userId, final boolean synced, final Continuation<? super List<HealthMetric>> arg3) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.HealthMetricDao_Impl$$ExternalSyntheticLambda19
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return HealthMetricDao_Impl.lambda$queryTemperatureSyncedWithYearToDay$17(synced, yearToDay, userId, (SQLiteConnection) obj);
            }
        }, arg3);
    }

    static /* synthetic */ List lambda$queryTemperatureSyncedWithYearToDay$17(boolean z, String str, String str2, SQLiteConnection sQLiteConnection) {
        ArrayList arrayList;
        Long lValueOf;
        int i2;
        int i3;
        String text;
        int i4;
        String text2;
        int i5;
        int i6;
        String text3;
        int i7;
        String text4;
        int i8;
        int i9;
        boolean z2;
        int i10;
        boolean z3;
        int i11;
        boolean z4;
        int i12;
        boolean z5;
        int i13;
        boolean z6;
        int i14;
        boolean z7;
        int i15;
        boolean z8;
        int i16;
        boolean z9;
        int i17;
        boolean z10;
        int i18;
        boolean z11;
        int i19;
        boolean z12;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("\n        SELECT * FROM health_metrics_data \n        WHERE is_temperature_uploaded = ?\n        AND time_year_to_day = ?\n        AND (user_id = ? \n        OR user_id = \"\"\n        OR user_id IS NULL)\n        ORDER BY start_timestamp DESC\n    ");
        try {
            sQLiteStatementPrepare.mo181bindLong(1, z ? 1L : 0L);
            if (str == null) {
                sQLiteStatementPrepare.mo182bindNull(2);
            } else {
                sQLiteStatementPrepare.mo183bindText(2, str);
            }
            if (str2 == null) {
                sQLiteStatementPrepare.mo182bindNull(3);
            } else {
                sQLiteStatementPrepare.mo183bindText(3, str2);
            }
            int columnIndexOrThrow = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "id");
            int columnIndexOrThrow2 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "query_id");
            int columnIndexOrThrow3 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "start_timestamp");
            int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "time_year_to_day");
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "heart_rate_value");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "hrv_value");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "cvrr_value");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "blood_oxygen_level");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "step_count");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "diastolic_bp");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "systolic_bp");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "respiratory_rate");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "temperature_integer_part");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "temperature_fractional_part");
            int columnIndexOrThrow15 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "body_fat_integer_part");
            int columnIndexOrThrow16 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "body_fat_fractional_part");
            int columnIndexOrThrow17 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "blood_sugar_level");
            int columnIndexOrThrow18 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "blood_sugar_measurement_mode");
            int columnIndexOrThrow19 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow20 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow21 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow22 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow23 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_hrv_uploaded");
            int columnIndexOrThrow24 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_blood_oxygen_uploaded");
            int columnIndexOrThrow25 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_respiratory_rate_uploaded");
            int columnIndexOrThrow26 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_temperature_uploaded");
            int columnIndexOrThrow27 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_body_fat_uploaded");
            int columnIndexOrThrow28 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_blood_sugar_uploaded");
            int columnIndexOrThrow29 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_hrv_uploaded");
            int columnIndexOrThrow30 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_blood_oxygen_uploaded");
            int columnIndexOrThrow31 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_respiratory_rate_uploaded");
            int columnIndexOrThrow32 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_temperature_uploaded");
            int columnIndexOrThrow33 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_body_fat_uploaded");
            int columnIndexOrThrow34 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_blood_sugar_uploaded");
            ArrayList arrayList2 = new ArrayList();
            while (sQLiteStatementPrepare.step()) {
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow)) {
                    i2 = columnIndexOrThrow14;
                    arrayList = arrayList2;
                    lValueOf = null;
                } else {
                    arrayList = arrayList2;
                    lValueOf = Long.valueOf(sQLiteStatementPrepare.getLong(columnIndexOrThrow));
                    i2 = columnIndexOrThrow14;
                }
                int i20 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
                long j2 = sQLiteStatementPrepare.getLong(columnIndexOrThrow3);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow4)) {
                    i4 = columnIndexOrThrow;
                    i3 = columnIndexOrThrow2;
                    text = null;
                } else {
                    i3 = columnIndexOrThrow2;
                    text = sQLiteStatementPrepare.getText(columnIndexOrThrow4);
                    i4 = columnIndexOrThrow;
                }
                int i21 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5);
                int i22 = i4;
                int i23 = columnIndexOrThrow3;
                int i24 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                int i25 = columnIndexOrThrow4;
                int i26 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7);
                int i27 = columnIndexOrThrow5;
                int i28 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow8);
                int i29 = columnIndexOrThrow6;
                int i30 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow9);
                int i31 = columnIndexOrThrow7;
                int i32 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10);
                int i33 = columnIndexOrThrow8;
                int i34 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11);
                int i35 = columnIndexOrThrow9;
                int i36 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12);
                int i37 = columnIndexOrThrow10;
                int i38 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow13);
                int i39 = columnIndexOrThrow12;
                int i40 = i2;
                int i41 = columnIndexOrThrow11;
                int i42 = (int) sQLiteStatementPrepare.getLong(i40);
                int i43 = columnIndexOrThrow15;
                int i44 = (int) sQLiteStatementPrepare.getLong(i43);
                int i45 = columnIndexOrThrow16;
                int i46 = columnIndexOrThrow13;
                int i47 = (int) sQLiteStatementPrepare.getLong(i45);
                int i48 = columnIndexOrThrow17;
                int i49 = (int) sQLiteStatementPrepare.getLong(i48);
                int i50 = columnIndexOrThrow18;
                int i51 = (int) sQLiteStatementPrepare.getLong(i50);
                int i52 = columnIndexOrThrow19;
                if (sQLiteStatementPrepare.isNull(i52)) {
                    i5 = i23;
                    i6 = columnIndexOrThrow20;
                    text2 = null;
                } else {
                    text2 = sQLiteStatementPrepare.getText(i52);
                    i5 = i23;
                    i6 = columnIndexOrThrow20;
                }
                if (sQLiteStatementPrepare.isNull(i6)) {
                    columnIndexOrThrow20 = i6;
                    i7 = columnIndexOrThrow21;
                    text3 = null;
                } else {
                    text3 = sQLiteStatementPrepare.getText(i6);
                    columnIndexOrThrow20 = i6;
                    i7 = columnIndexOrThrow21;
                }
                if (sQLiteStatementPrepare.isNull(i7)) {
                    columnIndexOrThrow21 = i7;
                    i8 = columnIndexOrThrow22;
                    text4 = null;
                } else {
                    text4 = sQLiteStatementPrepare.getText(i7);
                    columnIndexOrThrow21 = i7;
                    i8 = columnIndexOrThrow22;
                }
                String text5 = sQLiteStatementPrepare.isNull(i8) ? null : sQLiteStatementPrepare.getText(i8);
                columnIndexOrThrow22 = i8;
                int i53 = columnIndexOrThrow23;
                String str3 = text5;
                if (((int) sQLiteStatementPrepare.getLong(i53)) != 0) {
                    i9 = columnIndexOrThrow24;
                    z2 = true;
                } else {
                    i9 = columnIndexOrThrow24;
                    z2 = false;
                }
                if (((int) sQLiteStatementPrepare.getLong(i9)) != 0) {
                    i10 = columnIndexOrThrow25;
                    z3 = true;
                } else {
                    i10 = columnIndexOrThrow25;
                    z3 = false;
                }
                int i54 = i9;
                if (((int) sQLiteStatementPrepare.getLong(i10)) != 0) {
                    i11 = columnIndexOrThrow26;
                    z4 = true;
                } else {
                    i11 = columnIndexOrThrow26;
                    z4 = false;
                }
                int i55 = i10;
                if (((int) sQLiteStatementPrepare.getLong(i11)) != 0) {
                    i12 = columnIndexOrThrow27;
                    z5 = true;
                } else {
                    i12 = columnIndexOrThrow27;
                    z5 = false;
                }
                int i56 = i11;
                if (((int) sQLiteStatementPrepare.getLong(i12)) != 0) {
                    i13 = columnIndexOrThrow28;
                    z6 = true;
                } else {
                    i13 = columnIndexOrThrow28;
                    z6 = false;
                }
                int i57 = i12;
                if (((int) sQLiteStatementPrepare.getLong(i13)) != 0) {
                    i14 = columnIndexOrThrow29;
                    z7 = true;
                } else {
                    i14 = columnIndexOrThrow29;
                    z7 = false;
                }
                int i58 = i13;
                if (((int) sQLiteStatementPrepare.getLong(i14)) != 0) {
                    i15 = columnIndexOrThrow30;
                    z8 = true;
                } else {
                    i15 = columnIndexOrThrow30;
                    z8 = false;
                }
                int i59 = i14;
                if (((int) sQLiteStatementPrepare.getLong(i15)) != 0) {
                    i16 = columnIndexOrThrow31;
                    z9 = true;
                } else {
                    i16 = columnIndexOrThrow31;
                    z9 = false;
                }
                int i60 = i15;
                if (((int) sQLiteStatementPrepare.getLong(i16)) != 0) {
                    i17 = columnIndexOrThrow32;
                    z10 = true;
                } else {
                    i17 = columnIndexOrThrow32;
                    z10 = false;
                }
                int i61 = i16;
                if (((int) sQLiteStatementPrepare.getLong(i17)) != 0) {
                    i18 = columnIndexOrThrow33;
                    z11 = true;
                } else {
                    i18 = columnIndexOrThrow33;
                    z11 = false;
                }
                int i62 = i17;
                if (((int) sQLiteStatementPrepare.getLong(i18)) != 0) {
                    i19 = columnIndexOrThrow34;
                    z12 = true;
                } else {
                    i19 = columnIndexOrThrow34;
                    z12 = false;
                }
                int i63 = i18;
                HealthMetric healthMetric = new HealthMetric(lValueOf, i20, j2, text, i21, i24, i26, i28, i30, i32, i34, i36, i38, i42, i44, i47, i49, i51, text2, text3, text4, str3, z2, z3, z4, z5, z6, z7, z8, z9, z10, z11, z12, ((int) sQLiteStatementPrepare.getLong(i19)) != 0);
                ArrayList arrayList3 = arrayList;
                arrayList3.add(healthMetric);
                columnIndexOrThrow14 = i40;
                arrayList2 = arrayList3;
                columnIndexOrThrow3 = i5;
                columnIndexOrThrow18 = i50;
                columnIndexOrThrow27 = i57;
                columnIndexOrThrow28 = i58;
                columnIndexOrThrow29 = i59;
                columnIndexOrThrow33 = i63;
                columnIndexOrThrow11 = i41;
                columnIndexOrThrow19 = i52;
                columnIndexOrThrow = i22;
                columnIndexOrThrow4 = i25;
                columnIndexOrThrow5 = i27;
                columnIndexOrThrow6 = i29;
                columnIndexOrThrow7 = i31;
                columnIndexOrThrow8 = i33;
                columnIndexOrThrow9 = i35;
                columnIndexOrThrow10 = i37;
                columnIndexOrThrow15 = i43;
                columnIndexOrThrow23 = i53;
                columnIndexOrThrow34 = i19;
                columnIndexOrThrow2 = i3;
                columnIndexOrThrow12 = i39;
                columnIndexOrThrow13 = i46;
                columnIndexOrThrow16 = i45;
                columnIndexOrThrow17 = i48;
                columnIndexOrThrow24 = i54;
                columnIndexOrThrow25 = i55;
                columnIndexOrThrow26 = i56;
                columnIndexOrThrow30 = i60;
                columnIndexOrThrow31 = i61;
                columnIndexOrThrow32 = i62;
            }
            return arrayList2;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.HealthMetricDao
    public Object markBloodOxygenAsSynced(final List<Long> ids, final boolean synced, final Continuation<? super Integer> arg2) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE health_metrics_data SET is_blood_oxygen_uploaded = ? WHERE id IN (");
        StringUtil.appendPlaceholders(sb, ids.size());
        sb.append(")");
        final String string = sb.toString();
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.HealthMetricDao_Impl$$ExternalSyntheticLambda14
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return HealthMetricDao_Impl.lambda$markBloodOxygenAsSynced$18(string, synced, ids, (SQLiteConnection) obj);
            }
        }, arg2);
    }

    static /* synthetic */ Integer lambda$markBloodOxygenAsSynced$18(String str, boolean z, List list, SQLiteConnection sQLiteConnection) {
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare(str);
        try {
            sQLiteStatementPrepare.mo181bindLong(1, z ? 1L : 0L);
            Iterator it2 = list.iterator();
            int i2 = 2;
            while (it2.hasNext()) {
                Long l = (Long) it2.next();
                if (l == null) {
                    sQLiteStatementPrepare.mo182bindNull(i2);
                } else {
                    sQLiteStatementPrepare.mo181bindLong(i2, l.longValue());
                }
                i2++;
            }
            sQLiteStatementPrepare.step();
            return Integer.valueOf(SQLiteConnectionUtil.getTotalChangedRows(sQLiteConnection));
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.HealthMetricDao
    public Object markBloodSugarAsSynced(final List<Long> ids, final boolean synced, final Continuation<? super Integer> arg2) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE health_metrics_data SET is_blood_sugar_uploaded = ? WHERE id IN (");
        StringUtil.appendPlaceholders(sb, ids.size());
        sb.append(")");
        final String string = sb.toString();
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.HealthMetricDao_Impl$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return HealthMetricDao_Impl.lambda$markBloodSugarAsSynced$19(string, synced, ids, (SQLiteConnection) obj);
            }
        }, arg2);
    }

    static /* synthetic */ Integer lambda$markBloodSugarAsSynced$19(String str, boolean z, List list, SQLiteConnection sQLiteConnection) {
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare(str);
        try {
            sQLiteStatementPrepare.mo181bindLong(1, z ? 1L : 0L);
            Iterator it2 = list.iterator();
            int i2 = 2;
            while (it2.hasNext()) {
                Long l = (Long) it2.next();
                if (l == null) {
                    sQLiteStatementPrepare.mo182bindNull(i2);
                } else {
                    sQLiteStatementPrepare.mo181bindLong(i2, l.longValue());
                }
                i2++;
            }
            sQLiteStatementPrepare.step();
            return Integer.valueOf(SQLiteConnectionUtil.getTotalChangedRows(sQLiteConnection));
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.HealthMetricDao
    public Object markHrvAsSynced(final List<Long> ids, final boolean synced, final Continuation<? super Integer> arg2) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE health_metrics_data SET is_hrv_uploaded = ? WHERE id IN (");
        StringUtil.appendPlaceholders(sb, ids.size());
        sb.append(")");
        final String string = sb.toString();
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.HealthMetricDao_Impl$$ExternalSyntheticLambda12
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return HealthMetricDao_Impl.lambda$markHrvAsSynced$20(string, synced, ids, (SQLiteConnection) obj);
            }
        }, arg2);
    }

    static /* synthetic */ Integer lambda$markHrvAsSynced$20(String str, boolean z, List list, SQLiteConnection sQLiteConnection) {
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare(str);
        try {
            sQLiteStatementPrepare.mo181bindLong(1, z ? 1L : 0L);
            Iterator it2 = list.iterator();
            int i2 = 2;
            while (it2.hasNext()) {
                Long l = (Long) it2.next();
                if (l == null) {
                    sQLiteStatementPrepare.mo182bindNull(i2);
                } else {
                    sQLiteStatementPrepare.mo181bindLong(i2, l.longValue());
                }
                i2++;
            }
            sQLiteStatementPrepare.step();
            return Integer.valueOf(SQLiteConnectionUtil.getTotalChangedRows(sQLiteConnection));
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.HealthMetricDao
    public Object markRespiratoryRateAsSynced(final List<Long> ids, final boolean synced, final Continuation<? super Integer> arg2) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE health_metrics_data SET is_respiratory_rate_uploaded = ? WHERE id IN (");
        StringUtil.appendPlaceholders(sb, ids.size());
        sb.append(")");
        final String string = sb.toString();
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.HealthMetricDao_Impl$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return HealthMetricDao_Impl.lambda$markRespiratoryRateAsSynced$21(string, synced, ids, (SQLiteConnection) obj);
            }
        }, arg2);
    }

    static /* synthetic */ Integer lambda$markRespiratoryRateAsSynced$21(String str, boolean z, List list, SQLiteConnection sQLiteConnection) {
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare(str);
        try {
            sQLiteStatementPrepare.mo181bindLong(1, z ? 1L : 0L);
            Iterator it2 = list.iterator();
            int i2 = 2;
            while (it2.hasNext()) {
                Long l = (Long) it2.next();
                if (l == null) {
                    sQLiteStatementPrepare.mo182bindNull(i2);
                } else {
                    sQLiteStatementPrepare.mo181bindLong(i2, l.longValue());
                }
                i2++;
            }
            sQLiteStatementPrepare.step();
            return Integer.valueOf(SQLiteConnectionUtil.getTotalChangedRows(sQLiteConnection));
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.HealthMetricDao
    public Object markTemperatureAsSynced(final List<Long> ids, final boolean synced, final Continuation<? super Integer> arg2) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE health_metrics_data SET is_temperature_uploaded = ? WHERE id IN (");
        StringUtil.appendPlaceholders(sb, ids.size());
        sb.append(")");
        final String string = sb.toString();
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.HealthMetricDao_Impl$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return HealthMetricDao_Impl.lambda$markTemperatureAsSynced$22(string, synced, ids, (SQLiteConnection) obj);
            }
        }, arg2);
    }

    static /* synthetic */ Integer lambda$markTemperatureAsSynced$22(String str, boolean z, List list, SQLiteConnection sQLiteConnection) {
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare(str);
        try {
            sQLiteStatementPrepare.mo181bindLong(1, z ? 1L : 0L);
            Iterator it2 = list.iterator();
            int i2 = 2;
            while (it2.hasNext()) {
                Long l = (Long) it2.next();
                if (l == null) {
                    sQLiteStatementPrepare.mo182bindNull(i2);
                } else {
                    sQLiteStatementPrepare.mo181bindLong(i2, l.longValue());
                }
                i2++;
            }
            sQLiteStatementPrepare.step();
            return Integer.valueOf(SQLiteConnectionUtil.getTotalChangedRows(sQLiteConnection));
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.HealthMetricDao
    public Object deleteById(final long id, final Continuation<? super Integer> arg1) {
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.HealthMetricDao_Impl$$ExternalSyntheticLambda25
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return HealthMetricDao_Impl.lambda$deleteById$23(id, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    static /* synthetic */ Integer lambda$deleteById$23(long j2, SQLiteConnection sQLiteConnection) {
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("DELETE FROM health_metrics_data WHERE id = ?");
        try {
            sQLiteStatementPrepare.mo181bindLong(1, j2);
            sQLiteStatementPrepare.step();
            return Integer.valueOf(SQLiteConnectionUtil.getTotalChangedRows(sQLiteConnection));
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.HealthMetricDao
    public Object deleteAll(final Continuation<? super Integer> arg0) {
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.HealthMetricDao_Impl$$ExternalSyntheticLambda15
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return HealthMetricDao_Impl.lambda$deleteAll$24((SQLiteConnection) obj);
            }
        }, arg0);
    }

    static /* synthetic */ Integer lambda$deleteAll$24(SQLiteConnection sQLiteConnection) {
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("DELETE FROM health_metrics_data");
        try {
            sQLiteStatementPrepare.step();
            return Integer.valueOf(SQLiteConnectionUtil.getTotalChangedRows(sQLiteConnection));
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.HealthMetricDao
    public Object deleteAllByUser(final String userId, final Continuation<? super Integer> arg1) {
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.HealthMetricDao_Impl$$ExternalSyntheticLambda21
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return HealthMetricDao_Impl.lambda$deleteAllByUser$25(userId, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    static /* synthetic */ Integer lambda$deleteAllByUser$25(String str, SQLiteConnection sQLiteConnection) {
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("DELETE FROM health_metrics_data WHERE user_id = ?");
        try {
            if (str == null) {
                sQLiteStatementPrepare.mo182bindNull(1);
            } else {
                sQLiteStatementPrepare.mo183bindText(1, str);
            }
            sQLiteStatementPrepare.step();
            return Integer.valueOf(SQLiteConnectionUtil.getTotalChangedRows(sQLiteConnection));
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
