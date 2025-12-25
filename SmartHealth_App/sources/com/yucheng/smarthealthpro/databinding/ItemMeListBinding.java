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

/* loaded from: classes4.dex */
public final class ItemMeListBinding implements ViewBinding {
    public final LinearLayout itemMeListIcon;
    public final ImageView ivLeftIconOne;
    public final ImageView ivLeftIconTwo;
    public final ImageView ivRightIconOne;
    public final ImageView ivRightIconThree;
    public final ImageView ivRightIconTwo;
    private final RelativeLayout rootView;
    public final TextView tvTitle;
    public final TextView tvVersions;

    private ItemMeListBinding(RelativeLayout rootView, LinearLayout itemMeListIcon, ImageView ivLeftIconOne, ImageView ivLeftIconTwo, ImageView ivRightIconOne, ImageView ivRightIconThree, ImageView ivRightIconTwo, TextView tvTitle, TextView tvVersions) {
        this.rootView = rootView;
        this.itemMeListIcon = itemMeListIcon;
        this.ivLeftIconOne = ivLeftIconOne;
        this.ivLeftIconTwo = ivLeftIconTwo;
        this.ivRightIconOne = ivRightIconOne;
        this.ivRightIconThree = ivRightIconThree;
        this.ivRightIconTwo = ivRightIconTwo;
        this.tvTitle = tvTitle;
        this.tvVersions = tvVersions;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ItemMeListBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemMeListBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.item_me_list, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemMeListBinding bind(View rootView) {
        int i2 = R.id.item_me_list_icon;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
        if (linearLayout != null) {
            i2 = R.id.iv_left_icon_one;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i2);
            if (imageView != null) {
                i2 = R.id.iv_left_icon_two;
                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                if (imageView2 != null) {
                    i2 = R.id.iv_right_icon_one;
                    ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                    if (imageView3 != null) {
                        i2 = R.id.iv_right_icon_Three;
                        ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                        if (imageView4 != null) {
                            i2 = R.id.iv_right_icon_two;
                            ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                            if (imageView5 != null) {
                                i2 = R.id.tv_title;
                                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                if (textView != null) {
                                    i2 = R.id.tv_versions;
                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                    if (textView2 != null) {
                                        return new ItemMeListBinding((RelativeLayout) rootView, linearLayout, imageView, imageView2, imageView3, imageView4, imageView5, textView, textView2);
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
