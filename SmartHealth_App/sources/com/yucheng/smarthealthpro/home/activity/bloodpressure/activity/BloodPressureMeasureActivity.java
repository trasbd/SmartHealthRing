package com.yucheng.smarthealthpro.home.activity.bloodpressure.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.airbnb.lottie.LottieAnimationView;
import com.orhanobut.logger.Logger;
import com.yucheng.smarthealthpro.MainActivity;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.databinding.ActivityBpMeasureBinding;
import com.yucheng.smarthealthpro.framework.util.ToastUtil;
import com.yucheng.smarthealthpro.home.bean.RealDataResponse;
import com.yucheng.smarthealthpro.home.bean.ToAppDataResponse;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.DataSyncUtils;
import com.yucheng.smarthealthpro.utils.DebouncingClick;
import com.yucheng.smarthealthpro.utils.DialogUtils;
import com.yucheng.smarthealthpro.utils.EventBusMessageEvent;
import com.yucheng.smarthealthpro.utils.EventBusSyncData;
import com.yucheng.ycbtsdk.Constants;
import com.yucheng.ycbtsdk.YCBTClient;
import com.yucheng.ycbtsdk.response.BleDataResponse;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import java.util.Arrays;
import java.util.HashMap;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes5.dex */
public class BloodPressureMeasureActivity extends BaseVbActivity<ActivityBpMeasureBinding> {
    protected boolean hasMeasure = false;
    private boolean isStarting = false;
    ImageView ivLottieBg;
    LottieAnimationView mLottieAnimationView;
    TextView tvLottieData;
    TextView tvStartButton;

    private void initData() {
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        EventBus.getDefault().register(this);
    }

    private void initView() {
        this.ivLottieBg = ((ActivityBpMeasureBinding) this.mBinding).includeMeasure.ivLottieBg;
        this.tvLottieData = ((ActivityBpMeasureBinding) this.mBinding).includeMeasure.tvLottieData;
        this.mLottieAnimationView = ((ActivityBpMeasureBinding) this.mBinding).includeMeasure.lottie;
        TextView textView = ((ActivityBpMeasureBinding) this.mBinding).includeMeasure.tvStartButton;
        this.tvStartButton = textView;
        textView.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.bloodpressure.activity.BloodPressureMeasureActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        changeTitle(getString(R.string.home_blood_pressure_measure_title));
        showBack();
        this.ivLottieBg.setBackground(getResources().getDrawable(R.mipmap.bp_measure_bg));
        this.mLottieAnimationView.setAnimation(R.raw.blood_pressure);
        this.mLottieAnimationView.setSpeed(1.0f);
    }

    @Override // com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        playStopMeasure(0);
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override // com.yucheng.smarthealthpro.framework.BaseActivity
    public void backAction() {
        if (this.isStarting) {
            DialogUtils.showPromptDialog(this, getString(R.string.hint_over_measure), new DialogInterface.OnClickListener() { // from class: com.yucheng.smarthealthpro.home.activity.bloodpressure.activity.BloodPressureMeasureActivity$$ExternalSyntheticLambda1
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i2) {
                    this.f$0.lambda$backAction$0(dialogInterface, i2);
                }
            });
            return;
        }
        if (this.hasMeasure) {
            setResult(-1);
        }
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$backAction$0(DialogInterface dialogInterface, int i2) {
        playStopMeasure(0);
        dialogInterface.dismiss();
        if (this.hasMeasure) {
            setResult(-1);
        }
        finish();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        backAction();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDataResponse(ToAppDataResponse toAppDataResponse) {
        Log.e("result=", "onDataResponse deviceToApp");
        HashMap map = toAppDataResponse.hashMap;
        int i2 = toAppDataResponse.f5706i;
        Logger.d("chong-------hashMap==" + map.toString());
        if (map == null || map.get("dataType") == null || ((Integer) map.get("dataType")).intValue() != 1038 || map.get("datas") == null) {
            return;
        }
        final byte[] bArr = (byte[]) map.get("datas");
        Logger.d("chong-------data==" + Arrays.toString(bArr));
        if (bArr.length > 1) {
            runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.bloodpressure.activity.BloodPressureMeasureActivity.1
                @Override // java.lang.Runnable
                public void run() {
                    BloodPressureMeasureActivity.this.isStarting = false;
                    BloodPressureMeasureActivity.this.tvStartButton.setText(BloodPressureMeasureActivity.this.getString(R.string.health_reminder_of_long_sitting_reminder_time_start_title));
                    BloodPressureMeasureActivity.this.mLottieAnimationView.setRepeatCount(0);
                    BloodPressureMeasureActivity.this.mLottieAnimationView.clearAnimation();
                    byte b2 = bArr[1];
                    if (b2 == 1) {
                        ToastUtil.getInstance(BloodPressureMeasureActivity.this).toast(BloodPressureMeasureActivity.this.getString(R.string.measure_success));
                        BloodPressureMeasureActivity.this.syncData();
                    } else if (b2 == 2) {
                        ToastUtil.getInstance(BloodPressureMeasureActivity.this).toast(BloodPressureMeasureActivity.this.getString(R.string.measure_failed));
                    } else {
                        ToastUtil.getInstance(BloodPressureMeasureActivity.this).toast(BloodPressureMeasureActivity.this.getString(R.string.measure_cancle));
                    }
                }
            });
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRealDataResponse(RealDataResponse realDataResponse) {
        int i2 = realDataResponse.f5705i;
        final HashMap map = realDataResponse.hashMap;
        if (i2 == 1539) {
            runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.bloodpressure.activity.BloodPressureMeasureActivity.2
                @Override // java.lang.Runnable
                public void run() throws NumberFormatException {
                    String string = map.get("bloodSBP").toString();
                    String string2 = map.get("bloodDBP").toString();
                    try {
                        float f2 = Float.parseFloat(string);
                        float f3 = Float.parseFloat(string2);
                        if ("00".equals(string) || "00".equals(string2) || "0".equals(string) || "0".equals(string2) || f2 < 60.0f || f2 > 250.0f || f3 < 30.0f || f3 > 160.0f) {
                            return;
                        }
                        BloodPressureMeasureActivity.this.tvLottieData.setText(string + "/" + string2);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void syncData() {
        this.hasMeasure = true;
        DataSyncUtils.INSTANCE.getInstance(getApplicationContext()).getWatchesData(Constants.DATATYPE.Health_HistoryBlood);
        showProgressDialog(R.string.ecg_sync_data, true);
    }

    public void onViewClicked(View view) {
        if (this.isStarting) {
            DialogUtils.showPromptDialog(this, getString(R.string.hint_over_measure), new DialogInterface.OnClickListener() { // from class: com.yucheng.smarthealthpro.home.activity.bloodpressure.activity.BloodPressureMeasureActivity$$ExternalSyntheticLambda2
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i2) {
                    this.f$0.lambda$onViewClicked$1(dialogInterface, i2);
                }
            });
            return;
        }
        if (Constant.isHealthRing()) {
            if (YCBTClient.connectState() == 10) {
                DialogUtils.showPromptDialog(this, getString(R.string.hint_start_measuring), new DialogInterface.OnClickListener() { // from class: com.yucheng.smarthealthpro.home.activity.bloodpressure.activity.BloodPressureMeasureActivity$$ExternalSyntheticLambda3
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i2) {
                        this.f$0.lambda$onViewClicked$2(dialogInterface, i2);
                    }
                });
                return;
            } else {
                ToastUtil.getInstance(this).toast(getString(R.string.please_connect_the_device));
                return;
            }
        }
        if (YCBTClient.connectState() == 10) {
            this.tvLottieData.setText("--");
            this.mLottieAnimationView.setRepeatCount(-1);
            this.mLottieAnimationView.playAnimation();
            this.tvStartButton.setText(getString(R.string.health_reminder_of_long_sitting_reminder_time_end_title));
            playStopMeasure(1);
            return;
        }
        ToastUtil.getInstance(this).toast(getString(R.string.please_connect_the_device));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewClicked$1(DialogInterface dialogInterface, int i2) {
        playStopMeasure(0);
        dialogInterface.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewClicked$2(DialogInterface dialogInterface, int i2) {
        this.tvLottieData.setText("--");
        this.mLottieAnimationView.setRepeatCount(-1);
        this.mLottieAnimationView.playAnimation();
        this.tvStartButton.setText(getString(R.string.health_reminder_of_long_sitting_reminder_time_end_title));
        playStopMeasure(1);
        dialogInterface.dismiss();
    }

    private void playStopMeasure(final int type) {
        YCBTClient.appStartMeasurement(type, 1, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.home.activity.bloodpressure.activity.BloodPressureMeasureActivity.3
            @Override // com.yucheng.ycbtsdk.response.BleDataResponse
            public void onDataResponse(final int i2, float v, HashMap hashMap) {
                BloodPressureMeasureActivity.this.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.bloodpressure.activity.BloodPressureMeasureActivity.3.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (i2 == 0) {
                            if (type == 1) {
                                BloodPressureMeasureActivity.this.isStarting = true;
                                return;
                            }
                            BloodPressureMeasureActivity.this.isStarting = false;
                            BloodPressureMeasureActivity.this.tvStartButton.setText(BloodPressureMeasureActivity.this.getString(R.string.health_reminder_of_long_sitting_reminder_time_start_title));
                            BloodPressureMeasureActivity.this.mLottieAnimationView.setRepeatCount(0);
                            return;
                        }
                        BloodPressureMeasureActivity.this.isStarting = false;
                        BloodPressureMeasureActivity.this.tvStartButton.setText(BloodPressureMeasureActivity.this.getString(R.string.health_reminder_of_long_sitting_reminder_time_start_title));
                        BloodPressureMeasureActivity.this.mLottieAnimationView.setRepeatCount(0);
                        ToastUtil.getInstance(BloodPressureMeasureActivity.this).toast(BloodPressureMeasureActivity.this.getString(R.string.start_failed));
                        if (type == 1) {
                            BloodPressureMeasureActivity.this.tvStartButton.setText(BloodPressureMeasureActivity.this.getString(R.string.health_reminder_of_long_sitting_reminder_time_start_title));
                            BloodPressureMeasureActivity.this.mLottieAnimationView.setRepeatCount(0);
                        }
                    }
                });
            }
        });
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(EventBusSyncData eventBusSyncData) {
        this.tvStartButton.postDelayed(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.bloodpressure.activity.BloodPressureMeasureActivity.4
            @Override // java.lang.Runnable
            public void run() {
                BloodPressureMeasureActivity.this.dismissProgressDialog();
            }
        }, 1000L);
    }

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
