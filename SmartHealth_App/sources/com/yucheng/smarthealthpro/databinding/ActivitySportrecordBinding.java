package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;

/* loaded from: classes4.dex */
public final class ActivitySportrecordBinding implements ViewBinding {
    public final LinearLayout llNoData;
    public final NavigationBar navigationbar;
    private final RelativeLayout rootView;
    public final SwipeRecyclerView rvRecord;

    private ActivitySportrecordBinding(RelativeLayout rootView, LinearLayout llNoData, NavigationBar navigationbar, SwipeRecyclerView rvRecord) {
        this.rootView = rootView;
        this.llNoData = llNoData;
        this.navigationbar = navigationbar;
        this.rvRecord = rvRecord;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ActivitySportrecordBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivitySportrecordBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_sportrecord, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivitySportrecordBinding bind(View rootView) {
        int i2 = R.id.ll_no_data;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
        if (linearLayout != null) {
            i2 = R.id.navigationbar;
            NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
            if (navigationBar != null) {
                i2 = R.id.rv_record;
                SwipeRecyclerView swipeRecyclerView = (SwipeRecyclerView) ViewBindings.findChildViewById(rootView, i2);
                if (swipeRecyclerView != null) {
                    return new ActivitySportrecordBinding((RelativeLayout) rootView, linearLayout, navigationBar, swipeRecyclerView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
