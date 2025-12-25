package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;

/* loaded from: classes4.dex */
public final class ItemHealthAssistanceFunctionBinding implements ViewBinding {
    private final RelativeLayout rootView;
    public final Switch switchFunction;
    public final TextView tvTitle;

    private ItemHealthAssistanceFunctionBinding(RelativeLayout rootView, Switch switchFunction, TextView tvTitle) {
        this.rootView = rootView;
        this.switchFunction = switchFunction;
        this.tvTitle = tvTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ItemHealthAssistanceFunctionBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemHealthAssistanceFunctionBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.item_health_assistance_function, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemHealthAssistanceFunctionBinding bind(View rootView) {
        int i2 = R.id.switch_function;
        Switch r1 = (Switch) ViewBindings.findChildViewById(rootView, i2);
        if (r1 != null) {
            i2 = R.id.tv_title;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
            if (textView != null) {
                return new ItemHealthAssistanceFunctionBinding((RelativeLayout) rootView, r1, textView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
