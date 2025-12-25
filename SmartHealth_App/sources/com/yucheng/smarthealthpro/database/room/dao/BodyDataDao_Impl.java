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
import com.yucheng.smarthealthpro.database.room.bean.BodyData;
import com.yucheng.smarthealthpro.database.room.bean.DataGroupIdUpdate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;

/* loaded from: classes4.dex */
public final class BodyDataDao_Impl implements BodyDataDao {
    private final RoomDatabase __db;
    private final EntityInsertAdapter<BodyData> __insertAdapterOfBodyData = new EntityInsertAdapter<BodyData>() { // from class: com.yucheng.smarthealthpro.database.room.dao.BodyDataDao_Impl.1
        @Override // androidx.room.EntityInsertAdapter
        protected String createQuery() {
            return "INSERT OR REPLACE INTO `body_data` (`id`,`query_id`,`start_timestamp`,`time_year_to_day`,`load_index_integer_part`,`load_index_fractional_part`,`hrv_integer_part`,`hrv_fractional_part`,`pressure_integer_part`,`pressure_fractional_part`,`body_state_integer_part`,`body_state_fractional_part`,`sympathetic_integer_part`,`sympathetic_fractional_part`,`sdn_value`,`maximal_oxygen_intake`,`user_id`,`device_type`,`device_mac_address`,`data_group_id`,`is_uploaded`,`is_other_uploaded`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // androidx.room.EntityInsertAdapter
        public void bind(SQLiteStatement sQLiteStatement, BodyData bodyData) {
            if (bodyData.getId() == null) {
                sQLiteStatement.mo182bindNull(1);
            } else {
                sQLiteStatement.mo181bindLong(1, bodyData.getId().longValue());
            }
            sQLiteStatement.mo181bindLong(2, bodyData.getQueryID());
            sQLiteStatement.mo181bindLong(3, bodyData.getStartTimestamp());
            if (bodyData.getTimeYearToDay() == null) {
                sQLiteStatement.mo182bindNull(4);
            } else {
                sQLiteStatement.mo183bindText(4, bodyData.getTimeYearToDay());
            }
            sQLiteStatement.mo181bindLong(5, bodyData.getLoadIndexInteger());
            sQLiteStatement.mo181bindLong(6, bodyData.getLoadIndexFraction());
            sQLiteStatement.mo181bindLong(7, bodyData.getHrvInteger());
            sQLiteStatement.mo181bindLong(8, bodyData.getHrvFraction());
            sQLiteStatement.mo181bindLong(9, bodyData.getPressureInteger());
            sQLiteStatement.mo181bindLong(10, bodyData.getPressureFraction());
            sQLiteStatement.mo181bindLong(11, bodyData.getBodyStateInteger());
            sQLiteStatement.mo181bindLong(12, bodyData.getBodyStateFraction());
            sQLiteStatement.mo181bindLong(13, bodyData.getSympatheticInteger());
            sQLiteStatement.mo181bindLong(14, bodyData.getSympatheticFraction());
            sQLiteStatement.mo181bindLong(15, bodyData.getSdn());
            sQLiteStatement.mo181bindLong(16, bodyData.getMaximalOxygenIntake());
            if (bodyData.getUserId() == null) {
                sQLiteStatement.mo182bindNull(17);
            } else {
                sQLiteStatement.mo183bindText(17, bodyData.getUserId());
            }
            if (bodyData.getDeviceType() == null) {
                sQLiteStatement.mo182bindNull(18);
            } else {
                sQLiteStatement.mo183bindText(18, bodyData.getDeviceType());
            }
            if (bodyData.getDeviceMacAddress() == null) {
                sQLiteStatement.mo182bindNull(19);
            } else {
                sQLiteStatement.mo183bindText(19, bodyData.getDeviceMacAddress());
            }
            if (bodyData.getDataGroupId() == null) {
                sQLiteStatement.mo182bindNull(20);
            } else {
                sQLiteStatement.mo183bindText(20, bodyData.getDataGroupId());
            }
            sQLiteStatement.mo181bindLong(21, bodyData.isUploaded() ? 1L : 0L);
            sQLiteStatement.mo181bindLong(22, bodyData.isOtherUploaded() ? 1L : 0L);
        }
    };
    private final EntityDeleteOrUpdateAdapter<BodyData> __updateAdapterOfBodyData = new EntityDeleteOrUpdateAdapter<BodyData>() { // from class: com.yucheng.smarthealthpro.database.room.dao.BodyDataDao_Impl.2
        @Override // androidx.room.EntityDeleteOrUpdateAdapter
        protected String createQuery() {
            return "UPDATE OR ABORT `body_data` SET `id` = ?,`query_id` = ?,`start_timestamp` = ?,`time_year_to_day` = ?,`load_index_integer_part` = ?,`load_index_fractional_part` = ?,`hrv_integer_part` = ?,`hrv_fractional_part` = ?,`pressure_integer_part` = ?,`pressure_fractional_part` = ?,`body_state_integer_part` = ?,`body_state_fractional_part` = ?,`sympathetic_integer_part` = ?,`sympathetic_fractional_part` = ?,`sdn_value` = ?,`maximal_oxygen_intake` = ?,`user_id` = ?,`device_type` = ?,`device_mac_address` = ?,`data_group_id` = ?,`is_uploaded` = ?,`is_other_uploaded` = ? WHERE `id` = ?";
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // androidx.room.EntityDeleteOrUpdateAdapter
        public void bind(SQLiteStatement sQLiteStatement, BodyData bodyData) {
            if (bodyData.getId() == null) {
                sQLiteStatement.mo182bindNull(1);
            } else {
                sQLiteStatement.mo181bindLong(1, bodyData.getId().longValue());
            }
            sQLiteStatement.mo181bindLong(2, bodyData.getQueryID());
            sQLiteStatement.mo181bindLong(3, bodyData.getStartTimestamp());
            if (bodyData.getTimeYearToDay() == null) {
                sQLiteStatement.mo182bindNull(4);
            } else {
                sQLiteStatement.mo183bindText(4, bodyData.getTimeYearToDay());
            }
            sQLiteStatement.mo181bindLong(5, bodyData.getLoadIndexInteger());
            sQLiteStatement.mo181bindLong(6, bodyData.getLoadIndexFraction());
            sQLiteStatement.mo181bindLong(7, bodyData.getHrvInteger());
            sQLiteStatement.mo181bindLong(8, bodyData.getHrvFraction());
            sQLiteStatement.mo181bindLong(9, bodyData.getPressureInteger());
            sQLiteStatement.mo181bindLong(10, bodyData.getPressureFraction());
            sQLiteStatement.mo181bindLong(11, bodyData.getBodyStateInteger());
            sQLiteStatement.mo181bindLong(12, bodyData.getBodyStateFraction());
            sQLiteStatement.mo181bindLong(13, bodyData.getSympatheticInteger());
            sQLiteStatement.mo181bindLong(14, bodyData.getSympatheticFraction());
            sQLiteStatement.mo181bindLong(15, bodyData.getSdn());
            sQLiteStatement.mo181bindLong(16, bodyData.getMaximalOxygenIntake());
            if (bodyData.getUserId() == null) {
                sQLiteStatement.mo182bindNull(17);
            } else {
                sQLiteStatement.mo183bindText(17, bodyData.getUserId());
            }
            if (bodyData.getDeviceType() == null) {
                sQLiteStatement.mo182bindNull(18);
            } else {
                sQLiteStatement.mo183bindText(18, bodyData.getDeviceType());
            }
            if (bodyData.getDeviceMacAddress() == null) {
                sQLiteStatement.mo182bindNull(19);
            } else {
                sQLiteStatement.mo183bindText(19, bodyData.getDeviceMacAddress());
            }
            if (bodyData.getDataGroupId() == null) {
                sQLiteStatement.mo182bindNull(20);
            } else {
                sQLiteStatement.mo183bindText(20, bodyData.getDataGroupId());
            }
            sQLiteStatement.mo181bindLong(21, bodyData.isUploaded() ? 1L : 0L);
            sQLiteStatement.mo181bindLong(22, bodyData.isOtherUploaded() ? 1L : 0L);
            if (bodyData.getId() == null) {
                sQLiteStatement.mo182bindNull(23);
            } else {
                sQLiteStatement.mo181bindLong(23, bodyData.getId().longValue());
            }
        }
    };
    private final EntityDeleteOrUpdateAdapter<DataGroupIdUpdate> __updateAdapterOfDataGroupIdUpdateAsBodyData = new EntityDeleteOrUpdateAdapter<DataGroupIdUpdate>() { // from class: com.yucheng.smarthealthpro.database.room.dao.BodyDataDao_Impl.3
        @Override // androidx.room.EntityDeleteOrUpdateAdapter
        protected String createQuery() {
            return "UPDATE OR ABORT `body_data` SET `id` = ?,`data_group_id` = ? WHERE `id` = ?";
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

    public BodyDataDao_Impl(final RoomDatabase __db) {
        this.__db = __db;
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.BodyDataDao
    public Object insert(final BodyData metric, final Continuation<? super Long> arg1) {
        metric.getClass();
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.BodyDataDao_Impl$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f$0.lambda$insert$0(metric, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Long lambda$insert$0(BodyData bodyData, SQLiteConnection sQLiteConnection) {
        return Long.valueOf(this.__insertAdapterOfBodyData.insertAndReturnId(sQLiteConnection, bodyData));
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.BodyDataDao
    public Object insertAll(final List<BodyData> metrics, final Continuation<? super List<Long>> arg1) {
        metrics.getClass();
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.BodyDataDao_Impl$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f$0.lambda$insertAll$1(metrics, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ List lambda$insertAll$1(List list, SQLiteConnection sQLiteConnection) {
        return this.__insertAdapterOfBodyData.insertAndReturnIdsList(sQLiteConnection, list);
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.BodyDataDao
    public Object update(final BodyData metric, final Continuation<? super Unit> arg1) {
        metric.getClass();
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.BodyDataDao_Impl$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f$0.lambda$update$2(metric, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Unit lambda$update$2(BodyData bodyData, SQLiteConnection sQLiteConnection) throws Exception {
        this.__updateAdapterOfBodyData.handle(sQLiteConnection, bodyData);
        return Unit.INSTANCE;
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.BodyDataDao
    public Object updateDataGroupIds(final List<DataGroupIdUpdate> updates, final Continuation<? super Unit> arg1) {
        updates.getClass();
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.BodyDataDao_Impl$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f$0.lambda$updateDataGroupIds$3(updates, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Unit lambda$updateDataGroupIds$3(List list, SQLiteConnection sQLiteConnection) throws Exception {
        this.__updateAdapterOfDataGroupIdUpdateAsBodyData.handleMultiple(sQLiteConnection, list);
        return Unit.INSTANCE;
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.BodyDataDao
    public Object getById(final long id, final Continuation<? super BodyData> arg1) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.BodyDataDao_Impl$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return BodyDataDao_Impl.lambda$getById$4(id, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    static /* synthetic */ BodyData lambda$getById$4(long j2, SQLiteConnection sQLiteConnection) {
        String text;
        int i2;
        String text2;
        int i3;
        String text3;
        int i4;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("SELECT * FROM body_data WHERE id = ?");
        try {
            sQLiteStatementPrepare.mo181bindLong(1, j2);
            int columnIndexOrThrow = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "id");
            int columnIndexOrThrow2 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "query_id");
            int columnIndexOrThrow3 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "start_timestamp");
            int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "time_year_to_day");
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "load_index_integer_part");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "load_index_fractional_part");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "hrv_integer_part");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "hrv_fractional_part");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "pressure_integer_part");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "pressure_fractional_part");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "body_state_integer_part");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "body_state_fractional_part");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sympathetic_integer_part");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sympathetic_fractional_part");
            int columnIndexOrThrow15 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sdn_value");
            int columnIndexOrThrow16 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "maximal_oxygen_intake");
            int columnIndexOrThrow17 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow18 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow19 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow20 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow21 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            int columnIndexOrThrow22 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_uploaded");
            BodyData bodyData = null;
            if (sQLiteStatementPrepare.step()) {
                Long lValueOf = sQLiteStatementPrepare.isNull(columnIndexOrThrow) ? null : Long.valueOf(sQLiteStatementPrepare.getLong(columnIndexOrThrow));
                int i5 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
                long j3 = sQLiteStatementPrepare.getLong(columnIndexOrThrow3);
                String text4 = sQLiteStatementPrepare.isNull(columnIndexOrThrow4) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow4);
                int i6 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5);
                int i7 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                int i8 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7);
                int i9 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow8);
                int i10 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow9);
                int i11 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10);
                int i12 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11);
                int i13 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12);
                int i14 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow13);
                int i15 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow14);
                int i16 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow15);
                int i17 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow16);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow17)) {
                    i2 = columnIndexOrThrow18;
                    text = null;
                } else {
                    text = sQLiteStatementPrepare.getText(columnIndexOrThrow17);
                    i2 = columnIndexOrThrow18;
                }
                if (sQLiteStatementPrepare.isNull(i2)) {
                    i3 = columnIndexOrThrow19;
                    text2 = null;
                } else {
                    text2 = sQLiteStatementPrepare.getText(i2);
                    i3 = columnIndexOrThrow19;
                }
                if (sQLiteStatementPrepare.isNull(i3)) {
                    i4 = columnIndexOrThrow20;
                    text3 = null;
                } else {
                    text3 = sQLiteStatementPrepare.getText(i3);
                    i4 = columnIndexOrThrow20;
                }
                bodyData = new BodyData(lValueOf, i5, j3, text4, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, text, text2, text3, sQLiteStatementPrepare.isNull(i4) ? null : sQLiteStatementPrepare.getText(i4), ((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow21)) != 0, ((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow22)) != 0);
            }
            return bodyData;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.BodyDataDao
    public Object getByUser(final String userId, final Continuation<? super List<BodyData>> arg1) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.BodyDataDao_Impl$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return BodyDataDao_Impl.lambda$getByUser$5(userId, (SQLiteConnection) obj);
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
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("SELECT * FROM body_data WHERE user_id = ? ORDER BY start_timestamp DESC");
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
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "load_index_integer_part");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "load_index_fractional_part");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "hrv_integer_part");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "hrv_fractional_part");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "pressure_integer_part");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "pressure_fractional_part");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "body_state_integer_part");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "body_state_fractional_part");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sympathetic_integer_part");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sympathetic_fractional_part");
            int columnIndexOrThrow15 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sdn_value");
            int columnIndexOrThrow16 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "maximal_oxygen_intake");
            int columnIndexOrThrow17 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow18 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow19 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow20 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow21 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            int columnIndexOrThrow22 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_uploaded");
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
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow4)) {
                    i5 = columnIndexOrThrow2;
                    i4 = columnIndexOrThrow3;
                    text = null;
                } else {
                    i4 = columnIndexOrThrow3;
                    text = sQLiteStatementPrepare.getText(columnIndexOrThrow4);
                    i5 = columnIndexOrThrow2;
                }
                int i12 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5);
                int i13 = columnIndexOrThrow4;
                int i14 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                int i15 = columnIndexOrThrow5;
                int i16 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7);
                int i17 = columnIndexOrThrow6;
                int i18 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow8);
                int i19 = columnIndexOrThrow7;
                int i20 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow9);
                int i21 = columnIndexOrThrow8;
                int i22 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10);
                int i23 = columnIndexOrThrow9;
                int i24 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11);
                int i25 = columnIndexOrThrow10;
                int i26 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12);
                int i27 = columnIndexOrThrow12;
                int i28 = i3;
                int i29 = columnIndexOrThrow11;
                int i30 = (int) sQLiteStatementPrepare.getLong(i28);
                int i31 = i2;
                int i32 = (int) sQLiteStatementPrepare.getLong(i31);
                int i33 = columnIndexOrThrow15;
                int i34 = (int) sQLiteStatementPrepare.getLong(i33);
                columnIndexOrThrow15 = i33;
                int i35 = columnIndexOrThrow16;
                int i36 = (int) sQLiteStatementPrepare.getLong(i35);
                int i37 = columnIndexOrThrow17;
                if (sQLiteStatementPrepare.isNull(i37)) {
                    i6 = columnIndexOrThrow;
                    i7 = columnIndexOrThrow18;
                    text2 = null;
                } else {
                    text2 = sQLiteStatementPrepare.getText(i37);
                    i6 = columnIndexOrThrow;
                    i7 = columnIndexOrThrow18;
                }
                if (sQLiteStatementPrepare.isNull(i7)) {
                    columnIndexOrThrow18 = i7;
                    i8 = columnIndexOrThrow19;
                    text3 = null;
                } else {
                    text3 = sQLiteStatementPrepare.getText(i7);
                    columnIndexOrThrow18 = i7;
                    i8 = columnIndexOrThrow19;
                }
                if (sQLiteStatementPrepare.isNull(i8)) {
                    columnIndexOrThrow19 = i8;
                    i9 = columnIndexOrThrow20;
                    text4 = null;
                } else {
                    text4 = sQLiteStatementPrepare.getText(i8);
                    columnIndexOrThrow19 = i8;
                    i9 = columnIndexOrThrow20;
                }
                String text5 = sQLiteStatementPrepare.isNull(i9) ? null : sQLiteStatementPrepare.getText(i9);
                columnIndexOrThrow20 = i9;
                int i38 = columnIndexOrThrow21;
                String str2 = text5;
                if (((int) sQLiteStatementPrepare.getLong(i38)) != 0) {
                    i10 = columnIndexOrThrow22;
                    z = true;
                } else {
                    i10 = columnIndexOrThrow22;
                    z = false;
                }
                arrayList.add(new BodyData(lValueOf, i11, j2, text, i12, i14, i16, i18, i20, i22, i24, i26, i30, i32, i34, i36, text2, text3, text4, str2, z, ((int) sQLiteStatementPrepare.getLong(i10)) != 0));
                columnIndexOrThrow2 = i5;
                columnIndexOrThrow14 = i31;
                columnIndexOrThrow16 = i35;
                columnIndexOrThrow11 = i29;
                columnIndexOrThrow13 = i28;
                columnIndexOrThrow3 = i4;
                columnIndexOrThrow4 = i13;
                columnIndexOrThrow5 = i15;
                columnIndexOrThrow6 = i17;
                columnIndexOrThrow7 = i19;
                columnIndexOrThrow8 = i21;
                columnIndexOrThrow9 = i23;
                columnIndexOrThrow12 = i27;
                columnIndexOrThrow21 = i38;
                columnIndexOrThrow = i6;
                columnIndexOrThrow17 = i37;
                columnIndexOrThrow22 = i10;
                columnIndexOrThrow10 = i25;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.BodyDataDao
    public Object getByStartTimestamp(final long startTimestamp, final Continuation<? super List<BodyData>> arg1) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.BodyDataDao_Impl$$ExternalSyntheticLambda12
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return BodyDataDao_Impl.lambda$getByStartTimestamp$6(startTimestamp, (SQLiteConnection) obj);
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
        String text3;
        int i7;
        String text4;
        int i8;
        int i9;
        boolean z;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("SELECT * FROM body_data WHERE start_timestamp = ?");
        try {
            sQLiteStatementPrepare.mo181bindLong(1, j2);
            int columnIndexOrThrow = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "id");
            int columnIndexOrThrow2 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "query_id");
            int columnIndexOrThrow3 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "start_timestamp");
            int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "time_year_to_day");
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "load_index_integer_part");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "load_index_fractional_part");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "hrv_integer_part");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "hrv_fractional_part");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "pressure_integer_part");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "pressure_fractional_part");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "body_state_integer_part");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "body_state_fractional_part");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sympathetic_integer_part");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sympathetic_fractional_part");
            int columnIndexOrThrow15 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sdn_value");
            int columnIndexOrThrow16 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "maximal_oxygen_intake");
            int columnIndexOrThrow17 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow18 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow19 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow20 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow21 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            int columnIndexOrThrow22 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_uploaded");
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
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow4)) {
                    i5 = columnIndexOrThrow;
                    i4 = columnIndexOrThrow2;
                    text = null;
                } else {
                    i4 = columnIndexOrThrow2;
                    text = sQLiteStatementPrepare.getText(columnIndexOrThrow4);
                    i5 = columnIndexOrThrow;
                }
                int i11 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5);
                int i12 = columnIndexOrThrow3;
                int i13 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                int i14 = columnIndexOrThrow4;
                int i15 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7);
                int i16 = columnIndexOrThrow5;
                int i17 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow8);
                int i18 = columnIndexOrThrow6;
                int i19 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow9);
                int i20 = columnIndexOrThrow7;
                int i21 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10);
                int i22 = columnIndexOrThrow8;
                int i23 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11);
                int i24 = columnIndexOrThrow9;
                int i25 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12);
                int i26 = columnIndexOrThrow11;
                int i27 = i3;
                int i28 = columnIndexOrThrow10;
                int i29 = (int) sQLiteStatementPrepare.getLong(i27);
                int i30 = i2;
                int i31 = (int) sQLiteStatementPrepare.getLong(i30);
                int i32 = columnIndexOrThrow15;
                int i33 = columnIndexOrThrow12;
                int i34 = (int) sQLiteStatementPrepare.getLong(i32);
                int i35 = columnIndexOrThrow16;
                int i36 = (int) sQLiteStatementPrepare.getLong(i35);
                int i37 = columnIndexOrThrow17;
                if (sQLiteStatementPrepare.isNull(i37)) {
                    columnIndexOrThrow17 = i37;
                    i6 = columnIndexOrThrow18;
                    text2 = null;
                } else {
                    text2 = sQLiteStatementPrepare.getText(i37);
                    columnIndexOrThrow17 = i37;
                    i6 = columnIndexOrThrow18;
                }
                if (sQLiteStatementPrepare.isNull(i6)) {
                    columnIndexOrThrow18 = i6;
                    i7 = columnIndexOrThrow19;
                    text3 = null;
                } else {
                    text3 = sQLiteStatementPrepare.getText(i6);
                    columnIndexOrThrow18 = i6;
                    i7 = columnIndexOrThrow19;
                }
                if (sQLiteStatementPrepare.isNull(i7)) {
                    columnIndexOrThrow19 = i7;
                    i8 = columnIndexOrThrow20;
                    text4 = null;
                } else {
                    text4 = sQLiteStatementPrepare.getText(i7);
                    columnIndexOrThrow19 = i7;
                    i8 = columnIndexOrThrow20;
                }
                String text5 = sQLiteStatementPrepare.isNull(i8) ? null : sQLiteStatementPrepare.getText(i8);
                columnIndexOrThrow20 = i8;
                int i38 = columnIndexOrThrow21;
                String str = text5;
                if (((int) sQLiteStatementPrepare.getLong(i38)) != 0) {
                    i9 = columnIndexOrThrow22;
                    z = true;
                } else {
                    i9 = columnIndexOrThrow22;
                    z = false;
                }
                arrayList.add(new BodyData(lValueOf, i10, j3, text, i11, i13, i15, i17, i19, i21, i23, i25, i29, i31, i34, i36, text2, text3, text4, str, z, ((int) sQLiteStatementPrepare.getLong(i9)) != 0));
                columnIndexOrThrow = i5;
                columnIndexOrThrow21 = i38;
                columnIndexOrThrow10 = i28;
                columnIndexOrThrow13 = i27;
                columnIndexOrThrow2 = i4;
                columnIndexOrThrow3 = i12;
                columnIndexOrThrow4 = i14;
                columnIndexOrThrow5 = i16;
                columnIndexOrThrow6 = i18;
                columnIndexOrThrow7 = i20;
                columnIndexOrThrow8 = i22;
                columnIndexOrThrow11 = i26;
                columnIndexOrThrow14 = i30;
                columnIndexOrThrow22 = i9;
                columnIndexOrThrow9 = i24;
                columnIndexOrThrow12 = i33;
                columnIndexOrThrow15 = i32;
                columnIndexOrThrow16 = i35;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.BodyDataDao
    public Object getByUserId(final String userId, final Continuation<? super List<BodyData>> arg1) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.BodyDataDao_Impl$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return BodyDataDao_Impl.lambda$getByUserId$7(userId, (SQLiteConnection) obj);
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
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("\n        SELECT * FROM body_data \n        WHERE user_id = ? \n        OR user_id = \"\"\n        OR user_id IS NULL\n        ORDER BY start_timestamp DESC\n    ");
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
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "load_index_integer_part");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "load_index_fractional_part");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "hrv_integer_part");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "hrv_fractional_part");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "pressure_integer_part");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "pressure_fractional_part");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "body_state_integer_part");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "body_state_fractional_part");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sympathetic_integer_part");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sympathetic_fractional_part");
            int columnIndexOrThrow15 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sdn_value");
            int columnIndexOrThrow16 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "maximal_oxygen_intake");
            int columnIndexOrThrow17 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow18 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow19 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow20 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow21 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            int columnIndexOrThrow22 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_uploaded");
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
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow4)) {
                    i5 = columnIndexOrThrow2;
                    i4 = columnIndexOrThrow3;
                    text = null;
                } else {
                    i4 = columnIndexOrThrow3;
                    text = sQLiteStatementPrepare.getText(columnIndexOrThrow4);
                    i5 = columnIndexOrThrow2;
                }
                int i12 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5);
                int i13 = columnIndexOrThrow4;
                int i14 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                int i15 = columnIndexOrThrow5;
                int i16 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7);
                int i17 = columnIndexOrThrow6;
                int i18 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow8);
                int i19 = columnIndexOrThrow7;
                int i20 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow9);
                int i21 = columnIndexOrThrow8;
                int i22 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10);
                int i23 = columnIndexOrThrow9;
                int i24 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11);
                int i25 = columnIndexOrThrow10;
                int i26 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12);
                int i27 = columnIndexOrThrow12;
                int i28 = i3;
                int i29 = columnIndexOrThrow11;
                int i30 = (int) sQLiteStatementPrepare.getLong(i28);
                int i31 = i2;
                int i32 = (int) sQLiteStatementPrepare.getLong(i31);
                int i33 = columnIndexOrThrow15;
                int i34 = (int) sQLiteStatementPrepare.getLong(i33);
                columnIndexOrThrow15 = i33;
                int i35 = columnIndexOrThrow16;
                int i36 = (int) sQLiteStatementPrepare.getLong(i35);
                int i37 = columnIndexOrThrow17;
                if (sQLiteStatementPrepare.isNull(i37)) {
                    i6 = columnIndexOrThrow;
                    i7 = columnIndexOrThrow18;
                    text2 = null;
                } else {
                    text2 = sQLiteStatementPrepare.getText(i37);
                    i6 = columnIndexOrThrow;
                    i7 = columnIndexOrThrow18;
                }
                if (sQLiteStatementPrepare.isNull(i7)) {
                    columnIndexOrThrow18 = i7;
                    i8 = columnIndexOrThrow19;
                    text3 = null;
                } else {
                    text3 = sQLiteStatementPrepare.getText(i7);
                    columnIndexOrThrow18 = i7;
                    i8 = columnIndexOrThrow19;
                }
                if (sQLiteStatementPrepare.isNull(i8)) {
                    columnIndexOrThrow19 = i8;
                    i9 = columnIndexOrThrow20;
                    text4 = null;
                } else {
                    text4 = sQLiteStatementPrepare.getText(i8);
                    columnIndexOrThrow19 = i8;
                    i9 = columnIndexOrThrow20;
                }
                String text5 = sQLiteStatementPrepare.isNull(i9) ? null : sQLiteStatementPrepare.getText(i9);
                columnIndexOrThrow20 = i9;
                int i38 = columnIndexOrThrow21;
                String str2 = text5;
                if (((int) sQLiteStatementPrepare.getLong(i38)) != 0) {
                    i10 = columnIndexOrThrow22;
                    z = true;
                } else {
                    i10 = columnIndexOrThrow22;
                    z = false;
                }
                arrayList.add(new BodyData(lValueOf, i11, j2, text, i12, i14, i16, i18, i20, i22, i24, i26, i30, i32, i34, i36, text2, text3, text4, str2, z, ((int) sQLiteStatementPrepare.getLong(i10)) != 0));
                columnIndexOrThrow2 = i5;
                columnIndexOrThrow14 = i31;
                columnIndexOrThrow16 = i35;
                columnIndexOrThrow11 = i29;
                columnIndexOrThrow13 = i28;
                columnIndexOrThrow3 = i4;
                columnIndexOrThrow4 = i13;
                columnIndexOrThrow5 = i15;
                columnIndexOrThrow6 = i17;
                columnIndexOrThrow7 = i19;
                columnIndexOrThrow8 = i21;
                columnIndexOrThrow9 = i23;
                columnIndexOrThrow12 = i27;
                columnIndexOrThrow21 = i38;
                columnIndexOrThrow = i6;
                columnIndexOrThrow17 = i37;
                columnIndexOrThrow22 = i10;
                columnIndexOrThrow10 = i25;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.BodyDataDao
    public Object queryAll(final String userId, final Continuation<? super List<BodyData>> arg1) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.BodyDataDao_Impl$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return BodyDataDao_Impl.lambda$queryAll$8(userId, (SQLiteConnection) obj);
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
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("\n        SELECT * FROM body_data \n        WHERE (user_id = ? \n        OR user_id = \"\"\n        OR user_id IS NULL)\n        ORDER BY start_timestamp DESC\n    ");
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
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "load_index_integer_part");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "load_index_fractional_part");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "hrv_integer_part");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "hrv_fractional_part");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "pressure_integer_part");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "pressure_fractional_part");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "body_state_integer_part");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "body_state_fractional_part");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sympathetic_integer_part");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sympathetic_fractional_part");
            int columnIndexOrThrow15 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sdn_value");
            int columnIndexOrThrow16 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "maximal_oxygen_intake");
            int columnIndexOrThrow17 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow18 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow19 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow20 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow21 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            int columnIndexOrThrow22 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_uploaded");
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
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow4)) {
                    i5 = columnIndexOrThrow2;
                    i4 = columnIndexOrThrow3;
                    text = null;
                } else {
                    i4 = columnIndexOrThrow3;
                    text = sQLiteStatementPrepare.getText(columnIndexOrThrow4);
                    i5 = columnIndexOrThrow2;
                }
                int i12 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5);
                int i13 = columnIndexOrThrow4;
                int i14 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                int i15 = columnIndexOrThrow5;
                int i16 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7);
                int i17 = columnIndexOrThrow6;
                int i18 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow8);
                int i19 = columnIndexOrThrow7;
                int i20 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow9);
                int i21 = columnIndexOrThrow8;
                int i22 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10);
                int i23 = columnIndexOrThrow9;
                int i24 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11);
                int i25 = columnIndexOrThrow10;
                int i26 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12);
                int i27 = columnIndexOrThrow12;
                int i28 = i3;
                int i29 = columnIndexOrThrow11;
                int i30 = (int) sQLiteStatementPrepare.getLong(i28);
                int i31 = i2;
                int i32 = (int) sQLiteStatementPrepare.getLong(i31);
                int i33 = columnIndexOrThrow15;
                int i34 = (int) sQLiteStatementPrepare.getLong(i33);
                columnIndexOrThrow15 = i33;
                int i35 = columnIndexOrThrow16;
                int i36 = (int) sQLiteStatementPrepare.getLong(i35);
                int i37 = columnIndexOrThrow17;
                if (sQLiteStatementPrepare.isNull(i37)) {
                    i6 = columnIndexOrThrow;
                    i7 = columnIndexOrThrow18;
                    text2 = null;
                } else {
                    text2 = sQLiteStatementPrepare.getText(i37);
                    i6 = columnIndexOrThrow;
                    i7 = columnIndexOrThrow18;
                }
                if (sQLiteStatementPrepare.isNull(i7)) {
                    columnIndexOrThrow18 = i7;
                    i8 = columnIndexOrThrow19;
                    text3 = null;
                } else {
                    text3 = sQLiteStatementPrepare.getText(i7);
                    columnIndexOrThrow18 = i7;
                    i8 = columnIndexOrThrow19;
                }
                if (sQLiteStatementPrepare.isNull(i8)) {
                    columnIndexOrThrow19 = i8;
                    i9 = columnIndexOrThrow20;
                    text4 = null;
                } else {
                    text4 = sQLiteStatementPrepare.getText(i8);
                    columnIndexOrThrow19 = i8;
                    i9 = columnIndexOrThrow20;
                }
                String text5 = sQLiteStatementPrepare.isNull(i9) ? null : sQLiteStatementPrepare.getText(i9);
                columnIndexOrThrow20 = i9;
                int i38 = columnIndexOrThrow21;
                String str2 = text5;
                if (((int) sQLiteStatementPrepare.getLong(i38)) != 0) {
                    i10 = columnIndexOrThrow22;
                    z = true;
                } else {
                    i10 = columnIndexOrThrow22;
                    z = false;
                }
                arrayList.add(new BodyData(lValueOf, i11, j2, text, i12, i14, i16, i18, i20, i22, i24, i26, i30, i32, i34, i36, text2, text3, text4, str2, z, ((int) sQLiteStatementPrepare.getLong(i10)) != 0));
                columnIndexOrThrow2 = i5;
                columnIndexOrThrow14 = i31;
                columnIndexOrThrow16 = i35;
                columnIndexOrThrow11 = i29;
                columnIndexOrThrow13 = i28;
                columnIndexOrThrow3 = i4;
                columnIndexOrThrow4 = i13;
                columnIndexOrThrow5 = i15;
                columnIndexOrThrow6 = i17;
                columnIndexOrThrow7 = i19;
                columnIndexOrThrow8 = i21;
                columnIndexOrThrow9 = i23;
                columnIndexOrThrow12 = i27;
                columnIndexOrThrow21 = i38;
                columnIndexOrThrow = i6;
                columnIndexOrThrow17 = i37;
                columnIndexOrThrow22 = i10;
                columnIndexOrThrow10 = i25;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.BodyDataDao
    public Object querySyncedWithYearToDay(final String yearToDay, final String userId, final boolean synced, final Continuation<? super List<BodyData>> arg3) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.BodyDataDao_Impl$$ExternalSyntheticLambda15
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return BodyDataDao_Impl.lambda$querySyncedWithYearToDay$9(synced, yearToDay, userId, (SQLiteConnection) obj);
            }
        }, arg3);
    }

    static /* synthetic */ List lambda$querySyncedWithYearToDay$9(boolean z, String str, String str2, SQLiteConnection sQLiteConnection) {
        ArrayList arrayList;
        Long lValueOf;
        int i2;
        int i3;
        String text;
        int i4;
        int i5;
        int i6;
        String text2;
        String text3;
        int i7;
        String text4;
        int i8;
        int i9;
        boolean z2;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("\n        SELECT * FROM body_data \n        WHERE is_uploaded = ?\n        AND time_year_to_day = ?\n        AND (user_id = ? \n        OR user_id = \"\"\n        OR user_id IS NULL)\n        ORDER BY start_timestamp DESC\n    ");
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
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "load_index_integer_part");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "load_index_fractional_part");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "hrv_integer_part");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "hrv_fractional_part");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "pressure_integer_part");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "pressure_fractional_part");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "body_state_integer_part");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "body_state_fractional_part");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sympathetic_integer_part");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sympathetic_fractional_part");
            int columnIndexOrThrow15 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sdn_value");
            int columnIndexOrThrow16 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "maximal_oxygen_intake");
            int columnIndexOrThrow17 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow18 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow19 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow20 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow21 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            int columnIndexOrThrow22 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_uploaded");
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
                int i10 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
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
                int i11 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5);
                int i12 = i4;
                int i13 = columnIndexOrThrow3;
                int i14 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                int i15 = columnIndexOrThrow4;
                int i16 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7);
                int i17 = columnIndexOrThrow5;
                int i18 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow8);
                int i19 = columnIndexOrThrow6;
                int i20 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow9);
                int i21 = columnIndexOrThrow7;
                int i22 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10);
                int i23 = columnIndexOrThrow8;
                int i24 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11);
                int i25 = columnIndexOrThrow9;
                int i26 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12);
                int i27 = columnIndexOrThrow10;
                int i28 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow13);
                int i29 = columnIndexOrThrow12;
                int i30 = i2;
                int i31 = columnIndexOrThrow11;
                int i32 = (int) sQLiteStatementPrepare.getLong(i30);
                int i33 = columnIndexOrThrow15;
                int i34 = (int) sQLiteStatementPrepare.getLong(i33);
                int i35 = columnIndexOrThrow16;
                int i36 = columnIndexOrThrow13;
                int i37 = (int) sQLiteStatementPrepare.getLong(i35);
                int i38 = columnIndexOrThrow17;
                if (sQLiteStatementPrepare.isNull(i38)) {
                    i5 = i13;
                    i6 = columnIndexOrThrow18;
                    text2 = null;
                } else {
                    i5 = i13;
                    i6 = columnIndexOrThrow18;
                    text2 = sQLiteStatementPrepare.getText(i38);
                }
                if (sQLiteStatementPrepare.isNull(i6)) {
                    columnIndexOrThrow18 = i6;
                    i7 = columnIndexOrThrow19;
                    text3 = null;
                } else {
                    text3 = sQLiteStatementPrepare.getText(i6);
                    columnIndexOrThrow18 = i6;
                    i7 = columnIndexOrThrow19;
                }
                if (sQLiteStatementPrepare.isNull(i7)) {
                    columnIndexOrThrow19 = i7;
                    i8 = columnIndexOrThrow20;
                    text4 = null;
                } else {
                    text4 = sQLiteStatementPrepare.getText(i7);
                    columnIndexOrThrow19 = i7;
                    i8 = columnIndexOrThrow20;
                }
                String text5 = sQLiteStatementPrepare.isNull(i8) ? null : sQLiteStatementPrepare.getText(i8);
                columnIndexOrThrow20 = i8;
                int i39 = columnIndexOrThrow21;
                String str3 = text5;
                if (((int) sQLiteStatementPrepare.getLong(i39)) != 0) {
                    i9 = columnIndexOrThrow22;
                    z2 = true;
                } else {
                    i9 = columnIndexOrThrow22;
                    z2 = false;
                }
                BodyData bodyData = new BodyData(lValueOf, i10, j2, text, i11, i14, i16, i18, i20, i22, i24, i26, i28, i32, i34, i37, text2, text3, text4, str3, z2, ((int) sQLiteStatementPrepare.getLong(i9)) != 0);
                ArrayList arrayList3 = arrayList;
                arrayList3.add(bodyData);
                columnIndexOrThrow14 = i30;
                columnIndexOrThrow13 = i36;
                columnIndexOrThrow3 = i5;
                arrayList2 = arrayList3;
                columnIndexOrThrow16 = i35;
                columnIndexOrThrow17 = i38;
                columnIndexOrThrow = i12;
                columnIndexOrThrow4 = i15;
                columnIndexOrThrow5 = i17;
                columnIndexOrThrow6 = i19;
                columnIndexOrThrow7 = i21;
                columnIndexOrThrow8 = i23;
                columnIndexOrThrow9 = i25;
                columnIndexOrThrow10 = i27;
                columnIndexOrThrow12 = i29;
                columnIndexOrThrow15 = i33;
                columnIndexOrThrow21 = i39;
                columnIndexOrThrow22 = i9;
                columnIndexOrThrow11 = i31;
                columnIndexOrThrow2 = i3;
            }
            return arrayList2;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.BodyDataDao
    public Object queryByYearToDay(final String yearToDay, final String userId, final Continuation<? super List<BodyData>> arg2) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.BodyDataDao_Impl$$ExternalSyntheticLambda16
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return BodyDataDao_Impl.lambda$queryByYearToDay$10(yearToDay, userId, (SQLiteConnection) obj);
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
        String text2;
        int i6;
        int i7;
        String text3;
        int i8;
        String text4;
        int i9;
        int i10;
        boolean z;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("\n        SELECT * FROM body_data \n        WHERE time_year_to_day = ?\n        AND (user_id = ? \n        OR user_id = \"\"\n        OR user_id IS NULL)\n        ORDER BY start_timestamp DESC\n    ");
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
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "load_index_integer_part");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "load_index_fractional_part");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "hrv_integer_part");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "hrv_fractional_part");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "pressure_integer_part");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "pressure_fractional_part");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "body_state_integer_part");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "body_state_fractional_part");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sympathetic_integer_part");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sympathetic_fractional_part");
            int columnIndexOrThrow15 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sdn_value");
            int columnIndexOrThrow16 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "maximal_oxygen_intake");
            int columnIndexOrThrow17 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow18 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow19 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow20 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow21 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            int columnIndexOrThrow22 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_uploaded");
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
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow4)) {
                    i5 = columnIndexOrThrow;
                    i4 = columnIndexOrThrow2;
                    text = null;
                } else {
                    i4 = columnIndexOrThrow2;
                    text = sQLiteStatementPrepare.getText(columnIndexOrThrow4);
                    i5 = columnIndexOrThrow;
                }
                int i12 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5);
                int i13 = columnIndexOrThrow3;
                int i14 = columnIndexOrThrow4;
                int i15 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                int i16 = columnIndexOrThrow5;
                int i17 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7);
                int i18 = columnIndexOrThrow6;
                int i19 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow8);
                int i20 = columnIndexOrThrow7;
                int i21 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow9);
                int i22 = columnIndexOrThrow8;
                int i23 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10);
                int i24 = columnIndexOrThrow9;
                int i25 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11);
                int i26 = columnIndexOrThrow10;
                int i27 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12);
                int i28 = columnIndexOrThrow12;
                int i29 = i3;
                int i30 = columnIndexOrThrow11;
                int i31 = (int) sQLiteStatementPrepare.getLong(i29);
                int i32 = i2;
                int i33 = (int) sQLiteStatementPrepare.getLong(i32);
                int i34 = columnIndexOrThrow15;
                int i35 = (int) sQLiteStatementPrepare.getLong(i34);
                int i36 = columnIndexOrThrow16;
                int i37 = (int) sQLiteStatementPrepare.getLong(i36);
                int i38 = columnIndexOrThrow17;
                if (sQLiteStatementPrepare.isNull(i38)) {
                    i6 = i13;
                    i7 = columnIndexOrThrow18;
                    text2 = null;
                } else {
                    text2 = sQLiteStatementPrepare.getText(i38);
                    i6 = i13;
                    i7 = columnIndexOrThrow18;
                }
                if (sQLiteStatementPrepare.isNull(i7)) {
                    columnIndexOrThrow18 = i7;
                    i8 = columnIndexOrThrow19;
                    text3 = null;
                } else {
                    text3 = sQLiteStatementPrepare.getText(i7);
                    columnIndexOrThrow18 = i7;
                    i8 = columnIndexOrThrow19;
                }
                if (sQLiteStatementPrepare.isNull(i8)) {
                    columnIndexOrThrow19 = i8;
                    i9 = columnIndexOrThrow20;
                    text4 = null;
                } else {
                    text4 = sQLiteStatementPrepare.getText(i8);
                    columnIndexOrThrow19 = i8;
                    i9 = columnIndexOrThrow20;
                }
                String text5 = sQLiteStatementPrepare.isNull(i9) ? null : sQLiteStatementPrepare.getText(i9);
                columnIndexOrThrow20 = i9;
                int i39 = columnIndexOrThrow21;
                String str3 = text5;
                if (((int) sQLiteStatementPrepare.getLong(i39)) != 0) {
                    i10 = columnIndexOrThrow22;
                    z = true;
                } else {
                    i10 = columnIndexOrThrow22;
                    z = false;
                }
                arrayList.add(new BodyData(lValueOf, i11, j2, text, i12, i15, i17, i19, i21, i23, i25, i27, i31, i33, i35, i37, text2, text3, text4, str3, z, ((int) sQLiteStatementPrepare.getLong(i10)) != 0));
                columnIndexOrThrow = i5;
                columnIndexOrThrow3 = i6;
                columnIndexOrThrow17 = i38;
                columnIndexOrThrow11 = i30;
                columnIndexOrThrow13 = i29;
                columnIndexOrThrow4 = i14;
                columnIndexOrThrow5 = i16;
                columnIndexOrThrow6 = i18;
                columnIndexOrThrow7 = i20;
                columnIndexOrThrow8 = i22;
                columnIndexOrThrow9 = i24;
                columnIndexOrThrow12 = i28;
                columnIndexOrThrow14 = i32;
                columnIndexOrThrow15 = i34;
                columnIndexOrThrow22 = i10;
                columnIndexOrThrow16 = i36;
                columnIndexOrThrow10 = i26;
                columnIndexOrThrow21 = i39;
                columnIndexOrThrow2 = i4;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.BodyDataDao
    public Object querySinceYearToDay(final String yearToDay, final String userId, final Continuation<? super List<BodyData>> arg2) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.BodyDataDao_Impl$$ExternalSyntheticLambda13
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return BodyDataDao_Impl.lambda$querySinceYearToDay$11(yearToDay, userId, (SQLiteConnection) obj);
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
        String text2;
        int i6;
        int i7;
        String text3;
        int i8;
        String text4;
        int i9;
        int i10;
        boolean z;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("\n        SELECT * FROM body_data \n        WHERE time_year_to_day >= ?\n        AND (user_id = ? OR user_id = \"\" OR user_id IS NULL)\n    ");
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
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "load_index_integer_part");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "load_index_fractional_part");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "hrv_integer_part");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "hrv_fractional_part");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "pressure_integer_part");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "pressure_fractional_part");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "body_state_integer_part");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "body_state_fractional_part");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sympathetic_integer_part");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sympathetic_fractional_part");
            int columnIndexOrThrow15 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sdn_value");
            int columnIndexOrThrow16 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "maximal_oxygen_intake");
            int columnIndexOrThrow17 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow18 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow19 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow20 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow21 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            int columnIndexOrThrow22 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_uploaded");
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
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow4)) {
                    i5 = columnIndexOrThrow;
                    i4 = columnIndexOrThrow2;
                    text = null;
                } else {
                    i4 = columnIndexOrThrow2;
                    text = sQLiteStatementPrepare.getText(columnIndexOrThrow4);
                    i5 = columnIndexOrThrow;
                }
                int i12 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5);
                int i13 = columnIndexOrThrow3;
                int i14 = columnIndexOrThrow4;
                int i15 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                int i16 = columnIndexOrThrow5;
                int i17 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7);
                int i18 = columnIndexOrThrow6;
                int i19 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow8);
                int i20 = columnIndexOrThrow7;
                int i21 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow9);
                int i22 = columnIndexOrThrow8;
                int i23 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10);
                int i24 = columnIndexOrThrow9;
                int i25 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11);
                int i26 = columnIndexOrThrow10;
                int i27 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12);
                int i28 = columnIndexOrThrow12;
                int i29 = i3;
                int i30 = columnIndexOrThrow11;
                int i31 = (int) sQLiteStatementPrepare.getLong(i29);
                int i32 = i2;
                int i33 = (int) sQLiteStatementPrepare.getLong(i32);
                int i34 = columnIndexOrThrow15;
                int i35 = (int) sQLiteStatementPrepare.getLong(i34);
                int i36 = columnIndexOrThrow16;
                int i37 = (int) sQLiteStatementPrepare.getLong(i36);
                int i38 = columnIndexOrThrow17;
                if (sQLiteStatementPrepare.isNull(i38)) {
                    i6 = i13;
                    i7 = columnIndexOrThrow18;
                    text2 = null;
                } else {
                    text2 = sQLiteStatementPrepare.getText(i38);
                    i6 = i13;
                    i7 = columnIndexOrThrow18;
                }
                if (sQLiteStatementPrepare.isNull(i7)) {
                    columnIndexOrThrow18 = i7;
                    i8 = columnIndexOrThrow19;
                    text3 = null;
                } else {
                    text3 = sQLiteStatementPrepare.getText(i7);
                    columnIndexOrThrow18 = i7;
                    i8 = columnIndexOrThrow19;
                }
                if (sQLiteStatementPrepare.isNull(i8)) {
                    columnIndexOrThrow19 = i8;
                    i9 = columnIndexOrThrow20;
                    text4 = null;
                } else {
                    text4 = sQLiteStatementPrepare.getText(i8);
                    columnIndexOrThrow19 = i8;
                    i9 = columnIndexOrThrow20;
                }
                String text5 = sQLiteStatementPrepare.isNull(i9) ? null : sQLiteStatementPrepare.getText(i9);
                columnIndexOrThrow20 = i9;
                int i39 = columnIndexOrThrow21;
                String str3 = text5;
                if (((int) sQLiteStatementPrepare.getLong(i39)) != 0) {
                    i10 = columnIndexOrThrow22;
                    z = true;
                } else {
                    i10 = columnIndexOrThrow22;
                    z = false;
                }
                arrayList.add(new BodyData(lValueOf, i11, j2, text, i12, i15, i17, i19, i21, i23, i25, i27, i31, i33, i35, i37, text2, text3, text4, str3, z, ((int) sQLiteStatementPrepare.getLong(i10)) != 0));
                columnIndexOrThrow = i5;
                columnIndexOrThrow3 = i6;
                columnIndexOrThrow17 = i38;
                columnIndexOrThrow11 = i30;
                columnIndexOrThrow13 = i29;
                columnIndexOrThrow4 = i14;
                columnIndexOrThrow5 = i16;
                columnIndexOrThrow6 = i18;
                columnIndexOrThrow7 = i20;
                columnIndexOrThrow8 = i22;
                columnIndexOrThrow9 = i24;
                columnIndexOrThrow12 = i28;
                columnIndexOrThrow14 = i32;
                columnIndexOrThrow15 = i34;
                columnIndexOrThrow22 = i10;
                columnIndexOrThrow16 = i36;
                columnIndexOrThrow10 = i26;
                columnIndexOrThrow21 = i39;
                columnIndexOrThrow2 = i4;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.BodyDataDao
    public Object getDataInTimeRange(final long startTime, final long endTime, final String userName, final Continuation<? super List<BodyData>> arg3) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.BodyDataDao_Impl$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return BodyDataDao_Impl.lambda$getDataInTimeRange$12(startTime, endTime, userName, (SQLiteConnection) obj);
            }
        }, arg3);
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
        int i7;
        String str2;
        int i8;
        boolean z;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("\n        SELECT * FROM body_data \n        WHERE start_timestamp BETWEEN ? AND ?\n        AND (user_id = ? OR user_id IS NULL OR user_id = '')\n    ");
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
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "load_index_integer_part");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "load_index_fractional_part");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "hrv_integer_part");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "hrv_fractional_part");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "pressure_integer_part");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "pressure_fractional_part");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "body_state_integer_part");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "body_state_fractional_part");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sympathetic_integer_part");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sympathetic_fractional_part");
            int columnIndexOrThrow15 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sdn_value");
            int columnIndexOrThrow16 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "maximal_oxygen_intake");
            int columnIndexOrThrow17 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow18 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow19 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow20 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow21 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            int columnIndexOrThrow22 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_uploaded");
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
                int i9 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
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
                int i10 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5);
                int i11 = columnIndexOrThrow4;
                int i12 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                int i13 = columnIndexOrThrow5;
                int i14 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7);
                int i15 = columnIndexOrThrow6;
                int i16 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow8);
                int i17 = columnIndexOrThrow7;
                int i18 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow9);
                int i19 = columnIndexOrThrow8;
                int i20 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10);
                int i21 = columnIndexOrThrow9;
                int i22 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11);
                int i23 = columnIndexOrThrow10;
                int i24 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12);
                int i25 = columnIndexOrThrow12;
                int i26 = i3;
                int i27 = columnIndexOrThrow11;
                int i28 = (int) sQLiteStatementPrepare.getLong(i26);
                int i29 = i2;
                int i30 = (int) sQLiteStatementPrepare.getLong(i29);
                int i31 = columnIndexOrThrow15;
                int i32 = (int) sQLiteStatementPrepare.getLong(i31);
                int i33 = columnIndexOrThrow16;
                int i34 = (int) sQLiteStatementPrepare.getLong(i33);
                int i35 = columnIndexOrThrow17;
                if (sQLiteStatementPrepare.isNull(i35)) {
                    i6 = columnIndexOrThrow;
                    text2 = null;
                } else {
                    i6 = columnIndexOrThrow;
                    text2 = sQLiteStatementPrepare.getText(i35);
                }
                int i36 = columnIndexOrThrow18;
                if (sQLiteStatementPrepare.isNull(i36)) {
                    columnIndexOrThrow18 = i36;
                    text3 = null;
                } else {
                    columnIndexOrThrow18 = i36;
                    text3 = sQLiteStatementPrepare.getText(i36);
                }
                int i37 = columnIndexOrThrow19;
                if (sQLiteStatementPrepare.isNull(i37)) {
                    columnIndexOrThrow19 = i37;
                    i7 = columnIndexOrThrow20;
                    str2 = null;
                } else {
                    String text4 = sQLiteStatementPrepare.getText(i37);
                    columnIndexOrThrow19 = i37;
                    i7 = columnIndexOrThrow20;
                    str2 = text4;
                }
                String text5 = sQLiteStatementPrepare.isNull(i7) ? null : sQLiteStatementPrepare.getText(i7);
                columnIndexOrThrow20 = i7;
                int i38 = columnIndexOrThrow21;
                String str3 = text5;
                if (((int) sQLiteStatementPrepare.getLong(i38)) != 0) {
                    i8 = columnIndexOrThrow22;
                    z = true;
                } else {
                    i8 = columnIndexOrThrow22;
                    z = false;
                }
                arrayList.add(new BodyData(lValueOf, i9, j4, text, i10, i12, i14, i16, i18, i20, i22, i24, i28, i30, i32, i34, text2, text3, str2, str3, z, ((int) sQLiteStatementPrepare.getLong(i8)) != 0));
                columnIndexOrThrow2 = i5;
                columnIndexOrThrow11 = i27;
                columnIndexOrThrow13 = i26;
                columnIndexOrThrow3 = i4;
                columnIndexOrThrow4 = i11;
                columnIndexOrThrow5 = i13;
                columnIndexOrThrow6 = i15;
                columnIndexOrThrow7 = i17;
                columnIndexOrThrow8 = i19;
                columnIndexOrThrow9 = i21;
                columnIndexOrThrow12 = i25;
                columnIndexOrThrow14 = i29;
                columnIndexOrThrow15 = i31;
                columnIndexOrThrow16 = i33;
                columnIndexOrThrow21 = i38;
                columnIndexOrThrow = i6;
                columnIndexOrThrow17 = i35;
                columnIndexOrThrow22 = i8;
                columnIndexOrThrow10 = i23;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.BodyDataDao
    public Object markAsSynced(final List<Long> ids, final boolean synced, final Continuation<? super Integer> arg2) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE body_data SET is_uploaded = ? WHERE id IN (");
        StringUtil.appendPlaceholders(sb, ids.size());
        sb.append(")");
        final String string = sb.toString();
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.BodyDataDao_Impl$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return BodyDataDao_Impl.lambda$markAsSynced$13(string, synced, ids, (SQLiteConnection) obj);
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

    @Override // com.yucheng.smarthealthpro.database.room.dao.BodyDataDao
    public Object deleteById(final long id, final Continuation<? super Integer> arg1) {
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.BodyDataDao_Impl$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return BodyDataDao_Impl.lambda$deleteById$14(id, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    static /* synthetic */ Integer lambda$deleteById$14(long j2, SQLiteConnection sQLiteConnection) {
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("DELETE FROM body_data WHERE id = ?");
        try {
            sQLiteStatementPrepare.mo181bindLong(1, j2);
            sQLiteStatementPrepare.step();
            return Integer.valueOf(SQLiteConnectionUtil.getTotalChangedRows(sQLiteConnection));
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.BodyDataDao
    public Object deleteAll(final Continuation<? super Integer> arg0) {
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.BodyDataDao_Impl$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return BodyDataDao_Impl.lambda$deleteAll$15((SQLiteConnection) obj);
            }
        }, arg0);
    }

    static /* synthetic */ Integer lambda$deleteAll$15(SQLiteConnection sQLiteConnection) {
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("DELETE FROM body_data");
        try {
            sQLiteStatementPrepare.step();
            return Integer.valueOf(SQLiteConnectionUtil.getTotalChangedRows(sQLiteConnection));
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.BodyDataDao
    public Object deleteAllByUser(final String userId, final Continuation<? super Integer> arg1) {
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.BodyDataDao_Impl$$ExternalSyntheticLambda14
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return BodyDataDao_Impl.lambda$deleteAllByUser$16(userId, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    static /* synthetic */ Integer lambda$deleteAllByUser$16(String str, SQLiteConnection sQLiteConnection) {
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("DELETE FROM body_data WHERE user_id = ?");
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
