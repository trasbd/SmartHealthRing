package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import com.yucheng.smarthealthpro.home.view.CarBgView;

/* loaded from: classes4.dex */
public final class ActivityEcgrecordBinding implements ViewBinding {
    public final CarBgView carBigView;
    public final NavigationBar navigationbar;
    public final RelativeLayout rlEcgMeasureView;
    private final FrameLayout rootView;
    public final SpinkitDialogIncludeBinding rvDialog;
    public final TextView tvBpm;
    public final TextView tvHrv;
    public final TextView tvMmHg;

    private ActivityEcgrecordBinding(FrameLayout rootView, CarBgView carBigView, NavigationBar navigationbar, RelativeLayout rlEcgMeasureView, SpinkitDialogIncludeBinding rvDialog, TextView tvBpm, TextView tvHrv, TextView tvMmHg) {
        this.rootView = rootView;
        this.carBigView = carBigView;
        this.navigationbar = navigationbar;
        this.rlEcgMeasureView = rlEcgMeasureView;
        this.rvDialog = rvDialog;
        this.tvBpm = tvBpm;
        this.tvHrv = tvHrv;
        this.tvMmHg = tvMmHg;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static ActivityEcgrecordBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityEcgrecordBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_ecgrecord, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityEcgrecordBinding bind(View rootView) {
        View viewFindChildViewById;
        int i2 = R.id.carBigView;
        CarBgView carBgView = (CarBgView) ViewBindings.findChildViewById(rootView, i2);
        if (carBgView != null) {
            i2 = R.id.navigationbar;
            NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
            if (navigationBar != null) {
                i2 = R.id.rl_ecg_measure_view;
                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                if (relativeLayout != null && (viewFindChildViewById = ViewBindings.findChildViewById(rootView, (i2 = R.id.rv_dialog))) != null) {
                    SpinkitDialogIncludeBinding spinkitDialogIncludeBindingBind = SpinkitDialogIncludeBinding.bind(viewFindChildViewById);
                    i2 = R.id.tv_bpm;
                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                    if (textView != null) {
                        i2 = R.id.tv_hrv;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                        if (textView2 != null) {
                            i2 = R.id.tv_mmHg;
                            TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                            if (textView3 != null) {
                                return new ActivityEcgrecordBinding((FrameLayout) rootView, carBgView, navigationBar, relativeLayout, spinkitDialogIncludeBindingBind, textView, textView2, textView3);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
