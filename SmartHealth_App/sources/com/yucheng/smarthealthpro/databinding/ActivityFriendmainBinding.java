package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import com.yucheng.smarthealthpro.home.view.StepView;

/* loaded from: classes4.dex */
public final class ActivityFriendmainBinding implements ViewBinding {
    public final NavigationBar navigationbar;
    public final RecyclerView recycleHome;
    public final RelativeLayout rlRunning;
    private final FrameLayout rootView;
    public final SpinkitDialogIncludeBinding rvDialog;
    public final SmartRefreshLayout srlHome;
    public final StepView stepView;
    public final TextView tvKcal;
    public final TextView tvOdo;
    public final TextView tvOdoUnit;
    public final TextView tvStep;

    private ActivityFriendmainBinding(FrameLayout rootView, NavigationBar navigationbar, RecyclerView recycleHome, RelativeLayout rlRunning, SpinkitDialogIncludeBinding rvDialog, SmartRefreshLayout srlHome, StepView stepView, TextView tvKcal, TextView tvOdo, TextView tvOdoUnit, TextView tvStep) {
        this.rootView = rootView;
        this.navigationbar = navigationbar;
        this.recycleHome = recycleHome;
        this.rlRunning = rlRunning;
        this.rvDialog = rvDialog;
        this.srlHome = srlHome;
        this.stepView = stepView;
        this.tvKcal = tvKcal;
        this.tvOdo = tvOdo;
        this.tvOdoUnit = tvOdoUnit;
        this.tvStep = tvStep;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static ActivityFriendmainBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityFriendmainBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_friendmain, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityFriendmainBinding bind(View rootView) {
        View viewFindChildViewById;
        int i2 = R.id.navigationbar;
        NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
        if (navigationBar != null) {
            i2 = R.id.recycle_home;
            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, i2);
            if (recyclerView != null) {
                i2 = R.id.rl_running;
                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                if (relativeLayout != null && (viewFindChildViewById = ViewBindings.findChildViewById(rootView, (i2 = R.id.rv_dialog))) != null) {
                    SpinkitDialogIncludeBinding spinkitDialogIncludeBindingBind = SpinkitDialogIncludeBinding.bind(viewFindChildViewById);
                    i2 = R.id.srl_home;
                    SmartRefreshLayout smartRefreshLayout = (SmartRefreshLayout) ViewBindings.findChildViewById(rootView, i2);
                    if (smartRefreshLayout != null) {
                        i2 = R.id.step_view;
                        StepView stepView = (StepView) ViewBindings.findChildViewById(rootView, i2);
                        if (stepView != null) {
                            i2 = R.id.tv_kcal;
                            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                            if (textView != null) {
                                i2 = R.id.tv_odo;
                                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                if (textView2 != null) {
                                    i2 = R.id.tv_odo_unit;
                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                    if (textView3 != null) {
                                        i2 = R.id.tv_step;
                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                        if (textView4 != null) {
                                            return new ActivityFriendmainBinding((FrameLayout) rootView, navigationBar, recyclerView, relativeLayout, spinkitDialogIncludeBindingBind, smartRefreshLayout, stepView, textView, textView2, textView3, textView4);
                                        }
                                    }
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
