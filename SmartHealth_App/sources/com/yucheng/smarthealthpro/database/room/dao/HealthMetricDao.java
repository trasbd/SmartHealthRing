package com.yucheng.smarthealthpro.database.room.dao;

import com.yucheng.smarthealthpro.database.room.bean.DataGroupIdUpdate;
import com.yucheng.smarthealthpro.database.room.bean.HealthMetric;
import com.yucheng.smarthealthpro.utils.Constant;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

/* compiled from: HealthMetricDao.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0005\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H§@¢\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u0005H§@¢\u0006\u0002\u0010\u0006J\"\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\nH§@¢\u0006\u0002\u0010\fJ\u0018\u0010\r\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000e\u001a\u00020\u0003H§@¢\u0006\u0002\u0010\u000fJ\u001c\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00050\n2\u0006\u0010\u0011\u001a\u00020\u0012H§@¢\u0006\u0002\u0010\u0013J\u001c\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00050\n2\u0006\u0010\u0015\u001a\u00020\u0003H§@¢\u0006\u0002\u0010\u000fJ\u001c\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00050\n2\u0006\u0010\u0011\u001a\u00020\u0012H§@¢\u0006\u0002\u0010\u0013J\u001c\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00050\n2\u0006\u0010\u0011\u001a\u00020\u0012H§@¢\u0006\u0002\u0010\u0013J$\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00050\n2\u0006\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u0012H§@¢\u0006\u0002\u0010\u001aJ$\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00050\n2\u0006\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u0012H§@¢\u0006\u0002\u0010\u001aJ,\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00050\n2\u0006\u0010\u001d\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u001f\u001a\u00020\u0012H§@¢\u0006\u0002\u0010 J4\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00050\n2\u0006\u0010\u001d\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u00032\u0006\u0010\"\u001a\u00020#2\u0006\u0010\u001f\u001a\u00020\u0012H§@¢\u0006\u0002\u0010$J,\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00050\n2\u0006\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010&\u001a\u00020#H§@¢\u0006\u0002\u0010'J&\u0010(\u001a\u00020)2\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00030\n2\b\b\u0002\u0010&\u001a\u00020#H§@¢\u0006\u0002\u0010+J,\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00050\n2\u0006\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010&\u001a\u00020#H§@¢\u0006\u0002\u0010'J&\u0010-\u001a\u00020)2\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00030\n2\b\b\u0002\u0010&\u001a\u00020#H§@¢\u0006\u0002\u0010+J,\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00050\n2\u0006\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010&\u001a\u00020#H§@¢\u0006\u0002\u0010'J&\u0010/\u001a\u00020)2\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00030\n2\b\b\u0002\u0010&\u001a\u00020#H§@¢\u0006\u0002\u0010+J,\u00100\u001a\b\u0012\u0004\u0012\u00020\u00050\n2\u0006\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010&\u001a\u00020#H§@¢\u0006\u0002\u0010'J&\u00101\u001a\u00020)2\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00030\n2\b\b\u0002\u0010&\u001a\u00020#H§@¢\u0006\u0002\u0010+J,\u00102\u001a\b\u0012\u0004\u0012\u00020\u00050\n2\u0006\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010&\u001a\u00020#H§@¢\u0006\u0002\u0010'J&\u00103\u001a\u00020)2\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00030\n2\b\b\u0002\u0010&\u001a\u00020#H§@¢\u0006\u0002\u0010+J\u001c\u00104\u001a\u00020\b2\f\u00105\u001a\b\u0012\u0004\u0012\u0002060\nH§@¢\u0006\u0002\u0010\fJ\u0016\u00107\u001a\u00020)2\u0006\u0010\u000e\u001a\u00020\u0003H§@¢\u0006\u0002\u0010\u000fJ\u000e\u00108\u001a\u00020)H§@¢\u0006\u0002\u00109J\u0016\u0010:\u001a\u00020)2\u0006\u0010\u0011\u001a\u00020\u0012H§@¢\u0006\u0002\u0010\u0013¨\u0006;"}, d2 = {"Lcom/yucheng/smarthealthpro/database/room/dao/HealthMetricDao;", "", "insert", "", "metric", "Lcom/yucheng/smarthealthpro/database/room/bean/HealthMetric;", "(Lcom/yucheng/smarthealthpro/database/room/bean/HealthMetric;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "update", "", "insertAll", "", "metrics", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getById", "id", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getByUser", "userId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getByStartTimestamp", "startTimestamp", "getByUserId", "queryAll", "queryByYearToDay", "yearToDay", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "querySinceYearToDay", "getMetricsInTimeRange", "startTime", "endTime", Constant.SpConstKey.USER_NAME, "(JJLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getBoUploadedWithTimeRange", "isUpload", "", "(JJZLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "queryBloodOxygenSyncedWithYearToDay", "synced", "(Ljava/lang/String;Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "markBloodOxygenAsSynced", "", "ids", "(Ljava/util/List;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "queryBloodSugarSyncedWithYearToDay", "markBloodSugarAsSynced", "queryHrvSyncedWithYearToDay", "markHrvAsSynced", "queryRespiratoryRateSyncedWithYearToDay", "markRespiratoryRateAsSynced", "queryTemperatureSyncedWithYearToDay", "markTemperatureAsSynced", "updateDataGroupIds", "updates", "Lcom/yucheng/smarthealthpro/database/room/bean/DataGroupIdUpdate;", "deleteById", "deleteAll", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteAllByUser", "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes4.dex */
public interface HealthMetricDao {
    Object deleteAll(Continuation<? super Integer> continuation);

    Object deleteAllByUser(String str, Continuation<? super Integer> continuation);

    Object deleteById(long j2, Continuation<? super Integer> continuation);

    Object getBoUploadedWithTimeRange(long j2, long j3, boolean z, String str, Continuation<? super List<HealthMetric>> continuation);

    Object getById(long j2, Continuation<? super HealthMetric> continuation);

    Object getByStartTimestamp(long j2, Continuation<? super List<HealthMetric>> continuation);

    Object getByUser(String str, Continuation<? super List<HealthMetric>> continuation);

    Object getByUserId(String str, Continuation<? super List<HealthMetric>> continuation);

    Object getMetricsInTimeRange(long j2, long j3, String str, Continuation<? super List<HealthMetric>> continuation);

    Object insert(HealthMetric healthMetric, Continuation<? super Long> continuation);

    Object insertAll(List<HealthMetric> list, Continuation<? super List<Long>> continuation);

    Object markBloodOxygenAsSynced(List<Long> list, boolean z, Continuation<? super Integer> continuation);

    Object markBloodSugarAsSynced(List<Long> list, boolean z, Continuation<? super Integer> continuation);

    Object markHrvAsSynced(List<Long> list, boolean z, Continuation<? super Integer> continuation);

    Object markRespiratoryRateAsSynced(List<Long> list, boolean z, Continuation<? super Integer> continuation);

    Object markTemperatureAsSynced(List<Long> list, boolean z, Continuation<? super Integer> continuation);

    Object queryAll(String str, Continuation<? super List<HealthMetric>> continuation);

    Object queryBloodOxygenSyncedWithYearToDay(String str, String str2, boolean z, Continuation<? super List<HealthMetric>> continuation);

    Object queryBloodSugarSyncedWithYearToDay(String str, String str2, boolean z, Continuation<? super List<HealthMetric>> continuation);

    Object queryByYearToDay(String str, String str2, Continuation<? super List<HealthMetric>> continuation);

    Object queryHrvSyncedWithYearToDay(String str, String str2, boolean z, Continuation<? super List<HealthMetric>> continuation);

    Object queryRespiratoryRateSyncedWithYearToDay(String str, String str2, boolean z, Continuation<? super List<HealthMetric>> continuation);

    Object querySinceYearToDay(String str, String str2, Continuation<? super List<HealthMetric>> continuation);

    Object queryTemperatureSyncedWithYearToDay(String str, String str2, boolean z, Continuation<? super List<HealthMetric>> continuation);

    Object update(HealthMetric healthMetric, Continuation<? super Unit> continuation);

    Object updateDataGroupIds(List<DataGroupIdUpdate> list, Continuation<? super Unit> continuation);

    /* compiled from: HealthMetricDao.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public static final class DefaultImpls {
        public static /* synthetic */ Object markBloodOxygenAsSynced$default(HealthMetricDao healthMetricDao, List list, boolean z, Continuation continuation, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: markBloodOxygenAsSynced");
            }
            if ((i2 & 2) != 0) {
                z = true;
            }
            return healthMetricDao.markBloodOxygenAsSynced(list, z, continuation);
        }

        public static /* synthetic */ Object markBloodSugarAsSynced$default(HealthMetricDao healthMetricDao, List list, boolean z, Continuation continuation, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: markBloodSugarAsSynced");
            }
            if ((i2 & 2) != 0) {
                z = true;
            }
            return healthMetricDao.markBloodSugarAsSynced(list, z, continuation);
        }

        public static /* synthetic */ Object markHrvAsSynced$default(HealthMetricDao healthMetricDao, List list, boolean z, Continuation continuation, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: markHrvAsSynced");
            }
            if ((i2 & 2) != 0) {
                z = true;
            }
            return healthMetricDao.markHrvAsSynced(list, z, continuation);
        }

        public static /* synthetic */ Object markRespiratoryRateAsSynced$default(HealthMetricDao healthMetricDao, List list, boolean z, Continuation continuation, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: markRespiratoryRateAsSynced");
            }
            if ((i2 & 2) != 0) {
                z = true;
            }
            return healthMetricDao.markRespiratoryRateAsSynced(list, z, continuation);
        }

        public static /* synthetic */ Object markTemperatureAsSynced$default(HealthMetricDao healthMetricDao, List list, boolean z, Continuation continuation, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: markTemperatureAsSynced");
            }
            if ((i2 & 2) != 0) {
                z = true;
            }
            return healthMetricDao.markTemperatureAsSynced(list, z, continuation);
        }
    }
}
