package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;

/* loaded from: classes4.dex */
public final class ItemAlarmClockBinding implements ViewBinding {
    private final RelativeLayout rootView;
    public final Switch switchDndMode;
    public final TextView tvLabel;
    public final TextView tvRepetition;
    public final TextView tvTime;

    private ItemAlarmClockBinding(RelativeLayout rootView, Switch switchDndMode, TextView tvLabel, TextView tvRepetition, TextView tvTime) {
        this.rootView = rootView;
        this.switchDndMode = switchDndMode;
        this.tvLabel = tvLabel;
        this.tvRepetition = tvRepetition;
        this.tvTime = tvTime;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ItemAlarmClockBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemAlarmClockBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.item_alarm_clock, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemAlarmClockBinding bind(View rootView) {
        int i2 = R.id.switch_dnd_mode;
        Switch r4 = (Switch) ViewBindings.findChildViewById(rootView, i2);
        if (r4 != null) {
            i2 = R.id.tv_label;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
            if (textView != null) {
                i2 = R.id.tv_repetition;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                if (textView2 != null) {
                    i2 = R.id.tv_time;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                    if (textView3 != null) {
                        return new ItemAlarmClockBinding((RelativeLayout) rootView, r4, textView, textView2, textView3);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
