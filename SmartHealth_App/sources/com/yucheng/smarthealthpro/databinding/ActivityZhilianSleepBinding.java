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
public final class ActivityZhilianSleepBinding implements ViewBinding {
    public final ImageView ivDataRen;
    public final ImageView ivDataSecond;
    public final ImageView ivDataThirdly;
    public final LinearLayout llDataRem;
    public final LinearLayout llDataSecond;
    public final LinearLayout llDataThirdly;
    public final LinearLayout llSecondSleep;
    public final NavigationBar navigationbar;
    public final NestedScrollView nsv;
    public final RelativeLayout rlDataFirst;
    public final RelativeLayout rlFirstSleep;
    public final RelativeLayout rlTotalSleep;
    private final RelativeLayout rootView;
    public final TextView tvDataFirst;
    public final TextView tvDataFirstUnit;
    public final TextView tvDataNapUnit;
    public final TextView tvDataRem;
    public final TextView tvDataRemUnit;
    public final TextView tvDataSecond;
    public final TextView tvDataSecondUnit;
    public final TextView tvDataThirdly;
    public final TextView tvDataThirdlyUnit;
    public final TextView tvFirstSleep;
    public final TextView tvNap;
    public final TextView tvSecondSleep;
    public final TextView tvTotalSleep;
    public final TextView tvTotalSleepText;

    private ActivityZhilianSleepBinding(RelativeLayout rootView, ImageView ivDataRen, ImageView ivDataSecond, ImageView ivDataThirdly, LinearLayout llDataRem, LinearLayout llDataSecond, LinearLayout llDataThirdly, LinearLayout llSecondSleep, NavigationBar navigationbar, NestedScrollView nsv, RelativeLayout rlDataFirst, RelativeLayout rlFirstSleep, RelativeLayout rlTotalSleep, TextView tvDataFirst, TextView tvDataFirstUnit, TextView tvDataNapUnit, TextView tvDataRem, TextView tvDataRemUnit, TextView tvDataSecond, TextView tvDataSecondUnit, TextView tvDataThirdly, TextView tvDataThirdlyUnit, TextView tvFirstSleep, TextView tvNap, TextView tvSecondSleep, TextView tvTotalSleep, TextView tvTotalSleepText) {
        this.rootView = rootView;
        this.ivDataRen = ivDataRen;
        this.ivDataSecond = ivDataSecond;
        this.ivDataThirdly = ivDataThirdly;
        this.llDataRem = llDataRem;
        this.llDataSecond = llDataSecond;
        this.llDataThirdly = llDataThirdly;
        this.llSecondSleep = llSecondSleep;
        this.navigationbar = navigationbar;
        this.nsv = nsv;
        this.rlDataFirst = rlDataFirst;
        this.rlFirstSleep = rlFirstSleep;
        this.rlTotalSleep = rlTotalSleep;
        this.tvDataFirst = tvDataFirst;
        this.tvDataFirstUnit = tvDataFirstUnit;
        this.tvDataNapUnit = tvDataNapUnit;
        this.tvDataRem = tvDataRem;
        this.tvDataRemUnit = tvDataRemUnit;
        this.tvDataSecond = tvDataSecond;
        this.tvDataSecondUnit = tvDataSecondUnit;
        this.tvDataThirdly = tvDataThirdly;
        this.tvDataThirdlyUnit = tvDataThirdlyUnit;
        this.tvFirstSleep = tvFirstSleep;
        this.tvNap = tvNap;
        this.tvSecondSleep = tvSecondSleep;
        this.tvTotalSleep = tvTotalSleep;
        this.tvTotalSleepText = tvTotalSleepText;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ActivityZhilianSleepBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityZhilianSleepBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_zhilian_sleep, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityZhilianSleepBinding bind(View rootView) {
        int i2 = R.id.iv_data_ren;
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
                                i2 = R.id.ll_second_sleep;
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
                                                i2 = R.id.rl_first_sleep;
                                                RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                                                if (relativeLayout2 != null) {
                                                    i2 = R.id.rl_total_sleep;
                                                    RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                                                    if (relativeLayout3 != null) {
                                                        i2 = R.id.tv_data_first;
                                                        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                        if (textView != null) {
                                                            i2 = R.id.tv_data_first_unit;
                                                            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                            if (textView2 != null) {
                                                                i2 = R.id.tv_data_nap_unit;
                                                                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                if (textView3 != null) {
                                                                    i2 = R.id.tv_data_rem;
                                                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                    if (textView4 != null) {
                                                                        i2 = R.id.tv_data_rem_unit;
                                                                        TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                        if (textView5 != null) {
                                                                            i2 = R.id.tv_data_second;
                                                                            TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                            if (textView6 != null) {
                                                                                i2 = R.id.tv_data_second_unit;
                                                                                TextView textView7 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                if (textView7 != null) {
                                                                                    i2 = R.id.tv_data_thirdly;
                                                                                    TextView textView8 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                    if (textView8 != null) {
                                                                                        i2 = R.id.tv_data_thirdly_unit;
                                                                                        TextView textView9 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                        if (textView9 != null) {
                                                                                            i2 = R.id.tv_first_sleep;
                                                                                            TextView textView10 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                            if (textView10 != null) {
                                                                                                i2 = R.id.tv_nap;
                                                                                                TextView textView11 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                                if (textView11 != null) {
                                                                                                    i2 = R.id.tv_second_sleep;
                                                                                                    TextView textView12 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                                    if (textView12 != null) {
                                                                                                        i2 = R.id.tv_total_sleep;
                                                                                                        TextView textView13 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                                        if (textView13 != null) {
                                                                                                            i2 = R.id.tv_total_sleep_text;
                                                                                                            TextView textView14 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                                            if (textView14 != null) {
                                                                                                                return new ActivityZhilianSleepBinding((RelativeLayout) rootView, imageView, imageView2, imageView3, linearLayout, linearLayout2, linearLayout3, linearLayout4, navigationBar, nestedScrollView, relativeLayout, relativeLayout2, relativeLayout3, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10, textView11, textView12, textView13, textView14);
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
