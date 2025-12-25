package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.perfect.ui.ClipViewLayout;

/* loaded from: classes4.dex */
public final class ActivityClipImageBinding implements ViewBinding {
    public final TextView btOk;
    public final TextView btnCancel;
    public final ClipViewLayout clipViewLayout1;
    public final ClipViewLayout clipViewLayout2;
    public final ImageView ivBack;
    private final LinearLayout rootView;

    private ActivityClipImageBinding(LinearLayout rootView, TextView btOk, TextView btnCancel, ClipViewLayout clipViewLayout1, ClipViewLayout clipViewLayout2, ImageView ivBack) {
        this.rootView = rootView;
        this.btOk = btOk;
        this.btnCancel = btnCancel;
        this.clipViewLayout1 = clipViewLayout1;
        this.clipViewLayout2 = clipViewLayout2;
        this.ivBack = ivBack;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityClipImageBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityClipImageBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_clip_image, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityClipImageBinding bind(View rootView) {
        int i2 = R.id.bt_ok;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
        if (textView != null) {
            i2 = R.id.btn_cancel;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
            if (textView2 != null) {
                i2 = R.id.clipViewLayout1;
                ClipViewLayout clipViewLayout = (ClipViewLayout) ViewBindings.findChildViewById(rootView, i2);
                if (clipViewLayout != null) {
                    i2 = R.id.clipViewLayout2;
                    ClipViewLayout clipViewLayout2 = (ClipViewLayout) ViewBindings.findChildViewById(rootView, i2);
                    if (clipViewLayout2 != null) {
                        i2 = R.id.iv_back;
                        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                        if (imageView != null) {
                            return new ActivityClipImageBinding((LinearLayout) rootView, textView, textView2, clipViewLayout, clipViewLayout2, imageView);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
