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
public final class ActivityEcgsyncdataBinding implements ViewBinding {
    public final LinearLayout llNoData;
    public final NavigationBar navigationbar;
    public final RecyclerView recycleView;
    private final LinearLayout rootView;
    public final SmartRefreshLayout srlEcg;

    private ActivityEcgsyncdataBinding(LinearLayout rootView, LinearLayout llNoData, NavigationBar navigationbar, RecyclerView recycleView, SmartRefreshLayout srlEcg) {
        this.rootView = rootView;
        this.llNoData = llNoData;
        this.navigationbar = navigationbar;
        this.recycleView = recycleView;
        this.srlEcg = srlEcg;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityEcgsyncdataBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityEcgsyncdataBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_ecgsyncdata, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityEcgsyncdataBinding bind(View rootView) {
        int i2 = R.id.ll_no_data;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
        if (linearLayout != null) {
            i2 = R.id.navigationbar;
            NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
            if (navigationBar != null) {
                i2 = R.id.recycle_view;
                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, i2);
                if (recyclerView != null) {
                    i2 = R.id.srl_ecg;
                    SmartRefreshLayout smartRefreshLayout = (SmartRefreshLayout) ViewBindings.findChildViewById(rootView, i2);
                    if (smartRefreshLayout != null) {
                        return new ActivityEcgsyncdataBinding((LinearLayout) rootView, linearLayout, navigationBar, recyclerView, smartRefreshLayout);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
