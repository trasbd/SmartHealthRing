package com.yucheng.smarthealthpro.database.room.dao;

import androidx.constraintlayout.core.motion.utils.TypedValues;
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
import com.yucheng.smarthealthpro.database.room.bean.Physiotherapy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;

/* loaded from: classes4.dex */
public final class PhysiotherapyDao_Impl implements PhysiotherapyDao {
    private final RoomDatabase __db;
    private final EntityInsertAdapter<Physiotherapy> __insertAdapterOfPhysiotherapy = new EntityInsertAdapter<Physiotherapy>() { // from class: com.yucheng.smarthealthpro.database.room.dao.PhysiotherapyDao_Impl.1
        @Override // androidx.room.EntityInsertAdapter
        protected String createQuery() {
            return "INSERT OR REPLACE INTO `physiotherapy_data` (`id`,`query_id`,`start_timestamp`,`time_year_to_day`,`duration`,`type`,`start_type`,`power_level`,`duration_level`,`user_id`,`device_type`,`device_mac_address`,`data_group_id`,`is_uploaded`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // androidx.room.EntityInsertAdapter
        public void bind(SQLiteStatement sQLiteStatement, Physiotherapy physiotherapy) {
            if (physiotherapy.getId() == null) {
                sQLiteStatement.mo182bindNull(1);
            } else {
                sQLiteStatement.mo181bindLong(1, physiotherapy.getId().longValue());
            }
            sQLiteStatement.mo181bindLong(2, physiotherapy.getQueryID());
            sQLiteStatement.mo181bindLong(3, physiotherapy.getStartTimestamp());
            if (physiotherapy.getTimeYearToDay() == null) {
                sQLiteStatement.mo182bindNull(4);
            } else {
                sQLiteStatement.mo183bindText(4, physiotherapy.getTimeYearToDay());
            }
            sQLiteStatement.mo181bindLong(5, physiotherapy.getDuration());
            sQLiteStatement.mo181bindLong(6, physiotherapy.getType());
            sQLiteStatement.mo181bindLong(7, physiotherapy.getStartType());
            sQLiteStatement.mo181bindLong(8, physiotherapy.getPowerLevel());
            sQLiteStatement.mo181bindLong(9, physiotherapy.getDurationLevel());
            if (physiotherapy.getUserId() == null) {
                sQLiteStatement.mo182bindNull(10);
            } else {
                sQLiteStatement.mo183bindText(10, physiotherapy.getUserId());
            }
            if (physiotherapy.getDeviceType() == null) {
                sQLiteStatement.mo182bindNull(11);
            } else {
                sQLiteStatement.mo183bindText(11, physiotherapy.getDeviceType());
            }
            if (physiotherapy.getDeviceMacAddress() == null) {
                sQLiteStatement.mo182bindNull(12);
            } else {
                sQLiteStatement.mo183bindText(12, physiotherapy.getDeviceMacAddress());
            }
            if (physiotherapy.getDataGroupId() == null) {
                sQLiteStatement.mo182bindNull(13);
            } else {
                sQLiteStatement.mo183bindText(13, physiotherapy.getDataGroupId());
            }
            sQLiteStatement.mo181bindLong(14, physiotherapy.isUploaded() ? 1L : 0L);
        }
    };
    private final EntityDeleteOrUpdateAdapter<Physiotherapy> __updateAdapterOfPhysiotherapy = new EntityDeleteOrUpdateAdapter<Physiotherapy>() { // from class: com.yucheng.smarthealthpro.database.room.dao.PhysiotherapyDao_Impl.2
        @Override // androidx.room.EntityDeleteOrUpdateAdapter
        protected String createQuery() {
            return "UPDATE OR ABORT `physiotherapy_data` SET `id` = ?,`query_id` = ?,`start_timestamp` = ?,`time_year_to_day` = ?,`duration` = ?,`type` = ?,`start_type` = ?,`power_level` = ?,`duration_level` = ?,`user_id` = ?,`device_type` = ?,`device_mac_address` = ?,`data_group_id` = ?,`is_uploaded` = ? WHERE `id` = ?";
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // androidx.room.EntityDeleteOrUpdateAdapter
        public void bind(SQLiteStatement sQLiteStatement, Physiotherapy physiotherapy) {
            if (physiotherapy.getId() == null) {
                sQLiteStatement.mo182bindNull(1);
            } else {
                sQLiteStatement.mo181bindLong(1, physiotherapy.getId().longValue());
            }
            sQLiteStatement.mo181bindLong(2, physiotherapy.getQueryID());
            sQLiteStatement.mo181bindLong(3, physiotherapy.getStartTimestamp());
            if (physiotherapy.getTimeYearToDay() == null) {
                sQLiteStatement.mo182bindNull(4);
            } else {
                sQLiteStatement.mo183bindText(4, physiotherapy.getTimeYearToDay());
            }
            sQLiteStatement.mo181bindLong(5, physiotherapy.getDuration());
            sQLiteStatement.mo181bindLong(6, physiotherapy.getType());
            sQLiteStatement.mo181bindLong(7, physiotherapy.getStartType());
            sQLiteStatement.mo181bindLong(8, physiotherapy.getPowerLevel());
            sQLiteStatement.mo181bindLong(9, physiotherapy.getDurationLevel());
            if (physiotherapy.getUserId() == null) {
                sQLiteStatement.mo182bindNull(10);
            } else {
                sQLiteStatement.mo183bindText(10, physiotherapy.getUserId());
            }
            if (physiotherapy.getDeviceType() == null) {
                sQLiteStatement.mo182bindNull(11);
            } else {
                sQLiteStatement.mo183bindText(11, physiotherapy.getDeviceType());
            }
            if (physiotherapy.getDeviceMacAddress() == null) {
                sQLiteStatement.mo182bindNull(12);
            } else {
                sQLiteStatement.mo183bindText(12, physiotherapy.getDeviceMacAddress());
            }
            if (physiotherapy.getDataGroupId() == null) {
                sQLiteStatement.mo182bindNull(13);
            } else {
                sQLiteStatement.mo183bindText(13, physiotherapy.getDataGroupId());
            }
            sQLiteStatement.mo181bindLong(14, physiotherapy.isUploaded() ? 1L : 0L);
            if (physiotherapy.getId() == null) {
                sQLiteStatement.mo182bindNull(15);
            } else {
                sQLiteStatement.mo181bindLong(15, physiotherapy.getId().longValue());
            }
        }
    };
    private final EntityDeleteOrUpdateAdapter<DataGroupIdUpdate> __updateAdapterOfDataGroupIdUpdateAsPhysiotherapy = new EntityDeleteOrUpdateAdapter<DataGroupIdUpdate>() { // from class: com.yucheng.smarthealthpro.database.room.dao.PhysiotherapyDao_Impl.3
        @Override // androidx.room.EntityDeleteOrUpdateAdapter
        protected String createQuery() {
            return "UPDATE OR ABORT `physiotherapy_data` SET `id` = ?,`data_group_id` = ? WHERE `id` = ?";
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

    public PhysiotherapyDao_Impl(final RoomDatabase __db) {
        this.__db = __db;
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.PhysiotherapyDao
    public Object insert(final Physiotherapy metric, final Continuation<? super Long> arg1) {
        metric.getClass();
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.PhysiotherapyDao_Impl$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f$0.lambda$insert$0(metric, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Long lambda$insert$0(Physiotherapy physiotherapy, SQLiteConnection sQLiteConnection) {
        return Long.valueOf(this.__insertAdapterOfPhysiotherapy.insertAndReturnId(sQLiteConnection, physiotherapy));
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.PhysiotherapyDao
    public Object insertAll(final List<Physiotherapy> metrics, final Continuation<? super List<Long>> arg1) {
        metrics.getClass();
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.PhysiotherapyDao_Impl$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f$0.lambda$insertAll$1(metrics, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ List lambda$insertAll$1(List list, SQLiteConnection sQLiteConnection) {
        return this.__insertAdapterOfPhysiotherapy.insertAndReturnIdsList(sQLiteConnection, list);
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.PhysiotherapyDao
    public Object update(final Physiotherapy metric, final Continuation<? super Unit> arg1) {
        metric.getClass();
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.PhysiotherapyDao_Impl$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f$0.lambda$update$2(metric, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Unit lambda$update$2(Physiotherapy physiotherapy, SQLiteConnection sQLiteConnection) throws Exception {
        this.__updateAdapterOfPhysiotherapy.handle(sQLiteConnection, physiotherapy);
        return Unit.INSTANCE;
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.PhysiotherapyDao
    public Object updateDataGroupIds(final List<DataGroupIdUpdate> updates, final Continuation<? super Unit> arg1) {
        updates.getClass();
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.PhysiotherapyDao_Impl$$ExternalSyntheticLambda13
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f$0.lambda$updateDataGroupIds$3(updates, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Unit lambda$updateDataGroupIds$3(List list, SQLiteConnection sQLiteConnection) throws Exception {
        this.__updateAdapterOfDataGroupIdUpdateAsPhysiotherapy.handleMultiple(sQLiteConnection, list);
        return Unit.INSTANCE;
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.PhysiotherapyDao
    public Object getById(final long id, final Continuation<? super Physiotherapy> arg1) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.PhysiotherapyDao_Impl$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PhysiotherapyDao_Impl.lambda$getById$4(id, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    static /* synthetic */ Physiotherapy lambda$getById$4(long j2, SQLiteConnection sQLiteConnection) {
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("SELECT * FROM physiotherapy_data WHERE id = ?");
        boolean z = true;
        try {
            sQLiteStatementPrepare.mo181bindLong(1, j2);
            int columnIndexOrThrow = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "id");
            int columnIndexOrThrow2 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "query_id");
            int columnIndexOrThrow3 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "start_timestamp");
            int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "time_year_to_day");
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, TypedValues.TransitionType.S_DURATION);
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "type");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "start_type");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "power_level");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "duration_level");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            Physiotherapy physiotherapy = null;
            if (sQLiteStatementPrepare.step()) {
                Long lValueOf = sQLiteStatementPrepare.isNull(columnIndexOrThrow) ? null : Long.valueOf(sQLiteStatementPrepare.getLong(columnIndexOrThrow));
                int i2 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
                long j3 = sQLiteStatementPrepare.getLong(columnIndexOrThrow3);
                String text = sQLiteStatementPrepare.isNull(columnIndexOrThrow4) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow4);
                int i3 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5);
                int i4 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                int i5 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7);
                int i6 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow8);
                int i7 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow9);
                String text2 = sQLiteStatementPrepare.isNull(columnIndexOrThrow10) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow10);
                String text3 = sQLiteStatementPrepare.isNull(columnIndexOrThrow11) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow11);
                String text4 = sQLiteStatementPrepare.isNull(columnIndexOrThrow12) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow12);
                String text5 = sQLiteStatementPrepare.isNull(columnIndexOrThrow13) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow13);
                if (((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow14)) == 0) {
                    z = false;
                }
                physiotherapy = new Physiotherapy(lValueOf, i2, j3, text, i3, i4, i5, i6, i7, text2, text3, text4, text5, z);
            }
            return physiotherapy;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.PhysiotherapyDao
    public Object getByUser(final String userId, final Continuation<? super List<Physiotherapy>> arg1) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.PhysiotherapyDao_Impl$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PhysiotherapyDao_Impl.lambda$getByUser$5(userId, (SQLiteConnection) obj);
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
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("SELECT * FROM physiotherapy_data WHERE user_id = ? ORDER BY start_timestamp DESC");
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
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, TypedValues.TransitionType.S_DURATION);
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "type");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "start_type");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "power_level");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "duration_level");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            ArrayList arrayList = new ArrayList();
            while (sQLiteStatementPrepare.step()) {
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow)) {
                    i2 = columnIndexOrThrow13;
                    i3 = columnIndexOrThrow14;
                    lValueOf = null;
                } else {
                    i2 = columnIndexOrThrow13;
                    lValueOf = Long.valueOf(sQLiteStatementPrepare.getLong(columnIndexOrThrow));
                    i3 = columnIndexOrThrow14;
                }
                int i7 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
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
                int i8 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5);
                int i9 = columnIndexOrThrow4;
                int i10 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                int i11 = columnIndexOrThrow5;
                int i12 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7);
                int i13 = columnIndexOrThrow6;
                int i14 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow8);
                int i15 = columnIndexOrThrow7;
                int i16 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow9);
                String text3 = sQLiteStatementPrepare.isNull(columnIndexOrThrow10) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow10);
                String text4 = sQLiteStatementPrepare.isNull(columnIndexOrThrow11) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow11);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow12)) {
                    i6 = i2;
                    text2 = null;
                } else {
                    text2 = sQLiteStatementPrepare.getText(columnIndexOrThrow12);
                    i6 = i2;
                }
                int i17 = columnIndexOrThrow;
                int i18 = i3;
                int i19 = i6;
                int i20 = columnIndexOrThrow8;
                arrayList.add(new Physiotherapy(lValueOf, i7, j2, text, i8, i10, i12, i14, i16, text3, text4, text2, sQLiteStatementPrepare.isNull(i6) ? null : sQLiteStatementPrepare.getText(i6), ((int) sQLiteStatementPrepare.getLong(i18)) != 0));
                columnIndexOrThrow2 = i5;
                columnIndexOrThrow8 = i20;
                columnIndexOrThrow13 = i19;
                columnIndexOrThrow3 = i4;
                columnIndexOrThrow4 = i9;
                columnIndexOrThrow5 = i11;
                columnIndexOrThrow6 = i13;
                columnIndexOrThrow7 = i15;
                columnIndexOrThrow14 = i18;
                columnIndexOrThrow = i17;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.PhysiotherapyDao
    public Object getByStartTimestamp(final long startTimestamp, final Continuation<? super List<Physiotherapy>> arg1) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.PhysiotherapyDao_Impl$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PhysiotherapyDao_Impl.lambda$getByStartTimestamp$6(startTimestamp, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    static /* synthetic */ List lambda$getByStartTimestamp$6(long j2, SQLiteConnection sQLiteConnection) {
        int i2;
        int i3;
        Long lValueOf;
        int i4;
        String text;
        int i5;
        String text2;
        int i6;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("SELECT * FROM physiotherapy_data WHERE start_timestamp = ?");
        try {
            sQLiteStatementPrepare.mo181bindLong(1, j2);
            int columnIndexOrThrow = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "id");
            int columnIndexOrThrow2 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "query_id");
            int columnIndexOrThrow3 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "start_timestamp");
            int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "time_year_to_day");
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, TypedValues.TransitionType.S_DURATION);
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "type");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "start_type");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "power_level");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "duration_level");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            ArrayList arrayList = new ArrayList();
            while (sQLiteStatementPrepare.step()) {
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow)) {
                    i2 = columnIndexOrThrow13;
                    i3 = columnIndexOrThrow14;
                    lValueOf = null;
                } else {
                    i2 = columnIndexOrThrow13;
                    i3 = columnIndexOrThrow14;
                    lValueOf = Long.valueOf(sQLiteStatementPrepare.getLong(columnIndexOrThrow));
                }
                int i7 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
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
                int i8 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5);
                int i9 = columnIndexOrThrow3;
                int i10 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                int i11 = columnIndexOrThrow4;
                int i12 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7);
                int i13 = columnIndexOrThrow5;
                int i14 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow8);
                int i15 = columnIndexOrThrow6;
                int i16 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow9);
                String text3 = sQLiteStatementPrepare.isNull(columnIndexOrThrow10) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow10);
                String text4 = sQLiteStatementPrepare.isNull(columnIndexOrThrow11) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow11);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow12)) {
                    i6 = i2;
                    text2 = null;
                } else {
                    text2 = sQLiteStatementPrepare.getText(columnIndexOrThrow12);
                    i6 = i2;
                }
                int i17 = i6;
                String text5 = sQLiteStatementPrepare.isNull(i6) ? null : sQLiteStatementPrepare.getText(i6);
                int i18 = i3;
                int i19 = columnIndexOrThrow7;
                int i20 = columnIndexOrThrow8;
                arrayList.add(new Physiotherapy(lValueOf, i7, j3, text, i8, i10, i12, i14, i16, text3, text4, text2, text5, ((int) sQLiteStatementPrepare.getLong(i18)) != 0));
                columnIndexOrThrow13 = i17;
                columnIndexOrThrow7 = i19;
                columnIndexOrThrow = i5;
                columnIndexOrThrow2 = i4;
                columnIndexOrThrow8 = i20;
                columnIndexOrThrow3 = i9;
                columnIndexOrThrow4 = i11;
                columnIndexOrThrow5 = i13;
                columnIndexOrThrow14 = i18;
                columnIndexOrThrow6 = i15;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.PhysiotherapyDao
    public Object queryByYearToDay(final String yearToDay, final String userId, final Continuation<? super List<Physiotherapy>> arg2) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.PhysiotherapyDao_Impl$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PhysiotherapyDao_Impl.lambda$queryByYearToDay$7(yearToDay, userId, (SQLiteConnection) obj);
            }
        }, arg2);
    }

    static /* synthetic */ List lambda$queryByYearToDay$7(String str, String str2, SQLiteConnection sQLiteConnection) {
        int i2;
        int i3;
        Long lValueOf;
        int i4;
        String text;
        int i5;
        String text2;
        int i6;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("\n        SELECT * FROM physiotherapy_data \n        WHERE time_year_to_day = ?\n        AND (user_id = ? \n        OR user_id = \"\"\n        OR user_id IS NULL)\n        ORDER BY start_timestamp DESC\n    ");
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
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, TypedValues.TransitionType.S_DURATION);
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "type");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "start_type");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "power_level");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "duration_level");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            ArrayList arrayList = new ArrayList();
            while (sQLiteStatementPrepare.step()) {
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow)) {
                    i2 = columnIndexOrThrow13;
                    i3 = columnIndexOrThrow14;
                    lValueOf = null;
                } else {
                    i2 = columnIndexOrThrow13;
                    i3 = columnIndexOrThrow14;
                    lValueOf = Long.valueOf(sQLiteStatementPrepare.getLong(columnIndexOrThrow));
                }
                int i7 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
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
                int i8 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5);
                int i9 = columnIndexOrThrow3;
                int i10 = columnIndexOrThrow4;
                int i11 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                int i12 = columnIndexOrThrow5;
                int i13 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7);
                int i14 = columnIndexOrThrow6;
                int i15 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow8);
                int i16 = columnIndexOrThrow7;
                int i17 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow9);
                String text3 = sQLiteStatementPrepare.isNull(columnIndexOrThrow10) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow10);
                String text4 = sQLiteStatementPrepare.isNull(columnIndexOrThrow11) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow11);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow12)) {
                    i6 = i2;
                    text2 = null;
                } else {
                    text2 = sQLiteStatementPrepare.getText(columnIndexOrThrow12);
                    i6 = i2;
                }
                int i18 = i3;
                int i19 = i6;
                int i20 = columnIndexOrThrow8;
                arrayList.add(new Physiotherapy(lValueOf, i7, j2, text, i8, i11, i13, i15, i17, text3, text4, text2, sQLiteStatementPrepare.isNull(i6) ? null : sQLiteStatementPrepare.getText(i6), ((int) sQLiteStatementPrepare.getLong(i18)) != 0));
                columnIndexOrThrow3 = i9;
                columnIndexOrThrow8 = i20;
                columnIndexOrThrow = i5;
                columnIndexOrThrow13 = i19;
                columnIndexOrThrow4 = i10;
                columnIndexOrThrow5 = i12;
                columnIndexOrThrow6 = i14;
                columnIndexOrThrow7 = i16;
                columnIndexOrThrow14 = i18;
                columnIndexOrThrow2 = i4;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.PhysiotherapyDao
    public Object querySinceYearToDay(final String yearToDay, final String userId, final Continuation<? super List<Physiotherapy>> arg2) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.PhysiotherapyDao_Impl$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PhysiotherapyDao_Impl.lambda$querySinceYearToDay$8(yearToDay, userId, (SQLiteConnection) obj);
            }
        }, arg2);
    }

    static /* synthetic */ List lambda$querySinceYearToDay$8(String str, String str2, SQLiteConnection sQLiteConnection) {
        int i2;
        int i3;
        Long lValueOf;
        int i4;
        String text;
        int i5;
        String text2;
        int i6;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("\n        SELECT * FROM physiotherapy_data \n        WHERE time_year_to_day >= ?\n        AND (user_id = ? OR user_id = \"\" OR user_id IS NULL)\n    ");
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
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, TypedValues.TransitionType.S_DURATION);
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "type");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "start_type");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "power_level");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "duration_level");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            ArrayList arrayList = new ArrayList();
            while (sQLiteStatementPrepare.step()) {
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow)) {
                    i2 = columnIndexOrThrow13;
                    i3 = columnIndexOrThrow14;
                    lValueOf = null;
                } else {
                    i2 = columnIndexOrThrow13;
                    i3 = columnIndexOrThrow14;
                    lValueOf = Long.valueOf(sQLiteStatementPrepare.getLong(columnIndexOrThrow));
                }
                int i7 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
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
                int i8 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5);
                int i9 = columnIndexOrThrow3;
                int i10 = columnIndexOrThrow4;
                int i11 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                int i12 = columnIndexOrThrow5;
                int i13 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7);
                int i14 = columnIndexOrThrow6;
                int i15 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow8);
                int i16 = columnIndexOrThrow7;
                int i17 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow9);
                String text3 = sQLiteStatementPrepare.isNull(columnIndexOrThrow10) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow10);
                String text4 = sQLiteStatementPrepare.isNull(columnIndexOrThrow11) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow11);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow12)) {
                    i6 = i2;
                    text2 = null;
                } else {
                    text2 = sQLiteStatementPrepare.getText(columnIndexOrThrow12);
                    i6 = i2;
                }
                int i18 = i3;
                int i19 = i6;
                int i20 = columnIndexOrThrow8;
                arrayList.add(new Physiotherapy(lValueOf, i7, j2, text, i8, i11, i13, i15, i17, text3, text4, text2, sQLiteStatementPrepare.isNull(i6) ? null : sQLiteStatementPrepare.getText(i6), ((int) sQLiteStatementPrepare.getLong(i18)) != 0));
                columnIndexOrThrow3 = i9;
                columnIndexOrThrow8 = i20;
                columnIndexOrThrow = i5;
                columnIndexOrThrow13 = i19;
                columnIndexOrThrow4 = i10;
                columnIndexOrThrow5 = i12;
                columnIndexOrThrow6 = i14;
                columnIndexOrThrow7 = i16;
                columnIndexOrThrow14 = i18;
                columnIndexOrThrow2 = i4;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.PhysiotherapyDao
    public Object getByUserId(final String userId, final Continuation<? super List<Physiotherapy>> arg1) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.PhysiotherapyDao_Impl$$ExternalSyntheticLambda15
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PhysiotherapyDao_Impl.lambda$getByUserId$9(userId, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    static /* synthetic */ List lambda$getByUserId$9(String str, SQLiteConnection sQLiteConnection) {
        int i2;
        Long lValueOf;
        int i3;
        int i4;
        String text;
        int i5;
        String text2;
        int i6;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("\n        SELECT * FROM physiotherapy_data \n        WHERE user_id = ? \n        OR user_id = \"\"\n        OR user_id IS NULL\n        ORDER BY start_timestamp DESC\n    ");
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
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, TypedValues.TransitionType.S_DURATION);
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "type");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "start_type");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "power_level");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "duration_level");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            ArrayList arrayList = new ArrayList();
            while (sQLiteStatementPrepare.step()) {
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow)) {
                    i2 = columnIndexOrThrow13;
                    i3 = columnIndexOrThrow14;
                    lValueOf = null;
                } else {
                    i2 = columnIndexOrThrow13;
                    lValueOf = Long.valueOf(sQLiteStatementPrepare.getLong(columnIndexOrThrow));
                    i3 = columnIndexOrThrow14;
                }
                int i7 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
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
                int i8 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5);
                int i9 = columnIndexOrThrow4;
                int i10 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                int i11 = columnIndexOrThrow5;
                int i12 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7);
                int i13 = columnIndexOrThrow6;
                int i14 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow8);
                int i15 = columnIndexOrThrow7;
                int i16 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow9);
                String text3 = sQLiteStatementPrepare.isNull(columnIndexOrThrow10) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow10);
                String text4 = sQLiteStatementPrepare.isNull(columnIndexOrThrow11) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow11);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow12)) {
                    i6 = i2;
                    text2 = null;
                } else {
                    text2 = sQLiteStatementPrepare.getText(columnIndexOrThrow12);
                    i6 = i2;
                }
                int i17 = columnIndexOrThrow;
                int i18 = i3;
                int i19 = i6;
                int i20 = columnIndexOrThrow8;
                arrayList.add(new Physiotherapy(lValueOf, i7, j2, text, i8, i10, i12, i14, i16, text3, text4, text2, sQLiteStatementPrepare.isNull(i6) ? null : sQLiteStatementPrepare.getText(i6), ((int) sQLiteStatementPrepare.getLong(i18)) != 0));
                columnIndexOrThrow2 = i5;
                columnIndexOrThrow8 = i20;
                columnIndexOrThrow13 = i19;
                columnIndexOrThrow3 = i4;
                columnIndexOrThrow4 = i9;
                columnIndexOrThrow5 = i11;
                columnIndexOrThrow6 = i13;
                columnIndexOrThrow7 = i15;
                columnIndexOrThrow14 = i18;
                columnIndexOrThrow = i17;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.PhysiotherapyDao
    public Object queryAll(final String userId, final Continuation<? super List<Physiotherapy>> arg1) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.PhysiotherapyDao_Impl$$ExternalSyntheticLambda16
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PhysiotherapyDao_Impl.lambda$queryAll$10(userId, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    static /* synthetic */ List lambda$queryAll$10(String str, SQLiteConnection sQLiteConnection) {
        int i2;
        Long lValueOf;
        int i3;
        int i4;
        String text;
        int i5;
        String text2;
        int i6;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("\n        SELECT * FROM physiotherapy_data \n        WHERE (user_id = ? \n        OR user_id = \"\"\n        OR user_id IS NULL)\n        ORDER BY start_timestamp DESC\n    ");
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
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, TypedValues.TransitionType.S_DURATION);
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "type");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "start_type");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "power_level");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "duration_level");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            ArrayList arrayList = new ArrayList();
            while (sQLiteStatementPrepare.step()) {
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow)) {
                    i2 = columnIndexOrThrow13;
                    i3 = columnIndexOrThrow14;
                    lValueOf = null;
                } else {
                    i2 = columnIndexOrThrow13;
                    lValueOf = Long.valueOf(sQLiteStatementPrepare.getLong(columnIndexOrThrow));
                    i3 = columnIndexOrThrow14;
                }
                int i7 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
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
                int i8 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5);
                int i9 = columnIndexOrThrow4;
                int i10 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                int i11 = columnIndexOrThrow5;
                int i12 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7);
                int i13 = columnIndexOrThrow6;
                int i14 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow8);
                int i15 = columnIndexOrThrow7;
                int i16 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow9);
                String text3 = sQLiteStatementPrepare.isNull(columnIndexOrThrow10) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow10);
                String text4 = sQLiteStatementPrepare.isNull(columnIndexOrThrow11) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow11);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow12)) {
                    i6 = i2;
                    text2 = null;
                } else {
                    text2 = sQLiteStatementPrepare.getText(columnIndexOrThrow12);
                    i6 = i2;
                }
                int i17 = columnIndexOrThrow;
                int i18 = i3;
                int i19 = i6;
                int i20 = columnIndexOrThrow8;
                arrayList.add(new Physiotherapy(lValueOf, i7, j2, text, i8, i10, i12, i14, i16, text3, text4, text2, sQLiteStatementPrepare.isNull(i6) ? null : sQLiteStatementPrepare.getText(i6), ((int) sQLiteStatementPrepare.getLong(i18)) != 0));
                columnIndexOrThrow2 = i5;
                columnIndexOrThrow8 = i20;
                columnIndexOrThrow13 = i19;
                columnIndexOrThrow3 = i4;
                columnIndexOrThrow4 = i9;
                columnIndexOrThrow5 = i11;
                columnIndexOrThrow6 = i13;
                columnIndexOrThrow7 = i15;
                columnIndexOrThrow14 = i18;
                columnIndexOrThrow = i17;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.PhysiotherapyDao
    public Object querySyncedWithYearToDay(final String yearToDay, final String userId, final boolean synced, final Continuation<? super List<Physiotherapy>> arg3) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.PhysiotherapyDao_Impl$$ExternalSyntheticLambda14
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PhysiotherapyDao_Impl.lambda$querySyncedWithYearToDay$11(synced, yearToDay, userId, (SQLiteConnection) obj);
            }
        }, arg3);
    }

    static /* synthetic */ List lambda$querySyncedWithYearToDay$11(boolean z, String str, String str2, SQLiteConnection sQLiteConnection) {
        int i2;
        ArrayList arrayList;
        Long lValueOf;
        int i3;
        String text;
        int i4;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("\n        SELECT * FROM physiotherapy_data \n        WHERE is_uploaded = ?\n        AND time_year_to_day = ?\n        AND (user_id = ? \n        OR user_id = \"\"\n        OR user_id IS NULL)\n        ORDER BY start_timestamp DESC\n    ");
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
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, TypedValues.TransitionType.S_DURATION);
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "type");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "start_type");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "power_level");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "duration_level");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            ArrayList arrayList2 = new ArrayList();
            while (sQLiteStatementPrepare.step()) {
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow)) {
                    i2 = columnIndexOrThrow14;
                    arrayList = arrayList2;
                    lValueOf = null;
                } else {
                    i2 = columnIndexOrThrow14;
                    arrayList = arrayList2;
                    lValueOf = Long.valueOf(sQLiteStatementPrepare.getLong(columnIndexOrThrow));
                }
                int i5 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
                long j2 = sQLiteStatementPrepare.getLong(columnIndexOrThrow3);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow4)) {
                    i3 = columnIndexOrThrow;
                    i4 = columnIndexOrThrow2;
                    text = null;
                } else {
                    i3 = columnIndexOrThrow;
                    text = sQLiteStatementPrepare.getText(columnIndexOrThrow4);
                    i4 = columnIndexOrThrow2;
                }
                int i6 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5);
                int i7 = i4;
                int i8 = columnIndexOrThrow3;
                int i9 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                int i10 = columnIndexOrThrow4;
                int i11 = columnIndexOrThrow5;
                int i12 = columnIndexOrThrow6;
                int i13 = i2;
                int i14 = columnIndexOrThrow7;
                int i15 = columnIndexOrThrow8;
                Physiotherapy physiotherapy = new Physiotherapy(lValueOf, i5, j2, text, i6, i9, (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7), (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow8), (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow9), sQLiteStatementPrepare.isNull(columnIndexOrThrow10) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow10), sQLiteStatementPrepare.isNull(columnIndexOrThrow11) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow11), sQLiteStatementPrepare.isNull(columnIndexOrThrow12) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow12), sQLiteStatementPrepare.isNull(columnIndexOrThrow13) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow13), ((int) sQLiteStatementPrepare.getLong(i13)) != 0);
                ArrayList arrayList3 = arrayList;
                arrayList3.add(physiotherapy);
                columnIndexOrThrow7 = i14;
                arrayList2 = arrayList3;
                columnIndexOrThrow3 = i8;
                columnIndexOrThrow14 = i13;
                columnIndexOrThrow2 = i7;
                columnIndexOrThrow8 = i15;
                columnIndexOrThrow4 = i10;
                columnIndexOrThrow5 = i11;
                columnIndexOrThrow6 = i12;
                columnIndexOrThrow = i3;
            }
            return arrayList2;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.PhysiotherapyDao
    public Object getDataInTimeRange(final long startTime, final long endTime, final String userName, final Continuation<? super List<Physiotherapy>> arg3) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.PhysiotherapyDao_Impl$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PhysiotherapyDao_Impl.lambda$getDataInTimeRange$12(startTime, endTime, userName, (SQLiteConnection) obj);
            }
        }, arg3);
    }

    static /* synthetic */ List lambda$getDataInTimeRange$12(long j2, long j3, String str, SQLiteConnection sQLiteConnection) {
        int i2;
        int i3;
        Long lValueOf;
        int i4;
        int i5;
        String text;
        String text2;
        int i6;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("\n        SELECT * FROM physiotherapy_data \n        WHERE start_timestamp BETWEEN ? AND ?\n        AND (user_id = ? OR user_id IS NULL OR user_id = '')\n    ");
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
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, TypedValues.TransitionType.S_DURATION);
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "type");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "start_type");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "power_level");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "duration_level");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            ArrayList arrayList = new ArrayList();
            while (sQLiteStatementPrepare.step()) {
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow)) {
                    i2 = columnIndexOrThrow13;
                    i3 = columnIndexOrThrow14;
                    lValueOf = null;
                } else {
                    i2 = columnIndexOrThrow13;
                    i3 = columnIndexOrThrow14;
                    lValueOf = Long.valueOf(sQLiteStatementPrepare.getLong(columnIndexOrThrow));
                }
                int i7 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
                long j4 = sQLiteStatementPrepare.getLong(columnIndexOrThrow3);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow4)) {
                    i4 = columnIndexOrThrow2;
                    i5 = columnIndexOrThrow3;
                    text = null;
                } else {
                    i4 = columnIndexOrThrow2;
                    i5 = columnIndexOrThrow3;
                    text = sQLiteStatementPrepare.getText(columnIndexOrThrow4);
                }
                int i8 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5);
                int i9 = columnIndexOrThrow4;
                int i10 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                int i11 = columnIndexOrThrow5;
                int i12 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7);
                int i13 = columnIndexOrThrow6;
                int i14 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow8);
                int i15 = columnIndexOrThrow7;
                int i16 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow9);
                String text3 = sQLiteStatementPrepare.isNull(columnIndexOrThrow10) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow10);
                String text4 = sQLiteStatementPrepare.isNull(columnIndexOrThrow11) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow11);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow12)) {
                    i6 = i2;
                    text2 = null;
                } else {
                    text2 = sQLiteStatementPrepare.getText(columnIndexOrThrow12);
                    i6 = i2;
                }
                int i17 = columnIndexOrThrow;
                int i18 = i3;
                int i19 = i6;
                int i20 = columnIndexOrThrow8;
                arrayList.add(new Physiotherapy(lValueOf, i7, j4, text, i8, i10, i12, i14, i16, text3, text4, text2, sQLiteStatementPrepare.isNull(i6) ? null : sQLiteStatementPrepare.getText(i6), ((int) sQLiteStatementPrepare.getLong(i18)) != 0));
                columnIndexOrThrow8 = i20;
                columnIndexOrThrow2 = i4;
                columnIndexOrThrow3 = i5;
                columnIndexOrThrow4 = i9;
                columnIndexOrThrow5 = i11;
                columnIndexOrThrow6 = i13;
                columnIndexOrThrow13 = i19;
                columnIndexOrThrow7 = i15;
                columnIndexOrThrow14 = i18;
                columnIndexOrThrow = i17;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.PhysiotherapyDao
    public Object markAsSynced(final List<Long> ids, final boolean synced, final Continuation<? super Integer> arg2) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE physiotherapy_data SET is_uploaded = ? WHERE id IN (");
        StringUtil.appendPlaceholders(sb, ids.size());
        sb.append(")");
        final String string = sb.toString();
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.PhysiotherapyDao_Impl$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PhysiotherapyDao_Impl.lambda$markAsSynced$13(string, synced, ids, (SQLiteConnection) obj);
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

    @Override // com.yucheng.smarthealthpro.database.room.dao.PhysiotherapyDao
    public Object deleteById(final long id, final Continuation<? super Integer> arg1) {
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.PhysiotherapyDao_Impl$$ExternalSyntheticLambda12
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PhysiotherapyDao_Impl.lambda$deleteById$14(id, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    static /* synthetic */ Integer lambda$deleteById$14(long j2, SQLiteConnection sQLiteConnection) {
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("DELETE FROM physiotherapy_data WHERE id = ?");
        try {
            sQLiteStatementPrepare.mo181bindLong(1, j2);
            sQLiteStatementPrepare.step();
            return Integer.valueOf(SQLiteConnectionUtil.getTotalChangedRows(sQLiteConnection));
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.PhysiotherapyDao
    public Object deleteAll(final Continuation<? super Integer> arg0) {
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.PhysiotherapyDao_Impl$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PhysiotherapyDao_Impl.lambda$deleteAll$15((SQLiteConnection) obj);
            }
        }, arg0);
    }

    static /* synthetic */ Integer lambda$deleteAll$15(SQLiteConnection sQLiteConnection) {
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("DELETE FROM physiotherapy_data");
        try {
            sQLiteStatementPrepare.step();
            return Integer.valueOf(SQLiteConnectionUtil.getTotalChangedRows(sQLiteConnection));
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.PhysiotherapyDao
    public Object deleteAllByUser(final String userId, final Continuation<? super Integer> arg1) {
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.PhysiotherapyDao_Impl$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PhysiotherapyDao_Impl.lambda$deleteAllByUser$16(userId, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    static /* synthetic */ Integer lambda$deleteAllByUser$16(String str, SQLiteConnection sQLiteConnection) {
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("DELETE FROM physiotherapy_data WHERE user_id = ?");
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
