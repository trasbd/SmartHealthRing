package com.yucheng.ycbtsdk.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.yucheng.ycbtsdk.Constants;
import com.yucheng.ycbtsdk.YCBTClient;
import com.yucheng.ycbtsdk.utils.SPUtil;
import com.yucheng.ycbtsdk.utils.YCBTLog;

/* loaded from: classes5.dex */
public class MPhoneReceiver extends BroadcastReceiver {
    private void sendPhoneState(Context context, String str) {
        TelephonyManager telephonyManager;
        if (((Boolean) SPUtil.get(Constants.SharedKey.Call_Alerts_Str, Boolean.FALSE)).booleanValue() && (telephonyManager = (TelephonyManager) context.getSystemService("phone")) != null) {
            YCBTLog.e("来电状态：" + telephonyManager.getCallState());
            YCBTLog.e("incomingNumber：" + str);
            if (telephonyManager.getCallState() == 1 && str != null) {
                try {
                    YCBTLog.e("来电号码：" + str);
                    YCBTClient.appSengMessageToDevice(0, str, str, null);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        YCBTLog.e("收到广播：" + intent.getAction());
        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED") || intent.getAction().equals("android.intent.action.NEW_OUTGOING_CALL")) {
            return;
        }
        String stringExtra = intent.getStringExtra("incoming_number");
        if (TextUtils.isEmpty(stringExtra)) {
            stringExtra = "18688888888";
        }
        sendPhoneState(context, stringExtra);
    }
}
