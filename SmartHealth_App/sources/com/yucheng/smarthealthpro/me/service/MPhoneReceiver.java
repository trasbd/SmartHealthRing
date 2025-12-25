package com.yucheng.smarthealthpro.me.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.yucheng.smarthealthpro.framework.HealthApplication;
import com.yucheng.smarthealthpro.me.bean.PhoneBean;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.MLog;
import com.yucheng.smarthealthpro.utils.NotificationManagerUtils;
import com.yucheng.smarthealthpro.utils.PermissionUtil;
import com.yucheng.smarthealthpro.utils.PhoneUtils;
import org.greenrobot.eventbus.EventBus;

/* loaded from: classes5.dex */
public class MPhoneReceiver extends BroadcastReceiver {
    private Context context;
    private char[] mPushMessageData;
    private String lastDate = "";
    private boolean isPush = true;

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED") || intent.getAction().equals("android.intent.action.NEW_OUTGOING_CALL")) {
            return;
        }
        sendPhoneState(context, intent.getStringExtra("incoming_number"));
    }

    private void sendPhoneState(Context context, String incomingNumber) {
        char[] cArr;
        String contactNameFromPhoneNum;
        if (NotificationManagerUtils.getIsSwitch(context) && PermissionUtil.isNotificationEnable(context)) {
            this.mPushMessageData = NotificationManagerUtils.getAllSwitchState(context);
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager == null) {
                return;
            }
            int callState = telephonyManager.getCallState();
            if (callState == 0) {
                MLog.INSTANCE.d("挂断：");
                MLog.INSTANCE.d("incomingNumber：" + incomingNumber);
                TextUtils.isEmpty(incomingNumber);
                return;
            }
            if (callState != 1) {
                if (callState != 2) {
                    return;
                }
                MLog.INSTANCE.d("接听：");
                MLog.INSTANCE.d("incomingNumber：" + incomingNumber);
                TextUtils.isEmpty(incomingNumber);
                return;
            }
            MLog.INSTANCE.d("响铃：");
            MLog.INSTANCE.d("incomingNumber：" + incomingNumber);
            if (!HealthApplication.isSyncing && (cArr = this.mPushMessageData) != null && cArr.length > 0 && cArr[0] == '1') {
                try {
                    if (incomingNumber == null) {
                        return;
                    }
                    try {
                        contactNameFromPhoneNum = PhoneUtils.getContactNameFromPhoneNum(context, incomingNumber);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        contactNameFromPhoneNum = incomingNumber;
                    }
                    if (this.isPush) {
                        if (contactNameFromPhoneNum == null || contactNameFromPhoneNum.isEmpty()) {
                            EventBus.getDefault().post(new PhoneBean(incomingNumber, incomingNumber, Constant.EventBusTags.BROADCAST_RECEIVED_THE_PHONE));
                        } else {
                            EventBus.getDefault().post(new PhoneBean(contactNameFromPhoneNum, incomingNumber, Constant.EventBusTags.BROADCAST_RECEIVED_THE_PHONE));
                        }
                    }
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }
        }
    }
}
