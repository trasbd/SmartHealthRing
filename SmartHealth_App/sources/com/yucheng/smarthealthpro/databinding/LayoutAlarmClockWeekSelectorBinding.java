package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.contrarywind.view.WheelView;
import com.yucheng.smarthealthpro.R;

/* loaded from: classes4.dex */
public final class LayoutAlarmClockWeekSelectorBinding implements ViewBinding {
    public final CheckBox cbFriday;
    public final CheckBox cbMonday;
    public final CheckBox cbSaturday;
    public final CheckBox cbThursday;
    public final CheckBox cbTuesday;
    public final CheckBox cbWednesday;
    public final CheckBox cbWeekday;
    public final LinearLayout llBottom;
    public final LinearLayout llSelector;
    public final WheelView options1;
    public final WheelView options2;
    public final WheelView options3;
    public final LinearLayout optionspicker;
    public final RelativeLayout rlTopConfirmCancel;
    private final LinearLayout rootView;
    public final TextView tvCancel;
    public final TextView tvConfirm;

    private LayoutAlarmClockWeekSelectorBinding(LinearLayout rootView, CheckBox cbFriday, CheckBox cbMonday, CheckBox cbSaturday, CheckBox cbThursday, CheckBox cbTuesday, CheckBox cbWednesday, CheckBox cbWeekday, LinearLayout llBottom, LinearLayout llSelector, WheelView options1, WheelView options2, WheelView options3, LinearLayout optionspicker, RelativeLayout rlTopConfirmCancel, TextView tvCancel, TextView tvConfirm) {
        this.rootView = rootView;
        this.cbFriday = cbFriday;
        this.cbMonday = cbMonday;
        this.cbSaturday = cbSaturday;
        this.cbThursday = cbThursday;
        this.cbTuesday = cbTuesday;
        this.cbWednesday = cbWednesday;
        this.cbWeekday = cbWeekday;
        this.llBottom = llBottom;
        this.llSelector = llSelector;
        this.options1 = options1;
        this.options2 = options2;
        this.options3 = options3;
        this.optionspicker = optionspicker;
        this.rlTopConfirmCancel = rlTopConfirmCancel;
        this.tvCancel = tvCancel;
        this.tvConfirm = tvConfirm;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static LayoutAlarmClockWeekSelectorBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static LayoutAlarmClockWeekSelectorBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.layout_alarm_clock_week_selector, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutAlarmClockWeekSelectorBinding bind(View rootView) {
        int i2 = R.id.cb_friday;
        CheckBox checkBox = (CheckBox) ViewBindings.findChildViewById(rootView, i2);
        if (checkBox != null) {
            i2 = R.id.cb_monday;
            CheckBox checkBox2 = (CheckBox) ViewBindings.findChildViewById(rootView, i2);
            if (checkBox2 != null) {
                i2 = R.id.cb_saturday;
                CheckBox checkBox3 = (CheckBox) ViewBindings.findChildViewById(rootView, i2);
                if (checkBox3 != null) {
                    i2 = R.id.cb_thursday;
                    CheckBox checkBox4 = (CheckBox) ViewBindings.findChildViewById(rootView, i2);
                    if (checkBox4 != null) {
                        i2 = R.id.cb_tuesday;
                        CheckBox checkBox5 = (CheckBox) ViewBindings.findChildViewById(rootView, i2);
                        if (checkBox5 != null) {
                            i2 = R.id.cb_wednesday;
                            CheckBox checkBox6 = (CheckBox) ViewBindings.findChildViewById(rootView, i2);
                            if (checkBox6 != null) {
                                i2 = R.id.cb_weekday;
                                CheckBox checkBox7 = (CheckBox) ViewBindings.findChildViewById(rootView, i2);
                                if (checkBox7 != null) {
                                    i2 = R.id.ll_bottom;
                                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                                    if (linearLayout != null) {
                                        LinearLayout linearLayout2 = (LinearLayout) rootView;
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
                                                        i2 = R.id.rl_top_confirm_cancel;
                                                        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                                                        if (relativeLayout != null) {
                                                            i2 = R.id.tv_cancel;
                                                            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                            if (textView != null) {
                                                                i2 = R.id.tv_confirm;
                                                                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                if (textView2 != null) {
                                                                    return new LayoutAlarmClockWeekSelectorBinding(linearLayout2, checkBox, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6, checkBox7, linearLayout, linearLayout2, wheelView, wheelView2, wheelView3, linearLayout3, relativeLayout, textView, textView2);
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
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
