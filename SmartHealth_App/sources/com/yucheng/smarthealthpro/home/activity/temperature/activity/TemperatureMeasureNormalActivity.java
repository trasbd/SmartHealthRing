package com.yucheng.smarthealthpro.home.activity.temperature.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.yucheng.smarthealthpro.MainActivity;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.databinding.ActivityTempMeasureNormalBinding;
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
import java.math.RoundingMode;
import java.util.HashMap;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes5.dex */
public class TemperatureMeasureNormalActivity extends BaseMeasureActivity<ActivityTempMeasureNormalBinding> {
    public static int TEMPERATURE_MEASURE_NORMAL = 1;
    private int mTempUnit;

    @Override // com.yucheng.smarthealthpro.home.activity.BaseMeasureActivity
    protected int getType() {
        return 4;
    }

    public static void load(Activity activity) {
        activity.startActivityForResult(new Intent(activity, (Class<?>) TemperatureMeasureNormalActivity.class), TEMPERATURE_MEASURE_NORMAL);
    }

    @Override // com.yucheng.smarthealthpro.home.activity.BaseMeasureActivity, com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private String getUnit() {
        String str = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.TEMP_UNIT, "");
        if (str != null && str.equals(Constant.SpConstValue.TEMP_INCH)) {
            this.mTempUnit = 1;
            return str;
        }
        this.mTempUnit = 0;
        return Constant.SpConstValue.TEMP_ISO;
    }

    public String getTempData(double value) {
        if (this.mTempUnit == 0) {
            return "" + value;
        }
        return "" + FormatUtil.getBigDecimal((value * 1.8d) + 32.0d).setScale(1, RoundingMode.HALF_UP).floatValue();
    }

    private void initView() {
        changeTitle(getString(R.string.home_temperature_measure_normal));
        showBack();
        this.tvLottieDataUnit.setText(getUnit());
        this.ivLottieBg.setBackground(getResources().getDrawable(R.mipmap.temperature_measure_bg));
        this.mLottieAnimationView.setAnimation(R.raw.temperature);
        this.mLottieAnimationView.setSpeed(1.0f);
    }

    @Override // com.yucheng.smarthealthpro.home.activity.BaseMeasureActivity
    protected void syncData() {
        sync(Constants.DATATYPE.Health_HistoryAll);
        this.hasMeasure = true;
        showProgressDialog(R.string.ecg_sync_data, true);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    @Override // com.yucheng.smarthealthpro.home.activity.BaseMeasureActivity
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(RealDataResponse realDataResponse) {
        int i2 = realDataResponse.f5705i;
        final HashMap map = realDataResponse.hashMap;
        if (i2 == 1546) {
            runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.temperature.activity.TemperatureMeasureNormalActivity$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() throws NumberFormatException {
                    this.f$0.lambda$onEvent$0(map);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onEvent$0(HashMap map) throws NumberFormatException {
        double d2 = Double.parseDouble(((Integer) map.get("tempInteger")).intValue() + "." + ((Integer) map.get("tempFloat")).intValue());
        if (d2 < TransUtils.TEMPERATURE_VISIBLE_MIN || d2 > TransUtils.TEMPERATURE_VISIBLE_MAX) {
            return;
        }
        this.tvLottieData.setText(FormatUtil.getBigDecimal(getTempData(d2)).setScale(1, RoundingMode.HALF_UP).toPlainString());
    }

    @Override // com.yucheng.smarthealthpro.home.activity.BaseMeasureActivity
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventBusMessageEvent messageEvent) {
        if (messageEvent.belState != 0) {
            return;
        }
        ToastUtil.getInstance(this).toast(getString(R.string.device_disconnect));
        Log.e("TAG", "onEvent 断开连接" + messageEvent.belState);
        startActivity(new Intent(this, (Class<?>) MainActivity.class));
        finish();
    }

    @Override // com.yucheng.smarthealthpro.home.activity.BaseMeasureActivity, com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
    }
}
