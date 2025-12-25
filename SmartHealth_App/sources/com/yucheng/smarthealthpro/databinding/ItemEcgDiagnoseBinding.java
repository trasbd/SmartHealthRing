package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;

/* loaded from: classes4.dex */
public final class ItemEcgDiagnoseBinding implements ViewBinding {
    private final CardView rootView;
    public final TextView tvData;
    public final TextView tvName;
    public final TextView tvState;

    private ItemEcgDiagnoseBinding(CardView rootView, TextView tvData, TextView tvName, TextView tvState) {
        this.rootView = rootView;
        this.tvData = tvData;
        this.tvName = tvName;
        this.tvState = tvState;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CardView getRoot() {
        return this.rootView;
    }

    public static ItemEcgDiagnoseBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemEcgDiagnoseBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.item_ecg_diagnose, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemEcgDiagnoseBinding bind(View rootView) {
        int i2 = R.id.tvData;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
        if (textView != null) {
            i2 = R.id.tvName;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
            if (textView2 != null) {
                i2 = R.id.tvState;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                if (textView3 != null) {
                    return new ItemEcgDiagnoseBinding((CardView) rootView, textView, textView2, textView3);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
