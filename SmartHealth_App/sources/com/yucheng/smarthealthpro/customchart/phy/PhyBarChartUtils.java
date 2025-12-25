package com.yucheng.smarthealthpro.customchart.phy;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.DashPathEffect;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.widget.NestedScrollView;
import com.google.gson.Gson;
import com.google.maps.android.BuildConfig;
import com.orhanobut.logger.Logger;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.customchart.MyMonthCustomXAxisValueFormatter;
import com.yucheng.smarthealthpro.customchart.MyWeekCustomXAxisValueFormatter;
import com.yucheng.smarthealthpro.customchart.charts.BarChart;
import com.yucheng.smarthealthpro.customchart.charts.LineChart;
import com.yucheng.smarthealthpro.customchart.components.Legend;
import com.yucheng.smarthealthpro.customchart.components.XAxis;
import com.yucheng.smarthealthpro.customchart.components.YAxis;
import com.yucheng.smarthealthpro.customchart.data.BarData;
import com.yucheng.smarthealthpro.customchart.data.BarDataSet;
import com.yucheng.smarthealthpro.customchart.data.BarEntry;
import com.yucheng.smarthealthpro.customchart.data.Entry;
import com.yucheng.smarthealthpro.customchart.data.LineData;
import com.yucheng.smarthealthpro.customchart.data.LineDataSet;
import com.yucheng.smarthealthpro.customchart.formatter.IFillFormatter;
import com.yucheng.smarthealthpro.customchart.formatter.IValueFormatter;
import com.yucheng.smarthealthpro.customchart.highlight.Highlight;
import com.yucheng.smarthealthpro.customchart.interfaces.dataprovider.LineDataProvider;
import com.yucheng.smarthealthpro.customchart.interfaces.datasets.ILineDataSet;
import com.yucheng.smarthealthpro.customchart.listener.OnChartValueSelectedListener;
import com.yucheng.smarthealthpro.customchart.renderer.MyXAxisRenderer;
import com.yucheng.smarthealthpro.customchart.sleep.MySleepCustomXAxisValueFormatter;
import com.yucheng.smarthealthpro.customchart.utils.ColorTemplate;
import com.yucheng.smarthealthpro.customchart.utils.ViewPortHandler;
import com.yucheng.smarthealthpro.home.activity.phy.bean.PhyHisListBean;
import com.yucheng.smarthealthpro.utils.TimeStampUtils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class PhyBarChartUtils {
    public static final int[] PHYSIOTHERAPY_COLORS = {ColorTemplate.rgb("#7D9FFB"), ColorTemplate.rgb("#219DFC"), ColorTemplate.rgb("#00E5FF"), ColorTemplate.rgb("#004CFF")};
    private static PhyBarChartUtils mSleepBarChartUtils;
    private static XAxis xDayAxis;
    private static YAxis yDayAxis;

    public enum FORMATTER {
        DAY,
        WEEK,
        MONTH
    }

    private PhyBarChartUtils() {
    }

    public static synchronized PhyBarChartUtils getInstance() {
        if (mSleepBarChartUtils == null) {
            mSleepBarChartUtils = new PhyBarChartUtils();
        }
        return mSleepBarChartUtils;
    }

    public static void initPhyChart(LineChart mPhyDayChart, Context context, List<PhyHisListBean> mSleepDataBeans, final NestedScrollView mNestedScrollView, float yMaximum, float yMinimum, float xMaximum, int mXLabelCount, FORMATTER formatter) throws Resources.NotFoundException {
        mPhyDayChart.setBackgroundColor(-1);
        mPhyDayChart.getDescription().setEnabled(false);
        mPhyDayChart.setTouchEnabled(true);
        mPhyDayChart.setDrawGridBackground(false);
        mPhyDayChart.setDrawBorders(false);
        mPhyDayChart.getAxisLeft().setDrawGridLines(false);
        mPhyDayChart.getAxisRight().setDrawGridLines(false);
        mPhyDayChart.getXAxis().setDrawGridLines(false);
        mPhyDayChart.setDragEnabled(true);
        mPhyDayChart.setScaleEnabled(false);
        mPhyDayChart.setPinchZoom(false);
        mPhyDayChart.setOnTouchListener(new View.OnTouchListener() { // from class: com.yucheng.smarthealthpro.customchart.phy.PhyBarChartUtils.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == 1) {
                    mNestedScrollView.requestDisallowInterceptTouchEvent(false);
                } else {
                    mNestedScrollView.requestDisallowInterceptTouchEvent(true);
                }
                return false;
            }
        });
        XAxis xAxis = mPhyDayChart.getXAxis();
        xDayAxis = xAxis;
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xDayAxis.setDrawAxisLine(true);
        xDayAxis.setLabelCount(mXLabelCount, true);
        xDayAxis.setValueFormatter(new MySleepCustomXAxisValueFormatter(true));
        xDayAxis.setAvoidFirstLastClipping(true);
        mPhyDayChart.setXAxisRenderer(new MyXAxisRenderer(mPhyDayChart.getViewPortHandler(), mPhyDayChart.getXAxis(), mPhyDayChart.getTransformer(YAxis.AxisDependency.LEFT)));
        yDayAxis = mPhyDayChart.getAxisLeft();
        mPhyDayChart.getAxisRight().setEnabled(false);
        mPhyDayChart.getAxisLeft().setEnabled(false);
        yDayAxis.setDrawAxisLine(true);
        yDayAxis.setDrawLabels(true);
        yDayAxis.setAxisMaximum(20.0f);
        yDayAxis.setAxisMinimum(0.0f);
        setData(context, mPhyDayChart, mSleepDataBeans, xDayAxis);
        mPhyDayChart.getLegend().setForm(Legend.LegendForm.NONE);
    }

    private static void setData(Context context, LineChart mChart, List<PhyHisListBean> mPhyList, XAxis xAxis) throws Resources.NotFoundException {
        ArrayList arrayList = new ArrayList();
        if (mPhyList == null) {
            return;
        }
        int i2 = 0;
        if (mPhyList.size() == 0) {
            while (i2 < 1440) {
                arrayList.add(new Entry(i2, 0.0f));
                i2++;
            }
            xAxis.setAxisMaximum(1440.0f);
            xAxis.setAxisMinimum(0.0f);
            xAxis.setSleep(0.0f);
            xAxis.setUp(1440.0f);
        } else {
            int secondsForDay = TimeStampUtils.getSecondsForDay(mPhyList.get(0).getDateTime()) / 60;
            xAxis.setAxisMaximum(1440.0f);
            xAxis.setAxisMinimum(0.0f);
            for (int i3 = 0; i3 < 1440; i3++) {
                arrayList.add(new Entry(i3, 0.0f));
            }
            while (i2 < mPhyList.size()) {
                PhyHisListBean phyHisListBean = mPhyList.get(i2);
                int secondsForDay2 = TimeStampUtils.getSecondsForDay(phyHisListBean.getDateTime()) / 60;
                int level4Duration = (((int) (((phyHisListBean.getLevel4Duration() + phyHisListBean.getLevel3Duration()) + phyHisListBean.getLevel2Duration()) + phyHisListBean.getLevel1Duration())) / 60) + secondsForDay2;
                if (level4Duration < secondsForDay2) {
                    level4Duration += 1440;
                }
                while (secondsForDay2 < level4Duration) {
                    int i4 = secondsForDay2 - secondsForDay;
                    if (i4 < arrayList.size()) {
                        arrayList.remove(i4);
                        if (phyHisListBean.getLevel1Count() > 0) {
                            arrayList.add(i4, new Entry(i4, 5.0f));
                        } else if (phyHisListBean.getLevel2Count() > 0) {
                            arrayList.add(i4, new Entry(i4, 10.0f));
                        } else if (phyHisListBean.getLevel3Count() > 0) {
                            arrayList.add(i4, new Entry(i4, 15.0f));
                        } else if (phyHisListBean.getLevel4Count() > 0) {
                            arrayList.add(i4, new Entry(i4, 20.0f));
                        }
                    }
                    secondsForDay2++;
                }
                i2++;
            }
        }
        setChart(context, mChart, arrayList);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static void setChart(Context context, final LineChart chart, List<Entry> values) throws Resources.NotFoundException {
        LineDataSet lineDataSet = new LineDataSet(values, "");
        if (chart.getData() != null && ((LineData) chart.getData()).getDataSetCount() > 0) {
            lineDataSet = (LineDataSet) ((LineData) chart.getData()).getDataSetByIndex(0);
            lineDataSet.setEntries(values);
            lineDataSet.notifyDataSetChanged();
            ((LineData) chart.getData()).notifyDataChanged();
            chart.notifyDataSetChanged();
        } else {
            lineDataSet.setDrawIcons(false);
            lineDataSet.setDrawCircles(false);
            lineDataSet.setColor(-1);
            lineDataSet.setCircleColor(-1);
            lineDataSet.setDrawCircles(false);
            lineDataSet.setLineWidth(0.0f);
            lineDataSet.setCircleRadius(3.0f);
            lineDataSet.setDrawCircleHole(false);
            lineDataSet.setFormLineWidth(1.0f);
            lineDataSet.setFormLineDashEffect(new DashPathEffect(new float[]{10.0f, 5.0f}, 0.0f));
            lineDataSet.setFormSize(15.0f);
            lineDataSet.setValueTextSize(9.0f);
            lineDataSet.setDrawFilled(true);
            lineDataSet.type = 1;
            lineDataSet.setDrawValues(false);
            lineDataSet.setDrawHorizontalHighlightIndicator(false);
            lineDataSet.setFillFormatter(new IFillFormatter() { // from class: com.yucheng.smarthealthpro.customchart.phy.PhyBarChartUtils$$ExternalSyntheticLambda1
                @Override // com.yucheng.smarthealthpro.customchart.formatter.IFillFormatter
                public final float getFillLinePosition(ILineDataSet iLineDataSet, LineDataProvider lineDataProvider) {
                    return chart.getAxisLeft().getAxisMinimum();
                }
            });
            lineDataSet.setFillAlpha(255);
            context.getResources().getColor(R.color.transparent);
            lineDataSet.setFillColor(context.getColor(R.color._004cff));
            lineDataSet.dpColor = context.getColor(R.color._7d9ffb);
            lineDataSet.lightColor = context.getColor(R.color._219dfc);
            lineDataSet.remColor = context.getColor(R.color._00e5ff);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(lineDataSet);
        chart.setData(new LineData(arrayList));
    }

    public static void initChart(Context context, BarChart chart, List<PhyHisListBean> mHisListBean, String mToDay, FORMATTER formatter) {
        Logger.w(new Gson().toJson(mHisListBean), new Object[0]);
        chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() { // from class: com.yucheng.smarthealthpro.customchart.phy.PhyBarChartUtils.2
            @Override // com.yucheng.smarthealthpro.customchart.listener.OnChartValueSelectedListener
            public void onNothingSelected() {
            }

            @Override // com.yucheng.smarthealthpro.customchart.listener.OnChartValueSelectedListener
            public void onValueSelected(Entry e2, Highlight h2) {
            }
        });
        chart.getDescription().setEnabled(false);
        chart.setMaxVisibleValueCount(40);
        chart.setPinchZoom(false);
        chart.setScaleEnabled(false);
        chart.setScaleXEnabled(true);
        chart.setScaleYEnabled(false);
        chart.setDoubleTapToZoomEnabled(false);
        chart.setPinchZoom(false);
        chart.setDragXEnabled(true);
        chart.setDragYEnabled(false);
        chart.setDrawGridBackground(false);
        chart.setDrawBarShadow(false);
        chart.setDrawValueAboveBar(false);
        chart.setHighlightFullBarEnabled(false);
        YAxis axisLeft = chart.getAxisLeft();
        axisLeft.setAxisMinimum(0.0f);
        axisLeft.setDrawGridLines(false);
        axisLeft.setEnabled(false);
        chart.getAxisRight().setEnabled(false);
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawGridLines(false);
        if (formatter == FORMATTER.WEEK) {
            xAxis.setValueFormatter(new MyWeekCustomXAxisValueFormatter(true));
            xAxis.setAxisMaximum(0.0f);
            xAxis.setAxisMaximum(7.0f);
        } else if (formatter == FORMATTER.MONTH) {
            xAxis.setValueFormatter(new MyMonthCustomXAxisValueFormatter(true, 30));
            xAxis.setAxisMaximum(0.0f);
            xAxis.setAxisMaximum(30.0f);
            xAxis.setLabelCount(6);
        }
        chart.getLegend().setEnabled(false);
        chart.getDescription().setEnabled(false);
        setChartData(context, chart, mHisListBean, mToDay);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void setChartData(Context context, BarChart chart, List<PhyHisListBean> mHisListBean, String mToDay) {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < mHisListBean.size(); i2++) {
            arrayList.add(new BarEntry(i2, new float[]{mHisListBean.get(i2).getLevel1Duration(), mHisListBean.get(i2).getLevel2Duration(), mHisListBean.get(i2).getLevel3Duration(), mHisListBean.get(i2).getLevel4Duration()}));
        }
        if (chart.getData() != null && ((BarData) chart.getData()).getDataSetCount() > 0) {
            ((BarDataSet) ((BarData) chart.getData()).getDataSetByIndex(0)).setEntries(arrayList);
            ((BarData) chart.getData()).notifyDataChanged();
            chart.notifyDataSetChanged();
        } else {
            BarDataSet barDataSet = new BarDataSet(arrayList, "");
            barDataSet.setDrawIcons(false);
            barDataSet.setDrawValues(false);
            Logger.w(new Gson().toJson(getColors()), new Object[0]);
            barDataSet.setColors(getColors());
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(barDataSet);
            BarData barData = new BarData(arrayList2);
            barData.setValueFormatter(new IValueFormatter() { // from class: com.yucheng.smarthealthpro.customchart.phy.PhyBarChartUtils$$ExternalSyntheticLambda0
                @Override // com.yucheng.smarthealthpro.customchart.formatter.IValueFormatter
                public final String getFormattedValue(float f2, Entry entry, int i3, ViewPortHandler viewPortHandler) {
                    return BuildConfig.TRAVIS;
                }
            });
            barData.setValueTextColor(-1);
            barData.setBarWidth(0.2f);
            chart.setData(barData);
        }
        chart.setFitBars(true);
        chart.invalidate();
    }

    private static int[] getColors() {
        int[] iArr = new int[4];
        System.arraycopy(PHYSIOTHERAPY_COLORS, 0, iArr, 0, 4);
        return iArr;
    }
}
