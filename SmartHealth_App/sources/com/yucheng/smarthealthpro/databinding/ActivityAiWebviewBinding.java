package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;

/* loaded from: classes4.dex */
public final class ActivityAiWebviewBinding implements ViewBinding {
    public final LinearLayout llWeb;
    public final NavigationBar navigationbar;
    private final LinearLayout rootView;
    public final WebView webView;

    private ActivityAiWebviewBinding(LinearLayout rootView, LinearLayout llWeb, NavigationBar navigationbar, WebView webView) {
        this.rootView = rootView;
        this.llWeb = llWeb;
        this.navigationbar = navigationbar;
        this.webView = webView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityAiWebviewBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityAiWebviewBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_ai_webview, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityAiWebviewBinding bind(View rootView) {
        int i2 = R.id.ll_web;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
        if (linearLayout != null) {
            i2 = R.id.navigationbar;
            NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
            if (navigationBar != null) {
                i2 = R.id.webView;
                WebView webView = (WebView) ViewBindings.findChildViewById(rootView, i2);
                if (webView != null) {
                    return new ActivityAiWebviewBinding((LinearLayout) rootView, linearLayout, navigationBar, webView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
