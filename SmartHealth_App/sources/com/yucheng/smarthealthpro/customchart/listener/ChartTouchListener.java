package com.yucheng.smarthealthpro.customchart.listener;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import com.yucheng.smarthealthpro.customchart.charts.Chart;
import com.yucheng.smarthealthpro.customchart.highlight.Highlight;

/* loaded from: classes4.dex */
public abstract class ChartTouchListener<T extends Chart<?>> extends GestureDetector.SimpleOnGestureListener implements View.OnTouchListener {
    protected static final int DRAG = 1;
    protected static final int NONE = 0;
    protected static final int PINCH_ZOOM = 4;
    protected static final int POST_ZOOM = 5;
    protected static final int ROTATE = 6;
    protected static final int X_ZOOM = 2;
    protected static final int Y_ZOOM = 3;
    protected T mChart;
    protected GestureDetector mGestureDetector;
    protected Highlight mLastHighlighted;
    protected ChartGesture mLastGesture = ChartGesture.NONE;
    protected int mTouchMode = 0;

    public enum ChartGesture {
        NONE,
        DRAG,
        X_ZOOM,
        Y_ZOOM,
        PINCH_ZOOM,
        ROTATE,
        SINGLE_TAP,
        DOUBLE_TAP,
        LONG_PRESS,
        FLING
    }

    public ChartTouchListener(T chart) {
        this.mChart = chart;
        this.mGestureDetector = new GestureDetector(chart.getContext(), this);
    }

    public void startAction(MotionEvent me2) {
        OnChartGestureListener onChartGestureListener = this.mChart.getOnChartGestureListener();
        if (onChartGestureListener != null) {
            onChartGestureListener.onChartGestureStart(me2, this.mLastGesture);
        }
    }

    public void endAction(MotionEvent me2) {
        OnChartGestureListener onChartGestureListener = this.mChart.getOnChartGestureListener();
        if (onChartGestureListener != null) {
            onChartGestureListener.onChartGestureEnd(me2, this.mLastGesture);
        }
    }

    public void setLastHighlighted(Highlight high) {
        this.mLastHighlighted = high;
    }

    public int getTouchMode() {
        return this.mTouchMode;
    }

    public ChartGesture getLastGesture() {
        return this.mLastGesture;
    }

    protected void performHighlight(Highlight h2, MotionEvent e2) {
        if (h2 == null || h2.equalTo(this.mLastHighlighted)) {
            this.mChart.highlightValue(null, true);
            this.mLastHighlighted = null;
        } else {
            this.mChart.highlightValue(h2, true);
            this.mLastHighlighted = h2;
        }
    }

    protected static float distance(float eventX, float startX, float eventY, float startY) {
        float f2 = eventX - startX;
        float f3 = eventY - startY;
        return (float) Math.sqrt((f2 * f2) + (f3 * f3));
    }
}
