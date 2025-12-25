package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.flyco.tablayout.SlidingTabLayout;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import com.yucheng.smarthealthpro.home.view.NoScrollViewPager;

/* loaded from: classes4.dex */
public final class ActivityPdnumberBinding implements ViewBinding {
    public final ImageView ivFourthlyLeft;
    public final NavigationBar navigationbar;
    public final NestedScrollView nsl;
    public final RecyclerView recycleView;
    public final RelativeLayout rlFourthly;
    private final LinearLayout rootView;
    public final SmartRefreshLayout srlEcg;
    public final SlidingTabLayout stlTab;
    public final TextView tvFourthly;
    public final NoScrollViewPager vpTab;

    private ActivityPdnumberBinding(LinearLayout rootView, ImageView ivFourthlyLeft, NavigationBar navigationbar, NestedScrollView nsl, RecyclerView recycleView, RelativeLayout rlFourthly, SmartRefreshLayout srlEcg, SlidingTabLayout stlTab, TextView tvFourthly, NoScrollViewPager vpTab) {
        this.rootView = rootView;
        this.ivFourthlyLeft = ivFourthlyLeft;
        this.navigationbar = navigationbar;
        this.nsl = nsl;
        this.recycleView = recycleView;
        this.rlFourthly = rlFourthly;
        this.srlEcg = srlEcg;
        this.stlTab = stlTab;
        this.tvFourthly = tvFourthly;
        this.vpTab = vpTab;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityPdnumberBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityPdnumberBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_pdnumber, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityPdnumberBinding bind(View rootView) {
        int i2 = R.id.iv_fourthly_left;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i2);
        if (imageView != null) {
            i2 = R.id.navigationbar;
            NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
            if (navigationBar != null) {
                i2 = R.id.nsl;
                NestedScrollView nestedScrollView = (NestedScrollView) ViewBindings.findChildViewById(rootView, i2);
                if (nestedScrollView != null) {
                    i2 = R.id.recycle_view;
                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, i2);
                    if (recyclerView != null) {
                        i2 = R.id.rl_fourthly;
                        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                        if (relativeLayout != null) {
                            i2 = R.id.srl_ecg;
                            SmartRefreshLayout smartRefreshLayout = (SmartRefreshLayout) ViewBindings.findChildViewById(rootView, i2);
                            if (smartRefreshLayout != null) {
                                i2 = R.id.stl_tab;
                                SlidingTabLayout slidingTabLayout = (SlidingTabLayout) ViewBindings.findChildViewById(rootView, i2);
                                if (slidingTabLayout != null) {
                                    i2 = R.id.tv_fourthly;
                                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                    if (textView != null) {
                                        i2 = R.id.vp_tab;
                                        NoScrollViewPager noScrollViewPager = (NoScrollViewPager) ViewBindings.findChildViewById(rootView, i2);
                                        if (noScrollViewPager != null) {
                                            return new ActivityPdnumberBinding((LinearLayout) rootView, imageView, navigationBar, nestedScrollView, recyclerView, relativeLayout, smartRefreshLayout, slidingTabLayout, textView, noScrollViewPager);
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
