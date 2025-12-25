package com.yucheng.smarthealthpro.home.activity.pressure;

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
import com.yucheng.smarthealthpro.care.bean.CarePressureWeekMonthBean;
import com.yucheng.smarthealthpro.care.bean.HistoryPressureResponse;
import com.yucheng.smarthealthpro.data.packed.HealthDayData;
import com.yucheng.smarthealthpro.data.packed.HealthPackedData;
import com.yucheng.smarthealthpro.database.room.bean.BodyData;
import com.yucheng.smarthealthpro.databinding.ActivityHeartrateBinding;
import com.yucheng.smarthealthpro.framework.http.HttpUtils;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import com.yucheng.smarthealthpro.home.activity.HealthyActivity;
import com.yucheng.smarthealthpro.home.activity.MeasureTipActivity;
import com.yucheng.smarthealthpro.home.activity.pressure.PressureHisListAdapter;
import com.yucheng.smarthealthpro.home.activity.pressure.PressureTabFragmentAdapter;
import com.yucheng.smarthealthpro.home.activity.pressure.fragment.PressureTabFragment;
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
import com.yucheng.smarthealthpro.viewmodel.PressureViewModel;
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
public class PressureActivity extends BaseVbActivity<ActivityHeartrateBinding> implements CalendarView.OnCalendarSelectListener, CalendarView.OnMonthChangeListener {
    public static int PRESSURE_MEASURE;
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
    LinearLayout ll_pressure_analyse;
    private PressureTabFragmentAdapter mAdapter;
    private List<BodyData> mBodyDataDb;
    private Calendar mCalendar;
    CalendarView mCalendarView;
    private float mDayAveragePressureNum;
    private float mDayMaxPressureNum;
    private float mDayMinPressureNum;
    private float mDaySumUpPressureNum;
    private float mMonthAveragePressureNum;
    private float mMonthMaxPressureNum;
    private float mMonthMinPressureNum;
    private float mMonthSumUpPressureNum;
    NestedScrollView mNestedScrollView;
    private PressureHisListAdapter mPressureHisListAdapter;
    RecyclerView mRecyclerView;
    SlidingTabLayout mSlidingTabLayout;
    private String mToDay;
    private PressureViewModel mViewModel;
    NoScrollViewPager mViewPager;
    private float mWeekAveragePressureNum;
    private float mWeekMaxPressureNum;
    private float mWeekMinPressureNum;
    private float mWeekSumUpPressureNum;
    private int monthLastDay;
    RelativeLayout rlAnalyse;
    RelativeLayout rlDataFirst;
    RelativeLayout rlFirst;
    RelativeLayout rlFourthly;
    RelativeLayout rlSecond;
    private HistoryPressureResponse temp_bean;
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
    private List<TemperatureHisListBean> mDayPressureHisListBean = new ArrayList();
    private List<TemperatureHisListBean> mDayChartSumUpPressureHisListBean = new ArrayList();
    private List<TemperatureHisListBean> mWeekPressureHisListBean = new ArrayList();
    private List<TemperatureHisListBean> mWeekAdapterSumUpPressureHisListBean = new ArrayList();
    private List<TemperatureHisListBean> mWeekChartSumUpPressureHisListBean = new ArrayList();
    private List<TemperatureHisListBean> mMonthPressureHisListBean = new ArrayList();
    private List<TemperatureHisListBean> mMonthAdapterSumUpPressureHisListBean = new ArrayList();
    private List<TemperatureHisListBean> mMonthChartSumUpPressureHisListBean = new ArrayList();
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
        this.ll_pressure_analyse = ((ActivityHeartrateBinding) this.mBinding).includeItemBottom.llPressureAnalyse;
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
        this.llCalendar.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.pressure.PressureActivity$$ExternalSyntheticLambda1
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.tvBackToday.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.pressure.PressureActivity$$ExternalSyntheticLambda1
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.llStartButton.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.pressure.PressureActivity$$ExternalSyntheticLambda1
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlAnalyse.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.pressure.PressureActivity$$ExternalSyntheticLambda1
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlFirst.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.pressure.PressureActivity$$ExternalSyntheticLambda1
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlSecond.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.pressure.PressureActivity$$ExternalSyntheticLambda1
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlFourthly.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.pressure.PressureActivity$$ExternalSyntheticLambda1
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.llMonth.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.pressure.PressureActivity$$ExternalSyntheticLambda1
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        changeTitle(getString(R.string.pressure_str));
        showBack();
        showRightImage(R.mipmap.topbar_ic_share, new NavigationBar.MyOnClickListener() { // from class: com.yucheng.smarthealthpro.home.activity.pressure.PressureActivity.1
            @Override // com.yucheng.smarthealthpro.framework.view.NavigationBar.MyOnClickListener
            public void onClick(View btn) {
                if (PressureActivity.this.checkCanClick()) {
                    ShareUtils.share(PressureActivity.this);
                }
            }
        });
        int statusHeight = AppScreenMgr.getStatusHeight(this.context);
        this.llMonth.setPadding(0, DensityUtils.dip2px(this.context, 50.0f) + statusHeight, 0, 0);
        if (Constant.isTechFeel() || Constant.isHeGe()) {
            this.rlSecond.setVisibility(8);
        }
        String stringExtra = getIntent().getStringExtra("care");
        if (stringExtra != null && stringExtra.equals(getString(R.string.care_title))) {
            this.isCare = true;
            this.llStartButton.setVisibility(8);
            this.tvAnalyse.setText(getString(R.string.home_pressure_analysis));
            this.rlFirst.setVisibility(8);
            this.rlSecond.setVisibility(8);
            this.ivFourthlyRight.setBackground(ResourcesCompat.getDrawable(getResources(), R.mipmap.list_ic_arrow_n, null));
        } else {
            if (!YCBTClient.isSupportFunction(Constants.FunctionConstant.IS_HAS_PRESSURE_MEASUREMENT)) {
                this.llStartButton.setVisibility(8);
            }
            this.tvStartButton.setText(getString(R.string.pressure_measurement));
            this.tvAnalyse.setText(getString(R.string.home_pressure_analysis));
            this.tvFirst.setText(getString(R.string.include_bottom_tv_first_button));
            this.tvSecond.setText(getString(R.string.include_bottom_tv_second_button));
            this.tvFourthly.setText(getString(R.string.include_bottom_tv_fourthly_button));
            this.ivFourthlyRight.setBackground(ResourcesCompat.getDrawable(getResources(), R.mipmap.list_ic_arrow_n, null));
        }
        this.mToDay = TimeStampUtils.getToDay();
        this.rlAnalyse.setVisibility(8);
        this.ll_pressure_analyse.setVisibility(0);
    }

    private void initViewModel() {
        this.mViewModel = (PressureViewModel) new ViewModelProvider(this).get(PressureViewModel.class);
        FlowUtils.INSTANCE.collectFlow(this, this.mViewModel.getPressureDataFlow(), new FlowUtils.FlowCollector<HealthDayData<BodyData>>() { // from class: com.yucheng.smarthealthpro.home.activity.pressure.PressureActivity.2
            @Override // com.yucheng.smarthealthpro.utils.FlowUtils.FlowCollector
            public void collect(HealthDayData<BodyData> data) throws Resources.NotFoundException, NumberFormatException {
                PressureActivity.this.onThatVeryDayData(data.getDay(), data.getData());
            }
        });
        FlowUtils.INSTANCE.collectFlow(this, this.mViewModel.getPressurePackedDataFlow(), new FlowUtils.FlowCollector<HealthPackedData<BodyData>>() { // from class: com.yucheng.smarthealthpro.home.activity.pressure.PressureActivity.3
            @Override // com.yucheng.smarthealthpro.utils.FlowUtils.FlowCollector
            public void collect(HealthPackedData<BodyData> packedData) throws NumberFormatException {
                if (packedData.getDayCount() == 7) {
                    PressureActivity.this.onWeekData(packedData.getData());
                } else if (packedData.getDayCount() == 30) {
                    PressureActivity.this.onMonthData(packedData.getData());
                }
            }
        });
    }

    private void initData() throws Resources.NotFoundException {
        this.mDayPressureHisListBean = new ArrayList();
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
        new Thread(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.pressure.PressureActivity$$ExternalSyntheticLambda0
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
            getWeekPressure(pastStringArray.get(0), pastStringArray.get(0), pastStringArray.get(6));
            getMonthPressure(pastStringArray2.get(0), pastStringArray2.get(0), pastStringArray2.get(29));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initViewPager() throws Resources.NotFoundException {
        if (isFinishing()) {
            return;
        }
        PressureTabFragmentAdapter pressureTabFragmentAdapter = new PressureTabFragmentAdapter(getSupportFragmentManager(), new PressureTabFragmentAdapter.FragmentCreator() { // from class: com.yucheng.smarthealthpro.home.activity.pressure.PressureActivity.4
            @Override // com.yucheng.smarthealthpro.home.activity.pressure.PressureTabFragmentAdapter.FragmentCreator
            public Fragment createFragment(String data, int position) {
                return PressureTabFragment.newInstance(data, position, PressureActivity.this.mNestedScrollView, PressureActivity.this.mDayChartSumUpPressureHisListBean, PressureActivity.this.mWeekChartSumUpPressureHisListBean, PressureActivity.this.mMonthChartSumUpPressureHisListBean, PressureActivity.this.mDayMaxPressureNum);
            }

            @Override // com.yucheng.smarthealthpro.home.activity.pressure.PressureTabFragmentAdapter.FragmentCreator
            public String createTitle(String data) {
                return Html.fromHtml(data).toString();
            }
        });
        this.mAdapter = pressureTabFragmentAdapter;
        this.mViewPager.setAdapter(pressureTabFragmentAdapter);
        this.mAdapter.notifyDataSetChanged();
        this.mViewPager.setOffscreenPageLimit(this.mDayPressureHisListBean.size() - 1);
        this.mAdapter.setData(this.mTitles);
        this.mSlidingTabLayout.setViewPager(this.mViewPager, (String[]) this.mTitles.toArray(new String[0]));
        this.mSlidingTabLayout.setCurrentTab(2, true);
        this.mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.yucheng.smarthealthpro.home.activity.pressure.PressureActivity.5
            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int state) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int position) {
                if (position == 0) {
                    PressureActivity.this.mViewPager.setCurrentItem(0);
                    if (PressureActivity.this.mMonthAdapterSumUpPressureHisListBean != null && PressureActivity.this.mMonthAdapterSumUpPressureHisListBean.size() > 0) {
                        TextView textView = PressureActivity.this.tvDataFirst;
                        StringBuilder sb = new StringBuilder();
                        PressureActivity pressureActivity = PressureActivity.this;
                        textView.setText(sb.append(pressureActivity.transform(pressureActivity.mMonthAveragePressureNum)).append("").toString());
                        TextView textView2 = PressureActivity.this.tvDataSecond;
                        StringBuilder sb2 = new StringBuilder();
                        PressureActivity pressureActivity2 = PressureActivity.this;
                        textView2.setText(sb2.append(pressureActivity2.transform(pressureActivity2.mMonthMaxPressureNum)).append("").toString());
                        TextView textView3 = PressureActivity.this.tvDataThirdly;
                        StringBuilder sb3 = new StringBuilder();
                        PressureActivity pressureActivity3 = PressureActivity.this;
                        textView3.setText(sb3.append(pressureActivity3.transform(pressureActivity3.mMonthMinPressureNum)).append("").toString());
                    } else {
                        PressureActivity.this.tvDataFirst.setText("--");
                        PressureActivity.this.tvDataSecond.setText("--");
                        PressureActivity.this.tvDataThirdly.setText("--");
                        PressureActivity.this.mMonthAveragePressureNum = 0.0f;
                    }
                    PressureActivity.this.tvBackToday.setVisibility(0);
                    PressureActivity.this.llCalendar.setVisibility(8);
                    PressureActivity.this.mPressureHisListAdapter.replaceData(PressureActivity.this.mMonthAdapterSumUpPressureHisListBean);
                    PressureActivity.this.mPressureHisListAdapter.notifyDataSetChanged();
                    PressureActivity pressureActivity4 = PressureActivity.this;
                    pressureActivity4.dataAnalysis(pressureActivity4.mMonthAveragePressureNum);
                    return;
                }
                if (position == 1) {
                    PressureActivity.this.mViewPager.setCurrentItem(1);
                    if (PressureActivity.this.mWeekAdapterSumUpPressureHisListBean != null && PressureActivity.this.mWeekAdapterSumUpPressureHisListBean.size() > 0) {
                        TextView textView4 = PressureActivity.this.tvDataFirst;
                        StringBuilder sb4 = new StringBuilder();
                        PressureActivity pressureActivity5 = PressureActivity.this;
                        textView4.setText(sb4.append(pressureActivity5.transform(pressureActivity5.mWeekAveragePressureNum)).append("").toString());
                        TextView textView5 = PressureActivity.this.tvDataSecond;
                        StringBuilder sb5 = new StringBuilder();
                        PressureActivity pressureActivity6 = PressureActivity.this;
                        textView5.setText(sb5.append(pressureActivity6.transform(pressureActivity6.mWeekMaxPressureNum)).append("").toString());
                        TextView textView6 = PressureActivity.this.tvDataThirdly;
                        StringBuilder sb6 = new StringBuilder();
                        PressureActivity pressureActivity7 = PressureActivity.this;
                        textView6.setText(sb6.append(pressureActivity7.transform(pressureActivity7.mWeekMinPressureNum)).append("").toString());
                    } else {
                        PressureActivity.this.tvDataFirst.setText("--");
                        PressureActivity.this.tvDataSecond.setText("--");
                        PressureActivity.this.tvDataThirdly.setText("--");
                        PressureActivity.this.mWeekAveragePressureNum = 0.0f;
                    }
                    PressureActivity.this.tvBackToday.setVisibility(0);
                    PressureActivity.this.llCalendar.setVisibility(8);
                    PressureActivity.this.mPressureHisListAdapter.replaceData(PressureActivity.this.mWeekAdapterSumUpPressureHisListBean);
                    PressureActivity.this.mPressureHisListAdapter.notifyDataSetChanged();
                    PressureActivity pressureActivity8 = PressureActivity.this;
                    pressureActivity8.dataAnalysis(pressureActivity8.mWeekAveragePressureNum);
                    return;
                }
                if (position != 2) {
                    return;
                }
                PressureActivity.this.mViewPager.setCurrentItem(2);
                if (PressureActivity.this.mDayPressureHisListBean != null && PressureActivity.this.mDayPressureHisListBean.size() > 0) {
                    TextView textView7 = PressureActivity.this.tvDataFirst;
                    StringBuilder sb7 = new StringBuilder();
                    PressureActivity pressureActivity9 = PressureActivity.this;
                    textView7.setText(sb7.append(pressureActivity9.transform(pressureActivity9.mDayAveragePressureNum)).append("").toString());
                    TextView textView8 = PressureActivity.this.tvDataSecond;
                    StringBuilder sb8 = new StringBuilder();
                    PressureActivity pressureActivity10 = PressureActivity.this;
                    textView8.setText(sb8.append(pressureActivity10.transform(pressureActivity10.mDayMaxPressureNum)).append("").toString());
                    TextView textView9 = PressureActivity.this.tvDataThirdly;
                    StringBuilder sb9 = new StringBuilder();
                    PressureActivity pressureActivity11 = PressureActivity.this;
                    textView9.setText(sb9.append(pressureActivity11.transform(pressureActivity11.mDayMinPressureNum)).append("").toString());
                } else {
                    PressureActivity.this.tvDataFirst.setText("--");
                    PressureActivity.this.tvDataSecond.setText("--");
                    PressureActivity.this.tvDataThirdly.setText("--");
                    PressureActivity.this.mDayAveragePressureNum = 0.0f;
                }
                PressureActivity.this.tvBackToday.setVisibility(8);
                PressureActivity.this.llCalendar.setVisibility(0);
                PressureActivity.this.mPressureHisListAdapter.replaceData(PressureActivity.this.mDayPressureHisListBean);
                PressureActivity.this.mPressureHisListAdapter.notifyDataSetChanged();
                PressureActivity pressureActivity12 = PressureActivity.this;
                pressureActivity12.dataAnalysis(pressureActivity12.mDayAveragePressureNum);
            }
        });
    }

    public void dataAnalysis(float value) {
        if (value == 0.0f) {
            this.tvAnalyseData.setText("");
            return;
        }
        if (value >= 81.0f) {
            this.tvAnalyseData.setText(getText(R.string.home_pressure_analysis_severe));
            return;
        }
        if (value >= 51.0f) {
            this.tvAnalyseData.setText(getText(R.string.home_pressure_analysis_moderate));
        } else if (value >= 26.0f) {
            this.tvAnalyseData.setText(getText(R.string.home_pressure_analysis_mild));
        } else {
            this.tvAnalyseData.setText(getText(R.string.home_pressure_analysis_relax));
        }
    }

    private void setRecycleView() {
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        PressureHisListAdapter pressureHisListAdapter = new PressureHisListAdapter(R.layout.item_universal_his_list);
        this.mPressureHisListAdapter = pressureHisListAdapter;
        pressureHisListAdapter.addData((Collection) this.mDayPressureHisListBean);
        this.mRecyclerView.setAdapter(this.mPressureHisListAdapter);
        this.mRecyclerView.setNestedScrollingEnabled(false);
        this.mPressureHisListAdapter.setOnItemClickListener(new PressureHisListAdapter.OnItemClickListener() { // from class: com.yucheng.smarthealthpro.home.activity.pressure.PressureActivity.6
            @Override // com.yucheng.smarthealthpro.home.activity.pressure.PressureHisListAdapter.OnItemClickListener
            public void onClick(TemperatureHisListBean hisSearch, int position) {
            }

            @Override // com.yucheng.smarthealthpro.home.activity.pressure.PressureHisListAdapter.OnItemClickListener
            public void onDelClick(TemperatureHisListBean hisSearch, int position) {
            }

            @Override // com.yucheng.smarthealthpro.home.activity.pressure.PressureHisListAdapter.OnItemClickListener
            public void onLongClick(TemperatureHisListBean hisSearch, int position) {
            }
        });
    }

    private void initMonth() throws Resources.NotFoundException {
        this.mCalendarView.setOnCalendarSelectListener(this);
        this.mCalendarView.setOnMonthChangeListener(this);
        this.mCalendarView.scrollToCurrent();
        this.mCalendarView.setOnCalendarInterceptListener(new CalendarView.OnCalendarInterceptListener() { // from class: com.yucheng.smarthealthpro.home.activity.pressure.PressureActivity.7
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
            getDayPressure(strIntToStr);
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

    public void onThatVeryDayData(String thatVeryDay, List<BodyData> data) throws Resources.NotFoundException, NumberFormatException {
        List<TemperatureHisListBean> list = this.mDayPressureHisListBean;
        if (list != null) {
            list.clear();
        }
        this.mDayChartSumUpPressureHisListBean = new ArrayList();
        this.mBodyDataDb = data;
        if (data != null) {
            for (int i2 = 0; i2 < this.mBodyDataDb.size(); i2++) {
                if (TimeStampUtils.dateForStringYearToDate(TimeStampUtils.longStampForDate(this.mBodyDataDb.get(i2).getStartTimestamp())).equals(thatVeryDay) && this.mBodyDataDb.get(i2).getCompositePressure() != 0) {
                    this.mDayPressureHisListBean.add(new TemperatureHisListBean(TimeStampUtils.dateForStringToDate(TimeStampUtils.longStampForDate(this.mBodyDataDb.get(i2).getStartTimestamp())), this.mBodyDataDb.get(i2).getCompositePressure() + "", "正常"));
                    this.mDayChartSumUpPressureHisListBean.add(new TemperatureHisListBean(TimeStampUtils.dateForStringToDate(TimeStampUtils.longStampForDate(this.mBodyDataDb.get(i2).getStartTimestamp())), this.mBodyDataDb.get(i2).getCompositePressure() + "", "正常"));
                }
            }
        }
        if (this.mDayPressureHisListBean.size() != 0) {
            this.mThatVeryDay = thatVeryDay;
            this.mDaySumUpPressureNum = 0.0f;
            this.mDayMaxPressureNum = 0.0f;
            this.mDayMinPressureNum = Float.parseFloat(this.mDayPressureHisListBean.get(0).getmValue());
            for (int i3 = 0; i3 < this.mDayPressureHisListBean.size(); i3++) {
                float f2 = Float.parseFloat(this.mDayPressureHisListBean.get(i3).getmValue());
                if (f2 > this.mDayMaxPressureNum) {
                    this.mDayMaxPressureNum = f2;
                }
                if (f2 < this.mDayMinPressureNum) {
                    this.mDayMinPressureNum = f2;
                }
                this.mDaySumUpPressureNum += Float.parseFloat(this.mDayPressureHisListBean.get(i3).getmValue());
            }
            this.mDayAveragePressureNum = this.mDaySumUpPressureNum / this.mDayPressureHisListBean.size();
        }
        this.isGetDayData = true;
        if (this.isGetMonthData) {
            this.isGetDayData = false;
            initViewPager();
        }
    }

    public void onWeekData(List<BodyData> data) throws NumberFormatException {
        this.mWeekPressureHisListBean = new ArrayList();
        this.mWeekChartSumUpPressureHisListBean = new ArrayList();
        this.mWeekAdapterSumUpPressureHisListBean = new ArrayList();
        if (this.mBodyDataDb != null) {
            ArrayList<String> pastStringArray = YearToDayListUtils.getPastStringArray(this.mToDay, 6);
            if (pastStringArray.size() > 0) {
                this.mBodyDataDb = data;
            }
            for (int i2 = 0; i2 < pastStringArray.size(); i2++) {
                getWeekDay(pastStringArray.get(i2), i2);
            }
        }
        if (this.mWeekAdapterSumUpPressureHisListBean.size() != 0) {
            Collections.reverse(this.mWeekAdapterSumUpPressureHisListBean);
            this.mWeekSumUpPressureNum = 0.0f;
            this.mWeekMaxPressureNum = 0.0f;
            this.mWeekMinPressureNum = Float.parseFloat(this.mWeekAdapterSumUpPressureHisListBean.get(0).getmValue());
            for (int i3 = 0; i3 < this.mWeekAdapterSumUpPressureHisListBean.size(); i3++) {
                float f2 = Float.parseFloat(this.mWeekAdapterSumUpPressureHisListBean.get(i3).getmValue());
                if (f2 > this.mWeekMaxPressureNum) {
                    this.mWeekMaxPressureNum = f2;
                }
                if (f2 < this.mWeekMinPressureNum) {
                    this.mWeekMinPressureNum = f2;
                }
                this.mWeekSumUpPressureNum += Float.parseFloat(this.mWeekAdapterSumUpPressureHisListBean.get(i3).getmValue());
            }
            this.mWeekAveragePressureNum = this.mWeekSumUpPressureNum / this.mWeekAdapterSumUpPressureHisListBean.size();
        }
        Log.i("mWeekAdapter", "---" + this.mWeekMaxPressureNum + "mWeekMinPressureNum" + this.mWeekMinPressureNum + "mWeekSumUpPressureNum" + this.mWeekSumUpPressureNum);
    }

    private void getWeekDay(String mThatVeryDay, int index) {
        for (int i2 = 0; i2 < this.mBodyDataDb.size(); i2++) {
            if (TimeStampUtils.dateForStringYearToDate(TimeStampUtils.longStampForDate(this.mBodyDataDb.get(i2).getStartTimestamp())).equals(mThatVeryDay) && this.mBodyDataDb.get(i2).getCompositePressure() >= TransUtils.PRESSURE_VISIBLE_MIN && this.mBodyDataDb.get(i2).getCompositePressure() <= TransUtils.PRESSURE_VISIBLE_MAX) {
                this.mWeekPressureHisListBean.add(new TemperatureHisListBean(TimeStampUtils.dateForStringToDate(TimeStampUtils.longStampForDate(this.mBodyDataDb.get(i2).getStartTimestamp())), this.mBodyDataDb.get(i2).getCompositePressure() + "", "正常"));
            }
        }
        if (this.mWeekPressureHisListBean != null) {
            this.mWeekSumUpPressureNum = 0.0f;
            for (int i3 = 0; i3 < this.mWeekPressureHisListBean.size(); i3++) {
                this.mWeekSumUpPressureNum += Float.parseFloat(this.mWeekPressureHisListBean.get(i3).getmValue());
            }
            if (this.mWeekSumUpPressureNum != 0.0f) {
                this.mWeekAdapterSumUpPressureHisListBean.add(new TemperatureHisListBean(TimeStampUtils.dateForStringDates(TimeStampUtils.stringForDateDay(mThatVeryDay)), transform(this.mWeekSumUpPressureNum / this.mWeekPressureHisListBean.size()) + "", "正常"));
            }
            if (this.mWeekSumUpPressureNum != 0.0f && this.mWeekPressureHisListBean.size() != 0) {
                this.mWeekChartSumUpPressureHisListBean.add(index, new TemperatureHisListBean(TimeStampUtils.dateForStringDates(TimeStampUtils.stringForDateDay(mThatVeryDay)), transform(this.mWeekSumUpPressureNum / this.mWeekPressureHisListBean.size()) + "", "正常"));
            } else {
                this.mWeekChartSumUpPressureHisListBean.add(index, new TemperatureHisListBean(TimeStampUtils.dateForStringDates(TimeStampUtils.stringForDateDay(mThatVeryDay)), "0", "正常"));
            }
            this.mWeekPressureHisListBean.clear();
        }
    }

    public void onMonthData(List<BodyData> data) throws NumberFormatException {
        this.mMonthPressureHisListBean = new ArrayList();
        this.mMonthChartSumUpPressureHisListBean = new ArrayList();
        this.mMonthAdapterSumUpPressureHisListBean = new ArrayList();
        if (this.mBodyDataDb != null) {
            ArrayList<String> pastStringArray = YearToDayListUtils.getPastStringArray(this.mToDay, 29);
            if (pastStringArray.size() > 0) {
                this.mBodyDataDb = data;
            }
            for (int i2 = 0; i2 < pastStringArray.size(); i2++) {
                getMonthDay(pastStringArray.get(i2), i2);
            }
        }
        if (this.mMonthAdapterSumUpPressureHisListBean.size() != 0) {
            Collections.reverse(this.mMonthAdapterSumUpPressureHisListBean);
            this.mMonthSumUpPressureNum = 0.0f;
            this.mMonthMaxPressureNum = 0.0f;
            this.mMonthMinPressureNum = Float.parseFloat(this.mMonthAdapterSumUpPressureHisListBean.get(0).getmValue());
            for (int i3 = 0; i3 < this.mMonthAdapterSumUpPressureHisListBean.size(); i3++) {
                float f2 = Float.parseFloat(this.mMonthAdapterSumUpPressureHisListBean.get(i3).getmValue());
                if (f2 > this.mMonthMaxPressureNum) {
                    this.mMonthMaxPressureNum = f2;
                }
                if (f2 < this.mMonthMinPressureNum) {
                    this.mMonthMinPressureNum = f2;
                }
                this.mMonthSumUpPressureNum += Float.parseFloat(this.mMonthAdapterSumUpPressureHisListBean.get(i3).getmValue());
            }
            this.mMonthAveragePressureNum = this.mMonthSumUpPressureNum / this.mMonthAdapterSumUpPressureHisListBean.size();
        }
        runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.pressure.PressureActivity.8
            @Override // java.lang.Runnable
            public void run() throws Resources.NotFoundException {
                if (PressureActivity.this.isGetDayData) {
                    PressureActivity.this.isGetMonthData = true;
                    PressureActivity.this.initViewPager();
                }
            }
        });
    }

    private void getMonthDay(String mThatVeryDay, int index) {
        for (int i2 = 0; i2 < this.mBodyDataDb.size(); i2++) {
            if (TimeStampUtils.dateForStringYearToDate(TimeStampUtils.longStampForDate(this.mBodyDataDb.get(i2).getStartTimestamp())).equals(mThatVeryDay) && this.mBodyDataDb.get(i2).getCompositePressure() >= TransUtils.PRESSURE_VISIBLE_MIN && this.mBodyDataDb.get(i2).getCompositePressure() <= TransUtils.PRESSURE_VISIBLE_MAX) {
                this.mMonthPressureHisListBean.add(new TemperatureHisListBean(TimeStampUtils.dateForStringToDate(TimeStampUtils.longStampForDate(this.mBodyDataDb.get(i2).getStartTimestamp())), this.mBodyDataDb.get(i2).getCompositePressure() + "", "正常"));
            }
        }
        if (this.mMonthPressureHisListBean != null) {
            this.mMonthSumUpPressureNum = 0.0f;
            for (int i3 = 0; i3 < this.mMonthPressureHisListBean.size(); i3++) {
                this.mMonthSumUpPressureNum += Float.parseFloat(this.mMonthPressureHisListBean.get(i3).getmValue());
            }
            if (this.mMonthSumUpPressureNum != 0.0f) {
                this.mMonthAdapterSumUpPressureHisListBean.add(new TemperatureHisListBean(TimeStampUtils.dateForStringDates(TimeStampUtils.stringForDateDay(mThatVeryDay)), transform((this.mMonthSumUpPressureNum * 1.0f) / this.mMonthPressureHisListBean.size()) + "", "正常"));
            }
            if (this.mMonthSumUpPressureNum != 0.0f && this.mMonthPressureHisListBean.size() != 0) {
                this.mMonthChartSumUpPressureHisListBean.add(index, new TemperatureHisListBean(TimeStampUtils.dateForStringDates(TimeStampUtils.stringForDateDay(mThatVeryDay)), transform((this.mMonthSumUpPressureNum * 1.0f) / this.mMonthPressureHisListBean.size()) + "", "正常"));
            } else {
                this.mMonthChartSumUpPressureHisListBean.add(index, new TemperatureHisListBean(TimeStampUtils.dateForStringDates(TimeStampUtils.stringForDateDay(mThatVeryDay)), "0", "正常"));
            }
            this.mMonthPressureHisListBean.clear();
        }
    }

    private void getDayPressure(String dateTime) {
        HashMap map = new HashMap();
        map.put("userId", getIntent().getExtras().getString(Constant.SpConstKey.DEV_ID));
        map.put("day", dateTime);
        HttpUtils.getInstance().postMsgAsynHttp(this, com.yucheng.smarthealthpro.framework.util.Constants.pressureDayUrl, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.home.activity.pressure.PressureActivity.9
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                if (result != null) {
                    PressureActivity.this.temp_bean = (HistoryPressureResponse) new Gson().fromJson(result, HistoryPressureResponse.class);
                    PressureActivity.this.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.pressure.PressureActivity.9.1
                        @Override // java.lang.Runnable
                        public void run() throws Resources.NotFoundException {
                            PressureActivity.this.setDayData();
                            PressureActivity.this.initViewPager();
                        }
                    });
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v14, types: [java.util.List] */
    public void setDayData() {
        List<TemperatureHisListBean> list = this.mDayPressureHisListBean;
        if (list != null) {
            list.clear();
        }
        List<TemperatureHisListBean> list2 = this.mDayChartSumUpPressureHisListBean;
        if (list2 != null) {
            list2.clear();
        }
        HistoryPressureResponse historyPressureResponse = this.temp_bean;
        if (historyPressureResponse == null || historyPressureResponse.data == null || this.temp_bean.data.size() <= 0) {
            return;
        }
        ArrayList<HistoryPressureResponse.Mlist> arrayList = new ArrayList();
        try {
            arrayList = (List) new Gson().fromJson(this.temp_bean.data.get(0).mlist, new TypeToken<List<HistoryPressureResponse.Mlist>>() { // from class: com.yucheng.smarthealthpro.home.activity.pressure.PressureActivity.10
            }.getType());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        this.mDaySumUpPressureNum = 0.0f;
        this.mDayMaxPressureNum = 0.0f;
        this.mDayMinPressureNum = TransUtils.PRESSURE_VISIBLE_MAX;
        Collections.sort(arrayList);
        for (HistoryPressureResponse.Mlist mlist : arrayList) {
            int i2 = (int) (mlist.pressure * 10.0f);
            if (i2 >= TransUtils.PRESSURE_VISIBLE_MIN && i2 <= TransUtils.PRESSURE_VISIBLE_MAX) {
                float f2 = i2;
                if (f2 > this.mDayMaxPressureNum) {
                    this.mDayMaxPressureNum = f2;
                }
                if (f2 < this.mDayMinPressureNum) {
                    this.mDayMinPressureNum = f2;
                }
                this.mDaySumUpPressureNum += f2;
                this.mDayPressureHisListBean.add(new TemperatureHisListBean(TimeStampUtils.dateForStringToDate(TimeStampUtils.longStampForDate(mlist.rtime)), i2 + "", "正常"));
                this.mDayChartSumUpPressureHisListBean.add(new TemperatureHisListBean(TimeStampUtils.dateForStringToDate(TimeStampUtils.longStampForDate(mlist.rtime)), i2 + "", "正常"));
            }
        }
        if (this.mDayPressureHisListBean.size() > 0) {
            this.mDayAveragePressureNum = this.mDaySumUpPressureNum / this.mDayPressureHisListBean.size();
        }
    }

    private void getWeekPressure(String dateTime, String startTime, String endTime) {
        HashMap map = new HashMap();
        map.put("userId", getIntent().getExtras().getString(Constant.SpConstKey.DEV_ID));
        map.put("day", dateTime);
        map.put("startDate", startTime);
        map.put("endDate", endTime);
        map.put("type", "1");
        HttpUtils.getInstance().postMsgAsynHttp(this, com.yucheng.smarthealthpro.framework.util.Constants.pressureDayUrl, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.home.activity.pressure.PressureActivity.11
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                if (result != null) {
                    List<CarePressureWeekMonthBean.DataBean> data = ((CarePressureWeekMonthBean) new Gson().fromJson(result, CarePressureWeekMonthBean.class)).getData();
                    if (data.size() != 0) {
                        PressureActivity.this.mWeekSumUpPressureNum = 0.0f;
                        PressureActivity.this.mWeekMaxPressureNum = 0.0f;
                        PressureActivity.this.mWeekMinPressureNum = TransUtils.PRESSURE_VISIBLE_MAX;
                        int i2 = 0;
                        while (true) {
                            if (i2 >= data.size()) {
                                break;
                            }
                            int i3 = (int) (Float.parseFloat(data.get(i2).getMean().isEmpty() ? "0" : data.get(i2).getMean()) * 10.0f);
                            if (i3 >= TransUtils.PRESSURE_VISIBLE_MIN && i3 <= TransUtils.PRESSURE_VISIBLE_MAX) {
                                float f2 = i3;
                                if (f2 > PressureActivity.this.mWeekMaxPressureNum) {
                                    PressureActivity.this.mWeekMaxPressureNum = f2;
                                }
                                if (f2 < PressureActivity.this.mWeekMinPressureNum) {
                                    PressureActivity.this.mWeekMinPressureNum = f2;
                                }
                                PressureActivity.this.mWeekSumUpPressureNum += f2;
                                if (!data.get(i2).getMean().isEmpty()) {
                                    PressureActivity.this.mWeekAdapterSumUpPressureHisListBean.add(new TemperatureHisListBean(TimeStampUtils.dateForStringDates(TimeStampUtils.stringForDateDay(data.get(i2).getDateformat())), i3 + "", "正常"));
                                }
                            }
                            i2++;
                        }
                        if (PressureActivity.this.mWeekAdapterSumUpPressureHisListBean.size() > 0) {
                            PressureActivity pressureActivity = PressureActivity.this;
                            pressureActivity.mWeekAveragePressureNum = pressureActivity.mWeekSumUpPressureNum / PressureActivity.this.mWeekAdapterSumUpPressureHisListBean.size();
                        }
                        ArrayList<String> pastByMonthDayArray = YearToDayListUtils.getPastByMonthDayArray(PressureActivity.this.mToDay, 6);
                        for (int i4 = 0; i4 < pastByMonthDayArray.size(); i4++) {
                            PressureActivity.this.mWeekChartSumUpPressureHisListBean.add(new TemperatureHisListBean(pastByMonthDayArray.get(i4), "0", "正常"));
                        }
                        for (int i5 = 0; i5 < pastByMonthDayArray.size(); i5++) {
                            Iterator it2 = PressureActivity.this.mWeekAdapterSumUpPressureHisListBean.iterator();
                            while (true) {
                                if (it2.hasNext()) {
                                    TemperatureHisListBean temperatureHisListBean = (TemperatureHisListBean) it2.next();
                                    if (temperatureHisListBean.getTime().equals(pastByMonthDayArray.get(i5))) {
                                        PressureActivity.this.mWeekChartSumUpPressureHisListBean.remove(i5);
                                        PressureActivity.this.mWeekChartSumUpPressureHisListBean.add(i5, new TemperatureHisListBean(temperatureHisListBean.getTime(), temperatureHisListBean.getmValue(), "正常"));
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

    private void getMonthPressure(String dateTime, String startTime, String endTime) {
        HashMap map = new HashMap();
        map.put("userId", getIntent().getExtras().getString(Constant.SpConstKey.DEV_ID));
        map.put("day", dateTime);
        map.put("startDate", startTime);
        map.put("endDate", endTime);
        map.put("type", "1");
        HttpUtils.getInstance().postMsgAsynHttp(this, com.yucheng.smarthealthpro.framework.util.Constants.pressureDayUrl, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.home.activity.pressure.PressureActivity.12
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                if (result != null) {
                    List<CarePressureWeekMonthBean.DataBean> data = ((CarePressureWeekMonthBean) new Gson().fromJson(result, CarePressureWeekMonthBean.class)).getData();
                    if (data.size() != 0) {
                        PressureActivity.this.mMonthChartSumUpPressureHisListBean.clear();
                        PressureActivity.this.mMonthSumUpPressureNum = 0.0f;
                        PressureActivity.this.mMonthMaxPressureNum = 0.0f;
                        PressureActivity.this.mMonthMinPressureNum = TransUtils.PRESSURE_VISIBLE_MAX;
                        int i2 = 0;
                        while (true) {
                            if (i2 >= data.size()) {
                                break;
                            }
                            int i3 = (int) (Float.parseFloat(data.get(i2).getMean().isEmpty() ? "0" : data.get(i2).getMean()) * 10.0f);
                            if (i3 >= TransUtils.PRESSURE_VISIBLE_MIN && i3 <= TransUtils.PRESSURE_VISIBLE_MAX) {
                                float f2 = i3;
                                if (f2 > PressureActivity.this.mMonthMaxPressureNum) {
                                    PressureActivity.this.mMonthMaxPressureNum = f2;
                                }
                                if (f2 < PressureActivity.this.mMonthMinPressureNum) {
                                    PressureActivity.this.mMonthMinPressureNum = f2;
                                }
                                PressureActivity.this.mMonthSumUpPressureNum += f2;
                                if (!data.get(i2).getMean().isEmpty()) {
                                    PressureActivity.this.mMonthAdapterSumUpPressureHisListBean.add(new TemperatureHisListBean(TimeStampUtils.dateForStringDates(TimeStampUtils.stringForDateDay(data.get(i2).getDateformat())), i3 + "", "正常"));
                                }
                            }
                            i2++;
                        }
                        if (PressureActivity.this.mMonthAdapterSumUpPressureHisListBean.size() > 0) {
                            PressureActivity pressureActivity = PressureActivity.this;
                            pressureActivity.mMonthAveragePressureNum = pressureActivity.mMonthSumUpPressureNum / PressureActivity.this.mMonthAdapterSumUpPressureHisListBean.size();
                        }
                        ArrayList<String> pastByMonthDayArray = YearToDayListUtils.getPastByMonthDayArray(PressureActivity.this.mToDay, 29);
                        for (int i4 = 0; i4 < pastByMonthDayArray.size(); i4++) {
                            PressureActivity.this.mMonthChartSumUpPressureHisListBean.add(new TemperatureHisListBean(pastByMonthDayArray.get(i4), "0", "正常"));
                        }
                        for (int i5 = 0; i5 < pastByMonthDayArray.size(); i5++) {
                            Iterator it2 = PressureActivity.this.mMonthAdapterSumUpPressureHisListBean.iterator();
                            while (true) {
                                if (it2.hasNext()) {
                                    TemperatureHisListBean temperatureHisListBean = (TemperatureHisListBean) it2.next();
                                    if (temperatureHisListBean.getTime().equals(pastByMonthDayArray.get(i5))) {
                                        PressureActivity.this.mMonthChartSumUpPressureHisListBean.remove(i5);
                                        PressureActivity.this.mMonthChartSumUpPressureHisListBean.add(i5, new TemperatureHisListBean(temperatureHisListBean.getTime(), temperatureHisListBean.getmValue(), "正常"));
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
        if (requestCode == PRESSURE_MEASURE && resultCode == -1) {
            initData();
        }
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity
    public void onActivityResult(ActivityResult result, int requestCode) throws Resources.NotFoundException {
        super.onActivityResult(result, requestCode);
        if (requestCode == PressureMeasureActivity.HEART_RATE_MEASURE && result.getResultCode() == -1) {
            initData();
        } else if (requestCode == MeasureTipActivity.MEASURE_TIP) {
            launchActivityForResult(PressureMeasureActivity.HEART_RATE_MEASURE, PressureMeasureActivity.class);
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
                launchActivityForResult(PressureMeasureActivity.HEART_RATE_MEASURE, PressureMeasureActivity.class);
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
                this.mNestedScrollView.postDelayed(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.pressure.PressureActivity.13
                    @Override // java.lang.Runnable
                    public void run() {
                        PressureActivity.this.mNestedScrollView.smoothScrollTo(0, (int) (PressureActivity.this.mNestedScrollView.getScrollY() + (DpUtil.dp2px(PressureActivity.this.context, 56.0f) * 1.5f)));
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

    public String transform(float value) {
        return ((int) value) + "";
    }
}
