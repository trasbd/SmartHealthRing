package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import com.yucheng.smarthealthpro.view.CircleImageView;

/* loaded from: classes4.dex */
public final class FragmentMeBinding implements ViewBinding {
    public final CircleImageView ivHead;
    public final AppCompatImageView ivImg;
    public final NavigationBar navigationbar;
    public final RecyclerView recycleView;
    public final RelativeLayout rlHead;
    private final LinearLayout rootView;
    public final TextView tvUserName;

    private FragmentMeBinding(LinearLayout rootView, CircleImageView ivHead, AppCompatImageView ivImg, NavigationBar navigationbar, RecyclerView recycleView, RelativeLayout rlHead, TextView tvUserName) {
        this.rootView = rootView;
        this.ivHead = ivHead;
        this.ivImg = ivImg;
        this.navigationbar = navigationbar;
        this.recycleView = recycleView;
        this.rlHead = rlHead;
        this.tvUserName = tvUserName;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static FragmentMeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentMeBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_me, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentMeBinding bind(View rootView) {
        int i2 = R.id.iv_head;
        CircleImageView circleImageView = (CircleImageView) ViewBindings.findChildViewById(rootView, i2);
        if (circleImageView != null) {
            i2 = R.id.iv_img;
            AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(rootView, i2);
            if (appCompatImageView != null) {
                i2 = R.id.navigationbar;
                NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
                if (navigationBar != null) {
                    i2 = R.id.recycle_view;
                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, i2);
                    if (recyclerView != null) {
                        i2 = R.id.rl_head;
                        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                        if (relativeLayout != null) {
                            i2 = R.id.tv_user_name;
                            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                            if (textView != null) {
                                return new FragmentMeBinding((LinearLayout) rootView, circleImageView, appCompatImageView, navigationBar, recyclerView, relativeLayout, textView);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
