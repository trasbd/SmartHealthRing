package com.yucheng.smarthealthpro.customchart;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.widget.NestedScrollView;
import com.google.android.gms.common.ConnectionResult;
import com.orhanobut.logger.Logger;
import com.yucheng.smarthealthpro.customchart.charts.Chart;
import com.yucheng.smarthealthpro.customchart.charts.GradualBarChart;
import com.yucheng.smarthealthpro.customchart.components.XAxis;
import com.yucheng.smarthealthpro.customchart.components.YAxis;
import com.yucheng.smarthealthpro.customchart.data.BarData;
import com.yucheng.smarthealthpro.customchart.data.BarDataSet;
import com.yucheng.smarthealthpro.customchart.data.BarEntry;
import com.yucheng.smarthealthpro.customchart.renderer.MyXAxisRenderer;
import com.yucheng.smarthealthpro.framework.R;
import com.yucheng.smarthealthpro.home.activity.running.bean.StepBean;
import com.yucheng.smarthealthpro.home.bean.MyMonBean;
import com.yucheng.smarthealthpro.utils.TimeStampUtils;
import com.yucheng.smarthealthpro.utils.YearToDayListUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/* loaded from: classes4.dex */
public class MyBarChartUtils {
    private static List<BarEntry> dayBarEntries;
    private static List<BarEntry> halfYearBarEntries;
    private static MyBarChartUtils mBarChartUtils;
    private static List<BarEntry> monthBarEntries;
    private static List<BarEntry> weekBarEntries;
    private static XAxis xAxis;
    private static YAxis yAxis;
    private static List<BarEntry> yearBarEntries;
    private static int[] formatterValue = {5, 7, 2, 2, 2};
    private static int[] formatterCount = {24, 7, 30, 6, 12};

    public enum FORMATTER {
        DAY,
        WEEK,
        MONTH,
        HALFYEAR,
        YEAR
    }

    private MyBarChartUtils() {
    }

    public static synchronized MyBarChartUtils getInstance() {
        if (mBarChartUtils == null) {
            mBarChartUtils = new MyBarChartUtils();
        }
        return mBarChartUtils;
    }

    public static void initBarChart(GradualBarChart mBarChart, Context context, List<MyMonBean.Data.Values> datas, final NestedScrollView mNestedScrollView, float yMaximum, float yMinimum, FORMATTER formatter, String unit) {
        mBarChart.setBackgroundColor(context.getResources().getColor(R.color.white, null));
        mBarChart.getDescription().setEnabled(false);
        mBarChart.setTouchEnabled(true);
        mBarChart.setDrawGridBackground(false);
        mBarChart.getAxisLeft().setDrawGridLines(false);
        mBarChart.getAxisRight().setDrawGridLines(false);
        mBarChart.getXAxis().setDrawGridLines(false);
        mBarChart.setDragEnabled(true);
        mBarChart.setScaleEnabled(false);
        mBarChart.setPinchZoom(false);
        mBarChart.setMaxVisibleValueCount(60);
        mBarChart.setPinchZoom(false);
        mBarChart.setDrawBarShadow(false);
        mBarChart.getAxisLeft().setDrawGridLines(false);
        mBarChart.animateY(ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED);
        mBarChart.getLegend().setEnabled(false);
        mBarChart.setOnTouchListener(new View.OnTouchListener() { // from class: com.yucheng.smarthealthpro.customchart.MyBarChartUtils.1
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
        yAxis = mBarChart.getAxisLeft();
        mBarChart.getAxisRight().setEnabled(false);
        yAxis.setDrawAxisLine(false);
        yAxis.setAxisMaximum(yMaximum);
        yAxis.setAxisMinimum(yMinimum);
        XAxis xAxis2 = mBarChart.getXAxis();
        xAxis = xAxis2;
        xAxis2.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(true);
        xAxis.setAvoidFirstLastClipping(true);
        mBarChart.setXAxisRenderer(new MyXAxisRenderer(mBarChart.getViewPortHandler(), mBarChart.getXAxis(), mBarChart.getTransformer(YAxis.AxisDependency.LEFT)));
        if (formatter == FORMATTER.DAY) {
            xAxis.setAxisMaximum(formatterCount[0]);
            xAxis.setLabelCount(formatterValue[0], true);
            xAxis.setValueFormatter(new MyPDDayCustomXAxisValueFormatter(true));
            setDayData(mBarChart, datas, context, unit);
            setBarChart(mBarChart, dayBarEntries, context);
            return;
        }
        if (formatter == FORMATTER.WEEK) {
            xAxis.setAxisMaximum(formatterCount[1]);
            xAxis.setLabelCount(formatterValue[1], false);
            xAxis.setValueFormatter(new MyWeekCustomXAxisValueFormatter(true));
            setWeekData(mBarChart, datas, context, unit);
            setBarChart(mBarChart, weekBarEntries, context);
            return;
        }
        if (formatter == FORMATTER.MONTH) {
            Calendar calendar = Calendar.getInstance();
            int monthLastDay = YearToDayListUtils.getMonthLastDay(calendar.get(1), calendar.get(2) + 1);
            xAxis.setAxisMaximum(monthLastDay);
            xAxis.setLabelCount(formatterValue[2], true);
            xAxis.setValueFormatter(new MyMonthCustomXAxisValueFormatter(true, monthLastDay));
            setMonthData(mBarChart, datas, context, unit);
            setBarChart(mBarChart, monthBarEntries, context);
            return;
        }
        if (formatter == FORMATTER.HALFYEAR) {
            xAxis.setAxisMaximum(formatterCount[3]);
            xAxis.setLabelCount(formatterValue[3], true);
            xAxis.setValueFormatter(new MyHalfYearCustomXAxisValueFormatter(true, 6));
            setHalfYearData(mBarChart, datas, context, unit);
            setBarChart(mBarChart, halfYearBarEntries, context);
            return;
        }
        if (formatter == FORMATTER.YEAR) {
            xAxis.setAxisMaximum(formatterCount[4]);
            xAxis.setLabelCount(formatterValue[4], true);
            xAxis.setValueFormatter(new MyYearCustomXAxisValueFormatter(true, 12));
            setYearData(mBarChart, datas, context, unit);
            setBarChart(mBarChart, yearBarEntries, context);
        }
    }

    public static void setDayData(GradualBarChart mBarChart, List<MyMonBean.Data.Values> data, Context context, String unit) {
        ArrayList arrayList = new ArrayList();
        dayBarEntries = new ArrayList();
        int i2 = 0;
        while (i2 < 24) {
            arrayList.add(new StepBean(0.0f, (i2 < 10 ? new StringBuilder("0").append(i2) : new StringBuilder().append(i2).append("")).toString() + ":00", (i2 < 9 ? new StringBuilder("0").append(i2 + 1) : new StringBuilder().append(i2 + 1).append("")).toString() + ":00"));
            i2++;
        }
        for (int i3 = 0; i3 < data.size(); i3++) {
            int[] timeFromDateString = YearToDayListUtils.getTimeFromDateString(data.get(i3).time);
            if (timeFromDateString == null || timeFromDateString.length < 3 || timeFromDateString[1] != 0 || timeFromDateString[2] != 0) {
                Logger.d("chong-----------dayDataTime===" + data.get(i3).time);
            } else {
                arrayList.remove(timeFromDateString[0]);
                StepBean stepBean = new StepBean(0.0f, (timeFromDateString[0] < 10 ? new StringBuilder("0").append(timeFromDateString[0]) : new StringBuilder().append(timeFromDateString[0]).append("")).toString() + ":00", (timeFromDateString[0] < 9 ? new StringBuilder("0").append(timeFromDateString[0] + 1) : new StringBuilder().append(timeFromDateString[0] + 1).append("")).toString() + ":00");
                stepBean.unit = unit;
                try {
                    stepBean.setStep(Float.parseFloat(data.get(i3).displayvalue));
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                arrayList.add(timeFromDateString[0], stepBean);
            }
        }
        for (int i4 = 0; i4 < arrayList.size(); i4++) {
            dayBarEntries.add(new BarEntry(i4, ((StepBean) arrayList.get(i4)).getStep()));
        }
        MyMarkerView myMarkerView = new MyMarkerView(context, com.yucheng.smarthealthpro.R.layout.layout_for_custom_marker_view, null, null, null, arrayList);
        myMarkerView.setChartView(mBarChart);
        mBarChart.setMarker(myMarkerView, null, null, null, arrayList, Chart.MarkerLabel.STEP_CHART);
    }

    private static void setWeekData(GradualBarChart mBarChart, List<MyMonBean.Data.Values> data, Context context, String unit) {
        weekBarEntries = new ArrayList();
        List<StepBean> arrayList = new ArrayList<>();
        ArrayList<String> pastStringArrayByDate = YearToDayListUtils.getPastStringArrayByDate(new Date(), 7);
        for (int i2 = 0; i2 < pastStringArrayByDate.size(); i2++) {
            arrayList.add(new StepBean(0.0f, TimeStampUtils.dateForStringDate(TimeStampUtils.stringForDateDay(pastStringArrayByDate.get(i2))), ""));
        }
        for (int i3 = 0; i3 < pastStringArrayByDate.size(); i3++) {
            int i4 = 0;
            while (true) {
                if (i4 >= data.size()) {
                    break;
                }
                if (data.get(i4).time.equals(pastStringArrayByDate.get(i3))) {
                    arrayList.remove(i3);
                    StepBean stepBean = new StepBean(0.0f, data.get(i4).time, "");
                    stepBean.unit = unit;
                    try {
                        stepBean.setStep(Float.parseFloat(data.get(i4).displayvalue));
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    arrayList.add(i3, stepBean);
                    break;
                }
                i4++;
            }
        }
        for (int i5 = 0; i5 < arrayList.size(); i5++) {
            weekBarEntries.add(new BarEntry(i5, arrayList.get(i5).getStep()));
        }
        MyMarkerView myMarkerView = new MyMarkerView(context, com.yucheng.smarthealthpro.R.layout.layout_for_custom_marker_view, null, null, null, arrayList);
        myMarkerView.setChartView(mBarChart);
        mBarChart.setMarker(myMarkerView, null, null, null, arrayList, Chart.MarkerLabel.STEP_CHART);
    }

    private static void setMonthData(GradualBarChart mBarChart, List<MyMonBean.Data.Values> data, Context context, String unit) {
        monthBarEntries = new ArrayList();
        List<StepBean> arrayList = new ArrayList<>();
        ArrayList<String> pastStringArrayByDate = YearToDayListUtils.getPastStringArrayByDate(new Date(), YearToDayListUtils.getCurrMonthDay());
        for (int i2 = 0; i2 < pastStringArrayByDate.size(); i2++) {
            arrayList.add(new StepBean(0.0f, TimeStampUtils.dateForStringDate(TimeStampUtils.stringForDateDay(pastStringArrayByDate.get(i2))), ""));
        }
        for (int i3 = 0; i3 < pastStringArrayByDate.size(); i3++) {
            int i4 = 0;
            while (true) {
                if (i4 >= data.size()) {
                    break;
                }
                if (pastStringArrayByDate.get(i3).equals(data.get(i4).time)) {
                    arrayList.remove(i3);
                    StepBean stepBean = new StepBean(0.0f, data.get(i4).time, "");
                    stepBean.unit = unit;
                    try {
                        stepBean.setStep(Float.parseFloat(data.get(i4).displayvalue));
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    arrayList.add(i3, stepBean);
                    break;
                }
                i4++;
            }
        }
        for (int i5 = 0; i5 < arrayList.size(); i5++) {
            monthBarEntries.add(new BarEntry(i5, arrayList.get(i5).getStep()));
        }
        MyMarkerView myMarkerView = new MyMarkerView(context, com.yucheng.smarthealthpro.R.layout.layout_for_custom_marker_view, null, null, null, arrayList);
        myMarkerView.setChartView(mBarChart);
        mBarChart.setMarker(myMarkerView, null, null, null, arrayList, Chart.MarkerLabel.STEP_CHART);
    }

    private static void setHalfYearData(GradualBarChart mBarChart, List<MyMonBean.Data.Values> data, Context context, String unit) {
        halfYearBarEntries = new ArrayList();
        List<StepBean> arrayList = new ArrayList<>();
        ArrayList<String> postStringDateFromMonth = YearToDayListUtils.getPostStringDateFromMonth(6);
        for (int i2 = 0; i2 < postStringDateFromMonth.size(); i2++) {
            arrayList.add(new StepBean(0.0f, postStringDateFromMonth.get(i2), ""));
        }
        for (int i3 = 0; i3 < postStringDateFromMonth.size(); i3++) {
            int i4 = 0;
            while (true) {
                if (i4 >= data.size()) {
                    break;
                }
                if (Integer.parseInt(postStringDateFromMonth.get(i3).split("/")[1]) == Integer.parseInt(data.get(i4).time)) {
                    arrayList.remove(i3);
                    StepBean stepBean = new StepBean(0.0f, postStringDateFromMonth.get(i3), "");
                    stepBean.unit = unit;
                    try {
                        stepBean.setStep(Float.parseFloat(data.get(i4).displayvalue));
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    arrayList.add(i3, stepBean);
                    break;
                }
                i4++;
            }
        }
        for (int i5 = 0; i5 < arrayList.size(); i5++) {
            halfYearBarEntries.add(new BarEntry(i5, arrayList.get(i5).getStep()));
        }
        MyMarkerView myMarkerView = new MyMarkerView(context, com.yucheng.smarthealthpro.R.layout.layout_for_custom_marker_view, null, null, null, arrayList);
        myMarkerView.setChartView(mBarChart);
        mBarChart.setMarker(myMarkerView, null, null, null, arrayList, Chart.MarkerLabel.STEP_CHART);
    }

    private static void setYearData(GradualBarChart mBarChart, List<MyMonBean.Data.Values> data, Context context, String unit) {
        yearBarEntries = new ArrayList();
        List<StepBean> arrayList = new ArrayList<>();
        ArrayList<String> postStringDateFromMonth = YearToDayListUtils.getPostStringDateFromMonth(12);
        for (int i2 = 0; i2 < postStringDateFromMonth.size(); i2++) {
            arrayList.add(new StepBean(0.0f, postStringDateFromMonth.get(i2), ""));
        }
        for (int i3 = 0; i3 < postStringDateFromMonth.size(); i3++) {
            int i4 = 0;
            while (true) {
                if (i4 >= data.size()) {
                    break;
                }
                if (Integer.parseInt(postStringDateFromMonth.get(i3).split("/")[1]) == Integer.parseInt(data.get(i4).time)) {
                    arrayList.remove(i3);
                    StepBean stepBean = new StepBean(0.0f, postStringDateFromMonth.get(i3), "");
                    stepBean.unit = unit;
                    try {
                        stepBean.setStep(Float.parseFloat(data.get(i4).displayvalue));
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    arrayList.add(i3, stepBean);
                    break;
                }
                i4++;
            }
        }
        for (int i5 = 0; i5 < arrayList.size(); i5++) {
            yearBarEntries.add(new BarEntry(i5, arrayList.get(i5).getStep()));
        }
        MyMarkerView myMarkerView = new MyMarkerView(context, com.yucheng.smarthealthpro.R.layout.layout_for_custom_marker_view, null, null, null, arrayList);
        myMarkerView.setChartView(mBarChart);
        mBarChart.setMarker(myMarkerView, null, null, null, arrayList, Chart.MarkerLabel.STEP_CHART);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void setBarChart(GradualBarChart mBarChart, List<BarEntry> values, Context context) {
        if (mBarChart.getData() != null && ((BarData) mBarChart.getData()).getDataSetCount() > 0) {
            ((BarDataSet) ((BarData) mBarChart.getData()).getDataSetByIndex(0)).setValues(values);
            ((BarData) mBarChart.getData()).notifyDataChanged();
            mBarChart.notifyDataSetChanged();
        } else {
            BarDataSet barDataSet = new BarDataSet(values, "Data Set");
            barDataSet.setColors(context.getResources().getColor(com.yucheng.smarthealthpro.R.color.step_chart_start_bg));
            barDataSet.setDrawValues(false);
            ArrayList arrayList = new ArrayList();
            arrayList.add(barDataSet);
            mBarChart.setData(new BarData(arrayList));
            mBarChart.setFitBars(true);
        }
        mBarChart.invalidate();
    }
}
