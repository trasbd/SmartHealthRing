package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import com.yucheng.smarthealthpro.view.PieChartView;

/* loaded from: classes4.dex */
public final class ActivityTrendFollowingBinding implements ViewBinding {
    public final LinearLayoutCompat llType;
    public final NavigationBar navigationbar;
    public final PieChartView pieChart;
    public final RecyclerView recycleView;
    private final LinearLayoutCompat rootView;
    public final SmartRefreshLayout srlEcg;

    private ActivityTrendFollowingBinding(LinearLayoutCompat rootView, LinearLayoutCompat llType, NavigationBar navigationbar, PieChartView pieChart, RecyclerView recycleView, SmartRefreshLayout srlEcg) {
        this.rootView = rootView;
        this.llType = llType;
        this.navigationbar = navigationbar;
        this.pieChart = pieChart;
        this.recycleView = recycleView;
        this.srlEcg = srlEcg;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayoutCompat getRoot() {
        return this.rootView;
    }

    public static ActivityTrendFollowingBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityTrendFollowingBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_trend_following, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityTrendFollowingBinding bind(View rootView) {
        int i2 = R.id.ll_type;
        LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) ViewBindings.findChildViewById(rootView, i2);
        if (linearLayoutCompat != null) {
            i2 = R.id.navigationbar;
            NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
            if (navigationBar != null) {
                i2 = R.id.pieChart;
                PieChartView pieChartView = (PieChartView) ViewBindings.findChildViewById(rootView, i2);
                if (pieChartView != null) {
                    i2 = R.id.recycle_view;
                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, i2);
                    if (recyclerView != null) {
                        i2 = R.id.srl_ecg;
                        SmartRefreshLayout smartRefreshLayout = (SmartRefreshLayout) ViewBindings.findChildViewById(rootView, i2);
                        if (smartRefreshLayout != null) {
                            return new ActivityTrendFollowingBinding((LinearLayoutCompat) rootView, linearLayoutCompat, navigationBar, pieChartView, recyclerView, smartRefreshLayout);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
