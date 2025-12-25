package com.yucheng.smarthealthpro.home.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.databinding.ActivityMeasureTipBinding;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.utils.Constant;

/* loaded from: classes5.dex */
public class MeasureTipActivity extends BaseVbActivity<ActivityMeasureTipBinding> {
    public static final String BLOOD_OXYGEN = "BLOOD_OXYGEN";
    public static int MEASURE_TIP = 100;

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showBack();
        changeTitle(R.string.measure_tip_title);
        TextView textView = (TextView) findViewById(R.id.tv_sure);
        TextView textView2 = (TextView) findViewById(R.id.tv_understood);
        ImageView imageView = (ImageView) findViewById(R.id.iv_tip);
        if (BLOOD_OXYGEN.equals(getIntent().getStringExtra("type"))) {
            imageView.setImageResource(R.mipmap.measure_tip2);
        }
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.home.activity.MeasureTipActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                SharedPreferencesUtils.put(MeasureTipActivity.this.getActivity(), Constant.SpConstKey.NotShowMeasureTip, true);
                MeasureTipActivity.this.setResult(-1);
                MeasureTipActivity.this.finish();
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.home.activity.MeasureTipActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                SharedPreferencesUtils.put(MeasureTipActivity.this.getActivity(), Constant.SpConstKey.NotShowMeasureTip, true);
                MeasureTipActivity.this.setResult(-1);
                MeasureTipActivity.this.finish();
            }
        });
    }
}
