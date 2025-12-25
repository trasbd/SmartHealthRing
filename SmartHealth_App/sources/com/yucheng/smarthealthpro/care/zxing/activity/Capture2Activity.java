package com.yucheng.smarthealthpro.care.zxing.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import com.google.zxing.Result;
import com.gyf.immersionbar.ImmersionBar;
import com.orhanobut.logger.Logger;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.care.zxing.camera.CameraManager;
import com.yucheng.smarthealthpro.care.zxing.decoding.CaptureActivityHandler;
import com.yucheng.smarthealthpro.care.zxing.decoding.InactivityTimer;
import com.yucheng.smarthealthpro.care.zxing.view.ViewfinderView;
import com.yucheng.smarthealthpro.databinding.ActivityScannerBinding;
import com.yucheng.smarthealthpro.framework.util.ToastUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes4.dex */
public class Capture2Activity extends BaseVbActivity<ActivityScannerBinding> implements SurfaceHolder.Callback {
    private static final String TAG = "CaptureActivity";
    private static final long VIBRATE_DURATION = 100;
    private CaptureActivityHandler handler;
    private boolean hasSurface;
    private InactivityTimer inactivityTimer;
    private SurfaceView surfaceView;
    private boolean vibrate;
    private ViewfinderView viewfinderView;
    private boolean flag = true;
    private int type = 0;
    private List<String> packages = new ArrayList();

    private void initCamera(SurfaceHolder surfaceHolder) {
    }

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
            if (this.type == 1 && this.packages.contains(resultString.substring(0, resultString.indexOf("/", 10) + 1))) {
                gotoBind(resultString);
                return;
            }
            if (this.type == 0 && resultString.contains(":") && !resultString.endsWith(":")) {
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
        new Message().what = R.id.restart_preview;
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
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
        }

        private OnClickListenerImpl() {
        }
    }
}
