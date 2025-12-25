package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;

/* loaded from: classes4.dex */
public final class ItemSosStepBinding implements ViewBinding {
    public final View line;
    private final ConstraintLayout rootView;
    public final TextView tvDesc;
    public final TextView tvSeq;
    public final TextView tvTitle;

    private ItemSosStepBinding(ConstraintLayout rootView, View line, TextView tvDesc, TextView tvSeq, TextView tvTitle) {
        this.rootView = rootView;
        this.line = line;
        this.tvDesc = tvDesc;
        this.tvSeq = tvSeq;
        this.tvTitle = tvTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ItemSosStepBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemSosStepBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.item_sos_step, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemSosStepBinding bind(View rootView) {
        int i2 = R.id.line;
        View viewFindChildViewById = ViewBindings.findChildViewById(rootView, i2);
        if (viewFindChildViewById != null) {
            i2 = R.id.tvDesc;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
            if (textView != null) {
                i2 = R.id.tvSeq;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                if (textView2 != null) {
                    i2 = R.id.tvTitle;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                    if (textView3 != null) {
                        return new ItemSosStepBinding((ConstraintLayout) rootView, viewFindChildViewById, textView, textView2, textView3);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
