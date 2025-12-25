package com.yucheng.smarthealthpro.repository;

import com.yucheng.smarthealthpro.database.room.bean.EcgMeasure;
import com.yucheng.smarthealthpro.database.room.dao.EcgMeasureDao;
import com.yucheng.smarthealthpro.utils.Constant;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* compiled from: EcgMeasureRepository.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0086@¢\u0006\u0002\u0010\nJ\u001c\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\t0\u000eH\u0086@¢\u0006\u0002\u0010\u000fJ\u001c\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\t0\u000e2\u0006\u0010\u0011\u001a\u00020\u0012H\u0086@¢\u0006\u0002\u0010\u0013J,\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\t0\u000e2\u0006\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0012H\u0086@¢\u0006\u0002\u0010\u0017J.\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u0012H\u0086@¢\u0006\u0002\u0010\u001bJ\u001c\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\t0\u000e2\u0006\u0010\u001d\u001a\u00020\u0012H\u0086@¢\u0006\u0002\u0010\u0013J\u001c\u0010\u001e\u001a\u00020\f2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00070\u000eH\u0086@¢\u0006\u0002\u0010\u000fJ\u001e\u0010 \u001a\u00020!2\u0006\u0010\u0019\u001a\u00020\u00072\u0006\u0010\"\u001a\u00020\fH\u0086@¢\u0006\u0002\u0010#R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/yucheng/smarthealthpro/repository/EcgMeasureRepository;", "", "ecgMeasureDao", "Lcom/yucheng/smarthealthpro/database/room/dao/EcgMeasureDao;", "<init>", "(Lcom/yucheng/smarthealthpro/database/room/dao/EcgMeasureDao;)V", "insert", "", "ecgMeasure", "Lcom/yucheng/smarthealthpro/database/room/bean/EcgMeasure;", "(Lcom/yucheng/smarthealthpro/database/room/bean/EcgMeasure;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertAll", "", "ecgMeasureList", "", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllData", Constant.SpConstKey.USER_NAME, "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getByStartTime", "startTime", "deviceMac", "(JLjava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setDataGroupId", "id", "endTime", "(Ljava/lang/String;JJLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "querySyncedData", "userId", "markSynced", "ids", "updateUploaded", "", "isUploaded", "(JZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class EcgMeasureRepository {
    private final EcgMeasureDao ecgMeasureDao;

    /* compiled from: EcgMeasureRepository.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.repository.EcgMeasureRepository", f = "EcgMeasureRepository.kt", i = {}, l = {25}, m = "insertAll", n = {}, s = {})
    /* renamed from: com.yucheng.smarthealthpro.repository.EcgMeasureRepository$insertAll$1, reason: invalid class name */
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
            return EcgMeasureRepository.this.insertAll(null, this);
        }
    }

    /* compiled from: EcgMeasureRepository.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.repository.EcgMeasureRepository", f = "EcgMeasureRepository.kt", i = {0, 0}, l = {56, 71}, m = "setDataGroupId", n = {"this", "id"}, s = {"L$0", "L$1"})
    /* renamed from: com.yucheng.smarthealthpro.repository.EcgMeasureRepository$setDataGroupId$1, reason: invalid class name and case insensitive filesystem */
    static final class C02781 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C02781(Continuation<? super C02781> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return EcgMeasureRepository.this.setDataGroupId(null, 0L, 0L, null, this);
        }
    }

    public EcgMeasureRepository(EcgMeasureDao ecgMeasureDao) {
        Intrinsics.checkNotNullParameter(ecgMeasureDao, "ecgMeasureDao");
        this.ecgMeasureDao = ecgMeasureDao;
    }

    /* compiled from: EcgMeasureRepository.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.repository.EcgMeasureRepository$insert$2", f = "EcgMeasureRepository.kt", i = {}, l = {19}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.yucheng.smarthealthpro.repository.EcgMeasureRepository$insert$2, reason: invalid class name and case insensitive filesystem */
    static final class C02752 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Long>, Object> {
        final /* synthetic */ EcgMeasure $ecgMeasure;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02752(EcgMeasure ecgMeasure, Continuation<? super C02752> continuation) {
            super(2, continuation);
            this.$ecgMeasure = ecgMeasure;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return EcgMeasureRepository.this.new C02752(this.$ecgMeasure, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Long> continuation) {
            return ((C02752) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.label;
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = EcgMeasureRepository.this.ecgMeasureDao.insert(this.$ecgMeasure, this);
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

    public final Object insert(EcgMeasure ecgMeasure, Continuation<? super Long> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new C02752(ecgMeasure, null), continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object insertAll(java.util.List<com.yucheng.smarthealthpro.database.room.bean.EcgMeasure> r5, kotlin.coroutines.Continuation<? super java.lang.Boolean> r6) throws java.lang.Throwable {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.yucheng.smarthealthpro.repository.EcgMeasureRepository.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r6
            com.yucheng.smarthealthpro.repository.EcgMeasureRepository$insertAll$1 r0 = (com.yucheng.smarthealthpro.repository.EcgMeasureRepository.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L19
        L14:
            com.yucheng.smarthealthpro.repository.EcgMeasureRepository$insertAll$1 r0 = new com.yucheng.smarthealthpro.repository.EcgMeasureRepository$insertAll$1
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
            com.yucheng.smarthealthpro.database.room.dao.EcgMeasureDao r6 = r4.ecgMeasureDao     // Catch: java.lang.Exception -> L2a
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
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.repository.EcgMeasureRepository.insertAll(java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* compiled from: EcgMeasureRepository.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "Lcom/yucheng/smarthealthpro/database/room/bean/EcgMeasure;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.repository.EcgMeasureRepository$getAllData$2", f = "EcgMeasureRepository.kt", i = {}, l = {35}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.yucheng.smarthealthpro.repository.EcgMeasureRepository$getAllData$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends EcgMeasure>>, Object> {
        final /* synthetic */ String $userName;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(String str, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$userName = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return EcgMeasureRepository.this.new AnonymousClass2(this.$userName, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends EcgMeasure>> continuation) {
            return invoke2(coroutineScope, (Continuation<? super List<EcgMeasure>>) continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super List<EcgMeasure>> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.label;
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = EcgMeasureRepository.this.ecgMeasureDao.queryAll(this.$userName, this);
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

    public final Object getAllData(String str, Continuation<? super List<EcgMeasure>> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass2(str, null), continuation);
    }

    /* compiled from: EcgMeasureRepository.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "Lcom/yucheng/smarthealthpro/database/room/bean/EcgMeasure;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.repository.EcgMeasureRepository$getByStartTime$2", f = "EcgMeasureRepository.kt", i = {}, l = {41}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.yucheng.smarthealthpro.repository.EcgMeasureRepository$getByStartTime$2, reason: invalid class name and case insensitive filesystem */
    static final class C02742 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends EcgMeasure>>, Object> {
        final /* synthetic */ String $deviceMac;
        final /* synthetic */ long $startTime;
        final /* synthetic */ String $userName;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02742(long j2, String str, String str2, Continuation<? super C02742> continuation) {
            super(2, continuation);
            this.$startTime = j2;
            this.$userName = str;
            this.$deviceMac = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return EcgMeasureRepository.this.new C02742(this.$startTime, this.$userName, this.$deviceMac, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends EcgMeasure>> continuation) {
            return invoke2(coroutineScope, (Continuation<? super List<EcgMeasure>>) continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super List<EcgMeasure>> continuation) {
            return ((C02742) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.label;
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = EcgMeasureRepository.this.ecgMeasureDao.queryByStartTime(this.$startTime, this.$userName, this.$deviceMac, this);
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

    public final Object getByStartTime(long j2, String str, String str2, Continuation<? super List<EcgMeasure>> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new C02742(j2, str, str2, null), continuation);
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
            boolean r2 = r0 instanceof com.yucheng.smarthealthpro.repository.EcgMeasureRepository.C02781
            if (r2 == 0) goto L17
            r2 = r0
            com.yucheng.smarthealthpro.repository.EcgMeasureRepository$setDataGroupId$1 r2 = (com.yucheng.smarthealthpro.repository.EcgMeasureRepository.C02781) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r3 & r4
            if (r3 == 0) goto L17
            int r0 = r2.label
            int r0 = r0 - r4
            r2.label = r0
            goto L1c
        L17:
            com.yucheng.smarthealthpro.repository.EcgMeasureRepository$setDataGroupId$1 r2 = new com.yucheng.smarthealthpro.repository.EcgMeasureRepository$setDataGroupId$1
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
            com.yucheng.smarthealthpro.repository.EcgMeasureRepository r4 = (com.yucheng.smarthealthpro.repository.EcgMeasureRepository) r4
            kotlin.ResultKt.throwOnFailure(r0)     // Catch: java.lang.Exception -> L31
            r13 = r3
            r3 = r0
            r0 = r13
            goto L66
        L4b:
            kotlin.ResultKt.throwOnFailure(r0)
            com.yucheng.smarthealthpro.database.room.dao.EcgMeasureDao r3 = r1.ecgMeasureDao     // Catch: java.lang.Exception -> L31
            r2.L$0 = r1     // Catch: java.lang.Exception -> L31
            r0 = r15
            r2.L$1 = r0     // Catch: java.lang.Exception -> L31
            r2.label = r12     // Catch: java.lang.Exception -> L31
            r4 = r16
            r6 = r18
            r8 = r20
            r9 = r2
            java.lang.Object r3 = r3.getDataInTimeRange(r4, r6, r8, r9)     // Catch: java.lang.Exception -> L31
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
            com.yucheng.smarthealthpro.database.room.bean.EcgMeasure r6 = (com.yucheng.smarthealthpro.database.room.bean.EcgMeasure) r6     // Catch: java.lang.Exception -> L31
            com.yucheng.smarthealthpro.database.room.bean.DataGroupIdUpdate r7 = new com.yucheng.smarthealthpro.database.room.bean.DataGroupIdUpdate     // Catch: java.lang.Exception -> L31
            java.lang.Long r6 = r6.getId()     // Catch: java.lang.Exception -> L31
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6)     // Catch: java.lang.Exception -> L31
            long r8 = r6.longValue()     // Catch: java.lang.Exception -> L31
            r7.<init>(r8, r0)     // Catch: java.lang.Exception -> L31
            r5.add(r7)     // Catch: java.lang.Exception -> L31
            goto L7b
        L9b:
            java.util.List r5 = (java.util.List) r5     // Catch: java.lang.Exception -> L31
            com.yucheng.smarthealthpro.database.room.dao.EcgMeasureDao r0 = r4.ecgMeasureDao     // Catch: java.lang.Exception -> L31
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
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.repository.EcgMeasureRepository.setDataGroupId(java.lang.String, long, long, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* compiled from: EcgMeasureRepository.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "Lcom/yucheng/smarthealthpro/database/room/bean/EcgMeasure;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.repository.EcgMeasureRepository$querySyncedData$2", f = "EcgMeasureRepository.kt", i = {}, l = {81}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.yucheng.smarthealthpro.repository.EcgMeasureRepository$querySyncedData$2, reason: invalid class name and case insensitive filesystem */
    static final class C02772 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends EcgMeasure>>, Object> {
        final /* synthetic */ String $userId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02772(String str, Continuation<? super C02772> continuation) {
            super(2, continuation);
            this.$userId = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return EcgMeasureRepository.this.new C02772(this.$userId, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends EcgMeasure>> continuation) {
            return invoke2(coroutineScope, (Continuation<? super List<EcgMeasure>>) continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super List<EcgMeasure>> continuation) {
            return ((C02772) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.label;
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = EcgMeasureRepository.this.ecgMeasureDao.querySyncedData(this.$userId, false, this);
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

    public final Object querySyncedData(String str, Continuation<? super List<EcgMeasure>> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new C02772(str, null), continuation);
    }

    /* compiled from: EcgMeasureRepository.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.repository.EcgMeasureRepository$markSynced$2", f = "EcgMeasureRepository.kt", i = {}, l = {87}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.yucheng.smarthealthpro.repository.EcgMeasureRepository$markSynced$2, reason: invalid class name and case insensitive filesystem */
    static final class C02762 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
        final /* synthetic */ List<Long> $ids;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02762(List<Long> list, Continuation<? super C02762> continuation) {
            super(2, continuation);
            this.$ids = list;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return EcgMeasureRepository.this.new C02762(this.$ids, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
            return ((C02762) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.label;
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = EcgMeasureDao.DefaultImpls.markAsSynced$default(EcgMeasureRepository.this.ecgMeasureDao, this.$ids, false, this, 2, null);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Boxing.boxBoolean(((Number) obj).intValue() > 0);
        }
    }

    public final Object markSynced(List<Long> list, Continuation<? super Boolean> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new C02762(list, null), continuation);
    }

    /* compiled from: EcgMeasureRepository.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.repository.EcgMeasureRepository$updateUploaded$2", f = "EcgMeasureRepository.kt", i = {}, l = {93}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.yucheng.smarthealthpro.repository.EcgMeasureRepository$updateUploaded$2, reason: invalid class name and case insensitive filesystem */
    static final class C02792 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ long $id;
        final /* synthetic */ boolean $isUploaded;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02792(long j2, boolean z, Continuation<? super C02792> continuation) {
            super(2, continuation);
            this.$id = j2;
            this.$isUploaded = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return EcgMeasureRepository.this.new C02792(this.$id, this.$isUploaded, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C02792) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.label;
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = EcgMeasureRepository.this.ecgMeasureDao.updateUploaded(this.$id, this.$isUploaded, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            ((Number) obj).intValue();
            return Unit.INSTANCE;
        }
    }

    public final Object updateUploaded(long j2, boolean z, Continuation<? super Unit> continuation) {
        Object objWithContext = BuildersKt.withContext(Dispatchers.getIO(), new C02792(j2, z, null), continuation);
        return objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithContext : Unit.INSTANCE;
    }
}
