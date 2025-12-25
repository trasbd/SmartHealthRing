package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.customchart.charts.CandleStickChart;

/* loaded from: classes4.dex */
public final class FragmentBptabBinding implements ViewBinding {
    public final CandleStickChart candleStickChartDay;
    public final CandleStickChart candleStickChartMonth;
    public final CandleStickChart candleStickChartWeek;
    private final LinearLayout rootView;

    private FragmentBptabBinding(LinearLayout rootView, CandleStickChart candleStickChartDay, CandleStickChart candleStickChartMonth, CandleStickChart candleStickChartWeek) {
        this.rootView = rootView;
        this.candleStickChartDay = candleStickChartDay;
        this.candleStickChartMonth = candleStickChartMonth;
        this.candleStickChartWeek = candleStickChartWeek;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static FragmentBptabBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentBptabBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_bptab, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentBptabBinding bind(View rootView) {
        int i2 = R.id.candle_stick_chart_day;
        CandleStickChart candleStickChart = (CandleStickChart) ViewBindings.findChildViewById(rootView, i2);
        if (candleStickChart != null) {
            i2 = R.id.candle_stick_chart_month;
            CandleStickChart candleStickChart2 = (CandleStickChart) ViewBindings.findChildViewById(rootView, i2);
            if (candleStickChart2 != null) {
                i2 = R.id.candle_stick_chart_week;
                CandleStickChart candleStickChart3 = (CandleStickChart) ViewBindings.findChildViewById(rootView, i2);
                if (candleStickChart3 != null) {
                    return new FragmentBptabBinding((LinearLayout) rootView, candleStickChart, candleStickChart2, candleStickChart3);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
