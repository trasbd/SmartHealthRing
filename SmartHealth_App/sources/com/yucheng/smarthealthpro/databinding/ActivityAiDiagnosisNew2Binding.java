package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import com.yucheng.smarthealthpro.home.view.CardiographView;

/* loaded from: classes4.dex */
public final class ActivityAiDiagnosisNew2Binding implements ViewBinding {
    public final RelativeLayout aaaax;
    public final TextView aiDiagnosisDetailTitleTv;
    public final TextView aiDiagnosisInDoubt;
    public final CardiographView cardiographView;
    public final RecyclerView dataRecyclerView;
    public final HorizontalScrollView hsvEcg;
    public final ItemEcgDiagnoseHeaderBinding includeDiagnoseHeader;
    public final ImageView ivAiDiagnosisEcg;
    public final ImageView ivPlay;
    public final LinearLayoutCompat llAiCapture;
    public final NavigationBar navigationbar;
    public final RecyclerView recycleView;
    public final RelativeLayout rlEcgMeasureView;
    private final RelativeLayout rootView;
    public final SeekBar sbProgress;
    public final ScrollView svEcg;
    public final TextView tvDetail2;
    public final TextView tvDetailTag2;
    public final TextView tvDiagnosis2;
    public final TextView tvDiagnosisResult;

    private ActivityAiDiagnosisNew2Binding(RelativeLayout rootView, RelativeLayout aaaax, TextView aiDiagnosisDetailTitleTv, TextView aiDiagnosisInDoubt, CardiographView cardiographView, RecyclerView dataRecyclerView, HorizontalScrollView hsvEcg, ItemEcgDiagnoseHeaderBinding includeDiagnoseHeader, ImageView ivAiDiagnosisEcg, ImageView ivPlay, LinearLayoutCompat llAiCapture, NavigationBar navigationbar, RecyclerView recycleView, RelativeLayout rlEcgMeasureView, SeekBar sbProgress, ScrollView svEcg, TextView tvDetail2, TextView tvDetailTag2, TextView tvDiagnosis2, TextView tvDiagnosisResult) {
        this.rootView = rootView;
        this.aaaax = aaaax;
        this.aiDiagnosisDetailTitleTv = aiDiagnosisDetailTitleTv;
        this.aiDiagnosisInDoubt = aiDiagnosisInDoubt;
        this.cardiographView = cardiographView;
        this.dataRecyclerView = dataRecyclerView;
        this.hsvEcg = hsvEcg;
        this.includeDiagnoseHeader = includeDiagnoseHeader;
        this.ivAiDiagnosisEcg = ivAiDiagnosisEcg;
        this.ivPlay = ivPlay;
        this.llAiCapture = llAiCapture;
        this.navigationbar = navigationbar;
        this.recycleView = recycleView;
        this.rlEcgMeasureView = rlEcgMeasureView;
        this.sbProgress = sbProgress;
        this.svEcg = svEcg;
        this.tvDetail2 = tvDetail2;
        this.tvDetailTag2 = tvDetailTag2;
        this.tvDiagnosis2 = tvDiagnosis2;
        this.tvDiagnosisResult = tvDiagnosisResult;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ActivityAiDiagnosisNew2Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityAiDiagnosisNew2Binding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_ai_diagnosis_new2, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityAiDiagnosisNew2Binding bind(View rootView) {
        View viewFindChildViewById;
        int i2 = R.id.aaaax;
        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
        if (relativeLayout != null) {
            i2 = R.id.ai_diagnosis_detail_title_tv;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
            if (textView != null) {
                i2 = R.id.ai_diagnosis_in_doubt;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                if (textView2 != null) {
                    i2 = R.id.cardiographView;
                    CardiographView cardiographView = (CardiographView) ViewBindings.findChildViewById(rootView, i2);
                    if (cardiographView != null) {
                        i2 = R.id.dataRecyclerView;
                        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, i2);
                        if (recyclerView != null) {
                            i2 = R.id.hsv_ecg;
                            HorizontalScrollView horizontalScrollView = (HorizontalScrollView) ViewBindings.findChildViewById(rootView, i2);
                            if (horizontalScrollView != null && (viewFindChildViewById = ViewBindings.findChildViewById(rootView, (i2 = R.id.include_diagnose_header))) != null) {
                                ItemEcgDiagnoseHeaderBinding itemEcgDiagnoseHeaderBindingBind = ItemEcgDiagnoseHeaderBinding.bind(viewFindChildViewById);
                                i2 = R.id.iv_ai_diagnosis_ecg;
                                ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                                if (imageView != null) {
                                    i2 = R.id.iv_play;
                                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                                    if (imageView2 != null) {
                                        i2 = R.id.ll_ai_capture;
                                        LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) ViewBindings.findChildViewById(rootView, i2);
                                        if (linearLayoutCompat != null) {
                                            i2 = R.id.navigationbar;
                                            NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
                                            if (navigationBar != null) {
                                                i2 = R.id.recycle_view;
                                                RecyclerView recyclerView2 = (RecyclerView) ViewBindings.findChildViewById(rootView, i2);
                                                if (recyclerView2 != null) {
                                                    i2 = R.id.rl_ecg_measure_view;
                                                    RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                                                    if (relativeLayout2 != null) {
                                                        i2 = R.id.sb_progress;
                                                        SeekBar seekBar = (SeekBar) ViewBindings.findChildViewById(rootView, i2);
                                                        if (seekBar != null) {
                                                            i2 = R.id.sv_ecg;
                                                            ScrollView scrollView = (ScrollView) ViewBindings.findChildViewById(rootView, i2);
                                                            if (scrollView != null) {
                                                                i2 = R.id.tvDetail2;
                                                                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                if (textView3 != null) {
                                                                    i2 = R.id.tvDetailTag2;
                                                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                    if (textView4 != null) {
                                                                        i2 = R.id.tvDiagnosis2;
                                                                        TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                        if (textView5 != null) {
                                                                            i2 = R.id.tv_diagnosis_result;
                                                                            TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                            if (textView6 != null) {
                                                                                return new ActivityAiDiagnosisNew2Binding((RelativeLayout) rootView, relativeLayout, textView, textView2, cardiographView, recyclerView, horizontalScrollView, itemEcgDiagnoseHeaderBindingBind, imageView, imageView2, linearLayoutCompat, navigationBar, recyclerView2, relativeLayout2, seekBar, scrollView, textView3, textView4, textView5, textView6);
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
