package com.yucheng.smarthealthpro.home.activity.temperature.activity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import com.yucheng.smarthealthpro.MainActivity;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.databinding.ActivityTempMeasureBinding;
import com.yucheng.smarthealthpro.framework.util.ToastUtil;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import com.yucheng.smarthealthpro.home.view.StepView;
import com.yucheng.smarthealthpro.utils.DebouncingClick;
import com.yucheng.smarthealthpro.utils.EventBusMessageEvent;
import com.yucheng.smarthealthpro.utils.TransUtils;
import com.yucheng.ycbtsdk.YCBTClient;
import com.yucheng.ycbtsdk.response.BleDataResponse;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import java.util.HashMap;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes5.dex */
public class TemperatureMeasureActivity extends BaseVbActivity<ActivityTempMeasureBinding> {
    public static int TEMPERATURE_MEASURE = 2;
    ImageView ivLottieBg;
    StepView mStepView;
    NavigationBar navigationbar;
    TextView tvLottieData;
    TextView tvStartButton;
    private ValueAnimator valueAnimator;
    private final int total_time = 600;
    private int count = 0;
    private boolean is_starting = false;
    Handler handler = new Handler(new Handler.Callback() { // from class: com.yucheng.smarthealthpro.home.activity.temperature.activity.TemperatureMeasureActivity.1
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (message.what == 0) {
                if (!TemperatureMeasureActivity.this.is_starting) {
                    TemperatureMeasureActivity.this.count = 0;
                    return false;
                }
                TemperatureMeasureActivity.this.count++;
                if (TemperatureMeasureActivity.this.mStepView != null) {
                    TemperatureMeasureActivity.this.mStepView.setCurrentStep((TemperatureMeasureActivity.this.count * 600) / 600);
                }
                if (TemperatureMeasureActivity.this.count >= 600) {
                    TemperatureMeasureActivity.this.handler.removeMessages(0);
                    TemperatureMeasureActivity.this.sendMonitoringMsg(0);
                } else {
                    TemperatureMeasureActivity.this.handler.removeMessages(0);
                    TemperatureMeasureActivity.this.handler.sendEmptyMessageDelayed(0, 1000L);
                    TemperatureMeasureActivity.this.sendRealTempmMsg();
                }
            }
            return false;
        }
    });

    /* JADX INFO: Access modifiers changed from: private */
    public void sendRealTempmMsg() {
        YCBTClient.getRealTemp(new BleDataResponse() { // from class: com.yucheng.smarthealthpro.home.activity.temperature.activity.TemperatureMeasureActivity.2
            @Override // com.yucheng.ycbtsdk.response.BleDataResponse
            public void onDataResponse(int i2, float v, HashMap hashMap) {
                final String str;
                if (i2 != 0 || hashMap == null || (str = (String) hashMap.get("tempValue")) == null) {
                    return;
                }
                TemperatureMeasureActivity.this.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.temperature.activity.TemperatureMeasureActivity.2.1
                    @Override // java.lang.Runnable
                    public void run() throws NumberFormatException {
                        try {
                            float f2 = Float.parseFloat(str);
                            if (TemperatureMeasureActivity.this.tvLottieData == null || f2 > TransUtils.TEMPERATURE_VISIBLE_MIN || f2 < TransUtils.TEMPERATURE_VISIBLE_MAX) {
                                return;
                            }
                            TemperatureMeasureActivity.this.tvLottieData.setText(str);
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendMonitoringMsg(final int type) {
        TextView textView = this.tvStartButton;
        if (textView != null) {
            textView.setEnabled(false);
        }
        YCBTClient.appTemperatureMeasure(type, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.home.activity.temperature.activity.TemperatureMeasureActivity.3
            @Override // com.yucheng.ycbtsdk.response.BleDataResponse
            public void onDataResponse(final int i2, float v, final HashMap hashMap) {
                TemperatureMeasureActivity.this.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.temperature.activity.TemperatureMeasureActivity.3.1
                    @Override // java.lang.Runnable
                    public void run() {
                        HashMap map;
                        if (TemperatureMeasureActivity.this.tvStartButton != null) {
                            TemperatureMeasureActivity.this.tvStartButton.setEnabled(true);
                        }
                        if (i2 == 0 && (map = hashMap) != null && ((Integer) map.get("data")).intValue() == 0) {
                            if (type == 0) {
                                TemperatureMeasureActivity.this.is_starting = false;
                                if (TemperatureMeasureActivity.this.tvStartButton != null) {
                                    TemperatureMeasureActivity.this.tvStartButton.setText(TemperatureMeasureActivity.this.getString(R.string.health_reminder_of_long_sitting_reminder_time_start_title));
                                    return;
                                }
                                return;
                            }
                            if (type == 1) {
                                TemperatureMeasureActivity.this.is_starting = true;
                                TemperatureMeasureActivity.this.count = 0;
                                TemperatureMeasureActivity.this.handler.sendEmptyMessage(0);
                                if (TemperatureMeasureActivity.this.tvStartButton != null) {
                                    TemperatureMeasureActivity.this.tvStartButton.setText(TemperatureMeasureActivity.this.getString(R.string.health_reminder_of_long_sitting_reminder_time_end_title));
                                }
                            }
                        }
                    }
                });
            }
        });
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setStep();
        EventBus.getDefault().register(this);
    }

    private void initView() {
        this.ivLottieBg = ((ActivityTempMeasureBinding) this.mBinding).ivLottieBg;
        this.tvLottieData = ((ActivityTempMeasureBinding) this.mBinding).tvLottieData;
        this.mStepView = ((ActivityTempMeasureBinding) this.mBinding).stepView;
        this.tvStartButton = ((ActivityTempMeasureBinding) this.mBinding).tvStartButton;
        this.navigationbar = ((ActivityTempMeasureBinding) this.mBinding).navigationbar;
        this.tvStartButton.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.temperature.activity.TemperatureMeasureActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        changeTitle(getString(R.string.home_temperature_measure_title));
        showBack();
        this.ivLottieBg.setBackground(getResources().getDrawable(R.mipmap.temp_measure_bg));
    }

    private void setStep() {
        this.mStepView.isGoneText(true);
        this.mStepView.setStepMax(600);
        ValueAnimator valueAnimatorOfFloat = ObjectAnimator.ofFloat(0.0f, 100.0f);
        this.valueAnimator = valueAnimatorOfFloat;
        valueAnimatorOfFloat.setDuration(1000L);
        this.valueAnimator.setInterpolator(new DecelerateInterpolator());
        this.valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.yucheng.smarthealthpro.home.activity.temperature.activity.TemperatureMeasureActivity.4
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator animation) {
                TemperatureMeasureActivity.this.mStepView.setCurrentStep((int) ((Float) animation.getAnimatedValue()).floatValue());
            }
        });
    }

    public void onViewClicked(View view) {
        if (this.is_starting) {
            sendMonitoringMsg(0);
        } else {
            this.tvLottieData.setText("--");
            sendMonitoringMsg(1);
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        sendMonitoringMsg(0);
        EventBus.getDefault().unregister(this);
    }

    private String TimeToString(int value) {
        StringBuilder sb;
        StringBuilder sbAppend;
        int i2 = value / 60;
        if (i2 < 10) {
            sb = new StringBuilder("0");
            sbAppend = sb.append(i2);
        } else {
            sb = new StringBuilder();
            sbAppend = sb.append(i2).append("");
        }
        int i3 = value % 60;
        return sbAppend.toString() + ":" + (i3 < 10 ? new StringBuilder("0").append(i3) : new StringBuilder().append(i3).append("")).toString();
    }

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
}
