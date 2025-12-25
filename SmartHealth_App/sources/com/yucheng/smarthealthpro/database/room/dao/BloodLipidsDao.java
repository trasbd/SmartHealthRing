package com.yucheng.smarthealthpro.database.room.dao;

import com.yucheng.smarthealthpro.database.room.bean.BloodLipids;
import com.yucheng.smarthealthpro.database.room.bean.DataGroupIdUpdate;
import com.yucheng.smarthealthpro.utils.Constant;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

/* compiled from: BloodLipidsDao.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0005\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H§@¢\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u0005H§@¢\u0006\u0002\u0010\u0006J\"\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\nH§@¢\u0006\u0002\u0010\fJ\u0018\u0010\r\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000e\u001a\u00020\u0003H§@¢\u0006\u0002\u0010\u000fJ\u001c\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00050\n2\u0006\u0010\u0011\u001a\u00020\u0012H§@¢\u0006\u0002\u0010\u0013J\u001c\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00050\n2\u0006\u0010\u0015\u001a\u00020\u0003H§@¢\u0006\u0002\u0010\u000fJ\u001c\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00050\n2\u0006\u0010\u0011\u001a\u00020\u0012H§@¢\u0006\u0002\u0010\u0013J\u001c\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00050\n2\u0006\u0010\u0011\u001a\u00020\u0012H§@¢\u0006\u0002\u0010\u0013J,\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00050\n2\u0006\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u001bH§@¢\u0006\u0002\u0010\u001cJ&\u0010\u001d\u001a\u00020\u001e2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00030\n2\b\b\u0002\u0010\u001a\u001a\u00020\u001bH§@¢\u0006\u0002\u0010 J$\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00050\n2\u0006\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u0012H§@¢\u0006\u0002\u0010\"J$\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00050\n2\u0006\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u0012H§@¢\u0006\u0002\u0010\"J,\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00050\n2\u0006\u0010%\u001a\u00020\u00032\u0006\u0010&\u001a\u00020\u00032\u0006\u0010'\u001a\u00020\u0012H§@¢\u0006\u0002\u0010(J\u001c\u0010)\u001a\u00020\b2\f\u0010*\u001a\b\u0012\u0004\u0012\u00020+0\nH§@¢\u0006\u0002\u0010\fJ\u0016\u0010,\u001a\u00020\u001e2\u0006\u0010\u000e\u001a\u00020\u0003H§@¢\u0006\u0002\u0010\u000fJ\u000e\u0010-\u001a\u00020\u001eH§@¢\u0006\u0002\u0010.J\u0016\u0010/\u001a\u00020\u001e2\u0006\u0010\u0011\u001a\u00020\u0012H§@¢\u0006\u0002\u0010\u0013¨\u00060"}, d2 = {"Lcom/yucheng/smarthealthpro/database/room/dao/BloodLipidsDao;", "", "insert", "", "metric", "Lcom/yucheng/smarthealthpro/database/room/bean/BloodLipids;", "(Lcom/yucheng/smarthealthpro/database/room/bean/BloodLipids;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "update", "", "insertAll", "", "metrics", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getById", "id", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getByUser", "userId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getByStartTimestamp", "startTimestamp", "getByUserId", "queryAll", "querySyncedWithYearToDay", "yearToDay", "synced", "", "(Ljava/lang/String;Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "markAsSynced", "", "ids", "(Ljava/util/List;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "queryByYearToDay", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "querySinceYearToDay", "getDataInTimeRange", "startTime", "endTime", Constant.SpConstKey.USER_NAME, "(JJLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateDataGroupIds", "updates", "Lcom/yucheng/smarthealthpro/database/room/bean/DataGroupIdUpdate;", "deleteById", "deleteAll", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteAllByUser", "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes4.dex */
public interface BloodLipidsDao {
    Object deleteAll(Continuation<? super Integer> continuation);

    Object deleteAllByUser(String str, Continuation<? super Integer> continuation);

    Object deleteById(long j2, Continuation<? super Integer> continuation);

    Object getById(long j2, Continuation<? super BloodLipids> continuation);

    Object getByStartTimestamp(long j2, Continuation<? super List<BloodLipids>> continuation);

    Object getByUser(String str, Continuation<? super List<BloodLipids>> continuation);

    Object getByUserId(String str, Continuation<? super List<BloodLipids>> continuation);

    Object getDataInTimeRange(long j2, long j3, String str, Continuation<? super List<BloodLipids>> continuation);

    Object insert(BloodLipids bloodLipids, Continuation<? super Long> continuation);

    Object insertAll(List<BloodLipids> list, Continuation<? super List<Long>> continuation);

    Object markAsSynced(List<Long> list, boolean z, Continuation<? super Integer> continuation);

    Object queryAll(String str, Continuation<? super List<BloodLipids>> continuation);

    Object queryByYearToDay(String str, String str2, Continuation<? super List<BloodLipids>> continuation);

    Object querySinceYearToDay(String str, String str2, Continuation<? super List<BloodLipids>> continuation);

    Object querySyncedWithYearToDay(String str, String str2, boolean z, Continuation<? super List<BloodLipids>> continuation);

    Object update(BloodLipids bloodLipids, Continuation<? super Unit> continuation);

    Object updateDataGroupIds(List<DataGroupIdUpdate> list, Continuation<? super Unit> continuation);

    /* compiled from: BloodLipidsDao.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public static final class DefaultImpls {
        public static /* synthetic */ Object markAsSynced$default(BloodLipidsDao bloodLipidsDao, List list, boolean z, Continuation continuation, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: markAsSynced");
            }
            if ((i2 & 2) != 0) {
                z = true;
            }
            return bloodLipidsDao.markAsSynced(list, z, continuation);
        }
    }
}
