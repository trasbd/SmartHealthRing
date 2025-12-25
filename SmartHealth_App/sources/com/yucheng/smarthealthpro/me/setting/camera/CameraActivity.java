package com.yucheng.smarthealthpro.me.setting.camera;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.autonavi.amap.mapcore.AMapEngineUtils;
import com.gyf.immersionbar.ImmersionBar;
import com.newland.springdialog.AnimSpring;
import com.yucheng.smarthealthpro.MainActivity;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.databinding.ActivityCamreLayoutBinding;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.framework.util.ToastUtil;
import com.yucheng.smarthealthpro.utils.EventBusMessageEvent;
import com.yucheng.smarthealthpro.utils.EventBusTakePhotoEvent;
import com.yucheng.smarthealthpro.utils.PermissionUtil;
import com.yucheng.ycbtsdk.YCBTClient;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes5.dex */
public class CameraActivity extends BaseVbActivity<ActivityCamreLayoutBinding> {
    public static final String TAG = "CameraActivity";
    private ImageView camera_picture;
    private ImageView camera_switch;
    private boolean isFlashing;
    private boolean isFoucing;
    private boolean isTakePhoto;
    private String lastImagePath;
    private Camera mCamera;
    private Button mCancleButton;
    private ImageView mFlashButton;
    private OverCameraView mOverCameraView;
    private ImageView mPhotoButton;
    private RelativeLayout mPhotoLayout;
    private FrameLayout mPreviewLayout;
    private Runnable mRunnable;
    private int numberOfCameras;
    private CameraPreview preview;
    private Handler mHandler = new Handler(new Handler.Callback() { // from class: com.yucheng.smarthealthpro.me.setting.camera.CameraActivity.1
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message msg) {
            if (msg.what == 10010) {
                try {
                    CameraActivity.this.preview.switchCamera(CameraActivity.this.mCamera, CameraActivity.this.cameraCurrentlyLocked);
                    CameraActivity.this.mCamera.startPreview();
                    CameraActivity.this.isTakePhoto = false;
                    CameraActivity.this.setPreviewHeight();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            return false;
        }
    });
    private boolean isFront = true;
    private int cameraCurrentlyLocked = 1;
    private boolean isAuto = false;
    private List<byte[]> lists = new ArrayList();
    private OnClickListenerImpl onClickListener = new OnClickListenerImpl();
    private boolean saving = false;
    private boolean flag = true;
    private Thread thread = new Thread(new Runnable() { // from class: com.yucheng.smarthealthpro.me.setting.camera.CameraActivity.2
        @Override // java.lang.Runnable
        public void run() {
            FileOutputStream fileOutputStream;
            Throwable th;
            Exception e2;
            String str;
            synchronized (CameraActivity.this.thread) {
                while (CameraActivity.this.flag) {
                    if (CameraActivity.this.lists.size() > 0) {
                        try {
                            str = CameraActivity.this.getImageParentPath() + File.separator + "IMG_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".jpg";
                            fileOutputStream = new FileOutputStream(new File(str));
                        } catch (Exception e3) {
                            fileOutputStream = null;
                            e2 = e3;
                        } catch (Throwable th2) {
                            fileOutputStream = null;
                            th = th2;
                        }
                        try {
                            try {
                                fileOutputStream.write((byte[]) CameraActivity.this.lists.get(0));
                                Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
                                Uri uriFromFile = Uri.fromFile(new File(str));
                                Log.e(CameraActivity.TAG, "uri=" + uriFromFile);
                                intent.setData(uriFromFile);
                                CameraActivity.this.sendBroadcast(intent);
                                CameraActivity.this.lastImagePath = str;
                                Log.e(CameraActivity.TAG, " getPath:" + str);
                                CameraActivity.this.saving = false;
                                CameraActivity.this.lists.remove(0);
                            } catch (Exception e4) {
                                e2 = e4;
                                e2.printStackTrace();
                                CameraActivity.this.saving = false;
                                CameraActivity.this.lists.remove(0);
                                if (fileOutputStream != null) {
                                    try {
                                        fileOutputStream.close();
                                    } catch (IOException e5) {
                                        e = e5;
                                        e.printStackTrace();
                                        CameraActivity.this.thread.wait();
                                    }
                                }
                                CameraActivity.this.thread.wait();
                            }
                            try {
                                fileOutputStream.close();
                            } catch (IOException e6) {
                                e = e6;
                                e.printStackTrace();
                                CameraActivity.this.thread.wait();
                            }
                            try {
                                CameraActivity.this.thread.wait();
                            } catch (Exception e7) {
                                e7.printStackTrace();
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            CameraActivity.this.saving = false;
                            CameraActivity.this.lists.remove(0);
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (IOException e8) {
                                    e8.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    } else {
                        CameraActivity.this.thread.wait();
                    }
                }
            }
        }
    });
    private Camera.AutoFocusCallback autoFocusCallback = new Camera.AutoFocusCallback() { // from class: com.yucheng.smarthealthpro.me.setting.camera.CameraActivity.3
        @Override // android.hardware.Camera.AutoFocusCallback
        public void onAutoFocus(boolean success, Camera camera) {
            CameraActivity.this.isFoucing = false;
            CameraActivity.this.mOverCameraView.setFoucuing(false);
            CameraActivity.this.mOverCameraView.disDrawTouchFocusRect();
            CameraActivity.this.mHandler.removeCallbacks(CameraActivity.this.mRunnable);
        }
    };

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        if (!PermissionUtil.openCameraPermission(this, false) || !PermissionUtil.openSDCardPermission(this, false)) {
            if (!PermissionUtil.openCameraPermission(this, false)) {
                ToastUtil.getInstance(this).toast(getString(R.string.permission_camera_card));
            } else if (!PermissionUtil.openSDCardPermission(this, false)) {
                ToastUtil.getInstance(this).toast(getString(R.string.premission_sd_card));
            }
            finish();
            return;
        }
        initView();
        initData();
    }

    private void initView() {
        this.camera_switch = ((ActivityCamreLayoutBinding) this.mBinding).cameraSwitch;
        this.camera_picture = ((ActivityCamreLayoutBinding) this.mBinding).cameraPicture;
        this.mCancleButton = ((ActivityCamreLayoutBinding) this.mBinding).cameraCancle;
        this.mPreviewLayout = ((ActivityCamreLayoutBinding) this.mBinding).cameraPreviewLayout;
        this.mPhotoLayout = ((ActivityCamreLayoutBinding) this.mBinding).llPhotoLayout;
        this.mPhotoButton = ((ActivityCamreLayoutBinding) this.mBinding).cameraTakePhoto;
        this.mFlashButton = ((ActivityCamreLayoutBinding) this.mBinding).cameraFlash;
        String fileName = getFileName(getImageParentPath());
        if (fileName != null) {
            try {
                this.camera_picture.setImageBitmap(getSmallBitmap(fileName, (int) (getResources().getDisplayMetrics().density * 60.0f), (int) (getResources().getDisplayMetrics().density * 60.0f)));
                this.lastImagePath = fileName;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        initCamera();
        addCamera();
    }

    private void initData() {
        setOnclickListener();
        this.thread.start();
        ImmersionBar.with(this).titleBar(this.bar).statusBarDarkFont(true, 0.0f).navigationBarDarkIcon(true, 0.0f).navigationBarColor(R.color.transparent).keyboardEnable(true).init();
    }

    private void initCamera() {
        boolean zBooleanValue = ((Boolean) SharedPreferencesUtils.get(this.context, "isFront", true)).booleanValue();
        this.isFront = zBooleanValue;
        if (zBooleanValue) {
            this.mFlashButton.setVisibility(8);
        } else {
            this.mFlashButton.setVisibility(0);
        }
        this.numberOfCameras = Camera.getNumberOfCameras();
        for (int i2 = 0; i2 < this.numberOfCameras; i2++) {
            Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
            Camera.getCameraInfo(i2, cameraInfo);
            if (cameraInfo.facing == 1) {
                this.camera_switch.setVisibility(0);
                if (this.isFront) {
                    this.mCamera = Camera.open(i2);
                    this.cameraCurrentlyLocked = 1;
                    setPreviewHeight();
                    return;
                }
            }
        }
        if (this.mCamera == null) {
            this.mCamera = Camera.open();
            this.cameraCurrentlyLocked = 0;
        }
        setPreviewHeight();
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

    /* JADX INFO: Access modifiers changed from: private */
    public void setPreviewHeight() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(13);
        layoutParams.width = getResources().getDisplayMetrics().widthPixels;
        if (getPreviewSize(this.mCamera) != null) {
            layoutParams.height = (int) (((r1.widthPixels * r2.width) * 1.0f) / r2.height);
            this.mPreviewLayout.setLayoutParams(layoutParams);
        }
    }

    private void addCamera() {
        this.preview = new CameraPreview(this, this.mCamera, this.cameraCurrentlyLocked);
        this.mOverCameraView = new OverCameraView(this);
        this.mPreviewLayout.addView(this.preview);
        this.mPreviewLayout.addView(this.mOverCameraView);
    }

    private void setOnclickListener() {
        this.mCancleButton.setOnClickListener(this.onClickListener);
        this.mFlashButton.setOnClickListener(this.onClickListener);
        this.mPhotoButton.setOnClickListener(this.onClickListener);
        this.camera_switch.setOnClickListener(this.onClickListener);
        this.camera_picture.setOnClickListener(this.onClickListener);
    }

    public static String getFileName(String imageParentPath) {
        File[] fileArrListFiles = new File(imageParentPath).listFiles();
        if (fileArrListFiles != null && fileArrListFiles.length > 0) {
            for (int length = fileArrListFiles.length - 1; length >= 0; length--) {
                if (!fileArrListFiles[length].isDirectory() && (fileArrListFiles[length].getName().endsWith(".jpg") || fileArrListFiles[length].getName().endsWith(".png"))) {
                    return imageParentPath + File.separator + fileArrListFiles[length].getName();
                }
            }
        }
        return null;
    }

    @Override // com.yucheng.smarthealthpro.framework.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        Camera camera = this.mCamera;
        if (camera != null) {
            camera.release();
            this.mCamera = null;
        }
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        if (this.mCamera == null) {
            if (this.numberOfCameras == 0) {
                this.numberOfCameras = 1;
            }
            try {
                this.mCamera = Camera.open(this.cameraCurrentlyLocked % this.numberOfCameras);
                this.mHandler.sendEmptyMessageDelayed(10010, 100L);
            } catch (Exception unused) {
                Toast.makeText(this, R.string.setting_user_camera_visit_failed, 0).show();
                finish();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getImageParentPath() {
        String str = Environment.getExternalStorageDirectory().getPath() + File.separator + "DCIM" + File.separator + "Camera";
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        return str;
    }

    private Bitmap getSmallBitmap(byte[] data, int width, int height) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inPurgeable = true;
        options.inInputShareable = true;
        Bitmap bitmapDecodeByteArray = BitmapFactory.decodeByteArray(data, 0, data.length, options);
        Matrix matrix = new Matrix();
        matrix.postScale((width * 1.0f) / bitmapDecodeByteArray.getWidth(), (height * 1.0f) / bitmapDecodeByteArray.getHeight());
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmapDecodeByteArray, 0, 0, bitmapDecodeByteArray.getWidth(), bitmapDecodeByteArray.getHeight(), matrix, true);
        bitmapDecodeByteArray.recycle();
        return bitmapCreateBitmap;
    }

    private Bitmap getSmallBitmap(String path, int width, int height) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inPurgeable = true;
        options.inInputShareable = true;
        Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(path, options);
        if (bitmapDecodeFile == null) {
            return null;
        }
        Matrix matrix = new Matrix();
        matrix.postScale((width * 1.0f) / bitmapDecodeFile.getWidth(), (height * 1.0f) / bitmapDecodeFile.getHeight());
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmapDecodeFile, 0, 0, bitmapDecodeFile.getWidth(), bitmapDecodeFile.getHeight(), matrix, true);
        bitmapDecodeFile.recycle();
        return bitmapCreateBitmap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    class OnClickListenerImpl implements View.OnClickListener {
        private OnClickListenerImpl() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) throws IOException {
            if (view.getId() == R.id.camera_picture) {
                CameraActivity.this.startImage();
                return;
            }
            if (view.getId() == R.id.camera_switch) {
                CameraActivity.this.camera_switch.setOnClickListener(null);
                CameraActivity.this.switchCamera();
                CameraActivity.this.camera_switch.postDelayed(new Runnable() { // from class: com.yucheng.smarthealthpro.me.setting.camera.CameraActivity$OnClickListenerImpl$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onClick$0();
                    }
                }, 500L);
            } else {
                if (view.getId() == R.id.camera_cancle) {
                    CameraActivity.this.finish();
                    return;
                }
                if (view.getId() == R.id.camera_flash) {
                    CameraActivity.this.switchFlash();
                } else {
                    if (view.getId() != R.id.camera_take_photo || CameraActivity.this.isTakePhoto) {
                        return;
                    }
                    CameraActivity.this.takePhoto();
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onClick$0() {
            CameraActivity.this.camera_switch.setOnClickListener(CameraActivity.this.onClickListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startImage() {
        try {
            startActivity(new Intent("android.intent.action.VIEW", getImageContentUri(getApplicationContext(), this.lastImagePath)));
        } catch (Exception e2) {
            e2.printStackTrace();
            try {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setType("vnd.android.cursor.dir/image");
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                intent.setFlags(AMapEngineUtils.MAX_P20_WIDTH);
                startActivity(intent);
            } catch (Exception e3) {
                e3.printStackTrace();
                try {
                    Intent intent2 = new Intent("android.intent.action.MAIN", (Uri) null);
                    intent2.addCategory("android.intent.category.LAUNCHER");
                    for (ResolveInfo resolveInfo : getPackageManager().queryIntentActivities(intent2, 0)) {
                        if (resolveInfo.activityInfo.packageName.contains("gallery")) {
                            startActivity(getPackageManager().getLaunchIntentForPackage(resolveInfo.activityInfo.packageName));
                            return;
                        }
                    }
                } catch (Exception e4) {
                    e4.printStackTrace();
                }
            }
        }
    }

    public static Uri getImageContentUri(Context context, String path) {
        Uri uriInsert;
        Uri uri = Uri.parse("content://media/external/images/media");
        Cursor cursorQuery = null;
        try {
            try {
                File file = new File(path);
                if (file.exists()) {
                    String absolutePath = file.getAbsolutePath();
                    cursorQuery = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{"_id"}, "_data=? ", new String[]{absolutePath}, null);
                    if (cursorQuery == null || !cursorQuery.moveToFirst()) {
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("_data", absolutePath);
                        uriInsert = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                    } else {
                        uriInsert = Uri.withAppendedPath(uri, "" + cursorQuery.getInt(cursorQuery.getColumnIndex("_id")));
                    }
                    uri = uriInsert;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                if (0 != 0) {
                }
            }
            return uri;
        } finally {
            if (0 != 0) {
                cursorQuery.close();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void switchCamera() throws IOException {
        Camera camera = this.mCamera;
        if (camera != null) {
            camera.stopPreview();
            this.mCamera.release();
            this.mCamera = null;
        }
        this.mCamera = Camera.open((this.cameraCurrentlyLocked + 1) % this.numberOfCameras);
        this.cameraCurrentlyLocked = (this.cameraCurrentlyLocked + 1) % this.numberOfCameras;
        if (!this.isFront) {
            this.mFlashButton.setVisibility(8);
            this.isFront = true;
            SharedPreferencesUtils.put(this.context, "isFront", true);
        } else {
            this.mFlashButton.setVisibility(0);
            this.isFront = false;
            SharedPreferencesUtils.put(this.context, "isFront", false);
        }
        this.preview.switchCamera(this.mCamera, this.cameraCurrentlyLocked);
        this.mCamera.startPreview();
        this.isTakePhoto = false;
        setPreviewHeight();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void switchFlash() {
        boolean z = this.isFlashing;
        this.isFlashing = !z;
        this.mFlashButton.setImageResource(!z ? R.mipmap.flash_open : R.mipmap.flash_close);
        AnimSpring.getInstance(this.mFlashButton).startRotateAnim(120.0f, 360.0f);
        try {
            Camera.Parameters parameters = this.mCamera.getParameters();
            parameters.setFlashMode(this.isFlashing ? "torch" : "off");
            this.mCamera.setParameters(parameters);
        } catch (Exception unused) {
            Toast.makeText(this, R.string.flash_not_supported, 0).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void takePhoto() {
        Camera camera = this.mCamera;
        if (camera == null) {
            finish();
            return;
        }
        this.isTakePhoto = true;
        try {
            camera.takePicture(null, null, new Camera.PictureCallback() { // from class: com.yucheng.smarthealthpro.me.setting.camera.CameraActivity$$ExternalSyntheticLambda1
                @Override // android.hardware.Camera.PictureCallback
                public final void onPictureTaken(byte[] bArr, Camera camera2) {
                    this.f$0.lambda$takePhoto$0(bArr, camera2);
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$takePhoto$0(byte[] bArr, Camera camera) {
        this.lists.add(bArr);
        this.camera_picture.setImageBitmap(getSmallBitmap(bArr, (int) (getResources().getDisplayMetrics().density * 60.0f), (int) (getResources().getDisplayMetrics().density * 60.0f)));
        AnimSpring.getInstance(this.mPhotoLayout).startRotateAnim(120.0f, 360.0f);
        Camera camera2 = this.mCamera;
        if (camera2 != null) {
            camera2.startPreview();
        }
        synchronized (this.thread) {
            this.saving = true;
            this.thread.notify();
        }
        this.isTakePhoto = false;
    }

    @Override // android.app.Activity
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == 0 && !this.isFoucing) {
            float x = event.getX();
            float y = event.getY();
            this.isFoucing = true;
            Camera camera = this.mCamera;
            if (camera != null && !this.isTakePhoto) {
                this.mOverCameraView.setTouchFoucusRect(camera, this.autoFocusCallback, x, y);
            }
            Runnable runnable = new Runnable() { // from class: com.yucheng.smarthealthpro.me.setting.camera.CameraActivity$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onTouchEvent$1();
                }
            };
            this.mRunnable = runnable;
            this.mHandler.postDelayed(runnable, 3000L);
        }
        return super.onTouchEvent(event);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onTouchEvent$1() {
        this.isFoucing = false;
        this.mOverCameraView.setFoucuing(false);
        this.mOverCameraView.disDrawTouchFocusRect();
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        if (!this.isAuto) {
            sendStopTakePhoto();
        }
        this.flag = false;
        EventBus.getDefault().unregister(this);
    }

    public void sendStopTakePhoto() {
        YCBTClient.appControlTakePhoto(0, null);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void takePhotoState(EventBusTakePhotoEvent eventBusTakePhotoEvent) {
        if (eventBusTakePhotoEvent.data == 0) {
            this.isAuto = true;
            finish();
        } else {
            if (eventBusTakePhotoEvent.data != 2 || this.isTakePhoto) {
                return;
            }
            takePhoto();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getCompile(EventBusMessageEvent messageEvent) {
        if (!isDestroyed() && messageEvent.belState == 0) {
            startActivity(new Intent(this, (Class<?>) MainActivity.class));
            finish();
        }
    }
}
