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
public final class ItemHomeCompileBinding implements ViewBinding {
    public final ImageView ivAdjustDown;
    public final ImageView ivAdjustUp;
    public final ImageView ivHideIcon;
    private final RelativeLayout rootView;
    public final TextView tvName;

    private ItemHomeCompileBinding(RelativeLayout rootView, ImageView ivAdjustDown, ImageView ivAdjustUp, ImageView ivHideIcon, TextView tvName) {
        this.rootView = rootView;
        this.ivAdjustDown = ivAdjustDown;
        this.ivAdjustUp = ivAdjustUp;
        this.ivHideIcon = ivHideIcon;
        this.tvName = tvName;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ItemHomeCompileBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemHomeCompileBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.item_home_compile, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemHomeCompileBinding bind(View rootView) {
        int i2 = R.id.iv_adjust_down;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i2);
        if (imageView != null) {
            i2 = R.id.iv_adjust_up;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
            if (imageView2 != null) {
                i2 = R.id.iv_hide_icon;
                ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                if (imageView3 != null) {
                    i2 = R.id.tv_name;
                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                    if (textView != null) {
                        return new ItemHomeCompileBinding((RelativeLayout) rootView, imageView, imageView2, imageView3, textView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
