package com.yucheng.smarthealthpro.utils;

import android.content.Context;
import com.alibaba.fastjson2.internal.asm.Opcodes;
import com.google.android.gms.location.places.Place;
import com.orhanobut.logger.Logger;
import com.realsil.sdk.dfu.DfuException;
import com.yucheng.smarthealthpro.MyApplication;
import com.yucheng.smarthealthpro.framework.http.HttpUtils;
import com.yucheng.smarthealthpro.framework.util.Constants;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.repository.BloodKetonesRepository;
import com.yucheng.smarthealthpro.repository.BloodLipidsRepository;
import com.yucheng.smarthealthpro.repository.BloodPressureRepository;
import com.yucheng.smarthealthpro.repository.BodyDataRepository;
import com.yucheng.smarthealthpro.repository.EcgMeasureRepository;
import com.yucheng.smarthealthpro.repository.HealthMetricRepository;
import com.yucheng.smarthealthpro.repository.HeartRateRepository;
import com.yucheng.smarthealthpro.repository.MotionPatternRepository;
import com.yucheng.smarthealthpro.repository.PhysiotherapyRepository;
import com.yucheng.smarthealthpro.repository.SleepRepository;
import com.yucheng.smarthealthpro.repository.SportRecordRepository;
import com.yucheng.smarthealthpro.repository.StepRepository;
import com.yucheng.smarthealthpro.repository.UricAcidRepository;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.ycbtsdk.YCBTClient;
import com.yucheng.ycbtsdk.jl.WatchManager;
import com.yucheng.ycbtsdk.response.BleDataResponse;
import com.yucheng.ycbtsdk.utils.LogToFileUtils;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AnomalyDataMonitor.kt */
@Metadata(d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010L\u001a\u00020M2\u0006\u0010N\u001a\u00020OH\u0086@¢\u0006\u0002\u0010PJ\u0010\u0010Q\u001a\u00020R2\u0006\u0010N\u001a\u00020OH\u0002J\u0010\u0010S\u001a\u00020R2\u0006\u0010N\u001a\u00020OH\u0002J\u0010\u0010T\u001a\u00020R2\u0006\u0010N\u001a\u00020OH\u0002J\u0016\u0010U\u001a\u00020R2\u0006\u0010N\u001a\u00020OH\u0082@¢\u0006\u0002\u0010PJ\u0016\u0010V\u001a\u00020R2\u0006\u0010N\u001a\u00020OH\u0082@¢\u0006\u0002\u0010PJ\u0016\u0010W\u001a\u00020R2\u0006\u0010N\u001a\u00020OH\u0082@¢\u0006\u0002\u0010PJ\u000e\u0010X\u001a\u00020MH\u0086@¢\u0006\u0002\u0010YR\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u001b\u0010\n\u001a\u00020\u000b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\t\u001a\u0004\b\f\u0010\rR\u001b\u0010\u000f\u001a\u00020\u00108BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\t\u001a\u0004\b\u0011\u0010\u0012R\u001b\u0010\u0014\u001a\u00020\u00158BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\t\u001a\u0004\b\u0016\u0010\u0017R\u001b\u0010\u0019\u001a\u00020\u001a8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001d\u0010\t\u001a\u0004\b\u001b\u0010\u001cR\u001b\u0010\u001e\u001a\u00020\u001f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\"\u0010\t\u001a\u0004\b \u0010!R\u001b\u0010#\u001a\u00020$8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b'\u0010\t\u001a\u0004\b%\u0010&R\u001b\u0010(\u001a\u00020)8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b,\u0010\t\u001a\u0004\b*\u0010+R\u001b\u0010-\u001a\u00020.8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b1\u0010\t\u001a\u0004\b/\u00100R\u001b\u00102\u001a\u0002038BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b6\u0010\t\u001a\u0004\b4\u00105R\u001b\u00107\u001a\u0002088BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b;\u0010\t\u001a\u0004\b9\u0010:R\u001b\u0010<\u001a\u00020=8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b@\u0010\t\u001a\u0004\b>\u0010?R\u001b\u0010A\u001a\u00020B8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bE\u0010\t\u001a\u0004\bC\u0010DR\u0011\u0010F\u001a\u00020G¢\u0006\b\n\u0000\u001a\u0004\bH\u0010IR\u0011\u0010J\u001a\u00020G¢\u0006\b\n\u0000\u001a\u0004\bK\u0010I¨\u0006Z"}, d2 = {"Lcom/yucheng/smarthealthpro/utils/AnomalyDataMonitor;", "", "<init>", "()V", "healthMetricRepository", "Lcom/yucheng/smarthealthpro/repository/HealthMetricRepository;", "getHealthMetricRepository", "()Lcom/yucheng/smarthealthpro/repository/HealthMetricRepository;", "healthMetricRepository$delegate", "Lkotlin/Lazy;", "bloodKetonesRepository", "Lcom/yucheng/smarthealthpro/repository/BloodKetonesRepository;", "getBloodKetonesRepository", "()Lcom/yucheng/smarthealthpro/repository/BloodKetonesRepository;", "bloodKetonesRepository$delegate", "bloodLipidsRepository", "Lcom/yucheng/smarthealthpro/repository/BloodLipidsRepository;", "getBloodLipidsRepository", "()Lcom/yucheng/smarthealthpro/repository/BloodLipidsRepository;", "bloodLipidsRepository$delegate", "bloodPressureRepository", "Lcom/yucheng/smarthealthpro/repository/BloodPressureRepository;", "getBloodPressureRepository", "()Lcom/yucheng/smarthealthpro/repository/BloodPressureRepository;", "bloodPressureRepository$delegate", "bodyDataRepository", "Lcom/yucheng/smarthealthpro/repository/BodyDataRepository;", "getBodyDataRepository", "()Lcom/yucheng/smarthealthpro/repository/BodyDataRepository;", "bodyDataRepository$delegate", "ecgMeasureRepository", "Lcom/yucheng/smarthealthpro/repository/EcgMeasureRepository;", "getEcgMeasureRepository", "()Lcom/yucheng/smarthealthpro/repository/EcgMeasureRepository;", "ecgMeasureRepository$delegate", "heartRateRepository", "Lcom/yucheng/smarthealthpro/repository/HeartRateRepository;", "getHeartRateRepository", "()Lcom/yucheng/smarthealthpro/repository/HeartRateRepository;", "heartRateRepository$delegate", "motionPatternRepository", "Lcom/yucheng/smarthealthpro/repository/MotionPatternRepository;", "getMotionPatternRepository", "()Lcom/yucheng/smarthealthpro/repository/MotionPatternRepository;", "motionPatternRepository$delegate", "physiotherapyRepository", "Lcom/yucheng/smarthealthpro/repository/PhysiotherapyRepository;", "getPhysiotherapyRepository", "()Lcom/yucheng/smarthealthpro/repository/PhysiotherapyRepository;", "physiotherapyRepository$delegate", "sleepRepository", "Lcom/yucheng/smarthealthpro/repository/SleepRepository;", "getSleepRepository", "()Lcom/yucheng/smarthealthpro/repository/SleepRepository;", "sleepRepository$delegate", "sportRecordRepository", "Lcom/yucheng/smarthealthpro/repository/SportRecordRepository;", "getSportRecordRepository", "()Lcom/yucheng/smarthealthpro/repository/SportRecordRepository;", "sportRecordRepository$delegate", "stepRepository", "Lcom/yucheng/smarthealthpro/repository/StepRepository;", "getStepRepository", "()Lcom/yucheng/smarthealthpro/repository/StepRepository;", "stepRepository$delegate", "uricAcidRepository", "Lcom/yucheng/smarthealthpro/repository/UricAcidRepository;", "getUricAcidRepository", "()Lcom/yucheng/smarthealthpro/repository/UricAcidRepository;", "uricAcidRepository$delegate", "minute_15", "", "getMinute_15", "()I", "one_hour", "getOne_hour", "startMonitoring", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "shouldMonitorSleep", "", "shouldMonitorStep2Hr", "shouldMonitorModule", "sleepMonitoring", "stepToHrCorrelationMonitoring", "moduleCorrelationMonitoring", "uploadLogFile", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class AnomalyDataMonitor {
    public static final AnomalyDataMonitor INSTANCE = new AnomalyDataMonitor();

    /* renamed from: healthMetricRepository$delegate, reason: from kotlin metadata */
    private static final Lazy healthMetricRepository = LazyKt.lazy(new Function0() { // from class: com.yucheng.smarthealthpro.utils.AnomalyDataMonitor$$ExternalSyntheticLambda11
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return AnomalyDataMonitor.healthMetricRepository_delegate$lambda$0();
        }
    });

    /* renamed from: bloodKetonesRepository$delegate, reason: from kotlin metadata */
    private static final Lazy bloodKetonesRepository = LazyKt.lazy(new Function0() { // from class: com.yucheng.smarthealthpro.utils.AnomalyDataMonitor$$ExternalSyntheticLambda15
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return AnomalyDataMonitor.bloodKetonesRepository_delegate$lambda$1();
        }
    });

    /* renamed from: bloodLipidsRepository$delegate, reason: from kotlin metadata */
    private static final Lazy bloodLipidsRepository = LazyKt.lazy(new Function0() { // from class: com.yucheng.smarthealthpro.utils.AnomalyDataMonitor$$ExternalSyntheticLambda16
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return AnomalyDataMonitor.bloodLipidsRepository_delegate$lambda$2();
        }
    });

    /* renamed from: bloodPressureRepository$delegate, reason: from kotlin metadata */
    private static final Lazy bloodPressureRepository = LazyKt.lazy(new Function0() { // from class: com.yucheng.smarthealthpro.utils.AnomalyDataMonitor$$ExternalSyntheticLambda17
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return AnomalyDataMonitor.bloodPressureRepository_delegate$lambda$3();
        }
    });

    /* renamed from: bodyDataRepository$delegate, reason: from kotlin metadata */
    private static final Lazy bodyDataRepository = LazyKt.lazy(new Function0() { // from class: com.yucheng.smarthealthpro.utils.AnomalyDataMonitor$$ExternalSyntheticLambda18
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return AnomalyDataMonitor.bodyDataRepository_delegate$lambda$4();
        }
    });

    /* renamed from: ecgMeasureRepository$delegate, reason: from kotlin metadata */
    private static final Lazy ecgMeasureRepository = LazyKt.lazy(new Function0() { // from class: com.yucheng.smarthealthpro.utils.AnomalyDataMonitor$$ExternalSyntheticLambda19
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return AnomalyDataMonitor.ecgMeasureRepository_delegate$lambda$5();
        }
    });

    /* renamed from: heartRateRepository$delegate, reason: from kotlin metadata */
    private static final Lazy heartRateRepository = LazyKt.lazy(new Function0() { // from class: com.yucheng.smarthealthpro.utils.AnomalyDataMonitor$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return AnomalyDataMonitor.heartRateRepository_delegate$lambda$6();
        }
    });

    /* renamed from: motionPatternRepository$delegate, reason: from kotlin metadata */
    private static final Lazy motionPatternRepository = LazyKt.lazy(new Function0() { // from class: com.yucheng.smarthealthpro.utils.AnomalyDataMonitor$$ExternalSyntheticLambda2
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return AnomalyDataMonitor.motionPatternRepository_delegate$lambda$7();
        }
    });

    /* renamed from: physiotherapyRepository$delegate, reason: from kotlin metadata */
    private static final Lazy physiotherapyRepository = LazyKt.lazy(new Function0() { // from class: com.yucheng.smarthealthpro.utils.AnomalyDataMonitor$$ExternalSyntheticLambda3
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return AnomalyDataMonitor.physiotherapyRepository_delegate$lambda$8();
        }
    });

    /* renamed from: sleepRepository$delegate, reason: from kotlin metadata */
    private static final Lazy sleepRepository = LazyKt.lazy(new Function0() { // from class: com.yucheng.smarthealthpro.utils.AnomalyDataMonitor$$ExternalSyntheticLambda4
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return AnomalyDataMonitor.sleepRepository_delegate$lambda$9();
        }
    });

    /* renamed from: sportRecordRepository$delegate, reason: from kotlin metadata */
    private static final Lazy sportRecordRepository = LazyKt.lazy(new Function0() { // from class: com.yucheng.smarthealthpro.utils.AnomalyDataMonitor$$ExternalSyntheticLambda12
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return AnomalyDataMonitor.sportRecordRepository_delegate$lambda$10();
        }
    });

    /* renamed from: stepRepository$delegate, reason: from kotlin metadata */
    private static final Lazy stepRepository = LazyKt.lazy(new Function0() { // from class: com.yucheng.smarthealthpro.utils.AnomalyDataMonitor$$ExternalSyntheticLambda13
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return AnomalyDataMonitor.stepRepository_delegate$lambda$11();
        }
    });

    /* renamed from: uricAcidRepository$delegate, reason: from kotlin metadata */
    private static final Lazy uricAcidRepository = LazyKt.lazy(new Function0() { // from class: com.yucheng.smarthealthpro.utils.AnomalyDataMonitor$$ExternalSyntheticLambda14
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return AnomalyDataMonitor.uricAcidRepository_delegate$lambda$12();
        }
    });
    private static final int minute_15 = 900000;
    private static final int one_hour = 3600000;

    /* compiled from: AnomalyDataMonitor.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.utils.AnomalyDataMonitor", f = "AnomalyDataMonitor.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7}, l = {258, DfuException.ERROR_NO_CHARACTERISTIC_FOUND_OR_LOSS, DfuException.ERROR_READ_PATCH_INFO_ERROR, 334, 344, 354, 364, 374}, m = "moduleCorrelationMonitoring", n = {"this", "isSupportBloodPressure", "isSupportBloodOxygen", "isSupportRespiratoryRate", "isSupportTemperature", "isSupportBloodSugar", "isSupportBloodLipids", "isSupportUricAcid", "isSupportBloodKetone", "isSupportPhysiotherapy", "isSupportHrv", "isSupportPressure", "result", "endTime", "startTime", "this", "briefList", "isSupportBloodOxygen", "isSupportRespiratoryRate", "isSupportTemperature", "isSupportBloodSugar", "isSupportBloodLipids", "isSupportUricAcid", "isSupportBloodKetone", "isSupportPhysiotherapy", "isSupportHrv", "isSupportPressure", "result", "endTime", "startTime", "this", "briefList", "isSupportBloodOxygen", "isSupportRespiratoryRate", "isSupportTemperature", "isSupportBloodSugar", "isSupportBloodLipids", "isSupportUricAcid", "isSupportBloodKetone", "isSupportPhysiotherapy", "isSupportHrv", "isSupportPressure", "result", "endTime", "startTime", "this", "briefList", "isSupportUricAcid", "isSupportBloodKetone", "isSupportPhysiotherapy", "isSupportPressure", "result", "endTime", "startTime", "this", "briefList", "isSupportBloodKetone", "isSupportPhysiotherapy", "isSupportPressure", "result", "endTime", "startTime", "this", "briefList", "isSupportPhysiotherapy", "isSupportPressure", "result", "endTime", "startTime", "this", "briefList", "isSupportPressure", "result", "endTime", "startTime", "briefList", "result", "endTime", "startTime"}, s = {"L$0", "Z$0", "Z$1", "Z$2", "Z$3", "I$0", "I$1", "I$2", "Z$4", "Z$5", "Z$6", "Z$7", "I$3", "J$0", "J$1", "L$0", "L$1", "Z$0", "Z$1", "Z$2", "I$0", "I$1", "I$2", "Z$3", "Z$4", "Z$5", "Z$6", "I$3", "J$0", "J$1", "L$0", "L$1", "Z$0", "Z$1", "Z$2", "I$0", "I$1", "I$2", "Z$3", "Z$4", "Z$5", "Z$6", "I$3", "J$0", "J$1", "L$0", "L$1", "I$0", "Z$0", "Z$1", "Z$2", "I$1", "J$0", "J$1", "L$0", "L$1", "Z$0", "Z$1", "Z$2", "I$0", "J$0", "J$1", "L$0", "L$1", "Z$0", "Z$1", "I$0", "J$0", "J$1", "L$0", "L$1", "Z$0", "I$0", "J$0", "J$1", "L$0", "I$0", "J$0", "J$1"})
    /* renamed from: com.yucheng.smarthealthpro.utils.AnomalyDataMonitor$moduleCorrelationMonitoring$1, reason: invalid class name */
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        long J$0;
        long J$1;
        Object L$0;
        Object L$1;
        boolean Z$0;
        boolean Z$1;
        boolean Z$2;
        boolean Z$3;
        boolean Z$4;
        boolean Z$5;
        boolean Z$6;
        boolean Z$7;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AnomalyDataMonitor.this.moduleCorrelationMonitoring(null, this);
        }
    }

    /* compiled from: AnomalyDataMonitor.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.utils.AnomalyDataMonitor", f = "AnomalyDataMonitor.kt", i = {0, 0, 0, 1, 1, 1}, l = {Opcodes.FCMPG, Opcodes.DCMPG}, m = "sleepMonitoring", n = {"this", "yesterday8PmTimestamp", "today12PmTimestamp", "heartRateByTimeRange", "yesterday8PmTimestamp", "today12PmTimestamp"}, s = {"L$0", "J$0", "J$1", "L$0", "J$0", "J$1"})
    /* renamed from: com.yucheng.smarthealthpro.utils.AnomalyDataMonitor$sleepMonitoring$1, reason: invalid class name and case insensitive filesystem */
    static final class C03131 extends ContinuationImpl {
        long J$0;
        long J$1;
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C03131(Continuation<? super C03131> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AnomalyDataMonitor.this.sleepMonitoring(null, this);
        }
    }

    /* compiled from: AnomalyDataMonitor.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.utils.AnomalyDataMonitor", f = "AnomalyDataMonitor.kt", i = {0, 0, 1, 1, 2}, l = {82, 88, Place.TYPE_UNIVERSITY, 97}, m = "startMonitoring", n = {"this", "context", "this", "context", "this"}, s = {"L$0", "L$1", "L$0", "L$1", "L$0"})
    /* renamed from: com.yucheng.smarthealthpro.utils.AnomalyDataMonitor$startMonitoring$1, reason: invalid class name and case insensitive filesystem */
    static final class C03141 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C03141(Continuation<? super C03141> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AnomalyDataMonitor.this.startMonitoring(null, this);
        }
    }

    /* compiled from: AnomalyDataMonitor.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.utils.AnomalyDataMonitor", f = "AnomalyDataMonitor.kt", i = {0, 0, 0, 1, 1, 1}, l = {Opcodes.TABLESWITCH, Opcodes.IRETURN}, m = "stepToHrCorrelationMonitoring", n = {"this", "currentTimeMillis", "oneHourAgo", "stepByTimeRange", "currentTimeMillis", "oneHourAgo"}, s = {"L$0", "J$0", "J$1", "L$0", "J$0", "J$1"})
    /* renamed from: com.yucheng.smarthealthpro.utils.AnomalyDataMonitor$stepToHrCorrelationMonitoring$1, reason: invalid class name and case insensitive filesystem */
    static final class C03151 extends ContinuationImpl {
        long J$0;
        long J$1;
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C03151(Continuation<? super C03151> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AnomalyDataMonitor.this.stepToHrCorrelationMonitoring(null, this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void uploadLogFile$lambda$29$lambda$23$lambda$22(String str) {
    }

    private AnomalyDataMonitor() {
    }

    private final HealthMetricRepository getHealthMetricRepository() {
        return (HealthMetricRepository) healthMetricRepository.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final HealthMetricRepository healthMetricRepository_delegate$lambda$0() {
        return new HealthMetricRepository(MyApplication.sInstance.getAppDatabase().healthMetricDao());
    }

    private final BloodKetonesRepository getBloodKetonesRepository() {
        return (BloodKetonesRepository) bloodKetonesRepository.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final BloodKetonesRepository bloodKetonesRepository_delegate$lambda$1() {
        return new BloodKetonesRepository(MyApplication.sInstance.getAppDatabase().bloodKetonesDao());
    }

    private final BloodLipidsRepository getBloodLipidsRepository() {
        return (BloodLipidsRepository) bloodLipidsRepository.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final BloodLipidsRepository bloodLipidsRepository_delegate$lambda$2() {
        return new BloodLipidsRepository(MyApplication.sInstance.getAppDatabase().bloodLipidsDao());
    }

    private final BloodPressureRepository getBloodPressureRepository() {
        return (BloodPressureRepository) bloodPressureRepository.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final BloodPressureRepository bloodPressureRepository_delegate$lambda$3() {
        return new BloodPressureRepository(MyApplication.sInstance.getAppDatabase().bloodPressureDao());
    }

    private final BodyDataRepository getBodyDataRepository() {
        return (BodyDataRepository) bodyDataRepository.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final BodyDataRepository bodyDataRepository_delegate$lambda$4() {
        return new BodyDataRepository(MyApplication.sInstance.getAppDatabase().bodyDataDao());
    }

    private final EcgMeasureRepository getEcgMeasureRepository() {
        return (EcgMeasureRepository) ecgMeasureRepository.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final EcgMeasureRepository ecgMeasureRepository_delegate$lambda$5() {
        return new EcgMeasureRepository(MyApplication.sInstance.getAppDatabase().ecgMeasureDao());
    }

    private final HeartRateRepository getHeartRateRepository() {
        return (HeartRateRepository) heartRateRepository.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final HeartRateRepository heartRateRepository_delegate$lambda$6() {
        return new HeartRateRepository(MyApplication.sInstance.getAppDatabase().heartRateDao());
    }

    private final MotionPatternRepository getMotionPatternRepository() {
        return (MotionPatternRepository) motionPatternRepository.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MotionPatternRepository motionPatternRepository_delegate$lambda$7() {
        return new MotionPatternRepository(MyApplication.sInstance.getAppDatabase().motionPatternDao());
    }

    private final PhysiotherapyRepository getPhysiotherapyRepository() {
        return (PhysiotherapyRepository) physiotherapyRepository.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final PhysiotherapyRepository physiotherapyRepository_delegate$lambda$8() {
        return new PhysiotherapyRepository(MyApplication.sInstance.getAppDatabase().physiotherapyDao());
    }

    private final SleepRepository getSleepRepository() {
        return (SleepRepository) sleepRepository.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final SleepRepository sleepRepository_delegate$lambda$9() {
        return new SleepRepository(MyApplication.sInstance.getAppDatabase().sleepDao());
    }

    private final SportRecordRepository getSportRecordRepository() {
        return (SportRecordRepository) sportRecordRepository.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final SportRecordRepository sportRecordRepository_delegate$lambda$10() {
        return new SportRecordRepository(MyApplication.sInstance.getAppDatabase().sportRecordDao());
    }

    private final StepRepository getStepRepository() {
        return (StepRepository) stepRepository.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final StepRepository stepRepository_delegate$lambda$11() {
        return new StepRepository(MyApplication.sInstance.getAppDatabase().stepDao());
    }

    private final UricAcidRepository getUricAcidRepository() {
        return (UricAcidRepository) uricAcidRepository.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final UricAcidRepository uricAcidRepository_delegate$lambda$12() {
        return new UricAcidRepository(MyApplication.sInstance.getAppDatabase().uricAcidDao());
    }

    public final int getMinute_15() {
        return minute_15;
    }

    public final int getOne_hour() {
        return one_hour;
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00fe  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x013a  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0140  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0151  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object startMonitoring(android.content.Context r18, kotlin.coroutines.Continuation<? super kotlin.Unit> r19) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 340
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.utils.AnomalyDataMonitor.startMonitoring(android.content.Context, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [java.time.ZonedDateTime] */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.time.ZonedDateTime] */
    private final boolean shouldMonitorSleep(Context context) {
        LocalDateTime.now();
        LocalDateTime localDateTimeAtTime = LocalDate.now().atTime(20, 0);
        LocalDateTime localDateTimeAtTime2 = LocalDate.now().atTime(12, 0);
        long epochMilli = localDateTimeAtTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long epochMilli2 = localDateTimeAtTime2.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        Object obj = SharedPreferencesUtils.get(context, Constant.SpConstKey.sleepMonitorTimeStamp, 0L);
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Long");
        long jLongValue = ((Long) obj).longValue();
        long jCurrentTimeMillis = System.currentTimeMillis();
        return epochMilli2 <= jCurrentTimeMillis && jCurrentTimeMillis < epochMilli && jCurrentTimeMillis - jLongValue > ((long) minute_15);
    }

    private final boolean shouldMonitorStep2Hr(Context context) {
        Object obj = SharedPreferencesUtils.get(context, Constant.SpConstKey.step2HrCorrelationTimeStamp, 0L);
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Long");
        return System.currentTimeMillis() - ((Long) obj).longValue() > ((long) one_hour);
    }

    private final boolean shouldMonitorModule(Context context) {
        Object obj = SharedPreferencesUtils.get(context, Constant.SpConstKey.moduleCorrelationTimeStamp, 0L);
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Long");
        return System.currentTimeMillis() - ((Long) obj).longValue() > ((long) one_hour);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0136 A[LOOP:0: B:33:0x0130->B:35:0x0136, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /* JADX WARN: Type inference failed for: r1v5, types: [java.time.ZonedDateTime] */
    /* JADX WARN: Type inference failed for: r1v8, types: [java.time.ZonedDateTime] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object sleepMonitoring(android.content.Context r20, kotlin.coroutines.Continuation<? super java.lang.Boolean> r21) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 400
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.utils.AnomalyDataMonitor.sleepMonitoring(android.content.Context, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0101 A[LOOP:0: B:33:0x00fb->B:35:0x0101, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object stepToHrCorrelationMonitoring(android.content.Context r20, kotlin.coroutines.Continuation<? super java.lang.Boolean> r21) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 348
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.utils.AnomalyDataMonitor.stepToHrCorrelationMonitoring(android.content.Context, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:102:0x06ba  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0752  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x076e  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x07e6  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x07ea  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x085d  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x08d0  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x093e  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x09a5  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x09ed  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x0a26  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x0ac1  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x0ad4  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x0af2  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x0b8d  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x0b9f  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x0bc1  */
    /* JADX WARN: Removed duplicated region for block: B:221:0x0c49  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x0c5a  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x0c74  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x0ca9  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x0cee  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x0cf0  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x0cf3  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x0cf8  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x0cfa  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0452 A[LOOP:5: B:73:0x044c->B:75:0x0452, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x04d5  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x04d9  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x05c5  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x06a7 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:99:0x06a8  */
    /* JADX WARN: Type inference failed for: r3v47, types: [java.util.List] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object moduleCorrelationMonitoring(android.content.Context r42, kotlin.coroutines.Continuation<? super java.lang.Boolean> r43) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 3352
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.utils.AnomalyDataMonitor.moduleCorrelationMonitoring(android.content.Context, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final Object uploadLogFile(Continuation<? super Unit> continuation) {
        ThreadUtils.execute(new Runnable() { // from class: com.yucheng.smarthealthpro.utils.AnomalyDataMonitor$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() throws IOException {
                AnomalyDataMonitor.uploadLogFile$lambda$29();
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void uploadLogFile$lambda$29() throws IOException {
        String str;
        File logFile = LogToFileUtils.getLogFile("yclogs.txt");
        final String str2 = "android_" + YCBTClient.getBindDeviceMac() + "_" + System.currentTimeMillis() + "_yc_log.txt";
        final String str3 = "android_" + YCBTClient.getBindDeviceMac() + "_" + System.currentTimeMillis() + "_jl_log.txt";
        File logFile2 = LogToFileUtils.getLogFile(str2);
        long fileSize = LogToFileUtils.getFileSize(logFile);
        MLog.INSTANCE.d("开始上传SDK日志文件");
        MLog.INSTANCE.d("SDK 日志文件大小：" + fileSize);
        JxdUtils.copy(logFile, logFile2);
        HttpUtils.getInstance().uploadV2(MyApplication.getInstance().getApplicationContext(), Constants.UploadFileToCloud, "file", logFile2, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.utils.AnomalyDataMonitor$$ExternalSyntheticLambda6
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public final void onSuccess(String str4) {
                AnomalyDataMonitor.uploadLogFile$lambda$29$lambda$21(str2, str4);
            }
        });
        if (YCBTClient.connectState() == 10 && YCBTClient.isJieLi()) {
            WatchManager.getInstance().getLog(new BleDataResponse() { // from class: com.yucheng.smarthealthpro.utils.AnomalyDataMonitor$$ExternalSyntheticLambda7
                @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                public final void onDataResponse(int i2, float f2, HashMap map) throws Throwable {
                    AnomalyDataMonitor.uploadLogFile$lambda$29$lambda$23(str3, i2, f2, map);
                }
            });
        }
        File[] logFiles = AppLogManager.getInstance().getLogFiles();
        Intrinsics.checkNotNullExpressionValue(logFiles, "getLogFiles(...)");
        File file = (File) ArraysKt.firstOrNull(logFiles);
        if (file != null) {
            final String str4 = "android_" + YCBTClient.getBindDeviceMac() + "_" + System.currentTimeMillis() + "_app_log.txt";
            File logFile3 = LogToFileUtils.getLogFile(str4);
            JxdUtils.copy(file, logFile3);
            MLog.INSTANCE.d("开始上传APP日志文件");
            MLog.INSTANCE.d("APP 日志文件大小：" + fileSize);
            HttpUtils.getInstance().uploadV2(MyApplication.getInstance().getApplicationContext(), Constants.UploadFileToCloud, "file", logFile3, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.utils.AnomalyDataMonitor$$ExternalSyntheticLambda8
                @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
                public final void onSuccess(String str5) {
                    AnomalyDataMonitor.uploadLogFile$lambda$29$lambda$25$lambda$24(str4, str5);
                }
            });
        }
        if (Constant.isSmartHealth()) {
            str = "smart_health.db";
        } else {
            str = "health.db";
        }
        File databasePath = MyApplication.sInstance.getDatabasePath(str);
        final String str5 = "android_" + YCBTClient.getBindDeviceMac() + "_" + System.currentTimeMillis() + "_health.db";
        File logFile4 = LogToFileUtils.getLogFile(str5);
        JxdUtils.copy(databasePath, logFile4);
        long fileSize2 = LogToFileUtils.getFileSize(databasePath);
        MLog.INSTANCE.d("开始上传数据库文件");
        MLog.INSTANCE.d("数据库文件大小：" + fileSize2);
        HttpUtils.getInstance().uploadV2(MyApplication.getInstance().getApplicationContext(), Constants.UploadFileToCloud, "file", logFile4, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.utils.AnomalyDataMonitor$$ExternalSyntheticLambda9
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public final void onSuccess(String str6) {
                AnomalyDataMonitor.uploadLogFile$lambda$29$lambda$28$lambda$26(str5, str6);
            }
        });
        File file2 = new File(databasePath.getParent(), databasePath.getName() + "-wal");
        final String str6 = str5 + "-wal";
        File logFile5 = LogToFileUtils.getLogFile(str6);
        JxdUtils.copy(file2, logFile5);
        long fileSize3 = LogToFileUtils.getFileSize(file2);
        MLog.INSTANCE.d("开始上传数据库wal文件");
        MLog.INSTANCE.d("数据库wal文件大小：" + fileSize3);
        HttpUtils.getInstance().uploadV2(MyApplication.getInstance().getApplicationContext(), Constants.UploadFileToCloud, "file", logFile5, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.utils.AnomalyDataMonitor$$ExternalSyntheticLambda10
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public final void onSuccess(String str7) {
                AnomalyDataMonitor.uploadLogFile$lambda$29$lambda$28$lambda$27(str6, str7);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void uploadLogFile$lambda$29$lambda$21(String str, String str2) {
        LogToFileUtils.deleteLogFile(str);
        if (str2 != null) {
            MLog.INSTANCE.d("SDK日志文件上传完成");
        } else {
            MLog.INSTANCE.d("SDK日志文件上传失败");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void uploadLogFile$lambda$29$lambda$23(String str, int i2, float f2, HashMap map) throws Throwable {
        if (i2 == 0 && map != null && map.containsKey("size")) {
            Object obj = map.get("size");
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Int");
            ((Integer) obj).intValue();
            String strWriteJLLog = LogToFileUtils.writeJLLog(str, (byte[]) map.get("data"));
            File file = new File(strWriteJLLog);
            Logger.d("UploadFile upload=" + strWriteJLLog, new Object[0]);
            if (file.length() > 0) {
                HttpUtils.getInstance().uploadV2(MyApplication.getInstance().getApplicationContext(), Constants.UploadFileToCloud, "file", file, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.utils.AnomalyDataMonitor$$ExternalSyntheticLambda5
                    @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
                    public final void onSuccess(String str2) {
                        AnomalyDataMonitor.uploadLogFile$lambda$29$lambda$23$lambda$22(str2);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void uploadLogFile$lambda$29$lambda$25$lambda$24(String str, String str2) {
        LogToFileUtils.deleteLogFile(str);
        if (str2 != null) {
            MLog.INSTANCE.d("APP日志文件上传完成");
        } else {
            MLog.INSTANCE.d("APP日志文件上传失败");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void uploadLogFile$lambda$29$lambda$28$lambda$26(String str, String str2) {
        LogToFileUtils.deleteLogFile(str);
        if (str2 != null) {
            MLog.INSTANCE.d("数据库文件上传完成");
        } else {
            MLog.INSTANCE.d("数据库上传失败");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void uploadLogFile$lambda$29$lambda$28$lambda$27(String str, String str2) {
        LogToFileUtils.deleteLogFile(str);
        if (str2 != null) {
            MLog.INSTANCE.d("数据库wal文件上传完成");
        } else {
            MLog.INSTANCE.d("数据库wal上传失败");
        }
    }
}
