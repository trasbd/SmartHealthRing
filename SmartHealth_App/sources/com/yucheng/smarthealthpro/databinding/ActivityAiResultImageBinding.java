package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import com.yucheng.smarthealthpro.home.activity.ecg.photo.PhotoView;

/* loaded from: classes4.dex */
public final class ActivityAiResultImageBinding implements ViewBinding {
    public final PhotoView aiResultIv;
    public final NavigationBar navigationbar;
    private final LinearLayout rootView;

    private ActivityAiResultImageBinding(LinearLayout rootView, PhotoView aiResultIv, NavigationBar navigationbar) {
        this.rootView = rootView;
        this.aiResultIv = aiResultIv;
        this.navigationbar = navigationbar;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityAiResultImageBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityAiResultImageBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_ai_result_image, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityAiResultImageBinding bind(View rootView) {
        int i2 = R.id.ai_result_iv;
        PhotoView photoView = (PhotoView) ViewBindings.findChildViewById(rootView, i2);
        if (photoView != null) {
            i2 = R.id.navigationbar;
            NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
            if (navigationBar != null) {
                return new ActivityAiResultImageBinding((LinearLayout) rootView, photoView, navigationBar);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
