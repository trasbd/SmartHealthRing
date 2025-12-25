package com.yucheng.smarthealthpro.customchart.temperature;

import android.content.Context;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.core.widget.NestedScrollView;
import com.alibaba.fastjson2.internal.asm.Opcodes;
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
import com.yucheng.smarthealthpro.customchart.highlight.Highlight;
import com.yucheng.smarthealthpro.customchart.interfaces.dataprovider.LineDataProvider;
import com.yucheng.smarthealthpro.customchart.interfaces.datasets.ILineDataSet;
import com.yucheng.smarthealthpro.customchart.listener.OnChartValueSelectedListener;
import com.yucheng.smarthealthpro.customchart.renderer.MyXAxisRenderer;
import com.yucheng.smarthealthpro.customchart.utils.CurveUtils;
import com.yucheng.smarthealthpro.customchart.utils.HourToMinute;
import com.yucheng.smarthealthpro.customchart.utils.Utils;
import com.yucheng.smarthealthpro.home.activity.temperature.bean.TemperatureHisListBean;
import com.yucheng.smarthealthpro.me.setting.contacts.utils.DpUtil;
import com.yucheng.smarthealthpro.utils.AppDateMgr;
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
public class TempLineChartUtils {
    private static ArrayList<Entry> dayValues = null;
    private static LineDataSet mBrokenLine = null;
    private static TempLineChartUtils mTempLineChartUtils = null;
    private static List<TemperBean> mTrueDayTemperBean = null;
    private static List<TemperBean> mTrueMonthTemperBean = null;
    private static List<TemperBean> mTrueWeekTemperBean = null;
    private static float max = 10.0f;
    private static float maxYNum = 220.0f;
    private static int minYNum;
    private static ArrayList<Entry> monthValues;
    private static ArrayList<Entry> weekValues;
    private static XAxis xAxis;
    private static YAxis yAxis;

    public enum FORMATTER {
        DAY,
        WEEK,
        MONTH
    }

    public enum FUNCTION {
        HR,
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

    private TempLineChartUtils() {
    }

    public static synchronized TempLineChartUtils getInstance() {
        if (mTempLineChartUtils == null) {
            mTempLineChartUtils = new TempLineChartUtils();
        }
        return mTempLineChartUtils;
    }

    public static void initLineChart(LineChart mLineChart, Context context, List<TemperatureHisListBean> mLineChartBean, String mToDay, NestedScrollView mNestedScrollView, FUNCTION mFUNCTION, float yMaximum, float yMinimum, float xMaximum, int mXLabelCount, int mYLabelCount, FORMATTER formatter) throws NumberFormatException {
        initLineChart(mLineChart, context, mLineChartBean, mToDay, mNestedScrollView, mFUNCTION, yMaximum, yMinimum, xMaximum, 0.0f, mXLabelCount, mYLabelCount, formatter, false, true);
    }

    public static void initLineChart(LineChart mLineChart, Context context, List<TemperatureHisListBean> mLineChartBean, String mToDay, NestedScrollView mNestedScrollView, FUNCTION mFUNCTION, float yMaximum, float yMinimum, float xMaximum, int mXLabelCount, int mYLabelCount, FORMATTER formatter, boolean isCanTouch) throws NumberFormatException {
        initLineChart(mLineChart, context, mLineChartBean, mToDay, mNestedScrollView, mFUNCTION, yMaximum, yMinimum, xMaximum, 0.0f, mXLabelCount, mYLabelCount, formatter, false, isCanTouch);
    }

    public static void initLineChart(LineChart mLineChart, Context context, List<TemperatureHisListBean> mLineChartBean, String mToDay, final NestedScrollView mNestedScrollView, FUNCTION mFUNCTION, float yMaximum, float yMinimum, float xMaximum, float xMinimum, int mXLabelCount, int mYLabelCount, FORMATTER formatter, boolean isResetMax, boolean isCanTouch) throws NumberFormatException {
        max = 10.0f;
        mLineChart.setBackgroundColor(-1);
        mLineChart.getDescription().setEnabled(false);
        mLineChart.setTouchEnabled(isCanTouch);
        mLineChart.setDrawGridBackground(false);
        mLineChart.setDrawBorders(false);
        mLineChart.getAxisLeft().setDrawGridLines(false);
        mLineChart.getAxisRight().setDrawGridLines(false);
        mLineChart.getXAxis().setDrawGridLines(false);
        mLineChart.setDragEnabled(isCanTouch);
        mLineChart.setScaleXEnabled(false);
        mLineChart.setScaleYEnabled(false);
        mLineChart.setPinchZoom(false);
        mLineChart.animateY(ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED);
        mLineChart.setOnTouchListener(new View.OnTouchListener() { // from class: com.yucheng.smarthealthpro.customchart.temperature.TempLineChartUtils.1
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
        YAxis axisLeft = mLineChart.getAxisLeft();
        yAxis = axisLeft;
        axisLeft.setDrawAxisLine(false);
        mLineChart.getAxisRight().setEnabled(false);
        yAxis.setAxisMinimum(yMinimum);
        yAxis.setLabelCount(mYLabelCount, true);
        mLineChart.getLegend().setForm(Legend.LegendForm.NONE);
        XAxis xAxis2 = mLineChart.getXAxis();
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
            xAxis.setAxisMinimum(0.0f);
        }
        if (formatter == FORMATTER.DAY) {
            xAxis.setLabelCount(mXLabelCount, true);
            xAxis.setValueFormatter(new MyTempDayCustomXAxisValueFormatter(true));
            xAxis.setAvoidFirstLastClipping(true);
            mLineChart.setXAxisRenderer(new MyXAxisRenderer(mLineChart.getViewPortHandler(), mLineChart.getXAxis(), mLineChart.getTransformer(YAxis.AxisDependency.LEFT)));
            if (mFUNCTION == FUNCTION.HR) {
                setDayData(mLineChart, Chart.MarkerLabel.HEART_RATE_DAY_CHART, mLineChartBean, mToDay, context);
            } else if (mFUNCTION == FUNCTION.HRV) {
                setDayData(mLineChart, Chart.MarkerLabel.HRV_DAY_CHART, mLineChartBean, mToDay, context);
            } else if (mFUNCTION == FUNCTION.AWRR) {
                setDayData(mLineChart, Chart.MarkerLabel.RESPIRATORY_RATE_DAY_CHART, mLineChartBean, mToDay, context);
            } else if (mFUNCTION == FUNCTION.BO) {
                setDayData(mLineChart, Chart.MarkerLabel.BLOOD_OXYGEN_DAY_CHART, mLineChartBean, mToDay, context);
            } else if (mFUNCTION == FUNCTION.BLOODSUGAR || mFUNCTION == FUNCTION.BLOODFAT) {
                setDayData(mLineChart, Chart.MarkerLabel.BLOOD_SUGAR_DAY_CHART, mLineChartBean, mToDay, context);
            } else if (mFUNCTION == FUNCTION.URICACID) {
                setDayData(mLineChart, Chart.MarkerLabel.URIC_ACID_DAY_CHART, mLineChartBean, mToDay, context);
            } else if (mFUNCTION == FUNCTION.KETONE) {
                setDayData(mLineChart, Chart.MarkerLabel.BLOOD_KETONE_CHART, mLineChartBean, mToDay, context);
            } else {
                setDayData(mLineChart, Chart.MarkerLabel.TEMPER_CHART, mLineChartBean, mToDay, context);
            }
            yAxis.setAxisMaximum(maxYNum);
            setLineChart(mLineChart, dayValues, mFUNCTION, context, true);
        } else if (formatter == FORMATTER.WEEK) {
            xAxis.setLabelCount(mXLabelCount, true);
            xAxis.setValueFormatter(new MyWeekCustomXAxisValueFormatter(true));
            xAxis.setAvoidFirstLastClipping(true);
            mLineChart.setXAxisRenderer(new MyXAxisRenderer(mLineChart.getViewPortHandler(), mLineChart.getXAxis(), mLineChart.getTransformer(YAxis.AxisDependency.LEFT)));
            if (mFUNCTION == FUNCTION.HRV) {
                setWeekData(mLineChart, Chart.MarkerLabel.HRV_WEEK_MONTH_CHART, mLineChartBean, mToDay, context);
            } else if (mFUNCTION == FUNCTION.HR) {
                setWeekData(mLineChart, Chart.MarkerLabel.HEART_RATE_WEEK_MONTH_CHART, mLineChartBean, mToDay, context);
            } else if (mFUNCTION == FUNCTION.AWRR) {
                setWeekData(mLineChart, Chart.MarkerLabel.RESPIRATORY_WEEK_MONTH_CHART, mLineChartBean, mToDay, context);
            } else if (mFUNCTION == FUNCTION.BO) {
                setWeekData(mLineChart, Chart.MarkerLabel.BLOOD_OXYGEN_WEEK_MONTH_CHART, mLineChartBean, mToDay, context);
            } else if (mFUNCTION == FUNCTION.BLOODSUGAR || mFUNCTION == FUNCTION.BLOODFAT) {
                setWeekData(mLineChart, Chart.MarkerLabel.BLOOD_SUGAR_WEEK_MONTH_CHART, mLineChartBean, mToDay, context);
            } else if (mFUNCTION == FUNCTION.URICACID) {
                setWeekData(mLineChart, Chart.MarkerLabel.URIC_ACID_WEEK_MONTH_CHART, mLineChartBean, mToDay, context);
            } else if (mFUNCTION == FUNCTION.KETONE) {
                setWeekData(mLineChart, Chart.MarkerLabel.BLOOD_KETONE_WEEK_MONTH_CHART, mLineChartBean, mToDay, context);
            } else if (mFUNCTION == FUNCTION.PRESSURE) {
                setWeekData(mLineChart, Chart.MarkerLabel.PRESSURE_WEEK_MONTH_CHART, mLineChartBean, mToDay, context);
            } else {
                setWeekData(mLineChart, Chart.MarkerLabel.TEMPER_WEEK_MONTH_CHART, mLineChartBean, mToDay, context);
            }
            yAxis.setAxisMaximum(maxYNum);
            setLineChart(mLineChart, weekValues, mFUNCTION, context);
        } else {
            xAxis.setLabelCount(mXLabelCount, true);
            xAxis.setValueFormatter(new MyMonthCustomXAxisValueFormatter(true, 30));
            xAxis.setAvoidFirstLastClipping(true);
            mLineChart.setXAxisRenderer(new MyXAxisRenderer(mLineChart.getViewPortHandler(), mLineChart.getXAxis(), mLineChart.getTransformer(YAxis.AxisDependency.LEFT)));
            if (mFUNCTION == FUNCTION.HRV) {
                setMonthData(mLineChart, Chart.MarkerLabel.HRV_WEEK_MONTH_CHART, mLineChartBean, mToDay, context);
            } else if (mFUNCTION == FUNCTION.HR) {
                setMonthData(mLineChart, Chart.MarkerLabel.HEART_RATE_WEEK_MONTH_CHART, mLineChartBean, mToDay, context);
            } else if (mFUNCTION == FUNCTION.AWRR) {
                setMonthData(mLineChart, Chart.MarkerLabel.RESPIRATORY_WEEK_MONTH_CHART, mLineChartBean, mToDay, context);
            } else if (mFUNCTION == FUNCTION.BO) {
                setMonthData(mLineChart, Chart.MarkerLabel.BLOOD_OXYGEN_WEEK_MONTH_CHART, mLineChartBean, mToDay, context);
            } else if (mFUNCTION == FUNCTION.BLOODSUGAR || mFUNCTION == FUNCTION.BLOODFAT) {
                setMonthData(mLineChart, Chart.MarkerLabel.BLOOD_SUGAR_WEEK_MONTH_CHART, mLineChartBean, mToDay, context);
            } else if (mFUNCTION == FUNCTION.URICACID) {
                setMonthData(mLineChart, Chart.MarkerLabel.URIC_ACID_WEEK_MONTH_CHART, mLineChartBean, mToDay, context);
            } else if (mFUNCTION == FUNCTION.KETONE) {
                setMonthData(mLineChart, Chart.MarkerLabel.BLOOD_KETONE_WEEK_MONTH_CHART, mLineChartBean, mToDay, context);
            } else if (mFUNCTION == FUNCTION.PRESSURE) {
                setMonthData(mLineChart, Chart.MarkerLabel.PRESSURE_WEEK_MONTH_CHART, mLineChartBean, mToDay, context);
            } else {
                setMonthData(mLineChart, Chart.MarkerLabel.TEMPER_WEEK_MONTH_CHART, mLineChartBean, mToDay, context);
            }
            setLineChart(mLineChart, monthValues, mFUNCTION, context);
        }
        if (max * 1.2f < 10.0f) {
            maxYNum = 10.0f;
        } else {
            maxYNum = (float) new BigDecimal(max * 1.2f).setScale(0, 4).doubleValue();
        }
        if (mFUNCTION == FUNCTION.KETONE) {
            maxYNum = yMaximum;
        }
        yAxis.setAxisMaximum(maxYNum);
    }

    public static void setLineChart(LineChart mLineChart, List<Entry> values, FUNCTION mFUNCTION, Context context) {
        setLineChart(mLineChart, values, mFUNCTION, context, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void setLineChart(final LineChart mLineChart, List<Entry> values, FUNCTION mFUNCTION, Context context, boolean isRemoveYAxis) {
        if (mLineChart.getData() != null && ((LineData) mLineChart.getData()).getDataSetCount() > 0) {
            LineDataSet lineDataSet = (LineDataSet) ((LineData) mLineChart.getData()).getDataSetByIndex(0);
            mBrokenLine = lineDataSet;
            lineDataSet.setValues(values);
            mBrokenLine.notifyDataSetChanged();
            ((LineData) mLineChart.getData()).notifyDataChanged();
            mLineChart.notifyDataSetChanged();
        } else {
            LineDataSet lineDataSet2 = new LineDataSet(values, "");
            mBrokenLine = lineDataSet2;
            lineDataSet2.setDrawIcons(false);
            mBrokenLine.setDrawCircles(false);
            mBrokenLine.setColor(-1);
            mBrokenLine.setCircleColor(-1);
            mBrokenLine.setDrawCircles(false);
            mBrokenLine.setLineWidth(0.0f);
            mBrokenLine.setCircleRadius(3.0f);
            mBrokenLine.setDrawCircleHole(false);
            mBrokenLine.setFormLineWidth(1.0f);
            mBrokenLine.setFormLineDashEffect(new DashPathEffect(new float[]{10.0f, 5.0f}, 0.0f));
            mBrokenLine.setFormSize(15.0f);
            mBrokenLine.setValueTextSize(9.0f);
            mBrokenLine.setDrawHorizontalHighlightIndicator(false);
            mBrokenLine.setDrawVerticalHighlightIndicator(true);
            mBrokenLine.setDrawFilled(true);
            mBrokenLine.setFillFormatter(new IFillFormatter() { // from class: com.yucheng.smarthealthpro.customchart.temperature.TempLineChartUtils.2
                @Override // com.yucheng.smarthealthpro.customchart.formatter.IFillFormatter
                public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                    return mLineChart.getAxisLeft().getAxisMinimum();
                }
            });
            if (Utils.getSDKInt() >= 18) {
                if (mFUNCTION == FUNCTION.HR) {
                    mBrokenLine.setFillDrawable(ContextCompat.getDrawable(context, R.drawable.fade_red_hrv));
                } else if (mFUNCTION == FUNCTION.HRV) {
                    mBrokenLine.setFillDrawable(ContextCompat.getDrawable(context, R.drawable.fade_red_hrv));
                } else if (mFUNCTION == FUNCTION.AWRR) {
                    mBrokenLine.setFillDrawable(ContextCompat.getDrawable(context, R.drawable.fade_green_awrr));
                } else if (mFUNCTION == FUNCTION.BO) {
                    mBrokenLine.setFillDrawable(ContextCompat.getDrawable(context, R.drawable.fade_orange_bo));
                } else if (mFUNCTION == FUNCTION.BLOODSUGAR) {
                    mBrokenLine.setFillDrawable(ContextCompat.getDrawable(context, R.drawable.fade_blood_sugar));
                } else if (mFUNCTION == FUNCTION.BLOODFAT) {
                    mBrokenLine.setFillDrawable(ContextCompat.getDrawable(context, R.drawable.fade_blood_fat));
                } else if (mFUNCTION == FUNCTION.URICACID) {
                    mBrokenLine.setFillDrawable(ContextCompat.getDrawable(context, R.drawable.fade_uric_acid));
                } else if (mFUNCTION == FUNCTION.KETONE) {
                    mBrokenLine.setFillDrawable(ContextCompat.getDrawable(context, R.drawable.fade_ketone));
                } else {
                    mBrokenLine.setFillDrawable(ContextCompat.getDrawable(context, R.drawable.fade_blue_temp));
                }
            } else {
                mBrokenLine.setFillColor(ViewCompat.MEASURED_STATE_MASK);
            }
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(mBrokenLine);
        mLineChart.setData(new LineData(arrayList));
        Iterator it2 = ((LineData) mLineChart.getData()).getDataSets().iterator();
        while (it2.hasNext()) {
            LineDataSet lineDataSet3 = (LineDataSet) ((ILineDataSet) it2.next());
            lineDataSet3.setDrawValues(false);
            lineDataSet3.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
        }
        mLineChart.invalidate();
    }

    public static void setDayData(LineChart mLineChart, Chart.MarkerLabel mMarkerLabel, List<TemperatureHisListBean> mLineChartBean, String mToDay, Context context) throws NumberFormatException {
        dayValues = new ArrayList<>();
        List<TemperBean> arrayList = new ArrayList<>();
        int i2 = 1;
        if (mLineChartBean != null && mLineChartBean.size() != 0) {
            for (int size = mLineChartBean.size() - 1; size >= 0; size--) {
                float f2 = Float.parseFloat(mLineChartBean.get(size).getmValue().replaceAll(",", "."));
                arrayList.add(new TemperBean(f2, HourToMinute.timeToMinute(mLineChartBean.get(size).getTime()), ""));
                if (max < f2) {
                    max = f2;
                }
            }
            MyMarkerView myMarkerView = new MyMarkerView(context, R.layout.layout_for_custom_marker_view, arrayList, null, null, null);
            myMarkerView.setChartView(mLineChart);
            mLineChart.setMarker(myMarkerView, arrayList, null, null, null, mMarkerLabel);
        } else {
            arrayList.add(new TemperBean(0.0f, 0, ""));
            MyMarkerView myMarkerView2 = new MyMarkerView(context, R.layout.layout_for_custom_marker_view, arrayList, null, null, null);
            myMarkerView2.setChartView(mLineChart);
            mLineChart.setMarker(myMarkerView2, arrayList, null, null, null, mMarkerLabel);
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
            dayValues.add(new Entry((float) dArr3[i9], (float) dArrPchip[i9]));
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
                        dayValues.add(time8, new Entry(time8, minYNum));
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

    public static void setDayData(BarChart barChart, Chart.MarkerLabel mMarkerLabel, List<TemperatureHisListBean> mLineChartBean, String mToDay, Context context) throws NumberFormatException {
        dayValues = new ArrayList<>();
        List<TemperBean> arrayList = new ArrayList<>();
        int i2 = 1;
        if (mLineChartBean != null && mLineChartBean.size() != 0) {
            for (int size = mLineChartBean.size() - 1; size >= 0; size--) {
                float f2 = Float.parseFloat(mLineChartBean.get(size).getmValue().replaceAll(",", "."));
                arrayList.add(new TemperBean(f2, HourToMinute.timeToMinute(mLineChartBean.get(size).getTime()), ""));
                if (max < f2) {
                    max = f2;
                }
            }
            MyMarkerView myMarkerView = new MyMarkerView(context, R.layout.layout_for_custom_marker_view, arrayList, null, null, null);
            myMarkerView.setChartView(barChart);
            barChart.setMarker(myMarkerView, arrayList, null, null, null, mMarkerLabel);
        } else {
            arrayList.add(new TemperBean(0.0f, 0, ""));
            MyMarkerView myMarkerView2 = new MyMarkerView(context, R.layout.layout_for_custom_marker_view, arrayList, null, null, null);
            myMarkerView2.setChartView(barChart);
            barChart.setMarker(myMarkerView2, arrayList, null, null, null, mMarkerLabel);
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
            dayValues.add(new Entry((float) dArr3[i9], (float) dArrPchip[i9]));
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
                        dayValues.add(time8, new Entry(time8, minYNum));
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

    private static void setWeekData(LineChart mLineChart, Chart.MarkerLabel mMarkerLabel, List<TemperatureHisListBean> mLineChartBean, String mToDay, Context context) throws NumberFormatException {
        weekValues = new ArrayList<>();
        mTrueWeekTemperBean = new ArrayList();
        ArrayList<String> arrayListPastDay = pastDay(mToDay);
        if (mLineChartBean != null && mLineChartBean.size() != 0) {
            for (int i2 = 0; i2 < mLineChartBean.size(); i2++) {
                float f2 = Float.parseFloat(mLineChartBean.get(i2).getmValue().isEmpty() ? "0" : mLineChartBean.get(i2).getmValue().replaceAll(",", "."));
                if (i2 < arrayListPastDay.size()) {
                    mTrueWeekTemperBean.add(new TemperBean(f2, HourToMinute.timeToMinute(mLineChartBean.get(i2).getTime()), TimeStampUtils.dateForStringDates(TimeStampUtils.stringForDateDay(arrayListPastDay.get(i2)))));
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
        myMarkerView.setChartView(mLineChart);
        mLineChart.setMarker(myMarkerView, mTrueWeekTemperBean, null, null, null, mMarkerLabel);
        for (int i3 = 0; i3 < mTrueWeekTemperBean.size(); i3++) {
            weekValues.add(new Entry(i3, mTrueWeekTemperBean.get(i3).getTemper()));
        }
    }

    private static void setMonthData(LineChart mLineChart, Chart.MarkerLabel mMarkerLabel, List<TemperatureHisListBean> mLineChartBean, String mToDay, Context context) throws NumberFormatException {
        monthValues = new ArrayList<>();
        mTrueMonthTemperBean = new ArrayList();
        ArrayList<String> arrayListPastThirtyDay = pastThirtyDay(mToDay);
        if (mLineChartBean != null && mLineChartBean.size() != 0) {
            for (int i2 = 0; i2 < mLineChartBean.size(); i2++) {
                if (i2 < arrayListPastThirtyDay.size()) {
                    float f2 = Float.parseFloat(mLineChartBean.get(i2).getmValue().isEmpty() ? "0" : mLineChartBean.get(i2).getmValue().replaceAll(",", "."));
                    mTrueMonthTemperBean.add(new TemperBean(f2, HourToMinute.timeToMinute(mLineChartBean.get(i2).getTime()), TimeStampUtils.dateForStringDates(TimeStampUtils.stringForDateDay(arrayListPastThirtyDay.get(i2)))));
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
        myMarkerView.setChartView(mLineChart);
        mLineChart.setMarker(myMarkerView, mTrueMonthTemperBean, null, null, null, mMarkerLabel);
        for (int i3 = 0; i3 < mTrueMonthTemperBean.size(); i3++) {
            monthValues.add(new Entry(i3, mTrueMonthTemperBean.get(i3).getTemper()));
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
            Date date = new SimpleDateFormat(AppDateMgr.DF_YYYY_MM_DD).parse(time);
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
        return new SimpleDateFormat(AppDateMgr.DF_YYYY_MM_DD).format(calendar.getTime());
    }

    public static void initSportHeartRateLineChart(LineChart mLineChart, Context context, List<TemperatureHisListBean> mLineChartBean, String mToDay, NestedScrollView mNestedScrollView, FUNCTION mFUNCTION, float yMaximum, float yMinimum, float xMaximum, float xMinimum, int mXLabelCount, int mYLabelCount, FORMATTER formatter, boolean isResetMax) throws NumberFormatException {
        initSportHeartRateLineChart(mLineChart, context, mLineChartBean, mToDay, mNestedScrollView, mFUNCTION, yMaximum, yMinimum, xMaximum, xMinimum, mXLabelCount, mYLabelCount, formatter, isResetMax, true);
    }

    public static void initSportHeartRateLineChart(LineChart mLineChart, Context context, List<TemperatureHisListBean> mLineChartBean, String mToDay, final NestedScrollView mNestedScrollView, FUNCTION mFUNCTION, float yMaximum, float yMinimum, float xMaximum, float xMinimum, int mXLabelCount, int mYLabelCount, FORMATTER formatter, boolean isResetMax, boolean isCanTouch) throws NumberFormatException {
        max = 10.0f;
        mLineChart.setBackgroundColor(-1);
        mLineChart.getDescription().setEnabled(false);
        mLineChart.setTouchEnabled(true);
        mLineChart.setDrawGridBackground(false);
        mLineChart.setDrawBorders(false);
        mLineChart.getAxisLeft().setDrawGridLines(false);
        mLineChart.getAxisRight().setDrawGridLines(false);
        mLineChart.getXAxis().setDrawGridLines(false);
        mLineChart.setDragEnabled(true);
        mLineChart.setScaleXEnabled(false);
        mLineChart.setScaleYEnabled(false);
        mLineChart.setPinchZoom(false);
        mLineChart.animateY(ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED);
        mLineChart.setOnTouchListener(new View.OnTouchListener() { // from class: com.yucheng.smarthealthpro.customchart.temperature.TempLineChartUtils.3
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
        YAxis axisLeft = mLineChart.getAxisLeft();
        yAxis = axisLeft;
        axisLeft.setDrawAxisLine(false);
        mLineChart.getAxisRight().setEnabled(false);
        yAxis.setAxisMinimum(yMinimum);
        yAxis.setLabelCount(mYLabelCount, true);
        mLineChart.getLegend().setForm(Legend.LegendForm.NONE);
        XAxis xAxis2 = mLineChart.getXAxis();
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
            xAxis.setAxisMinimum(0.0f);
        }
        if (formatter == FORMATTER.DAY) {
            xAxis.setLabelCount(mXLabelCount, true);
            xAxis.setValueFormatter(new MySportHeartDayCustomXAxisValueFormatter(true));
            xAxis.setAvoidFirstLastClipping(true);
            mLineChart.setXAxisRenderer(new MyXAxisRenderer(mLineChart.getViewPortHandler(), mLineChart.getXAxis(), mLineChart.getTransformer(YAxis.AxisDependency.LEFT)));
            if (mFUNCTION == FUNCTION.HR) {
                setSportHeartDayData(mLineChart, Chart.MarkerLabel.HEART_RATE_DAY_CHART, mLineChartBean, mToDay, context);
            } else if (mFUNCTION == FUNCTION.HRV) {
                setSportHeartDayData(mLineChart, Chart.MarkerLabel.HRV_DAY_CHART, mLineChartBean, mToDay, context);
            } else if (mFUNCTION == FUNCTION.AWRR) {
                setDayData(mLineChart, Chart.MarkerLabel.RESPIRATORY_RATE_DAY_CHART, mLineChartBean, mToDay, context);
            } else if (mFUNCTION == FUNCTION.BO) {
                setDayData(mLineChart, Chart.MarkerLabel.BLOOD_OXYGEN_DAY_CHART, mLineChartBean, mToDay, context);
            } else if (mFUNCTION == FUNCTION.BLOODSUGAR || mFUNCTION == FUNCTION.BLOODFAT) {
                setDayData(mLineChart, Chart.MarkerLabel.BLOOD_SUGAR_DAY_CHART, mLineChartBean, mToDay, context);
            } else if (mFUNCTION == FUNCTION.URICACID) {
                setDayData(mLineChart, Chart.MarkerLabel.URIC_ACID_DAY_CHART, mLineChartBean, mToDay, context);
            } else {
                setDayData(mLineChart, Chart.MarkerLabel.TEMPER_CHART, mLineChartBean, mToDay, context);
            }
            yAxis.setAxisMaximum(maxYNum);
            setLineChart(mLineChart, dayValues, mFUNCTION, context, true);
        } else if (formatter == FORMATTER.WEEK) {
            xAxis.setLabelCount(mXLabelCount, true);
            xAxis.setValueFormatter(new MyWeekCustomXAxisValueFormatter(true));
            xAxis.setAvoidFirstLastClipping(true);
            mLineChart.setXAxisRenderer(new MyXAxisRenderer(mLineChart.getViewPortHandler(), mLineChart.getXAxis(), mLineChart.getTransformer(YAxis.AxisDependency.LEFT)));
            if (mFUNCTION == FUNCTION.HR) {
                setSportHeartDayData(mLineChart, Chart.MarkerLabel.HEART_RATE_WEEK_MONTH_CHART, mLineChartBean, mToDay, context);
            } else if (mFUNCTION == FUNCTION.HRV) {
                setWeekData(mLineChart, Chart.MarkerLabel.HRV_WEEK_MONTH_CHART, mLineChartBean, mToDay, context);
            } else if (mFUNCTION == FUNCTION.AWRR) {
                setWeekData(mLineChart, Chart.MarkerLabel.RESPIRATORY_WEEK_MONTH_CHART, mLineChartBean, mToDay, context);
            } else if (mFUNCTION == FUNCTION.BO) {
                setWeekData(mLineChart, Chart.MarkerLabel.BLOOD_OXYGEN_WEEK_MONTH_CHART, mLineChartBean, mToDay, context);
            } else if (mFUNCTION == FUNCTION.BLOODSUGAR || mFUNCTION == FUNCTION.BLOODFAT) {
                setWeekData(mLineChart, Chart.MarkerLabel.BLOOD_SUGAR_WEEK_MONTH_CHART, mLineChartBean, mToDay, context);
            } else if (mFUNCTION == FUNCTION.URICACID) {
                setWeekData(mLineChart, Chart.MarkerLabel.URIC_ACID_WEEK_MONTH_CHART, mLineChartBean, mToDay, context);
            } else {
                setWeekData(mLineChart, Chart.MarkerLabel.TEMPER_WEEK_MONTH_CHART, mLineChartBean, mToDay, context);
            }
            yAxis.setAxisMaximum(maxYNum);
            setLineChart(mLineChart, weekValues, mFUNCTION, context);
        } else {
            xAxis.setLabelCount(mXLabelCount, true);
            xAxis.setValueFormatter(new MyMonthCustomXAxisValueFormatter(true, 30));
            xAxis.setAvoidFirstLastClipping(true);
            mLineChart.setXAxisRenderer(new MyXAxisRenderer(mLineChart.getViewPortHandler(), mLineChart.getXAxis(), mLineChart.getTransformer(YAxis.AxisDependency.LEFT)));
            if (mFUNCTION == FUNCTION.HR) {
                setSportHeartDayData(mLineChart, Chart.MarkerLabel.HEART_RATE_WEEK_MONTH_CHART, mLineChartBean, mToDay, context);
            } else if (mFUNCTION == FUNCTION.HRV) {
                setMonthData(mLineChart, Chart.MarkerLabel.HRV_WEEK_MONTH_CHART, mLineChartBean, mToDay, context);
            } else if (mFUNCTION == FUNCTION.AWRR) {
                setMonthData(mLineChart, Chart.MarkerLabel.RESPIRATORY_WEEK_MONTH_CHART, mLineChartBean, mToDay, context);
            } else if (mFUNCTION == FUNCTION.BO) {
                setMonthData(mLineChart, Chart.MarkerLabel.BLOOD_OXYGEN_WEEK_MONTH_CHART, mLineChartBean, mToDay, context);
            } else if (mFUNCTION == FUNCTION.BLOODSUGAR || mFUNCTION == FUNCTION.BLOODFAT) {
                setMonthData(mLineChart, Chart.MarkerLabel.BLOOD_SUGAR_WEEK_MONTH_CHART, mLineChartBean, mToDay, context);
            } else if (mFUNCTION == FUNCTION.URICACID) {
                setMonthData(mLineChart, Chart.MarkerLabel.URIC_ACID_WEEK_MONTH_CHART, mLineChartBean, mToDay, context);
            } else {
                setMonthData(mLineChart, Chart.MarkerLabel.TEMPER_WEEK_MONTH_CHART, mLineChartBean, mToDay, context);
            }
            setLineChart(mLineChart, monthValues, mFUNCTION, context);
        }
        if (max * 1.2f < 10.0f) {
            maxYNum = 10.0f;
        } else {
            maxYNum = (float) new BigDecimal(max * 1.2f).setScale(0, 4).doubleValue();
        }
        yAxis.setAxisMaximum(maxYNum);
    }

    public static void setSportHeartDayData(LineChart mLineChart, Chart.MarkerLabel mMarkerLabel, List<TemperatureHisListBean> mLineChartBean, String mToDay, Context context) throws NumberFormatException {
        dayValues = new ArrayList<>();
        List<TemperBean> arrayList = new ArrayList<>();
        int i2 = 1;
        if (mLineChartBean != null && mLineChartBean.size() != 0) {
            for (int size = mLineChartBean.size() - 1; size >= 0; size--) {
                float f2 = Float.parseFloat(mLineChartBean.get(size).getmValue().replaceAll(",", "."));
                arrayList.add(new TemperBean(f2, mLineChartBean.get(size).getTimeSec(), ""));
                if (max < f2) {
                    max = f2;
                }
            }
            SportHeartMarkerView sportHeartMarkerView = new SportHeartMarkerView(context, R.layout.layout_for_custom_marker_view, arrayList, null, null, null);
            sportHeartMarkerView.setChartView(mLineChart);
            mLineChart.setMarker(sportHeartMarkerView, arrayList, null, null, null, mMarkerLabel);
        } else {
            arrayList.add(new TemperBean(0.0f, 0, ""));
            SportHeartMarkerView sportHeartMarkerView2 = new SportHeartMarkerView(context, R.layout.layout_for_custom_marker_view, arrayList, null, null, null);
            sportHeartMarkerView2.setChartView(mLineChart);
            mLineChart.setMarker(sportHeartMarkerView2, arrayList, null, null, null, mMarkerLabel);
        }
        ArrayList arrayList2 = new ArrayList();
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            arrayList2.add(new TemperBean(arrayList.get(i3).getTemper(), arrayList.get(i3).getTime(), ""));
        }
        arrayList.get(0).getTime();
        arrayList.get(arrayList.size() - 1).getTime();
        arrayList.size();
        arrayList.size();
        int size2 = arrayList2.size();
        int time = ((TemperBean) arrayList2.get(0)).getTime();
        if ((size2 > 1 ? ((TemperBean) arrayList2.get(1)).getTime() : 0) > 60 && time == 0) {
            arrayList2.add(1, new TemperBean(minYNum, 30, ""));
        }
        for (int i4 = 0; i4 < arrayList2.size(); i4++) {
            Log.i("TaG", "" + ((TemperBean) arrayList2.get(i4)).getTime());
        }
        int size3 = arrayList2.size();
        double[] dArr = new double[size3];
        double[] dArr2 = new double[size3];
        for (int i5 = 0; i5 < size3; i5++) {
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
        double[] dArrPchip = CurveUtils.pchip(dArr, dArr2, size3, dArr3, time2);
        for (int i9 = 0; i9 < dArrPchip.length; i9++) {
            dayValues.add(new Entry((float) dArr3[i9], (float) dArrPchip[i9]));
        }
        int i10 = 0;
        while (i10 < arrayList2.size() - 1) {
            int time5 = ((TemperBean) arrayList2.get(i10)).getTime();
            int i11 = i10 + 1;
            int time6 = ((TemperBean) arrayList2.get(i11)).getTime();
            if (time6 - time5 > 60 || ((time != 0 && i10 == 0) || i10 == arrayList2.size() - 2)) {
                while (true) {
                    time5++;
                    if (time5 >= time6 - 10 || dayValues.size() < time5) {
                        break;
                    }
                    dayValues.remove(time5);
                    dayValues.add(time5, new Entry(time5, minYNum));
                }
            }
            i10 = i11;
        }
        for (int i12 = 0; i12 < size3; i12++) {
        }
        for (int i13 = 0; i13 < dayValues.size(); i13++) {
        }
    }

    public static void initBarChart(BarChart mTempDayChart, List<TemperatureHisListBean> mLineChartBean, boolean isCanTouch) throws NumberFormatException {
        mTempDayChart.setRoundBar(true);
        mTempDayChart.getDescription().setEnabled(false);
        mTempDayChart.setPinchZoom(false);
        mTempDayChart.setDrawGridBackground(false);
        mTempDayChart.setExtraBottomOffset(15.0f);
        mTempDayChart.getLegend().setEnabled(false);
        mTempDayChart.setDoubleTapToZoomEnabled(false);
        mTempDayChart.setScaleEnabled(false);
        XAxis xAxis2 = mTempDayChart.getXAxis();
        xAxis2.setGranularity(1.0f);
        xAxis2.setDrawGridLines(false);
        xAxis2.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis2.setCenterAxisLabels(true);
        YAxis axisLeft = mTempDayChart.getAxisLeft();
        axisLeft.setDrawGridLines(false);
        axisLeft.setSpaceTop(35.0f);
        axisLeft.setAxisMinimum(0.0f);
        mTempDayChart.getAxisRight().setEnabled(false);
        mTempDayChart.getAxisLeft().setEnabled(false);
        mTempDayChart.getXAxis().setAxisLineColor(0);
        if (!isCanTouch) {
            mTempDayChart.setTouchEnabled(isCanTouch);
        }
        setData(mTempDayChart, mLineChartBean, mTempDayChart.getContext());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void setData(BarChart mTempDayChart, List<TemperatureHisListBean> mLineChartBean, Context context) throws NumberFormatException {
        boolean z;
        boolean z2;
        mTempDayChart.getXAxis().setValueFormatter(new MyTempDayCustomXAxisValueFormatter(true));
        mTempDayChart.getXAxis().setLabelCount(24, true);
        mTempDayChart.setXAxisRenderer(new MyXAxisRenderer(mTempDayChart.getViewPortHandler(), mTempDayChart.getXAxis(), mTempDayChart.getTransformer(YAxis.AxisDependency.LEFT)));
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        new ArrayList();
        ArrayList arrayList5 = new ArrayList();
        char c2 = 0;
        for (int i2 = 0; i2 < 24; i2++) {
            arrayList5.add(new BarEntry(i2, 0.0f));
        }
        int i3 = 0;
        int i4 = 0;
        float f2 = 1.0f;
        float f3 = 0.0f;
        while (i3 < mLineChartBean.size()) {
            int i5 = Integer.parseInt(mLineChartBean.get(i3).getTime().split(":")[c2]);
            float f4 = Float.parseFloat(mLineChartBean.get(i3).getmValue());
            if (i3 == mLineChartBean.size() - 1) {
                if (i5 == i4) {
                    f3 += f4;
                    f2 += 1.0f;
                    ((BarEntry) arrayList5.get(i4)).setY(f3 / f2);
                } else {
                    ((BarEntry) arrayList5.get(i4)).setY(f3 / f2);
                    ((BarEntry) arrayList5.get(i5)).setY(f4);
                }
            } else if (i5 == i4) {
                f3 += f4;
                f2 += 1.0f;
            } else {
                ((BarEntry) arrayList5.get(i4)).setY(f3 / f2);
                i4 = i5;
                f2 = 1.0f;
                f3 = f4;
            }
            i3++;
            c2 = 0;
        }
        setDayData(mTempDayChart, Chart.MarkerLabel.PRESSURE_CHART, mLineChartBean, "", context);
        int color = context.getColor(R.color.pressure_level4);
        int color2 = context.getColor(R.color.pressure_level3);
        int color3 = context.getColor(R.color.pressure_level2);
        int color4 = context.getColor(R.color.pressure_level1);
        BarData barData = new BarData();
        int i6 = 0;
        while (i6 < 24) {
            BarEntry barEntry = (BarEntry) arrayList5.get(i6);
            ArrayList arrayList6 = new ArrayList();
            arrayList6.add(barEntry);
            BarDataSet barDataSet = new BarDataSet(arrayList6, "");
            if (barEntry.getY() >= 81.0f) {
                barDataSet.setColor(color);
            } else if (barEntry.getY() >= 51.0f) {
                barDataSet.setColor(color2);
            } else if (barEntry.getY() >= 26.0f) {
                barDataSet.setColor(color3);
            } else {
                barDataSet.setColor(color4);
            }
            barData.addDataSet(barDataSet);
            i6++;
            arrayList = arrayList6;
        }
        if (mTempDayChart.getData() != null && ((BarData) mTempDayChart.getData()).getDataSetCount() > 0) {
            BarDataSet barDataSet2 = (BarDataSet) ((BarData) mTempDayChart.getData()).getDataSetByIndex(0);
            BarDataSet barDataSet3 = (BarDataSet) ((BarData) mTempDayChart.getData()).getDataSetByIndex(1);
            BarDataSet barDataSet4 = (BarDataSet) ((BarData) mTempDayChart.getData()).getDataSetByIndex(2);
            BarDataSet barDataSet5 = (BarDataSet) ((BarData) mTempDayChart.getData()).getDataSetByIndex(3);
            barDataSet2.setEntries(arrayList);
            barDataSet3.setEntries(arrayList2);
            barDataSet4.setEntries(arrayList3);
            barDataSet5.setEntries(arrayList4);
            ((BarData) mTempDayChart.getData()).notifyDataChanged();
            mTempDayChart.notifyDataSetChanged();
            z2 = true;
            z = false;
        } else {
            new BarDataSet(arrayList, "").setColor(Color.rgb(0, Opcodes.IFEQ, 255));
            new BarDataSet(arrayList2, "").setColor(Color.rgb(255, Opcodes.IFEQ, 102));
            new BarDataSet(arrayList3, "").setColor(Color.rgb(51, Opcodes.IFEQ, Opcodes.IFEQ));
            z = false;
            new BarDataSet(arrayList4, "").setColor(Color.rgb(255, 102, 0));
            mTempDayChart.setData(barData);
            z2 = true;
        }
        mTempDayChart.setDrawBarShadow(z2);
        mTempDayChart.getBarData().setBarWidth(0.85f);
        mTempDayChart.getBarData().setValueTextSize(0.0f);
        mTempDayChart.getXAxis().setEnabled(z);
        mTempDayChart.invalidate();
        mTempDayChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() { // from class: com.yucheng.smarthealthpro.customchart.temperature.TempLineChartUtils.4
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
