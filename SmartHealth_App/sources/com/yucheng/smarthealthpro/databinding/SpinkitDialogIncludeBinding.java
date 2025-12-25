package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.github.ybq.android.spinkit.SpinKitView;
import com.yucheng.smarthealthpro.R;

/* loaded from: classes4.dex */
public final class SpinkitDialogIncludeBinding implements ViewBinding {
    private final RelativeLayout rootView;
    public final RelativeLayout rvDialog;
    public final SpinKitView spinKit;

    private SpinkitDialogIncludeBinding(RelativeLayout rootView, RelativeLayout rvDialog, SpinKitView spinKit) {
        this.rootView = rootView;
        this.rvDialog = rvDialog;
        this.spinKit = spinKit;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static SpinkitDialogIncludeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static SpinkitDialogIncludeBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.spinkit_dialog_include, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static SpinkitDialogIncludeBinding bind(View rootView) {
        RelativeLayout relativeLayout = (RelativeLayout) rootView;
        int i2 = R.id.spin_kit;
        SpinKitView spinKitView = (SpinKitView) ViewBindings.findChildViewById(rootView, i2);
        if (spinKitView != null) {
            return new SpinkitDialogIncludeBinding(relativeLayout, relativeLayout, spinKitView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
