package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;

/* loaded from: classes4.dex */
public final class ActivityMeDndmodeBinding implements ViewBinding {
    public final LinearLayout llStartEnd;
    public final NavigationBar navigationbar;
    public final RelativeLayout rlEndTime;
    public final RelativeLayout rlStartTime;
    private final LinearLayout rootView;
    public final Switch switchDndMode;
    public final TextView tvEndTime;
    public final TextView tvStartTime;

    private ActivityMeDndmodeBinding(LinearLayout rootView, LinearLayout llStartEnd, NavigationBar navigationbar, RelativeLayout rlEndTime, RelativeLayout rlStartTime, Switch switchDndMode, TextView tvEndTime, TextView tvStartTime) {
        this.rootView = rootView;
        this.llStartEnd = llStartEnd;
        this.navigationbar = navigationbar;
        this.rlEndTime = rlEndTime;
        this.rlStartTime = rlStartTime;
        this.switchDndMode = switchDndMode;
        this.tvEndTime = tvEndTime;
        this.tvStartTime = tvStartTime;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityMeDndmodeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityMeDndmodeBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_me_dndmode, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityMeDndmodeBinding bind(View rootView) {
        int i2 = R.id.ll_start_end;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
        if (linearLayout != null) {
            i2 = R.id.navigationbar;
            NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
            if (navigationBar != null) {
                i2 = R.id.rl_end_time;
                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                if (relativeLayout != null) {
                    i2 = R.id.rl_start_time;
                    RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                    if (relativeLayout2 != null) {
                        i2 = R.id.switch_dnd_mode;
                        Switch r8 = (Switch) ViewBindings.findChildViewById(rootView, i2);
                        if (r8 != null) {
                            i2 = R.id.tv_end_time;
                            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                            if (textView != null) {
                                i2 = R.id.tv_start_time;
                                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                if (textView2 != null) {
                                    return new ActivityMeDndmodeBinding((LinearLayout) rootView, linearLayout, navigationBar, relativeLayout, relativeLayout2, r8, textView, textView2);
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
