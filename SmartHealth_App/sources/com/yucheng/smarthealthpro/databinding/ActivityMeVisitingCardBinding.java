package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;

/* loaded from: classes4.dex */
public final class ActivityMeVisitingCardBinding implements ViewBinding {
    public final ImageView ivVisitingCardQr;
    public final NavigationBar navigationbar;
    private final LinearLayout rootView;
    public final TextView tvVisitingCardBunding;

    private ActivityMeVisitingCardBinding(LinearLayout rootView, ImageView ivVisitingCardQr, NavigationBar navigationbar, TextView tvVisitingCardBunding) {
        this.rootView = rootView;
        this.ivVisitingCardQr = ivVisitingCardQr;
        this.navigationbar = navigationbar;
        this.tvVisitingCardBunding = tvVisitingCardBunding;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityMeVisitingCardBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityMeVisitingCardBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_me_visiting_card, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityMeVisitingCardBinding bind(View rootView) {
        int i2 = R.id.iv_visiting_card_qr;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i2);
        if (imageView != null) {
            i2 = R.id.navigationbar;
            NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
            if (navigationBar != null) {
                i2 = R.id.tv_visiting_card_bunding;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                if (textView != null) {
                    return new ActivityMeVisitingCardBinding((LinearLayout) rootView, imageView, navigationBar, textView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
