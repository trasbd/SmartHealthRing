package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;

/* loaded from: classes4.dex */
public final class MeasurePlanItemBinding implements ViewBinding {
    public final CheckBox cbPlan;
    public final ImageView ivRightIconOne2;
    private final RelativeLayout rootView;
    public final TextView tvPlan;
    public final TextView tvPlanTime;

    private MeasurePlanItemBinding(RelativeLayout rootView, CheckBox cbPlan, ImageView ivRightIconOne2, TextView tvPlan, TextView tvPlanTime) {
        this.rootView = rootView;
        this.cbPlan = cbPlan;
        this.ivRightIconOne2 = ivRightIconOne2;
        this.tvPlan = tvPlan;
        this.tvPlanTime = tvPlanTime;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static MeasurePlanItemBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static MeasurePlanItemBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.measure_plan_item, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static MeasurePlanItemBinding bind(View rootView) {
        int i2 = R.id.cb_plan;
        CheckBox checkBox = (CheckBox) ViewBindings.findChildViewById(rootView, i2);
        if (checkBox != null) {
            i2 = R.id.iv_right_icon_one2;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i2);
            if (imageView != null) {
                i2 = R.id.tv_plan;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                if (textView != null) {
                    i2 = R.id.tv_plan_time;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                    if (textView2 != null) {
                        return new MeasurePlanItemBinding((RelativeLayout) rootView, checkBox, imageView, textView, textView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
