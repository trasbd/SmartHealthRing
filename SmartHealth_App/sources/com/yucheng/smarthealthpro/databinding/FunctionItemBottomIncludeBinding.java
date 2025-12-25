package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.yucheng.smarthealthpro.R;

/* loaded from: classes4.dex */
public final class FunctionItemBottomIncludeBinding implements ViewBinding {
    public final ImageView ivFirstLeft;
    public final ImageView ivFirstRight;
    public final ImageView ivFourthlyLeft;
    public final ImageView ivFourthlyRight;
    public final ImageView ivPlan;
    public final ImageView ivPlanRight;
    public final ImageView ivSecondLeft;
    public final ImageView ivSecondRight;
    public final ImageView ivThirdlyLeft;
    public final ImageView ivThirdlyRight;
    public final LinearLayout llPressureAnalyse;
    public final LinearLayout llStartButton;
    public final RecyclerView recycleView;
    public final RelativeLayout rlAnalyse;
    public final RelativeLayout rlFirst;
    public final RelativeLayout rlFourthly;
    public final RelativeLayout rlPlan;
    public final RelativeLayout rlSecond;
    public final RelativeLayout rlThirdly;
    private final LinearLayout rootView;
    public final SmartRefreshLayout srlEcg;
    public final TextView tvAdditionalMsg;
    public final TextView tvAnalyse;
    public final TextView tvAnalyseData;
    public final TextView tvFirst;
    public final TextView tvFourthly;
    public final TextView tvPlan;
    public final TextView tvSecond;
    public final TextView tvStartButton;
    public final TextView tvStartButton2;
    public final TextView tvThirdly;

    private FunctionItemBottomIncludeBinding(LinearLayout rootView, ImageView ivFirstLeft, ImageView ivFirstRight, ImageView ivFourthlyLeft, ImageView ivFourthlyRight, ImageView ivPlan, ImageView ivPlanRight, ImageView ivSecondLeft, ImageView ivSecondRight, ImageView ivThirdlyLeft, ImageView ivThirdlyRight, LinearLayout llPressureAnalyse, LinearLayout llStartButton, RecyclerView recycleView, RelativeLayout rlAnalyse, RelativeLayout rlFirst, RelativeLayout rlFourthly, RelativeLayout rlPlan, RelativeLayout rlSecond, RelativeLayout rlThirdly, SmartRefreshLayout srlEcg, TextView tvAdditionalMsg, TextView tvAnalyse, TextView tvAnalyseData, TextView tvFirst, TextView tvFourthly, TextView tvPlan, TextView tvSecond, TextView tvStartButton, TextView tvStartButton2, TextView tvThirdly) {
        this.rootView = rootView;
        this.ivFirstLeft = ivFirstLeft;
        this.ivFirstRight = ivFirstRight;
        this.ivFourthlyLeft = ivFourthlyLeft;
        this.ivFourthlyRight = ivFourthlyRight;
        this.ivPlan = ivPlan;
        this.ivPlanRight = ivPlanRight;
        this.ivSecondLeft = ivSecondLeft;
        this.ivSecondRight = ivSecondRight;
        this.ivThirdlyLeft = ivThirdlyLeft;
        this.ivThirdlyRight = ivThirdlyRight;
        this.llPressureAnalyse = llPressureAnalyse;
        this.llStartButton = llStartButton;
        this.recycleView = recycleView;
        this.rlAnalyse = rlAnalyse;
        this.rlFirst = rlFirst;
        this.rlFourthly = rlFourthly;
        this.rlPlan = rlPlan;
        this.rlSecond = rlSecond;
        this.rlThirdly = rlThirdly;
        this.srlEcg = srlEcg;
        this.tvAdditionalMsg = tvAdditionalMsg;
        this.tvAnalyse = tvAnalyse;
        this.tvAnalyseData = tvAnalyseData;
        this.tvFirst = tvFirst;
        this.tvFourthly = tvFourthly;
        this.tvPlan = tvPlan;
        this.tvSecond = tvSecond;
        this.tvStartButton = tvStartButton;
        this.tvStartButton2 = tvStartButton2;
        this.tvThirdly = tvThirdly;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static FunctionItemBottomIncludeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FunctionItemBottomIncludeBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.function_item_bottom_include, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FunctionItemBottomIncludeBinding bind(View rootView) {
        int i2 = R.id.iv_first_left;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i2);
        if (imageView != null) {
            i2 = R.id.iv_first_right;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
            if (imageView2 != null) {
                i2 = R.id.iv_fourthly_left;
                ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                if (imageView3 != null) {
                    i2 = R.id.iv_fourthly_right;
                    ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                    if (imageView4 != null) {
                        i2 = R.id.iv_plan;
                        ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                        if (imageView5 != null) {
                            i2 = R.id.iv_plan_right;
                            ImageView imageView6 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                            if (imageView6 != null) {
                                i2 = R.id.iv_second_left;
                                ImageView imageView7 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                                if (imageView7 != null) {
                                    i2 = R.id.iv_second_right;
                                    ImageView imageView8 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                                    if (imageView8 != null) {
                                        i2 = R.id.iv_thirdly_left;
                                        ImageView imageView9 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                                        if (imageView9 != null) {
                                            i2 = R.id.iv_thirdly_right;
                                            ImageView imageView10 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                                            if (imageView10 != null) {
                                                i2 = R.id.ll_pressure_analyse;
                                                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                                                if (linearLayout != null) {
                                                    i2 = R.id.ll_start_button;
                                                    LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                                                    if (linearLayout2 != null) {
                                                        i2 = R.id.recycle_view;
                                                        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, i2);
                                                        if (recyclerView != null) {
                                                            i2 = R.id.rl_analyse;
                                                            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                                                            if (relativeLayout != null) {
                                                                i2 = R.id.rl_first;
                                                                RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                                                                if (relativeLayout2 != null) {
                                                                    i2 = R.id.rl_fourthly;
                                                                    RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                                                                    if (relativeLayout3 != null) {
                                                                        i2 = R.id.rl_plan;
                                                                        RelativeLayout relativeLayout4 = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                                                                        if (relativeLayout4 != null) {
                                                                            i2 = R.id.rl_second;
                                                                            RelativeLayout relativeLayout5 = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                                                                            if (relativeLayout5 != null) {
                                                                                i2 = R.id.rl_thirdly;
                                                                                RelativeLayout relativeLayout6 = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                                                                                if (relativeLayout6 != null) {
                                                                                    i2 = R.id.srl_ecg;
                                                                                    SmartRefreshLayout smartRefreshLayout = (SmartRefreshLayout) ViewBindings.findChildViewById(rootView, i2);
                                                                                    if (smartRefreshLayout != null) {
                                                                                        i2 = R.id.tv_additional_msg;
                                                                                        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                        if (textView != null) {
                                                                                            i2 = R.id.tv_analyse;
                                                                                            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                            if (textView2 != null) {
                                                                                                i2 = R.id.tv_analyse_data;
                                                                                                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                                if (textView3 != null) {
                                                                                                    i2 = R.id.tv_first;
                                                                                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                                    if (textView4 != null) {
                                                                                                        i2 = R.id.tv_fourthly;
                                                                                                        TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                                        if (textView5 != null) {
                                                                                                            i2 = R.id.tv_plan;
                                                                                                            TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                                            if (textView6 != null) {
                                                                                                                i2 = R.id.tv_second;
                                                                                                                TextView textView7 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                if (textView7 != null) {
                                                                                                                    i2 = R.id.tv_start_button;
                                                                                                                    TextView textView8 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                    if (textView8 != null) {
                                                                                                                        i2 = R.id.tv_start_button2;
                                                                                                                        TextView textView9 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                        if (textView9 != null) {
                                                                                                                            i2 = R.id.tv_thirdly;
                                                                                                                            TextView textView10 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                            if (textView10 != null) {
                                                                                                                                return new FunctionItemBottomIncludeBinding((LinearLayout) rootView, imageView, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8, imageView9, imageView10, linearLayout, linearLayout2, recyclerView, relativeLayout, relativeLayout2, relativeLayout3, relativeLayout4, relativeLayout5, relativeLayout6, smartRefreshLayout, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10);
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
