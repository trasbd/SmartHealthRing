package com.yucheng.smarthealthpro.home.activity.running.fragment;

import android.content.Context;
import android.view.View;
import androidx.core.widget.NestedScrollView;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseLazyLoadFragment;
import com.yucheng.smarthealthpro.customchart.charts.GradualBarChart;
import com.yucheng.smarthealthpro.customchart.step.StepBarChartUtils;
import com.yucheng.smarthealthpro.databinding.FragmentSteptabBinding;
import com.yucheng.smarthealthpro.home.activity.running.bean.RunningHisListBean;
import java.text.ParseException;
import java.util.Calendar;
import java.util.List;

/* loaded from: classes5.dex */
public class StepTabFragment extends BaseLazyLoadFragment<FragmentSteptabBinding> {
    private int mMaxStepNum;
    private List<RunningHisListBean> mMonthRunningHisListBean;
    private NestedScrollView mNestedScrollView;
    private List<RunningHisListBean> mRunningHisListBean;
    GradualBarChart mStepDayChart;
    GradualBarChart mStepMonthChart;
    GradualBarChart mStepWeekChart;
    private String mTitles;
    private String mToDay;
    private List<RunningHisListBean> mWeekRunningHisListBean;
    private int monthData;
    private Integer position;
    private int mDayXLabelCount = 5;
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
    private float yMaximum = 300.0f;
    private float yMinimum = 0.0f;
    private String mThatVeryDay = "";

    @Override // com.yucheng.smarthealthpro.framework.BaseFragment
    protected void initData(Context mContext) {
    }

    @Override // com.gyf.immersionbar.components.ImmersionOwner
    public void initImmersionBar() {
    }

    public static StepTabFragment newInstance(String mTitles, int position, NestedScrollView mNestedScrollView, int monthData, List<RunningHisListBean> mRunningHisListBean, List<RunningHisListBean> mWeekSumUpRunningHisListBean, List<RunningHisListBean> mMonthSumUpRunningHisListBean, int mMaxStepNum, String mThatVeryDay) {
        StepTabFragment stepTabFragment = new StepTabFragment();
        stepTabFragment.mTitles = mTitles;
        stepTabFragment.position = Integer.valueOf(position);
        stepTabFragment.monthData = monthData;
        stepTabFragment.mNestedScrollView = mNestedScrollView;
        stepTabFragment.mRunningHisListBean = mRunningHisListBean;
        stepTabFragment.mWeekRunningHisListBean = mWeekSumUpRunningHisListBean;
        stepTabFragment.mMonthRunningHisListBean = mMonthSumUpRunningHisListBean;
        stepTabFragment.mMaxStepNum = mMaxStepNum + 500;
        stepTabFragment.mThatVeryDay = mThatVeryDay;
        return stepTabFragment;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.yucheng.smarthealthpro.framework.BaseFragment
    protected void initView(View view) throws ParseException {
        this.mStepDayChart = ((FragmentSteptabBinding) getMViewBind()).barChartDay;
        this.mStepWeekChart = ((FragmentSteptabBinding) getMViewBind()).barChartWeek;
        this.mStepMonthChart = ((FragmentSteptabBinding) getMViewBind()).barChartMonth;
        Calendar calendar = Calendar.getInstance();
        this.mToDay = calendar.get(1) + "-" + (calendar.get(2) + 1) + "-" + calendar.get(5);
    }

    @Override // com.yucheng.smarthealthpro.base.BaseLazyLoadFragment
    protected void lazyLoadData() {
        if (this.mTitles.equals(getString(R.string.date_month_unit))) {
            this.mStepDayChart.setVisibility(8);
            this.mStepWeekChart.setVisibility(8);
            this.mStepMonthChart.setVisibility(0);
            StepBarChartUtils.initBarChart(this.mStepMonthChart, this.context, this.mMonthRunningHisListBean, this.mToDay, this.mNestedScrollView, this.yMaximum, this.yMinimum, this.xMonthMaximum, this.mMonthXLabelCount, StepBarChartUtils.FORMATTER.MONTH, "");
            return;
        }
        if (this.mTitles.equals(getString(R.string.date_week_unit))) {
            this.mStepDayChart.setVisibility(8);
            this.mStepWeekChart.setVisibility(0);
            this.mStepMonthChart.setVisibility(8);
            StepBarChartUtils.initBarChart(this.mStepWeekChart, this.context, this.mWeekRunningHisListBean, this.mToDay, this.mNestedScrollView, this.yMaximum, this.yMinimum, this.xWeekMaximum, this.mWeekXLabelCount, StepBarChartUtils.FORMATTER.WEEK, "");
            return;
        }
        this.mStepDayChart.setVisibility(0);
        this.mStepWeekChart.setVisibility(8);
        this.mStepMonthChart.setVisibility(8);
        StepBarChartUtils.initBarChart(this.mStepDayChart, this.context, this.mRunningHisListBean, this.mToDay, this.mNestedScrollView, this.mMaxStepNum, this.yMinimum, this.xDayMaximum, this.mDayXLabelCount, StepBarChartUtils.FORMATTER.DAY, "");
    }
}
