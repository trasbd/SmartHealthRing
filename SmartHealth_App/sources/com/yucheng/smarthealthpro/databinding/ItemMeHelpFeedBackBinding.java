package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.me.view.RoundImageView;

/* loaded from: classes4.dex */
public final class ItemMeHelpFeedBackBinding implements ViewBinding {
    public final ImageView ivDel;
    public final RoundImageView ivImage;
    private final FrameLayout rootView;

    private ItemMeHelpFeedBackBinding(FrameLayout rootView, ImageView ivDel, RoundImageView ivImage) {
        this.rootView = rootView;
        this.ivDel = ivDel;
        this.ivImage = ivImage;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static ItemMeHelpFeedBackBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemMeHelpFeedBackBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.item_me_help_feed_back, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemMeHelpFeedBackBinding bind(View rootView) {
        int i2 = R.id.iv_del;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i2);
        if (imageView != null) {
            i2 = R.id.iv_image;
            RoundImageView roundImageView = (RoundImageView) ViewBindings.findChildViewById(rootView, i2);
            if (roundImageView != null) {
                return new ItemMeHelpFeedBackBinding((FrameLayout) rootView, imageView, roundImageView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
