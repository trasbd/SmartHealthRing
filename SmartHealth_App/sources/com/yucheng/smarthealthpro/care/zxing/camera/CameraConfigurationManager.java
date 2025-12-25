package com.yucheng.smarthealthpro.care.zxing.camera;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.Camera;
import android.os.Build;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import java.util.regex.Pattern;

/* loaded from: classes4.dex */
final class CameraConfigurationManager {
    private static final Pattern COMMA_PATTERN = Pattern.compile(",");
    private static final String TAG = "CameraConfigurationManager";
    private static final int TEN_DESIRED_ZOOM = 27;
    private Point cameraResolution;
    private final Context context;
    private int previewFormat;
    private String previewFormatString;
    private Point screenResolution;

    CameraConfigurationManager(Context context) {
        this.context = context;
    }

    void initFromCameraParameters(Camera camera) {
        Camera.Parameters parameters = camera.getParameters();
        this.previewFormat = parameters.getPreviewFormat();
        this.previewFormatString = parameters.get("preview-format");
        Display defaultDisplay = ((WindowManager) this.context.getSystemService("window")).getDefaultDisplay();
        this.screenResolution = new Point(defaultDisplay.getWidth(), defaultDisplay.getHeight());
        Point point = new Point();
        point.x = this.screenResolution.x;
        point.y = this.screenResolution.y;
        if (this.screenResolution.x < this.screenResolution.y) {
            point.x = this.screenResolution.y;
            point.y = this.screenResolution.x;
        }
        this.cameraResolution = getCameraResolution(parameters, point);
        Log.d(TAG, "Camera resolution: " + this.screenResolution);
    }

    void setDesiredCameraParameters(Camera camera) {
        Camera.Parameters parameters = camera.getParameters();
        Log.d(TAG, "Setting preview size: " + this.cameraResolution);
        parameters.setPreviewSize(this.cameraResolution.x, this.cameraResolution.y);
        setFlash(parameters);
        setZoom(parameters);
        camera.setDisplayOrientation(90);
        camera.setParameters(parameters);
    }

    Point getCameraResolution() {
        return this.cameraResolution;
    }

    Point getScreenResolution() {
        return this.screenResolution;
    }

    int getPreviewFormat() {
        return this.previewFormat;
    }

    String getPreviewFormatString() {
        return this.previewFormatString;
    }

    private static Point getCameraResolution(Camera.Parameters parameters, Point screenResolution) {
        if (parameters.get("preview-size-values") == null) {
            parameters.get("preview-size-value");
        }
        Point pointFindBestPreviewSizeValue = findBestPreviewSizeValue(parameters, screenResolution);
        return pointFindBestPreviewSizeValue == null ? new Point((screenResolution.x >> 3) << 3, (screenResolution.y >> 3) << 3) : pointFindBestPreviewSizeValue;
    }

    private static Point findBestPreviewSizeValue(Camera.Parameters parameters, Point screenResolution) {
        Rect framingRect = CameraManager.get().getFramingRect();
        int i2 = framingRect.right - framingRect.left;
        int i3 = Integer.MAX_VALUE;
        int i4 = 0;
        int i5 = 0;
        for (Camera.Size size : parameters.getSupportedPreviewSizes()) {
            int i6 = size.height - i2;
            if (i6 > 0 && size.height * screenResolution.x == screenResolution.y * size.width && i6 < i3) {
                int i7 = size.width;
                i5 = size.height;
                i4 = i7;
                i3 = i6;
            }
        }
        Point point = i4 * i5 != 0 ? new Point(i4, i5) : null;
        if (point != null) {
            return point;
        }
        String str = parameters.get("preview-size-values");
        if (str == null) {
            str = parameters.get("preview-size-value");
        }
        if (str == null) {
            return null;
        }
        Log.d(TAG, "preview-size-values parameter: " + str);
        return findBestPreviewSizeValue(str, screenResolution);
    }

    private static Point findBestPreviewSizeValue(CharSequence previewSizeValueString, Point screenResolution) throws NumberFormatException {
        String[] strArrSplit = COMMA_PATTERN.split(previewSizeValueString);
        int length = strArrSplit.length;
        int i2 = Integer.MAX_VALUE;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (true) {
            if (i3 >= length) {
                break;
            }
            String strTrim = strArrSplit[i3].trim();
            int iIndexOf = strTrim.indexOf(120);
            if (iIndexOf < 0) {
                Log.w(TAG, "Bad preview-size: " + strTrim);
            } else {
                try {
                    int i6 = Integer.parseInt(strTrim.substring(0, iIndexOf));
                    int i7 = Integer.parseInt(strTrim.substring(iIndexOf + 1));
                    int iAbs = Math.abs(i6 - screenResolution.x) + Math.abs(i7 - screenResolution.y);
                    if (iAbs == 0) {
                        i5 = i7;
                        i4 = i6;
                        break;
                    }
                    if (iAbs < i2) {
                        i5 = i7;
                        i2 = iAbs;
                        i4 = i6;
                    }
                } catch (NumberFormatException unused) {
                    Log.w(TAG, "Bad preview-size: " + strTrim);
                }
            }
            i3++;
        }
        if (i4 <= 0 || i5 <= 0) {
            return null;
        }
        return new Point(i4, i5);
    }

    private static int findBestMotZoomValue(CharSequence stringValues, int tenDesiredZoom) throws NumberFormatException {
        int i2 = 0;
        for (String str : COMMA_PATTERN.split(stringValues)) {
            try {
                double d2 = Double.parseDouble(str.trim());
                int i3 = (int) (10.0d * d2);
                if (Math.abs(tenDesiredZoom - d2) < Math.abs(tenDesiredZoom - i2)) {
                    i2 = i3;
                }
            } catch (NumberFormatException unused) {
                return tenDesiredZoom;
            }
        }
        return i2;
    }

    private void setFlash(Camera.Parameters parameters) {
        if (Build.MODEL.contains("Behold II") && CameraManager.SDK_INT == 3) {
            parameters.set("flash-value", 1);
        } else {
            parameters.set("flash-value", 2);
        }
        parameters.set("flash-mode", "off");
    }

    private void setZoom(Camera.Parameters parameters) throws NumberFormatException {
        String str = parameters.get("zoom-supported");
        if (str == null || Boolean.parseBoolean(str)) {
            String str2 = parameters.get("max-zoom");
            int iFindBestMotZoomValue = 27;
            if (str2 != null) {
                try {
                    int i2 = (int) (Double.parseDouble(str2) * 10.0d);
                    if (27 > i2) {
                        iFindBestMotZoomValue = i2;
                    }
                } catch (NumberFormatException unused) {
                    Log.w(TAG, "Bad max-zoom: " + str2);
                }
            }
            String str3 = parameters.get("taking-picture-zoom-max");
            if (str3 != null) {
                try {
                    int i3 = Integer.parseInt(str3);
                    if (iFindBestMotZoomValue > i3) {
                        iFindBestMotZoomValue = i3;
                    }
                } catch (NumberFormatException unused2) {
                    Log.w(TAG, "Bad taking-picture-zoom-max: " + str3);
                }
            }
            String str4 = parameters.get("mot-zoom-values");
            if (str4 != null) {
                iFindBestMotZoomValue = findBestMotZoomValue(str4, iFindBestMotZoomValue);
            }
            String str5 = parameters.get("mot-zoom-step");
            if (str5 != null) {
                try {
                    int i4 = (int) (Double.parseDouble(str5.trim()) * 10.0d);
                    if (i4 > 1) {
                        iFindBestMotZoomValue -= iFindBestMotZoomValue % i4;
                    }
                } catch (NumberFormatException unused3) {
                }
            }
            if (str2 != null || str4 != null) {
                parameters.set("zoom", String.valueOf(iFindBestMotZoomValue / 10.0d));
            }
            if (str3 != null) {
                parameters.set("taking-picture-zoom", iFindBestMotZoomValue);
            }
        }
    }
}
