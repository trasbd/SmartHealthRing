package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;

/* loaded from: classes4.dex */
public final class ActivityMeShowsettingBinding implements ViewBinding {
    public final RelativeLayout llLuminance;
    public final NavigationBar navigationbar;
    public final RelativeLayout rlRaiseToWake;
    private final LinearLayout rootView;
    public final Switch switchRaiseToWake;
    public final TextView tvLuminance;

    private ActivityMeShowsettingBinding(LinearLayout rootView, RelativeLayout llLuminance, NavigationBar navigationbar, RelativeLayout rlRaiseToWake, Switch switchRaiseToWake, TextView tvLuminance) {
        this.rootView = rootView;
        this.llLuminance = llLuminance;
        this.navigationbar = navigationbar;
        this.rlRaiseToWake = rlRaiseToWake;
        this.switchRaiseToWake = switchRaiseToWake;
        this.tvLuminance = tvLuminance;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityMeShowsettingBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityMeShowsettingBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_me_showsetting, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityMeShowsettingBinding bind(View rootView) {
        int i2 = R.id.ll_luminance;
        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
        if (relativeLayout != null) {
            i2 = R.id.navigationbar;
            NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
            if (navigationBar != null) {
                i2 = R.id.rl_raise_to_wake;
                RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                if (relativeLayout2 != null) {
                    i2 = R.id.switch_raise_to_wake;
                    Switch r7 = (Switch) ViewBindings.findChildViewById(rootView, i2);
                    if (r7 != null) {
                        i2 = R.id.tv_luminance;
                        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                        if (textView != null) {
                            return new ActivityMeShowsettingBinding((LinearLayout) rootView, relativeLayout, navigationBar, relativeLayout2, r7, textView);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
