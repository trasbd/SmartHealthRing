package com.yucheng.smarthealthpro.me.setting.thirdservice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.databinding.ActivityMeThirdpartyserviceBinding;
import com.yucheng.smarthealthpro.utils.DebouncingClick;
import com.yucheng.ycbtsdk.Constants;
import com.yucheng.ycbtsdk.YCBTClient;

/* loaded from: classes5.dex */
public class ThirdPartyServiceActivity extends BaseVbActivity<ActivityMeThirdpartyserviceBinding> {
    LinearLayout lyAli;
    LinearLayout lyGoogle;
    LinearLayout lyWechat;

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        this.lyWechat = ((ActivityMeThirdpartyserviceBinding) this.mBinding).lyThirdPartyWechat;
        this.lyAli = ((ActivityMeThirdpartyserviceBinding) this.mBinding).lyThirdPartyAli;
        this.lyGoogle = ((ActivityMeThirdpartyserviceBinding) this.mBinding).lyThirdPartyGoogle;
        ((ActivityMeThirdpartyserviceBinding) this.mBinding).thirdPartyAli.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.me.setting.thirdservice.ThirdPartyServiceActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        ((ActivityMeThirdpartyserviceBinding) this.mBinding).thirdPartyWechat.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.me.setting.thirdservice.ThirdPartyServiceActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        ((ActivityMeThirdpartyserviceBinding) this.mBinding).lyThirdPartyGoogle.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.me.setting.thirdservice.ThirdPartyServiceActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        changeTitle(getString(R.string.me_my_device_more_settings_ott_services));
        showBack();
        if (!YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASWECHATSPORT)) {
            this.lyWechat.setVisibility(8);
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASALIIOT)) {
            return;
        }
        this.lyAli.setVisibility(8);
    }

    public void onViewClicked(View view) {
        if (view.getId() == R.id.third_party_wechat) {
            startActivity(new Intent(this, (Class<?>) WechatPartyServiceActivity.class));
        } else if (view.getId() == R.id.third_party_ali) {
            startActivity(new Intent(this, (Class<?>) AliPartyServiceActivity.class));
        } else {
            view.getId();
            int i2 = R.id.ly_third_party_google;
        }
    }
}
