package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;

/* loaded from: classes4.dex */
public final class ItemPhyHisListBinding implements ViewBinding {
    public final Guideline gearLine;
    private final ConstraintLayout rootView;
    public final TextView tvDate;
    public final TextView tvItemGear1;
    public final TextView tvItemGear2;
    public final TextView tvItemGear3;
    public final TextView tvItemGear4;

    private ItemPhyHisListBinding(ConstraintLayout rootView, Guideline gearLine, TextView tvDate, TextView tvItemGear1, TextView tvItemGear2, TextView tvItemGear3, TextView tvItemGear4) {
        this.rootView = rootView;
        this.gearLine = gearLine;
        this.tvDate = tvDate;
        this.tvItemGear1 = tvItemGear1;
        this.tvItemGear2 = tvItemGear2;
        this.tvItemGear3 = tvItemGear3;
        this.tvItemGear4 = tvItemGear4;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ItemPhyHisListBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemPhyHisListBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.item_phy_his_list, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemPhyHisListBinding bind(View rootView) {
        int i2 = R.id.gear_line;
        Guideline guideline = (Guideline) ViewBindings.findChildViewById(rootView, i2);
        if (guideline != null) {
            i2 = R.id.tvDate;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
            if (textView != null) {
                i2 = R.id.tvItemGear1;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                if (textView2 != null) {
                    i2 = R.id.tvItemGear2;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                    if (textView3 != null) {
                        i2 = R.id.tvItemGear3;
                        TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                        if (textView4 != null) {
                            i2 = R.id.tvItemGear4;
                            TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                            if (textView5 != null) {
                                return new ItemPhyHisListBinding((ConstraintLayout) rootView, guideline, textView, textView2, textView3, textView4, textView5);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
