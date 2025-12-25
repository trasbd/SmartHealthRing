package com.yucheng.smarthealthpro.home.activity.temperature.activity;

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
import com.tencent.bugly.crashreport.BuglyLog;
import com.tencent.bugly.crashreport.CrashReport;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.care.bean.CareTempWeekMonthBean;
import com.yucheng.smarthealthpro.care.bean.FriendCareDataBean;
import com.yucheng.smarthealthpro.care.bean.FriendCareTempBean;
import com.yucheng.smarthealthpro.data.packed.HealthDayData;
import com.yucheng.smarthealthpro.data.packed.HealthPackedData;
import com.yucheng.smarthealthpro.database.room.bean.HealthMetric;
import com.yucheng.smarthealthpro.databinding.ActivityTemperatureBinding;
import com.yucheng.smarthealthpro.framework.http.HttpUtils;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import com.yucheng.smarthealthpro.home.activity.HealthyActivity;
import com.yucheng.smarthealthpro.home.activity.MeasureTipActivity;
import com.yucheng.smarthealthpro.home.activity.temperature.adapter.TempTabFragmentAdapter;
import com.yucheng.smarthealthpro.home.activity.temperature.adapter.TemperatureHisListAdapter;
import com.yucheng.smarthealthpro.home.activity.temperature.bean.TemperatureHisListBean;
import com.yucheng.smarthealthpro.home.activity.temperature.fragment.TempTabFragment;
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
import com.yucheng.smarthealthpro.utils.FormatUtil;
import com.yucheng.smarthealthpro.utils.ShareUtils;
import com.yucheng.smarthealthpro.utils.TimeStampUtils;
import com.yucheng.smarthealthpro.utils.Tools;
import com.yucheng.smarthealthpro.utils.TransUtils;
import com.yucheng.smarthealthpro.utils.YearToDayListUtils;
import com.yucheng.smarthealthpro.viewmodel.TemperatureViewModel;
import com.yucheng.ycbtsdk.Constants;
import com.yucheng.ycbtsdk.YCBTClient;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class TemperatureActivity extends BaseVbActivity<ActivityTemperatureBinding> implements CalendarView.OnCalendarSelectListener, CalendarView.OnMonthChangeListener {
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
    private TempTabFragmentAdapter mAdapter;
    private List<HealthMetric> mAllDb;
    private Calendar mCalendar;
    CalendarView mCalendarView;
    private float mDayAverageTempNum;
    private float mDayMaxTempNum;
    private float mDayMinTempNum;
    private float mDaySumUpTempNum;
    private float mLastNum;
    private float mMonthAverageTempNum;
    private float mMonthMaxTempNum;
    private float mMonthMinTempNum;
    private float mMonthSumUpTempNum;
    NestedScrollView mNestedScrollView;
    RecyclerView mRecyclerView;
    SlidingTabLayout mSlidingTabLayout;
    private int mTempUnit;
    private TemperatureHisListAdapter mTemperatureHisListAdapter;
    private String mToDay;
    private TemperatureViewModel mViewModel;
    NoScrollViewPager mViewPager;
    private float mWeekAverageTempNum;
    private float mWeekMaxTempNum;
    private float mWeekMinTempNum;
    private float mWeekSumUpTempNum;
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
    private List<TemperatureHisListBean> mDayTemperatureHisListBean = new ArrayList();
    private List<TemperatureHisListBean> mDayChartSumUpTemperatureHisListBean = new ArrayList();
    private List<TemperatureHisListBean> mWeekAdapterTemperatureHisListBean = new ArrayList();
    private List<TemperatureHisListBean> mWeekChartSumUpTemperatureHisListBean = new ArrayList();
    private List<TemperatureHisListBean> mMonthAdapterTemperatureHisListBean = new ArrayList();
    private List<TemperatureHisListBean> mMonthChartSumUpTemperatureHisListBean = new ArrayList();
    private Boolean isCare = false;

    @Override // com.haibin.calendarview.CalendarView.OnCalendarSelectListener
    public void onCalendarOutOfRange(com.haibin.calendarview.Calendar calendar) {
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) throws Resources.NotFoundException {
        super.onCreate(savedInstanceState);
        String str = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.TEMP_UNIT, "");
        if ((str == null || !str.equals(Constant.SpConstValue.TEMP_ISO)) && str != null && str.equals(Constant.SpConstValue.TEMP_INCH)) {
            this.mTempUnit = 1;
        } else {
            this.mTempUnit = 0;
        }
        initView();
        initViewModel();
        initData();
    }

    private void initView() {
        this.mSlidingTabLayout = ((ActivityTemperatureBinding) this.mBinding).includeItemTop.stlTab;
        this.ivCalendar = ((ActivityTemperatureBinding) this.mBinding).includeItemTop.ivCalendar;
        this.tvCalendar = ((ActivityTemperatureBinding) this.mBinding).includeItemTop.tvCalendar;
        this.tvBackToday = ((ActivityTemperatureBinding) this.mBinding).includeItemTop.tvBackToday;
        this.llCalendar = ((ActivityTemperatureBinding) this.mBinding).includeItemTop.llCalendar;
        this.mViewPager = ((ActivityTemperatureBinding) this.mBinding).includeItemTop.vpTab;
        this.tvDataFirst = ((ActivityTemperatureBinding) this.mBinding).includeItemMessageData.tvDataFirst;
        this.tvDataFirstUnit = ((ActivityTemperatureBinding) this.mBinding).includeItemMessageData.tvDataFirstUnit;
        this.rlDataFirst = ((ActivityTemperatureBinding) this.mBinding).includeItemMessageData.rlDataFirst;
        this.tvDataSecond = ((ActivityTemperatureBinding) this.mBinding).includeItemMessageData.tvDataSecond;
        this.ivDataSecond = ((ActivityTemperatureBinding) this.mBinding).includeItemMessageData.ivDataSecond;
        this.tvDataSecondUnit = ((ActivityTemperatureBinding) this.mBinding).includeItemMessageData.tvDataSecondUnit;
        this.llDataSecond = ((ActivityTemperatureBinding) this.mBinding).includeItemMessageData.llDataSecond;
        this.tvDataThirdly = ((ActivityTemperatureBinding) this.mBinding).includeItemMessageData.tvDataThirdly;
        this.ivDataThirdly = ((ActivityTemperatureBinding) this.mBinding).includeItemMessageData.ivDataThirdly;
        this.tvDataThirdlyUnit = ((ActivityTemperatureBinding) this.mBinding).includeItemMessageData.tvDataThirdlyUnit;
        this.llDataThirdly = ((ActivityTemperatureBinding) this.mBinding).includeItemMessageData.llDataThirdly;
        this.tvStartButton = ((ActivityTemperatureBinding) this.mBinding).includeItemBottom.tvStartButton;
        this.llStartButton = ((ActivityTemperatureBinding) this.mBinding).includeItemBottom.llStartButton;
        this.tvAnalyse = ((ActivityTemperatureBinding) this.mBinding).includeItemBottom.tvAnalyse;
        this.tvAnalyseData = ((ActivityTemperatureBinding) this.mBinding).includeItemBottom.tvAnalyseData;
        this.rlAnalyse = ((ActivityTemperatureBinding) this.mBinding).includeItemBottom.rlAnalyse;
        this.ivFirstLeft = ((ActivityTemperatureBinding) this.mBinding).includeItemBottom.ivFirstLeft;
        this.tvFirst = ((ActivityTemperatureBinding) this.mBinding).includeItemBottom.tvFirst;
        this.ivFirstRight = ((ActivityTemperatureBinding) this.mBinding).includeItemBottom.ivFirstRight;
        this.rlFirst = ((ActivityTemperatureBinding) this.mBinding).includeItemBottom.rlFirst;
        this.ivSecondLeft = ((ActivityTemperatureBinding) this.mBinding).includeItemBottom.ivSecondLeft;
        this.tvSecond = ((ActivityTemperatureBinding) this.mBinding).includeItemBottom.tvSecond;
        this.ivSecondRight = ((ActivityTemperatureBinding) this.mBinding).includeItemBottom.ivSecondRight;
        this.rlSecond = ((ActivityTemperatureBinding) this.mBinding).includeItemBottom.rlSecond;
        this.ivFourthlyLeft = ((ActivityTemperatureBinding) this.mBinding).includeItemBottom.ivFourthlyLeft;
        this.tvFourthly = ((ActivityTemperatureBinding) this.mBinding).includeItemBottom.tvFourthly;
        this.ivFourthlyRight = ((ActivityTemperatureBinding) this.mBinding).includeItemBottom.ivFourthlyRight;
        this.rlFourthly = ((ActivityTemperatureBinding) this.mBinding).includeItemBottom.rlFourthly;
        this.mRecyclerView = ((ActivityTemperatureBinding) this.mBinding).includeItemBottom.recycleView;
        this.mNestedScrollView = ((ActivityTemperatureBinding) this.mBinding).nsv;
        this.tvYears = ((ActivityTemperatureBinding) this.mBinding).includeItemCalendar.tvYears;
        this.mCalendarView = ((ActivityTemperatureBinding) this.mBinding).includeItemCalendar.calendarView;
        this.llMonth = ((ActivityTemperatureBinding) this.mBinding).includeItemCalendar.llMonth;
        this.llCalendar.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.temperature.activity.TemperatureActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.tvBackToday.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.temperature.activity.TemperatureActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.llStartButton.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.temperature.activity.TemperatureActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlAnalyse.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.temperature.activity.TemperatureActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlFirst.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.temperature.activity.TemperatureActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlSecond.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.temperature.activity.TemperatureActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlFourthly.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.temperature.activity.TemperatureActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.llMonth.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.temperature.activity.TemperatureActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        changeTitle(getString(R.string.function_temp));
        showBack();
        showRightImage(R.mipmap.topbar_ic_share, new NavigationBar.MyOnClickListener() { // from class: com.yucheng.smarthealthpro.home.activity.temperature.activity.TemperatureActivity.1
            @Override // com.yucheng.smarthealthpro.framework.view.NavigationBar.MyOnClickListener
            public void onClick(View btn) {
                if (TemperatureActivity.this.checkCanClick()) {
                    ShareUtils.share(TemperatureActivity.this);
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
            this.tvAnalyse.setText(getString(R.string.home_temperature_analyse_tv));
            this.llStartButton.setVisibility(8);
            this.rlFirst.setVisibility(8);
            this.rlSecond.setVisibility(8);
            this.ivFourthlyRight.setBackground(ResourcesCompat.getDrawable(getResources(), R.mipmap.list_ic_arrow_n, null));
        } else {
            if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASTESTTEMP)) {
                this.llStartButton.setVisibility(0);
                this.tvStartButton.setText(getString(R.string.home_temperature_measure_normal));
            } else if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASTEMPAXILLARYTEST)) {
                this.llStartButton.setVisibility(0);
                this.tvStartButton.setText(getString(R.string.home_temperature_measure_title));
            } else {
                this.llStartButton.setVisibility(8);
            }
            this.tvAnalyse.setText(getString(R.string.home_temperature_analyse_tv));
            this.tvFirst.setText(getString(R.string.include_bottom_tv_first_button));
            this.tvSecond.setText(getString(R.string.include_bottom_tv_second_button));
            this.tvFourthly.setText(getString(R.string.include_bottom_tv_fourthly_button));
            this.ivFourthlyRight.setBackground(ResourcesCompat.getDrawable(getResources(), R.mipmap.list_ic_arrow_n, null));
        }
        this.mToDay = TimeStampUtils.getToDay();
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        this.isCare.booleanValue();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) throws Resources.NotFoundException {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TemperatureMeasureNormalActivity.TEMPERATURE_MEASURE_NORMAL && resultCode == -1) {
            initData();
        }
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity
    public void onActivityResult(ActivityResult result, int requestCode) throws Resources.NotFoundException {
        super.onActivityResult(result, requestCode);
        if ((requestCode == TemperatureMeasureNormalActivity.TEMPERATURE_MEASURE_NORMAL || requestCode == TemperatureMeasureActivity.TEMPERATURE_MEASURE) && result.getResultCode() == -1) {
            initData();
            return;
        }
        if (requestCode == MeasureTipActivity.MEASURE_TIP) {
            if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASTESTTEMP)) {
                launchActivityForResult(TemperatureMeasureNormalActivity.TEMPERATURE_MEASURE_NORMAL, TemperatureMeasureNormalActivity.class);
            } else if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASTEMPAXILLARYTEST)) {
                launchActivityForResult(TemperatureMeasureActivity.TEMPERATURE_MEASURE, TemperatureMeasureActivity.class);
            }
        }
    }

    private void initViewModel() {
        this.mViewModel = (TemperatureViewModel) new ViewModelProvider(this).get(TemperatureViewModel.class);
        FlowUtils.INSTANCE.collectFlow(this, this.mViewModel.getTemperatureDataFlow(), new FlowUtils.FlowCollector<HealthDayData<HealthMetric>>() { // from class: com.yucheng.smarthealthpro.home.activity.temperature.activity.TemperatureActivity.2
            @Override // com.yucheng.smarthealthpro.utils.FlowUtils.FlowCollector
            public void collect(HealthDayData<HealthMetric> data) throws Resources.NotFoundException {
                TemperatureActivity.this.onThatVeryDayData(data.getDay(), data.getData());
            }
        });
        FlowUtils.INSTANCE.collectFlow(this, this.mViewModel.getTemperaturePackedDataFlow(), new FlowUtils.FlowCollector<HealthPackedData<HealthMetric>>() { // from class: com.yucheng.smarthealthpro.home.activity.temperature.activity.TemperatureActivity.3
            @Override // com.yucheng.smarthealthpro.utils.FlowUtils.FlowCollector
            public void collect(HealthPackedData<HealthMetric> packedData) throws NumberFormatException {
                if (packedData.getDayCount() == 7) {
                    TemperatureActivity.this.onWeekData(packedData.getData());
                } else if (packedData.getDayCount() == 30) {
                    TemperatureActivity.this.onMonthData(packedData.getData());
                }
            }
        });
    }

    private void initData() throws Resources.NotFoundException {
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
            getWeekTemp(pastStringArray.get(0), pastStringArray.get(0), pastStringArray.get(6));
            getMonthTemp(pastStringArray2.get(0), pastStringArray2.get(0), pastStringArray2.get(29));
            return;
        }
        this.mViewModel.getDayData(this.mToDay);
        new Thread(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.temperature.activity.TemperatureActivity.4
            @Override // java.lang.Runnable
            public void run() {
                TemperatureActivity.this.mViewModel.getPackedDayData(YearToDayListUtils.getPastStringArray(TemperatureActivity.this.mToDay, 6).get(0), 7);
                TemperatureActivity.this.mViewModel.getPackedDayData(YearToDayListUtils.getPastStringArray(TemperatureActivity.this.mToDay, 29).get(0), 30);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initViewPager() throws Resources.NotFoundException {
        if (isFinishing()) {
            return;
        }
        TempTabFragmentAdapter tempTabFragmentAdapter = new TempTabFragmentAdapter(getSupportFragmentManager(), new TempTabFragmentAdapter.FragmentCreator() { // from class: com.yucheng.smarthealthpro.home.activity.temperature.activity.TemperatureActivity.5
            @Override // com.yucheng.smarthealthpro.home.activity.temperature.adapter.TempTabFragmentAdapter.FragmentCreator
            public Fragment createFragment(String data, int position) {
                return TempTabFragment.newInstance(data.toString(), position, TemperatureActivity.this.mNestedScrollView, TemperatureActivity.this.monthLastDay, TemperatureActivity.this.mDayChartSumUpTemperatureHisListBean, TemperatureActivity.this.mWeekChartSumUpTemperatureHisListBean, TemperatureActivity.this.mMonthChartSumUpTemperatureHisListBean, TemperatureActivity.this.mDayMaxTempNum, TemperatureActivity.this.mToDay, TemperatureActivity.this.mTempUnit);
            }

            @Override // com.yucheng.smarthealthpro.home.activity.temperature.adapter.TempTabFragmentAdapter.FragmentCreator
            public String createTitle(String data) {
                return Html.fromHtml(data).toString();
            }
        });
        this.mAdapter = tempTabFragmentAdapter;
        this.mViewPager.setAdapter(tempTabFragmentAdapter);
        this.mAdapter.notifyDataSetChanged();
        this.mViewPager.setOffscreenPageLimit(this.mDayTemperatureHisListBean.size() - 1);
        this.mAdapter.setData(this.mTitles);
        this.mSlidingTabLayout.setViewPager(this.mViewPager, (String[]) this.mTitles.toArray(new String[0]));
        this.mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.yucheng.smarthealthpro.home.activity.temperature.activity.TemperatureActivity.6
            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int state) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int position) {
                if (position == 0) {
                    TemperatureActivity.this.mViewPager.setCurrentItem(0);
                    if (TemperatureActivity.this.mMonthAdapterTemperatureHisListBean == null || TemperatureActivity.this.mMonthAdapterTemperatureHisListBean.size() <= 0) {
                        TemperatureActivity.this.tvDataFirst.setText("--");
                        TemperatureActivity.this.tvDataSecond.setText("--");
                        TemperatureActivity.this.tvDataThirdly.setText("--");
                        TemperatureActivity.this.mMonthAverageTempNum = 0.0f;
                    } else {
                        TemperatureActivity.this.tvDataFirst.setText(FormatUtil.keep1NoZero(TemperatureActivity.this.mMonthAverageTempNum));
                        TemperatureActivity.this.tvDataSecond.setText(FormatUtil.keep1NoZero(TemperatureActivity.this.mMonthMaxTempNum));
                        TemperatureActivity.this.tvDataThirdly.setText(FormatUtil.keep1NoZero(TemperatureActivity.this.mMonthMinTempNum));
                    }
                    TemperatureActivity.this.tvBackToday.setVisibility(0);
                    TemperatureActivity.this.llCalendar.setVisibility(8);
                    TemperatureActivity.this.mTemperatureHisListAdapter.replaceData(TemperatureActivity.this.mMonthAdapterTemperatureHisListBean);
                    TemperatureActivity.this.mTemperatureHisListAdapter.notifyDataSetChanged();
                    TemperatureActivity temperatureActivity = TemperatureActivity.this;
                    temperatureActivity.dataAnalysis(temperatureActivity.mMonthAverageTempNum);
                    return;
                }
                if (position == 1) {
                    TemperatureActivity.this.mViewPager.setCurrentItem(1);
                    if (TemperatureActivity.this.mWeekAdapterTemperatureHisListBean == null || TemperatureActivity.this.mWeekAdapterTemperatureHisListBean.size() <= 0) {
                        TemperatureActivity.this.tvDataFirst.setText("--");
                        TemperatureActivity.this.tvDataSecond.setText("--");
                        TemperatureActivity.this.tvDataThirdly.setText("--");
                        TemperatureActivity.this.mWeekAverageTempNum = 0.0f;
                    } else {
                        TemperatureActivity.this.tvDataFirst.setText(FormatUtil.keep1NoZero(TemperatureActivity.this.mWeekAverageTempNum));
                        TemperatureActivity.this.tvDataSecond.setText(FormatUtil.keep1NoZero(TemperatureActivity.this.mWeekMaxTempNum));
                        TemperatureActivity.this.tvDataThirdly.setText(FormatUtil.keep1NoZero(TemperatureActivity.this.mWeekMinTempNum));
                    }
                    TemperatureActivity.this.tvBackToday.setVisibility(0);
                    TemperatureActivity.this.llCalendar.setVisibility(8);
                    TemperatureActivity.this.mTemperatureHisListAdapter.replaceData(TemperatureActivity.this.mWeekAdapterTemperatureHisListBean);
                    TemperatureActivity.this.mTemperatureHisListAdapter.notifyDataSetChanged();
                    TemperatureActivity temperatureActivity2 = TemperatureActivity.this;
                    temperatureActivity2.dataAnalysis(temperatureActivity2.mWeekAverageTempNum);
                    return;
                }
                if (position != 2) {
                    return;
                }
                TemperatureActivity.this.mViewPager.setCurrentItem(2);
                if (TemperatureActivity.this.mDayTemperatureHisListBean == null || TemperatureActivity.this.mDayTemperatureHisListBean.size() <= 0) {
                    TemperatureActivity.this.tvDataFirst.setText("--");
                    TemperatureActivity.this.tvDataSecond.setText("--");
                    TemperatureActivity.this.tvDataThirdly.setText("--");
                    TemperatureActivity.this.mDayAverageTempNum = 0.0f;
                } else {
                    TemperatureActivity.this.tvDataFirst.setText(FormatUtil.keep1NoZero(TemperatureActivity.this.mDayAverageTempNum));
                    TemperatureActivity.this.tvDataSecond.setText(FormatUtil.keep1NoZero(TemperatureActivity.this.mDayMaxTempNum));
                    TemperatureActivity.this.tvDataThirdly.setText(FormatUtil.keep1NoZero(TemperatureActivity.this.mDayMinTempNum));
                }
                TemperatureActivity.this.tvBackToday.setVisibility(8);
                TemperatureActivity.this.llCalendar.setVisibility(0);
                if (TemperatureActivity.this.mDayTemperatureHisListBean != null) {
                    TemperatureActivity.this.mTemperatureHisListAdapter.replaceData(TemperatureActivity.this.mDayTemperatureHisListBean);
                    TemperatureActivity.this.mTemperatureHisListAdapter.notifyDataSetChanged();
                } else {
                    TemperatureActivity.this.mDayTemperatureHisListBean = new ArrayList();
                    TemperatureActivity.this.mTemperatureHisListAdapter.replaceData(TemperatureActivity.this.mDayTemperatureHisListBean);
                    TemperatureActivity.this.mTemperatureHisListAdapter.notifyDataSetChanged();
                }
                TemperatureActivity temperatureActivity3 = TemperatureActivity.this;
                temperatureActivity3.dataAnalysis(temperatureActivity3.mDayAverageTempNum);
            }
        });
        this.mSlidingTabLayout.setCurrentTab(2, true);
    }

    public void dataAnalysis(float value) {
        if (Constant.isTechFeel()) {
            if (this.mTemperatureHisListAdapter.getData().size() > 0) {
                this.tvDataFirst.setText(FormatUtil.keep1NoZero(Float.valueOf(Float.parseFloat(this.mTemperatureHisListAdapter.getData().get(0).getmValue())).floatValue()));
            } else {
                this.tvDataFirst.setText("--");
            }
        }
        if (this.mTempUnit != 0) {
            value = (value - 32.0f) / 1.8f;
        }
        if (value > 38.0f) {
            this.tvAnalyseData.setText(getText(R.string.home_temperature_analyse_high_temperature));
            return;
        }
        if (value > 37.3d) {
            this.tvAnalyseData.setText(getText(R.string.home_temperature_analyse_mild_fever));
            return;
        }
        if (value > 36.0f) {
            this.tvAnalyseData.setText(getText(R.string.home_temperature_analyse_normal));
        } else if (value > 33.0f) {
            this.tvAnalyseData.setText(getText(R.string.home_temperature_analyse_low_fever));
        } else {
            this.tvAnalyseData.setText("");
        }
    }

    private void setRecycleView() {
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        TemperatureHisListAdapter temperatureHisListAdapter = new TemperatureHisListAdapter(R.layout.item_universal_his_list);
        this.mTemperatureHisListAdapter = temperatureHisListAdapter;
        temperatureHisListAdapter.addData((Collection) this.mDayTemperatureHisListBean);
        this.mRecyclerView.setAdapter(this.mTemperatureHisListAdapter);
        this.mRecyclerView.setNestedScrollingEnabled(false);
        this.mTemperatureHisListAdapter.setOnItemClickListener(new TemperatureHisListAdapter.OnItemClickListener() { // from class: com.yucheng.smarthealthpro.home.activity.temperature.activity.TemperatureActivity.7
            @Override // com.yucheng.smarthealthpro.home.activity.temperature.adapter.TemperatureHisListAdapter.OnItemClickListener
            public void onClick(TemperatureHisListBean hisSearch, int position) {
            }

            @Override // com.yucheng.smarthealthpro.home.activity.temperature.adapter.TemperatureHisListAdapter.OnItemClickListener
            public void onDelClick(TemperatureHisListBean hisSearch, int position) {
            }

            @Override // com.yucheng.smarthealthpro.home.activity.temperature.adapter.TemperatureHisListAdapter.OnItemClickListener
            public void onLongClick(TemperatureHisListBean hisSearch, int position) {
            }
        });
    }

    private void initMonth() throws Resources.NotFoundException {
        this.mCalendarView.setOnCalendarSelectListener(this);
        this.mCalendarView.setOnMonthChangeListener(this);
        this.mCalendarView.setOnCalendarInterceptListener(new CalendarView.OnCalendarInterceptListener() { // from class: com.yucheng.smarthealthpro.home.activity.temperature.activity.TemperatureActivity.8
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
        this.mCalendarView.scrollToCurrent();
    }

    private void updateCalendarTitle(String string) {
        try {
            this.tvYears.setText(String.valueOf(new SimpleDateFormat(getString(R.string.mm_yyyy)).parse(string)));
        } catch (ParseException e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.haibin.calendarview.CalendarView.OnCalendarSelectListener
    public void onCalendarSelect(com.haibin.calendarview.Calendar calendar, boolean isClick) {
        this.monthLastDay = YearToDayListUtils.getMonthLastDay(calendar.getYear(), calendar.getMonth());
        String strIntToStr = TimeDateUtil.intToStr(calendar.getYear(), calendar.getMonth(), calendar.getDay());
        if (this.isCare.booleanValue()) {
            getDayTemp(strIntToStr);
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
        List<TemperatureHisListBean> list = this.mDayTemperatureHisListBean;
        if (list != null) {
            list.clear();
        }
        this.mDayChartSumUpTemperatureHisListBean = new ArrayList();
        this.mAllDb = data;
        List<HealthMetric> listFilterTemperatureData = HealthDataFilterKt.filterTemperatureData(data);
        this.mAllDb = listFilterTemperatureData;
        this.mLastNum = 0.0f;
        this.mDaySumUpTempNum = 0.0f;
        this.mDayMaxTempNum = 0.0f;
        this.mDayMinTempNum = 107.6f;
        for (HealthMetric healthMetric : listFilterTemperatureData) {
            float temperature = healthMetric.getTemperature();
            if (this.mTempUnit != 0) {
                temperature = FormatUtil.getBigDecimal((temperature * 1.8d) + 32.0d).setScale(1, 4).floatValue();
            }
            if (this.mLastNum == 0.0f) {
                this.mLastNum = temperature;
            }
            if (temperature > this.mDayMaxTempNum) {
                this.mDayMaxTempNum = temperature;
            }
            if (temperature < this.mDayMinTempNum) {
                this.mDayMinTempNum = temperature;
            }
            this.mDaySumUpTempNum += temperature;
            this.mDayTemperatureHisListBean.add(new TemperatureHisListBean(TimeStampUtils.dateForStringToDate(TimeStampUtils.longStampForDate(healthMetric.getStartTimestamp())), temperature + "", "正常"));
            this.mDayChartSumUpTemperatureHisListBean.add(new TemperatureHisListBean(TimeStampUtils.dateForStringToDate(TimeStampUtils.longStampForDate(healthMetric.getStartTimestamp())), temperature + "", "正常"));
        }
        if (this.mDayTemperatureHisListBean.size() > 0) {
            this.mDayAverageTempNum = FormatUtil.getBigDecimal(this.mDaySumUpTempNum / this.mDayTemperatureHisListBean.size()).setScale(1, RoundingMode.HALF_UP).floatValue();
        }
        initViewPager();
    }

    public void onWeekData(List<HealthMetric> data) throws NumberFormatException {
        this.mWeekAdapterTemperatureHisListBean.clear();
        this.mWeekChartSumUpTemperatureHisListBean.clear();
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
        if (this.mWeekAdapterTemperatureHisListBean.size() != 0) {
            Collections.reverse(this.mWeekAdapterTemperatureHisListBean);
            this.mWeekSumUpTempNum = 0.0f;
            this.mWeekMaxTempNum = 0.0f;
            this.mWeekMinTempNum = 107.6f;
            for (int i3 = 0; i3 < this.mWeekAdapterTemperatureHisListBean.size(); i3++) {
                float f2 = Float.parseFloat(this.mWeekAdapterTemperatureHisListBean.get(i3).getmValue().replaceAll(",", "."));
                if (f2 > this.mWeekMaxTempNum) {
                    this.mWeekMaxTempNum = f2;
                }
                if (f2 < this.mWeekMinTempNum) {
                    this.mWeekMinTempNum = f2;
                }
                this.mWeekSumUpTempNum += Float.parseFloat(this.mWeekAdapterTemperatureHisListBean.get(i3).getmValue().replaceAll(",", "."));
            }
            try {
                this.mWeekAverageTempNum = FormatUtil.getBigDecimal(this.mWeekSumUpTempNum / this.mWeekAdapterTemperatureHisListBean.size()).setScale(1, RoundingMode.HALF_UP).floatValue();
            } catch (Exception e3) {
                BuglyLog.e("Temperature", e3.getMessage());
                CrashReport.postCatchedException(e3);
            }
        }
    }

    private void getWeekDay(String mThatVeryDay, int index) {
        float fFloatValue;
        this.mWeekSumUpTempNum = 0.0f;
        int i2 = 0;
        for (int i3 = 0; i3 < this.mAllDb.size(); i3++) {
            if (TimeStampUtils.dateForStringYearToDate(TimeStampUtils.longStampForDate(this.mAllDb.get(i3).getStartTimestamp())).equals(mThatVeryDay)) {
                int temperatureInteger = this.mAllDb.get(i3).getTemperatureInteger();
                int temperatureFraction = this.mAllDb.get(i3).getTemperatureFraction();
                if (temperatureInteger <= 42 && temperatureInteger >= 33 && temperatureFraction != 15) {
                    this.mWeekSumUpTempNum += Float.parseFloat(temperatureInteger + "." + temperatureFraction);
                    i2++;
                }
            }
        }
        if (i2 <= 0) {
            fFloatValue = 0.0f;
        } else if (this.mTempUnit == 0) {
            fFloatValue = this.mWeekSumUpTempNum / i2;
        } else {
            fFloatValue = FormatUtil.getBigDecimal(((this.mWeekSumUpTempNum / i2) * 1.8d) + 32.0d).setScale(1, RoundingMode.HALF_UP).floatValue();
        }
        float fKeep1F = FormatUtil.keep1F(fFloatValue);
        if (fFloatValue != 0.0f) {
            this.mWeekAdapterTemperatureHisListBean.add(new TemperatureHisListBean(TimeStampUtils.dateForStringDates(TimeStampUtils.stringForDateDay(mThatVeryDay)), fKeep1F + "", "正常"));
        }
        try {
            this.mWeekChartSumUpTemperatureHisListBean.add(index, new TemperatureHisListBean(TimeStampUtils.dateForStringDates(TimeStampUtils.stringForDateDay(mThatVeryDay)), fKeep1F + "", "正常"));
        } catch (Exception e2) {
            BuglyLog.e("TemperatureActivity", e2.getMessage());
            CrashReport.postCatchedException(e2);
        }
    }

    public void onMonthData(List<HealthMetric> data) throws NumberFormatException {
        this.mMonthAdapterTemperatureHisListBean.clear();
        this.mMonthChartSumUpTemperatureHisListBean.clear();
        if (this.mAllDb != null) {
            ArrayList<String> pastStringArray = YearToDayListUtils.getPastStringArray(this.mToDay, 29);
            if (pastStringArray.size() > 0) {
                this.mAllDb = data;
            }
            for (int i2 = 0; i2 < pastStringArray.size(); i2++) {
                getMonthDay(pastStringArray.get(i2), i2);
            }
        }
        if (this.mMonthAdapterTemperatureHisListBean.size() != 0) {
            Collections.reverse(this.mMonthAdapterTemperatureHisListBean);
            this.mMonthSumUpTempNum = 0.0f;
            this.mMonthMaxTempNum = 0.0f;
            this.mMonthMinTempNum = Float.parseFloat(this.mMonthAdapterTemperatureHisListBean.get(0).getmValue().replaceAll(",", "."));
            for (int i3 = 0; i3 < this.mMonthAdapterTemperatureHisListBean.size(); i3++) {
                float f2 = Float.parseFloat(this.mMonthAdapterTemperatureHisListBean.get(i3).getmValue().replaceAll(",", "."));
                if (f2 > this.mMonthMaxTempNum) {
                    this.mMonthMaxTempNum = f2;
                }
                if (f2 < this.mMonthMinTempNum) {
                    this.mMonthMinTempNum = f2;
                }
                this.mMonthSumUpTempNum += Float.parseFloat(this.mMonthAdapterTemperatureHisListBean.get(i3).getmValue().replaceAll(",", "."));
            }
            this.mMonthAverageTempNum = FormatUtil.getBigDecimal(this.mMonthSumUpTempNum / this.mMonthAdapterTemperatureHisListBean.size()).setScale(1, RoundingMode.HALF_UP).floatValue();
        }
        runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.temperature.activity.TemperatureActivity.9
            @Override // java.lang.Runnable
            public void run() throws Resources.NotFoundException {
                TemperatureActivity.this.initViewPager();
            }
        });
    }

    private void getMonthDay(String mThatVeryDay, int index) {
        float fFloatValue;
        this.mMonthSumUpTempNum = 0.0f;
        int i2 = 0;
        for (int i3 = 0; i3 < this.mAllDb.size(); i3++) {
            if (TimeStampUtils.dateForStringYearToDate(TimeStampUtils.longStampForDate(this.mAllDb.get(i3).getStartTimestamp())).equals(mThatVeryDay)) {
                int temperatureInteger = this.mAllDb.get(i3).getTemperatureInteger();
                int temperatureFraction = this.mAllDb.get(i3).getTemperatureFraction();
                if (temperatureInteger <= 42 && temperatureInteger >= 33 && temperatureFraction != 15) {
                    this.mMonthSumUpTempNum += Float.parseFloat(temperatureInteger + "." + temperatureFraction);
                    i2++;
                }
            }
        }
        if (i2 <= 0) {
            fFloatValue = 0.0f;
        } else if (this.mTempUnit == 0) {
            fFloatValue = this.mMonthSumUpTempNum / i2;
        } else {
            fFloatValue = FormatUtil.getBigDecimal(((this.mMonthSumUpTempNum / i2) * 1.8d) + 32.0d).setScale(1, RoundingMode.HALF_UP).floatValue();
        }
        if (fFloatValue > 0.0f) {
            this.mMonthAdapterTemperatureHisListBean.add(new TemperatureHisListBean(TimeStampUtils.dateForStringDates(TimeStampUtils.stringForDateDay(mThatVeryDay)), FormatUtil.getBigDecimal(fFloatValue).setScale(1, RoundingMode.HALF_UP).floatValue() + "", "正常"));
        }
        try {
            this.mMonthChartSumUpTemperatureHisListBean.add(index, new TemperatureHisListBean(TimeStampUtils.dateForStringDates(TimeStampUtils.stringForDateDay(mThatVeryDay)), fFloatValue + "", "正常"));
        } catch (Exception e2) {
            BuglyLog.e("TemperatureActivity", e2.getMessage());
            CrashReport.postCatchedException(e2);
        }
    }

    private void getDayTemp(String dateTime) {
        HashMap map = new HashMap();
        map.put("userId", getIntent().getExtras().getString(Constant.SpConstKey.DEV_ID));
        map.put("day", dateTime);
        HttpUtils.getInstance().postMsgAsynHttp(this, com.yucheng.smarthealthpro.framework.util.Constants.temperatureDayUrl, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.home.activity.temperature.activity.TemperatureActivity.10
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                if (result != null) {
                    TemperatureActivity.this.temp_bean = (FriendCareDataBean) new Gson().fromJson(result, FriendCareDataBean.class);
                    TemperatureActivity.this.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.temperature.activity.TemperatureActivity.10.1
                        @Override // java.lang.Runnable
                        public void run() throws Resources.NotFoundException {
                            TemperatureActivity.this.setDayData();
                            TemperatureActivity.this.initViewPager();
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
        List<TemperatureHisListBean> list = this.mDayTemperatureHisListBean;
        if (list != null) {
            list.clear();
        }
        List<TemperatureHisListBean> list2 = this.mDayChartSumUpTemperatureHisListBean;
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
                arrayList = (List) new Gson().fromJson(it2.next().mlist, new TypeToken<List<FriendCareTempBean>>() { // from class: com.yucheng.smarthealthpro.home.activity.temperature.activity.TemperatureActivity.11
                }.getType());
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        List<FriendCareTempBean> listSortCareTempRepeatData = Tools.sortCareTempRepeatData(Tools.removeCareTempRepeatData(arrayList));
        this.mDaySumUpTempNum = 0.0f;
        this.mDayMaxTempNum = 0.0f;
        this.mDayMinTempNum = 107.6f;
        this.mLastNum = 0.0f;
        for (FriendCareTempBean friendCareTempBean : listSortCareTempRepeatData) {
            float fFloatValue = friendCareTempBean.temperature;
            if (fFloatValue <= TransUtils.TEMPERATURE_VISIBLE_MAX && fFloatValue >= TransUtils.TEMPERATURE_VISIBLE_MIN && !String.format("%.2f", Float.valueOf(fFloatValue)).endsWith(".15")) {
                if (this.mTempUnit != 0) {
                    fFloatValue = FormatUtil.getBigDecimal((fFloatValue * 1.8d) + 32.0d).setScale(1, RoundingMode.HALF_UP).floatValue();
                }
                if (fFloatValue > this.mDayMaxTempNum) {
                    this.mDayMaxTempNum = fFloatValue;
                }
                if (fFloatValue < this.mDayMinTempNum) {
                    this.mDayMinTempNum = fFloatValue;
                }
                if (this.mLastNum == 0.0f) {
                    this.mLastNum = fFloatValue;
                }
                this.mDaySumUpTempNum += fFloatValue;
                this.mDayTemperatureHisListBean.add(new TemperatureHisListBean(TimeStampUtils.dateForStringToDate(TimeStampUtils.longStampForDate(friendCareTempBean.rtime)), fFloatValue + "", "正常"));
                this.mDayChartSumUpTemperatureHisListBean.add(new TemperatureHisListBean(TimeStampUtils.dateForStringToDate(TimeStampUtils.longStampForDate(friendCareTempBean.rtime)), fFloatValue + "", "正常"));
            }
        }
        if (this.mDayTemperatureHisListBean.size() != 0) {
            this.mDayAverageTempNum = FormatUtil.getBigDecimal(this.mDaySumUpTempNum / this.mDayTemperatureHisListBean.size()).setScale(1, RoundingMode.HALF_UP).floatValue();
        }
    }

    private void getWeekTemp(String dateTime, String startTime, String endTime) {
        HashMap map = new HashMap();
        map.put("userId", getIntent().getExtras().getString(Constant.SpConstKey.DEV_ID));
        map.put("day", dateTime);
        map.put("startDate", startTime);
        map.put("endDate", endTime);
        map.put("type", "1");
        HttpUtils.getInstance().postMsgAsynHttp(this, com.yucheng.smarthealthpro.framework.util.Constants.temperatureDayUrl, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.home.activity.temperature.activity.TemperatureActivity.12
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) throws NumberFormatException {
                CareTempWeekMonthBean careTempWeekMonthBean;
                if (result != null) {
                    try {
                        careTempWeekMonthBean = (CareTempWeekMonthBean) new Gson().fromJson(result, CareTempWeekMonthBean.class);
                    } catch (JsonSyntaxException e2) {
                        e2.printStackTrace();
                        careTempWeekMonthBean = null;
                    }
                    if (careTempWeekMonthBean == null || careTempWeekMonthBean.getData() == null) {
                        return;
                    }
                    List<CareTempWeekMonthBean.DataBean> data = careTempWeekMonthBean.getData();
                    TemperatureActivity.this.mWeekSumUpTempNum = 0.0f;
                    TemperatureActivity.this.mWeekMaxTempNum = 0.0f;
                    TemperatureActivity.this.mWeekMinTempNum = 107.6f;
                    TemperatureActivity.this.mLastNum = 0.0f;
                    Iterator<CareTempWeekMonthBean.DataBean> it2 = data.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            break;
                        }
                        CareTempWeekMonthBean.DataBean next = it2.next();
                        float fFloatValue = Float.parseFloat(next.getTemperatureMean().isEmpty() ? "0" : next.getTemperatureMean().replaceAll(",", "."));
                        if (fFloatValue <= 42.0f && fFloatValue >= 33.0f && !String.format("%.2f", Float.valueOf(fFloatValue)).endsWith(".15")) {
                            if (TemperatureActivity.this.mTempUnit == 1) {
                                fFloatValue = FormatUtil.getBigDecimal((fFloatValue * 1.8d) + 32.0d).setScale(1, RoundingMode.HALF_UP).floatValue();
                            }
                            if (fFloatValue > TemperatureActivity.this.mWeekMaxTempNum) {
                                TemperatureActivity.this.mWeekMaxTempNum = fFloatValue;
                            }
                            if (fFloatValue < TemperatureActivity.this.mWeekMinTempNum) {
                                TemperatureActivity.this.mWeekMinTempNum = fFloatValue;
                            }
                            if (TemperatureActivity.this.mLastNum == 0.0f) {
                                TemperatureActivity.this.mLastNum = fFloatValue;
                            }
                            TemperatureActivity.this.mWeekSumUpTempNum += fFloatValue;
                            TemperatureActivity.this.mWeekAdapterTemperatureHisListBean.add(new TemperatureHisListBean(TimeStampUtils.dateForStringDates(TimeStampUtils.stringForDateDay(next.getDateformat())), FormatUtil.getBigDecimal(fFloatValue).setScale(1, RoundingMode.HALF_UP).toString(), "正常"));
                        }
                    }
                    if (TemperatureActivity.this.mWeekAdapterTemperatureHisListBean.size() > 0) {
                        TemperatureActivity temperatureActivity = TemperatureActivity.this;
                        temperatureActivity.mWeekAverageTempNum = FormatUtil.getBigDecimal(temperatureActivity.mWeekSumUpTempNum / TemperatureActivity.this.mWeekAdapterTemperatureHisListBean.size()).setScale(1, RoundingMode.HALF_UP).floatValue();
                    }
                    ArrayList<String> pastByMonthDayArray = YearToDayListUtils.getPastByMonthDayArray(TemperatureActivity.this.mToDay, 6);
                    for (int i2 = 0; i2 < pastByMonthDayArray.size(); i2++) {
                        TemperatureActivity.this.mWeekChartSumUpTemperatureHisListBean.add(new TemperatureHisListBean(pastByMonthDayArray.get(i2), "0", "正常"));
                    }
                    for (int i3 = 0; i3 < pastByMonthDayArray.size(); i3++) {
                        for (TemperatureHisListBean temperatureHisListBean : TemperatureActivity.this.mWeekAdapterTemperatureHisListBean) {
                            if (pastByMonthDayArray.get(i3).equals(temperatureHisListBean.getTime())) {
                                TemperatureActivity.this.mWeekChartSumUpTemperatureHisListBean.remove(i3);
                                TemperatureActivity.this.mWeekChartSumUpTemperatureHisListBean.add(i3, new TemperatureHisListBean(temperatureHisListBean.getTime(), temperatureHisListBean.getmValue(), "正常"));
                            }
                        }
                    }
                }
            }
        });
    }

    private void getMonthTemp(String dateTime, String startTime, String endTime) {
        HashMap map = new HashMap();
        map.put("userId", getIntent().getExtras().getString(Constant.SpConstKey.DEV_ID));
        map.put("day", dateTime);
        map.put("startDate", startTime);
        map.put("endDate", endTime);
        map.put("type", "1");
        HttpUtils.getInstance().postMsgAsynHttp(this, com.yucheng.smarthealthpro.framework.util.Constants.temperatureDayUrl, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.home.activity.temperature.activity.TemperatureActivity.13
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) throws NumberFormatException {
                CareTempWeekMonthBean careTempWeekMonthBean;
                if (result != null) {
                    try {
                        careTempWeekMonthBean = (CareTempWeekMonthBean) new Gson().fromJson(result, CareTempWeekMonthBean.class);
                    } catch (JsonSyntaxException e2) {
                        e2.printStackTrace();
                        careTempWeekMonthBean = null;
                    }
                    if (careTempWeekMonthBean == null || careTempWeekMonthBean.getData() == null) {
                        return;
                    }
                    List<CareTempWeekMonthBean.DataBean> data = careTempWeekMonthBean.getData();
                    TemperatureActivity.this.mMonthSumUpTempNum = 0.0f;
                    TemperatureActivity.this.mMonthMaxTempNum = 0.0f;
                    TemperatureActivity.this.mMonthMinTempNum = 107.6f;
                    TemperatureActivity.this.mLastNum = 0.0f;
                    Iterator<CareTempWeekMonthBean.DataBean> it2 = data.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            break;
                        }
                        CareTempWeekMonthBean.DataBean next = it2.next();
                        float fFloatValue = Float.parseFloat(next.getTemperatureMean().isEmpty() ? "0" : next.getTemperatureMean().replaceAll(",", "."));
                        if (fFloatValue <= 42.0f && fFloatValue >= 33.0f && !String.format("%.2f", Float.valueOf(fFloatValue)).endsWith(".15")) {
                            if (TemperatureActivity.this.mTempUnit == 1) {
                                fFloatValue = FormatUtil.getBigDecimal((fFloatValue * 1.8d) + 32.0d).setScale(1, RoundingMode.HALF_UP).floatValue();
                            }
                            if (fFloatValue > TemperatureActivity.this.mMonthMaxTempNum) {
                                TemperatureActivity.this.mMonthMaxTempNum = fFloatValue;
                            }
                            if (fFloatValue < TemperatureActivity.this.mMonthMinTempNum) {
                                TemperatureActivity.this.mMonthMinTempNum = fFloatValue;
                            }
                            if (TemperatureActivity.this.mLastNum == 0.0f) {
                                TemperatureActivity.this.mLastNum = fFloatValue;
                            }
                            TemperatureActivity.this.mMonthSumUpTempNum += fFloatValue;
                            TemperatureActivity.this.mMonthAdapterTemperatureHisListBean.add(new TemperatureHisListBean(TimeStampUtils.dateForStringDates(TimeStampUtils.stringForDateDay(next.getDateformat())), FormatUtil.getBigDecimal(fFloatValue).setScale(1, RoundingMode.HALF_UP).toString(), "正常"));
                        }
                    }
                    if (TemperatureActivity.this.mMonthAdapterTemperatureHisListBean.size() > 0) {
                        TemperatureActivity temperatureActivity = TemperatureActivity.this;
                        temperatureActivity.mMonthAverageTempNum = FormatUtil.getBigDecimal(temperatureActivity.mMonthSumUpTempNum / TemperatureActivity.this.mMonthAdapterTemperatureHisListBean.size()).setScale(1, RoundingMode.HALF_UP).floatValue();
                    }
                    ArrayList<String> pastByMonthDayArray = YearToDayListUtils.getPastByMonthDayArray(TemperatureActivity.this.mToDay, 29);
                    for (int i2 = 0; i2 < pastByMonthDayArray.size(); i2++) {
                        TemperatureActivity.this.mMonthChartSumUpTemperatureHisListBean.add(new TemperatureHisListBean(pastByMonthDayArray.get(i2), "0", "正常"));
                    }
                    for (int i3 = 0; i3 < pastByMonthDayArray.size(); i3++) {
                        for (TemperatureHisListBean temperatureHisListBean : TemperatureActivity.this.mMonthAdapterTemperatureHisListBean) {
                            if (pastByMonthDayArray.get(i3).equals(temperatureHisListBean.getTime())) {
                                TemperatureActivity.this.mMonthChartSumUpTemperatureHisListBean.remove(i3);
                                TemperatureActivity.this.mMonthChartSumUpTemperatureHisListBean.add(i3, new TemperatureHisListBean(temperatureHisListBean.getTime(), temperatureHisListBean.getmValue(), "正常"));
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
            Boolean bool = (Boolean) SharedPreferencesUtils.get(getActivity(), Constant.SpConstKey.NotShowMeasureTip, false);
            if (Constant.isHealthWear() || bool.booleanValue()) {
                if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASTESTTEMP)) {
                    launchActivityForResult(TemperatureMeasureNormalActivity.TEMPERATURE_MEASURE_NORMAL, TemperatureMeasureNormalActivity.class);
                    return;
                } else {
                    if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASTEMPAXILLARYTEST)) {
                        launchActivityForResult(TemperatureMeasureActivity.TEMPERATURE_MEASURE, TemperatureMeasureActivity.class);
                        return;
                    }
                    return;
                }
            }
            launchActivityForResult(MeasureTipActivity.MEASURE_TIP, MeasureTipActivity.class);
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
                this.mNestedScrollView.postDelayed(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.temperature.activity.TemperatureActivity.14
                    @Override // java.lang.Runnable
                    public void run() {
                        TemperatureActivity.this.mNestedScrollView.smoothScrollTo(0, (int) (TemperatureActivity.this.mNestedScrollView.getScrollY() + (DpUtil.dp2px(TemperatureActivity.this.context, 56.0f) * 1.5f)));
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
