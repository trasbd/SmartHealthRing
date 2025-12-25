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
public final class FragmentPhyTabBinding implements ViewBinding {
    public final BarChart barChartMonth;
    public final BarChart barChartWeek;
    public final LineChart lineChartDay;
    private final LinearLayout rootView;

    private FragmentPhyTabBinding(LinearLayout rootView, BarChart barChartMonth, BarChart barChartWeek, LineChart lineChartDay) {
        this.rootView = rootView;
        this.barChartMonth = barChartMonth;
        this.barChartWeek = barChartWeek;
        this.lineChartDay = lineChartDay;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static FragmentPhyTabBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentPhyTabBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_phy_tab, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentPhyTabBinding bind(View rootView) {
        int i2 = R.id.bar_chart_month;
        BarChart barChart = (BarChart) ViewBindings.findChildViewById(rootView, i2);
        if (barChart != null) {
            i2 = R.id.bar_chart_week;
            BarChart barChart2 = (BarChart) ViewBindings.findChildViewById(rootView, i2);
            if (barChart2 != null) {
                i2 = R.id.line_chart_day;
                LineChart lineChart = (LineChart) ViewBindings.findChildViewById(rootView, i2);
                if (lineChart != null) {
                    return new FragmentPhyTabBinding((LinearLayout) rootView, barChart, barChart2, lineChart);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
