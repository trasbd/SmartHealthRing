package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.customchart.charts.BarChart;
import com.yucheng.smarthealthpro.view.chart.SleepChart;

/* loaded from: classes4.dex */
public final class FragmentSleep2Binding implements ViewBinding {
    public final BarChart barChartMonth;
    public final BarChart barChartWeek;
    public final SleepChart lineChartDay;
    private final LinearLayout rootView;

    private FragmentSleep2Binding(LinearLayout rootView, BarChart barChartMonth, BarChart barChartWeek, SleepChart lineChartDay) {
        this.rootView = rootView;
        this.barChartMonth = barChartMonth;
        this.barChartWeek = barChartWeek;
        this.lineChartDay = lineChartDay;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static FragmentSleep2Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentSleep2Binding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_sleep_2, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentSleep2Binding bind(View rootView) {
        int i2 = R.id.bar_chart_month;
        BarChart barChart = (BarChart) ViewBindings.findChildViewById(rootView, i2);
        if (barChart != null) {
            i2 = R.id.bar_chart_week;
            BarChart barChart2 = (BarChart) ViewBindings.findChildViewById(rootView, i2);
            if (barChart2 != null) {
                i2 = R.id.line_chart_day;
                SleepChart sleepChart = (SleepChart) ViewBindings.findChildViewById(rootView, i2);
                if (sleepChart != null) {
                    return new FragmentSleep2Binding((LinearLayout) rootView, barChart, barChart2, sleepChart);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
