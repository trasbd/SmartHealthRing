package com.yucheng.smarthealthpro.home.activity.sleep.fragment;

import android.content.Context;
import android.view.View;
import androidx.core.widget.NestedScrollView;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseLazyLoadFragment;
import com.yucheng.smarthealthpro.customchart.charts.BarChart;
import com.yucheng.smarthealthpro.customchart.sleep.SleepBarChartUtils;
import com.yucheng.smarthealthpro.databinding.FragmentSleeptabBinding;
import com.yucheng.smarthealthpro.home.activity.sleep.bean.SleepDayInfo;
import com.yucheng.smarthealthpro.home.activity.sleep.bean.SleepHisListBean;
import com.yucheng.smarthealthpro.utils.SleepHelperKt;
import com.yucheng.smarthealthpro.view.SleepQualityWrapView;
import java.util.List;

/* loaded from: classes5.dex */
public class SleepTabFragment extends BaseLazyLoadFragment<FragmentSleeptabBinding> {
    private List<SleepDayInfo> mDaySleepDataBeans;
    private List<SleepHisListBean> mMonthSleepChartHisListBean;
    private NestedScrollView mNestedScrollView;
    BarChart mSleepMonthChart;
    SleepQualityWrapView mSleepQualityWrapView;
    BarChart mSleepWeekChart;
    private String mTitles;
    private List<SleepHisListBean> mWeekSleepChartHisListBean;
    private int monthData;
    private Integer position;
    private int mDayXLabelCount = 2;
    private int mDayYLabelCount = 7;
    private int mWeekXLabelCount = 6;
    private int mWeekYLabelCount = 7;
    private int mMonthXLabelCount = 2;
    private int mMonthYLabelCount = 7;
    private float xDayMaximum = 48.0f;
    private float xDayMinimum = 0.0f;
    private float xWeekMaximum = 7.0f;
    private float xWeekMinimum = 0.0f;
    private float xMonthMaximum = 30.0f;
    private float xMonthMinimum = 0.0f;
    private float yMaximum = 140.0f;
    private float yMinimum = 0.0f;

    @Override // com.yucheng.smarthealthpro.framework.BaseFragment
    protected void initData(Context mContext) {
    }

    @Override // com.gyf.immersionbar.components.ImmersionOwner
    public void initImmersionBar() {
    }

    public static SleepTabFragment newInstance(String mTitles, int position, NestedScrollView mNestedScrollView, int monthData, List<SleepDayInfo> mSleepDataBeans, List<SleepHisListBean> mWeekSleepChartHisListBean, List<SleepHisListBean> mMonthSleepChartHisListBean) {
        SleepTabFragment sleepTabFragment = new SleepTabFragment();
        sleepTabFragment.mTitles = mTitles;
        sleepTabFragment.position = Integer.valueOf(position);
        sleepTabFragment.monthData = monthData;
        sleepTabFragment.mNestedScrollView = mNestedScrollView;
        sleepTabFragment.mDaySleepDataBeans = mSleepDataBeans;
        sleepTabFragment.mWeekSleepChartHisListBean = mWeekSleepChartHisListBean;
        sleepTabFragment.mMonthSleepChartHisListBean = mMonthSleepChartHisListBean;
        return sleepTabFragment;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.yucheng.smarthealthpro.framework.BaseFragment
    protected void initView(View view) {
        this.mSleepQualityWrapView = ((FragmentSleeptabBinding) getMViewBind()).sleepQualityWrapView;
        this.mSleepWeekChart = ((FragmentSleeptabBinding) getMViewBind()).barChartWeek;
        this.mSleepMonthChart = ((FragmentSleeptabBinding) getMViewBind()).barChartMonth;
    }

    @Override // com.yucheng.smarthealthpro.base.BaseLazyLoadFragment
    protected void lazyLoadData() {
        if (this.mTitles.equals(getString(R.string.date_month_unit))) {
            this.mSleepQualityWrapView.setVisibility(8);
            this.mSleepWeekChart.setVisibility(8);
            this.mSleepMonthChart.setVisibility(0);
            SleepBarChartUtils.initBarChart(this.mSleepMonthChart, this.context, this.mMonthSleepChartHisListBean, this.mNestedScrollView, this.yMaximum, this.yMinimum, this.xMonthMaximum, this.mMonthXLabelCount, SleepBarChartUtils.FORMATTER.MONTH);
            return;
        }
        if (this.mTitles.equals(getString(R.string.date_week_unit))) {
            this.mSleepQualityWrapView.setVisibility(8);
            this.mSleepWeekChart.setVisibility(0);
            this.mSleepMonthChart.setVisibility(8);
            SleepBarChartUtils.initBarChart(this.mSleepWeekChart, this.context, this.mWeekSleepChartHisListBean, this.mNestedScrollView, this.yMaximum, this.yMinimum, this.xWeekMaximum, this.mWeekXLabelCount, SleepBarChartUtils.FORMATTER.WEEK);
            return;
        }
        this.mSleepQualityWrapView.setVisibility(0);
        this.mSleepWeekChart.setVisibility(8);
        this.mSleepMonthChart.setVisibility(8);
        this.mSleepQualityWrapView.setData(SleepHelperKt.mapToSleepStageList(this.mDaySleepDataBeans));
    }
}
