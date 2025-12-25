package com.yucheng.smarthealthpro.home.activity.bloodsugar.activity;

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
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.haibin.calendarview.CalendarView;
import com.orhanobut.logger.Logger;
import com.tencent.bugly.crashreport.CrashReport;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.care.bean.CareBloodSugarWeekMonthBean;
import com.yucheng.smarthealthpro.care.bean.FriendCareBloodSugarBean;
import com.yucheng.smarthealthpro.care.bean.FriendCareDataBean;
import com.yucheng.smarthealthpro.data.packed.HealthDayData;
import com.yucheng.smarthealthpro.data.packed.HealthPackedData;
import com.yucheng.smarthealthpro.database.room.bean.HealthMetric;
import com.yucheng.smarthealthpro.databinding.ActivityBloodSugarBinding;
import com.yucheng.smarthealthpro.framework.http.HttpUtils;
import com.yucheng.smarthealthpro.framework.util.Constants;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import com.yucheng.smarthealthpro.home.activity.HealthyActivity;
import com.yucheng.smarthealthpro.home.activity.bloodoxygen.adapter.BoTabFragmentAdapter;
import com.yucheng.smarthealthpro.home.activity.bloodsugar.adapter.BloodSugarHisListAdapter;
import com.yucheng.smarthealthpro.home.activity.bloodsugar.fragment.BloodSugarTabFragment;
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
import com.yucheng.smarthealthpro.utils.FormatUtil;
import com.yucheng.smarthealthpro.utils.ShareUtils;
import com.yucheng.smarthealthpro.utils.TimeStampUtils;
import com.yucheng.smarthealthpro.utils.Tools;
import com.yucheng.smarthealthpro.utils.TransUtils;
import com.yucheng.smarthealthpro.utils.YearToDayListUtils;
import com.yucheng.smarthealthpro.viewmodel.BloodSugarViewModel;
import com.yucheng.ycbtsdk.YCBTClient;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class BloodSugarActivity extends BaseVbActivity<ActivityBloodSugarBinding> implements CalendarView.OnCalendarSelectListener, CalendarView.OnMonthChangeListener {
    public static final String IS_HAS_BLOODSUGAR_MEASUREMENT = "isHasBloodSugarMeasurement";
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
    private BoTabFragmentAdapter mAdapter;
    private List<HealthMetric> mAllDb;
    private BloodSugarHisListAdapter mBloodSugarHisListAdapter;
    private String mBloodSugarUnit;
    private Calendar mCalendar;
    CalendarView mCalendarView;
    private float mDayAverageBloodSugarNum;
    private float mDayMaxBloodSugarNum;
    private float mDayMinBloodSugarNum;
    private float mDaySumUpBloodSugarNum;
    private float mMonthAverageBloodSugarNum;
    private float mMonthMaxBloodSugarNum;
    private float mMonthMinBloodSugarNum;
    private float mMonthSumUpBloodSugarNum;
    NestedScrollView mNestedScrollView;
    RecyclerView mRecyclerView;
    SlidingTabLayout mSlidingTabLayout;
    private String mToDay;
    private BloodSugarViewModel mViewModel;
    NoScrollViewPager mViewPager;
    private float mWeekAverageBloodSugarNum;
    private float mWeekMaxBloodSugarNum;
    private float mWeekMinBloodSugarNum;
    private float mWeekSumUpBloodSugarNum;
    private int monthLastDay;
    RelativeLayout rlAnalyse;
    RelativeLayout rlDataFirst;
    RelativeLayout rlFirst;
    RelativeLayout rlFourthly;
    RelativeLayout rlSecond;
    private FriendCareDataBean temp_bean;
    TextView tvAdditionalMsg;
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
    private List<TemperatureHisListBean> mDayBloodSugarHisListBean = new ArrayList();
    private List<TemperatureHisListBean> mDayChartSumUpBloodSugarHisListBean = new ArrayList();
    private List<TemperatureHisListBean> mWeekBloodSugarHisListBean = new ArrayList();
    private List<TemperatureHisListBean> mWeekAdapterBloodSugarHisListBean = new ArrayList();
    private List<TemperatureHisListBean> mWeekChartSumUpBloodSugarHisListBean = new ArrayList();
    private List<TemperatureHisListBean> mMonthBloodSugarHisListBean = new ArrayList();
    private List<TemperatureHisListBean> mMonthAdapterBloodSugarHisListBean = new ArrayList();
    private List<TemperatureHisListBean> mMonthChartSumUpBloodSugarHisListBean = new ArrayList();
    private Boolean isCare = false;
    private final int BLOOD_SUGAR_MEASURE = 0;

    @Override // com.haibin.calendarview.CalendarView.OnCalendarSelectListener
    public void onCalendarOutOfRange(com.haibin.calendarview.Calendar calendar) {
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) throws Resources.NotFoundException {
        super.onCreate(savedInstanceState);
        initView();
        this.mBloodSugarUnit = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.BLOOD_SUGAR_AND_BLOOD_FAT_UNIT, getString(R.string.blood_sugar_unit_1));
        initViewModel();
        initData();
    }

    private void initView() {
        this.mSlidingTabLayout = ((ActivityBloodSugarBinding) this.mBinding).includeItemTop.stlTab;
        this.ivCalendar = ((ActivityBloodSugarBinding) this.mBinding).includeItemTop.ivCalendar;
        this.tvCalendar = ((ActivityBloodSugarBinding) this.mBinding).includeItemTop.tvCalendar;
        this.tvBackToday = ((ActivityBloodSugarBinding) this.mBinding).includeItemTop.tvBackToday;
        this.llCalendar = ((ActivityBloodSugarBinding) this.mBinding).includeItemTop.llCalendar;
        this.mViewPager = ((ActivityBloodSugarBinding) this.mBinding).includeItemTop.vpTab;
        this.tvDataFirst = ((ActivityBloodSugarBinding) this.mBinding).includeItemMessageData.tvDataFirst;
        this.tvDataFirstUnit = ((ActivityBloodSugarBinding) this.mBinding).includeItemMessageData.tvDataFirstUnit;
        this.rlDataFirst = ((ActivityBloodSugarBinding) this.mBinding).includeItemMessageData.rlDataFirst;
        this.tvDataSecond = ((ActivityBloodSugarBinding) this.mBinding).includeItemMessageData.tvDataSecond;
        this.ivDataSecond = ((ActivityBloodSugarBinding) this.mBinding).includeItemMessageData.ivDataSecond;
        this.tvDataSecondUnit = ((ActivityBloodSugarBinding) this.mBinding).includeItemMessageData.tvDataSecondUnit;
        this.llDataSecond = ((ActivityBloodSugarBinding) this.mBinding).includeItemMessageData.llDataSecond;
        this.tvDataThirdly = ((ActivityBloodSugarBinding) this.mBinding).includeItemMessageData.tvDataThirdly;
        this.ivDataThirdly = ((ActivityBloodSugarBinding) this.mBinding).includeItemMessageData.ivDataThirdly;
        this.tvDataThirdlyUnit = ((ActivityBloodSugarBinding) this.mBinding).includeItemMessageData.tvDataThirdlyUnit;
        this.llDataThirdly = ((ActivityBloodSugarBinding) this.mBinding).includeItemMessageData.llDataThirdly;
        this.tvStartButton = ((ActivityBloodSugarBinding) this.mBinding).includeItemBottom.tvStartButton;
        this.llStartButton = ((ActivityBloodSugarBinding) this.mBinding).includeItemBottom.llStartButton;
        this.tvAdditionalMsg = ((ActivityBloodSugarBinding) this.mBinding).includeItemBottom.tvAdditionalMsg;
        this.tvAnalyse = ((ActivityBloodSugarBinding) this.mBinding).includeItemBottom.tvAnalyse;
        this.tvAnalyseData = ((ActivityBloodSugarBinding) this.mBinding).includeItemBottom.tvAnalyseData;
        this.rlAnalyse = ((ActivityBloodSugarBinding) this.mBinding).includeItemBottom.rlAnalyse;
        this.ivFirstLeft = ((ActivityBloodSugarBinding) this.mBinding).includeItemBottom.ivFirstLeft;
        this.tvFirst = ((ActivityBloodSugarBinding) this.mBinding).includeItemBottom.tvFirst;
        this.ivFirstRight = ((ActivityBloodSugarBinding) this.mBinding).includeItemBottom.ivFirstRight;
        this.rlFirst = ((ActivityBloodSugarBinding) this.mBinding).includeItemBottom.rlFirst;
        this.ivSecondLeft = ((ActivityBloodSugarBinding) this.mBinding).includeItemBottom.ivSecondLeft;
        this.tvSecond = ((ActivityBloodSugarBinding) this.mBinding).includeItemBottom.tvSecond;
        this.ivSecondRight = ((ActivityBloodSugarBinding) this.mBinding).includeItemBottom.ivSecondRight;
        this.rlSecond = ((ActivityBloodSugarBinding) this.mBinding).includeItemBottom.rlSecond;
        this.ivFourthlyLeft = ((ActivityBloodSugarBinding) this.mBinding).includeItemBottom.ivFourthlyLeft;
        this.tvFourthly = ((ActivityBloodSugarBinding) this.mBinding).includeItemBottom.tvFourthly;
        this.ivFourthlyRight = ((ActivityBloodSugarBinding) this.mBinding).includeItemBottom.ivFourthlyRight;
        this.rlFourthly = ((ActivityBloodSugarBinding) this.mBinding).includeItemBottom.rlFourthly;
        this.mRecyclerView = ((ActivityBloodSugarBinding) this.mBinding).includeItemBottom.recycleView;
        this.mNestedScrollView = ((ActivityBloodSugarBinding) this.mBinding).nsv;
        this.tvYears = ((ActivityBloodSugarBinding) this.mBinding).includeItemCalendar.tvYears;
        this.mCalendarView = ((ActivityBloodSugarBinding) this.mBinding).includeItemCalendar.calendarView;
        this.llMonth = ((ActivityBloodSugarBinding) this.mBinding).includeItemCalendar.llMonth;
        this.llCalendar.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.bloodsugar.activity.BloodSugarActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.tvBackToday.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.bloodsugar.activity.BloodSugarActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.llStartButton.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.bloodsugar.activity.BloodSugarActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlAnalyse.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.bloodsugar.activity.BloodSugarActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlFirst.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.bloodsugar.activity.BloodSugarActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlSecond.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.bloodsugar.activity.BloodSugarActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlFourthly.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.bloodsugar.activity.BloodSugarActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.llMonth.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.bloodsugar.activity.BloodSugarActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        changeTitle(getString(R.string.home_blood_sugar_title));
        showBack();
        showRightImage(R.mipmap.topbar_ic_share, new NavigationBar.MyOnClickListener() { // from class: com.yucheng.smarthealthpro.home.activity.bloodsugar.activity.BloodSugarActivity.1
            @Override // com.yucheng.smarthealthpro.framework.view.NavigationBar.MyOnClickListener
            public void onClick(View btn) {
                ShareUtils.share(BloodSugarActivity.this);
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
            this.tvAnalyse.setText(getString(R.string.home_blood_sugar_analyse_tv));
            this.llStartButton.setVisibility(8);
            this.rlFirst.setVisibility(8);
            this.rlSecond.setVisibility(8);
            this.ivFourthlyRight.setBackground(ResourcesCompat.getDrawable(getResources(), R.mipmap.list_ic_arrow_n, null));
            return;
        }
        if (YCBTClient.isSupportFunction("isHasBloodSugarMeasurement") && !Constant.isSmartHealth()) {
            this.llStartButton.setVisibility(0);
            this.tvAdditionalMsg.setVisibility(0);
        } else {
            this.llStartButton.setVisibility(8);
        }
        this.tvStartButton.setText(getString(R.string.home_blood_sugar_measure));
        this.tvAnalyse.setText(getString(R.string.home_blood_sugar_analyse_tv));
        this.rlFirst.setVisibility(0);
        this.tvSecond.setText(getString(R.string.include_bottom_tv_second_button));
        this.tvFourthly.setText(getString(R.string.include_bottom_tv_fourthly_button));
        this.ivFourthlyRight.setBackground(ResourcesCompat.getDrawable(getResources(), R.mipmap.list_ic_arrow_n, null));
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
    }

    private void initViewModel() {
        this.mViewModel = (BloodSugarViewModel) new ViewModelProvider(this).get(BloodSugarViewModel.class);
        FlowUtils.INSTANCE.collectFlow(this, this.mViewModel.getBloodSugarDataFlow(), new FlowUtils.FlowCollector<HealthDayData<HealthMetric>>() { // from class: com.yucheng.smarthealthpro.home.activity.bloodsugar.activity.BloodSugarActivity.2
            @Override // com.yucheng.smarthealthpro.utils.FlowUtils.FlowCollector
            public void collect(HealthDayData<HealthMetric> data) throws Resources.NotFoundException, NumberFormatException {
                BloodSugarActivity.this.onThatVeryDayData(data.getDay(), data.getData());
            }
        });
        FlowUtils.INSTANCE.collectFlow(this, this.mViewModel.getBloodSugarPackedDataFlow(), new FlowUtils.FlowCollector<HealthPackedData<HealthMetric>>() { // from class: com.yucheng.smarthealthpro.home.activity.bloodsugar.activity.BloodSugarActivity.3
            @Override // com.yucheng.smarthealthpro.utils.FlowUtils.FlowCollector
            public void collect(HealthPackedData<HealthMetric> packedData) throws NumberFormatException {
                if (packedData.getDayCount() == 7) {
                    BloodSugarActivity.this.onWeekData(packedData.getData());
                } else if (packedData.getDayCount() == 30) {
                    BloodSugarActivity.this.onMonthData(packedData.getData());
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
        if (this.isCare.booleanValue()) {
            ArrayList<String> pastStringArray = YearToDayListUtils.getPastStringArray(this.mToDay, 6);
            ArrayList<String> pastStringArray2 = YearToDayListUtils.getPastStringArray(this.mToDay, 29);
            getWeekBo(pastStringArray.get(0), pastStringArray.get(0), pastStringArray.get(6));
            getMonthBo(pastStringArray2.get(0), pastStringArray2.get(0), pastStringArray2.get(29));
            return;
        }
        this.mViewModel.getDayData(this.mToDay);
        new Thread(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.bloodsugar.activity.BloodSugarActivity.4
            @Override // java.lang.Runnable
            public void run() {
                BloodSugarActivity.this.mViewModel.getPackedDayData(YearToDayListUtils.getPastStringArray(BloodSugarActivity.this.mToDay, 6).get(0), 7);
                BloodSugarActivity.this.mViewModel.getPackedDayData(YearToDayListUtils.getPastStringArray(BloodSugarActivity.this.mToDay, 29).get(0), 30);
            }
        }).start();
    }

    @Override // com.yucheng.smarthealthpro.framework.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initViewPager() throws Resources.NotFoundException {
        if (isFinishing()) {
            return;
        }
        BoTabFragmentAdapter boTabFragmentAdapter = new BoTabFragmentAdapter(getSupportFragmentManager(), new BoTabFragmentAdapter.FragmentCreator() { // from class: com.yucheng.smarthealthpro.home.activity.bloodsugar.activity.BloodSugarActivity.5
            @Override // com.yucheng.smarthealthpro.home.activity.bloodoxygen.adapter.BoTabFragmentAdapter.FragmentCreator
            public Fragment createFragment(String data, int position) {
                return BloodSugarTabFragment.newInstance(data.toString(), position, BloodSugarActivity.this.mNestedScrollView, BloodSugarActivity.this.mDayChartSumUpBloodSugarHisListBean, BloodSugarActivity.this.mWeekChartSumUpBloodSugarHisListBean, BloodSugarActivity.this.mMonthChartSumUpBloodSugarHisListBean, BloodSugarActivity.this.mDayMaxBloodSugarNum, BloodSugarActivity.this.mDayMinBloodSugarNum);
            }

            @Override // com.yucheng.smarthealthpro.home.activity.bloodoxygen.adapter.BoTabFragmentAdapter.FragmentCreator
            public String createTitle(String data) {
                return Html.fromHtml(data).toString();
            }
        });
        this.mAdapter = boTabFragmentAdapter;
        NoScrollViewPager noScrollViewPager = this.mViewPager;
        if (noScrollViewPager == null) {
            return;
        }
        noScrollViewPager.setAdapter(boTabFragmentAdapter);
        this.mAdapter.notifyDataSetChanged();
        this.mViewPager.setOffscreenPageLimit(this.mDayBloodSugarHisListBean.size() - 1);
        this.mAdapter.setData(this.mTitles);
        this.mSlidingTabLayout.setViewPager(this.mViewPager, (String[]) this.mTitles.toArray(new String[0]));
        this.mSlidingTabLayout.setCurrentTab(2, true);
        this.mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.yucheng.smarthealthpro.home.activity.bloodsugar.activity.BloodSugarActivity.6
            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int state) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int position) {
                if (position == 0) {
                    BloodSugarActivity.this.mViewPager.setCurrentItem(0);
                    if (BloodSugarActivity.this.mMonthAdapterBloodSugarHisListBean == null || BloodSugarActivity.this.mMonthAdapterBloodSugarHisListBean.size() <= 0) {
                        BloodSugarActivity.this.tvDataFirst.setText("--");
                        BloodSugarActivity.this.tvDataSecond.setText("--");
                        BloodSugarActivity.this.tvDataThirdly.setText("--");
                        BloodSugarActivity.this.mMonthAverageBloodSugarNum = 0.0f;
                    } else {
                        BloodSugarActivity.this.tvDataFirst.setText(FormatUtil.keep1NoZero(BloodSugarActivity.this.mMonthAverageBloodSugarNum));
                        BloodSugarActivity.this.tvDataSecond.setText(FormatUtil.keep1NoZero(BloodSugarActivity.this.mMonthMaxBloodSugarNum));
                        BloodSugarActivity.this.tvDataThirdly.setText(FormatUtil.keep1NoZero(BloodSugarActivity.this.mMonthMinBloodSugarNum));
                    }
                    BloodSugarActivity.this.tvBackToday.setVisibility(0);
                    BloodSugarActivity.this.llCalendar.setVisibility(8);
                    BloodSugarActivity.this.mBloodSugarHisListAdapter.replaceData(BloodSugarActivity.this.mMonthAdapterBloodSugarHisListBean);
                    BloodSugarActivity.this.mBloodSugarHisListAdapter.notifyDataSetChanged();
                    BloodSugarActivity bloodSugarActivity = BloodSugarActivity.this;
                    bloodSugarActivity.dataAnalysis(bloodSugarActivity.mMonthAverageBloodSugarNum);
                    return;
                }
                if (position == 1) {
                    BloodSugarActivity.this.mViewPager.setCurrentItem(1);
                    if (BloodSugarActivity.this.mWeekAdapterBloodSugarHisListBean == null || BloodSugarActivity.this.mWeekAdapterBloodSugarHisListBean.size() <= 0) {
                        BloodSugarActivity.this.tvDataFirst.setText("--");
                        BloodSugarActivity.this.tvDataSecond.setText("--");
                        BloodSugarActivity.this.tvDataThirdly.setText("--");
                        BloodSugarActivity.this.mWeekAverageBloodSugarNum = 0.0f;
                    } else {
                        BloodSugarActivity.this.tvDataFirst.setText(FormatUtil.keep1NoZero(BloodSugarActivity.this.mWeekAverageBloodSugarNum));
                        BloodSugarActivity.this.tvDataSecond.setText(FormatUtil.keep1NoZero(BloodSugarActivity.this.mWeekMaxBloodSugarNum));
                        BloodSugarActivity.this.tvDataThirdly.setText(FormatUtil.keep1NoZero(BloodSugarActivity.this.mWeekMinBloodSugarNum));
                    }
                    BloodSugarActivity.this.tvBackToday.setVisibility(0);
                    BloodSugarActivity.this.llCalendar.setVisibility(8);
                    BloodSugarActivity.this.mBloodSugarHisListAdapter.replaceData(BloodSugarActivity.this.mWeekAdapterBloodSugarHisListBean);
                    BloodSugarActivity.this.mBloodSugarHisListAdapter.notifyDataSetChanged();
                    BloodSugarActivity bloodSugarActivity2 = BloodSugarActivity.this;
                    bloodSugarActivity2.dataAnalysis(bloodSugarActivity2.mWeekAverageBloodSugarNum);
                    return;
                }
                if (position != 2) {
                    return;
                }
                BloodSugarActivity.this.mViewPager.setCurrentItem(2);
                if (BloodSugarActivity.this.mDayBloodSugarHisListBean == null || BloodSugarActivity.this.mDayBloodSugarHisListBean.size() <= 0) {
                    BloodSugarActivity.this.tvDataFirst.setText("--");
                    BloodSugarActivity.this.tvDataSecond.setText("--");
                    BloodSugarActivity.this.tvDataThirdly.setText("--");
                    BloodSugarActivity.this.mDayAverageBloodSugarNum = 0.0f;
                } else {
                    BloodSugarActivity.this.tvDataFirst.setText(FormatUtil.keep1NoZero(BloodSugarActivity.this.mDayAverageBloodSugarNum));
                    BloodSugarActivity.this.tvDataSecond.setText(FormatUtil.keep1NoZero(BloodSugarActivity.this.mDayMaxBloodSugarNum));
                    BloodSugarActivity.this.tvDataThirdly.setText(FormatUtil.keep1NoZero(BloodSugarActivity.this.mDayMinBloodSugarNum));
                }
                BloodSugarActivity.this.tvBackToday.setVisibility(8);
                BloodSugarActivity.this.llCalendar.setVisibility(0);
                BloodSugarActivity.this.mBloodSugarHisListAdapter.replaceData(BloodSugarActivity.this.mDayBloodSugarHisListBean);
                BloodSugarActivity.this.mBloodSugarHisListAdapter.notifyDataSetChanged();
                BloodSugarActivity bloodSugarActivity3 = BloodSugarActivity.this;
                bloodSugarActivity3.dataAnalysis(bloodSugarActivity3.mDayAverageBloodSugarNum);
            }
        });
    }

    public void dataAnalysis(float value) {
        if (getString(R.string.blood_sugar_unit_2).equals(this.mBloodSugarUnit)) {
            value /= 18.0f;
        }
        double d2 = value;
        if (d2 >= 7.8d) {
            this.tvAnalyseData.setText(getText(R.string.home_blood_sugar_analyse_high));
            return;
        }
        if (d2 >= 2.8d) {
            this.tvAnalyseData.setText(getText(R.string.home_blood_sugar_analyse_nor));
        } else if (value >= 2.0f) {
            this.tvAnalyseData.setText(getText(R.string.home_blood_sugar_analyse_low));
        } else {
            this.tvAnalyseData.setText("");
        }
    }

    private void setRecycleView() {
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        BloodSugarHisListAdapter bloodSugarHisListAdapter = new BloodSugarHisListAdapter(R.layout.item_universal_his_list);
        this.mBloodSugarHisListAdapter = bloodSugarHisListAdapter;
        bloodSugarHisListAdapter.addData((Collection) this.mDayBloodSugarHisListBean);
        this.mRecyclerView.setAdapter(this.mBloodSugarHisListAdapter);
        this.mRecyclerView.setNestedScrollingEnabled(false);
        this.mBloodSugarHisListAdapter.setOnItemClickListener(new BloodSugarHisListAdapter.OnItemClickListener() { // from class: com.yucheng.smarthealthpro.home.activity.bloodsugar.activity.BloodSugarActivity.7
            @Override // com.yucheng.smarthealthpro.home.activity.bloodsugar.adapter.BloodSugarHisListAdapter.OnItemClickListener
            public void onClick(TemperatureHisListBean hisSearch, int position) {
            }

            @Override // com.yucheng.smarthealthpro.home.activity.bloodsugar.adapter.BloodSugarHisListAdapter.OnItemClickListener
            public void onDelClick(TemperatureHisListBean hisSearch, int position) {
            }

            @Override // com.yucheng.smarthealthpro.home.activity.bloodsugar.adapter.BloodSugarHisListAdapter.OnItemClickListener
            public void onLongClick(TemperatureHisListBean hisSearch, int position) {
            }
        });
    }

    private void initMonth() throws Resources.NotFoundException {
        this.mCalendarView.setOnCalendarSelectListener(this);
        this.mCalendarView.setOnMonthChangeListener(this);
        this.mCalendarView.scrollToCurrent();
        this.mCalendarView.setOnCalendarInterceptListener(new CalendarView.OnCalendarInterceptListener() { // from class: com.yucheng.smarthealthpro.home.activity.bloodsugar.activity.BloodSugarActivity.8
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
            getDayBo(strIntToStr);
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
        int bloodSugarLevel;
        List<TemperatureHisListBean> list = this.mDayBloodSugarHisListBean;
        if (list != null) {
            list.clear();
        }
        this.mDayChartSumUpBloodSugarHisListBean = new ArrayList();
        this.mAllDb = data;
        if (data != null) {
            for (int i2 = 0; i2 < this.mAllDb.size(); i2++) {
                if (TimeStampUtils.dateForStringYearToDate(TimeStampUtils.longStampForDate(this.mAllDb.get(i2).getStartTimestamp())).equals(thatVeryDay) && (bloodSugarLevel = this.mAllDb.get(i2).getBloodSugarLevel()) >= TransUtils.BLOOD_SUGAR_VISIBLE_MIN && bloodSugarLevel <= TransUtils.BLOOD_SUGAR_VISIBLE_MAX) {
                    String string = getString(R.string.value_normal);
                    if (bloodSugarLevel < 28) {
                        string = getString(R.string.value_low);
                    }
                    if (bloodSugarLevel > 78) {
                        string = getString(R.string.value_high);
                    }
                    if (getString(R.string.blood_sugar_unit_2).equals(this.mBloodSugarUnit)) {
                        bloodSugarLevel *= 18;
                    }
                    float f2 = bloodSugarLevel / 10.0f;
                    TemperatureHisListBean temperatureHisListBean = new TemperatureHisListBean(TimeStampUtils.dateForStringToDate(TimeStampUtils.longStampForDate(this.mAllDb.get(i2).getStartTimestamp())), FormatUtil.keep1NoZero(f2), string, this.mBloodSugarUnit);
                    temperatureHisListBean.setModel(this.mAllDb.get(i2).getBloodSugarMode());
                    this.mDayBloodSugarHisListBean.add(temperatureHisListBean);
                    this.mDayChartSumUpBloodSugarHisListBean.add(new TemperatureHisListBean(TimeStampUtils.dateForStringToDate(TimeStampUtils.longStampForDate(this.mAllDb.get(i2).getStartTimestamp())), FormatUtil.keep1NoZero(f2), string, this.mBloodSugarUnit));
                }
            }
        }
        if (this.mDayBloodSugarHisListBean.size() != 0) {
            this.mThatVeryDay = thatVeryDay;
            this.mDaySumUpBloodSugarNum = 0.0f;
            this.mDayMaxBloodSugarNum = 0.0f;
            this.mDayMinBloodSugarNum = 360.0f;
            for (int i3 = 0; i3 < this.mDayBloodSugarHisListBean.size(); i3++) {
                float f3 = Float.parseFloat(this.mDayBloodSugarHisListBean.get(i3).getmValue().replaceAll(",", "."));
                if (f3 > this.mDayMaxBloodSugarNum) {
                    this.mDayMaxBloodSugarNum = f3;
                }
                if (f3 < this.mDayMinBloodSugarNum) {
                    this.mDayMinBloodSugarNum = f3;
                }
                this.mDaySumUpBloodSugarNum += Float.parseFloat(this.mDayBloodSugarHisListBean.get(i3).getmValue().replaceAll(",", "."));
            }
            if (this.mDayBloodSugarHisListBean.size() != 0) {
                this.mDayAverageBloodSugarNum = FormatUtil.getBigDecimal(this.mDaySumUpBloodSugarNum / this.mDayBloodSugarHisListBean.size()).setScale(1, RoundingMode.HALF_UP).floatValue();
            }
        }
        initViewPager();
    }

    public void onWeekData(List<HealthMetric> data) throws NumberFormatException {
        this.mWeekBloodSugarHisListBean.clear();
        this.mWeekAdapterBloodSugarHisListBean.clear();
        this.mWeekChartSumUpBloodSugarHisListBean.clear();
        if (this.mAllDb != null) {
            ArrayList<String> pastStringArray = YearToDayListUtils.getPastStringArray(this.mToDay, 6);
            if (pastStringArray.size() > 0) {
                this.mAllDb = data;
            }
            for (int i2 = 0; i2 < pastStringArray.size(); i2++) {
                try {
                    getWeekDay(pastStringArray.get(i2), i2);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        if (this.mWeekAdapterBloodSugarHisListBean.size() != 0) {
            Collections.reverse(this.mWeekAdapterBloodSugarHisListBean);
            this.mWeekSumUpBloodSugarNum = 0.0f;
            this.mWeekMaxBloodSugarNum = 0.0f;
            this.mWeekMinBloodSugarNum = 360.0f;
            for (int i3 = 0; i3 < this.mWeekAdapterBloodSugarHisListBean.size(); i3++) {
                try {
                    float f2 = Float.parseFloat(this.mWeekAdapterBloodSugarHisListBean.get(i3).getmValue().replaceAll(",", "."));
                    if (f2 > this.mWeekMaxBloodSugarNum) {
                        this.mWeekMaxBloodSugarNum = f2;
                    }
                    if (f2 < this.mWeekMinBloodSugarNum) {
                        this.mWeekMinBloodSugarNum = f2;
                    }
                    this.mWeekSumUpBloodSugarNum += Float.parseFloat(this.mWeekAdapterBloodSugarHisListBean.get(i3).getmValue().replaceAll(",", "."));
                } catch (Exception e3) {
                    e3.printStackTrace();
                    return;
                }
            }
            if (this.mWeekAdapterBloodSugarHisListBean.size() != 0) {
                this.mWeekAverageBloodSugarNum = FormatUtil.getBigDecimal(this.mWeekSumUpBloodSugarNum / this.mWeekAdapterBloodSugarHisListBean.size()).setScale(1, RoundingMode.HALF_UP).floatValue();
            }
        }
    }

    private void getWeekDay(String mThatVeryDay, int index) {
        int bloodSugarLevel;
        for (int i2 = 0; i2 < this.mAllDb.size(); i2++) {
            if (TimeStampUtils.dateForStringYearToDate(TimeStampUtils.longStampForDate(this.mAllDb.get(i2).getStartTimestamp())).equals(mThatVeryDay) && (bloodSugarLevel = this.mAllDb.get(i2).getBloodSugarLevel()) >= TransUtils.BLOOD_SUGAR_VISIBLE_MIN && bloodSugarLevel <= TransUtils.BLOOD_SUGAR_VISIBLE_MAX) {
                String string = getString(R.string.value_normal);
                if (bloodSugarLevel < 28) {
                    string = getString(R.string.value_low);
                }
                if (bloodSugarLevel > 78) {
                    string = getString(R.string.value_high);
                }
                TemperatureHisListBean temperatureHisListBean = new TemperatureHisListBean(TimeStampUtils.dateForStringToDate(TimeStampUtils.longStampForDate(this.mAllDb.get(i2).getStartTimestamp())), FormatUtil.keep1NoZero(bloodSugarLevel / 10.0f), string, this.mBloodSugarUnit);
                temperatureHisListBean.setModel(this.mAllDb.get(i2).getBloodSugarMode());
                this.mWeekBloodSugarHisListBean.add(temperatureHisListBean);
            }
        }
        if (this.mWeekBloodSugarHisListBean != null) {
            this.mWeekSumUpBloodSugarNum = 0.0f;
            for (int i3 = 0; i3 < this.mWeekBloodSugarHisListBean.size(); i3++) {
                try {
                    this.mWeekSumUpBloodSugarNum += Float.parseFloat(this.mWeekBloodSugarHisListBean.get(i3).getmValue().replaceAll(",", "."));
                } catch (Exception e2) {
                    CrashReport.postCatchedException(e2);
                    e2.printStackTrace();
                }
            }
            if (this.mWeekSumUpBloodSugarNum != 0.0f && this.mWeekBloodSugarHisListBean.size() != 0) {
                float size = this.mWeekSumUpBloodSugarNum / this.mWeekBloodSugarHisListBean.size();
                String string2 = getString(R.string.value_normal);
                double d2 = size;
                if (d2 < 2.8d) {
                    string2 = getString(R.string.value_low);
                }
                if (d2 > 7.8d) {
                    string2 = getString(R.string.value_high);
                }
                if (getString(R.string.blood_sugar_unit_2).equals(this.mBloodSugarUnit)) {
                    size *= 18.0f;
                }
                TemperatureHisListBean temperatureHisListBean2 = new TemperatureHisListBean(TimeStampUtils.dateForStringDates(TimeStampUtils.stringForDateDay(mThatVeryDay)), FormatUtil.keep1NoZero(size), string2, this.mBloodSugarUnit);
                temperatureHisListBean2.setModel(0);
                this.mWeekAdapterBloodSugarHisListBean.add(temperatureHisListBean2);
                this.mWeekChartSumUpBloodSugarHisListBean.add(index, new TemperatureHisListBean(TimeStampUtils.dateForStringDates(TimeStampUtils.stringForDateDay(mThatVeryDay)), FormatUtil.keep1NoZero(size), string2, this.mBloodSugarUnit));
            } else {
                this.mWeekChartSumUpBloodSugarHisListBean.add(index, new TemperatureHisListBean(TimeStampUtils.dateForStringDates(TimeStampUtils.stringForDateDay(mThatVeryDay)), "0", "正常", this.mBloodSugarUnit));
            }
            this.mWeekBloodSugarHisListBean.clear();
        }
    }

    public void onMonthData(List<HealthMetric> data) throws NumberFormatException {
        this.mMonthBloodSugarHisListBean.clear();
        this.mMonthAdapterBloodSugarHisListBean.clear();
        this.mMonthChartSumUpBloodSugarHisListBean.clear();
        if (this.mAllDb != null) {
            ArrayList<String> pastStringArray = YearToDayListUtils.getPastStringArray(this.mToDay, 29);
            if (pastStringArray.size() > 0) {
                this.mAllDb = data;
            }
            for (int i2 = 0; i2 < pastStringArray.size(); i2++) {
                getMonthDay(pastStringArray.get(i2), i2);
            }
        }
        if (this.mMonthAdapterBloodSugarHisListBean.size() != 0) {
            Collections.reverse(this.mMonthAdapterBloodSugarHisListBean);
            this.mMonthSumUpBloodSugarNum = 0.0f;
            this.mMonthMaxBloodSugarNum = 0.0f;
            this.mMonthMinBloodSugarNum = 360.0f;
            for (int i3 = 0; i3 < this.mMonthAdapterBloodSugarHisListBean.size(); i3++) {
                float f2 = Float.parseFloat(this.mMonthAdapterBloodSugarHisListBean.get(i3).getmValue().replaceAll(",", "."));
                if (f2 > this.mMonthMaxBloodSugarNum) {
                    this.mMonthMaxBloodSugarNum = f2;
                }
                if (f2 < this.mMonthMinBloodSugarNum) {
                    this.mMonthMinBloodSugarNum = f2;
                }
                this.mMonthSumUpBloodSugarNum += Float.parseFloat(this.mMonthAdapterBloodSugarHisListBean.get(i3).getmValue().replaceAll(",", "."));
            }
            if (this.mMonthAdapterBloodSugarHisListBean.size() != 0) {
                this.mMonthAverageBloodSugarNum = FormatUtil.getBigDecimal(this.mMonthSumUpBloodSugarNum / this.mMonthAdapterBloodSugarHisListBean.size()).setScale(1, RoundingMode.HALF_UP).floatValue();
            }
        }
        runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.bloodsugar.activity.BloodSugarActivity.9
            @Override // java.lang.Runnable
            public void run() throws Resources.NotFoundException {
                BloodSugarActivity.this.initViewPager();
            }
        });
    }

    private void getMonthDay(String mThatVeryDay, int index) {
        int bloodSugarLevel;
        for (int i2 = 0; i2 < this.mAllDb.size(); i2++) {
            if (TimeStampUtils.dateForStringYearToDate(TimeStampUtils.longStampForDate(this.mAllDb.get(i2).getStartTimestamp())).equals(mThatVeryDay) && (bloodSugarLevel = this.mAllDb.get(i2).getBloodSugarLevel()) >= TransUtils.BLOOD_SUGAR_VISIBLE_MIN && bloodSugarLevel <= TransUtils.BLOOD_SUGAR_VISIBLE_MAX) {
                String string = getString(R.string.value_normal);
                if (bloodSugarLevel < 28) {
                    string = getString(R.string.value_low);
                }
                if (bloodSugarLevel > 78) {
                    string = getString(R.string.value_high);
                }
                this.mMonthBloodSugarHisListBean.add(new TemperatureHisListBean(TimeStampUtils.dateForStringToDate(TimeStampUtils.longStampForDate(this.mAllDb.get(i2).getStartTimestamp())), FormatUtil.keep1NoZero(bloodSugarLevel / 10.0f), string, this.mBloodSugarUnit));
            }
        }
        if (this.mMonthBloodSugarHisListBean != null) {
            this.mMonthSumUpBloodSugarNum = 0.0f;
            for (int i3 = 0; i3 < this.mMonthBloodSugarHisListBean.size(); i3++) {
                try {
                    this.mMonthSumUpBloodSugarNum += Float.parseFloat(this.mMonthBloodSugarHisListBean.get(i3).getmValue().replaceAll(",", "."));
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            if (this.mMonthBloodSugarHisListBean != null) {
                this.mMonthSumUpBloodSugarNum = 0.0f;
                for (int i4 = 0; i4 < this.mMonthBloodSugarHisListBean.size(); i4++) {
                    try {
                        this.mMonthSumUpBloodSugarNum += Float.parseFloat(this.mMonthBloodSugarHisListBean.get(i4).getmValue().replaceAll(",", "."));
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                }
                if (this.mMonthSumUpBloodSugarNum != 0.0f && this.mMonthBloodSugarHisListBean.size() != 0) {
                    float size = this.mMonthSumUpBloodSugarNum / this.mMonthBloodSugarHisListBean.size();
                    String string2 = getString(R.string.value_normal);
                    double d2 = size;
                    if (d2 < 2.8d) {
                        string2 = getString(R.string.value_low);
                    }
                    if (d2 > 7.8d) {
                        string2 = getString(R.string.value_high);
                    }
                    if (getString(R.string.blood_sugar_unit_2).equals(this.mBloodSugarUnit)) {
                        size *= 18.0f;
                    }
                    this.mMonthAdapterBloodSugarHisListBean.add(new TemperatureHisListBean(TimeStampUtils.dateForStringDates(TimeStampUtils.stringForDateDay(mThatVeryDay)), FormatUtil.keep1NoZero(size), string2, this.mBloodSugarUnit));
                    this.mMonthChartSumUpBloodSugarHisListBean.add(index, new TemperatureHisListBean(TimeStampUtils.dateForStringDates(TimeStampUtils.stringForDateDay(mThatVeryDay)), FormatUtil.keep1NoZero(size), string2, this.mBloodSugarUnit));
                } else {
                    this.mMonthChartSumUpBloodSugarHisListBean.add(index, new TemperatureHisListBean(TimeStampUtils.dateForStringDates(TimeStampUtils.stringForDateDay(mThatVeryDay)), "0", "正常", this.mBloodSugarUnit));
                }
            }
            this.mMonthBloodSugarHisListBean.clear();
        }
    }

    private void getDayBo(String dateTime) {
        HashMap map = new HashMap();
        map.put("userId", getIntent().getExtras().getString(Constant.SpConstKey.DEV_ID));
        map.put("day", dateTime);
        HttpUtils.getInstance().postMsgAsynHttp(this, Constants.BLOODSUGARDAYURL, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.home.activity.bloodsugar.activity.BloodSugarActivity.10
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                if (result != null) {
                    BloodSugarActivity.this.temp_bean = (FriendCareDataBean) new Gson().fromJson(result, FriendCareDataBean.class);
                    BloodSugarActivity.this.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.bloodsugar.activity.BloodSugarActivity.10.1
                        @Override // java.lang.Runnable
                        public void run() throws Resources.NotFoundException {
                            BloodSugarActivity.this.setDayData();
                            BloodSugarActivity.this.initViewPager();
                        }
                    });
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v15, types: [java.util.List] */
    public void setDayData() {
        List<TemperatureHisListBean> list = this.mDayBloodSugarHisListBean;
        if (list != null) {
            list.clear();
        }
        List<TemperatureHisListBean> list2 = this.mDayChartSumUpBloodSugarHisListBean;
        if (list2 != null) {
            list2.clear();
        }
        this.mDaySumUpBloodSugarNum = 0.0f;
        this.mDayMaxBloodSugarNum = 0.0f;
        this.mDayMinBloodSugarNum = 360.0f;
        if (this.temp_bean == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        try {
            Iterator<FriendCareDataBean.Data> it2 = this.temp_bean.data.iterator();
            while (it2.hasNext()) {
                arrayList = (List) new Gson().fromJson(it2.next().mlist, new TypeToken<List<FriendCareBloodSugarBean>>() { // from class: com.yucheng.smarthealthpro.home.activity.bloodsugar.activity.BloodSugarActivity.11
                }.getType());
            }
        } catch (Exception e2) {
            CrashReport.postCatchedException(e2);
            e2.printStackTrace();
        }
        List<FriendCareBloodSugarBean> listSortCareListBloodSugar = Tools.sortCareListBloodSugar(Tools.removeCareRealTimeBloodSugar(arrayList));
        for (int i2 = 0; i2 < listSortCareListBloodSugar.size(); i2++) {
            float f2 = listSortCareListBloodSugar.get(i2).bloodSugar;
            if (f2 >= TransUtils.BLOOD_SUGAR_VISIBLE_MIN && f2 <= TransUtils.BLOOD_SUGAR_VISIBLE_MAX) {
                f2 /= 10.0f;
            }
            if (f2 >= TransUtils.BLOOD_SUGAR_VISIBLE_MIN_2 && f2 <= TransUtils.BLOOD_SUGAR_VISIBLE_MAX) {
                String string = getString(R.string.value_normal);
                double d2 = f2;
                if (d2 < 2.8d) {
                    string = getString(R.string.value_low);
                }
                if (d2 > 7.8d) {
                    string = getString(R.string.value_high);
                }
                if (getString(R.string.blood_sugar_unit_2).equals(this.mBloodSugarUnit)) {
                    f2 *= 18.0f;
                }
                if (f2 > this.mDayMaxBloodSugarNum) {
                    this.mDayMaxBloodSugarNum = f2;
                }
                if (f2 < this.mDayMinBloodSugarNum) {
                    this.mDayMinBloodSugarNum = f2;
                }
                this.mDaySumUpBloodSugarNum += f2;
                this.mDayBloodSugarHisListBean.add(new TemperatureHisListBean(TimeStampUtils.dateForStringToDate(TimeStampUtils.longStampForDate(listSortCareListBloodSugar.get(i2).rtime)), FormatUtil.keep1NoZero(f2), string, this.mBloodSugarUnit));
                this.mDayChartSumUpBloodSugarHisListBean.add(new TemperatureHisListBean(TimeStampUtils.dateForStringToDate(TimeStampUtils.longStampForDate(listSortCareListBloodSugar.get(i2).rtime)), FormatUtil.keep1NoZero(f2), string, this.mBloodSugarUnit));
            }
        }
        if (this.mDayBloodSugarHisListBean.size() != 0) {
            this.mDayAverageBloodSugarNum = FormatUtil.getBigDecimal(this.mDaySumUpBloodSugarNum / this.mDayBloodSugarHisListBean.size()).setScale(1, RoundingMode.HALF_UP).floatValue();
        }
    }

    private void getWeekBo(String dateTime, String startTime, String endTime) {
        this.mWeekBloodSugarHisListBean.clear();
        this.mWeekAdapterBloodSugarHisListBean.clear();
        this.mWeekChartSumUpBloodSugarHisListBean.clear();
        HashMap map = new HashMap();
        map.put("userId", getIntent().getExtras().getString(Constant.SpConstKey.DEV_ID));
        map.put("day", dateTime);
        map.put("startDate", startTime);
        map.put("endDate", endTime);
        map.put("type", "1");
        HttpUtils.getInstance().postMsgAsynHttp(this, Constants.BLOODSUGARDAYURL, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.home.activity.bloodsugar.activity.BloodSugarActivity.12
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) throws NumberFormatException {
                if (result != null) {
                    List<CareBloodSugarWeekMonthBean.DataBean> data = ((CareBloodSugarWeekMonthBean) new Gson().fromJson(result, CareBloodSugarWeekMonthBean.class)).getData();
                    if (data.size() != 0) {
                        BloodSugarActivity.this.mWeekSumUpBloodSugarNum = 0.0f;
                        BloodSugarActivity.this.mWeekMaxBloodSugarNum = 0.0f;
                        BloodSugarActivity.this.mWeekMinBloodSugarNum = 360.0f;
                        int i2 = 0;
                        while (true) {
                            if (i2 >= data.size()) {
                                break;
                            }
                            Logger.d("chong-----sugar==" + data.get(i2).toString());
                            float f2 = Float.parseFloat(data.get(i2).getBloodSugarMean().isEmpty() ? "0" : data.get(i2).getBloodSugarMean().replaceAll(",", "."));
                            if (f2 >= TransUtils.BLOOD_SUGAR_VISIBLE_MIN && f2 <= TransUtils.BLOOD_SUGAR_VISIBLE_MAX) {
                                f2 /= 10.0f;
                            }
                            if (f2 >= TransUtils.BLOOD_SUGAR_VISIBLE_MIN_2 && f2 <= TransUtils.BLOOD_SUGAR_VISIBLE_MAX_2) {
                                String string = BloodSugarActivity.this.getString(R.string.value_normal);
                                double d2 = f2;
                                if (d2 < 2.8d) {
                                    string = BloodSugarActivity.this.getString(R.string.value_low);
                                }
                                if (d2 > 7.8d) {
                                    string = BloodSugarActivity.this.getString(R.string.value_high);
                                }
                                if (BloodSugarActivity.this.getString(R.string.blood_sugar_unit_2).equals(BloodSugarActivity.this.mBloodSugarUnit)) {
                                    f2 *= 18.0f;
                                }
                                if (f2 > BloodSugarActivity.this.mWeekMaxBloodSugarNum) {
                                    BloodSugarActivity.this.mWeekMaxBloodSugarNum = f2;
                                }
                                if (f2 < BloodSugarActivity.this.mWeekMinBloodSugarNum) {
                                    BloodSugarActivity.this.mWeekMinBloodSugarNum = f2;
                                }
                                BloodSugarActivity.this.mWeekSumUpBloodSugarNum += f2;
                                BloodSugarActivity.this.mWeekAdapterBloodSugarHisListBean.add(new TemperatureHisListBean(TimeStampUtils.dateForStringDates(TimeStampUtils.stringForDateDay(data.get(i2).getDateformat())), FormatUtil.getBigDecimal(f2).setScale(1, 4).toString(), string, BloodSugarActivity.this.mBloodSugarUnit));
                            }
                            i2++;
                        }
                        if (BloodSugarActivity.this.mWeekAdapterBloodSugarHisListBean.size() > 0) {
                            BloodSugarActivity bloodSugarActivity = BloodSugarActivity.this;
                            bloodSugarActivity.mWeekAverageBloodSugarNum = FormatUtil.getBigDecimal(bloodSugarActivity.mWeekSumUpBloodSugarNum / BloodSugarActivity.this.mWeekAdapterBloodSugarHisListBean.size()).setScale(1, RoundingMode.HALF_UP).floatValue();
                        }
                        ArrayList<String> pastByMonthDayArray = YearToDayListUtils.getPastByMonthDayArray(BloodSugarActivity.this.mToDay, 6);
                        for (int i3 = 0; i3 < pastByMonthDayArray.size(); i3++) {
                            BloodSugarActivity.this.mWeekChartSumUpBloodSugarHisListBean.add(new TemperatureHisListBean(pastByMonthDayArray.get(i3), "0", "正常", BloodSugarActivity.this.mBloodSugarUnit));
                        }
                        for (int i4 = 0; i4 < pastByMonthDayArray.size(); i4++) {
                            for (TemperatureHisListBean temperatureHisListBean : BloodSugarActivity.this.mWeekAdapterBloodSugarHisListBean) {
                                if (pastByMonthDayArray.get(i4).equals(temperatureHisListBean.getTime())) {
                                    BloodSugarActivity.this.mWeekChartSumUpBloodSugarHisListBean.remove(i4);
                                    BloodSugarActivity.this.mWeekChartSumUpBloodSugarHisListBean.add(i4, new TemperatureHisListBean(temperatureHisListBean.getTime(), temperatureHisListBean.getmValue(), temperatureHisListBean.getState(), BloodSugarActivity.this.mBloodSugarUnit));
                                }
                            }
                        }
                    }
                }
            }
        });
    }

    private void getMonthBo(String dateTime, String startTime, String endTime) {
        this.mMonthBloodSugarHisListBean.clear();
        this.mMonthAdapterBloodSugarHisListBean.clear();
        this.mMonthChartSumUpBloodSugarHisListBean.clear();
        HashMap map = new HashMap();
        map.put("userId", getIntent().getExtras().getString(Constant.SpConstKey.DEV_ID));
        map.put("day", dateTime);
        map.put("startDate", startTime);
        map.put("endDate", endTime);
        map.put("type", "1");
        HttpUtils.getInstance().postMsgAsynHttp(this, Constants.BLOODSUGARDAYURL, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.home.activity.bloodsugar.activity.BloodSugarActivity.13
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) throws NumberFormatException {
                CareBloodSugarWeekMonthBean careBloodSugarWeekMonthBean;
                if (result != null) {
                    try {
                        careBloodSugarWeekMonthBean = (CareBloodSugarWeekMonthBean) new Gson().fromJson(result, CareBloodSugarWeekMonthBean.class);
                    } catch (JsonSyntaxException e2) {
                        e2.printStackTrace();
                        careBloodSugarWeekMonthBean = null;
                    }
                    if (careBloodSugarWeekMonthBean == null || careBloodSugarWeekMonthBean.getData() == null) {
                        return;
                    }
                    List<CareBloodSugarWeekMonthBean.DataBean> data = careBloodSugarWeekMonthBean.getData();
                    BloodSugarActivity.this.mMonthSumUpBloodSugarNum = 0.0f;
                    BloodSugarActivity.this.mMonthMaxBloodSugarNum = 0.0f;
                    BloodSugarActivity.this.mMonthMinBloodSugarNum = 360.0f;
                    int i2 = 0;
                    while (true) {
                        if (i2 >= data.size()) {
                            break;
                        }
                        float f2 = Float.parseFloat(data.get(i2).getBloodSugarMean().isEmpty() ? "0" : data.get(i2).getBloodSugarMean().replaceAll(",", "."));
                        if (f2 >= TransUtils.BLOOD_SUGAR_VISIBLE_MIN && f2 <= TransUtils.BLOOD_SUGAR_VISIBLE_MAX) {
                            f2 /= 10.0f;
                        }
                        if (f2 >= TransUtils.BLOOD_SUGAR_VISIBLE_MIN_2 && f2 <= TransUtils.BLOOD_SUGAR_VISIBLE_MAX_2) {
                            String string = BloodSugarActivity.this.getString(R.string.value_normal);
                            double d2 = f2;
                            if (d2 < 2.8d) {
                                string = BloodSugarActivity.this.getString(R.string.value_low);
                            }
                            if (d2 > 7.8d) {
                                string = BloodSugarActivity.this.getString(R.string.value_high);
                            }
                            if (BloodSugarActivity.this.getString(R.string.blood_sugar_unit_2).equals(BloodSugarActivity.this.mBloodSugarUnit)) {
                                f2 *= 18.0f;
                            }
                            if (f2 > BloodSugarActivity.this.mMonthMaxBloodSugarNum) {
                                BloodSugarActivity.this.mMonthMaxBloodSugarNum = f2;
                            }
                            if (f2 < BloodSugarActivity.this.mMonthMinBloodSugarNum) {
                                BloodSugarActivity.this.mMonthMinBloodSugarNum = f2;
                            }
                            BloodSugarActivity.this.mMonthSumUpBloodSugarNum += f2;
                            BloodSugarActivity.this.mMonthAdapterBloodSugarHisListBean.add(new TemperatureHisListBean(TimeStampUtils.dateForStringDates(TimeStampUtils.stringForDateDay(data.get(i2).getDateformat())), FormatUtil.getBigDecimal(f2).setScale(1, 4).toString(), string, BloodSugarActivity.this.mBloodSugarUnit));
                        }
                        i2++;
                    }
                    if (BloodSugarActivity.this.mMonthAdapterBloodSugarHisListBean.size() > 0) {
                        BloodSugarActivity bloodSugarActivity = BloodSugarActivity.this;
                        bloodSugarActivity.mMonthAverageBloodSugarNum = FormatUtil.getBigDecimal(bloodSugarActivity.mMonthSumUpBloodSugarNum / BloodSugarActivity.this.mMonthAdapterBloodSugarHisListBean.size()).setScale(1, RoundingMode.HALF_UP).floatValue();
                    }
                    ArrayList<String> pastByMonthDayArray = YearToDayListUtils.getPastByMonthDayArray(BloodSugarActivity.this.mToDay, 29);
                    for (int i3 = 0; i3 < pastByMonthDayArray.size(); i3++) {
                        BloodSugarActivity.this.mMonthChartSumUpBloodSugarHisListBean.add(new TemperatureHisListBean(pastByMonthDayArray.get(i3), "0", "正常", BloodSugarActivity.this.mBloodSugarUnit));
                    }
                    for (int i4 = 0; i4 < pastByMonthDayArray.size(); i4++) {
                        for (TemperatureHisListBean temperatureHisListBean : BloodSugarActivity.this.mMonthAdapterBloodSugarHisListBean) {
                            if (pastByMonthDayArray.get(i4).equals(temperatureHisListBean.getTime())) {
                                BloodSugarActivity.this.mMonthChartSumUpBloodSugarHisListBean.remove(i4);
                                BloodSugarActivity.this.mMonthChartSumUpBloodSugarHisListBean.add(i4, new TemperatureHisListBean(temperatureHisListBean.getTime(), temperatureHisListBean.getmValue(), temperatureHisListBean.getState(), BloodSugarActivity.this.mBloodSugarUnit));
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
        if (view.getId() == R.id.ll_calendar) {
            this.mViewPager.setCurrentItem(2);
            return;
        }
        if (view.getId() == R.id.ll_start_button) {
            startActivityForResult(new Intent(this.context, (Class<?>) BloodSugarMeasureActivity.class), 0);
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
                this.mNestedScrollView.postDelayed(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.bloodsugar.activity.BloodSugarActivity.14
                    @Override // java.lang.Runnable
                    public void run() {
                        BloodSugarActivity.this.mNestedScrollView.smoothScrollTo(0, (int) (BloodSugarActivity.this.mNestedScrollView.getScrollY() + (DpUtil.dp2px(BloodSugarActivity.this.context, 56.0f) * 1.5f)));
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
        } else if (view.getId() == R.id.rl_first) {
            startActivity(new Intent(this.context, (Class<?>) MeHealthSettingActivity.class));
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) throws Resources.NotFoundException {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == -1) {
            initData();
        }
    }
}
