package com.yucheng.smarthealthpro.home.activity.bloodfat.activity;

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
import com.tencent.bugly.crashreport.CrashReport;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.care.bean.FriendCareBloodFatBean;
import com.yucheng.smarthealthpro.data.packed.HealthDayData;
import com.yucheng.smarthealthpro.data.packed.HealthPackedData;
import com.yucheng.smarthealthpro.database.room.bean.BloodLipids;
import com.yucheng.smarthealthpro.databinding.ActivityBloodfatBinding;
import com.yucheng.smarthealthpro.framework.http.HttpUtils;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import com.yucheng.smarthealthpro.home.activity.HealthyActivity;
import com.yucheng.smarthealthpro.home.activity.bloodfat.adapter.BloodFatHisListAdapter;
import com.yucheng.smarthealthpro.home.activity.bloodfat.adapter.BloodFatTabFragmentAdapter;
import com.yucheng.smarthealthpro.home.activity.bloodfat.fragment.BloodFatTabFragment;
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
import com.yucheng.smarthealthpro.viewmodel.BloodLipidsViewModel;
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
public class BloodFatActivity extends BaseVbActivity<ActivityBloodfatBinding> implements CalendarView.OnCalendarSelectListener, CalendarView.OnMonthChangeListener {
    private List<BloodLipids> bloodFatDbs;
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
    private BloodFatTabFragmentAdapter mAdapter;
    private BloodFatHisListAdapter mBloodFatHisListAdapter;
    private Calendar mCalendar;
    CalendarView mCalendarView;
    private float mDayAverageBloodFatNum;
    private float mDayMaxBloodFatNum;
    private float mDayMinBloodFatNum;
    private float mDaySumUpBloodFatNum;
    private Gson mGson;
    private float mLastNum;
    private float mMonthAverageBloodFatNum;
    private float mMonthMaxBloodFatNum;
    private float mMonthMinBloodFatNum;
    private float mMonthSumUpBloodFatNum;
    NestedScrollView mNestedScrollView;
    RecyclerView mRecyclerView;
    SlidingTabLayout mSlidingTabLayout;
    private String mToDay;
    private BloodLipidsViewModel mViewModel;
    NoScrollViewPager mViewPager;
    private float mWeekAverageBloodFatNum;
    private float mWeekMaxBloodFatNum;
    private float mWeekMinBloodFatNum;
    private float mWeekSumUpBloodFatNum;
    private int monthLastDay;
    RelativeLayout rlAnalyse;
    RelativeLayout rlDataFirst;
    RelativeLayout rlFirst;
    RelativeLayout rlFourthly;
    RelativeLayout rlSecond;
    private FriendCareBloodFatBean temp_bean;
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
    private List<TemperatureHisListBean> mDayBloodFatHisListBean = new ArrayList();
    private List<TemperatureHisListBean> mDayChartSumUpBloodFatHisListBean = new ArrayList();
    private List<TemperatureHisListBean> mWeekAdapterBloodFatHisListBean = new ArrayList();
    private List<TemperatureHisListBean> mWeekChartSumUpBloodFatHisListBean = new ArrayList();
    private List<TemperatureHisListBean> mMonthAdapterBloodFatHisListBean = new ArrayList();
    private List<TemperatureHisListBean> mMonthChartSumUpBloodFatHisListBean = new ArrayList();
    private Boolean isCare = false;

    @Override // com.haibin.calendarview.CalendarView.OnCalendarSelectListener
    public void onCalendarOutOfRange(com.haibin.calendarview.Calendar calendar) {
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) throws Resources.NotFoundException {
        super.onCreate(savedInstanceState);
        this.mGson = new Gson();
        this.unit = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.BLOOD_SUGAR_AND_BLOOD_FAT_UNIT, getString(R.string.blood_sugar_unit_1));
        initView();
        initViewModel();
        initData();
    }

    private void initView() {
        this.mSlidingTabLayout = ((ActivityBloodfatBinding) this.mBinding).includeItemTop.stlTab;
        this.ivCalendar = ((ActivityBloodfatBinding) this.mBinding).includeItemTop.ivCalendar;
        this.tvCalendar = ((ActivityBloodfatBinding) this.mBinding).includeItemTop.tvCalendar;
        this.tvBackToday = ((ActivityBloodfatBinding) this.mBinding).includeItemTop.tvBackToday;
        this.llCalendar = ((ActivityBloodfatBinding) this.mBinding).includeItemTop.llCalendar;
        this.mViewPager = ((ActivityBloodfatBinding) this.mBinding).includeItemTop.vpTab;
        this.tvDataFirst = ((ActivityBloodfatBinding) this.mBinding).includeItemMessageData.tvDataFirst;
        this.tvDataFirstUnit = ((ActivityBloodfatBinding) this.mBinding).includeItemMessageData.tvDataFirstUnit;
        this.rlDataFirst = ((ActivityBloodfatBinding) this.mBinding).includeItemMessageData.rlDataFirst;
        this.tvDataSecond = ((ActivityBloodfatBinding) this.mBinding).includeItemMessageData.tvDataSecond;
        this.ivDataSecond = ((ActivityBloodfatBinding) this.mBinding).includeItemMessageData.ivDataSecond;
        this.tvDataSecondUnit = ((ActivityBloodfatBinding) this.mBinding).includeItemMessageData.tvDataSecondUnit;
        this.llDataSecond = ((ActivityBloodfatBinding) this.mBinding).includeItemMessageData.llDataSecond;
        this.tvDataThirdly = ((ActivityBloodfatBinding) this.mBinding).includeItemMessageData.tvDataThirdly;
        this.ivDataThirdly = ((ActivityBloodfatBinding) this.mBinding).includeItemMessageData.ivDataThirdly;
        this.tvDataThirdlyUnit = ((ActivityBloodfatBinding) this.mBinding).includeItemMessageData.tvDataThirdlyUnit;
        this.llDataThirdly = ((ActivityBloodfatBinding) this.mBinding).includeItemMessageData.llDataThirdly;
        this.tvStartButton = ((ActivityBloodfatBinding) this.mBinding).includeItemBottom.tvStartButton;
        this.llStartButton = ((ActivityBloodfatBinding) this.mBinding).includeItemBottom.llStartButton;
        this.tvAnalyse = ((ActivityBloodfatBinding) this.mBinding).includeItemBottom.tvAnalyse;
        this.tvAnalyseData = ((ActivityBloodfatBinding) this.mBinding).includeItemBottom.tvAnalyseData;
        this.rlAnalyse = ((ActivityBloodfatBinding) this.mBinding).includeItemBottom.rlAnalyse;
        this.ivFirstLeft = ((ActivityBloodfatBinding) this.mBinding).includeItemBottom.ivFirstLeft;
        this.tvFirst = ((ActivityBloodfatBinding) this.mBinding).includeItemBottom.tvFirst;
        this.ivFirstRight = ((ActivityBloodfatBinding) this.mBinding).includeItemBottom.ivFirstRight;
        this.rlFirst = ((ActivityBloodfatBinding) this.mBinding).includeItemBottom.rlFirst;
        this.ivSecondLeft = ((ActivityBloodfatBinding) this.mBinding).includeItemBottom.ivSecondLeft;
        this.tvSecond = ((ActivityBloodfatBinding) this.mBinding).includeItemBottom.tvSecond;
        this.ivSecondRight = ((ActivityBloodfatBinding) this.mBinding).includeItemBottom.ivSecondRight;
        this.rlSecond = ((ActivityBloodfatBinding) this.mBinding).includeItemBottom.rlSecond;
        this.ivFourthlyLeft = ((ActivityBloodfatBinding) this.mBinding).includeItemBottom.ivFourthlyLeft;
        this.tvFourthly = ((ActivityBloodfatBinding) this.mBinding).includeItemBottom.tvFourthly;
        this.ivFourthlyRight = ((ActivityBloodfatBinding) this.mBinding).includeItemBottom.ivFourthlyRight;
        this.rlFourthly = ((ActivityBloodfatBinding) this.mBinding).includeItemBottom.rlFourthly;
        this.mRecyclerView = ((ActivityBloodfatBinding) this.mBinding).includeItemBottom.recycleView;
        this.mNestedScrollView = ((ActivityBloodfatBinding) this.mBinding).nsv;
        this.tvYears = ((ActivityBloodfatBinding) this.mBinding).includeItemCalendar.tvYears;
        this.mCalendarView = ((ActivityBloodfatBinding) this.mBinding).includeItemCalendar.calendarView;
        this.llMonth = ((ActivityBloodfatBinding) this.mBinding).includeItemCalendar.llMonth;
        this.llCalendar.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.bloodfat.activity.BloodFatActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.tvBackToday.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.bloodfat.activity.BloodFatActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.llStartButton.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.bloodfat.activity.BloodFatActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlAnalyse.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.bloodfat.activity.BloodFatActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlFirst.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.bloodfat.activity.BloodFatActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlSecond.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.bloodfat.activity.BloodFatActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.rlFourthly.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.bloodfat.activity.BloodFatActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        this.llMonth.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.home.activity.bloodfat.activity.BloodFatActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) throws Resources.NotFoundException {
                this.f$0.onViewClicked(view);
            }
        }));
        changeTitle(getString(R.string.blood_fat));
        showBack();
        showRightImage(R.mipmap.topbar_ic_share, new NavigationBar.MyOnClickListener() { // from class: com.yucheng.smarthealthpro.home.activity.bloodfat.activity.BloodFatActivity.1
            @Override // com.yucheng.smarthealthpro.framework.view.NavigationBar.MyOnClickListener
            public void onClick(View btn) {
                ShareUtils.share(BloodFatActivity.this);
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
            this.tvAnalyse.setText(getString(R.string.home_blood_fat_analyse_tv));
            this.llStartButton.setVisibility(8);
            this.rlFirst.setVisibility(8);
            this.rlSecond.setVisibility(8);
            this.ivFourthlyRight.setBackground(ResourcesCompat.getDrawable(getResources(), R.mipmap.list_ic_arrow_n, null));
            return;
        }
        this.rlFirst.setVisibility(0);
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.IS_HAS_BLOOD_FAT_MEASUREMENT) && !Constant.isSmartHealth()) {
            this.llStartButton.setVisibility(0);
        } else {
            this.llStartButton.setVisibility(8);
        }
        this.tvStartButton.setText(getString(R.string.home_blood_fat_measure_title));
        this.tvFirst.setText(getString(R.string.include_bottom_tv_first_button));
        this.tvSecond.setText(getString(R.string.include_bottom_tv_second_button));
        this.tvAnalyse.setText(getString(R.string.home_blood_fat_analyse_tv));
        this.tvFourthly.setText(getString(R.string.include_bottom_tv_fourthly_button));
        this.ivFourthlyRight.setBackground(ResourcesCompat.getDrawable(getResources(), R.mipmap.list_ic_arrow_n, null));
    }

    private void initViewModel() {
        this.mViewModel = (BloodLipidsViewModel) new ViewModelProvider(this).get(BloodLipidsViewModel.class);
        FlowUtils.INSTANCE.collectFlow(this, this.mViewModel.getBloodLipidsDataFlow(), new FlowUtils.FlowCollector<HealthDayData<BloodLipids>>() { // from class: com.yucheng.smarthealthpro.home.activity.bloodfat.activity.BloodFatActivity.2
            @Override // com.yucheng.smarthealthpro.utils.FlowUtils.FlowCollector
            public void collect(HealthDayData<BloodLipids> data) throws Resources.NotFoundException {
                BloodFatActivity.this.onThatVeryDayData(data.getDay(), data.getData());
            }
        });
        FlowUtils.INSTANCE.collectFlow(this, this.mViewModel.getBloodLipidsPackedDataFlow(), new FlowUtils.FlowCollector<HealthPackedData<BloodLipids>>() { // from class: com.yucheng.smarthealthpro.home.activity.bloodfat.activity.BloodFatActivity.3
            @Override // com.yucheng.smarthealthpro.utils.FlowUtils.FlowCollector
            public void collect(HealthPackedData<BloodLipids> packedData) {
                if (packedData.getDayCount() == 7) {
                    BloodFatActivity.this.onDataByDays(7, packedData.getData());
                } else if (packedData.getDayCount() == 30) {
                    BloodFatActivity.this.onDataByDays(30, packedData.getData());
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
            getWeek(pastStringArray.get(0), pastStringArray.get(0), pastStringArray.get(6));
            getMonth(pastStringArray2.get(0), pastStringArray2.get(0), pastStringArray2.get(29));
            return;
        }
        new Thread(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.bloodfat.activity.BloodFatActivity.4
            @Override // java.lang.Runnable
            public void run() {
                BloodFatActivity.this.getWeekData();
                BloodFatActivity.this.getMonthData();
            }
        }).start();
    }

    private void getWeek(String dateTime, String startTime, String endTime) {
        this.mWeekAdapterBloodFatHisListBean.clear();
        this.mWeekChartSumUpBloodFatHisListBean.clear();
        HashMap map = new HashMap();
        map.put("userId", getIntent().getExtras().getString(Constant.SpConstKey.DEV_ID));
        map.put("day", dateTime);
        map.put("startDate", startTime);
        map.put("endDate", endTime);
        map.put("type", "1");
        HttpUtils.getInstance().postMsgAsynHttp(this, com.yucheng.smarthealthpro.framework.util.Constants.bloodFatDayUrl, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.home.activity.bloodfat.activity.BloodFatActivity.5
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                FriendCareBloodFatBean friendCareBloodFatBean;
                if (result != null) {
                    new ArrayList();
                    try {
                        friendCareBloodFatBean = (FriendCareBloodFatBean) BloodFatActivity.this.mGson.fromJson(result, FriendCareBloodFatBean.class);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        CrashReport.postCatchedException(e2);
                        friendCareBloodFatBean = null;
                    }
                    if (friendCareBloodFatBean == null) {
                        return;
                    }
                    BloodFatActivity.this.mWeekSumUpBloodFatNum = 0.0f;
                    BloodFatActivity.this.mWeekMaxBloodFatNum = 0.0f;
                    BloodFatActivity.this.mWeekMinBloodFatNum = TransUtils.BLOOD_FAT_VISIBLE_MAX;
                    ArrayList<String> pastStringArray = YearToDayListUtils.getPastStringArray(BloodFatActivity.this.mToDay, 6);
                    Collections.sort(friendCareBloodFatBean.data);
                    for (int i2 = 0; i2 < friendCareBloodFatBean.data.size(); i2++) {
                        FriendCareBloodFatBean.Data data = friendCareBloodFatBean.data.get(i2);
                        List list = (List) BloodFatActivity.this.mGson.fromJson(data.mlist, new TypeToken<List<FriendCareBloodFatBean.BloodFat>>() { // from class: com.yucheng.smarthealthpro.home.activity.bloodfat.activity.BloodFatActivity.5.1
                        }.getType());
                        Tools.removeCareListBloodFat(list);
                        Tools.sortCareListBloodFat(list);
                        if (list.size() != 0) {
                            float f2 = 0.0f;
                            for (int i3 = 0; i3 < list.size(); i3++) {
                                f2 += ((FriendCareBloodFatBean.BloodFat) list.get(i3)).tc;
                            }
                            float size = f2 / list.size();
                            if (BloodFatActivity.this.mWeekMaxBloodFatNum < size) {
                                BloodFatActivity.this.mWeekMaxBloodFatNum = size;
                            }
                            if (BloodFatActivity.this.mWeekMinBloodFatNum > size) {
                                BloodFatActivity.this.mWeekMinBloodFatNum = size;
                            }
                            BloodFatActivity.this.mWeekSumUpBloodFatNum += size;
                            BloodFatActivity.this.mWeekAdapterBloodFatHisListBean.add(new TemperatureHisListBean(data.dateformat, FormatUtil.keep2(BloodFatActivity.this.transform(size)), BloodFatActivity.this.getState(size), BloodFatActivity.this.unit));
                        }
                    }
                    for (int i4 = 0; i4 < pastStringArray.size(); i4++) {
                        BloodFatActivity.this.mWeekChartSumUpBloodFatHisListBean.add(new TemperatureHisListBean(pastStringArray.get(i4), "0", "", BloodFatActivity.this.unit));
                    }
                    for (int i5 = 0; i5 < pastStringArray.size(); i5++) {
                        for (TemperatureHisListBean temperatureHisListBean : BloodFatActivity.this.mWeekAdapterBloodFatHisListBean) {
                            if (pastStringArray.get(i5).equals(temperatureHisListBean.getTime())) {
                                BloodFatActivity.this.mWeekChartSumUpBloodFatHisListBean.remove(i5);
                                BloodFatActivity.this.mWeekChartSumUpBloodFatHisListBean.add(i5, new TemperatureHisListBean(temperatureHisListBean.getTime(), temperatureHisListBean.getmValue(), temperatureHisListBean.getState(), temperatureHisListBean.unit));
                            }
                        }
                    }
                    if (friendCareBloodFatBean.data.size() > 0) {
                        BloodFatActivity bloodFatActivity = BloodFatActivity.this;
                        bloodFatActivity.mWeekAverageBloodFatNum = bloodFatActivity.mWeekSumUpBloodFatNum / friendCareBloodFatBean.data.size();
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
        HttpUtils.getInstance().postMsgAsynHttp(this, com.yucheng.smarthealthpro.framework.util.Constants.bloodFatDayUrl, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.home.activity.bloodfat.activity.BloodFatActivity.6
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                FriendCareBloodFatBean friendCareBloodFatBean;
                if (result != null) {
                    new ArrayList();
                    try {
                        friendCareBloodFatBean = (FriendCareBloodFatBean) BloodFatActivity.this.mGson.fromJson(result, FriendCareBloodFatBean.class);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        CrashReport.postCatchedException(e2);
                        friendCareBloodFatBean = null;
                    }
                    if (friendCareBloodFatBean == null) {
                        return;
                    }
                    BloodFatActivity.this.mMonthAdapterBloodFatHisListBean.clear();
                    BloodFatActivity.this.mMonthChartSumUpBloodFatHisListBean.clear();
                    BloodFatActivity.this.mMonthMaxBloodFatNum = 0.0f;
                    BloodFatActivity.this.mMonthMinBloodFatNum = TransUtils.BLOOD_FAT_VISIBLE_MAX;
                    BloodFatActivity.this.mMonthAverageBloodFatNum = 0.0f;
                    BloodFatActivity.this.mLastNum = 0.0f;
                    ArrayList<String> pastStringArray = YearToDayListUtils.getPastStringArray(BloodFatActivity.this.mToDay, 29);
                    Collections.sort(friendCareBloodFatBean.data);
                    for (int i2 = 0; i2 < friendCareBloodFatBean.data.size(); i2++) {
                        FriendCareBloodFatBean.Data data = friendCareBloodFatBean.data.get(i2);
                        List list = (List) BloodFatActivity.this.mGson.fromJson(data.mlist, new TypeToken<List<FriendCareBloodFatBean.BloodFat>>() { // from class: com.yucheng.smarthealthpro.home.activity.bloodfat.activity.BloodFatActivity.6.1
                        }.getType());
                        Tools.removeCareListBloodFat(list);
                        Tools.sortCareListBloodFat(list);
                        if (list.size() != 0) {
                            float f2 = 0.0f;
                            for (int i3 = 0; i3 < list.size(); i3++) {
                                f2 += ((FriendCareBloodFatBean.BloodFat) list.get(i3)).tc;
                            }
                            float size = f2 / list.size();
                            if (BloodFatActivity.this.mMonthMaxBloodFatNum < size) {
                                BloodFatActivity.this.mMonthMaxBloodFatNum = size;
                            }
                            if (BloodFatActivity.this.mMonthMinBloodFatNum > size) {
                                BloodFatActivity.this.mMonthMinBloodFatNum = size;
                            }
                            BloodFatActivity.this.mMonthSumUpBloodFatNum += size;
                            BloodFatActivity.this.mMonthAdapterBloodFatHisListBean.add(new TemperatureHisListBean(data.dateformat, FormatUtil.keep2(BloodFatActivity.this.transform(size)), BloodFatActivity.this.getState(size), BloodFatActivity.this.unit));
                        }
                    }
                    for (int i4 = 0; i4 < pastStringArray.size(); i4++) {
                        BloodFatActivity.this.mMonthChartSumUpBloodFatHisListBean.add(new TemperatureHisListBean(pastStringArray.get(i4), "0", "", BloodFatActivity.this.unit));
                    }
                    for (int i5 = 0; i5 < pastStringArray.size(); i5++) {
                        for (TemperatureHisListBean temperatureHisListBean : BloodFatActivity.this.mMonthAdapterBloodFatHisListBean) {
                            if (pastStringArray.get(i5).equals(temperatureHisListBean.getTime())) {
                                BloodFatActivity.this.mMonthChartSumUpBloodFatHisListBean.remove(i5);
                                BloodFatActivity.this.mMonthChartSumUpBloodFatHisListBean.add(i5, new TemperatureHisListBean(temperatureHisListBean.getTime(), temperatureHisListBean.getmValue(), temperatureHisListBean.getState(), temperatureHisListBean.unit));
                            }
                        }
                    }
                    if (friendCareBloodFatBean.data.size() > 0) {
                        BloodFatActivity bloodFatActivity = BloodFatActivity.this;
                        bloodFatActivity.mMonthAverageBloodFatNum = bloodFatActivity.mMonthSumUpBloodFatNum / friendCareBloodFatBean.data.size();
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
        BloodFatTabFragmentAdapter bloodFatTabFragmentAdapter = new BloodFatTabFragmentAdapter(getSupportFragmentManager(), new BloodFatTabFragmentAdapter.FragmentCreator() { // from class: com.yucheng.smarthealthpro.home.activity.bloodfat.activity.BloodFatActivity.7
            @Override // com.yucheng.smarthealthpro.home.activity.bloodfat.adapter.BloodFatTabFragmentAdapter.FragmentCreator
            public Fragment createFragment(String data, int position) {
                return BloodFatTabFragment.newInstance(data.toString(), position, BloodFatActivity.this.mNestedScrollView, BloodFatActivity.this.mDayChartSumUpBloodFatHisListBean, BloodFatActivity.this.mWeekChartSumUpBloodFatHisListBean, BloodFatActivity.this.mMonthChartSumUpBloodFatHisListBean, BloodFatActivity.this.mDayMaxBloodFatNum);
            }

            @Override // com.yucheng.smarthealthpro.home.activity.bloodfat.adapter.BloodFatTabFragmentAdapter.FragmentCreator
            public String createTitle(String data) {
                return Html.fromHtml(data).toString();
            }
        });
        this.mAdapter = bloodFatTabFragmentAdapter;
        NoScrollViewPager noScrollViewPager = this.mViewPager;
        if (noScrollViewPager == null) {
            return;
        }
        noScrollViewPager.setAdapter(bloodFatTabFragmentAdapter);
        this.mViewPager.setOffscreenPageLimit(this.mTitles.size() - 1);
        this.mAdapter.setData(this.mTitles);
        this.mSlidingTabLayout.setViewPager(this.mViewPager, (String[]) this.mTitles.toArray(new String[0]));
        this.mSlidingTabLayout.setCurrentTab(2);
        this.mViewPager.addOnPageChangeListener(new OnPageChangeListenerImpl());
    }

    public void dataAnalysis(float value) {
        if (value >= TransUtils.BLOOD_FAT_VISIBLE_MIN && value <= 5.2f) {
            this.tvAnalyseData.setText(getText(R.string.home_blood_fat_is_normal));
        } else if (value > 5.2f && value <= TransUtils.BLOOD_FAT_VISIBLE_MAX) {
            this.tvAnalyseData.setText(getText(R.string.home_blood_fat_is_abnormal));
        } else {
            this.tvAnalyseData.setText("");
        }
    }

    private void setRecycleView() {
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        BloodFatHisListAdapter bloodFatHisListAdapter = new BloodFatHisListAdapter(R.layout.item_universal_his_list);
        this.mBloodFatHisListAdapter = bloodFatHisListAdapter;
        bloodFatHisListAdapter.addData((Collection) this.mDayBloodFatHisListBean);
        this.mRecyclerView.setAdapter(this.mBloodFatHisListAdapter);
        this.mRecyclerView.setNestedScrollingEnabled(false);
    }

    private void initMonth() throws Resources.NotFoundException {
        this.mCalendarView.setOnCalendarSelectListener(this);
        this.mCalendarView.setOnMonthChangeListener(this);
        this.mCalendarView.scrollToCurrent();
        this.mCalendarView.setOnCalendarInterceptListener(new CalendarView.OnCalendarInterceptListener() { // from class: com.yucheng.smarthealthpro.home.activity.bloodfat.activity.BloodFatActivity.8
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
        HttpUtils.getInstance().postMsgAsynHttp(this, com.yucheng.smarthealthpro.framework.util.Constants.bloodFatDayUrl, map, new HttpUtils.HttpCallback() { // from class: com.yucheng.smarthealthpro.home.activity.bloodfat.activity.BloodFatActivity.9
            @Override // com.yucheng.smarthealthpro.framework.http.HttpUtils.HttpCallback
            public void onSuccess(String result) {
                if (result != null) {
                    try {
                        BloodFatActivity bloodFatActivity = BloodFatActivity.this;
                        bloodFatActivity.temp_bean = (FriendCareBloodFatBean) bloodFatActivity.mGson.fromJson(result, FriendCareBloodFatBean.class);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        CrashReport.postCatchedException(e2);
                    }
                    BloodFatActivity.this.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.bloodfat.activity.BloodFatActivity.9.1
                        @Override // java.lang.Runnable
                        public void run() throws Resources.NotFoundException {
                            BloodFatActivity.this.setDayData();
                            BloodFatActivity.this.initViewPager();
                        }
                    });
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v10, types: [java.util.List] */
    public void setDayData() {
        List<TemperatureHisListBean> list = this.mDayBloodFatHisListBean;
        if (list != null) {
            list.clear();
        }
        List<TemperatureHisListBean> list2 = this.mDayChartSumUpBloodFatHisListBean;
        if (list2 != null) {
            list2.clear();
        }
        this.mDaySumUpBloodFatNum = 0.0f;
        this.mDayMaxBloodFatNum = 0.0f;
        this.mDayMinBloodFatNum = TransUtils.BLOOD_FAT_VISIBLE_MAX;
        if (this.temp_bean == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        try {
            Iterator<FriendCareBloodFatBean.Data> it2 = this.temp_bean.data.iterator();
            while (it2.hasNext()) {
                arrayList = (List) new Gson().fromJson(it2.next().mlist, new TypeToken<List<FriendCareBloodFatBean.BloodFat>>() { // from class: com.yucheng.smarthealthpro.home.activity.bloodfat.activity.BloodFatActivity.10
                }.getType());
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        Tools.removeCareListBloodFat(arrayList);
        Tools.sortCareListBloodFat(arrayList);
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            float f2 = ((FriendCareBloodFatBean.BloodFat) arrayList.get(i2)).tc;
            if (f2 > this.mDayMaxBloodFatNum) {
                this.mDayMaxBloodFatNum = f2;
            }
            if (f2 < this.mDayMinBloodFatNum) {
                this.mDayMinBloodFatNum = f2;
            }
            this.mDaySumUpBloodFatNum += f2;
            float fTransform = transform(f2);
            TemperatureHisListBean temperatureHisListBean = new TemperatureHisListBean(TimeStampUtils.dateForStringToDate(TimeStampUtils.longStampForDate(((FriendCareBloodFatBean.BloodFat) arrayList.get(i2)).rtime)), FormatUtil.keep2(fTransform), getState(f2), this.unit);
            temperatureHisListBean.setModel(((FriendCareBloodFatBean.BloodFat) arrayList.get(i2)).cMode);
            this.mDayBloodFatHisListBean.add(temperatureHisListBean);
            this.mDayChartSumUpBloodFatHisListBean.add(new TemperatureHisListBean(TimeStampUtils.dateForStringToDate(TimeStampUtils.longStampForDate(((FriendCareBloodFatBean.BloodFat) arrayList.get(i2)).rtime)), FormatUtil.keep2(fTransform), getState(f2), this.unit));
        }
        if (this.mDayBloodFatHisListBean.size() != 0) {
            this.mDayAverageBloodFatNum = this.mDaySumUpBloodFatNum / this.mDayBloodFatHisListBean.size();
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

    public void onThatVeryDayData(String thatVeryDay, List<BloodLipids> data) throws Resources.NotFoundException {
        this.mDayBloodFatHisListBean.clear();
        this.mDayChartSumUpBloodFatHisListBean.clear();
        this.bloodFatDbs = data;
        this.mThatVeryDay = thatVeryDay;
        this.mDaySumUpBloodFatNum = 0.0f;
        this.mDayMaxBloodFatNum = 0.0f;
        this.mDayMinBloodFatNum = TransUtils.BLOOD_FAT_VISIBLE_MAX;
        this.mDayAverageBloodFatNum = 0.0f;
        this.mLastNum = 0.0f;
        if (this.bloodFatDbs != null) {
            for (int i2 = 0; i2 < this.bloodFatDbs.size(); i2++) {
                if (TimeStampUtils.dateForStringYearToDate(TimeStampUtils.longStampForDate(this.bloodFatDbs.get(i2).getStartTimestamp())).equals(thatVeryDay) && this.bloodFatDbs.get(i2).getCholesterol() != 0.0f) {
                    float cholesterol = this.bloodFatDbs.get(i2).getCholesterol();
                    if (cholesterol >= TransUtils.BLOOD_FAT_VISIBLE_MIN && cholesterol <= TransUtils.BLOOD_FAT_VISIBLE_MAX) {
                        String strDateForStringToDate = TimeStampUtils.dateForStringToDate(TimeStampUtils.longStampForDate(this.bloodFatDbs.get(i2).getStartTimestamp()));
                        float fTransform = transform(cholesterol);
                        TemperatureHisListBean temperatureHisListBean = new TemperatureHisListBean(strDateForStringToDate, FormatUtil.keep2(fTransform), "");
                        temperatureHisListBean.setModel(this.bloodFatDbs.get(i2).getMeasureMode());
                        this.mDayBloodFatHisListBean.add(temperatureHisListBean);
                        this.mDayChartSumUpBloodFatHisListBean.add(new TemperatureHisListBean(strDateForStringToDate, FormatUtil.keep2(fTransform), ""));
                        if (this.mLastNum == 0.0f) {
                            this.mLastNum = cholesterol;
                        }
                        if (cholesterol > this.mDayMaxBloodFatNum) {
                            this.mDayMaxBloodFatNum = cholesterol;
                        }
                        if (cholesterol < this.mDayMinBloodFatNum) {
                            this.mDayMinBloodFatNum = cholesterol;
                        }
                        this.mDaySumUpBloodFatNum += cholesterol;
                    }
                }
            }
        }
        if (this.mDayBloodFatHisListBean.size() != 0) {
            this.mDayAverageBloodFatNum = this.mDaySumUpBloodFatNum / this.mDayBloodFatHisListBean.size();
        }
        initViewPager();
    }

    public void getWeekData() {
        this.mWeekAdapterBloodFatHisListBean.clear();
        this.mWeekChartSumUpBloodFatHisListBean.clear();
        this.mWeekMaxBloodFatNum = 0.0f;
        this.mWeekMinBloodFatNum = 10000.0f;
        this.mWeekAverageBloodFatNum = 0.0f;
        this.mLastNum = 0.0f;
        this.mViewModel.getPackedDayData(YearToDayListUtils.getPastStringArray(this.mToDay, 6).get(0), 7);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onDataByDays(int days, List<BloodLipids> data) {
        float f2;
        int size;
        ArrayList<String> pastStringArray = YearToDayListUtils.getPastStringArray(this.mToDay, days - 1);
        float f3 = 0.0f;
        for (int i2 = 0; i2 < days; i2++) {
            List<BloodLipids> listFilterBloodLipidsByDate = HealthDataFilterKt.filterBloodLipidsByDate(data, pastStringArray.get(i2));
            this.bloodFatDbs = listFilterBloodLipidsByDate;
            if (listFilterBloodLipidsByDate != null) {
                size = listFilterBloodLipidsByDate.size();
                f2 = 0.0f;
                for (int i3 = 0; i3 < this.bloodFatDbs.size(); i3++) {
                    float cholesterol = this.bloodFatDbs.get(i3).getCholesterol();
                    if (cholesterol < TransUtils.BLOOD_FAT_VISIBLE_MIN || cholesterol > TransUtils.BLOOD_FAT_VISIBLE_MAX) {
                        size--;
                    } else {
                        f2 += cholesterol;
                    }
                }
            } else {
                f2 = 0.0f;
                size = 0;
            }
            if (f2 != 0.0f) {
                float f4 = f2 / size;
                f3 += f4;
                if (this.mLastNum == 0.0f) {
                    this.mLastNum = f4;
                }
                if (days == 7) {
                    if (f4 > this.mWeekMaxBloodFatNum) {
                        this.mWeekMaxBloodFatNum = f4;
                    }
                    if (f4 < this.mWeekMinBloodFatNum) {
                        this.mWeekMinBloodFatNum = f4;
                    }
                    float fTransform = transform(f4);
                    this.mWeekAdapterBloodFatHisListBean.add(new TemperatureHisListBean(pastStringArray.get(i2), FormatUtil.keep2(fTransform), ""));
                    this.mWeekChartSumUpBloodFatHisListBean.add(new TemperatureHisListBean(TimeStampUtils.toFormatDate(pastStringArray.get(i2)), FormatUtil.keep2(fTransform), ""));
                } else if (days == 30) {
                    if (f4 > this.mMonthMaxBloodFatNum) {
                        this.mMonthMaxBloodFatNum = f4;
                    }
                    if (f4 < this.mMonthMinBloodFatNum) {
                        this.mMonthMinBloodFatNum = f4;
                    }
                    float fTransform2 = transform(f4);
                    this.mMonthAdapterBloodFatHisListBean.add(new TemperatureHisListBean(pastStringArray.get(i2), FormatUtil.keep2(fTransform2), ""));
                    this.mMonthChartSumUpBloodFatHisListBean.add(new TemperatureHisListBean(TimeStampUtils.toFormatDate(pastStringArray.get(i2)), FormatUtil.keep2(fTransform2), ""));
                }
            } else if (days == 7) {
                this.mWeekChartSumUpBloodFatHisListBean.add(new TemperatureHisListBean(TimeStampUtils.toFormatDate(pastStringArray.get(i2)), "0", ""));
            } else if (days == 30) {
                this.mMonthChartSumUpBloodFatHisListBean.add(new TemperatureHisListBean(TimeStampUtils.toFormatDate(pastStringArray.get(i2)), "0", ""));
            }
        }
        if (f3 != 0.0f) {
            if (days == 7 && this.mWeekAdapterBloodFatHisListBean.size() > 0) {
                Collections.reverse(this.mWeekAdapterBloodFatHisListBean);
                this.mWeekAverageBloodFatNum = f3 / this.mWeekAdapterBloodFatHisListBean.size();
            } else {
                if (days != 30 || this.mMonthAdapterBloodFatHisListBean.size() <= 0) {
                    return;
                }
                Collections.reverse(this.mMonthAdapterBloodFatHisListBean);
                this.mMonthAverageBloodFatNum = f3 / this.mMonthAdapterBloodFatHisListBean.size();
            }
        }
    }

    public void getMonthData() {
        this.mMonthAdapterBloodFatHisListBean.clear();
        this.mMonthChartSumUpBloodFatHisListBean.clear();
        this.mMonthMaxBloodFatNum = 0.0f;
        this.mMonthMinBloodFatNum = 10000.0f;
        this.mMonthAverageBloodFatNum = 0.0f;
        this.mLastNum = 0.0f;
        this.mViewModel.getPackedDayData(YearToDayListUtils.getPastStringArray(this.mToDay, 29).get(0), 30);
        runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.bloodfat.activity.BloodFatActivity.11
            @Override // java.lang.Runnable
            public void run() throws Resources.NotFoundException {
                BloodFatActivity.this.initViewPager();
            }
        });
    }

    public void onViewClicked(View view) throws Resources.NotFoundException {
        if (view.getId() == R.id.ll_calendar) {
            this.llMonth.setVisibility(0);
            return;
        }
        if (view.getId() == R.id.ll_calendar) {
            this.mViewPager.setCurrentItem(2);
            this.mCalendarView.scrollToCurrent();
            return;
        }
        if (view.getId() == R.id.ll_start_button) {
            BloodFatMeasureActivity.load(this);
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
                this.mNestedScrollView.postDelayed(new Runnable() { // from class: com.yucheng.smarthealthpro.home.activity.bloodfat.activity.BloodFatActivity.12
                    @Override // java.lang.Runnable
                    public void run() {
                        BloodFatActivity.this.mNestedScrollView.smoothScrollTo(0, (int) (BloodFatActivity.this.mNestedScrollView.getScrollY() + (DpUtil.dp2px(BloodFatActivity.this.context, 56.0f) * 1.5f)));
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
        if (requestCode == BloodFatMeasureActivity.BLOOD_FAT_MEASURE && resultCode == -1) {
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
            BloodFatActivity.this.mViewPager.setCurrentItem(position);
            if (position == 0) {
                BloodFatActivity.this.freshMonthData();
            } else if (position == 1) {
                BloodFatActivity.this.freshWeekData();
            } else {
                if (position != 2) {
                    return;
                }
                BloodFatActivity.this.freshDayData();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void freshDayData() {
        this.tvBackToday.setVisibility(8);
        this.llCalendar.setVisibility(0);
        List<TemperatureHisListBean> list = this.mDayBloodFatHisListBean;
        if (list != null && list.size() > 0) {
            if (Constant.isTechFeel()) {
                this.tvDataFirst.setText(this.mLastNum + "");
            } else {
                this.tvDataFirst.setText(transformStr(this.mDayAverageBloodFatNum));
            }
            this.tvDataSecond.setText(transformStr(this.mDayMaxBloodFatNum));
            this.tvDataThirdly.setText(transformStr(this.mDayMinBloodFatNum));
        } else {
            this.tvDataFirst.setText("--");
            this.tvDataSecond.setText("--");
            this.tvDataThirdly.setText("--");
            this.mDayAverageBloodFatNum = 0.0f;
        }
        this.mBloodFatHisListAdapter.setList(this.mDayBloodFatHisListBean);
        this.mBloodFatHisListAdapter.notifyDataSetChanged();
        dataAnalysis(this.mDayAverageBloodFatNum);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void freshWeekData() {
        this.tvBackToday.setVisibility(0);
        this.llCalendar.setVisibility(8);
        List<TemperatureHisListBean> list = this.mWeekAdapterBloodFatHisListBean;
        if (list != null && list.size() > 0) {
            if (Constant.isTechFeel()) {
                this.tvDataFirst.setText(this.mLastNum + "");
            } else {
                this.tvDataFirst.setText(transformStr(this.mWeekAverageBloodFatNum));
            }
            this.tvDataSecond.setText(transformStr(this.mWeekMaxBloodFatNum));
            this.tvDataThirdly.setText(transformStr(this.mWeekMinBloodFatNum));
        } else {
            this.tvDataFirst.setText("--");
            this.tvDataSecond.setText("--");
            this.tvDataThirdly.setText("--");
            this.mWeekAverageBloodFatNum = 0.0f;
        }
        this.mBloodFatHisListAdapter.setList(this.mWeekAdapterBloodFatHisListBean);
        this.mBloodFatHisListAdapter.notifyDataSetChanged();
        dataAnalysis(this.mWeekAverageBloodFatNum);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void freshMonthData() {
        this.tvBackToday.setVisibility(0);
        this.llCalendar.setVisibility(8);
        List<TemperatureHisListBean> list = this.mMonthAdapterBloodFatHisListBean;
        if (list != null && list.size() > 0) {
            if (Constant.isTechFeel()) {
                this.tvDataFirst.setText(this.mLastNum + "");
            } else {
                this.tvDataFirst.setText(transformStr(this.mMonthAverageBloodFatNum));
            }
            this.tvDataSecond.setText(transformStr(this.mMonthMaxBloodFatNum));
            this.tvDataThirdly.setText(transformStr(this.mMonthMinBloodFatNum));
        } else {
            this.tvDataFirst.setText("--");
            this.tvDataSecond.setText("--");
            this.tvDataThirdly.setText("--");
            this.mMonthAverageBloodFatNum = 0.0f;
        }
        this.mBloodFatHisListAdapter.setList(this.mMonthAdapterBloodFatHisListBean);
        this.mBloodFatHisListAdapter.notifyDataSetChanged();
        dataAnalysis(this.mMonthAverageBloodFatNum);
    }

    public String getState(float value) {
        if (value > 5.2f) {
            return getString(R.string.home_blood_fat_is_abnormal);
        }
        return getString(R.string.home_blood_fat_is_normal);
    }

    public float transform(float value) {
        return getString(R.string.blood_sugar_unit_2).equals(this.unit) ? value * TransUtils.BLOOD_FAT_TRANS : value;
    }

    public String transformStr(float value) {
        if (getString(R.string.blood_sugar_unit_2).equals(this.unit)) {
            return FormatUtil.keep2(value * TransUtils.BLOOD_FAT_TRANS);
        }
        return FormatUtil.keep2(value);
    }
}
