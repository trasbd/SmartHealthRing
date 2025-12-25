package com.yucheng.smarthealthpro.perfect;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.alibaba.fastjson2.internal.asm.Opcodes;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.databinding.ActivityPerfectHeightWeightBinding;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.perfect.utils.DrawUtil;
import com.yucheng.smarthealthpro.perfect.view.DecimalScaleRulerView;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.DebouncingClick;

/* loaded from: classes5.dex */
public class HeightAndWeightActivity extends BaseVbActivity<ActivityPerfectHeightWeightBinding> {
    private int age;
    DecimalScaleRulerView mRulerHeight;
    DecimalScaleRulerView mRulerWeight;
    TextView mTvHeight;
    TextView mTvNext;
    TextView mTvWeight;
    private String nickName;
    private int sex;
    private float mRulerHeightMaxValue = 250.0f;
    private float mRulerHeightMinValue = 100.0f;
    private float mRulerWeightMaxValue = 200.0f;
    private float mRulerWeightMinValue = 30.0f;
    private int mHeight = Opcodes.TABLESWITCH;
    private int mWeight = 65;
    private boolean isIso = true;

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        this.mTvHeight = ((ActivityPerfectHeightWeightBinding) this.mBinding).tvHeight;
        this.mRulerHeight = ((ActivityPerfectHeightWeightBinding) this.mBinding).rulerHeight;
        this.mTvWeight = ((ActivityPerfectHeightWeightBinding) this.mBinding).tvWeight;
        this.mRulerWeight = ((ActivityPerfectHeightWeightBinding) this.mBinding).rulerWeight;
        TextView textView = ((ActivityPerfectHeightWeightBinding) this.mBinding).tvNext;
        this.mTvNext = textView;
        textView.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.perfect.HeightAndWeightActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        changeTitle(getString(R.string.perfect_heightandweight_title));
        this.nickName = getIntent().getStringExtra("nickName");
        this.sex = getIntent().getIntExtra(Constant.SpConstKey.SEX, 1);
        this.age = getIntent().getIntExtra(Constant.SpConstKey.AGE, 1);
        String str = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.UNIT, "");
        if (str == null || !str.equals(Constant.SpConstValue.ISO)) {
            this.isIso = false;
            this.mRulerHeightMaxValue = 100.0f;
            this.mRulerHeightMinValue = 40.0f;
            this.mRulerWeightMaxValue = 440.0f;
            this.mRulerWeightMinValue = 66.0f;
            this.mHeight = (int) ((this.mHeight * 0.3937f) + 0.5d);
            this.mTvHeight.setText(this.mHeight + "in");
            this.mWeight = (int) ((this.mWeight * 2.2046f) + 0.5d);
            this.mTvWeight.setText(this.mWeight + "lb");
        }
        this.mRulerHeight.setParam(DrawUtil.dip2px(20.0f), DrawUtil.dip2px(100.0f), DrawUtil.dip2px(50.0f), DrawUtil.dip2px(25.0f), DrawUtil.dip2px(15.0f), DrawUtil.dip2px(30.0f));
        this.mRulerHeight.initViewParam(this.mHeight, this.mRulerHeightMinValue, this.mRulerHeightMaxValue, 1);
        this.mRulerHeight.setValueChangeListener(new DecimalScaleRulerView.OnValueChangeListener() { // from class: com.yucheng.smarthealthpro.perfect.HeightAndWeightActivity.1
            @Override // com.yucheng.smarthealthpro.perfect.view.DecimalScaleRulerView.OnValueChangeListener
            public void onValueChange(float value) {
                if (HeightAndWeightActivity.this.isIso) {
                    HeightAndWeightActivity.this.mTvHeight.setText(value + "cm");
                    HeightAndWeightActivity.this.mHeight = (int) value;
                } else {
                    HeightAndWeightActivity.this.mTvHeight.setText(value + "in");
                    HeightAndWeightActivity.this.mHeight = (int) value;
                }
            }
        });
        this.mRulerWeight.setParam(DrawUtil.dip2px(20.0f), DrawUtil.dip2px(100.0f), DrawUtil.dip2px(50.0f), DrawUtil.dip2px(25.0f), DrawUtil.dip2px(15.0f), DrawUtil.dip2px(30.0f));
        this.mRulerWeight.initViewParam(this.mWeight, this.mRulerWeightMinValue, this.mRulerWeightMaxValue, 1);
        this.mRulerWeight.setValueChangeListener(new DecimalScaleRulerView.OnValueChangeListener() { // from class: com.yucheng.smarthealthpro.perfect.HeightAndWeightActivity.2
            @Override // com.yucheng.smarthealthpro.perfect.view.DecimalScaleRulerView.OnValueChangeListener
            public void onValueChange(float value) {
                if (HeightAndWeightActivity.this.isIso) {
                    HeightAndWeightActivity.this.mTvWeight.setText(value + "kg");
                    HeightAndWeightActivity.this.mWeight = (int) value;
                } else {
                    HeightAndWeightActivity.this.mTvWeight.setText(value + "lb");
                    HeightAndWeightActivity.this.mWeight = (int) value;
                }
            }
        });
    }

    public void onViewClicked(View view) {
        if (view.getId() == R.id.tv_next) {
            SharedPreferencesUtils.put(this.context, "height", Integer.valueOf(this.mHeight));
            SharedPreferencesUtils.put(this.context, Constant.SpConstKey.WEIGHT, Integer.valueOf(this.mWeight));
            SkinColorActivity.load(this, true, 2);
            finish();
        }
    }
}
