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
import com.yucheng.smarthealthpro.framework.view.NavigationBar;

/* loaded from: classes4.dex */
public final class ActivityRunninghealthgoalsBinding implements ViewBinding {
    public final NavigationBar navigationbar;
    public final RelativeLayout rlMovingObject;
    public final RelativeLayout rlSleepQuality;
    private final LinearLayout rootView;
    public final TextView tvMovingObject;
    public final TextView tvSleepQuality;

    private ActivityRunninghealthgoalsBinding(LinearLayout rootView, NavigationBar navigationbar, RelativeLayout rlMovingObject, RelativeLayout rlSleepQuality, TextView tvMovingObject, TextView tvSleepQuality) {
        this.rootView = rootView;
        this.navigationbar = navigationbar;
        this.rlMovingObject = rlMovingObject;
        this.rlSleepQuality = rlSleepQuality;
        this.tvMovingObject = tvMovingObject;
        this.tvSleepQuality = tvSleepQuality;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityRunninghealthgoalsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityRunninghealthgoalsBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_runninghealthgoals, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityRunninghealthgoalsBinding bind(View rootView) {
        int i2 = R.id.navigationbar;
        NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
        if (navigationBar != null) {
            i2 = R.id.rl_moving_object;
            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
            if (relativeLayout != null) {
                i2 = R.id.rl_sleep_quality;
                RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                if (relativeLayout2 != null) {
                    i2 = R.id.tv_moving_object;
                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                    if (textView != null) {
                        i2 = R.id.tv_sleep_quality;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                        if (textView2 != null) {
                            return new ActivityRunninghealthgoalsBinding((LinearLayout) rootView, navigationBar, relativeLayout, relativeLayout2, textView, textView2);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
