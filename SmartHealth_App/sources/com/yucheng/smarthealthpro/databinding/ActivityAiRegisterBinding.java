package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import com.yucheng.smarthealthpro.view.CleanableEditText;

/* loaded from: classes4.dex */
public final class ActivityAiRegisterBinding implements ViewBinding {
    public final ImageView isShowPassword;
    public final ImageView isShowPassword2;
    public final NavigationBar navigationbar;
    public final Button registerBtnRegister;
    public final CheckBox registerCbProtocal;
    public final CleanableEditText registerEdConfirmPassword;
    public final CleanableEditText registerEdPassword;
    public final ImageView registerIconAccount;
    public final ImageView registerIconConfirmPwd;
    public final ImageView registerIconPwd;
    public final TextView registerTvAccount;
    private final LinearLayout rootView;
    public final TextView tvPrivacyPolicy;
    public final TextView tvUserAgreement;

    private ActivityAiRegisterBinding(LinearLayout rootView, ImageView isShowPassword, ImageView isShowPassword2, NavigationBar navigationbar, Button registerBtnRegister, CheckBox registerCbProtocal, CleanableEditText registerEdConfirmPassword, CleanableEditText registerEdPassword, ImageView registerIconAccount, ImageView registerIconConfirmPwd, ImageView registerIconPwd, TextView registerTvAccount, TextView tvPrivacyPolicy, TextView tvUserAgreement) {
        this.rootView = rootView;
        this.isShowPassword = isShowPassword;
        this.isShowPassword2 = isShowPassword2;
        this.navigationbar = navigationbar;
        this.registerBtnRegister = registerBtnRegister;
        this.registerCbProtocal = registerCbProtocal;
        this.registerEdConfirmPassword = registerEdConfirmPassword;
        this.registerEdPassword = registerEdPassword;
        this.registerIconAccount = registerIconAccount;
        this.registerIconConfirmPwd = registerIconConfirmPwd;
        this.registerIconPwd = registerIconPwd;
        this.registerTvAccount = registerTvAccount;
        this.tvPrivacyPolicy = tvPrivacyPolicy;
        this.tvUserAgreement = tvUserAgreement;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityAiRegisterBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityAiRegisterBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_ai_register, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityAiRegisterBinding bind(View rootView) {
        int i2 = R.id.is_show_password;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i2);
        if (imageView != null) {
            i2 = R.id.is_show_password2;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
            if (imageView2 != null) {
                i2 = R.id.navigationbar;
                NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
                if (navigationBar != null) {
                    i2 = R.id.register_btn_register;
                    Button button = (Button) ViewBindings.findChildViewById(rootView, i2);
                    if (button != null) {
                        i2 = R.id.register_cb_protocal;
                        CheckBox checkBox = (CheckBox) ViewBindings.findChildViewById(rootView, i2);
                        if (checkBox != null) {
                            i2 = R.id.register_ed_confirm_password;
                            CleanableEditText cleanableEditText = (CleanableEditText) ViewBindings.findChildViewById(rootView, i2);
                            if (cleanableEditText != null) {
                                i2 = R.id.register_ed_password;
                                CleanableEditText cleanableEditText2 = (CleanableEditText) ViewBindings.findChildViewById(rootView, i2);
                                if (cleanableEditText2 != null) {
                                    i2 = R.id.register_icon_account;
                                    ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                                    if (imageView3 != null) {
                                        i2 = R.id.register_icon_confirm_pwd;
                                        ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                                        if (imageView4 != null) {
                                            i2 = R.id.register_icon_pwd;
                                            ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                                            if (imageView5 != null) {
                                                i2 = R.id.register_tv_account;
                                                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                if (textView != null) {
                                                    i2 = R.id.tvPrivacyPolicy;
                                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                    if (textView2 != null) {
                                                        i2 = R.id.tvUserAgreement;
                                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                        if (textView3 != null) {
                                                            return new ActivityAiRegisterBinding((LinearLayout) rootView, imageView, imageView2, navigationBar, button, checkBox, cleanableEditText, cleanableEditText2, imageView3, imageView4, imageView5, textView, textView2, textView3);
                                                        }
                                                    }
                                                }
                                            }
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
