package com.yucheng.ycbtsdk.gatt;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/* loaded from: classes5.dex */
public class MyBleService extends Service {
    private static final String TAG = "MyBleService";
    public static MyBleService myBleService;
    private MyBinder mBinder = new MyBinder();

    public class MyBinder extends Binder {
        public MyBinder() {
        }

        public void reStartService() {
            Log.e(MyBleService.TAG, "reStartService() executed");
        }
    }

    public static MyBleService getInstance() {
        return myBleService;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.mBinder;
    }

    @Override // android.app.Service
    public void onCreate() {
        System.out.println("vvvv-------------onCreate");
        myBleService = this;
        super.onCreate();
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        System.out.println("vvvv-------------onDestroy");
        startForegroundService(new Intent(this, (Class<?>) MyBleService.class));
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i2, int i3) {
        return 3;
    }

    @Override // android.app.Service
    public void onTaskRemoved(Intent intent) {
        super.onTaskRemoved(intent);
        System.out.println("vvvv-------------onTaskRemoved");
        startForegroundService(new Intent(this, (Class<?>) MyBleService.class));
    }
}
