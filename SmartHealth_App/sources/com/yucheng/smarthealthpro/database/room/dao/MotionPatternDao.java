package com.yucheng.smarthealthpro.database.room.dao;

import com.yucheng.smarthealthpro.database.room.bean.DataGroupIdUpdate;
import com.yucheng.smarthealthpro.database.room.bean.MotionPattern;
import com.yucheng.smarthealthpro.utils.Constant;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

/* compiled from: MotionPatternDao.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H§@¢\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u0005H§@¢\u0006\u0002\u0010\u0006J\"\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\nH§@¢\u0006\u0002\u0010\fJ\u0018\u0010\r\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000e\u001a\u00020\u0003H§@¢\u0006\u0002\u0010\u000fJ\u001c\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00050\n2\u0006\u0010\u0011\u001a\u00020\u0012H§@¢\u0006\u0002\u0010\u0013J\u001c\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00050\n2\u0006\u0010\u0015\u001a\u00020\u0003H§@¢\u0006\u0002\u0010\u000fJ\u001c\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00050\n2\u0006\u0010\u0011\u001a\u00020\u0012H§@¢\u0006\u0002\u0010\u0013J\u001c\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00050\n2\u0006\u0010\u0011\u001a\u00020\u0012H§@¢\u0006\u0002\u0010\u0013J$\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00050\n2\u0006\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u0012H§@¢\u0006\u0002\u0010\u001aJ$\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00050\n2\u0006\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u0012H§@¢\u0006\u0002\u0010\u001aJ,\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00050\n2\u0006\u0010\u001d\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0012H§@¢\u0006\u0002\u0010\u001fJ,\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00050\n2\u0006\u0010\u001d\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u00032\u0006\u0010!\u001a\u00020\u0012H§@¢\u0006\u0002\u0010\u001fJ\u001c\u0010\"\u001a\u00020\b2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020$0\nH§@¢\u0006\u0002\u0010\fJ\u0016\u0010%\u001a\u00020&2\u0006\u0010\u000e\u001a\u00020\u0003H§@¢\u0006\u0002\u0010\u000fJ\u000e\u0010'\u001a\u00020&H§@¢\u0006\u0002\u0010(J\u0016\u0010)\u001a\u00020&2\u0006\u0010\u0011\u001a\u00020\u0012H§@¢\u0006\u0002\u0010\u0013¨\u0006*"}, d2 = {"Lcom/yucheng/smarthealthpro/database/room/dao/MotionPatternDao;", "", "insert", "", "metric", "Lcom/yucheng/smarthealthpro/database/room/bean/MotionPattern;", "(Lcom/yucheng/smarthealthpro/database/room/bean/MotionPattern;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "update", "", "insertAll", "", "metrics", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getById", "id", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getByUser", "userId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getByStartTimestamp", "startTimestamp", "getByUserId", "queryAll", "queryByYearToDay", "yearToDay", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "querySinceYearToDay", "queryByTimeRange", "startTime", "endTime", "(JJLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getDataInTimeRange", Constant.SpConstKey.USER_NAME, "updateDataGroupIds", "updates", "Lcom/yucheng/smarthealthpro/database/room/bean/DataGroupIdUpdate;", "deleteById", "", "deleteAll", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteAllByUser", "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes4.dex */
public interface MotionPatternDao {
    Object deleteAll(Continuation<? super Integer> continuation);

    Object deleteAllByUser(String str, Continuation<? super Integer> continuation);

    Object deleteById(long j2, Continuation<? super Integer> continuation);

    Object getById(long j2, Continuation<? super MotionPattern> continuation);

    Object getByStartTimestamp(long j2, Continuation<? super List<MotionPattern>> continuation);

    Object getByUser(String str, Continuation<? super List<MotionPattern>> continuation);

    Object getByUserId(String str, Continuation<? super List<MotionPattern>> continuation);

    Object getDataInTimeRange(long j2, long j3, String str, Continuation<? super List<MotionPattern>> continuation);

    Object insert(MotionPattern motionPattern, Continuation<? super Long> continuation);

    Object insertAll(List<MotionPattern> list, Continuation<? super List<Long>> continuation);

    Object queryAll(String str, Continuation<? super List<MotionPattern>> continuation);

    Object queryByTimeRange(long j2, long j3, String str, Continuation<? super List<MotionPattern>> continuation);

    Object queryByYearToDay(String str, String str2, Continuation<? super List<MotionPattern>> continuation);

    Object querySinceYearToDay(String str, String str2, Continuation<? super List<MotionPattern>> continuation);

    Object update(MotionPattern motionPattern, Continuation<? super Unit> continuation);

    Object updateDataGroupIds(List<DataGroupIdUpdate> list, Continuation<? super Unit> continuation);
}
