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
public final class ActivityMeAntilostBinding implements ViewBinding {
    public final LinearLayout llAntiLost;
    public final NavigationBar navigationbar;
    public final RelativeLayout rlSwitch;
    private final LinearLayout rootView;
    public final Switch switchAntiLost;
    public final TextView tvAntiLost;

    private ActivityMeAntilostBinding(LinearLayout rootView, LinearLayout llAntiLost, NavigationBar navigationbar, RelativeLayout rlSwitch, Switch switchAntiLost, TextView tvAntiLost) {
        this.rootView = rootView;
        this.llAntiLost = llAntiLost;
        this.navigationbar = navigationbar;
        this.rlSwitch = rlSwitch;
        this.switchAntiLost = switchAntiLost;
        this.tvAntiLost = tvAntiLost;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityMeAntilostBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityMeAntilostBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_me_antilost, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityMeAntilostBinding bind(View rootView) {
        int i2 = R.id.llAntiLost;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
        if (linearLayout != null) {
            i2 = R.id.navigationbar;
            NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
            if (navigationBar != null) {
                i2 = R.id.rlSwitch;
                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                if (relativeLayout != null) {
                    i2 = R.id.switch_anti_lost;
                    Switch r7 = (Switch) ViewBindings.findChildViewById(rootView, i2);
                    if (r7 != null) {
                        i2 = R.id.tv_anti_lost;
                        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                        if (textView != null) {
                            return new ActivityMeAntilostBinding((LinearLayout) rootView, linearLayout, navigationBar, relativeLayout, r7, textView);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
