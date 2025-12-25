package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.haibin.calendarview.CalendarView;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;

/* loaded from: classes4.dex */
public final class ActivityFemalePhysiologicalCycleBinding implements ViewBinding {
    public final CalendarView calendarView;
    public final ImageView ivLastMonth;
    public final ImageView ivNextMonth;
    public final NavigationBar navigationbar;
    private final LinearLayout rootView;
    public final TextView tvBackToday;
    public final TextView tvMonth;

    private ActivityFemalePhysiologicalCycleBinding(LinearLayout rootView, CalendarView calendarView, ImageView ivLastMonth, ImageView ivNextMonth, NavigationBar navigationbar, TextView tvBackToday, TextView tvMonth) {
        this.rootView = rootView;
        this.calendarView = calendarView;
        this.ivLastMonth = ivLastMonth;
        this.ivNextMonth = ivNextMonth;
        this.navigationbar = navigationbar;
        this.tvBackToday = tvBackToday;
        this.tvMonth = tvMonth;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityFemalePhysiologicalCycleBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityFemalePhysiologicalCycleBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_female_physiological_cycle, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityFemalePhysiologicalCycleBinding bind(View rootView) {
        int i2 = R.id.calendarView;
        CalendarView calendarView = (CalendarView) ViewBindings.findChildViewById(rootView, i2);
        if (calendarView != null) {
            i2 = R.id.iv_last_month;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i2);
            if (imageView != null) {
                i2 = R.id.iv_next_month;
                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                if (imageView2 != null) {
                    i2 = R.id.navigationbar;
                    NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
                    if (navigationBar != null) {
                        i2 = R.id.tv_back_today;
                        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                        if (textView != null) {
                            i2 = R.id.tv_month;
                            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                            if (textView2 != null) {
                                return new ActivityFemalePhysiologicalCycleBinding((LinearLayout) rootView, calendarView, imageView, imageView2, navigationBar, textView, textView2);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
