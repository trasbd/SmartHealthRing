package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;

/* loaded from: classes4.dex */
public final class ItemEcgDiagnose2Binding implements ViewBinding {
    public final CardView cv1;
    public final CardView cv2;
    public final CardView cv3;
    public final CardView cv4;
    public final CardView cv5;
    public final ImageView ivPoint;
    public final LinearLayoutCompat llColor;
    private final CardView rootView;
    public final SeekBar sbPoint;
    public final TextView tvData;
    public final TextView tvName;
    public final TextView tvState;

    private ItemEcgDiagnose2Binding(CardView rootView, CardView cv1, CardView cv2, CardView cv3, CardView cv4, CardView cv5, ImageView ivPoint, LinearLayoutCompat llColor, SeekBar sbPoint, TextView tvData, TextView tvName, TextView tvState) {
        this.rootView = rootView;
        this.cv1 = cv1;
        this.cv2 = cv2;
        this.cv3 = cv3;
        this.cv4 = cv4;
        this.cv5 = cv5;
        this.ivPoint = ivPoint;
        this.llColor = llColor;
        this.sbPoint = sbPoint;
        this.tvData = tvData;
        this.tvName = tvName;
        this.tvState = tvState;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CardView getRoot() {
        return this.rootView;
    }

    public static ItemEcgDiagnose2Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemEcgDiagnose2Binding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.item_ecg_diagnose2, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemEcgDiagnose2Binding bind(View rootView) {
        int i2 = R.id.cv_1;
        CardView cardView = (CardView) ViewBindings.findChildViewById(rootView, i2);
        if (cardView != null) {
            i2 = R.id.cv_2;
            CardView cardView2 = (CardView) ViewBindings.findChildViewById(rootView, i2);
            if (cardView2 != null) {
                i2 = R.id.cv_3;
                CardView cardView3 = (CardView) ViewBindings.findChildViewById(rootView, i2);
                if (cardView3 != null) {
                    i2 = R.id.cv_4;
                    CardView cardView4 = (CardView) ViewBindings.findChildViewById(rootView, i2);
                    if (cardView4 != null) {
                        i2 = R.id.cv_5;
                        CardView cardView5 = (CardView) ViewBindings.findChildViewById(rootView, i2);
                        if (cardView5 != null) {
                            i2 = R.id.iv_point;
                            ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                            if (imageView != null) {
                                i2 = R.id.ll_color;
                                LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) ViewBindings.findChildViewById(rootView, i2);
                                if (linearLayoutCompat != null) {
                                    i2 = R.id.sb_point;
                                    SeekBar seekBar = (SeekBar) ViewBindings.findChildViewById(rootView, i2);
                                    if (seekBar != null) {
                                        i2 = R.id.tvData;
                                        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                        if (textView != null) {
                                            i2 = R.id.tvName;
                                            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                            if (textView2 != null) {
                                                i2 = R.id.tvState;
                                                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                if (textView3 != null) {
                                                    return new ItemEcgDiagnose2Binding((CardView) rootView, cardView, cardView2, cardView3, cardView4, cardView5, imageView, linearLayoutCompat, seekBar, textView, textView2, textView3);
                                                }
                                            }
                                        }
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
