package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.view.CircleImageView;

/* loaded from: classes4.dex */
public final class ItemCareNewFriendListBinding implements ViewBinding {
    public final ImageButton delete;
    public final CircleImageView ivHead;
    public final LinearLayout llCareFriendList;
    private final LinearLayout rootView;
    public final TextView tvName;
    public final TextView tvPass;

    private ItemCareNewFriendListBinding(LinearLayout rootView, ImageButton delete, CircleImageView ivHead, LinearLayout llCareFriendList, TextView tvName, TextView tvPass) {
        this.rootView = rootView;
        this.delete = delete;
        this.ivHead = ivHead;
        this.llCareFriendList = llCareFriendList;
        this.tvName = tvName;
        this.tvPass = tvPass;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ItemCareNewFriendListBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemCareNewFriendListBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.item_care_new_friend_list, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemCareNewFriendListBinding bind(View rootView) {
        int i2 = R.id.delete;
        ImageButton imageButton = (ImageButton) ViewBindings.findChildViewById(rootView, i2);
        if (imageButton != null) {
            i2 = R.id.iv_head;
            CircleImageView circleImageView = (CircleImageView) ViewBindings.findChildViewById(rootView, i2);
            if (circleImageView != null) {
                i2 = R.id.ll_care_friend_list;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                if (linearLayout != null) {
                    i2 = R.id.tv_name;
                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                    if (textView != null) {
                        i2 = R.id.tv_pass;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                        if (textView2 != null) {
                            return new ItemCareNewFriendListBinding((LinearLayout) rootView, imageButton, circleImageView, linearLayout, textView, textView2);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
