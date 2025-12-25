package com.yucheng.smarthealthpro.home.activity.ecg.activity;

import android.os.Bundle;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.care.utils.ImageViewUtil;
import com.yucheng.smarthealthpro.databinding.ActivityAiResultImageBinding;
import com.yucheng.smarthealthpro.home.activity.ecg.photo.PhotoView;
import com.yucheng.smarthealthpro.home.activity.ecg.photo.PhotoViewAttacher;

/* loaded from: classes5.dex */
public class AIResultImageActivity extends BaseVbActivity<ActivityAiResultImageBinding> {
    private PhotoView imageView;

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {
        if (ImageViewUtil.bitmap != null) {
            this.imageView.setImageBitmap(ImageViewUtil.bitmap);
            new PhotoViewAttacher(this.imageView).update();
        }
    }

    private void initView() {
        showBack();
        this.imageView = (PhotoView) findViewById(R.id.ai_result_iv);
    }
}
