package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import com.yucheng.smarthealthpro.view.CleanableEditText;

/* loaded from: classes4.dex */
public final class ActivityAiForwodBinding implements ViewBinding {
    public final ImageView isShowPassword;
    public final ImageView isShowPassword2;
    public final NavigationBar navigationbar;
    public final Button resetBtnReset;
    public final CleanableEditText resetEdConfirmPassword;
    public final CleanableEditText resetEdPassword;
    public final ImageView resetIconAccount;
    public final ImageView resetIconConfirmPwd;
    public final ImageView resetIconPwd;
    public final TextView resetTvAccount;
    private final ScrollView rootView;

    private ActivityAiForwodBinding(ScrollView rootView, ImageView isShowPassword, ImageView isShowPassword2, NavigationBar navigationbar, Button resetBtnReset, CleanableEditText resetEdConfirmPassword, CleanableEditText resetEdPassword, ImageView resetIconAccount, ImageView resetIconConfirmPwd, ImageView resetIconPwd, TextView resetTvAccount) {
        this.rootView = rootView;
        this.isShowPassword = isShowPassword;
        this.isShowPassword2 = isShowPassword2;
        this.navigationbar = navigationbar;
        this.resetBtnReset = resetBtnReset;
        this.resetEdConfirmPassword = resetEdConfirmPassword;
        this.resetEdPassword = resetEdPassword;
        this.resetIconAccount = resetIconAccount;
        this.resetIconConfirmPwd = resetIconConfirmPwd;
        this.resetIconPwd = resetIconPwd;
        this.resetTvAccount = resetTvAccount;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ScrollView getRoot() {
        return this.rootView;
    }

    public static ActivityAiForwodBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityAiForwodBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_ai_forwod, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityAiForwodBinding bind(View rootView) {
        int i2 = R.id.is_show_password;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i2);
        if (imageView != null) {
            i2 = R.id.is_show_password2;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
            if (imageView2 != null) {
                i2 = R.id.navigationbar;
                NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
                if (navigationBar != null) {
                    i2 = R.id.reset_btn_reset;
                    Button button = (Button) ViewBindings.findChildViewById(rootView, i2);
                    if (button != null) {
                        i2 = R.id.reset_ed_confirm_password;
                        CleanableEditText cleanableEditText = (CleanableEditText) ViewBindings.findChildViewById(rootView, i2);
                        if (cleanableEditText != null) {
                            i2 = R.id.reset_ed_password;
                            CleanableEditText cleanableEditText2 = (CleanableEditText) ViewBindings.findChildViewById(rootView, i2);
                            if (cleanableEditText2 != null) {
                                i2 = R.id.reset_icon_account;
                                ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                                if (imageView3 != null) {
                                    i2 = R.id.reset_icon_confirm_pwd;
                                    ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                                    if (imageView4 != null) {
                                        i2 = R.id.reset_icon_pwd;
                                        ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                                        if (imageView5 != null) {
                                            i2 = R.id.reset_tv_account;
                                            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                            if (textView != null) {
                                                return new ActivityAiForwodBinding((ScrollView) rootView, imageView, imageView2, navigationBar, button, cleanableEditText, cleanableEditText2, imageView3, imageView4, imageView5, textView);
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
