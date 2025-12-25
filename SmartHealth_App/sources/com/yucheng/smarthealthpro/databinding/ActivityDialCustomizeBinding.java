package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;

/* loaded from: classes4.dex */
public final class ActivityDialCustomizeBinding implements ViewBinding {
    public final GridView activityDialCustomizeGv;
    public final NavigationBar navigationbar;
    private final LinearLayout rootView;
    public final SmartRefreshLayout smartRefreshLayout;

    private ActivityDialCustomizeBinding(LinearLayout rootView, GridView activityDialCustomizeGv, NavigationBar navigationbar, SmartRefreshLayout smartRefreshLayout) {
        this.rootView = rootView;
        this.activityDialCustomizeGv = activityDialCustomizeGv;
        this.navigationbar = navigationbar;
        this.smartRefreshLayout = smartRefreshLayout;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityDialCustomizeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityDialCustomizeBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_dial_customize, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityDialCustomizeBinding bind(View rootView) {
        int i2 = R.id.activity_dial_customize_gv;
        GridView gridView = (GridView) ViewBindings.findChildViewById(rootView, i2);
        if (gridView != null) {
            i2 = R.id.navigationbar;
            NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
            if (navigationBar != null) {
                i2 = R.id.smartRefreshLayout;
                SmartRefreshLayout smartRefreshLayout = (SmartRefreshLayout) ViewBindings.findChildViewById(rootView, i2);
                if (smartRefreshLayout != null) {
                    return new ActivityDialCustomizeBinding((LinearLayout) rootView, gridView, navigationBar, smartRefreshLayout);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
