package com.yucheng.smarthealthpro.home.activity.uricacid.activity;

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
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.care.bean.FriendCareUricAcidBean;
import com.yucheng.smarthealthpro.data.packed.HealthDayData;
import com.yucheng.smarthealthpro.data.packed.HealthPackedData;
import com.yucheng.smarthealthpro.database.room.bean.UricAcid;
import com.yucheng.smarthealthpro.databinding.ActivityUricAcidBinding;
import com.yucheng.smarthealthpro.framework.http.HttpUtils;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import com.yucheng.smarthealthpro.home.activity.HealthyActivity;
import com.yucheng.smarthealthpro.home.activity.MeasureTipActivity;
import com.yucheng.smarthealthpro.home.activity.temperature.bean.TemperatureHisListBean;
import com.yucheng.smarthealthpro.home.activity.uricacid.adapter.UricAcidHisListAdapter;
import com.yucheng.smarthealthpro.home.activity.uricacid.adapter.UricAcidTabFragmentAdapter;
import com.yucheng.smarthealthpro.home.activity.uricacid.fragment.UricAcidTabFragment;
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
import com.yucheng.smarthealthpro.viewmodel.UricAcidViewModel;
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
public class UricAcidActivity extends BaseVbActivity<ActivityUricAcidBinding> implements CalendarView.OnCalendarSelectListener, CalendarView.OnMonthChangeListener {
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
    private UricAcidTabFragmentAdapter mAdapter;
    private Calendar mCalendar;
    CalendarView mCalendarView;
    private int mDayAverageUricAcidNum;
    private int mDayMaxUricAcidNum;
    private int mDayMinUricAcidNum;
    private int mDaySumUpUricAcidNum;
    private Gson mGson;
    private int mLastNum;
    private int mMonthAverageUricAcidNum;
    private int mMonthMaxUricAcidNum;
    private int mMonthMinUricAcidNum;
    private int mMonthSumUpUricAcidNum;
    NestedScrollView mNestedScrollView;
    RecyclerView mRecyclerView;
    SlidingTabLayout mSlidingTabLayout;
    private String mToDay;
    private UricAcidHisListAdapter mUricAcidHisListAdapter;
    private UricAcidViewModel mViewModel;
    NoScrollViewPager mViewPager;
    private int mWeekAverageUricAcidNum;
    private int mWeekMaxUricAcidNum;
    private int mWeekMinUricAcidNum;
    private int mWeekSumUpUricAcidNum;
    private int monthLastDay;
    RelativeLayout rlAnalyse;
    RelativeLayout rlDataFirst;
    RelativeLayout rlFirst;
    RelativeLayout rlFourthly;
    RelativeLayout rlSecond;
    private FriendCareUricAcidBean temp_bean;
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
    private List<UricAcid> uricAcidDbs;
    private int ARROW = 0;
    private List<String> mTitles = new ArrayList();
    private String mThatVeryDay = "";
    private List<TemperatureHisListBean> mDayUricAcidHisListBean = new ArrayList();
    private List<TemperatureHisListBean> mDayChartSumUpUricAcidHisListBean = new ArrayList();
    private List<TemperatureHisListBean> mWeekAdapterUricAcidHisListBean = new ArrayList();
    private List<TemperatureHisListBean> mWeekChartSumUpUricAcidHisListBean = new ArrayList();
    private List<TemperatureHisListBean> mMonthAdapterUricAcidHisListBean = new ArrayList();
    private List<TemperatureHisListBean> mMonthChartSumUpUricAcidHisListBean = new ArrayList();
    public boolean isCare = false;
    public int sex = 0;
    private final int min = TransUtils.URIC_ACID_VISIBLE_MIN;
    private final int max = TransUtils.URIC_ACID_VISIBLE_MAX;

    @Override // com.haibin.calendarview.CalendarView.OnCalendarSelectListener
    public void onCalendarOutOfRange(com.haibin.calendarview.Calendar calendar) {
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) throws Resources.NotFoundException {
        super.onCreate(savedInstanceState);
        this.mGson = new Gson();
        this.unit = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.URIC_ACID_UNIT, getString(R.string.uric_acid_unit_1));
        initView();
        initViewModel();
        initData();
    }

    private void initView() {
        this.mSlidingTabLayout = ((ActivityUricAcidBinding) this.mBinding).includeItemTop.stlTab;
        this.ivCalendar = ((ActivityUricAcidBinding) this.mBinding).includeItemTop.ivCalendar;
        this.tvCalendar = ((ActivityUricAcidBinding) this.mBinding).includeItemTop.tvCalendar;
        this.tvBackToday = ((ActivityUricAcidBinding) this.mBinding).includeItemTop.tvBackToday;
        this.llCalendar = ((ActivityUricAcidBinding) this.mBinding).includeItemTop.llCalendar;
        this.mViewPager = ((ActivityUricAcidBinding) this.mBinding).includeItemTop.vpTab;
        this.tvDataFirst = ((ActivityUricAcidBinding) this.mBinding).includeItemMessageData.tvDataFirst;
        this.tvDataFirstUnit = ((ActivityUricAcidBinding) this.mBinding).includeItemMessageData.tvDataFirstUnit;
        this.rlDataFirst = ((ActivityUricAcidBinding) this.mBinding).includeItemMessageData.rlDataFirst;
        this.tvDataSecond = ((ActivityUricAcidBinding) this.mBinding).includeItemMessageData.tvDataSecond;
        this.ivDataSecond = ((ActivityUricAcidBinding) this.mBinding).includeItemMessageData.ivDataSecond;
        this.tvDataSecondUnit = ((ActivityUricAcidBinding) this.mBinding).includeItemMessageData.tvDataSecondUnit;
        this.llDataSecond = ((ActivityUricAcidBinding) this.mBinding).includeItemMessageData.llDataSecond;
        this.tvDataThirdly = ((ActivityUricAcidBinding) this.mBinding).includeItemMessageData.tvDataThirdly;
        this.ivDataThirdly = ((ActivityUricAcidBinding) this.mBinding).includeItemMessageData.ivDataThirdly;
        this.tvDataThirdlyUnit = ((ActivityUricAcidBinding) this.mBinding).includeItemMessageData.tvDataThirdlyUnit;
        this.llDataThirdly = ((ActivityUricAcidBinding) this.mBinding).includeItemMessageData.llDataThirdly;
        this.tvStartButton = ((ActivityUricAcidBinding) this.mBinding).includeItemBottom.tvStartButton;
        this.llStartButton = ((ActivityUricAcidBinding) this.mBinding).includeItemBottom.llStartButton;
        this.tvAnalyse = ((ActivityUricAcidBinding) this.mBinding).includeItemBottom.tvAnalyse;
        this.tvAnalyseData = ((ActivityUricAcidBinding) this.mBinding).includeItemBottom.tvAnalyseData;
        this.rlAnalyse = ((ActivityUricAcidBinding) this.mBinding).includeItemBottom.rlAnalyse;
        this.ivFirstLeft = ((ActivityUricAcidBinding) this.mBinding).includeItemBottom.ivFirstLeft;
        this.tvFirst = ((ActivityUricAcidBinding) this.mBinding).includeItemBottom.tvFirst;
        this.ivFirstRight = ((ActivityUricAcidBinding) this.mBinding).includeItemBottom.ivFirstRight;
        this.rlFirst = ((ActivityUricAcidBinding) this.mBinding).includeItemBottom.rlFirst;
        this.ivSecondLeft = ((ActivityUricAcidBinding) this.mBinding).includeItemBottom.ivSecondLeft;
        this.tvSecond = ((ActivityUricAcidBinding) this.mBinding).includeItemBottom.tvSecond;
        this.ivSecondRight = ((ActivityUricAcidBinding) this.mBinding).includeItemBottom.ivSecondRight;
        this.rlSecond = ((ActivityUricAcidBinding) this.mBinding).includeItemBottom.rlSecond;
        this.ivFourthlyLeft = ((ActivityUricAcidBinding) this.mBinding).includeItemBottom.ivFourthlyLeft;
        this.tvFourthly = ((ActivityUricAcidBinding) this.mBinding).includeItemBottom.tvFourthly;
        this.ivFourthlyRight = ((ActivityUricAcidBinding) this.mBinding).includeItemBottom.ivFourthlyRight;
        this.rlFourthly = ((ActivityUricAcidBinding) this.mBinding).includeItemBottom.rlFourthly;
        this.mRecyclerView = ((ActivityUricAcidBinding) this.mBinding).includeItemBottom.recycleView;
        this.mNestedScrollView = ((ActivityUricAcidBinding) this.mBinding).nsv;
        this.tvYears = ((ActivityUricAcidBinding) this.mBinding).includeItemCalendar.tvYears;
        this.mCalendarView = ((ActivityUricAcidBinding) this.mBinding).includeItemCalendar.calendarView;
        this.llMonth = ((ActivityUricAcidBinding) this.mBinding).includeItemCalendar.llMonth;
        this.llCalendar.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.uricacid.activity.UricAcidActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.tvBackToday.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.uricacid.activity.UricAcidActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.llStartButton.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.uricacid.activity.UricAcidActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlAnalyse.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.uricacid.activity.UricAcidActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlFirst.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.uricacid.activity.UricAcidActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlSecond.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.uricacid.activity.UricAcidActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlFourthly.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.uricacid.activity.UricAcidActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.llMonth.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.uricacid.activity.UricAcidActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        changeTitle(getString(R.string.uric_acid));
        showBack();
        showRightImage(R.mipmap.topbar_ic_share, new NavigationBar.MyOnClickListener() { // from class: com.yucheng.smarthealthpro.home.activity.uricacid.activity.UricAcidActivity.1
            @Override // com.yucheng.smarthealthpro.framework.view.NavigationBar.MyOnClickListener
            public void onClick(View btn) {
                if (UricAcidActivity.this.checkCanClick()) {
                    ShareUtils.share(UricAcidActivity.this);
                }
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
            if (YCBTClient.isSupportFunction(Constants.FunctionConstant.IS_HAS_URIC_ACID_MEASUREMENT) && !Constant.isSmartHealth()) {
                this.llStartButton.setVisibility(0);
            } else {
                this.llStartButton.setVisibility(8);
            }
            this.tvStartButton.setText(getString(R.string.home_uric_acid_measure_title));
            this.tvFirst.setText(getString(R.string.include_bottom_tv_first_button));
            this.tvSecond.setText(getString(R.string.include_bottom_tv_second_button));
            this.tvAnalyse.setText(getString(R.string.home_uric_acid_analyse_tv));
            this.tvFourthly.setText(getString(R.string.include_bottom_tv_fourthly_button));
            this.ivFourthlyRight.setBackground(ResourcesCompat.getDrawable(getResources(), R.mipmap.list_ic_arrow_n, null));
            return;
        }
        this.isCare = true;
        this.sex = getIntent().getIntExtra(Constant.SpConstKey.SEX, 0);
        this.tvAnalyse.setText(getString(R.string.home_uric_acid_analyse_tv));
        this.llStartButton.setVisibility(8);
        this.rlFirst.setVisibility(8);
        this.rlSecond.setVisibility(8);
        this.ivFourthlyRight.setBackground(ResourcesCompat.getDrawable(getResources(), R.mipmap.list_ic_arrow_n, null));
    }

    private void initViewModel() {
        this.mViewModel = (UricAcidViewModel) new ViewModelProvider(this).get(UricAcidViewModel.class);
        FlowUtils.INSTANCE.collectFlow(this, this.mViewModel.getUricAcidDataFlow(), new FlowUtils.FlowCollector<HealthDayData<UricAcid>>() { // from class: com.yucheng.smarthealthpro.home.activity.uricacid.activity.UricAcidActivity.2
            @Override // com.yucheng.smarthealthpro.utils.FlowUtils.FlowCollector
            public void collect(HealthDayData<UricAcid> data) throws Resources.NotFoundException {
                UricAcidActivity.this.onThatVeryDayData(data.getDay(), data.getData());
            }
        });
        FlowUtils.INSTANCE.collectFlow(this, this.mViewModel.getUricAcidPackedDataFlow(), new FlowUtils.FlowCollector<HealthPackedData<UricAcid>>() { // from class: com.yucheng.smarthealthpro.home.activity.uricacid.activity.UricAcidActivity.3
            @Override // com.yucheng.smarthealthpro.utils.FlowUtils.FlowCollector
            public void collect(HealthPackedData<UricAcid> packedData) {
                if (packedData.getDayCount() == 7) {
                    UricAcidActivity.this.onDataByDays(7, packedData.getData());
                } else if (packedData.getDayCount() == 30) {
                    UricAcidActivity.this.onDataByDays(30, packedData.getData());
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
        new Thread(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.uricacid.activity.UricAcidActivity.4
            @Override // java.lang.Runnable
            public void run() {
                UricAcidActivity.this.getWeekData();
                UricAcidActivity.this.getMonthData();
            }
        }).start();
    }

    private void getWeek(String dateTime, String startTime, String endTime) {
        this.mWeekAdapterUricAcidHisListBean.clear();
        this.mWeekChartSumUpUricAcidHisListBean.clear();
        HashMap map = new HashMap();
        map.put("userId", getIntent().getExtras().getString(Constant.SpConstKey.DEV_ID));
        map.put("day", dateTime);
        map.put("startDate", startTime);
        map.put("endDate", endTime);
        map.put("type", "1");
        HttpUtils.getInstance().postMsgAsynHttp(this, com.yucheng.smarthealthpro.framework.util.Constants.uricAcidDayUrl, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.home.activity.uricacid.activity.UricAcidActivity.5
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                FriendCareUricAcidBean friendCareUricAcidBean;
                if (result != null) {
                    new ArrayList();
                    try {
                        friendCareUricAcidBean = (FriendCareUricAcidBean) UricAcidActivity.this.mGson.fromJson(result, FriendCareUricAcidBean.class);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        friendCareUricAcidBean = null;
                    }
                    if (friendCareUricAcidBean == null) {
                        return;
                    }
                    UricAcidActivity.this.mWeekSumUpUricAcidNum = 0;
                    UricAcidActivity.this.mWeekMaxUricAcidNum = 0;
                    UricAcidActivity uricAcidActivity = UricAcidActivity.this;
                    uricAcidActivity.mWeekMinUricAcidNum = uricAcidActivity.max;
                    ArrayList<String> pastStringArray = YearToDayListUtils.getPastStringArray(UricAcidActivity.this.mToDay, 6);
                    Collections.sort(friendCareUricAcidBean.data);
                    for (int i2 = 0; i2 < friendCareUricAcidBean.data.size(); i2++) {
                        FriendCareUricAcidBean.Data data = friendCareUricAcidBean.data.get(i2);
                        List list = (List) UricAcidActivity.this.mGson.fromJson(data.mlist, new TypeToken<List<FriendCareUricAcidBean.UricAcid>>() { // from class: com.yucheng.smarthealthpro.home.activity.uricacid.activity.UricAcidActivity.5.1
                        }.getType());
                        Tools.removeCareListUricAcid(list);
                        Tools.sortCareListUricAcid(list);
                        if (list.size() != 0) {
                            float f2 = 0.0f;
                            for (int i3 = 0; i3 < list.size(); i3++) {
                                f2 += ((FriendCareUricAcidBean.UricAcid) list.get(i3)).uricAcid;
                            }
                            int iRound = Math.round(f2 / list.size());
                            if (UricAcidActivity.this.mWeekMaxUricAcidNum < iRound) {
                                UricAcidActivity.this.mWeekMaxUricAcidNum = iRound;
                            }
                            if (UricAcidActivity.this.mWeekMinUricAcidNum > iRound) {
                                UricAcidActivity.this.mWeekMinUricAcidNum = iRound;
                            }
                            UricAcidActivity.this.mWeekSumUpUricAcidNum += iRound;
                            float f3 = iRound;
                            UricAcidActivity.this.mWeekAdapterUricAcidHisListBean.add(new TemperatureHisListBean(data.dateformat, UricAcidActivity.this.transform(f3), UricAcidActivity.this.getState(f3), UricAcidActivity.this.unit));
                        }
                    }
                    for (int i4 = 0; i4 < pastStringArray.size(); i4++) {
                        UricAcidActivity.this.mWeekChartSumUpUricAcidHisListBean.add(new TemperatureHisListBean(pastStringArray.get(i4), "0", "", UricAcidActivity.this.unit));
                    }
                    for (int i5 = 0; i5 < pastStringArray.size(); i5++) {
                        for (TemperatureHisListBean temperatureHisListBean : UricAcidActivity.this.mWeekAdapterUricAcidHisListBean) {
                            if (pastStringArray.get(i5).equals(temperatureHisListBean.getTime())) {
                                UricAcidActivity.this.mWeekChartSumUpUricAcidHisListBean.remove(i5);
                                UricAcidActivity.this.mWeekChartSumUpUricAcidHisListBean.add(i5, new TemperatureHisListBean(temperatureHisListBean.getTime(), temperatureHisListBean.getmValue(), temperatureHisListBean.getState(), temperatureHisListBean.unit));
                            }
                        }
                    }
                    if (friendCareUricAcidBean.data.size() > 0) {
                        UricAcidActivity.this.mWeekAverageUricAcidNum = Math.round((r0.mWeekSumUpUricAcidNum * 1.0f) / friendCareUricAcidBean.data.size());
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
        HttpUtils.getInstance().postMsgAsynHttp(this, com.yucheng.smarthealthpro.framework.util.Constants.uricAcidDayUrl, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.home.activity.uricacid.activity.UricAcidActivity.6
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                FriendCareUricAcidBean friendCareUricAcidBean;
                if (result != null) {
                    new ArrayList();
                    try {
                        friendCareUricAcidBean = (FriendCareUricAcidBean) UricAcidActivity.this.mGson.fromJson(result, FriendCareUricAcidBean.class);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        friendCareUricAcidBean = null;
                    }
                    if (friendCareUricAcidBean == null) {
                        return;
                    }
                    UricAcidActivity.this.mMonthAdapterUricAcidHisListBean.clear();
                    UricAcidActivity.this.mMonthChartSumUpUricAcidHisListBean.clear();
                    UricAcidActivity.this.mMonthMaxUricAcidNum = 0;
                    UricAcidActivity uricAcidActivity = UricAcidActivity.this;
                    uricAcidActivity.mMonthMinUricAcidNum = uricAcidActivity.max;
                    UricAcidActivity.this.mMonthAverageUricAcidNum = 0;
                    UricAcidActivity.this.mLastNum = 0;
                    ArrayList<String> pastStringArray = YearToDayListUtils.getPastStringArray(UricAcidActivity.this.mToDay, 29);
                    Collections.sort(friendCareUricAcidBean.data);
                    for (int i2 = 0; i2 < friendCareUricAcidBean.data.size(); i2++) {
                        FriendCareUricAcidBean.Data data = friendCareUricAcidBean.data.get(i2);
                        List list = (List) UricAcidActivity.this.mGson.fromJson(data.mlist, new TypeToken<List<FriendCareUricAcidBean.UricAcid>>() { // from class: com.yucheng.smarthealthpro.home.activity.uricacid.activity.UricAcidActivity.6.1
                        }.getType());
                        Tools.removeCareListUricAcid(list);
                        Tools.sortCareListUricAcid(list);
                        if (list.size() != 0) {
                            float f2 = 0.0f;
                            for (int i3 = 0; i3 < list.size(); i3++) {
                                f2 += ((FriendCareUricAcidBean.UricAcid) list.get(i3)).uricAcid;
                            }
                            int iRound = Math.round(f2 / list.size());
                            if (UricAcidActivity.this.mMonthMaxUricAcidNum < iRound) {
                                UricAcidActivity.this.mMonthMaxUricAcidNum = iRound;
                            }
                            if (UricAcidActivity.this.mMonthMinUricAcidNum > iRound) {
                                UricAcidActivity.this.mMonthMinUricAcidNum = iRound;
                            }
                            UricAcidActivity.this.mMonthSumUpUricAcidNum += iRound;
                            float f3 = iRound;
                            UricAcidActivity.this.mMonthAdapterUricAcidHisListBean.add(new TemperatureHisListBean(data.dateformat, UricAcidActivity.this.transform(f3), UricAcidActivity.this.getState(f3), UricAcidActivity.this.unit));
                        }
                    }
                    for (int i4 = 0; i4 < pastStringArray.size(); i4++) {
                        UricAcidActivity.this.mMonthChartSumUpUricAcidHisListBean.add(new TemperatureHisListBean(pastStringArray.get(i4), "0", "", UricAcidActivity.this.unit));
                    }
                    for (int i5 = 0; i5 < pastStringArray.size(); i5++) {
                        for (TemperatureHisListBean temperatureHisListBean : UricAcidActivity.this.mMonthAdapterUricAcidHisListBean) {
                            if (pastStringArray.get(i5).equals(temperatureHisListBean.getTime())) {
                                UricAcidActivity.this.mMonthChartSumUpUricAcidHisListBean.remove(i5);
                                UricAcidActivity.this.mMonthChartSumUpUricAcidHisListBean.add(i5, new TemperatureHisListBean(temperatureHisListBean.getTime(), temperatureHisListBean.getmValue(), temperatureHisListBean.getState(), temperatureHisListBean.unit));
                            }
                        }
                    }
                    if (friendCareUricAcidBean.data.size() > 0) {
                        UricAcidActivity.this.mMonthAverageUricAcidNum = Math.round((r0.mMonthSumUpUricAcidNum * 1.0f) / friendCareUricAcidBean.data.size());
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
        UricAcidTabFragmentAdapter uricAcidTabFragmentAdapter = new UricAcidTabFragmentAdapter(getSupportFragmentManager(), new UricAcidTabFragmentAdapter.FragmentCreator() { // from class: com.yucheng.smarthealthpro.home.activity.uricacid.activity.UricAcidActivity.7
            @Override // com.yucheng.smarthealthpro.home.activity.uricacid.adapter.UricAcidTabFragmentAdapter.FragmentCreator
            public Fragment createFragment(String data, int position) {
                return UricAcidTabFragment.newInstance(data.toString(), position, UricAcidActivity.this.mNestedScrollView, UricAcidActivity.this.mDayChartSumUpUricAcidHisListBean, UricAcidActivity.this.mWeekChartSumUpUricAcidHisListBean, UricAcidActivity.this.mMonthChartSumUpUricAcidHisListBean, UricAcidActivity.this.mDayMaxUricAcidNum);
            }

            @Override // com.yucheng.smarthealthpro.home.activity.uricacid.adapter.UricAcidTabFragmentAdapter.FragmentCreator
            public String createTitle(String data) {
                return Html.fromHtml(data).toString();
            }
        });
        this.mAdapter = uricAcidTabFragmentAdapter;
        this.mViewPager.setAdapter(uricAcidTabFragmentAdapter);
        this.mViewPager.setOffscreenPageLimit(this.mTitles.size() - 1);
        this.mAdapter.setData(this.mTitles);
        this.mSlidingTabLayout.setViewPager(this.mViewPager, (String[]) this.mTitles.toArray(new String[0]));
        this.mSlidingTabLayout.setCurrentTab(2);
        this.mViewPager.addOnPageChangeListener(new OnPageChangeListenerImpl());
    }

    public void dataAnalysis(float value) {
        int i2;
        int i3;
        if (this.sex == 1) {
            i2 = 90;
            i3 = 360;
        } else {
            i2 = 120;
            i3 = 420;
        }
        if (value < i2 && value >= TransUtils.URIC_ACID_VISIBLE_MIN) {
            this.tvAnalyseData.setText(getText(R.string.home_uric_acid_is_low));
            return;
        }
        if (value > i3 && value <= TransUtils.URIC_ACID_VISIBLE_MAX) {
            this.tvAnalyseData.setText(getText(R.string.home_uric_acid_is_high));
        } else if (value >= TransUtils.URIC_ACID_VISIBLE_MIN && value <= TransUtils.URIC_ACID_VISIBLE_MAX) {
            this.tvAnalyseData.setText(getText(R.string.home_uric_acid_is_normal));
        } else {
            this.tvAnalyseData.setText("");
        }
    }

    private void setRecycleView() {
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        UricAcidHisListAdapter uricAcidHisListAdapter = new UricAcidHisListAdapter(R.layout.item_universal_his_list);
        this.mUricAcidHisListAdapter = uricAcidHisListAdapter;
        uricAcidHisListAdapter.addData((Collection) this.mDayUricAcidHisListBean);
        this.mRecyclerView.setAdapter(this.mUricAcidHisListAdapter);
        this.mRecyclerView.setNestedScrollingEnabled(false);
    }

    private void initMonth() throws Resources.NotFoundException {
        this.mCalendarView.setOnCalendarSelectListener(this);
        this.mCalendarView.setOnMonthChangeListener(this);
        this.mCalendarView.scrollToCurrent();
        this.mCalendarView.setOnCalendarInterceptListener(new CalendarView.OnCalendarInterceptListener() { // from class: com.yucheng.smarthealthpro.home.activity.uricacid.activity.UricAcidActivity.8
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
        HttpUtils.getInstance().postMsgAsynHttp(this, com.yucheng.smarthealthpro.framework.util.Constants.uricAcidDayUrl, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.home.activity.uricacid.activity.UricAcidActivity.9
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                if (result != null) {
                    try {
                        UricAcidActivity uricAcidActivity = UricAcidActivity.this;
                        uricAcidActivity.temp_bean = (FriendCareUricAcidBean) uricAcidActivity.mGson.fromJson(result, FriendCareUricAcidBean.class);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    UricAcidActivity.this.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.uricacid.activity.UricAcidActivity.9.1
                        @Override // java.lang.Runnable
                        public void run() throws Resources.NotFoundException {
                            UricAcidActivity.this.setDayData();
                            UricAcidActivity.this.initViewPager();
                        }
                    });
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v10, types: [java.util.List] */
    public void setDayData() {
        List<TemperatureHisListBean> list = this.mDayUricAcidHisListBean;
        if (list != null) {
            list.clear();
        }
        List<TemperatureHisListBean> list2 = this.mDayChartSumUpUricAcidHisListBean;
        if (list2 != null) {
            list2.clear();
        }
        this.mDaySumUpUricAcidNum = 0;
        this.mDayMaxUricAcidNum = 0;
        this.mDayMinUricAcidNum = this.max;
        if (this.temp_bean == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        try {
            Iterator<FriendCareUricAcidBean.Data> it2 = this.temp_bean.data.iterator();
            while (it2.hasNext()) {
                arrayList = (List) new Gson().fromJson(it2.next().mlist, new TypeToken<List<FriendCareUricAcidBean.UricAcid>>() { // from class: com.yucheng.smarthealthpro.home.activity.uricacid.activity.UricAcidActivity.10
                }.getType());
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        Tools.removeCareListUricAcid(arrayList);
        Tools.sortCareListUricAcid(arrayList);
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            int i3 = ((FriendCareUricAcidBean.UricAcid) arrayList.get(i2)).uricAcid;
            if (i3 > this.mDayMaxUricAcidNum) {
                this.mDayMaxUricAcidNum = i3;
            }
            if (i3 < this.mDayMinUricAcidNum) {
                this.mDayMinUricAcidNum = i3;
            }
            this.mDaySumUpUricAcidNum += i3;
            float f2 = i3;
            String strTransform = transform(f2);
            TemperatureHisListBean temperatureHisListBean = new TemperatureHisListBean(TimeStampUtils.dateForStringToDate(TimeStampUtils.longStampForDate(((FriendCareUricAcidBean.UricAcid) arrayList.get(i2)).rtime)), strTransform, getState(f2), this.unit);
            temperatureHisListBean.setModel(((FriendCareUricAcidBean.UricAcid) arrayList.get(i2)).cMode);
            this.mDayUricAcidHisListBean.add(temperatureHisListBean);
            this.mDayChartSumUpUricAcidHisListBean.add(new TemperatureHisListBean(TimeStampUtils.dateForStringToDate(TimeStampUtils.longStampForDate(((FriendCareUricAcidBean.UricAcid) arrayList.get(i2)).rtime)), strTransform, getState(f2), this.unit));
        }
        if (this.mDayUricAcidHisListBean.size() != 0) {
            this.mDayAverageUricAcidNum = Math.round((this.mDaySumUpUricAcidNum * 1.0f) / this.mDayUricAcidHisListBean.size());
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

    public void onThatVeryDayData(String thatVeryDay, List<UricAcid> data) throws Resources.NotFoundException {
        int uricAcid;
        this.mDayUricAcidHisListBean.clear();
        this.mDayChartSumUpUricAcidHisListBean.clear();
        this.uricAcidDbs = data;
        this.mThatVeryDay = thatVeryDay;
        this.mDaySumUpUricAcidNum = 0;
        this.mDayMaxUricAcidNum = 0;
        this.mDayMinUricAcidNum = this.max;
        this.mDayAverageUricAcidNum = 0;
        this.mLastNum = 0;
        if (data != null) {
            for (int i2 = 0; i2 < this.uricAcidDbs.size(); i2++) {
                if (TimeStampUtils.dateForStringYearToDate(TimeStampUtils.longStampForDate(this.uricAcidDbs.get(i2).getStartTimestamp())).equals(thatVeryDay) && this.uricAcidDbs.get(i2).getUricAcid() != 0 && (uricAcid = this.uricAcidDbs.get(i2).getUricAcid()) >= this.min && uricAcid <= this.max) {
                    String strDateForStringToDate = TimeStampUtils.dateForStringToDate(TimeStampUtils.longStampForDate(this.uricAcidDbs.get(i2).getStartTimestamp()));
                    String strTransform = transform(uricAcid);
                    TemperatureHisListBean temperatureHisListBean = new TemperatureHisListBean(strDateForStringToDate, strTransform, "", this.unit);
                    temperatureHisListBean.setModel(this.uricAcidDbs.get(i2).getMeasureMode());
                    this.mDayUricAcidHisListBean.add(temperatureHisListBean);
                    this.mDayChartSumUpUricAcidHisListBean.add(new TemperatureHisListBean(strDateForStringToDate, strTransform, "", this.unit));
                    if (this.mLastNum == 0) {
                        this.mLastNum = uricAcid;
                    }
                    if (uricAcid > this.mDayMaxUricAcidNum) {
                        this.mDayMaxUricAcidNum = uricAcid;
                    }
                    if (uricAcid < this.mDayMinUricAcidNum) {
                        this.mDayMinUricAcidNum = uricAcid;
                    }
                    this.mDaySumUpUricAcidNum += uricAcid;
                }
            }
        }
        if (this.mDayUricAcidHisListBean.size() != 0) {
            this.mDayAverageUricAcidNum = Math.round((this.mDaySumUpUricAcidNum * 1.0f) / this.mDayUricAcidHisListBean.size());
        }
        initViewPager();
    }

    public void getWeekData() {
        this.mWeekAdapterUricAcidHisListBean.clear();
        this.mWeekChartSumUpUricAcidHisListBean.clear();
        this.mWeekMaxUricAcidNum = 0;
        this.mWeekMinUricAcidNum = this.max;
        this.mWeekAverageUricAcidNum = 0;
        this.mLastNum = 0;
        this.mViewModel.getPackedDayData(YearToDayListUtils.getPastStringArray(this.mToDay, 6).get(0), 7);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onDataByDays(int days, List<UricAcid> data) {
        int size;
        float f2;
        ArrayList<String> pastStringArray = YearToDayListUtils.getPastStringArray(this.mToDay, days - 1);
        int i2 = 0;
        for (int i3 = 0; i3 < days; i3++) {
            List<UricAcid> listFilterUricAcidByDate = HealthDataFilterKt.filterUricAcidByDate(data, pastStringArray.get(i3));
            this.uricAcidDbs = listFilterUricAcidByDate;
            if (listFilterUricAcidByDate != null) {
                size = listFilterUricAcidByDate.size();
                f2 = 0.0f;
                for (int i4 = 0; i4 < this.uricAcidDbs.size(); i4++) {
                    float uricAcid = this.uricAcidDbs.get(i4).getUricAcid();
                    if (uricAcid < this.min || uricAcid > this.max) {
                        size--;
                    } else {
                        f2 += uricAcid;
                    }
                }
            } else {
                size = 0;
                f2 = 0.0f;
            }
            if (f2 != 0.0f) {
                float f3 = f2 / size;
                i2 = (int) (i2 + f3);
                if (this.mLastNum == 0) {
                    this.mLastNum = (int) f3;
                }
                if (days == 7) {
                    if (f3 > this.mWeekMaxUricAcidNum) {
                        this.mWeekMaxUricAcidNum = Math.round(f3);
                    }
                    if (f3 < this.mWeekMinUricAcidNum) {
                        this.mWeekMinUricAcidNum = Math.round(f3);
                    }
                    String strTransform = transform(f3);
                    this.mWeekAdapterUricAcidHisListBean.add(new TemperatureHisListBean(pastStringArray.get(i3), strTransform, "", this.unit));
                    this.mWeekChartSumUpUricAcidHisListBean.add(new TemperatureHisListBean(TimeStampUtils.toFormatDate(pastStringArray.get(i3)), strTransform, "", this.unit));
                } else if (days == 30) {
                    if (f3 > this.mMonthMaxUricAcidNum) {
                        this.mMonthMaxUricAcidNum = Math.round(f3);
                    }
                    if (f3 < this.mMonthMinUricAcidNum) {
                        this.mMonthMinUricAcidNum = Math.round(f3);
                    }
                    String strTransform2 = transform(f3);
                    this.mMonthAdapterUricAcidHisListBean.add(new TemperatureHisListBean(pastStringArray.get(i3), strTransform2, "", this.unit));
                    this.mMonthChartSumUpUricAcidHisListBean.add(new TemperatureHisListBean(TimeStampUtils.toFormatDate(pastStringArray.get(i3)), strTransform2, "", this.unit));
                }
            } else if (days == 7) {
                this.mWeekChartSumUpUricAcidHisListBean.add(new TemperatureHisListBean(TimeStampUtils.toFormatDate(pastStringArray.get(i3)), "0", "", this.unit));
            } else if (days == 30) {
                this.mMonthChartSumUpUricAcidHisListBean.add(new TemperatureHisListBean(TimeStampUtils.toFormatDate(pastStringArray.get(i3)), "0", "", this.unit));
            }
        }
        if (i2 != 0) {
            if (days == 7 && this.mWeekAdapterUricAcidHisListBean.size() > 0) {
                Collections.reverse(this.mWeekAdapterUricAcidHisListBean);
                this.mWeekAverageUricAcidNum = Math.round((i2 * 1.0f) / this.mWeekAdapterUricAcidHisListBean.size());
            } else {
                if (days != 30 || this.mMonthAdapterUricAcidHisListBean.size() <= 0) {
                    return;
                }
                Collections.reverse(this.mMonthAdapterUricAcidHisListBean);
                this.mMonthAverageUricAcidNum = Math.round((i2 * 1.0f) / this.mMonthAdapterUricAcidHisListBean.size());
            }
        }
    }

    public void getMonthData() {
        this.mMonthAdapterUricAcidHisListBean.clear();
        this.mMonthChartSumUpUricAcidHisListBean.clear();
        this.mMonthMaxUricAcidNum = 0;
        this.mMonthMinUricAcidNum = this.max;
        this.mMonthAverageUricAcidNum = 0;
        this.mLastNum = 0;
        this.mViewModel.getPackedDayData(YearToDayListUtils.getPastStringArray(this.mToDay, 29).get(0), 30);
        runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.uricacid.activity.UricAcidActivity.11
            @Override // java.lang.Runnable
            public void run() throws Resources.NotFoundException {
                UricAcidActivity.this.initViewPager();
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
                launchActivityForResult(UricAcidMeasureActivity.URICACID_MEASURE, UricAcidMeasureActivity.class);
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
                this.mNestedScrollView.postDelayed(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.uricacid.activity.UricAcidActivity.12
                    @Override // java.lang.Runnable
                    public void run() {
                        UricAcidActivity.this.mNestedScrollView.smoothScrollTo(0, (int) (UricAcidActivity.this.mNestedScrollView.getScrollY() + (DpUtil.dp2px(UricAcidActivity.this.context, 56.0f) * 1.5f)));
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
        if (requestCode == UricAcidMeasureActivity.URICACID_MEASURE && resultCode == -1) {
            initData();
        }
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity
    public void onActivityResult(ActivityResult result, int requestCode) throws Resources.NotFoundException {
        super.onActivityResult(result, requestCode);
        if (requestCode == UricAcidMeasureActivity.URICACID_MEASURE && result.getResultCode() == -1) {
            initData();
        } else if (requestCode == MeasureTipActivity.MEASURE_TIP) {
            launchActivityForResult(UricAcidMeasureActivity.URICACID_MEASURE, UricAcidMeasureActivity.class);
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
            UricAcidActivity.this.mViewPager.setCurrentItem(position);
            if (position == 0) {
                UricAcidActivity.this.freshMonthData();
            } else if (position == 1) {
                UricAcidActivity.this.freshWeekData();
            } else {
                if (position != 2) {
                    return;
                }
                UricAcidActivity.this.freshDayData();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void freshDayData() {
        this.tvBackToday.setVisibility(8);
        this.llCalendar.setVisibility(0);
        List<TemperatureHisListBean> list = this.mDayUricAcidHisListBean;
        if (list != null && list.size() > 0) {
            if (Constant.isTechFeel()) {
                this.tvDataFirst.setText(this.mLastNum + "");
            } else {
                this.tvDataFirst.setText(transform(this.mDayAverageUricAcidNum));
            }
            this.tvDataSecond.setText(transform(this.mDayMaxUricAcidNum));
            this.tvDataThirdly.setText(transform(this.mDayMinUricAcidNum));
        } else {
            this.tvDataFirst.setText("--");
            this.tvDataSecond.setText("--");
            this.tvDataThirdly.setText("--");
            this.mDayAverageUricAcidNum = 0;
        }
        this.mUricAcidHisListAdapter.setList(this.mDayUricAcidHisListBean);
        this.mUricAcidHisListAdapter.notifyDataSetChanged();
        dataAnalysis(this.mDayAverageUricAcidNum);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void freshWeekData() {
        this.tvBackToday.setVisibility(0);
        this.llCalendar.setVisibility(8);
        List<TemperatureHisListBean> list = this.mWeekAdapterUricAcidHisListBean;
        if (list != null && list.size() > 0) {
            if (Constant.isTechFeel()) {
                this.tvDataFirst.setText(this.mLastNum + "");
            } else {
                this.tvDataFirst.setText(transform(this.mWeekAverageUricAcidNum));
            }
            this.tvDataSecond.setText(transform(this.mWeekMaxUricAcidNum));
            this.tvDataThirdly.setText(transform(this.mWeekMinUricAcidNum));
        } else {
            this.tvDataFirst.setText("--");
            this.tvDataSecond.setText("--");
            this.tvDataThirdly.setText("--");
            this.mWeekAverageUricAcidNum = 0;
        }
        this.mUricAcidHisListAdapter.setList(this.mWeekAdapterUricAcidHisListBean);
        this.mUricAcidHisListAdapter.notifyDataSetChanged();
        dataAnalysis(this.mWeekAverageUricAcidNum);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void freshMonthData() {
        this.tvBackToday.setVisibility(0);
        this.llCalendar.setVisibility(8);
        List<TemperatureHisListBean> list = this.mMonthAdapterUricAcidHisListBean;
        if (list != null && list.size() > 0) {
            if (Constant.isTechFeel()) {
                this.tvDataFirst.setText(this.mLastNum + "");
            } else {
                this.tvDataFirst.setText(transform(this.mMonthAverageUricAcidNum));
            }
            this.tvDataSecond.setText(transform(this.mMonthMaxUricAcidNum));
            this.tvDataThirdly.setText(transform(this.mMonthMinUricAcidNum));
        } else {
            this.tvDataFirst.setText("--");
            this.tvDataSecond.setText("--");
            this.tvDataThirdly.setText("--");
            this.mMonthAverageUricAcidNum = 0;
        }
        this.mUricAcidHisListAdapter.setList(this.mMonthAdapterUricAcidHisListBean);
        this.mUricAcidHisListAdapter.notifyDataSetChanged();
        dataAnalysis(this.mMonthAverageUricAcidNum);
    }

    public String getState(float value) {
        int i2;
        int i3;
        if (this.sex == 1) {
            i2 = 90;
            i3 = 360;
        } else {
            i2 = 120;
            i3 = 420;
        }
        if (value < i2) {
            return getText(R.string.home_uric_acid_is_low).toString();
        }
        if (value > i3) {
            return getText(R.string.home_uric_acid_is_high).toString();
        }
        return getText(R.string.home_uric_acid_is_normal).toString();
    }

    public String transform(float value) {
        if (getString(R.string.uric_acid_unit_2).equals(this.unit)) {
            return FormatUtil.keep1(value * TransUtils.URIC_ACID_TRANS);
        }
        return ((int) value) + "";
    }
}
