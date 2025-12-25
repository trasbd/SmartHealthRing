package com.yucheng.smarthealthpro.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.yucheng.smarthealthpro.MyApplication;
import com.yucheng.smarthealthpro.data.packed.HealthResult;
import com.yucheng.smarthealthpro.database.room.bean.MotionPattern;
import com.yucheng.smarthealthpro.login.normal.util.UserInfoUtil;
import com.yucheng.smarthealthpro.repository.MotionPatternRepository;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
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

/* compiled from: SportRunningHisMapViewModel.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0016R\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R \u0010\n\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R#\u0010\u000f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\f0\u00108F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0018"}, d2 = {"Lcom/yucheng/smarthealthpro/viewmodel/SportRunningHisMapViewModel;", "Landroidx/lifecycle/ViewModel;", "<init>", "()V", "motionPatternRepository", "Lcom/yucheng/smarthealthpro/repository/MotionPatternRepository;", "getMotionPatternRepository", "()Lcom/yucheng/smarthealthpro/repository/MotionPatternRepository;", "motionPatternRepository$delegate", "Lkotlin/Lazy;", "_historyHeartRateResultFlow", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lcom/yucheng/smarthealthpro/data/packed/HealthResult;", "", "Lcom/yucheng/smarthealthpro/database/room/bean/MotionPattern;", "historyHeartRateResultFlow", "Lkotlinx/coroutines/flow/SharedFlow;", "getHistoryHeartRateResultFlow", "()Lkotlinx/coroutines/flow/SharedFlow;", "getHeartRateDataByTimeRange", "", "startTime", "", "endTime", "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class SportRunningHisMapViewModel extends ViewModel {

    /* renamed from: motionPatternRepository$delegate, reason: from kotlin metadata */
    private final Lazy motionPatternRepository = LazyKt.lazy(new Function0() { // from class: com.yucheng.smarthealthpro.viewmodel.SportRunningHisMapViewModel$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return SportRunningHisMapViewModel.motionPatternRepository_delegate$lambda$0();
        }
    });
    private final MutableSharedFlow<HealthResult<List<MotionPattern>>> _historyHeartRateResultFlow = SharedFlowKt.MutableSharedFlow$default(0, 0, null, 7, null);

    /* JADX INFO: Access modifiers changed from: private */
    public final MotionPatternRepository getMotionPatternRepository() {
        return (MotionPatternRepository) this.motionPatternRepository.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MotionPatternRepository motionPatternRepository_delegate$lambda$0() {
        return new MotionPatternRepository(MyApplication.sInstance.getAppDatabase().motionPatternDao());
    }

    public final SharedFlow<HealthResult<List<MotionPattern>>> getHistoryHeartRateResultFlow() {
        return FlowKt.asSharedFlow(this._historyHeartRateResultFlow);
    }

    /* compiled from: SportRunningHisMapViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.viewmodel.SportRunningHisMapViewModel$getHeartRateDataByTimeRange$1", f = "SportRunningHisMapViewModel.kt", i = {}, l = {33, 34}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.yucheng.smarthealthpro.viewmodel.SportRunningHisMapViewModel$getHeartRateDataByTimeRange$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ long $endTime;
        final /* synthetic */ long $startTime;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(long j2, long j3, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$startTime = j2;
            this.$endTime = j3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return SportRunningHisMapViewModel.this.new AnonymousClass1(this.$startTime, this.$endTime, continuation);
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
                MotionPatternRepository motionPatternRepository = SportRunningHisMapViewModel.this.getMotionPatternRepository();
                long j2 = this.$startTime;
                long j3 = this.$endTime;
                String userName = UserInfoUtil.getUserName();
                Intrinsics.checkNotNullExpressionValue(userName, "getUserName(...)");
                this.label = 1;
                obj = motionPatternRepository.getByTimeRange(j2, j3, userName, this);
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
            if (SportRunningHisMapViewModel.this._historyHeartRateResultFlow.emit(new HealthResult((List) obj), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final void getHeartRateDataByTimeRange(long startTime, long endTime) {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new AnonymousClass1(startTime, endTime, null), 3, null);
    }
}
