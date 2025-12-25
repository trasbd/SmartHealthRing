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
public final class ActivityMeAboutusBinding implements ViewBinding {
    public final ImageView ivHead;
    public final NavigationBar navigationbar;
    public final RelativeLayout rlAppVersions;
    public final RelativeLayout rlDeveloper;
    public final RelativeLayout rlFirmwareRecovery;
    public final RelativeLayout rlFirmwareUpgrade;
    public final RelativeLayout rlPrivacyPolicy;
    public final RelativeLayout rlToFaq;
    public final RelativeLayout rlToPublicBeta;
    public final RelativeLayout rlToScoring;
    public final RelativeLayout rlUserAgreement;
    private final LinearLayout rootView;
    public final TextView tvAppName;
    public final TextView tvVersions;
    public final View vDeveloper;
    public final View vFaq;
    public final View vPublicBeta;

    private ActivityMeAboutusBinding(LinearLayout rootView, ImageView ivHead, NavigationBar navigationbar, RelativeLayout rlAppVersions, RelativeLayout rlDeveloper, RelativeLayout rlFirmwareRecovery, RelativeLayout rlFirmwareUpgrade, RelativeLayout rlPrivacyPolicy, RelativeLayout rlToFaq, RelativeLayout rlToPublicBeta, RelativeLayout rlToScoring, RelativeLayout rlUserAgreement, TextView tvAppName, TextView tvVersions, View vDeveloper, View vFaq, View vPublicBeta) {
        this.rootView = rootView;
        this.ivHead = ivHead;
        this.navigationbar = navigationbar;
        this.rlAppVersions = rlAppVersions;
        this.rlDeveloper = rlDeveloper;
        this.rlFirmwareRecovery = rlFirmwareRecovery;
        this.rlFirmwareUpgrade = rlFirmwareUpgrade;
        this.rlPrivacyPolicy = rlPrivacyPolicy;
        this.rlToFaq = rlToFaq;
        this.rlToPublicBeta = rlToPublicBeta;
        this.rlToScoring = rlToScoring;
        this.rlUserAgreement = rlUserAgreement;
        this.tvAppName = tvAppName;
        this.tvVersions = tvVersions;
        this.vDeveloper = vDeveloper;
        this.vFaq = vFaq;
        this.vPublicBeta = vPublicBeta;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityMeAboutusBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityMeAboutusBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_me_aboutus, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityMeAboutusBinding bind(View rootView) {
        View viewFindChildViewById;
        View viewFindChildViewById2;
        View viewFindChildViewById3;
        int i2 = R.id.iv_head;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i2);
        if (imageView != null) {
            i2 = R.id.navigationbar;
            NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
            if (navigationBar != null) {
                i2 = R.id.rl_app_versions;
                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                if (relativeLayout != null) {
                    i2 = R.id.rl_developer;
                    RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                    if (relativeLayout2 != null) {
                        i2 = R.id.rl_firmware_recovery;
                        RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                        if (relativeLayout3 != null) {
                            i2 = R.id.rl_firmware_upgrade;
                            RelativeLayout relativeLayout4 = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                            if (relativeLayout4 != null) {
                                i2 = R.id.rl_privacy_policy;
                                RelativeLayout relativeLayout5 = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                                if (relativeLayout5 != null) {
                                    i2 = R.id.rl_to_faq;
                                    RelativeLayout relativeLayout6 = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                                    if (relativeLayout6 != null) {
                                        i2 = R.id.rl_to_public_beta;
                                        RelativeLayout relativeLayout7 = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                                        if (relativeLayout7 != null) {
                                            i2 = R.id.rl_to_scoring;
                                            RelativeLayout relativeLayout8 = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                                            if (relativeLayout8 != null) {
                                                i2 = R.id.rl_user_agreement;
                                                RelativeLayout relativeLayout9 = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                                                if (relativeLayout9 != null) {
                                                    i2 = R.id.tvAppName;
                                                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                    if (textView != null) {
                                                        i2 = R.id.tv_versions;
                                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                        if (textView2 != null && (viewFindChildViewById = ViewBindings.findChildViewById(rootView, (i2 = R.id.v_developer))) != null && (viewFindChildViewById2 = ViewBindings.findChildViewById(rootView, (i2 = R.id.v_faq))) != null && (viewFindChildViewById3 = ViewBindings.findChildViewById(rootView, (i2 = R.id.v_public_beta))) != null) {
                                                            return new ActivityMeAboutusBinding((LinearLayout) rootView, imageView, navigationBar, relativeLayout, relativeLayout2, relativeLayout3, relativeLayout4, relativeLayout5, relativeLayout6, relativeLayout7, relativeLayout8, relativeLayout9, textView, textView2, viewFindChildViewById, viewFindChildViewById2, viewFindChildViewById3);
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
