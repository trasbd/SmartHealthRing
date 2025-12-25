package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.view.CircleImageView;

/* loaded from: classes4.dex */
public final class ItemCareFriendListBinding implements ViewBinding {
    public final ImageButton delete;
    public final ImageView ivEditRemarkName;
    public final CircleImageView ivHead;
    public final LinearLayout llCareFriendList;
    private final LinearLayout rootView;
    public final TextView tvName;

    private ItemCareFriendListBinding(LinearLayout rootView, ImageButton delete, ImageView ivEditRemarkName, CircleImageView ivHead, LinearLayout llCareFriendList, TextView tvName) {
        this.rootView = rootView;
        this.delete = delete;
        this.ivEditRemarkName = ivEditRemarkName;
        this.ivHead = ivHead;
        this.llCareFriendList = llCareFriendList;
        this.tvName = tvName;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ItemCareFriendListBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemCareFriendListBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.item_care_friend_list, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemCareFriendListBinding bind(View rootView) {
        int i2 = R.id.delete;
        ImageButton imageButton = (ImageButton) ViewBindings.findChildViewById(rootView, i2);
        if (imageButton != null) {
            i2 = R.id.iv_edit_remark_name;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i2);
            if (imageView != null) {
                i2 = R.id.iv_head;
                CircleImageView circleImageView = (CircleImageView) ViewBindings.findChildViewById(rootView, i2);
                if (circleImageView != null) {
                    i2 = R.id.ll_care_friend_list;
                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                    if (linearLayout != null) {
                        i2 = R.id.tv_name;
                        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                        if (textView != null) {
                            return new ItemCareFriendListBinding((LinearLayout) rootView, imageButton, imageView, circleImageView, linearLayout, textView);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
