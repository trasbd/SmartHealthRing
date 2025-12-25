package com.yucheng.smarthealthpro.home.activity.phy.fragment;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import androidx.core.widget.NestedScrollView;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseLazyLoadFragment;
import com.yucheng.smarthealthpro.customchart.charts.BarChart;
import com.yucheng.smarthealthpro.customchart.charts.LineChart;
import com.yucheng.smarthealthpro.customchart.phy.PhyBarChartUtils;
import com.yucheng.smarthealthpro.databinding.FragmentPhyTabBinding;
import com.yucheng.smarthealthpro.home.activity.phy.bean.PhyHisListBean;
import java.util.Calendar;
import java.util.List;

/* loaded from: classes5.dex */
public class PhyTabFragment extends BaseLazyLoadFragment<FragmentPhyTabBinding> {
    LineChart mDayChart;
    private List<PhyHisListBean> mDayDataBeans;
    BarChart mMonthChart;
    private List<PhyHisListBean> mMonthChartHisListBean;
    private NestedScrollView mNestedScrollView;
    private String mTitles;
    private String mToDay;
    BarChart mWeekChart;
    private List<PhyHisListBean> mWeekChartHisListBean;
    private int mDayXLabelCount = 2;
    private float xDayMaximum = 48.0f;
    private float yMaximum = 140.0f;
    private float yMinimum = 0.0f;

    @Override // com.yucheng.smarthealthpro.framework.BaseFragment
    protected void initData(Context mContext) {
    }

    @Override // com.gyf.immersionbar.components.ImmersionOwner
    public void initImmersionBar() {
    }

    public static PhyTabFragment newInstance(String mTitles, int position, NestedScrollView mNestedScrollView, int monthData, List<PhyHisListBean> mDayDataBeans, List<PhyHisListBean> mWeekSleepChartHisListBean, List<PhyHisListBean> mMonthSleepChartHisListBean) {
        PhyTabFragment phyTabFragment = new PhyTabFragment();
        phyTabFragment.mTitles = mTitles;
        phyTabFragment.mNestedScrollView = mNestedScrollView;
        phyTabFragment.mDayDataBeans = mDayDataBeans;
        phyTabFragment.mWeekChartHisListBean = mWeekSleepChartHisListBean;
        phyTabFragment.mMonthChartHisListBean = mMonthSleepChartHisListBean;
        return phyTabFragment;
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbFragment, com.yucheng.smarthealthpro.framework.BaseFragment
    protected int initLayout() {
        return R.layout.fragment_phy_tab;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.yucheng.smarthealthpro.framework.BaseFragment
    protected void initView(View view) {
        this.mDayChart = ((FragmentPhyTabBinding) getMViewBind()).lineChartDay;
        this.mWeekChart = ((FragmentPhyTabBinding) getMViewBind()).barChartWeek;
        this.mMonthChart = ((FragmentPhyTabBinding) getMViewBind()).barChartMonth;
        Calendar calendar = Calendar.getInstance();
        this.mToDay = calendar.get(1) + "-" + (calendar.get(2) + 1) + "-" + calendar.get(5);
    }

    @Override // com.yucheng.smarthealthpro.base.BaseLazyLoadFragment
    protected void lazyLoadData() throws Resources.NotFoundException {
        if (this.mTitles.equals(getString(R.string.date_month_unit))) {
            this.mDayChart.setVisibility(8);
            this.mWeekChart.setVisibility(8);
            this.mMonthChart.setVisibility(0);
            PhyBarChartUtils.initChart(this.context, this.mMonthChart, this.mMonthChartHisListBean, this.mToDay, PhyBarChartUtils.FORMATTER.MONTH);
            return;
        }
        if (this.mTitles.equals(getString(R.string.date_week_unit))) {
            this.mDayChart.setVisibility(8);
            this.mWeekChart.setVisibility(0);
            this.mMonthChart.setVisibility(8);
            PhyBarChartUtils.initChart(this.context, this.mWeekChart, this.mWeekChartHisListBean, this.mToDay, PhyBarChartUtils.FORMATTER.WEEK);
            return;
        }
        this.mDayChart.setVisibility(0);
        this.mWeekChart.setVisibility(8);
        this.mMonthChart.setVisibility(8);
        PhyBarChartUtils.initPhyChart(this.mDayChart, this.context, this.mDayDataBeans, this.mNestedScrollView, this.yMaximum, this.yMinimum, this.xDayMaximum, this.mDayXLabelCount, PhyBarChartUtils.FORMATTER.DAY);
    }
}
