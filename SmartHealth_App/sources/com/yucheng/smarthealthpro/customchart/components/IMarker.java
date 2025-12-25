package com.yucheng.smarthealthpro.customchart.components;

import android.graphics.Canvas;
import com.yucheng.smarthealthpro.customchart.charts.Chart;
import com.yucheng.smarthealthpro.customchart.data.Entry;
import com.yucheng.smarthealthpro.customchart.highlight.Highlight;
import com.yucheng.smarthealthpro.customchart.temperature.TemperBean;
import com.yucheng.smarthealthpro.customchart.utils.MPPointF;
import com.yucheng.smarthealthpro.home.activity.running.bean.StepBean;
import com.yucheng.smarthealthpro.home.activity.sleep.bean.SleepDayInfo;
import java.util.List;

/* loaded from: classes4.dex */
public interface IMarker {
    void draw(Canvas canvas, float posX, float posY);

    MPPointF getOffset();

    MPPointF getOffsetForDrawingAtPoint(float posX, float posY);

    void refreshContent(Entry e2, Highlight highlight);

    void refreshContentDayBloodOxygen(Entry e2, Highlight highlight, List<TemperBean> temperBeans);

    void refreshContentDayBloodPressure(Entry e2, Highlight highlight, List<TemperBean> temperBeans);

    void refreshContentDayBloodSugar(Entry e2, Highlight highlight, List<TemperBean> temperBeans);

    void refreshContentDayHRV(Entry e2, Highlight highlight, List<TemperBean> temperBeans);

    void refreshContentDayHeartRate(Entry e2, Highlight highlight, List<TemperBean> temperBeans);

    void refreshContentDayKetone(Entry e2, Highlight highlight, List<TemperBean> temperBeans);

    void refreshContentDayPressure(Entry e2, Highlight highlight, List<TemperBean> temperBeans);

    void refreshContentDayRespiratoryRate(Entry e2, Highlight highlight, List<TemperBean> temperBeans);

    void refreshContentDayUricAcid(Entry e2, Highlight highlight, List<TemperBean> temperBeans);

    void refreshContentSleep(Entry e2, Highlight highlight, List<SleepDayInfo> mSleepInfo);

    void refreshContentSleepWeekMonth(Entry e2, Highlight highlight, Chart.MarkerLabel markerLabel);

    void refreshContentStep(Entry e2, Highlight highlight, List<StepBean> mStepBean);

    void refreshContentTemp(Entry e2, Highlight highlight, List<TemperBean> temperBeans);

    void refreshContentTempWeekMohth(Entry e2, Highlight highlight, List<TemperBean> temperBeans);

    void refreshContentWeekMonthBloodOxygen(Entry e2, Highlight highlight, List<TemperBean> temperBeans);

    void refreshContentWeekMonthBloodPressure(Entry e2, Highlight highlight, List<TemperBean> temperBeans);

    void refreshContentWeekMonthBloodSugar(Entry e2, Highlight highlight, List<TemperBean> temperBeans);

    void refreshContentWeekMonthHRV(Entry e2, Highlight highlight, List<TemperBean> temperBeans);

    void refreshContentWeekMonthHeartRate(Entry e2, Highlight highlight, List<TemperBean> temperBeans);

    void refreshContentWeekMonthKetone(Entry e2, Highlight highlight, List<TemperBean> temperBeans);

    void refreshContentWeekMonthPressure(Entry e2, Highlight highlight, List<TemperBean> temperBeans);

    void refreshContentWeekMonthRespiratoryRate(Entry e2, Highlight highlight, List<TemperBean> temperBeans);

    void refreshContentWeekMonthUricAcid(Entry e2, Highlight highlight, List<TemperBean> temperBeans);
}
