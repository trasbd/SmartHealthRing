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
import com.yucheng.smarthealthpro.database.room.bean.Sleep;
import com.yucheng.smarthealthpro.database.room.bean.SleepItem;
import com.yucheng.smarthealthpro.database.room.convert.SleepItemListConvert;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;

/* loaded from: classes4.dex */
public final class SleepDao_Impl implements SleepDao {
    private final RoomDatabase __db;
    private final SleepItemListConvert __sleepItemListConvert = new SleepItemListConvert();
    private final EntityInsertAdapter<Sleep> __insertAdapterOfSleep = new EntityInsertAdapter<Sleep>() { // from class: com.yucheng.smarthealthpro.database.room.dao.SleepDao_Impl.1
        @Override // androidx.room.EntityInsertAdapter
        protected String createQuery() {
            return "INSERT OR REPLACE INTO `sleep_data` (`id`,`query_id`,`start_timestamp`,`end_timestamp`,`time_year_to_day`,`deep_sleep_count`,`light_sleep_count`,`deep_sleep_total_seconds`,`light_sleep_total_seconds`,`rem_total_seconds`,`wake_count`,`wake_duration_seconds`,`sleep_stages_json`,`user_id`,`device_type`,`device_mac_address`,`data_group_id`,`is_uploaded`,`is_other_uploaded`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // androidx.room.EntityInsertAdapter
        public void bind(SQLiteStatement sQLiteStatement, Sleep sleep) {
            if (sleep.getId() == null) {
                sQLiteStatement.mo182bindNull(1);
            } else {
                sQLiteStatement.mo181bindLong(1, sleep.getId().longValue());
            }
            sQLiteStatement.mo181bindLong(2, sleep.getQueryID());
            sQLiteStatement.mo181bindLong(3, sleep.getStartTime());
            sQLiteStatement.mo181bindLong(4, sleep.getEndTime());
            if (sleep.getTimeYearToDay() == null) {
                sQLiteStatement.mo182bindNull(5);
            } else {
                sQLiteStatement.mo183bindText(5, sleep.getTimeYearToDay());
            }
            sQLiteStatement.mo181bindLong(6, sleep.getDeepSleepCount());
            sQLiteStatement.mo181bindLong(7, sleep.getLightSleepCount());
            sQLiteStatement.mo181bindLong(8, sleep.getDeepSleepTotal());
            sQLiteStatement.mo181bindLong(9, sleep.getLightSleepTotal());
            sQLiteStatement.mo181bindLong(10, sleep.getRemTotal());
            sQLiteStatement.mo181bindLong(11, sleep.getWakeCount());
            sQLiteStatement.mo181bindLong(12, sleep.getWakeDuration());
            String strFromSleepItemList = SleepDao_Impl.this.__sleepItemListConvert.fromSleepItemList(sleep.getSleepItems());
            if (strFromSleepItemList == null) {
                sQLiteStatement.mo182bindNull(13);
            } else {
                sQLiteStatement.mo183bindText(13, strFromSleepItemList);
            }
            if (sleep.getUserId() == null) {
                sQLiteStatement.mo182bindNull(14);
            } else {
                sQLiteStatement.mo183bindText(14, sleep.getUserId());
            }
            if (sleep.getDeviceType() == null) {
                sQLiteStatement.mo182bindNull(15);
            } else {
                sQLiteStatement.mo183bindText(15, sleep.getDeviceType());
            }
            if (sleep.getDeviceMacAddress() == null) {
                sQLiteStatement.mo182bindNull(16);
            } else {
                sQLiteStatement.mo183bindText(16, sleep.getDeviceMacAddress());
            }
            if (sleep.getDataGroupId() == null) {
                sQLiteStatement.mo182bindNull(17);
            } else {
                sQLiteStatement.mo183bindText(17, sleep.getDataGroupId());
            }
            sQLiteStatement.mo181bindLong(18, sleep.isUploaded() ? 1L : 0L);
            sQLiteStatement.mo181bindLong(19, sleep.isOtherUploaded() ? 1L : 0L);
        }
    };
    private final EntityDeleteOrUpdateAdapter<Sleep> __updateAdapterOfSleep = new EntityDeleteOrUpdateAdapter<Sleep>() { // from class: com.yucheng.smarthealthpro.database.room.dao.SleepDao_Impl.2
        @Override // androidx.room.EntityDeleteOrUpdateAdapter
        protected String createQuery() {
            return "UPDATE OR ABORT `sleep_data` SET `id` = ?,`query_id` = ?,`start_timestamp` = ?,`end_timestamp` = ?,`time_year_to_day` = ?,`deep_sleep_count` = ?,`light_sleep_count` = ?,`deep_sleep_total_seconds` = ?,`light_sleep_total_seconds` = ?,`rem_total_seconds` = ?,`wake_count` = ?,`wake_duration_seconds` = ?,`sleep_stages_json` = ?,`user_id` = ?,`device_type` = ?,`device_mac_address` = ?,`data_group_id` = ?,`is_uploaded` = ?,`is_other_uploaded` = ? WHERE `id` = ?";
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // androidx.room.EntityDeleteOrUpdateAdapter
        public void bind(SQLiteStatement sQLiteStatement, Sleep sleep) {
            if (sleep.getId() == null) {
                sQLiteStatement.mo182bindNull(1);
            } else {
                sQLiteStatement.mo181bindLong(1, sleep.getId().longValue());
            }
            sQLiteStatement.mo181bindLong(2, sleep.getQueryID());
            sQLiteStatement.mo181bindLong(3, sleep.getStartTime());
            sQLiteStatement.mo181bindLong(4, sleep.getEndTime());
            if (sleep.getTimeYearToDay() == null) {
                sQLiteStatement.mo182bindNull(5);
            } else {
                sQLiteStatement.mo183bindText(5, sleep.getTimeYearToDay());
            }
            sQLiteStatement.mo181bindLong(6, sleep.getDeepSleepCount());
            sQLiteStatement.mo181bindLong(7, sleep.getLightSleepCount());
            sQLiteStatement.mo181bindLong(8, sleep.getDeepSleepTotal());
            sQLiteStatement.mo181bindLong(9, sleep.getLightSleepTotal());
            sQLiteStatement.mo181bindLong(10, sleep.getRemTotal());
            sQLiteStatement.mo181bindLong(11, sleep.getWakeCount());
            sQLiteStatement.mo181bindLong(12, sleep.getWakeDuration());
            String strFromSleepItemList = SleepDao_Impl.this.__sleepItemListConvert.fromSleepItemList(sleep.getSleepItems());
            if (strFromSleepItemList == null) {
                sQLiteStatement.mo182bindNull(13);
            } else {
                sQLiteStatement.mo183bindText(13, strFromSleepItemList);
            }
            if (sleep.getUserId() == null) {
                sQLiteStatement.mo182bindNull(14);
            } else {
                sQLiteStatement.mo183bindText(14, sleep.getUserId());
            }
            if (sleep.getDeviceType() == null) {
                sQLiteStatement.mo182bindNull(15);
            } else {
                sQLiteStatement.mo183bindText(15, sleep.getDeviceType());
            }
            if (sleep.getDeviceMacAddress() == null) {
                sQLiteStatement.mo182bindNull(16);
            } else {
                sQLiteStatement.mo183bindText(16, sleep.getDeviceMacAddress());
            }
            if (sleep.getDataGroupId() == null) {
                sQLiteStatement.mo182bindNull(17);
            } else {
                sQLiteStatement.mo183bindText(17, sleep.getDataGroupId());
            }
            sQLiteStatement.mo181bindLong(18, sleep.isUploaded() ? 1L : 0L);
            sQLiteStatement.mo181bindLong(19, sleep.isOtherUploaded() ? 1L : 0L);
            if (sleep.getId() == null) {
                sQLiteStatement.mo182bindNull(20);
            } else {
                sQLiteStatement.mo181bindLong(20, sleep.getId().longValue());
            }
        }
    };
    private final EntityDeleteOrUpdateAdapter<DataGroupIdUpdate> __updateAdapterOfDataGroupIdUpdateAsSleep = new EntityDeleteOrUpdateAdapter<DataGroupIdUpdate>() { // from class: com.yucheng.smarthealthpro.database.room.dao.SleepDao_Impl.3
        @Override // androidx.room.EntityDeleteOrUpdateAdapter
        protected String createQuery() {
            return "UPDATE OR ABORT `sleep_data` SET `id` = ?,`data_group_id` = ? WHERE `id` = ?";
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

    public SleepDao_Impl(final RoomDatabase __db) {
        this.__db = __db;
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.SleepDao
    public Object insert(final Sleep metric, final Continuation<? super Long> arg1) {
        metric.getClass();
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.SleepDao_Impl$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f$0.lambda$insert$0(metric, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Long lambda$insert$0(Sleep sleep, SQLiteConnection sQLiteConnection) {
        return Long.valueOf(this.__insertAdapterOfSleep.insertAndReturnId(sQLiteConnection, sleep));
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.SleepDao
    public Object insertAll(final List<Sleep> metrics, final Continuation<? super List<Long>> arg1) {
        metrics.getClass();
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.SleepDao_Impl$$ExternalSyntheticLambda16
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f$0.lambda$insertAll$1(metrics, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ List lambda$insertAll$1(List list, SQLiteConnection sQLiteConnection) {
        return this.__insertAdapterOfSleep.insertAndReturnIdsList(sQLiteConnection, list);
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.SleepDao
    public Object update(final Sleep metric, final Continuation<? super Unit> arg1) {
        metric.getClass();
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.SleepDao_Impl$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f$0.lambda$update$2(metric, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Unit lambda$update$2(Sleep sleep, SQLiteConnection sQLiteConnection) throws Exception {
        this.__updateAdapterOfSleep.handle(sQLiteConnection, sleep);
        return Unit.INSTANCE;
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.SleepDao
    public Object updateDataGroupIds(final List<DataGroupIdUpdate> updates, final Continuation<? super Unit> arg1) {
        updates.getClass();
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.SleepDao_Impl$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f$0.lambda$updateDataGroupIds$3(updates, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Unit lambda$updateDataGroupIds$3(List list, SQLiteConnection sQLiteConnection) throws Exception {
        this.__updateAdapterOfDataGroupIdUpdateAsSleep.handleMultiple(sQLiteConnection, list);
        return Unit.INSTANCE;
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.SleepDao
    public Object getById(final long id, final Continuation<? super Sleep> arg1) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.SleepDao_Impl$$ExternalSyntheticLambda13
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f$0.lambda$getById$4(id, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Sleep lambda$getById$4(long j2, SQLiteConnection sQLiteConnection) throws Throwable {
        String text;
        SleepDao_Impl sleepDao_Impl;
        String text2;
        int i2;
        String text3;
        int i3;
        String text4;
        int i4;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("SELECT * FROM sleep_data WHERE id = ?");
        try {
            sQLiteStatementPrepare.mo181bindLong(1, j2);
            int columnIndexOrThrow = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "id");
            int columnIndexOrThrow2 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "query_id");
            int columnIndexOrThrow3 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "start_timestamp");
            int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "end_timestamp");
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "time_year_to_day");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "deep_sleep_count");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "light_sleep_count");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "deep_sleep_total_seconds");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "light_sleep_total_seconds");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "rem_total_seconds");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "wake_count");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "wake_duration_seconds");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sleep_stages_json");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, AccessToken.USER_ID_KEY);
            int columnIndexOrThrow15 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_type");
            int columnIndexOrThrow16 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "device_mac_address");
            int columnIndexOrThrow17 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data_group_id");
            int columnIndexOrThrow18 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_uploaded");
            int columnIndexOrThrow19 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "is_other_uploaded");
            Sleep sleep = null;
            if (sQLiteStatementPrepare.step()) {
                Long lValueOf = sQLiteStatementPrepare.isNull(columnIndexOrThrow) ? null : Long.valueOf(sQLiteStatementPrepare.getLong(columnIndexOrThrow));
                int i5 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
                long j3 = sQLiteStatementPrepare.getLong(columnIndexOrThrow3);
                long j4 = sQLiteStatementPrepare.getLong(columnIndexOrThrow4);
                String text5 = sQLiteStatementPrepare.isNull(columnIndexOrThrow5) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow5);
                int i6 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                int i7 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7);
                int i8 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow8);
                int i9 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow9);
                int i10 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10);
                int i11 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11);
                int i12 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow13)) {
                    sleepDao_Impl = this;
                    text = null;
                } else {
                    text = sQLiteStatementPrepare.getText(columnIndexOrThrow13);
                    sleepDao_Impl = this;
                }
                try {
                    List<SleepItem> sleepItemList = sleepDao_Impl.__sleepItemListConvert.toSleepItemList(text);
                    if (sQLiteStatementPrepare.isNull(columnIndexOrThrow14)) {
                        i2 = columnIndexOrThrow15;
                        text2 = null;
                    } else {
                        text2 = sQLiteStatementPrepare.getText(columnIndexOrThrow14);
                        i2 = columnIndexOrThrow15;
                    }
                    if (sQLiteStatementPrepare.isNull(i2)) {
                        i3 = columnIndexOrThrow16;
                        text3 = null;
                    } else {
                        text3 = sQLiteStatementPrepare.getText(i2);
                        i3 = columnIndexOrThrow16;
                    }
                    if (sQLiteStatementPrepare.isNull(i3)) {
                        i4 = columnIndexOrThrow17;
                        text4 = null;
                    } else {
                        text4 = sQLiteStatementPrepare.getText(i3);
                        i4 = columnIndexOrThrow17;
                    }
                    sleep = new Sleep(lValueOf, i5, j3, j4, text5, i6, i7, i8, i9, i10, i11, i12, sleepItemList, text2, text3, text4, sQLiteStatementPrepare.isNull(i4) ? null : sQLiteStatementPrepare.getText(i4), ((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow18)) != 0, ((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow19)) != 0);
                } catch (Throwable th) {
                    th = th;
                    sQLiteStatementPrepare.close();
                    throw th;
                }
            }
            sQLiteStatementPrepare.close();
            return sleep;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.SleepDao
    public Object getByUser(final String userId, final Continuation<? super List<Sleep>> arg1) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.SleepDao_Impl$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f$0.lambda$getByUser$5(userId, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ List lambda$getByUser$5(String str, SQLiteConnection sQLiteConnection) {
        int i2;
        Long lValueOf;
        int i3;
        int i4;
        String text;
        int i5;
        int i6;
        int i7;
        String text2;
        int i8;
        String text3;
        int i9;
        int i10;
        int i11;
        String text4;
        String text5;
        int i12;
        int i13;
        boolean z;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("SELECT * FROM sleep_data WHERE user_id = ? ORDER BY start_timestamp DESC");
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
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "deep_sleep_count");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "light_sleep_count");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "deep_sleep_total_seconds");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "light_sleep_total_seconds");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "rem_total_seconds");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "wake_count");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "wake_duration_seconds");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sleep_stages_json");
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
                int i14 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
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
                int i15 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                int i16 = columnIndexOrThrow4;
                int i17 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7);
                int i18 = columnIndexOrThrow5;
                int i19 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow8);
                int i20 = columnIndexOrThrow6;
                int i21 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow9);
                int i22 = columnIndexOrThrow7;
                int i23 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10);
                int i24 = columnIndexOrThrow8;
                int i25 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11);
                int i26 = columnIndexOrThrow9;
                int i27 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12);
                int i28 = i3;
                if (sQLiteStatementPrepare.isNull(i28)) {
                    i6 = columnIndexOrThrow;
                    i8 = i28;
                    i7 = columnIndexOrThrow10;
                    text2 = null;
                } else {
                    i6 = columnIndexOrThrow;
                    i7 = columnIndexOrThrow10;
                    text2 = sQLiteStatementPrepare.getText(i28);
                    i8 = i28;
                }
                List<SleepItem> sleepItemList = this.__sleepItemListConvert.toSleepItemList(text2);
                int i29 = i2;
                if (sQLiteStatementPrepare.isNull(i29)) {
                    i9 = columnIndexOrThrow15;
                    text3 = null;
                } else {
                    text3 = sQLiteStatementPrepare.getText(i29);
                    i9 = columnIndexOrThrow15;
                }
                if (sQLiteStatementPrepare.isNull(i9)) {
                    i10 = i29;
                    i11 = columnIndexOrThrow16;
                    text4 = null;
                } else {
                    i10 = i29;
                    i11 = columnIndexOrThrow16;
                    text4 = sQLiteStatementPrepare.getText(i9);
                }
                if (sQLiteStatementPrepare.isNull(i11)) {
                    columnIndexOrThrow16 = i11;
                    i12 = columnIndexOrThrow17;
                    text5 = null;
                } else {
                    text5 = sQLiteStatementPrepare.getText(i11);
                    columnIndexOrThrow16 = i11;
                    i12 = columnIndexOrThrow17;
                }
                String text6 = sQLiteStatementPrepare.isNull(i12) ? null : sQLiteStatementPrepare.getText(i12);
                columnIndexOrThrow17 = i12;
                columnIndexOrThrow15 = i9;
                int i30 = columnIndexOrThrow18;
                String str2 = text6;
                int i31 = columnIndexOrThrow11;
                if (((int) sQLiteStatementPrepare.getLong(i30)) != 0) {
                    i13 = columnIndexOrThrow19;
                    z = true;
                } else {
                    i13 = columnIndexOrThrow19;
                    z = false;
                }
                arrayList.add(new Sleep(lValueOf, i14, j2, j3, text, i15, i17, i19, i21, i23, i25, i27, sleepItemList, text3, text4, text5, str2, z, ((int) sQLiteStatementPrepare.getLong(i13)) != 0));
                columnIndexOrThrow19 = i13;
                columnIndexOrThrow2 = i5;
                columnIndexOrThrow11 = i31;
                columnIndexOrThrow13 = i8;
                columnIndexOrThrow14 = i10;
                columnIndexOrThrow3 = i4;
                columnIndexOrThrow4 = i16;
                columnIndexOrThrow5 = i18;
                columnIndexOrThrow6 = i20;
                columnIndexOrThrow7 = i22;
                columnIndexOrThrow8 = i24;
                columnIndexOrThrow9 = i26;
                columnIndexOrThrow10 = i7;
                columnIndexOrThrow18 = i30;
                columnIndexOrThrow = i6;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.SleepDao
    public Object getByStartTimestamp(final long startTimestamp, final Continuation<? super List<Sleep>> arg1) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.SleepDao_Impl$$ExternalSyntheticLambda12
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f$0.lambda$getByStartTimestamp$6(startTimestamp, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ List lambda$getByStartTimestamp$6(long j2, SQLiteConnection sQLiteConnection) {
        int i2;
        Long lValueOf;
        int i3;
        int i4;
        String text;
        int i5;
        int i6;
        int i7;
        String text2;
        int i8;
        String text3;
        int i9;
        int i10;
        String text4;
        int i11;
        String str;
        int i12;
        boolean z;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("SELECT * FROM sleep_data WHERE start_timestamp = ?");
        try {
            sQLiteStatementPrepare.mo181bindLong(1, j2);
            int columnIndexOrThrow = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "id");
            int columnIndexOrThrow2 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "query_id");
            int columnIndexOrThrow3 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "start_timestamp");
            int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "end_timestamp");
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "time_year_to_day");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "deep_sleep_count");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "light_sleep_count");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "deep_sleep_total_seconds");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "light_sleep_total_seconds");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "rem_total_seconds");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "wake_count");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "wake_duration_seconds");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sleep_stages_json");
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
                int i13 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
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
                int i14 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                int i15 = columnIndexOrThrow3;
                int i16 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7);
                int i17 = columnIndexOrThrow4;
                int i18 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow8);
                int i19 = columnIndexOrThrow5;
                int i20 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow9);
                int i21 = columnIndexOrThrow6;
                int i22 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10);
                int i23 = columnIndexOrThrow7;
                int i24 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11);
                int i25 = columnIndexOrThrow8;
                int i26 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12);
                int i27 = i3;
                if (sQLiteStatementPrepare.isNull(i27)) {
                    i6 = i27;
                    i8 = columnIndexOrThrow9;
                    i7 = columnIndexOrThrow10;
                    text2 = null;
                } else {
                    i6 = i27;
                    i7 = columnIndexOrThrow10;
                    text2 = sQLiteStatementPrepare.getText(i27);
                    i8 = columnIndexOrThrow9;
                }
                List<SleepItem> sleepItemList = this.__sleepItemListConvert.toSleepItemList(text2);
                int i28 = i2;
                if (sQLiteStatementPrepare.isNull(i28)) {
                    i9 = columnIndexOrThrow15;
                    text3 = null;
                } else {
                    text3 = sQLiteStatementPrepare.getText(i28);
                    i9 = columnIndexOrThrow15;
                }
                if (sQLiteStatementPrepare.isNull(i9)) {
                    i10 = i28;
                    text4 = null;
                } else {
                    i10 = i28;
                    text4 = sQLiteStatementPrepare.getText(i9);
                }
                int i29 = columnIndexOrThrow16;
                if (sQLiteStatementPrepare.isNull(i29)) {
                    columnIndexOrThrow16 = i29;
                    i11 = columnIndexOrThrow17;
                    str = null;
                } else {
                    String text5 = sQLiteStatementPrepare.getText(i29);
                    columnIndexOrThrow16 = i29;
                    i11 = columnIndexOrThrow17;
                    str = text5;
                }
                String text6 = sQLiteStatementPrepare.isNull(i11) ? null : sQLiteStatementPrepare.getText(i11);
                columnIndexOrThrow17 = i11;
                columnIndexOrThrow15 = i9;
                int i30 = columnIndexOrThrow18;
                String str2 = text6;
                int i31 = columnIndexOrThrow11;
                if (((int) sQLiteStatementPrepare.getLong(i30)) != 0) {
                    i12 = columnIndexOrThrow19;
                    z = true;
                } else {
                    i12 = columnIndexOrThrow19;
                    z = false;
                }
                arrayList.add(new Sleep(lValueOf, i13, j3, j4, text, i14, i16, i18, i20, i22, i24, i26, sleepItemList, text3, text4, str, str2, z, ((int) sQLiteStatementPrepare.getLong(i12)) != 0));
                columnIndexOrThrow19 = i12;
                columnIndexOrThrow = i5;
                columnIndexOrThrow11 = i31;
                columnIndexOrThrow9 = i8;
                columnIndexOrThrow14 = i10;
                columnIndexOrThrow2 = i4;
                columnIndexOrThrow3 = i15;
                columnIndexOrThrow4 = i17;
                columnIndexOrThrow5 = i19;
                columnIndexOrThrow6 = i21;
                columnIndexOrThrow7 = i23;
                columnIndexOrThrow13 = i6;
                columnIndexOrThrow10 = i7;
                columnIndexOrThrow18 = i30;
                columnIndexOrThrow8 = i25;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.SleepDao
    public Object getByUserId(final String userId, final Continuation<? super List<Sleep>> arg1) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.SleepDao_Impl$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f$0.lambda$getByUserId$7(userId, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ List lambda$getByUserId$7(String str, SQLiteConnection sQLiteConnection) {
        int i2;
        Long lValueOf;
        int i3;
        int i4;
        String text;
        int i5;
        int i6;
        int i7;
        String text2;
        int i8;
        String text3;
        int i9;
        int i10;
        int i11;
        String text4;
        String text5;
        int i12;
        int i13;
        boolean z;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("\n        SELECT * FROM sleep_data \n        WHERE user_id = ? \n        OR user_id = \"\"\n        OR user_id IS NULL\n        ORDER BY start_timestamp DESC\n    ");
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
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "deep_sleep_count");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "light_sleep_count");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "deep_sleep_total_seconds");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "light_sleep_total_seconds");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "rem_total_seconds");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "wake_count");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "wake_duration_seconds");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sleep_stages_json");
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
                int i14 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
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
                int i15 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                int i16 = columnIndexOrThrow4;
                int i17 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7);
                int i18 = columnIndexOrThrow5;
                int i19 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow8);
                int i20 = columnIndexOrThrow6;
                int i21 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow9);
                int i22 = columnIndexOrThrow7;
                int i23 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10);
                int i24 = columnIndexOrThrow8;
                int i25 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11);
                int i26 = columnIndexOrThrow9;
                int i27 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12);
                int i28 = i3;
                if (sQLiteStatementPrepare.isNull(i28)) {
                    i6 = columnIndexOrThrow;
                    i8 = i28;
                    i7 = columnIndexOrThrow10;
                    text2 = null;
                } else {
                    i6 = columnIndexOrThrow;
                    i7 = columnIndexOrThrow10;
                    text2 = sQLiteStatementPrepare.getText(i28);
                    i8 = i28;
                }
                List<SleepItem> sleepItemList = this.__sleepItemListConvert.toSleepItemList(text2);
                int i29 = i2;
                if (sQLiteStatementPrepare.isNull(i29)) {
                    i9 = columnIndexOrThrow15;
                    text3 = null;
                } else {
                    text3 = sQLiteStatementPrepare.getText(i29);
                    i9 = columnIndexOrThrow15;
                }
                if (sQLiteStatementPrepare.isNull(i9)) {
                    i10 = i29;
                    i11 = columnIndexOrThrow16;
                    text4 = null;
                } else {
                    i10 = i29;
                    i11 = columnIndexOrThrow16;
                    text4 = sQLiteStatementPrepare.getText(i9);
                }
                if (sQLiteStatementPrepare.isNull(i11)) {
                    columnIndexOrThrow16 = i11;
                    i12 = columnIndexOrThrow17;
                    text5 = null;
                } else {
                    text5 = sQLiteStatementPrepare.getText(i11);
                    columnIndexOrThrow16 = i11;
                    i12 = columnIndexOrThrow17;
                }
                String text6 = sQLiteStatementPrepare.isNull(i12) ? null : sQLiteStatementPrepare.getText(i12);
                columnIndexOrThrow17 = i12;
                columnIndexOrThrow15 = i9;
                int i30 = columnIndexOrThrow18;
                String str2 = text6;
                int i31 = columnIndexOrThrow11;
                if (((int) sQLiteStatementPrepare.getLong(i30)) != 0) {
                    i13 = columnIndexOrThrow19;
                    z = true;
                } else {
                    i13 = columnIndexOrThrow19;
                    z = false;
                }
                arrayList.add(new Sleep(lValueOf, i14, j2, j3, text, i15, i17, i19, i21, i23, i25, i27, sleepItemList, text3, text4, text5, str2, z, ((int) sQLiteStatementPrepare.getLong(i13)) != 0));
                columnIndexOrThrow19 = i13;
                columnIndexOrThrow2 = i5;
                columnIndexOrThrow11 = i31;
                columnIndexOrThrow13 = i8;
                columnIndexOrThrow14 = i10;
                columnIndexOrThrow3 = i4;
                columnIndexOrThrow4 = i16;
                columnIndexOrThrow5 = i18;
                columnIndexOrThrow6 = i20;
                columnIndexOrThrow7 = i22;
                columnIndexOrThrow8 = i24;
                columnIndexOrThrow9 = i26;
                columnIndexOrThrow10 = i7;
                columnIndexOrThrow18 = i30;
                columnIndexOrThrow = i6;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.SleepDao
    public Object getByUploadAndUserId(final boolean isUpload, final String userId, final Continuation<? super List<Sleep>> arg2) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.SleepDao_Impl$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f$0.lambda$getByUploadAndUserId$8(isUpload, userId, (SQLiteConnection) obj);
            }
        }, arg2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ List lambda$getByUploadAndUserId$8(boolean z, String str, SQLiteConnection sQLiteConnection) {
        ArrayList arrayList;
        Long lValueOf;
        int i2;
        String text;
        int i3;
        String text2;
        int i4;
        int i5;
        String text3;
        int i6;
        String text4;
        int i7;
        int i8;
        String str2;
        int i9;
        boolean z2;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("\n        SELECT * FROM sleep_data \n        WHERE is_uploaded = ? \n        AND (user_id = ? \n        OR user_id = \"\"\n        OR user_id IS NULL)\n        ORDER BY start_timestamp DESC\n    ");
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
            int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "end_timestamp");
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "time_year_to_day");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "deep_sleep_count");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "light_sleep_count");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "deep_sleep_total_seconds");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "light_sleep_total_seconds");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "rem_total_seconds");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "wake_count");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "wake_duration_seconds");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sleep_stages_json");
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
                int i10 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
                long j2 = sQLiteStatementPrepare.getLong(columnIndexOrThrow3);
                long j3 = sQLiteStatementPrepare.getLong(columnIndexOrThrow4);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow5)) {
                    i3 = columnIndexOrThrow2;
                    text = null;
                } else {
                    text = sQLiteStatementPrepare.getText(columnIndexOrThrow5);
                    i3 = columnIndexOrThrow2;
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
                int i22 = columnIndexOrThrow8;
                int i23 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow13)) {
                    i4 = columnIndexOrThrow;
                    i5 = columnIndexOrThrow9;
                    text2 = null;
                } else {
                    text2 = sQLiteStatementPrepare.getText(columnIndexOrThrow13);
                    i4 = columnIndexOrThrow;
                    i5 = columnIndexOrThrow9;
                }
                List<SleepItem> sleepItemList = this.__sleepItemListConvert.toSleepItemList(text2);
                int i24 = i2;
                if (sQLiteStatementPrepare.isNull(i24)) {
                    i6 = columnIndexOrThrow15;
                    text3 = null;
                } else {
                    text3 = sQLiteStatementPrepare.getText(i24);
                    i6 = columnIndexOrThrow15;
                }
                if (sQLiteStatementPrepare.isNull(i6)) {
                    i7 = i24;
                    text4 = null;
                } else {
                    text4 = sQLiteStatementPrepare.getText(i6);
                    i7 = i24;
                }
                int i25 = columnIndexOrThrow16;
                if (sQLiteStatementPrepare.isNull(i25)) {
                    columnIndexOrThrow16 = i25;
                    i8 = columnIndexOrThrow17;
                    str2 = null;
                } else {
                    String text5 = sQLiteStatementPrepare.getText(i25);
                    columnIndexOrThrow16 = i25;
                    i8 = columnIndexOrThrow17;
                    str2 = text5;
                }
                String text6 = sQLiteStatementPrepare.isNull(i8) ? null : sQLiteStatementPrepare.getText(i8);
                columnIndexOrThrow17 = i8;
                columnIndexOrThrow15 = i6;
                int i26 = columnIndexOrThrow18;
                String str3 = text6;
                int i27 = columnIndexOrThrow10;
                if (((int) sQLiteStatementPrepare.getLong(i26)) != 0) {
                    i9 = columnIndexOrThrow19;
                    z2 = true;
                } else {
                    i9 = columnIndexOrThrow19;
                    z2 = false;
                }
                Sleep sleep = new Sleep(lValueOf, i10, j2, j3, text, i11, i13, i15, i17, i19, i21, i23, sleepItemList, text3, text4, str2, str3, z2, ((int) sQLiteStatementPrepare.getLong(i9)) != 0);
                ArrayList arrayList3 = arrayList;
                arrayList3.add(sleep);
                arrayList2 = arrayList3;
                columnIndexOrThrow19 = i9;
                columnIndexOrThrow10 = i27;
                columnIndexOrThrow14 = i7;
                columnIndexOrThrow2 = i3;
                columnIndexOrThrow3 = i12;
                columnIndexOrThrow4 = i14;
                columnIndexOrThrow5 = i16;
                columnIndexOrThrow6 = i18;
                columnIndexOrThrow7 = i20;
                columnIndexOrThrow8 = i22;
                columnIndexOrThrow9 = i5;
                columnIndexOrThrow18 = i26;
                columnIndexOrThrow = i4;
            }
            return arrayList2;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.SleepDao
    public Object querySyncedWithYearToDay(final String yearToDay, final String userId, final boolean synced, final Continuation<? super List<Sleep>> arg3) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.SleepDao_Impl$$ExternalSyntheticLambda17
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f$0.lambda$querySyncedWithYearToDay$9(synced, yearToDay, userId, (SQLiteConnection) obj);
            }
        }, arg3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ List lambda$querySyncedWithYearToDay$9(boolean z, String str, String str2, SQLiteConnection sQLiteConnection) {
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
        int i8;
        String text4;
        String text5;
        int i9;
        boolean z2;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("\n        SELECT * FROM sleep_data \n        WHERE is_uploaded = ?\n        AND time_year_to_day = ?\n        AND (user_id = ? \n        OR user_id = \"\"\n        OR user_id IS NULL)\n        ORDER BY start_timestamp DESC\n    ");
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
            int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "end_timestamp");
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "time_year_to_day");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "deep_sleep_count");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "light_sleep_count");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "deep_sleep_total_seconds");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "light_sleep_total_seconds");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "rem_total_seconds");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "wake_count");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "wake_duration_seconds");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sleep_stages_json");
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
                int i10 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
                long j2 = sQLiteStatementPrepare.getLong(columnIndexOrThrow3);
                long j3 = sQLiteStatementPrepare.getLong(columnIndexOrThrow4);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow5)) {
                    i4 = columnIndexOrThrow;
                    i3 = columnIndexOrThrow2;
                    text = null;
                } else {
                    i3 = columnIndexOrThrow2;
                    text = sQLiteStatementPrepare.getText(columnIndexOrThrow5);
                    i4 = columnIndexOrThrow;
                }
                int i11 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                int i12 = i4;
                int i13 = columnIndexOrThrow3;
                int i14 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7);
                int i15 = columnIndexOrThrow4;
                int i16 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow8);
                int i17 = columnIndexOrThrow5;
                int i18 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow9);
                int i19 = columnIndexOrThrow6;
                int i20 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10);
                int i21 = columnIndexOrThrow7;
                int i22 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11);
                int i23 = columnIndexOrThrow8;
                int i24 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12);
                if (sQLiteStatementPrepare.isNull(columnIndexOrThrow13)) {
                    i5 = i13;
                    i6 = columnIndexOrThrow9;
                    text2 = null;
                } else {
                    text2 = sQLiteStatementPrepare.getText(columnIndexOrThrow13);
                    i5 = i13;
                    i6 = columnIndexOrThrow9;
                }
                List<SleepItem> sleepItemList = this.__sleepItemListConvert.toSleepItemList(text2);
                int i25 = i2;
                if (sQLiteStatementPrepare.isNull(i25)) {
                    i7 = columnIndexOrThrow15;
                    text3 = null;
                } else {
                    text3 = sQLiteStatementPrepare.getText(i25);
                    i7 = columnIndexOrThrow15;
                }
                if (sQLiteStatementPrepare.isNull(i7)) {
                    i8 = columnIndexOrThrow16;
                    text4 = null;
                } else {
                    i8 = columnIndexOrThrow16;
                    text4 = sQLiteStatementPrepare.getText(i7);
                }
                if (sQLiteStatementPrepare.isNull(i8)) {
                    columnIndexOrThrow16 = i8;
                    text5 = null;
                } else {
                    columnIndexOrThrow16 = i8;
                    text5 = sQLiteStatementPrepare.getText(i8);
                }
                int i26 = columnIndexOrThrow17;
                String text6 = sQLiteStatementPrepare.isNull(i26) ? null : sQLiteStatementPrepare.getText(i26);
                columnIndexOrThrow17 = i26;
                columnIndexOrThrow15 = i7;
                int i27 = columnIndexOrThrow18;
                String str3 = text6;
                int i28 = columnIndexOrThrow10;
                if (((int) sQLiteStatementPrepare.getLong(i27)) != 0) {
                    i9 = columnIndexOrThrow19;
                    z2 = true;
                } else {
                    i9 = columnIndexOrThrow19;
                    z2 = false;
                }
                Sleep sleep = new Sleep(lValueOf, i10, j2, j3, text, i11, i14, i16, i18, i20, i22, i24, sleepItemList, text3, text4, text5, str3, z2, ((int) sQLiteStatementPrepare.getLong(i9)) != 0);
                ArrayList arrayList3 = arrayList;
                arrayList3.add(sleep);
                arrayList2 = arrayList3;
                columnIndexOrThrow19 = i9;
                columnIndexOrThrow10 = i28;
                columnIndexOrThrow14 = i25;
                columnIndexOrThrow = i12;
                columnIndexOrThrow4 = i15;
                columnIndexOrThrow5 = i17;
                columnIndexOrThrow6 = i19;
                columnIndexOrThrow7 = i21;
                columnIndexOrThrow8 = i23;
                columnIndexOrThrow3 = i5;
                columnIndexOrThrow9 = i6;
                columnIndexOrThrow18 = i27;
                columnIndexOrThrow2 = i3;
            }
            return arrayList2;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.SleepDao
    public Object queryAll(final String userId, final Continuation<? super List<Sleep>> arg1) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.SleepDao_Impl$$ExternalSyntheticLambda15
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f$0.lambda$queryAll$10(userId, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ List lambda$queryAll$10(String str, SQLiteConnection sQLiteConnection) {
        int i2;
        Long lValueOf;
        int i3;
        int i4;
        String text;
        int i5;
        int i6;
        int i7;
        String text2;
        int i8;
        String text3;
        int i9;
        int i10;
        int i11;
        String text4;
        String text5;
        int i12;
        int i13;
        boolean z;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("\n        SELECT * FROM sleep_data \n        WHERE (user_id = ? \n        OR user_id = \"\"\n        OR user_id IS NULL)\n        ORDER BY start_timestamp DESC\n    ");
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
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "deep_sleep_count");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "light_sleep_count");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "deep_sleep_total_seconds");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "light_sleep_total_seconds");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "rem_total_seconds");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "wake_count");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "wake_duration_seconds");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sleep_stages_json");
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
                int i14 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
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
                int i15 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                int i16 = columnIndexOrThrow4;
                int i17 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7);
                int i18 = columnIndexOrThrow5;
                int i19 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow8);
                int i20 = columnIndexOrThrow6;
                int i21 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow9);
                int i22 = columnIndexOrThrow7;
                int i23 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10);
                int i24 = columnIndexOrThrow8;
                int i25 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11);
                int i26 = columnIndexOrThrow9;
                int i27 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12);
                int i28 = i3;
                if (sQLiteStatementPrepare.isNull(i28)) {
                    i6 = columnIndexOrThrow;
                    i8 = i28;
                    i7 = columnIndexOrThrow10;
                    text2 = null;
                } else {
                    i6 = columnIndexOrThrow;
                    i7 = columnIndexOrThrow10;
                    text2 = sQLiteStatementPrepare.getText(i28);
                    i8 = i28;
                }
                List<SleepItem> sleepItemList = this.__sleepItemListConvert.toSleepItemList(text2);
                int i29 = i2;
                if (sQLiteStatementPrepare.isNull(i29)) {
                    i9 = columnIndexOrThrow15;
                    text3 = null;
                } else {
                    text3 = sQLiteStatementPrepare.getText(i29);
                    i9 = columnIndexOrThrow15;
                }
                if (sQLiteStatementPrepare.isNull(i9)) {
                    i10 = i29;
                    i11 = columnIndexOrThrow16;
                    text4 = null;
                } else {
                    i10 = i29;
                    i11 = columnIndexOrThrow16;
                    text4 = sQLiteStatementPrepare.getText(i9);
                }
                if (sQLiteStatementPrepare.isNull(i11)) {
                    columnIndexOrThrow16 = i11;
                    i12 = columnIndexOrThrow17;
                    text5 = null;
                } else {
                    text5 = sQLiteStatementPrepare.getText(i11);
                    columnIndexOrThrow16 = i11;
                    i12 = columnIndexOrThrow17;
                }
                String text6 = sQLiteStatementPrepare.isNull(i12) ? null : sQLiteStatementPrepare.getText(i12);
                columnIndexOrThrow17 = i12;
                columnIndexOrThrow15 = i9;
                int i30 = columnIndexOrThrow18;
                String str2 = text6;
                int i31 = columnIndexOrThrow11;
                if (((int) sQLiteStatementPrepare.getLong(i30)) != 0) {
                    i13 = columnIndexOrThrow19;
                    z = true;
                } else {
                    i13 = columnIndexOrThrow19;
                    z = false;
                }
                arrayList.add(new Sleep(lValueOf, i14, j2, j3, text, i15, i17, i19, i21, i23, i25, i27, sleepItemList, text3, text4, text5, str2, z, ((int) sQLiteStatementPrepare.getLong(i13)) != 0));
                columnIndexOrThrow19 = i13;
                columnIndexOrThrow2 = i5;
                columnIndexOrThrow11 = i31;
                columnIndexOrThrow13 = i8;
                columnIndexOrThrow14 = i10;
                columnIndexOrThrow3 = i4;
                columnIndexOrThrow4 = i16;
                columnIndexOrThrow5 = i18;
                columnIndexOrThrow6 = i20;
                columnIndexOrThrow7 = i22;
                columnIndexOrThrow8 = i24;
                columnIndexOrThrow9 = i26;
                columnIndexOrThrow10 = i7;
                columnIndexOrThrow18 = i30;
                columnIndexOrThrow = i6;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.SleepDao
    public Object queryByYearToDay(final String yearToDay, final String userId, final Continuation<? super List<Sleep>> arg2) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.SleepDao_Impl$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f$0.lambda$queryByYearToDay$11(yearToDay, userId, (SQLiteConnection) obj);
            }
        }, arg2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ List lambda$queryByYearToDay$11(String str, String str2, SQLiteConnection sQLiteConnection) {
        int i2;
        Long lValueOf;
        int i3;
        int i4;
        String text;
        int i5;
        int i6;
        int i7;
        String text2;
        int i8;
        String text3;
        int i9;
        int i10;
        String text4;
        int i11;
        String str3;
        int i12;
        boolean z;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("\n        SELECT * FROM sleep_data \n        WHERE time_year_to_day = ?\n        AND (user_id = ? \n        OR user_id = \"\"\n        OR user_id IS NULL)\n        ORDER BY start_timestamp DESC\n    ");
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
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "deep_sleep_count");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "light_sleep_count");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "deep_sleep_total_seconds");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "light_sleep_total_seconds");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "rem_total_seconds");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "wake_count");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "wake_duration_seconds");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sleep_stages_json");
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
                int i13 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
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
                int i14 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                int i15 = columnIndexOrThrow3;
                int i16 = columnIndexOrThrow4;
                int i17 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7);
                int i18 = columnIndexOrThrow5;
                int i19 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow8);
                int i20 = columnIndexOrThrow6;
                int i21 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow9);
                int i22 = columnIndexOrThrow7;
                int i23 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10);
                int i24 = columnIndexOrThrow8;
                int i25 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11);
                int i26 = columnIndexOrThrow9;
                int i27 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12);
                int i28 = i3;
                if (sQLiteStatementPrepare.isNull(i28)) {
                    i6 = i15;
                    i8 = i28;
                    i7 = columnIndexOrThrow10;
                    text2 = null;
                } else {
                    i6 = i15;
                    i7 = columnIndexOrThrow10;
                    text2 = sQLiteStatementPrepare.getText(i28);
                    i8 = i28;
                }
                List<SleepItem> sleepItemList = this.__sleepItemListConvert.toSleepItemList(text2);
                int i29 = i2;
                if (sQLiteStatementPrepare.isNull(i29)) {
                    i9 = columnIndexOrThrow15;
                    text3 = null;
                } else {
                    text3 = sQLiteStatementPrepare.getText(i29);
                    i9 = columnIndexOrThrow15;
                }
                if (sQLiteStatementPrepare.isNull(i9)) {
                    i10 = i29;
                    text4 = null;
                } else {
                    i10 = i29;
                    text4 = sQLiteStatementPrepare.getText(i9);
                }
                int i30 = columnIndexOrThrow16;
                if (sQLiteStatementPrepare.isNull(i30)) {
                    columnIndexOrThrow16 = i30;
                    i11 = columnIndexOrThrow17;
                    str3 = null;
                } else {
                    String text5 = sQLiteStatementPrepare.getText(i30);
                    columnIndexOrThrow16 = i30;
                    i11 = columnIndexOrThrow17;
                    str3 = text5;
                }
                String text6 = sQLiteStatementPrepare.isNull(i11) ? null : sQLiteStatementPrepare.getText(i11);
                columnIndexOrThrow17 = i11;
                columnIndexOrThrow15 = i9;
                int i31 = columnIndexOrThrow18;
                String str4 = text6;
                int i32 = columnIndexOrThrow11;
                if (((int) sQLiteStatementPrepare.getLong(i31)) != 0) {
                    i12 = columnIndexOrThrow19;
                    z = true;
                } else {
                    i12 = columnIndexOrThrow19;
                    z = false;
                }
                arrayList.add(new Sleep(lValueOf, i13, j2, j3, text, i14, i17, i19, i21, i23, i25, i27, sleepItemList, text3, text4, str3, str4, z, ((int) sQLiteStatementPrepare.getLong(i12)) != 0));
                columnIndexOrThrow19 = i12;
                columnIndexOrThrow = i5;
                columnIndexOrThrow11 = i32;
                columnIndexOrThrow13 = i8;
                columnIndexOrThrow14 = i10;
                columnIndexOrThrow4 = i16;
                columnIndexOrThrow5 = i18;
                columnIndexOrThrow6 = i20;
                columnIndexOrThrow7 = i22;
                columnIndexOrThrow8 = i24;
                columnIndexOrThrow9 = i26;
                columnIndexOrThrow3 = i6;
                columnIndexOrThrow10 = i7;
                columnIndexOrThrow18 = i31;
                columnIndexOrThrow2 = i4;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.SleepDao
    public Object querySinceYearToDay(final String yearToDay, final String userId, final Continuation<? super List<Sleep>> arg2) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.SleepDao_Impl$$ExternalSyntheticLambda14
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f$0.lambda$querySinceYearToDay$12(yearToDay, userId, (SQLiteConnection) obj);
            }
        }, arg2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ List lambda$querySinceYearToDay$12(String str, String str2, SQLiteConnection sQLiteConnection) {
        int i2;
        Long lValueOf;
        int i3;
        int i4;
        String text;
        int i5;
        int i6;
        int i7;
        String text2;
        int i8;
        String text3;
        int i9;
        int i10;
        String text4;
        int i11;
        String str3;
        int i12;
        boolean z;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("\n        SELECT * FROM sleep_data \n        WHERE time_year_to_day >= ?\n        AND (user_id = ? OR user_id = \"\" OR user_id IS NULL)\n        ORDER BY start_timestamp ASC\n    ");
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
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "deep_sleep_count");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "light_sleep_count");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "deep_sleep_total_seconds");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "light_sleep_total_seconds");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "rem_total_seconds");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "wake_count");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "wake_duration_seconds");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sleep_stages_json");
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
                int i13 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
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
                int i14 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6);
                int i15 = columnIndexOrThrow3;
                int i16 = columnIndexOrThrow4;
                int i17 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow7);
                int i18 = columnIndexOrThrow5;
                int i19 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow8);
                int i20 = columnIndexOrThrow6;
                int i21 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow9);
                int i22 = columnIndexOrThrow7;
                int i23 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10);
                int i24 = columnIndexOrThrow8;
                int i25 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11);
                int i26 = columnIndexOrThrow9;
                int i27 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow12);
                int i28 = i3;
                if (sQLiteStatementPrepare.isNull(i28)) {
                    i6 = i15;
                    i8 = i28;
                    i7 = columnIndexOrThrow10;
                    text2 = null;
                } else {
                    i6 = i15;
                    i7 = columnIndexOrThrow10;
                    text2 = sQLiteStatementPrepare.getText(i28);
                    i8 = i28;
                }
                List<SleepItem> sleepItemList = this.__sleepItemListConvert.toSleepItemList(text2);
                int i29 = i2;
                if (sQLiteStatementPrepare.isNull(i29)) {
                    i9 = columnIndexOrThrow15;
                    text3 = null;
                } else {
                    text3 = sQLiteStatementPrepare.getText(i29);
                    i9 = columnIndexOrThrow15;
                }
                if (sQLiteStatementPrepare.isNull(i9)) {
                    i10 = i29;
                    text4 = null;
                } else {
                    i10 = i29;
                    text4 = sQLiteStatementPrepare.getText(i9);
                }
                int i30 = columnIndexOrThrow16;
                if (sQLiteStatementPrepare.isNull(i30)) {
                    columnIndexOrThrow16 = i30;
                    i11 = columnIndexOrThrow17;
                    str3 = null;
                } else {
                    String text5 = sQLiteStatementPrepare.getText(i30);
                    columnIndexOrThrow16 = i30;
                    i11 = columnIndexOrThrow17;
                    str3 = text5;
                }
                String text6 = sQLiteStatementPrepare.isNull(i11) ? null : sQLiteStatementPrepare.getText(i11);
                columnIndexOrThrow17 = i11;
                columnIndexOrThrow15 = i9;
                int i31 = columnIndexOrThrow18;
                String str4 = text6;
                int i32 = columnIndexOrThrow11;
                if (((int) sQLiteStatementPrepare.getLong(i31)) != 0) {
                    i12 = columnIndexOrThrow19;
                    z = true;
                } else {
                    i12 = columnIndexOrThrow19;
                    z = false;
                }
                arrayList.add(new Sleep(lValueOf, i13, j2, j3, text, i14, i17, i19, i21, i23, i25, i27, sleepItemList, text3, text4, str3, str4, z, ((int) sQLiteStatementPrepare.getLong(i12)) != 0));
                columnIndexOrThrow19 = i12;
                columnIndexOrThrow = i5;
                columnIndexOrThrow11 = i32;
                columnIndexOrThrow13 = i8;
                columnIndexOrThrow14 = i10;
                columnIndexOrThrow4 = i16;
                columnIndexOrThrow5 = i18;
                columnIndexOrThrow6 = i20;
                columnIndexOrThrow7 = i22;
                columnIndexOrThrow8 = i24;
                columnIndexOrThrow9 = i26;
                columnIndexOrThrow3 = i6;
                columnIndexOrThrow10 = i7;
                columnIndexOrThrow18 = i31;
                columnIndexOrThrow2 = i4;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.SleepDao
    public Object getDataInTimeRange(final long startTime, final long endTime, final String userName, final Continuation<? super List<Sleep>> arg3) {
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.SleepDao_Impl$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f$0.lambda$getDataInTimeRange$13(startTime, endTime, userName, (SQLiteConnection) obj);
            }
        }, arg3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ List lambda$getDataInTimeRange$13(long j2, long j3, String str, SQLiteConnection sQLiteConnection) {
        int i2;
        Long lValueOf;
        int i3;
        int i4;
        String text;
        int i5;
        int i6;
        int i7;
        String text2;
        int i8;
        String text3;
        int i9;
        int i10;
        String text4;
        String text5;
        int i11;
        int i12;
        boolean z;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("\n        SELECT * FROM sleep_data \n        WHERE start_timestamp BETWEEN ? AND ?\n        AND (user_id = ? OR user_id IS NULL OR user_id = '')\n    ");
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
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "deep_sleep_count");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "light_sleep_count");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "deep_sleep_total_seconds");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "light_sleep_total_seconds");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "rem_total_seconds");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "wake_count");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "wake_duration_seconds");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sleep_stages_json");
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
                int i13 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
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
                int i27 = i3;
                if (sQLiteStatementPrepare.isNull(i27)) {
                    i6 = columnIndexOrThrow;
                    i8 = i27;
                    i7 = columnIndexOrThrow10;
                    text2 = null;
                } else {
                    i6 = columnIndexOrThrow;
                    i7 = columnIndexOrThrow10;
                    text2 = sQLiteStatementPrepare.getText(i27);
                    i8 = i27;
                }
                List<SleepItem> sleepItemList = this.__sleepItemListConvert.toSleepItemList(text2);
                int i28 = i2;
                if (sQLiteStatementPrepare.isNull(i28)) {
                    i9 = columnIndexOrThrow15;
                    text3 = null;
                } else {
                    text3 = sQLiteStatementPrepare.getText(i28);
                    i9 = columnIndexOrThrow15;
                }
                if (sQLiteStatementPrepare.isNull(i9)) {
                    i10 = i28;
                    text4 = null;
                } else {
                    i10 = i28;
                    text4 = sQLiteStatementPrepare.getText(i9);
                }
                int i29 = columnIndexOrThrow16;
                if (sQLiteStatementPrepare.isNull(i29)) {
                    columnIndexOrThrow16 = i29;
                    text5 = null;
                } else {
                    columnIndexOrThrow16 = i29;
                    text5 = sQLiteStatementPrepare.getText(i29);
                }
                int i30 = columnIndexOrThrow17;
                columnIndexOrThrow17 = i30;
                columnIndexOrThrow15 = i9;
                String text6 = sQLiteStatementPrepare.isNull(i30) ? null : sQLiteStatementPrepare.getText(i30);
                int i31 = columnIndexOrThrow18;
                if (((int) sQLiteStatementPrepare.getLong(i31)) != 0) {
                    i11 = columnIndexOrThrow19;
                    i12 = columnIndexOrThrow11;
                    z = true;
                } else {
                    i11 = columnIndexOrThrow19;
                    i12 = columnIndexOrThrow11;
                    z = false;
                }
                arrayList.add(new Sleep(lValueOf, i13, j4, j5, text, i14, i16, i18, i20, i22, i24, i26, sleepItemList, text3, text4, text5, text6, z, ((int) sQLiteStatementPrepare.getLong(i11)) != 0));
                columnIndexOrThrow11 = i12;
                columnIndexOrThrow18 = i31;
                columnIndexOrThrow19 = i11;
                columnIndexOrThrow2 = i5;
                columnIndexOrThrow13 = i8;
                columnIndexOrThrow14 = i10;
                columnIndexOrThrow3 = i4;
                columnIndexOrThrow4 = i15;
                columnIndexOrThrow5 = i17;
                columnIndexOrThrow6 = i19;
                columnIndexOrThrow7 = i21;
                columnIndexOrThrow8 = i23;
                columnIndexOrThrow9 = i25;
                columnIndexOrThrow = i6;
                columnIndexOrThrow10 = i7;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.SleepDao
    public Object markAsSynced(final List<Long> ids, final boolean synced, final Continuation<? super Integer> arg2) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE sleep_data SET is_uploaded = ? WHERE id IN (");
        StringUtil.appendPlaceholders(sb, ids.size());
        sb.append(")");
        final String string = sb.toString();
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.SleepDao_Impl$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SleepDao_Impl.lambda$markAsSynced$14(string, synced, ids, (SQLiteConnection) obj);
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

    @Override // com.yucheng.smarthealthpro.database.room.dao.SleepDao
    public Object deleteById(final long id, final Continuation<? super Integer> arg1) {
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.SleepDao_Impl$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SleepDao_Impl.lambda$deleteById$15(id, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    static /* synthetic */ Integer lambda$deleteById$15(long j2, SQLiteConnection sQLiteConnection) {
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("DELETE FROM sleep_data WHERE id = ?");
        try {
            sQLiteStatementPrepare.mo181bindLong(1, j2);
            sQLiteStatementPrepare.step();
            return Integer.valueOf(SQLiteConnectionUtil.getTotalChangedRows(sQLiteConnection));
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.SleepDao
    public Object deleteAll(final Continuation<? super Integer> arg0) {
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.SleepDao_Impl$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SleepDao_Impl.lambda$deleteAll$16((SQLiteConnection) obj);
            }
        }, arg0);
    }

    static /* synthetic */ Integer lambda$deleteAll$16(SQLiteConnection sQLiteConnection) {
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("DELETE FROM sleep_data");
        try {
            sQLiteStatementPrepare.step();
            return Integer.valueOf(SQLiteConnectionUtil.getTotalChangedRows(sQLiteConnection));
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.yucheng.smarthealthpro.database.room.dao.SleepDao
    public Object deleteAllByUser(final String userId, final Continuation<? super Integer> arg1) {
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.yucheng.smarthealthpro.database.room.dao.SleepDao_Impl$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SleepDao_Impl.lambda$deleteAllByUser$17(userId, (SQLiteConnection) obj);
            }
        }, arg1);
    }

    static /* synthetic */ Integer lambda$deleteAllByUser$17(String str, SQLiteConnection sQLiteConnection) {
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare("DELETE FROM sleep_data WHERE user_id = ?");
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
