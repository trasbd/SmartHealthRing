package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import com.yucheng.smarthealthpro.home.view.CardiographView;

/* loaded from: classes4.dex */
public final class ActivityEcgpalybackBinding implements ViewBinding {
    public final CardiographView cardiographView;
    public final NavigationBar navigationbar;
    public final ProgressBar progressBar;
    private final LinearLayout rootView;
    public final TextView tvBpm;
    public final TextView tvHrv;
    public final TextView tvMmHg;
    public final TextView tvSchedule;

    private ActivityEcgpalybackBinding(LinearLayout rootView, CardiographView cardiographView, NavigationBar navigationbar, ProgressBar progressBar, TextView tvBpm, TextView tvHrv, TextView tvMmHg, TextView tvSchedule) {
        this.rootView = rootView;
        this.cardiographView = cardiographView;
        this.navigationbar = navigationbar;
        this.progressBar = progressBar;
        this.tvBpm = tvBpm;
        this.tvHrv = tvHrv;
        this.tvMmHg = tvMmHg;
        this.tvSchedule = tvSchedule;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityEcgpalybackBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityEcgpalybackBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_ecgpalyback, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityEcgpalybackBinding bind(View rootView) {
        int i2 = R.id.cardiographView;
        CardiographView cardiographView = (CardiographView) ViewBindings.findChildViewById(rootView, i2);
        if (cardiographView != null) {
            i2 = R.id.navigationbar;
            NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
            if (navigationBar != null) {
                i2 = R.id.progress_bar;
                ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(rootView, i2);
                if (progressBar != null) {
                    i2 = R.id.tv_bpm;
                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                    if (textView != null) {
                        i2 = R.id.tv_hrv;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                        if (textView2 != null) {
                            i2 = R.id.tv_mmHg;
                            TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                            if (textView3 != null) {
                                i2 = R.id.tv_schedule;
                                TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                if (textView4 != null) {
                                    return new ActivityEcgpalybackBinding((LinearLayout) rootView, cardiographView, navigationBar, progressBar, textView, textView2, textView3, textView4);
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
