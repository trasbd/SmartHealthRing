package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;

/* loaded from: classes4.dex */
public final class ItemSportHisRunBinding implements ViewBinding {
    public final ImageButton delete;
    public final ImageView ivSportImg;
    public final ImageView ivUnit;
    private final LinearLayout rootView;
    public final TextView tvDistance;
    public final TextView tvKeepTime;
    public final TextView tvMotorPattern;
    public final TextView tvTime;
    public final TextView tvUnit;

    private ItemSportHisRunBinding(LinearLayout rootView, ImageButton delete, ImageView ivSportImg, ImageView ivUnit, TextView tvDistance, TextView tvKeepTime, TextView tvMotorPattern, TextView tvTime, TextView tvUnit) {
        this.rootView = rootView;
        this.delete = delete;
        this.ivSportImg = ivSportImg;
        this.ivUnit = ivUnit;
        this.tvDistance = tvDistance;
        this.tvKeepTime = tvKeepTime;
        this.tvMotorPattern = tvMotorPattern;
        this.tvTime = tvTime;
        this.tvUnit = tvUnit;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ItemSportHisRunBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemSportHisRunBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.item_sport_his_run, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemSportHisRunBinding bind(View rootView) {
        int i2 = R.id.delete;
        ImageButton imageButton = (ImageButton) ViewBindings.findChildViewById(rootView, i2);
        if (imageButton != null) {
            i2 = R.id.iv_sport_img;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i2);
            if (imageView != null) {
                i2 = R.id.iv_unit;
                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                if (imageView2 != null) {
                    i2 = R.id.tv_distance;
                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                    if (textView != null) {
                        i2 = R.id.tv_keep_time;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                        if (textView2 != null) {
                            i2 = R.id.tv_motorPattern;
                            TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                            if (textView3 != null) {
                                i2 = R.id.tv_time;
                                TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                if (textView4 != null) {
                                    i2 = R.id.tv_unit;
                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                    if (textView5 != null) {
                                        return new ItemSportHisRunBinding((LinearLayout) rootView, imageButton, imageView, imageView2, textView, textView2, textView3, textView4, textView5);
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
