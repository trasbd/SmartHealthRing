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
import com.flyco.tablayout.SlidingTabLayout;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import com.yucheng.smarthealthpro.home.view.NoScrollViewPager;

/* loaded from: classes4.dex */
public final class ActivityPhysiotherapyBinding implements ViewBinding {
    public final FunctionItemBottomIncludeBinding includeItemBottom;
    public final FunctionItemCalendarIncludeBinding includeItemCalendar;
    public final ImageView ivCalendar;
    public final ImageView ivDataRen;
    public final ImageView ivDataSecond;
    public final ImageView ivDataThirdly;
    public final LinearLayout llCalendar;
    public final LinearLayout llDataRem;
    public final LinearLayout llDataSecond;
    public final LinearLayout llDataThirdly;
    public final NavigationBar navigationbar;
    public final NestedScrollView nsv;
    public final RelativeLayout rlDataFirst;
    private final RelativeLayout rootView;
    public final SlidingTabLayout stlTab;
    public final TextView tvBackToday;
    public final TextView tvCalendar;
    public final TextView tvDataFirst;
    public final TextView tvDataFirstUnit;
    public final TextView tvDataRem;
    public final TextView tvDataRemUnit;
    public final TextView tvDataSecond;
    public final TextView tvDataSecondUnit;
    public final TextView tvDataThirdly;
    public final TextView tvDataThirdlyUnit;
    public final NoScrollViewPager vpTab;

    private ActivityPhysiotherapyBinding(RelativeLayout rootView, FunctionItemBottomIncludeBinding includeItemBottom, FunctionItemCalendarIncludeBinding includeItemCalendar, ImageView ivCalendar, ImageView ivDataRen, ImageView ivDataSecond, ImageView ivDataThirdly, LinearLayout llCalendar, LinearLayout llDataRem, LinearLayout llDataSecond, LinearLayout llDataThirdly, NavigationBar navigationbar, NestedScrollView nsv, RelativeLayout rlDataFirst, SlidingTabLayout stlTab, TextView tvBackToday, TextView tvCalendar, TextView tvDataFirst, TextView tvDataFirstUnit, TextView tvDataRem, TextView tvDataRemUnit, TextView tvDataSecond, TextView tvDataSecondUnit, TextView tvDataThirdly, TextView tvDataThirdlyUnit, NoScrollViewPager vpTab) {
        this.rootView = rootView;
        this.includeItemBottom = includeItemBottom;
        this.includeItemCalendar = includeItemCalendar;
        this.ivCalendar = ivCalendar;
        this.ivDataRen = ivDataRen;
        this.ivDataSecond = ivDataSecond;
        this.ivDataThirdly = ivDataThirdly;
        this.llCalendar = llCalendar;
        this.llDataRem = llDataRem;
        this.llDataSecond = llDataSecond;
        this.llDataThirdly = llDataThirdly;
        this.navigationbar = navigationbar;
        this.nsv = nsv;
        this.rlDataFirst = rlDataFirst;
        this.stlTab = stlTab;
        this.tvBackToday = tvBackToday;
        this.tvCalendar = tvCalendar;
        this.tvDataFirst = tvDataFirst;
        this.tvDataFirstUnit = tvDataFirstUnit;
        this.tvDataRem = tvDataRem;
        this.tvDataRemUnit = tvDataRemUnit;
        this.tvDataSecond = tvDataSecond;
        this.tvDataSecondUnit = tvDataSecondUnit;
        this.tvDataThirdly = tvDataThirdly;
        this.tvDataThirdlyUnit = tvDataThirdlyUnit;
        this.vpTab = vpTab;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ActivityPhysiotherapyBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityPhysiotherapyBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_physiotherapy, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityPhysiotherapyBinding bind(View rootView) {
        int i2 = R.id.include_item_bottom;
        View viewFindChildViewById = ViewBindings.findChildViewById(rootView, i2);
        if (viewFindChildViewById != null) {
            FunctionItemBottomIncludeBinding functionItemBottomIncludeBindingBind = FunctionItemBottomIncludeBinding.bind(viewFindChildViewById);
            i2 = R.id.include_item_calendar;
            View viewFindChildViewById2 = ViewBindings.findChildViewById(rootView, i2);
            if (viewFindChildViewById2 != null) {
                FunctionItemCalendarIncludeBinding functionItemCalendarIncludeBindingBind = FunctionItemCalendarIncludeBinding.bind(viewFindChildViewById2);
                i2 = R.id.iv_calendar;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                if (imageView != null) {
                    i2 = R.id.iv_data_ren;
                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                    if (imageView2 != null) {
                        i2 = R.id.iv_data_second;
                        ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                        if (imageView3 != null) {
                            i2 = R.id.iv_data_thirdly;
                            ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                            if (imageView4 != null) {
                                i2 = R.id.ll_calendar;
                                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                                if (linearLayout != null) {
                                    i2 = R.id.ll_data_rem;
                                    LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                                    if (linearLayout2 != null) {
                                        i2 = R.id.ll_data_second;
                                        LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                                        if (linearLayout3 != null) {
                                            i2 = R.id.ll_data_thirdly;
                                            LinearLayout linearLayout4 = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                                            if (linearLayout4 != null) {
                                                i2 = R.id.navigationbar;
                                                NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
                                                if (navigationBar != null) {
                                                    i2 = R.id.nsv;
                                                    NestedScrollView nestedScrollView = (NestedScrollView) ViewBindings.findChildViewById(rootView, i2);
                                                    if (nestedScrollView != null) {
                                                        i2 = R.id.rl_data_first;
                                                        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                                                        if (relativeLayout != null) {
                                                            i2 = R.id.stl_tab;
                                                            SlidingTabLayout slidingTabLayout = (SlidingTabLayout) ViewBindings.findChildViewById(rootView, i2);
                                                            if (slidingTabLayout != null) {
                                                                i2 = R.id.tv_back_today;
                                                                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                if (textView != null) {
                                                                    i2 = R.id.tv_calendar;
                                                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                    if (textView2 != null) {
                                                                        i2 = R.id.tv_data_first;
                                                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                        if (textView3 != null) {
                                                                            i2 = R.id.tv_data_first_unit;
                                                                            TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                            if (textView4 != null) {
                                                                                i2 = R.id.tv_data_rem;
                                                                                TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                if (textView5 != null) {
                                                                                    i2 = R.id.tv_data_rem_unit;
                                                                                    TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                    if (textView6 != null) {
                                                                                        i2 = R.id.tv_data_second;
                                                                                        TextView textView7 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                        if (textView7 != null) {
                                                                                            i2 = R.id.tv_data_second_unit;
                                                                                            TextView textView8 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                            if (textView8 != null) {
                                                                                                i2 = R.id.tv_data_thirdly;
                                                                                                TextView textView9 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                                if (textView9 != null) {
                                                                                                    i2 = R.id.tv_data_thirdly_unit;
                                                                                                    TextView textView10 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                                    if (textView10 != null) {
                                                                                                        i2 = R.id.vp_tab;
                                                                                                        NoScrollViewPager noScrollViewPager = (NoScrollViewPager) ViewBindings.findChildViewById(rootView, i2);
                                                                                                        if (noScrollViewPager != null) {
                                                                                                            return new ActivityPhysiotherapyBinding((RelativeLayout) rootView, functionItemBottomIncludeBindingBind, functionItemCalendarIncludeBindingBind, imageView, imageView2, imageView3, imageView4, linearLayout, linearLayout2, linearLayout3, linearLayout4, navigationBar, nestedScrollView, relativeLayout, slidingTabLayout, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10, noScrollViewPager);
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
