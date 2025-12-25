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
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;

/* loaded from: classes4.dex */
public final class ActivityAiDiagnosisBinding implements ViewBinding {
    public final RelativeLayout aaaax;
    public final LinearLayout aiDiagnosisDetailLy;
    public final TextView aiDiagnosisDetailTitleTv;
    public final TextView aiDiagnosisInDoubt;
    public final RecyclerView dataRecyclerView;
    public final ImageView ivAiDiagnosisEcg;
    public final ImageView ivAiTitle;
    public final NavigationBar navigationbar;
    public final RecyclerView recycleView;
    private final RelativeLayout rootView;
    public final TextView tvAge;
    public final TextView tvBool;
    public final TextView tvDetails;
    public final TextView tvDiagnosisResult;
    public final TextView tvHeart;
    public final TextView tvSex;

    private ActivityAiDiagnosisBinding(RelativeLayout rootView, RelativeLayout aaaax, LinearLayout aiDiagnosisDetailLy, TextView aiDiagnosisDetailTitleTv, TextView aiDiagnosisInDoubt, RecyclerView dataRecyclerView, ImageView ivAiDiagnosisEcg, ImageView ivAiTitle, NavigationBar navigationbar, RecyclerView recycleView, TextView tvAge, TextView tvBool, TextView tvDetails, TextView tvDiagnosisResult, TextView tvHeart, TextView tvSex) {
        this.rootView = rootView;
        this.aaaax = aaaax;
        this.aiDiagnosisDetailLy = aiDiagnosisDetailLy;
        this.aiDiagnosisDetailTitleTv = aiDiagnosisDetailTitleTv;
        this.aiDiagnosisInDoubt = aiDiagnosisInDoubt;
        this.dataRecyclerView = dataRecyclerView;
        this.ivAiDiagnosisEcg = ivAiDiagnosisEcg;
        this.ivAiTitle = ivAiTitle;
        this.navigationbar = navigationbar;
        this.recycleView = recycleView;
        this.tvAge = tvAge;
        this.tvBool = tvBool;
        this.tvDetails = tvDetails;
        this.tvDiagnosisResult = tvDiagnosisResult;
        this.tvHeart = tvHeart;
        this.tvSex = tvSex;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ActivityAiDiagnosisBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityAiDiagnosisBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_ai_diagnosis, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityAiDiagnosisBinding bind(View rootView) {
        int i2 = R.id.aaaax;
        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
        if (relativeLayout != null) {
            i2 = R.id.ai_diagnosis_detail_ly;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
            if (linearLayout != null) {
                i2 = R.id.ai_diagnosis_detail_title_tv;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                if (textView != null) {
                    i2 = R.id.ai_diagnosis_in_doubt;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                    if (textView2 != null) {
                        i2 = R.id.dataRecyclerView;
                        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, i2);
                        if (recyclerView != null) {
                            i2 = R.id.iv_ai_diagnosis_ecg;
                            ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                            if (imageView != null) {
                                i2 = R.id.iv_ai_title;
                                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                                if (imageView2 != null) {
                                    i2 = R.id.navigationbar;
                                    NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
                                    if (navigationBar != null) {
                                        i2 = R.id.recycle_view;
                                        RecyclerView recyclerView2 = (RecyclerView) ViewBindings.findChildViewById(rootView, i2);
                                        if (recyclerView2 != null) {
                                            i2 = R.id.tv_age;
                                            TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                            if (textView3 != null) {
                                                i2 = R.id.tv_bool;
                                                TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                if (textView4 != null) {
                                                    i2 = R.id.tv_details;
                                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                    if (textView5 != null) {
                                                        i2 = R.id.tv_diagnosis_result;
                                                        TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                        if (textView6 != null) {
                                                            i2 = R.id.tv_heart;
                                                            TextView textView7 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                            if (textView7 != null) {
                                                                i2 = R.id.tv_sex;
                                                                TextView textView8 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                if (textView8 != null) {
                                                                    return new ActivityAiDiagnosisBinding((RelativeLayout) rootView, relativeLayout, linearLayout, textView, textView2, recyclerView, imageView, imageView2, navigationBar, recyclerView2, textView3, textView4, textView5, textView6, textView7, textView8);
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
