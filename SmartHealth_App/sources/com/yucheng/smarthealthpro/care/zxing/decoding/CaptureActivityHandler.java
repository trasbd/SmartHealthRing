package com.yucheng.smarthealthpro.care.zxing.decoding;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.google.zxing.Result;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.care.zxing.activity.CaptureActivity;
import com.yucheng.smarthealthpro.care.zxing.camera.CameraManager;

/* loaded from: classes4.dex */
public final class CaptureActivityHandler extends Handler {
    private static final String TAG = "CaptureActivityHandler";
    private final CaptureActivity activity;
    private final DecodeThread decodeThread;
    private State state;

    private enum State {
        PREVIEW,
        SUCCESS,
        DONE
    }

    public CaptureActivityHandler(CaptureActivity activity) {
        Log.d("ltf", "CaptureActivityHandler");
        this.activity = activity;
        DecodeThread decodeThread = new DecodeThread(activity);
        this.decodeThread = decodeThread;
        decodeThread.start();
        this.state = State.SUCCESS;
        CameraManager.get().startPreview();
        restartPreviewAndDecode();
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        int i2 = message.what;
        Log.d("ltf", "handleMessage id=" + i2);
        if (i2 == R.id.auto_focus) {
            if (this.state == State.PREVIEW) {
                CameraManager.get().requestAutoFocus(this, R.id.auto_focus);
                return;
            }
            return;
        }
        if (i2 == R.id.restart_preview) {
            Log.d(TAG, "Got restart preview message");
            restartPreviewAndDecode();
            return;
        }
        if (i2 == R.id.decode_succeeded) {
            Log.d(TAG, "Got decode succeeded message");
            this.state = State.SUCCESS;
            this.activity.handleDecode((Result) message.obj);
            return;
        }
        if (i2 == R.id.decode_failed) {
            this.state = State.PREVIEW;
            CameraManager.get().requestPreviewFrame(this.decodeThread.getHandler(), R.id.decode);
            return;
        }
        if (i2 == R.id.return_scan_result) {
            Log.d(TAG, "Got return scan result message");
            this.activity.setResult(-1, (Intent) message.obj);
            this.activity.finish();
        } else if (i2 == R.id.launch_product_query) {
            Log.d(TAG, "Got product query message");
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse((String) message.obj));
            intent.addFlags(524288);
            this.activity.startActivity(intent);
        }
    }

    public void quitSynchronously() {
        this.state = State.DONE;
        CameraManager.get().stopPreview();
        Message.obtain(this.decodeThread.getHandler(), R.id.quit).sendToTarget();
        try {
            this.decodeThread.join();
        } catch (InterruptedException unused) {
        }
        removeMessages(R.id.decode_succeeded);
        removeMessages(R.id.decode_failed);
    }

    private void restartPreviewAndDecode() {
        if (this.state == State.SUCCESS) {
            this.state = State.PREVIEW;
            CameraManager.get().requestPreviewFrame(this.decodeThread.getHandler(), R.id.decode);
            CameraManager.get().requestAutoFocus(this, R.id.auto_focus);
            this.activity.drawViewfinder();
        }
    }
}
