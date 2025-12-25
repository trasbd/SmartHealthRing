package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import com.yucheng.smarthealthpro.home.view.Cardiograph2View;

/* loaded from: classes4.dex */
public final class ActivityEcgBinding implements ViewBinding {
    public final Cardiograph2View cardiograph2View;
    public final FunctionItemBottomIncludeBinding includeItemBottom;
    public final NavigationBar navigationbar;
    public final NestedScrollView nsv;
    private final RelativeLayout rootView;
    public final SmartRefreshLayout srlEcg;
    public final TextView tvBpm;
    public final TextView tvHrv;
    public final TextView tvMmhg;

    private ActivityEcgBinding(RelativeLayout rootView, Cardiograph2View cardiograph2View, FunctionItemBottomIncludeBinding includeItemBottom, NavigationBar navigationbar, NestedScrollView nsv, SmartRefreshLayout srlEcg, TextView tvBpm, TextView tvHrv, TextView tvMmhg) {
        this.rootView = rootView;
        this.cardiograph2View = cardiograph2View;
        this.includeItemBottom = includeItemBottom;
        this.navigationbar = navigationbar;
        this.nsv = nsv;
        this.srlEcg = srlEcg;
        this.tvBpm = tvBpm;
        this.tvHrv = tvHrv;
        this.tvMmhg = tvMmhg;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ActivityEcgBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityEcgBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_ecg, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityEcgBinding bind(View rootView) {
        View viewFindChildViewById;
        int i2 = R.id.cardiograph2View;
        Cardiograph2View cardiograph2View = (Cardiograph2View) ViewBindings.findChildViewById(rootView, i2);
        if (cardiograph2View != null && (viewFindChildViewById = ViewBindings.findChildViewById(rootView, (i2 = R.id.include_item_bottom))) != null) {
            FunctionItemBottomIncludeBinding functionItemBottomIncludeBindingBind = FunctionItemBottomIncludeBinding.bind(viewFindChildViewById);
            i2 = R.id.navigationbar;
            NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
            if (navigationBar != null) {
                i2 = R.id.nsv;
                NestedScrollView nestedScrollView = (NestedScrollView) ViewBindings.findChildViewById(rootView, i2);
                if (nestedScrollView != null) {
                    i2 = R.id.srl_ecg;
                    SmartRefreshLayout smartRefreshLayout = (SmartRefreshLayout) ViewBindings.findChildViewById(rootView, i2);
                    if (smartRefreshLayout != null) {
                        i2 = R.id.tv_bpm;
                        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                        if (textView != null) {
                            i2 = R.id.tv_hrv;
                            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                            if (textView2 != null) {
                                i2 = R.id.tv_mmhg;
                                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                if (textView3 != null) {
                                    return new ActivityEcgBinding((RelativeLayout) rootView, cardiograph2View, functionItemBottomIncludeBindingBind, navigationBar, nestedScrollView, smartRefreshLayout, textView, textView2, textView3);
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
