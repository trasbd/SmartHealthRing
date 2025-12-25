package com.yucheng.smarthealthpro.home.activity.bloodfat.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.tencent.bugly.crashreport.CrashReport;
import com.yucheng.smarthealthpro.MainActivity;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.databinding.ActivityBoMeasureBinding;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.framework.util.ToastUtil;
import com.yucheng.smarthealthpro.home.activity.BaseMeasureActivity;
import com.yucheng.smarthealthpro.home.bean.RealDataResponse;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.EventBusMessageEvent;
import com.yucheng.smarthealthpro.utils.FormatUtil;
import com.yucheng.smarthealthpro.utils.TransUtils;
import com.yucheng.ycbtsdk.Constants;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import java.util.HashMap;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes5.dex */
public class BloodFatMeasureActivity extends BaseMeasureActivity<ActivityBoMeasureBinding> {
    public static int BLOOD_FAT_MEASURE = 9;
    private String mBloodFatUnit;

    @Override // com.yucheng.smarthealthpro.home.activity.BaseMeasureActivity
    protected int getType() {
        return 9;
    }

    public static void load(Activity activity) {
        activity.startActivityForResult(new Intent(activity, (Class<?>) BloodFatMeasureActivity.class), BLOOD_FAT_MEASURE);
    }

    @Override // com.yucheng.smarthealthpro.home.activity.BaseMeasureActivity, com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        changeTitle(getString(R.string.home_blood_fat_measure_title));
        showBack();
        this.mBloodFatUnit = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.BLOOD_SUGAR_AND_BLOOD_FAT_UNIT, getString(R.string.blood_sugar_unit_1));
        this.tvLottieDataUnit.setText(this.mBloodFatUnit);
        this.ivLottieBg.setBackground(getResources().getDrawable(R.mipmap.blood_fat_measure_bg));
        this.mLottieAnimationView.setAnimation(R.raw.blood_fat);
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
        int i2 = realDataResponse.f5705i;
        final HashMap map = realDataResponse.hashMap;
        if (i2 != 1546 || map == null) {
            return;
        }
        runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.bloodfat.activity.BloodFatMeasureActivity$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() throws NumberFormatException {
                this.f$0.lambda$onEvent$0(map);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onEvent$0(HashMap map) throws NumberFormatException {
        try {
            float f2 = Float.parseFloat(((Integer) map.get("bloodLipidsInteger")) + "." + ((Integer) map.get("bloodLipidsFloat")));
            if (f2 == 0.0f || f2 < TransUtils.BLOOD_FAT_VISIBLE_MIN || f2 > TransUtils.BLOOD_FAT_VISIBLE_MAX) {
                return;
            }
            String strKeep2 = FormatUtil.keep2(f2);
            if (getString(R.string.blood_sugar_unit_2).equals(this.mBloodFatUnit)) {
                strKeep2 = FormatUtil.keep2(f2 * TransUtils.BLOOD_FAT_TRANS);
            }
            this.tvLottieData.setText(strKeep2);
        } catch (Exception e2) {
            CrashReport.postCatchedException(e2);
            e2.printStackTrace();
        }
    }

    @Override // com.yucheng.smarthealthpro.home.activity.BaseMeasureActivity
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventBusMessageEvent messageEvent) {
        if (messageEvent.belState != 0) {
            return;
        }
        ToastUtil.getInstance(this).toast(getString(R.string.device_disconnect));
        Log.e("TAG", "onEvent 断开连接" + messageEvent.belState);
        this.isStarting = false;
        this.tvStartButton.setText(getString(R.string.health_reminder_of_long_sitting_reminder_time_start_title));
        this.mLottieAnimationView.setRepeatCount(0);
        startActivity(new Intent(this, (Class<?>) MainActivity.class));
        finish();
    }
}
