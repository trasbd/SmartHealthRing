package com.yucheng.smarthealthpro.customchart.step;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.widget.NestedScrollView;
import com.alibaba.fastjson.parser.JSONLexer;
import com.dd.plist.ASCIIPropertyListParser;
import com.google.android.gms.common.ConnectionResult;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.customchart.MyMarkerView;
import com.yucheng.smarthealthpro.customchart.MyMonthCustomXAxisValueFormatter;
import com.yucheng.smarthealthpro.customchart.MyStepDayCustomXAxisValueFormatter;
import com.yucheng.smarthealthpro.customchart.MyWeekCustomXAxisValueFormatter;
import com.yucheng.smarthealthpro.customchart.charts.Chart;
import com.yucheng.smarthealthpro.customchart.charts.GradualBarChart;
import com.yucheng.smarthealthpro.customchart.components.XAxis;
import com.yucheng.smarthealthpro.customchart.components.YAxis;
import com.yucheng.smarthealthpro.customchart.data.BarData;
import com.yucheng.smarthealthpro.customchart.data.BarDataSet;
import com.yucheng.smarthealthpro.customchart.data.BarEntry;
import com.yucheng.smarthealthpro.customchart.renderer.MyXAxisRenderer;
import com.yucheng.smarthealthpro.customchart.utils.Fill;
import com.yucheng.smarthealthpro.home.activity.running.bean.RunningHisListBean;
import com.yucheng.smarthealthpro.home.activity.running.bean.StepBean;
import com.yucheng.smarthealthpro.me.setting.contacts.utils.DpUtil;
import com.yucheng.smarthealthpro.utils.AppDateMgr;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.TimeStampUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import kotlin.text.Typography;
import org.apache.commons.lang3.ClassUtils;

/* loaded from: classes4.dex */
public class StepBarChartUtils {
    private static List<BarEntry> dayValues;
    private static int mMonthMaxStepNum;
    private static int mMonthSumUpStepNum;
    private static StepBarChartUtils mStepBarChartUtils;
    private static int mWeekMaxStepNum;
    private static int mWeekSumUpStepNum;
    private static List<BarEntry> monthValues;
    private static List<BarEntry> weekValues;
    private static XAxis xAxis;
    private static YAxis yAxis;

    public enum FORMATTER {
        DAY,
        WEEK,
        MONTH
    }

    private StepBarChartUtils() {
    }

    public static synchronized StepBarChartUtils getInstance() {
        if (mStepBarChartUtils == null) {
            mStepBarChartUtils = new StepBarChartUtils();
        }
        return mStepBarChartUtils;
    }

    public static void initBarChart(GradualBarChart mBarChart, Context context, List<RunningHisListBean> mRunningHisListBean, String mToDay, final NestedScrollView mNestedScrollView, float yMaximum, float yMinimum, float xMaximum, int mXLabelCount, FORMATTER formatter, String mThatVeryDay) {
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
        mBarChart.setOnTouchListener(new View.OnTouchListener() { // from class: com.yucheng.smarthealthpro.customchart.step.StepBarChartUtils.1
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
            setDayData(mBarChart, mRunningHisListBean, mToDay, context);
            setBarChart(mBarChart, dayValues, context);
            return;
        }
        if (formatter == FORMATTER.WEEK) {
            xAxis.setLabelCount(mXLabelCount, false);
            xAxis.setValueFormatter(new MyWeekCustomXAxisValueFormatter(true));
            setWeekData(mBarChart, mRunningHisListBean, mToDay, context);
            setBarChart(mBarChart, weekValues, context);
            return;
        }
        xAxis.setLabelCount(mXLabelCount, true);
        xAxis.setValueFormatter(new MyMonthCustomXAxisValueFormatter(true, 30));
        xAxis.setAvoidFirstLastClipping(true);
        mBarChart.setXAxisRenderer(new MyXAxisRenderer(mBarChart.getViewPortHandler(), mBarChart.getXAxis(), mBarChart.getTransformer(YAxis.AxisDependency.LEFT)));
        setMonthData(mBarChart, mRunningHisListBean, mToDay, context);
        setBarChart(mBarChart, monthValues, context);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static void setDayData(GradualBarChart mBarChart, List<RunningHisListBean> mRunningHisListBean, String mToDay, Context context) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
        String str9;
        String str10;
        String str11;
        String str12;
        String str13;
        String str14;
        int i2;
        String str15;
        String str16;
        List<RunningHisListBean> list = mRunningHisListBean;
        ArrayList arrayList = new ArrayList();
        dayValues = new ArrayList();
        arrayList.add(new StepBean(0.0f, "00:00", "00:30"));
        arrayList.add(new StepBean(0.0f, "00:30", "01:00"));
        arrayList.add(new StepBean(0.0f, "01:00", "01:30"));
        arrayList.add(new StepBean(0.0f, "01:30", "02:00"));
        arrayList.add(new StepBean(0.0f, "02:00", "02:30"));
        arrayList.add(new StepBean(0.0f, "02:30", "03:00"));
        arrayList.add(new StepBean(0.0f, "03:00", "03:30"));
        arrayList.add(new StepBean(0.0f, "03:30", "04:00"));
        arrayList.add(new StepBean(0.0f, "04:00", "04:30"));
        arrayList.add(new StepBean(0.0f, "04:30", "05:00"));
        String str17 = "05:30";
        arrayList.add(new StepBean(0.0f, "05:00", "05:30"));
        arrayList.add(new StepBean(0.0f, "05:30", "06:00"));
        String str18 = "00:30";
        String str19 = "06:30";
        arrayList.add(new StepBean(0.0f, "06:00", "06:30"));
        String str20 = "01:00";
        String str21 = "07:00";
        arrayList.add(new StepBean(0.0f, "06:30", "07:00"));
        String str22 = "01:30";
        arrayList.add(new StepBean(0.0f, "07:00", "07:30"));
        String str23 = "02:00";
        arrayList.add(new StepBean(0.0f, "07:30", "08:00"));
        String str24 = "02:30";
        arrayList.add(new StepBean(0.0f, "08:00", "08:30"));
        String str25 = "03:00";
        arrayList.add(new StepBean(0.0f, "08:30", "09:00"));
        String str26 = "03:30";
        arrayList.add(new StepBean(0.0f, "09:00", "09:30"));
        String str27 = "04:00";
        arrayList.add(new StepBean(0.0f, "09:30", "10:00"));
        String str28 = "04:30";
        arrayList.add(new StepBean(0.0f, "10:00", "10:30"));
        String str29 = "05:00";
        arrayList.add(new StepBean(0.0f, "10:30", "11:00"));
        arrayList.add(new StepBean(0.0f, "11:00", "11:30"));
        arrayList.add(new StepBean(0.0f, "11:30", "12:00"));
        arrayList.add(new StepBean(0.0f, "12:00", "12:30"));
        arrayList.add(new StepBean(0.0f, "12:30", "13:00"));
        arrayList.add(new StepBean(0.0f, "13:00", "13:30"));
        arrayList.add(new StepBean(0.0f, "13:30", "14:00"));
        arrayList.add(new StepBean(0.0f, "14:00", "14:30"));
        arrayList.add(new StepBean(0.0f, "14:30", "15:00"));
        arrayList.add(new StepBean(0.0f, "15:00", "15:30"));
        arrayList.add(new StepBean(0.0f, "15:30", "16:00"));
        arrayList.add(new StepBean(0.0f, "16:00", "16:30"));
        arrayList.add(new StepBean(0.0f, "16:30", "17:00"));
        arrayList.add(new StepBean(0.0f, "17:00", "17:30"));
        arrayList.add(new StepBean(0.0f, "17:30", "18:00"));
        arrayList.add(new StepBean(0.0f, "18:00", "18:30"));
        arrayList.add(new StepBean(0.0f, "18:30", "19:00"));
        arrayList.add(new StepBean(0.0f, "19:00", "19:30"));
        arrayList.add(new StepBean(0.0f, "19:30", "20:00"));
        arrayList.add(new StepBean(0.0f, "20:00", "20:30"));
        arrayList.add(new StepBean(0.0f, "20:30", "21:00"));
        arrayList.add(new StepBean(0.0f, "21:00", "21:30"));
        arrayList.add(new StepBean(0.0f, "21:30", "22:00"));
        arrayList.add(new StepBean(0.0f, "22:00", "22:30"));
        arrayList.add(new StepBean(0.0f, "22:30", "23:00"));
        arrayList.add(new StepBean(0.0f, "23:00", "23:30"));
        arrayList.add(new StepBean(0.0f, "23:30", "24:00"));
        Collections.reverse(mRunningHisListBean);
        int i3 = 0;
        int i4 = 0;
        while (i4 < mRunningHisListBean.size()) {
            String str30 = list.get(i4).getSportStartTime().split("-")[i3];
            str30.hashCode();
            switch (str30.hashCode()) {
                case 45816250:
                    str = str29;
                    if (str30.equals("00:00")) {
                        c = 0;
                        break;
                    }
                    break;
                case 45816343:
                    String str31 = str18;
                    str = str29;
                    c = str30.equals(str31) ? (char) 1 : (char) 65535;
                    str18 = str31;
                    break;
                case 45846041:
                    String str32 = str20;
                    str = str29;
                    c = str30.equals(str32) ? (char) 2 : (char) 65535;
                    str20 = str32;
                    break;
                case 45846134:
                    String str33 = str22;
                    str = str29;
                    c = str30.equals(str33) ? (char) 3 : (char) 65535;
                    str22 = str33;
                    break;
                case 45875832:
                    String str34 = str23;
                    str = str29;
                    c = str30.equals(str34) ? (char) 4 : (char) 65535;
                    str23 = str34;
                    break;
                case 45875925:
                    String str35 = str24;
                    str = str29;
                    c = str30.equals(str35) ? (char) 5 : (char) 65535;
                    str24 = str35;
                    break;
                case 45905623:
                    String str36 = str25;
                    str = str29;
                    c = str30.equals(str36) ? (char) 6 : (char) 65535;
                    str25 = str36;
                    break;
                case 45905716:
                    String str37 = str26;
                    str = str29;
                    c = str30.equals(str37) ? (char) 7 : (char) 65535;
                    str26 = str37;
                    break;
                case 45935414:
                    String str38 = str27;
                    str = str29;
                    c = str30.equals(str38) ? '\b' : (char) 65535;
                    str27 = str38;
                    break;
                case 45935507:
                    String str39 = str28;
                    str = str29;
                    c = str30.equals(str39) ? '\t' : (char) 65535;
                    str28 = str39;
                    break;
                case 45965205:
                    str = str29;
                    if (str30.equals(str)) {
                        c = '\n';
                        break;
                    }
                    break;
                case 45965298:
                    if (str30.equals(str17)) {
                        c = 11;
                    }
                    str = str29;
                    break;
                case 45994996:
                    if (str30.equals("06:00")) {
                        c = '\f';
                    }
                    str = str29;
                    break;
                case 45995089:
                    if (str30.equals(str19)) {
                        c = '\r';
                    }
                    str = str29;
                    break;
                case 46024787:
                    if (str30.equals(str21)) {
                        c = 14;
                    }
                    str = str29;
                    break;
                case 46024880:
                    if (str30.equals("07:30")) {
                        c = 15;
                    }
                    str = str29;
                    break;
                case 46054578:
                    if (str30.equals("08:00")) {
                        c = 16;
                    }
                    str = str29;
                    break;
                case 46054671:
                    if (str30.equals("08:30")) {
                        c = 17;
                    }
                    str = str29;
                    break;
                case 46084369:
                    if (str30.equals("09:00")) {
                        c = 18;
                    }
                    str = str29;
                    break;
                case 46084462:
                    if (str30.equals("09:30")) {
                        c = 19;
                    }
                    str = str29;
                    break;
                case 46739771:
                    if (str30.equals("10:00")) {
                        c = 20;
                    }
                    str = str29;
                    break;
                case 46739864:
                    if (str30.equals("10:30")) {
                        c = 21;
                    }
                    str = str29;
                    break;
                case 46769562:
                    if (str30.equals("11:00")) {
                        c = 22;
                    }
                    str = str29;
                    break;
                case 46769655:
                    if (str30.equals("11:30")) {
                        c = 23;
                    }
                    str = str29;
                    break;
                case 46799353:
                    if (str30.equals("12:00")) {
                        c = 24;
                    }
                    str = str29;
                    break;
                case 46799446:
                    if (str30.equals("12:30")) {
                        c = 25;
                    }
                    str = str29;
                    break;
                case 46829144:
                    if (str30.equals("13:00")) {
                        c = JSONLexer.EOI;
                    }
                    str = str29;
                    break;
                case 46829237:
                    if (str30.equals("13:30")) {
                        c = 27;
                    }
                    str = str29;
                    break;
                case 46858935:
                    if (str30.equals("14:00")) {
                        c = 28;
                    }
                    str = str29;
                    break;
                case 46859028:
                    if (str30.equals("14:30")) {
                        c = 29;
                    }
                    str = str29;
                    break;
                case 46888726:
                    if (str30.equals("15:00")) {
                        c = 30;
                    }
                    str = str29;
                    break;
                case 46888819:
                    if (str30.equals("15:30")) {
                        c = 31;
                    }
                    str = str29;
                    break;
                case 46918517:
                    if (str30.equals("16:00")) {
                        c = ' ';
                    }
                    str = str29;
                    break;
                case 46918610:
                    if (str30.equals("16:30")) {
                        c = '!';
                    }
                    str = str29;
                    break;
                case 46948308:
                    if (str30.equals("17:00")) {
                        c = '\"';
                    }
                    str = str29;
                    break;
                case 46948401:
                    if (str30.equals("17:30")) {
                        c = '#';
                    }
                    str = str29;
                    break;
                case 46978099:
                    if (str30.equals("18:00")) {
                        c = '$';
                    }
                    str = str29;
                    break;
                case 46978192:
                    if (str30.equals("18:30")) {
                        c = '%';
                    }
                    str = str29;
                    break;
                case 47007890:
                    if (str30.equals("19:00")) {
                        c = Typography.amp;
                    }
                    str = str29;
                    break;
                case 47007983:
                    if (str30.equals("19:30")) {
                        c = '\'';
                    }
                    str = str29;
                    break;
                case 47663292:
                    if (str30.equals("20:00")) {
                        c = ASCIIPropertyListParser.ARRAY_BEGIN_TOKEN;
                    }
                    str = str29;
                    break;
                case 47663385:
                    if (str30.equals("20:30")) {
                        c = ASCIIPropertyListParser.ARRAY_END_TOKEN;
                    }
                    str = str29;
                    break;
                case 47693083:
                    if (str30.equals("21:00")) {
                        c = '*';
                    }
                    str = str29;
                    break;
                case 47693176:
                    if (str30.equals("21:30")) {
                        c = '+';
                    }
                    str = str29;
                    break;
                case 47722874:
                    if (str30.equals("22:00")) {
                        c = ASCIIPropertyListParser.ARRAY_ITEM_DELIMITER_TOKEN;
                    }
                    str = str29;
                    break;
                case 47722967:
                    if (str30.equals("22:30")) {
                        c = ASCIIPropertyListParser.DATE_DATE_FIELD_DELIMITER;
                    }
                    str = str29;
                    break;
                case 47752665:
                    if (str30.equals("23:00")) {
                        c = ClassUtils.PACKAGE_SEPARATOR_CHAR;
                    }
                    str = str29;
                    break;
                case 47752758:
                    if (str30.equals("23:30")) {
                        c = '/';
                    }
                    str = str29;
                    break;
                default:
                    str = str29;
                    break;
            }
            switch (c) {
                case 0:
                    str2 = str21;
                    str3 = str18;
                    String str40 = str28;
                    str4 = str19;
                    str5 = str20;
                    str6 = str22;
                    str7 = str23;
                    str8 = str24;
                    str9 = str25;
                    str10 = str26;
                    str11 = str27;
                    str12 = str40;
                    String str41 = str17;
                    str13 = str;
                    str14 = str41;
                    arrayList.remove(0);
                    StepBean stepBean = new StepBean(list.get(i4).getSportStep(), "00:00", str3);
                    i2 = 0;
                    arrayList.add(0, stepBean);
                    break;
                case 1:
                    str2 = str21;
                    String str42 = str22;
                    str7 = str23;
                    str8 = str24;
                    str9 = str25;
                    str10 = str26;
                    str11 = str27;
                    str12 = str28;
                    str4 = str19;
                    str5 = str20;
                    String str43 = str17;
                    str13 = str;
                    str14 = str43;
                    arrayList.remove(1);
                    str6 = str42;
                    str3 = str18;
                    arrayList.add(1, new StepBean(list.get(i4).getSportStep(), str3, str5));
                    i2 = 0;
                    break;
                case 2:
                    str2 = str21;
                    String str44 = str22;
                    String str45 = str28;
                    str4 = str19;
                    String str46 = str23;
                    str8 = str24;
                    str9 = str25;
                    str10 = str26;
                    str11 = str27;
                    str12 = str45;
                    String str47 = str17;
                    str13 = str;
                    str14 = str47;
                    arrayList.remove(2);
                    str7 = str46;
                    str5 = str20;
                    arrayList.add(2, new StepBean(list.get(i4).getSportStep(), str5, str44));
                    str6 = str44;
                    i2 = 0;
                    str3 = str18;
                    break;
                case 3:
                    str2 = str21;
                    String str48 = str24;
                    str9 = str25;
                    str10 = str26;
                    str11 = str27;
                    str12 = str28;
                    str4 = str19;
                    String str49 = str23;
                    String str50 = str17;
                    str13 = str;
                    str14 = str50;
                    arrayList.remove(3);
                    str8 = str48;
                    String str51 = str22;
                    arrayList.add(3, new StepBean(list.get(i4).getSportStep(), str51, str49));
                    str7 = str49;
                    str5 = str20;
                    i2 = 0;
                    str6 = str51;
                    str3 = str18;
                    break;
                case 4:
                    str2 = str21;
                    String str52 = str24;
                    String str53 = str28;
                    str4 = str19;
                    String str54 = str25;
                    str10 = str26;
                    str11 = str27;
                    str12 = str53;
                    String str55 = str17;
                    str13 = str;
                    str14 = str55;
                    arrayList.remove(4);
                    str9 = str54;
                    String str56 = str23;
                    arrayList.add(4, new StepBean(list.get(i4).getSportStep(), str56, str52));
                    str8 = str52;
                    i2 = 0;
                    str3 = str18;
                    String str57 = str22;
                    str7 = str56;
                    str5 = str20;
                    str6 = str57;
                    break;
                case 5:
                    str2 = str21;
                    String str58 = str26;
                    str11 = str27;
                    str12 = str28;
                    str4 = str19;
                    String str59 = str25;
                    String str60 = str17;
                    str13 = str;
                    str14 = str60;
                    arrayList.remove(5);
                    str10 = str58;
                    String str61 = str24;
                    arrayList.add(5, new StepBean(list.get(i4).getSportStep(), str61, str59));
                    str9 = str59;
                    str5 = str20;
                    str6 = str22;
                    str7 = str23;
                    i2 = 0;
                    str8 = str61;
                    str3 = str18;
                    break;
                case 6:
                    str2 = str21;
                    String str62 = str26;
                    String str63 = str28;
                    str4 = str19;
                    String str64 = str27;
                    str12 = str63;
                    String str65 = str17;
                    str13 = str;
                    str14 = str65;
                    arrayList.remove(6);
                    str11 = str64;
                    String str66 = str25;
                    arrayList.add(6, new StepBean(list.get(i4).getSportStep(), str66, str62));
                    str10 = str62;
                    i2 = 0;
                    str3 = str18;
                    String str67 = str24;
                    str9 = str66;
                    str5 = str20;
                    str6 = str22;
                    str7 = str23;
                    str8 = str67;
                    break;
                case 7:
                    str2 = str21;
                    String str68 = str28;
                    str4 = str19;
                    String str69 = str27;
                    String str70 = str17;
                    str13 = str;
                    str14 = str70;
                    arrayList.remove(7);
                    str12 = str68;
                    String str71 = str26;
                    arrayList.add(7, new StepBean(list.get(i4).getSportStep(), str71, str69));
                    str11 = str69;
                    str5 = str20;
                    str6 = str22;
                    str7 = str23;
                    str8 = str24;
                    str9 = str25;
                    i2 = 0;
                    str10 = str71;
                    str3 = str18;
                    break;
                case '\b':
                    str2 = str21;
                    String str72 = str28;
                    String str73 = str17;
                    str13 = str;
                    str14 = str73;
                    arrayList.remove(8);
                    str4 = str19;
                    String str74 = str27;
                    arrayList.add(8, new StepBean(list.get(i4).getSportStep(), str74, str72));
                    str12 = str72;
                    i2 = 0;
                    str3 = str18;
                    String str75 = str26;
                    str11 = str74;
                    str5 = str20;
                    str6 = str22;
                    str7 = str23;
                    str8 = str24;
                    str9 = str25;
                    str10 = str75;
                    break;
                case '\t':
                    String str76 = str17;
                    str13 = str;
                    str14 = str76;
                    arrayList.remove(9);
                    str2 = str21;
                    String str77 = str28;
                    arrayList.add(9, new StepBean(list.get(i4).getSportStep(), str77, str13));
                    str4 = str19;
                    str5 = str20;
                    str6 = str22;
                    str7 = str23;
                    str8 = str24;
                    str9 = str25;
                    str10 = str26;
                    str11 = str27;
                    i2 = 0;
                    str12 = str77;
                    str3 = str18;
                    break;
                case '\n':
                    String str78 = str;
                    str14 = str17;
                    arrayList.remove(10);
                    str13 = str78;
                    arrayList.add(10, new StepBean(list.get(i4).getSportStep(), str13, str14));
                    str2 = str21;
                    i2 = 0;
                    str3 = str18;
                    String str79 = str28;
                    str4 = str19;
                    str5 = str20;
                    str6 = str22;
                    str7 = str23;
                    str8 = str24;
                    str9 = str25;
                    str10 = str26;
                    str11 = str27;
                    str12 = str79;
                    break;
                case 11:
                    String str80 = str;
                    arrayList.remove(11);
                    str14 = str17;
                    arrayList.add(11, new StepBean(list.get(i4).getSportStep(), str14, "06:00"));
                    str13 = str80;
                    i2 = 0;
                    str2 = str21;
                    str3 = str18;
                    String str792 = str28;
                    str4 = str19;
                    str5 = str20;
                    str6 = str22;
                    str7 = str23;
                    str8 = str24;
                    str9 = str25;
                    str10 = str26;
                    str11 = str27;
                    str12 = str792;
                    break;
                case '\f':
                    str15 = str;
                    str16 = str17;
                    arrayList.remove(12);
                    arrayList.add(12, new StepBean(list.get(i4).getSportStep(), "06:00", str19));
                    str13 = str15;
                    str14 = str16;
                    i2 = 0;
                    str2 = str21;
                    str3 = str18;
                    String str7922 = str28;
                    str4 = str19;
                    str5 = str20;
                    str6 = str22;
                    str7 = str23;
                    str8 = str24;
                    str9 = str25;
                    str10 = str26;
                    str11 = str27;
                    str12 = str7922;
                    break;
                case '\r':
                    str15 = str;
                    str16 = str17;
                    arrayList.remove(13);
                    arrayList.add(13, new StepBean(list.get(i4).getSportStep(), str19, str21));
                    str13 = str15;
                    str14 = str16;
                    i2 = 0;
                    str2 = str21;
                    str3 = str18;
                    String str79222 = str28;
                    str4 = str19;
                    str5 = str20;
                    str6 = str22;
                    str7 = str23;
                    str8 = str24;
                    str9 = str25;
                    str10 = str26;
                    str11 = str27;
                    str12 = str79222;
                    break;
                case 14:
                    str15 = str;
                    str16 = str17;
                    arrayList.remove(14);
                    arrayList.add(14, new StepBean(list.get(i4).getSportStep(), str21, "07:30"));
                    str13 = str15;
                    str14 = str16;
                    i2 = 0;
                    str2 = str21;
                    str3 = str18;
                    String str792222 = str28;
                    str4 = str19;
                    str5 = str20;
                    str6 = str22;
                    str7 = str23;
                    str8 = str24;
                    str9 = str25;
                    str10 = str26;
                    str11 = str27;
                    str12 = str792222;
                    break;
                case 15:
                    str15 = str;
                    str16 = str17;
                    arrayList.remove(15);
                    arrayList.add(15, new StepBean(list.get(i4).getSportStep(), "07:30", "08:00"));
                    str13 = str15;
                    str14 = str16;
                    i2 = 0;
                    str2 = str21;
                    str3 = str18;
                    String str7922222 = str28;
                    str4 = str19;
                    str5 = str20;
                    str6 = str22;
                    str7 = str23;
                    str8 = str24;
                    str9 = str25;
                    str10 = str26;
                    str11 = str27;
                    str12 = str7922222;
                    break;
                case 16:
                    str15 = str;
                    str16 = str17;
                    arrayList.remove(16);
                    arrayList.add(16, new StepBean(list.get(i4).getSportStep(), "08:00", "08:30"));
                    str13 = str15;
                    str14 = str16;
                    i2 = 0;
                    str2 = str21;
                    str3 = str18;
                    String str79222222 = str28;
                    str4 = str19;
                    str5 = str20;
                    str6 = str22;
                    str7 = str23;
                    str8 = str24;
                    str9 = str25;
                    str10 = str26;
                    str11 = str27;
                    str12 = str79222222;
                    break;
                case 17:
                    str15 = str;
                    str16 = str17;
                    arrayList.remove(17);
                    arrayList.add(17, new StepBean(list.get(i4).getSportStep(), "08:30", "09:00"));
                    str13 = str15;
                    str14 = str16;
                    i2 = 0;
                    str2 = str21;
                    str3 = str18;
                    String str792222222 = str28;
                    str4 = str19;
                    str5 = str20;
                    str6 = str22;
                    str7 = str23;
                    str8 = str24;
                    str9 = str25;
                    str10 = str26;
                    str11 = str27;
                    str12 = str792222222;
                    break;
                case 18:
                    str15 = str;
                    str16 = str17;
                    arrayList.remove(18);
                    arrayList.add(18, new StepBean(list.get(i4).getSportStep(), "09:00", "09:30"));
                    str13 = str15;
                    str14 = str16;
                    i2 = 0;
                    str2 = str21;
                    str3 = str18;
                    String str7922222222 = str28;
                    str4 = str19;
                    str5 = str20;
                    str6 = str22;
                    str7 = str23;
                    str8 = str24;
                    str9 = str25;
                    str10 = str26;
                    str11 = str27;
                    str12 = str7922222222;
                    break;
                case 19:
                    str15 = str;
                    str16 = str17;
                    arrayList.remove(19);
                    arrayList.add(19, new StepBean(list.get(i4).getSportStep(), "09:30", "10:00"));
                    str13 = str15;
                    str14 = str16;
                    i2 = 0;
                    str2 = str21;
                    str3 = str18;
                    String str79222222222 = str28;
                    str4 = str19;
                    str5 = str20;
                    str6 = str22;
                    str7 = str23;
                    str8 = str24;
                    str9 = str25;
                    str10 = str26;
                    str11 = str27;
                    str12 = str79222222222;
                    break;
                case 20:
                    str15 = str;
                    str16 = str17;
                    arrayList.remove(20);
                    arrayList.add(20, new StepBean(list.get(i4).getSportStep(), "10:00", "10:30"));
                    str13 = str15;
                    str14 = str16;
                    i2 = 0;
                    str2 = str21;
                    str3 = str18;
                    String str792222222222 = str28;
                    str4 = str19;
                    str5 = str20;
                    str6 = str22;
                    str7 = str23;
                    str8 = str24;
                    str9 = str25;
                    str10 = str26;
                    str11 = str27;
                    str12 = str792222222222;
                    break;
                case 21:
                    str15 = str;
                    str16 = str17;
                    arrayList.remove(21);
                    arrayList.add(21, new StepBean(list.get(i4).getSportStep(), "10:30", "11:00"));
                    str13 = str15;
                    str14 = str16;
                    i2 = 0;
                    str2 = str21;
                    str3 = str18;
                    String str7922222222222 = str28;
                    str4 = str19;
                    str5 = str20;
                    str6 = str22;
                    str7 = str23;
                    str8 = str24;
                    str9 = str25;
                    str10 = str26;
                    str11 = str27;
                    str12 = str7922222222222;
                    break;
                case 22:
                    str15 = str;
                    str16 = str17;
                    arrayList.remove(22);
                    arrayList.add(22, new StepBean(list.get(i4).getSportStep(), "11:00", "11:30"));
                    str13 = str15;
                    str14 = str16;
                    i2 = 0;
                    str2 = str21;
                    str3 = str18;
                    String str79222222222222 = str28;
                    str4 = str19;
                    str5 = str20;
                    str6 = str22;
                    str7 = str23;
                    str8 = str24;
                    str9 = str25;
                    str10 = str26;
                    str11 = str27;
                    str12 = str79222222222222;
                    break;
                case 23:
                    str15 = str;
                    str16 = str17;
                    arrayList.remove(23);
                    arrayList.add(23, new StepBean(list.get(i4).getSportStep(), "11:30", "12:00"));
                    str13 = str15;
                    str14 = str16;
                    i2 = 0;
                    str2 = str21;
                    str3 = str18;
                    String str792222222222222 = str28;
                    str4 = str19;
                    str5 = str20;
                    str6 = str22;
                    str7 = str23;
                    str8 = str24;
                    str9 = str25;
                    str10 = str26;
                    str11 = str27;
                    str12 = str792222222222222;
                    break;
                case 24:
                    str15 = str;
                    str16 = str17;
                    arrayList.remove(24);
                    arrayList.add(24, new StepBean(list.get(i4).getSportStep(), "12:00", "12:30"));
                    str13 = str15;
                    str14 = str16;
                    i2 = 0;
                    str2 = str21;
                    str3 = str18;
                    String str7922222222222222 = str28;
                    str4 = str19;
                    str5 = str20;
                    str6 = str22;
                    str7 = str23;
                    str8 = str24;
                    str9 = str25;
                    str10 = str26;
                    str11 = str27;
                    str12 = str7922222222222222;
                    break;
                case 25:
                    str15 = str;
                    str16 = str17;
                    arrayList.remove(25);
                    arrayList.add(25, new StepBean(list.get(i4).getSportStep(), "12:30", "13:00"));
                    str13 = str15;
                    str14 = str16;
                    i2 = 0;
                    str2 = str21;
                    str3 = str18;
                    String str79222222222222222 = str28;
                    str4 = str19;
                    str5 = str20;
                    str6 = str22;
                    str7 = str23;
                    str8 = str24;
                    str9 = str25;
                    str10 = str26;
                    str11 = str27;
                    str12 = str79222222222222222;
                    break;
                case 26:
                    str15 = str;
                    str16 = str17;
                    arrayList.remove(26);
                    arrayList.add(26, new StepBean(list.get(i4).getSportStep(), "13:00", "13:30"));
                    str13 = str15;
                    str14 = str16;
                    i2 = 0;
                    str2 = str21;
                    str3 = str18;
                    String str792222222222222222 = str28;
                    str4 = str19;
                    str5 = str20;
                    str6 = str22;
                    str7 = str23;
                    str8 = str24;
                    str9 = str25;
                    str10 = str26;
                    str11 = str27;
                    str12 = str792222222222222222;
                    break;
                case 27:
                    str15 = str;
                    str16 = str17;
                    arrayList.remove(27);
                    arrayList.add(27, new StepBean(list.get(i4).getSportStep(), "13:30", "14:00"));
                    str13 = str15;
                    str14 = str16;
                    i2 = 0;
                    str2 = str21;
                    str3 = str18;
                    String str7922222222222222222 = str28;
                    str4 = str19;
                    str5 = str20;
                    str6 = str22;
                    str7 = str23;
                    str8 = str24;
                    str9 = str25;
                    str10 = str26;
                    str11 = str27;
                    str12 = str7922222222222222222;
                    break;
                case 28:
                    str15 = str;
                    str16 = str17;
                    arrayList.remove(28);
                    arrayList.add(28, new StepBean(list.get(i4).getSportStep(), "14:00", "14:30"));
                    str13 = str15;
                    str14 = str16;
                    i2 = 0;
                    str2 = str21;
                    str3 = str18;
                    String str79222222222222222222 = str28;
                    str4 = str19;
                    str5 = str20;
                    str6 = str22;
                    str7 = str23;
                    str8 = str24;
                    str9 = str25;
                    str10 = str26;
                    str11 = str27;
                    str12 = str79222222222222222222;
                    break;
                case 29:
                    str15 = str;
                    str16 = str17;
                    arrayList.remove(29);
                    arrayList.add(29, new StepBean(list.get(i4).getSportStep(), "14:30", "15:00"));
                    str13 = str15;
                    str14 = str16;
                    i2 = 0;
                    str2 = str21;
                    str3 = str18;
                    String str792222222222222222222 = str28;
                    str4 = str19;
                    str5 = str20;
                    str6 = str22;
                    str7 = str23;
                    str8 = str24;
                    str9 = str25;
                    str10 = str26;
                    str11 = str27;
                    str12 = str792222222222222222222;
                    break;
                case 30:
                    str15 = str;
                    str16 = str17;
                    arrayList.remove(30);
                    arrayList.add(30, new StepBean(list.get(i4).getSportStep(), "15:00", "15:30"));
                    str13 = str15;
                    str14 = str16;
                    i2 = 0;
                    str2 = str21;
                    str3 = str18;
                    String str7922222222222222222222 = str28;
                    str4 = str19;
                    str5 = str20;
                    str6 = str22;
                    str7 = str23;
                    str8 = str24;
                    str9 = str25;
                    str10 = str26;
                    str11 = str27;
                    str12 = str7922222222222222222222;
                    break;
                case 31:
                    str15 = str;
                    str16 = str17;
                    arrayList.remove(31);
                    arrayList.add(31, new StepBean(list.get(i4).getSportStep(), "15:30", "16:00"));
                    str13 = str15;
                    str14 = str16;
                    i2 = 0;
                    str2 = str21;
                    str3 = str18;
                    String str79222222222222222222222 = str28;
                    str4 = str19;
                    str5 = str20;
                    str6 = str22;
                    str7 = str23;
                    str8 = str24;
                    str9 = str25;
                    str10 = str26;
                    str11 = str27;
                    str12 = str79222222222222222222222;
                    break;
                case ' ':
                    str15 = str;
                    str16 = str17;
                    arrayList.remove(32);
                    arrayList.add(32, new StepBean(list.get(i4).getSportStep(), "16:00", "16:30"));
                    str13 = str15;
                    str14 = str16;
                    i2 = 0;
                    str2 = str21;
                    str3 = str18;
                    String str792222222222222222222222 = str28;
                    str4 = str19;
                    str5 = str20;
                    str6 = str22;
                    str7 = str23;
                    str8 = str24;
                    str9 = str25;
                    str10 = str26;
                    str11 = str27;
                    str12 = str792222222222222222222222;
                    break;
                case '!':
                    str15 = str;
                    str16 = str17;
                    arrayList.remove(33);
                    arrayList.add(33, new StepBean(list.get(i4).getSportStep(), "16:30", "17:00"));
                    str13 = str15;
                    str14 = str16;
                    i2 = 0;
                    str2 = str21;
                    str3 = str18;
                    String str7922222222222222222222222 = str28;
                    str4 = str19;
                    str5 = str20;
                    str6 = str22;
                    str7 = str23;
                    str8 = str24;
                    str9 = str25;
                    str10 = str26;
                    str11 = str27;
                    str12 = str7922222222222222222222222;
                    break;
                case '\"':
                    str15 = str;
                    str16 = str17;
                    arrayList.remove(34);
                    arrayList.add(34, new StepBean(list.get(i4).getSportStep(), "17:00", "17:30"));
                    str13 = str15;
                    str14 = str16;
                    i2 = 0;
                    str2 = str21;
                    str3 = str18;
                    String str79222222222222222222222222 = str28;
                    str4 = str19;
                    str5 = str20;
                    str6 = str22;
                    str7 = str23;
                    str8 = str24;
                    str9 = str25;
                    str10 = str26;
                    str11 = str27;
                    str12 = str79222222222222222222222222;
                    break;
                case '#':
                    str15 = str;
                    str16 = str17;
                    arrayList.remove(35);
                    arrayList.add(35, new StepBean(list.get(i4).getSportStep(), "17:30", "18:00"));
                    str13 = str15;
                    str14 = str16;
                    i2 = 0;
                    str2 = str21;
                    str3 = str18;
                    String str792222222222222222222222222 = str28;
                    str4 = str19;
                    str5 = str20;
                    str6 = str22;
                    str7 = str23;
                    str8 = str24;
                    str9 = str25;
                    str10 = str26;
                    str11 = str27;
                    str12 = str792222222222222222222222222;
                    break;
                case '$':
                    str15 = str;
                    str16 = str17;
                    arrayList.remove(36);
                    arrayList.add(36, new StepBean(list.get(i4).getSportStep(), "18:00", "18:30"));
                    str13 = str15;
                    str14 = str16;
                    i2 = 0;
                    str2 = str21;
                    str3 = str18;
                    String str7922222222222222222222222222 = str28;
                    str4 = str19;
                    str5 = str20;
                    str6 = str22;
                    str7 = str23;
                    str8 = str24;
                    str9 = str25;
                    str10 = str26;
                    str11 = str27;
                    str12 = str7922222222222222222222222222;
                    break;
                case '%':
                    str15 = str;
                    str16 = str17;
                    arrayList.remove(37);
                    arrayList.add(37, new StepBean(list.get(i4).getSportStep(), "18:30", "19:00"));
                    str13 = str15;
                    str14 = str16;
                    i2 = 0;
                    str2 = str21;
                    str3 = str18;
                    String str79222222222222222222222222222 = str28;
                    str4 = str19;
                    str5 = str20;
                    str6 = str22;
                    str7 = str23;
                    str8 = str24;
                    str9 = str25;
                    str10 = str26;
                    str11 = str27;
                    str12 = str79222222222222222222222222222;
                    break;
                case '&':
                    str15 = str;
                    str16 = str17;
                    arrayList.remove(38);
                    arrayList.add(38, new StepBean(list.get(i4).getSportStep(), "19:00", "19:30"));
                    str13 = str15;
                    str14 = str16;
                    i2 = 0;
                    str2 = str21;
                    str3 = str18;
                    String str792222222222222222222222222222 = str28;
                    str4 = str19;
                    str5 = str20;
                    str6 = str22;
                    str7 = str23;
                    str8 = str24;
                    str9 = str25;
                    str10 = str26;
                    str11 = str27;
                    str12 = str792222222222222222222222222222;
                    break;
                case '\'':
                    str15 = str;
                    str16 = str17;
                    arrayList.remove(39);
                    arrayList.add(39, new StepBean(list.get(i4).getSportStep(), "19:30", "20:00"));
                    str13 = str15;
                    str14 = str16;
                    i2 = 0;
                    str2 = str21;
                    str3 = str18;
                    String str7922222222222222222222222222222 = str28;
                    str4 = str19;
                    str5 = str20;
                    str6 = str22;
                    str7 = str23;
                    str8 = str24;
                    str9 = str25;
                    str10 = str26;
                    str11 = str27;
                    str12 = str7922222222222222222222222222222;
                    break;
                case '(':
                    str15 = str;
                    str16 = str17;
                    arrayList.remove(40);
                    arrayList.add(40, new StepBean(list.get(i4).getSportStep(), "20:00", "20:30"));
                    str13 = str15;
                    str14 = str16;
                    i2 = 0;
                    str2 = str21;
                    str3 = str18;
                    String str79222222222222222222222222222222 = str28;
                    str4 = str19;
                    str5 = str20;
                    str6 = str22;
                    str7 = str23;
                    str8 = str24;
                    str9 = str25;
                    str10 = str26;
                    str11 = str27;
                    str12 = str79222222222222222222222222222222;
                    break;
                case ')':
                    str15 = str;
                    str16 = str17;
                    arrayList.remove(41);
                    arrayList.add(41, new StepBean(list.get(i4).getSportStep(), "20:30", "21:00"));
                    str13 = str15;
                    str14 = str16;
                    i2 = 0;
                    str2 = str21;
                    str3 = str18;
                    String str792222222222222222222222222222222 = str28;
                    str4 = str19;
                    str5 = str20;
                    str6 = str22;
                    str7 = str23;
                    str8 = str24;
                    str9 = str25;
                    str10 = str26;
                    str11 = str27;
                    str12 = str792222222222222222222222222222222;
                    break;
                case '*':
                    str15 = str;
                    str16 = str17;
                    arrayList.remove(42);
                    arrayList.add(42, new StepBean(list.get(i4).getSportStep(), "21:00", "21:30"));
                    str13 = str15;
                    str14 = str16;
                    i2 = 0;
                    str2 = str21;
                    str3 = str18;
                    String str7922222222222222222222222222222222 = str28;
                    str4 = str19;
                    str5 = str20;
                    str6 = str22;
                    str7 = str23;
                    str8 = str24;
                    str9 = str25;
                    str10 = str26;
                    str11 = str27;
                    str12 = str7922222222222222222222222222222222;
                    break;
                case '+':
                    str15 = str;
                    str16 = str17;
                    arrayList.remove(43);
                    arrayList.add(43, new StepBean(list.get(i4).getSportStep(), "21:30", "22:00"));
                    str13 = str15;
                    str14 = str16;
                    i2 = 0;
                    str2 = str21;
                    str3 = str18;
                    String str79222222222222222222222222222222222 = str28;
                    str4 = str19;
                    str5 = str20;
                    str6 = str22;
                    str7 = str23;
                    str8 = str24;
                    str9 = str25;
                    str10 = str26;
                    str11 = str27;
                    str12 = str79222222222222222222222222222222222;
                    break;
                case ',':
                    str15 = str;
                    str16 = str17;
                    arrayList.remove(44);
                    arrayList.add(44, new StepBean(list.get(i4).getSportStep(), "22:00", "22:30"));
                    str13 = str15;
                    str14 = str16;
                    i2 = 0;
                    str2 = str21;
                    str3 = str18;
                    String str792222222222222222222222222222222222 = str28;
                    str4 = str19;
                    str5 = str20;
                    str6 = str22;
                    str7 = str23;
                    str8 = str24;
                    str9 = str25;
                    str10 = str26;
                    str11 = str27;
                    str12 = str792222222222222222222222222222222222;
                    break;
                case '-':
                    str15 = str;
                    str16 = str17;
                    arrayList.remove(45);
                    arrayList.add(45, new StepBean(list.get(i4).getSportStep(), "22:30", "23:00"));
                    str13 = str15;
                    str14 = str16;
                    i2 = 0;
                    str2 = str21;
                    str3 = str18;
                    String str7922222222222222222222222222222222222 = str28;
                    str4 = str19;
                    str5 = str20;
                    str6 = str22;
                    str7 = str23;
                    str8 = str24;
                    str9 = str25;
                    str10 = str26;
                    str11 = str27;
                    str12 = str7922222222222222222222222222222222222;
                    break;
                case '.':
                    str15 = str;
                    str16 = str17;
                    arrayList.remove(46);
                    arrayList.add(46, new StepBean(list.get(i4).getSportStep(), "23:00", "23:30"));
                    str13 = str15;
                    str14 = str16;
                    i2 = 0;
                    str2 = str21;
                    str3 = str18;
                    String str79222222222222222222222222222222222222 = str28;
                    str4 = str19;
                    str5 = str20;
                    str6 = str22;
                    str7 = str23;
                    str8 = str24;
                    str9 = str25;
                    str10 = str26;
                    str11 = str27;
                    str12 = str79222222222222222222222222222222222222;
                    break;
                case '/':
                    arrayList.remove(47);
                    str15 = str;
                    str16 = str17;
                    arrayList.add(47, new StepBean(list.get(i4).getSportStep(), "23:30", "00:00"));
                    str13 = str15;
                    str14 = str16;
                    i2 = 0;
                    str2 = str21;
                    str3 = str18;
                    String str792222222222222222222222222222222222222 = str28;
                    str4 = str19;
                    str5 = str20;
                    str6 = str22;
                    str7 = str23;
                    str8 = str24;
                    str9 = str25;
                    str10 = str26;
                    str11 = str27;
                    str12 = str792222222222222222222222222222222222222;
                    break;
                default:
                    str2 = str21;
                    i2 = 0;
                    str3 = str18;
                    String str81 = str28;
                    str4 = str19;
                    str5 = str20;
                    str6 = str22;
                    str7 = str23;
                    str8 = str24;
                    str9 = str25;
                    str10 = str26;
                    str11 = str27;
                    str12 = str81;
                    String str82 = str17;
                    str13 = str;
                    str14 = str82;
                    break;
            }
            i4++;
            i3 = i2;
            str18 = str3;
            str29 = str13;
            str21 = str2;
            list = mRunningHisListBean;
            str17 = str14;
            String str83 = str6;
            str20 = str5;
            str19 = str4;
            str28 = str12;
            str27 = str11;
            str26 = str10;
            str25 = str9;
            str24 = str8;
            str23 = str7;
            str22 = str83;
        }
        while (i3 < arrayList.size()) {
            StepBean stepBean2 = (StepBean) arrayList.get(i3);
            arrayList.size();
            Math.random();
            dayValues.add(new BarEntry(i3, stepBean2.getStep()));
            stepBean2.setBeginhhmm(stepBean2.getBeginhhmm() + "-" + stepBean2.getEndhhmm());
            i3++;
        }
        MyMarkerView myMarkerView = new MyMarkerView(context, R.layout.layout_for_custom_marker_view, null, null, null, arrayList);
        myMarkerView.setChartView(mBarChart);
        mBarChart.setMarker(myMarkerView, null, null, null, arrayList, Chart.MarkerLabel.STEP_CHART);
    }

    private static void setWeekData(GradualBarChart mBarChart, List<RunningHisListBean> mRunningHisListBean, String mToDay, Context context) {
        weekValues = new ArrayList();
        List<StepBean> arrayList = new ArrayList<>();
        ArrayList<String> arrayListPastDay = pastDay(mToDay);
        for (int i2 = 0; i2 < arrayListPastDay.size(); i2++) {
            arrayList.add(new StepBean(0.0f, TimeStampUtils.dateForStringDate(TimeStampUtils.stringForDateDay(arrayListPastDay.get(i2))), ""));
        }
        for (int i3 = 0; i3 < arrayListPastDay.size(); i3++) {
            for (int i4 = 0; i4 < mRunningHisListBean.size(); i4++) {
                if (mRunningHisListBean.get(i4).getSportStartTime().equals(TimeStampUtils.dateForStringDates(TimeStampUtils.stringForDateDay(arrayListPastDay.get(i3))))) {
                    arrayList.remove(i3);
                    arrayList.add(i3, new StepBean(mRunningHisListBean.get(i4).getSportStep(), TimeStampUtils.dateForStringDate(TimeStampUtils.stringForDateDay(arrayListPastDay.get(i3))), ""));
                }
            }
        }
        for (int i5 = 0; i5 < arrayList.size(); i5++) {
            if (arrayList.get(i5).getStep() > mWeekMaxStepNum) {
                mWeekMaxStepNum = (int) arrayList.get(i5).getStep();
            }
            setyAxisMax(mWeekMaxStepNum);
            weekValues.add(new BarEntry(i5, arrayList.get(i5).getStep()));
        }
        MyMarkerView myMarkerView = new MyMarkerView(context, R.layout.layout_for_custom_marker_view, null, null, null, arrayList);
        myMarkerView.setChartView(mBarChart);
        mBarChart.setMarker(myMarkerView, null, null, null, arrayList, Chart.MarkerLabel.STEP_CHART);
    }

    private static void setMonthData(GradualBarChart mBarChart, List<RunningHisListBean> mRunningHisListBean, String mToDay, Context context) {
        monthValues = new ArrayList();
        List<StepBean> arrayList = new ArrayList<>();
        ArrayList<String> arrayListPastThirtyDay = pastThirtyDay(mToDay);
        for (int i2 = 0; i2 < arrayListPastThirtyDay.size(); i2++) {
            arrayList.add(new StepBean(0.0f, TimeStampUtils.dateForStringDate(TimeStampUtils.stringForDateDay(arrayListPastThirtyDay.get(i2))), ""));
        }
        for (int i3 = 0; i3 < arrayListPastThirtyDay.size(); i3++) {
            for (int i4 = 0; i4 < mRunningHisListBean.size(); i4++) {
                if (mRunningHisListBean.get(i4).getSportStartTime().equals(TimeStampUtils.dateForStringDates(TimeStampUtils.stringForDateDay(arrayListPastThirtyDay.get(i3))))) {
                    arrayList.remove(i3);
                    arrayList.add(i3, new StepBean(mRunningHisListBean.get(i4).getSportStep(), TimeStampUtils.dateForStringDate(TimeStampUtils.stringForDateDay(arrayListPastThirtyDay.get(i3))), ""));
                }
            }
        }
        for (int i5 = 0; i5 < arrayList.size(); i5++) {
            if (arrayList.get(i5).getStep() > mMonthMaxStepNum) {
                mMonthMaxStepNum = (int) arrayList.get(i5).getStep();
            }
            setyAxisMax(mMonthMaxStepNum);
            monthValues.add(new BarEntry(i5, arrayList.get(i5).getStep()));
        }
        MyMarkerView myMarkerView = new MyMarkerView(context, R.layout.layout_for_custom_marker_view, null, null, null, arrayList);
        myMarkerView.setChartView(mBarChart);
        mBarChart.setMarker(myMarkerView, null, null, null, arrayList, Chart.MarkerLabel.STEP_CHART);
    }

    public static void setyAxisMax(int yMaximum) {
        yAxis.setAxisMaximum(yMaximum + 1000);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void setBarChart(GradualBarChart mBarChart, List<BarEntry> values, Context context) {
        if (mBarChart.getData() != null && ((BarData) mBarChart.getData()).getDataSetCount() > 0) {
            ((BarDataSet) ((BarData) mBarChart.getData()).getDataSetByIndex(0)).setValues(values);
            ((BarData) mBarChart.getData()).notifyDataChanged();
            mBarChart.notifyDataSetChanged();
        } else {
            BarDataSet barDataSet = new BarDataSet(values, "Data Set");
            if (Constant.isHealthband()) {
                Fill fill = new Fill();
                fill.setType(Fill.Type.LINEAR_GRADIENT);
                fill.setGradientColors(context.getResources().getColor(R.color.step_chart_start_bg), context.getResources().getColor(R.color.step_chart_end_bg));
                ArrayList arrayList = new ArrayList();
                arrayList.add(fill);
                barDataSet.setFills(arrayList);
            } else {
                barDataSet.setColors(context.getResources().getColor(R.color.step_chart_start_bg));
            }
            barDataSet.setDrawValues(false);
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(barDataSet);
            mBarChart.setData(new BarData(arrayList2));
            mBarChart.setFitBars(true);
        }
        mBarChart.invalidate();
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
}
