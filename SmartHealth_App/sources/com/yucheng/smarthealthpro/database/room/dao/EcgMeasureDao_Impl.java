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
import com.yucheng.smarthealthpro.database.room.bean.EcgMeasure;
import com.yucheng.smarthealthpro.utils.Constant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;

/* loaded from: classes4.dex */
public final class EcgMeasureDao_Impl implements EcgMeasureDao {
    private final RoomDatabase __db;
    private final EntityInsertAdapter<EcgMeasure> __insertAdapterOfEcgMeasure = new EntityInsertAdapter<EcgMeasure>() { // from class: com.yucheng.smarthealthpro.database.room.dao.EcgMeasureDao_Impl.1
        @Override // androidx.room.EntityInsertAdapter
        protected String createQuery() {
            return "INSERT OR REPLACE INTO `ecg_measure_data` (`id`,`query_id`,`start_timestamp`,`time_year_to_day`,`hrv_value`,`heart_rate`,`max_bp`,`min_bp`,`measure_data`,`age`,`sex`,`is_afib`,`diagnose_type`,`health_norm`,`user_id`,`device_type`,`device_mac_address`,`data_group_id`,`is_uploaded`,`is_other_uploaded`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // androidx.room.EntityInsertAdapter
        public void bind(SQLiteStatement sQLiteStatement, EcgMeasure ecgMeasure) {
            if (ecgMeasure.getId() == null) {
                sQLiteStatement.mo182bindNull(1);
            } else {
                sQLiteStatement.mo181bindLong(1, ecgMeasure.getId().longValue());
            }
            sQLiteStatement.mo181bindLong(2, ecgMeasure.getQueryID());
            sQLiteStatement.mo181bindLong(3, ecgMeasure.getStartTimestamp());
            if (ecgMeasure.getTimeYearToDay() == null) {
                sQLiteStatement.mo182bindNull(4);
            } else {
                sQLiteStatement.mo183bindText(4, ecgMeasure.getTimeYearToDay());
            }
            sQLiteStatement.mo181bindLong(5, ecgMeasure.getHrv());
            sQLiteStatement.mo181bindLong(6, ecgMeasure.getHeartRate());
            sQLiteStatement.mo181bindLong(7, ecgMeasure.getMaxBp());
            sQLiteStatement.mo181bindLong(8, ecgMeasure.getMinBp());
            if (ecgMeasure.getMeasureData() == null) {
                sQLiteStatement.mo182bindNull(9);
            } else {
                sQLiteStatement.mo183bindText(9, ecgMeasure.getMeasureData());
            }
            sQLiteStatement.mo181bindLong(10, ecgMeasure.getAge());
            sQLiteStatement.mo181bindLong(11, ecgMeasure.getSex());
            sQLiteStatement.mo181bindLong(12, ecgMeasure.isAfib() ? 1L : 0L);
            sQLiteStatement.mo181bindLong(13, ecgMeasure.getDiagnoseType());
            if (ecgMeasure.getHealthNorm() == null) {
                sQLiteStatement.mo182bindNull(14);
            } else {
                sQLiteStatement.mo183bindText(14, ecgMeasure.getHealthNorm());
            }
            if (ecgMeasure.getUserId() == null) {
                sQLiteStatement.mo182bindNull(15);
            } else {
                sQLiteStatement.mo183bindText(15, ecgMeasure.getUserId());
            }
            if (ecgMeasure.getDeviceType() == null) {
                sQLiteStatement.mo182bindNull(16);
            } else {
                sQLiteStatement.mo183bindText(16, ecgMeasure.getDeviceType());
            }
            if (ecgMeasure.getDeviceMacAddress() == null) {
                sQLiteStatement.mo182bindNull(17);
            } else {
                sQLiteStatement.mo183bindText(17, ecgMeasure.getDeviceMacAddress());
            }
            if (ecgMeasure.getDataGroupId() == null) {
                sQLiteStatement.mo182bindNull(18);
            } else {
                sQLiteStatement.mo183bindText(18, ecgMeasure.getDataGroupId());
            }
            sQLiteStatement.mo181bindLong(19, ecgMeasure.isUploaded() ? 1L : 0L);
            sQLiteStatement.mo181bindLong(20, ecgMeasure.isOtherUploaded() ? 1L : 0L);
        }
    };
    private final EntityDeleteOrUpdateAdapter<EcgMeasure> __updateAdapterOfEcgMeasure = new EntityDeleteOrUpdateAdapter<EcgMeasure>() { // from class: com.yucheng.smarthealthpro.database.room.dao.EcgMeasureDao_Impl.2
        @Override // androidx.room.EntityDeleteOrUpdateAdapter
        protected String createQuery() {
            return "UPDATE OR ABORT `ecg_measure_data` SET `id` = ?,`query_id` = ?,`start_timestamp` = ?,`time_year_to_day` = ?,`hrv_value` = ?,`heart_rate` = ?,`max_bp` = ?,`min_bp` = ?,`measure_data` = ?,`age` = ?,`sex` = ?,`is_afib` = ?,`diagnose_type` = ?,`health_norm` = ?,`user_id` = ?,`device_type` = ?,`device_mac_address` = ?,`data_group_id` = ?,`is_uploaded` = ?,`is_other_uploaded` = ? WHERE `id` = ?";
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // androidx.room.EntityDeleteOrUpdateAdapter
        public void bind(SQLiteStatement sQLiteStatement, EcgMeasure ecgMeasure) {
            if (ecgMeasure.getId() == null) {
                sQLiteStatement.mo182bindNull(1);
            } else {
                sQLiteStatement.mo181bindLong(1, ecgMeasure.getId().longValue());
            }
            sQLiteStatement.mo181bindLong(2, ecgMeasure.getQueryID());
            sQLiteStatement.mo181bindLong(3, ecgMeasure.getStartTimestamp());
            if (ecgMeasure.getTimeYearToDay() == null) {
                sQLiteStatement.mo182bindNull(4);
            } else {
                sQLiteStatement.mo183bindText(4, ecgMeasure.getTimeYearToDay());
            }
            sQLiteStatement.mo181bindLong(5, ecgMeasure.getHrv());
            sQLiteStatement.mo181bindLong(6, ecgMeasure.getHeartRate());
            sQLiteStatement.mo181bindLong(7, ecgMeasure.getMaxBp());
            sQLiteStatement.mo181bindLong(8, ecgMeasure.getMinBp());
            if (ecgMeasure.getMeasureData() == null) {
                sQLiteStatement.mo182bindNull(9);
            } else {
                sQLiteStatement.mo183bindText(9, ecgMeasure.getMeasureData());
            }
            sQLiteStatement.mo181bindLong(10, ecgMeasure.getAge());
            sQLiteStatement.mo181bindLong(11, ecgMeasure.getSex());
            sQLiteStatement.mo181bindLong(12, ecgMeasure.isAfib() ? 1L : 0L);
            sQLiteStatement.mo181bindLong(13, ecgMeasure.getDiagnoseType());
            if (ecgMeasure.getHealthNorm() == null) {
                sQLiteStatement.mo182bindNull(14);
            } else {
                sQLiteStatement.mo183bindText(14, ecgMeasure.getHealthNorm());
            }
            if (ecgMeasure.getUserId() == null) {
                sQLiteStatement.mo182bindNull(15);
            } else {
                sQLiteStatement.mo183bindText(15, ecgMeasure.getUserId());
            }
            if (ecgMeasure.getDeviceType() == null) {
                sQLiteStatement.mo182bindNull(16);
            } else {
                sQLiteStatement.mo183bindText(16, ecgMeasure.getDeviceType());
            }
            if (ecgMeasure.getDeviceMacAddress() == null) {
                sQLiteStatement.mo182bindNull(17);
            } else {
                sQLiteStatement.mo183bindText(17, ecgMeasure.getDeviceMacAddress());
            }
            if (ecgMeasure.getDataGroupId() == null) {
                sQLiteStatement.mo182bindNull(18);
            } else {
                sQLiteStatement.mo183bindText(18, ecgMeasure.getDataGroupId());
            }
            sQLiteStatement.mo181bindLong(19, ecgMeasure.isUploaded() ? 1L : 0L);
            sQLiteStatement.mo181bindLong(20, ecgMeasure.isOtherUploaded() ? 1L : 0L);
            if (ecgMeasure.getId() == null) {
                sQLiteStatement.mo182bindNull(21);
            } else {
                sQLiteStatement.mo181bindLong(21, ecgMeasure.getId().longValue());
            }
        }
    };
    private final EntityDeleteOrUpdateAdapter<DataGroupIdUpdate> __updateAdapterOfDataGroupIdUpdateAsEcgMeasure = new EntityDeleteOrUpdateAdapter<DataGroupIdUpdate>() { // from class: com.yucheng.smarthealthpro.database.room.dao.EcgMeasureDao_Impl.3
        @Override // androidx.room.EntityDeleteOrUpdateAdapter
        protected String createQuery() {
            return "UPDATE OR ABORT `ecg_measure_data` SET `id` = ?,`data_group_id` = ? WHERE `id` = ?";
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

    public EcgMeasureDao_Impl(final RoomDatabase __db) {
        this.__db = __db;
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.EcgMeasureDao
    public Object insert(final EcgMeasure metric, final Continuation<? super Long> arg1) {
        metric.getClass();
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.EcgMeasureDao_Impl$$ExternalSyntheticLambda14
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f$0.lambda$insert$0(metric, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Long lambda$insert$0(EcgMeasure ecgMeasure, SQLiteConnection sQLiteConnection) {
        return Long.valueOf(this.__insertAdapterOfEcgMeasure.insertAndReturnId(sQLiteConnection, ecgMeasure));
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.EcgMeasureDao
    public Object insertAll(final List<EcgMeasure> metrics, final Continuation<? super List<Long>> arg1) {
        metrics.getClass();
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.EcgMeasureDao_Impl$$ExternalSyntheticLambda15
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f$0.lambda$insertAll$1(metrics, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ List lambda$insertAll$1(List list, SQLiteConnection sQLiteConnection) {
        return this.__insertAdapterOfEcgMeasure.insertAndReturnIdsList(sQLiteConnection, list);
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.EcgMeasureDao
    public Object update(final EcgMeasure metric, final Continuation<? super Unit> arg1) {
        metric.getClass();
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.EcgMeasureDao_Impl$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f$0.lambda$update$2(metric, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Unit lambda$update$2(EcgMeasure ecgMeasure, SQLiteConnection sQLiteConnection) throws Exception {
        this.__updateAdapterOfEcgMeasure.handle(sQLiteConnection, ecgMeasure);
        return Unit.INSTANCE;
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.EcgMeasureDao
    public Object updateDataGroupIds(final List<DataGroupIdUpdate> updates, final Continuation<? super Unit> arg1) {
        updates.getClass();
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.EcgMeasureDao_Impl$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f$0.lambda$updateDataGroupIds$3(updates, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Unit lambda$updateDataGroupIds$3(List list, SQLiteConnection sQLiteConnection) throws Exception {
        this.__updateAdapterOfDataGroupIdUpdateAsEcgMeasure.handleMultiple(sQLiteConnection, list);
        return Unit.INSTANCE;
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.EcgMeasureDao
    public Object getById(final long id, final Continuation<? super EcgMeasure> arg1) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.EcgMeasureDao_Impl$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return EcgMeasureDao_Impl.lambda$getById$4(id, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    static /* synthetic */ EcgMeasure lambda$getById$4(long j2, SQLiteConnection sQLiteConnection) {
        String text;
        int i2;
        String text2;
        int i3;
        String text3;
        int i4;
        String text4;
        int i5;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("SELECT * FROM ecg_measure_data WHERE id = ?");
        try {
            sQLiteStatementPrepare.mo181bindLong(1, j2);
            int columnIndexOrThrow = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "id");
            int columnIndexOrThrow2 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "query_id");
            int columnIndexOrThrow3 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "start_timestamp");
            int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "time_year_to_day");
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "hrv_value");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "heart_rate");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "max_bp");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "min_bp");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "measure_data");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, Constant.SpConstKey.AGE);
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, Constant.SpConstKey.SEX);
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_afib");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "diagnose_type");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "health_norm");
            int columnIndexOrThrow15 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow16 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow17 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow18 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow19 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            int columnIndexOrThrow20 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_uploaded");
            EcgMeasure ecgMeasure = null;
            if (sQLiteStatementPrepare.step()) {
                Long lValueOf = sQLiteStatementPrepare.isNull(columnIndexOrThrow) ? null : Long.valueOf(sQLiteStatementPrepare.getLong(columnIndexOrThrow));
                int i6 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
                long j3 = sQLiteStatementPrepare.getLong(columnIndexOrThrow3);
                String text5 = sQLiteStatementPrepare.isNull(columnIndexOrThrow4) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow4);
                int i7 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5);
                int i8 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                int i9 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7);
                int i10 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow8);
                String text6 = sQLiteStatementPrepare.isNull(columnIndexOrThrow9) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow9);
                int i11 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10);
                int i12 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11);
                boolean z = ((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12)) != 0;
                int i13 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow13);
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
                if (sQLiteStatementPrepare.isNull(i4)) {
                    i5 = columnIndexOrThrow18;
                    text4 = null;
                } else {
                    text4 = sQLiteStatementPrepare.getText(i4);
                    i5 = columnIndexOrThrow18;
                }
                ecgMeasure = new EcgMeasure(lValueOf, i6, j3, text5, i7, i8, i9, i10, text6, i11, i12, z, i13, text, text2, text3, text4, sQLiteStatementPrepare.isNull(i5) ? null : sQLiteStatementPrepare.getText(i5), ((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow19)) != 0, ((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow20)) != 0);
            }
            return ecgMeasure;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.EcgMeasureDao
    public Object getByUser(final String userId, final Continuation<? super List<EcgMeasure>> arg1) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.EcgMeasureDao_Impl$$ExternalSyntheticLambda16
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return EcgMeasureDao_Impl.lambda$getByUser$5(userId, (SQLiteConnection) obj);
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
        boolean z;
        int i8;
        boolean z2;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("SELECT * FROM ecg_measure_data WHERE user_id = ? ORDER BY start_timestamp DESC");
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
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "hrv_value");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "heart_rate");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "max_bp");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "min_bp");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "measure_data");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, Constant.SpConstKey.AGE);
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, Constant.SpConstKey.SEX);
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_afib");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "diagnose_type");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "health_norm");
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
                int i9 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
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
                int i10 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5);
                int i11 = columnIndexOrThrow4;
                int i12 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                int i13 = columnIndexOrThrow5;
                int i14 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7);
                int i15 = columnIndexOrThrow6;
                int i16 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow8);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow9)) {
                    i6 = columnIndexOrThrow7;
                    text2 = null;
                } else {
                    text2 = sQLiteStatementPrepare.getText(columnIndexOrThrow9);
                    i6 = columnIndexOrThrow7;
                }
                int i17 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10);
                int i18 = columnIndexOrThrow8;
                int i19 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11);
                int i20 = columnIndexOrThrow9;
                if (((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12)) != 0) {
                    i7 = i3;
                    z = true;
                } else {
                    i7 = i3;
                    z = false;
                }
                int i21 = columnIndexOrThrow10;
                int i22 = (int) sQLiteStatementPrepare.getLong(i7);
                int i23 = i2;
                String text3 = sQLiteStatementPrepare.isNull(i23) ? null : sQLiteStatementPrepare.getText(i23);
                int i24 = columnIndexOrThrow;
                int i25 = columnIndexOrThrow15;
                String text4 = sQLiteStatementPrepare.isNull(i25) ? null : sQLiteStatementPrepare.getText(i25);
                int i26 = columnIndexOrThrow16;
                String text5 = sQLiteStatementPrepare.isNull(i26) ? null : sQLiteStatementPrepare.getText(i26);
                int i27 = columnIndexOrThrow17;
                String text6 = sQLiteStatementPrepare.isNull(i27) ? null : sQLiteStatementPrepare.getText(i27);
                int i28 = columnIndexOrThrow18;
                String text7 = sQLiteStatementPrepare.isNull(i28) ? null : sQLiteStatementPrepare.getText(i28);
                int i29 = columnIndexOrThrow11;
                int i30 = columnIndexOrThrow19;
                if (((int) sQLiteStatementPrepare.getLong(i30)) != 0) {
                    i8 = columnIndexOrThrow20;
                    z2 = true;
                } else {
                    i8 = columnIndexOrThrow20;
                    z2 = false;
                }
                int i31 = columnIndexOrThrow12;
                arrayList.add(new EcgMeasure(lValueOf, i9, j2, text, i10, i12, i14, i16, text2, i17, i19, z, i22, text3, text4, text5, text6, text7, z2, ((int) sQLiteStatementPrepare.getLong(i8)) != 0));
                columnIndexOrThrow13 = i7;
                columnIndexOrThrow2 = i5;
                columnIndexOrThrow12 = i31;
                columnIndexOrThrow11 = i29;
                columnIndexOrThrow14 = i23;
                columnIndexOrThrow3 = i4;
                columnIndexOrThrow4 = i11;
                columnIndexOrThrow5 = i13;
                columnIndexOrThrow6 = i15;
                columnIndexOrThrow7 = i6;
                columnIndexOrThrow8 = i18;
                columnIndexOrThrow9 = i20;
                columnIndexOrThrow20 = i8;
                columnIndexOrThrow10 = i21;
                columnIndexOrThrow = i24;
                columnIndexOrThrow15 = i25;
                columnIndexOrThrow16 = i26;
                columnIndexOrThrow17 = i27;
                columnIndexOrThrow18 = i28;
                columnIndexOrThrow19 = i30;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.EcgMeasureDao
    public Object getByStartTimestamp(final long startTimestamp, final Continuation<? super List<EcgMeasure>> arg1) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.EcgMeasureDao_Impl$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return EcgMeasureDao_Impl.lambda$getByStartTimestamp$6(startTimestamp, (SQLiteConnection) obj);
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
        boolean z;
        int i8;
        String text3;
        String text4;
        int i9;
        String str;
        String text5;
        int i10;
        int i11;
        boolean z2;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("SELECT * FROM ecg_measure_data WHERE start_timestamp = ?");
        try {
            sQLiteStatementPrepare.mo181bindLong(1, j2);
            int columnIndexOrThrow = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "id");
            int columnIndexOrThrow2 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "query_id");
            int columnIndexOrThrow3 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "start_timestamp");
            int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "time_year_to_day");
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "hrv_value");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "heart_rate");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "max_bp");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "min_bp");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "measure_data");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, Constant.SpConstKey.AGE);
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, Constant.SpConstKey.SEX);
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_afib");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "diagnose_type");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "health_norm");
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
                int i12 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
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
                int i13 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5);
                int i14 = columnIndexOrThrow3;
                int i15 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                int i16 = columnIndexOrThrow4;
                int i17 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7);
                int i18 = columnIndexOrThrow5;
                int i19 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow8);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow9)) {
                    i6 = columnIndexOrThrow6;
                    text2 = null;
                } else {
                    text2 = sQLiteStatementPrepare.getText(columnIndexOrThrow9);
                    i6 = columnIndexOrThrow6;
                }
                int i20 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10);
                int i21 = columnIndexOrThrow7;
                int i22 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11);
                int i23 = columnIndexOrThrow8;
                if (((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12)) != 0) {
                    i7 = i3;
                    z = true;
                } else {
                    i7 = i3;
                    z = false;
                }
                int i24 = columnIndexOrThrow9;
                int i25 = (int) sQLiteStatementPrepare.getLong(i7);
                int i26 = i2;
                if (sQLiteStatementPrepare.isNull(i26)) {
                    i8 = i7;
                    text3 = null;
                } else {
                    i8 = i7;
                    text3 = sQLiteStatementPrepare.getText(i26);
                }
                int i27 = columnIndexOrThrow15;
                if (sQLiteStatementPrepare.isNull(i27)) {
                    columnIndexOrThrow15 = i27;
                    text4 = null;
                } else {
                    columnIndexOrThrow15 = i27;
                    text4 = sQLiteStatementPrepare.getText(i27);
                }
                int i28 = columnIndexOrThrow16;
                if (sQLiteStatementPrepare.isNull(i28)) {
                    columnIndexOrThrow16 = i28;
                    i9 = columnIndexOrThrow17;
                    str = null;
                } else {
                    String text6 = sQLiteStatementPrepare.getText(i28);
                    columnIndexOrThrow16 = i28;
                    i9 = columnIndexOrThrow17;
                    str = text6;
                }
                if (sQLiteStatementPrepare.isNull(i9)) {
                    columnIndexOrThrow17 = i9;
                    i10 = columnIndexOrThrow18;
                    text5 = null;
                } else {
                    text5 = sQLiteStatementPrepare.getText(i9);
                    columnIndexOrThrow17 = i9;
                    i10 = columnIndexOrThrow18;
                }
                String text7 = sQLiteStatementPrepare.isNull(i10) ? null : sQLiteStatementPrepare.getText(i10);
                columnIndexOrThrow18 = i10;
                int i29 = columnIndexOrThrow19;
                String str2 = text7;
                int i30 = columnIndexOrThrow10;
                if (((int) sQLiteStatementPrepare.getLong(i29)) != 0) {
                    i11 = columnIndexOrThrow20;
                    z2 = true;
                } else {
                    i11 = columnIndexOrThrow20;
                    z2 = false;
                }
                int i31 = columnIndexOrThrow11;
                arrayList.add(new EcgMeasure(lValueOf, i12, j3, text, i13, i15, i17, i19, text2, i20, i22, z, i25, text3, text4, str, text5, str2, z2, ((int) sQLiteStatementPrepare.getLong(i11)) != 0));
                columnIndexOrThrow = i5;
                columnIndexOrThrow10 = i30;
                columnIndexOrThrow11 = i31;
                columnIndexOrThrow14 = i26;
                columnIndexOrThrow13 = i8;
                columnIndexOrThrow2 = i4;
                columnIndexOrThrow3 = i14;
                columnIndexOrThrow4 = i16;
                columnIndexOrThrow5 = i18;
                columnIndexOrThrow6 = i6;
                columnIndexOrThrow8 = i23;
                columnIndexOrThrow19 = i29;
                columnIndexOrThrow20 = i11;
                columnIndexOrThrow9 = i24;
                columnIndexOrThrow7 = i21;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.EcgMeasureDao
    public Object getByUserId(final String userId, final Continuation<? super List<EcgMeasure>> arg1) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.EcgMeasureDao_Impl$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return EcgMeasureDao_Impl.lambda$getByUserId$7(userId, (SQLiteConnection) obj);
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
        boolean z;
        int i8;
        boolean z2;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("\n        SELECT * FROM ecg_measure_data \n        WHERE user_id = ? \n        OR user_id = \"\"\n        OR user_id IS NULL\n        ORDER BY start_timestamp DESC\n    ");
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
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "hrv_value");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "heart_rate");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "max_bp");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "min_bp");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "measure_data");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, Constant.SpConstKey.AGE);
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, Constant.SpConstKey.SEX);
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_afib");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "diagnose_type");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "health_norm");
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
                int i9 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
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
                int i10 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5);
                int i11 = columnIndexOrThrow4;
                int i12 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                int i13 = columnIndexOrThrow5;
                int i14 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7);
                int i15 = columnIndexOrThrow6;
                int i16 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow8);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow9)) {
                    i6 = columnIndexOrThrow7;
                    text2 = null;
                } else {
                    text2 = sQLiteStatementPrepare.getText(columnIndexOrThrow9);
                    i6 = columnIndexOrThrow7;
                }
                int i17 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10);
                int i18 = columnIndexOrThrow8;
                int i19 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11);
                int i20 = columnIndexOrThrow9;
                if (((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12)) != 0) {
                    i7 = i3;
                    z = true;
                } else {
                    i7 = i3;
                    z = false;
                }
                int i21 = columnIndexOrThrow10;
                int i22 = (int) sQLiteStatementPrepare.getLong(i7);
                int i23 = i2;
                String text3 = sQLiteStatementPrepare.isNull(i23) ? null : sQLiteStatementPrepare.getText(i23);
                int i24 = columnIndexOrThrow;
                int i25 = columnIndexOrThrow15;
                String text4 = sQLiteStatementPrepare.isNull(i25) ? null : sQLiteStatementPrepare.getText(i25);
                int i26 = columnIndexOrThrow16;
                String text5 = sQLiteStatementPrepare.isNull(i26) ? null : sQLiteStatementPrepare.getText(i26);
                int i27 = columnIndexOrThrow17;
                String text6 = sQLiteStatementPrepare.isNull(i27) ? null : sQLiteStatementPrepare.getText(i27);
                int i28 = columnIndexOrThrow18;
                String text7 = sQLiteStatementPrepare.isNull(i28) ? null : sQLiteStatementPrepare.getText(i28);
                int i29 = columnIndexOrThrow11;
                int i30 = columnIndexOrThrow19;
                if (((int) sQLiteStatementPrepare.getLong(i30)) != 0) {
                    i8 = columnIndexOrThrow20;
                    z2 = true;
                } else {
                    i8 = columnIndexOrThrow20;
                    z2 = false;
                }
                int i31 = columnIndexOrThrow12;
                arrayList.add(new EcgMeasure(lValueOf, i9, j2, text, i10, i12, i14, i16, text2, i17, i19, z, i22, text3, text4, text5, text6, text7, z2, ((int) sQLiteStatementPrepare.getLong(i8)) != 0));
                columnIndexOrThrow13 = i7;
                columnIndexOrThrow2 = i5;
                columnIndexOrThrow12 = i31;
                columnIndexOrThrow11 = i29;
                columnIndexOrThrow14 = i23;
                columnIndexOrThrow3 = i4;
                columnIndexOrThrow4 = i11;
                columnIndexOrThrow5 = i13;
                columnIndexOrThrow6 = i15;
                columnIndexOrThrow7 = i6;
                columnIndexOrThrow8 = i18;
                columnIndexOrThrow9 = i20;
                columnIndexOrThrow20 = i8;
                columnIndexOrThrow10 = i21;
                columnIndexOrThrow = i24;
                columnIndexOrThrow15 = i25;
                columnIndexOrThrow16 = i26;
                columnIndexOrThrow17 = i27;
                columnIndexOrThrow18 = i28;
                columnIndexOrThrow19 = i30;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.EcgMeasureDao
    public Object queryAll(final String userId, final Continuation<? super List<EcgMeasure>> arg1) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.EcgMeasureDao_Impl$$ExternalSyntheticLambda12
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return EcgMeasureDao_Impl.lambda$queryAll$8(userId, (SQLiteConnection) obj);
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
        boolean z;
        int i8;
        boolean z2;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("\n        SELECT * FROM ecg_measure_data \n        WHERE (user_id = ? \n        OR user_id = \"\"\n        OR user_id IS NULL)\n        ORDER BY start_timestamp DESC\n    ");
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
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "hrv_value");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "heart_rate");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "max_bp");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "min_bp");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "measure_data");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, Constant.SpConstKey.AGE);
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, Constant.SpConstKey.SEX);
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_afib");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "diagnose_type");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "health_norm");
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
                int i9 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
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
                int i10 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5);
                int i11 = columnIndexOrThrow4;
                int i12 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                int i13 = columnIndexOrThrow5;
                int i14 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7);
                int i15 = columnIndexOrThrow6;
                int i16 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow8);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow9)) {
                    i6 = columnIndexOrThrow7;
                    text2 = null;
                } else {
                    text2 = sQLiteStatementPrepare.getText(columnIndexOrThrow9);
                    i6 = columnIndexOrThrow7;
                }
                int i17 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10);
                int i18 = columnIndexOrThrow8;
                int i19 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11);
                int i20 = columnIndexOrThrow9;
                if (((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12)) != 0) {
                    i7 = i3;
                    z = true;
                } else {
                    i7 = i3;
                    z = false;
                }
                int i21 = columnIndexOrThrow10;
                int i22 = (int) sQLiteStatementPrepare.getLong(i7);
                int i23 = i2;
                String text3 = sQLiteStatementPrepare.isNull(i23) ? null : sQLiteStatementPrepare.getText(i23);
                int i24 = columnIndexOrThrow;
                int i25 = columnIndexOrThrow15;
                String text4 = sQLiteStatementPrepare.isNull(i25) ? null : sQLiteStatementPrepare.getText(i25);
                int i26 = columnIndexOrThrow16;
                String text5 = sQLiteStatementPrepare.isNull(i26) ? null : sQLiteStatementPrepare.getText(i26);
                int i27 = columnIndexOrThrow17;
                String text6 = sQLiteStatementPrepare.isNull(i27) ? null : sQLiteStatementPrepare.getText(i27);
                int i28 = columnIndexOrThrow18;
                String text7 = sQLiteStatementPrepare.isNull(i28) ? null : sQLiteStatementPrepare.getText(i28);
                int i29 = columnIndexOrThrow11;
                int i30 = columnIndexOrThrow19;
                if (((int) sQLiteStatementPrepare.getLong(i30)) != 0) {
                    i8 = columnIndexOrThrow20;
                    z2 = true;
                } else {
                    i8 = columnIndexOrThrow20;
                    z2 = false;
                }
                int i31 = columnIndexOrThrow12;
                arrayList.add(new EcgMeasure(lValueOf, i9, j2, text, i10, i12, i14, i16, text2, i17, i19, z, i22, text3, text4, text5, text6, text7, z2, ((int) sQLiteStatementPrepare.getLong(i8)) != 0));
                columnIndexOrThrow13 = i7;
                columnIndexOrThrow2 = i5;
                columnIndexOrThrow12 = i31;
                columnIndexOrThrow11 = i29;
                columnIndexOrThrow14 = i23;
                columnIndexOrThrow3 = i4;
                columnIndexOrThrow4 = i11;
                columnIndexOrThrow5 = i13;
                columnIndexOrThrow6 = i15;
                columnIndexOrThrow7 = i6;
                columnIndexOrThrow8 = i18;
                columnIndexOrThrow9 = i20;
                columnIndexOrThrow20 = i8;
                columnIndexOrThrow10 = i21;
                columnIndexOrThrow = i24;
                columnIndexOrThrow15 = i25;
                columnIndexOrThrow16 = i26;
                columnIndexOrThrow17 = i27;
                columnIndexOrThrow18 = i28;
                columnIndexOrThrow19 = i30;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.EcgMeasureDao
    public Object queryByStartTime(final long startTime, final String userId, final String deviceMac, final Continuation<? super List<EcgMeasure>> arg3) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.EcgMeasureDao_Impl$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return EcgMeasureDao_Impl.lambda$queryByStartTime$9(startTime, userId, deviceMac, (SQLiteConnection) obj);
            }
        }, arg3);
    }

    static /* synthetic */ List lambda$queryByStartTime$9(long j2, String str, String str2, SQLiteConnection sQLiteConnection) {
        int i2;
        Long lValueOf;
        int i3;
        int i4;
        String text;
        int i5;
        String text2;
        int i6;
        int i7;
        boolean z;
        int i8;
        String text3;
        String text4;
        String text5;
        int i9;
        boolean z2;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("\n        SELECT * FROM ecg_measure_data \n        WHERE start_timestamp = ?\n        AND (user_id = ? \n        OR user_id = \"\"\n        OR user_id IS NULL)\n        AND device_mac_address = ?\n    ");
        try {
            sQLiteStatementPrepare.mo181bindLong(1, j2);
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
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "hrv_value");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "heart_rate");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "max_bp");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "min_bp");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "measure_data");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, Constant.SpConstKey.AGE);
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, Constant.SpConstKey.SEX);
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_afib");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "diagnose_type");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "health_norm");
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
                int i13 = columnIndexOrThrow4;
                int i14 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                int i15 = columnIndexOrThrow5;
                int i16 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7);
                int i17 = columnIndexOrThrow6;
                int i18 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow8);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow9)) {
                    i6 = columnIndexOrThrow7;
                    text2 = null;
                } else {
                    text2 = sQLiteStatementPrepare.getText(columnIndexOrThrow9);
                    i6 = columnIndexOrThrow7;
                }
                int i19 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10);
                int i20 = columnIndexOrThrow8;
                int i21 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11);
                int i22 = columnIndexOrThrow9;
                if (((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12)) != 0) {
                    i7 = i3;
                    z = true;
                } else {
                    i7 = i3;
                    z = false;
                }
                int i23 = columnIndexOrThrow10;
                int i24 = (int) sQLiteStatementPrepare.getLong(i7);
                int i25 = i2;
                String text6 = sQLiteStatementPrepare.isNull(i25) ? null : sQLiteStatementPrepare.getText(i25);
                int i26 = columnIndexOrThrow15;
                if (sQLiteStatementPrepare.isNull(i26)) {
                    i8 = i26;
                    text3 = null;
                } else {
                    i8 = i26;
                    text3 = sQLiteStatementPrepare.getText(i26);
                }
                int i27 = columnIndexOrThrow16;
                if (sQLiteStatementPrepare.isNull(i27)) {
                    columnIndexOrThrow16 = i27;
                    text4 = null;
                } else {
                    columnIndexOrThrow16 = i27;
                    text4 = sQLiteStatementPrepare.getText(i27);
                }
                int i28 = columnIndexOrThrow17;
                if (sQLiteStatementPrepare.isNull(i28)) {
                    columnIndexOrThrow17 = i28;
                    text5 = null;
                } else {
                    columnIndexOrThrow17 = i28;
                    text5 = sQLiteStatementPrepare.getText(i28);
                }
                int i29 = columnIndexOrThrow18;
                String text7 = sQLiteStatementPrepare.isNull(i29) ? null : sQLiteStatementPrepare.getText(i29);
                columnIndexOrThrow18 = i29;
                int i30 = columnIndexOrThrow19;
                String str3 = text7;
                int i31 = columnIndexOrThrow11;
                if (((int) sQLiteStatementPrepare.getLong(i30)) != 0) {
                    i9 = columnIndexOrThrow20;
                    z2 = true;
                } else {
                    i9 = columnIndexOrThrow20;
                    z2 = false;
                }
                int i32 = columnIndexOrThrow12;
                arrayList.add(new EcgMeasure(lValueOf, i10, j3, text, i11, i14, i16, i18, text2, i19, i21, z, i24, text6, text3, text4, text5, str3, z2, ((int) sQLiteStatementPrepare.getLong(i9)) != 0));
                columnIndexOrThrow3 = i12;
                columnIndexOrThrow13 = i7;
                columnIndexOrThrow = i5;
                columnIndexOrThrow11 = i31;
                columnIndexOrThrow12 = i32;
                columnIndexOrThrow14 = i25;
                columnIndexOrThrow15 = i8;
                columnIndexOrThrow4 = i13;
                columnIndexOrThrow5 = i15;
                columnIndexOrThrow6 = i17;
                columnIndexOrThrow7 = i6;
                columnIndexOrThrow8 = i20;
                columnIndexOrThrow9 = i22;
                columnIndexOrThrow19 = i30;
                columnIndexOrThrow20 = i9;
                columnIndexOrThrow10 = i23;
                columnIndexOrThrow2 = i4;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.EcgMeasureDao
    public Object queryByYearToDay(final String yearToDay, final String userId, final Continuation<? super List<EcgMeasure>> arg2) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.EcgMeasureDao_Impl$$ExternalSyntheticLambda17
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return EcgMeasureDao_Impl.lambda$queryByYearToDay$10(yearToDay, userId, (SQLiteConnection) obj);
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
        boolean z;
        int i8;
        String text3;
        int i9;
        String str3;
        String text4;
        int i10;
        int i11;
        boolean z2;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("\n        SELECT * FROM ecg_measure_data \n        WHERE time_year_to_day = ?\n        AND (user_id = ? \n        OR user_id = \"\"\n        OR user_id IS NULL)\n        ORDER BY start_timestamp DESC\n    ");
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
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "hrv_value");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "heart_rate");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "max_bp");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "min_bp");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "measure_data");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, Constant.SpConstKey.AGE);
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, Constant.SpConstKey.SEX);
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_afib");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "diagnose_type");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "health_norm");
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
                int i12 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
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
                int i13 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5);
                int i14 = columnIndexOrThrow3;
                int i15 = columnIndexOrThrow4;
                int i16 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                int i17 = columnIndexOrThrow5;
                int i18 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7);
                int i19 = columnIndexOrThrow6;
                int i20 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow8);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow9)) {
                    i6 = columnIndexOrThrow7;
                    text2 = null;
                } else {
                    text2 = sQLiteStatementPrepare.getText(columnIndexOrThrow9);
                    i6 = columnIndexOrThrow7;
                }
                int i21 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10);
                int i22 = columnIndexOrThrow8;
                int i23 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11);
                int i24 = columnIndexOrThrow9;
                if (((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12)) != 0) {
                    i7 = i3;
                    z = true;
                } else {
                    i7 = i3;
                    z = false;
                }
                int i25 = columnIndexOrThrow10;
                int i26 = (int) sQLiteStatementPrepare.getLong(i7);
                int i27 = i2;
                String text5 = sQLiteStatementPrepare.isNull(i27) ? null : sQLiteStatementPrepare.getText(i27);
                int i28 = columnIndexOrThrow15;
                if (sQLiteStatementPrepare.isNull(i28)) {
                    i8 = i28;
                    text3 = null;
                } else {
                    i8 = i28;
                    text3 = sQLiteStatementPrepare.getText(i28);
                }
                int i29 = columnIndexOrThrow16;
                if (sQLiteStatementPrepare.isNull(i29)) {
                    columnIndexOrThrow16 = i29;
                    i9 = columnIndexOrThrow17;
                    str3 = null;
                } else {
                    String text6 = sQLiteStatementPrepare.getText(i29);
                    columnIndexOrThrow16 = i29;
                    i9 = columnIndexOrThrow17;
                    str3 = text6;
                }
                if (sQLiteStatementPrepare.isNull(i9)) {
                    columnIndexOrThrow17 = i9;
                    i10 = columnIndexOrThrow18;
                    text4 = null;
                } else {
                    text4 = sQLiteStatementPrepare.getText(i9);
                    columnIndexOrThrow17 = i9;
                    i10 = columnIndexOrThrow18;
                }
                String text7 = sQLiteStatementPrepare.isNull(i10) ? null : sQLiteStatementPrepare.getText(i10);
                columnIndexOrThrow18 = i10;
                int i30 = columnIndexOrThrow19;
                String str4 = text7;
                int i31 = columnIndexOrThrow11;
                if (((int) sQLiteStatementPrepare.getLong(i30)) != 0) {
                    i11 = columnIndexOrThrow20;
                    z2 = true;
                } else {
                    i11 = columnIndexOrThrow20;
                    z2 = false;
                }
                int i32 = columnIndexOrThrow12;
                arrayList.add(new EcgMeasure(lValueOf, i12, j2, text, i13, i16, i18, i20, text2, i21, i23, z, i26, text5, text3, str3, text4, str4, z2, ((int) sQLiteStatementPrepare.getLong(i11)) != 0));
                columnIndexOrThrow3 = i14;
                columnIndexOrThrow13 = i7;
                columnIndexOrThrow = i5;
                columnIndexOrThrow11 = i31;
                columnIndexOrThrow12 = i32;
                columnIndexOrThrow14 = i27;
                columnIndexOrThrow15 = i8;
                columnIndexOrThrow4 = i15;
                columnIndexOrThrow5 = i17;
                columnIndexOrThrow6 = i19;
                columnIndexOrThrow7 = i6;
                columnIndexOrThrow8 = i22;
                columnIndexOrThrow9 = i24;
                columnIndexOrThrow19 = i30;
                columnIndexOrThrow20 = i11;
                columnIndexOrThrow10 = i25;
                columnIndexOrThrow2 = i4;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.EcgMeasureDao
    public Object querySinceYearToDay(final String yearToDay, final String userId, final Continuation<? super List<EcgMeasure>> arg2) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.EcgMeasureDao_Impl$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return EcgMeasureDao_Impl.lambda$querySinceYearToDay$11(yearToDay, userId, (SQLiteConnection) obj);
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
        boolean z;
        int i8;
        String text3;
        int i9;
        String str3;
        String text4;
        int i10;
        int i11;
        boolean z2;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("\n        SELECT * FROM ecg_measure_data \n        WHERE time_year_to_day >= ?\n        AND (user_id = ? OR user_id = \"\" OR user_id IS NULL)\n    ");
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
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "hrv_value");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "heart_rate");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "max_bp");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "min_bp");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "measure_data");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, Constant.SpConstKey.AGE);
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, Constant.SpConstKey.SEX);
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_afib");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "diagnose_type");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "health_norm");
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
                int i12 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
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
                int i13 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5);
                int i14 = columnIndexOrThrow3;
                int i15 = columnIndexOrThrow4;
                int i16 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                int i17 = columnIndexOrThrow5;
                int i18 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7);
                int i19 = columnIndexOrThrow6;
                int i20 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow8);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow9)) {
                    i6 = columnIndexOrThrow7;
                    text2 = null;
                } else {
                    text2 = sQLiteStatementPrepare.getText(columnIndexOrThrow9);
                    i6 = columnIndexOrThrow7;
                }
                int i21 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10);
                int i22 = columnIndexOrThrow8;
                int i23 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11);
                int i24 = columnIndexOrThrow9;
                if (((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12)) != 0) {
                    i7 = i3;
                    z = true;
                } else {
                    i7 = i3;
                    z = false;
                }
                int i25 = columnIndexOrThrow10;
                int i26 = (int) sQLiteStatementPrepare.getLong(i7);
                int i27 = i2;
                String text5 = sQLiteStatementPrepare.isNull(i27) ? null : sQLiteStatementPrepare.getText(i27);
                int i28 = columnIndexOrThrow15;
                if (sQLiteStatementPrepare.isNull(i28)) {
                    i8 = i28;
                    text3 = null;
                } else {
                    i8 = i28;
                    text3 = sQLiteStatementPrepare.getText(i28);
                }
                int i29 = columnIndexOrThrow16;
                if (sQLiteStatementPrepare.isNull(i29)) {
                    columnIndexOrThrow16 = i29;
                    i9 = columnIndexOrThrow17;
                    str3 = null;
                } else {
                    String text6 = sQLiteStatementPrepare.getText(i29);
                    columnIndexOrThrow16 = i29;
                    i9 = columnIndexOrThrow17;
                    str3 = text6;
                }
                if (sQLiteStatementPrepare.isNull(i9)) {
                    columnIndexOrThrow17 = i9;
                    i10 = columnIndexOrThrow18;
                    text4 = null;
                } else {
                    text4 = sQLiteStatementPrepare.getText(i9);
                    columnIndexOrThrow17 = i9;
                    i10 = columnIndexOrThrow18;
                }
                String text7 = sQLiteStatementPrepare.isNull(i10) ? null : sQLiteStatementPrepare.getText(i10);
                columnIndexOrThrow18 = i10;
                int i30 = columnIndexOrThrow19;
                String str4 = text7;
                int i31 = columnIndexOrThrow11;
                if (((int) sQLiteStatementPrepare.getLong(i30)) != 0) {
                    i11 = columnIndexOrThrow20;
                    z2 = true;
                } else {
                    i11 = columnIndexOrThrow20;
                    z2 = false;
                }
                int i32 = columnIndexOrThrow12;
                arrayList.add(new EcgMeasure(lValueOf, i12, j2, text, i13, i16, i18, i20, text2, i21, i23, z, i26, text5, text3, str3, text4, str4, z2, ((int) sQLiteStatementPrepare.getLong(i11)) != 0));
                columnIndexOrThrow3 = i14;
                columnIndexOrThrow13 = i7;
                columnIndexOrThrow = i5;
                columnIndexOrThrow11 = i31;
                columnIndexOrThrow12 = i32;
                columnIndexOrThrow14 = i27;
                columnIndexOrThrow15 = i8;
                columnIndexOrThrow4 = i15;
                columnIndexOrThrow5 = i17;
                columnIndexOrThrow6 = i19;
                columnIndexOrThrow7 = i6;
                columnIndexOrThrow8 = i22;
                columnIndexOrThrow9 = i24;
                columnIndexOrThrow19 = i30;
                columnIndexOrThrow20 = i11;
                columnIndexOrThrow10 = i25;
                columnIndexOrThrow2 = i4;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.EcgMeasureDao
    public Object getDataInTimeRange(final long startTime, final long endTime, final String userName, final Continuation<? super List<EcgMeasure>> arg3) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.EcgMeasureDao_Impl$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return EcgMeasureDao_Impl.lambda$getDataInTimeRange$12(startTime, endTime, userName, (SQLiteConnection) obj);
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
        String text2;
        int i6;
        int i7;
        boolean z;
        int i8;
        String text3;
        String text4;
        String text5;
        int i9;
        boolean z2;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("\n        SELECT * FROM ecg_measure_data \n        WHERE start_timestamp BETWEEN ? AND ?\n        AND (user_id = ? OR user_id IS NULL OR user_id = '')\n    ");
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
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "hrv_value");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "heart_rate");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "max_bp");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "min_bp");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "measure_data");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, Constant.SpConstKey.AGE);
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, Constant.SpConstKey.SEX);
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_afib");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "diagnose_type");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "health_norm");
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
                int i11 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5);
                int i12 = columnIndexOrThrow4;
                int i13 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                int i14 = columnIndexOrThrow5;
                int i15 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7);
                int i16 = columnIndexOrThrow6;
                int i17 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow8);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow9)) {
                    i6 = columnIndexOrThrow7;
                    text2 = null;
                } else {
                    text2 = sQLiteStatementPrepare.getText(columnIndexOrThrow9);
                    i6 = columnIndexOrThrow7;
                }
                int i18 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10);
                int i19 = columnIndexOrThrow8;
                int i20 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11);
                int i21 = columnIndexOrThrow9;
                if (((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12)) != 0) {
                    i7 = i3;
                    z = true;
                } else {
                    i7 = i3;
                    z = false;
                }
                int i22 = columnIndexOrThrow10;
                int i23 = (int) sQLiteStatementPrepare.getLong(i7);
                int i24 = i2;
                String text6 = sQLiteStatementPrepare.isNull(i24) ? null : sQLiteStatementPrepare.getText(i24);
                int i25 = columnIndexOrThrow;
                int i26 = columnIndexOrThrow15;
                if (sQLiteStatementPrepare.isNull(i26)) {
                    i8 = i26;
                    text3 = null;
                } else {
                    i8 = i26;
                    text3 = sQLiteStatementPrepare.getText(i26);
                }
                int i27 = columnIndexOrThrow16;
                if (sQLiteStatementPrepare.isNull(i27)) {
                    columnIndexOrThrow16 = i27;
                    text4 = null;
                } else {
                    columnIndexOrThrow16 = i27;
                    text4 = sQLiteStatementPrepare.getText(i27);
                }
                int i28 = columnIndexOrThrow17;
                if (sQLiteStatementPrepare.isNull(i28)) {
                    columnIndexOrThrow17 = i28;
                    text5 = null;
                } else {
                    columnIndexOrThrow17 = i28;
                    text5 = sQLiteStatementPrepare.getText(i28);
                }
                int i29 = columnIndexOrThrow18;
                columnIndexOrThrow18 = i29;
                String text7 = sQLiteStatementPrepare.isNull(i29) ? null : sQLiteStatementPrepare.getText(i29);
                int i30 = columnIndexOrThrow19;
                int i31 = columnIndexOrThrow11;
                if (((int) sQLiteStatementPrepare.getLong(i30)) != 0) {
                    i9 = columnIndexOrThrow20;
                    z2 = true;
                } else {
                    i9 = columnIndexOrThrow20;
                    z2 = false;
                }
                int i32 = columnIndexOrThrow12;
                arrayList.add(new EcgMeasure(lValueOf, i10, j4, text, i11, i13, i15, i17, text2, i18, i20, z, i23, text6, text3, text4, text5, text7, z2, ((int) sQLiteStatementPrepare.getLong(i9)) != 0));
                columnIndexOrThrow11 = i31;
                columnIndexOrThrow19 = i30;
                columnIndexOrThrow13 = i7;
                columnIndexOrThrow2 = i5;
                columnIndexOrThrow12 = i32;
                columnIndexOrThrow14 = i24;
                columnIndexOrThrow3 = i4;
                columnIndexOrThrow4 = i12;
                columnIndexOrThrow5 = i14;
                columnIndexOrThrow6 = i16;
                columnIndexOrThrow7 = i6;
                columnIndexOrThrow8 = i19;
                columnIndexOrThrow9 = i21;
                columnIndexOrThrow = i25;
                columnIndexOrThrow20 = i9;
                columnIndexOrThrow10 = i22;
                columnIndexOrThrow15 = i8;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.EcgMeasureDao
    public Object querySyncedData(final String userId, final boolean synced, final Continuation<? super List<EcgMeasure>> arg2) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.EcgMeasureDao_Impl$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return EcgMeasureDao_Impl.lambda$querySyncedData$13(synced, userId, (SQLiteConnection) obj);
            }
        }, arg2);
    }

    static /* synthetic */ List lambda$querySyncedData$13(boolean z, String str, SQLiteConnection sQLiteConnection) {
        ArrayList arrayList;
        Long lValueOf;
        int i2;
        String text;
        int i3;
        String text2;
        int i4;
        String text3;
        int i5;
        int i6;
        String str2;
        String text4;
        int i7;
        int i8;
        boolean z2;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("\n        SELECT * FROM ecg_measure_data \n        WHERE is_uploaded = ?\n        AND (user_id = ? \n        OR user_id = \"\"\n        OR user_id IS NULL)\n        ORDER BY start_timestamp DESC\n    ");
        try {
            sQLiteStatementPrepare.mo181bindLong(1, z ? 1L : 0L);
            if (str == null) {
                sQLiteStatementPrepare.mo182bindNull(2);
            } else {
                sQLiteStatementPrepare.mo183bindText(2, str);
            }
            int columnIndexOrThrow = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "id");
            int columnIndexOrThrow2 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "query_id");
            int columnIndexOrThrow3 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "start_timestamp");
            int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "time_year_to_day");
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "hrv_value");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "heart_rate");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "max_bp");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "min_bp");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "measure_data");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, Constant.SpConstKey.AGE);
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, Constant.SpConstKey.SEX);
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_afib");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "diagnose_type");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "health_norm");
            int columnIndexOrThrow15 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow16 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow17 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow18 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow19 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            int columnIndexOrThrow20 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_uploaded");
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
                int i9 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
                long j2 = sQLiteStatementPrepare.getLong(columnIndexOrThrow3);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow4)) {
                    i3 = columnIndexOrThrow2;
                    text = null;
                } else {
                    text = sQLiteStatementPrepare.getText(columnIndexOrThrow4);
                    i3 = columnIndexOrThrow2;
                }
                int i10 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5);
                int i11 = columnIndexOrThrow3;
                int i12 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                int i13 = columnIndexOrThrow4;
                int i14 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7);
                int i15 = columnIndexOrThrow5;
                int i16 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow8);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow9)) {
                    i4 = columnIndexOrThrow6;
                    text2 = null;
                } else {
                    text2 = sQLiteStatementPrepare.getText(columnIndexOrThrow9);
                    i4 = columnIndexOrThrow6;
                }
                int i17 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10);
                int i18 = columnIndexOrThrow7;
                int i19 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11);
                int i20 = columnIndexOrThrow8;
                boolean z3 = ((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12)) != 0;
                int i21 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow13);
                int i22 = i2;
                String text5 = sQLiteStatementPrepare.isNull(i22) ? null : sQLiteStatementPrepare.getText(i22);
                int i23 = columnIndexOrThrow;
                int i24 = columnIndexOrThrow15;
                if (sQLiteStatementPrepare.isNull(i24)) {
                    i5 = i24;
                    text3 = null;
                } else {
                    text3 = sQLiteStatementPrepare.getText(i24);
                    i5 = i24;
                }
                int i25 = columnIndexOrThrow16;
                if (sQLiteStatementPrepare.isNull(i25)) {
                    columnIndexOrThrow16 = i25;
                    i6 = columnIndexOrThrow17;
                    str2 = null;
                } else {
                    String text6 = sQLiteStatementPrepare.getText(i25);
                    columnIndexOrThrow16 = i25;
                    i6 = columnIndexOrThrow17;
                    str2 = text6;
                }
                if (sQLiteStatementPrepare.isNull(i6)) {
                    columnIndexOrThrow17 = i6;
                    i7 = columnIndexOrThrow18;
                    text4 = null;
                } else {
                    text4 = sQLiteStatementPrepare.getText(i6);
                    columnIndexOrThrow17 = i6;
                    i7 = columnIndexOrThrow18;
                }
                String text7 = sQLiteStatementPrepare.isNull(i7) ? null : sQLiteStatementPrepare.getText(i7);
                columnIndexOrThrow18 = i7;
                int i26 = columnIndexOrThrow19;
                String str3 = text7;
                int i27 = columnIndexOrThrow9;
                if (((int) sQLiteStatementPrepare.getLong(i26)) != 0) {
                    i8 = columnIndexOrThrow20;
                    z2 = true;
                } else {
                    i8 = columnIndexOrThrow20;
                    z2 = false;
                }
                int i28 = columnIndexOrThrow10;
                EcgMeasure ecgMeasure = new EcgMeasure(lValueOf, i9, j2, text, i10, i12, i14, i16, text2, i17, i19, z3, i21, text5, text3, str2, text4, str3, z2, ((int) sQLiteStatementPrepare.getLong(i8)) != 0);
                ArrayList arrayList3 = arrayList;
                arrayList3.add(ecgMeasure);
                arrayList2 = arrayList3;
                columnIndexOrThrow9 = i27;
                columnIndexOrThrow10 = i28;
                columnIndexOrThrow14 = i22;
                columnIndexOrThrow2 = i3;
                columnIndexOrThrow3 = i11;
                columnIndexOrThrow4 = i13;
                columnIndexOrThrow5 = i15;
                columnIndexOrThrow6 = i4;
                columnIndexOrThrow7 = i18;
                columnIndexOrThrow19 = i26;
                columnIndexOrThrow20 = i8;
                columnIndexOrThrow8 = i20;
                columnIndexOrThrow = i23;
                columnIndexOrThrow15 = i5;
            }
            return arrayList2;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.EcgMeasureDao
    public Object markAsSynced(final List<Long> ids, final boolean synced, final Continuation<? super Integer> arg2) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ecg_measure_data SET is_uploaded = ? WHERE id IN (");
        StringUtil.appendPlaceholders(sb, ids.size());
        sb.append(")");
        final String string = sb.toString();
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.EcgMeasureDao_Impl$$ExternalSyntheticLambda18
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return EcgMeasureDao_Impl.lambda$markAsSynced$14(string, synced, ids, (SQLiteConnection) obj);
            }
        }, arg2);
    }

    static /* synthetic */ Integer lambda$markAsSynced$14(String str, boolean z, List list, SQLiteConnection sQLiteConnection) {
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

    @Override // com.yucheng.smarthealthpro.database.room.dao.EcgMeasureDao
    public Object updateUploaded(final long id, final boolean synced, final Continuation<? super Integer> arg2) {
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.EcgMeasureDao_Impl$$ExternalSyntheticLambda13
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return EcgMeasureDao_Impl.lambda$updateUploaded$15(synced, id, (SQLiteConnection) obj);
            }
        }, arg2);
    }

    static /* synthetic */ Integer lambda$updateUploaded$15(boolean z, long j2, SQLiteConnection sQLiteConnection) {
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("UPDATE ecg_measure_data SET is_uploaded = ? WHERE id = ?");
        try {
            sQLiteStatementPrepare.mo181bindLong(1, z ? 1L : 0L);
            sQLiteStatementPrepare.mo181bindLong(2, j2);
            sQLiteStatementPrepare.step();
            return Integer.valueOf(SQLiteConnectionUtil.getTotalChangedRows(sQLiteConnection));
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.EcgMeasureDao
    public Object deleteById(final long id, final Continuation<? super Integer> arg1) {
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.EcgMeasureDao_Impl$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return EcgMeasureDao_Impl.lambda$deleteById$16(id, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    static /* synthetic */ Integer lambda$deleteById$16(long j2, SQLiteConnection sQLiteConnection) {
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("DELETE FROM ecg_measure_data WHERE id = ?");
        try {
            sQLiteStatementPrepare.mo181bindLong(1, j2);
            sQLiteStatementPrepare.step();
            return Integer.valueOf(SQLiteConnectionUtil.getTotalChangedRows(sQLiteConnection));
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.EcgMeasureDao
    public Object deleteAll(final Continuation<? super Integer> arg0) {
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.EcgMeasureDao_Impl$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return EcgMeasureDao_Impl.lambda$deleteAll$17((SQLiteConnection) obj);
            }
        }, arg0);
    }

    static /* synthetic */ Integer lambda$deleteAll$17(SQLiteConnection sQLiteConnection) {
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("DELETE FROM ecg_measure_data");
        try {
            sQLiteStatementPrepare.step();
            return Integer.valueOf(SQLiteConnectionUtil.getTotalChangedRows(sQLiteConnection));
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.EcgMeasureDao
    public Object deleteAllByUser(final String userId, final Continuation<? super Integer> arg1) {
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.EcgMeasureDao_Impl$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return EcgMeasureDao_Impl.lambda$deleteAllByUser$18(userId, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    static /* synthetic */ Integer lambda$deleteAllByUser$18(String str, SQLiteConnection sQLiteConnection) {
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("DELETE FROM ecg_measure_data WHERE user_id = ?");
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
