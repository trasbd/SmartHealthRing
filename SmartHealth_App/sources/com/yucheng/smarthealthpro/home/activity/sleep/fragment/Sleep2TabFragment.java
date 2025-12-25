package com.yucheng.smarthealthpro.home.activity.sleep.fragment;

import android.content.Context;
import android.view.View;
import androidx.core.widget.NestedScrollView;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseLazyLoadFragment;
import com.yucheng.smarthealthpro.customchart.charts.BarChart;
import com.yucheng.smarthealthpro.customchart.sleep.SleepBarChartUtils;
import com.yucheng.smarthealthpro.databinding.FragmentSleep2Binding;
import com.yucheng.smarthealthpro.home.activity.sleep.bean.SleepDayInfo;
import com.yucheng.smarthealthpro.home.activity.sleep.bean.SleepHisListBean;
import com.yucheng.smarthealthpro.view.chart.SleepChart;
import java.util.List;

/* loaded from: classes5.dex */
public class Sleep2TabFragment extends BaseLazyLoadFragment<FragmentSleep2Binding> {
    private List<SleepDayInfo> mDaySleepDataBeans;
    private List<SleepHisListBean> mMonthSleepChartHisListBean;
    private NestedScrollView mNestedScrollView;
    SleepChart mSleepDayChart;
    BarChart mSleepMonthChart;
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

    public static Sleep2TabFragment newInstance(String mTitles, int position, NestedScrollView mNestedScrollView, int monthData, List<SleepDayInfo> mSleepDataBeans, List<SleepHisListBean> mWeekSleepChartHisListBean, List<SleepHisListBean> mMonthSleepChartHisListBean) {
        Sleep2TabFragment sleep2TabFragment = new Sleep2TabFragment();
        sleep2TabFragment.mTitles = mTitles;
        sleep2TabFragment.position = Integer.valueOf(position);
        sleep2TabFragment.monthData = monthData;
        sleep2TabFragment.mNestedScrollView = mNestedScrollView;
        sleep2TabFragment.mDaySleepDataBeans = mSleepDataBeans;
        sleep2TabFragment.mWeekSleepChartHisListBean = mWeekSleepChartHisListBean;
        sleep2TabFragment.mMonthSleepChartHisListBean = mMonthSleepChartHisListBean;
        return sleep2TabFragment;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.yucheng.smarthealthpro.framework.BaseFragment
    protected void initView(View view) {
        this.mSleepDayChart = ((FragmentSleep2Binding) getMViewBind()).lineChartDay;
        this.mSleepWeekChart = ((FragmentSleep2Binding) getMViewBind()).barChartWeek;
        this.mSleepMonthChart = ((FragmentSleep2Binding) getMViewBind()).barChartMonth;
    }

    @Override // com.yucheng.smarthealthpro.base.BaseLazyLoadFragment
    protected void lazyLoadData() {
        if (this.mTitles.equals(getString(R.string.date_month_unit))) {
            this.mSleepDayChart.setVisibility(8);
            this.mSleepWeekChart.setVisibility(8);
            this.mSleepMonthChart.setVisibility(0);
            SleepBarChartUtils.initBarChart(this.mSleepMonthChart, this.context, this.mMonthSleepChartHisListBean, this.mNestedScrollView, this.yMaximum, this.yMinimum, this.xMonthMaximum, this.mMonthXLabelCount, SleepBarChartUtils.FORMATTER.MONTH);
            return;
        }
        if (this.mTitles.equals(getString(R.string.date_week_unit))) {
            this.mSleepDayChart.setVisibility(8);
            this.mSleepWeekChart.setVisibility(0);
            this.mSleepMonthChart.setVisibility(8);
            SleepBarChartUtils.initBarChart(this.mSleepWeekChart, this.context, this.mWeekSleepChartHisListBean, this.mNestedScrollView, this.yMaximum, this.yMinimum, this.xWeekMaximum, this.mWeekXLabelCount, SleepBarChartUtils.FORMATTER.WEEK);
            return;
        }
        this.mSleepDayChart.setVisibility(0);
        this.mSleepWeekChart.setVisibility(8);
        this.mSleepMonthChart.setVisibility(8);
    }
}
