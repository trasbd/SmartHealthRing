package com.yucheng.smarthealthpro.me.service;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import com.google.android.gms.location.DeviceOrientationRequest;
import com.realsil.sdk.dfu.DfuConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MShakeDetector.kt */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u00013B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+H\u0016J\u0018\u0010,\u001a\u00020)2\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020\u001bH\u0016J\u0010\u00100\u001a\u00020)2\u0006\u00101\u001a\u000202H\u0002R\u0014\u0010\u0004\u001a\u00020\u0005X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0011\"\u0004\b\u0016\u0010\u0013R\u001a\u0010\u0017\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0011\"\u0004\b\u0019\u0010\u0013R\u0014\u0010\u001a\u001a\u00020\u001bX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001e\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u000b\"\u0004\b \u0010\rR\u000e\u0010!\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\"\u001a\u0004\u0018\u00010#X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'¨\u00064"}, d2 = {"Lcom/yucheng/smarthealthpro/me/service/MShakeDetector;", "Landroid/hardware/SensorEventListener;", "<init>", "()V", ViewHierarchyConstants.TAG_KEY, "", "getTag", "()Ljava/lang/String;", "lastUpdateTime", "", "getLastUpdateTime", "()J", "setLastUpdateTime", "(J)V", "lastX", "", "getLastX", "()F", "setLastX", "(F)V", "lastY", "getLastY", "setLastY", "lastZ", "getLastZ", "setLastZ", "shake_threshold", "", "getShake_threshold", "()I", "lastDetectedTime", "getLastDetectedTime", "setLastDetectedTime", "availableInterval", "detectorListener", "Lcom/yucheng/smarthealthpro/me/service/MShakeDetector$DetectorListener;", "getDetectorListener", "()Lcom/yucheng/smarthealthpro/me/service/MShakeDetector$DetectorListener;", "setDetectorListener", "(Lcom/yucheng/smarthealthpro/me/service/MShakeDetector$DetectorListener;)V", "onSensorChanged", "", "event", "Landroid/hardware/SensorEvent;", "onAccuracyChanged", "sensor", "Landroid/hardware/Sensor;", "accuracy", "onShakeDetected", "isShake", "", "DetectorListener", "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class MShakeDetector implements SensorEventListener {
    private DetectorListener detectorListener;
    private long lastDetectedTime;
    private long lastUpdateTime;
    private float lastX;
    private float lastY;
    private float lastZ;
    private final String tag = "MSensorEventListener";
    private final int shake_threshold = 50;
    private long availableInterval = DeviceOrientationRequest.OUTPUT_PERIOD_DEFAULT;

    /* compiled from: MShakeDetector.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/yucheng/smarthealthpro/me/service/MShakeDetector$DetectorListener;", "", "onDetectResult", "", "isShake", "", "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public interface DetectorListener {
        void onDetectResult(boolean isShake);
    }

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        Intrinsics.checkNotNullParameter(sensor, "sensor");
    }

    public final String getTag() {
        return this.tag;
    }

    public final long getLastUpdateTime() {
        return this.lastUpdateTime;
    }

    public final void setLastUpdateTime(long j2) {
        this.lastUpdateTime = j2;
    }

    public final float getLastX() {
        return this.lastX;
    }

    public final void setLastX(float f2) {
        this.lastX = f2;
    }

    public final float getLastY() {
        return this.lastY;
    }

    public final void setLastY(float f2) {
        this.lastY = f2;
    }

    public final float getLastZ() {
        return this.lastZ;
    }

    public final void setLastZ(float f2) {
        this.lastZ = f2;
    }

    public final int getShake_threshold() {
        return this.shake_threshold;
    }

    public final long getLastDetectedTime() {
        return this.lastDetectedTime;
    }

    public final void setLastDetectedTime(long j2) {
        this.lastDetectedTime = j2;
    }

    public final DetectorListener getDetectorListener() {
        return this.detectorListener;
    }

    public final void setDetectorListener(DetectorListener detectorListener) {
        this.detectorListener = detectorListener;
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - this.lastDetectedTime < this.availableInterval) {
            return;
        }
        float f2 = event.values[0];
        float f3 = event.values[1];
        float f4 = event.values[2];
        long jCurrentTimeMillis2 = System.currentTimeMillis();
        if (jCurrentTimeMillis2 - this.lastUpdateTime > 100) {
            this.lastUpdateTime = jCurrentTimeMillis2;
            float fAbs = (((Math.abs(f2 - this.lastX) + Math.abs(f3 - this.lastY)) + Math.abs(f4 - this.lastZ)) / (jCurrentTimeMillis2 - r8)) * DfuConstants.MAX_NOTIFICATION_LOCK_WAIT_TIME;
            Log.d(this.tag, "onSensorChanged: speed = " + fAbs);
            if (fAbs > this.shake_threshold) {
                onShakeDetected(true);
            } else {
                onShakeDetected(false);
            }
            this.lastDetectedTime = jCurrentTimeMillis;
            this.lastX = f2;
            this.lastY = f3;
            this.lastZ = f4;
        }
    }

    private final void onShakeDetected(boolean isShake) {
        DetectorListener detectorListener = this.detectorListener;
        if (detectorListener != null) {
            detectorListener.onDetectResult(isShake);
        }
    }
}
