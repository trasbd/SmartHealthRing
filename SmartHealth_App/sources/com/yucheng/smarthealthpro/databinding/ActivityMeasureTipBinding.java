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

/* loaded from: classes4.dex */
public final class ActivityMeasureTipBinding implements ViewBinding {
    public final ImageView ivTip;
    public final NavigationBar navigationbar;
    private final LinearLayout rootView;
    public final TextView tvSure;
    public final TextView tvUnderstood;

    private ActivityMeasureTipBinding(LinearLayout rootView, ImageView ivTip, NavigationBar navigationbar, TextView tvSure, TextView tvUnderstood) {
        this.rootView = rootView;
        this.ivTip = ivTip;
        this.navigationbar = navigationbar;
        this.tvSure = tvSure;
        this.tvUnderstood = tvUnderstood;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityMeasureTipBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityMeasureTipBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_measure_tip, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityMeasureTipBinding bind(View rootView) {
        int i2 = R.id.iv_tip;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i2);
        if (imageView != null) {
            i2 = R.id.navigationbar;
            NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
            if (navigationBar != null) {
                i2 = R.id.tv_sure;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                if (textView != null) {
                    i2 = R.id.tv_understood;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                    if (textView2 != null) {
                        return new ActivityMeasureTipBinding((LinearLayout) rootView, imageView, navigationBar, textView, textView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
