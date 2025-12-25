package com.yucheng.smarthealthpro.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.yucheng.smarthealthpro.MyApplication;
import com.yucheng.smarthealthpro.data.packed.HealthDayData;
import com.yucheng.smarthealthpro.data.packed.HealthPackedData;
import com.yucheng.smarthealthpro.database.room.bean.UricAcid;
import com.yucheng.smarthealthpro.login.normal.util.UserInfoUtil;
import com.yucheng.smarthealthpro.repository.UricAcidRepository;
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

/* compiled from: UricAcidViewModel.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019J\u0016\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u001cR\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u000f8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u00130\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u00130\u000f8F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0011¨\u0006\u001d"}, d2 = {"Lcom/yucheng/smarthealthpro/viewmodel/UricAcidViewModel;", "Landroidx/lifecycle/ViewModel;", "<init>", "()V", "uricAcidRepository", "Lcom/yucheng/smarthealthpro/repository/UricAcidRepository;", "getUricAcidRepository", "()Lcom/yucheng/smarthealthpro/repository/UricAcidRepository;", "uricAcidRepository$delegate", "Lkotlin/Lazy;", "_uricAcidDataFlow", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lcom/yucheng/smarthealthpro/data/packed/HealthDayData;", "Lcom/yucheng/smarthealthpro/database/room/bean/UricAcid;", "uricAcidDataFlow", "Lkotlinx/coroutines/flow/SharedFlow;", "getUricAcidDataFlow", "()Lkotlinx/coroutines/flow/SharedFlow;", "_uricAcidPackedDataFlow", "Lcom/yucheng/smarthealthpro/data/packed/HealthPackedData;", "uricAcidPackedDataFlow", "getUricAcidPackedDataFlow", "getDayData", "", "dayStr", "", "getPackedDayData", "dayCount", "", "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class UricAcidViewModel extends ViewModel {

    /* renamed from: uricAcidRepository$delegate, reason: from kotlin metadata */
    private final Lazy uricAcidRepository = LazyKt.lazy(new Function0() { // from class: com.yucheng.smarthealthpro.viewmodel.UricAcidViewModel$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return UricAcidViewModel.uricAcidRepository_delegate$lambda$0();
        }
    });
    private final MutableSharedFlow<HealthDayData<UricAcid>> _uricAcidDataFlow = SharedFlowKt.MutableSharedFlow$default(0, 0, null, 7, null);
    private final MutableSharedFlow<HealthPackedData<UricAcid>> _uricAcidPackedDataFlow = SharedFlowKt.MutableSharedFlow$default(0, 0, null, 7, null);

    /* JADX INFO: Access modifiers changed from: private */
    public final UricAcidRepository getUricAcidRepository() {
        return (UricAcidRepository) this.uricAcidRepository.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final UricAcidRepository uricAcidRepository_delegate$lambda$0() {
        return new UricAcidRepository(MyApplication.sInstance.getAppDatabase().uricAcidDao());
    }

    public final SharedFlow<HealthDayData<UricAcid>> getUricAcidDataFlow() {
        return FlowKt.asSharedFlow(this._uricAcidDataFlow);
    }

    public final SharedFlow<HealthPackedData<UricAcid>> getUricAcidPackedDataFlow() {
        return FlowKt.asSharedFlow(this._uricAcidPackedDataFlow);
    }

    /* compiled from: UricAcidViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.viewmodel.UricAcidViewModel$getDayData$1", f = "UricAcidViewModel.kt", i = {}, l = {39, 40}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.yucheng.smarthealthpro.viewmodel.UricAcidViewModel$getDayData$1, reason: invalid class name */
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
            return UricAcidViewModel.this.new AnonymousClass1(this.$dayStr, continuation);
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
                UricAcidRepository uricAcidRepository = UricAcidViewModel.this.getUricAcidRepository();
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
            List list = (List) obj;
            this.label = 2;
            if (UricAcidViewModel.this._uricAcidDataFlow.emit(new HealthDayData(this.$dayStr, null, list, 2, null), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final void getDayData(String dayStr) {
        Intrinsics.checkNotNullParameter(dayStr, "dayStr");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new AnonymousClass1(dayStr, null), 3, null);
    }

    /* compiled from: UricAcidViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.viewmodel.UricAcidViewModel$getPackedDayData$1", f = "UricAcidViewModel.kt", i = {}, l = {49, 50}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.yucheng.smarthealthpro.viewmodel.UricAcidViewModel$getPackedDayData$1, reason: invalid class name and case insensitive filesystem */
    static final class C03661 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ int $dayCount;
        final /* synthetic */ String $dayStr;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03661(String str, int i2, Continuation<? super C03661> continuation) {
            super(2, continuation);
            this.$dayStr = str;
            this.$dayCount = i2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return UricAcidViewModel.this.new C03661(this.$dayStr, this.$dayCount, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03661) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.label;
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                UricAcidRepository uricAcidRepository = UricAcidViewModel.this.getUricAcidRepository();
                String str = this.$dayStr;
                String userName = UserInfoUtil.getUserName();
                Intrinsics.checkNotNullExpressionValue(userName, "getUserName(...)");
                this.label = 1;
                obj = uricAcidRepository.getSinceDayData(str, userName, this);
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
            MutableSharedFlow mutableSharedFlow = UricAcidViewModel.this._uricAcidPackedDataFlow;
            int i3 = this.$dayCount;
            this.label = 2;
            if (mutableSharedFlow.emit(new HealthPackedData(i3, (List) obj), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final void getPackedDayData(String dayStr, int dayCount) {
        Intrinsics.checkNotNullParameter(dayStr, "dayStr");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C03661(dayStr, dayCount, null), 3, null);
    }
}
