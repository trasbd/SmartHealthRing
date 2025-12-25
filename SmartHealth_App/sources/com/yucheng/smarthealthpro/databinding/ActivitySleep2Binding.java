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
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;

/* loaded from: classes4.dex */
public final class ActivitySleep2Binding implements ViewBinding {
    public final FunctionItemBottomIncludeBinding includeItemBottom;
    public final FunctionItemCalendarIncludeBinding includeItemCalendar;
    public final FunctionItemTopIncludeBinding includeItemTop;
    public final ImageView ivDataRen;
    public final ImageView ivDataSecond;
    public final ImageView ivDataThirdly;
    public final LinearLayout llDataRem;
    public final LinearLayout llDataSecond;
    public final LinearLayout llDataThirdly;
    public final NavigationBar navigationbar;
    public final NestedScrollView nsv;
    public final RelativeLayout rlDataFirst;
    private final RelativeLayout rootView;
    public final TextView tvDataFirst;
    public final TextView tvDataFirstUnit;
    public final TextView tvDataRem;
    public final TextView tvDataRemUnit;
    public final TextView tvDataSecond;
    public final TextView tvDataSecondUnit;
    public final TextView tvDataThirdly;
    public final TextView tvDataThirdlyUnit;

    private ActivitySleep2Binding(RelativeLayout rootView, FunctionItemBottomIncludeBinding includeItemBottom, FunctionItemCalendarIncludeBinding includeItemCalendar, FunctionItemTopIncludeBinding includeItemTop, ImageView ivDataRen, ImageView ivDataSecond, ImageView ivDataThirdly, LinearLayout llDataRem, LinearLayout llDataSecond, LinearLayout llDataThirdly, NavigationBar navigationbar, NestedScrollView nsv, RelativeLayout rlDataFirst, TextView tvDataFirst, TextView tvDataFirstUnit, TextView tvDataRem, TextView tvDataRemUnit, TextView tvDataSecond, TextView tvDataSecondUnit, TextView tvDataThirdly, TextView tvDataThirdlyUnit) {
        this.rootView = rootView;
        this.includeItemBottom = includeItemBottom;
        this.includeItemCalendar = includeItemCalendar;
        this.includeItemTop = includeItemTop;
        this.ivDataRen = ivDataRen;
        this.ivDataSecond = ivDataSecond;
        this.ivDataThirdly = ivDataThirdly;
        this.llDataRem = llDataRem;
        this.llDataSecond = llDataSecond;
        this.llDataThirdly = llDataThirdly;
        this.navigationbar = navigationbar;
        this.nsv = nsv;
        this.rlDataFirst = rlDataFirst;
        this.tvDataFirst = tvDataFirst;
        this.tvDataFirstUnit = tvDataFirstUnit;
        this.tvDataRem = tvDataRem;
        this.tvDataRemUnit = tvDataRemUnit;
        this.tvDataSecond = tvDataSecond;
        this.tvDataSecondUnit = tvDataSecondUnit;
        this.tvDataThirdly = tvDataThirdly;
        this.tvDataThirdlyUnit = tvDataThirdlyUnit;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ActivitySleep2Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivitySleep2Binding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_sleep2, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivitySleep2Binding bind(View rootView) {
        int i2 = R.id.include_item_bottom;
        View viewFindChildViewById = ViewBindings.findChildViewById(rootView, i2);
        if (viewFindChildViewById != null) {
            FunctionItemBottomIncludeBinding functionItemBottomIncludeBindingBind = FunctionItemBottomIncludeBinding.bind(viewFindChildViewById);
            i2 = R.id.include_item_calendar;
            View viewFindChildViewById2 = ViewBindings.findChildViewById(rootView, i2);
            if (viewFindChildViewById2 != null) {
                FunctionItemCalendarIncludeBinding functionItemCalendarIncludeBindingBind = FunctionItemCalendarIncludeBinding.bind(viewFindChildViewById2);
                i2 = R.id.include_item_top;
                View viewFindChildViewById3 = ViewBindings.findChildViewById(rootView, i2);
                if (viewFindChildViewById3 != null) {
                    FunctionItemTopIncludeBinding functionItemTopIncludeBindingBind = FunctionItemTopIncludeBinding.bind(viewFindChildViewById3);
                    i2 = R.id.iv_data_ren;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                    if (imageView != null) {
                        i2 = R.id.iv_data_second;
                        ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                        if (imageView2 != null) {
                            i2 = R.id.iv_data_thirdly;
                            ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                            if (imageView3 != null) {
                                i2 = R.id.ll_data_rem;
                                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                                if (linearLayout != null) {
                                    i2 = R.id.ll_data_second;
                                    LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                                    if (linearLayout2 != null) {
                                        i2 = R.id.ll_data_thirdly;
                                        LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                                        if (linearLayout3 != null) {
                                            i2 = R.id.navigationbar;
                                            NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
                                            if (navigationBar != null) {
                                                i2 = R.id.nsv;
                                                NestedScrollView nestedScrollView = (NestedScrollView) ViewBindings.findChildViewById(rootView, i2);
                                                if (nestedScrollView != null) {
                                                    i2 = R.id.rl_data_first;
                                                    RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                                                    if (relativeLayout != null) {
                                                        i2 = R.id.tv_data_first;
                                                        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                        if (textView != null) {
                                                            i2 = R.id.tv_data_first_unit;
                                                            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                            if (textView2 != null) {
                                                                i2 = R.id.tv_data_rem;
                                                                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                if (textView3 != null) {
                                                                    i2 = R.id.tv_data_rem_unit;
                                                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                    if (textView4 != null) {
                                                                        i2 = R.id.tv_data_second;
                                                                        TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                        if (textView5 != null) {
                                                                            i2 = R.id.tv_data_second_unit;
                                                                            TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                            if (textView6 != null) {
                                                                                i2 = R.id.tv_data_thirdly;
                                                                                TextView textView7 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                if (textView7 != null) {
                                                                                    i2 = R.id.tv_data_thirdly_unit;
                                                                                    TextView textView8 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                    if (textView8 != null) {
                                                                                        return new ActivitySleep2Binding((RelativeLayout) rootView, functionItemBottomIncludeBindingBind, functionItemCalendarIncludeBindingBind, functionItemTopIncludeBindingBind, imageView, imageView2, imageView3, linearLayout, linearLayout2, linearLayout3, navigationBar, nestedScrollView, relativeLayout, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8);
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
