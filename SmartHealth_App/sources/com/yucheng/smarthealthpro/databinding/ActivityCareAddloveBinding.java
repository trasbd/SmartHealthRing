package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;

/* loaded from: classes4.dex */
public final class ActivityCareAddloveBinding implements ViewBinding {
    public final EditText etCarePhone;
    public final LinearLayout llAddQr;
    public final NavigationBar navigationbar;
    private final LinearLayout rootView;
    public final TextView tvAddEdit;

    private ActivityCareAddloveBinding(LinearLayout rootView, EditText etCarePhone, LinearLayout llAddQr, NavigationBar navigationbar, TextView tvAddEdit) {
        this.rootView = rootView;
        this.etCarePhone = etCarePhone;
        this.llAddQr = llAddQr;
        this.navigationbar = navigationbar;
        this.tvAddEdit = tvAddEdit;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityCareAddloveBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityCareAddloveBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_care_addlove, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityCareAddloveBinding bind(View rootView) {
        int i2 = R.id.et_care_phone;
        EditText editText = (EditText) ViewBindings.findChildViewById(rootView, i2);
        if (editText != null) {
            i2 = R.id.ll_add_qr;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
            if (linearLayout != null) {
                i2 = R.id.navigationbar;
                NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
                if (navigationBar != null) {
                    i2 = R.id.tv_add_edit;
                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                    if (textView != null) {
                        return new ActivityCareAddloveBinding((LinearLayout) rootView, editText, linearLayout, navigationBar, textView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
