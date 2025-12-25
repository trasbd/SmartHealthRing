package com.yucheng.smarthealthpro.home.activity.running.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.realsil.sdk.dfu.DfuConstants;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.databinding.ActivityRunninghealthgoalsBinding;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.home.view.CustomSelectors;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.DebouncingClick;
import com.yucheng.ycbtsdk.YCBTClient;
import com.yucheng.ycbtsdk.response.BleDataResponse;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class RunningHealthGoalsActivity extends BaseVbActivity<ActivityRunninghealthgoalsBinding> {
    private CustomSelectors mCustomSelectors;
    private int mMovingObject;
    private int mSleepQuality;
    private String movingObject;
    RelativeLayout rlMovingObject;
    RelativeLayout rlSleepQuality;
    private String sleepQuality;
    TextView tvMovingObject;
    TextView tvSleepQuality;
    private ArrayList<String> exerciseList = new ArrayList<>();
    private ArrayList<String> sleepList = new ArrayList<>();
    private Handler mHandler = new Handler() { // from class: com.yucheng.smarthealthpro.home.activity.running.activity.RunningHealthGoalsActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                RunningHealthGoalsActivity.this.tvMovingObject.setText(RunningHealthGoalsActivity.this.movingObject + RunningHealthGoalsActivity.this.getString(R.string.step_unit));
                SharedPreferencesUtils.put(RunningHealthGoalsActivity.this.context, Constant.SpConstKey.TARGET_NUMBER_OF_MOVEMENT_STEPS, Integer.valueOf(Integer.parseInt(RunningHealthGoalsActivity.this.movingObject)));
                return;
            }
            if (msg.what == 2) {
                RunningHealthGoalsActivity.this.tvSleepQuality.setText(RunningHealthGoalsActivity.this.sleepQuality + RunningHealthGoalsActivity.this.getString(R.string.time_hour_unit));
                SharedPreferencesUtils.put(RunningHealthGoalsActivity.this.context, Constant.SpConstKey.TARGET_SLEEP_TIME, RunningHealthGoalsActivity.this.sleepQuality);
            } else if (msg.what == 3) {
                RunningHealthGoalsActivity.this.tvMovingObject.setText("");
                Toast.makeText(RunningHealthGoalsActivity.this.context, RunningHealthGoalsActivity.this.getString(R.string.health_set_failed), 0).show();
            } else if (msg.what == 4) {
                RunningHealthGoalsActivity.this.tvSleepQuality.setText("");
                Toast.makeText(RunningHealthGoalsActivity.this.context, RunningHealthGoalsActivity.this.getString(R.string.health_set_failed), 0).show();
            }
        }
    };

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        this.rlMovingObject = ((ActivityRunninghealthgoalsBinding) this.mBinding).rlMovingObject;
        this.rlSleepQuality = ((ActivityRunninghealthgoalsBinding) this.mBinding).rlSleepQuality;
        this.tvMovingObject = ((ActivityRunninghealthgoalsBinding) this.mBinding).tvMovingObject;
        this.tvSleepQuality = ((ActivityRunninghealthgoalsBinding) this.mBinding).tvSleepQuality;
        this.rlMovingObject.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.running.activity.RunningHealthGoalsActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlSleepQuality.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.running.activity.RunningHealthGoalsActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        changeTitle(getString(R.string.include_bottom_tv_first_button));
        showBack();
    }

    private void initData() {
        try {
            this.mMovingObject = ((Integer) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.TARGET_NUMBER_OF_MOVEMENT_STEPS, Integer.valueOf(DfuConstants.MAX_NOTIFICATION_LOCK_WAIT_TIME))).intValue();
        } catch (Exception e2) {
            e2.printStackTrace();
            this.mMovingObject = DfuConstants.MAX_NOTIFICATION_LOCK_WAIT_TIME;
        }
        this.tvMovingObject.setText(this.mMovingObject + getString(R.string.step_unit));
        try {
            this.mSleepQuality = ((Integer) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.TARGET_SLEEP_TIME, 8)).intValue();
        } catch (Exception e3) {
            e3.printStackTrace();
            this.mSleepQuality = 8;
        }
        this.tvSleepQuality.setText(this.mSleepQuality + getString(R.string.time_hour_unit));
        for (int i2 = 2000; i2 <= 30000; i2 += 1000) {
            this.exerciseList.add(i2 + "");
        }
        for (int i3 = 6; i3 <= 16; i3++) {
            this.sleepList.add(i3 + "");
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    public void onViewClicked(View view) {
        if (view.getId() == R.id.rl_moving_object) {
            CustomSelectors customSelectors = new CustomSelectors();
            this.mCustomSelectors = customSelectors;
            customSelectors.BpLevelPicker(this.exerciseList, null, null, (this.mMovingObject / 1000) - 1, 1, 1, "", "", "", false, CustomSelectors.IsShow.TOP_CONFIRM_CANCEL, CustomSelectors.SelectorsDataNum.ONE, this.context);
            this.mCustomSelectors.setOnOneSelectorsDataListener(new AnonymousClass2());
            return;
        }
        if (view.getId() == R.id.rl_sleep_quality) {
            CustomSelectors customSelectors2 = new CustomSelectors();
            this.mCustomSelectors = customSelectors2;
            customSelectors2.BpLevelPicker(this.sleepList, null, null, this.mSleepQuality - 6, 1, 1, "", "", "", false, CustomSelectors.IsShow.TOP_CONFIRM_CANCEL, CustomSelectors.SelectorsDataNum.ONE, this.context);
            this.mCustomSelectors.setOnOneSelectorsDataListener(new AnonymousClass3());
        }
    }

    /* renamed from: com.yucheng.smarthealthpro.home.activity.running.activity.RunningHealthGoalsActivity$2, reason: invalid class name */
    class AnonymousClass2 implements CustomSelectors.OnOneSelectorsDataListener {
        AnonymousClass2() {
        }

        @Override // com.yucheng.smarthealthpro.home.view.CustomSelectors.OnOneSelectorsDataListener
        public void getSelectorsDataClick(final String oneValue, int optionsOne) {
            Log.i("CustomSelectors", "" + oneValue);
            YCBTClient.settingGoal(0, Integer.parseInt(oneValue), 0, 0, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.home.activity.running.activity.RunningHealthGoalsActivity.2.1
                @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                public void onDataResponse(int code, float ratio, HashMap resultMap) {
                    if (code == 0) {
                        RunningHealthGoalsActivity.this.movingObject = oneValue;
                        new Thread(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.running.activity.RunningHealthGoalsActivity.2.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                RunningHealthGoalsActivity.this.mHandler.sendEmptyMessage(1);
                            }
                        }).start();
                        return;
                    }
                    new Thread(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.running.activity.RunningHealthGoalsActivity.2.1.2
                        @Override // java.lang.Runnable
                        public void run() {
                            RunningHealthGoalsActivity.this.mHandler.sendEmptyMessage(3);
                        }
                    }).start();
                }
            });
        }
    }

    /* renamed from: com.yucheng.smarthealthpro.home.activity.running.activity.RunningHealthGoalsActivity$3, reason: invalid class name */
    class AnonymousClass3 implements CustomSelectors.OnOneSelectorsDataListener {
        AnonymousClass3() {
        }

        @Override // com.yucheng.smarthealthpro.home.view.CustomSelectors.OnOneSelectorsDataListener
        public void getSelectorsDataClick(final String oneValue, int optionsOne) {
            Log.i("CustomSelectors", "" + oneValue);
            YCBTClient.settingGoal(3, 0, Integer.parseInt(oneValue), 0, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.home.activity.running.activity.RunningHealthGoalsActivity.3.1
                @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                public void onDataResponse(int code, float ratio, HashMap resultMap) {
                    if (code == 0) {
                        RunningHealthGoalsActivity.this.sleepQuality = oneValue;
                        new Thread(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.running.activity.RunningHealthGoalsActivity.3.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                RunningHealthGoalsActivity.this.mHandler.sendEmptyMessage(2);
                            }
                        }).start();
                        return;
                    }
                    new Thread(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.running.activity.RunningHealthGoalsActivity.3.1.2
                        @Override // java.lang.Runnable
                        public void run() {
                            RunningHealthGoalsActivity.this.mHandler.sendEmptyMessage(4);
                        }
                    }).start();
                }
            });
        }
    }
}
