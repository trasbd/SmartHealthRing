package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.Barrier;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;

/* loaded from: classes4.dex */
public final class ItemEcgDiagnoseHeaderBinding implements ViewBinding {
    public final Barrier barrier;
    private final ConstraintLayout rootView;
    public final TextView tvAgeTag;
    public final TextView tvAgeValue;
    public final TextView tvBPTag;
    public final TextView tvBPValue;
    public final TextView tvCardTitle;
    public final TextView tvDetail;
    public final TextView tvDetailTag;
    public final TextView tvDiagnosis;
    public final TextView tvGenTag;
    public final TextView tvGenValue;
    public final TextView tvHRVTag;
    public final TextView tvHRVValue;

    private ItemEcgDiagnoseHeaderBinding(ConstraintLayout rootView, Barrier barrier, TextView tvAgeTag, TextView tvAgeValue, TextView tvBPTag, TextView tvBPValue, TextView tvCardTitle, TextView tvDetail, TextView tvDetailTag, TextView tvDiagnosis, TextView tvGenTag, TextView tvGenValue, TextView tvHRVTag, TextView tvHRVValue) {
        this.rootView = rootView;
        this.barrier = barrier;
        this.tvAgeTag = tvAgeTag;
        this.tvAgeValue = tvAgeValue;
        this.tvBPTag = tvBPTag;
        this.tvBPValue = tvBPValue;
        this.tvCardTitle = tvCardTitle;
        this.tvDetail = tvDetail;
        this.tvDetailTag = tvDetailTag;
        this.tvDiagnosis = tvDiagnosis;
        this.tvGenTag = tvGenTag;
        this.tvGenValue = tvGenValue;
        this.tvHRVTag = tvHRVTag;
        this.tvHRVValue = tvHRVValue;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ItemEcgDiagnoseHeaderBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemEcgDiagnoseHeaderBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.item_ecg_diagnose_header, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemEcgDiagnoseHeaderBinding bind(View rootView) {
        int i2 = R.id.barrier;
        Barrier barrier = (Barrier) ViewBindings.findChildViewById(rootView, i2);
        if (barrier != null) {
            i2 = R.id.tvAgeTag;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
            if (textView != null) {
                i2 = R.id.tvAgeValue;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                if (textView2 != null) {
                    i2 = R.id.tvBPTag;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                    if (textView3 != null) {
                        i2 = R.id.tvBPValue;
                        TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                        if (textView4 != null) {
                            i2 = R.id.tvCardTitle;
                            TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                            if (textView5 != null) {
                                i2 = R.id.tvDetail;
                                TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                if (textView6 != null) {
                                    i2 = R.id.tvDetailTag;
                                    TextView textView7 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                    if (textView7 != null) {
                                        i2 = R.id.tvDiagnosis;
                                        TextView textView8 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                        if (textView8 != null) {
                                            i2 = R.id.tvGenTag;
                                            TextView textView9 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                            if (textView9 != null) {
                                                i2 = R.id.tvGenValue;
                                                TextView textView10 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                if (textView10 != null) {
                                                    i2 = R.id.tvHRVTag;
                                                    TextView textView11 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                    if (textView11 != null) {
                                                        i2 = R.id.tvHRVValue;
                                                        TextView textView12 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                        if (textView12 != null) {
                                                            return new ItemEcgDiagnoseHeaderBinding((ConstraintLayout) rootView, barrier, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10, textView11, textView12);
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
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
