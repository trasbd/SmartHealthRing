package com.yucheng.smarthealthpro.customchart.components;

import android.content.Context;
import android.graphics.Canvas;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import com.yucheng.smarthealthpro.customchart.charts.Chart;
import com.yucheng.smarthealthpro.customchart.data.Entry;
import com.yucheng.smarthealthpro.customchart.highlight.Highlight;
import com.yucheng.smarthealthpro.customchart.temperature.TemperBean;
import com.yucheng.smarthealthpro.customchart.utils.MPPointF;
import com.yucheng.smarthealthpro.home.activity.running.bean.StepBean;
import com.yucheng.smarthealthpro.home.activity.sleep.bean.SleepDayInfo;
import java.lang.ref.WeakReference;
import java.util.List;

/* loaded from: classes4.dex */
public abstract class MarkerView extends RelativeLayout implements IMarker {
    private MPPointF mOffset;
    private MPPointF mOffset2;
    private WeakReference<Chart> mWeakChart;

    public MarkerView(Context context, int layoutResource) {
        super(context);
        this.mOffset = new MPPointF();
        this.mOffset2 = new MPPointF();
        setupLayoutResource(layoutResource);
    }

    private void setupLayoutResource(int layoutResource) {
        View viewInflate = LayoutInflater.from(getContext()).inflate(layoutResource, this);
        viewInflate.setLayoutParams(new RelativeLayout.LayoutParams(-2, -2));
        viewInflate.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        viewInflate.layout(0, 0, viewInflate.getMeasuredWidth(), viewInflate.getMeasuredHeight());
    }

    public void setOffset(MPPointF offset) {
        this.mOffset = offset;
        if (offset == null) {
            this.mOffset = new MPPointF();
        }
    }

    public void setOffset(float offsetX, float offsetY) {
        this.mOffset.x = offsetX;
        this.mOffset.y = offsetY;
    }

    public void refreshContentDayPressure(Entry e2, Highlight highlight, List<TemperBean> temperBeans) {
        measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        layout(0, 0, getMeasuredWidth(), getMeasuredHeight());
    }

    public void refreshContentWeekMonthPressure(Entry e2, Highlight highlight, List<TemperBean> temperBeans) {
        measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        layout(0, 0, getMeasuredWidth(), getMeasuredHeight());
    }

    public MPPointF getOffset() {
        return this.mOffset;
    }

    public void setChartView(Chart chart) {
        this.mWeakChart = new WeakReference<>(chart);
    }

    public Chart getChartView() {
        WeakReference<Chart> weakReference = this.mWeakChart;
        if (weakReference == null) {
            return null;
        }
        return weakReference.get();
    }

    @Override // com.yucheng.smarthealthpro.customchart.components.IMarker
    public MPPointF getOffsetForDrawingAtPoint(float posX, float posY) {
        MPPointF offset = getOffset();
        this.mOffset2.x = offset.x;
        this.mOffset2.y = offset.y;
        Chart chartView = getChartView();
        float width = getWidth();
        float height = getHeight();
        if (this.mOffset2.x + posX < 0.0f) {
            this.mOffset2.x = -posX;
        } else if (chartView != null && posX + width + this.mOffset2.x > chartView.getWidth()) {
            this.mOffset2.x = (chartView.getWidth() - posX) - width;
        }
        if (this.mOffset2.y + posY < 0.0f) {
            this.mOffset2.y = -posY;
        } else if (chartView != null && posY + height + this.mOffset2.y > chartView.getHeight()) {
            this.mOffset2.y = (chartView.getHeight() - posY) - height;
        }
        return this.mOffset2;
    }

    public void refreshContent(Entry e2, Highlight highlight) {
        measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        layout(0, 0, getMeasuredWidth(), getMeasuredHeight());
    }

    public void draw(Canvas canvas, float posX, float posY) {
        MPPointF offsetForDrawingAtPoint = getOffsetForDrawingAtPoint(posX, posY);
        int iSave = canvas.save();
        canvas.translate(posX + offsetForDrawingAtPoint.x, posY + offsetForDrawingAtPoint.y);
        draw(canvas);
        canvas.restoreToCount(iSave);
    }

    public void refreshContentTemp(Entry e2, Highlight highlight, List<TemperBean> temperBeans) {
        measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        layout(0, 0, getMeasuredWidth(), getMeasuredHeight());
    }

    public void refreshContentTempWeekMohth(Entry e2, Highlight highlight, List<TemperBean> temperBeans) {
        measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        layout(0, 0, getMeasuredWidth(), getMeasuredHeight());
    }

    public void refreshContentDayBloodOxygen(Entry e2, Highlight highlight, List<TemperBean> temperBeans) {
        measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        layout(0, 0, getMeasuredWidth(), getMeasuredHeight());
    }

    public void refreshContentWeekMonthBloodOxygen(Entry e2, Highlight highlight, List<TemperBean> temperBeans) {
        measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        layout(0, 0, getMeasuredWidth(), getMeasuredHeight());
    }

    public void refreshContentDayBloodSugar(Entry e2, Highlight highlight, List<TemperBean> temperBeans) {
        measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        layout(0, 0, getMeasuredWidth(), getMeasuredHeight());
    }

    public void refreshContentWeekMonthBloodSugar(Entry e2, Highlight highlight, List<TemperBean> temperBeans) {
        measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        layout(0, 0, getMeasuredWidth(), getMeasuredHeight());
    }

    public void refreshContentDayUricAcid(Entry e2, Highlight highlight, List<TemperBean> temperBeans) {
        measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        layout(0, 0, getMeasuredWidth(), getMeasuredHeight());
    }

    public void refreshContentWeekMonthUricAcid(Entry e2, Highlight highlight, List<TemperBean> temperBeans) {
        measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        layout(0, 0, getMeasuredWidth(), getMeasuredHeight());
    }

    public void refreshContentDayKetone(Entry e2, Highlight highlight, List<TemperBean> temperBeans) {
        measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        layout(0, 0, getMeasuredWidth(), getMeasuredHeight());
    }

    public void refreshContentWeekMonthKetone(Entry e2, Highlight highlight, List<TemperBean> temperBeans) {
        measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        layout(0, 0, getMeasuredWidth(), getMeasuredHeight());
    }

    public void refreshContentDayHeartRate(Entry e2, Highlight highlight, List<TemperBean> temperBeans) {
        measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        layout(0, 0, getMeasuredWidth(), getMeasuredHeight());
    }

    public void refreshContentWeekMonthHeartRate(Entry e2, Highlight highlight, List<TemperBean> temperBeans) {
        measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        layout(0, 0, getMeasuredWidth(), getMeasuredHeight());
    }

    public void refreshContentDayHRV(Entry e2, Highlight highlight, List<TemperBean> temperBeans) {
        measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        layout(0, 0, getMeasuredWidth(), getMeasuredHeight());
    }

    public void refreshContentWeekMonthHRV(Entry e2, Highlight highlight, List<TemperBean> temperBeans) {
        measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        layout(0, 0, getMeasuredWidth(), getMeasuredHeight());
    }

    public void refreshContentDayRespiratoryRate(Entry e2, Highlight highlight, List<TemperBean> temperBeans) {
        measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        layout(0, 0, getMeasuredWidth(), getMeasuredHeight());
    }

    public void refreshContentWeekMonthRespiratoryRate(Entry e2, Highlight highlight, List<TemperBean> temperBeans) {
        measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        layout(0, 0, getMeasuredWidth(), getMeasuredHeight());
    }

    public void refreshContentDayBloodPressure(Entry e2, Highlight highlight, List<TemperBean> temperBeans) {
        measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        layout(0, 0, getMeasuredWidth(), getMeasuredHeight());
    }

    public void refreshContentWeekMonthBloodPressure(Entry e2, Highlight highlight, List<TemperBean> temperBeans) {
        measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        layout(0, 0, getMeasuredWidth(), getMeasuredHeight());
    }

    public void refreshContentStep(Entry e2, Highlight highlight, List<StepBean> mStepBean) {
        measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        layout(0, 0, getMeasuredWidth(), getMeasuredHeight());
    }

    public void refreshContentSleep(Entry e2, Highlight highlight, List<SleepDayInfo> mSleepInfo) {
        measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        layout(0, 0, getMeasuredWidth(), getMeasuredHeight());
    }

    public void refreshContentSleepWeekMonth(Entry e2, Highlight highlight, Chart.MarkerLabel markerLabel) {
        measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        layout(0, 0, getMeasuredWidth(), getMeasuredHeight());
    }
}
