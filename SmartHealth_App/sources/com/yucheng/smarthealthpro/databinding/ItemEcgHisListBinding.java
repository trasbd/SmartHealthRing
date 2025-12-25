package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;

/* loaded from: classes4.dex */
public final class ItemEcgHisListBinding implements ViewBinding {
    public final ImageView ivPlayback;
    public final LinearLayout llDiagnose;
    public final LinearLayout llEcg;
    private final RelativeLayout rootView;
    public final TextView tvTime;

    private ItemEcgHisListBinding(RelativeLayout rootView, ImageView ivPlayback, LinearLayout llDiagnose, LinearLayout llEcg, TextView tvTime) {
        this.rootView = rootView;
        this.ivPlayback = ivPlayback;
        this.llDiagnose = llDiagnose;
        this.llEcg = llEcg;
        this.tvTime = tvTime;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ItemEcgHisListBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemEcgHisListBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.item_ecg_his_list, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemEcgHisListBinding bind(View rootView) {
        int i2 = R.id.iv_playback;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i2);
        if (imageView != null) {
            i2 = R.id.ll_diagnose;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
            if (linearLayout != null) {
                i2 = R.id.ll_ecg;
                LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                if (linearLayout2 != null) {
                    i2 = R.id.tv_time;
                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                    if (textView != null) {
                        return new ItemEcgHisListBinding((RelativeLayout) rootView, imageView, linearLayout, linearLayout2, textView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
