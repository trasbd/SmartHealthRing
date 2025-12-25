package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.amap.api.maps.MapView;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;

/* loaded from: classes4.dex */
public final class ActivityFenceRangeBinding implements ViewBinding {
    public final Button btnNav;
    public final MapView map;
    public final NavigationBar navigationbar;
    private final LinearLayout rootView;

    private ActivityFenceRangeBinding(LinearLayout rootView, Button btnNav, MapView map, NavigationBar navigationbar) {
        this.rootView = rootView;
        this.btnNav = btnNav;
        this.map = map;
        this.navigationbar = navigationbar;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityFenceRangeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityFenceRangeBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_fence_range, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityFenceRangeBinding bind(View rootView) {
        int i2 = R.id.btn_nav;
        Button button = (Button) ViewBindings.findChildViewById(rootView, i2);
        if (button != null) {
            i2 = R.id.map;
            MapView mapView = (MapView) ViewBindings.findChildViewById(rootView, i2);
            if (mapView != null) {
                i2 = R.id.navigationbar;
                NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
                if (navigationBar != null) {
                    return new ActivityFenceRangeBinding((LinearLayout) rootView, button, mapView, navigationBar);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
