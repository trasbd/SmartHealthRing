package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.tabs.TabLayout;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;

/* loaded from: classes4.dex */
public final class ActivityHealthyBinding implements ViewBinding {
    public final EditText etSearch;
    public final ImageView ivSearch;
    public final NavigationBar navigationbar;
    public final RecyclerView recycleHealthy;
    private final LinearLayout rootView;
    public final SmartRefreshLayout srlHealthy;
    public final TabLayout tabLayout;
    public final WebView webView;

    private ActivityHealthyBinding(LinearLayout rootView, EditText etSearch, ImageView ivSearch, NavigationBar navigationbar, RecyclerView recycleHealthy, SmartRefreshLayout srlHealthy, TabLayout tabLayout, WebView webView) {
        this.rootView = rootView;
        this.etSearch = etSearch;
        this.ivSearch = ivSearch;
        this.navigationbar = navigationbar;
        this.recycleHealthy = recycleHealthy;
        this.srlHealthy = srlHealthy;
        this.tabLayout = tabLayout;
        this.webView = webView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityHealthyBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityHealthyBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_healthy, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityHealthyBinding bind(View rootView) {
        int i2 = R.id.et_search;
        EditText editText = (EditText) ViewBindings.findChildViewById(rootView, i2);
        if (editText != null) {
            i2 = R.id.iv_search;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i2);
            if (imageView != null) {
                i2 = R.id.navigationbar;
                NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
                if (navigationBar != null) {
                    i2 = R.id.recycle_healthy;
                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, i2);
                    if (recyclerView != null) {
                        i2 = R.id.srl_healthy;
                        SmartRefreshLayout smartRefreshLayout = (SmartRefreshLayout) ViewBindings.findChildViewById(rootView, i2);
                        if (smartRefreshLayout != null) {
                            i2 = R.id.tabLayout;
                            TabLayout tabLayout = (TabLayout) ViewBindings.findChildViewById(rootView, i2);
                            if (tabLayout != null) {
                                i2 = R.id.webView;
                                WebView webView = (WebView) ViewBindings.findChildViewById(rootView, i2);
                                if (webView != null) {
                                    return new ActivityHealthyBinding((LinearLayout) rootView, editText, imageView, navigationBar, recyclerView, smartRefreshLayout, tabLayout, webView);
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
