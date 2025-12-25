package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;

/* loaded from: classes4.dex */
public final class FragmentOneBloodBinding implements ViewBinding {
    private final LinearLayout rootView;
    public final TextView tvMaxBlood;
    public final TextView tvMaxBloodData;
    public final TextView tvMinBlood;
    public final TextView tvMinBloodData;
    public final TextView tvRecentlyBlood;

    private FragmentOneBloodBinding(LinearLayout rootView, TextView tvMaxBlood, TextView tvMaxBloodData, TextView tvMinBlood, TextView tvMinBloodData, TextView tvRecentlyBlood) {
        this.rootView = rootView;
        this.tvMaxBlood = tvMaxBlood;
        this.tvMaxBloodData = tvMaxBloodData;
        this.tvMinBlood = tvMinBlood;
        this.tvMinBloodData = tvMinBloodData;
        this.tvRecentlyBlood = tvRecentlyBlood;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static FragmentOneBloodBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentOneBloodBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_one_blood, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentOneBloodBinding bind(View rootView) {
        int i2 = R.id.tv_max_blood;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
        if (textView != null) {
            i2 = R.id.tv_max_blood_data;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
            if (textView2 != null) {
                i2 = R.id.tv_min_blood;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                if (textView3 != null) {
                    i2 = R.id.tv_min_blood_data;
                    TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                    if (textView4 != null) {
                        i2 = R.id.tv_recently_blood;
                        TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                        if (textView5 != null) {
                            return new FragmentOneBloodBinding((LinearLayout) rootView, textView, textView2, textView3, textView4, textView5);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
