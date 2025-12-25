package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;

/* loaded from: classes4.dex */
public final class ActivityPerfectMetricImperialBinding implements ViewBinding {
    public final NavigationBar navigationbar;
    public final RadioGroup radioGroup;
    public final RadioButton rbImperial;
    public final RadioButton rbMetric;
    private final LinearLayout rootView;
    public final TextView tvNext;

    private ActivityPerfectMetricImperialBinding(LinearLayout rootView, NavigationBar navigationbar, RadioGroup radioGroup, RadioButton rbImperial, RadioButton rbMetric, TextView tvNext) {
        this.rootView = rootView;
        this.navigationbar = navigationbar;
        this.radioGroup = radioGroup;
        this.rbImperial = rbImperial;
        this.rbMetric = rbMetric;
        this.tvNext = tvNext;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityPerfectMetricImperialBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityPerfectMetricImperialBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_perfect_metric_imperial, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityPerfectMetricImperialBinding bind(View rootView) {
        int i2 = R.id.navigationbar;
        NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
        if (navigationBar != null) {
            i2 = R.id.radio_group;
            RadioGroup radioGroup = (RadioGroup) ViewBindings.findChildViewById(rootView, i2);
            if (radioGroup != null) {
                i2 = R.id.rb_imperial;
                RadioButton radioButton = (RadioButton) ViewBindings.findChildViewById(rootView, i2);
                if (radioButton != null) {
                    i2 = R.id.rb_metric;
                    RadioButton radioButton2 = (RadioButton) ViewBindings.findChildViewById(rootView, i2);
                    if (radioButton2 != null) {
                        i2 = R.id.tv_next;
                        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                        if (textView != null) {
                            return new ActivityPerfectMetricImperialBinding((LinearLayout) rootView, navigationBar, radioGroup, radioButton, radioButton2, textView);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
