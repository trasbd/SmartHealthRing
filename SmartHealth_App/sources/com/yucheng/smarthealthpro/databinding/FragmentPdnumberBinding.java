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
public final class FragmentPdnumberBinding implements ViewBinding {
    public final GradualBarChart barChartDay;
    public final GradualBarChart barChartHalfyear;
    public final GradualBarChart barChartMonth;
    public final GradualBarChart barChartWeek;
    public final GradualBarChart barChartYear;
    private final LinearLayout rootView;

    private FragmentPdnumberBinding(LinearLayout rootView, GradualBarChart barChartDay, GradualBarChart barChartHalfyear, GradualBarChart barChartMonth, GradualBarChart barChartWeek, GradualBarChart barChartYear) {
        this.rootView = rootView;
        this.barChartDay = barChartDay;
        this.barChartHalfyear = barChartHalfyear;
        this.barChartMonth = barChartMonth;
        this.barChartWeek = barChartWeek;
        this.barChartYear = barChartYear;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static FragmentPdnumberBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentPdnumberBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_pdnumber, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentPdnumberBinding bind(View rootView) {
        int i2 = R.id.bar_chart_day;
        GradualBarChart gradualBarChart = (GradualBarChart) ViewBindings.findChildViewById(rootView, i2);
        if (gradualBarChart != null) {
            i2 = R.id.bar_chart_halfyear;
            GradualBarChart gradualBarChart2 = (GradualBarChart) ViewBindings.findChildViewById(rootView, i2);
            if (gradualBarChart2 != null) {
                i2 = R.id.bar_chart_month;
                GradualBarChart gradualBarChart3 = (GradualBarChart) ViewBindings.findChildViewById(rootView, i2);
                if (gradualBarChart3 != null) {
                    i2 = R.id.bar_chart_week;
                    GradualBarChart gradualBarChart4 = (GradualBarChart) ViewBindings.findChildViewById(rootView, i2);
                    if (gradualBarChart4 != null) {
                        i2 = R.id.bar_chart_year;
                        GradualBarChart gradualBarChart5 = (GradualBarChart) ViewBindings.findChildViewById(rootView, i2);
                        if (gradualBarChart5 != null) {
                            return new FragmentPdnumberBinding((LinearLayout) rootView, gradualBarChart, gradualBarChart2, gradualBarChart3, gradualBarChart4, gradualBarChart5);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
