package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;

/* loaded from: classes4.dex */
public final class ActivitySosSettingsBinding implements ViewBinding {
    public final Button btnSetting;
    public final NavigationBar navigationBar;
    public final RecyclerView recyclerView;
    private final ConstraintLayout rootView;
    public final View space;
    public final TextView tvTitle;
    public final TextView tvWaring;

    private ActivitySosSettingsBinding(ConstraintLayout rootView, Button btnSetting, NavigationBar navigationBar, RecyclerView recyclerView, View space, TextView tvTitle, TextView tvWaring) {
        this.rootView = rootView;
        this.btnSetting = btnSetting;
        this.navigationBar = navigationBar;
        this.recyclerView = recyclerView;
        this.space = space;
        this.tvTitle = tvTitle;
        this.tvWaring = tvWaring;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivitySosSettingsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivitySosSettingsBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_sos_settings, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivitySosSettingsBinding bind(View rootView) {
        View viewFindChildViewById;
        int i2 = R.id.btn_setting;
        Button button = (Button) ViewBindings.findChildViewById(rootView, i2);
        if (button != null) {
            i2 = R.id.navigationBar;
            NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
            if (navigationBar != null) {
                i2 = R.id.recyclerView;
                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, i2);
                if (recyclerView != null && (viewFindChildViewById = ViewBindings.findChildViewById(rootView, (i2 = R.id.space))) != null) {
                    i2 = R.id.tvTitle;
                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                    if (textView != null) {
                        i2 = R.id.tvWaring;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                        if (textView2 != null) {
                            return new ActivitySosSettingsBinding((ConstraintLayout) rootView, button, navigationBar, recyclerView, viewFindChildViewById, textView, textView2);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
