package com.yucheng.smarthealthpro.home.activity.running.activity;

import android.content.Context;
import android.os.Bundle;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.databinding.ActivityRunningknowBinding;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

/* loaded from: classes5.dex */
public class RunningKnowActivity extends BaseVbActivity<ActivityRunningknowBinding> {
    private void initData() {
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        changeTitle(getString(R.string.include_bottom_tv_second_button));
        showBack();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
}
