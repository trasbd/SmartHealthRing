package com.yucheng.smarthealthpro.home.activity.running.activity;

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
import com.haibin.calendarview.CalendarView;
import com.orhanobut.logger.Logger;
import com.realsil.sdk.dfu.DfuConstants;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.care.bean.CareStepWeekMonthBean;
import com.yucheng.smarthealthpro.care.bean.HistorySportResponse;
import com.yucheng.smarthealthpro.data.packed.HealthDayData;
import com.yucheng.smarthealthpro.data.packed.HealthPackedData;
import com.yucheng.smarthealthpro.database.room.bean.Step;
import com.yucheng.smarthealthpro.databinding.ActivityRunningBinding;
import com.yucheng.smarthealthpro.framework.http.HttpUtils;
import com.yucheng.smarthealthpro.framework.util.Constants;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import com.yucheng.smarthealthpro.home.activity.HealthyActivity;
import com.yucheng.smarthealthpro.home.activity.running.adapter.RunningHisListAdapter;
import com.yucheng.smarthealthpro.home.activity.running.adapter.StepTabFragmentAdapter;
import com.yucheng.smarthealthpro.home.activity.running.bean.RunningHisListBean;
import com.yucheng.smarthealthpro.home.activity.running.fragment.StepTabFragment;
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
import com.yucheng.smarthealthpro.utils.TransUtils;
import com.yucheng.smarthealthpro.utils.YearToDayListUtils;
import com.yucheng.smarthealthpro.viewmodel.StepViewModel;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class RunningActivity extends BaseVbActivity<ActivityRunningBinding> implements CalendarView.OnCalendarSelectListener, CalendarView.OnCalendarLongClickListener, CalendarView.OnYearChangeListener {
    ImageView ivCalendar;
    ImageView ivFirstLeft;
    ImageView ivFirstRight;
    ImageView ivFourthlyLeft;
    ImageView ivFourthlyRight;
    ImageView ivSecondLeft;
    ImageView ivSecondRight;
    LinearLayout llCalendar;
    LinearLayout llMonth;
    LinearLayout llStartButton;
    private StepTabFragmentAdapter mAdapter;
    private Calendar mCalendar;
    CalendarView mCalendarView;
    private List<RunningHisListBean> mDayChartSumUpRunningHisListBean;
    private List<RunningHisListBean> mDayRunningHisListBean;
    private List<RunningHisListBean> mMonthChartSumUpRunningHisListBean;
    private int mMonthMaxStepNum;
    private List<RunningHisListBean> mMonthRunningHisListBean;
    private int mMonthSumUpCalorieNum;
    private int mMonthSumUpDistanceNum;
    private int mMonthSumUpStepNum;
    NestedScrollView mNestedScrollView;
    RecyclerView mRecyclerView;
    private RunningHisListAdapter mRunningHisListAdapter;
    SlidingTabLayout mSlidingTabLayout;
    private List<Step> mStepDp;
    private int mSumUpStepNum;
    private String mToDay;
    private StepViewModel mViewModel;
    NoScrollViewPager mViewPager;
    private List<RunningHisListBean> mWeekChartSumUpRunningHisListBean;
    private int mWeekMaxStepNum;
    private List<RunningHisListBean> mWeekRunningHisListBean;
    private int mWeekSumUpCalorieNum;
    private int mWeekSumUpDistanceNum;
    private int mWeekSumUpStepNum;
    private int monthLastDay;
    RelativeLayout rlAnalyse;
    RelativeLayout rlFirst;
    RelativeLayout rlFourthly;
    RelativeLayout rlSecond;
    private HistorySportResponse temp_bean;
    TextView tvAnalyse;
    TextView tvAnalyseData;
    TextView tvBackToday;
    TextView tvCalendar;
    TextView tvFirst;
    TextView tvFourthly;
    TextView tvSecond;
    TextView tvStartButton;
    TextView tvStepNumber;
    TextView tvYears;
    private int ARROW = 0;
    private List<String> mTitles = new ArrayList();
    private List<Integer> mId = new ArrayList();
    private int MONTH = 0;
    private String mThatVeryDay = "";
    private int mMaxStepNum = 300;
    private int mMovingObject = DfuConstants.MAX_NOTIFICATION_LOCK_WAIT_TIME;
    private Boolean isCare = false;

    @Override // com.haibin.calendarview.CalendarView.OnCalendarLongClickListener
    public void onCalendarLongClick(com.haibin.calendarview.Calendar calendar) {
    }

    @Override // com.haibin.calendarview.CalendarView.OnCalendarLongClickListener
    public void onCalendarLongClickOutOfRange(com.haibin.calendarview.Calendar calendar) {
    }

    @Override // com.haibin.calendarview.CalendarView.OnCalendarSelectListener
    public void onCalendarOutOfRange(com.haibin.calendarview.Calendar calendar) {
    }

    @Override // com.haibin.calendarview.CalendarView.OnYearChangeListener
    public void onYearChange(int year) {
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) throws Resources.NotFoundException {
        super.onCreate(savedInstanceState);
        initView();
        initViewModel();
        initData();
    }

    private void initView() {
        this.mSlidingTabLayout = ((ActivityRunningBinding) this.mBinding).includeItemTop.stlTab;
        this.ivCalendar = ((ActivityRunningBinding) this.mBinding).includeItemTop.ivCalendar;
        this.tvCalendar = ((ActivityRunningBinding) this.mBinding).includeItemTop.tvCalendar;
        this.tvBackToday = ((ActivityRunningBinding) this.mBinding).includeItemTop.tvBackToday;
        this.llCalendar = ((ActivityRunningBinding) this.mBinding).includeItemTop.llCalendar;
        this.mViewPager = ((ActivityRunningBinding) this.mBinding).includeItemTop.vpTab;
        this.tvStartButton = ((ActivityRunningBinding) this.mBinding).includeItemBottom.tvStartButton;
        this.llStartButton = ((ActivityRunningBinding) this.mBinding).includeItemBottom.llStartButton;
        this.tvAnalyse = ((ActivityRunningBinding) this.mBinding).includeItemBottom.tvAnalyse;
        this.tvAnalyseData = ((ActivityRunningBinding) this.mBinding).includeItemBottom.tvAnalyseData;
        this.rlAnalyse = ((ActivityRunningBinding) this.mBinding).includeItemBottom.rlAnalyse;
        this.ivFirstLeft = ((ActivityRunningBinding) this.mBinding).includeItemBottom.ivFirstLeft;
        this.tvFirst = ((ActivityRunningBinding) this.mBinding).includeItemBottom.tvFirst;
        this.ivFirstRight = ((ActivityRunningBinding) this.mBinding).includeItemBottom.ivFirstRight;
        this.rlFirst = ((ActivityRunningBinding) this.mBinding).includeItemBottom.rlFirst;
        this.ivSecondLeft = ((ActivityRunningBinding) this.mBinding).includeItemBottom.ivSecondLeft;
        this.tvSecond = ((ActivityRunningBinding) this.mBinding).includeItemBottom.tvSecond;
        this.ivSecondRight = ((ActivityRunningBinding) this.mBinding).includeItemBottom.ivSecondRight;
        this.rlSecond = ((ActivityRunningBinding) this.mBinding).includeItemBottom.rlSecond;
        this.ivFourthlyLeft = ((ActivityRunningBinding) this.mBinding).includeItemBottom.ivFourthlyLeft;
        this.tvFourthly = ((ActivityRunningBinding) this.mBinding).includeItemBottom.tvFourthly;
        this.ivFourthlyRight = ((ActivityRunningBinding) this.mBinding).includeItemBottom.ivFourthlyRight;
        this.rlFourthly = ((ActivityRunningBinding) this.mBinding).includeItemBottom.rlFourthly;
        this.mRecyclerView = ((ActivityRunningBinding) this.mBinding).includeItemBottom.recycleView;
        this.mNestedScrollView = ((ActivityRunningBinding) this.mBinding).nsv;
        this.tvYears = ((ActivityRunningBinding) this.mBinding).includeItemCalendar.tvYears;
        this.mCalendarView = ((ActivityRunningBinding) this.mBinding).includeItemCalendar.calendarView;
        this.llMonth = ((ActivityRunningBinding) this.mBinding).includeItemCalendar.llMonth;
        this.tvStepNumber = ((ActivityRunningBinding) this.mBinding).tvStepNumber;
        this.llCalendar.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.running.activity.RunningActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.tvBackToday.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.running.activity.RunningActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.llStartButton.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.running.activity.RunningActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlAnalyse.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.running.activity.RunningActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlFirst.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.running.activity.RunningActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlSecond.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.running.activity.RunningActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlFourthly.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.running.activity.RunningActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.llMonth.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.running.activity.RunningActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        changeTitle(getString(R.string.step_number));
        showBack();
        showRightImage(R.mipmap.topbar_ic_share, new NavigationBar.MyOnClickListener() { // from class: com.yucheng.smarthealthpro.home.activity.running.activity.RunningActivity.1
            @Override // com.yucheng.smarthealthpro.framework.view.NavigationBar.MyOnClickListener
            public void onClick(View btn) {
                if (RunningActivity.this.checkCanClick()) {
                    ShareUtils.share(RunningActivity.this);
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
            this.tvAnalyse.setText(getString(R.string.include_bottom_tv_analyse_button));
            this.llStartButton.setVisibility(8);
            this.rlFirst.setVisibility(8);
            this.rlSecond.setVisibility(8);
            this.tvFourthly.setText(getString(R.string.include_bottom_tv_fourthly_button));
            this.ivFourthlyRight.setBackground(ResourcesCompat.getDrawable(getResources(), R.mipmap.list_ic_arrow_n, null));
        } else {
            this.llStartButton.setVisibility(8);
            this.tvAnalyse.setText(getString(R.string.include_bottom_tv_analyse_button));
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
    }

    private void initViewModel() {
        this.mViewModel = (StepViewModel) new ViewModelProvider(this).get(StepViewModel.class);
        FlowUtils.INSTANCE.collectFlow(this, this.mViewModel.getStepDataFlow(), new FlowUtils.FlowCollector<HealthDayData<Step>>() { // from class: com.yucheng.smarthealthpro.home.activity.running.activity.RunningActivity.2
            @Override // com.yucheng.smarthealthpro.utils.FlowUtils.FlowCollector
            public void collect(HealthDayData<Step> data) throws Resources.NotFoundException {
                RunningActivity.this.onThatVeryDayData(data.getDay(), data.getData());
            }
        });
        FlowUtils.INSTANCE.collectFlow(this, this.mViewModel.getStepPackedDataFlow(), new FlowUtils.FlowCollector<HealthPackedData<Step>>() { // from class: com.yucheng.smarthealthpro.home.activity.running.activity.RunningActivity.3
            @Override // com.yucheng.smarthealthpro.utils.FlowUtils.FlowCollector
            public void collect(HealthPackedData<Step> packedData) {
                if (packedData.getDayCount() == 7) {
                    RunningActivity.this.onWeekData(packedData.getData());
                } else if (packedData.getDayCount() == 30) {
                    RunningActivity.this.onMonthData(packedData.getData());
                }
            }
        });
    }

    private void initData() throws Resources.NotFoundException {
        this.mTitles.clear();
        this.mTitles.add(getString(R.string.date_month_unit));
        this.mTitles.add(getString(R.string.date_week_unit));
        this.mTitles.add(getString(R.string.date_day_unit));
        this.mDayRunningHisListBean = new ArrayList();
        this.mDayChartSumUpRunningHisListBean = new ArrayList();
        this.mWeekRunningHisListBean = new ArrayList();
        this.mWeekChartSumUpRunningHisListBean = new ArrayList();
        this.mMonthRunningHisListBean = new ArrayList();
        this.mMonthChartSumUpRunningHisListBean = new ArrayList();
        if (this.isCare.booleanValue()) {
            ArrayList<String> pastStringArray = YearToDayListUtils.getPastStringArray(this.mToDay, 6);
            ArrayList<String> pastStringArray2 = YearToDayListUtils.getPastStringArray(this.mToDay, 29);
            getWeekStep(pastStringArray.get(0), pastStringArray.get(0), pastStringArray.get(6));
            getMonthStep(pastStringArray2.get(0), pastStringArray2.get(0), pastStringArray2.get(29));
            setRecycleView();
            initViewPager();
            initMonth();
            return;
        }
        this.mDayChartSumUpRunningHisListBean = new ArrayList();
        this.mWeekChartSumUpRunningHisListBean = new ArrayList();
        this.mMonthChartSumUpRunningHisListBean = new ArrayList();
        this.mViewModel.getDayData(this.mToDay);
        setRecycleView();
        initViewPager();
        initMonth();
        new Thread(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.running.activity.RunningActivity.4
            @Override // java.lang.Runnable
            public void run() {
                RunningActivity.this.mViewModel.getPackedDayData(YearToDayListUtils.getPastStringArray(RunningActivity.this.mToDay, 6).get(0), 7);
                RunningActivity.this.mViewModel.getPackedDayData(YearToDayListUtils.getPastStringArray(RunningActivity.this.mToDay, 29).get(0), 30);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initViewPager() throws Resources.NotFoundException {
        if (isFinishing()) {
            return;
        }
        StepTabFragmentAdapter stepTabFragmentAdapter = new StepTabFragmentAdapter(getSupportFragmentManager(), new StepTabFragmentAdapter.FragmentCreator() { // from class: com.yucheng.smarthealthpro.home.activity.running.activity.RunningActivity.5
            @Override // com.yucheng.smarthealthpro.home.activity.running.adapter.StepTabFragmentAdapter.FragmentCreator
            public Fragment createFragment(String data, int position) {
                return StepTabFragment.newInstance(data.toString(), position, RunningActivity.this.mNestedScrollView, RunningActivity.this.monthLastDay, RunningActivity.this.mDayChartSumUpRunningHisListBean, RunningActivity.this.mWeekChartSumUpRunningHisListBean, RunningActivity.this.mMonthChartSumUpRunningHisListBean, RunningActivity.this.mMaxStepNum, RunningActivity.this.mThatVeryDay);
            }

            @Override // com.yucheng.smarthealthpro.home.activity.running.adapter.StepTabFragmentAdapter.FragmentCreator
            public String createTitle(String data) {
                return Html.fromHtml(data).toString();
            }
        });
        this.mAdapter = stepTabFragmentAdapter;
        this.mViewPager.setAdapter(stepTabFragmentAdapter);
        this.mAdapter.notifyDataSetChanged();
        this.mViewPager.setOffscreenPageLimit(this.mDayRunningHisListBean.size() - 1);
        this.mAdapter.setData(this.mTitles);
        this.mSlidingTabLayout.setViewPager(this.mViewPager, (String[]) this.mTitles.toArray(new String[0]));
        this.mSlidingTabLayout.setCurrentTab(2, true);
        this.mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.yucheng.smarthealthpro.home.activity.running.activity.RunningActivity.6
            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int state) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int position) {
                if (position == 0) {
                    RunningActivity.this.tvBackToday.setVisibility(8);
                    RunningActivity.this.llCalendar.setVisibility(0);
                    if (RunningActivity.this.mMonthChartSumUpRunningHisListBean != null && RunningActivity.this.mMonthChartSumUpRunningHisListBean.size() > 0) {
                        RunningActivity.this.tvStepNumber.setText(RunningActivity.this.mMonthMaxStepNum + "");
                        RunningActivity.this.mRunningHisListAdapter.replaceData(RunningActivity.this.mMonthChartSumUpRunningHisListBean);
                        RunningActivity.this.mRunningHisListAdapter.notifyDataSetChanged();
                        RunningActivity.this.dataAnalysis(Math.round((r7.mMonthMaxStepNum * 1.0f) / RunningActivity.this.mMonthChartSumUpRunningHisListBean.size()));
                        return;
                    }
                    RunningActivity.this.tvStepNumber.setText("--");
                    RunningActivity.this.mMonthMaxStepNum = 0;
                    RunningActivity.this.dataAnalysis(0);
                    return;
                }
                if (position == 1) {
                    RunningActivity.this.tvBackToday.setVisibility(8);
                    RunningActivity.this.llCalendar.setVisibility(0);
                    if (RunningActivity.this.mWeekChartSumUpRunningHisListBean != null && RunningActivity.this.mWeekChartSumUpRunningHisListBean.size() > 0) {
                        RunningActivity.this.tvStepNumber.setText(RunningActivity.this.mWeekMaxStepNum + "");
                        RunningActivity.this.mRunningHisListAdapter.replaceData(RunningActivity.this.mWeekChartSumUpRunningHisListBean);
                        RunningActivity.this.mRunningHisListAdapter.notifyDataSetChanged();
                        RunningActivity.this.dataAnalysis(Math.round((r7.mWeekMaxStepNum * 1.0f) / RunningActivity.this.mWeekChartSumUpRunningHisListBean.size()));
                        return;
                    }
                    RunningActivity.this.tvStepNumber.setText("--");
                    RunningActivity.this.mWeekMaxStepNum = 0;
                    RunningActivity.this.dataAnalysis(0);
                    return;
                }
                if (position != 2) {
                    return;
                }
                RunningActivity.this.tvBackToday.setVisibility(8);
                RunningActivity.this.llCalendar.setVisibility(0);
                if (RunningActivity.this.mDayRunningHisListBean != null && RunningActivity.this.mDayRunningHisListBean.size() > 0) {
                    RunningActivity.this.tvStepNumber.setText(RunningActivity.this.mSumUpStepNum + "");
                    RunningActivity.this.mRunningHisListAdapter.replaceData(RunningActivity.this.mDayRunningHisListBean);
                    RunningActivity.this.mRunningHisListAdapter.notifyDataSetChanged();
                    RunningActivity runningActivity = RunningActivity.this;
                    runningActivity.dataAnalysis(runningActivity.mSumUpStepNum);
                    return;
                }
                RunningActivity.this.tvStepNumber.setText("--");
                RunningActivity.this.mSumUpStepNum = 0;
                RunningActivity.this.dataAnalysis(0);
                if (RunningActivity.this.mDayRunningHisListBean != null) {
                    RunningActivity.this.mRunningHisListAdapter.replaceData(RunningActivity.this.mDayRunningHisListBean);
                } else {
                    RunningActivity.this.mDayRunningHisListBean = new ArrayList();
                    RunningActivity.this.mRunningHisListAdapter.replaceData(RunningActivity.this.mDayRunningHisListBean);
                }
                RunningActivity.this.mRunningHisListAdapter.notifyDataSetChanged();
            }
        });
    }

    public void dataAnalysis(int value) {
        int iIntValue = ((Integer) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.TARGET_NUMBER_OF_MOVEMENT_STEPS, Integer.valueOf(DfuConstants.MAX_NOTIFICATION_LOCK_WAIT_TIME))).intValue();
        this.mMovingObject = iIntValue;
        if (value > 100000) {
            this.tvAnalyseData.setText(getText(R.string.home_running_too_many_steps));
            return;
        }
        if (value > iIntValue) {
            this.tvAnalyseData.setText(getText(R.string.home_running_achieve_the_goal));
            return;
        }
        if (value > 1000) {
            this.tvAnalyseData.setText(getText(R.string.home_running_normal));
        } else if (value > 0) {
            this.tvAnalyseData.setText(getText(R.string.home_running_too_few_steps));
        } else {
            this.tvAnalyseData.setText("");
        }
    }

    private void setRecycleView() {
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        RunningHisListAdapter runningHisListAdapter = new RunningHisListAdapter(R.layout.item_running_his_list);
        this.mRunningHisListAdapter = runningHisListAdapter;
        runningHisListAdapter.addData(this.mDayRunningHisListBean);
        this.mRecyclerView.setAdapter(this.mRunningHisListAdapter);
        this.mRecyclerView.setNestedScrollingEnabled(true);
        this.mRunningHisListAdapter.setOnItemClickListener(new RunningHisListAdapter.OnItemClickListener() { // from class: com.yucheng.smarthealthpro.home.activity.running.activity.RunningActivity.7
            @Override // com.yucheng.smarthealthpro.home.activity.running.adapter.RunningHisListAdapter.OnItemClickListener
            public void onClick(RunningHisListBean hisSearch, int position) {
            }

            @Override // com.yucheng.smarthealthpro.home.activity.running.adapter.RunningHisListAdapter.OnItemClickListener
            public void onDelClick(RunningHisListBean hisSearch, int position) {
            }

            @Override // com.yucheng.smarthealthpro.home.activity.running.adapter.RunningHisListAdapter.OnItemClickListener
            public void onLongClick(RunningHisListBean hisSearch, int position) {
            }
        });
    }

    private void initMonth() throws Resources.NotFoundException {
        this.mCalendarView.setOnCalendarSelectListener(this);
        this.mCalendarView.setOnYearChangeListener(this);
        this.mCalendarView.setOnCalendarLongClickListener(this, false);
        this.mCalendarView.scrollToCurrent();
        this.mCalendarView.setOnCalendarInterceptListener(new CalendarView.OnCalendarInterceptListener() { // from class: com.yucheng.smarthealthpro.home.activity.running.activity.RunningActivity.8
            @Override // com.haibin.calendarview.CalendarView.OnCalendarInterceptListener
            public void onCalendarInterceptClick(com.haibin.calendarview.Calendar calendar, boolean isClick) {
            }

            @Override // com.haibin.calendarview.CalendarView.OnCalendarInterceptListener
            public boolean onCalendarIntercept(com.haibin.calendarview.Calendar calendar) {
                Date date;
                String str = calendar.getYear() + "-" + calendar.getMonth() + "-" + calendar.getDay();
                try {
                    date = new SimpleDateFormat(AppDateMgr.DF_YYYY_MM_DD, Locale.ENGLISH).parse(RunningActivity.this.mToDay);
                } catch (ParseException e2) {
                    e2.printStackTrace();
                    date = null;
                }
                return AppDateMgr.dateIsBeforeDay(str, RunningActivity.this.mToDay) || !AppDateMgr.dateIsBeforeDay(str, YearToDayListUtils.getPastDateString(30, date));
            }
        });
        this.tvYears.setText(this.mCalendarView.getCurYear() + "/" + String.format(Locale.getDefault(), TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(this.mCalendarView.getCurMonth())));
    }

    private com.haibin.calendarview.Calendar getSchemeCalendar(int year, int month, int day, int color, String text) {
        com.haibin.calendarview.Calendar calendar = new com.haibin.calendarview.Calendar();
        calendar.setYear(year);
        calendar.setMonth(month);
        calendar.setDay(day);
        calendar.setSchemeColor(color);
        calendar.setScheme(text);
        return calendar;
    }

    @Override // com.haibin.calendarview.CalendarView.OnCalendarSelectListener
    public void onCalendarSelect(com.haibin.calendarview.Calendar calendar, boolean isClick) {
        this.monthLastDay = YearToDayListUtils.getMonthLastDay(calendar.getYear(), calendar.getMonth());
        this.tvYears.setText(calendar.getYear() + "/" + calendar.getMonth());
        String strIntToStr = TimeDateUtil.intToStr(calendar.getYear(), calendar.getMonth(), calendar.getDay());
        if (this.isCare.booleanValue()) {
            getDayStep(strIntToStr);
        } else {
            this.mViewModel.getDayData(strIntToStr);
        }
        this.tvCalendar.setText(calendar.getMonth() + "/" + calendar.getDay());
        this.llMonth.setVisibility(8);
        this.MONTH = 0;
    }

    public void onThatVeryDayData(String thatVeryDay, List<Step> data) throws Resources.NotFoundException {
        String str = thatVeryDay;
        List<RunningHisListBean> list = this.mDayRunningHisListBean;
        if (list != null) {
            list.clear();
        }
        this.mDayChartSumUpRunningHisListBean = new ArrayList();
        this.mStepDp = data;
        if (data != null) {
            int i2 = 0;
            while (i2 < this.mStepDp.size()) {
                Logger.d("chong-------date==" + TimeStampUtils.dateForStringYearToDate(TimeStampUtils.longStampForDate(this.mStepDp.get(i2).getStartTime())));
                if (TimeStampUtils.dateForStringYearToDate(TimeStampUtils.longStampForDate(this.mStepDp.get(i2).getStartTime())).equals(str)) {
                    String strDateForStringToDate = TimeStampUtils.dateForStringToDate(TimeStampUtils.longStampForDate(this.mStepDp.get(i2).getStartTime()));
                    String strDateForStringToDate2 = TimeStampUtils.dateForStringToDate(TimeStampUtils.longStampForDate(this.mStepDp.get(i2).getEndTime()));
                    int sportStep = this.mStepDp.get(i2).getSportStep();
                    if (sportStep <= TransUtils.STEP_VISIBLE_MAX) {
                        this.mDayRunningHisListBean.add(new RunningHisListBean(strDateForStringToDate + "-" + strDateForStringToDate2, TimeStampUtils.dateForStringToDate(TimeStampUtils.longStampForDate(this.mStepDp.get(i2).getEndTime())), sportStep, this.mStepDp.get(i2).getSportDistance(), this.mStepDp.get(i2).getSportCalorie(), this.mStepDp.get(i2).getStartTime()));
                        this.mDayChartSumUpRunningHisListBean.add(new RunningHisListBean(strDateForStringToDate + "-" + strDateForStringToDate2, TimeStampUtils.dateForStringToDate(TimeStampUtils.longStampForDate(this.mStepDp.get(i2).getEndTime())), sportStep, this.mStepDp.get(i2).getSportDistance(), this.mStepDp.get(i2).getSportCalorie(), this.mStepDp.get(i2).getStartTime()));
                    }
                }
                i2++;
                str = thatVeryDay;
            }
        }
        checkMaxStep(thatVeryDay);
        Calendar calendar = Calendar.getInstance();
        if (TimeDateUtil.intToStr(calendar.get(1), calendar.get(2) + 1, calendar.get(5)).equals(thatVeryDay)) {
            addRunningHisListBean(this.mDayRunningHisListBean, this.mDayChartSumUpRunningHisListBean);
        }
        Logger.d("chong-------thatVeryDay==" + thatVeryDay);
        List<RunningHisListBean> list2 = this.mDayChartSumUpRunningHisListBean;
        if (list2 != null) {
            Collections.sort(list2);
        }
        initViewPager();
    }

    public void checkMaxStep(String thatVeryDay) {
        this.mThatVeryDay = thatVeryDay;
        this.mMaxStepNum = 300;
        this.mSumUpStepNum = 0;
        for (int i2 = 0; i2 < this.mDayRunningHisListBean.size(); i2++) {
            if (this.mDayRunningHisListBean.get(i2).getSportStep() > this.mMaxStepNum) {
                this.mMaxStepNum = this.mDayRunningHisListBean.get(i2).getSportStep();
            }
            this.mSumUpStepNum += this.mDayRunningHisListBean.get(i2).getSportStep();
        }
    }

    public void addRunningHisListBean(List<RunningHisListBean> mDayRunningHisListBean, List<RunningHisListBean> mDayChartSumUpRunningHisListBean) {
        int iIntValue = ((Integer) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.HOME_STEP, 0)).intValue();
        int iIntValue2 = ((Integer) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.HOME_KM, 0)).intValue();
        int iIntValue3 = ((Integer) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.HOME_KCAL, 0)).intValue();
        if (AppDateMgr.checkIsToday(((Long) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.HOME_STEP_TIME, 0L)).longValue())) {
            int i2 = this.mSumUpStepNum;
            int i3 = iIntValue - i2;
            if (iIntValue <= i2 || i3 >= TransUtils.STEP_VISIBLE_MAX) {
                return;
            }
            Calendar calendar = Calendar.getInstance();
            calendar.get(11);
            if (calendar.get(12) > 30) {
                calendar.set(12, 30);
            } else {
                calendar.set(12, 0);
            }
            String strDateForStringToDate = TimeStampUtils.dateForStringToDate(TimeStampUtils.longStampForDate(calendar.getTimeInMillis()));
            calendar.add(12, 30);
            String strDateForStringToDate2 = TimeStampUtils.dateForStringToDate(TimeStampUtils.longStampForDate(calendar.getTimeInMillis()));
            RunningHisListBean runningHisListBean = new RunningHisListBean();
            runningHisListBean.setSportStep(i3);
            runningHisListBean.setSportCalorie(iIntValue3);
            runningHisListBean.setSportDistance(iIntValue2);
            runningHisListBean.setSportStartTime(strDateForStringToDate + "-" + strDateForStringToDate2);
            mDayRunningHisListBean.add(runningHisListBean);
            mDayChartSumUpRunningHisListBean.add(runningHisListBean);
            if (i3 > this.mMaxStepNum) {
                this.mMaxStepNum = i3;
            }
            this.mSumUpStepNum += i3;
        }
    }

    public void onWeekData(List<Step> data) {
        this.mWeekMaxStepNum = 0;
        this.mWeekRunningHisListBean = new ArrayList();
        this.mWeekChartSumUpRunningHisListBean = new ArrayList();
        if (this.mStepDp != null) {
            ArrayList<String> pastStringArray = YearToDayListUtils.getPastStringArray(this.mToDay, 6);
            if (pastStringArray.size() > 0) {
                this.mStepDp = data;
            }
            for (int i2 = 0; i2 < pastStringArray.size(); i2++) {
                Log.i("AAAAA", "----" + pastStringArray.get(i2));
                getWeekDay(pastStringArray.get(i2), i2);
            }
        }
        for (int i3 = 0; i3 < this.mWeekChartSumUpRunningHisListBean.size(); i3++) {
            this.mWeekMaxStepNum += this.mWeekChartSumUpRunningHisListBean.get(i3).getSportStep();
        }
    }

    private void getWeekDay(String mThatVeryDay, int index) {
        for (int i2 = 0; i2 < this.mStepDp.size(); i2++) {
            if (TimeStampUtils.dateForStringYearToDate(TimeStampUtils.longStampForDate(this.mStepDp.get(i2).getStartTime())).equals(mThatVeryDay)) {
                String strDateForString = TimeStampUtils.dateForString(TimeStampUtils.longStampForDate(this.mStepDp.get(i2).getStartTime()));
                String strDateForString2 = TimeStampUtils.dateForString(TimeStampUtils.longStampForDate(this.mStepDp.get(i2).getEndTime()));
                int sportStep = this.mStepDp.get(i2).getSportStep();
                if (sportStep <= TransUtils.STEP_VISIBLE_MAX) {
                    this.mWeekRunningHisListBean.add(new RunningHisListBean(strDateForString, strDateForString2, sportStep, this.mStepDp.get(i2).getSportDistance(), this.mStepDp.get(i2).getSportCalorie(), this.mStepDp.get(i2).getStartTime()));
                }
            }
        }
        if (this.mWeekRunningHisListBean != null) {
            for (int i3 = 0; i3 < this.mWeekRunningHisListBean.size(); i3++) {
                this.mWeekSumUpStepNum += this.mWeekRunningHisListBean.get(i3).getSportStep();
                this.mWeekSumUpDistanceNum += this.mWeekRunningHisListBean.get(i3).getSportDistance();
                this.mWeekSumUpCalorieNum += this.mWeekRunningHisListBean.get(i3).getSportCalorie();
            }
        }
        if (this.mWeekSumUpStepNum != 0) {
            Date dateStringForDateDay = TimeStampUtils.stringForDateDay(mThatVeryDay);
            this.mWeekChartSumUpRunningHisListBean.add(new RunningHisListBean(TimeStampUtils.dateForStringDates(dateStringForDateDay), TimeStampUtils.dateForStringDates(dateStringForDateDay), this.mWeekSumUpStepNum, this.mWeekSumUpDistanceNum, this.mWeekSumUpCalorieNum, dateStringForDateDay.getTime()));
            this.mWeekRunningHisListBean.clear();
            this.mWeekSumUpStepNum = 0;
            this.mWeekSumUpDistanceNum = 0;
            this.mWeekSumUpCalorieNum = 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onMonthData(List<Step> data) {
        this.mMonthMaxStepNum = 0;
        this.mMonthRunningHisListBean = new ArrayList();
        this.mMonthChartSumUpRunningHisListBean = new ArrayList();
        if (this.mStepDp != null) {
            ArrayList<String> pastStringArray = YearToDayListUtils.getPastStringArray(this.mToDay, 29);
            if (pastStringArray.size() > 0) {
                this.mStepDp = data;
            }
            for (int i2 = 0; i2 < pastStringArray.size(); i2++) {
                getMonthDay(pastStringArray.get(i2), i2);
            }
        }
        for (int i3 = 0; i3 < this.mMonthChartSumUpRunningHisListBean.size(); i3++) {
            this.mMonthMaxStepNum += this.mMonthChartSumUpRunningHisListBean.get(i3).getSportStep();
        }
        runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.running.activity.RunningActivity.9
            @Override // java.lang.Runnable
            public void run() {
            }
        });
    }

    private void getMonthDay(String mThatVeryDay, int index) {
        int sportStep;
        for (int i2 = 0; i2 < this.mStepDp.size(); i2++) {
            try {
                if (TimeStampUtils.dateForStringYearToDate(TimeStampUtils.longStampForDate(this.mStepDp.get(i2).getStartTime())).equals(mThatVeryDay) && (sportStep = this.mStepDp.get(i2).getSportStep()) <= TransUtils.STEP_VISIBLE_MAX) {
                    this.mMonthRunningHisListBean.add(new RunningHisListBean(TimeStampUtils.dateForString(TimeStampUtils.longStampForDate(this.mStepDp.get(i2).getStartTime())), TimeStampUtils.dateForString(TimeStampUtils.longStampForDate(this.mStepDp.get(i2).getEndTime())), sportStep, this.mStepDp.get(i2).getSportDistance(), this.mStepDp.get(i2).getSportCalorie(), this.mStepDp.get(i2).getStartTime()));
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (this.mMonthRunningHisListBean != null) {
            for (int i3 = 0; i3 < this.mMonthRunningHisListBean.size(); i3++) {
                this.mMonthSumUpStepNum += this.mMonthRunningHisListBean.get(i3).getSportStep();
                this.mMonthSumUpDistanceNum += this.mMonthRunningHisListBean.get(i3).getSportDistance();
                this.mMonthSumUpCalorieNum += this.mMonthRunningHisListBean.get(i3).getSportCalorie();
            }
        }
        if (this.mMonthSumUpStepNum != 0) {
            Date dateStringForDateDay = TimeStampUtils.stringForDateDay(mThatVeryDay);
            this.mMonthChartSumUpRunningHisListBean.add(new RunningHisListBean(TimeStampUtils.dateForStringDates(dateStringForDateDay), TimeStampUtils.dateForStringDates(dateStringForDateDay), this.mMonthSumUpStepNum, this.mMonthSumUpDistanceNum, this.mMonthSumUpCalorieNum, dateStringForDateDay.getTime()));
            this.mMonthRunningHisListBean.clear();
            this.mMonthSumUpStepNum = 0;
            this.mMonthSumUpDistanceNum = 0;
            this.mMonthSumUpCalorieNum = 0;
        }
    }

    private void getDayStep(String dateTime) {
        HashMap map = new HashMap();
        map.put("userId", getIntent().getExtras().getString(Constant.SpConstKey.DEV_ID));
        map.put("day", dateTime);
        HttpUtils.getInstance().postMsgAsynHttp(this, Constants.sportDayUrl, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.home.activity.running.activity.RunningActivity.10
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                if (result != null) {
                    RunningActivity.this.temp_bean = (HistorySportResponse) new Gson().fromJson(result, HistorySportResponse.class);
                    RunningActivity.this.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.running.activity.RunningActivity.10.1
                        @Override // java.lang.Runnable
                        public void run() throws Resources.NotFoundException {
                            RunningActivity.this.setDayData();
                            RunningActivity.this.initViewPager();
                        }
                    });
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDayData() {
        List<HistorySportResponse.DataBean> list;
        JSONArray jSONArray;
        int i2;
        List<RunningHisListBean> list2 = this.mDayRunningHisListBean;
        if (list2 != null) {
            list2.clear();
        }
        List<RunningHisListBean> list3 = this.mDayChartSumUpRunningHisListBean;
        if (list3 != null) {
            list3.clear();
        }
        HistorySportResponse historySportResponse = this.temp_bean;
        if (historySportResponse == null || (list = historySportResponse.data) == null || list.size() <= 0) {
            return;
        }
        int i3 = 0;
        String str = "[" + list.get(0).mlist.replaceAll("\\[", "").replaceAll("]", "") + "]";
        this.mMaxStepNum = 300;
        this.mSumUpStepNum = 0;
        try {
            JSONArray jSONArray2 = new JSONArray(str);
            while (i3 < jSONArray2.length()) {
                JSONObject jSONObject = jSONArray2.getJSONObject(i3);
                int i4 = jSONObject.getInt("step");
                if (i4 > TransUtils.STEP_VISIBLE_MAX) {
                    i2 = i3;
                    jSONArray = jSONArray2;
                } else {
                    if (i4 > this.mMaxStepNum) {
                        this.mMaxStepNum = i4;
                    }
                    this.mSumUpStepNum += i4;
                    String strDateForStringToDate = TimeStampUtils.dateForStringToDate(TimeStampUtils.longStampForDate(jSONObject.getLong("begindate")));
                    String strDateForStringToDate2 = TimeStampUtils.dateForStringToDate(TimeStampUtils.longStampForDate(jSONObject.getLong("enddate")));
                    jSONArray = jSONArray2;
                    i2 = i3;
                    this.mDayRunningHisListBean.add(new RunningHisListBean(strDateForStringToDate + "-" + strDateForStringToDate2, strDateForStringToDate2, i4, jSONObject.getInt("des"), jSONObject.getInt("cakl"), jSONObject.getLong("begindate")));
                    this.mDayChartSumUpRunningHisListBean.add(new RunningHisListBean(strDateForStringToDate, strDateForStringToDate2, i4, jSONObject.getInt("des"), jSONObject.getInt("cakl"), jSONObject.getLong("begindate")));
                }
                i3 = i2 + 1;
                jSONArray2 = jSONArray;
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    private void getWeekStep(String dateTime, String startTime, String endTime) {
        HashMap map = new HashMap();
        map.put("userId", getIntent().getExtras().getString(Constant.SpConstKey.DEV_ID));
        map.put("day", dateTime);
        map.put("startDate", startTime);
        map.put("endDate", endTime);
        map.put("type", "1");
        HttpUtils.getInstance().postMsgAsynHttp(this, Constants.sportDayUrl, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.home.activity.running.activity.RunningActivity.11
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                if (result != null) {
                    List<CareStepWeekMonthBean.DataBean> data = ((CareStepWeekMonthBean) new Gson().fromJson(result, CareStepWeekMonthBean.class)).getData();
                    if (data.size() != 0) {
                        RunningActivity.this.mWeekMaxStepNum = 0;
                        for (int i2 = 0; i2 < data.size(); i2++) {
                            RunningActivity.this.mWeekMaxStepNum += Integer.parseInt(data.get(i2).getStepTotal().isEmpty() ? "0" : data.get(i2).getStepTotal().replaceAll(",", "."));
                            if (data.get(i2).getStepTotal().isEmpty()) {
                                RunningActivity.this.mWeekChartSumUpRunningHisListBean.add(new RunningHisListBean(TimeStampUtils.dateForStringDates(TimeStampUtils.stringForDateDay(data.get(i2).getDateformat())), TimeStampUtils.dateForStringDates(TimeStampUtils.stringForDateDay(data.get(i2).getDateformat())), 0, 0, 0, AppDateMgr.string2Date(data.get(i2).getDateformat(), AppDateMgr.YYYYMMDD_FORMAT).getTime()));
                            } else {
                                RunningActivity.this.mWeekChartSumUpRunningHisListBean.add(new RunningHisListBean(TimeStampUtils.dateForStringDates(TimeStampUtils.stringForDateDay(data.get(i2).getDateformat())), TimeStampUtils.dateForStringDates(TimeStampUtils.stringForDateDay(data.get(i2).getDateformat())), Integer.parseInt(data.get(i2).getStepTotal().replaceAll(",", ".")), Integer.parseInt(data.get(i2).getDesTotal().replaceAll(",", ".")), Integer.parseInt(data.get(i2).getCaklTotal().replaceAll(",", ".")), AppDateMgr.string2Date(data.get(i2).getBegindate(), AppDateMgr.YYYYMMDDHHMMSS_FORMAT).getTime()));
                            }
                        }
                    }
                }
            }
        });
    }

    private void getMonthStep(String dateTime, String startTime, String endTime) {
        HashMap map = new HashMap();
        map.put("userId", getIntent().getExtras().getString(Constant.SpConstKey.DEV_ID));
        map.put("day", dateTime);
        map.put("startDate", startTime);
        map.put("endDate", endTime);
        map.put("type", "1");
        HttpUtils.getInstance().postMsgAsynHttp(this, Constants.sportDayUrl, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.home.activity.running.activity.RunningActivity.12
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) throws Resources.NotFoundException {
                if (result != null) {
                    List<CareStepWeekMonthBean.DataBean> data = ((CareStepWeekMonthBean) new Gson().fromJson(result, CareStepWeekMonthBean.class)).getData();
                    if (data.size() != 0) {
                        RunningActivity.this.mMonthMaxStepNum = 0;
                        for (int i2 = 0; i2 < data.size(); i2++) {
                            RunningActivity.this.mMonthMaxStepNum += Integer.parseInt(data.get(i2).getStepTotal().isEmpty() ? "0" : data.get(i2).getStepTotal().replaceAll(",", "."));
                            if (data.get(i2).getStepTotal().isEmpty()) {
                                RunningActivity.this.mMonthChartSumUpRunningHisListBean.add(new RunningHisListBean(TimeStampUtils.dateForStringDates(TimeStampUtils.stringForDateDay(data.get(i2).getDateformat())), TimeStampUtils.dateForStringDates(TimeStampUtils.stringForDateDay(data.get(i2).getDateformat())), 0, 0, 0, AppDateMgr.string2Date(data.get(i2).getDateformat(), AppDateMgr.YYYYMMDD_FORMAT).getTime()));
                            } else {
                                RunningActivity.this.mMonthChartSumUpRunningHisListBean.add(new RunningHisListBean(TimeStampUtils.dateForStringDates(TimeStampUtils.stringForDateDay(data.get(i2).getDateformat())), TimeStampUtils.dateForStringDates(TimeStampUtils.stringForDateDay(data.get(i2).getDateformat())), Integer.parseInt(data.get(i2).getStepTotal().replaceAll(",", ".")), Integer.parseInt(data.get(i2).getDesTotal().replaceAll(",", ".")), Integer.parseInt(data.get(i2).getCaklTotal().replaceAll(",", ".")), AppDateMgr.string2Date(data.get(i2).getBegindate(), AppDateMgr.YYYYMMDDHHMMSS_FORMAT).getTime()));
                            }
                        }
                        if (RunningActivity.this.mCalendarView != null) {
                            RunningActivity.this.mCalendarView.scrollToCurrent();
                        }
                    }
                }
            }
        });
    }

    public void onViewClicked(View view) {
        if (view.getId() == R.id.ll_calendar) {
            if (this.MONTH == 0) {
                this.llMonth.setVisibility(0);
                this.MONTH = 1;
                return;
            } else {
                this.llMonth.setVisibility(8);
                this.MONTH = 0;
                return;
            }
        }
        if (view.getId() == R.id.tv_back_today) {
            this.mViewPager.setCurrentItem(2);
            return;
        }
        if (view.getId() == R.id.tv_start_button || view.getId() == R.id.rl_analyse) {
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
                this.mNestedScrollView.postDelayed(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.running.activity.RunningActivity.13
                    @Override // java.lang.Runnable
                    public void run() {
                        RunningActivity.this.mNestedScrollView.smoothScrollTo(0, (int) (RunningActivity.this.mNestedScrollView.getScrollY() + (DpUtil.dp2px(RunningActivity.this.context, 56.0f) * 1.5f)));
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
            this.MONTH = 0;
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
}
