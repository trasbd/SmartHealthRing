package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.care.zxing.view.ViewfinderView;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;

/* loaded from: classes4.dex */
public final class ActivityScannerBinding implements ViewBinding {
    public final NavigationBar navigationbar;
    private final LinearLayout rootView;
    public final SurfaceView scannerView;
    public final ImageView txtOpenLight;
    public final ImageView txtOpenPhotos;
    public final ViewfinderView viewfinderContent;

    private ActivityScannerBinding(LinearLayout rootView, NavigationBar navigationbar, SurfaceView scannerView, ImageView txtOpenLight, ImageView txtOpenPhotos, ViewfinderView viewfinderContent) {
        this.rootView = rootView;
        this.navigationbar = navigationbar;
        this.scannerView = scannerView;
        this.txtOpenLight = txtOpenLight;
        this.txtOpenPhotos = txtOpenPhotos;
        this.viewfinderContent = viewfinderContent;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityScannerBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityScannerBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_scanner, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityScannerBinding bind(View rootView) {
        int i2 = R.id.navigationbar;
        NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
        if (navigationBar != null) {
            i2 = R.id.scanner_view;
            SurfaceView surfaceView = (SurfaceView) ViewBindings.findChildViewById(rootView, i2);
            if (surfaceView != null) {
                i2 = R.id.txt_open_light;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                if (imageView != null) {
                    i2 = R.id.txt_open_photos;
                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                    if (imageView2 != null) {
                        i2 = R.id.viewfinder_content;
                        ViewfinderView viewfinderView = (ViewfinderView) ViewBindings.findChildViewById(rootView, i2);
                        if (viewfinderView != null) {
                            return new ActivityScannerBinding((LinearLayout) rootView, navigationBar, surfaceView, imageView, imageView2, viewfinderView);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
