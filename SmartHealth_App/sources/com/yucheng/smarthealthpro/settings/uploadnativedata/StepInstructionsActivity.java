package com.yucheng.smarthealthpro.settings.uploadnativedata;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.gyf.immersionbar.ImmersionBar;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.databinding.ActivityStepInstructionsBinding;

/* loaded from: classes5.dex */
public class StepInstructionsActivity extends BaseVbActivity<ActivityStepInstructionsBinding> {
    private TextView tv_step_instructions_start;

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        changeTitle(getString(R.string.home_blood_pressure_measure_title));
        showBack();
        ImmersionBar.with(this).statusBarDarkFont(true, 0.2f).navigationBarDarkIcon(true, 0.2f).init();
        TextView textView = (TextView) findViewById(R.id.tv_step_instructions_start);
        this.tv_step_instructions_start = textView;
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.settings.uploadnativedata.StepInstructionsActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                StepInstructionsActivity.this.startActivity(new Intent(StepInstructionsActivity.this, (Class<?>) ArmBloodPressureMeasurementActivity.class));
            }
        });
    }
}
