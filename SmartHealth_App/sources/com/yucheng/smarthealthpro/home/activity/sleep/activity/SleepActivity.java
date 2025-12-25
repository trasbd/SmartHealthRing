package com.yucheng.smarthealthpro.home.activity.sleep.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.flyco.tablayout.SlidingTabLayout;
import com.google.android.material.timepicker.TimeModel;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarView;
import com.orhanobut.logger.Logger;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.care.bean.CareSleepWeekMonthBean;
import com.yucheng.smarthealthpro.care.bean.HistorySleepResponse;
import com.yucheng.smarthealthpro.customchart.utils.SleepUtil;
import com.yucheng.smarthealthpro.data.packed.HealthDayData;
import com.yucheng.smarthealthpro.data.packed.HealthPackedData;
import com.yucheng.smarthealthpro.database.room.bean.Sleep;
import com.yucheng.smarthealthpro.database.room.bean.SleepItem;
import com.yucheng.smarthealthpro.databinding.ActivitySleepBinding;
import com.yucheng.smarthealthpro.framework.http.HttpUtils;
import com.yucheng.smarthealthpro.framework.util.Constants;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import com.yucheng.smarthealthpro.home.activity.HealthyActivity;
import com.yucheng.smarthealthpro.home.activity.sleep.adapter.SleepHisListAdapter;
import com.yucheng.smarthealthpro.home.activity.sleep.adapter.SleepTabFragmentAdapter;
import com.yucheng.smarthealthpro.home.activity.sleep.bean.SleepDayInfo;
import com.yucheng.smarthealthpro.home.activity.sleep.bean.SleepHisListBean;
import com.yucheng.smarthealthpro.home.activity.sleep.bean.SleepSummary;
import com.yucheng.smarthealthpro.home.activity.sleep.fragment.SleepTabFragment;
import com.yucheng.smarthealthpro.home.util.HealthDataFilterKt;
import com.yucheng.smarthealthpro.home.util.TimeDateUtil;
import com.yucheng.smarthealthpro.home.view.NoScrollViewPager;
import com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity;
import com.yucheng.smarthealthpro.me.setting.contacts.utils.DpUtil;
import com.yucheng.smarthealthpro.utils.AppDateMgr;
import com.yucheng.smarthealthpro.utils.AppScreenMgr;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.DebouncingClick;
import com.yucheng.smarthealthpro.utils.DensityUtils;
import com.yucheng.smarthealthpro.utils.FlowUtils;
import com.yucheng.smarthealthpro.utils.ShareUtils;
import com.yucheng.smarthealthpro.utils.TimeStampUtils;
import com.yucheng.smarthealthpro.utils.Tools;
import com.yucheng.smarthealthpro.utils.YearToDayListUtils;
import com.yucheng.smarthealthpro.viewmodel.SleepViewModel;
import com.yucheng.ycbtsdk.Constants;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes5.dex */
public class SleepActivity extends BaseVbActivity<ActivitySleepBinding> implements CalendarView.OnCalendarSelectListener, CalendarView.OnMonthChangeListener {
    private String date;
    ImageView ivCalendar;
    ImageView ivDataSecond;
    ImageView ivDataThirdly;
    ImageView ivFirstLeft;
    ImageView ivFirstRight;
    ImageView ivFourthlyLeft;
    ImageView ivFourthlyRight;
    ImageView ivSecondLeft;
    ImageView ivSecondRight;
    LinearLayout llCalendar;
    LinearLayout llDataSecond;
    LinearLayout llDataThirdly;
    LinearLayout llMonth;
    LinearLayout llRem;
    LinearLayout llSecondSleep;
    LinearLayout llStartButton;
    private SleepTabFragmentAdapter mAdapter;
    CalendarView mCalendarView;
    private int mDayNapsTotal;
    private int mDaySleepDeepSleepTotal;
    private int mDaySleepLightSleepTotal;
    private int mDaySleepRemTotal;
    private int mDaySleepWakeCount;
    private float mMonthSleepAverageDeepSleepTotal;
    private float mMonthSleepAverageLightSleepTotal;
    private float mMonthSleepAverageRemTotal;
    private float mMonthSleepAverageWakeCount;
    private int mMonthSleepDeepSleepTotal;
    private int mMonthSleepLightSleepTotal;
    NestedScrollView mNestedScrollView;
    RecyclerView mRecyclerView;
    private List<Sleep> mSleepDb;
    private SleepHisListAdapter mSleepHisListAdapter;
    SlidingTabLayout mSlidingTabLayout;
    private String mToDay;
    private SleepViewModel mViewModel;
    NoScrollViewPager mViewPager;
    private float mWeekSleepAverageDeepSleepTotal;
    private float mWeekSleepAverageLightSleepTotal;
    private float mWeekSleepAverageRemTotal;
    private float mWeekSleepAverageWakeCount;
    private int monthLastDay;
    RelativeLayout rlAnalyse;
    RelativeLayout rlDataFirst;
    RelativeLayout rlFirst;
    RelativeLayout rlFirstSleep;
    RelativeLayout rlFourthly;
    RelativeLayout rlSecond;
    RelativeLayout rl_total_sleep;
    TextView tvAnalyse;
    TextView tvAnalyseData;
    TextView tvBackToday;
    TextView tvCalendar;
    TextView tvDataFirst;
    TextView tvDataFirstUnit;
    TextView tvDataSecond;
    TextView tvDataSecondUnit;
    TextView tvDataThirdly;
    TextView tvDataThirdlyUnit;
    TextView tvFirst;
    TextView tvFirstSleep;
    TextView tvFourthly;
    TextView tvNapsStage;
    TextView tvRem;
    TextView tvSecond;
    TextView tvSecondSleep;
    TextView tvStartButton;
    TextView tvTotalSleep;
    TextView tvTotalSleepText;
    TextView tvYears;
    private int ARROW = 0;
    private List<String> mTitles = new ArrayList();
    private List<HistorySleepResponse.SleepBean> todayHistorySleepList = new ArrayList();
    private List<HistorySleepResponse.SleepBean> yesterdayHistorySleepList = new ArrayList();
    private List<HistorySleepResponse.SleepBean> mLists = new ArrayList();
    private List<SleepHisListBean> mDaySleepAdapterHisListBean = new ArrayList();
    private List<SleepDayInfo> mDaySleepChartDataBeans = new ArrayList();
    private List<SleepHisListBean> mWeekSleepAdapterHisListBean = new ArrayList();
    private List<SleepHisListBean> mWeekSleepChartDataBeans = new ArrayList();
    private List<SleepHisListBean> mMonthSleepAdapterHisListBean = new ArrayList();
    private List<SleepHisListBean> mMonthSleepChartDataBeans = new ArrayList();
    private boolean isCare = false;
    final int SLEEP_LIMIT_HIGH = 57600;
    List<String> napList = new ArrayList();

    public int getMin(float time) {
        return (int) (time / 60.0f);
    }

    @Override // com.haibin.calendarview.CalendarView.OnCalendarSelectListener
    public void onCalendarOutOfRange(Calendar calendar) {
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) throws Resources.NotFoundException {
        super.onCreate(savedInstanceState);
        initView();
        initViewModel();
        initData();
        getCurrData();
    }

    private void initView() {
        this.mSlidingTabLayout = ((ActivitySleepBinding) this.mBinding).includeItemTop.stlTab;
        this.ivCalendar = ((ActivitySleepBinding) this.mBinding).includeItemTop.ivCalendar;
        this.tvCalendar = ((ActivitySleepBinding) this.mBinding).includeItemTop.tvCalendar;
        this.tvBackToday = ((ActivitySleepBinding) this.mBinding).includeItemTop.tvBackToday;
        this.llCalendar = ((ActivitySleepBinding) this.mBinding).includeItemTop.llCalendar;
        this.mViewPager = ((ActivitySleepBinding) this.mBinding).includeItemTop.vpTab;
        this.tvDataFirst = ((ActivitySleepBinding) this.mBinding).tvDataFirst;
        this.tvDataFirstUnit = ((ActivitySleepBinding) this.mBinding).tvDataFirstUnit;
        this.rlDataFirst = ((ActivitySleepBinding) this.mBinding).rlDataFirst;
        this.tvDataSecond = ((ActivitySleepBinding) this.mBinding).tvDataSecond;
        this.ivDataSecond = ((ActivitySleepBinding) this.mBinding).ivDataSecond;
        this.tvDataSecondUnit = ((ActivitySleepBinding) this.mBinding).tvDataSecondUnit;
        this.llDataSecond = ((ActivitySleepBinding) this.mBinding).llDataSecond;
        this.tvDataThirdly = ((ActivitySleepBinding) this.mBinding).tvDataThirdly;
        this.ivDataThirdly = ((ActivitySleepBinding) this.mBinding).ivDataThirdly;
        this.tvDataThirdlyUnit = ((ActivitySleepBinding) this.mBinding).tvDataThirdlyUnit;
        this.llDataThirdly = ((ActivitySleepBinding) this.mBinding).llDataThirdly;
        this.tvStartButton = ((ActivitySleepBinding) this.mBinding).includeItemBottom.tvStartButton;
        this.llStartButton = ((ActivitySleepBinding) this.mBinding).includeItemBottom.llStartButton;
        this.tvAnalyse = ((ActivitySleepBinding) this.mBinding).includeItemBottom.tvAnalyse;
        this.tvAnalyseData = ((ActivitySleepBinding) this.mBinding).includeItemBottom.tvAnalyseData;
        this.rlAnalyse = ((ActivitySleepBinding) this.mBinding).includeItemBottom.rlAnalyse;
        this.ivFirstLeft = ((ActivitySleepBinding) this.mBinding).includeItemBottom.ivFirstLeft;
        this.tvFirst = ((ActivitySleepBinding) this.mBinding).includeItemBottom.tvFirst;
        this.ivFirstRight = ((ActivitySleepBinding) this.mBinding).includeItemBottom.ivFirstRight;
        this.rlFirst = ((ActivitySleepBinding) this.mBinding).includeItemBottom.rlFirst;
        this.ivSecondLeft = ((ActivitySleepBinding) this.mBinding).includeItemBottom.ivSecondLeft;
        this.tvSecond = ((ActivitySleepBinding) this.mBinding).includeItemBottom.tvSecond;
        this.ivSecondRight = ((ActivitySleepBinding) this.mBinding).includeItemBottom.ivSecondRight;
        this.rlSecond = ((ActivitySleepBinding) this.mBinding).includeItemBottom.rlSecond;
        this.ivFourthlyLeft = ((ActivitySleepBinding) this.mBinding).includeItemBottom.ivFourthlyLeft;
        this.tvFourthly = ((ActivitySleepBinding) this.mBinding).includeItemBottom.tvFourthly;
        this.ivFourthlyRight = ((ActivitySleepBinding) this.mBinding).includeItemBottom.ivFourthlyRight;
        this.rlFourthly = ((ActivitySleepBinding) this.mBinding).includeItemBottom.rlFourthly;
        this.mRecyclerView = ((ActivitySleepBinding) this.mBinding).includeItemBottom.recycleView;
        this.mNestedScrollView = ((ActivitySleepBinding) this.mBinding).nsv;
        this.tvYears = ((ActivitySleepBinding) this.mBinding).includeItemCalendar.tvYears;
        this.mCalendarView = ((ActivitySleepBinding) this.mBinding).includeItemCalendar.calendarView;
        this.llMonth = ((ActivitySleepBinding) this.mBinding).includeItemCalendar.llMonth;
        this.llRem = ((ActivitySleepBinding) this.mBinding).llDataRem;
        this.tvRem = ((ActivitySleepBinding) this.mBinding).tvDataRem;
        this.tvTotalSleep = ((ActivitySleepBinding) this.mBinding).tvTotalSleep;
        this.tvTotalSleepText = ((ActivitySleepBinding) this.mBinding).tvTotalSleepText;
        this.rl_total_sleep = ((ActivitySleepBinding) this.mBinding).rlTotalSleep;
        this.tvFirstSleep = ((ActivitySleepBinding) this.mBinding).tvFirstSleep;
        this.tvSecondSleep = ((ActivitySleepBinding) this.mBinding).tvSecondSleep;
        this.tvNapsStage = ((ActivitySleepBinding) this.mBinding).tvNapsStage;
        this.rlFirstSleep = ((ActivitySleepBinding) this.mBinding).rlFirstSleep;
        this.llSecondSleep = ((ActivitySleepBinding) this.mBinding).llSecondSleep;
        this.llCalendar.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.sleep.activity.SleepActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.tvBackToday.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.sleep.activity.SleepActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.llStartButton.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.sleep.activity.SleepActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlAnalyse.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.sleep.activity.SleepActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlFirst.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.sleep.activity.SleepActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlSecond.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.sleep.activity.SleepActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlFourthly.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.sleep.activity.SleepActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.llMonth.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.sleep.activity.SleepActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        changeTitle(getString(R.string.home_sleep_title));
        showBack();
        showRightImage(R.mipmap.topbar_ic_share, new NavigationBar.MyOnClickListener() { // from class: com.yucheng.smarthealthpro.home.activity.sleep.activity.SleepActivity.1
            @Override // com.yucheng.smarthealthpro.framework.view.NavigationBar.MyOnClickListener
            public void onClick(View btn) {
                ShareUtils.share(SleepActivity.this);
            }
        });
        int statusHeight = AppScreenMgr.getStatusHeight(this.context);
        this.llMonth.setPadding(0, DensityUtils.dip2px(this.context, 50.0f) + statusHeight, 0, 0);
        if (Constant.isTechFeel()) {
            this.rlSecond.setVisibility(8);
        }
        String stringExtra = getIntent().getStringExtra("care");
        if (stringExtra != null && stringExtra.equals(getString(R.string.care_title))) {
            this.isCare = true;
            this.rlFirst.setVisibility(8);
            this.rlSecond.setVisibility(8);
        } else {
            this.tvFirst.setText(getString(R.string.include_bottom_tv_first_button));
            this.tvSecond.setText(getString(R.string.include_bottom_tv_second_button));
        }
        this.tvDataFirstUnit.setText(getString(R.string.home_sleep_waking_unit));
        this.tvDataSecondUnit.setText(getString(R.string.home_sleep_light_sleep_unit));
        this.tvDataThirdlyUnit.setText(getString(R.string.home_sleep_deep_sleep_unit));
        this.llStartButton.setVisibility(8);
        this.tvAnalyse.setText(getString(R.string.home_sleep_analyse_title_tv));
        this.tvFourthly.setText(getString(R.string.include_bottom_tv_fourthly_button));
        this.ivFourthlyRight.setBackground(ResourcesCompat.getDrawable(getResources(), R.mipmap.list_ic_arrow_n, null));
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
    }

    private void initViewModel() {
        this.mViewModel = (SleepViewModel) new ViewModelProvider(this).get(SleepViewModel.class);
        FlowUtils.INSTANCE.collectFlow(this, this.mViewModel.getSleepDataFlow(), new FlowUtils.FlowCollector<HealthDayData<Sleep>>() { // from class: com.yucheng.smarthealthpro.home.activity.sleep.activity.SleepActivity.2
            @Override // com.yucheng.smarthealthpro.utils.FlowUtils.FlowCollector
            public void collect(HealthDayData<Sleep> data) throws Resources.NotFoundException, NumberFormatException {
                SleepActivity.this.onThatVeryDayData(data.getDay(), data.getData());
            }
        });
        FlowUtils.INSTANCE.collectFlow(this, this.mViewModel.getSleepPackedDataFlow(), new FlowUtils.FlowCollector<HealthPackedData<Sleep>>() { // from class: com.yucheng.smarthealthpro.home.activity.sleep.activity.SleepActivity.3
            @Override // com.yucheng.smarthealthpro.utils.FlowUtils.FlowCollector
            public void collect(HealthPackedData<Sleep> packedData) {
                if (packedData.getDayCount() == 7) {
                    SleepActivity.this.onDataByDays(7, packedData.getData());
                } else if (packedData.getDayCount() == 30) {
                    SleepActivity.this.onDataByDays(30, packedData.getData());
                }
            }
        });
    }

    private void initData() throws Resources.NotFoundException {
        this.mToDay = TimeStampUtils.getToDay();
        this.mTitles.add(getString(R.string.date_month_unit));
        this.mTitles.add(getString(R.string.date_week_unit));
        this.mTitles.add(getString(R.string.date_day_unit));
        setRecycleView();
        initViewPager();
        initMonth();
    }

    private void getCurrData() {
        if (this.isCare) {
            ArrayList<String> pastStringArray = YearToDayListUtils.getPastStringArray(this.mToDay, 6);
            ArrayList<String> pastStringArray2 = YearToDayListUtils.getPastStringArray(this.mToDay, 29);
            getNetSleep(pastStringArray.get(0), pastStringArray.get(0), pastStringArray.get(6), 7);
            getNetSleep(pastStringArray2.get(0), pastStringArray2.get(0), pastStringArray2.get(29), 30);
            return;
        }
        new Thread(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.sleep.activity.SleepActivity.4
            @Override // java.lang.Runnable
            public void run() {
                SleepActivity.this.getWeekData();
                SleepActivity.this.getMonthData();
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initViewPager() throws Resources.NotFoundException {
        if (isFinishing()) {
            return;
        }
        SleepTabFragmentAdapter sleepTabFragmentAdapter = new SleepTabFragmentAdapter(getSupportFragmentManager(), new SleepTabFragmentAdapter.FragmentCreator() { // from class: com.yucheng.smarthealthpro.home.activity.sleep.activity.SleepActivity.5
            @Override // com.yucheng.smarthealthpro.home.activity.sleep.adapter.SleepTabFragmentAdapter.FragmentCreator
            public Fragment createFragment(String data, int position) {
                return SleepTabFragment.newInstance(data.toString(), position, SleepActivity.this.mNestedScrollView, SleepActivity.this.monthLastDay, SleepActivity.this.mDaySleepChartDataBeans, SleepActivity.this.mWeekSleepChartDataBeans, SleepActivity.this.mMonthSleepChartDataBeans);
            }

            @Override // com.yucheng.smarthealthpro.home.activity.sleep.adapter.SleepTabFragmentAdapter.FragmentCreator
            public String createTitle(String data) {
                return Html.fromHtml(data).toString();
            }
        });
        this.mAdapter = sleepTabFragmentAdapter;
        this.mViewPager.setAdapter(sleepTabFragmentAdapter);
        this.mAdapter.notifyDataSetChanged();
        this.mViewPager.setOffscreenPageLimit(this.mDaySleepAdapterHisListBean.size() - 1);
        this.mAdapter.setData(this.mTitles);
        this.mSlidingTabLayout.setViewPager(this.mViewPager, (String[]) this.mTitles.toArray(new String[0]));
        this.mSlidingTabLayout.setCurrentTab(2, true);
        this.mViewPager.addOnPageChangeListener(new OnPageChangeListenerImpl());
    }

    private void updateWakeCount() {
        int i2 = 0;
        SleepDayInfo sleepDayInfo = null;
        for (SleepDayInfo sleepDayInfo2 : this.mDaySleepChartDataBeans) {
            if (sleepDayInfo2.type == 4) {
                i2++;
            }
            if (sleepDayInfo != null && sleepDayInfo2.beginTime - sleepDayInfo.endTime > 1 && sleepDayInfo.type != 4) {
                i2++;
            }
            sleepDayInfo = sleepDayInfo2;
        }
        this.mDaySleepWakeCount = i2;
    }

    private class OnPageChangeListenerImpl implements ViewPager.OnPageChangeListener {
        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int state) {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        private OnPageChangeListenerImpl() {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int position) {
            SleepActivity.this.mViewPager.setCurrentItem(position);
            if (position == 0) {
                SleepActivity.this.freshMonthData();
            } else if (position == 1) {
                SleepActivity.this.freshWeekData();
            } else {
                if (position != 2) {
                    return;
                }
                SleepActivity.this.freshDayData();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void freshDayData() {
        this.tvBackToday.setVisibility(8);
        this.llCalendar.setVisibility(0);
        if (this.mDaySleepDeepSleepTotal != 0 || this.mDaySleepLightSleepTotal != 0) {
            this.tvDataFirst.setText(this.mDaySleepWakeCount + "");
            this.tvDataSecond.setText(parseTime(this.mDaySleepLightSleepTotal));
            this.tvDataThirdly.setText(parseTime(this.mDaySleepDeepSleepTotal));
            this.tvTotalSleep.setText(parseDayTotalTime());
            this.tvTotalSleepText.setText(R.string.sleep_report_length_of_time);
            this.tvRem.setText(parseTime(this.mDaySleepRemTotal));
            int i2 = this.mDaySleepDeepSleepTotal;
            dataAnalysis(SleepUtil.getState(this.mDaySleepLightSleepTotal + i2 + this.mDaySleepRemTotal, i2));
        } else {
            this.tvDataFirst.setText("--");
            this.tvDataSecond.setText("--");
            this.tvDataThirdly.setText("--");
            this.tvRem.setText("--");
            this.mDaySleepDeepSleepTotal = 0;
            this.mDaySleepLightSleepTotal = 0;
            dataAnalysis(-1.0f);
            this.tvTotalSleep.setText("--");
        }
        this.mSleepHisListAdapter.setList(this.mDaySleepAdapterHisListBean);
        this.mSleepHisListAdapter.notifyDataSetChanged();
        this.tvDataFirstUnit.setText(getString(R.string.sleep_waking_unit));
        this.tvDataSecondUnit.setText(getString(R.string.light_sleep));
        this.tvDataThirdlyUnit.setText(getString(R.string.deep_sleep));
        refreshNaps();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void freshWeekData() {
        this.tvBackToday.setVisibility(0);
        this.llCalendar.setVisibility(8);
        List<SleepHisListBean> list = this.mWeekSleepAdapterHisListBean;
        if (list != null && list.size() > 0) {
            this.tvDataFirst.setText(((int) this.mWeekSleepAverageWakeCount) + "");
            this.tvDataSecond.setText(parseTime((int) this.mWeekSleepAverageLightSleepTotal));
            this.tvDataThirdly.setText(parseTime((int) this.mWeekSleepAverageDeepSleepTotal));
            this.tvRem.setText(parseTime((int) this.mWeekSleepAverageRemTotal));
            this.tvTotalSleep.setText(parseWeekTotalTime());
            this.tvTotalSleepText.setText(R.string.sleep_report_average_sleep_duration);
            float f2 = this.mWeekSleepAverageLightSleepTotal;
            float f3 = this.mWeekSleepAverageDeepSleepTotal;
            dataAnalysis(SleepUtil.getState((long) (f2 + f3 + this.mWeekSleepAverageRemTotal), (long) f3));
        } else {
            this.tvDataFirst.setText("--");
            this.tvDataSecond.setText("--");
            this.tvDataThirdly.setText("--");
            this.tvRem.setText("--");
            this.mWeekSleepAverageLightSleepTotal = 0.0f;
            this.mWeekSleepAverageDeepSleepTotal = 0.0f;
            dataAnalysis(-1.0f);
        }
        this.mSleepHisListAdapter.setList(this.mWeekSleepAdapterHisListBean);
        this.mSleepHisListAdapter.notifyDataSetChanged();
        this.tvDataFirstUnit.setText(getString(R.string.home_sleep_waking_unit));
        this.tvDataSecondUnit.setText(getString(R.string.home_sleep_light_sleep_unit));
        this.tvDataThirdlyUnit.setText(getString(R.string.home_sleep_deep_sleep_unit));
        hideNapsUi();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void freshMonthData() {
        this.tvBackToday.setVisibility(0);
        this.llCalendar.setVisibility(8);
        List<SleepHisListBean> list = this.mMonthSleepAdapterHisListBean;
        if (list != null && list.size() > 0) {
            this.tvDataFirst.setText(((int) this.mMonthSleepAverageWakeCount) + "");
            this.tvDataSecond.setText(parseTime((int) this.mMonthSleepAverageLightSleepTotal));
            this.tvDataThirdly.setText(parseTime((int) this.mMonthSleepAverageDeepSleepTotal));
            this.tvRem.setText(parseTime((int) this.mMonthSleepAverageRemTotal));
            this.tvTotalSleep.setText(parseMonthTotalTime());
            this.tvTotalSleepText.setText(R.string.sleep_report_average_sleep_duration);
            float f2 = this.mMonthSleepAverageLightSleepTotal;
            float f3 = this.mMonthSleepAverageDeepSleepTotal;
            dataAnalysis(SleepUtil.getState((long) (f2 + f3 + this.mMonthSleepAverageRemTotal), (long) f3));
        } else {
            this.tvDataFirst.setText("--");
            this.tvDataSecond.setText("--");
            this.tvDataThirdly.setText("--");
            this.tvRem.setText("--");
            this.mMonthSleepAverageLightSleepTotal = 0.0f;
            this.mMonthSleepAverageDeepSleepTotal = 0.0f;
            dataAnalysis(-1.0f);
        }
        this.mSleepHisListAdapter.setList(this.mMonthSleepAdapterHisListBean);
        this.mSleepHisListAdapter.notifyDataSetChanged();
        this.tvDataFirstUnit.setText(getString(R.string.home_sleep_waking_unit));
        this.tvDataSecondUnit.setText(getString(R.string.home_sleep_light_sleep_unit));
        this.tvDataThirdlyUnit.setText(getString(R.string.home_sleep_deep_sleep_unit));
        hideNapsUi();
    }

    public void dataAnalysis(float index) {
        if (index >= 3.0f) {
            this.tvAnalyseData.setText(getText(R.string.home_sleep_analyse_normal));
        } else if (index > 0.0f && index <= 2.0f) {
            this.tvAnalyseData.setText(getText(R.string.home_sleep_analyse_sleep_debt));
        } else {
            this.tvAnalyseData.setText(new StringBuffer().append(getString(R.string.sleep_empty_hint)).append("\n\n").append(getString(R.string.sleep_empty_explanation)).append(StringUtils.LF).append(getString(R.string.sleep_empty_reason_1)).append(StringUtils.LF).append(getString(R.string.sleep_empty_reason_2)).append(StringUtils.LF).append(getString(R.string.sleep_empty_reason_3)).append(StringUtils.LF).append(getString(R.string.sleep_empty_reason_4)).toString());
        }
    }

    private void setRecycleView() {
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        SleepHisListAdapter sleepHisListAdapter = new SleepHisListAdapter(R.layout.item_sleep_his_list);
        this.mSleepHisListAdapter = sleepHisListAdapter;
        sleepHisListAdapter.addData((Collection) this.mDaySleepAdapterHisListBean);
        this.mRecyclerView.setAdapter(this.mSleepHisListAdapter);
        this.mRecyclerView.setNestedScrollingEnabled(false);
    }

    private void initMonth() throws Resources.NotFoundException {
        this.mCalendarView.setOnCalendarSelectListener(this);
        this.mCalendarView.setOnMonthChangeListener(this);
        this.mCalendarView.scrollToCurrent();
        this.mCalendarView.setOnCalendarInterceptListener(new CalendarView.OnCalendarInterceptListener() { // from class: com.yucheng.smarthealthpro.home.activity.sleep.activity.SleepActivity.6
            @Override // com.haibin.calendarview.CalendarView.OnCalendarInterceptListener
            public void onCalendarInterceptClick(Calendar calendar, boolean isClick) {
            }

            @Override // com.haibin.calendarview.CalendarView.OnCalendarInterceptListener
            public boolean onCalendarIntercept(Calendar calendar) {
                return !YearToDayListUtils.isMidDate(YearToDayListUtils.getPastDate(30, new Date()), new StringBuilder().append(calendar.getYear()).append("-").append(String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(calendar.getMonth()))).append("-").append(String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(calendar.getDay()))).toString(), new Date());
            }
        });
        if (Constant.isTechFeel()) {
            this.tvYears.setText(String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(this.mCalendarView.getCurMonth())) + "/" + this.mCalendarView.getCurYear());
        } else {
            this.tvYears.setText(this.mCalendarView.getCurYear() + "/" + String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(this.mCalendarView.getCurMonth())));
        }
    }

    @Override // com.haibin.calendarview.CalendarView.OnCalendarSelectListener
    public void onCalendarSelect(Calendar calendar, boolean isClick) {
        this.monthLastDay = YearToDayListUtils.getMonthLastDay(calendar.getYear(), calendar.getMonth());
        this.date = TimeDateUtil.intToStr(calendar.getYear(), calendar.getMonth(), calendar.getDay());
        initDayData();
        if (this.isCare) {
            this.mLists.clear();
            this.todayHistorySleepList.clear();
            this.yesterdayHistorySleepList.clear();
            getDaySleep(this.date, 0);
        } else {
            this.mViewModel.getDayData(this.date, YearToDayListUtils.getPastStringArray(this.date, 1).get(0));
        }
        this.tvCalendar.setText(calendar.getMonth() + "/" + calendar.getDay());
        this.llMonth.setVisibility(8);
    }

    @Override // com.haibin.calendarview.CalendarView.OnMonthChangeListener
    public void onMonthChange(int year, int month) {
        if (Constant.isTechFeel()) {
            this.tvYears.setText(String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(month)) + "/" + year);
        } else {
            this.tvYears.setText(year + "/" + String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(month)));
        }
    }

    public void onThatVeryDayData(String thatVeryDay, List<Sleep> data) throws Resources.NotFoundException, NumberFormatException {
        getDaySleepData(thatVeryDay, 1, data);
        getDaySleepData(thatVeryDay, 0, data);
        if (this.mDaySleepAdapterHisListBean.size() > 0 && this.mDaySleepAdapterHisListBean.get(0).getDeepSleepCount() == 65535) {
            this.llRem.setVisibility(0);
        } else {
            this.llRem.setVisibility(8);
        }
        updateWakeCount();
        initViewPager();
        Collections.reverse(this.mDaySleepAdapterHisListBean);
        this.mSleepHisListAdapter.setList(this.mDaySleepAdapterHisListBean);
        this.mSleepHisListAdapter.notifyDataSetChanged();
    }

    protected void hideNapsUi() {
        this.rlFirstSleep.setVisibility(0);
        this.tvFirstSleep.setVisibility(8);
        this.llSecondSleep.setVisibility(8);
        this.tvSecondSleep.setVisibility(8);
    }

    protected void refreshNaps() {
        int i2 = this.mDaySleepDeepSleepTotal + this.mDaySleepLightSleepTotal + this.mDaySleepRemTotal;
        if (this.napList.isEmpty()) {
            this.tvFirstSleep.setVisibility(8);
            this.tvSecondSleep.setVisibility(8);
            this.llSecondSleep.setVisibility(8);
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int i3 = 0; i3 < this.napList.size(); i3++) {
            sb.append(this.napList.get(i3));
            if (i3 != this.napList.size() - 1) {
                sb.append(StringUtils.LF);
            }
        }
        this.tvNapsStage.setText(sb.toString());
        this.tvFirstSleep.setVisibility(0);
        this.llSecondSleep.setVisibility(0);
        if (i2 > 0) {
            this.rlFirstSleep.setVisibility(0);
            this.tvSecondSleep.setVisibility(0);
        } else {
            this.rlFirstSleep.setVisibility(8);
            this.tvSecondSleep.setVisibility(8);
        }
    }

    private void initDayData() {
        this.mDaySleepAdapterHisListBean.clear();
        this.mDaySleepChartDataBeans.clear();
        this.mDaySleepWakeCount = 0;
        this.mDaySleepDeepSleepTotal = 0;
        this.mDaySleepLightSleepTotal = 0;
        this.mDaySleepRemTotal = 0;
        this.mDayNapsTotal = 0;
        this.napList.clear();
    }

    private void getDaySleepData(String thatVeryDay, int past, List<Sleep> data) throws NumberFormatException {
        int i2 = past;
        try {
            String pastDateString = YearToDayListUtils.getPastDateString(i2, new SimpleDateFormat(AppDateMgr.DF_YYYY_MM_DD).parse(thatVeryDay));
            List<Sleep> listFilterSleepByDate = HealthDataFilterKt.filterSleepByDate(data, pastDateString);
            this.mSleepDb = listFilterSleepByDate;
            if (listFilterSleepByDate != null) {
                Logger.d("chong-------mSleepDb==" + this.mSleepDb.size());
                int i3 = 0;
                while (i3 < this.mSleepDb.size()) {
                    Sleep sleep = this.mSleepDb.get(i3);
                    if (TimeStampUtils.dateForStringYearToDate(TimeStampUtils.longStampForDate(sleep.getStartTime())).equals(pastDateString)) {
                        Integer.parseInt(TimeStampUtils.dateForStringToHourDate(TimeStampUtils.longStampForDate(sleep.getStartTime())));
                        Integer.parseInt(TimeStampUtils.dateForStringToHourDate(TimeStampUtils.longStampForDate(sleep.getEndTime())));
                        Logger.d("chong---------startTime==" + sleep.getStartTime());
                        SleepSummary sleepSummary = sleep.getSleepItems() == null ? new SleepSummary() : HealthDataFilterKt.sleepTimeSummary(sleep.getSleepItems());
                        int deepSleepTime = this.mDaySleepDeepSleepTotal + sleepSummary.getDeepSleepTime();
                        int lightSleepTime = this.mDaySleepLightSleepTotal + sleepSummary.getLightSleepTime();
                        int remTime = this.mDaySleepRemTotal + sleepSummary.getRemTime();
                        sleepSummary.getNapsTime();
                        if (deepSleepTime + lightSleepTime + remTime <= 57600 && SleepUtil.isTodaySleep(sleep, i2)) {
                            this.mDaySleepDeepSleepTotal += sleepSummary.getDeepSleepTime();
                            this.mDaySleepLightSleepTotal += sleepSummary.getLightSleepTime();
                            this.mDaySleepRemTotal += sleepSummary.getRemTime();
                            this.mDayNapsTotal += sleepSummary.getNapsTime();
                            this.mDaySleepAdapterHisListBean.add(new SleepHisListBean(sleep.getStartTime(), sleep.getEndTime(), sleep.getDeepSleepTotal() + sleep.getLightSleepTotal(), sleep.getDeepSleepTotal(), sleep.getLightSleepTotal(), sleep.getDeepSleepCount(), sleep.getLightSleepCount(), sleep.getWakeCount(), "", sleep.getRemTotal(), sleep.getWakeDuration(), false));
                            setDayCharData(sleep.getSleepItems());
                        }
                    }
                    i3++;
                    i2 = past;
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x00a8 A[Catch: Exception -> 0x00d2, TryCatch #0 {Exception -> 0x00d2, blocks: (B:14:0x0032, B:16:0x0089, B:19:0x00a8, B:21:0x00b0), top: B:27:0x0032 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void setDayCharData(java.util.List<com.yucheng.smarthealthpro.database.room.bean.SleepItem> r15) {
        /*
            r14 = this;
            java.lang.String r0 = ":"
            java.lang.String r1 = "HH:mm"
            if (r15 != 0) goto L7
            return
        L7:
            r2 = 0
            r3 = r2
        L9:
            int r4 = r15.size()
            if (r3 >= r4) goto Lda
            java.lang.Object r4 = r15.get(r3)
            com.yucheng.smarthealthpro.database.room.bean.SleepItem r4 = (com.yucheng.smarthealthpro.database.room.bean.SleepItem) r4
            long r5 = r4.getTime()
            int r7 = r4.getMode()
            r7 = r7 & 15
            if (r7 != 0) goto L22
            r7 = 2
        L22:
            int r8 = r4.getDuration()
            r9 = 9999999999(0x2540be3ff, double:4.940656458E-314)
            int r9 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
            r10 = 1000(0x3e8, double:4.94E-321)
            if (r9 >= 0) goto L32
            long r5 = r5 * r10
        L32:
            com.yucheng.smarthealthpro.home.activity.sleep.bean.SleepDayInfo r9 = new com.yucheng.smarthealthpro.home.activity.sleep.bean.SleepDayInfo     // Catch: java.lang.Exception -> Ld2
            r9.<init>()     // Catch: java.lang.Exception -> Ld2
            java.lang.Long r12 = java.lang.Long.valueOf(r5)     // Catch: java.lang.Exception -> Ld2
            java.lang.String r12 = com.yucheng.smarthealthpro.customchart.sleep.SleepBarChartUtils.transferLongToDate(r1, r12)     // Catch: java.lang.Exception -> Ld2
            r9.beginTimes = r12     // Catch: java.lang.Exception -> Ld2
            long r12 = (long) r8     // Catch: java.lang.Exception -> Ld2
            long r12 = r12 * r10
            long r12 = r12 + r5
            java.lang.Long r8 = java.lang.Long.valueOf(r12)     // Catch: java.lang.Exception -> Ld2
            java.lang.String r8 = com.yucheng.smarthealthpro.customchart.sleep.SleepBarChartUtils.transferLongToDate(r1, r8)     // Catch: java.lang.Exception -> Ld2
            r9.endTimes = r8     // Catch: java.lang.Exception -> Ld2
            java.lang.String r8 = r9.beginTimes     // Catch: java.lang.Exception -> Ld2
            java.lang.String[] r8 = r8.split(r0)     // Catch: java.lang.Exception -> Ld2
            java.lang.String r10 = r9.endTimes     // Catch: java.lang.Exception -> Ld2
            java.lang.String[] r10 = r10.split(r0)     // Catch: java.lang.Exception -> Ld2
            r11 = r8[r2]     // Catch: java.lang.Exception -> Ld2
            int r11 = java.lang.Integer.parseInt(r11)     // Catch: java.lang.Exception -> Ld2
            int r11 = r11 * 60
            r12 = 1
            r8 = r8[r12]     // Catch: java.lang.Exception -> Ld2
            int r8 = java.lang.Integer.parseInt(r8)     // Catch: java.lang.Exception -> Ld2
            int r11 = r11 + r8
            r9.beginTime = r11     // Catch: java.lang.Exception -> Ld2
            r8 = r10[r2]     // Catch: java.lang.Exception -> Ld2
            int r8 = java.lang.Integer.parseInt(r8)     // Catch: java.lang.Exception -> Ld2
            int r8 = r8 * 60
            r10 = r10[r12]     // Catch: java.lang.Exception -> Ld2
            int r10 = java.lang.Integer.parseInt(r10)     // Catch: java.lang.Exception -> Ld2
            int r8 = r8 + r10
            r9.endTime = r8     // Catch: java.lang.Exception -> Ld2
            r9.time = r5     // Catch: java.lang.Exception -> Ld2
            int r4 = r4.getDuration()     // Catch: java.lang.Exception -> Ld2
            r9.duration = r4     // Catch: java.lang.Exception -> Ld2
            r9.type = r7     // Catch: java.lang.Exception -> Ld2
            if (r3 <= 0) goto La8
            int r4 = r3 + (-1)
            java.lang.Object r8 = r15.get(r4)     // Catch: java.lang.Exception -> Ld2
            com.yucheng.smarthealthpro.database.room.bean.SleepItem r8 = (com.yucheng.smarthealthpro.database.room.bean.SleepItem) r8     // Catch: java.lang.Exception -> Ld2
            long r10 = r8.getTime()     // Catch: java.lang.Exception -> Ld2
            java.lang.Object r4 = r15.get(r4)     // Catch: java.lang.Exception -> Ld2
            com.yucheng.smarthealthpro.database.room.bean.SleepItem r4 = (com.yucheng.smarthealthpro.database.room.bean.SleepItem) r4     // Catch: java.lang.Exception -> Ld2
            int r4 = r4.getDuration()     // Catch: java.lang.Exception -> Ld2
            int r4 = r4 * 1000
            long r12 = (long) r4     // Catch: java.lang.Exception -> Ld2
            long r10 = r10 + r12
            int r4 = (r5 > r10 ? 1 : (r5 == r10 ? 0 : -1))
            if (r4 >= 0) goto La8
            goto Ld6
        La8:
            java.util.List<com.yucheng.smarthealthpro.home.activity.sleep.bean.SleepDayInfo> r4 = r14.mDaySleepChartDataBeans     // Catch: java.lang.Exception -> Ld2
            r4.add(r9)     // Catch: java.lang.Exception -> Ld2
            r4 = 5
            if (r7 != r4) goto Ld6
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> Ld2
            r4.<init>()     // Catch: java.lang.Exception -> Ld2
            java.lang.String r5 = r9.beginTimes     // Catch: java.lang.Exception -> Ld2
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch: java.lang.Exception -> Ld2
            java.lang.String r5 = "~"
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch: java.lang.Exception -> Ld2
            java.lang.String r5 = r9.endTimes     // Catch: java.lang.Exception -> Ld2
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch: java.lang.Exception -> Ld2
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Exception -> Ld2
            java.util.List<java.lang.String> r5 = r14.napList     // Catch: java.lang.Exception -> Ld2
            r5.add(r4)     // Catch: java.lang.Exception -> Ld2
            goto Ld6
        Ld2:
            r4 = move-exception
            r4.printStackTrace()
        Ld6:
            int r3 = r3 + 1
            goto L9
        Lda:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.home.activity.sleep.activity.SleepActivity.setDayCharData(java.util.List):void");
    }

    public void getWeekData() {
        this.mWeekSleepAdapterHisListBean.clear();
        this.mWeekSleepChartDataBeans.clear();
        this.mWeekSleepAverageDeepSleepTotal = 0.0f;
        this.mWeekSleepAverageLightSleepTotal = 0.0f;
        this.mWeekSleepAverageRemTotal = 0.0f;
        this.mWeekSleepAverageWakeCount = 0.0f;
        this.mViewModel.getPackedDayData(YearToDayListUtils.getPastStringArray(this.mToDay, 7).get(0), 7);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00b9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onDataByDays(int r50, java.util.List<com.yucheng.smarthealthpro.database.room.bean.Sleep> r51) {
        /*
            Method dump skipped, instructions count: 724
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.home.activity.sleep.activity.SleepActivity.onDataByDays(int, java.util.List):void");
    }

    public void getMonthData() {
        this.mMonthSleepAdapterHisListBean.clear();
        this.mMonthSleepChartDataBeans.clear();
        this.mMonthSleepAverageDeepSleepTotal = 0.0f;
        this.mMonthSleepAverageLightSleepTotal = 0.0f;
        this.mMonthSleepAverageRemTotal = 0.0f;
        this.mMonthSleepAverageWakeCount = 0.0f;
        this.mViewModel.getPackedDayData(YearToDayListUtils.getPastStringArray(this.mToDay, 30).get(0), 30);
        runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.sleep.activity.SleepActivity.7
            @Override // java.lang.Runnable
            public void run() throws Resources.NotFoundException {
                SleepActivity.this.initViewPager();
            }
        });
    }

    public void getDaySleep(final String dateTime, final int type) {
        HashMap map = new HashMap();
        map.put("userId", getIntent().getExtras().getString(Constant.SpConstKey.DEV_ID));
        map.put("day", dateTime);
        HttpUtils.getInstance().postMsgAsynHttp(this, Constants.sleepDayUrl, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.home.activity.sleep.activity.SleepActivity.8
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                if (result != null) {
                    try {
                        HistorySleepResponse historySleepResponse = (HistorySleepResponse) new Gson().fromJson(result, HistorySleepResponse.class);
                        if (historySleepResponse != null) {
                            List<HistorySleepResponse.SleepBean> list = historySleepResponse.data;
                            if (list != null && list.size() > 0) {
                                SleepActivity.this.mLists.addAll(Tools.sortListSleep(list));
                            }
                            if (type == 0) {
                                SleepActivity.this.getDaySleep(YearToDayListUtils.getPastDateString(1, new SimpleDateFormat(AppDateMgr.DF_YYYY_MM_DD).parse(dateTime)), 1);
                                return;
                            }
                            SleepActivity sleepActivity = SleepActivity.this;
                            sleepActivity.mLists = Tools.removeSleepDuplicate(sleepActivity.GetFixedDataOfSleep(sleepActivity.mLists, SleepActivity.this.date));
                            SleepActivity.this.setDayData();
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDayData() throws Resources.NotFoundException {
        Iterator<HistorySleepResponse.SleepBean> it2 = this.mLists.iterator();
        while (it2.hasNext()) {
            HistorySleepResponse.SleepBean next = it2.next();
            List<SleepItem> arrayList = new ArrayList<>();
            try {
                arrayList = (List) new Gson().fromJson(next.mlist, new TypeToken<List<SleepItem>>() { // from class: com.yucheng.smarthealthpro.home.activity.sleep.activity.SleepActivity.9
                }.getType());
            } catch (JsonSyntaxException e2) {
                e2.printStackTrace();
            }
            setDayCharData(arrayList);
            SleepSummary sleepSummarySleepTimeSummary = HealthDataFilterKt.sleepTimeSummary(arrayList);
            int deepSleepTime = this.mDaySleepDeepSleepTotal + sleepSummarySleepTimeSummary.getDeepSleepTime();
            int lightSleepTime = this.mDaySleepLightSleepTotal + sleepSummarySleepTimeSummary.getLightSleepTime();
            int remTime = this.mDaySleepRemTotal + sleepSummarySleepTimeSummary.getRemTime();
            sleepSummarySleepTimeSummary.getNapsTime();
            if (deepSleepTime + lightSleepTime + remTime <= 57600) {
                this.mDaySleepDeepSleepTotal += sleepSummarySleepTimeSummary.getDeepSleepTime();
                this.mDaySleepLightSleepTotal += sleepSummarySleepTimeSummary.getLightSleepTime();
                this.mDaySleepRemTotal += sleepSummarySleepTimeSummary.getRemTime();
                this.mDayNapsTotal += sleepSummarySleepTimeSummary.getNapsTime();
                if (next.dsCount != 65535) {
                    this.llRem.setVisibility(8);
                }
                this.mDaySleepAdapterHisListBean.add(new SleepHisListBean(TimeStampUtils.getStringToTimestamp(next.beginTime, "yyyy-MM-dd HH:mm:ss").longValue(), TimeStampUtils.getStringToTimestamp(next.endTime, "yyyy-MM-dd HH:mm:ss").longValue(), next.dsTimes + next.qsTimes + next.remTimes, next.dsTimes, next.qsTimes, next.dsCount, next.qsCount, this.mLists.size(), "", next.remTimes, 0, false));
                it2 = it2;
            }
        }
        updateWakeCount();
        initViewPager();
        Collections.reverse(this.mDaySleepAdapterHisListBean);
        this.mSleepHisListAdapter.setList(this.mDaySleepAdapterHisListBean);
        this.mSleepHisListAdapter.notifyDataSetChanged();
    }

    public List<HistorySleepResponse.SleepBean> GetFixedDataOfSleep(List<HistorySleepResponse.SleepBean> total_list, String dateString) {
        ArrayList arrayList = new ArrayList();
        if (total_list != null && total_list.size() > 0) {
            int size = total_list.size();
            long stringToDate = TimeStampUtils.getStringToDate(dateString, AppDateMgr.DF_YYYY_MM_DD);
            long j2 = stringToDate - 14400000;
            long j3 = stringToDate + 72000000;
            for (int i2 = 0; i2 < size; i2++) {
                if (Tools.getTime(total_list.get(i2).beginTime) >= j2 && Tools.getTime(total_list.get(i2).beginTime) <= j3) {
                    arrayList.add(total_list.get(i2));
                }
            }
        }
        return arrayList;
    }

    private void getNetSleep(String dateTime, String startTime, String endTime, final int days) {
        HashMap map = new HashMap();
        map.put("userId", getIntent().getExtras().getString(Constant.SpConstKey.DEV_ID));
        map.put("day", dateTime);
        map.put("startDate", startTime);
        map.put("endDate", endTime);
        map.put("type", "1");
        HttpUtils.getInstance().postMsgAsynHttp(this, Constants.sleepDayUrl, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.home.activity.sleep.activity.SleepActivity.10
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                if (result != null) {
                    SleepActivity.this.parseSleepData(result, days);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void parseSleepData(String json, int days) {
        CareSleepWeekMonthBean careSleepWeekMonthBean;
        int i2;
        try {
            careSleepWeekMonthBean = (CareSleepWeekMonthBean) new Gson().fromJson(json, CareSleepWeekMonthBean.class);
        } catch (JsonSyntaxException e2) {
            e2.printStackTrace();
            careSleepWeekMonthBean = null;
        }
        if (careSleepWeekMonthBean == null || careSleepWeekMonthBean.data == null) {
            return;
        }
        List<CareSleepWeekMonthBean.DataBean> list = careSleepWeekMonthBean.data;
        int i3 = 0;
        while (true) {
            i2 = 57600;
            if (i3 >= list.size()) {
                break;
            }
            CareSleepWeekMonthBean.DataBean dataBean = list.get(i3);
            if (dataBean.dsStatistics + dataBean.qsStatistics + dataBean.remTotalDuration > 57600) {
                list.remove(i3);
                i3--;
            }
            i3++;
        }
        List<CareSleepWeekMonthBean.DataBean> listSortWeekAndMonthListSleep = Tools.sortWeekAndMonthListSleep(list);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        while (i4 < listSortWeekAndMonthListSleep.size()) {
            CareSleepWeekMonthBean.DataBean dataBean2 = listSortWeekAndMonthListSleep.get(i4);
            if (dataBean2.dsStatistics + i5 + dataBean2.qsStatistics + i6 + dataBean2.remTotalDuration + i7 <= days * i2) {
                int i9 = i5 + dataBean2.dsStatistics;
                int i10 = i6 + dataBean2.qsStatistics;
                i7 += dataBean2.remTotalDuration;
                i8 += dataBean2.count;
                arrayList.add(new SleepHisListBean(TimeStampUtils.getStringToDate(dataBean2.dateformat, AppDateMgr.DF_YYYY_MM_DD), 0L, dataBean2.dsStatistics + dataBean2.qsStatistics + dataBean2.remTotalDuration, dataBean2.dsStatistics, dataBean2.qsStatistics, 0, 0, dataBean2.count, "正常", dataBean2.remTotalDuration, 0, true));
                i5 = i9;
                i6 = i10;
            }
            i4++;
            i2 = 57600;
        }
        ArrayList<String> pastStringArray = YearToDayListUtils.getPastStringArray(this.mToDay, days - 1);
        for (int i11 = 0; i11 < pastStringArray.size(); i11++) {
            arrayList2.add(new SleepHisListBean(0L, 0L, 0L, 0, 0, 0, 0, 0, "正常", 0, 0, true));
        }
        for (int i12 = 0; i12 < pastStringArray.size(); i12++) {
            int i13 = 0;
            while (true) {
                if (i13 < listSortWeekAndMonthListSleep.size()) {
                    CareSleepWeekMonthBean.DataBean dataBean3 = listSortWeekAndMonthListSleep.get(i13);
                    if (dataBean3.dateformat.equals(pastStringArray.get(i12))) {
                        arrayList2.remove(i12);
                        arrayList2.add(i12, new SleepHisListBean(TimeStampUtils.getStringToDate(dataBean3.dateformat, AppDateMgr.DF_YYYY_MM_DD), 0L, 0L, dataBean3.dsStatistics, dataBean3.qsStatistics, 0, 0, 0, "正常", dataBean3.remTotalDuration, 0, true));
                        break;
                    }
                    i13++;
                }
            }
        }
        if (listSortWeekAndMonthListSleep.size() == 0) {
            return;
        }
        if (days == 7) {
            this.mWeekSleepAverageDeepSleepTotal = i5 / listSortWeekAndMonthListSleep.size();
            this.mWeekSleepAverageLightSleepTotal = i6 / listSortWeekAndMonthListSleep.size();
            this.mWeekSleepAverageWakeCount = i8 / listSortWeekAndMonthListSleep.size();
            this.mWeekSleepAverageRemTotal = i7 / listSortWeekAndMonthListSleep.size();
            this.mWeekSleepAdapterHisListBean = arrayList;
            this.mWeekSleepChartDataBeans = arrayList2;
            return;
        }
        if (days == 30) {
            this.mMonthSleepAverageDeepSleepTotal = i5 / listSortWeekAndMonthListSleep.size();
            this.mMonthSleepAverageLightSleepTotal = i6 / listSortWeekAndMonthListSleep.size();
            this.mMonthSleepAverageWakeCount = i8 / listSortWeekAndMonthListSleep.size();
            this.mMonthSleepAverageRemTotal = i7 / listSortWeekAndMonthListSleep.size();
            this.mMonthSleepAdapterHisListBean = arrayList;
            this.mMonthSleepChartDataBeans = arrayList2;
        }
    }

    public void onViewClicked(View view) throws Resources.NotFoundException {
        if (view.getId() == R.id.ll_calendar) {
            this.llMonth.setVisibility(0);
            return;
        }
        if (view.getId() == R.id.tv_back_today) {
            this.mCalendarView.scrollToCurrent();
            return;
        }
        if (view.getId() == R.id.rl_first) {
            startActivity(new Intent(this.context, (Class<?>) MeHealthSettingActivity.class));
            return;
        }
        if (view.getId() == R.id.rl_second) {
            startActivity(new Intent(this.context, (Class<?>) HealthyActivity.class));
            return;
        }
        if (view.getId() == R.id.rl_fourthly) {
            if (this.ARROW == 0) {
                this.mRecyclerView.setVisibility(0);
                this.ivFourthlyRight.setBackground(ResourcesCompat.getDrawable(getResources(), R.mipmap.list_ic_arrow_s, null));
                this.ARROW = 1;
                this.mNestedScrollView.postDelayed(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.sleep.activity.SleepActivity.11
                    @Override // java.lang.Runnable
                    public void run() {
                        SleepActivity.this.mNestedScrollView.smoothScrollTo(0, (int) (SleepActivity.this.mNestedScrollView.getScrollY() + (DpUtil.dp2px(SleepActivity.this.context, 56.0f) * 1.5f)));
                    }
                }, 100L);
                return;
            }
            this.mRecyclerView.setVisibility(8);
            this.ivFourthlyRight.setBackground(ResourcesCompat.getDrawable(getResources(), R.mipmap.list_ic_arrow_n, null));
            this.ARROW = 0;
            return;
        }
        if (view.getId() == R.id.ll_month || view.getId() == R.id.include_item_calendar) {
            this.llMonth.setVisibility(8);
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    public SpannableString parseTime(int runTime) {
        int i2 = runTime - (runTime % 60);
        int i3 = (i2 / 60) % 60;
        return setSpaning(String.format("%d h %d min", Integer.valueOf((i2 - (i3 * 60)) / Constants.DATATYPE.FactoryTest), Integer.valueOf(i3)));
    }

    public SpannableString parseDayTotalTime() {
        return parseMin(getMin(this.mDaySleepDeepSleepTotal) + getMin(this.mDaySleepLightSleepTotal) + getMin(this.mDaySleepRemTotal));
    }

    public SpannableString parseWeekTotalTime() {
        return parseMin(getMin(this.mWeekSleepAverageLightSleepTotal) + getMin(this.mWeekSleepAverageDeepSleepTotal) + getMin(this.mWeekSleepAverageRemTotal));
    }

    public SpannableString parseMonthTotalTime() {
        return parseMin(getMin(this.mMonthSleepAverageLightSleepTotal) + getMin(this.mMonthSleepAverageDeepSleepTotal) + getMin(this.mMonthSleepAverageRemTotal));
    }

    public SpannableString parseMin(int totalMin) {
        return setSpaning(String.format("%d h %d min", Integer.valueOf(totalMin / 60), Integer.valueOf(totalMin % 60)));
    }

    private SpannableString setSpaning(String str) {
        SpannableString spannableString = new SpannableString(str);
        int iLastIndexOf = spannableString.toString().lastIndexOf(" h ");
        int iLastIndexOf2 = spannableString.toString().lastIndexOf(" min");
        if (iLastIndexOf != -1) {
            ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.parseColor("#808080"));
            AbsoluteSizeSpan absoluteSizeSpan = new AbsoluteSizeSpan(14, true);
            spannableString.setSpan(foregroundColorSpan, iLastIndexOf, " h ".length() + iLastIndexOf, 17);
            spannableString.setSpan(absoluteSizeSpan, iLastIndexOf, " h ".length() + iLastIndexOf, 17);
        }
        if (iLastIndexOf2 != -1) {
            ForegroundColorSpan foregroundColorSpan2 = new ForegroundColorSpan(Color.parseColor("#808080"));
            AbsoluteSizeSpan absoluteSizeSpan2 = new AbsoluteSizeSpan(14, true);
            spannableString.setSpan(foregroundColorSpan2, iLastIndexOf2, " min".length() + iLastIndexOf2, 17);
            spannableString.setSpan(absoluteSizeSpan2, iLastIndexOf2, " min".length() + iLastIndexOf2, 17);
        }
        return spannableString;
    }
}
