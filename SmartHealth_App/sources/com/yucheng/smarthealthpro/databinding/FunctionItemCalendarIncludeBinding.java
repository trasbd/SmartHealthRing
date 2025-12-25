package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.haibin.calendarview.CalendarView;
import com.yucheng.smarthealthpro.R;

/* loaded from: classes4.dex */
public final class FunctionItemCalendarIncludeBinding implements ViewBinding {
    public final CalendarView calendarView;
    public final LinearLayout llMonth;
    private final LinearLayout rootView;
    public final TextView tvYears;

    private FunctionItemCalendarIncludeBinding(LinearLayout rootView, CalendarView calendarView, LinearLayout llMonth, TextView tvYears) {
        this.rootView = rootView;
        this.calendarView = calendarView;
        this.llMonth = llMonth;
        this.tvYears = tvYears;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static FunctionItemCalendarIncludeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FunctionItemCalendarIncludeBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.function_item_calendar_include, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FunctionItemCalendarIncludeBinding bind(View rootView) {
        int i2 = R.id.calendarView;
        CalendarView calendarView = (CalendarView) ViewBindings.findChildViewById(rootView, i2);
        if (calendarView != null) {
            LinearLayout linearLayout = (LinearLayout) rootView;
            int i3 = R.id.tv_years;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i3);
            if (textView != null) {
                return new FunctionItemCalendarIncludeBinding(linearLayout, calendarView, linearLayout, textView);
            }
            i2 = i3;
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
