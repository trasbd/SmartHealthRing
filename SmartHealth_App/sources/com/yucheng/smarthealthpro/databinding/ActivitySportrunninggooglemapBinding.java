package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;

/* loaded from: classes4.dex */
public final class ActivitySportrunninggooglemapBinding implements ViewBinding {
    public final ImageView ivBack;
    public final ImageView ivLocation;
    private final RelativeLayout rootView;
    public final TextView tvFirstValue;
    public final TextView tvFourthlyValue;
    public final TextView tvKilometreValue;
    public final TextView tvUnit;

    private ActivitySportrunninggooglemapBinding(RelativeLayout rootView, ImageView ivBack, ImageView ivLocation, TextView tvFirstValue, TextView tvFourthlyValue, TextView tvKilometreValue, TextView tvUnit) {
        this.rootView = rootView;
        this.ivBack = ivBack;
        this.ivLocation = ivLocation;
        this.tvFirstValue = tvFirstValue;
        this.tvFourthlyValue = tvFourthlyValue;
        this.tvKilometreValue = tvKilometreValue;
        this.tvUnit = tvUnit;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ActivitySportrunninggooglemapBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivitySportrunninggooglemapBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_sportrunninggooglemap, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivitySportrunninggooglemapBinding bind(View rootView) {
        int i2 = R.id.iv_back;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i2);
        if (imageView != null) {
            i2 = R.id.iv_location;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
            if (imageView2 != null) {
                i2 = R.id.tv_first_value;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                if (textView != null) {
                    i2 = R.id.tv_fourthly_value;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                    if (textView2 != null) {
                        i2 = R.id.tv_kilometre_value;
                        TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                        if (textView3 != null) {
                            i2 = R.id.tv_unit;
                            TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                            if (textView4 != null) {
                                return new ActivitySportrunninggooglemapBinding((RelativeLayout) rootView, imageView, imageView2, textView, textView2, textView3, textView4);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
