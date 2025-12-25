package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager2.widget.ViewPager2;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.view.Vp2IndicatorView;

/* loaded from: classes4.dex */
public final class DialogLifeTipBinding implements ViewBinding {
    public final ImageView image;
    public final Vp2IndicatorView indicator;
    private final LinearLayoutCompat rootView;
    public final TextView tvDesc;
    public final TextView tvKnow;
    public final ViewPager2 viewPage;

    private DialogLifeTipBinding(LinearLayoutCompat rootView, ImageView image, Vp2IndicatorView indicator, TextView tvDesc, TextView tvKnow, ViewPager2 viewPage) {
        this.rootView = rootView;
        this.image = image;
        this.indicator = indicator;
        this.tvDesc = tvDesc;
        this.tvKnow = tvKnow;
        this.viewPage = viewPage;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayoutCompat getRoot() {
        return this.rootView;
    }

    public static DialogLifeTipBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static DialogLifeTipBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.dialog_life_tip, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DialogLifeTipBinding bind(View rootView) {
        int i2 = R.id.image;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i2);
        if (imageView != null) {
            i2 = R.id.indicator;
            Vp2IndicatorView vp2IndicatorView = (Vp2IndicatorView) ViewBindings.findChildViewById(rootView, i2);
            if (vp2IndicatorView != null) {
                i2 = R.id.tvDesc;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                if (textView != null) {
                    i2 = R.id.tvKnow;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                    if (textView2 != null) {
                        i2 = R.id.viewPage;
                        ViewPager2 viewPager2 = (ViewPager2) ViewBindings.findChildViewById(rootView, i2);
                        if (viewPager2 != null) {
                            return new DialogLifeTipBinding((LinearLayoutCompat) rootView, imageView, vp2IndicatorView, textView, textView2, viewPager2);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
