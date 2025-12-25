package com.yucheng.smarthealthpro.home.activity;

import android.animation.Animator;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.airbnb.lottie.LottieAnimationView;
import com.google.gson.GsonBuilder;
import com.orhanobut.logger.Logger;
import com.yucheng.smarthealthpro.MainActivity;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.framework.util.ToastUtil;
import com.yucheng.smarthealthpro.home.bean.RealDataResponse;
import com.yucheng.smarthealthpro.home.bean.ToAppDataResponse;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.DataSyncUtils;
import com.yucheng.smarthealthpro.utils.DebouncingClick;
import com.yucheng.smarthealthpro.utils.DialogUtils;
import com.yucheng.smarthealthpro.utils.EventBusMessageEvent;
import com.yucheng.smarthealthpro.utils.EventBusSyncData;
import com.yucheng.ycbtsdk.YCBTClient;
import com.yucheng.ycbtsdk.response.BleDataResponse;
import java.util.HashMap;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes5.dex */
public abstract class BaseMeasureActivity<VB extends ViewBinding> extends BaseVbActivity<VB> implements BleDataResponse {
    public ImageView ivLottieBg;
    protected LottieAnimationView mLottieAnimationView;
    public TextView tvLottieData;
    public TextView tvLottieDataUnit;
    public TextView tvStartButton;
    protected boolean hasMeasure = false;
    protected boolean isStarting = false;
    protected int onOff = 0;

    private void startMeasure() {
    }

    private void stopMeasure() {
    }

    protected abstract int getType();

    protected abstract void syncData();

    @Override // com.yucheng.ycbtsdk.response.BleDataResponse
    public void onDataResponse(final int i2, float v, final HashMap hashMap) {
        runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.BaseMeasureActivity$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onDataResponse$0(i2, hashMap);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onDataResponse$0(int i2, HashMap map) {
        if (i2 != 0 && ((Integer) map.get("dataType")).intValue() == 815 && this.isStarting) {
            ToastUtil.getInstance(getBaseContext()).toast(getString(R.string.start_failed));
            this.isStarting = false;
            this.tvStartButton.setText(getString(R.string.health_reminder_of_long_sitting_reminder_time_start_title));
            this.mLottieAnimationView.setRepeatCount(0);
            this.mLottieAnimationView.clearAnimation();
        }
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBindView();
        this.mLottieAnimationView.addAnimatorListener(new Animator.AnimatorListener() { // from class: com.yucheng.smarthealthpro.home.activity.BaseMeasureActivity.1
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animation) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animation) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animation) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                BaseMeasureActivity.this.mLottieAnimationView.setProgress(0.0f);
            }
        });
        EventBus.getDefault().register(this);
        startMeasure();
    }

    private void initBindView() {
        this.ivLottieBg = (ImageView) findViewById(R.id.iv_lottie_bg);
        this.tvLottieData = (TextView) findViewById(R.id.tv_lottie_data);
        this.mLottieAnimationView = (LottieAnimationView) findViewById(R.id.lottie);
        this.tvStartButton = (TextView) findViewById(R.id.tv_start_button);
        this.tvLottieDataUnit = (TextView) findViewById(R.id.tv_lottie_data_unit);
        this.tvStartButton.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.BaseMeasureActivity$$ExternalSyntheticLambda4
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
    }

    public void onViewClicked(View view) {
        if (this.isStarting) {
            DialogUtils.showPromptDialog(this, getString(R.string.hint_over_measure), new DialogInterface.OnClickListener() { // from class: com.yucheng.smarthealthpro.home.activity.BaseMeasureActivity$$ExternalSyntheticLambda0
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i2) {
                    this.f$0.lambda$onViewClicked$1(dialogInterface, i2);
                }
            });
            return;
        }
        if (Constant.isHealthRing()) {
            if (YCBTClient.connectState() == 10) {
                DialogUtils.showPromptDialog(this, getString(R.string.hint_start_measuring), new DialogInterface.OnClickListener() { // from class: com.yucheng.smarthealthpro.home.activity.BaseMeasureActivity$$ExternalSyntheticLambda1
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
            playStopMeasure(1);
        } else {
            ToastUtil.getInstance(this).toast(getString(R.string.please_connect_the_device));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewClicked$1(DialogInterface dialogInterface, int i2) {
        this.isStarting = false;
        this.tvStartButton.setText(getString(R.string.health_reminder_of_long_sitting_reminder_time_start_title));
        this.mLottieAnimationView.setRepeatCount(0);
        playStopMeasure(0);
        dialogInterface.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewClicked$2(DialogInterface dialogInterface, int i2) {
        playStopMeasure(1);
        dialogInterface.dismiss();
    }

    @Override // com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        playStopMeasure(0);
    }

    public void playStopMeasure(int onOff) {
        this.onOff = onOff;
        if (onOff == 1) {
            this.tvLottieData.setText("--");
            this.isStarting = true;
            this.tvStartButton.setText(getString(R.string.health_reminder_of_long_sitting_reminder_time_end_title));
            this.mLottieAnimationView.setRepeatCount(-1);
            this.mLottieAnimationView.playAnimation();
        } else {
            this.isStarting = false;
            this.tvStartButton.setText(getString(R.string.health_reminder_of_long_sitting_reminder_time_start_title));
            this.mLottieAnimationView.setRepeatCount(0);
        }
        startMeasure();
        YCBTClient.appStartMeasurement(onOff, getType(), this);
    }

    @Override // com.yucheng.smarthealthpro.framework.BaseActivity
    public void backAction() {
        if (this.isStarting) {
            DialogUtils.showPromptDialog(this, getString(R.string.hint_over_measure), new DialogInterface.OnClickListener() { // from class: com.yucheng.smarthealthpro.home.activity.BaseMeasureActivity$$ExternalSyntheticLambda2
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i2) {
                    this.f$0.lambda$backAction$3(dialogInterface, i2);
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
    public /* synthetic */ void lambda$backAction$3(DialogInterface dialogInterface, int i2) {
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

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        stopMeasure();
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(RealDataResponse realDataResponse) {
        HashMap map = realDataResponse.hashMap;
        int i2 = realDataResponse.f5705i;
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(EventBusSyncData eventBusSyncData) {
        dismissProgressDialog();
        Logger.d("BaseMeasureActivity onGetMessage");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDataResponse(ToAppDataResponse toAppDataResponse) {
        Log.e("result=", "onDataResponse deviceToApp");
        HashMap map = toAppDataResponse.hashMap;
        int i2 = toAppDataResponse.f5706i;
        if (map == null || map.get("dataType") == null) {
            return;
        }
        Log.e("TAG", "hashMap=" + new GsonBuilder().create().toJson(map) + " i=" + i2);
        if (((Integer) map.get("dataType")).intValue() == 1038 && map.get("datas") != null) {
            final byte[] bArr = (byte[]) map.get("datas");
            if (bArr.length <= 1 || bArr[0] != getType()) {
                return;
            }
            runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.BaseMeasureActivity.2
                @Override // java.lang.Runnable
                public void run() {
                    BaseMeasureActivity.this.isStarting = false;
                    BaseMeasureActivity.this.tvStartButton.setText(BaseMeasureActivity.this.getString(R.string.health_reminder_of_long_sitting_reminder_time_start_title));
                    BaseMeasureActivity.this.mLottieAnimationView.setRepeatCount(0);
                    BaseMeasureActivity.this.mLottieAnimationView.clearAnimation();
                    byte b2 = bArr[1];
                    if (b2 == 1) {
                        ToastUtil.getInstance(BaseMeasureActivity.this.getBaseContext()).toast(BaseMeasureActivity.this.getString(R.string.measure_success));
                        BaseMeasureActivity.this.syncData();
                    } else if (b2 == 2) {
                        ToastUtil.getInstance(BaseMeasureActivity.this.getBaseContext()).toast(BaseMeasureActivity.this.getString(R.string.measure_failed));
                    } else {
                        ToastUtil.getInstance(BaseMeasureActivity.this.getBaseContext()).toast(BaseMeasureActivity.this.getString(R.string.measure_cancle));
                    }
                }
            });
        }
    }

    public void sync(int type) {
        DataSyncUtils.INSTANCE.getInstance(getApplicationContext()).getWatchesData(type);
    }
}
