package com.yucheng.smarthealthpro.me.activity;

import android.os.Bundle;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.databinding.ActivityMeMessagecenterBinding;

/* loaded from: classes5.dex */
public class MeMessageCenterActivity extends BaseVbActivity<ActivityMeMessagecenterBinding> {
    private void initData() {
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        changeTitle(getString(R.string.me_message_center_title));
        showBack();
    }
}
