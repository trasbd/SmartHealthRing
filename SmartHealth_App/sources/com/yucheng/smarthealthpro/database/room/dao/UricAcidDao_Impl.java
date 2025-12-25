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
import com.yucheng.smarthealthpro.database.room.bean.UricAcid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;

/* loaded from: classes4.dex */
public final class UricAcidDao_Impl implements UricAcidDao {
    private final RoomDatabase __db;
    private final EntityInsertAdapter<UricAcid> __insertAdapterOfUricAcid = new EntityInsertAdapter<UricAcid>() { // from class: com.yucheng.smarthealthpro.database.room.dao.UricAcidDao_Impl.1
        @Override // androidx.room.EntityInsertAdapter
        protected String createQuery() {
            return "INSERT OR REPLACE INTO `uric_acid_data` (`id`,`query_id`,`start_timestamp`,`time_year_to_day`,`uric_acid`,`measure_mode`,`user_id`,`device_type`,`device_mac_address`,`data_group_id`,`is_uploaded`,`is_other_uploaded`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // androidx.room.EntityInsertAdapter
        public void bind(SQLiteStatement sQLiteStatement, UricAcid uricAcid) {
            if (uricAcid.getId() == null) {
                sQLiteStatement.mo182bindNull(1);
            } else {
                sQLiteStatement.mo181bindLong(1, uricAcid.getId().longValue());
            }
            sQLiteStatement.mo181bindLong(2, uricAcid.getQueryID());
            sQLiteStatement.mo181bindLong(3, uricAcid.getStartTimestamp());
            if (uricAcid.getTimeYearToDay() == null) {
                sQLiteStatement.mo182bindNull(4);
            } else {
                sQLiteStatement.mo183bindText(4, uricAcid.getTimeYearToDay());
            }
            sQLiteStatement.mo181bindLong(5, uricAcid.getUricAcid());
            sQLiteStatement.mo181bindLong(6, uricAcid.getMeasureMode());
            if (uricAcid.getUserId() == null) {
                sQLiteStatement.mo182bindNull(7);
            } else {
                sQLiteStatement.mo183bindText(7, uricAcid.getUserId());
            }
            if (uricAcid.getDeviceType() == null) {
                sQLiteStatement.mo182bindNull(8);
            } else {
                sQLiteStatement.mo183bindText(8, uricAcid.getDeviceType());
            }
            if (uricAcid.getDeviceMacAddress() == null) {
                sQLiteStatement.mo182bindNull(9);
            } else {
                sQLiteStatement.mo183bindText(9, uricAcid.getDeviceMacAddress());
            }
            if (uricAcid.getDataGroupId() == null) {
                sQLiteStatement.mo182bindNull(10);
            } else {
                sQLiteStatement.mo183bindText(10, uricAcid.getDataGroupId());
            }
            sQLiteStatement.mo181bindLong(11, uricAcid.isUploaded() ? 1L : 0L);
            sQLiteStatement.mo181bindLong(12, uricAcid.isOtherUploaded() ? 1L : 0L);
        }
    };
    private final EntityDeleteOrUpdateAdapter<UricAcid> __updateAdapterOfUricAcid = new EntityDeleteOrUpdateAdapter<UricAcid>() { // from class: com.yucheng.smarthealthpro.database.room.dao.UricAcidDao_Impl.2
        @Override // androidx.room.EntityDeleteOrUpdateAdapter
        protected String createQuery() {
            return "UPDATE OR ABORT `uric_acid_data` SET `id` = ?,`query_id` = ?,`start_timestamp` = ?,`time_year_to_day` = ?,`uric_acid` = ?,`measure_mode` = ?,`user_id` = ?,`device_type` = ?,`device_mac_address` = ?,`data_group_id` = ?,`is_uploaded` = ?,`is_other_uploaded` = ? WHERE `id` = ?";
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // androidx.room.EntityDeleteOrUpdateAdapter
        public void bind(SQLiteStatement sQLiteStatement, UricAcid uricAcid) {
            if (uricAcid.getId() == null) {
                sQLiteStatement.mo182bindNull(1);
            } else {
                sQLiteStatement.mo181bindLong(1, uricAcid.getId().longValue());
            }
            sQLiteStatement.mo181bindLong(2, uricAcid.getQueryID());
            sQLiteStatement.mo181bindLong(3, uricAcid.getStartTimestamp());
            if (uricAcid.getTimeYearToDay() == null) {
                sQLiteStatement.mo182bindNull(4);
            } else {
                sQLiteStatement.mo183bindText(4, uricAcid.getTimeYearToDay());
            }
            sQLiteStatement.mo181bindLong(5, uricAcid.getUricAcid());
            sQLiteStatement.mo181bindLong(6, uricAcid.getMeasureMode());
            if (uricAcid.getUserId() == null) {
                sQLiteStatement.mo182bindNull(7);
            } else {
                sQLiteStatement.mo183bindText(7, uricAcid.getUserId());
            }
            if (uricAcid.getDeviceType() == null) {
                sQLiteStatement.mo182bindNull(8);
            } else {
                sQLiteStatement.mo183bindText(8, uricAcid.getDeviceType());
            }
            if (uricAcid.getDeviceMacAddress() == null) {
                sQLiteStatement.mo182bindNull(9);
            } else {
                sQLiteStatement.mo183bindText(9, uricAcid.getDeviceMacAddress());
            }
            if (uricAcid.getDataGroupId() == null) {
                sQLiteStatement.mo182bindNull(10);
            } else {
                sQLiteStatement.mo183bindText(10, uricAcid.getDataGroupId());
            }
            sQLiteStatement.mo181bindLong(11, uricAcid.isUploaded() ? 1L : 0L);
            sQLiteStatement.mo181bindLong(12, uricAcid.isOtherUploaded() ? 1L : 0L);
            if (uricAcid.getId() == null) {
                sQLiteStatement.mo182bindNull(13);
            } else {
                sQLiteStatement.mo181bindLong(13, uricAcid.getId().longValue());
            }
        }
    };
    private final EntityDeleteOrUpdateAdapter<DataGroupIdUpdate> __updateAdapterOfDataGroupIdUpdateAsUricAcid = new EntityDeleteOrUpdateAdapter<DataGroupIdUpdate>() { // from class: com.yucheng.smarthealthpro.database.room.dao.UricAcidDao_Impl.3
        @Override // androidx.room.EntityDeleteOrUpdateAdapter
        protected String createQuery() {
            return "UPDATE OR ABORT `uric_acid_data` SET `id` = ?,`data_group_id` = ? WHERE `id` = ?";
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

    public UricAcidDao_Impl(final RoomDatabase __db) {
        this.__db = __db;
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.UricAcidDao
    public Object insert(final UricAcid metric, final Continuation<? super Long> arg1) {
        metric.getClass();
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.UricAcidDao_Impl$$ExternalSyntheticLambda15
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f$0.lambda$insert$0(metric, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Long lambda$insert$0(UricAcid uricAcid, SQLiteConnection sQLiteConnection) {
        return Long.valueOf(this.__insertAdapterOfUricAcid.insertAndReturnId(sQLiteConnection, uricAcid));
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.UricAcidDao
    public Object insertAll(final List<UricAcid> metrics, final Continuation<? super List<Long>> arg1) {
        metrics.getClass();
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.UricAcidDao_Impl$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f$0.lambda$insertAll$1(metrics, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ List lambda$insertAll$1(List list, SQLiteConnection sQLiteConnection) {
        return this.__insertAdapterOfUricAcid.insertAndReturnIdsList(sQLiteConnection, list);
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.UricAcidDao
    public Object update(final UricAcid metric, final Continuation<? super Unit> arg1) {
        metric.getClass();
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.UricAcidDao_Impl$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f$0.lambda$update$2(metric, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Unit lambda$update$2(UricAcid uricAcid, SQLiteConnection sQLiteConnection) throws Exception {
        this.__updateAdapterOfUricAcid.handle(sQLiteConnection, uricAcid);
        return Unit.INSTANCE;
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.UricAcidDao
    public Object updateDataGroupIds(final List<DataGroupIdUpdate> updates, final Continuation<? super Unit> arg1) {
        updates.getClass();
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.UricAcidDao_Impl$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f$0.lambda$updateDataGroupIds$3(updates, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Unit lambda$updateDataGroupIds$3(List list, SQLiteConnection sQLiteConnection) throws Exception {
        this.__updateAdapterOfDataGroupIdUpdateAsUricAcid.handleMultiple(sQLiteConnection, list);
        return Unit.INSTANCE;
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.UricAcidDao
    public Object getById(final long id, final Continuation<? super UricAcid> arg1) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.UricAcidDao_Impl$$ExternalSyntheticLambda16
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return UricAcidDao_Impl.lambda$getById$4(id, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    static /* synthetic */ UricAcid lambda$getById$4(long j2, SQLiteConnection sQLiteConnection) {
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("SELECT * FROM uric_acid_data WHERE id = ?");
        try {
            sQLiteStatementPrepare.mo181bindLong(1, j2);
            int columnIndexOrThrow = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "id");
            int columnIndexOrThrow2 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "query_id");
            int columnIndexOrThrow3 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "start_timestamp");
            int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "time_year_to_day");
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "uric_acid");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "measure_mode");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_uploaded");
            UricAcid uricAcid = null;
            if (sQLiteStatementPrepare.step()) {
                uricAcid = new UricAcid(sQLiteStatementPrepare.isNull(columnIndexOrThrow) ? null : Long.valueOf(sQLiteStatementPrepare.getLong(columnIndexOrThrow)), (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2), sQLiteStatementPrepare.getLong(columnIndexOrThrow3), sQLiteStatementPrepare.isNull(columnIndexOrThrow4) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow4), (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5), (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6), sQLiteStatementPrepare.isNull(columnIndexOrThrow7) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow7), sQLiteStatementPrepare.isNull(columnIndexOrThrow8) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow8), sQLiteStatementPrepare.isNull(columnIndexOrThrow9) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow9), sQLiteStatementPrepare.isNull(columnIndexOrThrow10) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow10), ((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11)) != 0, ((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12)) != 0);
            }
            return uricAcid;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.UricAcidDao
    public Object getByUser(final String userId, final Continuation<? super List<UricAcid>> arg1) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.UricAcidDao_Impl$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return UricAcidDao_Impl.lambda$getByUser$5(userId, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    static /* synthetic */ List lambda$getByUser$5(String str, SQLiteConnection sQLiteConnection) {
        ArrayList arrayList;
        Long lValueOf;
        String text;
        int i2;
        int i3;
        boolean z;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("SELECT * FROM uric_acid_data WHERE user_id = ? ORDER BY start_timestamp DESC");
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
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "uric_acid");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "measure_mode");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_uploaded");
            ArrayList arrayList2 = new ArrayList();
            while (sQLiteStatementPrepare.step()) {
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow)) {
                    arrayList = arrayList2;
                    lValueOf = null;
                } else {
                    arrayList = arrayList2;
                    lValueOf = Long.valueOf(sQLiteStatementPrepare.getLong(columnIndexOrThrow));
                }
                int i4 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
                long j2 = sQLiteStatementPrepare.getLong(columnIndexOrThrow3);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow4)) {
                    i2 = columnIndexOrThrow2;
                    text = null;
                } else {
                    text = sQLiteStatementPrepare.getText(columnIndexOrThrow4);
                    i2 = columnIndexOrThrow2;
                }
                int i5 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5);
                int i6 = columnIndexOrThrow3;
                int i7 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                String text2 = sQLiteStatementPrepare.isNull(columnIndexOrThrow7) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow7);
                String text3 = sQLiteStatementPrepare.isNull(columnIndexOrThrow8) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow8);
                String text4 = sQLiteStatementPrepare.isNull(columnIndexOrThrow9) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow9);
                String text5 = sQLiteStatementPrepare.isNull(columnIndexOrThrow10) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow10);
                int i8 = columnIndexOrThrow4;
                if (((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11)) != 0) {
                    i3 = columnIndexOrThrow5;
                    z = true;
                } else {
                    i3 = columnIndexOrThrow5;
                    z = false;
                }
                UricAcid uricAcid = new UricAcid(lValueOf, i4, j2, text, i5, i7, text2, text3, text4, text5, z, ((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12)) != 0);
                ArrayList arrayList3 = arrayList;
                arrayList3.add(uricAcid);
                arrayList2 = arrayList3;
                columnIndexOrThrow5 = i3;
                columnIndexOrThrow2 = i2;
                columnIndexOrThrow4 = i8;
                columnIndexOrThrow3 = i6;
            }
            return arrayList2;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.UricAcidDao
    public Object getByStartTimestamp(final long startTimestamp, final Continuation<? super List<UricAcid>> arg1) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.UricAcidDao_Impl$$ExternalSyntheticLambda13
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return UricAcidDao_Impl.lambda$getByStartTimestamp$6(startTimestamp, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    static /* synthetic */ List lambda$getByStartTimestamp$6(long j2, SQLiteConnection sQLiteConnection) {
        ArrayList arrayList;
        Long lValueOf;
        int i2;
        int i3;
        String text;
        int i4;
        boolean z;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("SELECT * FROM uric_acid_data WHERE start_timestamp = ?");
        try {
            sQLiteStatementPrepare.mo181bindLong(1, j2);
            int columnIndexOrThrow = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "id");
            int columnIndexOrThrow2 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "query_id");
            int columnIndexOrThrow3 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "start_timestamp");
            int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "time_year_to_day");
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "uric_acid");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "measure_mode");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_uploaded");
            ArrayList arrayList2 = new ArrayList();
            while (sQLiteStatementPrepare.step()) {
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow)) {
                    arrayList = arrayList2;
                    lValueOf = null;
                } else {
                    arrayList = arrayList2;
                    lValueOf = Long.valueOf(sQLiteStatementPrepare.getLong(columnIndexOrThrow));
                }
                int i5 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
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
                int i6 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5);
                int i7 = columnIndexOrThrow3;
                int i8 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                String text2 = sQLiteStatementPrepare.isNull(columnIndexOrThrow7) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow7);
                String text3 = sQLiteStatementPrepare.isNull(columnIndexOrThrow8) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow8);
                String text4 = sQLiteStatementPrepare.isNull(columnIndexOrThrow9) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow9);
                String text5 = sQLiteStatementPrepare.isNull(columnIndexOrThrow10) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow10);
                int i9 = columnIndexOrThrow4;
                if (((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11)) != 0) {
                    z = true;
                    i4 = columnIndexOrThrow5;
                } else {
                    i4 = columnIndexOrThrow5;
                    z = false;
                }
                UricAcid uricAcid = new UricAcid(lValueOf, i5, j3, text, i6, i8, text2, text3, text4, text5, z, ((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12)) != 0);
                ArrayList arrayList3 = arrayList;
                arrayList3.add(uricAcid);
                columnIndexOrThrow2 = i3;
                arrayList2 = arrayList3;
                columnIndexOrThrow5 = i4;
                columnIndexOrThrow3 = i7;
                columnIndexOrThrow4 = i9;
                columnIndexOrThrow = i2;
            }
            return arrayList2;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.UricAcidDao
    public Object getByUserId(final String userId, final Continuation<? super List<UricAcid>> arg1) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.UricAcidDao_Impl$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return UricAcidDao_Impl.lambda$getByUserId$7(userId, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    static /* synthetic */ List lambda$getByUserId$7(String str, SQLiteConnection sQLiteConnection) {
        ArrayList arrayList;
        Long lValueOf;
        String text;
        int i2;
        int i3;
        boolean z;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("\n        SELECT * FROM uric_acid_data \n        WHERE user_id = ? \n        OR user_id = \"\"\n        OR user_id IS NULL\n        ORDER BY start_timestamp DESC\n    ");
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
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "uric_acid");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "measure_mode");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_uploaded");
            ArrayList arrayList2 = new ArrayList();
            while (sQLiteStatementPrepare.step()) {
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow)) {
                    arrayList = arrayList2;
                    lValueOf = null;
                } else {
                    arrayList = arrayList2;
                    lValueOf = Long.valueOf(sQLiteStatementPrepare.getLong(columnIndexOrThrow));
                }
                int i4 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
                long j2 = sQLiteStatementPrepare.getLong(columnIndexOrThrow3);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow4)) {
                    i2 = columnIndexOrThrow2;
                    text = null;
                } else {
                    text = sQLiteStatementPrepare.getText(columnIndexOrThrow4);
                    i2 = columnIndexOrThrow2;
                }
                int i5 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5);
                int i6 = columnIndexOrThrow3;
                int i7 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                String text2 = sQLiteStatementPrepare.isNull(columnIndexOrThrow7) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow7);
                String text3 = sQLiteStatementPrepare.isNull(columnIndexOrThrow8) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow8);
                String text4 = sQLiteStatementPrepare.isNull(columnIndexOrThrow9) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow9);
                String text5 = sQLiteStatementPrepare.isNull(columnIndexOrThrow10) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow10);
                int i8 = columnIndexOrThrow4;
                if (((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11)) != 0) {
                    i3 = columnIndexOrThrow5;
                    z = true;
                } else {
                    i3 = columnIndexOrThrow5;
                    z = false;
                }
                UricAcid uricAcid = new UricAcid(lValueOf, i4, j2, text, i5, i7, text2, text3, text4, text5, z, ((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12)) != 0);
                ArrayList arrayList3 = arrayList;
                arrayList3.add(uricAcid);
                arrayList2 = arrayList3;
                columnIndexOrThrow5 = i3;
                columnIndexOrThrow2 = i2;
                columnIndexOrThrow4 = i8;
                columnIndexOrThrow3 = i6;
            }
            return arrayList2;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.UricAcidDao
    public Object queryAll(final String userId, final Continuation<? super List<UricAcid>> arg1) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.UricAcidDao_Impl$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return UricAcidDao_Impl.lambda$queryAll$8(userId, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    static /* synthetic */ List lambda$queryAll$8(String str, SQLiteConnection sQLiteConnection) {
        ArrayList arrayList;
        Long lValueOf;
        String text;
        int i2;
        int i3;
        boolean z;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("\n        SELECT * FROM uric_acid_data \n        WHERE (user_id = ? \n        OR user_id = \"\"\n        OR user_id IS NULL)\n        ORDER BY start_timestamp DESC\n    ");
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
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "uric_acid");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "measure_mode");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_uploaded");
            ArrayList arrayList2 = new ArrayList();
            while (sQLiteStatementPrepare.step()) {
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow)) {
                    arrayList = arrayList2;
                    lValueOf = null;
                } else {
                    arrayList = arrayList2;
                    lValueOf = Long.valueOf(sQLiteStatementPrepare.getLong(columnIndexOrThrow));
                }
                int i4 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
                long j2 = sQLiteStatementPrepare.getLong(columnIndexOrThrow3);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow4)) {
                    i2 = columnIndexOrThrow2;
                    text = null;
                } else {
                    text = sQLiteStatementPrepare.getText(columnIndexOrThrow4);
                    i2 = columnIndexOrThrow2;
                }
                int i5 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5);
                int i6 = columnIndexOrThrow3;
                int i7 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                String text2 = sQLiteStatementPrepare.isNull(columnIndexOrThrow7) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow7);
                String text3 = sQLiteStatementPrepare.isNull(columnIndexOrThrow8) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow8);
                String text4 = sQLiteStatementPrepare.isNull(columnIndexOrThrow9) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow9);
                String text5 = sQLiteStatementPrepare.isNull(columnIndexOrThrow10) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow10);
                int i8 = columnIndexOrThrow4;
                if (((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11)) != 0) {
                    i3 = columnIndexOrThrow5;
                    z = true;
                } else {
                    i3 = columnIndexOrThrow5;
                    z = false;
                }
                UricAcid uricAcid = new UricAcid(lValueOf, i4, j2, text, i5, i7, text2, text3, text4, text5, z, ((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12)) != 0);
                ArrayList arrayList3 = arrayList;
                arrayList3.add(uricAcid);
                arrayList2 = arrayList3;
                columnIndexOrThrow5 = i3;
                columnIndexOrThrow2 = i2;
                columnIndexOrThrow4 = i8;
                columnIndexOrThrow3 = i6;
            }
            return arrayList2;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.UricAcidDao
    public Object querySyncedWithYearToDay(final String yearToDay, final String userId, final boolean synced, final Continuation<? super List<UricAcid>> arg3) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.UricAcidDao_Impl$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return UricAcidDao_Impl.lambda$querySyncedWithYearToDay$9(synced, yearToDay, userId, (SQLiteConnection) obj);
            }
        }, arg3);
    }

    static /* synthetic */ List lambda$querySyncedWithYearToDay$9(boolean z, String str, String str2, SQLiteConnection sQLiteConnection) {
        int i2;
        int i3;
        String text;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("\n        SELECT * FROM uric_acid_data \n        WHERE is_uploaded = ?\n        AND time_year_to_day = ?\n        AND (user_id = ? \n        OR user_id = \"\"\n        OR user_id IS NULL)\n        ORDER BY start_timestamp DESC\n    ");
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
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "uric_acid");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "measure_mode");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_uploaded");
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
                arrayList.add(new UricAcid(lValueOf, i4, j2, text, (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5), (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6), sQLiteStatementPrepare.isNull(columnIndexOrThrow7) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow7), sQLiteStatementPrepare.isNull(columnIndexOrThrow8) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow8), sQLiteStatementPrepare.isNull(columnIndexOrThrow9) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow9), sQLiteStatementPrepare.isNull(columnIndexOrThrow10) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow10), ((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11)) != 0, ((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12)) != 0));
                columnIndexOrThrow = i2;
                columnIndexOrThrow2 = i3;
                columnIndexOrThrow3 = i5;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.UricAcidDao
    public Object queryByYearToDay(final String yearToDay, final String userId, final Continuation<? super List<UricAcid>> arg2) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.UricAcidDao_Impl$$ExternalSyntheticLambda12
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return UricAcidDao_Impl.lambda$queryByYearToDay$10(yearToDay, userId, (SQLiteConnection) obj);
            }
        }, arg2);
    }

    static /* synthetic */ List lambda$queryByYearToDay$10(String str, String str2, SQLiteConnection sQLiteConnection) {
        int i2;
        Long lValueOf;
        int i3;
        int i4;
        String text;
        int i5;
        boolean z;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("\n        SELECT * FROM uric_acid_data \n        WHERE time_year_to_day = ?\n        AND (user_id = ? \n        OR user_id = \"\"\n        OR user_id IS NULL)\n        ORDER BY start_timestamp DESC\n    ");
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
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "uric_acid");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "measure_mode");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_uploaded");
            ArrayList arrayList = new ArrayList();
            while (sQLiteStatementPrepare.step()) {
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow)) {
                    i2 = columnIndexOrThrow3;
                    lValueOf = null;
                } else {
                    i2 = columnIndexOrThrow3;
                    lValueOf = Long.valueOf(sQLiteStatementPrepare.getLong(columnIndexOrThrow));
                }
                int i6 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
                int i7 = i2;
                long j2 = sQLiteStatementPrepare.getLong(i7);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow4)) {
                    i3 = columnIndexOrThrow;
                    i4 = columnIndexOrThrow2;
                    text = null;
                } else {
                    i3 = columnIndexOrThrow;
                    i4 = columnIndexOrThrow2;
                    text = sQLiteStatementPrepare.getText(columnIndexOrThrow4);
                }
                int i8 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5);
                int i9 = columnIndexOrThrow4;
                int i10 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                String text2 = sQLiteStatementPrepare.isNull(columnIndexOrThrow7) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow7);
                String text3 = sQLiteStatementPrepare.isNull(columnIndexOrThrow8) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow8);
                String text4 = sQLiteStatementPrepare.isNull(columnIndexOrThrow9) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow9);
                String text5 = sQLiteStatementPrepare.isNull(columnIndexOrThrow10) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow10);
                int i11 = columnIndexOrThrow5;
                if (((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11)) != 0) {
                    i5 = columnIndexOrThrow6;
                    z = true;
                } else {
                    i5 = columnIndexOrThrow6;
                    z = false;
                }
                arrayList.add(new UricAcid(lValueOf, i6, j2, text, i8, i10, text2, text3, text4, text5, z, ((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12)) != 0));
                columnIndexOrThrow = i3;
                columnIndexOrThrow6 = i5;
                columnIndexOrThrow3 = i7;
                columnIndexOrThrow5 = i11;
                columnIndexOrThrow4 = i9;
                columnIndexOrThrow2 = i4;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.UricAcidDao
    public Object querySinceYearToDay(final String yearToDay, final String userId, final Continuation<? super List<UricAcid>> arg2) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.UricAcidDao_Impl$$ExternalSyntheticLambda14
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return UricAcidDao_Impl.lambda$querySinceYearToDay$11(yearToDay, userId, (SQLiteConnection) obj);
            }
        }, arg2);
    }

    static /* synthetic */ List lambda$querySinceYearToDay$11(String str, String str2, SQLiteConnection sQLiteConnection) {
        int i2;
        Long lValueOf;
        int i3;
        int i4;
        String text;
        int i5;
        boolean z;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("\n        SELECT * FROM uric_acid_data \n        WHERE time_year_to_day >= ?\n        AND (user_id = ? OR user_id = \"\" OR user_id IS NULL)\n    ");
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
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "uric_acid");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "measure_mode");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_uploaded");
            ArrayList arrayList = new ArrayList();
            while (sQLiteStatementPrepare.step()) {
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow)) {
                    i2 = columnIndexOrThrow3;
                    lValueOf = null;
                } else {
                    i2 = columnIndexOrThrow3;
                    lValueOf = Long.valueOf(sQLiteStatementPrepare.getLong(columnIndexOrThrow));
                }
                int i6 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
                int i7 = i2;
                long j2 = sQLiteStatementPrepare.getLong(i7);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow4)) {
                    i3 = columnIndexOrThrow;
                    i4 = columnIndexOrThrow2;
                    text = null;
                } else {
                    i3 = columnIndexOrThrow;
                    i4 = columnIndexOrThrow2;
                    text = sQLiteStatementPrepare.getText(columnIndexOrThrow4);
                }
                int i8 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5);
                int i9 = columnIndexOrThrow4;
                int i10 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                String text2 = sQLiteStatementPrepare.isNull(columnIndexOrThrow7) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow7);
                String text3 = sQLiteStatementPrepare.isNull(columnIndexOrThrow8) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow8);
                String text4 = sQLiteStatementPrepare.isNull(columnIndexOrThrow9) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow9);
                String text5 = sQLiteStatementPrepare.isNull(columnIndexOrThrow10) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow10);
                int i11 = columnIndexOrThrow5;
                if (((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11)) != 0) {
                    i5 = columnIndexOrThrow6;
                    z = true;
                } else {
                    i5 = columnIndexOrThrow6;
                    z = false;
                }
                arrayList.add(new UricAcid(lValueOf, i6, j2, text, i8, i10, text2, text3, text4, text5, z, ((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12)) != 0));
                columnIndexOrThrow = i3;
                columnIndexOrThrow6 = i5;
                columnIndexOrThrow3 = i7;
                columnIndexOrThrow5 = i11;
                columnIndexOrThrow4 = i9;
                columnIndexOrThrow2 = i4;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.UricAcidDao
    public Object getDataInTimeRange(final long startTime, final long endTime, final String userName, final Continuation<? super List<UricAcid>> arg3) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.UricAcidDao_Impl$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return UricAcidDao_Impl.lambda$getDataInTimeRange$12(startTime, endTime, userName, (SQLiteConnection) obj);
            }
        }, arg3);
    }

    static /* synthetic */ List lambda$getDataInTimeRange$12(long j2, long j3, String str, SQLiteConnection sQLiteConnection) {
        ArrayList arrayList;
        Long lValueOf;
        int i2;
        String text;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("\n        SELECT * FROM uric_acid_data \n        WHERE start_timestamp BETWEEN ? AND ?\n        AND (user_id = ? OR user_id IS NULL OR user_id = '')\n    ");
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
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "uric_acid");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "measure_mode");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_uploaded");
            ArrayList arrayList2 = new ArrayList();
            while (sQLiteStatementPrepare.step()) {
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow)) {
                    arrayList = arrayList2;
                    lValueOf = null;
                } else {
                    arrayList = arrayList2;
                    lValueOf = Long.valueOf(sQLiteStatementPrepare.getLong(columnIndexOrThrow));
                }
                int i3 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
                long j4 = sQLiteStatementPrepare.getLong(columnIndexOrThrow3);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow4)) {
                    i2 = columnIndexOrThrow2;
                    text = null;
                } else {
                    i2 = columnIndexOrThrow2;
                    text = sQLiteStatementPrepare.getText(columnIndexOrThrow4);
                }
                int i4 = columnIndexOrThrow3;
                int i5 = columnIndexOrThrow4;
                int i6 = columnIndexOrThrow5;
                ArrayList arrayList3 = arrayList;
                arrayList3.add(new UricAcid(lValueOf, i3, j4, text, (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5), (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6), sQLiteStatementPrepare.isNull(columnIndexOrThrow7) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow7), sQLiteStatementPrepare.isNull(columnIndexOrThrow8) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow8), sQLiteStatementPrepare.isNull(columnIndexOrThrow9) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow9), sQLiteStatementPrepare.isNull(columnIndexOrThrow10) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow10), ((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11)) != 0, ((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12)) != 0));
                columnIndexOrThrow2 = i2;
                columnIndexOrThrow3 = i4;
                columnIndexOrThrow4 = i5;
                columnIndexOrThrow5 = i6;
                arrayList2 = arrayList3;
            }
            return arrayList2;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.UricAcidDao
    public Object markAsSynced(final List<Long> ids, final boolean synced, final Continuation<? super Integer> arg2) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE uric_acid_data SET is_uploaded = ? WHERE id IN (");
        StringUtil.appendPlaceholders(sb, ids.size());
        sb.append(")");
        final String string = sb.toString();
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.UricAcidDao_Impl$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return UricAcidDao_Impl.lambda$markAsSynced$13(string, synced, ids, (SQLiteConnection) obj);
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

    @Override // com.yucheng.smarthealthpro.database.room.dao.UricAcidDao
    public Object deleteById(final long id, final Continuation<? super Integer> arg1) {
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.UricAcidDao_Impl$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return UricAcidDao_Impl.lambda$deleteById$14(id, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    static /* synthetic */ Integer lambda$deleteById$14(long j2, SQLiteConnection sQLiteConnection) {
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("DELETE FROM uric_acid_data WHERE id = ?");
        try {
            sQLiteStatementPrepare.mo181bindLong(1, j2);
            sQLiteStatementPrepare.step();
            return Integer.valueOf(SQLiteConnectionUtil.getTotalChangedRows(sQLiteConnection));
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.UricAcidDao
    public Object deleteAll(final Continuation<? super Integer> arg0) {
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.UricAcidDao_Impl$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return UricAcidDao_Impl.lambda$deleteAll$15((SQLiteConnection) obj);
            }
        }, arg0);
    }

    static /* synthetic */ Integer lambda$deleteAll$15(SQLiteConnection sQLiteConnection) {
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("DELETE FROM uric_acid_data");
        try {
            sQLiteStatementPrepare.step();
            return Integer.valueOf(SQLiteConnectionUtil.getTotalChangedRows(sQLiteConnection));
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.UricAcidDao
    public Object deleteAllByUser(final String userId, final Continuation<? super Integer> arg1) {
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.UricAcidDao_Impl$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return UricAcidDao_Impl.lambda$deleteAllByUser$16(userId, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    static /* synthetic */ Integer lambda$deleteAllByUser$16(String str, SQLiteConnection sQLiteConnection) {
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("DELETE FROM uric_acid_data WHERE user_id = ?");
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
