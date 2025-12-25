package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;

/* loaded from: classes4.dex */
public final class ActivityDeviceScanBinding implements ViewBinding {
    public final RecyclerView deviceRecyclerView;
    public final SmartRefreshLayout deviceRefreshLayout;
    public final NavigationBar navigationbar;
    private final LinearLayout rootView;

    private ActivityDeviceScanBinding(LinearLayout rootView, RecyclerView deviceRecyclerView, SmartRefreshLayout deviceRefreshLayout, NavigationBar navigationbar) {
        this.rootView = rootView;
        this.deviceRecyclerView = deviceRecyclerView;
        this.deviceRefreshLayout = deviceRefreshLayout;
        this.navigationbar = navigationbar;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityDeviceScanBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityDeviceScanBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_device_scan, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityDeviceScanBinding bind(View rootView) {
        int i2 = R.id.deviceRecyclerView;
        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, i2);
        if (recyclerView != null) {
            i2 = R.id.deviceRefreshLayout;
            SmartRefreshLayout smartRefreshLayout = (SmartRefreshLayout) ViewBindings.findChildViewById(rootView, i2);
            if (smartRefreshLayout != null) {
                i2 = R.id.navigationbar;
                NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
                if (navigationBar != null) {
                    return new ActivityDeviceScanBinding((LinearLayout) rootView, recyclerView, smartRefreshLayout, navigationBar);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
