package com.yucheng.smarthealthpro.customchart;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import androidx.core.widget.NestedScrollView;

/* loaded from: classes4.dex */
public class MyNestedScrollView extends NestedScrollView {
    private int downX;
    private int downY;
    private float mDownPosX;
    private float mDownPosY;
    private int mTouchSlop;
    private int moveX;
    private int moveY;

    public MyNestedScrollView(Context context) {
        super(context);
        this.mDownPosX = 0.0f;
        this.mDownPosY = 0.0f;
        this.mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public MyNestedScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mDownPosX = 0.0f;
        this.mDownPosY = 0.0f;
        this.mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public MyNestedScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mDownPosX = 0.0f;
        this.mDownPosY = 0.0f;
        this.mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override // androidx.core.widget.NestedScrollView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        float x = ev.getX();
        float y = ev.getY();
        int action = ev.getAction();
        if (action == 0) {
            this.mDownPosX = x;
            this.mDownPosY = y;
        } else if (action == 2 && Math.abs(x - this.mDownPosX) > Math.abs(y - this.mDownPosY)) {
            return false;
        }
        return super.onInterceptTouchEvent(ev);
    }
}
