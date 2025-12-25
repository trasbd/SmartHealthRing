package com.yucheng.smarthealthpro.home.activity.pressure.fragment;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import androidx.core.internal.view.SupportMenu;
import androidx.core.widget.NestedScrollView;
import com.alibaba.fastjson2.internal.asm.Opcodes;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseLazyLoadFragment;
import com.yucheng.smarthealthpro.customchart.MyTempDayCustomXAxisValueFormatter;
import com.yucheng.smarthealthpro.customchart.charts.BarChart;
import com.yucheng.smarthealthpro.customchart.charts.LineChart;
import com.yucheng.smarthealthpro.customchart.components.XAxis;
import com.yucheng.smarthealthpro.customchart.components.YAxis;
import com.yucheng.smarthealthpro.customchart.data.BarData;
import com.yucheng.smarthealthpro.customchart.data.BarDataSet;
import com.yucheng.smarthealthpro.customchart.data.BarEntry;
import com.yucheng.smarthealthpro.customchart.data.Entry;
import com.yucheng.smarthealthpro.customchart.highlight.Highlight;
import com.yucheng.smarthealthpro.customchart.listener.OnChartValueSelectedListener;
import com.yucheng.smarthealthpro.customchart.renderer.MyXAxisRenderer;
import com.yucheng.smarthealthpro.customchart.temperature.TempLineChartUtils;
import com.yucheng.smarthealthpro.databinding.FragmentPressureTabBinding;
import com.yucheng.smarthealthpro.home.activity.temperature.bean.TemperatureHisListBean;
import com.yucheng.smarthealthpro.utils.TransUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/* loaded from: classes5.dex */
public class PressureTabFragment extends BaseLazyLoadFragment<FragmentPressureTabBinding> {
    LinearLayout llTime;
    private List<TemperatureHisListBean> mDayPressureHisListBean;
    private float mMaxStepNum;
    private List<TemperatureHisListBean> mMonthSumUpPressureHisListBean;
    private NestedScrollView mNestedScrollView;
    BarChart mTempDayChart;
    LineChart mTempMonthChart;
    LineChart mTempWeekChart;
    private String mTitles;
    private String mToDay;
    private List<TemperatureHisListBean> mWeekSumUpPressureHisListBean;
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
    private float yMaximum = TransUtils.HRV_VISIBLE_MAX;
    private float yMinimum = 0.0f;
    private int minYNum = 0;

    @Override // com.yucheng.smarthealthpro.framework.BaseFragment
    protected void initData(Context mContext) {
    }

    @Override // com.gyf.immersionbar.components.ImmersionOwner
    public void initImmersionBar() {
    }

    public static PressureTabFragment newInstance(String mTitles, int position, NestedScrollView mNestedScrollView, List<TemperatureHisListBean> mDayHeartRateHisListBean, List<TemperatureHisListBean> mWeekSumUpHeartRateHisListBean, List<TemperatureHisListBean> mMonthSumUpHeartRateHisListBean, float mMaxStepNum) {
        PressureTabFragment pressureTabFragment = new PressureTabFragment();
        pressureTabFragment.mTitles = mTitles;
        pressureTabFragment.position = Integer.valueOf(position);
        pressureTabFragment.mNestedScrollView = mNestedScrollView;
        pressureTabFragment.mDayPressureHisListBean = mDayHeartRateHisListBean;
        pressureTabFragment.mWeekSumUpPressureHisListBean = mWeekSumUpHeartRateHisListBean;
        pressureTabFragment.mMonthSumUpPressureHisListBean = mMonthSumUpHeartRateHisListBean;
        pressureTabFragment.mMaxStepNum = mMaxStepNum;
        return pressureTabFragment;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.yucheng.smarthealthpro.framework.BaseFragment
    protected void initView(View view) {
        this.mTempDayChart = ((FragmentPressureTabBinding) getMViewBind()).barChartDay;
        this.mTempWeekChart = ((FragmentPressureTabBinding) getMViewBind()).lineChartWeek;
        this.mTempMonthChart = ((FragmentPressureTabBinding) getMViewBind()).lineChartMonth;
        this.llTime = ((FragmentPressureTabBinding) getMViewBind()).llTime;
        Calendar calendar = Calendar.getInstance();
        this.mToDay = calendar.get(1) + "-" + (calendar.get(2) + 1) + "-" + calendar.get(5);
    }

    @Override // com.yucheng.smarthealthpro.base.BaseLazyLoadFragment
    protected void lazyLoadData() throws NumberFormatException {
        this.llTime.setVisibility(8);
        if (getString(R.string.date_month_unit).equals(this.mTitles)) {
            this.mTempDayChart.setVisibility(8);
            this.mTempWeekChart.setVisibility(8);
            this.mTempMonthChart.setVisibility(0);
            TempLineChartUtils.initLineChart(this.mTempMonthChart, this.context, this.mMonthSumUpPressureHisListBean, this.mToDay, this.mNestedScrollView, TempLineChartUtils.FUNCTION.PRESSURE, this.yMaximum, this.yMinimum, this.xMonthMaximum, this.mMonthXLabelCount, this.mMonthYLabelCount, TempLineChartUtils.FORMATTER.MONTH);
            return;
        }
        if (getString(R.string.date_week_unit).equals(this.mTitles)) {
            this.mTempDayChart.setVisibility(8);
            this.mTempWeekChart.setVisibility(0);
            this.mTempMonthChart.setVisibility(8);
            TempLineChartUtils.initLineChart(this.mTempWeekChart, this.context, this.mWeekSumUpPressureHisListBean, this.mToDay, this.mNestedScrollView, TempLineChartUtils.FUNCTION.PRESSURE, this.yMaximum, this.yMinimum, this.xWeekMaximum, this.mWeekXLabelCount, this.mWeekYLabelCount, TempLineChartUtils.FORMATTER.WEEK);
            return;
        }
        this.llTime.setVisibility(0);
        this.mTempDayChart.setVisibility(0);
        this.mTempWeekChart.setVisibility(8);
        this.mTempMonthChart.setVisibility(8);
        TempLineChartUtils.initBarChart(this.mTempDayChart, this.mDayPressureHisListBean, true);
    }

    private void initBarChart() {
        BarChart barChart = this.mTempDayChart;
        barChart.getDescription().setEnabled(false);
        barChart.setPinchZoom(false);
        barChart.setDrawGridBackground(false);
        barChart.setExtraBottomOffset(15.0f);
        barChart.getLegend().setEnabled(false);
        XAxis xAxis = barChart.getXAxis();
        xAxis.setGranularity(1.0f);
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setCenterAxisLabels(true);
        YAxis axisLeft = barChart.getAxisLeft();
        axisLeft.setDrawGridLines(false);
        axisLeft.setSpaceTop(35.0f);
        axisLeft.setAxisMinimum(0.0f);
        barChart.getAxisRight().setEnabled(false);
        barChart.getAxisLeft().setEnabled(false);
        barChart.getXAxis().setAxisLineColor(0);
        setData();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void setData() {
        boolean z;
        BarChart barChart;
        BarEntry barEntry;
        BarChart barChart2 = this.mTempDayChart;
        barChart2.getXAxis().setValueFormatter(new MyTempDayCustomXAxisValueFormatter(true));
        barChart2.getXAxis().setLabelCount(24, true);
        barChart2.setXAxisRenderer(new MyXAxisRenderer(barChart2.getViewPortHandler(), barChart2.getXAxis(), barChart2.getTransformer(YAxis.AxisDependency.LEFT)));
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        float f2 = 100 * 100.0f;
        BarData barData = new BarData();
        int i2 = 0;
        for (int i3 = 24; i2 < i3; i3 = 24) {
            arrayList = new ArrayList();
            if (i2 > 18) {
                barEntry = new BarEntry(i2, 0.0f);
            } else {
                barEntry = new BarEntry(i2, (float) (f2 * Math.random()));
            }
            arrayList.add(barEntry);
            float f3 = i2;
            int i4 = i2;
            double d2 = f2;
            BarChart barChart3 = barChart2;
            arrayList2.add(new BarEntry(f3, (float) (Math.random() * d2)));
            float f4 = f2;
            BarData barData2 = barData;
            arrayList3.add(new BarEntry(f3, (float) (Math.random() * d2)));
            arrayList4.add(new BarEntry(f3, (float) (Math.random() * d2)));
            BarDataSet barDataSet = new BarDataSet(arrayList, "");
            Log.d("ltf", "barEntry.getY()=" + barEntry.getY());
            if (barEntry.getY() < 3333.0f) {
                barDataSet.setColor(SupportMenu.CATEGORY_MASK);
            } else if (barEntry.getY() < 6666.0f) {
                barDataSet.setColor(-16776961);
            } else {
                barDataSet.setColor(-16711936);
            }
            barData2.addDataSet(barDataSet);
            i2 = i4 + 1;
            f2 = f4;
            barData = barData2;
            barChart2 = barChart3;
        }
        BarChart barChart4 = barChart2;
        BarData barData3 = barData;
        if (barChart4.getData() != null && ((BarData) barChart4.getData()).getDataSetCount() > 0) {
            BarDataSet barDataSet2 = (BarDataSet) ((BarData) barChart4.getData()).getDataSetByIndex(0);
            BarDataSet barDataSet3 = (BarDataSet) ((BarData) barChart4.getData()).getDataSetByIndex(1);
            BarDataSet barDataSet4 = (BarDataSet) ((BarData) barChart4.getData()).getDataSetByIndex(2);
            BarDataSet barDataSet5 = (BarDataSet) ((BarData) barChart4.getData()).getDataSetByIndex(3);
            barDataSet2.setEntries(arrayList);
            barDataSet3.setEntries(arrayList2);
            barDataSet4.setEntries(arrayList3);
            barDataSet5.setEntries(arrayList4);
            ((BarData) barChart4.getData()).notifyDataChanged();
            barChart4.notifyDataSetChanged();
            barChart = barChart4;
            z = false;
        } else {
            new BarDataSet(arrayList, "").setColor(Color.rgb(0, Opcodes.IFEQ, 255));
            new BarDataSet(arrayList2, "").setColor(Color.rgb(255, Opcodes.IFEQ, 102));
            new BarDataSet(arrayList3, "").setColor(Color.rgb(51, Opcodes.IFEQ, Opcodes.IFEQ));
            z = false;
            new BarDataSet(arrayList4, "").setColor(Color.rgb(255, 102, 0));
            barChart = barChart4;
            barChart.setData(barData3);
        }
        barChart.setDrawBarShadow(true);
        barChart.getBarData().setBarWidth(0.85f);
        barChart.getBarData().setValueTextSize(0.0f);
        barChart.getXAxis().setEnabled(z);
        barChart.invalidate();
        barChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() { // from class: com.yucheng.smarthealthpro.home.activity.pressure.fragment.PressureTabFragment.1
            @Override // com.yucheng.smarthealthpro.customchart.listener.OnChartValueSelectedListener
            public void onValueSelected(Entry e2, Highlight h2) {
            }

            @Override // com.yucheng.smarthealthpro.customchart.listener.OnChartValueSelectedListener
            public void onNothingSelected() {
                Log.i("Activity", "Nothing selected.");
            }
        });
    }
}
