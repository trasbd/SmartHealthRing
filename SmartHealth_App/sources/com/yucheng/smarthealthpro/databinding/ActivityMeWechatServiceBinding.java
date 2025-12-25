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
import com.yucheng.smarthealthpro.framework.view.NavigationBar;

/* loaded from: classes4.dex */
public final class ActivityMeWechatServiceBinding implements ViewBinding {
    public final TextView btnBanding;
    public final NavigationBar navigationbar;
    private final RelativeLayout rootView;
    public final LinearLayout thirdPartyWechatFirstNoteLy;
    public final LinearLayout thirdPartyWechatNextNoteLy;
    public final TextView tvAdd;
    public final ImageView wechatQrImg;

    private ActivityMeWechatServiceBinding(RelativeLayout rootView, TextView btnBanding, NavigationBar navigationbar, LinearLayout thirdPartyWechatFirstNoteLy, LinearLayout thirdPartyWechatNextNoteLy, TextView tvAdd, ImageView wechatQrImg) {
        this.rootView = rootView;
        this.btnBanding = btnBanding;
        this.navigationbar = navigationbar;
        this.thirdPartyWechatFirstNoteLy = thirdPartyWechatFirstNoteLy;
        this.thirdPartyWechatNextNoteLy = thirdPartyWechatNextNoteLy;
        this.tvAdd = tvAdd;
        this.wechatQrImg = wechatQrImg;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ActivityMeWechatServiceBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityMeWechatServiceBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_me_wechat_service, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityMeWechatServiceBinding bind(View rootView) {
        int i2 = R.id.btn_banding;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
        if (textView != null) {
            i2 = R.id.navigationbar;
            NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
            if (navigationBar != null) {
                i2 = R.id.third_party_wechat_first_note_ly;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                if (linearLayout != null) {
                    i2 = R.id.third_party_wechat_next_note_ly;
                    LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                    if (linearLayout2 != null) {
                        i2 = R.id.tv_add;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                        if (textView2 != null) {
                            i2 = R.id.wechat_qr_img;
                            ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                            if (imageView != null) {
                                return new ActivityMeWechatServiceBinding((RelativeLayout) rootView, textView, navigationBar, linearLayout, linearLayout2, textView2, imageView);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
