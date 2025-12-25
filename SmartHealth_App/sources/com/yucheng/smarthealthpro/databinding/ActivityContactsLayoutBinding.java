package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import com.yucheng.smarthealthpro.me.setting.contacts.view.SideBar;

/* loaded from: classes4.dex */
public final class ActivityContactsLayoutBinding implements ViewBinding {
    public final LinearLayout lyContacts;
    public final NavigationBar navigationbar;
    public final RecyclerView rlRecycleView;
    private final RelativeLayout rootView;
    public final SideBar sideBar;

    private ActivityContactsLayoutBinding(RelativeLayout rootView, LinearLayout lyContacts, NavigationBar navigationbar, RecyclerView rlRecycleView, SideBar sideBar) {
        this.rootView = rootView;
        this.lyContacts = lyContacts;
        this.navigationbar = navigationbar;
        this.rlRecycleView = rlRecycleView;
        this.sideBar = sideBar;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ActivityContactsLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityContactsLayoutBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_contacts_layout, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityContactsLayoutBinding bind(View rootView) {
        int i2 = R.id.ly_contacts;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
        if (linearLayout != null) {
            i2 = R.id.navigationbar;
            NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
            if (navigationBar != null) {
                i2 = R.id.rl_recycle_view;
                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, i2);
                if (recyclerView != null) {
                    i2 = R.id.side_bar;
                    SideBar sideBar = (SideBar) ViewBindings.findChildViewById(rootView, i2);
                    if (sideBar != null) {
                        return new ActivityContactsLayoutBinding((RelativeLayout) rootView, linearLayout, navigationBar, recyclerView, sideBar);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
