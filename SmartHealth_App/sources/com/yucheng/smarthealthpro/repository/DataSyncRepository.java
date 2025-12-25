package com.yucheng.smarthealthpro.repository;

import com.alibaba.fastjson2.internal.asm.Opcodes;
import com.realsil.sdk.dfu.DfuException;
import com.realsil.sdk.dfu.model.DfuConfig;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.ycbtsdk.Constants;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function0;

/* compiled from: DataSyncRepository.kt */
@Metadata(d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 52\u00020\u0001:\u00015B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J&\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0086@¢\u0006\u0002\u0010\u0012J&\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00142\u0006\u0010\u0010\u001a\u00020\u0011H\u0086@¢\u0006\u0002\u0010\u0015J&\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00172\u0006\u0010\u0010\u001a\u00020\u0011H\u0086@¢\u0006\u0002\u0010\u0018J&\u0010\u0019\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u001a2\u0006\u0010\u0010\u001a\u00020\u0011H\u0086@¢\u0006\u0002\u0010\u001bJ&\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u001d2\u0006\u0010\u0010\u001a\u00020\u0011H\u0086@¢\u0006\u0002\u0010\u001eJ&\u0010\u001f\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020 2\u0006\u0010\u0010\u001a\u00020\u0011H\u0086@¢\u0006\u0002\u0010!J&\u0010\"\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020#2\u0006\u0010\u0010\u001a\u00020\u0011H\u0086@¢\u0006\u0002\u0010$J&\u0010%\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020&2\u0006\u0010\u0010\u001a\u00020\u0011H\u0086@¢\u0006\u0002\u0010'J&\u0010(\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020&2\u0006\u0010\u0010\u001a\u00020\u0011H\u0086@¢\u0006\u0002\u0010'J&\u0010)\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020&2\u0006\u0010\u0010\u001a\u00020\u0011H\u0086@¢\u0006\u0002\u0010'J&\u0010*\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020&2\u0006\u0010\u0010\u001a\u00020\u0011H\u0086@¢\u0006\u0002\u0010'J&\u0010+\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020,2\u0006\u0010\u0010\u001a\u00020\u0011H\u0086@¢\u0006\u0002\u0010-J&\u0010.\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020/2\u0006\u0010\u0010\u001a\u00020\u0011H\u0086@¢\u0006\u0002\u00100J&\u00101\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020&2\u0006\u0010\u0010\u001a\u00020\u0011H\u0086@¢\u0006\u0002\u0010'J&\u00102\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u0002032\u0006\u0010\u0010\u001a\u00020\u0011H\u0086@¢\u0006\u0002\u00104R\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007¨\u00066"}, d2 = {"Lcom/yucheng/smarthealthpro/repository/DataSyncRepository;", "", "<init>", "()V", "dataUploadRepository", "Lcom/yucheng/smarthealthpro/repository/DataUploadRepository;", "getDataUploadRepository", "()Lcom/yucheng/smarthealthpro/repository/DataUploadRepository;", "dataUploadRepository$delegate", "Lkotlin/Lazy;", "syncStepData", "", "context", "Landroid/content/Context;", "repository", "Lcom/yucheng/smarthealthpro/repository/StepRepository;", "userId", "", "(Landroid/content/Context;Lcom/yucheng/smarthealthpro/repository/StepRepository;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "syncBloodLipidsData", "Lcom/yucheng/smarthealthpro/repository/BloodLipidsRepository;", "(Landroid/content/Context;Lcom/yucheng/smarthealthpro/repository/BloodLipidsRepository;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "syncUricAcidData", "Lcom/yucheng/smarthealthpro/repository/UricAcidRepository;", "(Landroid/content/Context;Lcom/yucheng/smarthealthpro/repository/UricAcidRepository;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "syncBloodKetoneData", "Lcom/yucheng/smarthealthpro/repository/BloodKetonesRepository;", "(Landroid/content/Context;Lcom/yucheng/smarthealthpro/repository/BloodKetonesRepository;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "syncSleepData", "Lcom/yucheng/smarthealthpro/repository/SleepRepository;", "(Landroid/content/Context;Lcom/yucheng/smarthealthpro/repository/SleepRepository;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "syncHeartRateData", "Lcom/yucheng/smarthealthpro/repository/HeartRateRepository;", "(Landroid/content/Context;Lcom/yucheng/smarthealthpro/repository/HeartRateRepository;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "syncBloodPressureData", "Lcom/yucheng/smarthealthpro/repository/BloodPressureRepository;", "(Landroid/content/Context;Lcom/yucheng/smarthealthpro/repository/BloodPressureRepository;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "syncBloodOxygenData", "Lcom/yucheng/smarthealthpro/repository/HealthMetricRepository;", "(Landroid/content/Context;Lcom/yucheng/smarthealthpro/repository/HealthMetricRepository;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "syncBloodSugarData", "syncHrvData", "syncRespiratoryRateData", "syncPhysiotherapyData", "Lcom/yucheng/smarthealthpro/repository/PhysiotherapyRepository;", "(Landroid/content/Context;Lcom/yucheng/smarthealthpro/repository/PhysiotherapyRepository;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "syncPressureData", "Lcom/yucheng/smarthealthpro/repository/BodyDataRepository;", "(Landroid/content/Context;Lcom/yucheng/smarthealthpro/repository/BodyDataRepository;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "syncTemperatureData", "syncEcgData", "Lcom/yucheng/smarthealthpro/repository/EcgMeasureRepository;", "(Landroid/content/Context;Lcom/yucheng/smarthealthpro/repository/EcgMeasureRepository;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class DataSyncRepository {
    public static final long ONE_DAY_MILLISECOND = 86400000;

    /* renamed from: dataUploadRepository$delegate, reason: from kotlin metadata */
    private final Lazy dataUploadRepository = LazyKt.lazy(new Function0() { // from class: com.yucheng.smarthealthpro.repository.DataSyncRepository$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return DataSyncRepository.dataUploadRepository_delegate$lambda$0();
        }
    });

    /* compiled from: DataSyncRepository.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.repository.DataSyncRepository", f = "DataSyncRepository.kt", i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2}, l = {Opcodes.IFLE, Opcodes.ARETURN, 180}, m = "syncBloodKetoneData", n = {"this", "repository", "userId", Constant.SpConstKey.DEV_ID, "i", "this", "repository", "userId", Constant.SpConstKey.DEV_ID, "notSyncedData", "i", "this", "repository", "userId", Constant.SpConstKey.DEV_ID, "i"}, s = {"L$0", "L$1", "L$2", "L$3", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "I$0", "L$0", "L$1", "L$2", "L$3", "I$0"})
    /* renamed from: com.yucheng.smarthealthpro.repository.DataSyncRepository$syncBloodKetoneData$1, reason: invalid class name */
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DataSyncRepository.this.syncBloodKetoneData(null, null, null, this);
        }
    }

    /* compiled from: DataSyncRepository.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.repository.DataSyncRepository", f = "DataSyncRepository.kt", i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2}, l = {85, 106, DfuConfig.MAX_POWER_LEVER}, m = "syncBloodLipidsData", n = {"this", "repository", "userId", Constant.SpConstKey.DEV_ID, "i", "this", "repository", "userId", Constant.SpConstKey.DEV_ID, "notSyncedData", "i", "this", "repository", "userId", Constant.SpConstKey.DEV_ID, "i"}, s = {"L$0", "L$1", "L$2", "L$3", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "I$0", "L$0", "L$1", "L$2", "L$3", "I$0"})
    /* renamed from: com.yucheng.smarthealthpro.repository.DataSyncRepository$syncBloodLipidsData$1, reason: invalid class name and case insensitive filesystem */
    static final class C02601 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        C02601(Continuation<? super C02601> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DataSyncRepository.this.syncBloodLipidsData(null, null, null, this);
        }
    }

    /* compiled from: DataSyncRepository.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.repository.DataSyncRepository", f = "DataSyncRepository.kt", i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2}, l = {Constants.DATATYPE.SettingUploadReminder, 316, 320}, m = "syncBloodOxygenData", n = {"this", "repository", "userId", Constant.SpConstKey.DEV_ID, "i", "this", "repository", "userId", Constant.SpConstKey.DEV_ID, "notSyncedData", "i", "this", "repository", "userId", Constant.SpConstKey.DEV_ID, "i"}, s = {"L$0", "L$1", "L$2", "L$3", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "I$0", "L$0", "L$1", "L$2", "L$3", "I$0"})
    /* renamed from: com.yucheng.smarthealthpro.repository.DataSyncRepository$syncBloodOxygenData$1, reason: invalid class name and case insensitive filesystem */
    static final class C02611 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        C02611(Continuation<? super C02611> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DataSyncRepository.this.syncBloodOxygenData(null, null, null, this);
        }
    }

    /* compiled from: DataSyncRepository.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.repository.DataSyncRepository", f = "DataSyncRepository.kt", i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2}, l = {266, DfuException.ERROR_ENTER_OTA_MODE_FAILED, Constants.DATATYPE.SettingBloodPressureMonitor}, m = "syncBloodPressureData", n = {"this", "repository", "userId", Constant.SpConstKey.DEV_ID, "i", "this", "repository", "userId", Constant.SpConstKey.DEV_ID, "notSyncedData", "i", "this", "repository", "userId", Constant.SpConstKey.DEV_ID, "i"}, s = {"L$0", "L$1", "L$2", "L$3", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "I$0", "L$0", "L$1", "L$2", "L$3", "I$0"})
    /* renamed from: com.yucheng.smarthealthpro.repository.DataSyncRepository$syncBloodPressureData$1, reason: invalid class name and case insensitive filesystem */
    static final class C02621 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        C02621(Continuation<? super C02621> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DataSyncRepository.this.syncBloodPressureData(null, null, null, this);
        }
    }

    /* compiled from: DataSyncRepository.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.repository.DataSyncRepository", f = "DataSyncRepository.kt", i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2}, l = {335, 352, 356}, m = "syncBloodSugarData", n = {"this", "repository", "userId", Constant.SpConstKey.DEV_ID, "i", "this", "repository", "userId", Constant.SpConstKey.DEV_ID, "notSyncedData", "i", "this", "repository", "userId", Constant.SpConstKey.DEV_ID, "i"}, s = {"L$0", "L$1", "L$2", "L$3", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "I$0", "L$0", "L$1", "L$2", "L$3", "I$0"})
    /* renamed from: com.yucheng.smarthealthpro.repository.DataSyncRepository$syncBloodSugarData$1, reason: invalid class name and case insensitive filesystem */
    static final class C02631 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        C02631(Continuation<? super C02631> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DataSyncRepository.this.syncBloodSugarData(null, null, null, this);
        }
    }

    /* compiled from: DataSyncRepository.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.repository.DataSyncRepository", f = "DataSyncRepository.kt", i = {0, 0, 0, 1, 1}, l = {527, Constants.DATATYPE.GetPowerStatistics, Constants.DATATYPE.GetAlgorithmicLicense}, m = "syncEcgData", n = {"this", "repository", Constant.SpConstKey.DEV_ID, "repository", "notSyncedData"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1"})
    /* renamed from: com.yucheng.smarthealthpro.repository.DataSyncRepository$syncEcgData$1, reason: invalid class name and case insensitive filesystem */
    static final class C02641 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C02641(Continuation<? super C02641> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DataSyncRepository.this.syncEcgData(null, null, null, this);
        }
    }

    /* compiled from: DataSyncRepository.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.repository.DataSyncRepository", f = "DataSyncRepository.kt", i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2}, l = {230, 247, 251}, m = "syncHeartRateData", n = {"this", "repository", "userId", Constant.SpConstKey.DEV_ID, "i", "this", "repository", "userId", Constant.SpConstKey.DEV_ID, "notSyncedData", "i", "this", "repository", "userId", Constant.SpConstKey.DEV_ID, "i"}, s = {"L$0", "L$1", "L$2", "L$3", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "I$0", "L$0", "L$1", "L$2", "L$3", "I$0"})
    /* renamed from: com.yucheng.smarthealthpro.repository.DataSyncRepository$syncHeartRateData$1, reason: invalid class name and case insensitive filesystem */
    static final class C02651 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        C02651(Continuation<? super C02651> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DataSyncRepository.this.syncHeartRateData(null, null, null, this);
        }
    }

    /* compiled from: DataSyncRepository.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.repository.DataSyncRepository", f = "DataSyncRepository.kt", i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2}, l = {367, 384, 385}, m = "syncHrvData", n = {"this", "repository", "userId", Constant.SpConstKey.DEV_ID, "i", "this", "repository", "userId", Constant.SpConstKey.DEV_ID, "notSyncedData", "i", "this", "repository", "userId", Constant.SpConstKey.DEV_ID, "i"}, s = {"L$0", "L$1", "L$2", "L$3", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "I$0", "L$0", "L$1", "L$2", "L$3", "I$0"})
    /* renamed from: com.yucheng.smarthealthpro.repository.DataSyncRepository$syncHrvData$1, reason: invalid class name and case insensitive filesystem */
    static final class C02661 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        C02661(Continuation<? super C02661> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DataSyncRepository.this.syncHrvData(null, null, null, this);
        }
    }

    /* compiled from: DataSyncRepository.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.repository.DataSyncRepository", f = "DataSyncRepository.kt", i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2}, l = {436, 452, 456}, m = "syncPhysiotherapyData", n = {"this", "repository", "userId", Constant.SpConstKey.DEV_ID, "i", "this", "repository", "userId", Constant.SpConstKey.DEV_ID, "notSyncedData", "i", "this", "repository", "userId", Constant.SpConstKey.DEV_ID, "i"}, s = {"L$0", "L$1", "L$2", "L$3", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "I$0", "L$0", "L$1", "L$2", "L$3", "I$0"})
    /* renamed from: com.yucheng.smarthealthpro.repository.DataSyncRepository$syncPhysiotherapyData$1, reason: invalid class name and case insensitive filesystem */
    static final class C02671 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        C02671(Continuation<? super C02671> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DataSyncRepository.this.syncPhysiotherapyData(null, null, null, this);
        }
    }

    /* compiled from: DataSyncRepository.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.repository.DataSyncRepository", f = "DataSyncRepository.kt", i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2}, l = {467, 484, 488}, m = "syncPressureData", n = {"this", "repository", "userId", Constant.SpConstKey.DEV_ID, "i", "this", "repository", "userId", Constant.SpConstKey.DEV_ID, "notSyncedData", "i", "this", "repository", "userId", Constant.SpConstKey.DEV_ID, "i"}, s = {"L$0", "L$1", "L$2", "L$3", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "I$0", "L$0", "L$1", "L$2", "L$3", "I$0"})
    /* renamed from: com.yucheng.smarthealthpro.repository.DataSyncRepository$syncPressureData$1, reason: invalid class name and case insensitive filesystem */
    static final class C02681 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        C02681(Continuation<? super C02681> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DataSyncRepository.this.syncPressureData(null, null, null, this);
        }
    }

    /* compiled from: DataSyncRepository.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.repository.DataSyncRepository", f = "DataSyncRepository.kt", i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2}, l = {400, 417, 421}, m = "syncRespiratoryRateData", n = {"this", "repository", "userId", Constant.SpConstKey.DEV_ID, "i", "this", "repository", "userId", Constant.SpConstKey.DEV_ID, "notSyncedData", "i", "this", "repository", "userId", Constant.SpConstKey.DEV_ID, "i"}, s = {"L$0", "L$1", "L$2", "L$3", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "I$0", "L$0", "L$1", "L$2", "L$3", "I$0"})
    /* renamed from: com.yucheng.smarthealthpro.repository.DataSyncRepository$syncRespiratoryRateData$1, reason: invalid class name and case insensitive filesystem */
    static final class C02691 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        C02691(Continuation<? super C02691> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DataSyncRepository.this.syncRespiratoryRateData(null, null, null, this);
        }
    }

    /* compiled from: DataSyncRepository.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.repository.DataSyncRepository", f = "DataSyncRepository.kt", i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2}, l = {Opcodes.ATHROW, 211, 215}, m = "syncSleepData", n = {"this", "repository", "userId", Constant.SpConstKey.DEV_ID, "i", "this", "repository", "userId", Constant.SpConstKey.DEV_ID, "notSyncedData", "i", "this", "repository", "userId", Constant.SpConstKey.DEV_ID, "i"}, s = {"L$0", "L$1", "L$2", "L$3", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "I$0", "L$0", "L$1", "L$2", "L$3", "I$0"})
    /* renamed from: com.yucheng.smarthealthpro.repository.DataSyncRepository$syncSleepData$1, reason: invalid class name and case insensitive filesystem */
    static final class C02701 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        C02701(Continuation<? super C02701> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DataSyncRepository.this.syncSleepData(null, null, null, this);
        }
    }

    /* compiled from: DataSyncRepository.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.repository.DataSyncRepository", f = "DataSyncRepository.kt", i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2}, l = {49, 69, 70}, m = "syncStepData", n = {"this", "repository", "userId", Constant.SpConstKey.DEV_ID, "i", "this", "repository", "userId", Constant.SpConstKey.DEV_ID, "notSyncedData", "i", "this", "repository", "userId", Constant.SpConstKey.DEV_ID, "i"}, s = {"L$0", "L$1", "L$2", "L$3", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "I$0", "L$0", "L$1", "L$2", "L$3", "I$0"})
    /* renamed from: com.yucheng.smarthealthpro.repository.DataSyncRepository$syncStepData$1, reason: invalid class name and case insensitive filesystem */
    static final class C02711 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        C02711(Continuation<? super C02711> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DataSyncRepository.this.syncStepData(null, null, null, this);
        }
    }

    /* compiled from: DataSyncRepository.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.repository.DataSyncRepository", f = "DataSyncRepository.kt", i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2}, l = {499, 516, 520}, m = "syncTemperatureData", n = {"this", "repository", "userId", Constant.SpConstKey.DEV_ID, "i", "this", "repository", "userId", Constant.SpConstKey.DEV_ID, "notSyncedData", "i", "this", "repository", "userId", Constant.SpConstKey.DEV_ID, "i"}, s = {"L$0", "L$1", "L$2", "L$3", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "I$0", "L$0", "L$1", "L$2", "L$3", "I$0"})
    /* renamed from: com.yucheng.smarthealthpro.repository.DataSyncRepository$syncTemperatureData$1, reason: invalid class name and case insensitive filesystem */
    static final class C02721 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        C02721(Continuation<? super C02721> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DataSyncRepository.this.syncTemperatureData(null, null, null, this);
        }
    }

    /* compiled from: DataSyncRepository.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.repository.DataSyncRepository", f = "DataSyncRepository.kt", i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2}, l = {Opcodes.LSHL, Opcodes.F2I, Opcodes.D2L}, m = "syncUricAcidData", n = {"this", "repository", "userId", Constant.SpConstKey.DEV_ID, "i", "this", "repository", "userId", Constant.SpConstKey.DEV_ID, "notSyncedData", "i", "this", "repository", "userId", Constant.SpConstKey.DEV_ID, "i"}, s = {"L$0", "L$1", "L$2", "L$3", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "I$0", "L$0", "L$1", "L$2", "L$3", "I$0"})
    /* renamed from: com.yucheng.smarthealthpro.repository.DataSyncRepository$syncUricAcidData$1, reason: invalid class name and case insensitive filesystem */
    static final class C02731 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        C02731(Continuation<? super C02731> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DataSyncRepository.this.syncUricAcidData(null, null, null, this);
        }
    }

    private final DataUploadRepository getDataUploadRepository() {
        return (DataUploadRepository) this.dataUploadRepository.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DataUploadRepository dataUploadRepository_delegate$lambda$0() {
        return new DataUploadRepository();
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x01a7 A[LOOP:1: B:38:0x01a1->B:40:0x01a7, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x01d8 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x01d9  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x01ec  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:44:0x01d9 -> B:45:0x01de). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object syncStepData(android.content.Context r28, com.yucheng.smarthealthpro.repository.StepRepository r29, java.lang.String r30, kotlin.coroutines.Continuation<? super kotlin.Unit> r31) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 495
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.repository.DataSyncRepository.syncStepData(android.content.Context, com.yucheng.smarthealthpro.repository.StepRepository, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x01be A[LOOP:1: B:38:0x01b8->B:40:0x01be, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x01ef A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x01f0  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x01fd  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:44:0x01f0 -> B:45:0x01f5). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object syncBloodLipidsData(android.content.Context r28, com.yucheng.smarthealthpro.repository.BloodLipidsRepository r29, java.lang.String r30, kotlin.coroutines.Continuation<? super kotlin.Unit> r31) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 512
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.repository.DataSyncRepository.syncBloodLipidsData(android.content.Context, com.yucheng.smarthealthpro.repository.BloodLipidsRepository, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x019e A[LOOP:1: B:38:0x0198->B:40:0x019e, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x01cf A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x01d0  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x01dd  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:44:0x01d0 -> B:45:0x01d5). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object syncUricAcidData(android.content.Context r25, com.yucheng.smarthealthpro.repository.UricAcidRepository r26, java.lang.String r27, kotlin.coroutines.Continuation<? super kotlin.Unit> r28) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 480
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.repository.DataSyncRepository.syncUricAcidData(android.content.Context, com.yucheng.smarthealthpro.repository.UricAcidRepository, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x019e A[LOOP:1: B:38:0x0198->B:40:0x019e, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x01cf A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x01d0  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x01dd  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:44:0x01d0 -> B:45:0x01d5). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object syncBloodKetoneData(android.content.Context r25, com.yucheng.smarthealthpro.repository.BloodKetonesRepository r26, java.lang.String r27, kotlin.coroutines.Continuation<? super kotlin.Unit> r28) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 480
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.repository.DataSyncRepository.syncBloodKetoneData(android.content.Context, com.yucheng.smarthealthpro.repository.BloodKetonesRepository, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x01b9 A[LOOP:1: B:38:0x01b3->B:40:0x01b9, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x01ea A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x01eb  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x01fe  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:44:0x01eb -> B:45:0x01f0). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object syncSleepData(android.content.Context r32, com.yucheng.smarthealthpro.repository.SleepRepository r33, java.lang.String r34, kotlin.coroutines.Continuation<? super kotlin.Unit> r35) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 513
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.repository.DataSyncRepository.syncSleepData(android.content.Context, com.yucheng.smarthealthpro.repository.SleepRepository, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0196 A[LOOP:1: B:38:0x0190->B:40:0x0196, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x01c7 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x01c8  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x01d3  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:44:0x01c8 -> B:45:0x01cc). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object syncHeartRateData(android.content.Context r25, com.yucheng.smarthealthpro.repository.HeartRateRepository r26, java.lang.String r27, kotlin.coroutines.Continuation<? super kotlin.Unit> r28) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 470
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.repository.DataSyncRepository.syncHeartRateData(android.content.Context, com.yucheng.smarthealthpro.repository.HeartRateRepository, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0192 A[LOOP:1: B:38:0x018c->B:40:0x0192, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x01c3 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x01c4  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x01d1  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:44:0x01c4 -> B:45:0x01c9). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object syncBloodPressureData(android.content.Context r25, com.yucheng.smarthealthpro.repository.BloodPressureRepository r26, java.lang.String r27, kotlin.coroutines.Continuation<? super kotlin.Unit> r28) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 468
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.repository.DataSyncRepository.syncBloodPressureData(android.content.Context, com.yucheng.smarthealthpro.repository.BloodPressureRepository, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0196 A[LOOP:1: B:38:0x0190->B:40:0x0196, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x01c7 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x01c8  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x01d3  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:44:0x01c8 -> B:45:0x01cc). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object syncBloodOxygenData(android.content.Context r25, com.yucheng.smarthealthpro.repository.HealthMetricRepository r26, java.lang.String r27, kotlin.coroutines.Continuation<? super kotlin.Unit> r28) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 470
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.repository.DataSyncRepository.syncBloodOxygenData(android.content.Context, com.yucheng.smarthealthpro.repository.HealthMetricRepository, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x019a A[LOOP:1: B:38:0x0194->B:40:0x019a, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x01cb A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x01cc  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x01d7  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:44:0x01cc -> B:45:0x01d0). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object syncBloodSugarData(android.content.Context r25, com.yucheng.smarthealthpro.repository.HealthMetricRepository r26, java.lang.String r27, kotlin.coroutines.Continuation<? super kotlin.Unit> r28) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 474
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.repository.DataSyncRepository.syncBloodSugarData(android.content.Context, com.yucheng.smarthealthpro.repository.HealthMetricRepository, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0196 A[LOOP:1: B:38:0x0190->B:40:0x0196, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x01c7 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x01c8  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x01d3  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:44:0x01c8 -> B:45:0x01cc). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object syncHrvData(android.content.Context r25, com.yucheng.smarthealthpro.repository.HealthMetricRepository r26, java.lang.String r27, kotlin.coroutines.Continuation<? super kotlin.Unit> r28) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 470
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.repository.DataSyncRepository.syncHrvData(android.content.Context, com.yucheng.smarthealthpro.repository.HealthMetricRepository, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0196 A[LOOP:1: B:38:0x0190->B:40:0x0196, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x01c7 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x01c8  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x01d3  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:44:0x01c8 -> B:45:0x01cc). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object syncRespiratoryRateData(android.content.Context r25, com.yucheng.smarthealthpro.repository.HealthMetricRepository r26, java.lang.String r27, kotlin.coroutines.Continuation<? super kotlin.Unit> r28) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 470
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.repository.DataSyncRepository.syncRespiratoryRateData(android.content.Context, com.yucheng.smarthealthpro.repository.HealthMetricRepository, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0199 A[LOOP:1: B:38:0x0193->B:40:0x0199, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x01ca A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x01cb  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x01d8  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:44:0x01cb -> B:45:0x01d0). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object syncPhysiotherapyData(android.content.Context r27, com.yucheng.smarthealthpro.repository.PhysiotherapyRepository r28, java.lang.String r29, kotlin.coroutines.Continuation<? super kotlin.Unit> r30) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 475
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.repository.DataSyncRepository.syncPhysiotherapyData(android.content.Context, com.yucheng.smarthealthpro.repository.PhysiotherapyRepository, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0196 A[LOOP:1: B:38:0x0190->B:40:0x0196, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x01c7 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x01c8  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x01d3  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:44:0x01c8 -> B:45:0x01cc). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object syncPressureData(android.content.Context r25, com.yucheng.smarthealthpro.repository.BodyDataRepository r26, java.lang.String r27, kotlin.coroutines.Continuation<? super kotlin.Unit> r28) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 470
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.repository.DataSyncRepository.syncPressureData(android.content.Context, com.yucheng.smarthealthpro.repository.BodyDataRepository, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0196 A[LOOP:1: B:38:0x0190->B:40:0x0196, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x01c7 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x01c8  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x01d3  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:44:0x01c8 -> B:45:0x01cc). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object syncTemperatureData(android.content.Context r25, com.yucheng.smarthealthpro.repository.HealthMetricRepository r26, java.lang.String r27, kotlin.coroutines.Continuation<? super kotlin.Unit> r28) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 470
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.repository.DataSyncRepository.syncTemperatureData(android.content.Context, com.yucheng.smarthealthpro.repository.HealthMetricRepository, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x0172 A[LOOP:0: B:35:0x016c->B:37:0x0172, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x019a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object syncEcgData(android.content.Context r45, com.yucheng.smarthealthpro.repository.EcgMeasureRepository r46, java.lang.String r47, kotlin.coroutines.Continuation<? super kotlin.Unit> r48) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 414
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.repository.DataSyncRepository.syncEcgData(android.content.Context, com.yucheng.smarthealthpro.repository.EcgMeasureRepository, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
