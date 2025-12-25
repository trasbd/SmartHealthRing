package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;

/* loaded from: classes4.dex */
public final class ActivityDialBinding implements ViewBinding {
    public final GridView activityDialGv;
    public final TextView dialTvCustomize;
    public final TextView dialTvDownloadRecord;
    public final LinearLayout isHasCustomDial;
    public final NavigationBar navigationbar;
    private final LinearLayout rootView;
    public final SmartRefreshLayout smartRefreshLayout;

    private ActivityDialBinding(LinearLayout rootView, GridView activityDialGv, TextView dialTvCustomize, TextView dialTvDownloadRecord, LinearLayout isHasCustomDial, NavigationBar navigationbar, SmartRefreshLayout smartRefreshLayout) {
        this.rootView = rootView;
        this.activityDialGv = activityDialGv;
        this.dialTvCustomize = dialTvCustomize;
        this.dialTvDownloadRecord = dialTvDownloadRecord;
        this.isHasCustomDial = isHasCustomDial;
        this.navigationbar = navigationbar;
        this.smartRefreshLayout = smartRefreshLayout;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityDialBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityDialBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_dial, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityDialBinding bind(View rootView) {
        int i2 = R.id.activity_dial_gv;
        GridView gridView = (GridView) ViewBindings.findChildViewById(rootView, i2);
        if (gridView != null) {
            i2 = R.id.dial_tv_customize;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
            if (textView != null) {
                i2 = R.id.dial_tv_download_record;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                if (textView2 != null) {
                    i2 = R.id.is_has_custom_dial;
                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                    if (linearLayout != null) {
                        i2 = R.id.navigationbar;
                        NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
                        if (navigationBar != null) {
                            i2 = R.id.smartRefreshLayout;
                            SmartRefreshLayout smartRefreshLayout = (SmartRefreshLayout) ViewBindings.findChildViewById(rootView, i2);
                            if (smartRefreshLayout != null) {
                                return new ActivityDialBinding((LinearLayout) rootView, gridView, textView, textView2, linearLayout, navigationBar, smartRefreshLayout);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
