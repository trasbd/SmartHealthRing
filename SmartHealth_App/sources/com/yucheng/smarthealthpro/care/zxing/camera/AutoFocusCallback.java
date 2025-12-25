package com.yucheng.smarthealthpro.care.zxing.camera;

import android.hardware.Camera;
import android.os.Handler;
import android.util.Log;

/* loaded from: classes4.dex */
final class AutoFocusCallback implements Camera.AutoFocusCallback {
    private static final long AUTOFOCUS_INTERVAL_MS = 1000;
    private static final String TAG = "AutoFocusCallback";
    private Handler autoFocusHandler;
    private int autoFocusMessage;

    AutoFocusCallback() {
    }

    void setHandler(Handler autoFocusHandler, int autoFocusMessage) {
        this.autoFocusHandler = autoFocusHandler;
        this.autoFocusMessage = autoFocusMessage;
    }

    @Override // android.hardware.Camera.AutoFocusCallback
    public void onAutoFocus(boolean success, Camera camera) {
        Handler handler = this.autoFocusHandler;
        if (handler != null) {
            this.autoFocusHandler.sendMessageDelayed(handler.obtainMessage(this.autoFocusMessage, Boolean.valueOf(success)), 1000L);
            this.autoFocusHandler = null;
            return;
        }
        Log.d(TAG, "Got auto-focus callback, but no handler for it");
    }
}
