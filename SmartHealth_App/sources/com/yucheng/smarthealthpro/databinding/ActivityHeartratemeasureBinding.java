package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;

/* loaded from: classes4.dex */
public final class ActivityHeartratemeasureBinding implements ViewBinding {
    public final NavigationBar navigationbar;
    private final LinearLayout rootView;

    private ActivityHeartratemeasureBinding(LinearLayout rootView, NavigationBar navigationbar) {
        this.rootView = rootView;
        this.navigationbar = navigationbar;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityHeartratemeasureBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityHeartratemeasureBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_heartratemeasure, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityHeartratemeasureBinding bind(View rootView) {
        int i2 = R.id.navigationbar;
        NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
        if (navigationBar != null) {
            return new ActivityHeartratemeasureBinding((LinearLayout) rootView, navigationBar);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
