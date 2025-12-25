package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;

/* loaded from: classes4.dex */
public final class ActivityMeSafetysettingBinding implements ViewBinding {
    public final LinearLayout destroyAccount;
    public final LinearLayout llExitLogin;
    public final NavigationBar navigationbar;
    public final RelativeLayout rlLanguageSetting;
    public final RelativeLayout rlPermissionSetting;
    public final RelativeLayout rlResetPasswords;
    private final LinearLayout rootView;

    private ActivityMeSafetysettingBinding(LinearLayout rootView, LinearLayout destroyAccount, LinearLayout llExitLogin, NavigationBar navigationbar, RelativeLayout rlLanguageSetting, RelativeLayout rlPermissionSetting, RelativeLayout rlResetPasswords) {
        this.rootView = rootView;
        this.destroyAccount = destroyAccount;
        this.llExitLogin = llExitLogin;
        this.navigationbar = navigationbar;
        this.rlLanguageSetting = rlLanguageSetting;
        this.rlPermissionSetting = rlPermissionSetting;
        this.rlResetPasswords = rlResetPasswords;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityMeSafetysettingBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityMeSafetysettingBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_me_safetysetting, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityMeSafetysettingBinding bind(View rootView) {
        int i2 = R.id.destroy_account;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
        if (linearLayout != null) {
            i2 = R.id.ll_exit_login;
            LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
            if (linearLayout2 != null) {
                i2 = R.id.navigationbar;
                NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
                if (navigationBar != null) {
                    i2 = R.id.rl_language_setting;
                    RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                    if (relativeLayout != null) {
                        i2 = R.id.rl_permission_setting;
                        RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                        if (relativeLayout2 != null) {
                            i2 = R.id.rl_reset_passwords;
                            RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                            if (relativeLayout3 != null) {
                                return new ActivityMeSafetysettingBinding((LinearLayout) rootView, linearLayout, linearLayout2, navigationBar, relativeLayout, relativeLayout2, relativeLayout3);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
