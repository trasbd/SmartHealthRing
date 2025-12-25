package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;

/* loaded from: classes4.dex */
public final class ItemLifeHeaderBinding implements ViewBinding {
    public final ImageView ivTip;
    public final ConstraintLayout layoutTip;
    private final ConstraintLayout rootView;
    public final TextView tvDesc;
    public final TextView tvTipTitle;

    private ItemLifeHeaderBinding(ConstraintLayout rootView, ImageView ivTip, ConstraintLayout layoutTip, TextView tvDesc, TextView tvTipTitle) {
        this.rootView = rootView;
        this.ivTip = ivTip;
        this.layoutTip = layoutTip;
        this.tvDesc = tvDesc;
        this.tvTipTitle = tvTipTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ItemLifeHeaderBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemLifeHeaderBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.item_life_header, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemLifeHeaderBinding bind(View rootView) {
        int i2 = R.id.iv_tip;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i2);
        if (imageView != null) {
            ConstraintLayout constraintLayout = (ConstraintLayout) rootView;
            i2 = R.id.tv_desc;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
            if (textView != null) {
                i2 = R.id.tvTipTitle;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                if (textView2 != null) {
                    return new ItemLifeHeaderBinding(constraintLayout, imageView, constraintLayout, textView, textView2);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
