package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;

/* loaded from: classes4.dex */
public final class ItemEcgDiagnosisBinding implements ViewBinding {
    public final ImageView ivHomeFunction;
    public final ImageView rlHomeFunctionEcg;
    private final RelativeLayout rootView;
    public final TextView tvName;
    public final TextView tvUnit;
    public final TextView tvUnit2;
    public final TextView tvValue;
    public final TextView tvValue2;

    private ItemEcgDiagnosisBinding(RelativeLayout rootView, ImageView ivHomeFunction, ImageView rlHomeFunctionEcg, TextView tvName, TextView tvUnit, TextView tvUnit2, TextView tvValue, TextView tvValue2) {
        this.rootView = rootView;
        this.ivHomeFunction = ivHomeFunction;
        this.rlHomeFunctionEcg = rlHomeFunctionEcg;
        this.tvName = tvName;
        this.tvUnit = tvUnit;
        this.tvUnit2 = tvUnit2;
        this.tvValue = tvValue;
        this.tvValue2 = tvValue2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ItemEcgDiagnosisBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemEcgDiagnosisBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.item_ecg_diagnosis, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemEcgDiagnosisBinding bind(View rootView) {
        int i2 = R.id.iv_home_function;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i2);
        if (imageView != null) {
            i2 = R.id.rl_home_function_ecg;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
            if (imageView2 != null) {
                i2 = R.id.tv_name;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                if (textView != null) {
                    i2 = R.id.tv_unit;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                    if (textView2 != null) {
                        i2 = R.id.tv_unit2;
                        TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                        if (textView3 != null) {
                            i2 = R.id.tv_value;
                            TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                            if (textView4 != null) {
                                i2 = R.id.tv_value2;
                                TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                if (textView5 != null) {
                                    return new ItemEcgDiagnosisBinding((RelativeLayout) rootView, imageView, imageView2, textView, textView2, textView3, textView4, textView5);
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
