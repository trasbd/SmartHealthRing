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
import com.yucheng.smarthealthpro.R;

/* loaded from: classes4.dex */
public final class FunctionItemMeasureDataIncludeBinding implements ViewBinding {
    public final ImageView ivDataSecond;
    public final ImageView ivDataThirdly;
    public final LinearLayout llDataSecond;
    public final LinearLayout llDataThirdly;
    public final RelativeLayout rlDataFirst;
    private final RelativeLayout rootView;
    public final TextView tvDataFirst;
    public final TextView tvDataFirstUnit;
    public final TextView tvDataSecond;
    public final TextView tvDataSecondUnit;
    public final TextView tvDataThirdly;
    public final TextView tvDataThirdlyUnit;

    private FunctionItemMeasureDataIncludeBinding(RelativeLayout rootView, ImageView ivDataSecond, ImageView ivDataThirdly, LinearLayout llDataSecond, LinearLayout llDataThirdly, RelativeLayout rlDataFirst, TextView tvDataFirst, TextView tvDataFirstUnit, TextView tvDataSecond, TextView tvDataSecondUnit, TextView tvDataThirdly, TextView tvDataThirdlyUnit) {
        this.rootView = rootView;
        this.ivDataSecond = ivDataSecond;
        this.ivDataThirdly = ivDataThirdly;
        this.llDataSecond = llDataSecond;
        this.llDataThirdly = llDataThirdly;
        this.rlDataFirst = rlDataFirst;
        this.tvDataFirst = tvDataFirst;
        this.tvDataFirstUnit = tvDataFirstUnit;
        this.tvDataSecond = tvDataSecond;
        this.tvDataSecondUnit = tvDataSecondUnit;
        this.tvDataThirdly = tvDataThirdly;
        this.tvDataThirdlyUnit = tvDataThirdlyUnit;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static FunctionItemMeasureDataIncludeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FunctionItemMeasureDataIncludeBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.function_item_measure_data_include, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FunctionItemMeasureDataIncludeBinding bind(View rootView) {
        int i2 = R.id.iv_data_second;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i2);
        if (imageView != null) {
            i2 = R.id.iv_data_thirdly;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
            if (imageView2 != null) {
                i2 = R.id.ll_data_second;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                if (linearLayout != null) {
                    i2 = R.id.ll_data_thirdly;
                    LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                    if (linearLayout2 != null) {
                        i2 = R.id.rl_data_first;
                        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                        if (relativeLayout != null) {
                            i2 = R.id.tv_data_first;
                            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                            if (textView != null) {
                                i2 = R.id.tv_data_first_unit;
                                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                if (textView2 != null) {
                                    i2 = R.id.tv_data_second;
                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                    if (textView3 != null) {
                                        i2 = R.id.tv_data_second_unit;
                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                        if (textView4 != null) {
                                            i2 = R.id.tv_data_thirdly;
                                            TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                            if (textView5 != null) {
                                                i2 = R.id.tv_data_thirdly_unit;
                                                TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                if (textView6 != null) {
                                                    return new FunctionItemMeasureDataIncludeBinding((RelativeLayout) rootView, imageView, imageView2, linearLayout, linearLayout2, relativeLayout, textView, textView2, textView3, textView4, textView5, textView6);
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
