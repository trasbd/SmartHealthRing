package com.yucheng.smarthealthpro.home.activity.respiratoryrate.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Html;
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
import com.google.gson.reflect.TypeToken;
import com.haibin.calendarview.CalendarView;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.care.bean.CareRateWeekMonthBean;
import com.yucheng.smarthealthpro.care.bean.FriendCareDataBean;
import com.yucheng.smarthealthpro.care.bean.FriendCareRateBean;
import com.yucheng.smarthealthpro.data.packed.HealthDayData;
import com.yucheng.smarthealthpro.data.packed.HealthPackedData;
import com.yucheng.smarthealthpro.database.room.bean.HealthMetric;
import com.yucheng.smarthealthpro.databinding.ActivityRespiratoryrateBinding;
import com.yucheng.smarthealthpro.framework.http.HttpUtils;
import com.yucheng.smarthealthpro.framework.util.Constants;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import com.yucheng.smarthealthpro.home.activity.HealthyActivity;
import com.yucheng.smarthealthpro.home.activity.respiratoryrate.adapter.AwRRTabFragmentAdapter;
import com.yucheng.smarthealthpro.home.activity.respiratoryrate.adapter.RespiratoryRateHisListAdapter;
import com.yucheng.smarthealthpro.home.activity.respiratoryrate.fragment.AwRRTabFragment;
import com.yucheng.smarthealthpro.home.activity.temperature.bean.TemperatureHisListBean;
import com.yucheng.smarthealthpro.home.util.TimeDateUtil;
import com.yucheng.smarthealthpro.home.view.NoScrollViewPager;
import com.yucheng.smarthealthpro.me.setting.contacts.utils.DpUtil;
import com.yucheng.smarthealthpro.utils.AppScreenMgr;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.DebouncingClick;
import com.yucheng.smarthealthpro.utils.DensityUtils;
import com.yucheng.smarthealthpro.utils.FlowUtils;
import com.yucheng.smarthealthpro.utils.ShareUtils;
import com.yucheng.smarthealthpro.utils.TimeStampUtils;
import com.yucheng.smarthealthpro.utils.Tools;
import com.yucheng.smarthealthpro.utils.TransUtils;
import com.yucheng.smarthealthpro.utils.YearToDayListUtils;
import com.yucheng.smarthealthpro.viewmodel.RespiratoryRateViewModel;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class RespiratoryRateActivity extends BaseVbActivity<ActivityRespiratoryrateBinding> implements CalendarView.OnCalendarSelectListener, CalendarView.OnMonthChangeListener {
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
    LinearLayout llStartButton;
    private AwRRTabFragmentAdapter mAdapter;
    private List<HealthMetric> mAllDb;
    private Calendar mCalendar;
    CalendarView mCalendarView;
    private int mDayAverageAwRRNum;
    private int mDayMaxAwRRNum;
    private int mDayMinAwRRNum;
    private int mDaySumUpAwRRNum;
    private int mMonthAverageAwRRNum;
    private int mMonthMaxAwRRNum;
    private int mMonthMinAwRRNum;
    private int mMonthSumUpAwRRNum;
    NestedScrollView mNestedScrollView;
    RecyclerView mRecyclerView;
    private RespiratoryRateHisListAdapter mRespiratoryRateHisListAdapter;
    SlidingTabLayout mSlidingTabLayout;
    private String mToDay;
    private RespiratoryRateViewModel mViewModel;
    NoScrollViewPager mViewPager;
    private int mWeekAverageAwRRNum;
    private int mWeekMaxAwRRNum;
    private int mWeekMinAwRRNum;
    private int mWeekSumUpAwRRNum;
    private int monthLastDay;
    RelativeLayout rlAnalyse;
    RelativeLayout rlDataFirst;
    RelativeLayout rlFirst;
    RelativeLayout rlFourthly;
    RelativeLayout rlSecond;
    private FriendCareDataBean temp_bean;
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
    TextView tvSecond;
    TextView tvStartButton;
    TextView tvYears;
    private int ARROW = 0;
    private List<String> mTitles = new ArrayList();
    private String mThatVeryDay = "";
    private List<TemperatureHisListBean> mDayRespiratoryRateHisListBean = new ArrayList();
    private List<TemperatureHisListBean> mDayChartSumUpRespiratoryRateHisListBean = new ArrayList();
    private List<TemperatureHisListBean> mWeekRespiratoryRateHisListBean = new ArrayList();
    private List<TemperatureHisListBean> mWeekAdapterRespiratoryRateHisListBean = new ArrayList();
    private List<TemperatureHisListBean> mWeekChartSumUpRespiratoryRateHisListBean = new ArrayList();
    private List<TemperatureHisListBean> mMonthRespiratoryRateHisListBean = new ArrayList();
    private List<TemperatureHisListBean> mMonthAdapterRespiratoryRateHisListBean = new ArrayList();
    private List<TemperatureHisListBean> mMonthChartSumUpRespiratoryRateHisListBean = new ArrayList();
    private Boolean isCare = false;

    @Override // com.haibin.calendarview.CalendarView.OnCalendarSelectListener
    public void onCalendarOutOfRange(com.haibin.calendarview.Calendar calendar) {
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) throws Resources.NotFoundException {
        super.onCreate(savedInstanceState);
        initView();
        initViewModel();
        initData();
    }

    private void initView() {
        this.mSlidingTabLayout = ((ActivityRespiratoryrateBinding) this.mBinding).includeItemTop.stlTab;
        this.ivCalendar = ((ActivityRespiratoryrateBinding) this.mBinding).includeItemTop.ivCalendar;
        this.tvCalendar = ((ActivityRespiratoryrateBinding) this.mBinding).includeItemTop.tvCalendar;
        this.tvBackToday = ((ActivityRespiratoryrateBinding) this.mBinding).includeItemTop.tvBackToday;
        this.llCalendar = ((ActivityRespiratoryrateBinding) this.mBinding).includeItemTop.llCalendar;
        this.mViewPager = ((ActivityRespiratoryrateBinding) this.mBinding).includeItemTop.vpTab;
        this.tvDataFirst = ((ActivityRespiratoryrateBinding) this.mBinding).includeItemMessageData.tvDataFirst;
        this.tvDataFirstUnit = ((ActivityRespiratoryrateBinding) this.mBinding).includeItemMessageData.tvDataFirstUnit;
        this.rlDataFirst = ((ActivityRespiratoryrateBinding) this.mBinding).includeItemMessageData.rlDataFirst;
        this.tvDataSecond = ((ActivityRespiratoryrateBinding) this.mBinding).includeItemMessageData.tvDataSecond;
        this.ivDataSecond = ((ActivityRespiratoryrateBinding) this.mBinding).includeItemMessageData.ivDataSecond;
        this.tvDataSecondUnit = ((ActivityRespiratoryrateBinding) this.mBinding).includeItemMessageData.tvDataSecondUnit;
        this.llDataSecond = ((ActivityRespiratoryrateBinding) this.mBinding).includeItemMessageData.llDataSecond;
        this.tvDataThirdly = ((ActivityRespiratoryrateBinding) this.mBinding).includeItemMessageData.tvDataThirdly;
        this.ivDataThirdly = ((ActivityRespiratoryrateBinding) this.mBinding).includeItemMessageData.ivDataThirdly;
        this.tvDataThirdlyUnit = ((ActivityRespiratoryrateBinding) this.mBinding).includeItemMessageData.tvDataThirdlyUnit;
        this.llDataThirdly = ((ActivityRespiratoryrateBinding) this.mBinding).includeItemMessageData.llDataThirdly;
        this.tvStartButton = ((ActivityRespiratoryrateBinding) this.mBinding).includeItemBottom.tvStartButton;
        this.llStartButton = ((ActivityRespiratoryrateBinding) this.mBinding).includeItemBottom.llStartButton;
        this.tvAnalyse = ((ActivityRespiratoryrateBinding) this.mBinding).includeItemBottom.tvAnalyse;
        this.tvAnalyseData = ((ActivityRespiratoryrateBinding) this.mBinding).includeItemBottom.tvAnalyseData;
        this.rlAnalyse = ((ActivityRespiratoryrateBinding) this.mBinding).includeItemBottom.rlAnalyse;
        this.ivFirstLeft = ((ActivityRespiratoryrateBinding) this.mBinding).includeItemBottom.ivFirstLeft;
        this.tvFirst = ((ActivityRespiratoryrateBinding) this.mBinding).includeItemBottom.tvFirst;
        this.ivFirstRight = ((ActivityRespiratoryrateBinding) this.mBinding).includeItemBottom.ivFirstRight;
        this.rlFirst = ((ActivityRespiratoryrateBinding) this.mBinding).includeItemBottom.rlFirst;
        this.ivSecondLeft = ((ActivityRespiratoryrateBinding) this.mBinding).includeItemBottom.ivSecondLeft;
        this.tvSecond = ((ActivityRespiratoryrateBinding) this.mBinding).includeItemBottom.tvSecond;
        this.ivSecondRight = ((ActivityRespiratoryrateBinding) this.mBinding).includeItemBottom.ivSecondRight;
        this.rlSecond = ((ActivityRespiratoryrateBinding) this.mBinding).includeItemBottom.rlSecond;
        this.ivFourthlyLeft = ((ActivityRespiratoryrateBinding) this.mBinding).includeItemBottom.ivFourthlyLeft;
        this.tvFourthly = ((ActivityRespiratoryrateBinding) this.mBinding).includeItemBottom.tvFourthly;
        this.ivFourthlyRight = ((ActivityRespiratoryrateBinding) this.mBinding).includeItemBottom.ivFourthlyRight;
        this.rlFourthly = ((ActivityRespiratoryrateBinding) this.mBinding).includeItemBottom.rlFourthly;
        this.mRecyclerView = ((ActivityRespiratoryrateBinding) this.mBinding).includeItemBottom.recycleView;
        this.mNestedScrollView = ((ActivityRespiratoryrateBinding) this.mBinding).nsv;
        this.tvYears = ((ActivityRespiratoryrateBinding) this.mBinding).includeItemCalendar.tvYears;
        this.mCalendarView = ((ActivityRespiratoryrateBinding) this.mBinding).includeItemCalendar.calendarView;
        this.llMonth = ((ActivityRespiratoryrateBinding) this.mBinding).includeItemCalendar.llMonth;
        this.llCalendar.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.respiratoryrate.activity.RespiratoryRateActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.tvBackToday.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.respiratoryrate.activity.RespiratoryRateActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.llStartButton.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.respiratoryrate.activity.RespiratoryRateActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlAnalyse.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.respiratoryrate.activity.RespiratoryRateActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlFirst.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.respiratoryrate.activity.RespiratoryRateActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlSecond.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.respiratoryrate.activity.RespiratoryRateActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlFourthly.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.respiratoryrate.activity.RespiratoryRateActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.llMonth.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.respiratoryrate.activity.RespiratoryRateActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        changeTitle(getString(R.string.home_respiratory_rate_title));
        showBack();
        showRightImage(R.mipmap.topbar_ic_share, new NavigationBar.MyOnClickListener() { // from class: com.yucheng.smarthealthpro.home.activity.respiratoryrate.activity.RespiratoryRateActivity.1
            @Override // com.yucheng.smarthealthpro.framework.view.NavigationBar.MyOnClickListener
            public void onClick(View btn) {
                if (RespiratoryRateActivity.this.checkCanClick()) {
                    ShareUtils.share(RespiratoryRateActivity.this);
                }
            }
        });
        int statusHeight = AppScreenMgr.getStatusHeight(this.context);
        this.llMonth.setPadding(0, DensityUtils.dip2px(this.context, 50.0f) + statusHeight, 0, 0);
        if (Constant.isTechFeel()) {
            this.rlSecond.setVisibility(8);
        }
        this.llStartButton.setVisibility(8);
        this.rlFirst.setVisibility(8);
        this.tvAnalyse.setText(getString(R.string.home_respiratory_rate_analyse_tv));
        this.tvFourthly.setText(getString(R.string.include_bottom_tv_fourthly_button));
        this.ivFourthlyRight.setBackground(ResourcesCompat.getDrawable(getResources(), R.mipmap.list_ic_arrow_n, null));
        String stringExtra = getIntent().getStringExtra("care");
        if (stringExtra != null && stringExtra.equals(getString(R.string.care_title))) {
            this.isCare = true;
            this.rlSecond.setVisibility(8);
        } else {
            this.tvSecond.setText(getString(R.string.include_bottom_tv_second_button));
        }
        this.mToDay = TimeStampUtils.getToDay();
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
    }

    private void initViewModel() {
        this.mViewModel = (RespiratoryRateViewModel) new ViewModelProvider(this).get(RespiratoryRateViewModel.class);
        FlowUtils.INSTANCE.collectFlow(this, this.mViewModel.getRespiratoryRateDataFlow(), new FlowUtils.FlowCollector<HealthDayData<HealthMetric>>() { // from class: com.yucheng.smarthealthpro.home.activity.respiratoryrate.activity.RespiratoryRateActivity.2
            @Override // com.yucheng.smarthealthpro.utils.FlowUtils.FlowCollector
            public void collect(HealthDayData<HealthMetric> data) throws Resources.NotFoundException, NumberFormatException {
                RespiratoryRateActivity.this.onThatVeryDayData(data.getDay(), data.getData());
            }
        });
        FlowUtils.INSTANCE.collectFlow(this, this.mViewModel.getRespiratoryRatePackedDataFlow(), new FlowUtils.FlowCollector<HealthPackedData<HealthMetric>>() { // from class: com.yucheng.smarthealthpro.home.activity.respiratoryrate.activity.RespiratoryRateActivity.3
            @Override // com.yucheng.smarthealthpro.utils.FlowUtils.FlowCollector
            public void collect(HealthPackedData<HealthMetric> packedData) throws NumberFormatException {
                if (packedData.getDayCount() == 7) {
                    RespiratoryRateActivity.this.onWeekData(packedData.getData());
                } else if (packedData.getDayCount() == 30) {
                    RespiratoryRateActivity.this.onMonthData(packedData.getData());
                }
            }
        });
    }

    private void initData() throws Resources.NotFoundException {
        this.mDayRespiratoryRateHisListBean = new ArrayList();
        this.mTitles.clear();
        this.mTitles.add(getString(R.string.date_month_unit));
        this.mTitles.add(getString(R.string.date_week_unit));
        this.mTitles.add(getString(R.string.date_day_unit));
        setRecycleView();
        initViewPager();
        initMonth();
        if (this.isCare.booleanValue()) {
            ArrayList<String> pastStringArray = YearToDayListUtils.getPastStringArray(this.mToDay, 6);
            ArrayList<String> pastStringArray2 = YearToDayListUtils.getPastStringArray(this.mToDay, 29);
            getWeekRate(pastStringArray.get(0), pastStringArray.get(0), pastStringArray.get(6));
            getMonthRate(pastStringArray2.get(0), pastStringArray2.get(0), pastStringArray2.get(29));
            return;
        }
        this.mViewModel.getDayData(this.mToDay);
        new Thread(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.respiratoryrate.activity.RespiratoryRateActivity.4
            @Override // java.lang.Runnable
            public void run() {
                RespiratoryRateActivity.this.mViewModel.getPackedDayData(YearToDayListUtils.getPastStringArray(RespiratoryRateActivity.this.mToDay, 6).get(0), 7);
                RespiratoryRateActivity.this.mViewModel.getPackedDayData(YearToDayListUtils.getPastStringArray(RespiratoryRateActivity.this.mToDay, 29).get(0), 30);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initViewPager() throws Resources.NotFoundException {
        if (isFinishing()) {
            return;
        }
        AwRRTabFragmentAdapter awRRTabFragmentAdapter = new AwRRTabFragmentAdapter(getSupportFragmentManager(), new AwRRTabFragmentAdapter.FragmentCreator() { // from class: com.yucheng.smarthealthpro.home.activity.respiratoryrate.activity.RespiratoryRateActivity.5
            @Override // com.yucheng.smarthealthpro.home.activity.respiratoryrate.adapter.AwRRTabFragmentAdapter.FragmentCreator
            public Fragment createFragment(String data, int position) {
                return AwRRTabFragment.newInstance(data.toString(), position, RespiratoryRateActivity.this.mNestedScrollView, RespiratoryRateActivity.this.monthLastDay, RespiratoryRateActivity.this.mDayChartSumUpRespiratoryRateHisListBean, RespiratoryRateActivity.this.mWeekChartSumUpRespiratoryRateHisListBean, RespiratoryRateActivity.this.mMonthChartSumUpRespiratoryRateHisListBean, RespiratoryRateActivity.this.mDayMaxAwRRNum, RespiratoryRateActivity.this.mToDay);
            }

            @Override // com.yucheng.smarthealthpro.home.activity.respiratoryrate.adapter.AwRRTabFragmentAdapter.FragmentCreator
            public String createTitle(String data) {
                return Html.fromHtml(data).toString();
            }
        });
        this.mAdapter = awRRTabFragmentAdapter;
        this.mViewPager.setAdapter(awRRTabFragmentAdapter);
        this.mAdapter.notifyDataSetChanged();
        this.mViewPager.setOffscreenPageLimit(this.mDayRespiratoryRateHisListBean.size() - 1);
        this.mAdapter.setData(this.mTitles);
        this.mSlidingTabLayout.setViewPager(this.mViewPager, (String[]) this.mTitles.toArray(new String[0]));
        this.mSlidingTabLayout.setCurrentTab(2, true);
        this.mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.yucheng.smarthealthpro.home.activity.respiratoryrate.activity.RespiratoryRateActivity.6
            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int state) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int position) {
                if (position == 0) {
                    RespiratoryRateActivity.this.mViewPager.setCurrentItem(0);
                    if (RespiratoryRateActivity.this.mMonthAdapterRespiratoryRateHisListBean != null && RespiratoryRateActivity.this.mMonthAdapterRespiratoryRateHisListBean.size() > 0) {
                        RespiratoryRateActivity.this.tvDataFirst.setText(RespiratoryRateActivity.this.mMonthAverageAwRRNum + "");
                        RespiratoryRateActivity.this.tvDataSecond.setText(RespiratoryRateActivity.this.mMonthMaxAwRRNum + "");
                        RespiratoryRateActivity.this.tvDataThirdly.setText(RespiratoryRateActivity.this.mMonthMinAwRRNum + "");
                    } else {
                        RespiratoryRateActivity.this.tvDataFirst.setText("--");
                        RespiratoryRateActivity.this.tvDataSecond.setText("--");
                        RespiratoryRateActivity.this.tvDataThirdly.setText("--");
                        RespiratoryRateActivity.this.mMonthAverageAwRRNum = 0;
                    }
                    RespiratoryRateActivity.this.tvBackToday.setVisibility(0);
                    RespiratoryRateActivity.this.llCalendar.setVisibility(8);
                    RespiratoryRateActivity.this.mRespiratoryRateHisListAdapter.replaceData(RespiratoryRateActivity.this.mMonthAdapterRespiratoryRateHisListBean);
                    RespiratoryRateActivity.this.mRespiratoryRateHisListAdapter.notifyDataSetChanged();
                    RespiratoryRateActivity respiratoryRateActivity = RespiratoryRateActivity.this;
                    respiratoryRateActivity.dataAnalysis(respiratoryRateActivity.mMonthAverageAwRRNum);
                    return;
                }
                if (position == 1) {
                    RespiratoryRateActivity.this.mViewPager.setCurrentItem(1);
                    if (RespiratoryRateActivity.this.mWeekAdapterRespiratoryRateHisListBean != null && RespiratoryRateActivity.this.mWeekAdapterRespiratoryRateHisListBean.size() > 0) {
                        RespiratoryRateActivity.this.tvDataFirst.setText(RespiratoryRateActivity.this.mWeekAverageAwRRNum + "");
                        RespiratoryRateActivity.this.tvDataSecond.setText(RespiratoryRateActivity.this.mWeekMaxAwRRNum + "");
                        RespiratoryRateActivity.this.tvDataThirdly.setText(RespiratoryRateActivity.this.mWeekMinAwRRNum + "");
                    } else {
                        RespiratoryRateActivity.this.tvDataFirst.setText("--");
                        RespiratoryRateActivity.this.tvDataSecond.setText("--");
                        RespiratoryRateActivity.this.tvDataThirdly.setText("--");
                        RespiratoryRateActivity.this.mWeekAverageAwRRNum = 0;
                    }
                    RespiratoryRateActivity.this.tvBackToday.setVisibility(0);
                    RespiratoryRateActivity.this.llCalendar.setVisibility(8);
                    RespiratoryRateActivity.this.mRespiratoryRateHisListAdapter.replaceData(RespiratoryRateActivity.this.mWeekAdapterRespiratoryRateHisListBean);
                    RespiratoryRateActivity.this.mRespiratoryRateHisListAdapter.notifyDataSetChanged();
                    RespiratoryRateActivity respiratoryRateActivity2 = RespiratoryRateActivity.this;
                    respiratoryRateActivity2.dataAnalysis(respiratoryRateActivity2.mWeekAverageAwRRNum);
                    return;
                }
                if (position != 2) {
                    return;
                }
                RespiratoryRateActivity.this.mViewPager.setCurrentItem(2);
                if (RespiratoryRateActivity.this.mDayRespiratoryRateHisListBean != null && RespiratoryRateActivity.this.mDayRespiratoryRateHisListBean.size() > 0) {
                    RespiratoryRateActivity.this.tvDataFirst.setText(RespiratoryRateActivity.this.mDayAverageAwRRNum + "");
                    RespiratoryRateActivity.this.tvDataSecond.setText(RespiratoryRateActivity.this.mDayMaxAwRRNum + "");
                    RespiratoryRateActivity.this.tvDataThirdly.setText(RespiratoryRateActivity.this.mDayMinAwRRNum + "");
                } else {
                    RespiratoryRateActivity.this.tvDataFirst.setText("--");
                    RespiratoryRateActivity.this.tvDataSecond.setText("--");
                    RespiratoryRateActivity.this.tvDataThirdly.setText("--");
                    RespiratoryRateActivity.this.mDayAverageAwRRNum = 0;
                }
                RespiratoryRateActivity.this.tvBackToday.setVisibility(8);
                RespiratoryRateActivity.this.llCalendar.setVisibility(0);
                RespiratoryRateActivity.this.mRespiratoryRateHisListAdapter.replaceData(RespiratoryRateActivity.this.mDayRespiratoryRateHisListBean);
                RespiratoryRateActivity.this.mRespiratoryRateHisListAdapter.notifyDataSetChanged();
                RespiratoryRateActivity respiratoryRateActivity3 = RespiratoryRateActivity.this;
                respiratoryRateActivity3.dataAnalysis(respiratoryRateActivity3.mDayAverageAwRRNum);
            }
        });
    }

    public void dataAnalysis(int value) {
        if (Constant.isTechFeel()) {
            if (this.mRespiratoryRateHisListAdapter.getData().size() > 0) {
                this.tvDataFirst.setText(((int) Float.parseFloat(this.mRespiratoryRateHisListAdapter.getData().get(0).getmValue())) + "");
            } else {
                this.tvDataFirst.setText("--");
            }
        }
        if (value > 20) {
            this.tvAnalyseData.setText(getText(R.string.respiratoryrate_analyse_too_high));
            return;
        }
        if (value > 10) {
            this.tvAnalyseData.setText(getText(R.string.respiratoryrate_analyse_normal));
        } else if (value > 6) {
            this.tvAnalyseData.setText(getText(R.string.respiratoryrate_analyse_low_heart_rate));
        } else {
            this.tvAnalyseData.setText("");
        }
    }

    private void setRecycleView() {
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        RespiratoryRateHisListAdapter respiratoryRateHisListAdapter = new RespiratoryRateHisListAdapter(R.layout.item_universal_his_list);
        this.mRespiratoryRateHisListAdapter = respiratoryRateHisListAdapter;
        respiratoryRateHisListAdapter.addData((Collection) this.mDayRespiratoryRateHisListBean);
        this.mRecyclerView.setAdapter(this.mRespiratoryRateHisListAdapter);
        this.mRecyclerView.setNestedScrollingEnabled(false);
        this.mRespiratoryRateHisListAdapter.setOnItemClickListener(new RespiratoryRateHisListAdapter.OnItemClickListener() { // from class: com.yucheng.smarthealthpro.home.activity.respiratoryrate.activity.RespiratoryRateActivity.7
            @Override // com.yucheng.smarthealthpro.home.activity.respiratoryrate.adapter.RespiratoryRateHisListAdapter.OnItemClickListener
            public void onClick(TemperatureHisListBean hisSearch, int position) {
            }

            @Override // com.yucheng.smarthealthpro.home.activity.respiratoryrate.adapter.RespiratoryRateHisListAdapter.OnItemClickListener
            public void onDelClick(TemperatureHisListBean hisSearch, int position) {
            }

            @Override // com.yucheng.smarthealthpro.home.activity.respiratoryrate.adapter.RespiratoryRateHisListAdapter.OnItemClickListener
            public void onLongClick(TemperatureHisListBean hisSearch, int position) {
            }
        });
    }

    private void initMonth() throws Resources.NotFoundException {
        this.mCalendarView.setOnCalendarSelectListener(this);
        this.mCalendarView.setOnMonthChangeListener(this);
        this.mCalendarView.scrollToCurrent();
        this.mCalendarView.setOnCalendarInterceptListener(new CalendarView.OnCalendarInterceptListener() { // from class: com.yucheng.smarthealthpro.home.activity.respiratoryrate.activity.RespiratoryRateActivity.8
            @Override // com.haibin.calendarview.CalendarView.OnCalendarInterceptListener
            public void onCalendarInterceptClick(com.haibin.calendarview.Calendar calendar, boolean isClick) {
            }

            @Override // com.haibin.calendarview.CalendarView.OnCalendarInterceptListener
            public boolean onCalendarIntercept(com.haibin.calendarview.Calendar calendar) {
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
    public void onCalendarSelect(com.haibin.calendarview.Calendar calendar, boolean isClick) {
        this.monthLastDay = YearToDayListUtils.getMonthLastDay(calendar.getYear(), calendar.getMonth());
        String strIntToStr = TimeDateUtil.intToStr(calendar.getYear(), calendar.getMonth(), calendar.getDay());
        if (this.isCare.booleanValue()) {
            getDayRate(strIntToStr);
        } else {
            this.mViewModel.getDayData(strIntToStr);
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

    public void onThatVeryDayData(String thatVeryDay, List<HealthMetric> data) throws Resources.NotFoundException, NumberFormatException {
        List<TemperatureHisListBean> list = this.mDayRespiratoryRateHisListBean;
        if (list != null) {
            list.clear();
        }
        this.mDayChartSumUpRespiratoryRateHisListBean = new ArrayList();
        this.mAllDb = data;
        if (data != null) {
            for (int i2 = 0; i2 < this.mAllDb.size(); i2++) {
                if (TimeStampUtils.dateForStringYearToDate(TimeStampUtils.longStampForDate(this.mAllDb.get(i2).getStartTimestamp())).equals(thatVeryDay) && this.mAllDb.get(i2).getRespiratoryRate() >= TransUtils.RESPIRATORY_RATE_VISIBLE_MIN && this.mAllDb.get(i2).getRespiratoryRate() <= TransUtils.RESPIRATORY_RATE_VISIBLE_MAX) {
                    this.mDayRespiratoryRateHisListBean.add(new TemperatureHisListBean(TimeStampUtils.dateForStringToDate(TimeStampUtils.longStampForDate(this.mAllDb.get(i2).getStartTimestamp())), this.mAllDb.get(i2).getRespiratoryRate() + "", "正常"));
                    this.mDayChartSumUpRespiratoryRateHisListBean.add(new TemperatureHisListBean(TimeStampUtils.dateForStringToDate(TimeStampUtils.longStampForDate(this.mAllDb.get(i2).getStartTimestamp())), this.mAllDb.get(i2).getRespiratoryRate() + "", "正常"));
                }
            }
        }
        if (this.mDayRespiratoryRateHisListBean.size() != 0) {
            this.mThatVeryDay = thatVeryDay;
            this.mDaySumUpAwRRNum = 0;
            this.mDayMaxAwRRNum = 0;
            this.mDayMinAwRRNum = 50;
            for (int i3 = 0; i3 < this.mDayRespiratoryRateHisListBean.size(); i3++) {
                int i4 = Integer.parseInt(this.mDayRespiratoryRateHisListBean.get(i3).getmValue());
                if (i4 > this.mDayMaxAwRRNum) {
                    this.mDayMaxAwRRNum = i4;
                }
                if (i4 < this.mDayMinAwRRNum) {
                    this.mDayMinAwRRNum = i4;
                }
                this.mDaySumUpAwRRNum += i4;
            }
            this.mDayAverageAwRRNum = (int) Math.round((this.mDaySumUpAwRRNum * 1.0d) / this.mDayRespiratoryRateHisListBean.size());
        }
        initViewPager();
    }

    public void onWeekData(List<HealthMetric> data) throws NumberFormatException {
        this.mWeekRespiratoryRateHisListBean = new ArrayList();
        this.mWeekAdapterRespiratoryRateHisListBean = new ArrayList();
        this.mWeekChartSumUpRespiratoryRateHisListBean = new ArrayList();
        if (this.mAllDb != null) {
            ArrayList<String> pastStringArray = YearToDayListUtils.getPastStringArray(this.mToDay, 6);
            if (pastStringArray.size() > 0) {
                this.mAllDb = data;
            }
            for (int i2 = 0; i2 < pastStringArray.size(); i2++) {
                getWeekDay(pastStringArray.get(i2), i2);
            }
        }
        if (this.mWeekAdapterRespiratoryRateHisListBean.size() != 0) {
            Collections.reverse(this.mWeekAdapterRespiratoryRateHisListBean);
            this.mWeekSumUpAwRRNum = 0;
            this.mWeekMaxAwRRNum = 0;
            this.mWeekMinAwRRNum = Integer.parseInt(this.mWeekAdapterRespiratoryRateHisListBean.get(0).getmValue());
            for (int i3 = 0; i3 < this.mWeekAdapterRespiratoryRateHisListBean.size(); i3++) {
                int i4 = Integer.parseInt(this.mWeekAdapterRespiratoryRateHisListBean.get(i3).getmValue());
                if (i4 > this.mWeekMaxAwRRNum) {
                    this.mWeekMaxAwRRNum = i4;
                }
                if (i4 < this.mWeekMinAwRRNum) {
                    this.mWeekMinAwRRNum = i4;
                }
                this.mWeekSumUpAwRRNum += Integer.parseInt(this.mWeekAdapterRespiratoryRateHisListBean.get(i3).getmValue());
            }
            this.mWeekAverageAwRRNum = (int) Math.round((this.mWeekSumUpAwRRNum * 1.0d) / this.mWeekAdapterRespiratoryRateHisListBean.size());
        }
    }

    private void getWeekDay(String mThatVeryDay, int index) {
        int respiratoryRate;
        for (int i2 = 0; i2 < this.mAllDb.size(); i2++) {
            if (TimeStampUtils.dateForStringYearToDate(TimeStampUtils.longStampForDate(this.mAllDb.get(i2).getStartTimestamp())).equals(mThatVeryDay) && (respiratoryRate = this.mAllDb.get(i2).getRespiratoryRate()) >= TransUtils.RESPIRATORY_RATE_VISIBLE_MIN && respiratoryRate <= TransUtils.RESPIRATORY_RATE_VISIBLE_MAX) {
                this.mWeekRespiratoryRateHisListBean.add(new TemperatureHisListBean(TimeStampUtils.dateForStringToDate(TimeStampUtils.longStampForDate(this.mAllDb.get(i2).getStartTimestamp())), respiratoryRate + "", "正常"));
            }
        }
        if (this.mWeekRespiratoryRateHisListBean != null) {
            this.mWeekSumUpAwRRNum = 0;
            for (int i3 = 0; i3 < this.mWeekRespiratoryRateHisListBean.size(); i3++) {
                this.mWeekSumUpAwRRNum += Integer.parseInt(this.mWeekRespiratoryRateHisListBean.get(i3).getmValue());
            }
            if (this.mWeekSumUpAwRRNum != 0) {
                this.mWeekAdapterRespiratoryRateHisListBean.add(new TemperatureHisListBean(TimeStampUtils.dateForStringDates(TimeStampUtils.stringForDateDay(mThatVeryDay)), Math.round((this.mWeekSumUpAwRRNum * 1.0f) / this.mWeekRespiratoryRateHisListBean.size()) + "", "正常"));
            }
            if (this.mWeekSumUpAwRRNum != 0 && this.mWeekRespiratoryRateHisListBean.size() != 0) {
                this.mWeekChartSumUpRespiratoryRateHisListBean.add(index, new TemperatureHisListBean(TimeStampUtils.dateForStringDates(TimeStampUtils.stringForDateDay(mThatVeryDay)), Math.round((this.mWeekSumUpAwRRNum * 1.0f) / this.mWeekRespiratoryRateHisListBean.size()) + "", "正常"));
            } else {
                this.mWeekChartSumUpRespiratoryRateHisListBean.add(index, new TemperatureHisListBean(TimeStampUtils.dateForStringDates(TimeStampUtils.stringForDateDay(mThatVeryDay)), "0", "正常"));
            }
            this.mWeekRespiratoryRateHisListBean.clear();
        }
    }

    public void onMonthData(List<HealthMetric> data) throws NumberFormatException {
        this.mMonthRespiratoryRateHisListBean = new ArrayList();
        this.mMonthAdapterRespiratoryRateHisListBean = new ArrayList();
        this.mMonthChartSumUpRespiratoryRateHisListBean = new ArrayList();
        if (this.mAllDb != null) {
            ArrayList<String> pastStringArray = YearToDayListUtils.getPastStringArray(this.mToDay, 29);
            if (pastStringArray.size() > 0) {
                this.mAllDb = data;
            }
            for (int i2 = 0; i2 < pastStringArray.size(); i2++) {
                getMonthDay(pastStringArray.get(i2), i2);
            }
        }
        if (this.mMonthAdapterRespiratoryRateHisListBean.size() != 0) {
            Collections.reverse(this.mMonthAdapterRespiratoryRateHisListBean);
            this.mMonthSumUpAwRRNum = 0;
            this.mMonthMaxAwRRNum = 0;
            this.mMonthMinAwRRNum = TransUtils.RESPIRATORY_RATE_VISIBLE_MAX;
            for (int i3 = 0; i3 < this.mMonthAdapterRespiratoryRateHisListBean.size(); i3++) {
                int i4 = Integer.parseInt(this.mMonthAdapterRespiratoryRateHisListBean.get(i3).getmValue());
                if (i4 > this.mMonthMaxAwRRNum) {
                    this.mMonthMaxAwRRNum = i4;
                }
                if (i4 < this.mMonthMinAwRRNum) {
                    this.mMonthMinAwRRNum = i4;
                }
                this.mMonthSumUpAwRRNum += i4;
            }
            this.mMonthAverageAwRRNum = (int) Math.round((this.mMonthSumUpAwRRNum * 1.0d) / this.mMonthAdapterRespiratoryRateHisListBean.size());
        }
        runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.respiratoryrate.activity.RespiratoryRateActivity.9
            @Override // java.lang.Runnable
            public void run() throws Resources.NotFoundException {
                RespiratoryRateActivity.this.initViewPager();
            }
        });
    }

    private void getMonthDay(String mThatVeryDay, int index) {
        int respiratoryRate;
        for (int i2 = 0; i2 < this.mAllDb.size(); i2++) {
            if (TimeStampUtils.dateForStringYearToDate(TimeStampUtils.longStampForDate(this.mAllDb.get(i2).getStartTimestamp())).equals(mThatVeryDay) && (respiratoryRate = this.mAllDb.get(i2).getRespiratoryRate()) >= TransUtils.RESPIRATORY_RATE_VISIBLE_MIN && respiratoryRate <= TransUtils.RESPIRATORY_RATE_VISIBLE_MAX) {
                this.mMonthRespiratoryRateHisListBean.add(new TemperatureHisListBean(TimeStampUtils.dateForStringToDate(TimeStampUtils.longStampForDate(this.mAllDb.get(i2).getStartTimestamp())), respiratoryRate + "", "正常"));
            }
        }
        if (this.mMonthRespiratoryRateHisListBean != null) {
            this.mMonthSumUpAwRRNum = 0;
            for (int i3 = 0; i3 < this.mMonthRespiratoryRateHisListBean.size(); i3++) {
                this.mMonthSumUpAwRRNum += Integer.parseInt(this.mMonthRespiratoryRateHisListBean.get(i3).getmValue());
            }
            if (this.mMonthSumUpAwRRNum != 0) {
                this.mMonthAdapterRespiratoryRateHisListBean.add(new TemperatureHisListBean(TimeStampUtils.dateForStringDates(TimeStampUtils.stringForDateDay(mThatVeryDay)), Math.round((this.mMonthSumUpAwRRNum * 1.0f) / this.mMonthRespiratoryRateHisListBean.size()) + "", "正常"));
            }
            if (this.mMonthSumUpAwRRNum != 0 && this.mMonthRespiratoryRateHisListBean.size() != 0) {
                this.mMonthChartSumUpRespiratoryRateHisListBean.add(index, new TemperatureHisListBean(TimeStampUtils.dateForStringDates(TimeStampUtils.stringForDateDay(mThatVeryDay)), Math.round((this.mMonthSumUpAwRRNum * 1.0f) / this.mMonthRespiratoryRateHisListBean.size()) + "", "正常"));
            } else {
                this.mMonthChartSumUpRespiratoryRateHisListBean.add(index, new TemperatureHisListBean(TimeStampUtils.dateForStringDates(TimeStampUtils.stringForDateDay(mThatVeryDay)), "0", "正常"));
            }
            this.mMonthRespiratoryRateHisListBean.clear();
        }
    }

    private void getDayRate(String dateTime) {
        HashMap map = new HashMap();
        map.put("userId", getIntent().getExtras().getString(Constant.SpConstKey.DEV_ID));
        map.put("day", dateTime);
        HttpUtils.getInstance().postMsgAsynHttp(this, Constants.respiratoryRateDayUrl, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.home.activity.respiratoryrate.activity.RespiratoryRateActivity.10
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                if (result != null) {
                    RespiratoryRateActivity.this.temp_bean = (FriendCareDataBean) new Gson().fromJson(result, FriendCareDataBean.class);
                    RespiratoryRateActivity.this.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.respiratoryrate.activity.RespiratoryRateActivity.10.1
                        @Override // java.lang.Runnable
                        public void run() throws Resources.NotFoundException, NumberFormatException {
                            RespiratoryRateActivity.this.setDayData();
                            RespiratoryRateActivity.this.initViewPager();
                        }
                    });
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v16, types: [java.util.List] */
    public void setDayData() throws NumberFormatException {
        List<TemperatureHisListBean> list = this.mDayRespiratoryRateHisListBean;
        if (list != null) {
            list.clear();
        }
        List<TemperatureHisListBean> list2 = this.mDayChartSumUpRespiratoryRateHisListBean;
        if (list2 != null) {
            list2.clear();
        }
        if (this.temp_bean == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        try {
            Iterator<FriendCareDataBean.Data> it2 = this.temp_bean.data.iterator();
            while (it2.hasNext()) {
                arrayList = (List) new Gson().fromJson(it2.next().mlist, new TypeToken<List<FriendCareRateBean>>() { // from class: com.yucheng.smarthealthpro.home.activity.respiratoryrate.activity.RespiratoryRateActivity.11
                }.getType());
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        List<FriendCareRateBean> listSortRespiratoryRateData = Tools.sortRespiratoryRateData(Tools.removeRespiratoryRateData(arrayList));
        for (int i2 = 0; i2 < listSortRespiratoryRateData.size(); i2++) {
            if (listSortRespiratoryRateData.get(i2).respiratoryrate >= TransUtils.RESPIRATORY_RATE_VISIBLE_MIN && listSortRespiratoryRateData.get(i2).respiratoryrate <= TransUtils.RESPIRATORY_RATE_VISIBLE_MAX) {
                this.mDayRespiratoryRateHisListBean.add(new TemperatureHisListBean(TimeStampUtils.dateForStringToDate(TimeStampUtils.longStampForDate(listSortRespiratoryRateData.get(i2).rtime)), listSortRespiratoryRateData.get(i2).respiratoryrate + "", "正常"));
                this.mDayChartSumUpRespiratoryRateHisListBean.add(new TemperatureHisListBean(TimeStampUtils.dateForStringToDate(TimeStampUtils.longStampForDate(listSortRespiratoryRateData.get(i2).rtime)), listSortRespiratoryRateData.get(i2).respiratoryrate + "", "正常"));
            }
        }
        if (this.mDayRespiratoryRateHisListBean.size() != 0) {
            this.mDaySumUpAwRRNum = 0;
            this.mDayMaxAwRRNum = 0;
            this.mDayMinAwRRNum = TransUtils.RESPIRATORY_RATE_VISIBLE_MAX;
            for (int i3 = 0; i3 < this.mDayRespiratoryRateHisListBean.size(); i3++) {
                int i4 = Integer.parseInt(this.mDayRespiratoryRateHisListBean.get(i3).getmValue());
                if (i4 > this.mDayMaxAwRRNum) {
                    this.mDayMaxAwRRNum = i4;
                }
                if (i4 < this.mDayMinAwRRNum) {
                    this.mDayMinAwRRNum = i4;
                }
                this.mDaySumUpAwRRNum += i4;
            }
            if (this.mDayRespiratoryRateHisListBean.size() != 0) {
                this.mDayAverageAwRRNum = (int) Math.round((this.mDaySumUpAwRRNum * 1.0d) / this.mDayRespiratoryRateHisListBean.size());
            }
        }
    }

    private void getWeekRate(String dateTime, String startTime, String endTime) {
        HashMap map = new HashMap();
        map.put("userId", getIntent().getExtras().getString(Constant.SpConstKey.DEV_ID));
        map.put("day", dateTime);
        map.put("startDate", startTime);
        map.put("endDate", endTime);
        map.put("type", "1");
        HttpUtils.getInstance().postMsgAsynHttp(this, Constants.respiratoryRateDayUrl, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.home.activity.respiratoryrate.activity.RespiratoryRateActivity.12
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                if (result != null) {
                    List<CareRateWeekMonthBean.DataBean> data = ((CareRateWeekMonthBean) new Gson().fromJson(result, CareRateWeekMonthBean.class)).getData();
                    if (data.size() != 0) {
                        RespiratoryRateActivity.this.mWeekSumUpAwRRNum = 0;
                        RespiratoryRateActivity.this.mWeekMaxAwRRNum = 0;
                        RespiratoryRateActivity.this.mWeekMinAwRRNum = Math.round(Float.parseFloat(data.get(0).getRespiratoryMean().isEmpty() ? "0" : data.get(0).getRespiratoryMean()));
                        for (int i2 = 0; i2 < data.size(); i2++) {
                            int i3 = (int) Float.parseFloat(data.get(i2).getRespiratoryMean().isEmpty() ? "0" : data.get(i2).getRespiratoryMean());
                            if (i3 > RespiratoryRateActivity.this.mWeekMaxAwRRNum) {
                                RespiratoryRateActivity.this.mWeekMaxAwRRNum = i3;
                            }
                            if (i3 < RespiratoryRateActivity.this.mWeekMinAwRRNum) {
                                RespiratoryRateActivity.this.mWeekMinAwRRNum = i3;
                            }
                            RespiratoryRateActivity.this.mWeekSumUpAwRRNum += Math.round(Float.parseFloat(data.get(i2).getRespiratoryMean().isEmpty() ? "0" : data.get(i2).getRespiratoryMean()));
                            if (data.size() != 0) {
                                RespiratoryRateActivity.this.mWeekAverageAwRRNum = Math.round((r3.mWeekSumUpAwRRNum * 1.0f) / data.size());
                            }
                            if (!data.get(i2).getRespiratoryMean().isEmpty()) {
                                RespiratoryRateActivity.this.mWeekAdapterRespiratoryRateHisListBean.add(new TemperatureHisListBean(TimeStampUtils.dateForStringDates(TimeStampUtils.stringForDateDay(data.get(i2).getDateformat())), data.get(i2).getRespiratoryMean(), "正常"));
                            }
                        }
                        ArrayList<String> pastStringArray = YearToDayListUtils.getPastStringArray(RespiratoryRateActivity.this.mToDay, 6);
                        for (int i4 = 0; i4 < pastStringArray.size(); i4++) {
                            RespiratoryRateActivity.this.mWeekChartSumUpRespiratoryRateHisListBean.add(new TemperatureHisListBean(TimeStampUtils.dateForStringDates(TimeStampUtils.stringForDateDay(pastStringArray.get(i4))), "0", "正常"));
                        }
                        for (int i5 = 0; i5 < pastStringArray.size(); i5++) {
                            for (int i6 = 0; i6 < data.size(); i6++) {
                                if (data.get(i6).getDateformat().equals(pastStringArray.get(i5))) {
                                    RespiratoryRateActivity.this.mWeekChartSumUpRespiratoryRateHisListBean.remove(i5);
                                    RespiratoryRateActivity.this.mWeekChartSumUpRespiratoryRateHisListBean.add(i5, new TemperatureHisListBean(TimeStampUtils.dateForStringDates(TimeStampUtils.stringForDateDay(data.get(i6).getDateformat())), data.get(i6).getRespiratoryMean(), "正常"));
                                }
                            }
                        }
                    }
                }
            }
        });
    }

    private void getMonthRate(String dateTime, String startTime, String endTime) {
        HashMap map = new HashMap();
        map.put("userId", getIntent().getExtras().getString(Constant.SpConstKey.DEV_ID));
        map.put("day", dateTime);
        map.put("startDate", startTime);
        map.put("endDate", endTime);
        map.put("type", "1");
        HttpUtils.getInstance().postMsgAsynHttp(this, Constants.respiratoryRateDayUrl, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.home.activity.respiratoryrate.activity.RespiratoryRateActivity.13
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                if (result != null) {
                    List<CareRateWeekMonthBean.DataBean> data = ((CareRateWeekMonthBean) new Gson().fromJson(result, CareRateWeekMonthBean.class)).getData();
                    if (data.size() != 0) {
                        RespiratoryRateActivity.this.mMonthSumUpAwRRNum = 0;
                        RespiratoryRateActivity.this.mMonthMaxAwRRNum = 0;
                        RespiratoryRateActivity.this.mMonthMinAwRRNum = TransUtils.RESPIRATORY_RATE_VISIBLE_MAX;
                        int i2 = 0;
                        while (true) {
                            if (i2 >= data.size()) {
                                break;
                            }
                            int i3 = (int) Float.parseFloat(data.get(i2).getRespiratoryMean().isEmpty() ? "0" : data.get(i2).getRespiratoryMean());
                            if (i3 >= TransUtils.RESPIRATORY_RATE_VISIBLE_MIN && i3 <= TransUtils.RESPIRATORY_RATE_VISIBLE_MAX) {
                                if (i3 > RespiratoryRateActivity.this.mMonthMaxAwRRNum) {
                                    RespiratoryRateActivity.this.mMonthMaxAwRRNum = i3;
                                }
                                if (i3 < RespiratoryRateActivity.this.mMonthMinAwRRNum) {
                                    RespiratoryRateActivity.this.mMonthMinAwRRNum = i3;
                                }
                                RespiratoryRateActivity.this.mMonthSumUpAwRRNum += i3;
                                RespiratoryRateActivity.this.mMonthAdapterRespiratoryRateHisListBean.add(new TemperatureHisListBean(TimeStampUtils.dateForStringDates(TimeStampUtils.stringForDateDay(data.get(i2).getDateformat())), i3 + "", "正常"));
                            }
                            i2++;
                        }
                        if (RespiratoryRateActivity.this.mMonthAdapterRespiratoryRateHisListBean.size() > 0) {
                            RespiratoryRateActivity.this.mMonthAverageAwRRNum = (int) Math.round((r10.mMonthSumUpAwRRNum * 1.0d) / RespiratoryRateActivity.this.mMonthAdapterRespiratoryRateHisListBean.size());
                        }
                        ArrayList<String> pastByMonthDayArray = YearToDayListUtils.getPastByMonthDayArray(RespiratoryRateActivity.this.mToDay, 29);
                        for (int i4 = 0; i4 < pastByMonthDayArray.size(); i4++) {
                            RespiratoryRateActivity.this.mMonthChartSumUpRespiratoryRateHisListBean.add(new TemperatureHisListBean(pastByMonthDayArray.get(i4), "0", "正常"));
                        }
                        for (int i5 = 0; i5 < pastByMonthDayArray.size(); i5++) {
                            for (TemperatureHisListBean temperatureHisListBean : RespiratoryRateActivity.this.mMonthAdapterRespiratoryRateHisListBean) {
                                if (pastByMonthDayArray.get(i5).equals(temperatureHisListBean.getTime())) {
                                    RespiratoryRateActivity.this.mMonthChartSumUpRespiratoryRateHisListBean.remove(i5);
                                    RespiratoryRateActivity.this.mMonthChartSumUpRespiratoryRateHisListBean.add(i5, new TemperatureHisListBean(temperatureHisListBean.getTime(), temperatureHisListBean.getmValue(), "正常"));
                                }
                            }
                        }
                    }
                }
            }
        });
    }

    public void onViewClicked(View view) {
        if (view.getId() == R.id.ll_calendar) {
            this.llMonth.setVisibility(0);
            return;
        }
        if (view.getId() == R.id.tv_back_today) {
            this.mViewPager.setCurrentItem(2);
            return;
        }
        if (view.getId() == R.id.rl_analyse) {
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
                this.mNestedScrollView.postDelayed(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.respiratoryrate.activity.RespiratoryRateActivity.14
                    @Override // java.lang.Runnable
                    public void run() {
                        RespiratoryRateActivity.this.mNestedScrollView.smoothScrollTo(0, (int) (RespiratoryRateActivity.this.mNestedScrollView.getScrollY() + (DpUtil.dp2px(RespiratoryRateActivity.this.context, 56.0f) * 1.5f)));
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
