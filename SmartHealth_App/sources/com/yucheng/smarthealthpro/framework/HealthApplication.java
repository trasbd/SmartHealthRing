package com.yucheng.smarthealthpro.framework;

import android.app.Application;
import com.yucheng.smarthealthpro.framework.catchexception.MyUncaughtExceptionHandler;
import com.yucheng.smarthealthpro.framework.util.Constants;
import com.yucheng.smarthealthpro.framework.util.MusicHandler;
import com.yucheng.smarthealthpro.framework.util.SubObserver;

/* loaded from: classes4.dex */
public class HealthApplication extends Application {
    public static final int IntervalTime = 30;
    private static final String TAG = "HealthApplication";
    private static HealthApplication instance = null;
    public static boolean isBackground = false;
    public static boolean isSend = false;
    public static boolean isSyncing = false;
    public static boolean isUpgradeing = false;
    public static boolean refreshWeather = true;
    private int mFinalCount;

    public static HealthApplication getInstance() {
        return instance;
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        instance = this;
        SubObserver.getInstance();
        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        if (getCacheDir() != null) {
            Constants.avatarPath = getCacheDir().getAbsolutePath();
        } else if (getExternalCacheDir() != null) {
            Constants.avatarPath = getExternalCacheDir().getAbsolutePath();
        } else if (getFilesDir() != null) {
            Constants.avatarPath = getFilesDir().getAbsolutePath();
        }
    }

    public void musicCon(int i2) {
        if (i2 == 0) {
            MusicHandler.getInstance(instance).stop();
            return;
        }
        if (i2 == 1) {
            MusicHandler.getInstance(instance).play();
            return;
        }
        if (i2 == 2) {
            MusicHandler.getInstance(instance).previous();
            return;
        }
        if (i2 == 3) {
            MusicHandler.getInstance(instance).next();
        } else if (i2 == 4) {
            MusicHandler.getInstance(instance).raise();
        } else if (i2 == 5) {
            MusicHandler.getInstance(instance).lower();
        }
    }
}
