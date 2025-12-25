package com.yucheng.smarthealthpro.perfect;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.databinding.ActivityPerfectMetricImperialBinding;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.DebouncingClick;

/* loaded from: classes5.dex */
public class UnitActivity extends BaseVbActivity<ActivityPerfectMetricImperialBinding> {
    RadioGroup mRadioGroup;
    RadioButton mRbImperial;
    RadioButton mRbMetric;
    TextView mTvNext;

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        this.mRbMetric = ((ActivityPerfectMetricImperialBinding) this.mBinding).rbMetric;
        this.mRbImperial = ((ActivityPerfectMetricImperialBinding) this.mBinding).rbImperial;
        this.mRadioGroup = ((ActivityPerfectMetricImperialBinding) this.mBinding).radioGroup;
        TextView textView = ((ActivityPerfectMetricImperialBinding) this.mBinding).tvNext;
        this.mTvNext = textView;
        textView.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.perfect.UnitActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        changeTitle(getString(R.string.perfect_unit_title));
        SharedPreferencesUtils.put(this.context, Constant.SpConstKey.UNIT, Constant.SpConstValue.ISO);
        this.mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.yucheng.smarthealthpro.perfect.UnitActivity.1
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_metric) {
                    SharedPreferencesUtils.put(UnitActivity.this.context, Constant.SpConstKey.UNIT, Constant.SpConstValue.ISO);
                } else if (checkedId == R.id.rb_imperial) {
                    SharedPreferencesUtils.put(UnitActivity.this.context, Constant.SpConstKey.UNIT, Constant.SpConstValue.INCH);
                }
            }
        });
    }

    public void onViewClicked(View view) {
        if (view.getId() == R.id.tv_next) {
            startActivity(new Intent(this, (Class<?>) HeightAndWeightActivity.class));
            finish();
        }
    }
}
