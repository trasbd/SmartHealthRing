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
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;

/* loaded from: classes4.dex */
public final class ItemHomeFunctionBinding implements ViewBinding {
    public final FrameLayout flLearn;
    public final ImageView ivDataQuestion;
    public final ImageView ivHomeFunction;
    public final ImageView ivTriangle;
    public final LinearLayout llData;
    public final LinearLayoutCompat llLayout2;
    public final LinearLayout llSleepTime;
    public final ImageView rlHomeFunctionEcg;
    public final RelativeLayout rlLayout1;
    public final RelativeLayout rlLayout3;
    private final RelativeLayout rootView;
    public final TextView tvName;
    public final TextView tvSleepDate;
    public final TextView tvSleepTitle;
    public final TextView tvSleepUnit;
    public final TextView tvSleepUnit2;
    public final TextView tvSleepValue;
    public final TextView tvSleepValue2;
    public final TextView tvStatus;
    public final TextView tvUnit;
    public final TextView tvUnit2;
    public final TextView tvValue;
    public final TextView tvValue2;

    private ItemHomeFunctionBinding(RelativeLayout rootView, FrameLayout flLearn, ImageView ivDataQuestion, ImageView ivHomeFunction, ImageView ivTriangle, LinearLayout llData, LinearLayoutCompat llLayout2, LinearLayout llSleepTime, ImageView rlHomeFunctionEcg, RelativeLayout rlLayout1, RelativeLayout rlLayout3, TextView tvName, TextView tvSleepDate, TextView tvSleepTitle, TextView tvSleepUnit, TextView tvSleepUnit2, TextView tvSleepValue, TextView tvSleepValue2, TextView tvStatus, TextView tvUnit, TextView tvUnit2, TextView tvValue, TextView tvValue2) {
        this.rootView = rootView;
        this.flLearn = flLearn;
        this.ivDataQuestion = ivDataQuestion;
        this.ivHomeFunction = ivHomeFunction;
        this.ivTriangle = ivTriangle;
        this.llData = llData;
        this.llLayout2 = llLayout2;
        this.llSleepTime = llSleepTime;
        this.rlHomeFunctionEcg = rlHomeFunctionEcg;
        this.rlLayout1 = rlLayout1;
        this.rlLayout3 = rlLayout3;
        this.tvName = tvName;
        this.tvSleepDate = tvSleepDate;
        this.tvSleepTitle = tvSleepTitle;
        this.tvSleepUnit = tvSleepUnit;
        this.tvSleepUnit2 = tvSleepUnit2;
        this.tvSleepValue = tvSleepValue;
        this.tvSleepValue2 = tvSleepValue2;
        this.tvStatus = tvStatus;
        this.tvUnit = tvUnit;
        this.tvUnit2 = tvUnit2;
        this.tvValue = tvValue;
        this.tvValue2 = tvValue2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ItemHomeFunctionBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemHomeFunctionBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.item_home_function, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemHomeFunctionBinding bind(View rootView) {
        int i2 = R.id.fl_learn;
        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(rootView, i2);
        if (frameLayout != null) {
            i2 = R.id.iv_data_question;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i2);
            if (imageView != null) {
                i2 = R.id.iv_home_function;
                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                if (imageView2 != null) {
                    i2 = R.id.iv_triangle;
                    ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                    if (imageView3 != null) {
                        i2 = R.id.ll_data;
                        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                        if (linearLayout != null) {
                            i2 = R.id.ll_layout2;
                            LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) ViewBindings.findChildViewById(rootView, i2);
                            if (linearLayoutCompat != null) {
                                i2 = R.id.ll_sleep_time;
                                LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                                if (linearLayout2 != null) {
                                    i2 = R.id.rl_home_function_ecg;
                                    ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                                    if (imageView4 != null) {
                                        i2 = R.id.rl_layout1;
                                        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                                        if (relativeLayout != null) {
                                            i2 = R.id.rl_layout3;
                                            RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                                            if (relativeLayout2 != null) {
                                                i2 = R.id.tv_name;
                                                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                if (textView != null) {
                                                    i2 = R.id.tv_sleep_date;
                                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                    if (textView2 != null) {
                                                        i2 = R.id.tv_sleep_title;
                                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                        if (textView3 != null) {
                                                            i2 = R.id.tv_sleep_unit;
                                                            TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                            if (textView4 != null) {
                                                                i2 = R.id.tv_sleep_unit2;
                                                                TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                if (textView5 != null) {
                                                                    i2 = R.id.tv_sleep_value;
                                                                    TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                    if (textView6 != null) {
                                                                        i2 = R.id.tv_sleep_value2;
                                                                        TextView textView7 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                        if (textView7 != null) {
                                                                            i2 = R.id.tv_status;
                                                                            TextView textView8 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                            if (textView8 != null) {
                                                                                i2 = R.id.tv_unit;
                                                                                TextView textView9 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                if (textView9 != null) {
                                                                                    i2 = R.id.tv_unit2;
                                                                                    TextView textView10 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                    if (textView10 != null) {
                                                                                        i2 = R.id.tv_value;
                                                                                        TextView textView11 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                        if (textView11 != null) {
                                                                                            i2 = R.id.tv_value2;
                                                                                            TextView textView12 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                            if (textView12 != null) {
                                                                                                return new ItemHomeFunctionBinding((RelativeLayout) rootView, frameLayout, imageView, imageView2, imageView3, linearLayout, linearLayoutCompat, linearLayout2, imageView4, relativeLayout, relativeLayout2, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10, textView11, textView12);
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
