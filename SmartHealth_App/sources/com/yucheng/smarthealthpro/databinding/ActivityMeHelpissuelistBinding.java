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
public final class ActivityMeHelpissuelistBinding implements ViewBinding {
    public final NavigationBar navigationbar;
    public final RecyclerView recycleHelpIssue;
    private final LinearLayout rootView;

    private ActivityMeHelpissuelistBinding(LinearLayout rootView, NavigationBar navigationbar, RecyclerView recycleHelpIssue) {
        this.rootView = rootView;
        this.navigationbar = navigationbar;
        this.recycleHelpIssue = recycleHelpIssue;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityMeHelpissuelistBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityMeHelpissuelistBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_me_helpissuelist, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityMeHelpissuelistBinding bind(View rootView) {
        int i2 = R.id.navigationbar;
        NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
        if (navigationBar != null) {
            i2 = R.id.recycle_help_issue;
            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, i2);
            if (recyclerView != null) {
                return new ActivityMeHelpissuelistBinding((LinearLayout) rootView, navigationBar, recyclerView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
