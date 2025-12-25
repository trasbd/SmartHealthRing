package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;

/* loaded from: classes4.dex */
public final class ActivityWebBinding implements ViewBinding {
    public final NavigationBar navigationbar;
    private final RelativeLayout rootView;
    public final WebView webView;

    private ActivityWebBinding(RelativeLayout rootView, NavigationBar navigationbar, WebView webView) {
        this.rootView = rootView;
        this.navigationbar = navigationbar;
        this.webView = webView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ActivityWebBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityWebBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_web, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityWebBinding bind(View rootView) {
        int i2 = R.id.navigationbar;
        NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
        if (navigationBar != null) {
            i2 = R.id.webView;
            WebView webView = (WebView) ViewBindings.findChildViewById(rootView, i2);
            if (webView != null) {
                return new ActivityWebBinding((RelativeLayout) rootView, navigationBar, webView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
