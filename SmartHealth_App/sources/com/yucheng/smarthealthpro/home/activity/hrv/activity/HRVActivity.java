package com.yucheng.smarthealthpro.home.activity.hrv.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.activity.result.ActivityResult;
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
import com.yucheng.smarthealthpro.care.bean.CareHRVWeekMonthBean;
import com.yucheng.smarthealthpro.care.bean.HistoryHRVResponse;
import com.yucheng.smarthealthpro.data.packed.HealthDayData;
import com.yucheng.smarthealthpro.data.packed.HealthPackedData;
import com.yucheng.smarthealthpro.database.room.bean.HealthMetric;
import com.yucheng.smarthealthpro.databinding.ActivityHeartrateBinding;
import com.yucheng.smarthealthpro.framework.http.HttpUtils;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import com.yucheng.smarthealthpro.home.activity.HealthyActivity;
import com.yucheng.smarthealthpro.home.activity.MeasureTipActivity;
import com.yucheng.smarthealthpro.home.activity.hrv.adapter.HRVHisListAdapter;
import com.yucheng.smarthealthpro.home.activity.hrv.adapter.HRVTabFragmentAdapter;
import com.yucheng.smarthealthpro.home.activity.hrv.fragment.HRVTabFragment;
import com.yucheng.smarthealthpro.home.activity.temperature.bean.TemperatureHisListBean;
import com.yucheng.smarthealthpro.home.util.TimeDateUtil;
import com.yucheng.smarthealthpro.home.view.NoScrollViewPager;
import com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity;
import com.yucheng.smarthealthpro.me.setting.contacts.utils.DpUtil;
import com.yucheng.smarthealthpro.utils.AppScreenMgr;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.DebouncingClick;
import com.yucheng.smarthealthpro.utils.DensityUtils;
import com.yucheng.smarthealthpro.utils.FlowUtils;
import com.yucheng.smarthealthpro.utils.ShareUtils;
import com.yucheng.smarthealthpro.utils.TimeStampUtils;
import com.yucheng.smarthealthpro.utils.TransUtils;
import com.yucheng.smarthealthpro.utils.YearToDayListUtils;
import com.yucheng.smarthealthpro.viewmodel.HrvViewModel;
import com.yucheng.ycbtsdk.Constants;
import com.yucheng.ycbtsdk.YCBTClient;
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
public class HRVActivity extends BaseVbActivity<ActivityHeartrateBinding> implements CalendarView.OnCalendarSelectListener, CalendarView.OnMonthChangeListener {
    public static int HRV_MEASURE = 10;
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
    private HRVTabFragmentAdapter mAdapter;
    private List<HealthMetric> mAllDb;
    private Calendar mCalendar;
    CalendarView mCalendarView;
    private int mDayAverageHeartNum;
    private int mDayMaxHeartNum;
    private int mDayMinHeartNum;
    private int mDaySumUpHeartNum;
    private HRVHisListAdapter mHRVHisListAdapter;
    private int mMonthAverageHeartNum;
    private int mMonthMaxHeartNum;
    private int mMonthMinHeartNum;
    private int mMonthSumUpHeartNum;
    NestedScrollView mNestedScrollView;
    RecyclerView mRecyclerView;
    SlidingTabLayout mSlidingTabLayout;
    private String mToDay;
    NoScrollViewPager mViewPager;
    private int mWeekAverageHeartNum;
    private int mWeekMaxHeartNum;
    private int mWeekMinHeartNum;
    private int mWeekSumUpHeartNum;
    private int monthLastDay;
    RelativeLayout rlAnalyse;
    RelativeLayout rlDataFirst;
    RelativeLayout rlFirst;
    RelativeLayout rlFourthly;
    RelativeLayout rlSecond;
    private HistoryHRVResponse temp_bean;
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
    private List<TemperatureHisListBean> mDayHeartRateHisListBean = new ArrayList();
    private List<TemperatureHisListBean> mDayChartSumUpHeartRateHisListBean = new ArrayList();
    private List<TemperatureHisListBean> mWeekHeartRateHisListBean = new ArrayList();
    private List<TemperatureHisListBean> mWeekAdapterSumUpHeartRateHisListBean = new ArrayList();
    private List<TemperatureHisListBean> mWeekChartSumUpHeartRateHisListBean = new ArrayList();
    private List<TemperatureHisListBean> mMonthHeartRateHisListBean = new ArrayList();
    private List<TemperatureHisListBean> mMonthAdapterSumUpHeartRateHisListBean = new ArrayList();
    private List<TemperatureHisListBean> mMonthChartSumUpHeartRateHisListBean = new ArrayList();
    private Boolean isCare = false;
    private HrvViewModel mViewModel = null;
    private boolean isGetMonthData = false;
    private boolean isGetDayData = false;

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
        this.mSlidingTabLayout = ((ActivityHeartrateBinding) this.mBinding).includeItemTop.stlTab;
        this.ivCalendar = ((ActivityHeartrateBinding) this.mBinding).includeItemTop.ivCalendar;
        this.tvCalendar = ((ActivityHeartrateBinding) this.mBinding).includeItemTop.tvCalendar;
        this.tvBackToday = ((ActivityHeartrateBinding) this.mBinding).includeItemTop.tvBackToday;
        this.llCalendar = ((ActivityHeartrateBinding) this.mBinding).includeItemTop.llCalendar;
        this.mViewPager = ((ActivityHeartrateBinding) this.mBinding).includeItemTop.vpTab;
        this.tvDataFirst = ((ActivityHeartrateBinding) this.mBinding).includeItemMessageData.tvDataFirst;
        this.tvDataFirstUnit = ((ActivityHeartrateBinding) this.mBinding).includeItemMessageData.tvDataFirstUnit;
        this.rlDataFirst = ((ActivityHeartrateBinding) this.mBinding).includeItemMessageData.rlDataFirst;
        this.tvDataSecond = ((ActivityHeartrateBinding) this.mBinding).includeItemMessageData.tvDataSecond;
        this.ivDataSecond = ((ActivityHeartrateBinding) this.mBinding).includeItemMessageData.ivDataSecond;
        this.tvDataSecondUnit = ((ActivityHeartrateBinding) this.mBinding).includeItemMessageData.tvDataSecondUnit;
        this.llDataSecond = ((ActivityHeartrateBinding) this.mBinding).includeItemMessageData.llDataSecond;
        this.tvDataThirdly = ((ActivityHeartrateBinding) this.mBinding).includeItemMessageData.tvDataThirdly;
        this.ivDataThirdly = ((ActivityHeartrateBinding) this.mBinding).includeItemMessageData.ivDataThirdly;
        this.tvDataThirdlyUnit = ((ActivityHeartrateBinding) this.mBinding).includeItemMessageData.tvDataThirdlyUnit;
        this.llDataThirdly = ((ActivityHeartrateBinding) this.mBinding).includeItemMessageData.llDataThirdly;
        this.tvStartButton = ((ActivityHeartrateBinding) this.mBinding).includeItemBottom.tvStartButton;
        this.llStartButton = ((ActivityHeartrateBinding) this.mBinding).includeItemBottom.llStartButton;
        this.tvAnalyse = ((ActivityHeartrateBinding) this.mBinding).includeItemBottom.tvAnalyse;
        this.tvAnalyseData = ((ActivityHeartrateBinding) this.mBinding).includeItemBottom.tvAnalyseData;
        this.rlAnalyse = ((ActivityHeartrateBinding) this.mBinding).includeItemBottom.rlAnalyse;
        this.ivFirstLeft = ((ActivityHeartrateBinding) this.mBinding).includeItemBottom.ivFirstLeft;
        this.tvFirst = ((ActivityHeartrateBinding) this.mBinding).includeItemBottom.tvFirst;
        this.ivFirstRight = ((ActivityHeartrateBinding) this.mBinding).includeItemBottom.ivFirstRight;
        this.rlFirst = ((ActivityHeartrateBinding) this.mBinding).includeItemBottom.rlFirst;
        this.ivSecondLeft = ((ActivityHeartrateBinding) this.mBinding).includeItemBottom.ivSecondLeft;
        this.tvSecond = ((ActivityHeartrateBinding) this.mBinding).includeItemBottom.tvSecond;
        this.ivSecondRight = ((ActivityHeartrateBinding) this.mBinding).includeItemBottom.ivSecondRight;
        this.rlSecond = ((ActivityHeartrateBinding) this.mBinding).includeItemBottom.rlSecond;
        this.ivFourthlyLeft = ((ActivityHeartrateBinding) this.mBinding).includeItemBottom.ivFourthlyLeft;
        this.tvFourthly = ((ActivityHeartrateBinding) this.mBinding).includeItemBottom.tvFourthly;
        this.ivFourthlyRight = ((ActivityHeartrateBinding) this.mBinding).includeItemBottom.ivFourthlyRight;
        this.rlFourthly = ((ActivityHeartrateBinding) this.mBinding).includeItemBottom.rlFourthly;
        this.mRecyclerView = ((ActivityHeartrateBinding) this.mBinding).includeItemBottom.recycleView;
        this.mNestedScrollView = ((ActivityHeartrateBinding) this.mBinding).nsv;
        this.tvYears = ((ActivityHeartrateBinding) this.mBinding).includeItemCalendar.tvYears;
        this.mCalendarView = ((ActivityHeartrateBinding) this.mBinding).includeItemCalendar.calendarView;
        this.llMonth = ((ActivityHeartrateBinding) this.mBinding).includeItemCalendar.llMonth;
        this.llCalendar.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.hrv.activity.HRVActivity$$ExternalSyntheticLambda1
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.tvBackToday.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.hrv.activity.HRVActivity$$ExternalSyntheticLambda1
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.llStartButton.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.hrv.activity.HRVActivity$$ExternalSyntheticLambda1
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlAnalyse.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.hrv.activity.HRVActivity$$ExternalSyntheticLambda1
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlFirst.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.hrv.activity.HRVActivity$$ExternalSyntheticLambda1
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlSecond.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.hrv.activity.HRVActivity$$ExternalSyntheticLambda1
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlFourthly.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.hrv.activity.HRVActivity$$ExternalSyntheticLambda1
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.llMonth.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.hrv.activity.HRVActivity$$ExternalSyntheticLambda1
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        changeTitle(getString(R.string.hrv_unit));
        showBack();
        showRightImage(R.mipmap.topbar_ic_share, new NavigationBar.MyOnClickListener() { // from class: com.yucheng.smarthealthpro.home.activity.hrv.activity.HRVActivity.1
            @Override // com.yucheng.smarthealthpro.framework.view.NavigationBar.MyOnClickListener
            public void onClick(View btn) {
                if (HRVActivity.this.checkCanClick()) {
                    ShareUtils.share(HRVActivity.this);
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
            this.llStartButton.setVisibility(8);
            this.tvAnalyse.setText(getString(R.string.home_heart_rate_analyse_tv));
            this.rlFirst.setVisibility(8);
            this.rlSecond.setVisibility(8);
            this.ivFourthlyRight.setBackground(ResourcesCompat.getDrawable(getResources(), R.mipmap.list_ic_arrow_n, null));
        } else {
            if (!YCBTClient.isSupportFunction(Constants.FunctionConstant.IS_HAS_HRV_MEASUREMENT)) {
                this.llStartButton.setVisibility(8);
            }
            this.tvStartButton.setText(getString(R.string.home_hrv_measure_title));
            this.tvAnalyse.setText(getString(R.string.home_heart_rate_analyse_tv));
            this.tvFirst.setText(getString(R.string.include_bottom_tv_first_button));
            this.tvSecond.setText(getString(R.string.include_bottom_tv_second_button));
            this.tvFourthly.setText(getString(R.string.include_bottom_tv_fourthly_button));
            this.ivFourthlyRight.setBackground(ResourcesCompat.getDrawable(getResources(), R.mipmap.list_ic_arrow_n, null));
        }
        this.mToDay = TimeStampUtils.getToDay();
    }

    private void initViewModel() {
        this.mViewModel = (HrvViewModel) new ViewModelProvider(this).get(HrvViewModel.class);
        FlowUtils.INSTANCE.collectFlow(this, this.mViewModel.getHrvDataFlow(), new FlowUtils.FlowCollector<HealthDayData<HealthMetric>>() { // from class: com.yucheng.smarthealthpro.home.activity.hrv.activity.HRVActivity.2
            @Override // com.yucheng.smarthealthpro.utils.FlowUtils.FlowCollector
            public void collect(HealthDayData<HealthMetric> data) throws Resources.NotFoundException, NumberFormatException {
                HRVActivity.this.onThatVeryDayData(data.getDay(), data.getData());
            }
        });
        FlowUtils.INSTANCE.collectFlow(this, this.mViewModel.getHrvPackedDataFlow(), new FlowUtils.FlowCollector<HealthPackedData<HealthMetric>>() { // from class: com.yucheng.smarthealthpro.home.activity.hrv.activity.HRVActivity.3
            @Override // com.yucheng.smarthealthpro.utils.FlowUtils.FlowCollector
            public void collect(HealthPackedData<HealthMetric> packedData) throws NumberFormatException {
                if (packedData.getDayCount() == 7) {
                    HRVActivity.this.onWeekData(packedData.getData());
                } else if (packedData.getDayCount() == 30) {
                    HRVActivity.this.onMonthData(packedData.getData());
                }
            }
        });
    }

    private void initData() throws Resources.NotFoundException {
        this.mDayHeartRateHisListBean = new ArrayList();
        this.mTitles.clear();
        this.mTitles.add(getString(R.string.date_month_unit));
        this.mTitles.add(getString(R.string.date_week_unit));
        this.mTitles.add(getString(R.string.date_day_unit));
        setRecycleView();
        initViewPager();
        initMonth();
        if (this.isCare.booleanValue()) {
            return;
        }
        this.mViewModel.getDayData(this.mToDay);
        new Thread(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.hrv.activity.HRVActivity$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$initData$0();
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initData$0() {
        this.mViewModel.getPackedDayData(YearToDayListUtils.getPastStringArray(this.mToDay, 6).get(0), 7);
        this.mViewModel.getPackedDayData(YearToDayListUtils.getPastStringArray(this.mToDay, 29).get(0), 30);
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        if (this.isCare.booleanValue()) {
            ArrayList<String> pastStringArray = YearToDayListUtils.getPastStringArray(this.mToDay, 6);
            ArrayList<String> pastStringArray2 = YearToDayListUtils.getPastStringArray(this.mToDay, 29);
            getWeekHeart(pastStringArray.get(0), pastStringArray.get(0), pastStringArray.get(6));
            getMonthHeart(pastStringArray2.get(0), pastStringArray2.get(0), pastStringArray2.get(29));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initViewPager() throws Resources.NotFoundException {
        if (isFinishing()) {
            return;
        }
        HRVTabFragmentAdapter hRVTabFragmentAdapter = new HRVTabFragmentAdapter(getSupportFragmentManager(), new HRVTabFragmentAdapter.FragmentCreator() { // from class: com.yucheng.smarthealthpro.home.activity.hrv.activity.HRVActivity.4
            @Override // com.yucheng.smarthealthpro.home.activity.hrv.adapter.HRVTabFragmentAdapter.FragmentCreator
            public Fragment createFragment(String data, int position) {
                return HRVTabFragment.newInstance(data, position, HRVActivity.this.mNestedScrollView, HRVActivity.this.mDayChartSumUpHeartRateHisListBean, HRVActivity.this.mWeekChartSumUpHeartRateHisListBean, HRVActivity.this.mMonthChartSumUpHeartRateHisListBean, HRVActivity.this.mDayMaxHeartNum);
            }

            @Override // com.yucheng.smarthealthpro.home.activity.hrv.adapter.HRVTabFragmentAdapter.FragmentCreator
            public String createTitle(String data) {
                return Html.fromHtml(data).toString();
            }
        });
        this.mAdapter = hRVTabFragmentAdapter;
        this.mViewPager.setAdapter(hRVTabFragmentAdapter);
        this.mAdapter.notifyDataSetChanged();
        this.mViewPager.setOffscreenPageLimit(this.mDayHeartRateHisListBean.size() - 1);
        this.mAdapter.setData(this.mTitles);
        this.mSlidingTabLayout.setViewPager(this.mViewPager, (String[]) this.mTitles.toArray(new String[0]));
        this.mSlidingTabLayout.setCurrentTab(2, true);
        this.mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.yucheng.smarthealthpro.home.activity.hrv.activity.HRVActivity.5
            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int state) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int position) {
                if (position == 0) {
                    HRVActivity.this.mViewPager.setCurrentItem(0);
                    if (HRVActivity.this.mMonthAdapterSumUpHeartRateHisListBean != null && HRVActivity.this.mMonthAdapterSumUpHeartRateHisListBean.size() > 0) {
                        HRVActivity.this.tvDataFirst.setText(HRVActivity.this.mMonthAverageHeartNum + "");
                        HRVActivity.this.tvDataSecond.setText(HRVActivity.this.mMonthMaxHeartNum + "");
                        HRVActivity.this.tvDataThirdly.setText(HRVActivity.this.mMonthMinHeartNum + "");
                    } else {
                        HRVActivity.this.tvDataFirst.setText("--");
                        HRVActivity.this.tvDataSecond.setText("--");
                        HRVActivity.this.tvDataThirdly.setText("--");
                        HRVActivity.this.mMonthAverageHeartNum = 0;
                    }
                    HRVActivity.this.tvBackToday.setVisibility(0);
                    HRVActivity.this.llCalendar.setVisibility(8);
                    HRVActivity.this.mHRVHisListAdapter.replaceData(HRVActivity.this.mMonthAdapterSumUpHeartRateHisListBean);
                    HRVActivity.this.mHRVHisListAdapter.notifyDataSetChanged();
                    HRVActivity hRVActivity = HRVActivity.this;
                    hRVActivity.dataAnalysis(hRVActivity.mMonthAverageHeartNum);
                    return;
                }
                if (position == 1) {
                    HRVActivity.this.mViewPager.setCurrentItem(1);
                    if (HRVActivity.this.mWeekAdapterSumUpHeartRateHisListBean != null && HRVActivity.this.mWeekAdapterSumUpHeartRateHisListBean.size() > 0) {
                        HRVActivity.this.tvDataFirst.setText(HRVActivity.this.mWeekAverageHeartNum + "");
                        HRVActivity.this.tvDataSecond.setText(HRVActivity.this.mWeekMaxHeartNum + "");
                        HRVActivity.this.tvDataThirdly.setText(HRVActivity.this.mWeekMinHeartNum + "");
                    } else {
                        HRVActivity.this.tvDataFirst.setText("--");
                        HRVActivity.this.tvDataSecond.setText("--");
                        HRVActivity.this.tvDataThirdly.setText("--");
                        HRVActivity.this.mWeekAverageHeartNum = 0;
                    }
                    HRVActivity.this.tvBackToday.setVisibility(0);
                    HRVActivity.this.llCalendar.setVisibility(8);
                    HRVActivity.this.mHRVHisListAdapter.replaceData(HRVActivity.this.mWeekAdapterSumUpHeartRateHisListBean);
                    HRVActivity.this.mHRVHisListAdapter.notifyDataSetChanged();
                    HRVActivity hRVActivity2 = HRVActivity.this;
                    hRVActivity2.dataAnalysis(hRVActivity2.mWeekAverageHeartNum);
                    return;
                }
                if (position != 2) {
                    return;
                }
                HRVActivity.this.mViewPager.setCurrentItem(2);
                if (HRVActivity.this.mDayHeartRateHisListBean != null && HRVActivity.this.mDayHeartRateHisListBean.size() > 0) {
                    HRVActivity.this.tvDataFirst.setText(HRVActivity.this.mDayAverageHeartNum + "");
                    HRVActivity.this.tvDataSecond.setText(HRVActivity.this.mDayMaxHeartNum + "");
                    HRVActivity.this.tvDataThirdly.setText(HRVActivity.this.mDayMinHeartNum + "");
                } else {
                    HRVActivity.this.tvDataFirst.setText("--");
                    HRVActivity.this.tvDataSecond.setText("--");
                    HRVActivity.this.tvDataThirdly.setText("--");
                    HRVActivity.this.mDayAverageHeartNum = 0;
                }
                HRVActivity.this.tvBackToday.setVisibility(8);
                HRVActivity.this.llCalendar.setVisibility(0);
                HRVActivity.this.mHRVHisListAdapter.replaceData(HRVActivity.this.mDayHeartRateHisListBean);
                HRVActivity.this.mHRVHisListAdapter.notifyDataSetChanged();
                HRVActivity hRVActivity3 = HRVActivity.this;
                hRVActivity3.dataAnalysis(hRVActivity3.mDayAverageHeartNum);
            }
        });
    }

    public void dataAnalysis(int value) {
        if (value > 100) {
            this.tvAnalyseData.setText(getText(R.string.heart_rate_analyse_too_high));
            return;
        }
        if (value > 50) {
            this.tvAnalyseData.setText(getText(R.string.heart_rate_analyse_normal));
        } else if (value > 40) {
            this.tvAnalyseData.setText(getText(R.string.heart_rate_analyse_low_heart_rate));
        } else {
            this.tvAnalyseData.setText("");
        }
    }

    private void setRecycleView() {
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        HRVHisListAdapter hRVHisListAdapter = new HRVHisListAdapter(R.layout.item_universal_his_list);
        this.mHRVHisListAdapter = hRVHisListAdapter;
        hRVHisListAdapter.addData((Collection) this.mDayHeartRateHisListBean);
        this.mRecyclerView.setAdapter(this.mHRVHisListAdapter);
        this.mRecyclerView.setNestedScrollingEnabled(false);
        this.mHRVHisListAdapter.setOnItemClickListener(new HRVHisListAdapter.OnItemClickListener() { // from class: com.yucheng.smarthealthpro.home.activity.hrv.activity.HRVActivity.6
            @Override // com.yucheng.smarthealthpro.home.activity.hrv.adapter.HRVHisListAdapter.OnItemClickListener
            public void onClick(TemperatureHisListBean hisSearch, int position) {
            }

            @Override // com.yucheng.smarthealthpro.home.activity.hrv.adapter.HRVHisListAdapter.OnItemClickListener
            public void onDelClick(TemperatureHisListBean hisSearch, int position) {
            }

            @Override // com.yucheng.smarthealthpro.home.activity.hrv.adapter.HRVHisListAdapter.OnItemClickListener
            public void onLongClick(TemperatureHisListBean hisSearch, int position) {
            }
        });
    }

    private void initMonth() throws Resources.NotFoundException {
        this.mCalendarView.setOnCalendarSelectListener(this);
        this.mCalendarView.setOnMonthChangeListener(this);
        this.mCalendarView.scrollToCurrent();
        this.mCalendarView.setOnCalendarInterceptListener(new CalendarView.OnCalendarInterceptListener() { // from class: com.yucheng.smarthealthpro.home.activity.hrv.activity.HRVActivity.7
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
            getDayHeart(strIntToStr);
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
        List<TemperatureHisListBean> list = this.mDayHeartRateHisListBean;
        if (list != null) {
            list.clear();
        }
        this.mDayChartSumUpHeartRateHisListBean = new ArrayList();
        this.mAllDb = data;
        if (data != null) {
            for (int i2 = 0; i2 < this.mAllDb.size(); i2++) {
                if (TimeStampUtils.dateForStringYearToDate(TimeStampUtils.longStampForDate(this.mAllDb.get(i2).getStartTimestamp())).equals(thatVeryDay) && this.mAllDb.get(i2).getHeartRateVariability() >= TransUtils.HRV_VISIBLE_MIN && this.mAllDb.get(i2).getHeartRateVariability() <= TransUtils.HRV_VISIBLE_MAX && this.mAllDb.get(i2).getHeartRateVariability() != 0) {
                    this.mDayHeartRateHisListBean.add(new TemperatureHisListBean(TimeStampUtils.dateForStringToDate(TimeStampUtils.longStampForDate(this.mAllDb.get(i2).getStartTimestamp())), this.mAllDb.get(i2).getHeartRateVariability() + "", "正常"));
                    this.mDayChartSumUpHeartRateHisListBean.add(new TemperatureHisListBean(TimeStampUtils.dateForStringToDate(TimeStampUtils.longStampForDate(this.mAllDb.get(i2).getStartTimestamp())), this.mAllDb.get(i2).getHeartRateVariability() + "", "正常"));
                }
            }
        }
        if (this.mDayHeartRateHisListBean.size() != 0) {
            this.mThatVeryDay = thatVeryDay;
            this.mDaySumUpHeartNum = 0;
            this.mDayMaxHeartNum = 0;
            this.mDayMinHeartNum = Integer.parseInt(this.mDayHeartRateHisListBean.get(0).getmValue());
            for (int i3 = 0; i3 < this.mDayHeartRateHisListBean.size(); i3++) {
                int i4 = Integer.parseInt(this.mDayHeartRateHisListBean.get(i3).getmValue());
                if (i4 > this.mDayMaxHeartNum) {
                    this.mDayMaxHeartNum = i4;
                }
                if (i4 < this.mDayMinHeartNum) {
                    this.mDayMinHeartNum = i4;
                }
                this.mDaySumUpHeartNum += Integer.parseInt(this.mDayHeartRateHisListBean.get(i3).getmValue());
            }
            this.mDayAverageHeartNum = (int) Math.round((this.mDaySumUpHeartNum * 1.0d) / this.mDayHeartRateHisListBean.size());
        }
        this.isGetDayData = true;
        if (this.isGetMonthData) {
            this.isGetDayData = false;
            initViewPager();
        }
    }

    public void onWeekData(List<HealthMetric> data) throws NumberFormatException {
        this.mWeekHeartRateHisListBean = new ArrayList();
        this.mWeekChartSumUpHeartRateHisListBean = new ArrayList();
        this.mWeekAdapterSumUpHeartRateHisListBean = new ArrayList();
        if (this.mAllDb != null) {
            ArrayList<String> pastStringArray = YearToDayListUtils.getPastStringArray(this.mToDay, 6);
            this.mAllDb = data;
            for (int i2 = 0; i2 < pastStringArray.size(); i2++) {
                getWeekDay(pastStringArray.get(i2), i2);
            }
        }
        if (this.mWeekAdapterSumUpHeartRateHisListBean.size() != 0) {
            Collections.reverse(this.mWeekAdapterSumUpHeartRateHisListBean);
            this.mWeekSumUpHeartNum = 0;
            this.mWeekMaxHeartNum = 0;
            this.mWeekMinHeartNum = Integer.parseInt(this.mWeekAdapterSumUpHeartRateHisListBean.get(0).getmValue());
            for (int i3 = 0; i3 < this.mWeekAdapterSumUpHeartRateHisListBean.size(); i3++) {
                int i4 = Integer.parseInt(this.mWeekAdapterSumUpHeartRateHisListBean.get(i3).getmValue());
                if (i4 > this.mWeekMaxHeartNum) {
                    this.mWeekMaxHeartNum = i4;
                }
                if (i4 < this.mWeekMinHeartNum) {
                    this.mWeekMinHeartNum = i4;
                }
                this.mWeekSumUpHeartNum += Integer.parseInt(this.mWeekAdapterSumUpHeartRateHisListBean.get(i3).getmValue());
            }
            this.mWeekAverageHeartNum = (int) Math.round((this.mWeekSumUpHeartNum * 1.0d) / this.mWeekAdapterSumUpHeartRateHisListBean.size());
        }
        Log.i("mWeekAdapter", "---" + this.mWeekMaxHeartNum + "mWeekMinHeartNum" + this.mWeekMinHeartNum + "mWeekSumUpHeartNum" + this.mWeekSumUpHeartNum);
    }

    private void getWeekDay(String mThatVeryDay, int index) {
        for (int i2 = 0; i2 < this.mAllDb.size(); i2++) {
            if (TimeStampUtils.dateForStringYearToDate(TimeStampUtils.longStampForDate(this.mAllDb.get(i2).getStartTimestamp())).equals(mThatVeryDay) && this.mAllDb.get(i2).getHeartRateVariability() >= TransUtils.HRV_VISIBLE_MIN && this.mAllDb.get(i2).getHeartRateVariability() <= TransUtils.HRV_VISIBLE_MAX) {
                this.mWeekHeartRateHisListBean.add(new TemperatureHisListBean(TimeStampUtils.dateForStringToDate(TimeStampUtils.longStampForDate(this.mAllDb.get(i2).getStartTimestamp())), this.mAllDb.get(i2).getHeartRateVariability() + "", "正常"));
            }
        }
        if (this.mWeekHeartRateHisListBean != null) {
            this.mWeekSumUpHeartNum = 0;
            for (int i3 = 0; i3 < this.mWeekHeartRateHisListBean.size(); i3++) {
                this.mWeekSumUpHeartNum += Integer.parseInt(this.mWeekHeartRateHisListBean.get(i3).getmValue());
            }
            if (this.mWeekSumUpHeartNum != 0) {
                this.mWeekAdapterSumUpHeartRateHisListBean.add(new TemperatureHisListBean(TimeStampUtils.dateForStringDates(TimeStampUtils.stringForDateDay(mThatVeryDay)), Math.round((this.mWeekSumUpHeartNum * 1.0f) / this.mWeekHeartRateHisListBean.size()) + "", "正常"));
            }
            if (this.mWeekSumUpHeartNum != 0 && this.mWeekHeartRateHisListBean.size() != 0) {
                this.mWeekChartSumUpHeartRateHisListBean.add(index, new TemperatureHisListBean(TimeStampUtils.dateForStringDates(TimeStampUtils.stringForDateDay(mThatVeryDay)), Math.round((this.mWeekSumUpHeartNum * 1.0f) / this.mWeekHeartRateHisListBean.size()) + "", "正常"));
            } else {
                this.mWeekChartSumUpHeartRateHisListBean.add(index, new TemperatureHisListBean(TimeStampUtils.dateForStringDates(TimeStampUtils.stringForDateDay(mThatVeryDay)), "0", "正常"));
            }
            this.mWeekHeartRateHisListBean.clear();
        }
    }

    public void onMonthData(List<HealthMetric> data) throws NumberFormatException {
        this.mMonthHeartRateHisListBean = new ArrayList();
        this.mMonthChartSumUpHeartRateHisListBean = new ArrayList();
        this.mMonthAdapterSumUpHeartRateHisListBean = new ArrayList();
        if (this.mAllDb != null) {
            ArrayList<String> pastStringArray = YearToDayListUtils.getPastStringArray(this.mToDay, 29);
            this.mAllDb = data;
            for (int i2 = 0; i2 < pastStringArray.size(); i2++) {
                getMonthDay(pastStringArray.get(i2), i2);
            }
        }
        if (this.mMonthAdapterSumUpHeartRateHisListBean.size() != 0) {
            Collections.reverse(this.mMonthAdapterSumUpHeartRateHisListBean);
            this.mMonthSumUpHeartNum = 0;
            this.mMonthMaxHeartNum = 0;
            this.mMonthMinHeartNum = Integer.parseInt(this.mMonthAdapterSumUpHeartRateHisListBean.get(0).getmValue());
            for (int i3 = 0; i3 < this.mMonthAdapterSumUpHeartRateHisListBean.size(); i3++) {
                int i4 = Integer.parseInt(this.mMonthAdapterSumUpHeartRateHisListBean.get(i3).getmValue());
                if (i4 > this.mMonthMaxHeartNum) {
                    this.mMonthMaxHeartNum = i4;
                }
                if (i4 < this.mMonthMinHeartNum) {
                    this.mMonthMinHeartNum = i4;
                }
                this.mMonthSumUpHeartNum += Integer.parseInt(this.mMonthAdapterSumUpHeartRateHisListBean.get(i3).getmValue());
            }
            this.mMonthAverageHeartNum = (int) Math.round((this.mMonthSumUpHeartNum * 1.0d) / this.mMonthAdapterSumUpHeartRateHisListBean.size());
        }
        runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.hrv.activity.HRVActivity.8
            @Override // java.lang.Runnable
            public void run() throws Resources.NotFoundException {
                if (HRVActivity.this.isGetDayData) {
                    HRVActivity.this.isGetMonthData = true;
                    HRVActivity.this.initViewPager();
                }
            }
        });
    }

    private void getMonthDay(String mThatVeryDay, int index) {
        for (int i2 = 0; i2 < this.mAllDb.size(); i2++) {
            if (TimeStampUtils.dateForStringYearToDate(TimeStampUtils.longStampForDate(this.mAllDb.get(i2).getStartTimestamp())).equals(mThatVeryDay) && this.mAllDb.get(i2).getHeartRateVariability() >= TransUtils.HRV_VISIBLE_MIN && this.mAllDb.get(i2).getHeartRateVariability() <= TransUtils.HRV_VISIBLE_MAX) {
                this.mMonthHeartRateHisListBean.add(new TemperatureHisListBean(TimeStampUtils.dateForStringToDate(TimeStampUtils.longStampForDate(this.mAllDb.get(i2).getStartTimestamp())), this.mAllDb.get(i2).getHeartRateVariability() + "", "正常"));
            }
        }
        if (this.mMonthHeartRateHisListBean != null) {
            this.mMonthSumUpHeartNum = 0;
            for (int i3 = 0; i3 < this.mMonthHeartRateHisListBean.size(); i3++) {
                this.mMonthSumUpHeartNum += Integer.parseInt(this.mMonthHeartRateHisListBean.get(i3).getmValue());
            }
            if (this.mMonthSumUpHeartNum != 0) {
                this.mMonthAdapterSumUpHeartRateHisListBean.add(new TemperatureHisListBean(TimeStampUtils.dateForStringDates(TimeStampUtils.stringForDateDay(mThatVeryDay)), Math.round((this.mMonthSumUpHeartNum * 1.0f) / this.mMonthHeartRateHisListBean.size()) + "", "正常"));
            }
            if (this.mMonthSumUpHeartNum != 0 && this.mMonthHeartRateHisListBean.size() != 0) {
                this.mMonthChartSumUpHeartRateHisListBean.add(index, new TemperatureHisListBean(TimeStampUtils.dateForStringDates(TimeStampUtils.stringForDateDay(mThatVeryDay)), Math.round((this.mMonthSumUpHeartNum * 1.0f) / this.mMonthHeartRateHisListBean.size()) + "", "正常"));
            } else {
                this.mMonthChartSumUpHeartRateHisListBean.add(index, new TemperatureHisListBean(TimeStampUtils.dateForStringDates(TimeStampUtils.stringForDateDay(mThatVeryDay)), "0", "正常"));
            }
            this.mMonthHeartRateHisListBean.clear();
        }
    }

    private void getDayHeart(String dateTime) {
        HashMap map = new HashMap();
        map.put("userId", getIntent().getExtras().getString(Constant.SpConstKey.DEV_ID));
        map.put("day", dateTime);
        HttpUtils.getInstance().postMsgAsynHttp(this, com.yucheng.smarthealthpro.framework.util.Constants.hrvDayUrl, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.home.activity.hrv.activity.HRVActivity.9
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                if (result != null) {
                    HRVActivity.this.temp_bean = (HistoryHRVResponse) new Gson().fromJson(result, HistoryHRVResponse.class);
                    HRVActivity.this.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.hrv.activity.HRVActivity.9.1
                        @Override // java.lang.Runnable
                        public void run() throws Resources.NotFoundException {
                            HRVActivity.this.setDayData();
                            HRVActivity.this.initViewPager();
                        }
                    });
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v11, types: [java.util.List] */
    public void setDayData() {
        List<TemperatureHisListBean> list = this.mDayHeartRateHisListBean;
        if (list != null) {
            list.clear();
        }
        List<TemperatureHisListBean> list2 = this.mDayChartSumUpHeartRateHisListBean;
        if (list2 != null) {
            list2.clear();
        }
        HistoryHRVResponse historyHRVResponse = this.temp_bean;
        if (historyHRVResponse == null || historyHRVResponse.data == null || this.temp_bean.data.size() <= 0) {
            return;
        }
        ArrayList<HistoryHRVResponse.Mlist> arrayList = new ArrayList();
        try {
            arrayList = (List) new Gson().fromJson(this.temp_bean.data.get(0).mlist, new TypeToken<List<HistoryHRVResponse.Mlist>>() { // from class: com.yucheng.smarthealthpro.home.activity.hrv.activity.HRVActivity.10
            }.getType());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        this.mDaySumUpHeartNum = 0;
        this.mDayMaxHeartNum = 0;
        this.mDayMinHeartNum = TransUtils.HRV_VISIBLE_MAX;
        Collections.sort(arrayList);
        for (HistoryHRVResponse.Mlist mlist : arrayList) {
            int i2 = mlist.hrv;
            if (i2 >= TransUtils.HRV_VISIBLE_MIN && i2 <= TransUtils.HRV_VISIBLE_MAX) {
                if (i2 > this.mDayMaxHeartNum) {
                    this.mDayMaxHeartNum = i2;
                }
                if (i2 < this.mDayMinHeartNum) {
                    this.mDayMinHeartNum = i2;
                }
                this.mDaySumUpHeartNum += i2;
                this.mDayHeartRateHisListBean.add(new TemperatureHisListBean(TimeStampUtils.dateForStringToDate(TimeStampUtils.longStampForDate(mlist.rtime)), i2 + "", "正常"));
                this.mDayChartSumUpHeartRateHisListBean.add(new TemperatureHisListBean(TimeStampUtils.dateForStringToDate(TimeStampUtils.longStampForDate(mlist.rtime)), i2 + "", "正常"));
            }
        }
        if (this.mDayHeartRateHisListBean.size() > 0) {
            this.mDayAverageHeartNum = (int) Math.round((this.mDaySumUpHeartNum * 1.0d) / this.mDayHeartRateHisListBean.size());
        }
    }

    private void getWeekHeart(String dateTime, String startTime, String endTime) {
        HashMap map = new HashMap();
        map.put("userId", getIntent().getExtras().getString(Constant.SpConstKey.DEV_ID));
        map.put("day", dateTime);
        map.put("startDate", startTime);
        map.put("endDate", endTime);
        map.put("type", "1");
        HttpUtils.getInstance().postMsgAsynHttp(this, com.yucheng.smarthealthpro.framework.util.Constants.hrvDayUrl, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.home.activity.hrv.activity.HRVActivity.11
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                if (result != null) {
                    List<CareHRVWeekMonthBean.DataBean> data = ((CareHRVWeekMonthBean) new Gson().fromJson(result, CareHRVWeekMonthBean.class)).getData();
                    if (data.size() != 0) {
                        HRVActivity.this.mWeekSumUpHeartNum = 0;
                        HRVActivity.this.mWeekMaxHeartNum = 0;
                        HRVActivity.this.mWeekMinHeartNum = TransUtils.HRV_VISIBLE_MAX;
                        int i2 = 0;
                        while (true) {
                            if (i2 >= data.size()) {
                                break;
                            }
                            int i3 = (int) Float.parseFloat(data.get(i2).getHrvMean().isEmpty() ? "0" : data.get(i2).getHrvMean());
                            if (i3 >= TransUtils.HRV_VISIBLE_MIN && i3 <= TransUtils.HRV_VISIBLE_MAX) {
                                if (i3 > HRVActivity.this.mWeekMaxHeartNum) {
                                    HRVActivity.this.mWeekMaxHeartNum = i3;
                                }
                                if (i3 < HRVActivity.this.mWeekMinHeartNum) {
                                    HRVActivity.this.mWeekMinHeartNum = i3;
                                }
                                HRVActivity.this.mWeekSumUpHeartNum += i3;
                                if (!data.get(i2).getHrvMean().isEmpty()) {
                                    HRVActivity.this.mWeekAdapterSumUpHeartRateHisListBean.add(new TemperatureHisListBean(TimeStampUtils.dateForStringDates(TimeStampUtils.stringForDateDay(data.get(i2).getDateformat())), i3 + "", "正常"));
                                }
                            }
                            i2++;
                        }
                        if (HRVActivity.this.mWeekAdapterSumUpHeartRateHisListBean.size() > 0) {
                            HRVActivity.this.mWeekAverageHeartNum = (int) Math.round((r10.mWeekSumUpHeartNum * 1.0d) / HRVActivity.this.mWeekAdapterSumUpHeartRateHisListBean.size());
                        }
                        ArrayList<String> pastByMonthDayArray = YearToDayListUtils.getPastByMonthDayArray(HRVActivity.this.mToDay, 6);
                        for (int i4 = 0; i4 < pastByMonthDayArray.size(); i4++) {
                            HRVActivity.this.mWeekChartSumUpHeartRateHisListBean.add(new TemperatureHisListBean(pastByMonthDayArray.get(i4), "0", "正常"));
                        }
                        for (int i5 = 0; i5 < pastByMonthDayArray.size(); i5++) {
                            Iterator it2 = HRVActivity.this.mWeekAdapterSumUpHeartRateHisListBean.iterator();
                            while (true) {
                                if (it2.hasNext()) {
                                    TemperatureHisListBean temperatureHisListBean = (TemperatureHisListBean) it2.next();
                                    if (temperatureHisListBean.getTime().equals(pastByMonthDayArray.get(i5))) {
                                        HRVActivity.this.mWeekChartSumUpHeartRateHisListBean.remove(i5);
                                        HRVActivity.this.mWeekChartSumUpHeartRateHisListBean.add(i5, new TemperatureHisListBean(temperatureHisListBean.getTime(), temperatureHisListBean.getmValue(), "正常"));
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });
    }

    private void getMonthHeart(String dateTime, String startTime, String endTime) {
        HashMap map = new HashMap();
        map.put("userId", getIntent().getExtras().getString(Constant.SpConstKey.DEV_ID));
        map.put("day", dateTime);
        map.put("startDate", startTime);
        map.put("endDate", endTime);
        map.put("type", "1");
        HttpUtils.getInstance().postMsgAsynHttp(this, com.yucheng.smarthealthpro.framework.util.Constants.hrvDayUrl, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.home.activity.hrv.activity.HRVActivity.12
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                if (result != null) {
                    List<CareHRVWeekMonthBean.DataBean> data = ((CareHRVWeekMonthBean) new Gson().fromJson(result, CareHRVWeekMonthBean.class)).getData();
                    if (data.size() != 0) {
                        HRVActivity.this.mMonthChartSumUpHeartRateHisListBean.clear();
                        HRVActivity.this.mMonthSumUpHeartNum = 0;
                        HRVActivity.this.mMonthMaxHeartNum = 0;
                        HRVActivity.this.mMonthMinHeartNum = TransUtils.HRV_VISIBLE_MAX;
                        int i2 = 0;
                        while (true) {
                            if (i2 >= data.size()) {
                                break;
                            }
                            int i3 = (int) Float.parseFloat(data.get(i2).getHrvMean().isEmpty() ? "0" : data.get(i2).getHrvMean());
                            if (i3 >= TransUtils.HRV_VISIBLE_MIN && i3 <= TransUtils.HRV_VISIBLE_MAX) {
                                if (i3 > HRVActivity.this.mMonthMaxHeartNum) {
                                    HRVActivity.this.mMonthMaxHeartNum = i3;
                                }
                                if (i3 < HRVActivity.this.mMonthMinHeartNum) {
                                    HRVActivity.this.mMonthMinHeartNum = i3;
                                }
                                HRVActivity.this.mMonthSumUpHeartNum += i3;
                                if (!data.get(i2).getHrvMean().isEmpty()) {
                                    HRVActivity.this.mMonthAdapterSumUpHeartRateHisListBean.add(new TemperatureHisListBean(TimeStampUtils.dateForStringDates(TimeStampUtils.stringForDateDay(data.get(i2).getDateformat())), i3 + "", "正常"));
                                }
                            }
                            i2++;
                        }
                        if (HRVActivity.this.mMonthAdapterSumUpHeartRateHisListBean.size() > 0) {
                            HRVActivity.this.mMonthAverageHeartNum = (int) Math.round((r10.mMonthSumUpHeartNum * 1.0d) / HRVActivity.this.mMonthAdapterSumUpHeartRateHisListBean.size());
                        }
                        ArrayList<String> pastByMonthDayArray = YearToDayListUtils.getPastByMonthDayArray(HRVActivity.this.mToDay, 29);
                        for (int i4 = 0; i4 < pastByMonthDayArray.size(); i4++) {
                            HRVActivity.this.mMonthChartSumUpHeartRateHisListBean.add(new TemperatureHisListBean(pastByMonthDayArray.get(i4), "0", "正常"));
                        }
                        for (int i5 = 0; i5 < pastByMonthDayArray.size(); i5++) {
                            Iterator it2 = HRVActivity.this.mMonthAdapterSumUpHeartRateHisListBean.iterator();
                            while (true) {
                                if (it2.hasNext()) {
                                    TemperatureHisListBean temperatureHisListBean = (TemperatureHisListBean) it2.next();
                                    if (temperatureHisListBean.getTime().equals(pastByMonthDayArray.get(i5))) {
                                        HRVActivity.this.mMonthChartSumUpHeartRateHisListBean.remove(i5);
                                        HRVActivity.this.mMonthChartSumUpHeartRateHisListBean.add(i5, new TemperatureHisListBean(temperatureHisListBean.getTime(), temperatureHisListBean.getmValue(), "正常"));
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) throws Resources.NotFoundException {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == HRV_MEASURE && resultCode == -1) {
            initData();
        }
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity
    public void onActivityResult(ActivityResult result, int requestCode) throws Resources.NotFoundException {
        super.onActivityResult(result, requestCode);
        if (requestCode == HRV_MEASURE && result.getResultCode() == -1) {
            initData();
        } else if (requestCode == MeasureTipActivity.MEASURE_TIP) {
            launchActivityForResult(HRV_MEASURE, HRVMeasureActivity.class);
        }
    }

    public void onViewClicked(View view) throws Resources.NotFoundException {
        if (view.getId() == R.id.ll_calendar) {
            this.llMonth.setVisibility(0);
            return;
        }
        if (view.getId() == R.id.tv_back_today) {
            this.mViewPager.setCurrentItem(2);
            this.mCalendarView.scrollToCurrent();
            return;
        }
        if (view.getId() == R.id.ll_start_button) {
            Boolean bool = (Boolean) SharedPreferencesUtils.get(getActivity(), Constant.SpConstKey.NotShowMeasureTip, false);
            if (Constant.isHealthWear() || bool.booleanValue()) {
                startActivityForResult(new Intent(this.context, (Class<?>) HRVMeasureActivity.class), HRV_MEASURE);
                return;
            } else {
                launchActivityForResult(MeasureTipActivity.MEASURE_TIP, MeasureTipActivity.class);
                return;
            }
        }
        if (view.getId() == R.id.rl_analyse) {
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
                this.mNestedScrollView.postDelayed(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.hrv.activity.HRVActivity.13
                    @Override // java.lang.Runnable
                    public void run() {
                        HRVActivity.this.mNestedScrollView.smoothScrollTo(0, (int) (HRVActivity.this.mNestedScrollView.getScrollY() + (DpUtil.dp2px(HRVActivity.this.context, 56.0f) * 1.5f)));
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
