package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import com.yucheng.smarthealthpro.view.progress.NumberProgressBar;

/* loaded from: classes4.dex */
public final class ActivityRecoveryBinding implements ViewBinding {
    public final TextView filePathTxt;
    public final TextView findtxt;
    public final NavigationBar navigationbar;
    public final NumberProgressBar numberbar;
    public final TextView rectxt;
    private final RelativeLayout rootView;
    public final LinearLayout selFilePath;
    public final LinearLayout seldev;
    public final TextView tvProgress;

    private ActivityRecoveryBinding(RelativeLayout rootView, TextView filePathTxt, TextView findtxt, NavigationBar navigationbar, NumberProgressBar numberbar, TextView rectxt, LinearLayout selFilePath, LinearLayout seldev, TextView tvProgress) {
        this.rootView = rootView;
        this.filePathTxt = filePathTxt;
        this.findtxt = findtxt;
        this.navigationbar = navigationbar;
        this.numberbar = numberbar;
        this.rectxt = rectxt;
        this.selFilePath = selFilePath;
        this.seldev = seldev;
        this.tvProgress = tvProgress;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ActivityRecoveryBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityRecoveryBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_recovery, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityRecoveryBinding bind(View rootView) {
        int i2 = R.id.file_path_txt;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
        if (textView != null) {
            i2 = R.id.findtxt;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
            if (textView2 != null) {
                i2 = R.id.navigationbar;
                NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
                if (navigationBar != null) {
                    i2 = R.id.numberbar;
                    NumberProgressBar numberProgressBar = (NumberProgressBar) ViewBindings.findChildViewById(rootView, i2);
                    if (numberProgressBar != null) {
                        i2 = R.id.rectxt;
                        TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                        if (textView3 != null) {
                            i2 = R.id.sel_file_path;
                            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                            if (linearLayout != null) {
                                i2 = R.id.seldev;
                                LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                                if (linearLayout2 != null) {
                                    i2 = R.id.tv_progress;
                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                    if (textView4 != null) {
                                        return new ActivityRecoveryBinding((RelativeLayout) rootView, textView, textView2, navigationBar, numberProgressBar, textView3, linearLayout, linearLayout2, textView4);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
