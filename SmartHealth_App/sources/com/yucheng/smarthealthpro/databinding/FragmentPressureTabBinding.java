package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.customchart.charts.BarChart;
import com.yucheng.smarthealthpro.customchart.charts.LineChart;

/* loaded from: classes4.dex */
public final class FragmentPressureTabBinding implements ViewBinding {
    public final BarChart barChartDay;
    public final LineChart lineChartMonth;
    public final LineChart lineChartWeek;
    public final LinearLayout llTime;
    private final LinearLayout rootView;

    private FragmentPressureTabBinding(LinearLayout rootView, BarChart barChartDay, LineChart lineChartMonth, LineChart lineChartWeek, LinearLayout llTime) {
        this.rootView = rootView;
        this.barChartDay = barChartDay;
        this.lineChartMonth = lineChartMonth;
        this.lineChartWeek = lineChartWeek;
        this.llTime = llTime;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static FragmentPressureTabBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentPressureTabBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_pressure_tab, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentPressureTabBinding bind(View rootView) {
        int i2 = R.id.bar_chart_day;
        BarChart barChart = (BarChart) ViewBindings.findChildViewById(rootView, i2);
        if (barChart != null) {
            i2 = R.id.line_chart_month;
            LineChart lineChart = (LineChart) ViewBindings.findChildViewById(rootView, i2);
            if (lineChart != null) {
                i2 = R.id.line_chart_week;
                LineChart lineChart2 = (LineChart) ViewBindings.findChildViewById(rootView, i2);
                if (lineChart2 != null) {
                    i2 = R.id.ll_time;
                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                    if (linearLayout != null) {
                        return new FragmentPressureTabBinding((LinearLayout) rootView, barChart, lineChart, lineChart2, linearLayout);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
