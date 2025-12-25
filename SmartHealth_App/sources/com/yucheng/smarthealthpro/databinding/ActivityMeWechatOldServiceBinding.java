package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;

/* loaded from: classes4.dex */
public final class ActivityMeWechatOldServiceBinding implements ViewBinding {
    public final TextView btnBanding;
    public final NavigationBar navigationbar;
    private final RelativeLayout rootView;
    public final ImageView thirdpartyIcon;
    public final TextView tvAdd;

    private ActivityMeWechatOldServiceBinding(RelativeLayout rootView, TextView btnBanding, NavigationBar navigationbar, ImageView thirdpartyIcon, TextView tvAdd) {
        this.rootView = rootView;
        this.btnBanding = btnBanding;
        this.navigationbar = navigationbar;
        this.thirdpartyIcon = thirdpartyIcon;
        this.tvAdd = tvAdd;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ActivityMeWechatOldServiceBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityMeWechatOldServiceBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_me_wechat_old_service, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityMeWechatOldServiceBinding bind(View rootView) {
        int i2 = R.id.btn_banding;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
        if (textView != null) {
            i2 = R.id.navigationbar;
            NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
            if (navigationBar != null) {
                i2 = R.id.thirdparty_icon;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                if (imageView != null) {
                    i2 = R.id.tv_add;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                    if (textView2 != null) {
                        return new ActivityMeWechatOldServiceBinding((RelativeLayout) rootView, textView, navigationBar, imageView, textView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
