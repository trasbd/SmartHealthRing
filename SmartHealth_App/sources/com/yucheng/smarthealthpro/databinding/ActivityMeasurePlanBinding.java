package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;

/* loaded from: classes4.dex */
public final class ActivityMeasurePlanBinding implements ViewBinding {
    public final ImageView ivRightIconOne;
    public final ImageView ivRightIconOne2;
    public final LinearLayoutCompat llMeasurePlan;
    public final NavigationBar navigationbar;
    public final RelativeLayout rlBedTime;
    public final RelativeLayout rlWakeTime;
    private final LinearLayout rootView;
    public final Switch switchOpenReminder;
    public final TextView tvAdd;
    public final TextView tvBed;
    public final TextView tvBedTime;
    public final TextView tvCancel;
    public final TextView tvDelete;
    public final TextView tvOpenReminder;
    public final TextView tvSave;
    public final TextView tvWake;
    public final TextView tvWakeTime;
    public final TextView tvWakeTip;

    private ActivityMeasurePlanBinding(LinearLayout rootView, ImageView ivRightIconOne, ImageView ivRightIconOne2, LinearLayoutCompat llMeasurePlan, NavigationBar navigationbar, RelativeLayout rlBedTime, RelativeLayout rlWakeTime, Switch switchOpenReminder, TextView tvAdd, TextView tvBed, TextView tvBedTime, TextView tvCancel, TextView tvDelete, TextView tvOpenReminder, TextView tvSave, TextView tvWake, TextView tvWakeTime, TextView tvWakeTip) {
        this.rootView = rootView;
        this.ivRightIconOne = ivRightIconOne;
        this.ivRightIconOne2 = ivRightIconOne2;
        this.llMeasurePlan = llMeasurePlan;
        this.navigationbar = navigationbar;
        this.rlBedTime = rlBedTime;
        this.rlWakeTime = rlWakeTime;
        this.switchOpenReminder = switchOpenReminder;
        this.tvAdd = tvAdd;
        this.tvBed = tvBed;
        this.tvBedTime = tvBedTime;
        this.tvCancel = tvCancel;
        this.tvDelete = tvDelete;
        this.tvOpenReminder = tvOpenReminder;
        this.tvSave = tvSave;
        this.tvWake = tvWake;
        this.tvWakeTime = tvWakeTime;
        this.tvWakeTip = tvWakeTip;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityMeasurePlanBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityMeasurePlanBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_measure_plan, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityMeasurePlanBinding bind(View rootView) {
        int i2 = R.id.iv_right_icon_one;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i2);
        if (imageView != null) {
            i2 = R.id.iv_right_icon_one2;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
            if (imageView2 != null) {
                i2 = R.id.ll_measure_plan;
                LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) ViewBindings.findChildViewById(rootView, i2);
                if (linearLayoutCompat != null) {
                    i2 = R.id.navigationbar;
                    NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
                    if (navigationBar != null) {
                        i2 = R.id.rl_bed_time;
                        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                        if (relativeLayout != null) {
                            i2 = R.id.rl_wake_time;
                            RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                            if (relativeLayout2 != null) {
                                i2 = R.id.switch_open_reminder;
                                Switch r11 = (Switch) ViewBindings.findChildViewById(rootView, i2);
                                if (r11 != null) {
                                    i2 = R.id.tv_add;
                                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                    if (textView != null) {
                                        i2 = R.id.tv_bed;
                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                        if (textView2 != null) {
                                            i2 = R.id.tv_bed_time;
                                            TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                            if (textView3 != null) {
                                                i2 = R.id.tv_cancel;
                                                TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                if (textView4 != null) {
                                                    i2 = R.id.tv_delete;
                                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                    if (textView5 != null) {
                                                        i2 = R.id.tv_open_reminder;
                                                        TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                        if (textView6 != null) {
                                                            i2 = R.id.tv_save;
                                                            TextView textView7 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                            if (textView7 != null) {
                                                                i2 = R.id.tv_wake;
                                                                TextView textView8 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                if (textView8 != null) {
                                                                    i2 = R.id.tv_wake_time;
                                                                    TextView textView9 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                    if (textView9 != null) {
                                                                        i2 = R.id.tv_wake_tip;
                                                                        TextView textView10 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                        if (textView10 != null) {
                                                                            return new ActivityMeasurePlanBinding((LinearLayout) rootView, imageView, imageView2, linearLayoutCompat, navigationBar, relativeLayout, relativeLayout2, r11, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10);
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
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
