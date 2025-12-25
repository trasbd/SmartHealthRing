package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;

/* loaded from: classes4.dex */
public final class ActivityTemperaturesettingBinding implements ViewBinding {
    public final ImageView ivInterval;
    public final ImageView ivRemind;
    public final RelativeLayout llInterval;
    public final RelativeLayout llRemind;
    public final NavigationBar navigationbar;
    private final LinearLayout rootView;
    public final Switch switchMonitor;
    public final Switch switchPolice;
    public final TextView tvInterval;
    public final TextView tvRemind;
    public final View viewInterval;

    private ActivityTemperaturesettingBinding(LinearLayout rootView, ImageView ivInterval, ImageView ivRemind, RelativeLayout llInterval, RelativeLayout llRemind, NavigationBar navigationbar, Switch switchMonitor, Switch switchPolice, TextView tvInterval, TextView tvRemind, View viewInterval) {
        this.rootView = rootView;
        this.ivInterval = ivInterval;
        this.ivRemind = ivRemind;
        this.llInterval = llInterval;
        this.llRemind = llRemind;
        this.navigationbar = navigationbar;
        this.switchMonitor = switchMonitor;
        this.switchPolice = switchPolice;
        this.tvInterval = tvInterval;
        this.tvRemind = tvRemind;
        this.viewInterval = viewInterval;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityTemperaturesettingBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityTemperaturesettingBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_temperaturesetting, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityTemperaturesettingBinding bind(View rootView) {
        View viewFindChildViewById;
        int i2 = R.id.iv_interval;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i2);
        if (imageView != null) {
            i2 = R.id.iv_remind;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
            if (imageView2 != null) {
                i2 = R.id.ll_interval;
                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                if (relativeLayout != null) {
                    i2 = R.id.ll_remind;
                    RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                    if (relativeLayout2 != null) {
                        i2 = R.id.navigationbar;
                        NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
                        if (navigationBar != null) {
                            i2 = R.id.switch_monitor;
                            Switch r9 = (Switch) ViewBindings.findChildViewById(rootView, i2);
                            if (r9 != null) {
                                i2 = R.id.switch_police;
                                Switch r10 = (Switch) ViewBindings.findChildViewById(rootView, i2);
                                if (r10 != null) {
                                    i2 = R.id.tv_interval;
                                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                    if (textView != null) {
                                        i2 = R.id.tv_remind;
                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                        if (textView2 != null && (viewFindChildViewById = ViewBindings.findChildViewById(rootView, (i2 = R.id.view_interval))) != null) {
                                            return new ActivityTemperaturesettingBinding((LinearLayout) rootView, imageView, imageView2, relativeLayout, relativeLayout2, navigationBar, r9, r10, textView, textView2, viewFindChildViewById);
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
