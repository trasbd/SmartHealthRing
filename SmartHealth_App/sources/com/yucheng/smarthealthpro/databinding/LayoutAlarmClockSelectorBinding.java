package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.contrarywind.view.WheelView;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;

/* loaded from: classes4.dex */
public final class LayoutAlarmClockSelectorBinding implements ViewBinding {
    public final LinearLayout llDeleteClock;
    public final LinearLayout llSelector;
    public final NavigationBar navigationbar;
    public final WheelView options1;
    public final WheelView options2;
    public final WheelView options3;
    public final LinearLayout optionspicker;
    public final RelativeLayout rlLabel;
    public final RelativeLayout rlRepetition;
    private final LinearLayout rootView;
    public final TextView tvLabel;
    public final TextView tvRepetition;
    public final TextView tvRepetitionTitle;

    private LayoutAlarmClockSelectorBinding(LinearLayout rootView, LinearLayout llDeleteClock, LinearLayout llSelector, NavigationBar navigationbar, WheelView options1, WheelView options2, WheelView options3, LinearLayout optionspicker, RelativeLayout rlLabel, RelativeLayout rlRepetition, TextView tvLabel, TextView tvRepetition, TextView tvRepetitionTitle) {
        this.rootView = rootView;
        this.llDeleteClock = llDeleteClock;
        this.llSelector = llSelector;
        this.navigationbar = navigationbar;
        this.options1 = options1;
        this.options2 = options2;
        this.options3 = options3;
        this.optionspicker = optionspicker;
        this.rlLabel = rlLabel;
        this.rlRepetition = rlRepetition;
        this.tvLabel = tvLabel;
        this.tvRepetition = tvRepetition;
        this.tvRepetitionTitle = tvRepetitionTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static LayoutAlarmClockSelectorBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static LayoutAlarmClockSelectorBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.layout_alarm_clock_selector, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutAlarmClockSelectorBinding bind(View rootView) {
        int i2 = R.id.ll_delete_clock;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
        if (linearLayout != null) {
            LinearLayout linearLayout2 = (LinearLayout) rootView;
            i2 = R.id.navigationbar;
            NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
            if (navigationBar != null) {
                i2 = R.id.options1;
                WheelView wheelView = (WheelView) ViewBindings.findChildViewById(rootView, i2);
                if (wheelView != null) {
                    i2 = R.id.options2;
                    WheelView wheelView2 = (WheelView) ViewBindings.findChildViewById(rootView, i2);
                    if (wheelView2 != null) {
                        i2 = R.id.options3;
                        WheelView wheelView3 = (WheelView) ViewBindings.findChildViewById(rootView, i2);
                        if (wheelView3 != null) {
                            i2 = R.id.optionspicker;
                            LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                            if (linearLayout3 != null) {
                                i2 = R.id.rl_label;
                                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                                if (relativeLayout != null) {
                                    i2 = R.id.rl_repetition;
                                    RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                                    if (relativeLayout2 != null) {
                                        i2 = R.id.tv_label;
                                        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                        if (textView != null) {
                                            i2 = R.id.tv_repetition;
                                            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                            if (textView2 != null) {
                                                i2 = R.id.tv_repetition_title;
                                                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                if (textView3 != null) {
                                                    return new LayoutAlarmClockSelectorBinding(linearLayout2, linearLayout, linearLayout2, navigationBar, wheelView, wheelView2, wheelView3, linearLayout3, relativeLayout, relativeLayout2, textView, textView2, textView3);
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
