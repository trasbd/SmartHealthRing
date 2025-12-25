package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;

/* loaded from: classes4.dex */
public final class FragmentOneBakBinding implements ViewBinding {
    public final LinearLayout bloodOxygenView;
    public final LinearLayout bloodSugarView;
    public final LinearLayout bloodview;
    public final RelativeLayout conview;
    public final LinearLayout cvrrView;
    public final LinearLayout ecgLayout;
    public final LinearLayout heartview;
    public final LinearLayout hrvView;
    public final LinearLayout llExitLogin;
    public final ImageView mainfragmentBtState;
    public final TextView mbstep;
    public final NavigationBar navigationbar;
    public final TextView nowsetplal;
    public final LinearLayout respiratoryRateView;
    private final FrameLayout rootView;
    public final LinearLayout sleepview;
    public final SmartRefreshLayout smartRefreshLayout;
    public final LinearLayout tempView;
    public final TextView tvSportKcal;
    public final TextView tvSportMileage;
    public final TextView tvSportStep;

    private FragmentOneBakBinding(FrameLayout rootView, LinearLayout bloodOxygenView, LinearLayout bloodSugarView, LinearLayout bloodview, RelativeLayout conview, LinearLayout cvrrView, LinearLayout ecgLayout, LinearLayout heartview, LinearLayout hrvView, LinearLayout llExitLogin, ImageView mainfragmentBtState, TextView mbstep, NavigationBar navigationbar, TextView nowsetplal, LinearLayout respiratoryRateView, LinearLayout sleepview, SmartRefreshLayout smartRefreshLayout, LinearLayout tempView, TextView tvSportKcal, TextView tvSportMileage, TextView tvSportStep) {
        this.rootView = rootView;
        this.bloodOxygenView = bloodOxygenView;
        this.bloodSugarView = bloodSugarView;
        this.bloodview = bloodview;
        this.conview = conview;
        this.cvrrView = cvrrView;
        this.ecgLayout = ecgLayout;
        this.heartview = heartview;
        this.hrvView = hrvView;
        this.llExitLogin = llExitLogin;
        this.mainfragmentBtState = mainfragmentBtState;
        this.mbstep = mbstep;
        this.navigationbar = navigationbar;
        this.nowsetplal = nowsetplal;
        this.respiratoryRateView = respiratoryRateView;
        this.sleepview = sleepview;
        this.smartRefreshLayout = smartRefreshLayout;
        this.tempView = tempView;
        this.tvSportKcal = tvSportKcal;
        this.tvSportMileage = tvSportMileage;
        this.tvSportStep = tvSportStep;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static FragmentOneBakBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentOneBakBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_one_bak, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentOneBakBinding bind(View rootView) {
        int i2 = R.id.blood_oxygen_view;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
        if (linearLayout != null) {
            i2 = R.id.blood_sugar_view;
            LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
            if (linearLayout2 != null) {
                i2 = R.id.bloodview;
                LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                if (linearLayout3 != null) {
                    i2 = R.id.conview;
                    RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                    if (relativeLayout != null) {
                        i2 = R.id.cvrr_view;
                        LinearLayout linearLayout4 = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                        if (linearLayout4 != null) {
                            i2 = R.id.ecg_layout;
                            LinearLayout linearLayout5 = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                            if (linearLayout5 != null) {
                                i2 = R.id.heartview;
                                LinearLayout linearLayout6 = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                                if (linearLayout6 != null) {
                                    i2 = R.id.hrv_view;
                                    LinearLayout linearLayout7 = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                                    if (linearLayout7 != null) {
                                        i2 = R.id.ll_exit_login;
                                        LinearLayout linearLayout8 = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                                        if (linearLayout8 != null) {
                                            i2 = R.id.mainfragment_bt_state;
                                            ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                                            if (imageView != null) {
                                                i2 = R.id.mbstep;
                                                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                if (textView != null) {
                                                    i2 = R.id.navigationbar;
                                                    NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
                                                    if (navigationBar != null) {
                                                        i2 = R.id.nowsetplal;
                                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                        if (textView2 != null) {
                                                            i2 = R.id.respiratory_rate_view;
                                                            LinearLayout linearLayout9 = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                                                            if (linearLayout9 != null) {
                                                                i2 = R.id.sleepview;
                                                                LinearLayout linearLayout10 = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                                                                if (linearLayout10 != null) {
                                                                    i2 = R.id.smartRefreshLayout;
                                                                    SmartRefreshLayout smartRefreshLayout = (SmartRefreshLayout) ViewBindings.findChildViewById(rootView, i2);
                                                                    if (smartRefreshLayout != null) {
                                                                        i2 = R.id.temp_view;
                                                                        LinearLayout linearLayout11 = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                                                                        if (linearLayout11 != null) {
                                                                            i2 = R.id.tv_sport_kcal;
                                                                            TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                            if (textView3 != null) {
                                                                                i2 = R.id.tv_sport_mileage;
                                                                                TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                if (textView4 != null) {
                                                                                    i2 = R.id.tv_sport_step;
                                                                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                    if (textView5 != null) {
                                                                                        return new FragmentOneBakBinding((FrameLayout) rootView, linearLayout, linearLayout2, linearLayout3, relativeLayout, linearLayout4, linearLayout5, linearLayout6, linearLayout7, linearLayout8, imageView, textView, navigationBar, textView2, linearLayout9, linearLayout10, smartRefreshLayout, linearLayout11, textView3, textView4, textView5);
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
