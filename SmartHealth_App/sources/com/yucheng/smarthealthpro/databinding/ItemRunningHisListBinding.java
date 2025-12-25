package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;

/* loaded from: classes4.dex */
public final class ItemRunningHisListBinding implements ViewBinding {
    public final LinearLayout llDiagnose;
    public final LinearLayout llEcg;
    private final RelativeLayout rootView;
    public final TextView tvData;
    public final TextView tvDistance;
    public final TextView tvHeat;
    public final TextView tvStepNumber;
    public final TextView tvUnit;

    private ItemRunningHisListBinding(RelativeLayout rootView, LinearLayout llDiagnose, LinearLayout llEcg, TextView tvData, TextView tvDistance, TextView tvHeat, TextView tvStepNumber, TextView tvUnit) {
        this.rootView = rootView;
        this.llDiagnose = llDiagnose;
        this.llEcg = llEcg;
        this.tvData = tvData;
        this.tvDistance = tvDistance;
        this.tvHeat = tvHeat;
        this.tvStepNumber = tvStepNumber;
        this.tvUnit = tvUnit;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ItemRunningHisListBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemRunningHisListBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.item_running_his_list, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemRunningHisListBinding bind(View rootView) {
        int i2 = R.id.ll_diagnose;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
        if (linearLayout != null) {
            i2 = R.id.ll_ecg;
            LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
            if (linearLayout2 != null) {
                i2 = R.id.tv_data;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                if (textView != null) {
                    i2 = R.id.tv_distance;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                    if (textView2 != null) {
                        i2 = R.id.tv_heat;
                        TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                        if (textView3 != null) {
                            i2 = R.id.tv_step_number;
                            TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                            if (textView4 != null) {
                                i2 = R.id.tv_unit;
                                TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                if (textView5 != null) {
                                    return new ItemRunningHisListBinding((RelativeLayout) rootView, linearLayout, linearLayout2, textView, textView2, textView3, textView4, textView5);
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
