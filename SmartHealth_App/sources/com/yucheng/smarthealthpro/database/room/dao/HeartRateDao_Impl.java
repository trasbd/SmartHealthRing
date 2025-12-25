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
import com.yucheng.smarthealthpro.database.room.bean.HeartRate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;

/* loaded from: classes4.dex */
public final class HeartRateDao_Impl implements HeartRateDao {
    private final RoomDatabase __db;
    private final EntityInsertAdapter<HeartRate> __insertAdapterOfHeartRate = new EntityInsertAdapter<HeartRate>() { // from class: com.yucheng.smarthealthpro.database.room.dao.HeartRateDao_Impl.1
        @Override // androidx.room.EntityInsertAdapter
        protected String createQuery() {
            return "INSERT OR REPLACE INTO `heart_rate_data` (`id`,`query_id`,`start_timestamp`,`time_year_to_day`,`heart_rate`,`user_id`,`device_type`,`device_mac_address`,`data_group_id`,`is_uploaded`,`is_other_uploaded`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // androidx.room.EntityInsertAdapter
        public void bind(SQLiteStatement sQLiteStatement, HeartRate heartRate) {
            if (heartRate.getId() == null) {
                sQLiteStatement.mo182bindNull(1);
            } else {
                sQLiteStatement.mo181bindLong(1, heartRate.getId().longValue());
            }
            sQLiteStatement.mo181bindLong(2, heartRate.getQueryID());
            sQLiteStatement.mo181bindLong(3, heartRate.getStartTimestamp());
            if (heartRate.getTimeYearToDay() == null) {
                sQLiteStatement.mo182bindNull(4);
            } else {
                sQLiteStatement.mo183bindText(4, heartRate.getTimeYearToDay());
            }
            sQLiteStatement.mo181bindLong(5, heartRate.getHeartRate());
            if (heartRate.getUserId() == null) {
                sQLiteStatement.mo182bindNull(6);
            } else {
                sQLiteStatement.mo183bindText(6, heartRate.getUserId());
            }
            if (heartRate.getDeviceType() == null) {
                sQLiteStatement.mo182bindNull(7);
            } else {
                sQLiteStatement.mo183bindText(7, heartRate.getDeviceType());
            }
            if (heartRate.getDeviceMacAddress() == null) {
                sQLiteStatement.mo182bindNull(8);
            } else {
                sQLiteStatement.mo183bindText(8, heartRate.getDeviceMacAddress());
            }
            if (heartRate.getDataGroupId() == null) {
                sQLiteStatement.mo182bindNull(9);
            } else {
                sQLiteStatement.mo183bindText(9, heartRate.getDataGroupId());
            }
            sQLiteStatement.mo181bindLong(10, heartRate.isUploaded() ? 1L : 0L);
            sQLiteStatement.mo181bindLong(11, heartRate.isOtherUploaded() ? 1L : 0L);
        }
    };
    private final EntityDeleteOrUpdateAdapter<HeartRate> __updateAdapterOfHeartRate = new EntityDeleteOrUpdateAdapter<HeartRate>() { // from class: com.yucheng.smarthealthpro.database.room.dao.HeartRateDao_Impl.2
        @Override // androidx.room.EntityDeleteOrUpdateAdapter
        protected String createQuery() {
            return "UPDATE OR ABORT `heart_rate_data` SET `id` = ?,`query_id` = ?,`start_timestamp` = ?,`time_year_to_day` = ?,`heart_rate` = ?,`user_id` = ?,`device_type` = ?,`device_mac_address` = ?,`data_group_id` = ?,`is_uploaded` = ?,`is_other_uploaded` = ? WHERE `id` = ?";
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // androidx.room.EntityDeleteOrUpdateAdapter
        public void bind(SQLiteStatement sQLiteStatement, HeartRate heartRate) {
            if (heartRate.getId() == null) {
                sQLiteStatement.mo182bindNull(1);
            } else {
                sQLiteStatement.mo181bindLong(1, heartRate.getId().longValue());
            }
            sQLiteStatement.mo181bindLong(2, heartRate.getQueryID());
            sQLiteStatement.mo181bindLong(3, heartRate.getStartTimestamp());
            if (heartRate.getTimeYearToDay() == null) {
                sQLiteStatement.mo182bindNull(4);
            } else {
                sQLiteStatement.mo183bindText(4, heartRate.getTimeYearToDay());
            }
            sQLiteStatement.mo181bindLong(5, heartRate.getHeartRate());
            if (heartRate.getUserId() == null) {
                sQLiteStatement.mo182bindNull(6);
            } else {
                sQLiteStatement.mo183bindText(6, heartRate.getUserId());
            }
            if (heartRate.getDeviceType() == null) {
                sQLiteStatement.mo182bindNull(7);
            } else {
                sQLiteStatement.mo183bindText(7, heartRate.getDeviceType());
            }
            if (heartRate.getDeviceMacAddress() == null) {
                sQLiteStatement.mo182bindNull(8);
            } else {
                sQLiteStatement.mo183bindText(8, heartRate.getDeviceMacAddress());
            }
            if (heartRate.getDataGroupId() == null) {
                sQLiteStatement.mo182bindNull(9);
            } else {
                sQLiteStatement.mo183bindText(9, heartRate.getDataGroupId());
            }
            sQLiteStatement.mo181bindLong(10, heartRate.isUploaded() ? 1L : 0L);
            sQLiteStatement.mo181bindLong(11, heartRate.isOtherUploaded() ? 1L : 0L);
            if (heartRate.getId() == null) {
                sQLiteStatement.mo182bindNull(12);
            } else {
                sQLiteStatement.mo181bindLong(12, heartRate.getId().longValue());
            }
        }
    };
    private final EntityDeleteOrUpdateAdapter<DataGroupIdUpdate> __updateAdapterOfDataGroupIdUpdateAsHeartRate = new EntityDeleteOrUpdateAdapter<DataGroupIdUpdate>() { // from class: com.yucheng.smarthealthpro.database.room.dao.HeartRateDao_Impl.3
        @Override // androidx.room.EntityDeleteOrUpdateAdapter
        protected String createQuery() {
            return "UPDATE OR ABORT `heart_rate_data` SET `id` = ?,`data_group_id` = ? WHERE `id` = ?";
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

    public HeartRateDao_Impl(final RoomDatabase __db) {
        this.__db = __db;
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.HeartRateDao
    public Object insert(final HeartRate metric, final Continuation<? super Long> arg1) {
        metric.getClass();
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.HeartRateDao_Impl$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f$0.lambda$insert$0(metric, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Long lambda$insert$0(HeartRate heartRate, SQLiteConnection sQLiteConnection) {
        return Long.valueOf(this.__insertAdapterOfHeartRate.insertAndReturnId(sQLiteConnection, heartRate));
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.HeartRateDao
    public Object insertAll(final List<HeartRate> metrics, final Continuation<? super List<Long>> arg1) {
        metrics.getClass();
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.HeartRateDao_Impl$$ExternalSyntheticLambda12
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f$0.lambda$insertAll$1(metrics, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ List lambda$insertAll$1(List list, SQLiteConnection sQLiteConnection) {
        return this.__insertAdapterOfHeartRate.insertAndReturnIdsList(sQLiteConnection, list);
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.HeartRateDao
    public Object update(final HeartRate metric, final Continuation<? super Unit> arg1) {
        metric.getClass();
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.HeartRateDao_Impl$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f$0.lambda$update$2(metric, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Unit lambda$update$2(HeartRate heartRate, SQLiteConnection sQLiteConnection) throws Exception {
        this.__updateAdapterOfHeartRate.handle(sQLiteConnection, heartRate);
        return Unit.INSTANCE;
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.HeartRateDao
    public Object updateDataGroupIds(final List<DataGroupIdUpdate> updates, final Continuation<? super Unit> arg1) {
        updates.getClass();
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.HeartRateDao_Impl$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f$0.lambda$updateDataGroupIds$3(updates, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Unit lambda$updateDataGroupIds$3(List list, SQLiteConnection sQLiteConnection) throws Exception {
        this.__updateAdapterOfDataGroupIdUpdateAsHeartRate.handleMultiple(sQLiteConnection, list);
        return Unit.INSTANCE;
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.HeartRateDao
    public Object getById(final long id, final Continuation<? super HeartRate> arg1) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.HeartRateDao_Impl$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return HeartRateDao_Impl.lambda$getById$4(id, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    static /* synthetic */ HeartRate lambda$getById$4(long j2, SQLiteConnection sQLiteConnection) {
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("SELECT * FROM heart_rate_data WHERE id = ?");
        try {
            sQLiteStatementPrepare.mo181bindLong(1, j2);
            int columnIndexOrThrow = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "id");
            int columnIndexOrThrow2 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "query_id");
            int columnIndexOrThrow3 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "start_timestamp");
            int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "time_year_to_day");
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "heart_rate");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_uploaded");
            HeartRate heartRate = null;
            if (sQLiteStatementPrepare.step()) {
                heartRate = new HeartRate(sQLiteStatementPrepare.isNull(columnIndexOrThrow) ? null : Long.valueOf(sQLiteStatementPrepare.getLong(columnIndexOrThrow)), (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2), sQLiteStatementPrepare.getLong(columnIndexOrThrow3), sQLiteStatementPrepare.isNull(columnIndexOrThrow4) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow4), (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5), sQLiteStatementPrepare.isNull(columnIndexOrThrow6) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow6), sQLiteStatementPrepare.isNull(columnIndexOrThrow7) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow7), sQLiteStatementPrepare.isNull(columnIndexOrThrow8) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow8), sQLiteStatementPrepare.isNull(columnIndexOrThrow9) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow9), ((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10)) != 0, ((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11)) != 0);
            }
            return heartRate;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.HeartRateDao
    public Object getByUser(final String userId, final Continuation<? super List<HeartRate>> arg1) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.HeartRateDao_Impl$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return HeartRateDao_Impl.lambda$getByUser$5(userId, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    static /* synthetic */ List lambda$getByUser$5(String str, SQLiteConnection sQLiteConnection) {
        String text;
        int i2;
        String text2;
        int i3;
        int i4;
        boolean z;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("SELECT * FROM heart_rate_data WHERE user_id = ? ORDER BY start_timestamp DESC");
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
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "heart_rate");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_uploaded");
            ArrayList arrayList = new ArrayList();
            while (sQLiteStatementPrepare.step()) {
                Long lValueOf = sQLiteStatementPrepare.isNull(columnIndexOrThrow) ? null : Long.valueOf(sQLiteStatementPrepare.getLong(columnIndexOrThrow));
                int i5 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
                long j2 = sQLiteStatementPrepare.getLong(columnIndexOrThrow3);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow4)) {
                    i2 = columnIndexOrThrow2;
                    text = null;
                } else {
                    text = sQLiteStatementPrepare.getText(columnIndexOrThrow4);
                    i2 = columnIndexOrThrow2;
                }
                int i6 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5);
                String text3 = sQLiteStatementPrepare.isNull(columnIndexOrThrow6) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow6);
                String text4 = sQLiteStatementPrepare.isNull(columnIndexOrThrow7) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow7);
                String text5 = sQLiteStatementPrepare.isNull(columnIndexOrThrow8) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow8);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow9)) {
                    i3 = columnIndexOrThrow3;
                    text2 = null;
                } else {
                    text2 = sQLiteStatementPrepare.getText(columnIndexOrThrow9);
                    i3 = columnIndexOrThrow3;
                }
                if (((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10)) != 0) {
                    i4 = columnIndexOrThrow4;
                    z = true;
                } else {
                    i4 = columnIndexOrThrow4;
                    z = false;
                }
                arrayList.add(new HeartRate(lValueOf, i5, j2, text, i6, text3, text4, text5, text2, z, ((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11)) != 0));
                columnIndexOrThrow3 = i3;
                columnIndexOrThrow4 = i4;
                columnIndexOrThrow2 = i2;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.HeartRateDao
    public Object getByStartTimestamp(final long startTimestamp, final Continuation<? super List<HeartRate>> arg1) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.HeartRateDao_Impl$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return HeartRateDao_Impl.lambda$getByStartTimestamp$6(startTimestamp, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    static /* synthetic */ List lambda$getByStartTimestamp$6(long j2, SQLiteConnection sQLiteConnection) {
        int i2;
        int i3;
        String text;
        int i4;
        String text2;
        int i5;
        boolean z;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("SELECT * FROM heart_rate_data WHERE start_timestamp = ?");
        try {
            sQLiteStatementPrepare.mo181bindLong(1, j2);
            int columnIndexOrThrow = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "id");
            int columnIndexOrThrow2 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "query_id");
            int columnIndexOrThrow3 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "start_timestamp");
            int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "time_year_to_day");
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "heart_rate");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_uploaded");
            ArrayList arrayList = new ArrayList();
            while (sQLiteStatementPrepare.step()) {
                Long lValueOf = sQLiteStatementPrepare.isNull(columnIndexOrThrow) ? null : Long.valueOf(sQLiteStatementPrepare.getLong(columnIndexOrThrow));
                int i6 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
                long j3 = sQLiteStatementPrepare.getLong(columnIndexOrThrow3);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow4)) {
                    i2 = columnIndexOrThrow;
                    i3 = columnIndexOrThrow2;
                    text = null;
                } else {
                    i2 = columnIndexOrThrow;
                    i3 = columnIndexOrThrow2;
                    text = sQLiteStatementPrepare.getText(columnIndexOrThrow4);
                }
                int i7 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5);
                String text3 = sQLiteStatementPrepare.isNull(columnIndexOrThrow6) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow6);
                String text4 = sQLiteStatementPrepare.isNull(columnIndexOrThrow7) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow7);
                String text5 = sQLiteStatementPrepare.isNull(columnIndexOrThrow8) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow8);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow9)) {
                    i4 = columnIndexOrThrow3;
                    text2 = null;
                } else {
                    i4 = columnIndexOrThrow3;
                    text2 = sQLiteStatementPrepare.getText(columnIndexOrThrow9);
                }
                if (((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10)) != 0) {
                    z = true;
                    i5 = columnIndexOrThrow4;
                } else {
                    i5 = columnIndexOrThrow4;
                    z = false;
                }
                arrayList.add(new HeartRate(lValueOf, i6, j3, text, i7, text3, text4, text5, text2, z, ((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11)) != 0));
                columnIndexOrThrow3 = i4;
                columnIndexOrThrow = i2;
                columnIndexOrThrow4 = i5;
                columnIndexOrThrow2 = i3;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.HeartRateDao
    public Object getByUserId(final String userId, final Continuation<? super List<HeartRate>> arg1) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.HeartRateDao_Impl$$ExternalSyntheticLambda14
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return HeartRateDao_Impl.lambda$getByUserId$7(userId, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    static /* synthetic */ List lambda$getByUserId$7(String str, SQLiteConnection sQLiteConnection) {
        String text;
        int i2;
        String text2;
        int i3;
        int i4;
        boolean z;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("\n        SELECT * FROM heart_rate_data \n        WHERE user_id = ? \n        OR user_id = \"\"\n        OR user_id IS NULL\n        ORDER BY start_timestamp DESC\n    ");
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
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "heart_rate");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_uploaded");
            ArrayList arrayList = new ArrayList();
            while (sQLiteStatementPrepare.step()) {
                Long lValueOf = sQLiteStatementPrepare.isNull(columnIndexOrThrow) ? null : Long.valueOf(sQLiteStatementPrepare.getLong(columnIndexOrThrow));
                int i5 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
                long j2 = sQLiteStatementPrepare.getLong(columnIndexOrThrow3);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow4)) {
                    i2 = columnIndexOrThrow2;
                    text = null;
                } else {
                    text = sQLiteStatementPrepare.getText(columnIndexOrThrow4);
                    i2 = columnIndexOrThrow2;
                }
                int i6 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5);
                String text3 = sQLiteStatementPrepare.isNull(columnIndexOrThrow6) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow6);
                String text4 = sQLiteStatementPrepare.isNull(columnIndexOrThrow7) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow7);
                String text5 = sQLiteStatementPrepare.isNull(columnIndexOrThrow8) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow8);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow9)) {
                    i3 = columnIndexOrThrow3;
                    text2 = null;
                } else {
                    text2 = sQLiteStatementPrepare.getText(columnIndexOrThrow9);
                    i3 = columnIndexOrThrow3;
                }
                if (((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10)) != 0) {
                    i4 = columnIndexOrThrow4;
                    z = true;
                } else {
                    i4 = columnIndexOrThrow4;
                    z = false;
                }
                arrayList.add(new HeartRate(lValueOf, i5, j2, text, i6, text3, text4, text5, text2, z, ((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11)) != 0));
                columnIndexOrThrow3 = i3;
                columnIndexOrThrow4 = i4;
                columnIndexOrThrow2 = i2;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.HeartRateDao
    public Object querySyncedWithYearToDay(final String yearToDay, final String userId, final boolean synced, final Continuation<? super List<HeartRate>> arg3) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.HeartRateDao_Impl$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return HeartRateDao_Impl.lambda$querySyncedWithYearToDay$8(synced, yearToDay, userId, (SQLiteConnection) obj);
            }
        }, arg3);
    }

    static /* synthetic */ List lambda$querySyncedWithYearToDay$8(boolean z, String str, String str2, SQLiteConnection sQLiteConnection) {
        int i2;
        int i3;
        String text;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("\n        SELECT * FROM heart_rate_data \n        WHERE is_uploaded = ?\n        AND time_year_to_day = ?\n        AND (user_id = ? \n        OR user_id = \"\"\n        OR user_id IS NULL)\n        ORDER BY start_timestamp DESC\n    ");
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
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "heart_rate");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_uploaded");
            ArrayList arrayList = new ArrayList();
            while (sQLiteStatementPrepare.step()) {
                Long lValueOf = sQLiteStatementPrepare.isNull(columnIndexOrThrow) ? null : Long.valueOf(sQLiteStatementPrepare.getLong(columnIndexOrThrow));
                int i4 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
                long j2 = sQLiteStatementPrepare.getLong(columnIndexOrThrow3);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow4)) {
                    i2 = columnIndexOrThrow;
                    i3 = columnIndexOrThrow2;
                    text = null;
                } else {
                    i2 = columnIndexOrThrow;
                    i3 = columnIndexOrThrow2;
                    text = sQLiteStatementPrepare.getText(columnIndexOrThrow4);
                }
                int i5 = columnIndexOrThrow3;
                arrayList.add(new HeartRate(lValueOf, i4, j2, text, (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5), sQLiteStatementPrepare.isNull(columnIndexOrThrow6) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow6), sQLiteStatementPrepare.isNull(columnIndexOrThrow7) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow7), sQLiteStatementPrepare.isNull(columnIndexOrThrow8) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow8), sQLiteStatementPrepare.isNull(columnIndexOrThrow9) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow9), ((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10)) != 0, ((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11)) != 0));
                columnIndexOrThrow = i2;
                columnIndexOrThrow2 = i3;
                columnIndexOrThrow3 = i5;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.HeartRateDao
    public Object queryAll(final String userId, final Continuation<? super List<HeartRate>> arg1) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.HeartRateDao_Impl$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return HeartRateDao_Impl.lambda$queryAll$9(userId, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    static /* synthetic */ List lambda$queryAll$9(String str, SQLiteConnection sQLiteConnection) {
        String text;
        int i2;
        String text2;
        int i3;
        int i4;
        boolean z;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("\n        SELECT * FROM heart_rate_data \n        WHERE (user_id = ? \n        OR user_id = \"\"\n        OR user_id IS NULL)\n        ORDER BY start_timestamp DESC\n    ");
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
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "heart_rate");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_uploaded");
            ArrayList arrayList = new ArrayList();
            while (sQLiteStatementPrepare.step()) {
                Long lValueOf = sQLiteStatementPrepare.isNull(columnIndexOrThrow) ? null : Long.valueOf(sQLiteStatementPrepare.getLong(columnIndexOrThrow));
                int i5 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
                long j2 = sQLiteStatementPrepare.getLong(columnIndexOrThrow3);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow4)) {
                    i2 = columnIndexOrThrow2;
                    text = null;
                } else {
                    text = sQLiteStatementPrepare.getText(columnIndexOrThrow4);
                    i2 = columnIndexOrThrow2;
                }
                int i6 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5);
                String text3 = sQLiteStatementPrepare.isNull(columnIndexOrThrow6) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow6);
                String text4 = sQLiteStatementPrepare.isNull(columnIndexOrThrow7) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow7);
                String text5 = sQLiteStatementPrepare.isNull(columnIndexOrThrow8) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow8);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow9)) {
                    i3 = columnIndexOrThrow3;
                    text2 = null;
                } else {
                    text2 = sQLiteStatementPrepare.getText(columnIndexOrThrow9);
                    i3 = columnIndexOrThrow3;
                }
                if (((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10)) != 0) {
                    i4 = columnIndexOrThrow4;
                    z = true;
                } else {
                    i4 = columnIndexOrThrow4;
                    z = false;
                }
                arrayList.add(new HeartRate(lValueOf, i5, j2, text, i6, text3, text4, text5, text2, z, ((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11)) != 0));
                columnIndexOrThrow3 = i3;
                columnIndexOrThrow4 = i4;
                columnIndexOrThrow2 = i2;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.HeartRateDao
    public Object queryByYearToDay(final String yearToDay, final String userId, final Continuation<? super List<HeartRate>> arg2) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.HeartRateDao_Impl$$ExternalSyntheticLambda13
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return HeartRateDao_Impl.lambda$queryByYearToDay$10(yearToDay, userId, (SQLiteConnection) obj);
            }
        }, arg2);
    }

    static /* synthetic */ List lambda$queryByYearToDay$10(String str, String str2, SQLiteConnection sQLiteConnection) {
        int i2;
        Long lValueOf;
        int i3;
        int i4;
        String text;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("\n        SELECT * FROM heart_rate_data \n        WHERE time_year_to_day = ?\n        AND (user_id = ? \n        OR user_id = \"\"\n        OR user_id IS NULL)\n        ORDER BY start_timestamp DESC\n    ");
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
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "heart_rate");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_uploaded");
            ArrayList arrayList = new ArrayList();
            while (sQLiteStatementPrepare.step()) {
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow)) {
                    i2 = columnIndexOrThrow3;
                    lValueOf = null;
                } else {
                    i2 = columnIndexOrThrow3;
                    lValueOf = Long.valueOf(sQLiteStatementPrepare.getLong(columnIndexOrThrow));
                }
                int i5 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
                columnIndexOrThrow3 = i2;
                long j2 = sQLiteStatementPrepare.getLong(columnIndexOrThrow3);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow4)) {
                    i3 = columnIndexOrThrow;
                    i4 = columnIndexOrThrow2;
                    text = null;
                } else {
                    i3 = columnIndexOrThrow;
                    i4 = columnIndexOrThrow2;
                    text = sQLiteStatementPrepare.getText(columnIndexOrThrow4);
                }
                arrayList.add(new HeartRate(lValueOf, i5, j2, text, (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5), sQLiteStatementPrepare.isNull(columnIndexOrThrow6) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow6), sQLiteStatementPrepare.isNull(columnIndexOrThrow7) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow7), sQLiteStatementPrepare.isNull(columnIndexOrThrow8) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow8), sQLiteStatementPrepare.isNull(columnIndexOrThrow9) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow9), ((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10)) != 0, ((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11)) != 0));
                columnIndexOrThrow = i3;
                columnIndexOrThrow2 = i4;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.HeartRateDao
    public Object querySinceYearToDay(final String yearToDay, final String userId, final Continuation<? super List<HeartRate>> arg2) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.HeartRateDao_Impl$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return HeartRateDao_Impl.lambda$querySinceYearToDay$11(yearToDay, userId, (SQLiteConnection) obj);
            }
        }, arg2);
    }

    static /* synthetic */ List lambda$querySinceYearToDay$11(String str, String str2, SQLiteConnection sQLiteConnection) {
        int i2;
        Long lValueOf;
        int i3;
        int i4;
        String text;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("\n        SELECT * FROM heart_rate_data \n        WHERE time_year_to_day >= ?\n        AND (user_id = ? OR user_id = \"\" OR user_id IS NULL)\n    ");
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
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "heart_rate");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_uploaded");
            ArrayList arrayList = new ArrayList();
            while (sQLiteStatementPrepare.step()) {
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow)) {
                    i2 = columnIndexOrThrow3;
                    lValueOf = null;
                } else {
                    i2 = columnIndexOrThrow3;
                    lValueOf = Long.valueOf(sQLiteStatementPrepare.getLong(columnIndexOrThrow));
                }
                int i5 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
                columnIndexOrThrow3 = i2;
                long j2 = sQLiteStatementPrepare.getLong(columnIndexOrThrow3);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow4)) {
                    i3 = columnIndexOrThrow;
                    i4 = columnIndexOrThrow2;
                    text = null;
                } else {
                    i3 = columnIndexOrThrow;
                    i4 = columnIndexOrThrow2;
                    text = sQLiteStatementPrepare.getText(columnIndexOrThrow4);
                }
                arrayList.add(new HeartRate(lValueOf, i5, j2, text, (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5), sQLiteStatementPrepare.isNull(columnIndexOrThrow6) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow6), sQLiteStatementPrepare.isNull(columnIndexOrThrow7) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow7), sQLiteStatementPrepare.isNull(columnIndexOrThrow8) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow8), sQLiteStatementPrepare.isNull(columnIndexOrThrow9) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow9), ((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10)) != 0, ((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11)) != 0));
                columnIndexOrThrow = i3;
                columnIndexOrThrow2 = i4;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.HeartRateDao
    public Object getDataInTimeRange(final long startTime, final long endTime, final String userName, final Continuation<? super List<HeartRate>> arg3) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.HeartRateDao_Impl$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return HeartRateDao_Impl.lambda$getDataInTimeRange$12(startTime, endTime, userName, (SQLiteConnection) obj);
            }
        }, arg3);
    }

    static /* synthetic */ List lambda$getDataInTimeRange$12(long j2, long j3, String str, SQLiteConnection sQLiteConnection) {
        int i2;
        String text;
        int i3;
        String text2;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("\n        SELECT * FROM heart_rate_data \n        WHERE start_timestamp BETWEEN ? AND ?\n        AND (user_id = ? OR user_id IS NULL OR user_id = '')\n    ");
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
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "heart_rate");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_uploaded");
            ArrayList arrayList = new ArrayList();
            while (sQLiteStatementPrepare.step()) {
                Long lValueOf = sQLiteStatementPrepare.isNull(columnIndexOrThrow) ? null : Long.valueOf(sQLiteStatementPrepare.getLong(columnIndexOrThrow));
                int i4 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
                long j4 = sQLiteStatementPrepare.getLong(columnIndexOrThrow3);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow4)) {
                    i2 = columnIndexOrThrow2;
                    text = null;
                } else {
                    i2 = columnIndexOrThrow2;
                    text = sQLiteStatementPrepare.getText(columnIndexOrThrow4);
                }
                int i5 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5);
                String text3 = sQLiteStatementPrepare.isNull(columnIndexOrThrow6) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow6);
                String text4 = sQLiteStatementPrepare.isNull(columnIndexOrThrow7) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow7);
                String text5 = sQLiteStatementPrepare.isNull(columnIndexOrThrow8) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow8);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow9)) {
                    i3 = columnIndexOrThrow3;
                    text2 = null;
                } else {
                    i3 = columnIndexOrThrow3;
                    text2 = sQLiteStatementPrepare.getText(columnIndexOrThrow9);
                }
                int i6 = columnIndexOrThrow4;
                arrayList.add(new HeartRate(lValueOf, i4, j4, text, i5, text3, text4, text5, text2, ((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10)) != 0, ((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11)) != 0));
                columnIndexOrThrow3 = i3;
                columnIndexOrThrow2 = i2;
                columnIndexOrThrow4 = i6;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.HeartRateDao
    public Object markAsSynced(final List<Long> ids, final boolean synced, final Continuation<? super Integer> arg2) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE blood_ketones_data SET is_uploaded = ? WHERE id IN (");
        StringUtil.appendPlaceholders(sb, ids.size());
        sb.append(")");
        final String string = sb.toString();
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.HeartRateDao_Impl$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return HeartRateDao_Impl.lambda$markAsSynced$13(string, synced, ids, (SQLiteConnection) obj);
            }
        }, arg2);
    }

    static /* synthetic */ Integer lambda$markAsSynced$13(String str, boolean z, List list, SQLiteConnection sQLiteConnection) {
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

    @Override // com.yucheng.smarthealthpro.database.room.dao.HeartRateDao
    public Object deleteById(final long id, final Continuation<? super Integer> arg1) {
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.HeartRateDao_Impl$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return HeartRateDao_Impl.lambda$deleteById$14(id, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    static /* synthetic */ Integer lambda$deleteById$14(long j2, SQLiteConnection sQLiteConnection) {
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("DELETE FROM heart_rate_data WHERE id = ?");
        try {
            sQLiteStatementPrepare.mo181bindLong(1, j2);
            sQLiteStatementPrepare.step();
            return Integer.valueOf(SQLiteConnectionUtil.getTotalChangedRows(sQLiteConnection));
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.HeartRateDao
    public Object deleteAll(final Continuation<? super Integer> arg0) {
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.HeartRateDao_Impl$$ExternalSyntheticLambda15
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return HeartRateDao_Impl.lambda$deleteAll$15((SQLiteConnection) obj);
            }
        }, arg0);
    }

    static /* synthetic */ Integer lambda$deleteAll$15(SQLiteConnection sQLiteConnection) {
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("DELETE FROM heart_rate_data");
        try {
            sQLiteStatementPrepare.step();
            return Integer.valueOf(SQLiteConnectionUtil.getTotalChangedRows(sQLiteConnection));
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.HeartRateDao
    public Object deleteAllByUser(final String userId, final Continuation<? super Integer> arg1) {
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.HeartRateDao_Impl$$ExternalSyntheticLambda16
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return HeartRateDao_Impl.lambda$deleteAllByUser$16(userId, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    static /* synthetic */ Integer lambda$deleteAllByUser$16(String str, SQLiteConnection sQLiteConnection) {
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("DELETE FROM heart_rate_data WHERE user_id = ?");
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
