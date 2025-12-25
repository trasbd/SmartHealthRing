package com.yucheng.smarthealthpro.me.service;

import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import com.yucheng.ycbtsdk.YCBTClient;
import com.yucheng.ycbtsdk.response.BleDataResponse;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class MSmsReceiver extends ContentObserver {
    private String address;
    private boolean isPush;
    private String lastDate;
    private Context mContext;
    private char[] mPushMessageData;
    private String mPushMessageOne;
    private Uri mUri;

    public MSmsReceiver(Handler handler, Context context) {
        super(handler);
        this.lastDate = "";
        this.isPush = true;
        this.mContext = context;
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x00ea A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00eb  */
    @Override // android.database.ContentObserver
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onChange(boolean r20) {
        /*
            Method dump skipped, instructions count: 311
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.me.service.MSmsReceiver.onChange(boolean):void");
    }

    private void pushSms(final int type, String title, String message) {
        YCBTClient.appSengMessageToDevice(type, title, message, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.service.MSmsReceiver.1
            @Override // com.yucheng.ycbtsdk.response.BleDataResponse
            public void onDataResponse(int code, float ratio, HashMap resultMap) {
                if (type == 0 && code == 0) {
                    MSmsReceiver.this.isPush = true;
                }
            }
        });
    }
}
