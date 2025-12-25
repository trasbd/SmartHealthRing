package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.customchart.charts.LineChart;

/* loaded from: classes4.dex */
public final class FragmentTemptabBinding implements ViewBinding {
    public final LineChart lineChartDay;
    public final LineChart lineChartMonth;
    public final LineChart lineChartWeek;
    private final LinearLayout rootView;

    private FragmentTemptabBinding(LinearLayout rootView, LineChart lineChartDay, LineChart lineChartMonth, LineChart lineChartWeek) {
        this.rootView = rootView;
        this.lineChartDay = lineChartDay;
        this.lineChartMonth = lineChartMonth;
        this.lineChartWeek = lineChartWeek;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static FragmentTemptabBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentTemptabBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_temptab, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentTemptabBinding bind(View rootView) {
        int i2 = R.id.line_chart_day;
        LineChart lineChart = (LineChart) ViewBindings.findChildViewById(rootView, i2);
        if (lineChart != null) {
            i2 = R.id.line_chart_month;
            LineChart lineChart2 = (LineChart) ViewBindings.findChildViewById(rootView, i2);
            if (lineChart2 != null) {
                i2 = R.id.line_chart_week;
                LineChart lineChart3 = (LineChart) ViewBindings.findChildViewById(rootView, i2);
                if (lineChart3 != null) {
                    return new FragmentTemptabBinding((LinearLayout) rootView, lineChart, lineChart2, lineChart3);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
