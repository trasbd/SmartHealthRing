package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;

/* loaded from: classes4.dex */
public final class ActivityMeGelpfeedbackBinding implements ViewBinding {
    public final EditText etAppVersion;
    public final EditText etBraceletType;
    public final EditText etPhone;
    public final EditText etProblemDescription;
    public final NavigationBar navigationbar;
    public final RecyclerView recycleAddImage;
    private final LinearLayout rootView;
    public final TextView tvAddFeedBack;

    private ActivityMeGelpfeedbackBinding(LinearLayout rootView, EditText etAppVersion, EditText etBraceletType, EditText etPhone, EditText etProblemDescription, NavigationBar navigationbar, RecyclerView recycleAddImage, TextView tvAddFeedBack) {
        this.rootView = rootView;
        this.etAppVersion = etAppVersion;
        this.etBraceletType = etBraceletType;
        this.etPhone = etPhone;
        this.etProblemDescription = etProblemDescription;
        this.navigationbar = navigationbar;
        this.recycleAddImage = recycleAddImage;
        this.tvAddFeedBack = tvAddFeedBack;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityMeGelpfeedbackBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityMeGelpfeedbackBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_me_gelpfeedback, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityMeGelpfeedbackBinding bind(View rootView) {
        int i2 = R.id.et_app_version;
        EditText editText = (EditText) ViewBindings.findChildViewById(rootView, i2);
        if (editText != null) {
            i2 = R.id.et_bracelet_type;
            EditText editText2 = (EditText) ViewBindings.findChildViewById(rootView, i2);
            if (editText2 != null) {
                i2 = R.id.et_phone;
                EditText editText3 = (EditText) ViewBindings.findChildViewById(rootView, i2);
                if (editText3 != null) {
                    i2 = R.id.et_problem_description;
                    EditText editText4 = (EditText) ViewBindings.findChildViewById(rootView, i2);
                    if (editText4 != null) {
                        i2 = R.id.navigationbar;
                        NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
                        if (navigationBar != null) {
                            i2 = R.id.recycle_add_image;
                            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, i2);
                            if (recyclerView != null) {
                                i2 = R.id.tv_add_feed_back;
                                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                if (textView != null) {
                                    return new ActivityMeGelpfeedbackBinding((LinearLayout) rootView, editText, editText2, editText3, editText4, navigationBar, recyclerView, textView);
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
