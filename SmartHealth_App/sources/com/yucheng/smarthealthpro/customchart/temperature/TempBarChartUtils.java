package com.yucheng.smarthealthpro.customchart.temperature;

import android.content.Context;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import com.google.android.gms.common.ConnectionResult;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.customchart.MyMarkerView;
import com.yucheng.smarthealthpro.customchart.MyMonthCustomXAxisValueFormatter;
import com.yucheng.smarthealthpro.customchart.MySportHeartDayCustomXAxisValueFormatter;
import com.yucheng.smarthealthpro.customchart.MyTempDayCustomXAxisValueFormatter;
import com.yucheng.smarthealthpro.customchart.MyWeekCustomXAxisValueFormatter;
import com.yucheng.smarthealthpro.customchart.SportHeartMarkerView;
import com.yucheng.smarthealthpro.customchart.charts.BarChart;
import com.yucheng.smarthealthpro.customchart.charts.Chart;
import com.yucheng.smarthealthpro.customchart.components.Legend;
import com.yucheng.smarthealthpro.customchart.components.XAxis;
import com.yucheng.smarthealthpro.customchart.components.YAxis;
import com.yucheng.smarthealthpro.customchart.data.BarData;
import com.yucheng.smarthealthpro.customchart.data.BarDataSet;
import com.yucheng.smarthealthpro.customchart.data.BarEntry;
import com.yucheng.smarthealthpro.customchart.interfaces.datasets.IBarDataSet;
import com.yucheng.smarthealthpro.customchart.renderer.MyXAxisRenderer;
import com.yucheng.smarthealthpro.customchart.utils.CurveUtils;
import com.yucheng.smarthealthpro.customchart.utils.HourToMinute;
import com.yucheng.smarthealthpro.customchart.utils.Utils;
import com.yucheng.smarthealthpro.home.activity.temperature.bean.TemperatureHisListBean;
import com.yucheng.smarthealthpro.me.setting.contacts.utils.DpUtil;
import com.yucheng.smarthealthpro.utils.AppDateMgr;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.TimeStampUtils;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes4.dex */
public class TempBarChartUtils {
    private static ArrayList<BarEntry> dayValues = null;
    private static BarDataSet mBrokenLine = null;
    private static TempBarChartUtils mTempBarChartUtils = null;
    private static List<TemperBean> mTrueDayTemperBean = null;
    private static List<TemperBean> mTrueMonthTemperBean = null;
    private static List<TemperBean> mTrueWeekTemperBean = null;
    private static float max = 10.0f;
    private static float maxYNum = 220.0f;
    private static int minYNum;
    private static ArrayList<BarEntry> monthValues;
    private static ArrayList<BarEntry> weekValues;
    private static XAxis xAxis;
    private static YAxis yAxis;

    public enum FORMATTER {
        DAY,
        WEEK,
        MONTH
    }

    public enum FUNCTION {
        HEARTRATE,
        HRV,
        AWRR,
        BO,
        TEMP,
        BLOODSUGAR,
        BLOODFAT,
        URICACID,
        KETONE,
        PRESSURE
    }

    private TempBarChartUtils() {
    }

    public static synchronized TempBarChartUtils getInstance() {
        if (mTempBarChartUtils == null) {
            mTempBarChartUtils = new TempBarChartUtils();
        }
        return mTempBarChartUtils;
    }

    public static void initBarChart(BarChart mBarChart, Context context, List<TemperatureHisListBean> mBarChartBean, String mToDay, NestedScrollView mNestedScrollView, FUNCTION mFUNCTION, float yMaximum, float yMinimum, float xMaximum, int mXLabelCount, int mYLabelCount, FORMATTER formatter) throws NumberFormatException {
        initBarChart(mBarChart, context, mBarChartBean, mToDay, mNestedScrollView, mFUNCTION, yMaximum, yMinimum, xMaximum, 0.0f, mXLabelCount, mYLabelCount, formatter, false, true);
    }

    public static void initBarChart(BarChart mBarChart, Context context, List<TemperatureHisListBean> mBarChartBean, String mToDay, NestedScrollView mNestedScrollView, FUNCTION mFUNCTION, float yMaximum, float yMinimum, float xMaximum, int mXLabelCount, int mYLabelCount, FORMATTER formatter, boolean isCanTouch) throws NumberFormatException {
        initBarChart(mBarChart, context, mBarChartBean, mToDay, mNestedScrollView, mFUNCTION, yMaximum, yMinimum, xMaximum, 0.0f, mXLabelCount, mYLabelCount, formatter, false, isCanTouch);
    }

    public static void initBarChart(BarChart mBarChart, Context context, List<TemperatureHisListBean> mBarChartBean, String mToDay, final NestedScrollView mNestedScrollView, FUNCTION mFUNCTION, float yMaximum, float yMinimum, float xMaximum, float xMinimum, int mXLabelCount, int mYLabelCount, FORMATTER formatter, boolean isResetMax, boolean isCanTouch) throws NumberFormatException {
        max = 10.0f;
        mBarChart.setBackgroundColor(-1);
        mBarChart.getDescription().setEnabled(false);
        mBarChart.setTouchEnabled(isCanTouch);
        mBarChart.setDrawGridBackground(false);
        mBarChart.setDrawBorders(false);
        mBarChart.getAxisLeft().setDrawGridLines(false);
        mBarChart.getAxisRight().setDrawGridLines(false);
        mBarChart.getXAxis().setDrawGridLines(false);
        mBarChart.setDragEnabled(isCanTouch);
        mBarChart.setScaleXEnabled(false);
        mBarChart.setScaleYEnabled(false);
        mBarChart.setPinchZoom(false);
        mBarChart.setOnTouchListener(new View.OnTouchListener() { // from class: com.yucheng.smarthealthpro.customchart.temperature.TempBarChartUtils.1
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
        YAxis axisLeft = mBarChart.getAxisLeft();
        yAxis = axisLeft;
        axisLeft.setDrawAxisLine(false);
        mBarChart.getAxisRight().setEnabled(false);
        if (Constant.isHeGe() && !isCanTouch) {
            mBarChart.getAxisLeft().setEnabled(false);
        }
        yAxis.setAxisMinimum(yMinimum);
        yAxis.setLabelCount(mYLabelCount, true);
        mBarChart.getLegend().setForm(Legend.LegendForm.NONE);
        XAxis xAxis2 = mBarChart.getXAxis();
        xAxis = xAxis2;
        xAxis2.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(true);
        xAxis.setYOffset(DpUtil.dp2px(context, 7.0f));
        if (isResetMax) {
            xAxis.setAxisMaximum(xMaximum);
            xAxis.setAxisMinimum(xMinimum);
        } else {
            xAxis.setAxisMaximum(xMaximum);
        }
        if (formatter == FORMATTER.DAY) {
            xAxis.setLabelCount(mXLabelCount, true);
            xAxis.setValueFormatter(new MyTempDayCustomXAxisValueFormatter(true));
            xAxis.setAvoidFirstLastClipping(true);
            mBarChart.setXAxisRenderer(new MyXAxisRenderer(mBarChart.getViewPortHandler(), mBarChart.getXAxis(), mBarChart.getTransformer(YAxis.AxisDependency.LEFT)));
            if (mFUNCTION == FUNCTION.HRV) {
                setDayData(mBarChart, Chart.MarkerLabel.HRV_DAY_CHART, mBarChartBean, mToDay, context);
            } else if (mFUNCTION == FUNCTION.AWRR) {
                setDayData(mBarChart, Chart.MarkerLabel.RESPIRATORY_RATE_DAY_CHART, mBarChartBean, mToDay, context);
            } else if (mFUNCTION == FUNCTION.BO) {
                setDayData(mBarChart, Chart.MarkerLabel.BLOOD_OXYGEN_DAY_CHART, mBarChartBean, mToDay, context);
            } else if (mFUNCTION == FUNCTION.BLOODSUGAR || mFUNCTION == FUNCTION.BLOODFAT) {
                setDayData(mBarChart, Chart.MarkerLabel.BLOOD_SUGAR_DAY_CHART, mBarChartBean, mToDay, context);
            } else if (mFUNCTION == FUNCTION.URICACID) {
                setDayData(mBarChart, Chart.MarkerLabel.URIC_ACID_DAY_CHART, mBarChartBean, mToDay, context);
            } else if (mFUNCTION == FUNCTION.KETONE) {
                setDayData(mBarChart, Chart.MarkerLabel.BLOOD_KETONE_CHART, mBarChartBean, mToDay, context);
            } else if (mFUNCTION == FUNCTION.PRESSURE) {
                setDayData(mBarChart, Chart.MarkerLabel.PRESSURE_CHART, mBarChartBean, mToDay, context);
            } else {
                setDayData(mBarChart, Chart.MarkerLabel.TEMPER_CHART, mBarChartBean, mToDay, context);
            }
            yAxis.setAxisMaximum(maxYNum);
            setBarChart(mBarChart, dayValues, mFUNCTION, context, true);
        } else if (formatter == FORMATTER.WEEK) {
            xAxis.setLabelCount(mXLabelCount, true);
            xAxis.setValueFormatter(new MyWeekCustomXAxisValueFormatter(true));
            xAxis.setAvoidFirstLastClipping(true);
            mBarChart.setXAxisRenderer(new MyXAxisRenderer(mBarChart.getViewPortHandler(), mBarChart.getXAxis(), mBarChart.getTransformer(YAxis.AxisDependency.LEFT)));
            if (mFUNCTION == FUNCTION.HRV) {
                setWeekData(mBarChart, Chart.MarkerLabel.HRV_WEEK_MONTH_CHART, mBarChartBean, mToDay, context);
            } else if (mFUNCTION == FUNCTION.AWRR) {
                setWeekData(mBarChart, Chart.MarkerLabel.RESPIRATORY_WEEK_MONTH_CHART, mBarChartBean, mToDay, context);
            } else if (mFUNCTION == FUNCTION.BO) {
                setWeekData(mBarChart, Chart.MarkerLabel.BLOOD_OXYGEN_WEEK_MONTH_CHART, mBarChartBean, mToDay, context);
            } else if (mFUNCTION == FUNCTION.BLOODSUGAR || mFUNCTION == FUNCTION.BLOODFAT) {
                setWeekData(mBarChart, Chart.MarkerLabel.BLOOD_SUGAR_WEEK_MONTH_CHART, mBarChartBean, mToDay, context);
            } else if (mFUNCTION == FUNCTION.URICACID) {
                setWeekData(mBarChart, Chart.MarkerLabel.URIC_ACID_WEEK_MONTH_CHART, mBarChartBean, mToDay, context);
            } else if (mFUNCTION == FUNCTION.KETONE) {
                setWeekData(mBarChart, Chart.MarkerLabel.BLOOD_KETONE_WEEK_MONTH_CHART, mBarChartBean, mToDay, context);
            } else if (mFUNCTION == FUNCTION.PRESSURE) {
                setWeekData(mBarChart, Chart.MarkerLabel.PRESSURE_WEEK_MONTH_CHART, mBarChartBean, mToDay, context);
            } else {
                setWeekData(mBarChart, Chart.MarkerLabel.TEMPER_WEEK_MONTH_CHART, mBarChartBean, mToDay, context);
            }
            yAxis.setAxisMaximum(maxYNum);
            setBarChart(mBarChart, weekValues, mFUNCTION, context);
        } else {
            xAxis.setLabelCount(mXLabelCount, true);
            xAxis.setValueFormatter(new MyMonthCustomXAxisValueFormatter(true, 30));
            xAxis.setAvoidFirstLastClipping(true);
            mBarChart.setXAxisRenderer(new MyXAxisRenderer(mBarChart.getViewPortHandler(), mBarChart.getXAxis(), mBarChart.getTransformer(YAxis.AxisDependency.LEFT)));
            if (mFUNCTION == FUNCTION.HRV) {
                setMonthData(mBarChart, Chart.MarkerLabel.HEART_RATE_WEEK_MONTH_CHART, mBarChartBean, mToDay, context);
            } else if (mFUNCTION == FUNCTION.AWRR) {
                setMonthData(mBarChart, Chart.MarkerLabel.RESPIRATORY_WEEK_MONTH_CHART, mBarChartBean, mToDay, context);
            } else if (mFUNCTION == FUNCTION.BO) {
                setMonthData(mBarChart, Chart.MarkerLabel.BLOOD_OXYGEN_WEEK_MONTH_CHART, mBarChartBean, mToDay, context);
            } else if (mFUNCTION == FUNCTION.BLOODSUGAR || mFUNCTION == FUNCTION.BLOODFAT) {
                setMonthData(mBarChart, Chart.MarkerLabel.BLOOD_SUGAR_WEEK_MONTH_CHART, mBarChartBean, mToDay, context);
            } else if (mFUNCTION == FUNCTION.URICACID) {
                setMonthData(mBarChart, Chart.MarkerLabel.URIC_ACID_WEEK_MONTH_CHART, mBarChartBean, mToDay, context);
            } else if (mFUNCTION == FUNCTION.KETONE) {
                setMonthData(mBarChart, Chart.MarkerLabel.BLOOD_KETONE_WEEK_MONTH_CHART, mBarChartBean, mToDay, context);
            } else if (mFUNCTION == FUNCTION.PRESSURE) {
                setMonthData(mBarChart, Chart.MarkerLabel.PRESSURE_WEEK_MONTH_CHART, mBarChartBean, mToDay, context);
            } else {
                setMonthData(mBarChart, Chart.MarkerLabel.TEMPER_WEEK_MONTH_CHART, mBarChartBean, mToDay, context);
            }
            setBarChart(mBarChart, monthValues, mFUNCTION, context);
        }
        if (max * 1.2f < 10.0f) {
            maxYNum = 10.0f;
        } else {
            maxYNum = (float) new BigDecimal(max * 1.2f).setScale(0, 4).doubleValue();
        }
        if (mFUNCTION == FUNCTION.KETONE) {
            maxYNum = 50.0f;
        }
        yAxis.setAxisMaximum(maxYNum);
    }

    public static void setBarChart(BarChart mBarChart, List<BarEntry> values, FUNCTION mFUNCTION, Context context) {
        setBarChart(mBarChart, values, mFUNCTION, context, false);
    }

    public static void setBarChart(BarChart mBarChart, List<BarEntry> values, FUNCTION mFUNCTION, Context context, boolean isRemoveYAxis) {
        if (mBarChart.getData() != null && ((BarData) mBarChart.getData()).getDataSetCount() > 0) {
            BarDataSet barDataSet = (BarDataSet) ((IBarDataSet) ((BarData) mBarChart.getData()).getDataSetByIndex(0));
            mBrokenLine = barDataSet;
            barDataSet.setEntries(values);
            mBrokenLine.notifyDataSetChanged();
            ((BarData) mBarChart.getData()).notifyDataChanged();
            mBarChart.notifyDataSetChanged();
        } else {
            BarDataSet barDataSet2 = new BarDataSet(values, "");
            mBrokenLine = barDataSet2;
            barDataSet2.setDrawIcons(false);
            mBrokenLine.setColor(-1);
            mBrokenLine.setFormLineWidth(1.0f);
            mBrokenLine.setFormLineDashEffect(new DashPathEffect(new float[]{10.0f, 5.0f}, 0.0f));
            mBrokenLine.setFormSize(15.0f);
            mBrokenLine.setValueTextSize(9.0f);
            if (Utils.getSDKInt() >= 18) {
                if (mFUNCTION == FUNCTION.HEARTRATE) {
                    ContextCompat.getDrawable(context, R.drawable.fade_red_hrv);
                    if (Constant.isHeGe()) {
                        mBrokenLine.setColor(Color.parseColor("#bb2f4c"));
                    }
                } else if (mFUNCTION == FUNCTION.HRV) {
                    ContextCompat.getDrawable(context, R.drawable.fade_red_hrv);
                    if (Constant.isHeGe()) {
                        mBrokenLine.setColor(Color.parseColor("#bb2f4c"));
                    }
                } else if (mFUNCTION == FUNCTION.AWRR) {
                    ContextCompat.getDrawable(context, R.drawable.fade_green_awrr);
                } else if (mFUNCTION == FUNCTION.BO) {
                    ContextCompat.getDrawable(context, R.drawable.fade_orange_bo);
                    if (Constant.isHeGe()) {
                        mBrokenLine.setColor(Color.parseColor("#D420AB"));
                    }
                } else if (mFUNCTION == FUNCTION.BLOODSUGAR) {
                    ContextCompat.getDrawable(context, R.drawable.fade_blood_sugar);
                } else if (mFUNCTION == FUNCTION.BLOODFAT) {
                    ContextCompat.getDrawable(context, R.drawable.fade_blood_fat);
                } else if (mFUNCTION == FUNCTION.URICACID) {
                    ContextCompat.getDrawable(context, R.drawable.fade_uric_acid);
                } else if (mFUNCTION == FUNCTION.KETONE) {
                    ContextCompat.getDrawable(context, R.drawable.fade_ketone);
                } else {
                    ContextCompat.getDrawable(context, R.drawable.fade_blue_temp);
                    if (Constant.isHeGe()) {
                        mBrokenLine.setColor(Color.parseColor("#AFE3FE"));
                    }
                }
            }
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(mBrokenLine);
        if (isRemoveYAxis) {
            ArrayList arrayList2 = new ArrayList();
            for (int i2 = 0; i2 < mBrokenLine.getEntries().size(); i2++) {
                BarEntry barEntry = (BarEntry) mBrokenLine.getEntries().get(i2);
                BarEntry barEntry2 = new BarEntry(barEntry.getX(), 0.0f);
                if (i2 > 0 && ((BarEntry) mBrokenLine.getEntries().get(i2 - 1)).getY() == 0.0f && barEntry.getY() != 0.0f) {
                    barEntry2.setY(barEntry.getY());
                }
                if (i2 < mBrokenLine.getEntries().size() - 1) {
                    BarEntry barEntry3 = (BarEntry) mBrokenLine.getEntries().get(i2 + 1);
                    if (barEntry.getY() != 0.0f && barEntry3.getY() == 0.0f) {
                        barEntry2.setY(barEntry.getY());
                    }
                }
                arrayList2.add(barEntry2);
            }
            BarDataSet barDataSet3 = new BarDataSet(arrayList2, "");
            barDataSet3.setColor(-1);
            arrayList.add(barDataSet3);
        }
        mBarChart.setData(new BarData(arrayList));
        Iterator it2 = ((BarData) mBarChart.getData()).getDataSets().iterator();
        while (it2.hasNext()) {
            ((BarDataSet) ((IBarDataSet) it2.next())).setDrawValues(false);
        }
        mBarChart.invalidate();
    }

    public static void setDayData(BarChart mBarChart, Chart.MarkerLabel mMarkerLabel, List<TemperatureHisListBean> mBarChartBean, String mToDay, Context context) throws NumberFormatException {
        dayValues = new ArrayList<>();
        List<TemperBean> arrayList = new ArrayList<>();
        int i2 = 1;
        if (mBarChartBean != null && mBarChartBean.size() != 0) {
            for (int size = mBarChartBean.size() - 1; size >= 0; size--) {
                float f2 = Float.parseFloat(mBarChartBean.get(size).getmValue().replaceAll(",", "."));
                arrayList.add(new TemperBean(f2, HourToMinute.timeToMinute(mBarChartBean.get(size).getTime()), ""));
                if (max < f2) {
                    max = f2;
                }
            }
            MyMarkerView myMarkerView = new MyMarkerView(context, R.layout.layout_for_custom_marker_view, arrayList, null, null, null);
            myMarkerView.setChartView(mBarChart);
            mBarChart.setMarker(myMarkerView, arrayList, null, null, null, mMarkerLabel);
        } else {
            arrayList.add(new TemperBean(0.0f, 0, ""));
            MyMarkerView myMarkerView2 = new MyMarkerView(context, R.layout.layout_for_custom_marker_view, arrayList, null, null, null);
            myMarkerView2.setChartView(mBarChart);
            mBarChart.setMarker(myMarkerView2, arrayList, null, null, null, mMarkerLabel);
        }
        ArrayList arrayList2 = new ArrayList();
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            arrayList2.add(new TemperBean(arrayList.get(i3).getTemper(), arrayList.get(i3).getTime(), ""));
        }
        int time = arrayList.get(0).getTime();
        int time2 = arrayList.get(arrayList.size() - 1).getTime();
        int size2 = arrayList.size();
        int size3 = arrayList.size();
        if (size2 >= 3 || time == 0 || time2 == 1440) {
            if (time != 0) {
                arrayList2.add(0, new TemperBean(minYNum, 0, ""));
                size3++;
            }
            if (time2 != 1440) {
                arrayList2.add(size3, new TemperBean(minYNum, 1440, ""));
            }
        } else {
            arrayList2.add(0, new TemperBean(minYNum, 0, ""));
            arrayList2.add(size3 + 1, new TemperBean(minYNum, 1440, ""));
        }
        arrayList2.size();
        int time3 = ((TemperBean) arrayList2.get(0)).getTime();
        if (((TemperBean) arrayList2.get(1)).getTime() > 60 && time3 == 0) {
            arrayList2.add(1, new TemperBean(minYNum, 30, ""));
        }
        int time4 = ((TemperBean) arrayList2.get(arrayList2.size() - 1)).getTime();
        if (((TemperBean) arrayList2.get(arrayList2.size() - 2)).getTime() < 1380 && time4 == 1440) {
            arrayList2.add(arrayList2.size() - 1, new TemperBean(minYNum, 1410, ""));
        }
        for (int i4 = 0; i4 < arrayList2.size(); i4++) {
            Log.i("TaG", "" + ((TemperBean) arrayList2.get(i4)).getTime());
        }
        int size4 = arrayList2.size();
        double[] dArr = new double[size4];
        double[] dArr2 = new double[size4];
        for (int i5 = 0; i5 < size4; i5++) {
            dArr[i5] = ((TemperBean) arrayList2.get(i5)).getTime();
            dArr2[i5] = ((TemperBean) arrayList2.get(i5)).getTemper();
        }
        int time5 = ((TemperBean) arrayList2.get(arrayList2.size() - 1)).getTime() - ((TemperBean) arrayList2.get(0)).getTime();
        double[] dArr3 = new double[time5];
        int i6 = 0;
        int i7 = 0;
        while (i6 < arrayList2.size() - i2) {
            int time6 = ((TemperBean) arrayList2.get(i6)).getTime();
            int i8 = i6 + 1;
            int time7 = ((TemperBean) arrayList2.get(i8)).getTime();
            while (time6 < time7) {
                if (i7 < time5) {
                    try {
                        dArr3[i7] = time6;
                        i7++;
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                time6++;
                i2 = 1;
            }
            i6 = i8;
        }
        double[] dArrPchip = CurveUtils.pchip(dArr, dArr2, size4, dArr3, time5);
        for (int i9 = 0; i9 < dArrPchip.length; i9++) {
            dayValues.add(new BarEntry((float) dArr3[i9], (float) dArrPchip[i9]));
        }
        int i10 = 0;
        while (i10 < arrayList2.size() - 1) {
            int time8 = ((TemperBean) arrayList2.get(i10)).getTime();
            int i11 = i10 + 1;
            int time9 = ((TemperBean) arrayList2.get(i11)).getTime();
            if (time9 - time8 > 60 || ((time3 != 0 && i10 == 0) || i10 == arrayList2.size() - 2)) {
                while (true) {
                    time8++;
                    if (time8 < time9 - 10) {
                        dayValues.remove(time8);
                        dayValues.add(time8, new BarEntry(time8, minYNum));
                    }
                }
            }
            i10 = i11;
        }
        for (int i12 = 0; i12 < size4; i12++) {
        }
        for (int i13 = 0; i13 < dayValues.size(); i13++) {
        }
    }

    private static void setWeekData(BarChart mBarChart, Chart.MarkerLabel mMarkerLabel, List<TemperatureHisListBean> mBarChartBean, String mToDay, Context context) throws NumberFormatException {
        weekValues = new ArrayList<>();
        mTrueWeekTemperBean = new ArrayList();
        ArrayList<String> arrayListPastDay = pastDay(mToDay);
        if (mBarChartBean != null && mBarChartBean.size() != 0) {
            for (int i2 = 0; i2 < mBarChartBean.size(); i2++) {
                float f2 = Float.parseFloat(mBarChartBean.get(i2).getmValue().isEmpty() ? "0" : mBarChartBean.get(i2).getmValue().replaceAll(",", "."));
                if (i2 < arrayListPastDay.size()) {
                    mTrueWeekTemperBean.add(new TemperBean(f2, HourToMinute.timeToMinute(mBarChartBean.get(i2).getTime()), TimeStampUtils.dateForStringDates(TimeStampUtils.stringForDateDay(arrayListPastDay.get(i2)))));
                    if (max < f2) {
                        max = f2;
                    }
                }
            }
        } else {
            mTrueWeekTemperBean.add(new TemperBean(0.0f, 0, ""));
            mTrueWeekTemperBean.add(new TemperBean(0.0f, 1, ""));
            mTrueWeekTemperBean.add(new TemperBean(0.0f, 2, ""));
            mTrueWeekTemperBean.add(new TemperBean(0.0f, 3, ""));
            mTrueWeekTemperBean.add(new TemperBean(0.0f, 4, ""));
            mTrueWeekTemperBean.add(new TemperBean(0.0f, 5, ""));
            mTrueWeekTemperBean.add(new TemperBean(0.0f, 6, ""));
        }
        MyMarkerView myMarkerView = new MyMarkerView(context, R.layout.layout_for_custom_marker_view, mTrueWeekTemperBean, null, null, null);
        myMarkerView.setChartView(mBarChart);
        mBarChart.setMarker(myMarkerView, mTrueWeekTemperBean, null, null, null, mMarkerLabel);
        for (int i3 = 0; i3 < mTrueWeekTemperBean.size(); i3++) {
            weekValues.add(new BarEntry(i3, mTrueWeekTemperBean.get(i3).getTemper()));
        }
    }

    private static void setMonthData(BarChart mBarChart, Chart.MarkerLabel mMarkerLabel, List<TemperatureHisListBean> mBarChartBean, String mToDay, Context context) throws NumberFormatException {
        monthValues = new ArrayList<>();
        mTrueMonthTemperBean = new ArrayList();
        ArrayList<String> arrayListPastThirtyDay = pastThirtyDay(mToDay);
        if (mBarChartBean != null && mBarChartBean.size() != 0) {
            for (int i2 = 0; i2 < mBarChartBean.size(); i2++) {
                if (i2 < arrayListPastThirtyDay.size()) {
                    float f2 = Float.parseFloat(mBarChartBean.get(i2).getmValue().isEmpty() ? "0" : mBarChartBean.get(i2).getmValue().replaceAll(",", "."));
                    mTrueMonthTemperBean.add(new TemperBean(f2, HourToMinute.timeToMinute(mBarChartBean.get(i2).getTime()), TimeStampUtils.dateForStringDates(TimeStampUtils.stringForDateDay(arrayListPastThirtyDay.get(i2)))));
                    if (max < f2) {
                        max = f2;
                    }
                }
            }
        } else {
            mTrueMonthTemperBean.add(new TemperBean(0.0f, 0, ""));
            mTrueMonthTemperBean.add(new TemperBean(0.0f, 1, ""));
            mTrueMonthTemperBean.add(new TemperBean(0.0f, 2, ""));
            mTrueMonthTemperBean.add(new TemperBean(0.0f, 3, ""));
            mTrueMonthTemperBean.add(new TemperBean(0.0f, 4, ""));
            mTrueMonthTemperBean.add(new TemperBean(0.0f, 5, ""));
            mTrueMonthTemperBean.add(new TemperBean(0.0f, 6, ""));
            mTrueMonthTemperBean.add(new TemperBean(0.0f, 7, ""));
            mTrueMonthTemperBean.add(new TemperBean(0.0f, 8, ""));
            mTrueMonthTemperBean.add(new TemperBean(0.0f, 9, ""));
            mTrueMonthTemperBean.add(new TemperBean(0.0f, 10, ""));
            mTrueMonthTemperBean.add(new TemperBean(0.0f, 11, ""));
            mTrueMonthTemperBean.add(new TemperBean(0.0f, 12, ""));
            mTrueMonthTemperBean.add(new TemperBean(0.0f, 13, ""));
            mTrueMonthTemperBean.add(new TemperBean(0.0f, 14, ""));
            mTrueMonthTemperBean.add(new TemperBean(0.0f, 15, ""));
            mTrueMonthTemperBean.add(new TemperBean(0.0f, 16, ""));
            mTrueMonthTemperBean.add(new TemperBean(0.0f, 17, ""));
            mTrueMonthTemperBean.add(new TemperBean(0.0f, 18, ""));
            mTrueMonthTemperBean.add(new TemperBean(0.0f, 19, ""));
            mTrueMonthTemperBean.add(new TemperBean(0.0f, 20, ""));
            mTrueMonthTemperBean.add(new TemperBean(0.0f, 21, ""));
            mTrueMonthTemperBean.add(new TemperBean(0.0f, 22, ""));
            mTrueMonthTemperBean.add(new TemperBean(0.0f, 23, ""));
            mTrueMonthTemperBean.add(new TemperBean(0.0f, 24, ""));
            mTrueMonthTemperBean.add(new TemperBean(0.0f, 25, ""));
            mTrueMonthTemperBean.add(new TemperBean(0.0f, 26, ""));
            mTrueMonthTemperBean.add(new TemperBean(0.0f, 27, ""));
            mTrueMonthTemperBean.add(new TemperBean(0.0f, 28, ""));
            mTrueMonthTemperBean.add(new TemperBean(0.0f, 29, ""));
        }
        MyMarkerView myMarkerView = new MyMarkerView(context, R.layout.layout_for_custom_marker_view, mTrueMonthTemperBean, null, null, null);
        myMarkerView.setChartView(mBarChart);
        mBarChart.setMarker(myMarkerView, mTrueMonthTemperBean, null, null, null, mMarkerLabel);
        for (int i3 = 0; i3 < mTrueMonthTemperBean.size(); i3++) {
            monthValues.add(new BarEntry(i3, mTrueMonthTemperBean.get(i3).getTemper()));
        }
    }

    public static ArrayList<String> pastDay(String time) {
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            Date date = new SimpleDateFormat(AppDateMgr.DF_YYYY_MM_DD, Locale.ENGLISH).parse(time);
            for (int i2 = 6; i2 >= 0; i2--) {
                arrayList.add(getPastDate(i2, date));
            }
        } catch (ParseException e2) {
            e2.printStackTrace();
        }
        return arrayList;
    }

    public static ArrayList<String> pastThirtyDay(String time) {
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            Date date = new SimpleDateFormat(AppDateMgr.DF_YYYY_MM_DD, Locale.ENGLISH).parse(time);
            for (int i2 = 29; i2 >= 0; i2--) {
                arrayList.add(getPastDate(i2, date));
            }
        } catch (ParseException e2) {
            e2.printStackTrace();
        }
        return arrayList;
    }

    public static String getPastDate(int past, Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(5, calendar.get(5) - past);
        return new SimpleDateFormat(AppDateMgr.DF_YYYY_MM_DD, Locale.ENGLISH).format(calendar.getTime());
    }

    public static void initSportHeartRateBarChart(BarChart mBarChart, Context context, List<TemperatureHisListBean> mBarChartBean, String mToDay, NestedScrollView mNestedScrollView, FUNCTION mFUNCTION, float yMaximum, float yMinimum, float xMaximum, float xMinimum, int mXLabelCount, int mYLabelCount, FORMATTER formatter, boolean isResetMax) throws NumberFormatException {
        initSportHeartRateBarChart(mBarChart, context, mBarChartBean, mToDay, mNestedScrollView, mFUNCTION, yMaximum, yMinimum, xMaximum, xMinimum, mXLabelCount, mYLabelCount, formatter, isResetMax, true);
    }

    public static void initSportHeartRateBarChart(BarChart mBarChart, Context context, List<TemperatureHisListBean> mBarChartBean, String mToDay, final NestedScrollView mNestedScrollView, FUNCTION mFUNCTION, float yMaximum, float yMinimum, float xMaximum, float xMinimum, int mXLabelCount, int mYLabelCount, FORMATTER formatter, boolean isResetMax, boolean isCanTouch) throws NumberFormatException {
        max = 10.0f;
        mBarChart.setBackgroundColor(-1);
        mBarChart.getDescription().setEnabled(false);
        mBarChart.setTouchEnabled(true);
        mBarChart.setDrawGridBackground(false);
        mBarChart.setDrawBorders(false);
        mBarChart.getAxisLeft().setDrawGridLines(false);
        mBarChart.getAxisRight().setDrawGridLines(false);
        mBarChart.getXAxis().setDrawGridLines(false);
        mBarChart.setDragEnabled(true);
        mBarChart.setScaleXEnabled(false);
        mBarChart.setScaleYEnabled(false);
        mBarChart.setPinchZoom(false);
        if (!Constant.isHeGe() || isCanTouch) {
            mBarChart.animateY(ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED);
        }
        mBarChart.setOnTouchListener(new View.OnTouchListener() { // from class: com.yucheng.smarthealthpro.customchart.temperature.TempBarChartUtils.2
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
        YAxis axisLeft = mBarChart.getAxisLeft();
        yAxis = axisLeft;
        axisLeft.setDrawAxisLine(false);
        mBarChart.getAxisRight().setEnabled(false);
        yAxis.setAxisMinimum(yMinimum);
        yAxis.setLabelCount(mYLabelCount, true);
        mBarChart.getLegend().setForm(Legend.LegendForm.NONE);
        XAxis xAxis2 = mBarChart.getXAxis();
        xAxis = xAxis2;
        xAxis2.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(true);
        xAxis.setYOffset(DpUtil.dp2px(context, 7.0f));
        if (isResetMax) {
            xAxis.setAxisMaximum(xMaximum);
            xAxis.setAxisMinimum(xMinimum);
        } else {
            xAxis.setAxisMaximum(xMaximum);
        }
        if (formatter == FORMATTER.DAY) {
            xAxis.setLabelCount(mXLabelCount, true);
            xAxis.setValueFormatter(new MySportHeartDayCustomXAxisValueFormatter(true));
            xAxis.setAvoidFirstLastClipping(true);
            mBarChart.setXAxisRenderer(new MyXAxisRenderer(mBarChart.getViewPortHandler(), mBarChart.getXAxis(), mBarChart.getTransformer(YAxis.AxisDependency.LEFT)));
            if (mFUNCTION == FUNCTION.HRV) {
                setSportHeartDayData(mBarChart, Chart.MarkerLabel.HEART_RATE_DAY_CHART, mBarChartBean, mToDay, context);
            } else if (mFUNCTION == FUNCTION.AWRR) {
                setDayData(mBarChart, Chart.MarkerLabel.RESPIRATORY_RATE_DAY_CHART, mBarChartBean, mToDay, context);
            } else if (mFUNCTION == FUNCTION.BO) {
                setDayData(mBarChart, Chart.MarkerLabel.BLOOD_OXYGEN_DAY_CHART, mBarChartBean, mToDay, context);
            } else if (mFUNCTION == FUNCTION.BLOODSUGAR || mFUNCTION == FUNCTION.BLOODFAT) {
                setDayData(mBarChart, Chart.MarkerLabel.BLOOD_SUGAR_DAY_CHART, mBarChartBean, mToDay, context);
            } else if (mFUNCTION == FUNCTION.URICACID) {
                setDayData(mBarChart, Chart.MarkerLabel.URIC_ACID_DAY_CHART, mBarChartBean, mToDay, context);
            } else {
                setDayData(mBarChart, Chart.MarkerLabel.TEMPER_CHART, mBarChartBean, mToDay, context);
            }
            yAxis.setAxisMaximum(maxYNum);
            setBarChart(mBarChart, dayValues, mFUNCTION, context, true);
        } else if (formatter == FORMATTER.WEEK) {
            xAxis.setLabelCount(mXLabelCount, true);
            xAxis.setValueFormatter(new MyWeekCustomXAxisValueFormatter(true));
            xAxis.setAvoidFirstLastClipping(true);
            mBarChart.setXAxisRenderer(new MyXAxisRenderer(mBarChart.getViewPortHandler(), mBarChart.getXAxis(), mBarChart.getTransformer(YAxis.AxisDependency.LEFT)));
            if (mFUNCTION == FUNCTION.HRV) {
                setWeekData(mBarChart, Chart.MarkerLabel.HEART_RATE_WEEK_MONTH_CHART, mBarChartBean, mToDay, context);
            } else if (mFUNCTION == FUNCTION.AWRR) {
                setWeekData(mBarChart, Chart.MarkerLabel.RESPIRATORY_WEEK_MONTH_CHART, mBarChartBean, mToDay, context);
            } else if (mFUNCTION == FUNCTION.BO) {
                setWeekData(mBarChart, Chart.MarkerLabel.BLOOD_OXYGEN_WEEK_MONTH_CHART, mBarChartBean, mToDay, context);
            } else if (mFUNCTION == FUNCTION.BLOODSUGAR || mFUNCTION == FUNCTION.BLOODFAT) {
                setWeekData(mBarChart, Chart.MarkerLabel.BLOOD_SUGAR_WEEK_MONTH_CHART, mBarChartBean, mToDay, context);
            } else if (mFUNCTION == FUNCTION.URICACID) {
                setWeekData(mBarChart, Chart.MarkerLabel.URIC_ACID_WEEK_MONTH_CHART, mBarChartBean, mToDay, context);
            } else {
                setWeekData(mBarChart, Chart.MarkerLabel.TEMPER_WEEK_MONTH_CHART, mBarChartBean, mToDay, context);
            }
            yAxis.setAxisMaximum(maxYNum);
            setBarChart(mBarChart, weekValues, mFUNCTION, context);
        } else {
            xAxis.setLabelCount(mXLabelCount, true);
            xAxis.setValueFormatter(new MyMonthCustomXAxisValueFormatter(true, 30));
            xAxis.setAvoidFirstLastClipping(true);
            mBarChart.setXAxisRenderer(new MyXAxisRenderer(mBarChart.getViewPortHandler(), mBarChart.getXAxis(), mBarChart.getTransformer(YAxis.AxisDependency.LEFT)));
            if (mFUNCTION == FUNCTION.HRV) {
                setMonthData(mBarChart, Chart.MarkerLabel.HEART_RATE_WEEK_MONTH_CHART, mBarChartBean, mToDay, context);
            } else if (mFUNCTION == FUNCTION.AWRR) {
                setMonthData(mBarChart, Chart.MarkerLabel.RESPIRATORY_WEEK_MONTH_CHART, mBarChartBean, mToDay, context);
            } else if (mFUNCTION == FUNCTION.BO) {
                setMonthData(mBarChart, Chart.MarkerLabel.BLOOD_OXYGEN_WEEK_MONTH_CHART, mBarChartBean, mToDay, context);
            } else if (mFUNCTION == FUNCTION.BLOODSUGAR || mFUNCTION == FUNCTION.BLOODFAT) {
                setMonthData(mBarChart, Chart.MarkerLabel.BLOOD_SUGAR_WEEK_MONTH_CHART, mBarChartBean, mToDay, context);
            } else if (mFUNCTION == FUNCTION.URICACID) {
                setMonthData(mBarChart, Chart.MarkerLabel.URIC_ACID_WEEK_MONTH_CHART, mBarChartBean, mToDay, context);
            } else {
                setMonthData(mBarChart, Chart.MarkerLabel.TEMPER_WEEK_MONTH_CHART, mBarChartBean, mToDay, context);
            }
            setBarChart(mBarChart, monthValues, mFUNCTION, context);
        }
        if (max * 1.2f < 10.0f) {
            maxYNum = 10.0f;
        } else {
            maxYNum = (float) new BigDecimal(max * 1.2f).setScale(0, 4).doubleValue();
        }
        yAxis.setAxisMaximum(maxYNum);
    }

    public static void setSportHeartDayData(BarChart mBarChart, Chart.MarkerLabel mMarkerLabel, List<TemperatureHisListBean> mBarChartBean, String mToDay, Context context) throws NumberFormatException {
        dayValues = new ArrayList<>();
        List<TemperBean> arrayList = new ArrayList<>();
        int i2 = 1;
        if (mBarChartBean != null && mBarChartBean.size() != 0) {
            for (int size = mBarChartBean.size() - 1; size >= 0; size--) {
                float f2 = Float.parseFloat(mBarChartBean.get(size).getmValue().replaceAll(",", "."));
                arrayList.add(new TemperBean(f2, mBarChartBean.get(size).getTimeSec(), ""));
                if (max < f2) {
                    max = f2;
                }
            }
            SportHeartMarkerView sportHeartMarkerView = new SportHeartMarkerView(context, R.layout.layout_for_custom_marker_view, arrayList, null, null, null);
            sportHeartMarkerView.setChartView(mBarChart);
            mBarChart.setMarker(sportHeartMarkerView, arrayList, null, null, null, mMarkerLabel);
        } else {
            arrayList.add(new TemperBean(0.0f, 0, ""));
            SportHeartMarkerView sportHeartMarkerView2 = new SportHeartMarkerView(context, R.layout.layout_for_custom_marker_view, arrayList, null, null, null);
            sportHeartMarkerView2.setChartView(mBarChart);
            mBarChart.setMarker(sportHeartMarkerView2, arrayList, null, null, null, mMarkerLabel);
        }
        ArrayList arrayList2 = new ArrayList();
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            arrayList2.add(new TemperBean(arrayList.get(i3).getTemper(), arrayList.get(i3).getTime(), ""));
        }
        arrayList.get(0).getTime();
        arrayList.get(arrayList.size() - 1).getTime();
        arrayList.size();
        arrayList.size();
        arrayList2.size();
        int time = ((TemperBean) arrayList2.get(0)).getTime();
        if (((TemperBean) arrayList2.get(1)).getTime() > 60 && time == 0) {
            arrayList2.add(1, new TemperBean(minYNum, 30, ""));
        }
        ((TemperBean) arrayList2.get(arrayList2.size() - 1)).getTime();
        ((TemperBean) arrayList2.get(arrayList2.size() - 2)).getTime();
        for (int i4 = 0; i4 < arrayList2.size(); i4++) {
            Log.i("TaG", "" + ((TemperBean) arrayList2.get(i4)).getTime());
        }
        int size2 = arrayList2.size();
        double[] dArr = new double[size2];
        double[] dArr2 = new double[size2];
        for (int i5 = 0; i5 < size2; i5++) {
            dArr[i5] = ((TemperBean) arrayList2.get(i5)).getTime();
            dArr2[i5] = ((TemperBean) arrayList2.get(i5)).getTemper();
        }
        int time2 = ((TemperBean) arrayList2.get(arrayList2.size() - 1)).getTime() - ((TemperBean) arrayList2.get(0)).getTime();
        double[] dArr3 = new double[time2];
        int i6 = 0;
        int i7 = 0;
        while (i6 < arrayList2.size() - i2) {
            int time3 = ((TemperBean) arrayList2.get(i6)).getTime();
            int i8 = i6 + 1;
            int time4 = ((TemperBean) arrayList2.get(i8)).getTime();
            while (time3 < time4) {
                if (i7 < time2) {
                    try {
                        dArr3[i7] = time3;
                        i7++;
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                time3++;
                i2 = 1;
            }
            i6 = i8;
        }
        double[] dArrPchip = CurveUtils.pchip(dArr, dArr2, size2, dArr3, time2);
        for (int i9 = 0; i9 < dArrPchip.length; i9++) {
            dayValues.add(new BarEntry((float) dArr3[i9], (float) dArrPchip[i9]));
        }
        int i10 = 0;
        while (i10 < arrayList2.size() - 1) {
            int time5 = ((TemperBean) arrayList2.get(i10)).getTime();
            int i11 = i10 + 1;
            int time6 = ((TemperBean) arrayList2.get(i11)).getTime();
            if (time6 - time5 > 60 || ((time != 0 && i10 == 0) || i10 == arrayList2.size() - 2)) {
                while (true) {
                    time5++;
                    if (time5 < time6 - 10) {
                        dayValues.remove(time5);
                        dayValues.add(time5, new BarEntry(time5, minYNum));
                    }
                }
            }
            i10 = i11;
        }
        for (int i12 = 0; i12 < size2; i12++) {
        }
        for (int i13 = 0; i13 < dayValues.size(); i13++) {
        }
    }
}
