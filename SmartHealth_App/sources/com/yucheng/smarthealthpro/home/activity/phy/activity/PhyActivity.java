package com.yucheng.smarthealthpro.home.activity.phy.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.text.HtmlCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.flyco.tablayout.SlidingTabLayout;
import com.google.android.material.timepicker.TimeModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarView;
import com.orhanobut.logger.Logger;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.care.bean.FriendCarePhyBean;
import com.yucheng.smarthealthpro.care.bean.FriendPhyBean;
import com.yucheng.smarthealthpro.care.bean.HistoryPhyResponse;
import com.yucheng.smarthealthpro.data.packed.HealthDayData;
import com.yucheng.smarthealthpro.data.packed.HealthPackedData;
import com.yucheng.smarthealthpro.database.room.bean.Physiotherapy;
import com.yucheng.smarthealthpro.databinding.ActivityPhyBinding;
import com.yucheng.smarthealthpro.framework.http.HttpUtils;
import com.yucheng.smarthealthpro.framework.util.Constants;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import com.yucheng.smarthealthpro.home.activity.HealthyActivity;
import com.yucheng.smarthealthpro.home.activity.phy.adapter.PhyHisListAdapter;
import com.yucheng.smarthealthpro.home.activity.phy.adapter.PhyTabFragmentAdapter;
import com.yucheng.smarthealthpro.home.activity.phy.bean.PhyHisListBean;
import com.yucheng.smarthealthpro.home.activity.phy.fragment.PhyTabFragment;
import com.yucheng.smarthealthpro.home.bean.PhyBean;
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
import com.yucheng.smarthealthpro.utils.YearToDayListUtils;
import com.yucheng.smarthealthpro.view.chart.PhyUtils;
import com.yucheng.smarthealthpro.viewmodel.PhysiotherapyViewModel;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes5.dex */
public class PhyActivity extends BaseVbActivity<ActivityPhyBinding> implements CalendarView.OnCalendarSelectListener, CalendarView.OnMonthChangeListener {
    private String date;
    private long dateTime;
    ImageView ivCalendar;
    ImageView ivDataSecond;
    ImageView ivDataThirdly;
    ImageView ivFirstLeft;
    ImageView ivFirstRight;
    ImageView ivFourthlyLeft;
    ImageView ivFourthlyRight;
    ImageView ivSecondLeft;
    ImageView ivSecondRight;
    ViewGroup layoutDayGrid;
    ViewGroup layoutGrid;
    LinearLayout llCalendar;
    LinearLayout llDataSecond;
    LinearLayout llDataThirdly;
    LinearLayout llMonth;
    LinearLayout llRem;
    LinearLayout llStartButton;
    private PhyTabFragmentAdapter mAdapter;
    CalendarView mCalendarView;
    private PhyBean mDayPhyBean;
    NestedScrollView mNestedScrollView;
    private PhyHisListAdapter mPhyHisListAdapter;
    RecyclerView mRecyclerView;
    SlidingTabLayout mSlidingTabLayout;
    private String mToDay;
    private PhysiotherapyViewModel mViewModel;
    NoScrollViewPager mViewPager;
    private int monthLastDay;
    RelativeLayout rlAnalyse;
    RelativeLayout rlDataFirst;
    RelativeLayout rlFirst;
    RelativeLayout rlFourthly;
    RelativeLayout rlSecond;
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
    TextView tvFourthly;
    TextView tvGear1;
    TextView tvGear2;
    TextView tvGear3;
    TextView tvGear4;
    TextView tvRem;
    TextView tvSecond;
    TextView tvStartButton;
    TextView tvYears;
    private int ARROW = 0;
    private List<String> mTitles = new ArrayList();
    private List<HistoryPhyResponse.DataBean> todayHistorySleepList = new ArrayList();
    private List<HistoryPhyResponse.DataBean> yesterdayHistorySleepList = new ArrayList();
    private List<HistoryPhyResponse.DataBean> mLists = new ArrayList();
    private List<PhyHisListBean> mDayAdapterHisListBean = new ArrayList();
    private List<PhyHisListBean> mDayChartDataList = new ArrayList();
    private List<PhyHisListBean> mWeekSleepAdapterHisListBean = new ArrayList();
    private List<PhyHisListBean> mWeekSleepChartDataBeans = new ArrayList();
    private List<PhyHisListBean> mMonthAdapterHisListBean = new ArrayList();
    private List<PhyHisListBean> mMonthChartDataBeans = new ArrayList();
    private boolean isCare = false;
    private int mMonthTotalDuration = 0;
    private int mMonthTotalCount = 0;
    private int mMonthAvgCount = 0;
    private int mWeekTotalDuration = 0;
    private int mWeekTotalCount = 0;
    private int mWeekAvgCount = 0;
    private int mDayTotalDuration = 0;
    private int mDayTotalCount = 0;
    private int mDayAvgCount = 0;

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
        this.mSlidingTabLayout = ((ActivityPhyBinding) this.mBinding).stlTab;
        this.ivCalendar = ((ActivityPhyBinding) this.mBinding).ivCalendar;
        this.tvCalendar = ((ActivityPhyBinding) this.mBinding).tvCalendar;
        this.tvBackToday = ((ActivityPhyBinding) this.mBinding).tvBackToday;
        this.llCalendar = ((ActivityPhyBinding) this.mBinding).llCalendar;
        this.mViewPager = ((ActivityPhyBinding) this.mBinding).vpTab;
        this.tvDataFirst = ((ActivityPhyBinding) this.mBinding).tvDataFirst;
        this.tvDataFirstUnit = ((ActivityPhyBinding) this.mBinding).tvDataFirstUnit;
        this.rlDataFirst = ((ActivityPhyBinding) this.mBinding).rlDataFirst;
        this.tvDataSecond = ((ActivityPhyBinding) this.mBinding).tvDataSecond;
        this.ivDataSecond = ((ActivityPhyBinding) this.mBinding).ivDataSecond;
        this.tvDataSecondUnit = ((ActivityPhyBinding) this.mBinding).tvDataSecondUnit;
        this.llDataSecond = ((ActivityPhyBinding) this.mBinding).llDataSecond;
        this.tvDataThirdly = ((ActivityPhyBinding) this.mBinding).tvDataThirdly;
        this.ivDataThirdly = ((ActivityPhyBinding) this.mBinding).ivDataThirdly;
        this.tvDataThirdlyUnit = ((ActivityPhyBinding) this.mBinding).tvDataThirdlyUnit;
        this.llDataThirdly = ((ActivityPhyBinding) this.mBinding).llDataThirdly;
        this.tvStartButton = ((ActivityPhyBinding) this.mBinding).tvStartButton;
        this.llStartButton = ((ActivityPhyBinding) this.mBinding).llStartButton;
        this.tvAnalyse = ((ActivityPhyBinding) this.mBinding).tvAnalyse;
        this.tvAnalyseData = ((ActivityPhyBinding) this.mBinding).tvAnalyseData;
        this.rlAnalyse = ((ActivityPhyBinding) this.mBinding).rlAnalyse;
        this.ivFirstLeft = ((ActivityPhyBinding) this.mBinding).ivFirstLeft;
        this.tvFirst = ((ActivityPhyBinding) this.mBinding).tvFirst;
        this.ivFirstRight = ((ActivityPhyBinding) this.mBinding).ivFirstRight;
        this.rlFirst = ((ActivityPhyBinding) this.mBinding).rlFirst;
        this.ivSecondLeft = ((ActivityPhyBinding) this.mBinding).ivSecondLeft;
        this.tvSecond = ((ActivityPhyBinding) this.mBinding).tvSecond;
        this.ivSecondRight = ((ActivityPhyBinding) this.mBinding).ivSecondRight;
        this.rlSecond = ((ActivityPhyBinding) this.mBinding).rlSecond;
        this.ivFourthlyLeft = ((ActivityPhyBinding) this.mBinding).ivFourthlyLeft;
        this.tvFourthly = ((ActivityPhyBinding) this.mBinding).tvFourthly;
        this.ivFourthlyRight = ((ActivityPhyBinding) this.mBinding).ivFourthlyRight;
        this.rlFourthly = ((ActivityPhyBinding) this.mBinding).rlFourthly;
        this.mRecyclerView = ((ActivityPhyBinding) this.mBinding).recycleView;
        this.mNestedScrollView = ((ActivityPhyBinding) this.mBinding).nsv;
        this.llRem = ((ActivityPhyBinding) this.mBinding).llDataRem;
        this.tvRem = ((ActivityPhyBinding) this.mBinding).tvDataRem;
        this.tvGear1 = ((ActivityPhyBinding) this.mBinding).gear1;
        this.tvGear2 = ((ActivityPhyBinding) this.mBinding).gear2;
        this.tvGear3 = ((ActivityPhyBinding) this.mBinding).gear3;
        this.tvGear4 = ((ActivityPhyBinding) this.mBinding).gear4;
        this.layoutGrid = ((ActivityPhyBinding) this.mBinding).layoutGrid;
        this.layoutDayGrid = ((ActivityPhyBinding) this.mBinding).layoutDayGrid;
        this.tvYears = ((ActivityPhyBinding) this.mBinding).includeItemCalendar.tvYears;
        this.mCalendarView = ((ActivityPhyBinding) this.mBinding).includeItemCalendar.calendarView;
        this.llMonth = ((ActivityPhyBinding) this.mBinding).includeItemCalendar.llMonth;
        this.llCalendar.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.phy.activity.PhyActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.tvBackToday.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.phy.activity.PhyActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlFirst.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.phy.activity.PhyActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlSecond.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.phy.activity.PhyActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlFourthly.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.phy.activity.PhyActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.llMonth.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.phy.activity.PhyActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        changeTitle(getString(R.string.physiotherapy));
        showBack();
        showRightImage(R.mipmap.topbar_ic_share, new NavigationBar.MyOnClickListener() { // from class: com.yucheng.smarthealthpro.home.activity.phy.activity.PhyActivity.1
            @Override // com.yucheng.smarthealthpro.framework.view.NavigationBar.MyOnClickListener
            public void onClick(View btn) {
                if (PhyActivity.this.checkCanClick()) {
                    ShareUtils.share(PhyActivity.this);
                }
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
        this.llStartButton.setVisibility(8);
        this.ivFourthlyRight.setBackground(ResourcesCompat.getDrawable(getResources(), R.mipmap.list_ic_arrow_n, null));
    }

    private void initViewModel() {
        this.mViewModel = (PhysiotherapyViewModel) new ViewModelProvider(this).get(PhysiotherapyViewModel.class);
        FlowUtils.INSTANCE.collectFlow(this, this.mViewModel.getPhysiotherapyDataFlow(), new FlowUtils.FlowCollector<HealthDayData<Physiotherapy>>() { // from class: com.yucheng.smarthealthpro.home.activity.phy.activity.PhyActivity.2
            @Override // com.yucheng.smarthealthpro.utils.FlowUtils.FlowCollector
            public void collect(HealthDayData<Physiotherapy> data) throws Resources.NotFoundException {
                PhyActivity.this.onThatVeryDayData(data.getDay(), data.getData());
            }
        });
        FlowUtils.INSTANCE.collectFlow(this, this.mViewModel.getPhysiotherapyPackedDataFlow(), new FlowUtils.FlowCollector<HealthPackedData<Physiotherapy>>() { // from class: com.yucheng.smarthealthpro.home.activity.phy.activity.PhyActivity.3
            @Override // com.yucheng.smarthealthpro.utils.FlowUtils.FlowCollector
            public void collect(HealthPackedData<Physiotherapy> packedData) {
                if (packedData.getDayCount() == 7) {
                    PhyActivity.this.onDataByDays(7, packedData.getData());
                } else if (packedData.getDayCount() == 30) {
                    PhyActivity.this.onDataByDays(30, packedData.getData());
                }
            }
        });
    }

    private void initData() throws Resources.NotFoundException {
        this.mToDay = TimeStampUtils.getToDay();
        this.mTitles.clear();
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
        new Thread(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.phy.activity.PhyActivity.4
            @Override // java.lang.Runnable
            public void run() {
                PhyActivity.this.getWeekData();
                PhyActivity.this.getMonthData();
            }
        }).start();
    }

    private void setDayTotalText(TextView tv, int totalLen, int count) {
        String str;
        String str2;
        String str3;
        if (totalLen > 0 && count > 0) {
            int i2 = totalLen / 60;
            str = "" + (i2 / 60);
            str2 = "" + i2;
            String str4 = "" + (totalLen % 60);
            str3 = "" + count;
        } else {
            str = "0";
            str2 = "00";
            str3 = "0";
        }
        tv.setText(HtmlCompat.fromHtml(String.format(getString(R.string.phy_desc), str, str2, str3), 0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initViewPager() throws Resources.NotFoundException {
        if (isFinishing()) {
            return;
        }
        PhyTabFragmentAdapter phyTabFragmentAdapter = new PhyTabFragmentAdapter(getSupportFragmentManager(), new PhyTabFragmentAdapter.FragmentCreator() { // from class: com.yucheng.smarthealthpro.home.activity.phy.activity.PhyActivity.5
            @Override // com.yucheng.smarthealthpro.home.activity.phy.adapter.PhyTabFragmentAdapter.FragmentCreator
            public Fragment createFragment(String data, int position) {
                return PhyTabFragment.newInstance(data.toString(), position, PhyActivity.this.mNestedScrollView, PhyActivity.this.monthLastDay, PhyActivity.this.mDayChartDataList, PhyActivity.this.mWeekSleepChartDataBeans, PhyActivity.this.mMonthChartDataBeans);
            }

            @Override // com.yucheng.smarthealthpro.home.activity.phy.adapter.PhyTabFragmentAdapter.FragmentCreator
            public String createTitle(String data) {
                return Html.fromHtml(data).toString();
            }
        });
        this.mAdapter = phyTabFragmentAdapter;
        this.mViewPager.setAdapter(phyTabFragmentAdapter);
        this.mAdapter.notifyDataSetChanged();
        this.mViewPager.setOffscreenPageLimit(this.mDayAdapterHisListBean.size() - 1);
        this.mAdapter.setData(this.mTitles);
        this.mSlidingTabLayout.setViewPager(this.mViewPager, (String[]) this.mTitles.toArray(new String[0]));
        this.mSlidingTabLayout.setCurrentTab(2, true);
        this.mViewPager.addOnPageChangeListener(new OnPageChangeListenerImpl());
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
            if (position == 0) {
                PhyActivity.this.freshMonthData();
            } else if (position == 1) {
                PhyActivity.this.freshWeekData();
            } else {
                if (position != 2) {
                    return;
                }
                PhyActivity.this.freshDayData();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void freshDayData() {
        this.tvBackToday.setVisibility(8);
        this.llCalendar.setVisibility(0);
        this.layoutGrid.setVisibility(8);
        this.layoutDayGrid.setVisibility(0);
        PhyBean phyBean = this.mDayPhyBean;
        if (phyBean != null) {
            setDayTotalText(this.tvGear4, phyBean.level4Total, this.mDayPhyBean.level4Count);
            setDayTotalText(this.tvGear3, this.mDayPhyBean.level3Total, this.mDayPhyBean.level3Count);
            setDayTotalText(this.tvGear2, this.mDayPhyBean.level2Total, this.mDayPhyBean.level2Count);
            setDayTotalText(this.tvGear1, this.mDayPhyBean.level1Total, this.mDayPhyBean.level1Count);
        }
        this.mPhyHisListAdapter.setList(this.mDayAdapterHisListBean);
        this.mPhyHisListAdapter.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void freshWeekData() {
        this.tvBackToday.setVisibility(0);
        this.llCalendar.setVisibility(8);
        this.layoutGrid.setVisibility(0);
        this.layoutDayGrid.setVisibility(8);
        List<PhyHisListBean> list = this.mWeekSleepAdapterHisListBean;
        if (list != null && list.size() > 0) {
            this.tvDataFirst.setText("" + this.mWeekAvgCount);
            this.tvDataThirdly.setText("" + this.mWeekTotalDuration);
            this.tvDataThirdly.setText(PhyUtils.parseTime(this.mWeekTotalDuration));
            this.tvRem.setText("" + this.mWeekTotalCount);
        } else {
            this.tvDataFirst.setText("--");
            this.tvDataSecond.setText("--");
            this.tvDataThirdly.setText("--");
            this.tvRem.setText("--");
        }
        this.mPhyHisListAdapter.setList(this.mWeekSleepAdapterHisListBean);
        this.mPhyHisListAdapter.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void freshMonthData() {
        this.tvBackToday.setVisibility(0);
        this.llCalendar.setVisibility(8);
        this.layoutGrid.setVisibility(0);
        this.layoutDayGrid.setVisibility(8);
        List<PhyHisListBean> list = this.mMonthAdapterHisListBean;
        if (list != null && list.size() > 0) {
            this.tvDataFirst.setText("" + this.mMonthAvgCount);
            this.tvDataThirdly.setText(PhyUtils.parseTime(this.mMonthTotalDuration));
            this.tvRem.setText("" + this.mMonthTotalCount);
        } else {
            this.tvDataFirst.setText("--");
            this.tvDataSecond.setText("--");
            this.tvDataThirdly.setText("--");
            this.tvRem.setText("--");
        }
        this.mPhyHisListAdapter.setList(this.mMonthAdapterHisListBean);
        this.mPhyHisListAdapter.notifyDataSetChanged();
    }

    private void setRecycleView() {
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        PhyHisListAdapter phyHisListAdapter = new PhyHisListAdapter(R.layout.item_phy_his_list);
        this.mPhyHisListAdapter = phyHisListAdapter;
        phyHisListAdapter.addData((Collection) this.mDayAdapterHisListBean);
        this.mRecyclerView.setAdapter(this.mPhyHisListAdapter);
        this.mRecyclerView.setNestedScrollingEnabled(false);
    }

    private void initMonth() throws Resources.NotFoundException {
        this.mCalendarView.setOnCalendarSelectListener(this);
        this.mCalendarView.setOnMonthChangeListener(this);
        this.mCalendarView.scrollToCurrent();
        this.mCalendarView.setOnCalendarInterceptListener(new CalendarView.OnCalendarInterceptListener() { // from class: com.yucheng.smarthealthpro.home.activity.phy.activity.PhyActivity.6
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
        this.dateTime = calendar.getTimeInMillis();
        resetDayData();
        if (this.isCare) {
            this.mLists.clear();
            this.todayHistorySleepList.clear();
            this.yesterdayHistorySleepList.clear();
            getDaySleep(this.date, 0);
        } else {
            this.mViewModel.getDayData(this.date);
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

    public void onThatVeryDayData(String thatVeryDay, List<Physiotherapy> data) throws Resources.NotFoundException {
        getDayData(data);
        initViewPager();
        Collections.reverse(this.mDayAdapterHisListBean);
        this.mPhyHisListAdapter.setList(this.mDayAdapterHisListBean);
        this.mPhyHisListAdapter.notifyDataSetChanged();
    }

    private void resetDayData() {
        this.mDayAdapterHisListBean.clear();
        this.mDayChartDataList.clear();
        this.mDayPhyBean = null;
    }

    private void getDayData(List<Physiotherapy> data) {
        if (data != null) {
            try {
                PhyBean phyBean = new PhyBean(data);
                this.mDayPhyBean = phyBean;
                phyBean.updateData();
                setDayCharData(data);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private void setDayCharData(List<Physiotherapy> dbList) {
        if (dbList == null) {
            return;
        }
        for (int i2 = 0; i2 < dbList.size(); i2++) {
            Physiotherapy physiotherapy = dbList.get(i2);
            PhyHisListBean phyHisListBean = new PhyHisListBean();
            int powerLevel = physiotherapy.getPowerLevel();
            if (powerLevel == 0) {
                phyHisListBean.setLevel1Duration(phyHisListBean.getLevel1Duration() + physiotherapy.getDuration());
                phyHisListBean.setLevel1Count(phyHisListBean.getLevel1Count() + 1);
            } else if (powerLevel == 1) {
                phyHisListBean.setLevel2Duration(phyHisListBean.getLevel2Duration() + physiotherapy.getDuration());
                phyHisListBean.setLevel2Count(phyHisListBean.getLevel2Count() + 1);
            } else if (powerLevel == 2) {
                phyHisListBean.setLevel3Duration(phyHisListBean.getLevel3Duration() + physiotherapy.getDuration());
                phyHisListBean.setLevel3Count(phyHisListBean.getLevel3Count() + 1);
            } else if (powerLevel == 3) {
                phyHisListBean.setLevel4Duration(phyHisListBean.getLevel4Duration() + physiotherapy.getDuration());
                phyHisListBean.setLevel4Count(phyHisListBean.getLevel4Count() + 1);
            }
            phyHisListBean.setDateTime(physiotherapy.getStartTimestamp());
            this.mDayAdapterHisListBean.add(phyHisListBean);
            this.mDayChartDataList.add(phyHisListBean);
        }
    }

    public void getWeekData() {
        this.mWeekSleepAdapterHisListBean.clear();
        this.mWeekSleepChartDataBeans.clear();
        this.mViewModel.getPackedDayData(YearToDayListUtils.getPastStringArray(this.mToDay, 6).get(0), 7);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onDataByDays(int days, List<Physiotherapy> data) {
        ArrayList<String> pastStringArray = YearToDayListUtils.getPastStringArray(this.mToDay, days - 1);
        Logger.w(new Gson().toJson(pastStringArray), new Object[0]);
        for (int i2 = 0; i2 < pastStringArray.size(); i2++) {
            try {
                List<Physiotherapy> listFilterPhysiotherapyByDate = HealthDataFilterKt.filterPhysiotherapyByDate(data, pastStringArray.get(i2));
                PhyHisListBean phyHisListBean = new PhyHisListBean();
                Iterator<Physiotherapy> it2 = listFilterPhysiotherapyByDate.iterator();
                while (it2.hasNext()) {
                    int powerLevel = it2.next().getPowerLevel();
                    if (powerLevel == 0) {
                        phyHisListBean.setLevel1Duration(phyHisListBean.getLevel1Duration() + r4.getDuration());
                        phyHisListBean.setLevel1Count(phyHisListBean.getLevel1Count() + 1);
                    } else if (powerLevel == 1) {
                        phyHisListBean.setLevel2Duration(phyHisListBean.getLevel2Duration() + r4.getDuration());
                        phyHisListBean.setLevel2Count(phyHisListBean.getLevel2Count() + 1);
                    } else if (powerLevel == 2) {
                        phyHisListBean.setLevel3Duration(phyHisListBean.getLevel3Duration() + r4.getDuration());
                        phyHisListBean.setLevel3Count(phyHisListBean.getLevel3Count() + 1);
                    } else if (powerLevel == 3) {
                        phyHisListBean.setLevel4Duration(phyHisListBean.getLevel4Duration() + r4.getDuration());
                        phyHisListBean.setLevel4Count(phyHisListBean.getLevel4Count() + 1);
                    }
                }
                phyHisListBean.setDateTime(new SimpleDateFormat(AppDateMgr.DF_YYYY_MM_DD, Locale.getDefault()).parse(pastStringArray.get(i2)).getTime());
                if (days == 7) {
                    this.mWeekSleepAdapterHisListBean.add(phyHisListBean);
                    this.mWeekSleepChartDataBeans.add(phyHisListBean);
                    this.mWeekTotalDuration = (int) (phyHisListBean.getLevel1Duration() + phyHisListBean.getLevel2Duration() + phyHisListBean.getLevel3Duration() + phyHisListBean.getLevel4Duration());
                    this.mWeekTotalCount += phyHisListBean.getLevel1Count() + phyHisListBean.getLevel2Count() + phyHisListBean.getLevel3Count() + phyHisListBean.getLevel4Count();
                } else if (days == 30) {
                    this.mMonthAdapterHisListBean.add(phyHisListBean);
                    this.mMonthChartDataBeans.add(phyHisListBean);
                    this.mMonthTotalDuration = (int) (phyHisListBean.getLevel1Duration() + phyHisListBean.getLevel2Duration() + phyHisListBean.getLevel3Duration() + phyHisListBean.getLevel4Duration());
                    this.mMonthTotalCount += phyHisListBean.getLevel1Count() + phyHisListBean.getLevel2Count() + phyHisListBean.getLevel3Count() + phyHisListBean.getLevel4Count();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                return;
            }
        }
        this.mWeekAvgCount = Math.round((this.mWeekTotalCount * 1.0f) / 7.0f);
        this.mMonthAvgCount = Math.round((this.mMonthTotalCount * 1.0f) / 30.0f);
    }

    public void getMonthData() {
        this.mMonthAdapterHisListBean.clear();
        this.mMonthChartDataBeans.clear();
        this.mViewModel.getPackedDayData(YearToDayListUtils.getPastStringArray(this.mToDay, 29).get(0), 30);
        runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.phy.activity.PhyActivity.7
            @Override // java.lang.Runnable
            public void run() throws Resources.NotFoundException {
                PhyActivity.this.initViewPager();
            }
        });
    }

    public void getDaySleep(String dateTime, int type) {
        ArrayList<String> pastStringArray = YearToDayListUtils.getPastStringArray(this.mToDay, 0);
        String str = pastStringArray.get(0);
        String str2 = pastStringArray.get(0);
        HashMap map = new HashMap();
        map.put("userId", getIntent().getExtras().getString(Constant.SpConstKey.DEV_ID));
        map.put("day", dateTime);
        map.put("startDate", str);
        map.put("endDate", str2);
        map.put("type", "1");
        HttpUtils.getInstance().postMsgAsynHttp(this, Constants.GET_PHYSIOTHERAPY_DAY, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.home.activity.phy.activity.PhyActivity.8
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) throws Resources.NotFoundException {
                Logger.w("" + result, new Object[0]);
                if (result != null) {
                    PhyActivity.this.parsePhyData(result, 0);
                    PhyActivity.this.setDayData();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDayData() throws Resources.NotFoundException {
        initViewPager();
        Collections.reverse(this.mDayAdapterHisListBean);
        this.mPhyHisListAdapter.setList(this.mDayAdapterHisListBean);
        this.mPhyHisListAdapter.notifyDataSetChanged();
    }

    private void getNetSleep(String dateTime, String startTime, String endTime, final int days) {
        HashMap map = new HashMap();
        map.put("userId", getIntent().getExtras().getString(Constant.SpConstKey.DEV_ID));
        map.put("day", dateTime);
        map.put("startDate", startTime);
        map.put("endDate", endTime);
        map.put("type", "1");
        HttpUtils.getInstance().postMsgAsynHttp(this, Constants.GET_PHYSIOTHERAPY_DAY, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.home.activity.phy.activity.PhyActivity.9
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                if (result != null) {
                    PhyActivity.this.parsePhyData(result, days);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void parsePhyData(String result, int days) {
        int i2;
        int i3;
        List<PhyHisListBean> list;
        PhyHisListBean next;
        ArrayList arrayList;
        ArrayList arrayList2;
        int i4;
        int i5;
        try {
            FriendCarePhyBean friendCarePhyBean = (FriendCarePhyBean) new Gson().fromJson(result, FriendCarePhyBean.class);
            List<FriendCarePhyBean.DataDTO> data = friendCarePhyBean.getData();
            ArrayList<String> pastStringArray = YearToDayListUtils.getPastStringArray(this.mToDay, days);
            int i6 = 0;
            while (true) {
                i2 = 30;
                i3 = 7;
                if (i6 >= pastStringArray.size()) {
                    break;
                }
                PhyHisListBean phyHisListBean = new PhyHisListBean();
                phyHisListBean.setDateTime(new SimpleDateFormat(AppDateMgr.DF_YYYY_MM_DD, Locale.getDefault()).parse(pastStringArray.get(i6)).getTime());
                if (days == 7) {
                    this.mWeekSleepAdapterHisListBean.add(phyHisListBean);
                    this.mWeekSleepChartDataBeans.add(phyHisListBean);
                } else if (days == 30) {
                    this.mMonthAdapterHisListBean.add(phyHisListBean);
                    this.mMonthChartDataBeans.add(phyHisListBean);
                } else {
                    this.mDayAdapterHisListBean.add(phyHisListBean);
                    this.mDayChartDataList.add(phyHisListBean);
                }
                i6++;
            }
            for (int i7 = 0; i7 < data.size(); i7++) {
                ArrayList arrayList3 = new ArrayList((Collection) new Gson().fromJson(friendCarePhyBean.getData().get(i7).getMlist(), new TypeToken<List<FriendPhyBean>>() { // from class: com.yucheng.smarthealthpro.home.activity.phy.activity.PhyActivity.10
                }.getType()));
                int i8 = 0;
                while (i8 < arrayList3.size()) {
                    FriendPhyBean friendPhyBean = (FriendPhyBean) arrayList3.get(i8);
                    if (days == i3) {
                        list = this.mWeekSleepChartDataBeans;
                    } else if (days == i2) {
                        list = this.mMonthChartDataBeans;
                    } else {
                        list = this.mDayChartDataList;
                    }
                    Iterator<PhyHisListBean> it2 = list.iterator();
                    while (true) {
                        if (it2.hasNext()) {
                            next = it2.next();
                            if (TimeStampUtils.isSameDate(next.getDateTime(), friendPhyBean.getRtime())) {
                                break;
                            }
                        } else {
                            next = null;
                            break;
                        }
                    }
                    if (next == null) {
                        arrayList2 = arrayList3;
                    } else {
                        next.setDateTime(friendPhyBean.getRtime());
                        int powerLevel = friendPhyBean.getPowerLevel();
                        if (powerLevel == 0) {
                            arrayList = arrayList3;
                            next.setLevel1Duration(next.getLevel1Duration() + friendPhyBean.getDuration());
                            next.setLevel1Count(next.getLevel1Count() + 1);
                        } else if (powerLevel == 1) {
                            arrayList = arrayList3;
                            next.setLevel2Duration(next.getLevel2Duration() + friendPhyBean.getDuration());
                            next.setLevel2Count(next.getLevel2Count() + 1);
                        } else if (powerLevel == 2) {
                            arrayList = arrayList3;
                            next.setLevel3Duration(next.getLevel3Duration() + friendPhyBean.getDuration());
                            next.setLevel3Count(next.getLevel3Count() + 1);
                        } else if (powerLevel != 3) {
                            arrayList = arrayList3;
                        } else {
                            arrayList = arrayList3;
                            next.setLevel4Duration(next.getLevel4Duration() + friendPhyBean.getDuration());
                            next.setLevel4Count(next.getLevel4Count() + 1);
                        }
                        if (i8 != arrayList.size() - 1) {
                            arrayList2 = arrayList;
                            if (!friendPhyBean.getDateformat().equals(((FriendPhyBean) arrayList2.get(i8 + 1)).getDateformat())) {
                            }
                        } else {
                            arrayList2 = arrayList;
                        }
                        i4 = 7;
                        if (days == 7) {
                            this.mWeekTotalDuration = (int) (next.getLevel1Duration() + next.getLevel2Duration() + next.getLevel3Duration() + next.getLevel4Duration());
                            this.mWeekTotalCount += next.getLevel1Count() + next.getLevel2Count() + next.getLevel3Count() + next.getLevel4Count();
                            i5 = 30;
                            i8++;
                            i3 = i4;
                            ArrayList arrayList4 = arrayList2;
                            i2 = i5;
                            arrayList3 = arrayList4;
                        } else {
                            i5 = 30;
                            if (days == 30) {
                                this.mMonthTotalDuration = (int) (next.getLevel1Duration() + next.getLevel2Duration() + next.getLevel3Duration() + next.getLevel4Duration());
                                this.mMonthTotalCount += next.getLevel1Count() + next.getLevel2Count() + next.getLevel3Count() + next.getLevel4Count();
                            } else {
                                this.mDayTotalDuration = (int) (next.getLevel1Duration() + next.getLevel2Duration() + next.getLevel3Duration() + next.getLevel4Duration());
                                this.mDayTotalCount += next.getLevel1Count() + next.getLevel2Count() + next.getLevel3Count() + next.getLevel4Count();
                            }
                            i8++;
                            i3 = i4;
                            ArrayList arrayList42 = arrayList2;
                            i2 = i5;
                            arrayList3 = arrayList42;
                        }
                    }
                    i4 = 7;
                    i5 = 30;
                    i8++;
                    i3 = i4;
                    ArrayList arrayList422 = arrayList2;
                    i2 = i5;
                    arrayList3 = arrayList422;
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
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
                this.mNestedScrollView.postDelayed(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.phy.activity.PhyActivity.11
                    @Override // java.lang.Runnable
                    public void run() {
                        PhyActivity.this.mNestedScrollView.smoothScrollTo(0, (int) (PhyActivity.this.mNestedScrollView.getScrollY() + (DpUtil.dp2px(PhyActivity.this.context, 56.0f) * 1.5f)));
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
}
