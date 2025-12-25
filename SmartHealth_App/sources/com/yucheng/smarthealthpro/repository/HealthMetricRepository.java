package com.yucheng.smarthealthpro.repository;

import com.google.android.gms.location.places.Place;
import com.realsil.sdk.dfu.model.DfuConfig;
import com.yucheng.smarthealthpro.database.room.bean.HealthMetric;
import com.yucheng.smarthealthpro.database.room.dao.HealthMetricDao;
import com.yucheng.smarthealthpro.utils.Constant;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* compiled from: HealthMetricRepository.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0019\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0086@¢\u0006\u0002\u0010\nJ\u001c\u0010\u000b\u001a\u00020\u00072\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\rH\u0086@¢\u0006\u0002\u0010\u000eJ4\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\t0\r2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u0015H\u0086@¢\u0006\u0002\u0010\u0016J$\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\t0\r2\u0006\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0014\u001a\u00020\u0015H\u0086@¢\u0006\u0002\u0010\u0019J$\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\t0\r2\u0006\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0014\u001a\u00020\u0015H\u0086@¢\u0006\u0002\u0010\u0019J.\u0010\u001b\u001a\u00020\u00072\u0006\u0010\u001c\u001a\u00020\u00152\u0006\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0015H\u0086@¢\u0006\u0002\u0010\u001fJ$\u0010 \u001a\b\u0012\u0004\u0012\u00020\t0\r2\u0006\u0010\u0018\u001a\u00020\u00152\u0006\u0010!\u001a\u00020\u0015H\u0086@¢\u0006\u0002\u0010\u0019J\u001c\u0010\"\u001a\u00020\u00072\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00110\rH\u0086@¢\u0006\u0002\u0010\u000eJ$\u0010$\u001a\b\u0012\u0004\u0012\u00020\t0\r2\u0006\u0010\u0018\u001a\u00020\u00152\u0006\u0010!\u001a\u00020\u0015H\u0086@¢\u0006\u0002\u0010\u0019J\u001c\u0010%\u001a\u00020\u00072\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00110\rH\u0086@¢\u0006\u0002\u0010\u000eJ$\u0010&\u001a\b\u0012\u0004\u0012\u00020\t0\r2\u0006\u0010\u0018\u001a\u00020\u00152\u0006\u0010!\u001a\u00020\u0015H\u0086@¢\u0006\u0002\u0010\u0019J\u001c\u0010'\u001a\u00020\u00072\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00110\rH\u0086@¢\u0006\u0002\u0010\u000eJ$\u0010(\u001a\b\u0012\u0004\u0012\u00020\t0\r2\u0006\u0010\u0018\u001a\u00020\u00152\u0006\u0010!\u001a\u00020\u0015H\u0086@¢\u0006\u0002\u0010\u0019J\u001c\u0010)\u001a\u00020\u00072\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00110\rH\u0086@¢\u0006\u0002\u0010\u000eJ$\u0010*\u001a\b\u0012\u0004\u0012\u00020\t0\r2\u0006\u0010\u0018\u001a\u00020\u00152\u0006\u0010!\u001a\u00020\u0015H\u0086@¢\u0006\u0002\u0010\u0019J\u001c\u0010+\u001a\u00020\u00072\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00110\rH\u0086@¢\u0006\u0002\u0010\u000eJ,\u0010,\u001a\b\u0012\u0004\u0012\u00020\t0\r2\u0006\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u001e\u001a\u00020\u00112\u0006\u0010!\u001a\u00020\u0015H\u0086@¢\u0006\u0002\u0010-R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006."}, d2 = {"Lcom/yucheng/smarthealthpro/repository/HealthMetricRepository;", "", "healthMetricDao", "Lcom/yucheng/smarthealthpro/database/room/dao/HealthMetricDao;", "<init>", "(Lcom/yucheng/smarthealthpro/database/room/dao/HealthMetricDao;)V", "insert", "", "healthMetric", "Lcom/yucheng/smarthealthpro/database/room/bean/HealthMetric;", "(Lcom/yucheng/smarthealthpro/database/room/bean/HealthMetric;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertAll", "healthMetrics", "", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getBoUploadedWithTimeRange", "startTimestamp", "", "endTimestamp", "isUpload", Constant.SpConstKey.USER_NAME, "", "(JJZLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getDayData", "yearToDay", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getSinceDayData", "setDataGroupId", "id", "startTime", "endTime", "(Ljava/lang/String;JJLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getNotSyncedBloodOxygenWithYearToDay", "userId", "markBloodOxygenSynced", "ids", "getNotSyncedBloodSugarWithYearToDay", "markBloodSugarSynced", "getNotSyncedHrvWithYearToDay", "markHrvSynced", "getNotSyncedRespiratoryRateWithYearToDay", "markRespiratoryRateSynced", "getNotSyncedTemperatureWithYearToDay", "markTemperatureSynced", "queryByTimeRange", "(JJLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class HealthMetricRepository {
    private final HealthMetricDao healthMetricDao;

    /* compiled from: HealthMetricRepository.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.repository.HealthMetricRepository", f = "HealthMetricRepository.kt", i = {}, l = {18}, m = "insert", n = {}, s = {})
    /* renamed from: com.yucheng.smarthealthpro.repository.HealthMetricRepository$insert$1, reason: invalid class name */
    static final class AnonymousClass1 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HealthMetricRepository.this.insert(null, this);
        }
    }

    /* compiled from: HealthMetricRepository.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.repository.HealthMetricRepository", f = "HealthMetricRepository.kt", i = {}, l = {23}, m = "insertAll", n = {}, s = {})
    /* renamed from: com.yucheng.smarthealthpro.repository.HealthMetricRepository$insertAll$1, reason: invalid class name and case insensitive filesystem */
    static final class C02811 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        C02811(Continuation<? super C02811> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HealthMetricRepository.this.insertAll(null, this);
        }
    }

    /* compiled from: HealthMetricRepository.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.repository.HealthMetricRepository", f = "HealthMetricRepository.kt", i = {}, l = {Place.TYPE_STADIUM}, m = "markBloodOxygenSynced", n = {}, s = {})
    /* renamed from: com.yucheng.smarthealthpro.repository.HealthMetricRepository$markBloodOxygenSynced$1, reason: invalid class name and case insensitive filesystem */
    static final class C02821 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        C02821(Continuation<? super C02821> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HealthMetricRepository.this.markBloodOxygenSynced(null, this);
        }
    }

    /* compiled from: HealthMetricRepository.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.repository.HealthMetricRepository", f = "HealthMetricRepository.kt", i = {}, l = {Place.TYPE_UNIVERSITY}, m = "markBloodSugarSynced", n = {}, s = {})
    /* renamed from: com.yucheng.smarthealthpro.repository.HealthMetricRepository$markBloodSugarSynced$1, reason: invalid class name and case insensitive filesystem */
    static final class C02831 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        C02831(Continuation<? super C02831> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HealthMetricRepository.this.markBloodSugarSynced(null, this);
        }
    }

    /* compiled from: HealthMetricRepository.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.repository.HealthMetricRepository", f = "HealthMetricRepository.kt", i = {}, l = {102}, m = "markHrvSynced", n = {}, s = {})
    /* renamed from: com.yucheng.smarthealthpro.repository.HealthMetricRepository$markHrvSynced$1, reason: invalid class name and case insensitive filesystem */
    static final class C02841 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        C02841(Continuation<? super C02841> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HealthMetricRepository.this.markHrvSynced(null, this);
        }
    }

    /* compiled from: HealthMetricRepository.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.repository.HealthMetricRepository", f = "HealthMetricRepository.kt", i = {}, l = {DfuConfig.MAX_POWER_LEVER}, m = "markRespiratoryRateSynced", n = {}, s = {})
    /* renamed from: com.yucheng.smarthealthpro.repository.HealthMetricRepository$markRespiratoryRateSynced$1, reason: invalid class name and case insensitive filesystem */
    static final class C02851 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        C02851(Continuation<? super C02851> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HealthMetricRepository.this.markRespiratoryRateSynced(null, this);
        }
    }

    /* compiled from: HealthMetricRepository.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.repository.HealthMetricRepository", f = "HealthMetricRepository.kt", i = {}, l = {118}, m = "markTemperatureSynced", n = {}, s = {})
    /* renamed from: com.yucheng.smarthealthpro.repository.HealthMetricRepository$markTemperatureSynced$1, reason: invalid class name and case insensitive filesystem */
    static final class C02861 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        C02861(Continuation<? super C02861> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HealthMetricRepository.this.markTemperatureSynced(null, this);
        }
    }

    /* compiled from: HealthMetricRepository.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.repository.HealthMetricRepository", f = "HealthMetricRepository.kt", i = {0, 0}, l = {58, 73}, m = "setDataGroupId", n = {"this", "id"}, s = {"L$0", "L$1"})
    /* renamed from: com.yucheng.smarthealthpro.repository.HealthMetricRepository$setDataGroupId$1, reason: invalid class name and case insensitive filesystem */
    static final class C02871 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C02871(Continuation<? super C02871> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HealthMetricRepository.this.setDataGroupId(null, 0L, 0L, null, this);
        }
    }

    public HealthMetricRepository(HealthMetricDao healthMetricDao) {
        Intrinsics.checkNotNullParameter(healthMetricDao, "healthMetricDao");
        this.healthMetricDao = healthMetricDao;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object insert(com.yucheng.smarthealthpro.database.room.bean.HealthMetric r5, kotlin.coroutines.Continuation<? super java.lang.Boolean> r6) throws java.lang.Throwable {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.yucheng.smarthealthpro.repository.HealthMetricRepository.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r6
            com.yucheng.smarthealthpro.repository.HealthMetricRepository$insert$1 r0 = (com.yucheng.smarthealthpro.repository.HealthMetricRepository.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L19
        L14:
            com.yucheng.smarthealthpro.repository.HealthMetricRepository$insert$1 r0 = new com.yucheng.smarthealthpro.repository.HealthMetricRepository$insert$1
            r0.<init>(r6)
        L19:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L32
            if (r2 != r3) goto L2a
            kotlin.ResultKt.throwOnFailure(r6)
            goto L40
        L2a:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L32:
            kotlin.ResultKt.throwOnFailure(r6)
            com.yucheng.smarthealthpro.database.room.dao.HealthMetricDao r6 = r4.healthMetricDao
            r0.label = r3
            java.lang.Object r6 = r6.insert(r5, r0)
            if (r6 != r1) goto L40
            return r1
        L40:
            java.lang.Number r6 = (java.lang.Number) r6
            long r5 = r6.longValue()
            r0 = -1
            int r5 = (r5 > r0 ? 1 : (r5 == r0 ? 0 : -1))
            if (r5 == 0) goto L4d
            goto L4e
        L4d:
            r3 = 0
        L4e:
            java.lang.Boolean r5 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.repository.HealthMetricRepository.insert(com.yucheng.smarthealthpro.database.room.bean.HealthMetric, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object insertAll(java.util.List<com.yucheng.smarthealthpro.database.room.bean.HealthMetric> r5, kotlin.coroutines.Continuation<? super java.lang.Boolean> r6) throws java.lang.Throwable {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.yucheng.smarthealthpro.repository.HealthMetricRepository.C02811
            if (r0 == 0) goto L14
            r0 = r6
            com.yucheng.smarthealthpro.repository.HealthMetricRepository$insertAll$1 r0 = (com.yucheng.smarthealthpro.repository.HealthMetricRepository.C02811) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L19
        L14:
            com.yucheng.smarthealthpro.repository.HealthMetricRepository$insertAll$1 r0 = new com.yucheng.smarthealthpro.repository.HealthMetricRepository$insertAll$1
            r0.<init>(r6)
        L19:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L34
            if (r2 != r3) goto L2c
            kotlin.ResultKt.throwOnFailure(r6)     // Catch: java.lang.Exception -> L2a
            goto L42
        L2a:
            r5 = move-exception
            goto L4c
        L2c:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L34:
            kotlin.ResultKt.throwOnFailure(r6)
            com.yucheng.smarthealthpro.database.room.dao.HealthMetricDao r6 = r4.healthMetricDao     // Catch: java.lang.Exception -> L2a
            r0.label = r3     // Catch: java.lang.Exception -> L2a
            java.lang.Object r6 = r6.insertAll(r5, r0)     // Catch: java.lang.Exception -> L2a
            if (r6 != r1) goto L42
            return r1
        L42:
            java.util.List r6 = (java.util.List) r6     // Catch: java.lang.Exception -> L2a
            java.util.Collection r6 = (java.util.Collection) r6     // Catch: java.lang.Exception -> L2a
            boolean r5 = r6.isEmpty()     // Catch: java.lang.Exception -> L2a
            r5 = r5 ^ r3
            goto L66
        L4c:
            com.yucheng.smarthealthpro.utils.MLog$Companion r6 = com.yucheng.smarthealthpro.utils.MLog.INSTANCE
            r5.printStackTrace()
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "SQL操作失败 "
            r0.<init>(r1)
            java.lang.StringBuilder r5 = r0.append(r5)
            java.lang.String r5 = r5.toString()
            r6.d(r5)
            r5 = 0
        L66:
            java.lang.Boolean r5 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.repository.HealthMetricRepository.insertAll(java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final Object getBoUploadedWithTimeRange(long j2, long j3, boolean z, String str, Continuation<? super List<HealthMetric>> continuation) {
        return this.healthMetricDao.getBoUploadedWithTimeRange(j2, j3, z, str, continuation);
    }

    /* compiled from: HealthMetricRepository.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "Lcom/yucheng/smarthealthpro/database/room/bean/HealthMetric;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.repository.HealthMetricRepository$getDayData$2", f = "HealthMetricRepository.kt", i = {}, l = {37}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.yucheng.smarthealthpro.repository.HealthMetricRepository$getDayData$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends HealthMetric>>, Object> {
        final /* synthetic */ String $userName;
        final /* synthetic */ String $yearToDay;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(String str, String str2, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$yearToDay = str;
            this.$userName = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return HealthMetricRepository.this.new AnonymousClass2(this.$yearToDay, this.$userName, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends HealthMetric>> continuation) {
            return invoke2(coroutineScope, (Continuation<? super List<HealthMetric>>) continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super List<HealthMetric>> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.label;
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = HealthMetricRepository.this.healthMetricDao.queryByYearToDay(this.$yearToDay, this.$userName, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return obj;
        }
    }

    public final Object getDayData(String str, String str2, Continuation<? super List<HealthMetric>> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass2(str, str2, null), continuation);
    }

    /* compiled from: HealthMetricRepository.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "Lcom/yucheng/smarthealthpro/database/room/bean/HealthMetric;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.repository.HealthMetricRepository$getSinceDayData$2", f = "HealthMetricRepository.kt", i = {}, l = {43}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.yucheng.smarthealthpro.repository.HealthMetricRepository$getSinceDayData$2, reason: invalid class name and case insensitive filesystem */
    static final class C02802 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends HealthMetric>>, Object> {
        final /* synthetic */ String $userName;
        final /* synthetic */ String $yearToDay;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02802(String str, String str2, Continuation<? super C02802> continuation) {
            super(2, continuation);
            this.$yearToDay = str;
            this.$userName = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return HealthMetricRepository.this.new C02802(this.$yearToDay, this.$userName, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends HealthMetric>> continuation) {
            return invoke2(coroutineScope, (Continuation<? super List<HealthMetric>>) continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super List<HealthMetric>> continuation) {
            return ((C02802) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.label;
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = HealthMetricRepository.this.healthMetricDao.querySinceYearToDay(this.$yearToDay, this.$userName, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return obj;
        }
    }

    public final Object getSinceDayData(String str, String str2, Continuation<? super List<HealthMetric>> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new C02802(str, str2, null), continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object setDataGroupId(java.lang.String r15, long r16, long r18, java.lang.String r20, kotlin.coroutines.Continuation<? super java.lang.Boolean> r21) throws java.lang.Throwable {
        /*
            r14 = this;
            r1 = r14
            r0 = r21
            boolean r2 = r0 instanceof com.yucheng.smarthealthpro.repository.HealthMetricRepository.C02871
            if (r2 == 0) goto L17
            r2 = r0
            com.yucheng.smarthealthpro.repository.HealthMetricRepository$setDataGroupId$1 r2 = (com.yucheng.smarthealthpro.repository.HealthMetricRepository.C02871) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r3 & r4
            if (r3 == 0) goto L17
            int r0 = r2.label
            int r0 = r0 - r4
            r2.label = r0
            goto L1c
        L17:
            com.yucheng.smarthealthpro.repository.HealthMetricRepository$setDataGroupId$1 r2 = new com.yucheng.smarthealthpro.repository.HealthMetricRepository$setDataGroupId$1
            r2.<init>(r0)
        L1c:
            java.lang.Object r0 = r2.result
            java.lang.Object r10 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r2.label
            r11 = 2
            r12 = 1
            if (r3 == 0) goto L4b
            if (r3 == r12) goto L3c
            if (r3 != r11) goto L34
            kotlin.ResultKt.throwOnFailure(r0)     // Catch: java.lang.Exception -> L31
            goto Lb1
        L31:
            r0 = move-exception
            goto Lad
        L34:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r2)
            throw r0
        L3c:
            java.lang.Object r3 = r2.L$1
            java.lang.String r3 = (java.lang.String) r3
            java.lang.Object r4 = r2.L$0
            com.yucheng.smarthealthpro.repository.HealthMetricRepository r4 = (com.yucheng.smarthealthpro.repository.HealthMetricRepository) r4
            kotlin.ResultKt.throwOnFailure(r0)     // Catch: java.lang.Exception -> L31
            r13 = r3
            r3 = r0
            r0 = r13
            goto L66
        L4b:
            kotlin.ResultKt.throwOnFailure(r0)
            com.yucheng.smarthealthpro.database.room.dao.HealthMetricDao r3 = r1.healthMetricDao     // Catch: java.lang.Exception -> L31
            r2.L$0 = r1     // Catch: java.lang.Exception -> L31
            r0 = r15
            r2.L$1 = r0     // Catch: java.lang.Exception -> L31
            r2.label = r12     // Catch: java.lang.Exception -> L31
            r4 = r16
            r6 = r18
            r8 = r20
            r9 = r2
            java.lang.Object r3 = r3.getMetricsInTimeRange(r4, r6, r8, r9)     // Catch: java.lang.Exception -> L31
            if (r3 != r10) goto L65
            return r10
        L65:
            r4 = r1
        L66:
            java.util.List r3 = (java.util.List) r3     // Catch: java.lang.Exception -> L31
            java.lang.Iterable r3 = (java.lang.Iterable) r3     // Catch: java.lang.Exception -> L31
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch: java.lang.Exception -> L31
            r6 = 10
            int r6 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r3, r6)     // Catch: java.lang.Exception -> L31
            r5.<init>(r6)     // Catch: java.lang.Exception -> L31
            java.util.Collection r5 = (java.util.Collection) r5     // Catch: java.lang.Exception -> L31
            java.util.Iterator r3 = r3.iterator()     // Catch: java.lang.Exception -> L31
        L7b:
            boolean r6 = r3.hasNext()     // Catch: java.lang.Exception -> L31
            if (r6 == 0) goto L9b
            java.lang.Object r6 = r3.next()     // Catch: java.lang.Exception -> L31
            com.yucheng.smarthealthpro.database.room.bean.HealthMetric r6 = (com.yucheng.smarthealthpro.database.room.bean.HealthMetric) r6     // Catch: java.lang.Exception -> L31
            com.yucheng.smarthealthpro.database.room.bean.DataGroupIdUpdate r7 = new com.yucheng.smarthealthpro.database.room.bean.DataGroupIdUpdate     // Catch: java.lang.Exception -> L31
            java.lang.Long r6 = r6.getId()     // Catch: java.lang.Exception -> L31
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6)     // Catch: java.lang.Exception -> L31
            long r8 = r6.longValue()     // Catch: java.lang.Exception -> L31
            r7.<init>(r8, r0)     // Catch: java.lang.Exception -> L31
            r5.add(r7)     // Catch: java.lang.Exception -> L31
            goto L7b
        L9b:
            java.util.List r5 = (java.util.List) r5     // Catch: java.lang.Exception -> L31
            com.yucheng.smarthealthpro.database.room.dao.HealthMetricDao r0 = r4.healthMetricDao     // Catch: java.lang.Exception -> L31
            r3 = 0
            r2.L$0 = r3     // Catch: java.lang.Exception -> L31
            r2.L$1 = r3     // Catch: java.lang.Exception -> L31
            r2.label = r11     // Catch: java.lang.Exception -> L31
            java.lang.Object r0 = r0.updateDataGroupIds(r5, r2)     // Catch: java.lang.Exception -> L31
            if (r0 != r10) goto Lb1
            return r10
        Lad:
            r0.printStackTrace()
            r12 = 0
        Lb1:
            java.lang.Boolean r0 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r12)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.repository.HealthMetricRepository.setDataGroupId(java.lang.String, long, long, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final Object getNotSyncedBloodOxygenWithYearToDay(String str, String str2, Continuation<? super List<HealthMetric>> continuation) {
        return this.healthMetricDao.queryBloodOxygenSyncedWithYearToDay(str, str2, false, continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object markBloodOxygenSynced(java.util.List<java.lang.Long> r9, kotlin.coroutines.Continuation<? super java.lang.Boolean> r10) throws java.lang.Throwable {
        /*
            r8 = this;
            boolean r0 = r10 instanceof com.yucheng.smarthealthpro.repository.HealthMetricRepository.C02821
            if (r0 == 0) goto L14
            r0 = r10
            com.yucheng.smarthealthpro.repository.HealthMetricRepository$markBloodOxygenSynced$1 r0 = (com.yucheng.smarthealthpro.repository.HealthMetricRepository.C02821) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L19
        L14:
            com.yucheng.smarthealthpro.repository.HealthMetricRepository$markBloodOxygenSynced$1 r0 = new com.yucheng.smarthealthpro.repository.HealthMetricRepository$markBloodOxygenSynced$1
            r0.<init>(r10)
        L19:
            r4 = r0
            java.lang.Object r10 = r4.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r4.label
            r7 = 1
            if (r1 == 0) goto L33
            if (r1 != r7) goto L2b
            kotlin.ResultKt.throwOnFailure(r10)
            goto L45
        L2b:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L33:
            kotlin.ResultKt.throwOnFailure(r10)
            com.yucheng.smarthealthpro.database.room.dao.HealthMetricDao r1 = r8.healthMetricDao
            r4.label = r7
            r3 = 0
            r5 = 2
            r6 = 0
            r2 = r9
            java.lang.Object r10 = com.yucheng.smarthealthpro.database.room.dao.HealthMetricDao.DefaultImpls.markBloodOxygenAsSynced$default(r1, r2, r3, r4, r5, r6)
            if (r10 != r0) goto L45
            return r0
        L45:
            java.lang.Number r10 = (java.lang.Number) r10
            int r9 = r10.intValue()
            if (r9 <= 0) goto L4e
            goto L4f
        L4e:
            r7 = 0
        L4f:
            java.lang.Boolean r9 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r7)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.repository.HealthMetricRepository.markBloodOxygenSynced(java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final Object getNotSyncedBloodSugarWithYearToDay(String str, String str2, Continuation<? super List<HealthMetric>> continuation) {
        return this.healthMetricDao.queryBloodSugarSyncedWithYearToDay(str, str2, false, continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object markBloodSugarSynced(java.util.List<java.lang.Long> r9, kotlin.coroutines.Continuation<? super java.lang.Boolean> r10) throws java.lang.Throwable {
        /*
            r8 = this;
            boolean r0 = r10 instanceof com.yucheng.smarthealthpro.repository.HealthMetricRepository.C02831
            if (r0 == 0) goto L14
            r0 = r10
            com.yucheng.smarthealthpro.repository.HealthMetricRepository$markBloodSugarSynced$1 r0 = (com.yucheng.smarthealthpro.repository.HealthMetricRepository.C02831) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L19
        L14:
            com.yucheng.smarthealthpro.repository.HealthMetricRepository$markBloodSugarSynced$1 r0 = new com.yucheng.smarthealthpro.repository.HealthMetricRepository$markBloodSugarSynced$1
            r0.<init>(r10)
        L19:
            r4 = r0
            java.lang.Object r10 = r4.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r4.label
            r7 = 1
            if (r1 == 0) goto L33
            if (r1 != r7) goto L2b
            kotlin.ResultKt.throwOnFailure(r10)
            goto L45
        L2b:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L33:
            kotlin.ResultKt.throwOnFailure(r10)
            com.yucheng.smarthealthpro.database.room.dao.HealthMetricDao r1 = r8.healthMetricDao
            r4.label = r7
            r3 = 0
            r5 = 2
            r6 = 0
            r2 = r9
            java.lang.Object r10 = com.yucheng.smarthealthpro.database.room.dao.HealthMetricDao.DefaultImpls.markBloodSugarAsSynced$default(r1, r2, r3, r4, r5, r6)
            if (r10 != r0) goto L45
            return r0
        L45:
            java.lang.Number r10 = (java.lang.Number) r10
            int r9 = r10.intValue()
            if (r9 <= 0) goto L4e
            goto L4f
        L4e:
            r7 = 0
        L4f:
            java.lang.Boolean r9 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r7)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.repository.HealthMetricRepository.markBloodSugarSynced(java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final Object getNotSyncedHrvWithYearToDay(String str, String str2, Continuation<? super List<HealthMetric>> continuation) {
        return this.healthMetricDao.queryHrvSyncedWithYearToDay(str, str2, false, continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object markHrvSynced(java.util.List<java.lang.Long> r9, kotlin.coroutines.Continuation<? super java.lang.Boolean> r10) throws java.lang.Throwable {
        /*
            r8 = this;
            boolean r0 = r10 instanceof com.yucheng.smarthealthpro.repository.HealthMetricRepository.C02841
            if (r0 == 0) goto L14
            r0 = r10
            com.yucheng.smarthealthpro.repository.HealthMetricRepository$markHrvSynced$1 r0 = (com.yucheng.smarthealthpro.repository.HealthMetricRepository.C02841) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L19
        L14:
            com.yucheng.smarthealthpro.repository.HealthMetricRepository$markHrvSynced$1 r0 = new com.yucheng.smarthealthpro.repository.HealthMetricRepository$markHrvSynced$1
            r0.<init>(r10)
        L19:
            r4 = r0
            java.lang.Object r10 = r4.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r4.label
            r7 = 1
            if (r1 == 0) goto L33
            if (r1 != r7) goto L2b
            kotlin.ResultKt.throwOnFailure(r10)
            goto L45
        L2b:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L33:
            kotlin.ResultKt.throwOnFailure(r10)
            com.yucheng.smarthealthpro.database.room.dao.HealthMetricDao r1 = r8.healthMetricDao
            r4.label = r7
            r3 = 0
            r5 = 2
            r6 = 0
            r2 = r9
            java.lang.Object r10 = com.yucheng.smarthealthpro.database.room.dao.HealthMetricDao.DefaultImpls.markHrvAsSynced$default(r1, r2, r3, r4, r5, r6)
            if (r10 != r0) goto L45
            return r0
        L45:
            java.lang.Number r10 = (java.lang.Number) r10
            int r9 = r10.intValue()
            if (r9 <= 0) goto L4e
            goto L4f
        L4e:
            r7 = 0
        L4f:
            java.lang.Boolean r9 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r7)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.repository.HealthMetricRepository.markHrvSynced(java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final Object getNotSyncedRespiratoryRateWithYearToDay(String str, String str2, Continuation<? super List<HealthMetric>> continuation) {
        return this.healthMetricDao.queryRespiratoryRateSyncedWithYearToDay(str, str2, false, continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object markRespiratoryRateSynced(java.util.List<java.lang.Long> r9, kotlin.coroutines.Continuation<? super java.lang.Boolean> r10) throws java.lang.Throwable {
        /*
            r8 = this;
            boolean r0 = r10 instanceof com.yucheng.smarthealthpro.repository.HealthMetricRepository.C02851
            if (r0 == 0) goto L14
            r0 = r10
            com.yucheng.smarthealthpro.repository.HealthMetricRepository$markRespiratoryRateSynced$1 r0 = (com.yucheng.smarthealthpro.repository.HealthMetricRepository.C02851) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L19
        L14:
            com.yucheng.smarthealthpro.repository.HealthMetricRepository$markRespiratoryRateSynced$1 r0 = new com.yucheng.smarthealthpro.repository.HealthMetricRepository$markRespiratoryRateSynced$1
            r0.<init>(r10)
        L19:
            r4 = r0
            java.lang.Object r10 = r4.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r4.label
            r7 = 1
            if (r1 == 0) goto L33
            if (r1 != r7) goto L2b
            kotlin.ResultKt.throwOnFailure(r10)
            goto L45
        L2b:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L33:
            kotlin.ResultKt.throwOnFailure(r10)
            com.yucheng.smarthealthpro.database.room.dao.HealthMetricDao r1 = r8.healthMetricDao
            r4.label = r7
            r3 = 0
            r5 = 2
            r6 = 0
            r2 = r9
            java.lang.Object r10 = com.yucheng.smarthealthpro.database.room.dao.HealthMetricDao.DefaultImpls.markRespiratoryRateAsSynced$default(r1, r2, r3, r4, r5, r6)
            if (r10 != r0) goto L45
            return r0
        L45:
            java.lang.Number r10 = (java.lang.Number) r10
            int r9 = r10.intValue()
            if (r9 <= 0) goto L4e
            goto L4f
        L4e:
            r7 = 0
        L4f:
            java.lang.Boolean r9 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r7)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.repository.HealthMetricRepository.markRespiratoryRateSynced(java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final Object getNotSyncedTemperatureWithYearToDay(String str, String str2, Continuation<? super List<HealthMetric>> continuation) {
        return this.healthMetricDao.queryTemperatureSyncedWithYearToDay(str, str2, false, continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object markTemperatureSynced(java.util.List<java.lang.Long> r9, kotlin.coroutines.Continuation<? super java.lang.Boolean> r10) throws java.lang.Throwable {
        /*
            r8 = this;
            boolean r0 = r10 instanceof com.yucheng.smarthealthpro.repository.HealthMetricRepository.C02861
            if (r0 == 0) goto L14
            r0 = r10
            com.yucheng.smarthealthpro.repository.HealthMetricRepository$markTemperatureSynced$1 r0 = (com.yucheng.smarthealthpro.repository.HealthMetricRepository.C02861) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L19
        L14:
            com.yucheng.smarthealthpro.repository.HealthMetricRepository$markTemperatureSynced$1 r0 = new com.yucheng.smarthealthpro.repository.HealthMetricRepository$markTemperatureSynced$1
            r0.<init>(r10)
        L19:
            r4 = r0
            java.lang.Object r10 = r4.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r4.label
            r7 = 1
            if (r1 == 0) goto L33
            if (r1 != r7) goto L2b
            kotlin.ResultKt.throwOnFailure(r10)
            goto L45
        L2b:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L33:
            kotlin.ResultKt.throwOnFailure(r10)
            com.yucheng.smarthealthpro.database.room.dao.HealthMetricDao r1 = r8.healthMetricDao
            r4.label = r7
            r3 = 0
            r5 = 2
            r6 = 0
            r2 = r9
            java.lang.Object r10 = com.yucheng.smarthealthpro.database.room.dao.HealthMetricDao.DefaultImpls.markTemperatureAsSynced$default(r1, r2, r3, r4, r5, r6)
            if (r10 != r0) goto L45
            return r0
        L45:
            java.lang.Number r10 = (java.lang.Number) r10
            int r9 = r10.intValue()
            if (r9 <= 0) goto L4e
            goto L4f
        L4e:
            r7 = 0
        L4f:
            java.lang.Boolean r9 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r7)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.repository.HealthMetricRepository.markTemperatureSynced(java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final Object queryByTimeRange(long j2, long j3, String str, Continuation<? super List<HealthMetric>> continuation) {
        return this.healthMetricDao.getMetricsInTimeRange(j2, j3, str, continuation);
    }
}
