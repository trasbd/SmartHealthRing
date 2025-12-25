package com.yucheng.smarthealthpro.viewmodel;

import android.content.Context;
import android.database.sqlite.SQLiteException;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.room.RoomDatabaseKt;
import com.alibaba.fastjson2.internal.asm.Opcodes;
import com.google.gson.Gson;
import com.yucheng.smarthealthpro.MyApplication;
import com.yucheng.smarthealthpro.database.room.AppDatabase;
import com.yucheng.smarthealthpro.database.room.bean.BloodKetones;
import com.yucheng.smarthealthpro.database.room.bean.BloodLipids;
import com.yucheng.smarthealthpro.database.room.bean.BloodPressure;
import com.yucheng.smarthealthpro.database.room.bean.BodyData;
import com.yucheng.smarthealthpro.database.room.bean.HealthMetric;
import com.yucheng.smarthealthpro.database.room.bean.HeartRate;
import com.yucheng.smarthealthpro.database.room.bean.MotionPattern;
import com.yucheng.smarthealthpro.database.room.bean.Physiotherapy;
import com.yucheng.smarthealthpro.database.room.bean.Sleep;
import com.yucheng.smarthealthpro.database.room.bean.SleepItem;
import com.yucheng.smarthealthpro.database.room.bean.Step;
import com.yucheng.smarthealthpro.database.room.bean.UricAcid;
import com.yucheng.smarthealthpro.home.bean.AllDataResponse;
import com.yucheng.smarthealthpro.home.bean.BloodFatUricAcidResponse;
import com.yucheng.smarthealthpro.home.bean.BloodResponse;
import com.yucheng.smarthealthpro.home.bean.BodyDataResponse;
import com.yucheng.smarthealthpro.home.bean.HeartRateResponse;
import com.yucheng.smarthealthpro.home.bean.MotionPatternBean;
import com.yucheng.smarthealthpro.home.bean.PhysiotherapyBean;
import com.yucheng.smarthealthpro.home.bean.SleepResponse;
import com.yucheng.smarthealthpro.home.bean.StepNumberResponse;
import com.yucheng.smarthealthpro.login.normal.util.UserInfoUtil;
import com.yucheng.smarthealthpro.repository.BloodKetonesRepository;
import com.yucheng.smarthealthpro.repository.BloodLipidsRepository;
import com.yucheng.smarthealthpro.repository.BloodPressureRepository;
import com.yucheng.smarthealthpro.repository.BodyDataRepository;
import com.yucheng.smarthealthpro.repository.DataSyncRepository;
import com.yucheng.smarthealthpro.repository.EcgMeasureRepository;
import com.yucheng.smarthealthpro.repository.HealthMetricRepository;
import com.yucheng.smarthealthpro.repository.HeartRateRepository;
import com.yucheng.smarthealthpro.repository.MotionPatternRepository;
import com.yucheng.smarthealthpro.repository.PhysiotherapyRepository;
import com.yucheng.smarthealthpro.repository.SleepRepository;
import com.yucheng.smarthealthpro.repository.SportRecordRepository;
import com.yucheng.smarthealthpro.repository.StepRepository;
import com.yucheng.smarthealthpro.repository.UricAcidRepository;
import com.yucheng.smarthealthpro.utils.AnomalyDataMonitor;
import com.yucheng.smarthealthpro.utils.TimeStampUtils;
import com.yucheng.smarthealthpro.utils.Tools;
import com.yucheng.ycbtsdk.Constants;
import com.yucheng.ycbtsdk.YCBTClient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* compiled from: HealthDataViewModel.kt */
@Metadata(d1 = {"\u0000\u009e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\t\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J&\u0010K\u001a\u00020L2\u0006\u0010M\u001a\u00020N2\u000e\u0010O\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030P2\u0006\u0010Q\u001a\u00020RJ&\u0010S\u001a\u00020L2\u0006\u0010M\u001a\u00020N2\u000e\u0010O\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030P2\u0006\u0010Q\u001a\u00020RJ&\u0010T\u001a\u00020L2\u0006\u0010M\u001a\u00020N2\u000e\u0010O\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030P2\u0006\u0010Q\u001a\u00020RJ&\u0010U\u001a\u00020L2\u0006\u0010M\u001a\u00020N2\u000e\u0010O\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030P2\u0006\u0010Q\u001a\u00020RJ&\u0010V\u001a\u00020L2\u0006\u0010M\u001a\u00020N2\u000e\u0010O\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030P2\u0006\u0010Q\u001a\u00020RJ&\u0010W\u001a\u00020L2\u0006\u0010M\u001a\u00020N2\u000e\u0010O\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030P2\u0006\u0010Q\u001a\u00020RJ&\u0010X\u001a\u00020L2\u0006\u0010M\u001a\u00020N2\u000e\u0010O\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030P2\u0006\u0010Q\u001a\u00020RJ&\u0010Y\u001a\u00020L2\u0006\u0010M\u001a\u00020N2\u000e\u0010O\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030P2\u0006\u0010Q\u001a\u00020RJ&\u0010Z\u001a\u00020L2\u0006\u0010M\u001a\u00020N2\u000e\u0010O\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030P2\u0006\u0010Q\u001a\u00020RJ\u000e\u0010[\u001a\u00020L2\u0006\u0010M\u001a\u00020NJ\u000e\u0010\\\u001a\u00020L2\u0006\u0010M\u001a\u00020NJ\u000e\u0010]\u001a\u00020L2\u0006\u0010^\u001a\u00020_J\u000e\u0010`\u001a\u00020L2\u0006\u0010M\u001a\u00020NJ\u0006\u0010a\u001a\u00020LR\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u001b\u0010\n\u001a\u00020\u000b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\t\u001a\u0004\b\f\u0010\rR\u001b\u0010\u000f\u001a\u00020\u00108BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\t\u001a\u0004\b\u0011\u0010\u0012R\u001b\u0010\u0014\u001a\u00020\u00158BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\t\u001a\u0004\b\u0016\u0010\u0017R\u001b\u0010\u0019\u001a\u00020\u001a8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001d\u0010\t\u001a\u0004\b\u001b\u0010\u001cR\u001b\u0010\u001e\u001a\u00020\u001f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\"\u0010\t\u001a\u0004\b \u0010!R\u001b\u0010#\u001a\u00020$8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b'\u0010\t\u001a\u0004\b%\u0010&R\u001b\u0010(\u001a\u00020)8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b,\u0010\t\u001a\u0004\b*\u0010+R\u001b\u0010-\u001a\u00020.8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b1\u0010\t\u001a\u0004\b/\u00100R\u001b\u00102\u001a\u0002038BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b6\u0010\t\u001a\u0004\b4\u00105R\u001b\u00107\u001a\u0002088BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b;\u0010\t\u001a\u0004\b9\u0010:R\u001b\u0010<\u001a\u00020=8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b@\u0010\t\u001a\u0004\b>\u0010?R\u001b\u0010A\u001a\u00020B8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bE\u0010\t\u001a\u0004\bC\u0010DR\u001b\u0010F\u001a\u00020G8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bJ\u0010\t\u001a\u0004\bH\u0010I¨\u0006b"}, d2 = {"Lcom/yucheng/smarthealthpro/viewmodel/HealthDataViewModel;", "Landroidx/lifecycle/ViewModel;", "<init>", "()V", "healthMetricRepository", "Lcom/yucheng/smarthealthpro/repository/HealthMetricRepository;", "getHealthMetricRepository", "()Lcom/yucheng/smarthealthpro/repository/HealthMetricRepository;", "healthMetricRepository$delegate", "Lkotlin/Lazy;", "bloodKetonesRepository", "Lcom/yucheng/smarthealthpro/repository/BloodKetonesRepository;", "getBloodKetonesRepository", "()Lcom/yucheng/smarthealthpro/repository/BloodKetonesRepository;", "bloodKetonesRepository$delegate", "bloodLipidsRepository", "Lcom/yucheng/smarthealthpro/repository/BloodLipidsRepository;", "getBloodLipidsRepository", "()Lcom/yucheng/smarthealthpro/repository/BloodLipidsRepository;", "bloodLipidsRepository$delegate", "bloodPressureRepository", "Lcom/yucheng/smarthealthpro/repository/BloodPressureRepository;", "getBloodPressureRepository", "()Lcom/yucheng/smarthealthpro/repository/BloodPressureRepository;", "bloodPressureRepository$delegate", "bodyDataRepository", "Lcom/yucheng/smarthealthpro/repository/BodyDataRepository;", "getBodyDataRepository", "()Lcom/yucheng/smarthealthpro/repository/BodyDataRepository;", "bodyDataRepository$delegate", "ecgMeasureRepository", "Lcom/yucheng/smarthealthpro/repository/EcgMeasureRepository;", "getEcgMeasureRepository", "()Lcom/yucheng/smarthealthpro/repository/EcgMeasureRepository;", "ecgMeasureRepository$delegate", "heartRateRepository", "Lcom/yucheng/smarthealthpro/repository/HeartRateRepository;", "getHeartRateRepository", "()Lcom/yucheng/smarthealthpro/repository/HeartRateRepository;", "heartRateRepository$delegate", "motionPatternRepository", "Lcom/yucheng/smarthealthpro/repository/MotionPatternRepository;", "getMotionPatternRepository", "()Lcom/yucheng/smarthealthpro/repository/MotionPatternRepository;", "motionPatternRepository$delegate", "physiotherapyRepository", "Lcom/yucheng/smarthealthpro/repository/PhysiotherapyRepository;", "getPhysiotherapyRepository", "()Lcom/yucheng/smarthealthpro/repository/PhysiotherapyRepository;", "physiotherapyRepository$delegate", "sleepRepository", "Lcom/yucheng/smarthealthpro/repository/SleepRepository;", "getSleepRepository", "()Lcom/yucheng/smarthealthpro/repository/SleepRepository;", "sleepRepository$delegate", "sportRecordRepository", "Lcom/yucheng/smarthealthpro/repository/SportRecordRepository;", "getSportRecordRepository", "()Lcom/yucheng/smarthealthpro/repository/SportRecordRepository;", "sportRecordRepository$delegate", "stepRepository", "Lcom/yucheng/smarthealthpro/repository/StepRepository;", "getStepRepository", "()Lcom/yucheng/smarthealthpro/repository/StepRepository;", "stepRepository$delegate", "uricAcidRepository", "Lcom/yucheng/smarthealthpro/repository/UricAcidRepository;", "getUricAcidRepository", "()Lcom/yucheng/smarthealthpro/repository/UricAcidRepository;", "uricAcidRepository$delegate", "dataSyncRepository", "Lcom/yucheng/smarthealthpro/repository/DataSyncRepository;", "getDataSyncRepository", "()Lcom/yucheng/smarthealthpro/repository/DataSyncRepository;", "dataSyncRepository$delegate", "saveHealthMetricData", "", "context", "Landroid/content/Context;", "resultMap", "Ljava/util/HashMap;", "callback", "Lcom/yucheng/smarthealthpro/viewmodel/DatabaseCallback;", "saveBloodPressureData", "saveHeartRateData", "saveStepData", "saveSleepData", "savaBloodFatUricAcidData", "savaMotionPatternData", "savaPhysiotherapyData", "savaBodyData", "uploadHistoryData", "uploadEcgHistoryData", "deleteSportRecord", "startTimestamp", "", "startMonitoring", "forceUploadLogFile", "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class HealthDataViewModel extends ViewModel {

    /* renamed from: healthMetricRepository$delegate, reason: from kotlin metadata */
    private final Lazy healthMetricRepository = LazyKt.lazy(new Function0() { // from class: com.yucheng.smarthealthpro.viewmodel.HealthDataViewModel$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return HealthDataViewModel.healthMetricRepository_delegate$lambda$0();
        }
    });

    /* renamed from: bloodKetonesRepository$delegate, reason: from kotlin metadata */
    private final Lazy bloodKetonesRepository = LazyKt.lazy(new Function0() { // from class: com.yucheng.smarthealthpro.viewmodel.HealthDataViewModel$$ExternalSyntheticLambda9
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return HealthDataViewModel.bloodKetonesRepository_delegate$lambda$1();
        }
    });

    /* renamed from: bloodLipidsRepository$delegate, reason: from kotlin metadata */
    private final Lazy bloodLipidsRepository = LazyKt.lazy(new Function0() { // from class: com.yucheng.smarthealthpro.viewmodel.HealthDataViewModel$$ExternalSyntheticLambda10
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return HealthDataViewModel.bloodLipidsRepository_delegate$lambda$2();
        }
    });

    /* renamed from: bloodPressureRepository$delegate, reason: from kotlin metadata */
    private final Lazy bloodPressureRepository = LazyKt.lazy(new Function0() { // from class: com.yucheng.smarthealthpro.viewmodel.HealthDataViewModel$$ExternalSyntheticLambda11
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return HealthDataViewModel.bloodPressureRepository_delegate$lambda$3();
        }
    });

    /* renamed from: bodyDataRepository$delegate, reason: from kotlin metadata */
    private final Lazy bodyDataRepository = LazyKt.lazy(new Function0() { // from class: com.yucheng.smarthealthpro.viewmodel.HealthDataViewModel$$ExternalSyntheticLambda12
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return HealthDataViewModel.bodyDataRepository_delegate$lambda$4();
        }
    });

    /* renamed from: ecgMeasureRepository$delegate, reason: from kotlin metadata */
    private final Lazy ecgMeasureRepository = LazyKt.lazy(new Function0() { // from class: com.yucheng.smarthealthpro.viewmodel.HealthDataViewModel$$ExternalSyntheticLambda13
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return HealthDataViewModel.ecgMeasureRepository_delegate$lambda$5();
        }
    });

    /* renamed from: heartRateRepository$delegate, reason: from kotlin metadata */
    private final Lazy heartRateRepository = LazyKt.lazy(new Function0() { // from class: com.yucheng.smarthealthpro.viewmodel.HealthDataViewModel$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return HealthDataViewModel.heartRateRepository_delegate$lambda$6();
        }
    });

    /* renamed from: motionPatternRepository$delegate, reason: from kotlin metadata */
    private final Lazy motionPatternRepository = LazyKt.lazy(new Function0() { // from class: com.yucheng.smarthealthpro.viewmodel.HealthDataViewModel$$ExternalSyntheticLambda2
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return HealthDataViewModel.motionPatternRepository_delegate$lambda$7();
        }
    });

    /* renamed from: physiotherapyRepository$delegate, reason: from kotlin metadata */
    private final Lazy physiotherapyRepository = LazyKt.lazy(new Function0() { // from class: com.yucheng.smarthealthpro.viewmodel.HealthDataViewModel$$ExternalSyntheticLambda3
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return HealthDataViewModel.physiotherapyRepository_delegate$lambda$8();
        }
    });

    /* renamed from: sleepRepository$delegate, reason: from kotlin metadata */
    private final Lazy sleepRepository = LazyKt.lazy(new Function0() { // from class: com.yucheng.smarthealthpro.viewmodel.HealthDataViewModel$$ExternalSyntheticLambda4
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return HealthDataViewModel.sleepRepository_delegate$lambda$9();
        }
    });

    /* renamed from: sportRecordRepository$delegate, reason: from kotlin metadata */
    private final Lazy sportRecordRepository = LazyKt.lazy(new Function0() { // from class: com.yucheng.smarthealthpro.viewmodel.HealthDataViewModel$$ExternalSyntheticLambda5
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return HealthDataViewModel.sportRecordRepository_delegate$lambda$10();
        }
    });

    /* renamed from: stepRepository$delegate, reason: from kotlin metadata */
    private final Lazy stepRepository = LazyKt.lazy(new Function0() { // from class: com.yucheng.smarthealthpro.viewmodel.HealthDataViewModel$$ExternalSyntheticLambda6
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return HealthDataViewModel.stepRepository_delegate$lambda$11();
        }
    });

    /* renamed from: uricAcidRepository$delegate, reason: from kotlin metadata */
    private final Lazy uricAcidRepository = LazyKt.lazy(new Function0() { // from class: com.yucheng.smarthealthpro.viewmodel.HealthDataViewModel$$ExternalSyntheticLambda7
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return HealthDataViewModel.uricAcidRepository_delegate$lambda$12();
        }
    });

    /* renamed from: dataSyncRepository$delegate, reason: from kotlin metadata */
    private final Lazy dataSyncRepository = LazyKt.lazy(new Function0() { // from class: com.yucheng.smarthealthpro.viewmodel.HealthDataViewModel$$ExternalSyntheticLambda8
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return HealthDataViewModel.dataSyncRepository_delegate$lambda$13();
        }
    });

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

    /* JADX INFO: Access modifiers changed from: private */
    public final EcgMeasureRepository getEcgMeasureRepository() {
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

    /* JADX INFO: Access modifiers changed from: private */
    public final MotionPatternRepository getMotionPatternRepository() {
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

    /* JADX INFO: Access modifiers changed from: private */
    public final SportRecordRepository getSportRecordRepository() {
        return (SportRecordRepository) this.sportRecordRepository.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final SportRecordRepository sportRecordRepository_delegate$lambda$10() {
        return new SportRecordRepository(MyApplication.sInstance.getAppDatabase().sportRecordDao());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final StepRepository getStepRepository() {
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

    /* JADX INFO: Access modifiers changed from: private */
    public final DataSyncRepository getDataSyncRepository() {
        return (DataSyncRepository) this.dataSyncRepository.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DataSyncRepository dataSyncRepository_delegate$lambda$13() {
        return new DataSyncRepository();
    }

    /* compiled from: HealthDataViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.viewmodel.HealthDataViewModel$saveHealthMetricData$1", f = "HealthDataViewModel.kt", i = {}, l = {Opcodes.I2B}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.yucheng.smarthealthpro.viewmodel.HealthDataViewModel$saveHealthMetricData$1, reason: invalid class name and case insensitive filesystem */
    static final class C03341 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ DatabaseCallback $callback;
        final /* synthetic */ Context $context;
        final /* synthetic */ HashMap<?, ?> $resultMap;
        Object L$0;
        Object L$1;
        int label;
        final /* synthetic */ HealthDataViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03341(HashMap<?, ?> map, Context context, HealthDataViewModel healthDataViewModel, DatabaseCallback databaseCallback, Continuation<? super C03341> continuation) {
            super(2, continuation);
            this.$resultMap = map;
            this.$context = context;
            this.this$0 = healthDataViewModel;
            this.$callback = databaseCallback;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C03341(this.$resultMap, this.$context, this.this$0, this.$callback, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03341) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objInsertAll;
            DatabaseCallback databaseCallback;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.label;
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                Object objFromJson = new Gson().fromJson(this.$resultMap.toString(), (Class<Object>) AllDataResponse.class);
                Intrinsics.checkNotNullExpressionValue(objFromJson, "fromJson(...)");
                List<AllDataResponse.DataBean> data = ((AllDataResponse) objFromJson).data;
                Intrinsics.checkNotNullExpressionValue(data, "data");
                Context context = this.$context;
                ArrayList arrayList = new ArrayList();
                int i3 = 0;
                for (Object obj2 : data) {
                    int i4 = i3 + 1;
                    if (i3 < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    AllDataResponse.DataBean dataBean = (AllDataResponse.DataBean) obj2;
                    long j2 = dataBean.startTime;
                    int i5 = dataBean.heartValue;
                    String strDateForStringYearToDate = TimeStampUtils.dateForStringYearToDate(TimeStampUtils.longStampForDate(dataBean.startTime));
                    Intrinsics.checkNotNullExpressionValue(strDateForStringYearToDate, "dateForStringYearToDate(...)");
                    int i6 = dataBean.hrvValue;
                    int i7 = dataBean.cvrrValue;
                    int i8 = dataBean.OOValue;
                    int i9 = dataBean.stepValue;
                    int i10 = dataBean.DBPValue;
                    int i11 = dataBean.SBPValue;
                    int i12 = dataBean.respiratoryRateValue;
                    int i13 = dataBean.bodyFatIntValue;
                    int i14 = dataBean.bodyFatFloatValue;
                    int i15 = dataBean.tempIntValue;
                    int i16 = dataBean.tempFloatValue;
                    int i17 = dataBean.bloodSugarValue;
                    String userName = UserInfoUtil.getUserName();
                    Intrinsics.checkNotNullExpressionValue(userName, "getUserName(...)");
                    String deviceType = Tools.getDeviceType(context);
                    Intrinsics.checkNotNullExpressionValue(deviceType, "getDeviceType(...)");
                    String bindDeviceMac = YCBTClient.getBindDeviceMac();
                    Intrinsics.checkNotNullExpressionValue(bindDeviceMac, "getBindDeviceMac(...)");
                    arrayList.add(new HealthMetric(null, i3, j2, strDateForStringYearToDate, i5, i6, i7, i8, i9, i10, i11, i12, i15, i16, i13, i14, i17, 0, userName, deviceType, bindDeviceMac, null, false, false, false, false, false, false, false, false, false, false, false, false, -1966079, 3, null));
                    i3 = i4;
                }
                ArrayList arrayList2 = arrayList;
                HealthDataViewModel healthDataViewModel = this.this$0;
                DatabaseCallback databaseCallback2 = this.$callback;
                HealthMetricRepository healthMetricRepository = healthDataViewModel.getHealthMetricRepository();
                this.L$0 = arrayList2;
                this.L$1 = databaseCallback2;
                this.label = 1;
                objInsertAll = healthMetricRepository.insertAll(arrayList2, this);
                if (objInsertAll == coroutine_suspended) {
                    return coroutine_suspended;
                }
                databaseCallback = databaseCallback2;
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                databaseCallback = (DatabaseCallback) this.L$1;
                ResultKt.throwOnFailure(obj);
                objInsertAll = obj;
            }
            databaseCallback.onExecuteCallback(((Boolean) objInsertAll).booleanValue());
            return Unit.INSTANCE;
        }
    }

    public final void saveHealthMetricData(Context context, HashMap<?, ?> resultMap, DatabaseCallback callback) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(resultMap, "resultMap");
        Intrinsics.checkNotNullParameter(callback, "callback");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new C03341(resultMap, context, this, callback, null), 2, null);
    }

    /* compiled from: HealthDataViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.viewmodel.HealthDataViewModel$saveBloodPressureData$1", f = "HealthDataViewModel.kt", i = {}, l = {180}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.yucheng.smarthealthpro.viewmodel.HealthDataViewModel$saveBloodPressureData$1, reason: invalid class name and case insensitive filesystem */
    static final class C03331 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ DatabaseCallback $callback;
        final /* synthetic */ Context $context;
        final /* synthetic */ HashMap<?, ?> $resultMap;
        Object L$0;
        Object L$1;
        int label;
        final /* synthetic */ HealthDataViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03331(HashMap<?, ?> map, Context context, HealthDataViewModel healthDataViewModel, DatabaseCallback databaseCallback, Continuation<? super C03331> continuation) {
            super(2, continuation);
            this.$resultMap = map;
            this.$context = context;
            this.this$0 = healthDataViewModel;
            this.$callback = databaseCallback;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C03331(this.$resultMap, this.$context, this.this$0, this.$callback, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03331) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objInsertAll;
            DatabaseCallback databaseCallback;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.label;
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                Object objFromJson = new Gson().fromJson(this.$resultMap.toString(), (Class<Object>) BloodResponse.class);
                Intrinsics.checkNotNullExpressionValue(objFromJson, "fromJson(...)");
                List<BloodResponse.DataBean> data = ((BloodResponse) objFromJson).getData();
                Intrinsics.checkNotNullExpressionValue(data, "getData(...)");
                Context context = this.$context;
                ArrayList arrayList = new ArrayList();
                int i3 = 0;
                for (Object obj2 : data) {
                    int i4 = i3 + 1;
                    if (i3 < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    BloodResponse.DataBean dataBean = (BloodResponse.DataBean) obj2;
                    long bloodStartTime = dataBean.getBloodStartTime();
                    String strDateForStringYearToDate = TimeStampUtils.dateForStringYearToDate(TimeStampUtils.longStampForDate(dataBean.getBloodStartTime()));
                    Intrinsics.checkNotNullExpressionValue(strDateForStringYearToDate, "dateForStringYearToDate(...)");
                    int bloodDBP = dataBean.getBloodDBP();
                    int bloodSBP = dataBean.getBloodSBP();
                    int isInflated = dataBean.getIsInflated();
                    String userName = UserInfoUtil.getUserName();
                    Intrinsics.checkNotNullExpressionValue(userName, "getUserName(...)");
                    String deviceType = Tools.getDeviceType(context);
                    Intrinsics.checkNotNullExpressionValue(deviceType, "getDeviceType(...)");
                    String bindDeviceMac = YCBTClient.getBindDeviceMac();
                    Intrinsics.checkNotNullExpressionValue(bindDeviceMac, "getBindDeviceMac(...)");
                    arrayList.add(new BloodPressure(null, i3, bloodStartTime, strDateForStringYearToDate, bloodDBP, bloodSBP, isInflated, userName, deviceType, bindDeviceMac, null, false, false, 7169, null));
                    i3 = i4;
                }
                ArrayList arrayList2 = arrayList;
                HealthDataViewModel healthDataViewModel = this.this$0;
                DatabaseCallback databaseCallback2 = this.$callback;
                BloodPressureRepository bloodPressureRepository = healthDataViewModel.getBloodPressureRepository();
                this.L$0 = arrayList2;
                this.L$1 = databaseCallback2;
                this.label = 1;
                objInsertAll = bloodPressureRepository.insertAll(arrayList2, this);
                if (objInsertAll == coroutine_suspended) {
                    return coroutine_suspended;
                }
                databaseCallback = databaseCallback2;
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                databaseCallback = (DatabaseCallback) this.L$1;
                ResultKt.throwOnFailure(obj);
                objInsertAll = obj;
            }
            databaseCallback.onExecuteCallback(((Boolean) objInsertAll).booleanValue());
            return Unit.INSTANCE;
        }
    }

    public final void saveBloodPressureData(Context context, HashMap<?, ?> resultMap, DatabaseCallback callback) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(resultMap, "resultMap");
        Intrinsics.checkNotNullParameter(callback, "callback");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new C03331(resultMap, context, this, callback, null), 2, null);
    }

    /* compiled from: HealthDataViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.viewmodel.HealthDataViewModel$saveHeartRateData$1", f = "HealthDataViewModel.kt", i = {}, l = {209}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.yucheng.smarthealthpro.viewmodel.HealthDataViewModel$saveHeartRateData$1, reason: invalid class name and case insensitive filesystem */
    static final class C03351 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ DatabaseCallback $callback;
        final /* synthetic */ Context $context;
        final /* synthetic */ HashMap<?, ?> $resultMap;
        Object L$0;
        Object L$1;
        int label;
        final /* synthetic */ HealthDataViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03351(HashMap<?, ?> map, Context context, HealthDataViewModel healthDataViewModel, DatabaseCallback databaseCallback, Continuation<? super C03351> continuation) {
            super(2, continuation);
            this.$resultMap = map;
            this.$context = context;
            this.this$0 = healthDataViewModel;
            this.$callback = databaseCallback;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C03351(this.$resultMap, this.$context, this.this$0, this.$callback, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03351) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objInsertAll;
            DatabaseCallback databaseCallback;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.label;
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                Object objFromJson = new Gson().fromJson(this.$resultMap.toString(), (Class<Object>) HeartRateResponse.class);
                Intrinsics.checkNotNullExpressionValue(objFromJson, "fromJson(...)");
                List<HeartRateResponse.DataBean> data = ((HeartRateResponse) objFromJson).getData();
                Intrinsics.checkNotNullExpressionValue(data, "getData(...)");
                Context context = this.$context;
                ArrayList arrayList = new ArrayList();
                int i3 = 0;
                for (Object obj2 : data) {
                    int i4 = i3 + 1;
                    if (i3 < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    HeartRateResponse.DataBean dataBean = (HeartRateResponse.DataBean) obj2;
                    long heartStartTime = dataBean.getHeartStartTime();
                    int heartValue = dataBean.getHeartValue();
                    String strDateForStringYearToDate = TimeStampUtils.dateForStringYearToDate(TimeStampUtils.longStampForDate(dataBean.getHeartStartTime()));
                    Intrinsics.checkNotNullExpressionValue(strDateForStringYearToDate, "dateForStringYearToDate(...)");
                    String userName = UserInfoUtil.getUserName();
                    Intrinsics.checkNotNullExpressionValue(userName, "getUserName(...)");
                    String deviceType = Tools.getDeviceType(context);
                    Intrinsics.checkNotNullExpressionValue(deviceType, "getDeviceType(...)");
                    String bindDeviceMac = YCBTClient.getBindDeviceMac();
                    Intrinsics.checkNotNullExpressionValue(bindDeviceMac, "getBindDeviceMac(...)");
                    arrayList.add(new HeartRate(null, i3, heartStartTime, strDateForStringYearToDate, heartValue, userName, deviceType, bindDeviceMac, null, false, false, Constants.DATATYPE.Collect_GetWithIndex, null));
                    i3 = i4;
                }
                ArrayList arrayList2 = arrayList;
                HealthDataViewModel healthDataViewModel = this.this$0;
                DatabaseCallback databaseCallback2 = this.$callback;
                HeartRateRepository heartRateRepository = healthDataViewModel.getHeartRateRepository();
                this.L$0 = arrayList2;
                this.L$1 = databaseCallback2;
                this.label = 1;
                objInsertAll = heartRateRepository.insertAll(arrayList2, this);
                if (objInsertAll == coroutine_suspended) {
                    return coroutine_suspended;
                }
                databaseCallback = databaseCallback2;
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                databaseCallback = (DatabaseCallback) this.L$1;
                ResultKt.throwOnFailure(obj);
                objInsertAll = obj;
            }
            databaseCallback.onExecuteCallback(((Boolean) objInsertAll).booleanValue());
            return Unit.INSTANCE;
        }
    }

    public final void saveHeartRateData(Context context, HashMap<?, ?> resultMap, DatabaseCallback callback) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(resultMap, "resultMap");
        Intrinsics.checkNotNullParameter(callback, "callback");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new C03351(resultMap, context, this, callback, null), 2, null);
    }

    /* compiled from: HealthDataViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.viewmodel.HealthDataViewModel$saveStepData$1", f = "HealthDataViewModel.kt", i = {}, l = {241}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.yucheng.smarthealthpro.viewmodel.HealthDataViewModel$saveStepData$1, reason: invalid class name and case insensitive filesystem */
    static final class C03371 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ DatabaseCallback $callback;
        final /* synthetic */ Context $context;
        final /* synthetic */ HashMap<?, ?> $resultMap;
        Object L$0;
        Object L$1;
        int label;
        final /* synthetic */ HealthDataViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03371(HashMap<?, ?> map, Context context, HealthDataViewModel healthDataViewModel, DatabaseCallback databaseCallback, Continuation<? super C03371> continuation) {
            super(2, continuation);
            this.$resultMap = map;
            this.$context = context;
            this.this$0 = healthDataViewModel;
            this.$callback = databaseCallback;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C03371(this.$resultMap, this.$context, this.this$0, this.$callback, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03371) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objInsertAll;
            DatabaseCallback databaseCallback;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.label;
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                Object objFromJson = new Gson().fromJson(this.$resultMap.toString(), (Class<Object>) StepNumberResponse.class);
                Intrinsics.checkNotNullExpressionValue(objFromJson, "fromJson(...)");
                List<StepNumberResponse.DataBean> data = ((StepNumberResponse) objFromJson).getData();
                Intrinsics.checkNotNullExpressionValue(data, "getData(...)");
                Context context = this.$context;
                ArrayList arrayList = new ArrayList();
                int i3 = 0;
                for (Object obj2 : data) {
                    int i4 = i3 + 1;
                    if (i3 < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    StepNumberResponse.DataBean dataBean = (StepNumberResponse.DataBean) obj2;
                    long sportStartTime = dataBean.getSportStartTime();
                    long sportEndTime = dataBean.getSportEndTime();
                    String strDateForStringYearToDate = TimeStampUtils.dateForStringYearToDate(TimeStampUtils.longStampForDate(dataBean.getSportEndTime()));
                    Intrinsics.checkNotNullExpressionValue(strDateForStringYearToDate, "dateForStringYearToDate(...)");
                    int sportStep = dataBean.getSportStep();
                    int sportCalorie = dataBean.getSportCalorie();
                    int sportDistance = dataBean.getSportDistance();
                    String userName = UserInfoUtil.getUserName();
                    Intrinsics.checkNotNullExpressionValue(userName, "getUserName(...)");
                    String deviceType = Tools.getDeviceType(context);
                    Intrinsics.checkNotNullExpressionValue(deviceType, "getDeviceType(...)");
                    String bindDeviceMac = YCBTClient.getBindDeviceMac();
                    Intrinsics.checkNotNullExpressionValue(bindDeviceMac, "getBindDeviceMac(...)");
                    arrayList.add(new Step(null, i3, sportStartTime, sportEndTime, strDateForStringYearToDate, sportStep, sportDistance, sportCalorie, userName, deviceType, bindDeviceMac, null, false, false, 14337, null));
                    i3 = i4;
                }
                ArrayList arrayList2 = arrayList;
                HealthDataViewModel healthDataViewModel = this.this$0;
                DatabaseCallback databaseCallback2 = this.$callback;
                StepRepository stepRepository = healthDataViewModel.getStepRepository();
                this.L$0 = arrayList2;
                this.L$1 = databaseCallback2;
                this.label = 1;
                objInsertAll = stepRepository.insertAll(arrayList2, this);
                if (objInsertAll == coroutine_suspended) {
                    return coroutine_suspended;
                }
                databaseCallback = databaseCallback2;
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                databaseCallback = (DatabaseCallback) this.L$1;
                ResultKt.throwOnFailure(obj);
                objInsertAll = obj;
            }
            databaseCallback.onExecuteCallback(((Boolean) objInsertAll).booleanValue());
            return Unit.INSTANCE;
        }
    }

    public final void saveStepData(Context context, HashMap<?, ?> resultMap, DatabaseCallback callback) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(resultMap, "resultMap");
        Intrinsics.checkNotNullParameter(callback, "callback");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new C03371(resultMap, context, this, callback, null), 2, null);
    }

    /* compiled from: HealthDataViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.viewmodel.HealthDataViewModel$saveSleepData$1", f = "HealthDataViewModel.kt", i = {}, l = {Constants.DATATYPE.SettingBloodRange}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.yucheng.smarthealthpro.viewmodel.HealthDataViewModel$saveSleepData$1, reason: invalid class name and case insensitive filesystem */
    static final class C03361 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ DatabaseCallback $callback;
        final /* synthetic */ Context $context;
        final /* synthetic */ HashMap<?, ?> $resultMap;
        Object L$0;
        Object L$1;
        int label;
        final /* synthetic */ HealthDataViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03361(HashMap<?, ?> map, Context context, HealthDataViewModel healthDataViewModel, DatabaseCallback databaseCallback, Continuation<? super C03361> continuation) {
            super(2, continuation);
            this.$resultMap = map;
            this.$context = context;
            this.this$0 = healthDataViewModel;
            this.$callback = databaseCallback;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C03361(this.$resultMap, this.$context, this.this$0, this.$callback, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03361) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objInsertAll;
            DatabaseCallback databaseCallback;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.label;
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                Object objFromJson = new Gson().fromJson(this.$resultMap.toString(), (Class<Object>) SleepResponse.class);
                Intrinsics.checkNotNullExpressionValue(objFromJson, "fromJson(...)");
                List<SleepResponse.SleepDataBean> data = ((SleepResponse) objFromJson).getData();
                Intrinsics.checkNotNullExpressionValue(data, "getData(...)");
                Context context = this.$context;
                ArrayList arrayList = new ArrayList();
                int i3 = 0;
                for (Object obj2 : data) {
                    int i4 = i3 + 1;
                    if (i3 < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    SleepResponse.SleepDataBean sleepDataBean = (SleepResponse.SleepDataBean) obj2;
                    long startTime = sleepDataBean.getStartTime();
                    long endTime = sleepDataBean.getEndTime();
                    String strDateForStringYearToDate = TimeStampUtils.dateForStringYearToDate(TimeStampUtils.longStampForDate(sleepDataBean.getStartTime()));
                    Intrinsics.checkNotNullExpressionValue(strDateForStringYearToDate, "dateForStringYearToDate(...)");
                    int deepSleepCount = sleepDataBean.getDeepSleepCount();
                    int lightSleepCount = sleepDataBean.getLightSleepCount();
                    int deepSleepTotal = sleepDataBean.getDeepSleepTotal();
                    int lightSleepTotal = sleepDataBean.getLightSleepTotal();
                    int i5 = sleepDataBean.rapidEyeMovementTotal;
                    int i6 = sleepDataBean.wakeCount;
                    int i7 = sleepDataBean.wakeDuration;
                    List<SleepItem> sleepData = sleepDataBean.getSleepData();
                    String userName = UserInfoUtil.getUserName();
                    Intrinsics.checkNotNullExpressionValue(userName, "getUserName(...)");
                    String deviceType = Tools.getDeviceType(context);
                    Intrinsics.checkNotNullExpressionValue(deviceType, "getDeviceType(...)");
                    String bindDeviceMac = YCBTClient.getBindDeviceMac();
                    Intrinsics.checkNotNullExpressionValue(bindDeviceMac, "getBindDeviceMac(...)");
                    arrayList.add(new Sleep(null, i3, startTime, endTime, strDateForStringYearToDate, deepSleepCount, lightSleepCount, deepSleepTotal, lightSleepTotal, i5, i6, i7, sleepData, userName, deviceType, bindDeviceMac, null, false, false, 458753, null));
                    i3 = i4;
                }
                ArrayList arrayList2 = arrayList;
                HealthDataViewModel healthDataViewModel = this.this$0;
                DatabaseCallback databaseCallback2 = this.$callback;
                SleepRepository sleepRepository = healthDataViewModel.getSleepRepository();
                this.L$0 = arrayList2;
                this.L$1 = databaseCallback2;
                this.label = 1;
                objInsertAll = sleepRepository.insertAll(arrayList2, this);
                if (objInsertAll == coroutine_suspended) {
                    return coroutine_suspended;
                }
                databaseCallback = databaseCallback2;
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                databaseCallback = (DatabaseCallback) this.L$1;
                ResultKt.throwOnFailure(obj);
                objInsertAll = obj;
            }
            databaseCallback.onExecuteCallback(((Boolean) objInsertAll).booleanValue());
            return Unit.INSTANCE;
        }
    }

    public final void saveSleepData(Context context, HashMap<?, ?> resultMap, DatabaseCallback callback) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(resultMap, "resultMap");
        Intrinsics.checkNotNullParameter(callback, "callback");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new C03361(resultMap, context, this, callback, null), 2, null);
    }

    /* compiled from: HealthDataViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.viewmodel.HealthDataViewModel$savaBloodFatUricAcidData$1", f = "HealthDataViewModel.kt", i = {}, l = {384}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.yucheng.smarthealthpro.viewmodel.HealthDataViewModel$savaBloodFatUricAcidData$1, reason: invalid class name and case insensitive filesystem */
    static final class C03291 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ DatabaseCallback $callback;
        final /* synthetic */ Context $context;
        final /* synthetic */ HashMap<?, ?> $resultMap;
        int label;
        final /* synthetic */ HealthDataViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03291(HashMap<?, ?> map, DatabaseCallback databaseCallback, Context context, HealthDataViewModel healthDataViewModel, Continuation<? super C03291> continuation) {
            super(2, continuation);
            this.$resultMap = map;
            this.$callback = databaseCallback;
            this.$context = context;
            this.this$0 = healthDataViewModel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C03291(this.$resultMap, this.$callback, this.$context, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03291) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            C03291 c03291;
            Context context;
            Object obj2;
            Iterator it2;
            ArrayList arrayList;
            ArrayList arrayList2;
            ArrayList arrayList3;
            String str;
            String str2;
            String str3;
            String str4;
            ArrayList arrayList4;
            BloodFatUricAcidResponse.DataBean dataBean;
            ArrayList arrayList5;
            String str5;
            ArrayList arrayList6;
            String str6;
            String str7;
            String str8;
            ArrayList arrayList7;
            String str9;
            ArrayList arrayList8;
            ArrayList arrayList9;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.label;
            boolean z = true;
            try {
                if (i2 == 0) {
                    ResultKt.throwOnFailure(obj);
                    Object objFromJson = new Gson().fromJson(this.$resultMap.toString(), (Class<Object>) BloodFatUricAcidResponse.class);
                    Intrinsics.checkNotNullExpressionValue(objFromJson, "fromJson(...)");
                    BloodFatUricAcidResponse bloodFatUricAcidResponse = (BloodFatUricAcidResponse) objFromJson;
                    ArrayList arrayList10 = new ArrayList();
                    ArrayList arrayList11 = new ArrayList();
                    ArrayList arrayList12 = new ArrayList();
                    ArrayList arrayList13 = new ArrayList();
                    List<BloodFatUricAcidResponse.DataBean> data = bloodFatUricAcidResponse.data;
                    Intrinsics.checkNotNullExpressionValue(data, "data");
                    Context context2 = this.$context;
                    Iterator it3 = data.iterator();
                    int i3 = 0;
                    while (it3.hasNext()) {
                        try {
                            Object next = it3.next();
                            int i4 = i3 + 1;
                            if (i3 < 0) {
                                CollectionsKt.throwIndexOverflow();
                            }
                            BloodFatUricAcidResponse.DataBean dataBean2 = (BloodFatUricAcidResponse.DataBean) next;
                            if (dataBean2.cholesterolInteger == 0 && dataBean2.cholesterolFloat == 0) {
                                obj2 = coroutine_suspended;
                                it2 = it3;
                                str = "dateForStringYearToDate(...)";
                                context = context2;
                                arrayList4 = arrayList10;
                                arrayList3 = arrayList12;
                                arrayList2 = arrayList13;
                                arrayList = arrayList11;
                                str2 = "getUserName(...)";
                                str3 = "getDeviceType(...)";
                                dataBean = dataBean2;
                                str4 = "getBindDeviceMac(...)";
                            } else {
                                context = context2;
                                long j2 = dataBean2.time;
                                obj2 = coroutine_suspended;
                                it2 = it3;
                                String strDateForStringYearToDate = TimeStampUtils.dateForStringYearToDate(TimeStampUtils.longStampForDate(dataBean2.time));
                                Intrinsics.checkNotNullExpressionValue(strDateForStringYearToDate, "dateForStringYearToDate(...)");
                                int i5 = dataBean2.cholesterolInteger;
                                int i6 = dataBean2.cholesterolFloat;
                                arrayList = arrayList11;
                                int i7 = dataBean2.triglycerideCholesterolInteger;
                                arrayList2 = arrayList13;
                                int i8 = dataBean2.triglycerideCholesterolFloat;
                                arrayList3 = arrayList12;
                                int i9 = dataBean2.highLipoproteinCholesterolInteger;
                                str = "dateForStringYearToDate(...)";
                                int i10 = dataBean2.highLipoproteinCholesterolFloat;
                                ArrayList arrayList14 = arrayList10;
                                int i11 = dataBean2.lowLipoproteinCholesterolInteger;
                                int i12 = dataBean2.lowLipoproteinCholesterolFloat;
                                int i13 = dataBean2.bloodFatModel;
                                String userName = UserInfoUtil.getUserName();
                                Intrinsics.checkNotNullExpressionValue(userName, "getUserName(...)");
                                String deviceType = Tools.getDeviceType(context);
                                Intrinsics.checkNotNullExpressionValue(deviceType, "getDeviceType(...)");
                                String bindDeviceMac = YCBTClient.getBindDeviceMac();
                                Intrinsics.checkNotNullExpressionValue(bindDeviceMac, "getBindDeviceMac(...)");
                                str2 = "getUserName(...)";
                                str3 = "getDeviceType(...)";
                                str4 = "getBindDeviceMac(...)";
                                BloodLipids bloodLipids = new BloodLipids(null, i3, j2, strDateForStringYearToDate, i5, i6, i7, i8, i9, i10, i11, i12, i13, userName, deviceType, bindDeviceMac, null, false, false, 458753, null);
                                arrayList4 = arrayList14;
                                arrayList4.add(bloodLipids);
                                dataBean = dataBean2;
                            }
                            if (dataBean.uricAcid != 0) {
                                long j3 = dataBean.time;
                                String strDateForStringYearToDate2 = TimeStampUtils.dateForStringYearToDate(TimeStampUtils.longStampForDate(dataBean.time));
                                str5 = str;
                                Intrinsics.checkNotNullExpressionValue(strDateForStringYearToDate2, str5);
                                int i14 = dataBean.uricAcid;
                                int i15 = dataBean.uricAcidModel;
                                String userName2 = UserInfoUtil.getUserName();
                                str6 = str2;
                                Intrinsics.checkNotNullExpressionValue(userName2, str6);
                                String deviceType2 = Tools.getDeviceType(context);
                                String str10 = str3;
                                Intrinsics.checkNotNullExpressionValue(deviceType2, str10);
                                String bindDeviceMac2 = YCBTClient.getBindDeviceMac();
                                String str11 = str4;
                                Intrinsics.checkNotNullExpressionValue(bindDeviceMac2, str11);
                                arrayList5 = arrayList4;
                                str8 = str11;
                                str7 = str10;
                                UricAcid uricAcid = new UricAcid(null, i3, j3, strDateForStringYearToDate2, i14, i15, userName2, deviceType2, bindDeviceMac2, null, false, false, 3585, null);
                                arrayList6 = arrayList3;
                                arrayList6.add(uricAcid);
                            } else {
                                arrayList5 = arrayList4;
                                str5 = str;
                                arrayList6 = arrayList3;
                                str6 = str2;
                                str7 = str3;
                                str8 = str4;
                            }
                            if (dataBean.bloodKetoneInteger == 0 && dataBean.bloodKetoneFloat == 0) {
                                arrayList7 = arrayList6;
                                arrayList8 = arrayList2;
                                str9 = str7;
                            } else {
                                long j4 = dataBean.time;
                                String strDateForStringYearToDate3 = TimeStampUtils.dateForStringYearToDate(TimeStampUtils.longStampForDate(dataBean.time));
                                Intrinsics.checkNotNullExpressionValue(strDateForStringYearToDate3, str5);
                                int i16 = dataBean.bloodKetoneInteger;
                                int i17 = dataBean.bloodKetoneFloat;
                                int i18 = dataBean.bloodKetoneModel;
                                String userName3 = UserInfoUtil.getUserName();
                                Intrinsics.checkNotNullExpressionValue(userName3, str6);
                                String deviceType3 = Tools.getDeviceType(context);
                                String str12 = str7;
                                Intrinsics.checkNotNullExpressionValue(deviceType3, str12);
                                String bindDeviceMac3 = YCBTClient.getBindDeviceMac();
                                Intrinsics.checkNotNullExpressionValue(bindDeviceMac3, str8);
                                arrayList7 = arrayList6;
                                str9 = str12;
                                BloodKetones bloodKetones = new BloodKetones(null, i3, j4, strDateForStringYearToDate3, i16, i17, i18, userName3, deviceType3, bindDeviceMac3, null, false, false, 7169, null);
                                arrayList8 = arrayList2;
                                arrayList8.add(bloodKetones);
                            }
                            if (!YCBTClient.isSupportFunction(Constants.FunctionConstant.IS_HAS_PRECISION_BLOOD_GLUCOSE) || (dataBean.bloodSugarInteger == 0 && dataBean.bloodSugarFloat == 0)) {
                                arrayList9 = arrayList;
                            } else {
                                long j5 = dataBean.time;
                                String strDateForStringYearToDate4 = TimeStampUtils.dateForStringYearToDate(TimeStampUtils.longStampForDate(dataBean.time));
                                Intrinsics.checkNotNullExpressionValue(strDateForStringYearToDate4, str5);
                                int i19 = (dataBean.bloodSugarInteger * 10) + dataBean.bloodSugarFloat;
                                int i20 = dataBean.bloodSugarModel;
                                String userName4 = UserInfoUtil.getUserName();
                                Intrinsics.checkNotNullExpressionValue(userName4, str6);
                                String deviceType4 = Tools.getDeviceType(context);
                                Intrinsics.checkNotNullExpressionValue(deviceType4, str9);
                                String bindDeviceMac4 = YCBTClient.getBindDeviceMac();
                                Intrinsics.checkNotNullExpressionValue(bindDeviceMac4, str8);
                                arrayList9 = arrayList;
                                arrayList9.add(new HealthMetric(null, i3, j5, strDateForStringYearToDate4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, i19, i20, userName4, deviceType4, bindDeviceMac4, null, false, false, false, false, false, false, false, false, false, false, false, false, -2031631, 3, null));
                            }
                            context2 = context;
                            arrayList13 = arrayList8;
                            i3 = i4;
                            coroutine_suspended = obj2;
                            it3 = it2;
                            arrayList12 = arrayList7;
                            arrayList10 = arrayList5;
                            arrayList11 = arrayList9;
                        } catch (SQLiteException unused) {
                            c03291 = this;
                            c03291.$callback.onExecuteCallback(false);
                            return Unit.INSTANCE;
                        }
                    }
                    Object obj3 = coroutine_suspended;
                    ArrayList arrayList15 = arrayList10;
                    ArrayList arrayList16 = arrayList12;
                    ArrayList arrayList17 = arrayList11;
                    ArrayList arrayList18 = arrayList13;
                    AppDatabase appDatabase = MyApplication.sInstance.getAppDatabase();
                    Intrinsics.checkNotNullExpressionValue(appDatabase, "getAppDatabase(...)");
                    AppDatabase appDatabase2 = appDatabase;
                    c03291 = this;
                    try {
                        z = true;
                        c03291.label = 1;
                        if (RoomDatabaseKt.withTransaction(appDatabase2, new AnonymousClass2(arrayList15, c03291.this$0, arrayList16, arrayList18, arrayList17, null), c03291) == obj3) {
                            return obj3;
                        }
                    } catch (SQLiteException unused2) {
                        c03291.$callback.onExecuteCallback(false);
                        return Unit.INSTANCE;
                    }
                } else {
                    if (i2 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    c03291 = this;
                }
                c03291.$callback.onExecuteCallback(z);
            } catch (SQLiteException unused3) {
                c03291 = this;
            }
            return Unit.INSTANCE;
        }

        /* compiled from: HealthDataViewModel.kt */
        @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", ""}, k = 3, mv = {2, 1, 0}, xi = 48)
        @DebugMetadata(c = "com.yucheng.smarthealthpro.viewmodel.HealthDataViewModel$savaBloodFatUricAcidData$1$2", f = "HealthDataViewModel.kt", i = {}, l = {386, 389, 392, 395}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.yucheng.smarthealthpro.viewmodel.HealthDataViewModel$savaBloodFatUricAcidData$1$2, reason: invalid class name */
        static final class AnonymousClass2 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
            final /* synthetic */ ArrayList<BloodKetones> $bloodKetonesList;
            final /* synthetic */ ArrayList<BloodLipids> $bloodLipidsList;
            final /* synthetic */ ArrayList<HealthMetric> $healthMetricList;
            final /* synthetic */ ArrayList<UricAcid> $uricAcidList;
            int label;
            final /* synthetic */ HealthDataViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass2(ArrayList<BloodLipids> arrayList, HealthDataViewModel healthDataViewModel, ArrayList<UricAcid> arrayList2, ArrayList<BloodKetones> arrayList3, ArrayList<HealthMetric> arrayList4, Continuation<? super AnonymousClass2> continuation) {
                super(1, continuation);
                this.$bloodLipidsList = arrayList;
                this.this$0 = healthDataViewModel;
                this.$uricAcidList = arrayList2;
                this.$bloodKetonesList = arrayList3;
                this.$healthMetricList = arrayList4;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Continuation<?> continuation) {
                return new AnonymousClass2(this.$bloodLipidsList, this.this$0, this.$uricAcidList, this.$bloodKetonesList, this.$healthMetricList, continuation);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Continuation<? super Unit> continuation) {
                return ((AnonymousClass2) create(continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Removed duplicated region for block: B:26:0x007a  */
            /* JADX WARN: Removed duplicated region for block: B:31:0x009a  */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r8) throws java.lang.Throwable {
                /*
                    r7 = this;
                    java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                    int r1 = r7.label
                    r2 = 4
                    r3 = 3
                    r4 = 2
                    r5 = 1
                    if (r1 == 0) goto L2d
                    if (r1 == r5) goto L29
                    if (r1 == r4) goto L25
                    if (r1 == r3) goto L21
                    if (r1 != r2) goto L19
                    kotlin.ResultKt.throwOnFailure(r8)
                    goto Lb0
                L19:
                    java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                    java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                    r8.<init>(r0)
                    throw r8
                L21:
                    kotlin.ResultKt.throwOnFailure(r8)
                    goto L90
                L25:
                    kotlin.ResultKt.throwOnFailure(r8)
                    goto L70
                L29:
                    kotlin.ResultKt.throwOnFailure(r8)
                    goto L50
                L2d:
                    kotlin.ResultKt.throwOnFailure(r8)
                    java.util.ArrayList<com.yucheng.smarthealthpro.database.room.bean.BloodLipids> r8 = r7.$bloodLipidsList
                    java.util.Collection r8 = (java.util.Collection) r8
                    boolean r8 = r8.isEmpty()
                    if (r8 != 0) goto L50
                    com.yucheng.smarthealthpro.viewmodel.HealthDataViewModel r8 = r7.this$0
                    com.yucheng.smarthealthpro.repository.BloodLipidsRepository r8 = com.yucheng.smarthealthpro.viewmodel.HealthDataViewModel.access$getBloodLipidsRepository(r8)
                    java.util.ArrayList<com.yucheng.smarthealthpro.database.room.bean.BloodLipids> r1 = r7.$bloodLipidsList
                    java.util.List r1 = (java.util.List) r1
                    r6 = r7
                    kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
                    r7.label = r5
                    java.lang.Object r8 = r8.insertAll(r1, r6)
                    if (r8 != r0) goto L50
                    return r0
                L50:
                    java.util.ArrayList<com.yucheng.smarthealthpro.database.room.bean.UricAcid> r8 = r7.$uricAcidList
                    java.util.Collection r8 = (java.util.Collection) r8
                    boolean r8 = r8.isEmpty()
                    if (r8 != 0) goto L70
                    com.yucheng.smarthealthpro.viewmodel.HealthDataViewModel r8 = r7.this$0
                    com.yucheng.smarthealthpro.repository.UricAcidRepository r8 = com.yucheng.smarthealthpro.viewmodel.HealthDataViewModel.access$getUricAcidRepository(r8)
                    java.util.ArrayList<com.yucheng.smarthealthpro.database.room.bean.UricAcid> r1 = r7.$uricAcidList
                    java.util.List r1 = (java.util.List) r1
                    r5 = r7
                    kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
                    r7.label = r4
                    java.lang.Object r8 = r8.insertAll(r1, r5)
                    if (r8 != r0) goto L70
                    return r0
                L70:
                    java.util.ArrayList<com.yucheng.smarthealthpro.database.room.bean.BloodKetones> r8 = r7.$bloodKetonesList
                    java.util.Collection r8 = (java.util.Collection) r8
                    boolean r8 = r8.isEmpty()
                    if (r8 != 0) goto L90
                    com.yucheng.smarthealthpro.viewmodel.HealthDataViewModel r8 = r7.this$0
                    com.yucheng.smarthealthpro.repository.BloodKetonesRepository r8 = com.yucheng.smarthealthpro.viewmodel.HealthDataViewModel.access$getBloodKetonesRepository(r8)
                    java.util.ArrayList<com.yucheng.smarthealthpro.database.room.bean.BloodKetones> r1 = r7.$bloodKetonesList
                    java.util.List r1 = (java.util.List) r1
                    r4 = r7
                    kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
                    r7.label = r3
                    java.lang.Object r8 = r8.insertAll(r1, r4)
                    if (r8 != r0) goto L90
                    return r0
                L90:
                    java.util.ArrayList<com.yucheng.smarthealthpro.database.room.bean.HealthMetric> r8 = r7.$healthMetricList
                    java.util.Collection r8 = (java.util.Collection) r8
                    boolean r8 = r8.isEmpty()
                    if (r8 != 0) goto Lb0
                    com.yucheng.smarthealthpro.viewmodel.HealthDataViewModel r8 = r7.this$0
                    com.yucheng.smarthealthpro.repository.HealthMetricRepository r8 = com.yucheng.smarthealthpro.viewmodel.HealthDataViewModel.access$getHealthMetricRepository(r8)
                    java.util.ArrayList<com.yucheng.smarthealthpro.database.room.bean.HealthMetric> r1 = r7.$healthMetricList
                    java.util.List r1 = (java.util.List) r1
                    r3 = r7
                    kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3
                    r7.label = r2
                    java.lang.Object r8 = r8.insertAll(r1, r3)
                    if (r8 != r0) goto Lb0
                    return r0
                Lb0:
                    kotlin.Unit r8 = kotlin.Unit.INSTANCE
                    return r8
                */
                throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.viewmodel.HealthDataViewModel.C03291.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }
    }

    public final void savaBloodFatUricAcidData(Context context, HashMap<?, ?> resultMap, DatabaseCallback callback) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(resultMap, "resultMap");
        Intrinsics.checkNotNullParameter(callback, "callback");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new C03291(resultMap, callback, context, this, null), 2, null);
    }

    /* compiled from: HealthDataViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.viewmodel.HealthDataViewModel$savaMotionPatternData$1", f = "HealthDataViewModel.kt", i = {}, l = {438}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.yucheng.smarthealthpro.viewmodel.HealthDataViewModel$savaMotionPatternData$1, reason: invalid class name and case insensitive filesystem */
    static final class C03311 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ DatabaseCallback $callback;
        final /* synthetic */ Context $context;
        final /* synthetic */ HashMap<?, ?> $resultMap;
        Object L$0;
        Object L$1;
        int label;
        final /* synthetic */ HealthDataViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03311(HashMap<?, ?> map, Context context, HealthDataViewModel healthDataViewModel, DatabaseCallback databaseCallback, Continuation<? super C03311> continuation) {
            super(2, continuation);
            this.$resultMap = map;
            this.$context = context;
            this.this$0 = healthDataViewModel;
            this.$callback = databaseCallback;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C03311(this.$resultMap, this.$context, this.this$0, this.$callback, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03311) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objInsertAll;
            DatabaseCallback databaseCallback;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.label;
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                Object objFromJson = new Gson().fromJson(this.$resultMap.toString(), (Class<Object>) MotionPatternBean.class);
                Intrinsics.checkNotNullExpressionValue(objFromJson, "fromJson(...)");
                List<MotionPatternBean.DataBean> data = ((MotionPatternBean) objFromJson).data;
                Intrinsics.checkNotNullExpressionValue(data, "data");
                Context context = this.$context;
                ArrayList arrayList = new ArrayList();
                int i3 = 0;
                for (Object obj2 : data) {
                    int i4 = i3 + 1;
                    if (i3 < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    MotionPatternBean.DataBean dataBean = (MotionPatternBean.DataBean) obj2;
                    long j2 = dataBean.startTime;
                    long j3 = dataBean.endTime;
                    String strDateForStringYearToDate = TimeStampUtils.dateForStringYearToDate(TimeStampUtils.longStampForDate(dataBean.startTime));
                    Intrinsics.checkNotNullExpressionValue(strDateForStringYearToDate, "dateForStringYearToDate(...)");
                    int i5 = dataBean.sportSteps;
                    int i6 = dataBean.sportCalories;
                    int i7 = dataBean.sportDistances;
                    int i8 = dataBean.sportMode;
                    int i9 = dataBean.startMethod;
                    int i10 = dataBean.sportHeartRate;
                    long j4 = dataBean.sportTime;
                    int i11 = dataBean.minHeartRate;
                    int i12 = dataBean.maxHeartRate;
                    String userName = UserInfoUtil.getUserName();
                    Intrinsics.checkNotNullExpressionValue(userName, "getUserName(...)");
                    String deviceType = Tools.getDeviceType(context);
                    Intrinsics.checkNotNullExpressionValue(deviceType, "getDeviceType(...)");
                    String bindDeviceMac = YCBTClient.getBindDeviceMac();
                    Intrinsics.checkNotNullExpressionValue(bindDeviceMac, "getBindDeviceMac(...)");
                    arrayList.add(new MotionPattern(null, i3, j2, j3, strDateForStringYearToDate, i5, i7, i6, i8, i9, i10, j4, i11, i12, userName, deviceType, bindDeviceMac, null, false, false, 917505, null));
                    i3 = i4;
                }
                ArrayList arrayList2 = arrayList;
                HealthDataViewModel healthDataViewModel = this.this$0;
                DatabaseCallback databaseCallback2 = this.$callback;
                MotionPatternRepository motionPatternRepository = healthDataViewModel.getMotionPatternRepository();
                this.L$0 = arrayList2;
                this.L$1 = databaseCallback2;
                this.label = 1;
                objInsertAll = motionPatternRepository.insertAll(arrayList2, this);
                if (objInsertAll == coroutine_suspended) {
                    return coroutine_suspended;
                }
                databaseCallback = databaseCallback2;
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                databaseCallback = (DatabaseCallback) this.L$1;
                ResultKt.throwOnFailure(obj);
                objInsertAll = obj;
            }
            databaseCallback.onExecuteCallback(((Boolean) objInsertAll).booleanValue());
            return Unit.INSTANCE;
        }
    }

    public final void savaMotionPatternData(Context context, HashMap<?, ?> resultMap, DatabaseCallback callback) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(resultMap, "resultMap");
        Intrinsics.checkNotNullParameter(callback, "callback");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new C03311(resultMap, context, this, callback, null), 2, null);
    }

    /* compiled from: HealthDataViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.viewmodel.HealthDataViewModel$savaPhysiotherapyData$1", f = "HealthDataViewModel.kt", i = {}, l = {474}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.yucheng.smarthealthpro.viewmodel.HealthDataViewModel$savaPhysiotherapyData$1, reason: invalid class name and case insensitive filesystem */
    static final class C03321 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ DatabaseCallback $callback;
        final /* synthetic */ Context $context;
        final /* synthetic */ HashMap<?, ?> $resultMap;
        Object L$0;
        Object L$1;
        int label;
        final /* synthetic */ HealthDataViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03321(HashMap<?, ?> map, Context context, HealthDataViewModel healthDataViewModel, DatabaseCallback databaseCallback, Continuation<? super C03321> continuation) {
            super(2, continuation);
            this.$resultMap = map;
            this.$context = context;
            this.this$0 = healthDataViewModel;
            this.$callback = databaseCallback;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C03321(this.$resultMap, this.$context, this.this$0, this.$callback, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03321) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objInsertAll;
            DatabaseCallback databaseCallback;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.label;
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                Object objFromJson = new Gson().fromJson(this.$resultMap.toString(), (Class<Object>) PhysiotherapyBean.class);
                Intrinsics.checkNotNullExpressionValue(objFromJson, "fromJson(...)");
                PhysiotherapyBean physiotherapyBean = (PhysiotherapyBean) objFromJson;
                if (physiotherapyBean.getData() == null) {
                    return Unit.INSTANCE;
                }
                List<PhysiotherapyBean.DataDTO> data = physiotherapyBean.getData();
                Intrinsics.checkNotNullExpressionValue(data, "getData(...)");
                Context context = this.$context;
                ArrayList arrayList = new ArrayList();
                int i3 = 0;
                for (Object obj2 : data) {
                    int i4 = i3 + 1;
                    if (i3 < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    PhysiotherapyBean.DataDTO dataDTO = (PhysiotherapyBean.DataDTO) obj2;
                    String strDateForStringYearToDate = TimeStampUtils.dateForStringYearToDate(TimeStampUtils.longStampForDate(dataDTO.getPhysiotherapyStartTime()));
                    Intrinsics.checkNotNullExpressionValue(strDateForStringYearToDate, "dateForStringYearToDate(...)");
                    long physiotherapyStartTime = dataDTO.getPhysiotherapyStartTime();
                    int physiotherapyStartType = dataDTO.getPhysiotherapyStartType();
                    int physiotherapyDuration = dataDTO.getPhysiotherapyDuration();
                    int physiotherapyDurationLevel = dataDTO.getPhysiotherapyDurationLevel();
                    int physiotherapyPowerLevel = dataDTO.getPhysiotherapyPowerLevel();
                    int physiotherapyType = dataDTO.getPhysiotherapyType();
                    String userName = UserInfoUtil.getUserName();
                    Intrinsics.checkNotNullExpressionValue(userName, "getUserName(...)");
                    String deviceType = Tools.getDeviceType(context);
                    Intrinsics.checkNotNullExpressionValue(deviceType, "getDeviceType(...)");
                    String bindDeviceMac = YCBTClient.getBindDeviceMac();
                    Intrinsics.checkNotNullExpressionValue(bindDeviceMac, "getBindDeviceMac(...)");
                    arrayList.add(new Physiotherapy(null, i3, physiotherapyStartTime, strDateForStringYearToDate, physiotherapyDuration, physiotherapyType, physiotherapyStartType, physiotherapyPowerLevel, physiotherapyDurationLevel, userName, deviceType, bindDeviceMac, null, false, 12289, null));
                    i3 = i4;
                }
                ArrayList arrayList2 = arrayList;
                HealthDataViewModel healthDataViewModel = this.this$0;
                DatabaseCallback databaseCallback2 = this.$callback;
                PhysiotherapyRepository physiotherapyRepository = healthDataViewModel.getPhysiotherapyRepository();
                this.L$0 = arrayList2;
                this.L$1 = databaseCallback2;
                this.label = 1;
                objInsertAll = physiotherapyRepository.insertAll(arrayList2, this);
                if (objInsertAll == coroutine_suspended) {
                    return coroutine_suspended;
                }
                databaseCallback = databaseCallback2;
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                databaseCallback = (DatabaseCallback) this.L$1;
                ResultKt.throwOnFailure(obj);
                objInsertAll = obj;
            }
            databaseCallback.onExecuteCallback(((Boolean) objInsertAll).booleanValue());
            return Unit.INSTANCE;
        }
    }

    public final void savaPhysiotherapyData(Context context, HashMap<?, ?> resultMap, DatabaseCallback callback) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(resultMap, "resultMap");
        Intrinsics.checkNotNullParameter(callback, "callback");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new C03321(resultMap, context, this, callback, null), 2, null);
    }

    /* compiled from: HealthDataViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.viewmodel.HealthDataViewModel$savaBodyData$1", f = "HealthDataViewModel.kt", i = {}, l = {514}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.yucheng.smarthealthpro.viewmodel.HealthDataViewModel$savaBodyData$1, reason: invalid class name and case insensitive filesystem */
    static final class C03301 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ DatabaseCallback $callback;
        final /* synthetic */ Context $context;
        final /* synthetic */ HashMap<?, ?> $resultMap;
        Object L$0;
        Object L$1;
        int label;
        final /* synthetic */ HealthDataViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03301(HashMap<?, ?> map, Context context, HealthDataViewModel healthDataViewModel, DatabaseCallback databaseCallback, Continuation<? super C03301> continuation) {
            super(2, continuation);
            this.$resultMap = map;
            this.$context = context;
            this.this$0 = healthDataViewModel;
            this.$callback = databaseCallback;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C03301(this.$resultMap, this.$context, this.this$0, this.$callback, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03301) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objInsertAll;
            DatabaseCallback databaseCallback;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.label;
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                Object objFromJson = new Gson().fromJson(this.$resultMap.toString(), (Class<Object>) BodyDataResponse.class);
                Intrinsics.checkNotNullExpressionValue(objFromJson, "fromJson(...)");
                List<BodyDataResponse.DataBean> data = ((BodyDataResponse) objFromJson).getData();
                Intrinsics.checkNotNullExpressionValue(data, "getData(...)");
                Context context = this.$context;
                ArrayList arrayList = new ArrayList();
                int i3 = 0;
                for (Object obj2 : data) {
                    int i4 = i3 + 1;
                    if (i3 < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    BodyDataResponse.DataBean dataBean = (BodyDataResponse.DataBean) obj2;
                    long j2 = dataBean.time;
                    String strDateForStringYearToDate = TimeStampUtils.dateForStringYearToDate(TimeStampUtils.longStampForDate(dataBean.time));
                    Intrinsics.checkNotNullExpressionValue(strDateForStringYearToDate, "dateForStringYearToDate(...)");
                    int i5 = dataBean.loadIndexInteger;
                    int i6 = dataBean.loadIndexFloat;
                    int i7 = dataBean.hrvInteger;
                    int i8 = dataBean.hrvFloat;
                    int i9 = dataBean.pressureInteger;
                    int i10 = dataBean.pressureFloat;
                    int i11 = dataBean.bodyInteger;
                    int i12 = dataBean.bodyFloat;
                    int i13 = dataBean.sympatheticInteger;
                    int i14 = dataBean.sympatheticFloat;
                    int i15 = dataBean.sdn;
                    int i16 = dataBean.maximalOxygenIntake;
                    String userName = UserInfoUtil.getUserName();
                    Intrinsics.checkNotNullExpressionValue(userName, "getUserName(...)");
                    String deviceType = Tools.getDeviceType(context);
                    Intrinsics.checkNotNullExpressionValue(deviceType, "getDeviceType(...)");
                    String bindDeviceMac = YCBTClient.getBindDeviceMac();
                    Intrinsics.checkNotNullExpressionValue(bindDeviceMac, "getBindDeviceMac(...)");
                    arrayList.add(new BodyData(null, i3, j2, strDateForStringYearToDate, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, userName, deviceType, bindDeviceMac, null, false, false, 3670017, null));
                    i3 = i4;
                }
                ArrayList arrayList2 = arrayList;
                HealthDataViewModel healthDataViewModel = this.this$0;
                DatabaseCallback databaseCallback2 = this.$callback;
                BodyDataRepository bodyDataRepository = healthDataViewModel.getBodyDataRepository();
                this.L$0 = arrayList2;
                this.L$1 = databaseCallback2;
                this.label = 1;
                objInsertAll = bodyDataRepository.insertAll(arrayList2, this);
                if (objInsertAll == coroutine_suspended) {
                    return coroutine_suspended;
                }
                databaseCallback = databaseCallback2;
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                databaseCallback = (DatabaseCallback) this.L$1;
                ResultKt.throwOnFailure(obj);
                objInsertAll = obj;
            }
            databaseCallback.onExecuteCallback(((Boolean) objInsertAll).booleanValue());
            return Unit.INSTANCE;
        }
    }

    public final void savaBodyData(Context context, HashMap<?, ?> resultMap, DatabaseCallback callback) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(resultMap, "resultMap");
        Intrinsics.checkNotNullParameter(callback, "callback");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new C03301(resultMap, context, this, callback, null), 2, null);
    }

    /* compiled from: HealthDataViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.viewmodel.HealthDataViewModel$uploadHistoryData$1", f = "HealthDataViewModel.kt", i = {}, l = {523, 526, 534, 542, Constants.DATATYPE.GetPowerStatistics, Constants.DATATYPE.GetEcgMode, Constants.DATATYPE.GetAlgorithmicLicense, 556, 564, 572, 580, 588, 596, TypedValues.MotionType.TYPE_QUANTIZE_INTERPOLATOR}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.yucheng.smarthealthpro.viewmodel.HealthDataViewModel$uploadHistoryData$1, reason: invalid class name and case insensitive filesystem */
    static final class C03401 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Context $context;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03401(Context context, Continuation<? super C03401> continuation) {
            super(2, continuation);
            this.$context = context;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return HealthDataViewModel.this.new C03401(this.$context, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03401) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:25:0x0084  */
        /* JADX WARN: Removed duplicated region for block: B:30:0x00ae  */
        /* JADX WARN: Removed duplicated region for block: B:35:0x00d8  */
        /* JADX WARN: Removed duplicated region for block: B:40:0x011b A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:43:0x013d A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:46:0x015f A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:49:0x0168  */
        /* JADX WARN: Removed duplicated region for block: B:54:0x0193  */
        /* JADX WARN: Removed duplicated region for block: B:59:0x01be  */
        /* JADX WARN: Removed duplicated region for block: B:64:0x01e9  */
        /* JADX WARN: Removed duplicated region for block: B:69:0x0214  */
        /* JADX WARN: Removed duplicated region for block: B:74:0x023f  */
        /* JADX WARN: Removed duplicated region for block: B:79:0x026a  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r8) throws java.lang.Throwable {
            /*
                Method dump skipped, instructions count: 690
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.viewmodel.HealthDataViewModel.C03401.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public final void uploadHistoryData(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new C03401(context, null), 2, null);
    }

    /* compiled from: HealthDataViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.viewmodel.HealthDataViewModel$uploadEcgHistoryData$1", f = "HealthDataViewModel.kt", i = {}, l = {615}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.yucheng.smarthealthpro.viewmodel.HealthDataViewModel$uploadEcgHistoryData$1, reason: invalid class name and case insensitive filesystem */
    static final class C03391 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Context $context;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03391(Context context, Continuation<? super C03391> continuation) {
            super(2, continuation);
            this.$context = context;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return HealthDataViewModel.this.new C03391(this.$context, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03391) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.label;
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                DataSyncRepository dataSyncRepository = HealthDataViewModel.this.getDataSyncRepository();
                Context context = this.$context;
                EcgMeasureRepository ecgMeasureRepository = HealthDataViewModel.this.getEcgMeasureRepository();
                String userName = UserInfoUtil.getUserName();
                Intrinsics.checkNotNullExpressionValue(userName, "getUserName(...)");
                this.label = 1;
                if (dataSyncRepository.syncEcgData(context, ecgMeasureRepository, userName, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    public final void uploadEcgHistoryData(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new C03391(context, null), 2, null);
    }

    /* compiled from: HealthDataViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.viewmodel.HealthDataViewModel$deleteSportRecord$1", f = "HealthDataViewModel.kt", i = {}, l = {626}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.yucheng.smarthealthpro.viewmodel.HealthDataViewModel$deleteSportRecord$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ long $startTimestamp;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(long j2, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$startTimestamp = j2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return HealthDataViewModel.this.new AnonymousClass1(this.$startTimestamp, continuation);
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
                SportRecordRepository sportRecordRepository = HealthDataViewModel.this.getSportRecordRepository();
                long j2 = this.$startTimestamp;
                String userName = UserInfoUtil.getUserName();
                Intrinsics.checkNotNullExpressionValue(userName, "getUserName(...)");
                this.label = 1;
                obj = sportRecordRepository.deleteSportRecord(j2, userName, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            ((Boolean) obj).booleanValue();
            return Unit.INSTANCE;
        }
    }

    public final void deleteSportRecord(long startTimestamp) {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new AnonymousClass1(startTimestamp, null), 2, null);
    }

    /* compiled from: HealthDataViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.viewmodel.HealthDataViewModel$startMonitoring$1", f = "HealthDataViewModel.kt", i = {}, l = {633}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.yucheng.smarthealthpro.viewmodel.HealthDataViewModel$startMonitoring$1, reason: invalid class name and case insensitive filesystem */
    static final class C03381 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Context $context;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03381(Context context, Continuation<? super C03381> continuation) {
            super(2, continuation);
            this.$context = context;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C03381(this.$context, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03381) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.label;
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (AnomalyDataMonitor.INSTANCE.startMonitoring(this.$context, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    public final void startMonitoring(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new C03381(context, null), 2, null);
    }

    /* compiled from: HealthDataViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.viewmodel.HealthDataViewModel$forceUploadLogFile$1", f = "HealthDataViewModel.kt", i = {}, l = {639}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.yucheng.smarthealthpro.viewmodel.HealthDataViewModel$forceUploadLogFile$1, reason: invalid class name and case insensitive filesystem */
    static final class C03281 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C03281(Continuation<? super C03281> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C03281(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03281) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.label;
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (AnomalyDataMonitor.INSTANCE.uploadLogFile(this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    public final void forceUploadLogFile() {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new C03281(null), 2, null);
    }
}
