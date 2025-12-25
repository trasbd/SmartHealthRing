package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import cn.bingoogolapple.qrcode.zxing.ZXingView;
import com.yucheng.smarthealthpro.R;

/* loaded from: classes4.dex */
public final class ActivityScanQrcodeBinding implements ViewBinding {
    private final ConstraintLayout rootView;
    public final ZXingView zxingview;

    private ActivityScanQrcodeBinding(ConstraintLayout rootView, ZXingView zxingview) {
        this.rootView = rootView;
        this.zxingview = zxingview;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityScanQrcodeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityScanQrcodeBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_scan_qrcode, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityScanQrcodeBinding bind(View rootView) {
        int i2 = R.id.zxingview;
        ZXingView zXingView = (ZXingView) ViewBindings.findChildViewById(rootView, i2);
        if (zXingView != null) {
            return new ActivityScanQrcodeBinding((ConstraintLayout) rootView, zXingView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
