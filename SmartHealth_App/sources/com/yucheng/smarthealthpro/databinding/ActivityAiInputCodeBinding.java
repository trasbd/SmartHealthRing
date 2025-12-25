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
import com.yucheng.smarthealthpro.login.normal.view.CodeView;

/* loaded from: classes4.dex */
public final class ActivityAiInputCodeBinding implements ViewBinding {
    public final Button inputCodeBtnRegetCode;
    public final TextView inputCodeCanntGetCode;
    public final TextView inputCodeNoteAccount;
    public final CodeView inputCodeView;
    public final NavigationBar navigationbar;
    private final LinearLayout rootView;

    private ActivityAiInputCodeBinding(LinearLayout rootView, Button inputCodeBtnRegetCode, TextView inputCodeCanntGetCode, TextView inputCodeNoteAccount, CodeView inputCodeView, NavigationBar navigationbar) {
        this.rootView = rootView;
        this.inputCodeBtnRegetCode = inputCodeBtnRegetCode;
        this.inputCodeCanntGetCode = inputCodeCanntGetCode;
        this.inputCodeNoteAccount = inputCodeNoteAccount;
        this.inputCodeView = inputCodeView;
        this.navigationbar = navigationbar;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityAiInputCodeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityAiInputCodeBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_ai_input_code, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityAiInputCodeBinding bind(View rootView) {
        int i2 = R.id.input_code_btn_reget_code;
        Button button = (Button) ViewBindings.findChildViewById(rootView, i2);
        if (button != null) {
            i2 = R.id.input_code_cannt_get_code;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
            if (textView != null) {
                i2 = R.id.input_code_note_account;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                if (textView2 != null) {
                    i2 = R.id.input_code_view;
                    CodeView codeView = (CodeView) ViewBindings.findChildViewById(rootView, i2);
                    if (codeView != null) {
                        i2 = R.id.navigationbar;
                        NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
                        if (navigationBar != null) {
                            return new ActivityAiInputCodeBinding((LinearLayout) rootView, button, textView, textView2, codeView, navigationBar);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
