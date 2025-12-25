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
import com.yucheng.smarthealthpro.database.room.bean.MotionPattern;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;

/* loaded from: classes4.dex */
public final class MotionPatternDao_Impl implements MotionPatternDao {
    private final RoomDatabase __db;
    private final EntityInsertAdapter<MotionPattern> __insertAdapterOfMotionPattern = new EntityInsertAdapter<MotionPattern>() { // from class: com.yucheng.smarthealthpro.database.room.dao.MotionPatternDao_Impl.1
        @Override // androidx.room.EntityInsertAdapter
        protected String createQuery() {
            return "INSERT OR REPLACE INTO `motion_pattern_data` (`id`,`query_id`,`start_timestamp`,`end_timestamp`,`time_year_to_day`,`sport_steps`,`sport_distances`,`sport_calories`,`sport_mode`,`start_method`,`sport_heart_rate`,`sport_duration`,`min_heart_rate`,`max_heart_rate`,`user_id`,`device_type`,`device_mac_address`,`data_group_id`,`is_uploaded`,`is_other_uploaded`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // androidx.room.EntityInsertAdapter
        public void bind(SQLiteStatement sQLiteStatement, MotionPattern motionPattern) {
            if (motionPattern.getId() == null) {
                sQLiteStatement.mo182bindNull(1);
            } else {
                sQLiteStatement.mo181bindLong(1, motionPattern.getId().longValue());
            }
            sQLiteStatement.mo181bindLong(2, motionPattern.getQueryID());
            sQLiteStatement.mo181bindLong(3, motionPattern.getStartTimestamp());
            sQLiteStatement.mo181bindLong(4, motionPattern.getEndTimestamp());
            if (motionPattern.getTimeYearToDay() == null) {
                sQLiteStatement.mo182bindNull(5);
            } else {
                sQLiteStatement.mo183bindText(5, motionPattern.getTimeYearToDay());
            }
            sQLiteStatement.mo181bindLong(6, motionPattern.getSportSteps());
            sQLiteStatement.mo181bindLong(7, motionPattern.getSportDistances());
            sQLiteStatement.mo181bindLong(8, motionPattern.getSportCalories());
            sQLiteStatement.mo181bindLong(9, motionPattern.getSportMode());
            sQLiteStatement.mo181bindLong(10, motionPattern.getStartMethod());
            sQLiteStatement.mo181bindLong(11, motionPattern.getSportHeartRate());
            sQLiteStatement.mo181bindLong(12, motionPattern.getSportDuration());
            sQLiteStatement.mo181bindLong(13, motionPattern.getMinHeartRate());
            sQLiteStatement.mo181bindLong(14, motionPattern.getMaxHeartRate());
            if (motionPattern.getUserId() == null) {
                sQLiteStatement.mo182bindNull(15);
            } else {
                sQLiteStatement.mo183bindText(15, motionPattern.getUserId());
            }
            if (motionPattern.getDeviceType() == null) {
                sQLiteStatement.mo182bindNull(16);
            } else {
                sQLiteStatement.mo183bindText(16, motionPattern.getDeviceType());
            }
            if (motionPattern.getDeviceMacAddress() == null) {
                sQLiteStatement.mo182bindNull(17);
            } else {
                sQLiteStatement.mo183bindText(17, motionPattern.getDeviceMacAddress());
            }
            if (motionPattern.getDataGroupId() == null) {
                sQLiteStatement.mo182bindNull(18);
            } else {
                sQLiteStatement.mo183bindText(18, motionPattern.getDataGroupId());
            }
            sQLiteStatement.mo181bindLong(19, motionPattern.isUploaded() ? 1L : 0L);
            sQLiteStatement.mo181bindLong(20, motionPattern.isOtherUploaded() ? 1L : 0L);
        }
    };
    private final EntityDeleteOrUpdateAdapter<MotionPattern> __updateAdapterOfMotionPattern = new EntityDeleteOrUpdateAdapter<MotionPattern>() { // from class: com.yucheng.smarthealthpro.database.room.dao.MotionPatternDao_Impl.2
        @Override // androidx.room.EntityDeleteOrUpdateAdapter
        protected String createQuery() {
            return "UPDATE OR ABORT `motion_pattern_data` SET `id` = ?,`query_id` = ?,`start_timestamp` = ?,`end_timestamp` = ?,`time_year_to_day` = ?,`sport_steps` = ?,`sport_distances` = ?,`sport_calories` = ?,`sport_mode` = ?,`start_method` = ?,`sport_heart_rate` = ?,`sport_duration` = ?,`min_heart_rate` = ?,`max_heart_rate` = ?,`user_id` = ?,`device_type` = ?,`device_mac_address` = ?,`data_group_id` = ?,`is_uploaded` = ?,`is_other_uploaded` = ? WHERE `id` = ?";
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // androidx.room.EntityDeleteOrUpdateAdapter
        public void bind(SQLiteStatement sQLiteStatement, MotionPattern motionPattern) {
            if (motionPattern.getId() == null) {
                sQLiteStatement.mo182bindNull(1);
            } else {
                sQLiteStatement.mo181bindLong(1, motionPattern.getId().longValue());
            }
            sQLiteStatement.mo181bindLong(2, motionPattern.getQueryID());
            sQLiteStatement.mo181bindLong(3, motionPattern.getStartTimestamp());
            sQLiteStatement.mo181bindLong(4, motionPattern.getEndTimestamp());
            if (motionPattern.getTimeYearToDay() == null) {
                sQLiteStatement.mo182bindNull(5);
            } else {
                sQLiteStatement.mo183bindText(5, motionPattern.getTimeYearToDay());
            }
            sQLiteStatement.mo181bindLong(6, motionPattern.getSportSteps());
            sQLiteStatement.mo181bindLong(7, motionPattern.getSportDistances());
            sQLiteStatement.mo181bindLong(8, motionPattern.getSportCalories());
            sQLiteStatement.mo181bindLong(9, motionPattern.getSportMode());
            sQLiteStatement.mo181bindLong(10, motionPattern.getStartMethod());
            sQLiteStatement.mo181bindLong(11, motionPattern.getSportHeartRate());
            sQLiteStatement.mo181bindLong(12, motionPattern.getSportDuration());
            sQLiteStatement.mo181bindLong(13, motionPattern.getMinHeartRate());
            sQLiteStatement.mo181bindLong(14, motionPattern.getMaxHeartRate());
            if (motionPattern.getUserId() == null) {
                sQLiteStatement.mo182bindNull(15);
            } else {
                sQLiteStatement.mo183bindText(15, motionPattern.getUserId());
            }
            if (motionPattern.getDeviceType() == null) {
                sQLiteStatement.mo182bindNull(16);
            } else {
                sQLiteStatement.mo183bindText(16, motionPattern.getDeviceType());
            }
            if (motionPattern.getDeviceMacAddress() == null) {
                sQLiteStatement.mo182bindNull(17);
            } else {
                sQLiteStatement.mo183bindText(17, motionPattern.getDeviceMacAddress());
            }
            if (motionPattern.getDataGroupId() == null) {
                sQLiteStatement.mo182bindNull(18);
            } else {
                sQLiteStatement.mo183bindText(18, motionPattern.getDataGroupId());
            }
            sQLiteStatement.mo181bindLong(19, motionPattern.isUploaded() ? 1L : 0L);
            sQLiteStatement.mo181bindLong(20, motionPattern.isOtherUploaded() ? 1L : 0L);
            if (motionPattern.getId() == null) {
                sQLiteStatement.mo182bindNull(21);
            } else {
                sQLiteStatement.mo181bindLong(21, motionPattern.getId().longValue());
            }
        }
    };
    private final EntityDeleteOrUpdateAdapter<DataGroupIdUpdate> __updateAdapterOfDataGroupIdUpdateAsMotionPattern = new EntityDeleteOrUpdateAdapter<DataGroupIdUpdate>() { // from class: com.yucheng.smarthealthpro.database.room.dao.MotionPatternDao_Impl.3
        @Override // androidx.room.EntityDeleteOrUpdateAdapter
        protected String createQuery() {
            return "UPDATE OR ABORT `motion_pattern_data` SET `id` = ?,`data_group_id` = ? WHERE `id` = ?";
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

    public MotionPatternDao_Impl(final RoomDatabase __db) {
        this.__db = __db;
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.MotionPatternDao
    public Object insert(final MotionPattern metric, final Continuation<? super Long> $completion) {
        metric.getClass();
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.MotionPatternDao_Impl$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f$0.lambda$insert$0(metric, (SQLiteConnection) obj);
            }
        }, $completion);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Long lambda$insert$0(MotionPattern motionPattern, SQLiteConnection sQLiteConnection) {
        return Long.valueOf(this.__insertAdapterOfMotionPattern.insertAndReturnId(sQLiteConnection, motionPattern));
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.MotionPatternDao
    public Object insertAll(final List<MotionPattern> metrics, final Continuation<? super List<Long>> $completion) {
        metrics.getClass();
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.MotionPatternDao_Impl$$ExternalSyntheticLambda14
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f$0.lambda$insertAll$1(metrics, (SQLiteConnection) obj);
            }
        }, $completion);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ List lambda$insertAll$1(List list, SQLiteConnection sQLiteConnection) {
        return this.__insertAdapterOfMotionPattern.insertAndReturnIdsList(sQLiteConnection, list);
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.MotionPatternDao
    public Object update(final MotionPattern metric, final Continuation<? super Unit> $completion) {
        metric.getClass();
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.MotionPatternDao_Impl$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f$0.lambda$update$2(metric, (SQLiteConnection) obj);
            }
        }, $completion);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Unit lambda$update$2(MotionPattern motionPattern, SQLiteConnection sQLiteConnection) throws Exception {
        this.__updateAdapterOfMotionPattern.handle(sQLiteConnection, motionPattern);
        return Unit.INSTANCE;
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.MotionPatternDao
    public Object updateDataGroupIds(final List<DataGroupIdUpdate> updates, final Continuation<? super Unit> $completion) {
        updates.getClass();
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.MotionPatternDao_Impl$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f$0.lambda$updateDataGroupIds$3(updates, (SQLiteConnection) obj);
            }
        }, $completion);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Unit lambda$updateDataGroupIds$3(List list, SQLiteConnection sQLiteConnection) throws Exception {
        this.__updateAdapterOfDataGroupIdUpdateAsMotionPattern.handleMultiple(sQLiteConnection, list);
        return Unit.INSTANCE;
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.MotionPatternDao
    public Object getById(final long id, final Continuation<? super MotionPattern> $completion) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.MotionPatternDao_Impl$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MotionPatternDao_Impl.lambda$getById$4(id, (SQLiteConnection) obj);
            }
        }, $completion);
    }

    static /* synthetic */ MotionPattern lambda$getById$4(long j2, SQLiteConnection sQLiteConnection) {
        String text;
        int i2;
        String text2;
        int i3;
        String text3;
        int i4;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("SELECT * FROM motion_pattern_data WHERE id = ?");
        try {
            sQLiteStatementPrepare.mo181bindLong(1, j2);
            int columnIndexOrThrow = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "id");
            int columnIndexOrThrow2 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "query_id");
            int columnIndexOrThrow3 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "start_timestamp");
            int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "end_timestamp");
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "time_year_to_day");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sport_steps");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sport_distances");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sport_calories");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sport_mode");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "start_method");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sport_heart_rate");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sport_duration");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "min_heart_rate");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "max_heart_rate");
            int columnIndexOrThrow15 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow16 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow17 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow18 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow19 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            int columnIndexOrThrow20 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_uploaded");
            MotionPattern motionPattern = null;
            if (sQLiteStatementPrepare.step()) {
                Long lValueOf = sQLiteStatementPrepare.isNull(columnIndexOrThrow) ? null : Long.valueOf(sQLiteStatementPrepare.getLong(columnIndexOrThrow));
                int i5 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
                long j3 = sQLiteStatementPrepare.getLong(columnIndexOrThrow3);
                long j4 = sQLiteStatementPrepare.getLong(columnIndexOrThrow4);
                String text4 = sQLiteStatementPrepare.isNull(columnIndexOrThrow5) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow5);
                int i6 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                int i7 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7);
                int i8 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow8);
                int i9 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow9);
                int i10 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10);
                int i11 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11);
                long j5 = sQLiteStatementPrepare.getLong(columnIndexOrThrow12);
                int i12 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow13);
                int i13 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow14);
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
                motionPattern = new MotionPattern(lValueOf, i5, j3, j4, text4, i6, i7, i8, i9, i10, i11, j5, i12, i13, text, text2, text3, sQLiteStatementPrepare.isNull(i4) ? null : sQLiteStatementPrepare.getText(i4), ((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow19)) != 0, ((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow20)) != 0);
            }
            return motionPattern;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.MotionPatternDao
    public Object getByUser(final String userId, final Continuation<? super List<MotionPattern>> $completion) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.MotionPatternDao_Impl$$ExternalSyntheticLambda15
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MotionPatternDao_Impl.lambda$getByUser$5(userId, (SQLiteConnection) obj);
            }
        }, $completion);
    }

    static /* synthetic */ List lambda$getByUser$5(String str, SQLiteConnection sQLiteConnection) {
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
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("SELECT * FROM motion_pattern_data WHERE user_id = ? ORDER BY start_timestamp DESC");
        try {
            if (str == null) {
                sQLiteStatementPrepare.mo182bindNull(1);
            } else {
                sQLiteStatementPrepare.mo183bindText(1, str);
            }
            int columnIndexOrThrow = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "id");
            int columnIndexOrThrow2 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "query_id");
            int columnIndexOrThrow3 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "start_timestamp");
            int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "end_timestamp");
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "time_year_to_day");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sport_steps");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sport_distances");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sport_calories");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sport_mode");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "start_method");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sport_heart_rate");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sport_duration");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "min_heart_rate");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "max_heart_rate");
            int columnIndexOrThrow15 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow16 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow17 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow18 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow19 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            int columnIndexOrThrow20 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_uploaded");
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
                int i11 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
                long j2 = sQLiteStatementPrepare.getLong(columnIndexOrThrow3);
                long j3 = sQLiteStatementPrepare.getLong(columnIndexOrThrow4);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow5)) {
                    i5 = columnIndexOrThrow2;
                    i4 = columnIndexOrThrow3;
                    text = null;
                } else {
                    i4 = columnIndexOrThrow3;
                    text = sQLiteStatementPrepare.getText(columnIndexOrThrow5);
                    i5 = columnIndexOrThrow2;
                }
                int i12 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                int i13 = columnIndexOrThrow4;
                int i14 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7);
                int i15 = columnIndexOrThrow5;
                int i16 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow8);
                int i17 = columnIndexOrThrow6;
                int i18 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow9);
                int i19 = columnIndexOrThrow7;
                int i20 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10);
                int i21 = columnIndexOrThrow8;
                int i22 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11);
                long j4 = sQLiteStatementPrepare.getLong(columnIndexOrThrow12);
                int i23 = columnIndexOrThrow10;
                int i24 = i3;
                int i25 = columnIndexOrThrow9;
                int i26 = (int) sQLiteStatementPrepare.getLong(i24);
                int i27 = columnIndexOrThrow12;
                int i28 = i2;
                int i29 = columnIndexOrThrow11;
                int i30 = (int) sQLiteStatementPrepare.getLong(i28);
                int i31 = columnIndexOrThrow15;
                if (sQLiteStatementPrepare.isNull(i31)) {
                    i6 = columnIndexOrThrow;
                    i7 = columnIndexOrThrow16;
                    text2 = null;
                } else {
                    i6 = columnIndexOrThrow;
                    i7 = columnIndexOrThrow16;
                    text2 = sQLiteStatementPrepare.getText(i31);
                }
                if (sQLiteStatementPrepare.isNull(i7)) {
                    columnIndexOrThrow16 = i7;
                    i8 = columnIndexOrThrow17;
                    text3 = null;
                } else {
                    text3 = sQLiteStatementPrepare.getText(i7);
                    columnIndexOrThrow16 = i7;
                    i8 = columnIndexOrThrow17;
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
                String text5 = sQLiteStatementPrepare.isNull(i9) ? null : sQLiteStatementPrepare.getText(i9);
                columnIndexOrThrow18 = i9;
                int i32 = columnIndexOrThrow19;
                String str2 = text5;
                if (((int) sQLiteStatementPrepare.getLong(i32)) != 0) {
                    i10 = columnIndexOrThrow20;
                    z = true;
                } else {
                    i10 = columnIndexOrThrow20;
                    z = false;
                }
                arrayList.add(new MotionPattern(lValueOf, i11, j2, j3, text, i12, i14, i16, i18, i20, i22, j4, i26, i30, text2, text3, text4, str2, z, ((int) sQLiteStatementPrepare.getLong(i10)) != 0));
                columnIndexOrThrow13 = i24;
                columnIndexOrThrow2 = i5;
                columnIndexOrThrow14 = i28;
                columnIndexOrThrow9 = i25;
                columnIndexOrThrow11 = i29;
                columnIndexOrThrow3 = i4;
                columnIndexOrThrow4 = i13;
                columnIndexOrThrow5 = i15;
                columnIndexOrThrow6 = i17;
                columnIndexOrThrow7 = i19;
                columnIndexOrThrow8 = i21;
                columnIndexOrThrow12 = i27;
                columnIndexOrThrow19 = i32;
                columnIndexOrThrow = i6;
                columnIndexOrThrow15 = i31;
                columnIndexOrThrow20 = i10;
                columnIndexOrThrow10 = i23;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.MotionPatternDao
    public Object getByStartTimestamp(final long startTimestamp, final Continuation<? super List<MotionPattern>> $completion) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.MotionPatternDao_Impl$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MotionPatternDao_Impl.lambda$getByStartTimestamp$6(startTimestamp, (SQLiteConnection) obj);
            }
        }, $completion);
    }

    static /* synthetic */ List lambda$getByStartTimestamp$6(long j2, SQLiteConnection sQLiteConnection) {
        int i2;
        Long lValueOf;
        int i3;
        int i4;
        String text;
        int i5;
        int i6;
        String text2;
        int i7;
        String str;
        String text3;
        int i8;
        int i9;
        boolean z;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("SELECT * FROM motion_pattern_data WHERE start_timestamp = ?");
        try {
            sQLiteStatementPrepare.mo181bindLong(1, j2);
            int columnIndexOrThrow = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "id");
            int columnIndexOrThrow2 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "query_id");
            int columnIndexOrThrow3 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "start_timestamp");
            int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "end_timestamp");
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "time_year_to_day");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sport_steps");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sport_distances");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sport_calories");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sport_mode");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "start_method");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sport_heart_rate");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sport_duration");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "min_heart_rate");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "max_heart_rate");
            int columnIndexOrThrow15 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow16 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow17 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow18 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow19 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            int columnIndexOrThrow20 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_uploaded");
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
                long j3 = sQLiteStatementPrepare.getLong(columnIndexOrThrow3);
                long j4 = sQLiteStatementPrepare.getLong(columnIndexOrThrow4);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow5)) {
                    i5 = columnIndexOrThrow;
                    i4 = columnIndexOrThrow2;
                    text = null;
                } else {
                    i4 = columnIndexOrThrow2;
                    text = sQLiteStatementPrepare.getText(columnIndexOrThrow5);
                    i5 = columnIndexOrThrow;
                }
                int i11 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                int i12 = columnIndexOrThrow3;
                int i13 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7);
                int i14 = columnIndexOrThrow4;
                int i15 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow8);
                int i16 = columnIndexOrThrow5;
                int i17 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow9);
                int i18 = columnIndexOrThrow6;
                int i19 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10);
                int i20 = columnIndexOrThrow7;
                int i21 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11);
                long j5 = sQLiteStatementPrepare.getLong(columnIndexOrThrow12);
                int i22 = columnIndexOrThrow9;
                int i23 = i3;
                int i24 = columnIndexOrThrow8;
                int i25 = (int) sQLiteStatementPrepare.getLong(i23);
                int i26 = columnIndexOrThrow11;
                int i27 = i2;
                int i28 = columnIndexOrThrow10;
                int i29 = (int) sQLiteStatementPrepare.getLong(i27);
                int i30 = columnIndexOrThrow15;
                if (sQLiteStatementPrepare.isNull(i30)) {
                    i6 = i23;
                    text2 = null;
                } else {
                    i6 = i23;
                    text2 = sQLiteStatementPrepare.getText(i30);
                }
                int i31 = columnIndexOrThrow16;
                if (sQLiteStatementPrepare.isNull(i31)) {
                    columnIndexOrThrow16 = i31;
                    i7 = columnIndexOrThrow17;
                    str = null;
                } else {
                    String text4 = sQLiteStatementPrepare.getText(i31);
                    columnIndexOrThrow16 = i31;
                    i7 = columnIndexOrThrow17;
                    str = text4;
                }
                if (sQLiteStatementPrepare.isNull(i7)) {
                    columnIndexOrThrow17 = i7;
                    i8 = columnIndexOrThrow18;
                    text3 = null;
                } else {
                    text3 = sQLiteStatementPrepare.getText(i7);
                    columnIndexOrThrow17 = i7;
                    i8 = columnIndexOrThrow18;
                }
                String text5 = sQLiteStatementPrepare.isNull(i8) ? null : sQLiteStatementPrepare.getText(i8);
                columnIndexOrThrow18 = i8;
                int i32 = columnIndexOrThrow19;
                String str2 = text5;
                int i33 = columnIndexOrThrow12;
                if (((int) sQLiteStatementPrepare.getLong(i32)) != 0) {
                    i9 = columnIndexOrThrow20;
                    z = true;
                } else {
                    i9 = columnIndexOrThrow20;
                    z = false;
                }
                arrayList.add(new MotionPattern(lValueOf, i10, j3, j4, text, i11, i13, i15, i17, i19, i21, j5, i25, i29, text2, str, text3, str2, z, ((int) sQLiteStatementPrepare.getLong(i9)) != 0));
                columnIndexOrThrow13 = i6;
                columnIndexOrThrow20 = i9;
                columnIndexOrThrow = i5;
                columnIndexOrThrow12 = i33;
                columnIndexOrThrow8 = i24;
                columnIndexOrThrow15 = i30;
                columnIndexOrThrow10 = i28;
                columnIndexOrThrow2 = i4;
                columnIndexOrThrow3 = i12;
                columnIndexOrThrow4 = i14;
                columnIndexOrThrow5 = i16;
                columnIndexOrThrow6 = i18;
                columnIndexOrThrow11 = i26;
                columnIndexOrThrow19 = i32;
                columnIndexOrThrow14 = i27;
                columnIndexOrThrow7 = i20;
                columnIndexOrThrow9 = i22;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.MotionPatternDao
    public Object getByUserId(final String userId, final Continuation<? super List<MotionPattern>> $completion) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.MotionPatternDao_Impl$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MotionPatternDao_Impl.lambda$getByUserId$7(userId, (SQLiteConnection) obj);
            }
        }, $completion);
    }

    static /* synthetic */ List lambda$getByUserId$7(String str, SQLiteConnection sQLiteConnection) {
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
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("\n        SELECT * FROM motion_pattern_data \n        WHERE user_id = ? \n        OR user_id = \"\"\n        OR user_id IS NULL\n        ORDER BY start_timestamp DESC\n    ");
        try {
            if (str == null) {
                sQLiteStatementPrepare.mo182bindNull(1);
            } else {
                sQLiteStatementPrepare.mo183bindText(1, str);
            }
            int columnIndexOrThrow = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "id");
            int columnIndexOrThrow2 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "query_id");
            int columnIndexOrThrow3 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "start_timestamp");
            int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "end_timestamp");
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "time_year_to_day");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sport_steps");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sport_distances");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sport_calories");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sport_mode");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "start_method");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sport_heart_rate");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sport_duration");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "min_heart_rate");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "max_heart_rate");
            int columnIndexOrThrow15 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow16 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow17 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow18 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow19 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            int columnIndexOrThrow20 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_uploaded");
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
                int i11 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
                long j2 = sQLiteStatementPrepare.getLong(columnIndexOrThrow3);
                long j3 = sQLiteStatementPrepare.getLong(columnIndexOrThrow4);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow5)) {
                    i5 = columnIndexOrThrow2;
                    i4 = columnIndexOrThrow3;
                    text = null;
                } else {
                    i4 = columnIndexOrThrow3;
                    text = sQLiteStatementPrepare.getText(columnIndexOrThrow5);
                    i5 = columnIndexOrThrow2;
                }
                int i12 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                int i13 = columnIndexOrThrow4;
                int i14 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7);
                int i15 = columnIndexOrThrow5;
                int i16 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow8);
                int i17 = columnIndexOrThrow6;
                int i18 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow9);
                int i19 = columnIndexOrThrow7;
                int i20 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10);
                int i21 = columnIndexOrThrow8;
                int i22 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11);
                long j4 = sQLiteStatementPrepare.getLong(columnIndexOrThrow12);
                int i23 = columnIndexOrThrow10;
                int i24 = i3;
                int i25 = columnIndexOrThrow9;
                int i26 = (int) sQLiteStatementPrepare.getLong(i24);
                int i27 = columnIndexOrThrow12;
                int i28 = i2;
                int i29 = columnIndexOrThrow11;
                int i30 = (int) sQLiteStatementPrepare.getLong(i28);
                int i31 = columnIndexOrThrow15;
                if (sQLiteStatementPrepare.isNull(i31)) {
                    i6 = columnIndexOrThrow;
                    i7 = columnIndexOrThrow16;
                    text2 = null;
                } else {
                    i6 = columnIndexOrThrow;
                    i7 = columnIndexOrThrow16;
                    text2 = sQLiteStatementPrepare.getText(i31);
                }
                if (sQLiteStatementPrepare.isNull(i7)) {
                    columnIndexOrThrow16 = i7;
                    i8 = columnIndexOrThrow17;
                    text3 = null;
                } else {
                    text3 = sQLiteStatementPrepare.getText(i7);
                    columnIndexOrThrow16 = i7;
                    i8 = columnIndexOrThrow17;
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
                String text5 = sQLiteStatementPrepare.isNull(i9) ? null : sQLiteStatementPrepare.getText(i9);
                columnIndexOrThrow18 = i9;
                int i32 = columnIndexOrThrow19;
                String str2 = text5;
                if (((int) sQLiteStatementPrepare.getLong(i32)) != 0) {
                    i10 = columnIndexOrThrow20;
                    z = true;
                } else {
                    i10 = columnIndexOrThrow20;
                    z = false;
                }
                arrayList.add(new MotionPattern(lValueOf, i11, j2, j3, text, i12, i14, i16, i18, i20, i22, j4, i26, i30, text2, text3, text4, str2, z, ((int) sQLiteStatementPrepare.getLong(i10)) != 0));
                columnIndexOrThrow13 = i24;
                columnIndexOrThrow2 = i5;
                columnIndexOrThrow14 = i28;
                columnIndexOrThrow9 = i25;
                columnIndexOrThrow11 = i29;
                columnIndexOrThrow3 = i4;
                columnIndexOrThrow4 = i13;
                columnIndexOrThrow5 = i15;
                columnIndexOrThrow6 = i17;
                columnIndexOrThrow7 = i19;
                columnIndexOrThrow8 = i21;
                columnIndexOrThrow12 = i27;
                columnIndexOrThrow19 = i32;
                columnIndexOrThrow = i6;
                columnIndexOrThrow15 = i31;
                columnIndexOrThrow20 = i10;
                columnIndexOrThrow10 = i23;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.MotionPatternDao
    public Object queryAll(final String userId, final Continuation<? super List<MotionPattern>> $completion) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.MotionPatternDao_Impl$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MotionPatternDao_Impl.lambda$queryAll$8(userId, (SQLiteConnection) obj);
            }
        }, $completion);
    }

    static /* synthetic */ List lambda$queryAll$8(String str, SQLiteConnection sQLiteConnection) {
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
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("\n        SELECT * FROM motion_pattern_data \n        WHERE (user_id = ? \n        OR user_id = \"\"\n        OR user_id IS NULL)\n        ORDER BY start_timestamp DESC\n    ");
        try {
            if (str == null) {
                sQLiteStatementPrepare.mo182bindNull(1);
            } else {
                sQLiteStatementPrepare.mo183bindText(1, str);
            }
            int columnIndexOrThrow = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "id");
            int columnIndexOrThrow2 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "query_id");
            int columnIndexOrThrow3 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "start_timestamp");
            int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "end_timestamp");
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "time_year_to_day");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sport_steps");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sport_distances");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sport_calories");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sport_mode");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "start_method");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sport_heart_rate");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sport_duration");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "min_heart_rate");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "max_heart_rate");
            int columnIndexOrThrow15 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow16 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow17 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow18 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow19 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            int columnIndexOrThrow20 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_uploaded");
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
                int i11 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
                long j2 = sQLiteStatementPrepare.getLong(columnIndexOrThrow3);
                long j3 = sQLiteStatementPrepare.getLong(columnIndexOrThrow4);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow5)) {
                    i5 = columnIndexOrThrow2;
                    i4 = columnIndexOrThrow3;
                    text = null;
                } else {
                    i4 = columnIndexOrThrow3;
                    text = sQLiteStatementPrepare.getText(columnIndexOrThrow5);
                    i5 = columnIndexOrThrow2;
                }
                int i12 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                int i13 = columnIndexOrThrow4;
                int i14 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7);
                int i15 = columnIndexOrThrow5;
                int i16 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow8);
                int i17 = columnIndexOrThrow6;
                int i18 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow9);
                int i19 = columnIndexOrThrow7;
                int i20 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10);
                int i21 = columnIndexOrThrow8;
                int i22 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11);
                long j4 = sQLiteStatementPrepare.getLong(columnIndexOrThrow12);
                int i23 = columnIndexOrThrow10;
                int i24 = i3;
                int i25 = columnIndexOrThrow9;
                int i26 = (int) sQLiteStatementPrepare.getLong(i24);
                int i27 = columnIndexOrThrow12;
                int i28 = i2;
                int i29 = columnIndexOrThrow11;
                int i30 = (int) sQLiteStatementPrepare.getLong(i28);
                int i31 = columnIndexOrThrow15;
                if (sQLiteStatementPrepare.isNull(i31)) {
                    i6 = columnIndexOrThrow;
                    i7 = columnIndexOrThrow16;
                    text2 = null;
                } else {
                    i6 = columnIndexOrThrow;
                    i7 = columnIndexOrThrow16;
                    text2 = sQLiteStatementPrepare.getText(i31);
                }
                if (sQLiteStatementPrepare.isNull(i7)) {
                    columnIndexOrThrow16 = i7;
                    i8 = columnIndexOrThrow17;
                    text3 = null;
                } else {
                    text3 = sQLiteStatementPrepare.getText(i7);
                    columnIndexOrThrow16 = i7;
                    i8 = columnIndexOrThrow17;
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
                String text5 = sQLiteStatementPrepare.isNull(i9) ? null : sQLiteStatementPrepare.getText(i9);
                columnIndexOrThrow18 = i9;
                int i32 = columnIndexOrThrow19;
                String str2 = text5;
                if (((int) sQLiteStatementPrepare.getLong(i32)) != 0) {
                    i10 = columnIndexOrThrow20;
                    z = true;
                } else {
                    i10 = columnIndexOrThrow20;
                    z = false;
                }
                arrayList.add(new MotionPattern(lValueOf, i11, j2, j3, text, i12, i14, i16, i18, i20, i22, j4, i26, i30, text2, text3, text4, str2, z, ((int) sQLiteStatementPrepare.getLong(i10)) != 0));
                columnIndexOrThrow13 = i24;
                columnIndexOrThrow2 = i5;
                columnIndexOrThrow14 = i28;
                columnIndexOrThrow9 = i25;
                columnIndexOrThrow11 = i29;
                columnIndexOrThrow3 = i4;
                columnIndexOrThrow4 = i13;
                columnIndexOrThrow5 = i15;
                columnIndexOrThrow6 = i17;
                columnIndexOrThrow7 = i19;
                columnIndexOrThrow8 = i21;
                columnIndexOrThrow12 = i27;
                columnIndexOrThrow19 = i32;
                columnIndexOrThrow = i6;
                columnIndexOrThrow15 = i31;
                columnIndexOrThrow20 = i10;
                columnIndexOrThrow10 = i23;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.MotionPatternDao
    public Object queryByYearToDay(final String yearToDay, final String userId, final Continuation<? super List<MotionPattern>> $completion) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.MotionPatternDao_Impl$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MotionPatternDao_Impl.lambda$queryByYearToDay$9(yearToDay, userId, (SQLiteConnection) obj);
            }
        }, $completion);
    }

    static /* synthetic */ List lambda$queryByYearToDay$9(String str, String str2, SQLiteConnection sQLiteConnection) {
        int i2;
        Long lValueOf;
        int i3;
        int i4;
        String text;
        int i5;
        int i6;
        String text2;
        int i7;
        String str3;
        String text3;
        int i8;
        int i9;
        boolean z;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("\n        SELECT * FROM motion_pattern_data \n        WHERE time_year_to_day = ?\n        AND (user_id = ? \n        OR user_id = \"\"\n        OR user_id IS NULL)\n        ORDER BY start_timestamp DESC\n    ");
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
            int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "end_timestamp");
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "time_year_to_day");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sport_steps");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sport_distances");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sport_calories");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sport_mode");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "start_method");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sport_heart_rate");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sport_duration");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "min_heart_rate");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "max_heart_rate");
            int columnIndexOrThrow15 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow16 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow17 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow18 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow19 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            int columnIndexOrThrow20 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_uploaded");
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
                long j2 = sQLiteStatementPrepare.getLong(columnIndexOrThrow3);
                long j3 = sQLiteStatementPrepare.getLong(columnIndexOrThrow4);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow5)) {
                    i5 = columnIndexOrThrow;
                    i4 = columnIndexOrThrow2;
                    text = null;
                } else {
                    i4 = columnIndexOrThrow2;
                    text = sQLiteStatementPrepare.getText(columnIndexOrThrow5);
                    i5 = columnIndexOrThrow;
                }
                int i11 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                int i12 = columnIndexOrThrow3;
                int i13 = columnIndexOrThrow4;
                int i14 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7);
                int i15 = columnIndexOrThrow5;
                int i16 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow8);
                int i17 = columnIndexOrThrow6;
                int i18 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow9);
                int i19 = columnIndexOrThrow7;
                int i20 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10);
                int i21 = columnIndexOrThrow8;
                int i22 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11);
                long j4 = sQLiteStatementPrepare.getLong(columnIndexOrThrow12);
                int i23 = columnIndexOrThrow10;
                int i24 = i3;
                int i25 = columnIndexOrThrow9;
                int i26 = (int) sQLiteStatementPrepare.getLong(i24);
                int i27 = columnIndexOrThrow12;
                int i28 = i2;
                int i29 = columnIndexOrThrow11;
                int i30 = (int) sQLiteStatementPrepare.getLong(i28);
                int i31 = columnIndexOrThrow15;
                if (sQLiteStatementPrepare.isNull(i31)) {
                    i6 = i12;
                    text2 = null;
                } else {
                    i6 = i12;
                    text2 = sQLiteStatementPrepare.getText(i31);
                }
                int i32 = columnIndexOrThrow16;
                if (sQLiteStatementPrepare.isNull(i32)) {
                    columnIndexOrThrow16 = i32;
                    i7 = columnIndexOrThrow17;
                    str3 = null;
                } else {
                    String text4 = sQLiteStatementPrepare.getText(i32);
                    columnIndexOrThrow16 = i32;
                    i7 = columnIndexOrThrow17;
                    str3 = text4;
                }
                if (sQLiteStatementPrepare.isNull(i7)) {
                    columnIndexOrThrow17 = i7;
                    i8 = columnIndexOrThrow18;
                    text3 = null;
                } else {
                    text3 = sQLiteStatementPrepare.getText(i7);
                    columnIndexOrThrow17 = i7;
                    i8 = columnIndexOrThrow18;
                }
                String text5 = sQLiteStatementPrepare.isNull(i8) ? null : sQLiteStatementPrepare.getText(i8);
                columnIndexOrThrow18 = i8;
                int i33 = columnIndexOrThrow19;
                String str4 = text5;
                if (((int) sQLiteStatementPrepare.getLong(i33)) != 0) {
                    i9 = columnIndexOrThrow20;
                    z = true;
                } else {
                    i9 = columnIndexOrThrow20;
                    z = false;
                }
                arrayList.add(new MotionPattern(lValueOf, i10, j2, j3, text, i11, i14, i16, i18, i20, i22, j4, i26, i30, text2, str3, text3, str4, z, ((int) sQLiteStatementPrepare.getLong(i9)) != 0));
                columnIndexOrThrow3 = i6;
                columnIndexOrThrow13 = i24;
                columnIndexOrThrow = i5;
                columnIndexOrThrow14 = i28;
                columnIndexOrThrow15 = i31;
                columnIndexOrThrow9 = i25;
                columnIndexOrThrow11 = i29;
                columnIndexOrThrow4 = i13;
                columnIndexOrThrow5 = i15;
                columnIndexOrThrow6 = i17;
                columnIndexOrThrow7 = i19;
                columnIndexOrThrow8 = i21;
                columnIndexOrThrow12 = i27;
                columnIndexOrThrow19 = i33;
                columnIndexOrThrow20 = i9;
                columnIndexOrThrow2 = i4;
                columnIndexOrThrow10 = i23;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.MotionPatternDao
    public Object querySinceYearToDay(final String yearToDay, final String userId, final Continuation<? super List<MotionPattern>> $completion) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.MotionPatternDao_Impl$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MotionPatternDao_Impl.lambda$querySinceYearToDay$10(yearToDay, userId, (SQLiteConnection) obj);
            }
        }, $completion);
    }

    static /* synthetic */ List lambda$querySinceYearToDay$10(String str, String str2, SQLiteConnection sQLiteConnection) {
        int i2;
        Long lValueOf;
        int i3;
        int i4;
        String text;
        int i5;
        int i6;
        String text2;
        int i7;
        String str3;
        String text3;
        int i8;
        int i9;
        boolean z;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("\n        SELECT * FROM motion_pattern_data \n        WHERE time_year_to_day >= ?\n        AND (user_id = ? OR user_id = \"\" OR user_id IS NULL)\n    ");
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
            int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "end_timestamp");
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "time_year_to_day");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sport_steps");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sport_distances");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sport_calories");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sport_mode");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "start_method");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sport_heart_rate");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sport_duration");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "min_heart_rate");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "max_heart_rate");
            int columnIndexOrThrow15 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow16 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow17 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow18 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow19 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            int columnIndexOrThrow20 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_uploaded");
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
                long j2 = sQLiteStatementPrepare.getLong(columnIndexOrThrow3);
                long j3 = sQLiteStatementPrepare.getLong(columnIndexOrThrow4);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow5)) {
                    i5 = columnIndexOrThrow;
                    i4 = columnIndexOrThrow2;
                    text = null;
                } else {
                    i4 = columnIndexOrThrow2;
                    text = sQLiteStatementPrepare.getText(columnIndexOrThrow5);
                    i5 = columnIndexOrThrow;
                }
                int i11 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                int i12 = columnIndexOrThrow3;
                int i13 = columnIndexOrThrow4;
                int i14 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7);
                int i15 = columnIndexOrThrow5;
                int i16 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow8);
                int i17 = columnIndexOrThrow6;
                int i18 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow9);
                int i19 = columnIndexOrThrow7;
                int i20 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10);
                int i21 = columnIndexOrThrow8;
                int i22 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11);
                long j4 = sQLiteStatementPrepare.getLong(columnIndexOrThrow12);
                int i23 = columnIndexOrThrow10;
                int i24 = i3;
                int i25 = columnIndexOrThrow9;
                int i26 = (int) sQLiteStatementPrepare.getLong(i24);
                int i27 = columnIndexOrThrow12;
                int i28 = i2;
                int i29 = columnIndexOrThrow11;
                int i30 = (int) sQLiteStatementPrepare.getLong(i28);
                int i31 = columnIndexOrThrow15;
                if (sQLiteStatementPrepare.isNull(i31)) {
                    i6 = i12;
                    text2 = null;
                } else {
                    i6 = i12;
                    text2 = sQLiteStatementPrepare.getText(i31);
                }
                int i32 = columnIndexOrThrow16;
                if (sQLiteStatementPrepare.isNull(i32)) {
                    columnIndexOrThrow16 = i32;
                    i7 = columnIndexOrThrow17;
                    str3 = null;
                } else {
                    String text4 = sQLiteStatementPrepare.getText(i32);
                    columnIndexOrThrow16 = i32;
                    i7 = columnIndexOrThrow17;
                    str3 = text4;
                }
                if (sQLiteStatementPrepare.isNull(i7)) {
                    columnIndexOrThrow17 = i7;
                    i8 = columnIndexOrThrow18;
                    text3 = null;
                } else {
                    text3 = sQLiteStatementPrepare.getText(i7);
                    columnIndexOrThrow17 = i7;
                    i8 = columnIndexOrThrow18;
                }
                String text5 = sQLiteStatementPrepare.isNull(i8) ? null : sQLiteStatementPrepare.getText(i8);
                columnIndexOrThrow18 = i8;
                int i33 = columnIndexOrThrow19;
                String str4 = text5;
                if (((int) sQLiteStatementPrepare.getLong(i33)) != 0) {
                    i9 = columnIndexOrThrow20;
                    z = true;
                } else {
                    i9 = columnIndexOrThrow20;
                    z = false;
                }
                arrayList.add(new MotionPattern(lValueOf, i10, j2, j3, text, i11, i14, i16, i18, i20, i22, j4, i26, i30, text2, str3, text3, str4, z, ((int) sQLiteStatementPrepare.getLong(i9)) != 0));
                columnIndexOrThrow3 = i6;
                columnIndexOrThrow13 = i24;
                columnIndexOrThrow = i5;
                columnIndexOrThrow14 = i28;
                columnIndexOrThrow15 = i31;
                columnIndexOrThrow9 = i25;
                columnIndexOrThrow11 = i29;
                columnIndexOrThrow4 = i13;
                columnIndexOrThrow5 = i15;
                columnIndexOrThrow6 = i17;
                columnIndexOrThrow7 = i19;
                columnIndexOrThrow8 = i21;
                columnIndexOrThrow12 = i27;
                columnIndexOrThrow19 = i33;
                columnIndexOrThrow20 = i9;
                columnIndexOrThrow2 = i4;
                columnIndexOrThrow10 = i23;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.MotionPatternDao
    public Object queryByTimeRange(final long startTime, final long endTime, final String userId, final Continuation<? super List<MotionPattern>> $completion) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.MotionPatternDao_Impl$$ExternalSyntheticLambda13
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MotionPatternDao_Impl.lambda$queryByTimeRange$11(startTime, endTime, userId, (SQLiteConnection) obj);
            }
        }, $completion);
    }

    static /* synthetic */ List lambda$queryByTimeRange$11(long j2, long j3, String str, SQLiteConnection sQLiteConnection) {
        int i2;
        Long lValueOf;
        int i3;
        int i4;
        String text;
        int i5;
        int i6;
        String text2;
        String text3;
        String text4;
        int i7;
        boolean z;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("\n        SELECT * FROM motion_pattern_data \n        WHERE start_timestamp BETWEEN ? AND ?\n        AND (user_id = ? OR user_id = \"\" OR user_id IS NULL)\n    ");
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
            int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "end_timestamp");
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "time_year_to_day");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sport_steps");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sport_distances");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sport_calories");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sport_mode");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "start_method");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sport_heart_rate");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sport_duration");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "min_heart_rate");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "max_heart_rate");
            int columnIndexOrThrow15 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow16 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow17 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow18 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow19 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            int columnIndexOrThrow20 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_uploaded");
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
                int i8 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
                long j4 = sQLiteStatementPrepare.getLong(columnIndexOrThrow3);
                long j5 = sQLiteStatementPrepare.getLong(columnIndexOrThrow4);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow5)) {
                    i5 = columnIndexOrThrow2;
                    i4 = columnIndexOrThrow3;
                    text = null;
                } else {
                    i4 = columnIndexOrThrow3;
                    text = sQLiteStatementPrepare.getText(columnIndexOrThrow5);
                    i5 = columnIndexOrThrow2;
                }
                int i9 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                int i10 = columnIndexOrThrow4;
                int i11 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7);
                int i12 = columnIndexOrThrow5;
                int i13 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow8);
                int i14 = columnIndexOrThrow6;
                int i15 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow9);
                int i16 = columnIndexOrThrow7;
                int i17 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10);
                int i18 = columnIndexOrThrow8;
                int i19 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11);
                long j6 = sQLiteStatementPrepare.getLong(columnIndexOrThrow12);
                int i20 = columnIndexOrThrow10;
                int i21 = i3;
                int i22 = columnIndexOrThrow9;
                int i23 = (int) sQLiteStatementPrepare.getLong(i21);
                int i24 = columnIndexOrThrow12;
                int i25 = i2;
                int i26 = columnIndexOrThrow11;
                int i27 = (int) sQLiteStatementPrepare.getLong(i25);
                int i28 = columnIndexOrThrow15;
                if (sQLiteStatementPrepare.isNull(i28)) {
                    i6 = columnIndexOrThrow;
                    text2 = null;
                } else {
                    i6 = columnIndexOrThrow;
                    text2 = sQLiteStatementPrepare.getText(i28);
                }
                int i29 = columnIndexOrThrow16;
                if (sQLiteStatementPrepare.isNull(i29)) {
                    columnIndexOrThrow16 = i29;
                    text3 = null;
                } else {
                    columnIndexOrThrow16 = i29;
                    text3 = sQLiteStatementPrepare.getText(i29);
                }
                int i30 = columnIndexOrThrow17;
                if (sQLiteStatementPrepare.isNull(i30)) {
                    columnIndexOrThrow17 = i30;
                    text4 = null;
                } else {
                    columnIndexOrThrow17 = i30;
                    text4 = sQLiteStatementPrepare.getText(i30);
                }
                int i31 = columnIndexOrThrow18;
                columnIndexOrThrow18 = i31;
                String text5 = sQLiteStatementPrepare.isNull(i31) ? null : sQLiteStatementPrepare.getText(i31);
                int i32 = columnIndexOrThrow19;
                if (((int) sQLiteStatementPrepare.getLong(i32)) != 0) {
                    i7 = columnIndexOrThrow20;
                    z = true;
                } else {
                    i7 = columnIndexOrThrow20;
                    z = false;
                }
                arrayList.add(new MotionPattern(lValueOf, i8, j4, j5, text, i9, i11, i13, i15, i17, i19, j6, i23, i27, text2, text3, text4, text5, z, ((int) sQLiteStatementPrepare.getLong(i7)) != 0));
                columnIndexOrThrow19 = i32;
                columnIndexOrThrow13 = i21;
                columnIndexOrThrow2 = i5;
                columnIndexOrThrow9 = i22;
                columnIndexOrThrow14 = i25;
                columnIndexOrThrow11 = i26;
                columnIndexOrThrow3 = i4;
                columnIndexOrThrow4 = i10;
                columnIndexOrThrow5 = i12;
                columnIndexOrThrow6 = i14;
                columnIndexOrThrow7 = i16;
                columnIndexOrThrow8 = i18;
                columnIndexOrThrow12 = i24;
                columnIndexOrThrow = i6;
                columnIndexOrThrow15 = i28;
                columnIndexOrThrow20 = i7;
                columnIndexOrThrow10 = i20;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.MotionPatternDao
    public Object getDataInTimeRange(final long startTime, final long endTime, final String userName, final Continuation<? super List<MotionPattern>> $completion) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.MotionPatternDao_Impl$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MotionPatternDao_Impl.lambda$getDataInTimeRange$12(startTime, endTime, userName, (SQLiteConnection) obj);
            }
        }, $completion);
    }

    static /* synthetic */ List lambda$getDataInTimeRange$12(long j2, long j3, String str, SQLiteConnection sQLiteConnection) {
        int i2;
        Long lValueOf;
        int i3;
        int i4;
        String text;
        int i5;
        int i6;
        String text2;
        String text3;
        String text4;
        int i7;
        boolean z;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("\n        SELECT * FROM motion_pattern_data \n        WHERE start_timestamp BETWEEN ? AND ?\n        AND (user_id = ? OR user_id IS NULL OR user_id = '')\n    ");
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
            int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "end_timestamp");
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "time_year_to_day");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sport_steps");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sport_distances");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sport_calories");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sport_mode");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "start_method");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sport_heart_rate");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sport_duration");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "min_heart_rate");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "max_heart_rate");
            int columnIndexOrThrow15 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow16 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow17 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow18 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow19 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            int columnIndexOrThrow20 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_uploaded");
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
                int i8 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
                long j4 = sQLiteStatementPrepare.getLong(columnIndexOrThrow3);
                long j5 = sQLiteStatementPrepare.getLong(columnIndexOrThrow4);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow5)) {
                    i5 = columnIndexOrThrow2;
                    i4 = columnIndexOrThrow3;
                    text = null;
                } else {
                    i4 = columnIndexOrThrow3;
                    text = sQLiteStatementPrepare.getText(columnIndexOrThrow5);
                    i5 = columnIndexOrThrow2;
                }
                int i9 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                int i10 = columnIndexOrThrow4;
                int i11 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7);
                int i12 = columnIndexOrThrow5;
                int i13 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow8);
                int i14 = columnIndexOrThrow6;
                int i15 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow9);
                int i16 = columnIndexOrThrow7;
                int i17 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10);
                int i18 = columnIndexOrThrow8;
                int i19 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11);
                long j6 = sQLiteStatementPrepare.getLong(columnIndexOrThrow12);
                int i20 = columnIndexOrThrow10;
                int i21 = i3;
                int i22 = columnIndexOrThrow9;
                int i23 = (int) sQLiteStatementPrepare.getLong(i21);
                int i24 = columnIndexOrThrow12;
                int i25 = i2;
                int i26 = columnIndexOrThrow11;
                int i27 = (int) sQLiteStatementPrepare.getLong(i25);
                int i28 = columnIndexOrThrow15;
                if (sQLiteStatementPrepare.isNull(i28)) {
                    i6 = columnIndexOrThrow;
                    text2 = null;
                } else {
                    i6 = columnIndexOrThrow;
                    text2 = sQLiteStatementPrepare.getText(i28);
                }
                int i29 = columnIndexOrThrow16;
                if (sQLiteStatementPrepare.isNull(i29)) {
                    columnIndexOrThrow16 = i29;
                    text3 = null;
                } else {
                    columnIndexOrThrow16 = i29;
                    text3 = sQLiteStatementPrepare.getText(i29);
                }
                int i30 = columnIndexOrThrow17;
                if (sQLiteStatementPrepare.isNull(i30)) {
                    columnIndexOrThrow17 = i30;
                    text4 = null;
                } else {
                    columnIndexOrThrow17 = i30;
                    text4 = sQLiteStatementPrepare.getText(i30);
                }
                int i31 = columnIndexOrThrow18;
                columnIndexOrThrow18 = i31;
                String text5 = sQLiteStatementPrepare.isNull(i31) ? null : sQLiteStatementPrepare.getText(i31);
                int i32 = columnIndexOrThrow19;
                if (((int) sQLiteStatementPrepare.getLong(i32)) != 0) {
                    i7 = columnIndexOrThrow20;
                    z = true;
                } else {
                    i7 = columnIndexOrThrow20;
                    z = false;
                }
                arrayList.add(new MotionPattern(lValueOf, i8, j4, j5, text, i9, i11, i13, i15, i17, i19, j6, i23, i27, text2, text3, text4, text5, z, ((int) sQLiteStatementPrepare.getLong(i7)) != 0));
                columnIndexOrThrow19 = i32;
                columnIndexOrThrow13 = i21;
                columnIndexOrThrow2 = i5;
                columnIndexOrThrow9 = i22;
                columnIndexOrThrow14 = i25;
                columnIndexOrThrow11 = i26;
                columnIndexOrThrow3 = i4;
                columnIndexOrThrow4 = i10;
                columnIndexOrThrow5 = i12;
                columnIndexOrThrow6 = i14;
                columnIndexOrThrow7 = i16;
                columnIndexOrThrow8 = i18;
                columnIndexOrThrow12 = i24;
                columnIndexOrThrow = i6;
                columnIndexOrThrow15 = i28;
                columnIndexOrThrow20 = i7;
                columnIndexOrThrow10 = i20;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.MotionPatternDao
    public Object deleteById(final long id, final Continuation<? super Integer> $completion) {
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.MotionPatternDao_Impl$$ExternalSyntheticLambda12
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MotionPatternDao_Impl.lambda$deleteById$13(id, (SQLiteConnection) obj);
            }
        }, $completion);
    }

    static /* synthetic */ Integer lambda$deleteById$13(long j2, SQLiteConnection sQLiteConnection) {
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("DELETE FROM motion_pattern_data WHERE id = ?");
        try {
            sQLiteStatementPrepare.mo181bindLong(1, j2);
            sQLiteStatementPrepare.step();
            return Integer.valueOf(SQLiteConnectionUtil.getTotalChangedRows(sQLiteConnection));
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.MotionPatternDao
    public Object deleteAll(final Continuation<? super Integer> $completion) {
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.MotionPatternDao_Impl$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MotionPatternDao_Impl.lambda$deleteAll$14((SQLiteConnection) obj);
            }
        }, $completion);
    }

    static /* synthetic */ Integer lambda$deleteAll$14(SQLiteConnection sQLiteConnection) {
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("DELETE FROM motion_pattern_data");
        try {
            sQLiteStatementPrepare.step();
            return Integer.valueOf(SQLiteConnectionUtil.getTotalChangedRows(sQLiteConnection));
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.MotionPatternDao
    public Object deleteAllByUser(final String userId, final Continuation<? super Integer> $completion) {
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.MotionPatternDao_Impl$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MotionPatternDao_Impl.lambda$deleteAllByUser$15(userId, (SQLiteConnection) obj);
            }
        }, $completion);
    }

    static /* synthetic */ Integer lambda$deleteAllByUser$15(String str, SQLiteConnection sQLiteConnection) {
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("DELETE FROM motion_pattern_data WHERE user_id = ?");
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
