package com.yucheng.smarthealthpro.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.alibaba.fastjson2.internal.asm.Opcodes;
import com.jieli.jl_rcsp.constant.Command;
import com.yucheng.smarthealthpro.MyApplication;
import com.yucheng.smarthealthpro.database.room.bean.BloodKetones;
import com.yucheng.smarthealthpro.database.room.bean.BloodLipids;
import com.yucheng.smarthealthpro.database.room.bean.BloodPressure;
import com.yucheng.smarthealthpro.database.room.bean.BodyData;
import com.yucheng.smarthealthpro.database.room.bean.HealthMetric;
import com.yucheng.smarthealthpro.database.room.bean.HeartRate;
import com.yucheng.smarthealthpro.database.room.bean.Physiotherapy;
import com.yucheng.smarthealthpro.database.room.bean.Sleep;
import com.yucheng.smarthealthpro.database.room.bean.UricAcid;
import com.yucheng.smarthealthpro.login.normal.util.UserInfoUtil;
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
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.SharedFlowKt;

/* compiled from: HomeViewModel.kt */
@Metadata(d1 = {"\u0000Ü\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010{\u001a\u00020|2\u0006\u0010}\u001a\u00020~J\u000e\u0010\u007f\u001a\u00020|2\u0006\u0010}\u001a\u00020~J\u000f\u0010\u0080\u0001\u001a\u00020|2\u0006\u0010}\u001a\u00020~J\u000f\u0010\u0081\u0001\u001a\u00020|2\u0006\u0010}\u001a\u00020~J\u000f\u0010\u0082\u0001\u001a\u00020|2\u0006\u0010}\u001a\u00020~J\u000f\u0010\u0083\u0001\u001a\u00020|2\u0006\u0010}\u001a\u00020~J\u000f\u0010\u0084\u0001\u001a\u00020|2\u0006\u0010}\u001a\u00020~J\u000f\u0010\u0085\u0001\u001a\u00020|2\u0006\u0010}\u001a\u00020~J\u000f\u0010\u0086\u0001\u001a\u00020|2\u0006\u0010}\u001a\u00020~J\u000f\u0010\u0087\u0001\u001a\u00020|2\u0006\u0010}\u001a\u00020~J\u000f\u0010\u0088\u0001\u001a\u00020|2\u0006\u0010}\u001a\u00020~J\u000f\u0010\u0089\u0001\u001a\u00020|2\u0006\u0010}\u001a\u00020~J\u0019\u0010\u008a\u0001\u001a\u00020|2\u0007\u0010\u008b\u0001\u001a\u00020~2\u0007\u0010\u008c\u0001\u001a\u00020~R\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u001b\u0010\n\u001a\u00020\u000b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\t\u001a\u0004\b\f\u0010\rR\u001b\u0010\u000f\u001a\u00020\u00108BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\t\u001a\u0004\b\u0011\u0010\u0012R\u001b\u0010\u0014\u001a\u00020\u00158BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\t\u001a\u0004\b\u0016\u0010\u0017R\u001b\u0010\u0019\u001a\u00020\u001a8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001d\u0010\t\u001a\u0004\b\u001b\u0010\u001cR\u001b\u0010\u001e\u001a\u00020\u001f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\"\u0010\t\u001a\u0004\b \u0010!R\u001b\u0010#\u001a\u00020$8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b'\u0010\t\u001a\u0004\b%\u0010&R\u001b\u0010(\u001a\u00020)8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b,\u0010\t\u001a\u0004\b*\u0010+R\u001b\u0010-\u001a\u00020.8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b1\u0010\t\u001a\u0004\b/\u00100R\u001b\u00102\u001a\u0002038BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b6\u0010\t\u001a\u0004\b4\u00105R\u001b\u00107\u001a\u0002088BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b;\u0010\t\u001a\u0004\b9\u0010:R\u001b\u0010<\u001a\u00020=8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b@\u0010\t\u001a\u0004\b>\u0010?R\u001b\u0010A\u001a\u00020B8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bE\u0010\t\u001a\u0004\bC\u0010DR\u001a\u0010F\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020I0H0GX\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010J\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020I0H0K8F¢\u0006\u0006\u001a\u0004\bL\u0010MR\u001a\u0010N\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020O0H0GX\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010P\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020O0H0K8F¢\u0006\u0006\u001a\u0004\bQ\u0010MR\u001a\u0010R\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020S0H0GX\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010T\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020S0H0K8F¢\u0006\u0006\u001a\u0004\bU\u0010MR\u001a\u0010V\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020I0H0GX\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010W\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020I0H0K8F¢\u0006\u0006\u001a\u0004\bX\u0010MR\u001a\u0010Y\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020I0H0GX\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010Z\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020I0H0K8F¢\u0006\u0006\u001a\u0004\b[\u0010MR\u001a\u0010\\\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020I0H0GX\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010]\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020I0H0K8F¢\u0006\u0006\u001a\u0004\b^\u0010MR\u001a\u0010_\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020I0H0GX\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010`\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020I0H0K8F¢\u0006\u0006\u001a\u0004\ba\u0010MR\u001a\u0010b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020c0H0GX\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020c0H0K8F¢\u0006\u0006\u001a\u0004\be\u0010MR\u001a\u0010f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020g0H0GX\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010h\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020g0H0K8F¢\u0006\u0006\u001a\u0004\bi\u0010MR\u001a\u0010j\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020k0H0GX\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010l\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020k0H0K8F¢\u0006\u0006\u001a\u0004\bm\u0010MR\u001a\u0010n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020o0H0GX\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010p\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020o0H0K8F¢\u0006\u0006\u001a\u0004\bq\u0010MR\u001a\u0010r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020s0H0GX\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020s0H0K8F¢\u0006\u0006\u001a\u0004\bu\u0010MR,\u0010v\u001a \u0012\u001c\u0012\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020x0H\u0012\n\u0012\b\u0012\u0004\u0012\u00020x0H0w0GX\u0082\u0004¢\u0006\u0002\n\u0000R/\u0010y\u001a \u0012\u001c\u0012\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020x0H\u0012\n\u0012\b\u0012\u0004\u0012\u00020x0H0w0K8F¢\u0006\u0006\u001a\u0004\bz\u0010M¨\u0006\u008d\u0001"}, d2 = {"Lcom/yucheng/smarthealthpro/viewmodel/HomeViewModel;", "Landroidx/lifecycle/ViewModel;", "<init>", "()V", "healthMetricRepository", "Lcom/yucheng/smarthealthpro/repository/HealthMetricRepository;", "getHealthMetricRepository", "()Lcom/yucheng/smarthealthpro/repository/HealthMetricRepository;", "healthMetricRepository$delegate", "Lkotlin/Lazy;", "bloodKetonesRepository", "Lcom/yucheng/smarthealthpro/repository/BloodKetonesRepository;", "getBloodKetonesRepository", "()Lcom/yucheng/smarthealthpro/repository/BloodKetonesRepository;", "bloodKetonesRepository$delegate", "bloodLipidsRepository", "Lcom/yucheng/smarthealthpro/repository/BloodLipidsRepository;", "getBloodLipidsRepository", "()Lcom/yucheng/smarthealthpro/repository/BloodLipidsRepository;", "bloodLipidsRepository$delegate", "bloodPressureRepository", "Lcom/yucheng/smarthealthpro/repository/BloodPressureRepository;", "getBloodPressureRepository", "()Lcom/yucheng/smarthealthpro/repository/BloodPressureRepository;", "bloodPressureRepository$delegate", "bodyDataRepository", "Lcom/yucheng/smarthealthpro/repository/BodyDataRepository;", "getBodyDataRepository", "()Lcom/yucheng/smarthealthpro/repository/BodyDataRepository;", "bodyDataRepository$delegate", "ecgMeasureRepository", "Lcom/yucheng/smarthealthpro/repository/EcgMeasureRepository;", "getEcgMeasureRepository", "()Lcom/yucheng/smarthealthpro/repository/EcgMeasureRepository;", "ecgMeasureRepository$delegate", "heartRateRepository", "Lcom/yucheng/smarthealthpro/repository/HeartRateRepository;", "getHeartRateRepository", "()Lcom/yucheng/smarthealthpro/repository/HeartRateRepository;", "heartRateRepository$delegate", "motionPatternRepository", "Lcom/yucheng/smarthealthpro/repository/MotionPatternRepository;", "getMotionPatternRepository", "()Lcom/yucheng/smarthealthpro/repository/MotionPatternRepository;", "motionPatternRepository$delegate", "physiotherapyRepository", "Lcom/yucheng/smarthealthpro/repository/PhysiotherapyRepository;", "getPhysiotherapyRepository", "()Lcom/yucheng/smarthealthpro/repository/PhysiotherapyRepository;", "physiotherapyRepository$delegate", "sleepRepository", "Lcom/yucheng/smarthealthpro/repository/SleepRepository;", "getSleepRepository", "()Lcom/yucheng/smarthealthpro/repository/SleepRepository;", "sleepRepository$delegate", "sportRecordRepository", "Lcom/yucheng/smarthealthpro/repository/SportRecordRepository;", "getSportRecordRepository", "()Lcom/yucheng/smarthealthpro/repository/SportRecordRepository;", "sportRecordRepository$delegate", "stepRepository", "Lcom/yucheng/smarthealthpro/repository/StepRepository;", "getStepRepository", "()Lcom/yucheng/smarthealthpro/repository/StepRepository;", "stepRepository$delegate", "uricAcidRepository", "Lcom/yucheng/smarthealthpro/repository/UricAcidRepository;", "getUricAcidRepository", "()Lcom/yucheng/smarthealthpro/repository/UricAcidRepository;", "uricAcidRepository$delegate", "_hrvDataFlow", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "", "Lcom/yucheng/smarthealthpro/database/room/bean/HealthMetric;", "hrvDataFlow", "Lkotlinx/coroutines/flow/SharedFlow;", "getHrvDataFlow", "()Lkotlinx/coroutines/flow/SharedFlow;", "_heartRateDataFlow", "Lcom/yucheng/smarthealthpro/database/room/bean/HeartRate;", "heartRateDataFlow", "getHeartRateDataFlow", "_bloodPressureDataFlow", "Lcom/yucheng/smarthealthpro/database/room/bean/BloodPressure;", "bloodPressureDataFlow", "getBloodPressureDataFlow", "_respiratoryRateDataFlow", "respiratoryRateDataFlow", "getRespiratoryRateDataFlow", "_bloodOxygenDataFlow", "bloodOxygenDataFlow", "getBloodOxygenDataFlow", "_temperatureDataFlow", "temperatureDataFlow", "getTemperatureDataFlow", "_bloodSugarDataFlow", "bloodSugarDataFlow", "getBloodSugarDataFlow", "_bloodLipidsDataFlow", "Lcom/yucheng/smarthealthpro/database/room/bean/BloodLipids;", "bloodLipidsDataFlow", "getBloodLipidsDataFlow", "_uricAcidDataFlow", "Lcom/yucheng/smarthealthpro/database/room/bean/UricAcid;", "uricAcidDataFlow", "getUricAcidDataFlow", "_bloodKetonesDataFlow", "Lcom/yucheng/smarthealthpro/database/room/bean/BloodKetones;", "bloodKetonesDataFlow", "getBloodKetonesDataFlow", "_physiotherapyDataFlow", "Lcom/yucheng/smarthealthpro/database/room/bean/Physiotherapy;", "physiotherapyDataFlow", "getPhysiotherapyDataFlow", "_pressureDataFlow", "Lcom/yucheng/smarthealthpro/database/room/bean/BodyData;", "pressureDataFlow", "getPressureDataFlow", "_sleepDataFlow", "Lkotlin/Pair;", "Lcom/yucheng/smarthealthpro/database/room/bean/Sleep;", "sleepDataFlow", "getSleepDataFlow", "getHrvDayData", "", "dayStr", "", "getHeartRateDayData", "getBloodPressureDayData", "getRespiratoryRateDayData", "getBloodOxygenDayData", "getTemperatureDayData", "getBloodSugarDayData", "getBloodLipidsDayData", "getUricAcidDayData", "getBloodKetonesDayData", "getPhysiotherapyDayData", "getPressureDayData", "getSleepData", "dayStr1", "dayStr2", "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class HomeViewModel extends ViewModel {

    /* renamed from: healthMetricRepository$delegate, reason: from kotlin metadata */
    private final Lazy healthMetricRepository = LazyKt.lazy(new Function0() { // from class: com.yucheng.smarthealthpro.viewmodel.HomeViewModel$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return HomeViewModel.healthMetricRepository_delegate$lambda$0();
        }
    });

    /* renamed from: bloodKetonesRepository$delegate, reason: from kotlin metadata */
    private final Lazy bloodKetonesRepository = LazyKt.lazy(new Function0() { // from class: com.yucheng.smarthealthpro.viewmodel.HomeViewModel$$ExternalSyntheticLambda7
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return HomeViewModel.bloodKetonesRepository_delegate$lambda$1();
        }
    });

    /* renamed from: bloodLipidsRepository$delegate, reason: from kotlin metadata */
    private final Lazy bloodLipidsRepository = LazyKt.lazy(new Function0() { // from class: com.yucheng.smarthealthpro.viewmodel.HomeViewModel$$ExternalSyntheticLambda8
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return HomeViewModel.bloodLipidsRepository_delegate$lambda$2();
        }
    });

    /* renamed from: bloodPressureRepository$delegate, reason: from kotlin metadata */
    private final Lazy bloodPressureRepository = LazyKt.lazy(new Function0() { // from class: com.yucheng.smarthealthpro.viewmodel.HomeViewModel$$ExternalSyntheticLambda9
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return HomeViewModel.bloodPressureRepository_delegate$lambda$3();
        }
    });

    /* renamed from: bodyDataRepository$delegate, reason: from kotlin metadata */
    private final Lazy bodyDataRepository = LazyKt.lazy(new Function0() { // from class: com.yucheng.smarthealthpro.viewmodel.HomeViewModel$$ExternalSyntheticLambda10
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return HomeViewModel.bodyDataRepository_delegate$lambda$4();
        }
    });

    /* renamed from: ecgMeasureRepository$delegate, reason: from kotlin metadata */
    private final Lazy ecgMeasureRepository = LazyKt.lazy(new Function0() { // from class: com.yucheng.smarthealthpro.viewmodel.HomeViewModel$$ExternalSyntheticLambda11
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return HomeViewModel.ecgMeasureRepository_delegate$lambda$5();
        }
    });

    /* renamed from: heartRateRepository$delegate, reason: from kotlin metadata */
    private final Lazy heartRateRepository = LazyKt.lazy(new Function0() { // from class: com.yucheng.smarthealthpro.viewmodel.HomeViewModel$$ExternalSyntheticLambda12
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return HomeViewModel.heartRateRepository_delegate$lambda$6();
        }
    });

    /* renamed from: motionPatternRepository$delegate, reason: from kotlin metadata */
    private final Lazy motionPatternRepository = LazyKt.lazy(new Function0() { // from class: com.yucheng.smarthealthpro.viewmodel.HomeViewModel$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return HomeViewModel.motionPatternRepository_delegate$lambda$7();
        }
    });

    /* renamed from: physiotherapyRepository$delegate, reason: from kotlin metadata */
    private final Lazy physiotherapyRepository = LazyKt.lazy(new Function0() { // from class: com.yucheng.smarthealthpro.viewmodel.HomeViewModel$$ExternalSyntheticLambda2
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return HomeViewModel.physiotherapyRepository_delegate$lambda$8();
        }
    });

    /* renamed from: sleepRepository$delegate, reason: from kotlin metadata */
    private final Lazy sleepRepository = LazyKt.lazy(new Function0() { // from class: com.yucheng.smarthealthpro.viewmodel.HomeViewModel$$ExternalSyntheticLambda3
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return HomeViewModel.sleepRepository_delegate$lambda$9();
        }
    });

    /* renamed from: sportRecordRepository$delegate, reason: from kotlin metadata */
    private final Lazy sportRecordRepository = LazyKt.lazy(new Function0() { // from class: com.yucheng.smarthealthpro.viewmodel.HomeViewModel$$ExternalSyntheticLambda4
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return HomeViewModel.sportRecordRepository_delegate$lambda$10();
        }
    });

    /* renamed from: stepRepository$delegate, reason: from kotlin metadata */
    private final Lazy stepRepository = LazyKt.lazy(new Function0() { // from class: com.yucheng.smarthealthpro.viewmodel.HomeViewModel$$ExternalSyntheticLambda5
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return HomeViewModel.stepRepository_delegate$lambda$11();
        }
    });

    /* renamed from: uricAcidRepository$delegate, reason: from kotlin metadata */
    private final Lazy uricAcidRepository = LazyKt.lazy(new Function0() { // from class: com.yucheng.smarthealthpro.viewmodel.HomeViewModel$$ExternalSyntheticLambda6
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return HomeViewModel.uricAcidRepository_delegate$lambda$12();
        }
    });
    private final MutableSharedFlow<List<HealthMetric>> _hrvDataFlow = SharedFlowKt.MutableSharedFlow$default(0, 0, null, 7, null);
    private final MutableSharedFlow<List<HeartRate>> _heartRateDataFlow = SharedFlowKt.MutableSharedFlow$default(0, 0, null, 7, null);
    private final MutableSharedFlow<List<BloodPressure>> _bloodPressureDataFlow = SharedFlowKt.MutableSharedFlow$default(0, 0, null, 7, null);
    private final MutableSharedFlow<List<HealthMetric>> _respiratoryRateDataFlow = SharedFlowKt.MutableSharedFlow$default(0, 0, null, 7, null);
    private final MutableSharedFlow<List<HealthMetric>> _bloodOxygenDataFlow = SharedFlowKt.MutableSharedFlow$default(0, 0, null, 7, null);
    private final MutableSharedFlow<List<HealthMetric>> _temperatureDataFlow = SharedFlowKt.MutableSharedFlow$default(0, 0, null, 7, null);
    private final MutableSharedFlow<List<HealthMetric>> _bloodSugarDataFlow = SharedFlowKt.MutableSharedFlow$default(0, 0, null, 7, null);
    private final MutableSharedFlow<List<BloodLipids>> _bloodLipidsDataFlow = SharedFlowKt.MutableSharedFlow$default(0, 0, null, 7, null);
    private final MutableSharedFlow<List<UricAcid>> _uricAcidDataFlow = SharedFlowKt.MutableSharedFlow$default(0, 0, null, 7, null);
    private final MutableSharedFlow<List<BloodKetones>> _bloodKetonesDataFlow = SharedFlowKt.MutableSharedFlow$default(0, 0, null, 7, null);
    private final MutableSharedFlow<List<Physiotherapy>> _physiotherapyDataFlow = SharedFlowKt.MutableSharedFlow$default(0, 0, null, 7, null);
    private final MutableSharedFlow<List<BodyData>> _pressureDataFlow = SharedFlowKt.MutableSharedFlow$default(0, 0, null, 7, null);
    private final MutableSharedFlow<Pair<List<Sleep>, List<Sleep>>> _sleepDataFlow = SharedFlowKt.MutableSharedFlow$default(0, 0, null, 7, null);

    /* JADX INFO: Access modifiers changed from: private */
    public final HealthMetricRepository getHealthMetricRepository() {
        return (HealthMetricRepository) this.healthMetricRepository.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final HealthMetricRepository healthMetricRepository_delegate$lambda$0() {
        return new HealthMetricRepository(MyApplication.sInstance.getAppDatabase().healthMetricDao());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final BloodKetonesRepository getBloodKetonesRepository() {
        return (BloodKetonesRepository) this.bloodKetonesRepository.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final BloodKetonesRepository bloodKetonesRepository_delegate$lambda$1() {
        return new BloodKetonesRepository(MyApplication.sInstance.getAppDatabase().bloodKetonesDao());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final BloodLipidsRepository getBloodLipidsRepository() {
        return (BloodLipidsRepository) this.bloodLipidsRepository.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final BloodLipidsRepository bloodLipidsRepository_delegate$lambda$2() {
        return new BloodLipidsRepository(MyApplication.sInstance.getAppDatabase().bloodLipidsDao());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final BloodPressureRepository getBloodPressureRepository() {
        return (BloodPressureRepository) this.bloodPressureRepository.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final BloodPressureRepository bloodPressureRepository_delegate$lambda$3() {
        return new BloodPressureRepository(MyApplication.sInstance.getAppDatabase().bloodPressureDao());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final BodyDataRepository getBodyDataRepository() {
        return (BodyDataRepository) this.bodyDataRepository.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final BodyDataRepository bodyDataRepository_delegate$lambda$4() {
        return new BodyDataRepository(MyApplication.sInstance.getAppDatabase().bodyDataDao());
    }

    private final EcgMeasureRepository getEcgMeasureRepository() {
        return (EcgMeasureRepository) this.ecgMeasureRepository.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final EcgMeasureRepository ecgMeasureRepository_delegate$lambda$5() {
        return new EcgMeasureRepository(MyApplication.sInstance.getAppDatabase().ecgMeasureDao());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final HeartRateRepository getHeartRateRepository() {
        return (HeartRateRepository) this.heartRateRepository.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final HeartRateRepository heartRateRepository_delegate$lambda$6() {
        return new HeartRateRepository(MyApplication.sInstance.getAppDatabase().heartRateDao());
    }

    private final MotionPatternRepository getMotionPatternRepository() {
        return (MotionPatternRepository) this.motionPatternRepository.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MotionPatternRepository motionPatternRepository_delegate$lambda$7() {
        return new MotionPatternRepository(MyApplication.sInstance.getAppDatabase().motionPatternDao());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final PhysiotherapyRepository getPhysiotherapyRepository() {
        return (PhysiotherapyRepository) this.physiotherapyRepository.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final PhysiotherapyRepository physiotherapyRepository_delegate$lambda$8() {
        return new PhysiotherapyRepository(MyApplication.sInstance.getAppDatabase().physiotherapyDao());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final SleepRepository getSleepRepository() {
        return (SleepRepository) this.sleepRepository.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final SleepRepository sleepRepository_delegate$lambda$9() {
        return new SleepRepository(MyApplication.sInstance.getAppDatabase().sleepDao());
    }

    private final SportRecordRepository getSportRecordRepository() {
        return (SportRecordRepository) this.sportRecordRepository.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final SportRecordRepository sportRecordRepository_delegate$lambda$10() {
        return new SportRecordRepository(MyApplication.sInstance.getAppDatabase().sportRecordDao());
    }

    private final StepRepository getStepRepository() {
        return (StepRepository) this.stepRepository.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final StepRepository stepRepository_delegate$lambda$11() {
        return new StepRepository(MyApplication.sInstance.getAppDatabase().stepDao());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final UricAcidRepository getUricAcidRepository() {
        return (UricAcidRepository) this.uricAcidRepository.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final UricAcidRepository uricAcidRepository_delegate$lambda$12() {
        return new UricAcidRepository(MyApplication.sInstance.getAppDatabase().uricAcidDao());
    }

    public final SharedFlow<List<HealthMetric>> getHrvDataFlow() {
        return FlowKt.asSharedFlow(this._hrvDataFlow);
    }

    public final SharedFlow<List<HeartRate>> getHeartRateDataFlow() {
        return FlowKt.asSharedFlow(this._heartRateDataFlow);
    }

    public final SharedFlow<List<BloodPressure>> getBloodPressureDataFlow() {
        return FlowKt.asSharedFlow(this._bloodPressureDataFlow);
    }

    public final SharedFlow<List<HealthMetric>> getRespiratoryRateDataFlow() {
        return FlowKt.asSharedFlow(this._respiratoryRateDataFlow);
    }

    public final SharedFlow<List<HealthMetric>> getBloodOxygenDataFlow() {
        return FlowKt.asSharedFlow(this._bloodOxygenDataFlow);
    }

    public final SharedFlow<List<HealthMetric>> getTemperatureDataFlow() {
        return FlowKt.asSharedFlow(this._temperatureDataFlow);
    }

    public final SharedFlow<List<HealthMetric>> getBloodSugarDataFlow() {
        return FlowKt.asSharedFlow(this._bloodSugarDataFlow);
    }

    public final SharedFlow<List<BloodLipids>> getBloodLipidsDataFlow() {
        return FlowKt.asSharedFlow(this._bloodLipidsDataFlow);
    }

    public final SharedFlow<List<UricAcid>> getUricAcidDataFlow() {
        return FlowKt.asSharedFlow(this._uricAcidDataFlow);
    }

    public final SharedFlow<List<BloodKetones>> getBloodKetonesDataFlow() {
        return FlowKt.asSharedFlow(this._bloodKetonesDataFlow);
    }

    public final SharedFlow<List<Physiotherapy>> getPhysiotherapyDataFlow() {
        return FlowKt.asSharedFlow(this._physiotherapyDataFlow);
    }

    public final SharedFlow<List<BodyData>> getPressureDataFlow() {
        return FlowKt.asSharedFlow(this._pressureDataFlow);
    }

    public final SharedFlow<Pair<List<Sleep>, List<Sleep>>> getSleepDataFlow() {
        return FlowKt.asSharedFlow(this._sleepDataFlow);
    }

    /* compiled from: HomeViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.viewmodel.HomeViewModel$getHrvDayData$1", f = "HomeViewModel.kt", i = {}, l = {Opcodes.FCMPG, Opcodes.DCMPL}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.yucheng.smarthealthpro.viewmodel.HomeViewModel$getHrvDayData$1, reason: invalid class name and case insensitive filesystem */
    static final class C03471 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $dayStr;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03471(String str, Continuation<? super C03471> continuation) {
            super(2, continuation);
            this.$dayStr = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return HomeViewModel.this.new C03471(this.$dayStr, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03471) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.label;
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                HealthMetricRepository healthMetricRepository = HomeViewModel.this.getHealthMetricRepository();
                String str = this.$dayStr;
                String userName = UserInfoUtil.getUserName();
                Intrinsics.checkNotNullExpressionValue(userName, "getUserName(...)");
                this.label = 1;
                obj = healthMetricRepository.getDayData(str, userName, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i2 != 1) {
                    if (i2 != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                }
                ResultKt.throwOnFailure(obj);
            }
            MutableSharedFlow mutableSharedFlow = HomeViewModel.this._hrvDataFlow;
            this.label = 2;
            if (mutableSharedFlow.emit((List) obj, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final void getHrvDayData(String dayStr) {
        Intrinsics.checkNotNullParameter(dayStr, "dayStr");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C03471(dayStr, null), 3, null);
    }

    /* compiled from: HomeViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.viewmodel.HomeViewModel$getHeartRateDayData$1", f = "HomeViewModel.kt", i = {}, l = {Opcodes.IFGT, Opcodes.IFLE}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.yucheng.smarthealthpro.viewmodel.HomeViewModel$getHeartRateDayData$1, reason: invalid class name and case insensitive filesystem */
    static final class C03461 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $dayStr;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03461(String str, Continuation<? super C03461> continuation) {
            super(2, continuation);
            this.$dayStr = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return HomeViewModel.this.new C03461(this.$dayStr, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03461) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.label;
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                HeartRateRepository heartRateRepository = HomeViewModel.this.getHeartRateRepository();
                String str = this.$dayStr;
                String userName = UserInfoUtil.getUserName();
                Intrinsics.checkNotNullExpressionValue(userName, "getUserName(...)");
                this.label = 1;
                obj = heartRateRepository.getDayData(str, userName, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i2 != 1) {
                    if (i2 != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                }
                ResultKt.throwOnFailure(obj);
            }
            MutableSharedFlow mutableSharedFlow = HomeViewModel.this._heartRateDataFlow;
            this.label = 2;
            if (mutableSharedFlow.emit((List) obj, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final void getHeartRateDayData(String dayStr) {
        Intrinsics.checkNotNullParameter(dayStr, "dayStr");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C03461(dayStr, null), 3, null);
    }

    /* compiled from: HomeViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.viewmodel.HomeViewModel$getBloodPressureDayData$1", f = "HomeViewModel.kt", i = {}, l = {164, 165}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.yucheng.smarthealthpro.viewmodel.HomeViewModel$getBloodPressureDayData$1, reason: invalid class name and case insensitive filesystem */
    static final class C03441 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $dayStr;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03441(String str, Continuation<? super C03441> continuation) {
            super(2, continuation);
            this.$dayStr = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return HomeViewModel.this.new C03441(this.$dayStr, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03441) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.label;
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                BloodPressureRepository bloodPressureRepository = HomeViewModel.this.getBloodPressureRepository();
                String str = this.$dayStr;
                String userName = UserInfoUtil.getUserName();
                Intrinsics.checkNotNullExpressionValue(userName, "getUserName(...)");
                this.label = 1;
                obj = bloodPressureRepository.getDayData(str, userName, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i2 != 1) {
                    if (i2 != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                }
                ResultKt.throwOnFailure(obj);
            }
            MutableSharedFlow mutableSharedFlow = HomeViewModel.this._bloodPressureDataFlow;
            this.label = 2;
            if (mutableSharedFlow.emit((List) obj, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final void getBloodPressureDayData(String dayStr) {
        Intrinsics.checkNotNullParameter(dayStr, "dayStr");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C03441(dayStr, null), 3, null);
    }

    /* compiled from: HomeViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.viewmodel.HomeViewModel$getRespiratoryRateDayData$1", f = "HomeViewModel.kt", i = {}, l = {Opcodes.LOOKUPSWITCH, Opcodes.IRETURN}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.yucheng.smarthealthpro.viewmodel.HomeViewModel$getRespiratoryRateDayData$1, reason: invalid class name and case insensitive filesystem */
    static final class C03501 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $dayStr;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03501(String str, Continuation<? super C03501> continuation) {
            super(2, continuation);
            this.$dayStr = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return HomeViewModel.this.new C03501(this.$dayStr, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03501) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.label;
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                HealthMetricRepository healthMetricRepository = HomeViewModel.this.getHealthMetricRepository();
                String str = this.$dayStr;
                String userName = UserInfoUtil.getUserName();
                Intrinsics.checkNotNullExpressionValue(userName, "getUserName(...)");
                this.label = 1;
                obj = healthMetricRepository.getDayData(str, userName, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i2 != 1) {
                    if (i2 != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                }
                ResultKt.throwOnFailure(obj);
            }
            MutableSharedFlow mutableSharedFlow = HomeViewModel.this._respiratoryRateDataFlow;
            this.label = 2;
            if (mutableSharedFlow.emit((List) obj, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final void getRespiratoryRateDayData(String dayStr) {
        Intrinsics.checkNotNullParameter(dayStr, "dayStr");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C03501(dayStr, null), 3, null);
    }

    /* compiled from: HomeViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.viewmodel.HomeViewModel$getBloodOxygenDayData$1", f = "HomeViewModel.kt", i = {}, l = {Opcodes.GETSTATIC, Opcodes.PUTSTATIC}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.yucheng.smarthealthpro.viewmodel.HomeViewModel$getBloodOxygenDayData$1, reason: invalid class name and case insensitive filesystem */
    static final class C03431 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $dayStr;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03431(String str, Continuation<? super C03431> continuation) {
            super(2, continuation);
            this.$dayStr = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return HomeViewModel.this.new C03431(this.$dayStr, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03431) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.label;
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                HealthMetricRepository healthMetricRepository = HomeViewModel.this.getHealthMetricRepository();
                String str = this.$dayStr;
                String userName = UserInfoUtil.getUserName();
                Intrinsics.checkNotNullExpressionValue(userName, "getUserName(...)");
                this.label = 1;
                obj = healthMetricRepository.getDayData(str, userName, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i2 != 1) {
                    if (i2 != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                }
                ResultKt.throwOnFailure(obj);
            }
            MutableSharedFlow mutableSharedFlow = HomeViewModel.this._bloodOxygenDataFlow;
            this.label = 2;
            if (mutableSharedFlow.emit((List) obj, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final void getBloodOxygenDayData(String dayStr) {
        Intrinsics.checkNotNullParameter(dayStr, "dayStr");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C03431(dayStr, null), 3, null);
    }

    /* compiled from: HomeViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.viewmodel.HomeViewModel$getTemperatureDayData$1", f = "HomeViewModel.kt", i = {}, l = {Opcodes.INVOKEINTERFACE, 186}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.yucheng.smarthealthpro.viewmodel.HomeViewModel$getTemperatureDayData$1, reason: invalid class name and case insensitive filesystem */
    static final class C03521 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $dayStr;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03521(String str, Continuation<? super C03521> continuation) {
            super(2, continuation);
            this.$dayStr = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return HomeViewModel.this.new C03521(this.$dayStr, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03521) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.label;
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                HealthMetricRepository healthMetricRepository = HomeViewModel.this.getHealthMetricRepository();
                String str = this.$dayStr;
                String userName = UserInfoUtil.getUserName();
                Intrinsics.checkNotNullExpressionValue(userName, "getUserName(...)");
                this.label = 1;
                obj = healthMetricRepository.getDayData(str, userName, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i2 != 1) {
                    if (i2 != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                }
                ResultKt.throwOnFailure(obj);
            }
            MutableSharedFlow mutableSharedFlow = HomeViewModel.this._temperatureDataFlow;
            this.label = 2;
            if (mutableSharedFlow.emit((List) obj, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final void getTemperatureDayData(String dayStr) {
        Intrinsics.checkNotNullParameter(dayStr, "dayStr");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C03521(dayStr, null), 3, null);
    }

    /* compiled from: HomeViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.viewmodel.HomeViewModel$getBloodSugarDayData$1", f = "HomeViewModel.kt", i = {}, l = {192, 193}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.yucheng.smarthealthpro.viewmodel.HomeViewModel$getBloodSugarDayData$1, reason: invalid class name and case insensitive filesystem */
    static final class C03451 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $dayStr;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03451(String str, Continuation<? super C03451> continuation) {
            super(2, continuation);
            this.$dayStr = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return HomeViewModel.this.new C03451(this.$dayStr, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03451) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.label;
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                HealthMetricRepository healthMetricRepository = HomeViewModel.this.getHealthMetricRepository();
                String str = this.$dayStr;
                String userName = UserInfoUtil.getUserName();
                Intrinsics.checkNotNullExpressionValue(userName, "getUserName(...)");
                this.label = 1;
                obj = healthMetricRepository.getDayData(str, userName, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i2 != 1) {
                    if (i2 != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                }
                ResultKt.throwOnFailure(obj);
            }
            MutableSharedFlow mutableSharedFlow = HomeViewModel.this._bloodSugarDataFlow;
            this.label = 2;
            if (mutableSharedFlow.emit((List) obj, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final void getBloodSugarDayData(String dayStr) {
        Intrinsics.checkNotNullParameter(dayStr, "dayStr");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C03451(dayStr, null), 3, null);
    }

    /* compiled from: HomeViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.viewmodel.HomeViewModel$getBloodLipidsDayData$1", f = "HomeViewModel.kt", i = {}, l = {Opcodes.IFNONNULL, 200}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.yucheng.smarthealthpro.viewmodel.HomeViewModel$getBloodLipidsDayData$1, reason: invalid class name and case insensitive filesystem */
    static final class C03421 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $dayStr;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03421(String str, Continuation<? super C03421> continuation) {
            super(2, continuation);
            this.$dayStr = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return HomeViewModel.this.new C03421(this.$dayStr, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03421) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.label;
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                BloodLipidsRepository bloodLipidsRepository = HomeViewModel.this.getBloodLipidsRepository();
                String str = this.$dayStr;
                String userName = UserInfoUtil.getUserName();
                Intrinsics.checkNotNullExpressionValue(userName, "getUserName(...)");
                this.label = 1;
                obj = bloodLipidsRepository.getDayData(str, userName, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i2 != 1) {
                    if (i2 != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                }
                ResultKt.throwOnFailure(obj);
            }
            MutableSharedFlow mutableSharedFlow = HomeViewModel.this._bloodLipidsDataFlow;
            this.label = 2;
            if (mutableSharedFlow.emit((List) obj, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final void getBloodLipidsDayData(String dayStr) {
        Intrinsics.checkNotNullParameter(dayStr, "dayStr");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C03421(dayStr, null), 3, null);
    }

    /* compiled from: HomeViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.viewmodel.HomeViewModel$getUricAcidDayData$1", f = "HomeViewModel.kt", i = {}, l = {206, 207}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.yucheng.smarthealthpro.viewmodel.HomeViewModel$getUricAcidDayData$1, reason: invalid class name and case insensitive filesystem */
    static final class C03531 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $dayStr;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03531(String str, Continuation<? super C03531> continuation) {
            super(2, continuation);
            this.$dayStr = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return HomeViewModel.this.new C03531(this.$dayStr, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03531) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.label;
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                UricAcidRepository uricAcidRepository = HomeViewModel.this.getUricAcidRepository();
                String str = this.$dayStr;
                String userName = UserInfoUtil.getUserName();
                Intrinsics.checkNotNullExpressionValue(userName, "getUserName(...)");
                this.label = 1;
                obj = uricAcidRepository.getDayData(str, userName, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i2 != 1) {
                    if (i2 != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                }
                ResultKt.throwOnFailure(obj);
            }
            MutableSharedFlow mutableSharedFlow = HomeViewModel.this._uricAcidDataFlow;
            this.label = 2;
            if (mutableSharedFlow.emit((List) obj, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final void getUricAcidDayData(String dayStr) {
        Intrinsics.checkNotNullParameter(dayStr, "dayStr");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C03531(dayStr, null), 3, null);
    }

    /* compiled from: HomeViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.viewmodel.HomeViewModel$getBloodKetonesDayData$1", f = "HomeViewModel.kt", i = {}, l = {Command.CMD_GET_LOW_LATENCY_SETTINGS, Command.CMD_GET_EXTERNAL_FLASH_MSG}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.yucheng.smarthealthpro.viewmodel.HomeViewModel$getBloodKetonesDayData$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $dayStr;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(String str, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$dayStr = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return HomeViewModel.this.new AnonymousClass1(this.$dayStr, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.label;
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                BloodKetonesRepository bloodKetonesRepository = HomeViewModel.this.getBloodKetonesRepository();
                String str = this.$dayStr;
                String userName = UserInfoUtil.getUserName();
                Intrinsics.checkNotNullExpressionValue(userName, "getUserName(...)");
                this.label = 1;
                obj = bloodKetonesRepository.getDayData(str, userName, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i2 != 1) {
                    if (i2 != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                }
                ResultKt.throwOnFailure(obj);
            }
            MutableSharedFlow mutableSharedFlow = HomeViewModel.this._bloodKetonesDataFlow;
            this.label = 2;
            if (mutableSharedFlow.emit((List) obj, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final void getBloodKetonesDayData(String dayStr) {
        Intrinsics.checkNotNullParameter(dayStr, "dayStr");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new AnonymousClass1(dayStr, null), 3, null);
    }

    /* compiled from: HomeViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.viewmodel.HomeViewModel$getPhysiotherapyDayData$1", f = "HomeViewModel.kt", i = {}, l = {220, 221}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.yucheng.smarthealthpro.viewmodel.HomeViewModel$getPhysiotherapyDayData$1, reason: invalid class name and case insensitive filesystem */
    static final class C03481 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $dayStr;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03481(String str, Continuation<? super C03481> continuation) {
            super(2, continuation);
            this.$dayStr = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return HomeViewModel.this.new C03481(this.$dayStr, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03481) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.label;
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                PhysiotherapyRepository physiotherapyRepository = HomeViewModel.this.getPhysiotherapyRepository();
                String str = this.$dayStr;
                String userName = UserInfoUtil.getUserName();
                Intrinsics.checkNotNullExpressionValue(userName, "getUserName(...)");
                this.label = 1;
                obj = physiotherapyRepository.getDayData(str, userName, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i2 != 1) {
                    if (i2 != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                }
                ResultKt.throwOnFailure(obj);
            }
            MutableSharedFlow mutableSharedFlow = HomeViewModel.this._physiotherapyDataFlow;
            this.label = 2;
            if (mutableSharedFlow.emit((List) obj, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final void getPhysiotherapyDayData(String dayStr) {
        Intrinsics.checkNotNullParameter(dayStr, "dayStr");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C03481(dayStr, null), 3, null);
    }

    /* compiled from: HomeViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.viewmodel.HomeViewModel$getPressureDayData$1", f = "HomeViewModel.kt", i = {}, l = {227, 228}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.yucheng.smarthealthpro.viewmodel.HomeViewModel$getPressureDayData$1, reason: invalid class name and case insensitive filesystem */
    static final class C03491 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $dayStr;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03491(String str, Continuation<? super C03491> continuation) {
            super(2, continuation);
            this.$dayStr = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return HomeViewModel.this.new C03491(this.$dayStr, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03491) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.label;
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                BodyDataRepository bodyDataRepository = HomeViewModel.this.getBodyDataRepository();
                String str = this.$dayStr;
                String userName = UserInfoUtil.getUserName();
                Intrinsics.checkNotNullExpressionValue(userName, "getUserName(...)");
                this.label = 1;
                obj = bodyDataRepository.getDayData(str, userName, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i2 != 1) {
                    if (i2 != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                }
                ResultKt.throwOnFailure(obj);
            }
            MutableSharedFlow mutableSharedFlow = HomeViewModel.this._pressureDataFlow;
            this.label = 2;
            if (mutableSharedFlow.emit((List) obj, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final void getPressureDayData(String dayStr) {
        Intrinsics.checkNotNullParameter(dayStr, "dayStr");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C03491(dayStr, null), 3, null);
    }

    /* compiled from: HomeViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.viewmodel.HomeViewModel$getSleepData$1", f = "HomeViewModel.kt", i = {1}, l = {234, 235, 236}, m = "invokeSuspend", n = {"sleepList1"}, s = {"L$0"})
    /* renamed from: com.yucheng.smarthealthpro.viewmodel.HomeViewModel$getSleepData$1, reason: invalid class name and case insensitive filesystem */
    static final class C03511 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $dayStr1;
        final /* synthetic */ String $dayStr2;
        Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03511(String str, String str2, Continuation<? super C03511> continuation) {
            super(2, continuation);
            this.$dayStr1 = str;
            this.$dayStr2 = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return HomeViewModel.this.new C03511(this.$dayStr1, this.$dayStr2, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03511) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:20:0x0084 A[RETURN] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r9) throws java.lang.Throwable {
            /*
                r8 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r8.label
                java.lang.String r2 = "getUserName(...)"
                r3 = 3
                r4 = 2
                r5 = 1
                if (r1 == 0) goto L2b
                if (r1 == r5) goto L27
                if (r1 == r4) goto L1f
                if (r1 != r3) goto L17
                kotlin.ResultKt.throwOnFailure(r9)
                goto L85
            L17:
                java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r9.<init>(r0)
                throw r9
            L1f:
                java.lang.Object r1 = r8.L$0
                java.util.List r1 = (java.util.List) r1
                kotlin.ResultKt.throwOnFailure(r9)
                goto L69
            L27:
                kotlin.ResultKt.throwOnFailure(r9)
                goto L49
            L2b:
                kotlin.ResultKt.throwOnFailure(r9)
                com.yucheng.smarthealthpro.viewmodel.HomeViewModel r9 = com.yucheng.smarthealthpro.viewmodel.HomeViewModel.this
                com.yucheng.smarthealthpro.repository.SleepRepository r9 = com.yucheng.smarthealthpro.viewmodel.HomeViewModel.access$getSleepRepository(r9)
                java.lang.String r1 = r8.$dayStr1
                java.lang.String r6 = com.yucheng.smarthealthpro.login.normal.util.UserInfoUtil.getUserName()
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r2)
                r7 = r8
                kotlin.coroutines.Continuation r7 = (kotlin.coroutines.Continuation) r7
                r8.label = r5
                java.lang.Object r9 = r9.getDayData(r1, r6, r7)
                if (r9 != r0) goto L49
                return r0
            L49:
                r1 = r9
                java.util.List r1 = (java.util.List) r1
                com.yucheng.smarthealthpro.viewmodel.HomeViewModel r9 = com.yucheng.smarthealthpro.viewmodel.HomeViewModel.this
                com.yucheng.smarthealthpro.repository.SleepRepository r9 = com.yucheng.smarthealthpro.viewmodel.HomeViewModel.access$getSleepRepository(r9)
                java.lang.String r5 = r8.$dayStr2
                java.lang.String r6 = com.yucheng.smarthealthpro.login.normal.util.UserInfoUtil.getUserName()
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r2)
                r2 = r8
                kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
                r8.L$0 = r1
                r8.label = r4
                java.lang.Object r9 = r9.getDayData(r5, r6, r2)
                if (r9 != r0) goto L69
                return r0
            L69:
                java.util.List r9 = (java.util.List) r9
                com.yucheng.smarthealthpro.viewmodel.HomeViewModel r2 = com.yucheng.smarthealthpro.viewmodel.HomeViewModel.this
                kotlinx.coroutines.flow.MutableSharedFlow r2 = com.yucheng.smarthealthpro.viewmodel.HomeViewModel.access$get_sleepDataFlow$p(r2)
                kotlin.Pair r4 = new kotlin.Pair
                r4.<init>(r1, r9)
                r9 = r8
                kotlin.coroutines.Continuation r9 = (kotlin.coroutines.Continuation) r9
                r1 = 0
                r8.L$0 = r1
                r8.label = r3
                java.lang.Object r9 = r2.emit(r4, r9)
                if (r9 != r0) goto L85
                return r0
            L85:
                kotlin.Unit r9 = kotlin.Unit.INSTANCE
                return r9
            */
            throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.viewmodel.HomeViewModel.C03511.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public final void getSleepData(String dayStr1, String dayStr2) {
        Intrinsics.checkNotNullParameter(dayStr1, "dayStr1");
        Intrinsics.checkNotNullParameter(dayStr2, "dayStr2");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C03511(dayStr1, dayStr2, null), 3, null);
    }
}
