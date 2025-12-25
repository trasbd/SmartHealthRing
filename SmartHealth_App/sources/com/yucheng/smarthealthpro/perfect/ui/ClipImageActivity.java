package com.yucheng.smarthealthpro.perfect.ui;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.databinding.ActivityClipImageBinding;
import com.yucheng.smarthealthpro.utils.DialogUtils;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes5.dex */
public class ClipImageActivity extends BaseVbActivity<ActivityClipImageBinding> implements View.OnClickListener {
    private static final String TAG = "ClipImageActivity";
    private ImageView back;
    private TextView btnCancel;
    private TextView btnOk;
    private ClipViewLayout clipViewLayout1;
    private ClipViewLayout clipViewLayout2;
    private int height;
    private Dialog mLoading;
    private int type;
    private int width;

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mLoading = DialogUtils.createLoadingDialog(this, R.string.loading);
        this.type = getIntent().getIntExtra("type", 1);
        initView();
    }

    public void initView() {
        this.clipViewLayout1 = (ClipViewLayout) findViewById(R.id.clipViewLayout1);
        this.clipViewLayout2 = (ClipViewLayout) findViewById(R.id.clipViewLayout2);
        this.back = (ImageView) findViewById(R.id.iv_back);
        this.btnCancel = (TextView) findViewById(R.id.btn_cancel);
        this.btnOk = (TextView) findViewById(R.id.bt_ok);
        this.back.setOnClickListener(this);
        this.btnCancel.setOnClickListener(this);
        this.btnOk.setOnClickListener(this);
        if (getIntent() != null) {
            this.width = getIntent().getIntExtra(ViewHierarchyConstants.DIMENSION_WIDTH_KEY, 200);
            this.height = getIntent().getIntExtra("height", 200);
        } else {
            this.width = 200;
            this.height = 200;
        }
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        if (this.type == 1) {
            this.clipViewLayout1.setVisibility(0);
            this.clipViewLayout2.setVisibility(8);
            this.clipViewLayout1.setImageSrc(getIntent().getData());
            this.clipViewLayout1.setSize(this.width, this.height);
            return;
        }
        this.clipViewLayout2.setVisibility(0);
        this.clipViewLayout1.setVisibility(8);
        this.clipViewLayout2.setImageSrc(getIntent().getData());
        this.clipViewLayout2.setSize(this.width, this.height);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        if (v.getId() == R.id.iv_back) {
            finish();
        } else if (v.getId() == R.id.btn_cancel) {
            finish();
        } else if (v.getId() == R.id.bt_ok) {
            generateUriAndReturn();
        }
    }

    /* renamed from: com.yucheng.smarthealthpro.perfect.ui.ClipImageActivity$1, reason: invalid class name */
    class AnonymousClass1 implements Runnable {
        AnonymousClass1() {
        }

        @Override // java.lang.Runnable
        public void run() throws IOException {
            Bitmap bitmapClip;
            if (ClipImageActivity.this.type == 1) {
                bitmapClip = ClipImageActivity.this.clipViewLayout1.clip();
            } else {
                bitmapClip = ClipImageActivity.this.clipViewLayout2.clip();
            }
            if (bitmapClip == null) {
                Log.e("android", "zoomedCropBitmap == null");
                return;
            }
            final Uri uriFromFile = Uri.fromFile(new File(ClipImageActivity.this.getCacheDir(), "cropped_" + System.currentTimeMillis() + ".png"));
            if (uriFromFile != null) {
                OutputStream outputStreamOpenOutputStream = null;
                try {
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                try {
                    try {
                        outputStreamOpenOutputStream = ClipImageActivity.this.getContentResolver().openOutputStream(uriFromFile);
                        if (outputStreamOpenOutputStream != null) {
                            bitmapClip.compress(Bitmap.CompressFormat.JPEG, 100, outputStreamOpenOutputStream);
                        }
                    } catch (Exception e3) {
                        e3.printStackTrace();
                        Log.e("android", "Cannot open file: " + uriFromFile, e3);
                        if (outputStreamOpenOutputStream != null) {
                            outputStreamOpenOutputStream.close();
                        }
                    }
                    if (outputStreamOpenOutputStream != null) {
                        outputStreamOpenOutputStream.close();
                    }
                    ClipImageActivity.this.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.perfect.ui.ClipImageActivity$1$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.lambda$run$0(uriFromFile);
                        }
                    });
                } catch (Throwable th) {
                    if (outputStreamOpenOutputStream != null) {
                        try {
                            outputStreamOpenOutputStream.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th;
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$run$0(Uri uri) {
            ClipImageActivity.this.mLoading.dismiss();
            Intent intent = new Intent();
            intent.setData(uri);
            ClipImageActivity.this.setResult(-1, intent);
            ClipImageActivity.this.finish();
        }
    }

    private void generateUriAndReturn() {
        this.mLoading.show();
        new Thread(new AnonymousClass1()).start();
    }
}
