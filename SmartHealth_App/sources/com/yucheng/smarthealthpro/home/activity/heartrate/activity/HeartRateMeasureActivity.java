package com.yucheng.smarthealthpro.home.activity.heartrate.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.tencent.bugly.crashreport.BuglyLog;
import com.tencent.bugly.crashreport.CrashReport;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.databinding.ActivityHeartratemeasureBinding;
import com.yucheng.smarthealthpro.home.activity.BaseMeasureActivity;
import com.yucheng.smarthealthpro.home.bean.RealDataResponse;
import com.yucheng.smarthealthpro.utils.EventBusSyncData;
import com.yucheng.smarthealthpro.utils.TransUtils;
import com.yucheng.ycbtsdk.Constants;
import java.util.HashMap;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes5.dex */
public class HeartRateMeasureActivity extends BaseMeasureActivity<ActivityHeartratemeasureBinding> {
    public static int HEART_RATE_MEASURE;

    @Override // com.yucheng.smarthealthpro.home.activity.BaseMeasureActivity
    protected int getType() {
        return 0;
    }

    public static void load(Activity activity) {
        activity.startActivityForResult(new Intent(activity, (Class<?>) HeartRateMeasureActivity.class), HEART_RATE_MEASURE);
    }

    @Override // com.yucheng.smarthealthpro.home.activity.BaseMeasureActivity, com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        changeTitle(getString(R.string.heart_rate_measure_title));
        showBack();
        this.tvLottieDataUnit.setText("bpm");
        this.ivLottieBg.setBackground(getResources().getDrawable(R.mipmap.hrv_measure_bg));
        this.mLottieAnimationView.setAnimation(R.raw.heart_rate);
        this.mLottieAnimationView.setSpeed(1.0f);
    }

    @Override // com.yucheng.smarthealthpro.home.activity.BaseMeasureActivity
    protected void syncData() {
        showProgressDialog(R.string.ecg_sync_data, true);
        this.hasMeasure = true;
        sync(Constants.DATATYPE.Health_HistoryHeart);
    }

    @Override // com.yucheng.smarthealthpro.home.activity.BaseMeasureActivity
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(RealDataResponse realDataResponse) {
        int i2 = realDataResponse.f5705i;
        final HashMap map = realDataResponse.hashMap;
        if (i2 != 1537 || map == null) {
            return;
        }
        runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.heartrate.activity.HeartRateMeasureActivity.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    int iIntValue = ((Integer) map.get("heartValue")).intValue();
                    if (iIntValue < TransUtils.HEART_RATE_VISIBLE_MIN || iIntValue > TransUtils.HEART_RATE_VISIBLE_MAX) {
                        return;
                    }
                    HeartRateMeasureActivity.this.tvLottieData.setText(iIntValue + "");
                } catch (Exception e2) {
                    BuglyLog.e("HeartRateMeasureActivity", e2.getMessage());
                    CrashReport.postCatchedException(e2);
                }
            }
        });
    }

    @Override // com.yucheng.smarthealthpro.home.activity.BaseMeasureActivity, com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override // com.yucheng.smarthealthpro.home.activity.BaseMeasureActivity
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(EventBusSyncData eventBusSyncData) {
        try {
            dismissProgressDialog();
        } catch (Exception e2) {
            CrashReport.postCatchedException(e2);
        }
    }
}
