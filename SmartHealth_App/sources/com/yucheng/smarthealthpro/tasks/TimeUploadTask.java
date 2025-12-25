package com.yucheng.smarthealthpro.tasks;

import android.content.Context;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.ListenableWorker;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.orhanobut.logger.Logger;
import com.yucheng.smarthealthpro.framework.HealthApplication;
import com.yucheng.smarthealthpro.utils.DataSyncEvent;
import com.yucheng.smarthealthpro.utils.DataSyncUtils;
import com.yucheng.smarthealthpro.utils.SyncState;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TimeUploadTask.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\b\u001a\u00020\tH\u0016J\b\u0010\n\u001a\u00020\u000bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/yucheng/smarthealthpro/tasks/TimeUploadTask;", "Landroidx/work/Worker;", "appContext", "Landroid/content/Context;", "workerParams", "Landroidx/work/WorkerParameters;", "<init>", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "doWork", "Landroidx/work/ListenableWorker$Result;", "onStopped", "", "Companion", "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class TimeUploadTask extends Worker {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = "sync";
    private static boolean isSportRunning;
    private final Context appContext;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TimeUploadTask(Context appContext, WorkerParameters workerParams) {
        super(appContext, workerParams);
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        Intrinsics.checkNotNullParameter(workerParams, "workerParams");
        this.appContext = appContext;
    }

    /* compiled from: TimeUploadTask.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u000f"}, d2 = {"Lcom/yucheng/smarthealthpro/tasks/TimeUploadTask$Companion;", "", "<init>", "()V", "TAG", "", "isSportRunning", "", "()Z", "setSportRunning", "(Z)V", "request", "", "context", "Landroid/content/Context;", "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean isSportRunning() {
            return TimeUploadTask.isSportRunning;
        }

        public final void setSportRunning(boolean z) {
            TimeUploadTask.isSportRunning = z;
        }

        public final void request(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            WorkManager.INSTANCE.getInstance(context).enqueueUniquePeriodicWork(TimeUploadTask.TAG, ExistingPeriodicWorkPolicy.KEEP, new PeriodicWorkRequest.Builder((Class<? extends ListenableWorker>) TimeUploadTask.class, 30L, TimeUnit.MINUTES).addTag(TimeUploadTask.TAG).build());
        }
    }

    @Override // androidx.work.Worker
    public ListenableWorker.Result doWork() {
        boolean z = isSportRunning;
        Logger.d("定时同步历史数据" + (!z) + "  " + HealthApplication.isBackground + "  " + isSportRunning, new Object[0]);
        if (!z) {
            DataSyncUtils.INSTANCE.getInstance(this.appContext).startDataSync(new DataSyncEvent() { // from class: com.yucheng.smarthealthpro.tasks.TimeUploadTask.doWork.1
                @Override // com.yucheng.smarthealthpro.utils.DataSyncEvent
                public void callback(SyncState state) {
                    Intrinsics.checkNotNullParameter(state, "state");
                    Logger.w(String.valueOf(state), new Object[0]);
                }
            });
        }
        ListenableWorker.Result resultSuccess = ListenableWorker.Result.success();
        Intrinsics.checkNotNullExpressionValue(resultSuccess, "success(...)");
        return resultSuccess;
    }

    @Override // androidx.work.ListenableWorker
    public void onStopped() {
        DataSyncUtils.INSTANCE.release();
        super.onStopped();
    }
}
