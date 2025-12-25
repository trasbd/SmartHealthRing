package com.yucheng.smarthealthpro.customchart.klinechart;

import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import androidx.collection.ArrayMap;
import androidx.core.widget.NestedScrollView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.material.timepicker.TimeModel;
import com.yucheng.smarthealthpro.MyApplication;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.customchart.MyBpDayCustomXAxisValueFormatter;
import com.yucheng.smarthealthpro.customchart.MyMarkerView;
import com.yucheng.smarthealthpro.customchart.MyMonthCustomXAxisValueFormatter;
import com.yucheng.smarthealthpro.customchart.MyWeekCustomXAxisValueFormatter;
import com.yucheng.smarthealthpro.customchart.charts.CandleStickChart;
import com.yucheng.smarthealthpro.customchart.charts.Chart;
import com.yucheng.smarthealthpro.customchart.components.XAxis;
import com.yucheng.smarthealthpro.customchart.components.YAxis;
import com.yucheng.smarthealthpro.customchart.data.CandleData;
import com.yucheng.smarthealthpro.customchart.data.CandleDataSet;
import com.yucheng.smarthealthpro.customchart.data.CandleEntry;
import com.yucheng.smarthealthpro.customchart.renderer.MyXAxisRenderer;
import com.yucheng.smarthealthpro.customchart.utils.HourToMinute;
import com.yucheng.smarthealthpro.home.activity.bloodpressure.bean.BloodPressureHisListBean;
import com.yucheng.smarthealthpro.me.setting.contacts.utils.DpUtil;
import com.yucheng.smarthealthpro.utils.AppDateMgr;
import com.yucheng.smarthealthpro.utils.TimeStampUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.json.HTTP;

/* loaded from: classes4.dex */
public class BpCandleStickChartUtils {
    private static List<CandleEntry> dayValues;
    private static BpCandleStickChartUtils mBpCandleStickChartUtils;
    private static List<CandleEntry> monthValues;
    private static List<CandleEntry> weekValues;

    public enum FORMATTER {
        DAY,
        WEEK,
        MONTH
    }

    private BpCandleStickChartUtils() {
    }

    public static synchronized BpCandleStickChartUtils getInstance() {
        if (mBpCandleStickChartUtils == null) {
            mBpCandleStickChartUtils = new BpCandleStickChartUtils();
        }
        return mBpCandleStickChartUtils;
    }

    public static void initCandleStickChart(CandleStickChart mCandleStickChart, Context context, List<BloodPressureHisListBean> mBloodPressureHisListBean, String mToDay, final NestedScrollView mNestedScrollView, float yMaximum, float yMinimum, float xMaximum, float xMinimum, int mXLabelCount, int mYLabelCount, FORMATTER formatter) {
        mCandleStickChart.setBackgroundColor(-1);
        mCandleStickChart.getDescription().setEnabled(false);
        mCandleStickChart.setMaxVisibleValueCount(60);
        mCandleStickChart.setDrawGridBackground(false);
        mCandleStickChart.getAxisRight().setEnabled(false);
        mCandleStickChart.getLegend().setEnabled(false);
        mCandleStickChart.setDragEnabled(true);
        mCandleStickChart.setScaleEnabled(false);
        mCandleStickChart.setPinchZoom(false);
        mCandleStickChart.animateX(ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED);
        mCandleStickChart.setOnTouchListener(new View.OnTouchListener() { // from class: com.yucheng.smarthealthpro.customchart.klinechart.BpCandleStickChartUtils.1
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
        YAxis axisLeft = mCandleStickChart.getAxisLeft();
        axisLeft.setEnabled(true);
        axisLeft.setLabelCount(mYLabelCount, false);
        axisLeft.setDrawAxisLine(false);
        axisLeft.setDrawGridLines(false);
        if (mBloodPressureHisListBean.size() > 0) {
            int i2 = 0;
            for (int i3 = 0; i3 < mBloodPressureHisListBean.size(); i3++) {
                int bloodSBP = mBloodPressureHisListBean.get(i3).getBloodSBP();
                if (bloodSBP > i2) {
                    i2 = bloodSBP;
                }
            }
            axisLeft.setAxisMaximum(i2 > 300 ? 400.0f : i2 > 200 ? 300.0f : i2 > 100 ? 200.0f : i2 / 0.8f);
        } else {
            axisLeft.setAxisMaximum(yMaximum);
        }
        axisLeft.setAxisMinimum(yMinimum);
        XAxis xAxis = mCandleStickChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(true);
        xAxis.setAxisMaximum(xMaximum);
        xAxis.setYOffset(DpUtil.dp2px(MyApplication.getInstance().getApplicationContext(), 5.0f));
        if (formatter == FORMATTER.DAY) {
            xAxis.setLabelCount(mXLabelCount, true);
            xAxis.setValueFormatter(new MyBpDayCustomXAxisValueFormatter(true));
            xAxis.setAvoidFirstLastClipping(true);
            mCandleStickChart.setXAxisRenderer(new MyXAxisRenderer(mCandleStickChart.getViewPortHandler(), mCandleStickChart.getXAxis(), mCandleStickChart.getTransformer(YAxis.AxisDependency.LEFT)));
            setDayData(mCandleStickChart, mBloodPressureHisListBean, context);
            setCandleStickChart(mCandleStickChart, dayValues, context, FORMATTER.DAY);
            return;
        }
        if (formatter == FORMATTER.WEEK) {
            xAxis.setLabelCount(mXLabelCount, false);
            xAxis.setValueFormatter(new MyWeekCustomXAxisValueFormatter(true));
            setWeekData(mCandleStickChart, mBloodPressureHisListBean, mToDay, context);
            setCandleStickChart(mCandleStickChart, weekValues, context, FORMATTER.WEEK);
            return;
        }
        xAxis.setLabelCount(mXLabelCount, true);
        xAxis.setValueFormatter(new MyMonthCustomXAxisValueFormatter(true, 30));
        xAxis.setAvoidFirstLastClipping(true);
        mCandleStickChart.setXAxisRenderer(new MyXAxisRenderer(mCandleStickChart.getViewPortHandler(), mCandleStickChart.getXAxis(), mCandleStickChart.getTransformer(YAxis.AxisDependency.LEFT)));
        setMonthData(mCandleStickChart, mBloodPressureHisListBean, mToDay, context);
        setCandleStickChart(mCandleStickChart, monthValues, context, FORMATTER.MONTH);
    }

    public static void setDayData(CandleStickChart mCandleStickChart, List<BloodPressureHisListBean> mBloodPressureHisListBean, Context context) {
        mCandleStickChart.resetTracking();
        dayValues = new ArrayList();
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 <= 288; i2++) {
            dayValues.add(new CandleEntry(i2, 0.0f, 0.0f, 0.0f, 0.0f, ""));
        }
        if (mBloodPressureHisListBean.size() != 0) {
            Collections.reverse(mBloodPressureHisListBean);
            for (int i3 = 0; i3 < mBloodPressureHisListBean.size(); i3++) {
                arrayList.add(new BloodPressureBean(mBloodPressureHisListBean.get(i3).getBpStartTime(), HourToMinute.timeToMinute(mBloodPressureHisListBean.get(i3).getTime()), 0.0f, 0.0f, mBloodPressureHisListBean.get(i3).getBloodDBP(), mBloodPressureHisListBean.get(i3).getBloodSBP(), mBloodPressureHisListBean.get(i3).getBloodDBP(), mBloodPressureHisListBean.get(i3).getBloodSBP()));
            }
        } else {
            arrayList.add(new BloodPressureBean("", 0, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        }
        ArrayMap arrayMap = new ArrayMap();
        for (int i4 = 0; i4 < arrayList.size(); i4++) {
            int time = ((BloodPressureBean) arrayList.get(i4)).getTime() / 5;
            ArrayList arrayList2 = (ArrayList) arrayMap.get(new IntKey(time));
            if (arrayList2 == null) {
                arrayList2 = new ArrayList();
            }
            arrayList2.add((BloodPressureBean) arrayList.get(i4));
            arrayMap.put(new IntKey(time), arrayList2);
        }
        for (Map.Entry entry : arrayMap.entrySet()) {
            int id = ((IntKey) entry.getKey()).getId();
            ArrayList arrayList3 = (ArrayList) entry.getValue();
            String str = "";
            for (int i5 = 0; i5 < arrayList3.size(); i5++) {
                BloodPressureBean bloodPressureBean = (BloodPressureBean) arrayList3.get(i5);
                int time2 = bloodPressureBean.getTime();
                String str2 = str + String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(time2 / 60)) + ":" + String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(time2 % 60)) + StringUtils.SPACE + ((int) bloodPressureBean.getmOpen()) + "/" + ((int) bloodPressureBean.getmClose());
                if (i5 != arrayList3.size() - 1) {
                    str2 = str2 + HTTP.CRLF;
                }
                str = str2;
            }
            Log.d("Values---", "setDayData: " + str);
            BloodPressureBean bloodPressureBean2 = (BloodPressureBean) arrayList3.get(arrayList3.size() - 1);
            dayValues.remove(id);
            dayValues.add(id, new CandleEntry(id, bloodPressureBean2.getmShadowHigh(), bloodPressureBean2.getmShadowLow(), bloodPressureBean2.getmOpen(), bloodPressureBean2.getmClose(), str));
        }
        MyMarkerView myMarkerView = new MyMarkerView(context, R.layout.layout_for_custom_marker_view, null, arrayList, null, null);
        myMarkerView.setChartView(mCandleStickChart);
        mCandleStickChart.setMarker(myMarkerView, null, arrayList, null, null, Chart.MarkerLabel.BLOOD_PRESSURE_DAY_CHART);
    }

    public static void setWeekData(CandleStickChart mCandleStickChart, List<BloodPressureHisListBean> mBloodPressureHisListBean, String mToDay, Context context) {
        mCandleStickChart.resetTracking();
        weekValues = new ArrayList();
        ArrayList arrayList = new ArrayList();
        if (mBloodPressureHisListBean == null || mBloodPressureHisListBean.size() == 0) {
            for (int i2 = 0; i2 < 7; i2++) {
                arrayList.add(new BloodPressureBean("", i2, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f));
            }
        } else {
            for (int i3 = 0; i3 < mBloodPressureHisListBean.size(); i3++) {
                arrayList.add(new BloodPressureBean(mBloodPressureHisListBean.get(i3).getBpStartTime(), i3, 0.0f, 0.0f, mBloodPressureHisListBean.get(i3).getBloodSBP(), mBloodPressureHisListBean.get(i3).getBloodDBP(), mBloodPressureHisListBean.get(i3).getBloodSBP(), mBloodPressureHisListBean.get(i3).getBloodDBP()));
            }
        }
        for (int i4 = 0; i4 < arrayList.size(); i4++) {
            weekValues.add(new CandleEntry(i4, 0.0f, 0.0f, 0.0f, 0.0f, ""));
        }
        ArrayList<String> arrayListPastDay = pastDay(mToDay);
        for (int i5 = 0; i5 < arrayList.size(); i5++) {
            if (i5 < arrayListPastDay.size()) {
                weekValues.remove(i5);
                weekValues.add(i5, new CandleEntry(((BloodPressureBean) arrayList.get(i5)).getTime(), ((BloodPressureBean) arrayList.get(i5)).getmShadowHigh(), ((BloodPressureBean) arrayList.get(i5)).getmShadowLow(), ((BloodPressureBean) arrayList.get(i5)).getmOpen(), ((BloodPressureBean) arrayList.get(i5)).getmClose(), TimeStampUtils.dateForStringDates(TimeStampUtils.stringForDateDay(arrayListPastDay.get(i5)))));
            }
        }
        MyMarkerView myMarkerView = new MyMarkerView(context, R.layout.layout_for_custom_marker_view, null, arrayList, null, null);
        myMarkerView.setChartView(mCandleStickChart);
        mCandleStickChart.setMarker(myMarkerView, null, arrayList, null, null, Chart.MarkerLabel.BLOOD_PRESSURE_WEEK_MONTH_CHART);
    }

    public static void setMonthData(CandleStickChart mCandleStickChart, List<BloodPressureHisListBean> mBloodPressureHisListBean, String mToDay, Context context) {
        mCandleStickChart.resetTracking();
        monthValues = new ArrayList();
        ArrayList arrayList = new ArrayList();
        if (mBloodPressureHisListBean == null || mBloodPressureHisListBean.size() == 0) {
            for (int i2 = 0; i2 < 30; i2++) {
                arrayList.add(new BloodPressureBean("", i2, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f));
            }
        } else {
            for (int i3 = 0; i3 < mBloodPressureHisListBean.size(); i3++) {
                arrayList.add(new BloodPressureBean(mBloodPressureHisListBean.get(i3).getBpStartTime(), i3, 0.0f, 0.0f, mBloodPressureHisListBean.get(i3).getBloodSBP(), mBloodPressureHisListBean.get(i3).getBloodDBP(), mBloodPressureHisListBean.get(i3).getBloodSBP(), mBloodPressureHisListBean.get(i3).getBloodDBP()));
            }
        }
        for (int i4 = 0; i4 < arrayList.size(); i4++) {
            monthValues.add(new CandleEntry(i4, 0.0f, 0.0f, 0.0f, 0.0f, ""));
        }
        ArrayList<String> arrayListPastThirtyDay = pastThirtyDay(mToDay);
        for (int i5 = 0; i5 < arrayList.size(); i5++) {
            if (i5 < arrayListPastThirtyDay.size()) {
                monthValues.remove(i5);
                monthValues.add(i5, new CandleEntry(((BloodPressureBean) arrayList.get(i5)).getTime(), ((BloodPressureBean) arrayList.get(i5)).getmShadowHigh(), ((BloodPressureBean) arrayList.get(i5)).getmShadowLow(), ((BloodPressureBean) arrayList.get(i5)).getmOpen(), ((BloodPressureBean) arrayList.get(i5)).getmClose(), TimeStampUtils.dateForStringDates(TimeStampUtils.stringForDateDay(arrayListPastThirtyDay.get(i5)))));
            }
        }
        MyMarkerView myMarkerView = new MyMarkerView(context, R.layout.layout_for_custom_marker_view, null, arrayList, null, null);
        myMarkerView.setChartView(mCandleStickChart);
        mCandleStickChart.setMarker(myMarkerView, null, arrayList, null, null, Chart.MarkerLabel.BLOOD_PRESSURE_WEEK_MONTH_CHART);
    }

    public static void setCandleStickChart(CandleStickChart mCandleStickChart, List<CandleEntry> values, Context context, FORMATTER formatter) {
        CandleDataSet candleDataSet = new CandleDataSet(values, "Data Set");
        candleDataSet.setDrawIcons(false);
        candleDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        candleDataSet.setShadowColor(-16776961);
        if (formatter != FORMATTER.DAY && formatter == FORMATTER.WEEK) {
            candleDataSet.setShadowWidth(3.0f);
            candleDataSet.setBarSpace(0.4f);
        } else {
            candleDataSet.setShadowWidth(0.7f);
            candleDataSet.setBarSpace(0.05f);
        }
        candleDataSet.setDecreasingColor(context.getColor(R.color.blood_presure_bar_color_1));
        candleDataSet.setDecreasingPaintStyle(Paint.Style.FILL);
        candleDataSet.setIncreasingColor(context.getColor(R.color.blood_presure_bar_color_2));
        candleDataSet.setIncreasingPaintStyle(Paint.Style.FILL);
        candleDataSet.setNeutralColor(-16776961);
        candleDataSet.setDrawValues(!candleDataSet.isDrawValuesEnabled());
        candleDataSet.setDrawHorizontalHighlightIndicator(false);
        candleDataSet.setDrawVerticalHighlightIndicator(true);
        mCandleStickChart.setData(new CandleData(candleDataSet));
        mCandleStickChart.invalidate();
    }

    public static ArrayList<String> pastDay(String time) {
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            Date date = new SimpleDateFormat(AppDateMgr.DF_YYYY_MM_DD).parse(time);
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
}
