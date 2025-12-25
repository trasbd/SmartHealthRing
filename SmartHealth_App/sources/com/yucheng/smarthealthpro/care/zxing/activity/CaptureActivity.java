package com.yucheng.smarthealthpro.care.zxing.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.common.GlobalHistogramBinarizer;
import com.google.zxing.common.HybridBinarizer;
import com.gyf.immersionbar.ImmersionBar;
import com.journeyapps.barcodescanner.MixedDecoder;
import com.orhanobut.logger.Logger;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.care.zxing.camera.CameraManager;
import com.yucheng.smarthealthpro.care.zxing.decoding.CaptureActivityHandler;
import com.yucheng.smarthealthpro.care.zxing.decoding.DecodeThread;
import com.yucheng.smarthealthpro.care.zxing.decoding.InactivityTimer;
import com.yucheng.smarthealthpro.care.zxing.view.ViewfinderView;
import com.yucheng.smarthealthpro.databinding.ActivityScannerBinding;
import com.yucheng.smarthealthpro.framework.util.ToastUtil;
import com.yucheng.smarthealthpro.utils.QrCodeUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes4.dex */
public class CaptureActivity extends BaseVbActivity<ActivityScannerBinding> implements SurfaceHolder.Callback {
    private static final String TAG = "CaptureActivity";
    private static final long VIBRATE_DURATION = 100;
    private CaptureActivityHandler handler;
    private boolean hasSurface;
    private InactivityTimer inactivityTimer;
    private MultiFormatReader multiFormatReader;
    private SurfaceView surfaceView;
    private boolean vibrate;
    private ViewfinderView viewfinderView;
    private boolean flag = true;
    private int type = 0;
    private List<String> packages = new ArrayList();

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        SurfaceHolder holder = this.surfaceView.getHolder();
        if (this.hasSurface) {
            initCamera(holder);
        } else {
            holder.addCallback(this);
            holder.setType(3);
        }
        this.vibrate = true;
    }

    @Override // com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        CaptureActivityHandler captureActivityHandler = this.handler;
        if (captureActivityHandler != null) {
            captureActivityHandler.quitSynchronously();
            this.handler = null;
        }
        CameraManager.get().closeDriver();
        if (!this.hasSurface) {
            this.surfaceView.getHolder().removeCallback(this);
        }
        super.onPause();
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        InactivityTimer inactivityTimer = this.inactivityTimer;
        if (inactivityTimer != null) {
            inactivityTimer.shutdown();
            this.inactivityTimer = null;
        }
        CaptureActivityHandler captureActivityHandler = this.handler;
        if (captureActivityHandler != null) {
            captureActivityHandler.removeCallbacksAndMessages(null);
            this.handler = null;
        }
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initComponent();
        ImmersionBar.with(this).titleBar(this.bar).statusBarDarkFont(true, 0.0f).navigationBarDarkIcon(true, 0.0f).navigationBarColor(R.color.transparent).keyboardEnable(true).init();
    }

    protected void initComponent() {
        changeTitle(getString(R.string.scan_title));
        showBack();
        ((ActivityScannerBinding) this.mBinding).txtOpenLight.setOnClickListener(new OnClickListenerImpl());
        ((ActivityScannerBinding) this.mBinding).txtOpenPhotos.setOnClickListener(new OnClickListenerImpl());
        CameraManager.init(getApplicationContext());
        this.hasSurface = false;
        this.inactivityTimer = new InactivityTimer(this);
        this.surfaceView = (SurfaceView) findViewById(R.id.scanner_view);
        this.viewfinderView = (ViewfinderView) findViewById(R.id.viewfinder_content);
        if (getIntent() == null || getIntent().getIntExtra("type", 0) == 0) {
            return;
        }
        int intExtra = getIntent().getIntExtra("type", 0);
        this.type = intExtra;
        if (intExtra == 1) {
            this.packages.add("https://u.wechat.com/");
            this.packages.add("https://qm.qq.com/");
            this.packages.add("https://facebook.com/");
            this.packages.add("https://twitter.com/");
            this.packages.add("https://wa.me/");
            this.packages.add("https://instagram.com/");
            this.packages.add("http://instagram.com/");
        }
    }

    public void handleDecode(Result result) {
        this.inactivityTimer.onActivity();
        playBeepSoundAndVibrate();
        handleDecode(result.getText());
    }

    private void handleDecode(String resultString) {
        if (!TextUtils.isEmpty(resultString)) {
            Logger.d("chong----resultString==" + resultString);
            int i2 = this.type;
            if (i2 == 1) {
                gotoBind(resultString);
                return;
            }
            if (i2 == 0 && resultString.contains(":") && !resultString.endsWith(":")) {
                HashMap map = new HashMap();
                for (String str : resultString.split("--")) {
                    if (str.contains(":") && !str.endsWith(":")) {
                        map.put(str.split(":")[0], str.split(":")[1]);
                    }
                }
                if (map.get("yc_user_name") != null) {
                    gotoBind((String) map.get("yc_user_name"));
                    return;
                } else {
                    ToastUtil.getInstance(this).toast(resultString);
                    return;
                }
            }
            restartPreview();
            ToastUtil.getInstance(this).toast(resultString);
            return;
        }
        restartPreview();
    }

    private void gotoBind(String resultString) {
        setResult(100123, new Intent().putExtra("result", resultString));
        finish();
    }

    private void restartPreview() {
        Message message = new Message();
        message.what = R.id.restart_preview;
        if (this.handler == null) {
            this.handler = new CaptureActivityHandler(this);
        }
        this.handler.sendMessageDelayed(message, 2000L);
    }

    private void initCamera(SurfaceHolder surfaceHolder) {
        try {
            CameraManager.get().openDriver(surfaceHolder);
            if (this.handler == null) {
                this.handler = new CaptureActivityHandler(this);
            }
        } catch (IOException unused) {
            gotoBind("");
        } catch (RuntimeException unused2) {
            gotoBind("");
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder holder) {
        if (this.hasSurface) {
            return;
        }
        this.hasSurface = true;
        initCamera(holder);
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder holder) {
        this.hasSurface = false;
    }

    public Handler getHandler() {
        return this.handler;
    }

    public void drawViewfinder() {
        this.viewfinderView.drawViewfinder();
    }

    private void playBeepSoundAndVibrate() {
        if (this.vibrate) {
            ((Vibrator) getSystemService("vibrator")).vibrate(VIBRATE_DURATION);
        }
    }

    private class OnClickListenerImpl implements View.OnClickListener {
        private OnClickListenerImpl() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (view.getId() == R.id.txt_open_light) {
                if (CaptureActivity.this.flag) {
                    CaptureActivity.this.flag = false;
                    ((ImageView) view).setImageResource(R.mipmap.scan_light_on);
                    CameraManager.get().openLight();
                    return;
                } else {
                    CaptureActivity.this.flag = true;
                    ((ImageView) view).setImageResource(R.mipmap.scan_light);
                    CameraManager.get().offLight();
                    return;
                }
            }
            if (view.getId() == R.id.txt_open_photos) {
                CaptureActivity.this.gotoTakePhoto();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void gotoTakePhoto() {
        new Intent("android.intent.action.VIEW");
        Intent intent = new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, 1001);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) throws Throwable {
        Uri data2;
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode != 1001 || resultCode != -1 || data == null || (data2 = data.getData()) == null) {
            return;
        }
        Bitmap bitmapDecodeUri = QrCodeUtils.INSTANCE.decodeUri(this, data2, 400, 400);
        if (bitmapDecodeUri != null) {
            Log.e("onActivityResult", "bitmap=====" + bitmapDecodeUri.getWidth() + StringUtils.SPACE + bitmapDecodeUri.getHeight());
            String strSyncDecodeQRCode = syncDecodeQRCode(bitmapDecodeUri);
            Log.e("onActivityResult", "result=====" + strSyncDecodeQRCode);
            if (strSyncDecodeQRCode == null) {
                gotoBind("");
                return;
            } else {
                new Timer().schedule(new TimerTask() { // from class: com.yucheng.smarthealthpro.care.zxing.activity.CaptureActivity.1
                    @Override // java.util.TimerTask, java.lang.Runnable
                    public void run() {
                        CaptureActivity.this.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.care.zxing.activity.CaptureActivity.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                CaptureActivity.this.bitmapOnPause();
                            }
                        });
                    }
                }, 500L);
                handleDecode(strSyncDecodeQRCode);
                return;
            }
        }
        gotoBind("");
    }

    public void bitmapOnPause() {
        CaptureActivityHandler captureActivityHandler = this.handler;
        if (captureActivityHandler != null) {
            captureActivityHandler.quitSynchronously();
            this.handler = null;
        }
        CameraManager.get().closeDriver();
        if (!this.hasSurface) {
            this.surfaceView.getHolder().removeCallback(this);
        }
        SurfaceHolder holder = this.surfaceView.getHolder();
        if (this.hasSurface) {
            initCamera(holder);
        } else {
            holder.addCallback(this);
            holder.setType(3);
        }
        this.vibrate = true;
    }

    public String syncDecodeQRCode(Bitmap bitmap) {
        RGBLuminanceSource rGBLuminanceSource;
        try {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            int[] iArr = new int[width * height];
            bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
            rGBLuminanceSource = new RGBLuminanceSource(width, height, iArr);
            try {
                if (this.multiFormatReader == null) {
                    MultiFormatReader multiFormatReader = new MultiFormatReader();
                    this.multiFormatReader = multiFormatReader;
                    multiFormatReader.setHints(DecodeThread.getHints());
                }
                return this.multiFormatReader.decode(new BinaryBitmap(new HybridBinarizer(rGBLuminanceSource)), DecodeThread.getHints()).getText();
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                try {
                    return new MixedDecoder(this.multiFormatReader).decode(rGBLuminanceSource).getText();
                } catch (Exception unused) {
                    e.printStackTrace();
                    if (rGBLuminanceSource != null) {
                        try {
                            return new MultiFormatReader().decode(new BinaryBitmap(new GlobalHistogramBinarizer(rGBLuminanceSource)), DecodeThread.getHints()).getText();
                        } catch (Exception e3) {
                            e3.printStackTrace();
                            return null;
                        }
                    }
                    return null;
                }
            }
        } catch (Exception e4) {
            e = e4;
            rGBLuminanceSource = null;
        }
    }

    public Bitmap convertToGray(Bitmap colorBitmap) {
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(colorBitmap.getWidth(), colorBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint();
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0.0f);
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(colorBitmap, 0.0f, 0.0f, paint);
        return bitmapCreateBitmap;
    }

    private static Bitmap getDecodeAbleBitmap(String picturePath) {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            int i2 = 1;
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(picturePath, options);
            int i3 = options.outHeight / 400;
            if (i3 > 0) {
                i2 = i3;
            }
            options.inSampleSize = i2;
            options.inJustDecodeBounds = false;
            return BitmapFactory.decodeFile(picturePath, options);
        } catch (Exception unused) {
            return null;
        }
    }
}
