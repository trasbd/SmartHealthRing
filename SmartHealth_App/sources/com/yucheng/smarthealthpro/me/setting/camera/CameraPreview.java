package com.yucheng.smarthealthpro.me.setting.camera;

import android.app.Activity;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.io.IOException;
import java.util.List;

/* loaded from: classes5.dex */
public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {
    private int cameraCurrentlyLocked;
    private Activity context;
    private boolean isPreview;
    private Camera mCamera;
    private SurfaceHolder mHolder;
    private Camera.Size mPictureSize;
    private Camera.Size mPreviewSize;

    public CameraPreview(Activity context, Camera mCamera, int cameraCurrentlyLocked) {
        super(context);
        this.context = context;
        this.mCamera = mCamera;
        SurfaceHolder holder = getHolder();
        this.mHolder = holder;
        holder.addCallback(this);
        this.cameraCurrentlyLocked = cameraCurrentlyLocked;
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder holder) throws IOException {
        try {
            Camera camera = this.mCamera;
            if (camera != null) {
                this.isPreview = true;
                camera.setPreviewDisplay(holder);
                this.mCamera.setDisplayOrientation(getPreviewDegree(this.context));
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        if (holder.getSurface() == null) {
            return;
        }
        this.mCamera.stopPreview();
        try {
            this.mPreviewSize = getPreviewSize(this.mCamera);
            this.mPictureSize = getPictureSize(this.mCamera);
            Camera.Parameters parameters = this.mCamera.getParameters();
            parameters.setPreviewSize(this.mPreviewSize.width, this.mPreviewSize.height);
            parameters.setPictureSize(this.mPictureSize.width, this.mPictureSize.height);
            parameters.setPictureFormat(256);
            parameters.set("jpeg-quality", 100);
            if (this.cameraCurrentlyLocked == 1) {
                parameters.setRotation(getPreviewDegree(this.context) + 180);
            } else {
                parameters.setRotation(getPreviewDegree(this.context));
            }
            this.mCamera.setParameters(parameters);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        this.mCamera.startPreview();
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (this.mCamera == null || !this.isPreview) {
            return;
        }
        holder.removeCallback(this);
        this.mCamera.setPreviewCallback(null);
        this.mCamera.stopPreview();
        this.mCamera.lock();
        this.mCamera.release();
        this.mCamera = null;
    }

    public void switchCamera(Camera camera, int cameraCurrentlyLocked) throws IOException {
        this.mCamera = camera;
        try {
            this.mPreviewSize = getPreviewSize(camera);
            this.mPictureSize = getPictureSize(this.mCamera);
            this.mCamera.setPreviewDisplay(this.mHolder);
            this.mCamera.setDisplayOrientation(getPreviewDegree(this.context));
            Camera.Parameters parameters = this.mCamera.getParameters();
            parameters.setPreviewSize(this.mPreviewSize.width, this.mPreviewSize.height);
            parameters.setPictureSize(this.mPictureSize.width, this.mPictureSize.height);
            parameters.setPictureFormat(256);
            if (cameraCurrentlyLocked == 1) {
                parameters.setRotation(getPreviewDegree(this.context) + 180);
            } else {
                parameters.setRotation(getPreviewDegree(this.context));
            }
            this.mCamera.setParameters(parameters);
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    private int getPreviewDegree(Activity activity) {
        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        if (rotation == 0) {
            return 90;
        }
        if (rotation == 1) {
            return 0;
        }
        if (rotation != 2) {
            return rotation != 3 ? 0 : 180;
        }
        return 270;
    }

    private Camera.Size getOptimalPreviewSize(List<Camera.Size> sizes, int w, int h2) {
        double d2 = w / h2;
        Camera.Size size = null;
        if (sizes == null) {
            return null;
        }
        double dAbs = Double.MAX_VALUE;
        double dAbs2 = Double.MAX_VALUE;
        for (Camera.Size size2 : sizes) {
            if (Math.abs((size2.width / size2.height) - d2) <= 0.05d && Math.abs(size2.height - h2) < dAbs2) {
                dAbs2 = Math.abs(size2.height - h2);
                size = size2;
            }
        }
        if (size == null) {
            for (Camera.Size size3 : sizes) {
                if (Math.abs(size3.height - h2) < dAbs) {
                    size = size3;
                    dAbs = Math.abs(size3.height - h2);
                }
            }
        }
        return size;
    }

    private Camera.Size getPreviewSize(Camera mCamera) {
        if (mCamera == null) {
            return null;
        }
        List<Camera.Size> supportedPreviewSizes = mCamera.getParameters().getSupportedPreviewSizes();
        Camera.Size size = supportedPreviewSizes.get(0);
        for (Camera.Size size2 : supportedPreviewSizes) {
            if (size.width < size2.width && size.height < size2.height) {
                size = size2;
            }
        }
        return size;
    }

    private Camera.Size getPictureSize(Camera mCamera) {
        List<Camera.Size> supportedPictureSizes = mCamera.getParameters().getSupportedPictureSizes();
        if (supportedPictureSizes.size() < 1) {
            return null;
        }
        Camera.Size size = supportedPictureSizes.get(0);
        for (Camera.Size size2 : supportedPictureSizes) {
            if (size.width < size2.width && size.height < size2.height) {
                size = size2;
            }
        }
        return size;
    }
}
