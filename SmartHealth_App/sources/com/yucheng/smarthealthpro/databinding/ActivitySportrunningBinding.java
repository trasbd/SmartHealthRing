package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import com.yucheng.smarthealthpro.sport.view.StopProgressButton;

/* loaded from: classes4.dex */
public final class ActivitySportrunningBinding implements ViewBinding {
    public final ImageView ivLock;
    public final ImageView ivMap;
    public final ImageView ivStartStop;
    public final ImageView ivStop;
    public final ImageView ivUnlock;
    public final LinearLayout llFirst;
    public final LinearLayout llFourthly;
    public final LinearLayout llLockBg;
    public final LinearLayout llRunning;
    public final LinearLayout llSecond;
    public final LinearLayout llStep;
    public final LinearLayout llThirdly;
    public final LinearLayout llThirdlyFourthly;
    public final NavigationBar navigationbar;
    public final StopProgressButton pbStop;
    public final StopProgressButton pbUnlock;
    private final RelativeLayout rootView;
    public final TextView tvClock;
    public final TextView tvFirstValue;
    public final TextView tvFourthlyValue;
    public final TextView tvMotorPattern;
    public final TextView tvSecondUnit;
    public final TextView tvSecondValue;
    public final TextView tvStepValue;
    public final TextView tvThirdlyValue;
    public final View vBottomSpace;

    private ActivitySportrunningBinding(RelativeLayout rootView, ImageView ivLock, ImageView ivMap, ImageView ivStartStop, ImageView ivStop, ImageView ivUnlock, LinearLayout llFirst, LinearLayout llFourthly, LinearLayout llLockBg, LinearLayout llRunning, LinearLayout llSecond, LinearLayout llStep, LinearLayout llThirdly, LinearLayout llThirdlyFourthly, NavigationBar navigationbar, StopProgressButton pbStop, StopProgressButton pbUnlock, TextView tvClock, TextView tvFirstValue, TextView tvFourthlyValue, TextView tvMotorPattern, TextView tvSecondUnit, TextView tvSecondValue, TextView tvStepValue, TextView tvThirdlyValue, View vBottomSpace) {
        this.rootView = rootView;
        this.ivLock = ivLock;
        this.ivMap = ivMap;
        this.ivStartStop = ivStartStop;
        this.ivStop = ivStop;
        this.ivUnlock = ivUnlock;
        this.llFirst = llFirst;
        this.llFourthly = llFourthly;
        this.llLockBg = llLockBg;
        this.llRunning = llRunning;
        this.llSecond = llSecond;
        this.llStep = llStep;
        this.llThirdly = llThirdly;
        this.llThirdlyFourthly = llThirdlyFourthly;
        this.navigationbar = navigationbar;
        this.pbStop = pbStop;
        this.pbUnlock = pbUnlock;
        this.tvClock = tvClock;
        this.tvFirstValue = tvFirstValue;
        this.tvFourthlyValue = tvFourthlyValue;
        this.tvMotorPattern = tvMotorPattern;
        this.tvSecondUnit = tvSecondUnit;
        this.tvSecondValue = tvSecondValue;
        this.tvStepValue = tvStepValue;
        this.tvThirdlyValue = tvThirdlyValue;
        this.vBottomSpace = vBottomSpace;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ActivitySportrunningBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivitySportrunningBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_sportrunning, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivitySportrunningBinding bind(View rootView) {
        View viewFindChildViewById;
        int i2 = R.id.iv_lock;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i2);
        if (imageView != null) {
            i2 = R.id.iv_map;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
            if (imageView2 != null) {
                i2 = R.id.iv_start_stop;
                ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                if (imageView3 != null) {
                    i2 = R.id.iv_stop;
                    ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                    if (imageView4 != null) {
                        i2 = R.id.iv_unlock;
                        ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                        if (imageView5 != null) {
                            i2 = R.id.llFirst;
                            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                            if (linearLayout != null) {
                                i2 = R.id.llFourthly;
                                LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                                if (linearLayout2 != null) {
                                    i2 = R.id.ll_lock_bg;
                                    LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                                    if (linearLayout3 != null) {
                                        i2 = R.id.ll_running;
                                        LinearLayout linearLayout4 = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                                        if (linearLayout4 != null) {
                                            i2 = R.id.llSecond;
                                            LinearLayout linearLayout5 = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                                            if (linearLayout5 != null) {
                                                i2 = R.id.llStep;
                                                LinearLayout linearLayout6 = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                                                if (linearLayout6 != null) {
                                                    i2 = R.id.llThirdly;
                                                    LinearLayout linearLayout7 = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                                                    if (linearLayout7 != null) {
                                                        i2 = R.id.ll_thirdly_fourthly;
                                                        LinearLayout linearLayout8 = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                                                        if (linearLayout8 != null) {
                                                            i2 = R.id.navigationbar;
                                                            NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
                                                            if (navigationBar != null) {
                                                                i2 = R.id.pb_stop;
                                                                StopProgressButton stopProgressButton = (StopProgressButton) ViewBindings.findChildViewById(rootView, i2);
                                                                if (stopProgressButton != null) {
                                                                    i2 = R.id.pb_unlock;
                                                                    StopProgressButton stopProgressButton2 = (StopProgressButton) ViewBindings.findChildViewById(rootView, i2);
                                                                    if (stopProgressButton2 != null) {
                                                                        i2 = R.id.tv_clock;
                                                                        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                        if (textView != null) {
                                                                            i2 = R.id.tv_first_value;
                                                                            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                            if (textView2 != null) {
                                                                                i2 = R.id.tv_fourthly_value;
                                                                                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                if (textView3 != null) {
                                                                                    i2 = R.id.tv_motor_pattern;
                                                                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                    if (textView4 != null) {
                                                                                        i2 = R.id.tv_second_unit;
                                                                                        TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                        if (textView5 != null) {
                                                                                            i2 = R.id.tv_second_value;
                                                                                            TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                            if (textView6 != null) {
                                                                                                i2 = R.id.tv_step_value;
                                                                                                TextView textView7 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                                if (textView7 != null) {
                                                                                                    i2 = R.id.tv_thirdly_value;
                                                                                                    TextView textView8 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                                    if (textView8 != null && (viewFindChildViewById = ViewBindings.findChildViewById(rootView, (i2 = R.id.v_bottom_space))) != null) {
                                                                                                        return new ActivitySportrunningBinding((RelativeLayout) rootView, imageView, imageView2, imageView3, imageView4, imageView5, linearLayout, linearLayout2, linearLayout3, linearLayout4, linearLayout5, linearLayout6, linearLayout7, linearLayout8, navigationBar, stopProgressButton, stopProgressButton2, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, viewFindChildViewById);
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
