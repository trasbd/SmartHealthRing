package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.MyTextView;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import com.yucheng.smarthealthpro.home.view.StepView;

/* loaded from: classes4.dex */
public final class FragmentHomeBinding implements ViewBinding {
    public final MyTextView homePairPermissionTitle;
    public final TextView homePdNumber;
    public final TextView homePdNumberAAAAAAA;
    public final TextView homePdNumberMax;
    public final TextView homePdNumberMin;
    public final TextView homePdNumberUnit;
    public final MyTextView homePermissionTitle;
    public final ImageView ivHealth;
    public final LinearLayout llCompile;
    public final LinearLayout llEdit;
    public final LinearLayout llHealthy;
    public final LinearLayout llNoFunction;
    public final LinearLayoutCompat llPermission;
    public final LinearLayout llRanking;
    public final LinearLayout llRankingHealthy;
    public final ConstraintLayout lyAnswer;
    public final NavigationBar navigationbar;
    public final RecyclerView recycleHome;
    public final RelativeLayout rlHomePdNumber;
    public final RelativeLayout rlRunning;
    private final FrameLayout rootView;
    public final SpinkitDialogIncludeBinding rvDialog;
    public final SmartRefreshLayout srlHome;
    public final StepView stepView;
    public final TextView tvAnswer;
    public final TextView tvAnswer2;
    public final TextView tvKcal;
    public final TextView tvOdo;
    public final TextView tvOdoUnit;
    public final TextView tvStep;
    public final TextView tvSteps;

    private FragmentHomeBinding(FrameLayout rootView, MyTextView homePairPermissionTitle, TextView homePdNumber, TextView homePdNumberAAAAAAA, TextView homePdNumberMax, TextView homePdNumberMin, TextView homePdNumberUnit, MyTextView homePermissionTitle, ImageView ivHealth, LinearLayout llCompile, LinearLayout llEdit, LinearLayout llHealthy, LinearLayout llNoFunction, LinearLayoutCompat llPermission, LinearLayout llRanking, LinearLayout llRankingHealthy, ConstraintLayout lyAnswer, NavigationBar navigationbar, RecyclerView recycleHome, RelativeLayout rlHomePdNumber, RelativeLayout rlRunning, SpinkitDialogIncludeBinding rvDialog, SmartRefreshLayout srlHome, StepView stepView, TextView tvAnswer, TextView tvAnswer2, TextView tvKcal, TextView tvOdo, TextView tvOdoUnit, TextView tvStep, TextView tvSteps) {
        this.rootView = rootView;
        this.homePairPermissionTitle = homePairPermissionTitle;
        this.homePdNumber = homePdNumber;
        this.homePdNumberAAAAAAA = homePdNumberAAAAAAA;
        this.homePdNumberMax = homePdNumberMax;
        this.homePdNumberMin = homePdNumberMin;
        this.homePdNumberUnit = homePdNumberUnit;
        this.homePermissionTitle = homePermissionTitle;
        this.ivHealth = ivHealth;
        this.llCompile = llCompile;
        this.llEdit = llEdit;
        this.llHealthy = llHealthy;
        this.llNoFunction = llNoFunction;
        this.llPermission = llPermission;
        this.llRanking = llRanking;
        this.llRankingHealthy = llRankingHealthy;
        this.lyAnswer = lyAnswer;
        this.navigationbar = navigationbar;
        this.recycleHome = recycleHome;
        this.rlHomePdNumber = rlHomePdNumber;
        this.rlRunning = rlRunning;
        this.rvDialog = rvDialog;
        this.srlHome = srlHome;
        this.stepView = stepView;
        this.tvAnswer = tvAnswer;
        this.tvAnswer2 = tvAnswer2;
        this.tvKcal = tvKcal;
        this.tvOdo = tvOdo;
        this.tvOdoUnit = tvOdoUnit;
        this.tvStep = tvStep;
        this.tvSteps = tvSteps;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static FragmentHomeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentHomeBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_home, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentHomeBinding bind(View rootView) {
        View viewFindChildViewById;
        int i2 = R.id.home_pair_permission_title;
        MyTextView myTextView = (MyTextView) ViewBindings.findChildViewById(rootView, i2);
        if (myTextView != null) {
            i2 = R.id.home_pd_number;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
            if (textView != null) {
                i2 = R.id.home_pd_number_AAAAAAA;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                if (textView2 != null) {
                    i2 = R.id.home_pd_number_max;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                    if (textView3 != null) {
                        i2 = R.id.home_pd_number_min;
                        TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                        if (textView4 != null) {
                            i2 = R.id.home_pd_number_unit;
                            TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                            if (textView5 != null) {
                                i2 = R.id.home_permission_title;
                                MyTextView myTextView2 = (MyTextView) ViewBindings.findChildViewById(rootView, i2);
                                if (myTextView2 != null) {
                                    i2 = R.id.iv_health;
                                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                                    if (imageView != null) {
                                        i2 = R.id.ll_compile;
                                        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                                        if (linearLayout != null) {
                                            i2 = R.id.llEdit;
                                            LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                                            if (linearLayout2 != null) {
                                                i2 = R.id.ll_healthy;
                                                LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                                                if (linearLayout3 != null) {
                                                    i2 = R.id.ll_no_function;
                                                    LinearLayout linearLayout4 = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                                                    if (linearLayout4 != null) {
                                                        i2 = R.id.ll_permission;
                                                        LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) ViewBindings.findChildViewById(rootView, i2);
                                                        if (linearLayoutCompat != null) {
                                                            i2 = R.id.ll_ranking;
                                                            LinearLayout linearLayout5 = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                                                            if (linearLayout5 != null) {
                                                                i2 = R.id.ll_ranking_healthy;
                                                                LinearLayout linearLayout6 = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                                                                if (linearLayout6 != null) {
                                                                    i2 = R.id.lyAnswer;
                                                                    ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i2);
                                                                    if (constraintLayout != null) {
                                                                        i2 = R.id.navigationbar;
                                                                        NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
                                                                        if (navigationBar != null) {
                                                                            i2 = R.id.recycle_home;
                                                                            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, i2);
                                                                            if (recyclerView != null) {
                                                                                i2 = R.id.rl_home_pd_number;
                                                                                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                                                                                if (relativeLayout != null) {
                                                                                    i2 = R.id.rl_running;
                                                                                    RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                                                                                    if (relativeLayout2 != null && (viewFindChildViewById = ViewBindings.findChildViewById(rootView, (i2 = R.id.rv_dialog))) != null) {
                                                                                        SpinkitDialogIncludeBinding spinkitDialogIncludeBindingBind = SpinkitDialogIncludeBinding.bind(viewFindChildViewById);
                                                                                        i2 = R.id.srl_home;
                                                                                        SmartRefreshLayout smartRefreshLayout = (SmartRefreshLayout) ViewBindings.findChildViewById(rootView, i2);
                                                                                        if (smartRefreshLayout != null) {
                                                                                            i2 = R.id.step_view;
                                                                                            StepView stepView = (StepView) ViewBindings.findChildViewById(rootView, i2);
                                                                                            if (stepView != null) {
                                                                                                i2 = R.id.tvAnswer;
                                                                                                TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                                if (textView6 != null) {
                                                                                                    i2 = R.id.tvAnswer2;
                                                                                                    TextView textView7 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                                    if (textView7 != null) {
                                                                                                        i2 = R.id.tv_kcal;
                                                                                                        TextView textView8 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                                        if (textView8 != null) {
                                                                                                            i2 = R.id.tv_odo;
                                                                                                            TextView textView9 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                                            if (textView9 != null) {
                                                                                                                i2 = R.id.tv_odo_unit;
                                                                                                                TextView textView10 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                if (textView10 != null) {
                                                                                                                    i2 = R.id.tv_step;
                                                                                                                    TextView textView11 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                    if (textView11 != null) {
                                                                                                                        i2 = R.id.tv_steps;
                                                                                                                        TextView textView12 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                        if (textView12 != null) {
                                                                                                                            return new FragmentHomeBinding((FrameLayout) rootView, myTextView, textView, textView2, textView3, textView4, textView5, myTextView2, imageView, linearLayout, linearLayout2, linearLayout3, linearLayout4, linearLayoutCompat, linearLayout5, linearLayout6, constraintLayout, navigationBar, recyclerView, relativeLayout, relativeLayout2, spinkitDialogIncludeBindingBind, smartRefreshLayout, stepView, textView6, textView7, textView8, textView9, textView10, textView11, textView12);
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
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
