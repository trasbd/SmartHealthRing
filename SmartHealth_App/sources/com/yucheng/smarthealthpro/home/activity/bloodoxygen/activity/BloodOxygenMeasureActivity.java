package com.yucheng.smarthealthpro.home.activity.bloodoxygen.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.tencent.bugly.crashreport.CrashReport;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.databinding.ActivityBoMeasureBinding;
import com.yucheng.smarthealthpro.home.activity.BaseMeasureActivity;
import com.yucheng.smarthealthpro.home.bean.RealDataResponse;
import com.yucheng.smarthealthpro.utils.TransUtils;
import com.yucheng.ycbtsdk.Constants;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class BloodOxygenMeasureActivity extends BaseMeasureActivity<ActivityBoMeasureBinding> {
    public static int BLOOD_OXYGEN_MEASURE = 2;

    @Override // com.yucheng.smarthealthpro.home.activity.BaseMeasureActivity
    protected int getType() {
        return 2;
    }

    public static void load(Activity activity) {
        activity.startActivityForResult(new Intent(activity, (Class<?>) BloodOxygenMeasureActivity.class), BLOOD_OXYGEN_MEASURE);
    }

    @Override // com.yucheng.smarthealthpro.home.activity.BaseMeasureActivity, com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        changeTitle(getString(R.string.home_blood_oxygen_measure_title));
        showBack();
        this.tvLottieDataUnit.setText("%");
        this.ivLottieBg.setBackground(getResources().getDrawable(R.mipmap.bo_measure_bg));
        this.mLottieAnimationView.setAnimation(R.raw.blood_oxygen);
        this.mLottieAnimationView.setSpeed(1.0f);
    }

    @Override // com.yucheng.smarthealthpro.home.activity.BaseMeasureActivity
    protected void syncData() {
        this.hasMeasure = true;
        sync(Constants.DATATYPE.Health_HistoryAll);
        showProgressDialog(R.string.ecg_sync_data, true);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    @Override // com.yucheng.smarthealthpro.home.activity.BaseMeasureActivity
    public void onEvent(RealDataResponse realDataResponse) {
        int i2 = realDataResponse.f5705i;
        final HashMap map = realDataResponse.hashMap;
        if (i2 != 1538 || map == null) {
            return;
        }
        runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.bloodoxygen.activity.BloodOxygenMeasureActivity$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onEvent$0(map);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onEvent$0(HashMap map) {
        try {
            Integer num = (Integer) map.get("bloodOxygenValue");
            if (num == null || num.intValue() == 0 || num.intValue() < TransUtils.BLOOD_OXYGEN_VISIBLE_MIN || num.intValue() > TransUtils.BLOOD_OXYGEN_VISIBLE_MAX) {
                return;
            }
            this.tvLottieData.setText("" + num);
        } catch (Exception e2) {
            CrashReport.postCatchedException(e2);
            e2.printStackTrace();
        }
    }
}
