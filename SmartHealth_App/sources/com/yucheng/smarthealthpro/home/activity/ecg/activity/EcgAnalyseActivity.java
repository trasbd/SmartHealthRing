package com.yucheng.smarthealthpro.home.activity.ecg.activity;

import android.content.Context;
import android.os.Bundle;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.databinding.ActivityEcganalyseBinding;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

/* loaded from: classes5.dex */
public class EcgAnalyseActivity extends BaseVbActivity<ActivityEcganalyseBinding> {
    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        changeTitle(getString(R.string.ecg_analyse_title));
        showBack();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
}
