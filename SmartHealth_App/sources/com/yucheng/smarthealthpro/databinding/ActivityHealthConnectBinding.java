package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;

/* loaded from: classes4.dex */
public final class ActivityHealthConnectBinding implements ViewBinding {
    public final AppCompatButton btnOpen;
    public final AppCompatButton btnPermissions;
    public final ConstraintLayout layoutBody;
    public final RecyclerView recyclerView;
    private final ConstraintLayout rootView;
    public final Toolbar toolbar;
    public final TextView tvDesc;
    public final TextView tvToolbarTitle;

    private ActivityHealthConnectBinding(ConstraintLayout rootView, AppCompatButton btnOpen, AppCompatButton btnPermissions, ConstraintLayout layoutBody, RecyclerView recyclerView, Toolbar toolbar, TextView tvDesc, TextView tvToolbarTitle) {
        this.rootView = rootView;
        this.btnOpen = btnOpen;
        this.btnPermissions = btnPermissions;
        this.layoutBody = layoutBody;
        this.recyclerView = recyclerView;
        this.toolbar = toolbar;
        this.tvDesc = tvDesc;
        this.tvToolbarTitle = tvToolbarTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityHealthConnectBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityHealthConnectBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_health_connect, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityHealthConnectBinding bind(View rootView) {
        int i2 = R.id.btnOpen;
        AppCompatButton appCompatButton = (AppCompatButton) ViewBindings.findChildViewById(rootView, i2);
        if (appCompatButton != null) {
            i2 = R.id.btnPermissions;
            AppCompatButton appCompatButton2 = (AppCompatButton) ViewBindings.findChildViewById(rootView, i2);
            if (appCompatButton2 != null) {
                i2 = R.id.layoutBody;
                ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i2);
                if (constraintLayout != null) {
                    i2 = R.id.recyclerView;
                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, i2);
                    if (recyclerView != null) {
                        i2 = R.id.toolbar;
                        Toolbar toolbar = (Toolbar) ViewBindings.findChildViewById(rootView, i2);
                        if (toolbar != null) {
                            i2 = R.id.tvDesc;
                            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                            if (textView != null) {
                                i2 = R.id.tvToolbarTitle;
                                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                if (textView2 != null) {
                                    return new ActivityHealthConnectBinding((ConstraintLayout) rootView, appCompatButton, appCompatButton2, constraintLayout, recyclerView, toolbar, textView, textView2);
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
