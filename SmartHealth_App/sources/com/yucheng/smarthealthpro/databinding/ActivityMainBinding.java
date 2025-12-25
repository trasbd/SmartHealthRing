package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.home.view.NoScrollViewPager;

/* loaded from: classes4.dex */
public final class ActivityMainBinding implements ViewBinding {
    public final BottomNavigationView bnvMain;
    private final RelativeLayout rootView;
    public final SpinkitDialogIncludeBinding rvDialog;
    public final NoScrollViewPager vpMain;

    private ActivityMainBinding(RelativeLayout rootView, BottomNavigationView bnvMain, SpinkitDialogIncludeBinding rvDialog, NoScrollViewPager vpMain) {
        this.rootView = rootView;
        this.bnvMain = bnvMain;
        this.rvDialog = rvDialog;
        this.vpMain = vpMain;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ActivityMainBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityMainBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_main, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityMainBinding bind(View rootView) {
        View viewFindChildViewById;
        int i2 = R.id.bnv_main;
        BottomNavigationView bottomNavigationView = (BottomNavigationView) ViewBindings.findChildViewById(rootView, i2);
        if (bottomNavigationView != null && (viewFindChildViewById = ViewBindings.findChildViewById(rootView, (i2 = R.id.rv_dialog))) != null) {
            SpinkitDialogIncludeBinding spinkitDialogIncludeBindingBind = SpinkitDialogIncludeBinding.bind(viewFindChildViewById);
            int i3 = R.id.vp_main;
            NoScrollViewPager noScrollViewPager = (NoScrollViewPager) ViewBindings.findChildViewById(rootView, i3);
            if (noScrollViewPager != null) {
                return new ActivityMainBinding((RelativeLayout) rootView, bottomNavigationView, spinkitDialogIncludeBindingBind, noScrollViewPager);
            }
            i2 = i3;
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
