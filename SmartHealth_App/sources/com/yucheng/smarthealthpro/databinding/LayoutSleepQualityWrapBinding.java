package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.view.SleepQualityView;

/* loaded from: classes4.dex */
public final class LayoutSleepQualityWrapBinding implements ViewBinding {
    public final CardView moveSleepCard;
    private final ConstraintLayout rootView;
    public final SleepQualityView sleepQualityView;
    public final TextView tvSleepDetail;
    public final TextView tvSleepEnd;
    public final TextView tvSleepStart;

    private LayoutSleepQualityWrapBinding(ConstraintLayout rootView, CardView moveSleepCard, SleepQualityView sleepQualityView, TextView tvSleepDetail, TextView tvSleepEnd, TextView tvSleepStart) {
        this.rootView = rootView;
        this.moveSleepCard = moveSleepCard;
        this.sleepQualityView = sleepQualityView;
        this.tvSleepDetail = tvSleepDetail;
        this.tvSleepEnd = tvSleepEnd;
        this.tvSleepStart = tvSleepStart;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static LayoutSleepQualityWrapBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static LayoutSleepQualityWrapBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.layout_sleep_quality_wrap, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutSleepQualityWrapBinding bind(View rootView) {
        int i2 = R.id.move_sleep_card;
        CardView cardView = (CardView) ViewBindings.findChildViewById(rootView, i2);
        if (cardView != null) {
            i2 = R.id.sleep_quality_view;
            SleepQualityView sleepQualityView = (SleepQualityView) ViewBindings.findChildViewById(rootView, i2);
            if (sleepQualityView != null) {
                i2 = R.id.tv_sleep_detail;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                if (textView != null) {
                    i2 = R.id.tv_sleep_end;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                    if (textView2 != null) {
                        i2 = R.id.tv_sleep_start;
                        TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                        if (textView3 != null) {
                            return new LayoutSleepQualityWrapBinding((ConstraintLayout) rootView, cardView, sleepQualityView, textView, textView2, textView3);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
