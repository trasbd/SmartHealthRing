package com.yucheng.smarthealthpro.home.activity.heartrate.activity;

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
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.haibin.calendarview.CalendarView;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.care.bean.CareHeartWeekMonthBean;
import com.yucheng.smarthealthpro.care.bean.HistoryHeartResponse;
import com.yucheng.smarthealthpro.data.packed.HealthDayData;
import com.yucheng.smarthealthpro.data.packed.HealthPackedData;
import com.yucheng.smarthealthpro.database.room.bean.HeartRate;
import com.yucheng.smarthealthpro.databinding.ActivityHeartrateBinding;
import com.yucheng.smarthealthpro.framework.http.HttpUtils;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import com.yucheng.smarthealthpro.home.activity.HealthyActivity;
import com.yucheng.smarthealthpro.home.activity.MeasureTipActivity;
import com.yucheng.smarthealthpro.home.activity.heartrate.adapter.HeartRateHisListAdapter;
import com.yucheng.smarthealthpro.home.activity.heartrate.adapter.HeartRateTabFragmentAdapter;
import com.yucheng.smarthealthpro.home.activity.heartrate.fragment.HeartRateTabFragment;
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
import com.yucheng.smarthealthpro.utils.Tools;
import com.yucheng.smarthealthpro.utils.TransUtils;
import com.yucheng.smarthealthpro.utils.YearToDayListUtils;
import com.yucheng.smarthealthpro.viewmodel.HeartRateViewModel;
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
public class HeartRateActivity extends BaseVbActivity<ActivityHeartrateBinding> implements CalendarView.OnCalendarSelectListener, CalendarView.OnMonthChangeListener {
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
    private HeartRateTabFragmentAdapter mAdapter;
    private Calendar mCalendar;
    CalendarView mCalendarView;
    private int mDayAverageHeartNum;
    private int mDayMaxHeartNum;
    private int mDayMinHeartNum;
    private int mDaySumUpHeartNum;
    private List<HeartRate> mHeartDb;
    private HeartRateHisListAdapter mHeartRateHisListAdapter;
    private int mMonthAverageHeartNum;
    private int mMonthMaxHeartNum;
    private int mMonthMinHeartNum;
    private int mMonthSumUpHeartNum;
    NestedScrollView mNestedScrollView;
    RecyclerView mRecyclerView;
    SlidingTabLayout mSlidingTabLayout;
    private String mToDay;
    private HeartRateViewModel mViewModel;
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
    private HistoryHeartResponse temp_bean;
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
        this.llCalendar.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.heartrate.activity.HeartRateActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.tvBackToday.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.heartrate.activity.HeartRateActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.llStartButton.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.heartrate.activity.HeartRateActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlAnalyse.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.heartrate.activity.HeartRateActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlFirst.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.heartrate.activity.HeartRateActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlSecond.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.heartrate.activity.HeartRateActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlFourthly.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.heartrate.activity.HeartRateActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.llMonth.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.heartrate.activity.HeartRateActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        changeTitle(getString(R.string.function_heart));
        showBack();
        showRightImage(R.mipmap.topbar_ic_share, new NavigationBar.MyOnClickListener() { // from class: com.yucheng.smarthealthpro.home.activity.heartrate.activity.HeartRateActivity.1
            @Override // com.yucheng.smarthealthpro.framework.view.NavigationBar.MyOnClickListener
            public void onClick(View btn) {
                if (HeartRateActivity.this.checkCanClick()) {
                    ShareUtils.share(HeartRateActivity.this);
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
            if (!YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHATESTHEART)) {
                this.llStartButton.setVisibility(8);
            }
            this.tvStartButton.setText(getString(R.string.heart_rate_measure_title));
            this.tvAnalyse.setText(getString(R.string.home_heart_rate_analyse_tv));
            this.tvFirst.setText(getString(R.string.include_bottom_tv_first_button));
            this.tvSecond.setText(getString(R.string.include_bottom_tv_second_button));
            this.tvFourthly.setText(getString(R.string.include_bottom_tv_fourthly_button));
            this.ivFourthlyRight.setBackground(ResourcesCompat.getDrawable(getResources(), R.mipmap.list_ic_arrow_n, null));
        }
        this.mToDay = TimeStampUtils.getToDay();
    }

    private void initViewModel() {
        this.mViewModel = (HeartRateViewModel) new ViewModelProvider(this).get(HeartRateViewModel.class);
        FlowUtils.INSTANCE.collectFlow(this, this.mViewModel.getHeartRateDataFlow(), new FlowUtils.FlowCollector<HealthDayData<HeartRate>>() { // from class: com.yucheng.smarthealthpro.home.activity.heartrate.activity.HeartRateActivity.2
            @Override // com.yucheng.smarthealthpro.utils.FlowUtils.FlowCollector
            public void collect(HealthDayData<HeartRate> data) throws Resources.NotFoundException, NumberFormatException {
                HeartRateActivity.this.onThatVeryDayData(data.getDay(), data.getData());
            }
        });
        FlowUtils.INSTANCE.collectFlow(this, this.mViewModel.getHeartRatePackedDataFlow(), new FlowUtils.FlowCollector<HealthPackedData<HeartRate>>() { // from class: com.yucheng.smarthealthpro.home.activity.heartrate.activity.HeartRateActivity.3
            @Override // com.yucheng.smarthealthpro.utils.FlowUtils.FlowCollector
            public void collect(HealthPackedData<HeartRate> packedData) throws NumberFormatException {
                if (packedData.getDayCount() == 7) {
                    HeartRateActivity.this.onWeekData(packedData.getData());
                } else if (packedData.getDayCount() == 30) {
                    HeartRateActivity.this.onMonthData(packedData.getData());
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
        new Thread(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.heartrate.activity.HeartRateActivity$$ExternalSyntheticLambda1
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
        if (this.mViewPager == null) {
            if (isFinishing()) {
                return;
            }
            finish();
            return;
        }
        HeartRateTabFragmentAdapter heartRateTabFragmentAdapter = new HeartRateTabFragmentAdapter(getSupportFragmentManager(), new HeartRateTabFragmentAdapter.FragmentCreator() { // from class: com.yucheng.smarthealthpro.home.activity.heartrate.activity.HeartRateActivity.4
            @Override // com.yucheng.smarthealthpro.home.activity.heartrate.adapter.HeartRateTabFragmentAdapter.FragmentCreator
            public Fragment createFragment(String data, int position) {
                return HeartRateTabFragment.newInstance(data, position, HeartRateActivity.this.mNestedScrollView, HeartRateActivity.this.mDayChartSumUpHeartRateHisListBean, HeartRateActivity.this.mWeekChartSumUpHeartRateHisListBean, HeartRateActivity.this.mMonthChartSumUpHeartRateHisListBean, HeartRateActivity.this.mDayMaxHeartNum);
            }

            @Override // com.yucheng.smarthealthpro.home.activity.heartrate.adapter.HeartRateTabFragmentAdapter.FragmentCreator
            public String createTitle(String data) {
                return Html.fromHtml(data).toString();
            }
        });
        this.mAdapter = heartRateTabFragmentAdapter;
        this.mViewPager.setAdapter(heartRateTabFragmentAdapter);
        this.mAdapter.notifyDataSetChanged();
        this.mViewPager.setOffscreenPageLimit(this.mDayHeartRateHisListBean.size() - 1);
        this.mAdapter.setData(this.mTitles);
        this.mSlidingTabLayout.setViewPager(this.mViewPager, (String[]) this.mTitles.toArray(new String[0]));
        this.mSlidingTabLayout.setCurrentTab(2, true);
        this.mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.yucheng.smarthealthpro.home.activity.heartrate.activity.HeartRateActivity.5
            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int state) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int position) throws NumberFormatException {
                if (position == 0) {
                    HeartRateActivity.this.mViewPager.setCurrentItem(0);
                    if (HeartRateActivity.this.mMonthAdapterSumUpHeartRateHisListBean != null && HeartRateActivity.this.mMonthAdapterSumUpHeartRateHisListBean.size() > 0) {
                        HeartRateActivity.this.tvDataFirst.setText(HeartRateActivity.this.mMonthAverageHeartNum + "");
                        HeartRateActivity.this.tvDataSecond.setText(HeartRateActivity.this.mMonthMaxHeartNum + "");
                        HeartRateActivity.this.tvDataThirdly.setText(HeartRateActivity.this.mMonthMinHeartNum + "");
                    } else {
                        HeartRateActivity.this.tvDataFirst.setText("--");
                        HeartRateActivity.this.tvDataSecond.setText("--");
                        HeartRateActivity.this.tvDataThirdly.setText("--");
                        HeartRateActivity.this.mMonthAverageHeartNum = 0;
                    }
                    HeartRateActivity.this.tvBackToday.setVisibility(0);
                    HeartRateActivity.this.llCalendar.setVisibility(8);
                    HeartRateActivity.this.mHeartRateHisListAdapter.replaceData(HeartRateActivity.this.mMonthAdapterSumUpHeartRateHisListBean);
                    HeartRateActivity.this.mHeartRateHisListAdapter.notifyDataSetChanged();
                    HeartRateActivity heartRateActivity = HeartRateActivity.this;
                    heartRateActivity.dataAnalysis(heartRateActivity.mMonthAverageHeartNum);
                    return;
                }
                if (position == 1) {
                    HeartRateActivity.this.mViewPager.setCurrentItem(1);
                    if (HeartRateActivity.this.mWeekAdapterSumUpHeartRateHisListBean != null && HeartRateActivity.this.mWeekAdapterSumUpHeartRateHisListBean.size() > 0) {
                        HeartRateActivity.this.tvDataFirst.setText(HeartRateActivity.this.mWeekAverageHeartNum + "");
                        HeartRateActivity.this.tvDataSecond.setText(HeartRateActivity.this.mWeekMaxHeartNum + "");
                        HeartRateActivity.this.tvDataThirdly.setText(HeartRateActivity.this.mWeekMinHeartNum + "");
                    } else {
                        HeartRateActivity.this.tvDataFirst.setText("--");
                        HeartRateActivity.this.tvDataSecond.setText("--");
                        HeartRateActivity.this.tvDataThirdly.setText("--");
                        HeartRateActivity.this.mWeekAverageHeartNum = 0;
                    }
                    HeartRateActivity.this.tvBackToday.setVisibility(0);
                    HeartRateActivity.this.llCalendar.setVisibility(8);
                    HeartRateActivity.this.mHeartRateHisListAdapter.replaceData(HeartRateActivity.this.mWeekAdapterSumUpHeartRateHisListBean);
                    HeartRateActivity.this.mHeartRateHisListAdapter.notifyDataSetChanged();
                    HeartRateActivity heartRateActivity2 = HeartRateActivity.this;
                    heartRateActivity2.dataAnalysis(heartRateActivity2.mWeekAverageHeartNum);
                    return;
                }
                if (position != 2) {
                    return;
                }
                HeartRateActivity.this.mViewPager.setCurrentItem(2);
                if (HeartRateActivity.this.mDayHeartRateHisListBean != null && HeartRateActivity.this.mDayHeartRateHisListBean.size() > 0) {
                    HeartRateActivity.this.tvDataFirst.setText(HeartRateActivity.this.mDayAverageHeartNum + "");
                    HeartRateActivity.this.tvDataSecond.setText(HeartRateActivity.this.mDayMaxHeartNum + "");
                    HeartRateActivity.this.tvDataThirdly.setText(HeartRateActivity.this.mDayMinHeartNum + "");
                } else {
                    HeartRateActivity.this.tvDataFirst.setText("--");
                    HeartRateActivity.this.tvDataSecond.setText("--");
                    HeartRateActivity.this.tvDataThirdly.setText("--");
                    HeartRateActivity.this.mDayAverageHeartNum = 0;
                }
                HeartRateActivity.this.tvBackToday.setVisibility(8);
                HeartRateActivity.this.llCalendar.setVisibility(0);
                HeartRateActivity.this.mHeartRateHisListAdapter.replaceData(HeartRateActivity.this.mDayHeartRateHisListBean);
                HeartRateActivity.this.mHeartRateHisListAdapter.notifyDataSetChanged();
                HeartRateActivity heartRateActivity3 = HeartRateActivity.this;
                heartRateActivity3.dataAnalysis(heartRateActivity3.mDayAverageHeartNum);
            }
        });
    }

    public void dataAnalysis(int value) throws NumberFormatException {
        if (Constant.isTechFeel()) {
            if (this.mHeartRateHisListAdapter.getData().size() > 0) {
                this.tvDataFirst.setText("" + Math.round(Integer.parseInt(this.mHeartRateHisListAdapter.getData().get(0).getmValue())));
            } else {
                this.tvDataFirst.setText("--");
            }
        }
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
        HeartRateHisListAdapter heartRateHisListAdapter = new HeartRateHisListAdapter(R.layout.item_universal_his_list);
        this.mHeartRateHisListAdapter = heartRateHisListAdapter;
        heartRateHisListAdapter.addData((Collection) this.mDayHeartRateHisListBean);
        this.mRecyclerView.setAdapter(this.mHeartRateHisListAdapter);
        this.mRecyclerView.setNestedScrollingEnabled(false);
        this.mHeartRateHisListAdapter.setOnItemClickListener(new HeartRateHisListAdapter.OnItemClickListener() { // from class: com.yucheng.smarthealthpro.home.activity.heartrate.activity.HeartRateActivity.6
            @Override // com.yucheng.smarthealthpro.home.activity.heartrate.adapter.HeartRateHisListAdapter.OnItemClickListener
            public void onClick(TemperatureHisListBean hisSearch, int position) {
            }

            @Override // com.yucheng.smarthealthpro.home.activity.heartrate.adapter.HeartRateHisListAdapter.OnItemClickListener
            public void onDelClick(TemperatureHisListBean hisSearch, int position) {
            }

            @Override // com.yucheng.smarthealthpro.home.activity.heartrate.adapter.HeartRateHisListAdapter.OnItemClickListener
            public void onLongClick(TemperatureHisListBean hisSearch, int position) {
            }
        });
    }

    private void initMonth() throws Resources.NotFoundException {
        this.mCalendarView.setOnCalendarSelectListener(this);
        this.mCalendarView.setOnMonthChangeListener(this);
        this.mCalendarView.scrollToCurrent();
        this.mCalendarView.setOnCalendarInterceptListener(new CalendarView.OnCalendarInterceptListener() { // from class: com.yucheng.smarthealthpro.home.activity.heartrate.activity.HeartRateActivity.7
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

    public void onThatVeryDayData(String thatVeryDay, List<HeartRate> data) throws Resources.NotFoundException, NumberFormatException {
        List<TemperatureHisListBean> list = this.mDayHeartRateHisListBean;
        if (list != null) {
            list.clear();
        }
        this.mDayChartSumUpHeartRateHisListBean = new ArrayList();
        this.mHeartDb = data;
        if (data != null) {
            for (int i2 = 0; i2 < this.mHeartDb.size(); i2++) {
                if (TimeStampUtils.dateForStringYearToDate(TimeStampUtils.longStampForDate(this.mHeartDb.get(i2).getStartTimestamp())).equals(thatVeryDay) && this.mHeartDb.get(i2).getHeartRate() >= TransUtils.HEART_RATE_VISIBLE_MIN && this.mHeartDb.get(i2).getHeartRate() <= TransUtils.HEART_RATE_VISIBLE_MAX) {
                    this.mDayHeartRateHisListBean.add(new TemperatureHisListBean(TimeStampUtils.dateForStringToDate(TimeStampUtils.longStampForDate(this.mHeartDb.get(i2).getStartTimestamp())), this.mHeartDb.get(i2).getHeartRate() + "", "正常"));
                    this.mDayChartSumUpHeartRateHisListBean.add(new TemperatureHisListBean(TimeStampUtils.dateForStringToDate(TimeStampUtils.longStampForDate(this.mHeartDb.get(i2).getStartTimestamp())), this.mHeartDb.get(i2).getHeartRate() + "", "正常"));
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

    public void onWeekData(List<HeartRate> data) throws NumberFormatException {
        this.mWeekHeartRateHisListBean = new ArrayList();
        this.mWeekChartSumUpHeartRateHisListBean = new ArrayList();
        this.mWeekAdapterSumUpHeartRateHisListBean = new ArrayList();
        if (this.mHeartDb != null) {
            ArrayList<String> pastStringArray = YearToDayListUtils.getPastStringArray(this.mToDay, 6);
            if (pastStringArray.size() > 0) {
                this.mHeartDb = data;
            }
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
        for (int i2 = 0; i2 < this.mHeartDb.size(); i2++) {
            if (TimeStampUtils.dateForStringYearToDate(TimeStampUtils.longStampForDate(this.mHeartDb.get(i2).getStartTimestamp())).equals(mThatVeryDay) && this.mHeartDb.get(i2).getHeartRate() >= TransUtils.HEART_RATE_VISIBLE_MIN && this.mHeartDb.get(i2).getHeartRate() <= TransUtils.HEART_RATE_VISIBLE_MAX) {
                this.mWeekHeartRateHisListBean.add(new TemperatureHisListBean(TimeStampUtils.dateForStringToDate(TimeStampUtils.longStampForDate(this.mHeartDb.get(i2).getStartTimestamp())), this.mHeartDb.get(i2).getHeartRate() + "", "正常"));
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

    public void onMonthData(List<HeartRate> data) throws NumberFormatException {
        this.mMonthHeartRateHisListBean = new ArrayList();
        this.mMonthChartSumUpHeartRateHisListBean = new ArrayList();
        this.mMonthAdapterSumUpHeartRateHisListBean = new ArrayList();
        if (this.mHeartDb != null) {
            ArrayList<String> pastStringArray = YearToDayListUtils.getPastStringArray(this.mToDay, 29);
            if (pastStringArray.size() > 0) {
                this.mHeartDb = data;
            }
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
            this.mMonthAverageHeartNum = Math.round((this.mMonthSumUpHeartNum * 1.0f) / this.mMonthAdapterSumUpHeartRateHisListBean.size());
        }
        runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.heartrate.activity.HeartRateActivity.8
            @Override // java.lang.Runnable
            public void run() throws Resources.NotFoundException {
                if (HeartRateActivity.this.isGetDayData) {
                    HeartRateActivity.this.isGetMonthData = true;
                    HeartRateActivity.this.initViewPager();
                }
            }
        });
    }

    private void getMonthDay(String mThatVeryDay, int index) {
        for (int i2 = 0; i2 < this.mHeartDb.size(); i2++) {
            if (TimeStampUtils.dateForStringYearToDate(TimeStampUtils.longStampForDate(this.mHeartDb.get(i2).getStartTimestamp())).equals(mThatVeryDay) && this.mHeartDb.get(i2).getHeartRate() >= TransUtils.HEART_RATE_VISIBLE_MIN && this.mHeartDb.get(i2).getHeartRate() <= TransUtils.HEART_RATE_VISIBLE_MAX) {
                this.mMonthHeartRateHisListBean.add(new TemperatureHisListBean(TimeStampUtils.dateForStringToDate(TimeStampUtils.longStampForDate(this.mHeartDb.get(i2).getStartTimestamp())), this.mHeartDb.get(i2).getHeartRate() + "", "正常"));
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
        HttpUtils.getInstance().postMsgAsynHttp(this, com.yucheng.smarthealthpro.framework.util.Constants.heartDayUrl, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.home.activity.heartrate.activity.HeartRateActivity.9
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                if (result != null) {
                    HeartRateActivity.this.temp_bean = (HistoryHeartResponse) new Gson().fromJson(result, HistoryHeartResponse.class);
                    HeartRateActivity.this.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.heartrate.activity.HeartRateActivity.9.1
                        @Override // java.lang.Runnable
                        public void run() throws Resources.NotFoundException {
                            HeartRateActivity.this.setDayData();
                            HeartRateActivity.this.initViewPager();
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
        HistoryHeartResponse historyHeartResponse = this.temp_bean;
        if (historyHeartResponse == null || historyHeartResponse.data == null || this.temp_bean.data.size() <= 0) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        try {
            arrayList = (List) new Gson().fromJson(this.temp_bean.data.get(0).mlist, new TypeToken<List<HistoryHeartResponse.Mlist>>() { // from class: com.yucheng.smarthealthpro.home.activity.heartrate.activity.HeartRateActivity.10
            }.getType());
        } catch (JsonSyntaxException e2) {
            e2.printStackTrace();
        }
        List<HistoryHeartResponse.Mlist> listSortHeartData = Tools.sortHeartData(Tools.removeHeartData(arrayList));
        this.mDaySumUpHeartNum = 0;
        this.mDayMaxHeartNum = 0;
        this.mDayMinHeartNum = TransUtils.HEART_RATE_VISIBLE_MAX;
        for (HistoryHeartResponse.Mlist mlist : listSortHeartData) {
            int i2 = mlist.heartTimes;
            if (i2 >= TransUtils.HEART_RATE_VISIBLE_MIN && i2 <= TransUtils.HEART_RATE_VISIBLE_MAX) {
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
        HttpUtils.getInstance().postMsgAsynHttp(this, com.yucheng.smarthealthpro.framework.util.Constants.heartDayUrl, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.home.activity.heartrate.activity.HeartRateActivity.11
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                if (result != null) {
                    List<CareHeartWeekMonthBean.DataBean> data = ((CareHeartWeekMonthBean) new Gson().fromJson(result, CareHeartWeekMonthBean.class)).getData();
                    if (data.size() != 0) {
                        HeartRateActivity.this.mWeekSumUpHeartNum = 0;
                        HeartRateActivity.this.mWeekMaxHeartNum = 0;
                        HeartRateActivity.this.mWeekMinHeartNum = 220;
                        int i2 = 0;
                        while (true) {
                            if (i2 >= data.size()) {
                                break;
                            }
                            int iRound = Math.round(Float.parseFloat(data.get(i2).getHeartMean().isEmpty() ? "0" : data.get(i2).getHeartMean()));
                            if (iRound >= TransUtils.HEART_RATE_VISIBLE_MIN && iRound <= TransUtils.HEART_RATE_VISIBLE_MAX) {
                                if (iRound > HeartRateActivity.this.mWeekMaxHeartNum) {
                                    HeartRateActivity.this.mWeekMaxHeartNum = iRound;
                                }
                                if (iRound < HeartRateActivity.this.mWeekMinHeartNum) {
                                    HeartRateActivity.this.mWeekMinHeartNum = iRound;
                                }
                                HeartRateActivity.this.mWeekSumUpHeartNum += iRound;
                                if (!data.get(i2).getHeartMean().isEmpty()) {
                                    HeartRateActivity.this.mWeekAdapterSumUpHeartRateHisListBean.add(new TemperatureHisListBean(TimeStampUtils.dateForStringDates(TimeStampUtils.stringForDateDay(data.get(i2).getDateformat())), iRound + "", "正常"));
                                }
                            }
                            i2++;
                        }
                        if (HeartRateActivity.this.mWeekAdapterSumUpHeartRateHisListBean.size() > 0) {
                            HeartRateActivity.this.mWeekAverageHeartNum = (int) Math.round((r10.mWeekSumUpHeartNum * 1.0d) / HeartRateActivity.this.mWeekAdapterSumUpHeartRateHisListBean.size());
                        }
                        ArrayList<String> pastByMonthDayArray = YearToDayListUtils.getPastByMonthDayArray(HeartRateActivity.this.mToDay, 6);
                        for (int i3 = 0; i3 < pastByMonthDayArray.size(); i3++) {
                            HeartRateActivity.this.mWeekChartSumUpHeartRateHisListBean.add(new TemperatureHisListBean(pastByMonthDayArray.get(i3), "0", "正常"));
                        }
                        for (int i4 = 0; i4 < pastByMonthDayArray.size(); i4++) {
                            Iterator it2 = HeartRateActivity.this.mWeekAdapterSumUpHeartRateHisListBean.iterator();
                            while (true) {
                                if (it2.hasNext()) {
                                    TemperatureHisListBean temperatureHisListBean = (TemperatureHisListBean) it2.next();
                                    if (temperatureHisListBean.getTime().equals(pastByMonthDayArray.get(i4))) {
                                        HeartRateActivity.this.mWeekChartSumUpHeartRateHisListBean.remove(i4);
                                        HeartRateActivity.this.mWeekChartSumUpHeartRateHisListBean.add(i4, new TemperatureHisListBean(temperatureHisListBean.getTime(), temperatureHisListBean.getmValue(), "正常"));
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
        HttpUtils.getInstance().postMsgAsynHttp(this, com.yucheng.smarthealthpro.framework.util.Constants.heartDayUrl, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.home.activity.heartrate.activity.HeartRateActivity.12
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                if (result != null) {
                    List<CareHeartWeekMonthBean.DataBean> data = ((CareHeartWeekMonthBean) new Gson().fromJson(result, CareHeartWeekMonthBean.class)).getData();
                    if (data.size() != 0) {
                        HeartRateActivity.this.mMonthChartSumUpHeartRateHisListBean.clear();
                        HeartRateActivity.this.mMonthSumUpHeartNum = 0;
                        HeartRateActivity.this.mMonthMaxHeartNum = 0;
                        HeartRateActivity.this.mMonthMinHeartNum = TransUtils.HEART_RATE_VISIBLE_MAX;
                        int i2 = 0;
                        while (true) {
                            if (i2 >= data.size()) {
                                break;
                            }
                            int iRound = Math.round(Float.parseFloat(data.get(i2).getHeartMean().isEmpty() ? "0" : data.get(i2).getHeartMean()));
                            if (iRound >= TransUtils.HEART_RATE_VISIBLE_MIN && iRound <= TransUtils.HEART_RATE_VISIBLE_MAX) {
                                if (iRound > HeartRateActivity.this.mMonthMaxHeartNum) {
                                    HeartRateActivity.this.mMonthMaxHeartNum = iRound;
                                }
                                if (iRound < HeartRateActivity.this.mMonthMinHeartNum) {
                                    HeartRateActivity.this.mMonthMinHeartNum = iRound;
                                }
                                HeartRateActivity.this.mMonthSumUpHeartNum += iRound;
                                if (!data.get(i2).getHeartMean().isEmpty()) {
                                    HeartRateActivity.this.mMonthAdapterSumUpHeartRateHisListBean.add(new TemperatureHisListBean(TimeStampUtils.dateForStringDates(TimeStampUtils.stringForDateDay(data.get(i2).getDateformat())), iRound + "", "正常"));
                                }
                            }
                            i2++;
                        }
                        if (HeartRateActivity.this.mMonthAdapterSumUpHeartRateHisListBean.size() > 0) {
                            HeartRateActivity.this.mMonthAverageHeartNum = Math.round((r9.mMonthSumUpHeartNum * 1.0f) / HeartRateActivity.this.mMonthAdapterSumUpHeartRateHisListBean.size());
                        }
                        ArrayList<String> pastByMonthDayArray = YearToDayListUtils.getPastByMonthDayArray(HeartRateActivity.this.mToDay, 29);
                        for (int i3 = 0; i3 < pastByMonthDayArray.size(); i3++) {
                            HeartRateActivity.this.mMonthChartSumUpHeartRateHisListBean.add(new TemperatureHisListBean(pastByMonthDayArray.get(i3), "0", "正常"));
                        }
                        for (int i4 = 0; i4 < pastByMonthDayArray.size(); i4++) {
                            Iterator it2 = HeartRateActivity.this.mMonthAdapterSumUpHeartRateHisListBean.iterator();
                            while (true) {
                                if (it2.hasNext()) {
                                    TemperatureHisListBean temperatureHisListBean = (TemperatureHisListBean) it2.next();
                                    if (temperatureHisListBean.getTime().equals(pastByMonthDayArray.get(i4))) {
                                        HeartRateActivity.this.mMonthChartSumUpHeartRateHisListBean.remove(i4);
                                        HeartRateActivity.this.mMonthChartSumUpHeartRateHisListBean.add(i4, new TemperatureHisListBean(temperatureHisListBean.getTime(), temperatureHisListBean.getmValue(), "正常"));
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
        if (requestCode == HeartRateMeasureActivity.HEART_RATE_MEASURE && resultCode == -1) {
            initData();
        }
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity
    public void onActivityResult(ActivityResult result, int requestCode) throws Resources.NotFoundException {
        super.onActivityResult(result, requestCode);
        if (requestCode == HeartRateMeasureActivity.HEART_RATE_MEASURE && result.getResultCode() == -1) {
            initData();
        } else if (requestCode == MeasureTipActivity.MEASURE_TIP) {
            launchActivityForResult(HeartRateMeasureActivity.HEART_RATE_MEASURE, HeartRateMeasureActivity.class);
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
                HeartRateMeasureActivity.load(this);
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
                this.mNestedScrollView.postDelayed(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.heartrate.activity.HeartRateActivity.13
                    @Override // java.lang.Runnable
                    public void run() {
                        HeartRateActivity.this.mNestedScrollView.smoothScrollTo(0, (int) (HeartRateActivity.this.mNestedScrollView.getScrollY() + (DpUtil.dp2px(HeartRateActivity.this.context, 56.0f) * 1.5f)));
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
