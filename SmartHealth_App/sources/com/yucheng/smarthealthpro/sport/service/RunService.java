package com.yucheng.smarthealthpro.sport.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import androidx.core.app.ActivityCompat;
import com.yucheng.smarthealthpro.sport.utils.LocationAMapUtils;
import com.yucheng.ycbtsdk.AITools;

@Deprecated
/* loaded from: classes5.dex */
public class RunService extends Service {
    private Intent intent = new Intent("com.health.RunService.Rev");
    private MBroadcastReceiver mBroadcastReceiver = new MBroadcastReceiver();
    boolean isRun = false;

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        return 1;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void refRev() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.health.RunService.Send");
        ActivityCompat.registerReceiver(this, this.mBroadcastReceiver, intentFilter, 2);
    }

    public class MBroadcastReceiver extends BroadcastReceiver {
        public MBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            int intExtra = intent.getIntExtra("run", -1);
            if (intExtra == 1) {
                RunService.this.beginRun();
                return;
            }
            if (intExtra == 2) {
                RunService.this.beginTimeRun();
                return;
            }
            if (intExtra == 3) {
                RunService.this.stoprRun();
            } else if (intExtra == 4) {
                RunService.this.pauseRun();
            } else {
                if (intExtra != 5) {
                    return;
                }
                RunService.this.continueRun();
            }
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        refRev();
    }

    public void beginRun() {
        startLocal();
        this.isRun = true;
        new Thread(new Runnable() { // from class: com.yucheng.smarthealthpro.sport.service.RunService.1
            @Override // java.lang.Runnable
            public void run() throws InterruptedException {
                while (RunService.this.isRun) {
                    RunService.this.intent.putExtra("type", 2);
                    RunService runService = RunService.this;
                    runService.sendBroadcast(runService.intent);
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void beginTimeRun() {
        this.isRun = true;
        new Thread(new Runnable() { // from class: com.yucheng.smarthealthpro.sport.service.RunService.2
            @Override // java.lang.Runnable
            public void run() throws InterruptedException {
                while (RunService.this.isRun) {
                    RunService.this.intent.putExtra("type", 2);
                    RunService runService = RunService.this;
                    runService.sendBroadcast(runService.intent);
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public synchronized void stoprRun() {
        this.isRun = false;
        stopLocal();
    }

    public void pauseRun() {
        this.isRun = false;
        LocationAMapUtils.getInstance().stopLocalService();
    }

    public void continueRun() {
        this.isRun = true;
        new Thread(new Runnable() { // from class: com.yucheng.smarthealthpro.sport.service.RunService.3
            @Override // java.lang.Runnable
            public void run() throws InterruptedException {
                while (RunService.this.isRun) {
                    RunService.this.intent.putExtra("type", 2);
                    RunService runService = RunService.this;
                    runService.sendBroadcast(runService.intent);
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }).start();
        LocationAMapUtils.getInstance().startLocalService();
    }

    public synchronized void startLocal() {
        LocationAMapUtils.getInstance().startLocalService();
        new Thread(new Runnable() { // from class: com.yucheng.smarthealthpro.sport.service.RunService$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                AITools.getInstance().initGps();
            }
        }).start();
    }

    public synchronized void stopLocal() {
        LocationAMapUtils.getInstance().stopLocalService();
        new Thread(new Runnable() { // from class: com.yucheng.smarthealthpro.sport.service.RunService$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                AITools.getInstance().freeGps();
            }
        }).start();
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.mBroadcastReceiver);
    }
}
