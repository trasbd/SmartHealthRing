package com.yucheng.smarthealthpro.home.activity.heartrate.fragment;

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
public class HeartRateTabFragment extends BaseLazyLoadFragment<FragmentTemptabBinding> {
    private List<TemperatureHisListBean> mDayHeartRateHisListBean;
    private float mMaxStepNum;
    private List<TemperatureHisListBean> mMonthSumUpHeartRateHisListBean;
    private NestedScrollView mNestedScrollView;
    LineChart mTempDayChart;
    LineChart mTempMonthChart;
    LineChart mTempWeekChart;
    private String mTitles;
    private String mToDay;
    private List<TemperatureHisListBean> mWeekSumUpHeartRateHisListBean;
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
    private float yMaximum = 220.0f;
    private float yMinimum = 0.0f;
    private int minYNum = 0;

    @Override // com.yucheng.smarthealthpro.framework.BaseFragment
    protected void initData(Context mContext) {
    }

    @Override // com.gyf.immersionbar.components.ImmersionOwner
    public void initImmersionBar() {
    }

    public static HeartRateTabFragment newInstance(String mTitles, int position, NestedScrollView mNestedScrollView, List<TemperatureHisListBean> mDayHeartRateHisListBean, List<TemperatureHisListBean> mWeekSumUpHeartRateHisListBean, List<TemperatureHisListBean> mMonthSumUpHeartRateHisListBean, float mMaxStepNum) {
        HeartRateTabFragment heartRateTabFragment = new HeartRateTabFragment();
        heartRateTabFragment.mTitles = mTitles;
        heartRateTabFragment.position = Integer.valueOf(position);
        heartRateTabFragment.mNestedScrollView = mNestedScrollView;
        heartRateTabFragment.mDayHeartRateHisListBean = mDayHeartRateHisListBean;
        heartRateTabFragment.mWeekSumUpHeartRateHisListBean = mWeekSumUpHeartRateHisListBean;
        heartRateTabFragment.mMonthSumUpHeartRateHisListBean = mMonthSumUpHeartRateHisListBean;
        heartRateTabFragment.mMaxStepNum = mMaxStepNum;
        return heartRateTabFragment;
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
        if (getString(R.string.date_month_unit).equals(this.mTitles)) {
            this.mTempDayChart.setVisibility(8);
            this.mTempWeekChart.setVisibility(8);
            this.mTempMonthChart.setVisibility(0);
            TempLineChartUtils.initLineChart(this.mTempMonthChart, this.context, this.mMonthSumUpHeartRateHisListBean, this.mToDay, this.mNestedScrollView, TempLineChartUtils.FUNCTION.HR, this.yMaximum, this.yMinimum, this.xMonthMaximum, this.mMonthXLabelCount, this.mMonthYLabelCount, TempLineChartUtils.FORMATTER.MONTH);
            return;
        }
        if (getString(R.string.date_week_unit).equals(this.mTitles)) {
            this.mTempDayChart.setVisibility(8);
            this.mTempWeekChart.setVisibility(0);
            this.mTempMonthChart.setVisibility(8);
            TempLineChartUtils.initLineChart(this.mTempWeekChart, this.context, this.mWeekSumUpHeartRateHisListBean, this.mToDay, this.mNestedScrollView, TempLineChartUtils.FUNCTION.HR, this.yMaximum, this.yMinimum, this.xWeekMaximum, this.mWeekXLabelCount, this.mWeekYLabelCount, TempLineChartUtils.FORMATTER.WEEK);
            return;
        }
        this.mTempDayChart.setVisibility(0);
        this.mTempWeekChart.setVisibility(8);
        this.mTempMonthChart.setVisibility(8);
        TempLineChartUtils.initLineChart(this.mTempDayChart, this.context, this.mDayHeartRateHisListBean, this.mToDay, this.mNestedScrollView, TempLineChartUtils.FUNCTION.HR, this.yMaximum, this.yMinimum, this.xDayMaximum, this.mDayXLabelCount, this.mDayYLabelCount, TempLineChartUtils.FORMATTER.DAY);
    }
}
