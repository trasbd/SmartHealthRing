package com.yucheng.smarthealthpro.repository;

import com.yucheng.smarthealthpro.MyApplication;
import com.yucheng.smarthealthpro.framework.http.HttpUtils;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;

/* compiled from: DataUploadRepository.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0086@¢\u0006\u0002\u0010\t¨\u0006\n"}, d2 = {"Lcom/yucheng/smarthealthpro/repository/DataUploadRepository;", "", "<init>", "()V", "uploadHealthData", "", "url", "", "data", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class DataUploadRepository {
    public final Object uploadHealthData(String str, String str2, Continuation<? super Boolean> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        final CancellableContinuationImpl cancellableContinuationImpl2 = cancellableContinuationImpl;
        HttpUtils.getInstance().postJsonMsgAsynHttp(MyApplication.getInstance(), str, str2, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.repository.DataUploadRepository$uploadHealthData$2$1
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public final void onSuccess(String str3) {
                if (str3 != null) {
                    cancellableContinuationImpl2.resume((CancellableContinuation<Boolean>) true, (Function1<? super Throwable, Unit>) null);
                } else {
                    cancellableContinuationImpl2.resume((CancellableContinuation<Boolean>) false, (Function1<? super Throwable, Unit>) null);
                }
            }
        });
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }
}
