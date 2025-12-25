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
public final class ActivityAiLoginBinding implements ViewBinding {
    public final ImageView isShowPassword;
    public final Button loginBtnLogin;
    public final CheckBox loginCbProtocal;
    public final CleanableEditText loginEdAccount;
    public final CleanableEditText loginEdPassword;
    public final ImageView loginFacebook;
    public final ImageView loginGoogle;
    public final ImageView loginIconAccout;
    public final ImageView loginIconPwd;
    public final LinearLayout loginInChina;
    public final LinearLayout loginOutChina;
    public final ImageView loginQq;
    public final TextView loginTvForgetPassword;
    public final TextView loginTvNoAccount;
    public final TextView loginTvProtocal;
    public final TextView loginTvRegister;
    public final TextView loginTvUserAgreement;
    public final ImageView loginTwitter;
    public final ImageView loginWechat;
    public final ImageView loginWeibo;
    public final NavigationBar navigationbar;
    private final LinearLayout rootView;

    private ActivityAiLoginBinding(LinearLayout rootView, ImageView isShowPassword, Button loginBtnLogin, CheckBox loginCbProtocal, CleanableEditText loginEdAccount, CleanableEditText loginEdPassword, ImageView loginFacebook, ImageView loginGoogle, ImageView loginIconAccout, ImageView loginIconPwd, LinearLayout loginInChina, LinearLayout loginOutChina, ImageView loginQq, TextView loginTvForgetPassword, TextView loginTvNoAccount, TextView loginTvProtocal, TextView loginTvRegister, TextView loginTvUserAgreement, ImageView loginTwitter, ImageView loginWechat, ImageView loginWeibo, NavigationBar navigationbar) {
        this.rootView = rootView;
        this.isShowPassword = isShowPassword;
        this.loginBtnLogin = loginBtnLogin;
        this.loginCbProtocal = loginCbProtocal;
        this.loginEdAccount = loginEdAccount;
        this.loginEdPassword = loginEdPassword;
        this.loginFacebook = loginFacebook;
        this.loginGoogle = loginGoogle;
        this.loginIconAccout = loginIconAccout;
        this.loginIconPwd = loginIconPwd;
        this.loginInChina = loginInChina;
        this.loginOutChina = loginOutChina;
        this.loginQq = loginQq;
        this.loginTvForgetPassword = loginTvForgetPassword;
        this.loginTvNoAccount = loginTvNoAccount;
        this.loginTvProtocal = loginTvProtocal;
        this.loginTvRegister = loginTvRegister;
        this.loginTvUserAgreement = loginTvUserAgreement;
        this.loginTwitter = loginTwitter;
        this.loginWechat = loginWechat;
        this.loginWeibo = loginWeibo;
        this.navigationbar = navigationbar;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityAiLoginBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityAiLoginBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_ai_login, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityAiLoginBinding bind(View rootView) {
        int i2 = R.id.is_show_password;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i2);
        if (imageView != null) {
            i2 = R.id.login_btn_login;
            Button button = (Button) ViewBindings.findChildViewById(rootView, i2);
            if (button != null) {
                i2 = R.id.login_cb_protocal;
                CheckBox checkBox = (CheckBox) ViewBindings.findChildViewById(rootView, i2);
                if (checkBox != null) {
                    i2 = R.id.login_ed_account;
                    CleanableEditText cleanableEditText = (CleanableEditText) ViewBindings.findChildViewById(rootView, i2);
                    if (cleanableEditText != null) {
                        i2 = R.id.login_ed_password;
                        CleanableEditText cleanableEditText2 = (CleanableEditText) ViewBindings.findChildViewById(rootView, i2);
                        if (cleanableEditText2 != null) {
                            i2 = R.id.login_facebook;
                            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                            if (imageView2 != null) {
                                i2 = R.id.login_google;
                                ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                                if (imageView3 != null) {
                                    i2 = R.id.login_icon_accout;
                                    ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                                    if (imageView4 != null) {
                                        i2 = R.id.login_icon_pwd;
                                        ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                                        if (imageView5 != null) {
                                            i2 = R.id.login_in_china;
                                            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                                            if (linearLayout != null) {
                                                i2 = R.id.login_out_china;
                                                LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                                                if (linearLayout2 != null) {
                                                    i2 = R.id.login_qq;
                                                    ImageView imageView6 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                                                    if (imageView6 != null) {
                                                        i2 = R.id.login_tv_forgetPassword;
                                                        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                        if (textView != null) {
                                                            i2 = R.id.login_tv_no_account;
                                                            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                            if (textView2 != null) {
                                                                i2 = R.id.login_tv_protocal;
                                                                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                if (textView3 != null) {
                                                                    i2 = R.id.login_tv_register;
                                                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                    if (textView4 != null) {
                                                                        i2 = R.id.login_tv_user_agreement;
                                                                        TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                        if (textView5 != null) {
                                                                            i2 = R.id.login_twitter;
                                                                            ImageView imageView7 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                                                                            if (imageView7 != null) {
                                                                                i2 = R.id.login_wechat;
                                                                                ImageView imageView8 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                                                                                if (imageView8 != null) {
                                                                                    i2 = R.id.login_weibo;
                                                                                    ImageView imageView9 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                                                                                    if (imageView9 != null) {
                                                                                        i2 = R.id.navigationbar;
                                                                                        NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
                                                                                        if (navigationBar != null) {
                                                                                            return new ActivityAiLoginBinding((LinearLayout) rootView, imageView, button, checkBox, cleanableEditText, cleanableEditText2, imageView2, imageView3, imageView4, imageView5, linearLayout, linearLayout2, imageView6, textView, textView2, textView3, textView4, textView5, imageView7, imageView8, imageView9, navigationBar);
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
