package com.yucheng.smarthealthpro.service;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.PowerManager;
import android.os.Process;
import android.telephony.TelephonyManager;
import android.util.Log;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import com.android.internal.telephony.ITelephony;
import com.orhanobut.logger.Logger;
import com.tencent.bugly.crashreport.CrashReport;
import com.yanzhenjie.permission.Permission;
import com.yucheng.smarthealthpro.MainActivity;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.HealthApplication;
import com.yucheng.smarthealthpro.framework.http.HttpUtils;
import com.yucheng.smarthealthpro.framework.util.Constants;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.me.bean.PhoneBean;
import com.yucheng.smarthealthpro.me.service.MPhoneStateListener;
import com.yucheng.smarthealthpro.me.service.MShakeDetector;
import com.yucheng.smarthealthpro.me.service.NotificationMonitor;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.HangUpTelephonyUtil;
import com.yucheng.smarthealthpro.utils.MLog;
import com.yucheng.smarthealthpro.utils.MultiLanguageUtils;
import com.yucheng.ycbtsdk.YCBTClient;
import com.yucheng.ycbtsdk.response.BleDataResponse;
import java.util.HashMap;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes5.dex */
public class MyBleService extends Service {
    public static final String CHANNEL_ID_STRING = "SmartHealth";
    private static final int REGISTERPHONETYPE = 45652;
    private static final String TAG = "MyBleService";
    public static MyBleService myBleService;
    private String lastDate;
    private String logInfo;
    private boolean isRegistSenor = false;
    private boolean isRegistSMS = false;
    private boolean isRegistPhone = false;
    private boolean collectorRunning = false;
    private boolean isPush = false;
    private boolean isDestroy = false;
    private ContentResolver mContentResolver = null;
    private MShakeDetector mSensorEventListener = null;
    Handler handler = new Handler(Looper.getMainLooper()) { // from class: com.yucheng.smarthealthpro.service.MyBleService.1
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            int i2 = msg.what;
            if (i2 == 0) {
                MyBleService.this.isPush = false;
                return;
            }
            if (i2 == 1) {
                MyBleService myBleService2 = MyBleService.this;
                myBleService2.registerSMS(myBleService2);
            } else if (i2 == 2) {
                MyBleService myBleService3 = MyBleService.this;
                myBleService3.registerPhoneStateListener(myBleService3);
            } else {
                if (i2 != 3) {
                    return;
                }
                MyBleService myBleService4 = MyBleService.this;
                myBleService4.registerSenor(myBleService4);
            }
        }
    };
    private Thread thread = new Thread(new Runnable() { // from class: com.yucheng.smarthealthpro.service.MyBleService.2
        @Override // java.lang.Runnable
        public void run() throws InterruptedException {
            while (!MyBleService.this.isDestroy) {
                if (!MyBleService.this.collectorRunning) {
                    MyBleService.this.toggleNotificationListenerService();
                }
                if (!MyBleService.this.isRegistSMS) {
                    MyBleService.this.handler.sendEmptyMessage(1);
                }
                if (!MyBleService.this.isRegistPhone) {
                    MyBleService.this.handler.sendEmptyMessage(2);
                }
                if (Constant.isSmartHealth() && !MyBleService.this.isRegistSenor) {
                    MyBleService.this.handler.sendEmptyMessage(3);
                }
                try {
                    Thread.sleep(5000L);
                } catch (InterruptedException e2) {
                    e2.getStackTrace();
                }
            }
        }
    });
    private MyBinder mBinder = new MyBinder();

    private void callPhone() {
    }

    private void registerScreenEvent(Context context) {
    }

    public static MyBleService getInstance() {
        return myBleService;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            EventBus.getDefault().unregister(this);
            EventBus.getDefault().register(this);
        } catch (Exception e2) {
            CrashReport.postCatchedException(e2);
            e2.printStackTrace();
        }
        myBleService = this;
        this.isDestroy = false;
        MultiLanguageUtils.resetLanguage(this);
        initNotify();
        initListen();
        return 3;
    }

    private void initNotify() {
        ((NotificationManager) getSystemService("notification")).cancel(1);
        ((NotificationManager) getApplication().getSystemService("notification")).createNotificationChannel(new NotificationChannel("SmartHealth", getString(R.string.app_name), 4));
        startForeground(1, new Notification.Builder(getApplicationContext(), "SmartHealth").setSmallIcon(R.mipmap.icon_notification).setContentTitle(getString(R.string.app_name)).setContentText(getString(R.string.lookDetail)).setContentIntent(PendingIntent.getActivity(this, 0, new Intent(getApplicationContext(), (Class<?>) MainActivity.class), AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL)).build());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sleepNotify() {
        ((NotificationManager) getApplication().getSystemService("notification")).createNotificationChannel(new NotificationChannel("SmartHealth", "SmartHealthPro", 4));
        ((NotificationManager) getSystemService("notification")).notify(11, new Notification.Builder(getApplicationContext(), "SmartHealth").setSmallIcon(R.mipmap.icon_notification).setContentTitle(getString(R.string.prompt)).setContentText(getString(R.string.hint_sleep_detect)).setContentIntent(PendingIntent.getActivity(this, 0, new Intent(getApplicationContext(), (Class<?>) MainActivity.class), AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL)).setAutoCancel(true).build());
    }

    private void initListen() {
        Thread thread = this.thread;
        if (thread == null || thread.isAlive()) {
            return;
        }
        this.thread.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void registerPhoneStateListener(Context context) {
        TelephonyManager telephonyManager;
        if (ContextCompat.checkSelfPermission(context, Permission.READ_PHONE_STATE) == 0 && ContextCompat.checkSelfPermission(context, Permission.CALL_PHONE) == 0 && ContextCompat.checkSelfPermission(context, "android.permission.ANSWER_PHONE_CALLS") == 0 && (telephonyManager = (TelephonyManager) context.getSystemService("phone")) != null) {
            telephonyManager.listen(MPhoneStateListener.getInstance(context), 32);
            this.isRegistPhone = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void registerSMS(Context context) {
        if (this.mContentResolver == null) {
            this.mContentResolver = myBleService.getContentResolver();
        }
        if (ActivityCompat.checkSelfPermission(context, Permission.READ_SMS) == 0) {
            this.mContentResolver.registerContentObserver(Uri.parse("content://sms"), true, new SmssReciever(new Handler()));
            this.isRegistSMS = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void registerSenor(final Context context) {
        SensorManager sensorManager = (SensorManager) context.getSystemService("sensor");
        if (sensorManager == null) {
            Logger.d("chong----sensorManager==NULL");
            return;
        }
        Sensor defaultSensor = sensorManager.getDefaultSensor(1);
        if (this.mSensorEventListener == null) {
            MShakeDetector mShakeDetector = new MShakeDetector();
            this.mSensorEventListener = mShakeDetector;
            mShakeDetector.setDetectorListener(new MShakeDetector.DetectorListener() { // from class: com.yucheng.smarthealthpro.service.MyBleService$$ExternalSyntheticLambda0
                @Override // com.yucheng.smarthealthpro.me.service.MShakeDetector.DetectorListener
                public final void onDetectResult(boolean z) {
                    this.f$0.lambda$registerSenor$0(context, z);
                }
            });
        }
        sensorManager.registerListener(this.mSensorEventListener, defaultSensor, 3);
        this.isRegistSenor = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$registerSenor$0(Context context, boolean z) {
        if (HealthApplication.isBackground && !z && YCBTClient.connectState() == 10 && ((PowerManager) context.getSystemService("power")).isInteractive()) {
            YCBTClient.getSleepStatus(new BleDataResponse() { // from class: com.yucheng.smarthealthpro.service.MyBleService.3
                @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                public void onDataResponse(int i2, float v, HashMap hashMap) {
                    if (i2 == 0 && ((Integer) hashMap.get("sleepStatus")).intValue() == 1) {
                        MyBleService.this.sleepNotify();
                    }
                }
            });
        }
    }

    private void unregisterSenor(Context context) {
        if (this.mSensorEventListener == null) {
            return;
        }
        SensorManager sensorManager = (SensorManager) context.getSystemService("sensor");
        if (sensorManager == null) {
            Logger.d("chong----sensorManager==NULL");
            return;
        }
        sensorManager.unregisterListener(this.mSensorEventListener, sensorManager.getDefaultSensor(1));
        this.isRegistSenor = false;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMonitorPush(PhoneBean phoneBean) {
        if (Constant.EventBusTags.MONITOR_RECEIVED_THE_PHONE.equals(phoneBean.getMessage()) || Constant.EventBusTags.BROADCAST_RECEIVED_THE_PHONE.equals(phoneBean.getMessage())) {
            Logger.d("chong----isPush==" + this.isPush);
            if (this.isPush) {
                this.handler.removeMessages(0);
                this.handler.sendEmptyMessageDelayed(0, 3000L);
                return;
            }
            this.isPush = true;
            this.handler.removeMessages(0);
            this.handler.sendEmptyMessageDelayed(0, 3000L);
            String str = phoneBean.name;
            if (str == null || str.length() == 0) {
                str = phoneBean.phone;
            }
            Logger.d("chong--------laidian--name==" + str + "--phone==" + phoneBean.phone);
            pushMessage(0, str, phoneBean.phone);
            return;
        }
        if (Constant.EventBusTags.BROADCAST_ACCEPT_THE_PHONE.equals(phoneBean.getMessage()) || Constant.EventBusTags.MONITOR_ACCEPT_THE_PHONE.equals(phoneBean.getMessage())) {
            YCBTClient.appPushCallState(1, null);
        } else if (Constant.EventBusTags.BROADCAST_HANG_UP_THE_PHONE.equals(phoneBean.getMessage()) || Constant.EventBusTags.MONITOR_HANG_UP_THE_PHONE.equals(phoneBean.getMessage())) {
            YCBTClient.appPushCallState(2, null);
        }
    }

    private void pushMessage(int type, String title, String message) {
        YCBTClient.appSengMessageToDevice(type, title, message, null);
    }

    private void ensureCollectorRunning() throws SecurityException {
        ComponentName componentName = new ComponentName(this, (Class<?>) NotificationMonitor.class);
        List<ActivityManager.RunningServiceInfo> runningServices = ((ActivityManager) getSystemService("activity")).getRunningServices(Integer.MAX_VALUE);
        Logger.d("chong----runningServices==" + runningServices);
        if (runningServices == null) {
            return;
        }
        for (ActivityManager.RunningServiceInfo runningServiceInfo : runningServices) {
            if (runningServiceInfo.service.equals(componentName) && runningServiceInfo.pid == Process.myPid()) {
                toggleNotificationListenerService();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toggleNotificationListenerService() {
        ComponentName componentName = new ComponentName(this, (Class<?>) NotificationMonitor.class);
        PackageManager packageManager = getPackageManager();
        packageManager.setComponentEnabledSetting(componentName, 2, 1);
        packageManager.setComponentEnabledSetting(componentName, 1, 1);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.mBinder;
    }

    public class MyBinder extends Binder {
        public MyBinder() {
        }

        public void reStartService() {
            Log.e(MyBleService.TAG, "reStartService() executed");
        }
    }

    public void onTimeout(int startId, int fgsType) {
        super.onTimeout(startId, fgsType);
        MLog.INSTANCE.d("onTimeout startId: " + startId + "  fgsType: " + fgsType);
        stopSelf();
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        this.isDestroy = true;
        unregisterSenor(this);
        EventBus.getDefault().unregister(this);
        Logger.d("vvvv-------------onDestroy");
        try {
            startForegroundService(new Intent(this, (Class<?>) MyBleService.class));
        } catch (Exception e2) {
            CrashReport.postCatchedException(e2);
            e2.printStackTrace();
        }
    }

    @Override // android.app.Service
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        Logger.d("vvvv-------------onTaskRemoved");
        if (YCBTClient.connectState() == 10) {
            startForegroundService(new Intent(this, (Class<?>) MyBleService.class));
        }
    }

    private class SmssReciever extends ContentObserver {
        public SmssReciever(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            MyBleService.this.sendSMS();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0149 A[DONT_GENERATE] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x014b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized void sendSMS() {
        /*
            Method dump skipped, instructions count: 369
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.service.MyBleService.sendSMS():void");
    }

    public void answerCall() {
        Log.i("chong", " 接电话...................");
        HangUpTelephonyUtil.answerCall(getApplicationContext());
    }

    public void rejectCall() {
        Log.i("chong", " 挂电话...................");
        if (HangUpTelephonyUtil.endCall(getApplicationContext())) {
            Log.i("chong", " 挂电话...................成功");
        } else {
            Log.i("chong", " 挂电话...................失败");
        }
        try {
            ITelephony iTelephonyAsInterface = ITelephony.Stub.asInterface((IBinder) Class.forName("android.os.ServiceManager").getMethod("getService", String.class).invoke(null, "phone"));
            if (iTelephonyAsInterface != null) {
                iTelephonyAsInterface.endCall();
            }
        } catch (Exception e2) {
            CrashReport.postCatchedException(e2);
            e2.printStackTrace();
        }
    }

    private void upLoadLogData() {
        HashMap map = new HashMap();
        map.put("appInfo", getAppInfo());
        map.put("bandInfo", getDeviceInfo());
        map.put("contactWay", "15502595088");
        map.put("functionMessage", "校验失败的手环数据--" + this.logInfo);
        map.put("functionType", "android_log");
        map.put(Constant.SpConstKey.TOKEN, SharedPreferencesUtils.get(myBleService, Constant.SpConstKey.TOKEN, ""));
        HttpUtils.getInstance().upload(this, Constants.feedback, map, "photos", null, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.service.MyBleService.4
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                Logger.d("chong----提交成功--" + result);
            }
        });
    }

    private String getDeviceInfo() {
        return YCBTClient.getBindDeviceName() + "," + YCBTClient.getBindDeviceMac() + "," + YCBTClient.getBindDeviceVersion();
    }

    private String getAppInfo() {
        try {
            return getPackageManager().getPackageInfo(getPackageName(), 0).versionName + "," + ((String) SharedPreferencesUtils.get(myBleService, Constant.SpConstKey.USER_NAME, "")) + "," + Build.MODEL + "--" + Build.VERSION.RELEASE;
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }
}
