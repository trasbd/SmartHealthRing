package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;

/* loaded from: classes4.dex */
public final class ActivityAiDiagnosisNewBinding implements ViewBinding {
    public final RelativeLayout aaaax;
    public final TextView aiDiagnosisDetailTitleTv;
    public final TextView aiDiagnosisInDoubt;
    public final RecyclerView dataRecyclerView;
    public final ItemEcgDiagnoseHeaderBinding includeDiagnoseHeader;
    public final ImageView ivAiDiagnosisEcg;
    public final NavigationBar navigationbar;
    public final RecyclerView recycleView;
    private final RelativeLayout rootView;
    public final TextView tvDiagnosisResult;

    private ActivityAiDiagnosisNewBinding(RelativeLayout rootView, RelativeLayout aaaax, TextView aiDiagnosisDetailTitleTv, TextView aiDiagnosisInDoubt, RecyclerView dataRecyclerView, ItemEcgDiagnoseHeaderBinding includeDiagnoseHeader, ImageView ivAiDiagnosisEcg, NavigationBar navigationbar, RecyclerView recycleView, TextView tvDiagnosisResult) {
        this.rootView = rootView;
        this.aaaax = aaaax;
        this.aiDiagnosisDetailTitleTv = aiDiagnosisDetailTitleTv;
        this.aiDiagnosisInDoubt = aiDiagnosisInDoubt;
        this.dataRecyclerView = dataRecyclerView;
        this.includeDiagnoseHeader = includeDiagnoseHeader;
        this.ivAiDiagnosisEcg = ivAiDiagnosisEcg;
        this.navigationbar = navigationbar;
        this.recycleView = recycleView;
        this.tvDiagnosisResult = tvDiagnosisResult;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ActivityAiDiagnosisNewBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityAiDiagnosisNewBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_ai_diagnosis_new, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityAiDiagnosisNewBinding bind(View rootView) {
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
                    i2 = R.id.dataRecyclerView;
                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, i2);
                    if (recyclerView != null && (viewFindChildViewById = ViewBindings.findChildViewById(rootView, (i2 = R.id.include_diagnose_header))) != null) {
                        ItemEcgDiagnoseHeaderBinding itemEcgDiagnoseHeaderBindingBind = ItemEcgDiagnoseHeaderBinding.bind(viewFindChildViewById);
                        i2 = R.id.iv_ai_diagnosis_ecg;
                        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                        if (imageView != null) {
                            i2 = R.id.navigationbar;
                            NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
                            if (navigationBar != null) {
                                i2 = R.id.recycle_view;
                                RecyclerView recyclerView2 = (RecyclerView) ViewBindings.findChildViewById(rootView, i2);
                                if (recyclerView2 != null) {
                                    i2 = R.id.tv_diagnosis_result;
                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                    if (textView3 != null) {
                                        return new ActivityAiDiagnosisNewBinding((RelativeLayout) rootView, relativeLayout, textView, textView2, recyclerView, itemEcgDiagnoseHeaderBindingBind, imageView, navigationBar, recyclerView2, textView3);
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
