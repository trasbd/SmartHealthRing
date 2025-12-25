package com.yucheng.smarthealthpro.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.yucheng.smarthealthpro.MyApplication;
import com.yucheng.smarthealthpro.data.packed.HealthDayData;
import com.yucheng.smarthealthpro.database.room.bean.SportRecord;
import com.yucheng.smarthealthpro.login.normal.util.UserInfoUtil;
import com.yucheng.smarthealthpro.repository.SportRecordRepository;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Result;
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

/* compiled from: SportViewModel.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aJ\u000e\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u001dR\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u000f8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u00130\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u00130\u000f8F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0011¨\u0006\u001e"}, d2 = {"Lcom/yucheng/smarthealthpro/viewmodel/SportViewModel;", "Landroidx/lifecycle/ViewModel;", "<init>", "()V", "sportRecordRepository", "Lcom/yucheng/smarthealthpro/repository/SportRecordRepository;", "getSportRecordRepository", "()Lcom/yucheng/smarthealthpro/repository/SportRecordRepository;", "sportRecordRepository$delegate", "Lkotlin/Lazy;", "_sportRecordDataFlow", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lcom/yucheng/smarthealthpro/data/packed/HealthDayData;", "Lcom/yucheng/smarthealthpro/database/room/bean/SportRecord;", "sportRecordDataFlow", "Lkotlinx/coroutines/flow/SharedFlow;", "getSportRecordDataFlow", "()Lkotlinx/coroutines/flow/SharedFlow;", "_deleteResultFlow", "Lkotlin/Result;", "", "deleteResultFlow", "getDeleteResultFlow", "getAllData", "", "type", "", "deleteSportRecord", "startTimestamp", "", "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class SportViewModel extends ViewModel {

    /* renamed from: sportRecordRepository$delegate, reason: from kotlin metadata */
    private final Lazy sportRecordRepository = LazyKt.lazy(new Function0() { // from class: com.yucheng.smarthealthpro.viewmodel.SportViewModel$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return SportViewModel.sportRecordRepository_delegate$lambda$0();
        }
    });
    private final MutableSharedFlow<HealthDayData<SportRecord>> _sportRecordDataFlow = SharedFlowKt.MutableSharedFlow$default(0, 0, null, 7, null);
    private final MutableSharedFlow<Result<Boolean>> _deleteResultFlow = SharedFlowKt.MutableSharedFlow$default(0, 0, null, 7, null);

    /* JADX INFO: Access modifiers changed from: private */
    public final SportRecordRepository getSportRecordRepository() {
        return (SportRecordRepository) this.sportRecordRepository.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final SportRecordRepository sportRecordRepository_delegate$lambda$0() {
        return new SportRecordRepository(MyApplication.sInstance.getAppDatabase().sportRecordDao());
    }

    public final SharedFlow<HealthDayData<SportRecord>> getSportRecordDataFlow() {
        return FlowKt.asSharedFlow(this._sportRecordDataFlow);
    }

    public final SharedFlow<Result<Boolean>> getDeleteResultFlow() {
        return FlowKt.asSharedFlow(this._deleteResultFlow);
    }

    /* compiled from: SportViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.viewmodel.SportViewModel$getAllData$1", f = "SportViewModel.kt", i = {}, l = {41, 42}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.yucheng.smarthealthpro.viewmodel.SportViewModel$getAllData$1, reason: invalid class name and case insensitive filesystem */
    static final class C03631 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C03631(Continuation<? super C03631> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return SportViewModel.this.new C03631(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03631) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.label;
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                SportRecordRepository sportRecordRepository = SportViewModel.this.getSportRecordRepository();
                String userName = UserInfoUtil.getUserName();
                Intrinsics.checkNotNullExpressionValue(userName, "getUserName(...)");
                this.label = 1;
                obj = sportRecordRepository.getAllData(userName, this);
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
            List list = (List) obj;
            this.label = 2;
            if (SportViewModel.this._sportRecordDataFlow.emit(new HealthDayData("", null, list, 2, null), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final void getAllData(int type) {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C03631(null), 3, null);
    }

    /* compiled from: SportViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.viewmodel.SportViewModel$deleteSportRecord$1", f = "SportViewModel.kt", i = {}, l = {51, 52}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.yucheng.smarthealthpro.viewmodel.SportViewModel$deleteSportRecord$1, reason: invalid class name */
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
            return SportViewModel.this.new AnonymousClass1(this.$startTimestamp, continuation);
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
                SportRecordRepository sportRecordRepository = SportViewModel.this.getSportRecordRepository();
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
                    if (i2 != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                }
                ResultKt.throwOnFailure(obj);
            }
            ((Boolean) obj).booleanValue();
            MutableSharedFlow mutableSharedFlow = SportViewModel.this._deleteResultFlow;
            Result.Companion companion = Result.INSTANCE;
            this.label = 2;
            if (mutableSharedFlow.emit(Result.m2617boximpl(Result.m2618constructorimpl(Boxing.boxBoolean(true))), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final void deleteSportRecord(long startTimestamp) {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new AnonymousClass1(startTimestamp, null), 3, null);
    }
}
