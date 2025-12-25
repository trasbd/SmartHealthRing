package com.yucheng.smarthealthpro.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.google.android.gms.location.places.Place;
import com.realsil.sdk.dfu.model.DfuConfig;
import com.yucheng.smarthealthpro.MyApplication;
import com.yucheng.smarthealthpro.data.bean.SaveMotionPattern;
import com.yucheng.smarthealthpro.data.bean.SaveSportRecord;
import com.yucheng.smarthealthpro.data.packed.HealthDayData;
import com.yucheng.smarthealthpro.data.packed.HealthResult;
import com.yucheng.smarthealthpro.database.room.bean.MotionPattern;
import com.yucheng.smarthealthpro.database.room.bean.SportRecord;
import com.yucheng.smarthealthpro.login.normal.util.UserInfoUtil;
import com.yucheng.smarthealthpro.repository.MotionPatternRepository;
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

/* compiled from: SportRunningViewModel.kt */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010 \u001a\u00020!J\u000e\u0010\"\u001a\u00020!2\u0006\u0010#\u001a\u00020$J\u000e\u0010%\u001a\u00020!2\u0006\u0010&\u001a\u00020'J\u000e\u0010(\u001a\u00020!2\u0006\u0010(\u001a\u00020)R\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u001b\u0010\n\u001a\u00020\u000b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\t\u001a\u0004\b\f\u0010\rR\u001a\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\u00148F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u00180\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u00180\u00148F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0016R\u001a\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u001d0\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u001d0\u00148F¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u0016¨\u0006*"}, d2 = {"Lcom/yucheng/smarthealthpro/viewmodel/SportRunningViewModel;", "Landroidx/lifecycle/ViewModel;", "<init>", "()V", "sportRecordRepository", "Lcom/yucheng/smarthealthpro/repository/SportRecordRepository;", "getSportRecordRepository", "()Lcom/yucheng/smarthealthpro/repository/SportRecordRepository;", "sportRecordRepository$delegate", "Lkotlin/Lazy;", "motionPatternRepository", "Lcom/yucheng/smarthealthpro/repository/MotionPatternRepository;", "getMotionPatternRepository", "()Lcom/yucheng/smarthealthpro/repository/MotionPatternRepository;", "motionPatternRepository$delegate", "_sportRecordDataFlow", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lcom/yucheng/smarthealthpro/data/packed/HealthDayData;", "Lcom/yucheng/smarthealthpro/database/room/bean/SportRecord;", "sportRecordDataFlow", "Lkotlinx/coroutines/flow/SharedFlow;", "getSportRecordDataFlow", "()Lkotlinx/coroutines/flow/SharedFlow;", "_deleteResultFlow", "Lkotlin/Result;", "", "deleteResultFlow", "getDeleteResultFlow", "_saveResultFlow", "Lcom/yucheng/smarthealthpro/data/packed/HealthResult;", "saveResultFlow", "getSaveResultFlow", "getAllData", "", "saveSportRecord", "saveRecord", "Lcom/yucheng/smarthealthpro/data/bean/SaveSportRecord;", "deleteSportRecord", "startTimestamp", "", "saveMotionPattern", "Lcom/yucheng/smarthealthpro/data/bean/SaveMotionPattern;", "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class SportRunningViewModel extends ViewModel {

    /* renamed from: sportRecordRepository$delegate, reason: from kotlin metadata */
    private final Lazy sportRecordRepository = LazyKt.lazy(new Function0() { // from class: com.yucheng.smarthealthpro.viewmodel.SportRunningViewModel$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return SportRunningViewModel.sportRecordRepository_delegate$lambda$0();
        }
    });

    /* renamed from: motionPatternRepository$delegate, reason: from kotlin metadata */
    private final Lazy motionPatternRepository = LazyKt.lazy(new Function0() { // from class: com.yucheng.smarthealthpro.viewmodel.SportRunningViewModel$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return SportRunningViewModel.motionPatternRepository_delegate$lambda$1();
        }
    });
    private final MutableSharedFlow<HealthDayData<SportRecord>> _sportRecordDataFlow = SharedFlowKt.MutableSharedFlow$default(0, 0, null, 7, null);
    private final MutableSharedFlow<Result<Boolean>> _deleteResultFlow = SharedFlowKt.MutableSharedFlow$default(0, 0, null, 7, null);
    private final MutableSharedFlow<HealthResult<Boolean>> _saveResultFlow = SharedFlowKt.MutableSharedFlow$default(0, 0, null, 7, null);

    /* JADX INFO: Access modifiers changed from: private */
    public final SportRecordRepository getSportRecordRepository() {
        return (SportRecordRepository) this.sportRecordRepository.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final SportRecordRepository sportRecordRepository_delegate$lambda$0() {
        return new SportRecordRepository(MyApplication.sInstance.getAppDatabase().sportRecordDao());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MotionPatternRepository getMotionPatternRepository() {
        return (MotionPatternRepository) this.motionPatternRepository.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MotionPatternRepository motionPatternRepository_delegate$lambda$1() {
        return new MotionPatternRepository(MyApplication.sInstance.getAppDatabase().motionPatternDao());
    }

    public final SharedFlow<HealthDayData<SportRecord>> getSportRecordDataFlow() {
        return FlowKt.asSharedFlow(this._sportRecordDataFlow);
    }

    public final SharedFlow<Result<Boolean>> getDeleteResultFlow() {
        return FlowKt.asSharedFlow(this._deleteResultFlow);
    }

    public final SharedFlow<HealthResult<Boolean>> getSaveResultFlow() {
        return FlowKt.asSharedFlow(this._saveResultFlow);
    }

    /* compiled from: SportRunningViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.viewmodel.SportRunningViewModel$getAllData$1", f = "SportRunningViewModel.kt", i = {}, l = {52, 53}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.yucheng.smarthealthpro.viewmodel.SportRunningViewModel$getAllData$1, reason: invalid class name and case insensitive filesystem */
    static final class C03601 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C03601(Continuation<? super C03601> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return SportRunningViewModel.this.new C03601(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03601) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.label;
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                SportRecordRepository sportRecordRepository = SportRunningViewModel.this.getSportRecordRepository();
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
            if (SportRunningViewModel.this._sportRecordDataFlow.emit(new HealthDayData("", null, list, 2, null), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final void getAllData() {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C03601(null), 3, null);
    }

    /* compiled from: SportRunningViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.viewmodel.SportRunningViewModel$saveSportRecord$1", f = "SportRunningViewModel.kt", i = {}, l = {83, 84}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.yucheng.smarthealthpro.viewmodel.SportRunningViewModel$saveSportRecord$1, reason: invalid class name and case insensitive filesystem */
    static final class C03621 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ SaveSportRecord $saveRecord;
        int label;
        final /* synthetic */ SportRunningViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03621(SaveSportRecord saveSportRecord, SportRunningViewModel sportRunningViewModel, Continuation<? super C03621> continuation) {
            super(2, continuation);
            this.$saveRecord = saveSportRecord;
            this.this$0 = sportRunningViewModel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C03621(this.$saveRecord, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03621) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objInsert;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.label;
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                SportRecord sportRecord = new SportRecord(null, 0, this.$saveRecord.getType(), this.$saveRecord.getBeginDate(), this.$saveRecord.getTimeYearToDay(), this.$saveRecord.getTotalSteps(), this.$saveRecord.getTotalDistance(), this.$saveRecord.getLastDistance(), this.$saveRecord.getTotalCalories(), this.$saveRecord.getLastCalories(), this.$saveRecord.getMinkm(), this.$saveRecord.getHeartRate(), this.$saveRecord.getRunDuration(), this.$saveRecord.getKmh(), this.$saveRecord.getStartPoint(), this.$saveRecord.getEndPoint(), this.$saveRecord.getPathLinePoints(), this.$saveRecord.getUserId(), this.$saveRecord.getDeviceType(), this.$saveRecord.getDeviceMacAddress(), null, this.$saveRecord.getIsUploaded(), false, 5242883, null);
                this.label = 1;
                objInsert = this.this$0.getSportRecordRepository().insert(sportRecord, this);
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
            boolean zBooleanValue = ((Boolean) objInsert).booleanValue();
            this.label = 2;
            if (this.this$0._saveResultFlow.emit(new HealthResult(Boxing.boxBoolean(zBooleanValue)), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final void saveSportRecord(SaveSportRecord saveRecord) {
        Intrinsics.checkNotNullParameter(saveRecord, "saveRecord");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C03621(saveRecord, this, null), 3, null);
    }

    /* compiled from: SportRunningViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.viewmodel.SportRunningViewModel$deleteSportRecord$1", f = "SportRunningViewModel.kt", i = {}, l = {93, Place.TYPE_UNIVERSITY}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.yucheng.smarthealthpro.viewmodel.SportRunningViewModel$deleteSportRecord$1, reason: invalid class name */
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
            return SportRunningViewModel.this.new AnonymousClass1(this.$startTimestamp, continuation);
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
                SportRecordRepository sportRecordRepository = SportRunningViewModel.this.getSportRecordRepository();
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
            MutableSharedFlow mutableSharedFlow = SportRunningViewModel.this._deleteResultFlow;
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

    /* compiled from: SportRunningViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.viewmodel.SportRunningViewModel$saveMotionPattern$1", f = "SportRunningViewModel.kt", i = {}, l = {DfuConfig.MAX_POWER_LEVER}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.yucheng.smarthealthpro.viewmodel.SportRunningViewModel$saveMotionPattern$1, reason: invalid class name and case insensitive filesystem */
    static final class C03611 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ SaveMotionPattern $saveMotionPattern;
        int label;
        final /* synthetic */ SportRunningViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03611(SaveMotionPattern saveMotionPattern, SportRunningViewModel sportRunningViewModel, Continuation<? super C03611> continuation) {
            super(2, continuation);
            this.$saveMotionPattern = saveMotionPattern;
            this.this$0 = sportRunningViewModel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C03611(this.$saveMotionPattern, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03611) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objInsert;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.label;
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                MotionPattern motionPattern = new MotionPattern(null, 0, this.$saveMotionPattern.getStartTimestamp(), 0L, null, 0, 0, 0, 0, 0, this.$saveMotionPattern.getSportHeartRate(), 0L, 0, 0, this.$saveMotionPattern.getUserId(), this.$saveMotionPattern.getDeviceType(), this.$saveMotionPattern.getDeviceMacAddress(), null, false, false, 932859, null);
                this.label = 1;
                objInsert = this.this$0.getMotionPatternRepository().insert(motionPattern, this);
                if (objInsert == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                objInsert = obj;
            }
            ((Boolean) objInsert).booleanValue();
            return Unit.INSTANCE;
        }
    }

    public final void saveMotionPattern(SaveMotionPattern saveMotionPattern) {
        Intrinsics.checkNotNullParameter(saveMotionPattern, "saveMotionPattern");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C03611(saveMotionPattern, this, null), 3, null);
    }
}
