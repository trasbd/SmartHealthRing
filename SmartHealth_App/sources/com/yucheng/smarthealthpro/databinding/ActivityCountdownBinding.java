package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.sport.view.TimeDownView;

/* loaded from: classes4.dex */
public final class ActivityCountdownBinding implements ViewBinding {
    private final LinearLayout rootView;
    public final TimeDownView tv;

    private ActivityCountdownBinding(LinearLayout rootView, TimeDownView tv) {
        this.rootView = rootView;
        this.tv = tv;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityCountdownBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityCountdownBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_countdown, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityCountdownBinding bind(View rootView) {
        int i2 = R.id.tv;
        TimeDownView timeDownView = (TimeDownView) ViewBindings.findChildViewById(rootView, i2);
        if (timeDownView != null) {
            return new ActivityCountdownBinding((LinearLayout) rootView, timeDownView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
