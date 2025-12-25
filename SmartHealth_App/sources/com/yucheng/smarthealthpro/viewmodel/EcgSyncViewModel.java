package com.yucheng.smarthealthpro.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.yucheng.smarthealthpro.MyApplication;
import com.yucheng.smarthealthpro.data.bean.EcgSaveBean;
import com.yucheng.smarthealthpro.data.bean.EcgSyncCheckResult;
import com.yucheng.smarthealthpro.data.packed.HealthResult;
import com.yucheng.smarthealthpro.database.room.bean.EcgMeasure;
import com.yucheng.smarthealthpro.home.activity.ecg.bean.EcgSyncListResponse;
import com.yucheng.smarthealthpro.login.normal.util.UserInfoUtil;
import com.yucheng.smarthealthpro.repository.EcgMeasureRepository;
import com.yucheng.ycbtsdk.YCBTClient;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
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

/* compiled from: EcgSyncViewModel.kt */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u001e\u001a\u00020\u001fJ\u000e\u0010 \u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\"J\u0016\u0010#\u001a\u00020\u001f2\u0006\u0010$\u001a\u00020\u000e2\u0006\u0010%\u001a\u00020\"J\u000e\u0010&\u001a\u00020\u001f2\u0006\u0010'\u001a\u00020\"J\u001a\u0010(\u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\"2\n\u0010)\u001a\u00060*R\u00020+R\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R \u0010\n\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R#\u0010\u000f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\f0\u00108F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R \u0010\u0013\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R#\u0010\u0014\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\f0\u00108F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0012R\u001a\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\f0\u00108F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0012R\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001b0\u00108F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u0012¨\u0006,"}, d2 = {"Lcom/yucheng/smarthealthpro/viewmodel/EcgSyncViewModel;", "Landroidx/lifecycle/ViewModel;", "<init>", "()V", "ecgMeasureRepository", "Lcom/yucheng/smarthealthpro/repository/EcgMeasureRepository;", "getEcgMeasureRepository", "()Lcom/yucheng/smarthealthpro/repository/EcgMeasureRepository;", "ecgMeasureRepository$delegate", "Lkotlin/Lazy;", "_historyEcgResultFlow", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lcom/yucheng/smarthealthpro/data/packed/HealthResult;", "", "Lcom/yucheng/smarthealthpro/database/room/bean/EcgMeasure;", "historyEcgResultFlow", "Lkotlinx/coroutines/flow/SharedFlow;", "getHistoryEcgResultFlow", "()Lkotlinx/coroutines/flow/SharedFlow;", "_findEcgResultFlow", "findEcgResultFlow", "getFindEcgResultFlow", "_saveResultFlow", "Lcom/yucheng/smarthealthpro/data/bean/EcgSaveBean;", "saveResultFlow", "getSaveResultFlow", "_checkResultFlow", "Lcom/yucheng/smarthealthpro/data/bean/EcgSyncCheckResult;", "checkResultFlow", "getCheckResultFlow", "getAllData", "", "getByStartTime", "startTime", "", "saveEcgMeasureData", "ecgMeasure", "sendTime", "updateEcgUploaded", "id", "checkIfDuplicate", "bean", "Lcom/yucheng/smarthealthpro/home/activity/ecg/bean/EcgSyncListResponse$DataBean;", "Lcom/yucheng/smarthealthpro/home/activity/ecg/bean/EcgSyncListResponse;", "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class EcgSyncViewModel extends ViewModel {

    /* renamed from: ecgMeasureRepository$delegate, reason: from kotlin metadata */
    private final Lazy ecgMeasureRepository = LazyKt.lazy(new Function0() { // from class: com.yucheng.smarthealthpro.viewmodel.EcgSyncViewModel$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return EcgSyncViewModel.ecgMeasureRepository_delegate$lambda$0();
        }
    });
    private final MutableSharedFlow<HealthResult<List<EcgMeasure>>> _historyEcgResultFlow = SharedFlowKt.MutableSharedFlow$default(0, 0, null, 7, null);
    private final MutableSharedFlow<HealthResult<List<EcgMeasure>>> _findEcgResultFlow = SharedFlowKt.MutableSharedFlow$default(0, 0, null, 7, null);
    private final MutableSharedFlow<HealthResult<EcgSaveBean>> _saveResultFlow = SharedFlowKt.MutableSharedFlow$default(0, 0, null, 7, null);
    private final MutableSharedFlow<EcgSyncCheckResult> _checkResultFlow = SharedFlowKt.MutableSharedFlow$default(0, 0, null, 7, null);

    /* JADX INFO: Access modifiers changed from: private */
    public final EcgMeasureRepository getEcgMeasureRepository() {
        return (EcgMeasureRepository) this.ecgMeasureRepository.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final EcgMeasureRepository ecgMeasureRepository_delegate$lambda$0() {
        return new EcgMeasureRepository(MyApplication.sInstance.getAppDatabase().ecgMeasureDao());
    }

    public final SharedFlow<HealthResult<List<EcgMeasure>>> getHistoryEcgResultFlow() {
        return FlowKt.asSharedFlow(this._historyEcgResultFlow);
    }

    public final SharedFlow<HealthResult<List<EcgMeasure>>> getFindEcgResultFlow() {
        return FlowKt.asSharedFlow(this._findEcgResultFlow);
    }

    public final SharedFlow<HealthResult<EcgSaveBean>> getSaveResultFlow() {
        return FlowKt.asSharedFlow(this._saveResultFlow);
    }

    public final SharedFlow<EcgSyncCheckResult> getCheckResultFlow() {
        return FlowKt.asSharedFlow(this._checkResultFlow);
    }

    /* compiled from: EcgSyncViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.viewmodel.EcgSyncViewModel$getAllData$1", f = "EcgSyncViewModel.kt", i = {}, l = {57, 58}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.yucheng.smarthealthpro.viewmodel.EcgSyncViewModel$getAllData$1, reason: invalid class name and case insensitive filesystem */
    static final class C03211 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C03211(Continuation<? super C03211> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return EcgSyncViewModel.this.new C03211(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03211) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.label;
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                EcgMeasureRepository ecgMeasureRepository = EcgSyncViewModel.this.getEcgMeasureRepository();
                String userName = UserInfoUtil.getUserName();
                Intrinsics.checkNotNullExpressionValue(userName, "getUserName(...)");
                this.label = 1;
                obj = ecgMeasureRepository.getAllData(userName, this);
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
            this.label = 2;
            if (EcgSyncViewModel.this._historyEcgResultFlow.emit(new HealthResult((List) obj), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final void getAllData() {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C03211(null), 3, null);
    }

    /* compiled from: EcgSyncViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.viewmodel.EcgSyncViewModel$getByStartTime$1", f = "EcgSyncViewModel.kt", i = {}, l = {64, 65}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.yucheng.smarthealthpro.viewmodel.EcgSyncViewModel$getByStartTime$1, reason: invalid class name and case insensitive filesystem */
    static final class C03221 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ long $startTime;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03221(long j2, Continuation<? super C03221> continuation) {
            super(2, continuation);
            this.$startTime = j2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return EcgSyncViewModel.this.new C03221(this.$startTime, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03221) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.label;
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                EcgMeasureRepository ecgMeasureRepository = EcgSyncViewModel.this.getEcgMeasureRepository();
                long j2 = this.$startTime;
                String userName = UserInfoUtil.getUserName();
                Intrinsics.checkNotNullExpressionValue(userName, "getUserName(...)");
                String bindDeviceMac = YCBTClient.getBindDeviceMac();
                Intrinsics.checkNotNullExpressionValue(bindDeviceMac, "getBindDeviceMac(...)");
                this.label = 1;
                obj = ecgMeasureRepository.getByStartTime(j2, userName, bindDeviceMac, this);
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
            this.label = 2;
            if (EcgSyncViewModel.this._historyEcgResultFlow.emit(new HealthResult((List) obj), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final void getByStartTime(long startTime) {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C03221(startTime, null), 3, null);
    }

    /* compiled from: EcgSyncViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.viewmodel.EcgSyncViewModel$saveEcgMeasureData$1", f = "EcgSyncViewModel.kt", i = {}, l = {71, 72}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.yucheng.smarthealthpro.viewmodel.EcgSyncViewModel$saveEcgMeasureData$1, reason: invalid class name and case insensitive filesystem */
    static final class C03231 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ EcgMeasure $ecgMeasure;
        final /* synthetic */ long $sendTime;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03231(EcgMeasure ecgMeasure, long j2, Continuation<? super C03231> continuation) {
            super(2, continuation);
            this.$ecgMeasure = ecgMeasure;
            this.$sendTime = j2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return EcgSyncViewModel.this.new C03231(this.$ecgMeasure, this.$sendTime, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03231) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objInsert;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.label;
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                objInsert = EcgSyncViewModel.this.getEcgMeasureRepository().insert(this.$ecgMeasure, this);
                if (objInsert == coroutine_suspended) {
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
                objInsert = obj;
            }
            long jLongValue = ((Number) objInsert).longValue();
            MutableSharedFlow mutableSharedFlow = EcgSyncViewModel.this._saveResultFlow;
            long j2 = this.$sendTime;
            EcgMeasure ecgMeasure = this.$ecgMeasure;
            this.label = 2;
            if (mutableSharedFlow.emit(new HealthResult(new EcgSaveBean(j2, ecgMeasure.copy((1048574 & 1) != 0 ? ecgMeasure.id : Boxing.boxLong(jLongValue), (1048574 & 2) != 0 ? ecgMeasure.queryID : 0, (1048574 & 4) != 0 ? ecgMeasure.startTimestamp : 0L, (1048574 & 8) != 0 ? ecgMeasure.timeYearToDay : null, (1048574 & 16) != 0 ? ecgMeasure.hrv : 0, (1048574 & 32) != 0 ? ecgMeasure.heartRate : 0, (1048574 & 64) != 0 ? ecgMeasure.maxBp : 0, (1048574 & 128) != 0 ? ecgMeasure.minBp : 0, (1048574 & 256) != 0 ? ecgMeasure.measureData : null, (1048574 & 512) != 0 ? ecgMeasure.age : 0, (1048574 & 1024) != 0 ? ecgMeasure.sex : 0, (1048574 & 2048) != 0 ? ecgMeasure.isAfib : false, (1048574 & 4096) != 0 ? ecgMeasure.diagnoseType : 0, (1048574 & 8192) != 0 ? ecgMeasure.healthNorm : null, (1048574 & 16384) != 0 ? ecgMeasure.userId : null, (1048574 & 32768) != 0 ? ecgMeasure.deviceType : null, (1048574 & 65536) != 0 ? ecgMeasure.deviceMacAddress : null, (1048574 & 131072) != 0 ? ecgMeasure.dataGroupId : null, (1048574 & 262144) != 0 ? ecgMeasure.isUploaded : false, (1048574 & 524288) != 0 ? ecgMeasure.isOtherUploaded : false))), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final void saveEcgMeasureData(EcgMeasure ecgMeasure, long sendTime) {
        Intrinsics.checkNotNullParameter(ecgMeasure, "ecgMeasure");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C03231(ecgMeasure, sendTime, null), 3, null);
    }

    /* compiled from: EcgSyncViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.viewmodel.EcgSyncViewModel$updateEcgUploaded$1", f = "EcgSyncViewModel.kt", i = {}, l = {78}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.yucheng.smarthealthpro.viewmodel.EcgSyncViewModel$updateEcgUploaded$1, reason: invalid class name and case insensitive filesystem */
    static final class C03241 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ long $id;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03241(long j2, Continuation<? super C03241> continuation) {
            super(2, continuation);
            this.$id = j2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return EcgSyncViewModel.this.new C03241(this.$id, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03241) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.label;
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (EcgSyncViewModel.this.getEcgMeasureRepository().updateUploaded(this.$id, true, this) == coroutine_suspended) {
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

    public final void updateEcgUploaded(long id) {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C03241(id, null), 3, null);
    }

    /* compiled from: EcgSyncViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.viewmodel.EcgSyncViewModel$checkIfDuplicate$1", f = "EcgSyncViewModel.kt", i = {}, l = {84, 85}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.yucheng.smarthealthpro.viewmodel.EcgSyncViewModel$checkIfDuplicate$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ EcgSyncListResponse.DataBean $bean;
        final /* synthetic */ long $startTime;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(long j2, EcgSyncListResponse.DataBean dataBean, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$startTime = j2;
            this.$bean = dataBean;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return EcgSyncViewModel.this.new AnonymousClass1(this.$startTime, this.$bean, continuation);
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
                EcgMeasureRepository ecgMeasureRepository = EcgSyncViewModel.this.getEcgMeasureRepository();
                long j2 = this.$startTime;
                String userName = UserInfoUtil.getUserName();
                Intrinsics.checkNotNullExpressionValue(userName, "getUserName(...)");
                String bindDeviceMac = YCBTClient.getBindDeviceMac();
                Intrinsics.checkNotNullExpressionValue(bindDeviceMac, "getBindDeviceMac(...)");
                this.label = 1;
                obj = ecgMeasureRepository.getByStartTime(j2, userName, bindDeviceMac, this);
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
            this.label = 2;
            if (EcgSyncViewModel.this._checkResultFlow.emit(new EcgSyncCheckResult(this.$startTime, !((List) obj).isEmpty(), this.$bean), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final void checkIfDuplicate(long startTime, EcgSyncListResponse.DataBean bean) {
        Intrinsics.checkNotNullParameter(bean, "bean");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new AnonymousClass1(startTime, bean, null), 3, null);
    }
}
