package com.yucheng.smarthealthpro.me.service;

import android.content.Context;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import com.yucheng.smarthealthpro.framework.HealthApplication;
import com.yucheng.smarthealthpro.me.bean.PhoneBean;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.MLog;
import com.yucheng.smarthealthpro.utils.NotificationManagerUtils;
import com.yucheng.smarthealthpro.utils.PermissionUtil;
import com.yucheng.smarthealthpro.utils.PhoneUtils;
import org.greenrobot.eventbus.EventBus;

/* loaded from: classes5.dex */
public class MPhoneStateListener extends PhoneStateListener {
    public static final String TAG = "MPhoneStateListener";
    private static MPhoneStateListener listener;
    private Context context;
    private char[] mPushMessageData;

    public static MPhoneStateListener getInstance(Context context) {
        if (listener == null) {
            listener = new MPhoneStateListener(context);
        }
        return listener;
    }

    private MPhoneStateListener(Context context) {
        this.context = context;
    }

    @Override // android.telephony.PhoneStateListener
    public void onServiceStateChanged(ServiceState serviceState) {
        super.onServiceStateChanged(serviceState);
    }

    @Override // android.telephony.PhoneStateListener
    public void onCallStateChanged(int state, String incomingNumber) {
        super.onCallStateChanged(state, incomingNumber);
        if (NotificationManagerUtils.getIsSwitch(this.context) && PermissionUtil.isNotificationEnable(this.context)) {
            this.mPushMessageData = NotificationManagerUtils.getAllSwitchState(this.context);
            if (state == 0) {
                MLog.INSTANCE.d("来电挂断==");
                if (this.mPushMessageData[0] == '1') {
                    EventBus.getDefault().post(new PhoneBean(incomingNumber, incomingNumber, Constant.EventBusTags.MONITOR_HANG_UP_THE_PHONE));
                    return;
                }
                return;
            }
            if (state != 1) {
                if (state != 2) {
                    return;
                }
                MLog.INSTANCE.d("来电接通==");
                if (this.mPushMessageData[0] == '1') {
                    EventBus.getDefault().post(new PhoneBean(incomingNumber, incomingNumber, Constant.EventBusTags.MONITOR_ACCEPT_THE_PHONE));
                    return;
                }
                return;
            }
            MLog.INSTANCE.d("来电响铃==" + incomingNumber);
            if (HealthApplication.isSyncing || this.mPushMessageData[0] != '1' || incomingNumber == null) {
                return;
            }
            try {
                String contactNameFromPhoneNum = PhoneUtils.getContactNameFromPhoneNum(this.context, incomingNumber);
                if (contactNameFromPhoneNum == null || contactNameFromPhoneNum.isEmpty()) {
                    EventBus.getDefault().post(new PhoneBean(incomingNumber, incomingNumber, Constant.EventBusTags.MONITOR_RECEIVED_THE_PHONE));
                } else {
                    EventBus.getDefault().post(new PhoneBean(contactNameFromPhoneNum, incomingNumber, Constant.EventBusTags.MONITOR_RECEIVED_THE_PHONE));
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
