package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;

/* loaded from: classes4.dex */
public final class ItemLifeBinding implements ViewBinding {
    public final ImageView ivIcon;
    public final ImageView ivIndicate;
    public final ImageView ivRightIconOne;
    public final LinearLayout llText;
    private final ConstraintLayout rootView;
    public final Switch switchBar;
    public final TextView text1;
    public final TextView text2;
    public final TextView text3;

    private ItemLifeBinding(ConstraintLayout rootView, ImageView ivIcon, ImageView ivIndicate, ImageView ivRightIconOne, LinearLayout llText, Switch switchBar, TextView text1, TextView text2, TextView text3) {
        this.rootView = rootView;
        this.ivIcon = ivIcon;
        this.ivIndicate = ivIndicate;
        this.ivRightIconOne = ivRightIconOne;
        this.llText = llText;
        this.switchBar = switchBar;
        this.text1 = text1;
        this.text2 = text2;
        this.text3 = text3;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ItemLifeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemLifeBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.item_life, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemLifeBinding bind(View rootView) {
        int i2 = R.id.ivIcon;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i2);
        if (imageView != null) {
            i2 = R.id.ivIndicate;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
            if (imageView2 != null) {
                i2 = R.id.iv_right_icon_one;
                ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                if (imageView3 != null) {
                    i2 = R.id.llText;
                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                    if (linearLayout != null) {
                        i2 = R.id.switchBar;
                        Switch r8 = (Switch) ViewBindings.findChildViewById(rootView, i2);
                        if (r8 != null) {
                            i2 = R.id.text1;
                            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                            if (textView != null) {
                                i2 = R.id.text2;
                                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                if (textView2 != null) {
                                    i2 = R.id.text3;
                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                    if (textView3 != null) {
                                        return new ItemLifeBinding((ConstraintLayout) rootView, imageView, imageView2, imageView3, linearLayout, r8, textView, textView2, textView3);
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
