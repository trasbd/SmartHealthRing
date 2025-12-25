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
public final class ActivityLocationBinding implements ViewBinding {
    public final TextView locationContent1;
    public final TextView locationContent2;
    public final TextView locationNoThanks;
    public final TextView locationTurnOn;
    private final LinearLayout rootView;

    private ActivityLocationBinding(LinearLayout rootView, TextView locationContent1, TextView locationContent2, TextView locationNoThanks, TextView locationTurnOn) {
        this.rootView = rootView;
        this.locationContent1 = locationContent1;
        this.locationContent2 = locationContent2;
        this.locationNoThanks = locationNoThanks;
        this.locationTurnOn = locationTurnOn;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityLocationBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityLocationBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_location, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityLocationBinding bind(View rootView) {
        int i2 = R.id.location_content1;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
        if (textView != null) {
            i2 = R.id.location_content2;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
            if (textView2 != null) {
                i2 = R.id.location_no_thanks;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                if (textView3 != null) {
                    i2 = R.id.location_turn_on;
                    TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                    if (textView4 != null) {
                        return new ActivityLocationBinding((LinearLayout) rootView, textView, textView2, textView3, textView4);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
