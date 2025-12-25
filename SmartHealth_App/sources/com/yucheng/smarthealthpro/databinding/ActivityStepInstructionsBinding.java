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
public final class ActivityStepInstructionsBinding implements ViewBinding {
    public final NavigationBar navigationbar;
    private final LinearLayout rootView;
    public final TextView tvStepInstructionsStart;
    public final View view;

    private ActivityStepInstructionsBinding(LinearLayout rootView, NavigationBar navigationbar, TextView tvStepInstructionsStart, View view) {
        this.rootView = rootView;
        this.navigationbar = navigationbar;
        this.tvStepInstructionsStart = tvStepInstructionsStart;
        this.view = view;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityStepInstructionsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityStepInstructionsBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_step_instructions, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityStepInstructionsBinding bind(View rootView) {
        View viewFindChildViewById;
        int i2 = R.id.navigationbar;
        NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
        if (navigationBar != null) {
            i2 = R.id.tv_step_instructions_start;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
            if (textView != null && (viewFindChildViewById = ViewBindings.findChildViewById(rootView, (i2 = R.id.view))) != null) {
                return new ActivityStepInstructionsBinding((LinearLayout) rootView, navigationBar, textView, viewFindChildViewById);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
