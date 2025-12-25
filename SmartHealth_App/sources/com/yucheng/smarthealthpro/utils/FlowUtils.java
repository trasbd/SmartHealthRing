package com.yucheng.smarthealthpro.utils;

import androidx.exifinterface.media.ExifInterface;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.RepeatOnLifecycleKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;

/* compiled from: FlowUtils.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\rB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J0\u0010\u0004\u001a\u00020\u0005\"\u0004\b\u0000\u0010\u00062\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00060\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00060\f¨\u0006\u000e"}, d2 = {"Lcom/yucheng/smarthealthpro/utils/FlowUtils;", "", "<init>", "()V", "collectFlow", "", ExifInterface.GPS_DIRECTION_TRUE, "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "flow", "Lkotlinx/coroutines/flow/Flow;", "collector", "Lcom/yucheng/smarthealthpro/utils/FlowUtils$FlowCollector;", "FlowCollector", "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class FlowUtils {
    public static final FlowUtils INSTANCE = new FlowUtils();

    /* compiled from: FlowUtils.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\u0015\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/yucheng/smarthealthpro/utils/FlowUtils$FlowCollector;", ExifInterface.GPS_DIRECTION_TRUE, "", "collect", "", "data", "(Ljava/lang/Object;)V", "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public interface FlowCollector<T> {
        void collect(T data);
    }

    private FlowUtils() {
    }

    /* compiled from: FlowUtils.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.yucheng.smarthealthpro.utils.FlowUtils$collectFlow$1", f = "FlowUtils.kt", i = {}, l = {26}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.yucheng.smarthealthpro.utils.FlowUtils$collectFlow$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ FlowCollector<T> $collector;
        final /* synthetic */ Flow<T> $flow;
        final /* synthetic */ LifecycleOwner $lifecycleOwner;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass1(LifecycleOwner lifecycleOwner, Flow<? extends T> flow, FlowCollector<T> flowCollector, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$lifecycleOwner = lifecycleOwner;
            this.$flow = flow;
            this.$collector = flowCollector;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$lifecycleOwner, this.$flow, this.$collector, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* compiled from: FlowUtils.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
        @DebugMetadata(c = "com.yucheng.smarthealthpro.utils.FlowUtils$collectFlow$1$1", f = "FlowUtils.kt", i = {}, l = {27}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.yucheng.smarthealthpro.utils.FlowUtils$collectFlow$1$1, reason: invalid class name and collision with other inner class name */
        static final class C01341 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<T> $collector;
            final /* synthetic */ Flow<T> $flow;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C01341(Flow<? extends T> flow, FlowCollector<T> flowCollector, Continuation<? super C01341> continuation) {
                super(2, continuation);
                this.$flow = flow;
                this.$collector = flowCollector;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C01341(this.$flow, this.$collector, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C01341) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i2 = this.label;
                if (i2 == 0) {
                    ResultKt.throwOnFailure(obj);
                    Flow<T> flow = this.$flow;
                    final FlowCollector<T> flowCollector = this.$collector;
                    this.label = 1;
                    if (flow.collect(new kotlinx.coroutines.flow.FlowCollector() { // from class: com.yucheng.smarthealthpro.utils.FlowUtils.collectFlow.1.1.1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public final Object emit(T t, Continuation<? super Unit> continuation) {
                            flowCollector.collect(t);
                            return Unit.INSTANCE;
                        }
                    }, this) == coroutine_suspended) {
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

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.label;
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(this.$lifecycleOwner, Lifecycle.State.STARTED, new C01341(this.$flow, this.$collector, null), this) == coroutine_suspended) {
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

    public final <T> void collectFlow(LifecycleOwner lifecycleOwner, Flow<? extends T> flow, FlowCollector<T> collector) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        Intrinsics.checkNotNullParameter(flow, "flow");
        Intrinsics.checkNotNullParameter(collector, "collector");
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(lifecycleOwner), null, null, new AnonymousClass1(lifecycleOwner, flow, collector, null), 3, null);
    }
}
