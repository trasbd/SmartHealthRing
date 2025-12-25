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
import com.flyco.tablayout.SlidingTabLayout;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.home.view.NoScrollViewPager;

/* loaded from: classes4.dex */
public final class FunctionItemTopIncludeBinding implements ViewBinding {
    public final ImageView ivCalendar;
    public final LinearLayout llCalendar;
    private final RelativeLayout rootView;
    public final SlidingTabLayout stlTab;
    public final TextView tvBackToday;
    public final TextView tvCalendar;
    public final NoScrollViewPager vpTab;

    private FunctionItemTopIncludeBinding(RelativeLayout rootView, ImageView ivCalendar, LinearLayout llCalendar, SlidingTabLayout stlTab, TextView tvBackToday, TextView tvCalendar, NoScrollViewPager vpTab) {
        this.rootView = rootView;
        this.ivCalendar = ivCalendar;
        this.llCalendar = llCalendar;
        this.stlTab = stlTab;
        this.tvBackToday = tvBackToday;
        this.tvCalendar = tvCalendar;
        this.vpTab = vpTab;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static FunctionItemTopIncludeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FunctionItemTopIncludeBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.function_item_top_include, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FunctionItemTopIncludeBinding bind(View rootView) {
        int i2 = R.id.iv_calendar;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i2);
        if (imageView != null) {
            i2 = R.id.ll_calendar;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
            if (linearLayout != null) {
                i2 = R.id.stl_tab;
                SlidingTabLayout slidingTabLayout = (SlidingTabLayout) ViewBindings.findChildViewById(rootView, i2);
                if (slidingTabLayout != null) {
                    i2 = R.id.tv_back_today;
                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                    if (textView != null) {
                        i2 = R.id.tv_calendar;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                        if (textView2 != null) {
                            i2 = R.id.vp_tab;
                            NoScrollViewPager noScrollViewPager = (NoScrollViewPager) ViewBindings.findChildViewById(rootView, i2);
                            if (noScrollViewPager != null) {
                                return new FunctionItemTopIncludeBinding((RelativeLayout) rootView, imageView, linearLayout, slidingTabLayout, textView, textView2, noScrollViewPager);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
