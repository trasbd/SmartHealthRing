package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import com.yucheng.smarthealthpro.view.CircleImageView;

/* loaded from: classes4.dex */
public final class ActivityRankingBinding implements ViewBinding {
    public final CircleImageView ivHeadOne;
    public final CircleImageView ivHeadPortrait;
    public final ImageView likeButton;
    public final LinearLayout llEndLikeNumber;
    public final NavigationBar navigationbar;
    public final RecyclerView recycleRanking;
    public final RelativeLayout rlRankingMe;
    private final FrameLayout rootView;
    public final SpinkitDialogIncludeBinding rvDialog;
    public final SmartRefreshLayout srlRanking;
    public final TextView tvLikeNumber;
    public final TextView tvName;
    public final TextView tvRanking;
    public final TextView tvStepNumber;

    private ActivityRankingBinding(FrameLayout rootView, CircleImageView ivHeadOne, CircleImageView ivHeadPortrait, ImageView likeButton, LinearLayout llEndLikeNumber, NavigationBar navigationbar, RecyclerView recycleRanking, RelativeLayout rlRankingMe, SpinkitDialogIncludeBinding rvDialog, SmartRefreshLayout srlRanking, TextView tvLikeNumber, TextView tvName, TextView tvRanking, TextView tvStepNumber) {
        this.rootView = rootView;
        this.ivHeadOne = ivHeadOne;
        this.ivHeadPortrait = ivHeadPortrait;
        this.likeButton = likeButton;
        this.llEndLikeNumber = llEndLikeNumber;
        this.navigationbar = navigationbar;
        this.recycleRanking = recycleRanking;
        this.rlRankingMe = rlRankingMe;
        this.rvDialog = rvDialog;
        this.srlRanking = srlRanking;
        this.tvLikeNumber = tvLikeNumber;
        this.tvName = tvName;
        this.tvRanking = tvRanking;
        this.tvStepNumber = tvStepNumber;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static ActivityRankingBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityRankingBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_ranking, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityRankingBinding bind(View rootView) {
        View viewFindChildViewById;
        int i2 = R.id.iv_head_one;
        CircleImageView circleImageView = (CircleImageView) ViewBindings.findChildViewById(rootView, i2);
        if (circleImageView != null) {
            i2 = R.id.iv_head_portrait;
            CircleImageView circleImageView2 = (CircleImageView) ViewBindings.findChildViewById(rootView, i2);
            if (circleImageView2 != null) {
                i2 = R.id.like_button;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                if (imageView != null) {
                    i2 = R.id.ll_end_like_number;
                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                    if (linearLayout != null) {
                        i2 = R.id.navigationbar;
                        NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
                        if (navigationBar != null) {
                            i2 = R.id.recycle_ranking;
                            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, i2);
                            if (recyclerView != null) {
                                i2 = R.id.rl_ranking_me;
                                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                                if (relativeLayout != null && (viewFindChildViewById = ViewBindings.findChildViewById(rootView, (i2 = R.id.rv_dialog))) != null) {
                                    SpinkitDialogIncludeBinding spinkitDialogIncludeBindingBind = SpinkitDialogIncludeBinding.bind(viewFindChildViewById);
                                    i2 = R.id.srl_ranking;
                                    SmartRefreshLayout smartRefreshLayout = (SmartRefreshLayout) ViewBindings.findChildViewById(rootView, i2);
                                    if (smartRefreshLayout != null) {
                                        i2 = R.id.tv_like_number;
                                        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                        if (textView != null) {
                                            i2 = R.id.tv_name;
                                            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                            if (textView2 != null) {
                                                i2 = R.id.tv_ranking;
                                                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                if (textView3 != null) {
                                                    i2 = R.id.tv_step_number;
                                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                    if (textView4 != null) {
                                                        return new ActivityRankingBinding((FrameLayout) rootView, circleImageView, circleImageView2, imageView, linearLayout, navigationBar, recyclerView, relativeLayout, spinkitDialogIncludeBindingBind, smartRefreshLayout, textView, textView2, textView3, textView4);
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
