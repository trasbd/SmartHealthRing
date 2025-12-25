package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Switch;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;

/* loaded from: classes4.dex */
public final class ActivityMePushmessageBinding implements ViewBinding {
    public final NavigationBar navigationbar;
    public final RecyclerView recycleView;
    private final LinearLayout rootView;
    public final Switch switchMessage;

    private ActivityMePushmessageBinding(LinearLayout rootView, NavigationBar navigationbar, RecyclerView recycleView, Switch switchMessage) {
        this.rootView = rootView;
        this.navigationbar = navigationbar;
        this.recycleView = recycleView;
        this.switchMessage = switchMessage;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityMePushmessageBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityMePushmessageBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_me_pushmessage, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityMePushmessageBinding bind(View rootView) {
        int i2 = R.id.navigationbar;
        NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
        if (navigationBar != null) {
            i2 = R.id.recycle_view;
            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, i2);
            if (recyclerView != null) {
                i2 = R.id.switch_message;
                Switch r3 = (Switch) ViewBindings.findChildViewById(rootView, i2);
                if (r3 != null) {
                    return new ActivityMePushmessageBinding((LinearLayout) rootView, navigationBar, recyclerView, r3);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
