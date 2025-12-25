package com.yucheng.smarthealthpro.sport.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;

/* loaded from: classes5.dex */
public class NoScrollSwipeRecyclerView extends SwipeRecyclerView {
    private float mDownPosX;
    private float mDownPosY;

    public NoScrollSwipeRecyclerView(Context context) {
        super(context);
        this.mDownPosX = 0.0f;
        this.mDownPosY = 0.0f;
    }

    public NoScrollSwipeRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mDownPosX = 0.0f;
        this.mDownPosY = 0.0f;
    }

    public NoScrollSwipeRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mDownPosX = 0.0f;
        this.mDownPosY = 0.0f;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent ev) {
        float x = ev.getX();
        float y = ev.getY();
        int action = ev.getAction();
        if (action == 0) {
            this.mDownPosX = x;
            this.mDownPosY = y;
        } else if (action == 2 && Math.abs(x - this.mDownPosX) < Math.abs(y - this.mDownPosY)) {
            return true;
        }
        return super.dispatchTouchEvent(ev);
    }
}
