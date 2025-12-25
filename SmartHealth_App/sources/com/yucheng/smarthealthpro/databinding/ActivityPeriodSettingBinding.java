package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;

/* loaded from: classes4.dex */
public final class ActivityPeriodSettingBinding implements ViewBinding {
    public final NavigationBar navigationbar;
    private final LinearLayout rootView;
    public final TextView tvConfirm;
    public final TextView tvPeriodDayNum;
    public final TextView tvPeriodLong;
    public final TextView tvPeriodTime;

    private ActivityPeriodSettingBinding(LinearLayout rootView, NavigationBar navigationbar, TextView tvConfirm, TextView tvPeriodDayNum, TextView tvPeriodLong, TextView tvPeriodTime) {
        this.rootView = rootView;
        this.navigationbar = navigationbar;
        this.tvConfirm = tvConfirm;
        this.tvPeriodDayNum = tvPeriodDayNum;
        this.tvPeriodLong = tvPeriodLong;
        this.tvPeriodTime = tvPeriodTime;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityPeriodSettingBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityPeriodSettingBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_period_setting, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityPeriodSettingBinding bind(View rootView) {
        int i2 = R.id.navigationbar;
        NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
        if (navigationBar != null) {
            i2 = R.id.tv_confirm;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
            if (textView != null) {
                i2 = R.id.tv_period_day_num;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                if (textView2 != null) {
                    i2 = R.id.tv_period_long;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                    if (textView3 != null) {
                        i2 = R.id.tv_period_time;
                        TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                        if (textView4 != null) {
                            return new ActivityPeriodSettingBinding((LinearLayout) rootView, navigationBar, textView, textView2, textView3, textView4);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
