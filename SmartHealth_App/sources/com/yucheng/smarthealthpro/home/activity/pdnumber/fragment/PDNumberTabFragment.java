package com.yucheng.smarthealthpro.home.activity.pdnumber.fragment;

import android.content.Context;
import android.view.View;
import androidx.core.widget.NestedScrollView;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseLazyLoadFragment;
import com.yucheng.smarthealthpro.customchart.MyBarChartUtils;
import com.yucheng.smarthealthpro.customchart.charts.GradualBarChart;
import com.yucheng.smarthealthpro.databinding.FragmentPdnumberBinding;
import com.yucheng.smarthealthpro.home.bean.MyMonBean;
import java.text.ParseException;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class PDNumberTabFragment extends BaseLazyLoadFragment<FragmentPdnumberBinding> {
    private List<MyMonBean.Data.Values> dayDatas;
    private MyBarChartUtils.FORMATTER formatter;
    private List<MyMonBean.Data.Values> halfYearDatas;
    GradualBarChart mDayChart;
    GradualBarChart mHalfYearChart;
    GradualBarChart mMonthChart;
    private NestedScrollView mNestedScrollView;
    private String mTitles;
    GradualBarChart mWeekChart;
    GradualBarChart mYearChart;
    private List<MyMonBean.Data.Values> monthDatas;
    private Integer position;
    private String unit;
    private List<MyMonBean.Data.Values> weekDatas;
    private float yMaximum = 0.0f;
    private float yMinimum = 25.0f;
    private List<MyMonBean.Data.Values> yearDatas;

    @Override // com.yucheng.smarthealthpro.framework.BaseFragment
    protected void initData(Context mContext) {
    }

    @Override // com.gyf.immersionbar.components.ImmersionOwner
    public void initImmersionBar() {
    }

    public static PDNumberTabFragment newInstance(String mTitles, int position, NestedScrollView mNestedScrollView, List<MyMonBean.Data.Values> dayDatas, List<MyMonBean.Data.Values> weekDatas, List<MyMonBean.Data.Values> monthDatas, List<MyMonBean.Data.Values> halfYearDatas, List<MyMonBean.Data.Values> yearDatas, String unit) {
        PDNumberTabFragment pDNumberTabFragment = new PDNumberTabFragment();
        pDNumberTabFragment.mNestedScrollView = mNestedScrollView;
        pDNumberTabFragment.mTitles = mTitles;
        pDNumberTabFragment.position = Integer.valueOf(position);
        pDNumberTabFragment.dayDatas = dayDatas;
        pDNumberTabFragment.weekDatas = weekDatas;
        pDNumberTabFragment.monthDatas = monthDatas;
        pDNumberTabFragment.halfYearDatas = halfYearDatas;
        pDNumberTabFragment.yearDatas = yearDatas;
        pDNumberTabFragment.unit = unit;
        return pDNumberTabFragment;
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbFragment, com.yucheng.smarthealthpro.framework.BaseFragment
    protected int initLayout() {
        return R.layout.fragment_pdnumber;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.yucheng.smarthealthpro.framework.BaseFragment
    protected void initView(View view) throws ParseException {
        this.mDayChart = ((FragmentPdnumberBinding) getMViewBind()).barChartDay;
        this.mWeekChart = ((FragmentPdnumberBinding) getMViewBind()).barChartWeek;
        this.mMonthChart = ((FragmentPdnumberBinding) getMViewBind()).barChartMonth;
        this.mHalfYearChart = ((FragmentPdnumberBinding) getMViewBind()).barChartHalfyear;
        this.mYearChart = ((FragmentPdnumberBinding) getMViewBind()).barChartYear;
    }

    @Override // com.yucheng.smarthealthpro.base.BaseLazyLoadFragment
    protected void lazyLoadData() throws NumberFormatException {
        List<MyMonBean.Data.Values> list;
        GradualBarChart gradualBarChart;
        if (this.mTitles.equals(getString(R.string.pd_month_text))) {
            this.mDayChart.setVisibility(8);
            this.mWeekChart.setVisibility(8);
            this.mMonthChart.setVisibility(0);
            this.mHalfYearChart.setVisibility(8);
            this.mYearChart.setVisibility(8);
            list = this.monthDatas;
            gradualBarChart = this.mMonthChart;
            this.formatter = MyBarChartUtils.FORMATTER.MONTH;
        } else if (this.mTitles.equals(getString(R.string.pd_week_text))) {
            this.mDayChart.setVisibility(8);
            this.mWeekChart.setVisibility(0);
            this.mMonthChart.setVisibility(8);
            this.mHalfYearChart.setVisibility(8);
            this.mYearChart.setVisibility(8);
            list = this.weekDatas;
            gradualBarChart = this.mWeekChart;
            this.formatter = MyBarChartUtils.FORMATTER.WEEK;
        } else if (this.mTitles.equals(getString(R.string.pd_halfyear_text))) {
            this.mDayChart.setVisibility(8);
            this.mWeekChart.setVisibility(8);
            this.mMonthChart.setVisibility(8);
            this.mHalfYearChart.setVisibility(0);
            this.mYearChart.setVisibility(8);
            list = this.halfYearDatas;
            gradualBarChart = this.mHalfYearChart;
            this.formatter = MyBarChartUtils.FORMATTER.HALFYEAR;
        } else if (this.mTitles.equals(getString(R.string.pd_year_text))) {
            this.mDayChart.setVisibility(8);
            this.mWeekChart.setVisibility(8);
            this.mMonthChart.setVisibility(8);
            this.mHalfYearChart.setVisibility(8);
            this.mYearChart.setVisibility(0);
            list = this.yearDatas;
            gradualBarChart = this.mYearChart;
            this.formatter = MyBarChartUtils.FORMATTER.YEAR;
        } else {
            this.mDayChart.setVisibility(0);
            this.mWeekChart.setVisibility(8);
            this.mMonthChart.setVisibility(8);
            this.mHalfYearChart.setVisibility(8);
            this.mYearChart.setVisibility(8);
            list = this.dayDatas;
            gradualBarChart = this.mDayChart;
            this.formatter = MyBarChartUtils.FORMATTER.DAY;
        }
        List<MyMonBean.Data.Values> list2 = list;
        GradualBarChart gradualBarChart2 = gradualBarChart;
        getMaxMinValue(list2);
        MyBarChartUtils.initBarChart(gradualBarChart2, this.context, list2, this.mNestedScrollView, this.yMaximum, this.yMinimum, this.formatter, this.unit);
    }

    private void getMaxMinValue(List<MyMonBean.Data.Values> dataLists) throws NumberFormatException {
        float f2;
        float f3 = 25.0f;
        if (dataLists != null && dataLists.size() > 0) {
            Iterator<MyMonBean.Data.Values> it2 = dataLists.iterator();
            float f4 = 0.0f;
            while (it2.hasNext()) {
                try {
                    f2 = Float.parseFloat(it2.next().displayvalue);
                } catch (Exception e2) {
                    e2.printStackTrace();
                    f2 = 0.0f;
                }
                if (f2 > f4) {
                    f4 = f2;
                }
                if (f2 < f3) {
                    f3 = f2;
                }
            }
            this.yMaximum = (int) (f4 * 1.2d);
            this.yMinimum = (int) (f3 * 0.8d);
            return;
        }
        this.yMaximum = 25.0f;
        this.yMinimum = 0.0f;
    }
}
