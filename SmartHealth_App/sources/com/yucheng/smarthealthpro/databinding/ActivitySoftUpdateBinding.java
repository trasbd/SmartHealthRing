package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import com.yucheng.smarthealthpro.view.progress.NumberProgressBar;

/* loaded from: classes4.dex */
public final class ActivitySoftUpdateBinding implements ViewBinding {
    public final NavigationBar navigationbar;
    public final NumberProgressBar numberbar;
    private final RelativeLayout rootView;
    public final TextView tvDfuTip;
    public final TextView upgradeContent;
    public final TextView wversion;

    private ActivitySoftUpdateBinding(RelativeLayout rootView, NavigationBar navigationbar, NumberProgressBar numberbar, TextView tvDfuTip, TextView upgradeContent, TextView wversion) {
        this.rootView = rootView;
        this.navigationbar = navigationbar;
        this.numberbar = numberbar;
        this.tvDfuTip = tvDfuTip;
        this.upgradeContent = upgradeContent;
        this.wversion = wversion;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ActivitySoftUpdateBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivitySoftUpdateBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_soft_update, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivitySoftUpdateBinding bind(View rootView) {
        int i2 = R.id.navigationbar;
        NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
        if (navigationBar != null) {
            i2 = R.id.numberbar;
            NumberProgressBar numberProgressBar = (NumberProgressBar) ViewBindings.findChildViewById(rootView, i2);
            if (numberProgressBar != null) {
                i2 = R.id.tv_dfu_tip;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                if (textView != null) {
                    i2 = R.id.upgrade_content;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                    if (textView2 != null) {
                        i2 = R.id.wversion;
                        TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                        if (textView3 != null) {
                            return new ActivitySoftUpdateBinding((RelativeLayout) rootView, navigationBar, numberProgressBar, textView, textView2, textView3);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
