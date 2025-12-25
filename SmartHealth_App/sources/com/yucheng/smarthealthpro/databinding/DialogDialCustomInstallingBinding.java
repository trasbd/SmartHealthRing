package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;

/* loaded from: classes4.dex */
public final class DialogDialCustomInstallingBinding implements ViewBinding {
    public final ProgressBar dialItemProgress;
    public final TextView dialItemProgressTv;
    public final LinearLayout dialogView;
    private final LinearLayout rootView;

    private DialogDialCustomInstallingBinding(LinearLayout rootView, ProgressBar dialItemProgress, TextView dialItemProgressTv, LinearLayout dialogView) {
        this.rootView = rootView;
        this.dialItemProgress = dialItemProgress;
        this.dialItemProgressTv = dialItemProgressTv;
        this.dialogView = dialogView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static DialogDialCustomInstallingBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static DialogDialCustomInstallingBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.dialog_dial_custom_installing, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DialogDialCustomInstallingBinding bind(View rootView) {
        int i2 = R.id.dial_item_progress;
        ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(rootView, i2);
        if (progressBar != null) {
            i2 = R.id.dial_item_progress_tv;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
            if (textView != null) {
                LinearLayout linearLayout = (LinearLayout) rootView;
                return new DialogDialCustomInstallingBinding(linearLayout, progressBar, textView, linearLayout);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
