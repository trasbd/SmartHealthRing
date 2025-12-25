package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;

/* loaded from: classes4.dex */
public final class FragmentOneEcgBinding implements ViewBinding {
    public final RelativeLayout carRelativelayout;
    private final LinearLayout rootView;
    public final TextView tvHrv;

    private FragmentOneEcgBinding(LinearLayout rootView, RelativeLayout carRelativelayout, TextView tvHrv) {
        this.rootView = rootView;
        this.carRelativelayout = carRelativelayout;
        this.tvHrv = tvHrv;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static FragmentOneEcgBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentOneEcgBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_one_ecg, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentOneEcgBinding bind(View rootView) {
        int i2 = R.id.car_relativelayout;
        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
        if (relativeLayout != null) {
            i2 = R.id.tv_hrv;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
            if (textView != null) {
                return new FragmentOneEcgBinding((LinearLayout) rootView, relativeLayout, textView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
