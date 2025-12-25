package com.yucheng.smarthealthpro.me.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarView;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.databinding.ActivityFemalePhysiologicalCycleBinding;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import com.yucheng.smarthealthpro.me.setting.contacts.utils.DpUtil;
import com.yucheng.smarthealthpro.utils.AppDateMgr;
import com.yucheng.smarthealthpro.utils.DebouncingClick;
import java.text.SimpleDateFormat;
import java.util.Locale;

/* loaded from: classes5.dex */
public class MePeriodActivity extends BaseVbActivity<ActivityFemalePhysiologicalCycleBinding> implements CalendarView.OnCalendarSelectListener, CalendarView.OnMonthChangeListener {
    int count = 0;
    private int cycle_value;
    private String date_value;
    private int day_value;
    ImageView ivLastMonth;
    ImageView ivNextMonth;
    CalendarView mCalendarView;
    TextView tvBackToday;
    TextView tvMonth;

    @Override // com.haibin.calendarview.CalendarView.OnCalendarSelectListener
    public void onCalendarOutOfRange(Calendar calendar) {
    }

    @Override // com.haibin.calendarview.CalendarView.OnCalendarSelectListener
    public void onCalendarSelect(Calendar calendar, boolean isClick) {
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) throws Resources.NotFoundException {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) throws Resources.NotFoundException {
        super.onNewIntent(intent);
        initData();
    }

    private void initView() {
        this.ivLastMonth = ((ActivityFemalePhysiologicalCycleBinding) this.mBinding).ivLastMonth;
        this.ivNextMonth = ((ActivityFemalePhysiologicalCycleBinding) this.mBinding).ivNextMonth;
        this.tvBackToday = ((ActivityFemalePhysiologicalCycleBinding) this.mBinding).tvBackToday;
        this.tvMonth = ((ActivityFemalePhysiologicalCycleBinding) this.mBinding).tvMonth;
        this.mCalendarView = ((ActivityFemalePhysiologicalCycleBinding) this.mBinding).calendarView;
        this.ivLastMonth.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.me.activity.MePeriodActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.ivNextMonth.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.me.activity.MePeriodActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.tvBackToday.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.me.activity.MePeriodActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        showBack();
        changeTitle(getString(R.string.setting_menstruation_menstrual_adjustment));
        showRightText(getString(R.string.setting_title), new NavigationBar.MyOnClickListener() { // from class: com.yucheng.smarthealthpro.me.activity.MePeriodActivity.1
            @Override // com.yucheng.smarthealthpro.framework.view.NavigationBar.MyOnClickListener
            public void onClick(View btn) {
                MePeriodActivity.this.startActivity(new Intent(MePeriodActivity.this, (Class<?>) MePeriodSettingActivity.class));
            }
        });
        this.mCalendarView.setOnCalendarSelectListener(this);
        this.mCalendarView.setOnMonthChangeListener(this);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) getTitleTextView().getLayoutParams();
        int iDp2px = (int) DpUtil.dp2px(this, 120.0f);
        layoutParams.setMargins(iDp2px, 0, iDp2px, 0);
        getTitleTextView().setLayoutParams(layoutParams);
    }

    private void initData() throws Resources.NotFoundException {
        int curYear = this.mCalendarView.getCurYear();
        int curMonth = this.mCalendarView.getCurMonth();
        this.tvMonth.setText(curYear + "/" + curMonth);
        this.mCalendarView.scrollToCurrent();
        this.date_value = (String) SharedPreferencesUtils.get(this.context, "menstrual_date_value", "");
        this.day_value = ((Integer) SharedPreferencesUtils.get(this.context, "menstrual_day_value", 5)).intValue();
        this.cycle_value = ((Integer) SharedPreferencesUtils.get(this.context, "menstrual_cycle_value", 28)).intValue();
        setDatas(curYear, curMonth);
    }

    private Calendar getSchemeCalendar(int year, int month, int day, String type) {
        Calendar calendar = new Calendar();
        calendar.setYear(year);
        calendar.setMonth(month);
        calendar.setDay(day);
        calendar.setScheme(type);
        return calendar;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0035  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void setDatas(int r8, int r9) {
        /*
            Method dump skipped, instructions count: 249
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.me.activity.MePeriodActivity.setDatas(int, int):void");
    }

    @Override // com.haibin.calendarview.CalendarView.OnMonthChangeListener
    public void onMonthChange(int year, int month) {
        this.tvMonth.setText(year + "/" + month);
        setDatas(year, month);
    }

    public int sub(String date1, String date2) {
        long time;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(AppDateMgr.DF_YYYY_MM_DD, Locale.ENGLISH);
        try {
            time = simpleDateFormat.parse(date1).getTime() - simpleDateFormat.parse(date2).getTime();
        } catch (Exception e2) {
            e2.printStackTrace();
            time = 0;
        }
        return (int) (time / 86400000);
    }

    public void onViewClicked(View view) throws Resources.NotFoundException {
        if (view.getId() == R.id.iv_last_month) {
            this.mCalendarView.scrollToPre();
        } else if (view.getId() == R.id.iv_next_month) {
            this.mCalendarView.scrollToNext();
        } else if (view.getId() == R.id.tv_back_today) {
            this.mCalendarView.scrollToCurrent();
        }
    }
}
