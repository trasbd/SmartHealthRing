package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;

/* loaded from: classes4.dex */
public final class ActivityAlarmClockBinding implements ViewBinding {
    public final ImageView ivAddAlarmClock;
    public final NavigationBar navigationbar;
    public final RecyclerView recycleView;
    private final RelativeLayout rootView;

    private ActivityAlarmClockBinding(RelativeLayout rootView, ImageView ivAddAlarmClock, NavigationBar navigationbar, RecyclerView recycleView) {
        this.rootView = rootView;
        this.ivAddAlarmClock = ivAddAlarmClock;
        this.navigationbar = navigationbar;
        this.recycleView = recycleView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ActivityAlarmClockBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityAlarmClockBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_alarm_clock, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityAlarmClockBinding bind(View rootView) {
        int i2 = R.id.iv_add_alarm_clock;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i2);
        if (imageView != null) {
            i2 = R.id.navigationbar;
            NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
            if (navigationBar != null) {
                i2 = R.id.recycle_view;
                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, i2);
                if (recyclerView != null) {
                    return new ActivityAlarmClockBinding((RelativeLayout) rootView, imageView, navigationBar, recyclerView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
