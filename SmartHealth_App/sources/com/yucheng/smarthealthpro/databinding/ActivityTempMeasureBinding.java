package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import com.yucheng.smarthealthpro.home.view.StepView;

/* loaded from: classes4.dex */
public final class ActivityTempMeasureBinding implements ViewBinding {
    public final ImageView ivLottieBg;
    public final NavigationBar navigationbar;
    private final LinearLayout rootView;
    public final StepView stepView;
    public final TextView tvLottieData;
    public final TextView tvStartButton;

    private ActivityTempMeasureBinding(LinearLayout rootView, ImageView ivLottieBg, NavigationBar navigationbar, StepView stepView, TextView tvLottieData, TextView tvStartButton) {
        this.rootView = rootView;
        this.ivLottieBg = ivLottieBg;
        this.navigationbar = navigationbar;
        this.stepView = stepView;
        this.tvLottieData = tvLottieData;
        this.tvStartButton = tvStartButton;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityTempMeasureBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityTempMeasureBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_temp_measure, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityTempMeasureBinding bind(View rootView) {
        int i2 = R.id.iv_lottie_bg;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i2);
        if (imageView != null) {
            i2 = R.id.navigationbar;
            NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
            if (navigationBar != null) {
                i2 = R.id.step_view;
                StepView stepView = (StepView) ViewBindings.findChildViewById(rootView, i2);
                if (stepView != null) {
                    i2 = R.id.tv_lottie_data;
                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                    if (textView != null) {
                        i2 = R.id.tv_start_button;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                        if (textView2 != null) {
                            return new ActivityTempMeasureBinding((LinearLayout) rootView, imageView, navigationBar, stepView, textView, textView2);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
