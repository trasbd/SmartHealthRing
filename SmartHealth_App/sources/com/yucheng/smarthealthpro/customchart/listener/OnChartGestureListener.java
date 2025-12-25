package com.yucheng.smarthealthpro.customchart.listener;

import android.view.MotionEvent;
import com.yucheng.smarthealthpro.customchart.listener.ChartTouchListener;

/* loaded from: classes4.dex */
public interface OnChartGestureListener {
    void onChartDoubleTapped(MotionEvent me2);

    void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY);

    void onChartGestureEnd(MotionEvent me2, ChartTouchListener.ChartGesture lastPerformedGesture);

    void onChartGestureStart(MotionEvent me2, ChartTouchListener.ChartGesture lastPerformedGesture);

    void onChartLongPressed(MotionEvent me2);

    void onChartScale(MotionEvent me2, float scaleX, float scaleY);

    void onChartSingleTapped(MotionEvent me2);

    void onChartTranslate(MotionEvent me2, float dX, float dY);
}
