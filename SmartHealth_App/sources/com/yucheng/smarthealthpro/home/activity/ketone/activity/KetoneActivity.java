package com.yucheng.smarthealthpro.home.activity.ketone.activity;

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
import com.google.gson.reflect.TypeToken;
import com.haibin.calendarview.CalendarView;
import com.realsil.sdk.dfu.DfuConstants;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.care.bean.FriendCareKetoneBean;
import com.yucheng.smarthealthpro.data.packed.HealthDayData;
import com.yucheng.smarthealthpro.data.packed.HealthPackedData;
import com.yucheng.smarthealthpro.database.room.bean.BloodKetones;
import com.yucheng.smarthealthpro.databinding.ActivityBloodKetoneBinding;
import com.yucheng.smarthealthpro.framework.http.HttpUtils;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import com.yucheng.smarthealthpro.home.activity.HealthyActivity;
import com.yucheng.smarthealthpro.home.activity.MeasureTipActivity;
import com.yucheng.smarthealthpro.home.activity.ketone.adapter.KetoneHisListAdapter;
import com.yucheng.smarthealthpro.home.activity.ketone.adapter.KetoneTabFragmentAdapter;
import com.yucheng.smarthealthpro.home.activity.ketone.fragment.KetoneTabFragment;
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
import com.yucheng.smarthealthpro.utils.FormatUtil;
import com.yucheng.smarthealthpro.utils.ShareUtils;
import com.yucheng.smarthealthpro.utils.TimeStampUtils;
import com.yucheng.smarthealthpro.utils.Tools;
import com.yucheng.smarthealthpro.utils.TransUtils;
import com.yucheng.smarthealthpro.utils.YearToDayListUtils;
import com.yucheng.smarthealthpro.viewmodel.BloodKetonesViewModel;
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
public class KetoneActivity extends BaseVbActivity<ActivityBloodKetoneBinding> implements CalendarView.OnCalendarSelectListener, CalendarView.OnMonthChangeListener {
    private List<BloodKetones> dbs;
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
    private KetoneTabFragmentAdapter mAdapter;
    private Calendar mCalendar;
    CalendarView mCalendarView;
    private int mDayAverageNum;
    private float mDayMaxNum;
    private float mDayMinNum;
    private int mDaySumUpNum;
    private Gson mGson;
    private KetoneHisListAdapter mKetoneHisListAdapter;
    private float mLastNum;
    private int mMonthAverageNum;
    private int mMonthMaxNum;
    private int mMonthMinNum;
    private int mMonthSumUpNum;
    NestedScrollView mNestedScrollView;
    RecyclerView mRecyclerView;
    SlidingTabLayout mSlidingTabLayout;
    private String mToDay;
    private BloodKetonesViewModel mViewModel;
    NoScrollViewPager mViewPager;
    private int mWeekAverageNum;
    private int mWeekMaxNum;
    private int mWeekMinNum;
    private int mWeekSumUpNum;
    private int monthLastDay;
    RelativeLayout rlAnalyse;
    RelativeLayout rlDataFirst;
    RelativeLayout rlFirst;
    RelativeLayout rlFourthly;
    RelativeLayout rlSecond;
    private FriendCareKetoneBean temp_bean;
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
    private String unit;
    private int ARROW = 0;
    private List<String> mTitles = new ArrayList();
    private String mThatVeryDay = "";
    private List<TemperatureHisListBean> mDayHisListBean = new ArrayList();
    private List<TemperatureHisListBean> mDayChartSumUpHisListBean = new ArrayList();
    private List<TemperatureHisListBean> mWeekAdapterHisListBean = new ArrayList();
    private List<TemperatureHisListBean> mWeekChartSumUpHisListBean = new ArrayList();
    private List<TemperatureHisListBean> mMonthAdapterHisListBean = new ArrayList();
    private List<TemperatureHisListBean> mMonthChartSumUpHisListBean = new ArrayList();
    public boolean isCare = false;
    public int sex = 0;
    private final int min = 0;
    private final int max = 100;

    @Override // com.haibin.calendarview.CalendarView.OnCalendarSelectListener
    public void onCalendarOutOfRange(com.haibin.calendarview.Calendar calendar) {
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) throws Resources.NotFoundException {
        super.onCreate(savedInstanceState);
        this.mGson = new Gson();
        this.unit = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.URIC_ACID_UNIT, getString(R.string.uric_acid_unit_1));
        initView();
        initData();
    }

    private void initView() {
        this.mSlidingTabLayout = ((ActivityBloodKetoneBinding) this.mBinding).includeItemTop.stlTab;
        this.ivCalendar = ((ActivityBloodKetoneBinding) this.mBinding).includeItemTop.ivCalendar;
        this.tvCalendar = ((ActivityBloodKetoneBinding) this.mBinding).includeItemTop.tvCalendar;
        this.tvBackToday = ((ActivityBloodKetoneBinding) this.mBinding).includeItemTop.tvBackToday;
        this.llCalendar = ((ActivityBloodKetoneBinding) this.mBinding).includeItemTop.llCalendar;
        this.mViewPager = ((ActivityBloodKetoneBinding) this.mBinding).includeItemTop.vpTab;
        this.tvDataFirst = ((ActivityBloodKetoneBinding) this.mBinding).includeItemMessageData.tvDataFirst;
        this.tvDataFirstUnit = ((ActivityBloodKetoneBinding) this.mBinding).includeItemMessageData.tvDataFirstUnit;
        this.rlDataFirst = ((ActivityBloodKetoneBinding) this.mBinding).includeItemMessageData.rlDataFirst;
        this.tvDataSecond = ((ActivityBloodKetoneBinding) this.mBinding).includeItemMessageData.tvDataSecond;
        this.ivDataSecond = ((ActivityBloodKetoneBinding) this.mBinding).includeItemMessageData.ivDataSecond;
        this.tvDataSecondUnit = ((ActivityBloodKetoneBinding) this.mBinding).includeItemMessageData.tvDataSecondUnit;
        this.llDataSecond = ((ActivityBloodKetoneBinding) this.mBinding).includeItemMessageData.llDataSecond;
        this.tvDataThirdly = ((ActivityBloodKetoneBinding) this.mBinding).includeItemMessageData.tvDataThirdly;
        this.ivDataThirdly = ((ActivityBloodKetoneBinding) this.mBinding).includeItemMessageData.ivDataThirdly;
        this.tvDataThirdlyUnit = ((ActivityBloodKetoneBinding) this.mBinding).includeItemMessageData.tvDataThirdlyUnit;
        this.llDataThirdly = ((ActivityBloodKetoneBinding) this.mBinding).includeItemMessageData.llDataThirdly;
        this.tvStartButton = ((ActivityBloodKetoneBinding) this.mBinding).includeItemBottom.tvStartButton;
        this.llStartButton = ((ActivityBloodKetoneBinding) this.mBinding).includeItemBottom.llStartButton;
        this.tvAnalyse = ((ActivityBloodKetoneBinding) this.mBinding).includeItemBottom.tvAnalyse;
        this.tvAnalyseData = ((ActivityBloodKetoneBinding) this.mBinding).includeItemBottom.tvAnalyseData;
        this.rlAnalyse = ((ActivityBloodKetoneBinding) this.mBinding).includeItemBottom.rlAnalyse;
        this.ivFirstLeft = ((ActivityBloodKetoneBinding) this.mBinding).includeItemBottom.ivFirstLeft;
        this.tvFirst = ((ActivityBloodKetoneBinding) this.mBinding).includeItemBottom.tvFirst;
        this.ivFirstRight = ((ActivityBloodKetoneBinding) this.mBinding).includeItemBottom.ivFirstRight;
        this.rlFirst = ((ActivityBloodKetoneBinding) this.mBinding).includeItemBottom.rlFirst;
        this.ivSecondLeft = ((ActivityBloodKetoneBinding) this.mBinding).includeItemBottom.ivSecondLeft;
        this.tvSecond = ((ActivityBloodKetoneBinding) this.mBinding).includeItemBottom.tvSecond;
        this.ivSecondRight = ((ActivityBloodKetoneBinding) this.mBinding).includeItemBottom.ivSecondRight;
        this.rlSecond = ((ActivityBloodKetoneBinding) this.mBinding).includeItemBottom.rlSecond;
        this.ivFourthlyLeft = ((ActivityBloodKetoneBinding) this.mBinding).includeItemBottom.ivFourthlyLeft;
        this.tvFourthly = ((ActivityBloodKetoneBinding) this.mBinding).includeItemBottom.tvFourthly;
        this.ivFourthlyRight = ((ActivityBloodKetoneBinding) this.mBinding).includeItemBottom.ivFourthlyRight;
        this.rlFourthly = ((ActivityBloodKetoneBinding) this.mBinding).includeItemBottom.rlFourthly;
        this.mRecyclerView = ((ActivityBloodKetoneBinding) this.mBinding).includeItemBottom.recycleView;
        this.mNestedScrollView = ((ActivityBloodKetoneBinding) this.mBinding).nsv;
        this.tvYears = ((ActivityBloodKetoneBinding) this.mBinding).includeItemCalendar.tvYears;
        this.mCalendarView = ((ActivityBloodKetoneBinding) this.mBinding).includeItemCalendar.calendarView;
        this.llMonth = ((ActivityBloodKetoneBinding) this.mBinding).includeItemCalendar.llMonth;
        this.llCalendar.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.ketone.activity.KetoneActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.tvBackToday.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.ketone.activity.KetoneActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.llStartButton.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.ketone.activity.KetoneActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlAnalyse.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.ketone.activity.KetoneActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlFirst.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.ketone.activity.KetoneActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlSecond.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.ketone.activity.KetoneActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlFourthly.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.ketone.activity.KetoneActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.llMonth.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.ketone.activity.KetoneActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        changeTitle(getString(R.string.blood_ketones));
        showBack();
        showRightImage(R.mipmap.topbar_ic_share, new NavigationBar.MyOnClickListener() { // from class: com.yucheng.smarthealthpro.home.activity.ketone.activity.KetoneActivity$$ExternalSyntheticLambda1
            @Override // com.yucheng.smarthealthpro.framework.view.NavigationBar.MyOnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initView$0(view);
            }
        });
        int statusHeight = AppScreenMgr.getStatusHeight(this.context);
        this.llMonth.setPadding(0, DensityUtils.dip2px(this.context, 50.0f) + statusHeight, 0, 0);
        if (Constant.isTechFeel()) {
            this.rlSecond.setVisibility(8);
        }
        String stringExtra = getIntent().getStringExtra("care");
        if (stringExtra == null || !stringExtra.equals(getString(R.string.care_title))) {
            this.sex = ((Integer) SharedPreferencesUtils.get(this, Constant.SpConstKey.SEX, 0)).intValue();
            this.rlFirst.setVisibility(0);
            if (!YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASTESTSPO2)) {
                this.llStartButton.setVisibility(8);
            }
            this.tvStartButton.setText(getString(R.string.home_blood_ketones_measure_title));
            this.tvFirst.setText(getString(R.string.include_bottom_tv_first_button));
            this.tvSecond.setText(getString(R.string.include_bottom_tv_second_button));
            this.tvAnalyse.setText(getString(R.string.blood_ketones_analyse));
            this.tvFourthly.setText(getString(R.string.include_bottom_tv_fourthly_button));
            this.ivFourthlyRight.setBackground(ResourcesCompat.getDrawable(getResources(), R.mipmap.list_ic_arrow_n, null));
            return;
        }
        this.isCare = true;
        this.sex = getIntent().getIntExtra(Constant.SpConstKey.SEX, 0);
        this.tvAnalyse.setText(getString(R.string.blood_ketones_analyse));
        this.llStartButton.setVisibility(8);
        this.rlFirst.setVisibility(8);
        this.rlSecond.setVisibility(8);
        this.ivFourthlyRight.setBackground(ResourcesCompat.getDrawable(getResources(), R.mipmap.list_ic_arrow_n, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initView$0(View view) {
        if (checkCanClick()) {
            ShareUtils.share(this);
        }
    }

    private void initViewModel() {
        this.mViewModel = (BloodKetonesViewModel) new ViewModelProvider(this).get(BloodKetonesViewModel.class);
        FlowUtils.INSTANCE.collectFlow(this, this.mViewModel.getBloodKetonesDataFlow(), new FlowUtils.FlowCollector<HealthDayData<BloodKetones>>() { // from class: com.yucheng.smarthealthpro.home.activity.ketone.activity.KetoneActivity.1
            @Override // com.yucheng.smarthealthpro.utils.FlowUtils.FlowCollector
            public void collect(HealthDayData<BloodKetones> data) throws Resources.NotFoundException {
                KetoneActivity.this.onThatVeryDayData(data.getDay(), data.getData());
            }
        });
        FlowUtils.INSTANCE.collectFlow(this, this.mViewModel.getBloodKetonesPackedDataFlow(), new FlowUtils.FlowCollector<HealthPackedData<BloodKetones>>() { // from class: com.yucheng.smarthealthpro.home.activity.ketone.activity.KetoneActivity.2
            @Override // com.yucheng.smarthealthpro.utils.FlowUtils.FlowCollector
            public void collect(HealthPackedData<BloodKetones> packedData) {
                if (packedData.getDayCount() == 7) {
                    KetoneActivity.this.onDataByDays(7, packedData.getData());
                } else if (packedData.getDayCount() == 30) {
                    KetoneActivity.this.onDataByDays(30, packedData.getData());
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
        if (this.isCare) {
            ArrayList<String> pastStringArray = YearToDayListUtils.getPastStringArray(this.mToDay, 6);
            ArrayList<String> pastStringArray2 = YearToDayListUtils.getPastStringArray(this.mToDay, 29);
            getWeek(pastStringArray.get(0), pastStringArray.get(0), pastStringArray.get(6));
            getMonth(pastStringArray2.get(0), pastStringArray2.get(0), pastStringArray2.get(29));
            return;
        }
        new Thread(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.ketone.activity.KetoneActivity.3
            @Override // java.lang.Runnable
            public void run() {
                KetoneActivity.this.getWeekData();
                KetoneActivity.this.getMonthData();
            }
        }).start();
    }

    private void getWeek(String dateTime, String startTime, String endTime) {
        this.mWeekAdapterHisListBean.clear();
        this.mWeekChartSumUpHisListBean.clear();
        HashMap map = new HashMap();
        map.put("userId", getIntent().getExtras().getString(Constant.SpConstKey.DEV_ID));
        map.put("day", dateTime);
        map.put("startDate", startTime);
        map.put("endDate", endTime);
        map.put("type", "1");
        HttpUtils.getInstance().postMsgAsynHttp(this, com.yucheng.smarthealthpro.framework.util.Constants.ketoneDayUrl, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.home.activity.ketone.activity.KetoneActivity.4
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                FriendCareKetoneBean friendCareKetoneBean;
                if (result != null) {
                    new ArrayList();
                    try {
                        friendCareKetoneBean = (FriendCareKetoneBean) KetoneActivity.this.mGson.fromJson(result, FriendCareKetoneBean.class);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        friendCareKetoneBean = null;
                    }
                    if (friendCareKetoneBean == null) {
                        return;
                    }
                    KetoneActivity.this.mWeekSumUpNum = 0;
                    KetoneActivity.this.mWeekMaxNum = 0;
                    KetoneActivity.this.mWeekMinNum = DfuConstants.MAX_NOTIFICATION_LOCK_WAIT_TIME;
                    ArrayList<String> pastStringArray = YearToDayListUtils.getPastStringArray(KetoneActivity.this.mToDay, 6);
                    Collections.sort(friendCareKetoneBean.data);
                    for (int i2 = 0; i2 < friendCareKetoneBean.data.size(); i2++) {
                        FriendCareKetoneBean.Data data = friendCareKetoneBean.data.get(i2);
                        List list = (List) KetoneActivity.this.mGson.fromJson(data.mlist, new TypeToken<List<FriendCareKetoneBean.BloodKetone>>() { // from class: com.yucheng.smarthealthpro.home.activity.ketone.activity.KetoneActivity.4.1
                        }.getType());
                        if (list != null && list.size() != 0) {
                            Tools.removeCareListBloodKetone(list);
                            Tools.sortCareListBloodKetone(list);
                            float f2 = 0.0f;
                            for (int i3 = 0; i3 < list.size(); i3++) {
                                f2 += ((FriendCareKetoneBean.BloodKetone) list.get(i3)).bloodKetone;
                            }
                            int iRound = Math.round(f2 / list.size());
                            if (KetoneActivity.this.mWeekMaxNum < iRound) {
                                KetoneActivity.this.mWeekMaxNum = iRound;
                            }
                            if (KetoneActivity.this.mWeekMinNum > iRound) {
                                KetoneActivity.this.mWeekMinNum = iRound;
                            }
                            KetoneActivity.this.mWeekSumUpNum += iRound;
                            float f3 = iRound;
                            KetoneActivity.this.mWeekAdapterHisListBean.add(new TemperatureHisListBean(data.dateformat, KetoneActivity.this.transform(f3), KetoneActivity.this.getState(f3), KetoneActivity.this.unit));
                        }
                    }
                    for (int i4 = 0; i4 < pastStringArray.size(); i4++) {
                        KetoneActivity.this.mWeekChartSumUpHisListBean.add(new TemperatureHisListBean(pastStringArray.get(i4), "0", "", KetoneActivity.this.unit));
                    }
                    for (int i5 = 0; i5 < pastStringArray.size(); i5++) {
                        for (TemperatureHisListBean temperatureHisListBean : KetoneActivity.this.mWeekAdapterHisListBean) {
                            if (pastStringArray.get(i5).equals(temperatureHisListBean.getTime())) {
                                KetoneActivity.this.mWeekChartSumUpHisListBean.remove(i5);
                                KetoneActivity.this.mWeekChartSumUpHisListBean.add(i5, new TemperatureHisListBean(temperatureHisListBean.getTime(), temperatureHisListBean.getmValue(), temperatureHisListBean.getState(), temperatureHisListBean.unit));
                            }
                        }
                    }
                    if (friendCareKetoneBean.data.size() > 0) {
                        KetoneActivity.this.mWeekAverageNum = Math.round((r0.mWeekSumUpNum * 1.0f) / friendCareKetoneBean.data.size());
                    }
                }
            }
        });
    }

    private void getMonth(String dateTime, String startTime, String endTime) {
        HashMap map = new HashMap();
        map.put("userId", getIntent().getExtras().getString(Constant.SpConstKey.DEV_ID));
        map.put("day", dateTime);
        map.put("startDate", startTime);
        map.put("endDate", endTime);
        map.put("type", "1");
        HttpUtils.getInstance().postMsgAsynHttp(this, com.yucheng.smarthealthpro.framework.util.Constants.ketoneDayUrl, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.home.activity.ketone.activity.KetoneActivity.5
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                FriendCareKetoneBean friendCareKetoneBean;
                if (result != null) {
                    new ArrayList();
                    try {
                        friendCareKetoneBean = (FriendCareKetoneBean) KetoneActivity.this.mGson.fromJson(result, FriendCareKetoneBean.class);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        friendCareKetoneBean = null;
                    }
                    if (friendCareKetoneBean == null) {
                        return;
                    }
                    KetoneActivity.this.mMonthAdapterHisListBean.clear();
                    KetoneActivity.this.mMonthChartSumUpHisListBean.clear();
                    KetoneActivity.this.mMonthMaxNum = 0;
                    KetoneActivity.this.mMonthMinNum = 100;
                    KetoneActivity.this.mMonthAverageNum = 0;
                    KetoneActivity.this.mLastNum = 0.0f;
                    ArrayList<String> pastStringArray = YearToDayListUtils.getPastStringArray(KetoneActivity.this.mToDay, 29);
                    Collections.sort(friendCareKetoneBean.data);
                    for (int i2 = 0; i2 < friendCareKetoneBean.data.size(); i2++) {
                        FriendCareKetoneBean.Data data = friendCareKetoneBean.data.get(i2);
                        List list = (List) KetoneActivity.this.mGson.fromJson(data.mlist, new TypeToken<List<FriendCareKetoneBean.BloodKetone>>() { // from class: com.yucheng.smarthealthpro.home.activity.ketone.activity.KetoneActivity.5.1
                        }.getType());
                        if (list != null && list.size() != 0) {
                            Tools.removeCareListBloodKetone(list);
                            Tools.sortCareListBloodKetone(list);
                            float f2 = 0.0f;
                            for (int i3 = 0; i3 < list.size(); i3++) {
                                f2 += ((FriendCareKetoneBean.BloodKetone) list.get(i3)).bloodKetone;
                            }
                            int iRound = Math.round(f2 / list.size());
                            if (KetoneActivity.this.mMonthMaxNum < iRound) {
                                KetoneActivity.this.mMonthMaxNum = iRound;
                            }
                            if (KetoneActivity.this.mMonthMinNum > iRound) {
                                KetoneActivity.this.mMonthMinNum = iRound;
                            }
                            KetoneActivity.this.mMonthSumUpNum += iRound;
                            float f3 = iRound;
                            KetoneActivity.this.mMonthAdapterHisListBean.add(new TemperatureHisListBean(data.dateformat, KetoneActivity.this.transform(f3), KetoneActivity.this.getState(f3), KetoneActivity.this.unit));
                        }
                    }
                    for (int i4 = 0; i4 < pastStringArray.size(); i4++) {
                        KetoneActivity.this.mMonthChartSumUpHisListBean.add(new TemperatureHisListBean(pastStringArray.get(i4), "0", "", KetoneActivity.this.unit));
                    }
                    for (int i5 = 0; i5 < pastStringArray.size(); i5++) {
                        for (TemperatureHisListBean temperatureHisListBean : KetoneActivity.this.mMonthAdapterHisListBean) {
                            if (pastStringArray.get(i5).equals(temperatureHisListBean.getTime())) {
                                KetoneActivity.this.mMonthChartSumUpHisListBean.remove(i5);
                                KetoneActivity.this.mMonthChartSumUpHisListBean.add(i5, new TemperatureHisListBean(temperatureHisListBean.getTime(), temperatureHisListBean.getmValue(), temperatureHisListBean.getState(), temperatureHisListBean.unit));
                            }
                        }
                    }
                    if (friendCareKetoneBean.data.size() > 0) {
                        KetoneActivity.this.mMonthAverageNum = Math.round((r0.mMonthSumUpNum * 1.0f) / friendCareKetoneBean.data.size());
                    }
                }
            }
        });
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
        KetoneTabFragmentAdapter ketoneTabFragmentAdapter = new KetoneTabFragmentAdapter(getSupportFragmentManager(), new KetoneTabFragmentAdapter.FragmentCreator() { // from class: com.yucheng.smarthealthpro.home.activity.ketone.activity.KetoneActivity.6
            @Override // com.yucheng.smarthealthpro.home.activity.ketone.adapter.KetoneTabFragmentAdapter.FragmentCreator
            public Fragment createFragment(String data, int position) {
                return KetoneTabFragment.newInstance(data.toString(), position, KetoneActivity.this.mNestedScrollView, KetoneActivity.this.mDayChartSumUpHisListBean, KetoneActivity.this.mWeekChartSumUpHisListBean, KetoneActivity.this.mMonthChartSumUpHisListBean, KetoneActivity.this.mDayMaxNum);
            }

            @Override // com.yucheng.smarthealthpro.home.activity.ketone.adapter.KetoneTabFragmentAdapter.FragmentCreator
            public String createTitle(String data) {
                return Html.fromHtml(data).toString();
            }
        });
        this.mAdapter = ketoneTabFragmentAdapter;
        this.mViewPager.setAdapter(ketoneTabFragmentAdapter);
        this.mViewPager.setOffscreenPageLimit(this.mTitles.size() - 1);
        this.mAdapter.setData(this.mTitles);
        this.mSlidingTabLayout.setViewPager(this.mViewPager, (String[]) this.mTitles.toArray(new String[0]));
        this.mSlidingTabLayout.setCurrentTab(2);
        this.mViewPager.addOnPageChangeListener(new OnPageChangeListenerImpl());
    }

    public void dataAnalysis(float value) {
        float f2 = KetoneHisListAdapter.LOW;
        float f3 = KetoneHisListAdapter.HIGH;
        if (value >= f2 && value <= f3) {
            this.tvAnalyseData.setText(getText(R.string.blood_ketones_normal));
        } else if (value < f2) {
            this.tvAnalyseData.setText(getText(R.string.blood_ketones_lower));
        } else {
            this.tvAnalyseData.setText(getText(R.string.blood_ketones_higher));
        }
    }

    private void setRecycleView() {
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        KetoneHisListAdapter ketoneHisListAdapter = new KetoneHisListAdapter(R.layout.item_universal_his_list);
        this.mKetoneHisListAdapter = ketoneHisListAdapter;
        ketoneHisListAdapter.addData((Collection) this.mDayHisListBean);
        this.mRecyclerView.setAdapter(this.mKetoneHisListAdapter);
        this.mRecyclerView.setNestedScrollingEnabled(false);
    }

    private void initMonth() throws Resources.NotFoundException {
        this.mCalendarView.setOnCalendarSelectListener(this);
        this.mCalendarView.setOnMonthChangeListener(this);
        this.mCalendarView.scrollToCurrent();
        this.mCalendarView.setOnCalendarInterceptListener(new CalendarView.OnCalendarInterceptListener() { // from class: com.yucheng.smarthealthpro.home.activity.ketone.activity.KetoneActivity.7
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
        if (this.isCare) {
            getDay(strIntToStr);
        } else {
            this.mViewModel.getDayData(strIntToStr);
        }
        this.tvCalendar.setText(calendar.getMonth() + "/" + calendar.getDay());
        this.llMonth.setVisibility(8);
    }

    private void getDay(String dateTime) {
        HashMap map = new HashMap();
        map.put("userId", getIntent().getExtras().getString(Constant.SpConstKey.DEV_ID));
        map.put("day", dateTime);
        HttpUtils.getInstance().postMsgAsynHttp(this, com.yucheng.smarthealthpro.framework.util.Constants.ketoneDayUrl, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.home.activity.ketone.activity.KetoneActivity.8
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                if (result != null) {
                    try {
                        KetoneActivity ketoneActivity = KetoneActivity.this;
                        ketoneActivity.temp_bean = (FriendCareKetoneBean) ketoneActivity.mGson.fromJson(result, FriendCareKetoneBean.class);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    KetoneActivity.this.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.ketone.activity.KetoneActivity.8.1
                        @Override // java.lang.Runnable
                        public void run() throws Resources.NotFoundException {
                            KetoneActivity.this.setDayData();
                            KetoneActivity.this.initViewPager();
                        }
                    });
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v14, types: [java.util.List] */
    public void setDayData() {
        List<TemperatureHisListBean> list = this.mDayHisListBean;
        if (list != null) {
            list.clear();
        }
        List<TemperatureHisListBean> list2 = this.mDayChartSumUpHisListBean;
        if (list2 != null) {
            list2.clear();
        }
        this.mDaySumUpNum = 0;
        this.mDayMaxNum = 0.0f;
        this.mDayMinNum = 100.0f;
        if (this.temp_bean == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        try {
            Iterator<FriendCareKetoneBean.Data> it2 = this.temp_bean.data.iterator();
            while (it2.hasNext()) {
                arrayList = (List) new Gson().fromJson(it2.next().mlist, new TypeToken<List<FriendCareKetoneBean.BloodKetone>>() { // from class: com.yucheng.smarthealthpro.home.activity.ketone.activity.KetoneActivity.9
                }.getType());
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        Tools.removeCareListBloodKetone(arrayList);
        Tools.sortCareListBloodKetone(arrayList);
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            float f2 = ((FriendCareKetoneBean.BloodKetone) arrayList.get(i2)).bloodKetone;
            if (f2 > this.mDayMaxNum) {
                this.mDayMaxNum = f2;
            }
            if (f2 < this.mDayMinNum) {
                this.mDayMinNum = f2;
            }
            this.mDaySumUpNum = (int) (this.mDaySumUpNum + f2);
            String strTransform = transform(f2);
            TemperatureHisListBean temperatureHisListBean = new TemperatureHisListBean(TimeStampUtils.dateForStringToDate(TimeStampUtils.longStampForDate(((FriendCareKetoneBean.BloodKetone) arrayList.get(i2)).rtime)), strTransform, getState(f2));
            temperatureHisListBean.setModel(((FriendCareKetoneBean.BloodKetone) arrayList.get(i2)).cMode);
            this.mDayHisListBean.add(temperatureHisListBean);
            TemperatureHisListBean temperatureHisListBean2 = new TemperatureHisListBean(TimeStampUtils.dateForStringToDate(TimeStampUtils.longStampForDate(((FriendCareKetoneBean.BloodKetone) arrayList.get(i2)).rtime)), strTransform, getState(f2), this.unit);
            temperatureHisListBean2.setModel(((FriendCareKetoneBean.BloodKetone) arrayList.get(i2)).cMode);
            this.mDayChartSumUpHisListBean.add(temperatureHisListBean2);
        }
        if (this.mDayHisListBean.size() != 0) {
            this.mDayAverageNum = Math.round((this.mDaySumUpNum * 1.0f) / this.mDayHisListBean.size());
        }
    }

    @Override // com.haibin.calendarview.CalendarView.OnMonthChangeListener
    public void onMonthChange(int year, int month) {
        if (Constant.isTechFeel()) {
            this.tvYears.setText(String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(month)) + "/" + year);
        } else {
            this.tvYears.setText(year + "/" + String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(month)));
        }
    }

    public void onThatVeryDayData(String thatVeryDay, List<BloodKetones> data) throws Resources.NotFoundException {
        this.mDayHisListBean.clear();
        this.mDayChartSumUpHisListBean.clear();
        this.dbs = data;
        this.mThatVeryDay = thatVeryDay;
        this.mDaySumUpNum = 0;
        this.mDayMaxNum = 0.0f;
        this.mDayMinNum = 100.0f;
        this.mDayAverageNum = 0;
        this.mLastNum = 0.0f;
        if (data != null) {
            for (int i2 = 0; i2 < this.dbs.size(); i2++) {
                BloodKetones bloodKetones = this.dbs.get(i2);
                if (TimeStampUtils.dateForStringYearToDate(TimeStampUtils.longStampForDate(bloodKetones.getStartTimestamp())).equals(thatVeryDay) && bloodKetones.getBloodKetones() != 0.0f) {
                    float bloodKetones2 = bloodKetones.getBloodKetones();
                    if (bloodKetones2 >= TransUtils.KETONE_VISIBLE_MIN && bloodKetones2 <= TransUtils.KETONE_VISIBLE_MAX) {
                        TemperatureHisListBean temperatureHisListBean = new TemperatureHisListBean(TimeStampUtils.dateForStringToDate(TimeStampUtils.longStampForDate(bloodKetones.getStartTimestamp())), transform(bloodKetones2), "", this.unit);
                        temperatureHisListBean.setModel(bloodKetones.getMeasureMode());
                        this.mDayHisListBean.add(temperatureHisListBean);
                        this.mDayChartSumUpHisListBean.add(temperatureHisListBean);
                        if (this.mLastNum == 0.0f) {
                            this.mLastNum = bloodKetones2;
                        }
                        if (bloodKetones2 > this.mDayMaxNum) {
                            this.mDayMaxNum = bloodKetones2;
                        }
                        if (bloodKetones2 < this.mDayMinNum) {
                            this.mDayMinNum = bloodKetones2;
                        }
                        this.mDaySumUpNum = (int) (this.mDaySumUpNum + bloodKetones2);
                    }
                }
            }
        }
        if (this.mDayHisListBean.size() != 0) {
            this.mDayAverageNum = Math.round((this.mDaySumUpNum * 1.0f) / this.mDayHisListBean.size());
        }
        initViewPager();
    }

    public void getWeekData() {
        this.mWeekAdapterHisListBean.clear();
        this.mWeekChartSumUpHisListBean.clear();
        this.mWeekMaxNum = 0;
        this.mWeekMinNum = DfuConstants.MAX_NOTIFICATION_LOCK_WAIT_TIME;
        this.mWeekAverageNum = 0;
        this.mLastNum = 0.0f;
        this.mViewModel.getPackedDayData(YearToDayListUtils.getPastStringArray(this.mToDay, 6).get(0), 7);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onDataByDays(int days, List<BloodKetones> data) {
        int size;
        int i2;
        float f2;
        ArrayList<String> pastStringArray = YearToDayListUtils.getPastStringArray(this.mToDay, days - 1);
        int i3 = 0;
        for (int i4 = 0; i4 < days; i4++) {
            List<BloodKetones> listFilterBloodKetonesByDate = HealthDataFilterKt.filterBloodKetonesByDate(data, pastStringArray.get(i4));
            this.dbs = listFilterBloodKetonesByDate;
            if (listFilterBloodKetonesByDate != null) {
                size = listFilterBloodKetonesByDate.size();
                int i5 = 0;
                i2 = 0;
                f2 = 0.0f;
                while (i5 < this.dbs.size()) {
                    BloodKetones bloodKetones = this.dbs.get(i5);
                    int measureMode = bloodKetones.getMeasureMode();
                    float bloodKetones2 = bloodKetones.getBloodKetones();
                    if (bloodKetones2 < TransUtils.KETONE_VISIBLE_MIN || bloodKetones2 > TransUtils.KETONE_VISIBLE_MAX) {
                        size--;
                    } else {
                        f2 += bloodKetones2;
                    }
                    i5++;
                    i2 = measureMode;
                }
            } else {
                size = 0;
                i2 = 0;
                f2 = 0.0f;
            }
            if (f2 != 0.0f) {
                float f3 = f2 / size;
                i3 = (int) (i3 + f3);
                if (this.mLastNum == 0.0f) {
                    this.mLastNum = (int) f3;
                }
                if (days == 7) {
                    if (f3 > this.mWeekMaxNum) {
                        this.mWeekMaxNum = Math.round(f3);
                    }
                    if (f3 < this.mWeekMinNum) {
                        this.mWeekMinNum = Math.round(f3);
                    }
                    TemperatureHisListBean temperatureHisListBean = new TemperatureHisListBean(pastStringArray.get(i4), transform(f3), "", this.unit);
                    temperatureHisListBean.setModel(i2);
                    this.mWeekAdapterHisListBean.add(temperatureHisListBean);
                    TemperatureHisListBean temperatureHisListBean2 = new TemperatureHisListBean(TimeStampUtils.toFormatDate(pastStringArray.get(i4)), "0", "", this.unit);
                    temperatureHisListBean2.setModel(i2);
                    this.mWeekChartSumUpHisListBean.add(temperatureHisListBean2);
                } else if (days == 30) {
                    if (f3 > this.mMonthMaxNum) {
                        this.mMonthMaxNum = Math.round(f3);
                    }
                    if (f3 < this.mMonthMinNum) {
                        this.mMonthMinNum = Math.round(f3);
                    }
                    TemperatureHisListBean temperatureHisListBean3 = new TemperatureHisListBean(pastStringArray.get(i4), transform(f3), "", this.unit);
                    temperatureHisListBean3.setModel(i2);
                    this.mMonthAdapterHisListBean.add(temperatureHisListBean3);
                    TemperatureHisListBean temperatureHisListBean4 = new TemperatureHisListBean(TimeStampUtils.toFormatDate(pastStringArray.get(i4)), "0", "", this.unit);
                    temperatureHisListBean4.setModel(i2);
                    this.mMonthChartSumUpHisListBean.add(temperatureHisListBean4);
                }
            } else if (days == 7) {
                TemperatureHisListBean temperatureHisListBean5 = new TemperatureHisListBean(pastStringArray.get(i4), "0", "", this.unit);
                temperatureHisListBean5.setModel(i2);
                this.mWeekChartSumUpHisListBean.add(temperatureHisListBean5);
            } else if (days == 30) {
                TemperatureHisListBean temperatureHisListBean6 = new TemperatureHisListBean(TimeStampUtils.toFormatDate(pastStringArray.get(i4)), "0", "", this.unit);
                temperatureHisListBean6.setModel(i2);
                this.mMonthChartSumUpHisListBean.add(temperatureHisListBean6);
            }
        }
        if (i3 != 0) {
            if (days == 7 && this.mWeekAdapterHisListBean.size() > 0) {
                Collections.reverse(this.mWeekAdapterHisListBean);
                this.mWeekAverageNum = Math.round((i3 * 1.0f) / this.mWeekAdapterHisListBean.size());
            } else {
                if (days != 30 || this.mMonthAdapterHisListBean.size() <= 0) {
                    return;
                }
                Collections.reverse(this.mMonthAdapterHisListBean);
                this.mMonthAverageNum = Math.round((i3 * 1.0f) / this.mMonthAdapterHisListBean.size());
            }
        }
    }

    public void getMonthData() {
        this.mMonthAdapterHisListBean.clear();
        this.mMonthChartSumUpHisListBean.clear();
        this.mMonthMaxNum = 0;
        this.mMonthMinNum = 100;
        this.mMonthAverageNum = 0;
        this.mLastNum = 0.0f;
        this.mViewModel.getPackedDayData(YearToDayListUtils.getPastStringArray(this.mToDay, 29).get(0), 30);
        runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.ketone.activity.KetoneActivity.10
            @Override // java.lang.Runnable
            public void run() throws Resources.NotFoundException {
                KetoneActivity.this.initViewPager();
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
                KetoneMeasureActivity.load(this);
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
                this.mNestedScrollView.postDelayed(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.ketone.activity.KetoneActivity.11
                    @Override // java.lang.Runnable
                    public void run() {
                        KetoneActivity.this.mNestedScrollView.smoothScrollTo(0, (int) (KetoneActivity.this.mNestedScrollView.getScrollY() + (DpUtil.dp2px(KetoneActivity.this.context, 56.0f) * 1.5f)));
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
        if (requestCode == KetoneMeasureActivity.URICACID_MEASURE && resultCode == -1) {
            initData();
        }
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity
    public void onActivityResult(ActivityResult result, int requestCode) throws Resources.NotFoundException {
        super.onActivityResult(result, requestCode);
        if (requestCode == KetoneMeasureActivity.URICACID_MEASURE && result.getResultCode() == -1) {
            initData();
        } else if (requestCode == MeasureTipActivity.MEASURE_TIP) {
            launchActivityForResult(KetoneMeasureActivity.URICACID_MEASURE, KetoneMeasureActivity.class);
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
            KetoneActivity.this.mViewPager.setCurrentItem(position);
            if (position == 0) {
                KetoneActivity.this.freshMonthData();
            } else if (position == 1) {
                KetoneActivity.this.freshWeekData();
            } else {
                if (position != 2) {
                    return;
                }
                KetoneActivity.this.freshDayData();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void freshDayData() {
        this.tvBackToday.setVisibility(8);
        this.llCalendar.setVisibility(0);
        List<TemperatureHisListBean> list = this.mDayHisListBean;
        if (list != null && list.size() > 0) {
            if (Constant.isTechFeel()) {
                this.tvDataFirst.setText(this.mLastNum + "");
            } else {
                this.tvDataFirst.setText(transform(this.mDayAverageNum));
            }
            this.tvDataSecond.setText(transform(this.mDayMaxNum));
            this.tvDataThirdly.setText(transform(this.mDayMinNum));
        } else {
            this.tvDataFirst.setText("--");
            this.tvDataSecond.setText("--");
            this.tvDataThirdly.setText("--");
            this.mDayAverageNum = 0;
        }
        this.mKetoneHisListAdapter.setList(this.mDayHisListBean);
        this.mKetoneHisListAdapter.notifyDataSetChanged();
        dataAnalysis(this.mDayAverageNum);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void freshWeekData() {
        this.tvBackToday.setVisibility(0);
        this.llCalendar.setVisibility(8);
        List<TemperatureHisListBean> list = this.mWeekAdapterHisListBean;
        if (list != null && list.size() > 0) {
            if (Constant.isTechFeel()) {
                this.tvDataFirst.setText(this.mLastNum + "");
            } else {
                this.tvDataFirst.setText(transform(this.mWeekAverageNum));
            }
            this.tvDataSecond.setText(transform(this.mWeekMaxNum));
            this.tvDataThirdly.setText(transform(this.mWeekMinNum));
        } else {
            this.tvDataFirst.setText("--");
            this.tvDataSecond.setText("--");
            this.tvDataThirdly.setText("--");
            this.mWeekAverageNum = 0;
        }
        this.mKetoneHisListAdapter.setList(this.mWeekAdapterHisListBean);
        this.mKetoneHisListAdapter.notifyDataSetChanged();
        dataAnalysis(this.mWeekAverageNum);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void freshMonthData() {
        this.tvBackToday.setVisibility(0);
        this.llCalendar.setVisibility(8);
        List<TemperatureHisListBean> list = this.mMonthAdapterHisListBean;
        if (list != null && list.size() > 0) {
            if (Constant.isTechFeel()) {
                this.tvDataFirst.setText(this.mLastNum + "");
            } else {
                this.tvDataFirst.setText(transform(this.mMonthAverageNum));
            }
            this.tvDataSecond.setText(transform(this.mMonthMaxNum));
            this.tvDataThirdly.setText(transform(this.mMonthMinNum));
        } else {
            this.tvDataFirst.setText("--");
            this.tvDataSecond.setText("--");
            this.tvDataThirdly.setText("--");
            this.mMonthAverageNum = 0;
        }
        this.mKetoneHisListAdapter.setList(this.mMonthAdapterHisListBean);
        this.mKetoneHisListAdapter.notifyDataSetChanged();
        dataAnalysis(this.mMonthAverageNum);
    }

    public String getState(float value) {
        float f2 = KetoneHisListAdapter.LOW;
        float f3 = KetoneHisListAdapter.HIGH;
        if (value < f2) {
            return getText(R.string.blood_ketones_lower).toString();
        }
        if (value > f3) {
            return getText(R.string.blood_ketones_higher).toString();
        }
        return getText(R.string.blood_ketones_normal).toString();
    }

    public String transform(float value) {
        if (getString(R.string.uric_acid_unit_2).equals(this.unit)) {
            return FormatUtil.keep1(value * TransUtils.URIC_ACID_TRANS);
        }
        return ((int) value) + "";
    }
}
