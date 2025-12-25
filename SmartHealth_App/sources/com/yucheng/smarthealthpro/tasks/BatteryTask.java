package com.yucheng.smarthealthpro.tasks;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.ListenableWorker;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.orhanobut.logger.Logger;
import com.yucheng.smarthealthpro.MainActivity;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.HealthApplication;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.ycbtsdk.YCBTClient;
import com.yucheng.ycbtsdk.response.BleDataResponse;
import com.yucheng.ycbtsdk.utils.YCBTLog;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BatteryTask.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\b\u001a\u00020\tH\u0016J\b\u0010\n\u001a\u00020\u000bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/yucheng/smarthealthpro/tasks/BatteryTask;", "Landroidx/work/Worker;", "context", "Landroid/content/Context;", "workerParams", "Landroidx/work/WorkerParameters;", "<init>", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "doWork", "Landroidx/work/ListenableWorker$Result;", "onStopped", "", "Companion", "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class BatteryTask extends Worker {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = "battery";
    private static boolean isSportRunning;
    private final Context context;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BatteryTask(Context context, WorkerParameters workerParams) {
        super(context, workerParams);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(workerParams, "workerParams");
        this.context = context;
    }

    /* compiled from: BatteryTask.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u000f\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u0010"}, d2 = {"Lcom/yucheng/smarthealthpro/tasks/BatteryTask$Companion;", "", "<init>", "()V", "TAG", "", "isSportRunning", "", "()Z", "setSportRunning", "(Z)V", "request", "", "context", "Landroid/content/Context;", "sendLowBatteryMsg", "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean isSportRunning() {
            return BatteryTask.isSportRunning;
        }

        public final void setSportRunning(boolean z) {
            BatteryTask.isSportRunning = z;
        }

        public final void request(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            WorkManager.INSTANCE.getInstance(context).enqueueUniquePeriodicWork(BatteryTask.TAG, ExistingPeriodicWorkPolicy.KEEP, new PeriodicWorkRequest.Builder((Class<? extends ListenableWorker>) BatteryTask.class, 3L, TimeUnit.MINUTES).addTag(BatteryTask.TAG).build());
        }

        public final void sendLowBatteryMsg(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            try {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "SmartHealth");
                builder.setSmallIcon(R.mipmap.icon_notification);
                builder.setContentTitle(context.getString(R.string.app_name));
                builder.setContentText(context.getString(R.string.update_batty_low));
                builder.setShowWhen(true).setVisibility(1);
                builder.setPriority(1);
                builder.setContentIntent(PendingIntent.getActivity(context, 0, new Intent(context, (Class<?>) MainActivity.class), 201326592));
                builder.setAutoCancel(true);
                Object systemService = context.getSystemService("notification");
                Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
                ((NotificationManager) systemService).notify(2, builder.build());
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    @Override // androidx.work.Worker
    public ListenableWorker.Result doWork() {
        Logger.d("触发定时获取电量 SmartHealth", new Object[0]);
        if (YCBTClient.connectState() == 10) {
            YCBTClient.getDeviceInfo(new BleDataResponse() { // from class: com.yucheng.smarthealthpro.tasks.BatteryTask$$ExternalSyntheticLambda0
                @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                public final void onDataResponse(int i2, float f2, HashMap map) {
                    BatteryTask.doWork$lambda$1(this.f$0, i2, f2, map);
                }
            });
        }
        ListenableWorker.Result resultSuccess = ListenableWorker.Result.success();
        Intrinsics.checkNotNullExpressionValue(resultSuccess, "success(...)");
        return resultSuccess;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void doWork$lambda$1(BatteryTask batteryTask, int i2, float f2, HashMap map) {
        YCBTLog.d("触发定时获取电量完成 " + map);
        if (map == null || i2 != 0) {
            return;
        }
        YCBTLog.d("resultMap: " + map);
        Object obj = map.get("data");
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type java.util.HashMap<kotlin.Any, kotlin.Any>");
        Object obj2 = ((HashMap) obj).get("deviceBatteryValue");
        Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.Int");
        if (((Integer) obj2).intValue() <= 15) {
            Object obj3 = SharedPreferencesUtils.get(batteryTask.context, Constant.IS_SEND_MESSAGE, true);
            Intrinsics.checkNotNull(obj3, "null cannot be cast to non-null type kotlin.Boolean");
            if (((Boolean) obj3).booleanValue() && HealthApplication.isBackground) {
                INSTANCE.sendLowBatteryMsg(batteryTask.context);
                SharedPreferencesUtils.put(batteryTask.context, Constant.IS_SEND_MESSAGE, false);
                return;
            }
            return;
        }
        SharedPreferencesUtils.put(batteryTask.context, Constant.IS_SEND_MESSAGE, true);
    }

    @Override // androidx.work.ListenableWorker
    public void onStopped() {
        super.onStopped();
    }
}
