package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;

/* loaded from: classes4.dex */
public final class ActivityBloodpressuresettingBinding implements ViewBinding {
    public final ImageView ivBpCalibration;
    public final ImageView ivBpLevel;
    public final ImageView ivRemind;
    public final NavigationBar navigationbar;
    private final LinearLayout rootView;
    public final Switch switchPolice;
    public final TextView tvBpCalibration;
    public final TextView tvBpLevel;
    public final TextView tvRemind;

    private ActivityBloodpressuresettingBinding(LinearLayout rootView, ImageView ivBpCalibration, ImageView ivBpLevel, ImageView ivRemind, NavigationBar navigationbar, Switch switchPolice, TextView tvBpCalibration, TextView tvBpLevel, TextView tvRemind) {
        this.rootView = rootView;
        this.ivBpCalibration = ivBpCalibration;
        this.ivBpLevel = ivBpLevel;
        this.ivRemind = ivRemind;
        this.navigationbar = navigationbar;
        this.switchPolice = switchPolice;
        this.tvBpCalibration = tvBpCalibration;
        this.tvBpLevel = tvBpLevel;
        this.tvRemind = tvRemind;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityBloodpressuresettingBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityBloodpressuresettingBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_bloodpressuresetting, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityBloodpressuresettingBinding bind(View rootView) {
        int i2 = R.id.iv_bp_calibration;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i2);
        if (imageView != null) {
            i2 = R.id.iv_bp_level;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
            if (imageView2 != null) {
                i2 = R.id.iv_remind;
                ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                if (imageView3 != null) {
                    i2 = R.id.navigationbar;
                    NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
                    if (navigationBar != null) {
                        i2 = R.id.switch_police;
                        Switch r8 = (Switch) ViewBindings.findChildViewById(rootView, i2);
                        if (r8 != null) {
                            i2 = R.id.tv_bp_calibration;
                            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                            if (textView != null) {
                                i2 = R.id.tv_bp_level;
                                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                if (textView2 != null) {
                                    i2 = R.id.tv_remind;
                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                    if (textView3 != null) {
                                        return new ActivityBloodpressuresettingBinding((LinearLayout) rootView, imageView, imageView2, imageView3, navigationBar, r8, textView, textView2, textView3);
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
