package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.customchart.charts.BarChart;
import com.yucheng.smarthealthpro.view.SleepQualityWrapView;

/* loaded from: classes4.dex */
public final class FragmentSleeptabBinding implements ViewBinding {
    public final BarChart barChartMonth;
    public final BarChart barChartWeek;
    private final LinearLayout rootView;
    public final SleepQualityWrapView sleepQualityWrapView;

    private FragmentSleeptabBinding(LinearLayout rootView, BarChart barChartMonth, BarChart barChartWeek, SleepQualityWrapView sleepQualityWrapView) {
        this.rootView = rootView;
        this.barChartMonth = barChartMonth;
        this.barChartWeek = barChartWeek;
        this.sleepQualityWrapView = sleepQualityWrapView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static FragmentSleeptabBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentSleeptabBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_sleeptab, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentSleeptabBinding bind(View rootView) {
        int i2 = R.id.bar_chart_month;
        BarChart barChart = (BarChart) ViewBindings.findChildViewById(rootView, i2);
        if (barChart != null) {
            i2 = R.id.bar_chart_week;
            BarChart barChart2 = (BarChart) ViewBindings.findChildViewById(rootView, i2);
            if (barChart2 != null) {
                i2 = R.id.sleep_quality_wrap_view;
                SleepQualityWrapView sleepQualityWrapView = (SleepQualityWrapView) ViewBindings.findChildViewById(rootView, i2);
                if (sleepQualityWrapView != null) {
                    return new FragmentSleeptabBinding((LinearLayout) rootView, barChart, barChart2, sleepQualityWrapView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
