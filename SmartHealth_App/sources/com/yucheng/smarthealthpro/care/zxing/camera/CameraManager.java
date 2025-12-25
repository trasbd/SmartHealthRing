package com.yucheng.smarthealthpro.care.zxing.camera;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.Camera;
import android.os.Build;
import android.os.Handler;
import android.view.SurfaceHolder;
import com.realsil.sdk.dfu.DfuConstants;
import java.io.IOException;

/* loaded from: classes4.dex */
public final class CameraManager {
    static final int SDK_INT;
    private static CameraManager cameraManager;
    private final AutoFocusCallback autoFocusCallback;
    private Camera camera;
    private final CameraConfigurationManager configManager;
    private final Context context;
    private Rect framingRect;
    private Rect framingRectInPreview;
    private boolean initialized;
    private Camera.Parameters parameter;
    private final PreviewCallback previewCallback;
    private boolean previewing;
    private final boolean useOneShotPreviewCallback;

    static {
        int i2;
        try {
            i2 = Integer.parseInt(Build.VERSION.SDK);
        } catch (NumberFormatException unused) {
            i2 = DfuConstants.MAX_NOTIFICATION_LOCK_WAIT_TIME;
        }
        SDK_INT = i2;
    }

    public static void init(Context context) {
        if (cameraManager == null) {
            cameraManager = new CameraManager(context);
        }
    }

    public static CameraManager get() {
        return cameraManager;
    }

    private CameraManager(Context context) {
        this.context = context;
        CameraConfigurationManager cameraConfigurationManager = new CameraConfigurationManager(context);
        this.configManager = cameraConfigurationManager;
        boolean z = Integer.parseInt(Build.VERSION.SDK) > 3;
        this.useOneShotPreviewCallback = z;
        this.previewCallback = new PreviewCallback(cameraConfigurationManager, z);
        this.autoFocusCallback = new AutoFocusCallback();
    }

    public void openDriver(SurfaceHolder holder) throws IOException {
        if (this.camera == null) {
            Camera cameraOpen = Camera.open();
            this.camera = cameraOpen;
            if (cameraOpen == null) {
                throw new IOException();
            }
            cameraOpen.setPreviewDisplay(holder);
            if (!this.initialized) {
                this.initialized = true;
                this.configManager.initFromCameraParameters(this.camera);
            }
            this.configManager.setDesiredCameraParameters(this.camera);
            FlashlightManager.enableFlashlight();
        }
    }

    public void closeDriver() {
        if (this.camera != null) {
            FlashlightManager.disableFlashlight();
            this.camera.release();
            this.camera = null;
        }
    }

    public void startPreview() {
        Camera camera = this.camera;
        if (camera == null || this.previewing) {
            return;
        }
        try {
            camera.startPreview();
            this.previewing = true;
        } catch (RuntimeException e2) {
            e2.printStackTrace();
        }
    }

    public void stopPreview() {
        Camera camera = this.camera;
        if (camera == null || !this.previewing) {
            return;
        }
        if (!this.useOneShotPreviewCallback) {
            camera.setPreviewCallback(null);
        }
        this.camera.stopPreview();
        this.previewCallback.setHandler(null, 0);
        this.autoFocusCallback.setHandler(null, 0);
        this.previewing = false;
    }

    public void requestPreviewFrame(Handler handler, int message) {
        if (this.camera == null || !this.previewing) {
            return;
        }
        this.previewCallback.setHandler(handler, message);
        if (this.useOneShotPreviewCallback) {
            this.camera.setOneShotPreviewCallback(this.previewCallback);
        } else {
            this.camera.setPreviewCallback(this.previewCallback);
        }
    }

    public void requestAutoFocus(Handler handler, int message) {
        if (this.camera == null || !this.previewing) {
            return;
        }
        this.autoFocusCallback.setHandler(handler, message);
        this.camera.autoFocus(this.autoFocusCallback);
    }

    public void openLight() {
        Camera camera = this.camera;
        if (camera != null) {
            Camera.Parameters parameters = camera.getParameters();
            this.parameter = parameters;
            parameters.setFlashMode("torch");
            this.camera.setParameters(this.parameter);
        }
    }

    public void offLight() {
        Camera camera = this.camera;
        if (camera != null) {
            Camera.Parameters parameters = camera.getParameters();
            this.parameter = parameters;
            parameters.setFlashMode("off");
            this.camera.setParameters(this.parameter);
        }
    }

    public Rect getFramingRect() {
        Point screenResolution = this.configManager.getScreenResolution();
        if (screenResolution == null) {
            return null;
        }
        if (this.framingRect == null) {
            if (this.camera == null) {
                return null;
            }
            int i2 = ((screenResolution.x > screenResolution.y ? screenResolution.y : screenResolution.x) * 7) / 10;
            int i3 = (screenResolution.x - i2) / 2;
            int i4 = (screenResolution.y - i2) / 3;
            this.framingRect = new Rect(i3, i4, i3 + i2, i2 + i4);
        }
        return this.framingRect;
    }

    public Rect getFramingRectInPreview() {
        if (this.framingRectInPreview == null) {
            Rect rect = new Rect(getFramingRect());
            Point cameraResolution = this.configManager.getCameraResolution();
            Point screenResolution = this.configManager.getScreenResolution();
            rect.left = (rect.left * cameraResolution.y) / screenResolution.x;
            rect.right = (rect.right * cameraResolution.y) / screenResolution.x;
            rect.top = (rect.top * cameraResolution.x) / screenResolution.y;
            rect.bottom = (rect.bottom * cameraResolution.x) / screenResolution.y;
            this.framingRectInPreview = rect;
        }
        return this.framingRectInPreview;
    }

    public PlanarYUVLuminanceSource buildLuminanceSource(byte[] data, int width, int height) {
        Rect framingRectInPreview = getFramingRectInPreview();
        int previewFormat = this.configManager.getPreviewFormat();
        String previewFormatString = this.configManager.getPreviewFormatString();
        if (previewFormat == 16 || previewFormat == 17) {
            return new PlanarYUVLuminanceSource(data, width, height, framingRectInPreview.left, framingRectInPreview.top, framingRectInPreview.width(), framingRectInPreview.height());
        }
        if ("yuv420p".equals(previewFormatString)) {
            return new PlanarYUVLuminanceSource(data, width, height, framingRectInPreview.left, framingRectInPreview.top, framingRectInPreview.width(), framingRectInPreview.height());
        }
        throw new IllegalArgumentException("Unsupported picture format: " + previewFormat + '/' + previewFormatString);
    }
}
