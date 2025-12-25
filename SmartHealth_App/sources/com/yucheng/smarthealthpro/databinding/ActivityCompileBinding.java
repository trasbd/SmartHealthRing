package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;

/* loaded from: classes4.dex */
public final class ActivityCompileBinding implements ViewBinding {
    public final NavigationBar navigationbar;
    public final RecyclerView recycleCompile;
    public final RecyclerView recycleCompileAdd;
    private final LinearLayout rootView;

    private ActivityCompileBinding(LinearLayout rootView, NavigationBar navigationbar, RecyclerView recycleCompile, RecyclerView recycleCompileAdd) {
        this.rootView = rootView;
        this.navigationbar = navigationbar;
        this.recycleCompile = recycleCompile;
        this.recycleCompileAdd = recycleCompileAdd;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityCompileBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityCompileBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_compile, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityCompileBinding bind(View rootView) {
        int i2 = R.id.navigationbar;
        NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
        if (navigationBar != null) {
            i2 = R.id.recycle_compile;
            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, i2);
            if (recyclerView != null) {
                i2 = R.id.recycle_compile_add;
                RecyclerView recyclerView2 = (RecyclerView) ViewBindings.findChildViewById(rootView, i2);
                if (recyclerView2 != null) {
                    return new ActivityCompileBinding((LinearLayout) rootView, navigationBar, recyclerView, recyclerView2);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
