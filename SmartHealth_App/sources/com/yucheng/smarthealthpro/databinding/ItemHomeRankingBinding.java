package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.view.CircleImageView;

/* loaded from: classes4.dex */
public final class ItemHomeRankingBinding implements ViewBinding {
    public final CircleImageView ivHeadPortrait;
    public final ImageView likeButton;
    public final LinearLayout llEndLikeNumber;
    private final RelativeLayout rootView;
    public final TextView tvLikeNumber;
    public final TextView tvName;
    public final TextView tvSerialNumber;
    public final TextView tvStepNumber;

    private ItemHomeRankingBinding(RelativeLayout rootView, CircleImageView ivHeadPortrait, ImageView likeButton, LinearLayout llEndLikeNumber, TextView tvLikeNumber, TextView tvName, TextView tvSerialNumber, TextView tvStepNumber) {
        this.rootView = rootView;
        this.ivHeadPortrait = ivHeadPortrait;
        this.likeButton = likeButton;
        this.llEndLikeNumber = llEndLikeNumber;
        this.tvLikeNumber = tvLikeNumber;
        this.tvName = tvName;
        this.tvSerialNumber = tvSerialNumber;
        this.tvStepNumber = tvStepNumber;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ItemHomeRankingBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemHomeRankingBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.item_home_ranking, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemHomeRankingBinding bind(View rootView) {
        int i2 = R.id.iv_head_portrait;
        CircleImageView circleImageView = (CircleImageView) ViewBindings.findChildViewById(rootView, i2);
        if (circleImageView != null) {
            i2 = R.id.like_button;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i2);
            if (imageView != null) {
                i2 = R.id.ll_end_like_number;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                if (linearLayout != null) {
                    i2 = R.id.tv_like_number;
                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                    if (textView != null) {
                        i2 = R.id.tv_name;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                        if (textView2 != null) {
                            i2 = R.id.tv_serial_number;
                            TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                            if (textView3 != null) {
                                i2 = R.id.tv_step_number;
                                TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                if (textView4 != null) {
                                    return new ItemHomeRankingBinding((RelativeLayout) rootView, circleImageView, imageView, linearLayout, textView, textView2, textView3, textView4);
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
