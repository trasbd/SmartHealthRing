package com.yucheng.smarthealthpro.home.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.viewpager.widget.ViewPager;

/* loaded from: classes5.dex */
public class NoScrollViewPager extends ViewPager {
    private boolean noScroll;

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.noScroll = true;
    }

    public NoScrollViewPager(Context context) {
        super(context);
        this.noScroll = true;
    }

    public void setNoScroll(boolean noScroll) {
        this.noScroll = noScroll;
    }

    @Override // android.view.View
    public void scrollTo(int x, int y) {
        super.scrollTo(x, y);
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent arg0) {
        if (this.noScroll) {
            return false;
        }
        return super.onTouchEvent(arg0);
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        if (this.noScroll) {
            return false;
        }
        return super.onInterceptTouchEvent(arg0);
    }

    @Override // androidx.viewpager.widget.ViewPager
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item, smoothScroll);
    }

    @Override // androidx.viewpager.widget.ViewPager
    public void setCurrentItem(int item) {
        super.setCurrentItem(item);
    }
}
