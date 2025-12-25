package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.tabs.TabLayout;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import com.yucheng.smarthealthpro.home.view.NoScrollViewPager;
import com.yucheng.smarthealthpro.sport.view.NoScrollSwipeRecyclerView;

/* loaded from: classes4.dex */
public final class FragmentSportBinding implements ViewBinding {
    public final ImageView ivNoHis;
    public final ImageView ivStartSport;
    public final ImageView ivState;
    public final ImageView ivWeather;
    public final LinearLayout llAddress;
    public final LinearLayout llContain;
    public final NavigationBar navigationbar;
    public final NestedScrollView nslView;
    public final NoScrollSwipeRecyclerView recycleView;
    public final RelativeLayout rlSport;
    private final LinearLayout rootView;
    public final TabLayout tabLayout;
    public final TextView tvAddress;
    public final TextView tvTemp;
    public final TextView tvTempBetween;
    public final NoScrollViewPager vpCommon;

    private FragmentSportBinding(LinearLayout rootView, ImageView ivNoHis, ImageView ivStartSport, ImageView ivState, ImageView ivWeather, LinearLayout llAddress, LinearLayout llContain, NavigationBar navigationbar, NestedScrollView nslView, NoScrollSwipeRecyclerView recycleView, RelativeLayout rlSport, TabLayout tabLayout, TextView tvAddress, TextView tvTemp, TextView tvTempBetween, NoScrollViewPager vpCommon) {
        this.rootView = rootView;
        this.ivNoHis = ivNoHis;
        this.ivStartSport = ivStartSport;
        this.ivState = ivState;
        this.ivWeather = ivWeather;
        this.llAddress = llAddress;
        this.llContain = llContain;
        this.navigationbar = navigationbar;
        this.nslView = nslView;
        this.recycleView = recycleView;
        this.rlSport = rlSport;
        this.tabLayout = tabLayout;
        this.tvAddress = tvAddress;
        this.tvTemp = tvTemp;
        this.tvTempBetween = tvTempBetween;
        this.vpCommon = vpCommon;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static FragmentSportBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentSportBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_sport, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentSportBinding bind(View rootView) {
        int i2 = R.id.iv_no_his;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i2);
        if (imageView != null) {
            i2 = R.id.iv_start_sport;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
            if (imageView2 != null) {
                i2 = R.id.iv_state;
                ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                if (imageView3 != null) {
                    i2 = R.id.iv_weather;
                    ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                    if (imageView4 != null) {
                        i2 = R.id.ll_address;
                        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                        if (linearLayout != null) {
                            i2 = R.id.ll_contain;
                            LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                            if (linearLayout2 != null) {
                                i2 = R.id.navigationbar;
                                NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
                                if (navigationBar != null) {
                                    i2 = R.id.nsl_view;
                                    NestedScrollView nestedScrollView = (NestedScrollView) ViewBindings.findChildViewById(rootView, i2);
                                    if (nestedScrollView != null) {
                                        i2 = R.id.recycle_view;
                                        NoScrollSwipeRecyclerView noScrollSwipeRecyclerView = (NoScrollSwipeRecyclerView) ViewBindings.findChildViewById(rootView, i2);
                                        if (noScrollSwipeRecyclerView != null) {
                                            i2 = R.id.rl_sport;
                                            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                                            if (relativeLayout != null) {
                                                i2 = R.id.tabLayout;
                                                TabLayout tabLayout = (TabLayout) ViewBindings.findChildViewById(rootView, i2);
                                                if (tabLayout != null) {
                                                    i2 = R.id.tv_address;
                                                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                    if (textView != null) {
                                                        i2 = R.id.tv_temp;
                                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                        if (textView2 != null) {
                                                            i2 = R.id.tv_temp_between;
                                                            TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                            if (textView3 != null) {
                                                                i2 = R.id.vp_common;
                                                                NoScrollViewPager noScrollViewPager = (NoScrollViewPager) ViewBindings.findChildViewById(rootView, i2);
                                                                if (noScrollViewPager != null) {
                                                                    return new FragmentSportBinding((LinearLayout) rootView, imageView, imageView2, imageView3, imageView4, linearLayout, linearLayout2, navigationBar, nestedScrollView, noScrollSwipeRecyclerView, relativeLayout, tabLayout, textView, textView2, textView3, noScrollViewPager);
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
