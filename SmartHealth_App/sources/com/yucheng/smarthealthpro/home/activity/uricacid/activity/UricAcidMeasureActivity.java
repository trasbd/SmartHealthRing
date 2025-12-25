package com.yucheng.smarthealthpro.home.activity.uricacid.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.tencent.bugly.crashreport.CrashReport;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.databinding.ActivityBoMeasureBinding;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.home.activity.BaseMeasureActivity;
import com.yucheng.smarthealthpro.home.bean.RealDataResponse;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.FormatUtil;
import com.yucheng.smarthealthpro.utils.TransUtils;
import com.yucheng.ycbtsdk.Constants;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class UricAcidMeasureActivity extends BaseMeasureActivity<ActivityBoMeasureBinding> {
    public static int URICACID_MEASURE = 6;
    private String mUricAcidUnit;

    @Override // com.yucheng.smarthealthpro.home.activity.BaseMeasureActivity
    protected int getType() {
        return 6;
    }

    public static void load(Activity activity) {
        activity.startActivityForResult(new Intent(activity, (Class<?>) UricAcidMeasureActivity.class), URICACID_MEASURE);
    }

    @Override // com.yucheng.smarthealthpro.home.activity.BaseMeasureActivity, com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        changeTitle(getString(R.string.home_uric_acid_measure_title));
        showBack();
        this.mUricAcidUnit = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.URIC_ACID_UNIT, getString(R.string.uric_acid_unit_1));
        this.tvLottieDataUnit.setText(this.mUricAcidUnit);
        this.ivLottieBg.setBackground(getResources().getDrawable(R.mipmap.uric_acid_measure_bg));
        this.mLottieAnimationView.setAnimation(R.raw.uric_acid);
        this.mLottieAnimationView.setSpeed(1.0f);
    }

    @Override // com.yucheng.smarthealthpro.home.activity.BaseMeasureActivity
    protected void syncData() {
        this.hasMeasure = true;
        sync(Constants.DATATYPE.Health_HistoryComprehensiveMeasureData);
        showProgressDialog(R.string.ecg_sync_data, true);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    @Override // com.yucheng.smarthealthpro.home.activity.BaseMeasureActivity
    public void onEvent(RealDataResponse realDataResponse) {
        final HashMap map = realDataResponse.hashMap;
        if (realDataResponse.f5705i != 1546 || map == null) {
            return;
        }
        runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.uricacid.activity.UricAcidMeasureActivity$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onEvent$0(map);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onEvent$0(HashMap map) {
        try {
            Integer num = (Integer) map.get("uricAcid");
            if (num == null || num.intValue() == 0 || num.intValue() < TransUtils.URIC_ACID_VISIBLE_MIN || num.intValue() > TransUtils.URIC_ACID_VISIBLE_MAX) {
                return;
            }
            String string = num.toString();
            if (getString(R.string.uric_acid_unit_2).equals(this.mUricAcidUnit)) {
                string = FormatUtil.keep1(num.intValue() * TransUtils.URIC_ACID_TRANS);
            }
            this.tvLottieData.setText(string);
        } catch (Exception e2) {
            CrashReport.postCatchedException(e2);
            e2.printStackTrace();
        }
    }
}
