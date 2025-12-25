package com.yucheng.smarthealthpro.home.activity.bloodpressure.activity;

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
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.care.bean.CareBpWeekMonthBean;
import com.yucheng.smarthealthpro.care.bean.HistoryBloodResponse;
import com.yucheng.smarthealthpro.data.packed.HealthDayData;
import com.yucheng.smarthealthpro.data.packed.HealthPackedData;
import com.yucheng.smarthealthpro.database.room.bean.BloodPressure;
import com.yucheng.smarthealthpro.databinding.ActivityBloodpressureBinding;
import com.yucheng.smarthealthpro.framework.http.HttpUtils;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import com.yucheng.smarthealthpro.home.activity.HealthyActivity;
import com.yucheng.smarthealthpro.home.activity.bloodpressure.adapter.BloodPressureHisListAdapter;
import com.yucheng.smarthealthpro.home.activity.bloodpressure.adapter.BpTabFragmentAdapter;
import com.yucheng.smarthealthpro.home.activity.bloodpressure.bean.BloodPressureHisListBean;
import com.yucheng.smarthealthpro.home.activity.bloodpressure.fragment.BpTabFragment;
import com.yucheng.smarthealthpro.home.util.HealthDataFilterKt;
import com.yucheng.smarthealthpro.home.util.TimeDateUtil;
import com.yucheng.smarthealthpro.home.view.NoScrollViewPager;
import com.yucheng.smarthealthpro.me.activity.MeHealthSettingActivity;
import com.yucheng.smarthealthpro.me.setting.contacts.utils.DpUtil;
import com.yucheng.smarthealthpro.settings.uploadnativedata.StepInstructionsActivity;
import com.yucheng.smarthealthpro.sport.view.CommonDialog;
import com.yucheng.smarthealthpro.utils.AppScreenMgr;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.DebouncingClick;
import com.yucheng.smarthealthpro.utils.DensityUtils;
import com.yucheng.smarthealthpro.utils.FlowUtils;
import com.yucheng.smarthealthpro.utils.ShareUtils;
import com.yucheng.smarthealthpro.utils.TimeStampUtils;
import com.yucheng.smarthealthpro.utils.Tools;
import com.yucheng.smarthealthpro.utils.YearToDayListUtils;
import com.yucheng.smarthealthpro.viewmodel.BloodPressureViewModel;
import com.yucheng.ycbtsdk.Constants;
import com.yucheng.ycbtsdk.YCBTClient;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes5.dex */
public class BloodPressureActivity extends BaseVbActivity<ActivityBloodpressureBinding> implements CalendarView.OnCalendarSelectListener, CalendarView.OnMonthChangeListener {
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
    private BpTabFragmentAdapter mAdapter;
    private List<BloodPressure> mBloodDb;
    private BloodPressureHisListAdapter mBloodPressureHisListAdapter;
    private Calendar mCalendar;
    CalendarView mCalendarView;
    private int mDayAverageDBPNum;
    private int mDayAverageSBPNum;
    private int mDayMaxDBPNum;
    private int mDayMaxSBPNum;
    private int mDayMinDBPNum;
    private int mDayMinSBPNum;
    private int mDaySumUpDBPNum;
    private int mDaySumUpSBPNum;
    private int mMonthAverageDBPNum;
    private int mMonthAverageSBPNum;
    private int mMonthMaxDBPNum;
    private int mMonthMaxSBPNum;
    private int mMonthMinDBPNum;
    private int mMonthMinSBPNum;
    NestedScrollView mNestedScrollView;
    RecyclerView mRecyclerView;
    SlidingTabLayout mSlidingTabLayout;
    private String mToDay;
    private BloodPressureViewModel mViewModel;
    NoScrollViewPager mViewPager;
    private int mWeekAverageDBPNum;
    private int mWeekAverageSBPNum;
    private int mWeekMaxDBPNum;
    private int mWeekMaxSBPNum;
    private int mWeekMinDBPNum;
    private int mWeekMinSBPNum;
    private int monthLastDay;
    RelativeLayout rlAnalyse;
    RelativeLayout rlDataFirst;
    RelativeLayout rlFirst;
    RelativeLayout rlFourthly;
    RelativeLayout rlPlan;
    RelativeLayout rlSecond;
    private HistoryBloodResponse temp_bean;
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
    TextView tvStartButton2;
    TextView tvYears;
    private int ARROW = 0;
    private List<String> mTitles = new ArrayList();
    private int MONTH = 0;
    private List<BloodPressureHisListBean> mDayBloodPressureHisListBean = new ArrayList();
    private List<BloodPressureHisListBean> mDayChartSumUpBloodPressureHisListBean = new ArrayList();
    private List<BloodPressureHisListBean> mWeekAdapterBloodPressureHisListBean = new ArrayList();
    private List<BloodPressureHisListBean> mWeekChartSumUpBloodPressureHisListBean = new ArrayList();
    private List<BloodPressureHisListBean> mMonthAdapterBloodPressureHisListBean = new ArrayList();
    private List<BloodPressureHisListBean> mMonthChartSumUpBloodPressureHisListBean = new ArrayList();
    private Boolean isCare = false;
    final int BLOOD_PRESSURE_MEASURE = 0;

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
        this.mSlidingTabLayout = ((ActivityBloodpressureBinding) this.mBinding).includeItemTop.stlTab;
        this.ivCalendar = ((ActivityBloodpressureBinding) this.mBinding).includeItemTop.ivCalendar;
        this.tvCalendar = ((ActivityBloodpressureBinding) this.mBinding).includeItemTop.tvCalendar;
        this.tvBackToday = ((ActivityBloodpressureBinding) this.mBinding).includeItemTop.tvBackToday;
        this.llCalendar = ((ActivityBloodpressureBinding) this.mBinding).includeItemTop.llCalendar;
        this.mViewPager = ((ActivityBloodpressureBinding) this.mBinding).includeItemTop.vpTab;
        this.tvDataFirst = ((ActivityBloodpressureBinding) this.mBinding).includeItemMessageData.tvDataFirst;
        this.tvDataFirstUnit = ((ActivityBloodpressureBinding) this.mBinding).includeItemMessageData.tvDataFirstUnit;
        this.rlDataFirst = ((ActivityBloodpressureBinding) this.mBinding).includeItemMessageData.rlDataFirst;
        this.tvDataSecond = ((ActivityBloodpressureBinding) this.mBinding).includeItemMessageData.tvDataSecond;
        this.ivDataSecond = ((ActivityBloodpressureBinding) this.mBinding).includeItemMessageData.ivDataSecond;
        this.tvDataSecondUnit = ((ActivityBloodpressureBinding) this.mBinding).includeItemMessageData.tvDataSecondUnit;
        this.llDataSecond = ((ActivityBloodpressureBinding) this.mBinding).includeItemMessageData.llDataSecond;
        this.tvDataThirdly = ((ActivityBloodpressureBinding) this.mBinding).includeItemMessageData.tvDataThirdly;
        this.ivDataThirdly = ((ActivityBloodpressureBinding) this.mBinding).includeItemMessageData.ivDataThirdly;
        this.tvDataThirdlyUnit = ((ActivityBloodpressureBinding) this.mBinding).includeItemMessageData.tvDataThirdlyUnit;
        this.llDataThirdly = ((ActivityBloodpressureBinding) this.mBinding).includeItemMessageData.llDataThirdly;
        this.tvAdditionalMsg = ((ActivityBloodpressureBinding) this.mBinding).includeItemBottom.tvAdditionalMsg;
        this.tvStartButton = ((ActivityBloodpressureBinding) this.mBinding).includeItemBottom.tvStartButton;
        this.tvStartButton2 = ((ActivityBloodpressureBinding) this.mBinding).includeItemBottom.tvStartButton2;
        this.llStartButton = ((ActivityBloodpressureBinding) this.mBinding).includeItemBottom.llStartButton;
        this.tvAnalyse = ((ActivityBloodpressureBinding) this.mBinding).includeItemBottom.tvAnalyse;
        this.tvAnalyseData = ((ActivityBloodpressureBinding) this.mBinding).includeItemBottom.tvAnalyseData;
        this.rlAnalyse = ((ActivityBloodpressureBinding) this.mBinding).includeItemBottom.rlAnalyse;
        this.ivFirstLeft = ((ActivityBloodpressureBinding) this.mBinding).includeItemBottom.ivFirstLeft;
        this.tvFirst = ((ActivityBloodpressureBinding) this.mBinding).includeItemBottom.tvFirst;
        this.ivFirstRight = ((ActivityBloodpressureBinding) this.mBinding).includeItemBottom.ivFirstRight;
        this.rlFirst = ((ActivityBloodpressureBinding) this.mBinding).includeItemBottom.rlFirst;
        this.ivSecondLeft = ((ActivityBloodpressureBinding) this.mBinding).includeItemBottom.ivSecondLeft;
        this.tvSecond = ((ActivityBloodpressureBinding) this.mBinding).includeItemBottom.tvSecond;
        this.ivSecondRight = ((ActivityBloodpressureBinding) this.mBinding).includeItemBottom.ivSecondRight;
        this.rlSecond = ((ActivityBloodpressureBinding) this.mBinding).includeItemBottom.rlSecond;
        this.ivFourthlyLeft = ((ActivityBloodpressureBinding) this.mBinding).includeItemBottom.ivFourthlyLeft;
        this.tvFourthly = ((ActivityBloodpressureBinding) this.mBinding).includeItemBottom.tvFourthly;
        this.ivFourthlyRight = ((ActivityBloodpressureBinding) this.mBinding).includeItemBottom.ivFourthlyRight;
        this.rlFourthly = ((ActivityBloodpressureBinding) this.mBinding).includeItemBottom.rlFourthly;
        this.mRecyclerView = ((ActivityBloodpressureBinding) this.mBinding).includeItemBottom.recycleView;
        this.mNestedScrollView = ((ActivityBloodpressureBinding) this.mBinding).nsv;
        this.tvYears = ((ActivityBloodpressureBinding) this.mBinding).includeItemCalendar.tvYears;
        this.mCalendarView = ((ActivityBloodpressureBinding) this.mBinding).includeItemCalendar.calendarView;
        this.llMonth = ((ActivityBloodpressureBinding) this.mBinding).includeItemCalendar.llMonth;
        this.rlPlan = ((ActivityBloodpressureBinding) this.mBinding).includeItemBottom.rlPlan;
        this.tvStartButton.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.bloodpressure.activity.BloodPressureActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.tvStartButton2.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.bloodpressure.activity.BloodPressureActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.llCalendar.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.bloodpressure.activity.BloodPressureActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.tvBackToday.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.bloodpressure.activity.BloodPressureActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.llStartButton.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.bloodpressure.activity.BloodPressureActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlAnalyse.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.bloodpressure.activity.BloodPressureActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlFirst.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.bloodpressure.activity.BloodPressureActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlSecond.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.bloodpressure.activity.BloodPressureActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlFourthly.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.bloodpressure.activity.BloodPressureActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.llMonth.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.bloodpressure.activity.BloodPressureActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlPlan.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.bloodpressure.activity.BloodPressureActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        changeTitle(getString(R.string.home_blood_pressure_title));
        showBack();
        showRightImage(R.mipmap.topbar_ic_share, new NavigationBar.MyOnClickListener() { // from class: com.yucheng.smarthealthpro.home.activity.bloodpressure.activity.BloodPressureActivity.1
            @Override // com.yucheng.smarthealthpro.framework.view.NavigationBar.MyOnClickListener
            public void onClick(View btn) {
                ShareUtils.share(BloodPressureActivity.this);
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
            this.rlSecond.setVisibility(8);
            this.rlFirst.setVisibility(8);
        } else {
            if (!YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASTESTBLOOD) && !YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASINFLATED)) {
                this.llStartButton.setVisibility(8);
            } else {
                this.llStartButton.setVisibility(0);
                this.tvAdditionalMsg.setVisibility(0);
            }
            if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASTESTBLOOD)) {
                this.tvStartButton.setVisibility(0);
                this.tvAdditionalMsg.setVisibility(0);
            } else {
                this.tvStartButton.setVisibility(8);
            }
            this.tvStartButton2.setVisibility(8);
            this.tvStartButton.setText(getString(R.string.home_blood_pressure_measure_title));
            this.tvStartButton2.setText(getString(R.string.include_tv_start_button));
            this.tvFirst.setText(getString(R.string.include_bottom_tv_first_button));
            this.tvSecond.setText(getString(R.string.include_bottom_tv_second_button));
        }
        this.tvAnalyse.setText(getString(R.string.home_blood_pressure_analyse_tv));
        this.tvFourthly.setText(getString(R.string.include_bottom_tv_fourthly_button));
        this.ivFourthlyRight.setBackground(ResourcesCompat.getDrawable(getResources(), R.mipmap.list_ic_arrow_n, null));
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        getCurrData();
    }

    private void initViewModel() {
        this.mViewModel = (BloodPressureViewModel) new ViewModelProvider(this).get(BloodPressureViewModel.class);
        FlowUtils.INSTANCE.collectFlow(this, this.mViewModel.getBloodPressureDataFlow(), new FlowUtils.FlowCollector<HealthDayData<BloodPressure>>() { // from class: com.yucheng.smarthealthpro.home.activity.bloodpressure.activity.BloodPressureActivity.2
            @Override // com.yucheng.smarthealthpro.utils.FlowUtils.FlowCollector
            public void collect(HealthDayData<BloodPressure> data) throws Resources.NotFoundException {
                BloodPressureActivity.this.onThatVeryDayData(data.getDay(), data.getData());
            }
        });
        FlowUtils.INSTANCE.collectFlow(this, this.mViewModel.getBloodPressurePackedDataFlow(), new FlowUtils.FlowCollector<HealthPackedData<BloodPressure>>() { // from class: com.yucheng.smarthealthpro.home.activity.bloodpressure.activity.BloodPressureActivity.3
            @Override // com.yucheng.smarthealthpro.utils.FlowUtils.FlowCollector
            public void collect(HealthPackedData<BloodPressure> packedData) {
                if (packedData.getDayCount() == 7) {
                    BloodPressureActivity.this.onDataByDays(7, packedData.getData());
                } else if (packedData.getDayCount() == 30) {
                    BloodPressureActivity.this.onDataByDays(30, packedData.getData());
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
        if (this.isCare.booleanValue()) {
            ArrayList<String> pastStringArray = YearToDayListUtils.getPastStringArray(this.mToDay, 6);
            ArrayList<String> pastStringArray2 = YearToDayListUtils.getPastStringArray(this.mToDay, 29);
            getWeekBp(pastStringArray.get(0), pastStringArray.get(0), pastStringArray.get(6));
            getMonthBp(pastStringArray2.get(0), pastStringArray2.get(0), pastStringArray2.get(29));
            return;
        }
        new Thread(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.bloodpressure.activity.BloodPressureActivity.4
            @Override // java.lang.Runnable
            public void run() {
                BloodPressureActivity.this.getWeekData();
                BloodPressureActivity.this.getMonthData();
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
        BpTabFragmentAdapter bpTabFragmentAdapter = new BpTabFragmentAdapter(getSupportFragmentManager(), new BpTabFragmentAdapter.FragmentCreator() { // from class: com.yucheng.smarthealthpro.home.activity.bloodpressure.activity.BloodPressureActivity.5
            @Override // com.yucheng.smarthealthpro.home.activity.bloodpressure.adapter.BpTabFragmentAdapter.FragmentCreator
            public Fragment createFragment(String data, int position) {
                return BpTabFragment.newInstance(data.toString(), position, BloodPressureActivity.this.mNestedScrollView, BloodPressureActivity.this.mDayChartSumUpBloodPressureHisListBean, BloodPressureActivity.this.mWeekChartSumUpBloodPressureHisListBean, BloodPressureActivity.this.mMonthChartSumUpBloodPressureHisListBean);
            }

            @Override // com.yucheng.smarthealthpro.home.activity.bloodpressure.adapter.BpTabFragmentAdapter.FragmentCreator
            public String createTitle(String data) {
                return Html.fromHtml(data).toString();
            }
        });
        this.mAdapter = bpTabFragmentAdapter;
        this.mViewPager.setAdapter(bpTabFragmentAdapter);
        this.mAdapter.notifyDataSetChanged();
        this.mViewPager.setOffscreenPageLimit(this.mDayBloodPressureHisListBean.size() - 1);
        this.mAdapter.setData(this.mTitles);
        this.mSlidingTabLayout.setViewPager(this.mViewPager, (String[]) this.mTitles.toArray(new String[0]));
        this.mSlidingTabLayout.setCurrentTab(2, true);
        this.mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.yucheng.smarthealthpro.home.activity.bloodpressure.activity.BloodPressureActivity.6
            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int state) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int position) {
                BloodPressureActivity.this.mViewPager.setCurrentItem(position);
                if (position == 0) {
                    BloodPressureActivity.this.freshMonthData();
                } else if (position == 1) {
                    BloodPressureActivity.this.freshWeekData();
                } else {
                    if (position != 2) {
                        return;
                    }
                    BloodPressureActivity.this.freshDayData();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void freshDayData() {
        this.tvBackToday.setVisibility(8);
        this.llCalendar.setVisibility(0);
        List<BloodPressureHisListBean> list = this.mDayBloodPressureHisListBean;
        if (list != null && list.size() > 0) {
            this.tvDataFirst.setText(this.mDayAverageSBPNum + "/" + this.mDayAverageDBPNum);
            this.tvDataSecond.setText(this.mDayMaxSBPNum + "/" + this.mDayMaxDBPNum);
            this.tvDataThirdly.setText(this.mDayMinSBPNum + "/" + this.mDayMinDBPNum);
        } else {
            this.tvDataFirst.setText("--");
            this.tvDataSecond.setText("--");
            this.tvDataThirdly.setText("--");
            this.mDayAverageDBPNum = 0;
            this.mDayAverageSBPNum = 0;
        }
        this.mBloodPressureHisListAdapter.setList(this.mDayBloodPressureHisListBean);
        this.mBloodPressureHisListAdapter.notifyDataSetChanged();
        dataAnalysis(this.mDayAverageDBPNum, this.mDayAverageSBPNum);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void freshWeekData() {
        this.tvBackToday.setVisibility(0);
        this.llCalendar.setVisibility(8);
        List<BloodPressureHisListBean> list = this.mWeekAdapterBloodPressureHisListBean;
        if (list != null && list.size() > 0) {
            this.tvDataFirst.setText(this.mWeekAverageSBPNum + "/" + this.mWeekAverageDBPNum);
            this.tvDataSecond.setText(this.mWeekMaxSBPNum + "/" + this.mWeekMaxDBPNum);
            this.tvDataThirdly.setText(this.mWeekMinSBPNum + "/" + this.mWeekMinDBPNum);
        } else {
            this.tvDataFirst.setText("--");
            this.tvDataSecond.setText("--");
            this.tvDataThirdly.setText("--");
            this.mWeekAverageDBPNum = 0;
            this.mWeekAverageSBPNum = 0;
        }
        this.mBloodPressureHisListAdapter.setList(this.mWeekAdapterBloodPressureHisListBean);
        this.mBloodPressureHisListAdapter.notifyDataSetChanged();
        dataAnalysis(this.mWeekAverageDBPNum, this.mWeekAverageSBPNum);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void freshMonthData() {
        this.tvBackToday.setVisibility(0);
        this.llCalendar.setVisibility(8);
        List<BloodPressureHisListBean> list = this.mMonthAdapterBloodPressureHisListBean;
        if (list != null && list.size() > 0) {
            this.tvDataFirst.setText(this.mMonthAverageSBPNum + "/" + this.mMonthAverageDBPNum);
            this.tvDataSecond.setText(this.mMonthMaxSBPNum + "/" + this.mMonthMaxDBPNum);
            this.tvDataThirdly.setText(this.mMonthMinSBPNum + "/" + this.mMonthMinDBPNum);
        } else {
            this.tvDataFirst.setText("--/--");
            this.tvDataSecond.setText("--/--");
            this.tvDataThirdly.setText("--/--");
            this.mMonthAverageDBPNum = 0;
            this.mMonthAverageSBPNum = 0;
        }
        this.mBloodPressureHisListAdapter.replaceData(this.mMonthAdapterBloodPressureHisListBean);
        this.mBloodPressureHisListAdapter.notifyDataSetChanged();
        dataAnalysis(this.mMonthAverageDBPNum, this.mMonthAverageSBPNum);
    }

    public void dataAnalysis(int DBP, int SBP) {
        if (Constant.isTechFeel()) {
            if (this.mBloodPressureHisListAdapter.getData().size() > 0) {
                this.tvDataFirst.setText(this.mBloodPressureHisListAdapter.getData().get(0).getBloodSBP() + "/" + this.mBloodPressureHisListAdapter.getData().get(0).getBloodDBP());
            } else {
                this.tvDataFirst.setText("--/--");
            }
        }
        if ((SBP < 90 && SBP > 70) || (DBP < 50 && DBP > 40)) {
            this.tvAnalyseData.setText(getText(R.string.blood_pressure_analysis_hypotension));
            return;
        }
        if (SBP > 180 || DBP > 130) {
            this.tvAnalyseData.setText(getText(R.string.blood_pressure_analysis_severe_hypertension));
            return;
        }
        if (SBP > 160 || DBP > 110) {
            this.tvAnalyseData.setText(getText(R.string.blood_pressure_analysis_moderate_hypertension));
            return;
        }
        if (SBP > 140 || DBP > 90) {
            this.tvAnalyseData.setText(getText(R.string.blood_pressure_analysis_mild_hypertension));
        } else if (SBP != 0 && DBP != 0) {
            this.tvAnalyseData.setText(getText(R.string.blood_pressure_analysis_normal));
        } else {
            this.tvAnalyseData.setText("");
        }
    }

    private void setRecycleView() {
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        BloodPressureHisListAdapter bloodPressureHisListAdapter = new BloodPressureHisListAdapter(R.layout.item_universal_his_list);
        this.mBloodPressureHisListAdapter = bloodPressureHisListAdapter;
        bloodPressureHisListAdapter.addData((Collection) this.mDayBloodPressureHisListBean);
        this.mRecyclerView.setAdapter(this.mBloodPressureHisListAdapter);
        this.mRecyclerView.setNestedScrollingEnabled(false);
        this.mBloodPressureHisListAdapter.setOnItemClickListener(new BloodPressureHisListAdapter.OnItemClickListener() { // from class: com.yucheng.smarthealthpro.home.activity.bloodpressure.activity.BloodPressureActivity.7
            @Override // com.yucheng.smarthealthpro.home.activity.bloodpressure.adapter.BloodPressureHisListAdapter.OnItemClickListener
            public void onClick(BloodPressureHisListBean hisSearch, int position) {
            }

            @Override // com.yucheng.smarthealthpro.home.activity.bloodpressure.adapter.BloodPressureHisListAdapter.OnItemClickListener
            public void onDelClick(BloodPressureHisListBean hisSearch, int position) {
            }

            @Override // com.yucheng.smarthealthpro.home.activity.bloodpressure.adapter.BloodPressureHisListAdapter.OnItemClickListener
            public void onLongClick(BloodPressureHisListBean hisSearch, int position) {
            }
        });
    }

    private void initMonth() throws Resources.NotFoundException {
        this.mCalendarView.setOnCalendarSelectListener(this);
        this.mCalendarView.setOnMonthChangeListener(this);
        this.mCalendarView.scrollToCurrent();
        this.mCalendarView.setOnCalendarInterceptListener(new CalendarView.OnCalendarInterceptListener() { // from class: com.yucheng.smarthealthpro.home.activity.bloodpressure.activity.BloodPressureActivity.8
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
            getDayBp(strIntToStr);
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

    public void onThatVeryDayData(String thatVeryDay, List<BloodPressure> data) throws Resources.NotFoundException {
        int i2;
        this.mDayBloodPressureHisListBean.clear();
        this.mDayChartSumUpBloodPressureHisListBean.clear();
        this.mDaySumUpSBPNum = 0;
        this.mDaySumUpDBPNum = 0;
        this.mDayMaxSBPNum = 0;
        this.mDayMaxDBPNum = 0;
        this.mDayMinSBPNum = 250;
        this.mDayMinDBPNum = 160;
        this.mDayAverageDBPNum = 0;
        this.mDayAverageSBPNum = 0;
        this.mBloodDb = data;
        if (data != null) {
            for (int i3 = 0; i3 < this.mBloodDb.size(); i3++) {
                int diastolicBloodPressure = this.mBloodDb.get(i3).getDiastolicBloodPressure();
                int systolicBloodPressure = this.mBloodDb.get(i3).getSystolicBloodPressure();
                if (diastolicBloodPressure >= 30 && diastolicBloodPressure <= 160 && systolicBloodPressure >= 60 && systolicBloodPressure <= 250 && (i2 = systolicBloodPressure - diastolicBloodPressure) >= 10 && i2 <= 90) {
                    if (systolicBloodPressure > this.mDayMaxSBPNum) {
                        this.mDayMaxDBPNum = diastolicBloodPressure;
                        this.mDayMaxSBPNum = systolicBloodPressure;
                    }
                    if (diastolicBloodPressure < this.mDayMinDBPNum) {
                        this.mDayMinSBPNum = systolicBloodPressure;
                        this.mDayMinDBPNum = diastolicBloodPressure;
                    }
                    this.mDaySumUpSBPNum += systolicBloodPressure;
                    this.mDaySumUpDBPNum += diastolicBloodPressure;
                    this.mDayBloodPressureHisListBean.add(new BloodPressureHisListBean(TimeStampUtils.dateForStringYearToDate(TimeStampUtils.longStampForDate(this.mBloodDb.get(i3).getStartTimestamp())), TimeStampUtils.dateForStringToDate(TimeStampUtils.longStampForDate(this.mBloodDb.get(i3).getStartTimestamp())), this.mBloodDb.get(i3).getDiastolicBloodPressure(), this.mBloodDb.get(i3).getSystolicBloodPressure(), "正常", this.mBloodDb.get(i3).getMeasureMode()));
                    this.mDayChartSumUpBloodPressureHisListBean.add(new BloodPressureHisListBean(TimeStampUtils.dateForStringYearToDate(TimeStampUtils.longStampForDate(this.mBloodDb.get(i3).getStartTimestamp())), TimeStampUtils.dateForStringToDate(TimeStampUtils.longStampForDate(this.mBloodDb.get(i3).getStartTimestamp())), this.mBloodDb.get(i3).getDiastolicBloodPressure(), this.mBloodDb.get(i3).getSystolicBloodPressure(), "正常", this.mBloodDb.get(i3).getMeasureMode()));
                }
            }
        }
        List<BloodPressureHisListBean> list = this.mDayChartSumUpBloodPressureHisListBean;
        if (list != null) {
            Collections.reverse(list);
        }
        if (this.mDayBloodPressureHisListBean.size() != 0) {
            this.mDayAverageDBPNum = Math.round((this.mDaySumUpDBPNum * 1.0f) / this.mDayBloodPressureHisListBean.size());
            this.mDayAverageSBPNum = Math.round((this.mDaySumUpSBPNum * 1.0f) / this.mDayBloodPressureHisListBean.size());
        }
        initViewPager();
    }

    public void getWeekData() {
        this.mWeekAdapterBloodPressureHisListBean.clear();
        this.mWeekChartSumUpBloodPressureHisListBean.clear();
        this.mWeekAverageDBPNum = 0;
        this.mWeekAverageSBPNum = 0;
        this.mWeekMaxDBPNum = 0;
        this.mWeekMaxSBPNum = 0;
        this.mWeekMinSBPNum = 250;
        this.mWeekMinDBPNum = 160;
        this.mViewModel.getPackedDayData(YearToDayListUtils.getPastStringArray(this.mToDay, 6).get(0), 7);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onDataByDays(int days, List<BloodPressure> data) {
        int size;
        int i2;
        int i3;
        int i4;
        ArrayList<String> pastStringArray = YearToDayListUtils.getPastStringArray(this.mToDay, days - 1);
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (true) {
            int i8 = 30;
            if (i5 >= days) {
                break;
            }
            List<BloodPressure> listFilterBloodPressureByDate = HealthDataFilterKt.filterBloodPressureByDate(data, pastStringArray.get(i5));
            this.mBloodDb = listFilterBloodPressureByDate;
            if (listFilterBloodPressureByDate != null) {
                size = listFilterBloodPressureByDate.size();
                int i9 = 0;
                i2 = 0;
                i3 = 0;
                while (i9 < this.mBloodDb.size()) {
                    int diastolicBloodPressure = this.mBloodDb.get(i9).getDiastolicBloodPressure();
                    int systolicBloodPressure = this.mBloodDb.get(i9).getSystolicBloodPressure();
                    if (diastolicBloodPressure < i8 || diastolicBloodPressure > 160 || systolicBloodPressure < 60 || systolicBloodPressure > 250 || (i4 = systolicBloodPressure - diastolicBloodPressure) < 10 || i4 > 90) {
                        size--;
                    } else {
                        i2 += systolicBloodPressure;
                        i3 += diastolicBloodPressure;
                    }
                    i9++;
                    i8 = 30;
                }
            } else {
                size = 0;
                i2 = 0;
                i3 = 0;
            }
            if (i2 != 0 && i3 != 0) {
                int i10 = i2 / size;
                int i11 = i3 / size;
                i6 += i10;
                i7 += i11;
                if (days == 7) {
                    if (i10 > this.mWeekMaxSBPNum) {
                        this.mWeekMaxSBPNum = i10;
                        this.mWeekMaxDBPNum = i11;
                    }
                    if (i11 < this.mWeekMinDBPNum) {
                        this.mWeekMinDBPNum = i11;
                        this.mWeekMinSBPNum = i10;
                    }
                    this.mWeekAdapterBloodPressureHisListBean.add(new BloodPressureHisListBean(pastStringArray.get(i5), pastStringArray.get(i5), i11, i10, "", -1));
                    this.mWeekChartSumUpBloodPressureHisListBean.add(new BloodPressureHisListBean(TimeStampUtils.toFormatDate(pastStringArray.get(i5)), TimeStampUtils.dateForStringDates(TimeStampUtils.stringForDateDay(pastStringArray.get(i5))), i11, i10, "", -1));
                } else if (days == 30) {
                    if (i10 > this.mMonthMaxSBPNum) {
                        this.mMonthMaxSBPNum = i10;
                        this.mMonthMaxDBPNum = i11;
                    }
                    if (i11 < this.mMonthMinDBPNum) {
                        this.mMonthMinDBPNum = i11;
                        this.mMonthMinSBPNum = i10;
                    }
                    this.mMonthAdapterBloodPressureHisListBean.add(new BloodPressureHisListBean(pastStringArray.get(i5), pastStringArray.get(i5), i11, i10, "", -1));
                    this.mMonthChartSumUpBloodPressureHisListBean.add(new BloodPressureHisListBean(TimeStampUtils.toFormatDate(pastStringArray.get(i5)), TimeStampUtils.dateForStringDates(TimeStampUtils.stringForDateDay(pastStringArray.get(i5))), i11, i10, "", -1));
                }
            } else if (days == 7) {
                this.mWeekChartSumUpBloodPressureHisListBean.add(new BloodPressureHisListBean(TimeStampUtils.toFormatDate(pastStringArray.get(i5)), TimeStampUtils.dateForStringDates(TimeStampUtils.stringForDateDay(pastStringArray.get(i5))), 0, 0, "", -1));
            } else if (days == 30) {
                this.mMonthChartSumUpBloodPressureHisListBean.add(new BloodPressureHisListBean(TimeStampUtils.toFormatDate(pastStringArray.get(i5)), TimeStampUtils.dateForStringDates(TimeStampUtils.stringForDateDay(pastStringArray.get(i5))), 0, 0, "", -1));
            }
            i5++;
        }
        Logger.d("chong-----------" + this.mWeekAdapterBloodPressureHisListBean.size() + "--" + this.mMonthAdapterBloodPressureHisListBean.size());
        if (days == 7 && this.mWeekAdapterBloodPressureHisListBean.size() > 0) {
            Collections.reverse(this.mWeekAdapterBloodPressureHisListBean);
            this.mWeekAverageSBPNum = Math.round((i6 * 1.0f) / this.mWeekAdapterBloodPressureHisListBean.size());
            this.mWeekAverageDBPNum = Math.round((i7 * 1.0f) / this.mWeekAdapterBloodPressureHisListBean.size());
        } else {
            if (days != 30 || this.mMonthAdapterBloodPressureHisListBean.size() <= 0) {
                return;
            }
            Collections.reverse(this.mMonthAdapterBloodPressureHisListBean);
            this.mMonthAverageSBPNum = Math.round((i6 * 1.0f) / this.mMonthAdapterBloodPressureHisListBean.size());
            this.mMonthAverageDBPNum = Math.round((i7 * 1.0f) / this.mMonthAdapterBloodPressureHisListBean.size());
        }
    }

    public void getMonthData() {
        this.mMonthAdapterBloodPressureHisListBean.clear();
        this.mMonthChartSumUpBloodPressureHisListBean.clear();
        this.mMonthAverageDBPNum = 0;
        this.mMonthAverageSBPNum = 0;
        this.mMonthMaxDBPNum = 0;
        this.mMonthMaxSBPNum = 0;
        this.mMonthMinDBPNum = 250;
        this.mMonthMinSBPNum = 160;
        this.mViewModel.getPackedDayData(YearToDayListUtils.getPastStringArray(this.mToDay, 29).get(0), 30);
        runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.bloodpressure.activity.BloodPressureActivity.9
            @Override // java.lang.Runnable
            public void run() throws Resources.NotFoundException {
                BloodPressureActivity.this.initViewPager();
            }
        });
    }

    private void getDayBp(String dateTime) {
        HashMap map = new HashMap();
        map.put("userId", getIntent().getExtras().getString(Constant.SpConstKey.DEV_ID));
        map.put("day", dateTime);
        HttpUtils.getInstance().postMsgAsynHttp(this, com.yucheng.smarthealthpro.framework.util.Constants.bloodDayUrl, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.home.activity.bloodpressure.activity.BloodPressureActivity.10
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                if (result != null) {
                    BloodPressureActivity.this.temp_bean = (HistoryBloodResponse) new Gson().fromJson(result, HistoryBloodResponse.class);
                    BloodPressureActivity.this.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.bloodpressure.activity.BloodPressureActivity.10.1
                        @Override // java.lang.Runnable
                        public void run() throws Resources.NotFoundException {
                            BloodPressureActivity.this.setDayData();
                            BloodPressureActivity.this.initViewPager();
                        }
                    });
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDayData() {
        List list;
        int i2;
        this.mDayBloodPressureHisListBean.clear();
        this.mDayChartSumUpBloodPressureHisListBean.clear();
        HistoryBloodResponse historyBloodResponse = this.temp_bean;
        if (historyBloodResponse == null || historyBloodResponse.data == null || this.temp_bean.data == null || this.temp_bean.data.size() <= 0) {
            return;
        }
        try {
            list = (List) new Gson().fromJson(this.temp_bean.data.get(0).mlist, new TypeToken<List<HistoryBloodResponse.Mlist>>() { // from class: com.yucheng.smarthealthpro.home.activity.bloodpressure.activity.BloodPressureActivity.11
            }.getType());
        } catch (JsonSyntaxException e2) {
            e2.printStackTrace();
            list = null;
        }
        if (list == null) {
            return;
        }
        List<HistoryBloodResponse.Mlist> listSortBloodData = Tools.sortBloodData(Tools.removeBloodData(list));
        this.mDaySumUpSBPNum = 0;
        this.mDaySumUpDBPNum = 0;
        this.mDayMaxSBPNum = 0;
        this.mDayMaxDBPNum = 0;
        int i3 = 250;
        this.mDayMinSBPNum = 250;
        this.mDayMinDBPNum = 160;
        this.mDayAverageDBPNum = 0;
        this.mDayAverageSBPNum = 0;
        for (HistoryBloodResponse.Mlist mlist : listSortBloodData) {
            int i4 = mlist.sbp;
            int i5 = mlist.dbp;
            int i6 = mlist.isInflated;
            if (i5 >= 30 && i5 <= 160 && i4 >= 60 && i4 <= i3 && (i2 = i4 - i5) >= 10 && i2 <= 90) {
                if (i4 > this.mDayMaxSBPNum) {
                    this.mDayMaxDBPNum = i5;
                    this.mDayMaxSBPNum = i4;
                }
                if (i5 < this.mDayMinDBPNum) {
                    this.mDayMinSBPNum = i4;
                    this.mDayMinDBPNum = i5;
                }
                this.mDaySumUpSBPNum += i4;
                this.mDaySumUpDBPNum += i5;
                this.mDayBloodPressureHisListBean.add(new BloodPressureHisListBean(TimeStampUtils.dateForStringYearToDate(TimeStampUtils.longStampForDate(mlist.rtime)), TimeStampUtils.dateForStringToDate(TimeStampUtils.longStampForDate(mlist.rtime)), i5, i4, "正常", i6));
                this.mDayChartSumUpBloodPressureHisListBean.add(new BloodPressureHisListBean(TimeStampUtils.dateForStringYearToDate(TimeStampUtils.longStampForDate(mlist.rtime)), TimeStampUtils.dateForStringToDate(TimeStampUtils.longStampForDate(mlist.rtime)), i5, i4, "正常", i6));
                i3 = 250;
            }
        }
        if (this.mDayBloodPressureHisListBean.size() > 0) {
            this.mDayAverageDBPNum = Math.round((this.mDaySumUpDBPNum * 1.0f) / this.mDayBloodPressureHisListBean.size());
            this.mDayAverageSBPNum = Math.round((this.mDaySumUpSBPNum * 1.0f) / this.mDayBloodPressureHisListBean.size());
        }
    }

    private void getWeekBp(String dateTime, String startTime, String endTime) {
        HashMap map = new HashMap();
        map.put("userId", getIntent().getExtras().getString(Constant.SpConstKey.DEV_ID));
        map.put("day", dateTime);
        map.put("startDate", startTime);
        map.put("endDate", endTime);
        map.put("type", "1");
        HttpUtils.getInstance().postMsgAsynHttp(this, com.yucheng.smarthealthpro.framework.util.Constants.bloodDayUrl, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.home.activity.bloodpressure.activity.BloodPressureActivity.12
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                CareBpWeekMonthBean careBpWeekMonthBean;
                int i2;
                if (result != null) {
                    try {
                        careBpWeekMonthBean = (CareBpWeekMonthBean) new Gson().fromJson(result, CareBpWeekMonthBean.class);
                    } catch (JsonSyntaxException e2) {
                        e2.printStackTrace();
                        careBpWeekMonthBean = null;
                    }
                    if (careBpWeekMonthBean == null || careBpWeekMonthBean.getData() == null || careBpWeekMonthBean.getData().size() <= 0) {
                        return;
                    }
                    BloodPressureActivity.this.mWeekAverageDBPNum = 0;
                    BloodPressureActivity.this.mWeekAverageSBPNum = 0;
                    BloodPressureActivity.this.mWeekMaxDBPNum = 0;
                    BloodPressureActivity.this.mWeekMaxSBPNum = 0;
                    BloodPressureActivity.this.mWeekMinDBPNum = 160;
                    BloodPressureActivity.this.mWeekMinSBPNum = 250;
                    int i3 = 0;
                    int i4 = 0;
                    for (CareBpWeekMonthBean.DataBean dataBean : careBpWeekMonthBean.getData()) {
                        int i5 = (int) Float.parseFloat(dataBean.getSbpMean().isEmpty() ? "0" : dataBean.getSbpMean());
                        int i6 = (int) Float.parseFloat(dataBean.getDbpMean().isEmpty() ? "0" : dataBean.getDbpMean());
                        if (i6 >= 30 && i6 <= 160 && i5 >= 60 && i5 <= 250 && (i2 = i5 - i6) >= 10 && i2 <= 90) {
                            if (i5 > BloodPressureActivity.this.mWeekMaxSBPNum) {
                                BloodPressureActivity.this.mWeekMaxDBPNum = i6;
                                BloodPressureActivity.this.mWeekMaxSBPNum = i5;
                            }
                            if (i6 < BloodPressureActivity.this.mWeekMinDBPNum) {
                                BloodPressureActivity.this.mWeekMinSBPNum = i5;
                                BloodPressureActivity.this.mWeekMinDBPNum = i6;
                            }
                            i3 += i6;
                            i4 += i5;
                            BloodPressureActivity.this.mWeekAdapterBloodPressureHisListBean.add(new BloodPressureHisListBean(dataBean.getDateformat(), TimeStampUtils.dateForStringDates(TimeStampUtils.stringForDateDay(dataBean.getDateformat())), i6, i5, "正常", -1));
                        }
                    }
                    if (BloodPressureActivity.this.mWeekAdapterBloodPressureHisListBean.size() > 0) {
                        BloodPressureActivity.this.mWeekAverageDBPNum = Math.round((i3 * 1.0f) / r0.mWeekAdapterBloodPressureHisListBean.size());
                        BloodPressureActivity.this.mWeekAverageSBPNum = Math.round((i4 * 1.0f) / r0.mWeekAdapterBloodPressureHisListBean.size());
                    }
                    ArrayList<String> pastByMonthDayArray = YearToDayListUtils.getPastByMonthDayArray(BloodPressureActivity.this.mToDay, 6);
                    for (int i7 = 0; i7 < pastByMonthDayArray.size(); i7++) {
                        BloodPressureActivity.this.mWeekChartSumUpBloodPressureHisListBean.add(new BloodPressureHisListBean(pastByMonthDayArray.get(i7), pastByMonthDayArray.get(i7), 0, 0, "正常", -1));
                    }
                    for (int i8 = 0; i8 < pastByMonthDayArray.size(); i8++) {
                        for (BloodPressureHisListBean bloodPressureHisListBean : BloodPressureActivity.this.mWeekAdapterBloodPressureHisListBean) {
                            if (pastByMonthDayArray.get(i8).equals(bloodPressureHisListBean.getTime())) {
                                BloodPressureActivity.this.mWeekChartSumUpBloodPressureHisListBean.remove(i8);
                                BloodPressureActivity.this.mWeekChartSumUpBloodPressureHisListBean.add(i8, new BloodPressureHisListBean(bloodPressureHisListBean.getBpStartTime(), bloodPressureHisListBean.getTime(), bloodPressureHisListBean.getBloodDBP(), bloodPressureHisListBean.getBloodSBP(), "正常", -1));
                            }
                        }
                    }
                }
            }
        });
    }

    private void getMonthBp(String dateTime, String startTime, String endTime) {
        HashMap map = new HashMap();
        map.put("userId", getIntent().getExtras().getString(Constant.SpConstKey.DEV_ID));
        map.put("day", dateTime);
        map.put("startDate", startTime);
        map.put("endDate", endTime);
        map.put("type", "1");
        HttpUtils.getInstance().postMsgAsynHttp(this, com.yucheng.smarthealthpro.framework.util.Constants.bloodDayUrl, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.home.activity.bloodpressure.activity.BloodPressureActivity.13
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                CareBpWeekMonthBean careBpWeekMonthBean;
                int i2;
                if (result != null) {
                    try {
                        careBpWeekMonthBean = (CareBpWeekMonthBean) new Gson().fromJson(result, CareBpWeekMonthBean.class);
                    } catch (JsonSyntaxException e2) {
                        e2.printStackTrace();
                        careBpWeekMonthBean = null;
                    }
                    if (careBpWeekMonthBean == null || careBpWeekMonthBean.getData() == null || careBpWeekMonthBean.getData().size() <= 0) {
                        return;
                    }
                    BloodPressureActivity.this.mMonthAverageDBPNum = 0;
                    BloodPressureActivity.this.mMonthAverageSBPNum = 0;
                    BloodPressureActivity.this.mMonthMaxDBPNum = 0;
                    BloodPressureActivity.this.mMonthMaxSBPNum = 0;
                    BloodPressureActivity.this.mMonthMinDBPNum = 160;
                    BloodPressureActivity.this.mMonthMinSBPNum = 250;
                    int i3 = 0;
                    int i4 = 0;
                    for (CareBpWeekMonthBean.DataBean dataBean : careBpWeekMonthBean.getData()) {
                        int i5 = (int) Float.parseFloat(dataBean.getSbpMean().isEmpty() ? "0" : dataBean.getSbpMean());
                        int i6 = (int) Float.parseFloat(dataBean.getDbpMean().isEmpty() ? "0" : dataBean.getDbpMean());
                        if (i6 >= 30 && i6 <= 160 && i5 >= 60 && i5 <= 250 && (i2 = i5 - i6) >= 10 && i2 <= 90) {
                            if (i5 > BloodPressureActivity.this.mMonthMaxSBPNum) {
                                BloodPressureActivity.this.mMonthMaxDBPNum = i6;
                                BloodPressureActivity.this.mMonthMaxSBPNum = i5;
                            }
                            if (i6 < BloodPressureActivity.this.mMonthMinDBPNum) {
                                BloodPressureActivity.this.mMonthMinSBPNum = i5;
                                BloodPressureActivity.this.mMonthMinDBPNum = i6;
                            }
                            i3 += i6;
                            i4 += i5;
                            BloodPressureActivity.this.mMonthAdapterBloodPressureHisListBean.add(new BloodPressureHisListBean(dataBean.getDateformat(), TimeStampUtils.dateForStringDates(TimeStampUtils.stringForDateDay(dataBean.getDateformat())), i6, i5, "正常", -1));
                        }
                    }
                    if (BloodPressureActivity.this.mMonthAdapterBloodPressureHisListBean.size() > 0) {
                        BloodPressureActivity.this.mMonthAverageDBPNum = Math.round((i3 * 1.0f) / r0.mMonthAdapterBloodPressureHisListBean.size());
                        BloodPressureActivity.this.mMonthAverageSBPNum = Math.round((i4 * 1.0f) / r0.mMonthAdapterBloodPressureHisListBean.size());
                    }
                    ArrayList<String> pastByMonthDayArray = YearToDayListUtils.getPastByMonthDayArray(BloodPressureActivity.this.mToDay, 29);
                    for (int i7 = 0; i7 < pastByMonthDayArray.size(); i7++) {
                        BloodPressureActivity.this.mMonthChartSumUpBloodPressureHisListBean.add(new BloodPressureHisListBean(pastByMonthDayArray.get(i7), pastByMonthDayArray.get(i7), 0, 0, "正常", -1));
                    }
                    for (int i8 = 0; i8 < pastByMonthDayArray.size(); i8++) {
                        for (BloodPressureHisListBean bloodPressureHisListBean : BloodPressureActivity.this.mMonthAdapterBloodPressureHisListBean) {
                            if (pastByMonthDayArray.get(i8).equals(bloodPressureHisListBean.getTime())) {
                                BloodPressureActivity.this.mMonthChartSumUpBloodPressureHisListBean.remove(i8);
                                BloodPressureActivity.this.mMonthChartSumUpBloodPressureHisListBean.add(i8, new BloodPressureHisListBean(bloodPressureHisListBean.getBpStartTime(), bloodPressureHisListBean.getTime(), bloodPressureHisListBean.getBloodDBP(), bloodPressureHisListBean.getBloodSBP(), "正常", -1));
                            }
                        }
                    }
                }
            }
        });
    }

    public void onViewClicked(View view) throws Resources.NotFoundException {
        if (view.getId() == R.id.tv_start_button2) {
            showBloodPressureTip();
            return;
        }
        if (view.getId() == R.id.rl_plan) {
            startActivity(new Intent(this.context, (Class<?>) MeasurePlanActivity.class));
            return;
        }
        if (view.getId() == R.id.ll_calendar) {
            this.llMonth.setVisibility(0);
            return;
        }
        if (view.getId() == R.id.tv_back_today) {
            this.mViewPager.setCurrentItem(2);
            this.mCalendarView.scrollToCurrent();
            return;
        }
        if (view.getId() == R.id.tv_start_button) {
            startActivityForResult(new Intent(this.context, (Class<?>) BloodPressureMeasureActivity.class), 0);
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
                this.mNestedScrollView.postDelayed(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.bloodpressure.activity.BloodPressureActivity.14
                    @Override // java.lang.Runnable
                    public void run() {
                        BloodPressureActivity.this.mNestedScrollView.smoothScrollTo(0, (int) (BloodPressureActivity.this.mNestedScrollView.getScrollY() + (DpUtil.dp2px(BloodPressureActivity.this.context, 56.0f) * 1.5f)));
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

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) throws Resources.NotFoundException {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == -1) {
            initData();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void goMesureActivity() {
        startActivity(new Intent(this, (Class<?>) StepInstructionsActivity.class));
    }

    private void showBloodPressureTip() {
        final CommonDialog commonDialog = new CommonDialog(this);
        commonDialog.setMessage(getString(R.string.upload_server_content)).setTitle(getString(R.string.prompt)).setConfirm(getString(R.string.ok)).setCancel(getString(R.string.cancel)).setSingle(false).setOnClickBottomListener(new CommonDialog.OnClickBottomListener() { // from class: com.yucheng.smarthealthpro.home.activity.bloodpressure.activity.BloodPressureActivity.15
            @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
            public void onConfirmClick() {
                commonDialog.dismiss();
                BloodPressureActivity.this.goMesureActivity();
            }

            @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
            public void onCancelClick() {
                commonDialog.dismiss();
            }

            @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
            public void onEditTextConfirmClick(String mEditText) {
                commonDialog.dismiss();
            }
        }).show();
    }
}
