package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.airbnb.lottie.LottieAnimationView;
import com.yucheng.smarthealthpro.R;

/* loaded from: classes4.dex */
public final class MeasureIncludeBinding implements ViewBinding {
    public final ImageView ivLottieBg;
    public final LottieAnimationView lottie;
    private final LinearLayout rootView;
    public final TextView tvLottieData;
    public final TextView tvLottieDataUnit;
    public final TextView tvStartButton;

    private MeasureIncludeBinding(LinearLayout rootView, ImageView ivLottieBg, LottieAnimationView lottie, TextView tvLottieData, TextView tvLottieDataUnit, TextView tvStartButton) {
        this.rootView = rootView;
        this.ivLottieBg = ivLottieBg;
        this.lottie = lottie;
        this.tvLottieData = tvLottieData;
        this.tvLottieDataUnit = tvLottieDataUnit;
        this.tvStartButton = tvStartButton;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static MeasureIncludeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static MeasureIncludeBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.measure_include, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static MeasureIncludeBinding bind(View rootView) {
        int i2 = R.id.iv_lottie_bg;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i2);
        if (imageView != null) {
            i2 = R.id.lottie;
            LottieAnimationView lottieAnimationView = (LottieAnimationView) ViewBindings.findChildViewById(rootView, i2);
            if (lottieAnimationView != null) {
                i2 = R.id.tv_lottie_data;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                if (textView != null) {
                    i2 = R.id.tv_lottie_data_unit;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                    if (textView2 != null) {
                        i2 = R.id.tv_start_button;
                        TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                        if (textView3 != null) {
                            return new MeasureIncludeBinding((LinearLayout) rootView, imageView, lottieAnimationView, textView, textView2, textView3);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
