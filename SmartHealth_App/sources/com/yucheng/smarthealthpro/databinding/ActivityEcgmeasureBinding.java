package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import com.yucheng.smarthealthpro.home.view.CardiographView4;

/* loaded from: classes4.dex */
public final class ActivityEcgmeasureBinding implements ViewBinding {
    public final CardiographView4 cardiographView;
    public final ImageView ivStop;
    public final LinearLayout llElectricOff;
    public final LinearLayout llElectricOn;
    public final NavigationBar navigationbar;
    public final ProgressBar progressBar;
    private final RelativeLayout rootView;
    public final TextView tvBpm;
    public final TextView tvHrv;
    public final TextView tvMmhg;
    public final TextView tvSchedule;
    public final TextView tvStartFinish;

    private ActivityEcgmeasureBinding(RelativeLayout rootView, CardiographView4 cardiographView, ImageView ivStop, LinearLayout llElectricOff, LinearLayout llElectricOn, NavigationBar navigationbar, ProgressBar progressBar, TextView tvBpm, TextView tvHrv, TextView tvMmhg, TextView tvSchedule, TextView tvStartFinish) {
        this.rootView = rootView;
        this.cardiographView = cardiographView;
        this.ivStop = ivStop;
        this.llElectricOff = llElectricOff;
        this.llElectricOn = llElectricOn;
        this.navigationbar = navigationbar;
        this.progressBar = progressBar;
        this.tvBpm = tvBpm;
        this.tvHrv = tvHrv;
        this.tvMmhg = tvMmhg;
        this.tvSchedule = tvSchedule;
        this.tvStartFinish = tvStartFinish;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ActivityEcgmeasureBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityEcgmeasureBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_ecgmeasure, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityEcgmeasureBinding bind(View rootView) {
        int i2 = R.id.cardiographView;
        CardiographView4 cardiographView4 = (CardiographView4) ViewBindings.findChildViewById(rootView, i2);
        if (cardiographView4 != null) {
            i2 = R.id.iv_stop;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i2);
            if (imageView != null) {
                i2 = R.id.ll_electric_off;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                if (linearLayout != null) {
                    i2 = R.id.ll_electric_on;
                    LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                    if (linearLayout2 != null) {
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
                                        i2 = R.id.tv_mmhg;
                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                        if (textView3 != null) {
                                            i2 = R.id.tv_schedule;
                                            TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                            if (textView4 != null) {
                                                i2 = R.id.tv_start_finish;
                                                TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                if (textView5 != null) {
                                                    return new ActivityEcgmeasureBinding((RelativeLayout) rootView, cardiographView4, imageView, linearLayout, linearLayout2, navigationBar, progressBar, textView, textView2, textView3, textView4, textView5);
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
