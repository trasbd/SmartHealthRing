package com.yucheng.smarthealthpro.home.activity.ecg.activity;

import android.os.Message;
import android.util.Log;

/* loaded from: classes5.dex */
public class HeartLineNDKCallback {
    public void hrv_evt_handle(int evt_type, float params) {
        Log.e("qob", "hrv_evt_handle " + evt_type);
        if (evt_type == 3) {
            Message message = new Message();
            message.what = 22;
            message.obj = Float.valueOf(params);
            if (EcgMeasureActivity.getInstance().mHandler != null) {
                EcgMeasureActivity.getInstance().mHandler.sendMessage(message);
                return;
            }
            return;
        }
        if (evt_type == 4 && params != 0.0f) {
            Message message2 = new Message();
            message2.what = 21;
            message2.obj = Float.valueOf(params);
            if (EcgMeasureActivity.getInstance() == null || EcgMeasureActivity.getInstance().mHandler == null) {
                return;
            }
            EcgMeasureActivity.getInstance().mHandler.sendMessage(message2);
        }
    }
}
