package com.yucheng.smarthealthpro.database.room.dao;

import androidx.room.EntityDeleteOrUpdateAdapter;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteConnectionUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.SQLiteStatement;
import com.facebook.AccessToken;
import com.yucheng.smarthealthpro.database.room.bean.DataGroupIdUpdate;
import com.yucheng.smarthealthpro.database.room.bean.SportRecord;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;

/* loaded from: classes4.dex */
public final class SportRecordDao_Impl implements SportRecordDao {
    private final RoomDatabase __db;
    private final EntityInsertAdapter<SportRecord> __insertAdapterOfSportRecord = new EntityInsertAdapter<SportRecord>() { // from class: com.yucheng.smarthealthpro.database.room.dao.SportRecordDao_Impl.1
        @Override // androidx.room.EntityInsertAdapter
        protected String createQuery() {
            return "INSERT OR REPLACE INTO `sport_data` (`id`,`query_id`,`activity_type`,`start_timestamp`,`time_year_to_day`,`total_steps`,`total_distance`,`last_distance`,`total_calories`,`last_calories`,`pace_per_km`,`avg_heart_rate`,`duration_seconds`,`speed_kmh`,`start_coordinates`,`end_coordinates`,`path_coordinates`,`user_id`,`device_type`,`device_mac_address`,`data_group_id`,`is_uploaded`,`is_other_uploaded`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // androidx.room.EntityInsertAdapter
        public void bind(SQLiteStatement sQLiteStatement, SportRecord sportRecord) {
            if (sportRecord.getId() == null) {
                sQLiteStatement.mo182bindNull(1);
            } else {
                sQLiteStatement.mo181bindLong(1, sportRecord.getId().longValue());
            }
            sQLiteStatement.mo181bindLong(2, sportRecord.getQueryID());
            sQLiteStatement.mo181bindLong(3, sportRecord.getType());
            sQLiteStatement.mo181bindLong(4, sportRecord.getBeginDate());
            if (sportRecord.getTimeYearToDay() == null) {
                sQLiteStatement.mo182bindNull(5);
            } else {
                sQLiteStatement.mo183bindText(5, sportRecord.getTimeYearToDay());
            }
            sQLiteStatement.mo181bindLong(6, sportRecord.getTotalSteps());
            sQLiteStatement.mo180bindDouble(7, sportRecord.getTotalDistance());
            sQLiteStatement.mo180bindDouble(8, sportRecord.getLastDistance());
            sQLiteStatement.mo181bindLong(9, sportRecord.getTotalCalories());
            sQLiteStatement.mo181bindLong(10, sportRecord.getLastCalories());
            if (sportRecord.getMinkm() == null) {
                sQLiteStatement.mo182bindNull(11);
            } else {
                sQLiteStatement.mo183bindText(11, sportRecord.getMinkm());
            }
            sQLiteStatement.mo181bindLong(12, sportRecord.getHeartRate());
            sQLiteStatement.mo181bindLong(13, sportRecord.getRunDuration());
            sQLiteStatement.mo180bindDouble(14, sportRecord.getKmh());
            if (sportRecord.getStartPoint() == null) {
                sQLiteStatement.mo182bindNull(15);
            } else {
                sQLiteStatement.mo183bindText(15, sportRecord.getStartPoint());
            }
            if (sportRecord.getEndPoint() == null) {
                sQLiteStatement.mo182bindNull(16);
            } else {
                sQLiteStatement.mo183bindText(16, sportRecord.getEndPoint());
            }
            if (sportRecord.getPathLinePoints() == null) {
                sQLiteStatement.mo182bindNull(17);
            } else {
                sQLiteStatement.mo183bindText(17, sportRecord.getPathLinePoints());
            }
            if (sportRecord.getUserId() == null) {
                sQLiteStatement.mo182bindNull(18);
            } else {
                sQLiteStatement.mo183bindText(18, sportRecord.getUserId());
            }
            if (sportRecord.getDeviceType() == null) {
                sQLiteStatement.mo182bindNull(19);
            } else {
                sQLiteStatement.mo183bindText(19, sportRecord.getDeviceType());
            }
            if (sportRecord.getDeviceMacAddress() == null) {
                sQLiteStatement.mo182bindNull(20);
            } else {
                sQLiteStatement.mo183bindText(20, sportRecord.getDeviceMacAddress());
            }
            if (sportRecord.getDataGroupId() == null) {
                sQLiteStatement.mo182bindNull(21);
            } else {
                sQLiteStatement.mo183bindText(21, sportRecord.getDataGroupId());
            }
            sQLiteStatement.mo181bindLong(22, sportRecord.isUploaded() ? 1L : 0L);
            sQLiteStatement.mo181bindLong(23, sportRecord.isOtherUploaded() ? 1L : 0L);
        }
    };
    private final EntityDeleteOrUpdateAdapter<SportRecord> __updateAdapterOfSportRecord = new EntityDeleteOrUpdateAdapter<SportRecord>() { // from class: com.yucheng.smarthealthpro.database.room.dao.SportRecordDao_Impl.2
        @Override // androidx.room.EntityDeleteOrUpdateAdapter
        protected String createQuery() {
            return "UPDATE OR ABORT `sport_data` SET `id` = ?,`query_id` = ?,`activity_type` = ?,`start_timestamp` = ?,`time_year_to_day` = ?,`total_steps` = ?,`total_distance` = ?,`last_distance` = ?,`total_calories` = ?,`last_calories` = ?,`pace_per_km` = ?,`avg_heart_rate` = ?,`duration_seconds` = ?,`speed_kmh` = ?,`start_coordinates` = ?,`end_coordinates` = ?,`path_coordinates` = ?,`user_id` = ?,`device_type` = ?,`device_mac_address` = ?,`data_group_id` = ?,`is_uploaded` = ?,`is_other_uploaded` = ? WHERE `id` = ?";
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // androidx.room.EntityDeleteOrUpdateAdapter
        public void bind(SQLiteStatement sQLiteStatement, SportRecord sportRecord) {
            if (sportRecord.getId() == null) {
                sQLiteStatement.mo182bindNull(1);
            } else {
                sQLiteStatement.mo181bindLong(1, sportRecord.getId().longValue());
            }
            sQLiteStatement.mo181bindLong(2, sportRecord.getQueryID());
            sQLiteStatement.mo181bindLong(3, sportRecord.getType());
            sQLiteStatement.mo181bindLong(4, sportRecord.getBeginDate());
            if (sportRecord.getTimeYearToDay() == null) {
                sQLiteStatement.mo182bindNull(5);
            } else {
                sQLiteStatement.mo183bindText(5, sportRecord.getTimeYearToDay());
            }
            sQLiteStatement.mo181bindLong(6, sportRecord.getTotalSteps());
            sQLiteStatement.mo180bindDouble(7, sportRecord.getTotalDistance());
            sQLiteStatement.mo180bindDouble(8, sportRecord.getLastDistance());
            sQLiteStatement.mo181bindLong(9, sportRecord.getTotalCalories());
            sQLiteStatement.mo181bindLong(10, sportRecord.getLastCalories());
            if (sportRecord.getMinkm() == null) {
                sQLiteStatement.mo182bindNull(11);
            } else {
                sQLiteStatement.mo183bindText(11, sportRecord.getMinkm());
            }
            sQLiteStatement.mo181bindLong(12, sportRecord.getHeartRate());
            sQLiteStatement.mo181bindLong(13, sportRecord.getRunDuration());
            sQLiteStatement.mo180bindDouble(14, sportRecord.getKmh());
            if (sportRecord.getStartPoint() == null) {
                sQLiteStatement.mo182bindNull(15);
            } else {
                sQLiteStatement.mo183bindText(15, sportRecord.getStartPoint());
            }
            if (sportRecord.getEndPoint() == null) {
                sQLiteStatement.mo182bindNull(16);
            } else {
                sQLiteStatement.mo183bindText(16, sportRecord.getEndPoint());
            }
            if (sportRecord.getPathLinePoints() == null) {
                sQLiteStatement.mo182bindNull(17);
            } else {
                sQLiteStatement.mo183bindText(17, sportRecord.getPathLinePoints());
            }
            if (sportRecord.getUserId() == null) {
                sQLiteStatement.mo182bindNull(18);
            } else {
                sQLiteStatement.mo183bindText(18, sportRecord.getUserId());
            }
            if (sportRecord.getDeviceType() == null) {
                sQLiteStatement.mo182bindNull(19);
            } else {
                sQLiteStatement.mo183bindText(19, sportRecord.getDeviceType());
            }
            if (sportRecord.getDeviceMacAddress() == null) {
                sQLiteStatement.mo182bindNull(20);
            } else {
                sQLiteStatement.mo183bindText(20, sportRecord.getDeviceMacAddress());
            }
            if (sportRecord.getDataGroupId() == null) {
                sQLiteStatement.mo182bindNull(21);
            } else {
                sQLiteStatement.mo183bindText(21, sportRecord.getDataGroupId());
            }
            sQLiteStatement.mo181bindLong(22, sportRecord.isUploaded() ? 1L : 0L);
            sQLiteStatement.mo181bindLong(23, sportRecord.isOtherUploaded() ? 1L : 0L);
            if (sportRecord.getId() == null) {
                sQLiteStatement.mo182bindNull(24);
            } else {
                sQLiteStatement.mo181bindLong(24, sportRecord.getId().longValue());
            }
        }
    };
    private final EntityDeleteOrUpdateAdapter<DataGroupIdUpdate> __updateAdapterOfDataGroupIdUpdateAsSportRecord = new EntityDeleteOrUpdateAdapter<DataGroupIdUpdate>() { // from class: com.yucheng.smarthealthpro.database.room.dao.SportRecordDao_Impl.3
        @Override // androidx.room.EntityDeleteOrUpdateAdapter
        protected String createQuery() {
            return "UPDATE OR ABORT `sport_data` SET `id` = ?,`data_group_id` = ? WHERE `id` = ?";
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

    public SportRecordDao_Impl(final RoomDatabase __db) {
        this.__db = __db;
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.SportRecordDao
    public Object insert(final SportRecord metric, final Continuation<? super Long> $completion) {
        metric.getClass();
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.SportRecordDao_Impl$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f$0.lambda$insert$0(metric, (SQLiteConnection) obj);
            }
        }, $completion);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Long lambda$insert$0(SportRecord sportRecord, SQLiteConnection sQLiteConnection) {
        return Long.valueOf(this.__insertAdapterOfSportRecord.insertAndReturnId(sQLiteConnection, sportRecord));
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.SportRecordDao
    public Object insertAll(final List<SportRecord> metrics, final Continuation<? super List<Long>> $completion) {
        metrics.getClass();
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.SportRecordDao_Impl$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f$0.lambda$insertAll$1(metrics, (SQLiteConnection) obj);
            }
        }, $completion);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ List lambda$insertAll$1(List list, SQLiteConnection sQLiteConnection) {
        return this.__insertAdapterOfSportRecord.insertAndReturnIdsList(sQLiteConnection, list);
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.SportRecordDao
    public Object update(final SportRecord metric, final Continuation<? super Unit> $completion) {
        metric.getClass();
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.SportRecordDao_Impl$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f$0.lambda$update$2(metric, (SQLiteConnection) obj);
            }
        }, $completion);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Unit lambda$update$2(SportRecord sportRecord, SQLiteConnection sQLiteConnection) throws Exception {
        this.__updateAdapterOfSportRecord.handle(sQLiteConnection, sportRecord);
        return Unit.INSTANCE;
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.SportRecordDao
    public Object updateDataGroupIds(final List<DataGroupIdUpdate> updates, final Continuation<? super Unit> $completion) {
        updates.getClass();
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.SportRecordDao_Impl$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f$0.lambda$updateDataGroupIds$3(updates, (SQLiteConnection) obj);
            }
        }, $completion);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Unit lambda$updateDataGroupIds$3(List list, SQLiteConnection sQLiteConnection) throws Exception {
        this.__updateAdapterOfDataGroupIdUpdateAsSportRecord.handleMultiple(sQLiteConnection, list);
        return Unit.INSTANCE;
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.SportRecordDao
    public Object getById(final long id, final Continuation<? super SportRecord> $completion) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.SportRecordDao_Impl$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SportRecordDao_Impl.lambda$getById$4(id, (SQLiteConnection) obj);
            }
        }, $completion);
    }

    static /* synthetic */ SportRecord lambda$getById$4(long j2, SQLiteConnection sQLiteConnection) {
        String text;
        int i2;
        String text2;
        int i3;
        String text3;
        int i4;
        String text4;
        int i5;
        String text5;
        int i6;
        String text6;
        int i7;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("SELECT * FROM sport_data WHERE id = ?");
        try {
            sQLiteStatementPrepare.mo181bindLong(1, j2);
            int columnIndexOrThrow = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "id");
            int columnIndexOrThrow2 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "query_id");
            int columnIndexOrThrow3 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "activity_type");
            int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "start_timestamp");
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "time_year_to_day");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "total_steps");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "total_distance");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "last_distance");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "total_calories");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "last_calories");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "pace_per_km");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "avg_heart_rate");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "duration_seconds");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "speed_kmh");
            int columnIndexOrThrow15 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "start_coordinates");
            int columnIndexOrThrow16 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "end_coordinates");
            int columnIndexOrThrow17 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "path_coordinates");
            int columnIndexOrThrow18 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow19 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow20 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow21 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow22 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            int columnIndexOrThrow23 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_uploaded");
            SportRecord sportRecord = null;
            if (sQLiteStatementPrepare.step()) {
                Long lValueOf = sQLiteStatementPrepare.isNull(columnIndexOrThrow) ? null : Long.valueOf(sQLiteStatementPrepare.getLong(columnIndexOrThrow));
                int i8 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
                int i9 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow3);
                long j3 = sQLiteStatementPrepare.getLong(columnIndexOrThrow4);
                String text7 = sQLiteStatementPrepare.isNull(columnIndexOrThrow5) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow5);
                int i10 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                float f2 = (float) sQLiteStatementPrepare.getDouble(columnIndexOrThrow7);
                float f3 = (float) sQLiteStatementPrepare.getDouble(columnIndexOrThrow8);
                int i11 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow9);
                int i12 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10);
                String text8 = sQLiteStatementPrepare.isNull(columnIndexOrThrow11) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow11);
                int i13 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12);
                int i14 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow13);
                float f4 = (float) sQLiteStatementPrepare.getDouble(columnIndexOrThrow14);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow15)) {
                    i2 = columnIndexOrThrow16;
                    text = null;
                } else {
                    text = sQLiteStatementPrepare.getText(columnIndexOrThrow15);
                    i2 = columnIndexOrThrow16;
                }
                if (sQLiteStatementPrepare.isNull(i2)) {
                    i3 = columnIndexOrThrow17;
                    text2 = null;
                } else {
                    text2 = sQLiteStatementPrepare.getText(i2);
                    i3 = columnIndexOrThrow17;
                }
                if (sQLiteStatementPrepare.isNull(i3)) {
                    i4 = columnIndexOrThrow18;
                    text3 = null;
                } else {
                    text3 = sQLiteStatementPrepare.getText(i3);
                    i4 = columnIndexOrThrow18;
                }
                if (sQLiteStatementPrepare.isNull(i4)) {
                    i5 = columnIndexOrThrow19;
                    text4 = null;
                } else {
                    text4 = sQLiteStatementPrepare.getText(i4);
                    i5 = columnIndexOrThrow19;
                }
                if (sQLiteStatementPrepare.isNull(i5)) {
                    i6 = columnIndexOrThrow20;
                    text5 = null;
                } else {
                    text5 = sQLiteStatementPrepare.getText(i5);
                    i6 = columnIndexOrThrow20;
                }
                if (sQLiteStatementPrepare.isNull(i6)) {
                    i7 = columnIndexOrThrow21;
                    text6 = null;
                } else {
                    text6 = sQLiteStatementPrepare.getText(i6);
                    i7 = columnIndexOrThrow21;
                }
                sportRecord = new SportRecord(lValueOf, i8, i9, j3, text7, i10, f2, f3, i11, i12, text8, i13, i14, f4, text, text2, text3, text4, text5, text6, sQLiteStatementPrepare.isNull(i7) ? null : sQLiteStatementPrepare.getText(i7), ((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow22)) != 0, ((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow23)) != 0);
            }
            return sportRecord;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.SportRecordDao
    public Object getByUser(final String userId, final Continuation<? super List<SportRecord>> $completion) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.SportRecordDao_Impl$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SportRecordDao_Impl.lambda$getByUser$5(userId, (SQLiteConnection) obj);
            }
        }, $completion);
    }

    static /* synthetic */ List lambda$getByUser$5(String str, SQLiteConnection sQLiteConnection) {
        int i2;
        Long lValueOf;
        int i3;
        String text;
        int i4;
        String text2;
        int i5;
        int i6;
        int i7;
        String text3;
        String text4;
        int i8;
        String text5;
        int i9;
        String text6;
        int i10;
        String text7;
        int i11;
        String text8;
        int i12;
        int i13;
        boolean z;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("SELECT * FROM sport_data WHERE user_id = ? ORDER BY start_timestamp DESC");
        try {
            if (str == null) {
                sQLiteStatementPrepare.mo182bindNull(1);
            } else {
                sQLiteStatementPrepare.mo183bindText(1, str);
            }
            int columnIndexOrThrow = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "id");
            int columnIndexOrThrow2 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "query_id");
            int columnIndexOrThrow3 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "activity_type");
            int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "start_timestamp");
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "time_year_to_day");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "total_steps");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "total_distance");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "last_distance");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "total_calories");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "last_calories");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "pace_per_km");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "avg_heart_rate");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "duration_seconds");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "speed_kmh");
            int columnIndexOrThrow15 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "start_coordinates");
            int columnIndexOrThrow16 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "end_coordinates");
            int columnIndexOrThrow17 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "path_coordinates");
            int columnIndexOrThrow18 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow19 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow20 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow21 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow22 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            int columnIndexOrThrow23 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_uploaded");
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
                int i14 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
                ArrayList arrayList2 = arrayList;
                int i15 = columnIndexOrThrow2;
                int i16 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow3);
                long j2 = sQLiteStatementPrepare.getLong(columnIndexOrThrow4);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow5)) {
                    i4 = columnIndexOrThrow3;
                    text = null;
                } else {
                    text = sQLiteStatementPrepare.getText(columnIndexOrThrow5);
                    i4 = columnIndexOrThrow3;
                }
                int i17 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                int i18 = columnIndexOrThrow4;
                float f2 = (float) sQLiteStatementPrepare.getDouble(columnIndexOrThrow7);
                int i19 = columnIndexOrThrow5;
                float f3 = (float) sQLiteStatementPrepare.getDouble(columnIndexOrThrow8);
                int i20 = columnIndexOrThrow6;
                int i21 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow9);
                int i22 = columnIndexOrThrow7;
                int i23 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow11)) {
                    i5 = columnIndexOrThrow8;
                    text2 = null;
                } else {
                    text2 = sQLiteStatementPrepare.getText(columnIndexOrThrow11);
                    i5 = columnIndexOrThrow8;
                }
                int i24 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12);
                int i25 = columnIndexOrThrow10;
                int i26 = i3;
                int i27 = columnIndexOrThrow9;
                int i28 = (int) sQLiteStatementPrepare.getLong(i26);
                int i29 = columnIndexOrThrow12;
                int i30 = i2;
                int i31 = columnIndexOrThrow11;
                float f4 = (float) sQLiteStatementPrepare.getDouble(i30);
                int i32 = columnIndexOrThrow15;
                if (sQLiteStatementPrepare.isNull(i32)) {
                    i6 = columnIndexOrThrow;
                    i7 = columnIndexOrThrow16;
                    text3 = null;
                } else {
                    i6 = columnIndexOrThrow;
                    i7 = columnIndexOrThrow16;
                    text3 = sQLiteStatementPrepare.getText(i32);
                }
                if (sQLiteStatementPrepare.isNull(i7)) {
                    columnIndexOrThrow16 = i7;
                    i8 = columnIndexOrThrow17;
                    text4 = null;
                } else {
                    text4 = sQLiteStatementPrepare.getText(i7);
                    columnIndexOrThrow16 = i7;
                    i8 = columnIndexOrThrow17;
                }
                if (sQLiteStatementPrepare.isNull(i8)) {
                    columnIndexOrThrow17 = i8;
                    i9 = columnIndexOrThrow18;
                    text5 = null;
                } else {
                    text5 = sQLiteStatementPrepare.getText(i8);
                    columnIndexOrThrow17 = i8;
                    i9 = columnIndexOrThrow18;
                }
                if (sQLiteStatementPrepare.isNull(i9)) {
                    columnIndexOrThrow18 = i9;
                    i10 = columnIndexOrThrow19;
                    text6 = null;
                } else {
                    text6 = sQLiteStatementPrepare.getText(i9);
                    columnIndexOrThrow18 = i9;
                    i10 = columnIndexOrThrow19;
                }
                if (sQLiteStatementPrepare.isNull(i10)) {
                    columnIndexOrThrow19 = i10;
                    i11 = columnIndexOrThrow20;
                    text7 = null;
                } else {
                    text7 = sQLiteStatementPrepare.getText(i10);
                    columnIndexOrThrow19 = i10;
                    i11 = columnIndexOrThrow20;
                }
                if (sQLiteStatementPrepare.isNull(i11)) {
                    columnIndexOrThrow20 = i11;
                    i12 = columnIndexOrThrow21;
                    text8 = null;
                } else {
                    text8 = sQLiteStatementPrepare.getText(i11);
                    columnIndexOrThrow20 = i11;
                    i12 = columnIndexOrThrow21;
                }
                String text9 = sQLiteStatementPrepare.isNull(i12) ? null : sQLiteStatementPrepare.getText(i12);
                columnIndexOrThrow21 = i12;
                int i33 = columnIndexOrThrow22;
                String str2 = text9;
                if (((int) sQLiteStatementPrepare.getLong(i33)) != 0) {
                    i13 = columnIndexOrThrow23;
                    z = true;
                } else {
                    i13 = columnIndexOrThrow23;
                    z = false;
                }
                SportRecord sportRecord = new SportRecord(lValueOf, i14, i16, j2, text, i17, f2, f3, i21, i23, text2, i24, i28, f4, text3, text4, text5, text6, text7, text8, str2, z, ((int) sQLiteStatementPrepare.getLong(i13)) != 0);
                arrayList = arrayList2;
                arrayList.add(sportRecord);
                columnIndexOrThrow13 = i26;
                columnIndexOrThrow2 = i15;
                columnIndexOrThrow14 = i30;
                columnIndexOrThrow9 = i27;
                columnIndexOrThrow11 = i31;
                columnIndexOrThrow3 = i4;
                columnIndexOrThrow4 = i18;
                columnIndexOrThrow5 = i19;
                columnIndexOrThrow6 = i20;
                columnIndexOrThrow7 = i22;
                columnIndexOrThrow8 = i5;
                columnIndexOrThrow12 = i29;
                columnIndexOrThrow22 = i33;
                columnIndexOrThrow = i6;
                columnIndexOrThrow15 = i32;
                columnIndexOrThrow23 = i13;
                columnIndexOrThrow10 = i25;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.SportRecordDao
    public Object getByStartTimestamp(final long startTimestamp, final Continuation<? super List<SportRecord>> $completion) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.SportRecordDao_Impl$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SportRecordDao_Impl.lambda$getByStartTimestamp$6(startTimestamp, (SQLiteConnection) obj);
            }
        }, $completion);
    }

    static /* synthetic */ List lambda$getByStartTimestamp$6(long j2, SQLiteConnection sQLiteConnection) {
        int i2;
        Long lValueOf;
        int i3;
        String text;
        int i4;
        String text2;
        int i5;
        int i6;
        String text3;
        int i7;
        String str;
        String text4;
        int i8;
        String text5;
        int i9;
        String text6;
        int i10;
        String text7;
        int i11;
        int i12;
        boolean z;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("SELECT * FROM sport_data WHERE start_timestamp = ?");
        try {
            sQLiteStatementPrepare.mo181bindLong(1, j2);
            int columnIndexOrThrow = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "id");
            int columnIndexOrThrow2 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "query_id");
            int columnIndexOrThrow3 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "activity_type");
            int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "start_timestamp");
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "time_year_to_day");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "total_steps");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "total_distance");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "last_distance");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "total_calories");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "last_calories");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "pace_per_km");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "avg_heart_rate");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "duration_seconds");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "speed_kmh");
            int columnIndexOrThrow15 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "start_coordinates");
            int columnIndexOrThrow16 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "end_coordinates");
            int columnIndexOrThrow17 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "path_coordinates");
            int columnIndexOrThrow18 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow19 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow20 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow21 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow22 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            int columnIndexOrThrow23 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_uploaded");
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
                int i13 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
                int i14 = columnIndexOrThrow;
                int i15 = columnIndexOrThrow2;
                int i16 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow3);
                long j3 = sQLiteStatementPrepare.getLong(columnIndexOrThrow4);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow5)) {
                    i4 = columnIndexOrThrow3;
                    text = null;
                } else {
                    text = sQLiteStatementPrepare.getText(columnIndexOrThrow5);
                    i4 = columnIndexOrThrow3;
                }
                int i17 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                int i18 = columnIndexOrThrow4;
                float f2 = (float) sQLiteStatementPrepare.getDouble(columnIndexOrThrow7);
                int i19 = columnIndexOrThrow5;
                float f3 = (float) sQLiteStatementPrepare.getDouble(columnIndexOrThrow8);
                int i20 = columnIndexOrThrow6;
                int i21 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow9);
                int i22 = columnIndexOrThrow7;
                int i23 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow11)) {
                    i5 = columnIndexOrThrow8;
                    text2 = null;
                } else {
                    text2 = sQLiteStatementPrepare.getText(columnIndexOrThrow11);
                    i5 = columnIndexOrThrow8;
                }
                int i24 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12);
                int i25 = columnIndexOrThrow10;
                int i26 = i3;
                int i27 = columnIndexOrThrow9;
                int i28 = (int) sQLiteStatementPrepare.getLong(i26);
                int i29 = columnIndexOrThrow12;
                int i30 = i2;
                int i31 = columnIndexOrThrow11;
                float f4 = (float) sQLiteStatementPrepare.getDouble(i30);
                int i32 = columnIndexOrThrow15;
                if (sQLiteStatementPrepare.isNull(i32)) {
                    i6 = i26;
                    text3 = null;
                } else {
                    i6 = i26;
                    text3 = sQLiteStatementPrepare.getText(i32);
                }
                int i33 = columnIndexOrThrow16;
                if (sQLiteStatementPrepare.isNull(i33)) {
                    columnIndexOrThrow16 = i33;
                    i7 = columnIndexOrThrow17;
                    str = null;
                } else {
                    String text8 = sQLiteStatementPrepare.getText(i33);
                    columnIndexOrThrow16 = i33;
                    i7 = columnIndexOrThrow17;
                    str = text8;
                }
                if (sQLiteStatementPrepare.isNull(i7)) {
                    columnIndexOrThrow17 = i7;
                    i8 = columnIndexOrThrow18;
                    text4 = null;
                } else {
                    text4 = sQLiteStatementPrepare.getText(i7);
                    columnIndexOrThrow17 = i7;
                    i8 = columnIndexOrThrow18;
                }
                if (sQLiteStatementPrepare.isNull(i8)) {
                    columnIndexOrThrow18 = i8;
                    i9 = columnIndexOrThrow19;
                    text5 = null;
                } else {
                    text5 = sQLiteStatementPrepare.getText(i8);
                    columnIndexOrThrow18 = i8;
                    i9 = columnIndexOrThrow19;
                }
                if (sQLiteStatementPrepare.isNull(i9)) {
                    columnIndexOrThrow19 = i9;
                    i10 = columnIndexOrThrow20;
                    text6 = null;
                } else {
                    text6 = sQLiteStatementPrepare.getText(i9);
                    columnIndexOrThrow19 = i9;
                    i10 = columnIndexOrThrow20;
                }
                if (sQLiteStatementPrepare.isNull(i10)) {
                    columnIndexOrThrow20 = i10;
                    i11 = columnIndexOrThrow21;
                    text7 = null;
                } else {
                    text7 = sQLiteStatementPrepare.getText(i10);
                    columnIndexOrThrow20 = i10;
                    i11 = columnIndexOrThrow21;
                }
                String text9 = sQLiteStatementPrepare.isNull(i11) ? null : sQLiteStatementPrepare.getText(i11);
                columnIndexOrThrow21 = i11;
                int i34 = columnIndexOrThrow22;
                String str2 = text9;
                if (((int) sQLiteStatementPrepare.getLong(i34)) != 0) {
                    i12 = columnIndexOrThrow23;
                    z = true;
                } else {
                    i12 = columnIndexOrThrow23;
                    z = false;
                }
                arrayList.add(new SportRecord(lValueOf, i13, i16, j3, text, i17, f2, f3, i21, i23, text2, i24, i28, f4, text3, str, text4, text5, text6, text7, str2, z, ((int) sQLiteStatementPrepare.getLong(i12)) != 0));
                columnIndexOrThrow13 = i6;
                columnIndexOrThrow = i14;
                columnIndexOrThrow14 = i30;
                columnIndexOrThrow15 = i32;
                columnIndexOrThrow9 = i27;
                columnIndexOrThrow11 = i31;
                columnIndexOrThrow2 = i15;
                columnIndexOrThrow3 = i4;
                columnIndexOrThrow4 = i18;
                columnIndexOrThrow5 = i19;
                columnIndexOrThrow6 = i20;
                columnIndexOrThrow7 = i22;
                columnIndexOrThrow12 = i29;
                columnIndexOrThrow22 = i34;
                columnIndexOrThrow23 = i12;
                columnIndexOrThrow8 = i5;
                columnIndexOrThrow10 = i25;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.SportRecordDao
    public Object getByUserId(final String userId, final Continuation<? super List<SportRecord>> $completion) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.SportRecordDao_Impl$$ExternalSyntheticLambda12
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SportRecordDao_Impl.lambda$getByUserId$7(userId, (SQLiteConnection) obj);
            }
        }, $completion);
    }

    static /* synthetic */ List lambda$getByUserId$7(String str, SQLiteConnection sQLiteConnection) {
        int i2;
        Long lValueOf;
        int i3;
        String text;
        int i4;
        String text2;
        int i5;
        int i6;
        int i7;
        String text3;
        String text4;
        int i8;
        String text5;
        int i9;
        String text6;
        int i10;
        String text7;
        int i11;
        String text8;
        int i12;
        int i13;
        boolean z;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("\n        SELECT * FROM sport_data \n        WHERE user_id = ? \n        OR user_id = \"\"\n        OR user_id IS NULL\n        ORDER BY start_timestamp DESC\n    ");
        try {
            if (str == null) {
                sQLiteStatementPrepare.mo182bindNull(1);
            } else {
                sQLiteStatementPrepare.mo183bindText(1, str);
            }
            int columnIndexOrThrow = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "id");
            int columnIndexOrThrow2 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "query_id");
            int columnIndexOrThrow3 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "activity_type");
            int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "start_timestamp");
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "time_year_to_day");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "total_steps");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "total_distance");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "last_distance");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "total_calories");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "last_calories");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "pace_per_km");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "avg_heart_rate");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "duration_seconds");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "speed_kmh");
            int columnIndexOrThrow15 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "start_coordinates");
            int columnIndexOrThrow16 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "end_coordinates");
            int columnIndexOrThrow17 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "path_coordinates");
            int columnIndexOrThrow18 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow19 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow20 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow21 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow22 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            int columnIndexOrThrow23 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_uploaded");
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
                int i14 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
                ArrayList arrayList2 = arrayList;
                int i15 = columnIndexOrThrow2;
                int i16 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow3);
                long j2 = sQLiteStatementPrepare.getLong(columnIndexOrThrow4);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow5)) {
                    i4 = columnIndexOrThrow3;
                    text = null;
                } else {
                    text = sQLiteStatementPrepare.getText(columnIndexOrThrow5);
                    i4 = columnIndexOrThrow3;
                }
                int i17 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                int i18 = columnIndexOrThrow4;
                float f2 = (float) sQLiteStatementPrepare.getDouble(columnIndexOrThrow7);
                int i19 = columnIndexOrThrow5;
                float f3 = (float) sQLiteStatementPrepare.getDouble(columnIndexOrThrow8);
                int i20 = columnIndexOrThrow6;
                int i21 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow9);
                int i22 = columnIndexOrThrow7;
                int i23 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow11)) {
                    i5 = columnIndexOrThrow8;
                    text2 = null;
                } else {
                    text2 = sQLiteStatementPrepare.getText(columnIndexOrThrow11);
                    i5 = columnIndexOrThrow8;
                }
                int i24 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12);
                int i25 = columnIndexOrThrow10;
                int i26 = i3;
                int i27 = columnIndexOrThrow9;
                int i28 = (int) sQLiteStatementPrepare.getLong(i26);
                int i29 = columnIndexOrThrow12;
                int i30 = i2;
                int i31 = columnIndexOrThrow11;
                float f4 = (float) sQLiteStatementPrepare.getDouble(i30);
                int i32 = columnIndexOrThrow15;
                if (sQLiteStatementPrepare.isNull(i32)) {
                    i6 = columnIndexOrThrow;
                    i7 = columnIndexOrThrow16;
                    text3 = null;
                } else {
                    i6 = columnIndexOrThrow;
                    i7 = columnIndexOrThrow16;
                    text3 = sQLiteStatementPrepare.getText(i32);
                }
                if (sQLiteStatementPrepare.isNull(i7)) {
                    columnIndexOrThrow16 = i7;
                    i8 = columnIndexOrThrow17;
                    text4 = null;
                } else {
                    text4 = sQLiteStatementPrepare.getText(i7);
                    columnIndexOrThrow16 = i7;
                    i8 = columnIndexOrThrow17;
                }
                if (sQLiteStatementPrepare.isNull(i8)) {
                    columnIndexOrThrow17 = i8;
                    i9 = columnIndexOrThrow18;
                    text5 = null;
                } else {
                    text5 = sQLiteStatementPrepare.getText(i8);
                    columnIndexOrThrow17 = i8;
                    i9 = columnIndexOrThrow18;
                }
                if (sQLiteStatementPrepare.isNull(i9)) {
                    columnIndexOrThrow18 = i9;
                    i10 = columnIndexOrThrow19;
                    text6 = null;
                } else {
                    text6 = sQLiteStatementPrepare.getText(i9);
                    columnIndexOrThrow18 = i9;
                    i10 = columnIndexOrThrow19;
                }
                if (sQLiteStatementPrepare.isNull(i10)) {
                    columnIndexOrThrow19 = i10;
                    i11 = columnIndexOrThrow20;
                    text7 = null;
                } else {
                    text7 = sQLiteStatementPrepare.getText(i10);
                    columnIndexOrThrow19 = i10;
                    i11 = columnIndexOrThrow20;
                }
                if (sQLiteStatementPrepare.isNull(i11)) {
                    columnIndexOrThrow20 = i11;
                    i12 = columnIndexOrThrow21;
                    text8 = null;
                } else {
                    text8 = sQLiteStatementPrepare.getText(i11);
                    columnIndexOrThrow20 = i11;
                    i12 = columnIndexOrThrow21;
                }
                String text9 = sQLiteStatementPrepare.isNull(i12) ? null : sQLiteStatementPrepare.getText(i12);
                columnIndexOrThrow21 = i12;
                int i33 = columnIndexOrThrow22;
                String str2 = text9;
                if (((int) sQLiteStatementPrepare.getLong(i33)) != 0) {
                    i13 = columnIndexOrThrow23;
                    z = true;
                } else {
                    i13 = columnIndexOrThrow23;
                    z = false;
                }
                SportRecord sportRecord = new SportRecord(lValueOf, i14, i16, j2, text, i17, f2, f3, i21, i23, text2, i24, i28, f4, text3, text4, text5, text6, text7, text8, str2, z, ((int) sQLiteStatementPrepare.getLong(i13)) != 0);
                arrayList = arrayList2;
                arrayList.add(sportRecord);
                columnIndexOrThrow13 = i26;
                columnIndexOrThrow2 = i15;
                columnIndexOrThrow14 = i30;
                columnIndexOrThrow9 = i27;
                columnIndexOrThrow11 = i31;
                columnIndexOrThrow3 = i4;
                columnIndexOrThrow4 = i18;
                columnIndexOrThrow5 = i19;
                columnIndexOrThrow6 = i20;
                columnIndexOrThrow7 = i22;
                columnIndexOrThrow8 = i5;
                columnIndexOrThrow12 = i29;
                columnIndexOrThrow22 = i33;
                columnIndexOrThrow = i6;
                columnIndexOrThrow15 = i32;
                columnIndexOrThrow23 = i13;
                columnIndexOrThrow10 = i25;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.SportRecordDao
    public Object queryAll(final String userId, final Continuation<? super List<SportRecord>> $completion) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.SportRecordDao_Impl$$ExternalSyntheticLambda15
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SportRecordDao_Impl.lambda$queryAll$8(userId, (SQLiteConnection) obj);
            }
        }, $completion);
    }

    static /* synthetic */ List lambda$queryAll$8(String str, SQLiteConnection sQLiteConnection) {
        int i2;
        Long lValueOf;
        int i3;
        String text;
        int i4;
        String text2;
        int i5;
        int i6;
        int i7;
        String text3;
        String text4;
        int i8;
        String text5;
        int i9;
        String text6;
        int i10;
        String text7;
        int i11;
        String text8;
        int i12;
        int i13;
        boolean z;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("\n        SELECT * FROM sport_data \n        WHERE (user_id = ? \n        OR user_id = \"\"\n        OR user_id IS NULL)\n        ORDER BY start_timestamp ASC\n    ");
        try {
            if (str == null) {
                sQLiteStatementPrepare.mo182bindNull(1);
            } else {
                sQLiteStatementPrepare.mo183bindText(1, str);
            }
            int columnIndexOrThrow = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "id");
            int columnIndexOrThrow2 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "query_id");
            int columnIndexOrThrow3 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "activity_type");
            int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "start_timestamp");
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "time_year_to_day");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "total_steps");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "total_distance");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "last_distance");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "total_calories");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "last_calories");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "pace_per_km");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "avg_heart_rate");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "duration_seconds");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "speed_kmh");
            int columnIndexOrThrow15 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "start_coordinates");
            int columnIndexOrThrow16 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "end_coordinates");
            int columnIndexOrThrow17 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "path_coordinates");
            int columnIndexOrThrow18 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow19 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow20 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow21 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow22 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            int columnIndexOrThrow23 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_uploaded");
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
                int i14 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
                ArrayList arrayList2 = arrayList;
                int i15 = columnIndexOrThrow2;
                int i16 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow3);
                long j2 = sQLiteStatementPrepare.getLong(columnIndexOrThrow4);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow5)) {
                    i4 = columnIndexOrThrow3;
                    text = null;
                } else {
                    text = sQLiteStatementPrepare.getText(columnIndexOrThrow5);
                    i4 = columnIndexOrThrow3;
                }
                int i17 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                int i18 = columnIndexOrThrow4;
                float f2 = (float) sQLiteStatementPrepare.getDouble(columnIndexOrThrow7);
                int i19 = columnIndexOrThrow5;
                float f3 = (float) sQLiteStatementPrepare.getDouble(columnIndexOrThrow8);
                int i20 = columnIndexOrThrow6;
                int i21 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow9);
                int i22 = columnIndexOrThrow7;
                int i23 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow11)) {
                    i5 = columnIndexOrThrow8;
                    text2 = null;
                } else {
                    text2 = sQLiteStatementPrepare.getText(columnIndexOrThrow11);
                    i5 = columnIndexOrThrow8;
                }
                int i24 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12);
                int i25 = columnIndexOrThrow10;
                int i26 = i3;
                int i27 = columnIndexOrThrow9;
                int i28 = (int) sQLiteStatementPrepare.getLong(i26);
                int i29 = columnIndexOrThrow12;
                int i30 = i2;
                int i31 = columnIndexOrThrow11;
                float f4 = (float) sQLiteStatementPrepare.getDouble(i30);
                int i32 = columnIndexOrThrow15;
                if (sQLiteStatementPrepare.isNull(i32)) {
                    i6 = columnIndexOrThrow;
                    i7 = columnIndexOrThrow16;
                    text3 = null;
                } else {
                    i6 = columnIndexOrThrow;
                    i7 = columnIndexOrThrow16;
                    text3 = sQLiteStatementPrepare.getText(i32);
                }
                if (sQLiteStatementPrepare.isNull(i7)) {
                    columnIndexOrThrow16 = i7;
                    i8 = columnIndexOrThrow17;
                    text4 = null;
                } else {
                    text4 = sQLiteStatementPrepare.getText(i7);
                    columnIndexOrThrow16 = i7;
                    i8 = columnIndexOrThrow17;
                }
                if (sQLiteStatementPrepare.isNull(i8)) {
                    columnIndexOrThrow17 = i8;
                    i9 = columnIndexOrThrow18;
                    text5 = null;
                } else {
                    text5 = sQLiteStatementPrepare.getText(i8);
                    columnIndexOrThrow17 = i8;
                    i9 = columnIndexOrThrow18;
                }
                if (sQLiteStatementPrepare.isNull(i9)) {
                    columnIndexOrThrow18 = i9;
                    i10 = columnIndexOrThrow19;
                    text6 = null;
                } else {
                    text6 = sQLiteStatementPrepare.getText(i9);
                    columnIndexOrThrow18 = i9;
                    i10 = columnIndexOrThrow19;
                }
                if (sQLiteStatementPrepare.isNull(i10)) {
                    columnIndexOrThrow19 = i10;
                    i11 = columnIndexOrThrow20;
                    text7 = null;
                } else {
                    text7 = sQLiteStatementPrepare.getText(i10);
                    columnIndexOrThrow19 = i10;
                    i11 = columnIndexOrThrow20;
                }
                if (sQLiteStatementPrepare.isNull(i11)) {
                    columnIndexOrThrow20 = i11;
                    i12 = columnIndexOrThrow21;
                    text8 = null;
                } else {
                    text8 = sQLiteStatementPrepare.getText(i11);
                    columnIndexOrThrow20 = i11;
                    i12 = columnIndexOrThrow21;
                }
                String text9 = sQLiteStatementPrepare.isNull(i12) ? null : sQLiteStatementPrepare.getText(i12);
                columnIndexOrThrow21 = i12;
                int i33 = columnIndexOrThrow22;
                String str2 = text9;
                if (((int) sQLiteStatementPrepare.getLong(i33)) != 0) {
                    i13 = columnIndexOrThrow23;
                    z = true;
                } else {
                    i13 = columnIndexOrThrow23;
                    z = false;
                }
                SportRecord sportRecord = new SportRecord(lValueOf, i14, i16, j2, text, i17, f2, f3, i21, i23, text2, i24, i28, f4, text3, text4, text5, text6, text7, text8, str2, z, ((int) sQLiteStatementPrepare.getLong(i13)) != 0);
                arrayList = arrayList2;
                arrayList.add(sportRecord);
                columnIndexOrThrow13 = i26;
                columnIndexOrThrow2 = i15;
                columnIndexOrThrow14 = i30;
                columnIndexOrThrow9 = i27;
                columnIndexOrThrow11 = i31;
                columnIndexOrThrow3 = i4;
                columnIndexOrThrow4 = i18;
                columnIndexOrThrow5 = i19;
                columnIndexOrThrow6 = i20;
                columnIndexOrThrow7 = i22;
                columnIndexOrThrow8 = i5;
                columnIndexOrThrow12 = i29;
                columnIndexOrThrow22 = i33;
                columnIndexOrThrow = i6;
                columnIndexOrThrow15 = i32;
                columnIndexOrThrow23 = i13;
                columnIndexOrThrow10 = i25;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.SportRecordDao
    public Object queryByYearToDay(final String yearToDay, final String userId, final Continuation<? super List<SportRecord>> $completion) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.SportRecordDao_Impl$$ExternalSyntheticLambda13
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SportRecordDao_Impl.lambda$queryByYearToDay$9(yearToDay, userId, (SQLiteConnection) obj);
            }
        }, $completion);
    }

    static /* synthetic */ List lambda$queryByYearToDay$9(String str, String str2, SQLiteConnection sQLiteConnection) {
        int i2;
        Long lValueOf;
        int i3;
        String text;
        int i4;
        int i5;
        String text2;
        int i6;
        int i7;
        String text3;
        int i8;
        String str3;
        String text4;
        int i9;
        String text5;
        int i10;
        String text6;
        int i11;
        String text7;
        int i12;
        int i13;
        boolean z;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("\n        SELECT * FROM sport_data \n        WHERE time_year_to_day = ?\n        AND (user_id = ? \n        OR user_id = \"\"\n        OR user_id IS NULL)\n        ORDER BY start_timestamp DESC\n    ");
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
            int columnIndexOrThrow3 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "activity_type");
            int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "start_timestamp");
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "time_year_to_day");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "total_steps");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "total_distance");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "last_distance");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "total_calories");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "last_calories");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "pace_per_km");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "avg_heart_rate");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "duration_seconds");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "speed_kmh");
            int columnIndexOrThrow15 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "start_coordinates");
            int columnIndexOrThrow16 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "end_coordinates");
            int columnIndexOrThrow17 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "path_coordinates");
            int columnIndexOrThrow18 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow19 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow20 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow21 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow22 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            int columnIndexOrThrow23 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_uploaded");
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
                int i14 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
                int i15 = columnIndexOrThrow;
                int i16 = columnIndexOrThrow2;
                int i17 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow3);
                long j2 = sQLiteStatementPrepare.getLong(columnIndexOrThrow4);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow5)) {
                    i4 = columnIndexOrThrow3;
                    i5 = columnIndexOrThrow4;
                    text = null;
                } else {
                    text = sQLiteStatementPrepare.getText(columnIndexOrThrow5);
                    i4 = columnIndexOrThrow3;
                    i5 = columnIndexOrThrow4;
                }
                int i18 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                int i19 = columnIndexOrThrow5;
                float f2 = (float) sQLiteStatementPrepare.getDouble(columnIndexOrThrow7);
                int i20 = columnIndexOrThrow6;
                float f3 = (float) sQLiteStatementPrepare.getDouble(columnIndexOrThrow8);
                int i21 = columnIndexOrThrow7;
                int i22 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow9);
                int i23 = columnIndexOrThrow8;
                int i24 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow11)) {
                    i6 = columnIndexOrThrow9;
                    text2 = null;
                } else {
                    text2 = sQLiteStatementPrepare.getText(columnIndexOrThrow11);
                    i6 = columnIndexOrThrow9;
                }
                int i25 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12);
                int i26 = columnIndexOrThrow11;
                int i27 = i3;
                int i28 = columnIndexOrThrow10;
                int i29 = (int) sQLiteStatementPrepare.getLong(i27);
                int i30 = i2;
                float f4 = (float) sQLiteStatementPrepare.getDouble(i30);
                int i31 = columnIndexOrThrow15;
                if (sQLiteStatementPrepare.isNull(i31)) {
                    i7 = i4;
                    text3 = null;
                } else {
                    i7 = i4;
                    text3 = sQLiteStatementPrepare.getText(i31);
                }
                int i32 = columnIndexOrThrow16;
                if (sQLiteStatementPrepare.isNull(i32)) {
                    columnIndexOrThrow16 = i32;
                    i8 = columnIndexOrThrow17;
                    str3 = null;
                } else {
                    String text8 = sQLiteStatementPrepare.getText(i32);
                    columnIndexOrThrow16 = i32;
                    i8 = columnIndexOrThrow17;
                    str3 = text8;
                }
                if (sQLiteStatementPrepare.isNull(i8)) {
                    columnIndexOrThrow17 = i8;
                    i9 = columnIndexOrThrow18;
                    text4 = null;
                } else {
                    text4 = sQLiteStatementPrepare.getText(i8);
                    columnIndexOrThrow17 = i8;
                    i9 = columnIndexOrThrow18;
                }
                if (sQLiteStatementPrepare.isNull(i9)) {
                    columnIndexOrThrow18 = i9;
                    i10 = columnIndexOrThrow19;
                    text5 = null;
                } else {
                    text5 = sQLiteStatementPrepare.getText(i9);
                    columnIndexOrThrow18 = i9;
                    i10 = columnIndexOrThrow19;
                }
                if (sQLiteStatementPrepare.isNull(i10)) {
                    columnIndexOrThrow19 = i10;
                    i11 = columnIndexOrThrow20;
                    text6 = null;
                } else {
                    text6 = sQLiteStatementPrepare.getText(i10);
                    columnIndexOrThrow19 = i10;
                    i11 = columnIndexOrThrow20;
                }
                if (sQLiteStatementPrepare.isNull(i11)) {
                    columnIndexOrThrow20 = i11;
                    i12 = columnIndexOrThrow21;
                    text7 = null;
                } else {
                    text7 = sQLiteStatementPrepare.getText(i11);
                    columnIndexOrThrow20 = i11;
                    i12 = columnIndexOrThrow21;
                }
                String text9 = sQLiteStatementPrepare.isNull(i12) ? null : sQLiteStatementPrepare.getText(i12);
                columnIndexOrThrow21 = i12;
                int i33 = columnIndexOrThrow22;
                String str4 = text9;
                if (((int) sQLiteStatementPrepare.getLong(i33)) != 0) {
                    i13 = columnIndexOrThrow23;
                    z = true;
                } else {
                    i13 = columnIndexOrThrow23;
                    z = false;
                }
                int i34 = columnIndexOrThrow12;
                arrayList.add(new SportRecord(lValueOf, i14, i17, j2, text, i18, f2, f3, i22, i24, text2, i25, i29, f4, text3, str3, text4, text5, text6, text7, str4, z, ((int) sQLiteStatementPrepare.getLong(i13)) != 0));
                columnIndexOrThrow3 = i7;
                columnIndexOrThrow = i15;
                columnIndexOrThrow14 = i30;
                columnIndexOrThrow12 = i34;
                columnIndexOrThrow15 = i31;
                columnIndexOrThrow13 = i27;
                columnIndexOrThrow4 = i5;
                columnIndexOrThrow5 = i19;
                columnIndexOrThrow6 = i20;
                columnIndexOrThrow7 = i21;
                columnIndexOrThrow8 = i23;
                columnIndexOrThrow9 = i6;
                columnIndexOrThrow11 = i26;
                columnIndexOrThrow22 = i33;
                columnIndexOrThrow23 = i13;
                columnIndexOrThrow10 = i28;
                columnIndexOrThrow2 = i16;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.SportRecordDao
    public Object querySinceYearToDay(final String yearToDay, final String userId, final Continuation<? super List<SportRecord>> $completion) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.SportRecordDao_Impl$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SportRecordDao_Impl.lambda$querySinceYearToDay$10(yearToDay, userId, (SQLiteConnection) obj);
            }
        }, $completion);
    }

    static /* synthetic */ List lambda$querySinceYearToDay$10(String str, String str2, SQLiteConnection sQLiteConnection) {
        int i2;
        Long lValueOf;
        int i3;
        String text;
        int i4;
        int i5;
        String text2;
        int i6;
        int i7;
        String text3;
        int i8;
        String str3;
        String text4;
        int i9;
        String text5;
        int i10;
        String text6;
        int i11;
        String text7;
        int i12;
        int i13;
        boolean z;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("\n        SELECT * FROM sport_data \n        WHERE time_year_to_day >= ?\n        AND (user_id = ? OR user_id = \"\" OR user_id IS NULL)\n    ");
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
            int columnIndexOrThrow3 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "activity_type");
            int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "start_timestamp");
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "time_year_to_day");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "total_steps");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "total_distance");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "last_distance");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "total_calories");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "last_calories");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "pace_per_km");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "avg_heart_rate");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "duration_seconds");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "speed_kmh");
            int columnIndexOrThrow15 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "start_coordinates");
            int columnIndexOrThrow16 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "end_coordinates");
            int columnIndexOrThrow17 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "path_coordinates");
            int columnIndexOrThrow18 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow19 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow20 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow21 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow22 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            int columnIndexOrThrow23 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_uploaded");
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
                int i14 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
                int i15 = columnIndexOrThrow;
                int i16 = columnIndexOrThrow2;
                int i17 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow3);
                long j2 = sQLiteStatementPrepare.getLong(columnIndexOrThrow4);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow5)) {
                    i4 = columnIndexOrThrow3;
                    i5 = columnIndexOrThrow4;
                    text = null;
                } else {
                    text = sQLiteStatementPrepare.getText(columnIndexOrThrow5);
                    i4 = columnIndexOrThrow3;
                    i5 = columnIndexOrThrow4;
                }
                int i18 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                int i19 = columnIndexOrThrow5;
                float f2 = (float) sQLiteStatementPrepare.getDouble(columnIndexOrThrow7);
                int i20 = columnIndexOrThrow6;
                float f3 = (float) sQLiteStatementPrepare.getDouble(columnIndexOrThrow8);
                int i21 = columnIndexOrThrow7;
                int i22 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow9);
                int i23 = columnIndexOrThrow8;
                int i24 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow11)) {
                    i6 = columnIndexOrThrow9;
                    text2 = null;
                } else {
                    text2 = sQLiteStatementPrepare.getText(columnIndexOrThrow11);
                    i6 = columnIndexOrThrow9;
                }
                int i25 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12);
                int i26 = columnIndexOrThrow11;
                int i27 = i3;
                int i28 = columnIndexOrThrow10;
                int i29 = (int) sQLiteStatementPrepare.getLong(i27);
                int i30 = i2;
                float f4 = (float) sQLiteStatementPrepare.getDouble(i30);
                int i31 = columnIndexOrThrow15;
                if (sQLiteStatementPrepare.isNull(i31)) {
                    i7 = i4;
                    text3 = null;
                } else {
                    i7 = i4;
                    text3 = sQLiteStatementPrepare.getText(i31);
                }
                int i32 = columnIndexOrThrow16;
                if (sQLiteStatementPrepare.isNull(i32)) {
                    columnIndexOrThrow16 = i32;
                    i8 = columnIndexOrThrow17;
                    str3 = null;
                } else {
                    String text8 = sQLiteStatementPrepare.getText(i32);
                    columnIndexOrThrow16 = i32;
                    i8 = columnIndexOrThrow17;
                    str3 = text8;
                }
                if (sQLiteStatementPrepare.isNull(i8)) {
                    columnIndexOrThrow17 = i8;
                    i9 = columnIndexOrThrow18;
                    text4 = null;
                } else {
                    text4 = sQLiteStatementPrepare.getText(i8);
                    columnIndexOrThrow17 = i8;
                    i9 = columnIndexOrThrow18;
                }
                if (sQLiteStatementPrepare.isNull(i9)) {
                    columnIndexOrThrow18 = i9;
                    i10 = columnIndexOrThrow19;
                    text5 = null;
                } else {
                    text5 = sQLiteStatementPrepare.getText(i9);
                    columnIndexOrThrow18 = i9;
                    i10 = columnIndexOrThrow19;
                }
                if (sQLiteStatementPrepare.isNull(i10)) {
                    columnIndexOrThrow19 = i10;
                    i11 = columnIndexOrThrow20;
                    text6 = null;
                } else {
                    text6 = sQLiteStatementPrepare.getText(i10);
                    columnIndexOrThrow19 = i10;
                    i11 = columnIndexOrThrow20;
                }
                if (sQLiteStatementPrepare.isNull(i11)) {
                    columnIndexOrThrow20 = i11;
                    i12 = columnIndexOrThrow21;
                    text7 = null;
                } else {
                    text7 = sQLiteStatementPrepare.getText(i11);
                    columnIndexOrThrow20 = i11;
                    i12 = columnIndexOrThrow21;
                }
                String text9 = sQLiteStatementPrepare.isNull(i12) ? null : sQLiteStatementPrepare.getText(i12);
                columnIndexOrThrow21 = i12;
                int i33 = columnIndexOrThrow22;
                String str4 = text9;
                if (((int) sQLiteStatementPrepare.getLong(i33)) != 0) {
                    i13 = columnIndexOrThrow23;
                    z = true;
                } else {
                    i13 = columnIndexOrThrow23;
                    z = false;
                }
                int i34 = columnIndexOrThrow12;
                arrayList.add(new SportRecord(lValueOf, i14, i17, j2, text, i18, f2, f3, i22, i24, text2, i25, i29, f4, text3, str3, text4, text5, text6, text7, str4, z, ((int) sQLiteStatementPrepare.getLong(i13)) != 0));
                columnIndexOrThrow3 = i7;
                columnIndexOrThrow = i15;
                columnIndexOrThrow14 = i30;
                columnIndexOrThrow12 = i34;
                columnIndexOrThrow15 = i31;
                columnIndexOrThrow13 = i27;
                columnIndexOrThrow4 = i5;
                columnIndexOrThrow5 = i19;
                columnIndexOrThrow6 = i20;
                columnIndexOrThrow7 = i21;
                columnIndexOrThrow8 = i23;
                columnIndexOrThrow9 = i6;
                columnIndexOrThrow11 = i26;
                columnIndexOrThrow22 = i33;
                columnIndexOrThrow23 = i13;
                columnIndexOrThrow10 = i28;
                columnIndexOrThrow2 = i16;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.SportRecordDao
    public Object getDataInTimeRange(final long startTime, final long endTime, final String userName, final Continuation<? super List<SportRecord>> $completion) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.SportRecordDao_Impl$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SportRecordDao_Impl.lambda$getDataInTimeRange$11(startTime, endTime, userName, (SQLiteConnection) obj);
            }
        }, $completion);
    }

    static /* synthetic */ List lambda$getDataInTimeRange$11(long j2, long j3, String str, SQLiteConnection sQLiteConnection) {
        int i2;
        Long lValueOf;
        int i3;
        String text;
        int i4;
        String text2;
        int i5;
        int i6;
        String text3;
        String text4;
        String text5;
        String text6;
        int i7;
        String str2;
        String text7;
        int i8;
        int i9;
        boolean z;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("\n        SELECT * FROM sport_data \n        WHERE start_timestamp BETWEEN ? AND ?\n        AND (user_id = ? OR user_id IS NULL OR user_id = '')\n    ");
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
            int columnIndexOrThrow3 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "activity_type");
            int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "start_timestamp");
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "time_year_to_day");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "total_steps");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "total_distance");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "last_distance");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "total_calories");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "last_calories");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "pace_per_km");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "avg_heart_rate");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "duration_seconds");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "speed_kmh");
            int columnIndexOrThrow15 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "start_coordinates");
            int columnIndexOrThrow16 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "end_coordinates");
            int columnIndexOrThrow17 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "path_coordinates");
            int columnIndexOrThrow18 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow19 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow20 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow21 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow22 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            int columnIndexOrThrow23 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_uploaded");
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
                int i10 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
                ArrayList arrayList2 = arrayList;
                int i11 = columnIndexOrThrow2;
                int i12 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow3);
                long j4 = sQLiteStatementPrepare.getLong(columnIndexOrThrow4);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow5)) {
                    i4 = columnIndexOrThrow3;
                    text = null;
                } else {
                    text = sQLiteStatementPrepare.getText(columnIndexOrThrow5);
                    i4 = columnIndexOrThrow3;
                }
                int i13 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                int i14 = columnIndexOrThrow4;
                float f2 = (float) sQLiteStatementPrepare.getDouble(columnIndexOrThrow7);
                int i15 = columnIndexOrThrow5;
                float f3 = (float) sQLiteStatementPrepare.getDouble(columnIndexOrThrow8);
                int i16 = columnIndexOrThrow6;
                int i17 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow9);
                int i18 = columnIndexOrThrow7;
                int i19 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow11)) {
                    i5 = columnIndexOrThrow8;
                    text2 = null;
                } else {
                    text2 = sQLiteStatementPrepare.getText(columnIndexOrThrow11);
                    i5 = columnIndexOrThrow8;
                }
                int i20 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12);
                int i21 = columnIndexOrThrow10;
                int i22 = i3;
                int i23 = columnIndexOrThrow9;
                int i24 = (int) sQLiteStatementPrepare.getLong(i22);
                int i25 = columnIndexOrThrow12;
                int i26 = i2;
                int i27 = columnIndexOrThrow11;
                float f4 = (float) sQLiteStatementPrepare.getDouble(i26);
                int i28 = columnIndexOrThrow15;
                if (sQLiteStatementPrepare.isNull(i28)) {
                    i6 = columnIndexOrThrow;
                    text3 = null;
                } else {
                    i6 = columnIndexOrThrow;
                    text3 = sQLiteStatementPrepare.getText(i28);
                }
                int i29 = columnIndexOrThrow16;
                if (sQLiteStatementPrepare.isNull(i29)) {
                    columnIndexOrThrow16 = i29;
                    text4 = null;
                } else {
                    columnIndexOrThrow16 = i29;
                    text4 = sQLiteStatementPrepare.getText(i29);
                }
                int i30 = columnIndexOrThrow17;
                if (sQLiteStatementPrepare.isNull(i30)) {
                    columnIndexOrThrow17 = i30;
                    text5 = null;
                } else {
                    columnIndexOrThrow17 = i30;
                    text5 = sQLiteStatementPrepare.getText(i30);
                }
                int i31 = columnIndexOrThrow18;
                if (sQLiteStatementPrepare.isNull(i31)) {
                    columnIndexOrThrow18 = i31;
                    text6 = null;
                } else {
                    columnIndexOrThrow18 = i31;
                    text6 = sQLiteStatementPrepare.getText(i31);
                }
                int i32 = columnIndexOrThrow19;
                if (sQLiteStatementPrepare.isNull(i32)) {
                    columnIndexOrThrow19 = i32;
                    i7 = columnIndexOrThrow20;
                    str2 = null;
                } else {
                    String text8 = sQLiteStatementPrepare.getText(i32);
                    columnIndexOrThrow19 = i32;
                    i7 = columnIndexOrThrow20;
                    str2 = text8;
                }
                if (sQLiteStatementPrepare.isNull(i7)) {
                    columnIndexOrThrow20 = i7;
                    i8 = columnIndexOrThrow21;
                    text7 = null;
                } else {
                    text7 = sQLiteStatementPrepare.getText(i7);
                    columnIndexOrThrow20 = i7;
                    i8 = columnIndexOrThrow21;
                }
                String text9 = sQLiteStatementPrepare.isNull(i8) ? null : sQLiteStatementPrepare.getText(i8);
                columnIndexOrThrow21 = i8;
                int i33 = columnIndexOrThrow22;
                String str3 = text9;
                if (((int) sQLiteStatementPrepare.getLong(i33)) != 0) {
                    i9 = columnIndexOrThrow23;
                    z = true;
                } else {
                    i9 = columnIndexOrThrow23;
                    z = false;
                }
                SportRecord sportRecord = new SportRecord(lValueOf, i10, i12, j4, text, i13, f2, f3, i17, i19, text2, i20, i24, f4, text3, text4, text5, text6, str2, text7, str3, z, ((int) sQLiteStatementPrepare.getLong(i9)) != 0);
                arrayList = arrayList2;
                arrayList.add(sportRecord);
                columnIndexOrThrow13 = i22;
                columnIndexOrThrow2 = i11;
                columnIndexOrThrow14 = i26;
                columnIndexOrThrow9 = i23;
                columnIndexOrThrow11 = i27;
                columnIndexOrThrow3 = i4;
                columnIndexOrThrow4 = i14;
                columnIndexOrThrow5 = i15;
                columnIndexOrThrow6 = i16;
                columnIndexOrThrow7 = i18;
                columnIndexOrThrow8 = i5;
                columnIndexOrThrow12 = i25;
                columnIndexOrThrow22 = i33;
                columnIndexOrThrow = i6;
                columnIndexOrThrow15 = i28;
                columnIndexOrThrow23 = i9;
                columnIndexOrThrow10 = i21;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.SportRecordDao
    public Object deleteByStartTimestamp(final long startTimestamp, final String userId, final Continuation<? super Integer> $completion) {
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.SportRecordDao_Impl$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SportRecordDao_Impl.lambda$deleteByStartTimestamp$12(startTimestamp, userId, (SQLiteConnection) obj);
            }
        }, $completion);
    }

    static /* synthetic */ Integer lambda$deleteByStartTimestamp$12(long j2, String str, SQLiteConnection sQLiteConnection) {
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("DELETE FROM sport_data \n        WHERE start_timestamp = ?\n        AND (user_id = ?\n        OR user_id = \"\"\n        OR user_id IS NULL)\n    ");
        try {
            sQLiteStatementPrepare.mo181bindLong(1, j2);
            if (str == null) {
                sQLiteStatementPrepare.mo182bindNull(2);
            } else {
                sQLiteStatementPrepare.mo183bindText(2, str);
            }
            sQLiteStatementPrepare.step();
            return Integer.valueOf(SQLiteConnectionUtil.getTotalChangedRows(sQLiteConnection));
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.SportRecordDao
    public Object deleteById(final long id, final Continuation<? super Integer> $completion) {
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.SportRecordDao_Impl$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SportRecordDao_Impl.lambda$deleteById$13(id, (SQLiteConnection) obj);
            }
        }, $completion);
    }

    static /* synthetic */ Integer lambda$deleteById$13(long j2, SQLiteConnection sQLiteConnection) {
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("DELETE FROM sport_data WHERE id = ?");
        try {
            sQLiteStatementPrepare.mo181bindLong(1, j2);
            sQLiteStatementPrepare.step();
            return Integer.valueOf(SQLiteConnectionUtil.getTotalChangedRows(sQLiteConnection));
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.SportRecordDao
    public Object deleteAll(final Continuation<? super Integer> $completion) {
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.SportRecordDao_Impl$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SportRecordDao_Impl.lambda$deleteAll$14((SQLiteConnection) obj);
            }
        }, $completion);
    }

    static /* synthetic */ Integer lambda$deleteAll$14(SQLiteConnection sQLiteConnection) {
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("DELETE FROM sport_data");
        try {
            sQLiteStatementPrepare.step();
            return Integer.valueOf(SQLiteConnectionUtil.getTotalChangedRows(sQLiteConnection));
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.SportRecordDao
    public Object deleteAllByUser(final String userId, final Continuation<? super Integer> $completion) {
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.SportRecordDao_Impl$$ExternalSyntheticLambda14
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SportRecordDao_Impl.lambda$deleteAllByUser$15(userId, (SQLiteConnection) obj);
            }
        }, $completion);
    }

    static /* synthetic */ Integer lambda$deleteAllByUser$15(String str, SQLiteConnection sQLiteConnection) {
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("DELETE FROM sport_data WHERE user_id = ?");
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
