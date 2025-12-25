package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.customchart.charts.GradualBarChart;

/* loaded from: classes4.dex */
public final class FragmentSteptabBinding implements ViewBinding {
    public final GradualBarChart barChartDay;
    public final GradualBarChart barChartMonth;
    public final GradualBarChart barChartWeek;
    private final LinearLayout rootView;

    private FragmentSteptabBinding(LinearLayout rootView, GradualBarChart barChartDay, GradualBarChart barChartMonth, GradualBarChart barChartWeek) {
        this.rootView = rootView;
        this.barChartDay = barChartDay;
        this.barChartMonth = barChartMonth;
        this.barChartWeek = barChartWeek;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static FragmentSteptabBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentSteptabBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_steptab, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentSteptabBinding bind(View rootView) {
        int i2 = R.id.bar_chart_day;
        GradualBarChart gradualBarChart = (GradualBarChart) ViewBindings.findChildViewById(rootView, i2);
        if (gradualBarChart != null) {
            i2 = R.id.bar_chart_month;
            GradualBarChart gradualBarChart2 = (GradualBarChart) ViewBindings.findChildViewById(rootView, i2);
            if (gradualBarChart2 != null) {
                i2 = R.id.bar_chart_week;
                GradualBarChart gradualBarChart3 = (GradualBarChart) ViewBindings.findChildViewById(rootView, i2);
                if (gradualBarChart3 != null) {
                    return new FragmentSteptabBinding((LinearLayout) rootView, gradualBarChart, gradualBarChart2, gradualBarChart3);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
