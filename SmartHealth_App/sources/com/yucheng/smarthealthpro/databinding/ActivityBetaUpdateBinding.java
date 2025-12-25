package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;

/* loaded from: classes4.dex */
public final class ActivityBetaUpdateBinding implements ViewBinding {
    public final ImageView ivHead;
    public final LinearLayoutCompat llThankTip;
    public final LinearLayoutCompat llUpdateTip;
    public final NavigationBar navigationbar;
    private final LinearLayoutCompat rootView;
    public final TextView tvAppName;
    public final TextView tvInfo;
    public final TextView tvThankTip;
    public final TextView tvToDownload;
    public final TextView tvVersion;

    private ActivityBetaUpdateBinding(LinearLayoutCompat rootView, ImageView ivHead, LinearLayoutCompat llThankTip, LinearLayoutCompat llUpdateTip, NavigationBar navigationbar, TextView tvAppName, TextView tvInfo, TextView tvThankTip, TextView tvToDownload, TextView tvVersion) {
        this.rootView = rootView;
        this.ivHead = ivHead;
        this.llThankTip = llThankTip;
        this.llUpdateTip = llUpdateTip;
        this.navigationbar = navigationbar;
        this.tvAppName = tvAppName;
        this.tvInfo = tvInfo;
        this.tvThankTip = tvThankTip;
        this.tvToDownload = tvToDownload;
        this.tvVersion = tvVersion;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayoutCompat getRoot() {
        return this.rootView;
    }

    public static ActivityBetaUpdateBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityBetaUpdateBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_beta_update, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityBetaUpdateBinding bind(View rootView) {
        int i2 = R.id.iv_head;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i2);
        if (imageView != null) {
            i2 = R.id.ll_thank_tip;
            LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) ViewBindings.findChildViewById(rootView, i2);
            if (linearLayoutCompat != null) {
                i2 = R.id.ll_update_tip;
                LinearLayoutCompat linearLayoutCompat2 = (LinearLayoutCompat) ViewBindings.findChildViewById(rootView, i2);
                if (linearLayoutCompat2 != null) {
                    i2 = R.id.navigationbar;
                    NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
                    if (navigationBar != null) {
                        i2 = R.id.tvAppName;
                        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                        if (textView != null) {
                            i2 = R.id.tv_info;
                            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                            if (textView2 != null) {
                                i2 = R.id.tv_thank_tip;
                                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                if (textView3 != null) {
                                    i2 = R.id.tv_to_download;
                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                    if (textView4 != null) {
                                        i2 = R.id.tv_version;
                                        TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                        if (textView5 != null) {
                                            return new ActivityBetaUpdateBinding((LinearLayoutCompat) rootView, imageView, linearLayoutCompat, linearLayoutCompat2, navigationBar, textView, textView2, textView3, textView4, textView5);
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
