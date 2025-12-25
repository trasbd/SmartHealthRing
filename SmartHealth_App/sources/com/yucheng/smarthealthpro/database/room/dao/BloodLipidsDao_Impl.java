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
import com.yucheng.smarthealthpro.database.room.bean.BloodLipids;
import com.yucheng.smarthealthpro.database.room.bean.DataGroupIdUpdate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;

/* loaded from: classes4.dex */
public final class BloodLipidsDao_Impl implements BloodLipidsDao {
    private final RoomDatabase __db;
    private final EntityInsertAdapter<BloodLipids> __insertAdapterOfBloodLipids = new EntityInsertAdapter<BloodLipids>() { // from class: com.yucheng.smarthealthpro.database.room.dao.BloodLipidsDao_Impl.1
        @Override // androidx.room.EntityInsertAdapter
        protected String createQuery() {
            return "INSERT OR REPLACE INTO `blood_lipids_data` (`id`,`query_id`,`start_timestamp`,`time_year_to_day`,`cholesterol_integer_part`,`cholesterol_fractional_part`,`triglyceride_integer_part`,`triglyceride_fractional_part`,`high_lipoprotein_cholesterol_integer_part`,`high_lipoprotein_cholesterol_fractional_part`,`low_lipoprotein_cholesterol_integer_part`,`low_lipoprotein_cholesterol_fractional_part`,`measure_mode`,`user_id`,`device_type`,`device_mac_address`,`data_group_id`,`is_uploaded`,`is_other_uploaded`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // androidx.room.EntityInsertAdapter
        public void bind(SQLiteStatement sQLiteStatement, BloodLipids bloodLipids) {
            if (bloodLipids.getId() == null) {
                sQLiteStatement.mo182bindNull(1);
            } else {
                sQLiteStatement.mo181bindLong(1, bloodLipids.getId().longValue());
            }
            sQLiteStatement.mo181bindLong(2, bloodLipids.getQueryID());
            sQLiteStatement.mo181bindLong(3, bloodLipids.getStartTimestamp());
            if (bloodLipids.getTimeYearToDay() == null) {
                sQLiteStatement.mo182bindNull(4);
            } else {
                sQLiteStatement.mo183bindText(4, bloodLipids.getTimeYearToDay());
            }
            sQLiteStatement.mo181bindLong(5, bloodLipids.getCholesterolInteger());
            sQLiteStatement.mo181bindLong(6, bloodLipids.getCholesterolFraction());
            sQLiteStatement.mo181bindLong(7, bloodLipids.getTriglycerideInteger());
            sQLiteStatement.mo181bindLong(8, bloodLipids.getTriglycerideFraction());
            sQLiteStatement.mo181bindLong(9, bloodLipids.getHighLipoproteinCholesterolInteger());
            sQLiteStatement.mo181bindLong(10, bloodLipids.getHighLipoproteinCholesterolFraction());
            sQLiteStatement.mo181bindLong(11, bloodLipids.getLowLipoproteinCholesterolInteger());
            sQLiteStatement.mo181bindLong(12, bloodLipids.getLowLipoproteinCholesterolFraction());
            sQLiteStatement.mo181bindLong(13, bloodLipids.getMeasureMode());
            if (bloodLipids.getUserId() == null) {
                sQLiteStatement.mo182bindNull(14);
            } else {
                sQLiteStatement.mo183bindText(14, bloodLipids.getUserId());
            }
            if (bloodLipids.getDeviceType() == null) {
                sQLiteStatement.mo182bindNull(15);
            } else {
                sQLiteStatement.mo183bindText(15, bloodLipids.getDeviceType());
            }
            if (bloodLipids.getDeviceMacAddress() == null) {
                sQLiteStatement.mo182bindNull(16);
            } else {
                sQLiteStatement.mo183bindText(16, bloodLipids.getDeviceMacAddress());
            }
            if (bloodLipids.getDataGroupId() == null) {
                sQLiteStatement.mo182bindNull(17);
            } else {
                sQLiteStatement.mo183bindText(17, bloodLipids.getDataGroupId());
            }
            sQLiteStatement.mo181bindLong(18, bloodLipids.isUploaded() ? 1L : 0L);
            sQLiteStatement.mo181bindLong(19, bloodLipids.isOtherUploaded() ? 1L : 0L);
        }
    };
    private final EntityDeleteOrUpdateAdapter<BloodLipids> __updateAdapterOfBloodLipids = new EntityDeleteOrUpdateAdapter<BloodLipids>() { // from class: com.yucheng.smarthealthpro.database.room.dao.BloodLipidsDao_Impl.2
        @Override // androidx.room.EntityDeleteOrUpdateAdapter
        protected String createQuery() {
            return "UPDATE OR ABORT `blood_lipids_data` SET `id` = ?,`query_id` = ?,`start_timestamp` = ?,`time_year_to_day` = ?,`cholesterol_integer_part` = ?,`cholesterol_fractional_part` = ?,`triglyceride_integer_part` = ?,`triglyceride_fractional_part` = ?,`high_lipoprotein_cholesterol_integer_part` = ?,`high_lipoprotein_cholesterol_fractional_part` = ?,`low_lipoprotein_cholesterol_integer_part` = ?,`low_lipoprotein_cholesterol_fractional_part` = ?,`measure_mode` = ?,`user_id` = ?,`device_type` = ?,`device_mac_address` = ?,`data_group_id` = ?,`is_uploaded` = ?,`is_other_uploaded` = ? WHERE `id` = ?";
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // androidx.room.EntityDeleteOrUpdateAdapter
        public void bind(SQLiteStatement sQLiteStatement, BloodLipids bloodLipids) {
            if (bloodLipids.getId() == null) {
                sQLiteStatement.mo182bindNull(1);
            } else {
                sQLiteStatement.mo181bindLong(1, bloodLipids.getId().longValue());
            }
            sQLiteStatement.mo181bindLong(2, bloodLipids.getQueryID());
            sQLiteStatement.mo181bindLong(3, bloodLipids.getStartTimestamp());
            if (bloodLipids.getTimeYearToDay() == null) {
                sQLiteStatement.mo182bindNull(4);
            } else {
                sQLiteStatement.mo183bindText(4, bloodLipids.getTimeYearToDay());
            }
            sQLiteStatement.mo181bindLong(5, bloodLipids.getCholesterolInteger());
            sQLiteStatement.mo181bindLong(6, bloodLipids.getCholesterolFraction());
            sQLiteStatement.mo181bindLong(7, bloodLipids.getTriglycerideInteger());
            sQLiteStatement.mo181bindLong(8, bloodLipids.getTriglycerideFraction());
            sQLiteStatement.mo181bindLong(9, bloodLipids.getHighLipoproteinCholesterolInteger());
            sQLiteStatement.mo181bindLong(10, bloodLipids.getHighLipoproteinCholesterolFraction());
            sQLiteStatement.mo181bindLong(11, bloodLipids.getLowLipoproteinCholesterolInteger());
            sQLiteStatement.mo181bindLong(12, bloodLipids.getLowLipoproteinCholesterolFraction());
            sQLiteStatement.mo181bindLong(13, bloodLipids.getMeasureMode());
            if (bloodLipids.getUserId() == null) {
                sQLiteStatement.mo182bindNull(14);
            } else {
                sQLiteStatement.mo183bindText(14, bloodLipids.getUserId());
            }
            if (bloodLipids.getDeviceType() == null) {
                sQLiteStatement.mo182bindNull(15);
            } else {
                sQLiteStatement.mo183bindText(15, bloodLipids.getDeviceType());
            }
            if (bloodLipids.getDeviceMacAddress() == null) {
                sQLiteStatement.mo182bindNull(16);
            } else {
                sQLiteStatement.mo183bindText(16, bloodLipids.getDeviceMacAddress());
            }
            if (bloodLipids.getDataGroupId() == null) {
                sQLiteStatement.mo182bindNull(17);
            } else {
                sQLiteStatement.mo183bindText(17, bloodLipids.getDataGroupId());
            }
            sQLiteStatement.mo181bindLong(18, bloodLipids.isUploaded() ? 1L : 0L);
            sQLiteStatement.mo181bindLong(19, bloodLipids.isOtherUploaded() ? 1L : 0L);
            if (bloodLipids.getId() == null) {
                sQLiteStatement.mo182bindNull(20);
            } else {
                sQLiteStatement.mo181bindLong(20, bloodLipids.getId().longValue());
            }
        }
    };
    private final EntityDeleteOrUpdateAdapter<DataGroupIdUpdate> __updateAdapterOfDataGroupIdUpdateAsBloodLipids = new EntityDeleteOrUpdateAdapter<DataGroupIdUpdate>() { // from class: com.yucheng.smarthealthpro.database.room.dao.BloodLipidsDao_Impl.3
        @Override // androidx.room.EntityDeleteOrUpdateAdapter
        protected String createQuery() {
            return "UPDATE OR ABORT `blood_lipids_data` SET `id` = ?,`data_group_id` = ? WHERE `id` = ?";
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

    public BloodLipidsDao_Impl(final RoomDatabase __db) {
        this.__db = __db;
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.BloodLipidsDao
    public Object insert(final BloodLipids metric, final Continuation<? super Long> arg1) {
        metric.getClass();
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.BloodLipidsDao_Impl$$ExternalSyntheticLambda15
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f$0.lambda$insert$0(metric, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Long lambda$insert$0(BloodLipids bloodLipids, SQLiteConnection sQLiteConnection) {
        return Long.valueOf(this.__insertAdapterOfBloodLipids.insertAndReturnId(sQLiteConnection, bloodLipids));
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.BloodLipidsDao
    public Object insertAll(final List<BloodLipids> metrics, final Continuation<? super List<Long>> arg1) {
        metrics.getClass();
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.BloodLipidsDao_Impl$$ExternalSyntheticLambda12
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f$0.lambda$insertAll$1(metrics, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ List lambda$insertAll$1(List list, SQLiteConnection sQLiteConnection) {
        return this.__insertAdapterOfBloodLipids.insertAndReturnIdsList(sQLiteConnection, list);
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.BloodLipidsDao
    public Object update(final BloodLipids metric, final Continuation<? super Unit> arg1) {
        metric.getClass();
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.BloodLipidsDao_Impl$$ExternalSyntheticLambda13
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f$0.lambda$update$2(metric, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Unit lambda$update$2(BloodLipids bloodLipids, SQLiteConnection sQLiteConnection) throws Exception {
        this.__updateAdapterOfBloodLipids.handle(sQLiteConnection, bloodLipids);
        return Unit.INSTANCE;
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.BloodLipidsDao
    public Object updateDataGroupIds(final List<DataGroupIdUpdate> updates, final Continuation<? super Unit> arg1) {
        updates.getClass();
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.BloodLipidsDao_Impl$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f$0.lambda$updateDataGroupIds$3(updates, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Unit lambda$updateDataGroupIds$3(List list, SQLiteConnection sQLiteConnection) throws Exception {
        this.__updateAdapterOfDataGroupIdUpdateAsBloodLipids.handleMultiple(sQLiteConnection, list);
        return Unit.INSTANCE;
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.BloodLipidsDao
    public Object getById(final long id, final Continuation<? super BloodLipids> arg1) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.BloodLipidsDao_Impl$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return BloodLipidsDao_Impl.lambda$getById$4(id, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    static /* synthetic */ BloodLipids lambda$getById$4(long j2, SQLiteConnection sQLiteConnection) {
        String text;
        int i2;
        String text2;
        int i3;
        String text3;
        int i4;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("SELECT * FROM blood_lipids_data WHERE id = ?");
        try {
            sQLiteStatementPrepare.mo181bindLong(1, j2);
            int columnIndexOrThrow = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "id");
            int columnIndexOrThrow2 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "query_id");
            int columnIndexOrThrow3 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "start_timestamp");
            int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "time_year_to_day");
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "cholesterol_integer_part");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "cholesterol_fractional_part");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "triglyceride_integer_part");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "triglyceride_fractional_part");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "high_lipoprotein_cholesterol_integer_part");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "high_lipoprotein_cholesterol_fractional_part");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "low_lipoprotein_cholesterol_integer_part");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "low_lipoprotein_cholesterol_fractional_part");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "measure_mode");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow15 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow16 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow17 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow18 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            int columnIndexOrThrow19 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_uploaded");
            BloodLipids bloodLipids = null;
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
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow14)) {
                    i2 = columnIndexOrThrow15;
                    text = null;
                } else {
                    text = sQLiteStatementPrepare.getText(columnIndexOrThrow14);
                    i2 = columnIndexOrThrow15;
                }
                if (sQLiteStatementPrepare.isNull(i2)) {
                    i3 = columnIndexOrThrow16;
                    text2 = null;
                } else {
                    text2 = sQLiteStatementPrepare.getText(i2);
                    i3 = columnIndexOrThrow16;
                }
                if (sQLiteStatementPrepare.isNull(i3)) {
                    i4 = columnIndexOrThrow17;
                    text3 = null;
                } else {
                    text3 = sQLiteStatementPrepare.getText(i3);
                    i4 = columnIndexOrThrow17;
                }
                bloodLipids = new BloodLipids(lValueOf, i5, j3, text4, i6, i7, i8, i9, i10, i11, i12, i13, i14, text, text2, text3, sQLiteStatementPrepare.isNull(i4) ? null : sQLiteStatementPrepare.getText(i4), ((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow18)) != 0, ((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow19)) != 0);
            }
            return bloodLipids;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.BloodLipidsDao
    public Object getByUser(final String userId, final Continuation<? super List<BloodLipids>> arg1) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.BloodLipidsDao_Impl$$ExternalSyntheticLambda14
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return BloodLipidsDao_Impl.lambda$getByUser$5(userId, (SQLiteConnection) obj);
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
        int i6;
        boolean z;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("SELECT * FROM blood_lipids_data WHERE user_id = ? ORDER BY start_timestamp DESC");
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
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "cholesterol_integer_part");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "cholesterol_fractional_part");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "triglyceride_integer_part");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "triglyceride_fractional_part");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "high_lipoprotein_cholesterol_integer_part");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "high_lipoprotein_cholesterol_fractional_part");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "low_lipoprotein_cholesterol_integer_part");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "low_lipoprotein_cholesterol_fractional_part");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "measure_mode");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow15 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow16 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow17 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow18 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            int columnIndexOrThrow19 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_uploaded");
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
                int i17 = columnIndexOrThrow8;
                int i18 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10);
                int i19 = columnIndexOrThrow9;
                int i20 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11);
                int i21 = columnIndexOrThrow10;
                int i22 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12);
                int i23 = columnIndexOrThrow12;
                int i24 = i3;
                int i25 = columnIndexOrThrow11;
                int i26 = (int) sQLiteStatementPrepare.getLong(i24);
                int i27 = i2;
                String text2 = sQLiteStatementPrepare.isNull(i27) ? null : sQLiteStatementPrepare.getText(i27);
                int i28 = columnIndexOrThrow;
                int i29 = columnIndexOrThrow15;
                String text3 = sQLiteStatementPrepare.isNull(i29) ? null : sQLiteStatementPrepare.getText(i29);
                int i30 = columnIndexOrThrow16;
                String text4 = sQLiteStatementPrepare.isNull(i30) ? null : sQLiteStatementPrepare.getText(i30);
                int i31 = columnIndexOrThrow17;
                String text5 = sQLiteStatementPrepare.isNull(i31) ? null : sQLiteStatementPrepare.getText(i31);
                int i32 = columnIndexOrThrow18;
                if (((int) sQLiteStatementPrepare.getLong(i32)) != 0) {
                    i6 = columnIndexOrThrow19;
                    z = true;
                } else {
                    i6 = columnIndexOrThrow19;
                    z = false;
                }
                arrayList.add(new BloodLipids(lValueOf, i7, j2, text, i8, i10, i12, i14, i16, i18, i20, i22, i26, text2, text3, text4, text5, z, ((int) sQLiteStatementPrepare.getLong(i6)) != 0));
                columnIndexOrThrow19 = i6;
                columnIndexOrThrow2 = i5;
                columnIndexOrThrow11 = i25;
                columnIndexOrThrow13 = i24;
                columnIndexOrThrow14 = i27;
                columnIndexOrThrow3 = i4;
                columnIndexOrThrow4 = i9;
                columnIndexOrThrow5 = i11;
                columnIndexOrThrow6 = i13;
                columnIndexOrThrow7 = i15;
                columnIndexOrThrow8 = i17;
                columnIndexOrThrow9 = i19;
                columnIndexOrThrow10 = i21;
                columnIndexOrThrow12 = i23;
                columnIndexOrThrow = i28;
                columnIndexOrThrow15 = i29;
                columnIndexOrThrow16 = i30;
                columnIndexOrThrow17 = i31;
                columnIndexOrThrow18 = i32;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.BloodLipidsDao
    public Object getByStartTimestamp(final long startTimestamp, final Continuation<? super List<BloodLipids>> arg1) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.BloodLipidsDao_Impl$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return BloodLipidsDao_Impl.lambda$getByStartTimestamp$6(startTimestamp, (SQLiteConnection) obj);
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
        int i6;
        String text2;
        String text3;
        int i7;
        String str;
        int i8;
        boolean z;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("SELECT * FROM blood_lipids_data WHERE start_timestamp = ?");
        try {
            sQLiteStatementPrepare.mo181bindLong(1, j2);
            int columnIndexOrThrow = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "id");
            int columnIndexOrThrow2 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "query_id");
            int columnIndexOrThrow3 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "start_timestamp");
            int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "time_year_to_day");
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "cholesterol_integer_part");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "cholesterol_fractional_part");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "triglyceride_integer_part");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "triglyceride_fractional_part");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "high_lipoprotein_cholesterol_integer_part");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "high_lipoprotein_cholesterol_fractional_part");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "low_lipoprotein_cholesterol_integer_part");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "low_lipoprotein_cholesterol_fractional_part");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "measure_mode");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow15 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow16 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow17 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow18 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            int columnIndexOrThrow19 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_uploaded");
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
                int i10 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5);
                int i11 = columnIndexOrThrow3;
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
                int i23 = columnIndexOrThrow9;
                int i24 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12);
                int i25 = columnIndexOrThrow11;
                int i26 = i3;
                int i27 = columnIndexOrThrow10;
                int i28 = (int) sQLiteStatementPrepare.getLong(i26);
                int i29 = i2;
                if (sQLiteStatementPrepare.isNull(i29)) {
                    i6 = i26;
                    text2 = null;
                } else {
                    i6 = i26;
                    text2 = sQLiteStatementPrepare.getText(i29);
                }
                int i30 = columnIndexOrThrow15;
                if (sQLiteStatementPrepare.isNull(i30)) {
                    columnIndexOrThrow15 = i30;
                    text3 = null;
                } else {
                    columnIndexOrThrow15 = i30;
                    text3 = sQLiteStatementPrepare.getText(i30);
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
                String text5 = sQLiteStatementPrepare.isNull(i7) ? null : sQLiteStatementPrepare.getText(i7);
                columnIndexOrThrow17 = i7;
                int i32 = columnIndexOrThrow18;
                String str2 = text5;
                int i33 = columnIndexOrThrow12;
                if (((int) sQLiteStatementPrepare.getLong(i32)) != 0) {
                    i8 = columnIndexOrThrow19;
                    z = true;
                } else {
                    i8 = columnIndexOrThrow19;
                    z = false;
                }
                arrayList.add(new BloodLipids(lValueOf, i9, j3, text, i10, i12, i14, i16, i18, i20, i22, i24, i28, text2, text3, str, str2, z, ((int) sQLiteStatementPrepare.getLong(i8)) != 0));
                columnIndexOrThrow19 = i8;
                columnIndexOrThrow = i5;
                columnIndexOrThrow12 = i33;
                columnIndexOrThrow10 = i27;
                columnIndexOrThrow14 = i29;
                columnIndexOrThrow13 = i6;
                columnIndexOrThrow2 = i4;
                columnIndexOrThrow3 = i11;
                columnIndexOrThrow4 = i13;
                columnIndexOrThrow5 = i15;
                columnIndexOrThrow6 = i17;
                columnIndexOrThrow7 = i19;
                columnIndexOrThrow8 = i21;
                columnIndexOrThrow11 = i25;
                columnIndexOrThrow18 = i32;
                columnIndexOrThrow9 = i23;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.BloodLipidsDao
    public Object getByUserId(final String userId, final Continuation<? super List<BloodLipids>> arg1) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.BloodLipidsDao_Impl$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return BloodLipidsDao_Impl.lambda$getByUserId$7(userId, (SQLiteConnection) obj);
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
        int i6;
        boolean z;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("\n        SELECT * FROM blood_lipids_data \n        WHERE user_id = ? \n        OR user_id = \"\"\n        OR user_id IS NULL\n        ORDER BY start_timestamp DESC\n    ");
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
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "cholesterol_integer_part");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "cholesterol_fractional_part");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "triglyceride_integer_part");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "triglyceride_fractional_part");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "high_lipoprotein_cholesterol_integer_part");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "high_lipoprotein_cholesterol_fractional_part");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "low_lipoprotein_cholesterol_integer_part");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "low_lipoprotein_cholesterol_fractional_part");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "measure_mode");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow15 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow16 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow17 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow18 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            int columnIndexOrThrow19 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_uploaded");
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
                int i17 = columnIndexOrThrow8;
                int i18 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10);
                int i19 = columnIndexOrThrow9;
                int i20 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11);
                int i21 = columnIndexOrThrow10;
                int i22 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12);
                int i23 = columnIndexOrThrow12;
                int i24 = i3;
                int i25 = columnIndexOrThrow11;
                int i26 = (int) sQLiteStatementPrepare.getLong(i24);
                int i27 = i2;
                String text2 = sQLiteStatementPrepare.isNull(i27) ? null : sQLiteStatementPrepare.getText(i27);
                int i28 = columnIndexOrThrow;
                int i29 = columnIndexOrThrow15;
                String text3 = sQLiteStatementPrepare.isNull(i29) ? null : sQLiteStatementPrepare.getText(i29);
                int i30 = columnIndexOrThrow16;
                String text4 = sQLiteStatementPrepare.isNull(i30) ? null : sQLiteStatementPrepare.getText(i30);
                int i31 = columnIndexOrThrow17;
                String text5 = sQLiteStatementPrepare.isNull(i31) ? null : sQLiteStatementPrepare.getText(i31);
                int i32 = columnIndexOrThrow18;
                if (((int) sQLiteStatementPrepare.getLong(i32)) != 0) {
                    i6 = columnIndexOrThrow19;
                    z = true;
                } else {
                    i6 = columnIndexOrThrow19;
                    z = false;
                }
                arrayList.add(new BloodLipids(lValueOf, i7, j2, text, i8, i10, i12, i14, i16, i18, i20, i22, i26, text2, text3, text4, text5, z, ((int) sQLiteStatementPrepare.getLong(i6)) != 0));
                columnIndexOrThrow19 = i6;
                columnIndexOrThrow2 = i5;
                columnIndexOrThrow11 = i25;
                columnIndexOrThrow13 = i24;
                columnIndexOrThrow14 = i27;
                columnIndexOrThrow3 = i4;
                columnIndexOrThrow4 = i9;
                columnIndexOrThrow5 = i11;
                columnIndexOrThrow6 = i13;
                columnIndexOrThrow7 = i15;
                columnIndexOrThrow8 = i17;
                columnIndexOrThrow9 = i19;
                columnIndexOrThrow10 = i21;
                columnIndexOrThrow12 = i23;
                columnIndexOrThrow = i28;
                columnIndexOrThrow15 = i29;
                columnIndexOrThrow16 = i30;
                columnIndexOrThrow17 = i31;
                columnIndexOrThrow18 = i32;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.BloodLipidsDao
    public Object queryAll(final String userId, final Continuation<? super List<BloodLipids>> arg1) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.BloodLipidsDao_Impl$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return BloodLipidsDao_Impl.lambda$queryAll$8(userId, (SQLiteConnection) obj);
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
        int i6;
        boolean z;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("\n        SELECT * FROM blood_lipids_data \n        WHERE (user_id = ? \n        OR user_id = \"\"\n        OR user_id IS NULL)\n        ORDER BY start_timestamp DESC\n    ");
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
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "cholesterol_integer_part");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "cholesterol_fractional_part");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "triglyceride_integer_part");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "triglyceride_fractional_part");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "high_lipoprotein_cholesterol_integer_part");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "high_lipoprotein_cholesterol_fractional_part");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "low_lipoprotein_cholesterol_integer_part");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "low_lipoprotein_cholesterol_fractional_part");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "measure_mode");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow15 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow16 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow17 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow18 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            int columnIndexOrThrow19 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_uploaded");
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
                int i17 = columnIndexOrThrow8;
                int i18 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10);
                int i19 = columnIndexOrThrow9;
                int i20 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11);
                int i21 = columnIndexOrThrow10;
                int i22 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12);
                int i23 = columnIndexOrThrow12;
                int i24 = i3;
                int i25 = columnIndexOrThrow11;
                int i26 = (int) sQLiteStatementPrepare.getLong(i24);
                int i27 = i2;
                String text2 = sQLiteStatementPrepare.isNull(i27) ? null : sQLiteStatementPrepare.getText(i27);
                int i28 = columnIndexOrThrow;
                int i29 = columnIndexOrThrow15;
                String text3 = sQLiteStatementPrepare.isNull(i29) ? null : sQLiteStatementPrepare.getText(i29);
                int i30 = columnIndexOrThrow16;
                String text4 = sQLiteStatementPrepare.isNull(i30) ? null : sQLiteStatementPrepare.getText(i30);
                int i31 = columnIndexOrThrow17;
                String text5 = sQLiteStatementPrepare.isNull(i31) ? null : sQLiteStatementPrepare.getText(i31);
                int i32 = columnIndexOrThrow18;
                if (((int) sQLiteStatementPrepare.getLong(i32)) != 0) {
                    i6 = columnIndexOrThrow19;
                    z = true;
                } else {
                    i6 = columnIndexOrThrow19;
                    z = false;
                }
                arrayList.add(new BloodLipids(lValueOf, i7, j2, text, i8, i10, i12, i14, i16, i18, i20, i22, i26, text2, text3, text4, text5, z, ((int) sQLiteStatementPrepare.getLong(i6)) != 0));
                columnIndexOrThrow19 = i6;
                columnIndexOrThrow2 = i5;
                columnIndexOrThrow11 = i25;
                columnIndexOrThrow13 = i24;
                columnIndexOrThrow14 = i27;
                columnIndexOrThrow3 = i4;
                columnIndexOrThrow4 = i9;
                columnIndexOrThrow5 = i11;
                columnIndexOrThrow6 = i13;
                columnIndexOrThrow7 = i15;
                columnIndexOrThrow8 = i17;
                columnIndexOrThrow9 = i19;
                columnIndexOrThrow10 = i21;
                columnIndexOrThrow12 = i23;
                columnIndexOrThrow = i28;
                columnIndexOrThrow15 = i29;
                columnIndexOrThrow16 = i30;
                columnIndexOrThrow17 = i31;
                columnIndexOrThrow18 = i32;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.BloodLipidsDao
    public Object querySyncedWithYearToDay(final String yearToDay, final String userId, final boolean synced, final Continuation<? super List<BloodLipids>> arg3) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.BloodLipidsDao_Impl$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return BloodLipidsDao_Impl.lambda$querySyncedWithYearToDay$9(synced, yearToDay, userId, (SQLiteConnection) obj);
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
        String text2;
        int i5;
        String text3;
        int i6;
        boolean z2;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("\n        SELECT * FROM blood_lipids_data \n        WHERE is_uploaded = ?\n        AND time_year_to_day = ?\n        AND (user_id = ? \n        OR user_id = \"\"\n        OR user_id IS NULL)\n        ORDER BY start_timestamp DESC\n    ");
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
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "cholesterol_integer_part");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "cholesterol_fractional_part");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "triglyceride_integer_part");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "triglyceride_fractional_part");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "high_lipoprotein_cholesterol_integer_part");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "high_lipoprotein_cholesterol_fractional_part");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "low_lipoprotein_cholesterol_integer_part");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "low_lipoprotein_cholesterol_fractional_part");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "measure_mode");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow15 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow16 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow17 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow18 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            int columnIndexOrThrow19 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_uploaded");
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
                int i7 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
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
                int i8 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5);
                int i9 = i4;
                int i10 = columnIndexOrThrow3;
                int i11 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                int i12 = columnIndexOrThrow4;
                int i13 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7);
                int i14 = columnIndexOrThrow5;
                int i15 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow8);
                int i16 = columnIndexOrThrow6;
                int i17 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow9);
                int i18 = columnIndexOrThrow7;
                int i19 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10);
                int i20 = columnIndexOrThrow8;
                int i21 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11);
                int i22 = columnIndexOrThrow9;
                int i23 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12);
                int i24 = columnIndexOrThrow10;
                int i25 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow13);
                int i26 = i2;
                String text4 = sQLiteStatementPrepare.isNull(i26) ? null : sQLiteStatementPrepare.getText(i26);
                int i27 = columnIndexOrThrow15;
                if (sQLiteStatementPrepare.isNull(i27)) {
                    i5 = i27;
                    text2 = null;
                } else {
                    text2 = sQLiteStatementPrepare.getText(i27);
                    i5 = i27;
                }
                int i28 = columnIndexOrThrow16;
                if (sQLiteStatementPrepare.isNull(i28)) {
                    columnIndexOrThrow16 = i28;
                    text3 = null;
                } else {
                    columnIndexOrThrow16 = i28;
                    text3 = sQLiteStatementPrepare.getText(i28);
                }
                int i29 = columnIndexOrThrow17;
                String text5 = sQLiteStatementPrepare.isNull(i29) ? null : sQLiteStatementPrepare.getText(i29);
                columnIndexOrThrow17 = i29;
                int i30 = columnIndexOrThrow18;
                String str3 = text5;
                int i31 = columnIndexOrThrow11;
                if (((int) sQLiteStatementPrepare.getLong(i30)) != 0) {
                    i6 = columnIndexOrThrow19;
                    z2 = true;
                } else {
                    i6 = columnIndexOrThrow19;
                    z2 = false;
                }
                int i32 = columnIndexOrThrow12;
                BloodLipids bloodLipids = new BloodLipids(lValueOf, i7, j2, text, i8, i11, i13, i15, i17, i19, i21, i23, i25, text4, text2, text3, str3, z2, ((int) sQLiteStatementPrepare.getLong(i6)) != 0);
                ArrayList arrayList3 = arrayList;
                arrayList3.add(bloodLipids);
                columnIndexOrThrow3 = i10;
                arrayList2 = arrayList3;
                columnIndexOrThrow11 = i31;
                columnIndexOrThrow12 = i32;
                columnIndexOrThrow15 = i5;
                columnIndexOrThrow14 = i26;
                columnIndexOrThrow = i9;
                columnIndexOrThrow4 = i12;
                columnIndexOrThrow5 = i14;
                columnIndexOrThrow6 = i16;
                columnIndexOrThrow7 = i18;
                columnIndexOrThrow8 = i20;
                columnIndexOrThrow9 = i22;
                columnIndexOrThrow18 = i30;
                columnIndexOrThrow19 = i6;
                columnIndexOrThrow2 = i3;
                columnIndexOrThrow10 = i24;
            }
            return arrayList2;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.BloodLipidsDao
    public Object queryByYearToDay(final String yearToDay, final String userId, final Continuation<? super List<BloodLipids>> arg2) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.BloodLipidsDao_Impl$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return BloodLipidsDao_Impl.lambda$queryByYearToDay$10(yearToDay, userId, (SQLiteConnection) obj);
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
        int i6;
        String text2;
        int i7;
        String str3;
        int i8;
        boolean z;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("\n        SELECT * FROM blood_lipids_data \n        WHERE time_year_to_day = ?\n        AND (user_id = ? \n        OR user_id = \"\"\n        OR user_id IS NULL)\n        ORDER BY start_timestamp DESC\n    ");
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
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "cholesterol_integer_part");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "cholesterol_fractional_part");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "triglyceride_integer_part");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "triglyceride_fractional_part");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "high_lipoprotein_cholesterol_integer_part");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "high_lipoprotein_cholesterol_fractional_part");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "low_lipoprotein_cholesterol_integer_part");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "low_lipoprotein_cholesterol_fractional_part");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "measure_mode");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow15 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow16 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow17 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow18 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            int columnIndexOrThrow19 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_uploaded");
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
                int i10 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5);
                int i11 = columnIndexOrThrow3;
                int i12 = columnIndexOrThrow4;
                int i13 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                int i14 = columnIndexOrThrow5;
                int i15 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7);
                int i16 = columnIndexOrThrow6;
                int i17 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow8);
                int i18 = columnIndexOrThrow7;
                int i19 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow9);
                int i20 = columnIndexOrThrow8;
                int i21 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10);
                int i22 = columnIndexOrThrow9;
                int i23 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11);
                int i24 = columnIndexOrThrow10;
                int i25 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12);
                int i26 = columnIndexOrThrow12;
                int i27 = i3;
                int i28 = columnIndexOrThrow11;
                int i29 = (int) sQLiteStatementPrepare.getLong(i27);
                int i30 = i2;
                String text3 = sQLiteStatementPrepare.isNull(i30) ? null : sQLiteStatementPrepare.getText(i30);
                int i31 = columnIndexOrThrow15;
                if (sQLiteStatementPrepare.isNull(i31)) {
                    i6 = i31;
                    text2 = null;
                } else {
                    i6 = i31;
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
                String text5 = sQLiteStatementPrepare.isNull(i7) ? null : sQLiteStatementPrepare.getText(i7);
                columnIndexOrThrow17 = i7;
                int i33 = columnIndexOrThrow18;
                String str4 = text5;
                if (((int) sQLiteStatementPrepare.getLong(i33)) != 0) {
                    i8 = columnIndexOrThrow19;
                    z = true;
                } else {
                    i8 = columnIndexOrThrow19;
                    z = false;
                }
                arrayList.add(new BloodLipids(lValueOf, i9, j2, text, i10, i13, i15, i17, i19, i21, i23, i25, i29, text3, text2, str3, str4, z, ((int) sQLiteStatementPrepare.getLong(i8)) != 0));
                columnIndexOrThrow3 = i11;
                columnIndexOrThrow = i5;
                columnIndexOrThrow13 = i27;
                columnIndexOrThrow14 = i30;
                columnIndexOrThrow11 = i28;
                columnIndexOrThrow15 = i6;
                columnIndexOrThrow4 = i12;
                columnIndexOrThrow5 = i14;
                columnIndexOrThrow6 = i16;
                columnIndexOrThrow7 = i18;
                columnIndexOrThrow8 = i20;
                columnIndexOrThrow9 = i22;
                columnIndexOrThrow12 = i26;
                columnIndexOrThrow18 = i33;
                columnIndexOrThrow19 = i8;
                columnIndexOrThrow2 = i4;
                columnIndexOrThrow10 = i24;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.BloodLipidsDao
    public Object querySinceYearToDay(final String yearToDay, final String userId, final Continuation<? super List<BloodLipids>> arg2) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.BloodLipidsDao_Impl$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return BloodLipidsDao_Impl.lambda$querySinceYearToDay$11(yearToDay, userId, (SQLiteConnection) obj);
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
        int i6;
        String text2;
        int i7;
        String str3;
        int i8;
        boolean z;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("\n        SELECT * FROM blood_lipids_data \n        WHERE time_year_to_day >= ?\n        AND (user_id = ? OR user_id = \"\" OR user_id IS NULL)\n    ");
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
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "cholesterol_integer_part");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "cholesterol_fractional_part");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "triglyceride_integer_part");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "triglyceride_fractional_part");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "high_lipoprotein_cholesterol_integer_part");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "high_lipoprotein_cholesterol_fractional_part");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "low_lipoprotein_cholesterol_integer_part");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "low_lipoprotein_cholesterol_fractional_part");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "measure_mode");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow15 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow16 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow17 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow18 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            int columnIndexOrThrow19 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_uploaded");
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
                int i10 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5);
                int i11 = columnIndexOrThrow3;
                int i12 = columnIndexOrThrow4;
                int i13 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                int i14 = columnIndexOrThrow5;
                int i15 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7);
                int i16 = columnIndexOrThrow6;
                int i17 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow8);
                int i18 = columnIndexOrThrow7;
                int i19 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow9);
                int i20 = columnIndexOrThrow8;
                int i21 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10);
                int i22 = columnIndexOrThrow9;
                int i23 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11);
                int i24 = columnIndexOrThrow10;
                int i25 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12);
                int i26 = columnIndexOrThrow12;
                int i27 = i3;
                int i28 = columnIndexOrThrow11;
                int i29 = (int) sQLiteStatementPrepare.getLong(i27);
                int i30 = i2;
                String text3 = sQLiteStatementPrepare.isNull(i30) ? null : sQLiteStatementPrepare.getText(i30);
                int i31 = columnIndexOrThrow15;
                if (sQLiteStatementPrepare.isNull(i31)) {
                    i6 = i31;
                    text2 = null;
                } else {
                    i6 = i31;
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
                String text5 = sQLiteStatementPrepare.isNull(i7) ? null : sQLiteStatementPrepare.getText(i7);
                columnIndexOrThrow17 = i7;
                int i33 = columnIndexOrThrow18;
                String str4 = text5;
                if (((int) sQLiteStatementPrepare.getLong(i33)) != 0) {
                    i8 = columnIndexOrThrow19;
                    z = true;
                } else {
                    i8 = columnIndexOrThrow19;
                    z = false;
                }
                arrayList.add(new BloodLipids(lValueOf, i9, j2, text, i10, i13, i15, i17, i19, i21, i23, i25, i29, text3, text2, str3, str4, z, ((int) sQLiteStatementPrepare.getLong(i8)) != 0));
                columnIndexOrThrow3 = i11;
                columnIndexOrThrow = i5;
                columnIndexOrThrow13 = i27;
                columnIndexOrThrow14 = i30;
                columnIndexOrThrow11 = i28;
                columnIndexOrThrow15 = i6;
                columnIndexOrThrow4 = i12;
                columnIndexOrThrow5 = i14;
                columnIndexOrThrow6 = i16;
                columnIndexOrThrow7 = i18;
                columnIndexOrThrow8 = i20;
                columnIndexOrThrow9 = i22;
                columnIndexOrThrow12 = i26;
                columnIndexOrThrow18 = i33;
                columnIndexOrThrow19 = i8;
                columnIndexOrThrow2 = i4;
                columnIndexOrThrow10 = i24;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.BloodLipidsDao
    public Object getDataInTimeRange(final long startTime, final long endTime, final String userName, final Continuation<? super List<BloodLipids>> arg3) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.BloodLipidsDao_Impl$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return BloodLipidsDao_Impl.lambda$getDataInTimeRange$12(startTime, endTime, userName, (SQLiteConnection) obj);
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
        int i8;
        boolean z;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("\n        SELECT * FROM blood_lipids_data \n        WHERE start_timestamp BETWEEN ? AND ?\n        AND (user_id = ? OR user_id IS NULL OR user_id = '')\n    ");
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
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "cholesterol_integer_part");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "cholesterol_fractional_part");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "triglyceride_integer_part");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "triglyceride_fractional_part");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "high_lipoprotein_cholesterol_integer_part");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "high_lipoprotein_cholesterol_fractional_part");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "low_lipoprotein_cholesterol_integer_part");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "low_lipoprotein_cholesterol_fractional_part");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "measure_mode");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow15 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow16 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow17 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow18 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            int columnIndexOrThrow19 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_uploaded");
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
                String text4 = sQLiteStatementPrepare.isNull(i29) ? null : sQLiteStatementPrepare.getText(i29);
                int i30 = columnIndexOrThrow;
                int i31 = columnIndexOrThrow15;
                if (sQLiteStatementPrepare.isNull(i31)) {
                    i6 = i31;
                    text2 = null;
                } else {
                    i6 = i31;
                    text2 = sQLiteStatementPrepare.getText(i31);
                }
                int i32 = columnIndexOrThrow16;
                if (sQLiteStatementPrepare.isNull(i32)) {
                    columnIndexOrThrow16 = i32;
                    text3 = null;
                } else {
                    columnIndexOrThrow16 = i32;
                    text3 = sQLiteStatementPrepare.getText(i32);
                }
                int i33 = columnIndexOrThrow17;
                columnIndexOrThrow17 = i33;
                String text5 = sQLiteStatementPrepare.isNull(i33) ? null : sQLiteStatementPrepare.getText(i33);
                int i34 = columnIndexOrThrow18;
                if (((int) sQLiteStatementPrepare.getLong(i34)) != 0) {
                    i7 = columnIndexOrThrow19;
                    i8 = i29;
                    z = true;
                } else {
                    i7 = columnIndexOrThrow19;
                    i8 = i29;
                    z = false;
                }
                arrayList.add(new BloodLipids(lValueOf, i9, j4, text, i10, i12, i14, i16, i18, i20, i22, i24, i28, text4, text2, text3, text5, z, ((int) sQLiteStatementPrepare.getLong(i7)) != 0));
                columnIndexOrThrow19 = i7;
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
                columnIndexOrThrow10 = i23;
                columnIndexOrThrow12 = i25;
                columnIndexOrThrow14 = i8;
                columnIndexOrThrow18 = i34;
                columnIndexOrThrow = i30;
                columnIndexOrThrow15 = i6;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.BloodLipidsDao
    public Object markAsSynced(final List<Long> ids, final boolean synced, final Continuation<? super Integer> arg2) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE step_data SET is_uploaded = ? WHERE id IN (");
        StringUtil.appendPlaceholders(sb, ids.size());
        sb.append(")");
        final String string = sb.toString();
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.BloodLipidsDao_Impl$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return BloodLipidsDao_Impl.lambda$markAsSynced$13(string, synced, ids, (SQLiteConnection) obj);
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

    @Override // com.yucheng.smarthealthpro.database.room.dao.BloodLipidsDao
    public Object deleteById(final long id, final Continuation<? super Integer> arg1) {
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.BloodLipidsDao_Impl$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return BloodLipidsDao_Impl.lambda$deleteById$14(id, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    static /* synthetic */ Integer lambda$deleteById$14(long j2, SQLiteConnection sQLiteConnection) {
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("DELETE FROM blood_lipids_data WHERE id = ?");
        try {
            sQLiteStatementPrepare.mo181bindLong(1, j2);
            sQLiteStatementPrepare.step();
            return Integer.valueOf(SQLiteConnectionUtil.getTotalChangedRows(sQLiteConnection));
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.BloodLipidsDao
    public Object deleteAll(final Continuation<? super Integer> arg0) {
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.BloodLipidsDao_Impl$$ExternalSyntheticLambda16
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return BloodLipidsDao_Impl.lambda$deleteAll$15((SQLiteConnection) obj);
            }
        }, arg0);
    }

    static /* synthetic */ Integer lambda$deleteAll$15(SQLiteConnection sQLiteConnection) {
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("DELETE FROM blood_lipids_data");
        try {
            sQLiteStatementPrepare.step();
            return Integer.valueOf(SQLiteConnectionUtil.getTotalChangedRows(sQLiteConnection));
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.BloodLipidsDao
    public Object deleteAllByUser(final String userId, final Continuation<? super Integer> arg1) {
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.BloodLipidsDao_Impl$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return BloodLipidsDao_Impl.lambda$deleteAllByUser$16(userId, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    static /* synthetic */ Integer lambda$deleteAllByUser$16(String str, SQLiteConnection sQLiteConnection) {
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("DELETE FROM blood_lipids_data WHERE user_id = ?");
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
