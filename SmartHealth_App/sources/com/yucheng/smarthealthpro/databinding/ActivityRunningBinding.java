package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;

/* loaded from: classes4.dex */
public final class ActivityRunningBinding implements ViewBinding {
    public final FunctionItemBottomIncludeBinding includeItemBottom;
    public final FunctionItemCalendarIncludeBinding includeItemCalendar;
    public final FunctionItemTopIncludeBinding includeItemTop;
    public final NavigationBar navigationbar;
    public final NestedScrollView nsv;
    private final RelativeLayout rootView;
    public final TextView tvStepNumber;

    private ActivityRunningBinding(RelativeLayout rootView, FunctionItemBottomIncludeBinding includeItemBottom, FunctionItemCalendarIncludeBinding includeItemCalendar, FunctionItemTopIncludeBinding includeItemTop, NavigationBar navigationbar, NestedScrollView nsv, TextView tvStepNumber) {
        this.rootView = rootView;
        this.includeItemBottom = includeItemBottom;
        this.includeItemCalendar = includeItemCalendar;
        this.includeItemTop = includeItemTop;
        this.navigationbar = navigationbar;
        this.nsv = nsv;
        this.tvStepNumber = tvStepNumber;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ActivityRunningBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityRunningBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_running, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityRunningBinding bind(View rootView) {
        int i2 = R.id.include_item_bottom;
        View viewFindChildViewById = ViewBindings.findChildViewById(rootView, i2);
        if (viewFindChildViewById != null) {
            FunctionItemBottomIncludeBinding functionItemBottomIncludeBindingBind = FunctionItemBottomIncludeBinding.bind(viewFindChildViewById);
            i2 = R.id.include_item_calendar;
            View viewFindChildViewById2 = ViewBindings.findChildViewById(rootView, i2);
            if (viewFindChildViewById2 != null) {
                FunctionItemCalendarIncludeBinding functionItemCalendarIncludeBindingBind = FunctionItemCalendarIncludeBinding.bind(viewFindChildViewById2);
                i2 = R.id.include_item_top;
                View viewFindChildViewById3 = ViewBindings.findChildViewById(rootView, i2);
                if (viewFindChildViewById3 != null) {
                    FunctionItemTopIncludeBinding functionItemTopIncludeBindingBind = FunctionItemTopIncludeBinding.bind(viewFindChildViewById3);
                    i2 = R.id.navigationbar;
                    NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
                    if (navigationBar != null) {
                        i2 = R.id.nsv;
                        NestedScrollView nestedScrollView = (NestedScrollView) ViewBindings.findChildViewById(rootView, i2);
                        if (nestedScrollView != null) {
                            i2 = R.id.tv_step_number;
                            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                            if (textView != null) {
                                return new ActivityRunningBinding((RelativeLayout) rootView, functionItemBottomIncludeBindingBind, functionItemCalendarIncludeBindingBind, functionItemTopIncludeBindingBind, navigationBar, nestedScrollView, textView);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
