package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import com.yucheng.smarthealthpro.view.CircleImageView;

/* loaded from: classes4.dex */
public final class ActivityPersonalBinding implements ViewBinding {
    public final EditText edUserName;
    public final ImageView ivEditUserName;
    public final CircleImageView ivPersonalHead;
    public final NavigationBar navigationbar;
    public final RecyclerView recycleView;
    private final LinearLayout rootView;
    public final TextView tvUserName;

    private ActivityPersonalBinding(LinearLayout rootView, EditText edUserName, ImageView ivEditUserName, CircleImageView ivPersonalHead, NavigationBar navigationbar, RecyclerView recycleView, TextView tvUserName) {
        this.rootView = rootView;
        this.edUserName = edUserName;
        this.ivEditUserName = ivEditUserName;
        this.ivPersonalHead = ivPersonalHead;
        this.navigationbar = navigationbar;
        this.recycleView = recycleView;
        this.tvUserName = tvUserName;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityPersonalBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityPersonalBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_personal, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityPersonalBinding bind(View rootView) {
        int i2 = R.id.ed_user_name;
        EditText editText = (EditText) ViewBindings.findChildViewById(rootView, i2);
        if (editText != null) {
            i2 = R.id.iv_edit_user_name;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i2);
            if (imageView != null) {
                i2 = R.id.iv_personal_head;
                CircleImageView circleImageView = (CircleImageView) ViewBindings.findChildViewById(rootView, i2);
                if (circleImageView != null) {
                    i2 = R.id.navigationbar;
                    NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
                    if (navigationBar != null) {
                        i2 = R.id.recycle_view;
                        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, i2);
                        if (recyclerView != null) {
                            i2 = R.id.tv_user_name;
                            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                            if (textView != null) {
                                return new ActivityPersonalBinding((LinearLayout) rootView, editText, imageView, circleImageView, navigationBar, recyclerView, textView);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
