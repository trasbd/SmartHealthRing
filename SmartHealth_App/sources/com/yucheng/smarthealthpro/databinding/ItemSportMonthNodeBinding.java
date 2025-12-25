package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;
import com.yucheng.smarthealthpro.R;

/* loaded from: classes4.dex */
public final class ItemSportMonthNodeBinding implements ViewBinding {
    public final ImageView ivSubordinate;
    public final LinearLayout llSportMonthNode;
    private final LinearLayout rootView;
    public final SwipeRecyclerView rvRecord;
    public final TextView tvMonth;

    private ItemSportMonthNodeBinding(LinearLayout rootView, ImageView ivSubordinate, LinearLayout llSportMonthNode, SwipeRecyclerView rvRecord, TextView tvMonth) {
        this.rootView = rootView;
        this.ivSubordinate = ivSubordinate;
        this.llSportMonthNode = llSportMonthNode;
        this.rvRecord = rvRecord;
        this.tvMonth = tvMonth;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ItemSportMonthNodeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemSportMonthNodeBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.item_sport_month_node, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemSportMonthNodeBinding bind(View rootView) {
        int i2 = R.id.iv_subordinate;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i2);
        if (imageView != null) {
            LinearLayout linearLayout = (LinearLayout) rootView;
            i2 = R.id.rv_record;
            SwipeRecyclerView swipeRecyclerView = (SwipeRecyclerView) ViewBindings.findChildViewById(rootView, i2);
            if (swipeRecyclerView != null) {
                i2 = R.id.tv_month;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                if (textView != null) {
                    return new ItemSportMonthNodeBinding(linearLayout, imageView, linearLayout, swipeRecyclerView, textView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
