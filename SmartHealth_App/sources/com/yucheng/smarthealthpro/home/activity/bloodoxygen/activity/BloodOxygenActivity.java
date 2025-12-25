package com.yucheng.smarthealthpro.home.activity.bloodoxygen.activity;

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
import com.yucheng.smarthealthpro.care.bean.CareBoWeekMonthBean;
import com.yucheng.smarthealthpro.care.bean.FriendCareDataBean;
import com.yucheng.smarthealthpro.care.bean.FriendCareSpo2Bean;
import com.yucheng.smarthealthpro.data.packed.HealthDayData;
import com.yucheng.smarthealthpro.data.packed.HealthPackedData;
import com.yucheng.smarthealthpro.database.room.bean.HealthMetric;
import com.yucheng.smarthealthpro.databinding.ActivityBloodoxygenBinding;
import com.yucheng.smarthealthpro.framework.http.HttpUtils;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import com.yucheng.smarthealthpro.home.activity.HealthyActivity;
import com.yucheng.smarthealthpro.home.activity.bloodoxygen.adapter.BloodOxygenHisListAdapter;
import com.yucheng.smarthealthpro.home.activity.bloodoxygen.adapter.BoTabFragmentAdapter;
import com.yucheng.smarthealthpro.home.activity.bloodoxygen.fragment.BoTabFragment;
import com.yucheng.smarthealthpro.home.activity.temperature.bean.TemperatureHisListBean;
import com.yucheng.smarthealthpro.home.util.HealthDataFilterKt;
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
import com.yucheng.smarthealthpro.utils.Tools;
import com.yucheng.smarthealthpro.utils.TransUtils;
import com.yucheng.smarthealthpro.utils.YearToDayListUtils;
import com.yucheng.smarthealthpro.viewmodel.BloodOxygenViewModel;
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
public class BloodOxygenActivity extends BaseVbActivity<ActivityBloodoxygenBinding> implements CalendarView.OnCalendarSelectListener, CalendarView.OnMonthChangeListener {
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
    private BloodOxygenHisListAdapter mBloodOxygenHisListAdapter;
    private Calendar mCalendar;
    CalendarView mCalendarView;
    private int mDayAverageBloodOxygenNum;
    private int mDayMaxBloodOxygenNum;
    private int mDayMinBloodOxygenNum;
    private int mDaySumUpBloodOxygenNum;
    private int mMonthAverageBloodOxygenNum;
    private int mMonthMaxBloodOxygenNum;
    private int mMonthMinBloodOxygenNum;
    private int mMonthSumUpBloodOxygenNum;
    NestedScrollView mNestedScrollView;
    RecyclerView mRecyclerView;
    SlidingTabLayout mSlidingTabLayout;
    private String mToDay;
    private BloodOxygenViewModel mViewModel;
    NoScrollViewPager mViewPager;
    private int mWeekAverageBloodOxygenNum;
    private int mWeekMaxBloodOxygenNum;
    private int mWeekMinBloodOxygenNum;
    private int mWeekSumUpBloodOxygenNum;
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
    private List<TemperatureHisListBean> mDayBloodOxygenHisListBean = new ArrayList();
    private List<TemperatureHisListBean> mDayChartSumUpBloodOxygenHisListBean = new ArrayList();
    private List<TemperatureHisListBean> mWeekAdapterBloodOxygenHisListBean = new ArrayList();
    private List<TemperatureHisListBean> mWeekChartSumUpBloodOxygenHisListBean = new ArrayList();
    private List<TemperatureHisListBean> mMonthAdapterBloodOxygenHisListBean = new ArrayList();
    private List<TemperatureHisListBean> mMonthChartSumUpBloodOxygenHisListBean = new ArrayList();
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
        this.mSlidingTabLayout = ((ActivityBloodoxygenBinding) this.mBinding).includeItemTop.stlTab;
        this.ivCalendar = ((ActivityBloodoxygenBinding) this.mBinding).includeItemTop.ivCalendar;
        this.tvCalendar = ((ActivityBloodoxygenBinding) this.mBinding).includeItemTop.tvCalendar;
        this.tvBackToday = ((ActivityBloodoxygenBinding) this.mBinding).includeItemTop.tvBackToday;
        this.llCalendar = ((ActivityBloodoxygenBinding) this.mBinding).includeItemTop.llCalendar;
        this.mViewPager = ((ActivityBloodoxygenBinding) this.mBinding).includeItemTop.vpTab;
        this.tvDataFirst = ((ActivityBloodoxygenBinding) this.mBinding).includeItemMessageData.tvDataFirst;
        this.tvDataFirstUnit = ((ActivityBloodoxygenBinding) this.mBinding).includeItemMessageData.tvDataFirstUnit;
        this.rlDataFirst = ((ActivityBloodoxygenBinding) this.mBinding).includeItemMessageData.rlDataFirst;
        this.tvDataSecond = ((ActivityBloodoxygenBinding) this.mBinding).includeItemMessageData.tvDataSecond;
        this.ivDataSecond = ((ActivityBloodoxygenBinding) this.mBinding).includeItemMessageData.ivDataSecond;
        this.tvDataSecondUnit = ((ActivityBloodoxygenBinding) this.mBinding).includeItemMessageData.tvDataSecondUnit;
        this.llDataSecond = ((ActivityBloodoxygenBinding) this.mBinding).includeItemMessageData.llDataSecond;
        this.tvDataThirdly = ((ActivityBloodoxygenBinding) this.mBinding).includeItemMessageData.tvDataThirdly;
        this.ivDataThirdly = ((ActivityBloodoxygenBinding) this.mBinding).includeItemMessageData.ivDataThirdly;
        this.tvDataThirdlyUnit = ((ActivityBloodoxygenBinding) this.mBinding).includeItemMessageData.tvDataThirdlyUnit;
        this.llDataThirdly = ((ActivityBloodoxygenBinding) this.mBinding).includeItemMessageData.llDataThirdly;
        this.tvStartButton = ((ActivityBloodoxygenBinding) this.mBinding).includeItemBottom.tvStartButton;
        this.llStartButton = ((ActivityBloodoxygenBinding) this.mBinding).includeItemBottom.llStartButton;
        this.tvAnalyse = ((ActivityBloodoxygenBinding) this.mBinding).includeItemBottom.tvAnalyse;
        this.tvAnalyseData = ((ActivityBloodoxygenBinding) this.mBinding).includeItemBottom.tvAnalyseData;
        this.rlAnalyse = ((ActivityBloodoxygenBinding) this.mBinding).includeItemBottom.rlAnalyse;
        this.ivFirstLeft = ((ActivityBloodoxygenBinding) this.mBinding).includeItemBottom.ivFirstLeft;
        this.tvFirst = ((ActivityBloodoxygenBinding) this.mBinding).includeItemBottom.tvFirst;
        this.ivFirstRight = ((ActivityBloodoxygenBinding) this.mBinding).includeItemBottom.ivFirstRight;
        this.rlFirst = ((ActivityBloodoxygenBinding) this.mBinding).includeItemBottom.rlFirst;
        this.ivSecondLeft = ((ActivityBloodoxygenBinding) this.mBinding).includeItemBottom.ivSecondLeft;
        this.tvSecond = ((ActivityBloodoxygenBinding) this.mBinding).includeItemBottom.tvSecond;
        this.ivSecondRight = ((ActivityBloodoxygenBinding) this.mBinding).includeItemBottom.ivSecondRight;
        this.rlSecond = ((ActivityBloodoxygenBinding) this.mBinding).includeItemBottom.rlSecond;
        this.ivFourthlyLeft = ((ActivityBloodoxygenBinding) this.mBinding).includeItemBottom.ivFourthlyLeft;
        this.tvFourthly = ((ActivityBloodoxygenBinding) this.mBinding).includeItemBottom.tvFourthly;
        this.ivFourthlyRight = ((ActivityBloodoxygenBinding) this.mBinding).includeItemBottom.ivFourthlyRight;
        this.rlFourthly = ((ActivityBloodoxygenBinding) this.mBinding).includeItemBottom.rlFourthly;
        this.mRecyclerView = ((ActivityBloodoxygenBinding) this.mBinding).includeItemBottom.recycleView;
        this.mNestedScrollView = ((ActivityBloodoxygenBinding) this.mBinding).nsv;
        this.tvYears = ((ActivityBloodoxygenBinding) this.mBinding).includeItemCalendar.tvYears;
        this.mCalendarView = ((ActivityBloodoxygenBinding) this.mBinding).includeItemCalendar.calendarView;
        this.llMonth = ((ActivityBloodoxygenBinding) this.mBinding).includeItemCalendar.llMonth;
        this.llCalendar.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.bloodoxygen.activity.BloodOxygenActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.tvBackToday.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.bloodoxygen.activity.BloodOxygenActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.llStartButton.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.bloodoxygen.activity.BloodOxygenActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlAnalyse.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.bloodoxygen.activity.BloodOxygenActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlFirst.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.bloodoxygen.activity.BloodOxygenActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlSecond.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.bloodoxygen.activity.BloodOxygenActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlFourthly.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.bloodoxygen.activity.BloodOxygenActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.llMonth.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.bloodoxygen.activity.BloodOxygenActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        changeTitle(getString(R.string.home_blood_oxygen_title));
        showBack();
        showRightImage(R.mipmap.topbar_ic_share, new NavigationBar.MyOnClickListener() { // from class: com.yucheng.smarthealthpro.home.activity.bloodoxygen.activity.BloodOxygenActivity.1
            @Override // com.yucheng.smarthealthpro.framework.view.NavigationBar.MyOnClickListener
            public void onClick(View btn) {
                ShareUtils.share(BloodOxygenActivity.this);
            }
        });
        int statusHeight = AppScreenMgr.getStatusHeight(this.context);
        this.llMonth.setPadding(0, DensityUtils.dip2px(this.context, 50.0f) + statusHeight, 0, 0);
        if (Constant.isTechFeel()) {
            this.rlSecond.setVisibility(8);
        }
        this.rlFirst.setVisibility(8);
        String stringExtra = getIntent().getStringExtra("care");
        if (stringExtra != null && stringExtra.equals(getString(R.string.care_title))) {
            this.isCare = true;
            this.llStartButton.setVisibility(8);
            this.rlSecond.setVisibility(8);
        } else {
            if (!YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASTESTSPO2)) {
                this.llStartButton.setVisibility(8);
            }
            this.tvStartButton.setText(getString(R.string.home_blood_oxygen_measure_title));
            this.tvFirst.setText(getString(R.string.include_bottom_tv_first_button));
            this.tvSecond.setText(getString(R.string.include_bottom_tv_second_button));
        }
        this.tvAnalyse.setText(getString(R.string.home_blood_oxygen_analyse_tv));
        this.tvFourthly.setText(getString(R.string.include_bottom_tv_fourthly_button));
        this.ivFourthlyRight.setBackground(ResourcesCompat.getDrawable(getResources(), R.mipmap.list_ic_arrow_n, null));
    }

    private void initViewModel() {
        this.mViewModel = (BloodOxygenViewModel) new ViewModelProvider(this).get(BloodOxygenViewModel.class);
        FlowUtils.INSTANCE.collectFlow(this, this.mViewModel.getBloodOxygenDataFlow(), new FlowUtils.FlowCollector<HealthDayData<HealthMetric>>() { // from class: com.yucheng.smarthealthpro.home.activity.bloodoxygen.activity.BloodOxygenActivity.2
            @Override // com.yucheng.smarthealthpro.utils.FlowUtils.FlowCollector
            public void collect(HealthDayData<HealthMetric> data) throws Resources.NotFoundException {
                BloodOxygenActivity.this.onThatVeryDayData(data.getDay(), data.getData());
            }
        });
        FlowUtils.INSTANCE.collectFlow(this, this.mViewModel.getBloodOxygenPackedDataFlow(), new FlowUtils.FlowCollector<HealthPackedData<HealthMetric>>() { // from class: com.yucheng.smarthealthpro.home.activity.bloodoxygen.activity.BloodOxygenActivity.3
            @Override // com.yucheng.smarthealthpro.utils.FlowUtils.FlowCollector
            public void collect(HealthPackedData<HealthMetric> packedData) {
                if (packedData.getDayCount() == 7) {
                    BloodOxygenActivity.this.onWeekData(packedData.getData());
                } else if (packedData.getDayCount() == 30) {
                    BloodOxygenActivity.this.onMonthData(packedData.getData());
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

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        getCurrData();
    }

    private void getCurrData() {
        if (this.isCare.booleanValue()) {
            ArrayList<String> pastStringArray = YearToDayListUtils.getPastStringArray(this.mToDay, 6);
            ArrayList<String> pastStringArray2 = YearToDayListUtils.getPastStringArray(this.mToDay, 29);
            getWeekBo(pastStringArray.get(0), pastStringArray.get(0), pastStringArray.get(6));
            getMonthBo(pastStringArray2.get(0), pastStringArray2.get(0), pastStringArray2.get(29));
            return;
        }
        new Thread(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.bloodoxygen.activity.BloodOxygenActivity.4
            @Override // java.lang.Runnable
            public void run() {
                BloodOxygenActivity.this.getWeekData();
                BloodOxygenActivity.this.getMonthData();
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
        BoTabFragmentAdapter boTabFragmentAdapter = new BoTabFragmentAdapter(getSupportFragmentManager(), new BoTabFragmentAdapter.FragmentCreator() { // from class: com.yucheng.smarthealthpro.home.activity.bloodoxygen.activity.BloodOxygenActivity.5
            @Override // com.yucheng.smarthealthpro.home.activity.bloodoxygen.adapter.BoTabFragmentAdapter.FragmentCreator
            public Fragment createFragment(String data, int position) {
                return BoTabFragment.newInstance(data.toString(), position, BloodOxygenActivity.this.mNestedScrollView, BloodOxygenActivity.this.mDayChartSumUpBloodOxygenHisListBean, BloodOxygenActivity.this.mWeekChartSumUpBloodOxygenHisListBean, BloodOxygenActivity.this.mMonthChartSumUpBloodOxygenHisListBean, BloodOxygenActivity.this.mDayMaxBloodOxygenNum);
            }

            @Override // com.yucheng.smarthealthpro.home.activity.bloodoxygen.adapter.BoTabFragmentAdapter.FragmentCreator
            public String createTitle(String data) {
                return Html.fromHtml(data).toString();
            }
        });
        this.mAdapter = boTabFragmentAdapter;
        this.mViewPager.setAdapter(boTabFragmentAdapter);
        this.mViewPager.setOffscreenPageLimit(this.mTitles.size() - 1);
        this.mAdapter.setData(this.mTitles);
        this.mSlidingTabLayout.setViewPager(this.mViewPager, (String[]) this.mTitles.toArray(new String[0]));
        this.mSlidingTabLayout.setCurrentTab(2);
        this.mViewPager.addOnPageChangeListener(new OnPageChangeListenerImpl());
    }

    public void dataAnalysis(int value) {
        if (Constant.isTechFeel()) {
            if (this.mBloodOxygenHisListAdapter.getData().size() > 0) {
                this.tvDataFirst.setText(((int) Float.parseFloat(this.mBloodOxygenHisListAdapter.getData().get(0).getmValue())) + "");
            } else {
                this.tvDataFirst.setText("--");
            }
        }
        if (value >= 90) {
            this.tvAnalyseData.setText(getText(R.string.home_blood_oxygen_saturation_is_normal));
        } else if (value >= 70) {
            this.tvAnalyseData.setText(getText(R.string.home_blood_abnormal_oxygen_saturation));
        } else {
            this.tvAnalyseData.setText("");
        }
    }

    private void setRecycleView() {
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        BloodOxygenHisListAdapter bloodOxygenHisListAdapter = new BloodOxygenHisListAdapter(R.layout.item_universal_his_list);
        this.mBloodOxygenHisListAdapter = bloodOxygenHisListAdapter;
        bloodOxygenHisListAdapter.addData((Collection) this.mDayBloodOxygenHisListBean);
        this.mRecyclerView.setAdapter(this.mBloodOxygenHisListAdapter);
        this.mRecyclerView.setNestedScrollingEnabled(false);
    }

    private void initMonth() throws Resources.NotFoundException {
        this.mCalendarView.setOnCalendarSelectListener(this);
        this.mCalendarView.setOnMonthChangeListener(this);
        this.mCalendarView.scrollToCurrent();
        this.mCalendarView.setOnCalendarInterceptListener(new CalendarView.OnCalendarInterceptListener() { // from class: com.yucheng.smarthealthpro.home.activity.bloodoxygen.activity.BloodOxygenActivity.6
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

    public void onThatVeryDayData(String thatVeryDay, List<HealthMetric> data) throws Resources.NotFoundException {
        int bloodOxygenLevel;
        this.mDayBloodOxygenHisListBean.clear();
        this.mDayChartSumUpBloodOxygenHisListBean.clear();
        this.mAllDb = data;
        this.mThatVeryDay = thatVeryDay;
        this.mDaySumUpBloodOxygenNum = 0;
        this.mDayMaxBloodOxygenNum = 0;
        this.mDayMinBloodOxygenNum = 100;
        this.mDayAverageBloodOxygenNum = 0;
        if (data != null) {
            for (int i2 = 0; i2 < this.mAllDb.size(); i2++) {
                if (TimeStampUtils.dateForStringYearToDate(TimeStampUtils.longStampForDate(this.mAllDb.get(i2).getStartTimestamp())).equals(thatVeryDay) && this.mAllDb.get(i2).getBloodOxygenLevel() != 0 && (bloodOxygenLevel = this.mAllDb.get(i2).getBloodOxygenLevel()) >= TransUtils.BLOOD_OXYGEN_VISIBLE_MIN && bloodOxygenLevel <= TransUtils.BLOOD_OXYGEN_VISIBLE_MAX) {
                    String strDateForStringToDate = TimeStampUtils.dateForStringToDate(TimeStampUtils.longStampForDate(this.mAllDb.get(i2).getStartTimestamp()));
                    this.mDayBloodOxygenHisListBean.add(new TemperatureHisListBean(strDateForStringToDate, bloodOxygenLevel + "", ""));
                    this.mDayChartSumUpBloodOxygenHisListBean.add(new TemperatureHisListBean(strDateForStringToDate, bloodOxygenLevel + "", ""));
                    if (bloodOxygenLevel > this.mDayMaxBloodOxygenNum) {
                        this.mDayMaxBloodOxygenNum = bloodOxygenLevel;
                    }
                    if (bloodOxygenLevel < this.mDayMinBloodOxygenNum) {
                        this.mDayMinBloodOxygenNum = bloodOxygenLevel;
                    }
                    this.mDaySumUpBloodOxygenNum += bloodOxygenLevel;
                }
            }
        }
        if (this.mDayBloodOxygenHisListBean.size() != 0) {
            this.mDayAverageBloodOxygenNum = Math.round((this.mDaySumUpBloodOxygenNum * 1.0f) / this.mDayBloodOxygenHisListBean.size());
        }
        initViewPager();
    }

    public void getWeekData() {
        this.mWeekAdapterBloodOxygenHisListBean.clear();
        this.mWeekChartSumUpBloodOxygenHisListBean.clear();
        this.mWeekMaxBloodOxygenNum = 0;
        this.mWeekMinBloodOxygenNum = 100;
        this.mWeekAverageBloodOxygenNum = 0;
        this.mViewModel.getPackedDayData(YearToDayListUtils.getPastStringArray(this.mToDay, 6).get(0), 7);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onWeekData(List<HealthMetric> data) {
        int size;
        int i2;
        ArrayList<String> pastStringArray = YearToDayListUtils.getPastStringArray(this.mToDay, 6);
        this.mAllDb = data;
        int i3 = 0;
        for (int i4 = 0; i4 < pastStringArray.size(); i4++) {
            List<HealthMetric> listFilterHealthMetricByDate = HealthDataFilterKt.filterHealthMetricByDate(this.mAllDb, pastStringArray.get(i4));
            if (listFilterHealthMetricByDate != null) {
                size = listFilterHealthMetricByDate.size();
                i2 = 0;
                for (int i5 = 0; i5 < listFilterHealthMetricByDate.size(); i5++) {
                    int bloodOxygenLevel = listFilterHealthMetricByDate.get(i5).getBloodOxygenLevel();
                    if (bloodOxygenLevel < TransUtils.BLOOD_OXYGEN_VISIBLE_MIN || bloodOxygenLevel > TransUtils.BLOOD_OXYGEN_VISIBLE_MAX) {
                        size--;
                    } else {
                        i2 += bloodOxygenLevel;
                    }
                }
            } else {
                size = 0;
                i2 = 0;
            }
            if (i2 == 0) {
                this.mWeekChartSumUpBloodOxygenHisListBean.add(new TemperatureHisListBean(TimeStampUtils.toFormatDate(pastStringArray.get(i4)), "0", ""));
            } else {
                int i6 = i2 / size;
                i3 += i6;
                if (i6 > this.mWeekMaxBloodOxygenNum) {
                    this.mWeekMaxBloodOxygenNum = i6;
                }
                if (i6 < this.mWeekMinBloodOxygenNum) {
                    this.mWeekMinBloodOxygenNum = i6;
                }
                this.mWeekAdapterBloodOxygenHisListBean.add(new TemperatureHisListBean(pastStringArray.get(i4), i6 + "", ""));
                this.mWeekChartSumUpBloodOxygenHisListBean.add(new TemperatureHisListBean(TimeStampUtils.toFormatDate(pastStringArray.get(i4)), i6 + "", ""));
            }
        }
        if (i3 == 0 || this.mWeekAdapterBloodOxygenHisListBean.isEmpty()) {
            return;
        }
        Collections.reverse(this.mWeekAdapterBloodOxygenHisListBean);
        this.mWeekAverageBloodOxygenNum = (int) Math.round((i3 * 1.0d) / this.mWeekAdapterBloodOxygenHisListBean.size());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onMonthData(List<HealthMetric> data) {
        int size;
        int i2;
        ArrayList<String> pastStringArray = YearToDayListUtils.getPastStringArray(this.mToDay, 29);
        this.mAllDb = data;
        int i3 = 0;
        for (int i4 = 0; i4 < pastStringArray.size(); i4++) {
            List<HealthMetric> listFilterHealthMetricByDate = HealthDataFilterKt.filterHealthMetricByDate(this.mAllDb, pastStringArray.get(i4));
            if (listFilterHealthMetricByDate != null) {
                size = listFilterHealthMetricByDate.size();
                i2 = 0;
                for (int i5 = 0; i5 < listFilterHealthMetricByDate.size(); i5++) {
                    int bloodOxygenLevel = listFilterHealthMetricByDate.get(i5).getBloodOxygenLevel();
                    if (bloodOxygenLevel < TransUtils.BLOOD_OXYGEN_VISIBLE_MIN || bloodOxygenLevel > TransUtils.BLOOD_OXYGEN_VISIBLE_MAX) {
                        size--;
                    } else {
                        i2 += bloodOxygenLevel;
                    }
                }
            } else {
                size = 0;
                i2 = 0;
            }
            if (i2 == 0) {
                this.mMonthChartSumUpBloodOxygenHisListBean.add(new TemperatureHisListBean(TimeStampUtils.toFormatDate(pastStringArray.get(i4)), "0", ""));
            } else {
                int i6 = i2 / size;
                i3 += i6;
                if (i6 > this.mMonthMaxBloodOxygenNum) {
                    this.mMonthMaxBloodOxygenNum = i6;
                }
                if (i6 < this.mMonthMinBloodOxygenNum) {
                    this.mMonthMinBloodOxygenNum = i6;
                }
                this.mMonthAdapterBloodOxygenHisListBean.add(new TemperatureHisListBean(pastStringArray.get(i4), i6 + "", ""));
                this.mMonthChartSumUpBloodOxygenHisListBean.add(new TemperatureHisListBean(TimeStampUtils.toFormatDate(pastStringArray.get(i4)), i6 + "", ""));
            }
        }
        if (i3 == 0 || this.mMonthAdapterBloodOxygenHisListBean.isEmpty()) {
            return;
        }
        Collections.reverse(this.mMonthAdapterBloodOxygenHisListBean);
        this.mMonthAverageBloodOxygenNum = (int) Math.round((i3 * 1.0d) / this.mMonthAdapterBloodOxygenHisListBean.size());
    }

    public void getMonthData() {
        this.mMonthAdapterBloodOxygenHisListBean.clear();
        this.mMonthChartSumUpBloodOxygenHisListBean.clear();
        this.mMonthMaxBloodOxygenNum = 0;
        this.mMonthMinBloodOxygenNum = 100;
        this.mMonthAverageBloodOxygenNum = 0;
        this.mViewModel.getPackedDayData(YearToDayListUtils.getPastStringArray(this.mToDay, 29).get(0), 30);
        runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.bloodoxygen.activity.BloodOxygenActivity.7
            @Override // java.lang.Runnable
            public void run() throws Resources.NotFoundException {
                BloodOxygenActivity.this.initViewPager();
            }
        });
    }

    private void getDayBo(String dateTime) {
        HashMap map = new HashMap();
        map.put("userId", getIntent().getExtras().getString(Constant.SpConstKey.DEV_ID));
        map.put("day", dateTime);
        HttpUtils.getInstance().postMsgAsynHttp(this, com.yucheng.smarthealthpro.framework.util.Constants.bloodOxygenDayUrl, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.home.activity.bloodoxygen.activity.BloodOxygenActivity.8
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                if (result != null) {
                    BloodOxygenActivity.this.temp_bean = (FriendCareDataBean) new Gson().fromJson(result, FriendCareDataBean.class);
                    BloodOxygenActivity.this.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.bloodoxygen.activity.BloodOxygenActivity.8.1
                        @Override // java.lang.Runnable
                        public void run() throws Resources.NotFoundException {
                            BloodOxygenActivity.this.setDayData();
                            BloodOxygenActivity.this.initViewPager();
                        }
                    });
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v7, types: [java.util.List] */
    public void setDayData() {
        List<TemperatureHisListBean> list = this.mDayBloodOxygenHisListBean;
        if (list != null) {
            list.clear();
        }
        List<TemperatureHisListBean> list2 = this.mDayChartSumUpBloodOxygenHisListBean;
        if (list2 != null) {
            list2.clear();
        }
        FriendCareDataBean friendCareDataBean = this.temp_bean;
        if (friendCareDataBean == null || friendCareDataBean.data == null || this.temp_bean.data.size() == 0) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        try {
            arrayList = (List) new Gson().fromJson(this.temp_bean.data.get(0).mlist, new TypeToken<List<FriendCareSpo2Bean>>() { // from class: com.yucheng.smarthealthpro.home.activity.bloodoxygen.activity.BloodOxygenActivity.9
            }.getType());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        List<FriendCareSpo2Bean> listSortSpo2Data = Tools.sortSpo2Data(Tools.removeSpo2Data(arrayList));
        this.mDaySumUpBloodOxygenNum = 0;
        this.mDayMaxBloodOxygenNum = 0;
        this.mDayMinBloodOxygenNum = 100;
        for (FriendCareSpo2Bean friendCareSpo2Bean : listSortSpo2Data) {
            int i2 = friendCareSpo2Bean.bloodoxygen;
            if (i2 >= TransUtils.BLOOD_OXYGEN_VISIBLE_MIN && i2 <= TransUtils.BLOOD_OXYGEN_VISIBLE_MAX) {
                if (i2 > this.mDayMaxBloodOxygenNum) {
                    this.mDayMaxBloodOxygenNum = i2;
                }
                if (i2 < this.mDayMinBloodOxygenNum) {
                    this.mDayMinBloodOxygenNum = i2;
                }
                this.mDaySumUpBloodOxygenNum += i2;
                this.mDayBloodOxygenHisListBean.add(new TemperatureHisListBean(TimeStampUtils.dateForStringToDate(TimeStampUtils.longStampForDate(friendCareSpo2Bean.rtime)), i2 + "", "正常"));
                this.mDayChartSumUpBloodOxygenHisListBean.add(new TemperatureHisListBean(TimeStampUtils.dateForStringToDate(TimeStampUtils.longStampForDate(friendCareSpo2Bean.rtime)), i2 + "", "正常"));
            }
        }
        if (this.mDayBloodOxygenHisListBean.size() > 0) {
            this.mDayAverageBloodOxygenNum = Math.round((this.mDaySumUpBloodOxygenNum * 1.0f) / this.mDayBloodOxygenHisListBean.size());
        }
    }

    private void getWeekBo(String dateTime, String startTime, String endTime) {
        HashMap map = new HashMap();
        map.put("userId", getIntent().getExtras().getString(Constant.SpConstKey.DEV_ID));
        map.put("day", dateTime);
        map.put("startDate", startTime);
        map.put("endDate", endTime);
        map.put("type", "1");
        HttpUtils.getInstance().postMsgAsynHttp(this, com.yucheng.smarthealthpro.framework.util.Constants.bloodOxygenDayUrl, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.home.activity.bloodoxygen.activity.BloodOxygenActivity.10
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                if (result != null) {
                    List<CareBoWeekMonthBean.DataBean> data = ((CareBoWeekMonthBean) new Gson().fromJson(result, CareBoWeekMonthBean.class)).getData();
                    if (data.size() != 0) {
                        BloodOxygenActivity.this.mWeekSumUpBloodOxygenNum = 0;
                        BloodOxygenActivity.this.mWeekMaxBloodOxygenNum = 0;
                        BloodOxygenActivity.this.mWeekMinBloodOxygenNum = 100;
                        int i2 = 0;
                        while (true) {
                            if (i2 >= data.size()) {
                                break;
                            }
                            int i3 = (int) Float.parseFloat(data.get(i2).getBloodOxygenMean().isEmpty() ? "0" : data.get(i2).getBloodOxygenMean());
                            if (i3 >= TransUtils.BLOOD_OXYGEN_VISIBLE_MIN && i3 <= TransUtils.BLOOD_OXYGEN_VISIBLE_MAX) {
                                if (i3 > BloodOxygenActivity.this.mWeekMaxBloodOxygenNum) {
                                    BloodOxygenActivity.this.mWeekMaxBloodOxygenNum = i3;
                                }
                                if (i3 < BloodOxygenActivity.this.mWeekMinBloodOxygenNum) {
                                    BloodOxygenActivity.this.mWeekMinBloodOxygenNum = i3;
                                }
                                BloodOxygenActivity.this.mWeekSumUpBloodOxygenNum += i3;
                                BloodOxygenActivity.this.mWeekAdapterBloodOxygenHisListBean.add(new TemperatureHisListBean(TimeStampUtils.dateForStringDates(TimeStampUtils.stringForDateDay(data.get(i2).getDateformat())), i3 + "", "正常"));
                            }
                            i2++;
                        }
                        if (BloodOxygenActivity.this.mWeekAdapterBloodOxygenHisListBean.size() > 0) {
                            BloodOxygenActivity.this.mWeekAverageBloodOxygenNum = Math.round((r9.mWeekSumUpBloodOxygenNum * 1.0f) / BloodOxygenActivity.this.mWeekAdapterBloodOxygenHisListBean.size());
                        }
                        ArrayList<String> pastByMonthDayArray = YearToDayListUtils.getPastByMonthDayArray(BloodOxygenActivity.this.mToDay, 6);
                        for (int i4 = 0; i4 < pastByMonthDayArray.size(); i4++) {
                            BloodOxygenActivity.this.mWeekChartSumUpBloodOxygenHisListBean.add(new TemperatureHisListBean(pastByMonthDayArray.get(i4), "0", "正常"));
                        }
                        for (int i5 = 0; i5 < pastByMonthDayArray.size(); i5++) {
                            Iterator it2 = BloodOxygenActivity.this.mWeekAdapterBloodOxygenHisListBean.iterator();
                            while (true) {
                                if (it2.hasNext()) {
                                    TemperatureHisListBean temperatureHisListBean = (TemperatureHisListBean) it2.next();
                                    if (temperatureHisListBean.getTime().equals(pastByMonthDayArray.get(i5))) {
                                        BloodOxygenActivity.this.mWeekChartSumUpBloodOxygenHisListBean.remove(i5);
                                        BloodOxygenActivity.this.mWeekChartSumUpBloodOxygenHisListBean.add(i5, new TemperatureHisListBean(temperatureHisListBean.getTime(), temperatureHisListBean.getmValue(), "正常"));
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

    private void getMonthBo(String dateTime, String startTime, String endTime) {
        HashMap map = new HashMap();
        map.put("userId", getIntent().getExtras().getString(Constant.SpConstKey.DEV_ID));
        map.put("day", dateTime);
        map.put("startDate", startTime);
        map.put("endDate", endTime);
        map.put("type", "1");
        HttpUtils.getInstance().postMsgAsynHttp(this, com.yucheng.smarthealthpro.framework.util.Constants.bloodOxygenDayUrl, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.home.activity.bloodoxygen.activity.BloodOxygenActivity.11
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                if (result != null) {
                    List<CareBoWeekMonthBean.DataBean> data = ((CareBoWeekMonthBean) new Gson().fromJson(result, CareBoWeekMonthBean.class)).getData();
                    if (data.size() != 0) {
                        BloodOxygenActivity.this.mMonthSumUpBloodOxygenNum = 0;
                        BloodOxygenActivity.this.mMonthMaxBloodOxygenNum = 0;
                        BloodOxygenActivity.this.mMonthMinBloodOxygenNum = 100;
                        int i2 = 0;
                        while (true) {
                            if (i2 >= data.size()) {
                                break;
                            }
                            int i3 = (int) Float.parseFloat(data.get(i2).getBloodOxygenMean().isEmpty() ? "0" : data.get(i2).getBloodOxygenMean());
                            if (i3 <= TransUtils.BLOOD_OXYGEN_VISIBLE_MAX && i3 >= TransUtils.BLOOD_OXYGEN_VISIBLE_MIN) {
                                if (i3 > BloodOxygenActivity.this.mMonthMaxBloodOxygenNum) {
                                    BloodOxygenActivity.this.mMonthMaxBloodOxygenNum = i3;
                                }
                                if (i3 < BloodOxygenActivity.this.mMonthMinBloodOxygenNum) {
                                    BloodOxygenActivity.this.mMonthMinBloodOxygenNum = i3;
                                }
                                BloodOxygenActivity.this.mMonthSumUpBloodOxygenNum += i3;
                                BloodOxygenActivity.this.mMonthAdapterBloodOxygenHisListBean.add(new TemperatureHisListBean(TimeStampUtils.dateForStringDates(TimeStampUtils.stringForDateDay(data.get(i2).getDateformat())), i3 + "", "正常"));
                            }
                            i2++;
                        }
                        if (BloodOxygenActivity.this.mMonthAdapterBloodOxygenHisListBean.size() > 0) {
                            BloodOxygenActivity.this.mMonthAverageBloodOxygenNum = (int) Math.round((r10.mMonthSumUpBloodOxygenNum * 1.0d) / BloodOxygenActivity.this.mMonthAdapterBloodOxygenHisListBean.size());
                        }
                        ArrayList<String> pastByMonthDayArray = YearToDayListUtils.getPastByMonthDayArray(BloodOxygenActivity.this.mToDay, 29);
                        for (int i4 = 0; i4 < pastByMonthDayArray.size(); i4++) {
                            BloodOxygenActivity.this.mMonthChartSumUpBloodOxygenHisListBean.add(new TemperatureHisListBean(pastByMonthDayArray.get(i4), "0", "正常"));
                        }
                        for (int i5 = 0; i5 < pastByMonthDayArray.size(); i5++) {
                            Iterator it2 = BloodOxygenActivity.this.mMonthAdapterBloodOxygenHisListBean.iterator();
                            while (true) {
                                if (it2.hasNext()) {
                                    TemperatureHisListBean temperatureHisListBean = (TemperatureHisListBean) it2.next();
                                    if (temperatureHisListBean.getTime().equals(pastByMonthDayArray.get(i5))) {
                                        BloodOxygenActivity.this.mMonthChartSumUpBloodOxygenHisListBean.remove(i5);
                                        BloodOxygenActivity.this.mMonthChartSumUpBloodOxygenHisListBean.add(i5, new TemperatureHisListBean(temperatureHisListBean.getTime(), temperatureHisListBean.getmValue(), "正常"));
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
            BloodOxygenMeasureActivity.load(this);
            return;
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
                this.mNestedScrollView.postDelayed(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.bloodoxygen.activity.BloodOxygenActivity.12
                    @Override // java.lang.Runnable
                    public void run() {
                        BloodOxygenActivity.this.mNestedScrollView.smoothScrollTo(0, (int) (BloodOxygenActivity.this.mNestedScrollView.getScrollY() + (DpUtil.dp2px(BloodOxygenActivity.this.context, 56.0f) * 1.5f)));
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

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) throws Resources.NotFoundException {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == BloodOxygenMeasureActivity.BLOOD_OXYGEN_MEASURE && resultCode == -1) {
            initData();
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
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
            BloodOxygenActivity.this.mViewPager.setCurrentItem(position);
            if (position == 0) {
                BloodOxygenActivity.this.freshMonthData();
            } else if (position == 1) {
                BloodOxygenActivity.this.freshWeekData();
            } else {
                if (position != 2) {
                    return;
                }
                BloodOxygenActivity.this.freshDayData();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void freshDayData() {
        this.tvBackToday.setVisibility(8);
        this.llCalendar.setVisibility(0);
        List<TemperatureHisListBean> list = this.mDayBloodOxygenHisListBean;
        if (list != null && list.size() > 0) {
            this.tvDataFirst.setText(this.mDayAverageBloodOxygenNum + "");
            this.tvDataSecond.setText(this.mDayMaxBloodOxygenNum + "");
            this.tvDataThirdly.setText(this.mDayMinBloodOxygenNum + "");
        } else {
            this.tvDataFirst.setText("--");
            this.tvDataSecond.setText("--");
            this.tvDataThirdly.setText("--");
            this.mDayAverageBloodOxygenNum = 0;
        }
        this.mBloodOxygenHisListAdapter.setList(this.mDayBloodOxygenHisListBean);
        this.mBloodOxygenHisListAdapter.notifyDataSetChanged();
        dataAnalysis(this.mDayAverageBloodOxygenNum);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void freshWeekData() {
        this.tvBackToday.setVisibility(0);
        this.llCalendar.setVisibility(8);
        List<TemperatureHisListBean> list = this.mWeekAdapterBloodOxygenHisListBean;
        if (list != null && list.size() > 0) {
            this.tvDataFirst.setText(this.mWeekAverageBloodOxygenNum + "");
            this.tvDataSecond.setText(this.mWeekMaxBloodOxygenNum + "");
            this.tvDataThirdly.setText(this.mWeekMinBloodOxygenNum + "");
        } else {
            this.tvDataFirst.setText("--");
            this.tvDataSecond.setText("--");
            this.tvDataThirdly.setText("--");
            this.mWeekAverageBloodOxygenNum = 0;
        }
        this.mBloodOxygenHisListAdapter.setList(this.mWeekAdapterBloodOxygenHisListBean);
        this.mBloodOxygenHisListAdapter.notifyDataSetChanged();
        dataAnalysis(this.mWeekAverageBloodOxygenNum);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void freshMonthData() {
        this.tvBackToday.setVisibility(0);
        this.llCalendar.setVisibility(8);
        List<TemperatureHisListBean> list = this.mMonthAdapterBloodOxygenHisListBean;
        if (list != null && list.size() > 0) {
            this.tvDataFirst.setText(this.mMonthAverageBloodOxygenNum + "");
            this.tvDataSecond.setText(this.mMonthMaxBloodOxygenNum + "");
            this.tvDataThirdly.setText(this.mMonthMinBloodOxygenNum + "");
        } else {
            this.tvDataFirst.setText("--");
            this.tvDataSecond.setText("--");
            this.tvDataThirdly.setText("--");
            this.mMonthAverageBloodOxygenNum = 0;
        }
        this.mBloodOxygenHisListAdapter.setList(this.mMonthAdapterBloodOxygenHisListBean);
        this.mBloodOxygenHisListAdapter.notifyDataSetChanged();
        dataAnalysis(this.mMonthAverageBloodOxygenNum);
    }
}
