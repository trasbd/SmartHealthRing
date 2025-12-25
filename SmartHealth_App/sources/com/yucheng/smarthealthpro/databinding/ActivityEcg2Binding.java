package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import com.yucheng.smarthealthpro.home.view.CardiographView;

/* loaded from: classes4.dex */
public final class ActivityEcg2Binding implements ViewBinding {
    public final CardiographView cardiographView;
    public final FunctionItemBottomIncludeBinding includeItemBottom;
    public final ImageView ivPlay;
    public final NavigationBar navigationbar;
    private final RelativeLayout rootView;
    public final SeekBar sbProgress;
    public final SmartRefreshLayout srlEcg;
    public final TextView tvBpm;
    public final TextView tvHrv;
    public final TextView tvMmhg;

    private ActivityEcg2Binding(RelativeLayout rootView, CardiographView cardiographView, FunctionItemBottomIncludeBinding includeItemBottom, ImageView ivPlay, NavigationBar navigationbar, SeekBar sbProgress, SmartRefreshLayout srlEcg, TextView tvBpm, TextView tvHrv, TextView tvMmhg) {
        this.rootView = rootView;
        this.cardiographView = cardiographView;
        this.includeItemBottom = includeItemBottom;
        this.ivPlay = ivPlay;
        this.navigationbar = navigationbar;
        this.sbProgress = sbProgress;
        this.srlEcg = srlEcg;
        this.tvBpm = tvBpm;
        this.tvHrv = tvHrv;
        this.tvMmhg = tvMmhg;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ActivityEcg2Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityEcg2Binding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_ecg2, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityEcg2Binding bind(View rootView) {
        View viewFindChildViewById;
        int i2 = R.id.cardiographView;
        CardiographView cardiographView = (CardiographView) ViewBindings.findChildViewById(rootView, i2);
        if (cardiographView != null && (viewFindChildViewById = ViewBindings.findChildViewById(rootView, (i2 = R.id.include_item_bottom))) != null) {
            FunctionItemBottomIncludeBinding functionItemBottomIncludeBindingBind = FunctionItemBottomIncludeBinding.bind(viewFindChildViewById);
            i2 = R.id.iv_play;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i2);
            if (imageView != null) {
                i2 = R.id.navigationbar;
                NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
                if (navigationBar != null) {
                    i2 = R.id.sb_progress;
                    SeekBar seekBar = (SeekBar) ViewBindings.findChildViewById(rootView, i2);
                    if (seekBar != null) {
                        i2 = R.id.srl_ecg;
                        SmartRefreshLayout smartRefreshLayout = (SmartRefreshLayout) ViewBindings.findChildViewById(rootView, i2);
                        if (smartRefreshLayout != null) {
                            i2 = R.id.tv_bpm;
                            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                            if (textView != null) {
                                i2 = R.id.tv_hrv;
                                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                if (textView2 != null) {
                                    i2 = R.id.tv_mmhg;
                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                    if (textView3 != null) {
                                        return new ActivityEcg2Binding((RelativeLayout) rootView, cardiographView, functionItemBottomIncludeBindingBind, imageView, navigationBar, seekBar, smartRefreshLayout, textView, textView2, textView3);
                                    }
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
