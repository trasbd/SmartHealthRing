package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;

/* loaded from: classes4.dex */
public final class ActivityMeThirdpartyserviceBinding implements ViewBinding {
    public final LinearLayout lyThirdPartyAli;
    public final LinearLayout lyThirdPartyGoogle;
    public final LinearLayout lyThirdPartyWechat;
    public final NavigationBar navigationbar;
    private final RelativeLayout rootView;
    public final TextView thirdPartyAli;
    public final TextView thirdPartyGoogle;
    public final TextView thirdPartyWechat;

    private ActivityMeThirdpartyserviceBinding(RelativeLayout rootView, LinearLayout lyThirdPartyAli, LinearLayout lyThirdPartyGoogle, LinearLayout lyThirdPartyWechat, NavigationBar navigationbar, TextView thirdPartyAli, TextView thirdPartyGoogle, TextView thirdPartyWechat) {
        this.rootView = rootView;
        this.lyThirdPartyAli = lyThirdPartyAli;
        this.lyThirdPartyGoogle = lyThirdPartyGoogle;
        this.lyThirdPartyWechat = lyThirdPartyWechat;
        this.navigationbar = navigationbar;
        this.thirdPartyAli = thirdPartyAli;
        this.thirdPartyGoogle = thirdPartyGoogle;
        this.thirdPartyWechat = thirdPartyWechat;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ActivityMeThirdpartyserviceBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityMeThirdpartyserviceBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_me_thirdpartyservice, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityMeThirdpartyserviceBinding bind(View rootView) {
        int i2 = R.id.ly_third_party_ali;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
        if (linearLayout != null) {
            i2 = R.id.ly_third_party_google;
            LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
            if (linearLayout2 != null) {
                i2 = R.id.ly_third_party_wechat;
                LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                if (linearLayout3 != null) {
                    i2 = R.id.navigationbar;
                    NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
                    if (navigationBar != null) {
                        i2 = R.id.third_party_ali;
                        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                        if (textView != null) {
                            i2 = R.id.third_party_google;
                            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                            if (textView2 != null) {
                                i2 = R.id.third_party_wechat;
                                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                if (textView3 != null) {
                                    return new ActivityMeThirdpartyserviceBinding((RelativeLayout) rootView, linearLayout, linearLayout2, linearLayout3, navigationBar, textView, textView2, textView3);
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
