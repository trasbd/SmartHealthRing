package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;

/* loaded from: classes4.dex */
public final class ItemContactsLayoutBinding implements ViewBinding {
    public final CheckBox checkBox;
    public final ImageView ivImg;
    private final LinearLayout rootView;
    public final TextView tvName;

    private ItemContactsLayoutBinding(LinearLayout rootView, CheckBox checkBox, ImageView ivImg, TextView tvName) {
        this.rootView = rootView;
        this.checkBox = checkBox;
        this.ivImg = ivImg;
        this.tvName = tvName;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ItemContactsLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemContactsLayoutBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.item_contacts_layout, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemContactsLayoutBinding bind(View rootView) {
        int i2 = R.id.check_box;
        CheckBox checkBox = (CheckBox) ViewBindings.findChildViewById(rootView, i2);
        if (checkBox != null) {
            i2 = R.id.iv_img;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i2);
            if (imageView != null) {
                i2 = R.id.tv_name;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                if (textView != null) {
                    return new ItemContactsLayoutBinding((LinearLayout) rootView, checkBox, imageView, textView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
