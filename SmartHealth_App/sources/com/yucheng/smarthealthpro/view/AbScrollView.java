package com.yucheng.smarthealthpro.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

/* loaded from: classes5.dex */
public class AbScrollView extends ScrollView {
    private static final int DEFAULT_POSITION = -1;
    private View inner;
    private Rect normal;
    private float xDistance;
    private float xLast;
    private float y;
    private float yDistance;
    private float yLast;

    private boolean isDefaultPosition(float position) {
        return position == -1.0f;
    }

    public AbScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.y = -1.0f;
        this.normal = new Rect();
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        if (getChildCount() > 0) {
            this.inner = getChildAt(0);
        }
        super.onFinishInflate();
    }

    @Override // android.widget.ScrollView, android.view.View
    public boolean onTouchEvent(MotionEvent ev) {
        if (this.inner == null) {
            return super.onTouchEvent(ev);
        }
        commOnTouchEvent(ev);
        return super.onTouchEvent(ev);
    }

    public void commOnTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        if (action == 0) {
            this.y = ev.getY();
            return;
        }
        if (action == 1) {
            if (isNeedAnimation()) {
                animation();
            }
            this.y = -1.0f;
            return;
        }
        if (action != 2) {
            return;
        }
        float f2 = this.y;
        float y = ev.getY();
        if (isDefaultPosition(this.y)) {
            f2 = y;
        }
        int i2 = (int) (f2 - y);
        scrollBy(0, i2);
        this.y = y;
        if (isNeedMove()) {
            if (this.normal.isEmpty()) {
                this.normal.set(this.inner.getLeft(), this.inner.getTop(), this.inner.getRight(), this.inner.getBottom());
            }
            View view = this.inner;
            view.layout(view.getLeft(), this.inner.getTop() - i2, this.inner.getRight(), this.inner.getBottom() - i2);
        }
    }

    public void animation() {
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, this.inner.getTop(), this.normal.top);
        translateAnimation.setDuration(200L);
        this.inner.startAnimation(translateAnimation);
        this.inner.layout(this.normal.left, this.normal.top, this.normal.right, this.normal.bottom);
        this.normal.setEmpty();
    }

    public boolean isNeedAnimation() {
        return !this.normal.isEmpty();
    }

    public boolean isNeedMove() {
        int measuredHeight = this.inner.getMeasuredHeight() - getHeight();
        int scrollY = getScrollY();
        return scrollY == 0 || scrollY == measuredHeight;
    }

    @Override // android.widget.ScrollView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        if (action == 0) {
            this.yDistance = 0.0f;
            this.xDistance = 0.0f;
            this.xLast = ev.getX();
            this.yLast = ev.getY();
        } else if (action == 2) {
            float x = ev.getX();
            float y = ev.getY();
            this.xDistance += Math.abs(x - this.xLast);
            float fAbs = this.yDistance + Math.abs(y - this.yLast);
            this.yDistance = fAbs;
            this.xLast = x;
            this.yLast = y;
            if (this.xDistance > fAbs) {
                return false;
            }
        }
        return super.onInterceptTouchEvent(ev);
    }
}
