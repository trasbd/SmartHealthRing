package com.yucheng.smarthealthpro.me.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.databinding.ActivityPeriodSettingBinding;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.home.view.CustomSelectors;
import com.yucheng.smarthealthpro.me.view.CustomYearToDateSelectors;
import com.yucheng.smarthealthpro.utils.AppDateMgr;
import com.yucheng.smarthealthpro.utils.DebouncingClick;
import com.yucheng.ycbtsdk.YCBTClient;
import com.yucheng.ycbtsdk.response.BleDataResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class MePeriodSettingActivity extends BaseVbActivity<ActivityPeriodSettingBinding> {
    private int curr_cycle_value;
    private String curr_date_value;
    private int curr_day_value;
    private CustomSelectors mPeriodDayNumCustomSelectors;
    private CustomSelectors mPeriodLongCustomSelectors;
    private CustomYearToDateSelectors mPeriodTimeCustomYearToDateSelectors;
    TextView tvConfirm;
    TextView tvPeriodDayNum;
    TextView tvPeriodLong;
    TextView tvPeriodTime;
    private ArrayList<String> mPeriodDayNumList = new ArrayList<>();
    private ArrayList<String> mPeriodLongList = new ArrayList<>();

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        this.tvPeriodTime = ((ActivityPeriodSettingBinding) this.mBinding).tvPeriodTime;
        this.tvPeriodDayNum = ((ActivityPeriodSettingBinding) this.mBinding).tvPeriodDayNum;
        this.tvPeriodLong = ((ActivityPeriodSettingBinding) this.mBinding).tvPeriodLong;
        this.tvConfirm = ((ActivityPeriodSettingBinding) this.mBinding).tvConfirm;
        this.tvPeriodTime.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.me.activity.MePeriodSettingActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.tvPeriodDayNum.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.me.activity.MePeriodSettingActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.tvPeriodLong.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.me.activity.MePeriodSettingActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.tvConfirm.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.me.activity.MePeriodSettingActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        showBack();
        changeTitle(getString(R.string.setting_menstruation_setting));
    }

    private void initData() {
        this.curr_date_value = (String) SharedPreferencesUtils.get(this.context, "menstrual_date_value", "");
        this.curr_day_value = ((Integer) SharedPreferencesUtils.get(this.context, "menstrual_day_value", 5)).intValue();
        this.curr_cycle_value = ((Integer) SharedPreferencesUtils.get(this.context, "menstrual_cycle_value", 28)).intValue();
        this.tvPeriodTime.setText(this.curr_date_value);
        this.tvPeriodDayNum.setText(this.curr_day_value + getString(R.string.setting_menstruation_unit));
        this.tvPeriodLong.setText(this.curr_cycle_value + getString(R.string.setting_menstruation_unit));
        for (int i2 = 3; i2 <= 7; i2++) {
            this.mPeriodDayNumList.add(i2 + "");
        }
        for (int i3 = 21; i3 <= 35; i3++) {
            this.mPeriodLongList.add(i3 + "");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getTime(Date date) {
        return new SimpleDateFormat(getString(R.string.default_date_format)).format(date);
    }

    private void initPeriodTimePicker() {
        CustomYearToDateSelectors customYearToDateSelectors = new CustomYearToDateSelectors();
        this.mPeriodTimeCustomYearToDateSelectors = customYearToDateSelectors;
        customYearToDateSelectors.BpLevelPicker(this.curr_date_value, null, null, this.context);
        this.mPeriodTimeCustomYearToDateSelectors.setOnOneSelectorsDataListener(new CustomYearToDateSelectors.OnOneSelectorsDataListener() { // from class: com.yucheng.smarthealthpro.me.activity.MePeriodSettingActivity.1
            @Override // com.yucheng.smarthealthpro.me.view.CustomYearToDateSelectors.OnOneSelectorsDataListener
            public void getSelectorsDataClick(Date date) {
                MePeriodSettingActivity.this.tvPeriodTime.setText(MePeriodSettingActivity.this.getTime(date));
                MePeriodSettingActivity mePeriodSettingActivity = MePeriodSettingActivity.this;
                mePeriodSettingActivity.curr_date_value = mePeriodSettingActivity.getTime(date);
            }
        });
    }

    private void initPeriodDayNumPicker() {
        this.mPeriodDayNumCustomSelectors = new CustomSelectors();
        this.mPeriodDayNumCustomSelectors.BpLevelPicker(this.mPeriodDayNumList, null, null, this.curr_day_value - 3, 1, 1, getString(R.string.setting_menstruation_unit), "", "", false, CustomSelectors.IsShow.TOP_CONFIRM_CANCEL, CustomSelectors.SelectorsDataNum.ONE, this.context);
        this.mPeriodDayNumCustomSelectors.setOnOneSelectorsDataListener(new CustomSelectors.OnOneSelectorsDataListener() { // from class: com.yucheng.smarthealthpro.me.activity.MePeriodSettingActivity.2
            @Override // com.yucheng.smarthealthpro.home.view.CustomSelectors.OnOneSelectorsDataListener
            public void getSelectorsDataClick(String oneValue, int optionsOne) {
                MePeriodSettingActivity.this.tvPeriodDayNum.setText(oneValue + MePeriodSettingActivity.this.getString(R.string.setting_menstruation_unit));
                MePeriodSettingActivity.this.curr_day_value = Integer.parseInt(oneValue);
            }
        });
    }

    private void initPeriodLongPicker() {
        this.mPeriodLongCustomSelectors = new CustomSelectors();
        this.mPeriodLongCustomSelectors.BpLevelPicker(this.mPeriodLongList, null, null, this.curr_cycle_value - 21, 1, 1, getString(R.string.setting_menstruation_unit), "", "", false, CustomSelectors.IsShow.TOP_CONFIRM_CANCEL, CustomSelectors.SelectorsDataNum.ONE, this.context);
        this.mPeriodLongCustomSelectors.setOnOneSelectorsDataListener(new CustomSelectors.OnOneSelectorsDataListener() { // from class: com.yucheng.smarthealthpro.me.activity.MePeriodSettingActivity.3
            @Override // com.yucheng.smarthealthpro.home.view.CustomSelectors.OnOneSelectorsDataListener
            public void getSelectorsDataClick(String oneValue, int optionsOne) {
                MePeriodSettingActivity.this.tvPeriodLong.setText(oneValue + MePeriodSettingActivity.this.getString(R.string.setting_menstruation_unit));
                MePeriodSettingActivity.this.curr_cycle_value = Integer.parseInt(oneValue);
            }
        });
    }

    public void onViewClicked(View view) {
        long time;
        if (view.getId() == R.id.tv_period_time) {
            initPeriodTimePicker();
            return;
        }
        if (view.getId() == R.id.tv_period_day_num) {
            initPeriodDayNumPicker();
            return;
        }
        if (view.getId() == R.id.tv_period_long) {
            initPeriodLongPicker();
            return;
        }
        if (view.getId() == R.id.tv_confirm) {
            try {
                time = new SimpleDateFormat(AppDateMgr.DF_YYYY_MM_DD).parse(this.curr_date_value).getTime() / 1000;
            } catch (ParseException e2) {
                e2.printStackTrace();
                time = 0;
            }
            YCBTClient.appPushFemalePhysiological(time, this.curr_day_value, this.curr_cycle_value, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.activity.MePeriodSettingActivity.4
                @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                public void onDataResponse(int code, float v, HashMap hashMap) {
                    if (code == 0) {
                        MePeriodSettingActivity.this.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.me.activity.MePeriodSettingActivity.4.1
                            @Override // java.lang.Runnable
                            public void run() {
                                SharedPreferencesUtils.put(MePeriodSettingActivity.this.context, "menstrual_date_value", MePeriodSettingActivity.this.curr_date_value);
                                SharedPreferencesUtils.put(MePeriodSettingActivity.this.context, "menstrual_day_value", Integer.valueOf(MePeriodSettingActivity.this.curr_day_value));
                                SharedPreferencesUtils.put(MePeriodSettingActivity.this.context, "menstrual_cycle_value", Integer.valueOf(MePeriodSettingActivity.this.curr_cycle_value));
                                SharedPreferencesUtils.put(MePeriodSettingActivity.this.context, "is_menstrual_setting", true);
                                MePeriodSettingActivity.this.startActivity(new Intent(MePeriodSettingActivity.this, (Class<?>) MePeriodActivity.class));
                                MePeriodSettingActivity.this.finish();
                            }
                        });
                    }
                }
            });
        }
    }
}
