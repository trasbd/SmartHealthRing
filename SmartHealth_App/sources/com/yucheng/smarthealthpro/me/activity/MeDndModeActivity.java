package com.yucheng.smarthealthpro.me.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.databinding.ActivityMeDndmodeBinding;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.home.view.CustomSelectors;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.DebouncingClick;
import com.yucheng.ycbtsdk.YCBTClient;
import com.yucheng.ycbtsdk.response.BleDataResponse;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class MeDndModeActivity extends BaseVbActivity<ActivityMeDndmodeBinding> {
    private int endTimeHour;
    private int endTimeMin;
    LinearLayout llStartEnd;
    private CustomSelectors mEndCustomSelectors;
    private CustomSelectors mStartCustomSelectors;
    Switch mSwitchDndMode;
    RelativeLayout rlEndTime;
    RelativeLayout rlStartTime;
    private int startTimeHour;
    private int startTimeMin;
    TextView tvEndTime;
    TextView tvStartTime;
    private ArrayList<String> mFirstHourList = new ArrayList<>();
    private ArrayList<String> mSecondMinuteList = new ArrayList<>();
    private Handler mHandler = new Handler() { // from class: com.yucheng.smarthealthpro.me.activity.MeDndModeActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                MeDndModeActivity.this.llStartEnd.setVisibility(8);
                SharedPreferencesUtils.put(MeDndModeActivity.this.context, Constant.SpConstKey.IS_DND_MODE, Constant.SpConstValue.CLOSE);
                SharedPreferencesUtils.put(MeDndModeActivity.this.context, Constant.SpConstKey.DND_START_TIME_HOUR, Integer.valueOf(MeDndModeActivity.this.startTimeHour));
                SharedPreferencesUtils.put(MeDndModeActivity.this.context, Constant.SpConstKey.DND_START_TIME_MIN, Integer.valueOf(MeDndModeActivity.this.startTimeMin));
                SharedPreferencesUtils.put(MeDndModeActivity.this.context, Constant.SpConstKey.DND_END_TIME_HOUR, Integer.valueOf(MeDndModeActivity.this.endTimeHour));
                SharedPreferencesUtils.put(MeDndModeActivity.this.context, Constant.SpConstKey.DND_END_TIME_MIN, Integer.valueOf(MeDndModeActivity.this.endTimeMin));
                return;
            }
            if (msg.what == 2) {
                MeDndModeActivity.this.llStartEnd.setVisibility(0);
                SharedPreferencesUtils.put(MeDndModeActivity.this.context, Constant.SpConstKey.IS_DND_MODE, Constant.SpConstValue.OPEN);
                SharedPreferencesUtils.put(MeDndModeActivity.this.context, Constant.SpConstKey.DND_START_TIME_HOUR, Integer.valueOf(MeDndModeActivity.this.startTimeHour));
                SharedPreferencesUtils.put(MeDndModeActivity.this.context, Constant.SpConstKey.DND_START_TIME_MIN, Integer.valueOf(MeDndModeActivity.this.startTimeMin));
                SharedPreferencesUtils.put(MeDndModeActivity.this.context, Constant.SpConstKey.DND_END_TIME_HOUR, Integer.valueOf(MeDndModeActivity.this.endTimeHour));
                SharedPreferencesUtils.put(MeDndModeActivity.this.context, Constant.SpConstKey.DND_END_TIME_MIN, Integer.valueOf(MeDndModeActivity.this.endTimeMin));
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
        this.tvStartTime = ((ActivityMeDndmodeBinding) this.mBinding).tvStartTime;
        this.rlStartTime = ((ActivityMeDndmodeBinding) this.mBinding).rlStartTime;
        this.tvEndTime = ((ActivityMeDndmodeBinding) this.mBinding).tvEndTime;
        this.rlEndTime = ((ActivityMeDndmodeBinding) this.mBinding).rlEndTime;
        this.mSwitchDndMode = ((ActivityMeDndmodeBinding) this.mBinding).switchDndMode;
        this.llStartEnd = ((ActivityMeDndmodeBinding) this.mBinding).llStartEnd;
        this.rlStartTime.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.me.activity.MeDndModeActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlEndTime.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.me.activity.MeDndModeActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        changeTitle(getString(R.string.me_my_device_more_settings_dnd_mode_title));
        showBack();
        String str = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.IS_DND_MODE, "");
        if (str != null && !str.isEmpty() && str.equals(Constant.SpConstValue.OPEN)) {
            this.mSwitchDndMode.setChecked(true);
            this.startTimeHour = ((Integer) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.DND_START_TIME_HOUR, 1)).intValue();
            this.startTimeMin = ((Integer) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.DND_START_TIME_MIN, 1)).intValue();
            this.endTimeHour = ((Integer) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.DND_END_TIME_HOUR, 1)).intValue();
            this.endTimeMin = ((Integer) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.DND_END_TIME_MIN, 1)).intValue();
            this.llStartEnd.setVisibility(0);
            this.tvStartTime.setText(format(this.startTimeHour, this.startTimeMin));
            this.tvEndTime.setText(format(this.endTimeHour, this.endTimeMin));
        } else {
            this.mSwitchDndMode.setChecked(false);
            this.llStartEnd.setVisibility(8);
            this.startTimeHour = 23;
            this.startTimeMin = 0;
            this.endTimeHour = 7;
            this.endTimeMin = 30;
            this.tvStartTime.setText(format(23, 0));
            this.tvEndTime.setText(format(this.endTimeHour, this.endTimeMin));
        }
        this.mSwitchDndMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.yucheng.smarthealthpro.me.activity.MeDndModeActivity.2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    MeDndModeActivity.this.startDndMode(1);
                } else {
                    MeDndModeActivity.this.startDndMode(0);
                }
            }
        });
    }

    private String format(int hour, int minu) {
        return (hour < 10 ? new StringBuilder("0") : new StringBuilder("")).append(hour).toString() + ":" + (minu < 10 ? new StringBuilder("0").append(minu) : new StringBuilder("").append(minu)).toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startDndMode(final int on) {
        YCBTClient.settingNotDisturb(on, this.startTimeHour, this.startTimeMin, this.endTimeHour, this.endTimeMin, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.activity.MeDndModeActivity.3
            @Override // com.yucheng.ycbtsdk.response.BleDataResponse
            public void onDataResponse(int code, float ratio, HashMap resultMap) {
                if (code == 0) {
                    MeDndModeActivity.this.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.me.activity.MeDndModeActivity.3.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (on == 0) {
                                MeDndModeActivity.this.mHandler.sendEmptyMessage(1);
                            } else {
                                MeDndModeActivity.this.mHandler.sendEmptyMessage(2);
                            }
                        }
                    });
                }
            }
        });
    }

    private void initData() {
        int i2 = 0;
        int i3 = 0;
        while (i3 < 24) {
            this.mFirstHourList.add((i3 < 10 ? new StringBuilder("0").append(i3) : new StringBuilder("").append(i3)).toString());
            i3++;
        }
        while (i2 < 60) {
            this.mSecondMinuteList.add((i2 < 10 ? new StringBuilder("0") : new StringBuilder("")).append(i2).toString());
            i2++;
        }
    }

    private void initStartTimePicker() {
        CustomSelectors customSelectors = new CustomSelectors();
        this.mStartCustomSelectors = customSelectors;
        customSelectors.BpLevelPicker(this.mFirstHourList, this.mSecondMinuteList, null, this.startTimeHour, this.startTimeMin, 1, ":", "", "", true, CustomSelectors.IsShow.TOP_CONFIRM_CANCEL, CustomSelectors.SelectorsDataNum.TWO, this.context);
        this.mStartCustomSelectors.setOnTwoSelectorsDataListener(new CustomSelectors.OnTwoSelectorsDataListener() { // from class: com.yucheng.smarthealthpro.me.activity.MeDndModeActivity.4
            @Override // com.yucheng.smarthealthpro.home.view.CustomSelectors.OnTwoSelectorsDataListener
            public void getSelectorsDataClick(String oneValue, String twoValue, int optionsOne, int optionsTwo) {
                MeDndModeActivity.this.startTimeHour = Integer.parseInt(oneValue);
                MeDndModeActivity.this.startTimeMin = Integer.parseInt(twoValue);
                MeDndModeActivity.this.tvStartTime.setText(oneValue + ":" + twoValue);
                MeDndModeActivity.this.startDndMode(1);
            }
        });
    }

    private void initEndTimePicker() {
        CustomSelectors customSelectors = new CustomSelectors();
        this.mEndCustomSelectors = customSelectors;
        customSelectors.BpLevelPicker(this.mFirstHourList, this.mSecondMinuteList, null, this.endTimeHour, this.endTimeMin, 1, ":", "", "", true, CustomSelectors.IsShow.TOP_CONFIRM_CANCEL, CustomSelectors.SelectorsDataNum.TWO, this.context);
        this.mEndCustomSelectors.setOnTwoSelectorsDataListener(new CustomSelectors.OnTwoSelectorsDataListener() { // from class: com.yucheng.smarthealthpro.me.activity.MeDndModeActivity.5
            @Override // com.yucheng.smarthealthpro.home.view.CustomSelectors.OnTwoSelectorsDataListener
            public void getSelectorsDataClick(String oneValue, String twoValue, int optionsOne, int optionsTwo) {
                MeDndModeActivity.this.endTimeHour = Integer.parseInt(oneValue);
                MeDndModeActivity.this.endTimeMin = Integer.parseInt(twoValue);
                MeDndModeActivity.this.tvEndTime.setText(oneValue + ":" + twoValue);
                MeDndModeActivity.this.startDndMode(1);
            }
        });
    }

    public void onViewClicked(View view) {
        if (view.getId() == R.id.rl_start_time) {
            initStartTimePicker();
        } else if (view.getId() == R.id.rl_end_time) {
            initEndTimePicker();
        }
    }

    @Override // com.yucheng.smarthealthpro.framework.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
    }
}
