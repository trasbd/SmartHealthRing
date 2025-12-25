package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import com.yucheng.smarthealthpro.perfect.view.DecimalScaleRulerView;

/* loaded from: classes4.dex */
public final class ActivityPerfectHeightWeightBinding implements ViewBinding {
    public final NavigationBar navigationbar;
    private final LinearLayout rootView;
    public final DecimalScaleRulerView rulerHeight;
    public final DecimalScaleRulerView rulerWeight;
    public final TextView tvHeight;
    public final TextView tvNext;
    public final TextView tvWeight;

    private ActivityPerfectHeightWeightBinding(LinearLayout rootView, NavigationBar navigationbar, DecimalScaleRulerView rulerHeight, DecimalScaleRulerView rulerWeight, TextView tvHeight, TextView tvNext, TextView tvWeight) {
        this.rootView = rootView;
        this.navigationbar = navigationbar;
        this.rulerHeight = rulerHeight;
        this.rulerWeight = rulerWeight;
        this.tvHeight = tvHeight;
        this.tvNext = tvNext;
        this.tvWeight = tvWeight;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityPerfectHeightWeightBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityPerfectHeightWeightBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_perfect_height_weight, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityPerfectHeightWeightBinding bind(View rootView) {
        int i2 = R.id.navigationbar;
        NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
        if (navigationBar != null) {
            i2 = R.id.ruler_height;
            DecimalScaleRulerView decimalScaleRulerView = (DecimalScaleRulerView) ViewBindings.findChildViewById(rootView, i2);
            if (decimalScaleRulerView != null) {
                i2 = R.id.ruler_weight;
                DecimalScaleRulerView decimalScaleRulerView2 = (DecimalScaleRulerView) ViewBindings.findChildViewById(rootView, i2);
                if (decimalScaleRulerView2 != null) {
                    i2 = R.id.tv_height;
                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                    if (textView != null) {
                        i2 = R.id.tv_next;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                        if (textView2 != null) {
                            i2 = R.id.tv_weight;
                            TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                            if (textView3 != null) {
                                return new ActivityPerfectHeightWeightBinding((LinearLayout) rootView, navigationBar, decimalScaleRulerView, decimalScaleRulerView2, textView, textView2, textView3);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
