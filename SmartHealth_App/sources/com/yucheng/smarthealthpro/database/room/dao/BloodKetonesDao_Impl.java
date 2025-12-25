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
import com.yucheng.smarthealthpro.database.room.bean.BloodKetones;
import com.yucheng.smarthealthpro.database.room.bean.DataGroupIdUpdate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;

/* loaded from: classes4.dex */
public final class BloodKetonesDao_Impl implements BloodKetonesDao {
    private final RoomDatabase __db;
    private final EntityInsertAdapter<BloodKetones> __insertAdapterOfBloodKetones = new EntityInsertAdapter<BloodKetones>() { // from class: com.yucheng.smarthealthpro.database.room.dao.BloodKetonesDao_Impl.1
        @Override // androidx.room.EntityInsertAdapter
        protected String createQuery() {
            return "INSERT OR REPLACE INTO `blood_ketones_data` (`id`,`query_id`,`start_timestamp`,`time_year_to_day`,`blood_ketones_integer_part`,`blood_ketones_fractional_part`,`measure_mode`,`user_id`,`device_type`,`device_mac_address`,`data_group_id`,`is_uploaded`,`is_other_uploaded`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // androidx.room.EntityInsertAdapter
        public void bind(SQLiteStatement sQLiteStatement, BloodKetones bloodKetones) {
            if (bloodKetones.getId() == null) {
                sQLiteStatement.mo182bindNull(1);
            } else {
                sQLiteStatement.mo181bindLong(1, bloodKetones.getId().longValue());
            }
            sQLiteStatement.mo181bindLong(2, bloodKetones.getQueryID());
            sQLiteStatement.mo181bindLong(3, bloodKetones.getStartTimestamp());
            if (bloodKetones.getTimeYearToDay() == null) {
                sQLiteStatement.mo182bindNull(4);
            } else {
                sQLiteStatement.mo183bindText(4, bloodKetones.getTimeYearToDay());
            }
            sQLiteStatement.mo181bindLong(5, bloodKetones.getBloodKetonesInteger());
            sQLiteStatement.mo181bindLong(6, bloodKetones.getBloodKetonesFraction());
            sQLiteStatement.mo181bindLong(7, bloodKetones.getMeasureMode());
            if (bloodKetones.getUserId() == null) {
                sQLiteStatement.mo182bindNull(8);
            } else {
                sQLiteStatement.mo183bindText(8, bloodKetones.getUserId());
            }
            if (bloodKetones.getDeviceType() == null) {
                sQLiteStatement.mo182bindNull(9);
            } else {
                sQLiteStatement.mo183bindText(9, bloodKetones.getDeviceType());
            }
            if (bloodKetones.getDeviceMacAddress() == null) {
                sQLiteStatement.mo182bindNull(10);
            } else {
                sQLiteStatement.mo183bindText(10, bloodKetones.getDeviceMacAddress());
            }
            if (bloodKetones.getDataGroupId() == null) {
                sQLiteStatement.mo182bindNull(11);
            } else {
                sQLiteStatement.mo183bindText(11, bloodKetones.getDataGroupId());
            }
            sQLiteStatement.mo181bindLong(12, bloodKetones.isUploaded() ? 1L : 0L);
            sQLiteStatement.mo181bindLong(13, bloodKetones.isOtherUploaded() ? 1L : 0L);
        }
    };
    private final EntityDeleteOrUpdateAdapter<BloodKetones> __updateAdapterOfBloodKetones = new EntityDeleteOrUpdateAdapter<BloodKetones>() { // from class: com.yucheng.smarthealthpro.database.room.dao.BloodKetonesDao_Impl.2
        @Override // androidx.room.EntityDeleteOrUpdateAdapter
        protected String createQuery() {
            return "UPDATE OR ABORT `blood_ketones_data` SET `id` = ?,`query_id` = ?,`start_timestamp` = ?,`time_year_to_day` = ?,`blood_ketones_integer_part` = ?,`blood_ketones_fractional_part` = ?,`measure_mode` = ?,`user_id` = ?,`device_type` = ?,`device_mac_address` = ?,`data_group_id` = ?,`is_uploaded` = ?,`is_other_uploaded` = ? WHERE `id` = ?";
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // androidx.room.EntityDeleteOrUpdateAdapter
        public void bind(SQLiteStatement sQLiteStatement, BloodKetones bloodKetones) {
            if (bloodKetones.getId() == null) {
                sQLiteStatement.mo182bindNull(1);
            } else {
                sQLiteStatement.mo181bindLong(1, bloodKetones.getId().longValue());
            }
            sQLiteStatement.mo181bindLong(2, bloodKetones.getQueryID());
            sQLiteStatement.mo181bindLong(3, bloodKetones.getStartTimestamp());
            if (bloodKetones.getTimeYearToDay() == null) {
                sQLiteStatement.mo182bindNull(4);
            } else {
                sQLiteStatement.mo183bindText(4, bloodKetones.getTimeYearToDay());
            }
            sQLiteStatement.mo181bindLong(5, bloodKetones.getBloodKetonesInteger());
            sQLiteStatement.mo181bindLong(6, bloodKetones.getBloodKetonesFraction());
            sQLiteStatement.mo181bindLong(7, bloodKetones.getMeasureMode());
            if (bloodKetones.getUserId() == null) {
                sQLiteStatement.mo182bindNull(8);
            } else {
                sQLiteStatement.mo183bindText(8, bloodKetones.getUserId());
            }
            if (bloodKetones.getDeviceType() == null) {
                sQLiteStatement.mo182bindNull(9);
            } else {
                sQLiteStatement.mo183bindText(9, bloodKetones.getDeviceType());
            }
            if (bloodKetones.getDeviceMacAddress() == null) {
                sQLiteStatement.mo182bindNull(10);
            } else {
                sQLiteStatement.mo183bindText(10, bloodKetones.getDeviceMacAddress());
            }
            if (bloodKetones.getDataGroupId() == null) {
                sQLiteStatement.mo182bindNull(11);
            } else {
                sQLiteStatement.mo183bindText(11, bloodKetones.getDataGroupId());
            }
            sQLiteStatement.mo181bindLong(12, bloodKetones.isUploaded() ? 1L : 0L);
            sQLiteStatement.mo181bindLong(13, bloodKetones.isOtherUploaded() ? 1L : 0L);
            if (bloodKetones.getId() == null) {
                sQLiteStatement.mo182bindNull(14);
            } else {
                sQLiteStatement.mo181bindLong(14, bloodKetones.getId().longValue());
            }
        }
    };
    private final EntityDeleteOrUpdateAdapter<DataGroupIdUpdate> __updateAdapterOfDataGroupIdUpdateAsBloodKetones = new EntityDeleteOrUpdateAdapter<DataGroupIdUpdate>() { // from class: com.yucheng.smarthealthpro.database.room.dao.BloodKetonesDao_Impl.3
        @Override // androidx.room.EntityDeleteOrUpdateAdapter
        protected String createQuery() {
            return "UPDATE OR ABORT `blood_ketones_data` SET `id` = ?,`data_group_id` = ? WHERE `id` = ?";
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

    public BloodKetonesDao_Impl(final RoomDatabase __db) {
        this.__db = __db;
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.BloodKetonesDao
    public Object insert(final BloodKetones metric, final Continuation<? super Long> arg1) {
        metric.getClass();
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.BloodKetonesDao_Impl$$ExternalSyntheticLambda15
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f$0.lambda$insert$0(metric, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Long lambda$insert$0(BloodKetones bloodKetones, SQLiteConnection sQLiteConnection) {
        return Long.valueOf(this.__insertAdapterOfBloodKetones.insertAndReturnId(sQLiteConnection, bloodKetones));
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.BloodKetonesDao
    public Object insertAll(final List<BloodKetones> metrics, final Continuation<? super List<Long>> arg1) {
        metrics.getClass();
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.BloodKetonesDao_Impl$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f$0.lambda$insertAll$1(metrics, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ List lambda$insertAll$1(List list, SQLiteConnection sQLiteConnection) {
        return this.__insertAdapterOfBloodKetones.insertAndReturnIdsList(sQLiteConnection, list);
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.BloodKetonesDao
    public Object update(final BloodKetones metric, final Continuation<? super Unit> arg1) {
        metric.getClass();
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.BloodKetonesDao_Impl$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f$0.lambda$update$2(metric, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Unit lambda$update$2(BloodKetones bloodKetones, SQLiteConnection sQLiteConnection) throws Exception {
        this.__updateAdapterOfBloodKetones.handle(sQLiteConnection, bloodKetones);
        return Unit.INSTANCE;
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.BloodKetonesDao
    public Object updateDataGroupIds(final List<DataGroupIdUpdate> updates, final Continuation<? super Unit> arg1) {
        updates.getClass();
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.BloodKetonesDao_Impl$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f$0.lambda$updateDataGroupIds$3(updates, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Unit lambda$updateDataGroupIds$3(List list, SQLiteConnection sQLiteConnection) throws Exception {
        this.__updateAdapterOfDataGroupIdUpdateAsBloodKetones.handleMultiple(sQLiteConnection, list);
        return Unit.INSTANCE;
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.BloodKetonesDao
    public Object getById(final long id, final Continuation<? super BloodKetones> arg1) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.BloodKetonesDao_Impl$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return BloodKetonesDao_Impl.lambda$getById$4(id, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    static /* synthetic */ BloodKetones lambda$getById$4(long j2, SQLiteConnection sQLiteConnection) {
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("SELECT * FROM blood_ketones_data WHERE id = ?");
        try {
            sQLiteStatementPrepare.mo181bindLong(1, j2);
            int columnIndexOrThrow = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "id");
            int columnIndexOrThrow2 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "query_id");
            int columnIndexOrThrow3 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "start_timestamp");
            int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "time_year_to_day");
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "blood_ketones_integer_part");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "blood_ketones_fractional_part");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "measure_mode");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_uploaded");
            BloodKetones bloodKetones = null;
            if (sQLiteStatementPrepare.step()) {
                bloodKetones = new BloodKetones(sQLiteStatementPrepare.isNull(columnIndexOrThrow) ? null : Long.valueOf(sQLiteStatementPrepare.getLong(columnIndexOrThrow)), (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2), sQLiteStatementPrepare.getLong(columnIndexOrThrow3), sQLiteStatementPrepare.isNull(columnIndexOrThrow4) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow4), (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5), (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6), (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7), sQLiteStatementPrepare.isNull(columnIndexOrThrow8) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow8), sQLiteStatementPrepare.isNull(columnIndexOrThrow9) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow9), sQLiteStatementPrepare.isNull(columnIndexOrThrow10) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow10), sQLiteStatementPrepare.isNull(columnIndexOrThrow11) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow11), ((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12)) != 0, ((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow13)) != 0);
            }
            return bloodKetones;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.BloodKetonesDao
    public Object getByUser(final String userId, final Continuation<? super List<BloodKetones>> arg1) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.BloodKetonesDao_Impl$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return BloodKetonesDao_Impl.lambda$getByUser$5(userId, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    static /* synthetic */ List lambda$getByUser$5(String str, SQLiteConnection sQLiteConnection) {
        int i2;
        ArrayList arrayList;
        Long lValueOf;
        int i3;
        String text;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("SELECT * FROM blood_ketones_data WHERE user_id = ? ORDER BY start_timestamp DESC");
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
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "blood_ketones_integer_part");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "blood_ketones_fractional_part");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "measure_mode");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_uploaded");
            ArrayList arrayList2 = new ArrayList();
            while (sQLiteStatementPrepare.step()) {
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow)) {
                    i2 = columnIndexOrThrow13;
                    arrayList = arrayList2;
                    lValueOf = null;
                } else {
                    i2 = columnIndexOrThrow13;
                    arrayList = arrayList2;
                    lValueOf = Long.valueOf(sQLiteStatementPrepare.getLong(columnIndexOrThrow));
                }
                int i4 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
                long j2 = sQLiteStatementPrepare.getLong(columnIndexOrThrow3);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow4)) {
                    i3 = columnIndexOrThrow2;
                    text = null;
                } else {
                    i3 = columnIndexOrThrow2;
                    text = sQLiteStatementPrepare.getText(columnIndexOrThrow4);
                }
                int i5 = columnIndexOrThrow3;
                int i6 = columnIndexOrThrow4;
                int i7 = columnIndexOrThrow5;
                int i8 = i2;
                int i9 = columnIndexOrThrow6;
                ArrayList arrayList3 = arrayList;
                arrayList3.add(new BloodKetones(lValueOf, i4, j2, text, (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5), (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6), (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7), sQLiteStatementPrepare.isNull(columnIndexOrThrow8) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow8), sQLiteStatementPrepare.isNull(columnIndexOrThrow9) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow9), sQLiteStatementPrepare.isNull(columnIndexOrThrow10) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow10), sQLiteStatementPrepare.isNull(columnIndexOrThrow11) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow11), ((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12)) != 0, ((int) sQLiteStatementPrepare.getLong(i8)) != 0));
                columnIndexOrThrow6 = i9;
                arrayList2 = arrayList3;
                columnIndexOrThrow13 = i8;
                columnIndexOrThrow2 = i3;
                columnIndexOrThrow5 = i7;
                columnIndexOrThrow3 = i5;
                columnIndexOrThrow4 = i6;
            }
            return arrayList2;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.BloodKetonesDao
    public Object getByStartTimestamp(final long startTimestamp, final Continuation<? super List<BloodKetones>> arg1) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.BloodKetonesDao_Impl$$ExternalSyntheticLambda12
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return BloodKetonesDao_Impl.lambda$getByStartTimestamp$6(startTimestamp, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    static /* synthetic */ List lambda$getByStartTimestamp$6(long j2, SQLiteConnection sQLiteConnection) {
        int i2;
        ArrayList arrayList;
        Long lValueOf;
        int i3;
        String text;
        int i4;
        boolean z;
        int i5;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("SELECT * FROM blood_ketones_data WHERE start_timestamp = ?");
        try {
            sQLiteStatementPrepare.mo181bindLong(1, j2);
            int columnIndexOrThrow = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "id");
            int columnIndexOrThrow2 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "query_id");
            int columnIndexOrThrow3 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "start_timestamp");
            int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "time_year_to_day");
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "blood_ketones_integer_part");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "blood_ketones_fractional_part");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "measure_mode");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_uploaded");
            ArrayList arrayList2 = new ArrayList();
            while (sQLiteStatementPrepare.step()) {
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow)) {
                    i2 = columnIndexOrThrow13;
                    arrayList = arrayList2;
                    lValueOf = null;
                } else {
                    i2 = columnIndexOrThrow13;
                    arrayList = arrayList2;
                    lValueOf = Long.valueOf(sQLiteStatementPrepare.getLong(columnIndexOrThrow));
                }
                int i6 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
                long j3 = sQLiteStatementPrepare.getLong(columnIndexOrThrow3);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow4)) {
                    i3 = columnIndexOrThrow;
                    i4 = columnIndexOrThrow2;
                    text = null;
                } else {
                    i3 = columnIndexOrThrow;
                    text = sQLiteStatementPrepare.getText(columnIndexOrThrow4);
                    i4 = columnIndexOrThrow2;
                }
                int i7 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5);
                int i8 = columnIndexOrThrow3;
                int i9 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                int i10 = columnIndexOrThrow4;
                int i11 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7);
                String text2 = sQLiteStatementPrepare.isNull(columnIndexOrThrow8) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow8);
                String text3 = sQLiteStatementPrepare.isNull(columnIndexOrThrow9) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow9);
                String text4 = sQLiteStatementPrepare.isNull(columnIndexOrThrow10) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow10);
                String text5 = sQLiteStatementPrepare.isNull(columnIndexOrThrow11) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow11);
                int i12 = columnIndexOrThrow5;
                if (((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12)) != 0) {
                    i5 = i2;
                    z = true;
                } else {
                    z = false;
                    i5 = i2;
                }
                int i13 = columnIndexOrThrow6;
                BloodKetones bloodKetones = new BloodKetones(lValueOf, i6, j3, text, i7, i9, i11, text2, text3, text4, text5, z, ((int) sQLiteStatementPrepare.getLong(i5)) != 0);
                ArrayList arrayList3 = arrayList;
                arrayList3.add(bloodKetones);
                columnIndexOrThrow6 = i13;
                columnIndexOrThrow13 = i5;
                columnIndexOrThrow2 = i4;
                columnIndexOrThrow3 = i8;
                columnIndexOrThrow5 = i12;
                columnIndexOrThrow4 = i10;
                arrayList2 = arrayList3;
                columnIndexOrThrow = i3;
            }
            return arrayList2;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.BloodKetonesDao
    public Object getByUserId(final String userId, final Continuation<? super List<BloodKetones>> arg1) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.BloodKetonesDao_Impl$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return BloodKetonesDao_Impl.lambda$getByUserId$7(userId, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    static /* synthetic */ List lambda$getByUserId$7(String str, SQLiteConnection sQLiteConnection) {
        int i2;
        ArrayList arrayList;
        Long lValueOf;
        int i3;
        String text;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("\n        SELECT * FROM blood_ketones_data \n        WHERE user_id = ? \n        OR user_id = \"\"\n        OR user_id IS NULL\n        ORDER BY start_timestamp DESC\n    ");
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
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "blood_ketones_integer_part");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "blood_ketones_fractional_part");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "measure_mode");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_uploaded");
            ArrayList arrayList2 = new ArrayList();
            while (sQLiteStatementPrepare.step()) {
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow)) {
                    i2 = columnIndexOrThrow13;
                    arrayList = arrayList2;
                    lValueOf = null;
                } else {
                    i2 = columnIndexOrThrow13;
                    arrayList = arrayList2;
                    lValueOf = Long.valueOf(sQLiteStatementPrepare.getLong(columnIndexOrThrow));
                }
                int i4 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
                long j2 = sQLiteStatementPrepare.getLong(columnIndexOrThrow3);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow4)) {
                    i3 = columnIndexOrThrow2;
                    text = null;
                } else {
                    i3 = columnIndexOrThrow2;
                    text = sQLiteStatementPrepare.getText(columnIndexOrThrow4);
                }
                int i5 = columnIndexOrThrow3;
                int i6 = columnIndexOrThrow4;
                int i7 = columnIndexOrThrow5;
                int i8 = i2;
                int i9 = columnIndexOrThrow6;
                ArrayList arrayList3 = arrayList;
                arrayList3.add(new BloodKetones(lValueOf, i4, j2, text, (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5), (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6), (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7), sQLiteStatementPrepare.isNull(columnIndexOrThrow8) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow8), sQLiteStatementPrepare.isNull(columnIndexOrThrow9) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow9), sQLiteStatementPrepare.isNull(columnIndexOrThrow10) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow10), sQLiteStatementPrepare.isNull(columnIndexOrThrow11) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow11), ((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12)) != 0, ((int) sQLiteStatementPrepare.getLong(i8)) != 0));
                columnIndexOrThrow6 = i9;
                arrayList2 = arrayList3;
                columnIndexOrThrow13 = i8;
                columnIndexOrThrow2 = i3;
                columnIndexOrThrow5 = i7;
                columnIndexOrThrow3 = i5;
                columnIndexOrThrow4 = i6;
            }
            return arrayList2;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.BloodKetonesDao
    public Object queryAll(final String userId, final Continuation<? super List<BloodKetones>> arg1) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.BloodKetonesDao_Impl$$ExternalSyntheticLambda16
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return BloodKetonesDao_Impl.lambda$queryAll$8(userId, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    static /* synthetic */ List lambda$queryAll$8(String str, SQLiteConnection sQLiteConnection) {
        int i2;
        ArrayList arrayList;
        Long lValueOf;
        int i3;
        String text;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("\n        SELECT * FROM blood_ketones_data \n        WHERE (user_id = ? \n        OR user_id = \"\"\n        OR user_id IS NULL)\n        ORDER BY start_timestamp DESC\n    ");
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
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "blood_ketones_integer_part");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "blood_ketones_fractional_part");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "measure_mode");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_uploaded");
            ArrayList arrayList2 = new ArrayList();
            while (sQLiteStatementPrepare.step()) {
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow)) {
                    i2 = columnIndexOrThrow13;
                    arrayList = arrayList2;
                    lValueOf = null;
                } else {
                    i2 = columnIndexOrThrow13;
                    arrayList = arrayList2;
                    lValueOf = Long.valueOf(sQLiteStatementPrepare.getLong(columnIndexOrThrow));
                }
                int i4 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
                long j2 = sQLiteStatementPrepare.getLong(columnIndexOrThrow3);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow4)) {
                    i3 = columnIndexOrThrow2;
                    text = null;
                } else {
                    i3 = columnIndexOrThrow2;
                    text = sQLiteStatementPrepare.getText(columnIndexOrThrow4);
                }
                int i5 = columnIndexOrThrow3;
                int i6 = columnIndexOrThrow4;
                int i7 = columnIndexOrThrow5;
                int i8 = i2;
                int i9 = columnIndexOrThrow6;
                ArrayList arrayList3 = arrayList;
                arrayList3.add(new BloodKetones(lValueOf, i4, j2, text, (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5), (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6), (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7), sQLiteStatementPrepare.isNull(columnIndexOrThrow8) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow8), sQLiteStatementPrepare.isNull(columnIndexOrThrow9) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow9), sQLiteStatementPrepare.isNull(columnIndexOrThrow10) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow10), sQLiteStatementPrepare.isNull(columnIndexOrThrow11) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow11), ((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12)) != 0, ((int) sQLiteStatementPrepare.getLong(i8)) != 0));
                columnIndexOrThrow6 = i9;
                arrayList2 = arrayList3;
                columnIndexOrThrow13 = i8;
                columnIndexOrThrow2 = i3;
                columnIndexOrThrow5 = i7;
                columnIndexOrThrow3 = i5;
                columnIndexOrThrow4 = i6;
            }
            return arrayList2;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.BloodKetonesDao
    public Object querySyncedWithYearToDay(final String yearToDay, final String userId, final boolean synced, final Continuation<? super List<BloodKetones>> arg3) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.BloodKetonesDao_Impl$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return BloodKetonesDao_Impl.lambda$querySyncedWithYearToDay$9(synced, yearToDay, userId, (SQLiteConnection) obj);
            }
        }, arg3);
    }

    static /* synthetic */ List lambda$querySyncedWithYearToDay$9(boolean z, String str, String str2, SQLiteConnection sQLiteConnection) {
        int i2;
        Long lValueOf;
        int i3;
        int i4;
        String text;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("\n        SELECT * FROM blood_ketones_data \n        WHERE is_uploaded = ?\n        AND time_year_to_day = ?\n        AND (user_id = ? \n        OR user_id = \"\"\n        OR user_id IS NULL)\n        ORDER BY start_timestamp DESC\n    ");
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
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "blood_ketones_integer_part");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "blood_ketones_fractional_part");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "measure_mode");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_uploaded");
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
                int i6 = i2;
                long j2 = sQLiteStatementPrepare.getLong(i6);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow4)) {
                    i3 = columnIndexOrThrow;
                    i4 = columnIndexOrThrow2;
                    text = null;
                } else {
                    i3 = columnIndexOrThrow;
                    i4 = columnIndexOrThrow2;
                    text = sQLiteStatementPrepare.getText(columnIndexOrThrow4);
                }
                int i7 = columnIndexOrThrow4;
                int i8 = columnIndexOrThrow5;
                int i9 = columnIndexOrThrow6;
                arrayList.add(new BloodKetones(lValueOf, i5, j2, text, (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5), (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6), (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7), sQLiteStatementPrepare.isNull(columnIndexOrThrow8) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow8), sQLiteStatementPrepare.isNull(columnIndexOrThrow9) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow9), sQLiteStatementPrepare.isNull(columnIndexOrThrow10) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow10), sQLiteStatementPrepare.isNull(columnIndexOrThrow11) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow11), ((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12)) != 0, ((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow13)) != 0));
                columnIndexOrThrow = i3;
                columnIndexOrThrow4 = i7;
                columnIndexOrThrow3 = i6;
                columnIndexOrThrow6 = i9;
                columnIndexOrThrow5 = i8;
                columnIndexOrThrow2 = i4;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.BloodKetonesDao
    public Object queryByYearToDay(final String yearToDay, final String userId, final Continuation<? super List<BloodKetones>> arg2) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.BloodKetonesDao_Impl$$ExternalSyntheticLambda13
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return BloodKetonesDao_Impl.lambda$queryByYearToDay$10(yearToDay, userId, (SQLiteConnection) obj);
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
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("\n        SELECT * FROM blood_ketones_data \n        WHERE time_year_to_day = ?\n        AND (user_id = ? \n        OR user_id = \"\"\n        OR user_id IS NULL)\n        ORDER BY start_timestamp DESC\n    ");
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
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "blood_ketones_integer_part");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "blood_ketones_fractional_part");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "measure_mode");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_uploaded");
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
                int i11 = columnIndexOrThrow5;
                int i12 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7);
                String text2 = sQLiteStatementPrepare.isNull(columnIndexOrThrow8) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow8);
                String text3 = sQLiteStatementPrepare.isNull(columnIndexOrThrow9) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow9);
                String text4 = sQLiteStatementPrepare.isNull(columnIndexOrThrow10) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow10);
                String text5 = sQLiteStatementPrepare.isNull(columnIndexOrThrow11) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow11);
                int i13 = columnIndexOrThrow6;
                if (((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12)) != 0) {
                    i5 = columnIndexOrThrow7;
                    z = true;
                } else {
                    i5 = columnIndexOrThrow7;
                    z = false;
                }
                arrayList.add(new BloodKetones(lValueOf, i6, j2, text, i8, i10, i12, text2, text3, text4, text5, z, ((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow13)) != 0));
                columnIndexOrThrow = i3;
                columnIndexOrThrow4 = i9;
                columnIndexOrThrow7 = i5;
                columnIndexOrThrow3 = i7;
                columnIndexOrThrow6 = i13;
                columnIndexOrThrow5 = i11;
                columnIndexOrThrow2 = i4;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.BloodKetonesDao
    public Object querySinceYearToDay(final String yearToDay, final String userId, final Continuation<? super List<BloodKetones>> arg2) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.BloodKetonesDao_Impl$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return BloodKetonesDao_Impl.lambda$querySinceYearToDay$11(yearToDay, userId, (SQLiteConnection) obj);
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
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("\n        SELECT * FROM blood_ketones_data \n        WHERE time_year_to_day >= ?\n        AND (user_id = ? OR user_id = \"\" OR user_id IS NULL)\n    ");
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
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "blood_ketones_integer_part");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "blood_ketones_fractional_part");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "measure_mode");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_uploaded");
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
                int i11 = columnIndexOrThrow5;
                int i12 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7);
                String text2 = sQLiteStatementPrepare.isNull(columnIndexOrThrow8) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow8);
                String text3 = sQLiteStatementPrepare.isNull(columnIndexOrThrow9) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow9);
                String text4 = sQLiteStatementPrepare.isNull(columnIndexOrThrow10) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow10);
                String text5 = sQLiteStatementPrepare.isNull(columnIndexOrThrow11) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow11);
                int i13 = columnIndexOrThrow6;
                if (((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12)) != 0) {
                    i5 = columnIndexOrThrow7;
                    z = true;
                } else {
                    i5 = columnIndexOrThrow7;
                    z = false;
                }
                arrayList.add(new BloodKetones(lValueOf, i6, j2, text, i8, i10, i12, text2, text3, text4, text5, z, ((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow13)) != 0));
                columnIndexOrThrow = i3;
                columnIndexOrThrow4 = i9;
                columnIndexOrThrow7 = i5;
                columnIndexOrThrow3 = i7;
                columnIndexOrThrow6 = i13;
                columnIndexOrThrow5 = i11;
                columnIndexOrThrow2 = i4;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.BloodKetonesDao
    public Object getDataInTimeRange(final long startTime, final long endTime, final String userName, final Continuation<? super List<BloodKetones>> arg3) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.BloodKetonesDao_Impl$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return BloodKetonesDao_Impl.lambda$getDataInTimeRange$12(startTime, endTime, userName, (SQLiteConnection) obj);
            }
        }, arg3);
    }

    static /* synthetic */ List lambda$getDataInTimeRange$12(long j2, long j3, String str, SQLiteConnection sQLiteConnection) {
        int i2;
        ArrayList arrayList;
        Long lValueOf;
        int i3;
        String text;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("\n        SELECT * FROM blood_ketones_data \n        WHERE start_timestamp BETWEEN ? AND ?\n        AND (user_id = ? OR user_id IS NULL OR user_id = '')\n    ");
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
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "blood_ketones_integer_part");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "blood_ketones_fractional_part");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "measure_mode");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_uploaded");
            ArrayList arrayList2 = new ArrayList();
            while (sQLiteStatementPrepare.step()) {
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow)) {
                    i2 = columnIndexOrThrow13;
                    arrayList = arrayList2;
                    lValueOf = null;
                } else {
                    i2 = columnIndexOrThrow13;
                    arrayList = arrayList2;
                    lValueOf = Long.valueOf(sQLiteStatementPrepare.getLong(columnIndexOrThrow));
                }
                int i4 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
                long j4 = sQLiteStatementPrepare.getLong(columnIndexOrThrow3);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow4)) {
                    i3 = columnIndexOrThrow2;
                    text = null;
                } else {
                    i3 = columnIndexOrThrow2;
                    text = sQLiteStatementPrepare.getText(columnIndexOrThrow4);
                }
                int i5 = columnIndexOrThrow3;
                int i6 = columnIndexOrThrow4;
                int i7 = columnIndexOrThrow5;
                int i8 = i2;
                int i9 = columnIndexOrThrow6;
                ArrayList arrayList3 = arrayList;
                arrayList3.add(new BloodKetones(lValueOf, i4, j4, text, (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5), (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6), (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7), sQLiteStatementPrepare.isNull(columnIndexOrThrow8) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow8), sQLiteStatementPrepare.isNull(columnIndexOrThrow9) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow9), sQLiteStatementPrepare.isNull(columnIndexOrThrow10) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow10), sQLiteStatementPrepare.isNull(columnIndexOrThrow11) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow11), ((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12)) != 0, ((int) sQLiteStatementPrepare.getLong(i8)) != 0));
                columnIndexOrThrow6 = i9;
                columnIndexOrThrow2 = i3;
                columnIndexOrThrow3 = i5;
                columnIndexOrThrow13 = i8;
                columnIndexOrThrow5 = i7;
                columnIndexOrThrow4 = i6;
                arrayList2 = arrayList3;
            }
            return arrayList2;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.BloodKetonesDao
    public Object markAsSynced(final List<Long> ids, final boolean synced, final Continuation<? super Integer> arg2) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE blood_ketones_data SET is_uploaded = ? WHERE id IN (");
        StringUtil.appendPlaceholders(sb, ids.size());
        sb.append(")");
        final String string = sb.toString();
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.BloodKetonesDao_Impl$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return BloodKetonesDao_Impl.lambda$markAsSynced$13(string, synced, ids, (SQLiteConnection) obj);
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

    @Override // com.yucheng.smarthealthpro.database.room.dao.BloodKetonesDao
    public Object deleteById(final long id, final Continuation<? super Integer> arg1) {
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.BloodKetonesDao_Impl$$ExternalSyntheticLambda14
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return BloodKetonesDao_Impl.lambda$deleteById$14(id, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    static /* synthetic */ Integer lambda$deleteById$14(long j2, SQLiteConnection sQLiteConnection) {
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("DELETE FROM blood_ketones_data WHERE id = ?");
        try {
            sQLiteStatementPrepare.mo181bindLong(1, j2);
            sQLiteStatementPrepare.step();
            return Integer.valueOf(SQLiteConnectionUtil.getTotalChangedRows(sQLiteConnection));
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.BloodKetonesDao
    public Object deleteAll(final Continuation<? super Integer> arg0) {
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.BloodKetonesDao_Impl$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return BloodKetonesDao_Impl.lambda$deleteAll$15((SQLiteConnection) obj);
            }
        }, arg0);
    }

    static /* synthetic */ Integer lambda$deleteAll$15(SQLiteConnection sQLiteConnection) {
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("DELETE FROM blood_ketones_data");
        try {
            sQLiteStatementPrepare.step();
            return Integer.valueOf(SQLiteConnectionUtil.getTotalChangedRows(sQLiteConnection));
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.BloodKetonesDao
    public Object deleteAllByUser(final String userId, final Continuation<? super Integer> arg1) {
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.BloodKetonesDao_Impl$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return BloodKetonesDao_Impl.lambda$deleteAllByUser$16(userId, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    static /* synthetic */ Integer lambda$deleteAllByUser$16(String str, SQLiteConnection sQLiteConnection) {
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("DELETE FROM blood_ketones_data WHERE user_id = ?");
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
