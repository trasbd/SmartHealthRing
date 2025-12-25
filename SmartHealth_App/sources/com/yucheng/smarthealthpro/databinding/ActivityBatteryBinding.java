package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.github.mikephil.charting.charts.PieChart;
import com.yucheng.smarthealthpro.R;

/* loaded from: classes4.dex */
public final class ActivityBatteryBinding implements ViewBinding {
    public final TextView aratedBloodPressure;
    public final TextView battery;
    public final TextView callDuration;
    public final TextView healthMeasurementDuration;
    public final TextView lastChargingEndBattery;
    public final TextView lastChargingTime;
    public final TextView messagesNumber;
    public final TextView musicDuration;
    public final PieChart pieChartView;
    private final ConstraintLayout rootView;
    public final TextView screenDuration;
    public final Toolbar toolbar;
    public final TextView toolbarTitle;
    public final TextView usageTime;

    private ActivityBatteryBinding(ConstraintLayout rootView, TextView aratedBloodPressure, TextView battery, TextView callDuration, TextView healthMeasurementDuration, TextView lastChargingEndBattery, TextView lastChargingTime, TextView messagesNumber, TextView musicDuration, PieChart pieChartView, TextView screenDuration, Toolbar toolbar, TextView toolbarTitle, TextView usageTime) {
        this.rootView = rootView;
        this.aratedBloodPressure = aratedBloodPressure;
        this.battery = battery;
        this.callDuration = callDuration;
        this.healthMeasurementDuration = healthMeasurementDuration;
        this.lastChargingEndBattery = lastChargingEndBattery;
        this.lastChargingTime = lastChargingTime;
        this.messagesNumber = messagesNumber;
        this.musicDuration = musicDuration;
        this.pieChartView = pieChartView;
        this.screenDuration = screenDuration;
        this.toolbar = toolbar;
        this.toolbarTitle = toolbarTitle;
        this.usageTime = usageTime;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityBatteryBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityBatteryBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_battery, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityBatteryBinding bind(View rootView) {
        int i2 = R.id.aratedBloodPressure;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
        if (textView != null) {
            i2 = R.id.battery;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
            if (textView2 != null) {
                i2 = R.id.callDuration;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                if (textView3 != null) {
                    i2 = R.id.healthMeasurementDuration;
                    TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                    if (textView4 != null) {
                        i2 = R.id.lastChargingEndBattery;
                        TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                        if (textView5 != null) {
                            i2 = R.id.lastChargingTime;
                            TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                            if (textView6 != null) {
                                i2 = R.id.messagesNumber;
                                TextView textView7 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                if (textView7 != null) {
                                    i2 = R.id.musicDuration;
                                    TextView textView8 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                    if (textView8 != null) {
                                        i2 = R.id.pieChartView;
                                        PieChart pieChart = (PieChart) ViewBindings.findChildViewById(rootView, i2);
                                        if (pieChart != null) {
                                            i2 = R.id.screenDuration;
                                            TextView textView9 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                            if (textView9 != null) {
                                                i2 = R.id.toolbar;
                                                Toolbar toolbar = (Toolbar) ViewBindings.findChildViewById(rootView, i2);
                                                if (toolbar != null) {
                                                    i2 = R.id.toolbarTitle;
                                                    TextView textView10 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                    if (textView10 != null) {
                                                        i2 = R.id.usageTime;
                                                        TextView textView11 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                        if (textView11 != null) {
                                                            return new ActivityBatteryBinding((ConstraintLayout) rootView, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, pieChart, textView9, toolbar, textView10, textView11);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
