package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;

/* loaded from: classes4.dex */
public final class ActivityArmBloodPressureMeasurementBinding implements ViewBinding {
    public final TextView bpMeasure;
    public final NavigationBar navigationbar;
    private final LinearLayout rootView;
    public final TextView tvStepInstructionsNext;
    public final View view;

    private ActivityArmBloodPressureMeasurementBinding(LinearLayout rootView, TextView bpMeasure, NavigationBar navigationbar, TextView tvStepInstructionsNext, View view) {
        this.rootView = rootView;
        this.bpMeasure = bpMeasure;
        this.navigationbar = navigationbar;
        this.tvStepInstructionsNext = tvStepInstructionsNext;
        this.view = view;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityArmBloodPressureMeasurementBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityArmBloodPressureMeasurementBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_arm_blood_pressure_measurement, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityArmBloodPressureMeasurementBinding bind(View rootView) {
        View viewFindChildViewById;
        int i2 = R.id.bp_measure;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
        if (textView != null) {
            i2 = R.id.navigationbar;
            NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
            if (navigationBar != null) {
                i2 = R.id.tv_step_instructions_next;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                if (textView2 != null && (viewFindChildViewById = ViewBindings.findChildViewById(rootView, (i2 = R.id.view))) != null) {
                    return new ActivityArmBloodPressureMeasurementBinding((LinearLayout) rootView, textView, navigationBar, textView2, viewFindChildViewById);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
