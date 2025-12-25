package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;

/* loaded from: classes4.dex */
public final class ActivityUsinghelpBinding implements ViewBinding {
    public final EditText etSearch;
    public final ImageView ivFeedBack;
    public final ImageView ivSearch;
    public final NavigationBar navigationbar;
    public final RecyclerView recycleHelpIssue;
    public final RecyclerView recycleHelpModule;
    private final RelativeLayout rootView;

    private ActivityUsinghelpBinding(RelativeLayout rootView, EditText etSearch, ImageView ivFeedBack, ImageView ivSearch, NavigationBar navigationbar, RecyclerView recycleHelpIssue, RecyclerView recycleHelpModule) {
        this.rootView = rootView;
        this.etSearch = etSearch;
        this.ivFeedBack = ivFeedBack;
        this.ivSearch = ivSearch;
        this.navigationbar = navigationbar;
        this.recycleHelpIssue = recycleHelpIssue;
        this.recycleHelpModule = recycleHelpModule;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ActivityUsinghelpBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityUsinghelpBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_usinghelp, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityUsinghelpBinding bind(View rootView) {
        int i2 = R.id.et_search;
        EditText editText = (EditText) ViewBindings.findChildViewById(rootView, i2);
        if (editText != null) {
            i2 = R.id.iv_feed_back;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i2);
            if (imageView != null) {
                i2 = R.id.iv_search;
                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                if (imageView2 != null) {
                    i2 = R.id.navigationbar;
                    NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
                    if (navigationBar != null) {
                        i2 = R.id.recycle_help_issue;
                        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, i2);
                        if (recyclerView != null) {
                            i2 = R.id.recycle_help_module;
                            RecyclerView recyclerView2 = (RecyclerView) ViewBindings.findChildViewById(rootView, i2);
                            if (recyclerView2 != null) {
                                return new ActivityUsinghelpBinding((RelativeLayout) rootView, editText, imageView, imageView2, navigationBar, recyclerView, recyclerView2);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
