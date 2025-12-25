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

/* loaded from: classes4.dex */
public final class LayoutYearToDateSelectorBinding implements ViewBinding {
    public final WheelView day;
    public final WheelView hour;
    public final LinearLayout llBottom;
    public final LinearLayout llContainer;
    public final WheelView min;
    public final WheelView month;
    public final RelativeLayout rlTopConfirmCancel;
    private final LinearLayout rootView;
    public final WheelView second;
    public final LinearLayout timepicker;
    public final TextView tvCancel;
    public final TextView tvConfirm;
    public final WheelView year;

    private LayoutYearToDateSelectorBinding(LinearLayout rootView, WheelView day, WheelView hour, LinearLayout llBottom, LinearLayout llContainer, WheelView min, WheelView month, RelativeLayout rlTopConfirmCancel, WheelView second, LinearLayout timepicker, TextView tvCancel, TextView tvConfirm, WheelView year) {
        this.rootView = rootView;
        this.day = day;
        this.hour = hour;
        this.llBottom = llBottom;
        this.llContainer = llContainer;
        this.min = min;
        this.month = month;
        this.rlTopConfirmCancel = rlTopConfirmCancel;
        this.second = second;
        this.timepicker = timepicker;
        this.tvCancel = tvCancel;
        this.tvConfirm = tvConfirm;
        this.year = year;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static LayoutYearToDateSelectorBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static LayoutYearToDateSelectorBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.layout_year_to_date_selector, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutYearToDateSelectorBinding bind(View rootView) {
        int i2 = R.id.day;
        WheelView wheelView = (WheelView) ViewBindings.findChildViewById(rootView, i2);
        if (wheelView != null) {
            i2 = R.id.hour;
            WheelView wheelView2 = (WheelView) ViewBindings.findChildViewById(rootView, i2);
            if (wheelView2 != null) {
                i2 = R.id.ll_bottom;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                if (linearLayout != null) {
                    LinearLayout linearLayout2 = (LinearLayout) rootView;
                    i2 = R.id.min;
                    WheelView wheelView3 = (WheelView) ViewBindings.findChildViewById(rootView, i2);
                    if (wheelView3 != null) {
                        i2 = R.id.month;
                        WheelView wheelView4 = (WheelView) ViewBindings.findChildViewById(rootView, i2);
                        if (wheelView4 != null) {
                            i2 = R.id.rl_top_confirm_cancel;
                            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                            if (relativeLayout != null) {
                                i2 = R.id.second;
                                WheelView wheelView5 = (WheelView) ViewBindings.findChildViewById(rootView, i2);
                                if (wheelView5 != null) {
                                    i2 = R.id.timepicker;
                                    LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                                    if (linearLayout3 != null) {
                                        i2 = R.id.tv_cancel;
                                        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                        if (textView != null) {
                                            i2 = R.id.tv_confirm;
                                            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                            if (textView2 != null) {
                                                i2 = R.id.year;
                                                WheelView wheelView6 = (WheelView) ViewBindings.findChildViewById(rootView, i2);
                                                if (wheelView6 != null) {
                                                    return new LayoutYearToDateSelectorBinding(linearLayout2, wheelView, wheelView2, linearLayout, linearLayout2, wheelView3, wheelView4, relativeLayout, wheelView5, linearLayout3, textView, textView2, wheelView6);
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
