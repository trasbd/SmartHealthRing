package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;

/* loaded from: classes4.dex */
public final class ActivityAboutDeviceBinding implements ViewBinding {
    public final ImageView ivQr;
    public final NavigationBar navigationbar;
    public final RecyclerView recyclerview;
    private final LinearLayout rootView;

    private ActivityAboutDeviceBinding(LinearLayout rootView, ImageView ivQr, NavigationBar navigationbar, RecyclerView recyclerview) {
        this.rootView = rootView;
        this.ivQr = ivQr;
        this.navigationbar = navigationbar;
        this.recyclerview = recyclerview;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityAboutDeviceBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityAboutDeviceBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_about_device, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityAboutDeviceBinding bind(View rootView) {
        int i2 = R.id.ivQr;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i2);
        if (imageView != null) {
            i2 = R.id.navigationbar;
            NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
            if (navigationBar != null) {
                i2 = R.id.recyclerview;
                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, i2);
                if (recyclerView != null) {
                    return new ActivityAboutDeviceBinding((LinearLayout) rootView, imageView, navigationBar, recyclerView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
