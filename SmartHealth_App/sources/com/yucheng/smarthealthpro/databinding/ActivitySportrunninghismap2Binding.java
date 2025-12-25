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
import com.amap.api.maps.MapView;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.customchart.charts.LineChart;

/* loaded from: classes4.dex */
public final class ActivitySportrunninghismap2Binding implements ViewBinding {
    public final ImageView ivBack;
    public final ImageView ivBg;
    public final ImageView ivLocation;
    public final ImageView ivSportImg;
    public final LineChart lineChartDay;
    public final LinearLayout llFourthly;
    public final LinearLayout llSportData;
    public final LinearLayout llStep;
    public final LinearLayout llThirdly;
    public final MapView map;
    public final RelativeLayout rlSportHisMap;
    private final RelativeLayout rootView;
    public final TextView tvDistance;
    public final TextView tvFirstValue;
    public final TextView tvFourthlyValue;
    public final TextView tvKeepTime;
    public final TextView tvMotorPattern;
    public final TextView tvSecondValue;
    public final TextView tvStepValue;
    public final TextView tvThirdlyValue;
    public final TextView tvTime;
    public final TextView tvUnit;
    public final View viewBack;

    private ActivitySportrunninghismap2Binding(RelativeLayout rootView, ImageView ivBack, ImageView ivBg, ImageView ivLocation, ImageView ivSportImg, LineChart lineChartDay, LinearLayout llFourthly, LinearLayout llSportData, LinearLayout llStep, LinearLayout llThirdly, MapView map, RelativeLayout rlSportHisMap, TextView tvDistance, TextView tvFirstValue, TextView tvFourthlyValue, TextView tvKeepTime, TextView tvMotorPattern, TextView tvSecondValue, TextView tvStepValue, TextView tvThirdlyValue, TextView tvTime, TextView tvUnit, View viewBack) {
        this.rootView = rootView;
        this.ivBack = ivBack;
        this.ivBg = ivBg;
        this.ivLocation = ivLocation;
        this.ivSportImg = ivSportImg;
        this.lineChartDay = lineChartDay;
        this.llFourthly = llFourthly;
        this.llSportData = llSportData;
        this.llStep = llStep;
        this.llThirdly = llThirdly;
        this.map = map;
        this.rlSportHisMap = rlSportHisMap;
        this.tvDistance = tvDistance;
        this.tvFirstValue = tvFirstValue;
        this.tvFourthlyValue = tvFourthlyValue;
        this.tvKeepTime = tvKeepTime;
        this.tvMotorPattern = tvMotorPattern;
        this.tvSecondValue = tvSecondValue;
        this.tvStepValue = tvStepValue;
        this.tvThirdlyValue = tvThirdlyValue;
        this.tvTime = tvTime;
        this.tvUnit = tvUnit;
        this.viewBack = viewBack;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ActivitySportrunninghismap2Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivitySportrunninghismap2Binding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_sportrunninghismap2, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivitySportrunninghismap2Binding bind(View rootView) {
        View viewFindChildViewById;
        int i2 = R.id.iv_back;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i2);
        if (imageView != null) {
            i2 = R.id.iv_bg;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
            if (imageView2 != null) {
                i2 = R.id.iv_location;
                ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                if (imageView3 != null) {
                    i2 = R.id.iv_sport_img;
                    ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                    if (imageView4 != null) {
                        i2 = R.id.line_chart_day;
                        LineChart lineChart = (LineChart) ViewBindings.findChildViewById(rootView, i2);
                        if (lineChart != null) {
                            i2 = R.id.ll_fourthly;
                            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                            if (linearLayout != null) {
                                i2 = R.id.ll_sport_data;
                                LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                                if (linearLayout2 != null) {
                                    i2 = R.id.llStep;
                                    LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                                    if (linearLayout3 != null) {
                                        i2 = R.id.ll_thirdly;
                                        LinearLayout linearLayout4 = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                                        if (linearLayout4 != null) {
                                            i2 = R.id.map;
                                            MapView mapView = (MapView) ViewBindings.findChildViewById(rootView, i2);
                                            if (mapView != null) {
                                                RelativeLayout relativeLayout = (RelativeLayout) rootView;
                                                i2 = R.id.tv_distance;
                                                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                if (textView != null) {
                                                    i2 = R.id.tv_first_value;
                                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                    if (textView2 != null) {
                                                        i2 = R.id.tv_fourthly_value;
                                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                        if (textView3 != null) {
                                                            i2 = R.id.tv_keep_time;
                                                            TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                            if (textView4 != null) {
                                                                i2 = R.id.tv_motorPattern;
                                                                TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                if (textView5 != null) {
                                                                    i2 = R.id.tv_second_value;
                                                                    TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                    if (textView6 != null) {
                                                                        i2 = R.id.tv_step_value;
                                                                        TextView textView7 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                        if (textView7 != null) {
                                                                            i2 = R.id.tv_thirdly_value;
                                                                            TextView textView8 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                            if (textView8 != null) {
                                                                                i2 = R.id.tv_time;
                                                                                TextView textView9 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                if (textView9 != null) {
                                                                                    i2 = R.id.tv_unit;
                                                                                    TextView textView10 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                    if (textView10 != null && (viewFindChildViewById = ViewBindings.findChildViewById(rootView, (i2 = R.id.view_back))) != null) {
                                                                                        return new ActivitySportrunninghismap2Binding(relativeLayout, imageView, imageView2, imageView3, imageView4, lineChart, linearLayout, linearLayout2, linearLayout3, linearLayout4, mapView, relativeLayout, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10, viewFindChildViewById);
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
