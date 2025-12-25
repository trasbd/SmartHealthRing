package com.yucheng.smarthealthpro.me.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.care.zxing.util.QrImageUtil;
import com.yucheng.smarthealthpro.databinding.ActivityMeQrBinding;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import com.yucheng.smarthealthpro.utils.AppImageMgr;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.ShareUtils;

/* loaded from: classes5.dex */
public class MeQrActivity extends BaseVbActivity<ActivityMeQrBinding> {
    ImageView ivMeQr;

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        this.ivMeQr = ((ActivityMeQrBinding) this.mBinding).ivMeQr;
        changeTitle(getString(R.string.me_personal_details_qr_cord_title));
        showBack();
    }

    private void initData() {
        Bitmap roundedCornerBitmap;
        String str = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.IMAGE_PATH, "");
        Log.i("AAAAAAAAAA", "==imagePath==" + str);
        if (str != null && !"".equals(str)) {
            roundedCornerBitmap = QrImageUtil.getRoundedCornerBitmap(AppImageMgr.getSmallBitmap(str), 70, 70, 7);
        } else {
            roundedCornerBitmap = QrImageUtil.getRoundedCornerBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.icon_head), 70, 70, 7);
        }
        final Bitmap bitmapCreateQRImage = QrImageUtil.createQRImage(this, "yc_user_name:" + SharedPreferencesUtils.get(this.context, Constant.SpConstKey.USER_NAME, ""), roundedCornerBitmap, (int) (getResources().getDisplayMetrics().density * 250.0f), (int) (getResources().getDisplayMetrics().density * 250.0f));
        if (bitmapCreateQRImage != null) {
            this.ivMeQr.setImageBitmap(bitmapCreateQRImage);
            showRightImage(R.mipmap.topbar_ic_share, new NavigationBar.MyOnClickListener() { // from class: com.yucheng.smarthealthpro.me.activity.MeQrActivity$$ExternalSyntheticLambda0
                @Override // com.yucheng.smarthealthpro.framework.view.NavigationBar.MyOnClickListener
                public final void onClick(View view) throws Throwable {
                    this.f$0.lambda$initData$0(bitmapCreateQRImage, view);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initData$0(Bitmap bitmap, View view) throws Throwable {
        ShareUtils.share(this, bitmap, "QRCode");
    }
}
