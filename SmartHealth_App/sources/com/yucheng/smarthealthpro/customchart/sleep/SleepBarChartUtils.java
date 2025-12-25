package com.yucheng.smarthealthpro.customchart.sleep;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.DashPathEffect;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.widget.NestedScrollView;
import com.google.android.gms.common.ConnectionResult;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.customchart.MyMarkerView;
import com.yucheng.smarthealthpro.customchart.MyMonthCustomXAxisValueFormatter;
import com.yucheng.smarthealthpro.customchart.MyStepDayCustomXAxisValueFormatter;
import com.yucheng.smarthealthpro.customchart.MyWeekCustomXAxisValueFormatter;
import com.yucheng.smarthealthpro.customchart.charts.BarChart;
import com.yucheng.smarthealthpro.customchart.charts.Chart;
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
import com.yucheng.smarthealthpro.customchart.interfaces.dataprovider.LineDataProvider;
import com.yucheng.smarthealthpro.customchart.interfaces.datasets.ILineDataSet;
import com.yucheng.smarthealthpro.customchart.renderer.MyXAxisRenderer;
import com.yucheng.smarthealthpro.customchart.utils.ColorTemplate;
import com.yucheng.smarthealthpro.database.room.bean.SleepItem;
import com.yucheng.smarthealthpro.home.activity.phy.bean.PhyDayInfo;
import com.yucheng.smarthealthpro.home.activity.sleep.bean.SleepDayInfo;
import com.yucheng.smarthealthpro.home.activity.sleep.bean.SleepHisListBean;
import com.yucheng.smarthealthpro.me.setting.contacts.utils.DpUtil;
import com.yucheng.smarthealthpro.utils.FormatUtil;
import com.yucheng.smarthealthpro.view.chart.PhyData;
import com.yucheng.smarthealthpro.view.chart.PhyDataSet;
import com.yucheng.smarthealthpro.view.chart.SleepChart;
import com.yucheng.smarthealthpro.view.chart.SleepItemEntry;
import com.yucheng.smarthealthpro.view.chart.TimeAxisValueFormatter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* loaded from: classes4.dex */
public class SleepBarChartUtils {
    private static SleepBarChartUtils mSleepBarChartUtils = null;
    private static List<SleepItem> mSleepData = null;
    private static int maxTime = 2;
    private static List<BarEntry> monthValues;
    private static int sunTime;
    private static List<BarEntry> weekValues;
    private static XAxis xAxis;
    private static XAxis xDayAxis;
    private static YAxis yAxis;
    private static YAxis yDayAxis;
    private BarChart mBarChart;
    private BarDataSet mBrokenLine;
    private BarDataSet mDayBrokenLine;
    private BarDataSet mMonthBrokenLine;
    private NestedScrollView mNestedScrollView;
    private BarDataSet mWeekBrokenLine;
    public static final int[] SLEEP_WEEK_COLORS = {ColorTemplate.rgb("#5E32DA"), ColorTemplate.rgb("#DD53D8"), ColorTemplate.rgb("#F36F6C"), ColorTemplate.rgb("#e74c3c"), ColorTemplate.rgb("#3498db")};
    public static final int[] PHYSIOTHERAPY_COLORS = {ColorTemplate.rgb("#7D9FFB"), ColorTemplate.rgb("#219DFC"), ColorTemplate.rgb("#00E5FF"), ColorTemplate.rgb("#004CFF")};

    public enum FORMATTER {
        DAY,
        WEEK,
        MONTH
    }

    private SleepBarChartUtils() {
    }

    public static synchronized SleepBarChartUtils getInstance() {
        if (mSleepBarChartUtils == null) {
            mSleepBarChartUtils = new SleepBarChartUtils();
        }
        return mSleepBarChartUtils;
    }

    public static void initBarChart(BarChart mBarChart, Context context, List<SleepHisListBean> mSleepHisListBean, final NestedScrollView mNestedScrollView, float yMaximum, float yMinimum, float xMaximum, int mXLabelCount, FORMATTER formatter) {
        mBarChart.setBackgroundColor(-1);
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
        mBarChart.setOnTouchListener(new View.OnTouchListener() { // from class: com.yucheng.smarthealthpro.customchart.sleep.SleepBarChartUtils.1
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
        xAxis.setAxisMaximum(xMaximum);
        xAxis.setYOffset(DpUtil.dp2px(context, 7.0f));
        if (formatter == FORMATTER.DAY) {
            xAxis.setLabelCount(mXLabelCount, true);
            xAxis.setValueFormatter(new MyStepDayCustomXAxisValueFormatter(true));
            xAxis.setAvoidFirstLastClipping(true);
            mBarChart.setXAxisRenderer(new MyXAxisRenderer(mBarChart.getViewPortHandler(), mBarChart.getXAxis(), mBarChart.getTransformer(YAxis.AxisDependency.LEFT)));
        } else if (formatter == FORMATTER.WEEK) {
            xAxis.setLabelCount(mXLabelCount, false);
            xAxis.setValueFormatter(new MyWeekCustomXAxisValueFormatter(true));
            setWeekData(mBarChart, context, mSleepHisListBean);
            setBarChart(mBarChart, weekValues, context);
        } else {
            xAxis.setLabelCount(mXLabelCount, true);
            xAxis.setValueFormatter(new MyMonthCustomXAxisValueFormatter(true, 30));
            xAxis.setAvoidFirstLastClipping(true);
            mBarChart.setXAxisRenderer(new MyXAxisRenderer(mBarChart.getViewPortHandler(), mBarChart.getXAxis(), mBarChart.getTransformer(YAxis.AxisDependency.LEFT)));
            setMonthData(mBarChart, context, mSleepHisListBean);
            setBarChart(mBarChart, monthValues, context);
        }
        yAxis.setAxisMaximum(((float) maxTime) * 1.6f >= 8.0f ? (float) new BigDecimal(maxTime * 1.6f).setScale(0, 4).doubleValue() : 8.0f);
    }

    public static void initSleepChart(LineChart mSleepDayChart, Context context, List<SleepDayInfo> mSleepDataBeans, NestedScrollView mNestedScrollView, float yMaximum, float yMinimum, float xMaximum, int mXLabelCount, FORMATTER formatter) throws Resources.NotFoundException {
        initSleepChart(mSleepDayChart, context, mSleepDataBeans, mNestedScrollView, yMaximum, yMinimum, xMaximum, mXLabelCount, formatter, true);
    }

    public static void initSleepChart(LineChart mSleepDayChart, Context context, List<SleepDayInfo> mSleepDataBeans, final NestedScrollView mNestedScrollView, float yMaximum, float yMinimum, float xMaximum, int mXLabelCount, FORMATTER formatter, boolean isCanTouch) throws Resources.NotFoundException {
        mSleepDayChart.setBackgroundColor(-1);
        mSleepDayChart.getDescription().setEnabled(false);
        mSleepDayChart.setTouchEnabled(isCanTouch);
        mSleepDayChart.setDrawGridBackground(false);
        mSleepDayChart.setDrawBorders(false);
        mSleepDayChart.getAxisLeft().setDrawGridLines(false);
        mSleepDayChart.getAxisRight().setDrawGridLines(false);
        mSleepDayChart.getXAxis().setDrawGridLines(false);
        mSleepDayChart.setDragEnabled(isCanTouch);
        mSleepDayChart.setScaleEnabled(false);
        mSleepDayChart.setPinchZoom(false);
        mSleepDayChart.setOnTouchListener(new View.OnTouchListener() { // from class: com.yucheng.smarthealthpro.customchart.sleep.SleepBarChartUtils.2
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View v, MotionEvent event) {
                if (mNestedScrollView == null) {
                    return false;
                }
                if (event.getAction() == 1) {
                    mNestedScrollView.requestDisallowInterceptTouchEvent(false);
                } else {
                    mNestedScrollView.requestDisallowInterceptTouchEvent(true);
                }
                return false;
            }
        });
        XAxis xAxis2 = mSleepDayChart.getXAxis();
        xDayAxis = xAxis2;
        xAxis2.setPosition(XAxis.XAxisPosition.BOTTOM);
        xDayAxis.setDrawAxisLine(true);
        xDayAxis.setLabelCount(mXLabelCount, true);
        xDayAxis.setValueFormatter(new MySleepCustomXAxisValueFormatter(true));
        xDayAxis.setAvoidFirstLastClipping(true);
        xDayAxis.setYOffset(DpUtil.dp2px(context, 7.0f));
        mSleepDayChart.setXAxisRenderer(new MyXAxisRenderer(mSleepDayChart.getViewPortHandler(), mSleepDayChart.getXAxis(), mSleepDayChart.getTransformer(YAxis.AxisDependency.LEFT)));
        yDayAxis = mSleepDayChart.getAxisLeft();
        mSleepDayChart.getAxisRight().setEnabled(false);
        yDayAxis.setDrawAxisLine(false);
        yDayAxis.setDrawLabels(false);
        yDayAxis.setAxisMaximum(30.0f);
        yDayAxis.setAxisMinimum(0.0f);
        setData(context, mSleepDayChart, mSleepDataBeans, xDayAxis);
        mSleepDayChart.getLegend().setForm(Legend.LegendForm.NONE);
    }

    private static void setData(Context context, LineChart mSleepChart, List<SleepDayInfo> mSleepInfo, XAxis xAxis2) throws Resources.NotFoundException {
        int i2;
        ArrayList arrayList = new ArrayList();
        if (mSleepInfo == null) {
            return;
        }
        int i3 = 0;
        if (mSleepInfo.size() == 0) {
            while (i3 < 1440) {
                arrayList.add(new Entry(i3, 0.0f));
                i3++;
            }
            xAxis2.setAxisMaximum(1440.0f);
            xAxis2.setAxisMinimum(0.0f);
            xAxis2.setSleep(0.0f);
            xAxis2.setUp(1440.0f);
        } else {
            xAxis2.setSleep(mSleepInfo.get(0).beginTime);
            xAxis2.setUp(mSleepInfo.get(mSleepInfo.size() - 1).endTime);
            int i4 = mSleepInfo.get(0).beginTime;
            if (i4 > mSleepInfo.get(mSleepInfo.size() - 1).endTime) {
                i2 = (1440 - mSleepInfo.get(0).beginTime) + mSleepInfo.get(mSleepInfo.size() - 1).endTime;
            } else {
                i2 = mSleepInfo.get(mSleepInfo.size() - 1).endTime - mSleepInfo.get(0).beginTime;
            }
            xAxis2.setAxisMaximum(i2);
            xAxis2.setAxisMinimum(0.0f);
            for (int i5 = 0; i5 < i2; i5++) {
                arrayList.add(new Entry(i5, 20.0f));
            }
            while (i3 < mSleepInfo.size()) {
                SleepDayInfo sleepDayInfo = mSleepInfo.get(i3);
                int i6 = sleepDayInfo.beginTime;
                int i7 = sleepDayInfo.endTime;
                if (i7 < i6) {
                    i7 += 1440;
                }
                while (i6 < i7) {
                    int i8 = i6 - i4;
                    if (i8 < 0) {
                        i8 += 1440;
                    }
                    if (i8 < arrayList.size()) {
                        arrayList.remove(i8);
                        if (sleepDayInfo.type == 1) {
                            arrayList.add(i8, new Entry(i8, 5.0f));
                        } else if (sleepDayInfo.type == 2) {
                            arrayList.add(i8, new Entry(i8, 10.0f));
                        } else if (sleepDayInfo.type == 3) {
                            arrayList.add(i8, new Entry(i8, 15.0f));
                        } else if (sleepDayInfo.type == 4) {
                            arrayList.add(i8, new Entry(i8, 20.0f));
                        } else if (sleepDayInfo.type == 5) {
                            arrayList.add(i8, new Entry(i8, 15.0f));
                        } else {
                            arrayList.add(i8, new Entry(i8, 20.0f));
                        }
                    }
                    i6++;
                }
                i3++;
            }
        }
        MyMarkerView myMarkerView = new MyMarkerView(context, R.layout.layout_for_custom_marker_view, null, null, mSleepInfo, null);
        myMarkerView.setChartView(mSleepChart);
        mSleepChart.setMarker(myMarkerView, null, null, mSleepInfo, null, Chart.MarkerLabel.SLEEP_DAY_CHART);
        setLineChart(context, mSleepChart, arrayList);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static void setLineChart(Context context, final LineChart mSleepChart, List<Entry> values) throws Resources.NotFoundException {
        LineDataSet lineDataSet = new LineDataSet(values, "");
        lineDataSet.setMode(LineDataSet.Mode.SLEEP_MODE);
        if (mSleepChart.getData() != null && ((LineData) mSleepChart.getData()).getDataSetCount() > 0) {
            lineDataSet = (LineDataSet) ((LineData) mSleepChart.getData()).getDataSetByIndex(0);
            lineDataSet.setValues(values);
            lineDataSet.notifyDataSetChanged();
            ((LineData) mSleepChart.getData()).notifyDataChanged();
            mSleepChart.notifyDataSetChanged();
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
            lineDataSet.setFillFormatter(new IFillFormatter() { // from class: com.yucheng.smarthealthpro.customchart.sleep.SleepBarChartUtils.3
                @Override // com.yucheng.smarthealthpro.customchart.formatter.IFillFormatter
                public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                    return mSleepChart.getAxisLeft().getAxisMinimum();
                }
            });
            lineDataSet.setFillAlpha(255);
            context.getResources().getColor(R.color.transparent);
            lineDataSet.setFillColor(context.getColor(R.color.sleep_wake_color));
            lineDataSet.dpColor = context.getColor(R.color.sleep_deep_color);
            lineDataSet.lightColor = context.getColor(R.color.sleep_light_color);
            lineDataSet.remColor = context.getColor(R.color.sleep_eye_movement_color);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(lineDataSet);
        mSleepChart.setData(new LineData(arrayList));
    }

    private static void setWeekData(BarChart mBarChart, Context context, List<SleepHisListBean> mSleepHisListBean) {
        weekValues = new ArrayList();
        sunTime = 0;
        if (mSleepHisListBean.size() != 0) {
            int i2 = 0;
            while (i2 < mSleepHisListBean.size()) {
                double deepSleepTotal = (mSleepHisListBean.get(i2).getDeepSleepTotal() / 60.0f) / 60.0f;
                double lightSleepTotal = (mSleepHisListBean.get(i2).getLightSleepTotal() / 60.0f) / 60.0f;
                double d2 = (mSleepHisListBean.get(i2).remTimes / 60.0f) / 60.0f;
                int i3 = i2;
                weekValues.add(new BarEntry(i2, new float[]{(float) FormatUtil.getBigDecimal(deepSleepTotal).setScale(1, 4).doubleValue(), (float) FormatUtil.getBigDecimal(lightSleepTotal).setScale(1, 4).doubleValue(), (float) FormatUtil.getBigDecimal(d2).setScale(1, 4).doubleValue()}));
                double d3 = deepSleepTotal + lightSleepTotal + d2;
                if (sunTime < d3) {
                    sunTime = (int) d3;
                }
                int i4 = maxTime;
                int i5 = sunTime;
                if (i4 < i5) {
                    maxTime = i5;
                }
                i2 = i3 + 1;
            }
        } else {
            for (int i6 = 0; i6 < 7; i6++) {
                weekValues.add(new BarEntry(i6, new float[]{0.0f, 0.0f, 0.0f}));
            }
        }
        MyMarkerView myMarkerView = new MyMarkerView(context, R.layout.layout_for_custom_marker_view, null, null, null, null);
        myMarkerView.setChartView(mBarChart);
        mBarChart.setMarker(myMarkerView, null, null, null, null, Chart.MarkerLabel.SLEEP_WEEK_CHART);
    }

    private static void setMonthData(BarChart mBarChart, Context context, List<SleepHisListBean> mSleepHisListBean) {
        monthValues = new ArrayList();
        sunTime = 0;
        if (mSleepHisListBean.size() != 0) {
            for (int i2 = 0; i2 < mSleepHisListBean.size(); i2++) {
                float deepSleepTotal = (mSleepHisListBean.get(i2).getDeepSleepTotal() / 60.0f) / 60.0f;
                float lightSleepTotal = (mSleepHisListBean.get(i2).getLightSleepTotal() / 60.0f) / 60.0f;
                float f2 = (mSleepHisListBean.get(i2).remTimes / 60.0f) / 60.0f;
                monthValues.add(new BarEntry(i2, new float[]{(float) FormatUtil.getBigDecimal(deepSleepTotal).setScale(1, 4).doubleValue(), (float) FormatUtil.getBigDecimal(lightSleepTotal).setScale(1, 4).doubleValue(), (float) FormatUtil.getBigDecimal(f2).setScale(1, 4).doubleValue()}));
                float f3 = deepSleepTotal + lightSleepTotal + f2;
                if (sunTime < f3) {
                    sunTime = (int) f3;
                }
                int i3 = maxTime;
                int i4 = sunTime;
                if (i3 < i4) {
                    maxTime = i4;
                }
            }
        } else {
            for (int i5 = 0; i5 < 30; i5++) {
                monthValues.add(new BarEntry(i5, new float[]{0.0f, 0.0f, 0.0f}));
            }
        }
        MyMarkerView myMarkerView = new MyMarkerView(context, R.layout.layout_for_custom_marker_view, null, null, null, null);
        myMarkerView.setChartView(mBarChart);
        mBarChart.setMarker(myMarkerView, null, null, null, null, Chart.MarkerLabel.SLEEP_MONTH_CHART);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void setBarChart(BarChart mBarChart, List<BarEntry> values, Context context) {
        if (mBarChart.getData() != null && ((BarData) mBarChart.getData()).getDataSetCount() > 0) {
            ((BarDataSet) ((BarData) mBarChart.getData()).getDataSetByIndex(0)).setValues(values);
            ((BarData) mBarChart.getData()).notifyDataChanged();
            mBarChart.notifyDataSetChanged();
        } else {
            BarDataSet barDataSet = new BarDataSet(values, "Data Set");
            barDataSet.setColors(getColors());
            barDataSet.setDrawValues(false);
            ArrayList arrayList = new ArrayList();
            arrayList.add(barDataSet);
            mBarChart.setData(new BarData(arrayList));
            mBarChart.setFitBars(true);
        }
        mBarChart.invalidate();
    }

    private static int[] getColors() {
        int[] iArr = new int[3];
        System.arraycopy(SLEEP_WEEK_COLORS, 0, iArr, 0, 3);
        return iArr;
    }

    public static String transferLongToDate(String dateFormat, Long millSec) {
        return new SimpleDateFormat(dateFormat).format(new Date(millSec.longValue()));
    }

    public static void initSleepChart(SleepChart mSleepDayChart, Context context, List<PhyDayInfo> mSleepDataBeans, final NestedScrollView mNestedScrollView, float yMaximum, float yMinimum, float xMaximum, int mXLabelCount, FORMATTER formatter) {
        mSleepDayChart.setBackgroundColor(-1);
        mSleepDayChart.getDescription().setEnabled(false);
        mSleepDayChart.setTouchEnabled(true);
        mSleepDayChart.setDrawGridBackground(false);
        mSleepDayChart.setDrawBorders(false);
        mSleepDayChart.getAxisLeft().setDrawGridLines(false);
        mSleepDayChart.getAxisRight().setDrawGridLines(false);
        mSleepDayChart.getXAxis().setDrawGridLines(false);
        mSleepDayChart.setDragEnabled(true);
        mSleepDayChart.setScaleEnabled(true);
        mSleepDayChart.setScaleYEnabled(false);
        mSleepDayChart.setPinchZoom(false);
        mSleepDayChart.setOnTouchListener(new View.OnTouchListener() { // from class: com.yucheng.smarthealthpro.customchart.sleep.SleepBarChartUtils.4
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
        XAxis xAxis2 = mSleepDayChart.getXAxis();
        xAxis2.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis2.setDrawAxisLine(true);
        xAxis2.setLabelCount(5, true);
        xAxis2.setValueFormatter(new TimeAxisValueFormatter());
        xAxis2.setAvoidFirstLastClipping(true);
        mSleepDayChart.setXAxisRenderer(new MyXAxisRenderer(mSleepDayChart.getViewPortHandler(), mSleepDayChart.getXAxis(), mSleepDayChart.getTransformer(YAxis.AxisDependency.LEFT)));
        YAxis axisLeft = mSleepDayChart.getAxisLeft();
        mSleepDayChart.getAxisRight().setEnabled(false);
        axisLeft.setDrawAxisLine(false);
        axisLeft.setDrawLabels(false);
        axisLeft.setAxisMaximum(30.0f);
        axisLeft.setAxisMinimum(0.0f);
        mSleepDayChart.getLegend().setForm(Legend.LegendForm.NONE);
        setData(context, mSleepDayChart, mSleepDataBeans);
        mSleepDayChart.setFitBars(true);
        mSleepDayChart.invalidate();
    }

    private static void setData(Context context, SleepChart mSleepChart, List<PhyDayInfo> mPhyInfo) {
        int i2;
        ArrayList arrayList = new ArrayList();
        if (mPhyInfo == null) {
            return;
        }
        if (mPhyInfo.size() > 0) {
            XAxis xAxis2 = mSleepChart.getXAxis();
            if (mPhyInfo.size() > 1) {
                xAxis2.setAxisMinimum(-240.0f);
                if (mPhyInfo.get(mPhyInfo.size() - 1).endTime < 1200) {
                    xAxis2.setAxisMaximum(1200.0f);
                } else {
                    xAxis2.setAxisMaximum(r2 + 100);
                }
            } else {
                xAxis2.setAxisMinimum(-240.0f);
                xAxis2.setAxisMaximum(1200.0f);
            }
            for (int i3 = 0; i3 < mPhyInfo.size(); i3++) {
                PhyData phyData = mPhyInfo.get(i3).toPhyData();
                if (phyData.type == 1) {
                    i2 = 5;
                } else if (phyData.type == 2) {
                    i2 = 10;
                } else {
                    i2 = phyData.type == 3 ? 15 : 20;
                }
                arrayList.add(i3, new SleepItemEntry(phyData.beginTime, i2, phyData.type, phyData.beginTime, phyData.endTime));
            }
        }
        setSleepChart(context, mSleepChart, arrayList);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static void setSleepChart(Context context, SleepChart mSleepChart, List<BarEntry> values) {
        PhyDataSet phyDataSet = new PhyDataSet(values, "");
        phyDataSet.setStackLabels(new String[]{"1档", "2档", "3档", "4档"});
        phyDataSet.setDrawValues(false);
        int[] iArr = new int[4];
        System.arraycopy(PHYSIOTHERAPY_COLORS, 0, iArr, 0, 4);
        phyDataSet.setColors(iArr);
        if (mSleepChart.getData() != null && ((BarData) mSleepChart.getData()).getDataSetCount() > 0) {
            phyDataSet = (PhyDataSet) ((BarData) mSleepChart.getData()).getDataSetByIndex(0);
            phyDataSet.setEntries(values);
            phyDataSet.notifyDataSetChanged();
            ((BarData) mSleepChart.getData()).notifyDataChanged();
            mSleepChart.notifyDataSetChanged();
        } else {
            phyDataSet.setDrawIcons(false);
            phyDataSet.setFormLineWidth(1.0f);
            phyDataSet.setFormLineDashEffect(new DashPathEffect(new float[]{10.0f, 5.0f}, 0.0f));
            phyDataSet.setFormSize(15.0f);
            phyDataSet.setValueTextSize(9.0f);
            phyDataSet.setDrawValues(false);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(phyDataSet);
        mSleepChart.setData(new BarData(arrayList));
    }
}
