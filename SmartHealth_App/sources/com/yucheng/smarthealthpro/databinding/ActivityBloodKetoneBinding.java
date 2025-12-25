package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.core.widget.NestedScrollView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;

/* loaded from: classes4.dex */
public final class ActivityBloodKetoneBinding implements ViewBinding {
    public final FunctionItemBottomIncludeBinding includeItemBottom;
    public final FunctionItemCalendarIncludeBinding includeItemCalendar;
    public final FunctionItemMeasureDataIncludeBinding includeItemMessageData;
    public final FunctionItemTopIncludeBinding includeItemTop;
    public final NavigationBar navigationbar;
    public final NestedScrollView nsv;
    private final RelativeLayout rootView;

    private ActivityBloodKetoneBinding(RelativeLayout rootView, FunctionItemBottomIncludeBinding includeItemBottom, FunctionItemCalendarIncludeBinding includeItemCalendar, FunctionItemMeasureDataIncludeBinding includeItemMessageData, FunctionItemTopIncludeBinding includeItemTop, NavigationBar navigationbar, NestedScrollView nsv) {
        this.rootView = rootView;
        this.includeItemBottom = includeItemBottom;
        this.includeItemCalendar = includeItemCalendar;
        this.includeItemMessageData = includeItemMessageData;
        this.includeItemTop = includeItemTop;
        this.navigationbar = navigationbar;
        this.nsv = nsv;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ActivityBloodKetoneBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityBloodKetoneBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_blood_ketone, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityBloodKetoneBinding bind(View rootView) {
        int i2 = R.id.include_item_bottom;
        View viewFindChildViewById = ViewBindings.findChildViewById(rootView, i2);
        if (viewFindChildViewById != null) {
            FunctionItemBottomIncludeBinding functionItemBottomIncludeBindingBind = FunctionItemBottomIncludeBinding.bind(viewFindChildViewById);
            i2 = R.id.include_item_calendar;
            View viewFindChildViewById2 = ViewBindings.findChildViewById(rootView, i2);
            if (viewFindChildViewById2 != null) {
                FunctionItemCalendarIncludeBinding functionItemCalendarIncludeBindingBind = FunctionItemCalendarIncludeBinding.bind(viewFindChildViewById2);
                i2 = R.id.include_item_message_data;
                View viewFindChildViewById3 = ViewBindings.findChildViewById(rootView, i2);
                if (viewFindChildViewById3 != null) {
                    FunctionItemMeasureDataIncludeBinding functionItemMeasureDataIncludeBindingBind = FunctionItemMeasureDataIncludeBinding.bind(viewFindChildViewById3);
                    i2 = R.id.include_item_top;
                    View viewFindChildViewById4 = ViewBindings.findChildViewById(rootView, i2);
                    if (viewFindChildViewById4 != null) {
                        FunctionItemTopIncludeBinding functionItemTopIncludeBindingBind = FunctionItemTopIncludeBinding.bind(viewFindChildViewById4);
                        i2 = R.id.navigationbar;
                        NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
                        if (navigationBar != null) {
                            i2 = R.id.nsv;
                            NestedScrollView nestedScrollView = (NestedScrollView) ViewBindings.findChildViewById(rootView, i2);
                            if (nestedScrollView != null) {
                                return new ActivityBloodKetoneBinding((RelativeLayout) rootView, functionItemBottomIncludeBindingBind, functionItemCalendarIncludeBindingBind, functionItemMeasureDataIncludeBindingBind, functionItemTopIncludeBindingBind, navigationBar, nestedScrollView);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
