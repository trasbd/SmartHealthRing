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
public final class ItemUniversalHisListBinding implements ViewBinding {
    public final ImageView ivMode;
    public final RelativeLayout rlValue;
    private final RelativeLayout rootView;
    public final TextView tvState;
    public final TextView tvTime;
    public final TextView tvUnit;
    public final TextView tvValue;
    public final View vDot;

    private ItemUniversalHisListBinding(RelativeLayout rootView, ImageView ivMode, RelativeLayout rlValue, TextView tvState, TextView tvTime, TextView tvUnit, TextView tvValue, View vDot) {
        this.rootView = rootView;
        this.ivMode = ivMode;
        this.rlValue = rlValue;
        this.tvState = tvState;
        this.tvTime = tvTime;
        this.tvUnit = tvUnit;
        this.tvValue = tvValue;
        this.vDot = vDot;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ItemUniversalHisListBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemUniversalHisListBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.item_universal_his_list, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemUniversalHisListBinding bind(View rootView) {
        View viewFindChildViewById;
        int i2 = R.id.iv_mode;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i2);
        if (imageView != null) {
            i2 = R.id.rl_value;
            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
            if (relativeLayout != null) {
                i2 = R.id.tv_state;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                if (textView != null) {
                    i2 = R.id.tv_time;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                    if (textView2 != null) {
                        i2 = R.id.tv_unit;
                        TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                        if (textView3 != null) {
                            i2 = R.id.tv_value;
                            TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                            if (textView4 != null && (viewFindChildViewById = ViewBindings.findChildViewById(rootView, (i2 = R.id.v_dot))) != null) {
                                return new ItemUniversalHisListBinding((RelativeLayout) rootView, imageView, relativeLayout, textView, textView2, textView3, textView4, viewFindChildViewById);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
