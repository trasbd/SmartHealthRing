package com.yucheng.smarthealthpro.home.activity.bloodpressure.fragment;

import android.content.Context;
import android.view.View;
import androidx.core.widget.NestedScrollView;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseLazyLoadFragment;
import com.yucheng.smarthealthpro.customchart.charts.CandleStickChart;
import com.yucheng.smarthealthpro.customchart.klinechart.BpCandleStickChartUtils;
import com.yucheng.smarthealthpro.databinding.FragmentBptabBinding;
import com.yucheng.smarthealthpro.home.activity.bloodpressure.bean.BloodPressureHisListBean;
import java.util.Calendar;
import java.util.List;

/* loaded from: classes5.dex */
public class BpTabFragment extends BaseLazyLoadFragment<FragmentBptabBinding> {
    CandleStickChart mBpDayChart;
    CandleStickChart mBpMonthChart;
    CandleStickChart mBpWeekChart;
    private List<BloodPressureHisListBean> mDayBloodPressureHisListBean;
    private NestedScrollView mNestedScrollView;
    private String mTitles;
    private String mToDay;
    private List<BloodPressureHisListBean> mWeekBloodPressureHisListBean;
    private List<BloodPressureHisListBean> mWeekSumUpBloodPressureHisListBean;
    private Integer position;
    private int mDayXLabelCount = 5;
    private int mDayYLabelCount = 5;
    private int mWeekXLabelCount = 6;
    private int mWeekYLabelCount = 5;
    private int mMonthXLabelCount = 2;
    private int mMonthYLabelCount = 5;
    private float xDayMaximum = 288.0f;
    private float xDayMinimum = 0.0f;
    private float xWeekMaximum = 7.0f;
    private float xWeekMinimum = 0.0f;
    private float xMonthMaximum = 30.0f;
    private float xMonthMinimum = 0.0f;
    private float yMaximum = 150.0f;
    private float yMinimum = 0.0f;

    @Override // com.yucheng.smarthealthpro.framework.BaseFragment
    protected void initData(Context mContext) {
    }

    @Override // com.gyf.immersionbar.components.ImmersionOwner
    public void initImmersionBar() {
    }

    public static BpTabFragment newInstance(String mTitles, int position, NestedScrollView mNestedScrollView, List<BloodPressureHisListBean> mDayBloodPressureHisListBean, List<BloodPressureHisListBean> mWeekBloodPressureHisListBean, List<BloodPressureHisListBean> mWeekSumUpBloodPressureHisListBean) {
        BpTabFragment bpTabFragment = new BpTabFragment();
        bpTabFragment.mTitles = mTitles;
        bpTabFragment.position = Integer.valueOf(position);
        bpTabFragment.mNestedScrollView = mNestedScrollView;
        bpTabFragment.mDayBloodPressureHisListBean = mDayBloodPressureHisListBean;
        bpTabFragment.mWeekBloodPressureHisListBean = mWeekBloodPressureHisListBean;
        bpTabFragment.mWeekSumUpBloodPressureHisListBean = mWeekSumUpBloodPressureHisListBean;
        return bpTabFragment;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.yucheng.smarthealthpro.framework.BaseFragment
    protected void initView(View view) {
        this.mBpDayChart = ((FragmentBptabBinding) getMViewBind()).candleStickChartDay;
        this.mBpWeekChart = ((FragmentBptabBinding) getMViewBind()).candleStickChartWeek;
        this.mBpMonthChart = ((FragmentBptabBinding) getMViewBind()).candleStickChartMonth;
        Calendar calendar = Calendar.getInstance();
        this.mToDay = calendar.get(1) + "-" + (calendar.get(2) + 1) + "-" + calendar.get(5);
    }

    @Override // com.yucheng.smarthealthpro.base.BaseLazyLoadFragment
    protected void lazyLoadData() {
        if (this.mTitles.equals(getString(R.string.date_month_unit))) {
            this.mBpDayChart.setVisibility(8);
            this.mBpWeekChart.setVisibility(8);
            this.mBpMonthChart.setVisibility(0);
            BpCandleStickChartUtils.initCandleStickChart(this.mBpMonthChart, this.context, this.mWeekSumUpBloodPressureHisListBean, this.mToDay, this.mNestedScrollView, this.yMaximum, this.yMinimum, this.xMonthMaximum, this.xMonthMinimum, this.mMonthXLabelCount, this.mMonthYLabelCount, BpCandleStickChartUtils.FORMATTER.MONTH);
            return;
        }
        if (this.mTitles.equals(getString(R.string.date_week_unit))) {
            this.mBpDayChart.setVisibility(8);
            this.mBpWeekChart.setVisibility(0);
            this.mBpMonthChart.setVisibility(8);
            BpCandleStickChartUtils.initCandleStickChart(this.mBpWeekChart, this.context, this.mWeekBloodPressureHisListBean, this.mToDay, this.mNestedScrollView, this.yMaximum, this.yMinimum, this.xWeekMaximum, this.xWeekMinimum, this.mWeekXLabelCount, this.mWeekYLabelCount, BpCandleStickChartUtils.FORMATTER.WEEK);
            return;
        }
        this.mBpDayChart.setVisibility(0);
        this.mBpWeekChart.setVisibility(8);
        this.mBpMonthChart.setVisibility(8);
        BpCandleStickChartUtils.initCandleStickChart(this.mBpDayChart, this.context, this.mDayBloodPressureHisListBean, this.mToDay, this.mNestedScrollView, this.yMaximum, this.yMinimum, this.xDayMaximum, this.xDayMinimum, this.mDayXLabelCount, this.mDayYLabelCount, BpCandleStickChartUtils.FORMATTER.DAY);
    }
}
