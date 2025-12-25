package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;

/* loaded from: classes4.dex */
public final class ItemMePushMessageBinding implements ViewBinding {
    public final ImageView ivImage;
    private final RelativeLayout rootView;
    public final Switch switchPushMessage;
    public final TextView tvAppName;

    private ItemMePushMessageBinding(RelativeLayout rootView, ImageView ivImage, Switch switchPushMessage, TextView tvAppName) {
        this.rootView = rootView;
        this.ivImage = ivImage;
        this.switchPushMessage = switchPushMessage;
        this.tvAppName = tvAppName;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ItemMePushMessageBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemMePushMessageBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.item_me_push_message, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemMePushMessageBinding bind(View rootView) {
        int i2 = R.id.iv_image;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i2);
        if (imageView != null) {
            i2 = R.id.switch_push_message;
            Switch r2 = (Switch) ViewBindings.findChildViewById(rootView, i2);
            if (r2 != null) {
                i2 = R.id.tv_app_name;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                if (textView != null) {
                    return new ItemMePushMessageBinding((RelativeLayout) rootView, imageView, r2, textView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
