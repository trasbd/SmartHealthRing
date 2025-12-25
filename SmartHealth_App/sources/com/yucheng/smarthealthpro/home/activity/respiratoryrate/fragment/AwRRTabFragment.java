package com.yucheng.smarthealthpro.home.activity.respiratoryrate.fragment;

import android.content.Context;
import android.view.View;
import androidx.core.widget.NestedScrollView;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseLazyLoadFragment;
import com.yucheng.smarthealthpro.customchart.charts.LineChart;
import com.yucheng.smarthealthpro.customchart.temperature.TempLineChartUtils;
import com.yucheng.smarthealthpro.databinding.FragmentTemptabBinding;
import com.yucheng.smarthealthpro.home.activity.temperature.bean.TemperatureHisListBean;
import java.util.Calendar;
import java.util.List;

/* loaded from: classes5.dex */
public class AwRRTabFragment extends BaseLazyLoadFragment<FragmentTemptabBinding> {
    private float mMaxStepNum;
    private List<TemperatureHisListBean> mMonthSumUpTemperatureHisListBean;
    private NestedScrollView mNestedScrollView;
    private List<TemperatureHisListBean> mRespiratoryRateHisListBean;
    LineChart mTempDayChart;
    LineChart mTempMonthChart;
    LineChart mTempWeekChart;
    private String mTitles;
    private String mToDay;
    private List<TemperatureHisListBean> mWeekSumUpTemperatureHisListBean;
    private int monthData;
    private Integer position;
    private int mDayXLabelCount = 5;
    private int mDayYLabelCount = 5;
    private int mWeekXLabelCount = 7;
    private int mWeekYLabelCount = 5;
    private int mMonthXLabelCount = 2;
    private int mMonthYLabelCount = 5;
    private float xDayMaximum = 1440.0f;
    private float xDayMinimum = 0.0f;
    private float xWeekMaximum = 6.0f;
    private float xWeekMinimum = 0.0f;
    private float xMonthMaximum = 30.0f;
    private float xMonthMinimum = 0.0f;
    private float yMaximum = 30.0f;
    private float yMinimum = 0.0f;
    private int minYNum = 0;
    private String mThatVeryDay = "";

    @Override // com.yucheng.smarthealthpro.framework.BaseFragment
    protected void initData(Context mContext) {
    }

    @Override // com.gyf.immersionbar.components.ImmersionOwner
    public void initImmersionBar() {
    }

    public static AwRRTabFragment newInstance(String mTitles, int position, NestedScrollView mNestedScrollView, int monthData, List<TemperatureHisListBean> mRespiratoryRateHisListBean, List<TemperatureHisListBean> mWeekSumUpTemperatureHisListBean, List<TemperatureHisListBean> mMonthSumUpTemperatureHisListBean, float mMaxStepNum, String mThatVeryDay) {
        AwRRTabFragment awRRTabFragment = new AwRRTabFragment();
        awRRTabFragment.mTitles = mTitles;
        awRRTabFragment.position = Integer.valueOf(position);
        awRRTabFragment.monthData = monthData;
        awRRTabFragment.mNestedScrollView = mNestedScrollView;
        awRRTabFragment.mRespiratoryRateHisListBean = mRespiratoryRateHisListBean;
        awRRTabFragment.mWeekSumUpTemperatureHisListBean = mWeekSumUpTemperatureHisListBean;
        awRRTabFragment.mMonthSumUpTemperatureHisListBean = mMonthSumUpTemperatureHisListBean;
        awRRTabFragment.mMaxStepNum = mMaxStepNum;
        awRRTabFragment.mThatVeryDay = mThatVeryDay;
        return awRRTabFragment;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.yucheng.smarthealthpro.framework.BaseFragment
    protected void initView(View view) {
        this.mTempDayChart = ((FragmentTemptabBinding) getMViewBind()).lineChartDay;
        this.mTempWeekChart = ((FragmentTemptabBinding) getMViewBind()).lineChartWeek;
        this.mTempMonthChart = ((FragmentTemptabBinding) getMViewBind()).lineChartMonth;
        Calendar calendar = Calendar.getInstance();
        this.mToDay = calendar.get(1) + "-" + (calendar.get(2) + 1) + "-" + calendar.get(5);
    }

    @Override // com.yucheng.smarthealthpro.base.BaseLazyLoadFragment
    protected void lazyLoadData() throws NumberFormatException {
        if (this.mTitles.equals(getString(R.string.date_month_unit))) {
            this.mTempDayChart.setVisibility(8);
            this.mTempWeekChart.setVisibility(8);
            this.mTempMonthChart.setVisibility(0);
            TempLineChartUtils.initLineChart(this.mTempMonthChart, this.context, this.mMonthSumUpTemperatureHisListBean, this.mToDay, this.mNestedScrollView, TempLineChartUtils.FUNCTION.AWRR, this.yMaximum, this.yMinimum, this.xMonthMaximum, this.mMonthXLabelCount, this.mMonthYLabelCount, TempLineChartUtils.FORMATTER.MONTH);
            return;
        }
        if (this.mTitles.equals(getString(R.string.date_week_unit))) {
            this.mTempDayChart.setVisibility(8);
            this.mTempWeekChart.setVisibility(0);
            this.mTempMonthChart.setVisibility(8);
            TempLineChartUtils.initLineChart(this.mTempWeekChart, this.context, this.mWeekSumUpTemperatureHisListBean, this.mToDay, this.mNestedScrollView, TempLineChartUtils.FUNCTION.AWRR, this.yMaximum, this.yMinimum, this.xWeekMaximum, this.mWeekXLabelCount, this.mWeekYLabelCount, TempLineChartUtils.FORMATTER.WEEK);
            return;
        }
        this.mTempDayChart.setVisibility(0);
        this.mTempWeekChart.setVisibility(8);
        this.mTempMonthChart.setVisibility(8);
        TempLineChartUtils.initLineChart(this.mTempDayChart, this.context, this.mRespiratoryRateHisListBean, this.mToDay, this.mNestedScrollView, TempLineChartUtils.FUNCTION.AWRR, this.yMaximum, this.yMinimum, this.xDayMaximum, this.mDayXLabelCount, this.mDayYLabelCount, TempLineChartUtils.FORMATTER.DAY);
    }
}
