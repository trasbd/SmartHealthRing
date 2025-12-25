package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;

/* loaded from: classes4.dex */
public final class ActivityDeveloperModelBinding implements ViewBinding {
    public final Button btnBattery;
    public final Button btnNav;
    public final Button btnOpenLog;
    public final Button btnShare;
    public final Button btnShare2;
    public final Button btnUpload;
    public final NavigationBar navigationbar;
    private final LinearLayout rootView;
    public final TextView tvRelease;
    public final TextView tvSign;
    public final TextView tvSmartHealth;
    public final TextView tvVersion;
    public final TextView tvVersioncode;

    private ActivityDeveloperModelBinding(LinearLayout rootView, Button btnBattery, Button btnNav, Button btnOpenLog, Button btnShare, Button btnShare2, Button btnUpload, NavigationBar navigationbar, TextView tvRelease, TextView tvSign, TextView tvSmartHealth, TextView tvVersion, TextView tvVersioncode) {
        this.rootView = rootView;
        this.btnBattery = btnBattery;
        this.btnNav = btnNav;
        this.btnOpenLog = btnOpenLog;
        this.btnShare = btnShare;
        this.btnShare2 = btnShare2;
        this.btnUpload = btnUpload;
        this.navigationbar = navigationbar;
        this.tvRelease = tvRelease;
        this.tvSign = tvSign;
        this.tvSmartHealth = tvSmartHealth;
        this.tvVersion = tvVersion;
        this.tvVersioncode = tvVersioncode;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityDeveloperModelBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityDeveloperModelBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_developer_model, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityDeveloperModelBinding bind(View rootView) {
        int i2 = R.id.btn_battery;
        Button button = (Button) ViewBindings.findChildViewById(rootView, i2);
        if (button != null) {
            i2 = R.id.btn_nav;
            Button button2 = (Button) ViewBindings.findChildViewById(rootView, i2);
            if (button2 != null) {
                i2 = R.id.btn_open_log;
                Button button3 = (Button) ViewBindings.findChildViewById(rootView, i2);
                if (button3 != null) {
                    i2 = R.id.btn_share;
                    Button button4 = (Button) ViewBindings.findChildViewById(rootView, i2);
                    if (button4 != null) {
                        i2 = R.id.btn_share2;
                        Button button5 = (Button) ViewBindings.findChildViewById(rootView, i2);
                        if (button5 != null) {
                            i2 = R.id.btn_upload;
                            Button button6 = (Button) ViewBindings.findChildViewById(rootView, i2);
                            if (button6 != null) {
                                i2 = R.id.navigationbar;
                                NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
                                if (navigationBar != null) {
                                    i2 = R.id.tv_release;
                                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                    if (textView != null) {
                                        i2 = R.id.tv_sign;
                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                        if (textView2 != null) {
                                            i2 = R.id.tv_smart_health;
                                            TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                            if (textView3 != null) {
                                                i2 = R.id.tv_version;
                                                TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                if (textView4 != null) {
                                                    i2 = R.id.tv_versioncode;
                                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                    if (textView5 != null) {
                                                        return new ActivityDeveloperModelBinding((LinearLayout) rootView, button, button2, button3, button4, button5, button6, navigationBar, textView, textView2, textView3, textView4, textView5);
                                                    }
                                                }
                                            }
                                        }
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
