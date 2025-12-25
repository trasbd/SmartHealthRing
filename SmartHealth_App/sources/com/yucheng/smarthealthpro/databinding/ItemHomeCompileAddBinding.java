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
public final class ItemHomeCompileAddBinding implements ViewBinding {
    public final ImageView ivAdjustDown;
    public final ImageView ivAdjustUp;
    public final ImageView ivFunctionIcon;
    private final RelativeLayout rootView;
    public final TextView tvName;

    private ItemHomeCompileAddBinding(RelativeLayout rootView, ImageView ivAdjustDown, ImageView ivAdjustUp, ImageView ivFunctionIcon, TextView tvName) {
        this.rootView = rootView;
        this.ivAdjustDown = ivAdjustDown;
        this.ivAdjustUp = ivAdjustUp;
        this.ivFunctionIcon = ivFunctionIcon;
        this.tvName = tvName;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ItemHomeCompileAddBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemHomeCompileAddBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.item_home_compile_add, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemHomeCompileAddBinding bind(View rootView) {
        int i2 = R.id.iv_adjust_down;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i2);
        if (imageView != null) {
            i2 = R.id.iv_adjust_up;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
            if (imageView2 != null) {
                i2 = R.id.iv_function_icon;
                ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                if (imageView3 != null) {
                    i2 = R.id.tv_name;
                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                    if (textView != null) {
                        return new ItemHomeCompileAddBinding((RelativeLayout) rootView, imageView, imageView2, imageView3, textView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
