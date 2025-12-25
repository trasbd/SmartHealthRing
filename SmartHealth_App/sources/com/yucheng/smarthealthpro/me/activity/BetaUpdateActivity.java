package com.yucheng.smarthealthpro.me.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.care.bean.UploadFileTypeBean;
import com.yucheng.smarthealthpro.databinding.ActivityBetaUpdateBinding;
import com.yucheng.smarthealthpro.utils.UpdateVersionUtil;

/* loaded from: classes5.dex */
public class BetaUpdateActivity extends BaseVbActivity<ActivityBetaUpdateBinding> {
    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final UploadFileTypeBean.AppInfo appInfoCheckBetaVersion = UpdateVersionUtil.getInstance().checkBetaVersion(false);
        changeTitle(getString(R.string.beta_version_title));
        showBack();
        if (appInfoCheckBetaVersion == null) {
            ((ActivityBetaUpdateBinding) this.mBinding).llUpdateTip.setVisibility(8);
            ((ActivityBetaUpdateBinding) this.mBinding).llThankTip.setVisibility(0);
        } else {
            ((ActivityBetaUpdateBinding) this.mBinding).llUpdateTip.setVisibility(0);
            ((ActivityBetaUpdateBinding) this.mBinding).llThankTip.setVisibility(8);
            ((ActivityBetaUpdateBinding) this.mBinding).tvVersion.setText(appInfoCheckBetaVersion.appVersion + "(" + appInfoCheckBetaVersion.bundleVersion + ")");
            ((ActivityBetaUpdateBinding) this.mBinding).tvToDownload.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.me.activity.BetaUpdateActivity.1
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    BetaUpdateActivity.this.context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(appInfoCheckBetaVersion.link)));
                }
            });
        }
    }
}
