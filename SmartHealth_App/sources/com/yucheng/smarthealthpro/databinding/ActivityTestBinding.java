package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.customchart.charts.BarChart;

/* loaded from: classes4.dex */
public final class ActivityTestBinding implements ViewBinding {
    public final BarChart chart1;
    private final LinearLayout rootView;

    private ActivityTestBinding(LinearLayout rootView, BarChart chart1) {
        this.rootView = rootView;
        this.chart1 = chart1;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityTestBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityTestBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_test, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityTestBinding bind(View rootView) {
        int i2 = R.id.chart1;
        BarChart barChart = (BarChart) ViewBindings.findChildViewById(rootView, i2);
        if (barChart != null) {
            return new ActivityTestBinding((LinearLayout) rootView, barChart);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
