package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;

/* loaded from: classes4.dex */
public final class ActivityMeDeviceBinding implements ViewBinding {
    public final AppCompatImageView ivHead;
    public final ImageView ivTvDeviceState;
    public final LinearLayout llDevice;
    public final LinearLayout llDisConnect;
    public final NavigationBar navigationbar;
    public final RecyclerView recycleView;
    public final RelativeLayout rlTop;
    private final LinearLayout rootView;
    public final TextView tvDeviceKwh;
    public final TextView tvDeviceName;

    private ActivityMeDeviceBinding(LinearLayout rootView, AppCompatImageView ivHead, ImageView ivTvDeviceState, LinearLayout llDevice, LinearLayout llDisConnect, NavigationBar navigationbar, RecyclerView recycleView, RelativeLayout rlTop, TextView tvDeviceKwh, TextView tvDeviceName) {
        this.rootView = rootView;
        this.ivHead = ivHead;
        this.ivTvDeviceState = ivTvDeviceState;
        this.llDevice = llDevice;
        this.llDisConnect = llDisConnect;
        this.navigationbar = navigationbar;
        this.recycleView = recycleView;
        this.rlTop = rlTop;
        this.tvDeviceKwh = tvDeviceKwh;
        this.tvDeviceName = tvDeviceName;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityMeDeviceBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityMeDeviceBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_me_device, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityMeDeviceBinding bind(View rootView) {
        int i2 = R.id.iv_head;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(rootView, i2);
        if (appCompatImageView != null) {
            i2 = R.id.iv_tv_device_state;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i2);
            if (imageView != null) {
                i2 = R.id.llDevice;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                if (linearLayout != null) {
                    i2 = R.id.ll_dis_connect;
                    LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                    if (linearLayout2 != null) {
                        i2 = R.id.navigationbar;
                        NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
                        if (navigationBar != null) {
                            i2 = R.id.recycle_view;
                            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, i2);
                            if (recyclerView != null) {
                                i2 = R.id.rl_top;
                                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                                if (relativeLayout != null) {
                                    i2 = R.id.tv_device_kwh;
                                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                    if (textView != null) {
                                        i2 = R.id.tv_device_name;
                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                        if (textView2 != null) {
                                            return new ActivityMeDeviceBinding((LinearLayout) rootView, appCompatImageView, imageView, linearLayout, linearLayout2, navigationBar, recyclerView, relativeLayout, textView, textView2);
                                        }
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
