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
public final class ItemSleepHisListBinding implements ViewBinding {
    public final LinearLayout llDiagnose;
    public final LinearLayout llEcg;
    private final RelativeLayout rootView;
    public final TextView tvData;
    public final TextView tvDeepSleepTime;
    public final TextView tvLightSleepTime;
    public final TextView tvRemTime;
    public final TextView tvSleepTotalTime;
    public final TextView tvState;

    private ItemSleepHisListBinding(RelativeLayout rootView, LinearLayout llDiagnose, LinearLayout llEcg, TextView tvData, TextView tvDeepSleepTime, TextView tvLightSleepTime, TextView tvRemTime, TextView tvSleepTotalTime, TextView tvState) {
        this.rootView = rootView;
        this.llDiagnose = llDiagnose;
        this.llEcg = llEcg;
        this.tvData = tvData;
        this.tvDeepSleepTime = tvDeepSleepTime;
        this.tvLightSleepTime = tvLightSleepTime;
        this.tvRemTime = tvRemTime;
        this.tvSleepTotalTime = tvSleepTotalTime;
        this.tvState = tvState;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ItemSleepHisListBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemSleepHisListBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.item_sleep_his_list, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemSleepHisListBinding bind(View rootView) {
        int i2 = R.id.ll_diagnose;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
        if (linearLayout != null) {
            i2 = R.id.ll_ecg;
            LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
            if (linearLayout2 != null) {
                i2 = R.id.tv_data;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                if (textView != null) {
                    i2 = R.id.tv_deep_sleep_time;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                    if (textView2 != null) {
                        i2 = R.id.tv_light_sleep_time;
                        TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                        if (textView3 != null) {
                            i2 = R.id.tv_rem_time;
                            TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                            if (textView4 != null) {
                                i2 = R.id.tv_sleep_total_time;
                                TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                if (textView5 != null) {
                                    i2 = R.id.tv_state;
                                    TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                    if (textView6 != null) {
                                        return new ItemSleepHisListBinding((RelativeLayout) rootView, linearLayout, linearLayout2, textView, textView2, textView3, textView4, textView5, textView6);
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
