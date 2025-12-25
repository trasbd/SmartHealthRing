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
public final class LayoutDeviceInfoBinding implements ViewBinding {
    private final ConstraintLayout rootView;
    public final TextView tvContent;
    public final TextView tvTag;

    private LayoutDeviceInfoBinding(ConstraintLayout rootView, TextView tvContent, TextView tvTag) {
        this.rootView = rootView;
        this.tvContent = tvContent;
        this.tvTag = tvTag;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static LayoutDeviceInfoBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static LayoutDeviceInfoBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.layout_device_info, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutDeviceInfoBinding bind(View rootView) {
        int i2 = R.id.tvContent;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
        if (textView != null) {
            i2 = R.id.tvTag;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
            if (textView2 != null) {
                return new LayoutDeviceInfoBinding((ConstraintLayout) rootView, textView, textView2);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
