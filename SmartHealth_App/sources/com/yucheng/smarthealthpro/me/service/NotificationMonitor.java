package com.yucheng.smarthealthpro.me.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ResolveInfo;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.provider.Telephony;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import androidx.core.app.ActivityCompat;
import com.tencent.bugly.crashreport.CrashReport;
import com.yucheng.ycbtsdk.YCBTClient;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes5.dex */
public class NotificationMonitor extends NotificationListenerService {
    private static final String ACTION_NLS_CONTROL = "com.zhuoting.health.NLSCONTROL";
    private static final int EVENT_UPDATE_CURRENT_NOS = 0;
    public static final String TAG = "NotificationMonitor";
    private static List<StatusBarNotification[]> mCurrentNotifications = new ArrayList();
    private static int mCurrentNotificationsCounts = 0;
    private String defaultSmsPackageName;
    private ArrayList<String> dialerApps;
    private String mLastContent;
    private String mLastPackage;
    private long mLastPostTime;
    private char[] mPushMessageData;
    private String mTitle;
    private CancelNotificationReceiver mReceiver = new CancelNotificationReceiver();
    private long mPostInterval = 1000;
    private Handler mMonitorHandler = new Handler(Looper.getMainLooper()) { // from class: com.yucheng.smarthealthpro.me.service.NotificationMonitor.1
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            if (msg.what != 0) {
                return;
            }
            NotificationMonitor.this.updateCurrentNotifications();
        }
    };
    private boolean connected = false;
    Timer timer = new Timer();
    List<SendToDeviceMsg> sendToDeviceMsgs = new ArrayList();

    private class CancelNotificationReceiver extends BroadcastReceiver {
        private CancelNotificationReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null || intent.getAction() == null || !intent.getAction().equals(NotificationMonitor.ACTION_NLS_CONTROL)) {
                return;
            }
            String stringExtra = intent.getStringExtra("command");
            if (TextUtils.equals(stringExtra, "cancel_last")) {
                if (NotificationMonitor.mCurrentNotifications == null || NotificationMonitor.mCurrentNotificationsCounts < 1) {
                    return;
                }
                StatusBarNotification statusBarNotification = NotificationMonitor.getCurrentNotifications()[NotificationMonitor.mCurrentNotificationsCounts - 1];
                NotificationMonitor.this.cancelNotification(statusBarNotification.getPackageName(), statusBarNotification.getTag(), statusBarNotification.getId());
                return;
            }
            if (TextUtils.equals(stringExtra, "cancel_all")) {
                NotificationMonitor.this.cancelAllNotifications();
            }
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_NLS_CONTROL);
        ActivityCompat.registerReceiver(this, this.mReceiver, intentFilter, 2);
        Handler handler = this.mMonitorHandler;
        handler.sendMessage(handler.obtainMessage(0));
        this.dialerApps = getPackagesOfDialerApps(getApplicationContext());
        this.defaultSmsPackageName = Telephony.Sms.getDefaultSmsPackage(getApplicationContext());
        this.timer.schedule(new TimerTask() { // from class: com.yucheng.smarthealthpro.me.service.NotificationMonitor.2
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                if (NotificationMonitor.this.sendToDeviceMsgs.size() > 0) {
                    if (NotificationMonitor.this.sendToDeviceMsgs.size() > 10) {
                        NotificationMonitor.this.send(NotificationMonitor.this.sendToDeviceMsgs.get(NotificationMonitor.this.sendToDeviceMsgs.size() - 1));
                        NotificationMonitor.this.sendToDeviceMsgs.clear();
                    } else {
                        NotificationMonitor.this.send(NotificationMonitor.this.sendToDeviceMsgs.get(0));
                        NotificationMonitor.this.sendToDeviceMsgs.remove(0);
                    }
                }
            }
        }, 0L, 1000L);
    }

    public void send(SendToDeviceMsg sendToDeviceMsg) {
        YCBTClient.appSengMessageToDevice(sendToDeviceMsg.data, sendToDeviceMsg.mTitle + "", sendToDeviceMsg.conmsg, null);
    }

    @Override // android.service.notification.NotificationListenerService, android.app.Service
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.mReceiver);
        this.timer.cancel();
        this.timer = null;
    }

    @Override // android.service.notification.NotificationListenerService, android.app.Service
    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }

    private ArrayList<String> getPackagesOfDialerApps(Context context) {
        ArrayList<String> arrayList = new ArrayList<>();
        Intent intent = new Intent();
        intent.setAction("android.intent.action.DIAL");
        Iterator<ResolveInfo> it2 = context.getPackageManager().queryIntentActivities(intent, 0).iterator();
        while (it2.hasNext()) {
            arrayList.add(it2.next().activityInfo.applicationInfo.packageName);
        }
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:174:0x03a5 A[Catch: Exception -> 0x067b, TryCatch #0 {Exception -> 0x067b, blocks: (B:3:0x0006, B:5:0x0012, B:8:0x001a, B:11:0x0024, B:13:0x0030, B:16:0x0059, B:19:0x0062, B:21:0x006e, B:23:0x007a, B:25:0x0086, B:27:0x0092, B:29:0x0096, B:32:0x00a2, B:34:0x00ac, B:37:0x00b8, B:39:0x00cb, B:41:0x00cf, B:43:0x00d9, B:45:0x00f9, B:48:0x0100, B:219:0x04ea, B:225:0x04f8, B:235:0x0511, B:237:0x0516, B:239:0x0526, B:252:0x0554, B:254:0x055a, B:255:0x0560, B:257:0x0568, B:260:0x0581, B:262:0x059e, B:263:0x05b9, B:251:0x0548, B:243:0x0534, B:266:0x05c5, B:232:0x0507, B:267:0x05c9, B:268:0x05d5, B:270:0x05db, B:273:0x05e8, B:274:0x05eb, B:280:0x0609, B:282:0x0619, B:285:0x0625, B:289:0x0632, B:292:0x063b, B:279:0x0603, B:51:0x010c, B:53:0x0112, B:55:0x011a, B:57:0x0126, B:58:0x0131, B:61:0x013e, B:63:0x0148, B:65:0x0152, B:67:0x015c, B:69:0x0168, B:71:0x0174, B:73:0x0180, B:75:0x018c, B:77:0x0198, B:79:0x01a3, B:82:0x01ac, B:84:0x01b6, B:85:0x01bf, B:88:0x01c8, B:90:0x01d2, B:92:0x01dd, B:95:0x01e6, B:97:0x01f0, B:99:0x01fc, B:100:0x0205, B:103:0x020d, B:105:0x0217, B:106:0x0223, B:108:0x022a, B:110:0x0236, B:111:0x023f, B:113:0x0247, B:115:0x0253, B:118:0x025a, B:119:0x0264, B:121:0x026c, B:123:0x0278, B:124:0x0284, B:126:0x028c, B:128:0x0298, B:129:0x02a4, B:131:0x02aa, B:133:0x02b6, B:134:0x02c2, B:136:0x02ca, B:138:0x02d6, B:140:0x02e2, B:141:0x02ee, B:143:0x02f6, B:145:0x0302, B:146:0x030e, B:148:0x0316, B:150:0x0322, B:151:0x032e, B:153:0x0338, B:155:0x0344, B:156:0x034e, B:158:0x0356, B:160:0x035d, B:162:0x0361, B:164:0x036d, B:165:0x0377, B:167:0x037f, B:169:0x0386, B:171:0x038c, B:173:0x0399, B:174:0x03a5, B:176:0x03ad, B:178:0x03b4, B:180:0x03ba, B:182:0x03c6, B:183:0x03d2, B:185:0x03da, B:187:0x03df, B:189:0x03e3, B:191:0x03ef, B:192:0x03fb, B:195:0x0410, B:197:0x041d, B:198:0x0422, B:200:0x0494, B:201:0x049a, B:203:0x04a2, B:204:0x04a7, B:206:0x04af, B:207:0x04b4, B:210:0x04c3, B:212:0x04cb, B:214:0x04d2, B:216:0x04d6, B:218:0x04e0, B:244:0x0537, B:246:0x053f, B:248:0x0542, B:228:0x04ff, B:240:0x052c, B:276:0x05f1), top: B:298:0x0006, inners: #1, #2, #3, #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:183:0x03d2 A[Catch: Exception -> 0x067b, TryCatch #0 {Exception -> 0x067b, blocks: (B:3:0x0006, B:5:0x0012, B:8:0x001a, B:11:0x0024, B:13:0x0030, B:16:0x0059, B:19:0x0062, B:21:0x006e, B:23:0x007a, B:25:0x0086, B:27:0x0092, B:29:0x0096, B:32:0x00a2, B:34:0x00ac, B:37:0x00b8, B:39:0x00cb, B:41:0x00cf, B:43:0x00d9, B:45:0x00f9, B:48:0x0100, B:219:0x04ea, B:225:0x04f8, B:235:0x0511, B:237:0x0516, B:239:0x0526, B:252:0x0554, B:254:0x055a, B:255:0x0560, B:257:0x0568, B:260:0x0581, B:262:0x059e, B:263:0x05b9, B:251:0x0548, B:243:0x0534, B:266:0x05c5, B:232:0x0507, B:267:0x05c9, B:268:0x05d5, B:270:0x05db, B:273:0x05e8, B:274:0x05eb, B:280:0x0609, B:282:0x0619, B:285:0x0625, B:289:0x0632, B:292:0x063b, B:279:0x0603, B:51:0x010c, B:53:0x0112, B:55:0x011a, B:57:0x0126, B:58:0x0131, B:61:0x013e, B:63:0x0148, B:65:0x0152, B:67:0x015c, B:69:0x0168, B:71:0x0174, B:73:0x0180, B:75:0x018c, B:77:0x0198, B:79:0x01a3, B:82:0x01ac, B:84:0x01b6, B:85:0x01bf, B:88:0x01c8, B:90:0x01d2, B:92:0x01dd, B:95:0x01e6, B:97:0x01f0, B:99:0x01fc, B:100:0x0205, B:103:0x020d, B:105:0x0217, B:106:0x0223, B:108:0x022a, B:110:0x0236, B:111:0x023f, B:113:0x0247, B:115:0x0253, B:118:0x025a, B:119:0x0264, B:121:0x026c, B:123:0x0278, B:124:0x0284, B:126:0x028c, B:128:0x0298, B:129:0x02a4, B:131:0x02aa, B:133:0x02b6, B:134:0x02c2, B:136:0x02ca, B:138:0x02d6, B:140:0x02e2, B:141:0x02ee, B:143:0x02f6, B:145:0x0302, B:146:0x030e, B:148:0x0316, B:150:0x0322, B:151:0x032e, B:153:0x0338, B:155:0x0344, B:156:0x034e, B:158:0x0356, B:160:0x035d, B:162:0x0361, B:164:0x036d, B:165:0x0377, B:167:0x037f, B:169:0x0386, B:171:0x038c, B:173:0x0399, B:174:0x03a5, B:176:0x03ad, B:178:0x03b4, B:180:0x03ba, B:182:0x03c6, B:183:0x03d2, B:185:0x03da, B:187:0x03df, B:189:0x03e3, B:191:0x03ef, B:192:0x03fb, B:195:0x0410, B:197:0x041d, B:198:0x0422, B:200:0x0494, B:201:0x049a, B:203:0x04a2, B:204:0x04a7, B:206:0x04af, B:207:0x04b4, B:210:0x04c3, B:212:0x04cb, B:214:0x04d2, B:216:0x04d6, B:218:0x04e0, B:244:0x0537, B:246:0x053f, B:248:0x0542, B:228:0x04ff, B:240:0x052c, B:276:0x05f1), top: B:298:0x0006, inners: #1, #2, #3, #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:221:0x04f2  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x04fe  */
    /* JADX WARN: Removed duplicated region for block: B:270:0x05db A[Catch: Exception -> 0x067b, TryCatch #0 {Exception -> 0x067b, blocks: (B:3:0x0006, B:5:0x0012, B:8:0x001a, B:11:0x0024, B:13:0x0030, B:16:0x0059, B:19:0x0062, B:21:0x006e, B:23:0x007a, B:25:0x0086, B:27:0x0092, B:29:0x0096, B:32:0x00a2, B:34:0x00ac, B:37:0x00b8, B:39:0x00cb, B:41:0x00cf, B:43:0x00d9, B:45:0x00f9, B:48:0x0100, B:219:0x04ea, B:225:0x04f8, B:235:0x0511, B:237:0x0516, B:239:0x0526, B:252:0x0554, B:254:0x055a, B:255:0x0560, B:257:0x0568, B:260:0x0581, B:262:0x059e, B:263:0x05b9, B:251:0x0548, B:243:0x0534, B:266:0x05c5, B:232:0x0507, B:267:0x05c9, B:268:0x05d5, B:270:0x05db, B:273:0x05e8, B:274:0x05eb, B:280:0x0609, B:282:0x0619, B:285:0x0625, B:289:0x0632, B:292:0x063b, B:279:0x0603, B:51:0x010c, B:53:0x0112, B:55:0x011a, B:57:0x0126, B:58:0x0131, B:61:0x013e, B:63:0x0148, B:65:0x0152, B:67:0x015c, B:69:0x0168, B:71:0x0174, B:73:0x0180, B:75:0x018c, B:77:0x0198, B:79:0x01a3, B:82:0x01ac, B:84:0x01b6, B:85:0x01bf, B:88:0x01c8, B:90:0x01d2, B:92:0x01dd, B:95:0x01e6, B:97:0x01f0, B:99:0x01fc, B:100:0x0205, B:103:0x020d, B:105:0x0217, B:106:0x0223, B:108:0x022a, B:110:0x0236, B:111:0x023f, B:113:0x0247, B:115:0x0253, B:118:0x025a, B:119:0x0264, B:121:0x026c, B:123:0x0278, B:124:0x0284, B:126:0x028c, B:128:0x0298, B:129:0x02a4, B:131:0x02aa, B:133:0x02b6, B:134:0x02c2, B:136:0x02ca, B:138:0x02d6, B:140:0x02e2, B:141:0x02ee, B:143:0x02f6, B:145:0x0302, B:146:0x030e, B:148:0x0316, B:150:0x0322, B:151:0x032e, B:153:0x0338, B:155:0x0344, B:156:0x034e, B:158:0x0356, B:160:0x035d, B:162:0x0361, B:164:0x036d, B:165:0x0377, B:167:0x037f, B:169:0x0386, B:171:0x038c, B:173:0x0399, B:174:0x03a5, B:176:0x03ad, B:178:0x03b4, B:180:0x03ba, B:182:0x03c6, B:183:0x03d2, B:185:0x03da, B:187:0x03df, B:189:0x03e3, B:191:0x03ef, B:192:0x03fb, B:195:0x0410, B:197:0x041d, B:198:0x0422, B:200:0x0494, B:201:0x049a, B:203:0x04a2, B:204:0x04a7, B:206:0x04af, B:207:0x04b4, B:210:0x04c3, B:212:0x04cb, B:214:0x04d2, B:216:0x04d6, B:218:0x04e0, B:244:0x0537, B:246:0x053f, B:248:0x0542, B:228:0x04ff, B:240:0x052c, B:276:0x05f1), top: B:298:0x0006, inners: #1, #2, #3, #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:288:0x0630  */
    /* JADX WARN: Removed duplicated region for block: B:291:0x063a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:292:0x063b A[Catch: Exception -> 0x067b, TRY_LEAVE, TryCatch #0 {Exception -> 0x067b, blocks: (B:3:0x0006, B:5:0x0012, B:8:0x001a, B:11:0x0024, B:13:0x0030, B:16:0x0059, B:19:0x0062, B:21:0x006e, B:23:0x007a, B:25:0x0086, B:27:0x0092, B:29:0x0096, B:32:0x00a2, B:34:0x00ac, B:37:0x00b8, B:39:0x00cb, B:41:0x00cf, B:43:0x00d9, B:45:0x00f9, B:48:0x0100, B:219:0x04ea, B:225:0x04f8, B:235:0x0511, B:237:0x0516, B:239:0x0526, B:252:0x0554, B:254:0x055a, B:255:0x0560, B:257:0x0568, B:260:0x0581, B:262:0x059e, B:263:0x05b9, B:251:0x0548, B:243:0x0534, B:266:0x05c5, B:232:0x0507, B:267:0x05c9, B:268:0x05d5, B:270:0x05db, B:273:0x05e8, B:274:0x05eb, B:280:0x0609, B:282:0x0619, B:285:0x0625, B:289:0x0632, B:292:0x063b, B:279:0x0603, B:51:0x010c, B:53:0x0112, B:55:0x011a, B:57:0x0126, B:58:0x0131, B:61:0x013e, B:63:0x0148, B:65:0x0152, B:67:0x015c, B:69:0x0168, B:71:0x0174, B:73:0x0180, B:75:0x018c, B:77:0x0198, B:79:0x01a3, B:82:0x01ac, B:84:0x01b6, B:85:0x01bf, B:88:0x01c8, B:90:0x01d2, B:92:0x01dd, B:95:0x01e6, B:97:0x01f0, B:99:0x01fc, B:100:0x0205, B:103:0x020d, B:105:0x0217, B:106:0x0223, B:108:0x022a, B:110:0x0236, B:111:0x023f, B:113:0x0247, B:115:0x0253, B:118:0x025a, B:119:0x0264, B:121:0x026c, B:123:0x0278, B:124:0x0284, B:126:0x028c, B:128:0x0298, B:129:0x02a4, B:131:0x02aa, B:133:0x02b6, B:134:0x02c2, B:136:0x02ca, B:138:0x02d6, B:140:0x02e2, B:141:0x02ee, B:143:0x02f6, B:145:0x0302, B:146:0x030e, B:148:0x0316, B:150:0x0322, B:151:0x032e, B:153:0x0338, B:155:0x0344, B:156:0x034e, B:158:0x0356, B:160:0x035d, B:162:0x0361, B:164:0x036d, B:165:0x0377, B:167:0x037f, B:169:0x0386, B:171:0x038c, B:173:0x0399, B:174:0x03a5, B:176:0x03ad, B:178:0x03b4, B:180:0x03ba, B:182:0x03c6, B:183:0x03d2, B:185:0x03da, B:187:0x03df, B:189:0x03e3, B:191:0x03ef, B:192:0x03fb, B:195:0x0410, B:197:0x041d, B:198:0x0422, B:200:0x0494, B:201:0x049a, B:203:0x04a2, B:204:0x04a7, B:206:0x04af, B:207:0x04b4, B:210:0x04c3, B:212:0x04cb, B:214:0x04d2, B:216:0x04d6, B:218:0x04e0, B:244:0x0537, B:246:0x053f, B:248:0x0542, B:228:0x04ff, B:240:0x052c, B:276:0x05f1), top: B:298:0x0006, inners: #1, #2, #3, #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:306:0x05f1 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // android.service.notification.NotificationListenerService
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onNotificationPosted(android.service.notification.StatusBarNotification r18) {
        /*
            Method dump skipped, instructions count: 1664
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.me.service.NotificationMonitor.onNotificationPosted(android.service.notification.StatusBarNotification):void");
    }

    public class SendToDeviceMsg {
        public String conmsg;
        public byte data;
        public String mTitle;

        public SendToDeviceMsg(byte data, String mTitle, String conmsg) {
            this.data = data;
            this.mTitle = mTitle;
            this.conmsg = conmsg;
        }
    }

    @Override // android.service.notification.NotificationListenerService
    public void onNotificationRemoved(StatusBarNotification sbn) {
        updateCurrentNotifications();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateCurrentNotifications() {
        try {
            StatusBarNotification[] activeNotifications = getActiveNotifications();
            if (mCurrentNotifications.size() == 0) {
                mCurrentNotifications.add(null);
            }
            mCurrentNotifications.set(0, activeNotifications);
            mCurrentNotificationsCounts = activeNotifications.length;
        } catch (Exception e2) {
            CrashReport.postCatchedException(e2);
            e2.printStackTrace();
        }
    }

    public static StatusBarNotification[] getCurrentNotifications() {
        if (mCurrentNotifications.size() == 0) {
            return null;
        }
        return mCurrentNotifications.get(0);
    }
}
