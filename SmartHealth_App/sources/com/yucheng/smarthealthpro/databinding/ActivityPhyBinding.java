package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
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
public final class ActivityPhyBinding implements ViewBinding {
    public final TextView gear1;
    public final TextView gear2;
    public final TextView gear3;
    public final TextView gear4;
    public final Guideline gearLine;
    public final FunctionItemCalendarIncludeBinding includeItemCalendar;
    public final ImageView ivCalendar;
    public final ImageView ivDataRen;
    public final ImageView ivDataSecond;
    public final ImageView ivDataThirdly;
    public final ImageView ivFirstLeft;
    public final ImageView ivFirstRight;
    public final ImageView ivFourthlyLeft;
    public final ImageView ivFourthlyRight;
    public final ImageView ivSecondLeft;
    public final ImageView ivSecondRight;
    public final ImageView ivThirdlyLeft;
    public final ImageView ivThirdlyRight;
    public final ConstraintLayout layoutDayGrid;
    public final RelativeLayout layoutGrid;
    public final LinearLayout llCalendar;
    public final LinearLayout llDataRem;
    public final LinearLayout llDataSecond;
    public final LinearLayout llDataThirdly;
    public final LinearLayout llStartButton;
    public final NavigationBar navigationbar;
    public final NestedScrollView nsv;
    public final RecyclerView recycleView;
    public final RelativeLayout rlAnalyse;
    public final RelativeLayout rlDataFirst;
    public final RelativeLayout rlFirst;
    public final RelativeLayout rlFourthly;
    public final RelativeLayout rlSecond;
    public final RelativeLayout rlThirdly;
    private final RelativeLayout rootView;
    public final SmartRefreshLayout srlEcg;
    public final SlidingTabLayout stlTab;
    public final TextView tvAnalyse;
    public final TextView tvAnalyseData;
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
    public final TextView tvFirst;
    public final TextView tvFourthly;
    public final TextView tvSecond;
    public final TextView tvStartButton;
    public final TextView tvThirdly;
    public final NoScrollViewPager vpTab;

    private ActivityPhyBinding(RelativeLayout rootView, TextView gear1, TextView gear2, TextView gear3, TextView gear4, Guideline gearLine, FunctionItemCalendarIncludeBinding includeItemCalendar, ImageView ivCalendar, ImageView ivDataRen, ImageView ivDataSecond, ImageView ivDataThirdly, ImageView ivFirstLeft, ImageView ivFirstRight, ImageView ivFourthlyLeft, ImageView ivFourthlyRight, ImageView ivSecondLeft, ImageView ivSecondRight, ImageView ivThirdlyLeft, ImageView ivThirdlyRight, ConstraintLayout layoutDayGrid, RelativeLayout layoutGrid, LinearLayout llCalendar, LinearLayout llDataRem, LinearLayout llDataSecond, LinearLayout llDataThirdly, LinearLayout llStartButton, NavigationBar navigationbar, NestedScrollView nsv, RecyclerView recycleView, RelativeLayout rlAnalyse, RelativeLayout rlDataFirst, RelativeLayout rlFirst, RelativeLayout rlFourthly, RelativeLayout rlSecond, RelativeLayout rlThirdly, SmartRefreshLayout srlEcg, SlidingTabLayout stlTab, TextView tvAnalyse, TextView tvAnalyseData, TextView tvBackToday, TextView tvCalendar, TextView tvDataFirst, TextView tvDataFirstUnit, TextView tvDataRem, TextView tvDataRemUnit, TextView tvDataSecond, TextView tvDataSecondUnit, TextView tvDataThirdly, TextView tvDataThirdlyUnit, TextView tvFirst, TextView tvFourthly, TextView tvSecond, TextView tvStartButton, TextView tvThirdly, NoScrollViewPager vpTab) {
        this.rootView = rootView;
        this.gear1 = gear1;
        this.gear2 = gear2;
        this.gear3 = gear3;
        this.gear4 = gear4;
        this.gearLine = gearLine;
        this.includeItemCalendar = includeItemCalendar;
        this.ivCalendar = ivCalendar;
        this.ivDataRen = ivDataRen;
        this.ivDataSecond = ivDataSecond;
        this.ivDataThirdly = ivDataThirdly;
        this.ivFirstLeft = ivFirstLeft;
        this.ivFirstRight = ivFirstRight;
        this.ivFourthlyLeft = ivFourthlyLeft;
        this.ivFourthlyRight = ivFourthlyRight;
        this.ivSecondLeft = ivSecondLeft;
        this.ivSecondRight = ivSecondRight;
        this.ivThirdlyLeft = ivThirdlyLeft;
        this.ivThirdlyRight = ivThirdlyRight;
        this.layoutDayGrid = layoutDayGrid;
        this.layoutGrid = layoutGrid;
        this.llCalendar = llCalendar;
        this.llDataRem = llDataRem;
        this.llDataSecond = llDataSecond;
        this.llDataThirdly = llDataThirdly;
        this.llStartButton = llStartButton;
        this.navigationbar = navigationbar;
        this.nsv = nsv;
        this.recycleView = recycleView;
        this.rlAnalyse = rlAnalyse;
        this.rlDataFirst = rlDataFirst;
        this.rlFirst = rlFirst;
        this.rlFourthly = rlFourthly;
        this.rlSecond = rlSecond;
        this.rlThirdly = rlThirdly;
        this.srlEcg = srlEcg;
        this.stlTab = stlTab;
        this.tvAnalyse = tvAnalyse;
        this.tvAnalyseData = tvAnalyseData;
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
        this.tvFirst = tvFirst;
        this.tvFourthly = tvFourthly;
        this.tvSecond = tvSecond;
        this.tvStartButton = tvStartButton;
        this.tvThirdly = tvThirdly;
        this.vpTab = vpTab;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ActivityPhyBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityPhyBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_phy, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityPhyBinding bind(View rootView) {
        View viewFindChildViewById;
        int i2 = R.id.gear_1;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
        if (textView != null) {
            i2 = R.id.gear_2;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
            if (textView2 != null) {
                i2 = R.id.gear_3;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                if (textView3 != null) {
                    i2 = R.id.gear_4;
                    TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                    if (textView4 != null) {
                        i2 = R.id.gear_line;
                        Guideline guideline = (Guideline) ViewBindings.findChildViewById(rootView, i2);
                        if (guideline != null && (viewFindChildViewById = ViewBindings.findChildViewById(rootView, (i2 = R.id.include_item_calendar))) != null) {
                            FunctionItemCalendarIncludeBinding functionItemCalendarIncludeBindingBind = FunctionItemCalendarIncludeBinding.bind(viewFindChildViewById);
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
                                            i2 = R.id.iv_first_left;
                                            ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                                            if (imageView5 != null) {
                                                i2 = R.id.iv_first_right;
                                                ImageView imageView6 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                                                if (imageView6 != null) {
                                                    i2 = R.id.iv_fourthly_left;
                                                    ImageView imageView7 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                                                    if (imageView7 != null) {
                                                        i2 = R.id.iv_fourthly_right;
                                                        ImageView imageView8 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                                                        if (imageView8 != null) {
                                                            i2 = R.id.iv_second_left;
                                                            ImageView imageView9 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                                                            if (imageView9 != null) {
                                                                i2 = R.id.iv_second_right;
                                                                ImageView imageView10 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                                                                if (imageView10 != null) {
                                                                    i2 = R.id.iv_thirdly_left;
                                                                    ImageView imageView11 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                                                                    if (imageView11 != null) {
                                                                        i2 = R.id.iv_thirdly_right;
                                                                        ImageView imageView12 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                                                                        if (imageView12 != null) {
                                                                            i2 = R.id.layoutDayGrid;
                                                                            ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i2);
                                                                            if (constraintLayout != null) {
                                                                                i2 = R.id.layoutGrid;
                                                                                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                                                                                if (relativeLayout != null) {
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
                                                                                                    i2 = R.id.ll_start_button;
                                                                                                    LinearLayout linearLayout5 = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                                                                                                    if (linearLayout5 != null) {
                                                                                                        i2 = R.id.navigationbar;
                                                                                                        NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
                                                                                                        if (navigationBar != null) {
                                                                                                            i2 = R.id.nsv;
                                                                                                            NestedScrollView nestedScrollView = (NestedScrollView) ViewBindings.findChildViewById(rootView, i2);
                                                                                                            if (nestedScrollView != null) {
                                                                                                                i2 = R.id.recycle_view;
                                                                                                                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                if (recyclerView != null) {
                                                                                                                    i2 = R.id.rl_analyse;
                                                                                                                    RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                    if (relativeLayout2 != null) {
                                                                                                                        i2 = R.id.rl_data_first;
                                                                                                                        RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                        if (relativeLayout3 != null) {
                                                                                                                            i2 = R.id.rl_first;
                                                                                                                            RelativeLayout relativeLayout4 = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                            if (relativeLayout4 != null) {
                                                                                                                                i2 = R.id.rl_fourthly;
                                                                                                                                RelativeLayout relativeLayout5 = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                                if (relativeLayout5 != null) {
                                                                                                                                    i2 = R.id.rl_second;
                                                                                                                                    RelativeLayout relativeLayout6 = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                                    if (relativeLayout6 != null) {
                                                                                                                                        i2 = R.id.rl_thirdly;
                                                                                                                                        RelativeLayout relativeLayout7 = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                                        if (relativeLayout7 != null) {
                                                                                                                                            i2 = R.id.srl_ecg;
                                                                                                                                            SmartRefreshLayout smartRefreshLayout = (SmartRefreshLayout) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                                            if (smartRefreshLayout != null) {
                                                                                                                                                i2 = R.id.stl_tab;
                                                                                                                                                SlidingTabLayout slidingTabLayout = (SlidingTabLayout) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                                                if (slidingTabLayout != null) {
                                                                                                                                                    i2 = R.id.tv_analyse;
                                                                                                                                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                                                    if (textView5 != null) {
                                                                                                                                                        i2 = R.id.tv_analyse_data;
                                                                                                                                                        TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                                                        if (textView6 != null) {
                                                                                                                                                            i2 = R.id.tv_back_today;
                                                                                                                                                            TextView textView7 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                                                            if (textView7 != null) {
                                                                                                                                                                i2 = R.id.tv_calendar;
                                                                                                                                                                TextView textView8 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                                                                if (textView8 != null) {
                                                                                                                                                                    i2 = R.id.tv_data_first;
                                                                                                                                                                    TextView textView9 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                                                                    if (textView9 != null) {
                                                                                                                                                                        i2 = R.id.tv_data_first_unit;
                                                                                                                                                                        TextView textView10 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                                                                        if (textView10 != null) {
                                                                                                                                                                            i2 = R.id.tv_data_rem;
                                                                                                                                                                            TextView textView11 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                                                                            if (textView11 != null) {
                                                                                                                                                                                i2 = R.id.tv_data_rem_unit;
                                                                                                                                                                                TextView textView12 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                                                                                if (textView12 != null) {
                                                                                                                                                                                    i2 = R.id.tv_data_second;
                                                                                                                                                                                    TextView textView13 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                                                                                    if (textView13 != null) {
                                                                                                                                                                                        i2 = R.id.tv_data_second_unit;
                                                                                                                                                                                        TextView textView14 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                                                                                        if (textView14 != null) {
                                                                                                                                                                                            i2 = R.id.tv_data_thirdly;
                                                                                                                                                                                            TextView textView15 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                                                                                            if (textView15 != null) {
                                                                                                                                                                                                i2 = R.id.tv_data_thirdly_unit;
                                                                                                                                                                                                TextView textView16 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                                                                                                if (textView16 != null) {
                                                                                                                                                                                                    i2 = R.id.tv_first;
                                                                                                                                                                                                    TextView textView17 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                                                                                                    if (textView17 != null) {
                                                                                                                                                                                                        i2 = R.id.tv_fourthly;
                                                                                                                                                                                                        TextView textView18 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                                                                                                        if (textView18 != null) {
                                                                                                                                                                                                            i2 = R.id.tv_second;
                                                                                                                                                                                                            TextView textView19 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                                                                                                            if (textView19 != null) {
                                                                                                                                                                                                                i2 = R.id.tv_start_button;
                                                                                                                                                                                                                TextView textView20 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                                                                                                                if (textView20 != null) {
                                                                                                                                                                                                                    i2 = R.id.tv_thirdly;
                                                                                                                                                                                                                    TextView textView21 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                                                                                                                    if (textView21 != null) {
                                                                                                                                                                                                                        i2 = R.id.vp_tab;
                                                                                                                                                                                                                        NoScrollViewPager noScrollViewPager = (NoScrollViewPager) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                                                                                                                        if (noScrollViewPager != null) {
                                                                                                                                                                                                                            return new ActivityPhyBinding((RelativeLayout) rootView, textView, textView2, textView3, textView4, guideline, functionItemCalendarIncludeBindingBind, imageView, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8, imageView9, imageView10, imageView11, imageView12, constraintLayout, relativeLayout, linearLayout, linearLayout2, linearLayout3, linearLayout4, linearLayout5, navigationBar, nestedScrollView, recyclerView, relativeLayout2, relativeLayout3, relativeLayout4, relativeLayout5, relativeLayout6, relativeLayout7, smartRefreshLayout, slidingTabLayout, textView5, textView6, textView7, textView8, textView9, textView10, textView11, textView12, textView13, textView14, textView15, textView16, textView17, textView18, textView19, textView20, textView21, noScrollViewPager);
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
