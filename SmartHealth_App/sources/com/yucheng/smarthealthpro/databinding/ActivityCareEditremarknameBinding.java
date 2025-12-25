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
public final class ActivityCareEditremarknameBinding implements ViewBinding {
    public final EditText edtRemark;
    public final NavigationBar navigationbar;
    private final LinearLayout rootView;
    public final TextView tvComplete;
    public final TextView tvPhone;

    private ActivityCareEditremarknameBinding(LinearLayout rootView, EditText edtRemark, NavigationBar navigationbar, TextView tvComplete, TextView tvPhone) {
        this.rootView = rootView;
        this.edtRemark = edtRemark;
        this.navigationbar = navigationbar;
        this.tvComplete = tvComplete;
        this.tvPhone = tvPhone;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityCareEditremarknameBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityCareEditremarknameBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_care_editremarkname, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityCareEditremarknameBinding bind(View rootView) {
        int i2 = R.id.edt_remark;
        EditText editText = (EditText) ViewBindings.findChildViewById(rootView, i2);
        if (editText != null) {
            i2 = R.id.navigationbar;
            NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
            if (navigationBar != null) {
                i2 = R.id.tv_complete;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                if (textView != null) {
                    i2 = R.id.tv_phone;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                    if (textView2 != null) {
                        return new ActivityCareEditremarknameBinding((LinearLayout) rootView, editText, navigationBar, textView, textView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
