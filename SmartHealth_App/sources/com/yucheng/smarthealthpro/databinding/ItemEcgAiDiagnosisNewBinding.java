package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;

/* loaded from: classes4.dex */
public final class ItemEcgAiDiagnosisNewBinding implements ViewBinding {
    private final RelativeLayout rootView;
    public final TextView tvAiDiagnosisTypeItem;
    public final TextView tvAiValueDiagnosisTypeItem;

    private ItemEcgAiDiagnosisNewBinding(RelativeLayout rootView, TextView tvAiDiagnosisTypeItem, TextView tvAiValueDiagnosisTypeItem) {
        this.rootView = rootView;
        this.tvAiDiagnosisTypeItem = tvAiDiagnosisTypeItem;
        this.tvAiValueDiagnosisTypeItem = tvAiValueDiagnosisTypeItem;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ItemEcgAiDiagnosisNewBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemEcgAiDiagnosisNewBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.item_ecg_ai_diagnosis_new, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemEcgAiDiagnosisNewBinding bind(View rootView) {
        int i2 = R.id.tv_ai_diagnosis_type_item;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
        if (textView != null) {
            i2 = R.id.tv_ai_value_diagnosis_type_item;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
            if (textView2 != null) {
                return new ItemEcgAiDiagnosisNewBinding((RelativeLayout) rootView, textView, textView2);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
