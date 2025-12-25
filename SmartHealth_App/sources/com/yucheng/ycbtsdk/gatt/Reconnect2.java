package com.yucheng.ycbtsdk.gatt;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import com.yucheng.ycbtsdk.utils.YCBTLog;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class Reconnect2 {
    private static Reconnect2 gReconnect;
    public int count;
    public String currentMac;
    public String currentName;
    boolean hasFound;
    private Context mContext;
    public StateListener stateListener;
    private boolean isStartThread = false;
    private boolean isReconnect = false;
    private int connectingTimeOut = 0;
    private boolean isAutoOta = false;
    private String deviceType = "";
    Map<String, Boolean> macUpgradeMap = new HashMap();
    public int maxCount = 10;
    public boolean isScan = true;
    Thread thread = new Thread(new Runnable() { // from class: com.yucheng.ycbtsdk.gatt.Reconnect2.1
        /* JADX WARN: Removed duplicated region for block: B:11:0x0032  */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void run() throws java.lang.InterruptedException {
            /*
                Method dump skipped, instructions count: 323
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.yucheng.ycbtsdk.gatt.Reconnect2.AnonymousClass1.run():void");
        }
    });
    private BroadcastReceiver mReceiver = new BroadcastReceiver() { // from class: com.yucheng.ycbtsdk.gatt.Reconnect2.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            action.hashCode();
            if (action.equals("android.bluetooth.adapter.action.STATE_CHANGED")) {
                switch (intent.getIntExtra("android.bluetooth.adapter.extra.STATE", 0)) {
                    case 10:
                        YCBTLog.e("蓝牙已关闭");
                        break;
                    case 11:
                        YCBTLog.e("蓝牙正在开启");
                        break;
                    case 12:
                        YCBTLog.e("蓝牙已开启，开始连接");
                        Reconnect2.getInstance().wakeUp();
                        break;
                    case 13:
                        YCBTLog.e("蓝牙正在关闭");
                        break;
                }
            }
        }
    };
    private final Object lock = new Object();

    public interface StateListener {
        void onState(int i2);
    }

    static /* synthetic */ int access$208(Reconnect2 reconnect2) {
        int i2 = reconnect2.connectingTimeOut;
        reconnect2.connectingTimeOut = i2 + 1;
        return i2;
    }

    public static Reconnect2 getInstance() {
        if (gReconnect == null) {
            synchronized (Reconnect2.class) {
                if (gReconnect == null) {
                    gReconnect = new Reconnect2();
                }
            }
        }
        return gReconnect;
    }

    private IntentFilter makeFilter() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        return intentFilter;
    }

    public void checkThread() {
        this.thread.isAlive();
    }

    public void init(Context context, boolean z) {
        BroadcastReceiver broadcastReceiver;
        Context context2 = this.mContext;
        if (context2 != null && (broadcastReceiver = this.mReceiver) != null) {
            context2.unregisterReceiver(broadcastReceiver);
        }
        this.mContext = context;
        if (Build.VERSION.SDK_INT >= 33) {
            context.registerReceiver(this.mReceiver, makeFilter(), 2);
        } else {
            context.registerReceiver(this.mReceiver, makeFilter());
        }
        this.isReconnect = z;
        if (this.isStartThread) {
            return;
        }
        this.isStartThread = true;
        this.thread.start();
    }

    public void resetCurrentMac() {
        this.currentMac = null;
        this.currentName = null;
        this.count = 0;
    }

    public void setAutoOta(boolean z, String str) {
        this.isAutoOta = z;
        this.deviceType = str;
    }

    public void setMacUpgradeMap(Map<String, Boolean> map) {
        this.macUpgradeMap = map;
    }

    public void setReconnect(boolean z) {
        this.isReconnect = z;
    }

    public void setReconnectAndDisconnect(boolean z) {
    }

    public void setStateListener(StateListener stateListener) {
        this.stateListener = stateListener;
    }

    public void wakeUp() {
        synchronized (this.thread) {
            this.thread.notify();
        }
    }
}
